import Controller.AirportController;
import Model.Airplane;
import Model.AirplaneCapabilities;
import Model.Airport;
import View.AirportMenuView;

public class Main {
    public static void main(String[] args) {
        /** Models **/
        Airport airport = new Airport();

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
    }
}
