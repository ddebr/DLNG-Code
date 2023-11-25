<%@ include file="../sess/Expire.jsp"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
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
<script language="JavaScript">

  gnm = new Array();
  gcd = new Array();
  var oldVal=new Array();
  var newVal=new Array();
  var valUpd=new Array();
  var mixVal=new Array();
  var tcompadd1=new Array();
  
  flag=true;
  temp="";
  tp="";
  
  k=0;
  var l=0;
  var g=0;
   function count(me){
     var val =me.value;
     var cnt=document.forms[0].cnt.value;
 
     if(parseInt(val)>=parseInt(cnt)){
         alert(" Module Priority has to be within Module code");
		 return false;
     }
	}
	
</script>
</head>

<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="group_mst" scope="page"/>

<%
//sra1-14/aug/09

String username=(String)session.getAttribute("username");
//System.out.println("nmaeeeeeeeeeeeeeeeeee"+username);
group_mst.setUsername(username);
//
  	
	 String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	 String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
     String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
     
//      String head_tab = request.getParameter("head_tab")==null?"":request.getParameter("head_tab");
  	 String alw_add1 = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
     String alw_view1 = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
     String alw_upd1 = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
     String alw_del1 = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
     String alw_print1 = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");
     String selModule_cd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
 	 String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
 	 String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
 	
 	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200421
 	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200421
 	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200421
 	
//      System.out.println("alw_add******"+alw_add1);
     
//      group_mst.setFormID(formId);
//      group_mst.setSelModule(selModule_cd);
//  	 group_mst.setHead_tab(head_tab);
     /* *****************set permissions***************** */
 	group_mst.setWrite_permission(alw_add1);
 	group_mst.setDelete_permission(alw_del1);
 	group_mst.setView_permission(alw_view1);
 	group_mst.setUpdate_permission(alw_upd1);
 	group_mst.setPrint_permission(alw_print1);
 	group_mst.setApprove_permission(approve_permission);
 	group_mst.setAudit_permission(audit_permission);
 	
     group_mst.setCallFlag("Add_Module");
     
	 group_mst.init();

     String formName = group_mst.getFormName();
     Vector Vmodule_cd=group_mst.getVmoduleCd();
     Vector Vmodule_name=group_mst.getVmoduleName();
     Vector Vpriority=group_mst.getVpriority();
     Vector VmodPath=group_mst.getVmodPath();
     
     int count= Vmodule_cd.size()+1;
     int cnt=Vmodule_cd.size();
     
     alw_add1 = group_mst.getWrite_permission();
     alw_view1 = group_mst.getView_permission();
     alw_upd1 = group_mst.getUpdate_permission();
     alw_del1 = group_mst.getDelete_permission();
     alw_print1 = group_mst.getPrint_permission();
	 approve_permission = group_mst.getApprove_permission(); 
	 audit_permission = group_mst.getAudit_permission();
	 
// 	 head_tab =  group_mst.getHead_tab();
	 selModule_cd = group_mst.getSelModule();
%> 
<%
	String form_nm = "Add/Update Module";
%>

<body>

