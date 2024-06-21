package Interfaces;

import Exceptions.NotAvailableForSaleException;
import Models.Flight;
import Models.Passanger;

import java.sql.Time;
import java.time.LocalDateTime;

public interface ITicketManagement <T, E>{

    T sellTicket(Flight flight, LocalDateTime time, String seat, Passanger passanger, E element) throws NotAvailableForSaleException;
}
