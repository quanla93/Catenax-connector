package consulting.sit.catenax.repository.serialparttypization;


import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import consulting.sit.catenax.model.serialparttypization.SerialPartTypizationModel;
import consulting.sit.catenax.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialPartTypizationRepository extends GenericRepository<SerialPartTypizationModel, Integer> {
}
