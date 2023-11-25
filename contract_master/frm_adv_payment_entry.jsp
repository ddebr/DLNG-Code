<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DLNG | Advance Payment Entry/Modify</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>

<style type="text/css">
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
</style>
<script type="text/javascript">

function checkNum(obj){
	var reg = /^[0-9.,]+$/; 
	var val = obj.value; 
	if(reg.test(val)){
// 		alert('match')
	}else{
		obj.value="";
		alert('Please enter valid number!!')
	} 
}

function doSubmit(){
	
	var recv_dt = document.forms[0].recv_dt.value;
// 	var adv_amt =  document.forms[0].adv_amt.value;
		
	var currency = document.forms[0].hid_currency.value;
	var fgsa_revno = document.forms[0].fgsa_revno.value;
	var sn_cd = document.forms[0].sn_cd.value;
	var sn_revno = document.forms[0].sn_revno.value;
	var fgsa_cd = document.forms[0].fgsa_cd.value;
	var bscode  = document.forms[0].bscode.value;
	var subFlg = document.forms[0].subFlg.value;
	var pay_typ	 = document.forms[0].pay_typ.value;
// 	alert(pay_typ)
	var flag = false;
	var msg =""; 
	if(fgsa_revno!='' && sn_cd!='' && sn_revno!='' && fgsa_cd!='' && bscode!=''  ){
		
		if(currency == '1'){
			if(recv_dt!='' ){
				flag = true;
			}else{
				msg="Please enter all the mandaory details";
			}
		}else if(currency == '2'){
			
			var exg_rt = document.forms[0].exg_rt.value;
			var exg_dt = document.forms[0].exg_dt.value;
			
			if(recv_dt!='' && exg_rt!='' && exg_dt!=''){
				flag = true;
			}else{
				msg="Please enter all the mandaory details";
			}
		}else{
			msg+="Please select proper currency!!";
		}
	}else{
		msg = 'Please select proper customer details';
	}
	
	 if(pay_typ == '' || pay_typ == ' '){
		flag = false;
		msg+= 'Please select atleast one advance amount type!!';
	}else{
		
		 if(pay_typ=='AP'){
			var adv_amt =  document.forms[0].AP.value;
			if(adv_amt == 0 || adv_amt == '' || adv_amt == ' '){
				flag = false;
				msg+= 'Please enter valid Advance Payment Amount!!';
			}	
		} 
		 if(pay_typ=='SP'){
			var sec_amt =  document.forms[0].SP.value;
			if(sec_amt == 0 || sec_amt == '' || sec_amt == ' '){
				flag = false;
				msg+= 'Please enter valid Security Payment Amount!!';
			}
		}
		if(pay_typ=='LP'){
			var lmsm_amt =  document.forms[0].LP.value;			
			if (lmsm_amt == 0 || lmsm_amt == '' || lmsm_amt == ' '){
				flag = false;
				msg+= 'Please enter valid Lump-sum Payment Amount!!';
			}
		}  
	}
	 
	if(flag){
		var a = "";
		if(subFlg == 'entry'){
			a = confirm("Dou you want to submit ?");
		}else if(subFlg == 'modify'){
			a = confirm("Dou you want to Modify ?");
		}
		
		if(a){
			document.forms[0].submit();	
		}
		
	}else{
		alert(msg);
	}
}

