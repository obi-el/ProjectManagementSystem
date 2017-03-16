package PMS;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

/**
 * Created by yashpatel on 3/8/2017.
 */
@RepositoryRestResource(collectionResourceRel = "Students", path = "Students")
public interface StudentRepo extends CrudRepository<Student, Integer>{
    List<Student> findByFirstName(String firstname);
    List<Student> findAll();
    boolean existsByEmail(String email);
    Student findByEmail(String Email);

}
