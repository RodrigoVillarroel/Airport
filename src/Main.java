import Controller.AirportController;
import Model.Airport;
import View.AirportMenuView;
import View.SellsMenuView;

public class Main {
    public static void main(String[] args) {
        /** Models **/
        Airport airport = new Airport();

        /** Views **/
        AirportMenuView airportMenuView = new AirportMenuView();

        /** Controllers **/
        new AirportController(airport, airportMenuView);

        /** Initializations **/
        airportMenuView.setVisible(true);
    }
}
