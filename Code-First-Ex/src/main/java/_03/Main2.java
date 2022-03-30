package _03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEMO");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();


        entityManager.getTransaction().commit();
    }
}
