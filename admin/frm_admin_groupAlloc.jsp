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

function SaveDelete(activity_flg,access)
{
 	access= document.forms[0].write_permission.value;
	
 	if(access == 'Y'){
		var a = false;
		var activity = activity_flg;
			
		var flg = document.forms[0].group_cd.value;
	 	var flg1 = document.forms[0].emp_id.value;
	 	var flg2 = document.forms[0].from_dt.value;
	 	var flg3 = document.forms[0].to_dt.value;
	// 		alert(flg+"**"+flg1+"***"+flg2+"***"+flg3)
		if((flg=='') || (flg==' ') || (flg=='0'))
		{
			alert('Group Not Selected, Please Select The Group !!!');
			document.forms[0].group_cd.focus();
			return false;
		}
	
		if((flg1=='') || (flg1=='0'))
		{
			alert('Employee Not Selected, Please Select The Employee !!!');
			document.forms[0].emp_id.focus();
			return false;
		}
		
		if((flg2=='') || (flg2==' '))
		{
			alert('From Date Not Entered, Please Enter From Date !!!');
			document.forms[0].from_dt.focus();
			return false;
		}
		
		if((flg3=='') || (flg3==' '))
		{
			alert('To Date Not Entered, Please Enter To Date !!!');
			document.forms[0].to_dt.focus();
			return false;
		}
		else
		{
			 pp=compareDate(document.forms[0].from_dt.value,document.forms[0].to_dt.value);
	// 		 alert("pp--"+pp);
			 if(pp==1)
			 {
			      alert("Start date cann't be greater than To date");
			      document.forms[0].to_dt.value='';
			      return false;
			} 
		}
		
		if(activity=="Save")
		{
			document.forms[0].option.value="Add_Emp_Group_Allocation";
			a = confirm('Do you want to ALLOCATE The Group for the selected User ?');
		}
		else if(activity=="Delete")
		{
			document.forms[0].option.value="Delete_Emp_Group_Allocation";
			a = confirm('Do you want to DELETE Allocated Group for the selected User ?');
		}
		
		if(a)
		{
			document.forms[0].emp_name.value = document.forms[0].emp_id[document.forms[0].emp_id.selectedIndex].innerText;
		    document.forms[0].group_name.value = document.forms[0].group_cd[document.forms[0].group_cd.selectedIndex].innerText;
		    
			document.forms[0].emp_cd.value = document.forms[0].emp_id.value;
	    	document.forms[0].submit();    	
	    }
	}else{
		alert('Sorry ! You Have No Access Rights..!')
	}	
}


function val_entry()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	if(document.forms[0].emp_id.value == "0")
	{
 		alert('Plsease select the employee first...');
 		document.forms[0].emp_id.focus();
 	}
 	else
 	{
 		var emp_cd = document.forms[0].emp_id.value;
//  		alert(emp_cd)
 		var write_permission = document.forms[0].write_permission.value;
		var delete_permission = document.forms[0].delete_permission.value;
		var print_permission = document.forms[0].print_permission.value;
		var approve_permission = document.forms[0].approve_permission.value;
		var audit_permission = document.forms[0].audit_permission.value;
		var view_permission = document.forms[0].view_permission.value;
		var update_permission = document.forms[0].update_permission.value;
		
 		location.replace("../admin/frm_mst_administrator.jsp?emp_cd="+emp_cd+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission);
 	}
}


function resetVal()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var emp_cd = "0";
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var view_permission = document.forms[0].view_permission.value;
	var update_permission = document.forms[0].update_permission.value;
		
		location.replace("../admin/frm_mst_administrator.jsp?emp_cd="+emp_cd+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission);
}


// function validateTodate()
// {
// 	//alert("hee");
	
// 	from_dt=document.forms[0].from_dt.value;
// 	 to_date=document.forms[0].to_dt.value;
	 
