package Model;

import Exceptions.FormatIncorrectException;
import Exceptions.InvalidIndexException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import Utils.Input;
import Utils.RandomCodeGenerator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.*;

public class Airport {
    @JsonProperty("airlines")
    private LinkedList<Airline> airlines;
    @JsonProperty("passengers")
    private HashSet<Passenger> passengers;
    @JsonProperty("airportTicketOffice")
    AirportTicketOffice airportTicketOffice;
    @JsonProperty("onlineTicketOffice")
    OnlineTicketOffice onlineTicketOffice;

    public Airport(LinkedList<Airline> airlines, HashSet<Passenger> passengers) {
        setAirlines(airlines);
        setPassengers(passengers);
    }

    public Airport() {
        setAirlines(new LinkedList<>());
        setPassengers(new HashSet<>());
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

    public HashSet<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(HashSet<Passenger> passengers) {
        this.passengers = passengers;
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
        return MessageFormat.format("Airport'{'airlines={0}, passengers={1}'}'", getAirlines(), getPassengers());
    }

    public Passenger searchPersonByDNI() throws NotFoundException {
        System.out.println("Ingrese DNI del Pasajero:");
        int dni = Input.requestUserInputInt();

        if (!passengers.isEmpty()) {
            for (Passenger p : passengers) {
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

    public Boolean addPassenger(Passenger passenger) {
        return this.passengers.add(passenger);
    }

    public Boolean removePassengerById(String passportNumber) {
        return this.passengers.removeIf(passenger -> passenger.getNroPassport().equals(passportNumber));
    }

    public Optional<Passenger> findPassengerById(String passportNumber) {
        return this.passengers.stream().filter(passenger -> passenger.getNroPassport().equals(passportNumber)).findFirst();
    }

    public void removeAirline(String iataAirlineCode) {
        this.airlines.removeIf(airline -> airline.getIATAcode().equals(iataAirlineCode));
    }

    public boolean hasStock(Flight flight) {
        Airplane airplane = flight.getAirplane();
        return airportTicketOffice.hasStock(flight.getOrigin(), flight.getDestiny(), flight.getTime(), airplane.getCapabilities().getSeatForLetter(), airplane.getCapabilities().getTotalCapacity(), flight.getDoor());
    }

    public void sellAirportTicket() throws InvalidIndexException, NotFoundException, NotAvailableForSaleException {
        Passenger p = searchPersonByDNI();
        Airline airline = showAndSelectAirline();
        Flight flight = showAndSelectFlights(airline);
        buyAirportTicket(flight, p);
    }




    public Airline showAndSelectAirline() throws InvalidIndexException {
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

    public void buyAirportTicket(Flight flight, Passenger passanger) throws InvalidIndexException, NotAvailableForSaleException {
        if (hasStock(flight)) {
            System.out.println(getAirportTicketOffice().listSeats(flight));
            try {
                String seat = flight.selectSeat();
                if (verifyFormat(seat)) {
                    Luggage<Equipaje> luggage = Luggage.addRandomLuggage();
                    AirportTicket ticket = getAirportTicketOffice().sellTicket(flight, seat, passanger, luggage);
                    ticket.printTicket();
                }else{
                    throw new FormatIncorrectException("Formato de Asiento Incorrecto");
                }
            } catch (NotAvailableForSaleException | FormatIncorrectException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            throw new NotAvailableForSaleException("No hay stock disponible, regenere o cree un vuelo nuevo");
        }
    }

    public boolean verifyFormat(String seat) throws FormatIncorrectException {
        if (seat.length() != 2) {
            throw new FormatIncorrectException("Formato de Asiento Incorrecto");
        }
        char letra = seat.charAt(0);
        char digito = seat.charAt(1);

        boolean letraEsMayuscula = Character.isUpperCase(letra);
        boolean digitoEsNumero = Character.isDigit(digito);

        return letraEsMayuscula && digitoEsNumero;
    }

    public void sellAirportTicketOnline() throws InvalidIndexException, NotFoundException {
        Passenger p = searchPersonByDNI();
        Airline airline = showAndSelectAirline();
        Flight flight = showAndSelectFlights(airline);
        buyOnlineAirportTicket(flight, p);
    }

    public void buyOnlineAirportTicket(Flight flight, Passenger p) {
        if (hasStock(flight)) {
            System.out.println(getAirportTicketOffice().listSeats(flight));
            try {
                String seat = flight.selectSeat();
                System.out.println("Su Codigo de Canje: " + getOnlineTicketOffice().sellTicket(flight, seat, p, getAirportTicketOffice()));
            } catch (NotAvailableForSaleException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Flight showAllFlightsAndSelect() throws InvalidIndexException {
        ArrayList<Flight> flights = new ArrayList<>();
        for (Airline a : airlines) {
            flights.addAll(a.getFlights());
        }
        int i=1;
        for (Flight f : flights){
            System.out.println(i + ")\n" + f.printFlight());
            i++;
        }
            System.out.println("Seleccione el indice del Vuelo que desee: ");
            int choice = Input.requestUserInputInt();
            if(choice-1<0 || choice>flights.size()) {
            throw new InvalidIndexException("Seleccionó un indice invalido");
            }
        return flights.get(choice-1);


    }

    public void buyTicketByFlight() throws NotFoundException {
        try {
            Passenger p = searchPersonByDNI();
            Flight flight = showAllFlightsAndSelect();
            buyAirportTicket(flight, p);
        } catch (NotFoundException | InvalidIndexException | NotAvailableForSaleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buyTicketByDestiny() throws NotFoundException, InvalidIndexException, NotAvailableForSaleException {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Flight> flights = new HashMap<>();
        System.out.println("Escriba el destino al que desea viajar: ");
        String destiny = scanner.nextLine();
        for (Airline a : airlines) {
            if (isDestinyAvailable(destiny)) {
                flights.putAll(a.getThisFlight(destiny));
            }
        }
        if (!flights.isEmpty()) {
            selectFlight(flights);
        } else {
            throw new NotFoundException("No se encontraron vuelos disponibles con el Destino a " + destiny);
        }
    }

    public boolean isDestinyAvailable(String destiny) {
        for (Airline a : airlines) {
            return a.containThisDestiny(destiny);
        }
        return false;
    }

    public void selectFlight(HashMap<String, Flight> flights) throws NotFoundException, InvalidIndexException, NotAvailableForSaleException {
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
        Flight f = flights.get(keys.get(choice - 1));
        Passenger p = searchPersonByDNI();
        buyAirportTicket(f, p);
    }

    public void simuleFlightCost() throws InvalidIndexException {
        Airline a = showAndSelectAirline();
        showAndSelectFlights(a);
        airportTicketOffice.getAllCosts();
    }

    public void addFlight(Airline airline) throws NotFoundException {
        String code = RandomCodeGenerator.generateRandomFlightAirline(airline.getIATAcode());
        System.out.println("Código de Vuelo: " + code);
        if(!thisCodeExist(code)){
            airline.addFlight(code);
        }
        if(airline.searchFlightCode(code)!=null){
            System.out.println("Vuelo creado con éxito");
            airportTicketOffice.updateTicketStock(airline.searchFlightCode(code));
        }else {
            System.out.println("Se cancelo la creación de vuelo");
        }
    }

    public boolean thisCodeExist (String code){
        if (!airlines.isEmpty()){
            for (int i=0;i<airlines.size();i++){
                if (airlines.get(i).thisCodeIsInUse(code)) {
                    return true;
                }
            }
        }
        return false;
    }


    public void deleteFlight(Airline airline) throws InvalidIndexException {
        airline.showFlights();
        System.out.println("Seleccione indice del Vuelo que desee borrar: ");
        int index = Input.requestUserInputInt();
        System.out.println("Se borro el vuelo: " + airline.getFlights().remove(index-1).getCode());
    }

    public void modifyFlight(Airline airline) throws InvalidIndexException, NotFoundException, FormatIncorrectException {
        airline.showFlights();
        System.out.println("Seleccione indice del Vuelo que desee Modificar: ");
       int index = Input.requestUserInputInt();
        Flight flight = airline.getFlights().get(index-1);
        airline.menuFlightModification(flight);
    }

    public void searchFlight(Airline airline) throws InvalidIndexException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese Código del Vuelo que busca:");
        String code = scanner.nextLine();
        Flight flight = airline.searchFlightCode(code);
        System.out.println(flight);
    }
    public void showAllFlights(Airline airline){
        airline.showFlights();
    }

    public void regenerateAllStock(){
        for (Airline a : airlines){
            for (Flight f : a.getFlights()){
                airportTicketOffice.regenerateTicketStock(f);
            }
        }
        System.out.println("Se regeneraron todos los Stocks de vuelos");
    }

    public void renerateStockByFlight(){
        showAirlines();
        System.out.println("Seleccione la Aerolínea del Vuelo que desee actualizar el Stock");
        Airline a = getAirlines().get(Input.requestUserInputInt()-1);
        a.showFlights();
        System.out.println("Seleccione el Vuelo a Regenerar Stock");
        airportTicketOffice.regenerateTicketStock(a.getFlights().get(Input.requestUserInputInt()-1));
        System.out.println("Se regenero el Stock");
    }

}
