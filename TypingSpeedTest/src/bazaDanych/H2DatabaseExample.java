package bazaDanych;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class H2DatabaseExample {
    public static void main(String[] args) {
        // Adres URL bazy danych H2 online
        String jdbcUrl = "jdbc:h2:~/tstData";
        String username = "artur";
        String password = "";

        try {
            // Tworzenie połączenia
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Tworzenie zapytania SQL
            String query = "SELECT * FROM `wyniki`;"; // Zmień na nazwę swojej tabeli

            // Tworzenie obiektu Statement
            Statement statement = connection.createStatement();

            // Wykonanie zapytania i pobranie wyników
            ResultSet resultSet = statement.executeQuery(query);

            // Przetwarzanie wyników
            while (resultSet.next()) {
                // Wypisywanie zawartości kolumn
                System.out.println("Kolumna1: " + resultSet.getString("nazwaKolumny1"));
                System.out.println("Kolumna2: " + resultSet.getString("nazwaKolumny2"));
                // Kontynuuj wypisywanie kolejnych kolumn, jeśli istnieją
            }

            // Zamykanie zasobów
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}