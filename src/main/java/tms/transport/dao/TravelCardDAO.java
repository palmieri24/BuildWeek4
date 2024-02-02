package tms.transport.dao;

import tms.transport.entities.TravelCard;
import tms.transport.entities.TravelId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class TravelCardDAO {
    private final EntityManager em;

    public TravelCardDAO(EntityManager em) {
        this.em = em;
    }

    public void save(TravelCard travelCard) {
        EntityTransaction transaction = em.getTransaction();
        System.out.println("⚪ Initializing transaction.");
        transaction.begin();
        System.out.println("⚪ Adding new object Travel Card to Persistence Context.");
        em.persist(travelCard);
        System.out.println("⚪ Saving new Travel Card.");
        transaction.commit();
        System.out.println("🟢 New data added.");
        em.close();
    }

    public TravelCard findTravelCardById(long id) { return em.find(TravelCard.class, id); }

    public List<TravelId> findTravelCardByDates(LocalDate from, LocalDate to) {
        TypedQuery<TravelId> getTravelCard = em.createQuery("SELECT tc FROM TravelId tc WHERE tc.dtype = 'TravelCard' AND tc.dateOfEmission BETWEEN :from AND :to ", TravelId.class);
        getTravelCard.setParameter("from", from);
        getTravelCard.setParameter("to", to);
        return getTravelCard.getResultList();
    }

    //RINNOVA TESSERA ANNUALE
    private LocalDate calculateNewExpire(LocalDate dateOfEmission){
        return dateOfEmission.plusYears(1);
    }
    public void renewTravelCard(long id, LocalDate dateOfEmission, LocalDate dateOfExpire){
        LocalDate newDateOfExpire = calculateNewExpire(dateOfEmission);
        em.createQuery("UPDATE TravelCard tc SET tc.dateOfExpire = :newDateOfExpire " + "WHERE tc.id = :id AND tc.dateOfEmission AND tc.dateOfExpire = :dateOfExpire")
                .setParameter("newDateOfExpire", newDateOfExpire)
                .setParameter("id", id)
                .setParameter("dateOfEmission", dateOfEmission)
                .setParameter("dateOfExpire", dateOfExpire)
                .executeUpdate();

    }

    //




}
