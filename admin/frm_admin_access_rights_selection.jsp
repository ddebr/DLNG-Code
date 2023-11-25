<%-- <%@ page errorPage="../home/ExceptionHandler.jsp"%> --%>
<%-- <%@ page import="java.util.*"%> --%>
<%-- <%@ include file="../sess/Expire.jsp" %> --%>

<html>
<head>
<%@ page import="java.util.Vector" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">



<style type="text/css">
.s-red{
color: red;
font-size: 18px;
}

.s-green{
color: green;
font-size: 14px;
}

.size{
font-size: 15px;
}

</style>

<script>

function doInsertion(alw_write)
{
	if(alw_write == 'Y'){
		var flag = true;
		var message = "Cannot Be Inserted - Read The Following Requests !!! \n";
		
		var count = parseInt(document.forms[0].count.value);
		var i = 0;
		
		if(count==1)
		{
			if(document.forms[0].addPerm.checked)
			{
				document.forms[0].add_perm.value = "Y";
			}
			else
			{
				document.forms[0].add_perm.value = "N";
			}
			
			if(document.forms[0].deletePerm.checked)
			{
				document.forms[0].delete_perm.value = "Y";
			}
			else
			{
				document.forms[0].delete_perm.value = "N";
			}
			
			if(document.forms[0].viewPrintPerm.checked)
			{
				document.forms[0].view_print_perm.value = "Y";
			}
			else
			{
				document.forms[0].view_print_perm.value = "N";
			}
			
			if(document.forms[0].CheckPerm.checked)
			{
				document.forms[0].Check_perm.value = "Y";
			}
			else
			{
				document.forms[0].Check_perm.value = "N";
			}
			
			if(document.forms[0].AuthorizePerm.checked)
			{
				document.forms[0].Authorize_perm.value = "Y";
			}
			else
			{
				document.forms[0].Authorize_perm.value = "N";
			}
			
			if(document.forms[0].PrintPerm.checked)
			{
				document.forms[0].print_perm.value = "Y";
			}
			else
			{
				document.forms[0].print_perm.value = "N";
			}
			
			if(document.forms[0].UpdatePerm.checked)
			{
				document.forms[0].Update_perm.value = "Y";
			}
			else
			{
				document.forms[0].Update_perm.value = "N";
			}
			
			if(document.forms[0].grantPerm.checked)
			{
				document.forms[0].grant_perm.value = "Y";
			}
			else
			{
				document.forms[0].grant_perm.value = "N";
			}
		}
		else if(count>1)
		{
			for(i=0;i<count;i++)
			{
				if(document.forms[0].addPerm[i].checked)
				{
					document.forms[0].add_perm[i].value = "Y";
				}
				else
				{
					document.forms[0].add_perm[i].value = "N";
				}
				
				if(document.forms[0].deletePerm[i].checked)
				{
					document.forms[0].delete_perm[i].value = "Y";
				}
				else
				{
					document.forms[0].delete_perm[i].value = "N";
				}
				
				if(document.forms[0].viewPrintPerm[i].checked)
				{
					document.forms[0].view_print_perm[i].value = "Y";
				}
				else
				{
					document.forms[0].view_print_perm[i].value = "N";
				}
				
				if(document.forms[0].CheckPerm[i].checked)
				{
					document.forms[0].Check_perm[i].value = "Y";
				}
				else
				{
					document.forms[0].Check_perm[i].value = "N";
				}
				
				if(document.forms[0].AuthorizePerm[i].checked)
				{
					document.forms[0].Authorize_perm[i].value = "Y";
				}
				else
				{
					document.forms[0].Authorize_perm[i].value = "N";
				}
			
				if(document.forms[0].PrintPerm[i].checked)
				{
					document.forms[0].print_perm[i].value = "Y";
				}
				else
				{
					document.forms[0].print_perm[i].value = "N";
				}
				if(document.forms[0].UpdatePerm[i].checked)
				{
					document.forms[0].Update_perm[i].value = "Y";
				}
				else
				{
					document.forms[0].Update_perm[i].value = "N";
				}
				
				if(document.forms[0].grantPerm[i].checked)
				{
					document.forms[0].grant_perm[i].value = "Y";
				}
				else
				{
					document.forms[0].grant_perm[i].value = "N";
				}
			}
		}
				
		if(!flag)
	    {	
			alert(message);
			return flag;
		}
		else
		{
	    	var a = confirm('Do You Want To Insert/Modify Access Rights Details ?');
			if(a)
			{
				document.forms[0].submit();		       
		    }
		}
	}else{
		alert('Sorry!.. You have no access rights')
	}
}

