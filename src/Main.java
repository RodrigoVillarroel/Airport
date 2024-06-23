import Controller.AirportController;
import Exceptions.NotAvailableForSaleException;
import Model.Airline;
import Model.Airplane;
import Model.AirplaneCapabilities;
import Model.Airport;
import Utils.Json;
import View.AirportMenuView;

public class Main {
    public static void main(String[] args) throws NotAvailableForSaleException {
        final String airportJsonPath = "airport.json";

        /** Models **/
        Airport airport = Json.loadFromJson(airportJsonPath);
        Airline airline = new Airline();

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
