<%@ page import="java.util.*"%>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<html>
<head>
<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<link rel="stylesheet" href="../css/tlu.css">
<title>Invoice </title>

<script>
var newWindow;
function sel_typ()
{
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var inv_type=document.forms[0].inv_type.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	
	var form_cd=document.forms[0].form_id.value;
	var form_name=document.forms[0].form_nm.value;
	var url="../general_inv/frm_other_invoices_dtl.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission+"&form_cd="+form_cd+"&form_name="+form_name;
	location.replace(url);
}

function Do_close(SuplCd,year,month,inv_type,msg,write_perm,check_perm,authoerize_perm,approve_perm,print_perm,audit_perm,delete_perm)
{
	window.opener.refersh(SuplCd,year,month,inv_type,msg,write_perm,check_perm,authoerize_perm,approve_perm,print_perm,audit_perm,delete_perm);
	window.close();
}
function refersh(supp_cd,yr,month,inv_type,msg,write_perm,check_perm,approve_perm,print_perm,delete_perm)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var url="../sales_invoice/frm_mst_prepareInvoice.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&msg="+msg+"&supplier_cd="+supp_cd+"&alw_add="+write_perm+"&alw_del="+delete_perm+"&alw_print="+print_perm+"&alw_upd="+approve_perm+"&alw_view="+check_perm+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
	location.replace(url);
	}
