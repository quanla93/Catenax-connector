package consulting.sit.catenax.facade.shelldescriptor;

import consulting.sit.catenax.controller.dtos.shelldescriptors.ShellDescriptorDTO;
import consulting.sit.catenax.mapper.converttodto.ConvertToShellDescriptorDTO;
import consulting.sit.catenax.model.serialparttypization.SerialPartTypizationModel;
import consulting.sit.catenax.model.shelldescriptors.ShellDescriptorModel;
import consulting.sit.catenax.service.shelldescriptors.ShellDescriptorService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ShellDescriptorFacade {

    private final ShellDescriptorService shellDescriptorService;
    private final ConvertToShellDescriptorDTO convertToShellDescriptorDTO;

    public ShellDescriptorFacade(final ShellDescriptorService shellDescriptorService, final ConvertToShellDescriptorDTO convertToShellDescriptorDTO) {
        this.shellDescriptorService = shellDescriptorService;
        this.convertToShellDescriptorDTO = convertToShellDescriptorDTO;
    }


    public Optional<List<ShellDescriptorDTO>> getAllShellDescriptor(){
        List<ShellDescriptorModel> shellDescriptorModels = shellDescriptorService.getAll();
        if(CollectionUtils.isEmpty(shellDescriptorModels)){
            return Optional.empty();
        }
        List<ShellDescriptorDTO> shellDescriptorDTOs = convertToShellDescriptorDTO.convertToListShellDescriptorDTO(shellDescriptorModels);
        return Optional.of(shellDescriptorDTOs);

    }
}
