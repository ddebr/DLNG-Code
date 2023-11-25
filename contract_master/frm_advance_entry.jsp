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

<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/sort/dataTables.bootstrap.min.css">
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script  type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<!-- <script type="text/javascript" src="../js/jquery.dataTables.min.js"></script> -->
<!-- <script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script> -->
<script type="text/javascript" src="../js/sweetalert.min.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>

<style>
.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 10px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
}

.active, .accordion:hover {
  background-color: #ccc;
}

 /* .accordion:after {
  content: '\002B';
  color: #777;
  font-weight: bold;
  float: right;
  margin-left: 5px;
} 
  */
/*  .active:after {
  content: "\2212";
}  
 */
.panel {
  padding: 0 18px;
  background-color: white;
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.2s ease-out;
}
input:read-only {
  background-color: #cdc3b7;
}

</style>
</head>

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

function relaodPage(){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	var cont_typ = document.getElementById('cont_typ').value;
	var cust_cd = document.getElementById('cust_cd').value;

	var url = modUrl+"?formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&cont_typ="+cont_typ+"&cust_cd="+cust_cd;
// 	alert(url)
	location.replace(url);
}
function setAccordian(obj,id){
	var acc = document.getElementsByClassName("accordion");
	var i;
	for (i = 0; i < acc.length; i++) {
		var oth_id = 'acrdBtn'+i;
// 		alert(oth_id)
		if(id == oth_id){
// 			alert('in')
		   var newclass=''; 
		   var classNm = document.getElementById('plusmins'+i).className;
		   
		   if(classNm == 'fa fa-plus-circle'){
			   newclass = 'fa fa-minus-circle';
		   }else{
			   if(classNm == 'fa fa-minus-circle'){
				   newclass = 'fa fa-plus-circle';
			   }
		   }
		   
		   document.getElementById('plusmins'+i).className = newclass; 
		   
			obj.classList.toggle("active");
		    var panel = obj.nextElementSibling;
		    
		    if (panel.style.maxHeight) {
		      panel.style.maxHeight = null;
		    } else {
		      panel.style.maxHeight = panel.scrollHeight + "px";
		    }
		}
	}
}
function setVal(selText,ind){
	document.getElementById('pay_typ'+ind).value = selText;
	 
	if(selText == 'AP'){
		
		document.getElementById('AP'+ind).readOnly=false;
		document.getElementById('SP'+ind).readOnly=true;
		document.getElementById('LP'+ind).readOnly=true;
		
		document.getElementById('SP'+ind).value='';
		document.getElementById('LP'+ind).value='';
	}
	if(selText == 'SP'){
		
		document.getElementById('AP'+ind).readOnly=true;
		document.getElementById('SP'+ind).readOnly=false;
		document.getElementById('LP'+ind).readOnly=true;
		
		document.getElementById('AP'+ind).value='';
		document.getElementById('LP'+ind).value='';
	}
	if(selText == 'LP'){
		
		document.getElementById('AP'+ind).readOnly=true;
		document.getElementById('SP'+ind).readOnly=true;
		document.getElementById('LP'+ind).readOnly=false;
		
		document.getElementById('AP'+ind).value='';
		document.getElementById('SP'+ind).value='';
	}
}  

