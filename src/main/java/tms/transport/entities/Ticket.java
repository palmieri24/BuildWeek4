package tms.transport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity()
public class Ticket extends TransportDocument {

    @Column(name = "checked_date")
    private LocalDate checkedDate;

    public Ticket() {
    }

    public Ticket(LocalDate dateOfEmission, LocalDate checkedDate) {
        super(dateOfEmission);
        this.checkedDate = checkedDate;
    }

    public LocalDate getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(LocalDate checkedDate) {
        this.checkedDate = checkedDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + getId() + ", " +
                "dateOfEmission=" + getDateOfEmission() + ", " +
                "checkedDate=" + checkedDate +
                '}';
    }
}