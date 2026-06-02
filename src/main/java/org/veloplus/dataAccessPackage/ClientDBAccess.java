package org.veloplus.dataAccessPackage;

import org.veloplus.exceptionPackage.DataAccessException;
import org.veloplus.modelPackage.Category;
import org.veloplus.modelPackage.Client;
import org.veloplus.modelPackage.ClientKind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDBAccess implements ClientDataAccess {

    private Connection connection;

    public ClientDBAccess() throws DataAccessException {
        connection = SingletonConnection.getInstance();
    }

    @Override
    public ArrayList<Client> getAll() throws DataAccessException {
        ArrayList<Client> clients = new ArrayList<>();

        String sql = """
            SELECT
                c.client_id,
                c.first_name,
                c.last_name,
                c.phone,
                c.email,
                c.address,
                ck.kind_id,
                ck.label AS kind_label,
                cat.category_id,
                cat.label AS category_label
            FROM client c
            JOIN client_kind ck ON c.kind_id = ck.kind_id
            JOIN category cat ON c.category_id = cat.category_id
            ORDER BY c.last_name, c.first_name
            """;

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Client client = new Client();

                client.setClientId(resultSet.getInt("client_id"));
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setPhone(resultSet.getString("phone"));
                client.setEmail(resultSet.getString("email"));
                client.setAddress(resultSet.getString("address"));

                ClientKind clientKind = new ClientKind();
                clientKind.setKindId(resultSet.getInt("kind_id"));
                clientKind.setLabel(resultSet.getString("kind_label"));

                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setLabel(resultSet.getString("category_label"));

                client.setClientKind(clientKind);
                client.setCategory(category);

                clients.add(client);
            }

            return clients;

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la récupération des clients.", exception);
        }
    }

    @Override
    public void insert(Client client) throws DataAccessException {
        String sql = """
                INSERT INTO client
                (first_name, last_name, phone, email, address, kind_id, category_id)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getAddress());
            statement.setInt(6, client.getClientKind().getKindId());
            statement.setInt(7, client.getCategory().getCategoryId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de l'ajout du client.", exception);
        }
    }

    @Override
    public void update(Client client) throws DataAccessException {
        String sql = """
                UPDATE client
                SET first_name = ?,
                    last_name = ?,
                    phone = ?,
                    email = ?,
                    address = ?,
                    kind_id = ?,
                    category_id = ?
                WHERE client_id = ?
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getEmail());
            statement.setString(5, client.getAddress());
            statement.setInt(6, client.getClientKind().getKindId());
            statement.setInt(7, client.getCategory().getCategoryId());
            statement.setInt(8, client.getClientId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la modification du client.", exception);
        }
    }

    @Override
    public void delete(int clientId) throws DataAccessException {
        String sql = """
                DELETE FROM client
                WHERE client_id = ?
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, clientId);

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DataAccessException("Erreur lors de la suppression du client.", exception);
        }
    }
}
