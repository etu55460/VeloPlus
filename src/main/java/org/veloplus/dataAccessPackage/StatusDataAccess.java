package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.Status;

import java.util.ArrayList;

public interface StatusDataAccess {

    ArrayList<Status> getAll() throws DataAccessException;
}