<%@ page buffer="128kb" %>
<%@ page import="java.util.*" %>

<html>
<head>
<title>DLNG | LoA Billing Clause Details</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- For accordion  -->
<link rel="stylesheet" href="../responsive/w3/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">
<link href="../responsive/css/toggle-switch.css" rel="stylesheet" />
<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
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

.switch-toggle {
  width: 10em;
}

.switch-toggle label:not(.disabled) {
  cursor: pointer;
}
</style>
<script language="JavaScript">
function doSubmit(fgsa_cd,fgsa_rev_no)
{
	var ExchngRef = document.forms[0].rbiref.value;	
	var InvR = document.forms[0].inv_currency.value;
	var PayCur = document.forms[0].payment_currency.value;	
	var end_dt_clause = document.forms[0].bill_end_dt_clause.value;
	//alert("ExchngRef = "+ExchngRef+" InvR = "+InvR+" PayCur = "+PayCur);
	var msg = "First Check The Following Fields :\n\n";	
	var flag1=true;		
	var fgsa_cd=fgsa_cd;
	var fgsa_rev_no=fgsa_rev_no;
	var tax_str_cd = document.forms[0].tax_str_cd.value;
	
	if(document.forms[0].exch_calc_base[0].checked)
	{
		if(document.forms[0].inv_criteria.value=='')
		{
			msg += "Please, Select The Exchange Criteria !!!\n";
			flag1 = false;
		}
	}
	else if(document.forms[0].exch_calc_base[1].checked)
	{
		document.forms[0].inv_criteria.value='';
		/*SB20200722	if(document.forms[0].inv_criteria.value=='')
		{
			msg += "Please, Select The Exchange Criteria !!!\n";
			flag1 = false;
		}*/
	}
	
	//var url = "../servlet/Frm_Contract_Master?PayCur="+PayCur+"&Freq="+Freq+"&rate="+rate+"&period="+period+"&ExchngRef="+ExchngRef+"&ExchngCal="+ExchngCal+"&InvR="+InvR+"&modeper="+modeper+"&fgsa_cd="+fgsa_cd+"&fgsa_rev_no="+fgsa_rev_no;
	document.forms[0].option.value="LOA_BillingDetail";
	document.forms[0].fgsa_cd.value=fgsa_cd;
	document.forms[0].fgsa_rev_no.value=fgsa_rev_no;
		
	 for (var i=0; i < document.forms[0].Freq.length; i++)
	 {
	   if(document.forms[0].Freq[i].checked)
	   {
			 Freq=document.forms[0].Freq[i].value;
			 document.forms[0].freq_flag.value=Freq;						 
	   }		
	 }
	 //==Exchange Rate Calculation
     for (var i=0; i < document.forms[0].exch_calc_base.length; i++)
     {
	   if(document.forms[0].exch_calc_base[i].checked)
	   {
			 ExchngCal=document.forms[0].exch_calc_base[i].value;		 
			 document.forms[0].exch_calc_base_flag.value=ExchngCal;		
	   }	
     }
	
	if(ExchngRef==null || ExchngRef=='0' || ExchngRef=='' || ExchngRef==' ' || ExchngRef=='  ')
	{
			msg += "Please, First Select The Exchange Rate Reference Field !!!\n";
			flag1 = false;
	}
	if(InvR==null || InvR=='0' || InvR=='' || InvR==' ' || InvR=='  ')
	{
			msg += "Please, First Select The Invoice Raised In Field !!!\n";
			flag1 = false;
	}
	if(PayCur==null || PayCur=='0' || PayCur=='' || PayCur==' ' || PayCur=='  ')
	{
			msg += "Please, First Select The Payment Done In Field !!!\n";
			flag1 = false;
	}
	if(end_dt_clause=='Y' || end_dt_clause=='y')
	{
			msg += "Select INVOICE BILLING PERIOD ON !!!\n";
			flag1 = false;
	}
// 	alert(tax_str_cd)
	if(parseFloat(tax_str_cd) == 0)
	{
			msg += "Please Enter TAX Structure Details !!!\n";
			flag1 = false;
	}
	var advAmtFlg = document.forms[0].advAmtFlg.value;
// 	alert(advAmtFlg)
	if(flag1)
	{
		var cform_chk = "";
		if(advAmtFlg == 'Y' && parseFloat(tax_str_cd) > 0){
			/* var len=document.forms[0].chk.length;
			if(len==undefined){
				if(document.forms[0].chk.checked==true){
					document.forms[0].chk_flag.value="Y";
				}
			}else{
				for(var i=0;i<len;i++){
					if(document.forms[0].chk[i].checked==true){
						document.forms[0].chk_flag[i].value="Y";
					}	
				}
			} */
			for(var i = 0 ; i < parseFloat(tax_str_cd) ; i++){
				if(document.getElementById('on'+i).checked){
					document.getElementById('chk_flag'+i).value="Y";
				}else {
					document.getElementById('chk_flag'+i).value="N";
				}
				if(document.getElementById('na'+i).checked){
					cform_chk = "N";
					break;
				}
			}
		}
		
		if(cform_chk == "N"){
			alert("Please Select C-Form Applicability (Yes/No)");
		}else{
			var a = confirm("Do You Want To Submit Data Regarding Letter of Agreement Bill Clause ???");	
			if(a)
			{
				document.forms[0].submit();
			}
		}
	}
	else
	{
		alert(msg);
	}
}


