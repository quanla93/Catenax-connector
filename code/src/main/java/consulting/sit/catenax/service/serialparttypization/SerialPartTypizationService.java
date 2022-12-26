package consulting.sit.catenax.service.serialparttypization;


import consulting.sit.catenax.model.serialparttypization.SerialPartTypizationModel;
import consulting.sit.catenax.repository.serialparttypization.SerialPartTypizationRepository;
import consulting.sit.catenax.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerialPartTypizationService extends GenericService<SerialPartTypizationModel, Integer> {

    @Autowired
    private SerialPartTypizationRepository serialPartTypizationRepository;

    public List<SerialPartTypizationModel> getAll() {
        List<SerialPartTypizationModel> serialPartTypizationModels = serialPartTypizationRepository.findAll();
        return serialPartTypizationModels;
    }
}
