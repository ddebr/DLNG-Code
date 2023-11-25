<%@ page import="java.util.*"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG | Advance Advice Report</title>
<!-- Bootstrap CSS -->
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
function compareDates(obj) {
	var from_date = document.forms[0].from_date.value;
	var to_date = document.forms[0].to_date.value;
	if(from_date!='' && to_date!='') {
		var c = compareDate(from_date,to_date);
		if(c == 1){
			alert("From Date Should Be Less Then To Date!!");
			obj.value = '';
			
		}
	}
}

function fetchRpt(){
	
	var flg=true;
	var msg='';
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	var from_date = document.forms[0].from_date.value;
	var to_date = ""; //document.forms[0].to_date.value;
	var mapping_id = document.forms[0].mapping_id.value;
	var plant_no = document.forms[0].plant_no.value;
// 	alert(mapping_id)
	/* if(from_date == '' || from_date == ' '){
		flg=false;
		msg='Please select from date!';
	}
	if(to_date == '' || to_date == ' '){
		flg=false;
		msg+='\nPlease select to date!';
	} */
	
	if(flg){
		location.replace(modUrl+"?to_date="+to_date+"&from_date="+from_date+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&mapping_id="+mapping_id+"&modUrl="+modUrl+"&plant_no="+plant_no);
	}else{
		alert(msg)
	}
}

function showModel(sz){
	
	/* for(var i = 0 ; i < parseFloat(sz) ; i++){
		
		document.getElementById('vol_mmbtu'+i).innerHTML = document.getElementById('last_inv_qty').value;	
	} */
	$('#myModal').modal('show');
	
}

function printPDF(btnFlg){
	
	var plant_no = document.forms[0].plant_no.value;
	var mapping_id = document.forms[0].mapp_id.value;
	var from_date =  document.forms[0].from_date.value;
	var last_inv_qty = document.forms[0].last_inv_qty.value;
	
	if(plant_no!="" && mapping_id!=""){
	
		if(btnFlg == 'P'){
			
			newWindow2 = window.open("../reports/print_pdf_advance_advice_cust.jsp?mapping_id="+mapping_id+"&plant_no="+plant_no+"&from_date="+from_date+"&last_inv_qty="+last_inv_qty,"Print_PDF_Advance_Advice","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
			
		}else if(btnFlg == 'E'){
			
			newWindow2 = window.open("../reports/mail_advance_advice.jsp?mapping_id="+mapping_id+"&plant_no="+plant_no+"&last_inv_qty="+last_inv_qty,"Print_PDF_Advance_Advice","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
		}
		
	}		
}
function refreshPageFromChild1(from_dt,msg){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	var mapping_id = document.forms[0].mapping_id.value;
	var plant_no = document.forms[0].plant_no.value;
	
	location.replace(modUrl+"?from_date="+from_dt+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&mapping_id="+mapping_id+"&modUrl="+modUrl+"&plant_no="+plant_no+"&msg="+msg);
}

function setQty(){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	var mapping_id = document.forms[0].mapping_id.value;
	var plant_no = document.forms[0].plant_no.value;
	var last_inv_qty = document.forms[0].last_inv_qty.value;
	var from_dt = document.forms[0].from_date.value;
	
	var url = modUrl+"?from_date="+from_dt+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&mapping_id="+mapping_id+"&modUrl="+modUrl+"&plant_no="+plant_no+"&last_inv_qty="+last_inv_qty;
// 	alert(url)
	location.replace(url);
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Advance_Advice" id="daa" scope="page"/> 

<%

java.text.NumberFormat nf7 = new java.text.DecimalFormat("###,###,###,##0.00");
java.text.NumberFormat nf5 = new java.text.DecimalFormat("##########0");
java.text.NumberFormat nf = new java.text.DecimalFormat("###########0.00");

utilBean.init();
String current_date = utilBean.getGen_dt();
String time_gen = utilBean.getTime_gen();
// System.out.println("current_date----"+current_date);

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20200212

String from_dt = request.getParameter("from_date")==null?current_date:request.getParameter("from_date");
String to_dt = request.getParameter("to_date")==null?current_date:request.getParameter("to_date");
String cont_typ = request.getParameter("cont_typ")==null?"":request.getParameter("cont_typ");
String mapp_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
String plant_no = request.getParameter("plant_no")==null?"":request.getParameter("plant_no");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
double last_inv_qty = Double.parseDouble(request.getParameter("last_inv_qty")==null?"0":request.getParameter("last_inv_qty"));

daa.setCallFlag("Advance_Advice_Rpt");
daa.setFrom_dt(from_dt);
daa.setTo_dt(to_dt);
daa.setSelMapId(mapp_id);
daa.setPlant_no(plant_no);
daa.setSysdate(from_dt);
daa.setLast_inv_qty(last_inv_qty);

daa.init();

Vector mapping_id = daa.getMapping_id();
Vector Vcust_abbr = daa.getVcust_abbr();

String contact_Suppl_Name = daa.getContact_Suppl_Name();

String contTyp="",custCd="",flsaCd ="",flsaRev =  "",snCd = "",start_dt = "",end_dt="",snRev="";
if(mapp_id.contains("-")){
	
	String temp_arr[] = mapp_id.toString().split("-");
	 contTyp = temp_arr[0];
	 custCd =  temp_arr[1];
	 flsaCd =  temp_arr[2];
	 flsaRev =  temp_arr[3];
	 snCd = temp_arr[4];
	 snRev = temp_arr[5];
	 start_dt = temp_arr[6];
	 end_dt = temp_arr[7];
	 
	 if(contTyp.equals("S")){
		 contTyp = "SN-"+ snCd;
		 
	 }else if(contTyp.equals("L")){
		 contTyp = "LoA-"+ snCd;
	 }
}

double bal_perc = daa.getBal_perc();
Vector plant_seq_no = daa.getPlant_seq_no();
Vector plant_name = daa.getPlant_name();

String customer_contact_cd = daa.getCustomer_contact_cd();
String customer_contact_nm = daa.getCustomer_contact_nm();

String contact_Customer_Name = daa.getContact_Customer_Name();
// String customer_Invoice_FGSA_Dt = daa.getCustomer_Invoice_FGSA_Dt();
// String invoice_Customer_Contact_Cd = daa.getInvoice_Customer_Contact_Cd();
String contact_Customer_Person_Address = daa.getContact_Customer_Person_Address();
String contact_Customer_Person_City = daa.getContact_Customer_Person_City();
String contact_Customer_Person_Pin = daa.getContact_Customer_Person_Pin();
String contact_Customer_Plant_State = daa.getContact_Customer_Plant_State();
String contact_Customer_State_Code = daa.getContact_Customer_State_Code();

String sales_rate = daa.getSales_rate();
last_inv_qty = daa.getLast_inv_qty();
String Previous_Exchg_Rate_Value = daa.getPrevious_Exchg_Rate_Value(); 
Vector tax_factor = daa.getTax_factor();
Vector cform_flag = daa.getCform_flag();
Vector taxLiability = daa.getTaxLiability();
Vector adv_required =  daa.getAdv_required();
Vector top_up_per =  daa.getTop_up_per();
Vector top_up_amt =  daa.getTop_up_amt();
Vector total_incl_top_up =  daa.getTotal_incl_top_up();
Vector mini_req_amt = daa.getMini_req_amt();
Vector total_req_advance = daa.getTotal_req_advance(); 
double closing_bal = daa.getClosing_bal();
%>
<div class="tab-content">
		<div class="tab-pane active" id="hcasreport">
		<!-- Default box -->
			<div class="box mb-0">
				<form >
				<input type="hidden" name="modCd" value="<%=modCd%>">
				<input type="hidden" name="formId" value="<%=formId%>">
				<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
				<input type="hidden" name="modUrl" value="<%=modUrl%>">
				
				<input type="hidden" name="mapp_id" value="<%=mapp_id%>">
				<input type="hidden" name="time_gen" value="<%=time_gen%>">
				
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
					<div class="box-header with-border" style="background-color:#ffe57f;">
						<div class="form-group col-md-2">
						 	 <label for="">Date</label>
						      <div class='input-group date' id='datetimepicker'>
								<input type='text' class="form-control" id="d1" type="text"  name="from_date"  value="<%=from_dt%>" onBlur="validateDate(this);"/>
								<span class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</span>
							</div>
						 </div>
 
						<%--  <div class="form-group col-md-2">
						 	 <label for="">To Date</label>
						     <div class='input-group date' id='datetimepicker1'>
								 <input type='text' class="form-control" id="d1" type="text" name="to_date"  value="<%=to_dt%>" onBlur="validateDate(this);" onchange="compareDates(this);"/>
								 <span class="input-group-addon">
								 	<i class="fa fa-calendar"></i>
								 </span>
							  </div>
						 </div> --%>
						<div class="form-group col-md-2">
						 	<label for="">Customer</label>	
							<select class="form-control" name="mapping_id" onchange="fetchRpt();">
								<option value="" selected="selected">--select--</option>
								<%for(int i = 0 ; i < mapping_id.size() ; i++){ %>
									<option value="<%=mapping_id.elementAt(i)%>"><%=Vcust_abbr.elementAt(i) %></option>
								<%} %>	
							</select>
							<%if(!mapp_id.equals("")) {%>
								<script type="text/javascript">
									document.forms[0].mapping_id.value='<%=mapp_id%>';
								</script>
							<%} %>
						 </div>
						 
						<div class="form-group col-md-2">
						 	<label for="">Customer Plant</label>	
							<select class="form-control" name="plant_no" id="plant_no" onchange="fetchRpt();" >
								<option value="" selected="selected">--select--</option>
								<%for(int i = 0 ; i < plant_seq_no.size() ; i++){ %>
									<option value="<%=plant_seq_no.elementAt(i)%>"><%=plant_name.elementAt(i) %></option>
								<%} %>	
							</select>
							
							<script type="text/javascript">
								document.forms[0].plant_no.value='<%=plant_no%>';
							</script>
						 </div>
						 
						 <div class="form-group col-md-2">
						 	<label for="">Contact Person</label>	
							<input type="text" class="form-control" value="<%=customer_contact_nm %>" readonly="readonly" name="contact_person_nm" id="contact_person_nm">
							<input type="hidden"  value="<%=customer_contact_cd %>" name="contact_person_cd" id="contact_person_cd">
						 </div>
						
						<div class="form-group col-md-1" title="Last Invoice Quantity (MMBtu)">
						 	 <label for="">Quantity</label>
						 	  <div class='input-group'>
						     	<input type="text" class="form-control text-right" value="<%=nf.format(last_inv_qty)%>" name="last_inv_qty" id="last_inv_qty" onchange="setQty();">
						     </div>
						 </div>
						 
						<div class="form-group col-md-1">
						 	 <label for="">&nbsp;</label>
						 	  <div class='input-group'>
						     	<button type="button"  class="btn btn-primary btn-sm"   name="viewList" value="View" onclick="showModel('<%=plant_seq_no.size() %>');" >Preview</button>
						     </div>
						 </div>
 
						<div class="form-group col-md-1">
						 	 <label for="">&nbsp;</label>
						 	  <div class='input-group'>
						     	<button type="button"  class="btn btn-primary btn-sm" id="btn" onclick="printPDF('P');"  >PRINT PDF</button>
						     </div>
						 </div>
						 
						 <div class="form-group col-md-1">
						 	 <label for="">&nbsp;</label>
						 	  <div class='input-group'>
						     	<button type="button"  class="btn btn-primary btn-sm" id="sendMail" onclick="printPDF('E');"  >Send Mail</button>
						     </div>
						 </div> 
					</div>
				</form>
			</div>		
		</div>
	</div>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document" >
			<div class="modal-content">
				<div class="modal-header" align="center">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
					
					<tr valign="middle">
				    	<td  width="10%">
							<div align="left">
								<img alt="" src="../images/logo/company_Logo.png" height="50px;" width="50px;">
							</div>
						</td>
<!-- 					</tr> -->
<!-- 					<tr valign="middle"> -->
				    	<td width="90%">
							<div align="center">
								<font size="4" face="Arial">
									<b><%=contact_Suppl_Name%></b>
								</font>
								<br>
								<font size="3" face="Arial">
									<b>Advance Advice for Next Truck Loading</b>
								</font>
							</div>
						</td>
					</tr>
					</table>
					<table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0">
					
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>
					
					<tr valign="top">
						<!-- <td colspan="1" width="5%">
							<div align="left">
								<font size="2px" face="Arial">
									<b>To:</b>
								</font>
							</div>
					    </td> -->
					    <td colspan="3" width="40%">
							<div align="left" class="just-font">
								<font size="2px" face="Arial">
									To,<br>
									<%=customer_contact_nm%>,<br>
									<%=contact_Customer_Name%>,<br>
									<%=contact_Customer_Person_Address%>,<br>
									<%=contact_Customer_Person_City%>&nbsp;-&nbsp;<%=contact_Customer_Person_Pin%>
								</font>
							</div>
					    </td>
					   <!--  <td colspan="4" width="30%">
							<div align="left">
								<font size="2px" face="Arial">
									
								</font>
							</div>
					    </td> -->
					    <td colspan="2" width="20%">
							<div align="left">
								<font size="2px" face="Arial">
									<b>Sent Date:</b> <%=current_date %> 
									<br>
									<b>Sent Time:</b> <%=time_gen %>
								</font>
							</div>
					    </td> 
					   
					</tr>
					
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>
					<tr valign="top">
						<td colspan="12" >
							<div align="left">
								<font size="2px" face="Arial"><b>Subject : Advance Advice</b></font> 
							</div>
					    </td>
					</tr>
					
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>
					
					<tr valign="middle">
						<td colspan="12">
							<div align="left">
								<font size="2px" face="Arial">Dear Sir/Madam,<br><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;As per the requirements of advance payment clause mentioned in <b> <%=contTyp %> Dt.<%=start_dt %> </b>, kindly deposit the advance amount before the nomination of next truck.</font>
							</div>
					    </td>
					</tr>
					
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>
					
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>
					
					<tr valign="middle">
						 <td colspan="1" width="25%">
							<div align="center">
								<table width="100%"  border="1" align="center" >
									<tr style="height: 15px;">
										<td><div align="center" style="vertical-align: middle;"><font size="2px" face="Arial"><b>Items</b></font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td ><div align="Left" ><font size="2px" face="Arial">&nbsp;Price (USD/MMBtu)</font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial">&nbsp;Fx (USD/INR)</font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial">&nbsp;Volume (MMBtu)</font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial">&nbsp;Tax Rate</font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial">&nbsp;Tax Liability</font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial"><b>&nbsp;Advance/Truck (INR)</b></font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial">&nbsp;Top-up (%)</font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial">&nbsp;Top-up Amount (INR)</font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial"><b>&nbsp;Total incl. top-up (INR) (A)</b></font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial">&nbsp;Balance (B)</font></div></td>
									</tr>
									<tr style="height: 15px;">
										<td style="vertical-align: middle;"><div align="left" ><font size="2px" face="Arial"><b>&nbsp;Min. Advance Required (INR) (A-B)</b></font></div></td>
									</tr>
								</table>
							</div>
					    </td>
					    <%for (int i = 0 ; i < plant_seq_no.size(); i++){ %>
							<td width="15%">
								<div align="center">
									<table width="100%"  border="1" align="center" >
										<tr style="height: 15px;">
											<td><div align="center" style="vertical-align: middle;"><font size="2px" face="Arial"><b><%=plant_name.elementAt(i) %></b></font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><%=sales_rate %>&nbsp;</font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><%=Previous_Exchg_Rate_Value%>&nbsp;</font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial" id="vol_mmbtu<%=i%>"><%=nf.format(last_inv_qty)%>&nbsp;</font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><%=tax_factor.elementAt(i) %> %&nbsp;</font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td>
											<%if(cform_flag.elementAt(i).toString().equalsIgnoreCase("N")){ %>
											<div align="right" style="vertical-align: middle;"><font size="2px" face="Arial">
												N/A
											<%}else if(cform_flag.elementAt(i).equals("")){ %>
											<div align="left" style="vertical-align: middle;"><font size="2px" face="Arial">
												&nbsp;C-form Not Configured
											<%}else{ %>
											<div align="right" style="vertical-align: middle;"><font size="2px" face="Arial">
												<%=taxLiability.elementAt(i)%>&nbsp;
											<%} %>
											
											</font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><b><%=adv_required.elementAt(i) %>&nbsp;</b></font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><%=top_up_per.elementAt(i) %>&nbsp;</font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><%=top_up_amt.elementAt(i) %>&nbsp;</font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><b><%=total_incl_top_up.elementAt(i) %>&nbsp;</b></font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><%=nf7.format(closing_bal)%>&nbsp;</font></div></td>
										</tr>
										<tr style="height: 15px;">
											<td><div align="right" style="vertical-align: middle;"><font size="2px" face="Arial"><b><%=total_req_advance.elementAt(i) %>&nbsp;</b></font></div></td>
										</tr>
									</table>
								</div>		
							</td>	
					    <%} %>
					</tr>
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>
					
					<tr valign="middle">
						<td colspan="12">
							<div align="left">
								<font size="2px" face="Arial">
									<b>
									Thanking You,
									<br><br>
									For <%=contact_Suppl_Name%>
									<br><br>
									Note: Nomination is subject to advance payment received.
									
									</b>
								</font>
							</div>
					    </td>
					</tr>
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>	
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>
					<tr valign="middle">
						<td colspan="12">
							<div align="center">
								&nbsp;
							</div>
					    </td>
					</tr>		 	
				</table>
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
						 