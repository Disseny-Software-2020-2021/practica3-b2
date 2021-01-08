package resources.dao;

import model.Serie;
import model.Temporada;

import java.util.ArrayList;


public interface DAOTemporada extends DAO<Temporada> {
    ArrayList<Temporada> getTemporadesForSerie(Serie s);
}