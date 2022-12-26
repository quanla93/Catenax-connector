package consulting.sit.catenax.repository;

import consulting.sit.catenax.model.ModelBaseInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericRepository<T extends ModelBaseInterface<I>, I extends Serializable> extends JpaRepository<T, I> {

}
