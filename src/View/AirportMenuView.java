package View;

import Exceptions.InvalidIndexException;

import java.util.Scanner;

public class AirportMenuView {
    private final Scanner scanner;

    public AirportMenuView() {
        this.scanner =  new Scanner(System.in);
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

    public void displayFlightsMenu() {
        System.out.println("\n----Menu Vuelos----");
        System.out.println("1. Crear Vuelo");
        System.out.println("2. Borrar Vuelo");
        System.out.println("3. Modificar Vuelo");
        System.out.println("4. Buscar Vuelo");
        System.out.println("5. Volver al Menú Principal");
    }

    public void displayAirlinesMenu() {
        System.out.println("\n----Menu Aerolíneas----");
        System.out.println("1. Menu Vuelos");
        System.out.println("2. Menu Aviones");
        System.out.println("3. Menu Locaciones");
        System.out.println("4. Menu Empleados");
        System.out.println("5. Volver al Menú Principal");
    }

    public void displayAirplanesMenu() {
        System.out.println("\n----Administrar Aviones----");
        System.out.println("1. Agregar Avion");
        System.out.println("2. Quitar Avion");
        System.out.println("3. Modificar Avion");
        System.out.println("4. buscar Avion");
        System.out.println("5. Volver al Menú Principal");
    }

    public void displayEmployeeMenu() {
        System.out.println("\n----Administrar Empleados----");
        System.out.println("1. Agregar Empleado");
        System.out.println("2. Quitar Empleado");
        System.out.println("3. Modificar Empleado");
        System.out.println("4. Buscar Empleado");
        System.out.println("5. Volver al Menú Principal");
    }

    public void displayLocationsMenu() {
        System.out.println("\n----Administrar Locaciones----");
        System.out.println("1. Agregar Nueva Locación");
        System.out.println("2. Quitar Locación");
        System.out.println("3. Modificar Locación");
        System.out.println("4. Buscar Locación");
        System.out.println("5. Volver al Menú Principal");
    }

    public void displayTicketsSellsMenu() {
        System.out.println("\n----Menu de Venta----");
        System.out.println("1. Compra Online");
        System.out.println("2. Elegir Aerolíneas");
        System.out.println("3. Elegir Destino");
        System.out.println("4. Simular Vuelo");
        System.out.println("5. Volver al Menú Principal");
    }

    public int displayRequestPassangerInfo(){
        System.out.println("Ingresar Informacion del Pasagero:");
        System.out.println("Numero de Identificacion:");
        return scanner.nextInt();
    }

    public int displayRequesAirlineIndex(){
        System.out.println("Selecciones el indice de la Aerolinea:");
        return handleUserInput()-1;
    }

    public int displayRequestFlight(){
        System.out.println("Seleccione el indice del Vuelo:");
        return scanner.nextInt() - 1;
    }

    public String displayRequestSeat(){
        System.out.println("Seleccione el numero de asiento que desee");
        return scanner.nextLine();
    }


    // endregion

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

    public int handleUserInput() {
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }
    // endregion

}
