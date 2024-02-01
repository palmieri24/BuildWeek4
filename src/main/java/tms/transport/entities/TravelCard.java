package tms.transport.entities;

import javax.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_of_expire")
    private Date dateOfExpire;

    public TravelCard() {
    }

    public TravelCard(long id, Date dateOfExpire, User user, TicketProviders ticketProvider) {
        this.id = id;
        this.dateOfExpire = dateOfExpire;
        this.user = user;
        this.ticketProvider = ticketProvider;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfExpire() {
        return dateOfExpire;
    }
    public void setDateOfExpire(Date dateOfExpire) {
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
