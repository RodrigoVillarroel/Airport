package Model;
import Exceptions.AlreadyExistsException;
import Exceptions.NotAvailableForSaleException;
import Interfaces.ITicketManagement;

public class OnlineTicketOffice extends TicketOffice implements ITicketManagement<String, AirportTicketOffice>{
    public OnlineTicketOffice() {
        super();
    }

    public String sellTicket(Flight flight, String seat, Passenger passenger, AirportTicketOffice airportTicketOffice) throws NotAvailableForSaleException {
        if (airportTicketOffice.isTicketAvailable(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor())) {
            double price = 15;
            AirportTicket ticket = airportTicketOffice.removeTicketFromStock(flight.getOrigin(), flight.getDestiny(), flight.getTime(), seat, flight.getDoor());
            ticket.setPrice(price);

            String code = RandomCodeGenerator.generateRandomCode();
            try {
                airportTicketOffice.addReservedTicket(code, ticket);
            } catch (AlreadyExistsException exception) {
                ticket.setPrice(0D);
                airportTicketOffice.addTicketToStock(ticket);
                sellTicket(flight, seat, passenger, airportTicketOffice);
            }
            return code;
        } else {
            throw new NotAvailableForSaleException("This seat is not available.");
        }
    }
}
