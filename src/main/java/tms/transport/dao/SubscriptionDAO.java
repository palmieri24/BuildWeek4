package tms.transport.dao;

import tms.transport.entities.Subscription;
import tms.transport.entities.TransportDocument;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class SubscriptionDAO {
    private final EntityManager em;

    public SubscriptionDAO(EntityManager em) { this.em = em; }

    public void save(Subscription subscription) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(subscription);
        transaction.commit();
        System.out.println("Subscription with ID: '" + subscription.getId() + "' added to DB");
    }
    public Subscription findById(long id) { return em.find(Subscription.class, id); }

    public void deleteById(long id) {
        Subscription found = this.findById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Subscription with ID = '" + found.getId() + "' deleted from DB");
        } else { System.out.println("Subscription with ID = '" + found.getId() + "' not found"); }
    }

    public List<TransportDocument> findSubscriptionsByDate(LocalDate from, LocalDate to) {
        TypedQuery<TransportDocument> getSubscriptionsByDate = em.createQuery("SELECT t FROM TransportDocument t WHERE t.dtype = 'Subscription' AND t.dateOfEmission BETWEEN :from AND :to", TransportDocument.class);
        getSubscriptionsByDate.setParameter("from", from);
        getSubscriptionsByDate.setParameter("to", to);
        return getSubscriptionsByDate.getResultList();
    }


}
