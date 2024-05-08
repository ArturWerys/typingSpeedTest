package bazaDanych;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatingTable{

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(	"jdbc:h2:tstData", "artur", "");
	
		     Statement statement = conn.createStatement();
		     // Usuwanie tabeli - kolejne uruchomienie nie wygeneruje bledu:
		     statement.executeUpdate("DROP TABLE IF EXISTS `wyniki`;");
				
			 // Tworzenie tabeli o okreslonej strukturze danych
		     statement.executeUpdate("CREATE TABLE `wyniki` ("+
						  "`Id` int(6) unsigned NOT NULL auto_increment,"+
						  "`data` date default NULL,"+
                          "`hour` VARCHAR(5) default NULL,"+ // VARCHAR dla godziny
						  "`Correct words` float default NULL,"+
						  "PRIMARY KEY  (`Id`)"+
						") ;");
				
				// Dane poczatkowe
				statement.executeUpdate("INSERT INTO `wyniki` VALUES (1,'2024-05-08',17.50, 88.54);");
				statement.executeUpdate("INSERT INTO `wyniki` VALUES (2,'2024-05-08',17.51, 90.54);");

			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		
   	
		System.out.println("Utworzono tabele danych");

	}

}