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

        try {
            String query = "CREATE TABLE IF NOT EXISTS `nizekproject`.`project` (\n" +
                    "  `idproject` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `description` VARCHAR(100) NULL,\n" +
                    "  PRIMARY KEY (`idproject`));";

            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating project table: " + e.getMessage());
        }
    }

    public static DataBase getInstance() {
        if (instance == null) instance = new DataBase();
        return instance;
    }

    public void createProjectTable() {

    }

    public  Connection getConnection() {
        return connection;
    }

}

