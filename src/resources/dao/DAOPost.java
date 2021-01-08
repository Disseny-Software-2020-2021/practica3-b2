package resources.dao;

import model.Post;

public interface DAOPost extends DAO<Post> {
    boolean add(String usuari, Post post) throws Exception;
}
