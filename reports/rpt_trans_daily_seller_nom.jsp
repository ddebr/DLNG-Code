<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<%@ page import="java.io.File"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script src="../js/jquery.min.js"></script>
<style>
   .custom-table{border-collapse:collapse;width:100%;border:solid 1px #c0c0c0;}
            .custom-table th{text-align:center;padding:8px;border:solid 1px #c0c0c0;},.custom-table td{text-align:left;padding:8px;border:solid 1px #c0c0c0}
            .custom-table th{color:#000080}
            .custom-table tr:nth-child(odd){background-color:#f7f7ff}
            .custom-table>thead>tr{background-color:#dde8f7!important}
            .tbtn{border:0;outline:0;background-color:transparent;font-size:13px;cursor:pointer}
            .toggler{display:none}
            .toggler1{display:table-row;}
            .custom-table a{color: #0033cc;}
            .custom-table a:hover{color: #f00;}
            .page-header{background-color: #eee;}
            /* Define the hover highlight color for the table row */
    		.custom-table tr:hover {background-color: #ffff99;}
    		.custom-table tr:hover {background-color: #ffff99;}
 
 .table-responsive {
    max-height:400px;
}   		 
</style>
<script type="text/javascript">
/* for accordian */
$(document).ready(function () {
    $(".tbtn").click(function () {
        $(this).parents(".custom-table").find(".toggler1").removeClass("toggler1");
        $(this).parents("tbody").find(".toggler").addClass("toggler1");
        $(this).parents(".custom-table").find(".fa-minus-circle").removeClass("fa-minus-circle");
        $(this).parents("tbody").find(".fa-plus-circle").addClass("fa-minus-circle");
    });
});

function refreshPage()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	var gas_date = document.forms[0].gas_date.value;
	var to_cntct = "";
	var from_cntct = "";
	
	location.replace(modUrl+"?gas_date="+gas_date+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl);
}

function sendParent1(gas_date,gen_date,ind)
{
	var trans_cd = document.getElementById('trans_cd'+ind).value;
	var seq_no = document.getElementById('to'+ind).value;
	var supp_seq_no = document.getElementById('supp_seq_no'+ind).value;
	
	if(!newWindow || newWindow.closed)
	{
// 		alert(gas_date)
		newWindow= window.open("../reports/rpt_trans_daily_seller_nomination.jsp?gas_date="+gas_date+"&index="+ind+"&trans_cd="+trans_cd+"&seq_no="+seq_no+"&supp_seq_no="+supp_seq_no,"rpt_Trans_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
	else
	{
		newWindow.close();
	    newWindow= window.open("../reports/rpt_trans_daily_seller_nomination.jsp?gas_date="+gas_date+"&index="+ind+"&trans_cd="+trans_cd+"&seq_no="+seq_no+"&supp_seq_no="+supp_seq_no,"rpt_Trans_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
}

function sendParent2(gas_date,gen_date,ind)
{
	var trans_cd = document.getElementById('trans_cd'+ind).value;
	var seq_no = document.getElementById('to'+ind).value;
	var supp_seq_no = document.getElementById('supp_seq_no'+ind).value;
	
	if(!newWindow || newWindow.closed)
	{
// 		alert(gas_date)
		newWindow= window.open("../reports/pdf_trans_daily_seller_nom.jsp?gas_date="+gas_date+"&index="+ind+"&trans_cd="+trans_cd+"&seq_no="+seq_no+"&supp_seq_no="+supp_seq_no,"rpt_Trans_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
	else
	{
		newWindow.close();
	    newWindow= window.open("../reports/pdf_trans_daily_seller_nom.jsp?gas_date="+gas_date+"&index="+ind+"&trans_cd="+trans_cd+"&seq_no="+seq_no+"&supp_seq_no="+supp_seq_no,"rpt_Trans_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
}
function openxlsheet(gas_date,gen_date,ind)
{	
	var trans_cd = document.getElementById('trans_cd'+ind).value;
	var seq_no = document.getElementById('to'+ind).value;
	var supp_seq_no = document.getElementById('supp_seq_no'+ind).value;
   	  		
	if(!newWindow || newWindow.closed)
	{
		newWindow= window.open("../reports/xls_trans_daily_seller_nomination.jsp?gas_date="+gas_date+"&index="+ind+"&trans_cd="+trans_cd+"&seq_no="+seq_no+"&supp_seq_no="+supp_seq_no,"top=10,left=70,width=900,height=700,scrollbars=1");
	}
	else 
	{
		newWindow.close();
	    newWindow= window.open("../reports/xls_trans_daily_seller_nomination.jsp?gas_date="+gas_date+"&index="+ind+"&trans_cd="+trans_cd+"&seq_no="+seq_no+"&supp_seq_no="+supp_seq_no,"rpt_Trans_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}			
}
function sendEmail(gas_date,gen_date,ind)
{	
	var trans_cd = document.getElementById('trans_cd'+ind).value;
	var seq_no = document.getElementById('to'+ind).value;
	var supp_seq_no = document.getElementById('supp_seq_no'+ind).value;
   	  		
		if(!newWindow || newWindow.closed)
		{
			newWindow= window.open("../reports/mail_trans_daily_seller_nomination.jsp?gas_date="+gas_date+"&index="+ind+"&trans_cd="+trans_cd+"&seq_no="+seq_no+"&supp_seq_no="+supp_seq_no,"top=10,left=70,width=900,height=700,scrollbars=1");
		}
		else 
		{
			newWindow.close();
		    newWindow= window.open("../reports/mail_trans_daily_seller_nomination.jsp?gas_date="+gas_date+"&index="+ind+"&trans_cd="+trans_cd+"&seq_no="+seq_no+"&supp_seq_no="+supp_seq_no,"rpt_Trans_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
		}			
}

function checkUncheckAll()
{
	var count = parseInt(document.forms[0].count1.value);
	var chk = document.forms[0].chkUNchk.value;
	var i = 0;
	
	if(count==1)
	{
		if(chk=='Check All')
		{
			document.forms[0].chk_cont.checked = true;
		}
		else if(chk=='Un-Check All')
		{
			document.forms[0].chk_cont.checked = false;
		}
	}
	else if(count>1)
	{
		if(chk=='Check All')
		{
			for(i=0;i<count;i++)
			{
				document.forms[0].chk_cont[i].checked = true;
			}
		}
		else if(chk=='Un-Check All')
		{
			for(i=0;i<count;i++)
			{
				document.forms[0].chk_cont[i].checked = false;
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

function refreshPageFromChild2(gas_date,msg){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	location.replace("../reports/rpt_master.jsp?gas_date="+gas_date+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&msg="+msg+"&modUrl="+modUrl);
}

function doSubmit(ind)
{
	var flag = true;
	var msg = "First Check The Following Fields :\n\n";	
	var gas_date = document.forms[0].gas_date.value;
	
	if(gas_date==null || gas_date=='' || gas_date==' ')
	{
		msg += "Please, First Select The Gas - Date !!!\n";
		flag = false;
	}
		
	if(flag)
	{
		var a = confirm("Do You Want To Submit Data Regarding This Daily Seller Nomination to Customer Report???");		
		if(a)
		{
			document.forms[0].selIndx.value = ind;
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
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Seller_Report_Generation" id="dsRpt"  scope="page"/>

<%
	double convt_mmbtu_to_mt = (double) session.getAttribute("convt_mmbtu_to_mt");
	utilBean.init();
	
	String curr_dt = utilBean.getGen_dt();
	String next_dt = utilBean.getDate_tomorrow();

	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();
	String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210814
	String write_permission = (String)session.getAttribute("write_permission")==null?"":(String)session.getAttribute("write_permission");
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"":(String)session.getAttribute("delete_permission");
	String print_permission = (String)session.getAttribute("print_permission")==null?"":(String)session.getAttribute("print_permission");
	String approve_permission = (String)session.getAttribute("approve_permission")==null?"":(String)session.getAttribute("approve_permission");
	String audit_permission = (String)session.getAttribute("audit_permission")==null?"":(String)session.getAttribute("audit_permission");
	String check_permission =(String)session.getAttribute("check_permission")==null?"":(String)session.getAttribute("check_permission");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	
	String gas_date = request.getParameter("gas_date")==null?next_dt:request.getParameter("gas_date");
	String gen_date = request.getParameter("gen_date")==null?curr_dt:request.getParameter("gen_date");
	
	dsRpt.setGen_date(gen_date);
	dsRpt.setGas_date(gas_date);
	dsRpt.setCallFlag("DAILY_SELLER_NOM_TRANSPORTER");
	dsRpt.init();	
	
	if(!dsRpt.getGas_date().trim().equalsIgnoreCase(""))
	{
		gas_date = dsRpt.getGas_date().trim();
	}
	
	Vector Vtrans_cd = dsRpt.getVtrans_cd();
	Vector Vtrans_abbr = dsRpt.getVtrans_abbr();
	Vector Vtrans_nm =  dsRpt.getVtrans_nm();
	Vector Vtrans_pers_cnt = dsRpt.getVtrans_pers_cnt();
	Vector Vtrans_nom_cnt = dsRpt.getVtrans_nom_cnt();
	
	Vector Vcont_pers_nm = dsRpt.getVcont_pers_nm();
	Vector Vcont_pers_seq = dsRpt.getVcont_pers_seq();
	Vector Vcont_pers_desig = dsRpt.getVcont_pers_desig();
	Vector SUPP_SEQ_NO = dsRpt.getSUPP_SEQ_NO();
	Vector SUPP_CONTACT_PERSON = dsRpt.getSUPP_CONTACT_PERSON();
	
	Vector CUSTOMER_ABBR = dsRpt.getCUSTOMER_ABBR();
	Vector PLANT_NM =  dsRpt.getPLANT_NM();
	Vector CONTRACT_TYPE = dsRpt.getCONTRACT_TYPE();
	Vector SN_NO = dsRpt.getSN_NO();
	Vector REMARKS = dsRpt.getREMARKS();
%>	
<div class="tab-content">
<div class="tab-pane active" id="hcasreport">
<!-- Default box -->
<div class="box mb-0">
<form method="post" name="daily_seller_nom" action="../servlet/Frm_MgmtV2">

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="modUrl" value="<%=modUrl%>">
<input type="hidden" name="count1" value="<%=Vtrans_cd.size() %>" >
<input type="hidden" name="convt_mmbtu_to_mt" value="<%=convt_mmbtu_to_mt %>" >
<input name="selIndx" type="hidden" value="">
<input name="option" type="hidden" value="SELLER_TRANSPORTER">
<%if(msg.length()>5){%>
	
<div class="box-body table-responsive no-padding">
	<table class="table  table-bordered">
		<thead>   
			<tr class="info">
				<th class="text-center"  style="color: blue;"><%=msg %></th>
			</tr>
		</thead>
	</table>
</div>
<%} %>
	<div class="box-header with-border main-header" >
		 <div class="form-group col-md-2">
		 	 <label for="">Gas Day</label>
		      <div class='input-group date' id='datetimepicker'>
				<input type='text' class="form-control" id="d1" type="text"  name="gas_date"  value="<%=gas_date%>" onBlur="validateDate(this);" />
				<span class="input-group-addon">
					<i class="fa fa-calendar"></i>
				</span>
			</div>
		 </div>
		
		<div class="form-group col-md-9">
		 	 <label for="">&nbsp;</label>
		 	  <div class='input-group'>
		     	<button type="button"  class="btn btn-primary btn-sm"   name="viewList" value="View" onclick="refreshPage();" >View List</button>
		     </div>
		 </div>
		 
		 <%-- <div class="form-group col-md-1">
		 	 <label for="">&nbsp;</label>
		 	  <div class='input-group'>
		 	  <input type=button class="btn btn-primary btn-sm" name=print value="Print All" onclick="setPoints(this);"
			<%if(CUSTOMER_CD.size()<=0) {%>
				disabled="disabled"
			<%} %>
			>
		     </div>
		 </div> --%>
	</div>
	
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered custom-table" style="overflow-x:auto;" width="100%">	
		  <thead>   
			<tr class="info">
				<th></th>
				<th class="text-center">Transporter ABBR</th>
				<th class="text-center">To Contact <br>Person</th>
				<th class="text-center">From Contact<br>Person</th>
				<th class="text-center" colspan="2">Remarks</th>
				<th class="text-center" colspan="4">Generate Details</th>
				<th class="text-center"><input class="btn btn-primary btn-sm" type=button name=chkUNchk value="Check All" onClick="checkUncheckAll();"
				<%if(Vtrans_cd.size()==0){%>
					disabled="disabled"<%} %> >
				</th>
				<th class="text-center" ></th>
			</tr>
			</thead>
				<%
				int k = 0,p=0;
				for(int i = 0 ; i < Vtrans_cd.size() ; i++){ %>
				<tbody>
					<tr>
						<td class="page-header">
							<button type="button" class="tbtn">
								<i class="fa fa-plus-circle"></i>
							</button>
						</td>
						<td class="text-center"><%=Vtrans_abbr.elementAt(i) %>
							<input type="hidden" name="" id="trans_nm<%=i %>" value="<%=Vtrans_nm.elementAt(i) %>"> 
							<input type="hidden" name="trans_cd" id="trans_cd<%=i %>" value="<%=Vtrans_cd.elementAt(i) %>"> 
						</td>
						<td>
							<select class="form-control form-control-sm" id="to<%=i%>">
							<%for(int j = 0 ; j < Integer.parseInt(Vtrans_pers_cnt.elementAt(i)+""); j ++){ %>
								<option value="<%=Vcont_pers_seq.elementAt(k)%>"> <%=Vcont_pers_nm.elementAt(k) %></option>
							<%k++;} %>
							</select>
						</td>
						<td>
							<select class="form-control form-control-sm" id="supp_seq_no<%=i%>">
							<%for(int j = 0 ; j < SUPP_SEQ_NO.size(); j++){ %>
								<option value="<%=SUPP_SEQ_NO.elementAt(j)%>"><%=SUPP_CONTACT_PERSON.elementAt(j) %></option>
								
							<%} %>
							</select>
						</td>
						<td class="text-center"><input class="form-control form-control-sm" type="text" name="remarks" value="<%=REMARKS.elementAt(i)%>" maxlength="400" size="25">
						</td>
						<td>	
							<input type="button" class="btn btn-success btn-sm" name="remark" value="Submit" onclick="doSubmit('<%=i%>');">
						</td>
						<td>
							<input type="button" name="dtls" class="btn btn-primary btn-sm" value="Generate Details" onclick="sendParent1('<%=gas_date%>','<%=gen_date%>','<%=i%>');">
						</td>
						<td class="text-center"><input type="button" class="btn btn-primary btn-sm" name="dtls" value="View PDF" onclick="sendParent2('<%=gas_date%>','<%=gen_date%>','<%=i%>');" ></td>
				  		<td class="text-center"><input type="button" class="btn btn-warning btn-sm" name="open_excel" value="Export to Excel" onclick="openxlsheet('<%=gas_date%>','<%=gen_date%>','<%=i%>');" ></td>
				  		<td class="text-center"><input type="button" class="btn btn-primary btn-sm" name="send_email" value="Send Email" onclick="sendEmail('<%=gas_date%>','<%=gen_date%>','<%=i%>');" ></td>
				  		<td class="text-center"><input type=checkbox name=chk_cont  ></td>
					</tr>
					<tr class="toggler"  id="tr<%=i%>">
					 <td colspan="12">
					 <table width = "100%">
					 <tr><td colspan="12">
					<%for (int j = 0 ; j < Integer.parseInt(Vtrans_nom_cnt.elementAt(i)+""); j++ ) {%>
						<%if(j == 0) {%>
							<tr class="text-center" style="font-size:small;font-style: oblique;">
<!-- 								<th></th>	 -->
								<th class="text-center">Customer ABBR</th>
								<th class="text-center">Plant Name</th>
								<th class="text-center">SN/LoA No</th>
							</tr>
						<%} %>
							<tr style="background-color: #d1dcdc;font-size:small;font-style: oblique;">
<!-- 								<td></td> -->
								<td class="text-center"> <%=CUSTOMER_ABBR.elementAt(p) %></td>
								<td class="text-center"> <%=PLANT_NM.elementAt(p) %></td>
								<td class="text-center"> 
									<%if(CONTRACT_TYPE.elementAt(p).equals("S")) {%>
										SN-<%= SN_NO.elementAt(p)%>
									<%}else{ %>
										LoA-<%= SN_NO.elementAt(p)%>
									<%} %>
								</td>
								
							</tr>
						
					<%p++;} %>
					
					</td></tr></table></td></tr>
					</tbody>
				<%} %>
			
		</table>
	</div>
</form>
</div>
</div>
</div>
<!-- jQuery -->
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script>
$(function () {
	$('#datetimepicker').datepicker({
	changeMonth: true,
	changeYear: true,
	format: "dd/mm/yyyy",
	language: "en",
	autoclose: true,
	todayHighlight: true,
	orientation: "bottom auto"
	});
	});
	$(function () {
	$('#datetimepicker1').datepicker({
	changeMonth: true,
	changeYear: true,
	format: "dd/mm/yyyy",
	language: "en",
	autoclose: true,
	todayHighlight: true,
	orientation: "bottom auto"
	});
	});
</script>
</html>
