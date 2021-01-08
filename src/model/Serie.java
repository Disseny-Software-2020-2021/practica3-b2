package model;
import java.util.List;

public class Serie {
    private String titol;
    private String descripcio;
    private List<Temporada> temporades;

    public Serie(String titol, String descripcio) {
        this.titol = titol;
        this.descripcio = descripcio;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public List<Temporada> getTemporades() {
        return temporades;
    }

    public void setTemporades(List<Temporada> temporades) {
        this.temporades = temporades;
    }
}
