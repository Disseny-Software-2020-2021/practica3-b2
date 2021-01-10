package model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String pwd;
    private String nom;
    private String idClient;
    private String adress;
    private boolean vip;

    private List<Usuari> usuaris;

    public Client(String nom, String pwd) {
        this.pwd = pwd;
        this.nom = nom;
        this.idClient = nom;
    }
    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getAdress() { return adress; }

    public void setAdress(String adress) { this.adress = adress; }

    public boolean isVip() { return vip; }

    public void setVip(boolean vip) { this.vip = vip; }

    public boolean findUserByNameBool(String nameUsuari)  {
        for(Usuari u: usuaris){
            if(u.getName().equals(nameUsuari)) return true;
        }
        return false;
    }
    public Usuari findUserByName(String nameUsuari)  {
        for(Usuari u: usuaris){
            if(u.getName().equals(nameUsuari)) return u;
        }
        return null;
    }

    public void setUsuaris(List<Usuari> llistaUsuaris) {
        usuaris = llistaUsuaris;
    }

    public List<Usuari> getUsuaris() {
        return this.usuaris;
    }

    public Usuari findUserById(String idUser)  {
        for(Usuari u: usuaris){
            if(u.getIdUser().equals(idUser)) return u;
        }
        return null;
    }

    public Usuari findUser(String nom)  {
        for(Usuari u: usuaris){
            if(u.getName().equals(nom)) return u;
        }
        return null;
    }

    public boolean findUserByIdBool(String idUser)  {
        for(Usuari u: usuaris){
            if(u.getIdUser().equals(idUser)) return true;
        }
        return false;
    }

    public static boolean isValidPassword(String psw){
        boolean majuscula = false;
        boolean numero = false;
        for(int i=0;i<psw.length();i++){
            if(Character.isDigit(psw.charAt(i))){
                numero = true;
            }
            if(Character.isUpperCase(psw.charAt(i))){
                majuscula = true;
            }
            if(majuscula && numero){
                return true;
            }
        }
        return false;
    }
    public Usuari addUsuari(String idClient, String Nomusuari, String idUsuari) throws Exception {
        if (this.usuaris == null){
            Usuari usuari = new Usuari(idClient, Nomusuari, idUsuari);
            usuari.setIdClient(idClient);
            usuari.setIdUser(idUsuari);
            usuari.setNom(Nomusuari);
            this.usuaris = new ArrayList<Usuari>();
            this.usuaris.add(usuari);
            return usuari;
        }
        else if (this.usuaris.size() < 5) {
            if (this.findUserByNameBool(idUsuari)){
                throw new Exception("Ja existeix aquest usuari");
            }
            Usuari usuari = new Usuari(idClient, Nomusuari, idUsuari);
            usuari.setIdClient(idClient);
            this.usuaris.add(usuari);
            usuari.setIdUser(idUsuari);
            usuari.setNom(Nomusuari);
            return usuari;
        } else {
            throw new Exception("Ja hi han cinc usuaris creats");
        }
    }
}
