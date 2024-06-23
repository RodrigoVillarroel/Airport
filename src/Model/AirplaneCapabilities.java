package Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum AirplaneCapabilities {
    SMALL(18,24,48,60,6),
    MEDIUM(21,35,63,77,7),
    BIG(24,32,64,80,8);

    @JsonProperty("first_class_capacity")
    private Integer capacityFirstClass;
    @JsonProperty("executive_class_capacity")
    private Integer capacityEjecutive;
    @JsonProperty("premium_economic_class_capacity")
    private Integer capacityPremiumEconomic;
    @JsonProperty("economic_class_capacity")
    private Integer capacityEconomic;
    @JsonProperty("total_capacity")
    private Integer totalCapacity;
    @JsonProperty("seatForLetter")
    private Integer seatForLetter;


    AirplaneCapabilities(Integer capacityFirstClass, Integer capacityEjecutive, Integer capacityPremiumEconomic,
                         Integer capacityEconomic, Integer seatForLetter) {
        this.capacityFirstClass = capacityFirstClass;
        this.capacityEjecutive = capacityEjecutive;
        this.capacityPremiumEconomic = capacityPremiumEconomic;
        this.capacityEconomic = capacityEconomic;
        this.seatForLetter = seatForLetter;
        setTotalCapacity();
    }

    private void setTotalCapacity(){
        this.totalCapacity = getCapacityFirstClass()+getCapacityEjecutive()+getCapacityPremiumEconomic()+getCapacityEconomic();
    }

    public Integer getCapacityFirstClass() {
        return capacityFirstClass;
    }

    public Integer getCapacityEjecutive() {
        return capacityEjecutive;
    }

    public Integer getCapacityPremiumEconomic() {
        return capacityPremiumEconomic;
    }

    public Integer getCapacityEconomic() {
        return capacityEconomic;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public Integer getSeatForLetter() {
        return seatForLetter;
    }
}
