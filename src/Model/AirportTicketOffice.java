package Model;

import Exceptions.AlreadyExistsException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import Interfaces.ITicketManagement;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

public class AirportTicketOffice extends TicketOffice implements ITicketManagement <AirportTicket, Luggage> {
    @JsonIgnore
    private HashMap <String, AirportTicket> reservedTickets;
    @JsonIgnore
    private HashMap<String, AirportTicket> ticketStock;

    public AirportTicketOffice() {
        super();
        this.reservedTickets = new HashMap<>();
        this.ticketStock = new HashMap<>();
    }

    public HashMap<String, AirportTicket> getReservedTickets() {
        return reservedTickets;
    }

    public AirportTicket sellTicket(Flight flight, String seat, Passenger passenger, Luggage luggage) throws NotAvailableForSaleException {
        if (isTicketAvailable(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor())) {
            Double price = getPrice();
            int count = luggage.countFines(); // Cuento las infracción de sobrepeso o de dimensiones en el equipaje
            if(count != 0) {//Si hay al menos una infracción aplico los impuestos según las infracciones detectadas
                price = price * (getTaxes()+count);//Sumamos el % de los impuestos sumados a la cantidad de multas
            }
            AirportTicket ticket = removeTicketFromStock(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor());
            ticket.setPrice(price);
            ticket.setPassanger(passenger);
            return ticket;
        } else {
            throw new NotAvailableForSaleException("This seat is not available");
        }
    }

    public boolean isTicketAvailable(String from, String to, LocalDateTime time, String seat, String gate){
        String key = generateTicketKey(from, to, time, seat, gate);
        return ticketStock.containsKey(key);
    }

    public String generateTicketKey(String from, String to, LocalDateTime time, String seat, String gate){
        return from + "-" + to + "/" + time + "/" + gate + "/" + seat;
    }

    public AirportTicket removeTicketFromStock(String from, String to, LocalDateTime time, String seat, String gate){
        String key = generateTicketKey(from, to, time, seat, gate);
        return ticketStock.remove(key);
    }

    public void updateTicketStock(Flight flight) {
        char seatRow = 'A';  // Letra inicial para la fila de asientos
        int maxSeatsPerRow = flight.getAirplane().getCapabilities().getSeatForLetter();

        for (int i = 1; i <= flight.getAirplane().getCapabilities().getTotalCapacity(); i++) {
            int seatNumber = i % maxSeatsPerRow;  // Número de asiento (1-10)

            // Si hemos alcanzado el asiento número 10, incrementamos la letra de la fila
            if (seatNumber == 0) {
                seatNumber = maxSeatsPerRow;
            }

            String seat = seatRow + String.valueOf(seatNumber);
            if (!isTicketAvailable(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor())) {
                AirportTicket ticket = new AirportTicket(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, null,0D, flight.getDoor(), flight.getCode());
                addTicketToStock(ticket);
            }
            if (seatNumber == maxSeatsPerRow) {
                seatRow++;
            }
        }
    }

    public void addTicketToStock(AirportTicket ticket) {
        String key = generateTicketKey(ticket.getFrom(), ticket.getTo(),ticket.geBordingTime(), ticket.getSeat(), ticket.getGate());
        ticketStock.put(key, ticket);
    }

    public void regenerateTicketStock(Flight flight){
        updateTicketStock(flight);
    }

