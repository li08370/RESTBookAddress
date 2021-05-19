package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class UpdateDataBase {
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;
    private static String sqlDomain = "localhost:3360";

    public static String addContact(Address address) {
        String response = "";
        try {
            initializeDB();
            //It was really cool debugging for 4 hours, and it was a missing ) in the String below.
            preparedStatement = connect.prepareStatement("insert into contactdb.contacts values(default, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, address.getFirst_name());
            preparedStatement.setString(2, address.getLast_name());
            preparedStatement.setString(3, address.getPhone_number());
            preparedStatement.setString(4, address.getAddress());
            preparedStatement.executeUpdate();
            response = "ok";
        } catch (ClassNotFoundException | SQLException e) {
            response = e.getMessage();
        } finally {
            close();
        }
        return response;
    }

    private static void close() {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initializeDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://" + sqlDomain + "/addressDB", "root", "Teajay2023!");
        statement = connect.createStatement();

    }
    public static ArrayList<Address> listContacts() {
        ArrayList<Address> contacts = new ArrayList<Address>();
        try {
            initializeDB();
            ResultSet results = statement.executeQuery("select * from addressDB.contactT");
            while(results.next()) {
                Address contact = createTempAddress(results);
                contacts.add(contact);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return contacts;

    }

    private static Address createTempAddress(ResultSet results) throws SQLException {
        Address tempA = new Address(
                results.getString("firstName"),
                results.getString("firstName"),
                results.getString("phonenumber"),
                results.getString("address"));
              //  results.getString("address"));
        return tempA;
    }
}