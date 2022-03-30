import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000_04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<String> resultList = em.createQuery("SELECT e.firstName FROM Employee e WHERE e.salary > 50000", String.class).getResultList();

        for (String firstName : resultList) {
            System.out.println(firstName);
        }

        em.getTransaction().commit();
    }
}
