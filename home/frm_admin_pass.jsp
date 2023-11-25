<%@ include file="../sess/Expire.jsp" %>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title> DLNG | Change Password </title>
<link rel="stylesheet" href="../responsive/w3/w3.css">  <!-- For accordion  -->
<!-- BS CSS -->
<link href="../responsive/css/bootstrap.min.css" rel="stylesheet">
<!-- CSS -->
<link href="../responsive/css/main.css" rel="stylesheet">
<link href="responsive/css/style.css" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">  
<link href="../css/pt_sans.css" rel="stylesheet">
<script src="../responsive/js/jquery.min.js"></script>

<!-- Bootstrap JS -->
<script src="../js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script src="../js/admin.js"></script>
<script language="javascript" src="../js/util1.js"></script>

<style type="text/css">
.s-red{
color: red;
font-size: 18px;
}

</style>
<script>
function showpro()
{
	if(document.forms[0].process.style.visibility='hidden')
		document.forms[0].process.style.visibility='visible';
}
function hidepro()
{
	if(document.forms[0].process.style.visibility='visible')
		document.forms[0].process.style.visibility='hidden';
}


function checkForNumber(e)
{
	var charCode = e.charCodeAt(0);	
	if(charCode > 31 && (charCode < 48 || charCode > 57))
	{
		return false;
	}
	return true;
}

function checkForChar(e)
{
	var charCode = e.charCodeAt(0);	
	if(charCode < 97 || charCode > 122)
	{
		return false;
	}
	return true;
}

function checkForCaps(e)
{
	var charCode = e.charCodeAt(0);	
	if(charCode < 65 || charCode > 90)
	{
		return false;
	}
	return true;
}


function passwd_std_chk(passwd,obj,minimum)
{
	var count = parseInt("0");
	var cnt1 = parseInt("0");
	var cnt2 = parseInt("0");
	var cnt3 = parseInt("0");
	var cnt4 = parseInt("0");
	var min = parseInt(""+minimum);
	var lenfl = true;
	
	var message = "Password not confirming standards defined by Administrator ...\n\n";

	//alert("passwd.length = "+passwd.length);
	
	if(min>0)
	{
		if(passwd.length<min)
		{
			lenfl = false;
			message += "Password length should be atleast "+min+" alphanumeric characters long !!!\n"
		}
	}
	else
	{
		lenfl = false;
		message += "Please, Contact Administrator to improve User ID Minimum Length Acceptable !!!\n";
	}
	
	count += checkForNumber2(obj);
	cnt1 = checkForNumber2(obj);
	
	count += checkForChar2(obj);
	cnt2 = checkForChar2(obj);
	
	count += checkForCaps2(obj);
	cnt3 = checkForCaps2(obj);
	
	count += checkForSpecialChar2(obj);
	cnt4 = checkForSpecialChar2(obj);
	
	if(count!=passwd.length)
	{
		lenfl = false;
		message += "Please, Enter Valid Password !!!\n";
	}
	else
	{
		if((cnt1==0 && cnt2==0) || (cnt1==0 && cnt3==0) || (cnt1==0 && cnt4==0) || (cnt2==0 && cnt3==0) || (cnt2==0 && cnt4==0) || (cnt3==0 && cnt4==0))
		{
			lenfl = false;
			message += "Please, Enter Valid Password !!!\n";
		}
	}
	
	var userId = document.forms[0].user_id.value;
	
	if(passwd==userId)
	{
		lenfl = false;
		message += "Please, Enter different Password - Password can not be same as User Login ID !!!\n";
	}
		
	if(!lenfl)
	{
		alert(message);
		obj.value = '';
	}
}


function checkPass()
{
   	if(document.forms[0].opt.value=='N')
   	{
    	alert("document.forms[0].opt.value::"+document.forms[0].opt.value);
		
		if(document.forms[0].passwd.value!=document.forms[0].cf_passwd.value)
		{
     		alert('Confirmation Password must be same as password');
	 		document.forms[0].cf_passwd.focus();
 		}
	}
}


function checkAll()
{
   	var flg;
   	message="Enter The Following\n";
   	flag=true;
   	
   	if(document.forms[0].user_id.value=='')
   	{
    	message+=" User ID\n";
     	flag=false;
   	}
   	if(document.forms[0].passwd.value=='')
   	{
    	message+=" Password \n";
     	flag=false;
   	}
   	if(document.forms[0].new_passwd.value=='')
   	{
    	message+=" New Password \n";
     	flag=false;
   	}
   	if(document.forms[0].cf_passwd.value=='')
   	{
    	message+=" Confirm Password \n";
     	flag=false;
   	}
   
   	var confirm_passwd=document.forms[0].cf_passwd.value;
   	var new_passwd=document.forms[0].new_passwd.value;
   
   	if(confirm_passwd != new_passwd)
   	{
    	alert("New password must be same\nas Confirm password !!!");
     	document.forms[0].cf_passwd.value = '';
     	document.forms[0].cf_passwd.focus();
     	return false;
   	}
   	
   	if(!flag)
   	{
      	alert(message)
    	return false;
   	}
   	else
   	{
		var a = confirm("Do you want to modify the current password ?");
		if(a){
			document.forms[0].method="post";
			document.forms[0].submit();
		}	
   	}    
}


