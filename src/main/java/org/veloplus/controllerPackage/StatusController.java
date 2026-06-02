package org.veloplus.controllerPackage;

import org.veloplus.businessPackage.StatusManager;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.modelPackage.Status;

import java.util.ArrayList;

public class StatusController {

    private StatusManager statusManager;

    public StatusController() throws ApplicationException {
        statusManager = new StatusManager();
    }

    public ArrayList<Status> getAllStatuses() throws ApplicationException {
        return statusManager.getAllStatuses();
    }
}
