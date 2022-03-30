import entities.Address;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressesWithEmployeeCount_07 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        List<Address> resultList = em.createQuery("SELECT a FROM Address a ORDER BY a.employees.size desc", Address.class).setMaxResults(10).getResultList();

        for (Address address : resultList) {
            System.out.println(address.getText() + ", " + address.getTown().getName() + "- " + address.getEmployees().size());
        }

        em.getTransaction().commit();
    }
}
