<%@ page buffer="128kb" %>
<%@ page import="java.util.*" %>
<html>
<head>
<title>DLNG | FLSA Billing Clause Details</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" >
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>

<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<script language="JavaScript" src="../js/nhv.js"></script>  
<style type="text/css">
td {
    font-size:small;
}
.select {
    width: 200px;
    height: 27px;
} 
</style>
<script type="text/javascript">

document.write('<div id="loading"><br><br>Loading, Please Wait...</div>');

window.onload=function()
{
	document.getElementById("loading").style.display="none";
}


function checkWithContractStartDate(obj)
{
	var from_dt = obj.value;
	
	var start_dt = document.forms[0].start_dt.value;
	if(from_dt!=null && from_dt!="" && from_dt!=" " && start_dt!=null && start_dt!="" && start_dt!=" ")
	{
		var value = compareDate(from_dt,start_dt);
  	
  		if(value == 2)
  		{
    		alert("Deactivation From Date ("+from_dt+") Must Be Greater Than Or Equal To FLSA Start Date ("+start_dt+") !!!");
    		obj.value = "";
    		obj.select();
    		return false;
  		}
  	}
}


function checkWithContractEndDate(obj)
{
	var to_dt = obj.value;
	var end_dt = document.forms[0].end_dt.value;
	
	if(end_dt!=null && end_dt!="" && end_dt!=" " && to_dt!=null && to_dt!="" && to_dt!=" ")
	{
		var value = compareDate(end_dt,to_dt);
  	
  		if(value == 2)
  		{
    		alert("Deactivation To Date ("+to_dt+") Must Be Less Than Or Equal To FLSA End Date ("+end_dt+") !!!");
    		obj.value = "";
    		obj.select();
    		return false;
  		}
  	}	
}


function checkDurationValidity(ind,sz,obj)
{
	var from_dt = "";
	var to_dt = "";
	
	var index = parseInt(""+ind);
	var size = parseInt(""+sz);
	
	if(size==1)
	{
		from_dt = document.forms[0].from_dt.value;
		to_dt = document.forms[0].to_dt.value;
	}
	else if(size>1)
	{
		from_dt = document.forms[0].from_dt[index].value;
		to_dt = document.forms[0].to_dt[index].value;
	}
	
	if(from_dt!=null && from_dt!="" && from_dt!=" " && to_dt!=null && to_dt!="" && to_dt!=" ")
	{
		var value = compareDate(to_dt,from_dt);
	  	
	  	if(value == 2)
	  	{
	    	alert("Deactivation To Date ("+to_dt+") Must Be Greater Than Or Equal To From Date ("+from_dt+") !!!");
	    	obj.value = "";
	    	obj.select();
	    	return false;
	  	}
	}
}


function refreshPage()
{
	var buyer_cd = document.forms[0].buyer_cd.value;
	var buyer_name = document.forms[0].buyer_name.value;
	var fgsa_cd = document.forms[0].fgsa_cd.value;
	var fgsa_revno = document.forms[0].fgsa_revno.value;
	var start_dt = document.forms[0].start_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var index = document.forms[0].index.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var form_id = document.forms[0].form_id.value;
	var form_nm = document.forms[0].form_nm.value;
	
	if(index!=null)
	{	
		if(index!='' && index!=' ' && index!='  ')
		{
			if(parseInt(index)>=1)
			{
				location.replace("frm_FLSA_active_deactive.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&index="+index+"&buyer_name="+buyer_name+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm);
			}
			else
			{
				document.forms[0].index.value = "1";
			}
		}
		else
		{
			document.forms[0].index.value = "1";
		}
	}
	else
	{
		document.forms[0].index.value = "1";
	}
}


