package consulting.sit.catenax.facade.serialparttypization;

import consulting.sit.catenax.controller.dtos.serialparttypization.SerialPartTypizationDTO;
import consulting.sit.catenax.mapper.converttodto.ConvertToSerialPartTypizationDTO;
import consulting.sit.catenax.model.serialparttypization.SerialPartTypizationModel;
import consulting.sit.catenax.service.serialparttypization.SerialPartTypizationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SerialPartTypizationFacade {

    private final SerialPartTypizationService serialPartTypizationService;
    private final ConvertToSerialPartTypizationDTO convertToSerialPartTypizationDTO;

    public SerialPartTypizationFacade(final SerialPartTypizationService serialPartTypizationService, final ConvertToSerialPartTypizationDTO convertToSerialPartTypizationDTO) {
        this.serialPartTypizationService = serialPartTypizationService;
        this.convertToSerialPartTypizationDTO = convertToSerialPartTypizationDTO;
    }


    public Optional<List<SerialPartTypizationDTO>> getAllSerialPartTypization(){
        List<SerialPartTypizationModel> serialPartTypizationModels = serialPartTypizationService.getAll();
        if(CollectionUtils.isEmpty(serialPartTypizationModels)){
            return Optional.empty();
        }
        List<SerialPartTypizationDTO> serialPartTypizationDTOs = convertToSerialPartTypizationDTO.convertToListSerialPartTypizationDTO(serialPartTypizationModels);
        return Optional.of(serialPartTypizationDTOs);

    }
}
