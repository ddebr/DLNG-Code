<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="faccess" scope="page" /> 
</head>
<%
 String username1=(String)session.getAttribute("username");
 //String formId1 = request.getParameter("formId")==null?(String)session.getAttribute("sessformID"): request.getParameter("formId");
 String formId1 = request.getParameter("formId");//==null?(String)session.getAttribute("sessformID"):request.getParameter("formId");

 System.out.println("JSP Accessrights before Access Lock contents from Session ");


 if(formId1==null || formId1.equalsIgnoreCase("") || formId1.equalsIgnoreCase(" "))
 {
     formId1=(String)session.getAttribute("sessformID");
 }
 System.out.println("formId1-->"+formId1);
 
  faccess.setUsername(username1);
  faccess.setFormID(formId1);
  faccess.setCallFlag("access_rights");
  faccess.init();
   
   
   //sra1-2-feb-10
 String  alwadd1=faccess.getALW_ADD();
 String alwdel1=faccess.getALW_DEL();
 String alwupd1=faccess.getALW_UPD();
 String alwprint1=faccess.getALW_PRINT();
//  String Access_alw_lock=faccess.getLock_table_lock_status_value();
 
  System.out.println("new123--->"+alwadd1+alwdel1+alwupd1+alwprint1);
    
  String access_add=""; 
  String access_del=""; 
  String access_update=""; 
  String access_print=""; 
  String acess_update_readonly="";
  String access_update_href="";
  String acess_print_readonly="";
  String access_print_href="";
  String acess_print_disabled="";
  
    if(alwadd1.equalsIgnoreCase("N"))
    {
        access_add="disabled";
    }
    if(alwdel1.equalsIgnoreCase("N"))
    {
        access_del="disabled";
    }
    if(alwupd1.equalsIgnoreCase("N"))
    {
        access_update="disabled";
        acess_update_readonly="readonly";
        access_update_href="display:none;";
    }
    if(alwprint1.equalsIgnoreCase("N"))
    {
        access_print="disabled";
        acess_print_readonly="readonly";
        acess_print_disabled="disabled";
        access_print_href="display:none;";
    }
    
    System.out.println("JSP Access_add: "+access_add);

   session.setAttribute("sessformID",formId1);
   
   // Session Management
   String var_simple="";
   session.setAttribute("lock_contract",var_simple);
   session.setAttribute("lock_contract_frm_dt",var_simple);
   session.setAttribute("lock_contract_to_dt",var_simple);
    %>
<body>

</body>
</html>