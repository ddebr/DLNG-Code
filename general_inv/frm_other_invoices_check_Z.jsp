<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*,java.io.*,java.text.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css">
<link href="../css/utilities.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Invoice </title>

<script>

function click_rd(flag)
{
	var a = confirm("Do You Want To Submit Data ?");
		if(a)
		{
		
			document.forms[0].submit();
		}
}
function Do_close(suppCd,year,month,inv_type,msg,write_perm,check_perm,authoerize_perm,approve_perm,print_perm,audit_perm,delete_perm)
{
	//window.opener.refersh(year,month,inv_type,msg,write_perm,check_perm,authoerize_perm,approve_perm,print_perm,audit_perm,delete_perm);
	//window.close();
}


</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Other_InvoiceV2" id="dta" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String year=request.getParameter("year")==null?"":request.getParameter("year");
String month=request.getParameter("month")==null?"":request.getParameter("month");
String inv_type=request.getParameter("inv_type")==null?"":request.getParameter("inv_type");
String state=request.getParameter("state")==null?"":request.getParameter("state");
String sac_desc=request.getParameter("sac_desc")==null?"":request.getParameter("sac_desc");
String sac_cd=request.getParameter("sac_cd")==null?"":request.getParameter("sac_cd");
String address=request.getParameter("address")==null?"":request.getParameter("address");
String city=request.getParameter("city")==null?"":request.getParameter("city");
String pin_code=request.getParameter("pin_code")==null?"":request.getParameter("pin_code");
String inv_dt=request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
String t2=request.getParameter("t2")==null?"":request.getParameter("t2");
String net_amt_inr=request.getParameter("net_amt_inr")==null?"":request.getParameter("net_amt_inr");
String gross_amt=request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
String supplier_cd=request.getParameter("supplier_cd")==null?"1":request.getParameter("supplier_cd");

String flag=request.getParameter("flag")==null?"INSERT":request.getParameter("flag");
String fin_yr=request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
String seq_no=request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
String hlpl_seq_no=request.getParameter("hlpl_seq_no")==null?"":request.getParameter("hlpl_seq_no");
String pdf_flag=request.getParameter("pdf_flag")==null?"":request.getParameter("pdf_flag");
String TemplateType1=request.getParameter("Template_Type")==null?"0":request.getParameter("Template_Type"); //SB20171107
String cust_cd=request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd"); //SB20171104
String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");

String form_cd = request.getParameter("form_cd")==null?"":request.getParameter("form_cd");
String form_name = request.getParameter("form_name")==null?"":request.getParameter("form_name");


String Contact_Suppl_PAN_NO=request.getParameter("Contact_Suppl_PAN_NO")==null?"":request.getParameter("Contact_Suppl_PAN_NO");
String Contact_Suppl_gstin=request.getParameter("Contact_Suppl_gstin")==null?"":request.getParameter("Contact_Suppl_gstin");
HttpServletRequest req = request;
String pdfpath="";
String url_start="";
Vector vcargo_amt=new Vector();
Vector vcrate=new Vector();
Vector vcqty=new Vector();
Vector vitm=new Vector();

// String tax_str="18";
// String tax_str1="9";
String tax_amt_inr="";Vector Vtax_amt_inr=new Vector();

util.init();
String sysdate = util.getGen_dt();
String curr_year = util.getGet_yr();
//dta.setInv_type(inv_type);
dta.setTemplate_Type(TemplateType1);
dta.setMonth(month);
dta.setYear(year);
dta.setCust_sts_cd(state);
dta.setFlag_act(flag);
if(flag.equalsIgnoreCase("Modify")|| flag.equalsIgnoreCase("C") || flag.equalsIgnoreCase("A") || flag.equalsIgnoreCase("P")) {
	dta.setVFin_yr(fin_yr);
	dta.setHlpl_seq_no(hlpl_seq_no);
	dta.setSeq_no(seq_no);
	dta.setInv_type(inv_type);
	dta.setPdf_flag(pdf_flag);
	dta.setRequest(req);
}
System.out.println("supplier_cd: "+supplier_cd);
dta.setCust_Cd(cust_cd);
dta.setSupp_cd_set(supplier_cd);
dta.setCallFlag("CheckApprove_INVOICE_DTL");
dta.init();
 Contact_Suppl_PAN_NO=dta.getContact_Suppl_PAN_NO();
 Contact_Suppl_gstin=dta.getContact_Suppl_gstin_no();

