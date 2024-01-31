package tms.transport.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
public abstract class TravelCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_of_expire")
    private Date dateOfExpire;

    public TravelCard() {
    }

    public TravelCard(long id, Date dateOfExpire) {
        this.id = id;
        this.dateOfExpire = dateOfExpire;
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

    //VALIDITA' ANNUALE TESSERA
    public boolean isExpired(){
        Date today = new Date();
        return today.after(dateOfExpire);
    }

    //RINNOVO TESSERA
    public void renewTravelCard(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfExpire);
        calendar.add(Calendar.YEAR, 1);
        dateOfExpire = calendar.getTime();
    }

    @Override
    public String toString() {
        return "TravelCard {" +
                "id=" + id +
                ", dateOfExpire=" + dateOfExpire +
                '}';
    }
}
