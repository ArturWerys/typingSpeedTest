package bazaDanych;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatingTable{

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(	"jdbc:h2:tstData", "artur", "");
			// domyslnie nazwa uzytkownika to "sa" a dostep jest bez hasla - ""
			
			
		     Statement statement = conn.createStatement();
		     // Usuwanie tabeli je�li ju� istnieje - kolejne uruchomienie przykladu nie wygeneruje bledu:
		     statement.executeUpdate("DROP TABLE IF EXISTS `wyniki`;");
				
			 // Tworzenie tabeli o okreslonej strukturze danych
		     statement.executeUpdate("CREATE TABLE `wyniki` ("+
						  "`Id` int(6) unsigned NOT NULL auto_increment,"+
						  "`data` date default NULL,"+
                          "`hour` VARCHAR(5) default NULL,"+ // Używamy VARCHAR dla godziny
						  "`Correct words` float default NULL,"+
						  "PRIMARY KEY  (`Id`)"+
						") ;");
				
				
				//Dodawanie "reczne" poszczegolnych rekordow do tabeli:

//		     statement.executeUpdate("INSERT INTO `wyniki` (`Id`,`data`, `hour`,`Correct words`) VALUES (1, '2024-04-28',15.10, 50);");
		     
				// Przykladowe rownowazne polecenia SQL: 
//				statement.executeUpdate("INSERT INTO `wyniki` VALUES (2,'2024-04-28',12.50,100);");
				//statement.executeUpdate("INSERT INTO `waluty` (`data`,`USD`,`EUR`,`GBP`) VALUES ('2000-01-03',4.1171,4.165,6.6576);");
 		        //statement.executeUpdate("INSERT INTO waluty VALUES (1,'2000-01-03',4.1171,4.165,6.6576);");
		        //statement.executeUpdate("INSERT INTO waluty (Id,data,USD,EUR,GBP) VALUES (1,'2000-01-03',4.1171,4.165,6.6576);");

			 
		   //Rownowazne wywolanie z wykorzystaniem PreparedStatement 
			 
		   /*  PreparedStatement prep = conn.prepareStatement("INSERT into waluty(data, usd, eur, gbp) values (?, ?, ?, ?)");
			 prep.setString(1, "2000-01-03");
			 prep.setString(2, "4.1171");
			 prep.setString(3, "4.165");
			 prep.setString(4, "6.6576");
			 prep.executeUpdate();*/
			 
			 
//				statement.executeUpdate("INSERT INTO `waluty` (`Id`,`data`,`USD`,`EUR`,`GBP`) VALUES (2,'2000-01-04',4.1267,4.2232,6.7571);");
//				statement.executeUpdate("INSERT INTO `waluty` (`Id`,`data`,`USD`,`EUR`,`GBP`) VALUES (3,'2000-01-05',4.1299,4.2797,6.776);");
//				statement.executeUpdate("INSERT INTO `waluty` (`Id`,`data`,`USD`,`EUR`,`GBP`) VALUES (4,'2000-01-06',4.1129,4.2553,6.762);");

			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		
   	
		System.out.println("Utworzono tabele danych");

	}

}