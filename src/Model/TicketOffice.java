package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class TicketOffice {
    @JsonProperty("price")
    private Double price;
    @JsonProperty("taxes")
    private Float taxes;

    TicketOffice(){}

    // region Getters & Setters
    public Double getPrice() {
        return price;
    }

    public Float getTaxes() {
        return taxes;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTaxes(Float taxes) {
        this.taxes = taxes;
    }


}
