package data;

import domain.*;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

public class Persistencia {
    private static ArrayList<Mamifero> animales = new ArrayList<>();
    private static ArrayList<Sector> sectores = new ArrayList<>();
    private static ArrayList<Especie> especies = new ArrayList<>();
    private static ArrayList<Pais> paises = new ArrayList<>();

    private static void inicializarEspecies() {
        especies.add(new Especie("León", TipoAlimentacion.CARNIVORO, 0.2));
        especies.add(new Especie("Jirafa", TipoAlimentacion.HERBIVORO, 0));
        especies.add(new Especie("Tigre", TipoAlimentacion.CARNIVORO, 0.15));
        especies.add(new Especie("Elefante", TipoAlimentacion.HERBIVORO, 0));
    }

    private static void inicializarSectores() {
        Empleado raul = new Empleado("Raul A", "20111222", "Tucumán" );
        Empleado maria = new Empleado("Maria B", "30111222", "Tucumán" );
        sectores.add(new Sector(1, -26.250724, -65.522827, 10, TipoAlimentacion.HERBIVORO, raul));
        sectores.add(new Sector(2, -26.252359, -65.521511, 10, TipoAlimentacion.CARNIVORO, maria));
        sectores.add(new Sector(3, -26.254661, -65.522726, 10, TipoAlimentacion.HERBIVORO, maria));
        sectores.add(new Sector(4, -26.257250, -65.523514, 10, TipoAlimentacion.CARNIVORO, raul));
    }
    
    private static void inicializarAnimales() throws InvalidPropertiesFormatException {
        animales.add(new Carnivoro(5,250,especies.get(0), sectores.get(1), paises.get(0)));
        animales.add(new Carnivoro(2,180,especies.get(2), sectores.get(3), paises.get(2)));
        animales.add(new Herbivoro(3, 1020,especies.get(1), sectores.get(0), 170, paises.get(4)));
        animales.add(new Herbivoro(8, 3800,especies.get(3), sectores.get(2), 320, paises.get(1)));
    }
    
    
 
    
    private static void inicializarPaises(){
        paises.add(new Pais("Madagascar","450"));
        paises.add(new Pais("Rusia","643"));
        paises.add(new Pais("India","356"));
        paises.add(new Pais("Brasil","076"));
        paises.add(new Pais("Australia","036"));
    }

    public static void inicializar() throws InvalidPropertiesFormatException{
        inicializarEspecies();
        inicializarSectores();
        inicializarPaises();
        //inicializarAnimales();
    }

    public static ArrayList<Mamifero> getAnimales() {
        return animales;
    }

    public static ArrayList<Sector> getSectores() {
        return sectores;
    }

    public static ArrayList<Especie> getEspecies() {
        return especies;
    }
    /* agegue esta funcion */
    public static ArrayList<Pais> getPaises(){
        return paises;
    }
    
    public static void guardarAnimal(Mamifero mamifero){
        System.out.print("persistencia");
        animales.add(mamifero);
    }
    /* --------------------- */
    public static double getTotalComida(TipoAlimentacion tipoAlimentacion) {
        double total = 0;
        for(Mamifero animal : animales){
            total += animal.TieneAlimentacion(tipoAlimentacion) ? animal.calcularCantidadDeComida() : 0;
        }
        return total;
    }
}

