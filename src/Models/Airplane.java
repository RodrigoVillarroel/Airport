package Models;

import java.text.MessageFormat;

public class Airplane {
    private String registrationNumber;
    private int capacityFirstClass;
    private int capacityEjecutive;
    private int capacityPremiumEconomic;
    private int capacityEconomic;
    private int totalCapacity;
    private int seatForLetter;
    private String status;

    public Airplane(String registrationNumber, int capacityFirstClass, int capacityEjecutive,
                    int capacityPremiumEconomic, int capacityEconomic, int totalCapacity, int seatForLetter, String status) {
        setRegistrationNumber(registrationNumber);
        setCapacityFirstClass(capacityFirstClass);
        setCapacityEjecutive(capacityEjecutive);
        setCapacityPremiumEconomic(capacityPremiumEconomic);
        setCapacityEconomic(capacityEconomic);
        setTotalCapacity(totalCapacity);
        setSeatForLetter(seatForLetter);
        setStatus(status);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getCapacityFirstClass() {
        return capacityFirstClass;
    }

    public void setCapacityFirstClass(int capacityFirstClass) {
        this.capacityFirstClass = capacityFirstClass;
    }

    public int getCapacityEjecutive() {
        return capacityEjecutive;
    }

    public void setCapacityEjecutive(int capacityEjecutive) {
        this.capacityEjecutive = capacityEjecutive;
    }

    public int getCapacityPremiumEconomic() {
        return capacityPremiumEconomic;
    }

    public void setCapacityPremiumEconomic(int capacityPremiumEconomic) {
        this.capacityPremiumEconomic = capacityPremiumEconomic;
    }

    public int getCapacityEconomic() {
        return capacityEconomic;
    }

    public void setCapacityEconomic(int capacityEconomic) {
        this.capacityEconomic = capacityEconomic;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getSeatForLetter() {
        return seatForLetter;
    }

    public void setSeatForLetter(int seatForLetter) {
        this.seatForLetter = seatForLetter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Airplane'{'registrationNumber=''{0}'', capacityFirstClass={1}, capacityEjecutive={2}, capacityPremiumEconomic={3}, capacityEconomic={4}, totalCapacity={5}, seatForLetter={6}, status=''{7}'''}'", getRegistrationNumber(), getCapacityFirstClass(), getCapacityEjecutive(), getCapacityPremiumEconomic(), getCapacityEconomic(), getTotalCapacity(), getSeatForLetter(), getStatus());
    }
}
