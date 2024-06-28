import Controller.AirportController;
import Exceptions.InvalidIndexException;
import Exceptions.NotFoundException;
import Model.*;
import Utils.Json;
import View.AirportMenuView;

public class Main {
    public static void main(String[] args) throws InvalidIndexException, NotFoundException {
        new Json();
        final String airportJsonPath = "airport.json";

        /** Models **/
        Airport airport = Json.loadFromJson(airportJsonPath);
       // Airline airline = new Airline();

        // Obtener una lista plana de todos los vuelos
        /** Views **/
        AirportMenuView airportMenuView = new AirportMenuView();

        /** Controllers **/
        AirportController airportController = new AirportController(airport, airportMenuView);

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
