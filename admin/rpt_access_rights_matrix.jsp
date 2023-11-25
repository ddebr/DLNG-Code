<!DOCTYPE html>
<%@ page errorPage="../home/ExceptionHandler.jsp" %>
<%@ include file="../sess/Expire.jsp" %>
<%@ page buffer="128kb"%>
<%@ page import="java.util.Vector" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="javascript" src="../js/util1.js"></script>
<script language="javascript" src="../js/fms.js"></script>

<title>DLNG | Access Rights Matrix</title>

<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" >
<link rel="stylesheet" href="../css/pt_sans.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js" ></script>
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
function OpenXLS()
{
	var newWindow1
	if (!newWindow1 || newWindow1.closed){
			newWindow1 = window.open("../admin/xls_access_rights_matrix.jsp");
	}
	else
	{
			newWindow1 = window.open("../admin/xls_access_rights_matrix.jsp");
	}
}

</script>

</head>

<jsp:useBean class="com.hazira.hlpl.tlu.admin.DataBean_Admin" id="access_rights" scope="page"/>
<%
	String status= request.getParameter("status")==null?"":request.getParameter("status");	  
	String write_permission = (String)session.getAttribute("write_permission") ==null?"":session.getAttribute("write_permission").toString();
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"":session.getAttribute("delete_permission").toString();
	String print_permission = (String)session.getAttribute("print_permission") ==null?"":session.getAttribute("print_permission").toString();
	String approve_permission = (String)session.getAttribute("approve_permission") ==null?"":session.getAttribute("approve_permission").toString();
	String audit_permission = (String)session.getAttribute("audit_permission") ==null?"":session.getAttribute("audit_permission").toString();
	
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200218
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200218
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200218
	
	access_rights.setFormID(formId);
	access_rights.setCallFlag("GENERATE_ACCESS_RIGHTS_MATRIX");
	access_rights.init();

	String formName = access_rights.getFormName();
	
	//Following (9) Vector Getter Methods has been defined By Samik Shah On 9th June, 2011 ...
	Vector V_Group_cd = access_rights.getV_Group_cd();
	Vector V_Group_nm = access_rights.getV_Group_nm();
	Vector V_User_cd = access_rights.getV_User_cd();
	Vector V_User_nm = access_rights.getV_User_nm();
	Vector V_Module_cd = access_rights.getV_Module_cd();
	Vector V_Module_nm = access_rights.getV_Module_nm();
	Vector V_Form_cd = access_rights.getV_Form_cd();
	Vector V_Form_nm = access_rights.getV_Form_nm();
	Vector V_Access_rights = access_rights.getV_Access_rights();
	
	String module_nm = "";
	int cnt = 0;
	int table_width = 18+24+8+(V_Group_cd.size()*3*7); 
// 	System.out.println("V_Group_cd---"+V_Group_cd.size());
%>
<body>
<div class="tab-content">
		<div class="tab-pane active" id="hcasreport">
		<!-- Default box -->
			<div class="box mb-0">
				<form name="" method="post">
				<div class="box-header with-border" style="background-color:#ffe57f;">
						<div class="form-group col-md-10">
							<h2  align="center" id="head"  class="text-center"> Access Rights Matrix</h2>
						</div>
						<div class="form-group col-md-2 text-right">
							<label></label>
							<div class="form-group">
								<button type="button" name="exp_xls" class="btn btn-warning" id="btn" onclick="fnExcelReport();" value="export" >Export To Excel</button>
							</div>
						</div>
				</div>		
					<div class="box-body table-responsive no-padding " style="overflow-x:auto;height: 400px;">
						<table class="table  table-bordered" id="example">
							<thead>  
								<tr  valign="bottom" class="info">
									<td >&nbsp;</td>
									<td >&nbsp;</td>
									<td><div align="left">&nbsp;<b>Groups</b></div></td>
									<%	for(int i=0; i<V_Group_cd.size(); i++)
										{	%>
											<td  colspan="3"><div align="center"><b><%=V_Group_nm.elementAt(i)%></b></div></td>
									<%	}	%>
								</tr>
								    
								<tr  valign="bottom" >
									<td >&nbsp;</td>
									<td >&nbsp;</td>
									<td><div align="left">&nbsp;Users</div></td>
									<%	for(int i=0; i<V_Group_cd.size(); i++)
										{	%>
											<td  colspan="3"><div align="center"><%=V_User_nm.elementAt(i)%></div></td>
									<%	}	%>
								</tr>
								<tr  valign="bottom" class="info">
									<td width="18%"><div align="left"><b>Module Name</b></div></td>
									<td width="24%"><div align="left"><b>Form/Report Name</b></div></td>
									<td width="8%"><div align="left">&nbsp;</div></td>
									<%	for(int i=0; i<V_Group_cd.size(); i++)
										{	%>
											<td width="7%" colspan="1"><div align="center">&nbsp;</div></td>
											<td width="7%" colspan="1"><div align="center">&nbsp;</div></td>
											<td width="7%" colspan="1"><div align="center">&nbsp;</div></td>
									<%	}	%>
								</tr>
								
								<%	for(int i=0; i<V_Form_cd.size(); i++)
									{	
										if(!module_nm.trim().equalsIgnoreCase(""+V_Module_nm.elementAt(i)))
										{
											module_nm = ""+V_Module_nm.elementAt(i);
											cnt = 0;
										}
										else
										{
											++cnt;
										}	%>
									<%	if(cnt==0)
										{	%>
											<tr  valign="bottom" class="info">
												<td >&nbsp;</td>
												<td >&nbsp;</td>
												<td><div align="left">&nbsp;<b>Rights</b></div></td>
												<%	for(int k=0; k<V_Group_cd.size(); k++)
													{	%>
														<td  colspan="1"><div align="center"><b>Read</b></div></td>
														<td  colspan="1"><div align="center"><b>Write</b></div></td>
														<td  colspan="1"><div align="center"><b>Approve</b></div></td>
												<%	}	%>
											</tr>
									<%	}	%>
										<tr  valign="middle">
											<td><div align="left"><%if(cnt==0){%><%=module_nm%><%}%></div></td>
											<td><div align="left"><%=V_Form_nm.elementAt(i)%></div></td>
											<td><div align="left">&nbsp;</div></td>
											<%	for(int j=0; j<V_Group_cd.size()*3; j++)
												{	%>
													<td colspan="1"><div align="center"><%=((Vector)V_Access_rights.elementAt(i)).elementAt(j)%></div></td>
											<%	}	%>
										</tr>
								<%	}	%>
								
								<tr  valign="middle">
									<td><div align="left">&nbsp;</div></td>
									<td><div align="left">&nbsp;</div></td>
									<td><div align="left">&nbsp;</div></td>
									<%	for(int i=0; i<V_Group_cd.size(); i++)
										{	%>
											<td colspan="1"><div align="center">&nbsp;</div></td>
											<td colspan="1"><div align="center">&nbsp;</div></td>
											<td colspan="1"><div align="center">&nbsp;</div></td>
									<%	}	%>
								</tr>
							</thead> 
							
						</table>
					</div>
		

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">

<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">

</form>
</div>
</div>
</div>

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
</body>
</html>