package consulting.sit.catenax.repository.shelldescriptors;


import consulting.sit.catenax.model.serialparttypization.SerialPartTypizationModel;
import consulting.sit.catenax.model.shelldescriptors.ShellDescriptorModel;
import consulting.sit.catenax.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShellDescriptorsRepository extends GenericRepository<ShellDescriptorModel, Integer> {
}
