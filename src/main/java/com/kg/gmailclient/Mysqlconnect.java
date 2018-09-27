package com.kg.gmailclient;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @desc A singleton database access class for MySQL
 * @author Ramindu
 */
public final class Mysqlconnect {
    public Connection conn;
    private Statement statement;
    public static Mysqlconnect db;
    private Mysqlconnect() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "travellog";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized Mysqlconnect getDbCon() {
        if ( db == null ) {
            db = new Mysqlconnect();
        }
        return db;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException {
        System.out.println(query);
        statement = db.conn.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        System.out.println("result"+resultset);
        return resultset;
       
        
    }
     public List<Object> resultSetToArrayList(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
 
        ResultSetMetaData md = rs.getMetaData();
        // System.out.println("meta data is ::"+md);
        int columns = md.getColumnCount();
        // System.out.println("value in column::"+columns);
        
        ArrayList list = new ArrayList();
        
        while (rs.next()){
            HashMap<Object, Object> row = new HashMap<>(columns);
           for(int i=1; i<=columns; ++i){
            row.put(md.getColumnName(i),rs.getObject(i));
           }
            list.add(row);
        }
 
       return list;
      }
       public int insert(String insertQuery) throws SQLException {
        System.out.println(insertQuery);
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
    }
    public int delete(String insertQuery) throws SQLException {
        System.out.println(insertQuery);
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
    }
        public int update(String insertQuery) throws SQLException {
            System.out.println(insertQuery);
            statement = db.conn.createStatement();
            int result = statement.executeUpdate(insertQuery);
            return result;
     
        }
    

}