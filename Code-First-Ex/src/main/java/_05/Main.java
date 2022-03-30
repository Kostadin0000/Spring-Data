package _05;

import _05.entities.BankAccount;
import _05.entities.CreditCard;
import _05.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        User user1 = new User();
        user1.setEmail("pesho@abv.bg");
        user1.setFirstName("Pesho");
        user1.setLastName("Georgiev");
        user1.setPassword("2222");
        user1.setBillingDetails(new HashSet<>());

        User user2 = new User();
        user2.setEmail("gogo@abv.bg");
        user2.setFirstName("Gosho");
        user2.setLastName("Ivanov");
        user2.setPassword("3424");
        user2.setBillingDetails(new HashSet<>());

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setName("Bank 0");
        bankAccount1.setSwiftCode("Swift 0");
        bankAccount1.setNumber("234113123");
        bankAccount1.setOwner(user1);

        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setName("Bank 1");
        bankAccount2.setSwiftCode("Swift 1");
        bankAccount2.setNumber("2222122321");
        bankAccount2.setOwner(user2);

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setCardType("Card 0");
        creditCard1.setExpirationMonth((byte) 1);
        creditCard1.setExpirationYear((short) 2021);
        creditCard1.setNumber("Card0");
        creditCard1.setOwner(user1);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setCardType("Card 1");
        creditCard2.setExpirationMonth((byte) 2);
        creditCard2.setExpirationYear((short) 2022);
        creditCard2.setNumber("Card1");
        creditCard2.setOwner(user2);

        user1.getBillingDetails().add(bankAccount1);
        user2.getBillingDetails().add(bankAccount2);
        user1.getBillingDetails().add(creditCard1);
        user2.getBillingDetails().add(creditCard2);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DEMO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user1);
            entityManager.persist(user2);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
