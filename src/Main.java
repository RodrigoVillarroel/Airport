import Controller.AirportController;
import Exceptions.InvalidIndexException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import Model.*;
import Utils.Json;
import View.AirportMenuView;

public class Main {
    public static void main(String[] args) throws NotAvailableForSaleException, InvalidIndexException, NotFoundException {
        final String airportJsonPath = "airport.json";

        /** Models **/
        Airport airport = Json.loadFromJson(airportJsonPath);
        Airline airline = new Airline();

        // Obtener una lista plana de todos los vuelos
        for (Airline a: airport.getAirlines()){
            airport.getAirportTicketOffice().regenerateTicketStock(a);
        }
        /** Views **/
        AirportMenuView airportMenuView = new AirportMenuView();

        /** Controllers **/
        AirportController airportController = new AirportController(airport, airportMenuView, airline);

        /** Display main menu **/
        int opcion;
        do {
            airportController.displayMenu();
            opcion = airportController.handleUserInput();
        } while (opcion != 4);

        /** Persist data **/
        Json.saveToJson(airportJsonPath, airport);
    }
}