function setvalues(Freq,Due_Date,Int_Cal_Cd ,Int_Cal_Sign,Int_Cal_Per,Exchng_Rate_Cd,Exchng_Rate_Cal,Invoice_Cur_Cd,Payment_Cur_Cd,Tax_Str_CD,Exchg_Rate_Note,C_flag)
{
	for(var i=0; i < document.forms[0].Freq.length; i++)
   	{
   		if(document.forms[0].Freq[i].value==Freq)
   		{
   			document.forms[0].Freq[i].checked=true;
 		}	
 	}
	document.forms[0].period.value=Due_Date;
	document.forms[0].rbiref.value=Exchng_Rate_Cd;
	document.forms[0].rate.value=Int_Cal_Cd;
	document.forms[0].plusmin.value=Int_Cal_Sign;
	document.forms[0].modeper.value=Int_Cal_Per;
	document.forms[0].inv_currency.value=Invoice_Cur_Cd;
	document.forms[0].payment_currency.value=Payment_Cur_Cd;
	document.forms[0].exchg_rate_note.value=Exchg_Rate_Note;
	document.forms[0].bill_end_dt_clause.value=C_flag;
	//document.forms[0].TAX_STR_CD.value=Tax_Str_CD;
	for(var i=0; i < document.forms[0].exch_calc_base.length; i++)
	{
	   	if(document.forms[0].exch_calc_base[i].value==Exchng_Rate_Cal)
	   	{
	   		document.forms[0].exch_calc_base[i].checked=true;
	   	}	
	}	
}

function clrData()
{
	for(var i=0; i < document.forms[0].Freq.length; i++)
   	{
    	document.forms[0].Freq[i].checked=false;
   	}
	document.forms[0].period.value="0";
	document.forms[0].rbiref.value="0";
	document.forms[0].rate.value="0";
	document.forms[0].plusmin.value="0";
	document.forms[0].modeper.value="0";
	for(var i=0; i < document.forms[0].exch_calc_base.length; i++)
	{
	    document.forms[0].exch_calc_base[i].checked=false;
	}
	document.forms[0].inv_currency.value="0";
	document.forms[0].payment_currency.value="0";
	document.forms[0].exchg_rate_note.value="";
}

