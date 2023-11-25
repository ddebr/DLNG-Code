<%@ page import="java.util.*"%>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script language="JavaScript" src="../js/date-picker.js"></script>
<!-- <script language="JavaScript" src="../js/aris.js"></script> -->
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<link rel="stylesheet" href="../css/tlu.css">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<title>Service | Invoice </title>

<script>
function modifyInvoice(cont_type,invDt,dueDt,mapping_id,cust_cd,cust_plant_cd,period_start_dt,period_end_dt,new_inv_seq_no,fin_yr,inv_seq_no,supp_cd)
{
// 	alert('FY');
	var flag = true;
 	var msg = "Following Fields To Be Filled Properly :\n";
 	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	var bill_cycle=document.forms[0].bill_cycle.value;
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	/* //alert("in dfgjd");
	if(yr=='' || yr==' '|| yr==null)
	{
		msg += "Please Select Year !\n";
 		flag = true;
	}
	
 	if(month=='' || month==' '|| month==null)
	{
		msg += "Please Select Month !\n";
 		flag = true;
 	} */
	/* if(flag) {
		alert(msg);
	}else { */
			var url="../general_inv/frm_modify_service_invoice.jsp?cont_type="+cont_type+"&invDt="+invDt+"&dueDt="+dueDt+
			"&mapping_id="+mapping_id+"&cust_cd="+cust_cd+"&cust_plant_cd="+cust_plant_cd+
			"&period_end_dt="+period_end_dt+"&period_start_dt="+period_start_dt+"&new_inv_seq_no="+new_inv_seq_no+
			"&fin_yr="+fin_yr+"&inv_seq_no="+inv_seq_no+"&year="+yr+"&month="+month+"&bill_cycle="+bill_cycle+"&supp_cd="+supp_cd;
// 			alert(url)
			if(!newWindow || newWindow.closed)
			{
				newWindow = window.open(url,"Service_Invoice_Modify","top=10,left=10,width=1050,height=550,scrollbars=1,status=1,menubar=1");
			}
			else
			{
				newWindow.close();
			    newWindow = window.open(url,"Service_Invoice_Modify","top=10,left=10,width=1050,height=550,scrollbars=1,status=1,menubar=1");
			}
		
// 	}
}
function click_inv(FY)
{
	//alert(FY);
	var flag = false;
 	var msg = "Following Fields To Be Filled Properly :\n";
 	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
 	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var bill_cycle=document.forms[0].bill_cycle.value;
	var alwInvGen = document.getElementById('alwInvGen').value;
// 	var supp_cd=document.forms[0].Supp_Cd.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	
	
	//alert("in dfgjd");
	if(yr=='' || yr==' '|| yr==null)
	{
		msg += "Please Select Year !\n";
 		flag = true;
	}
	
 	if(month=='' || month==' '|| month==null)
	{
		msg += "Please Select Month !\n";
 		flag = true;
 	}
 	/* if(alwInvGen == 'N')
	{
		msg += "You Cannot Prepare Invoice for Running/Advance Period !\n";
 		flag = true;
 	} */
	if(flag) {
		alert(msg);
	}else {
// 		alert(bill_cycle)
			var url="../general_inv/frm_prepare_service_invoice.jsp?year="+yr+"&month="+month+"&fin_yr="+FY+"&bill_cycle="+bill_cycle+
					"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&alwInvGen="+alwInvGen;
// 			var url="../general_inv/frm_other_invoices_Z.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type;
// 			location.replace(url);
			
			if(!newWindow || newWindow.closed)
			{
				newWindow = window.open(url,"Service_Invoice","top=10,left=10,width=1050,height=550,scrollbars=1,status=1,menubar=1");
			}
			else
			{
				newWindow.close();
			    newWindow = window.open(url,"Service_Invoice","top=10,left=10,width=1050,height=550,scrollbars=1,status=1,menubar=1");
			}
		
	}
}
function refreshParant(year,month,bill_cycle,msg,error_msg,modUrl,modCd,formId,subModUrl)
{
// 	alert('in')
	var url=modUrl+"?year="+year+"&month="+month+"&bill_cycle="+bill_cycle+"&msg="+msg+"&error_msg="+error_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&modUrl="+modUrl;
// 	alert(url)
	location.replace(url);
}
function change_month()
{
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var bill_cycle=document.forms[0].bill_cycle.value;
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	var url=modUrl+"?year="+yr+"&month="+month+"&bill_cycle="+bill_cycle+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl;
	
// 	var url="../general_inv/frm_other_invoices_dtl.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&supplier_cd="+supp_cd+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission;
	location.replace(url);
}
function viewInv(ind,cont_type,invDt,dueDt,mapping_id,cust_cd,cust_plant_cd,period_start_dt,period_end_dt,new_inv_seq_no,fin_yr,inv_seq_no,inv_flag,ind,size,irn_flg,contact_per_cd,dr_cr_doc_no,dr_cr_irn_cnt){
	
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var bill_cycle=document.forms[0].bill_cycle.value;
	var InvView = document.getElementById('InvView'+ind).value;
	var dr_cr_flag = document.getElementById('dr_cr_flag'+ind).value;
	var invoice_title = document.getElementById('invoice_title'+ind).value;
	
	if(inv_flag=='V' || inv_flag=='A' || inv_flag=='C'){
		
		if(InvView == 'drcr'){
			
			newWindow2 = window.open("../general_inv/frm_view_chk_apr_dr_cr.jsp?cont_type="+cont_type+"&invDt="+invDt+"&dueDt="+dueDt+
					"&mapping_id="+mapping_id+"&cust_cd="+cust_cd+"&cust_plant_cd="+cust_plant_cd+
					"&period_end_dt="+period_end_dt+"&period_start_dt="+period_start_dt+"&new_inv_seq_no="+new_inv_seq_no+
					"&fin_yr="+fin_yr+"&inv_seq_no="+inv_seq_no+"&year="+yr+"&month="+month+"&bill_cycle="+bill_cycle+
					"&inv_flag="+inv_flag+"&dr_cr_flag="+dr_cr_flag+"&invoice_title="+invoice_title+"&dr_cr_doc_no="+dr_cr_doc_no,"Preview_Service_Invoice","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
			
		}else{
			newWindow2 = window.open("../general_inv/view_service_invoice.jsp?cont_type="+cont_type+"&invDt="+invDt+"&dueDt="+dueDt+
			"&mapping_id="+mapping_id+"&cust_cd="+cust_cd+"&cust_plant_cd="+cust_plant_cd+
			"&period_end_dt="+period_end_dt+"&period_start_dt="+period_start_dt+"&new_inv_seq_no="+new_inv_seq_no+
			"&fin_yr="+fin_yr+"&inv_seq_no="+inv_seq_no+"&year="+yr+"&month="+month+"&bill_cycle="+bill_cycle+"&inv_flag="+inv_flag,"Preview_Service_Invoice","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
		}
	}else if(inv_flag=='P'){
// 		alert(contact_per_cd)
		 if(contact_per_cd == '' && contact_per_cd == ' '){
			alert("Contact Person is not available so you cannot print PDF");
		}else{	
			
			var cust_abbr = document.getElementById('cust_abbr'+ind).value;
			var plant_nm = document.getElementById('plant_nm'+ind).value;
			var invoice_title = document.getElementById('invoice_title'+ind).value;
// 			alert(invoice_title)
			document.getElementById('index_hid').value = ind;
			document.getElementById('size_hid').value = size;
			
			if(invoice_title.indexOf('CREDIT')!=-1 || invoice_title.indexOf('DEBIT')!=-1){
				if(parseFloat(dr_cr_irn_cnt) <= 0){
					alert("IRN Number/ QR code is not available so you cannot print PDF");
				}else{
					newWindow2 = window.open("../general_inv/print_pdf_dr_cr_note.jsp?cont_type="+cont_type+"&invDt="+invDt+"&dueDt="+dueDt+
						"&mapping_id="+mapping_id+"&cust_cd="+cust_cd+"&cust_plant_cd="+cust_plant_cd+
						"&period_end_dt="+period_end_dt+"&period_start_dt="+period_start_dt+"&new_inv_seq_no="+new_inv_seq_no+
						"&fin_yr="+fin_yr+"&inv_seq_no="+inv_seq_no+"&year="+yr+"&month="+month+"&bill_cycle="+bill_cycle+
						"&inv_flag="+inv_flag+"&dr_cr_flag="+dr_cr_flag+"&invoice_title="+invoice_title+"&dr_cr_doc_no="+dr_cr_doc_no+"&customer_abbr="+cust_abbr+"&customer_plant_nm="+plant_nm,"Print_PDF_Service_Invoice","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
				}
			}else{
				 if(irn_flg!='Y'){
						alert("IRN Number/ QR code is not available so you cannot print PDF");
				}else{
					newWindow2 = window.open("../general_inv/print_pdf_service_invoice.jsp?cont_type="+cont_type+"&invDt="+invDt+"&dueDt="+dueDt+
						"&mapping_id="+mapping_id+"&cust_cd="+cust_cd+"&cust_plant_cd="+cust_plant_cd+
						"&period_end_dt="+period_end_dt+"&period_start_dt="+period_start_dt+"&new_inv_seq_no="+new_inv_seq_no+"&fin_yr="+fin_yr+"&inv_seq_no="+inv_seq_no+
						"&invoice_title="+invoice_title+"&customer_abbr="+cust_abbr+"&customer_plant_nm="+plant_nm+"&year="+yr+"&month="+month+"&bill_cycle="+bill_cycle+
						"&irn_flg="+irn_flg,"Print_PDF_Service_Invoice","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
				}
			}
			
		}	
	}	
}

function refreshPage1()
{
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var bill_cycle=document.forms[0].bill_cycle.value;
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	var inv_title_string=document.forms[0].inv_title_string.value;
// 	alert('here='+yr)
	location.replace(modUrl+"?inv_title_string="+inv_title_string+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&year="+yr+"&month="+month+"&bill_cycle="+bill_cycle);
}

function checkPrint(ind,printStr,selCopy,c_cnt,c_cnt_sign,c_cnt_mail_sign,cd_cnt,cd_cnt_sign,cd_cnt_mail_sign,
		cred_T,ct_cnt_sign,ct_cnt_mail_sign,de_cnt,de_cnt_sign,de_cnt_mail_sign,ded_cnt,ded_cnt_sign,
		ded_cnt_mail_sign,deb_T,det_cnt_sign,det_cnt_mail_sign,dr_cr_aprv_by){
// 	alert('in--'+deb_T)

	document.getElementById("InvView"+ind).value = "View Inv";
	document.getElementById("InvView"+ind).innerHTML = "View Inv";
	if(selCopy == 'ORIGINAL'){
		
		if(printStr.includes("O")){
			
			document.getElementById('printPDF'+ind).disabled = true;
			document.getElementById('signPDF'+ind).disabled = false;
			
			var osign_cnt = document.getElementById('osign_cnt'+ind).value;
			if(parseFloat(osign_cnt) > 0){
				document.getElementById('viewPDF'+ind).disabled = false;
			}else{
				document.getElementById('viewPDF'+ind).disabled = true;
			}
		}else{
			
			document.getElementById('printPDF'+ind).disabled = false;
			document.getElementById('signPDF'+ind).disabled = true;
			
			var osign_cnt = document.getElementById('osign_cnt'+ind).value;
			if(parseFloat(osign_cnt) > 0){
				document.getElementById('viewPDF'+ind).disabled = false;
			}else{
				document.getElementById('viewPDF'+ind).disabled = true;
			}
		}
	}else if(selCopy == 'DUPLICATE'){
		
		if(printStr.includes("D")){
			
			document.getElementById('printPDF'+ind).disabled = true;
			document.getElementById('signPDF'+ind).disabled = false;
			
			var dsign_cnt = document.getElementById('dsign_cnt'+ind).value;
			if(parseFloat(dsign_cnt) > 0){
				document.getElementById('viewPDF'+ind).disabled = false;
			}else{
				document.getElementById('viewPDF'+ind).disabled = true;
			}
			
		}else{
			document.getElementById('printPDF'+ind).disabled = false;
			document.getElementById('signPDF'+ind).disabled = true;
			
			var dsign_cnt = document.getElementById('dsign_cnt'+ind).value;
			if(parseFloat(dsign_cnt) > 0){
				document.getElementById('viewPDF'+ind).disabled = false;
			}else{
				document.getElementById('viewPDF'+ind).disabled = true;
			}
		}
		
	}else if(selCopy == 'TRIPLICATE'){
// 		alert('in')
		if(printStr.includes("T")){
			
			document.getElementById('printPDF'+ind).disabled = true;
			document.getElementById('signPDF'+ind).disabled = false;
			
			var tsign_cnt = document.getElementById('tsign_cnt'+ind).value;
			if(parseFloat(tsign_cnt) > 0){
				document.getElementById('viewPDF'+ind).disabled = false;
			}else{
				document.getElementById('viewPDF'+ind).disabled = true;
			}
			
		}else{
			
			document.getElementById('printPDF'+ind).disabled = false;
			document.getElementById('signPDF'+ind).disabled = true;
			
			var tsign_cnt = document.getElementById('tsign_cnt'+ind).value;
			if(parseFloat(tsign_cnt) > 0){
				document.getElementById('viewPDF'+ind).disabled = false;
			}else{
				document.getElementById('viewPDF'+ind).disabled = true;
			}
		}
	}else if(selCopy=='CREDITO' || selCopy=='CREDITD' || selCopy=='CREDITT' || selCopy=='DEBITO' || selCopy=='DEBITD' || selCopy=='DEBITT' || selCopy=="CR_signO" || selCopy=="CR_signD" || selCopy=="CR_signT" || selCopy=="DE_signO" || selCopy=="DE_signD" || selCopy=="DE_signT") {
		
		document.getElementById("InvView"+ind).disabled = false;
		document.getElementById('signPDF'+ind).disabled=true;
		document.getElementById('printPDF'+ind).disabled=true;//InvPdf
		document.getElementById('sendMail'+ind).disabled=true;
		document.getElementById('viewPDF'+ind).disabled=true;//InvPdf1_view
// 		alert(dr_cr_aprv_by)
		if(parseFloat(dr_cr_aprv_by) > 0 ){ // for approved CR/DR Note
			document.getElementById('printPDF'+ind).disabled = false;
		}
		
		if(selCopy=='CREDITO' || selCopy=='CREDITD' || selCopy=='CREDITT' || selCopy=="CR_signO" || selCopy=="CR_signD" || selCopy=="CR_signT") {
			if(parseFloat(dr_cr_aprv_by) > 0){
				document.getElementById("InvView"+ind).innerHTML = "View Credit";	
			}else{
				document.getElementById("InvView"+ind).innerHTML = "View / Approve Credit";	
			}
		} else {
			if(parseFloat(dr_cr_aprv_by) > 0){
				document.getElementById("InvView"+ind).innerHTML = "View Debit";
			}else{
				document.getElementById("InvView"+ind).innerHTML = "View / Approve Debit";
			}
		}
		document.getElementById("InvView"+ind).value = "drcr";
		
		if(selCopy=='CREDITO' || selCopy=='CR_signO') {
			if(parseFloat(c_cnt) > 0 && parseFloat(dr_cr_aprv_by) > 0){
				
				document.getElementById('viewPDF'+ind).disabled=false;
				document.getElementById('printPDF'+ind).disabled=true;
				
				if(c_cnt_sign == 0 ){
					document.getElementById('signPDF'+ind).disabled=false;
				}else{
					document.getElementById('signPDF'+ind).disabled=true;
					document.getElementById('sendMail'+ind).disabled=false;
				}
			}else{
				document.getElementById('viewPDF'+ind).disabled=true;
				if(parseFloat(dr_cr_aprv_by) > 0){
					document.getElementById('printPDF'+ind).disabled=false;	
				}
			}
		} else if(selCopy=='CREDITD' || selCopy=='CR_signD'){
//				alert("cd_cnt***"+cd_cnt+"***"+cr_flg)
			if(cd_cnt>0 && parseFloat(dr_cr_aprv_by) > 0){
				
				document.getElementById('viewPDF'+ind).disabled=false;
				document.getElementById('printPDF'+ind).disabled=true;
				
				if(cd_cnt_sign == 0 ){
					document.getElementById('signPDF'+ind).disabled=false;
				}else{
					document.getElementById('signPDF'+ind).disabled=true;
					document.getElementById('sendMail'+ind).disabled=false;
				}
			}else{
				document.getElementById('viewPDF'+ind).disabled=true;
				if(parseFloat(dr_cr_aprv_by) > 0){
					document.getElementById('printPDF'+ind).disabled=false;	
				}
			}
		}else if(selCopy=='CREDITT' || selCopy=='CR_signT'){
//				alert("cd_cnt***"+cd_cnt+"***"+cr_flg)
			if(cred_T>0 && parseFloat(dr_cr_aprv_by) > 0){
				
				document.getElementById('viewPDF'+ind).disabled=false;
				document.getElementById('printPDF'+ind).disabled=true;
				if(ct_cnt_sign == 0 ){
					document.getElementById('signPDF'+ind).disabled=false;
				}else{
					document.getElementById('signPdf'+ind).disabled=true;
					document.getElementById('sendMail'+ind).disabled=false;
				}
			}else{
				document.getElementById('viewPDF'+ind).disabled=true;
				if(parseFloat(dr_cr_aprv_by) > 0){
					document.getElementById('printPDF'+ind).disabled=false;	
				}
			}
		}else{
			document.getElementById('printPDF'+ind).disabled=true;
			document.getElementById('viewPDF'+ind).disabled=false;
		}
		if(selCopy=='DEBITO' || selCopy=='DE_signO') {
			if(parseFloat(de_cnt) > 0 && parseFloat(dr_cr_aprv_by) > 0){
				document.getElementById('viewPDF'+ind).disabled=false;
				document.getElementById('printPDF'+ind).disabled=true;
				if(parseFloat(de_cnt_sign) == 0 ){
					document.getElementById('signPDF'+ind).disabled=false;
				}else{
					document.getElementById('signPDF'+ind).disabled=true;
					document.getElementById('sendMail'+ind).disabled=false;
				}
			}else{
				document.getElementById('viewPDF'+ind).disabled=true;
				if(parseFloat(dr_cr_aprv_by) > 0){
					document.getElementById('printPDF'+ind).disabled=false;	
				}
			} 
		} else if(selCopy=='DEBITD' || selCopy=='DE_signD') {
			if(ded_cnt > 0 && parseFloat(dr_cr_aprv_by) > 0){
				
				document.getElementById('viewPDF'+ind).disabled=false;
				document.getElementById('printPDF'+ind).disabled=true;
				
				if(ded_cnt_sign == 0 ){
					document.getElementById('signPDF'+ind).disabled=false;
				}else{
					document.getElementById('signPDF'+ind).disabled=true;
					document.getElementById('sendMail'+ind).disabled=false;
				}
			}else{
				document.getElementById('viewPDF'+ind).disabled=true;
				if(parseFloat(dr_cr_aprv_by) > 0){
					document.getElementById('printPDF'+ind).disabled=false;	
				}
			} 
		}else if(selCopy=='DEBITT' || selCopy=='DE_signT') {
			
			if(deb_T>0 && parseFloat(dr_cr_aprv_by) > 0){
				
				document.getElementById('viewPDF'+ind).disabled=false;
				document.getElementById('printPDF'+ind).disabled=true;
				
				if(det_cnt_sign == 0 ){
					document.getElementById('signPDF'+ind).disabled=false;
				}else{
					document.getElementById('signPDF'+ind).disabled=true;
					document.getElementById('sendMail'+ind).disabled=false;
				}
			}else{
				document.getElementById('viewPDF'+ind).disabled=true;
				if(parseFloat(dr_cr_aprv_by) > 0){
					document.getElementById('printPDF'+ind).disabled=false;	
				}
			} 
		}else{
			if(selCopy!='CREDITT' && selCopy!='CREDITD' && selCopy!='CREDITO'){
				document.getElementById('printPDF'+ind).disabled=true;
				document.getElementById('viewPDF'+ind).disabled=false;	
			}
		}
	}else{
		
		document.getElementById("InvView"+ind).value = "View Inv";
		document.getElementById("InvView"+ind).innerHTML = "View Inv";
	}
}

function openJarTab(){
	
	var url="../PDF_signer.jar";
	window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1");
}
var invoicePdf;
var newWindow223;
function viewInvPDF(ind,path,path_o,path_d,path_t,obj,pdf_type,contract_type,customer_cd,contact_cd,customer_plant_seq_no,invoice_dt,hlpl_inv_no){
// 	alert('in')
	var invoice_path="";
	var pdf_nm=path.split(",");
	var invoice_title = "";
	var inv_flag="";
	var path="";
	var invoice_title = document.getElementById('invoice_title'+ind).value;
	
	var year=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var bill_cycle=document.forms[0].bill_cycle.value;
	
	for(var i=0;i<pdf_nm.length;i++){
		
		var  pdf_inv_type=pdf_nm[i].toString().substring(pdf_nm[i].length-1);
		
		if(invoice_title=='ORIGINAL' && pdf_inv_type=='O'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='DUPLICATE' && pdf_inv_type=='D'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='TRIPLICATE' && pdf_inv_type=='T'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}
	} 
	if(invoice_title=='ORIGINAL') {
		path = path_o;
	} else if(invoice_title=='DUPLICATE') {
		//alert("here");
		path = path_d;
	} else if(invoice_title == 'TRIPLICATE') {
		path = path_t;
	}
	
// 	alert(invoice_path)
	if(pdf_type =='V'){
		if(!invoicePdf || invoicePdf.closed)
		{	
			invoicePdf = window.open("../general_inv/pdf_view_service_invoice.jsp?invoice_path="+invoice_path+"&pdf_type="+pdf_type+"&contract_type="+contract_type+"&invoice_title="+invoice_title,"Rpt_InvoiceDtl1","top=10,left=10,width=1,height=1,scrollbars=1,menubar=1");
		}
		else
		{
			invoicePdf = window.open("../general_inv/pdf_view_service_invoice.jsp?invoice_path="+invoice_path+"&pdf_type="+pdf_type+"&invoice_title="+invoice_title,"Rpt_InvoiceDtl2","top=10,left=10,width=1,height=1,scrollbars=1,menubar=1");
		} 
	}else if(pdf_type =='E'){
		
		if(contract_type=='V'){
			inv_flag="SERVICE_";	
		}
		
		if(!newWindow223 || newWindow223.closed){
			newWindow223 = window.open("frm_sign_pdf_mail.jsp?invoice_path="+path+"&pdf_type="+pdf_type+
					"&invoice_title="+invoice_title+"&inv_flag="+inv_flag+"&inv_type="+invoice_title+
					"&customer_cd="+customer_cd+"&contact_cd="+contact_cd+"&customer_plant_seq_no="+customer_plant_seq_no+
					"&invoice_dt="+invoice_dt+"&contract_type="+contract_type+"&hlpl_inv_no="+hlpl_inv_no+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle,
					"Send Mail","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
		}
		else{
			newWindow223 = window.open("frm_sign_pdf_mail.jsp?invoice_path="+path+"&pdf_type="+pdf_type+
					"&invoice_title="+invoice_title+"&inv_flag="+inv_flag+"&inv_type="+invoice_title+
					"&customer_cd="+customer_cd+"&contact_cd="+contact_cd+"&customer_plant_seq_no="+customer_plant_seq_no+
					"&invoice_dt="+invoice_dt+"&contract_type="+contract_type+"&hlpl_inv_no="+hlpl_inv_no+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle,
					"Send Mail","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
		}
	}
	
}

function refreshPage11(msg)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	var bill_cycle = document.forms[0].bill_cycle.value;
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var bill_cycle=document.forms[0].bill_cycle.value;
	var inv_title_string=document.forms[0].inv_title_string.value;
	
	location.replace(modUrl+"?msg="+msg+"&month="+month+"&inv_title_string="+inv_title_string+"&year="+yr+"&bill_cycle="+bill_cycle+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl);
}

function refreshPageFromChild2(msg, month, year, bill_cycle, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	location.replace(modUrl+"?msg="+msg+"&bill_cycle="+bill_cycle+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl);
}
function refreshPageFromChild3(year,month,bill_cycle,msg,error_msg){
	
// 	alert('inn');
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	location.replace(modUrl+"?msg="+msg+"&bill_cycle="+bill_cycle+"&formId="+formId+
			"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&year="+year+"&month="+month+"&error_msg="+error_msg);
}


</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice" id="dta" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
util.init();
String sysdate = util.getGen_dt();
String curr_year = util.getGet_yr();
String curr_mth = util.getGen_month();

String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String error_msg=request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
String year=request.getParameter("year")==null?curr_year:request.getParameter("year");
String month=request.getParameter("month")==null?curr_mth:request.getParameter("month");
String inv_type=request.getParameter("inv_type")==null?"Z":request.getParameter("inv_type");
String supplier_cd=request.getParameter("supplier_cd")==null?"1":request.getParameter("supplier_cd");
String bill_cycle=request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");

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
	
String inv_title_string = request.getParameter("inv_title_string")==null?"":request.getParameter("inv_title_string");

HttpServletRequest req = request;

dta.setYear(year);
dta.setMonth(month);
dta.setBill_cycle(bill_cycle);

dta.setCallFlag("FETCH_GEN_INVOICE_DTL");
dta.init();
String period_start_dt = dta.getPeriod_start_dt();
String period_end_dt = dta.getPeriod_end_dt();

Vector yearList = dta.getYearList();
Vector VinvCustomer_cd = dta.getVinvCustomer_cd();
Vector VinvCustomer_abbr =  dta.getVinvCustomer_abbr();
Vector VinvMapping_id =  dta.getVinvMapping_id();
Vector VinvContNo =  dta.getVinvContNo();
Vector VinvContType =  dta.getVinvContType();
Vector VinvPlant_seq_no =  dta.getVinvPlant_seq_no();
Vector VinvPlant_name =  dta.getVinvPlant_name();
Vector VinvNew_inv_seq_no =  dta.getVinvNew_inv_seq_no();
Vector VinvInv_seq_no =  dta.getVinvInv_seq_no();
Vector VinvInvoice_dt =  dta.getVinvInvoice_dt();
Vector VinvInvoice_due_dt = dta.getVinvInvoice_due_dt();
Vector VinvInvoice_fin_yr = dta.getVinvInvoice_fin_yr();
String alwInvGen = dta.getAlwInvGen();
Vector VinvInvoice_print = dta.getVinvInvoice_print();
Vector VinvInvoice_bgColor = dta.getVinvInvoice_bgColor();
Vector VinvInvoice_cont_cd = dta.getVinvInvoice_cont_cd();
Vector VinvInvoice_supp_cd = dta.getVinvInvoice_supp_cd();
Vector VinvChecked_by = dta.getVinvChecked_by();
Vector VinvChecked_flag = dta.getVinvChecked_flag();
Vector VinvApproved_by = dta.getVinvApproved_by();
Vector VinvApproved_flag = dta.getVinvApproved_flag();

Vector irn_flag = dta.getIrn_flag();
String inv_title[]=new String[VinvInv_seq_no.size()];
boolean flag= false;

if(inv_title_string.equalsIgnoreCase(""))
{

}
else
{
	flag=true;
	inv_title=inv_title_string.split("-");
}

Map invoice_view_pdf=dta.getInvoice_view_pdf();
Map invoice_view_signed_pdf = dta.getInvoice_view_signed_pdf();
Map Invoice_mail_sent = dta.getInvoice_mail_sent();

Vector dr_cr_doc_no = dta.getDr_cr_doc_no();
Vector dr_cr_flag = dta.getDr_cr_flag();
// Vector dr_cr_dt = dta.getDr_cr_dt();
Vector dr_cr_aprv_by = dta.getDr_cr_aprv_by();
// Vector dr_cr_aprv_dt = dta.getDr_cr_aprv_dt();
// Vector dr_cr_criteria = dta.getDr_cr_criteria();
// Vector dr_cr_remark = dta.getDr_cr_remark();
Vector dr_cr_inv_flag = dta.getDr_cr_inv_flag();
Vector dr_cr_irn_cnt = dta.getDr_cr_irn_cnt();
// System.out.println("dr_cr_flag****"+dr_cr_flag);

%>
<div class="tab-content">
<div class="tab-pane active" id="invoicing">
<form method="post"  action="">

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="modUrl" value="<%=modUrl%>">
<input type="hidden" name="alwInvGen" id="alwInvGen"  value="<%=alwInvGen%>">
<input type="hidden" name="index_hid" id="index_hid" value="">
<input type="hidden" name="size_hid" id="size_hid" value="0">
<input type="hidden" name="inv_title_string" id="inv_title_string" value="<%=inv_title_string%>">

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

<%if(error_msg.length()>5) { %>
	<div class="box-body table-responsive no-padding">
	<table class="table  table-bordered">
		<thead>   
			<tr class="info">
				<th class="text-center"  style="color: red;"><%=error_msg %></th>
			</tr>
		</thead>
	</table>
</div>
<%} %>

		<div class="box-header with-border main-header" >
			<div class="form-group col-md-2">
		 	 <label for="">Year</label>
				<select class="form-control" name="year" id="year" onchange="change_month();">	
				<option value="0">--Select--</option>				
				  <%for(int i=0 ; i < yearList.size(); i++) {%> 
				  <option value="<%=yearList.elementAt(i)%>"><%=yearList.elementAt(i)%></option>
			  <%}%>					  
			</select>			
			<script>
			 		document.forms[0].year.value="<%=year%>";
			 </script>
		 </div>
		 
			<div class="form-group col-md-2">
		 		<label for="">Month</label>
		 		<select class="form-control" name="month" id="month"  onchange="change_month();">
					<option value="0">--Select--</option>
					<option value="01">January</option>
					<option value="02">February</option>
					<option value="03">March</option>
					<option value="04">April</option>
					<option value="05">May</option>
					<option value="06">June</option>
					<option value="07">July</option>
					<option value="08">August</option>
					<option value="09">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
				</select>
				<script>
				 		document.forms[0].month.value="<%=month%>";
				 </script>
		 	</div>	
		 	<div class="form-group col-md-2">
		 		<label for="">Billing Cycle</label>
			 		<select name="bill_cycle" id="bill_cycle" class="form-control" onchange="change_month();">
						<option value="0">--Select--</option>
						<option value="1">1st-Fortnight</option>
						<option value="2">2nd-Fortnight</option>
						<option value="7">Monthly</option>
						<!-- <option value="3">1st-Weekly</option>
						<option value="4">2nd-Weekly</option>
						<option value="5">3rd-Weekly</option>
						<option value="6">4th-Weekly</option>
						<option value="9">5th-Weekly</option> -->
						
<!-- 						<option value="8">Other</option> -->
					</select>
		 		<script>
					document.forms[0].bill_cycle.value="<%=bill_cycle%>";
				</script>
		 	</div>
		 	
		 	<div class="form-group col-md-2" title="Click here to Prepare New Invoice">
				<label for="">&nbsp;</label>
			 	  <div class='input-group'>
			 	  	<button type="button"  class="btn btn-primary"  name="btn"  value="Prepare New Invoice" onclick="click_inv('<%//=Fin_yr %>');" >Prepare New Invoice</button>
			     </div>
			 </div>	
		</div>
		
		<div class="box-body table-responsive no-padding">
			<table class="table  table-bordered" style="overflow-x:scroll;"  >	
			  <thead> 
						<tr>
							<th colspan="12" class="text-center info">
								<font color="">List of Service Invoice(s) Generated During the Period <%=period_start_dt %> to <%=period_end_dt %></font>
							</th>
						</tr>
						<tr class="info">
							<th  class="text-center">CUSTOMER</th>
							<th  class="text-center">SN/LOA No.</th>
							<th  class="text-center">PLANT</th>
							<th  class="text-center">INVOICE SEQ NO</th>
							<th  class="text-center">INVOICE DATE</th>
							
							<th  class="text-center">ACTIVITY-I</th>
							<th  class="text-center">ACTIVITY-II</th>
							<th  class="text-center">ACTIVITY-III</th>
							<th  class="text-center">ACTIVITY-IV</th>
<!-- 							<th  class="text-center">ACTIVITY-V</th> -->
							
							<th  class="text-center">INVOICE TITLE</th>
							<th  class="text-center">ARCHIVED PDF</th>
							<th  class="text-center">SEND MAIL</th>
						</tr>
						</thead>
						<tbody>
						<%
// 						System.out.println("VinvCustomer_cd.size()****"+VinvCustomer_cd.size());
						
						for(int i = 0 ; i <VinvCustomer_cd.size(); i++){ 
							String rowcolor="";
							
							Vector invoice_path=new Vector();
							Vector invoice_signed_path = new Vector();
							Vector invoice_mail_sent_flag = new Vector();
							
					 		String invoice[]=invoice_view_pdf.get(i).toString().replace("[", "").replace("]", "").split(",");
					 		String invoice_sign[] = invoice_view_signed_pdf.get(i).toString().replace("[", "").replace("]", "").split(",");
					 		String invoice_mail_flag[]=Invoice_mail_sent.get(i).toString().replace("[", "").replace("]", "").split(","); 
					 		
					 		for(int p1=0;p1<invoice.length;p1++){
// 				 	 			System.out.println("invoice[p1]****"+invoice[p1]);
					 			invoice_path.add(invoice[p1].trim());
					 			invoice_signed_path.add(invoice_sign[p1].trim());
					 			invoice_mail_sent_flag.add(invoice_mail_flag[p1].trim());
					 		}
						%>
						
						<%int o_cnt=0,d_cnt=0,t_cnt=0,c_cnt=0,s_cnt=0,de_cnt=0,sd_cnt=0,cd_cnt=0,ded_cnt=0,cred_T=0,deb_T=0;
					 		String o_filename="",d_filename="",t_filename="",c_filename="",s_filename="",de_filename="",cd_filename="",sd_filename="",ded_filename="",credT_filename="",debT_filename="";
					 		int osign_cnt = 0, dsign_cnt = 0, tsign_cnt = 0,osign_mail_cnt=0,dsign_mail_cnt=0,tsign_mail_cnt=0 ; 
					 		int c_cnt_sign = 0,c_cnt_mail_sign = 0,cd_cnt_sign=0,cd_cnt_mail_sign=0,ct_cnt_sign=0,ct_cnt_mail_sign=0 ;
					 		int de_cnt_sign = 0,de_cnt_mail_sign=0,ded_cnt_sign=0,ded_cnt_mail_sign=0,det_cnt_sign=0,det_cnt_mail_sign=0;
					 		
					 		String s1=session.getAttribute("invoice_pdf_path").toString();
							String p2="/pdf_reports/pdf_files";
							
							for(int l=0;l<invoice_path.size();l++){
								
								String inv_ty="";
								if(!invoice_path.elementAt(l).toString().equalsIgnoreCase("")){
									inv_ty=invoice_path.elementAt(l).toString().substring(invoice_path.elementAt(l).toString().length()-1);
								}
								String inv__flag = "";
								if (!invoice_path.elementAt(l).toString().equalsIgnoreCase("")) {
									inv__flag = invoice_signed_path.elementAt(l).toString().trim();
								}
				// 				System.out.println("inv__flag******"+inv__flag);
								String inv_mail_flag = "";
								if (!invoice_path.elementAt(l).toString().equalsIgnoreCase("")) {
									inv_mail_flag = invoice_mail_sent_flag.elementAt(l).toString().trim();
								}
								//String temp=invoice_path.elementAt(l).toString().substring(0,invoice_path.elementAt(l).toString().length()-4);
								if(inv_ty.equalsIgnoreCase("O") ){
									o_cnt++;
									o_filename=invoice_path.elementAt(l).toString();
									
									if(inv__flag.equalsIgnoreCase("Y")){
										osign_cnt++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										osign_mail_cnt++;											
									}
									%>
								<%}
								if(inv_ty.equals("D")){
									d_cnt++;
									d_filename=invoice_path.elementAt(l).toString();
									if(inv__flag.equalsIgnoreCase("Y")){
										dsign_cnt++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										dsign_mail_cnt++;											
									}
									
									%>
								<%}
								if(inv_ty.equalsIgnoreCase("T")){
									t_cnt++;
									t_filename=invoice_path.elementAt(l).toString();
									
									if(inv__flag.equalsIgnoreCase("Y")){
										tsign_cnt++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										tsign_mail_cnt++;											
									}
									%>
								<%}%>
								
								<%if(inv_ty.equalsIgnoreCase("C") && dr_cr_inv_flag.elementAt(i).toString().equalsIgnoreCase("X")){ // for (0) Origional Credit Note
									c_cnt++;
									c_filename=invoice_path.elementAt(l).toString();
									if(inv__flag.equalsIgnoreCase("Y")){
										c_cnt_sign++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										c_cnt_mail_sign++;											
									}
								}	
								if(inv_ty.equalsIgnoreCase("1") && dr_cr_inv_flag.elementAt(i).toString().equalsIgnoreCase("X")){
									cd_cnt++;
									cd_filename=invoice_path.elementAt(l).toString();
									if(inv__flag.equalsIgnoreCase("Y")){
										cd_cnt_sign++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										cd_cnt_mail_sign++;											
									}
									%>
								<%}
								if(inv_ty.equalsIgnoreCase("2") && dr_cr_inv_flag.elementAt(i).toString().equalsIgnoreCase("X")){
									cred_T++;
									credT_filename=invoice_path.elementAt(l).toString();
				// 					System.out.println("inv__flag*************************************"+credT_filename);
									if(inv__flag.equalsIgnoreCase("Y")){
										ct_cnt_sign++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										ct_cnt_mail_sign++;											
									}
									%>
								<%}
								if(inv_ty.equals("d") && dr_cr_inv_flag.elementAt(i).toString().equalsIgnoreCase("Y")){ 
									de_cnt++;
									de_filename=invoice_path.elementAt(l).toString();
									
									if(inv__flag.equalsIgnoreCase("Y")){
										de_cnt_sign++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										de_cnt_mail_sign++;											
									}
									%>
								<%}
								if(inv_ty.equalsIgnoreCase("1") && dr_cr_inv_flag.elementAt(i).toString().equalsIgnoreCase("Y")){
									ded_cnt++;
									ded_filename=invoice_path.elementAt(l).toString();
									
									if(inv__flag.equalsIgnoreCase("Y")){
										ded_cnt_sign++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										ded_cnt_mail_sign++;											
									}
									%>
								<%}
								if(inv_ty.equalsIgnoreCase("2") && dr_cr_inv_flag.elementAt(i).toString().equalsIgnoreCase("Y")){
									deb_T++;
									debT_filename=invoice_path.elementAt(l).toString();
									
									if(inv__flag.equalsIgnoreCase("Y")){
										det_cnt_sign++;											
									}
									if(inv_mail_flag.equalsIgnoreCase("Y")){
										det_cnt_mail_sign++;											
									}
									%>
								<%}
							} 
							
							if(!dr_cr_flag.elementAt(i).equals("")){
								rowcolor="#DBDB70";
							}
				// 			System.out.println("osign_cnt---"+osign_cnt+"***dsign_cnt--"+dsign_cnt+"***tsign_cnt---"+tsign_cnt);
							%>
							<tr class="text-center" style="background-color: <%=rowcolor%>">
								<td ><%=VinvCustomer_abbr.elementAt(i) %>
									<input type="hidden" name="cust_abbr" id="cust_abbr<%=i %>" value="<%=VinvCustomer_abbr.elementAt(i) %>">
								</td>
								<td> <%=VinvContNo.elementAt(i) %></td>
								<td><%=VinvPlant_name.elementAt(i) %>
									<input type="hidden" name="plant_nm" id="plant_nm<%=i %>" value="<%=VinvPlant_name.elementAt(i) %>">
								</td>
								<td style="background-color: <%=VinvInvoice_bgColor.elementAt(i)%>"><%=VinvNew_inv_seq_no.elementAt(i) %></td>
								<td><%=VinvInvoice_dt.elementAt(i) %></td>
								
								<td>
								<%if(!VinvChecked_flag.elementAt(i).equals("Y") && !VinvApproved_flag.elementAt(i).equals("Y") ) {%>
								
									<button type="button" class="btn btn-block btn-primary" id="InvView<%=i %>" name="InvView" value="InvView" 
									onclick="viewInv('<%=i %>','<%=VinvContType.elementAt(i)%>','<%=VinvInvoice_dt.elementAt(i)%>',
									'<%=VinvInvoice_due_dt.elementAt(i)%>','<%=VinvMapping_id.elementAt(i)%>',
									'<%=VinvCustomer_cd.elementAt(i)%>','<%=VinvPlant_seq_no.elementAt(i)%>',
									'<%=period_start_dt%>','<%=period_end_dt%>','<%=VinvNew_inv_seq_no.elementAt(i)%>',
									'<%=VinvInvoice_fin_yr.elementAt(i)%>','<%=VinvInv_seq_no.elementAt(i)%>','C','<%=i%>',
									'<%=VinvInv_seq_no.size()%>','<%=irn_flag.elementAt(i) %>','<%=VinvInvoice_cont_cd.elementAt(i)%>','<%=dr_cr_doc_no.elementAt(i) %>','<%=dr_cr_irn_cnt.elementAt(i)%>');">Check Inv</button>
								
								<%}else if(VinvChecked_flag.elementAt(i).equals("Y") && !VinvApproved_flag.elementAt(i).equals("Y")){ %>
								
									<button type="button" class="btn btn-block btn-primary" id="InvView<%=i %>" name="InvView" value="InvView" 
									onclick="viewInv('<%=i %>','<%=VinvContType.elementAt(i)%>','<%=VinvInvoice_dt.elementAt(i)%>',
									'<%=VinvInvoice_due_dt.elementAt(i)%>','<%=VinvMapping_id.elementAt(i)%>',
									'<%=VinvCustomer_cd.elementAt(i)%>','<%=VinvPlant_seq_no.elementAt(i)%>',
									'<%=period_start_dt%>','<%=period_end_dt%>','<%=VinvNew_inv_seq_no.elementAt(i)%>',
									'<%=VinvInvoice_fin_yr.elementAt(i)%>','<%=VinvInv_seq_no.elementAt(i)%>','A','<%=i%>',
									'<%=VinvInv_seq_no.size()%>','<%=irn_flag.elementAt(i) %>','<%=VinvInvoice_cont_cd.elementAt(i)%>','<%=dr_cr_doc_no.elementAt(i) %>','<%=dr_cr_irn_cnt.elementAt(i)%>');">Approve Inv</button>
								
								<%}else if(VinvChecked_flag.elementAt(i).equals("Y") && VinvApproved_flag.elementAt(i).equals("Y")){ %>
									<button type="button" class="btn btn-block btn-primary" id="InvView<%=i %>" name="InvView" value="InvView" 
									onclick="viewInv('<%=i %>','<%=VinvContType.elementAt(i)%>','<%=VinvInvoice_dt.elementAt(i)%>',
									'<%=VinvInvoice_due_dt.elementAt(i)%>','<%=VinvMapping_id.elementAt(i)%>',
									'<%=VinvCustomer_cd.elementAt(i)%>','<%=VinvPlant_seq_no.elementAt(i)%>',
									'<%=period_start_dt%>','<%=period_end_dt%>','<%=VinvNew_inv_seq_no.elementAt(i)%>',
									'<%=VinvInvoice_fin_yr.elementAt(i)%>','<%=VinvInv_seq_no.elementAt(i)%>','V','<%=i%>',
									'<%=VinvInv_seq_no.size()%>','<%=irn_flag.elementAt(i) %>','<%=VinvInvoice_cont_cd.elementAt(i)%>','<%=dr_cr_doc_no.elementAt(i) %>','<%=dr_cr_irn_cnt.elementAt(i)%>');">View Inv</button>
								<%} %>
								</td>
								
								
								<td><button type="button" class="btn btn-block btn-primary" id="modifyInv<%=i %>" name="modifyInv" value="ModifyInv" 
									onclick="modifyInvoice('<%=VinvContType.elementAt(i)%>','<%=VinvInvoice_dt.elementAt(i)%>',
									'<%=VinvInvoice_due_dt.elementAt(i)%>','<%=VinvMapping_id.elementAt(i)%>',
									'<%=VinvCustomer_cd.elementAt(i)%>','<%=VinvPlant_seq_no.elementAt(i)%>',
									'<%=period_start_dt%>','<%=period_end_dt%>','<%=VinvNew_inv_seq_no.elementAt(i)%>',
									'<%=VinvInvoice_fin_yr.elementAt(i)%>','<%=VinvInv_seq_no.elementAt(i)%>',
									'<%=VinvInvoice_supp_cd.elementAt(i) %>');" 
									
									<%if(!VinvInvoice_print.elementAt(i).toString().equals("") || (VinvChecked_flag.elementAt(i).equals("Y") && VinvApproved_flag.elementAt(i).equals("Y"))) {%>
										disabled="disabled"
										title="Print PDF already done"
									<%} %>
									
									
									>Modify Inv</button></td>
								<td>
									<button type="button" class="btn btn-block btn-primary" id="printPDF<%=i %>" name="printPDF" value="printPDF" 
									<%if(!VinvChecked_flag.elementAt(i).equals("Y") || !VinvApproved_flag.elementAt(i).equals("Y")){ %>
									 	disabled="disabled" title="Make sure Invoice Checked & Approved" 
									 <%} %>
									onclick="viewInv('<%=i %>','<%=VinvContType.elementAt(i)%>','<%=VinvInvoice_dt.elementAt(i)%>',
										'<%=VinvInvoice_due_dt.elementAt(i)%>','<%=VinvMapping_id.elementAt(i)%>',
										'<%=VinvCustomer_cd.elementAt(i)%>','<%=VinvPlant_seq_no.elementAt(i)%>',
										'<%=period_start_dt%>','<%=period_end_dt%>','<%=VinvNew_inv_seq_no.elementAt(i)%>','<%=VinvInvoice_fin_yr.elementAt(i)%>','<%=VinvInv_seq_no.elementAt(i)%>','P','<%=i%>','<%=VinvInv_seq_no.size()%>','<%=irn_flag.elementAt(i) %>','<%=VinvInvoice_cont_cd.elementAt(i)%>','<%=dr_cr_doc_no.elementAt(i) %>','<%=dr_cr_irn_cnt.elementAt(i)%>');"
										>Print PDF</button>
								</td>
								<td>
									<button type="button" class="btn btn-block btn-primary" id="signPDF<%=i %>" name="signPDF"
									<%if((o_cnt > 0 || d_cnt > 0 || t_cnt > 0 || c_cnt>0 || de_cnt>0 || cd_cnt>0 ||  ded_cnt>0 || cred_T > 0 || deb_T > 0)){%>
										onclick="openJarTab(this.id);"
									<%}else{%>
										disabled="disabled" title="Please Print atleast one PDF to Sign!"
									<%} %>
									>Sign PDF</button>
								</td>
								<td>
									<select class="form-control w-100" name="invoice_title" id="invoice_title<%=i%>" 
										onchange="checkPrint('<%=i%>','<%=VinvInvoice_print.elementAt(i)%>',this.value,
									 	'<%=c_cnt%>','<%=c_cnt_sign%>','<%=c_cnt_mail_sign%>','<%=cd_cnt%>','<%=cd_cnt_sign%>','<%=cd_cnt_mail_sign%>','<%=cred_T%>','<%=ct_cnt_sign%>','<%=ct_cnt_mail_sign%>',
										'<%=de_cnt%>','<%=de_cnt_sign%>','<%=de_cnt_mail_sign%>','<%=ded_cnt%>','<%=ded_cnt_sign%>','<%=ded_cnt_mail_sign%>','<%=deb_T%>','<%=det_cnt_sign%>','<%=det_cnt_mail_sign%>','<%=dr_cr_aprv_by.elementAt(i)%>');"
										
										>
										<option id="ori<%=i%>" value="ORIGINAL"
										<%if(VinvInvoice_print.elementAt(i).toString().contains("O")){ %>
											style="background-color: #99ff99;"
										<%} %>>ORIGINAL</option>
										 
										<option id="dup<%=i%>" value="DUPLICATE"
										<%if(VinvInvoice_print.elementAt(i).toString().contains("D")){ %>
											style="background-color: #ff6666;"
										<%} %>
										>DUPLICATE</option>
										
										<option id="tri<%=i%>" value="TRIPLICATE"
										<%if(VinvInvoice_print.elementAt(i).toString().contains("T")){ %>
											style="background-color: lightblue;"
										<%} %>
										>TRIPLICATE</option>
										
										<%if (dr_cr_inv_flag.elementAt(i).toString().equalsIgnoreCase("Y")|| dr_cr_inv_flag.elementAt(i).toString().equalsIgnoreCase("X")) { %>
											<%if (dr_cr_flag.elementAt(i).toString().equalsIgnoreCase("cr")) { %>
												<%if(c_cnt_sign>0){ %>
													<option id="creO_sign<%=i%>" 
													<%if(c_cnt_mail_sign>0){ %>
													 	style="background-color: #FFEC8B" title="Mail sent" 
													<%}else{ %>
														style="background-color: lightgreen;" 
													 <%} %> value="CR_signO">CREDIT - (O)(S)</option>
												<%}else{ %>
													<option id="creO<%=i%>" value="CREDITO">CREDIT - (O)</option>
												<%} %>
										<%if(cd_cnt_sign>0){ %>
												<option id="creD_sign<%=i%>" 
												<%if(cd_cnt_mail_sign>0){ %>
													style="background-color: #FFEC8B" title="Mail sent" 
												<%}else{ %>
													 style="background-color: lightgreen;" 
												<%} %>
												value="CR_signD">CREDIT - (D)(S)</option>
											<%}else{ %>
												<option id="creD<%=i%>" value="CREDITD">CREDIT - (D)</option>
											<%} %>
											
											<%if(ct_cnt_sign>0){ %>
												<option id="creT_sign<%=i%>" 
												<%if(ct_cnt_mail_sign>0){ %>
													style="background-color: #FFEC8B" title="Mail sent" 
												<%}else{ %>
													 style="background-color: lightgreen;" 
												<%} %>
												value="CR_signT">CREDIT - (T)(S)</option>
											<%}else{ %>
												<option id="creT<%=i%>" value="CREDITT">CREDIT - (T)</option>
											<%} %>
										<%} else { %>
											<%if(de_cnt_sign>0){ %>
												<option id="dO_sign<%=i%>" <%if(de_cnt_mail_sign>0){ %> style="background-color: #FFEC8B" title="Mail sent" <%}else{ %> style="background-color: lightgreen;" <%} %> value="DE_signO">DEBIT - (O)(S)</option>
											<%}else{ %>
												<option id="dO<%=i%>" value="DEBITO">DEBIT - (O)</option>
											<%} %>
											<%if(ded_cnt_sign>0){ %>
												<option id="dD_sign<%=i%>" <%if(ded_cnt_mail_sign>0){ %> style="background-color: #FFEC8B" title="Mail sent" <%}else{ %> style="background-color: lightgreen;" <%} %> value="DE_signD">DEBIT - (D)(S)</option>
											<%}else{ %>
												<option id="dD<%=i%>" value="DEBITD">DEBIT - (D)</option>
											<%} 
											if(det_cnt_sign>0){ %>
											<option id="dT_sign<%=i%>" <%if(det_cnt_mail_sign>0){ %> style="background-color: #FFEC8B" title="Mail sent" <%}else{ %> style="background-color: lightgreen;" <%} %> value="DE_signT">DEBIT - (T)(S)</option>
											<%}else{ %>
											<option id="dT<%=i%>" value="DEBITT">DEBIT - (T)</option>
											<%} 
										}
									}%>
									</select>
								</td>
								<td>
									<input type="hidden" name="osign_cnt" id="osign_cnt<%=i%>" value="<%=osign_cnt%>">
									<input type="hidden" name="dsign_cnt" id="dsign_cnt<%=i%>" value="<%=dsign_cnt%>">
									<input type="hidden" name="tsign_cnt" id="tsign_cnt<%=i%>" value="<%=tsign_cnt%>">
									<input type="hidden" value="<%=dr_cr_flag.elementAt(i)%>" name = "dr_cr_flag" id = "dr_cr_flag<%=i%>">
									<button type="button" class="btn btn-block btn-primary" id="viewPDF<%=i %>" name="viewPDF" value="viewPDF" 
									<%if((osign_cnt > 0 || dsign_cnt > 0 || tsign_cnt > 0) && (o_cnt>0 || d_cnt>0 || t_cnt>0) ){%>
									
									<%}else{ %>
										disabled="disabled"
									<%} %>
									onclick="viewInvPDF('<%=i %>','<%=invoice_path.toString().replace("[", "").replace("]", "")%>',
									'<%=o_filename%>','<%=d_filename%>','<%=t_filename%>','<%=p2%>','V','<%=(String)VinvContType.elementAt(i)%>',
									'<%=VinvCustomer_cd.elementAt(i)%>','<%=VinvInvoice_cont_cd.elementAt(i)%>','<%=VinvPlant_seq_no.elementAt(i)%>',
									'<%=VinvInvoice_dt.elementAt(i)%>','<%=VinvNew_inv_seq_no.elementAt(i)%>');">View PDF</button>
								</td>
								<td>
									<button type="button" class="btn btn-block btn-primary" id="sendMail<%=i %>" name="sendMail" value="sendMail" 
									<%if((osign_cnt > 0 || dsign_cnt > 0 || tsign_cnt > 0) && (o_cnt>0 || d_cnt>0 || t_cnt>0) ){%>
									
									<%}else{ %>
										disabled="disabled"
									<%} %>
									onclick="viewInvPDF('<%=i %>','<%=invoice_path.toString().replace("[", "").replace("]", "")%>',
									'<%=o_filename%>','<%=d_filename%>','<%=t_filename%>','<%=p2%>','E','<%=(String)VinvContType.elementAt(i)%>',
									'<%=VinvCustomer_cd.elementAt(i)%>','<%=VinvInvoice_cont_cd.elementAt(i)%>','<%=VinvPlant_seq_no.elementAt(i)%>',
									'<%=VinvInvoice_dt.elementAt(i)%>','<%=VinvNew_inv_seq_no.elementAt(i)%>');">Send Mail</button>
								</td>
							</tr>
							
							<%if(VinvInvoice_print.elementAt(i).toString().contains("O")){ %>
								<script>
									var ind = '<%=i%>';
									document.getElementById('signPDF'+ind).disabled = false;
									document.getElementById('printPDF'+ind).disabled = true;
									
								</script>
							<%}%>
						<%} %>
						<%if(VinvCustomer_cd.size() == 0 ) {%>
						<tr>
							<th colspan="12" class="text-center info">
								<font color="red">No Service Invoice(s) Generated During the Period <%=period_start_dt %> to <%=period_end_dt %></font>
							</th>
						</tr>
						<%} %>
					
					</tbody>
				</table>
<!-- 			</div> -->
		</div>
		</div>			
	</div>
</div>

	
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="check_permission" value="<%=check_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
</form>
<!-- </body> -->
</div>
</div>
</html>