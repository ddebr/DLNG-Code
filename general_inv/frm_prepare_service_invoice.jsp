<!DOCTYPE html>
<%@ page import="java.util.Vector" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG | Service Invoice Preparation </title>

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

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<script type="text/javascript" src="../js/bootstrap.min.js"></script>

<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<link rel="stylesheet" href="../css/tlu.css">

	<style>
	/* #loading {
	   width: 100%;
	   height: 100%;
	   top: 0;
	   left: 0;
	   position: fixed;
	   display: block;
	   opacity: 0.7;
	   background-color: #fff;
	   z-index: 99;
	   text-align: center;
	} */
	
	/* #loading-image {
	  position: absolute;
	  top: 50%; /*window.innerHeight/2; /*300px;*/
	  left: 50%; /*window.innerWidth/2; /*240px;*/
	  z-index: 100;
	} */
	</style>
	
	 <style type="text/css">
	table tr{
/* 	   height: 50px; */
	   color: black;
	}
	</style> 
	
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

function copyPaste(sz,id){
	
	var tempId = id;
	for(var i = 0 ; parseFloat(sz); i++){
		if(i == 0){
			id = id+i;
		}else{
			
			if(tempId == 'km'){
				
				document.getElementById('km'+i).value = document.getElementById(id).value;
				calcInrKm(sz);
				
			}else if(tempId == 'lumpsum'){
				
				document.getElementById('lumpsum'+i).value = document.getElementById(id).value;
				calcLump(sz);
			}
		}
	}
}

function checkNum(obj){
	
	var regex  = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var val = obj.value;
	if(val.match(regex)){
		
	}else{
// 		alert('Not Allowed');
		obj.value= "";
		
	}
}

function refreshParant(year,month,bill_cycle,msg,error_msg,modUrl,modCd,formId,subModUrl){
	window.opener.refreshParant(year,month,bill_cycle,msg,error_msg,modUrl,modCd,formId,subModUrl)
	window.close();
}

function doSubmit () {
	
	var msg="";
	var flag=true;
	
	var calcBase = document.getElementById('calcBase').value;
	var invDt = document.getElementById('datetimepicker1').value;
	var dueDt = document.getElementById('datetimepicker2').value;
	var tax_structure = document.getElementById('tax_structure').value;
	var rowno = parseInt(document.forms[0].rowno.value);
	var inv_seq_no = document.getElementById('inv_seq_no').value;
	
	var cust_st_no = document.getElementById('cust_st_no').value;
	var cont_perc = document.getElementById('cont_perc').value;
	var plant_state_cd = document.getElementById('plant_state_cd').value;
	var cust_pan_no = document.getElementById('cust_pan_no').value;
	var truckInvSz = document.getElementById('truckInvSz').value;
	var contact_person_cd  = document.getElementById('contact_person_cd').value;
	
	if(inv_seq_no == '' || inv_seq_no == ' '){
		msg = "Invoice Number cannot be blank ";
	}
	if(invDt == '' || invDt == ' '){
		msg+= "\nInvoice Date cannot be blank ";
	}
	if(dueDt == '' || dueDt == ' '){
		msg+= "\nInvoice Due Date cannot be blank ";
	}
	if(tax_structure == '' || tax_structure == ' '){
		msg+= "\nPlease select Tax Structure";
	}
	if(calcBase == '' || calcBase == ' '){
		msg+= "\nPlease select Calculation Rate";
	}
	
	if(cust_st_no == '' || cust_st_no == ' '){
// 		msg+= "\nCustomer Plant GSTIN No. cannot be blank";
	}
	if(cont_perc == '' || cont_perc == ' '){
// 		msg+= "\nCustomer Contact Person cannot be blank";
	}
	if(plant_state_cd == '' || plant_state_cd == ' '){
		msg+= "\nCustomer Plant State Code cannot be blank";
	}
	if(cust_pan_no == '' || cust_pan_no == ' '){
		msg+= "\nCustomer PAN No. cannot be blank";
	}
	if(parseFloat(truckInvSz) == '0'){
		msg+= "\nTruck Invoice Details Not Available";
	}
	if(contact_person_cd == '' || contact_person_cd == ' '){
		msg+= "\nContact Person cannot be blank";
	}
	
	var indx = 0;
	
	for(var i = 0 ; i < rowno ; i++){
		indx++;
		if(document.getElementById('sac_cd'+i).value=='' || document.getElementById('sac_cd'+i).value==' '){
			msg+="\nSAC CODE cannot be blank at row no."+indx;
		}
		if(document.getElementById('line_item'+i).value=='' || document.getElementById('line_item'+i).value==' '){
			msg+="\nLINE ITEM cannot be blank at row no."+indx;
		}
		if(document.getElementById('qty'+i).value=='' || document.getElementById('qty'+i).value==' '){
			msg+="\nQuantity cannot be blank at row no."+indx;
		}
		if(document.getElementById('rate'+i).value=='' || document.getElementById('rate'+i).value==''){
			msg+="\nRATE cannot be blank at row no."+indx;
		}
		if(document.getElementById('amt'+i).value=='' || document.getElementById('amt'+i).value==' '){
			msg+="\nAMOUNT cannot be blank at row no."+indx;
		}
	}
	if(msg!=""){
		alert(msg)
	}else{
		var a = confirm('Do You Want To Generate Invoice ? ')
		if(a){
			document.forms[0].submit();		
		}
	}
}

function blockSpecialChar(e){
// 	alert('in')
        var k;
        document.all ? k = e.keyCode : k = e.which;
        return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
        }
function chkdue_dt()   
{
	var payduedt=document.forms[0].due_dt.value;
	var inv_dt=document.forms[0].inv_dt.value;
	
	if(inv_dt!='' && payduedt!=''){
// 		alert(inv_dt+"---"+payduedt)
		var value = compareDate(inv_dt,payduedt);
	  	if(value==1)
	  	{
	    	alert("Payment due Date Should Be Greater Than Invoice Date !!!");
	    	document.forms[0].due_dt.value ="";
	    	return false;
	  	}
	}else if(inv_dt=='' && payduedt!='') {
		
		alert("First enter Invoice Date !!!");
    	document.forms[0].due_dt.value ="";
    	return false;
	}
	
	var period_start_dt = document.getElementById('period_start_dt').value;
	var period_end_dt = document.getElementById('period_end_dt').value;
	var value1 = compareDate(inv_dt,period_start_dt);
// 	var value2 = compareDate(inv_dt,period_end_dt);
// 	alert(value1)
	/* if(parseFloat(value1) == 2 || parseFloat(value2) == 1){
		document.forms[0].inv_dt.value ="";
		alert("Invoice Date Must Be Between Selected Period : "+period_start_dt+" - "+period_end_dt);
		return false;
	} */
	
	if(parseFloat(value1) == 2 ){
		document.forms[0].inv_dt.value ="";
		alert("Invoice Date Must Be Equal or Higher than Period : "+period_start_dt);
		return false;
	} 
	
	var chkFlg = document.getElementById('chkFlg').value;
	var cont_st_dt = document.getElementById('cont_st_dt'+chkFlg).value;
	var cont_end_dt = document.getElementById('cont_end_dt'+chkFlg).value;
	
	var value3 = compareDate(inv_dt,cont_st_dt);
	var value4 = compareDate(inv_dt,cont_end_dt);
// 	alert(value4)
	if(parseFloat(value3) == 2 || parseFloat(value4) == 1){
		document.forms[0].inv_dt.value ="";
		alert("Invoice Date Must Be Between Contract Period : "+cont_st_dt+" - "+cont_end_dt);
		return false;
	}
}

function fetchSupDtl(){
		var modCd = document.forms[0].modCd.value;
		var formId = document.forms[0].formId.value;
		var subModUrl = document.forms[0].subModUrl.value;
		var modUrl = document.forms[0].modUrl.value;
		var month = document.forms[0].month.value;
		var year = document.forms[0].year.value;
		var bill_cycle = document.forms[0].bill_cycle.value;
		
		var cust_cd = document.forms[0].cust_cd.value;
		var supp_cd = document.forms[0].supp_cd.value;
		var cust_plant_cd = document.forms[0].cust_plant_cd.value;
		
		
		var url = "";
		url = "../general_inv/frm_prepare_service_invoice.jsp?supp_cd="+supp_cd+"&cust_cd="+cust_cd+"&cust_plant_cd="+cust_plant_cd+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle;	
// 		alert(url)
		location.replace(url);
}
function fetchInvDtl(sz){
	
	var mapping_id = "";
	var rate="";
	var flag = false;
	var ind="";
	var chkFlg="";
	var inr_km="";
	var inr_mmbtu="";
	var lumpsumFlg = "";
	var period_end_dt = document.getElementById('period_end_dt').value;
	var alwInvGen = document.getElementById('alwInvGen').value;
	var cont_end_dt = "";
	
	for (var i = 0 ; i < parseFloat(sz); i++){
		if(document.getElementById('chk'+i).checked == true){
// 			alert(document.getElementById('chk'+i).checked)
			chkFlg=i;
			flag = true;
			mapping_id = document.getElementById('cont_type'+i).value+"-"+document.getElementById('agr_no'+i).value+"-"+document.getElementById('agr_rev_no'+i).value+"-"+document.getElementById('cont_no'+i).value+"-"+document.getElementById('cont_rev_no'+i).value;
			inr_km = document.getElementById('inr_km'+i).value;
			inr_mmbtu = document.getElementById('inr_mmbtu'+i).value;
			lumpsumFlg = document.getElementById('lumpsumFlg'+i).value;
			cont_end_dt = document.getElementById('cont_end_dt'+i).value;
		}		
	}
	if(flag == false){
		alert('Please select atleast one contract');
	}else{
		
		 var value = compareDate(cont_end_dt,period_end_dt);
// 		 alert(value)
		if(value == 2 || value == 0){ //date1 < date2 == 2
	  		
	  		flag = true;
	  	}else if(alwInvGen == "Y"){
	  		
	  		flag = true;
	  	}else {
	  		flag = false;
	  	}
// 	  	alert(flag)
		if(flag == false){
			
			alert("Invoice Generation is not allowed for the selected contract ");	
			return false;
		} 
		var modCd = document.forms[0].modCd.value;
		var formId = document.forms[0].formId.value;
		var subModUrl = document.forms[0].subModUrl.value;
		var modUrl = document.forms[0].modUrl.value;
		var month = document.forms[0].month.value;
		var year = document.forms[0].year.value;
		var bill_cycle = document.forms[0].bill_cycle.value;
		
		var cust_cd = document.forms[0].cust_cd.value;
		var supp_cd = document.forms[0].supp_cd.value;
		var cust_plant_cd = document.forms[0].cust_plant_cd.value;
		
		
		var url = "";
		url = "../general_inv/frm_prepare_service_invoice.jsp?supp_cd="+supp_cd+"&cust_cd="+cust_cd+
				"&cust_plant_cd="+cust_plant_cd+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+
				"&modUrl="+modUrl+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&mapping_id="+mapping_id+
				"&inr_km="+inr_km+"&inr_mmbtu="+inr_mmbtu+"&chkFlg="+chkFlg+"&lumpsumFlg="+lumpsumFlg;	
// 		alert(url)
		location.replace(url);
	}
}

