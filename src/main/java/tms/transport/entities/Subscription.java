package tms.transport.entities;

import tms.transport.enums.SubscriptionPeriodicityDataTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "subscriptions")
@Entity
public class Subscription extends TransportDocument {
    @Column
    private SubscriptionPeriodicityDataTypes periodicity;

    public Subscription() {
    }
}
