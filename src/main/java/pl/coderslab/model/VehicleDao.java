package pl.coderslab.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    public static void saveToDB(Connection conn, Vehicle vehicle) throws SQLException {
        if (vehicle.getId() == 0) {
            String sql = "INSERT INTO vehicles(model, make, manufactured, next_review, registration_number, customer_id) VALUES (?,?,?,?,?,?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, vehicle.getModel());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setDate(3, vehicle.getManufactured());
            preparedStatement.setDate(4, vehicle.getNextReview());
            preparedStatement.setString(5, vehicle.getRegistrationNumber());
            if (vehicle.getOwner() != null) {
            preparedStatement.setInt(6, vehicle.getOwner().getId());
            } else {
                preparedStatement.setInt(6, 999);
            }
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                vehicle.setId(rs.getInt(1));
            }
        } else {
            String sql = "UPDATE vehicles SET model = ?, make = ?, manufactured = ?, next_review = ?, registration_number = ?, customer_id = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getModel());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setDate(3, vehicle.getManufactured());
            preparedStatement.setDate(4, vehicle.getNextReview());
            preparedStatement.setString(5, vehicle.getRegistrationNumber());
            if (vehicle.getOwner() != null) {
                preparedStatement.setInt(6, vehicle.getOwner().getId());
            } else {
                preparedStatement.setInt(6, 999);
            }
            preparedStatement.setInt(7, vehicle.getId());
            preparedStatement.executeUpdate();
        }
    }


    public static Vehicle loadVehicleById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM vehicles where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return getVehicleFromResultSet(conn, resultSet);
        }
        return null;
    }

    public static List<Vehicle> loadVehiclesByCustomerId(Connection conn, int id) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles where customer_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vehicles.add(getVehicleFromResultSet(conn, resultSet));
        }
        return vehicles;
    }

    public static List<Vehicle> loadAllVehicles(Connection conn, int limit) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles limit ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, limit);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            vehicles.add(getVehicleFromResultSet(conn, resultSet));
        }
        return vehicles;
    }

    private static Vehicle getVehicleFromResultSet(Connection conn, ResultSet resultSet) throws SQLException {
        Vehicle loadedVehicle = new Vehicle();
        loadedVehicle.setId(resultSet.getInt("id"));
        loadedVehicle.setMake(resultSet.getString("make"));
        loadedVehicle.setModel(resultSet.getString("model"));
        loadedVehicle.setManufactured(resultSet.getDate("manufactured"));
        loadedVehicle.setNextReview(resultSet.getDate("next_review"));
        loadedVehicle.setRegistrationNumber(resultSet.getString("registration_number"));
        loadedVehicle.setOwner(CustomerDao.loadCustomerById(DbUtil.getConn(), resultSet.getInt("customer_id")));
        return loadedVehicle;
    }

    public static void delete(Connection conn, Vehicle vehicle) throws SQLException {
        if (vehicle.getId() != 0) {
            String sql = "DELETE FROM vehicles WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, vehicle.getId());
            preparedStatement.executeUpdate();
            vehicle.setId(0);
        }
    }
    public static void delete(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

}
//
//    private int id;
//    private String model;
//    private String make;
//    private int manufactured;
//    private String registrationNumber;
//    private Timestamp nextReview;