function calcInrKm(sz){
	
	var hid_inr_km = document.getElementById('hid_inr_km').value;
	var total_km_inr = 0;
	for (var i = 0 ; i < parseFloat(sz); i++){
		if(document.getElementById('chkBox'+i).checked){
			var km = document.getElementById('km'+i).value;
			if(km==''){
				km = 0;
// 				document.getElementById('km'+i).value = 0;
			}
			if(parseFloat(hid_inr_km) > 0){
				document.getElementById('inv_km_inr'+i).value = parseFloat(km) *  parseFloat(hid_inr_km);
				total_km_inr+= Math.round(parseFloat(km) *  parseFloat(hid_inr_km),2);
			}
		}	
	}
	document.getElementById('total_km_inr').value = total_km_inr;

}

function calcLump(sz){
	
	var tot_lumpSum = 0;
	for (var i = 0 ; i < parseFloat(sz); i++){
		var lumpAmt = document.getElementById('lumpsum'+i).value;
		if(parseFloat(lumpAmt) > 0){
			
			if(!document.getElementById('chkBox'+i).checked){
				lumpAmt = 0;
			}
			tot_lumpSum+= parseFloat(lumpAmt);
		}
	}
	document.getElementById('total_ls_inr').value = tot_lumpSum;
}
function calcMmbtu(sz){// for INR/MMBtu
	
	var total_qty = 0;
	var total_amt = 0;
	
	for (var i = 0 ; i < parseFloat(sz); i++){
		
		var truckQty = document.getElementById('truckInvQty'+i).value;
		var servInvQtyAmt = document.getElementById('servInvQtyAmt'+i).value;
			
		if(!document.getElementById('chkBox'+i).checked){
			truckQty = 0;
			servInvQtyAmt = 0;
		}
		
		total_qty+= parseFloat(truckQty);
		total_amt+= parseFloat(servInvQtyAmt);
	}
// 	alert(document.getElementById('hid_mmbtu_qty'))
	document.getElementById('hid_mmbtu_qty').value = total_qty;
	document.getElementById('mmbtu_qty').innerHTML = total_qty;
	document.getElementById('total_mmbtu_inr').value = total_amt;
} 


