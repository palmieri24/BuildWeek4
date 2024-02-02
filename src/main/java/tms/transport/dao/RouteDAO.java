package tms.transport.dao;

import tms.transport.entities.Route;
import tms.transport.entities.Stop;
import tms.transport.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class RouteDAO {
  private final EntityManagerFactory emf;

  public RouteDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void addRoute(Long departureStopId, Long arrivalStopId, int averageTime) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
      System.out.println("⚪ Initializing transaction for adding new Route.");
      transaction.begin();

      Stop departureStop = em.find(Stop.class, departureStopId);
      Stop arrivalStop = em.find(Stop.class, arrivalStopId);

      if (departureStop != null && arrivalStop != null) {
        Route route = new Route();
        route.setDeparturePoint(departureStop.getStopName());
        route.setArrivalPoint(arrivalStop.getStopName());
        route.setAverageTime(averageTime);

        em.persist(route);

        System.out.println("⚪ Saving new Route.");
        transaction.commit();
        System.out.println("🟢 New Route added successfully.");
      } else {
        System.out.println("🔴 Departure or Arrival Stop not found.");
      }
    } catch (Exception e) {
      System.out.println("🔴 Error adding new Route.");
      if (transaction.isActive()) {
        transaction.rollback();
        System.out.println("🔴 Transaction rolled back.");
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }

  public void editRoute(long id, Stop newDeparturePoint, Stop newArrivalPoint, double newAvgTime) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
      System.out.println("⚪ Initializing transaction for editing Route.");
      transaction.begin();

      Route route = em.find(Route.class, id);
      if (route != null) {
        route.setDeparturePoint(String.valueOf(newDeparturePoint));
        route.setArrivalPoint(String.valueOf(newArrivalPoint));
        route.setAvgTime((int) newAvgTime);

        System.out.println("⚪ Saving edited Route.");
        transaction.commit();
        System.out.println("🟢 Route edited successfully.");
      } else {
        System.out.println("🔴 Route with ID " + id + " not found.");
      }
    } catch (Exception e) {
      System.out.println("🔴 Error editing Route.");
      if (transaction.isActive()) {
        transaction.rollback();
        System.out.println("🔴 Transaction rolled back.");
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }

  public void deleteRoute(long id) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
      System.out.println("⚪ Initializing transaction for deleting Route.");
      transaction.begin();

      Route route = em.find(Route.class, id);
      if (route != null) {
        em.remove(route);

        System.out.println("⚪ Deleting Route.");
        transaction.commit();
        System.out.println("🟢 Route deleted successfully.");
      } else {
        System.out.println("🔴 Route with ID " + id + " not found.");
      }
    } catch (Exception e) {
      System.out.println("🔴 Error deleting Route.");
      if (transaction.isActive()) {
        transaction.rollback();
        System.out.println("🔴 Transaction rolled back.");
      }
      e.fillInStackTrace();
    } finally {
      em.close();
    }
  }

  public void displayAllRoutes() {
    EntityManager em = emf.createEntityManager();
    try {
      System.out.println("⚪ Fetching all Routes from the database.");
      List<Route> routes = em.createQuery("SELECT r FROM Route r", Route.class).getResultList();

      if (routes.isEmpty()) {
        System.out.println("🔴 No Routes found in the database.");
      } else {
        System.out.println("🟢 List of Routes:");
        for (Route route : routes) {
          System.out.println("ID Tratta: " + route.getId() +
                  ", Tappa di Partenza: " + route.getDeparturePoint() +
                  ", Tappa di Arrivo: " + route.getArrivalPoint());
        }
      }
    } finally {
      em.close();
    }
  }

  public void assignVehicleToRoute(Long routeId, Long vehicleId) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();

    try {
      System.out.println("⚪ Initializing transaction for assigning Vehicle to Route.");
      transaction.begin();

      Route route = em.find(Route.class, routeId);
      Vehicle vehicle = em.find(Vehicle.class, vehicleId);

      if (route != null && vehicle != null) {
        route.setVehicle(vehicle);
        em.merge(route);
        transaction.commit();
        System.out.println("🟢 Vehicle assigned to Route successfully.");
      } else {
        System.out.println("🔴 Route or Vehicle not found.");
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

  public List<Route> getRoutesList() {
    EntityManager em = emf.createEntityManager();
    try {
      return em.createQuery("SELECT r FROM Route r", Route.class).getResultList();
    } finally {
      em.close();
    }
  }

  public int getAvgTime(Long routeId) {
    EntityManager em = emf.createEntityManager();
    try {
      System.out.println("⚪ Initializing transaction for retrieving Route Average Time.");
      TypedQuery<Integer> query = em.createQuery(
              "SELECT r.averageTime FROM Route r WHERE r.id = :routeId\n", Integer.class);
      query.setParameter("routeId", routeId);
      Integer avgTime = query.getSingleResult();
      return avgTime != null ? avgTime : 0;
    } finally {
      em.close();
    }
  }
}
