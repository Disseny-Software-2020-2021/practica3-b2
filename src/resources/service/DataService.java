package resources.service;

import model.*;
import resources.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataService {
    private DAOSerie serieDAO;
    private DAOClient clientDAO;
    private DAOUsuari usuariDAO;
    private DAOMyList mylistDAO;
    private DAOEpisodi EpisodiDAO;
    private DAOTemporada TemporadaDAO;
    private DAOValoracioEstrella ValoracioEstrellaDAO;
    private DAOValoracioThumb ValoracioThumbDAO;
    private DAOFollowing followingDAO;
    private DAOFollowers followersDAO;
    private DAOWatching watchingDAO;
    private DAOPost postDAO;

    public DataService(AbstractFactoryData factory) {
        this.serieDAO = factory.createDAOSerie();
        this.clientDAO = factory.createDAOClient();
        this.usuariDAO = factory.createDAOUsuari();
        this.mylistDAO = factory.createDAOMyList();
        this.EpisodiDAO = factory.createDAOEpisodi();
        this.TemporadaDAO = factory.createDAOTemporada();
        this.ValoracioEstrellaDAO = factory.createDAOValoracioEstrella();
        this.ValoracioThumbDAO = factory.createDAOValoracioThumb();
        this.followingDAO = factory.createDAOFollowing();
        this.followersDAO = factory.createDAOFollowers();
        this.watchingDAO = factory.createDAOWatching();
        this.postDAO = factory.createDAOPost();
        // TO DO: Crear els altres DAO de les altres estructures
    }

    public Client getClientByUsuariAndPassword(String usuari, String password) {
        try {
            return clientDAO.findClientByUserNameAndPassword(usuari, password);
        } catch (Exception e) {
            //TODO
        }
        return null;
    }

    public List<Client> getAllClients() throws Exception {
        return clientDAO.getAll();
    }

    public List<Usuari> getAllUsuaris() throws Exception {
        return usuariDAO.getAll();
    }

    public List<Usuari> getUsuarisByClient(String nomClient) throws Exception {
        Optional<Client> c = clientDAO.getById(nomClient);
        if (c.isPresent()) return (usuariDAO.getUsuarisForClient(c.get()));
        else return null;
    }

    public List<Temporada> getTemporadesBySerie(String Nomserie) throws Exception {
        Optional<Serie> c = serieDAO.getById(Nomserie);
        if (c.isPresent()) return (TemporadaDAO.getTemporadesForSerie(c.get()));
        else return null;
    }
    public List<Episodi> getEpisodisbyTemporada(String Nomtemporada) throws Exception {
        Optional<Temporada> c = TemporadaDAO.getById(Nomtemporada);
        if (c.isPresent()) return (EpisodiDAO.getEpisodisForTemporada(c.get()));
        else return null;
    }

    public List<Serie> getAllSeries() throws Exception {
        return serieDAO.getAll();
    }

    public List<Post> getAllPosts() throws Exception {
        return postDAO.getAll();
    }

    public void addUsuari(Usuari u) throws Exception {
        if(!usuariDAO.add(u)){
            throw new Exception("aquest usuari ja existeix");
        }
    }

    public boolean isTakenUsernameU(String username) throws Exception {
        List clients = this.getAllClients();
        for(int i = 0; i< clients.size(); i++){
            Client client = (Client) clients.get(i);
            List<Usuari> usuaris = this.getUsuarisByClient(client.toString());
            for(int j = 0; j < usuaris.size(); j++){
                Usuari usuari = (Usuari) usuaris.get(j);
                if(usuari.getName().equals(username)){
                    return false;
                }
            }
        }return true;
    }

    public boolean isTakenUsernameUsername(String idClient, String username) throws Exception {
        List clients = this.getAllClients();
        for(int i = 0; i< clients.size(); i++){
            Client client = (Client) clients.get(i);
            List usuaris = this.getUsuarisByClient(idClient);
            for(int j = 0; j < usuaris.size(); j++){
                Usuari usuari = (Usuari) usuaris.get(j);
                if(usuari.getName().equals(username)){
                    return false;
                }
            }
        }return true;
    }

    public List<Serie> getMyListForUsuari (Usuari usuari){
        return mylistDAO.getMyListForUsuari(usuari);
    }

    public String markInMyList (Serie serie, Usuari usuari){
        return this.mylistDAO.addToMyList(serie, usuari);
    }

    public String unmarkInMyList (Serie serie, Usuari usuari){
        return this.mylistDAO.removeFromMyList(serie, usuari);
    }

    public ArrayList<Temporada> getAllTemporades(String idSerie) throws Exception {
        Optional<Serie> serie = this.serieDAO.getById(idSerie);
        if (serie.isPresent()) return (this.TemporadaDAO.getTemporadesForSerie(serie.get()));
        else return null;
    }
    public List<Episodi> getAllEpisodis(String idSerie) throws Exception {
        ArrayList<Temporada> temporades = this.getAllTemporades(idSerie);
        ArrayList<Episodi> episodis = new ArrayList<Episodi>();
        if (temporades == null){return null;}
        for (Temporada t:temporades){
            ArrayList<Episodi> temp = this.EpisodiDAO.getEpisodisForTemporada(t);
            for (Episodi e:temp){
                episodis.add(e);
            }
        }
        return episodis;
    }

    public ValoracioEstrella getValoracioEstrella (String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi) throws Exception {
        Serie serie = null;
        Temporada temporada = null;
        List<Usuari> usuaris = this.getUsuarisByClient(idClient);
        boolean flag = false;
        for(Usuari u: usuaris){
            if(u.getName().equals(idUsuari)){flag=true;}
        }
        if(!flag){ return null; }
        flag = false;
        List<Serie> series = this.getAllSeries();
        for (Serie s: series){
            if(s.getTitol().equals(idSerie)){ serie = s; }
        }
        if(serie == null){return null;}
        List<Temporada> temporades = this.TemporadaDAO.getTemporadesForSerie(serie);
        for(Temporada t: temporades){
            if(t.getIdTemporada().equals(idTemporada)){ temporada = t;}
        }
        if(temporada == null){return null;}
        List<Episodi> episodis = this.EpisodiDAO.getEpisodisForTemporada(temporada);
        for (Episodi e: episodis){
            if (e.getIdEpisodi().equals(idEpisodi)){ flag = true; }
        }
        if(!flag){ return null; }
        Optional<ValoracioEstrella> valoracioEstrella = this.ValoracioEstrellaDAO.getById(idEpisodi);
        if (valoracioEstrella.isPresent()) return valoracioEstrella.get();
        else return null;
    }

    public void setValoracioEstrella( String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi, ValoracioEstrella v/*, Date data*/) throws Exception {
        Serie serie = null;
        Temporada temporada = null;
        List<Usuari> usuaris = this.getUsuarisByClient(idClient);
        boolean flag = false;
        for(Usuari u: usuaris){
            if(u.getName().equals(idUsuari)){flag=true;}
        }
        if(!flag){ throw new Exception(); }
        flag = false;
        List<Serie> series = this.getAllSeries();
        for (Serie s: series){
            if(s.getTitol().equals(idSerie)){ serie = s; }
        }
        if(serie == null){ throw new Exception(); }
        List<Temporada> temporades = this.TemporadaDAO.getTemporadesForSerie(serie);
        for(Temporada t: temporades){
            if(t.getIdTemporada().equals(idTemporada)){ temporada = t;}
        }
        if(temporada == null){ throw new Exception(); }
        List<Episodi> episodis = this.EpisodiDAO.getEpisodisForTemporada(temporada);
        for (Episodi e: episodis){
            if (e.getIdEpisodi().equals(idEpisodi)){
                flag = true;
            }
        }
        if(!flag){ throw new Exception(); }
        if (!this.ValoracioEstrellaDAO.add(idUsuari, v)){
            flag = this.ValoracioEstrellaDAO.update(idUsuari, v, v.getValoracio());
        }
    }

    public ValoracioThumb getValoracioThumb (String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi) throws Exception {
        Serie serie = null;
        Temporada temporada = null;
        List<Usuari> usuaris = this.getUsuarisByClient(idClient);
        boolean flag = false;
        for(Usuari u: usuaris){
            if(u.getName().equals(idUsuari)){flag=true;}
        }
        if(!flag){ return null; }
        flag = false;
        List<Serie> series = this.getAllSeries();
        for (Serie s: series){
            if(s.getTitol().equals(idSerie)){ serie = s; }
        }
        if(serie == null){return null;}
        List<Temporada> temporades = this.TemporadaDAO.getTemporadesForSerie(serie);
        for(Temporada t: temporades){
            if(t.getIdTemporada().equals(idTemporada)){ temporada = t;}
        }
        if(temporada == null){return null;}
        List<Episodi> episodis = this.EpisodiDAO.getEpisodisForTemporada(temporada);
        for (Episodi e: episodis){
            if (e.getIdEpisodi().equals(idEpisodi)){ flag = true; }
        }
        if(!flag){ return null; }
        Optional<ValoracioThumb> valoracioThumb = this.ValoracioThumbDAO.getById(idEpisodi);
        if (valoracioThumb.isPresent()) return valoracioThumb.get();
        else return null;
    }

    public void setValoracioThumb (String idClient, String idUsuari, String idSerie, String idTemporada, String idEpisodi, ValoracioThumb v/*, Date data*/) throws Exception {
        Serie serie = null;
        Temporada temporada = null;
        List<Usuari> usuaris = this.getUsuarisByClient(idClient);
        boolean flag = false;
        for(Usuari u: usuaris){
            if(u.getName().equals(idUsuari)){flag=true;}
        }
        if(!flag){ throw new Exception(); }
        flag = false;
        List<Serie> series = this.getAllSeries();
        for (Serie s: series){
            if(s.getTitol().equals(idSerie)){ serie = s; }
        }
        if(serie == null){ throw new Exception(); }
        List<Temporada> temporades = this.TemporadaDAO.getTemporadesForSerie(serie);
        for(Temporada t: temporades){
            if(t.getIdTemporada().equals(idTemporada)){ temporada = t;}
        }
        if(temporada == null){ throw new Exception(); }
        List<Episodi> episodis = this.EpisodiDAO.getEpisodisForTemporada(temporada);
        for (Episodi e: episodis){
            if (e.getIdEpisodi().equals(idEpisodi)){ flag = true; }
        }
        if(!flag){ throw new Exception(); }
        if (!this.ValoracioThumbDAO.add(idUsuari, v)){
            flag = this.ValoracioThumbDAO.update(idUsuari, v, v.getValoracio());
        }
    }

    public List<Usuari> getFollowingForUsuari(Usuari u){
        return this.followingDAO.getFollowingForUsuari(u);
    }

    public void startWatching (String idClient, String idUsuari,  String idSerie, String idTemporada, String idEpisodi /*Date data*/) throws Exception {
        Watching watching = new Watching(idSerie, idTemporada, idEpisodi, "0");
        this.watchingDAO.addToWatching(idUsuari, idSerie, idTemporada, idEpisodi, watching);

    }
    public List<Usuari> getFollowersForUsuari(Usuari u){
        return this.followersDAO.getFollowersForUsuari(u);
    }

    public void pauseWatching(String idClient, String idUsuari,  String idSerie, String idTemporada, String idEpisodi, String numMinutes) throws Exception {
        this.watchingDAO.updateToWatching(idClient, idUsuari,idTemporada, idEpisodi, numMinutes);
    }

    public void finishWatching(String idClient, String idUsuari,  String idSerie, String idTemporada, String idEpisodi/*, Date data*/){
        this.watchingDAO.updateToWatching(idClient, idUsuari,idTemporada, idEpisodi, "Acabat");
    }

    public List<Watching> getWatchingForUsuari(Usuari u){
        return this.watchingDAO.getWatchingForUsuari(u);
    }

    public List<ValoracioEstrella> getAllValoracionsEstrella() throws Exception {
        return ValoracioEstrellaDAO.getAll();
    }

    public List<ValoracioThumb> getAllValoracionsThumb() throws Exception {
        return ValoracioThumbDAO.getAll();
    }


        /*  TO DO
    CAPÇALERES de les FUNCIONS que cal implementar en la pràctica 2 com a DataServices que consultaran dels DAOs corresponents
    les dades que es volen extreure

    public List<Artista> getAllArtistes( String idSerie)

    public List<Productora> getAllProductores (String idSerie)

    public List<Tematica> getAllTematiques (String idSerie)

    public List<Director> getAllDirectors (String idSerie)
*/

}