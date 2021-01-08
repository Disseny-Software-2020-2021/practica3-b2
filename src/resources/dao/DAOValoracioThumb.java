package resources.dao;

import model.ValoracioThumb;

public interface DAOValoracioThumb extends DAO<ValoracioThumb>{
    boolean add(String usuari, ValoracioThumb valoracioThumb) throws Exception;
    boolean update(String usuari, ValoracioThumb valoracioThumb, boolean valoracio) throws Exception;
}
