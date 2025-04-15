package domain;

import java.util.InvalidPropertiesFormatException;

public class Carnivoro extends Mamifero {
    private static double porcentajeExtra = 0.1;
    private static double pesoMaximo = 200;

    public Carnivoro(int edad, double peso, Especie especie, Sector sector, Pais pais) throws InvalidPropertiesFormatException {
        super(TipoAlimentacion.CARNIVORO, edad, peso, especie, sector, pais);
    }

    public static void setPorcentajeExtra(double porcentajeExtra){
        Carnivoro.porcentajeExtra = porcentajeExtra;
    }

    public static void setPesoMaximo(double pesoMaximo){
        Carnivoro.pesoMaximo = pesoMaximo;
    }

    public double getPorcentajeExtra() {
        return porcentajeExtra;
    }

    public double getPesoMaximo() {
        return pesoMaximo;
    }

    @Override
    public double calcularCantidadDeComida() {
        double porcentajeASumar = peso > pesoMaximo ? porcentajeExtra : 0;
        double comida = peso * especie.getPorcentajePesoCarnivoro() * (1 + porcentajeASumar);
        return comida;
    }
}