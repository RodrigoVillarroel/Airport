package Model;

public class Backpack extends Equipaje{
    ;
    private BackpackWeight backpackWeight;

    public Backpack() {
    }

    public void setBackpackWeight(BackpackWeight backpackWeight) {
        this.backpackWeight = backpackWeight;
    }

    public BackpackWeight getBackpackWeight() {
        return backpackWeight;
    }
    public void setRandomsCharacteristics(int reference){

    }

    @Override
    public String toString() {
        return "Backpack{" +
                "backpackWeight=" + backpackWeight +
                '}';
    }
}

