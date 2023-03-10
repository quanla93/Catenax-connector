package consulting.sit.catenax.controller.dtos.shelldescriptors;

import lombok.Data;

import java.util.List;

@Data
public class ShellDescriptorDTO {
    private List<DescriptionDTO> description;
    private GlobalAssetIdDTO globalAssetId;
    private String idShort;
    private String identification;
    private List<SpecificAssetIdDTO> specificAssetIds;
    private List<SubModelDescriptorDTO> submodelDescriptors;
}
