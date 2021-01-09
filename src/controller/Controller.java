package controller;

import model.*;
import resources.service.AbstractFactoryData;
import resources.service.DataService;
import resources.service.FactoryMOCK;

import java.util.*;

public class Controller {

    private static Controller instancia;
    private AbstractFactoryData factory;      // Origen de les dades
    private DataService dataService;         // Connexio amb les dades
    private CarteraClients carteraClients;   // Model
    private carteraSeries llistaSeries;
    private List<Post> llistaPosts;
    private List<ValoracioEstrella> valoracionsEstrella;
    private List<ValoracioThumb> valoracionsThumb;

    private Controller() {
        factory = new FactoryMOCK();
        dataService = new DataService(factory);
        try {
            llistaPosts = dataService.getAllPosts();
            iniSetUp();
            iniSeries();
            valoracionsEstrella = dataService.getAllValoracionsEstrella();
            valoracionsThumb = dataService.getAllValoracionsThumb();
        } catch (Exception e) {
            // TO DO
        }
    }

    public static Controller getInstance(){
        if (instancia == null) {
            instancia = new Controller();
        }
        return instancia;
    }

    public void iniSetUp() throws Exception {
        List<Client> l = dataService.getAllClients();
        if (l != null) {
            carteraClients = new CarteraClients(l);
            for (Client c : l) {
                List<Usuari> llistaUsuaris = dataService.getUsuarisByClient(c.getName());
                c.setUsuaris(llistaUsuaris);
                for (Usuari u : llistaUsuaris) {
                    List<Serie> MyList = dataService.getMyListForUsuari(u);
                    List<Watching> watching = dataService.getWatchingForUsuari(u);
                    List<Usuari> followings = dataService.getFollowingForUsuari(u);
                    List<Usuari> followers = dataService.getFollowersForUsuari(u);
                    u.setMyList(MyList);
                    u.setWatching(watching);
                    u.setFollowings(followings);
                    u.setFollowers(followers);
                }
            }
        }
    }

    public void iniSeries() throws Exception{
        List<Serie> s = dataService.getAllSeries();
        if(s != null){
            llistaSeries = new carteraSeries(s);
            for(Serie se: s){
                List<Temporada> llistaTemporades = dataService.getTemporadesBySerie(se.getTitol());
                se.setTemporades(llistaTemporades);
                for(Temporada t: llistaTemporades){
                    List<Episodi> llistaEpisodis = dataService.getEpisodisbyTemporada(t.getIdTemporada());
                    t.setEpisodis(llistaEpisodis);
                }

            }
        }
    }

    public String isValidNameClient(String username) {
        Client client = carteraClients.find(username);
        if (client != null) return "Valid Client";
        else return "Client Unknown";
    }

    public boolean validateClient(String username, String password) {
        return dataService.getClientByUsuariAndPassword(username, password) != null;
    }

    public boolean validateLoginUser(String clientName, String userName) {
        return carteraClients.validateLoginUser(clientName, userName);
    }

    public Iterable<String> llistarCatalegSeries() {
        SortedSet<String> titols = new TreeSet<>();
        if (llistaSeries.getLlista().isEmpty()) {
            titols.add("No hi ha series disponibles");
        } else {
            for (Serie s : llistaSeries.getLlista()) {
                titols.add(s.getTitol());
            }
        }
        return titols;
    }

    public Iterable<String> llistarPost() {
        SortedSet<String> post = new TreeSet<>();
        if (llistaPosts.isEmpty()) {
            post.add("No hi ha series disponibles");
        } else {
            for (Post p : llistaPosts) {
                post.add(p.getPost());
            }
        }
        return post;
    }

    public List<String> catalegSeries() {
        List<String>llista = new ArrayList<>();
        for (String ec : this.llistarCatalegSeries()) {
            llista.add(ec);
        }
        return llista;


    }

    public String catalegPosts(String nom) {
        for (String ec : this.llistarPost()) {
            if (ec.equals(nom)) {
                return "succes";
            }
        }
        return "fail";


    }


    public void addClient(String idClient, String psw, String dni, String adress, boolean vip) throws Exception {
        if (idClient.equals("")  || psw.equals("")  || dni.equals("")  || adress.equals("")){
            throw new Exception("ERROR: Omplena les dades");
        }
        if ((carteraClients.find(idClient) == null) && (Client.isValidPassword(psw)) && (carteraClients.find_id(dni) == null)) {
            carteraClients.addClient(idClient, psw, dni, adress, vip);
            //List<Usuari> listausuaris = Collections.emptyList();
            //carteraClients.find(idClient).setUsuaris(listausuaris);
            this.dataService.addClient(carteraClients.find(idClient));
        } else {
            throw new Exception("Error");
        }
    }

    public void addUsuari(String idClient, String nom, String idusuari) throws Exception {
        Usuari u = this.carteraClients.find(idClient).addUsuari(idClient, nom, idusuari);
        this.dataService.addUsuari(u);
    }

