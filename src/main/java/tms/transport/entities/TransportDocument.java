package tms.transport.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class TransportDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(insertable = false, updatable = false)
    private String dtype;


  @Column(name = "date_of_emission")
  private LocalDate dateOfEmission;

  public TransportDocument() {
  }

  public TransportDocument(LocalDate dateOfEmission) {
    this.dateOfEmission = dateOfEmission;
  }

  public long getId() {
    return id;
  }

  public LocalDate getDateOfEmission() {
    return dateOfEmission;
  }


    public void setDateOfEmission(LocalDate dateOfEmission) {
        this.dateOfEmission = dateOfEmission;
    }

    @Override
    public String toString() {
        return "TransportDocument{" +
                "id=" + id +
                ", dateOfEmission=" + dateOfEmission +
                '}';
    }

}
