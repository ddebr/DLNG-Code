<%@ include file="../sess/Expire.jsp" %>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html>
<html lang="en">

<head>

<script src="../js/admin.js"></script>
<style type="text/css">
.s-red{
color: red;
font-size: 18px;
}

</style>
<script type="text/javascript">
function doInsertion(access)
{
	if(access == 'Y'){
		var flag = true;
		var message = "Cannot Be Inserted - Read The Following Requests !!! \n";
		  
		var sub_menu_id = document.forms[0].sub_menu_id.value;
		var sub_menu_nm = document.forms[0].sub_menu_nm.value;
		var module_id = document.forms[0].module_id.value;
		var sub_menu_path = document.forms[0].sub_menu_path.value;
	// 	var group_id = document.forms[0].group_id.value;
		var grp_id = document.forms[0].grp_id.value; 
		
		if(sub_menu_id=='0')
		{
			flag = false;
			message += "Sub Menu ID Is Not Defined ... \n";
		}
		if(sub_menu_nm=='' || sub_menu_nm==' ' || sub_menu_nm==null)
		{
			flag = false;
			message += "Please Enter The Proper Sub Menu Name  ... \n";
		}
		if(module_id=='0')
		{
			flag = false;
			message += "Please Select The Proper Module Name From Drop Down List ... \n";
		}
	 if(grp_id=='0'||grp_id=='')
		{
			flag = false;
			message += "Please Select The Proper Group Name From Drop Down List ... \n";
		} 
		if(sub_menu_path=='' || sub_menu_path==' ' || sub_menu_path==null)
		{
			flag = false;
			message += "Please Enter The Proper Sub Menu Location Path ... \n";
		}
		/*if(group_id=='0')
		{
			flag = false;
			message += "Please Select Atleast One Proper Group Name From Drop Down List ... \n";
		}*/
		
		if(!flag)
	    {	
			alert(message);
			return flag;
		}
		else
		{
	    	var a = confirm('Do You Want To Insert Entered Sub Menu Details ?');
			if(a)
			{
				document.forms[0].submit();		       
		    }
		}
	}else{
		alert('Sorry ! You Have No Access Rights..!')
	}	
}
function refresh(){
	

	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
// alert(module_cd+"******"+head_tab)	
 var moduleId = document.forms[0].module_id.value;
 var subMenuName = document.forms[0].sub_menu_nm.value;
 var menu_path = document.forms[0].sub_menu_path.value;
 
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
// 	var approve_permission = document.forms[0].approve_permission.value;
// 	var audit_permission = document.forms[0].audit_permission.value;
	
 var url = "../admin/frm_mst_administrator.jsp?moduleId="+moduleId+"&SubmenuName="+subMenuName+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission;
 
 location.replace(url);
}

function setData(moduleId,menuName){
 	document.forms[0].module_id.value = moduleId;
 	document.forms[0].sub_menu_nm.value = menuName;
}

function fillVal()
{
	if (document.forms[0].grp_id.value != 'other')
	{
		//url="frm_fuel_const_mst.jsp?hidden_fuel_cd="+document.forms[0].fuel_Name.value;
		//location.replace(url);
		//document.forms[0].cdFlag.value = '0';
		//document.forms[0].fuelNm.value=document.forms[0].fuel_Name[document.forms[0].fuel_Name.selectedIndex].innerText;
	}
}

function hidegrp_id()
{	
	if (document.forms[0].grp_id.value == 'other')
	{
		document.forms[0].new_grp_nm.style.visibility='visible'; 
		document.forms[0].new_grp_nm.focus();
		document.forms[0].grpNm.value = '';
	}
	else
	{
		document.forms[0].new_grp_nm.style.visibility='hidden'; 
		document.forms[0].grpNm.value=document.forms[0].grp_id[document.forms[0].grp_id.selectedIndex].innerText;
	}
	
}
</script>
</head>

<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="menu_mst" scope="page"/>

<%
String username=(String)session.getAttribute("username");
//System.out.println("nmaeeeeeeeeeeeeeeeeee"+username);
menu_mst.setUsername(username);
//    
  String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
  String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
  String sub_menu_nm = request.getParameter("sub_menu_nm")==null?"":request.getParameter("sub_menu_nm");
  String moduleId = request.getParameter("moduleId")==null?"0":request.getParameter("moduleId");
  String SubmenuName = request.getParameter("SubmenuName")==null?"":request.getParameter("SubmenuName");
  
  String alw_add1 = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
  String alw_view1 = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
  String alw_upd1 = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
  String alw_del1 = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
  String alw_print1 = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");
  
  	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200421
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200421
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200421
  
//   menu_mst.setFormID(formId);
  menu_mst.setSet_module_id(moduleId);
  menu_mst.setCallFlag("Module_List_Dtl");
  
  menu_mst.init();
    	
	
	Vector module_ids = menu_mst.getModuleIds();
	Vector module_nms = menu_mst.getModuleNms();
	Vector group_ids = menu_mst.getGroupIds();
	Vector group_nms = menu_mst.getGroupNms();
	String sub_menu_id = menu_mst.getSubMenuId();
	System.out.println("alw_add1****"+alw_add1);
	String formName = menu_mst.getFormName();
	Vector V_mod_group_id = menu_mst.getV_mod_group_id();
  	Vector V_mod_group_nm = menu_mst.getV_mod_group_nm();
  	moduleId = menu_mst.getSet_module_id();
