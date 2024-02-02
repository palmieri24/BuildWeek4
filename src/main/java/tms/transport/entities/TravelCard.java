package tms.transport.entities;

import tms.transport.enums.TicketProviderDataTypes;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TravelCard extends TravelId {
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_provider_id")
    private TicketProvider ticketProvider;

    @Enumerated(EnumType.STRING)
    private TicketProviderDataTypes ticketProviderDataTypes;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_of_expire")
    private LocalDate dateOfExpire;

    public TravelCard() {
    }

    public TravelCard(LocalDate dateOfEmission, User user, TicketProviderDataTypes ticketProviderDataTypes, LocalDate dateOfExpire) {
        super(dateOfEmission);
        this.user = user;
        this.ticketProviderDataTypes = ticketProviderDataTypes;
        this.dateOfExpire = dateOfExpire;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateOfExpire() {
        return dateOfExpire;
    }
    public void setDateOfExpire(LocalDate dateOfExpire) {
        this.dateOfExpire = dateOfExpire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public TicketProvider getTicketProvider(){
        return ticketProvider;
    }
    public void setTicketProvider(TicketProvider ticketProvider){
        this.ticketProvider = ticketProvider;
    }

    @Override
    public String toString() {
        return "TravelCard { Card ID = " + id +
                "User ID = " + user.getId() +
                ", dateOfEmission = " + this.getDateOfEmission() +
                ", dateOfExpire = " + dateOfExpire +
                ", TicketProvider = " + ticketProvider.getTicketProviderDataTypes() +
                '}';
    }
}
