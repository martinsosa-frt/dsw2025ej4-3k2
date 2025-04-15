package domain;

public class Pais {
    private final String nombre;
    private final String codigoIso;

    public Pais(String nombre, String codigoIso) {
        this.nombre = nombre;
        this.codigoIso = codigoIso;
    }


    public String getNombre() {
        return nombre;
    }


    public String getCodigoIso() {
        return codigoIso;
}

}
