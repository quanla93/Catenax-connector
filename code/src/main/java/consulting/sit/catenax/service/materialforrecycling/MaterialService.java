package consulting.sit.catenax.service.materialforrecycling;


import consulting.sit.catenax.controller.dtos.materialforrecycling.ComponentDTO;
import consulting.sit.catenax.controller.dtos.materialforrecycling.MaterialDTO;
import consulting.sit.catenax.controller.dtos.materialforrecycling.QuantityDTO;
import consulting.sit.catenax.mapper.converttodto.ConvertToDTO;
import consulting.sit.catenax.model.materialforrecycling.ComponentModel;
import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import consulting.sit.catenax.repository.materialforrecycling.MaterialRepository;
import consulting.sit.catenax.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService extends GenericService<MaterialModel, Integer> {

    @Autowired
    private MaterialRepository materialRepository;

    public List<MaterialModel> getAll() {
        List<MaterialModel> materialModels = materialRepository.findAll();
        return materialModels;
    }
}