function modifyPayment(i,pay_typ_cd){

	var seq_no = document.getElementById('seq_no'+i).value;
	var payment_dt = document.getElementById('mod_payment_dt'+i).value;
	var adv_amt = document.getElementById('mod_adv_amt'+i).value;
	var currency = document.getElementById('mod_currency'+i).value;
	var exg_rt = document.getElementById('mod_exg_rt'+i).value;
	var exg_dt = document.getElementById('mod_exg_dt'+i).value;
	var ent_by_cd = document.getElementById('mod_ent_by_cd'+i).value;
	var ent_dt = document.getElementById('mod_ent_dt'+i).value;
	var rmk = document.getElementById('hid_mod_rmk'+i).value;
	document.getElementById('sel_amt').value = adv_amt;
	
	document.getElementById('pay_typ').value = pay_typ_cd;
	if(pay_typ_cd == 'AP'){
		
		document.forms[0].chk[2].disabled = false;
		document.forms[0].chk[2].checked = true;
		
		document.forms[0].AP.disabled = false;
		document.forms[0].SP.disabled = true;
		document.forms[0].LP.disabled = true;
		
		document.forms[0].chk[0].checked = false;
		document.forms[0].chk[1].checked = false;
		
		document.forms[0].chk[0].disabled = true;
		document.forms[0].chk[1].disabled = true;
		
		document.getElementById('AP').value = adv_amt;
		document.getElementById('LP').value = '';
		document.getElementById('SP').value = '';
		
		
	}
	if(pay_typ_cd == 'SP'){
		
		document.forms[0].chk[0].disabled = false;
		document.forms[0].chk[0].checked = true;
		
		document.forms[0].SP.disabled = false;
		document.forms[0].AP.disabled = true;
		document.forms[0].LP.disabled = true;
		
		document.forms[0].chk[2].checked = false;
		document.forms[0].chk[1].checked = false;
		
		document.forms[0].chk[2].disabled = true;
		document.forms[0].chk[1].disabled = true;
		
		document.getElementById('SP').value = adv_amt;
		document.getElementById('AP').value = '';
		document.getElementById('LP').value = '';
	}
	if(pay_typ_cd == 'LP'){
		
		document.forms[0].chk[1].disabled = false;
		document.forms[0].chk[1].checked = true;
		
		document.forms[0].LP.disabled = false;
		document.forms[0].AP.disabled = true;
		document.forms[0].SP.disabled = true;
		
		document.forms[0].chk[2].checked = false;
		document.forms[0].chk[0].checked = false;
		
		document.forms[0].chk[2].disabled = true;
		document.forms[0].chk[0].disabled = true;
		
		document.getElementById('LP').value = adv_amt;
		document.getElementById('AP').value = '';
		document.getElementById('SP').value = '';
	}
	
	document.forms[0].seq_no.value = seq_no;
	document.forms[0].recv_dt.value = payment_dt;
// 	document.forms[0].adv_amt.value = adv_amt;
	document.forms[0].hid_currency.value = currency;
	
	if(currency == '1'){
		document.getElementById('view_currency').innerHTML = "INR";
	}else if(currency == '2'){
		document.getElementById('view_currency').innerHTML = "USD";
	}
	
	if(currency == '2'){
		
		document.forms[0].exg_rt.value = exg_rt;
		document.forms[0].exg_dt.value = exg_dt;
		document.getElementById('exg_rt').style.display="table-cell";
		document.getElementById('exg_dt').style.display="table-cell";
	}else{
		
		document.forms[0].exg_rt.value = "0";
		document.forms[0].exg_dt.value = "";
		document.getElementById('exg_rt').style.display="none";
		document.getElementById('exg_dt').style.display="none";
	}
	document.getElementById('rmk').innerHTML = rmk;
	
	document.getElementById('sub').innerHTML = "Modify";
	document.getElementById('subFlg').value = "modify";

}

