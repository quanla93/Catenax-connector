package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CatalogDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("contractOffers")
    private List<ContractOfferDTO> contractOffers;
}
