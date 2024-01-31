package tms.transport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "tickets")
public class Ticket extends TravelId {

    @Column(name = "checked_date")
    private LocalDate checkedDate;

    public Ticket() {
    }

}