%>
<body>

<div class="tab-content">
<div class="tab-pane active" id="add_new_action">
<div class="box mb-0">
<div class="box-body">

 <div class="box-header with-border">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;"> 
 				<form class="form-horizontal"  action="../servlet/Frm_admin" method="post">
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
				<div class="col-xs-1" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >	</table>
					    </div>
				    </div>	
				<div class="col-xs-10" >
					<h2 class="sub-header" align="center" id="head">Menu Insertion</h2>
					<div class="table-responsive"  >
					<table class="table table-striped" >
						<%if(!msg.equals("")){ %>	
							<tr>
								<td style="color: blue" colspan="2"><%=msg %></td>
							</tr>
						<%} %>
						<%if(!error_msg.equals("")){ %>	
							<tr>
								<td style="color: red" colspan="2"><%=error_msg %></td>
							</tr>
						<%} %>
						<tr>
							<th class="success">Select Module Name</th>
							<td>
								<select name="module_id" onchange="refresh();">
							
									<option value="0"> -- Select -- </option>
									<%	for(int i=0;i<module_ids.size();i++)
										{	%>
											<option value="<%=module_ids.elementAt(i)%>"><%=module_nms.elementAt(i)%></option>
									<%	}	%>
								</select>
								
								<script>
									document.forms[0].module_id.value="<%=moduleId%>";
// 									alert(document.forms[0].module_id.value)
								</script>
							</td>
						</tr>
						
						 <tr>
							<th class="success">Select Group Name</th>
							<td>
								<select name="grp_id" onchange="hidegrp_id();fillVal();" onblur="hidegrp_id();fillVal();">
									<option value="0"> -- Select -- </option>
								<%for(int i=0;i<V_mod_group_id.size();i++){	%>
									<option value="<%=V_mod_group_id.elementAt(i)%>"><%=V_mod_group_nm.elementAt(i)%></option>
								<%}	%>	
									<option value="other">other</option>
								</select>
								<input type="hidden" name="grpNm" value="">
						 	 	<input type="text" name="new_grp_nm" value="" size="40" maxLength="50" style="visibility: hidden;">
							</td>
						</tr> 
					
						<tr>
							<th class="success">Menu Name</th>
							<td>
								<input type="text" name="sub_menu_nm" value="" size="40" maxLength="50" title="Max Length 50 chars">
								<input type="hidden" name="sub_menu_id" value="<%=sub_menu_id%>">
							</td>
						</tr>
						
					<tr >
						<th class="success">Menu Path</th>			
						<td>
							<input type="text" name="sub_menu_path" value="" size="40" maxLength="60" title="Max Length 60 chars">
						</td>
					</tr>
						
					<tr>
						<th class="success">Select Menu Group</th>
						<td>	
							<select name="group_id" multiple>
								<option value="0" selected> -- Select -- </option>
								<%	for(int i=0;i<group_ids.size();i++)
									{	
									System.out.println(group_nms.elementAt(i));%>
									
										<option value="<%=group_ids.elementAt(i)%>"><%=group_nms.elementAt(i)%></option>
								<%	}	%>
							</select>&nbsp;<font face='verdana' size='-1'>Press Ctrl/Shift For Multiple Selection</font>
						</td>
					</tr>
						
						<tr>
							<th class="success" >Select Menu Rights</th>
							<td>
								<input type="checkBox" name="View" value="Y">&nbsp;<font face='verdana' size='-1'>Read</font>&nbsp;&nbsp;
								<input type="checkBox" name="Add" value="Y">&nbsp;<font face='verdana' size='-1'>Write</font>&nbsp;&nbsp;
								<input type="checkBox" name="Delete" value="Y">&nbsp;<font face='verdana' size='-1'>Delete</font>&nbsp;&nbsp;
								<input type="checkBox" name="Print" value="Y">&nbsp;<font face='verdana' size='-1'>Print</font>&nbsp;&nbsp;
							</td>
						</tr>
						
						<tr>
							<th class="success" >Select Menu Type</th>
							<td>
								<select name="menu_type">
									<option value="F">Form</option>
									<option value="R">Report</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" class="text-center" style="background-color: white;" >
<%-- 								<button type="button" class="btn btn-warning" onclick="goBck('<%=selModule_cd%>','<%=head_tab%>');">Back  </button> --%>
								<button type="button"  class="btn btn-success" onclick="doInsertion('<%=alw_add1 %>');" id="subBtn" value="sub">Submit </button>
							</td>
						</tr>
								
					</table>
					</div>
					</div>
<%-- 						<input type="hidden" name="formId" value="<%=formId%>"> --%>
						<input type="hidden" name="option" value="Insert_Sub_Menu_Dtl">
<%-- 						<input type="hidden" name="head_tab" value="<%=head_tab%>"> --%>
						
						<input type="hidden" name="write_permission" value="<%=alw_add1%>">
						<input type="hidden" name="delete_permission" value="<%=alw_del1%>">
						<input type="hidden" name="print_permission" value="<%=alw_print1%>">
<%-- 					<input type="hidden" name="approve_permission" value="<%=approve_permission%>"> --%>
<%-- 					<input type="hidden" name="audit_permission" value="<%=audit_permission%>"> --%>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>					