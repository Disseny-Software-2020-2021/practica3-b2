package resources.dao;

import model.Client;


public interface DAOClient extends DAO<Client> {
    Client  findClientByUserNameAndPassword(String usuari, String pwd) throws Exception;
}
