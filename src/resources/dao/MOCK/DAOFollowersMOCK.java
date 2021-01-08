package resources.dao.MOCK;

import model.Usuari;
import resources.dao.DAOFollowers;

import java.util.*;

public class DAOFollowersMOCK implements DAOFollowers {
    private Map<String, ArrayList<Usuari>> followers = new HashMap<>();

    public DAOFollowersMOCK(){
        // Series pel usuari "Pol"
        ArrayList<Usuari> followers_1 = new ArrayList<>();
        followers_1.add(new Usuari("ajaleo", "Manuel", "id2"));
        followers_1.add(new Usuari("dtomacal", "Marc", "id3"));
        followers_1.add(new Usuari("dtomacal", "Laura", "id4"));
        followers_1.add(new Usuari("dtomacal", "Marco", "id5"));
        followers_1.add(new Usuari("dtomacal", "Ignasi", "id6"));
        followers.put("Pol", followers_1);

        // Series pel usuari "Manuel"
        ArrayList<Usuari> followers_2 = new ArrayList<>();
        followers_2.add(new Usuari("ajaleo", "Manuel", "id2"));
        followers.put("Manuel", followers_2);

        // Series pel usuari "Marc"
        ArrayList<Usuari> followers_3 = new ArrayList<>();
        followers_3.add(new Usuari("ajaleo", "Manuel", "id2"));
        followers_3.add(new Usuari("dtomacal", "Laura", "id4"));
        followers_3.add(new Usuari("dtomacal", "Marco", "id5"));
        followers_3.add(new Usuari("dtomacal", "Ignasi", "id6"));
        followers.put("Marc", followers_3);

        // Series pel usuari "Laura"
        ArrayList<Usuari> followers_4 = new ArrayList<>();
        followers.put("Laura", followers_4);

        // Series pel usuari "Marco"
        ArrayList<Usuari> followers_5 = new ArrayList<>();
        followers.put("Marco", followers_5);

        // Series pel usuari "Ignasi"
        ArrayList<Usuari> followers_6 = new ArrayList<>();
        followers.put("Ignasi", followers_6);
    }

    @Override
    public Optional<Usuari> getById(String id) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Usuari> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean add(Usuari usuari) throws Exception {
        return false;
    }

    @Override
    public boolean update(Usuari usuari, String[] params) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Usuari usuari) throws Exception {
        return false;
    }

    public List<Usuari> getFollowersForUsuari(Usuari u) {
        if (u == null) {
            throw new ClassCastException();
        }
        if (followers.containsKey(u.getName())) {
            return (followers.get(u.getName()));
        }
        return null;
    }
}
