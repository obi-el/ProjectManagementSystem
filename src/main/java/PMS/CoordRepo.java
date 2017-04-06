package PMS;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

/**
 * Created by Matt on 3/23/2017.
*/
@RepositoryRestResource(collectionResourceRel = "Coordinators", path = "Coordinators")
public interface CoordRepo extends CrudRepository<Coordinator, Integer> {
    List<Coordinator> findByFirstName(String firstname);
    List<Coordinator> findAll();
    boolean existsByEmail(String email);
    Coordinator findByEmail(String Email);

}
