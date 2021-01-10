package model;

import java.util.List;

public class Temporada {
    private String serie;
    private String idTemporada;
    private List<Episodi> episodis;
    private boolean acabat;

    public boolean isAcabat() {
        return acabat;
    }

    public void setAcabat(boolean acabat) {
        this.acabat = acabat;
    }

    public Temporada(String serie, String idTemporada) {
        this.serie = serie;
        this.idTemporada = idTemporada;

    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(String idTemporada) {
        this.idTemporada = idTemporada;
    }

    public void setEpisodis(List<Episodi> Episodis) {
        this.episodis = Episodis;
    }

    public List<Episodi> getEpisodis() {
        return episodis;
    }

    public Episodi find(String episodi) {

        for (Episodi e: episodis) {
            if (e.getIdEpisodi().equals(episodi)) return e;
        }
        return null;

    }

    public Episodi getEpisodi(int num) {
        return episodis.get(num);
    }
}

