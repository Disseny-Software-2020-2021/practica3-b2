package resources.dao;

import model.Client;
import model.Usuari;

import java.util.List;

public interface DAOUsuari extends DAO<Usuari> {
    List<Usuari> getUsuarisForClient(Client c);
}