function doSubmit(ind){
	var recv_dt = document.getElementById('recv_dt'+ind).value;
	var currency = document.getElementById('hid_currency'+ind).value;
	var subFlg = document.getElementById('subFlg'+ind).value;
	var pay_typ	 = document.getElementById('pay_typ'+ind).value;
	document.getElementById('selInd').value = ind;
	var k = document.getElementById("selInInd").value;
	var drcrtd = document.getElementById('crdrtd'+ind).value
// 	alert(document.forms[0].SP.length)
	var adv_amt = 0 ;
	
	var flag = false;
	var msg =""; 
	
	if(currency == '1'){
		if(recv_dt!='' ){
			flag = true;
		}else{
			msg="Please enter all the mandaory details";
		}
	}else if(currency == '2'){
		
		var exg_rt = document.getElementById('exg_rt'+ind).value;
		var exg_dt = document.getElementById('exg_dt'+ind).value;
		
		if(recv_dt!='' && exg_rt!='' && exg_dt!=''){
			flag = true;
		}else{
			msg="\nPlease enter all the mandaory details";
		}
	}else{
		msg+="\nPlease select proper currency!!";
	}
	if(pay_typ == '' || pay_typ == ' '){
		
		flag = false;
		msg+= '\nPlease select atleast one advance amount type!!';
		
	}else{
		 
		 if(pay_typ=='AP'){
			 
			adv_amt =  document.getElementById('AP'+ind).value;
			if(adv_amt == 0 || adv_amt == '' || adv_amt == ' '){
				flag = false;
				msg+= '\nPlease enter valid Advance Payment Amount!!';
			}	
		} 
		 if(pay_typ=='SP'){
			adv_amt = document.getElementById('SP'+ind).value;
			if(adv_amt == 0 || adv_amt == '' || adv_amt == ' '){
				flag = false;
				msg+= '\nPlease enter valid Security Payment Amount!!';
			}
		}
		if(pay_typ=='LP'){
			adv_amt = document.getElementById('LP'+ind).value;			
			if (adv_amt == 0 || adv_amt == '' || adv_amt == ' '){
				flag = false;
				msg+= '\nPlease enter valid Lump-sum Payment Amount!!';
			}
		}  
	}
	
	if(subFlg == 'generate_cr_dr_note' || drcrtd !=''){
		
		var mod_adv_amt = document.getElementById("seq_wise_total_amt"+k).value;
// 		alert(mod_adv_amt)
		if(document.getElementById("crdrtd"+ind).value == ''){
			flag = false;
			msg+="\nPlease select atleast one option Credit/Debit ";
			
		}else if(document.getElementById("rmk"+ind).value == '' || document.getElementById("rmk"+ind).value == ' '){
			flag = false;
			msg+="\nPlease enter Remark for Credit/Debit Note";
		}
		if(document.getElementById("crdrtd"+ind).value == 'D'){
// 			alert(adv_amt+"**"+mod_adv_amt)
			if(parseFloat(adv_amt) > parseFloat(mod_adv_amt)){
				flag = false;
				msg+="\nDebit Note Amount ("+adv_amt+") should be less than Advance Amount ("+mod_adv_amt+")";
			}
		}
	 }	
	 
	if(flag){
		var a = "";
		if(subFlg == 'entry'){
			a = confirm("Dou you want to submit ?");
		}else if(subFlg == 'modify'){
			a = confirm("Dou you want to Modify ?");
		}else if(subFlg == 'generate_cr_dr_note'){
			a = confirm("Dou you want to Generate CR/DR Note ?");
		}
		if(a){
			document.forms[0].submit();	
		}
	}else{
		alert(msg);
	}
}

function compareDate(advDt,ind){
	
	var fgsa_st_dt = document.getElementById('sn_st_dt'+ind).value;
	var fgsa_end_dt = document.getElementById('sn_end_dt'+ind).value;
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
			document.getElementById('recv_dt'+ind).value='';
			alert("Payment Received date should not be greater than Current Date :"+sysdate);	
		}	
		
	}else if (advDtArr > edt){
		document.getElementById('recv_dt'+ind).value='';
		alert("Payment Received date should not be greater than Contract End Date :"+fgsa_end_dt);
	} 
}

