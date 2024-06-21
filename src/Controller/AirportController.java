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

    private void loadFromJson() {
        try {
            File file = new File(airportJsonPath);
            if (!file.exists()) return;

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Airport airportReadValue = mapper.readValue(file, Airport.class);

            airport.setAirlines(airportReadValue.getAirlines());
            airport.setPassangers(airportReadValue.getPassangers());

            airportMenuView.displayAirlines(airport.getAirlines());
            airportMenuView.displayPassengers(airport.getPassangers());

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
