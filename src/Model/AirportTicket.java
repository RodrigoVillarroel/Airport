package Model;

import java.time.LocalDateTime;


public class AirportTicket {
    private String from;
    private String to;
    private LocalDateTime bordingTime;
    private Passanger passanger;
    private Double price;
    private String seat;
    private String gate;

    public AirportTicket(String from, String to, LocalDateTime bordingTime, String seat, Passanger passanger, Double price, String gate) {
        this.from = from;
        this.to = to;
        this.bordingTime = bordingTime;
        this.seat = seat;
        this.passanger = passanger;
        this.price = price;
        this.gate = gate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime geBordingTime() {
        return bordingTime;
    }

    public void setBordingTime(LocalDateTime abordingTime) {
        this.bordingTime = abordingTime;
    }

    public Passanger getPassanger() {
        return passanger;
    }

    public LocalDateTime getBordingTime() {
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