function setLineItem(){
	
	var contSz = document.getElementById('contSz').value;
	var invSz = document.getElementById('invSz').value;
	var calcBase = document.getElementById('calcBase').value;
	var invDt = document.getElementById('datetimepicker1').value;
	var dueDt = document.getElementById('datetimepicker2').value;
	var tax_structure = document.getElementById('tax_structure').value;
	var rowno = parseInt(document.forms[0].rowno.value);
	var lsFlg = document.forms[0].lsFlg.value;
	
	var mapping_id = "";
	var rate="";
	var flag = false;
	var ind="";
	var chkFlg="";
	var inr_km="";
	var inr_mmbtu="";
	var inr_km_str="";
	var km_str="";
	var total_km_inr = 0;
	var lumpSum_str = "";
	var msg = "";
	var total_ls_inr = "";
	var inv_mmbtu_inr = "";
	var total_mmbtu_inr = 0;
	var inr_mmbtu_str = "";
	var qty_mmbtu_str = "";
	 
	
	for (var i = 0 ; i < parseFloat(contSz); i++){
		if(document.getElementById('chk'+i).checked == true){
// 			alert(document.getElementById('chk'+i).checked)
			chkFlg=i;
			flag = true;
			mapping_id = document.getElementById('cont_type'+i).value+"-"+document.getElementById('agr_no'+i).value+"-"+document.getElementById('agr_rev_no'+i).value+"-"+document.getElementById('cont_no'+i).value+"-"+document.getElementById('cont_rev_no'+i).value;
			inr_km = document.getElementById('inr_km'+i).value;
			inr_mmbtu = document.getElementById('inr_mmbtu'+i).value;
		}		
	}
	if(flag == false){
		msg = "Please select atleast one contract";
	}
	var chkBoxFlgStr = "";
	for (var i = 0 ; i < parseFloat(invSz); i++){
		chkBoxFlgStr+=document.getElementById('chkBoxFlg'+i).value+"@@"; 
	}
	
	if(!chkBoxFlgStr.includes("Y")){
		msg+= "Please Tick atleast one Checkbox of The Truck";
// 		flag = false;
	}
	
	if(msg!=""){
		alert(msg);
		
	}else{
		
		var modCd = document.forms[0].modCd.value;
		var formId = document.forms[0].formId.value;
		var subModUrl = document.forms[0].subModUrl.value;
		var modUrl = document.forms[0].modUrl.value;
		var month = document.forms[0].month.value;
		var year = document.forms[0].year.value;
		var bill_cycle = document.forms[0].bill_cycle.value;
		
		var cust_cd = document.forms[0].cust_cd.value;
		var supp_cd = document.forms[0].supp_cd.value;
		var cust_plant_cd = document.forms[0].cust_plant_cd.value;
	
// 		alert('in')
		if(calcBase == '1'){ // for INR/MMBtu
			var hid_inr_mmbtu = document.getElementById('hid_inr_mmbtu').value; 
			for (var i = 0 ; i < parseFloat(invSz); i++){
				if(document.getElementById('chkBox'+i).checked){
					var truckQty = document.getElementById('truckInvQty'+i).value;
					var servInvQtyAmt = document.getElementById('servInvQtyAmt'+i).value;
					
					if(truckQty==''){
						truckQty = 0;
					}
					if(parseFloat(hid_inr_mmbtu) > 0 ){
						
						total_mmbtu_inr+=parseFloat(servInvQtyAmt);
						inr_mmbtu_str+=document.getElementById('servInvQtyAmt'+i).value+"@@";
						qty_mmbtu_str+=truckQty+"@@";
						
					}else{
						
						inr_mmbtu_str+="0@@";
						qty_mmbtu_str+="0@@";
					}
				}else{
					
					inr_mmbtu_str+="0@@";
					qty_mmbtu_str+="0@@";
				}
			}
			document.getElementById('total_mmbtu_inr').value = total_mmbtu_inr;
		}
// 		alert('out')
	if(calcBase == '2'){ // for INR/KM
		var hid_inr_km = document.getElementById('hid_inr_km').value; 
		for (var i = 0 ; i < parseFloat(invSz); i++){
			if(document.getElementById('chkBox'+i).checked){
				var km = document.getElementById('km'+i).value;
				if(km==''){
					km = 0;
				}
				if(parseFloat(hid_inr_km) > 0 ){
					
					document.getElementById('inv_km_inr'+i).value = parseFloat(km) *  parseFloat(hid_inr_km);
					total_km_inr = parseFloat(document.getElementById('inv_km_inr'+i).value) + parseFloat(total_km_inr);
					inr_km_str+=document.getElementById('inv_km_inr'+i).value+"@@";
					km_str+=km+"@@";
					
				}else{
					
					inr_km_str+="0@@";
					km_str+=km+"0@@";
				}
			}else{
				
				inr_km_str+="0@@";
				km_str+="0@@";
			}
		}
		document.getElementById('total_km_inr').value = total_km_inr;
	}
	
	if(calcBase == '3'){ // for Lump sum
		for (var i = 0 ; i < parseFloat(invSz); i++){
			if(document.getElementById('chkBox'+i).checked){
				var lumpAmt = document.getElementById('lumpsum'+i).value;
				if(parseFloat(lumpAmt) > 0 ){
					
					lumpSum_str+=document.getElementById('lumpsum'+i).value+"@@";
					
				}else{
					flag = false;
					lumpSum_str+="0@@";
					msg+="Lump-Sum Amount cannot be blank!";
					break;
				}
			}else{
				lumpSum_str+="0@@";
			}	
		}
		total_ls_inr = document.getElementById('total_ls_inr').value;
	}
	
	//for line item
	var sacStr = "";
	var itemStr = "";
	var qtyStr = "";
	var rateStr = "";
	var amtStr = "";
// 	alert(rowno)
	for(var i = 0 ; i < rowno ; i++){
		
		if(document.getElementById('sac_cd'+i).value != '' && document.getElementById('sac_cd'+i).value != ' '){
			sacStr+=document.getElementById('sac_cd'+i).value+"@@";
		}else{
			sacStr+="-@@";
		}
		if(document.getElementById('line_item'+i).value != '' && document.getElementById('line_item'+i).value != ' '){
			itemStr+=document.getElementById('line_item'+i).value+"@@";
		}else{
			itemStr+="-@@";
		}
		
		if(document.getElementById('qty'+i).value != '' && document.getElementById('qty'+i).value != ' '){
			qtyStr+=document.getElementById('qty'+i).value+"@@";
		}else{
			qtyStr+="-@@";
		}
		
		if(document.getElementById('rate'+i).value != '' && document.getElementById('rate'+i).value != ' '){
			rateStr+=document.getElementById('rate'+i).value+"@@";
		}else{
			rateStr+="-@@";
		}
		
		if(document.getElementById('amt'+i).value != '' && document.getElementById('amt'+i).value != ' '){
			amtStr+=document.getElementById('amt'+i).value+"@@";
		}else{
			amtStr+="-@@";
		}
	}
// 	alert(qtyStr)
	var url = "";
	if(msg==""){
		url = "../general_inv/frm_prepare_service_invoice.jsp?supp_cd="+supp_cd+"&cust_cd="+cust_cd+"&cust_plant_cd="+cust_plant_cd+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&modUrl="+modUrl+
				"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&mapping_id="+mapping_id+"&inr_km="+inr_km+"&inr_mmbtu="+inr_mmbtu+"&chkFlg="+chkFlg+
				"&inr_km_str="+inr_km_str+"&km_str="+km_str+"&total_km_inr="+total_km_inr+"&invDt="+invDt+"&dueDt="+dueDt+"&calcBase="+calcBase+"&tax_structure="+tax_structure+
				"&sacStr="+sacStr+"&itemStr="+itemStr+"&qtyStr="+qtyStr+"&rateStr="+rateStr+"&amtStr="+amtStr+"&rowno="+rowno+"&ls_str="+lumpSum_str+"&total_ls_inr="+total_ls_inr+"&lumpsumFlg="+lsFlg+"&chkBoxFlgStr="+chkBoxFlgStr+"&inr_mmbtu_str="+inr_mmbtu_str+"&qty_mmbtu_str="+qty_mmbtu_str;	
// 		alert(url)
		location.replace(url);
	}else{
		alert(msg);
		
	}
	}
}
var data="";
function addnewdesc(tax_descsz,tax_desc)
{
	var tab_name = document.getElementById("tabId");
    var r = parseInt(document.forms[0].rowno.value);
    var sac_code = document.getElementById("sac_code").value;
//   	var r1 = parseInt(document.forms[0].rowno.value);
  	var tr = document.createElement("tr");
  	tr.id = 'row'+r;
  	
  	var td01 = document.createElement("TD");
  	td01.style.width = "8%";
//   	td01.colSpan = "1";
	var input01 = document.createElement("input")
	input01.name = "sac_cd";
	input01.id = "sac_cd"+r;
	input01.type = "text";
	input01.readOnly = true;
	input01.value = sac_code;
	input01.className = 'form-control text-center';
	
	var td02 = document.createElement("TD");
	td02.id="td02";
	td02.style.width = "35%";
// 	td02.colSpan = "5";
// 	td02.setAttribute("colSpan","5");
// 	document.getElementById("td02").colSpan = "5";
//   	td02.colSpan="5";
  	
	var input02 = document.createElement("input")
	input02.name = "line_item";
  	input02.id = "line_item"+r;
  	input02.type = "text";
//   	input02.size = "70";
  	input02.className = 'form-control';
  	input02.setAttribute("onkeypress","return blockSpecialChar(event);");
  	
  	var td03 = document.createElement("TD");
  	td03.style.width = "10%";
//   	td03.colSpan="2";
	var input03 = document.createElement("input")
	input03.name = "qty";
  	input03.id = "qty"+r;
  	input03.type = "number";
//   	input03.size = "5";
  	input03.className = 'form-control text-right';
  	input03.setAttribute("onkeyup","checkNum(this);");
  	input03.setAttribute("onchange","caclInvAmt();");
//   	alert(input03.id)
  	var td04 = document.createElement("TD");
  	td04.style.width = "10%";
//   	td04.colSpan="2";
	var input04 = document.createElement("input")
	input04.name = "rate";
  	input04.id = "rate"+r;
  	input04.type = "number";
//   	input04.size = "10";
  	input04.className = 'form-control text-right';
  	input04.setAttribute("onkeyup","checkNum(this);");
  	input04.setAttribute("onchange","caclInvAmt();");
  	
  	var td05 = document.createElement("TD");
  	td05.style.width = "10%";
//   	td05.colSpan="2";
	var input05 = document.createElement("input")
	input05.name = "amt";
  	input05.id = "amt"+r;
  	input05.type = "text";
//   	input05.size = "10";
  	input05.readOnly = true;
  	input05.className = 'form-control text-right';
	
  	var td06 = document.createElement("TD");
// 	td06.style.display="none";
	td06.id ='delTd'+r;
	td06.style.width = "5%";
	var input06 = document.createElement("button")
	input06.type = "button";
	input06.className='btn btn-sm btn-danger btn-flat';
	input06.title="Click here to Delete Row"
	input06.setAttribute("onclick","removeRow('"+tr.id+"');");
	var i=document.createElement("I");
	i.className="fa fa-fw fa-minus-circle font-16";
	input06.appendChild(i);
	
	td01.appendChild(input01);
	td02.appendChild(input02);
	td03.appendChild(input03);
	td04.appendChild(input04);
	td05.appendChild(input05);
	td06.appendChild(input06);
	
	tr.appendChild(td01);
	tr.appendChild(td02);
	tr.appendChild(td03);
	tr.appendChild(td04);
	tr.appendChild(td05);
	tr.appendChild(td06);
	
	tab_name.appendChild(tr);
	
	var rows = parseInt(document.forms[0].rowno.value);
	rows=rows+1;
	document.forms[0].rowno.value=rows;
}

function removeRow(rowid){
// 	alert(rowid)
	var row = document.getElementById(rowid);
    row.parentNode.removeChild(row);
	document.forms[0].rowno.value = parseInt(document.forms[0].rowno.value) - 1;
	
	caclInvAmt();
}

function caclInvAmt(){
	
	var rows = parseInt(document.forms[0].rowno.value);
	var gross_amt = 0;
	var tax_structure = document.getElementById('tax_structure').value;
	var tax_sz = document.getElementById('tax_sz').value;
	var totalTax = 0;
	var total_qty = 0;
	
	
	if(rows > 1){
		for(var i = 0 ; i < rows ; i++){
	// 		alert(document.getElementById('qty'+i).value)
			if(document.forms[0].qty[i].value == ''){
				document.forms[0].qty[i].value = 0;
			}
			if(document.forms[0].rate[i].value ==''){
				document.forms[0].rate[i].value = 0;
			}
			if(document.forms[0].amt[i].value){
				document.forms[0].amt[i].value = 0;
			}
	// 		alert(document.getElementById('qty'+i).value+"--"+document.getElementById('rate'+i).value)
			document.forms[0].amt[i].value = parseFloat(document.forms[0].qty[i].value) * parseFloat(document.forms[0].rate[i].value);
			
			gross_amt+=parseFloat(document.forms[0].amt[i].value);
			total_qty+=parseFloat(document.forms[0].qty[i].value);
	// 		document.getElementById('amt'+i).value = parseFloat(document.getElementById('qty'+i).value) * parseFloat(document.getElementById('rate'+i).value);
		}
	}else{
		
		if(document.forms[0].qty.value == ''){
			document.forms[0].qty.value = 0;
		}
		if(document.forms[0].rate.value ==''){
			document.forms[0].rate.value = 0;
		}
		if(document.forms[0].amt.value){
			document.forms[0].amt.value = 0;
		}
// 		alert(document.getElementById('qty'+i).value+"--"+document.getElementById('rate'+i).value)
		document.forms[0].amt.value = parseFloat(document.forms[0].qty.value) * parseFloat(document.forms[0].rate.value);
		
		gross_amt+=parseFloat(document.forms[0].amt.value);
		total_qty+=parseFloat(document.forms[0].qty.value);
	}
	
	document.getElementById('grossAmt').value = Math.round(gross_amt);
	for(var i = 0 ; i < parseInt(tax_sz) ; i++){
		
		document.getElementById('taxAmt'+i).value = Math.round((gross_amt * parseFloat(tax_structure))/100);
		
		totalTax+= Math.round((gross_amt * parseFloat(tax_structure))/100);
	}
	
	document.getElementById('totalTax').value = totalTax;
	document.getElementById('netAmt').value = gross_amt + totalTax;
	document.getElementById('total_qty').value = total_qty;
	
}

function enableDisable(inr_km,lsFlg,ind,sz,inr_mmbtu){
	
	for(var i = 0 ; i < parseFloat(sz) ; i++){
		if(document.getElementById('chkBox'+i).checked){
			
			document.getElementById('chkBoxFlg'+i).value = "Y"; 
			if(inr_km!='0'){
				document.getElementById('km'+i).readOnly = false;
			}
			if(lsFlg == 'Y'){
				document.getElementById('lumpsum'+i).readOnly = false;	
			}
			if(inr_mmbtu!='0'){
				calcMmbtu(sz);	
			}
		}else{
			document.getElementById('chkBoxFlg'+i).value = "N";
			if(inr_km!='0'){
				document.getElementById('km'+i).readOnly = true;
				calcInrKm(sz);
			}
			if(lsFlg == 'Y'){
				document.getElementById('lumpsum'+i).readOnly = true;
				calcLump(sz);
			}
			if(inr_mmbtu!='0'){
				calcMmbtu(sz);	
			}
		}
	}
}
</script>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice" id="serviceInv" scope="page"/> 
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
util.init();
String sysdate = util.getGen_dt();
String curr_year = util.getGet_yr();

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String error_msg=request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
String year=request.getParameter("year")==null?"":request.getParameter("year");
String month=request.getParameter("month")==null?"":request.getParameter("month");
String bill_cycle=request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");

