package resources.dao.MOCK;

import model.Usuari;
import resources.dao.DAOFollowing;

import java.util.*;

public class DAOFollowingMOCK implements DAOFollowing {
    private Map<String, ArrayList<Usuari>> following = new HashMap<>();

    public DAOFollowingMOCK(){
        // Following pel usuari "Pol"
        ArrayList<Usuari> following_1 = new ArrayList<>();
        following_1.add(new Usuari("ajaleo", "Manuel", "id2"));
        following_1.add(new Usuari("dtomacal", "Marc", "id3"));
        following_1.add(new Usuari("dtomacal", "Laura", "id4"));
        following_1.add(new Usuari("dtomacal", "Marco", "id5"));
        following_1.add(new Usuari("dtomacal", "Ignasi", "id6"));
        following.put("Pol", following_1);

        // Following pel usuari "Manuel"
        ArrayList<Usuari> following_2 = new ArrayList<>();
        following_2.add(new Usuari("ajaleo", "Manuel", "id2"));
        following.put("Manuel", following_2);

        // Following pel usuari "Marc"
        ArrayList<Usuari> following_3 = new ArrayList<>();
        following_3.add(new Usuari("ajaleo", "Manuel", "id2"));
        following_3.add(new Usuari("dtomacal", "Laura", "id4"));
        following_3.add(new Usuari("dtomacal", "Marco", "id5"));
        following_3.add(new Usuari("dtomacal", "Ignasi", "id6"));
        following.put("Marc", following_3);

        // Following pel usuari "Laura"
        ArrayList<Usuari> following_4 = new ArrayList<>();
        following.put("Laura", following_4);

        // Following pel usuari "Marco"
        ArrayList<Usuari> following_5 = new ArrayList<>();
        following.put("Marco", following_5);

        // Following pel usuari "Ignasi"
        ArrayList<Usuari> following_6 = new ArrayList<>();
        following.put("Ignasi", following_6);
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

    public List<Usuari> getFollowingForUsuari(Usuari u) {
        if (u == null) {
            throw new ClassCastException();
        }
        if (following.containsKey(u.getName())) {
            return (following.get(u.getName()));
        }
        return null;
    }
}