Vector tax_name=dta.getTax_nm();

String cust_nm="";String gate_no="";


	inv_dt=dta.getSinv_dt();
//	hsn_cd=dta.getHasn_cd();
	sac_cd=dta.getSac_cd();
	t2=dta.getCust_nm();
	address=dta.getCust_addr();
	city=dta.getCust_city();
	pin_code=dta.getCust_pin();
 	
	state=dta.getCust_state_cd1();
	gate_no=dta.getGate_no();

	 gross_amt=dta.getSgross_amt_inr_chk();
		 tax_amt_inr=dta.getTax_amt_inr_chk();
		 net_amt_inr=dta.getSnet_amt_inr_chk();
		  Vtax_amt_inr=dta.getTtax_amt_inr_chk();
	   sac_desc=dta.getSac_descr();
	   
	    vcargo_amt=dta.getVCamt();
	    vcrate=dta.getVCrate();
	    vcqty=dta.getVCqty();
	    vitm=dta.getVitm();
	    
	    String supp_addr=dta.getSupp_addr_nm_R();
		String suppcity=dta.getSupp_addr_city_R();
		String Pin_R=dta.getPin_R();
		String phone_R=dta.getPhone_R();
		String Fax_R=dta.getFax_R();
		String country=dta.getCntry_R();
		String supp_nm=dta.getSupplier_nm();
		Vector  tt1_rate=dta.getTt1_rate();
		String result=dta.getResult();
		Vector camt=dta.getCamt();
		Vector crt=dta.getCrt();
		String state_nm1=dta.getState();
		String supp_satte_cd=dta.getSupp_stat_CD();
		String rule=dta.getRule_rmk();
		String cust_sts_nm=dta.getState_nm1();
		String cust_pan_no=dta.getsCust_pan_no();
		String Cust_gstin_no11=dta.getCust_gstin_no11();
		String flag_sac=dta.getFlag_sac();
		Vector Vhsn_cd=dta.getVvsac_cd();
		String tax_amt_chk=dta.getTax_amt_inr_chk();
		String sale_no=dta.getSale_no();
		Vector vuam_no=dta.getVuam_no();
// 		System.out.println("--rule--"+rule);
	  
Vector VCGST_Perc = dta.getVCGST_Perc();
Vector VSGST_Perc = dta.getVSGST_Perc();
Vector VIGST_Perc = dta.getVIGST_Perc();
Vector VCESS_Perc = dta.getVCESS_Perc();
Vector VIGST_Amt = dta.getVIGST_Amt();
Vector VCGST_Amt = dta.getVCGST_Amt();
Vector VSGST_Amt = dta.getVSGST_Amt();
Vector VCESS_Amt = dta.getVCESS_Amt();
Vector VSAC_CD = dta.getVSAC_CD();
Vector VHSN_CD = dta.getVHSN_CD();	    
Vector Vdesc = dta.getVdesc();
Vector Vsac_cd = dta.getVsac_cd();


 
	if(flag.equalsIgnoreCase("P")) {
	pdfpath = dta.getPdf_path().trim();
	url_start = dta.getUrl_start().trim();
	
	pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
	pdfpath = url_start+"/pdf_reports/other_invoices/pdf_files"+pdfpath;
	}
	
	  String supp_cd = dta.getSupplier_cd();

System.out.println("inv_dt--"+inv_dt);
NumberFormat nf3 = new DecimalFormat("###,###,###,##0.00");
//NumberFormat nf1=new DecimalFormat("#########.##");


		
%>

<body  <%if(msg.length()>6) { %>onload="Do_close('<%=supp_cd%>','<%=year%>','<%=month%>','<%=inv_type%>','<%=msg%>','<%=write_permission%>','<%=check_permission%>','<%=authorize_permission%>','<%=approve_permission%>','<%=print_permission%>','<%=audit_permission%>','<%=delete_permission%>');"<%}else if(pdfpath.length()>3) { %>onload="refreshopener();"<%} %>>
<%if(print_permission.trim().equalsIgnoreCase("N")){%>
<script language="javascript" src="../js/mouseclk.js"></script>
<%}%>

