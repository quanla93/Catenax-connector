package consulting.sit.catenax.mapper.converttodto;

import consulting.sit.catenax.controller.dtos.materialforrecycling.ComponentDTO;
import consulting.sit.catenax.controller.dtos.materialforrecycling.MaterialDTO;
import consulting.sit.catenax.controller.dtos.materialforrecycling.QuantityDTO;
import consulting.sit.catenax.model.materialforrecycling.ComponentModel;
import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import consulting.sit.catenax.model.materialforrecycling.QuantityModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertToDTO {

    @Autowired
    private ModelMapper modelMapper;

    public MaterialDTO convertToMaterialDTO(MaterialModel materialModel) {
        MaterialDTO materialDTO = modelMapper.map(materialModel, MaterialDTO.class);
        return materialDTO;

    }

    public ComponentDTO convertComponentDTO(ComponentModel componentModel) {
        ComponentDTO componentDTO = modelMapper.map(componentModel, ComponentDTO.class);
        QuantityDTO quantityDTO = convertQuantityDTO(componentModel.getQuantityModel());
        componentDTO.setQuantity(quantityDTO);
        return componentDTO;
    }

    public QuantityDTO convertQuantityDTO(QuantityModel quantityModel) {
        QuantityDTO quantityDTO = modelMapper.map(quantityModel, QuantityDTO.class);
        return quantityDTO;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public List<MaterialDTO> convertToListMaterialDTO(List<MaterialModel> materialModels) {
//          List<MaterialDTO> materialDTOs = mapList(materialModels, MaterialDTO.class);
        List<MaterialDTO> materialDTOs = materialModels.stream().map(p -> {
            MaterialDTO materialDTO = convertToMaterialDTO(p);
            List<ComponentDTO> componentDTOs = p.getComponents().stream().map(a -> {
                ComponentDTO componentDTO = convertComponentDTO(a);
                return componentDTO;
            }).collect(Collectors.toList());
            materialDTO.setComponents(componentDTOs);
            return materialDTO;
        }).collect(Collectors.toList());

        return materialDTOs;
    }


}