    public String listSeats(Flight flight){
        StringBuilder builder = new StringBuilder();
        char seatRow = 'A';  // Letra inicial para la fila de asientos
        int sideRows = 2;
        int maxSeatsPerRow = flight.getAirplane().getCapabilities().getSeatForLetter();
        int totalCapacity = flight.getAirplane().getCapabilities().getTotalCapacity();
        if (maxSeatsPerRow <= 6) {
            sideRows = 1;
        }
        int middleRow = maxSeatsPerRow - sideRows * 2;
        for (int i=1; i <= totalCapacity; i++) {
            int seatNumber = i % maxSeatsPerRow;  // Número de asiento
            if (seatNumber == 0) {
                seatNumber = maxSeatsPerRow;
            }

            String seat = seatRow + String.valueOf(seatNumber);
            checkStockAndPrintBySeat(flight, i, builder, seat);
            builder.append(seat).append("\u001B[0m").append(" "); // Restablecer el color
            if ((seatNumber == sideRows) || (seatNumber == (middleRow + sideRows))){
                builder.append("  ");
            }
            if (seatNumber == maxSeatsPerRow) {
                seatRow++;
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public void checkStockAndPrintBySeat(Flight flight, int i, StringBuilder builder, String seat) {
        if(i<=flight.getAirplane().getCapabilities().getCapacityFirstClass()) {
            if (!isTicketAvailable(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor())) {
                builder.append("\u001B[31m"); // Color rojo para los asientos no disponibles
            } else {
                builder.append("\u001B[33m"); // Color Amarillo para los asientos Primera Clase disponibles
            }
        }
        if (i>flight.getAirplane().getCapabilities().getCapacityFirstClass() && i<=flight.getAirplane().getCapabilities().getCapacityEjecutive()+flight.getAirplane().getCapabilities().getCapacityFirstClass()){
            if (!isTicketAvailable(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor())) {
                builder.append("\u001B[31m"); // Color rojo para los asientos no disponibles
            } else {
                builder.append("\u001B[35m"); // Color violeta para los asientos Ejecutivos disponibles
            }
        }
        if (i>flight.getAirplane().getCapabilities().getCapacityFirstClass()+flight.getAirplane().getCapabilities().getCapacityEjecutive() && i<=flight.getAirplane().getCapabilities().getCapacityPremiumEconomic()+flight.getAirplane().getCapabilities().getCapacityFirstClass()+flight.getAirplane().getCapabilities().getCapacityEjecutive()){
            if (!isTicketAvailable(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor())) {
                builder.append("\u001B[31m"); // Color rojo para los asientos no disponibles
            } else {
                builder.append("\u001B[34m"); // Color azul para los asientos Económica Premium disponibles
            }
        }
        if (i>flight.getAirplane().getCapabilities().getCapacityPremiumEconomic()+flight.getAirplane().getCapabilities().getCapacityFirstClass()+flight.getAirplane().getCapabilities().getCapacityEjecutive() && i<=flight.getAirplane().getCapabilities().getCapacityEconomic()+flight.getAirplane().getCapabilities().getCapacityPremiumEconomic()+flight.getAirplane().getCapabilities().getCapacityFirstClass()+flight.getAirplane().getCapabilities().getCapacityEjecutive()){
            if (!isTicketAvailable(flight.getOrigin(),  flight.getDestiny(), flight.getTime(), seat, flight.getDoor())) {
                builder.append("\u001B[31m"); // Color rojo para los asientos no disponibles
            } else {
                builder.append("\u001B[32m"); // Color verde para los asientos disponibles
            }
        }
    }

    public boolean hasStock(String from, String to, LocalDateTime time, int maxSeatsPerRow, int totalCapacity, String door) {
        char seatRow = 'A';  // Letra inicial para la fila de asientos
        int i = 1;
        boolean ans = false;
        while (i <= totalCapacity && !ans) {
            int seatNumber = i % maxSeatsPerRow;  // Número de asiento

            if (seatNumber == 0) {
                seatNumber = maxSeatsPerRow;
            }
            String seat = seatRow + String.valueOf(seatNumber);
            if (isTicketAvailable(from, to, time, seat, door)) {
                ans = true;
            }
            if (seatNumber == maxSeatsPerRow) {
                seatRow++;
            }
            i++;
        }
        return ans;
    }
        //REMUEVE TODOS LOS TICKETS DE UN VUELO DEL STOCK
    public void removeTicketStock(String from, String to, LocalDateTime time, Flight flight) {
        char seatRow = 'A';  // Letra inicial para la fila de asientos
        int maxSeatsPerRow = flight.getAirplane().getCapabilities().getSeatForLetter();

        for (int i = 1; i <= flight.getAirplane().getCapabilities().getTotalCapacity(); i++) {
            int seatNumber = i % maxSeatsPerRow;

            // Si hemos alcanzado el asiento número 10, incrementamos la letra de la fila
            if (seatNumber == 0) {
                seatNumber = maxSeatsPerRow;
            }

            String seat = seatRow + String.valueOf(seatNumber);
            if (isTicketAvailable(from, to, time, seat, flight.getDoor())) {
                removeTicketFromStock(from, to, time, seat, flight.getDoor());
            }

            if (seatNumber == maxSeatsPerRow) {
                seatRow++;
            }
        }
    }

    public void addReservedTicket(String code, AirportTicket airportTicket) throws AlreadyExistsException {
        if(!getReservedTickets().containsKey(code)){
            getReservedTickets().put(code, airportTicket);
        }else {
            throw new AlreadyExistsException("Este Código de Ticket ya existe: " + code);
        }
    }

    public void exchangeTicket() throws NotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el Código de Ticket: ");
        String code = scanner.nextLine();
        if (getReservedTickets().containsKey(code)) {
            getReservedTickets().remove(code).printTicket();
        } else {
            throw new NotFoundException("Este Código de Ticket no existe: " + code);
        }
    }

    public boolean isPricesSet(){
        if (getPrice() != null || getTaxes() != null){
            return true;
        }
        System.out.println("Los valores de venta no están configurados. Sera redirigido para configurarlos");
        return false;
    }

    public void setPricesForSale(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el precio de los Boletos de Vuelo");
        setPrice(scanner.nextDouble());
        System.out.println("Ingrese el % de impuesto por Excesos en el Equipaje");
        setTaxes(scanner.nextFloat());
    }

    public void getAllCosts(){
        System.out.println("Costo de pasaje sin Multas:");
        System.out.println(getPrice());
        System.out.println("Costo de pasaje con 1 Multa:");
        System.out.println(getPrice() * getTaxes());
        System.out.println("Costo de pasaje con 2 Multas:");
        System.out.println(getPrice() * (getTaxes()*2));
        System.out.println("Costo de pasaje con 3 Multas");
        System.out.println(getPrice() * (getTaxes()*3));
    }
}
