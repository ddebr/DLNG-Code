<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>SN Selection List</title>

<link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/utilities.css">


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

<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<title>Invoice </title>

<script>
function sel_rd(sac_cd,desc_nm)
{
	//alert("---"+cust_cd+"--"+cust_addr+"--"+cust_state_cd+"--999--"+cust_state_nm);
	window.opener.document.forms[0].sac_cd.value=sac_cd;
	window.opener.document.forms[0].sac_desc.value=desc_nm;
	
// 	var st='24';
// 	window.opener.refrseh(cust_nm,pan_no,st,cust_cd,yr,mnth);
	window.close();

}
function sel_rd1(vs_cd,vs_nm,vs_flg,vs_item)
{
	//alert("---"+cust_cd+"--"+cust_nm);
	window.opener.document.forms[0].vessel_nm.value=vs_nm;
	window.opener.document.forms[0].vessel_flg.value=vs_flg;
	window.opener.document.forms[0].vessel_itm.value=vs_item;
	window.opener.document.forms[0].vessel_cd.value=vs_cd;
	window.close();

}
function sel_supp(vs_cd,vs_nm,state_cd,gstin_no,pan_no,GST_INVOICE_SEQ_NO,GST_INVOICE_SEQ_NO1,inv_seq_no,inv_seq_no1,cust_seq,cust_seq1)
{
	//alert("---"+cust_cd+"--"+cust_nm);
	window.opener.document.forms[0].supp_nm.value=vs_nm;
	window.opener.document.forms[0].supp_cd_hid.value=vs_cd;
	window.opener.document.forms[0].sup_stcd.value=state_cd;
	window.opener.document.forms[0].ST_NO.value=gstin_no;
	window.opener.document.forms[0].pan_no.value=pan_no;
	if(vs_cd=='1') {
		window.opener.document.forms[0].inv_no.value=GST_INVOICE_SEQ_NO;
		window.opener.document.forms[0].INVOICE_SEQ_NO.value=inv_seq_no;
		window.opener.document.forms[0].cust_seq_no1.value=cust_seq;
		
	}else if(vs_cd=='2') 
	{
		window.opener.document.forms[0].inv_no.value=GST_INVOICE_SEQ_NO1;
		window.opener.document.forms[0].INVOICE_SEQ_NO.value=inv_seq_no1;
		window.opener.document.forms[0].cust_seq_no1.value=cust_seq1;
	}
	
	window.close();

}
function sel_supp_X(vs_cd,vs_nm,state_cd,gstin_no,pan_no,GST_INVOICE_SEQ_NO,GST_INVOICE_SEQ_NO1,inv_seq_no,inv_seq_no1,cust_seq,cust_seq1)
{
	//alert("---"+cust_cd+"--"+cust_nm);
	window.opener.document.forms[0].supp_nm.value=vs_nm;
	window.opener.document.forms[0].supp_cd_hid.value=vs_cd;
	window.opener.document.forms[0].sup_stcd.value=state_cd;
	window.opener.document.forms[0].ST_NO.value=gstin_no;
	window.opener.document.forms[0].pan_no.value=pan_no;
	if(vs_cd=='1') {
		window.opener.document.forms[0].inv_no.value="RCL"+GST_INVOICE_SEQ_NO;
		window.opener.document.forms[0].newinv_no.value=GST_INVOICE_SEQ_NO;
		window.opener.document.forms[0].INVOICE_SEQ_NO.value=inv_seq_no;
		window.opener.document.forms[0].cust_seq_no1.value=cust_seq;
		
	}else if(vs_cd=='2') 
	{
		window.opener.document.forms[0].inv_no.value="RCP"+GST_INVOICE_SEQ_NO1;
		window.opener.document.forms[0].newinv_no.value=GST_INVOICE_SEQ_NO1;
		window.opener.document.forms[0].INVOICE_SEQ_NO.value=inv_seq_no1;
		window.opener.document.forms[0].cust_seq_no1.value=cust_seq1;
	}
	
	window.close();

}
function sel_Vendor(Flag,vendor_cd,city,gstin_no,pan_no,country,pin,state_cd,year,month,supp_cd_hid,inv_type11,state_nm)
{
	//alert(" FLAG: "+Flag);
	if(inv_type11=='X') {
	//	window.opener.document.forms[0].t2.value=nm;
		//window.opener.document.forms[0].address.value=addr;
	//	alert("dnfmk--country-->>>"+country+"<<--");
		window.opener.document.forms[0].city.value=city;
		window.opener.document.forms[0].state_nmS.value=state_nm;
		window.opener.document.forms[0].cust_stcd.value=state_cd;
		window.opener.document.forms[0].pin_code.value=pin;
		window.opener.document.forms[0].cust_pan_no.value=pan_no;
		window.opener.document.forms[0].CUST_CD11.value=vendor_cd;
		var write_permission = document.forms[0].write_permission.value;
		var delete_permission = document.forms[0].delete_permission.value;
		var print_permission = document.forms[0].print_permission.value;
		var check_permission = document.forms[0].check_permission.value;
		var authorize_permission = document.forms[0].authorize_permission.value;
		var approve_permission = document.forms[0].approve_permission.value;
		var audit_permission = document.forms[0].audit_permission.value;
		window.opener.refrseh(vendor_cd,city,pan_no,country,pin,state_cd,write_permission,check_permission,authorize_permission,approve_permission,print_permission,audit_permission,delete_permission,year,month,supp_cd_hid,gstin_no,state_nm);
		window.close();
	}else if(inv_type11=='Z'){
// 		window.opener.document.forms[0].t2.value=nm;
// 		window.opener.document.forms[0].address.value=addr;
		window.opener.document.forms[0].city.value=city;
		window.opener.document.forms[0].state.value=state_cd;//alert(state_cd);
		window.opener.document.forms[0].pin_code.value=pin;
		window.opener.document.forms[0].cust_gstin_no1.value=gstin_no;
		window.opener.document.forms[0].cust_pan_no1.value=pan_no;
		window.opener.document.forms[0].cust_type.value=Flag;//alert(Flag);
		window.opener.document.forms[0].CUST_CD11.value=vendor_cd;//alert(vendor_cd);
		var write_permission = document.forms[0].write_permission.value;
		var delete_permission = document.forms[0].delete_permission.value;
		var print_permission = document.forms[0].print_permission.value;
		var check_permission = document.forms[0].check_permission.value;
		var authorize_permission = document.forms[0].authorize_permission.value;
		var approve_permission = document.forms[0].approve_permission.value;
		var audit_permission = document.forms[0].audit_permission.value;//alert();
		window.opener.refrseh(Flag,vendor_cd,city,pan_no,country,pin,state_cd,write_permission,check_permission,authorize_permission,approve_permission,print_permission,audit_permission,delete_permission,year,month,supp_cd_hid,gstin_no);
		window.close();
	}
}



