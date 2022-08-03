import java.sql.SQLException;

import org.json.JSONObject;

public class RequestValidator {

	
	
	public RequestValidator() {
		
		
		
		
	} // End of Constructor
	
	
	
	
	public boolean validate(JSONObject requestJSON) {
		
		
		
		if(requestJSON.get("function").equals("FamilyUnit")) {
			
			
			boolean checkList = false;
			
			
			
			if(requestJSON.has("user_id")) {
				
				
				checkList = true;
				
				
				return true;
				
			}
			
			
			
			
		} // End of getFamilyUnit if
		
		
		if(requestJSON.get("function").equals("AddMember")) {
			
			
			boolean[] checkList = new boolean[6];
			
			
			if(requestJSON.has("fu_id")) {
				
				checkList[0] = true;
				
			}
			
			if(requestJSON.has("first_name")) {
				
				checkList[1] = true;
				
			}


			if(requestJSON.has("middle_name")) {
				
				checkList[2] = true;
				
			}

			if(requestJSON.has("last_name")) {
				
				checkList[3] = true;
				
			}
			
			if(requestJSON.has("sex")) {
							
				checkList[4] = true;
							
			}
			
			if(requestJSON.has("birthdate")) {
				
				checkList[5] = true;
				
			}
			
			
			for(int i = 0; i < checkList.length; i++) {
				
		
				
				if(checkList[i] == false) {
					
					return false;
					
				} // End of if 
				
				
				
			} // End of for
			
			
			return true;
			
		} // End of if AddMember
		
		
		if(requestJSON.get("function").equals("Delete")) {
			
			
			boolean checkList = false;
			
			
			
			if(requestJSON.has("user_id")) {
				
				
				checkList = true;
				
				
				return true;
				
			}
			
			
			
			
		} // End of getFamilyUnit if
		
		
		
		return false;
		

		
	} // End of validate
	
	
	

} // End of Class
