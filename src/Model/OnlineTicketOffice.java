package Model;
import Exceptions.AlreadyExistsException;
import Exceptions.NotAvailableForSaleException;
import Interfaces.ITicketManagement;
import java.time.LocalDateTime;

public class OnlineTicketOffice extends OfficeTicket implements ITicketManagement<String, AirportTicketOffice> {
    public OnlineTicketOffice() {
        super();
    }

    public String sellTicket(Flight flight, LocalDateTime time, String seat, Passanger passanger, AirportTicketOffice airportTicketOffice) throws NotAvailableForSaleException {
        if (airportTicketOffice.isTicketAvailable(flight.getOrigin(), flight.getDestiny(), time, seat, flight.getDoor())) {
            double price = getPrice();
            /*if () {
                price = additionalCost(); VERIFICACION DE ASIENTO VIP Y APLICACION DE COSTOS ADICIONALES
            }*/
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


}
