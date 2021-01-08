package model;

public class Episodi {
    private String idtemporada;
    private String idEpisodi;

    public String getIdtemporada() {
        return idtemporada;
    }

    public void setIdtemporada(String idtemporada) {
        this.idtemporada = idtemporada;
    }

    public String getIdEpisodi() {
        return idEpisodi;
    }

    public void setIdEpisodi(String idEpisodi) {
        this.idEpisodi = idEpisodi;
    }

    public Episodi(String idtemporada, String idEpisodi) {
        this.idtemporada = idtemporada;
        this.idEpisodi = idEpisodi;
    }
}
