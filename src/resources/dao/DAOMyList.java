package resources.dao;

import model.Serie;
import model.Usuari;

import java.util.List;

public interface DAOMyList extends DAO<Serie> {
    List<Serie> getMyListForUsuari(Usuari u);
    String addToMyList(Serie s, Usuari u);
    String removeFromMyList(Serie s, Usuari u);
}
