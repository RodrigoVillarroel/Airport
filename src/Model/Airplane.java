package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.Objects;

public class Airplane {
    @JsonProperty("registration_number")
    private String registrationNumber;
    @JsonProperty("plane_status")
    private String status;
    @JsonProperty("capabilities")
    private AirplaneCapabilities capabilities;

    public Airplane() {

    }

    public Airplane(String registrationNumber, String status, AirplaneCapabilities capabilities) {
        setRegistrationNumber(registrationNumber);
        setStatus(status);
        setCapabilities(capabilities);
    }

    // region Getters & Setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AirplaneCapabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(AirplaneCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    // endregion
    @Override
    public String toString() {
        return "Código de Identificacion: " + getRegistrationNumber() +" - " + "Capacidad Total: " +getCapabilities().getTotalCapacity() + "-" + "Estatus: " + getStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Objects.equals(registrationNumber, airplane.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
