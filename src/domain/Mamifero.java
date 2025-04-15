package domain;

import java.util.InvalidPropertiesFormatException;

public abstract class Mamifero {
    protected int edad;
    protected double peso;
    protected Especie especie;
    protected Sector sector;
    protected TipoAlimentacion tipoAlimentacion;
    protected Pais pais;


    protected Mamifero(TipoAlimentacion tipoAlimentacion,
                       int edad, double peso, Especie especie, Sector sector, Pais pais) throws IllegalArgumentException, InvalidPropertiesFormatException {
        if(especie == null) throw new IllegalArgumentException();
        if(sector == null) throw  new IllegalArgumentException();
        if(especie.getTipoAlimentacion() != tipoAlimentacion) throw new InvalidPropertiesFormatException("El animal no puede pertenecer a la especie");
        this.tipoAlimentacion = tipoAlimentacion;
        this.edad = edad;
        this.peso = peso;
        this.especie = especie;
        this.sector = sector;
        this.pais = pais;
        sector.agregarAnimal(this);
    }

    public abstract double calcularCantidadDeComida();

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Sector getSector() {
        return sector;
    }
    public TipoAlimentacion getTipoAlimentacion(){
        return tipoAlimentacion;
    }
    
    public boolean TieneAlimentacion(TipoAlimentacion tipo){
        return tipoAlimentacion == tipo;
    }
}