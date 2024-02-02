package tms.transport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Entity(name = "tickets")
public class Ticket extends TravelId {
    @Column(name = "checked_date")
    private LocalDate checkedDate;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private TicketProvider ticketProvider;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Ticket() {
    }

    public Ticket(LocalDate dateOfEmission, LocalDate checkedDate, TicketProviders ticketProvider, Vehicle vehicle) {
        super(dateOfEmission);
        this.checkedDate = checkedDate;
        this.ticketProvider = ticketProvider;
        this.vehicle = vehicle;
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