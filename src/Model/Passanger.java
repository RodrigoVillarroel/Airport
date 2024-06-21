package Model;

import java.text.MessageFormat;

public class Passanger extends Person{
    private String nroPassport;
    private Luggage luggage;

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

    public boolean isOverweight(){
        boolean response = false;
        int i = 0;
        while (i <= luggage.getLuggage().size() && !response) {
            response = getLuggage().isOverweight(i);
            i++;
        }
        return response;
    }

    @Override
    public String toString() {
        return super.toString().concat(MessageFormat.format("Passanger'{'nroPassport=''{0}'', luggage={1}'}'", getNroPassport(), getLuggage()));
    }
}
