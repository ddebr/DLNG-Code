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
<style type="text/css">
.s-red{
color: red;
font-size: 18px;
}

</style>
<script language="JavaScript">


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
// 			alert(document.forms[0].action)
			document.forms[0].submit();
		}	
   	}    
}

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="pass" scope="page"/>

<%
//   	 int h=0;
//sra1-14/aug/09

String username=(String)session.getAttribute("username");
// System.out.println("username****"+username);
pass.setUsername(username);
//
  	 String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
  	 String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
     String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
     String head_tab = request.getParameter("head_tab")==null?"":request.getParameter("head_tab");
//   	 System.out.println("flag****"+flag);
//      String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
     
     String alw_add1 = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
     String alw_view1 = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
     String alw_upd1 = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
     String alw_del1 = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
     String alw_print1 = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");
     String selModule_cd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
     
     String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
 	 String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
 	 String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
 	
     pass.setFormID(formId);
     pass.setSelModule(selModule_cd);
     pass.setHead_tab(head_tab);
     pass.setCallFlag("Change_Password");
	 
     pass.init();
	 
    int u_min = pass.getUmin();
 	int u_max = pass.getUmax();
 	int p_min = pass.getPmin();
 	int p_max = pass.getPmax();
 	String tp = pass.getFormName();
// 	System.out.println(p_max);
 	String val=request.getParameter("val") == null?tp:request.getParameter("val");
 	String x="2";  
 	String opt="";  
 	
 	String formName = pass.getFormName();
     
     String access_add="";
     String access_update="",acess_update_readonly="", access_update_href="";
     String access_print="",  acess_print_readonly="", acess_print_disabled="", access_print_href="";
     
     if(alw_add1.equalsIgnoreCase("N"))
     {
         access_add="disabled";
     }
     if(alw_del1.equalsIgnoreCase("N"))
     {
    	 alw_del1="disabled";
     }
     if(alw_upd1.equalsIgnoreCase("N"))
     {
         access_update="disabled";
         acess_update_readonly="readonly";
         access_update_href="display:none;";
     }
     if(alw_print1.equalsIgnoreCase("N"))
     {
         access_print="disabled";
         acess_print_readonly="readonly";
         acess_print_disabled="disabled";
         access_print_href="display:none;";
     }
//sra1-14/aug/09
String update="";
 if(alw_upd1.equalsIgnoreCase("n"))
{
	update="readonly" ;
	
}
%> 
 <div class="tab-content">
<div class="tab-pane active" id="add_new_action">
<div class="box mb-0">
<div class="box-body">

 <div class="box-header with-border">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;"> 
				
 				<form class="form-horizontal"  name="pass" method="post" action="../servlet/Frm_admin">
 				
 				<input type="hidden" name="modCd" value="<%=modCd%>">
				<input type="hidden" name="formId" value="<%=formId%>">
				<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
				 				
				 				 
					<div class="col-xs-2" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >	</table>
					    </div>
				    </div>		
					
					<div class="col-xs-8" >
					<h2 class="sub-header" align="center" id="head"><%=formName %></h2>
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >
				    		<%if(!msg.equals("")){ %>
							    	<tr>
							    		<td colspan="2" class="text-center" style="color: blue;font-weight: bold;font-size: 13px;"><%=msg %></td>
							    	</tr>
							    	
						    	<%} %>	
						    	<%if(!error_msg.equals("")){ %>
							    	<tr>
							    		<td colspan="2" class="text-center" style="color: red;font-weight: bold;font-size: 13px;"><%=error_msg %></td>
							    	</tr>
						    	<%} %>
				    			<tr >
				    				<th class="success">User Name</th>
				    				<td><%=session.getAttribute("userid")%><input type=hidden name=user_id size=7 maxlength=<%=u_max%> value='<%=session.getAttribute("userid")%>' readonly></td>
				    			</tr>
				    				
				    			<tr >	
				    				<th class="success">Current Password</th>
				    				<td><input type=password name=passwd size=18 title="Enter your current password"> </td>
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
							    		Note : <font color="red">The same password will be for the FMS</font> 
							    	</td>
							    </tr>	
								
								<tr>
						    		<td align="center" colspan="2"  style="background-color: white;" class="text-center">
						    			<button type="button" class="btn btn-warning" onclick="goBck('<%=selModule_cd%>','<%=head_tab%>');">Back  </button>
						    			<button type="reset" class="btn btn-info" > Reset  </button>
						    			<button type="button"  class="btn btn-success" onclick="checkAll();" id="subBtn" value="sub" <%=access_add %>>Submit </button>
						    		</td>
						    	</tr>
				    		</table>
				    	</div>
				    </div>
				    
				    <input type=hidden name="option" value="Change_Password">
					<input type=hidden name="formId" value="<%=formId%>">
					<input  type="hidden" name="head_tab"  value="<%=head_tab%>">
					<input  type="hidden" name="module_cd"  value="<%=selModule_cd%>">

				    <input  type="hidden" name="write_permission"  value="<%=alw_add1%>">
					<input  type="hidden" name="delete_permission"  value="<%=alw_del1%>">
					<input  type="hidden" name="print_permission"  value="<%=alw_print1%>">
					<input  type="hidden" name="check_permission"  value="<%=alw_view1%>">
					<input  type="hidden" name="authorize_permission"  value="<%=alw_upd1%>">
					<input  type="hidden" name="approve_permission"  value="">
					<input  type="hidden" name="audit_permission"  value="">
					
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