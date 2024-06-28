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
    private String codeFligth;
    public AirportTicket(String from, String to, LocalDateTime bordingTime, String seat, Passenger passenger, Double price, String gate, String codeFligth) {
        this.from = from;
        this.to = to;
        this.bordingTime = bordingTime;
        this.seat = seat;
        this.passenger = passenger;
        this.price = price;
        this.gate = gate;
        this.codeFligth = codeFligth;
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

    public LocalDateTime geBordingTime() {
        return bordingTime;
    }

    public void setBordingTime(LocalDateTime abordingTime) {
        this.bordingTime = abordingTime;
    }
    public String getCodeFligth() {
        return codeFligth;
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

    public void setPassanger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getGate() {
        return gate;
    }

    public void printTicket(){
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("           BOARDING PASS - FLIGHT " + getCodeFligth());
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("From: " + getFrom());
        System.out.println("To: " + getTo());
        System.out.println("Boarding Time: " + geBordingTime());
        System.out.println(passenger.printPassanger());
        System.out.println("Price: $" + getPrice());
        System.out.println("Seat: " + getSeat());
        System.out.println("Gate: " + getGate());
        System.out.println("-------------------------------------------------------------------------------------");
    }
    // endregion
}