    public boolean isValidPassword(String psw) {
        return Client.isValidPassword(psw);
    }

    public boolean isTakenUsername(String username) {
        return this.carteraClients.find(username) == null;
    }


    public boolean isValidDni(String dni) {
        if ((dni.length() < 9) || (!Character.isAlphabetic(dni.charAt(dni.length() - 1)))) {
            return false;
        }
        return this.carteraClients.find_id(dni) == null;
    }

    public String getUsername(String idClient, String idUser) {
        return this.carteraClients.find(idClient).findUserById(idUser).getName();
    }

    public String getIcona(String idClient, String idUser) {
        return this.carteraClients.find(idClient).findUserById(idUser).getIcona();
    }

    public boolean setProfile(String icona, String idClient, String idUser, String username) {
        try {
            List<Usuari> client = this.carteraClients.find(idClient).getUsuaris();
            Usuari usuari = this.carteraClients.find(idClient).findUserById(idUser);
            for (int i = 0; i < client.size(); i++) {
                if (client.get(i).getName().equals(username) && !client.get(i).getIdUser().equals(idUser)) {
                    return false;
                }
            }
            usuari.setIcona(icona);
            usuari.setName(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFromMyList(String idClient, String idUsuari, String idSerie) {
        if (carteraClients.find(idClient) != null) {
            Usuari usuari = carteraClients.find(idClient).findUserById(idUsuari);
            if (usuari != null) {
                List<Serie> mylist = usuari.getMyList();
                for (Serie s : mylist) {
                    if (s.getTitol().equals(idSerie)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String markInMyList(String idClient, String idUsuari, String idSerie) {
        if (carteraClients.find(idClient) != null) {
            Usuari usuari = carteraClients.find(idClient).findUserById(idUsuari);
            if (usuari != null) {
                for (Serie s : llistaSeries.getLlista()) {
                    if (s.getTitol().equals(idSerie)) {
                        List<Serie> mylist = usuari.getMyList();
                        for (Serie my : mylist) {
                            if (my.getTitol().equals(idSerie)) {
                                return "Ja estava marcada";
                            }
                        }
                        mylist.add(s);
                        usuari.setMyList(mylist);
                        return "Marcada";
                    }
                }
                return "Serie no existent";
            }
            return "Id usuari no correcte";
        }
        return "Client no correcte";
    }

    public String unmarkInMyList(String idClient, String idUsuari, String idSerie) {
        if (carteraClients.find(idClient) != null) {
            Usuari usuari = carteraClients.find(idClient).findUserById(idUsuari);
            if (usuari != null) {
                for (Serie s : llistaSeries.getLlista()) {
                    if (s.getTitol().equals(idSerie)) {
                        List<Serie> mylist = usuari.getMyList();
                        for (int x = 0; x < mylist.size(); x++) {
                            if (mylist.get(x).getTitol().equals(idSerie)) {
                                mylist.remove(x);
                                usuari.setMyList(mylist);
                                return "Desmarcada";
                            }
                            return "No esta marcada";
                        }
                        return "No s'ha trobat la serie";
                    }
                }
                return "Serie no existent";
            }
            return "Id usuari no correcte";
        }
        return "Client no correcte";
    }

    public String mostrarDetallsSerie(String nomSerie){
        for (Serie serie : llistaSeries.getLlista()) {
            if (serie.getTitol().equals(nomSerie)) {
                return serie.getDescripcio();
            }
        }
        return "Error";
        //throw new Exception("Error");
    }

    public String llistarFollowing(String idClient, String idUsuari, String idUser) {
        if (carteraClients.find(idClient) != null) {
            Usuari usuari = carteraClients.find(idClient).findUserById(idUsuari);
            if (usuari != null) {
                List<Usuari> followings = usuari.getFollowings();
                for (Usuari u : followings) {
                    if (u.getIdUser().equals(idUser)) {
                        return "Success";
                    }
                }
            }
        }
        return "Fail";
    }

    public boolean veureEpisodi(String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi) throws Exception {
        List<Usuari> usuaris = carteraClients.find(idClient).getUsuaris();
        List<Serie> series = llistaSeries.getLlista();
        List<Temporada> temporada = null;
        List<Episodi> episodis = null;
        Usuari usuari = null;
        boolean us = false;
        boolean se = false;
        boolean te = false;
        boolean ep = false;
        for(Usuari u: usuaris){
            if(u.getName().equals(idUsuari)){
                us = true;
                usuari = u;
            }
        }
        for(Serie s: series){
            if(s.getTitol().equals(idSerie)){
                se = true;
                temporada = s.getTemporades();
            }

        }
        if(temporada != null) {
            for (Temporada t : temporada) {
                if (t.getIdTemporada().equals(idTemporada)) {
                    te = true;
                    episodis = t.getEpisodis();
                }
            }if(episodis != null){
                for (Episodi e : episodis) {
                    if (e.getIdEpisodi().equals(idEpisodi)) {
                        ep = true;

                    }
                }
            }
        }
        if(us & se & te & ep){
            this.dataService.startWatching(idClient,idUsuari,idSerie,idTemporada,idEpisodi);
            return true;
        }else{
            return false;
        }
    }

    public String llistarFollowers(String idClient, String idUsuari, String idUser) {
        if (carteraClients.find(idClient) != null) {
            Usuari usuari = carteraClients.find(idClient).findUserById(idUsuari);
            if (usuari != null) {
                List<Usuari> followers = usuari.getFollowers();
                for (Usuari u : followers) {
                    if (u.getIdUser().equals(idUser)) {
                        return "Success";
                    }
                }
            }
        }
        return "Fail";
    }

    public int getValoracioEstrella(String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi) throws Exception {
        ValoracioEstrella valoracioEstrella = this.dataService.getValoracioEstrella(idClient, idUsuari, idSerie, idTemporada, idEpisodi);
        if (valoracioEstrella == null) {
            throw new Exception();
        }
        return valoracioEstrella.getValoracio();
    }

    public boolean getValoracioThumb(String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi) throws Exception {
        ValoracioThumb valoracioThumb = this.dataService.getValoracioThumb(idClient, idUsuari, idSerie, idTemporada, idEpisodi);
        if (valoracioThumb == null) {
            throw new Exception();
        }
        return valoracioThumb.getValoracio();
    }

    public void setValoracioEstrella(String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi, int valoracio) throws Exception {
        try {
            if (carteraClients.find(idClient) != null) {
                Usuari usuari = carteraClients.find(idClient).findUserByName(idUsuari);
                if (usuari != null) {
                    ValoracioEstrella v = usuari.valorarEstrella(idEpisodi, valoracio);
                    this.dataService.setValoracioEstrella(idClient, idUsuari, idSerie, idTemporada, idEpisodi, v);
                    valoracionsEstrella.add(v);
                } else throw new Exception();
            } else throw new Exception();
        } catch (Exception e) {
            throw e;
        }
    }

    public void setValoracioThumb(String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi, boolean valoracio) throws Exception {
        try {
            if (carteraClients.find(idClient) != null) {
                Usuari usuari = carteraClients.find(idClient).findUserByName(idUsuari);
                if (usuari != null) {
                    ValoracioThumb v = usuari.valorarThumb(idEpisodi, valoracio);
                    this.dataService.setValoracioThumb(idClient, idUsuari, idSerie, idTemporada, idEpisodi, v);
                    valoracionsThumb.add(v);
                } else throw new Exception();
            } else throw new Exception();
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean veureLlistaWatching(String idClient, String idUsuari, String idSerie) {
        if (carteraClients.find(idClient) != null) {
            Usuari usuari = carteraClients.find(idClient).findUserById(idUsuari);
            if (usuari != null) {
                List<Watching> watching = usuari.getWatching();
                for (Watching w : watching) {
                    if (w.getIdSerie().equals(idSerie)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean veureLlistaMyList(String idClient, String idUsuari, String idSerie) {
        if (carteraClients.find(idClient) != null) {
            Usuari usuari = carteraClients.find(idClient).findUserById(idUsuari);
            if (usuari != null) {
                List<Serie> mylist = usuari.getMyList();
                for (Serie s : mylist) {
                    if (s.getTitol().equals(idSerie)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean LoginClient(String idClient, String psw){
        try {
            return carteraClients.validLogin(idClient, psw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getMyList(String idClient, String nom) {
        List<String> llista = new ArrayList<>();
        List<Serie> mylist = carteraClients.find(idClient).findUser(nom).getMyList();
        for(Serie s: mylist){
            llista.add(s.getTitol());
        }
        return llista;

    }

    public List<Usuari> getUsuaris(String idClient) {
        return carteraClients.find(idClient).getUsuaris();

    }

    public List<String> getTemporades(String idSerie) {
        List<String> llista = new ArrayList<>();
        List<Temporada> temporades = llistaSeries.find(idSerie).getTemporades();
        for(Temporada t: temporades){
            llista.add(t.getIdTemporada());
        }
        return llista;
    }

    public List<String> getEpisodis(String idSerie, String temporada) {
        List<String> llista = new ArrayList<>();
        List<Temporada> temporades = llistaSeries.find(idSerie).getTemporades();
        for(Temporada t: temporades){
            if(t.getIdTemporada().equals(temporada)){
                List<Episodi> episodis = t.getEpisodis();
                for(Episodi e: episodis){
                    llista.add(e.getIdEpisodi());
                }
            }
        }
        return llista;
    }

    public int getMinTotals(String idSerie, String temporada, String episodi){
        List<Temporada> temporades = llistaSeries.find(idSerie).getTemporades();
        for(Temporada t: temporades){
            if(t.getIdTemporada().equals(temporada)){
                List<Episodi> episodis = t.getEpisodis();
                for(Episodi e: episodis){
                    if(e.getIdEpisodi().equals(episodi)){
                        return e.getMinutstotals();
                    }
                }
            }
        }
        return 0;
    }
}
