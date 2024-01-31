package tms.transport.entities;

import tms.transport.enums.TicketProviderDataTypes;

import javax.persistence.*;

@Entity
@Table(name = "ticket_provider")
public abstract class TicketProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "ticket_emitter")
    @Enumerated(EnumType.STRING)
    private TicketProviderDataTypes ticketProviderDataTypes;

    public TicketProvider() {
    }

    public TicketProvider(long id, boolean status, TicketProviderDataTypes ticketProviderDataTypes) {
        this.id = id;
        this.status = status;
        this.ticketProviderDataTypes = ticketProviderDataTypes;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
