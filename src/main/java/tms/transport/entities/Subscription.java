package tms.transport.entities;

import tms.transport.enums.SubscriptionPeriodicityDataTypes;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "subscriptions")
@Entity
public class Subscription extends TravelId {
    @Column
    private LocalDate dateOfExpire;
    @Enumerated(EnumType.STRING)
    private SubscriptionPeriodicityDataTypes periodicity;


    public Subscription() {
    }

    public Subscription(LocalDate dateOfEmission, LocalDate dateOfExpire, SubscriptionPeriodicityDataTypes periodicity) {
        super(dateOfEmission);
        this.periodicity = periodicity;
        this.dateOfExpire = dateOfExpire;
    }

    public LocalDate getDateOfExpire() {
        return dateOfExpire;
    }

    public void setDateOfExpire(LocalDate dateOfExpire) {
        this.dateOfExpire = dateOfExpire;
    }

    public SubscriptionPeriodicityDataTypes getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(SubscriptionPeriodicityDataTypes periodicity) {
        this.periodicity = periodicity;
    }

}
