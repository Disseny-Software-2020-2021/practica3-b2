package model;

import java.util.List;

public class carteraSeries {
    private List<Serie> llista;

    public carteraSeries(List<Serie> allSeries) {
        llista = allSeries;
    }

        public Serie find(String idSerie) {

        for (Serie c: llista) {
            if (c.getTitol().equals(idSerie)) return c;
        }
        return null;

    }

    public List<Serie> getLlista() {
        return llista;
    }

    public void setLlista(List<Serie> llista) {
        this.llista = llista;
    }
}


