<%@ page import="java.util.Vector" %>
<jsp:useBean class="com.seipl.hazira.dlng.util.Menu_Bean" id="menu" scope="page" />
<%
String selModule1 = request.getParameter("modCd")==null?"":request.getParameter("modCd");
String selForm_id = request.getParameter("formId")==null?"":request.getParameter("formId");
String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
String modUrl1 = request.getParameter("modUrl")==null?"":request.getParameter("modUrl");
String memp_name = "" + session.getAttribute("userid");
// System.out.println("modUrl****"+modUrl1);
Vector mform_id=new Vector();
Vector mform_name=new Vector();
Vector mform_type=new Vector();
Vector mform_seq_no=new Vector();
Vector mapplication_path=new Vector();
Vector alw_add=new Vector();
Vector alw_del=new Vector();
Vector alw_upd=new Vector();
Vector alw_print=new Vector();
Vector alw_view  = new Vector();
Vector alw_audit  = new Vector();
Vector alw_approve  = new Vector();
Vector alw_check  = new Vector();

if(!selModule1.equals(""))
{	
	session.setAttribute("module_cd",selModule1);
	session.setAttribute("selFormId",selForm_id);
	session.setAttribute("subModUrl",subModUrl);
	session.setAttribute("modUrl",modUrl1);
	
	menu.setemp_nm(memp_name);
	menu.setOption("SUBMOD");
	menu.setModule_cd(selModule1);
	menu.setSelForm_id(selForm_id);
// 	menu.setFile_path(file_path);

	menu.init();
	
	selModule1 = menu.getModule_cd();
	mform_id = menu.getFormId();
	mform_name = menu.getFormName();
	mapplication_path = menu.getApplicationPath();
	alw_add=menu.getALW_ADD();
	alw_del=menu.getALW_DEL();
	alw_upd=menu.getALW_UPD();
	alw_print=menu.getALW_PRINT();
	alw_audit=menu.getALW_AUTHORIZE();
	alw_approve = menu.getALW_GRANT();
	
	alw_view=menu.getALW_VIEW();
	alw_check = menu.getALW_CHECK();
	
	selForm_id = menu.getSelForm_id();
// 	System.out.println("alw_upd****"+alw_upd);
}%>
