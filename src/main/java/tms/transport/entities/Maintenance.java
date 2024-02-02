package tms.transport.entities;

import tms.transport.enums.VehicleDataTypes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "maintenance")
public class Maintenance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "maintenance_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "vehicle_id")
  private Vehicle vehicle;

  @Column(name = "maintenance_start_date")
  @Temporal(TemporalType.DATE)
  private Date maintenanceStartDate;

  @Column(name = "maintenance_end_date")
  @Temporal(TemporalType.DATE)
  private Date maintenanceEndDate;

  public Long getId() {
    return id;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public Date getMaintenanceStartDate() {
    return maintenanceStartDate;
  }

  public void setMaintenanceStartDate(Date maintenanceStartDate) {
    this.maintenanceStartDate = maintenanceStartDate;
  }

  public Date getMaintenanceEndDate() {
    return maintenanceEndDate;
  }

  public void setMaintenanceEndDate(Date maintenanceEndDate) {
    this.maintenanceEndDate = maintenanceEndDate;
  }

  public Maintenance() {
  }

  public Maintenance(Vehicle vehicle, Date maintenanceStartDate, Date maintenanceEndDate) {
    this.vehicle = vehicle;
    this.maintenanceStartDate = maintenanceStartDate;
    this.maintenanceEndDate = maintenanceEndDate;
  }
}
