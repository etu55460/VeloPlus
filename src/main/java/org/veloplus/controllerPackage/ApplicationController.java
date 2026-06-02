package org.veloplus.controllerPackage;

import org.veloplus.dataAccessPackage.SingletonConnection;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.exceptionPackage.DataAccessException;

public class ApplicationController {

    public void closeApplication() throws ApplicationException {
        try {
            SingletonConnection.closeConnection();
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de fermer la connexion a la base de donnees.", exception);
        }
    }
}
