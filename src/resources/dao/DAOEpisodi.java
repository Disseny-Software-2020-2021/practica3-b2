package resources.dao;

import model.Episodi;
import model.Temporada;

import java.util.ArrayList;


public interface DAOEpisodi extends DAO<Episodi> {
    ArrayList<Episodi> getEpisodisForTemporada(Temporada t);
}