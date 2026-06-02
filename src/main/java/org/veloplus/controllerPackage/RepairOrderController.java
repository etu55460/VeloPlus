package org.veloplus.controllerPackage;

import org.veloplus.businessPackage.RepairOrderManager;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.modelPackage.RepairOrder;

import java.util.ArrayList;

public class RepairOrderController {

    private RepairOrderManager repairOrderManager;

    public RepairOrderController() throws ApplicationException {
        repairOrderManager = new RepairOrderManager();
    }

    public ArrayList<RepairOrder> getAllRepairOrders() throws ApplicationException {
        return repairOrderManager.getAllRepairOrders();
    }

    public void addRepairOrder(RepairOrder repairOrder) throws ApplicationException {
        repairOrderManager.addRepairOrder(repairOrder);
    }

    public void updateRepairOrder(RepairOrder repairOrder) throws ApplicationException {
        repairOrderManager.updateRepairOrder(repairOrder);
    }

    public void deleteRepairOrder(int repairId, boolean deleteLinkedInvoices) throws ApplicationException {
        repairOrderManager.deleteRepairOrder(repairId, deleteLinkedInvoices);
    }

    public boolean hasLinkedInvoice(int repairId) throws ApplicationException {
        return repairOrderManager.hasLinkedInvoice(repairId);
    }
}
