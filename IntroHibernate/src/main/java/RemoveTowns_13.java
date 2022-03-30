import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class RemoveTowns_13 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();
        int removedAddresses = 0;

        em.getTransaction().begin();

        Town town = em.createQuery("SELECT t FROM Town t WHERE t.name =:name", Town.class)
                .setParameter("name", townName)
                .getSingleResult();

        List<Address> addresses = em.createQuery("SELECT a FROM Address a WHERE a.town.id=:id", Address.class)
                .setParameter("id", town.getId())
                .getResultList();

        removedAddresses = addresses.size();

        List<Employee> employeeList = em.createQuery("SELECT e FROM Employee e WHERE e.address.town.id =:id", Employee.class)
                .setParameter("id", town.getId())
                .getResultList();

        for (Employee employee : employeeList) {
            employee.setAddress(null);
        }
        for (Address address : addresses) {
            address.setTown(null);
            em.remove(address);
        }
        em.remove(town);

        System.out.printf("%d addresses in %s deleted", removedAddresses, townName);

        em.getTransaction().commit();

    }
}
