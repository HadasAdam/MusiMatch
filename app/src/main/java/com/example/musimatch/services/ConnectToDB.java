package com.example.musimatch.services;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

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
            String ConnectionUrl = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
//                ConnectionUrl = "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+
//                        "databasename="+ database+";user="+username+";password="+pass+";";
                ConnectionUrl = "jdbc:mysql:bamba.cs.colman.ac.il:3306/MusiMatch";
                connection = DriverManager.getConnection(ConnectionUrl, username, pass );
            } catch (Exception ex) {
                Log.e("Error", ex.getMessage());
            }
            return connection;
        }
}