package tms.transport.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "route_id")
  private Long id;

  @Column(name = "departure_point")
  private String departurePoint;

  @Column(name = "arrival_point")
  private String arrivalPoint;

  @Column(name = "avg_time")
  private int averageTime;

  @ManyToMany(mappedBy = "routes")
  private List<Vehicle> vehicles;

  @JoinColumn(name = "vehicle_id")
  private Vehicle vehicle;

  @ManyToMany
  @JoinTable(
          name = "route_stops",
          joinColumns = @JoinColumn(name = "route_id")
  )
  private List<Stop> stops;

  public Route() {
  }

  public Vehicle getVehicle() {
    return vehicle;
  }


  public Long getId() {
    return id;
  }

  public String getDeparturePoint() {
    return departurePoint;
  }

  public void setDeparturePoint(String departurePoint) {
    this.departurePoint = departurePoint;
  }

  public String getArrivalPoint() {
    return arrivalPoint;
  }

  public void setArrivalPoint(String arrivalPoint) {
    this.arrivalPoint = arrivalPoint;
  }

  public double getAverageTime() {
    return averageTime;
  }

  public void setAverageTime(int averageTime) {
    this.averageTime = averageTime;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

  public List<Stop> getStops() {
    return stops;
  }

  public void setStops(List<Stop> stops) {
    this.stops = stops;
  }

  public Route(String departurePoint, String arrivalPoint, int averageTime, List<Vehicle> vehicles, List<Stop> stops) {
    this.departurePoint = departurePoint;
    this.arrivalPoint = arrivalPoint;
    this.averageTime = averageTime;
    this.vehicles = vehicles;
    this.stops = stops;
  }

  public Route(String departurePoint, String arrivalPoint, int averageTime, List<Stop> stops) {
    this.departurePoint = departurePoint;
    this.arrivalPoint = arrivalPoint;
    this.averageTime = averageTime;
    this.stops = stops;
  }

  public void setAvgTime(int avgTime) {
    this.averageTime = avgTime;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
}

