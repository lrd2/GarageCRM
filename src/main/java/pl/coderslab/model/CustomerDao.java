package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public static void saveToDB(Connection conn, Customer customer) throws SQLException {
        if (customer.getId() == 0) {
            String sql = "INSERT INTO customers(first_name, last_name, birthday) VALUES (?,?,?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, customer.getBirthday());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                customer.setId(rs.getInt(1));
            }
        } else {
            String sql = "UPDATE customers SET first_name = ?, last_name = ?, birthday = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, customer.getBirthday());
            preparedStatement.setInt(4, customer.getId());
            preparedStatement.executeUpdate();
        }
    }

    public static List<Customer> loadAllCustomers(Connection conn, int limit) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers limit ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, limit);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            customers.add(getCustomerFromResultSet(conn, resultSet));
        }
        return customers;
    }

    public static Customer loadCustomerById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM customers where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return getCustomerFromResultSet(conn, resultSet);
        }
        return null;
    }

    private static Customer getCustomerFromResultSet(Connection conn, ResultSet resultSet) throws SQLException {
        Customer loadedCustomer = new Customer();
        loadedCustomer.setId(resultSet.getInt("id"));
        loadedCustomer.setFirstName(resultSet.getString("first_name"));
        loadedCustomer.setLastName(resultSet.getString("last_name"));
        loadedCustomer.setBirthday(resultSet.getDate("birthday"));
        return loadedCustomer;
    }

    public static void delete(Connection conn, Customer customer) throws SQLException {
        if (customer.getId() != 0) {
            String sql = "DELETE FROM customers WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();
            customer.setId(0);
        }
    }
    public static void delete(Connection conn, int id) throws SQLException {
            String sql = "DELETE FROM customers WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

    }

}