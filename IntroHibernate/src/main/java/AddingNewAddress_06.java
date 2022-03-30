import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;


public class AddingNewAddress_06 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        String nameFromInput = scanner.nextLine();

        em.getTransaction().begin();

        Town town = new Town();
        town.setName("Plovdiv");
        em.persist(town);

        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);
        em.persist(address);

        Employee employee = em.createQuery("SELECT e FROM Employee e WHERE e.lastName = :name", Employee.class)
                .setParameter("name", nameFromInput)
                .getSingleResult();

        employee.setAddress(address);
        em.persist(employee);

        em.getTransaction().commit();
    }
}
