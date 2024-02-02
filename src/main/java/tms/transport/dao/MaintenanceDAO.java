package tms.transport.dao;

import tms.transport.entities.Maintenance;
import tms.transport.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class MaintenanceDAO {
  private final EntityManagerFactory emf;

  public MaintenanceDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void addMaintenance(Long vehicleId, Date startDate, Date endDate) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      System.out.println("⚪ Adding new Maintenance record.");

      Vehicle vehicle = em.find(Vehicle.class, vehicleId);
      if (vehicle == null) {
        System.out.println("🔴 Vehicle with ID " + vehicleId + " not found.");
        return;
      }

      Maintenance maintenance = new Maintenance();
      maintenance.setVehicle(vehicle);
      maintenance.setStartDate(startDate);
      maintenance.setEndDate(endDate);
      em.persist(maintenance);

      transaction.commit();
      System.out.println("🟢 New Maintenance added successfully.");
    } catch (Exception e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }

  public void editMaintenance(Long maintenanceId, Date newStartDate, Date newEndDate) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      System.out.println("⚪ Editing Maintenance record.");

      Maintenance maintenance = em.find(Maintenance.class, maintenanceId);
      if (maintenance == null) {
        System.out.println("🔴 Maintenance with ID " + maintenanceId + " not found.");
        return;
      }

      maintenance.setStartDate(newStartDate);
      maintenance.setEndDate(newEndDate);
      em.merge(maintenance);

      transaction.commit();
      System.out.println("🟢 Maintenance edited successfully.");
    } catch (Exception e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }

  public void deleteMaintenance(Long maintenanceId) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    try {
      transaction.begin();
      System.out.println("⚪ Deleting Maintenance record.");

      Maintenance maintenance = em.find(Maintenance.class, maintenanceId);
      if (maintenance == null) {
        System.out.println("🔴 Maintenance with ID " + maintenanceId + " not found.");
        return;
      }

      em.remove(maintenance);

      transaction.commit();
      System.out.println("🔴 Maintenance deleted successfully.");
    } catch (Exception e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }
}