package tms.transport.dao;

import tms.transport.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class TicketProvidersDAO {
    private final EntityManagerFactory emf;

    public TicketProvidersDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Ticket ticket) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        System.out.println("⚪ Initializing transaction.");
        transaction.begin();
        System.out.println("⚪ Adding new object Ticket to Persistence Context.");
        em.persist(ticket);
        System.out.println("⚪ Saving new Ticket.");
        transaction.commit();
        System.out.println("🟢 New data added.");
        em.close();
    }





}
