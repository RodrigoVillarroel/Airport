package Controller;

import Exceptions.InvalidIndexException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import Model.*;
import View.AirportMenuView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

public class AirportController {
    private static final String airportJsonPath = "airport.json";
    Airport airport;
    AirportMenuView airportMenuView;

    public AirportController() {
        loadFromJson();
    }

    public AirportController(Airport airport, AirportMenuView airportMenuView) {
        this.airport = airport;
        this.airportMenuView = airportMenuView;
        loadFromJson();
    }

    // region View
    public void displayMenu() {
        airportMenuView.displayMainMenu();
    }

    public int handleUserInput() throws NotAvailableForSaleException {
        int opcion = airportMenuView.handleUserInput();
        try {
            switch (opcion) {
                case 1:
                    handleAirlinesMenu();
                    break;
                case 2:
                    handleLocationMenu();
                    break;
                case 3:
                    handleTicketsSellsMenu();
                    break;
                case 4:
                    airportMenuView.displayLogOutMessage();
                    break;
                default:
                    airportMenuView.displayInvalidOptionMessage();
            }
        }catch (NotAvailableForSaleException e){
            System.out.println(e.getMessage());
        } catch (InvalidIndexException e) {
            System.out.println(e.getMessage());
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return opcion;
    }


    private void handleAirlinesMenu() {
        airportMenuView.displayAirlinesMenu();
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // administrarAerolineas.agregarAerolinea();
                break;
            case 2:
                // administrarAerolineas.quitarAerolinea();
                break;
            case 3:
                // administrarAerolineas.modificarAerolinea();
                break;
            case 4:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleLocationMenu() {
        airportMenuView.displayLocationsMenu();
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // administrarLocaciones.agregarLocacion();
                break;
            case 2:
                // administrarLocaciones.quitarLocacion();
                break;
            case 3:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleTicketsSellsMenu() throws NotAvailableForSaleException, InvalidIndexException, NotFoundException {
        airportMenuView.displayTicketsSellsMenu();
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // TODO
                int dni = airportMenuView.displayRequestPassangerInfo();
                Passanger p = airport.searchPersonByDNI(dni);
                airport.showAirlines();
                int index = airportMenuView.displayRequesAirlineIndex();
                Airline airline = airport.searchAirlineByIndex(index);
                airport.getAirportTicketOffice().regenerateTicketStock(airline);
                airline.showFlights();
                index = airportMenuView.displayRequestFlight();
                if (index>airport.getAirlines().size())
                {
                    throw new  InvalidIndexException("Indice no valido");
                }
                Flight flight = airline.searchFlightByIndex(index);
                if (airport.hasStock(flight)) {
                    Airplane airplane = flight.getAirplane();
                    airport.getAirportTicketOffice().listSeats(flight.getOrigin(), flight.getDestiny(), flight.getTime(), airplane.getCapabilities().getSeatForLetter(), airplane.getCapabilities().getTotalCapacity(), flight.getDoor());
                    String asiento = airportMenuView.displayRequestSeat();
                    Luggage<Equipaje> luggage = Luggage.addRandomLuggage();
                    airport.getAirportTicketOffice().sellTicket(flight, asiento, p, luggage);
                }else {
                    throw new NotAvailableForSaleException("Asiento no disponible");
                }
                break;
            case 2:
                // administrarAerolineas.mostrarAerolineas();
                break;
            case 3:
                // administrarLocaciones.mostrarLocaciones();
                break;
            case 4:
                // TODO
                // System.out.println("Funcionalidad de Simular Vuelo aún no implementada.");
                break;
            case 5:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }
    // endregion

    private void loadFromJson() {
        try {
            File file = new File(airportJsonPath);
            if (!file.exists()) return;

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Airport airportReadValue = mapper.readValue(file, Airport.class);

            airport.setAirlines(airportReadValue.getAirlines());
            airport.setPassangers(airportReadValue.getPassangers());

            // airportMenuView.displayAirlines(airport.getAirlines());
            //  airportMenuView.displayPassengers(airport.getPassangers());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
