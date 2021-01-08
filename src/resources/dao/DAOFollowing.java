package resources.dao;

import model.Usuari;

import java.util.List;

public interface DAOFollowing extends DAO<Usuari> {
    List<Usuari> getFollowingForUsuari(Usuari u);
}