String write_permission = (String)session.getAttribute("write_permission")==null?"":(String)session.getAttribute("write_permission");
String delete_permission = (String)session.getAttribute("delete_permission")==null?"":(String)session.getAttribute("delete_permission");
String print_permission = (String)session.getAttribute("print_permission")==null?"":(String)session.getAttribute("print_permission");
String approve_permission = (String)session.getAttribute("approve_permission")==null?"":(String)session.getAttribute("approve_permission");
String audit_permission = (String)session.getAttribute("audit_permission")==null?"":(String)session.getAttribute("audit_permission");
String check_permission =(String)session.getAttribute("check_permission")==null?"":(String)session.getAttribute("check_permission");
	
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210814
String supp_cd = request.getParameter("supp_cd")==null?"":request.getParameter("supp_cd");
String cust_cd = request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");

String cust_plant_cd = request.getParameter("cust_plant_cd")==null?"":request.getParameter("cust_plant_cd");
String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
String inr_mmbtu = request.getParameter("inr_mmbtu")==null?"0":request.getParameter("inr_mmbtu");
String inr_km = request.getParameter("inr_km")==null?"0":request.getParameter("inr_km");
String chkFlg = request.getParameter("chkFlg")==null?"99999":request.getParameter("chkFlg");
String tax_val=request.getParameter("tax_val")==null?"0":request.getParameter("tax_val");
String inr_km_str=request.getParameter("inr_km_str")==null?"":request.getParameter("inr_km_str");
String km_str=request.getParameter("km_str")==null?"":request.getParameter("km_str");
String total_km_inr=request.getParameter("total_km_inr")==null?"0":request.getParameter("total_km_inr");

String invDt=request.getParameter("invDt")==null?"":request.getParameter("invDt");
String dueDt=request.getParameter("dueDt")==null?"":request.getParameter("dueDt");
String calcBase=request.getParameter("calcBase")==null?"":request.getParameter("calcBase");
String tax_structure=request.getParameter("tax_structure")==null?"0":request.getParameter("tax_structure");

String rowno = request.getParameter("rowno")==null?"1":request.getParameter("rowno");
String sacStr = request.getParameter("sacStr")==null?"":request.getParameter("sacStr");
String itemStr = request.getParameter("itemStr")==null?"":request.getParameter("itemStr");
String qtyStr = request.getParameter("qtyStr")==null?"":request.getParameter("qtyStr");
String rateStr = request.getParameter("rateStr")==null?"":request.getParameter("rateStr");
String amtStr = request.getParameter("amtStr")==null?"":request.getParameter("amtStr");
String ls_str = request.getParameter("ls_str")==null?"":request.getParameter("ls_str");
String total_ls_inr = request.getParameter("total_ls_inr")==null?"":request.getParameter("total_ls_inr");
double total_qty = Double.parseDouble(request.getParameter("total_qty")==null?"0":request.getParameter("total_qty"));
String lumpsumFlg = request.getParameter("lumpsumFlg")==null?"":request.getParameter("lumpsumFlg");
String alwInvGen = request.getParameter("alwInvGen")==null?"":request.getParameter("alwInvGen");
String chkBoxFlgStr = request.getParameter("chkBoxFlgStr")==null?"":request.getParameter("chkBoxFlgStr");
String inr_mmbtu_str = request.getParameter("inr_mmbtu_str")==null?"":request.getParameter("inr_mmbtu_str");
String qty_mmbtu_str = request.getParameter("qty_mmbtu_str")==null?"":request.getParameter("qty_mmbtu_str");

String contract_type = "V";

serviceInv.setCallFlag("FETCH_INVOICE_DTL");
serviceInv.setYear(year);
serviceInv.setMonth(month);
serviceInv.setSupp_cd(supp_cd);
serviceInv.setCust_cd(cust_cd);
serviceInv.setPlant_no(cust_plant_cd);
serviceInv.setBill_cycle(bill_cycle);
serviceInv.setSmapping_id(mapping_id);
serviceInv.setInr_km(inr_km);
serviceInv.setInr_mmbtu(inr_mmbtu);
serviceInv.setChkFlg(chkFlg);
serviceInv.setInr_km_str(inr_km_str);
serviceInv.setKm_str(km_str);
serviceInv.setTotal_km_inr(total_km_inr);
serviceInv.setInvDt(invDt);
serviceInv.setDueDt(dueDt);
serviceInv.setCalcBase(calcBase);
serviceInv.setTax_structure(tax_structure);
serviceInv.setSacStr(sacStr);
serviceInv.setItemStr(itemStr);
serviceInv.setQtyStr(qtyStr);
serviceInv.setRateStr(rateStr);
serviceInv.setAmtStr(amtStr);
serviceInv.setRowno(rowno);
serviceInv.setContract_type(contract_type);
serviceInv.setLs_str(ls_str);
serviceInv.setTotal_ls_inr(total_ls_inr);
serviceInv.setLumpsumFlg(lumpsumFlg);
serviceInv.setAlwInvGen(alwInvGen);
serviceInv.setChkBoxFlgStr(chkBoxFlgStr);
serviceInv.setInr_mmbtu_str(inr_mmbtu_str);
serviceInv.setQty_mmbtu_str(qty_mmbtu_str);

serviceInv.init();

Vector Vsupp_cd = serviceInv.getVsupp_cd();
Vector Vsupp_nm = serviceInv.getVsupp_nm();
supp_cd = serviceInv.getSupp_cd();

String supp_addr = serviceInv.getSupp_addr();
String supp_city =  serviceInv.getSupp_city();
String supp_pin =  serviceInv.getSupp_pin();
String supp_state =  serviceInv.getSupp_state();
String supp_state_cd = serviceInv.getSupp_state_cd();
String supp_pan_no = serviceInv.getSupp_pan_no();
String supp_gstin_no = serviceInv.getSupp_gstin_no();

Vector Vcust_cd=serviceInv.getVcust_cd();
Vector Vcust_abbr=serviceInv.getVcust_abbr();
Vector Vcust_nm=serviceInv.getVcust_nm();

Vector plant_seq_no = serviceInv.getPlant_seq_no();
Vector plant_name = serviceInv.getPlant_name();

String cust_pan_no=serviceInv.getCust_pan_no();
String cust_gstin_no=serviceInv.getCust_gstin_no();
String cust_addr=serviceInv.getCust_addr();
String cust_city=serviceInv.getCust_city();
String cust_pin=serviceInv.getCust_pin();
String cust_country=serviceInv.getCust_country();
String cust_state=serviceInv.getCust_state();
String state_cd=serviceInv.getState_cd();

String plant_addr = serviceInv.getPlant_addr();
String plant_state = serviceInv.getPlant_state();
String plant_state_cd = serviceInv.getPlant_state_cd();
String plant_city = serviceInv.getPlant_city();
String plant_pin = serviceInv.getPlant_pin();
String plant_pan_no = serviceInv.getPlant_pan_no();
String plant_gstin_no = serviceInv.getPlant_gstin_no();
cust_plant_cd = serviceInv.getPlant_no();

Vector Vcont_no = serviceInv.getVcont_no();
Vector Vcont_rev_no = serviceInv.getVcont_rev_no();
Vector Vagr_no = serviceInv.getVagr_no();
Vector Vagr_rev_no = serviceInv.getVagr_rev_no();
Vector Vcont_type = serviceInv.getVcont_type();
Vector Vinr_km = serviceInv.getVinr_km();
Vector Vinr_mmbtu = serviceInv.getVinr_mmbtu();
Vector tax_desc=serviceInv.getTax_desc();
Vector SGST_CGST_RT=serviceInv.getSGST_CGST_RT();
Vector tax_name=serviceInv.getTax_nm();
Vector taxstr=serviceInv.getTaxstr();

Vector Vcont_start_dt = serviceInv.getVcont_start_dt();
Vector Vcont_end_dt = serviceInv.getVcont_end_dt();


String period_start_dt = serviceInv.getPeriod_start_dt();
String period_end_dt = serviceInv.getPeriod_end_dt();
String inv_seq_no = serviceInv.getInv_seq_no();
String new_inv_seq_no = serviceInv.getNew_inv_seq_no();
String hlpl_inv_seq_no = serviceInv.getInv_seq_no();
chkFlg = serviceInv.getChkFlg();

Vector VInv_new_inv_seq_no = serviceInv.getVInv_new_inv_seq_no();
Vector VInv_invoice_dt = serviceInv.getVInv_invoice_dt();
Vector VInv_total_qty= serviceInv.getVInv_total_qty();
Vector Vinv_truck_nm= serviceInv.getVinv_truck_nm();
inr_km = serviceInv.getInr_km();
inr_mmbtu = serviceInv.getInr_mmbtu();
total_qty = serviceInv.getTotal_qty();
Vector Vinv_qty_inr = serviceInv.getVinv_qty_inr();
double total_qty_inr = serviceInv.getTotal_qty_inr();
double total_qty_mmbtu = serviceInv.getTotal_qty_mmbtu();
double total_qty_km = serviceInv.getTotal_qty_km();
Vector Vinv_km_inr = serviceInv.getVinv_km_inr();
Vector Vinv_km = serviceInv.getVinv_km();
total_km_inr = serviceInv.getTotal_km_inr();
Vector Vinv_lumpSum =serviceInv.getVinv_lumpSum(); 
java.text.NumberFormat nf7 = new java.text.DecimalFormat("###,###,###,##0.00");
java.text.NumberFormat nf5 = new java.text.DecimalFormat("##########0");
java.text.NumberFormat nf = new java.text.DecimalFormat("###########0.00");