// 	// alert("from_dt--"+from_dt);
// 	 //alert("to_date--"+to_date);
// 	 pp=compareDate(document.forms[0].from_dt.value,document.forms[0].to_dt.value);
// 	 //alert("pp--"+pp);
// 	 if(pp==1)
// 		  {
// 		      alert("Start date cann't be greater than To date");
// 		      document.forms[0].to_dt.value='';
// 		  } 
// }

function setValues(emp_cd,group_cd,from_dt,to_dt)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var view_permission = document.forms[0].view_permission.value;
	var update_permission = document.forms[0].update_permission.value;
	
	document.forms[0].emp_id.value = emp_cd;
	document.forms[0].group_cd.value = group_cd;
	document.forms[0].from_dt.value = from_dt;
	document.forms[0].to_dt.value = to_dt;
	
// 	alert(emp_cd)
	
// 		location.replace("../admin/frm_mst_administrator.jsp?emp_cd="+emp_cd+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission);
}

</script>

</head>

<jsp:useBean class="com.hazira.hlpl.tlu.admin.DataBean_Admin" id="group_mst" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
	util.init();
	String sysdate = util.getGen_dt();
	String nex_year_dt = sysdate.substring(0,6)+(Integer.parseInt(sysdate.substring(6))+1);

	String username=(String)session.getAttribute("username");
	String user_cd=(String)session.getAttribute("user_cd");
	group_mst.setUsername(username);
	
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
 	String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
	
	String emp_cd = request.getParameter("emp_cd")==null?"0":request.getParameter("emp_cd");
	String group_cd = request.getParameter("group_cd")==null?"0":request.getParameter("group_cd");
	String from_dt = request.getParameter("from_dt")==null?sysdate:request.getParameter("from_dt");
	String to_dt = request.getParameter("to_dt")==null?nex_year_dt:request.getParameter("to_dt");
	
	String alw_add1 = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
    String alw_view1 = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
    String alw_upd1 = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
    String alw_del1 = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
    String alw_print1 = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	 
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200421
 	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200421
 	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200421
 	
	group_mst.setCallFlag("Allocate_Group");
	group_mst.setEmpCD(emp_cd);
	
	/* *****************set permissions***************** */
	group_mst.setWrite_permission(alw_add1);
	group_mst.setDelete_permission(alw_del1);
	group_mst.setView_permission(alw_view1);
	group_mst.setUpdate_permission(alw_upd1);
	group_mst.setPrint_permission(alw_print1);
	group_mst.setApprove_permission(approve_permission);
	group_mst.setAudit_permission(audit_permission);
	
	
	// SM 06-06-2012  group_mst.setEmpCD(user_cd);
//	System.out.println("user_cd :"+user_cd);
	group_mst.init();
	
	String[] group_cd1= group_mst.getGroup_cd1();
//	System.out.println("group_cd1:: "+group_cd1.length);
	String[] group_nm1= group_mst.getGroup_nm1();
//	System.out.println("group_nm1:: "+group_nm1.length);
	String[] emp_id = group_mst.getEmp_id();
// 	System.out.println("emp_id:: "+emp_id.length);
	String[] emp_nm = group_mst.getEmp_nm();
// 	System.out.println("emp_nm:: "+emp_nm.length);
	String[] group_cd3 = group_mst.getGroup_cd3();
// 	System.out.println("group_cd3: "+group_cd3.length);
	String[] group_nm3 = group_mst.getGroup_nm3();
//	System.out.println("group_nm3: "+group_nm3.length);
	
	String[] from_date = group_mst.getFrom_date();
//	System.out.println("from_date: "+from_date.length);
	String[] to_date = group_mst.getTo_date();
//	System.out.println("to_date: "+to_date.length);
	String formName = group_mst.getFormName();
	
	String emp_name = "";