function checkUncheckAll()
{
	var count = parseInt(document.forms[0].count.value);
	var chk = document.forms[0].chkUNchk.value;
	var i = 0;
	
	if(count==1)
	{
		if(chk=='Check All')
		{
			document.forms[0].addPerm.checked = true;
			document.forms[0].deletePerm.checked = true;
			document.forms[0].viewPrintPerm.checked = true;
			document.forms[0].PrintPerm.checked = true;
			document.forms[0].CheckPerm.checked = true;
			document.forms[0].AuthorizePerm.checked = true;
			document.forms[0].UpdatePerm.checked = true;
			document.forms[0].grantPerm.checked = true;
		}
		else if(chk=='Un-Check All')
		{
			document.forms[0].addPerm.checked = false;
			document.forms[0].deletePerm.checked = false;
			document.forms[0].viewPrintPerm.checked = false;
			document.forms[0].PrintPerm.checked = false;
			document.forms[0].CheckPerm.checked = false;
			document.forms[0].AuthorizePerm.checked = false;
			document.forms[0].UpdatePerm.checked = false;
			document.forms[0].grantPerm.checked = false;
		}
	}
	else if(count>1)
	{
		if(chk=='Check All')
		{
			for(i=0;i<count;i++)
			{
				document.forms[0].addPerm[i].checked = true;
				document.forms[0].deletePerm[i].checked = true;
				document.forms[0].viewPrintPerm[i].checked = true;
				document.forms[0].PrintPerm[i].checked = true;
				document.forms[0].CheckPerm[i].checked = true;
				document.forms[0].AuthorizePerm[i].checked = true;
				document.forms[0].UpdatePerm[i].checked = true;
				document.forms[0].grantPerm[i].checked = true;
			}
		}
		else if(chk=='Un-Check All')
		{
			for(i=0;i<count;i++)
			{
				document.forms[0].addPerm[i].checked = false;
				document.forms[0].deletePerm[i].checked = false;
				document.forms[0].viewPrintPerm[i].checked = false;
				document.forms[0].PrintPerm[i].checked = false;
				document.forms[0].CheckPerm[i].checked = false;
				document.forms[0].AuthorizePerm[i].checked = false;
				document.forms[0].UpdatePerm[i].checked = false;
				document.forms[0].grantPerm[i].checked = false;
			}
		}	
	}
	
	if(chk=='Check All')
	{
		document.forms[0].chkUNchk.value = 'Un-Check All';
	}
	else if(chk=='Un-Check All')
	{
		document.forms[0].chkUNchk.value = 'Check All';
	}
}


function checkModuleWiseActivity(obj,act_cd,st_ind,end_ind)
{
	var activity_code = parseInt(act_cd);
	var start_index = parseInt(st_ind);
	var end_index = parseInt(end_ind);
	var object_value = obj.value;
	var i = 0;
	//alert("activity_code---"+activity_code);
	//alert("start_index---"+start_index);
	//alert("end_index---"+end_index);
	//alert("object_value--"+object_value);
	if(activity_code==1)
	{
		if(object_value=='Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].addPerm[i].checked = true;
			}
			//alert("object_value--"+object_value);
			obj.value = "De-Select";
			//alert("obj--"+obj.value);
		}
		else if(object_value=='De-Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].addPerm[i].checked = false;
			}
			obj.value = "Select";
		}
	}
	
	if(activity_code==2)
	{
		if(object_value=='Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].deletePerm[i].checked = true;
			}
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].deletePerm[i].checked = false;
			}
			obj.value = "Select";
		}
	}
	
	if(activity_code==3)
	{
		if(object_value=='Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].viewPrintPerm[i].checked = true;
			}
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].viewPrintPerm[i].checked = false;
			}
			obj.value = "Select";
		}
	}
	
	if(activity_code==4)
	{
		if(object_value=='Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].PrintPerm[i].checked = true;
			}
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].PrintPerm[i].checked = false;
			}
			obj.value = "Select";
		}
	}
	
	if(activity_code==5)
	{
		if(object_value=='Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].CheckPerm[i].checked = true;
			}
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].CheckPerm[i].checked = false;
			}
			obj.value = "Select";
		}
	}
	
	if(activity_code==6)
	{
		if(object_value=='Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].AuthorizePerm[i].checked = true;
			}
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].AuthorizePerm[i].checked = false;
			}
			obj.value = "Select";
		}
	}
	
	if(activity_code==7)
	{
		if(object_value=='Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].UpdatePerm[i].checked = true;
			}
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].UpdatePerm[i].checked = false;
			}
			obj.value = "Select";
		}
	}
	
	if(activity_code==8)
	{
		if(object_value=='Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].grantPerm[i].checked = true;
			}
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			for(i=start_index; i<=end_index; i++)
			{
				document.forms[0].grantPerm[i].checked = false;
			}
			obj.value = "Select";
		}
	}
}


