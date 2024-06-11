package bazaDanych_wykresy;

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
		     statement.executeUpdate("DROP TABLE IF EXISTS `wyniki`;");
				
		     statement.executeUpdate("CREATE TABLE `wyniki` ("+
						  "`Id` int(6) unsigned NOT NULL auto_increment,"+
						  "`data` date default NULL,"+
                          "`hour` VARCHAR(5) default NULL,"+ 
						  "`Correct words` float default NULL,"+
                          "`WPM` float default NULL,"+
						  "PRIMARY KEY  (`Id`)"+
						") ;");
				
				// Dane poczatkowe
				statement.executeUpdate("INSERT INTO `wyniki` VALUES (1,'2024-06-12',16.50, 88.54, 55);");
				statement.executeUpdate("INSERT INTO `wyniki` VALUES (2,'2024-06-12',16.51, 90.54, 45);");
				statement.executeUpdate("INSERT INTO `wyniki` VALUES (3,'2024-06-12',16.52, 20.12, 45);");
				statement.executeUpdate("INSERT INTO `wyniki` VALUES (4,'2024-06-12',16.53, 15.4, 55);");
				statement.executeUpdate("INSERT INTO `wyniki` VALUES (5,'2024-06-12',16.54, 66.54, 70);");
				

			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}

		System.out.println("Utworzono tabele danych");

	}

}