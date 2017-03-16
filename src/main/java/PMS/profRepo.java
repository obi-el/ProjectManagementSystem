package PMS;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

/**
 * Created by yashpatel on 3/8/2017.
 */
@RepositoryRestResource(collectionResourceRel = "Professors", path = "Professors")
public interface profRepo extends CrudRepository<Professor, Integer>{
    List<Professor> findByFirstName(String firstname);
    List<Professor> findAll();
    boolean existsByEmail(String email);
    Student findByEmail(String Email);

}