<script>
function refreshopener()
{
	window.opener.location.reload();
		window.close();
}
</script>
<form name="rpt_invoice_dtl" method="post" action="../servlet/Frm_General_InvoiceV2">

<table width="98%"  align="center" >
	<tr>
	<td width="55%" align="center">
		<font size="5px" face="Arial"><b><%=supp_nm %></b>
 	</td>
 	</tr> 
<%--  	<tr align="center"><td align="center" colspan="2"><div align="center"><font size="4" face="Arial"><b><%=pdf_print %></b></font></div></td></tr> --%>
	<tr align="center"><td align="center" colspan="2"><div align="center"><font size="4" face="Arial"><b>TAX INVOICE</b></font><br><font size="1.5px" face="Arial"> <%=rule %></font></div></td></tr>
</table>
<table width="98%"  align="center">
	<tr>	
	<td align="left" width="45%"> 
		<table width="100%" cellpadding="0" cellspacing="0" align="center" >
			<tr align="left"><td align="left" ><font size="1.5px" face="Arial">Registered Office</font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial"><b><%=supp_nm%></b></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial"><%=supp_addr %></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial"><%=suppcity %>-&nbsp;<%=Pin_R %></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial">&nbsp;</font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial">State : <%=state_nm1 %></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial">State Code: <%=supp_satte_cd %></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial">GSTIN : <%=Contact_Suppl_gstin %></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial">PAN : <%=Contact_Suppl_PAN_NO %></font></td></tr>
		<tr><td align="left"><font size="1.5px" face="Arial">Place of Supply : <%=cust_sts_nm %></font></td></tr>
		</table>
	</td>
	<td width="10%" >&nbsp;</td>	
	<td width="45%" style="vertical-align: top;">
		<table width="100%" cellpadding="0" cellspacing="0" align="left" >
			<tr align="left"><td align="left" ><font size="1.5px" face="Arial">To : </font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial"><b><%=t2%></b></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial"><%=address %></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial"><%=city %><%if(!pin_code.equalsIgnoreCase("")) { %>-&nbsp;<%=pin_code %><%} %></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial">&nbsp;</font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial">State : <%=cust_sts_nm %></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial">State Code : <%=state %></font></td></tr>
			<%if(!Cust_gstin_no11.equalsIgnoreCase("")) { %>
			<tr><td align="left"><font size="1.5px" face="Arial">GSTIN : <%=Cust_gstin_no11 %></font></td></tr>
			<%} %>
			<%if(!cust_pan_no.equalsIgnoreCase("")) { %>
			<tr><td align="left"><font size="1.5px" face="Arial">PAN : <%=cust_pan_no %></font></td></tr>
			<%} %>
			<tr><td align="left"><font size="1.5px" face="Arial"></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial"></font></td></tr>
			<tr><td align="left"><font size="1.5px" face="Arial"></font></td></tr>
		</table>
	</td>	
	</tr>
</table>
<table width="98%" cellpadding="0" cellspacing="0"  align="left">

</table>
<BR><BR>
<table width="98%" cellpadding="0" cellspacing="0"  align="right">
<TR>
	<TD width="100%">
		<table width="100%" cellpadding="0" cellspacing="0" align="right">
			<tr>
			<td width="45%">
				<table width="80%" cellpadding="0" cellspacing="0" border="0" align="left">
				<tr>
					<td><font size="1.5px" face="Arial"><b>&nbsp;Order No</b></font></td>
					<td  align="right"><font size="1.5px" face="Arial"><b>&nbsp;<%=sale_no %>&nbsp;</b></font></td>
				</tr>
				<tr>
					<td  align="left"><font size="1.5px" face="Arial"><b>&nbsp;Order Date</b></font></td>
					<td  align="right"><font size="1.5px" face="Arial"><b>&nbsp;<%=gate_no %>&nbsp;</b></font></td>
				</tr>
				</table>
			</td>
			<td width="10%">&nbsp;</td>
			<td width="45%" align="center">
				<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
					<tr>
						<td  align="right"><font size="1.5px" face="Arial"><b>&nbsp;Invoice No</b></font></td>
						<td align="right"><font size="1.5px" face="Arial"><b>&nbsp;<%=seq_no %></b></font></td>
					</tr>
					<tr>
						<td  align="right"><font size="1.5px" face="Arial"><b>&nbsp;Invoice Date</b></font></td>
						<td  align="right"><font size="1.5px" face="Arial"><b>&nbsp;<%=inv_dt %></b></font></td>
					</tr>
				</table>
			</td>
			</tr>
		</table>
