package model.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static DataBase instance = null;

    private Connection connection;


    private DataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nizekproject", "root", "sh4966014");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataBase getInstance() {
        if (instance == null) instance = new DataBase();
        return instance;
    }
    public  Connection getConnection() {
        return connection;
    }

}

