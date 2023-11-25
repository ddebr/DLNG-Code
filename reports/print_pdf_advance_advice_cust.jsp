<%@ page import="java.util.*"%>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Advance_Advice" id="daa" scope="page"/> 


<%

java.text.NumberFormat nf7 = new java.text.DecimalFormat("###,###,###,##0.00");
java.text.NumberFormat nf5 = new java.text.DecimalFormat("##########0");
java.text.NumberFormat nf = new java.text.DecimalFormat("###########0.00");

utilBean.init();
String current_date = utilBean.getGen_dt();
String time_gen = utilBean.getTime_gen();

String from_dt = request.getParameter("from_date")==null?current_date:request.getParameter("from_date");
// String to_dt = request.getParameter("to_date")==null?current_date:request.getParameter("to_date");
String mapp_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
String plant_no = request.getParameter("plant_no")==null?"":request.getParameter("plant_no");
double last_inv_qty = Double.parseDouble(request.getParameter("last_inv_qty")==null?"0":request.getParameter("last_inv_qty"));

String print_pdf_flg = "Y";

daa.setCallFlag("Advance_Advice_Rpt");
daa.setFrom_dt(from_dt);
// daa.setTo_dt(to_dt);
HttpServletRequest req = request;	
daa.setRequest(req);
daa.setSelMapId(mapp_id);
daa.setPlant_no(plant_no);
daa.setSysdate(from_dt);
daa.setPrint_pdf_flg(print_pdf_flg);
daa.setTime_gen(time_gen);
daa.setLast_inv_qty(last_inv_qty);

daa.init();
String pdfpath="";
String url_start ="";

pdfpath = daa.getPdf_path().trim();
url_start = daa.getUrl_start().trim();

String contPath = request.getContextPath()== null?"":""+request.getContextPath();
if(contPath.trim().length()>1)
{
	contPath = contPath.trim().substring(1);
}
else
{
	contPath = contPath.trim();
}

if(contPath==null || contPath.equals("") || pdfpath==null || pdfpath.equals(""))
{
	
}
else
{
	pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
 	System.out.println("inner pdfPath = "+pdfpath);
 	pdfpath = pdfpath.substring(1);	
 	pdfpath = url_start+"/pdf_reports/advance_advice/pdf_files/"+pdfpath;
}
// System.out.println("pdfpath : "+pdfpath);

%>
<body bgcolor="white" onload="refreshopener();window.open('<%=pdfpath%>');">
</body>
<script>
function refreshopener()
{
	window.close();
}
</script>