function reset_val(){
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	var currency = document.forms[0].hid_currency.value;
	var fgsa_revno = document.forms[0].fgsa_revno.value;
	var sn_cd = document.forms[0].sn_cd.value;
	var sn_revno = document.forms[0].sn_revno.value;
	var fgsa_cd = document.forms[0].fgsa_cd.value;
	var bscode  = document.forms[0].bscode.value;
	var cont_typ  = document.forms[0].cont_typ.value;
	var fgsa_st_dt = document.forms[0].fgsa_st_dt.value;
	var fgsa_end_dt = document.forms[0].fgsa_end_dt.value;
	
	location.replace("../contract_master/frm_adv_payment_entry.jsp?fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&fgsa_cd="+fgsa_cd+"&bscode="+bscode+"&selCurrency="+currency+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&fgsa_st_dt="+fgsa_st_dt+"&fgsa_end_dt="+fgsa_end_dt+"&cont_typ="+cont_typ);
}

function compareDate(advDt){
	
		var fgsa_st_dt = document.forms[0].fgsa_st_dt.value;
		var fgsa_end_dt = document.forms[0].fgsa_end_dt.value;
		var sysdate = document.forms[0].sysdate.value;
		
		var end_dt = fgsa_end_dt.split(/[/: ]/);
		var sysdt = fgsa_st_dt.split(/[/: ]/);
		var payDt =  advDt.split(/[/: ]/);
		var sysDt =  sysdate.split(/[/: ]/);
		
		var edt = new Date(end_dt[2], end_dt[1] - 1, end_dt[0]);
		var sys = new Date(sysdt[2], sysdt[1] - 1, sysdt[0]);
		var advDtArr = new Date(payDt[2], payDt[1] - 1, payDt[0]);
		var sysDtArr = new Date(sysDt[2], sysDt[1] - 1, sysDt[0]);
		
// 		alert(edt+"---"+sys+"---"+advDtArr);
		if(edt > advDtArr){
			if(sysDtArr < advDtArr){
				document.getElementById('recv_dt').value='';
				alert("Payment Received date should not be greater than Current Date :"+sysdate);	
			}	
			
		}else{
			document.getElementById('recv_dt').value='';
			alert("Payment Received date should not be greater than Contract End Date :"+fgsa_end_dt);
		} 
	}
	
function doClse(fgsa_cd,fgsa_revno,sn_cd,sn_revno,bscode,msg,err_msg){
	
	window.opener.fromAdvPayChild(fgsa_cd,fgsa_revno,sn_cd,sn_revno,bscode,msg,err_msg);
	window.close();	
	
}	

  function setVal(selText){
	 
	document.forms[0].pay_typ.value = selText;
	if(selText == 'AP'){
		
		document.getElementById('AP').disabled=false;
		document.getElementById('SP').disabled=true;
		document.getElementById('LP').disabled=true;
		
		document.getElementById('SP').value='';
		document.getElementById('LP').value='';
	}
	if(selText == 'SP'){
		
		document.getElementById('AP').disabled=true;
		document.getElementById('SP').disabled=false;
		document.getElementById('LP').disabled=true;
		
		document.getElementById('AP').value='';
		document.getElementById('LP').value='';
	}
	if(selText == 'LP'){
		
		document.getElementById('AP').disabled=true;
		document.getElementById('SP').disabled=true;
		document.getElementById('LP').disabled=false;
		
		document.getElementById('AP').value='';
		document.getElementById('SP').value='';
	}
}   
  
function aprAdvPay(indx){
	
	document.getElementById('selIndx').value=indx;
	var a = confirm("Do you want to Approve Advance Payment ?");
	if(a){
		
		document.forms[0].option.value="ApproveAdvancePayment";
		document.forms[0].submit();	
	}
 } 
</script>


</head>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.Databean_Advance_Payment" id="advPay" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<%
	utilBean.init();
	String  sysdate = utilBean.getGen_dt();
	NumberFormat nf = new DecimalFormat("###########0.00"); 
	String user_cd = (String)session.getAttribute("user_cd");
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200227
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200227
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200227

	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	
	
	String fgsa_revno = request.getParameter("fgsa_revno")==null?"0":request.getParameter("fgsa_revno");
	String sn_cd = request.getParameter("sn_cd")== null?"":request.getParameter("sn_cd");
	String sn_revno = request.getParameter("sn_revno")==null?"0":request.getParameter("sn_revno");
	String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
	String bscode = request.getParameter("bscode")==null?"0":request.getParameter("bscode");
	String selCurrency = request.getParameter("selCurrency")==null?"":request.getParameter("selCurrency");
	String fgsa_st_dt = request.getParameter("fgsa_st_dt")==null?"":request.getParameter("fgsa_st_dt");
	String fgsa_end_dt = request.getParameter("fgsa_end_dt")==null?"":request.getParameter("fgsa_end_dt");
	String cont_typ = request.getParameter("cont_typ")==null?"":request.getParameter("cont_typ");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String err_msg = request.getParameter("err_msg")==null?"":request.getParameter("err_msg");
	
	
	advPay.setCallFlag("fetchAdvPayDtl");
	advPay.setBscode(bscode);
	advPay.setFgsa_cd(fgsa_cd);
	advPay.setFgsa_revno(fgsa_revno);
	advPay.setSn_cd(sn_cd);
	advPay.setSn_revno(sn_revno);
	advPay.setCont_typ(cont_typ);
	advPay.init();
	
	 Vector payment_dt = advPay.getPayment_dt();
	 Vector  adv_amt = advPay.getAdv_amt();
	 Vector  exg_rt =  advPay.getExg_rt();
	 Vector  currency =  advPay.getCurrency();
	 Vector  exg_dt =  advPay.getExg_dt();
	 Vector  seq_no =  advPay.getSeq_no();                                               
	 Vector  ent_by_cd =  advPay.getEnt_by_cd();
	 Vector  ent_by_nm =  advPay.getEnt_by_nm();
	 Vector  ent_dt =  advPay.getEnt_dt();
	 Vector  remark =  advPay.getRemark();
	 Vector  pay_typ_cd =  advPay.getPay_type_cd();
	 Vector  pay_typ_nm =  advPay.getPay_type_nm();
		
	 double total_amt = advPay.getTotal_amt();
	 
	 Vector approved_dt  =  advPay.getApproved_dt();
	 Vector approved_flag = advPay.getApproved_flag();
	System.out.println("approve_permission--------"+approve_permission);
// 	System.out.println("err_msg--------"+err_msg);
%>
	<body <%if(msg.length() > 5 || err_msg.length() > 5) {%>
			onload="doClse('<%=fgsa_cd %>','<%=fgsa_revno %>','<%=sn_cd %>','<%=sn_revno %>','<%=bscode %>','<%=msg %>','<%=err_msg %>');"
		<%} %>>
		<div class="box mb-0">
			<form name="" method="post" action="../servlet/Frm_Advance_Payment">
			
			<input type="hidden" name="option" value="AdvancePaymentEntry">
			<input type="hidden" name="fgsa_revno" value="<%=fgsa_revno%>">
			<input type="hidden" name="sn_cd" value="<%=sn_cd%>">
			<input type="hidden" name="sn_revno" value="<%=sn_revno%>">
			<input type="hidden" name="fgsa_cd" value="<%=fgsa_cd%>">
			<input type="hidden" name="bscode" value="<%=bscode%>">
			<input type="hidden" name="user_cd" value="<%=user_cd%>">
			<input type="hidden" name="modCd" value="<%=modCd%>">
			<input type="hidden" name="formId" value="<%=formId%>">
			<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
			<input type="hidden" name="cont_typ" value="<%=cont_typ%>">
			
			<input type="hidden" name="fgsa_st_dt" value="<%=fgsa_st_dt%>">
			<input type="hidden" name=fgsa_end_dt value="<%=fgsa_end_dt%>">
			<input type="hidden" name="write_permission" value="<%=write_permission%>">
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
			<input type="hidden" name="print_permission" value="<%=print_permission%>">
			<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
			<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			
			<input type="hidden" name="subFlg" id="subFlg" value="entry">
			<input type="hidden" name="total_amt" id="total_amt" value="<%=total_amt%>">
			<input type="hidden" name="sel_amt" id="sel_amt" value="0">
			<input type="hidden" name="sysdate" id="sysdate" value="<%=sysdate %>" >
			<input type="hidden" name="pay_typ" id="pay_typ" value="" >
			
			<!-- for approval part -->
			<input type="hidden" name="selIndx" id="selIndx" value="" >
			
			
				<div class="box-body table-responsive no-padding">
					<%-- <table class="table  table-bordered">
						<thead>
						<%if(msg.length()>5){%>
				    			<tr>
				    				<th class="text-center" colspan="11" style="color: blue;"> <%=msg%> </th>
				    			</tr>
						<%} %>  
						
						<%if(err_msg.length()>5){%>
				    			<tr>
				    				<th class="text-center" colspan="11" style="color: red;"> <%=err_msg%> </th>
				    			</tr>
						<%} %>   
							<tr>
								<th colspan="9" class="text-center info"><b>Advance Payment Entry/Modify</b></th>
							</tr>
							
							<tr style="font-size: 12px;" class="info" >
								<th class="text-center ">Payment Received Dt.<font color="red">*</font> </th>
								<th class="text-center">Advance Amt. Type <font color="red">*</font></th>
								<th class="text-center">Currency</th>
								<%if(selCurrency.equalsIgnoreCase("USD")){%>
									<th class="text-center" >Exchange Rate <font color="red">*</font></th>
									<th class="text-center">Exchange Rate Dt. <font color="red">*</font></th>
								<%} %>
								
								
								<th class="text-center">Remark</th>
							</tr>
							</thead>
							<tr align="center">
								<td >
									<input type="text" name="recv_dt" id="recv_dt" value="" class="datepick" size="11" placeholder="dd/mm/yyyy" onchange="compareDate(this.value);">
									<input type="hidden" name="seq_no" id="seq_no" value="">
								</td>
								<td class="text-center">
									<input type="radio" name="chk"  onclick="setVal('SP');" >&nbsp;Security: &nbsp;&nbsp;&nbsp;<input type="text" class="text-right" name="SP" id="SP" value="" size="15" onkeyup="checkNum(this);" disabled="disabled" ><br>
									<input type="radio" name="chk"  onclick="setVal('LP');" >&nbsp;lump-sum:  <input type="text" class="text-right" name="LP" id="LP" value="" size="15" onkeyup="checkNum(this);" disabled="disabled" ><br>
									<input type="radio" name="chk"  onclick="setVal('AP');" >&nbsp;Advance: &nbsp;&nbsp;<input type="text" class="text-right" name="AP" id="AP" value="" size="15" onkeyup="checkNum(this);" disabled="disabled" >
								</td> 
								<!-- <td class="text-center">
									<input type="text" class="text-right" name="adv_amt" id="adv_amt" value="" size="15" onkeyup="checkNum(this);">
								</td>  -->
								<td>
									<span id="view_currency"> 
									<%if(selCurrency.equals("1")){ %>
										INR
									<%}else if(selCurrency.equals("2")){ %>
										USD
									<%} %>
									</span>
									<input type="hidden" name="hid_currency"  value="<%=selCurrency %>">
								</td>
									 <td>
										<input type="text" class="text-right" name="exg_rt" id="exg_rt" value="" size="10" <%if(selCurrency.equals("1")) {%> style="display: none;" <%} %> onkeyup="checkNum(this);">
									</td>
									<td>
										<input type="text" name="exg_dt" id="exg_dt" value="" size="11" class="datepick" placeholder="dd/mm/yyyy" <%if(selCurrency.equals("1")) {%> style="display: none;" <%} %>>
									</td> 
								<td>
									<textarea rows="3" cols="30" name="rmk" id="rmk"></textarea>
								</td>
							</tr>
							
							<tr>
								
								<td align="right" colspan="9">
									<button type="button"  class="btn btn-warning" onclick="reset_val();" id="rset">Reset</button>
									<button type="button"  class="btn btn-success" id="sub"
									<%if(write_permission.equalsIgnoreCase("Y")) {%>
											onclick="doSubmit();"
										<%}else{%>
											disabled="disabled" title="No Access Rights!!"
										 <%} %>
										>Submit</button>
								</td>
							</tr>	
						
					</table> --%>
					
					<table class="table  table-bordered text-center">
						<thead>   
							<tr>
								<th colspan="11" class="text-center info"><b>Available Advance Payment Entries</b></th>
							</tr>
							<tr class="info" style="font-size: 12px;">
								<th rowspan="2" colspan="1">Seq. No.</th>
								<th rowspan="2" colspan="1">Payment Receive Dt.</th>
								<th rowspan="2" colspan="1">Advance Amt.</th>
								<th rowspan="2" colspan="1">Currency</th>
								<th rowspan="2" colspan="1">Exchange Rate</th>
								<th rowspan="2" colspan="1">Exchange Rate Dt.</th>
								<th rowspan="2" colspan="1">Payment Type.</th>
								<th rowspan="1" colspan="2">Entry</th>
								<th rowspan="2" colspan="1">Remarks</th>
								<th rowspan="2" colspan="1">Status</th>
							</tr>
							<tr  class="info" style="font-size: 12px;">
								<th>By</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody>
						<%if(seq_no.size() > 0 ){ %>
							<%for(int i = 0 ; i <seq_no.size() ; i++){ %>
								<tr>
									<td>
									<%if(!approved_flag.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
										<i class="fa fa-pencil-square-o text-left" style="font-size:18px;color:blue" title="Click to modify" onclick="modifyPayment('<%=i%>','<%=pay_typ_cd.elementAt(i)%>');"></i>&nbsp;&nbsp;&nbsp;&nbsp;
									<%} %>
										<%=seq_no.elementAt(i) %>
										<span class="text-right"> 
											<input type="hidden" value="<%=seq_no.elementAt(i) %>" name="mod_seq_no" id="seq_no<%=i%>">
										</span>	
									</td>
									<td>
										<%=payment_dt.elementAt(i) %>
										<input type="hidden" value="<%=payment_dt.elementAt(i) %>" name="mod_payment_dt" id="mod_payment_dt<%=i%>">
									</td>
									<td class="text-right">
										<%=adv_amt.elementAt(i) %>
										<input type="hidden" value="<%=adv_amt.elementAt(i) %>" name="mod_adv_amt" id="mod_adv_amt<%=i%>">	
									</td>
									<td>
										<%if(currency.elementAt(i).toString().equals("1")){ %>
											INR
										<%}else if(currency.elementAt(i).toString().equals("2")){ %>
											USD
										<%} %>
										<input type="hidden" value="<%=currency.elementAt(i) %>" name="mod_currency" id="mod_currency<%=i%>">
									</td>
									<td class="text-right">
										<%=exg_rt.elementAt(i) %>
										<input type="hidden" value="<%=exg_rt.elementAt(i) %>" name="mod_exg_rt" id="mod_exg_rt<%=i%>">
									</td>
									<td>
										<%=exg_dt.elementAt(i) %>
										<input type="hidden" value="<%=exg_dt.elementAt(i) %>" name="mod_exg_dt" id="mod_exg_dt<%=i%>">
									</td>
									<td class="text-center">
										<%=pay_typ_nm.elementAt(i) %>
										<input type="hidden" value="<%=pay_typ_cd.elementAt(i) %>" name="pay_typ_cd" id="pay_typ_cd<%=i%>">
									</td>
									<td>
										<%=ent_by_nm.elementAt(i) %>
										<input type="hidden" value="<%=ent_by_cd.elementAt(i) %>" name="mod_ent_by_cd" id="mod_ent_by_cd<%=i%>">
									</td>
									<td >
										<%=ent_dt.elementAt(i) %>
										<input type="hidden" value="<%=ent_dt.elementAt(i) %>" name="mod_ent_dt" id="mod_ent_dt<%=i%>">
									</td>
									<td>
										<%=remark.elementAt(i) %>
										<input type="hidden" name="hid_mod_rmk" id= "hid_mod_rmk<%=i %>" value="<%=remark.elementAt(i) %>">
									</td>
									<td>
									<%if(approved_flag.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
										<button class="btn btn-default" type="button" name="Approve" value="Approve" disabled="disabled" title="Approved On : <%=approved_dt.elementAt(i) %>">Approved</button>
									<%}else{%>
										<button class="btn btn-primary" type="button" name="Approve" value="Approve" >Yet to Approve</button>
										<%if(approve_permission.equalsIgnoreCase("Y")){ %>
<%-- 											onclick="aprAdvPay('<%=i%>');" --%>
										<%}else{ %>
<!-- 											disabled="disabled" title="You have no access rights!!" -->
										<%} %>
										
									<%} %>
									</td>
									
								</tr>
							<%} %>
							<tr class="info" style="font-weight: bold;">
								<td>Total </td>
								<td colspan="2" class="text-right"><%=nf.format(total_amt) %></td>
								<td colspan="8"></td>
							</tr>
							
							<%}else{ %>
								<tr>
									<td colspan="11" style="color: red"> No Advance Payment Entry found for the selected Supply Notice..!</td>
								</tr>
							<%} %>
						</tbody>
					</table>		
				</div>
			</form>
		</div>
		
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
		
<script type="text/javascript">
$('.datepick').each(function() {
	$(this).datepicker({
		changeMonth : true,
		changeYear : true,
		format : "dd/mm/yyyy",
		language : "en",
		autoclose : true,
		todayHighlight : true,
		orientation : "bottom auto"
// 		startDate : "tommorow"
	});
});


</script>
	</body>
</html>		
					