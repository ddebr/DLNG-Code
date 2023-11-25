<%@ page import="java.util.*,java.text.*"%>
<!DOCTYPE html>
<%@ page import="java.util.Vector" %>
<%@page import="java.io.File"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG | Late Payment Invoice Preparation </title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/pt_sans.css">
<link rel="stylesheet" href="../css/tlu.css">
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<script language="JavaScript" src="../js/fms.js"></script>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_LatePayment" id="latePay" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
util.init();
String late_pay_inv_dt=request.getParameter("late_pay_inv_dt")==null?"":request.getParameter("late_pay_inv_dt");
if(late_pay_inv_dt==null || late_pay_inv_dt.equals(""))
	late_pay_inv_dt = request.getParameter("invoice_dt")==null?util.getGen_dt():request.getParameter("invoice_dt");
String newDate = util.getGen_dt();
String late_pay_hlpl_inv_seq_no = request.getParameter("late_pay_hlpl_inv_seq_no")==null?"":request.getParameter("late_pay_hlpl_inv_seq_no");
String late_pay_customer_cd = request.getParameter("late_pay_customer_cd")==null?"":request.getParameter("late_pay_customer_cd");
String late_pay_contract_type = request.getParameter("late_pay_contract_type")==null?"":request.getParameter("late_pay_contract_type");
String late_pay_customer_abbr = request.getParameter("late_pay_customer_abbr")==null?"":request.getParameter("late_pay_customer_abbr");
String refreshFlag = request.getParameter("refreshFlag")==null?"N":request.getParameter("refreshFlag");
String late_pay_financial_year = request.getParameter("late_pay_financial_year")==null?"":request.getParameter("late_pay_financial_year");
String late_pay_new_inv_seq_no = request.getParameter("late_pay_new_inv_seq_no")==null?"":request.getParameter("late_pay_new_inv_seq_no");
String differ_days=request.getParameter("differ_days")==null?"":request.getParameter("differ_days");
String disc_days=request.getParameter("disc_days")==null?"0":request.getParameter("disc_days");
String month = request.getParameter("month")==null?"0":request.getParameter("month");
String year = request.getParameter("year")==null?"0":request.getParameter("year");
String activity = request.getParameter("activity")==null?"P":request.getParameter("activity");
String modify_flag = request.getParameter("modify_flag")==null?"N":request.getParameter("modify_flag");
String form_id = request.getParameter("form_id")==null?"0":request.getParameter("form_id");
String form_nm = request.getParameter("form_nm")==null?"0":request.getParameter("form_nm");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
String int_rate = request.getParameter("int_rate")==null?"":request.getParameter("int_rate");
String contactPerson = request.getParameter("contactPerson")==null?"0":request.getParameter("contactPerson");
String invoice_date = request.getParameter("invoice_date")==null?newDate:request.getParameter("invoice_date");
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200506
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200506
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200506
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210814
String write_permission = (String)session.getAttribute("write_permission")==null?"":(String)session.getAttribute("write_permission");
String delete_permission = (String)session.getAttribute("delete_permission")==null?"":(String)session.getAttribute("delete_permission");
String print_permission = (String)session.getAttribute("print_permission")==null?"":(String)session.getAttribute("print_permission");
String approve_permission = (String)session.getAttribute("approve_permission")==null?"":(String)session.getAttribute("approve_permission");
String audit_permission = (String)session.getAttribute("audit_permission")==null?"":(String)session.getAttribute("audit_permission");
String check_permission =(String)session.getAttribute("check_permission")==null?"":(String)session.getAttribute("check_permission");

//For Modify Invoice
String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
String invoice_dt = request.getParameter("invoice_dt")==null?"":request.getParameter("invoice_dt");
String act_cont_type = request.getParameter("act_cont_type")==null?"":request.getParameter("act_cont_type");
System.out.println("new_inv_seq_no-------"+new_inv_seq_no);
latePay.setCallFlag("Prepare_Late_Payment_Invoice");
latePay.setpHlpl_inv_seq_no(late_pay_hlpl_inv_seq_no);
latePay.setpContract_type(late_pay_contract_type);
latePay.setpCustomer_cd(late_pay_customer_cd);
latePay.setpFinancial_year(late_pay_financial_year);
latePay.setpInvoice_date(late_pay_inv_dt);
latePay.setpNew_inv_seq_no(late_pay_new_inv_seq_no);
latePay.setnInvoice_date(invoice_date);
latePay.setActivity(activity);
latePay.setpCal_percentage(int_rate);
latePay.setRefresh_flag(refreshFlag);
latePay.setmNew_inv_seq_no(new_inv_seq_no);
latePay.setmHlpl_inv_seq_no(hlpl_inv_seq_no);
latePay.setmInvoice_dt(invoice_dt);
latePay.setDiff_days(differ_days);
latePay.setAct_cont_type(act_cont_type);