function checkUncheckRowWise(ind,obj)
{
	var count = parseInt(""+document.forms[0].count.value);
	var index = parseInt(""+ind);
	var object_value = obj.value;
		
	if(count==1)
	{
		if(object_value=='Select')
		{
			document.forms[0].addPerm.checked = true;
			document.forms[0].deletePerm.checked = true;
			document.forms[0].viewPrintPerm.checked = true;
			document.forms[0].PrintPerm.checked = true;
			document.forms[0].CheckPerm.checked = true;
			document.forms[0].AuthorizePerm.checked = true;
			document.forms[0].UpdatePerm.checked = true;
			document.forms[0].grantPerm.checked = true;
			
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			document.forms[0].addPerm.checked = false;
			document.forms[0].deletePerm.checked = false;
			document.forms[0].viewPrintPerm.checked = false;
			document.forms[0].PrintPerm.checked = false;
			document.forms[0].CheckPerm.checked = false;
			document.forms[0].AuthorizePerm.checked = false;
			document.forms[0].UpdatePerm.checked = false;
			document.forms[0].grantPerm.checked = false;
			
			obj.value = "Select";
		}
	}
	else if(count>1)
	{
		if(object_value=='Select')
		{
			document.forms[0].addPerm[index].checked = true;
			document.forms[0].deletePerm[index].checked = true;
			document.forms[0].viewPrintPerm[index].checked = true;
			document.forms[0].PrintPerm[index].checked = true;
			document.forms[0].CheckPerm[index].checked = true;
			document.forms[0].AuthorizePerm[index].checked = true;
			document.forms[0].UpdatePerm[index].checked = true;
			document.forms[0].grantPerm[index].checked = true;
			
			obj.value = "De-Select";
		}
		else if(object_value=='De-Select')
		{
			document.forms[0].addPerm[index].checked = false;
			document.forms[0].deletePerm[index].checked = false;
			document.forms[0].viewPrintPerm[index].checked = false;
			document.forms[0].PrintPerm[index].checked = false;
			document.forms[0].CheckPerm[index].checked = false;
			document.forms[0].AuthorizePerm[index].checked = false;
			document.forms[0].UpdatePerm[index].checked = false;
			document.forms[0].grantPerm[index].checked = false;
			
			obj.value = "Select";
		}	
	}
}


function goBackToPreviousPage()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var group_id = document.forms[0].group_id.value;
	var module_id = document.forms[0].module_id.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("../admin/frm_mst_administrator.jsp?group_id="+group_id+"&module_id="+module_id+
			"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
			"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&approve_permission="+approve_permission+
			"&audit_permission="+audit_permission+"&modCd="+modCd+"&subModUrl="+subModUrl+"&formId="+formId);
}

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="fetch_rights" scope="page" />  
<%
    String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
    String module_id = request.getParameter("module_id")==null?"All":request.getParameter("module_id");
    String group_id = request.getParameter("group_id")==null?"0":request.getParameter("group_id");
    String group_nm = request.getParameter("group_nm")==null?"":request.getParameter("group_nm");
    
   /*  String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
	String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission"); */
	
	String write_permission = (String)session.getAttribute("write_permission") ==null?"N":session.getAttribute("write_permission").toString();
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"N":session.getAttribute("delete_permission").toString();
	String print_permission = (String)session.getAttribute("print_permission") ==null?"N":session.getAttribute("print_permission").toString();
	String approve_permission = (String)session.getAttribute("approve_permission") ==null?"N":session.getAttribute("approve_permission").toString();
	String audit_permission = (String)session.getAttribute("audit_permission") ==null?"N":session.getAttribute("audit_permission").toString();
	String update_permission = (String)session.getAttribute("update_permission") ==null?"N":session.getAttribute("update_permission").toString();
	
	String check_permission = (String)session.getAttribute("check_permission") ==null?"N":session.getAttribute("check_permission").toString();
	String authorize_permission = (String)session.getAttribute("audit_permission") ==null?"N":session.getAttribute("audit_permission").toString();

