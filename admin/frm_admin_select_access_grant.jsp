<%@ page errorPage="../home/ExceptionHandler.jsp" %>
<%@ include file="../sess/Expire.jsp" %>
<%@ page import ="java.util.*" %>

<!DOCTYPE html>
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

</style>
<script language="JavaScript">

function Set()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var module_id = document.forms[0].module.options[document.forms[0].module.selectedIndex].value;
	var group_id = document.forms[0].group.options[document.forms[0].group.selectedIndex].value;
	var group_nm = document.forms[0].group.options[document.forms[0].group.selectedIndex].innerText;
// 	alert(formId+"**"+module_id+"**"+group_id+"***"+group_nm)
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	/* var url ="../admin/frm_admin_access_rights_selection.jsp?module_id="+module_id+"&group_id="+group_id+"&modCd="+modCd+"&subModUrl="+subModUrl+
			"&group_nm="+group_nm+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+
			"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission; */
	var url ="../admin/frm_mst_administrator.jsp?module_id="+module_id+"&group_id="+group_id+"&modCd="+modCd+"&subModUrl="+subModUrl+
			"&group_nm="+group_nm+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+
			"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&show_rights=Y";
			
// 	alert(url)
	location.replace(url);	
}

function showpro(){
if(document.forms[0].process.style.visibility='hidden')
document.forms[0].process.style.visibility='visible';
}
function hidepro(){
if(document.forms[0].process.style.visibility='visible')
document.forms[0].process.style.visibility='hidden';
}


function bck(module_cd,head_tab){
// 	alert('in'+module_cd)
	if(module_cd!=''){
		var url="../admin/frm_mst_administrator.jsp?modCd="+module_cd+"&head_tab="+head_tab;
		location.replace(url);
	}
}

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="fetch" scope="page" />  
<%
  	String show_rights = request.getParameter("show_rights")==null?"":request.getParameter("show_rights");//HA20200422
  if(show_rights.equals("")){	
      String status = request.getParameter("status")==null?"":request.getParameter("status");
  	String group_id = request.getParameter("group_id")==null?"0":request.getParameter("group_id");
  	String module_id = request.getParameter("module_id")==null?"0":request.getParameter("module_id");
  	String write_permission = request.getParameter("alw_add")==null?"N":request.getParameter("alw_add");
  	String delete_permission = request.getParameter("alw_del")==null?"N":request.getParameter("alw_del");
  	String print_permission = request.getParameter("alw_print")==null?"N":request.getParameter("alw_print");
  	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
  	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
      
  	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200422
  	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200422
  	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200422
  // 	System.out.println("modCd*****"+modCd);
  	
  	fetch.setCallFlag("Module_List_Dtl");
      
      fetch.init();
        	
      Vector module_ids = fetch.getModuleIds();
  	Vector module_nms = fetch.getModuleNms();
  	
  	Vector group_ids = fetch.getGroupIds();
  	Vector group_nms = fetch.getGroupNms();
  	
  	String formName = fetch.getFormName();
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
				<form class="form-horizontal" id="formId">
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
					
					<div class="form-group">
						<div class="col-lg-2 col-md-2 col-sm-3 col-xs-4">
						<label class="control-label"> Grant to<span class="s-red">*</span> :</label>
						</div>
						<div class="col-lg-10 col-md-10 col-sm-9 col-xs-8">
						<select class="form-control" name="group">
						<%
							for(int i=0;i<group_ids.size();i++){
						%>
							<option value='<%=group_ids.elementAt(i)%>'><%=group_nms.elementAt(i)%></option>
						<%
							}
						%>
						</select>
						<%
							if(!group_id.equals("0") && !group_id.trim().equals(""))
											{
						%>
								<script>
									document.forms[0].group.value = "<%=group_id%>";
								</script>
						<%
							}
						%>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-lg-2 col-md-2 col-sm-3 col-xs-4">
						<label class="control-label"> Select Module<span class="s-red">*</span> :</label>
						</div>
						<div class="col-lg-10 col-md-10 col-sm-9 col-xs-8">
						<select class="form-control"name=module>
							<option value='All'>All</option>
							<%
								for(int i=0;i<module_ids.size();i++){
							%>
							<option value='<%=module_ids.elementAt(i)%>'><%=module_nms.elementAt(i)%></option>
							<%
								}
							%>
						</select>
						<%
							if(!module_id.equals("0") && !module_id.trim().equals(""))
										{
						%>
							<script>
								document.forms[0].module.value = "<%=module_id%>";
							</script>
					<%
						}
					%>
						
						</div>
					</div>
					
					<input type="hidden" name="write_permission" value="<%=write_permission%>">
					<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
					<input type="hidden" name="print_permission" value="<%=print_permission%>">
					<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
					<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
										
				</form>
			</div>
		</div>
	</div>
	<div class="box-footer">
	<div class="row">
		<div class="col-md-12 text-center">
<%-- 			<button type="button" class="btn btn-warning" onclick="bck('<%=selModule_cd%>','<%=head_tab%>');">Back  </button> --%>
			<button type="submit" class="btn btn-info"><i class="fa fa-times"></i> Reset  </button>
			<button type="button" class="btn btn-success" onclick="Set();"><i class="fa fa-check"></i> Submit </button>
		</div>
	</div>
</div>

</div>
</div>
</div>
</div>
</div>
</section>
</body>
<%
	}else{
%>
<%@ include file="../admin/frm_admin_access_rights_selection.jsp"%>
<%} %>

</html>