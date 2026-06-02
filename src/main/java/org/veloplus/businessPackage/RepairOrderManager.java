package org.veloplus.businessPackage;

import org.veloplus.dataAccessPackage.RepairOrderDBAccess;
import org.veloplus.dataAccessPackage.RepairOrderDataAccess;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.exceptionPackage.BusinessException;
import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.exceptionPackage.ValidationException;
import org.veloplus.modelPackage.RepairOrder;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RepairOrderManager {

    private RepairOrderDataAccess repairOrderDataAccess;

    public RepairOrderManager() throws ApplicationException {
        try {
            repairOrderDataAccess = new RepairOrderDBAccess();
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de preparer l'acces aux reparations.", exception);
        }
    }

    public ArrayList<RepairOrder> getAllRepairOrders() throws ApplicationException {
        try {
            return repairOrderDataAccess.getAll();
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de charger les reparations.", exception);
        }
    }

    public void addRepairOrder(RepairOrder repairOrder) throws ApplicationException {
        validateRepairOrder(repairOrder);

        try {
            repairOrderDataAccess.insert(repairOrder);
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible d'enregistrer la reparation.", exception);
        }
    }

    public void updateRepairOrder(RepairOrder repairOrder) throws ApplicationException {
        validateRepairOrder(repairOrder);

        if (repairOrder.getRepairId() == null) {
            throw new ValidationException("L'identifiant de la reparation est obligatoire pour modifier une reparation.");
        }

        try {
            repairOrderDataAccess.update(repairOrder);
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de modifier la reparation.", exception);
        }
    }

    public void deleteRepairOrder(int repairId, boolean deleteLinkedInvoices) throws ApplicationException {
        try {
            if (repairOrderDataAccess.hasLinkedInvoice(repairId)) {
                if (!deleteLinkedInvoices) {
                    throw new ValidationException(
                            "Impossible de supprimer cette reparation car une facture y est liee."
                    );
                }

                repairOrderDataAccess.deleteWithLinkedInvoices(repairId);
                return;
            }

            repairOrderDataAccess.delete(repairId);

        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de supprimer la reparation.", exception);
        }
    }

    public boolean hasLinkedInvoice(int repairId) throws ApplicationException {
        try {
            return repairOrderDataAccess.hasLinkedInvoice(repairId);
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de verifier les factures liees.", exception);
        }
    }

    private void validateRepairOrder(RepairOrder repairOrder) throws BusinessException {
        if (repairOrder == null) {
            throw new ValidationException("La reparation est obligatoire.");
        }

        if (repairOrder.getClient() == null || repairOrder.getClient().getClientId() == null) {
            throw new ValidationException("Le client est obligatoire.");
        }

        if (repairOrder.getProblemDescription() == null || repairOrder.getProblemDescription().isBlank()) {
            throw new ValidationException("La description du probleme est obligatoire.");
        }

        if (repairOrder.getDepositDate() == null) {
            throw new ValidationException("La date de depot est obligatoire.");
        }

        if (repairOrder.getAppointmentDate() != null
                && repairOrder.getAppointmentDate().isBefore(repairOrder.getDepositDate())) {
            throw new ValidationException("La date de rendez-vous ne peut pas etre avant la date de depot.");
        }

        if (repairOrder.getEstimateAmount() == null) {
            throw new ValidationException("Le montant du devis est obligatoire.");
        }

        if (repairOrder.getEstimateAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Le montant du devis ne peut pas etre negatif.");
        }

        if (repairOrder.getLaborHours() != null
                && repairOrder.getLaborHours().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Le nombre d'heures de main-d'oeuvre ne peut pas etre negatif.");
        }

        if (repairOrder.getStatus() == null || repairOrder.getStatus().getStatusId() == null) {
            throw new ValidationException("Le statut est obligatoire.");
        }

        if (repairOrder.getAcceptedEstimate() == null) {
            repairOrder.setAcceptedEstimate(false);
        }
    }
}
