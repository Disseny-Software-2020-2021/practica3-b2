package resources.dao;

import model.Usuari;

import java.util.List;

public interface DAOFollowers extends DAO<Usuari> {
    List<Usuari> getFollowersForUsuari(Usuari u);
}
