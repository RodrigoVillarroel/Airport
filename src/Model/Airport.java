package Model;

import Exceptions.InvalidIndexException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import Utils.Input;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.*;

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
        System.out.println("Ingrese DNI del Pasajero:");
        int dni = Input.requestUserInputInt();

        if (!passangers.isEmpty()) {
            for (Passanger p : passangers) {
                if (p.getNumberIdentify().equals(dni)) {
                    return p;
                }
            }
            throw new NotFoundException("No se encontro al pasajero");
        } else {
            throw new NotFoundException("Lista de Pasajeros vacía");
        }
    }

    public void showAirlines() {
        int i = 1;
        for (Airline a : airlines) {
            System.out.println(i + ")" + a.getAirlineName());
            i++;
        }
    }

    public Airline searchAirlineByIndex(int index) {
        if (!airlines.isEmpty()) {
            if (index < airlines.size()) {
                return airlines.get(index);
            }
        }
        return null;
    }

    public void addAirline(Airline airline) {
        this.airlines.add(airline);
    }

    public void removeAirline(String iataAirlineCode) {
        this.airlines.removeIf(airline -> airline.getIATAcode().equals(iataAirlineCode));
    }

    public boolean hasStock(Flight flight) {
        Airplane airplane = flight.getAirplane();
        return airportTicketOffice.hasStock(flight.getOrigin(), flight.getDestiny(), flight.getTime(), airplane.getCapabilities().getSeatForLetter(), airplane.getCapabilities().getTotalCapacity(), flight.getDoor());
    }

    public void sellAirportTicket() throws InvalidIndexException, NotFoundException {
        Passanger p = searchPersonByDNI();
        Airline airline = showAndSelectAirline();
        Flight flight = showAndSelectFlights(airline);
        buyAirportTicket(flight, p);
    }


    public Airline showAndSelectAirline() throws NotFoundException, InvalidIndexException {
        showAirlines();
        System.out.println("Seleccione el indice de la Aerolinea que desee:");
        int index = Input.requestUserInputInt();
        if (index > getAirlines().size()) {
            throw new InvalidIndexException("Indice no valido");
        }
        return searchAirlineByIndex(index - 1);
    }

    public Flight showAndSelectFlights(Airline airline) throws InvalidIndexException {
        airline.showFlights();
        System.out.println("Seleccione el indice del Vuelo que desee");
        int index = Input.requestUserInputInt();

        if (index > airline.getFlights().size() || index < 0) {
            throw new InvalidIndexException("Indice no valido");
        } else {
            return airline.searchFlightByIndex(index - 1);
        }
    }

    public void buyAirportTicket(Flight flight, Passanger passanger) {
        if (hasStock(flight)) {
            System.out.println(getAirportTicketOffice().listSeats(flight.getOrigin(), flight.getDestiny(), flight.getTime(), flight.getAirplane().getCapabilities().getSeatForLetter(), flight.getAirplane().getCapabilities().getTotalCapacity(), flight.getDoor()));
            try {
                String seat = flight.selectSeat();
                Luggage<Equipaje> luggage = Luggage.addRandomLuggage();
                System.out.println("Su ticket de vuelo: ");
                System.out.println(getAirportTicketOffice().sellTicket(flight, seat, passanger, luggage));
            } catch (NotAvailableForSaleException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void sellAirportTicketOnline() throws InvalidIndexException, NotFoundException {
        Passanger p = searchPersonByDNI();
        Airline airline = showAndSelectAirline();
        Flight flight = showAndSelectFlights(airline);
        buyOnlineAirportTicket(flight, p);
    }

    public void buyOnlineAirportTicket(Flight flight, Passanger p) {
        if (hasStock(flight)) {
            System.out.println(getAirportTicketOffice().listSeats(flight.getOrigin(), flight.getDestiny(), flight.getTime(), flight.getAirplane().getCapabilities().getSeatForLetter(), flight.getAirplane().getCapabilities().getTotalCapacity(), flight.getDoor()));
            try {
                String seat = flight.selectSeat();
                System.out.println("Su Codigo de Canje: " + getOnlineTicketOffice().sellTicket(flight, seat, p, getAirportTicketOffice()));
            } catch (NotAvailableForSaleException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Flight showAllFlightsAndSelect() {
        ArrayList<Flight> flights = new ArrayList<>();
        for (Airline a : airlines) {
            flights.addAll(a.getFlights());
        }
        System.out.println(flights);
        System.out.println("Seleccione el indice del Vuelo que desee: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return flights.get(choice);


    }

    public void buyTicketByFlight() throws NotFoundException {
        try {
            Passanger p = searchPersonByDNI();
            Flight flight = showAllFlightsAndSelect();
            buyAirportTicket(flight, p);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buyTicketByDestiny() throws NotFoundException {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Flight> flights = new HashMap<>();
        System.out.println("Escriba el destino al que desea viajar: ");
        String destiny = scanner.nextLine();
        for (Airline a : airlines) {
            if (isDestinyAvailable(destiny)) {
                flights.putAll(a.getThisFlight(destiny));
            }
        }
        if (!flights.isEmpty()){
            selectFlight(flights);
        }else {
            throw new NotFoundException("No se encontraron vuelos disponibles con el Destino a " + destiny);
        }
    }

    public boolean isDestinyAvailable(String destiny) {
        for (Airline a : airlines) {
            return a.containThisDestiny(destiny);
        }
        return false;
    }

    public void selectFlight(HashMap<String, Flight> flights) throws NotFoundException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> keys = new ArrayList<>();
        int i = 1;
        System.out.println("Seleccione el Vuelo con el horario mas conveniente: ");

        for (Map.Entry<String, Flight> entry : flights.entrySet()) {

            System.out.println(i + ")");
            System.out.println("Hora de Salida: " + entry.getValue().getTime());

            System.out.println("De la Aerolínea: " + entry.getKey());
            keys.add(entry.getKey());
            i++;
        }
        int choice = scanner.nextInt();
        Flight f = flights.get(keys.get(choice-1));
        Passanger p = searchPersonByDNI();
        buyAirportTicket(f, p);
    }
}
