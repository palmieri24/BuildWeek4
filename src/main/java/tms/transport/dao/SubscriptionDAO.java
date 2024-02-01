package tms.transport.dao;

import tms.transport.entities.Subscription;
import tms.transport.entities.TravelId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
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

    public List<TravelId> findSubscriptionsByDate(LocalDate from, LocalDate to) {
        TypedQuery<TravelId> getSubscriptionsByDate = em.createQuery("SELECT t FROM TravelId t WHERE t.dtype = 'Subscription' AND t.dateOfEmission BETWEEN :from AND :to", TravelId.class);
        getSubscriptionsByDate.setParameter("from", from);
        getSubscriptionsByDate.setParameter("to", to);
        return getSubscriptionsByDate.getResultList();
    }

    public List<TravelId> findSubcriptionsByProviderId(long providerId) {
        TypedQuery<TravelId> getSubscriptions = em.createQuery("SELECT t FROM TravelId WHERE t.dtype = 'Subscription' AND t.ticketProvider = :providerId", TravelId.class);
        getSubscriptions.setParameter("providerId", providerId);
        return getSubscriptions.getResultList();
    }


    public boolean isSubscriptionValid(long id){
        try {
            Query query = em.createQuery("SELECT tc.subscription FROM TravelCard tc WHERE tc.id = :id");
            query.setParameter("id", id);
            Subscription subscription = (Subscription) query.getSingleResult();
            if (subscription != null){
                LocalDate currentDate = LocalDate.now();
                if (currentDate.isBefore(subscription.getDateOfEmission()) && currentDate.isBefore(subscription.getDateOfExpire())){
                    return true;
                }
            }
        }catch (NoResultException ex){
            System.out.println("Errore: Nessun abbonamento trovato!" );
        }
        return false;
    }
}
