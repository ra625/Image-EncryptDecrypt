<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image EncryptDecrypt</title>
<link rel="stylesheet"  href="style.css" />
</head>
<body>
    
        <div class=main><h1>Image EncryptDecrypt</h1></div>
        
        <div class=main><form action="uploadServlet" method="POST"  enctype="multipart/form-data">
            
                    Upload Image: <br/><br/>
                    
                    <input   type="file"  name="photo" size="60"/>
                       
                        <input class="submit" type="submit" value="Save">
                  
                
            
        </form></div>
       <div class=main> <form action="view.jsp"  >
       Encrypt image<br/><br/>
        Enter the id:
        <input class="txt"  type="text" name="id">
        <input class="submit" type="submit" value="Encrypt">
              
         </form ></div>
         <div class=main><form action="Unview.jsp"  >
         Decrypt image<br/><br/>
        Enter the id:
        <input class="txt" type="text" name="id">
        
        <input class="submit" type="submit" value="Decrypt">
              
         </form ></div>
    
</body>
</html>