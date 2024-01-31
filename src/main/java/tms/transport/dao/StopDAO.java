package tms.transport.dao;

import tms.transport.entities.Stop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class StopDAO {
  private final EntityManagerFactory emf;

  public StopDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void addStop(String stopName) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    try {
      System.out.println("⚪ Initializing transaction.");
      transaction.begin();

      System.out.println("⚪ Adding new object Stop to Persistence Context.");
      Stop newStop = new Stop();
      newStop.setStopName(stopName);
      em.persist(newStop);

      System.out.println("⚪ Saving new Stop.");
      transaction.commit();
      System.out.println("🟢 New Stop added.");
    } catch (RuntimeException e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
  }

  public void editStop(Long stopId, String newName) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      System.out.println("⚪ Searching for Stop with ID: " + stopId);

      Stop stop = em.find(Stop.class, stopId);
      if (stop == null) {
        System.out.println("❌ Stop with ID " + stopId + " not found.");
        return;
      }

      System.out.println("⚪ Editing Stop with ID: " + stopId);
      stop.setStopName(newName);

      transaction.commit();
      System.out.println("🟢 Stop with ID " + stopId + " updated to new name: " + newName);
    } catch (RuntimeException e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
  }

  public void deleteStop(Long stopId) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      System.out.println("⚪ Searching for Stop with ID: " + stopId);

      Stop stop = em.find(Stop.class, stopId);
      if (stop == null) {
        System.out.println("❌ Stop with ID " + stopId + " not found.");
        return;
      }

      System.out.println("⚪ Deleting Stop with ID: " + stopId);
      em.remove(stop);

      transaction.commit();
      System.out.println("🔴 Stop with ID " + stopId + " deleted.");
    } catch (RuntimeException e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
  }

  public void displayAllStops() {
    EntityManager em = emf.createEntityManager();
    try {
      List<Stop> stops = em.createQuery("SELECT s FROM Stop s", Stop.class).getResultList();

      if (stops.isEmpty()) {
        System.out.println("Nessuna fermata trovata nel database.");
      } else {
        System.out.println("Elenco delle fermate:");
        for (Stop stop : stops) {
          System.out.println("ID Fermata: " + stop.getStopId() + ", Nome Fermata: " + stop.getStopName());
        }
      }
    } finally {
      em.close();
    }
  }
}
