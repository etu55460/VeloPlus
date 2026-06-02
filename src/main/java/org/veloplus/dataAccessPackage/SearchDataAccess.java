package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.SearchResultInvoice;
import org.veloplus.modelPackage.SearchResultOrderLine;
import org.veloplus.modelPackage.SearchResultRepair;

import java.time.LocalDate;
import java.util.ArrayList;

public interface SearchDataAccess {

    ArrayList<SearchResultOrderLine> findBikeOrderLinesBetweenDates(LocalDate startDate, LocalDate endDate)
            throws DataAccessException;

    ArrayList<SearchResultRepair> findRepairsByClient(Integer clientId) throws DataAccessException;

    ArrayList<SearchResultInvoice> findInvoicesByPaidStatus(boolean paid) throws DataAccessException;
}
