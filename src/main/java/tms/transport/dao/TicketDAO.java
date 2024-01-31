package tms.transport.dao;

import tms.transport.entities.Ticket;
import tms.transport.entities.TransportDocument;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class TicketDAO {
    private final EntityManager em;

    public TicketDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Ticket ticket) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(ticket);
        transaction.commit();
        System.out.println("Ticket with ID: '" + ticket.getId() + "' added to DB");
    }
    public Ticket findById(long id) { return em.find(Ticket.class, id); }

    public void deleteById(long id) {
        Ticket found = this.findById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Ticket with ID: '" + id + "' deleted from DB");
        } else { System.out.println("Ticket with ID: '" + id + "' not found"); }
    }

    public List<TransportDocument> findTicketsByDates(LocalDate from, LocalDate to) {
        TypedQuery<TransportDocument> getTickets = em.createQuery("SELECT t FROM TransportDocument t WHERE t.dtype = 'Ticket' AND t.dateOfEmission BETWEEN :from AND :to ", TransportDocument.class);
        getTickets.setParameter("from", from);
        getTickets.setParameter("to", to);
        return getTickets.getResultList();
    }
}
