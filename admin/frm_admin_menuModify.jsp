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

<script src="../js/admin.js"></script>
<style type="text/css">
.s-red{
color: red;
font-size: 18px;
}

</style>
<script type="text/javascript">
function refreshForm()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var alw_add = document.forms[0].alw_add.value;
	var alw_upd = document.forms[0].alw_upd.value;
	var alw_del = document.forms[0].alw_del.value;
	var sub_menu_id = document.forms[0].sub_menu_id.value;
	var module_Id = document.forms[0].module_id.value;
	var grp_Id = document.forms[0].grp_id.value;

	location.replace("../admin/frm_mst_administrator.jsp?formId="+formId+"&sub_menu_id="+sub_menu_id+"&moduleId="+module_Id+"&grp_Id="+grp_Id+"&modCd="+modCd+"&subModUrl="+subModUrl+
			"&alw_add="+alw_add+"&alw_upd="+alw_upd+"&alw_del="+alw_del);
}

function doModify(access)
{
	if(access == 'Y'){
		var flag = true;
		var message = "Cannot Be Modified - Read The Following Requests !!! \n";
		  
		var sub_menu_id = document.forms[0].sub_menu_id.value;
		var sub_menu_nm = document.forms[0].sub_menu_nm.value;
		var module_id = document.forms[0].module_id.value;
		var sub_menu_path = document.forms[0].sub_menu_path.value;
		
		if(sub_menu_id=='0')
		{
			flag = false;
			message += "Please Select The Proper Sub Menu From Drop Down List ... \n";
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
		if(sub_menu_path=='' || sub_menu_path==' ' || sub_menu_path==null)
		{
			flag = false;
			message += "Please Enter The Proper Sub Menu Location Path ... \n";
		}
		
		if(!flag)
	    {	
			alert(message);
			return flag;
		}
		else
		{
	    	var a = confirm('Do You Want To Modify Selected Sub Menu Details ?');
			if(a)
			{
				//sra1-12/AUG/09
				 document.forms[0].old_submenu.value = document.forms[0].sub_menu_id[document.forms[0].sub_menu_id.selectedIndex].innerText;
			     document.forms[0].new_submenu.value = document.forms[0].sub_menu_nm.value;
				//
				
				document.forms[0].submit();		       
		    }
		}
	}else{
		alert('Sorry ! You Have No Access Rights..!')
	}	
}

</script>

</head>
<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="fetch" scope="page"/>
<%
//sra1-14/aug/09

String username=(String)session.getAttribute("username");
//System.out.println("nmaeeeeeeeeeeeeeeeeee"+username);
fetch.setUsername(username);
//   
	
   	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
  	String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
    String sub_menu_id = request.getParameter("sub_menu_id")==null?"0":request.getParameter("sub_menu_id");
    String module_Id = request.getParameter("module_Id")==null?"0":request.getParameter("module_Id");
    String grp_Id = request.getParameter("grp_Id")==null?"0":request.getParameter("grp_Id");
    
    
    String alw_add1 = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
    String alw_view1 = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
    String alw_upd1 = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
    String alw_del1 = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
    String alw_print1 = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");
    
    String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200421
 	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200421
 	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200421
    
    fetch.setCallFlag("Menu_List_Dtl");
    fetch.setSet_module_id(module_Id);
    fetch.setMod_group_id(grp_Id);
    fetch.setSubMenuId(sub_menu_id);
    
    fetch.init();
    
    Vector sub_menu_ids = fetch.getSubMenuIds();
	Vector sub_menu_nms = fetch.getSubMenuNms();
	Vector module_ids = fetch.getModuleIds();
	Vector module_nms = fetch.getModuleNms();
	
	String sub_menu_nm = fetch.getSubMenuNm();
	String module_id = fetch.getModuleId();
	String sub_menu_path = fetch.getSubMenuPath();
	String menu_type = fetch.getDocType();
	String grp_seq_no = fetch.getGrp_seq_no();
//	System.out.println("seq no"+grp_seq_no);
	
    String formName = fetch.getFormName();
    
    Vector V_form_seq_no = fetch.getV_form_seq_no();
    Vector V_frm_rpt_flag = fetch.getV_frm_rpt_flag();
    Vector V_mod_group_id =fetch.getV_mod_group_id();
    Vector V_mod_group_nm = fetch.getV_mod_group_nm();
   
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
					<input type="hidden" name="alw_add" value="<%=alw_add1%>">
					<input type="hidden" name="alw_del" value="<%=alw_del1%>">
					<input type="hidden" name="alw_upd" value="<%=alw_upd1%>">
				<div class="col-xs-1" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >	</table>
					    </div>
				    </div>	
				<div class="col-xs-10" >
					<h2 class="sub-header" align="center" id="head">Menu Modification</h2>
					<div class="table-responsive"  >
					<table class="table table-striped" >
							<%if(!msg.equals("")){ %>	
							<tr>
								<td style="color: blue;font-weight: bold;font-size: 13px;background-color: white;" colspan="2" class="text-center"><%=msg %></td>
							</tr>
						<%} %>
						<%if(!error_msg.equals("")){ %>	
							<tr>
								<td style="color: red;font-weight: bold;font-size: 13px;background-color: white;" colspan="2" class="text-center"><%=error_msg %></td>
							</tr>
						<%} %>
						<tr>
							<th class="success">Select Sub Menu</th>
							<td>
								<select name="sub_menu_id" onchange="refreshForm();">
									<option value="0"> -- Select -- </option>
									<%	for(int i=0;i<sub_menu_ids.size();i++)
										{	%>
											<option value="<%=sub_menu_ids.elementAt(i)%>"><%=sub_menu_nms.elementAt(i)%></option>
									<%	}	%>
								</select>
							</td>
						</tr>
						
						<tr>
							<th class="success">New Sub Menu Name</th>
							<td>
								<input type="text" name="sub_menu_nm" value="<%=sub_menu_nm%>" size="40" maxLength="50" title="Max Length 50 chars">
							</td>
						</tr>
						
						<tr class="content1">
							<th class="success">Select Module Name</th>
							<td>
								<select name="module_id"  onchange="refreshForm();">
									<option value="0"> -- Select -- </option>
									<%	for(int i=0;i<module_ids.size();i++)
										{	%>
											<option value="<%=module_ids.elementAt(i)%>"><%=module_nms.elementAt(i)%></option>
									<%	}	%>
								</select>
							</td>
						</tr>
						
						<tr class="content1">
							<th class="success">Select Group Name</th>
							<td>
								<select name="grp_id"  onchange="refreshForm();">
									<option value="0"> -- Select -- </option>
									<%	for(int i=0;i<V_mod_group_id.size();i++)
										{	%>
											<option value="<%=V_mod_group_id.elementAt(i)%>"><%=V_mod_group_nm.elementAt(i)%></option>
									<%	}	%>
								</select>
							</td>
						</tr>
					
						<tr>
							<th class="success">Set Priority</th>
							<td>
								<select name="priority_id">
									<option value="0"> -- Select -- </option>
									<%	for(int i=0;i<V_form_seq_no.size();i++)
										{	%>
											<option value="<%=V_form_seq_no.elementAt(i)%>"><%=V_form_seq_no.elementAt(i)%></option>
									<%	}	%>
								</select>
								<input type="hidden" name="priority_old_id" value="0">
								<script>
									document.forms[0].sub_menu_id.value = "<%=sub_menu_id%>";
									document.forms[0].grp_id.value = "<%=grp_Id%>";
									
								</script>
								<script>
									document.forms[0].priority_id.value='<%=grp_seq_no%>';
									document.forms[0].priority_old_id.value='<%=grp_seq_no%>';
								</script>
							</td>
						</tr>
						<script>
							document.forms[0].module_id.value = "<%=module_id%>";
						</script>
						
						<tr>
							<th class="success">Select Menu Type</th>
							<td>
								<select name="menu_type">
									<option value="F">Form</option>
									<option value="R">Report</option>
								</select>
							</td>
						</tr>
						
						<script>
							document.forms[0].menu_type.value = "<%=menu_type%>";
						</script>
						
						<tr>
							<th class="success">Sub Menu Path</th>
							<td>
								<input type="text" name="sub_menu_path" value="<%=sub_menu_path%>" size="40" maxLength="60" title="Max Length 60 chars">
							</td>
						</tr>
						
						<tr>
							<td colspan="2" class="text-center" style="background-color: white;" rowspan="2">
								<input type="hidden" name="option" value="Modify_Sub_Menu_Dtl">
<%-- 								<button type="button" class="btn btn-warning" onclick="goBck('<%=selModule_cd%>','<%=head_tab%>');">Back  </button> --%>
								<button type="reset" class="btn btn-info" name="reset" value="Refresh"> Reset  </button>
								<button type="button"  class="btn btn-success" value="Modify Sub Menu Details" onClick="doModify('<%=alw_add1 %>');" >Modify Sub Menu Details</button>
							    
							    <input type="hidden" name="old_submenu" value="Refresh" />
							    <input type="hidden"  name="new_submenu" value="Refresh" />
							    
							</td>
						</tr>
						
						
					</table>
					</div>
				</div>
				
				<%
					HttpSession menu_modify_sess=request.getSession();
					String audittrack =menu_modify_sess.getAttribute("audittrack")==null?"[VIEW]":(String)menu_modify_sess.getAttribute("audittrack");
					menu_modify_sess.removeAttribute("audittrack");
				%>
				
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