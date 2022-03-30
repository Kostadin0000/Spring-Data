import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GetEmployeeWithProject_08 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        int readID = Integer.parseInt(scanner.nextLine());

        em.getTransaction().begin();

        Employee employee = em.createQuery("SELECT e FROM Employee e WHERE e.id = :takeId", Employee.class)
                .setParameter("takeId", readID).getSingleResult();

        List<Project> collect = employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (Project project : collect) {
            sb.append(project.getName()).append(System.lineSeparator()).append("   ");
        }

        System.out.println(employee.getFirstName() + " " + employee.getLastName()
                + " - " + employee.getJobTitle());
        System.out.println("   " + sb.toString().trim());

        em.getTransaction().commit();
    }
}