</script>
<!-- <link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css"> -->
</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="Util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_Master" id="masOthers" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<%
	//String formId=request.getParameter("formId")==null?"":request.getParameter("formId");
	String ml_username=(String)session.getAttribute("username");
	String ml_formId=request.getParameter("formId");
	String ml_sessionId=session.getId();
	String ml_temp="other"; 
	modlock.setSet_username(ml_username);
	modlock.setFormId(ml_formId);
	modlock.setSessionId(ml_sessionId);
	modlock.setCallFrom(ml_temp);
	
	Util.init();
	String FGSA_No=request.getParameter("FGSA_No")==null?"":request.getParameter("FGSA_No");
	String FGSA_Rev_No=request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");
	String bscode=request.getParameter("bscode")==null?"":request.getParameter("bscode");
	String Start_Dt=request.getParameter("Start_Dt")==null?"":request.getParameter("Start_Dt");
	String msg = request.getParameter("msg")== null?"":request.getParameter("msg");
	String SN_No=request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");
	String SN_Rev_No=request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");
	String form_id = request.getParameter("form_id")==null?"0":request.getParameter("form_id");
	String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
	System.out.println("form_id = "+form_id);
	System.out.println("form_nm = "+form_nm);
	String write_permission = "Y";//request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	
	String inv_criteria = request.getParameter("inv_criteria")==null?"":request.getParameter("inv_criteria");
	String advAmtFlg = request.getParameter("advAmtFlg")==null?"N":request.getParameter("advAmtFlg");
	String cont_typ="L";
	
	masOthers.setFGSA_cd(FGSA_No);
	masOthers.setStartDate(Start_Dt);
	masOthers.setFGSA_REVNo(FGSA_Rev_No);
	masOthers.setSN_CD(SN_No);
	masOthers.setSN_REVNo(SN_Rev_No);
	masOthers.setBscode(bscode);
	masOthers.setCont_typ(cont_typ);
	masOthers.setCallFlag("LOA_Billing_Dtl");
	
	masOthers.init();
	
	FGSA_No=masOthers.getFGSA_NO()==null?FGSA_No:masOthers.getFGSA_NO();
	FGSA_Rev_No=masOthers.getFGSA_REV_NO()==null?FGSA_Rev_No:masOthers.getFGSA_REV_NO();
	bscode=masOthers.getBUYERS_CD()==null?bscode:masOthers.getBUYERS_CD();	
	String Tax_Struct_Cd=masOthers.getTax_Struct_Cd()==null?"":masOthers.getTax_Struct_Cd();	
	Tax_Struct_Cd=request.getParameter("TAX_STR_CD")==null?Tax_Struct_Cd:request.getParameter("TAX_STR_CD");
		
	String Freq=masOthers.getFreq()==null?"F":masOthers.getFreq();	
	if( Freq.trim().equals("") )
	{
		Freq="F";
	}	
	if(inv_criteria.trim().equals(""))
	{
		inv_criteria=masOthers.getInv_criteria();
	}
	String Due_Date=masOthers.getDue_Date()==null?"":masOthers.getDue_Date();
	String Int_Cal_Cd=masOthers.getInt_Cal_Cd()==null?"":masOthers.getInt_Cal_Cd();
	String Int_Cal_Sign=masOthers.getInt_Cal_Sign()==null?"":masOthers.getInt_Cal_Sign();
	String Int_Cal_Per=masOthers.getInt_Cal_Per()==null?"":masOthers.getInt_Cal_Per();
	String Exchng_Rate_Cd=masOthers.getExchng_Rate_Cd()==null?"":masOthers.getExchng_Rate_Cd();
	String Exchng_Rate_Cal=masOthers.getExchng_Rate_Cal()==null?"":masOthers.getExchng_Rate_Cal();
	String Invoice_Cur_Cd=masOthers.getInvoice_Cur_Cd()==null?"":masOthers.getInvoice_Cur_Cd();
	String Payment_Cur_Cd=masOthers.getPayment_Cur_Cd()==null?"":masOthers.getPayment_Cur_Cd();
	String Exchg_Rate_Note=masOthers.getExchg_Rate_Note()==null?"":masOthers.getExchg_Rate_Note();	
	String Flag=masOthers.getFlag()==null?"Y":masOthers.getFlag(); //SB20111207
	String cust_nm=masOthers.getcust_nm()==null?"*":masOthers.getcust_nm(); //SB20111207
	String cust_abr=masOthers.getcust_abr()==null?"*":masOthers.getcust_abr(); //SB20111207
	
	Vector TAX_Plant_Nm = masOthers.getTAX_Plant_Nm();
	Vector TAX_STR_CD = masOthers.getTAX_Str_CD();
	Vector TAX_Remarks = masOthers.getTAX_Remarks();
	Vector int_rate_cd=masOthers.getVInt_cd();
	Vector int_rate_nm=masOthers.getVInt_cal();
	Vector exg_rate_cd=masOthers.getVExc_cd();
	Vector exg_rate_nm=masOthers.getVExv_rt(); 
	boolean billFlag = masOthers.isBillFlag();
	Vector TAX_cform_flg = masOthers.getTAX_cform_flg();
	Vector TAX_Plant_cd = masOthers.getTAX_Plant_cd();
	String due_dt_flag = masOthers.getDue_dt_flag();

	
