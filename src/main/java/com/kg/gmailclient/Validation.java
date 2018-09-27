package com.kg.gmailclient;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * validation
 */

public class Validation extends HttpServlet{
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("log.jsp");
    }
    
    public int getLogin(String username) throws SQLException {
     int a=0;
        System.out.println("username  " + username);

       
        String sql = "SELECT username,password from login WHERE username='"+username+"'";
        ResultSet res = Mysqlconnect.getDbCon().query(sql);
       
        // ResultSetMetaData md = (ResultSetMetaData) res.getMetaData();
        // int columns = md.getColumnCount();
        // return columns;
        // System.out.println("***************");
        while (res.next()) {
				System.out.println("returned values"+( res.getString("username")+","+res.getString("password")));
            if(res.getString("username")!=null)
            {
                a=1;
            }
            }

        return a;
    }
	
	
    
}