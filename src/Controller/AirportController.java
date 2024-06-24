package Controller;

import Exceptions.InvalidIndexException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import Model.*;
import View.AirportMenuView;

public class AirportController {
    Airport airport;
    Airline airLine;
    AirportMenuView airportMenuView;

    public AirportController() {

    }

    public AirportController(Airport airport, AirportMenuView airportMenuView, Airline airline) {
        this.airport = airport;
        this.airportMenuView = airportMenuView;
        this.airLine = airline;

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
        } catch (NotAvailableForSaleException e) {
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
                handleAirlineMenu();
                break;
            case 2:
                handlePassengersMenu();
                break;
            case 3:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleAirlineMenu() {
        airportMenuView.displayAirlineMenu();
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                handleAddAirlineOption();
                break;
            case 2:
                handleDeleteAirlineOption();
                break;
            case 3:
                handleModifyAirlineOption();
                break;
            case 4:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleAddAirlineOption() {
        airportMenuView.displayLineBreak();
        airportMenuView.displayAirlinesSummaryOption(airport.getAirlines());
        String [] inputs = airportMenuView.handleAddAirlineInput();
        String airlineName = inputs[0];
        String airlineIotaCode = inputs[1];
        airport.addAirline(new Airline(airlineName, airlineIotaCode));
    }

    private void handleDeleteAirlineOption() {
        airportMenuView.displayLineBreak();
        airportMenuView.displayAirlinesSummaryOption(airport.getAirlines());
        String iataAirlineCode = airportMenuView.handleDeleteAirlineInput();
        // TODO funciona pero se puede mejorar con equals
        airport.removeAirline(iataAirlineCode);
    }

    private void handleModifyAirlineOption() {
        airportMenuView.displayLineBreak();
        airportMenuView.displayAirlinesSummaryOption(airport.getAirlines());
        String iataAirlineCode = airportMenuView.handleModifyAirlineInput();
        for(Airline airline : airport.getAirlines()) {
            if(airline.getIATAcode().equals(iataAirlineCode)) {
                String airlineName = airportMenuView.handleModifyAirlineNameInput();
                airline.setAirlineName(airlineName);
            }
        }
    }

    private void handlePassengersMenu() {

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
        try {
            switch (opcion) {
                case 1:
                    // TODO
                    airport.sellAirportTicket();
                    break;
                case 2:
                    airport.sellAirportTicketOnline();
                    break;
                case 3:
                    airport.getAirportTicketOffice().exchangeTicket();
                    break;
                case 4:
                    airport.buyTicketByFlight();
                    break;
                case 5:
                    airport.buyTicketByDestiny();
                    break;
                case 6:
                    //Simular vuelo
                    break;
                case 7:
                    airportMenuView.displayBackMessage();
                    break;
                case 8:
                    break;
                default:
                    airportMenuView.displayInvalidOptionMessage();
            }
        }catch (InvalidIndexException e){
            System.out.println(e.getMessage());
        }catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    // endregion
}
