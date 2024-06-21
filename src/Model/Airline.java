package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.TreeSet;

public class Airline {
    @JsonProperty("airline_name")
    private String airlineName;
    @JsonProperty("iata_code")
    private String IATAcode;
    @JsonProperty("airplanes")
    private ArrayList<Airplane> airplanes;
    private ArrayList<Employee> employees;
    private ArrayList<Flight> flights;
    private TreeSet<Location> locations;

    public Airline() {

    }

    public Airline(String airlineName, String IATAcode, ArrayList<Airplane> airplanes,
                   ArrayList<Employee> employees, ArrayList<Flight> flights, TreeSet<Location> locations) {
        setAirlineName(airlineName);
        setIATAcode(IATAcode);
        setAirplanes(airplanes);
        setFlights(flights);
        setEmployees(employees);
        setLocations(locations);
    }

    public Airline(String airlineName, String IATAcode) {
        setAirlineName(airlineName);
        setIATAcode(IATAcode);
        setAirplanes(new ArrayList<>());
        setEmployees(new ArrayList<>());
        setFlights(new ArrayList<>());
        setLocations(new TreeSet<>());
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getIATAcode() {
        return IATAcode;
    }

    public void setIATAcode(String IATAcode) {
        this.IATAcode = IATAcode;
    }

    public ArrayList<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(ArrayList<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public TreeSet<Location> getLocations() {
        return locations;
    }

    public void setLocations(TreeSet<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Airline'{'airlineName=''{0}'', IATAcode=''{1}'', airplanes={2}, employees={3}, flights={4}, locations={5}'}'", getAirlineName(), getIATAcode(), getAirplanes(), getEmployees(), getFlights(), getLocations());
    }

}
