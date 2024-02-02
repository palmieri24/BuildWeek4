package tms.transport.entities;

import tms.transport.enums.TicketProviderDataTypes;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ticket_provider")
public class TicketProviders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "ticket_emitter")
    @Enumerated(EnumType.STRING)
    private TicketProviderDataTypes ticketProviderDataTypes;

     @OneToMany(mappedBy = "ticketProvider")
     private List<Ticket> ticketList;

    private Set<TravelCard> travelCards;
    @OneToMany(mappedBy = "travel_card")
    public Set<TravelCard> getTravelCard(){
        return travelCards;
    }
    public void setTravelCards(Set<TravelCard> travelCards){
        this.travelCards = travelCards;
    }

    public TicketProviders() {
    }

    public TicketProviders(boolean status, TicketProviderDataTypes ticketProviderDataTypes) {
        this.status = status;
        this.ticketProviderDataTypes = ticketProviderDataTypes;
    }

    public long getId() {
        return id;
    }

//VERIFICARE STATO DEL DISTRIBUTORE
    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TicketProviderDataTypes getTicketProviderDataTypes() {
        return ticketProviderDataTypes;
    }

    public void setTicketProviderDataTypes(TicketProviderDataTypes ticketProviderDataTypes) {
        this.ticketProviderDataTypes = ticketProviderDataTypes;
    }

    @Override
    public String toString() {
        return "TicketProvider { ticketProvider= " + ticketProviderDataTypes +
                ", status=" + status +
                '}';
    }



}
