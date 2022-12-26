package consulting.sit.catenax.mapper.converttodto;

import consulting.sit.catenax.controller.dtos.materialforrecycling.MaterialDTO;
import consulting.sit.catenax.controller.dtos.serialparttypization.SerialPartTypizationDTO;
import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import consulting.sit.catenax.model.serialparttypization.SerialPartTypizationModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertToSerialPartTypizationDTO {

    @Autowired
    private ModelMapper modelMapper;

    public SerialPartTypizationDTO convertToSerialPartTypizationDTO(SerialPartTypizationModel serialPartTypizationModel) {
        SerialPartTypizationDTO serialPartTypizationDTO = modelMapper.map(serialPartTypizationModel, SerialPartTypizationDTO.class);
        return serialPartTypizationDTO;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public List<SerialPartTypizationDTO> convertToListSerialPartTypizationDTO(List<SerialPartTypizationModel> serialPartTypizationModels) {
        List<SerialPartTypizationDTO> serialPartTypizationDTOs = mapList(serialPartTypizationModels, SerialPartTypizationDTO.class);
        return serialPartTypizationDTOs;
    }
}
