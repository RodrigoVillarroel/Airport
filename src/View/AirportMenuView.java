package View;

import java.util.Scanner;

public class AirportMenuView {
    private final Scanner scanner;

    public AirportMenuView() {
        this.scanner =  new Scanner(System.in);
    }

    // region Menu
    public void displayMainMenu() {
        System.out.println("Menu Principal:");
        System.out.println("1. Administrar Aerolíneas");
        System.out.println("2. Administrar Locaciones");
        System.out.println("3. Menu de Venta");
        System.out.println("4. Salir");
    }

    public void displayAirlinesMenu() {
        System.out.println("Administrar Aerolíneas:");
        System.out.println("1. Agregar Aerolínea");
        System.out.println("2. Quitar Aerolínea");
        System.out.println("3. Modificar Aerolínea");
        System.out.println("4. Volver al Menú Principal");
    }

    public void displayLocationsMenu() {
        System.out.println("Administrar Locaciones:");
        System.out.println("1. Agregar Nueva Locación");
        System.out.println("2. Quitar Locación");
        System.out.println("3. Volver al Menú Principal");
    }

    public void displayTicketsSellsMenu() {
        System.out.println("Menu de Venta:");
        System.out.println("1. Compra Online");
        System.out.println("2. Elegir Aerolíneas");
        System.out.println("3. Elegir Destino");
        System.out.println("4. Simular Vuelo");
        System.out.println("5. Volver al Menú Principal");
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
