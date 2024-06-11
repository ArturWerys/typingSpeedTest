package bazaDanych_wykresy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintTable {

    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:h2:tstData", "artur",
                    "");

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM `wyniki`");

            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + " | "); 
                }
                System.out.println(); 
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
