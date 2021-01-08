package resources.dao.MOCK;

import model.ValoracioEstrella;
import resources.dao.DAOValoracioEstrella;

import java.util.*;

public class DAOValoracioEstrellaMOCK implements DAOValoracioEstrella{
    private Map<String, ArrayList<ValoracioEstrella>> listValoracions = new HashMap<>();
    public DAOValoracioEstrellaMOCK() {
        // Valoracions per a l'usuari Pol
        ArrayList<ValoracioEstrella> valoracions_1 = new ArrayList<>();
        valoracions_1.add(new ValoracioEstrella("BB: temporada 1: episodi 1", 5));
        valoracions_1.add(new ValoracioEstrella( "BB: temporada 2: episodi 2", 4));
        valoracions_1.add(new ValoracioEstrella( "GoT: temporada 1: episodi 2", 3));
        valoracions_1.add(new ValoracioEstrella( "GoT: temporada 3: episodi 1", 2));
        listValoracions.put("Pol",valoracions_1);
        // Valoracions per a l'usuari Marco
        ArrayList<ValoracioEstrella> valoracions_2 = new ArrayList<>();
        valoracions_2.add(new ValoracioEstrella("BB: temporada 1: episodi 2", 1));
        valoracions_2.add(new ValoracioEstrella( "BB: temporada 2: episodi 1", 3));
        valoracions_2.add(new ValoracioEstrella( "GoT: temporada 1: episodi 1", 3));
        valoracions_2.add(new ValoracioEstrella( "GoT: temporada 3: episodi 3", 5));
        listValoracions.put("Marco",valoracions_1);
    }

    @Override
    public Optional<ValoracioEstrella> getById(String id){
        for (Map.Entry<String, ArrayList<ValoracioEstrella>> entry: listValoracions.entrySet()) {
            String key = entry.getKey();
            List<ValoracioEstrella> values = entry.getValue();
            for (ValoracioEstrella v:values) {
                if (v.getEpisodi().equals(id)){
                    return Optional.of(v);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ValoracioEstrella> getAll(){

        List<ValoracioEstrella> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<ValoracioEstrella>> entry: listValoracions.entrySet()) {
            String key = entry.getKey();
            List<ValoracioEstrella> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(ValoracioEstrella valoracioEstrella) {
        //no l'implementem perque necessitem un usuari com a parametre
        return false;
    }

    @Override
    public boolean update(ValoracioEstrella valoracioEstrella, String[] params) {
        //no l'implementem perque necessitem un usuari i una valoracio com a parametre
        return false;
    }

    @Override
    public boolean add(String usuari, ValoracioEstrella valoracioEstrella) {
        if (listValoracions.containsKey(usuari)) {
            ArrayList<ValoracioEstrella> llista = listValoracions.get(usuari);
            for (ValoracioEstrella v:llista) {
                if (v.getEpisodi().equals (valoracioEstrella.getEpisodi())) return false;
            }
            llista.add(valoracioEstrella);
        }
        else {
            ArrayList<ValoracioEstrella> valoracions_3 = new ArrayList<>();
            valoracions_3.add(valoracioEstrella);
            listValoracions.put(usuari, valoracions_3);
        }
        return true;
    }

    @Override
    public boolean update(String usuari, ValoracioEstrella valoracioEstrella, int valoracio) {
        // El update de valoracio estrella, li canviarà la valoració
        if (listValoracions.containsKey(usuari)) {
            ArrayList<ValoracioEstrella> llista = listValoracions.get(usuari);
            for (ValoracioEstrella v : llista) {
                if (v.getEpisodi().equals(valoracioEstrella.getEpisodi())) {
                    v.setValoracio(Objects.requireNonNull(
                            valoracio, "Name cannot be null"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(ValoracioEstrella valoracioEstrella) {
        //no l'implementem ja que no l'utilitzem
        return false;
    }
}
