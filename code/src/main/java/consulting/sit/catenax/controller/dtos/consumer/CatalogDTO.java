package consulting.sit.catenax.controller.dtos.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CatalogDTO {
    @JsonProperty("id")
    String id;
    @JsonProperty("contractOffers")
    List<ContractOfferDTO> contractOffers;
}
