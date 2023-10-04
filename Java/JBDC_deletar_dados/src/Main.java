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
        PreparedStatement st = null;

        try {
            connection = DB.getConnection();

            st = connection.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE "
                    + "Id = ?");


            st.setInt(1, 5);

            int rows = st.executeUpdate();

            System.out.println("Done! Rows affected " + rows);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }


    }
}