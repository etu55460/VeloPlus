package org.veloplus.controllerPackage;

import org.veloplus.businessPackage.ClientManager;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.modelPackage.Client;

import java.util.ArrayList;

public class ClientController {

    private ClientManager clientManager;

    public ClientController() throws ApplicationException {
        clientManager = new ClientManager();
    }

    public ArrayList<Client> getAllClients() throws ApplicationException {
        return clientManager.getAllClients();
    }

    public void addClient(Client client) throws ApplicationException {
        clientManager.addClient(client);
    }
}