</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.Databean_Service_InvV2" id="serviceInv" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
String year=request.getParameter("year")==null?"":request.getParameter("year");
String month=request.getParameter("month")==null?"":request.getParameter("month");
String inv_type=request.getParameter("inv_type")==null?"":request.getParameter("inv_type");
String inv_type11=request.getParameter("inv_type11")==null?"":request.getParameter("inv_type11");
String flag=request.getParameter("flag")==null?"N":request.getParameter("flag");
String supp_cd_hid=request.getParameter("supp_cd_hid")==null?"":request.getParameter("supp_cd_hid");
String fin_yr_kk=request.getParameter("fin_yr_kk")==null?"":request.getParameter("fin_yr_kk");

String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");

util.init();
String sysdate = util.getGen_dt();
String curr_year = util.getGet_yr();

/* serviceInv.setFlag(flag);
serviceInv.setSupp_cd_hid(supp_cd_hid); */
// serviceInv.setYear(year);
// serviceInv.setMonth(month);
// serviceInv.setFin_yr(fin_yr_kk);
serviceInv.setCallFlag("transporter_list");
serviceInv.init();

Vector Vtrans_cd = serviceInv.getVtrans_cd();
Vector Vtrans_nm = serviceInv.getVtrans_nm();
/* Vector Vtrans_gst_no = serviceInv.getVtrans_gst_no();
Vector Vtrans_state = serviceInv.getVtrans_state();
Vector Vtrans_abbr = serviceInv.getVtrans_abbr();
Vector Vtrans_pin = serviceInv.getVtrans_pin();
Vector Vtrans_addr = serviceInv.getVtrans_addr(); */
%>

<input type="hidden" name="option" value="Submit_Other_Invoice">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="check_permission" value="<%=check_permission%>">
<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">

<body>
<div class="box mb-0">
<form name="product_list" method="post">
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr><td colspan="9" class="text-center"><b>Transporter List</b></td></tr>
				<tr class="info">
					<th class="text-center" rowspan="2">Select</th>
					<th class="text-center" rowspan="2">SN&nbsp;No</th>
					<th class="text-center" rowspan="2">SN Rev&nbsp;No</th>
					<th class="text-center" rowspan="2">FGSA&nbsp;No</th>
					<th class="text-center" rowspan="2">FGSA Rev&nbsp;No</th>
					<th class="text-center" rowspan="2">Start Dt</th>
					<th class="text-center" rowspan="2">End Dt</th>
					<th class="text-center" rowspan="2">TCQ<br>(MMBTU)</th>
					<th class="text-center" rowspan="2">Approved</th>				
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</form>
</div>
	
				
</body>
</html>