package model.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

        try {
            String query = "CREATE TABLE IF NOT EXISTS `nizekproject`.`user` (\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`name` VARCHAR(45) NOT NULL,\n" +
                    "`lastname` VARCHAR(45) NOT NULL,\n" +
                    "`email` VARCHAR(45) NOT NULL,\n" +
                    "`password` VARCHAR(45) NOT NULL,\n" +
                    "`role` VARCHAR(45) NOT NULL,\n" +
                    "PRIMARY KEY (`id`),\n" +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,\n" +
                    "UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);";

            connection.createStatement().executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException("Error creating users table: " + e.getMessage());
        }

        try {
            String createUserProjectTableQuery = "CREATE TABLE IF NOT EXISTS `nizekproject`.`user_project` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `user_id` INT NOT NULL,\n" +
                    "  `project_id` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  FOREIGN KEY (`project_id`) REFERENCES `project` (`idproject`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");";

            connection.createStatement().executeUpdate(createUserProjectTableQuery);

        } catch (SQLException e) {
            throw new RuntimeException("Error creating user_project table: " + e.getMessage());
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