// 	String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");

	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200422
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200422
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200422    
// 	System.out.println("head_tab---"+head_tab);
	
    fetch_rights.setFormID(formId);
    fetch_rights.setCallFlag("Access_Rights_List");
    fetch_rights.setGroupId(group_id);
    fetch_rights.setModuleId(module_id);
    fetch_rights.init();
      	
    Vector module_ids = fetch_rights.getModuleIds();
	Vector module_nms = fetch_rights.getModuleNms();
	Vector sub_menu_ids = fetch_rights.getSubMenuIds();
	Vector sub_menu_nms = fetch_rights.getSubMenuNms();
	
	Vector view_print_perm = fetch_rights.getViewPrintPerm();
	Vector delete_perm = fetch_rights.getDeletePerm();
	Vector add_perm = fetch_rights.getAddPerm();
	Vector grant_perm = fetch_rights.getGrantPerm();
	Vector check_perm=fetch_rights.getCheckPerm();
	Vector authorize_perm=fetch_rights.getAuthorizePerm();
	Vector update_perm=fetch_rights.getUpdatePerm();
	Vector print_perm=fetch_rights.getPrintPerm();
	
	
	Vector view_print_perm_value = fetch_rights.getViewPrintPermValue();
	Vector delete_perm_value = fetch_rights.getDeletePermValue();
	Vector add_perm_value = fetch_rights.getAddPermValue();
	Vector grant_perm_value = fetch_rights.getGrantPermValue();
	Vector check_perm_value=fetch_rights.getCheckPermValue();
	Vector authorize_perm_value=fetch_rights.getAuthorizePermValue();
  	Vector update_perm_value=fetch_rights.getUpdatePermValue();
	Vector print_perm_value=fetch_rights.getPrintPermValue();
		
	//Following (2) Vector Getter Methods Has Been Defined By Samik Shah On 31st August, 2010 ...
	Vector V_module_start_index = fetch_rights.getV_module_start_index();
	Vector V_module_end_index = fetch_rights.getV_module_end_index();
	//System.out.println("V_module_start_index---"+V_module_start_index);
	//System.out.println("V_module_end_index---"+V_module_end_index);
	int cnt = 0;
	
	String formName = fetch_rights.getFormName();
%>  

<body>
<div class="tab-content">

