package Interfaces;

import Exceptions.NotAvailableForSaleException;
import Model.Flight;
import Model.Passenger;

public interface ITicketManagement <T, E>{

    T sellTicket(Flight flight, String seat, Passenger passenger, E element) throws NotAvailableForSaleException;
}
