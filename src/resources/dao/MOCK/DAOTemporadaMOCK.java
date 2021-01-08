package resources.dao.MOCK;

import model.Serie;
import model.Temporada;
import resources.dao.DAOTemporada;

import java.util.*;

public class DAOTemporadaMOCK implements DAOTemporada {

    private Map<String, ArrayList<Temporada>> idToTemporades = new HashMap<>();

    public DAOTemporadaMOCK() {
        // Temporades per a la serie Breaking bad
        ArrayList<Temporada> temporades_1 = new ArrayList<>();
        temporades_1.add(new Temporada("Breaking Bad", "Breaking Bad: temporada 1"));
        temporades_1.add(new Temporada("Breaking Bad", "Breaking Bad: temporada 2"));
        idToTemporades.put("Breaking Bad", temporades_1);

        // Temporades per a la serie Game of Thrones
        ArrayList<Temporada> temporades_2 = new ArrayList<>();
        temporades_2.add(new Temporada("Game of Thrones", "Game of Thrones: temporada 1"));
        temporades_2.add(new Temporada("Game of Thrones", "Game of Thrones: temporada 2"));
        temporades_2.add(new Temporada("Game of Thrones", "Game of Thrones: temporada 3"));
        idToTemporades.put("Breaking Bad", temporades_1);
    }

    @Override
    public Optional<Temporada> getById(String id){
        for (Map.Entry<String, ArrayList<Temporada>> entry: idToTemporades.entrySet()) {
            String key = entry.getKey();
            List<Temporada> values = entry.getValue();
            for (Temporada v:values) {
                if (v.getIdTemporada().equals(id)){
                    return Optional.of(v);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Temporada> getAll() {
        List<Temporada> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Temporada>> entry: idToTemporades.entrySet()) {
            String key = entry.getKey();
            List<Temporada> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(Temporada temporada) {
        if (idToTemporades.containsKey(temporada.getSerie())) {
            ArrayList<Temporada> llista = idToTemporades.get(temporada.getSerie());
            for (Temporada t:llista) {
                if (t.getIdTemporada().equals(temporada.getIdTemporada())) return false;
            }
            llista.add(temporada);
        }
        else {
            ArrayList<Temporada> temporades_3 = new ArrayList<>();
            temporades_3.add(temporada);
            idToTemporades.put(temporada.getSerie(), temporades_3);
        }
        return true;
    }

    @Override
    public boolean update(Temporada temporada, String[] params){
        //No implementat ja que no s'utilitzarà
        return true;
    }

    @Override
    public boolean delete(Temporada temporada) throws Exception {
        throw new Exception();
    }

    public ArrayList<Temporada> getTemporadesForSerie(Serie s) {
        if (s == null) {
            throw new ClassCastException();
        }
        if (idToTemporades.containsKey(s.getTitol())) {
            return (idToTemporades.get(s.getTitol()));
        }
        return null;
    }
}
