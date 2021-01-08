package resources.service;

import resources.dao.*;

public interface AbstractFactoryData {
     DAOClient createDAOClient();
     DAOSerie createDAOSerie();
     DAOUsuari createDAOUsuari();
     DAOMyList createDAOMyList();
     DAOTemporada createDAOTemporada();
     DAOEpisodi createDAOEpisodi();
     DAOValoracioEstrella createDAOValoracioEstrella();
     DAOValoracioThumb createDAOValoracioThumb();
     DAOFollowing createDAOFollowing();
     DAOFollowers createDAOFollowers();
     DAOWatching createDAOWatching();
     DAOPost createDAOPost();
}
