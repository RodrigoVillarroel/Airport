package Interfaces;

import Exceptions.NotAvailableForSaleException;
import Model.Flight;
import Model.Passanger;

import java.sql.Time;

public interface ITicketManagement <T, E>{

    T sellTicket(Flight flight, Time time, String seat, Passanger passanger, E element) throws NotAvailableForSaleException;
}
