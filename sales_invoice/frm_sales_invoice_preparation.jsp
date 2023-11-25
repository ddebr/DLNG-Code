<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<head>
<script type="JavaScript" src="../js/util2.js"></script>
<script type="JavaScript" src="../js/util1.js"></script>
<script type="javascript" src="../js/util.js"></script>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<script>
$(function () {
$('#datetimepicker1').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,
orientation: "bottom auto",	
});
});
</script>
<script>
var newWindow;
var newWindow2;

function openInvoiceInPdffor_View(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,
		customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,
		exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,sz,ind,mapping_id,approval_flag,aprv
		,invoice_tax_adj,dr_cr_docNo,dr_cr_dt,CRDRcriteria)
{
	var invoice_title = "";
	var size = parseInt(sz);
	var index = parseInt(ind);
	
	var print_permission = document.forms[0].print_permission.value;
	var date_flag = document.forms[0].date_flag.value;
	if(size==1)
	{
		invoice_title = document.forms[0].invoice_title.value;
	}
	else if(size>1)
	{
		invoice_title = document.forms[0].invoice_title[index].value;
	}
	
	if(aprv=='N' || aprv=='')
	{
		alert("CN/PERIOD APPROVAL IS PENDING!!!");
	}	
	else
	{
		if(approval_flag=='Y')
		{
			alert("Commercial Details approval is pending.");
		}
		else
		{
	
			if(!invoicePdf || invoicePdf.closed)
			{
				invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl_view_temp.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&invoice_tax_adj="+invoice_tax_adj+"&dr_cr_docNo="+dr_cr_docNo+"&dr_cr_dt="+dr_cr_dt+"&CrDrcriteria="+CRDRcriteria,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
			}
			else
			{
				invoicePdf.close();
			    invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl_view_temp.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&invoice_tax_adj="+invoice_tax_adj+"&dr_cr_docNo="+dr_cr_docNo+"&dr_cr_dt="+dr_cr_dt+"&CrDrcriteria="+CRDRcriteria,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
			}
		}
	}
	
}
var newWindow3;
function openInvoiceViewPage(obj,customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,sz,ind,inv_approved_flag,adjust_flag,mapping_id,approval_flag,aprv,activity_name,cr_dr_criteria,cr_dr_no,cr_dr_dt,cr_dr_flag,new_inv_seq_no,dr_cr_aprv_by)
{
	var invoice_title = "";
	var size = parseInt(sz);
	var index = parseInt(ind);
	var date_flag = document.forms[0].date_flag.value;
	var print_permission = document.forms[0].print_permission.value;
	invoice_title = document.getElementById('invoice_title'+index).value;
	if(obj.value=="View Inv") {
		
		invoice_title = document.forms[0].invoice_title[index].value;
		if(aprv=='N' || aprv=='')
		{
			alert("CN/PERIOD APPROVAL IS PENDING!!!");
		}	
		else
		{
			if(approval_flag=='Y')
			{
				alert("Commercial Details approval is pending.");
			}
			else
			{
				if(date_flag=="true") {
					if(!newWindow2 || newWindow2.closed)
					{
						newWindow2 = window.open("../sales_invoice/rpt_view_chk_aprv_dtl.jsp?activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&adjust_flag="+adjust_flag,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
					}
					else
					{
						newWindow2.close();
						newWindow2 = window.open("../sales_invoice/rpt_view_chk_aprv_dtl.jsp?activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&adjust_flag="+adjust_flag,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
					}
				} else {
					if(!newWindow2 || newWindow2.closed)
					{
		 				newWindow2 = window.open("../sales_invoice/rpt_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&adjust_flag="+adjust_flag,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
					}
					else
					{
						newWindow2.close();
		 			    newWindow2 = window.open("../sales_invoice/rpt_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&adjust_flag="+adjust_flag,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
					}
				}
			}
		}
	} else {
		if(!newWindow3 || newWindow3.closed)
		{
			newWindow3 = window.open("../sales_invoice/frm_approve_cr_dr_note.jsp?customer_plant_nm="+customer_plant_nm+"&customer_abbr="+customer_abbr+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&new_inv_seq_no="+new_inv_seq_no+"&view_flag=Y&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&exchg_rate_cd="+exchg_rate_cd+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&CrDrcriteria="+cr_dr_criteria+"&cr_dr="+cr_dr_flag+"&inv_dt="+cr_dr_dt+"&inv_no="+cr_dr_no+"&invoice_title="+invoice_title+"&dr_cr_aprv_by="+dr_cr_aprv_by,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=550,scrollbars=1,status=1,menubar=1");
		}
		else
		{
			newWindow3.close();
			newWindow3 = window.open("../sales_invoice/frm_approve_cr_dr_note.jsp?customer_plant_nm="+customer_plant_nm+"&customer_abbr="+customer_abbr+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&new_inv_seq_no="+new_inv_seq_no+"&view_flag=Y&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&exchg_rate_cd="+exchg_rate_cd+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&CrDrcriteria="+cr_dr_criteria+"&cr_dr="+cr_dr_flag+"&inv_dt="+cr_dr_dt+"&inv_no="+cr_dr_no+"&invoice_title="+invoice_title+"&dr_cr_aprv_by="+dr_cr_aprv_by,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=550,scrollbars=1,status=1,menubar=1");
		}
	}
}

function openInvoiceCheckPage(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,sz,ind,inv_approved_flag,adjflag,mapping_id,approval_flag,aprv,activity_name,check_flag)
{
	var invoice_title = "";
	var size = parseInt(sz);
	var index = parseInt(ind);
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var date_flag = document.forms[0].date_flag.value;
	
	if(size==1)
	{
		invoice_title = document.forms[0].invoice_title.value;
	}
	else if(size>1)
	{
		invoice_title = document.forms[0].invoice_title[index].value;
	}
	if(aprv=='N' || aprv=='')
	{
		alert("CN/PERIOD APPROVAL IS PENDING!!!");
	}	
	else
	{
		if(approval_flag=='Y')
		{
			alert("Commercial Details approval is pending.");
		}
		else
		{
			if(date_flag=="true") {
				if(!newWindow2 || newWindow2.closed)
				{
					newWindow2 = window.open("../sales_invoice/rpt_view_chk_aprv_dtl.jsp?check_flag="+check_flag+"&activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission,"Check_InvoiceDtl","top=10,left=10,width=1050,height=650,scrollbars=1,menubar=1");
				}
				else
				{ 
					newWindow2.close();
				    newWindow2 = window.open("../sales_invoice/rpt_view_chk_aprv_dtl.jsp?check_flag="+check_flag+"&activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission,"Check_InvoiceDtl","top=10,left=10,width=1050,height=650,scrollbars=1,menubar=1");
				}
			} else {
				if(!newWindow2 || newWindow2.closed)
				{
	 				newWindow2 = window.open("../sales_invoice/check_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission,"Check_InvoiceDtl","top=10,left=10,width=1050,height=650,scrollbars=1,menubar=1");
				}
				else
				{
					newWindow2.close();
	 			    newWindow2 = window.open("../sales_invoice/check_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission,"Check_InvoiceDtl","top=10,left=10,width=1050,height=650,scrollbars=1,menubar=1");
				}
			}
			
		}
	}
}
function openInvoiceAuthorizePage(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,sz,ind,inv_approved_flag)
{
	var invoice_title = "";
	var size = parseInt(sz);
	var index = parseInt(ind);
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	if(size==1)
	{
		invoice_title = document.forms[0].invoice_title.value;
	}
	else if(size>1)
	{
		invoice_title = document.forms[0].invoice_title[index].value;
	}
	
	if(!newWindow2 || newWindow2.closed)
	{
		newWindow2 = window.open("../authorize_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission,"Authorize_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
	}
	else
	{
		newWindow2.close();
	    newWindow2 = window.open("../authorize_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission,"Authorize_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
	}
}
function openInvoiceApprovePage(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,sz,ind,inv_approved_flag,adjflag,mapping_id,approval_flag,aprv, CR_aprv_by, CRDocNo, CRDocDt,CRcriteria,activity_name )
{
	var invoice_title = "";
	var size = parseInt(sz);
	var index = parseInt(ind);
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var date_flag = document.forms[0].date_flag.value;
	
	if(size==1)
	{
		invoice_title = document.forms[0].invoice_title.value;
	}
	else if(size>1)
	{
		invoice_title = document.forms[0].invoice_title[index].value;
	}
	
	if(aprv=='N' || aprv=='')
	{
		alert("CN/PERIOD APPROVAL IS PENDING!!!");
	}	
	else
	{
		if(approval_flag=='Y')
		{
			alert("Commercial Details approval is pending.");
		}
		else
		{
			if(date_flag=="true") {
				if(!newWindow2 || newWindow2.closed)
				{
					//newWindow2 = window.open("rpt_view_chk_aprv_dtl.jsp?activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&inv_approved_flag="+inv_approved_flag+"&CR_aprv_by="+CR_aprv_by+"&CR_Doc_No="+CRDocNo+"&CR_Doc_Dt="+CRDocDt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&CrDrcriteria="+CRcriteria,"Approve_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
					newWindow2 = window.open("../sales_invoice/rpt_view_chk_aprv_dtl.jsp?activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&inv_approved_flag="+inv_approved_flag+"&CR_aprv_by="+CR_aprv_by+"&CR_Doc_No="+CRDocNo+"&CR_Doc_Dt="+CRDocDt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&CrDrcriteria="+CRcriteria,"Approve_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
				}
				else
				{
					newWindow2.close();
				   // newWindow2 = window.open("rpt_view_chk_aprv_dtl.jsp?activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&inv_approved_flag="+inv_approved_flag+"&CR_aprv_by="+CR_aprv_by+"&CR_Doc_No="+CRDocNo+"&CR_Doc_Dt="+CRDocDt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&CrDrcriteria="+CRcriteria,"Approve_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
					newWindow2 = window.open("../sales_invoice/rpt_view_chk_aprv_dtl.jsp?activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&inv_approved_flag="+inv_approved_flag+"&CR_aprv_by="+CR_aprv_by+"&CR_Doc_No="+CRDocNo+"&CR_Doc_Dt="+CRDocDt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&CrDrcriteria="+CRcriteria,"Approve_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
				}
			} else {
				if(!newWindow2 || newWindow2.closed)
				{
	 				newWindow2 = window.open("../approve_invoice_dtl.jsp?activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&inv_approved_flag="+inv_approved_flag+"&CR_aprv_by="+CR_aprv_by+"&CR_Doc_No="+CRDocNo+"&CR_Doc_Dt="+CRDocDt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&CrDrcriteria="+CRcriteria,"Approve_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
				}
				else
				{
					newWindow2.close();
	 			    newWindow2 = window.open("../approve_invoice_dtl.jsp?activity_name="+activity_name+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&CR_aprv_by="+CR_aprv_by+"&CR_Doc_No="+CRDocNo+"&CR_Doc_Dt="+CRDocDt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&CrDrcriteria="+CRcriteria,"Approve_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
				}
			}
		}
	}
}
var invoicePdf;
function openInvoiceInPdf(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,sz,ind,mapping_id,approval_flag,aprv,invoice_tax_adj,dr_cr_docNo,dr_cr_dt,CRDRcriteria,path,obj,pdf_type)
{
	var btn_val=document.getElementById("InvPdf"+ind).value;
	
	if(btn_val=="View PDF"){
		var invoice_path="";
		var pdf_nm=path.split(",");
		var invoice_title = "";
		var size = parseInt(sz);
		var index = parseInt(ind);
		var print_permission = document.forms[0].print_permission.value;
		/* if(size==1)
		{
			invoice_title = document.forms[0].invoice_title.value;
		}
		else if(size>1)
		{ */
			invoice_title = document.forms[0].invoice_title[index].value;
// 		}
// 		alert("--invoice_title--"+invoice_title);
		for(var i=0;i<pdf_nm.length;i++){
			var  pdf_inv_type=pdf_nm[i].toString().substring(pdf_nm[i].length-1);
			if(invoice_title=='O' && pdf_inv_type=='O'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}else if(invoice_title=='D' && pdf_inv_type=='D'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}else if(invoice_title=='T' && pdf_inv_type=='T'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}else if(invoice_title=='CR_signO' && pdf_inv_type=='C'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}else if(invoice_title=='CR_signD' && pdf_inv_type=='1'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}else if(invoice_title=='DE_signO' && pdf_inv_type=='d'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}else if(invoice_title=='DE_signD' && pdf_inv_type=='1'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}
			else if(invoice_title=='Sup_signO' && pdf_inv_type=='S'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}
			else if(invoice_title=='Sup_signD' && pdf_inv_type=='1'){
				invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			}
		}
		if(aprv=='N' || aprv=='')
		{
			alert("CN/PERIOD APPROVAL IS PENDING!!!");
		}	
		else
		{
			if(approval_flag=='Y')
			{
				alert("Commercial Details approval is pending.");
			}
			else
			{
				if(invoice_path==''){
					invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&invoice_tax_adj="+invoice_tax_adj+"&dr_cr_docNo="+dr_cr_docNo+"&dr_cr_dt="+dr_cr_dt,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
				}else{
					if(!invoicePdf || invoicePdf.closed)
					{
						invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl_view.jsp?invoice_path="+invoice_path+"&print_permission="+print_permission+"&pdf_type="+pdf_type+"&contract_type="+contract_type+"&invoice_title="+invoice_title,
								"Rpt_InvoiceDtl","top=10,left=10,width=1,height=1,scrollbars=1,menubar=1");
					}
					else
					{
						invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl_view.jsp?invoice_path="+invoice_path+"&print_permission="+print_permission+"&pdf_type="+pdf_type+"&contract_type="+contract_type+"&invoice_title="+invoice_title,
								"Rpt_InvoiceDtl","top=10,left=10,width=1,height=1,scrollbars=1,menubar=1");
					}
				}
			}
		}
	}else{
		var invoice_title = "";
		var size = parseInt(sz);
		var index = parseInt(ind);
		
		var print_permission = document.forms[0].print_permission.value;
		var date_flag = document.forms[0].date_flag.value;
		/* if(size==1)
		{
			invoice_title = document.forms[0].invoice_title.value;
		}
		else if(size>1)
		{ */
			invoice_title = document.getElementById('invoice_title'+index).value;
// 			invoice_title = document.forms[0].invoice_title[index].value;
// 		}
// 		alert(invoice_title);
		if(invoice_title == 'ORIGINAL' || invoice_title == 'DUPLICATE' || invoice_title == 'TRIPLICATE' ){
			CRDRcriteria = "";
		}
		if(aprv=='N' || aprv=='')
		{
			alert("CN/PERIOD APPROVAL IS PENDING!!!");
		}	
		else
		{
			if(approval_flag=='Y')
			{
				alert("Commercial Details approval is pending.");
			}
			else
			{
				if(!invoicePdf || invoicePdf.closed)
				{
					invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&invoice_tax_adj="+invoice_tax_adj+"&dr_cr_docNo="+dr_cr_docNo+"&dr_cr_dt="+dr_cr_dt+"&CrDrcriteria="+CRDRcriteria,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
				}
				else
				{
					invoicePdf.close();
				    invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&invoice_tax_adj="+invoice_tax_adj+"&dr_cr_docNo="+dr_cr_docNo+"&dr_cr_dt="+dr_cr_dt+"&CrDrcriteria="+CRDRcriteria,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
				}
			}
		}
	}
}
var newWindow223;
function openInvoiceInPdf_View(indx,customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,sz,ind,mapping_id,approval_flag,aprv,invoice_tax_adj,dr_cr_docNo,dr_cr_dt,obj,path,pdf_type,msg,path_o,path_d,path_t,contact_cd,invoicedt,pdf_duedt,new_hlpl_inv_no,path_c,path_cd,path_ct,path_de,path_ded,path_det)
{
	
	var invoice_path="";
	var pdf_nm=path.split(",");
	var invoice_title = "";
	var size = parseInt(sz);
	var index = parseInt(ind);
	var inv_flag="";
	var path="";
	var sel = document.getElementById('invoice_title'+ind).value;
	var print_permission = document.forms[0].print_permission.value;
	var cr_dr_sign_flag="";
	
	if(size==1)
	{
		invoice_title = document.forms[0].invoice_title.value;
	}
	else if(size>1)
	{
		invoice_title = document.forms[0].invoice_title[index].value;
	}
	for(var i=0;i<pdf_nm.length;i++){
		
		var  pdf_inv_type=pdf_nm[i].toString().substring(pdf_nm[i].length-1);
		
		if(invoice_title=='ORIGINAL' && pdf_inv_type=='O'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='DUPLICATE' && pdf_inv_type=='D'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='TRIPLICATE' && pdf_inv_type=='T'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			
		}else if(invoice_title=='CREDITO' && pdf_inv_type=='C'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='CREDITD' && pdf_inv_type=='1'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='CREDITT' && pdf_inv_type=='2'){
			
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
			
		}else if(invoice_title=='DEBITO' && pdf_inv_type=='d'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='DEBITD' && pdf_inv_type=='1'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='DEBITT' && pdf_inv_type=='2'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf"; 
		
		}else if(invoice_title=='SupO' && pdf_inv_type=='S'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='SupD' && pdf_inv_type=='1'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='CR_signO' && pdf_inv_type=='C'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='CR_signD' && pdf_inv_type=='1'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='CR_signT' && pdf_inv_type=='2'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='DE_signO' && pdf_inv_type=='d'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='DE_signD' && pdf_inv_type=='1'){
			invoice_path=obj+"/"+pdf_nm[i].toString().trim()+".pdf";
		}else if(invoice_title=='DE_signT' && pdf_inv_type=='2'){
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
	
	if(sel=="CR_signO"){
		new_inv_seq_no=dr_cr_docNo;
		new_hlpl_inv_no=dr_cr_docNo;
		cr_dr_sign_flag="CO";
		path = path_c;
	} else if(sel=="CR_signD"){
		new_hlpl_inv_no=dr_cr_docNo;
		cr_dr_sign_flag="CD";
		path = path_cd;
		new_inv_seq_no=dr_cr_docNo;
	}else if(sel=="CR_signT"){
		new_hlpl_inv_no=dr_cr_docNo;
		cr_dr_sign_flag="CT";
		path = path_ct;
		new_inv_seq_no=dr_cr_docNo;
	}
	
	else if(sel=="DE_signO"){
		new_hlpl_inv_no=dr_cr_docNo;
		cr_dr_sign_flag="DEO";
		new_inv_seq_no=dr_cr_docNo;
		path = path_de;
		
	} else if(sel=="DE_signD"){
		new_hlpl_inv_no=dr_cr_docNo;
		cr_dr_sign_flag="DED";
		new_inv_seq_no=dr_cr_docNo;
		path = path_ded;
	}else if(sel=="DE_signT"){
		new_hlpl_inv_no=dr_cr_docNo;
		cr_dr_sign_flag="DET";
		new_inv_seq_no=dr_cr_docNo;
		path = path_det;
	}
	
	if(contract_type=='S'){
		inv_flag="SALES_";
	}else if(contract_type=='L'){
		inv_flag="LOA_";
	}
	
	if(sel=="CR_signO" || sel=="CR_signD" || sel=="CR_signT"){
		inv_flag="CREDIT_";
	}else if(sel=="DE_signD" || sel=="DE_signO" || sel=="DE_signT" ){
		inv_flag="DEBIT_";
	}
	
	if(aprv=='N' || aprv=='')
	{
		alert("CN/PERIOD APPROVAL IS PENDING!!!");
	}	
	else
	{
		if(approval_flag=='Y')
		{
			alert("Commercial Details approval is pending.");
		}
		else
		{
			if(invoice_path==''){
				if(pdf_type =='F'){
					 	
						var truck_no = document.getElementById('truck_no'+indx).value;
						var invNo = document.getElementById('invNo'+indx).value;
					   	
					if(!newWindow223 || newWindow223.closed){
						newWindow223 = window.open("frm_form_402_mail.jsp?invoice_path="+path+"&inv_flag="+inv_flag+
								"&inv_type="+invoice_title+"&contact_cd="+contact_cd+"&customer_cd="+customer_cd+
								"&invoice_dt="+invoicedt+"&contract_type="+contract_type+"&month="+month+"&year="+year+
								"&bill_cycle="+bill_cycle+"&hlpl_inv_no="+new_hlpl_inv_no+"&customer_plant_seq_no="+customer_plant_seq_no+
								"&due_dt="+pdf_duedt+"&truck_no="+truck_no+"&invNo="+invNo,"View PDF","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
					}
					else{
						newWindow223 = window.open("frm_form_402_mail.jsp?invoice_path="+path+"&inv_flag="+inv_flag+
								"&inv_type="+invoice_title+"&contact_cd="+contact_cd+"&customer_cd="+customer_cd+
								"&invoice_dt="+invoicedt+"&contract_type="+contract_type+"&month="+month+"&year="+year+
								"&bill_cycle="+bill_cycle+"&hlpl_inv_no="+hlpl_inv_no+"&customer_plant_seq_no="+customer_plant_seq_no+
								"&due_dt="+pdf_duedt+"&truck_no="+truck_no+"&invNo="+invNo,"View PDF","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
// 						newWindow223.close();
// 						newWindow223 = window.open("frm_sign_pdf_mail.jsp?invoice_path="+path+"&inv_flag="+inv_flag+"&cr_dr_sign_flag="+cr_dr_sign_flag+"&dr_cr_dt="+dr_cr_dt+"&inv_type="+sel+"&contact_cd="+contact_cd+"&customer_cd="+customer_cd+"&invoice_dt="+invoicedt+"&contract_type="+contract_type+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&hlpl_inv_no="+new_inv_seq_no+"&customer_plant_seq_no="+customer_plant_seq_no+"&due_dt="+pdf_duedt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&form_cd="+form_cd+"&form_name="+form_name,"View PDF","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
					}
				}else{
					invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&invoice_title="+invoice_title+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&invoice_tax_adj="+invoice_tax_adj+"&dr_cr_docNo="+dr_cr_docNo+"&dr_cr_dt="+dr_cr_dt,"Rpt_InvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
				}
			}else{
				if(pdf_type =='E'){
					if(!newWindow223 || newWindow223.closed){
						newWindow223 = window.open("frm_sign_pdf_mail.jsp?invoice_path="+path+"&inv_flag="+inv_flag+
								"&inv_type="+invoice_title+"&contact_cd="+contact_cd+"&customer_cd="+customer_cd+
								"&invoice_dt="+invoicedt+"&contract_type="+contract_type+"&month="+month+"&year="+year+
								"&bill_cycle="+bill_cycle+"&hlpl_inv_no="+new_hlpl_inv_no+"&customer_plant_seq_no="+customer_plant_seq_no+
								"&due_dt="+pdf_duedt+"&dr_cr_dt="+dr_cr_dt+"&cr_dr_sign_flag="+cr_dr_sign_flag,"View PDF","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
					}
					else{
						newWindow223 = window.open("frm_sign_pdf_mail.jsp?invoice_path="+path+"&inv_flag="+inv_flag+
								"&inv_type="+invoice_title+"&contact_cd="+contact_cd+"&customer_cd="+customer_cd+
								"&invoice_dt="+invoicedt+"&contract_type="+contract_type+"&month="+month+"&year="+year+
								"&bill_cycle="+bill_cycle+"&hlpl_inv_no="+hlpl_inv_no+"&customer_plant_seq_no="+customer_plant_seq_no+
								"&due_dt="+pdf_duedt+"&dr_cr_dt="+dr_cr_dt+"&cr_dr_sign_flag="+cr_dr_sign_flag,"View PDF","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
// 						newWindow223.close();
// 						newWindow223 = window.open("frm_sign_pdf_mail.jsp?invoice_path="+path+"&inv_flag="+inv_flag+"&cr_dr_sign_flag="+cr_dr_sign_flag+"&dr_cr_dt="+dr_cr_dt+"&inv_type="+sel+"&contact_cd="+contact_cd+"&customer_cd="+customer_cd+"&invoice_dt="+invoicedt+"&contract_type="+contract_type+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&hlpl_inv_no="+new_inv_seq_no+"&customer_plant_seq_no="+customer_plant_seq_no+"&due_dt="+pdf_duedt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&form_cd="+form_cd+"&form_name="+form_name,"View PDF","top=10,left=10,width=850,height=850,scrollbars=1,menubar=1");
					}
				}else{
					if(!invoicePdf || invoicePdf.closed)
					{	
					
						invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl_view.jsp?invoice_path="+invoice_path+"&print_permission="+print_permission+"&pdf_type="+pdf_type+"&contract_type="+contract_type+"&invoice_title="+invoice_title,"Rpt_InvoiceDtl1","top=10,left=10,width=1,height=1,scrollbars=1,menubar=1");
					}
					else
					{
						invoicePdf = window.open("../pdf_reports/sales_invoice/pdf_invoice_dtl_view.jsp?invoice_path="+invoice_path+"&print_permission="+print_permission+"&pdf_type="+pdf_type+"&invoice_title="+invoice_title,"Rpt_InvoiceDtl2","top=10,left=10,width=1,height=1,scrollbars=1,menubar=1");
					}
				}
			}
		}
	}
}

function deleteInvoice(inv_financial_year,hlpl_inv_seq_no,hlpl_inv_no,contract_type,mapping_id)
{
	document.forms[0].inv_financial_year.value = inv_financial_year;
	document.forms[0].hlpl_inv_seq_no.value = hlpl_inv_seq_no;
	document.forms[0].hlpl_inv_no.value = hlpl_inv_no;
	document.forms[0].contract_type.value = contract_type;
	document.forms[0].del_mapping_id.value = mapping_id;
	
	var delete_code1=document.getElementById("deletecode").value;
	var flag=true;
	var correctflag=false;
	var anscode=prompt("Enter the code to delete the invoice as shown here  "+delete_code1,"");
	if(anscode==delete_code1)
	{
		correctflag=true;
	}
	else
	{
		correctflag=false;
	}
	
	if(flag && correctflag){
	var a = confirm("Do You Want To DELETE All Details Regarding Invoice : "+hlpl_inv_no+" ???");
	if(a)
	{
		document.forms[0].submit();
	}
	}
	else
	{
		if(flag){
		alert("Sory !! Wrong code");}
	}
}
function openInvoiceGenerationForm(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,activity,hlpl_invoice_sequence_no,gross_amt_usd,gross_amt_inr,hlpl_inv_seq_no,inv_financial_year,offspec_rate,adjust_flag,mapping_id,approval_flag,aprv,invoice_tax_adj,truck_cd,sn_start_dt,sn_end_dt)
{
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	var date_flag = document.forms[0].date_flag.value;
	if(aprv=='N' || aprv=='')
	{
		alert("CN/PERIOD APPROVAL IS PENDING!!!");
	}	
	else
	{
		if(approval_flag=='Y')
		{
			alert("Commercial Details approval is pending.");
		}
		else
		{
			if(invoice_tax_adj == "Y")
			{
				if(date_flag=="true") {
					if(!newWindow || newWindow.closed)
					{	
						newWindow = window.open("../sales_invoice/frm_invoice_dtl_tax_adjust_gst.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&adjust_flag="+adjust_flag,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
					}
					else
					{	
						newWindow.close();
					    newWindow = window.open("../sales_invoice/frm_invoice_dtl_tax_adjust_gst.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&adjust_flag="+adjust_flag,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
					}
				} else {
					if(!newWindow || newWindow.closed)
					{	
						newWindow = window.open("../sales_invoice/frm_invoice_dtl_tax_adjust.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&adjust_flag="+adjust_flag,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
					}
					else
					{	
						newWindow.close();
					    newWindow = window.open("../sales_invoice/frm_invoice_dtl_tax_adjust.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&adjust_flag="+adjust_flag,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
					}
				}
			}
			else
			{	
				if(date_flag=="true") {
					if(!newWindow || newWindow.closed)
					{	
						newWindow = window.open("../sales_invoice/frm_invoice_dtl_gst.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&adjust_flag="+adjust_flag+"&truck_cd="+truck_cd+"&sn_start_dt="+sn_start_dt+"&sn_end_dt="+sn_end_dt,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
					}
					else
					{	
						newWindow.close();
					    newWindow = window.open("../sales_invoice/frm_invoice_dtl_gst.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&adjust_flag="+adjust_flag+"&truck_cd="+truck_cd+"&sn_start_dt="+sn_start_dt+"&sn_end_dt="+sn_end_dt,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
					}
				} else {
					if(!newWindow || newWindow.closed)
					{	
						newWindow = window.open("../sales_invoice/frm_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&adjust_flag="+adjust_flag+"&truck_cd="+truck_cd+"&sn_start_dt="+sn_start_dt+"&sn_end_dt="+sn_end_dt,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
					}
					else
					{	
						newWindow.close();
					    newWindow = window.open("../sales_invoice/frm_invoice_dtl?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&adjust_flag="+adjust_flag+"&truck_cd="+truck_cd+"&sn_start_dt="+sn_start_dt+"&sn_end_dt="+sn_end_dt,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
					}
				}
			}
		}
	}
}

function openInvoiceAdjustmentForm(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,activity,hlpl_invoice_sequence_no,gross_amt_usd,gross_amt_inr,hlpl_inv_seq_no,inv_financial_year,offspec_rate)
{
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	if(!newWindow || newWindow.closed)
	{
		newWindow = window.open("sales_invoice/frm_invoice_dtl?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission,"Invoice_Details","top=10,left=10,width=950,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
	}
	else
	{
		newWindow.close();
	    newWindow = window.open("sales_invoice/frm_invoice_dtl?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&gross_amt_usd2="+gross_amt_usd+"&gross_amt_inr2="+gross_amt_inr+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&offspec_rate="+offspec_rate+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission,"Invoice_Details","top=10,left=10,width=950,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
	}
}
function refreshPage()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
// 	
// 	
	var bill_cycle = document.forms[0].bill_cycle.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	if(bill_cycle!='' && bill_cycle!=' ' && bill_cycle!='0'){
		location.replace("../sales_invoice/frm_mst_prepareInvoice.jsp?&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+
			"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
	}else{
		alert('Please select Gas Date!');
	}
}

function refreshPage1()
{
	var bill_cycle = document.forms[0].bill_cycle.value;
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var inv_title_string=document.forms[0].inv_title_string.value;
// 	alert('here')
	location.replace("../../sales_invoice/frm_mst_prepareInvoice.jsp?inv_title_string="+inv_title_string+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+
			"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}

function refreshPage11(msg)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var bill_cycle = document.forms[0].bill_cycle.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var inv_title_string=document.forms[0].inv_title_string.value;
	
	location.replace("../../sales_invoice/frm_mst_prepareInvoice.jsp?msg="+msg+"&month="+month+"&inv_title_string="+inv_title_string+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}

function refreshPageFromChild(month, year, bill_cycle, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	location.replace("../frm_mst_prepareInvoice.jsp?&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}
function refreshPageFromChild2(msg, month, year, bill_cycle, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	location.replace("../sales_invoice/frm_mst_prepareInvoice.jsp?msg="+msg+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}
function refreshPageFromChild3(msg, month, year, bill_cycle,error_msg,billing_dt)
{
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	location.replace("../sales_invoice/frm_mst_prepareInvoice.jsp?msg="+msg+"&bill_cycle="+billing_dt+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&error_msg="+error_msg);
}

function generateedeletecode()
{

}

function print_enanble(index,ori,dupi,trip,cred,cr_flg,sred,dred,flagQty,drcr,ded_cnt,supd_cnt,cd_cnt,osign,dsign,tsign,hlpl_inv_seq_no,deb_T,cred_T,co_sign,cd_sign,ct_sign,do_sign,dd_sign,dt_sign){
	
	var inv_type=document.getElementById('invoice_title'+index).value;
	
	if(inv_type=='Form402'){
		
		document.getElementById('sendMail'+index).style.display="none";
		document.getElementById('form402sendMail'+index).style.display="inline-block";

		document.getElementById('signPdf'+index).disabled=true;	
		document.getElementById('form402sendMail'+index).disabled=false;
		document.getElementById('InvPdf'+index).disabled=true;
		document.getElementById('InvView'+index).disabled=true;
		document.getElementById('InvPdf1_view'+index).disabled=true;
		
	}
	else{
		document.getElementById('signPdf'+index).disabled=false;	
		document.getElementById('form402sendMail'+index).disabled=true;
		document.getElementById('form402sendMail'+index).style.display="none";
		document.getElementById('sendMail'+index).style.display="inline";
		document.getElementById('InvPdf'+index).disabled=false;	
		document.getElementById('InvView'+index).disabled=false;
		document.getElementById('InvPdf1_view'+index).disabled=false;
	}
	
	if(inv_type=='ORIGINAL' && osign>0 && ori > 0 ){
		
		document.getElementById('signPdf'+index).disabled=true;	
		document.getElementById('sendMail'+index).disabled=false;
		document.getElementById('InvPdf'+index).disabled=true;
		document.getElementById('InvView'+index).disabled=true;
		
	}else if(inv_type=='DUPLICATE' && dsign>0 && dupi > 0 ){
		
		document.getElementById('signPdf'+index).disabled=true;	
		document.getElementById('sendMail'+index).disabled=false;
		document.getElementById('InvPdf'+index).disabled=true;
		document.getElementById('InvView'+index).disabled=true;
		
	}else if(inv_type=='TRIPLICATE' && tsign>0 && trip > 0 ){
		
		document.getElementById('signPdf'+index).disabled=true;	
		document.getElementById('sendMail'+index).disabled=false;
		document.getElementById('InvPdf'+index).disabled=true;
		document.getElementById('InvView'+index).disabled=true;
	}else{
// 	alert('in else')
		if(inv_type=='ORIGINAL' && osign == 0 && ori == 0 ){
			document.getElementById('signPdf'+index).disabled=true;	
			document.getElementById('InvPdf'+index).disabled=false;
			document.getElementById('sendMail'+index).disabled=true;
			document.getElementById('InvPdf1_view'+index).disabled=true;
			
		}else if(inv_type=='DUPLICATE' && dsign==0 && dupi == 0 ){
			document.getElementById('signPdf'+index).disabled=true;
			document.getElementById('InvPdf'+index).disabled=false;
			document.getElementById('sendMail'+index).disabled=true;
			document.getElementById('InvPdf1_view'+index).disabled=true;
			
		}else if(inv_type=='TRIPLICATE' && tsign==0 && trip == 0 ){
			
			document.getElementById('signPdf'+index).disabled=true;
			document.getElementById('InvPdf'+index).disabled=false;
			document.getElementById('sendMail'+index).disabled=true;
			document.getElementById('InvPdf1_view'+index).disabled=true;
		}
// 	alert(dsign+"****"+ori)
		if(inv_type=='ORIGINAL' && osign == 0 && ori > 0 ){
			document.getElementById('signPdf'+index).disabled=false;	
			document.getElementById('InvPdf'+index).disabled=true;
			document.getElementById('sendMail'+index).disabled=true;
			document.getElementById('InvPdf1_view'+index).disabled=false;
			document.getElementById('InvView'+index).disabled=true;
			
		}else if(inv_type=='DUPLICATE' && dsign==0 && dupi > 0 ){
			document.getElementById('signPdf'+index).disabled=false;
			document.getElementById('InvPdf'+index).disabled=true;
			document.getElementById('sendMail'+index).disabled=true;
			document.getElementById('InvPdf1_view'+index).disabled=false;
			document.getElementById('InvView'+index).disabled=true;
			
		}else if(inv_type=='TRIPLICATE' && tsign==0 && trip > 0 ){
			
			document.getElementById('signPdf'+index).disabled=false;
			document.getElementById('InvPdf'+index).disabled=true;
			document.getElementById('sendMail'+index).disabled=true;
			document.getElementById('InvPdf1_view'+index).disabled=false;
			document.getElementById('InvView'+index).disabled=true;
			
		}else if(inv_type=='CREDITO' || inv_type=='CREDITD' || inv_type=='CREDITT' || inv_type=='DEBITO' || inv_type=='DEBITD' || inv_type=='DEBITT' || inv_type=='SupD' || inv_type=='SupO' || inv_type=="CR_signO" || inv_type=="CR_signD" || inv_type=="CR_signT" || inv_type=="DE_signO" || inv_type=="DE_signD" || inv_type=="DE_signT" || inv_type=="Sup_signO" || inv_type=="Sup_signD" || inv_type=="Sup_signT") {
			
			document.getElementById("InvView"+index).disabled = false;
			document.getElementById('signPdf'+index).disabled=true;
			document.getElementById('InvPdf'+index).disabled=true;
			document.getElementById('sendMail'+index).disabled=true;
			document.getElementById('InvPdf1_view'+index).disabled=true;
			
// 			InvPdf1_view
			if(cr_flg!='0' && cr_flg!=''){ // for approved CR/DR Note
				
// 				document.getElementById("InvView"+index).disabled = true;
				document.getElementById('InvPdf'+index).disabled = false;
			}else{
				
// 				document.getElementById("InvView"+index).disabled = false;
// 				document.getElementById('InvPdf'+index).disabled = true;
			}
// 			alert(cr_flg)
			if(inv_type=='CREDITO' || inv_type=='CREDITD' || inv_type=='CREDITT' || inv_type=="CR_signO" || inv_type=="CR_signD" || inv_type=="CR_signT") {
				if(cr_flg > 0){
					document.getElementById("InvView"+index).innerHTML = "View Credit";	
					document.getElementById("InvView"+index).value = "Approve Credit";
				}else{
					document.getElementById("InvView"+index).value = "Approve Credit";
					document.getElementById("InvView"+index).innerHTML = "View / Approve Credit";	
				}
				
			} else {
				if(cr_flg > 0){
					document.getElementById("InvView"+index).innerHTML = "View Debit";
					document.getElementById("InvView"+index).value = "Approve Debit";
				}else{
					document.getElementById("InvView"+index).value = "Approve Debit";
					document.getElementById("InvView"+index).innerHTML = "View / Approve Debit";
				}
			}
// 			alert("cd_cnt***"+cred+"***"+cr_flg)
			if(inv_type=='CREDITO' || inv_type=='CR_signO') {
				if(cred>0 && cr_flg>0){
					
					document.getElementById('InvPdf1_view'+index).disabled=false;
					document.getElementById('InvPdf'+index).disabled=true;
					
					if(co_sign == 0 ){
						document.getElementById('signPdf'+index).disabled=false;
					}else{
						document.getElementById('signPdf'+index).disabled=true;
						document.getElementById('sendMail'+index).disabled=false;
					}
					
				}else{
					
					document.getElementById('InvPdf1_view'+index).disabled=true;
					if(cr_flg>0){
						document.getElementById('InvPdf'+index).disabled=false;	
					}
				}
			} else if(inv_type=='CREDITD' || inv_type=='CR_signD'){
// 				alert("cd_cnt***"+cd_cnt+"***"+cr_flg)
				if(cd_cnt>0 && cr_flg>0){
					
					document.getElementById('InvPdf1_view'+index).disabled=false;
					document.getElementById('InvPdf'+index).disabled=true;
					
					if(cd_sign == 0 ){
						document.getElementById('signPdf'+index).disabled=false;
					}else{
						document.getElementById('signPdf'+index).disabled=true;
						document.getElementById('sendMail'+index).disabled=false;
					}
				}else{
					document.getElementById('InvPdf1_view'+index).disabled=true;
					if(cr_flg>0){
						document.getElementById('InvPdf'+index).disabled=false;	
					}
				}
			}else if(inv_type=='CREDITT' || inv_type=='CR_signT'){
// 				alert("cd_cnt***"+cd_cnt+"***"+cr_flg)
				if(cred_T>0 && cr_flg>0){
					
					document.getElementById('InvPdf1_view'+index).disabled=false;
					document.getElementById('InvPdf'+index).disabled=true;
					if(ct_sign == 0 ){
						document.getElementById('signPdf'+index).disabled=false;
					}else{
						document.getElementById('signPdf'+index).disabled=true;
						document.getElementById('sendMail'+index).disabled=false;
					}
				}else{
					document.getElementById('InvPdf1_view'+index).disabled=true;
					if(cr_flg>0){
						document.getElementById('InvPdf'+index).disabled=false;	
					}
				}
			}else{
				
				document.getElementById('InvPdf'+index).disabled=true;
				document.getElementById('InvPdf1_view'+index).disabled=false;
			}
// 			alert(inv_type+"***"+dred+"****"+cr_flg)
			if(inv_type=='DEBITO' || inv_type=='DE_signO') {
				if(dred>0 && cr_flg>0){
					
					document.getElementById('InvPdf1_view'+index).disabled=false;
					document.getElementById('InvPdf'+index).disabled=true;
					
					if(do_sign == 0 ){
						document.getElementById('signPdf'+index).disabled=false;
					}else{
						document.getElementById('signPdf'+index).disabled=true;
						document.getElementById('sendMail'+index).disabled=false;
					}
				}else{
					
					document.getElementById('InvPdf1_view'+index).disabled=true;
					if(cr_flg>0){
						document.getElementById('InvPdf'+index).disabled=false;	
					}
				}
			} else if(inv_type=='DEBITD' || inv_type=='DE_signD') {
				if(ded_cnt>0 && cr_flg>0){
					
					document.getElementById('InvPdf1_view'+index).disabled=false;
					document.getElementById('InvPdf'+index).disabled=true;
					
					if(dd_sign == 0 ){
						document.getElementById('signPdf'+index).disabled=false;
					}else{
						document.getElementById('signPdf'+index).disabled=true;
						document.getElementById('sendMail'+index).disabled=false;
					}
				}else{
					document.getElementById('InvPdf1_view'+index).disabled=true;
					if(cr_flg>0){
						document.getElementById('InvPdf'+index).disabled=false;	
					}
				}
			}else if(inv_type=='DEBITT' || inv_type=='DE_signT') {
				
				if(deb_T>0 && cr_flg>0){
					
					document.getElementById('InvPdf1_view'+index).disabled=false;
					document.getElementById('InvPdf'+index).disabled=true;
					
					if(dt_sign == 0 ){
						document.getElementById('signPdf'+index).disabled=false;
					}else{
						document.getElementById('signPdf'+index).disabled=true;
						document.getElementById('sendMail'+index).disabled=false;
					}
				}else{
					document.getElementById('InvPdf1_view'+index).disabled=true;
					if(cr_flg>0){
						document.getElementById('InvPdf'+index).disabled=false;	
					}
				}
			}else{
				if(inv_type!='CREDITT' && inv_type!='CREDITD' && inv_type!='CREDITO'){
					document.getElementById('InvPdf'+index).disabled=true;
					document.getElementById('InvPdf1_view'+index).disabled=false;		
				}	
			}
			/* alert(drcr+"****"+flagQty)
			if(drcr=='DIFF-QTY--'){
				if(flagQty=='N' || flagQty==''){
					alert("First Change The Quantity From Daily Allocation!!!!");
					document.getElementById('InvPdf1_view'+index).disabled=true;
					document.getElementById('InvPdf'+index).disabled=true;
				}
			} */
		}else{
			
			document.getElementById("InvView"+index).innerHTML = "View Inv";
		}	
	}
}

function view_enable(obj,crdr_aprv,flag_,index,pdf_lock_flag,actual_invoice_no,VDrCriteria,VDrCrDocNo,VDrCrDt,invoice_Customer_Cd,invoice_FGSA_No,invoice_FGSA_Rev_No,invoice_SN_No,invoice_SN_Rev_No,invoice_Contract_Type,invoice_Customer_Plant_Nm,invoice_Customer_Plant_Seq_No,invoice_Bill_Period_Start_Dt,invoice_Bill_Period_End_Dt,invoice_Due_Dt,invoice_HLPL_Seq_No,mapping_id)
{
	var view_but = document.forms[0].InvView;
	var write_p = document.forms[0].write_permission.value;
	if(view_but) {
		if(obj.value=='ORIGINAL' || obj.value=='DUPLICATE' || obj.value=='TRIPLICATE')
		{
			if(actual_invoice_no=='' || actual_invoice_no=='0') {
				document.getElementById("InvView"+index).value = 'Prepare Inv';
				if(write_p=='Y') 
				{
					if(crdr_aprv=='0' && flag_=='Y') {
						document.getElementById("InvView"+index).disabled = true;
					} else {
						document.getElementById("InvView"+index).disabled = false;
					}
				} else {
					document.getElementById("InvView"+index).disabled = true;
				}
			}
			else 
			{
				document.getElementById("InvView"+index).value = "View Inv";
				if(pdf_lock_flag=='Y') {
					document.getElementById("InvView"+index).disabled = true;
				} else {
					document.getElementById("InvView"+index).disabled = false;
				}
			}
		}
	}
}

function openJarTab(){
	
		var url="../PDF_signer.jar";
		window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1");
		//location.replace(url);
		
	/*  $.post("../servlet/Frm_Sales_InvoiceV2?option=digitalSignJar",function(responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
	        $.each(responseText, function(text) {
	            
	         });                   
     }); */
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_InvoiceV2" id="salesInv" scope="page"/>   
<%--HA20181225 <jsp:useBean class="com.henergy.bombay.util.DataBean_modulelock" id="modlock" scope="request"/> --%>
<jsp:useBean class="com.seipl.hazira.dlng.advance_payment.DataBean_Advance_payment" id="adv" scope="page"/>  
<%
	util.init();
	String sysdate = util.getGen_dt();
	String curr_year = util.getGet_yr();	
	
	String page_nm = "../home/header.jsp";
	
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
			 
// 	String year = request.getParameter("year")==null?"0":request.getParameter("year");
	String bill_cycle = request.getParameter("bill_cycle")==null?sysdate:request.getParameter("bill_cycle");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
	
	/* String write_permission = request.getParameter("write_permission")==null?"Y":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"Y":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"Y":request.getParameter("print_permission");
	String check_permission = request.getParameter("check_permission")==null?"Y":request.getParameter("check_permission");
	String authorize_permission = request.getParameter("authorize_permission")==null?"Y":request.getParameter("authorize_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"Y":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"Y":request.getParameter("audit_permission"); */
	
	String write_permission = (String)session.getAttribute("write_permission") ;
	String delete_permission = (String)session.getAttribute("delete_permission") ;
	String print_permission = (String)session.getAttribute("print_permission") ;
	String approve_permission = (String)session.getAttribute("approve_permission") ;
	String audit_permission = (String)session.getAttribute("audit_permission") ;
	String check_permission = (String)session.getAttribute("check_permission") ;
	String authorize_permission = (String)session.getAttribute("update_permission") ;
	String view_permission = (String)session.getAttribute("view_permission") ;

	String inv_title_string = request.getParameter("inv_title_string")==null?"":request.getParameter("inv_title_string");

	// 	System.out.println("bill_cycle***"+bill_cycle);
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
	
	adv.setCallFlag("Genrate_Delete_Code");
	adv.init();
	String delete_code=adv.getDelete_code();
	
	salesInv.setCallFlag("FetchMaxYear");
	salesInv.init();
	String maxYear=salesInv.getMaxYear();
	
	
	salesInv.setCallFlag("SALES_INVOICE_PREPERATION");
	salesInv.setBillCycle(bill_cycle);
	salesInv.setTcqflag("Calculate");
	salesInv.setRequest(request);
	String month="";
	String year= "";
	if(!bill_cycle.equals("0") && !bill_cycle.equals("") )
	{
		if(bill_cycle.contains("/")){
	
			String temp_month[] = bill_cycle.split("/");
			month = temp_month[1];
			year = temp_month[2];
		}
		salesInv.init();
	}
	
// 	Vector FY_AllocatedData = salesInv.getFY_AllocatedData();
	Vector new_Invoice_Seq_No = salesInv.getNew_Invoice_Seq_No();
	Vector invoice_Customer_Cd = salesInv.getInvoice_Customer_Cd();
	Vector invoice_Customer_Abbr = salesInv.getInvoice_Customer_Abbr();
	Vector invoice_Customer_Plant_Seq_No = salesInv.getInvoice_Customer_Plant_Seq_No();
	Vector invoice_Customer_Plant_Nm = salesInv.getInvoice_Customer_Plant_Nm();
	boolean date_flag = salesInv.isDate_flag();
	Vector invoice_SN_Start_Dt = salesInv.getInvoice_SN_Start_Dt();
	Vector invoice_SN_End_Dt = salesInv.getInvoice_SN_End_Dt();
	Vector invoice_Contract_Type = salesInv.getInvoice_Contract_Type();
	Vector invoice_SN_Ref_No = salesInv.getInvoice_SN_Ref_No();
	Vector invoice_SN_No = salesInv.getInvoice_SN_No();
	Vector invoice_FGSA_No = salesInv.getInvoice_FGSA_No();
	Vector invoice_FGSA_Rev_No = salesInv.getInvoice_FGSA_Rev_No();
	
	Vector customer_Invoice_Actual_Seq_No = salesInv.getCustomer_Invoice_Actual_Seq_No();
	Vector invoice_Bill_Period_Start_Dt = salesInv.getInvoice_Bill_Period_Start_Dt();
	Vector hlpl_Invoice_Actual_Seq_No = salesInv.getHlpl_Invoice_Actual_Seq_No();
	Vector invoice_SN_Rev_No = salesInv.getInvoice_SN_Rev_No();
	Vector invoice_Bill_Period_End_Dt = salesInv.getInvoice_Bill_Period_End_Dt();
	Vector invoice_Due_Dt = salesInv.getInvoice_Due_Dt();
	Vector invoice_HLPL_Seq_No = salesInv.getInvoice_HLPL_Seq_No(); 
	Vector invoice_Exchg_Rate_Cd = salesInv.getInvoice_Exchg_Rate_Cd();
	Vector invoice_Exchg_Rate_Calculation_Method = salesInv.getInvoice_Exchg_Rate_Calculation_Method();
	String blank_string = "";
	Vector invoice_adjust_flag=salesInv.getInv_Checked_Flag(); //RG20140830
	Vector cust_invoice_mapping_id=salesInv.getInvoice_Mapping_Id();
	Vector invoice_pre_aprv=salesInv.getInvoice_pre_aprv();
	Vector Invoice_Pending_approval=salesInv.getInvoice_Pending_approval();
	Vector invoice_tax_adj=salesInv.getInvoice_tax_adj();
	Vector hlpl_Invoice_Seq_No_Arr = salesInv.getHlpl_Invoice_Seq_No_Arr();
	Vector hlpl_Invoice_Financial_Year_Arr = salesInv.getHlpl_Invoice_Financial_Year_Arr();
	Vector inv_Approved_Flag = salesInv.getInv_Approved_Flag();
	Vector pdf_color = salesInv.getPdf_color();
	Vector customer_invoice_pdf_path_flag=salesInv.getCustomer_invoice_pdf_path_flag();
	Vector VDrCrFlag=salesInv.getVDrCrFlag();
	Vector Vdrcrflag=salesInv.getVdrcrflag();
	String total_Invoice_Qty = salesInv.getTotal_Invoice_Qty();
// 	Vector TCQ=salesInv.getTCQ();
// 	Vector AllocatedQty=salesInv.getAllocatedQty();
	Vector CuttoffDate=salesInv.getCuttDate();
	String tit="";
	Vector RemainingQty=salesInv.getRemainingQty();
	Vector Vdrcrcontype=salesInv.getVctype();
	Vector VDrCriteria=salesInv.getVDrcrCriteria();
	Vector VDrCrDocNo=salesInv.getVDrCrDocNo();
	Vector VDrCrDt=salesInv.getVDrCrDt();
	Vector tax_adj_flag_pdf=salesInv.getTax_adj_flag_pdf();
	Vector customer_invoice_pdf_lock_flag=salesInv.getCustomer_invoice_pdf_lock_flag();
	Map invoice_view_pdf=salesInv.getInvoice_view_pdf();
	Map credit_map = salesInv.getCredit_map();
	Map invoice_view_signed_pdf = salesInv.getInvoice_view_signed_pdf();
	Map Invoice_mail_sent = salesInv.getInvoice_mail_sent();
	Vector VCustContPlantCrDrAprv_Flag=salesInv.getVCustContPlantCrDrAprv_Flag(); 
	Vector VDrCrAprvBy=salesInv.getVDrCrAprvBy(); 
	Vector flagQty=salesInv.getFlagqty();
// 	System.out.println("flagQty***"+flagQty);
	String inv_title[]=new String[hlpl_Invoice_Actual_Seq_No.size()];
	boolean flag= false;

	if(inv_title_string.equalsIgnoreCase(""))
	{
	
	}
	else
	{
		flag=true;
		inv_title=inv_title_string.split("-");
	}
	int k =0;
	Vector cust_loaded_truck = salesInv.getCust_loaded_truck();//Hiren_20200313
	Vector cust_loaded_truck_cd = salesInv.getCust_loaded_truck_cd();//Hiren_20200316
	Vector cust_loaded_truck_cnt = salesInv.getCust_loaded_truck_cnt();//Hiren_20200313
	Vector contact_person_cd=salesInv.getVPDF_Contact_person_cd();
	Vector VPDF_Inv_Dt = salesInv.getVPDF_Inv_Dt();
	Vector vpdf_due_dt=salesInv.getVPDF_due_dt();
	
	Vector VPDF_File_Nm = salesInv.getVPDF_File_Nm(); //SB20160527
	Vector VDrCrAprvDt = salesInv.getVDrCrAprvDt(); //SB20160602
	Vector VBalanceQty_mmbtu = salesInv.getVBalanceQty_mmbtu(); //SB20160603
	Vector VInv_AllocatedQty_mmbtu = salesInv.getVInv_AllocatedQty_mmbtu(); //SB20160604
	Map invoice_pdf_path = salesInv.getInvoice_signded_pdf();//HS20160531
// 	Vector VRemark = salesInv.getVRemark();
// 	Vector invoice_agr_base=salesInv.getInvoice_agr_base();
// 	System.out.println("invoice_agr_base***"+invoice_agr_base);
%>
<%try{ %>

<div class="tab-content">
	
<!-- /.Invoicing TAB START-->
<div class="tab-pane active" id="invoicing">
<form  method="post" onload="refreshPage();">
<input type="hidden" name="date_flag" value="<%=date_flag%>">
<!-- Default box -->
<div class="box mb-0">

<div class="box-header with-border">

<!-- 1st start --> 
<div class="row">


<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
	<div class="form-group input-group">
		<span class="input-group-addon  weight-600"> Billing Date: </span>
		<div  class="form-group" >
			<input type="text" class="form-control" name="bill_cycle" id='datetimepicker1' onchange="refreshPage();" value="<%=bill_cycle%>">
			
		</div>
	</div>
</div>

<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
	<button type="button" class="btn btn-block btn-primary" name="list" value="View List" onClick="refreshPage();"> View List </button>
</div>
<div class="col-sm-7 text-right">
	<button type="button" class="btn btn-warning" name="refresh" value="Refresh" onClick="refreshPage();"> Refresh</button>
</div>


</div><!-- 1st end -->


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
<%if(error_msg.length()>5){%>
	
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

<div class="row">
	<div class="col-lg-12">
		<div class="pt-5">
			<a class="mr-20" href="#">
			<i class="fa fa-square text-yellow1"></i> 
			<span class="txt-grey"> ORIGINAL PRINTED </span>
			</a>
			
			<a class="mr-20" href="#">
			<i class="fa fa-square text-yellow2"></i> 
			<span class="txt-grey"> DUPLICATE PRINTED </span>
			</a>
			
			<a class="mr-20" href="#">
			<i class="fa fa-square text-yellow3"></i> 
			<span class="txt-grey"> TRIPLICATE PRINTED </span>
			</a>
		</div>
	</div>
</div>
    
</div>

<div class="box-body table-responsive no-padding">

<div class="row">
	<div class="col-lg-12">
		<div class="call-action call-action-boxed call-action-style3 clearfix">
		<h2 class="primary">
			INVOICE GENERATION/VIEW
		</h2>
		</div>
	</div>
</div>


<div class="table-responsive">
<table class="table table-bordered table-hover">

<thead>
<tr class="info">
<!-- <th></th> -->
<th class="col-lg-1"> CUSTOMER </th>
<th class="col-lg-1">CONTRACT</th>
<th class="col-lg-1" colspan="3"> PLANT </th>
<th class="col-lg-1" colspan="4"> START DATE & END DATE  </th>
</tr>
</thead>

<%if(invoice_Customer_Cd.size()>0)
	{
	String cls="";
	String color="";
	String rowcolor="";
	String title="";
	for(int i=0; i<invoice_Customer_Cd.size(); i++){ %>
		<tbody>
		<tr  style="background-color: #ffe57f">

			<td TITLE="Sr.#: <%=i+1%>"><div class="mt-5" > <%=(String)invoice_Customer_Abbr.elementAt(i)%> </div></td>
			
			<td title=""><div class="mt-5">
				 <%if((""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("S")){%>
					SN - <%=(String)invoice_SN_No.elementAt(i)%>
				<%}%>
				<%if((""+invoice_Contract_Type.elementAt(i)).trim().equals("L")){%>
					LoA - <%=(String)invoice_SN_No.elementAt(i)%>
				<%}%>
				
				<%if(!invoice_SN_Ref_No.elementAt(i).toString().equalsIgnoreCase("")){%>
					<font size="1" color="green"><b>(Ref No. <%=(String)invoice_SN_Ref_No.elementAt(i) %>)</b></font>	
				<%} %>
			</div>
			</td>
			<td colspan="2">
				<div class="mt-5"> 
				
					<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>				
				</div>
			</td>
			<td colspan="4">
				<div class="text-center mt-5"> 
				<%=(String)invoice_SN_Start_Dt.elementAt(i)%> to <%=(String)invoice_SN_End_Dt.elementAt(i)%>
				</div>
			</td>
		</tr>
		
		<%
	for(int j = 0 ; j < Integer.parseInt(cust_loaded_truck_cnt.elementAt(i)+"") ; j ++) {
// 		System.out.println("Vdrcrflag.elementAt(k)**********"+Vdrcrflag.elementAt(k));
		String key = ""+invoice_Customer_Cd.elementAt(i)+":"+invoice_FGSA_No.elementAt(i)+":"+invoice_SN_No.elementAt(i)+":"+invoice_Customer_Plant_Seq_No.elementAt(i)+":"+invoice_Contract_Type.elementAt(i);
		String flag_ = ""+credit_map.get(key);
		
		if(flag_==null || flag_=="null" || flag_.equals("null") || flag_.equals(null)) {
			flag_ = "N";
		}
		  if(Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("Y"))
		 	{
		 		rowcolor="#DBDB70";
		 		if(date_flag) {
		 			title="Credit is generated for Invoice No: "+new_Invoice_Seq_No.elementAt(k).toString();
		 		} else {
		 			title="Credit is generated for Invoice No: "+hlpl_Invoice_Actual_Seq_No.elementAt(k).toString();
		 		}
		 		salesInv.setTotal_Invoice_Qty(total_Invoice_Qty);
		 	}
		 	else if(Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("X"))
		 	{
		 		rowcolor="#DBDB7F";
		 		if(date_flag) {
		 			title="Reverse Invoice/ Credit is generated for Invoice No: "+new_Invoice_Seq_No.elementAt(k).toString();
		 		} else {
		 			title="Reverse Invoice/ Credit is generated for Invoice No: "+hlpl_Invoice_Actual_Seq_No.elementAt(k).toString();
		 		}
		 		
		 		salesInv.setTotal_Invoice_Qty(total_Invoice_Qty);
		 	} 
		 	else
		 	{
		 		rowcolor="";
		 		title="";
		 	}
// 		 System.out.println("Vdrcrflag.elementAt(k)**********"+Vdrcrflag.elementAt(k));
			/* if(TCQ.elementAt(i).toString().equalsIgnoreCase(AllocatedQty.elementAt(i).toString()) || RemainingQty.elementAt(i).toString().equalsIgnoreCase("0.0")  )
	  		{
				if((""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("T") || (""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("C") || (""+invoice_Contract_Type.elementAt(i)).equals("R"))
				{
		  			tit="LNG Quantity completed on "+CuttoffDate.elementAt(i);	

				}
				else
				{
		  			tit="TCQ completed on "+CuttoffDate.elementAt(i);	

				}	
	  		}
	  		else
	  		{
	  			java.text.NumberFormat nf=new java.text.DecimalFormat("#.##");
	  			
	  			tit=nf.format(Double.parseDouble(RemainingQty.elementAt(i).toString()))+" MMBTU Quantity Remaining";
	  		} */
	  		
			if((""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("T") || (""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("C") ){
				cls="content2";
			}
			else
			{
				cls="content1"; 	
			}
		
			Vector invoice_path=new Vector();
			Vector invoice_signed_path = new Vector();
			Vector invoice_mail_sent_flag = new Vector();
			
	 		String invoice[]=invoice_view_pdf.get(k).toString().replace("[", "").replace("]", "").split(",");
	 		String invoice_sign[] = invoice_view_signed_pdf.get(k).toString().replace("[", "").replace("]", "").split(",");
	 		String invoice_mail_flag[]=Invoice_mail_sent.get(k).toString().replace("[", "").replace("]", "").split(","); 
	 		
	 		for(int p1=0;p1<invoice.length;p1++){
// 	 			System.out.println("invoice[p1]****"+invoice[p1]);
	 			invoice_path.add(invoice[p1].trim());
	 			invoice_signed_path.add(invoice_sign[p1]);
	 			invoice_mail_sent_flag.add(invoice_mail_flag[p1]);
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
// 				System.out.println("invoice_path******"+invoice_path.elementAt(l));
// 				System.out.println("inv_ty******"+inv_ty);
				
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
				<%}
			
				if(inv_ty.equalsIgnoreCase("C") && Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("X")){ // for (0) Origional Credit Note
					c_cnt++;
					c_filename=invoice_path.elementAt(l).toString();
					if(inv__flag.equalsIgnoreCase("Y")){
						c_cnt_sign++;											
					}
					if(inv_mail_flag.equalsIgnoreCase("Y")){
						c_cnt_mail_sign++;											
					}
				}	
				if(inv_ty.equalsIgnoreCase("1") && Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("X")){
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
				if(inv_ty.equalsIgnoreCase("2") && Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("X")){
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
				if(inv_ty.equalsIgnoreCase("S") && Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("Y")){
					s_cnt++;
					s_filename=invoice_path.elementAt(l).toString();%>
				<%}
				if(inv_ty.equalsIgnoreCase("1") && Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("Y") && invoice_Contract_Type.elementAt(i).equals("C")){
					sd_cnt++;
					sd_filename=invoice_path.elementAt(l).toString();%>
				<%}
				if(inv_ty.equals("d") && Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("Y")){ 
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
				if(inv_ty.equalsIgnoreCase("1") && Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("Y") && !invoice_Contract_Type.elementAt(i).equals("C")){
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
				if(inv_ty.equalsIgnoreCase("2") && Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("Y") && !invoice_Contract_Type.elementAt(i).equals("C")){
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
			}%>
		
		<%//System.out.println("osign_cnt---"+osign_cnt+"--"+i);%>
		<%if(j==0){ %>
		<tr style="background-color: #f6f6f6">
			<th>Truck No. </th>
			<th class="col-lg-1"> INVOICE  SEQ NO </th>
			<th class="col-lg-1"> ACTIVITY-I </th>
			<th class="col-lg-1"> ACTIVITY-II </th>
			<th class="col-lg-1"> ACTIVITY-III </th>
			<th class="col-lg-1"> INVOICE TITLE </th>
			<th class="col-lg-1"> ARCHIVED PDF </th> 
			<th class="col-lg-1"> SEND MAIL </th> 
		</tr>
		<%} 
		if(pdf_color.elementAt(k).toString().equals("T"))
		{
			color="#FFA500";
		}
		else if(pdf_color.elementAt(k).toString().equals("D"))
		{
			color="#F0E68C";
		}
		else if(pdf_color.elementAt(k).toString().equals("O"))
		{
			color="#FFFFE0";
		}
		else
		{
			color="";
		}
		
		%>
		<tr title="<%=title%>" style="background-color: <%=rowcolor%>" title="<%=title%>" >
			<td><%=cust_loaded_truck.elementAt(k) %></td>
<%-- 			<td><%=customer_Invoice_Actual_Seq_No.elementAt(k) %></td> --%>
			<td style="background-color: <%=color%>" >
				<div class="mt-5">
				<%	
				String invno = "";
				if((""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).trim().length()>13)
				{	
				
					if(!new_Invoice_Seq_No.elementAt(k).equals("")) {
						invno = ""+new_Invoice_Seq_No.elementAt(k);
					} else {
						
						invno = (""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).substring(0,5)+""+(""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).substring(7,10)+""+(""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).substring(12);	
					}
					%>
					<%-- <%if(Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("X")){ %><FONT COLOR=RED SIZE=2><B>
					<%=Vdrcrflag.elementAt(k) %>:</B></FONT><%}%> --%>
					
					<%=invno%> 
			<%	}
				else
				{	%>
					&nbsp;<FONT COLOR=GREEN SIZE=2><B><%=Vdrcrflag.elementAt(k) %></B></FONT>
			<%	}	%> </div>
			
			<input type="hidden" id="truck_no<%=k %>" value="<%=cust_loaded_truck.elementAt(k)%>">
			<input type="hidden" id="invNo<%=k %>" value="<%=invno%>">
			</td>
			<td>
				<div class="mt-5"> 
				<%	if((""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).trim().equals("") || (""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).trim().equals("0")){%>	
					
					<button type="button" class="btn btn-block btn-primary" id="InvView<%=k%>" name="InvGeneration" value="Prepare Inv" 
						onClick="openInvoiceGenerationForm('<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>','<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>','<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>','<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>','<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>','insert','0','<%=blank_string%>','<%=blank_string%>','<%=blank_string%>','<%=blank_string%>','<%=blank_string%>','<%=invoice_adjust_flag.elementAt(k)%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>','<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>','<%=invoice_tax_adj.elementAt(k)%>','<%=cust_loaded_truck_cd.elementAt(k)%>','<%=invoice_SN_Start_Dt.elementAt(i)%>','<%=invoice_SN_End_Dt.elementAt(i)%>');">Prepare Inv</button>
						
				<%}else{
// 					System.out.println("customer_invoice_pdf_lock_flag.elementAt(k)****"+customer_invoice_pdf_lock_flag.elementAt(k));
					if (("" + customer_invoice_pdf_lock_flag.elementAt(k)).equalsIgnoreCase("Y")) {%>
						<button type="button" class="btn btn-block btn-primary" id="InvView<%=k%>" name="InvView" value="View Inv" disabled="disabled" title="Invoice Locked">View Inv</button>
					<%}else{ %>
						<button type="button" class="btn btn-block btn-primary" id="InvView<%=k%>" name="InvView" value="View Inv" onClick="openInvoiceViewPage(this,'<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>','<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>','<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>','<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>','<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(i)%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>','<%=(String)hlpl_Invoice_Seq_No_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Financial_Year_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=(String)customer_Invoice_Actual_Seq_No.elementAt(k)%>','<%=invoice_Customer_Cd.size()%>','<%=k%>','<%=(""+inv_Approved_Flag.elementAt(k)).trim()%>','<%=invoice_adjust_flag.elementAt(k)%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>','<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>','VIEW','<%=VDrCriteria.elementAt(k).toString()%>','<%=(String)VDrCrDocNo.elementAt(k) %>','<%=(String)VDrCrDt.elementAt(k) %>','<%=(String)VDrCrFlag.elementAt(k)%>','<%=new_Invoice_Seq_No.elementAt(k)%>','<%=VDrCrAprvBy.elementAt(k)%>');">View Inv</button>
					<%} %>
				<%} %>
				</div>
			</td>
			
			<td>
				<div class="mt-5"> 
				<%	if((""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).trim().equals("") || (""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).trim().equals("0"))
					{	%>
					<button type="button" class="btn btn-block btn-primary" id="InvPdf<%=k %>" name="InvPdf" value="Print PDF" style="font-size:15;" disabled="disabled" >Print PDF</button>
				<%	}
					else
					{	%>
						<%-- <%	if(print_permission.trim().equalsIgnoreCase("Y") && (""+inv_Approved_Flag.elementAt(k)).trim().equalsIgnoreCase("Y")) --%>
						<%	if((""+inv_Approved_Flag.elementAt(k)).trim().equalsIgnoreCase("Y")){
								if((o_cnt>0 && d_cnt>0 && t_cnt>0) && (!VDrCrFlag.elementAt(k).toString().equalsIgnoreCase("cr")) && (!VDrCrFlag.elementAt(k).toString().equalsIgnoreCase("dr"))) {
									%>
									<button type="button" class="btn btn-block btn-primary"id="InvPdf<%=k%>" name="InvPdf" value="Print PDF" style="font-size:15;font-weight:bold;" disabled="disabled" >Print PDF</button>
									<%
								}else{%>
								
									<button type="button" class="btn btn-block btn-primary" id="InvPdf<%=k%>" name="InvPdf" value="Print PDF" 
									onClick="setpara('<%=k %>','<%=hlpl_Invoice_Actual_Seq_No.size() %>');
									openInvoiceInPdf('<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>','<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>','<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>','<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>','<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>','<%=(String)hlpl_Invoice_Seq_No_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Financial_Year_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=(String)customer_Invoice_Actual_Seq_No.elementAt(k)%>','<%=invoice_Customer_Cd.size()%>','<%=k%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>','<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>','<%=""+tax_adj_flag_pdf.elementAt(k)%>','<%=VDrCrDocNo.elementAt(k) %>','<%=VDrCrDt.elementAt(k) %>','<%=VDrCriteria.elementAt(k) %>');" >Print PDF</button>
									
							<%	}
							}
							else
							{	%>
							<button type="button" class="btn btn-block btn-primary" id="InvPdf<%=k%>" name="InvPdf" value="Print PDF" style="font-size:15;font-weight:bold;" disabled="disabled" >Print PDF</button>
						<%	}	%>
				<%	}	%>
				</div>
			</td>
			 <td>
				<div class="mt-5"> 
				
					<button type="button" class="btn btn-block btn-primary" id="signPdf<%=k%>" name="signPdf" value="Sign PDF" style="font-size:15;font-weight:bold;" 
					<%
					if((o_cnt > 0 || d_cnt > 0 || t_cnt > 0 || c_cnt>0 || de_cnt>0 || cd_cnt>0 ||  ded_cnt>0 || cred_T > 0 || deb_T > 0)){%>
						onclick="openJarTab(this.id);"
					<%}else{%>
						disabled="disabled" title="Please Print atleast one PDF to Sign!"
					<%} %>
					>Sign PDF</button>
				</div>
			</td>
			
			<td>
<%-- 			<%System.out.println("invoice_HLPL_Seq_No.elementAt(k)****"+hlpl_Invoice_Actual_Seq_No.elementAt(k)); %> --%>
				<div class="mt-5"> 
					<select class="form-control w-100" name="invoice_title" id="invoice_title<%=k%>" 
						onchange="view_enable(this,'<%=VCustContPlantCrDrAprv_Flag.elementAt(k)%>','<%=flag_%>','<%=k%>','<%=customer_invoice_pdf_lock_flag.elementAt(k)%>','<%=hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=VDrCriteria.elementAt(k).toString()%>','<%=VDrCrDocNo.elementAt(k) %>','<%=VDrCrDt.elementAt(k) %>','<%=(String)invoice_Customer_Cd.elementAt(i)%>','<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>','<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>','<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>','<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=(String)cust_invoice_mapping_id.elementAt(i)%>');
							print_enanble('<%=k%>','<%=o_cnt%>','<%=d_cnt%>','<%=t_cnt%>','<%=c_cnt%>','<%=VDrCrAprvBy.elementAt(k).toString()%>','<%=s_cnt%>','<%=de_cnt%>','<%=flagQty.elementAt(k).toString()%>','<%=VDrCriteria.elementAt(k).toString()%>','<%=ded_cnt%>','<%=sd_cnt%>','<%=cd_cnt%>','<%=osign_cnt%>','<%=dsign_cnt%>','<%=tsign_cnt%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=deb_T %>','<%=cred_T %>','<%=c_cnt_sign %>','<%=cd_cnt_sign %>','<%=ct_cnt_sign %>','<%=de_cnt_sign %>','<%=ded_cnt_sign %>','<%=det_cnt_sign %>');"
							<%if(hlpl_Invoice_Actual_Seq_No.elementAt(k).equals("")){%>
								disabled="disabled"
							<%} %>
							>		
							<option id="ori<%=k%>" value="ORIGINAL" >ORIGINAL</option>
							<option id="dup<%=k%>" value="DUPLICATE">DUPLICATE</option>
							<option id="tri<%=k%>" value="TRIPLICATE">TRIPLICATE</option>
							<%if (Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("Y")|| Vdrcrflag.elementAt(k).toString().equalsIgnoreCase("X")) { %>
								<%
									if (VDrCrFlag.elementAt(k).toString().equalsIgnoreCase("cr")) {
								%>
								<%if(c_cnt_sign>0){ %>
									<option id="creO_sign<%=k%>" 
									<%if(c_cnt_mail_sign>0){ %>
									 	style="background-color: #FFEC8B" title="Mail sent" 
									<%}else{ %>
										style="background-color: lightgreen;" 
									 <%} %> value="CR_signO">CREDIT - (O)(S)</option>
								<%}else{ %>
									<option id="creO<%=k%>" value="CREDITO">CREDIT - (O)</option>
								<%} %>
							<%if(cd_cnt_sign>0){ %>
									<option id="creD_sign<%=k%>" 
									<%if(cd_cnt_mail_sign>0){ %>
										style="background-color: #FFEC8B" title="Mail sent" 
									<%}else{ %>
										 style="background-color: lightgreen;" 
									<%} %>
									value="CR_signD">CREDIT - (D)(S)</option>
								<%}else{ %>
									<option id="creD<%=k%>" value="CREDITD">CREDIT - (D)</option>
								<%} %>
								
								<%if(ct_cnt_sign>0){ %>
									<option id="creT_sign<%=k%>" 
									<%if(ct_cnt_mail_sign>0){ %>
										style="background-color: #FFEC8B" title="Mail sent" 
									<%}else{ %>
										 style="background-color: lightgreen;" 
									<%} %>
									value="CR_signT">CREDIT - (T)(S)</option>
								<%}else{ %>
									<option id="creT<%=k%>" value="CREDITT">CREDIT - (T)</option>
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
								<option id="dT_sign<%=k%>" <%if(det_cnt_mail_sign>0){ %> style="background-color: #FFEC8B" title="Mail sent" <%}else{ %> style="background-color: lightgreen;" <%} %> value="DE_signT">DEBIT - (T)(S)</option>
								<%}else{ %>
								<option id="dT<%=k%>" value="DEBITT">DEBIT - (T)</option>
								<%} 
							}
						}%>
							<option id="Form402<%=k%>" value="Form402">FORM402</option>
					</select>
				</div>
			</td>
			
			<%
			osign_cnt = 0; dsign_cnt = 0; tsign_cnt = 0;
			for(int l=0;l<invoice_path.size();l++){
			String inv_ty="";
			if(!invoice_path.elementAt(l).toString().equalsIgnoreCase("")){
				inv_ty=invoice_path.elementAt(l).toString().substring(invoice_path.elementAt(l).toString().length()-1);
			}
			String inv__flag = "";
			if (!invoice_path.elementAt(l).toString().equalsIgnoreCase("")) {
				inv__flag = invoice_signed_path.elementAt(l).toString().trim();
			}
// 			System.out.println("inv_ty***"+inv__flag);
			//String temp=invoice_path.elementAt(l).toString().substring(0,invoice_path.elementAt(l).toString().length()-4);
			
			if(inv_ty.equalsIgnoreCase("O") ){
				
				o_cnt++;
				o_filename=invoice_path.elementAt(l).toString();
				
				if(inv__flag.equalsIgnoreCase("Y")){
					osign_cnt++;											
				}%>
				<script>
					if(document.getElementById('ori'+<%=k%>).value=='ORIGINAL'){ //HS20160617
						document.getElementById('ori'+<%=k%>).style.backgroundColor="#99ff99";
					}
				</script>
			<%
				if(osign_cnt > 0){%>
					<script>
						document.getElementById('signPdf'+<%=k%>).disabled=true;
						document.getElementById('signPdf'+<%=k%>).title="Already Signed!!";
						
						document.getElementById('InvPdf'+<%=k%>).disabled=true;
						document.getElementById('InvPdf'+<%=k%>).title="Already Printed!!";
					</script>
				<%}
	
				if(o_cnt > 0){%>
					<script>
					document.getElementById('InvPdf'+<%=k%>).disabled=true;
					document.getElementById('InvPdf'+<%=k%>).title="Already Printed!!";
					</script>
				<%}}
			
			if(inv_ty.equalsIgnoreCase("D")){
				d_cnt++;
				
				d_filename=invoice_path.elementAt(l).toString();%>
				<script>
				if(document.getElementById('dup'+<%=k%>).value=='DUPLICATE'){ //HS20160617 
					document.getElementById('dup'+<%=k%>).style.backgroundColor="#ff6666";
				}
				</script>
				
				<%if(inv__flag.equalsIgnoreCase("Y")){
					dsign_cnt++;											
				}%>
			<%
				if(dsign_cnt > 0){%>
					<script>
						document.getElementById('signPdf'+<%=k%>).disabled=true;
						document.getElementById('signPdf'+<%=k%>).title="Already Signed!!";
					</script>
				<%}
			if(d_cnt > 0){%>
				<script>
				document.getElementById('InvPdf'+<%=k%>).disabled=true;
				document.getElementById('InvPdf'+<%=k%>).title="Already Printed!!";
				</script>
			<%}
			}
			if(inv_ty.equalsIgnoreCase("T")){
				t_cnt++;
				
				t_filename=invoice_path.elementAt(l).toString();%>
				<script>
				if(document.getElementById('tri'+<%=k%>).value=='TRIPLICATE'){ //HS20160617
					document.getElementById('tri'+<%=k%>).style.backgroundColor="lightblue";
				}
				</script>
				
				<%if(inv__flag.equalsIgnoreCase("Y")){
					tsign_cnt++;											
				}%>
			<%
				if(tsign_cnt > 0){%>
					<script>
						document.getElementById('signPdf'+<%=k%>).disabled=true;
						document.getElementById('signPdf'+<%=k%>).title="Already Signed!!";

					</script>
				<%}
				if(t_cnt > 0){%>
				<script>
				document.getElementById('InvPdf'+<%=k%>).disabled=true;
				document.getElementById('InvPdf'+<%=k%>).title="Already Printed!!";
				</script>
			<%}
			}
		}
// 			System.out.println("invoice_path***rtyty**"+invoice_path);
		%>
		
			<td>
			 <div class="mt-5"> 
			<%	
				if((""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).trim().equals("") || (""+hlpl_Invoice_Actual_Seq_No.elementAt(k)).trim().equals("0"))
					{ 	%>
					<button type="button" class="btn btn-block btn-primary" id="InvPdf1_view<%=k%>" name="InvPdf1" value="View PDF" style="font-size:15;font-weight:bold;" disabled="disabled">View PDF</button>
				<%	}
					else
					{	
						if((osign_cnt > 0 || dsign_cnt > 0 || tsign_cnt > 0) && (o_cnt>0 || d_cnt>0 || t_cnt>0) ){
							//if PDF printed & Signed
							
							%>
							<button type="button" class="btn btn-block btn-primary" id="InvPdf1_view<%=k%>" name="InvPdf1" value="View PDF"  style="font-size:15;font-weight:bold;"
							onclick="setpara('<%=k%>','<%=hlpl_Invoice_Seq_No_Arr.size() %>'),openInvoiceInPdf_View('<%=k %>','<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>',
							'<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>',
							'<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>',
							'<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>',
							'<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>',
							'<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=month%>','<%=year%>',
							'<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>',
							'<%=(String)hlpl_Invoice_Seq_No_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Financial_Year_Arr.elementAt(k)%>',
							'<%=(String)hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=(String)customer_Invoice_Actual_Seq_No.elementAt(k)%>',
							'<%=hlpl_Invoice_Actual_Seq_No.size()%>','<%=k%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>',
							'<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>',
							'<%=""+tax_adj_flag_pdf.elementAt(k)%>','<%=VDrCrDocNo.elementAt(k) %>','<%=VDrCrDt.elementAt(k) %>','<%=p2%>','<%=invoice_path.toString().replace("[", "").replace("]", "")%>','V','<%=msg%>','<%=o_filename%>','<%=d_filename%>','<%=t_filename%>','<%=contact_person_cd.elementAt(k)%>','<%=VPDF_Inv_Dt.elementAt(k)%>','<%=vpdf_due_dt.elementAt(k)%>','<%=new_Invoice_Seq_No.elementAt(k)%>','<%=c_filename%>','<%=cd_filename%>','<%=credT_filename%>','<%=de_filename%>','<%=ded_filename%>','<%=debT_filename%>');">View PDF</button>
							
							<%
						}else{  
						
						if((""+inv_Approved_Flag.elementAt(k)).trim().equalsIgnoreCase("Y") && (""+customer_invoice_pdf_path_flag.elementAt(k)).trim().equalsIgnoreCase("Y"))
// 						if(print_permission.trim().equalsIgnoreCase("Y") && (""+inv_Approved_Flag.elementAt(k)).trim().equalsIgnoreCase("Y") )
						{	
							boolean condition = false;	
						
							if(invoice_Contract_Type.elementAt(i).equals("C") && date_flag) {} else {
								if((o_cnt>0 && d_cnt>0 && t_cnt>0) || (o_cnt>0 && d_cnt>0 && t_cnt>0 && (c_cnt>0 || s_cnt>0 || de_cnt>0 || cd_cnt>0 || sd_cnt>0 || ded_cnt>0) )) {
									condition = true;
								}
							}
// 							System.out.println("condition***"+condition);
// 							condition=true; //HA20181227 temporery TRUE 
							
							if(condition){ %>
							
							<button type="button" class="btn btn-block btn-primary" id="InvPdf1_view<%=k%>" name="InvPdf1" value="View PDF"  style="font-size:15;font-weight:bold;"
							onclick="setpara('<%=k%>','<%=hlpl_Invoice_Seq_No_Arr.size() %>'),openInvoiceInPdf_View('<%=k %>','<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>',
							'<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>',
							'<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>',
							'<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>',
							'<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>',
							'<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=month%>','<%=year%>',
							'<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>',
							'<%=(String)hlpl_Invoice_Seq_No_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Financial_Year_Arr.elementAt(k)%>',
							'<%=(String)hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=(String)customer_Invoice_Actual_Seq_No.elementAt(k)%>',
							'<%=hlpl_Invoice_Actual_Seq_No.size()%>','<%=k%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>',
							'<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>',
							'<%=""+tax_adj_flag_pdf.elementAt(k)%>','<%=VDrCrDocNo.elementAt(k) %>','<%=VDrCrDt.elementAt(k) %>','<%=p2%>','<%=invoice_path.toString().replace("[", "").replace("]", "")%>','V','<%=msg%>','<%=o_filename%>','<%=d_filename%>','<%=t_filename%>','<%=contact_person_cd.elementAt(k)%>','<%=VPDF_Inv_Dt.elementAt(k)%>','<%=vpdf_due_dt.elementAt(k)%>','<%=new_Invoice_Seq_No.elementAt(k)%>','<%=c_filename%>','<%=cd_filename%>','<%=credT_filename%>','<%=de_filename%>','<%=ded_filename%>','<%=debT_filename%>');">View PDF</button>	
						<% }
						
						else
						{%>
						<button disabled="disabled" type="button" class="btn btn-block btn-primary" id="InvPdf1_view<%=k%>" name="InvPdf1" value="View PDF"  style="font-size:15;font-weight:bold;"
							onclick="setpara('<%=k%>','<%=hlpl_Invoice_Seq_No_Arr.size() %>'),openInvoiceInPdf_View('<%=k %>','<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>',
							'<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>',
							'<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>',
							'<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>',
							'<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>',
							'<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=month%>','<%=year%>',
							'<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>',
							'<%=(String)hlpl_Invoice_Seq_No_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Financial_Year_Arr.elementAt(k)%>',
							'<%=(String)hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=(String)customer_Invoice_Actual_Seq_No.elementAt(k)%>',
							'<%=hlpl_Invoice_Actual_Seq_No.size()%>','<%=k%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>',
							'<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>',
							'<%=""+tax_adj_flag_pdf.elementAt(k)%>','<%=VDrCrDocNo.elementAt(k) %>','<%=VDrCrDt.elementAt(k) %>','<%=p2%>','<%=invoice_path.toString().replace("[", "").replace("]", "")%>','V','<%=msg%>','<%=o_filename%>','<%=d_filename%>','<%=t_filename%>','<%=contact_person_cd.elementAt(k)%>','<%=VPDF_Inv_Dt.elementAt(k)%>','<%=vpdf_due_dt.elementAt(k)%>','<%=new_Invoice_Seq_No.elementAt(k)%>','<%=c_filename%>','<%=cd_filename%>','<%=credT_filename%>','<%=de_filename%>','<%=ded_filename%>','<%=debT_filename%>');">View PDF</button>
						<%-- <button type="button" class="btn btn-block btn-primary" id="InvPdf1_view<%=k%>" name="InvPdf1" value="View PDF" style="font-size:15;font-weight:bold;" disabled="disabled">View PDF</button> --%>
						<%}}else
						{%>
							<button disabled="disabled" type="button" class="btn btn-block btn-primary" id="InvPdf1_view<%=k%>" name="InvPdf1" value="View PDF"  style="font-size:15;font-weight:bold;"
							onclick="setpara('<%=k%>','<%=hlpl_Invoice_Seq_No_Arr.size() %>'),openInvoiceInPdf_View('<%=k %>','<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>',
							'<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>',
							'<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>',
							'<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>',
							'<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>',
							'<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=month%>','<%=year%>',
							'<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>',
							'<%=(String)hlpl_Invoice_Seq_No_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Financial_Year_Arr.elementAt(k)%>',
							'<%=(String)hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=(String)customer_Invoice_Actual_Seq_No.elementAt(k)%>',
							'<%=hlpl_Invoice_Actual_Seq_No.size()%>','<%=k%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>',
							'<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>',
							'<%=""+tax_adj_flag_pdf.elementAt(k)%>','<%=VDrCrDocNo.elementAt(k) %>','<%=VDrCrDt.elementAt(k) %>','<%=p2%>','<%=invoice_path.toString().replace("[", "").replace("]", "")%>','V','<%=msg%>','<%=o_filename%>','<%=d_filename%>','<%=t_filename%>','<%=contact_person_cd.elementAt(k)%>','<%=VPDF_Inv_Dt.elementAt(k)%>','<%=vpdf_due_dt.elementAt(k)%>','<%=new_Invoice_Seq_No.elementAt(k)%>','<%=c_filename%>','<%=cd_filename%>','<%=credT_filename%>','<%=de_filename%>','<%=ded_filename%>','<%=debT_filename%>');">View PDF</button>
						<%-- <button type="button" class="btn btn-block btn-primary" id="InvPdf1_view<%=k%>" name="InvPdf1" value="View PDF" style="font-size:15;font-weight:bold;" disabled="disabled">View PDF</button> --%>
						<%}}
				}
				%>
			</div> 
		</td>
		
		<td>
			 <div class="mt-5"> 
			 	<button type="button" class="btn btn-block btn-primary" id="sendMail<%=k%>" name="sendMail" value="Send Mail" style="font-size:15;font-weight:bold;"
			 	<%if(osign_cnt > 0) {
			 	//System.out.println("invoice_path*****"+invoice_path);
			 	%>
			 		onclick="setpara('<%=k%>','<%=hlpl_Invoice_Seq_No_Arr.size() %>'),openInvoiceInPdf_View('<%=k %>','<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>',
							'<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>',
							'<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>',
							'<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>',
							'<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>',
							'<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=month%>','<%=year%>',
							'<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>',
							'<%=(String)hlpl_Invoice_Seq_No_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Financial_Year_Arr.elementAt(k)%>',
							'<%=(String)hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=(String)customer_Invoice_Actual_Seq_No.elementAt(k)%>',
							'<%=hlpl_Invoice_Actual_Seq_No.size()%>','<%=k%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>',
							'<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>',
							'<%=""+tax_adj_flag_pdf.elementAt(k)%>','<%=VDrCrDocNo.elementAt(k) %>','<%=VDrCrDt.elementAt(k) %>','<%=p2%>','<%=invoice_path.toString().replace("[", "").replace("]", "")%>','E','<%=msg%>','<%=o_filename%>','<%=d_filename%>','<%=t_filename%>','<%=contact_person_cd.elementAt(k)%>','<%=VPDF_Inv_Dt.elementAt(k)%>','<%=vpdf_due_dt.elementAt(k)%>','<%=new_Invoice_Seq_No.elementAt(k)%>','<%=c_filename%>','<%=cd_filename%>','<%=credT_filename%>','<%=de_filename%>','<%=ded_filename%>','<%=debT_filename%>');"
			 	<%}else{ %>
			 		disabled="disabled"	
			 	<%} %>
			 	 >Send Mail</button>
			 	 
			 	 <!-- for Form-402 -->
			 	 <button   type="button" class="btn btn-block btn-primary" id="form402sendMail<%=k%>" name="form402" value="Send Mail" style="font-size:15;font-weight:bold;display: none;"
			 		onclick="setpara('<%=k%>','<%=hlpl_Invoice_Seq_No_Arr.size() %>'),openInvoiceInPdf_View('<%=k %>','<%=(String)invoice_Customer_Abbr.elementAt(i)%>','<%=(String)invoice_Customer_Cd.elementAt(i)%>',
							'<%=(String)invoice_FGSA_No.elementAt(i)%>','<%=(String)invoice_FGSA_Rev_No.elementAt(i)%>','<%=(String)invoice_SN_No.elementAt(i)%>',
							'<%=(String)invoice_SN_Rev_No.elementAt(i)%>','<%=(String)invoice_Contract_Type.elementAt(i)%>',
							'<%=(String)invoice_Customer_Plant_Nm.elementAt(i)%>','<%=(String)invoice_Customer_Plant_Seq_No.elementAt(i)%>',
							'<%=(String)invoice_Bill_Period_Start_Dt.elementAt(i)%>','<%=(String)invoice_Bill_Period_End_Dt.elementAt(i)%>',
							'<%=(String)invoice_Due_Dt.elementAt(i)%>','<%=(String)invoice_HLPL_Seq_No.elementAt(k)%>','<%=month%>','<%=year%>',
							'<%=bill_cycle%>','<%=(String)invoice_Exchg_Rate_Cd.elementAt(i)%>','<%=(String)invoice_Exchg_Rate_Calculation_Method.elementAt(i)%>',
							'<%=(String)hlpl_Invoice_Seq_No_Arr.elementAt(k)%>','<%=(String)hlpl_Invoice_Financial_Year_Arr.elementAt(k)%>',
							'<%=(String)hlpl_Invoice_Actual_Seq_No.elementAt(k)%>','<%=(String)customer_Invoice_Actual_Seq_No.elementAt(k)%>',
							'<%=hlpl_Invoice_Actual_Seq_No.size()%>','<%=k%>','<%=(String)cust_invoice_mapping_id.elementAt(i) %>',
							'<%=(String)Invoice_Pending_approval.elementAt(k)%>','<%=invoice_pre_aprv.elementAt(i)%>',
							'<%=""+tax_adj_flag_pdf.elementAt(k)%>','<%=VDrCrDocNo.elementAt(k) %>','<%=VDrCrDt.elementAt(k) %>','<%=p2%>','<%=invoice_path.toString().replace("[", "").replace("]", "")%>','F','<%=msg%>','<%=o_filename%>','<%=d_filename%>','<%=t_filename%>','<%=contact_person_cd.elementAt(k)%>','<%=VPDF_Inv_Dt.elementAt(k)%>','<%=vpdf_due_dt.elementAt(k)%>','<%=new_Invoice_Seq_No.elementAt(k)%>','<%=c_filename%>','<%=cd_filename%>','<%=credT_filename%>','<%=de_filename%>','<%=ded_filename%>','<%=debT_filename%>');"
			 	 >Send Mail</button>
			 </div>
		</td>	 
			 
		
		<%
		if(flag){%>
		<script>
<%-- 			document.getElementById('invoice_title<%=k%>').value='<%=inv_title[k]%>'; --%>
		</script>
		<%} %>			
		<script>
		function setpara(ind,sz)
		{
// 				alert('in')
				document.forms[0].index_hid.value=ind;//alert(document.forms[0].index_hid.value);
				document.forms[0].size_hid.value=sz;
		}
		function openpdf(var1,ind){
		
			var1 = var1.substring(1,var1.length-1);
			var pdfpath=var1.split(",");
				
			var invoice_tit=document.getElementById("invoice_title"+ind).value;
			if(invoice_tit=="ORIGINAL")
			{
				if(pdfpath[0]=='' || pdfpath[0]==' ')
				{
					alert("PDF not available.Please print pdf for ORIGINAL Invoice !!");
				}
				else
				{
					window.open(pdfpath[0]);
				}
			}
			else if(invoice_tit=="DUPLICATE")
			{
				if(pdfpath[1]=='' || pdfpath[1]==' ')
				{
					alert("PDF not available.Please print pdf for DUPLICATE Invoice !!");
				}
				else
				{
					window.open(pdfpath[1]);
				}
			}
			else if(invoice_tit=="TRIPLICATE")
			{
				if(pdfpath[2]=='' || pdfpath[2]==' ')
				{
					alert("PDF not available.Please print pdf for TRIPLICATE Invoice !!");
				}
				else
				{
					window.open(pdfpath[2]);
				}
			}
			else if(invoice_tit=="CREDIT")
			{
				if(pdfpath[3]=='' || pdfpath[3]==' ')
				{
					alert("PDF not available.Please print pdf for CREDIT Invoice !!");
				}
				else
				{
					window.open(pdfpath[4]);
				}
			}else if(invoice_tit=="Sup"){
				if(pdfpath[4]=='' || pdfpath[4]==' '){
					alert("PDF not available.Please print pdf for Debit Invoice !!");
				}
				else{
					window.open(pdfpath[4]);
				}
			}
			else if(invoice_tit=="DEBIT"){
				if(pdfpath[5]=='' || pdfpath[5]==' '){
					alert("PDF not available.Please print pdf for Debit Invoice !!");
				}
				else{
					window.open(pdfpath[5]);
				}
			}
			
		}
		</script>
		</tr>
	<%k++;} %> 
	<%} %>
	<%}%>
	
			

</tbody>
</table>
</div>

<%if(invoice_Customer_Cd.size()==0){%>
<div class="box-body table-responsive no-padding">
	<table class="table  table-bordered">
		<thead>   
			<tr class="info">
				<th class="text-center"  style="color: red;">Data Not Available for the selected Month, Year &amp; Gas Day !!!</th>
			</tr>
		</thead>
	</table>
</div>
<%} %>
<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">

<input type="hidden" name="option" value="deleteInvoice">
<input type="hidden" name="inv_financial_year" value="">
<input type="hidden" name="hlpl_inv_seq_no" value="">
<input type="hidden" name="hlpl_inv_no" value="">
<input type="hidden" name="contract_type" value="">
<input type="hidden" name="del_mapping_id" value="">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="check_permission" value="<%=check_permission%>">
<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
<input type="hidden" name="deletecode" id="deletecode" value="<%=delete_code %>">
<input type="hidden" name="index_hid" id="index_hid" value="">
<input type="hidden" name="size_hid" id="size_hid" value="0">
<input type="hidden" name="inv_title_string" id="inv_title_string" value="<%=inv_title_string%>">
<%}catch(Exception e){ 
e.printStackTrace();
} %>	
</div>
<!-- /.box-body -->


</div>
 </form>   
</div>
<!-- /.box -->
</div>

<!-- jQuery -->
<script src="../js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<!-- <script src="../js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>

</body>
</html>