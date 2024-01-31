package tms.transport.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TravelId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_of_emission")
    private LocalDate dateOfEmission;

    public TravelId() {
    }

    public TravelId(LocalDate dateOfEmission) {
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
}
