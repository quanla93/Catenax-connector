package consulting.sit.catenax.mapper.converttodto;

import consulting.sit.catenax.controller.dtos.serialparttypization.SerialPartTypizationDTO;
import consulting.sit.catenax.controller.dtos.shelldescriptors.ShellDescriptorDTO;
import consulting.sit.catenax.model.serialparttypization.SerialPartTypizationModel;
import consulting.sit.catenax.model.shelldescriptors.ShellDescriptorModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertToShellDescriptorDTO {

    @Autowired
    private ModelMapper modelMapper;

    public ShellDescriptorDTO convertToShellDescriptorDTO(ShellDescriptorModel shellDescriptorModel) {
        ShellDescriptorDTO shellDescriptorDTO = modelMapper.map(shellDescriptorModel, ShellDescriptorDTO.class);
        return shellDescriptorDTO;
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public List<ShellDescriptorDTO> convertToListShellDescriptorDTO(List<ShellDescriptorModel> shellDescriptorModels) {
        List<ShellDescriptorDTO> shellDescriptorDTOs = mapList(shellDescriptorModels, ShellDescriptorDTO.class);
        return shellDescriptorDTOs;
    }
}