function Check_inv(new_seq_no,inv_type,hlpl_seq_no,fin_yr,supp_cd_x)
{
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var supp_cd=document.forms[0].Supp_Cd.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var form_cd=document.forms[0].form_id.value;
	var form_name=document.forms[0].form_nm.value;
	var flag="C";
	if(inv_type=="Z" || inv_type=="A") {
			var url="../general_inv/frm_other_invoices_check_Z.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&supplier_cd="+supp_cd+"&seq_no="+new_seq_no+"&hlpl_seq_no="+hlpl_seq_no+"&fin_yr="+fin_yr+"&flag="+flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&form_cd="+form_cd+"&form_name="+form_name;
			window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1");
		}
}
function printed_pdf(flag)
{
	if(flag=='O') {
		alert("ORIGINAL PDF Already Printed");
	}
	if(flag=='D') {
		alert("DUPLICATE PDF Already Printed");
	}
	if(flag=='T') {
		alert("TRIPLICATE PDF Already Printed");
	}
}
function PDF_inv(CustCd, new_seq_no,inv_type,hlpl_seq_no,fin_yr,pdf_flag,supp_cd_x,PrtFormat)
{
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	
	var flag="P";
	if(inv_type=="Z" || inv_type=="A") {
			var url="../general_inv/frm_other_invoices_check_Z.jsp?year="+yr+"&month="+month+"&cust_cd="+CustCd+"&inv_type="+inv_type+"&seq_no="+new_seq_no+"&hlpl_seq_no="+hlpl_seq_no+"&fin_yr="+fin_yr+"&flag="+flag+"&pdf_flag="+pdf_flag+"&Template_Type="+PrtFormat+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission;
			//alert(url);
			window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1");
		}
}
function Approve_inv(new_seq_no,inv_type,hlpl_seq_no,fin_yr,supp_cd_x)
{
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var supp_cd=document.forms[0].Supp_Cd.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var form_cd=document.forms[0].form_id.value;
	var form_name=document.forms[0].form_nm.value;
	var flag="A";
	if(inv_type=="Z" || inv_type=="A") {
			var url="../general_inv/frm_other_invoices_check_Z.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&supplier_cd="+supp_cd+"&seq_no="+new_seq_no+"&hlpl_seq_no="+hlpl_seq_no+"&fin_yr="+fin_yr+"&flag="+flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&form_cd="+form_cd+"&form_name="+form_name;
			window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1");
		}
}
function print_pdf(pdfpath)
{
   // alert(pdfpath);
	window.open(pdfpath,"actionReport","top=10,left=70,width=650,height=650,scrollbars=1,status=1,maximize=yes,resizable=1");
}
function Modify_inv(new_seq_no,inv_type,hlpl_seq_no,fin_yr,supp_cd_x,PdfFormat)
{
	//alert("in flag");
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var supp_cd=document.forms[0].Supp_Cd.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	
	var form_cd=document.forms[0].form_id.value;
	var form_name=document.forms[0].form_nm.value;
	var flag="Modify";
	if(inv_type=="Z" || inv_type=="A") {
		var url="../general_inv/frm_other_invoices_Z.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&supplier_cd="+supp_cd+"&seq_no="+new_seq_no+"&hlpl_seq_no="+hlpl_seq_no+"&fin_yr="+fin_yr+"&flag="+flag+"&pdf_format_type="+PdfFormat+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission+"&form_cd="+form_cd+"&form_name="+form_name;
		location.replace(url);
	}
}
function click_inv(FY)
{
	//alert(FY);
	var flag = false;
 	var msg = "Following Fields To Be Filled Properly :\n";
 	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
 	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var inv_type=document.forms[0].inv_type.value;
	var supp_cd=document.forms[0].Supp_Cd.value;
	
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
 	
 	if(inv_type=='' || inv_type==' '|| inv_type==null)
	{
		msg += "Please Select Inv Type!\n";
 		flag = true;
 	}
	if(flag) {
		alert(msg);
	}else {
		if(inv_type=="Z" || inv_type=="A")
		{
			var url="../general_inv/frm_other_invoices_Z.jsp?year="+yr+"&month="+month+"&fin_yr="+FY+"&inv_type="+inv_type+
					"&supplier_cd="+supp_cd+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
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
}
function limitText1(limitField, limitCount, limitNum) {
	var limitField= document.forms[0].t2;
	var limitCount=document.forms[0].countdown1;
	var limitNum=2000;
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, 2000);
		alert("Can not allowed more than 2000 Characters!!!");
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}

function breakline1()
{
	if(event.keyCode==13)
	{
		document.forms[0].t2.value=document.forms[0].t2.value;
		document.forms[0].t2.focus();
	}
}
function subm()
{
 	var flag = false;
 	var msg = "Following Fields To Be Filled Properly :\n";
	
	if(document.forms[0].inv_dt.value==''||document.forms[0].inv_dt.value==' '||document.forms[0].inv_dt.value==null)
	{
		msg += "Invoice Date field cannot be blank !\n";
 		flag = true;
	}
	
 	if(document.forms[0].pay_dt.value==''||document.forms[0].pay_dt.value==' '||document.forms[0].pay_dt.value==null)
	{
		msg += "Payment Due Date field cannot be blank !\n";
 		flag = true;
 	}
 	
 	if(document.forms[0].t2.value==''||document.forms[0].t2.value==' '||document.forms[0].t2.value==null)
	{
		msg += "Address field cannot be blank !\n";
 		flag = true;
 	}
 	if(document.forms[0].curr.value=='0' ||document.forms[0].curr.value==' '||document.forms[0].curr.value==null)
	{
		msg += "Currency field cannot be blank !\n";
 		flag = true;
 	}
 	if(document.forms[0].amt.value==''||document.forms[0].amt.value==' '||document.forms[0].amt.value=='0')
	{
		msg += "Amount field cannot be blank !\n";
 		flag = true;
 	}
 	
 	if(flag)
 	{
 		alert(msg);
 	}
 	else
 	{
 		var a = confirm("Do You Want To Submit Data ?");
 		if(a)
 		{
//  			document.getElementById("loading").style.visibility = "visible";
//     		document.getElementById("loading-image").style.visibility = "visible";
 			document.forms[0].sub.disabled=true;
 			document.forms[0].submit();
 		}
 	}
}
function change_st()
{
	    var yr=document.forms[0].year.value;
		var month=document.forms[0].month.value;
		var inv_type=document.forms[0].inv_type.value;
		var t2=document.forms[0].t2.value;
		var pan_no=document.forms[0].pan_no.value;
		var inv_dt=document.forms[0].inv_dt.value;
		var pay_dt=document.forms[0].pay_dt.value;
		var ST_NO=document.forms[0].ST_NO.value;
		var curr=document.forms[0].curr.value;
		var cust_pan_no=document.forms[0].cust_pan_no.value;
		var amount=document.forms[0].amt.value;
		var state=document.forms[0].state.value;
		var t3=document.forms[0].t3.value;
	
		var url="../general_inv/frm_other_invoices.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&t2="+t2+"&customer_pan_no="+pan_no+"&inv_dt="+inv_dt+"&pay_dt="+pay_dt+"&Contact_Suppl_Service_tax_no="+ST_NO+"&curr="+curr+"&cust_pan_no="+cust_pan_no+"&amt="+amount+"&state="+state+"&t3="+t3;
		location.replace(url);
}
function viewlist()
{
	var url="../general_inv/frm_customerlist.jsp";
	window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1")
	
}
function check_pan(obj)
{
	var p=obj.value;
	if(p.length>10)
		{
			alert("YOU CANNOT ENTER PAN NO MORE THAN 10 CHARACTERS");
			document.forms[0].cust_pan_no.value='';
		}
}


function refrseh(cust_nm,pan_no,st,CUST_CD)

{
	//alert(cust_nm);
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var inv_type=document.forms[0].inv_type.value;
	var t2=cust_nm;
	document.forms[0].t2.value=t2;
	var pan_no=pan_no;
	document.forms[0].pan_no.value=pan_no;
	var inv_dt=document.forms[0].inv_dt.value;
	var pay_dt=document.forms[0].pay_dt.value;
	//var CUST_CD11=document.forms[0].CUST_CD11.value;
	var url="../general_inv/frm_other_invoices.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&t2="+t2+"&customer_pan_no="+pan_no+"&inv_dt="+inv_dt+"&pay_dt="+pay_dt+"&state="+st+"&CUST_CD="+CUST_CD;
	location.replace(url);
}
function change_month()
{
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var inv_type=document.forms[0].inv_type.value;
	var supp_cd=document.forms[0].Supp_Cd.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var url="../sales_invoice/frm_mst_prepareInvoice.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&supplier_cd="+supp_cd+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
	
// 	var url="../general_inv/frm_other_invoices_dtl.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&supplier_cd="+supp_cd+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission;
	location.replace(url);
}

</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Other_InvoiceV2" id="dta" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
util.init();
String sysdate = util.getGen_dt();
String curr_year = util.getGet_yr();
String curr_mth = "07";//util.getGet_mn();

String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String year=request.getParameter("year")==null?curr_year:request.getParameter("year");
String month=request.getParameter("month")==null?curr_mth:request.getParameter("month");
String inv_type=request.getParameter("inv_type")==null?"Z":request.getParameter("inv_type");
String supplier_cd=request.getParameter("supplier_cd")==null?"1":request.getParameter("supplier_cd");


String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200506
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200506
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200506

String write_permission = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
String check_permission = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
String approve_permission = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
String delete_permission = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
String print_permission = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");

// String tax_str="18";
// String tax_str1="9";
HttpServletRequest req = request;


dta.setYear(year);
dta.setMonth(month);
dta.setInv_type(inv_type);
dta.setSupp_cd_set(supplier_cd);
dta.setRequest(req);
dta.setCallFlag("FETCH_INVOICE_DTL");
dta.init();
Vector Vinv_seq_no=dta.getVinv_seq_no();
Vector Vinv_dt=dta.getVinv_dt();
Vector Vhlpl_inv_seq_no=dta.getVhlpl_inv_seq_no();
Vector Vfin_yr=dta.getVfin_yr();
Vector Vcust_nm=dta.getVcust_nm();
Vector VCust_cd=dta.getVCust_cd();
Vector checked=dta.getChecked();
Vector approved=dta.getApproved();
Vector Vpdf_dtl=dta.getVpdf_dtl();
Vector pdf=dta.getPdf();
Vector pdf1=dta.getPdf1();
Vector pdf2=dta.getPdf2();
String supp_abbr=dta.getSupplier_abbr();
Vector flag_sac=dta.getVflag_sac();
Vector Vsupp_cd=dta.getVSupp_cd();
Vector Vvendor_abr=dta.getVvendor_abr();
 int yr=2017;
//////////SB20170814///////// 
String Fin_yr=dta.getFin_yr();
String NoInvGeneratedBeyond=dta.getNoInvGeneratedBeyond();
String LastInvGeneratedDt=dta.getLastInvGeneratedDt();
Vector VPDF_Template_Allocated=dta.getVPDF_Template_Allocated();	

/////////////////SB20180319/////////////
String Min_FY=dta.getMin_FY(); //SB20180316
Vector VSUPP_CD=dta.getSUPP_CD();
Vector VSUPP_NM=dta.getSUPP_NM();
Vector VSUPP_ABR=dta.getSUPP_ABR();
////////////////////////////////////////
%>
<div class="tab-content">
<div class="tab-pane active" id="invoicing">
<body>
<%-- <body <%if(msg.length()>6) { %>onload="Do_close('<%=supplier_cd%>','<%=year%>','<%=month%>','<%=inv_type%>','<%=msg%>','<%=write_permission%>','<%=check_permission%>','<%=authorize_permission%>','<%=approve_permission%>','<%=print_permission%>','<%=audit_permission%>','<%=delete_permission%>');"<%}%>> --%>
<form method="post"  action="../servlet/Frm_General_Invoice">

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">

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
		<div class="box-header with-border main-header" >
			<div class="form-group col-md-12 text-center">
		 	 	<label for="" >
		 	 		<select name="Supp_Cd" onchange="change_month();">			
					  <%for(int i=0;i<VSUPP_CD.size(); i++) {%> 
					  <option value="<%=VSUPP_CD.elementAt(i)%>"><%=VSUPP_ABR.elementAt(i)%></option>
					  <%}%>					  
					</select> Invoice
								
					<script>
					 		document.forms[0].Supp_Cd.value="<%=supplier_cd%>";
					</script>
				</label>
			</div>
		</div>	
		<div class="box-header with-border main-header" >
			<div class="form-group col-md-2">
		 	 <label for="">Year</label>
				<select class="form-control" name="year" onChange="">	
				<option value="0">--Select--</option>				
				  <%for(int i=Integer.parseInt(curr_year);i>=Integer.parseInt(Min_FY); i--) {%> 
				  <option value="<%=i%>"><%=i%></option>
			  <%}%>					  
			</select>			
			<script>
			 		document.forms[0].year.value="<%=year%>";
			 </script>
		 </div>
		 
			<div class="form-group col-md-2">
		 		<label for="">Month</label>
		 		<select class="form-control" name="month"  onchange="change_month();">
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
		 	
		 	<div class="form-group col-md-3">
		 		<label for="">Invoice Type</label>
		 		<select class="form-control" name="inv_type"  onchange="sel_typ();">
		 			<option value="Z">Invoice Preparation (<%=supp_abbr%>)</option>
<%-- 					<option value="A">Receipt Voucher (<%=supp_abbr%>)</option> --%>
		 		</select>
		 		<script>
					document.forms[0].inv_type.value="<%=inv_type%>";
				</script>
		 	</div>
		 	
		 	<div class="form-group col-md-2" title="Click here to Prepare New Invoice">
				<label for="">&nbsp;</label>
			 	  <div class='input-group'>
<!-- 			     	<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="refreshPage();" > View List </button> -->
			     	
			     	 <%if(!NoInvGeneratedBeyond.equals("0")) { %>
<%-- 					<img src="../images/xmark.png" width="30" height="30" title="<%=NoInvGeneratedBeyond %> Invoice(s) Already Generated Till Date <%=LastInvGeneratedDt %>"/> --%>
					 <%if(inv_type.equals("Z")){ %>
				 	<button type="button"  class="btn btn-primary"  name="btn"  value="[<%=Fin_yr %>] Prepare New Invoice" onclick="click_inv('<%=Fin_yr %>');" DISABLED >Prepare New Invoice</button>
				 	<%} else if(inv_type.equals("A")) { %>
				 	<button type="button"  class="btn btn-primary"  name="btn" style="background-color:green; color: white;" value="[<%=Fin_yr %>] Prepare Receipt  Voucher" onclick="click_inv('<%=Fin_yr %>');" DISABLED> Prepare New Invoice</button>
				 	<%} %>
				<%} else {%>
<%-- 					<img src="../images/checkmark.png" width="30" height="30"  title="Last Inv Dt:  <%=LastInvGeneratedDt %> and Allowed To Generate Invoice(s) "/> --%>
					<%if(inv_type.equals("Z")){ %>
				 	<button type="button"  class="btn btn-primary"  name="btn"  value="[<%=Fin_yr %>] Prepare New Invoice" onclick="click_inv('<%=Fin_yr %>');" >Prepare New Invoice</button>
				 	<%} else if(inv_type.equals("A")) { %>
				 	<button type="button"  class="btn btn-primary"  name="btn" style="background-color:green; color: white;" value="[<%=Fin_yr %>] Prepare Receipt  Voucher" onclick="click_inv('<%=Fin_yr %>');" >Prepare New Invoice</button>
				 	<%} %>
				<%} %>
			     </div>
			 </div>	
		</div>
		
		<div class="box-body table-responsive no-padding">
			<div class="table-responsive">
				<table id="example" class="table table-bordered table-hover">
					<thead>
						<tr class="info">
							<th rowspan="2" class="text-center">SR#</th>
							<th rowspan="2" class="text-center">Transporter</th>
							<th rowspan="2" class="text-center">FY</th>
							<th rowspan="2" class="text-center">Invoice<br> Seq NO</th>
							<th rowspan="2" class="text-center">Invoice<br> Date</th>
							<td class="text-center" rowspan="2">Prt</td>
							<%if(inv_type.equalsIgnoreCase("Z")) { %>
							<th class="text-center" colspan="3">Print PDF</th>
							<th class="text-center" colspan="3">View PDF</th>
							<%}else { %>
							<th class="text-center" colspan="2">Print PDF</th>
							<th class="text-center" colspan="2">View PDF</th>
							<%} %>
						</tr>
						<tr class="info">
						<%if(inv_type.equalsIgnoreCase("Z")) { %>
							<th class="text-center" colspan="1">Ori</th>
							<th class="text-center" colspan="1">Dup</th>
							<th class="text-center" colspan="1">Tri</th>
							<th class="text-center" colspan="1">Ori</th>
							<th class="text-center" colspan="1">Dup</th>
							<th class="text-center" colspan="1">Tri</th>
							<%}else { %>
							<th class="text-center" colspan="1">Ori</th>
							<th class="text-center" colspan="1">Dup</th>
							<th class="text-center" colspan="1">Ori</th>
							<th class="text-center" colspan="1">Dup</th>
							<%} %>
						</tr>
						
						<%
						if(Vinv_seq_no.size()>0) { %>
						<%for(int i=0;i<Vinv_seq_no.size();i++){ 
							if(i%2==0) {%>
							<tr class="content1" style="background-color: #E0EEE0">
							<%}else{ %>
							<tr class="content1">
							<%} %>
								<td align="center">
									<b><%=i+1 %></b>
								</td>
					
								<td title="Customer Name: <%=VCust_cd.elementAt(i) %>">
									<b>&nbsp;<%=Vcust_nm.elementAt(i) %></b> [<%=Vvendor_abr.elementAt(i)%>]
								</td>
								<td align="center" title="Financial Year">
									<b><%=Vfin_yr.elementAt(i) %></b>				
								</td>
								<%if(inv_type.equalsIgnoreCase("1") || inv_type.equalsIgnoreCase("2")) { %>
								<td align="center" title="New Inv Seq No">
									<b><%=supp_abbr%>/<%=Vinv_seq_no.elementAt(i) %>/P</b>
								</td>
								<%}else if(inv_type.equalsIgnoreCase("X")){ %>
									<td align="center" title="Inv Seq No">
									<%if(Vsupp_cd.elementAt(i).equals("1")){ %>
									<b>RCL<%=Vinv_seq_no.elementAt(i) %></b>
									<%}else if(Vsupp_cd.elementAt(i).equals("2")){ %>
									<b>RCP<%=Vinv_seq_no.elementAt(i) %></b>
									<%} %>
								</td>
							<%	} else { %>
								<td align="center" title="Inv Seq No">
									<b><%=Vinv_seq_no.elementAt(i) %></b>
								</td>
								
								<%} %>
								<td align="center" title="Invoice Date">
									<b><%=Vinv_dt.elementAt(i) %></b>
								</td>
								<%if(approved.elementAt(i).toString().equalsIgnoreCase("N")) { %>
<%-- 									<td align="center" title="Click here to Modify Invoice"><input type="button" <%if(checked.elementAt(i).equals("Y")) { %>disabled="disabled"  style="" <%} %> style="color: black;" name="modify" value="Modify" onclick="Modify_inv('<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','<%=Vsupp_cd.elementAt(i)%>','<%=VPDF_Template_Allocated.elementAt(i) %>');"></td><!-- Modify Invoice --> --%>
								<%}else{ %>
<%-- 									<td align="center" title="Click here to Modify Invoice"><input type="button" <%if(checked.elementAt(i).equals("Y")) { %>disabled="disabled"  style="" <%} %> style="color: black;" name="modify" value="Modify" onclick="Modify_inv('<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','<%=Vsupp_cd.elementAt(i)%>','<%=VPDF_Template_Allocated.elementAt(i) %>');"></td><!-- Modify Invoice --> --%>
								<%} %>
								<%if(checked.elementAt(i).equals("Y")) { %>
<%-- 									<td align="center" title="Checked Invoice" style="background-color: #FFDAB9"><input type="button"   name="check" <%if(checked.elementAt(i).equals("Y")) { %>disabled="disabled"    <%}else if(check_permission.equalsIgnoreCase("N")) { %> disabled="disabled"    <%} %>  <%if(checked.elementAt(i).equals("Y")) { %> value="Checked" style="color: blue;" <%} %> value="Check" onclick="Check_inv('<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','<%=Vsupp_cd.elementAt(i)%>');"></td> --%>
								<%}else{ %>
<%-- 									<td align="center" ><input type="button"  name="check" <%if(check_permission.equalsIgnoreCase("N")) { %> disabled="disabled"  title="You Have no access to check invoice" <%} %> title="Click To Check Invoice"  value="Check" style="color: black;" onclick="Check_inv('<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','<%=Vsupp_cd.elementAt(i)%>');"></td> --%>
								<%} %>
								
								
								<%if(approved.elementAt(i).equals("Y")) { %>
<%-- 									<td align="center" title="Approved Invoice" style="background-color: lightgreen"><input type="button"   name="approve" <%if(approved.elementAt(i).equals("Y")) { %> disabled="disabled"  <%}else if(checked.elementAt(i).equals("N") || checked.elementAt(i).equals("") ) { %> disabled="disabled"  <%}else if(approve_permission.equalsIgnoreCase("N")) { %> disabled="disabled"  <%} %>  value="Approved" style="color: blue;" onclick="Approve_inv('<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','<%=Vsupp_cd.elementAt(i)%>');"></td> --%>
								<%}else{ %>
<%-- 									<td align="center"><input type="button"   name="approve" <%if(checked.elementAt(i).equals("N") || checked.elementAt(i).equals("") ) { %> disabled="disabled"  title="Check Process Is not completed"  <%}else if(approve_permission.equalsIgnoreCase("N")) { %> disabled="disabled"  title="You have no access to approve Invoice"  <%} %> style="color: black;"  title="Click Here to Appprove Invoice" value="Approve" onclick="Approve_inv('<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','<%=Vsupp_cd.elementAt(i)%>');"></td> --%>
								<%} %>
								<td align="center" title="Print Format Selected">
									<b><%=VPDF_Template_Allocated.elementAt(i) %></b>
								</td>
								
								<% if(print_permission.equalsIgnoreCase("N") || checked.elementAt(i).equals("N") || approved.elementAt(i).equals("N") || checked.elementAt(i).equals("") || approved.elementAt(i).equals("")) { %>   
									<td align="center" <% if(print_permission.equalsIgnoreCase("N")) {%> title="You have no access to print pdf for invoice" <%}else if(approved.elementAt(i).equals("") || approved.elementAt(i).equals("N")){ %> title="Approve process is not completed" <%} %>>
								</td>
								 <%}else{ %>
								 	<td align="center" title="Click here to Print Invoice">
										<img src="../images/printa.gif" width="30" height="30" alt=""   value="Print" style="color: green;" <%if(Vpdf_dtl.elementAt(i).toString().length()==2) { %> onclick="printed_pdf('O');" title="Original Printed" <%}else{ %> onclick="PDF_inv('<%=VCust_cd.elementAt(i) %>','<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','O','<%=Vsupp_cd.elementAt(i)%>','<%=VPDF_Template_Allocated.elementAt(i) %>');"<%} %>/>
								</td>
								 <%} %>
								 
								<% if(print_permission.equalsIgnoreCase("N") || checked.elementAt(i).equals("N") || approved.elementAt(i).equals("N") || checked.elementAt(i).equals("") || approved.elementAt(i).equals("") || Vpdf_dtl.elementAt(i).equals("")) { %>   
									<td align="center" <% if(print_permission.equalsIgnoreCase("N")) {%> title="You have no access to print pdf for invoice" <%}else if(approved.elementAt(i).equals("") || approved.elementAt(i).equals("N")){ %> title="Approve process is not completed" <%} %>>
								</td>
								 <%}else{ %>
								 	<td align="center" title="Click here to Print Invoice">
										<img src="../images/printa.gif" width="30" height="30" alt=""   value="Print" style="color: green;" <%if(Vpdf_dtl.elementAt(i).toString().length()==2) { %> onclick="printed_pdf('D');" title="Duplicate printed" <%}else{ %> onclick="PDF_inv('<%=VCust_cd.elementAt(i) %>','<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','D','<%=Vsupp_cd.elementAt(i)%>','<%=VPDF_Template_Allocated.elementAt(i) %>');" <%} %>/>
									</td>
								 <%} %>
							 	<% if(inv_type.equalsIgnoreCase("Z")) {
							 		if(flag_sac.elementAt(i).equals("S")) {%>
										 	<% if(print_permission.equalsIgnoreCase("N") || checked.elementAt(i).equals("N") || approved.elementAt(i).equals("N") || checked.elementAt(i).equals("") || approved.elementAt(i).equals("") || Vpdf_dtl.elementAt(i).toString().length()<2) { %>   
											<td align="center" <% if(print_permission.equalsIgnoreCase("N")) {%> title="You have no access to print pdf for invoice" <%}else if(approved.elementAt(i).equals("") || approved.elementAt(i).equals("N")){ %> title="Approve process is not completed" <%} %>>
												-</td>
										 <%}else{ %>
										 	<td align="center" title="Click here to Print Invoice">
												<img src="../images/printa.gif" width="30" height="30" alt=""   value="Print" style="color: green;" <%if(Vpdf_dtl.elementAt(i).toString().length()==3) { %> onclick="printed_pdf('T');" title="Triplicate printed" <%}else{ %> onclick="PDF_inv('<%=VCust_cd.elementAt(i) %>','<%=Vinv_seq_no.elementAt(i) %>','<%=inv_type%>','<%=Vhlpl_inv_seq_no.elementAt(i)%>','<%=Vfin_yr.elementAt(i)%>','T','<%=Vsupp_cd.elementAt(i)%>','<%=VPDF_Template_Allocated.elementAt(i) %>');" <%} %>/>
											</td>
										 <%} %>
									 <%}else{ %>
										 <td align="center" title="">-</td>
										<%} %>
							 	<%} %>
				
										<td align="center" title="Click here to Print Invoice">
										<%if(!pdf.elementAt(i).equals("")) { %>
											<button type="button"  class="btn btn-primary" onclick="print_pdf('<%=pdf.elementAt(i) %>');" title="Click to see Original(RECIPIENT) Printed PDF"> View PDF </button>
											<%} %>
										</td>
										<td align="center" title="Click here to Print Invoice">
										<%if(!pdf1.elementAt(i).equals("")) { %>
											<button type="button"  class="btn btn-primary" onclick="print_pdf('<%=pdf1.elementAt(i) %>');" title="Click to see Duplicate(Supplier) Printed PDF"> View PDF </button>
											<%} %>
										</td>
										<td align="center" title="Click here to Print Invoice">
										<%if(!pdf2.elementAt(i).equals("")) { %>
											<button type="button"  class="btn btn-primary" onclick="print_pdf('<%=pdf2.elementAt(i) %>');" title="Click to see Duplicate(Transporter) Printed PDF"> View PDF </button>
											<%} %>
										</td>
							</tr>
							
						<%}%>
						<tr class="title1" >
							<td colspan="15">
							 <%if(!NoInvGeneratedBeyond.equals("0")) { %>
								<img src="../images/xmark.png" width="20" height="20" title="<%=NoInvGeneratedBeyond %> Invoice(s) Already Generated Till Date<%=LastInvGeneratedDt %>"/> <%=NoInvGeneratedBeyond %> Invoice(s) Already Generated Till Date <%=LastInvGeneratedDt %>
							<%} %>
							</td>
						</tr>
						<%} else {%>
							<tr class="title1" ><td colspan="15">Data Not Available</td></tr>
						<%} %>
					</thead>
				</table>
			</div>
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
</body>
</div>
</div>
</html>