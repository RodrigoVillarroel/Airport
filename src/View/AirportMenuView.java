package View;

import Model.Airline;
import Model.Passanger;

import java.util.HashSet;
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

    private void concat(String str) {
        this.output = this.output.concat(str);
    }

    public void displayAirlines(LinkedList<Airline> airlines) {
        for (Airline airline : airlines) {
            concat("\n" + airline.toString());
        }
    }

    public void displayPassengers(HashSet<Passanger> passangers) {
        concat("\n--Listado de Pasajeros--");
        for (Passanger passanger : passangers) {
            concat("\n" + passanger.toString());
        }

    }

}
