package resources.dao.MOCK;
import model.Client;
import model.Usuari;
import resources.dao.DAOUsuari;

import java.util.*;

public class DAOUsuariMOCK implements DAOUsuari {
    private Map<String, ArrayList<Usuari>> listUsuaris = new HashMap<>();

    public DAOUsuariMOCK(){
        // Usuaris pel client "ajaleo"
        ArrayList<Usuari> usuaris_1 = new ArrayList<>();
        usuaris_1.add(new Usuari("ajaleo", "Pol", "id1"));
        usuaris_1.add(new Usuari("ajaleo", "Manuel", "id2"));
        listUsuaris.put("ajaleo", usuaris_1);

        // Usuaris pel client "dtomacal"
        ArrayList<Usuari> usuaris_2 = new ArrayList<>();
        usuaris_2.add(new Usuari("dtomacal", "Marc", "id3"));
        usuaris_2.add(new Usuari("dtomacal", "Laura", "id4"));
        usuaris_2.add(new Usuari("dtomacal", "Marco", "id5"));
        usuaris_2.add(new Usuari("dtomacal", "Ignasi", "id6"));
        listUsuaris.put("dtomacal", usuaris_2);
    }

    @Override
    public List<Usuari> getAll() {
        List<Usuari> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Usuari>> entry: listUsuaris.entrySet()) {
            String key = entry.getKey();
            List<Usuari> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public Optional<Usuari> getById(String id) {
        for (Map.Entry<String, ArrayList<Usuari>> entry: listUsuaris.entrySet()) {
            String key = entry.getKey();
            List<Usuari> values = entry.getValue();
            for (Usuari v:values) {
                if (v.getIdUser().equals(id)) return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean add(final Usuari usuari) {
        if (listUsuaris.containsKey(usuari.getIdClient())) {
           ArrayList<Usuari> llista = listUsuaris.get(usuari.getIdClient());
           for (Usuari u:llista) {
               if (u.getIdUser().equals(usuari.getIdUser())) return false;
           }
           llista.add(usuari);
        }
        else {
            ArrayList<Usuari> usuaris_3 = new ArrayList<>();
            usuaris_3.add(usuari);
            listUsuaris.put(usuari.getIdClient(), usuaris_3);
        }
        return true;
    }

    @Override
    // El update de usuari, li canviarà el nom de l'usuari
    public boolean update(final Usuari usuari, String[] params) {
        if (listUsuaris.containsKey(usuari.getIdClient())) {
            ArrayList<Usuari> llista = listUsuaris.get(usuari.getIdClient());
            for (Usuari u : llista) {
                if (u.getIdUser().equals(usuari.getIdUser())) {
                    u.setName(Objects.requireNonNull(
                            params[0], "Name cannot be null"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Usuari usuari) throws Exception {
        throw new Exception();
    }

    public List<Usuari> getUsuarisForClient(Client c) {
        if (c == null) {
            throw new ClassCastException();
        }
        if (listUsuaris.containsKey(c.getName())) {
            return (listUsuaris.get(c.getName()));
        }
        return null;
    }
}
