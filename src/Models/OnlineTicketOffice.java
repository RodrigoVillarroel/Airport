package Model;

import Exceptions.AlreadyExistsException;
import Exceptions.NotAvailableForSaleException;
import Interface.ITicketManagement;

import java.sql.Time;

public class OnlineTicketOffice extends OfficeTicket implements ITicketManagement<String, AirportTicketOffice> {
    public OnlineTicketOffice() {
        super();
    }

    public String sellTicket(Flight flight, Time time, String seat, Passanger passanger, AirportTicketOffice airportTicketOffice) throws NotAvailableForSaleException {
        if (airportTicketOffice.isTicketAvailable(flight.getOrigen().getLocation(), flight.getDestiny().getLocation(), time, seat, flight.getGate())) {
            double price = getPrice();
            /*if () {
                price = additionalCost(); VERIFICACION DE ASIENTO VIP Y APLICACION DE COSTOS ADICIONALES
            }*/
            AirportTicket ticket = airportTicketOffice.removeTicketFromStock(flight.getOrigen().getLocation(), flight.getDestiny().getLocation(), time, seat, flight.getGate());
            ticket.setPrice(price);

            String code = RandomCodeGenerator.generateRandomCode();
            try {
                airportTicketOffice.addReservedTicket(code, ticket);
            } catch (AlreadyExistsException exception) {
                ticket.setPrice(0D);
                airportTicketOffice.addTicketToStock(ticket);
                sellTicket(flight, time, seat, passanger, airportTicketOffice);
            }
            return code;
        }
        else {
            throw new NotAvailableForSaleException("This seat is not available.");
        }
    }


}
