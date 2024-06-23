package Model;

public class  Suitcase extends Equipaje{
    private SuitcaseCharacteristics suitcaseCharacteristics;
    public Suitcase(){}

    public void setSuitcaseCharacteristics(SuitcaseCharacteristics suitcaseCharacteristics) {
        this.suitcaseCharacteristics = suitcaseCharacteristics;
    }

    public SuitcaseCharacteristics getSuitcaseCharacteristics() {
        return suitcaseCharacteristics;
    }

    public void setRandomCharacteristcs(int reference){
        if (reference<6){
            setSuitcaseCharacteristics(SuitcaseCharacteristics.NORMAL);
        }
        if (reference<11 && reference>=6){
            setSuitcaseCharacteristics(SuitcaseCharacteristics.SOBREDIMENSIONES);
        }
        if (reference<=15 && reference>=11){
            setSuitcaseCharacteristics(SuitcaseCharacteristics.SOBREPESO);
        }
    }

    public boolean isOverDimension(){
        return suitcaseCharacteristics.isOverDimension();
    }
    public boolean isOverweight(){
        return suitcaseCharacteristics.isOverweight();
    }

    @Override
    public String toString() {
        return "Suitcase{" +
                "suitcaseCharacteristics=" + suitcaseCharacteristics +
                '}';
    }
}
