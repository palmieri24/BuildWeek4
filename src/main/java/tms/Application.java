package tms;

import com.github.javafaker.Faker;
import tms.transport.dao.*;
import tms.transport.entities.*;
import tms.transport.enums.SubscriptionPeriodicityDataTypes;
import tms.transport.enums.TicketProviderDataTypes;

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
       User user = new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday(18, 99));
//        userDAO.save(user);

//        TicketDAO td = new TicketDAO(em);
  //      SubscriptionDAO sd = new SubscriptionDAO(em);
  //      Subscription sub = new Subscription(LocalDate.of(2023, 5, 5), null, SubscriptionPeriodicityDataTypes.MONTHLY);
////        sd.save(sub);
//
//        System.out.println("FIND TICKETS BY DATE");
//        List<TransportDocument> yearTicket = td.findTicketsByDates(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
//        yearTicket.forEach(System.out::println);
//
//        System.out.println("FIND SUBSCRIPTIONS BY DATE");
//        List<TransportDocument> yearSub = sd.findSubscriptionsByDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
//        yearSub.forEach(System.out::println);

        //RINNOVA TESSERA
       /* TravelCardDAO tc = new TravelCardDAO(em);
        LocalDate emissionDate = LocalDate.of(2020, 1, 8);
        LocalDate expireDate = LocalDate.of(2021,1,8);
        TravelCard travelCard = new TravelCard(emissionDate, user, TicketProviderDataTypes.MACHINE, expireDate);
        tc.renewTravelCard(travelCard.getId(), emissionDate, expireDate);
        System.out.println("Tessera" + travelCard.getId() + "rinnovata con successo!"); */

        //VERIFICA VALIDITA' ABBONAMENTO
       /* boolean subValidity = sd.isSubscriptionValid(sub.getId());
        if (subValidity == true){
            System.out.println("Abbonamento valido!");
        } else {
            System.out.println("Abbonamento non valido!");
        }*/




        System.out.println("🔴 Chiusura dell'EntityManagerFactory & EntityManager alla fine dell'applicazione");
        emf.close();
        em.close();
    }
}
