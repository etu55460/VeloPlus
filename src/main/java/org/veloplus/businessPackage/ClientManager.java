package org.veloplus.businessPackage;

import org.veloplus.dataAccessPackage.ClientDBAccess;
import org.veloplus.dataAccessPackage.ClientDataAccess;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.exceptionPackage.BusinessException;
import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.exceptionPackage.ValidationException;
import org.veloplus.modelPackage.Client;

import java.util.ArrayList;

public class ClientManager {

    private ClientDataAccess clientDataAccess;

    public ClientManager() throws ApplicationException {
        try {
            clientDataAccess = new ClientDBAccess();
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de préparer l'accès aux clients.", exception);
        }
    }

    public ArrayList<Client> getAllClients() throws ApplicationException {
        try {
            return clientDataAccess.getAll();
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de charger les clients.", exception);
        }
    }

    public void addClient(Client client) throws ApplicationException {
        validateClient(client);

        try {
            clientDataAccess.insert(client);
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible d'enregistrer le client.", exception);
        }
    }

    private void validateClient(Client client) throws BusinessException {
        if (client == null) {
            throw new ValidationException("Le client est obligatoire.");
        }

        if (client.getFirstName() == null || client.getFirstName().isBlank()) {
            throw new ValidationException("Le prénom ou nom d'entreprise est obligatoire.");
        }

        if (client.getPhone() == null || client.getPhone().isBlank()) {
            throw new ValidationException("Le téléphone est obligatoire.");
        }

        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new ValidationException("L'email est obligatoire.");
        }

        if (client.getClientKind() == null || client.getClientKind().getKindId() == null) {
            throw new ValidationException("Le type de client est obligatoire.");
        }

        if (client.getCategory() == null || client.getCategory().getCategoryId() == null) {
            throw new ValidationException("La catégorie du client est obligatoire.");
        }
    }
}
