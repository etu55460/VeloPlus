package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDBAccess implements StatusDataAccess {

    private Connection connection;

    public StatusDBAccess() throws DataAccessException {
        connection = SingletonConnection.getInstance();
    }

    @Override
    public ArrayList<Status> getAll() throws DataAccessException {
        ArrayList<Status> statuses = new ArrayList<>();

        String sql = "SELECT status_id, label FROM status ORDER BY label";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Status status = new Status();

                status.setStatusId(resultSet.getInt("status_id"));
                status.setLabel(resultSet.getString("label"));

                statuses.add(status);
            }

            return statuses;

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la récupération des statuts.", exception);
        }
    }
}