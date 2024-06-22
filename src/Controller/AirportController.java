package Controller;

import Model.Airport;
import View.AirportMenuView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public int handleUserInput() {
        int opcion = airportMenuView.handleUserInput();
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

    private void handleTicketsSellsMenu() {
        airportMenuView.displayTicketsSellsMenu();
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // TODO
                // System.out.println("Funcionalidad de Compra Online aún no implementada.");
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
