<html>
<head>
<title>TIMS : Hazira LNG Pvt. Ltd.</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_locked_login" id="err_log" scope="page"/>
<body style="background-color:silver ">

<%
    String loginname = request.getParameter("login");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
    err_log.setLogin(loginname);
    err_log.setMsg(msg);
    err_log.init();		
	
	String message = "Your Login has been locked, Kindly contact your Administrator !!!";
	
	if(msg.equalsIgnoreCase("In-Active User Not Permitted to Log-In into the System !!!"))
	{
		message = msg;
	}
    System.out.println("call");
%>
   <div align="center"> 
  <p><b><font face="Verdana, Arial, Helvetica, sans-serif" size="2" color="#FFFFFF"><br>
    </font></b></p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p><b><font face="Verdana, Arial, Helvetica, sans-serif" size="2" color="#FFFFFF">Training Information 
    Management System </font></b></p>
  <form name="form1" method="post" action="">

    <p>&nbsp;</p>
    <table width="51%" border="2" bordercolor="black" cellpadding="0" cellspacing="0" align="center" style="background-color:#E9E9E9 ">
      <tr>
        <td height="53" align="center"> 
         <b> 
				<%=message%>		
		 </b>
        </td>
      </tr>
    </table>
    <p>
    </p>
    <p>&nbsp; </p>
    </form>
</div>
</body>
</html>
