package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.LinkedList;

public class Airport {
    @JsonProperty("airlines")
    private LinkedList<Airline> airlines;
    @JsonProperty("passengers")
    private HashSet<Passanger> passangers;


    public Airport(LinkedList<Airline> airlines, HashSet<Passanger> passangers) {
        setAirlines(airlines);
        setPassangers(passangers);
    }

    public Airport() {
        setAirlines(new LinkedList<>());
        setPassangers(new HashSet<>());
    }

    // region Getters & Setters
    public LinkedList<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(LinkedList<Airline> airlines) {
        this.airlines = airlines;
    }

    public HashSet<Passanger> getPassangers() {
        return passangers;
    }

    public void setPassangers(HashSet<Passanger> passangers) {
        this.passangers = passangers;
    }
    // endregion

    @Override
    public String toString() {
        return MessageFormat.format("Airport'{'airlines={0}, passangers={1}'}'", getAirlines(), getPassangers());
    }
}
