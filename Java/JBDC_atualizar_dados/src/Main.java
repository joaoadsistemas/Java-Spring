import db.DB;
import db.DbException;

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
                 "UPDATE seller "
                 + "SET BaseSalary = BaseSalary + ? "
                 + "WHERE "
                 + "(DepartmentId = ?)");



         st.setDouble(1, 200);
         st.setInt(2, 2);

         int rowsAffected = st.executeUpdate();
         System.out.println("Done! " + rowsAffected);

     } catch (SQLException e) {
        e.printStackTrace();
     } finally {
         DB.closeStatement(st);
         DB.closeConnection();
     }


    }
}