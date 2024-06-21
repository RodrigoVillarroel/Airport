package Model;

import java.sql.Time;
import java.util.concurrent.atomic.LongAccumulator;

public class AirportTicket {
    private Location from;
    private Location to;
    private Time bordingTime;
    private Passanger passanger;
    private Double price;
    private String seat;
    private String gate;

    public AirportTicket(Location from, Location to, Time bordingTime, String seat, Passanger passanger, Double price, String gate) {
        this.from = from;
        this.to = to;
        this.bordingTime = bordingTime;
        this.seat = seat;
        this.passanger = passanger;
        this.price = price;
        this.gate = gate;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public Time geBordingTime() {
        return bordingTime;
    }

    public void setBordingTime(Time abordingTime) {
        this.bordingTime = abordingTime;
    }

    public Passanger getPassanger() {
        return passanger;
    }

    public Time getBordingTime() {
        return bordingTime;
    }

    public void setPassanger(Passanger passanger) {
        this.passanger = passanger;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        seat = seat;
    }

    public String getGate() {
        return gate;
    }
}
