package org.veloplus.businessPackage;

import org.veloplus.dataAccessPackage.SearchDBAccess;
import org.veloplus.dataAccessPackage.SearchDataAccess;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.exceptionPackage.BusinessException;
import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.exceptionPackage.ValidationException;
import org.veloplus.modelPackage.SearchResultInvoice;
import org.veloplus.modelPackage.SearchResultOrderLine;
import org.veloplus.modelPackage.SearchResultRepair;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchManager {

    private SearchDataAccess searchDataAccess;

    public SearchManager() throws ApplicationException {
        try {
            searchDataAccess = new SearchDBAccess();
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de preparer les recherches.", exception);
        }
    }

    public ArrayList<SearchResultOrderLine> findBikeOrderLinesBetweenDates(LocalDate startDate, LocalDate endDate)
            throws ApplicationException {

        validateDateInterval(startDate, endDate);

        try {
            return searchDataAccess.findBikeOrderLinesBetweenDates(startDate, endDate);
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de charger les commandes de velos.", exception);
        }
    }

    public ArrayList<SearchResultRepair> findRepairsByClient(Integer clientId) throws ApplicationException {
        if (clientId == null) {
            throw new ValidationException("Le client est obligatoire.");
        }

        try {
            return searchDataAccess.findRepairsByClient(clientId);
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de charger les reparations du client.", exception);
        }
    }

    public ArrayList<SearchResultInvoice> findInvoicesByPaidStatus(boolean paid) throws ApplicationException {
        try {
            return searchDataAccess.findInvoicesByPaidStatus(paid);
        } catch (DataAccessException exception) {
            throw new ApplicationException("Impossible de charger les factures.", exception);
        }
    }

    private void validateDateInterval(LocalDate startDate, LocalDate endDate) throws BusinessException {
        if (startDate == null) {
            throw new ValidationException("La date de debut est obligatoire.");
        }

        if (endDate == null) {
            throw new ValidationException("La date de fin est obligatoire.");
        }

        if (endDate.isBefore(startDate)) {
            throw new ValidationException("La date de fin ne peut pas etre avant la date de debut.");
        }
    }
}
