package model;

public class ValoracioEstrella {
    private String episodi;
    private int valoracio;

    public ValoracioEstrella(String episodi, int valoracio) {
        this.episodi = episodi;
        this.valoracio = valoracio;
    }

    public int getValoracio() { return valoracio; }

    public void setValoracio(int valoracio) { this.valoracio = valoracio; }

    public String getEpisodi() { return episodi; }

    public void setEpisodi(String episodi) { this.episodi = episodi; }
}
