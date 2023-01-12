package consulting.sit.catenax.service;

import consulting.sit.catenax.controller.dtos.consumer.CatalogDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsDTO;
import consulting.sit.catenax.controller.dtos.consumer.DataDestinationDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.OfferResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.StateDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsRequestDTO;
import consulting.sit.catenax.controller.dtos.consumer.ContractNegotiationsResponseDTO;
import consulting.sit.catenax.controller.dtos.consumer.TransferProcessDTO;
import consulting.sit.catenax.controller.dtos.shelldescriptors.EndpointDTO;
import consulting.sit.catenax.controller.dtos.shelldescriptors.ProtocolInformationDTO;
import consulting.sit.catenax.controller.dtos.shelldescriptors.ShellDescriptorDTO;
import consulting.sit.catenax.controller.dtos.shelldescriptors.SubModelDescriptorDTO;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ConsumerService {

    private static final String TYPE = "HttpProxy";
    private static final String MANAGEDRESOURCES = "false";
    private static final String CONTRACTOFFERCATALOG = "/data/catalog";
    private static final String CONTRACTNEGOTIATIONIDURL = "/data/contractnegotiations";
    private static final String INITIATETRANSFERURL = "/data/transferprocess";

    private static final String LOOKUP_SHELLS_PATH = "/registry/lookup/shells";
    private static final String SHELL_DESCRIPTORS = "/registry/registry/shell-descriptors";

    @Value("${edc.registry.url}")
    private String edcRegistryUrl;

    @Value("${edc.token}")
    private String token;

    @Value("${edc.consumer.control.plane}")
    private String edcConsumerControlplane;

    @Value("${edc.consumer.backend.application}")
    private String edcConsumerBackend;
    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * get all contract offer catalog
     *
     * @param  providerUrl
     * @return CatalogDTO
     */

    public CatalogDTO getContractOfferCatalog(String providerUrl){
        String contractOfferUrl = new StringBuilder().append(edcConsumerControlplane).append(CONTRACTOFFERCATALOG).toString();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(contractOfferUrl).queryParam("providerUrl", providerUrl);
        CatalogDTO catalogDTO = webClientBuilder.build()
                .get()
                .uri(builder.build().toUri())
                .headers(h -> {
                    h.set("X-Api-Key", "password");
                    h.setBearerAuth(token);
                })
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CatalogDTO.class)
                .block();

        return catalogDTO;
    }

    /**
     * request Offer Response
     *
     * @param  OfferRequestDTO
     * @return OfferResponseDTO
     */
    public OfferResponseDTO requestOfferResponse(OfferRequestDTO offerRequestDTO) {
        String offerUrl = new StringBuilder().append(edcConsumerControlplane).append(CONTRACTNEGOTIATIONIDURL).toString();
        OfferResponseDTO offerResponseDTO = webClientBuilder.build()
                .post()
                .uri(offerUrl)
                .body(Mono.just(offerRequestDTO), OfferRequestDTO.class)
                .headers(h -> {
                    h.set("X-Api-Key", "password");
                })
                .retrieve()
                .bodyToMono(OfferResponseDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
        return offerResponseDTO;
    }

    /**
     * get final data transfer
     *
     * @param
     * @return TransferProcessDTO
     */

    public ContractNegotiationsDTO requestContractNegotiationID(OfferResponseDTO offerResponseDTO) {
        String contractNegotiationUrl = new StringBuilder().append(edcConsumerControlplane).append(CONTRACTNEGOTIATIONIDURL).append("/").append(offerResponseDTO.getId()).toString();
        ContractNegotiationsDTO contractNegotiationsDTO = new ContractNegotiationsDTO();
        for (int i=1; i <= 5; i ++) {
            if (contractNegotiationsDTO.getContractAgreementId() == null) {
                contractNegotiationsDTO = webClientBuilder.build()
                        .get()
                        .uri(contractNegotiationUrl)
                        .headers(h -> {
                            h.set("X-Api-Key", "password");
                        })
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(ContractNegotiationsDTO.class)
                        .delaySubscription(Duration.ofMillis(2000))
                        .block();
            } else if (contractNegotiationsDTO.getContractAgreementId() == null || i >5) {
                return null; // here to return error
            }
        }

        return contractNegotiationsDTO;
    }

    /**
     * request Initiate Transfer for provider
     *
     * @param ContractNegotiationsDTO, OfferRequestDTO
     * @return ContractNegotiationsResponseDTO
     */

    public ContractNegotiationsResponseDTO requestInitiateTransfer(ContractNegotiationsDTO contractNegotiationsDTO, OfferRequestDTO offerRequestDTO) {
        String initiateTransferUrl = new StringBuilder().append(edcConsumerControlplane).append(INITIATETRANSFERURL).toString();

        ContractNegotiationsRequestDTO contractNegotiationsRequestDTO = new ContractNegotiationsRequestDTO();
        UUID randomId = UUID.randomUUID();
        contractNegotiationsRequestDTO.setId(randomId.toString());
        contractNegotiationsRequestDTO.setAssetId(offerRequestDTO.getOffer().getAssetId());
        contractNegotiationsRequestDTO.setConnectorAddress(offerRequestDTO.getConnectorAddress());
        contractNegotiationsRequestDTO.setConnectorId(offerRequestDTO.getConnectorId());
        contractNegotiationsRequestDTO.setContractId(contractNegotiationsDTO.getContractAgreementId());
        contractNegotiationsRequestDTO.setManagedResources(MANAGEDRESOURCES);
        DataDestinationDTO dataDestinationDTO = new DataDestinationDTO();
        dataDestinationDTO.setType(TYPE);
        contractNegotiationsRequestDTO.setDataDestination(dataDestinationDTO);

               ContractNegotiationsResponseDTO contractNegotiationsResponseDTO = webClientBuilder.build()
                .post()
                .uri(initiateTransferUrl)
                .body(Mono.just(contractNegotiationsRequestDTO), ContractNegotiationsRequestDTO.class)
                .headers(h -> {
                    h.set("X-Api-Key", "password");
                })
                .retrieve()
                .bodyToMono(ContractNegotiationsResponseDTO.class)
                .timeout(Duration.ofMillis(10_000))
                .delaySubscription(Duration.ofMillis(500))
                .block();
        contractNegotiationsResponseDTO.setTransferProcessId(contractNegotiationsRequestDTO.getId());
        return contractNegotiationsResponseDTO;
    }

    /**
     * get final data transfer
     *
     * @param
     * @return TransferProcessDTO
     */

    public List<TransferProcessDTO> getAllTransferProcessData(){
        String transferProcessUrl = new StringBuilder().append(edcConsumerControlplane).append(INITIATETRANSFERURL).toString();
        Flux<TransferProcessDTO> transferProcessDTOFlux = webClientBuilder.build()
                .get()
                .uri(transferProcessUrl)
                .headers(h -> {
                    h.set("X-Api-Key", "password");
                })
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(TransferProcessDTO.class);

        List<TransferProcessDTO> transferProcessDTOs = transferProcessDTOFlux
                .collect(Collectors.toList())
                .share().block();
        return transferProcessDTOs;
    }

    /**
     * get final data transfer
     *
     * @param transferProcessId
     * @return data
     */

    public String getTransferProcessData(String transferProcessId){
        String transferProcessUrl = new StringBuilder().append(edcConsumerBackend).append("/").append(transferProcessId).toString();
        return webClientBuilder.build()
                .get()
                .uri(transferProcessUrl)
                .headers(h -> {
                    h.set("Accept", "application/octet-stream");
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * Check state of the transfer process
     *
     * @param transferId
     * @return state
     */
    public StateDTO checkStateOfTheTransfer(final String transferId) {
        String checkStateUrl = new StringBuilder().append(edcConsumerControlplane).append(INITIATETRANSFERURL).append("/" + transferId + "/state").toString();
        StateDTO stateDTO = webClientBuilder.build()
                .get()
                .uri(checkStateUrl)
//                .uri(uriBuilder -> uriBuilder.path(checkStateUrl + "/{transferId}/state").build(transferId))
                .headers(h -> {
                    h.set("X-Api-Key", "password");
                })
                .retrieve()
                .bodyToMono(StateDTO.class)
                .block();

        return stateDTO;
    }


    /**
     * Lookup asset
     *
     * @param assetIds
     * @return
     */
    private String lookupAsset(final String assetIds) {
        String lookupUrl = new StringBuilder().append(edcRegistryUrl).append(LOOKUP_SHELLS_PATH).toString();
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(edcRegistryUrl);
//        try {
//            SpecificAssetIdDTO assetIdDTO = objectMapper.readValue(assetIds, SpecificAssetIdDTO.class);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(lookupUrl).queryParam("assetIds", assetIds);
        String[] digitalTwinIds = webClientBuilder
                .baseUrl(edcRegistryUrl)
                .uriBuilderFactory(uriBuilderFactory)
                .build()
                .get()
                .uri(builder.build().toUri())
                .headers(h -> {
                    h.setBearerAuth(token);
                    h.set("X-Api-Key", "password");
                })
//                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String[].class)
                .block();
        // return the first record.
        if (digitalTwinIds != null && digitalTwinIds.length > 0) {
            return digitalTwinIds[0];
        } else {
            return null;
        }
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

//        } catch (SSLException e) {
//            throw new RuntimeException(e);
//        }
    }

    /**
     * Get Shell descriptors
     *
     * @param digitalTwinId
     */
    private ShellDescriptorDTO getShellDescriptors(final String digitalTwinId) {
        // Call to get shell descriptors
//        log.info("Digital TwinId = " + digitalTwinId);
        String getShellDescriptorsUrl = new StringBuilder().append(SHELL_DESCRIPTORS).toString();
        ShellDescriptorDTO shellDescriptor = webClientBuilder
                .baseUrl(edcRegistryUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(getShellDescriptorsUrl + "/{digitalTwinId}").build(digitalTwinId))
                .headers(h -> {
                    h.setBearerAuth(token);
                    h.set("X-Api-Key", "password");
                })
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ShellDescriptorDTO.class)
                .block();
        return shellDescriptor;
    }

    /**
     * Get shell descriptor from digitalTwinId and digitalSubModelTwinId
     *
     * @param digitalTwinId
     * @param digitalSubModelTwinId
     * @return
     */
    private SubModelDescriptorDTO getShellDescriptors(final String digitalTwinId, final String digitalSubModelTwinId) {
        // Call to get digitalSubModelTwinId
        String getShellDescriptorsUrl = new StringBuilder().append(edcConsumerControlplane).append(SHELL_DESCRIPTORS).toString();
        SubModelDescriptorDTO subModelDescriptor = webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder.path(getShellDescriptorsUrl + "/{digitalTwinId}/submodel-descriptors/{digitalSubModelTwinId}").build(digitalTwinId, digitalSubModelTwinId))
                .header("X-Api-Key", "password")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SubModelDescriptorDTO.class)
                .block();
        return subModelDescriptor;
    }

    /**
     * Lookup provider Url from assetIds
     *
     * @param assetIds
     * @return
     */
    public String lookupProviderUrl(final String assetIds) {
        String digitalTwinId = this.lookupAsset(assetIds);

        if (digitalTwinId != null) {
            ShellDescriptorDTO shellDescriptor = this.getShellDescriptors(digitalTwinId);
            // check sub model from shell descriptor
            List<SubModelDescriptorDTO> subModelDescriptors = shellDescriptor.getSubmodelDescriptors();
            if (!CollectionUtils.isEmpty(subModelDescriptors)) {
                SubModelDescriptorDTO subModelDescriptor = subModelDescriptors.get(0);
                List<EndpointDTO> endpoints = subModelDescriptor.getEndpoints();
                if (!CollectionUtils.isEmpty(endpoints)) {
                    EndpointDTO endpoint = endpoints.get(0);
                    ProtocolInformationDTO protocolInformation = endpoint.getProtocolInformation();
                    if (protocolInformation != null) {
                        return protocolInformation.getEndpointAddress();
                    }
                }
            }
            return null;
        } else {
            return null;
        }
    }

}
