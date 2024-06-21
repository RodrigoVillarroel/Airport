package Models;

import Exceptions.AlreadyExistsException;
import Exceptions.InvalidIndexException;
import Exceptions.NotAvailableForSaleException;
import Exceptions.NotFoundException;
import Interfaces.ITicketManagement;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class AirportTicketOffice extends OfficeTicket implements ITicketManagement <AirportTicket, Double> {
    private HashMap <String, AirportTicket> reservedTickets;
    private HashMap<String, AirportTicket> ticketStock;

    public AirportTicketOffice() {
        super();
        this.reservedTickets = new HashMap<>();
        this.ticketStock = new HashMap<>();
    }

    public HashMap<String, AirportTicket> getReservedTickets() {
        return reservedTickets;
    }

    public HashMap<String, AirportTicket> getTicketStock() {
        return ticketStock;
    }

    public AirportTicket sellTicket(Flight flight, LocalDateTime time, String seat, Passanger passanger, Double price) throws NotAvailableForSaleException {
        if (isTicketAvailable(flight.getOrigin(), flight.getDestiny(), time, seat, flight.getDoor())) {
           /* if (seat) {
                price = additionalCost(); //Verificar el tipo de asiento para definir costos adicionales
            }*/
            if(passanger.isOverweight()){//PENDIENTE DE CREACION
                price = additionalCost();
            }
            AirportTicket ticket = removeTicketFromStock(flight.getOrigin(), flight.getDestiny(), time, seat, flight.getDoor());
            ticket.setPrice(price);
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
        int maxSeatsPerRow = flight.getAirplane().getSeatForLetter();

        for (int i = 1; i <= flight.getAirplane().getTotalCapacity(); i++) {
            int seatNumber = i % maxSeatsPerRow;  // Número de asiento (1-10)

            // Si hemos alcanzado el asiento número 10, incrementamos la letra de la fila
            if (seatNumber == 0) {
                seatNumber = maxSeatsPerRow;
            }

            String seat = seatRow + String.valueOf(seatNumber);
            if (!isTicketAvailable(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor())) {
                AirportTicket ticket = new AirportTicket(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, null,0D, flight.getDoor());
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

    public void regenerateTicketStock(Airline airline) throws NotFoundException, InvalidIndexException {
        for(int i=0; i<airline.getFlights().size(); i++){
            Flight flight = airline.getFlights().get(i);
            updateTicketStock(flight);
        }
    }

    public String listSeats(String from, String to, LocalDateTime time, Flight flight){
        StringBuilder builder = new StringBuilder();
        char seatRow = 'A';  // Letra inicial para la fila de asientos
        int maxSeatsPerRow = flight.getAirplane().getSeatForLetter();
        int sideRows = 2;
        if (maxSeatsPerRow <= 6) {
            sideRows = 1;
        }
        int middleRow = maxSeatsPerRow - sideRows * 2;

        for (int i = 1; i <= flight.getAirplane().getTotalCapacity(); i++) {
            int seatNumber = i % maxSeatsPerRow;  // Número de asiento

            if (seatNumber == 0) {
                seatNumber = maxSeatsPerRow;
            }
            String seat = seatRow + String.valueOf(seatNumber);
            if (!isTicketAvailable(from, to, time, seat, flight.getDoor())) {
                builder.append("\u001B[31m"); // Color rojo para los asientos no disponibles
            } else {
                builder.append("\u001B[32m"); // Color verde para los asientos disponibles
            }
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

    public boolean hasStock(String from, String to, LocalDateTime time, Flight flight) {
        char seatRow = 'A';  // Letra inicial para la fila de asientos
        int maxSeatsPerRow = flight.getAirplane().getSeatForLetter();
        int i = 1;
        boolean ans = false;
        while ((i <= flight.getAirplane().getTotalCapacity()) && !ans) {
            int seatNumber = i % maxSeatsPerRow;  // Número de asiento

            if (seatNumber == 0) {
                seatNumber = maxSeatsPerRow;
            }
            String seat = seatRow + String.valueOf(seatNumber);
            if (isTicketAvailable(from, to, time, seat, flight.getDoor())) {
                ans = true;
            }
            if (seatNumber == maxSeatsPerRow) {
                seatRow++;
            }
            i++;
        }
        return ans;
    }

    public void removeTicketStock(String from, String to, LocalDateTime time, Flight flight) {
        char seatRow = 'A';  // Letra inicial para la fila de asientos
        int maxSeatsPerRow = flight.getAirplane().getSeatForLetter();

        for (int i = 1; i <= flight.getAirplane().getTotalCapacity(); i++) {
            int seatNumber = i % maxSeatsPerRow;  // Número de asiento (1-10)

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
            throw new AlreadyExistsException("This ticket code already exists: " + code);
        }
    }

}