if(msg.length()<5)
	latePay.init();

String pCustomer_abbr = latePay.getpCustomer_abbr();
String pContract_no = latePay.getpContract_no();
String pPlant_name = latePay.getpPlant_nm();
Vector pContact_person_cd = latePay.getpContact_person_cd();
Vector pContact_person_nm = latePay.getpContact_person_nm();
String pContract_start_dt = latePay.getpContract_start_dt();
String pContract_end_dt = latePay.getpContract_end_dt();
String pDue_date = latePay.getpDue_date();
String pPay_recv_date = latePay.getpPay_recv_date();
String pPay_recv_amt = latePay.getpPay_recv_amt();
String pNet_amt_inr = latePay.getpNet_amt_inr();
String pNo_days = latePay.getpNo_days();
String pCal_percentage = latePay.getpCal_percentage();
String nTax_Structure_dtl = latePay.getnTax_Structure_dtl();
String nTax_struct_cd = latePay.getnTax_struct_cd();
String nNet_amt = latePay.getnNet_amt();
Vector nTax_cd = latePay.getnTax_cd();
Vector nTax_abbr = latePay.getnTax_abbr();
Vector nTax_factor = latePay.getnTax_factor();
String nTotal_amount = latePay.getnTotal_amount();
String nTotal_tax_amount = latePay.getnTotal_tax_amount();
Map nTax_component_amt = latePay.getnTax_component_amt();
String nNew_inv_seq_no = latePay.getnNew_inv_seq_no();
// String nNew_inv_seq_no = latePay.getGST_INVOICE_SEQ_NO();//RG20190425
String nInvoice_seq_no_actual = latePay.getnInvoice_seq_no_actual();
String nCust_inv_seq_no = latePay.getnCust_inv_seq_no();
String Financial_year = latePay.getInvFinancialYear();
// System.out.println("nNew_inv_seq_no-------"+nNew_inv_seq_no);
String ndiff_days=latePay.getNdiff_days();
String year_interest=latePay.getYear_interest();
String rmk=latePay.getRmk();
act_cont_type = latePay.getAct_cont_type();
String tax_flag = latePay.getTax_flag();

//String nRemark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : Standard Chartered Bank (Ahmedabad Branch) - A/C No: 233-0-505333-1";//RG20190425
//String nRemark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : Standard Chartered Bank (Ahmedabad Branch) - A/C No: 233-0-525465-5 \n\n "+rmk;//New account number in invoice effective from 16th Nov 2021 as shown in below line
String nRemark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : CitiBank N.A., Mumbai - A/C No: 522614033, IFSC code: CITI0100000 \n\n "+rmk;
if(activity.equals("M") && refreshFlag.equals("N")) {
	nRemark_1 = latePay.getnRemark_1();
	contactPerson = latePay.getnContact_person_cd();
	invoice_date = latePay.getmInvoice_dt();
	if(!ndiff_days.equals("")){
		disc_days=""+(Integer.parseInt(pNo_days)-Integer.parseInt(ndiff_days));
	}
	nRemark_1=nRemark_1.replace("%$%$%$%$","\n");
}
/*if(!ndiff_days.equals("")){
	//disc_days=""+(Integer.parseInt(pNo_days)-Integer.parseInt(ndiff_days));
}*/
%>

