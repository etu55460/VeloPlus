package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.Client;
import org.veloplus.modelPackage.RepairOrder;
import org.veloplus.modelPackage.Status;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

public class RepairOrderDBAccess implements RepairOrderDataAccess {

    private Connection connection;

    public RepairOrderDBAccess() throws DataAccessException {
        connection = SingletonConnection.getInstance();
    }

    @Override
    public ArrayList<RepairOrder> getAll() throws DataAccessException {
        ArrayList<RepairOrder> repairOrders = new ArrayList<>();

        String sql = """
                SELECT
                    ro.repair_id,
                    ro.problem_description,
                    ro.deposit_date,
                    ro.appointment_date,
                    ro.estimate_amount,
                    ro.accepted_estimate,
                    ro.labor_hours,
                    ro.notes,
                    c.client_id,
                    c.first_name,
                    c.last_name,
                    s.status_id,
                    s.label
                FROM repair_order ro
                JOIN client c ON ro.client_id = c.client_id
                JOIN status s ON ro.status_id = s.status_id
                ORDER BY ro.repair_id
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RepairOrder repairOrder = new RepairOrder();

                Client client = new Client();
                client.setClientId(resultSet.getInt("client_id"));
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));

                Status status = new Status();
                status.setStatusId(resultSet.getInt("status_id"));
                status.setLabel(resultSet.getString("label"));

                repairOrder.setRepairId(resultSet.getInt("repair_id"));
                repairOrder.setClient(client);
                repairOrder.setProblemDescription(resultSet.getString("problem_description"));
                repairOrder.setDepositDate(resultSet.getDate("deposit_date").toLocalDate());
                repairOrder.setAppointmentDate(getNullableLocalDate(resultSet, "appointment_date"));
                repairOrder.setEstimateAmount(resultSet.getBigDecimal("estimate_amount"));
                repairOrder.setAcceptedEstimate(resultSet.getBoolean("accepted_estimate"));
                repairOrder.setLaborHours(resultSet.getBigDecimal("labor_hours"));
                repairOrder.setStatus(status);
                repairOrder.setNotes(resultSet.getString("notes"));

                repairOrders.add(repairOrder);
            }

            return repairOrders;

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la recuperation des reparations.", exception);
        }
    }

    @Override
    public void insert(RepairOrder repairOrder) throws DataAccessException {
        String sql = """
                INSERT INTO repair_order
                (client_id, problem_description, deposit_date, appointment_date,
                 estimate_amount, accepted_estimate, labor_hours, status_id, notes)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, repairOrder.getClient().getClientId());
            statement.setString(2, repairOrder.getProblemDescription());
            statement.setDate(3, Date.valueOf(repairOrder.getDepositDate()));
            setNullableDate(statement, 4, repairOrder.getAppointmentDate());
            statement.setBigDecimal(5, repairOrder.getEstimateAmount());
            statement.setBoolean(6, repairOrder.getAcceptedEstimate());
            setNullableBigDecimal(statement, 7, repairOrder.getLaborHours());
            statement.setInt(8, repairOrder.getStatus().getStatusId());
            setNullableString(statement, 9, repairOrder.getNotes());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de l'ajout de la reparation.", exception);
        }
    }

    @Override
    public void update(RepairOrder repairOrder) throws DataAccessException {
        String sql = """
                UPDATE repair_order
                SET client_id = ?,
                    problem_description = ?,
                    deposit_date = ?,
                    appointment_date = ?,
                    estimate_amount = ?,
                    accepted_estimate = ?,
                    labor_hours = ?,
                    status_id = ?,
                    notes = ?
                WHERE repair_id = ?
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, repairOrder.getClient().getClientId());
            statement.setString(2, repairOrder.getProblemDescription());
            statement.setDate(3, Date.valueOf(repairOrder.getDepositDate()));
            setNullableDate(statement, 4, repairOrder.getAppointmentDate());
            statement.setBigDecimal(5, repairOrder.getEstimateAmount());
            statement.setBoolean(6, repairOrder.getAcceptedEstimate());
            setNullableBigDecimal(statement, 7, repairOrder.getLaborHours());
            statement.setInt(8, repairOrder.getStatus().getStatusId());
            setNullableString(statement, 9, repairOrder.getNotes());
            statement.setInt(10, repairOrder.getRepairId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la modification de la reparation.", exception);
        }
    }

    @Override
    public boolean hasLinkedInvoice(int repairId) throws DataAccessException {
        String sql = "SELECT COUNT(*) AS invoice_count FROM invoice WHERE repair_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, repairId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("invoice_count") > 0;
            }

            return false;

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la verification des factures liees.", exception);
        }
    }

    @Override
    public void delete(int repairId) throws DataAccessException {
        String sql = "DELETE FROM repair_order WHERE repair_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, repairId);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la suppression de la reparation.", exception);
        }
    }

    @Override
    public void deleteWithLinkedInvoices(int repairId) throws DataAccessException {
        String deleteInvoicesSql = "DELETE FROM invoice WHERE repair_id = ?";
        String deleteRepairSql = "DELETE FROM repair_order WHERE repair_id = ?";

        try {
            connection.setAutoCommit(false);

            PreparedStatement deleteInvoicesStatement = connection.prepareStatement(deleteInvoicesSql);
            deleteInvoicesStatement.setInt(1, repairId);
            deleteInvoicesStatement.executeUpdate();

            PreparedStatement deleteRepairStatement = connection.prepareStatement(deleteRepairSql);
            deleteRepairStatement.setInt(1, repairId);
            deleteRepairStatement.executeUpdate();

            connection.commit();

        } catch (SQLException exception) {
            rollbackQuietly();
            throw new DataAccessException(
                    "Erreur lors de la suppression de la reparation et des factures liees.",
                    exception
            );

        } finally {
            resetAutoCommit();
        }
    }

    private LocalDate getNullableLocalDate(ResultSet resultSet, String columnName) throws SQLException {
        Date sqlDate = resultSet.getDate(columnName);

        if (sqlDate == null) {
            return null;
        }

        return sqlDate.toLocalDate();
    }

    private void setNullableDate(PreparedStatement statement, int position, LocalDate value) throws SQLException {
        if (value == null) {
            statement.setNull(position, Types.DATE);
        } else {
            statement.setDate(position, Date.valueOf(value));
        }
    }

    private void setNullableBigDecimal(PreparedStatement statement, int position, BigDecimal value) throws SQLException {
        if (value == null) {
            statement.setNull(position, Types.DECIMAL);
        } else {
            statement.setBigDecimal(position, value);
        }
    }

    private void setNullableString(PreparedStatement statement, int position, String value) throws SQLException {
        if (value == null || value.isBlank()) {
            statement.setNull(position, Types.VARCHAR);
        } else {
            statement.setString(position, value);
        }
    }

    private void rollbackQuietly() throws DataAccessException {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors du rollback de la suppression.", exception);
        }
    }

    private void resetAutoCommit() throws DataAccessException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la reinitialisation de la transaction.", exception);
        }
    }
}
