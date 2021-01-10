package resources.dao.MOCK;

import model.Usuari;
import model.Watching;
import resources.dao.DAOWatching;

import java.util.*;

public class DAOWatchingMOCK implements DAOWatching {
    private Map<String, ArrayList<Watching>> watching = new HashMap<>();

    public DAOWatchingMOCK() {
        // Watching pel usuari "Pol"
        ArrayList<Watching> watching_1 = new ArrayList<>();
        watching_1.add(new Watching("Vikings", "Vikings: temporada 1", "V: temporada 1: episodi 1","Acabat"));
        watching_1.add(new Watching("Vikings", "Vikings: temporada 1", "V: temporada 1: episodi 2","Acabat"));
        watching_1.add(new Watching("Breaking Bad", "Breaking Bad: temporada 1", "BB: temporada 1: episodi 1","23"));
        watching.put("Pol", watching_1);

        // Watching pel usuari "Manuel"
        ArrayList<Watching> watching_2 = new ArrayList<>();
        watching.put("Manuel", watching_2);

        // Watching pel usuari "Marc"
        ArrayList<Watching> watching_3 = new ArrayList<>();
        watching_3.add(new Watching("Breaking Bad", "Breaking Bad: temporada 1", "BB: temporada 1: episodi 1","Acabat"));
        watching_3.add(new Watching("Breaking Bad", "Breaking Bad: temporada 1", "BB: temporada 1: episodi 2","20"));
        watching_3.add(new Watching("Game of Thrones", "Game of Thrones: temporada 1", "GoT: temporada 1: episodi 1","Acabat"));
        watching_3.add(new Watching("Game of Thrones", "Game of Thrones: temporada 1", "GoT: temporada 1: episodi 2","Acabat"));
        watching.put("Marc", watching_3);

        // Watching pel usuari "Laura"
        ArrayList<Watching> watching_4 = new ArrayList<>();
        watching.put("Laura", watching_4);

        // Watching pel usuari "Marco"
        ArrayList<Watching> watching_5 = new ArrayList<>();
        watching_4.add(new Watching("Breaking Bad", "Breaking Bad: temporada 1", "BB: temporada 1: episodi 1","Acabat"));
        watching_4.add(new Watching("Breaking Bad", "Breaking Bad: temporada 1", "BB: temporada 1: episodi 2","20"));
        watching_4.add(new Watching("Game of Thrones", "Game of Thrones: temporada 1", "GoT: temporada 1: episodi 1","Acabat"));
        watching_4.add(new Watching("Game of Thrones", "Game of Thrones: temporada 1", "GoT: temporada 1: episodi 2","Acabat"));
        watching.put("Marco", watching_5);

        // Watching pel usuari "Ignasi"
        ArrayList<Watching> watching_6 = new ArrayList<>();
        watching_6.add(new Watching("Vikings", "Vikings: temporada 1", "V: temporada 1: episodi 1","Acabat"));
        watching_6.add(new Watching("Vikings", "Vikings: temporada 1", "V: temporada 1: episodi 2","Acabat"));
        watching_6.add(new Watching("Breaking Bad", "Breaking Bad: temporada 1", "BB: temporada 1: episodi 1","25"));
        watching.put("Ignasi", watching_6);
    }

    @Override
    public Optional<Watching> getById(String id) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Watching> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean add(Watching watching) throws Exception {
        return false;
    }

    @Override
    public boolean update(Watching watching, String[] params) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Watching watching) throws Exception {
        return false;
    }

    public List<Watching> getWatchingForUsuari(Usuari u) {
        if (u == null) {
            throw new ClassCastException();
        }
        if (watching.containsKey(u.getName())) {
            return (watching.get(u.getName()));
        }
        return null;
    }

    public boolean addToWatching(String usuari, String idSerie, String idTemporada, String idEpisodi, Watching watch) {
        if (this.watching.containsKey(usuari)) {
            ArrayList<Watching> llista = watching.get(usuari);
            for (Watching w:llista) {
                if (w.getIdSerie().equals (watch.getIdSerie())) return false;
            }
            llista.add(watch);
        }
        else {
            ArrayList<Watching> watchings = new ArrayList<>();
            watchings.add(watch);
            watching.put(usuari, watchings);
        }
        return true;
    }

    public void updateToWatching(String usuari, String idSerie, String idTemporada, String idEpisodi, String numMinutes) {
        if (watching.containsKey(usuari)) {
            ArrayList<Watching> llista = watching.get(usuari);
            for (Watching w: llista) {
                if (w.getIdSerie().equals(idSerie)) {
                    if(w.getIdTemporada().equals(idTemporada)){
                        if(w.getIdEpisodi().equals(idEpisodi)){
                            w.setNumMinuts(numMinutes);
                        }
                    }
                }
            }
        }
    }
}