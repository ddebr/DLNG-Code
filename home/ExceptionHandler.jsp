<%@ page isErrorPage="true" import="java.io.*"%>
<html>
<head>
<!-- <link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css"> -->
	<title>DLNG</title>
	<style>
	body, p { font-family:Tahoma; font-size:10pt; padding-left:30; }
	pre { font-size:8pt; }
	</style>
	
  <script language="JavaScript">

  function gotoForm()
  {
    fnm=document.forms[0].fnm.value;
    mnm=document.forms[0].mnm.value;
   // alert(fnm+"   "+mnm);
   // alert(mnm);
   if(fnm=="")
   {
     url="../home/index3.jsp?std=1";
   }
   else
   {
      url="../"+mnm+"/"+fnm;
      
   } 
    //alert(url);
    location.replace(url);
  }
  
 </script>
	
</head>
<form  method="post"> 
<body>
<%@ include file="../home/header.jsp" %>
<%-- Exception Handler --%>

<%
   String exc="";
   String fnm="";
   exc=request.getParameter("excp")==null?"":request.getParameter("excp");
   //&modulename=gas_monitoring&formname=frm_gasnom_schedule.jsp
   String mnm=request.getParameter("modulename")==null?"":request.getParameter("modulename");
   fnm=request.getParameter("formname")==null?"":request.getParameter("formname");
   
   if(exc.equalsIgnoreCase(""))
   {
      	exc=exception.getMessage();
   }


%>
<div class="header">
<div id="col-three">

	<table width="60%" align="center">
	    <tr class="title1">
	       <td align="center">Error Generated </td>
	    </tr>
	    
		<tr class="content1">
		   <td align="center">
		      An Error has Occured. Please check the data you have entered.&nbsp;&nbsp;
		   </td>
		</tr>
		
		<tr class="content1">
		  <td align="center">
		     Error Details: <b class="mand"><%=exc%> Error</b> 
		  </td>
		</tr>
		
		<tr class="title2">
			<td align = center colspan="2">
                <input type="button" name="button" value="Back" onClick="gotoForm();">
			</td>
		</tr>
		
		<input type="hidden" name="mnm" value="<%=mnm %>">
		<input type="hidden" name="fnm" value="<%=fnm %>">
	</table>
</div>
 <br><br><br>
</div>

</form>
</body>
</html>
