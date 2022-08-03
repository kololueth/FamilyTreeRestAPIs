import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONObject;

public class AddMember {

	
Connection conn = null;
	
	ArrayList<Member> siblings;
	ArrayList<Member> parents;
	
	JSONObject responseJSON = new JSONObject();
	
	public AddMember() {
		
		
		conn = new MYSQLConnection().getConnection();
						
		
	} // End of Constructor
	
	
	/*
	
	
	private boolean memberCheck(JSONObject requestJSON) {
		
		// This should be removed at this point, memberCheck.  It may be usefull once there are multiple concurrent users using the same table, but right now
		// we should have the application check this for a simple check adding a duplicate member.  We can create a more targeted memberCheck for concurrent users
		// when we get to that point. 
		
		
		boolean exists = false;
		
		
		String query = "SELECT * FROM siblings WHERE first_name = " + requestJSON.getString("first_name") + " AND last_name = " + requestJSON.getString("last_name");
		
		
		try(Statement stmt = conn.createStatement()) {
			
			
			ResultSet rs = stmt.executeQuery(query);
			
			
			if(rs != null) {
				
				while(rs.next()) {
								
					exists = true;
					
					return exists;
					
				} // End of while
			
				
			} // End of if
			
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		
		return false;
		
		
	} // End of memberCheck
	
	
	*/
	
	
	public JSONObject add(JSONObject requestJSON) {
		
		
		int fu_id = requestJSON.getInt("fu_id");
		
		String fn = requestJSON.getString("first_name");
		String mn = requestJSON.getString("middle_name");
		String ln = requestJSON.getString("last_name");
		String s = requestJSON.getString("sex");
		String b = requestJSON.getString("birthdate");
					
		
		String query = "INSERT INTO siblings (parent_pair_id, first_name, middle_name, last_name, sex, birthdate) VALUES (" + fu_id + ", '" + fn + "', '" + mn + "', '" + ln + "', '" + s + "', '" + b + "')";
		

			try(Statement stmt = conn.createStatement()) {
				
				
				int result = stmt.executeUpdate(query); // Returns row count
				
					try {
						
						conn.close();
						
					} catch (SQLException e) {
						
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				
		
				responseJSON.put("result", result);
				
	
				return responseJSON;
				
				
			} catch (SQLException e) {
				
				try {
					
					conn.close();
					
				} catch (SQLException se) {
					
					// TODO Auto-generated catch block
					se.printStackTrace();
					
				}
				
	
				responseJSON.put("error", e.toString());
				
				
				return responseJSON;
		
			
			} // End of try Catch
		
		

		
	} // End of Add
	
	
	
	
	
} // End of Class
