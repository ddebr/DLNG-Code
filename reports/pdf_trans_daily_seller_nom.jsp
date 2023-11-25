<%@ page import="java.util.*"%>
<%@ page errorPage="../../home/ExceptionHandler.jsp"%>
<%@ include file="../../sess/Expire.jsp"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<html>
<head>

<title>Daily Transporter Seller Nomination Details</title>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Seller_Report_Generation" id="contMgmt" scope="page"/>   
<%
	NumberFormat nf = new DecimalFormat("###########0.00");
	double convt_mmbtu_to_mt = (double) session.getAttribute("convt_mmbtu_to_mt");

	utilBean.init();
	String curr_dt = utilBean.getGen_dt();
	String next_dt = utilBean.getDate_tomorrow();
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");

	String gas_dt = request.getParameter("gas_date")==null?next_dt:request.getParameter("gas_date");
	String index = request.getParameter("index")==null?"":request.getParameter("index");
	String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
	String seq_no = request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
	String supp_seq_no = request.getParameter("supp_seq_no")==null?"":request.getParameter("supp_seq_no");
	System.out.println("request---"+request);

	contMgmt.setGas_date(gas_dt);
	contMgmt.setTrans_cd(trans_cd);
	contMgmt.setSeq_no(seq_no);
	contMgmt.setSupp_seq_no(supp_seq_no);
	contMgmt.setConvt_mmbtu_to_mt(convt_mmbtu_to_mt);
	contMgmt.setPrint_pdf("Y");
	contMgmt.setRequest(request);
	
	contMgmt.setCallFlag("PREVIEW_DAILY_SELLER_NOM_TRANSPORTER");
	contMgmt.init();
	
	String contPath = request.getContextPath()== null?"":""+request.getContextPath();
	System.out.println("contPath = "+contPath);
	if(contPath.trim().length()>1)
	{
		contPath = contPath.trim().substring(1);
	}
	else
	{
		contPath = contPath.trim();
	}
	
	String pdfpath = contMgmt.getNom_trans_pdf_path().trim();
	String url_start = contMgmt.getUrl_start().trim();
	System.out.println("url_start = "+url_start);
	if(contPath==null || contPath.equals("") || pdfpath==null || pdfpath.equals(""))
	{
		
	}
	else
	{
		pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
	 	System.out.println("inner pdfPath = "+pdfpath);
	 	pdfpath = url_start+"/pdf_reports/daily_nomination/pdf_files"+pdfpath;
	}
	System.out.println("PDF Path = "+pdfpath+", contPath= "+contPath);
		    
	//System.out.println("TRANSPORTER_CD"+TRANSPORTER_CD);
	//System.out.println("TRANS_ABBR"+TRANS_ABBR);
	//System.out.println("QTY_MMBTU"+QTY_MMBTU);
%>
<body bgcolor="white">
<iframe src="<%=pdfpath%>" width="100%" height="100%">
</iframe>	