Vector Vsac_cd = serviceInv.getVsac_cd();
Vector Vitem_desc = serviceInv.getVitem_desc();
Vector Vqty = serviceInv.getVqty();
Vector Vrate = serviceInv.getVrate();
Vector Vamt = serviceInv.getVamt();
rowno = serviceInv.getRowno();
Vector Vlumpsum_flg = serviceInv.getVlumpsum_flg();

double inv_gross_amt = serviceInv.getInv_gross_amt();
// System.out.println("rowno----"+rowno);
//String remark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : Standard Chartered Bank (Ahmedabad Branch) - A/C No: 233-0-525465-5. \n";
String remark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : CitiBank N.A., Mumbai - A/C No: 522614033, IFSC code: CITI0100000 \n";
String remark_2 = "NOTE : Bank Account Changes Should Not Be Made Without a Re-Confirmation With Your Usual SHELL Contact.\nUnless Communicated By Your Usual Shell Contact/Authorized Representative, Bank Account Changes Should Not Be Made. Any Proposed Change Should Always Be Confirmed Initially By Phone And Then Electronically (Email) With Your Usual SHELL Contact. Most Importantly Check For Valid Domain Name (@SHELL.COM) in the E-mail Address.";

String invoice_Customer_Contact_Cd = serviceInv.getInvoice_Customer_Contact_Cd();
String invoice_Customer_Contact_Nm = serviceInv.getInvoice_Customer_Contact_Nm();
String fin_yr = serviceInv.getFin_yr();

Vector serv_inv_no = serviceInv.getServ_inv_no();
Vector serv_inv_bill_cycle = serviceInv.getServ_inv_bill_cycle();
String sac_code = serviceInv.getSac_code();
total_ls_inr =  serviceInv.getTotal_ls_inr();
lumpsumFlg = serviceInv.getLumpsumFlg();
alwInvGen = serviceInv.getAlwInvGen();
Vector Vinv_chkBox_Flg = serviceInv.getVinv_chkBox_Flg();
chkBoxFlgStr = serviceInv.getChkBoxFlgStr();
// System.out.println("lumpsumFlg----"+lumpsumFlg);

%>
<body <%if(!msg.equals("") || !error_msg.equals("")) {%>onload="refreshParant('<%=year%>','<%=month%>','<%=bill_cycle%>','<%=msg%>','<%=error_msg%>','<%=modUrl %>','<%=modCd%>','<%=formId%>','<%=subModUrl%>');"<%} %>>
	<div class="tab-content">
	<!-- /.Invoicing TAB START-->
		<div class="tab-pane active" id="invoicing">
			<form  method="post" action="../servlet/Frm_Service_Invoice" id = "SIform" >
			
			<input type="hidden" name="option" value="Submit_Service_Invoice">
			<input type="hidden" name="modCd" value="<%=modCd%>">
			<input type="hidden" name="formId" value="<%=formId%>">
			<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
			<input type="hidden" name="modUrl" value="<%=modUrl%>">
			<input type="hidden" name="bill_cycle" value="<%=bill_cycle%>">
			<input type="hidden" name="month" value="<%=month%>">
			<input type="hidden" name="year" value="<%=year%>">
			<input type="hidden" name="hid_inr_km" id="hid_inr_km" value="<%=inr_km%>">
			<input type="hidden" name="hid_inr_mmbtu" id="hid_inr_mmbtu" value="<%=inr_mmbtu%>">
			<input type="hidden" name="contSz"  id="contSz" value="<%=Vcont_no.size()%>">
			<input type="hidden" name="invSz"  id="invSz" value="<%=VInv_new_inv_seq_no.size()%>">
