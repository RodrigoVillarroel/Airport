package Model;

public enum Backpack {
    SOBREPESO(4F),
    NORMAL(3F);
    private Float peso;

    Backpack(Float peso){
        this.peso = peso;
    }

    public Float getPeso() {
        return peso;
    }

    public boolean isOverweight(){
        if(getPeso()>3){
            return true;
        }

        return false;
    }
}
