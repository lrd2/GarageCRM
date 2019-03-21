package pl.coderslab.controller;

import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/vehicles_main")
public class VehiclesMain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        if (request.getParameter("activity").equals("add")) {
            addVehicle(request);
        }

        if (request.getParameter("activity").equals("delete")) {
            deleteVehicle(request);
        }

        if (request.getParameter("activity").equals("editRequest")) {
            editRequest(request);
        }

        if (request.getParameter("activity").equals("editedValues")) {
            editVehicle(request);
        }

        doGet(request, response);
    }

    private void editVehicle(HttpServletRequest request) {
        try {
            Vehicle editedVehicle = VehicleDao.loadVehicleById(DbUtil.getConn(), Integer.valueOf(request.getParameter("id")));
            editedVehicle.setModel(request.getParameter("model"));
            editedVehicle.setMake(request.getParameter("make"));
            editedVehicle.setRegistrationNumber(request.getParameter("registrationNumber").toUpperCase());
            if (!request.getParameter("customer").equals("")) {
                editedVehicle.setOwner(CustomerDao.loadCustomerById(DbUtil.getConn(), Integer.valueOf(request.getParameter("customer"))));
            }
            VehicleDao.saveToDB(DbUtil.getConn(), editedVehicle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        try {
            List<Vehicle> vehicles = VehicleDao.loadAllVehicles(DbUtil.getConn(), 10000);
            request.setAttribute("Vehicles", vehicles);
            List<Customer> customers = CustomerDao.loadAllCustomers(DbUtil.getConn(), 10000);
            customers.sort(Comparator.comparing(Customer::getLastName));
            request.setAttribute("Customers", customers);
            getServletContext().getRequestDispatcher("/vehicles/vehicles_main.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addVehicle(HttpServletRequest request) {
        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setModel(request.getParameter("model"));
            vehicle.setMake(request.getParameter("make"));
            if (!request.getParameter("manufactured").equals("")) {
                vehicle.setManufactured(Date.valueOf(request.getParameter("manufactured")));
            }
            if (!request.getParameter("nextReview").equals("")) {
                vehicle.setNextReview(Date.valueOf(request.getParameter("nextReview")));
            }
            vehicle.setRegistrationNumber(request.getParameter("registrationNumber").toUpperCase());
            if (!request.getParameter("customer").equals("")) {
                vehicle.setOwner(CustomerDao.loadCustomerById(DbUtil.getConn(), Integer.valueOf(request.getParameter("customer"))));
            }
            VehicleDao.saveToDB(DbUtil.getConn(), vehicle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteVehicle(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        try {
            VehicleDao.delete(DbUtil.getConn(),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editRequest(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        Vehicle vehicle;
        try {
            vehicle = VehicleDao.loadVehicleById(DbUtil.getConn(), id);
            request.setAttribute("VehicleToEdit", vehicle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
