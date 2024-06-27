package Model;

import Exceptions.NotFoundException;
import Utils.Input;
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

    public void showFlights(){
        int i=1;
        for (Flight f : flights){
            System.out.println(i + ")" + "\n" + "\t");
            System.out.println("Desde: " + f.getOrigin() + " - ");
            System.out.println("Hasta: " + f.getDestiny() + "\n");
            System.out.println("Horario: " + f.getTime());
            i++;
        }
    }
    public Flight searchFlightByIndex(int index){
        if (!flights.isEmpty()){
            if(flights.size()>index){
                return flights.get(index);
            }
        }
        return null;
    }

    /** METHODS CONTROL AIRPLANES */
    public boolean addAirplaneByKeyboard(){
        if(getAirplanes()==null) {
            setAirplanes(new HashSet<>());
        }
        int option;
        int selection;
        Scanner scanner = new Scanner(System.in);
        Airplane airplane = new Airplane();
        System.out.println("\nCarga de avion...");
        System.out.println("\nCodigo de identificacion: ");
        airplane.setRegistrationNumber(scanner.nextLine());
        System.out.println("\nseleccione las capacidades:\n1) AirplaneCapabilities.SMALL}\n2) AirplaneCapabilities.MEDIUM}\n3) AirplaneCapabilities.BIG}");
        selection = scanner.nextInt();
        switch (selection){
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
        if(option==1){
            return this.addAirplane(airplane);
        }else{
            return false;
        }
    }
    public boolean addAirplane(Airplane airplane){
        if (getAirplanes() == null) {
            setAirplanes(new HashSet<>());
        }
        return getAirplanes().add(airplane);
    }
    public boolean removeAirplane(Airplane airplane){
        if(getAirplanes()!=null){
            return getAirplanes().remove(airplane);
        }
        return false;
    }
    public boolean removeAirplaneByRegistrationNumber(String numberRegistration){
        if(getAirplanes()!=null){
            return this.removeAirplane(this.searchAirplane(numberRegistration));
        }
        return false;
    }
    public Airplane searchAirplane(String numberRegistration){
        if (getAirplanes()!=null){
            for(Airplane airplane: getAirplanes()){
                if(airplane.getRegistrationNumber().equals(numberRegistration)){
                    return airplane;
                }
            }
        }
        return null;
    }
    public void modifyStatusAirplane(String numberRegistration){
        Airplane airplane = searchAirplane(numberRegistration);
        if (airplane!=null){
            System.out.println(airplane);
            if(airplane.getStatus().equals("available")){
                airplane.setStatus("not available");
            }else{
                airplane.setStatus("available");
            }
        }
    }
    public void listAirplanes(){
        if(getAirplanes()!=null){
            getAirplanes().forEach(System.out::println);
        }else if(getAirplanes().isEmpty()){
            System.out.println("vacio");
        }
    }
    public void listAirplanesWithOptions(){
        if(getAirplanes()!=null){
            int i = 1;
            for (Airplane a : getAirplanes()) {
                System.out.println(i + ") " + a.getRegistrationNumber());
            }
        }

    }

    /** METHODS CONTROL EMPLOYEES */
    public boolean addEmployee(Employee employee){
        if (getEmployees() == null) {
            setEmployees(new HashSet<>());
        }
        return getEmployees().add(employee);
    }
    public boolean addEmployeeByKeyboard(){

        if(getEmployees()==null) {
            setEmployees(new HashSet<>());
        }
        String[] workstations = {"PILOTO","TRIPULANTE DE CABINA", "AUXILIAR DE TIERRA",
                "TECNICO DE OPERACIONES","TECNICO ADMINISTRATIVO","AGENTE DE SERVICIO","DESPACHADOR DE VUELOS"};
        int option;
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();
        System.out.println("\nCarga de Empleado...");
        System.out.println("\nLegajo de identificacion: ");
        employee.setFile(scanner.nextLine());
        System.out.println("\nNombre: ");
        employee.setName(scanner.nextLine());
        System.out.println("\nApellido: ");
        employee.setSurname(scanner.nextLine());
        System.out.println("\nDNI: ");
        employee.setNumberIdentify(scanner.nextInt());
        System.out.println("\nPuesto de trabajo: ");
        for (int i = 0; i < workstations.length; i++) {
            System.out.println("i}) workstations[i]}");
        }
        employee.setWorkstation(workstations[scanner.nextInt()]);
        System.out.println("\nEdad: ");
        employee.setAge(scanner.nextInt());

        employee.setStatus("ACTIVE");

        System.out.println("\nDesea cargarlo? \n1) SI \n2) NO");
        option = scanner.nextInt();
        if(option ==2){
            return false;
        }

        System.out.println(employee);
        return this.addEmployee(employee);
    }
    public boolean removeEmployee(Employee employee){
        if(getEmployees()!=null) {
            return getEmployees().remove(employee);
        }
        return false;
    }
    public boolean searchEmployee(Employee employee){
        if(getEmployees()!=null){
            return getEmployees().contains(employee);
        }
        return false;
    }
    public Employee searchEmployee(Integer nroIdentify){
        if (getEmployees()!=null){
            for (Employee employee : getEmployees()){
                if (employee.getNumberIdentify().equals(nroIdentify)){
                    return employee;
                }
            }
        }
        return null;
    }
    public void listEmployee(){
        if(getEmployees()!=null){
            getEmployees().forEach(System.out::println);
        }
    }
    public boolean changeStatus(Integer nroIdentity){
        boolean respuesta = false;
        Employee x = this.searchEmployee(nroIdentity);
        if(x!=null){
            x.setStatus("INACTIVE");
            respuesta = true;
        }else{
            x.setStatus("ACTIVE");
            respuesta = true;
        }
        return respuesta;
    }

    public void changeWorkstation(Integer nroIdentity){
        Employee x = this.searchEmployee(nroIdentity);
        System.out.printf("el empleado: %s es %s a que puesto desea modificarlo?%n", x.getName(), x.getWorkstation());
        Scanner scanner = new Scanner(System.in);
        String[] workstations = {"PILOTO","TRIPULANTE DE CABINA", "AUXILIAR DE TIERRA",
                "TECNICO DE OPERACIONES","TECNICO ADMINISTRATIVO","AGENTE DE SERVICIO","DESPACHADOR DE VUELOS"};
        for (int i = 0; i < workstations.length; i++) {
            System.out.println("i+1) workstations[i]");
        }
        x.setWorkstation(workstations[scanner.nextInt()-1]);
    }

    /** METHODS CONTROL LOCATIONS */
    public boolean addLocation(Location location){
        if (getLocations() == null) {
            setLocations(new HashSet<>());
        }
        return getLocations().add(location);
    }
    public boolean addLocationByKeyboard(){
        if(getLocations()==null) {
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
        int quantity = scanner.nextInt();
        if(quantity>=0){
            location.setDoors(new ArrayList<>());
            for (int i = 0; i < quantity; i++) {
                location.getDoors().add(new BoardingDoor((new Random().nextInt(1,9999)),true));
            }
        }
        System.out.println("\nDesea cargarlo? \n1) SI \n2) NO");
        option = scanner.nextInt();
        if(option ==2){
            return false;
        }

        System.out.println(location);
        return this.addLocation(location);
    }
    public boolean removeLocation(Location location){
        if(getLocations()!=null){
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
    public void searchLocation(){
        if (getLocations()!=null){
            int i = 0;
            int selection = 0;
            this.menuSearchLocation();
            System.out.println("seleccione un aeropuerto...");
            Iterator<Location> iterator = getLocations().iterator();
            while (iterator.hasNext()) {
                System.out.println();
                System.out.println(iterator.next());
            }
        }
    }
    public Location searchLocationForAirportName(String name){
        if(getLocations()!=null){
            for (Location location : getLocations()){
                if(location.getNameAirport().equals(name)){
                    return location;
                }
            }
        }
        return null;
    }
    public void menuSearchLocation(){
        int optionLocation = 1;
        if(getLocations()!=null){
            for (Location location : getLocations()){
                System.out.println(optionLocation+ ") Aeropuerto: " + location.getNameAirport() + " Pais: " + location.getLocation());
                optionLocation++;
            }
        }
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
    public void listLocation(){
        if(getLocations()!=null){
            getLocations().forEach(System.out::println);
        }
    }
    public boolean containThisDestiny(String destiny){
        if (!flights.isEmpty()){
            for (Flight f : flights) {
                if (f.thisFlightExist(destiny)){
                    return true;
                }
            }
        }
        return false;
    }

    public HashMap<String, Flight> getThisFlight(String destiny){
        HashMap<String, Flight> myDestiny = new HashMap<>();
        for (Flight a : flights){
            if (a.getDestiny().equalsIgnoreCase(destiny)){
                myDestiny.put(getAirlineName(), a);
            }
        }
        return myDestiny;
    }

    public boolean searchFlightCode(String code){
        if(!flights.isEmpty()){
            for (int i=0;i<flights.size();i++){
                if(flights.get(i).getCode().equalsIgnoreCase(code)){
                    return true;
                }
            }
        }
        return false;
    }
    public void addFlight(String code) throws NotFoundException {
        Flight flight=new Flight();
        flight.newFlight(code);
        flight.setAirplane(listAndReturnAirplanesAvailable());
        setOriginAndDestiny(flight);
        flight.setTime(setFlightTime());
        flights.add(flight);
    }

    public Airplane listAndReturnAirplanesAvailable(){
        ArrayList<Airplane> availables = new ArrayList<>();
        System.out.println("Seleccione el Indice del Avión a usar:");
        for (Airplane airplane : airplanes){
            int i=1;
            if(airplane.getStatus().equalsIgnoreCase("Available")){
                System.out.println(i +")" + airplane.getRegistrationNumber());
                availables.add(airplane);
                i++;
            }
        }
        int index = Input.requestUserInputInt();
        availables.get(index-1).setStatus("Not Available");
        return availables.get(index-1);
    }

    public void setOriginAndDestiny(Flight flight) throws NotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese nombre de la Locación de Origen");
        String location = scanner.nextLine();
        if (!thisLocationExists(location)){
            throw new NotFoundException("La locación de Origen no se pudo encontrar, verifique la información o agréguela");
        }
        flight.setOrigin(location);
        flight.setDoor(String.valueOf(autoSelectOriginDoor(location)));
        System.out.println("Ingrese nombre de la Locación de Destino");
        location = scanner.nextLine();
        if (!thisLocationExists(location))
        {
            throw new NotFoundException("La locacion de Destino no se pudo encontrar, verifique la información o agréguela");
        }
        flight.setDestiny(location);
    }

    public Integer autoSelectOriginDoor(String location){
        for (Location value : locations){
            if ((value.getLocation().equalsIgnoreCase(location))){
                return searchAvailableDoor(value);
            }
        }
        return null;
    }

    public boolean thisLocationExists(String location){
        if (!location.isEmpty()) {
            for (Location value : locations) {
                if (value.getLocation().equalsIgnoreCase(location)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer searchAvailableDoor(Location location){
        if (!location.getDoors().isEmpty()){
            for (int i=0; i<location.getDoors().size();i++){
                if (location.getDoors().get(i).getStatus()){
                    location.getDoors().get(i).setStatus(false);
                    return location.getDoors().get(i).getCode();
                }
            }
        }
        return null;
    }

    public LocalDateTime setFlightTime(){
        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        LocalTime time = null;
        System.out.println("Ingrese dia del Vuelo");
        int day = Input.requestUserInputInt();
        System.out.println("Ingrese Mes del Vuelo");
        int month = Input.requestUserInputInt();
        System.out.println("Ingrese Año del Vuelo");
        int year = Input.requestUserInputInt();
        System.out.println("Ingrese la Hora de salida del Vuelo (hh:mm)");
        if(day>31){
            System.out.println("Error: Dia no puede ser superior 31");
        } else if (month>12){
            System.out.println("Error: Mes no puede ser superior a 12");
        } else if (year<2024){
            System.out.println("Error: Año no puede ser inferior a 2024");
        }else {
            date = LocalDate.of(year,month,day);

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
                }else {
                    time = LocalTime.of(hour, minute);
                }
            } else {
                System.out.println("Error: El formato que debe usar es (hh:mm)");
            }
        }
        LocalDateTime schedule = LocalDateTime.of(date, time);
        return schedule;
    }

    private boolean formatIsCorrect(String flightTime) {
        // Verificar que el formato sea "hh:mm" y no contenga otros caracteres
        return flightTime.matches("\\d{2}:\\d{2}");
    }

    public void selectFlight() throws NotFoundException {
        System.out.println("Seleccione el Vuelo a Modificar:");
        showFlights();
        int index = Input.requestUserInputInt()-1;
        Flight flight = flights.get(index);
        menuFlightModification(flight);
    }

    public void menuFlightModification(Flight flight) throws NotFoundException {
        System.out.println("Menu de Modificación de Vuelo:");
        System.out.println("1. Cambiar Avión");
        System.out.println("2. Cambiar Origen y Destino");
        System.out.println("3. Cambiar Fecha y Hora de Despegue");
        int option = Input.requestUserInputInt();
        switch (option){
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
