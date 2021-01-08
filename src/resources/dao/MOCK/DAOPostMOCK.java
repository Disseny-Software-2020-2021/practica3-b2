package resources.dao.MOCK;

import model.Post;
import resources.dao.DAOPost;

import java.util.*;

public class DAOPostMOCK implements DAOPost {
    private Map<String, ArrayList<Post>> postList = new HashMap<>();

    public DAOPostMOCK(){
        // Post del usuari "Pol"
        ArrayList<Post> Postlist_1 = new ArrayList<>();
        Postlist_1.add(new Post("Breaking Bad es molt recomanable"));
        Postlist_1.add(new Post("Game of Thrones es la millor serie d'aquest any"));
        postList.put("Pol", Postlist_1);

        // Post del usuari "Manuel"
        ArrayList<Post> Postlist_2 = new ArrayList<>();
        Postlist_2.add(new Post("Breaking Bad Tras cumplir 50 años, Walter White..."));
        Postlist_2.add(new Post("Game of Thronesno esta pas malament"));
        postList.put("Manuel", Postlist_2);

        // Post del usuari "Marc"
        ArrayList<Post> Postlist_3 = new ArrayList<>();
        Postlist_3.add(new Post("Breaking Bad Tras cumplir 50 años, Walter White..."));
        Postlist_3.add(new Post("Game of Thrones La historia se desarrolla..."));
        postList.put("Marc", Postlist_3);

        // Post del usuari "Laura"
        ArrayList<Post> Postlist_4 = new ArrayList<>();
        Postlist_4.add(new Post("Breaking Bad es una bona serie"));
        Postlist_4.add(new Post("Game of Thrones no esta malament"));
        postList.put("Laura", Postlist_4);

        // Post del usuari "Marco"
        ArrayList<Post> Postlist_5 = new ArrayList<>();
        Postlist_5.add(new Post("Breaking Bad es una serie massa llarga"));
        Postlist_5.add(new Post("Game of Thrones es la millor serie de la historia"));
        postList.put("Marco", Postlist_5);

        // Post del usuari "Ignasi"
        ArrayList<Post> Postlist_6 = new ArrayList<>();
        Postlist_6.add(new Post("Breaking Bad no esta malament pero es millorble"));
        Postlist_6.add(new Post("Game of Thrones podria ser molt millor"));
        postList.put("Ignasi", Postlist_6);
    }

    @Override
    public Optional<Post> getById(String id) throws Exception {
        for (Map.Entry<String, ArrayList<Post>> entry: postList.entrySet()) {
            String key = entry.getKey();
            List<Post> values = entry.getValue();
            for (Post p :values) {
                if (p.getPost().equals(id)) return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Post> getAll() throws Exception {
        List<Post> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Post>> entry: postList.entrySet()) {
            String key = entry.getKey();
            List<Post> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(Post post) throws Exception {
        return false;
    }


    @Override
    public boolean add(String usuari, Post post) throws Exception {
        if (postList.containsKey(usuari)) {
            ArrayList<Post> llista = postList.get(usuari);
            for (Post p:llista) {
                if (p.getPost().equals(post.getPost())) return false;
            }
            llista.add(post);
        }
        else {
            ArrayList<Post> postList_3 = new ArrayList<>();
            postList_3.add(post);
            postList.put(usuari, postList_3);
        }
        return true;
    }

    @Override
    public boolean update(Post post, String[] params) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Post post) throws Exception {
        return false;
    }
}