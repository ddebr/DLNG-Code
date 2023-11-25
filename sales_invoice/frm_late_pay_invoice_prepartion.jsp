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
<script type="text/javascript">
function refreshPage(){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	var year = document.forms[0].year.value;
 	var month = document.forms[0].month.value;
	 
	 if(year=='0' || month=='0') {
		 alert('Please Select Month/Year Proper!!!');
	 } else {
		var url=modUrl+"?year="+year+"&month="+month+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl;

	 	location.replace(url);
	 }
}
function refreshPageFromChild(msg,month,year) {
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value; 
	
	var url=modUrl+"?year="+year+"&month="+month+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&msg="+msg;

	location.replace(url);
}

</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_LatePayment" id="latePay" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
util.init();
util.init();
String curr_year = util.getGet_yr();	
String sysdate = util.getGen_dt();
String CurYr=sysdate.substring(6,10);
String CurMth=sysdate.substring(3,5); 

String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String error_msg=request.getParameter("error_msg")==null?"":request.getParameter("error_msg");

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
// System.out.println("check_permission----"+check_permission);	
String month1=request.getParameter("month")==null?CurMth:request.getParameter("month");
String month = request.getParameter("month")==null?CurMth:request.getParameter("month");
String year = request.getParameter("year")==null?CurYr:request.getParameter("year");
String tempcompnm = (String)session.getAttribute("tempcompnm")==null?"":(String)session.getAttribute("tempcompnm");

String pdf_flag = request.getParameter("pdf_flag")==null?"N":request.getParameter("pdf_flag");
String nhlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
String ninvoice_dt = request.getParameter("invoice_dt")==null?"":request.getParameter("invoice_dt");
String ninvoice_title = request.getParameter("invoice_title")==null?"ORIGINAL":request.getParameter("invoice_title");
String phlpl_inv_seq_no = request.getParameter("late_pay_hlpl_inv_seq_no")==null?"":request.getParameter("late_pay_hlpl_inv_seq_no");
String pcustomer_cd = request.getParameter("late_pay_customer_cd")==null?"":request.getParameter("late_pay_customer_cd");
String pcontract_type = request.getParameter("late_pay_contract_type")==null?"":request.getParameter("late_pay_contract_type");
String pcustomer_abbr = request.getParameter("late_pay_customer_abbr")==null?"":request.getParameter("late_pay_customer_abbr");
String pfinancial_year = request.getParameter("late_pay_financial_year")==null?"":request.getParameter("late_pay_financial_year");
// System.out.println("pfinancial_year---"+pfinancial_year);
String pnew_inv_seq_no = request.getParameter("late_pay_new_inv_seq_no")==null?"":request.getParameter("late_pay_new_inv_seq_no");
String pinv_dt=request.getParameter("late_pay_inv_dt")==null?"":request.getParameter("late_pay_inv_dt");
String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");

String form_nm = "Late Payment Invoice";
HttpServletRequest req = request;
latePay.setCallFlag("LatePayInvoicePrepare");
latePay.setLate_pay_month(month1);
latePay.setLate_pay_year(year);
latePay.setTempcompname(tempcompnm);//RG20190425
latePay.init();

Vector monthss=latePay.getMonths();
Vector month_id=latePay.getMonths_id();
String StartDt=latePay.getStartDt(); 
String EndDt=latePay.getEndDt();

Vector late_pay_new_inv_seq_no = latePay.getLate_pay_new_inv_seq_no();
Vector late_pay_hlpl_inv_seq_no = latePay.getLate_pay_hlpl_inv_seq_no();
Vector late_pay_inv_dt = latePay.getLate_pay_inv_dt();
Vector late_pay_due_dt = latePay.getLate_pay_due_dt();
Vector late_pay_recv_dt = latePay.getLate_pay_recv_dt();
Vector late_pay_recv_amt = latePay.getLate_pay_recv_amt();
Vector late_pay_net_amt = latePay.getLate_pay_net_amt();
Vector late_pay_payable_amt = latePay.getLate_pay_payable_amt();
Vector late_pay_int_cal_percentage = latePay.getLate_pay_int_cal_percentage();
Vector late_pay_customer_cd = latePay.getLate_pay_customer_cd();
Vector late_pay_contract_type = latePay.getLate_pay_contract_type();
Vector late_pay_customer_abbr = latePay.getLate_pay_customer_abbr();
Vector late_pay_financial_year = latePay.getLate_pay_financial_year();
Vector late_pay_no_days = latePay.getLate_pay_no_days();
Vector late_pay_plant_seq_no = latePay.getLate_pay_plant_seq_no();
Vector late_pay_customer_nm = latePay.getLate_pay_customer_nm();

Vector Mhlpl_inv_seq_no = latePay.getMhlpl_inv_seq_no();
Vector Mnew_inv_seq_no = latePay.getMnew_inv_seq_no();
Vector Minvoice_dt = latePay.getMinvoice_dt();
Vector Mcontract_type = latePay.getMcontract_type();
Vector Mchecked_flag = latePay.getMchecked_flag();
Vector Mapproved_flag = latePay.getMapproved_flag();
Vector Mpdf_inv_dtl = latePay.getMpdf_inv_dtl();
Vector Mpdf_lock_flag = latePay.getMpdf_lock_flag();
Vector Mpdf_view = latePay.getMpdf_view();
Vector Mpdf_file_nm = latePay.getMpdf_file_nm();
//RG20200320 added for dgs mail part
Vector Mpdf_view_signpdf=latePay.getMpdf_view_signpdf();
Vector Mpdf_view_mail_sent=latePay.getMpdf_view_mail_sent();
Vector Mcontact_person_cd= latePay.getMcontact_person_cd();
Vector Vact_cont_type = latePay.getVact_cont_type();

