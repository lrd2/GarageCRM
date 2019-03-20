package pl.coderslab.controller;

import pl.coderslab.model.Customer;
import pl.coderslab.model.CustomerDao;
import pl.coderslab.model.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customers_main")
public class CustomersMain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Customer> customers = CustomerDao.loadAllCustomers(DbUtil.getConn(),10000);
            request.setAttribute("Customers", customers);
            getServletContext().getRequestDispatcher("/customers/customers_main.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
