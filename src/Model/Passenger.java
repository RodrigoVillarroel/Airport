package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.Objects;

public class Passenger extends Person {
    @JsonProperty("passport_number")
    private String nroPassport;

    public Passenger() {
        super();
    }

    public Passenger(String name, String surname,
                     Integer age, Integer numberIdentify,
                     String nroPassport) {
        super(name, surname, age, numberIdentify);
        setNroPassport(nroPassport);
    }

    // region Getters & Setters
    public String getNroPassport() {
        return nroPassport;
    }

    public void setNroPassport(String nroPassport) {
        this.nroPassport = nroPassport;
    }

    // endregion

    @Override
    public int hashCode() {
        return Objects.hash(this.getNumberIdentify(), this.getNroPassport());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Passenger)) return false;
        Passenger passenger = (Passenger) obj;
        return Objects.equals(passenger.getNumberIdentify(), this.getNumberIdentify()) && Objects.equals(passenger.getNroPassport(), this.getNroPassport());
    }

    public String printPassanger(){
        return super.toString() + " - " + (" - Numero de Pasaporte: " + getNroPassport());
    }
}