<div class="tab-content">
<div class="tab-pane active" id="add_new_action">
<div class="box mb-0">
<div class="box-body">

 <div class="box-header with-border">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;"> 
 				<form class="form-horizontal"  id="grpForm" method="post" name="share"  onSubmit="return checkAll();" action="../servlet/Frm_admin">
				<input type="hidden" name="modCd" value="<%=modCd%>">
				<input type="hidden" name="formId" value="<%=formId%>">
				<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
				
				<div class="col-xs-1" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >	</table>
					    </div>
				    </div>	
				<div class="col-xs-10" >
					<h2 class="sub-header" align="center" id="head"><%=form_nm%></h2>
					<div class="table-responsive"  >
					<table class="table table-striped" >
					<%if(!msg.equals("")){ %>
			    	<tr>
			    		<td colspan="5" class="text-center" style="color: blue;font-weight: bold;font-size: 13px;"><%=msg %></td>
			    	</tr>
		    		<%} %>	
		    		<%if(!error_msg.equals("")){ %>
			    	<tr>
			    		<td colspan="5" class="text-center" style="color: red;font-weight: bold;font-size: 13px;"><%=error_msg %></td>
			    	</tr>
					<%} %>
						<tr class="success">
							<th>Module Code</th>
							<th>Module Description</th>
							<th colspan="">Module Priority</th>
							<th colspan="2" class="">Module Path</th>
						</tr>
				<%for (int h=0;h<Vmodule_cd.size();h++) {

				   String tMdcd =Vmodule_cd.elementAt(h)+"";
				   String tMdnm=Vmodule_name.elementAt(h)+"";
				   String prty=Vpriority.elementAt(h)+"";
				   String path = VmodPath.elementAt(h)+"";
				    %>
				    
				       <tr style="font-size: 13px;">
				         <td align="center"><input type="text" name="modulecd" size=5 maxlength=5 value="<%=tMdcd%>"  readonly="readonly"  style="text-align:right;"></td>
				         <td align="center"><b class="s-red">*</b><input type="text" name="modulename" size=35 maxlength=50 value="<%=tMdnm%>"  title="Write the Group Name, Max Size 50 chars"></td>
				         <td align="center"><input type="text" name="priority" size=3 maxlength=3 value="<%=prty%>"    onBlur="count(this);" style="text-align:right;"></td>
				      	<td>
				      		<input type="text" name="modUrl" value="<%=path %>" placeholder="i.e: ../admin/frm_admin_administrator.jsp" size="50">
				      	</td>
				      	<td>
				      	<%if(h==(Vmodule_cd.size()-1)){ %>
				      	
				      		<button type="button" class="btn btn-sm btn-success btn-flat" title="Click to ADD new Module.." style="height: 25px;" onclick="addNewModule();">
							<i class="fa fa-fw fa-plus-circle font-16"></i> </button>
				      	
				      <%} %>
				      </td>
				       </tr>
				    <%}%>
				    <tbody id="modTab" >
					</tbody>
					<tr>
		    			<td align="center" colspan="5"  style="background-color: white;" class="text-center">
		    				<button type="button" class="btn btn-warning" onclick="goBck('<%=selModule_cd%>','<%=modCd%>');">Back  </button>
		    				<button type="reset" class="btn btn-info" onclick="rset('grpForm');"> Reset  </button>
		    				<button type="button"  class="btn btn-success" onclick="sub(this.value);" id="updBtn" value="upd"  >Update </button>
		    				<button type="button"  class="btn btn-success" onclick="sub(this.value);" id="subBtn" value="sub" style="display: none;" >Submit </button>
		    			</td>
		    		</tr>
		    								
				    </table>
				   	</div>
				</div>	
				<div class="col-xs-1" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >	</table>
					    </div>
				    </div>	
				
				<input type="hidden" name="text_size" id="text_size" value="<%=Vmodule_cd.size()%>">
				<input type="hidden" name="org_size" id="org_size" value="<%=Vmodule_cd.size()%>">
				
				<input type="hidden" name="form_id" value="<%=formId%>">
				<input type="hidden" name="form_nm" value="<%=form_nm%>">
				
				<input type=hidden name="option" value="Add_Module">
				<input type="hidden" name="flag" value="<%=flag%>">
				<input type="hidden" name="formId" value="<%=formId%>">
				<input type="hidden" name="cnt" value="<%=cnt%>">
				<input type=hidden name="module_cd" value="<%=selModule_cd%>">
				<input type=hidden name="head_tab" value="<%=modCd%>">
				
				<input type="hidden" name="write_permission" value="<%=alw_add1%>">
				<input type="hidden" name="delete_permission" value="<%=alw_del1%>">
				<input type="hidden" name="print_permission" value="<%=alw_print1%>">
				<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
				<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
				<input type="hidden" name="view_permission" value="<%=alw_view1%>">
				<input type="hidden" name="update_permission" value="<%=alw_upd1%>">
								
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>
				

<%
//sra1-11/08/09
HttpSession addmoudle_sess=request.getSession();

int auditflag=0;

String print="";
String first="";

String second="";

String added="";

Vector Add_mobule_1=new Vector();

Vector Add_mobule_2=new Vector();

if(addmoudle_sess.getAttribute("pass1")==null)
{
	
	Add_mobule_2=Add_mobule_1;
}
else
{
	
	
	Add_mobule_2=(Vector)addmoudle_sess.getAttribute("pass1");
}

for (int h=0;h<Vmodule_cd.size();h++) 
{
	Add_mobule_1.add(Vmodule_name.elementAt(h));
}
//System.out.println("test11");
addmoudle_sess.setAttribute("pass1",Add_mobule_1);
//System.out.println(Add_mobule_1+" "+Add_mobule_2);
if((Add_mobule_1.size())==(Add_mobule_2.size()))
{
//	System.out.println("test9");
	for (int h=0;h<Add_mobule_2.size();h++) 
		 {
//		System.out.println("test10"); 
//		System.out.println(Add_mobule_2.size());
//		System.out.println(Add_mobule_1.size());
		if((Add_mobule_1.elementAt(h)+"").equalsIgnoreCase(Add_mobule_2.elementAt(h)+""))
		  {
			   
//			System.out.print(Add_mobule_1.elementAt(i));
//			System.out.print("->");
//			System.out.println(Add_mobule_2.elementAt(i));
//			System.out.println("view");
			   print="[VIEW]";
			   
			}
		   else
		   {
			  
			   second=(String)(Add_mobule_1.elementAt(h));
			   first=(String)(Add_mobule_2.elementAt(h));
			   System.out.println("updated");
			   print="[updated->"+first+" "+"to"+" "+second+"]";
			 // print="[updated-><"+ first +"> to <"+ second +">]";
//			   System.out.println(print);
			   break;
			 
			 }
		
		 }
}
	else
	{
		added=(String)Add_mobule_1.lastElement();
		System.out.println("added");
		print="[added->"+added+"]";
	}
//System.out.println("test12");
//System.out.println(Add_mobule_1);
//System.out.println("test13");
//System.out.println(print);
//new com.hlpl.hazira.tims.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: Adding Module:"+print); 
%>

</body>
</html>