%>  

<body onload="setvalues('<%=Freq%>','<%=Due_Date%>','<%=Int_Cal_Cd %>','<%=Int_Cal_Sign %>','<%=Int_Cal_Per %>','<%=Exchng_Rate_Cd %>','<%=Exchng_Rate_Cal %>','<%=Invoice_Cur_Cd %>','<%=Payment_Cur_Cd %>','<%=Tax_Struct_Cd %>',&quot;<%=Exchg_Rate_Note%>&quot,&quot;<%=Flag%>&quot;);">
<div class="tab-content">
	<div class="tab-pane active" id="">
		<div class="box mb-0">
			<form method="post" action="../servlet/Frm_Contract_MasterV2">
			
			<input name="option" type="hidden" value="LOA_BillingDetail">
			<input name="signa" type="hidden" value="">
			<input name="fgsa_cd" type="hidden" value="">
			<input name="fgsa_rev_no" type="hidden" value="<%=FGSA_Rev_No%>">
			<input name="bscode" type="hidden" value="<%=bscode%>">
			<input name="Start_Dt" type="hidden" value="<%=Start_Dt%>">
			<input name="SN_No" type="hidden" value="<%=SN_No%>">
			<input name="SN_Rev_No" type="hidden" value="<%=SN_Rev_No%>">
			<input type="hidden" name="form_id" value="<%=form_id%>">
			<input type="hidden" name="form_nm" value="<%=form_nm%>">
			<input type="hidden" name="write_permission" value="<%=write_permission%>">
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
			<input type="hidden" name="print_permission" value="<%=print_permission%>">
			<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
			<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			<input name="advAmtFlg" type="hidden" value=<%=advAmtFlg%>>
			<input name="tax_str_cd" type="hidden" value=<%=TAX_STR_CD.size()%>>
			
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
									<tr><th colspan="7" class="info text-center"><b><%=cust_abr%>[<%=FGSA_No %>-<%=FGSA_Rev_No %>-<%=SN_No %>-<%=SN_Rev_No %>]: BILLING DETAILS</b></th></tr>
									<tr>
										<td colspan="3" class="info">Billing and Payment Frequency</td>
										<td ><input type="radio"  name="Freq" value="W" onclick="">&nbsp;Weekly</td>
										<td ><input type="radio" name="Freq"  value="F" onclick="">&nbsp;Fortnightly</td>
										<td ><input type="radio" name="Freq" value="M" onclick="">&nbsp;Monthly</td>
										<td ><input type="radio" name="Freq" value="Q" onclick="">&nbsp;Quarterly
											<input type="hidden" name="freq_flag" value="F">
										</td>
									</tr>
									<tr>
										<td colspan="3" class="info">Invoice Billing Period on</td>	
										<td colspan="4" align="left">
									    	<select name="bill_end_dt_clause" class="select">
									    		<option value="Y">--Select--</option>
									     		<option value="T"> [T] TCQ Completion </option>
									     		<option value="B"> [B] Billing Period </option>
									     	</select>
									    </td>
									</tr>
									<tr >
										<td colspan="3" class="info">Payment Due Date Period</td>
									    <td ><input type="text" name="period" value="" size="3" style='text-align:right;' onblur="negNumber(this),checkNumber1(this,3,0);">&nbsp;Days</td>
									     <td colspan="3">
									    	Consider Due Date in &nbsp;&nbsp;<select name="due_dt_calc" id="due_dt_calc" class="select">
									    		<option value="">--Select--</option>
									     		<option value="C">Calendar Days</option>
									     		<option value="B">Business Days</option>
									     	</select>
									     	<%if(!due_dt_flag.equalsIgnoreCase("")){ %>
										     	<script type="text/javascript">
										     		document.getElementById('due_dt_calc').value = '<%=due_dt_flag%>';
										     	</script>
									     	<%} %>
									    </td>
									</tr>
									<tr>
										<td colspan="3" class="info">Interest Calculation</td>
									    <td colspan="4" align="left">Rate:&nbsp;
									    	<select name="rate" class="select">
									     	<%	for(int i=0;i<int_rate_cd.size();i++)
									     		{	
									     	%>		<option value="<%=int_rate_cd.elementAt(i)%>"><%=int_rate_nm.elementAt(i)%></option>
									     	<%	}	
									     	%>
									     	</select>&nbsp;
									     	<select name="plusmin" class="select">
									     		<option value="0">select</option>
									     		<option value="+"> + </option>
									     		<option value="-" > - </option>
									     	</select>
									    	<input type="text" name="modeper" value="" size="3" style='text-align:right;' onblur="negNumber(this),checkNumber1(this,4,2);" maxlength="6"><b>(%)</b>
									    </td>
									</tr>
									<tr>
										<td colspan="3" class="info">Exchange Rate Reference</td>
									    <td>
									    	<select name="rbiref" class="select">
									    		<option value="0">--Select--</option>
									     		<%	for(int i=0;i<exg_rate_cd.size();i++)
									     			{	
									     		%>		<option value="<%=exg_rate_cd.elementAt(i)%>"><%=exg_rate_nm.elementAt(i)%></option>
									     		<%	}	
									     		%>
									     	</select> 
									    </td>
									</tr>
									<tr>
										<td colspan="3" class="info">Exchange Rate Calculation</td>
									    <td colspan="2">
									    	<input type="radio" name="exch_calc_base" value="D" checked>&nbsp;On Particular Day Base
									    </td>
									    <td colspan="2">	
									    	<input type="radio" name="exch_calc_base" value="A">&nbsp;On Daily Basis of Billing Period
									    	<input type="hidden" name="exch_calc_base_flag" value="" >
									    </td>
									</tr>
									<tr>
										<td colspan="3" class="info">Exchange Rate Criteria</td>
									    <td colspan="4">
									    	<select name="inv_criteria" class="select">
									    		<option value="">--Select--</option>
									     		<option value="INV">Date of Invoice</option>
									     		<option value="LST">Last Day of Billing Cycle</option>
									     		<option value="PRE">Previous Day of Billing Cycle</option>	     		
									     	</select> 
									     	<script> document.forms[0].inv_criteria.value="<%=inv_criteria%>"</script>
									    </td>
									</tr>
									<tr>
										<td colspan="3" class="info">Invoice Raised In</td>
									    <td >
									    	<select name="inv_currency" class="select">
									    		<option value="0">--Select--</option>
									     		<option value="1"> INR </option>
									     		<option value="2"> USD </option>
									     	</select>
									    </td>
									</tr>
									<tr>
										<td colspan="3" class="info">Payment Done In</td>
									    <td colspan="4">
									    	<select name="payment_currency" class="select">
									    		<option value="0">--Select--</option>
									     		<option value="1"> INR </option>
									     		<option value="2"> USD </option>
									     	</select>
									    </td>
									</tr>
									<tr>
										<td colspan="3" class="info">Exchange Rate Consideration On</td>
									    <td colspan="4"><input type="text" name="exchg_rate_note" size="80" maxlength="200"></td>
									</tr>
									
									<tr><th colspan="7" class="info text-center">Applicable Taxes</th></tr>	
									<tr>	
										<td colspan="1" class="info">Plant</td>
									    <td colspan="2" class="info">Tax Structure</td>
									    <td colspan="2" class="info">Structure Details</td>
									    <%if(advAmtFlg.equals("Y")){ %>
									    	<td colspan="2" class="info">C-FORM Applicable</td>
									    <%}else{ %>
									    	<td colspan="2" class="info">&nbsp;</td>
									    <%} %>
									</tr>
									<%  if(TAX_STR_CD.size()>=0)
											{ 
												for(int i=0;i<TAX_STR_CD.size();i++)
												{ 
										%>			<tr >	
														<td colspan="1" class="text-left"><%=(String)TAX_Plant_Nm.elementAt(i)%></td>
													    <td colspan="2" class="text-left"><%=(String)TAX_STR_CD.elementAt(i)%>
													    <input name="TAX_STR_CD" type="hidden" value="<%=(String)TAX_STR_CD.elementAt(i)%>"></td>
													    <td colspan="2" class="text-left"><%=(String)TAX_Remarks.elementAt(i)%></td>
													     <%if(advAmtFlg.equals("Y")){ %>
													     <td>
														     <div class="switch-toggle switch-3 switch-candy">
																  <input id="on<%=i %>" name="state_d<%=i %>" type="radio" <%if(TAX_cform_flg.elementAt(i).equals("Y")){ %> checked="checked" <%} %> />
																  <label for="on<%=i %>" onclick="" >YES</label>
																
																  <input id="na<%=i %>" name="state_d<%=i %>" type="radio" disabled <%if(!TAX_cform_flg.elementAt(i).equals("Y")){ %>checked="checked" <%} %> />
																  <label for="na<%=i %>" class="disabled" onclick="">&nbsp;</label>
																
																  <input id="off<%=i %>" name="state_d<%=i %>" type="radio" <%if(TAX_cform_flg.elementAt(i).equals("N")){ %>checked="checked" <%} %>/>
																  <label for="off<%=i %>" onclick="">NO</label>
																
																  <a></a>
																</div>
															</td>
														   <%--  <td colspan="2" class="text-left">
														    	<input type="checkbox" name="chk" value="" <%if(TAX_cform_flg.elementAt(i).equals("Y")){ %> checked="checked" <%} %>>
														    </td> --%>
													    <%}else{%>
													    	<td colspan="2" >&nbsp;</td>
													    <%} %>
													     	<input type="hidden" name="chk_flag" id="chk_flag<%=i %>" value="N">
				   			 								<input type="hidden" name="plant_cd" value="<%=TAX_Plant_cd.elementAt(i)%>">				   
													</tr>
										<%		}
										    } 
										%>
										<tr>
								    		<td align="center" colspan="7"  style="background-color: white;" class="text-center">
							    				<button type="reset" class="btn btn-info" name="reset" value="Reset" onClick="clrData();"> Reset  </button>
							    				<%	if(write_permission.trim().equalsIgnoreCase("Y")){	%>
							    					<button type="button"  class="btn btn-success" onClick="doSubmit('<%=FGSA_No%>','<%=FGSA_Rev_No%>');"><%if(billFlag) { %>Modify<% } else { %>Submit <% } %> </button>
							    					
							    				<%}else{ %>
							    					<button type="button"  class="btn btn-success" disabled="disabled">Submit </button>
							    				<%} %>	
								    		</td>
								    	</tr>
	
										<%if(!billFlag) { %>
											<tr> 
												<td colspan="7" class="info text-center">
														<font color="red" size="1" face="Verdana, Arial, Helvetica, sans-serif">
															LoA Billing Detail is Shown Here, Please Submit it for LoA.
														</font>
												</td>
											</tr>
										<% } %>
									</table>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>	
</body>
</html>