package tms.transport.dao;

import tms.transport.entities.Vehicle;
import tms.transport.enums.VehicleDataTypes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

  public void editVehicle(Long vehicleId, String vehicleType, int newCapacity) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
      System.out.println("⚪ Initializing transaction.");
      transaction.begin();

      Vehicle vehicle = em.find(Vehicle.class, vehicleId);

      if (vehicle != null) {
        vehicle.setCapacity(newCapacity);
        vehicle.setVehicleType(VehicleDataTypes.valueOf(vehicleType));
        em.merge(vehicle);

        System.out.println("⚪ Saving edited Vehicle.");
        transaction.commit();
        System.out.println("🟢 Vehicle edited successfully.");
      } else {
        System.out.println("🔴 Vehicle with ID " + vehicleId + " not found.");
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

  public void displayAllVehicles() {
    EntityManager em = emf.createEntityManager();
    try {
      List<Vehicle> vehicles = em.createQuery("SELECT v FROM Vehicle v", Vehicle.class).getResultList();

      if (vehicles.isEmpty()) {
        System.out.println("Nessun veicolo trovato nel database.");
      } else {
        System.out.println("Elenco dei veicoli:");
        for (Vehicle vehicle : vehicles) {
          System.out.println("ID Veicolo: " + vehicle.getId() + ", Tipo: " + vehicle.getVehicleType() + ", Capacità: " + vehicle.getCapacity());
        }
      }
    } finally {
      em.close();
    }
  }

  public List<Vehicle> getVehiclesList() {
    EntityManager em = emf.createEntityManager();
    try {
      return em.createQuery("SELECT v FROM Vehicle v", Vehicle.class).getResultList();
    } finally {
      em.close();
    }
  }

//  metodo validazione del ticket
}