function modifyPayment(k,pay_typ_cd,i,type){

	/*for scrolling to top of the page
	document.body.scrollTop = 0;
	document.documentElement.scrollTop = 0; */
	
	/* for scrolling to specific div */
	$('html, body').animate({
        scrollTop: $("#panelDiv"+i).offset().top
    }, 100);
	
	document.getElementById('selInInd').value = k;
	var seq_no = document.getElementById('mod_seq_no'+k).value;
	var payment_dt = document.getElementById('mod_payment_dt'+k).value;
	var adv_amt = document.getElementById('mod_adv_amt'+k).value;
	var currency = document.getElementById('mod_currency'+k).value;
	var exg_rt = document.getElementById('mod_exg_rt'+k).value;
	var exg_dt = document.getElementById('mod_exg_dt'+k).value;
	var ent_by_cd = document.getElementById('mod_ent_by_cd'+k).value;
	var ent_dt = document.getElementById('mod_ent_dt'+k).value;
	var rmk = document.getElementById('hid_mod_rmk'+k).value;
	document.getElementById('sel_amt'+i).value = adv_amt;
	
	document.getElementById('pay_typ'+i).value = pay_typ_cd;
	
	if(pay_typ_cd == 'AP'){
		
		document.getElementById('chk2'+i).disabled = false;
		document.getElementById('chk2'+i).checked = true;

		document.getElementById('SP'+i).readOnly=true;
		document.getElementById('AP'+i).readOnly=false;
		document.getElementById('LP'+i).readOnly=true;
		
		document.getElementById('chk0'+i).checked = false;
		document.getElementById('chk1'+i).checked = false;
		document.getElementById('chk0'+i).disabled=true;
		document.getElementById('chk1'+i).disabled=true;
		
		
		document.getElementById('SP'+i).value = '';
		document.getElementById('AP'+i).value = adv_amt;
		document.getElementById('LP'+i).value = '';
	}
	if(pay_typ_cd == 'SP'){
		document.getElementById('chk0'+i).disabled = false;
		document.getElementById('chk0'+i).checked = true;

		document.getElementById('SP'+i).readOnly=false;
		document.getElementById('AP'+i).readOnly=true;
		document.getElementById('LP'+i).readOnly=true;
		
		document.getElementById('chk2'+i).checked = false;
		document.getElementById('chk1'+i).checked = false;
		document.getElementById('chk2'+i).disabled=true;
		document.getElementById('chk1'+i).disabled=true;
		
		document.getElementById('SP'+i).value = adv_amt;
		document.getElementById('AP'+i).value = '';
		document.getElementById('LP'+i).value = '';
	}
	if(pay_typ_cd == 'LP'){
		
		document.getElementById('chk1'+i).disabled = false;
		document.getElementById('chk1'+i).checked = true;

		document.getElementById('SP'+i).readOnly=true;
		document.getElementById('AP'+i).readOnly=true;
		document.getElementById('LP'+i).readOnly=false;
		
		document.getElementById('chk2'+i).checked = false;
		document.getElementById('chk0'+i).checked = false;
		document.getElementById('chk2'+i).disabled=true;
		document.getElementById('chk0'+i).disabled=true;
		
		document.getElementById('SP'+i).value = '';
		document.getElementById('AP'+i).value = '';
		document.getElementById('LP'+i).value = adv_amt;
		
	}
	
	document.getElementById('seq_no'+i).value = seq_no;
	document.getElementById('recv_dt'+i).value = payment_dt;
	document.getElementById('hid_currency'+i).value = currency;
	
	if(currency == '1'){
		document.getElementById('view_currency'+i).innerHTML = "INR";
	}else if(currency == '2'){
		document.getElementById('view_currency'+i).innerHTML = "USD";
	}
	
	if(currency == '2'){
		
		document.getElementById('exg_rt'+i).value = exg_rt;
		document.getElementById('exg_dt'+i).value = exg_dt;
		
		document.getElementById('exg_rt'+i).style.display="table-cell";
		document.getElementById('exg_dt'+i).style.display="table-cell";
	}else{
		
		document.getElementById('exg_rt'+i).value = "0";
		document.getElementById('exg_dt'+i).value = "";
		document.getElementById('exg_rt'+i).style.display="none";
		document.getElementById('exg_dt'+i).style.display="none";
	}
	if(type == 'M'){
		
		document.getElementById('rmk'+i).innerHTML = rmk;
		document.getElementById('sub'+i).innerHTML = "Modify";
		document.getElementById('subFlg'+i).value = "modify";
		document.getElementById('cr_dr_flag'+i).value = "N";
		if(document.getElementById('dr_cr_flag'+k).value!=""){
			
			if(document.getElementById('dr_cr_flag'+k).value=="C"){
				
				document.getElementById('rad_1'+i).checked = true;
				document.getElementById('crdrtd'+i).value = 'C';
				
			}else if(document.getElementById('dr_cr_flag'+k).value=="D"){
				
				document.getElementById('rad_2'+i).checked = true;
				document.getElementById('crdrtd'+i).value = 'D';
			}
		}else{
			document.getElementById('rad_1'+i).disabled = true;
			document.getElementById('rad_2'+i).disabled = true;
		}
	}else {
		
		document.getElementById('rad_1'+i).disabled = false;
		document.getElementById('rad_2'+i).disabled = false;
		document.getElementById('rmk'+i).innerHTML = "";
		document.getElementById('sub'+i).innerHTML = "Submit";
		document.getElementById('subFlg'+i).value = "generate_cr_dr_note";
		document.getElementById('cr_dr_flag'+i).value = "Y";
		
	}
}

