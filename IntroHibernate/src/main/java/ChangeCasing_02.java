import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ChangeCasing_02 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Town> from_town = em.createQuery("SELECT t FROM Town t", Town.class).getResultList();

        for (Town town : from_town) {
            String name = town.getName();
            if (name.length() <= 5){
                String upperName = name.toUpperCase();
                town.setName(upperName);
                em.persist(town);
            }
        }
        em.getTransaction().commit();
    }
}
