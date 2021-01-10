package resources.dao.MOCK;

import model.Serie;
import resources.dao.DAOSerie;

import java.util.*;

public class DAOSerieMOCK implements DAOSerie {

    private Map<String, Serie> idToSerie = new HashMap<>();

    public DAOSerieMOCK() {
        idToSerie.put("Game of Thrones", new Serie( "Game of Thrones", "La historia se desarrolla...",60));
        idToSerie.put("Vikings", new Serie( "Vikings", "Está inspirada en las sagas del vikingo Ragnar Lodbrok, uno de los héroes nórdicos legendarios...",40));
        idToSerie.put("Breaking Bad", new Serie("Breaking Bad", "Tras cumplir 50 años, Walter White...",70));

    }


    @Override
    public List<Serie> getAll() {
        return new ArrayList<>(idToSerie.values());
    }

    @Override
    public Optional<Serie> getById(String id) {
        return Optional.ofNullable(idToSerie.get(id));
    }

    @Override
    public boolean add(final Serie serie) {

        if (getById(serie.getTitol()).isPresent()) {
            return false;
        }
        idToSerie.put(serie.getTitol(), serie);
        return true;
    }

    @Override
    public boolean update(final Serie serie, String[] params) {
        serie.setTitol(Objects.requireNonNull(
                params[0], "Title cannot be null"));
        serie.setDescripcio(Objects.requireNonNull(
                params[1], "Description cannot be null"));
        return idToSerie.replace(serie.getTitol(), serie) != null;
    }

    @Override
    public boolean delete(final Serie serie) {
        return idToSerie.remove(serie.getTitol()) != null;
    }
}
