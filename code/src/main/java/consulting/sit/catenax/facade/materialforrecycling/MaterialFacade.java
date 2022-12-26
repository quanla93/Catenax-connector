package consulting.sit.catenax.facade.materialforrecycling;


import consulting.sit.catenax.controller.dtos.materialforrecycling.MaterialDTO;
import consulting.sit.catenax.mapper.converttodto.ConvertToMaterialDTO;
import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import consulting.sit.catenax.service.materialforrecycling.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialFacade {

    private final MaterialService materialService;
    private final ConvertToMaterialDTO convertToMaterialDTO;

    public MaterialFacade(final MaterialService materialService, final ConvertToMaterialDTO convertToMaterialDTO) {
        this.materialService = materialService;
        this.convertToMaterialDTO = convertToMaterialDTO;
    }

    public Optional<List<MaterialDTO>> getAllMaterial(){
        List<MaterialModel> materialModels = materialService.getAll();
        if(CollectionUtils.isEmpty(materialModels)){
            return Optional.empty();
        }
        List<MaterialDTO> materialDTOs = convertToMaterialDTO.convertToListMaterialDTO(materialModels);
            return Optional.of(materialDTOs);

    }
}
