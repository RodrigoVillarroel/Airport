package View;

import Model.Airline;
import java.util.LinkedList;

public class AirportMenuView {
    private String output;

    public AirportMenuView() {
        this.output = "---------Menu Airport-----------";
    }

    public void setVisible(Boolean visible) {
        if (!visible) {
            this.output = "";
        }
        System.out.println(output);
    }

    public void displayAirlines(LinkedList<Airline> airlines) {
        for (Airline airline : airlines) {
            this.output = this.output.concat("\n" + airline.toString());
        }
    }

}
