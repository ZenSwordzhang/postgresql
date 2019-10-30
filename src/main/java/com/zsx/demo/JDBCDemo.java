package com.zsx.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo {

    public static void main(String[] args) {
        getInfo();
    }

    public static void getInfo() {
        Connection coon = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            coon = DriverManager.getConnection("jdbc:postgresql://localhost:5433/mydb10", "postgres", "1234");
            coon.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = coon.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id, name, age, address, salary, join_date \"joinDate\" FROM company" );
            while (rs.next()) {
                String  name = rs.getString("name");
                System.out.println( "NAME = " + name );
                System.out.println();
            }
            rs.close();
            stmt.close();
            coon.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}