</TD>
</TR>
</table>
<BR><BR><BR><BR>
<table width="98%"  border="1" align="center" cellpadding="2" cellspacing="0">
<tr >
	<td width="5%" colspan="1"><div align="center"><font size="1.5px" face="Arial"><b>SR No.</b></font></div></td>
	<%if(flag_sac.equalsIgnoreCase("P")) { %>		
		<td width="5%" colspan="1"><div align="center"><font size="1.5px" face="Arial"><b>HSN Code</b></font></div></td>
	<%}else{ %>
		<td width="5%" colspan="1"><div align="center"><font size="1.5px" face="Arial"><b>SAC Code</b></font></div></td>
	<%} %>
	<td width="34%" colspan="1"><div align="center"><font size="1.5px" face="Arial"><b>Particulars.</b></font></div></td>
	<td width="10%" colspan="1"><div align="center"><font size="1.5px" face="Arial"><b>UOM</b></font></div></td>
	<td width="10%" colspan="1"><div align="center"><font size="1.5px" face="Arial"><b>Quantity</b></font></div></td>
	<td width="10%" colspan="1"><div align="center"><font size="1.5px" face="Arial"><b>Rate Per Qty</b></font></div></td>
	<td width="10%" colspan="1"><div align="center"><font size="1.5px" face="Arial"><b>Amount (Rs.)</b></font></div></td>
</tr>

<%for(int k=0;k<vcqty.size();k++) { %>
	<tr><td align="center">
		<font size="1.5px" face="Arial"><b><%=k+1 %></b></font>
	</td>
	<td align="center"><font size="1.5px" face="Arial"><b><%=Vhsn_cd.elementAt(k)%></b></font></td>
	<td align="left"><font size="1.5px" face="Arial"><b><%=vitm.elementAt(k)%></b></font></td>
	<td align="center"><font size="1.5px" face="Arial">&nbsp;<b><%=vuam_no.elementAt(k)%></b></font></td>
	<td align="right"><font size="1.5px" face="Arial"><b><%=vcqty.elementAt(k)%></b></font></td>
	<td align="right"><font size="1.5px" face="Arial"><b><%=crt.elementAt(k)%></b></font></td>
	<td align="right"><font size="1.5px" face="Arial"><b><%=camt.elementAt(k)%></b></font></td>
	</tr>
	
<%} %>
<tr><td align="center">
		<font size="1.5px" face="Arial"><b>&nbsp;<%//=k+1 %></b></font>
	</td>
	<td align="left"><font size="1.5px" face="Arial"><b>&nbsp;<%//=Vhsn_cd.elementAt(k)%></b></font></td>
	<td align="left"><font size="1.5px" face="Arial"><b>Gross Amount</b></font></td>
	<td align="left"><font size="1.5px" face="Arial"><b>&nbsp;<%//=vitm.elementAt(k)%></b></font></td>
	<td align="right"><font size="1.5px" face="Arial"><b>&nbsp;<%//=vcqty.elementAt(k)%></b></font></td>
	<td align="right"><font size="1.5px" face="Arial"><b>&nbsp;<%//=crt.elementAt(k)%></b></font></td>
	<td align="right"><font size="1.5px" face="Arial"><b><%=gross_amt%></b></font></td>
	</tr>
	

	<%for(int k=0;k<tax_name.size();k++) { %>	
	
		<tr>
		<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
		
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td align="left" colspan="1"><font size="1.5px" face="Arial"><b><%=tax_name.elementAt(k) %>&nbsp;</b></font></td>
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td colspan="1" align="right"><font size="1.5px" face="Arial"><b><%=Vtax_amt_inr.elementAt(k) %></b></font></td>
	</tr>
<%} %>
<tr>
		<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
		
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td align="left" colspan="1"><font size="1.5px" face="Arial"><b>Total GST</b></font></td>
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td colspan="1" align="right"><font size="1.5px" face="Arial"><b><%=tax_amt_chk %></b></font></td>
	</tr>
	
	<tr> 
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td colspan="1" align="left"><font size="1.5px" face="Arial"><b>Total Invoice Amount in INR&nbsp;</b></font></td>
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td align="left"><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td>
	<td align="right"><font size="1.5px" face="Arial"><b><%=net_amt_inr %></b></font></td>
	</tr>