<!--Add new Action  TAB START-->
<div class="tab-pane active" id="access_rights">
<!-- Default box -->
<div class="box mb-0">
<div class="box-body">
	<div class="box-header with-border">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;">
				<form class="form-horizontal" id="formId" name="rpt_admin_access_rights_selection" method="post" action="../servlet/Frm_admin">
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
					
					<div class="col-xs-12" >
					<h2 class="sub-header" align="center" id="head"> Access Rights Administration</h2>
					<div class="table-responsive"  >
					<table class="table table-striped" border="1">
						<tr>
							<td colspan="6" style="font-weight: bold;">Group Name : <%=group_nm%>
								<input type="hidden" name="group_id" value="<%=group_id%>">
								<input type="hidden" name="group_nm" value="<%=group_nm%>">
								<input type="hidden" name="module_id" value="<%=module_id%>">
							</td>
							<td colspan="6">
								<div align="right"><input type="button" name="chkUNchk" value="Check All" onClick="checkUncheckAll();" class="btn btn-info"></div>
							</td>			
						</tr>
						
						<tr class="success" style="font-size: 13px;" >
							<th rowspan="2" class="text-center">Module Name</th>
							<th rowspan="2" class="text-center">Form/Report Name</th>
							<th rowspan="2" class="text-center">Select</th>
							<th colspan="8" class="text-center">Access Rights List</th>
						</tr>
						
						<tr class="success" valign="bottom" style="font-size: 13px;">
							<th class="text-center">Read</th>
							<th class="text-center">Write<br>(Prepared)</th>
							<th class="text-center">Delete</th>
							<th class="text-center">Print</th>
							<th class="text-center">Check</th>
							<th class="text-center">Authorize<br>(Forward)</th>
							<th class="text-center">Approve<br>(Re-Assign)</th>
							<th class="text-center">Audit</th>
						</tr>
					<%
					// 						System.out.println("module_nms---"+module_nms+"--"+module_nms.size());
					for(int i=0;i<module_nms.size();i++)
						{	%>
							<%	if(!((""+module_nms.elementAt(i)).trim().equals("")))
								{	%>
									<tr style="font-size: 13px;">
										<td width="10%">
											<div align="left">
												&nbsp;
											</div>
										</td>
										<td width="20%">
											<div align="left">
												&nbsp;
											</div>
										</td>
										<td width="6%">
											<div align="left">
												&nbsp;
											</div>
										</td>
										<td width="8%">
											<div align="center">
												<input type="button" name="choose" title="Read Permission" value="Select" onClick="checkModuleWiseActivity(this,'3','<%=V_module_start_index.elementAt(cnt)%>','<%=V_module_end_index.elementAt(cnt)%>');">
											</div>
										</td>
										<td width="8%">
											<div align="center">
												<input type="button" name="choose" title="Write Permission" value="Select" onClick="checkModuleWiseActivity(this,'1','<%=V_module_start_index.elementAt(cnt)%>','<%=V_module_end_index.elementAt(cnt)%>');">
											</div>
										</td>
										<td width="8%">
											<div align="center">
												<input type="button" name="choose" title="Delete Permission" value="Select" onClick="checkModuleWiseActivity(this,'2','<%=V_module_start_index.elementAt(cnt)%>','<%=V_module_end_index.elementAt(cnt)%>');">
											</div>
										</td>
										<td width="8%">
											<div align="center">
												<input type="button" name="choose" title="Print Permission" value="Select" onClick="checkModuleWiseActivity(this,'4','<%=V_module_start_index.elementAt(cnt)%>','<%=V_module_end_index.elementAt(cnt)%>');">
											</div>
										</td>
										<td width="8%">
											<div align="center">
												<input type="button" name="choose" title="Check Permission" value="Select" onClick="checkModuleWiseActivity(this,'5','<%=V_module_start_index.elementAt(cnt)%>','<%=V_module_end_index.elementAt(cnt)%>');">
											</div>
										</td>
										<td width="8%">
											<div align="center">
												<input type="button" name="choose" title="Authorise Permission" value="Select" onClick="checkModuleWiseActivity(this,'6','<%=V_module_start_index.elementAt(cnt)%>','<%=V_module_end_index.elementAt(cnt)%>');">
											</div>
										</td>
										<td width="8%">
											<div align="center">
												<input type="button" name="choose" title="Approve Permission" value="Select" onClick="checkModuleWiseActivity(this,'7','<%=V_module_start_index.elementAt(cnt)%>','<%=V_module_end_index.elementAt(cnt)%>');">
											</div>
										</td>
										<td width="8%">
											<div align="center">
												<input type="button" name="choose" title="Audit Permission" value="Select" onClick="checkModuleWiseActivity(this,'8','<%=V_module_start_index.elementAt(cnt)%>','<%=V_module_end_index.elementAt(cnt)%>');">
											</div>
										</td>
									</tr>
							<%	++cnt;
								}	%>
							<tr style="font-size: 13px;">
								<td>
									<div align="left">
										<b><%=(String)module_nms.elementAt(i)%></b>
										<input type="hidden" name="module_ids" value="<%=(String)module_ids.elementAt(i)%>">
									</div>
								</td>
								<td>
									<div align="left">
										<b><%=(String)sub_menu_nms.elementAt(i)%></b>
										<input type="hidden" name="sub_menu_id" value="<%=(String)sub_menu_ids.elementAt(i)%>">
									</div>
								</td>
								<td>
									<div align="right">
										<input type="button" name="select_row" value="Select" onClick="checkUncheckRowWise('<%=i%>',this);">
									</div>
								</td>
								<td>
									<div align="center">
										<input type="checkBox" name="viewPrintPerm" title="Read Permission" value="<%=(String)view_print_perm_value.elementAt(i)%>" <%=(String)view_print_perm.elementAt(i)%>>
										<input type="hidden" name="view_print_perm" value="">
									</div>
								</td>
								<td>
									<div align="center">
										<input type="checkBox" name="addPerm" title="Write Permission" value="<%=(String)add_perm_value.elementAt(i)%>" <%=(String)add_perm.elementAt(i)%>>
										<input type="hidden" name="add_perm" value="">
									</div>
								</td>
								<td>
									<div align="center">
										<input type="checkBox" name="deletePerm" title="Delete Permission" value="<%=(String)delete_perm_value.elementAt(i)%>" <%=(String)delete_perm.elementAt(i)%>>
										<input type="hidden" name="delete_perm" value="">
									</div>
								</td>
								<td>
									<div align="center">
										<input type="checkBox" name="PrintPerm" title="Print Permission" value="<%=(String)print_perm_value.elementAt(i)%>" <%=(String)print_perm.elementAt(i)%>>
										<input type="hidden" name="print_perm" value="">
									</div>
								</td>
								<td>
									<div align="center">
										<input type="checkBox" name="CheckPerm" title="Check Permission" value="<%=(String)check_perm_value.elementAt(i)%>" <%=(String)check_perm.elementAt(i)%>>
										<input type="hidden" name="Check_perm" value="">
									</div>
								</td>
								<td>
									<div align="center">
										<input type="checkBox" name="AuthorizePerm" title="Authorise Permission" value="<%=(String)authorize_perm_value.elementAt(i)%>" <%=(String)authorize_perm.elementAt(i)%>>
										<input type="hidden" name="Authorize_perm" value="">
									</div>
								</td>
								<td>
									<div align="center">
										<input type="checkBox" name="UpdatePerm" title="Approve Permission" value="<%=(String)update_perm_value.elementAt(i)%>" <%=(String)update_perm.elementAt(i)%>>
										<input type="hidden" name="Update_perm" value="">
									</div>
								</td>
								<td>
									<div align="center">
										<input type="checkBox" name="grantPerm" title="Audit Permission" value="<%=(String)grant_perm_value.elementAt(i)%>" <%=(String)grant_perm.elementAt(i)%>>
										<input type="hidden" name="grant_perm" value="">
									</div>
								</td>
							</tr>
					<%	}	%>
						
						<tr>
							<td colspan="12" class="text-center" style="background-color: white;" >
								<button type="button" class="btn btn-warning" onclick="goBackToPreviousPage();">Back  </button>
								<button type="button"  class="btn btn-success" name="insertModifyAccessRights" value="Insert/Modify Access Rights" onClick="doInsertion('<%=write_permission%>');">Insert/Modify Access Rights </button>
							</td>
						</tr>
						<%if(!msg.equals("")){ %>
						<tr>
							<td class="text-center" colspan="12" style="color: blue;font-weight: bold;font-size: 13px;"><%=msg %></td>
						</tr>
						<%} %>
						<%if(!error_msg.equals("")){ %>
						<tr>
							<td class="text-center" colspan="12" style="color: red;font-weight: bold;font-size: 13px;"><%=error_msg %></td>
						</tr>
						<%} %>
						
					</table>
				</div>
			</div>
			<input type="hidden" name="count" value="<%=module_nms.size()%>">
			<input type="hidden" name="option" value="Insert_Access_Rights_Dtl">
			<input type="hidden" name="write_permission" value="<%=write_permission%>">
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
			<input type="hidden" name="print_permission" value="<%=print_permission%>">
			<input type="hidden" name="check_permission" value="<%=check_permission%>">
			<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
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
<% 
//sra1-12/aug/09
HttpSession accessrights_sess=request.getSession();
String print="";
Vector v1=new Vector();
Vector v2=new Vector();
if(accessrights_sess.getAttribute("pass3")==null)
{
	
	v2=v1;
}
else
{
	
	
v2=(Vector)accessrights_sess.getAttribute("pass3");
}

v1.add(view_print_perm);
v1.add(delete_perm);
v1.add(add_perm);
v1.add(grant_perm);
v1.add(check_perm);
v1.add(authorize_perm);
v1.add(update_perm);
v1.add(print_perm);
accessrights_sess.setAttribute("pass3",v1);
if(v1.equals(v2))
{
	print="[view->group->"+group_nm+"]";
}
else
{
	print="[update->"+group_nm+"]";
}

//new com.hlpl.hazira.tims.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: AccessRights Module:"+print);
%>
</body>
</html>