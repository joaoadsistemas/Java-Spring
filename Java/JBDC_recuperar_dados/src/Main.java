import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Connection connection = null;
        Statement st = null;
        ResultSet resultSet = null;


        try {

            connection = DB.getConnection();

            st = connection.createStatement();

            resultSet = st.executeQuery("select * from department");

            while(resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(resultSet);
            DB.closeConnection();
        }



    }
}