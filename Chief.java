import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Chief extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JSONObject responseJSON;
	
	

	public void init() throws ServletException {
	
		responseJSON = new JSONObject();
		
	} // End of init
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
	  
		
		PrintWriter writer = response.getWriter();
		
		
		
		
			writer.println("<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<meta charset=\"UTF-8\">"
					+ "<title>The Chief</title>"
					+ "</head>"
					+ "<body> FROM SERVLET VERSION 13"
					+ "</body>"
					+ "</html>");
					
					
				
	
		writer.close();
	
		
	} // End of doGet
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


			response.setContentType("application/json");
			
			
			JSONObject requestJSON = createJSONObject(request);
			
			
			if(requestJSON.has("error")) {
				
				// If the createJSON method has an exception, the method will return a JSONObject with an a key named "error"
				
				
				responseJSON = requestJSON;
				
				
				
			} else if(requestJSON .has("function")) {
				
				// If the JSONObject has a key named "function", the JSONObject was sent from the application
				
			//	responseJSON = new JSONObject();  // initializing here so we can set the function of the response JSON first
				
				
				if(new RequestValidator().validate(requestJSON)) {
					
					
				
				
						if(requestJSON.get("function").equals("FamilyUnit")) { responseJSON = new FamilyUnit().getUnit(requestJSON); } // End of getFamilyUnit if
						
						if(requestJSON .get("function").equals("AddMember")) { responseJSON = new AddMember().add(requestJSON); } // End of AddMember
						
						if(requestJSON .get("function").equals("Delete")) { responseJSON = new FamilyUnit().deleteMember(requestJSON); } // End of DeleteMember
						
						
						responseJSON.put("function", requestJSON.get("function")); // notifies application how to respond to the response.  Validator knows all Functions.
						
						
				} else {
					
					
					// If the JSONObject could not be validated, then there are missing keys in the JSON from the request
					
					responseJSON.put("error", "JSON could not be Validated for it's function");
					responseJSON.put("function", requestJSON.get("function"));
					
				}
				
				
			} else {
				
		//		responseJSON = new JSONObject();
				
				responseJSON.put("error", "No Function");
				
			}
			
		
			response.getWriter().println(responseJSON.toString());
					    
			    
	} // End of doPost
	
	
	public JSONObject createJSONObject(HttpServletRequest request) {
		
		
		String requestString = getBodyContent(request);
			
		
		return new JSONObject(requestString);
		
		
	}
	
	
    public String getBodyContent(HttpServletRequest request) {


            try {

                InputStreamReader characters = new InputStreamReader(request.getInputStream(), "UTF-8");

                BufferedReader bufferedReader= new BufferedReader(characters);

                String bodyContent = "";
                
                String string;
                
                while((string = bufferedReader.readLine()) != null) {
                	
                	
                	bodyContent = bodyContent + string; 
                	
                }

                characters.close();
                bufferedReader.close();
                request.getInputStream().close();

                return bodyContent;


            } catch (IOException e) {
            	
            	return new JSONObject().put("error", e.toString()).toString();
            	
            }



    } // End of getBodyContent
	
	
	public void destroy() {
		
	
		
	} // End of Destroy
	
	
} // End of class
