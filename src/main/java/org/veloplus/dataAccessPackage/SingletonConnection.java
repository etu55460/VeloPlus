package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {

    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/veloplus_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Tigrou007";

    private SingletonConnection() {
    }

    public static Connection getInstance() throws DataAccessException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }

            return connection;

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur de connexion à la base de données.", exception);
        }
    }

    public static void closeConnection() throws DataAccessException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la fermeture de la connexion.", exception);
        }
    }
}
