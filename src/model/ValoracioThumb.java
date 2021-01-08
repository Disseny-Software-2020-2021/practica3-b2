package model;

public class ValoracioThumb {
    private String episodi;
    private boolean valoracio;

    public ValoracioThumb(String episodi, boolean valoracio) {
        this.episodi = episodi;
        this.valoracio = valoracio;
    }

    public boolean getValoracio() { return valoracio; }

    public void setValoracio(boolean valoracio) { this.valoracio = valoracio; }

    public String getEpisodi() { return episodi; }

    public void setEpisodi(String episodi) { this.episodi = episodi; }
}