<!-- 			<input type="hidden" name="norows" value="1"> -->
			<input type="hidden" name="rowno" value="<%=rowno%>">
			<input type="hidden" name="tax_sz" id="tax_sz"  value="<%=tax_name.size()%>">
			<input type="hidden" name="mapping_id" id="mapping_id"  value="<%=mapping_id%>">
			<input type="hidden" name="period_start_dt" id="period_start_dt"  value="<%=period_start_dt%>">
			<input type="hidden" name="period_end_dt" id="period_end_dt"  value="<%=period_end_dt%>">
			<input type="hidden" name="contact_person_cd" id="contact_person_cd"  value="<%=invoice_Customer_Contact_Cd%>">
			<input type="hidden" name="contract_type" id="contract_type"  value="<%=contract_type%>">
			<input type="hidden" name="emp_cd" id="emp_cd"  value="<%=emp_cd%>">
			<input type="hidden" name="fin_yr" id="fin_yr"  value="<%=fin_yr%>">
			<input type="hidden" name="hlpl_inv_seq_no" id="hlpl_inv_seq_no"  value="<%=hlpl_inv_seq_no%>">
			<input type="hidden" name="supp_state_cd" id="supp_state_cd"  value="<%=supp_state_cd%>">
			<input type="hidden" name="total_qty" id="total_qty"  value="<%=total_qty%>">
			<input type="hidden" name="truckInvSz" id="truckInvSz"  value="<%=VInv_new_inv_seq_no.size()%>">
			<input type="hidden" name="chkFlg" id="chkFlg"  value="<%=chkFlg%>">
			<input type="hidden" name="sac_code" id="sac_code"  value="<%=sac_code%>">
			<input type="hidden" name="alwInvGen" id="alwInvGen"  value="<%=alwInvGen%>">
			<input type="hidden" name="alwInvGen" id="lsFlg"  value="<%=lumpsumFlg%>">
			<input type="hidden" name="chkBoxFlgStr" id="chkBoxFlgStr"  value="<%=chkBoxFlgStr%>">
			<input type="hidden" name="totalInvQty" id="hid_mmbtu_qty" value="<%=nf5.format(total_qty_mmbtu)%>">
			
				<div class="box mb-0">
					<div class="box-header with-border">
						<div id="col-new">
							<%if(msg.length()>5) { %>
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
							<div class="box-header with-border" >
								<div class="form-group col-md-12 text-center">
							 	 	<label for="" ><h2> Service Invoice Preparation</h2></label>
								</div>
								<div class="box-tools pull-right">
									<button type="button"  class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove" name="close_window" value="Close Window" onclick="closeWindow();">Close  <i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							
							<div class="row">
								<div class="table-responsive col-lg-6">
									<table class="table table-striped" >
										<tr>
											<th colspan="6" class="text-center main-header">Supplier Details</th>
										</tr>
										<tr>
											<td class="info " colspan="2" >Supplier Name</td>
											<td colspan="4">
												<select class="form-control form-control-sm" onchange="fetchSupDtl();" name="supp_cd" id="supp_cd">
													<option value="">-Select-</option>
													<%for(int i = 0 ; i < Vsupp_cd.size() ; i++) {%>
														<option value="<%=Vsupp_cd.elementAt(i)%>"> <%=Vsupp_nm.elementAt(i) %> </option>
													<%} %>	
												</select>
												<%if(!supp_cd.equals("")){ %>
													<script type="text/javascript">
														document.getElementById('supp_cd').value = '<%=supp_cd%>';
													</script>
												<%} %>
											</td>
										</tr>
										<tr>
											<td class="info" colspan="2">Supplier Address</td>
											<td colspan="4">
								    			<input type="text" class="form-control form-control-sm" title="Supplier address"   name="SupAddress" value="<%=supp_addr %>"  readonly="readonly">
								     		</td>
										</tr>
										<tr>
											<td class="info" colspan="2">GSTIN No.</td>
											<td colspan="4">
								    			<input type="text" class="form-control form-control-sm" readonly="readonly"  name="ST_NO" id="ST_NO" value="<%=supp_gstin_no %>" >	
								     		</td>
										</tr>
										<tr>
											<td class="info" colspan="2">PAN No.</td>
											<td colspan="4">
								    			<input type="text" class="form-control form-control-sm" readonly="readonly"  name="pan_no" id="pan_no" value="<%=supp_pan_no%>" >	
								     		</td>
										</tr>	
										<tr>
											<td class="info" colspan="2">City</td>
											<td colspan="4">
												<input type="text" class="form-control form-control-sm" readonly="readonly"  name="supp_city" value="<%=supp_city%>" >
								     		</td>
										</tr>
										<tr>
											<td class="info" colspan="2">State</td>
										 	<td colspan="4" ><input type="text" class="form-control form-control-sm" readonly="readonly"  name="supp_state" value="<%=supp_state%>" ></td>
										</tr>	
									</table>
								</div>
								<div class="table-responsive col-lg-6">
									<table class="table table-striped" >
										<tr>
											<th colspan="6" class="text-center main-header">Buyer Details</th>
										</tr>
										<tr>
											<td class="info" colspan="2" >Buyer Name</td>
											<td colspan="4">
												<select class="form-control form-control-sm" onchange="fetchSupDtl();" name="cust_cd" id="cust_cd">
													<option value="">-Select-</option>
													<%for(int i = 0 ; i < Vcust_cd.size() ; i++) {%>
														<option value="<%=Vcust_cd.elementAt(i)%>" title=""> <%=Vcust_abbr.elementAt(i) %> </option>
													<%} %>	
												</select>
												<%if(!cust_cd.equals("")){ %>
													<script type="text/javascript">
														document.getElementById('cust_cd').value = '<%=cust_cd%>';
													</script>
												<%} %>
											</td>
										</tr>
 										<tr> 
 											<td class="info" colspan="2" >Buyer Plant(s)</td> 
											<td colspan="4">
												<select class="form-control form-control-sm" onchange="fetchSupDtl();" name="cust_plant_cd" id="cust_plant_cd" >
													<option value="">-Select-</option>
													<%for(int i = 0 ; i < plant_seq_no.size() ; i++) {%>
														<option value="<%=plant_seq_no.elementAt(i)%>" title=""> <%=plant_name.elementAt(i) %> </option>
													<%} %>	
												</select>
												<%if(!cust_plant_cd.equals("")){ %>
													<script type="text/javascript">
														document.getElementById('cust_plant_cd').value = '<%=cust_plant_cd%>';
													</script>
												<%} %>
											</td>
										</tr> 
										
										<tr>
											<td class="info" colspan="2">Buyer Address</td>
											<td colspan="4">
								    			<input type="text" class="form-control form-control-sm" title="Customer address"   name="CustAddress" value="<%=plant_addr %>"  readonly="readonly">
								     		</td>
										</tr>
										<tr>
											<td class="info" colspan="2">GSTIN No.</td>
											<td colspan="4">
								    			<input type="text" class="form-control form-control-sm" readonly="readonly"  name="CUST_ST_NO" id="cust_st_no" value="<%=plant_gstin_no %>" >	
								     		</td>
										</tr>
										 <tr>
											<td class="info" colspan="2">PAN No.</td>
											<td colspan="1"><input type="text" class="form-control form-control-sm" readonly="readonly"  name="cust_pan_no" id="cust_pan_no" value="<%=plant_pan_no%>"></td>
											<td class="info" colspan="2">State</td>
								     		<td colspan="1" >
								     			<input type="text" class="form-control form-control-sm" readonly="readonly"  name="cust_state" id="cust_state" value="<%=plant_state%>" >
								     			<input type="hidden" class="form-control form-control-sm" readonly="readonly"  name="plant_state_cd" id="plant_state_cd" value="<%=plant_state_cd%>" >
								     		</td>
										</tr>	
										<tr>
											<td class="info" colspan="2">City</td>
											<td colspan="1"><input type="text" class="form-control form-control-sm" readonly="readonly"  name="cust_city" id="cust_city" value="<%=plant_city%>" ></td>
								     		
								     		<td class="info" colspan="2">Pin</td>
								     		<td colspan="1" ><input type="text" class="form-control form-control-sm" readonly="readonly"  name="cust_pin" id="cust_pin" value="<%=plant_pin%>" ></td>
										</tr>
										<tr>
											<td class="info" colspan="2">Contact Person</td>
											<td colspan="4"><input type="text" class="form-control form-control-sm" readonly="readonly" name="cont_perc" id="cont_perc" value="<%=invoice_Customer_Contact_Nm %>" ></td>
										</tr>
									</table>
								</div>		
							</div>
							
							<div class="row">
								<div class="table-responsive col-lg-12">
									<table class="table table-striped" >
										<tr class="text-center main-header">
											<th colspan="12" class="text-center main-header">Available Contract(s) Details for the period <%=period_start_dt %> to <%=period_end_dt %></th>
										</tr>
										<tr class="text-center">
<!-- 											<th colspan="2" rowspan="2" class="text-center">&nbsp;</th> -->
											<th colspan="1" rowspan="2" class="text-center info">Select</th>
											<th colspan="1" rowspan="2" class="text-center info">Contract Type</th>
											<th colspan="1" rowspan="2" class="text-center info">Contract Rev. No.</th>
											<th colspan="1" rowspan="2" class="text-center info">Agreement No.</th>
											<th colspan="2" rowspan="2" class="text-center info">Agreement Rev. No.</th>
											<th colspan="1" rowspan="2" class="text-center info">Contract Start Dt.</th>
											<th colspan="1" rowspan="2" class="text-center info">Contract End Dt.</th>
											<th colspan="2" rowspan="1" class="text-center info">Transportation Charge</th>
											<th colspan="2" rowspan="2" class="text-center info">Service Invoice No.</th>
										</tr>
										<tr class="text-center info">
											<th colspan="1" class="text-center">INR/MMBTU</th>
											<th colspan="1" class="text-center">INR/KM</th>											
										</tr>
											<%for(int i = 0 ; i < Vcont_no.size() ; i ++ ) { %>
											<tr class="text-center" 
												<%if(!serv_inv_no.elementAt(i).equals("")){ %> style="background-color: #e1dbdb;" title="Invoice Generated" <%} %>>
<!-- 												<td colspan="2"></td> -->
												<td><input type="radio" name="chk" id="chk<%=i%>"
													<%if(Integer.parseInt(chkFlg+"") == i){%>
															checked="checked"
													<%}%>
													<%if(!serv_inv_no.elementAt(i).equals("")){ %>
														disabled="disabled"
													<%}else{ %>
														onclick="fetchInvDtl('<%=Vcont_no.size() %>');"
													<%} %>
												> </td>
												<td>
													<%if(Vcont_type.elementAt(i).equals("S")) {%>
														SN - <%= Vcont_no.elementAt(i) %>
													<%}else{%>
														LoA - <%= Vcont_no.elementAt(i) %>
													<%} %>
													<input type="hidden" name="cont_type" id="cont_type<%=i%>" value="<%= Vcont_type.elementAt(i) %>">
													<input type="hidden" name="cont_no" id="cont_no<%=i %>" value="<%= Vcont_no.elementAt(i) %>">
												</td>
												<td><%=Vcont_rev_no.elementAt(i) %>
													<input type="hidden" name="cont_rev_no" id="cont_rev_no<%=i %>" value="<%=Vcont_rev_no.elementAt(i) %>">
												</td>	
												<td><%=Vagr_no.elementAt(i) %>
													<input type="hidden" name="agr_no" id="agr_no<%=i %>" value="<%=Vagr_no.elementAt(i) %>">
												</td>
												<td colspan="2"><%=Vagr_rev_no.elementAt(i) %>
													<input type="hidden" name="agr_rev_no" id="agr_rev_no<%=i %>" value="<%=Vagr_rev_no.elementAt(i) %>">
												</td>
												<td><%=Vcont_start_dt.elementAt(i) %>
													<input type="hidden" name="cont_st_dt" id="cont_st_dt<%=i %>" value="<%=Vcont_start_dt.elementAt(i) %>">
												</td>
												<td><%=Vcont_end_dt.elementAt(i) %>
													<input type="hidden" name="cont_end_dt" id="cont_end_dt<%=i %>" value="<%=Vcont_end_dt.elementAt(i) %>">
												</td>
												<td><%=Vinr_mmbtu.elementAt(i) %>
													<input type="hidden" name="inr_mmbtu" id="inr_mmbtu<%=i %>" value="<%=Vinr_mmbtu.elementAt(i) %>">
												</td>
												<td><%=Vinr_km.elementAt(i) %>
													<input type="hidden" name="inr_km" id="inr_km<%=i %>" value="<%=Vinr_km.elementAt(i) %>">
												</td>
												<td colspan="2"><%=serv_inv_no.elementAt(i) %></td>
												
												<input type="hidden" name="lumpsumFlg" id="lumpsumFlg<%=i %>" value="<%=Vlumpsum_flg.elementAt(i)%>">
											</tr>	
											<%} %>
											
											<%if(Vcont_no.size() > 0) {%>
												 <tr>
<!-- 													<th colspan="2"  >&nbsp;</th> -->
													<th colspan="12" class="text-center info">
<%-- 														<button type="button"  class="btn btn-primary"  name="btn" onclick="fetchInvDtl('<%=Vcont_no.size() %>');" >View</button> --%>
													</th> 
												</tr>
											<%}else{ %>
												<tr>
													<th colspan="12" class="text-center">
														<font color="red">Contract Details Not Available for the Selected Period</font>
													</th>
												</tr>
											<%} %>
										<%if(!chkFlg.equals("99999")) {%>
											<tr>
												<td colspan="12">
													<button class="accordion" type="button" onclick="setAccordian(this,this.id);" id="acrdBtn0" >
														<table width="100%" align="center">
															<tr>
																<%if(VInv_new_inv_seq_no.size() > 0) {%>
																	<th colspan="11"><font color="blue"> Click to see Selected Contract Invoice Details</font></th>
																<%}else{ %>
																	<th colspan="11"><font color="red">No Invoice has been generated in the selected contract & period</font></th>
																<%} %>
																
																<th colspan="1" class="text-right" >
																	<span id="plusmins0" class="fa fa-plus-circle"></span>
																</th>
															</tr>
														</table>
													</button>
													<div class="panel" >
														<table  width="100%" class="table  table-bordered text-center" >
															<%for(int i = 0 ; i < VInv_new_inv_seq_no.size(); i ++) {%>
																<%if(i == 0) {%>
																	<tr class="info">
																		<th>Select</th>
																		<th>Invoice Date</th>
																		<th>Invoice No.</th>
																		<th>Truck Number</th>
																		<th>Invoice Quantity <br>(MMBTU) </th>
																		<%if(!inr_mmbtu.equals("0")) {%>
																			<th width="5%">Rate <br>(INR/MMBTU) </th>
																			<th>Amount <br>(INR/MMBTU) </th>
																		<%} %>
																		<%if(!inr_km.equals("0")) {%>
																			<th >Kilometer(s) <i class="fa fa-clipboard" style="font-size:24px;color:red;vertical-align: middle;" title="Click here to Copy & Paste" onclick="copyPaste('<%=VInv_new_inv_seq_no.size()%>','km');"></i></th>
																			<th  width="5%">Rate <br>(INR/KM) </th>
																			<th  width="10%">Amount <br>(INR/KM) </th>
																		<%} %>
																		<%if(lumpsumFlg.equalsIgnoreCase("Y")) {%>
																			<th>Lump Sum <i class="fa fa-clipboard" style="font-size:24px;color:red;vertical-align: middle;" title="Click here to Copy & Paste" onclick="copyPaste('<%=VInv_new_inv_seq_no.size()%>','lumpsum');"> </i></th>
																		<%} %>
																	</tr>
																<%} %>
																<tr class="text-center">
																	<td width="5%"> <input type="checkbox" name="chkBox<%=i%>" id ="chkBox<%=i%>" onclick="enableDisable('<%=inr_km%>','<%=lumpsumFlg %>','<%=i%>','<%=VInv_new_inv_seq_no.size()%>','<%=inr_mmbtu %>');" 
																	<%if(Vinv_chkBox_Flg.elementAt(i).equals("Y")){ %>checked="checked" <%} %>
																	> 
																		<input type="hidden" name="chkBoxFlg" id="chkBoxFlg<%=i %>" <%if(Vinv_chkBox_Flg.elementAt(i).equals("Y")){ %>value="Y"<%}else{ %> value = "N" <%} %> >
																	</td>
																	<td><%=VInv_invoice_dt.elementAt(i) %>
																		<input type="hidden" name="truckInvDt" id="truckInvDt<%=i %>" value="<%=VInv_invoice_dt.elementAt(i) %>"> 
																	</td>
																	<td><%=VInv_new_inv_seq_no.elementAt(i) %>
																		<input type="hidden" name="truckInvNo" id="truckInvNo<%=i %>" value="<%=VInv_new_inv_seq_no.elementAt(i) %>">
																	</td>
																	<td><%=Vinv_truck_nm.elementAt(i) %>
																		<input type="hidden" name="truckNm" id="truckNm<%=i %>"  value="<%=Vinv_truck_nm.elementAt(i) %>">
																	</td>
																	<td class="text-right" style="background-color: #E8E8E8;"><%=VInv_total_qty.elementAt(i) %>
																		<input type="hidden" name="truckInvQty" id="truckInvQty<%=i %>" value="<%=VInv_total_qty.elementAt(i) %>">
																	</td>
																	
																	<%if(!inr_mmbtu.equals("0")) {%>
																		<td class="text-right" style="background-color: #E8E8E8;"><%=inr_mmbtu %></td>
																		<td class="text-right" style="background-color: #E8E8E8;" width="10%"><%=Vinv_qty_inr.elementAt(i) %>
																			<input type="hidden" name="servInvQtyAmt" id="servInvQtyAmt<%=i %>" value="<%=Vinv_qty_inr.elementAt(i) %>">
																		</td>
																	<%} %>
																	<%if(!inr_km.equals("0")) {%>
																		<td colspan="1" width="10%" style="background-color: #dde7ec;">
																			<input class="form-control form-control-sm text-center" type="number"  onkeyup="checkNum(this)" min="0" name="km" id="km<%=i%>" onchange="calcInrKm('<%=VInv_new_inv_seq_no.size()%>');" value="<%=Vinv_km.elementAt(i)%>" <%if(!Vinv_chkBox_Flg.elementAt(i).equals("Y")){ %>readonly="readonly"<%} %>>
																		</td>
																		<td style="background-color: #dde7ec;"><%=inr_km %> </td>
																		<td style="background-color: #dde7ec;">
																			<input class="form-control form-control-sm text-right" width="10%" type="text" name="inv_km_inr" id="inv_km_inr<%=i %>" readonly="readonly" value="<%=Vinv_km_inr.elementAt(i)%>" size="10">
																		</td>
																	<%} %>
																	<%if(lumpsumFlg.equalsIgnoreCase("Y")){ %>
																		<td colspan="1" width="10%" >
																			<input class="form-control form-control-sm text-center" type="number"  min="0" name="lumpsum" id="lumpsum<%=i%>" onkeyup="checkNum(this);" onchange="calcLump('<%=VInv_new_inv_seq_no.size()%>');" value="<%=Vinv_lumpSum.elementAt(i) %>" <%if(!Vinv_chkBox_Flg.elementAt(i).equals("Y")){ %>readonly="readonly"<%} %>>
																		</td>
																	<%} %>	
																	<input type="hidden" name="rate_mmbtu" id="rate_mmbtu"  value="<%=inr_mmbtu %>">
																	<input type="hidden" name="rate_km" id="rate_km" value="<%=inr_km%>">
																	
																</tr>
																<%if(i == VInv_new_inv_seq_no.size()-1 ) {%>
																<tr class="info">
																	<th colspan="4" class="center">TOTAL</th>
																	<th colspan="1" class="text-right" id="mmbtu_qty"><%= nf5.format(total_qty_mmbtu) %>
																	</th>
																	<%if(!inr_mmbtu.equals("0")) {%>
																		<th colspan="1" class="center"></th>
																		<th colspan="1" class="text-right" >
																			<input  type="text" class="form-control form-control-sm text-right" name="total_mmbtu_inr" id="total_mmbtu_inr" readonly="readonly" value="<%= nf7.format(total_qty_inr) %>">
																		</th>
																	<%} %>
																	<%if(!inr_km.equals("0")) {%>
																		<th colspan="2" class="center"></th>
																		<th colspan="1" class="center">
																			<input  type="text" class="form-control form-control-sm text-right" name="total_km_inr" id="total_km_inr" readonly="readonly" value="<%=total_km_inr %>" >
																		</th>
																	<%} %>
																	<%if(lumpsumFlg.equalsIgnoreCase("Y")){ %>
																		<th>
																			<input  type="text" class="form-control form-control-sm text-right" name="total_ls_inr" id="total_ls_inr" readonly="readonly" value="<%=total_ls_inr %>" >
																		</th>
																	<%} %>
																</tr>	
																
																<%} %>
															<%} %>
														</table>
													</div> 
												</td>
											</tr>					
										<%}%>	
										<tr>
											<th colspan="12" class="text-center main-header">Transporter Invoice Details</th>
										</tr>
										<tr class="text-center info">
											<th colspan="2">Invoice No.</th>
											<th colspan="2">Invoice Date</th>
											<th colspan="2">Payment Due Date</th>
											<th colspan="2">Rate</th>
											<th colspan="2">Invoice Type</th>
											<th colspan="2">Tax Structure Type</th>
										</tr>
										<tr>
											<td colspan="2"><input class="form-control form-control-sm" readonly="readonly" type="text" name="inv_seq_no" id="inv_seq_no" value="<%=new_inv_seq_no%>">  </td>
											<td colspan="2"><input class="form-control form-control-sm" type="text" name="inv_dt" id="datetimepicker1" maxlength="10" value="<%=invDt %>" onBlur="validateDate(this);" onchange="chkdue_dt();" placeholder="dd/mm/yyyy" autocomplete="off" >  </td>
											<td colspan="2"><input class="form-control form-control-sm" type="text" name="due_dt" id="datetimepicker2" value="<%=dueDt %>" onBlur="validateDate(this);"  onchange="chkdue_dt();" placeholder="dd/mm/yyyy" autocomplete="off" >  </td>
											
											<td colspan="2">
												<select class="form-control form-control-sm" onchange="setLineItem();" name="calcBase" id="calcBase">
													<option value="">-Select-</option>
													<%if(!inr_mmbtu.equals("0")) {%>
														<option value="1">INR/MMBTU</option>
													<%} %>
													<%if(!inr_km.equals("0")) {%>
														<option value="2">INR/KM</option>	
													<%} %>
													<%if(lumpsumFlg.equalsIgnoreCase("Y")){ %>
														<option value="3">Lump Sum</option>
													<%} %>
												</select>
												<%if(!calcBase.equals("")){ %>
													<script type="text/javascript">
														document.getElementById('calcBase').value = '<%=calcBase%>';
													</script>					
												<%} %>						
											</td>
											<td colspan="2">
												<select class="form-control form-control-sm">
													<option value="1">Service</option>
												</select>	
											</td>
											<td colspan="2">
												<select class="form-control form-control-sm" name="tax_structure" id="tax_structure" onchange="setLineItem();">
												 <%for(int i=0;i<tax_desc.size();i++) {%>
												 	<option value="<%=SGST_CGST_RT.elementAt(i)%>"><%=tax_desc.elementAt(i).toString().trim()%></option>
											 	<%}%>		
												</select>
												<%if(!tax_structure.equals("") && !tax_structure.equals("0")){ %>
													<script type="text/javascript">
														document.getElementById('tax_structure').value = '<%=tax_structure%>';
													</script>					
												<%} %>
											</td>
										</tr>
										<tr>
											<th colspan="12" class="text-center info">Description of Line Item
												&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn btn-success btn-sm" value="Add More" onclick="addnewdesc('<%=tax_desc.size() %>','<%=tax_desc%>');">
											</th>
										</tr>
										<tr class="text-center info">
											<th class="text-center">SAC Code</th>
											<th colspan="5" class="text-center">Line Item</th>
											<th class="text-center" colspan="2">Quantity</th>
											<th class="text-center" colspan="2">Rate</th>
											<th class="text-center" colspan="2">Amount</th>
										</tr>
										<%for(int i = 0 ; i < Integer.parseInt(rowno+"") ; i++){
											if(!chkFlg.equals("99999")){
											%>
										<tr>
											<td colspan="1">
												<input class="form-control form-control-sm text-center" type="text" name="sac_cd" id="sac_cd<%=i %>" size="10" value="<%=Vsac_cd.elementAt(i)%>" readonly="readonly" >
											</td>
											<td colspan="5" >
												<input class="form-control form-control-sm" type="text" name="line_item" id="line_item<%=i %>" size="50" onkeypress="return blockSpecialChar(event);" <%if(i == 0){%>value="Transport Management Services Charge" readonly="readonly"<%}else{ %> value="<%=Vitem_desc.elementAt(i)%>" <%} %>>
											</td>
											<td colspan="2">
												<input class="form-control form-control-sm text-center" type="text" name="qty" id="qty<%=i %>" 
												 <%if(i == 0) {%>readonly="readonly" <%}else{ %> onkeyup="checkNum(this);" onchange="caclInvAmt();"<%} %>  size="10" value="<%=Vqty.elementAt(i)%>"> 
											</td>
											<td colspan="2">
												<input class="form-control form-control-sm text-right" type="text" name="rate" id="rate<%=i %>"  size="10"
												<%if(i == 0) {%>readonly="readonly" <%}else{ %>onkeyup="checkNum(this);" onchange="caclInvAmt();"<%} %> value="<%=Vrate.elementAt(i)%>">
											</td>
											<td colspan="2">
												<input class="form-control form-control-sm text-right" type="text" name="amt" id="amt<%=i %>"  
												value="<%=Vamt.elementAt(i) %>"
												<%if(i == 0) {%>readonly="readonly" <%} %> size="10">
											</td>
										</tr>
										<%}} %>
							  		</table>	
						  			<table  width="100%" class="table  table-bordered text-center" id="lineItem">
							  			<tbody id="tabId">
							  			
							  			</tbody>
						  			</table>
					  			</div>
					  		</div>	
					  		<div class="row">
					  			<div class="table-responsive col-lg-12">
									<table class="table table-striped" >
										<tr>
											<th colspan="12" class="text-left info">Tax Structure</th>
										</tr>
									</table>
								</div>	
								<!-- <div class="table-responsive col-lg-6">
									<table class="table table-striped" >
									</table>
								</div> -->
					  			<div class="table-responsive col-lg-6">
									<table class="table table-striped" >
										<tr>
											<th class="text-left info" colspan="2">Total Charges (INR)</th>
											<td colspan="2">
												<input class="form-control form-control-sm text-right" type="text" name="grossAmt" id="grossAmt" readonly="readonly" value="<%=nf.format(Math.round(inv_gross_amt))%>">
											</td>
											<td colspan="2"></td>
										</tr>
										
										<tr>
											<th colspan="6" class="text-left info">Tax Structure Details</th>
										</tr>
										<%String taxnm_str = "", taxAmt_str = "";
										double total_tax = 0,inv_net_amt=0;
										for(int i=0; i < tax_name.size(); i++){
											double tax_amt = (inv_gross_amt* Double.parseDouble(tax_structure+""))/100;
											total_tax+=Math.round(tax_amt);
											taxnm_str+=tax_name.elementAt(i)+"@@";
											taxAmt_str+=tax_amt+"@@";
											%>
											<tr>
												<td colspan="2" class="text-right info"><%=tax_name.elementAt(i)%>
												 </td>	
												<td colspan="2">
													<input class="form-control form-control-sm text-right" type="text" name="taxAmt" id="taxAmt<%=i %>" readonly="readonly" value="<%=nf.format(Math.round(tax_amt))%>">
												</td> 
												<td colspan="2" class="text-left">@ <%=tax_structure %> %</td>
											</tr>
										<%} %>
										
										<tr>
											<th  colspan="2" class="text-left info">Total GST </th>
											<td colspan="2">
												<input class="form-control form-control-sm text-right" type="text" name="totalTax" id="totalTax" readonly="readonly" value="<%=nf.format(total_tax)%>">
											</td>
											<td colspan="2"></td>
										</tr>
										
										<tr>	
											<th  colspan="2" class="text-left info">Total Amount in INR</th>
											<td colspan="2">
												<input class="form-control form-control-sm text-right" type="text" name="netAmt" id="netAmt" readonly="readonly" value="<%=nf.format(Math.round(inv_gross_amt+total_tax))%>">
											</td>
											<td colspan="1"></td>
										</tr>
									</table>
								</div>
								
								<div class="table-responsive col-lg-12">
									<table class="table table-striped" >
										<tr>
											<th colspan="12" class="text-center info">Remarks</th>
										</tr>
										<tr>
											<th class="col-lg-2 info">Remark - 1 :</th>
											<td class="col-lg-10">
												<textarea rows="3" cols="100" name="remark_1" id="remark_1"><%=remark_1 %> </textarea>
											</td>
										</tr>
										<tr>	
											<th class="col-lg-2 info">Remark - 2 :</th>
											<td class="col-lg-10">
												<textarea rows="5" cols="100" name="remark_2" id="remark_2"><%=remark_2 %></textarea>
											</td>
										</tr>
										<tr>
											<th colspan="11" class="text-right">
												<button type="button" class="btn btn-primary" name="verify" id="verify" value="Verify Before Submit" onclick="verifyInv('<%=contract_type%>','<%=new_inv_seq_no%>','<%=tax_name.size()%>');"> Verify Before Submit </button>
											</th>
											<th colspan="1" class="text-right">
												<button type="button" class="btn btn-success" name="" id="" title="Submit Inivoice Details" onclick="doSubmit();">Submit </button>
											</th>
										</tr>
									</table>
								</div>		
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>

	<script type="text/javascript">
	$(function () {
		var k= 5;
		for(var i=1;i<=k;i++) {		
		$('#datetimepicker'+i).datepicker({
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
	function setAccordian(obj,id){
		var acc = document.getElementsByClassName("accordion");
		var i;
		for (i = 0; i < acc.length; i++) {
			var oth_id = 'acrdBtn'+i;
// 	 		alert(oth_id)
			if(id == oth_id){
// 	 			alert('in')
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
	
	function verifyInv(cont_type,new_inv_seq_no,tax_desc){
		
		var rows = parseInt(document.forms[0].rowno.value);
		var invDt = document.getElementById('datetimepicker1').value;
		var dueDt = document.getElementById('datetimepicker2').value;
		var mapping_id = document.getElementById('mapping_id').value;
		var cust_cd = document.getElementById('cust_cd').value;
		var cust_plant_cd = document.getElementById('cust_plant_cd').value;
		var period_start_dt = document.getElementById('period_start_dt').value;
		var period_end_dt = document.getElementById('period_end_dt').value;
		var totalTax = document.getElementById('totalTax').value;
		var netAmt = document.getElementById('netAmt').value;
		var taxnm_str = '<%=taxnm_str%>';
		var taxAmt_str = '<%=taxAmt_str%>';
		var tax_structure = '<%=tax_structure%>';
		var calcBase = document.getElementById('calcBase').value;
		var grossAmt = document.getElementById('grossAmt').value;
		var truckInvSz = document.getElementById('truckInvSz').value;
		var chkBoxFlgStr = document.getElementById('chkBoxFlgStr').value;
		var msg = "";
		
// 		alert('in')
		var sacStr = "";
		var itemStr = "";
		var qtyStr = "";
		var rateStr = "";
		var amtStr = "";
// 		alert(taxnm_str)
		if(invDt == '' || invDt == ' '){
			msg+= "\nInvoice Date cannot be blank ";
		}
		if(dueDt == '' || dueDt == ' '){
			msg+= "\nInvoice Due Date cannot be blank ";
		}
		if(calcBase == '' || calcBase == ' '){
			msg+= "\nPlease Select Rate ";
		}
		
		if(msg!=""){
			
			alert(msg);
			
		}else{ 
			
			for(var i = 0 ; i < rows ; i++){
	// 			alert('in')
				if(document.getElementById('sac_cd'+i).value != '' && document.getElementById('sac_cd'+i).value != ' '){
					sacStr+=document.getElementById('sac_cd'+i).value+"@@";
				}else{
					sacStr+="-@@";
				}
				if(document.getElementById('line_item'+i).value != '' && document.getElementById('line_item'+i).value != ' '){
					itemStr+=document.getElementById('line_item'+i).value+"@@";
				}else{
					itemStr+="-@@";
				}
	// 			alert('in')
				if(document.getElementById('qty'+i).value != '' && document.getElementById('qty'+i).value != ' '){
					qtyStr+=document.getElementById('qty'+i).value+"@@";
				}else{
					qtyStr+="-@@";
				}
				
				if(document.getElementById('rate'+i).value != '' && document.getElementById('rate'+i).value != ' '){
					rateStr+=document.getElementById('rate'+i).value+"@@";
				}else{
					rateStr+="-@@";
				}
				
				if(document.getElementById('amt'+i).value != '' && document.getElementById('amt'+i).value != ' '){
					amtStr+=document.getElementById('amt'+i).value+"@@";
				}else{
					amtStr+="-@@";
				}
			}
			newWindow2 = window.open("preview_service_invoice.jsp?cont_type="+cont_type+"&invDt="+invDt+"&dueDt="+dueDt+
				"&mapping_id="+mapping_id+"&cust_cd="+cust_cd+"&cust_plant_cd="+cust_plant_cd+"&period_end_dt="+period_end_dt+"&period_start_dt="+period_start_dt+"&new_inv_seq_no="+new_inv_seq_no+
				"&tax_size="+tax_desc+"&taxnm_str="+taxnm_str+"&calcBase="+calcBase+
				"&sacStr="+sacStr+"&itemStr="+itemStr+"&qtyStr="+qtyStr+"&rateStr="+rateStr+"&amtStr="+amtStr+"&rowno="+rows+
				"&tax_structure="+tax_structure+"&totalTax="+totalTax+"&netAmt="+netAmt+"&taxAmt_str="+taxAmt_str+"&grossAmt="+grossAmt+"&truckInvSz="+truckInvSz+"&chkBoxFlgStr="+chkBoxFlgStr,"Preview_Service_Invoice","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
		}
}
	</script>
	
</body>
</html>
