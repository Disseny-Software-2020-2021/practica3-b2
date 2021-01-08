package resources.dao.MOCK;

import model.ValoracioThumb;
import resources.dao.DAOValoracioThumb;

import java.util.*;

public class DAOValoracioThumbMOCK implements DAOValoracioThumb{
    private Map<String, ArrayList<ValoracioThumb>> listValoracions = new HashMap<>();
    public DAOValoracioThumbMOCK() {
        // Valoracions per a l'usuari Pol
        ArrayList<ValoracioThumb> valoracions_1 = new ArrayList<>();
        valoracions_1.add(new ValoracioThumb("BB: temporada 1: episodi 1", true));
        valoracions_1.add(new ValoracioThumb( "BB: temporada 2: episodi 2", true));
        valoracions_1.add(new ValoracioThumb( "GoT: temporada 1: episodi 2", false));
        valoracions_1.add(new ValoracioThumb( "GoT: temporada 3: episodi 1", false));
        listValoracions.put("Pol",valoracions_1);
        // Valoracions per a l'usuari Marco
        ArrayList<ValoracioThumb> valoracions_2 = new ArrayList<>();
        valoracions_2.add(new ValoracioThumb("BB: temporada 1: episodi 2", false));
        valoracions_2.add(new ValoracioThumb( "BB: temporada 2: episodi 1", false));
        valoracions_2.add(new ValoracioThumb( "GoT: temporada 1: episodi 1", false));
        valoracions_2.add(new ValoracioThumb( "GoT: temporada 3: episodi 3", true));
        listValoracions.put("Marco",valoracions_1);
    }

    @Override
    public Optional<ValoracioThumb> getById(String id){
        for (Map.Entry<String, ArrayList<ValoracioThumb>> entry: listValoracions.entrySet()) {
            String key = entry.getKey();
            List<ValoracioThumb> values = entry.getValue();
            for (ValoracioThumb v:values) {
                if (v.getEpisodi().equals(id)){
                    return Optional.of(v);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ValoracioThumb> getAll(){

        List<ValoracioThumb> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<ValoracioThumb>> entry: listValoracions.entrySet()) {
            String key = entry.getKey();
            List<ValoracioThumb> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(ValoracioThumb valoracioThumb){
        //no l'implementem perque necessitem un usuari com a parametre
        return false;
    }

    @Override
    public boolean update(ValoracioThumb valoracioThumb, String[] params){
        //no l'implementem perque necessitem un usuari i una valoracio com a parametre
        return false;
    }

    @Override
    public boolean add(String usuari, ValoracioThumb valoracioThumb){
        if (listValoracions.containsKey(usuari)) {
            ArrayList<ValoracioThumb> llista = listValoracions.get(usuari);
            for (ValoracioThumb v:llista) {
                if (v.getEpisodi().equals (valoracioThumb.getEpisodi())) return false;
            }
            llista.add(valoracioThumb);
        }
        else {
            ArrayList<ValoracioThumb> valoracions_3 = new ArrayList<>();
            valoracions_3.add(valoracioThumb);
            listValoracions.put(usuari, valoracions_3);
        }
        return true;
    }

    @Override
    public boolean update(String usuari, ValoracioThumb valoracioThumb, boolean valoracio){
        // El update de valoracio thumb, li canviarà la valoració
        if (listValoracions.containsKey(usuari)) {
            ArrayList<ValoracioThumb> llista = listValoracions.get(usuari);
            for (ValoracioThumb v : llista) {
                if (v.getEpisodi().equals(valoracioThumb.getEpisodi())) {
                    v.setValoracio(Objects.requireNonNull(
                            valoracio, "Name cannot be null"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(ValoracioThumb valoracioThumb){
        //no l'implementem ja que no l'utilitzem
        return false;
    }
}
