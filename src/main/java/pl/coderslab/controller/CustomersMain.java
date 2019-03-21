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
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/customers_main")
public class CustomersMain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        if (request.getParameter("activity").equals("add")) {
            addCustomer(request);
        }

        if (request.getParameter("activity").equals("delete")) {
            deleteCustomer(request);
        }

        if (request.getParameter("activity").equals("editRequest")) {
            editRequest(request);
        }

        if (request.getParameter("activity").equals("editedValues")) {
            editCustomer(request);
        }

        if (request.getParameter("activity").equals("details")) {
            showCustomer(request, response);
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        try {
            List<Customer> customers = CustomerDao.loadAllCustomers(DbUtil.getConn(), 10000);
            request.setAttribute("Customers", customers);
            getServletContext().getRequestDispatcher("/customers/customers_main.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCustomer(HttpServletRequest request) {
        Customer customer = new Customer();
        String firstName = request.getParameter("firstName");
        customer.setFirstName(firstName);
        String lastName = request.getParameter("lastName");
        customer.setLastName(lastName);
        String birthday = request.getParameter("birthday");
        if (!birthday.equals("")) {
            Date birthdayTs = Date.valueOf(birthday);
            customer.setBirthday(birthdayTs);
        }
        try {
            CustomerDao.saveToDB(DbUtil.getConn(), customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        try {
            CustomerDao.delete(DbUtil.getConn(),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void editRequest(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        Customer customer;
        try {
            customer = CustomerDao.loadCustomerById(DbUtil.getConn(), id);
            request.setAttribute("CustomerToEdit", customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editCustomer(HttpServletRequest request) {
        try {
            Customer editedCustomer = CustomerDao.loadCustomerById(DbUtil.getConn(), Integer.valueOf(request.getParameter("id")));
            editedCustomer.setFirstName(request.getParameter("firstName"));
            editedCustomer.setLastName(request.getParameter("lastName"));
            CustomerDao.saveToDB(DbUtil.getConn(), editedCustomer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        try {
            Customer customer = CustomerDao.loadCustomerById(DbUtil.getConn(), id);
            List<Vehicle> vehicles = VehicleDao.loadVehiclesByCustomerId(DbUtil.getConn(), id);
            for (Vehicle vehicle: vehicles) {
                System.out.println(vehicle.getMake());
            }
            request.setAttribute("Customer", customer);
            request.setAttribute("Vehicles", vehicles);
            getServletContext().getRequestDispatcher("/customers/customer_details.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