function goToLoginPage()
{
	location.replace("index1.jsp");
}

</script>

<!-- <link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css"> -->
<!-- <LINK href="../css/default.css" type="text/css" rel=STYLESHEET> -->
</head>

<jsp:useBean class="com.hazira.hlpl.tlu.admin.DataBean_Admin" id="pass" scope="page"/>
<%
	String status = request.getParameter("status")==null?"":request.getParameter("status");	  
		System.out.println("stATUS--"+status);
	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
 	 String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
	pass.setCallFlag("Change_Password2");
	pass.init();
	
	int u_min = pass.getUmin();
	int u_max = pass.getUmax();
	int p_min = pass.getPmin();
	int p_max = pass.getPmax();
	String tp = pass.getFormName();
// 	System.out.println("ppppp---"+p_max);
	String val=request.getParameter("val") == null?tp:request.getParameter("val");
	String x="2";  
	String opt="";
%>
<%-- <%@ include file="../home/header.jsp"%>  --%>
<body onLoad="document.forms[0].new_passwd.focus();">
<div class="tab-content">
<div class="tab-pane active" id="add_new_action">
<div class="box mb-0">
<div class="box-body">

 <div class="box-header with-border">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;"> 
				
 				<form class="form-horizontal"  name="pass" method="post" action="../servlet/Frm_admin"> 
					<div class="col-xs-2" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >
				    			
				    		</table>
					    </div>
				    </div>	
				    
				    <div class="col-xs-8" >
					<h2 class="sub-header" align="center" id="head"><font size="4" style="font-weight: bold;"><%=status %></font> </h2>
				  		<div class="table-responsive"  >
				    		<table class="table table-striped" >
				    			<%	if(status.contains("You need to change your PASSWORD compulsary, As your password has EXPIRED ...") || status.contains("You need to change your PASSWORD compulsary, As your password has been RESET ...")){%>
				    			<tr>
				    				<th class="success">Login</th>
				    				<td><%=session.getAttribute("userid")%><input type=hidden name=user_id size=7 maxlength=<%=u_max%> value='<%=session.getAttribute("userid")%>' readonly></td>
				    			</tr>
				    		
				    			<tr >	
				    				<th class="success">Current Password</th>
				    				<td>
				    				<%	if(status.trim().equalsIgnoreCase("You need to change your PASSWORD compulsary, As your password has EXPIRED ...") || status.contains("You need to change your PASSWORD compulsary, As your password has been RESET ..."))
							    		{	%>
							    			<input type=password name=passwd size=18 title="Enter your current password" value="">
							    	<%	}
							    		else
							    		{	%>
							    			<input type=password name=passwd size=18 title="Enter your current password" value="<%=session.getAttribute("userid")%>" readOnly>
							    	<%	}	%>
							    </td>	
				    			</tr>
				    			<tr >	
				    				<th class="success">New Password</th>
				    				<td><input type=password name=new_passwd maxlength='<%=p_max%>' size=18 onchange="passwd_std_chk(this.value,this,'<%=p_min%>');" title="Maximum Size <%=p_max%> chars"></td>
				    			</tr>
				    			
				    			<tr >		
				    				<th class="success">Confirm  Password</th>
				    				<td><input type=password name=cf_passwd maxlength='<%=p_max%>' size=18 onchange="passwd_std_chk(this.value,this,'<%=p_min%>');" title="Maximum Size <%=p_max %> chars"> </td>
				    			</tr>
				    			
				    			<tr class="content1"> 
									 <td colspan=2 align="left">
									 <font color="red">
									 	Password to be minimum of <%=p_min%> chars long &amp; maximum of <%=p_max%> chars long.
									 </font>
									 <br>
									 <font color="blue">
									 	<b>Password should contain atleast any 3 criteria from below mentioned RULE(s):</b><br>
									 	(1) Atleast 1 Capital Alphabet,<br>
									 	(2) Atleast 1 Small Alphabet,<br>
									 	(3) Atleast 1 Numeric Digit, and<br>
									 	(4) Atleast 1 Special Character.
									 </font>
									 </td>
								</tr>
								
								<tr>
							    	<td colspan="2" class="text-center" style="color: blue;font-weight: bold;font-size: 13px;">
							    		Note : <font color="red">The same password will be for the FMS and DLNG</font> 
							    	</td>
							    </tr>
							    
							    <tr>
						    		<td align="center" colspan="2"  style="background-color: white;" class="text-center">
						    			<button type="reset" class="btn btn-info" > Reset  </button>
						    			<button type="submit"  class="btn btn-success" onclick="checkAll();" id="subBtn" value="sub" >Submit </button>
						    		</td>
						    	</tr>
						    	
						    <%	}else{	%>
									<tr>
										<td colspan="2"><div align="center"><br><br><br><br><input type="button" name="gotologin" value="Go To Log-In Page" onClick="goToLoginPage();"></div></td>
									</tr>
							<%	}	%>		
				    		</table>
				    	</div>
				    </div>	
				    
				    <div class="col-xs-2" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >
				    			
				    		</table>
					    </div>
				    </div>			
	
	

<input type=hidden name="option" value="Change_Password2">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">

</form>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

</body>
</html>
