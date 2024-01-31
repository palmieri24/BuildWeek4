package tms.transport.entities;

import tms.transport.enums.VehicleDataTypes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Vehicle {

  public Vehicle(VehicleDataTypes vehicleType, int capacity) {
  }

  @Entity
  @Table(name = "vehicles")
  public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;
    @Column(name = "vehicle_type")
    private VehicleDataTypes vehicleType;
    private int capacity;
    private boolean maintenance;

    @OneToMany(mappedBy = "vehicle")
    private Set<Route> routes = new HashSet<>();

    public Long getId() {
      return id;
    }

    public VehicleDataTypes getVehicleType() {
      return vehicleType;
    }

    public void setVehicleType(VehicleDataTypes vehicleType) {
      this.vehicleType = vehicleType;
    }

    public int getCapacity() {
      return capacity;
    }

    public void setCapacity(int capacity) {
      this.capacity = capacity;
    }

    public boolean isMaintenance() {
      return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
      this.maintenance = maintenance;
    }

    public Set<Route> getRoutes() {
      return routes;
    }

    public void setRoutes(Set<Route> routes) {
      this.routes = routes;
    }

    public Vehicle() {
    }

    public Vehicle(VehicleDataTypes vehicleType, int capacity, Set<Route> routes) {
      this.vehicleType = vehicleType;
      this.capacity = capacity;
      this.routes = routes;
    }
  }
}
