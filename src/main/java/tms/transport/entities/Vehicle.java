package tms.transport.entities;

import tms.transport.enums.VehicleDataTypes;
import javax.persistence.*;
import java.util.List;

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
  private List<Ticket> tickets;

  @ManyToMany(mappedBy = "vehicles")
  private List<Route> routes;

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

  public Vehicle() {
  }

  public Vehicle(VehicleDataTypes vehicleType, int capacity, boolean maintenance) {
    this.vehicleType = vehicleType;
    this.capacity = capacity;
    this.maintenance = maintenance;
  }
}
