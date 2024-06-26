package Model;

import java.time.LocalDateTime;


public class AirportTicket {
    private String from;
    private String to;
    private LocalDateTime bordingTime;
    private Passenger passenger;
    private Double price;
    private String seat;
    private String gate;

    public AirportTicket(String from, String to, LocalDateTime bordingTime, String seat, Passenger passenger, Double price, String gate) {
        this.from = from;
        this.to = to;
        this.bordingTime = bordingTime;
        this.seat = seat;
        this.passenger = passenger;
        this.price = price;
        this.gate = gate;
    }

    // region Getters & Setters
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

    public Passenger getPassenger() {
        return passenger;
    }

    public LocalDateTime getBordingTime() {
        return bordingTime;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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

    @Override
    public String toString() {
        return "AirportTicket{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", bordingTime=" + bordingTime +
                ", passenger=" + passenger +
                ", price=" + price +
                ", seat='" + seat + '\'' +
                ", gate='" + gate + '\'' +
                '}';
    }
    // endregion
}

