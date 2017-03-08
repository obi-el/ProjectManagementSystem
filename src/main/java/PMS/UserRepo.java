package PMS;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

/**
 * Created by yashpatel on 3/8/2017.
 */
@RepositoryRestResource(collectionResourceRel = "Project", path = "Project")
public interface UserRepo extends CrudRepository<User, Integer>{
    List<Student> findByFirstName(String firstname);
}
