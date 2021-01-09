package resources.dao.MOCK;

import model.Episodi;
import model.Temporada;
import resources.dao.DAOEpisodi;

import java.util.*;

public class DAOEpisodiMOCK implements DAOEpisodi {

    private Map<String, ArrayList<Episodi>> idToEpisodis = new HashMap<>();

    public DAOEpisodiMOCK() {
        // Episodis per a la temporada 1 de la serie Breaking bad
        ArrayList<Episodi> episodis_1 = new ArrayList<>();
        episodis_1.add(new Episodi("Breaking Bad: temporada 1", "BB: temporada 1: episodi 1", 40));
        episodis_1.add(new Episodi("Breaking Bad: temporada 1", "BB: temporada 1: episodi 2", 30));
        idToEpisodis.put("Breaking Bad: temporada 1", episodis_1);

        // Episodis per a la temporada 2 de la serie Breaking bad
        ArrayList<Episodi> episodis_2 = new ArrayList<>();
        episodis_2.add(new Episodi("Breaking Bad: temporada 2", "BB: temporada 2: episodi 1", 40));
        episodis_2.add(new Episodi("Breaking Bad: temporada 2", "BB: temporada 2: episodi 2", 50));
        episodis_2.add(new Episodi("Breaking Bad: temporada 2", "BB: temporada 2: episodi 3", 60));
        idToEpisodis.put("Breaking Bad: temporada 2", episodis_2);

        // Episodis per a la temporada 1 de la serie Game of Thrones
        ArrayList<Episodi> episodis_3 = new ArrayList<>();
        episodis_3.add(new Episodi("Game of Thrones: temporada 1", "GoT: temporada 1: episodi 1",35));
        episodis_3.add(new Episodi("Game of Thrones: temporada 1", "GoT: temporada 1: episodi 2",40));
        idToEpisodis.put("Game of Thrones: temporada 1", episodis_3);

        // Episodis per a la temporada 2 de la serie Game of Thrones
        ArrayList<Episodi> episodis_4 = new ArrayList<>();
        episodis_4.add(new Episodi("Game of Thrones: temporada 2", "GoT: temporada 2: episodi 1", 66));
        episodis_4.add(new Episodi("Game of Thrones: temporada 2", "GoT: temporada 2: episodi 2", 42));
        episodis_4.add(new Episodi("Game of Thrones: temporada 2", "GoT: temporada 2: episodi 3", 35));
        idToEpisodis.put("Game of Thrones: temporada 2", episodis_4);

        // Episodis per a la temporada 3 de la serie Game of Thrones
        ArrayList<Episodi> episodis_5 = new ArrayList<>();
        episodis_5.add(new Episodi("Game of Thrones: temporada 3", "GoT: temporada 3: episodi 1",40));
        episodis_5.add(new Episodi("Game of Thrones: temporada 3", "GoT: temporada 3: episodi 2", 43));
        episodis_5.add(new Episodi("Game of Thrones: temporada 3", "GoT: temporada 3: episodi 3", 25));
        idToEpisodis.put("Game of Thrones: temporada 3", episodis_5);
    }


    @Override
    public Optional<Episodi> getById(String id) {
        for (Map.Entry<String, ArrayList<Episodi>> entry: idToEpisodis.entrySet()) {
            String key = entry.getKey();
            List<Episodi> values = entry.getValue();
            for (Episodi v:values) {
                if (v.getIdEpisodi().equals(id)){
                    return Optional.of(v);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Episodi> getAll() {
        List<Episodi> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Episodi>> entry: idToEpisodis.entrySet()) {
            String key = entry.getKey();
            List<Episodi> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(Episodi episodi) {
        if (idToEpisodis.containsKey(episodi.getIdtemporada())) {
            ArrayList<Episodi> llista = idToEpisodis.get(episodi.getIdtemporada());
            for (Episodi e:llista) {
                if (e.getIdEpisodi().equals (episodi.getIdEpisodi())) return false;
            }
            llista.add(episodi);
        }
        else {
            ArrayList<Episodi> episodis_6 = new ArrayList<>();
            episodis_6.add(episodi);
            idToEpisodis.put(episodi.getIdtemporada(), episodis_6);
        }
        return true;
    }

    @Override
    public boolean update(Episodi episodi, String[] params) {
        //No implementat ja que no s'utilitzarà
        return true;
    }

    @Override
    public boolean delete(Episodi episodi) throws Exception {
        throw new Exception();
    }

    @Override
    public ArrayList<Episodi> getEpisodisForTemporada(Temporada t) {
        if (t == null) {
            throw new ClassCastException();
        }
        if (idToEpisodis.containsKey(t.getIdTemporada())) {
            return (idToEpisodis.get(t.getIdTemporada()));
        }
        return null;
    }
}
