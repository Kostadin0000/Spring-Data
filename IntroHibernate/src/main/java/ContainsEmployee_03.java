import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee_03 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        String[] names = scanner.nextLine().split("\\s+");

        em.getTransaction().begin();

        Long firstResult = em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.firstName = :first_Name AND e.lastName = :last_Name", Long.class)
                .setParameter("first_Name", names[0])
                .setParameter("last_Name", names[1])
                .getSingleResult();

        if (firstResult == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
        em.getTransaction().commit();
    }
}
