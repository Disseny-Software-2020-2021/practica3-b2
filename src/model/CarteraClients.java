package model;

import java.util.Collections;
import java.util.List;

public class CarteraClients {
    private List<Client> llista;

    public CarteraClients(List<Client> allClients) {
        llista = allClients;
    }

    public Client find(String username) {

        for (Client c : llista) {
            if (c.getName().equals(username)) return c;
        }
        return null;

    }

    public Client find_id(String dni) {

        for (Client c : llista) {
            if (c.getIdClient().equals(dni)) return c;
        }
        return null;

    }

    public boolean validateLoginUser(String clientName, String userName) {
        Client client = find(clientName);
        assert client != null;
        return (client.findUserByNameBool(userName));
    }

    public void addClient(String idClient, String psw, String dni, String adress, boolean vip) {
        Client client = new Client(idClient, psw);
        client.setIdClient(dni);
        client.setAdress(adress);
        client.setVip(vip);
        llista.add(client);
    }

    public boolean validLogin(String idClient, String psw) {
        Client client = this.find(idClient);
        if (client.getPwd().equals(psw)) {
            return true;
        }
        return false;
    }
}
