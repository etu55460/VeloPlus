package org.veloplus.businessPackage;

import org.veloplus.dataAccessPackage.StatusDBAccess;
import org.veloplus.dataAccessPackage.StatusDataAccess;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.Status;

import java.util.ArrayList;

public class StatusManager {

    private StatusDataAccess statusDataAccess;

    public StatusManager() throws ApplicationException {
        try {
            statusDataAccess = new StatusDBAccess();
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de préparer l'accès aux statuts.", exception);
        }
    }

    public ArrayList<Status> getAllStatuses() throws ApplicationException {
        try {
            return statusDataAccess.getAll();
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de charger les statuts.", exception);
        }
    }
}
