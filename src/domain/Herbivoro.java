package domain;

import java.util.InvalidPropertiesFormatException;

public class Herbivoro extends Mamifero{
    private static double porcentajePeso = 2;
    private final double valorFijo;

    public Herbivoro(int edad, double peso, Especie especie, Sector sector, double valorFijo, Pais pais) throws InvalidPropertiesFormatException {
        super(TipoAlimentacion.HERBIVORO, edad, peso, especie, sector, pais);
        this.valorFijo = valorFijo;
    }

    public static void setPorcentajePeso(double porcentajePeso){
        Herbivoro.porcentajePeso = porcentajePeso;
    }

    public static double getPorcentajePeso() {
        return porcentajePeso;
    }


    public double getValorFijo() {
        return valorFijo;
    }

    @Override
    public double calcularCantidadDeComida() {
        double comida = peso * porcentajePeso + valorFijo;
        return comida;
    }
}