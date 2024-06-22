package Model;

public enum BackpackWeight {
    SOBREPESO(4F),
    NORMAL(3F);
    private Float peso;

    BackpackWeight(Float peso) {
        this.peso = peso;
    }

    public void setPeso(Float peso) {
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
