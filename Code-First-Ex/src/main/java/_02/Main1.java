package _02;

import _02.entities.Customer;
import _02.entities.Product;
import _02.entities.Sale;
import _02.entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;

public class Main1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEMO");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        Product product = new Product("product", 123.00, BigDecimal.TEN);
        Customer customer =
                new Customer("customer", "customer", "asd");
        StoreLocation location = new StoreLocation("location");
        Date date = new Date();
        Sale sale = new Sale(product, customer, location,date);

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(location);
        entityManager.persist(sale);


        entityManager.getTransaction().commit();
    }
}
