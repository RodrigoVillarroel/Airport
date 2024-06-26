package Controller;

import Exceptions.*;
import Model.*;
import Utils.Input;
import View.AirportMenuView;

import java.util.Optional;
import java.util.Scanner;

public class AirportController {
    Airport airport;
    AirportMenuView airportMenuView;
    Airline airLine;

    public AirportController() {
    }

    public AirportController(Airport airport, AirportMenuView airportMenuView) {
        this.airport = airport;
        this.airportMenuView = airportMenuView;
    }

    // region Main Menu
    public void displayMenu() {
        airportMenuView.displayMainMenu();
    }

    public int handleUserInput() {
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
        } catch (NotAvailableForSaleException | InvalidIndexException | NotFoundException | AlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        return opcion;
    }
    // endregion

    // region Select Airline Menu
    private void handleSelectionAirlinesMenu() throws InvalidIndexException, NotFoundException {
        System.out.println("Seleccione una Aerolínea: \n");
        airport.showAirlines();

        int opcion = Input.requestUserInputInt();
        Airline airline = airport.searchAirlineByIndex(opcion - 1);
        if (airline != null) {
            this.airLine = airline;
            handleAirlinesMenu(airline);
        } else {
            throw new NotFoundException("No se encuentra la Aerolínea");
        }
    }
    // endregion

