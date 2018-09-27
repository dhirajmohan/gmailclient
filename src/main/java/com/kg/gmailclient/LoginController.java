package com.kg.gmailclient;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginController
 */
@WebServlet("/user")
public class LoginController extends HttpServlet {
    ArrayList<Login> login = new ArrayList<Login>();

    String action ="";
    String mode = "";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet doGet");
        String action = req.getParameter("action");
        System.out.println(action);

        try {

            switch (action) {

            case "delete":
                System.out.println("delete engaged");
                deleteBook(req, resp);
                break;
            case "validateuser":
               
                validateUser(req, resp);
                break;
            case "edit":
                editBook(req, resp);
                break;
            case "update":
                saveorupdatefunc(req, resp);
                break;        
            default:
                user(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void validateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {

        System.out.println("validate user called");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + password);

        Validation userController = new Validation();
        int a = userController.getLogin(username);
        if (a == 1) {
            ServletContext context = getServletContext();
            context.setAttribute("username", username);
            resp.sendRedirect("log.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }

    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {

        String username = req.getParameter("username");
        System.out.println("88888888888welcome to delete book88888888888");
        String sql = "DELETE FROM login WHERE  (username='" + username + "')";
        int resultSet = Mysqlconnect.getDbCon().delete(sql);
        req.setAttribute("login", login);
        RequestDispatcher dis = req.getRequestDispatcher("/user?action=");
        dis.forward(req, resp);

    }

    private void editBook(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        mode = "update";
        RequestDispatcher view = req.getRequestDispatcher("login.jsp");
        view.forward(req, resp);

    }

    private void saveorupdatefunc(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        if (mode != "update") {
            System.out.println("insert called");

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            System.out.println(username + password);
            String sql = "INSERT INTO login (username, password) VALUES ('" + username + "'" + ",'" + password + "')";
            int resultSet = Mysqlconnect.getDbCon().insert(sql);
            System.err.println("no of rows inserted" + resultSet);
        } else {
            System.out.println("update called");

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String sql = "UPDATE login SET username='" + username + "',password='" + password + "' WHERE username='"
                    + username + "'";
            int resultSet = Mysqlconnect.getDbCon().update(sql);
            System.out.println("no of rows updated" + resultSet);
            mode = "";

        }
        RequestDispatcher dis = req.getRequestDispatcher("/user?action=");
        dis.forward(req, resp);
    }

    protected void user(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        String query = "select * from login";
        List resultSet = (List) Mysqlconnect.getDbCon().resultSetToArrayList(query);
        // List list=MysqlConnect.getDbCon().resultSetToArrayList(MysqlConnect.getDbCon().query(query));
        // books.clear();
        // while (resultSet.next()) {
        // 	books.add(new Book(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("price")));
        // }
        login.clear();
        login.addAll((Collection<? extends Login>) resultSet);
        req.setAttribute("login", login);
        RequestDispatcher dis = req.getRequestDispatcher("list.jsp");
        dis.forward(req, resp);

    }
}
