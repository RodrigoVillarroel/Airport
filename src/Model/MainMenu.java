package Model;

import java.util.Scanner;

public class MainMenu {
    public void masterMenu() {

    }

//        public void masterMenu() {
//            Scanner scanner = new Scanner(System.in);
//            int opcion;
//
//            do {
//                System.out.println("Menu Principal:");
//                System.out.println("1. Administrar Aerolíneas");
//                System.out.println("2. Administrar Locaciones");
//                System.out.println("3. Menu de Venta");
//                System.out.println("4. Salir");
//                System.out.print("Seleccione una opción: ");
//                opcion = scanner.nextInt();
//
//                switch (opcion) {
//                    case 1:
//                        menuAerolineas();
//                        break;
//                    case 2:
//                        menuLocaciones();
//                        break;
//                    case 3:
//                        menuVentas();
//                        break;
//                    case 4:
//                        System.out.println("Saliendo...");
//                        break;
//                    default:
//                        System.out.println("Opción no válida, intente nuevamente.");
//                }
//            } while (opcion != 4);
//        }
//
//        private static void menuAerolineas() {
//            Scanner scanner = new Scanner(System.in);
//            int opcion;
//
//            do {
//                System.out.println("Administrar Aerolíneas:");
//                System.out.println("1. Agregar Aerolínea");
//                System.out.println("2. Quitar Aerolínea");
//                System.out.println("3. Modificar Aerolínea");
//                System.out.println("4. Volver al Menú Principal");
//                System.out.print("Seleccione una opción: ");
//                opcion = scanner.nextInt();
//
//                switch (opcion) {
//                    case 1:
//                        administrarAerolineas.agregarAerolinea();
//                        break;
//                    case 2:
//                        administrarAerolineas.quitarAerolinea();
//                        break;
//                    case 3:
//                        administrarAerolineas.modificarAerolinea();
//                        break;
//                    case 4:
//                        System.out.println("Volviendo al Menú Principal...");
//                        break;
//                    default:
//                        System.out.println("Opción no válida, intente nuevamente.");
//                }
//            } while (opcion != 4);
//        }
//
//        private static void menuLocaciones() {
//            Scanner scanner = new Scanner(System.in);
//            int opcion;
//
//            do {
//                System.out.println("Administrar Locaciones:");
//                System.out.println("1. Agregar Nueva Locación");
//                System.out.println("2. Quitar Locación");
//                System.out.println("3. Volver al Menú Principal");
//                System.out.print("Seleccione una opción: ");
//                opcion = scanner.nextInt();
//
//                switch (opcion) {
//                    case 1:
//                        administrarLocaciones.agregarLocacion();
//                        break;
//                    case 2:
//                        administrarLocaciones.quitarLocacion();
//                        break;
//                    case 3:
//                        System.out.println("Volviendo al Menú Principal...");
//                        break;
//                    default:
//                        System.out.println("Opción no válida, intente nuevamente.");
//                }
//            } while (opcion != 3);
//        }
//        public void agregarLocacion() {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Ingrese el nombre de la nueva locación:");
//            String nombre = scanner.nextLine();
//            locaciones.add(new Locacion(nombre));
//            System.out.println("Locación agregada.");
//        }
//
//        public void quitarLocacion() {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Ingrese el nombre de la locación a quitar:");
//            String nombre = scanner.nextLine();
//            locaciones.removeIf(locacion -> locacion.getNombre().equals(nombre));
//            System.out.println("Locación eliminada.");
//        }
//
//        public void mostrarLocaciones() {
//            System.out.println("Locaciones disponibles:");
//            for (Locacion locacion : locaciones) {
//                System.out.println(locacion);
//            }
//        }
//
//
//        private static void menuVentas() {
//            Scanner scanner = new Scanner(System.in);
//            int opcion;
//
//            do {
//                System.out.println("Menu de Venta:");
//                System.out.println("1. Compra Online");
//                System.out.println("2. Elegir Aerolíneas");
//                System.out.println("3. Elegir Destino");
//                System.out.println("4. Simular Vuelo");
//                System.out.println("5. Volver al Menú Principal");
//                System.out.print("Seleccione una opción: ");
//                opcion = scanner.nextInt();
//
//                switch (opcion) {
//                    case 1:
//                        System.out.println("Funcionalidad de Compra Online aún no implementada.");
//                        break;
//                    case 2:
//                        administrarAerolineas.mostrarAerolineas();
//                        break;
//                    case 3:
//                        administrarLocaciones.mostrarLocaciones();
//                        break;
//                    case 4:
//                        System.out.println("Funcionalidad de Simular Vuelo aún no implementada.");
//                        break;
//                    case 5:
//                        System.out.println("Volviendo al Menú Principal...");
//                        break;
//                    default:
//                        System.out.println("Opción no válida, intente nuevamente.");
//                }
//            } while (opcion!=5);
//        }
//
//        public void agregarAerolinea() {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Ingrese el nombre de la nueva aerolínea:");
//            String nombre = scanner.nextLine();
//            aerolineas.add(new Aerolinea(nombre));
//            System.out.println("Aerolínea agregada.");
//        }
//
//        public void quitarAerolinea() {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Ingrese el nombre de la aerolínea a quitar:");
//            String nombre = scanner.nextLine();
//            aerolineas.removeIf(aerolinea -> aerolinea.getNombre().equals(nombre));
//            System.out.println("Aerolínea eliminada.");
//        }
//
//        public void modificarAerolinea() {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Ingrese el nombre de la aerolínea a modificar:");
//            String nombre = scanner.nextLine();
//            for (Aerolinea aerolinea : aerolineas) {
//                if (aerolinea.getNombre().equals(nombre)) {
//                    System.out.println("Ingrese el nuevo nombre:");
//                    String nuevoNombre = scanner.nextLine();
//                    aerolinea.setNombre(nuevoNombre);
//                    System.out.println("Aerolínea modificada.");
//                    return;
//                }
//            }
//            System.out.println("Aerolínea no encontrada.");
//        }
//
//        public void mostrarAerolineas() {
//            System.out.println("Aerolíneas disponibles:");
//            for (Aerolinea aerolinea : aerolineas) {
//                System.out.println(aerolinea);
//            }
//        }
    }
