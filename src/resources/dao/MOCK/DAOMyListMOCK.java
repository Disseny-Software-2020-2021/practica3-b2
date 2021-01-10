package resources.dao.MOCK;
import model.Serie;
import model.Usuari;
import resources.dao.DAOMyList;

import java.util.*;

public class DAOMyListMOCK implements DAOMyList {
    private Map<String, ArrayList<Serie>> mylist = new HashMap<>();

    public DAOMyListMOCK(){
        // Series pel usuari "Pol"
        ArrayList<Serie> mylist_1 = new ArrayList<>();
        mylist_1.add(new Serie("Breaking Bad", "Tras cumplir 50 años, Walter White...",70));
        mylist_1.add(new Serie("Game of Thrones", "La historia se desarrolla...",60));
        mylist.put("Pol", mylist_1);

        // Series pel usuari "Manuel"
        ArrayList<Serie> mylist_2 = new ArrayList<>();
        mylist_2.add(new Serie("Breaking Bad", "Tras cumplir 50 años, Walter White...",70));
        mylist.put("Manuel", mylist_2);

        // Series pel usuari "Marc"
        ArrayList<Serie> mylist_3 = new ArrayList<>();
        mylist.put("Marc", mylist_3);

        // Series pel usuari "Laura"
        ArrayList<Serie> mylist_4 = new ArrayList<>();
        mylist_4.add(new Serie("Game of Thrones", "La historia se desarrolla...",60));
        mylist.put("Laura", mylist_4);

        // Series pel usuari "Marco"
        ArrayList<Serie> mylist_5 = new ArrayList<>();
        mylist.put("Marco", mylist_5);

        // Series pel usuari "Ignasi"
        ArrayList<Serie> mylist_6 = new ArrayList<>();
        mylist.put("Ignasi", mylist_6);
    }

    @Override
    public Optional<Serie> getById(String id) throws Exception {
        for (Map.Entry<String, ArrayList<Serie>> entry: mylist.entrySet()) {
            String key = entry.getKey();
            List<Serie> values = entry.getValue();
            for (Serie s:values) {
                if (s.getTitol().equals(id)) return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Serie> getAll() throws Exception {
        List<Serie> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Serie>> entry: mylist.entrySet()) {
            String key = entry.getKey();
            List<Serie> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(Serie serie) throws Exception {
        if (mylist.containsKey(serie.getTitol())) {
            ArrayList<Serie> llista = mylist.get(serie.getTitol());
            for (Serie s:llista) {
                if (s.getTitol().equals(serie.getTitol())) return false;
            }
            llista.add(serie);
        }
        else {
            ArrayList<Serie> serie_3 = new ArrayList<>();
            serie_3.add(serie);
            mylist.put(serie.getTitol(), serie_3);
        }
        return true;
    }

    @Override
    public boolean update(Serie serie, String[] params) throws Exception {
        if (mylist.containsKey(serie.getTitol())) {
            ArrayList<Serie> llista = mylist.get(serie.getTitol());
            for (Serie s : llista) {
                if (s.getTitol().equals(serie.getTitol())) {
                    s.setTitol(Objects.requireNonNull(
                            params[0], "Titol cannot be null"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Serie serie) throws Exception {
        return false;
    }

    public List<Serie> getMyListForUsuari(Usuari u) {
        if (u == null) {
            throw new ClassCastException();
        }
        if (mylist.containsKey(u.getName())) {
            return (mylist.get(u.getName()));
        }
        return null;
    }

    public String addToMyList(Serie serie, Usuari usuari){
        if (mylist.containsKey(usuari.getName())) {
            List<Serie> series = this.getMyListForUsuari(usuari);
            for(int x = 0; x < series.size(); x++){
                if(series.get(x).getTitol().equals(serie.getTitol())){
                    return "Ja estava marcada";
                }
            }
            series.add(new Serie(serie.getTitol(), serie.getDescripcio(), serie.getVisualitzacio()));
            mylist.put(usuari.getName(), (ArrayList<Serie>) series);
            return "Marcada";
        }
        return "Usuari no trobat";
    }
    public String removeFromMyList(Serie serie, Usuari usuari){
        if (mylist.containsKey(usuari.getName())) {
            List<Serie> series = this.getMyListForUsuari(usuari);
            boolean desmarcada = false;
            for(int x = 0; x < series.size(); x++){
                if(series.get(x).getTitol().equals(serie.getTitol())){
                    series.remove(x);
                    desmarcada = true;
                }
            }
            mylist.put(usuari.getName(), (ArrayList<Serie>) series);
            if(desmarcada){
                return "Desmarcada";
            }else{
                return "No s'ha trobat la serie";
            }
        }
        return "Usuari no trobat";
    }
}
