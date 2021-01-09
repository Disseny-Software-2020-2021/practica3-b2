package model;

import java.util.List;

public class Usuari {
    private String idClient;
    private String idUser;    // Identificació única de l'usuari
    private String nom;
    private String icona;
    private List<Serie> mylist;
    private List<Watching> watching;
    private List<ValoracioEstrella> valoracionsEstrella;
    private List<ValoracioThumb> valoracioThumbs;
    private List<Usuari> followings;
    private List<Usuari> followers;

    public Usuari(String idClient, String nom, String idUsuari) {
        this.idClient = idClient;
        this.nom = nom;
        this.idUser = idUsuari;
        icona = "Icona";
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIcona() {
        return icona;
    }

    public void setIcona(String icona) {
        this.icona = icona;

    }

    public void setMyList(List<Serie> mylist) {
        this.mylist = mylist;

    }

    public List<Serie> getMyList() {
        return mylist;

    }

    public void setWatching(List<Watching> watching) {
        this.watching = watching;

    }

    public List<Watching> getWatching() {
        return watching;

    }

    public ValoracioEstrella valorarEstrella(String episodi, int i) throws Exception {
        if (i < 1 || i > 5) throw new Exception();
        ValoracioEstrella v = new ValoracioEstrella(episodi, i);
        return v;
    }

    public ValoracioThumb valorarThumb(String episodi, boolean i){
        ValoracioThumb v = new ValoracioThumb(episodi, i);
        return v;
    }

    public void setFollowings(List<Usuari> followings) {
        this.followings = followings;

    }

    public List<Usuari> getFollowings() {
        return followings;

    }

    public void setFollowers(List<Usuari> followers) {
        this.followers = followers;

    }

    public List<Usuari> getFollowers() {
        return followers;

    }
}
