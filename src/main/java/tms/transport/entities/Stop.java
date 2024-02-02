package tms.transport.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stops")
public class Stop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stop_id")
  private Long stopId;

  @Column(name = "stop_name")
  private String stopName;

  @ManyToMany(mappedBy = "stops")
  private List<Route> routes;

  public Stop() {
  }

  public Long getStopId() {
    return stopId;
  }

  public String getStopName() {
    return stopName;
  }

  public void setStopName(String stopName) {
    this.stopName = stopName;
  }

  public Stop(String stopName, List<Route> routes) {
    this.stopName = stopName;
    this.routes = routes;
  }
}