<body <%if(msg.length()>10){ %>onLoad="doClose('<%=msg%>','<%=month%>','<%=year%>','<%=write_permission%>','<%=delete_permission%>','<%=check_permission%>','<%=approve_permission%>','<%//=authorize_permission%>','<%=print_permission%>');"<%} %>>
<div class="tab-content">
	<div class="tab-pane active" id="invoicing">
		<form method="post"  action="../servlet/Frm_Sales_InvoiceV2">
			<input type="hidden" name="modCd" value="<%=modCd%>">
			<input type="hidden" name="formId" value="<%=formId%>">
			<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
			<input type="hidden" name="modUrl" value="<%=modUrl%>" >
			<input type="hidden" name="act_cont_type" value="<%=act_cont_type%>" >
			<input type="hidden" name="tax_flag" value="<%=tax_flag%>" >
						
			<div class="box-body table-responsive no-padding">
				<table class="table  table-bordered" style="overflow-x:scroll"  >
				 	<thead> 
						<tr>
							<th colspan="11" class="text-center info">
								<font color="" size="4">LATE PAYMENT INVOICE DETAILS</font>
							</th>
							<th colspan="1" class="text-right info">
								<input type="button" class="btn btn-primary btn-small" name="close_window" value="Close Window" onClick="window.close();">
							</th>
						</tr>
						<tr class="main-header">
							<th  class="text-center" colspan="3">CUSTOMER</th>
							<th  class="text-center" colspan="3">CONTRACT NO.</th>
							<th  class="text-center" colspan="3">PLANT</th>
							<th  class="text-center" colspan="3">CONTACT PERSON</th>
						</tr>
						<tr>
							<th colspan="3"><div align="center"> <b><%=pCustomer_abbr%></b> </div> </th>
							<th colspan="3"><div align="center"> <b> <%=pContract_no%></b></div> </th>
						    <th colspan="3"><div align="center"> <b><%=pPlant_name%></b></div> </th>
						    <th colspan="3"><div align="center"> 
								<select name="contact_person" class = "form-control">
										<option value="0">--Select--</option>
										<%	for(int i=0; i<pContact_person_cd.size(); i++){%>
											<option value="<%=(String)pContact_person_cd.elementAt(i)%>"><%=(String)pContact_person_nm.elementAt(i)%></option>
										<%}%>
									</select>
								</div>
						    </th>
						    <script>
								document.forms[0].contact_person.value="<%=contactPerson%>";
							</script>
						</tr>
						<tr class="main-header">
							<th  class="text-center" colspan="3">LATE PAYMENT<BR>INVOICE DATE</th>
							<th  class="text-center" colspan="3">CONTRACT START&nbsp;DATE</th>
							<th  class="text-center" colspan="3">CONTRACT END&nbsp;DATE</th>
							<th  class="text-center" colspan="3">SEIPL&nbsp;LATE PAYMENT<BR>SEQUENCE&nbsp;NO</th>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
										<input class = "form-control" onchange="refreshPage(this);" onblur="validateDate(this);comparePayDate(this,'<%=pPay_recv_date%>');" type="text" style="text-align: center;" name="invoice_date" value="<%=invoice_date%>" size="8" maxlength="11" >
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<input class = "form-control" type="text" style="text-align: center;" name="contract_start_dt" value="<%=pContract_start_dt%>" size="8" class="mkRdly" readOnly maxlength="11">
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<input class = "form-control" type="text" style="text-align: center;" name="contract_end_dt" value="<%=pContract_end_dt%>" size="8" class="mkRdly" readOnly maxlength="11">
								</div>
							</td>
							<td colspan="3">
								<div align="center" style="">
								<%if(activity.equals("M")){ %>
									<font size="2"><b><%=new_inv_seq_no%></b></font>
									<%}else{ %>
									<font size="2"><b><%=nNew_inv_seq_no%></b></font>	
									<%} %>
								</div>
						    </td>
						</tr>	
						
						<tr>
							<th colspan="12" class="text-center info">
								<font color="" size="4">LATE PAYMENT INVOICE DETAILS</font>
							</th>
						</tr>
						<tr>
							<td colspan="3" style="background-color: #E0EEE0;" align="right">
								<font ><b>Due Date :</b></font>&nbsp;
							</td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
									<b><%=pDue_date%></b>
							</td>
							<td colspan="3" style="background-color: #E0EEE0;" align="right">
								<font ><b>Payment Received Date :</b></font>&nbsp;
							</td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
									<b><%=pPay_recv_date%></b>
							</td>
						</tr>
						
						<tr>
							<td colspan="3" style="background-color: #E0EEE0;" align="right">
								<font ><b>Net Amount INR :</b></font>&nbsp;
							</td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
									<b><%=pNet_amt_inr%></b>
									<input type='hidden' name='net_amt' id='net_amt' value='<%=pNet_amt_inr.replaceAll(",", "")%>'>
							</td>
							<td colspan="3" style="background-color: #E0EEE0;" align="right">
								<font ><b>Payment Received Amount INR :</b></font>&nbsp;
							</td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
										<b><%=pPay_recv_amt%></b>
						    </td>
						</tr>
						<tr>
							<td colspan="3" style="background-color: #E0EEE0;" align="right">
								<font ><b>No Of Days For Late Payment :</b></font>&nbsp;
							</td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
									<b><%=pNo_days%></b>
									<input type='hidden' name='no_days' id='no_days' value='<%=pNo_days%>'>
							</td>
							<td colspan="3" style="background-color: #E0EEE0;" align="right">
								<font ><b>Interest Rate :</b></font>&nbsp;
							</td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
								<b><input name='int_rate' class='mkRdly' readonly="readonly" type='text' size='10' value='<%=pCal_percentage%>' onchange="checkNumber(this,6,8);calculateData(this);" style="text-align: right;">&nbsp;(%/P.A)
					<%-- 			&nbsp;<%=year_interest %>&nbsp;(%/year) --%>
								</b>
						    </td>
						</tr>
						<tr >
							<td colspan="3" style="background-color: #E0EEE0;" align="right">
								<font ><b>Discount days :</b></font>&nbsp;
							</td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
									
									<b><input name='disc_days' type='text' size='3' value='<%=disc_days %>' onchange="checkNumber(this,5,2);calculateDiscount(this);" style="text-align: right;"></b>
							</td>
							<td colspan="3" style="background-color: #E0EEE0;" align="right">
								<font ><b>No of days of Interest  :</b></font>&nbsp;
							</td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
								<%if(activity.equals("M") && refreshFlag.equals("N")){ %>
									<b id = "intrDays" ><%=ndiff_days%></b>
								<%}else{ 
									if(differ_days.equals("")){%>
										<b id = "intrDays" ><%=pNo_days%></b>
									<%}else{ %>
									<b id = "intrDays" ><%=differ_days%></b>
								<%} }%>
						    </td>
						</tr>
						
						<%if(late_pay_contract_type.equals("S") || (late_pay_contract_type.equals("L"))){ %>
							<tr class="info" style="background-color: lightblue;text-align: center;">
								<td colspan="12" align="center"><font color="white"><b>&nbsp;</b></font></td>
							</tr>
						<%}else{ %>
							<tr class="" style="background-color: lightblue;text-align: center;">
								<td colspan="12" align="center"><font color="white"><b>Tax Structure Details : <%=nTax_Structure_dtl%></b></font></td>
							</tr>
						<%} %>
						<tr >
							
							<td colspan="9" align="right" style="background-color: #EDEDED;" ><b><font >Amount Payable (INR) :&nbsp;</font></b></td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
								<input class='mkRdly' readonly="readonly" type='text' value='<%=nNet_amt%>' name='total_pay' id='total_pay' style="text-align: right;" size='10'>
							</td>
						</tr>
						<%if(late_pay_contract_type.equals("S") || (late_pay_contract_type.equals("L"))){ %>
							<tr >
								<td colspan="9" align="right" style="background-color: #EDEDED;" ><b><font >Net Amount Payable (INR) :&nbsp;</font></b></td>
								<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
									<input type='text' class='mkRdly' readonly="readonly" value='<%=nNet_amt%>' name='total_amt_pay' id='total_amt_pay' style="text-align: right;" size='10'>
								</td>
							</tr>
							<%}else{ %>
								<%for(int i=0;i<nTax_cd.size();i++) { 
							String tax_amt = ""+nTax_component_amt.get(nTax_cd.elementAt(i));
							if(tax_amt==null || tax_amt=="null" || tax_amt.equals(null) || tax_amt.equals("null"))
								tax_amt = "0.00";
							%>
								<tr >
									<td colspan="9" align="right" style="background-color: #EDEDED;" ><b><font ><%=nTax_abbr.elementAt(i)%>&nbsp;(<%=nTax_factor.elementAt(i)%> %)&nbsp;</font></b></td>
									<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
										<input type='text' class='mkRdly' readonly="readonly" value='<%=tax_amt%>' name='tax_amt' style="text-align: right;" size='10'>
									</td>
								</tr>
							<% } %>
							<tr >
								<td colspan="9"  align="right" style="background-color: #EDEDED;" ><b><font >Total<%if(nTax_Structure_dtl.contains("GST")) { %> GST <% } else { %> Tax <% } %>Payable (INR) :&nbsp;</font></b></td>
								<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
									<input type='text' class='mkRdly' readonly="readonly" value='<%=nTotal_tax_amount%>' name='total_tax_pay' id='total_tax_pay' style="text-align: right;" size='10'>
								</td>
							</tr>
							<tr >
							<td colspan="9" align="right" style="background-color: #EDEDED;" ><b><font >Net Amount Payable (INR) :&nbsp;</font></b></td>
							<td colspan="3" style="background-color: #EDEDED;" align="left">&nbsp;
								<input type='text' class='mkRdly' readonly="readonly" value='<%=nTotal_amount%>' name='total_amt_pay' id='total_amt_pay' style="text-align: right;" size='10'>
							</td>
							</tr>
							<%} %>
						
						<tr class="title2" style="background-color: #EDEDED;">
							<td  colspan="3">
								<div align="center">
									&nbsp;Remark-1&nbsp;:
								</div>
							</td>
							<td colspan="9" style="background-color: #EDEDED;">
								<div align="left">
									<textarea name="remark_1" cols="80" rows="5"><%=nRemark_1%></textarea>
								</div>
							</td>
						</tr>
						<tr>
					</thead>
				</table>
				<table width = "100%">
					<tr >
						<td align="center" colspan="12">
							<input class = "btn btn-success btn-small" type='button' value='Submit' name='submitData' onclick="doSubmit();">
						</td>
					</tr>
				</table>
				<br><br>
			</div>	
			
			<input type="hidden" name="option" value="submitLatePaymentDetails">
			<input type='hidden' name='customer_cd' value='<%=late_pay_customer_cd%>'>
			<input type='hidden' name='hlpl_inv_seq_no' value='<%=late_pay_hlpl_inv_seq_no%>'>
			<input type='hidden' name='financial_year' value='<%=late_pay_financial_year%>'>
			<input type='hidden' name='financial_yr_hid' value='<%=Financial_year%>'>
			<input type='hidden' name='new_inv_seq_no' value='<%=late_pay_new_inv_seq_no%>'>
			<input type='hidden' name='contract_type' value='<%=late_pay_contract_type%>'>
			<input type='hidden' name='inv_date' value='<%=late_pay_inv_dt%>'>
			<input type='hidden' name='tax_struct_cd' value='<%=nTax_struct_cd%>'>
			<input type='hidden' name='nNew_inv_seq_no' value='<%=nNew_inv_seq_no%>'>
			<input type='hidden' name='nHlpl_inv_seq_no' value='<%=nInvoice_seq_no_actual%>'>
			<input type='hidden' name='nCust_inv_seq_no' value='<%=nCust_inv_seq_no%>'>
			<input type='hidden' name='month' value='<%=month%>'>
			<input type='hidden' name='year' value='<%=year%>'>
			<input type='hidden' name='diff_days' value='<%=differ_days%>'>
			<input type='hidden' name='write_permission' value='<%=write_permission%>'>
			<input type='hidden' name='approve_permission' value='<%=approve_permission%>'>
			<input type='hidden' name='delete_permission' value='<%=delete_permission%>'>
			<input type='hidden' name='print_permission' value='<%=print_permission%>'>
			<input type='hidden' name='check_permission' value='<%=check_permission%>'>
			<input type='hidden' name='authorize_permission' value='<%//=authorize_permission%>'>
			<input type='hidden' name='activity' value='<%=activity%>'>
			<input type='hidden' name='form_id' value='<%=form_id%>'>
			<input type='hidden' name='form_nm' value='<%=form_nm%>'>
		</form>
	</div>
