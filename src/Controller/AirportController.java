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
import java.util.Scanner;

public class AirportController {
    private static final String airportJsonPath = "airport.json";
    Airport airport;
    Airline airLine;
    AirportMenuView airportMenuView;

    public AirportController() {
        loadFromJson();
    }

    public AirportController(Airport airport, AirportMenuView airportMenuView, Airline airline) {
        this.airport = airport;
        this.airportMenuView = airportMenuView;
        this.airLine = airline;
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
                    handleAirportMenu();
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
                handleFlightsMenu();
                break;
            case 2:
                handleAirplanesMenu();
                break;
            case 3:
                handleLocationMenu();
                break;
            case 4:
                handleEmployeeMenu();
                break;
            case 5:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }
    private void handleAirportMenu() {
        airportMenuView.displayAirportMenu();
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
    private void handleFlightsMenu() {
        airportMenuView.displayFlightsMenu();
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                    // crear vuelo
                break;
            case 2:
                    // borrar vuelo
                break;
            case 3:
                    // modificar vuelo
                break;
            case 4:
                    // buscar vuelo
                break;
            case 5:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleAirplanesMenu() {
        airportMenuView.displayAirplanesMenu();
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // agregar avion
                System.out.println(airLine.addAirplaneByKeyboard());
                break;
            case 2:
                // borrar avion
                break;
            case 3:
                // modificar avion
                break;
            case 4:
                // buscar avion
                break;
            case 5:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleEmployeeMenu() {
        airportMenuView.displayEmployeeMenu();
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // agregar empleado
                break;
            case 2:
                // borrar empleado
                break;
            case 3:
                // modificar empleado
                break;
            case 4:
                // buscar empleado
            case 5:
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
                // agregar locacion
                break;
            case 2:
                // borrar locacion
                break;
            case 3:
                // modificar locacion
                break;
            case 4:
                // buscar locacion
                break;
            case 5:
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
                airport.sellAirportTicket();
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

    public void saveToJson() {
        try {
            File file = new File(airportJsonPath);
            if (!file.exists()) return;

            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(file, airport);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
