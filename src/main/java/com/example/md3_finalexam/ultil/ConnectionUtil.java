package com.example.md3_finalexam.ultil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/md3_finalexam";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";

    public Connection mConnection = null;
    public PreparedStatement mPreparedStatement = null;
    public ResultSet mResultSet = null;

    public Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace(System.err);
        }
        return conn;
    }

    public void open(){
        mConnection = getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    public void close() throws Exception {
        if (mResultSet != null) {
            mResultSet.close();
        }
        if (mPreparedStatement != null) {
            mPreparedStatement.close();
        }
        if (mConnection != null) {
            mConnection.close();
        }
    }
}
