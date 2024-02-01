package tms.transport.entities;

import tms.transport.enums.TicketProviderDataTypes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Entity
public class TravelCard extends TravelId {
    @OneToOne
    @JoinColumn(name = "id", unique = true, nullable = false)
    private User user;
    private TicketProviders ticketProvider;

    @Enumerated(EnumType.STRING)
    private TicketProviderDataTypes ticketProviderDataTypes;
    @Id
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
    @JoinColumn(name = "ticket_providers")
    public TicketProviders getTicketProvider(){
        return ticketProvider;
    }
    public void setTicketProvider(TicketProviders ticketProvider){
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
