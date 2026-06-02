package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.RepairOrder;

import java.util.ArrayList;

public interface RepairOrderDataAccess {

    ArrayList<RepairOrder> getAll() throws DataAccessException;

    void insert(RepairOrder repairOrder) throws DataAccessException;

    void update(RepairOrder repairOrder) throws DataAccessException;

    boolean hasLinkedInvoice(int repairId) throws DataAccessException;

    void delete(int repairId) throws DataAccessException;

    void deleteWithLinkedInvoices(int repairId) throws DataAccessException;
}
