package Model;

import Exceptions.InvalidIndexException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

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
    public Passanger searchPersonByDNI() throws NotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese DNI del Pasajero:");
        int dni = scanner.nextInt();

        if(!passangers.isEmpty()){
            for (Passanger p : passangers){
                if(p.getNumberIdentify().equals(dni)){
                    return p;
                }
            }
        }else {
            throw new NotFoundException("Lista de Pasajeros vacia");
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

    public void sellAirportTicket() throws InvalidIndexException, NotFoundException, NotAvailableForSaleException {
        showAndSelectAirline();
    }


    public void showAndSelectAirline() throws NotFoundException, InvalidIndexException, NotAvailableForSaleException {
        Passanger p = searchPersonByDNI();
        Scanner scanner = new Scanner(System.in);
        showAirlines();
        System.out.println("Seleccione el indice de la Aerolinea que desee:");
        int index = scanner.nextInt();
        if (index>getAirlines().size())
        {
            throw new  InvalidIndexException("Indice no valido");
        }
        Airline airline = searchAirlineByIndex(index-1);
        Flight flight = showAndSelectFlights(airline);
        buyAirportTicket(flight, p);
    }

    public Flight showAndSelectFlights(Airline airline) throws InvalidIndexException {
        Scanner scanner = new Scanner(System.in);
        airline.showFlights();
        System.out.println("Seleccione el indice del Vuelo que desee");
        int index = scanner.nextInt();

        if (index>airline.getFlights().size())
        {
            throw new InvalidIndexException("Indice no valido");
        }else {
            return airline.searchFlightByIndex(index);
        }
    }

    public void buyAirportTicket(Flight flight, Passanger passanger) throws NotAvailableForSaleException {
        if (hasStock(flight)){
            System.out.println(getAirportTicketOffice().listSeats(flight.getOrigin(), flight.getDestiny(), flight.getTime(), flight.getAirplane().getCapabilities().getSeatForLetter(), flight.getAirplane().getCapabilities().getTotalCapacity(), flight.getDoor()));
            String seat = flight.selectSeat();
            Luggage<Equipaje> luggage = Luggage.addRandomLuggage();
            System.out.println("Su ticket de vuelo: ");
            System.out.println(getAirportTicketOffice().sellTicket(flight, seat, passanger, luggage));
        }
    }

}
