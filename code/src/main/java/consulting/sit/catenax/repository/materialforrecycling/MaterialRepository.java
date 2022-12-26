package consulting.sit.catenax.repository.materialforrecycling;


import consulting.sit.catenax.model.materialforrecycling.MaterialModel;
import consulting.sit.catenax.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends GenericRepository<MaterialModel, Integer> {
}
