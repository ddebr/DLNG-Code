<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ page import="java.util.*"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>DLNG : RELEASE LOCK and RESET PASSWORD</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../responsive/w3/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" >
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/actionJS.js"></script>

</head>
<script language="JavaScript">

function checkAll()
{
	var login = document.forms[0].login.value; 
	flg = true;
	if(login == '' || login == ' '){
		flg=false;
	}
	if(flg)
	{
		if(document.forms[0].chk.checked)
		{
			var message = 'Do You Want To Reset Password ?';
		}
		else
			{
			var message = 'Do You Want To Release Lock ?';
			}
		var a = confirm(message);
   		if(a)
 		{
   			
  			document.forms[0].option.value = "User_Release_Lock_self";
  			document.forms[0].submit();
 		}
 		else
  		{
   			return false;
  		}
 	}else{
 		alert('Please enter valid User id/Email id');
 	}
}


function chkClick()
{
 	if(document.forms[0].chk.checked)
  	{
   		document.forms[0].release.value = "Reset Password";
  	}
  	else
   	{
   		document.forms[0].release.value = "Release Lock";
   	}
}


function mes(i)
{ //alert(i);
    if(i=='Y')
    {
        alert("USER is locked "+document.forms[0].login.value);
    }
}
function refresh(id)
{ //alert(id);
	document.forms[0].emp_id.value =document.forms[0].login.value;
	var id=document.forms[0].login.value;
    var url="../admin/frm_admin_release_lock2.jsp?login="+id;//alert(url);
    location.replace(url);
}
function back()
{ alert();
}
</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin_Reset" id="rel_lock" scope="page"/>
<%
	String status = request.getParameter("status")==null?"":request.getParameter("status");
	String formId = "1010";//request.getParameter("formId");
	String formNm = "DLNG : RELEASE LOCK / RESET PASSWORD";
	String emp_id = request.getParameter("login")==null?"":request.getParameter("login");
	String write_permission = request.getParameter("write_permission")==null?"Y":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	
	
	rel_lock.setFormID(formId);
	rel_lock.setUsername(emp_id);
	rel_lock.setCallFlag("Lock_Info_self");
    if(!emp_id.equals("")){
		rel_lock.init();
    }
    int cnt = rel_lock.getCount();
	
	String emp_lock=rel_lock.getEmp_lock();
	System.out.println("emp_lock :" +emp_lock); 
	String emp_sts = rel_lock.getStatus();//HA20200420
%> 

<div class="tab-content">
		<div class="tab-pane active" id="hcasreport">
		<!-- Default box -->
			<div class="box mb-0">
				<form name="frm_admin_release_lock" method="post" action="../servlet/Frm_admin_Reset" >

				<input type="hidden" name="form_id" value="<%=formId%>">
				<input type="hidden" name="form_nm" value="<%=formNm%>">
				<input type="hidden" name="emp_id" value="<%=emp_id%>">
				<input type="hidden" name="emp_id_size" value="<%=cnt%>">
				<input type="hidden" name="formId" value="<%=formId%>">
				<input type="hidden" name="option" value="User_Release_Lock_self">
				<input type="hidden" name="write_permission" value="<%=write_permission%>">
				<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
				<input type="hidden" name="print_permission" value="<%=print_permission%>">
				<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
				<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
								
				<div class="box-body table-responsive no-padding">
					<table id="example" class="table table-bordered">
						<thead>
							<tr><td colspan="3" align="center" style="font-size: 13px;font-weight: bold;"> <%=formNm%> </td></tr>   
							<tr><td colspan="3"><div align="center"><b><%=status%></b></div></td> </tr>
							<%if(!emp_sts.equals("")) {%>
								<tr><td colspan="3" style="color: red;font-size: 13px;font-weight: bold;"><div align="center"><b><%=emp_sts%></b></div></td> </tr>
								<script>
									document.forms[0].login.value="";
								</script>
							<%} %>
							<tr class="info">
								<td>Login Id:</td>
								<td width="60%">&nbsp;<input type="text" style="background-color: YELLOW" name="login" value="<%=emp_id%>" size="40" maxlength="40" onChange="refresh('<%=emp_id%>')"/></td>
								<td width="20%">&nbsp;<b>Reset Password ?&nbsp;<input type="checkbox" name="chk" value="Y" onClick="chkClick();"></b></td>
							</tr>
							<tr class="title2">
								<td colspan="3" > 
							    	<div align="center">
							    	<%	if(write_permission.trim().equalsIgnoreCase("Y"))
										{	%>
							    			<input type=button name="release" value="Release Lock-<%=emp_lock %>" onClick="checkAll();">
							    	<%	}
							    		else
							    		{	%>
							    			<input type=button name="release" value="Release Lock<%=emp_lock %>" style="font-size:15;font-weight:bold;" disabled>
							    	<%	}	%>
									</div>
							    </td>
							</tr>
						</thead>
						<%if(!emp_sts.equals("")) {%>
								<script>
									document.forms[0].login.value="";
								</script>
							<%} %>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>

</body>
</html>