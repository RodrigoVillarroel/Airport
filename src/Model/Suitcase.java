package Model;

public enum Suitcase {
    NORMAL(0.56F, 0.40F, 0.25F, 23F),
    SOBREPESO(0.56F, 0.40F, 0.25F, 24.7F),
    SOBREDIMENSIONES(0.60F, 0.50F, 0.30F, 23F);

    private Float alto;
    private Float largo;
    private Float ancho;
    private Float peso;

    Suitcase(Float alto, Float largo, Float ancho, Float peso) {
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.peso = peso;
    }

    public Float getAlto() {
        return alto;
    }

    public Float getLargo() {
        return largo;
    }

    public Float getAncho() {
        return ancho;
    }

    public Float getPeso() {
        return peso;
    }

    public boolean isOverDimension(){
        if (getAlto()>0.56F || getAncho()>0.40F || getLargo()>0.40F){
            return true;
        }
        return false;
    }
    public boolean isOverweight(){
        if (getPeso()>23F){
            return true;
        }

        return false;
    }


}
