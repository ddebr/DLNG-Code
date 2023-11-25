<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ page import="java.util.*"%>
<%@ include file="../sess/Expire.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title> ATS </title>
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
<script type="text/javascript">

function checkAll()
{
	flg = true;

	if(document.forms[0].emp_id_size.value=='0')
	{
		alert('There Is No User In The User Drop Down List !!!');
    	flg = false;
	}	
	
	if(document.forms[0].chk.checked && document.forms[0].emp_id_size.value!='0')
	{
	   	if(document.forms[0].party1.value == document.forms[0].emp_pass.value)
	   	{
	    	alert('Password Should Be Different From User Name !!!');
	    	flg = false;
	   	}
	   	if(flg)
	 	{
	 		if(document.forms[0].emp_pass.value==null || document.forms[0].emp_pass.value=="" || document.forms[0].emp_pass.value==" ")
	 		{
	 			alert('Password Can Not Be Blank !!!');
	  			flg = false;
	 		}
	 	}
	}
	
	if(flg)
	{
		var a = confirm('Do You Want To Release Lock For The Selected User ?');
   		if(a)
 		{
   			var qual2 = document.forms[0].party1.selectedIndex;
  			document.forms[0].emp_id.value = document.forms[0].party1.options[qual2].value;
  			document.forms[0].option.value = "User_Release_Lock";
  			document.forms[0].submit();
 		}
 		else
  		{
   			return false;
  		}
 	}
}


function checkAll2()
{
	var flg = true;
	var change_passwd = document.forms[0].change_passwd.value;
// 	alert(change_passwd)
	if(change_passwd==null || change_passwd=='' || change_passwd==' ' || change_passwd=='  ' || change_passwd.length<8)
	{
	 	alert('Password Can Not Be Blank !!!');
		flg = false;	
	}
	if(flg)
	{
		var a = confirm('Do You Want To Reset Password for the selected User ?');
   		if(a)
 		{
   			document.forms[0].option.value = "User_Reset_Password";
   			document.forms[0].submit();
 		}
 		else
  		{
   			return false;
  		}
 	}
}


function chkClick()
{
 	if(document.forms[0].chk.checked)
  	{
   		document.forms[0].passwd.style.visibility = "visible";
   		document.forms[0].emp_pass.style.visibility = "visible";
   		document.forms[0].emp_pass.disabled = false;
   		
   		var len = parseInt(""+document.forms[0].emp_id_size.value);
   		
   		if(len>1)
   		{
  			var qual2 = document.forms[0].party1.selectedIndex;
  			document.forms[0].emp_pass.value = document.forms[0].user_id[qual2].value;
  		}
  		else
  		{
  			document.forms[0].emp_pass.value = document.forms[0].user_id.value;
  		}
  	}
  	else
   	{
   		document.forms[0].passwd.style.visibility = "hidden";
   		document.forms[0].emp_pass.style.visibility = "hidden";
   	}
}


function mes(i)
{ 
    if(i==0)
    {
        //alert("no user is locked");
    }
}


function setUserID(sz)
{
	var size = parseInt(sz);
	
	if(size==1)
	{
		document.forms[0].change_passwd.value = document.forms[0].user_cd.value;
	}
	else if(size>1)
	{
		var qual2 = document.forms[0].user_code.selectedIndex;
	  	document.forms[0].change_passwd.value = document.forms[0].user_cd[qual2].value;
	}
}


function checkPassword(obj,min_len)
{
	var min_length = parseInt(""+min_len);
	var pass_length = parseInt(""+obj.value.length);
	
	if(pass_length<min_length)
	{
		alert("Minimum Password Length is : "+min_length+" alphanumeric chars !!!");
		obj.value = '';
	}	
}

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="rel_lock" scope="page"/>
<%

	String username=(String)session.getAttribute("username");
	rel_lock.setUsername(username);
	
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
 	String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
    String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
    String head_tab = request.getParameter("head_tab")==null?"":request.getParameter("head_tab");
//  	 System.out.println("flag****"+flag);
    String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    
    String alw_add1 = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
    String alw_view1 = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
    String alw_upd1 = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
    String alw_del1 = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
    String alw_print1 = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");
    String selModule_cd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
	
    rel_lock.setFormID(formId);
    rel_lock.setSelModule(selModule_cd);
    rel_lock.setHead_tab(head_tab);
	rel_lock.setCallFlag("Lock_Info");
    rel_lock.init();
	
    int cnt = rel_lock.getCount();
	String emp_id[] = null;
	String emp_name[] = null;
	String emp_abr[] = null;
	if (cnt > 0)
	{
        emp_id=rel_lock.getEmp_id();
        emp_name=rel_lock.getEmp_nm();
        emp_abr=rel_lock.getEmp_abr();
	}

	String formName = rel_lock.getFormName();
//	System.out.println("ABC :" +emp_id.length); 

	String [] emp_code = rel_lock.getEmp_code();
    String [] emp_nm = rel_lock.getEmp_name();
    String [] emp_abbr = rel_lock.getEmp_abbr();
    
    int count = emp_code.length;
    
    int u_min = rel_lock.getU_min();
	int u_max = rel_lock.getU_max();
	int p_min = rel_lock.getP_min();
	int p_max = rel_lock.getP_max();
%> 

<body onload="mes(<%=rel_lock.getCount()%>);">
<%@ include file="../home/header.jsp"%>
<%
	String form_nm = "Release Lock/password";
