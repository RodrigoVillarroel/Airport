package Model;

import Exceptions.FormatIncorrectException;
import Exceptions.InvalidIndexException;
import Exceptions.NotFoundException;
import Utils.Input;
import Utils.RandomCodeGenerator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Airline {
    @JsonProperty("airline_name")
    private String airlineName;
    @JsonProperty("iata_code")
    private String IATAcode;
    @JsonProperty("airplanes")
    private HashSet<Airplane> airplanes;
    @JsonProperty("employees")
    private HashSet<Employee> employees;
    @JsonProperty("flights")
    private ArrayList<Flight> flights;
    @JsonProperty("locations")
    private HashSet<Location> locations;

    public Airline() {

    }

    public Airline(String airlineName, String IATAcode, HashSet<Airplane> airplanes,
                   HashSet<Employee> employees, ArrayList<Flight> flights, HashSet<Location> locations) {
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
        setAirplanes(new HashSet<>());
        setEmployees(new HashSet<>());
        setFlights(new ArrayList<>());
        setLocations(new HashSet<>());
    }

    // region Getters & Setters
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

    public HashSet<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(HashSet<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public HashSet<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(HashSet<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public HashSet<Location> getLocations() {
        return locations;
    }

    public void setLocations(HashSet<Location> locations) {
        this.locations = locations;
    }
    // endregion

    @Override
    public String toString() {
        return MessageFormat.format("Airline'{'airlineName=''{0}'', IATAcode=''{1}'', airplanes={2}, employees={3}, flights={4}, locations={5}'}'", getAirlineName(), getIATAcode(), getAirplanes(), getEmployees(), getFlights(), getLocations());
    }

    public void showFlights() {
        int i = 1;
        for (Flight f : flights) {
            System.out.println("-------------------------------------------");
            System.out.println(i + ")");
            System.out.println("Desde: " + f.getOrigin() + " - ");
            System.out.println("Hasta: " + f.getDestiny());
            System.out.println("Horario: " + f.getTime());
            i++;
        }
    }

    public Flight searchFlightByIndex(int index) {
        if (!flights.isEmpty()) {
            if (flights.size() > index) {
                return flights.get(index);
            }
        }
        return null;
    }

    /**
     * METHODS CONTROL AIRPLANES
     */
    public boolean addAirplaneByKeyboard() {
        if (getAirplanes() == null) {
            setAirplanes(new HashSet<>());
        }
        int option;
        int selection;
        Scanner scanner = new Scanner(System.in);
        Airplane airplane = new Airplane();
        System.out.println("\nCarga de avión: ");
        airplane.setRegistrationNumber(RandomCodeGenerator.generateRandomRegistrationNumber(getIATAcode()));
        System.out.println("\nCódigo de identificación del Avión: " + airplane.getRegistrationNumber());
        System.out.println("\nseleccione las capacidades:\n");
        System.out.println("1) CAPACIDAD BAJA: ASIENTOS DE PRIMERA CLASE: 18 / ASIENTOS CLASE EJECUTIVO: 24 / ASIENTOS CLASE ECONÓMICA PREMIUM: 48 / ASIENTOS CLASE ECONÓMICA: 60,8");
        System.out.println("2) CAPACIDAD MEDIA: ASIENTOS DE PRIMERA CLASE: 21 / ASIENTOS CLASE EJECUTIVO: 35 / ASIENTOS CLASE ECONÓMICA PREMIUM: 63 / ASIENTOS CLASE ECONÓMICA:77");
        System.out.println("3) CAPACIDAD ALTA: ASIENTOS DE PRIMERA CLASE: 24 / ASIENTOS CLASE EJECUTIVO: 32 / ASIENTOS CLASE ECONÓMICA PREMIUM: 64 / ASIENTOS CLASE ECONÓMICA:80");

        selection = Input.requestUserInputInt();

        switch (selection) {
            case 1:
                airplane.setCapabilities(AirplaneCapabilities.SMALL);
                break;
            case 2:
                airplane.setCapabilities(AirplaneCapabilities.MEDIUM);
                break;
            case 3:
                airplane.setCapabilities(AirplaneCapabilities.BIG);
                break;
            default:
        }
        airplane.setStatus("available");
        System.out.println(airplane);
        System.out.println("\nDesea cargarlo? \n1) SI \n2) NO");
        option = scanner.nextInt();
        if (option == 1) {
            return this.addAirplane(airplane);
        } else {
            return false;
        }
    }

    public boolean addAirplane(Airplane airplane) {
        if (getAirplanes() == null) {
            setAirplanes(new HashSet<>());
        }
        return getAirplanes().add(airplane);
    }

    public void removeAirplane(Airplane airplane) {
        if (getAirplanes() != null) {
            getAirplanes().remove(airplane);
        }
    }

    public void removeAirplaneByRegistrationNumber(String numberRegistration) {
        if (getAirplanes() != null) {
            this.removeAirplane(this.searchAirplane(numberRegistration));
        }
    }

    public Airplane searchAirplane(String numberRegistration) {
        if (getAirplanes() != null) {
            for (Airplane airplane : getAirplanes()) {
                if (airplane.getRegistrationNumber().equals(numberRegistration)) {
                    return airplane;
                }
            }
        }
        return null;
    }

    public void modifyStatusAirplane(String numberRegistration) {
        Airplane airplane = searchAirplane(numberRegistration);
        if (airplane != null) {
            System.out.println(airplane);
            if (airplane.getStatus().equals("available")) {
                airplane.setStatus("not available");
            } else {
                airplane.setStatus("available");
            }
        }
    }

    public void listAirplanes() {
        if (getAirplanes() != null) {
            getAirplanes().forEach(System.out::println);
        } else if (getAirplanes().isEmpty()) {
            System.out.println("vacio");
        }
    }

    public void listAirplanesWithOptions() {
        if (getAirplanes() != null) {
            int i = 1;
            for (Airplane a : getAirplanes()) {
                System.out.println(i + ") " + a.getRegistrationNumber());
            }
        }

    }

    /**
     * METHODS CONTROL EMPLOYEES
     */
    public boolean addEmployee(Employee employee) {
        if (getEmployees() == null) {
            setEmployees(new HashSet<>());
        }
        return getEmployees().add(employee);
    }

    public void removeEmployee(Employee employee) {
        if (getEmployees() != null) {
            getEmployees().remove(employee);
        }
    }

    public Employee searchEmployee(Integer nroIdentify) throws NotFoundException {
        if (getEmployees() != null) {
            for (Employee employee : getEmployees()) {
                if (employee.getNumberIdentify().equals(nroIdentify)) {
                    return employee;
                }
            }
            throw new NotFoundException("No se encontro al empleado");
        }
        return null;
    }

    public void listEmployee() {
        if (getEmployees() != null) {
            getEmployees().forEach(System.out::println);
        }
    }

    public boolean changeStatus(Integer nroIdentity) {
        try {
            Employee x = this.searchEmployee(nroIdentity);
            if (x != null) {
                if (x.getStatus().equalsIgnoreCase("ACTIVE")) {
                    x.setStatus("INACTIVE");
                    return true;
                } else if (x.getStatus().equalsIgnoreCase("INACTIVE")) {
                    x.setStatus("ACTIVE");
                    return true;
                }
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void changeWorkstation(Integer nroIdentity) throws NotFoundException {
        Employee x = this.searchEmployee(nroIdentity);
        System.out.printf("el empleado: %s es %s a que puesto desea modificarlo?%n", x.getName(), x.getWorkstation());
        Scanner scanner = new Scanner(System.in);
        String[] workstations = {"PILOTO", "TRIPULANTE DE CABINA", "AUXILIAR DE TIERRA",
                "TECNICO DE OPERACIONES", "TECNICO ADMINISTRATIVO", "AGENTE DE SERVICIO", "DESPACHADOR DE VUELOS"};
        for (int i = 0; i < workstations.length; i++) {
            System.out.println("i+1) workstations[i]");
        }
        x.setWorkstation(workstations[scanner.nextInt() - 1]);
    }

    /**
     * METHODS CONTROL LOCATIONS
     */
    public boolean addLocation(Location location) {
        if (getLocations() == null) {
            setLocations(new HashSet<>());
        }
        return getLocations().add(location);
    }

    public boolean addLocationByKeyboard() {
        if (getLocations() == null) {
            setLocations(new HashSet<>());
        }
        int option;
        Scanner scanner = new Scanner(System.in);
        Location location = new Location();
        System.out.println("\nCarga de Locacion...");
        System.out.println("\nNombre del Aeropuerto: ");
        location.setNameAirport(scanner.nextLine());
        System.out.println("\nCiudad,Pais: ");
        location.setLocation(scanner.nextLine());
        System.out.println("\nCantidad de puertas: ");
        int quantity = Input.requestUserInputInt();
        if (quantity >= 0) {
            location.setDoors(new ArrayList<>());
            for (int i = 0; i < quantity; i++) {
                location.getDoors().add(new BoardingDoor((new Random().nextInt(100, 999)), true));
            }
        }
        System.out.println("\nDesea cargarlo? \n1) SI \n2) NO");
        option = Input.requestUserInputInt();
        if (option == 2) {
            return false;
        }

        System.out.println(location);
        return this.addLocation(location);
    }

    public boolean removeLocation(Location location) {
        if (getLocations() != null) {
            return getLocations().remove(location);
        }
        return false;
    }

    /*public void SearchLocation(){

        int selection = 0;
        int i = 0;
        if(getLocations()!=null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("seleccione un aeropuerto...");
            this.menuSearchLocation();
            selection = scanner.nextInt();
            Location location = searchLocationForAirportName()
            BoardingDoor door = this.searchBoardingDoor(getLocations().);
        }
    }*/

    public Location searchLocationForAirportName(String name) {
        if (getLocations() != null) {
            for (Location location : getLocations()) {
                if (location.getNameAirport().equals(name)) {
                    return location;
                }
            }
        }
        return null;
    }

    /*public void menuSearchLocation(){
        int optionDoor = 1;
        int optionLocation = 1;
        int selection = 0;
        if(getLocations()!=null){
            for (Location location : getLocations()){
                System.out.println(optionLocation+ ") Aeropuerto: " + location.getNameAirport() + " Pais: " + location.getLocation());
                optionLocation++;
                for (BoardingDoor boardingDoor : location.getDoors()){
                    System.out.println(optionDoor+ ") Door: "+ boardingDoor.getCode() + " Status: " + boardingDoor.isStatus());
                    optionDoor++;
                }
                optionDoor = 1;
            }
        }
    }*/

    /*public BoardingDoor searchBoardingDoor(Location location){
        if(location!=null){
            int optionDoor = 1;
            int selection = 0;
            for (BoardingDoor boardingDoor : location.getDoors()){
                System.out.println(optionDoor+ ") Door: "+ boardingDoor.getCode() + " Status: " + boardingDoor.isStatus());
                optionDoor++;
                switch (selection){
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        }
    }*/

    //public String searchLocation()
    public void listLocation() {
        if (getLocations() != null) {
            for (Location l : getLocations()){
                l.printLocation();
            }
        }
    }

    public boolean containThisDestiny(String destiny) {
        if (!flights.isEmpty()) {
            for (Flight f : flights) {
                if (f.thisFlightExist(destiny)) {
                    return true;
                }
            }
        }
        return false;
    }

    public HashMap<String, Flight> getThisFlight(String destiny) {
        HashMap<String, Flight> myDestiny = new HashMap<>();
        for (Flight a : flights) {
            if (a.getDestiny().equalsIgnoreCase(destiny)) {
                myDestiny.put(getAirlineName(), a);
            }
        }
        return myDestiny;
    }

    public boolean thisCodeIsInUse(String code) {
        if (!flights.isEmpty()) {
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getCode().equalsIgnoreCase(code)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Flight searchFlightCode(String code) {
        if (!flights.isEmpty()) {
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getCode().equalsIgnoreCase(code)) {
                    return flights.get(i);
                }
            }
        }
        return null;
    }

    public void addFlight(String code) {
        Flight flight = new Flight();
        try {
            flight.setCode(code);
            flight.setAirplane(listAndReturnAirplanesAvailable());
            setOriginAndDestiny(flight);
            flight.setTime(setFlightTime());
            flights.add(flight);
        } catch (NotFoundException | FormatIncorrectException | InvalidIndexException e) {
            flight.getAirplane().setStatus("Available");
            System.out.println(e.getMessage());
        }
    }

    public Airplane listAndReturnAirplanesAvailable() throws InvalidIndexException {
        ArrayList<Airplane> availables = new ArrayList<>();
        int i = 1;
        System.out.println("Seleccione el Indice del Avión a usar:");
        for (Airplane airplane : airplanes) {
            if (airplane.getStatus().equalsIgnoreCase("Available")) {
                System.out.println(i + ")" + airplane.getRegistrationNumber());
                availables.add(airplane);
                i++;
            }
        }
        int index = Input.requestUserInputInt();
        if (index > availables.size()) {
            throw new InvalidIndexException("El indice que selecciono es invalido");
        }
        availables.get(index - 1).setStatus("Not Available");
        return availables.get(index - 1);
    }

    public void setOriginAndDestiny(Flight flight) throws NotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese nombre de la Locación de Origen");
        String location = scanner.nextLine();
        if (!thisLocationExists(location)) {
            throw new NotFoundException("La locación de Origen no se pudo encontrar, verifique la información o agréguela");
        }
        flight.setOrigin(location);
        flight.setDoor(String.valueOf(autoSelectOriginDoor(location)));
        System.out.println("Ingrese nombre de la Locación de Destino");
        location = scanner.nextLine();
        if (!thisLocationExists(location)) {
            throw new NotFoundException("La locacion de Destino no se pudo encontrar, verifique la información o agréguela");
        }
        flight.setDestiny(location);
    }

    public Integer autoSelectOriginDoor(String location) {
        for (Location value : locations) {
            if ((value.getLocation().equalsIgnoreCase(location))) {
                return searchAvailableDoor(value);
            }
        }
        return null;
    }

    public boolean thisLocationExists(String location) {
        if (!locations.isEmpty()) {
            for (Location value : locations) {
                if (value.getLocation().equalsIgnoreCase(location)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer searchAvailableDoor(Location location) {
        if (!location.getDoors().isEmpty()) {
            for (int i = 0; i < location.getDoors().size(); i++) {
                if (location.getDoors().get(i).getStatus()) {
                    location.getDoors().get(i).setStatus(false);
                    return location.getDoors().get(i).getCode();
                }
            }
        }
        return null;
    }

    public LocalDateTime setFlightTime() throws FormatIncorrectException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese dia del Vuelo: [1 a 31]");
        int day = Input.requestUserInputInt();
        if (day > 31 || day < 1) {
            throw new FormatIncorrectException("Dia no puede ser:" + day);
        }
        System.out.println("Ingrese Mes del Vuelo: [1 a 12]");
        int month = Input.requestUserInputInt();
        if (month < 1 || month > 12) {
            throw new FormatIncorrectException("Mes no puede ser:" + month);
        }
        System.out.println("Ingrese Año del Vuelo: [Desde 2024 en adelante]");
        int year = Input.requestUserInputInt();
        if (year < 2024) {
            throw new FormatIncorrectException("Año no puede ser menor a 2024");
        }
        LocalDate date = LocalDate.of(year, month, day);
        System.out.println("Ingrese Horario del Vuelo: (hh:mm)");
        String fligthTime = scanner.nextLine();
        if (formatIsCorrect(fligthTime)) {
            String[] parts = fligthTime.split(":");
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            if ((hour < 0) || (hour > 23)) {
                System.out.println("Error: Hora no puede ser " + hour);
            } else if ((minute < 0) || (minute > 59)) {
                System.out.println("Error: Minutos no puede ser: " + minute);
            } else {
                LocalTime time = LocalTime.of(hour, minute);
                LocalDateTime schedule = LocalDateTime.of(date, time);
                return schedule;
            }
        } else {
            System.out.println("Error: El formato que debe usar es (hh:mm)");
        }
        return null;
    }


    private boolean formatIsCorrect(String flightTime) {
        // Verificar que el formato sea "hh:mm" y no contenga otros caracteres
        return flightTime.matches("\\d{2}:\\d{2}");
    }

    public void menuFlightModification(Flight flight) throws NotFoundException, FormatIncorrectException, InvalidIndexException {
        System.out.println("Menu de Modificación de Vuelo:");
        System.out.println("1. Cambiar Avión");
        System.out.println("2. Cambiar Origen y Destino");
        System.out.println("3. Cambiar Fecha y Hora de Despegue");
        int option = Input.requestUserInputInt();
        switch (option) {
            case 1:
                flight.setAirplane(listAndReturnAirplanesAvailable());
                break;
            case 2:
                setOriginAndDestiny(flight);
                break;
            case 3:
                flight.setTime(setFlightTime());
                break;
            default:
                System.out.println("Opción Invalida");
                break;
        }
    }
}