    // region Airlines Menu
    private void handleAirlinesMenu(Airline airline) {
        airportMenuView.displayAirlinesMenu(airline.getAirlineName(), airline.getIATAcode());
        int opcion = airportMenuView.handleUserInput();
        try {
            switch (opcion) {
                case 1:
                    handleFlightsMenu(airline);
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
        }catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    // endregion

    // region Airline Menu: Flights Menu
    private void handleFlightsMenu(Airline airline) {
        airportMenuView.displayFlightsMenu();
        int opcion = airportMenuView.handleUserInput();
        try {
            switch (opcion) {
                case 1:
                    airport.addFlight(airline);
                    break;
                case 2:
                    airport.deleteFlight(airline);
                    break;
                case 3:
                    airport.modifyFlight(airline);
                    break;
                case 4:
                    airport.searchFlight(airline);
                    break;
                case 5:
                    airport.showAllFlights(airline);
                    break;
                case 6:
                    airportMenuView.displayBackMessage();
                    break;
                default:
                    airportMenuView.displayInvalidOptionMessage();
            }
        }catch (InvalidIndexException | NotFoundException | FormatIncorrectException e){
            System.out.println(e.getMessage());
        }
    }
    // endregion

    // region Airline Menu: Airplanes Menu
    private void handleAirplanesMenu() {
        airportMenuView.displayAirplanesMenu();
        int option = airportMenuView.handleUserInput();
        switch (option) {
            case 1:
                handleAddAirplaneOption();
                break;
            case 2:
                handleDeleteAirplaneOption();
                break;
            case 3:
                handleModifyAirplaneOption();
                break;
            case 4:
                handleFindAirplaneOption();
                break;
            case 5:
                handleListAirplaneOption();
                break;
            case 6:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleAddAirplaneOption() {
        if(!airLine.addAirplaneByKeyboard()){
            System.out.println("Se cancelo el agregado de Avión");
        }
    }

    private void handleDeleteAirplaneOption() {
        Scanner scanner = new Scanner(System.in);
        airLine.listAirplanesWithOptions();
        System.out.println("Escriba el cod del avión a eliminar: ");
        String airplaneCode = scanner.nextLine();
        airLine.removeAirplaneByRegistrationNumber(airplaneCode);
    }

    private void handleModifyAirplaneOption() {
        Scanner scanner = new Scanner(System.in);
        airLine.listAirplanesWithOptions();
        System.out.println("Escriba el cod del avion a modificar: ");
        airLine.modifyStatusAirplane(scanner.nextLine());
        airLine.listAirplanesWithOptions();
    }

    private void handleFindAirplaneOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el cod del avion a buscar: ");
        System.out.println(airLine.searchAirplane(scanner.nextLine()));
    }

    private void handleListAirplaneOption() {
        airLine.listAirplanes();
    }
    // endregion

    // region Airline Menu: Employees Menu
    private void handleEmployeeMenu() throws NotFoundException {
        airportMenuView.displayEmployeeMenu();
        int option = airportMenuView.handleUserInput();
        switch (option) {
            case 1:
                handleAddEmployeeOption();
                break;
            case 2:
                handleDeleteEmployeeOption();
                break;
            case 3:
                handleModifyStatusEmployeeOption();
                break;
            case 4:
                handleModifyWorkstationEmployeeOption();
                break;
            case 5:
                handleSearchEmployeeOption();
                break;
            case 6:
                handleListEmployeesOption();
                break;
            case 7:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleAddEmployeeOption() {
        Employee employee = airportMenuView.displayAddEmployeeOption();
        airLine.addEmployee(employee);
    }

    private void handleDeleteEmployeeOption() {
        try {
            Integer nationalId = airportMenuView.displayFindEmployeeOption();
            Employee employeeFound = airLine.searchEmployee(nationalId);
            if (employeeFound == null) {
                System.out.println("Empleado no encontrado");
            } else {
                airLine.removeEmployee(employeeFound);
                System.out.println(employeeFound.getName() + " borrado con exito");
            }
        }catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private void handleModifyStatusEmployeeOption() {
        Integer nationalId = airportMenuView.displayFindEmployeeOption();
        if(airLine.changeStatus(nationalId)){
            System.out.println("Empleado cambiado correctamente..");
        }else{
            System.out.println("El Empleado no se pudo cambiar correctamente..");
        }
    }

    private void handleModifyWorkstationEmployeeOption() throws NotFoundException {
        Integer nationalId = airportMenuView.displayFindEmployeeOption();
        airLine.changeWorkstation(nationalId);
    }

    private void handleSearchEmployeeOption() throws NotFoundException {
        Integer nationalId = airportMenuView.displayFindEmployeeOption();
        Employee employeeFound = airLine.searchEmployee(nationalId);
        if (employeeFound == null) {
            System.out.println("Empleado no encontrado");
            return;
        }
        System.out.println(employeeFound);
    }

    private void handleListEmployeesOption() {
        for (Employee employee : airLine.getEmployees()) {
            System.out.println(employee);
        }
    }
    // endregion

    // region Airline Menu: Location Menu
    private void handleLocationMenu() {
        airportMenuView.displayLocationsMenu();
        int option = airportMenuView.handleUserInput();
        switch (option) {
            case 1:
                handleAddLocationOption();
                break;
            case 2:
                handleDeleteLocationOption();
                break;
            case 3:
                handleModifyLocationOption();
                break;
            case 4:
                handleFindLocationOption();
                break;
            case 5:
                handleListLocationOption();
                break;
            case 6:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
    }

    private void handleAddLocationOption() {
        if(airLine.addLocationByKeyboard()){
            System.out.println("Locación agregada con éxito");
        }else {
            System.out.println("Se cancelo la carga de Locación");
        }
    }

    private void handleDeleteLocationOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese nombre del aeropuerto:");
        System.out.println(airLine.removeLocation(airLine.searchLocationForAirportName(scanner.nextLine())));
    }

    private void handleModifyLocationOption() {
        // TODO
    }

    private void handleFindLocationOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese nombre del aeropuerto:");
        String location = scanner.nextLine();
        if(airLine.searchLocationForAirportName(location) == null){
            System.out.println("No se encontró la locación");
        }else {
            System.out.println(airLine.searchLocationForAirportName(location));
        }
    }

    private void handleListLocationOption() {
        airLine.listLocation();
    }
    // endregion

    // region Airport Menu
    private void handleAirportMenu() throws AlreadyExistsException {
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
        String[] inputs = airportMenuView.handleAddAirlineInput();
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
        for (Airline airline : airport.getAirlines()) {
            if (airline.getIATAcode().equals(iataAirlineCode)) {
                String airlineName = airportMenuView.handleModifyAirlineNameInput();
                airline.setAirlineName(airlineName);
            }
        }
    }
    // endregion

    // region Airport Menu: Passenger Menu
    private void handlePassengersMenu(){
        airportMenuView.displayPassengersMenu();
        int opcion = airportMenuView.handleUserInput();
        try {
        switch (opcion) {
            case 1:
                handleListPassengerOption();
                break;
            case 2:
                handleAddPassengerOption();
                break;
            case 3:
                handleModifyPassengerOption();
                break;
            case 4:
                handleDeletePassengerOption();
                break;
            case 5:
                airportMenuView.displayBackMessage();
                break;
            default:
                airportMenuView.displayInvalidOptionMessage();
        }
        }catch (AlreadyExistsException e){
            System.out.println(e.getMessage());
        }

    }

    private void handleListPassengerOption() {
        for (Passenger passenger : airport.getPassengers()) {
            System.out.println(passenger.toString().concat(" - " + "Nro. de Pasaporte:" + passenger.getNroPassport()));
        }
    }

    private void handleAddPassengerOption() throws AlreadyExistsException {
        Passenger passenger = airportMenuView.displayAddPassengerOption();
        Boolean added = airport.addPassenger(passenger);
        if (added) {
            System.out.println("Pasajero agregado al sistema");
        } else {
            throw new AlreadyExistsException("El Pasajero ya existe");
        }
    }

    private void handleModifyPassengerOption() {
        String passportNumber = airportMenuView.displayFindPassengerOption();
        Optional<Passenger> passengerFound = airport.findPassengerById(passportNumber);
        if (passengerFound.isPresent()) {
            try {
                System.out.println(passengerFound);
                Passenger passengerModified = airportMenuView.displayModifyPassengerOption(passengerFound.get());
                airport.addPassenger(passengerModified);
            } catch (InvalidIndexException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("El pasajero no existe");
        }
    }

    private void handleDeletePassengerOption() {
        String passportNumber = airportMenuView.displayFindPassengerOption();
        Boolean deleted = airport.removePassengerById(passportNumber);
        if (deleted) {
            System.out.println("Pasajero borrado");
        } else {
            System.out.println("Lo sentimos, el pasajero no pudo ser borrado");
        }
    }
    // endregion

    // region Tickets Sells Menu
    private void handleTicketsSellsMenu() throws NotAvailableForSaleException, InvalidIndexException, NotFoundException {

        int opcion = 0;
        if(!airport.getAirportTicketOffice().isPricesSet()){
            airport.getAirportTicketOffice().setPricesForSale();
        }
        do{
            airportMenuView.displayTicketsSellsMenu();
            opcion = airportMenuView.handleUserInput();
            try {
                switch (opcion) {
                    case 1:
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
                        airport.simuleFlightCost();
                        break;
                    case 7:
                        airport.getAirportTicketOffice().setPricesForSale();
                        break;
                    case 8:
                        airport.regenerateAllStock();
                        break;
                    case 9:
                        airport.renerateStockByFlight();
                        break;
                    case 10:
                        airportMenuView.displayBackMessage();
                        break;
                    default:
                        airportMenuView.displayInvalidOptionMessage();
                }
            } catch (InvalidIndexException | NotFoundException e) {
                System.out.println(e.getMessage());
            }
        }while (opcion<8);
    }
    // endregion
}
