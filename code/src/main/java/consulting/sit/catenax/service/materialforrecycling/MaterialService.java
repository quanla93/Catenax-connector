package consulting.sit.catenax.service.materialforrecycling;


import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import consulting.sit.catenax.repository.materialforrecycling.MaterialRepository;
import consulting.sit.catenax.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService extends GenericService<MaterialModel, Integer> {

    @Autowired
    private MaterialRepository materialRepository;

    public List<MaterialModel> getAll() {
        List<MaterialModel> materialModels = materialRepository.findAll();
        return materialModels;
    }
}
