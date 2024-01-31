package tms.transport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "tickets")
public class Ticket extends TransportDocument {

    @Column(name = "checked_date")
    private LocalDate checkedDate;

    public Ticket() {
    }



}
