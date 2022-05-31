package com.example.musimatch.services;

import android.os.StrictMode;
import android.util.Log;

import com.example.musimatch.models.PostModel;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDB {
    Connection connection;
    String username, pass, ip, port, database;

    public final static ConnectToDB instance = new ConnectToDB();

    private ConnectToDB() {
        this.connection = connectionClass();
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection connectionClass() {
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
            ConnectionUrl = "jdbc:mysql://bamba.cs.colman.ac.il:3306/MusiMatch?user=cs115&password=Move@115&useUnicode=true&characterEncoding=UTF-8";

            connection = DriverManager.getConnection(ConnectionUrl, username, pass);
        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}