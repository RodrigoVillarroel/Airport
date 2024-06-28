package Model;

public class Backpack extends Equipaje{
    ;
    private BackpackWeight backpackWeight;

    public Backpack() {
    }

    public void setBackpackWeight(BackpackWeight backpackWeight) {
        this.backpackWeight = backpackWeight;
    }

    public void setRandomsCharacteristics(int reference){
        if(reference<7){
            setBackpackWeight(BackpackWeight.NORMAL);
        }
        else {
            setBackpackWeight(BackpackWeight.SOBREPESO);
        }
    }
    public boolean isOverweight() {
        return backpackWeight.isOverweight();
    }
}

