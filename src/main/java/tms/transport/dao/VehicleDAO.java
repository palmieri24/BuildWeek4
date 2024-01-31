package tms.transport.dao;

import tms.transport.entities.Vehicle;
import tms.transport.enums.VehicleDataTypes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class VehicleDAO {
  private final EntityManagerFactory emf;

  public VehicleDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void addVehicle(VehicleDataTypes vehicleType, int capacity) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
      System.out.println("⚪ Initializing transaction.");
      transaction.begin();

      Vehicle vehicle = new Vehicle(vehicleType, capacity);

      em.persist(vehicle);

      System.out.println("⚪ Saving new Vehicle.");
      transaction.commit();
      System.out.println("🟢 New Vehicle added.");
    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }

  public void editVehicle(Long vehicleId, VehicleDataTypes vehicleType, int capacity) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
      System.out.println("⚪ Initializing transaction.");
      transaction.begin();

      Vehicle vehicle = em.find(Vehicle.class, vehicleId);

      if (vehicle != null) {
        vehicle.setVehicleType(vehicleType);
        vehicle.setCapacity(capacity);

        System.out.println("⚪ Updating Vehicle.");
        em.merge(vehicle);

        transaction.commit();
        System.out.println("🟢 Vehicle updated.");
      } else {
        System.out.println("🔴 Vehicle not found.");
      }
    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }

  public void deleteVehicle(Long id) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
      System.out.println("⚪ Initializing transaction.");
      transaction.begin();

      Vehicle vehicle = em.find(Vehicle.class, id);

      if (vehicle != null) {
        em.remove(vehicle);

        System.out.println("⚪ Deleting Vehicle.");
        transaction.commit();
        System.out.println("🟢 Vehicle deleted successfully.");
      } else {
        System.out.println("🔴 Vehicle with ID " + id + " not found.");
      }
    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }
}