function resetPage()
{
	var buyer_cd = document.forms[0].buyer_cd.value;
	var buyer_name = document.forms[0].buyer_name.value;
	var fgsa_cd = document.forms[0].fgsa_cd.value;
	var fgsa_revno = document.forms[0].fgsa_revno.value;
	var start_dt = document.forms[0].start_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var index = "1";
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("frm_FLSA_active_deactive.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&index="+index+"&buyer_name="+buyer_name+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
}


function doSubmit1(sz)
{
	var msg = "Please Check the Following Field(s):\n\n";
	var flag = true;
	
	var i = 0;
	var index = 0;
	var size = parseInt(sz);
	
	if(size==1)
	{
		var from_dt = document.forms[0].from_dt.value;
		var to_dt = document.forms[0].to_dt.value;
				
		if(from_dt==null || from_dt=='' || from_dt==' ' || from_dt=='0')
		{
			msg += "Please Enter The From Date Field Properly !!!\n";
			flag = false;
		}
		
		if(to_dt==null || to_dt=='' || to_dt==' ' || to_dt=='0')
		{
			msg += "Please Enter The To Date Field Properly !!!\n";
			flag = false;
		}
	}
	else if(size>1)
	{
		for(i=0; i<(size-1); i++)
		{
			index = i+1;
			
			var from_dt = document.forms[0].from_dt[i].value;
			var to_dt = document.forms[0].to_dt[i].value;
						
			if(from_dt==null || from_dt=='' || from_dt==' ' || from_dt=='0')
			{
				msg += "Please Enter The From Date Field Properly For Record NO: "+index+" !!!\n";
				flag = false;
			}
			
			if(to_dt==null || to_dt=='' || to_dt==' ' || to_dt=='0')
			{
				msg += "Please Enter The To Date Field Properly For Record NO: "+index+" !!!\n";
				flag = false;
			}	
		}
	}
	else
	{
		msg += "NOT A Single Record Is Generated For Submission !!!\n";
		flag = false;
	}
		
	if(flag)
	{
		var a = confirm("Do You Want To Submit Deactivation Specification Details For Selected FLSA ???");
		
		if(a)
		{
			document.forms[0].submit();
		}
	}
	else
	{
		alert(msg);
	}
}

function delete_fgsa_deactivation(del_frm_dt, del_to_dt)
{
	document.forms[0].del_from_dt.value = del_frm_dt;
	document.forms[0].del_to_dt.value = del_to_dt;
	
	document.forms[0].option.value = "Delete_FGSA_Deactivation";
	document.forms[0].submit();
}

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_MasterV2" id="mst" scope="page"/>
<%
	String comp_cd=session.getAttribute("comp_cd")==null?"56":""+session.getAttribute("comp_cd");
	String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
	String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	String fgsa_cd = request.getParameter("fgsa_cd")==null?"0":request.getParameter("fgsa_cd");
	String fgsa_revno = request.getParameter("fgsa_revno")==null?"0":request.getParameter("fgsa_revno");
	String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
	String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
	String index = request.getParameter("index")==null?"1":request.getParameter("index");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String form_id = request.getParameter("form_id")==null?"0":request.getParameter("form_id");
	String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
	System.out.println("form_id = "+form_id);
	System.out.println("form_nm = "+form_nm);
	int ind = Integer.parseInt(index);
	
	String write_permission = (String)session.getAttribute("write_permission")==null?"":(String)session.getAttribute("write_permission");
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"":(String)session.getAttribute("delete_permission");
	String print_permission = (String)session.getAttribute("print_permission")==null?"":(String)session.getAttribute("print_permission");
	String approve_permission = (String)session.getAttribute("approve_permission")==null?"":(String)session.getAttribute("approve_permission");
	String audit_permission = (String)session.getAttribute("audit_permission")==null?"":(String)session.getAttribute("audit_permission");
	
	mst.setCallFlag("FGSA_DEACTIVATION_LIST");
	mst.setBuyer_cd(buyer_cd);
	mst.setFGSA_cd(fgsa_cd);
	mst.setFGSA_REVNo(fgsa_revno);
	mst.init();
	
	//Following 3 Vector Getter Methods Has Been Introduced By Samik Shah On 11th January, 2011 ...
	Vector fgsa_Deactivation_From_Dt = mst.getFgsa_Deactivation_From_Dt();
	Vector fgsa_Deactivation_To_Dt = mst.getFgsa_Deactivation_To_Dt();
	Vector fgsa_Deactivation_Remark = mst.getFgsa_Deactivation_Remark();
	
	int ind2 = fgsa_Deactivation_From_Dt.size();
%>
<body >
<div class="tab-content">
	<div class="tab-pane active" id="">
		<div class="box mb-0">
			<form method="post" action="../servlet/Frm_Contract_MasterV2">
			<%if (msg.length() > 5) {%>
				<div class="box-body table-responsive no-padding">
					<table class="table  table-bordered">
						<thead>
							<tr class="info">
								<th class="text-center" colspan="7" style="color: blue;">
									<%=msg%>
								</th>
							</tr>
						</thead>
					</table>
				</div>
				<%}%>
			
				<div class="box-body table-responsive no-padding">
					<div class="row">
						<div class="table-responsive col-lg-12">
							<table class="table table-striped" >
								<thead>   
									<tr><th colspan="7" class="info text-center"><b><%=buyer_name%> - FLSA: <%=fgsa_cd%> Deactivation Specification Within Duration Of --&gt; <%=start_dt%> To: <%=end_dt%></b></th></tr>
									<tr>
										<th colspan="7" class="text-center" style="font-size: 14px;">
											<b>Existing FLSA Deacivation Specification</b>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr class="info text-center">
										<td ><font color="red">*</font><b>From Date</b></td>
										<td ><font color="red">*</font><b>To Date</b></td>
										<td><b>Remark</b></td>
										<td >&nbsp;</td>
									</tr>
									
									<%	for(int i=0; i<ind2; i++){%>
											<tr>
												<td>
													<div align="center">
														<input type="text" name="from_dt" value="<%=(String)fgsa_Deactivation_From_Dt.elementAt(i)%>" size="10" maxlength="11" onchange="validateDate(this);checkWithContractStartDate(this);checkDurationValidity('<%=i%>','<%=(ind2+ind)%>',this);">
													<%	if((ind+ind2)>1)
														{	%>
															<%-- <a href="javascript:show_calendar('forms[0].from_dt[<%=i%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
																<img src="../images/calendar.gif" width="25" height="22" border="0">
															</a> --%>
													<%	}
														else
														{	%>
															<!-- <a href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
																<img src="../images/calendar.gif" width="25" height="22" border="0">
															</a> -->
													<%	}	%>
													</div>
												</td>
												<td>
													<div align="center">
														<input type="text" name="to_dt" value="<%=(String)fgsa_Deactivation_To_Dt.elementAt(i)%>" size="10" maxlength="11" onchange="validateDate(this);checkWithContractEndDate(this);checkDurationValidity('<%=i%>','<%=(ind2+ind)%>',this);">
													<%	if((ind+ind2)>1)
														{	%>
															<a href="javascript:show_calendar('forms[0].to_dt[<%=i%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
																<img src="../images/calendar.gif" width="25" height="22" border="0">
															</a>
													<%	}
														else
														{	%>
															<a href="javascript:show_calendar('forms[0].to_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
																<img src="../images/calendar.gif" width="25" height="22" border="0">
															</a>
													<%	}	%>
													</div>
												</td>
												<td>
													<div align="center">
														<input type="text" name="remark" value="<%=(String)fgsa_Deactivation_Remark.elementAt(i)%>" size="60" maxlength="500">
													</div>
												</td>
												<td>
													<div align="center">
														<input type="button" name="save" value="Delete" onclick="delete_fgsa_deactivation('<%=(String)fgsa_Deactivation_From_Dt.elementAt(i)%>','<%=(String)fgsa_Deactivation_To_Dt.elementAt(i)%>')">
													</div>
												</td>
											</tr>
									<%	}	%>
									<%	if(ind2>0)
										{	%>
											<tr >
												<td colspan="4">
													<div align="center">
														&nbsp;
													</div>
												</td>
											</tr>
									<%	}
										else
										{	%>
											<tr class="text-center">
												<td colspan="4">
													<div align="center">
														<b style="color: red">FLSA Deactivation Specification NOT Exist For The Selected FLSA ...</b>
													</div>
												</td>
											</tr>
									<%	}	%>
										<tr class="text-center">
											<td colspan="4" >
												<div align="left"><b >NO Of Periods For Which FLSA Deactivation Specification Is Required:&nbsp;</b><input type="text" name="index" size="3" maxlength="2" value="<%=ind%>" onchange="checkNumber1(this,2,0);refreshPage();"></div>
											</td>
										</tr>
										<tr class="info text-center">
											<td width="20%"><div align="center"><font color="red">*</font><b>From&nbsp;Date</b></div></td>
											<td width="20%"><div align="center"><font color="red">*</font><b>To&nbsp;Date</b></div></td>
											<td width="60%" colspan="2"><div align="center"><b>Remark</b></div></td>
										</tr>
									<%	for(int i=ind2; i<(ind+ind2); i++)
										{	%>
											<tr class="content1">
												<td>
													<div align="center">
														<input type="text" name="from_dt" class="datepick2" value="" size="10" maxlength="11" onchange="validateDate(this);checkWithContractStartDate(this);checkDurationValidity('<%=i%>','<%=(ind2+ind)%>',this);">
													<%	if((ind+ind2)>1)
														{	%>
															<%-- <a href="javascript:show_calendar('forms[0].from_dt[<%=i%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
																<img src="../images/calendar.gif" width="25" height="22" border="0">
															</a> --%>
													<%	}
														else
														{	%>
															<!-- <a href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
																<img src="../images/calendar.gif" width="25" height="22" border="0">
															</a> -->
													<%	}	%>
													</div>
												</td>
												<td>
													<div align="center">
														<input type="text" class="datepick1" id="to_dt" name="to_dt" value="" size="10" maxlength="11" onchange="validateDate(this);checkWithContractEndDate(this);checkDurationValidity('<%=i%>','<%=(ind2+ind)%>',this);">
													<%	if((ind+ind2)>1)
														{	%>
															<%-- <a href="javascript:show_calendar('forms[0].to_dt[<%=i%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
																<img src="../images/calendar.gif" width="25" height="22" border="0">
															</a> --%>
													<%	}
														else
														{	%>
															<!-- <a href="javascript:show_calendar('forms[0].to_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
																<img src="../images/calendar.gif" width="25" height="22" border="0">
															</a> -->
													<%	}	%>
													</div>
												</td>
												<td colspan="2">
													<div align="center">
														<input type="text" name="remark" value="" size="80" maxlength="500">
													</div>
												</td>
											</tr>
									<%	}	%>
										<tr >
											<td colspan="4">
												<div align="right">
													&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">* Mandatory Information</font>
												</div>
											</td>
										</tr>
										<tr >
											<td colspan="2">
												<div align="left">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 						
													&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="reload" value="Reset" onclick="resetPage();">
												</font>
												</div>
											</td>
											<td colspan="2">
												<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 						
												<%-- <%	if(write_permission.trim().equalsIgnoreCase("Y"))
													{	%> --%>
<%-- 														<input type="button" name="save" value="Submit" onClick="doSubmit1('<%=(ind+ind2)%>');">&nbsp;&nbsp;&nbsp;&nbsp; --%>
														<input type="button" class="btn btn-success" name="save" value="Submit" onClick="doSubmit1('<%=(ind+ind2)%>');">
												<%-- <%	}
													else
													{	%>
														<input type="button" class="btn btn-success" name="save" value="Submit" disabled title="You have no permission!!">
												<%	}	%> --%>
												</font>
												</div>
											</td>
										</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>				
		
			
			<input type="hidden" name="option" value="insertModifyFgsaDeactivationDetails">
			<input type="hidden" name="buyer_cd" value="<%=buyer_cd%>">
			<input type="hidden" name="buyer_name" value="<%=buyer_name%>">
			<input type="hidden" name="fgsa_cd" value="<%=fgsa_cd%>">
			<input type="hidden" name="fgsa_revno" value="<%=fgsa_revno%>">
			<input type="hidden" name="start_dt" value="<%=start_dt%>">
			<input type="hidden" name="end_dt" value="<%=end_dt%>">
			<input type="hidden" name="form_id" value="<%=form_id%>">
			<input type="hidden" name="form_nm" value="<%=form_nm%>">
			<input type="hidden" name="write_permission" value="<%=write_permission%>">
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
			<input type="hidden" name="print_permission" value="<%=print_permission%>">
			<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
			<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			
			<input type="hidden" name="del_from_dt" value="">
			<input type="hidden" name="del_to_dt" value="">
		</form>
		</div>
	</div>
</div>
</body>
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>

<script type="text/javascript">
$(document).ready(function()
		{
			for(var k=1;k<=5;k++) 
			{
				var dtt = ".datepick"+k;
				$(dtt).datepicker({
					changeMonth: true,
					changeYear: true,
					format: "dd/mm/yyyy",
					language: "en",
					orientation: "bottom auto",
					autoclose: true,
					todayHighlight: true
				});
			}
		});
</script>

</html>