</div>		
			
<script>
function comparePayDate(obj,pay_dt) {
	var val = compareDate(obj.value,pay_dt);
	if(val=='2') {
		alert('Invoice Date Should Be Greater Than Payment Received Date '+pay_dt);
		obj.value = '';
		obj.focus();
	} else {
		refreshPage('I');
	}
}
function calculateDiscount(obj){
// 	alert("no_days--"+document.forms[0].no_days.value+"----"+obj.value);
	var no_days=parseFloat(document.forms[0].no_days.value);
	var disc_days=parseFloat(obj.value);
	var diff_days="";
	if(disc_days<=no_days && disc_days>=0){
		if(disc_days==no_days){
			alert("No need to Generate Invoice \n Please enter discount days less than delay payment days");
			obj.value='0';
		}else{
			diff_days=no_days - disc_days;
			//alert("--diff fdays--"+diff_days);
			document.forms[0].diff_days.value=diff_days;
			refreshPage();
		}
	}else{
		document.getElementById('intrDays').innerHTML = no_days;
		document.forms[0].diff_days.value=no_days;
		if(disc_days<0){
			alert("You cannot enter discount days in negative");
			obj.value='0';
		}else{
			alert("You cannot enter discount days greater than delay payment days");
			obj.value='0';
		}
		refreshPage();
	}
}
function doSubmit() {
	var invoice_date = document.forms[0].invoice_date.value;
	var cal_int = document.forms[0].int_rate.value;
	var contact_per=document.forms[0].contact_person.value;
	
	var msg = ''; var flag = true;
	if(invoice_date=='') {
		msg += 'Please Enter Invoice Date!!!\n';
		flag = false;
	}
	if(cal_int=='' || parseFloat(cal_int)==0) {
		msg += 'Please Enter Proper Interest Percentage!!!\n';
		flag = false;
	}
	if(contact_per=='' || contact_per==0) {
		msg += 'Please Select contact Person!!!\n';
		flag = false;
	}
	if(flag==true) {
		var c = confirm("Do You Want To Submit?");
		if(c) {
			var no_days=document.forms[0].no_days.value;
			var disc_days=document.forms[0].disc_days.value;
			var diff_days=parseFloat(no_days)-parseFloat(disc_days);
			document.forms[0].diff_days.value=diff_days;
			//alert("--diff_days--"+diff_days);
			document.forms[0].submitData.disabled = true;
			document.forms[0].submit();
		}
	} else {
		alert("Please Check Following Fields: \n"+msg);
	}
}
function calculateData(obj) {
	if(obj.value=='' || parseFloat(obj.value)==0) {
		alert("Enter Proper Interest Percentage!!!");
	} else {
		var per = obj.value;
		refreshPage();
	}
}
function refreshPage(val) {
	var invoice_date = document.forms[0].invoice_date.value;
	var flag = true;
	var msg = '';
	if(val=='I') {
		if(invoice_date=='') {
			msg += "Please Enter Proper Invoice Date!!!";
			flag = false;
		}
	}
	if(flag==true) {
		
		var contact_person = document.forms[0].contact_person.value;
		var per = document.forms[0].int_rate.value;
		var diff_days=document.forms[0].diff_days.value;
		var disc_days=document.forms[0].disc_days.value;
		
		var url = "frm_late_pay_invoice_dtl.jsp?contactPerson="+contact_person+"&invoice_date="+invoice_date
		+"&refreshFlag=Y&int_rate="+per+"&differ_days="+diff_days+"&disc_days="+disc_days+"&late_pay_new_inv_seq_no="+'<%=late_pay_new_inv_seq_no%>'
		+"&late_pay_inv_dt="+'<%=late_pay_inv_dt%>'+"&late_pay_customer_cd="+'<%=late_pay_customer_cd%>'
		+"&late_pay_financial_year="+'<%=late_pay_financial_year%>'+"&late_pay_hlpl_inv_seq_no="+'<%=late_pay_hlpl_inv_seq_no%>'
		+"&late_pay_contract_type="+'<%=late_pay_contract_type%>'+"&late_pay_customer_abbr="+'<%=late_pay_customer_abbr%>'
		+"&activity="+'<%=activity%>'+"&form_id="+'<%=form_id%>'+"&form_nm="+'<%=form_nm%>'
		+"&write_permission="+'<%=write_permission%>'+"&print_permission="+'<%=print_permission%>'
		+"&approve_permission="+'<%=approve_permission%>'+"&authorize_permission="+'<%//=authorize_permission%>'
		+"&delete_permission="+'<%=delete_permission%>'+"&check_permission="+'<%=check_permission%>'
		+"&new_inv_seq_no="+'<%=nNew_inv_seq_no%>'+"&hlpl_inv_seq_no="+'<%=nInvoice_seq_no_actual%>'
		+"&invoice_dt="+invoice_date+"&month="+'<%=month%>'+"&year="+'<%=year%>'
		+"&act_cont_type="+'<%=act_cont_type%>';
		location.replace(url);
	} else {
		alert(msg);
	}
}
function addCommas(nStr)
{
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	if(x.length==1) {
		x2 = '.00'; 
	} else if(x.length>1) {
		x2 = '.'+x[1];
	} else {
		x2 = '';
	}
	//x2 = x.length > 1 ? '.' + x[1] : '';
	var t = '';var counter = 0;
	for(var j=(x1.length-1);j>=0;j--) {
		if(j==x1.length)
			t = x1[j];
		else {
			if(counter%3==0 && counter!=0) {
				t = x1[j] +','+ t;
			} else {
				t = x1[j] + t;
			}
		}
		counter = counter+1;
	}
	var val = t + x2;
	return val;
}

function doClose(msg,month,year)
{
	window.opener.refreshPageFromChild(msg,month,year);
	window.close();
}
</script>

</body>
</html>	
