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
  

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="group_mst" scope="page"/>

<%
//   	 int h=0;
//sra1-14/aug/09

String username=(String)session.getAttribute("username");
// System.out.println("username****"+username);
group_mst.setUsername(username);
//
  	 String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
  	 String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
     String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
//      String head_tab = request.getParameter("head_tab")==null?"":request.getParameter("head_tab");
//   	 System.out.println("flag****"+flag);
    String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200421
 	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200421
 	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200421
     
     String alw_add1 = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
     String alw_view1 = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
     String alw_upd1 = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
     String alw_del1 = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
     String alw_print1 = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");
//      String selModule_cd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
//      System.out.println("alw_add******"+alw_add1);
     group_mst.setFormID(formId);
//      group_mst.setSelModule(selModule_cd);
//      group_mst.setHead_tab(head_tab);
     group_mst.setCallFlag("Add_Group");
	 
     group_mst.init();
	 
     int count= group_mst.getCount();
	 int grp_cnt= group_mst.getGroup_Count();
	 String [] group_id = null;
	 String [] group_nm = null;
	 group_id = group_mst.getGroup_id();
	 group_nm = group_mst.getGroup_name();
     String formName = group_mst.getFormName();
//      selModule_cd = group_mst.getSelModule();
//      head_tab =  group_mst.getHead_tab();
     
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
//  System.out.println("grp_cnt---------"+grp_cnt);
%> 
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
					
					<div class="col-xs-2" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >	</table>
					    </div>
				    </div>		
					
					<div class="col-xs-8" >
					<h2 class="sub-header" align="center" id="head">Group Master Entry/Update</h2>
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
				    			<tr class="success">
				    				<th >Group Code</th>
				    				<th >Group Name</th>
				    			</tr>
				    			
				    			<tbody id="grpTab" >
				    				<%for(int i = 0; i < grp_cnt; i++) {
				    					String tGds =""+grp_cnt;
				    				%>
				    				<tr style="font-size: 13px;" >
				    					<td ><input type="text" name="groupcd" size=5 maxlength=5 value="<%=group_id[i]==null?"":group_id[i]%>"  readonly="readonly" onClick="enable('<%=i%>','<%=tGds %>');" style="text-align:right;"></td>
				    					<td ><b class="s-red">*</b><input type="text" name="groupname" size=35 maxlength=50 value="<%=group_nm[i]==null?"":group_nm[i]%>" <%=update %> onClick="enable('<%=i%>','<%=tGds %>');" title="Write the Group Name, Max Size 50 chars">
				    					<%if(i==(grp_cnt-1)){ %>
											<button type="button" class="btn btn-sm btn-success btn-flat" style="height: 25px;" onclick="addNewGroup();">
											 <i class="fa fa-fw fa-plus-circle font-16"></i> </button>
				    					<%} %>
				    					</td>
				    				</tr>
				    				<%} %>
				    				
						    		
				    			</tbody>
				    			<tr>
						    			<td align="center" colspan="2"  style="background-color: white;" class="text-center">
<%-- 						    				<button type="button" class="btn btn-warning" onclick="goBck('<%=selModule_cd%>','<%=head_tab%>');">Back  </button> --%>
						    				<button type="reset" class="btn btn-info" onclick="rset('grpForm');"> Reset  </button>
						    				<button type="button"  class="btn btn-success" onclick="sub(this.value);" id="updBtn" value="upd" <%=access_update %> >Update </button>
						    				<button type="button"  class="btn btn-success" onclick="sub(this.value);" id="subBtn" value="sub" style="display: none;" <%//=access_add %>>Submit </button>
						    			</td>
						    		</tr>
						    
				    		</table>
				    	</div>		
					</div>
					
					<div class="col-xs-2" >
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >	</table>
					    </div>
				    </div>	
				    
					<input type="hidden" name="text_size" id="text_size" value="<%=grp_cnt%>">
					<input type="hidden" name="org_size" id="org_size" value="<%=grp_cnt%>">
					<input  type="hidden" name="option"  value="Add_Group">
					<input  type="hidden" name="formId"  value="<%=formId%>">
<%-- 					<input  type="hidden" name="head_tab"  value="<%=head_tab%>"> --%>
<%-- 					<input  type="hidden" name="module_cd"  value="<%=selModule_cd%>"> --%>
					
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
<%
//sra1-10/08/09
HttpSession tracksess=request.getSession(); 
Vector pass =new Vector();
String groupprint="";
String first="";
String second="";
String added="";
Vector v1=new Vector();
if(tracksess.getAttribute("pass4")==null)
{
v1=pass;
}
else
{
v1=(Vector)tracksess.getAttribute("pass4");
}
// System.out.println("************"+v1);
for (int h=0;h<grp_cnt;h++)  
{
pass.add(group_nm[h]);
}
tracksess.setAttribute("pass4",pass);
if((v1.size())==(pass.size()))
{
   for (int h=0;h<v1.size();h++)  

   {
     if((v1.elementAt(h)+"").equalsIgnoreCase(pass.elementAt(h)+""))
        {
	   //System.out.println("view");
	   groupprint="[VIEW]";
	  // System.out.println(groupprint);
	  
	   }
     else
       {
	   second=(String)(pass.elementAt(h));
	   //System.out.println("second"+second);
	   
	   first=(String)(v1.elementAt(h));
	  // System.out.println("first"+first);
	  // System.out.println("updated");
	   groupprint="[updated-><"+first+"> to <"+second+">]";
	  // System.out.println(groupprint);
	   break;
       }
	}
}
else
{
	added=(String)pass.lastElement();
	//System.out.println("added");
	groupprint="[added->"+added+"]";
	//System.out.println(groupprint);
}
// System.out.println("**********"+groupprint);
// new com.ats.hlpl.hazira.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: ADD GROUP:"+ groupprint ); 
		
		
		
 %> 
</form>
</body>
</html>