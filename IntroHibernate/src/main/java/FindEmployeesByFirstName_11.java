import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName_11 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String concat = scanner.nextLine().concat("%");

        List<Employee> resultList = em.createQuery("SELECT e FROM Employee e " +
                " WHERE e.firstName LIKE :formatter ", Employee.class).setParameter("formatter", concat).getResultList();


        for (Employee employee : resultList) {
            System.out.printf(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getJobTitle() + " - ($%.2f)%n" , employee.getSalary());
        }

        em.getTransaction().commit();

    }
}
