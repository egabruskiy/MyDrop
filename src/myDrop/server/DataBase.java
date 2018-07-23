package myDrop.server;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private static Connection connection;
    private static Statement stmt;


    public static void addFile(String fileName) throws SQLException {
        stmt.executeUpdate("INSERT INTO files (name) VALUES ('"+ fileName + "');");
    }
    public static void createTableEx() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS files (\n" +
                "        id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "        name  TEXT\n" +
                "        );");
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:dbTest.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList showAllData() throws SQLException {
        ResultSet resultSet =  stmt.executeQuery( " SELECT * FROM files");
        ArrayList fileList = new ArrayList();
        while ((resultSet.next())){
            fileList.add(resultSet.getString("name") );
//            System.out.println(resultSet.getString("name"));
        }


        return fileList;

    }

}






