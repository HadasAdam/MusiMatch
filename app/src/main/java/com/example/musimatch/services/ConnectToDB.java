package com.example.musimatch.services;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDB {
        Connection con;
        String username, pass,ip,port,database;

        public Connection connectionClass() {
            ip = "bamba.cs.colman.ac.il";
            database = "MusiMatch";
            username = "cs115";
            pass = "Move@115";
            port = "3306";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            String ConnectionUrl;

            try {
                Class.forName("com.mysql.jdbc.Driver");
//                ConnectionUrl = "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+"database="+ database+";user="+username+";password="+pass+";";
//                ConnectionUrl = "jdbc:mysql://bamba.cs.colman.ac.il:3306/MusiMatch";
                ConnectionUrl = "jdbc:mysql://bamba.cs.colman.ac.il:3306/MusiMatch?user=cs115&password=Move@115&useUnicode=true&characterEncoding=UTF-8";

                connection = DriverManager.getConnection(ConnectionUrl, username, pass );
            } catch (Exception ex) {
                Log.e("Error", ex.getMessage());
            }
            return connection;
        }





//    public class SQLDatabaseConnection {

        // Connect to your database.
        // Replace server name, username, and password with your credentials
//        public ResultSet connectionClass() {
//            String connectionUrl =
//                    "jdbc:sqlserver://bamba.cs.colman.ac.il:3306;"
//                            + "database=MusiMatch;"
//                            + "user=cs115;"
//                            + "password=Move@115;"
//                            + "encrypt=true;"
//                            + "trustServerCertificate=false;"
//                            + "loginTimeout=30;";
//            ResultSet resultSet = null;
//
//            try (Connection connection = DriverManager.getConnection(connectionUrl);
//                 Statement statement = connection.createStatement();) {
//
//                // Create and execute a SELECT SQL statement.
//                String selectSql = "SELECT * from 'Users'";
//                resultSet = statement.executeQuery(selectSql);
//
//                // Print results from select statement
//                while (resultSet.next()) {
//                    Log.d("connect", resultSet.getString(1));
//                    System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
//                }
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//                Log.e("connect", e.getMessage());
//            }
//            return resultSet;
//        }
//    }
}
