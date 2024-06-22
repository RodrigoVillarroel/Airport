package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;

public class Passanger extends Person {
    @JsonProperty("passport_number")
    private String nroPassport;
    private Luggage luggage;

    public Passanger() {
        super();
    }

    public Passanger(String name, String surname,
                     Integer age, Integer numberIdentify,
                     String nroPassport, Luggage luggage) {
        super(name, surname, age, numberIdentify);
        setNroPassport(nroPassport);
        setLuggage(luggage);
    }

    public Passanger(String name, String surname,
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

    public Luggage getLuggage() {
        return luggage;
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }
    // endregion

    public int isOverweight() {
       return getLuggage().isOverweight();
    }

    @Override
    public String toString() {
        return super.toString().concat(MessageFormat.format("Passanger'{'nroPassport=''{0}'', luggage={1}'}'", getNroPassport(), getLuggage()));
    }

    public int getSizeLuggage(){
        return luggage.getLuggage().size();
    }

}
