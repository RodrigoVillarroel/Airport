package Interfaces;

import Exceptions.NotAvailableForSaleException;
import Model.Flight;
import Model.Passanger;

import java.time.LocalDateTime;

public interface ITicketManagement <T, E>{

    T sellTicket(Flight flight, LocalDateTime time, String seat, Passanger passanger, E element) throws NotAvailableForSaleException;
}
