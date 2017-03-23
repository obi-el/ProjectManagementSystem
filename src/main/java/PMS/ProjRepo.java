package PMS;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Obinna on 3/22/2017.
 */
@RepositoryRestResource(collectionResourceRel = "Projects", path = "Projects")
public interface ProjRepo extends CrudRepository<Project, Integer> {
    Project findByName(String name);
    List<Project> findAll();
    boolean existsByName(String name);
    List<Project> findBySupervisor(Professor supervisor);
}
