import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FindLatestProjects_09 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        List<Project> resultList = em.createQuery("SELECT p FROM Project p ORDER BY p.startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList();

        List<Project> collect = resultList.stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());

        DateTimeFormatter zonedFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Project project : collect) {
            System.out.printf("Project name: %s\n" +
                    " \tProject Description: %s...\n" +
                    " \tProject Start Date: %s\n" +
                    " \tProject End Date: %s\n", project.getName(), project.getDescription().substring(0,36), project.getStartDate().format(zonedFormatter), project.getEndDate());
        }

        em.getTransaction().commit();
    }
}
