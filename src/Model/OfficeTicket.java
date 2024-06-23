package Model;

public abstract class OfficeTicket {
    private Double price;
    private Double additionalCost;

    OfficeTicket(){}

    // region Getters & Setters
    public Double getPrice() {
        return price;
    }

    public Double getAdditionalCost() {
        return 150D;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAdditionalCost(Double additionalCost) {
        this.additionalCost = additionalCost;
    }
    // endregion

    public Double additionalCost(){
        return getPrice() + getAdditionalCost();
    }
}
