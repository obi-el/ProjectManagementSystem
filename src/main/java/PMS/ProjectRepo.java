package PMS;

import org.hibernate.validator.internal.metadata.provider.MetaDataProviderKeyedByClassName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

/**
 * Created by yashpatel on 3/15/2017.
 */

public interface ProjectRepo extends CrudRepository<Project, Integer>{
    List<Project> findAll();
}
