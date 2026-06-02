package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.SearchResultInvoice;
import org.veloplus.modelPackage.SearchResultOrderLine;
import org.veloplus.modelPackage.SearchResultRepair;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchDBAccess implements SearchDataAccess {

    private Connection connection;

    public SearchDBAccess() throws DataAccessException {
        connection = SingletonConnection.getInstance();
    }

    @Override
    public ArrayList<SearchResultOrderLine> findBikeOrderLinesBetweenDates(LocalDate startDate, LocalDate endDate)
            throws DataAccessException {

        ArrayList<SearchResultOrderLine> orderLines = new ArrayList<>();

        String sql = """
                SELECT
                    so.order_date,
                    so.deposit_amount,
                    sol.quantity,
                    ROUND(sol.quantity * sol.unit_price * (1 - COALESCE(sol.discount_percent, 0) / 100), 2) AS line_price,
                    sol.discount_percent,
                    b.model,
                    b.size_id,
                    b.unit_price AS bike_unit_price
                FROM sale_order so
                JOIN sale_order_line sol ON so.order_id = sol.order_id
                JOIN bike b ON sol.bike_id = b.bike_id
                WHERE so.order_date BETWEEN ? AND ?
                ORDER BY so.order_date
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(startDate));
            statement.setDate(2, Date.valueOf(endDate));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SearchResultOrderLine orderLine = new SearchResultOrderLine();

                orderLine.setOrderDate(resultSet.getDate("order_date").toLocalDate());
                orderLine.setDepositAmount(resultSet.getBigDecimal("deposit_amount"));
                orderLine.setQuantity(resultSet.getInt("quantity"));
                orderLine.setLinePrice(resultSet.getBigDecimal("line_price"));
                orderLine.setDiscountPercent(resultSet.getBigDecimal("discount_percent"));
                orderLine.setBikeModel(resultSet.getString("model"));
                orderLine.setBikeSizeId(resultSet.getInt("size_id"));
                orderLine.setBikeUnitPrice(resultSet.getBigDecimal("bike_unit_price"));

                orderLines.add(orderLine);
            }

            return orderLines;

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la recherche des lignes de commandes de velos.", exception);
        }
    }

    @Override
    public ArrayList<SearchResultRepair> findRepairsByClient(Integer clientId) throws DataAccessException {
        ArrayList<SearchResultRepair> repairs = new ArrayList<>();

        String sql = """
                SELECT
                    ro.repair_id,
                    TRIM(CONCAT_WS(' ', NULLIF(c.first_name, ''), NULLIF(c.last_name, ''))) AS client_name,
                    ro.problem_description,
                    ro.deposit_date,
                    ro.estimate_amount,
                    ro.accepted_estimate,
                    s.label AS status_label
                FROM repair_order ro
                JOIN client c ON ro.client_id = c.client_id
                JOIN status s ON ro.status_id = s.status_id
                WHERE c.client_id = ?
                ORDER BY ro.deposit_date DESC
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SearchResultRepair repair = new SearchResultRepair();

                repair.setRepairId(resultSet.getInt("repair_id"));
                repair.setClientName(resultSet.getString("client_name"));
                repair.setProblemDescription(resultSet.getString("problem_description"));
                repair.setDepositDate(resultSet.getDate("deposit_date").toLocalDate());
                repair.setEstimateAmount(resultSet.getBigDecimal("estimate_amount"));
                repair.setAcceptedEstimate(resultSet.getBoolean("accepted_estimate"));
                repair.setStatusLabel(resultSet.getString("status_label"));

                repairs.add(repair);
            }

            return repairs;

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la recherche des reparations du client.", exception);
        }
    }

    @Override
    public ArrayList<SearchResultInvoice> findInvoicesByPaidStatus(boolean paid) throws DataAccessException {
        ArrayList<SearchResultInvoice> invoices = new ArrayList<>();

        String sql = """
                SELECT
                    i.invoice_id,
                    i.invoice_date,
                    i.payment_date,
                    i.total_amount,
                    i.is_paid,
                    TRIM(CONCAT_WS(' ', NULLIF(c.first_name, ''), NULLIF(c.last_name, ''))) AS client_name,
                    pm.payment_method_name
                FROM invoice i
                JOIN client c ON i.client_id = c.client_id
                JOIN payment_method pm ON i.payment_method_id = pm.payment_method_id
                WHERE i.is_paid = ?
                ORDER BY i.invoice_date DESC
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, paid);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SearchResultInvoice invoice = new SearchResultInvoice();

                invoice.setInvoiceId(resultSet.getInt("invoice_id"));
                invoice.setInvoiceDate(resultSet.getDate("invoice_date").toLocalDate());
                invoice.setPaymentDate(getNullableLocalDate(resultSet, "payment_date"));
                invoice.setTotalAmount(resultSet.getBigDecimal("total_amount"));
                invoice.setPaid(resultSet.getBoolean("is_paid"));
                invoice.setClientName(resultSet.getString("client_name"));
                invoice.setPaymentMethodName(resultSet.getString("payment_method_name"));

                invoices.add(invoice);
            }

            return invoices;

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la recherche des factures.", exception);
        }
    }

    private LocalDate getNullableLocalDate(ResultSet resultSet, String columnName) throws SQLException {
        Date sqlDate = resultSet.getDate(columnName);

        if (sqlDate == null) {
            return null;
        }

        return sqlDate.toLocalDate();
    }
}
