package resources.service;


import resources.dao.*;
import resources.dao.MOCK.*;

public class FactoryMOCK implements AbstractFactoryData {

    @Override
    public DAOClient createDAOClient() {
        return new DAOClientMOCK();
    }

    @Override
    public DAOSerie createDAOSerie() {
        return new DAOSerieMOCK();
    }

    @Override
    public DAOUsuari createDAOUsuari() { return new DAOUsuariMOCK(); }

    @Override
    public DAOMyList createDAOMyList() { return new DAOMyListMOCK(); }

    @Override
    public DAOTemporada createDAOTemporada() { return new DAOTemporadaMOCK(); }

    @Override
    public DAOEpisodi createDAOEpisodi() { return new DAOEpisodiMOCK(); }

    @Override
    public DAOValoracioEstrella createDAOValoracioEstrella() { return new DAOValoracioEstrellaMOCK(); }

    @Override
    public DAOValoracioThumb createDAOValoracioThumb() { return new DAOValoracioThumbMOCK(); }

    @Override
    public DAOFollowing createDAOFollowing() { return new DAOFollowingMOCK(); }

    @Override
    public DAOFollowers createDAOFollowers() { return new DAOFollowersMOCK(); }

    @Override
    public DAOWatching createDAOWatching() { return new DAOWatchingMOCK(); }

    @Override
    public DAOPost createDAOPost() { return new DAOPostMOCK(); }
    }


    // TO DO crear els altres DAOs de les altres classes
