package tms;

import com.github.javafaker.Faker;
import tms.transport.dao.UserDAO;
import tms.transport.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {

    public static void main(String[] args) {
        System.out.println("⚪ Creazione dell'Entity Manager Factory (emf) per l'accesso al database.");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tms");

        EntityManager em = emf.createEntityManager();

        System.out.println("⚪ Creazione DAO...");
        Faker faker = new Faker();
        UserDAO userDAO = new UserDAO(emf);

        // 👤 Aggiungi utente
        User user = new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday(18, 99));
        userDAO.save(user);

        System.out.println("🔴 Chiusura dell'EntityManagerFactory alla fine dell'applicazione");
        emf.close();
    }
}
