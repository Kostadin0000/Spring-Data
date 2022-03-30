package _01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

@PersistenceUnit(unitName = "DEMO1")
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEMO");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


        em.getTransaction().commit();
    }
}
