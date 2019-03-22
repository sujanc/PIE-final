package com.stackroute.pie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class XMLtoJSON {


    static String line = "";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        String[] link = {"UserAnusha.xml", "Userkavya.xml"}; //url of xml file that is storing the data
        String[] users = {"Anusha", "kavya"};
        String[] strr = new String[2];

        for (int i = 0; i < users.length; i++) {

            int cnt = 0;

            //parsing through the xml file line by line and storing it in a string

            BufferedReader br = new BufferedReader(new FileReader(link[i]));

            while ((line = br.readLine()) != null) {

                if (cnt > 2)
                    strr[i] += line;
                cnt++;

            }


            //setting up the JDBC connection for the mysql database
            String dbURL = "jdbc:mysql://localhost:3306/policyDB";
            String dbUser = "root";
            String dbPass = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            // inserting data from xml file into UserPolicies table in the policyDB database

            String sql = "INSERT INTO " + users[i] + "(id,Policy) values(?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, 1);
            statement.setString(2, strr[i].substring(0, strr[i].length() - 10));

            statement.executeUpdate();
        }
    }
}