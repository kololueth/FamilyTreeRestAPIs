import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection {
	
	
	private final String url = "jdbc:mysql://localhost:3306";
	private final String database = "/familytree";
	private final String user = "famo";
	private final String pass = "1234";
	
	
	Connection conn = null;
	
	
	public MYSQLConnection() {
		
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			conn = DriverManager.getConnection(url + database, user, pass);
			
		
		} catch (SQLException se) {
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} // End of try/catch
		
		
		
		
		
	} // End of construction
	
	
	
	
	public Connection getConnection() {
		
		
		return conn;
		
		
	} // End of getConnection
	
	
	
	
	

} // End of Class
