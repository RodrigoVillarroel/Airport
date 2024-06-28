package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Location{
    @JsonProperty("airport_name")
    private String nameAirport;
    @JsonProperty("location")
    private String location;
    @JsonProperty("doors")
    private ArrayList<BoardingDoor> doors;

    public Location() {

    }

    public Location(String nameAirport, String location) {
        setLocation(location);
        setNameAirport(nameAirport);
        setDoors(new ArrayList<>());
    }

    public Location(String nameAirport, String location, ArrayList<BoardingDoor> doors) {
        setNameAirport(nameAirport);
        setLocation(location);
        setDoors(doors);
    }

    // region Getters & Setters
    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<BoardingDoor> getDoors() {
        return doors;
    }

    public void setDoors(ArrayList<BoardingDoor> doors) {
        this.doors = doors;
    }
    // endregion

    public void printLocation(){
        System.out.println("Nombre de Aeropuerto: " + getNameAirport() + " - " + "Ciudad de Ubicacion: " + getLocation());
    }
}
