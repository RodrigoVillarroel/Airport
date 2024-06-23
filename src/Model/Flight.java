package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Flight {
    @JsonProperty("code")
    private String code;
    @JsonProperty("airplane")
    private Airplane airplane;
    @JsonProperty("door")
    private String door;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("destiny")
    private String destiny;

    // TODO
    // `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling
    @JsonProperty("time")
    private LocalDateTime time;
    @JsonProperty("state")
    private String stateFlight;

    public Flight() {

    }

    public Flight(String code,String door, Airplane airplane, String origin, String destiny, LocalDateTime time, String stateFlight) {
        setCode(code);
        setDoor(door);
        setAirplane(airplane);
        setOrigin(origin);
        setDestiny(destiny);
        setTime(time);
        setStateFlight(stateFlight);
    }

    // region Getters & Setters
    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStateFlight() {
        return stateFlight;
    }

    public void setStateFlight(String stateFlight) {
        this.stateFlight = stateFlight;
    }
    // endregion

    @Override
    public String toString() {
        return MessageFormat.format("Flight'{'code=''{0}'', airplane={1}, door=''{2}'', origin=''{3}'', destiny=''{4}'', time={5}, stateFlight=''{6}'''}'", getCode(), getAirplane(), getDoor(), getOrigin(), getDestiny(), getTime(), getStateFlight());
    }

    public String selectSeat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese LETRA y NUMERO del asieto que desee comprar:");

        System.out.println("FORMATO REQUERIDO (LETRA MAYUSCULA/NUMERO)");
        String seat = scanner.nextLine();

        return seat;
    }

}
