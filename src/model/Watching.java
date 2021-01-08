package model;

public class Watching {
    private String idSerie;
    private String idTemporada;
    private String idEpisodi;
    private String numMinuts;

    public Watching(String idSerie, String idTemporada, String idEpisodi, String numMinuts) {
        this.idSerie = idSerie;
        this.idTemporada = idTemporada;
        this.idEpisodi = idEpisodi;
        this.numMinuts = numMinuts;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public String getIdTemporada() {
        return idTemporada;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public void setIdTemporada(String idTemporada) {
        this.idTemporada = idTemporada;
    }

    public void setIdEpisodi(String idEpisodi) {
        this.idEpisodi = idEpisodi;
    }

    public void setNumMinuts(String numMinuts) {
        this.numMinuts = numMinuts;
    }

    public String getIdEpisodi() {
        return idEpisodi;
    }

    public String getNumMinuts() {
        return numMinuts;
    }



}
