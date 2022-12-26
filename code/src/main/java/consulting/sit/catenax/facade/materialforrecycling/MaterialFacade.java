package consulting.sit.catenax.facade.materialforrecycling;


import consulting.sit.catenax.controller.dtos.materialforrecycling.MaterialDTO;
import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import consulting.sit.catenax.service.materialforrecycling.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialFacade {


    @Autowired
    private ModelMapper modelMapper;

    private final MaterialService materialService;

    public MaterialFacade(final MaterialService materialService) {
        this.materialService = materialService;
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public Optional<List<MaterialDTO>> getAllMaterial(){
        List<MaterialModel> materialModels = materialService.getAll();
        if(CollectionUtils.isEmpty(materialModels)){
            return Optional.empty();
        } else {
            List<MaterialDTO> materialDTOs = mapList(materialModels, MaterialDTO.class);
            return Optional.of(materialDTOs);
        }
    }
}
