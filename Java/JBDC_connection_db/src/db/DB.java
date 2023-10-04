package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {


    private static Connection connection = null;

    // Conectar com o banco de dados
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Pega as propriedades do banco de dados
                Properties properties = loadProperties();
                // peguei o valor que esta na propriedade dburl do db.properties
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return connection;
    }

    // Fechar conexão
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }


    // Load no arquivo banco de dados os arquivos do db.properties
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }


}
