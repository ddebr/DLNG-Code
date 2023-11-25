<!DOCTYPE html>
<html lang="en">
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<head>
	<title>DLNG</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
<!-- 	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/> -->
<!--===============================================================================================-->
<!-- 	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css"> -->
<!--===============================================================================================-->
<!-- 	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css"> -->
<!--===============================================================================================-->
<!-- 	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css"> -->
<!--===============================================================================================-->	
<!-- 	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css"> -->
<!--===============================================================================================-->
<!-- 	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css"> -->
<!--===============================================================================================-->
<!-- 	<link rel="stylesheet" type="text/css" href="css/util.css"> -->
<!-- 	<link rel="stylesheet" type="text/css" href="css/main.css"> -->
<!--===============================================================================================-->
<!--  <link rel="shortcut icon" href="../images/logo.png"  /> -->
 
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script src="../responsive/js/jquery.min.js"></script>  
<script src="../responsive/js/bootstrap.min.js"></script>  
</head>
<!-- <script>
   resizeTo(300,420);
        moveTo((window.width-300)/2,(window.height-420)/2);
        
</script> -->

<%
String action_id =request.getParameter("action_id")==null?"":request.getParameter("action_id");
String unit_cd =request.getParameter("unit_cd")==null?"":request.getParameter("unit_cd");
String assign_cd =request.getParameter("assign_by")==null?"":request.getParameter("assign_by");

session.setAttribute("action_id", action_id);
session.setAttribute("unit_cd", unit_cd);
session.setAttribute("assign_cd", assign_cd);

// System.out.println("action_id&&&&"+action_id);
%>
<frameset rows="90%">
	 <frame name="mainframe" src="index1.jsp" noresize frameborder="0" marginheight="0" marginwidth="0" border="0" framespacing="0" scrolling=auto>
	 <!--<frame name="bottomframe" src="footer.jsp" noresize frameborder="0" marginheight="0" marginwidth="0" border="0" framespacing="0" scrolling=auto>-->
</frameset>

</html>