package Controller;

import Exceptions.InvalidIndexException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import Model.*;
import View.AirportMenuView;
import java.util.Scanner;

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

    // region Main Menu
    public void displayMenu() {
        airportMenuView.displayMainMenu();
    }

    public int handleUserInput() throws NotAvailableForSaleException {
        int opcion = airportMenuView.handleUserInput();
        try {
            switch (opcion) {
                case 1:
                    handleSelectionAirlinesMenu();
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
    // endregion

    // region Select Airline Menu
    private void handleSelectionAirlinesMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("seleccione una aerolinea: ");
        int opcion = 0;
        airport.showAirlines();
        opcion = scanner.nextInt();
        Airline airline = airport.searchAirlineByIndex(opcion-1);

        if(airline!=null){
            System.out.println(airline);
            handleAirlinesMenu(airline);

        }else {
            System.out.println("no se encuentra la aerolinea..");
            handleSelectionAirlinesMenu();
        }
    }
    // endregion

    // region Airlines Menu
    private void handleAirlinesMenu(Airline airline) {
        airportMenuView.displayAirlinesMenu(airline.getAirlineName(), airline.getIATAcode());
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                handleFlightsMenu();
                break;
            case 2:
                handleAirplanesMenu(airline);
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
    // endregion

    // region Airline Menu: Flights Menu
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
    // endregion

    // region Airline Menu: Airplanes Menu
    private void handleAirplanesMenu(Airline airline) {
        airportMenuView.displayAirplanesMenu();
        airline.listAirplanesWithOptions();
        Scanner scanner = new Scanner(System.in);
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // agregar avion
                System.out.println(airLine.addAirplaneByKeyboard());
                break;
            case 2:
                // borrar avion
                airline.listAirplanesWithOptions();
                System.out.println("Escriba el cod del avion a eliminar: ");
                System.out.println(airline.removeAirplaneByRegistrationNumber(scanner.nextLine()));
                break;
            case 3:
                // modificar status avion
                airline.listAirplanesWithOptions();
                System.out.println("Escriba el cod del avion a modificar: ");
                airline.modifyStatusAirplane(scanner.nextLine());
                airline.listAirplanesWithOptions();
                break;
            case 4:
                // buscar avion
                System.out.println("Escriba el cod del avion a buscar: ");
                System.out.println(airline.searchAirplane(scanner.nextLine()));
                break;
            case 5:
                // listar aviones
                airline.listAirplanes();
                break;
            case 6:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }
    // endregion

    // region Airline Menu: Employees Menu
    private void handleEmployeeMenu() {
        airportMenuView.displayEmployeeMenu();
        Scanner scanner = new Scanner(System.in);
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // agregar empleado
                System.out.println(airLine.addEmployeeByKeyboard());
                break;
            case 2:
                // borrar empleado
                System.out.println("Ingrese dni del empleado a eliminar:");
                System.out.println(airLine.removeEmployee(airLine.searchEmployee(scanner.nextInt())));
                break;
            case 3:
                // modificar empleado
                break;
            case 4:
                // buscar empleado
                System.out.println("Ingrese dni:");
                System.out.println(airLine.searchEmployee(scanner.nextInt()));
            case 5:
                airLine.listEmployee();
            case 6:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }
    // endregion

    // region Airline Menu: Location Menu
    private void handleLocationMenu() {
        airportMenuView.displayLocationsMenu();
        Scanner scanner = new Scanner(System.in);
        int opcion = airportMenuView.handleUserInput();
        switch (opcion) {
            case 1:
                // agregar locacion
                System.out.println(airLine.addLocationByKeyboard());
                break;
            case 2:
                System.out.println("Ingrese nombre del aeropuerto:");
                System.out.println(airLine.removeLocation(airLine.searchLocationForAirportName(scanner.nextLine())));
                break;
            case 3:
                // modificar locacion
                break;
            case 4:
                System.out.println("Ingrese nombre del aeropuerto:");
                System.out.println(airLine.searchLocationForAirportName(scanner.nextLine()));
                break;
            case 5:
                airLine.listLocation();
                break;
            case 6:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }
    // endregion

    // region Airport Menu
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
    // endregion

    // region Airport Menu: Airline Menu
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
    // endregion

    // region Airport Menu: Passenger Menu
    private void handlePassengersMenu() {

    }
    // endregion

    // region Tickets Sells Menu
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
