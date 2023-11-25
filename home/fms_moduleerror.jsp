
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script language="javascript">
	function goHome()
	{
	  var url="../home/index3.jsp?std=1";
	  location.replace(url);
	}
</script>
<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="javascript" src="../js/util1.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="javascript" src="../js/fms.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TIMS : Hazira LNG Pvt. Ltd.</title>
</head>
<form>
<body>
<%@ include file="../home/header.jsp"%>
<%
 	 String uname=request.getParameter("uname");
	System.out.println("In side fms_modulerror.jsp");
%>
<div class="header">
  <div id="col-three">
     <table width="60%" align="center">
     <tr class="title2"> 
       <td>  This module is already locked by <b class="mand"><%=uname %></b>.<br>You can access this module once the user releases this module. </td>
     </tr>
     </table>
     <br><br>
   </div> 
</div>  
</body>
</form>
</html>