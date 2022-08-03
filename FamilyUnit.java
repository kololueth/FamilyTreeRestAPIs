import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONObject;

public class FamilyUnit {
	
	
	Connection conn = null;
	
	ArrayList<Member> siblings;
	ArrayList<Member> parents;
	
	JSONObject responseJSON;
	 
	
	public FamilyUnit() {
		
		
		conn = new MYSQLConnection().getConnection();
		
		
						
		
	} // End of Constructor
	
	
	/******************************* Main Methods  *******************************/
	
	public JSONObject getUnit(JSONObject requestJSON) {
		
		
		responseJSON = new JSONObject();  // Initialize this first so the Chief has a guaranteed response.
		
		if(conn != null) {
		
			
			int user_id = requestJSON.getInt("user_id");
			
			int pair_id = getPairId(user_id);
			
			// use this later when you know the server logic well
			// int pair_id = getPairId(requestJSON.getInt("user_id"));
			
			fillSiblings(pair_id);
			
			fillParents(pair_id);
			
			
				responseJSON.put("siblings", siblings);
				responseJSON.put("parents", parents);
				
				// somehow let the the application know what the pair_id is if any
				
				// responseJSON.put("parent_pair_id", pair_id);
			
				
				
				
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
		} else {
			
			
			responseJSON.put("error", "FamilyUnit has an issue getting a Unit - database connection problem");
			
		}
		

		return responseJSON;
		
	} // End of getUnit
	
	public JSONObject deleteMember(JSONObject requestJSON) {
		
		responseJSON = new JSONObject();  // Initialize this first so the Chief has a guaranteed response.
		
		
		if(conn != null) {
		
			
			
			//String query = "DELETE FROM Siblings WHERE parent_pair_id = ";
			
		//	int pair_id = 0;
			
			
			try(Statement stmt = conn.createStatement()) {
		
				
			//	ResultSet rs = stmt.executeQuery(query);
				
				
			//		while(rs.next()) {
						
				//		pair_id = rs.getInt("parent_pair_id");
						
				//	} // End of while
				
				
				
			} catch (SQLException e) { e.printStackTrace();}
				
			
			
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
		} else {
			
			
			responseJSON.put("error", "FamilyUnit has an issue deleting a member - database connection problem");
			
		}
		

		return responseJSON;
		
	} // End of getUnit
	
	
	
	/******************************* Main Methods  *******************************/
	
	/******************************* Other Methods  *******************************/
	
	public int getPairId(int user_id) {
		
		
		String query = "SELECT parent_pair_id FROM user_properties WHERE user_id = " + user_id;
		
		int pair_id = 0;
		
		
		try(Statement stmt = conn.createStatement()) {
	
			
			ResultSet rs = stmt.executeQuery(query);
			
			
				while(rs.next()) {
					
					pair_id = rs.getInt("parent_pair_id");
					
				} // End of while
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return pair_id;
		
		
	}  // End of getPairId
	
	
	public void fillSiblings(int pair_id) {
		
		
		String query = "SELECT * FROM siblings WHERE parent_pair_id = " + pair_id;
		
		
		try(Statement stmt = conn.createStatement()) {
			
			
			ResultSet rs = stmt.executeQuery(query);
			
			
			if(rs != null) {
				
				siblings = new ArrayList<Member>();
				
				while(rs.next()) {
					
					Member member = new Member();
			
					member.put("first_name", rs.getString("first_name"));
					member.put("last_name", rs.getString("last_name"));
					member.put("birthdate", rs.getString("birthdate"));
					member.put("sex", rs.getString("sex"));
					member.put("user_id", rs.getInt("user_id"));
					member.put("fu_id", pair_id);
										
				siblings.add(member);
				
					
				} // End of while
			
				
			} // End of if
			
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		
		
		
	}  // End of fillSiblings


	public void fillParents(int pair_id) {
		
		
		String query = "(SELECT xx.first_name, xx.last_name, xx.sex AS sex, xx.xx_id AS parent_id FROM xx JOIN pairs ON pairs.xx_id = xx.xx_id WHERE pairs.pair_id = " + pair_id + ") UNION (SELECT xy.first_name, xy.last_name, xy.sex AS sex, xy.xy_id AS parent_id FROM xy JOIN pairs ON pairs.xy_id = xy.xy_id WHERE pairs.pair_id = " + pair_id + ")";
		
		
		try(Statement stmt = conn.createStatement()) {
			
			
			ResultSet rs = stmt.executeQuery(query);
			
			
			if(rs != null) {
				
				parents = new ArrayList<Member>();
				
				while(rs.next()) {
					
					Member member = new Member();
			
					member.put("first_name", rs.getString("first_name"));
					member.put("last_name", rs.getString("last_name"));
			//		member.put("birthdate", rs.getString("birthdate"));
					member.put("sex", rs.getString("sex"));
					member.put("parent_id", rs.getInt("parent_id"));
										
				parents.add(member);
				
					
				} // End of while
				
			
				
			} // End of if
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} // End of try catch
		
		
		
	}  // End of fillParents
	
	
	/******************************* Other Methods  *******************************/
	

} // End of Class
