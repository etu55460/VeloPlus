package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.Client;

import java.util.ArrayList;

public interface ClientDataAccess {

    ArrayList<Client> getAll() throws DataAccessException;

    void insert(Client client) throws DataAccessException;

    void update(Client client) throws DataAccessException;

    void delete(int clientId) throws DataAccessException;
}