package Models;

import java.text.MessageFormat;
import java.time.LocalDateTime;

public class Flight {
    private String code;
    private Airplane airplane;
    private String door;
    private String origin;
    private String destiny;
    private LocalDateTime time;
    private String stateFlight;

    public Flight(String code,String door, Airplane airplane, String origin, String destiny, LocalDateTime time, String stateFlight) {
        setCode(code);
        setDoor(door);
        setAirplane(airplane);
        setOrigin(origin);
        setDestiny(destiny);
        setTime(time);
        setStateFlight(stateFlight);
    }

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

    @Override
    public String toString() {
        return MessageFormat.format("Flight'{'code=''{0}'', airplane={1}, door=''{2}'', origin=''{3}'', destiny=''{4}'', time={5}, stateFlight=''{6}'''}'", getCode(), getAirplane(), getDoor(), getOrigin(), getDestiny(), getTime(), getStateFlight());
    }
}
