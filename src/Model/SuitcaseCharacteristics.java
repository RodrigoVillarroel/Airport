package Model;

public enum SuitcaseCharacteristics {
    NORMAL(0.56F, 0.40F, 0.25F, 23F),
    SOBREPESO(0.56F, 0.40F, 0.25F, 24.7F),
    SOBREDIMENSIONES(0.60F, 0.50F, 0.30F, 23F);

    private final Float alto;
    private final Float largo;
    private final Float ancho;
    private final Float peso;

    SuitcaseCharacteristics(Float alto, Float largo, Float ancho, Float peso) {
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.peso = peso;
    }

    public Float getLargo() {
        return largo;
    }

    public Float getAncho() {
        return ancho;
    }

    public Float getAlto() {
        return alto;
    }

    public Float getPeso() {
        return peso;
    }

    public boolean isOverDimension(){
        return getAlto() > 0.56F || getAncho() > 0.40F || getLargo() > 0.40F;
    }
    public boolean isOverweight(){
        return getPeso() > 23F;
    }
}