function aprAdvPay(indx){
	
	var a = confirm("Do you want to Approve Advance Payment ?");
	if(a){
		
		document.getElementById('selInd').value=indx;
		document.forms[0].option.value="MultiApproveAdvancePayment";
		document.forms[0].submit();	
	}
 } 
 
function resetForm(){
	
	var	formId = document.forms[0].formId.value;
	var	modCd = document.forms[0].modCd.value;
	var	subModUrl = document.forms[0].subModUrl.value;
	var	modUrl = document.forms[0].modUrl.value;
	var cont_typ = document.forms[0].cont_typ.value;
	var cust_cd = document.forms[0].cust_cd.value;
	
	var url = modUrl+"?formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&cust_cd="+cust_cd+"&cont_typ="+cont_typ;
	location.replace(url);
}
</script>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.Databean_Advance_Payment" id="advPay" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<%
utilBean.init();
String  sysdate = utilBean.getGen_dt();

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210607

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");

String write_permission = (String)session.getAttribute("write_permission") ==null?"N":session.getAttribute("write_permission").toString();
String delete_permission = (String)session.getAttribute("delete_permission")==null?"N":session.getAttribute("delete_permission").toString();
String print_permission = (String)session.getAttribute("print_permission") ==null?"N":session.getAttribute("print_permission").toString();
String approve_permission = (String)session.getAttribute("approve_permission") ==null?"N":session.getAttribute("approve_permission").toString();
String audit_permission = (String)session.getAttribute("audit_permission") ==null?"N":session.getAttribute("audit_permission").toString();
String update_permission = (String)session.getAttribute("update_permission") ==null?"N":session.getAttribute("update_permission").toString();
// System.out.println("approve_permission---"+approve_permission);

String cont_typ = request.getParameter("cont_typ")==null?"S":request.getParameter("cont_typ");
String cust_cd = request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
String err_msg = request.getParameter("err_msg")==null?"":request.getParameter("err_msg");

advPay.setCallFlag("fetchAdvPayDtlMulti");
advPay.setCont_typ(cont_typ);
advPay.setCust_cd(cust_cd);

advPay.init();

Vector Vcust_cd = advPay.getVcust_cd();
Vector Vcust_nm = advPay.getVcust_nm();
Vector Vcust_abbr = advPay.getVcust_abbr();

 Vector vsn_no = advPay.getVsn_no();
 Vector vsn_rev_No = advPay.getVsn_rev_No(); 
 Vector vsn_tcq =  advPay.getVsn_tcq();
 Vector vsn_dcq =  advPay.getVsn_dcq();
 Vector vsn_stDt =  advPay.getVsn_stDt();
 Vector vsn_endDt =  advPay.getVsn_endDt();
 Vector vsn_name =  advPay.getVsn_name();
 Vector vsn_fcc_flag =  advPay.getVsn_fcc_flag();
 Vector Vflsa_no = advPay.getVflsa_no();
 Vector Vflsa_rev_no =  advPay.getVflsa_rev_no();
 Vector vadv_cnt = advPay.getVadv_cnt();
 
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
 Vector  pay_type_nm =  advPay.getPay_type_nm();
 Vector  pay_type_cd =  advPay.getPay_type_cd();
 Vector approved_dt  =  advPay.getApproved_dt();
 Vector approved_flag = advPay.getApproved_flag();
 Vector vadv_collection_flag = advPay.getVadv_collection_flag();
 Vector vadv_collection_currency= advPay.getVadv_collection_currency();
 Vector approved_amt = advPay.getApproved_amt();
 Vector dr_cr_flag = advPay.getDr_cr_flag();
 Vector Vseq_wise_total_amt =  advPay.getVseq_wise_total_amt();
