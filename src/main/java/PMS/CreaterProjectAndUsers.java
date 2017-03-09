package PMS;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by zeningjiang on 3/9/2017.
 */
public class CreaterProjectAndUsers {
    public CreaterProjectAndUsers() {
    }

    public static void main(String[] args) {
        // Create the EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        for(Project project: projects) {
            em.persist(project);
        }

        for(User user: users) {
            user.setProject(projects[0]);
            projects[0].addMembers(user);
            em.persist(user);
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    private static Project[] projects = new Project[] {

    };

    public static User[] users = new User[] {

    };
}
