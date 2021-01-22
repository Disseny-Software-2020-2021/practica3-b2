package model;

public class Episodi {
    private String idtemporada;
    private String idEpisodi;
    private int minutstotals;

    public int getMinutstotals() {
        return minutstotals;
    }

    public void setMinutstotals(int minutstotals) {
        this.minutstotals = minutstotals;
    }

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

    public Episodi(String idtemporada, String idEpisodi, int minutstotals) {
        this.idtemporada = idtemporada;
        this.idEpisodi = idEpisodi;
        this.minutstotals = minutstotals;
    }
}
