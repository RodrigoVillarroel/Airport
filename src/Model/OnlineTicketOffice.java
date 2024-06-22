package Model;
import Exceptions.AlreadyExistsException;
import Exceptions.NotAvailableForSaleException;
import Interfaces.IAdditionalCost;
import Interfaces.ITicketManagement;
import java.time.LocalDateTime;

public class OnlineTicketOffice extends OfficeTicket implements ITicketManagement<String, AirportTicketOffice>, IAdditionalCost {
    public OnlineTicketOffice() {
        super();
    }

    public String sellTicket(Flight flight, LocalDateTime time, String seat, Passanger passanger, AirportTicketOffice airportTicketOffice) throws NotAvailableForSaleException {
        if (airportTicketOffice.isTicketAvailable(flight.getOrigin(), flight.getDestiny(), time, seat, flight.getDoor())) {
            double price = getPrice();
            int count = passanger.isOverweight();
            price = price + (getAdditionalCost() * count);
            AirportTicket ticket = airportTicketOffice.removeTicketFromStock(flight.getOrigin(), flight.getDestiny(), time, seat, flight.getDoor());
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


    @Override
    public Double addAdditionalCost() {

        return getAdditionalCost();
    }
}
