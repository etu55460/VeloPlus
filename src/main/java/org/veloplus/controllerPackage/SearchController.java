package org.veloplus.controllerPackage;

import org.veloplus.businessPackage.SearchManager;
import org.veloplus.exceptionPackage.ApplicationException;
import org.veloplus.modelPackage.SearchResultInvoice;
import org.veloplus.modelPackage.SearchResultOrderLine;
import org.veloplus.modelPackage.SearchResultRepair;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchController {

    private SearchManager searchManager;

    public SearchController() throws ApplicationException {
        searchManager = new SearchManager();
    }

    public ArrayList<SearchResultOrderLine> findBikeOrderLinesBetweenDates(LocalDate startDate, LocalDate endDate)
            throws ApplicationException {
        return searchManager.findBikeOrderLinesBetweenDates(startDate, endDate);
    }

    public ArrayList<SearchResultRepair> findRepairsByClient(Integer clientId) throws ApplicationException {
        return searchManager.findRepairsByClient(clientId);
    }

    public ArrayList<SearchResultInvoice> findInvoicesByPaidStatus(boolean paid) throws ApplicationException {
        return searchManager.findInvoicesByPaidStatus(paid);
    }
}
