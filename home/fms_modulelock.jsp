<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TIMS/title>
</head>
<html>

<form method="post">
<body>
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock1" scope="request"/>

<%
 //System.out.println("FORM ID: "+request.getParameter("formId"));
 //System.out.println("TEMP: "+request.getParameter("temp"));
 String ml_username=(String)session.getAttribute("username");
 String ml_formId=request.getParameter("formId");
 String ml_temp=request.getParameter("temp");
 String ml_sessionId=session.getId();
 
// ml_temp="other";
// System.out.println("USERNAMES: "+ml_username+" FORM ID: "+ml_formId+"  temp:  "+ml_temp+"  sessionId: "+ml_sessionId);
 modlock1.setSet_username(ml_username);
 modlock1.setFormId(ml_formId);
 modlock1.setSessionId(ml_sessionId);
 modlock1.setCallFrom(ml_temp);
 modlock1.init();
 
 boolean ml_flg=modlock1.isModLock();
 if(ml_flg)
 {
	 String ml_uname=modlock1.getUname();
	 ml_uname="User";
	 //String ml_url="../home/fms_moduleerror.jsp?uname="+ml_uname;
	 try
	 {
		 String url1="../home/fms_moduleerror.jsp?uname="+ml_uname;
   	     response.sendRedirect(url1);
	 }catch(Exception e){
		 System.out.println("EXCEPTION: "+e);
	 }
 }

// System.out.println("MODULE LOCK");
%>
</body>
</form>

</html>

