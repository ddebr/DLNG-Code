<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>DCQ Specification For SN</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="../css/guidefaultGeneral.css">
<link rel="stylesheet" href="../css/utilities.css">

<script type="text/javascript" src="../js/date-picker.js"></script>
<script type="text/javascript" src="../js/aris.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/validations.js"></script>

<style type="text/css">
<!--
#loading {

	width: 200px;

	height: 100px;

	background-color: #c0c0c0;

	position: absolute;

	left: 50%;

	top: 50%;

	margin-top: -50px;

	margin-left: -100px;

	text-align: center;

}
-->
</style>

<script type="text/javascript">

document.write('<div id="loading"><br><br>Loading, Please Wait...</div>');

window.onload=function()
{
	document.getElementById("loading").style.display="none";
   var flag1=document.forms[0].flag.value;
   var index=document.forms[0].index.value
  // alert(flag1)
   if(flag1=='F')
   {
       document.getElementById("dcqline").style.display="none";
       document.forms[0].index.value = '1';
   }
   else
   {
        document.getElementById("dcqline").style.display="table-row";
        document.forms[0].index.value = parseInt(index)+1;
   }
}

function refreshPage()
{
	var buyer_cd = document.forms[0].buyer_cd.value;
	var fgsa_cd = document.forms[0].fgsa_cd.value;
	var fgsa_revno = document.forms[0].fgsa_revno.value;
	var sn_cd = document.forms[0].sn_cd.value;
	var sn_revno = document.forms[0].sn_revno.value;
	var start_dt = document.forms[0].start_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var index = document.forms[0].index.value;
	var flag1 = 'T';
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	//alert(index)
	if(index!=null)
	{	
		if(index!='' && index!=' ' && index!='  ')
		{
			if(parseInt(index)>=1)
			{
			    
				location.replace("frm_sn_var_dcq.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&index="+index+"&flag1="+flag1+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
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

function adddcq()
{


}

function resetPage()
{
	var buyer_cd = document.forms[0].buyer_cd.value;
	var fgsa_cd = document.forms[0].fgsa_cd.value;
	var fgsa_revno = document.forms[0].fgsa_revno.value;
	var sn_cd = document.forms[0].sn_cd.value;
	var sn_revno = document.forms[0].sn_revno.value;
	var start_dt = document.forms[0].start_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var index = "1";
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("frm_sn_var_dcq.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&index="+index+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
}


function checkForDateValidity(obj,start_date,end_date)
{
	var start_dt = ""+start_date;
	var end_dt = ""+end_date;
	var dcq_dt = obj.value;
	
	var from_dt = document.forms[0].from_dt.value;
	var to_dt = document.forms[0].to_dt.value;
	
	if(from_dt!='' && to_dt!=''){
		
		var value_3 = compareDate(to_dt,from_dt);
		if(value_3 == 2){
			alert("DCQ To Date ("+to_dt+") Must Be Greater Than Or Equal To DCQ From Date ("+from_dt+") !!!");
	    	obj.value = "";
	    	//obj.select();
	    	return false;
		}
		
	}
	//alert("dcq_dt = "+dcq_dt+",  start_dt = "+start_dt+",  And  end_dt = "+end_dt);
  	var value_1 = compareDate(dcq_dt,start_dt);
  	//alert("Value_1 = "+value_1);
  	if(value_1 == 2)
  	{
    	alert("DCQ Duration Date ("+dcq_dt+") Must Be Greater Than Or Equal To SN Start Date ("+start_dt+") !!!");
    	obj.value = "";
    	//obj.select();
    	return false;
  	}
  	
  	var value_2 = compareDate(dcq_dt,end_dt);
  	//alert("Value_2 = "+value_2);
  	if(value_2 == 1)
  	{
    	alert("DCQ Duration Date ("+dcq_dt+") Must Be Less Than Or Equal To SN End Date ("+end_dt+") !!!");
    	obj.value = "";
    	//obj.select();
    	return false;
  	}
  	
//   	alert("from_dt---"+from_dt+"--"+to_dt);
	
}
//JHP START
function checkForDateValidityall(obj,index,st_dt1,end_dt1)
{
	//alert("hiii"+index);
	var st_dt1 = ""+st_dt1;
	var end_dt1 = ""+end_dt1;
	var start_dt = document.forms[0].from_dt;
	var end_dt = document.forms[0].to_dt;
	var dcq_dt = obj.value;
	var size=document.forms[0].size.value;
	//alert(size);
	
	var value_11 = compareDate(dcq_dt,st_dt1);
	var value_21 = compareDate(dcq_dt,end_dt1);
	//alert("value_11:"+value_11+"value_21:"+value_21);
	for(var i=0;i<size;i++)
	{
	//alert(start_dt[i].value+"-----"+end_dt[i].value+"-----"+dcq_dt);
	var start_dt1=start_dt[i].value;
	var value_1 = compareDate(dcq_dt,start_dt1);
	if(i!=index)
	{
	if(value_1 == 0)
  	{
  	alert("DCQ Duration Date ("+dcq_dt+") is already Present.");
  	obj.value = "";
  	return false;
  	}
  	}
	var	end_dt1=end_dt[i].value;
  	var value_2 = compareDate(dcq_dt,end_dt1);
  	
	if(i!=index)
	{
  	if(value_2 == 0)
  	{
  	alert("DCQ Duration Date ("+dcq_dt+") is already Present.");
  	obj.value = "";
  	return false;
  	}
  	}
  //	alert("value_1:"+value_1);
  //	alert("value_2:"+value_2);
  	if(value_1 == 1 &&  value_2 == 2 )
  	{
  	alert("DCQ Duration Date ("+dcq_dt+") is already Present in Period ("+start_dt1+") to ("+end_dt1+")");
  	obj.value = "";
  	return false;
  	
	}
	if(value_1 == 2 &&  value_2 == 1 )
  	{
  	alert("DCQ Duration Date ("+dcq_dt+") is already Present in Period ("+start_dt1+") to ("+end_dt1+")");
  	obj.value = "";
  	return false;
  	
	}
	
	}
	
}
/*
function checkForDateValidityall1(obj,index,end_date)
{
	//alert("hiii"+index);
	var start_dt = document.forms[0].from_dt;
	var end_dt = document.forms[0].to_dt;
	var dcq_dt = obj.value;
	var size=document.forms[0].size.value;
	//alert(size);
	for(var i=0;i<size;i++)
	{
	//alert(start_dt[i].value+" "+end_dt[i].value+" "+dcq_dt);
	var start_dt1=start_dt[i].value;
	var value_1 = compareDate(dcq_dt,start_dt1);
	if(i!=index)
	{
	if(value_1 == 0)
  	{
  	alert("Date is already Present....");
  	obj.value = "";
  	return false;
  	}
  	}
	var	end_dt1=end_dt[i].value;
  	var value_2 = compareDate(dcq_dt,end_dt1);
  	
	if(i!=index)
	{
  	if(value_2 == 0)
  	{
  	alert("Date is already Present....");
  	obj.value = "";
  	return false;
  	}
  	}
  	if(value_1 == 1 &&  value_2 == 2 )
  	{
  	alert("Date Priod is already Present....");
  	obj.value = "";
  	return false;
  	
	}
	if(value_1 == 2 &&  value_2 == 1 )
  	{
  	alert("Date Priod is already Present....");
  	obj.value = "";
  	return false;
  	
	}
	}
	
}
*/
//JHP END
function doSubmit(sz)
{
	var msg = "Please Check the Following Field(s):\n\n";
	var flag = true;
	
	var i = 0;
	var index = 0;
	var size = parseInt(sz);
	
	if(size<=1)
	{
		var from_dt = document.forms[0].from_dt.value;
		var to_dt = document.forms[0].to_dt.value;
		var dcq = document.forms[0].dcq.value;
		
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
		if(dcq==null || dcq=='' || dcq==' ')
		{
			msg += "Please Enter The DCQ Field Properly !!!\n";
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
			var dcq = document.forms[0].dcq[i].value;
			
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
			
			if(dcq==null || dcq=='' || dcq==' ')
			{
				msg += "Please Enter The DCQ Field Properly For Record NO: "+index+" !!!\n";
				flag = false;
			}	
		}
	}
	
	if(flag)
	{
		var a = confirm("Do You Want To Submit DCQ Specification Details For Selected SN ???");
		
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

</script>

</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_MasterV2" id="mst" scope="page"/>
<%
	utilBean.init();
	String date = utilBean.getGen_dt();
// 	System.out.println("date---"+date);
	
	String comp_cd=session.getAttribute("comp_cd")==null?"56":""+session.getAttribute("comp_cd");
	String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
	String fgsa_cd = request.getParameter("fgsa_cd")==null?"0":request.getParameter("fgsa_cd");
	String fgsa_revno = request.getParameter("fgsa_revno")==null?"0":request.getParameter("fgsa_revno");
	String sn_cd = request.getParameter("sn_cd")==null?"0":request.getParameter("sn_cd");
	String sn_revno = request.getParameter("sn_revno")==null?"0":request.getParameter("sn_revno");
	String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
	String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
	String index = request.getParameter("index")==null?"1":request.getParameter("index");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String flag1= request.getParameter("flag1")==null?"F":request.getParameter("flag1");
	int ind = Integer.parseInt(index);
	
	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	
	mst.setCallFlag("SN_DCQ_LIST");
	mst.setBuyer_cd(buyer_cd);
	mst.setFGSA_cd(fgsa_cd);
	mst.setFGSA_REVNo(fgsa_revno);
	mst.setSN_CD(sn_cd);
	mst.setSN_REVNo(sn_revno);
	mst.init();
	
	//Following 4 Vector Getter Methods Has Been Introduced By Samik Shah On 13th July, 2010 ...
	Vector sn_Dcq_From_Dt = mst.getSn_Dcq_From_Dt();
	Vector sn_Dcq_To_Dt = mst.getSn_Dcq_To_Dt();
	Vector sn_Dcq_Value = mst.getSn_Dcq_Value();
	Vector sn_Dcq_Remark = mst.getSn_Dcq_Remark();
	
	int ind2 = sn_Dcq_From_Dt.size();
%>
<body>

<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script type="text/javascript" src="../js/mouseclk.js"></script>
<%	}	%>

<div align="center" id="col-three">
<form name="product_list" method="post" action="../servlet/Frm_Contract_MasterV2">
<%	if(msg.length()>10)
    {	%>
    	<table align="center" width="90%">
    		<tr>
				<td class="message">
					<div align="left"><font size="3"><b><%=msg%></b></font></div>
				</td>
			</tr>
		</table>
<%	}	%>
<div id="col-new">
<fieldset  style="width:90%" align="center">
    <table width="100%" border="0">
    <tr>
		<td colspan="4" class="highlighttext">
			<div align="left"><font size="3"><b>DCQ Specification For SN From: <%=start_dt%> To: <%=end_dt%></b></font></div>
		</td>
	</tr>
	<tr>
		<td colspan="4" class="title2">
			<div align="left"><b>Existing DCQ Specification</b></div>
		</td>
	</tr>
	<tr class="title2">
		<td width="20%"><div align="center"><font color="red">*</font>From&nbsp;Date</div></td>
		<td width="20%"><div align="center"><font color="red">*</font>To&nbsp;Date</div></td>
		<td width="15%"><div align="center"><font color="red">*</font>DCQ&nbsp;(MMBTU)</div></td>
		<td width="45%"><div align="center">Remark</div></td>
		<input type="hidden" name="size" value="<%=ind2 %>" >
	</tr>
	
<%	for(int i=0; i<ind2; i++)
	{	%>
		<tr class="content1">
			<td>
				<div align="center">
					<input type="text" name="from_dt" value="<%=(String)sn_Dcq_From_Dt.elementAt(i)%>" size="10" maxlength="11" onBlur="validateDate(this);checkForDateValidity(this,'<%=start_dt%>','<%=end_dt%>');checkForDateValidityall(this,'<%=i%>','<%=start_dt%>','<%=end_dt%>');">
					
				<%	if((ind+ind2)>1)
					{	%>
						<a href="javascript:show_calendar('forms[0].from_dt[<%=i%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0">
						</a>
				<%	}
					else
					{	%>
						<a href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0">
						</a>
				<%	}	%>
				</div>
			</td>
			<td>
				<div align="center">
					<input type="text" name="to_dt" value="<%=(String)sn_Dcq_To_Dt.elementAt(i)%>" size="10" maxlength="11" onBlur="validateDate(this);checkForDateValidity(this,'<%=start_dt%>','<%=end_dt%>');checkForDateValidityall(this,'<%=i%>','<%=start_dt%>','<%=end_dt%>');">
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
					<input type="text" name="dcq" value="<%=(String)sn_Dcq_Value.elementAt(i)%>" size="10" maxlength="10" onBlur="checkNumber1(this,9,2);">
				</div>
			</td>
			<td>
				<div align="center">
					<input type="text" name="remark" value="<%=(String)sn_Dcq_Remark.elementAt(i)%>" size="40" maxlength="500">
				</div>
			</td>
		</tr>
<%	}	%>
<%	if(ind2>0)
	{	%>
		<tr class="title2">
			<td colspan="4">
				<div align="center">
					&nbsp;
				</div>
			</td>
		</tr>
<%	}
	else
	{	%>
		<tr class="content1">
			<td colspan="4">
				<div align="center">
					<b>DCQ Specification NOT Exist For The Selected SN ...</b>
				</div>
			</td>
		</tr>
<%	}	%>
	<tr>
		<td colspan="3" class="title2">
			<div align="left"><b></b></div>
		</td>
		<td colspan="1" class="content1">
			<div align="left">
			<input type="hidden" name="index" size="3" maxlength="2" value="<%=ind%>" onBlur="checkNumber1(this,2,0);refreshPage();">
			<input type="button" name="c" size="3" maxlength="2" value="Add New DCQ Specification" onclick="refreshPage();">
			</div>
		</td>
	</tr>
	<tr class="title2">
		<td width="20%"><div align="center"><font color="red">*</font>From&nbsp;Date</div></td>
		<td width="20%"><div align="center"><font color="red">*</font>To&nbsp;Date</div></td>
		<td width="15%"><div align="center"><font color="red">*</font>DCQ&nbsp;(MMBTU)</div></td>
		<td width="45%"><div align="center">Remark</div></td>
	</tr>
<%	for(int i=ind2; i<(ind2+ind); i++)
	{	%>
		<tr class="content1" id="dcqline">
			<td>
				<div align="center">
					<input type="text" name="from_dt" value="" size="10" maxlength="11" onBlur="validateDate(this);checkForDateValidity(this,'<%=start_dt%>','<%=end_dt%>');checkForDateValidityall(this,'<%=i%>','<%=start_dt%>','<%=end_dt%>');">
				<%	if((ind+ind2)>1)
					{	%>
						<a href="javascript:show_calendar('forms[0].from_dt[<%=i%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0">
						</a>
				<%	}
					else
					{	%>
						<a href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0">
						</a>
				<%	}	%>
				</div>
			</td>
			<td>
				<div align="center">
					<input type="text" name="to_dt" value="" size="10" maxlength="11" onBlur="validateDate(this);checkForDateValidity(this,'<%=start_dt%>','<%=end_dt%>');checkForDateValidityall(this,'<%=i%>','<%=start_dt%>','<%=end_dt%>');">
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
					<input type="text" name="dcq" value="" size="10" maxlength="10" onBlur="checkNumber1(this,9,2);">
				</div>
			</td>
			<td>
				<div align="center">
					<input type="text" name="remark" value="" size="40" maxlength="500">
				</div>
			</td>
		</tr>
<%	}	%>
	<tr class="title2">
		<td colspan="4">
			<div align="right">
				&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">* Mandatory Information</font>
			</div>
		</td>
	</tr>
	<tr class="title2">
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
			<%	if(write_permission.trim().equalsIgnoreCase("Y"))
				{	%>
					<input type="button" name="save" value="Submit" onClick="doSubmit('<%=(ind+ind2)%>');">&nbsp;&nbsp;&nbsp;&nbsp;
			<%	}
				else
				{	%>
					<input type="button" name="save" value="Submit" style="font-size:15;font-weight:bold;" disabled>&nbsp;&nbsp;&nbsp;&nbsp;
			<%	}	%>
			</font>
			</div>
		</td>
	</tr>
</table>
</fieldset>
<table>
	<tr>
		<td>
			<input type="hidden" name="option" value="insertModifySnDcqDetails">
			<input type="hidden" name="buyer_cd" value="<%=buyer_cd%>">
			<input type="hidden" name="fgsa_cd" value="<%=fgsa_cd%>">
			<input type="hidden" name="fgsa_revno" value="<%=fgsa_revno%>">
			<input type="hidden" name="sn_cd" value="<%=sn_cd%>">
			<input type="hidden" name="sn_revno" value="<%=sn_revno%>">
			<input type="hidden" name="start_dt" value="<%=start_dt%>">
			<input type="hidden" name="end_dt" value="<%=end_dt%>">
			<input type="hidden" name="write_permission" value="<%=write_permission%>">
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
			<input type="hidden" name="print_permission" value="<%=print_permission%>">
			<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
			<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			<input type="hidden" name="flag" value="<%=flag1%>">
			<input type="hidden" name="sysdate" value="<%=date%>">
		</td>
	</tr>
</table>
</div>
</form>
</div>
</body>
</html>