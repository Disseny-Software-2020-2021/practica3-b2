package resources.dao;

import model.Usuari;
import model.Watching;

import java.util.List;

public interface DAOWatching extends DAO<Watching>{
    List<Watching> getWatchingForUsuari(Usuari u);
    boolean addToWatching(String usuari, String idSerie, String idTemporada, String idEpisodi, Watching watch);
    void updateToWatching(String usuari, String idSerie, String idTemporada, String idEpisodi, String numMinutes);
}