</table>
<BR>	

	<table width="98%" cellpadding="0" cellspacing="0" border="1" align="center">
	<tr><td><font size="1.5px" face="Arial"><b>Amount in Words &nbsp;&nbsp;| &nbsp;Rupees&nbsp;<%=result %>&nbsp;Only</b></font></td>
	</tr></table>
		<BR>
<table width="98%" cellpadding="0" cellspacing="0" border="0" align="center">
	<tr>
		<%for(int k=0;k<Vsac_cd.size();k++) { %>
		<td  align="left">
		<font size="1.5px" face="Arial"><%if(flag_sac.equalsIgnoreCase("P")) { %><b>HSN:</b><%}else{ %><b>SAC:</b><%} %><b>&nbsp;<%=Vsac_cd.elementAt(k) %></b></font>&nbsp;<font size="1.5px" face="Arial"><b>&nbsp;<%=Vdesc.elementAt(k) %></b></font><BR>
		</td>
		<%} %>
	</tr>
</table>
	<BR><BR><BR>
	<table width="98%">
			<tr><td align="right"><font size="1.5px" face="Arial"><b></b></font></td></tr>
			<tr><td align="right"><font size="1.5px" face="Arial"><b></b></font></td></tr>
			<tr><td align="right" style="padding-left: 7px;"><font size="1.5px" face="Arial"><b>For <%=supp_nm %></b></font></td></tr>
			<tr><td align="right"><font size="1.5px" face="Arial"><b></b></font></td></tr>
			<tr><td align="right"><font size="1.5px" face="Arial"><b></b></font></td></tr>
			<tr><td align="right"><font size="1.5px" face="Arial"><b></b></font></td></tr>
			<tr><td align="right"><font size="1.5px" face="Arial"><b></b></font></td></tr>
			<tr><td align="right"><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td></tr>
			<tr><td align="right"><font size="1.5px" face="Arial"><b>&nbsp;</b></font></td></tr>
			<tr><td align="right" style="padding-left: 7px;"><font size="1.5px" face="Arial"><b>Authorised Signatory</b></font></td></tr>
	</table>

<table width="98%" align="center"><tr><td>&nbsp;</td></tr></table>
<table width="98%" align="center">
				<tr><td>&nbsp;</td></tr>
					<tr><td>&nbsp;</td></tr>
					
		<tr align="center">
	<td align="center">	
	<%if(flag.equalsIgnoreCase("C")) { %><font size="2px" face="Arial"><b>Invoice Check </b>:</font><%}else if(flag.equalsIgnoreCase("A")) { %><font size="2px" face="Arial"><b>Invoice	Approval </b>:</font><%} %> &nbsp;<input type="radio" onclick="click_rd('Y');"  name="rd" value="Y"><font size="3px" face="Arial"><b>Yes</b></font> &nbsp;&nbsp;
	<input type="radio" name="rd" value="N" onclick="click_rd('N');"><font size="3px" face="Arial"><b>No</b></font> 
	</td></tr>
</table>
	
	

<input type="hidden" name="option" value="Submit_Check_Invoice">
<input type="hidden" name="seq_no" value="<%=seq_no%>">
<input type="hidden" name="user_cd_hid" value="<%=emp_cd%>">
<input type="hidden" name="hlpl_seq_no" value="<%=hlpl_seq_no%>">
<input type="hidden" name="flag" value="<%=flag%>">
<input type="hidden" name="year_hid" value="<%=year%>">
<input type="hidden" name="month_hid" value="<%=month%>">
<input type="hidden" name="Fin_yr_hid" value="<%=fin_yr%>">
<input type="hidden" name="inv_type_hid" value="<%=inv_type%>">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="check_permission" value="<%=check_permission%>">
<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
<input type="hidden" name="form_id" value="<%=form_cd %>">
<input type="hidden" name="form_nm" value="<%=form_name %>">
<input type="hidden" name="supp_cd" value="<%=supp_cd%>">
</form>
</body>
</html>