// 	System.out.println("emp_id.length = "+emp_id.length+"****"+emp_cd);
	 for(int j=0; j<emp_id.length; j++)
	{
		System.out.println("emp_id["+j+"] = "+emp_id[j]+",  emp_cd = "+emp_cd+",  emp_nm["+j+"] = "+emp_nm[j]);
		 if(emp_id[j].trim().equals(emp_cd.trim()))
		{
			emp_name = emp_nm[j];
			break;
		} 
	} 
	
	 
	 alw_add1 = group_mst.getWrite_permission();
     alw_view1 = group_mst.getView_permission();
     alw_upd1 = group_mst.getUpdate_permission();
     alw_del1 = group_mst.getDelete_permission();
     alw_print1 = group_mst.getPrint_permission();
	 approve_permission = group_mst.getApprove_permission(); 
	 audit_permission = group_mst.getAudit_permission();
	
	
	
	int h=0;
	String access_add="",access_del="",access_update="",access_print="",acess_update_readonly="";
	String access_update_href="",acess_print_readonly="",acess_print_disabled="",access_print_href="";
	 if(alw_add1.equalsIgnoreCase("N"))
     {
         access_add="disabled";
     }
     if(alw_del1.equalsIgnoreCase("N"))
     {
    	 access_del="disabled";
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
     
    Vector all_group_cd = group_mst.getGroup_cd();
 	Vector all_group_nm = group_mst.getGroup_nm();
 	Vector all_emp_cd = group_mst.getEmp_cd();
 	Vector emp_nme = group_mst.getEmp_nme(); 
 	Vector emp_cnt = group_mst.getEmp_cnt();
 	Vector emp_flag = group_mst.getEmp_flag();
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
					<%
						String form_id = "0";
						String form_nm = "";
					%>
					<input type="hidden" name="form_nm" value="<%=form_nm%>">
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
					<div class="col-xs-6" >
						<h2 class="sub-header" align="center" id="head"><%=formName%></h2>
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
				    			<tr>
				    				<th class="success">User Name</th>
					    				<td>
											<select name=emp_id onChange="val_entry();">
												<option value="0">--Select--</option>
												<%	for(int z=0;z<emp_id.length;z++)
													{
														if(emp_id[z] != null)
														{	%>
															<option value='<%=emp_id[z]%>'><%=emp_nm[z]%></option>
												<%		}
													}		%>
											</select>
											<script>
									    		document.forms[0].emp_id.value=<%=emp_cd%>;
									    	</script>
								    	</td>
							    </tr>
							    <tr>
				    				<th class="success">Group Name</th>
				    				<td>
										<select name=group_cd>
											<option value="0">--Select--</option>
											<%	for(int j=0;j<group_cd1.length;j++)
												{	%>
													<option value='<%=group_cd1[j]%>'><%=group_nm1[j]%></option>
											<%	}	%>
										</select>
										<script>
								    		document.forms[0].group_cd.value=<%=group_cd%>;
								    	</script>
							    	</td>
				    			</tr>
				    			<tr>
				    				<th class="success">From Date</th>
				    				<td><input type="text" name="from_dt" id="from_dt" value="<%=from_dt%>" onblur="validateDate(this);" size="10" maxlength="10" class="datepick" >
				    				<input type="hidden" name="from_date" value="<%=from_dt%>">
				    				</td>
				    			</tr>
				    			<tr>
				    				<th class="success">To Date</th>
				    				<td><input type="text" name="to_dt" id="to_dt" value="<%=to_dt%>" onblur="validateDate(this);"	size="10" maxlength="10" class="datepick"></td>
				    			</tr>
				    			
				    			<tr>
						    		<td align="center" colspan="2"  style="background-color: white;" class="text-center">
<%-- 					    				<button type="button" class="btn btn-warning" onclick="goBck('<%=selModule_cd%>','<%=head_tab%>');">Back  </button> --%>
					    				<button type="reset" class="btn btn-info" onclick="rset('grpForm');"> Reset  </button>
					    				<button type="button"  class="btn btn-success" onclick="SaveDelete('Save','<%=alw_add1 %>');" id="subBtn" value="sub">Submit </button>
					    				<button type="button"  class="btn btn-danger" onclick="SaveDelete('Delete','<%=alw_del1 %>');" >Delete </button>
						    		</td>
						    	</tr>
					    		
				    		</table>
				    	</div>	
					</div>
					
					<div class="col-xs-6" >
					<h2 class="sub-header" align="center" id="head">Allocated Groups<%if(!emp_name.trim().equals("")){%> to <%=emp_name%><%}%></h2>
				  		<div class="table-responsive"  >
				    		<table class="table table-striped"  >
								<tr class="success">
									<td ><strong>Select</strong></td>
							    	<td><strong>Group</strong></td>				    		
							    	<td><strong>From Date</strong></td>
							    	<td><strong>To Date</strong></td>
								</tr>
								<%	
								if(group_cd3!=null)
									{
										if(group_cd3.length>0 && !emp_name.trim().equals(""))
										{
// 											System.out.println("group_cd3.length***********"+group_cd3.length);
											for(int j=0;j<group_cd3.length;j++)
											{	
// 												System.out.println("group_cd3.length***********"+group_cd3[j]+"***"+j);
											%>
												<tr style="background-color: white;">
													<td align="center">
											    		<input type="radio" name="rd" onClick="setValues('<%=emp_cd%>','<%=group_cd3[j]%>','<%=from_date[j]%>','<%=to_date[j]%>');">
											    	</td>
											    	<td align="left">
											    		<%=group_nm3[j]%>
											    	</td>
											    	<td align="center">
											    		<%=from_date[j]%>
											    	</td>
											    	<td align="center">
											    		<%=to_date[j]%>
											    	</td>
												</tr>
								<%			}
										}
									}else{%>
										<tr><td colspan="4" style="color: red;font-size: 15px;font-weight: bold;" align="center" class="text-center">No group(s) details available..!</td></tr>
									<%} %>
								
				    		</table>
					    </div>
				    </div>	
				   <br>
<!-- 		<table >	     -->
<!-- 		<div align="center"  > -->
			<div class="col-xs-7"  align="center" style="width: 90%;padding-left: 200px">
				<h2 class="sub-header" align="center" >Group Allocation Details</h2>
			  		<div class="table-responsive"  style="height: 200px;overflow: auto;" align="center">
			    		<table class="table table-striped"  >
							<tr class="success">
								<td ><strong>Group Name</strong></td>
						    	<td><strong>Allocated Employee(s)</strong></td>
							</tr>
							<%
							int m=0;
							for(int i = 0 ; i < all_group_cd.size() ; i++){ %>
							<tr style="background-color: white;">
								<td align="center"><%=all_group_nm.elementAt(i) %>	</td>
								<td align="center">
								<%for(int j = 0 ; j < Integer.parseInt(emp_cnt.elementAt(i)+""); j++){
									String fontColor = "green",title="Active User";
									if(emp_flag.elementAt(m).toString().equalsIgnoreCase("Y")) {
										fontColor = "red";
										title="Deactive User";
									} %>
									<font color="<%=fontColor%>" title="<%=title%>"> <%=emp_nme.elementAt(m) %></font> <br>
								<%m++;} %>
								</td>
							</tr>	
							<%} %>
							</tbody>
					</table>
				</div>
			</div>
<!-- 			</div> -->
<!-- 		</table>					 -->
				
<%
//sra1-12/08/09
HttpSession groupalloc_sess=request.getSession();
String audittrack = groupalloc_sess.getAttribute("audittrack")==null?"[VIEW]":(String)groupalloc_sess.getAttribute("audittrack");
groupalloc_sess.removeAttribute("audittrack");
%>

<input type="hidden" name="option" value="">
<input type=hidden name=emp_cd>
<input type="hidden" name=emp_name>
<input type=hidden name=group_name>


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
</body>
<link rel="stylesheet" href="../ui/jquery-ui.css">
<script src="../js/jquery-1.12.4.js"></script>
<script src="../js/jquery-ui.js"></script>

<script type="text/javascript">

$('.datepick').each(function(){
	
   $(this).datepicker({changeMonth: true,changeYear: true});
   
}); 
</script>
</html>