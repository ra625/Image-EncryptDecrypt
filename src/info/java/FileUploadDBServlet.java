package info.java;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)   

public class FileUploadDBServlet extends HttpServlet {
	
	
    // database connection settings
     String dbURL = "jdbc:mysql://localhost:3306/mydb";
     String dbUser = "root";
     String dbPass = "keshavrao";
    
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
        
       
         
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        
            // prints out some information for debugging
            //System.out.println(filePart.getName());
            //System.out.println(filePart.getSize());
            //System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        
         
        Connection conn = null; // connection to the database
        String message = null;
        String extra=null;// message will be sent back to client
         
        try {
            // connects to the database
        	Class.forName("com.mysql.cj.jdbc.Driver");
           // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            String sql = "INSERT INTO contacts (photo) values ( ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(1, inputStream);
            }
            String last="SELECT contact_id FROM contacts ORDER BY contact_id DESC LIMIT 1";
            
            Statement stmt = conn.createStatement();
            // sends the statement to the database server
            int row = statement.executeUpdate();
            if (row > 0) { 
            	ResultSet rs=stmt.executeQuery(last);
            	while(rs.next()){  
            		System.out.println(rs.getInt(1));
            	 extra =String.valueOf(rs.getInt(1));
            	System.out.println(extra);
            	}
                message="Image stored successfully in the database with id "+extra;
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            System.out.println("Error");
            //ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (conn != null) { 
            	
            	//System.out.print(down);
               
                try {
                    conn.close();
                } catch (SQLException ex) {
                    //ex.printStackTrace();
                }
            }
            // sets the message in request scope
            request.setAttribute("Message",message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        }
    }
}
