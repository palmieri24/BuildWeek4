package tms.transport.dao;
import tms.transport.entities.TicketProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
public class TicketProvidersDAO {
    private final EntityManagerFactory emf;

    public TicketProvidersDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

  public void save(TicketProvider ticketProvider) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    System.out.println("⚪ Initializing transaction.");
    transaction.begin();
    System.out.println("⚪ Adding new object Ticket Provider to Persistence Context.");
    em.persist(ticketProvider);
    System.out.println("⚪ Saving new Ticket Provider.");
    transaction.commit();
    System.out.println("🟢 New data added.");
    em.close();
  }



}
