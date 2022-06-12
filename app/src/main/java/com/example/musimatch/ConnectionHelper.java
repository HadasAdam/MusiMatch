package com.example.musimatch;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection conn;
    String username, pass, ip, port, database;

    public Connection connectionClass() {
        ip = "bamba.cs.colman.ac.il";
        database = "MusiMatch";
        port = "3306";
        username = "cs115";
        pass = "Move@115";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectionURL = "jdbc:mysql://" + ip + ":" + port + ";" + "databasename=" + database
            + "; user=" + username + "; password=" + pass + ";";

            connection = DriverManager.getConnection(connectionURL);

        } catch (Exception e) {
            Log.e("error: ", e.getMessage());
        }

        return connection;
    }
}
