//Package
package connect;

//Import Library
import java.sql.Connection;
import java.sql.DriverManager;

//Main Class
public class Database {
	// Variable Declaration
	final String DRIVER = "com.mysql.cj.jdbc.Driver";
	final String HOST = "localhost";
	final int PORT = 3306;
	final String DATABASE = "luton";
	final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
	final String USER = "root";
	final String PASS = "";

	// Connection
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // loading driver
			conn = DriverManager.getConnection(URL, USER, PASS); // connection with database server
						
		} catch (Exception ex) {
			System.out.println("ERROR : " + ex.getMessage()); // display error message
		}
		return conn; // return connection
	}
}