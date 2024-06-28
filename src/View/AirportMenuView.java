package View;

import Exceptions.InvalidIndexException;
import Model.*;
import Utils.Input;
import Utils.RandomCodeGenerator;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class AirportMenuView {
    private final Scanner scanner;

    public AirportMenuView() {
        this.scanner = new Scanner(System.in);
    }

    // region Menu
    public void displayMainMenu() {
        System.out.println("\n----Menu Principal----");
        System.out.println("1. Menu Aerolíneas");
        System.out.println("2. Menu Aeropuerto");
        System.out.println("3. Menu de Venta");
        System.out.println("4. Salir");
    }

    public void displayAirportMenu() {
        System.out.println("\n----Administrar Aeropuerto----");
        System.out.println("1. Administrar Aerolínea");
        System.out.println("2. Administrar Pasajeros");
        System.out.println("3. Volver al Menú Principal");
    }

    public void displayAirlineMenu() {
        System.out.println("\n----Administrar Aerolínea----");
        System.out.println("1. Dar de alta");
        System.out.println("2. Dar de baja");
        System.out.println("3. Modificar nombre");
        System.out.println("4. Volver al Menú Principal");
    }

    public void displayPassengersMenu() {
        System.out.println("\n----Administrar Clientes/Pasajeros dados de alta en el sistema----");
        System.out.println("1. Listar pasajeros");
        System.out.println("2. Agregar Pasajero");
        System.out.println("3. Modificar Pasajero");
        System.out.println("4. Quitar Pasajero");
        System.out.println("5. Volver al Menú Principal");
    }

    public void displayFlightsMenu() {
        System.out.println("\n----Menu Vuelos----");
        System.out.println("1. Crear Vuelo");
        System.out.println("2. Borrar Vuelo");
        System.out.println("3. Modificar Vuelo");
        System.out.println("4. Buscar Vuelo");
        System.out.println("5. Enlistar Todos los Vuelos");
        System.out.println("6. Volver al Menú Principal");
    }

    public void displayAirlinesMenu(String name, String iataCode) {
        System.out.println("----Menu Aerolínea----");
        System.out.println("\n------" + name + "------\n------IATA CODE: " + iataCode + "------");
        System.out.println("1. Menu Vuelos");
        System.out.println("2. Menu Aviones");
        System.out.println("3. Menu Locaciones");
        System.out.println("4. Menu Empleados");
        System.out.println("5. Volver al Menú Principal");
    }

    public void displayAirplanesMenu() {
        System.out.println("\n----Administrar Aviones----");
        System.out.println("1. Agregar Avión");
        System.out.println("2. Quitar Avión");
        System.out.println("3. Modificar Estado de Avión");
        System.out.println("4. buscar Avión");
        System.out.println("5. listar Aviones");
        System.out.println("6. Volver al Menú Principal");
    }

    public void displayEmployeeMenu() {
        System.out.println("\n----Administrar Empleados----");
        System.out.println("1. Agregar Empleado");
        System.out.println("2. Quitar Empleado");
        System.out.println("3. Modificar Estado del empleado");
        System.out.println("4. Modificar Puesto de Trabajo del Empleado");
        System.out.println("5. Buscar Empleado");
        System.out.println("6. Listar Empleados");
        System.out.println("7. Volver al Menú Principal");
    }

    public void displayLocationsMenu() {
        System.out.println("\n----Administrar Locaciones----");
        System.out.println("1. Agregar Nueva Locación");
        System.out.println("2. Quitar Locación");
        System.out.println("3. Modificar Locación");
        System.out.println("4. Buscar Locación");
        System.out.println("5. Listar Locaciónes");
        System.out.println("6. Volver al Menú Principal");
    }

    public void displayTicketsSellsMenu() {
        System.out.println("\n----Menu de Venta----");
        System.out.println("1. Compra un Ticket de Vuelo");
        System.out.println("2. Comprar un Ticket Online");
        System.out.println("3. Canjear Código de Ticket");
        System.out.println("4. Comprar por Vuelo");
        System.out.println("5. Comprar por Destino");
        System.out.println("6. Simular Vuelo");
        System.out.println("7. Cambiar Valores de Tickets e Impuestos");
        System.out.println("8. Regenerar Todos los Stocks");
        System.out.println("9. Regenerar Stock de un Vuelo");
        System.out.println("10. Volver al Menú Principal");
    }
    // endregion

    public void displayAirlinesSummaryOption(LinkedList<Airline> airlines) {
        System.out.println("Aerolíneas disponibles:");
        for (Airline airline : airlines) {
            System.out.printf("(%s) Name: %s, Aita: %s, Airplanes: %d, Employees: %d, Flights: %d, Locations: %d  \n", airline.getClass().getSimpleName(), airline.getAirlineName(), airline.getIATAcode(), getSize(airline.getAirplanes()), getSize(airline.getEmployees()), getSize(airline.getFlights()), getSize(airline.getLocations()));
        }
    }

    public String handleModifyAirlineInput() {
        System.out.println("Ingrese el código IATA de la aerolínea a modificar:");
        return scanner.nextLine();
    }

    public String handleModifyAirlineNameInput() {
        scanner.nextLine();
        System.out.println("Ingrese el nuevo nombre:");
        return scanner.nextLine();
    }

    public String[] handleAddAirlineInput() {
        scanner.nextLine();
        System.out.println("Ingrese el nombre de la nueva aerolínea:");
        String airlineName = scanner.nextLine();
        System.out.println("Ingrese el IATA Code de la nueva aerolínea:");
        String iataCode = scanner.nextLine();
        return new String[]{airlineName, iataCode};
    }

    public String handleDeleteAirlineInput() {
        scanner.nextLine();
        System.out.println("Ingrese el código IATA de la aerolínea a quitar:");
        return scanner.nextLine();
    }

    public Person displayAddPersonOption() {
        System.out.println("DNI: ");
        Integer nationalId = Input.requestUserInputInt();
        System.out.println("Nombre: ");
        String name = scanner.nextLine();
        System.out.println("Apellido: ");
        String surname = scanner.nextLine();
        System.out.println("Edad: ");
        Integer age = Input.requestUserInputInt();
        return new Person(name, surname, age, nationalId);
    }

    public Employee displayAddEmployeeOption() {
        String[] workstations = {"PILOTO","TRIPULANTE DE CABINA", "AUXILIAR DE TIERRA",
                "TECNICO DE OPERACIONES","TECNICO ADMINISTRATIVO","AGENTE DE SERVICIO","DESPACHADOR DE VUELOS"};
        System.out.println("\nCarga de Empleado...");
        Person person = this.displayAddPersonOption();
        System.out.println("\nLegajo de identificacion: ");
        String file = scanner.nextLine();
        System.out.println("\nPuesto de trabajo: ");
        for (int i = 0; i < workstations.length; i++) {
            System.out.println(i+1 + ")" + workstations[i]);
        }
        String workStation = workstations[scanner.nextInt()-1];
        // TODO deberia cargar el status o esta bien que este harcodeado en active ??
        String status = "active";
        return new Employee(person.getName(), person.getSurname(), person.getAge(), person.getNumberIdentify(), file, workStation, status);
    }

    public Integer displayFindEmployeeOption() {
        System.out.println("Ingrese el dni del empleado: ");
        return Input.requestUserInputInt();
    }

    public Passenger displayAddPassengerOption() {
        Person person = this.displayAddPersonOption();
        String passportNumber = RandomCodeGenerator.generateRandomPassportNumber();
        return new Passenger(person.getName(), person.getSurname(), person.getAge(), person.getNumberIdentify(), passportNumber);
    }

    public String displayFindPassengerOption() {
        System.out.println("Ingrese el numero de pasaporte: ");
        return scanner.nextLine();
    }

    public Passenger displayModifyPassengerOption(Passenger passengerFound) throws InvalidIndexException {
        System.out.println("Ingrese que campo desea modificar : ");
        System.out.println("1- Nombre");
        System.out.println("2- Apellido");
        System.out.println("3- Edad");
        int option = Input.requestUserInputInt();
        if(option == 1) {
           String name = scanner.nextLine();
           passengerFound.setName(name);
        } else if(option == 2) {
            String surname = scanner.nextLine();
            passengerFound.setSurname(surname);
        } else if(option == 3) {
            Integer age = Input.requestUserInputInt();
            passengerFound.setAge(age);
        } else {
            throw new InvalidIndexException("Indice no valido");
        }
        return passengerFound;
    }

    // region Commons
    public void displayInvalidOptionMessage() {
        System.out.println("Opción no válida, intente nuevamente.");
    }

    public void displayBackMessage() {
        System.out.println("Volviendo al Menú Principal...");
    }

    public void displayLogOutMessage() {
        System.out.println("Saliendo...");
    }

    public void displayLineBreak() {
        System.out.println("\n");
    }

    public int handleUserInput() {
        System.out.print("Seleccione una opción: ");
        return Input.requestUserInputInt();
    }

    private int getSize(Collection<?> list) {
        return list == null ? 0 : list.size();
    }
    // endregion
}
