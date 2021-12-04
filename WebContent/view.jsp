<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.crypto.Cipher"%>
<%@ page import="javax.crypto.CipherOutputStream"%>
<%@ page import="javax.crypto.spec.SecretKeySpec"%>
<%@ page import="javax.servlet.*"%>






<%

Blob image = null;
Connection con = null;
byte[ ] imgData = null ;
Statement stmt = null;
ResultSet rs = null;
OutputStream o;
String id = request.getParameter("id");  
  out.println("id ="); 
  out.println(id);
	

try {
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","keshavrao");
stmt = con.createStatement();
rs = stmt.executeQuery("select photo from contacts where contact_id  ="+id);
if (rs.next()) {
image = rs.getBlob(1);
imgData = image.getBytes(1,(int)image.length());
}
else {
out.println("Display Blob Example");
out.println("image not found for given id>");
return;
} 
byte k[]="CooL2116NiTh5252".getBytes();
SecretKeySpec key = new SecretKeySpec(k, "AES");
Cipher enc = Cipher.getInstance("AES");
enc.init(Cipher.ENCRYPT_MODE, key);

// display the image
response.setContentType("image/jpg");

response.setHeader("Content-Disposition","attachment;filename=encrypt.jpg"); 
 o = response.getOutputStream();
 CipherOutputStream cos = new CipherOutputStream(o, enc);
 cos.write(imgData);
 
//o.write(imgData);
cos.close();
  
o.flush();
o.close();
} catch (Exception e) {
out.println("Unable To Display image");
out.println("Image Display Error=" + e.getMessage());
return;
} finally {
try {
//rs.close();
stmt.close();
con.close();
} catch (SQLException e) {
	System.out.println("error");
//e.printStackTrace();
}
}

%>