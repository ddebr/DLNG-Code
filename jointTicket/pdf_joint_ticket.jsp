<%@ page import="java.util.*,java.text.*"%>
<%@ page errorPage="../../home/ExceptionHandler.jsp"%>
<%@ include file="../../sess/Expire.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title><%=request.getParameter("plant_nm")+"  "%> : Joint Ticket PDF Report</title>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_mgmt.DataBean_Contract_Mgmt" id="dbContMgmt" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_Pdf_Generation" id="pdfFile" scope="request"/>
<%
	utilBean.init();
	String current_date = utilBean.getGen_dt();
	String tomorrow_date = utilBean.getDate_tomorrow();
	String yesterday_date = utilBean.getYest_dt();
	
	NumberFormat nf = new DecimalFormat("###########0.00"); 
	
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
	String tempcompnm = (String)session.getAttribute("tempcompnm")==null?"":(String)session.getAttribute("tempcompnm"); //RG20190326
			 
	String gas_date = request.getParameter("gas_date")==null?yesterday_date:request.getParameter("gas_date");
	String gen_date = request.getParameter("gen_date")==null?current_date:request.getParameter("gen_date");
	
	String seller_nom_qty = request.getParameter("seller_nom_qty")==null?"":request.getParameter("seller_nom_qty");
	String qty_mmbtu = request.getParameter("qty_mmbtu")==null?"":request.getParameter("qty_mmbtu");
	String qty_scm = request.getParameter("qty_scm")==null?"":request.getParameter("qty_scm");
	String customer_cd = request.getParameter("customer_cd")==null?"0":request.getParameter("customer_cd");
	String customer_nm = request.getParameter("customer_nm")==null?"":request.getParameter("customer_nm");
	String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
	String customer_contact_cd = request.getParameter("customer_contact_cd")==null?"0":request.getParameter("customer_contact_cd");
	String plant_cd = request.getParameter("plant_cd")==null?"0":request.getParameter("plant_cd");
	String plant_nm = request.getParameter("plant_nm")==null?"":request.getParameter("plant_nm");
	
	String gcv = request.getParameter("gcv")==null?"":request.getParameter("gcv");
	String ncv = request.getParameter("ncv")==null?"":request.getParameter("ncv");
	String transporter_cd = request.getParameter("transporter_cd")==null?"":request.getParameter("transporter_cd");
	String transporter_nm = request.getParameter("transporter_nm")==null?"":request.getParameter("transporter_nm");
	String transporter_abbr = request.getParameter("transporter_abbr")==null?"":request.getParameter("transporter_abbr");
	String transporter_qty = request.getParameter("transporter_qty")==null?"":request.getParameter("transporter_qty");
	
	String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
	String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
	String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
	String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
	String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
	String sn_qty = request.getParameter("sn_qty")==null?"":request.getParameter("sn_qty");
	String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
	String sn_offspec_qty = request.getParameter("sn_offspec_qty")==null?"":request.getParameter("sn_offspec_qty");//JHP
	String sn_offspec_flag = request.getParameter("sn_offspec_flag")==null?"":request.getParameter("sn_offspec_flag");//JHP
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
	
	String[] splitdt=gas_date.split("/");
	
	int dt=20150201;
	int d=Integer.parseInt(""+splitdt[2]+splitdt[1]+splitdt[0]);
	if(d>=dt)
	{
	dbContMgmt.setCallFlag("JOINT_TICKET_REPORT");
	dbContMgmt.setGas_date(gas_date);
	dbContMgmt.setCustomer_cd(customer_cd);
	dbContMgmt.setCustomer_contact_cd(customer_contact_cd);
	dbContMgmt.setPlant_seq_no(plant_cd);
	dbContMgmt.setContract_type(contract_type);
	dbContMgmt.init();
	
	Vector daily_JT_Transporter_Cd=dbContMgmt.getDaily_JT_Transporter_Cd();
	Vector daily_JT_Transporter_Nm=dbContMgmt.getDaily_JT_Transporter_Nm();
	Vector daily_JT_Transporter_Abbr=dbContMgmt.getDaily_JT_Transporter_Abbr();
	Vector daily_JT_Transporter_Qty_Mmbtu=dbContMgmt.getDaily_JT_Transporter_Qty_Mmbtu();
	Vector daily_JT_Ncv=dbContMgmt.getDaily_JT_Ncv();
	Vector daily_JT_Gcv=dbContMgmt.getDaily_JT_Gcv();
	Vector daily_JT_Seller_Nom_Qty_Mmbtu=dbContMgmt.getDaily_JT_Seller_Nom_Qty_Mmbtu();
	Vector daily_JT_Qty_Scm=dbContMgmt.getDaily_JT_Qty_Scm();
	Vector daily_JT_Qty_Mmbtu=dbContMgmt.getDaily_JT_Qty_Mmbtu();
	Vector daily_JT_Mapping_Id=dbContMgmt.getDaily_JT_Mapping_Id();
	Vector daily_JT_Fgsa_No = dbContMgmt.getDaily_JT_Fgsa_No();
	Vector daily_JT_Fgsa_Rev_No = dbContMgmt.getDaily_JT_Fgsa_Rev_No();
	Vector daily_JT_Sn_No = dbContMgmt.getDaily_JT_Sn_No();
	Vector daily_JT_Sn_Rev_No = dbContMgmt.getDaily_JT_Sn_Rev_No();
	Vector daily_JT_SN_Qty_Mmbtu = dbContMgmt.getDaily_JT_SN_Qty_Mmbtu();
	Vector daily_JT_SN_Ref_No = dbContMgmt.getDaily_JT_SN_Ref_No(); //Introduced By Samik Shah On 19th April, 2011 ...
	Vector daily_JT_offspec_qty=dbContMgmt.getDaily_JT_offspec_qty();//Introduced By Jaimin Patel On 27th Sep, 2011 ...
	Vector daily_JT_offspec_flag=dbContMgmt.getDaily_JT_offspec_flag();//Introduced By Jaimin Patel On 27th Sep, 2011 ...
	Vector daily_JT_Sn_Signing_Dt=dbContMgmt.getDaily_JT_Sn_Signing_Dt();
	
	Vector daily_JT_Qty_MT = new Vector();
	for(int i=0;i<daily_JT_Qty_Mmbtu.size();i++){
			daily_JT_Qty_MT.add(Double.parseDouble(daily_JT_Qty_Mmbtu.elementAt(i).toString()) / convt_mmbtu_to_mt);
	}
	
	//Following String Getter Methods Are Defined By Samik Shah For Joint Ticket Format Preperation On 21st June, 2010 ...
	String contact_Person_Name = dbContMgmt.getContact_Person_Name();
	String contact_Person_Desg = dbContMgmt.getContact_Person_Desg();
	String contact_Customer_Name = dbContMgmt.getContact_Customer_Name();
	String contact_Customer_Person_Address = dbContMgmt.getContact_Customer_Person_Address();
	String contact_Customer_Person_City = dbContMgmt.getContact_Customer_Person_City();
	String contact_Customer_Person_Pin = dbContMgmt.getContact_Customer_Person_Pin();
	String contact_Customer_Person_Phone = dbContMgmt.getContact_Customer_Person_Phone();
	String contact_Customer_Person_Fax = dbContMgmt.getContact_Customer_Person_Fax();
	String contact_Customer_Person_State = dbContMgmt.getContact_Customer_Person_State();
	String contact_Customer_Person_Country = dbContMgmt.getContact_Customer_Person_Country();
	String contact_Suppl_Name = dbContMgmt.getContact_Suppl_Name();
	String contact_Suppl_Person_Address = dbContMgmt.getContact_Suppl_Person_Address();
	String contact_Suppl_Person_City = dbContMgmt.getContact_Suppl_Person_City();
	String contact_Suppl_Person_Pin = dbContMgmt.getContact_Suppl_Person_Pin();
	String contact_Suppl_Person_Phone = dbContMgmt.getContact_Suppl_Person_Phone();
	String contact_Suppl_Person_Fax = dbContMgmt.getContact_Suppl_Person_Fax();
	String contact_Suppl_Person_State = dbContMgmt.getContact_Suppl_Person_State();
	String contact_Suppl_Person_Country = dbContMgmt.getContact_Suppl_Person_Country();

	//Following All Setter Methods and Variables For PDF File Generation ... Defined By Samik Shah On 21st September, 2010 ...
	HttpServletRequest req = request;
	
	pdfFile.setGas_date(gas_date);
	pdfFile.setGen_date(gen_date);

	pdfFile.setSeller_nom_qty(seller_nom_qty);
	pdfFile.setQty_mmbtu(qty_mmbtu);
	pdfFile.setQty_scm(qty_scm);
	pdfFile.setCustomer_cd(customer_cd);
	pdfFile.setCustomer_nm(customer_nm);
	pdfFile.setCustomer_abbr(customer_abbr);
	pdfFile.setCustomer_contact_cd(customer_contact_cd);
	pdfFile.setPlant_cd(plant_cd);
	pdfFile.setPlant_nm(plant_nm);

	pdfFile.setDaily_JT_Gcv(daily_JT_Gcv);
	pdfFile.setDaily_JT_Ncv(daily_JT_Ncv);

	pdfFile.setPrint_permission(print_permission);

	pdfFile.setDaily_JT_Transporter_Cd(daily_JT_Transporter_Cd);
	pdfFile.setDaily_JT_Transporter_Nm(daily_JT_Transporter_Nm);
	pdfFile.setDaily_JT_Transporter_Abbr(daily_JT_Transporter_Abbr);
	pdfFile.setDaily_JT_Transporter_Qty_Mmbtu(daily_JT_Transporter_Qty_Mmbtu);
	pdfFile.setDaily_JT_Seller_Nom_Qty_Mmbtu(daily_JT_Seller_Nom_Qty_Mmbtu);
	pdfFile.setDaily_JT_Qty_Mmbtu(daily_JT_Qty_Mmbtu);
	pdfFile.setDaily_JT_Qty_Scm(daily_JT_Qty_Scm);
	pdfFile.setDaily_JT_Qty_MT(daily_JT_Qty_MT);
	pdfFile.setDaily_JT_Mapping_Id(daily_JT_Mapping_Id);

	pdfFile.setDaily_JT_Fgsa_No(daily_JT_Fgsa_No);
	pdfFile.setDaily_JT_Sn_No(daily_JT_Sn_No);
	pdfFile.setDaily_JT_SN_Ref_No(daily_JT_SN_Ref_No);
	pdfFile.setDaily_JT_Sn_Rev_No(daily_JT_Sn_Rev_No);
	pdfFile.setDaily_JT_SN_Qty_Mmbtu(daily_JT_SN_Qty_Mmbtu);
	pdfFile.setContract_type(contract_type);
	pdfFile.setDaily_JT_offspec_flag(daily_JT_offspec_flag);
	pdfFile.setDaily_JT_offspec_qty(daily_JT_offspec_qty);
	pdfFile.setDaily_JT_Sn_Signing_Dt(daily_JT_Sn_Signing_Dt);
	
	pdfFile.setContact_Person_Name(contact_Person_Name);
	pdfFile.setContact_Person_Desg(contact_Person_Desg);
	pdfFile.setContact_Customer_Name(contact_Customer_Name);
	pdfFile.setContact_Customer_Person_Address(contact_Customer_Person_Address);
	pdfFile.setContact_Customer_Person_City(contact_Customer_Person_City);
	pdfFile.setContact_Customer_Person_Country(contact_Customer_Person_Country);
	pdfFile.setContact_Customer_Person_Fax(contact_Customer_Person_Fax);
	pdfFile.setContact_Customer_Person_Phone(contact_Customer_Person_Phone);
	pdfFile.setContact_Customer_Person_Pin(contact_Customer_Person_Pin);
	pdfFile.setContact_Customer_Person_State(contact_Customer_Person_State);
	pdfFile.setContact_Suppl_Name(contact_Suppl_Name);
	pdfFile.setContact_Suppl_Person_Address(contact_Suppl_Person_Address);
	pdfFile.setContact_Suppl_Person_City(contact_Suppl_Person_City);
	pdfFile.setContact_Suppl_Person_Country(contact_Suppl_Person_Country);
	pdfFile.setContact_Suppl_Person_Fax(contact_Suppl_Person_Fax);
	pdfFile.setContact_Suppl_Person_Phone(contact_Suppl_Person_Phone);
	pdfFile.setContact_Suppl_Person_Pin(contact_Suppl_Person_Pin);
	pdfFile.setContact_Suppl_Person_State(contact_Suppl_Person_State);	
	pdfFile.setTempcompname(tempcompnm);
	pdfFile.setRequest(req);
	pdfFile.setCall_flag("Pdf_For_JT");
	pdfFile.init();
	
	String contPath = request.getContextPath()== null?"":""+request.getContextPath();
	if(contPath.trim().length()>1)
	{
		contPath = contPath.trim().substring(1);
	}
	else
	{
		contPath = contPath.trim();
	}
	
	String pdfpath = pdfFile.getJt_pdf_path().trim();
	String url_start = pdfFile.getUrl_start().trim();
	
	if(contPath==null || contPath.equals("") || pdfpath==null || pdfpath.equals(""))
	{
		
	}
	else
	{
		pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
		pdfpath = pdfpath.substring(1);	
	 	pdfpath = url_start+"/pdf_reports/joint_tickets/pdf_files"+pdfpath;
	}
%>

<body bgcolor="white">
<iframe src="<%=pdfpath%>" width="100%" height="100%">
</iframe>
</body>
<%}else{ 
	dbContMgmt.setCallFlag("JOINT_TICKET_REPORT");
	dbContMgmt.setGas_date(gas_date);
	dbContMgmt.setCustomer_cd(customer_cd);
	dbContMgmt.setCustomer_contact_cd(customer_contact_cd);
	dbContMgmt.setPlant_seq_no(plant_cd);
	dbContMgmt.init();
	
	String [] gcv_arr = gcv.split("~~");
	String [] ncv_arr = ncv.split("~~");
	String [] transporter_cd_arr = transporter_cd.split("~~");
	String [] transporter_nm_arr = transporter_nm.split("~~");
	String [] transporter_abbr_arr = transporter_abbr.split("~~");
	String [] transporter_qty_arr = transporter_qty.split("~~");
	
	String [] fgsa_no_arr = fgsa_no.split("~~");
	String [] fgsa_rev_no_arr = fgsa_rev_no.split("~~");
	String [] sn_no_arr = sn_no.split("~~");
	String [] sn_ref_no_arr = sn_ref_no.split("~~");
	String [] sn_rev_no_arr = sn_rev_no.split("~~");
	String [] sn_qty_arr = sn_qty.split("~~");
	String [] contract_type_arr = contract_type.split("~~");
	String [] sn_offspec_qty_arr=sn_offspec_qty.split("~~");
	String [] sn_offspec_flag_arr=sn_offspec_flag.split("~~");
	String gcv_value = "";
	String ncv_value = "";
	
	double gcv_val = 0;
	double ncv_val = 0;
	int gcv_count = 0;
	int ncv_count = 0;
	
	for(int i=0; i<gcv_arr.length; i++)
	{
		if(!gcv_arr[i].equals("") && !gcv_arr[i].equals(" ") && !gcv_arr[i].equals("0") && !gcv_arr[i].equals("0.00"))
		{
			gcv_val += Double.parseDouble(gcv_arr[i]);
			++gcv_count;
		}
		
		if(!ncv_arr[i].equals("") && !ncv_arr[i].equals(" ") && !ncv_arr[i].equals("0") && !ncv_arr[i].equals("0.00"))
		{
			ncv_val += Double.parseDouble(ncv_arr[i]);
			++ncv_count;
		}
	}
	
	if(gcv_count>0)
	{
		gcv_val = gcv_val/gcv_count;
		gcv_value = nf.format(gcv_val);
	}
	
	if(ncv_count>0)
	{
		ncv_val = ncv_val/ncv_count;
		ncv_value = nf.format(ncv_val);
	}
	
	//Following String Getter Methods Are Defined By Samik Shah For Joint Ticket Format Preperation On 21st June, 2010 ...
	String contact_Person_Name = dbContMgmt.getContact_Person_Name();
	String contact_Person_Desg = dbContMgmt.getContact_Person_Desg();
	String contact_Customer_Name = dbContMgmt.getContact_Customer_Name();
	String contact_Customer_Person_Address = dbContMgmt.getContact_Customer_Person_Address();
	String contact_Customer_Person_City = dbContMgmt.getContact_Customer_Person_City();
	String contact_Customer_Person_Pin = dbContMgmt.getContact_Customer_Person_Pin();
	String contact_Customer_Person_Phone = dbContMgmt.getContact_Customer_Person_Phone();
	String contact_Customer_Person_Fax = dbContMgmt.getContact_Customer_Person_Fax();
	String contact_Customer_Person_State = dbContMgmt.getContact_Customer_Person_State();
	String contact_Customer_Person_Country = dbContMgmt.getContact_Customer_Person_Country();
	String contact_Suppl_Name = dbContMgmt.getContact_Suppl_Name();
	String contact_Suppl_Person_Address = dbContMgmt.getContact_Suppl_Person_Address();
	String contact_Suppl_Person_City = dbContMgmt.getContact_Suppl_Person_City();
	String contact_Suppl_Person_Pin = dbContMgmt.getContact_Suppl_Person_Pin();
	String contact_Suppl_Person_Phone = dbContMgmt.getContact_Suppl_Person_Phone();
	String contact_Suppl_Person_Fax = dbContMgmt.getContact_Suppl_Person_Fax();
	String contact_Suppl_Person_State = dbContMgmt.getContact_Suppl_Person_State();
	String contact_Suppl_Person_Country = dbContMgmt.getContact_Suppl_Person_Country();
	
	
	//Following All Setter Methods and Variables For PDF File Generation ... Defined By Samik Shah On 21st September, 2010 ...
	HttpServletRequest req = request;
	
	pdfFile.setGas_date(gas_date);
	pdfFile.setGen_date(gen_date);

	pdfFile.setSeller_nom_qty(seller_nom_qty);
	pdfFile.setQty_mmbtu(qty_mmbtu);
	pdfFile.setQty_scm(qty_scm);
	pdfFile.setCustomer_cd(customer_cd);
	pdfFile.setCustomer_nm(customer_nm);
	pdfFile.setCustomer_abbr(customer_abbr);
	pdfFile.setCustomer_contact_cd(customer_contact_cd);
	pdfFile.setPlant_cd(plant_cd);
	pdfFile.setPlant_nm(plant_nm);

	pdfFile.setGcv_value(gcv_value);
	pdfFile.setNcv_value(ncv_value);

	pdfFile.setPrint_permission(print_permission);

	pdfFile.setGcv_arr(gcv_arr);
	pdfFile.setNcv_arr(ncv_arr);
	pdfFile.setTransporter_cd_arr(transporter_cd_arr);
	pdfFile.setTransporter_nm_arr(transporter_nm_arr);
	pdfFile.setTransporter_abbr_arr(transporter_abbr_arr);
	pdfFile.setTransporter_qty_arr(transporter_qty_arr);

	pdfFile.setFgsa_no_arr(fgsa_no_arr);
	pdfFile.setSn_no_arr(sn_no_arr);
	pdfFile.setSn_ref_no_arr(sn_ref_no_arr);
	pdfFile.setSn_rev_no_arr(sn_rev_no_arr);
	pdfFile.setSn_qty_arr(sn_qty_arr);
	pdfFile.setContract_type_arr(contract_type_arr);
	pdfFile.setSn_offspec_qty_arr(sn_offspec_qty_arr);
	pdfFile.setSn_offspec_flag_arr(sn_offspec_flag_arr);
	
	
	pdfFile.setContact_Person_Name(contact_Person_Name);
	pdfFile.setContact_Person_Desg(contact_Person_Desg);
	pdfFile.setContact_Customer_Name(contact_Customer_Name);
	pdfFile.setContact_Customer_Person_Address(contact_Customer_Person_Address);
	pdfFile.setContact_Customer_Person_City(contact_Customer_Person_City);
	pdfFile.setContact_Customer_Person_Country(contact_Customer_Person_Country);
	pdfFile.setContact_Customer_Person_Fax(contact_Customer_Person_Fax);
	pdfFile.setContact_Customer_Person_Phone(contact_Customer_Person_Phone);
	pdfFile.setContact_Customer_Person_Pin(contact_Customer_Person_Pin);
	pdfFile.setContact_Customer_Person_State(contact_Customer_Person_State);
	pdfFile.setContact_Suppl_Name(contact_Suppl_Name);
	pdfFile.setContact_Suppl_Person_Address(contact_Suppl_Person_Address);
	pdfFile.setContact_Suppl_Person_City(contact_Suppl_Person_City);
	pdfFile.setContact_Suppl_Person_Country(contact_Suppl_Person_Country);
	pdfFile.setContact_Suppl_Person_Fax(contact_Suppl_Person_Fax);
	pdfFile.setContact_Suppl_Person_Phone(contact_Suppl_Person_Phone);
	pdfFile.setContact_Suppl_Person_Pin(contact_Suppl_Person_Pin);
	pdfFile.setContact_Suppl_Person_State(contact_Suppl_Person_State);	
	
	pdfFile.setRequest(req);
	pdfFile.setCall_flag("Pdf_For_JT_OLD");
	pdfFile.init();
	
	String contPath = request.getContextPath()== null?"":""+request.getContextPath();
	if(contPath.trim().length()>1)
	{
		contPath = contPath.trim().substring(1);
	}
	else
	{
		contPath = contPath.trim();
	}
	
	String pdfpath = pdfFile.getJt_pdf_path().trim();
	String url_start = pdfFile.getUrl_start().trim();

	if(contPath==null || contPath.equals("") || pdfpath==null || pdfpath.equals(""))
	{
		
	}
	else
	{
		pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
	 	pdfpath = url_start+"/pdf_reports/joint_tickets/pdf_files"+pdfpath;
	 	//pdfpath = "../joint_tickets/pdf_files"+pdfpath;
	}
 %>
<body bgcolor="white">
<iframe src="<%=pdfpath%>" width="100%" height="100%">
</iframe>
</body>
<%} %>
</html>