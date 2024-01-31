package tms;

import com.github.javafaker.Faker;
import tms.transport.dao.SubscriptionDAO;
import tms.transport.dao.TicketDAO;
import tms.transport.dao.UserDAO;
import tms.transport.entities.Subscription;
import tms.transport.entities.Ticket;
import tms.transport.entities.TransportDocument;
import tms.transport.entities.User;
import tms.transport.enums.SubscriptionPeriodicityDataTypes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        System.out.println("⚪ Creazione dell'Entity Manager Factory (emf) per l'accesso al database.");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tms");

        EntityManager em = emf.createEntityManager();

        System.out.println("⚪ Creazione DAO...");
        Faker faker = new Faker();
        UserDAO userDAO = new UserDAO(emf);

        // 👤 Aggiungi utente
//        User user = new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday(18, 99));
//        userDAO.save(user);

//        TicketDAO td = new TicketDAO(em);
//        SubscriptionDAO sd = new SubscriptionDAO(em);
//        Subscription sub = new Subscription(LocalDate.of(2023, 5, 5), null, SubscriptionPeriodicityDataTypes.MONTHLY);
////        sd.save(sub);
//
//        System.out.println("FIND TICKETS BY DATE");
//        List<TransportDocument> yearTicket = td.findTicketsByDates(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
//        yearTicket.forEach(System.out::println);
//
//        System.out.println("FIND SUBSCRIPTIONS BY DATE");
//        List<TransportDocument> yearSub = sd.findSubscriptionsByDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
//        yearSub.forEach(System.out::println);


        System.out.println("🔴 Chiusura dell'EntityManagerFactory & EntityManager alla fine dell'applicazione");
        emf.close();
        em.close();
    }
}
