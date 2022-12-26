package consulting.sit.catenax.service.shelldescriptors;


import consulting.sit.catenax.model.serialparttypization.SerialPartTypizationModel;
import consulting.sit.catenax.model.shelldescriptors.ShellDescriptorModel;
import consulting.sit.catenax.repository.serialparttypization.SerialPartTypizationRepository;
import consulting.sit.catenax.repository.shelldescriptors.ShellDescriptorsRepository;
import consulting.sit.catenax.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShellDescriptorService extends GenericService<ShellDescriptorModel, Integer> {

    @Autowired
    private ShellDescriptorsRepository shellDescriptorsRepository;

    public List<ShellDescriptorModel> getAll() {
        List<ShellDescriptorModel> shellDescriptorModels = shellDescriptorsRepository.findAll();
        return shellDescriptorModels;
    }
}
