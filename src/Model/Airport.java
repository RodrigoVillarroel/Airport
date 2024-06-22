package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;

public class Airport {
    @JsonProperty("airlines")
    private LinkedList<Airline> airlines;
    @JsonProperty("passengers")
    private HashSet<Passanger> passangers;
    AirportTicketOffice airportTicketOffice;
    OnlineTicketOffice onlineTicketOffice;


    public Airport(LinkedList<Airline> airlines, HashSet<Passanger> passangers) {
        setAirlines(airlines);
        setPassangers(passangers);
    }

    public Airport() {
        setAirlines(new LinkedList<>());
        setPassangers(new HashSet<>());
        airportTicketOffice = new AirportTicketOffice();
        onlineTicketOffice = new OnlineTicketOffice();
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

    public AirportTicketOffice getAirportTicketOffice() {
        return airportTicketOffice;
    }

    public OnlineTicketOffice getOnlineTicketOffice() {
        return onlineTicketOffice;
    }

    // endregion

    @Override
    public String toString() {
        return MessageFormat.format("Airport'{'airlines={0}, passangers={1}'}'", getAirlines(), getPassangers());
    }
    public Passanger searchPersonByDNI(Integer dni){
        if(!passangers.isEmpty()){
            for (Passanger p : passangers){
                if(p.getNumberIdentify().equals(dni)){
                    return p;
                }
            }
        }
        return null;
    }
    public void showAirlines(){
        int i = 1;
        for (Airline a : airlines){
            System.out.println(i + ")" + a.getAirlineName());
            i++;
        }
    }

    public Airline searchAirlineByIndex(int index){
        if (!airlines.isEmpty()) {
            if (index < airlines.size()) {
                return airlines.get(index);
            }
        }
        return null;
    }

    public boolean hasStock(Flight flight){
        Airplane airplane = flight.getAirplane();
        return airportTicketOffice.hasStock(flight.getOrigin(), flight.getDestiny(), flight.getTime(), airplane.getCapabilities().getSeatForLetter(), airplane.getCapabilities().getTotalCapacity(), flight.getDoor());
    }
}
