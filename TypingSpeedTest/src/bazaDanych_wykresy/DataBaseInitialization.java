package bazaDanych_wykresy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseInitialization {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		try {
			
			conn = DriverManager.getConnection(	"jdbc:h2:tstData", "artur", "");
			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		

	}
}
