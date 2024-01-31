package tms.transport.entities;

import tms.transport.enums.VehicleDataTypes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicles")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vehicle_id")
  private Long id;
  @Enumerated(EnumType.STRING)
  @Column(name = "vehicle_type")
  private VehicleDataTypes vehicleType;
  private int capacity;
  private boolean maintenance;

  @OneToMany(mappedBy = "vehicle")
  private Set<Route> routes = new HashSet<>();

  public Vehicle() {
  }

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

  public Vehicle(VehicleDataTypes vehicleType, int capacity) {
    this.vehicleType = vehicleType;
    this.capacity = capacity;
  }
}