//
Vector irn_flag= latePay.getIrn_flag();
String pdf_path = "";

if(pdf_flag.equals("Y")) {
	latePay.setCallFlag("Prepare_Late_Payment_Invoice");
	latePay.setpHlpl_inv_seq_no(phlpl_inv_seq_no);
	latePay.setpContract_type(pcontract_type);
	latePay.setpCustomer_cd(pcustomer_cd);
	latePay.setpFinancial_year(pfinancial_year);
	latePay.setpInvoice_date(pinv_dt);
	latePay.setpNew_inv_seq_no(pnew_inv_seq_no);
	latePay.setnInvoice_date(ninvoice_dt);
	latePay.setActivity("V");
	latePay.setmNew_inv_seq_no(new_inv_seq_no);
	latePay.setmHlpl_inv_seq_no(nhlpl_inv_seq_no);
	latePay.setmInvoice_dt(ninvoice_dt);
	latePay.setPdf_flag("Y");
	latePay.setRequest(request);
	latePay.setnInvoice_title(ninvoice_title);
	latePay.init();
	
	pdf_path = latePay.getPdf_path();
	String url_start = latePay.getUrl_start().trim();
	
	String contPath = request.getContextPath()== null?"":""+request.getContextPath();
	if(contPath.trim().length()>1)
	{
		contPath = contPath.trim().substring(1);
	}
	else
	{
		contPath = contPath.trim();
	}
	if(contPath==null || contPath.equals("") || pdf_path==null || pdf_path.equals(""))
	{
	}
	else
	{
		pdf_path = pdf_path.substring(pdf_path.indexOf("//"));
	 	pdf_path = url_start+"/pdf_reports/sales_invoice/late_pay_invoice"+pdf_path;
	}
}
%>
<body <%if(!pdf_path.equals("")) { %>onload="window.open('<%=pdf_path%>');refreshPage();"<% } %>>
<div class="tab-content">
	<div class="tab-pane active" id="invoicing">
		<form method="post"  action="">
			<input type="hidden" name="modCd" value="<%=modCd%>">
			<input type="hidden" name="formId" value="<%=formId%>">
			<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
			<input type="hidden" name="modUrl" value="<%=modUrl%>" >
			
			<input type="hidden" name="write_permission" value="<%=write_permission%>" >
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>" >
			<input type="hidden" name="print_permission" value="<%=print_permission%>" >
			<input type="hidden" name="approve_permission" value="<%=approve_permission%>" >
			<input type="hidden" name="audit_permission" value="<%=audit_permission%>" >
			<input type="hidden" name="check_permission" value="<%=check_permission%>" >
		
			<div class="box mb-0">
				<div class="box-header with-border">
					<div id="col-new">
						<%if(msg.length()>5) { %>
							<div class="box-body table-responsive no-padding">
								<table class="table  table-bordered">
									<thead>   
										<tr class="info">
											<th class="text-center"  <%if(msg.contains("Error:")) { %> style="color: red;" <%}else{ %>style="color: blue;" <%} %>><%=msg %></th>
										</tr>
									</thead>
								</table>
						</div>
						<%} %>
						<div class="box-header with-border" >
							<div class="form-group col-md-2">
						 		<label for="">Month</label>
						 		<select class="form-control" name="month" id="month"  >
									<%for(int i=0;i<monthss.size();i++){ %>
										<option value="<%=month_id.elementAt(i)%>"><%=monthss.elementAt(i) %></option>
									<%} %>
								</select>
					 		</div>
							<div class="form-group col-md-2">
						 		<label for="">Year</label>
								<select class="form-control" name="year" id="year" >	
									<option value="0">--Select--</option>				
								  	<%for(int i=(Integer.parseInt(curr_year));i>=(Integer.parseInt(curr_year)-4);i--) {%>
								  		<option value="<%=i%>"><%=i%></option> 
									<%}%>					  
								</select>
							</div>
							
							<div class="form-group col-md-2" title="View Invoice List">
								<label for="">&nbsp;</label>
							 	  <div class='input-group'>
							 	  	<button type="button"  class="btn btn-primary"  name="btn"  value="View List" onclick="refreshPage();" >View List</button>
							     </div>
							 </div>				
					 	</div>
					 	
						 	<div class="box-body table-responsive no-padding">
								<table class="table  table-bordered" style="overflow-x:scroll"  >
								<%if(late_pay_new_inv_seq_no.size()>0) { %>	
								  <thead> 
										<tr>
											<th colspan="11" class="text-center info">
												<font color="">List of SALES/LOA/SERVICE Invoice(s) Generated During the Period <%=StartDt %> to <%=EndDt %></font>
											</th>
										</tr>
										<tr class="info">
											<th  class="text-center">PARTY</th>
											<th  class="text-center">ORI INVOICE<br>NO</th>
											<th  class="text-center">ORI INVOICE<br>DT</th>
											<th  class="text-center">ORI DUE DT</th>
											<th  class="text-center">PAYMENT<br>RECEIVED<br>DATE</th>
											<th  class="text-center">LATE PAYMENT<br>INVOICE NO</th>
											<th  class="text-center">ACTIVITY-I</th>
											<th  class="text-center">ACTIVITY-II</th>
											<th  class="text-center">ACTIVITY-III</th>
											<th  class="text-center">ACTIVITY-IV</th>
											<th  class="text-center">ACTIVITY-V</th>
										</tr>
									</thead>
									<tbody>
									<% for(int i=0;i<late_pay_new_inv_seq_no.size();i++) { %>
										<tr style="background-color: #E0EEE0;border: 1px solid white;border-right: 1px solid white;line-height: 1.5">
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center">
											&nbsp;<%=late_pay_customer_abbr.elementAt(i)%></td>
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center"><%=late_pay_new_inv_seq_no.elementAt(i)%></td>
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center"><%=late_pay_inv_dt.elementAt(i)%></td>
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center"><%=late_pay_due_dt.elementAt(i) %></td>
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center"><%=late_pay_recv_dt.elementAt(i) %></td>
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center"><%=Mnew_inv_seq_no.elementAt(i)%></td>
											
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center">
			
												<%if((""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("") || (""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("0")) {	%>
															<input type="button" class="btn btn-info  btn-sm" value="Prepare" style="padding: 1px 14px;font-size: 13px;font-weight: bolder;"
															<%if(write_permission.trim().equalsIgnoreCase("N")){%> disabled="disabled" title="You Don't Have Permission to Prepare late payment Invoice" <%}else{%>
																title="Click to Prepare late payment Invoice" onclick="openprepareinvoice('<%=late_pay_new_inv_seq_no.elementAt(i)%>','<%=late_pay_inv_dt.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=late_pay_financial_year.elementAt(i)%>','<%=late_pay_hlpl_inv_seq_no.elementAt(i)%>','<%=late_pay_contract_type.elementAt(i)%>','<%=late_pay_customer_abbr.elementAt(i)%>','P','','','','<%=Vact_cont_type.elementAt(i)%>')";	
															<%}%>>
												<%} else{ %>	
															<input class="btn btn-primary btn-sm" type="button" style="font-weight: bold;padding: 3px 6px;" value="View" id="mod_view-<%=i%>" 
															<%if(write_permission.trim().equalsIgnoreCase("Y") && ((""+Mpdf_lock_flag.elementAt(i)).equalsIgnoreCase("N")  && ((""+Mchecked_flag.elementAt(i)).trim().equalsIgnoreCase("") || (""+Mchecked_flag.elementAt(i)).trim().equalsIgnoreCase("N")))){	%>
														 		onclick="if(this.value=='Modify'){this.value='View'}else{this.value='Modify'};"
														 	<%}else{ %>disabled<%} %>/> 
														 	<img width="15" height="15" src="../images/Down_Arrow.png" style="cursor: pointer;" <% if(write_permission.trim().equalsIgnoreCase("Y") && (""+Mpdf_lock_flag.elementAt(i)).equalsIgnoreCase("N")){%>
																		onclick="if(document.getElementById('mod_view-<%=i%>').value=='Modify'){
													  			 openprepareinvoice('<%=late_pay_new_inv_seq_no.elementAt(i)%>','<%=late_pay_inv_dt.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=late_pay_financial_year.elementAt(i)%>','<%=late_pay_hlpl_inv_seq_no.elementAt(i)%>','<%=late_pay_contract_type.elementAt(i)%>','<%=late_pay_customer_abbr.elementAt(i)%>','M','<%=Mnew_inv_seq_no.elementAt(i)%>','<%=Mhlpl_inv_seq_no.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','<%=Vact_cont_type.elementAt(i)%>');}
													  			 		else{
													  			 openInvoiceViewPage('<%=late_pay_new_inv_seq_no.elementAt(i)%>','<%=late_pay_inv_dt.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=late_pay_financial_year.elementAt(i)%>','<%=late_pay_hlpl_inv_seq_no.elementAt(i)%>','<%=late_pay_contract_type.elementAt(i)%>','<%=late_pay_customer_abbr.elementAt(i)%>','V','<%=Mnew_inv_seq_no.elementAt(i)%>','<%=Mhlpl_inv_seq_no.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','View','<%=Mchecked_flag.elementAt(i)%>','<%=Mapproved_flag.elementAt(i)%>','<%=Vact_cont_type.elementAt(i)%>');	
													  			 			}"
													  			 <%}else{%> disabled <%}%>>
												<% } %>			 
											</td>
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center">
												<% if((""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("") || (""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("0")) {	%>
												<%	} else {	%>
													<%	
													if(check_permission.trim().equalsIgnoreCase("Y") && ((""+Mapproved_flag.elementAt(i)).trim().equalsIgnoreCase("") || (""+Mapproved_flag.elementAt(i)).trim().equalsIgnoreCase("N"))) {	%>
													<input type="button" class="btn btn-warning  btn-sm"
														<%if((""+Mchecked_flag.elementAt(i)).trim().equalsIgnoreCase("Y")){ %>
															value="Checked"
														<%}else{ %>
															value="Check"
														<%} %>
														style="padding: 1px 14px; font-size: 13px;font-weight: bolder;" 
														onclick="openInvoiceViewPage('<%=late_pay_new_inv_seq_no.elementAt(i)%>','<%=late_pay_inv_dt.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=late_pay_financial_year.elementAt(i)%>','<%=late_pay_hlpl_inv_seq_no.elementAt(i)%>','<%=late_pay_contract_type.elementAt(i)%>','<%=late_pay_customer_abbr.elementAt(i)%>','V','<%=Mnew_inv_seq_no.elementAt(i)%>','<%=Mhlpl_inv_seq_no.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','Check','<%=Mchecked_flag.elementAt(i)%>','<%=Mapproved_flag.elementAt(i)%>','<%=Vact_cont_type.elementAt(i)%>');	">
													<%	} else {	%>
													 		<input type="button" class="btn btn-info btn-sm" value="Checked" style="padding: 1px 14px; font-size: 13px;font-weight: bolder;" disabled> 
													<%	}	%>			
												<%	}	%>
											</td>
											
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center">
												<%
												if((""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("") || (""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("0")) {	%>
					<!-- 									<input type="button" class="btn btn-default  btn-sm" value="Approve" style="padding: 1px 14px; font-size: 13px;font-weight: bolder;" disabled > -->
												<%	} else {	 %>
														<% if(approve_permission.trim().equalsIgnoreCase("Y")  && (""+Mchecked_flag.elementAt(i)).trim().equalsIgnoreCase("Y"))  {	 %>
															<input type="button"  style="padding: 1px 14px; font-size: 13px;font-weight: bolder;"
																<%if((""+Mapproved_flag.elementAt(i)).equalsIgnoreCase("Y")){  %>
															 			value="Approved" class="btn btn-success btn-sm" disabled
															 		<%}else{ %>
															 			class="btn btn-warning  btn-sm" value="Approve" style="font-size:15;font-weight:bold;" onclick="openInvoiceViewPage('<%=late_pay_new_inv_seq_no.elementAt(i)%>','<%=late_pay_inv_dt.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=late_pay_financial_year.elementAt(i)%>','<%=late_pay_hlpl_inv_seq_no.elementAt(i)%>','<%=late_pay_contract_type.elementAt(i)%>','<%=late_pay_customer_abbr.elementAt(i)%>','V','<%=Mnew_inv_seq_no.elementAt(i)%>','<%=Mhlpl_inv_seq_no.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','Approve','<%=Mchecked_flag.elementAt(i)%>','<%=Mapproved_flag.elementAt(i)%>','<%=Vact_cont_type.elementAt(i)%>');"
															 		<%} %>>
															<%	}else{
															if(!approve_permission.trim().equalsIgnoreCase("Y")) {%>
															<input type="button" class="btn btn-default  btn-sm" value="Approve" style="padding: 1px 14px; font-size: 13px;font-weight: bolder;" disabled >	
												<%}}}	%>
											</td>
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center">
												<% 
												String s1=session.getAttribute("late_payment_invoice_pdf_path").toString();
												String p2="pdf_reports/sales_invoice/late_pay_invoice";
												String temp_path="/unsigned_pdf/signed";
												String path_sign_pdf=request.getRealPath(temp_path);
												
												Vector invoice_path=new Vector();
												//Vector invoice_path_signpdf=new Vector();
												Vector invoice_path_mail_sent=new Vector();
												
										 		String invoice[]=Mpdf_view.elementAt(i).toString().replace("[", "").replace("]", "").split(",");
										 		//String invoice_signpdf[]=Mpdf_view_signpdf.elementAt(i).toString().replace("[", "").replace("]", "").split(",");
										 		String invoice_mail_sent[]=Mpdf_view_mail_sent.elementAt(i).toString().replace("[", "").replace("]", "").split(",");
										 		
										 		for(int p1=0;p1<invoice.length;p1++){
										 			invoice_path.add(invoice[p1].trim());
										 			//invoice_path_signpdf.add(invoice_signpdf[p1].trim());
										 			invoice_path_mail_sent.add(invoice_mail_sent[p1].trim());
										 		}
// 										 		System.out.println("==="+invoice_path);
										 		int o_cnt=0,d_cnt=0,t_cnt=0,c_cnt=0,osign_cnt=0,dsign_cnt=0,tsign_cnt=0;
										 		int osign_mail_cnt=0,dsign_mail_cnt=0,tsign_mail_cnt=0;
										 		String o_filename="",d_filename="",t_filename="",c_filename="",osign_filename="",dsign_filename="",tsign_filename="";
										 		
												for(int l=0;l<invoice_path.size();l++){
													String inv_ty="";
													if(!invoice_path.elementAt(l).toString().equalsIgnoreCase("")){
														inv_ty=invoice_path.elementAt(l).toString().substring(invoice_path.elementAt(l).toString().length()-1);
													}
													
													String inv_mail_flag = "";
													if (!invoice_path.elementAt(l).toString().equalsIgnoreCase("")) {
														inv_mail_flag = invoice_path_mail_sent.elementAt(l).toString().trim();
													}
													
// 													System.out.println(""+inv_ty);
													if(inv_ty.equalsIgnoreCase("O") ){
														o_cnt++;
														o_filename=invoice_path.elementAt(l).toString();
														
														//RG20191121 changed for fetching sign pdf
														String file_temp=path_sign_pdf+"/LATEPAY_"+invoice_path.elementAt(l).toString()+".pdf";
// 														System.out.println("file_temp==="+file_temp);
														File pdf_sign_file=new File(file_temp);
														if(pdf_sign_file.exists()){
															osign_cnt++;
															osign_filename="LATEPAY_"+invoice_path.elementAt(l).toString()+"";
														}
														if(inv_mail_flag.equalsIgnoreCase("Y")){
															osign_mail_cnt++;											
														}
													}
													if(inv_ty.equalsIgnoreCase("D")){
														d_cnt++;
														d_filename=invoice_path.elementAt(l).toString();
														
														//RG20191121 changed for fetching sign pdf
														String file_temp=path_sign_pdf+"/LATEPAY_"+invoice_path.elementAt(l).toString()+".pdf";
														File pdf_sign_file=new File(file_temp);
														if(pdf_sign_file.exists()){
															dsign_cnt++;
															dsign_filename="LATEPAY_"+invoice_path.elementAt(l).toString()+"";
														}
														if(inv_mail_flag.equalsIgnoreCase("Y")){
															dsign_mail_cnt++;											
														}
													}
													if(inv_ty.equalsIgnoreCase("T")){
														t_cnt++;
														t_filename=invoice_path.elementAt(l).toString();
														
														//RG20191121 changed for fetching sign pdf
														String file_temp=path_sign_pdf+"/LATEPAY_"+invoice_path.elementAt(l).toString()+".pdf";
														File pdf_sign_file=new File(file_temp);
														if(pdf_sign_file.exists()){
															tsign_cnt++;
															tsign_filename="LATEPAY_"+invoice_path.elementAt(l).toString()+"";
														}
														if(inv_mail_flag.equalsIgnoreCase("Y")){
															tsign_mail_cnt++;											
														}
													}
												}
// 												System.out.println("osign_cnt---"+osign_cnt);
												String abs_path=request.getContextPath(); 
												if((""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("") || (""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("0")) {	%>
												<%	} else {%>
														<!-- for original -->
														 	<%if(o_cnt>0){
														 		if(osign_cnt>0){%>
														 			<img title="Original-View Signed PDF" id="InvViewPdfO-<%=i%>" name="InvViewPdfO"  height="20" width="20" src="../images/PDF_O1.png" 
															 		style="cursor: pointer; visibility: visible;"
															 			onmouseover="this.src='../images/PDF_O1_DIS.png'"
															 			onmouseout="this.src='../images/PDF_O1.png'"
															 			onclick="openInvoiceInPdf_View('<%=abs_path %>/<%=temp_path%>/<%=osign_filename+".pdf"%>','InvViewPdfO-<%=i%>');">
														 		<%}else{ %>
															 			<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfO" width="20" height="20"
															 			src="../images/PDF_O1_DIS.png" title="Has been Generated..!!" >
														 			<%} %>
														 		<%}else{
														 			if((""+Mapproved_flag.elementAt(i)).equalsIgnoreCase("N")){  %>
														 			<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfO" width="20" height="20" src="../images/PDF_O1_DIS.png"> 
														 			<%}else{ 
														 			if(!(""+Mchecked_flag.elementAt(i)).equals("Y") || !(""+Mapproved_flag.elementAt(i)).equals("Y")) { %>
														 			<% } else { %>
														 				<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfO" width="20" height="20"
															 			 style="cursor: pointer;" title="Original-PrintPDF" src="../images/PDF_O1.png" onmouseover="this.src='../images/PDF_O1_DIS.png'"
															 			onmouseout="this.src='../images/PDF_O1.png'" 
														 				onclick="openInvoiceInPdf('<%=late_pay_new_inv_seq_no.elementAt(i)%>','<%=late_pay_inv_dt.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=late_pay_financial_year.elementAt(i)%>','<%=late_pay_hlpl_inv_seq_no.elementAt(i)%>','<%=late_pay_contract_type.elementAt(i)%>','<%=late_pay_customer_abbr.elementAt(i)%>','V','<%=Mnew_inv_seq_no.elementAt(i)%>','<%=Mhlpl_inv_seq_no.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','PDF','<%=Mchecked_flag.elementAt(i)%>','<%=Mapproved_flag.elementAt(i)%>','ORIGINAL','<%=tempcompnm %>','<%=irn_flag.elementAt(i) %>');">
														 		 <%}} }%> 
															<!-- for original end-->
															<!-- for duplicate -->
														
														 	<%if(d_cnt>0){
														 		if(dsign_cnt>0){%>
														 			<img title="Duplicate-View Signed PDF" id="InvViewPdfD-<%=i%>" name="InvViewPdfD" height="20" width="20" src="../images/PDF_D1.png" 
															 			style="cursor: pointer; visibility: visible;" 
															 			onmouseover="this.src='../images/PDF_D1_DIS.png'"
															 			onmouseout="this.src='../images/PDF_D1.png'"
															 			onclick="openInvoiceInPdf_View('<%=abs_path %>/<%=temp_path%>/<%=dsign_filename+".pdf"%>','InvViewPdfD-<%=i%>');">
														 		<%}else{ %>
															 		<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfD" width="20" height="20"
															 		src="../images/PDF_D1_DIS.png" title="Has been Generated..!!" >
														 	<%}}else{  
														 		if((""+Mapproved_flag.elementAt(i)).toString().equalsIgnoreCase("N")){  %>
														 			<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfD" width="20" height="20"
														 			src="../images/PDF_D1_DIS.png">
														 		<%}else{ if(!(""+Mchecked_flag.elementAt(i)).equals("Y")  || !(""+Mapproved_flag.elementAt(i)).equals("Y")) { %>
														 			<% } else { %>	
														 			<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfD" width="20" height="20"
														 			 style="cursor: pointer;" title="Duplicate-PrintPDF"  src="../images/PDF_D1.png"   onmouseover="this.src='../images/PDF_D1_DIS.png'"
														 			onmouseout="this.src='../images/PDF_D1.png'"
											 						onclick="openInvoiceInPdf('<%=late_pay_new_inv_seq_no.elementAt(i)%>','<%=late_pay_inv_dt.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=late_pay_financial_year.elementAt(i)%>','<%=late_pay_hlpl_inv_seq_no.elementAt(i)%>','<%=late_pay_contract_type.elementAt(i)%>','<%=late_pay_customer_abbr.elementAt(i)%>','V','<%=Mnew_inv_seq_no.elementAt(i)%>','<%=Mhlpl_inv_seq_no.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','PDF','<%=Mchecked_flag.elementAt(i)%>','<%=Mapproved_flag.elementAt(i)%>','DUPLICATE','<%=tempcompnm %>','<%=irn_flag.elementAt(i) %>');">
														 	<%}} }%>
														
														<!-- for duplicate end -->
														<!-- for triplicate -->
														
														 	<%if(t_cnt>0){
														 		if(tsign_cnt>0){%>
														 			<img title="Triplicate-View Signed PDF" id="InvViewPdfT-<%=i%>" name="InvViewPdfT" height="20" width="20" src="../images/PDF_T1.png"
															 			style="cursor: pointer; visibility: visible;" 
															 			onmouseover="this.src='../images/PDF_T1_DIS.png'"
															 			onmouseout="this.src='../images/PDF_T1.png'"
															 			onclick="openInvoiceInPdf_View('<%=abs_path %>/<%=temp_path%>/<%=tsign_filename+".pdf"%>','InvViewPdfT-<%=i%>');">
														 		<%}else{ %>
															 		<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfT" width="20" height="20"
															 		src="../images/PDF_T1_DIS.png" title="Has been Generated..!!" >
														 	<%}}else{ 
														 		if((""+Mapproved_flag.elementAt(i)).toString().equalsIgnoreCase("N")){  %>
														 			<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfT" width="20" height="20"
														 			src="../images/PDF_T1_DIS.png">
														 		<%}else{ if(!(""+Mchecked_flag.elementAt(i)).equals("Y") || !(""+Mapproved_flag.elementAt(i)).equals("Y")) { %>
														 			<% } else { %>
														 			<img style="margin-right: 3px;cursor: pointer;" name="InvPriPdfT" width="20" height="20"
														 		 	style="cursor: pointer;" title="Triplicate-PrintPDF" src="../images/PDF_T1.png"  onmouseover="this.src='../images/PDF_T1_DIS.png'"
														 			onmouseout="this.src='../images/PDF_T1.png'"
														 			onclick="openInvoiceInPdf('<%=late_pay_new_inv_seq_no.elementAt(i)%>','<%=late_pay_inv_dt.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=late_pay_financial_year.elementAt(i)%>','<%=late_pay_hlpl_inv_seq_no.elementAt(i)%>','<%=late_pay_contract_type.elementAt(i)%>','<%=late_pay_customer_abbr.elementAt(i)%>','V','<%=Mnew_inv_seq_no.elementAt(i)%>','<%=Mhlpl_inv_seq_no.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','PDF','<%=Mchecked_flag.elementAt(i)%>','<%=Mapproved_flag.elementAt(i)%>','TRIPLICATE','<%=tempcompnm %>','<%=irn_flag.elementAt(i) %>');">
														 	 <%}}} %> 
														 	 <!-- for triplicate end -->
												<%}%>
											</td>
											<td style="border: 1px solid white;border-right: 1px solid white;" align="center">
												<% if((""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("") || (""+Mhlpl_inv_seq_no.elementAt(i)).trim().equals("0")) {	%>
													&nbsp;
												<%	} else {%> 
															 
															 	<%if(o_cnt>0){
															 		if(osign_cnt>0){%>
															 			<img <%if(osign_mail_cnt>0){ %> title="Original-Mail Sent"<%}else{ %>title="Original-SendMail" <%} %> id="InvSendmailO<%=i%>" name="InvSendmailO"  height="25" width="25" src="../images/icon_email.jpg" onclick="Sending_mail('O','<%=o_filename%>','<%=Mnew_inv_seq_no.elementAt(i)%>','M','<%=late_pay_customer_nm.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','<%=late_pay_plant_seq_no.elementAt(i)%>','<%=Mcontact_person_cd.elementAt(i)%>');">
															 		<%}else{%>
															 		<img title="Original-ViewPDF" id="InvViewPdfO-<%=i%>" name="InvViewPdfO"  height="20" width="20" src="../images/PDF_O1.png"
															 			style="cursor: pointer; visibility: visible;"
															 			onmouseover="this.src='../images/PDF_O1_DIS.png'"
															 			onmouseout="this.src='../images/PDF_O1.png'"
															 			onclick="openInvoiceInPdf_View('<%=abs_path %>/<%=p2%>/<%=o_filename+".pdf"%>','InvViewPdfO-<%=i%>');">
															 	<%}}else{ %>
															 		<img title="Original-ViewPDF" id="InvViewPdfO-<%=i%>" name="InvViewPdfO"  height="20" width="20" src="../images/PDF_O1.png"
															 		 style="cursor: pointer; visibility: hidden;" >
															 	 <%} %>
																<!-- for duplicate -->
															 
															 	<%if(d_cnt>0){
															 		if(dsign_cnt>0){%>
															 			<img <%if(dsign_mail_cnt>0){ %> title="Duplicate-Mail Sent"<%}else{ %>title="Duplicate-SendMail" <%} %> id="InvSendmailD<%=i%>" name="InvSendmailD"  height="25" width="25" src="../images/icon_email.jpg" onclick="Sending_mail('D','<%=d_filename%>','<%=Mnew_inv_seq_no.elementAt(i)%>','M','<%=late_pay_customer_nm.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','<%=late_pay_plant_seq_no.elementAt(i)%>','<%=Mcontact_person_cd.elementAt(i)%>');">
															 		<%}else{ %>
																 		<img title="Duplicate-ViewPDF" id="InvViewPdfD-<%=i%>" name="InvViewPdfD" height="20" width="20" src="../images/PDF_D1.png"
																 		style="cursor: pointer; visibility: visible;" 
																 		onmouseover="this.src='../images/PDF_D1_DIS.png'"
																 		onmouseout="this.src='../images/PDF_D1.png'"
																 		onclick="openInvoiceInPdf_View('<%=abs_path %>/<%=p2%>/<%=d_filename+".pdf"%>','InvViewPdfD-<%=i%>');">
															 	<%}}else{ %>
															 		<img title="Duplicate-ViewPDF" id="InvViewPdfD-<%=i%>" name="InvViewPdfD" height="20" width="20" src="../images/PDF_D1.png"
															 		 style="cursor: pointer; visibility: hidden;">
															 	<%} %> 
															
															<!-- for Triplicate -->
															
															 	<%if(t_cnt>0){
															 		if(tsign_cnt>0){%>
															 			<img <%if(tsign_mail_cnt>0){ %> title="Triplicate-Mail Sent"<%}else{ %>title="Triplicate-SendMail"<%} %> id="InvSendmailT<%=i%>" name="InvSendmailT"  height="25" width="25" src="../images/icon_email.jpg" onclick="Sending_mail('T','<%=t_filename%>','<%=Mnew_inv_seq_no.elementAt(i)%>','M','<%=late_pay_customer_nm.elementAt(i)%>','<%=late_pay_customer_cd.elementAt(i)%>','<%=Minvoice_dt.elementAt(i)%>','<%=late_pay_plant_seq_no.elementAt(i)%>','<%=Mcontact_person_cd.elementAt(i)%>');">
															 		<%}else{ %>
															 			<img title="Triplicate-ViewPDF" id="InvViewPdfT-<%=i%>" name="InvViewPdfT" height="20" width="20" src="../images/PDF_T1.png"
															 			style="cursor: pointer; visibility: visible;" 
															 			onmouseover="this.src='../images/PDF_T1_DIS.png'"
															 			onmouseout="this.src='../images/PDF_T1.png'"
															 			onclick="openInvoiceInPdf_View('<%=abs_path %>/<%=p2%>/<%=t_filename+".pdf"%>','InvViewPdfT-<%=i%>');">
															 	<%}}else{ %>
															 		<img title="Triplicate-ViewPDF" id="InvViewPdfT-<%=i%>" name="InvViewPdfT" height="20" width="20" src="../images/PDF_T1.png"
															 		 style="cursor: pointer; visibility: hidden;">
															 	 <%} %> 
												<%}%>
											</td>
										</tr>
										<%} %>
									</tbody>
								<%}else{ %>
								<tr  align='center'>
									<td align='center'>No Data Available For Selected Month &amp; Year!!!</td>
								</tr>
								<%} %>
							</table>
						</div>
					</div>
				</div>
			</div>
			<script>
				document.forms[0].month.value="<%=month1%>";
				document.forms[0].year.value="<%=year%>";
			</script>
		</form>
	</div>
</div>
<script type="text/javascript">
var newPrepareWindow;
function openprepareinvoice(late_pay_new_inv_seq_no,late_pay_inv_dt,late_pay_customer_cd,late_pay_financial_year,late_pay_hlpl_inv_seq_no,late_pay_contract_type,late_pay_customer_abbr,activity,new_inv_seq_no,hlpl_inv_seq_no,invoice_dt,act_cont_type) {
	var url = "frm_late_pay_invoice_dtl.jsp?new_inv_seq_no="+new_inv_seq_no+"&hlpl_inv_seq_no="+hlpl_inv_seq_no
			+"&invoice_dt="+invoice_dt+"&late_pay_new_inv_seq_no="+late_pay_new_inv_seq_no
			+"&late_pay_inv_dt="+late_pay_inv_dt+"&late_pay_customer_cd="+late_pay_customer_cd
			+"&late_pay_financial_year="+late_pay_financial_year+"&late_pay_hlpl_inv_seq_no="+late_pay_hlpl_inv_seq_no
			+"&late_pay_contract_type="+late_pay_contract_type+"&late_pay_customer_abbr="+late_pay_customer_abbr
			+"&activity="+activity+"&form_id="+'<%=formId%>'+"&form_nm="+'<%=form_nm%>'
			+"&write_permission="+'<%=write_permission%>'+"&print_permission="+'<%=print_permission%>'
			+"&approve_permission="+'<%=approve_permission%>'+"&authorize_permission="+'<%//=authorize_permission%>'
			+"&delete_permission="+'<%=delete_permission%>'+"&check_permission="+'<%=check_permission%>'
			+"&month="+'<%=month%>'+"&year="+'<%=year%>'
			+"&act_cont_type="+act_cont_type;
	if(!newPrepareWindow || newPrepareWindow.closed) {
		newPrepareWindow = window.open(url,"Invoice_Details_Late_Pay","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
	} else {
		newPrepareWindow.close();
		newPrepareWindow = window.open(url,"Invoice_Details_Late_Pay","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
	}
}
var newViewWindow;
function openInvoiceViewPage(late_pay_new_inv_seq_no,late_pay_inv_dt,late_pay_customer_cd,late_pay_financial_year,late_pay_hlpl_inv_seq_no,late_pay_contract_type,late_pay_customer_abbr,activity,new_inv_seq_no,hlpl_inv_seq_no,invoice_dt,flag,check_flag,aprv_flg,act_cont_type) {
	var url = "rpt_late_pay_view_invoice_dtl.jsp?new_inv_seq_no="+new_inv_seq_no+"&hlpl_inv_seq_no="+hlpl_inv_seq_no
		+"&invoice_dt="+invoice_dt+"&late_pay_new_inv_seq_no="+late_pay_new_inv_seq_no
		+"&flag="+flag+"&check_flag="+check_flag+"&aprv_flg="+aprv_flg
		+"&late_pay_inv_dt="+late_pay_inv_dt+"&late_pay_customer_cd="+late_pay_customer_cd
		+"&late_pay_financial_year="+late_pay_financial_year+"&late_pay_hlpl_inv_seq_no="+late_pay_hlpl_inv_seq_no
		+"&late_pay_contract_type="+late_pay_contract_type+"&late_pay_customer_abbr="+late_pay_customer_abbr
		+"&activity="+activity+"&form_id="+'<%=formId%>'+"&form_nm="+'<%=form_nm%>'
		+"&write_permission="+'<%=write_permission%>'+"&print_permission="+'<%=print_permission%>'
		+"&approve_permission="+'<%=approve_permission%>'+"&authorize_permission="+'<%//=authorize_permission%>'
		+"&delete_permission="+'<%=delete_permission%>'+"&check_permission="+'<%=check_permission%>'
		+"&month="+'<%=month%>'+"&year="+'<%=year%>'
		+"&act_cont_type="+act_cont_type;
	if(!newViewWindow || newViewWindow.closed) {
		newViewWindow = window.open(url,"Invoice_Details_Late_Pay_View","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
	} else {
		newViewWindow.close();
		newViewWindow = window.open(url,"Invoice_Details_Late_Pay_View","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
	}
}
function openInvoiceInPdf_View(pdf_path,ID){
	 window.open(pdf_path);
}
function openInvoiceInPdf(late_pay_new_inv_seq_no,late_pay_inv_dt,late_pay_customer_cd,late_pay_financial_year,late_pay_hlpl_inv_seq_no,late_pay_contract_type,late_pay_customer_abbr,activity,new_inv_seq_no,hlpl_inv_seq_no,invoice_dt,flag,check_flag,aprv_flg,invoice_title,tempcompnm,irn_flag) {
	var flg_irn="Y";
// 	alert(late_pay_contract_type);
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	if(late_pay_contract_type=='V'){
		if(irn_flag!='Y'){
			alert("IRN Number/ QR code is not available so you cannot print PDF");
			flg_irn="N";
		}
	}
	if(flg_irn=='Y'){
		
		var url = modUrl+"?invoice_title="+invoice_title+"&pdf_flag=Y&new_inv_seq_no="+new_inv_seq_no+"&tempcompname="+tempcompnm+"&hlpl_inv_seq_no="+hlpl_inv_seq_no
		+"&invoice_dt="+invoice_dt+"&late_pay_new_inv_seq_no="+late_pay_new_inv_seq_no
		+"&flag="+flag+"&check_flag="+check_flag+"&aprv_flg="+aprv_flg
		+"&late_pay_inv_dt="+late_pay_inv_dt+"&late_pay_customer_cd="+late_pay_customer_cd
		+"&late_pay_financial_year="+late_pay_financial_year+"&late_pay_hlpl_inv_seq_no="+late_pay_hlpl_inv_seq_no
		+"&late_pay_contract_type="+late_pay_contract_type+"&late_pay_customer_abbr="+late_pay_customer_abbr
		+"&activity="+activity+"&form_id="+'<%=formId%>'+"&form_nm="+'<%=form_nm%>'
		+"&write_permission="+'<%=write_permission%>'+"&print_permission="+'<%=print_permission%>'
		+"&approve_permission="+'<%=approve_permission%>'+"&authorize_permission="+'<%//=authorize_permission%>'
		+"&delete_permission="+'<%=delete_permission%>'+"&check_permission="+'<%=check_permission%>'
		+"&month="+'<%=month%>'+"&year="+'<%=year%>'
		+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl;
		
		location.replace(url);
	}
}

var newWindow223;
function Sending_mail(inv_type,path,new_seq_no,contract_type,cust_nm,customer_cd,inv_dt,customer_plant_seq_no,contact_cd){
	var year=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = "";
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var inv_flag="LATEPAY_";
	if(!newWindow223 || newWindow223.closed){
		newWindow223 = window.open("../sales_invoice/frm_sign_pdf_mail.jsp?invoice_path="+path+"&inv_flag="+inv_flag+"&inv_type="+inv_type+"&invoice_dt="+inv_dt+"&customer_cd="+customer_cd+"&month="+month+"&year="+year+"&hlpl_inv_no="+new_seq_no+"&contract_type="+contract_type+"&customer_abbr="+cust_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&contact_cd="+contact_cd+"&approve_permission="+approve_permission+"&authorize_permission="+authorize_permission+"&check_permission="+check_permission+"&delete_permission="+delete_permission+"&write_permission="+write_permission+"&print_permission="+print_permission+"&form_id="+'<%=formId%>'+"&form_nm="+'<%=form_nm%>',"View PDF","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
	}
	else{
		newWindow223.close();
		newWindow223 = window.open("../sales_invoice/frm_sign_pdf_mail.jsp?invoice_path="+path+"&inv_flag="+inv_flag+"&inv_type="+inv_type+"&invoice_dt="+inv_dt+"&customer_cd="+customer_cd+"&month="+month+"&year="+year+"&hlpl_inv_no="+new_seq_no+"&contract_type="+contract_type+"&customer_abbr="+cust_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&contact_cd="+contact_cd+"&approve_permission="+approve_permission+"&authorize_permission="+authorize_permission+"&check_permission="+check_permission+"&delete_permission="+delete_permission+"&write_permission="+write_permission+"&print_permission="+print_permission+"&form_id="+'<%=formId%>'+"&form_nm="+'<%=form_nm%>',"View PDF","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
	}
}
</script>
</html>
					
