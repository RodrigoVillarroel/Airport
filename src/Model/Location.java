package Model;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Location {
    private String nameAirport;
    private String location;
    private ArrayList<BoardingDoor> doors;

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

    @Override
    public String toString() {
        return MessageFormat.format("Location'{'nameAirport=''{0}'', location=''{1}'', doors={2}'}'", getNameAirport(), getLocation(), getDoors());
    }
}
