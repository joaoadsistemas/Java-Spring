import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Connection connection = null;
        Statement st = null;

        try {
            connection = DB.getConnection();

            // Bloqueia as atualizações automáticas
            connection.setAutoCommit(false);

            st = connection.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            int x = 1;
            if (x < 2) {
                throw new SQLException("Fake Error");
            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");


            // Faz as alterações
            connection.commit();

            System.out.println("rows1 " + rows1);
            System.out.println("rows2 " + rows2);

        } catch (SQLException e) {

            try {

                // Volta para o banco de dados antes da alteração
                connection.rollback();
                throw new DbException("Transaction rolled back! Coused by: " + e.getMessage());
            } catch (SQLException ex) {

                throw new DbException("Error trying to rollback! Caused by: " + ex.getMessage());
            }

        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }


    }
}