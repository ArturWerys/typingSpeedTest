package bazaDanych;

import java.sql.*;

public class NumberOfTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Utwórz połączenie
            conn = DriverManager.getConnection("jdbc:h2:tstData", "artur", "");

            // Utwórz obiekt instrukcji
            stmt = conn.createStatement();

            // Wykonaj zapytanie SQL
            rs = stmt.executeQuery("SELECT `ID` FROM wyniki");

            // Przetwórz wyniki zapytania
            while (rs.next()) {
                // Wyciągnij wartość z kolumny "ID"
                int idValue = rs.getInt("ID");
                // Tutaj możesz wykonać dowolną operację na wartości z kolumny "ID"
                System.out.println("Wartość kolumny 'ID': " + idValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Zamykanie ResultSet, Statement i Connection
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