%>
<div class="tab-content">
<div class="tab-pane active" id="">
<div class="box mb-0">
<div class="box-body">

 <div class="box-header with-border">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;"> 
				
 				<form name="frm_admin_release_lock" method="post" action="../servlet/Frm_admin">
					<div class="col-xs-7" >
					<h2 class="sub-header" align="center" id="head"><%=formName %></h2>
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >
				    			<tr>
				    				<th class="success">User Name</th>
				    				<td>
				    					<select name=party1>
								   		<%	if(cnt>0)
								   			{
								   				for(int z=0;z<emp_id.length;z++)
								   				{	%>
								          			<option value='<%=emp_id[z]%>'><%=emp_name[z]%></option>
								        <%		}
								   			}
								   			else
								   			{	%>
								   				<option value="nouser">  NO User had been locked  </option>
								   		<%	}	%>
										</select>&nbsp;
										<%	if(cnt>0)
											{	
												for(int z=0;z<emp_id.length;z++)
								   				{	%>
								          			<input type="hidden" name="user_id" value="<%=emp_abr[z]%>">
								        <%		}
											}
											else
											{	%>
												<input type="hidden" name="user_id" value="">
										<%	}	%>
				    				</td>
				    				<td>
										<b>Reset Password ?&nbsp;<input type="checkbox" name="chk" value="Y" onClick="chkClick();"></b>
    								</td>
								</tr>
								<tr> 
									<th class="success">
										<b><input type="text" name="passwd" maxlength=<%=p_max%> style="visibility:hidden;font-family:Verdana,Arial,Helvitca,sans-serif;border:0; background-color:#E9E9E9;font-weight:bold;" size="10" value="Password"></b>
									</th>
									<td>
								    	<input type="password" name="emp_pass" maxlength=<%=p_max%> size=17  disabled style="visibility:hidden;" title="Max Size 15 chars" onBlur="checkPassword(this,'<%=p_min%>');">
									</td>
									<td>
										&nbsp;&nbsp;
									</td>
								</tr> 					
																			

							 <tr style="background-color: white;">
								<td colspan="3"> 
							    	<div align="center">
							    	<%	if(alw_add1.trim().equalsIgnoreCase("Y"))
										{	%>
											<button type="button" class="btn btn-warning" onclick="goBck('<%=selModule_cd%>','<%=head_tab%>');">Back  </button>
							    			<button type="button"  name="release" class="btn btn-success" onclick="checkAll();" id="subBtn" value="sub">Release Lock </button>
							    	<%	}
							    		else
							    		{	%>
							    			<button type="button" class="btn btn-warning" onclick="goBck('<%=selModule_cd%>','<%=head_tab%>');">Back  </button>
							    			<button type="button"  name="release" class="btn btn-success" onclick="" id="subBtn" value="sub" disabled="disabled">Release Lock </button>
							    	<%	}	%>
									</div>
							    </td>
							</tr>
						</table>
						</div>
					</div>
						<%	if(count>0){%>
						
						<div class="col-xs-5" >
						<h2 class="sub-header" align="center" id="head">Reset Password</h2>
				  			<div class="table-responsive"  >
				    			<table class="table table-striped"  >
				    				<tr>
				    					<th class="success">User Name</th>
				    					<td colspan="">
											<select name="user_code" onChange="setUserID('<%=count%>');">
									   		<%	for(int z=0;z<emp_code.length;z++)
									   			{	%>
									          		<option value="<%=emp_code[z]%>"><%=emp_nm[z]%></option>
									        <%	}	%>   			
											</select>
											<%	for(int z=0;z<emp_code.length;z++)
									   			{	%>
									          		<input type="hidden" name="user_cd" value="<%=emp_abbr[z]%>">
									        <%	}	%>
									    </td>
				    				</tr>
				    				
					    			<tr>
									    <th  class="success">
											<b>Default Password</b>
									    </th>
									    <td>
											<b><input type="text" name="change_passwd" size="10" maxlength="<%=p_max%>" value="<%=emp_abbr[0]%>"  onchange="checkPassword(this,'<%=p_min%>');" ></b>
										</td>
									</tr>
									<tr>
										<td colspan=2>
											<font color="red" size="2">
						 						Password to be 8 chars long &amp; contain at least 1 Caps, 1 alphabet(small),1 Number and 1 Special. Char&nbsp;
						 						Password to be minimum of <%=p_min%> chars long &amp; maximum of <%=p_max%> chars long.
						 					</font>
						 				</td>
									</tr>
									<tr >
										<td colspan="2"> 
									    	<div align="center">
									    	<%	if(alw_add1.trim().equalsIgnoreCase("Y"))
												{	%>
													<button type="reset" class="btn btn-info" name="reset_pass" value="Reset Password" onClick="checkAll2();"> Reset Password  </button>
									    	<%	}
									    		else
									    		{	%>
									    			<button type="reset" class="btn btn-info" name="reset_pass" value="Reset Password" onClick="#" disabled="disabled"> Reset Password  </button>
									    	<%	}	%>
											</div>
									    </td>
									</tr>
				    			</table>
				    		</div>
				    	</div>	
				    		
				    	<%} %>			


<input type="hidden" name="emp_id">
<input type="hidden" name="emp_id_size" value="<%=cnt%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="option" value="User_Release_Lock">

<input  type="hidden" name="write_permission"  value="<%=alw_add1%>">
<input  type="hidden" name="delete_permission"  value="<%=alw_del1%>">
<input  type="hidden" name="print_permission"  value="<%=alw_print1%>">
<input  type="hidden" name="check_permission"  value="<%=alw_view1%>">
<input  type="hidden" name="authorize_permission"  value="<%=alw_upd1%>">
<input  type="hidden" name="approve_permission"  value="">
<input  type="hidden" name="audit_permission"  value="">
</div>
</div>

</form>
</body>
</html>