%>

<body >
	<div class="tab-content">
		<!-- Default box -->
		<div class="box mb-0">
			<form  method="post" action="../servlet/Frm_Advance_Payment"  id="advForm">
			
				<input type="hidden" name="option" value="MultiAdvancePaymentEntry">
				<input type="hidden" name="modCd" value="<%=modCd%>">
				<input type="hidden" name="formId" value="<%=formId%>">
				<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
				<input type="hidden" name="modUrl" value="<%=modUrl%>">
				<input type="hidden" name="user_cd" value="<%=emp_cd%>">
				<input type="hidden" name="selInd" id="selInd" value="">
				<input type="hidden" name="selInInd" id="selInInd" value="">
				<input type="hidden" name="sysdate" id="sysdate" value="<%=sysdate %>" >
			
				<div class="box-header with-border" style="background-color:#ffe57f;">
			
					<div class="form-group col-md-2">
					 	<label for="">Contract Type</label>	
						<select class="form-control" name="cont_typ" id="cont_typ" onchange="relaodPage();">
							<option value="S" selected="selected">SN</option>
							<option value="L">LoA</option>
						</select>
						
						<script type="text/javascript">
								document.forms[0].cont_typ.value='<%=cont_typ%>';
						</script>
					 </div>
					 
					<div class="form-group col-md-2">
					 	<label for="">Customer</label>	
						<select class="form-control" name="cust_cd" id="cust_cd" onchange="relaodPage();">
							<option value="" selected="selected">--select--</option>
								<%for(int i = 0 ; i < Vcust_cd.size() ; i++){ %>
									<option value="<%=Vcust_cd.elementAt(i)%>"><%=Vcust_abbr.elementAt(i) %></option>
								<%} %>	
						</select>
						<%if(!cust_cd.equals("")){ %>
						<script type="text/javascript">
								document.forms[0].cust_cd.value='<%=cust_cd%>';
						</script>
						<%} %>
					 </div>
					 <div class="form-group col-md-1">
					 	 <label for="">&nbsp;</label>
					 	  <div class='input-group'>
					     	<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="fetchRpt();" >View List</button>
					     </div>
					 </div>
				</div>
				
				<div class="box-body table-responsive no-padding">
					<table class="table  table-bordered" >
						<tr>
							<th colspan="12" class="text-center info"><b>Available SN/LoA Notice(s)</b></th>
						</tr>
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
						
						<%
						int  k = 0;
						for(int i = 0 ; i < vsn_no.size(); i++) { %>
							<tr>
								<td colspan="12">
									<button class="accordion" type="button" onclick="setAccordian(this,this.id);" id="acrdBtn<%=i %>" >
										<table width="100%">
											<tr>	
												<th colspan="1">SN No.: <font color="blue"><%=vsn_no.elementAt(i) %></font> </th>	
												<th colspan="1">SN Rev. No.: <font color="blue"><%=vsn_rev_No.elementAt(i) %></font></th>
												<th colspan="1">FLSA No.: <font color="blue"><%=Vflsa_no.elementAt(i) %></font> </th>
												<th colspan="1">FLSA Rev. No.: <font color="blue"><%=Vflsa_rev_no.elementAt(i) %></font> </th>
												<th colspan="2">Start Dt.:<font color="blue"> <%=vsn_stDt.elementAt(i) %></font> </th>
												<th colspan="2">End Dt.: <font color="blue"><%=vsn_endDt.elementAt(i) %></font> </th>
												<th colspan="1">TCQ (MMBTU): <font color="blue"><%=vsn_tcq.elementAt(i) %></font> </th>
												<th colspan="1">Approved: <font color="blue"><%=vsn_fcc_flag.elementAt(i) %></font> </th>
												<th colspan="1">Approved AMT: <font color="blue"><%=approved_amt.elementAt(i) %></font> </th>
												<th align="right" ><span id="plusmins<%=i%>" class="fa fa-plus-circle"></span></th>
												
												<input type="hidden" name="fgsa_cd"  value="<%=Vflsa_no.elementAt(i) %>" >
												<input type="hidden" name="fgsa_revno"  value="<%=Vflsa_rev_no.elementAt(i) %>" >
												<input type="hidden" name="sn_cd"  value="<%=vsn_no.elementAt(i) %>" >
												<input type="hidden" name="sn_revno"  value="<%=vsn_rev_No.elementAt(i) %>" >
												<input type="hidden" name="sn_st_dt" id="sn_st_dt<%=i %>"  value="<%=vsn_stDt.elementAt(i) %>" >
												<input type="hidden" name="sn_end_dt" id="sn_end_dt<%=i %>"  value="<%=vsn_endDt.elementAt(i) %>" >
												
											</tr>	
										</table>
									</button>
									<div class="panel" id="panelDiv<%=i%>">
										<table  width="100%" class="table  table-bordered" >
										<tr style="background-color:#ffe57f;">
											<th class="text-center " colspan="2">Pay Recv Dt.<font color="red">*</font> </th>
											<th class="text-center" colspan="3">Advance Amt. Type <font color="red">*</font></th>
											<th class="text-center">Currency</th>
											<th class="text-center" >Exg. Rate <font color="red">*</font></th>
											<th class="text-center">Exg. Rate Dt. <font color="red">*</font></th>
											<th class="text-center" colspan="2">Remark</th>
											<th colspan="2" class="text-center" style="vertical-align: middle;"><input type="radio" name="rad<%=i %>" id="rad_1<%=i%>" onclick="document.getElementById('crdrtd<%=i%>').value='C';" disabled="disabled"> Credit
												&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="rad<%=i %>" id="rad_2<%=i%>" onclick="document.getElementById('crdrtd<%=i%>').value='D';" disabled="disabled"> Debit
												<input type="hidden" name="crdrtd" value="" id="crdrtd<%=i%>">
											</th>
										</tr>
										
										<tr align="center" style="background-color:#ffe57f;">
											<td colspan="2">
												<input class= "form-control datepick"  type="text" name="recv_dt" id="recv_dt<%=i %>" value=""  size="11" placeholder="dd/mm/yyyy" onchange="validateDate(this);compareDate(this.value,'<%=i %>');" autocomplete="off">
												<input type="hidden" name="seq_no" id="seq_no<%=i %>" value="">
											</td>
											 <td class="text-center">
												<input type="radio" name="chk<%=i %>" id="chk0<%=i %>"  onclick="setVal('SP','<%=i %>');" >&nbsp;Security: &nbsp;&nbsp;&nbsp;<input type="text" class="text-right" name="SP" id="SP<%=i %>" value="" size="10" onkeyup="checkNum(this);" readonly="readonly"  >
											</td>
											<td>	
												<input type="radio" name="chk<%=i %>" id="chk1<%=i %>" onclick="setVal('LP','<%=i %>');" >&nbsp;lump-sum:  <input type="text" class="text-right" name="LP" id="LP<%=i %>" value="" size="10" onkeyup="checkNum(this);" readonly="readonly"  >
											</td>
											<td>	
												<input type="radio" name="chk<%=i %>" id="chk2<%=i %>"  onclick="setVal('AP','<%=i %>');" >&nbsp;Advance: &nbsp;&nbsp;<input type="text" class="text-right" name="AP" id="AP<%=i %>" value="" size="10" onkeyup="checkNum(this);" readonly="readonly"  >
											</td> 
											<td>
												<span id="view_currency<%=i%>"> 
												<%if(vadv_collection_currency.elementAt(i).equals("1")){ %>
													INR
												<%}else if(vadv_collection_currency.elementAt(i).equals("2")){ %>
													USD
												<%} %>
												</span>
												<input type="hidden" name="hid_currency" id="hid_currency<%=i %>"  value="<%=vadv_collection_currency.elementAt(i) %>">
											</td>
											 <td>
												<input type="text" class="text-right" name="exg_rt" id="exg_rt<%=i %>" value="0" size="10" <%if(vadv_collection_currency.elementAt(i).equals("1")) {%> style="display: none;" <%} %> onkeyup="checkNum(this);">
											</td>
											<td>
												<input type="text" name="exg_dt" id="exg_dt<%=i %>" value="" size="11" class="datepick" placeholder="dd/mm/yyyy" <%if(vadv_collection_currency.elementAt(i).equals("1")) {%> style="display: none;" <%} %>>
											</td> 
											<td colspan="2">
												<textarea rows="3" cols="30" name="rmk" id="rmk<%=i %>"></textarea>
											</td>
											<td align="center" colspan="2" style="vertical-align: middle;">
												<button type="button"  class="btn btn-success" id="sub<%=i %>"
												<%if(write_permission.equalsIgnoreCase("Y")) { %>
													<%if(vadv_collection_flag.elementAt(i).equals("Y")){ %>
														onclick="doSubmit('<%=i %>');"
													<%}else{%>
														disabled="disabled" title="You have to enable advance collection from SN/LoA master!!"
													<%} %>
												<%}else{%>
													disabled="disabled" title="No Access Rights!!"
												 <%} %>
												>Submit</button>
												
												<button type="button" class="btn btn-warning" name="reset" onclick="resetForm();">Reset</button>
											</td> 
										</tr>
										<%
// 										System.out.println("vadv_cnt.elementAt(i)---"+vadv_cnt.elementAt(i));
										for(int j = 0 ; j < Integer.parseInt(vadv_cnt.elementAt(i).toString()); j++){ %>
												<%if(j==0) { %>
													<tr class="info" style="font-weight: bold;">
														<td colspan="1" width="10%" class="text-left">Seq.No.</td>
														<td colspan="1" width="10%" class="text-center">Pay Recv Dt.</td>
														<td colspan="1" width="10%" class="text-right">Advance Amt.</td>
														<td colspan="1" width="5%" class="text-center">Currency</td>
														<td colspan="1" width="5%" class="text-right">Exg. Rate</td>
														<td colspan="1" width="10%" class="text-center">Exg. Rate Dt.</td>
														<td colspan="1" width="10%" class="text-left">Payment Type.</td>
														<td colspan="1" width="10%" class="text-left">Ent. By</td>
														<td colspan="1" width="10%" class="text-center">Ent. Dt.</td>
														<td colspan="2" width="10%" class="text-left">Remarks</td>
														<td colspan="1" width="10%" class="text-center" >Status</td> 
													</tr>
												<%} %>
												
											<%
											String crdr = "";
											if(!dr_cr_flag.elementAt(k).toString().equalsIgnoreCase("")){ 
												if(dr_cr_flag.elementAt(k).toString().equalsIgnoreCase("C")){ 
													crdr = "Credit";
												}else if(dr_cr_flag.elementAt(k).toString().equalsIgnoreCase("D")){ 
													crdr = "Debit";
												}
											%>
												<tr style="background-color: #f8fbd6;" title="<%=crdr%> Note">								
											<%}else{ %>
												<tr>
											<%} %>
												
													<td colspan="1" width="10%" class="text-center">
													<%if(!approved_flag.elementAt(k).toString().equalsIgnoreCase("Y")) {%>
														<i class="fa fa-pencil-square-o text-left" style="font-size:18px;color:blue" title="Click to modify" onclick="modifyPayment('<%=k%>','<%=pay_type_cd.elementAt(k)%>','<%=i%>','M');"></i>&nbsp;&nbsp;&nbsp;&nbsp;
													<%} %>
													<%=seq_no.elementAt(k) %>
														<input type="hidden" value="<%=seq_no.elementAt(k) %>" name="mod_seq_no" id="mod_seq_no<%=k%>">
														<input type="hidden" name="fgsa_cd_mod"  value="<%=Vflsa_no.elementAt(i) %>" >
														<input type="hidden" name="fgsa_revno_mod"  value="<%=Vflsa_rev_no.elementAt(i) %>" >
														<input type="hidden" name="sn_cd_mod"  value="<%=vsn_no.elementAt(i) %>" >
														<input type="hidden" name="sn_revno_mod"  value="<%=vsn_rev_No.elementAt(i) %>" >
													&nbsp;&nbsp;
													<%if(approved_flag.elementAt(k).toString().equalsIgnoreCase("Y") && dr_cr_flag.elementAt(k).toString().equalsIgnoreCase("")) {%>
														<font size="1" > <a href="#" onclick="modifyPayment('<%=k%>','<%=pay_type_cd.elementAt(k)%>','<%=i%>','crdr');"> <u><b>CR/DR</b></u></a> </font>
													<%} %>
													<input type = "hidden" name = "dr_cr_flag" id = "dr_cr_flag<%=k %>" value="<%=dr_cr_flag.elementAt(k)%>">
													</td>
													
													<td colspan="1" width="10%" class="text-center"><%=payment_dt.elementAt(k) %>
														<input type="hidden" value="<%=payment_dt.elementAt(k) %>" name="mod_payment_dt" id="mod_payment_dt<%=k%>">
													</td>
													
													<td colspan="1" width="10%" class="text-right"><%=adv_amt.elementAt(k)%>
														<input type="hidden" value="<%=adv_amt.elementAt(k) %>" name="mod_adv_amt" id="mod_adv_amt<%=k%>">
														<input type="hidden" value="<%=Vseq_wise_total_amt.elementAt(k) %>" name="seq_wise_total_amt" id="seq_wise_total_amt<%=k%>">
													</td>
													<td colspan="1" width="5%" class="text-center">
														<%if(currency.elementAt(k).toString().equals("1")){ %>
															INR
														<%}else if(currency.elementAt(k).toString().equals("2")){ %>
															USD
														<%} %>
														<input type="hidden" value="<%=currency.elementAt(k) %>" name="mod_currency" id="mod_currency<%=k%>">
													</td>
													<td colspan="1" width="5%" class="text-right"><%=exg_rt.elementAt(k) %>
														<input type="hidden" value="<%=exg_rt.elementAt(k) %>" name="mod_exg_rt" id="mod_exg_rt<%=k%>">
													</td>
													<td colspan="1" width="10%" class="text-center"><%=exg_dt.elementAt(k) %>
														<input type="hidden" value="<%=exg_dt.elementAt(k) %>" name="mod_exg_dt" id="mod_exg_dt<%=k%>">
													</td>
													<td colspan="1" width="10%" class="text-left"><%=pay_type_nm.elementAt(k) %>
														<input type="hidden" value="<%=pay_type_cd.elementAt(k) %>" name="pay_typ_cd" id="pay_typ_cd<%=k%>">
													</td>
													<td colspan="1" width="10%" class="text-left"><%=ent_by_nm.elementAt(k) %>
														<input type="hidden" value="<%=ent_by_cd.elementAt(k) %>" name="mod_ent_by_cd" id="mod_ent_by_cd<%=k%>">
													</td>
													<td colspan="1" width="10%" class="text-center"><%=ent_dt.elementAt(k) %>
														<input type="hidden" value="<%=ent_dt.elementAt(k) %>" name="mod_ent_dt" id="mod_ent_dt<%=k%>">
													</td>
													<td colspan="2" width="10%" class="text-left"><%=remark.elementAt(k) %>
														<input type="hidden" name="hid_mod_rmk" id= "hid_mod_rmk<%=k %>" value="<%=remark.elementAt(k) %>">
													</td>
													<td colspan="1" width="10%" class="text-center" >
														<%if(approved_flag.elementAt(k).toString().equalsIgnoreCase("Y")) {%>
															<button class="btn btn-default" type="button" name="Approve" value="Approve" disabled="disabled" title="Approved On : <%=approved_dt.elementAt(k) %>"><%=crdr %> Approved</button>
														<%}else{%>
															<button class="btn btn-primary " type="button" name="Approve" value="Approve" 
															<%if(approve_permission.equalsIgnoreCase("Y")){ %>
																onclick="aprAdvPay('<%=k%>');"
															<%}else{ %>
																disabled="disabled" title="You have no access rights!!"
															<%} %>
															><%=crdr %> Approve</button>
														<%} %>
													</td> 
												</tr>
										<% k++; } %>
										</table>
									</div>	
								</td>
							</tr>
							</table>
							
							<input type="hidden" name="pay_typ" id="pay_typ<%=i %>" value="" >
							<input type="hidden" name="subFlg" id="subFlg<%=i %>" value="entry">
							<input type="hidden" name="sel_amt" id="sel_amt<%=i %>" value="0">
							<input type="hidden" name="cr_dr_flag" id="cr_dr_flag<%=i %>" value="">
						<%} %>
				</div>	
			</form>
		</div>
	</div>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
		
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