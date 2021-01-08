package resources.dao;

import model.ValoracioEstrella;

public interface DAOValoracioEstrella extends DAO<ValoracioEstrella>{
    boolean add(String usuari, ValoracioEstrella valoracioEstrella) throws Exception;
    boolean update(String usuari, ValoracioEstrella valoracioEstrella, int valoracio) throws Exception;
}
