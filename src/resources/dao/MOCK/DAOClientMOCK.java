package resources.dao.MOCK;

import model.Client;
import resources.dao.DAOClient;

import java.util.*;

public class DAOClientMOCK implements DAOClient {

    private  Map<String, Client> listClients = new HashMap<>();

    public DAOClientMOCK() {
        listClients.put("ajaleo", new Client("ajaleo", "ajaleoPassw"));
        listClients.put("dtomacal", new Client("dtomacal", "qwerty"));
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(listClients.values());
    }

    @Override
    public Optional<Client> getById(String id) {
        return Optional.ofNullable(listClients.get(id));
    }

    @Override
    public boolean add(final Client client) {
        if (listClients.containsKey(client.getName())) {
            return false;
        }
        listClients.put(client.getName(), client);
        return true;
    }

    @Override
    public boolean update(final Client client, String[] params) {
        client.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        client.setPwd(Objects.requireNonNull(
                params[1], "Password cannot be null"));
        return listClients.replace(client.getName(), client) != null;
    }

    @Override
    public boolean delete(final Client client) {
        return listClients.remove(client.getName()) != null;
    }

    @Override
    public Client findClientByUserNameAndPassword(String usuari, String pwd) throws Exception {
        if (getById(usuari).isPresent()) {
            Client c = listClients.get(usuari);
            if (c.getPwd().equals(pwd)) {
                return c;
            } else throw new Exception();
        } else throw new Exception();
    }

}
