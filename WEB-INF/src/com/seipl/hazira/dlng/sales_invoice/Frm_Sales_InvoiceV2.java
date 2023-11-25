package com.seipl.hazira.dlng.sales_invoice;

//Servlet Introduced By     : Samik Shah ...
//Servlet Introduced On     : 20th May, 2010 ...
//Code Reviewed By			:  
//Code Reviewed Date		:  
//Code Review Status  		:
//Last Modified By			: Samik Shah ...
//Last Modified Date		: 17th June, 2010 ...

import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.mail.Session;
import javax.naming.*;
import javax.sql.*;



//import com.seipl.hazira.dlng.invoice_report.CipherEncrypter;
import com.seipl.hazira.dlng.mail.mail;
import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

@WebServlet("/servlet/Frm_Sales_InvoiceV2")
public class Frm_Sales_InvoiceV2 extends HttpServlet
{ 
	
	public static Connection dbcon;
	
	public String servletName = "Frm_Sales_Invoice";
	public String methodName = "";
	public String option = "";
	public String form_name = "";
	public String msg = "",error_msg ="";
	int count = 0;
	static escapeSingleQuotes obj = new escapeSingleQuotes();
	
	//Following NumberFormat Object is defined by Samik Shah ... On 2nd June, 2010 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("##0.0000"); //For Currency Purpose ... Defined By Samik Shah On 1st June, 2010 ...
	NumberFormat nf4 = new DecimalFormat("##0.000"); //For Currency Purpose ... Defined By Samik Shah On 1st June, 2010 ...
	NumberFormat nff = new DecimalFormat("######,##0.00");
	
	private static String queryString = null;
	private static String queryString1 = null;
	private static String queryString2 = null;
	private static String queryString3 = null;
	private static String queryString4 = null;
	private static Statement stmt = null;
	private static Statement stmt1 = null;
	private static Statement stmt2 = null;
	private static Statement stmt3 = null;
	private static Statement stmt4 = null;
	private ResultSet rset = null;
	private ResultSet rset1 = null;
	private ResultSet rset2 = null;
	private ResultSet rset3 = null;
	private ResultSet rset4 = null;
	
	public String write_permission = "N";
	public String delete_permission = "N";
	public String print_permission = "N";
	public String check_permission = "N";
	public String authorize_permission = "N";
	public String approve_permission = "N";
	public String audit_permission = "N";
	String hostname="",userid="",password="";
	
	public String form_id = "0";
	public String form_nm = "";
	public String tds_percent = ""; //Hiren_20210709
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		try
		{
			Context Context = new InitialContext();
			if(Context == null) 
			{
				throw new Exception("Boom - No Context");
			}			
			Context envContext  = (Context)Context.lookup("java:/comp/env");
			
			Context envContext1 = (Context)Context.lookup("java:/comp/env");
//            Session mailServer = (Session)envContext.lookup("mail/Session");
//		    hostname = mailServer.getProperty("mail.smtp.host") ;
//		    userid = mailServer.getProperty("userid") ;
//		    password = mailServer.getProperty("password") ;
			
			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);			
			if(ds != null)
			{
				dbcon = ds.getConnection();
			}
			else
			{
				//System.out.println("Data Source Not Found - Error In "+servletName+" Servlet !!!");
			}
			
			if(dbcon != null)
			{
				dbcon.setAutoCommit(false);
				stmt = dbcon.createStatement(); 
				stmt1 = dbcon.createStatement();
				stmt2 = dbcon.createStatement(); 
				stmt3 = dbcon.createStatement();
				stmt4 = dbcon.createStatement();
			}			
			option=req.getParameter("option")==null?"":req.getParameter("option");
			
			write_permission = req.getParameter("write_permission")==null?"N":req.getParameter("write_permission");
			delete_permission = req.getParameter("delete_permission")==null?"N":req.getParameter("delete_permission");
			print_permission = req.getParameter("print_permission")==null?"N":req.getParameter("print_permission");
			check_permission = req.getParameter("check_permission")==null?"N":req.getParameter("check_permission");
			authorize_permission = req.getParameter("authorize_permission")==null?"N":req.getParameter("authorize_permission");
			approve_permission = req.getParameter("approve_permission")==null?"N":req.getParameter("approve_permission");
			audit_permission = req.getParameter("audit_permission")==null?"N":req.getParameter("audit_permission");
			
			form_id = req.getParameter("form_id")==null?"0":req.getParameter("form_id");
			form_nm = req.getParameter("form_nm")==null?"":req.getParameter("form_nm");
			System.out.println("option****"+option);
			if(option.equalsIgnoreCase("submitBillingDetails"))
			{
				//submitBillingDetails(req,res); //NB20140904 //Defined By Samik Shah On 1st June, 2010 ...
				submitBillingDetails_ByRS19042017(req,res);
			}
			else if(option.equalsIgnoreCase("submitBillingDetails_TAX_ADJ"))
			{
//				submitBillingDetails_tax_adj(req,res); //SB20160509:SBC //NB20140904 //Defined By Samik Shah On 1st June, 2010 ...
				submitBillingDetails_tax_adj_ByRS20042017(req,res);
			}
			else if(option.equalsIgnoreCase("deleteInvoice"))
			{
				deleteInvoice(req,res); //Defined By Samik Shah On 16th June, 2010 ...
			}
			else if(option.equalsIgnoreCase("dr_cr_note"))
			{
				submitDebitCreditNote(req,res); //Defined By Achal On 2nd December, 2010 ...
			}
			else if(option.equalsIgnoreCase("C-FORM"))
			{
				submitCForm(req,res); //Defined By Achal On 8th December, 2010 ...
			}
			else if(option.equalsIgnoreCase("payment_security"))
			{
				submitPaySecurity(req,res); //Defined By Achal On 12th January, 2011 ...
			}
			else if(option.equalsIgnoreCase("Check_Invoice"))
			{
				Check_Invoice(req,res); //Defined By Samik Shah On 21st January, 2011 ...
			}
			else if(option.equalsIgnoreCase("Approve_Invoice"))
			{
				Approve_Invoice(req,res); //Defined By Samik Shah On 21st January, 2011 ...
			}
			else if(option.equalsIgnoreCase("Approve_Credit")) //SB20160602
			{
				Approve_Credit(req,res); //Defined By Samik Shah On 21st January, 2011 ...
			}
			else if(option.equalsIgnoreCase("inv_tracking"))
			{
				insertinvtracking(req,res);
			}
			else if(option.equalsIgnoreCase("submitadvancecollectiondetails"))//NB20140902
			{
				insertpartyadvancedtl(req,res);//NB20140902
			}
			else if(option.equalsIgnoreCase("InsertInvoicePaymentDetails"))
			{
//				//System.out.println("---insertInvoicePaymentDetails starts---");
				insertInvoicePaymentDetails(req,res);	//BK20160309
//				//System.out.println("---insertInvoicePaymentDetails ends---");
			}
			else if(option.equalsIgnoreCase("UpdateInvoicePaymentDetails"))
			{
//				//System.out.println("---updateInvoicePaymentDetails starts---");
				updateInvoicePaymentDetails(req,res);	//BK20160309
//				//System.out.println("---updateInvoicePaymentDetails ends---");
			}
			else if(option.equalsIgnoreCase("dr_cr_note_approval"))
			{
				submitDebitCreditNote_approval(req,res); //RU20161113
			}
			else if(option.equalsIgnoreCase("submitDebitCreditNote_gen"))
			{
				submitDebitCreditNote_gen(req,res); //RU20161114
			}
			else if(option.equalsIgnoreCase("submitSUGInvoiceDetails"))
			{
				submitSUGInvoiceDetails(req,res);
			}
			else if(option.equalsIgnoreCase("Check_Sug_Invoice"))
			{
				Check_Sug_Invoice(req,res);
			}
			else if(option.equalsIgnoreCase("Approve_Sug_Invoice"))
			{
				Approve_Sug_Invoice(req,res);
			}
			else if(option.equalsIgnoreCase("SubmitPlantDtls"))
			{
				SubmitPlantDtls(req,res);
			}else if(option.equalsIgnoreCase("submitLatePaymentDetails")) {
				submitLatePaymentDetails(req,res);
			} else if(option.equalsIgnoreCase("Check_Late_Invoice")) {
				checkLatePaymentInvoice(req,res);
			} else if(option.equalsIgnoreCase("Approve_Late_Invoice")) {
				approveLatePaymentInvoice(req,res);
			}
		}		   
		catch(Exception e)
		{
			//System.out.println("Exception In "+servletName+" - (doPost())-(CONNECTION) : "+e.getMessage());
			e.printStackTrace();
			res.sendRedirect("../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=sales_invoice&formname="+form_name);
		}
		finally
		{
			if(rset != null)
			{
				try
				{
					rset.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("rset is not close "+e.getMessage());
				}
			}
			if(rset1 != null)
			{
				try
				{
					rset1.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("rset1 is not close "+e.getMessage());
				}
			}
			if(rset2 != null)
			{
				try
				{
					rset2.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("rset2 is not close "+e.getMessage());
				}
			}
			if(rset3 != null)
			{
				try
				{
					rset3.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("rset3 is not close "+e.getMessage());
				}
			}
			if(rset4 != null)
			{
				try
				{
					rset4.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("rset4 is not close "+e.getMessage());
				}
			}
			if(stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("stmt is not close "+e.getMessage());
				} 
			}
			if(stmt1 != null)
			{
				try
				{
					stmt1.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("stmt1 is not close "+e.getMessage());
				}
			}
			if(stmt2 != null)
			{
				try
				{
					stmt2.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("stmt2 is not close "+e.getMessage());
				}
			}
			if(stmt3 != null)
			{
				try
				{
					stmt3.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("stmt3 is not close "+e.getMessage());
				}
			}
			if(stmt4 != null)
			{
				try
				{
					stmt4.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("stmt4 is not close "+e.getMessage());
				}
			}
			if(dbcon != null)
			{
				try
				{
					dbcon.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					//System.out.println("dbcon is not close "+e.getMessage());
				}
			}
		}
		
		if(option.equals("digitalSignJar")) {
			
			String realPath = req.getRealPath("PDF_signer.jar");
			System.out.println("realPath**top**"+realPath);
			realPath = realPath.replaceAll("\\\\", "/");
			System.out.println("realPath****"+realPath);
			System.out.println("File.separator = "+File.separator);
			
			ProcessBuilder pb = new ProcessBuilder("java", "-jar", realPath);
			Process p = pb.start();
			
//			Runtime.getRuntime().exec("cmd /c start "+realPath);

		}
		/*RequestDispatcher rd=req.getRequestDispatcher(url); 
	   	 try {
					rd.forward(req, res);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		res.sendRedirect(url);*/
	}
	public void approveLatePaymentInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		String year = request.getParameter("year")==null?"":request.getParameter("year");
		String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		form_id = formId;
		try {
			methodName = "approveLatePaymentInvoice()";
			form_name = "rpt_late_pay_view_invoice_dtl.jsp";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
			String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
			String financial_year = request.getParameter("financial_year")==null?"":request.getParameter("financial_year");
			String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
			String approve_flag = request.getParameter("approve_flag")==null?"N":request.getParameter("approve_flag");
			String temp_cont="";
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){
				temp_cont="I";
			}else{
				temp_cont="M";
			}

			String query = "UPDATE DLNG_INVOICE_MST SET APPROVED_FLAG='"+approve_flag+"', APPROVED_BY='"+user_cd+"', APPROVED_DT = SYSDATE "
				//	+ "WHERE HLPL_INV_SEQ_NO='"+hlpl_inv_seq_no+"' AND NEW_INV_SEQ_NO='"+new_inv_seq_no+"' "
				+ "WHERE  NEW_INV_SEQ_NO='"+new_inv_seq_no+"' "
					//+ "AND CUSTOMER_CD='"+customer_cd+"' AND FINANCIAL_YEAR='"+financial_year+"' "
				+ "AND CUSTOMER_CD='"+customer_cd+"'  "
					+ "AND CONTRACT_TYPE='"+temp_cont+"' AND FLAG='"+temp_cont+"'";
			stmt.executeUpdate(query);
			if(approve_flag.equals("Y"))
				msg = "Invoice "+new_inv_seq_no+" Approved Successfully!!";
			else 
				msg = "Invoice "+new_inv_seq_no+" DisApproved Successfully!!";
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
			
			dbcon.commit();
		} catch(Exception e) {
			dbcon.rollback();
			msg = "Invoice "+new_inv_seq_no+" Approving Process Not Done Successfully!!";
			e.printStackTrace();
		}
		response.sendRedirect("../sales_invoice/rpt_late_pay_view_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year);

	}
	public void checkLatePaymentInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		String year = request.getParameter("year")==null?"":request.getParameter("year");
		String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		form_id = formId;
		try {
			methodName = "checkLatePaymentInvoice()";
			form_name = "rpt_late_pay_view_invoice_dtl.jsp";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
			String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
			String financial_year = request.getParameter("financial_year")==null?"":request.getParameter("financial_year");
			String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
			String check_flag = request.getParameter("check_flag")==null?"N":request.getParameter("check_flag");
			String temp_cont="";
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){
				temp_cont="I";
			}else{
				temp_cont="M";
			}

			String query = "UPDATE DLNG_INVOICE_MST SET CHECKED_FLAG='"+check_flag+"', CHECKED_BY='"+user_cd+"', CHECKED_DT = SYSDATE "
					//+ "WHERE HLPL_INV_SEQ_NO='"+hlpl_inv_seq_no+"' AND NEW_INV_SEQ_NO='"+new_inv_seq_no+"' "
					+ "WHERE NEW_INV_SEQ_NO='"+new_inv_seq_no+"' "
					//+ "AND CUSTOMER_CD='"+customer_cd+"' AND FINANCIAL_YEAR='"+financial_year+"' "
					+ "AND CUSTOMER_CD='"+customer_cd+"'  "
					+ "AND CONTRACT_TYPE='"+temp_cont+"' AND FLAG='"+temp_cont+"'";
			stmt.executeUpdate(query);
			if(check_flag.equals("Y"))
				msg = "Invoice "+new_inv_seq_no+" Checked Successfully!!";
			else 
				msg = "Invoice "+new_inv_seq_no+" UnChecked Successfully!!";
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
			
			dbcon.commit();
		} catch(Exception e) {
			dbcon.rollback();
			msg = "Invoice "+new_inv_seq_no+" Checking Process Not Done Successfully!!";
			e.printStackTrace();
		}
		response.sendRedirect("../sales_invoice/rpt_late_pay_view_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year);

//		url = "../sales_invoice/rpt_late_pay_view_invoice_dtl.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg+"&month="+month+"&year="+year+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
	}
	public void submitLatePaymentDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		String year = request.getParameter("year")==null?"":request.getParameter("year");
		String nNew_inv_seq_no = request.getParameter("nNew_inv_seq_no")==null?"":request.getParameter("nNew_inv_seq_no");
		try {
			methodName = "submitLatePaymentDetails()";
			form_name = "frm_late_pay_invoice_dtl.jsp";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
			String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
			String financial_year = request.getParameter("financial_year")==null?"":request.getParameter("financial_year");
			String financial_yr_hid = request.getParameter("financial_yr_hid")==null?"":request.getParameter("financial_yr_hid");
			String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
			String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
			String inv_date = request.getParameter("inv_date")==null?"":request.getParameter("inv_date");
			String gross_amt_inr = request.getParameter("total_pay")==null?"":request.getParameter("total_pay");
			String net_amt_inr = request.getParameter("total_amt_pay")==null?"":request.getParameter("total_amt_pay");
			String tax_amt_inr = request.getParameter("total_tax_pay")==null?"":request.getParameter("total_tax_pay");
			String invoice_date = request.getParameter("invoice_date")==null?"":request.getParameter("invoice_date");
			//String no_days = request.getParameter("no_days")==null?"":request.getParameter("no_days");
			String no_days = request.getParameter("diff_days")==null?"":request.getParameter("diff_days");
			String contract_start_dt = request.getParameter("contract_start_dt")==null?"":request.getParameter("contract_start_dt");
			String tax_struct_cd = request.getParameter("tax_struct_cd")==null?"":request.getParameter("tax_struct_cd");
			String contract_end_dt = request.getParameter("contract_end_dt")==null?"":request.getParameter("contract_end_dt");
			String contact_person = request.getParameter("contact_person")==null?"0":request.getParameter("contact_person");
			String int_rate = request.getParameter("int_rate")==null?"1":request.getParameter("int_rate");
			String nHlpl_inv_seq_no = request.getParameter("nHlpl_inv_seq_no")==null?"":request.getParameter("nHlpl_inv_seq_no");
			String nCust_inv_seq_no = request.getParameter("nCust_inv_seq_no")==null?"":request.getParameter("nCust_inv_seq_no");
			String remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
			String tax_flag = request.getParameter("tax_flag")==null?"":request.getParameter("tax_flag");
			String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
			String act_cont_type = request.getParameter("act_cont_type")==null?"":request.getParameter("act_cont_type");
			form_id = formId;
			form_nm = "Late Payment Invoice";
			String temp_cont="";
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L") ){
				temp_cont="I";
			}else{
				temp_cont="M";
			}
			escapeSingleQuotes es = new escapeSingleQuotes();
			remark_1 = es.replaceSingleQuotes(remark_1);
			//RG20200402
			remark_1=remark_1.replace("\n","%$%$%$%$");
			//
			gross_amt_inr = gross_amt_inr.replaceAll(",", "");
			net_amt_inr = net_amt_inr.replaceAll(",", "");
			tax_amt_inr = tax_amt_inr.replaceAll(",", "");
			String activity = request.getParameter("activity")==null?"P":request.getParameter("activity");
			
			String query = "SELECT FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,FINANCIAL_YEAR,PLANT_SEQ_NO,MAPPING_ID,"
					+ "TOTAL_QTY,SALE_PRICE,TO_CHAR(PAY_RECV_DT,'DD/MM/YYYY'),PAY_RECV_AMT,SUPPLIER_CD "
					+ "FROM DLNG_INVOICE_MST "
					+ "WHERE HLPL_INV_SEQ_NO='"+hlpl_inv_seq_no+"' AND CUSTOMER_CD='"+customer_cd+"' AND "
					+ "CONTRACT_TYPE='"+contract_type+"' AND INVOICE_DT = TO_DATE('"+inv_date+"','DD/MM/YYYY') "
					+ "AND FINANCIAL_YEAR='"+financial_year+"' ";
			System.out.println("Update Data.."+query);
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				if(activity.equals("P")) {
					query = "INSERT INTO DLNG_INVOICE_MST(FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CUSTOMER_CD,CONTRACT_TYPE,"
							+ "HLPL_INV_SEQ_NO,CUST_INV_SEQ_NO,INVOICE_DT,MAPPING_ID,TAX_STRUCT_CD,PLANT_SEQ_NO,"
							+ "TAX_AMT_INR,GROSS_AMT_INR,NET_AMT_INR,CONTACT_PERSON_CD,ENT_DT,EMP_CD, REMARK_SPECIFICATION,"
							//+ "REMARK_1,TOTAL_QTY,SALE_PRICE,PERIOD_START_DT,PERIOD_END_DT,PAY_RECV_DT,PAY_RECV_AMT,"
							+ "REMARK_1,TOTAL_QTY,SALE_PRICE,PERIOD_START_DT,PERIOD_END_DT,"
							+ "SUPPLIER_CD,NEW_INV_SEQ_NO,FLAG,OFFSPEC_QTY,OFFSPEC_RATE,FINANCIAL_YEAR,GROSS_AMT_USD,TAX_FLAG) "
							+ "VALUES('"+rset.getString(1)+"',"
							+ "'"+rset.getString(2)+"','"+rset.getString(3)+"','"+rset.getString(4)+"','"+customer_cd+"',"
							+ "'"+temp_cont+"','"+nHlpl_inv_seq_no+"','"+nCust_inv_seq_no+"',TO_DATE('"+invoice_date+"','DD/MM/YYYY'),"
							+ "'"+rset.getString(7)+"','"+tax_struct_cd+"','"+rset.getString(6)+"','"+tax_amt_inr+"',"
							+ "'"+gross_amt_inr+"','"+net_amt_inr+"','"+contact_person+"',SYSDATE,'"+user_cd+"','"+new_inv_seq_no+"',"
							+ "'"+remark_1+"','"+rset.getString(8)+"','"+rset.getString(9)+"',TO_DATE('"+contract_start_dt+"','DD/MM/YYYY'),"
//							+ "TO_DATE('"+contract_end_dt+"','DD/MM/YYYY'),TO_DATE('"+rset.getString(10)+"','DD/MM/YYYY'),"
//							+ "'"+rset.getString(11)+"','"+rset.getString(12)+"','"+nNew_inv_seq_no+"','"+temp_cont+"','"+no_days+"',"
							+ "TO_DATE('"+contract_end_dt+"','DD/MM/YYYY'),'"+rset.getString(12)+"','"+nNew_inv_seq_no+"','"+temp_cont+"','"+no_days+"',"
							+ "'"+int_rate+"','"+financial_yr_hid+"','0','"+tax_flag+"') ";
					System.out.println("Insert Data.."+query);
					msg = "Late Payment Invoice "+nNew_inv_seq_no+" Submitted Successfully!!!";
				} else {
					query = "UPDATE DLNG_INVOICE_MST SET GROSS_AMT_INR='"+gross_amt_inr+"', NET_AMT_INR='"+net_amt_inr+"', "
							+ "TAX_AMT_INR='"+tax_amt_inr+"', INVOICE_DT=TO_DATE('"+invoice_date+"','DD/MM/YYYY'), TAX_STRUCT_CD='"+tax_struct_cd+"', "
							+ "OFFSPEC_QTY='"+no_days+"', OFFSPEC_RATE='"+int_rate+"', PERIOD_START_DT= TO_DATE('"+contract_start_dt+"','DD/MM/YYYY'), "
							+ "PERIOD_END_DT = TO_DATE('"+contract_end_dt+"','DD/MM/YYYY'), CONTACT_PERSON_CD='"+contact_person+"', REMARK_1='"+remark_1+"' "
							+ "WHERE NEW_INV_SEQ_NO='"+nNew_inv_seq_no+"' AND HLPL_INV_SEQ_NO='"+nHlpl_inv_seq_no+"' AND "
							+ "CONTRACT_TYPE='"+temp_cont+"' AND FLAG='"+temp_cont+"' AND CUSTOMER_CD='"+customer_cd+"' "
							//+ "CONTRACT_TYPE='M' AND FLAG='M' AND CUSTOMER_CD='"+customer_cd+"' "
							+ "AND PLANT_SEQ_NO='"+rset.getString(6)+"' ";
					System.out.println("Update Data.."+query);
					msg = "Late Payment Invoice "+nNew_inv_seq_no+" Updated Successfully!!!";
				}
				stmt.executeUpdate(query);
			}
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		} catch(Exception e) {
			msg = "Error: Late Payment Invoice "+nNew_inv_seq_no+" Not Submitted Successfully!!!";
			dbcon.rollback();
			e.printStackTrace();
		}
		response.sendRedirect("../sales_invoice/frm_late_pay_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year);

	}
	public void SubmitPlantDtls(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException
	{
		methodName = "SubmitPlantDtls()";
		form_name = "frm_sug_st_invoice_preparation.jsp";
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String cargo_no = request.getParameter("cargo_no")==null?"":request.getParameter("cargo_no");
		String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
		String index = request.getParameter("index")==null?"-1":request.getParameter("index");	
		String selected_map_id = request.getParameter("selected_map_id")==null?"":request.getParameter("selected_map_id");
		String count_check = request.getParameter("count_check")==null?"0":request.getParameter("count_check");
		String selected_customer_id = request.getParameter("selected_customer_id")==null?"0":request.getParameter("selected_customer_id");
		String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
		String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
		String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
		String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
		String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
		String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
		String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			String date = "";
			
			String q = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY'))+1,'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(q);
			if(rset.next())
			{
				date = rset.getString(1);
			}
			
			for(int i=0;i<Integer.parseInt(count_check);i++)
			{
				String chk = "check"+index+"-"+i;
				String chk_val = request.getParameter(chk)==null?"N":request.getParameter(chk);
				if(chk_val.equals("Y"))
				{
					String val = "plant"+index+"-"+i;
					String plant_seq = request.getParameter(val)==null?"":request.getParameter(val);
					
					String query = "SELECT COUNT(PLANT_SEQ_NO) FROM FMS8_SUG_INVOICE_PLANT_DTLS WHERE "
							+ "PLANT_SEQ_NO='"+plant_seq+"' AND CUSTOMER_CD='"+selected_customer_id+"' "
							+ "AND MAPPING_ID='"+selected_map_id+"' AND CARGO_SEQ_NO='"+cargo_no+"' ";
//					//System.out.println("query=="+query);
					rset = stmt.executeQuery(query);
					if(rset.next())
					{
						int count = rset.getInt(1);
						if(count>0) 
						{
							query = "DELETE FROM FMS8_SUG_INVOICE_PLANT_DTLS WHERE "
									+ "PLANT_SEQ_NO='"+plant_seq+"' AND CUSTOMER_CD='"+selected_customer_id+"' "
									+ "AND MAPPING_ID='"+selected_map_id+"' AND CARGO_SEQ_NO='"+cargo_no+"' ";
//							//System.out.println("query=="+query);
							stmt.executeUpdate(query);
						}
					}
					
					query = "INSERT INTO FMS8_SUG_INVOICE_PLANT_DTLS(PLANT_SEQ_NO,CUSTOMER_CD, "
							+ "MAPPING_ID, CARGO_SEQ_NO, EFF_DT, ENTER_BY, ENTER_DT, FLAG) VALUES("
							+ "'"+plant_seq+"','"+selected_customer_id+"','"+selected_map_id+"','"+cargo_no+"'"
							+ ",TO_DATE('"+date+"','DD/MM/YYYY'),'"+user_cd+"',SYSDATE,'Y')";
//					//System.out.println("query=="+query);
					stmt.executeUpdate(query);
					
				}
				else
				{
					String val = "plant"+index+"-"+i;
					String plant_seq = request.getParameter(val)==null?"":request.getParameter(val);
					
					String query = "SELECT COUNT(PLANT_SEQ_NO) FROM FMS8_SUG_INVOICE_PLANT_DTLS WHERE "
							+ "PLANT_SEQ_NO='"+plant_seq+"' AND CUSTOMER_CD='"+selected_customer_id+"' "
							+ "AND MAPPING_ID='"+selected_map_id+"' AND CARGO_SEQ_NO='"+cargo_no+"' ";
//					//System.out.println("query=="+query);
					rset = stmt.executeQuery(query);
					if(rset.next())
					{
						int count = rset.getInt(1);
						if(count>0) 
						{
							query = "DELETE FROM FMS8_SUG_INVOICE_PLANT_DTLS WHERE "
									+ "PLANT_SEQ_NO='"+plant_seq+"' AND CUSTOMER_CD='"+selected_customer_id+"' "
									+ "AND MAPPING_ID='"+selected_map_id+"' AND CARGO_SEQ_NO='"+cargo_no+"' ";
//							//System.out.println("query=="+query);
							stmt.executeUpdate(query);
						}
					}
				}
			}
				
			msg = "Plant Details Has Been Successfully Submitted !!!";
			response.sendRedirect("../sales_invoice/frm_sug_st_invoice_preparation.jsp?selected_customer_id="+selected_customer_id+"&selected_map_id="+selected_map_id+"&index="+index+"&visible_flag=Y&mapping_id="+mapping_id+"&cargo_no="+cargo_no+"&msg="+msg+"&month="+month+"&year="+year+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Plant Details Has Not Been Submitted !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_sug_st_invoice_preparation.jsp?selected_customer_id="+selected_customer_id+"&selected_map_id="+selected_map_id+"&index="+index+"&visible_flag=Y&mapping_id="+mapping_id+"&cargo_no="+cargo_no+"&msg="+msg+"&month="+month+"&year="+year+"write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);	
		}
	}//End Of SubmitPlantDtl Method ...
	
	public void Approve_Sug_Invoice(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "Approve_Sug_Invoice()";
		form_name = "frm_sug_st_invoice_preparation.jsp";
		String check_flag = request.getParameter("check_flag")==null?"N":request.getParameter("check_flag");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no");
		String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String contract_type = request.getParameter("contract_type")==null?"C":request.getParameter("contract_type");
		String cargo_no = request.getParameter("cargo_no")==null?"":request.getParameter("cargo_no");
		String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id");
		String index = request.getParameter("index")==null?"-1":request.getParameter("index");	
		String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
		String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
		String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
		String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
		String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
		String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
		String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
		String selected_map_id = request.getParameter("selected_map_id")==null?"":request.getParameter("selected_map_id");
		String count_check = request.getParameter("count_check")==null?"0":request.getParameter("count_check");
		String selected_customer_id = request.getParameter("selected_customer_id")==null?"0":request.getParameter("selected_customer_id");
		String new_inv_seq_no = "";
		
		try {
			String q = "SELECT FORM_CD,FORM_NAME FROM SEC_FORM_MST WHERE FORM_NAME = 'SUG ST Invoice' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(2);
				form_id = rset.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
		
			if(check_flag.equals("N"))
			{
				queryString1 = "UPDATE DLNG_INVOICE_MST SET approved_flag='', " +
						   "approved_by='', approved_dt=null, checked_flag='', checked_by='', checked_dt='' " +
						   "WHERE financial_year='"+inv_financial_year+"' AND " +
			  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
			  			   "contract_type='"+contract_type+"' and flag='U' ";
			} else {
				queryString1 = "UPDATE DLNG_INVOICE_MST SET approved_flag='"+check_flag+"', " +
						   "approved_by='"+user_cd+"', approved_dt=sysdate " +
						   "WHERE financial_year='"+inv_financial_year+"' AND " +
			  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
			  			   "contract_type='"+contract_type+"' and flag='U' ";
			}
			
				
//			//System.out.println("Query For Updating The Check Status of Invoice = "+queryString1);
				
			stmt1.executeUpdate(queryString1);								
					
			queryString1 = "SELECT NEW_INV_SEQ_NO FROM DLNG_INVOICE_MST WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_inv_seq_no)+"' "
					+ "AND FINANCIAL_YEAR='"+inv_financial_year+"' AND CONTRACT_TYPE='C' AND FLAG='U'";
			rset = stmt1.executeQuery(queryString1);
			if(rset.next()) {
				new_inv_seq_no = rset.getString(1);
			} else {
				new_inv_seq_no = hlpl_inv_seq_no;
			}
			
			msg = "Invoice Approval Process Of "+new_inv_seq_no+" Has Been Successfully Completed !!!";
			response.sendRedirect("../sales_invoice/rpt_sug_approve_invoice_dtl.jsp?selected_map_id="+selected_map_id+"&selected_customer_id="+selected_customer_id+"&index="+index+"&visible_flag=Y&mapping_id="+mapping_id+"&cargo_no="+cargo_no+"&msg="+msg+"&month="+month+"&year="+year+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Invoice Approval Process is Failed For "+hlpl_inv_seq_no+" Invoice !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/rpt_sug_approve_invoice_dtl.jsp?selected_map_id="+selected_map_id+"&selected_customer_id="+selected_customer_id+"&index="+index+"&visible_flag=Y&mapping_id="+mapping_id+"&cargo_no="+cargo_no+"&msg="+msg+"&month="+month+"&year="+year+"write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);	
		}
	}//End Of Approve_Sug_Invoice() Method ...
	
	public void Check_Sug_Invoice(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "Check_Sug_Invoice()";
		form_name = "frm_sug_st_invoice_preparation.jsp";
		
		String check_flag = request.getParameter("check_flag")==null?"N":request.getParameter("check_flag");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no");
		String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String contract_type = request.getParameter("contract_type")==null?"C":request.getParameter("contract_type");
		String cargo_no = request.getParameter("cargo_no")==null?"":request.getParameter("cargo_no");
		String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id");
		String index = request.getParameter("index")==null?"-1":request.getParameter("index");
		String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
		String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
		String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
		String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
		String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
		String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
		String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
		String selected_map_id = request.getParameter("selected_map_id")==null?"":request.getParameter("selected_map_id");
		String count_check = request.getParameter("count_check")==null?"0":request.getParameter("count_check");
		String selected_customer_id = request.getParameter("selected_customer_id")==null?"0":request.getParameter("selected_customer_id");
		String new_inv_seq_no = "";
		
		try {
			String q = "SELECT FORM_CD,FORM_NAME FROM SEC_FORM_MST WHERE FORM_NAME = 'SUG ST Invoice' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(2);
				form_id = rset.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			queryString1 = "UPDATE DLNG_INVOICE_MST SET checked_flag='"+check_flag+"', " +
						   "checked_by='"+user_cd+"', checked_dt=sysdate " +
						   "WHERE financial_year='"+inv_financial_year+"' AND " +
			  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
			  			   "contract_type='"+contract_type+"' and flag='U' ";
				
//			//System.out.println("Query For Updating The Check Status of Invoice = "+queryString1);
				
			stmt1.executeUpdate(queryString1);								
			
			queryString1 = "SELECT NEW_INV_SEQ_NO FROM DLNG_INVOICE_MST WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_inv_seq_no)+"' "
					+ "AND FINANCIAL_YEAR='"+inv_financial_year+"' AND CONTRACT_TYPE='C' AND FLAG='U'";
			rset = stmt1.executeQuery(queryString1);
			if(rset.next()) {
				new_inv_seq_no = rset.getString(1);
			} else {
				new_inv_seq_no = hlpl_inv_seq_no;
			}
					
			msg = "Invoice Checking Process Of "+new_inv_seq_no+" Has Been Successfully Completed !!!";
			response.sendRedirect("../sales_invoice/rpt_sug_check_invoice_dtl.jsp?selected_map_id="+selected_map_id+"&selected_customer_id="+selected_customer_id+"&index="+index+"&visible_flag=Y&mapping_id="+mapping_id+"&cargo_no="+cargo_no+"&msg="+msg+"&month="+month+"&year="+year+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Invoice Checking Process is Failed For "+hlpl_inv_seq_no+" Invoice !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/rpt_sug_check_invoice_dtl.jsp?selected_map_id="+selected_map_id+"&selected_customer_id="+selected_customer_id+"&index="+index+"&visible_flag=Y&mapping_id="+mapping_id+"&cargo_no="+cargo_no+"&msg="+msg+"&month="+month+"&year="+year+"write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);	
		}
	}//End Of Check_Sug_Invoice() Method ...
	
	
	//ADDED BY RS20/02/2017 FOR SUG INVOICE ENTRY
	
	public void submitSUGInvoiceDetails(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
		String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
		String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
		String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
		String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
		String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
		String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
		String cargo_no = request.getParameter("cargo_no")==null?"":request.getParameter("cargo_no");
		String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id");
		String index = request.getParameter("index")==null?"-1":request.getParameter("index");
		String selected_map_id = request.getParameter("selected_map_id")==null?"":request.getParameter("selected_map_id");
		String count_check = request.getParameter("count_check")==null?"0":request.getParameter("count_check");
		String selected_customer_id = request.getParameter("selected_customer_id")==null?"0":request.getParameter("selected_customer_id");
		
		try {
			String q = "SELECT FORM_CD,FORM_NAME FROM SEC_FORM_MST WHERE FORM_NAME = 'SUG ST Invoice' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(2);
				form_id = rset.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try
		{
			HttpSession session = request.getSession();
			String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
			String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
			
			String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
			String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
			String fgsa_no = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
			String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"0":request.getParameter("fgsa_rev_no");
			String sn_no = request.getParameter("sn_no")==null?"0":request.getParameter("sn_no");
			String sn_rev_no = request.getParameter("sn_rev_no")==null?"0":request.getParameter("sn_rev_no");
			String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
			String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");
			String customer_plant_seq_no = request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
			String contact_person = request.getParameter("contact_person")==null?"0":request.getParameter("contact_person");
			String invoice_date = request.getParameter("invoice_date")==null?"":request.getParameter("invoice_date");
			String due_date = request.getParameter("due_date")==null?"":request.getParameter("due_date");
			
			String boe_no = request.getParameter("boe_no")==null?"":request.getParameter("boe_no");
			String boe_dt = request.getParameter("boe_dt")==null?"":request.getParameter("boe_dt");
			
			String contact_person_nm = request.getParameter("contact_person_nm")==null?"0":request.getParameter("contact_person_nm");
			String sug_qty = request.getParameter("sug_qty")==null?"0":request.getParameter("sug_qty");
			String gross_amt_inr = request.getParameter("gross_amt_inr")==null?"":request.getParameter("gross_amt_inr");
			String qty = request.getParameter("qty")==null?"":request.getParameter("qty");
			String price = request.getParameter("price")==null?"":request.getParameter("price");
			String raw_sug = request.getParameter("raw_sug")==null?"":request.getParameter("raw_sug");
			String raw_amt_inr = request.getParameter("raw_amt_inr")==null?"":request.getParameter("raw_amt_inr");
				
			String remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
			String remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
			String remark_3 = request.getParameter("remark_3")==null?"":request.getParameter("remark_3");
			
			String total_tax = request.getParameter("total_tax")==null?"0":request.getParameter("total_tax");
			String net_amt_inr = request.getParameter("net_amt_inr")==null?"0":request.getParameter("net_amt_inr");
			
			String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
			
			remark_1 = obj.replaceSingleQuotes(remark_1);
			remark_2 = obj.replaceSingleQuotes(remark_2);
			remark_3 = obj.replaceSingleQuotes(remark_3);
			
			String hlpl_Inv_Seq_No = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
			String financial_Year = request.getParameter("financial_year")==null?"":request.getParameter("financial_year");
			String customer_Inv_Seq_No = request.getParameter("customer_inv_seq_no")==null?"":request.getParameter("customer_inv_seq_no");
			String activity = request.getParameter("activity")==null?"":request.getParameter("activity");
//			//System.out.println("customer_inv_seq_no"+customer_Inv_Seq_No);
			
			String hlpl_inv_no_without_year [] ={};
			String cust_inv_no_without_year [] ={};
			
			String hlpl_no = "", cust_no = "";
			
			if(hlpl_Inv_Seq_No.contains("/"))
			{
				hlpl_inv_no_without_year = hlpl_Inv_Seq_No.split("/");
				hlpl_no = hlpl_inv_no_without_year[0];
			} else {
				hlpl_no = hlpl_Inv_Seq_No;
			}
			
			if(customer_Inv_Seq_No.contains("/"))
			{
				cust_inv_no_without_year = customer_Inv_Seq_No.split("/");
				cust_no = cust_inv_no_without_year[0];
			} else {
				cust_no = customer_Inv_Seq_No;
			}
			
			String bill_period_end_dt = "", bill_period_start_dt = "";
	 		queryString1 = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
	 		rset = stmt.executeQuery(queryString1);
	 		if(rset.next())
	 		{
	 			bill_period_end_dt = rset.getString(1);
	 		}
	 		
	 		bill_period_start_dt = "01/"+month+"/"+year;
	 		

			/////////////////////////////////////////////////////////////////////////////////////////
			
				
			String q2="SELECT COUNT(*) FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+cargo_no+"' AND " +
			"SN_REV_NO='0' AND FGSA_NO='"+sn_no+"' AND FGSA_REV_NO='"+sn_rev_no+"' " +
			"AND CUSTOMER_CD='"+customer_cd+"' AND "+
			"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
			"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_no)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
			rset3=stmt3.executeQuery(q2);
			int count=0;
			if(rset3.next())
			{
				count=rset3.getInt(1);
			}
			if(count>0)
			{
				String q3="UPDATE DLNG_INVOICE_DEL_LOG SET RESTORE_FLAG='Y' WHERE SN_NO='"+cargo_no+"' AND " +
			"SN_REV_NO='0' AND FGSA_NO='"+sn_no+"' AND FGSA_REV_NO='"+sn_rev_no+"' " +
			"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
			"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
			"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_no)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
				//System.out.println("STEP-2:DLNG_INVOICE_DEL_LOG: "+q3);
				stmt3.executeUpdate(q3);
			}
			
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
				String tax_Structure_Dtl = "";
				String tax_struct_cd = "";
				
				queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
						  "A.customer_cd="+customer_cd+" AND " +
						  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
				 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
				 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
				 		  "B.tax_struct_dt<=TO_DATE('"+invoice_date+"','DD/MM/YYYY'))";
			
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
						tax_struct_cd = rset.getString(2)==null?"0":rset.getString(2);
					}
					else
					{
						tax_Structure_Dtl = "";
						tax_struct_cd = "0";
					}
				
					
					Vector tax_code = new Vector();
					Vector tax_amount = new Vector();
					String tax_cd="";

					queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
			 					  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
			 					  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
//					//System.out.println("STEP-5:FMS7_TAX_STRUCTURE_DTL: For "+customer_abbr+" = "+queryString);
					rset=stmt.executeQuery(queryString);
			 		while(rset.next())
			 		{
			 			tax_cd = rset.getString(1);
			 			tax_code.add(tax_cd);
			 		}
			 		
			 		String tax_flag="";
			 		
			 		for(int i=0; i<tax_code.size(); i++)
			 		{
			 			
			 		queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.firstElement()+"";
//			 			//System.out.println("STEP-6:FMS7_TAX_STRUCTURE_DTL: Tax Name = "+queryString);
			 			rset = stmt.executeQuery(queryString);
			 			if(rset.next())
			 			{
			 				String tax_nm = rset.getString(1).trim()==null?"":rset.getString(1).trim();
			 				
			 				if(tax_nm.contains("VAT"))
			 				{
			 					tax_flag = "V";
			 				}
			 				else if(tax_nm.contains("CST"))
			 				{
			 					tax_flag = "C";
			 				}
			 				else if(tax_nm.contains("ST"))
			 				{
			 					tax_flag = "S";
			 				}
			 				else 
			 				{
			 					tax_flag = "S";
			 				}
			 			}
			 		}
			 		String msg1 = "";
			 		if(activity.equals("insert")) {
				 		boolean flag = fetchMaxInvoiceSeqNo(contract_type,hlpl_no,financial_Year,cust_no,new_inv_seq_no);
			 			if(flag) {
			 				msg1 = "(During Preparation : Invoice Seq No - "+new_inv_seq_no+" ,During Submission Invoice Seq No - "+gst_inv_seq_no_new+")";
			 				hlpl_no = hlpl_inv_seq_no_new;
			 				cust_no = customer_inv_seq_no_new;
			 				new_inv_seq_no = gst_inv_seq_no_new;
			 				
			 			}
					
					queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
							   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
							   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
							   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
							   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
							   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag, " +
							   "INV_AMT_USD, INV_AMT_INR, INV_CUR_FLAG, MAPPING_ID"+ 
							   ",ADV_INV_NO,ADV_INV_DT,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD) " + 
							   "VALUES('"+sn_no+"', '"+sn_rev_no+"', '"+cargo_no+"', '0', '"+customer_cd+"', " +
							   "'"+customer_plant_seq_no+"', '"+contract_type+"', '"+Integer.parseInt(hlpl_no)+"', '"+financial_Year+"', " +
							   "'"+Integer.parseInt(cust_no)+"', TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
							   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), '"+contact_person+"', TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
							   "TO_DATE('"+due_date+"','DD/MM/YYYY'), '"+nf.format(Double.parseDouble(qty))+"', " +
							   "'"+price+"', '"+gross_amt_inr+"', '0', " +
							   "'"+net_amt_inr+"', '"+total_tax+"', '"+tax_struct_cd+"', '"+tax_flag+"', " +
							   "'0', '', '0', " +
							   "'', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '-1', " +
							   "'', '0', '0', "+emp_cd+", TO_DATE(sysdate,'DD/MM/YYYY'), 'U', " +
							   "'0','"+raw_amt_inr+"',''," +
							   "'"+mapping_id+"'" +
							   ",'','','','' ,'' ,'"+new_inv_seq_no+"','1')";
					System.out.println("Insertion Query:=="+queryString1);
					
					stmt.executeUpdate(queryString1);
			 	} else {
			 		queryString1 = "UPDATE DLNG_INVOICE_MST SET PERIOD_START_DT=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), "
			 				+ "PERIOD_END_DT=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), CONTACT_PERSON_CD='"+contact_person+"', "
			 				+ "INVOICE_DT=TO_DATE('"+invoice_date+"','DD/MM/YYYY'), DUE_DT=TO_DATE('"+due_date+"','DD/MM/YYYY'), "
			 				+ "TOTAL_QTY = '"+nf.format(Double.parseDouble(qty))+"', SALE_PRICE='"+price+"',"
			 				+ "GROSS_AMT_INR='"+gross_amt_inr+"', NET_AMT_INR='"+net_amt_inr+"', TAX_AMT_INR='"+total_tax+"', "
			 				+ "TAX_STRUCT_CD='"+tax_struct_cd+"', TAX_FLAG='"+tax_flag+"',"
			 				+ "REMARK_1='"+remark_1+"', REMARK_2='"+remark_2+"', REMARK_3='"+remark_3+"', EMP_CD='"+emp_cd+"', "
			 				+ "ENT_DT=TO_DATE(sysdate,'DD/MM/YYYY'),INV_AMT_INR = '"+raw_amt_inr+"' "
			 				+ ",MAPPING_ID='"+mapping_id+"',NEW_INV_SEQ_NO = '"+new_inv_seq_no+"' WHERE "
			 				+ "HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_no)+"' AND FINANCIAL_YEAR='"+financial_Year+"' "
			 				+ "AND CONTRACT_TYPE='"+contract_type+"' AND FLAG='U' ";
			 		System.out.println("UPDATE Query:=="+queryString1);
			 		stmt.executeUpdate(queryString1);
			 	}
				msg = "SUG Invoice Details of "+new_inv_seq_no+" For "+customer_abbr+" Submitted Successfully!!!"+msg1;
				dbcon.commit();
				
				try
				{
					new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
				}
				catch(Exception infoLogger)
				{
					//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
					infoLogger.printStackTrace();
				}
				
		}
		catch(Exception e)
		{
			try
			{
				dbcon.rollback();
			} catch(Exception e1)
			{
				//System.out.println("Exception in Connection ..."+e);
				e.printStackTrace();
			}
			msg = "SUG Invoice Details Not Submitted!!!";
			//System.out.println("Exception in Frm_Sales_Invoice..While Submitting SUG Invoice Data"+e);
			e.printStackTrace();
		}
		response.sendRedirect("../sales_invoice/frm_sug_invoice_dtl.jsp?selected_map_id="+selected_map_id+"&selected_customer_id="+selected_customer_id+"&index="+index+"&visible_flag=Y&mapping_id="+mapping_id+"&cargo_no="+cargo_no+"&audit_permission="+audit_permission+"&approve_permission="+approve_permission+"&authorize_permission="+authorize_permission+"&check_permission="+check_permission+"&print_permission="+print_permission+"&delete_permission="+delete_permission+"&write_permission="+write_permission+"&year="+year+"&month="+month+"&msg="+msg);
	}
	
	public void insertInvoicePaymentDetails(HttpServletRequest request,HttpServletResponse response) throws IOException	//BK20160309
	{

		String month=request.getParameter("month")==null?"":request.getParameter("month");
		String year=request.getParameter("year")==null?"":request.getParameter("year");
		String to_month=request.getParameter("to_month")==null?"":request.getParameter("to_month");
		String to_year=request.getParameter("to_year")==null?"":request.getParameter("to_year");
		String cust_name=request.getParameter("cust_name")==null?"":request.getParameter("cust_name");
		String cust_cd=request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
		String invstatus=request.getParameter("invstatus")==null?"":request.getParameter("invstatus");
		String segment=request.getParameter("segment")==null?"":request.getParameter("segment");
		String customer_abbr=request.getParameter("cust_name")==null?"":request.getParameter("cust_name");
		
		String formcd=request.getParameter("formcd")==null?"":request.getParameter("formcd");
		String formname=request.getParameter("formname")==null?"":request.getParameter("formname");
		String mid=request.getRemoteHost();
		
		try
		{
			String tcd[]=new String[3];
			String tamt[]=new String[3];
		//	//System.out.println("===in frm===");
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			//String month=request.getParameter("month")==null?"":request.getParameter("month");
			String chk[]=request.getParameterValues("chk");
			String actual_rec[]=request.getParameterValues("actual_rec");
			String payment_rec_date[]=request.getParameterValues("payment_rec_date");
			String remark[]=request.getParameterValues("remarks");
			String hlplinvseqno[]=request.getParameterValues("hlplinvseqno");
			String financialyear[]=request.getParameterValues("financialyear");
			String contracttype[]=request.getParameterValues("contracttype");
			String flgall[]=request.getParameterValues("flgall");
			
			String inv_gen_by_emailid[]=request.getParameterValues("inv_gen_by_emailid");
			String checked_by_emailid[]=request.getParameterValues("checked_by_emailid");
			//String approved_by_emailid[]=request.getParameterValues("approved_by_emailid");
			
			String print_by_emailid[]=request.getParameterValues("print_by_emailid");
			String pdf_inv_dtl[]=request.getParameterValues("pdf_inv_dtl");
			String tds_per[]=request.getParameterValues("tds_per");
			String tds_val[]=request.getParameterValues("tds_val");
			String short_rec[]=request.getParameterValues("short_rec");
			//--Mail--//
			String mailVcustomer_abbr[]=request.getParameterValues("mailVcustomer_abbr");
			String mailVhlpl_inv_seq[]=request.getParameterValues("mailVhlpl_inv_seq");
			String mailVinv_dt[]=request.getParameterValues("mailVinv_dt");
			String mailVsales_value[]=request.getParameterValues("mailVsales_value");
			String mailVtaxcd[]=request.getParameterValues("mailVtaxcd");
			String mailVtaxamt[]=request.getParameterValues("mailVtaxamt");
			String mailVtaxcnt[]=request.getParameterValues("mailVtaxcnt");
			String mailVinv_value[]=request.getParameterValues("mailVinv_value");
			String mailVdue_dt[]=request.getParameterValues("mailVdue_dt");
			String netrec[]=request.getParameterValues("netrec");
			String new_inv_seq_no[]=request.getParameterValues("new_inv_seq_no");
			String flag_type[] = request.getParameterValues("flag_type");
			//--Mail--//
			
			String logged_on_user_nm="";
			String logged_on_user_emailid="";
			
			queryString2="select emp_nm,email_id from hr_emp_mst where emp_cd='"+user_cd+"'";
			rset2=stmt2.executeQuery(queryString2);
			while(rset2.next())
			{
				logged_on_user_nm=rset2.getString(1);
				logged_on_user_emailid=rset2.getString(2);
			}
			
			HashMap m = new HashMap();
			Vector v=new Vector();
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
	        String tsString = ts.toString().substring(0, 19);
	        String tsDate = tsString.substring(0, 10);
	        String time = tsString.substring(11, 19);
	        String tds="";
			for(int i=0;i<hlplinvseqno.length;i++)
			{
				if(flgall[i].equalsIgnoreCase("1"))
				{
					if(segment.equalsIgnoreCase("REGAS")  || segment.equalsIgnoreCase("LTCORA_CN"))
					{
						tds=tds_per[i];
					}
					else
					{
						tds="-";
					}
					
					if(tds.equalsIgnoreCase("-"))
					{
						tds="";
					}
					
					queryString="update DLNG_INVOICE_MST set pay_recv_amt='"+actual_rec[i]+"',pay_recv_dt=to_date('"+payment_rec_date[i]+"','dd/mm/yy'),pay_remark='"+remark[i]+"',pay_insert_by='"+user_cd+"',pay_insert_dt=to_date(sysdate,'dd/mm/yy HH24:MI'),tds_percent='"+tds+"' where hlpl_inv_seq_no='"+hlplinvseqno[i]+"' and financial_year='"+financialyear[i]+"' and contract_type='"+contracttype[i]+"' and flag='"+flag_type[i]+"' ";
					System.out.println("===in update==="+queryString);
					stmt.executeUpdate(queryString);
					
					String temp_no = mailVhlpl_inv_seq[i];
					if(!new_inv_seq_no[i].equals("")) {
						temp_no = new_inv_seq_no[i];
					}
					
					String wholeInvoice=mailVcustomer_abbr[i]+"!@#"+temp_no+"!@#"+mailVinv_dt[i]+"!@#"+mailVsales_value[i]+"!@#"+mailVinv_value[i];
				    	   wholeInvoice+="!@#"+mailVdue_dt[i]+"!@#"+actual_rec[i]+"!@#"+payment_rec_date[i]+"!@#";
				    	   
				    if(!segment.equalsIgnoreCase("SALES"))
				    	wholeInvoice+= netrec[i]+"!@#"+tds_val[i];
				    else
				    	wholeInvoice+= mailVinv_value[i]+"!@#-";
//				      
				      
//					if(!mailVtaxcd[i].equalsIgnoreCase(""))
//					{
//						tcd=mailVtaxcd[i].split("@");
//						tamt=mailVtaxamt[i].split("@");
//					} 
//					if(mailVtaxcnt[i].equalsIgnoreCase("3"))
//					{
//						wholeInvoice+="!@#-!@#-!@#-!@#"+tamt[0]+"!@#-!@#"+tamt[1]+"!@#"+tamt[2]+"!@#-";
//					} else if(mailVtaxcnt[i].equalsIgnoreCase("2")) {
//						if(tcd[0].equalsIgnoreCase("101")) {
//							wholeInvoice+="!@#"+tamt[0]+"!@#"+tamt[1];
//						} else {
//							wholeInvoice+="!@#-!@#-";
//						}
//						wholeInvoice+="!@#-";
//						if(tcd[0].equalsIgnoreCase("102")) {
//							wholeInvoice+="!@#"+tamt[0]+"!@#"+tamt[1];
//						} else {
//							wholeInvoice+="!@#-!@#-";
//						}
//						wholeInvoice+="!@#-!@#-!@#-";
//						
//					} else if(mailVtaxcnt[i].equalsIgnoreCase("1")) {
//						
//						if(tcd[0].equalsIgnoreCase("101")) { 
//							wholeInvoice+="!@#"+tamt[0];
//						} else {
//							wholeInvoice+="!@#-";
//						}
//					
//						if(tcd[0].equalsIgnoreCase("112")) {
//							wholeInvoice+="!@#"+tamt[0];
//						 } else { 
//							 wholeInvoice+="!@#-";
//						 } 
//					
//						if(tcd[0].equalsIgnoreCase("115")) {
//							wholeInvoice+="!@#"+tamt[0];
//						} else {
//							wholeInvoice+="!@#-";
//						} 
//					
//					 if(tcd[0].equalsIgnoreCase("102")) { 
//					wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//					wholeInvoice+="!@#-";
//					 } 
//					
//					 if(tcd[0].equalsIgnoreCase("117")) { 
//					wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//					wholeInvoice+="!@#-";
//					 } 
//					 
//					 if(tcd[0].equalsIgnoreCase("118")) {  //HS20160622
//							wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//						wholeInvoice+="!@#-";
//					} 
//					 if(tcd[0].equalsIgnoreCase("119")) {  
//							wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//						wholeInvoice+="!@#-";
//					} 
//					 if(tcd[0].equalsIgnoreCase("120")) {  
//							wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//						wholeInvoice+="!@#-";
//					} 
//					 if(tcd[0].equalsIgnoreCase("121")) {  
//							wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//						wholeInvoice+="!@#-";
//					} 
//					 if(tcd[0].equalsIgnoreCase("122")) {  
//							wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//						wholeInvoice+="!@#-";
//					} 
//					
//			/*SB20161213		if(tcd[0].equalsIgnoreCase("103")) { 
//					wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//					wholeInvoice+="!@#-";
//					 } 
//					
//					if(tcd[0].equalsIgnoreCase("104")) { 
//					wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//					wholeInvoice+="!@#-";
//					 } 
//					*///Nolonger in use, thus reduce the sequence#
//					 wholeInvoice+="!@#-"; //SB20161213: added to have total 8 no.
//					if(tcd[0].equalsIgnoreCase("105")) { 
//					wholeInvoice+="!@#"+tamt[0];
//					 } else { 
//					wholeInvoice+="!@#-";
//					 } 
//					} 
//					else if(mailVtaxcnt[i].equalsIgnoreCase("0")) {
//						wholeInvoice+="!@#-!@#-!@#-!@#-!@#-!@#-!@#-!@#-";
//					}
					
					String ids="";
					ids+=inv_gen_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(checked_by_emailid[i]))
						ids+="#@#"+checked_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(print_by_emailid[i]) 
							&& !checked_by_emailid[i].equals(print_by_emailid[i]))
						ids+="#@#"+print_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(logged_on_user_emailid) 
							&& !checked_by_emailid[i].equals(logged_on_user_emailid) 
							&& !print_by_emailid[i].equals(logged_on_user_emailid))
						ids+="#@#"+logged_on_user_emailid;
					if(m.containsKey(ids)) {
						String d= (String) m.get(ids);
						d+="@@@"+wholeInvoice;
						m.put(ids,d);
					} else {
						v.addElement(ids);
						m.put(ids,wholeInvoice);
					}
					
				//	//System.out.println("---ids--- "+ids);
					
					String hlpl_inv_no_disp="";
					if(Integer.parseInt(hlplinvseqno[i])<10)
					{
						hlpl_inv_no_disp="000"+hlplinvseqno[i]+"/"+financialyear[i];
					}
					else if(Integer.parseInt(hlplinvseqno[i])<100) 
					{
						hlpl_inv_no_disp="00"+hlplinvseqno[i]+"/"+financialyear[i];
					}
					else if(Integer.parseInt(hlplinvseqno[i])<1000) 
					{
						hlpl_inv_no_disp="0"+hlplinvseqno[i]+"/"+financialyear[i];
					}
					else
					{
						hlpl_inv_no_disp=hlplinvseqno[i]+"/"+financialyear[i];
					}
					String no = hlpl_inv_no_disp;
					
					if(!new_inv_seq_no[i].equals("")) {
						no = new_inv_seq_no[i];
					}
					String remarks="inserted-->"+contracttype[i]+"/"+no+"-->"+ids;
					String q1="insert into SEC_LOG_DETAILS (LOG_DT,LOG_TIME,LOG_UID,REMARKS,EMP_CD,FORM_CD,FORM_NAME,LOG_MACH_ID) " +
						 " VALUES(to_date(sysdate,'dd/mm/yy HH24:MI'),'"+time+"','"+logged_on_user_nm+"','"+remarks+"','"+user_cd+"','"+formcd+"','"+formname+"','"+mid+"') ";
					stmt1.executeUpdate(q1);
				}
			}
			
			HashMap hm =new HashMap();
			Vector v1=new Vector();
			for(int i=0;i<m.size();i++) {
				////System.out.println(i+"--m-"+m.get(v.elementAt(i)));
				String value=m.get(v.elementAt(i)).toString();
				String sd[]=v.elementAt(i).toString().split("#@#");
				for(int j=0;j<sd.length;j++) {
					if(hm.containsKey(sd[j])) {
						String fd=hm.get(sd[j]).toString();
						hm.put(sd[j],fd+"@@@"+value);
					} else {
						v1.add(sd[j]);
						hm.put(sd[j],value);
					}
				}
			}
			
			String mail_list_path="";
			
			File fsetup=new File(request.getRealPath("WEB-INF//classes//com//hlpl//hazira//fms7//sales_invoice//InvoiceSetup.txt"));
			mail_list_path=fsetup.getAbsolutePath();
			
			FileInputStream f1 = new FileInputStream(mail_list_path);
			DataInputStream in = new DataInputStream(f1);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strline="",host="",from_mail="",dbline="",username="",encrypted="",password="";
		
			while((strline = br.readLine())!=null)
			{
				if(strline.startsWith("host"))
				{
					String  tmp[]=strline.split("host:");
					host = tmp[1].toString();
				}
				if(strline.startsWith("from"))
				{
					String  tmp[]=strline.split("from:");
					from_mail = tmp[1].toString();
				}
				if(strline.startsWith("pwd"))
				{
 					String  tmp[]=strline.split("pwd:");
                    encrypted = tmp[1].toString();
                    password=encrypted;
				}
				if(strline.startsWith("dbline"))
				{
					String  tmp[]=strline.split("dbline:");
					dbline = tmp[1].toString();
				}
				if(strline.startsWith("username"))
				{
					String  tmp[]=strline.split("username:");
					username = tmp[1].toString();
				}
				if(strline.startsWith("password"))
				{
                    String  tmp[]=strline.split("password:");
                    encrypted = tmp[1].toString();
                  
				}
			}
			///////////////////////
			
			mail m1=new mail();
			
			boolean actual_rec_flag=false;
			String MailBody="";
			int x=1;
			boolean flg=false;
			for(int i=0;i<hm.size();i++) 
			{
				String Value[] = ((String) hm.get(v1.elementAt(i))).split("@@@");
				
				for(int xx=0;xx<Value.length;xx++) 
				{
					String Invoice[]=Value[xx].split("!@#");
				}
				//#9E9E9E
				
				MailBody="<h4>Dear Recipient(s),</h4><br>We have received payment for the following invoice(s):<br><br><table align='center' bordercolor='blue' width='100%' style='font-size: x-small;' border='1' cellpadding='0' cellspacing='0'><tr bgcolor='green'>";
								
				MailBody+="<th align='center' width='13%'><font size='2' color='white'>INVOICE NUMBER</font></th>" +
						  "<th align='center' width='8%'><font size='2' color='white'>INVOICE DATE</font></th>" +
						  "<th align='center' width='8%'><font size='2' color='white'>DUE DATE</font></th>" +
						  "<th align='center' width='8%'><font size='2' color='white'>NAME OF PARTY</font></th>" +
						  "<th align='center' width='15%'><font size='2' color='white'>INVOICE AMOUNT (INR)</font></th>";	
					
						if(!segment.equalsIgnoreCase("SALES"))
						{
							MailBody+="<th align='center' width='10%'><font size='2' color='white'>TDS VALUE (INR)</font></th>";
						}				
							MailBody+="<th align='center' width='15%'><font size='2' color='white'>NET RECEIVABLE (INR)</font></th>";						
							MailBody+="<th align='center' width='15%'><font size='2' color='white'>ACTUAL RECEIVED (INR)</font></th>" +
							"<th align='center' width='8%'><font size='2' color='white'>PAYMENT RECEIPT DATE</font></th>";					
				MailBody+="</tr>";
				String actrecv="";
				String netrecv="";
				String tdsval="";
//				 abbr-invno-invdt-salesvalue-invvalue-duedt-actrec-payrecdt-netrec-tds
				for(int xx=0;xx<Value.length;xx++) 
				{
					String bg="";
					if(xx%2!=0) 
						bg="#E0E0E0";
					else 
						bg="white";
					MailBody+="<tr bgcolor="+bg+">";
					String Invoice[]=Value[xx].split("!@#");
					
					
					MailBody+="<td align='center' width='13%'><font size='2' color='black'><b>"+Invoice[1]+"</b></font></td>";		//INVOICE NUMBER
					MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+Invoice[2]+"</b></font></td>";		//INVOICE DATE
					
					//DUE DATE
					MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+Invoice[5]+"</b></font></td>";
					
					//NAME OF PARTY
					MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+Invoice[0]+"</b></font></td>";		//NAME OF PARTY
					
					//INVOICE AMOUNT
					MailBody+="<td align='right' width='15%'><font size='2' color='black'><b>"+Invoice[4]+"</b>&nbsp;</font></td>";
					
					//TDS VALUE
					if(!segment.equalsIgnoreCase("SALES"))
					{
						tdsval=nff.format(Double.parseDouble(Invoice[9]));
						MailBody+="<td align='right' width='10%'><font size='2' color='black'><b>"+tdsval+"&nbsp;</b></font></td>";
					}
					
					//NET REC
					if(!Invoice[8].equals("-"))
						netrecv=nff.format(Double.parseDouble(Invoice[8].replaceAll(",", "")));
					
					MailBody+="<td align='right' width='15%'><font size='2' color='black'><b>"+netrecv+"&nbsp;</b></font></td>";	//NET RECEIVABLE			
					
					//ACT REC
					if(!Invoice[6].equals("-"))
						actrecv=nff.format(Double.parseDouble(Invoice[6].replaceAll(",", "")));
					MailBody+="<td align='right' width='15%'><font size='2' color='blue'><b>"+actrecv+"&nbsp;</b></font></td>";	//ACTUAL RECEIVED AMOUNT
					
					MailBody+="<td align='center' width='8%'><font size='2' color='blue'><b>"+Invoice[7]+"</b></font></td>";	//PAYMENT RECEIPT DATE
				}
				
				MailBody+="</tr>";
				flg=true;
				MailBody+="</table><br><br>";
				MailBody+="Please maintain confidentiality.<br> NOTE: This notification has been auto-generated - PLEASE DO NOT REPLY. <br>If you have any queries contact finance.";
				MailBody+="<br><br><font style='font-size: x-small'>Thanks</font><br><font style='font-size: xx-small; color: gray'>- This is System Generated Email.</font>";
				if(!v1.elementAt(i).toString().equalsIgnoreCase("-"))
				{
					if(segment.equalsIgnoreCase("REGAS"))
					{
						x *=m1.sendMail1("Regas Payment Receipt Confirmation",MailBody,"",v1.elementAt(i).toString(),from_mail,from_mail,"",host,from_mail,password,false);
					}
					else if(segment.equalsIgnoreCase("SALES"))
					{
						x *=m1.sendMail1("Sales Payment Receipt Confirmation",MailBody,"",v1.elementAt(i).toString(),from_mail,from_mail,"",host,from_mail,password,false);
					}
					else if(segment.equalsIgnoreCase("LTCORA_CN"))
					{
						x *=m1.sendMail1("LTCORA & CN Payment Receipt Confirmation",MailBody,"",v1.elementAt(i).toString(),from_mail,from_mail,"",host,from_mail,password,false);
					}
				}
			}
			if(x==1 && flg) 
			{
				 dbcon.commit(); //HS20160809
				 msg="Data Submitted Successfully..!";
			} 
			else
			{	
				dbcon.rollback();
				if(x==2 && flg) 
					msg="ERR in Sending Email..!";
				else
					msg="ERR in Submission..!";
			}	
		}
		catch(Exception e)
		{
			try {
				dbcon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			msg="Data Not Submitted..!";
		}
		try {
			response.sendRedirect("../sales_invoice/frm_invoice_payment_dtl.jsp?year="+year+"&month="+month+"&to_year="+to_year+"&to_month="+to_month+"&segment="+segment+"&invstatus="+invstatus+"&cust_name="+cust_name+"&customer_cd="+cust_cd+"&msg="+msg+"&btnflag=Y&customer_abbr="+customer_abbr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateInvoicePaymentDetails(HttpServletRequest request,HttpServletResponse response) throws IOException	//BK20160309
	{
		String month=request.getParameter("month")==null?"":request.getParameter("month");
		String year=request.getParameter("year")==null?"":request.getParameter("year");
		String to_month=request.getParameter("to_month")==null?"":request.getParameter("to_month");
		String to_year=request.getParameter("to_year")==null?"":request.getParameter("to_year");
		String cust_name=request.getParameter("cust_name")==null?"":request.getParameter("cust_name");
		String cust_cd=request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
		String invstatus=request.getParameter("invstatus")==null?"":request.getParameter("invstatus");
		String segment=request.getParameter("segment")==null?"":request.getParameter("segment");
		String customer_abbr=request.getParameter("cust_name")==null?"":request.getParameter("cust_name");
		
		String formcd=request.getParameter("formcd")==null?"":request.getParameter("formcd");
		String formname=request.getParameter("formname")==null?"":request.getParameter("formname");
		String mid=request.getRemoteHost();

//		//System.out.println("update---FRM---formcd--- "+formcd);
//		//System.out.println("update---FRM---formname--- "+formname);
//		//System.out.println("update---FRM---mid--- "+mid);

		try
		{
			String tcd[]=new String[3];
			String tamt[]=new String[3];
//			//System.out.println("===in frm===");
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			//String month=request.getParameter("month")==null?"":request.getParameter("month");
			String chk[]=request.getParameterValues("chk");
			String actual_rec[]=request.getParameterValues("actual_rec");
			String payment_rec_date[]=request.getParameterValues("payment_rec_date");
			String remark[]=request.getParameterValues("remarks");
			String hlplinvseqno[]=request.getParameterValues("hlplinvseqno");
			String financialyear[]=request.getParameterValues("financialyear");
			String contracttype[]=request.getParameterValues("contracttype");
			String flgall[]=request.getParameterValues("flgall");
			
			String inv_gen_by_emailid[]=request.getParameterValues("inv_gen_by_emailid");
			String checked_by_emailid[]=request.getParameterValues("checked_by_emailid");
			//String approved_by_emailid[]=request.getParameterValues("approved_by_emailid");
			
			String print_by_emailid[]=request.getParameterValues("print_by_emailid");
			String pdf_inv_dtl[]=request.getParameterValues("pdf_inv_dtl");
			String tds_per[]=request.getParameterValues("tds_per");
			
			String short_rec[]=request.getParameterValues("short_rec");
			String tds_val[]=request.getParameterValues("tds_val");
			
			//--Mail--//
			String mailVcustomer_abbr[]=request.getParameterValues("mailVcustomer_abbr");
			String mailVhlpl_inv_seq[]=request.getParameterValues("mailVhlpl_inv_seq");
			String mailVinv_dt[]=request.getParameterValues("mailVinv_dt");
			String mailVsales_value[]=request.getParameterValues("mailVsales_value");
			String mailVtaxcd[]=request.getParameterValues("mailVtaxcd");
			String mailVtaxamt[]=request.getParameterValues("mailVtaxamt");
			String mailVtaxcnt[]=request.getParameterValues("mailVtaxcnt");
			String mailVinv_value[]=request.getParameterValues("mailVinv_value");
			String mailVdue_dt[]=request.getParameterValues("mailVdue_dt");
			String netrec[]=request.getParameterValues("netrec");
			String new_inv_seq_no[]=request.getParameterValues("new_inv_seq_no");
			String flag_type[]=request.getParameterValues("flag_type");
			//--Mail--//
			
			String logged_on_user_nm="";
			String logged_on_user_emailid="";
			
			queryString2="select emp_nm,email_id from hr_emp_mst where emp_cd='"+user_cd+"'";
			rset2=stmt2.executeQuery(queryString2);
			while(rset2.next())
			{
				logged_on_user_nm=rset2.getString(1);
				logged_on_user_emailid=rset2.getString(2);
			}
			
			////System.out.println("---logged_on_user_nm---"+logged_on_user_nm);
			////System.out.println("---logged_on_user_emailid---"+logged_on_user_emailid);
			HashMap m = new HashMap();
			Vector v=new Vector();
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
	        String tsString = ts.toString().substring(0, 19);
	        String tsDate = tsString.substring(0, 10);
	        String time = tsString.substring(11, 19);
	        String tds="";
	        
			for(int i=0;i<hlplinvseqno.length;i++)
			{
				if(flgall[i].equalsIgnoreCase("1"))
				{
					if(segment.equalsIgnoreCase("REGAS") || segment.equalsIgnoreCase("LTCORA_CN"))
					{
						tds=tds_per[i];
					}
					else
					{
						tds="-";
					}
					
					if(tds.equalsIgnoreCase("-"))
					{
						tds="";
					}
					
					
					int updcnt=0;
					queryString1="select pay_update_cnt from DLNG_INVOICE_MST where hlpl_inv_seq_no='"+hlplinvseqno[i]+"' and financial_year='"+financialyear[i]+"' and contract_type='"+contracttype[i]+"' and flag='"+flag_type[i]+"'";
//					//System.out.println("---queryString1---: "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						if(rset1.getString(1)==null)
						{
							updcnt=1;
						}
						else
						{
							updcnt=Integer.parseInt(rset1.getString(1))+1;
						}
					}
					
					queryString="update DLNG_INVOICE_MST set pay_recv_amt='"+actual_rec[i]+"',pay_update_dt=to_date(sysdate,'dd/mm/yy HH24:MI'),pay_remark='"+remark[i]+"',pay_update_by='"+user_cd+"',tds_percent='"+tds+"',pay_update_cnt='"+updcnt+"' where hlpl_inv_seq_no='"+hlplinvseqno[i]+"' and financial_year='"+financialyear[i]+"' and contract_type='"+contracttype[i]+"' and flag='"+flag_type[i]+"'";
//					//System.out.println("---update payment query---"+queryString);
					stmt.executeUpdate(queryString);
					
					String temp_no = mailVhlpl_inv_seq[i];
					if(!new_inv_seq_no[i].equals("")) {
						temp_no = new_inv_seq_no[i];
					}
					
					String wholeInvoice=mailVcustomer_abbr[i]+"!@#"+temp_no+"!@#"+mailVinv_dt[i]+"!@#"+mailVsales_value[i];
					if(!mailVtaxcd[i].equalsIgnoreCase(""))
					{
						tcd=mailVtaxcd[i].split("@");
						tamt=mailVtaxamt[i].split("@");
					} 
					if(mailVtaxcnt[i].equalsIgnoreCase("3"))
					{
						wholeInvoice+="!@#-!@#-!@#-!@#"+tamt[0]+"!@#-!@#"+tamt[1]+"!@#"+tamt[2]+"!@#-";
					} else if(mailVtaxcnt[i].equalsIgnoreCase("2")) {
						if(tcd[0].equalsIgnoreCase("101")) {
							wholeInvoice+="!@#"+tamt[0]+"!@#"+tamt[1];
						} else {
							wholeInvoice+="!@#-!@#-";
						}
						wholeInvoice+="!@#-";
						if(tcd[0].equalsIgnoreCase("102")) {
							wholeInvoice+="!@#"+tamt[0]+"!@#"+tamt[1];
						} else {
							wholeInvoice+="!@#-!@#-";
						}
						wholeInvoice+="!@#-!@#-!@#-";
						
					} else if(mailVtaxcnt[i].equalsIgnoreCase("1")) {
						
						if(tcd[0].equalsIgnoreCase("101")) { 
							wholeInvoice+="!@#"+tamt[0];
						} else {
							wholeInvoice+="!@#-";
						}
					
						if(tcd[0].equalsIgnoreCase("112")) {
							wholeInvoice+="!@#"+tamt[0];
						 } else { 
							 wholeInvoice+="!@#-";
						 } 
					
						if(tcd[0].equalsIgnoreCase("115")) {
							wholeInvoice+="!@#"+tamt[0];
						} else {
							wholeInvoice+="!@#-";
						} 
					
					 if(tcd[0].equalsIgnoreCase("102")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					
					 if(tcd[0].equalsIgnoreCase("117")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					 
					 if(tcd[0].equalsIgnoreCase("118")) {  //HS20160622
							wholeInvoice+="!@#"+tamt[0];
					 } else { 
						wholeInvoice+="!@#-";
					}
					 if(tcd[0].equalsIgnoreCase("119")) {  //HS20160622
							wholeInvoice+="!@#"+tamt[0];
					 } else { 
						wholeInvoice+="!@#-";
					}
					 if(tcd[0].equalsIgnoreCase("120")) {  //HS20160622
							wholeInvoice+="!@#"+tamt[0];
					 } else { 
						wholeInvoice+="!@#-";
					}
					 if(tcd[0].equalsIgnoreCase("121")) {  //HS20160622
							wholeInvoice+="!@#"+tamt[0];
					 } else { 
						wholeInvoice+="!@#-";
					}
					 if(tcd[0].equalsIgnoreCase("122")) {  //HS20160622
							wholeInvoice+="!@#"+tamt[0];
					 } else { 
						wholeInvoice+="!@#-";
					}
					
					if(tcd[0].equalsIgnoreCase("103")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					
					if(tcd[0].equalsIgnoreCase("104")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					
					if(tcd[0].equalsIgnoreCase("105")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					} else if(mailVtaxcnt[i].equalsIgnoreCase("0")) {
						wholeInvoice+="!@#-!@#-!@#-!@#-!@#-!@#-!@#-!@#-";
					}
					wholeInvoice+="!@#"+mailVinv_value[i];
					       if(contracttype[0].equals("R")){
					    	   wholeInvoice+="!@#-!@#-";
					       }
					       
					if(!segment.equalsIgnoreCase("SALES"))
					{
					   wholeInvoice+="!@#"+mailVdue_dt[i]+"!@#"+actual_rec[i]+"!@#"+short_rec[i]+"!@#"+payment_rec_date[i]+"!@#"+remark[i]+"!@#"+netrec[i]+"!@#"+tds_val[i];
					}
					else
					{
					   wholeInvoice+="!@#"+mailVdue_dt[i]+"!@#"+actual_rec[i]+"!@#"+short_rec[i]+"!@#"+payment_rec_date[i]+"!@#"+remark[i];
					}
					       
					String ids="";
					ids+=inv_gen_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(checked_by_emailid[i]))
						ids+="#@#"+checked_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(print_by_emailid[i]) 
							&& !checked_by_emailid[i].equals(print_by_emailid[i]))
						ids+="#@#"+print_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(logged_on_user_emailid) 
							&& !checked_by_emailid[i].equals(logged_on_user_emailid) 
							&& !print_by_emailid[i].equals(logged_on_user_emailid))
						ids+="#@#"+logged_on_user_emailid;
					if(m.containsKey(ids)) {
						String d= (String) m.get(ids);
						d+="@@@"+wholeInvoice;
						m.put(ids,d);
					} else {
						v.addElement(ids);
						m.put(ids,wholeInvoice);
					}
					
					String hlpl_inv_no_disp="";
					if(Integer.parseInt(hlplinvseqno[i])<10)
					{
						hlpl_inv_no_disp="000"+hlplinvseqno[i]+"/"+financialyear[i];
					}
					else if(Integer.parseInt(hlplinvseqno[i])<100) 
					{
						hlpl_inv_no_disp="00"+hlplinvseqno[i]+"/"+financialyear[i];
					}
					else if(Integer.parseInt(hlplinvseqno[i])<1000) 
					{
						hlpl_inv_no_disp="0"+hlplinvseqno[i]+"/"+financialyear[i];
					}
					else
					{
						hlpl_inv_no_disp=hlplinvseqno[i]+"/"+financialyear[i];
					}
					
					String no = hlpl_inv_no_disp;
					if(!new_inv_seq_no[i].equals("")) {
						no = new_inv_seq_no[i];
					}
					
					String remarks="updated-->"+contracttype[i]+"/"+no+"-->"+ids;
					String q1="insert into SEC_LOG_DETAILS (LOG_DT,LOG_TIME,LOG_UID,REMARKS,EMP_CD,FORM_CD,FORM_NAME,LOG_MACH_ID) " +
						 " VALUES(to_date(sysdate,'dd/mm/yy HH24:MI'),'"+time+"','"+logged_on_user_nm+"','"+remarks+"','"+user_cd+"','"+formcd+"','"+formname+"','"+mid+"') ";
					stmt1.executeUpdate(q1);
				}
			}
			
			//System.out.println(m);
			HashMap hm =new HashMap();
			Vector v1=new Vector();
			for(int i=0;i<m.size();i++) {
				////System.out.println(i+"--m-"+m.get(v.elementAt(i)));
				String value=m.get(v.elementAt(i)).toString();
				String sd[]=v.elementAt(i).toString().split("#@#");
				for(int j=0;j<sd.length;j++) {
					if(hm.containsKey(sd[j])) {
						String fd=hm.get(sd[j]).toString();
						hm.put(sd[j],fd+"@@@"+value);
					} else {
						v1.add(sd[j]);
						hm.put(sd[j],value);
					}
				}
			}
			
			String mail_list_path="";
			
			File fsetup=new File(request.getRealPath("WEB-INF//classes//com//hlpl//hazira//fms7//sales_invoice//InvoiceSetup.txt"));
			mail_list_path=fsetup.getAbsolutePath();
			
			FileInputStream f1 = new FileInputStream(mail_list_path);
			DataInputStream in = new DataInputStream(f1);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strline="",host="",from_mail="",dbline="",username="",encrypted="",password="";
		
			while((strline = br.readLine())!=null)
			{
				if(strline.startsWith("host"))
				{
					String  tmp[]=strline.split("host:");
					host = tmp[1].toString();
				}
				if(strline.startsWith("from"))
				{
					String  tmp[]=strline.split("from:");
					from_mail = tmp[1].toString();
				}
				if(strline.startsWith("pwd"))
				{
					String  tmp[]=strline.split("pwd:");
			        encrypted = tmp[1].toString();
			        password=encrypted;
				}
				if(strline.startsWith("dbline"))
				{
					String  tmp[]=strline.split("dbline:");
					dbline = tmp[1].toString();
				}
				if(strline.startsWith("username"))
				{
					String  tmp[]=strline.split("username:");
					username = tmp[1].toString();
				}
				if(strline.startsWith("password"))
				{
		          String  tmp[]=strline.split("password:");
		          encrypted = tmp[1].toString();
				}
			}
			
			mail m1=new mail();
			
			boolean actual_rec_flag=false;
			String MailBody="";
			int x=1;
			boolean flg=true;
//			for(int i=0;i<hm.size();i++) 
//			{
//				String Value[] = ((String) hm.get(v1.elementAt(i))).split("@@@");
//				
//				for(int xx=0;xx<Value.length;xx++) 
//				{
//					String Invoice[]=Value[xx].split("!@#");
//				}
//				//#9E9E9E
//				
//				MailBody="<h4>Dear Recipient(s),</h4><br>We have updated payment for the following invoice(s):<br><br><table align='center' bordercolor='blue' width='100%' style='font-size: x-small;' border='1' cellpadding='0' cellspacing='0'><tr bgcolor='orange'>";
//								
//				MailBody+="<th align='center' width='13%'><font size='2' color='white'>INVOICE NUMBER</font></th>" +
//						  "<th align='center' width='8%'><font size='2' color='white'>INVOICE DATE</font></th>" +
//						  "<th align='center' width='8%'><font size='2' color='white'>DUE DATE</font></th>" +
//						  "<th align='center' width='8%'><font size='2' color='white'>NAME OF PARTY</font></th>" +
//						  "<th align='center' width='15%'><font size='2' color='white'>INVOICE AMOUNT (INR)</font></th>";	
//					
//						if(!segment.equalsIgnoreCase("SALES"))
//						{
//							MailBody+="<th align='center' width='10%'><font size='2' color='white'>TDS VALUE (INR)</font></th>";
//						}
//				
//						//if(!segment.equalsIgnoreCase("SALES"))
//						//{
//							MailBody+="<th align='center' width='15%'><font size='2' color='white'>NET RECEIVABLE (INR)</font></th>";
//						//}
//						
//					MailBody+="<th align='center' width='15%'><font size='2' color='white'>ACTUAL RECEIVED (INR)</font></th>" +
//							"<th align='center' width='8%'><font size='2' color='white'>PAYMENT RECEIPT DATE</font></th>";	
//							
//				MailBody+="</tr>";
//				String actrecv="";
//				String netrecv="";
//				String tdsval="";
//				for(int xx=0;xx<Value.length;xx++) 
//				{
//					String bg="";
//					if(xx%2!=0) 
//						bg="#E0E0E0";
//					else 
//						bg="white";
//					MailBody+="<tr bgcolor="+bg+">";
//					String Invoice[]=Value[xx].split("!@#");
//					
//					MailBody+="<td align='center' width='13%'><font size='2' color='black'><b>"+Invoice[1]+"</b></font></td>";		//INVOICE NUMBER
//					MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+Invoice[2]+"</b></font></td>";		//INVOICE DATE
//					
//					//DUE DATE
//					if(segment.equalsIgnoreCase("REGAS"))
//					{
//						MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+Invoice[18]+"</b></font></td>";
//					}
//					else
//					{
//						MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+Invoice[18]+"</b></font></td>";
//					}
//					
//					//NAME OF PARTY
//					MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+Invoice[0]+"</b></font></td>";		//NAME OF PARTY
//					
//					//INVOICE AMOUNT
//					if(segment.equalsIgnoreCase("REGAS"))
//					{
//						MailBody+="<td align='right' width='15%'><font size='2' color='black'><b>"+Invoice[17]+"</b>&nbsp;</font></td>";
//					}
//					else
//					{
//						MailBody+="<td align='right' width='15%'><font size='2' color='black'><b>"+Invoice[17]+"</b>&nbsp;</font></td>";		
//					}
//					
//					//TDS VALUE
//					if(segment.equalsIgnoreCase("REGAS"))
//					{
//						//tdsval=nff.format(Double.parseDouble(Invoice[19]));
//						tdsval=nff.format(Double.parseDouble(Invoice[24]));
//						MailBody+="<td align='right' width='10%'><font size='2' color='black'><b>"+tdsval+"&nbsp;</b></font></td>";
//					}
//					else
//					{
//						if(segment.equalsIgnoreCase("LTCORA_CN"))
//						{
//							tdsval=nff.format(Double.parseDouble(Invoice[24]));
//							MailBody+="<td align='right' width='10%'><font size='2' color='black'><b>"+tdsval+"&nbsp;</b></font></td>";
//						}
//					}
//					
//					if(segment.equalsIgnoreCase("REGAS"))
//					{
//						actrecv=nff.format(Double.parseDouble(Invoice[19]));
//						//actrecv=nff.format(Double.parseDouble(Invoice[14]));
//						//netrecv=nff.format(Double.parseDouble(Invoice[18]));
//						netrecv=nff.format(Double.parseDouble(Invoice[23]));
//					}
//					else
//					{
//						if(!Invoice[19].equals("-"))
//							actrecv=nff.format(Double.parseDouble(Invoice[19].replaceAll(",", "")));
//						else
//							actrecv=Invoice[19];
//						if(segment.equalsIgnoreCase("LTCORA_CN"))
//						{
//							netrecv=nff.format(Double.parseDouble(Invoice[23].replaceAll(",", "")));
//						}
//						else
//						{
//							netrecv=Invoice[23];
//						}
//					}
//					
//					//if(!segment.equalsIgnoreCase("SALES"))
//					{
//						MailBody+="<td align='right' width='15%'><font size='2' color='black'><b>"+netrecv+"&nbsp;</b></font></td>";	//NET RECEIVABLE
//					}
//					
//					MailBody+="<td align='right' width='15%'><font size='2' color='blue'><b>"+actrecv+"&nbsp;</b></font></td>";	//ACTUAL RECEIVED AMOUNT
//					
//					if(segment.equalsIgnoreCase("REGAS"))
//					{
//						MailBody+="<td align='center' width='8%'><font size='2' color='blue'><b>"+Invoice[18]+"</b></font></td>";	//PAYMENT RECEIPT DATE
//					}
//					else
//					{
//						MailBody+="<td align='center' width='8%'><font size='2' color='blue'><b>"+Invoice[18]+"</b></font></td>";	//PAYMENT RECEIPT DATE
//					}
//				}
//				
//				MailBody+="</tr>";
//				flg=true;
//				MailBody+="</table><br><br>";
//				MailBody+="Please maintain confidentiality.<br> NOTE: This notification has been auto-generated - PLEASE DO NOT REPLY. <br>If you have any queries contact finance.";
//				MailBody+="<br><br><font style='font-size: x-small'>Thanks</font><br><font style='font-size: xx-small; color: gray'>- This is System Generated Email.</font>";
//				System.out.println("---MailBody--- "+MailBody.replaceAll("<br>", "\n"));
//				if(!v1.elementAt(i).toString().equalsIgnoreCase("-"))
//				{
//					if(segment.equalsIgnoreCase("REGAS"))
//					{
//						//UN-COMMENT THIS LINE TO ENABLE EMAIL		//BK20160308
//						//x *=m1.sendMail1("Regas Payment Update Confirmation",MailBody,"",v1.elementAt(i).toString(),from_mail,from_mail,"",host,from_mail,password,false);
//					}
//					else if(segment.equalsIgnoreCase("SALES"))
//					{
//						//UN-COMMENT THIS LINE TO ENABLE EMAIL		//BK20160308
//						//x *=m1.sendMail1("Sales Payment Update Confirmation",MailBody,"",v1.elementAt(i).toString(),from_mail,from_mail,"",host,from_mail,password,false);
//					}
//					else if(segment.equalsIgnoreCase("LTCORA_CN"))
//					{
//						//UN-COMMENT THIS LINE TO ENABLE EMAIL		//BK20160308
//						//x *=m1.sendMail1("LTCORA & CN Payment Update Confirmation",MailBody,"",v1.elementAt(i).toString(),from_mail,from_mail,"",host,from_mail,password,false);
//					}
//				}
//				////System.out.println("x------"+x);
//			}
			
			if(x==1 && flg) 
			{
				dbcon.commit();
			} 
			else
			{
				dbcon.rollback();
			}	

			msg="Data Updated Successfully..!";
		}
		catch(Exception e)
		{
			try {
				dbcon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			//System.out.println("Exception in updateInvoicePaymentDetails() "+e);
			e.printStackTrace();
			msg="Data Not Updated..!";
		}
		//response.sendRedirect( "../sales_invoice/frm_invoice_payment_dtl.jsp?year=2016&month=02&to_year=2016&to_month=02&segment=SALES&btnflag=Y";
		try {
			response.sendRedirect("../sales_invoice/frm_update_invoice_payment_dtl.jsp?year="+year+"&month="+month+"&to_year="+to_year+"&to_month="+to_month+"&segment="+segment+"&invstatus="+invstatus+"&cust_name="+cust_name+"&customer_cd="+cust_cd+"&msg="+msg+"&btnflag=Y&customer_abbr="+customer_abbr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void insertInvoicePaymentDetails_old_20160302(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String month=request.getParameter("month")==null?"":request.getParameter("month");
		String year=request.getParameter("year")==null?"":request.getParameter("year");
		String to_month=request.getParameter("to_month")==null?"":request.getParameter("to_month");
		String to_year=request.getParameter("to_year")==null?"":request.getParameter("to_year");
		String cust_name=request.getParameter("cust_name")==null?"":request.getParameter("cust_name");
		String cust_cd=request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
		String invstatus=request.getParameter("invstatus")==null?"":request.getParameter("invstatus");
		String segment=request.getParameter("segment")==null?"":request.getParameter("segment");
		String customer_abbr=request.getParameter("cust_name")==null?"":request.getParameter("cust_name");
		
		try
		{
			String tcd[]=new String[3];
			String tamt[]=new String[3];
			//System.out.println("===in frm===");
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			//String month=request.getParameter("month")==null?"":request.getParameter("month");
			String chk[]=request.getParameterValues("chk");
			String actual_rec[]=request.getParameterValues("actual_rec");
			String payment_rec_date[]=request.getParameterValues("payment_rec_date");
			String remark[]=request.getParameterValues("remarks");
			String hlplinvseqno[]=request.getParameterValues("hlplinvseqno");
			String financialyear[]=request.getParameterValues("financialyear");
			String contracttype[]=request.getParameterValues("contracttype");
			String flgall[]=request.getParameterValues("flgall");
			
			String inv_gen_by_emailid[]=request.getParameterValues("inv_gen_by_emailid");
			String checked_by_emailid[]=request.getParameterValues("checked_by_emailid");
			String approved_by_emailid[]=request.getParameterValues("approved_by_emailid");
			String short_rec[]=request.getParameterValues("short_rec");
			//--Mail--//
			String mailVcustomer_abbr[]=request.getParameterValues("mailVcustomer_abbr");
			String mailVhlpl_inv_seq[]=request.getParameterValues("mailVhlpl_inv_seq");
			String mailVinv_dt[]=request.getParameterValues("mailVinv_dt");
			String mailVsales_value[]=request.getParameterValues("mailVsales_value");
			String mailVtaxcd[]=request.getParameterValues("mailVtaxcd");
			String mailVtaxamt[]=request.getParameterValues("mailVtaxamt");
			String mailVtaxcnt[]=request.getParameterValues("mailVtaxcnt");
			String mailVinv_value[]=request.getParameterValues("mailVinv_value");
			String mailVdue_dt[]=request.getParameterValues("mailVdue_dt");
			
			//--Mail--//
			
			
			String logged_on_user_nm="";
			String logged_on_user_emailid="";
			
			queryString2="select emp_nm,email_id from hr_emp_mst where emp_cd='"+user_cd+"'";
			rset2=stmt2.executeQuery(queryString2);
			while(rset2.next())
			{
				logged_on_user_nm=rset2.getString(1);
				logged_on_user_emailid=rset2.getString(2);
			}
			//System.out.println("---logged_on_user_nm---"+logged_on_user_nm);
			//System.out.println("---logged_on_user_emailid---"+logged_on_user_emailid);
			HashMap m = new HashMap();
			Vector v=new Vector();
			
			
			for(int i=0;i<hlplinvseqno.length;i++)
			{
				if(flgall[i].equalsIgnoreCase("1"))
				{
					//System.out.println("---inv_gen_by_emailid---: "+inv_gen_by_emailid[i]);
					//System.out.println("---checked_by_emailid---: "+checked_by_emailid[i]);
					//System.out.println("---approved_by_emailid---: "+approved_by_emailid[i]);
					queryString="update DLNG_INVOICE_MST set pay_recv_amt='"+actual_rec[i]+"',pay_recv_dt=to_date('"+payment_rec_date[i]+"','dd/mm/yy'),pay_remark='"+remark[i]+"',pay_insert_by='"+user_cd+"',pay_insert_dt=to_date(sysdate,'dd/mm/yy HH24:MI') where hlpl_inv_seq_no='"+hlplinvseqno[i]+"' and financial_year='"+financialyear[i]+"' and contract_type='"+contracttype[i]+"'";
					//System.out.println("---update payment query---"+queryString);
					stmt.executeUpdate(queryString);
					
					String wholeInvoice=mailVcustomer_abbr[i]+"!@#"+mailVhlpl_inv_seq[i]+"!@#"+mailVinv_dt[i]+"!@#"+mailVsales_value[i];
					if(!mailVtaxcd[i].equalsIgnoreCase(""))
					{
						tcd=mailVtaxcd[i].split("@");
						tamt=mailVtaxamt[i].split("@");
					} 
					if(mailVtaxcnt[i].equalsIgnoreCase("3"))
					{
						wholeInvoice+="!@#-!@#-!@#-!@#"+tamt[0]+"!@#-!@#"+tamt[1]+"!@#"+tamt[2]+"!@#-";
					} else if(mailVtaxcnt[i].equalsIgnoreCase("2")) {
						if(tcd[0].equalsIgnoreCase("101")) {
							wholeInvoice+="!@#"+tamt[0]+"!@#"+tamt[1];
						} else {
							wholeInvoice+="!@#-!@#-";
						}
						wholeInvoice+="!@#-";
						if(tcd[0].equalsIgnoreCase("102")) {
							wholeInvoice+="!@#"+tamt[0]+"!@#"+tamt[1];
						} else {
							wholeInvoice+="!@#-!@#-";
						}
						wholeInvoice+="!@#-!@#-!@#-";
						
					} else if(mailVtaxcnt[i].equalsIgnoreCase("1")) {
						
						if(tcd[0].equalsIgnoreCase("101")) { 
							wholeInvoice+="!@#"+tamt[0];
						} else {
							wholeInvoice+="!@#-";
						}
					
						if(tcd[0].equalsIgnoreCase("112")) {
							wholeInvoice+="!@#"+tamt[0];
						 } else { 
							 wholeInvoice+="!@#-";
						 } 
					
						if(tcd[0].equalsIgnoreCase("115")) {
							wholeInvoice+="!@#"+tamt[0];
						} else {
							wholeInvoice+="!@#-";
						} 
					
					 if(tcd[0].equalsIgnoreCase("102")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					
					 if(tcd[0].equalsIgnoreCase("117")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					 
					 if(tcd[0].equalsIgnoreCase("118")) {  //HS20160622
							wholeInvoice+="!@#"+tamt[0];
					 } else { 
						wholeInvoice+="!@#-";
					}
					
					if(tcd[0].equalsIgnoreCase("103")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					
					if(tcd[0].equalsIgnoreCase("104")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					
					if(tcd[0].equalsIgnoreCase("105")) { 
					wholeInvoice+="!@#"+tamt[0];
					 } else { 
					wholeInvoice+="!@#-";
					 } 
					} else if(mailVtaxcnt[i].equalsIgnoreCase("0")) {
						wholeInvoice+="!@#-!@#-!@#-!@#-!@#-!@#-!@#-!@#-";
					}
					wholeInvoice+="!@#"+mailVinv_value[i];
					       if(contracttype[0].equals("R")){
					    	   wholeInvoice+="!@#-!@#-";
					       }
					wholeInvoice+="!@#"+mailVdue_dt[i]+"!@#"+actual_rec[i]+"!@#"+short_rec[i]+"!@#"+payment_rec_date[i]+"!@#"+remark[i];
					String ids="";
					ids+=inv_gen_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(checked_by_emailid[i]))
						ids+="#@#"+checked_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(approved_by_emailid[i]) 
							&& !checked_by_emailid[i].equals(approved_by_emailid[i]))
						ids+="#@#"+approved_by_emailid[i];
					if(!inv_gen_by_emailid[i].equals(logged_on_user_emailid) 
							&& !checked_by_emailid[i].equals(logged_on_user_emailid) 
							&& !approved_by_emailid[i].equals(logged_on_user_emailid))
						ids+="#@#"+logged_on_user_emailid;
					if(m.containsKey(ids)) {
						String d= (String) m.get(ids);
						d+="@@@"+wholeInvoice;
						m.put(ids,d);
					} else {
						v.addElement(ids);
						m.put(ids,wholeInvoice);
					}
				}
			}
			//System.out.println(m);
			HashMap hm =new HashMap();
			Vector v1=new Vector();
			for(int i=0;i<m.size();i++) {
				//System.out.println(i+"--m-"+m.get(v.elementAt(i)));
				String value=m.get(v.elementAt(i)).toString();
				String sd[]=v.elementAt(i).toString().split("#@#");
				for(int j=0;j<sd.length;j++) {
					if(hm.containsKey(sd[j])) {
						String fd=hm.get(sd[j]).toString();
						hm.put(sd[j],fd+"@@@"+value);
					} else {
						v1.add(sd[j]);
						hm.put(sd[j],value);
					}
				}
			}
			//System.out.println("hm---"+hm);
			//System.out.println("v1---"+v1);
			mail m1=new mail();
			for (int i=0;i<hm.size();i++) {
				String Value[] = ((String) hm.get(v1.elementAt(i))).split("@@@");
				
				String MailBody="<h3>Hello,</h3><br><table bordercolor='gray' style='font-size: x-small;' border='1' cellpadding='0' cellspacing='1'><tr bgcolor='#9E9E9E'>" +
								"<th>NAME OF PARTY</th>" +
								"<th>INVOICE NUMBER</th>" +
								"<th>INVOICE DATE</th>";
				if(contracttype[0].equals("R")) {
					MailBody+="<th>SERVICE VALUE (INR)</th>";
				} else{
					MailBody+="<th>SALES VALUE (INR)</th>";
				}
					MailBody+="<th>VAT</th>" +
								"<th>ADD. VAT</th>" +
								"<th>ZVAT</th>" +
								"<th>ST</th>" +
								"<th>SBC</th>" +
								"<th>ECS</th>" +
								"<th>HECS</th>" +
								"<th>CST</th>" +
								"<th>TOTAL INVOICE VALUE (INR)</th>";
				if(contracttype[0].equals("R")) {
					MailBody+="<th>TDS VALUE</th>" +
							"<th>NET RECEIVABLE</th>";
				}
				MailBody+="<th>DUE DATE</th>" +
								"<th>ACTUAL RECEIVED (INR)</th>" +
								"<th>SHORT RECEIVED (INR)</th>" +
								"<th>PAYMENT RECEIPT DATE</th>" +
								"<th>REMARKS</th></tr>";
				for(int xx=0;xx<Value.length;xx++) {
					String bg="";
					if(xx%2!=0) 
						bg="#E0E0E0";
					else 
						bg="white";
					//System.out.println("---"+Value[xx]);
					MailBody+="<tr bgcolor="+bg+">";
					String Invoice[]=Value[xx].split("!@#");
					MailBody+="<td>"+Invoice[0]+"</td>";
					MailBody+="<td>"+Invoice[1]+"</td>";
					MailBody+="<td>"+Invoice[2]+"</td>";
					MailBody+="<td align='right'>"+Invoice[3]+"</td>";
					MailBody+="<td>"+Invoice[4]+"</td>";
					MailBody+="<td>"+Invoice[5]+"</td>";
					MailBody+="<td>"+Invoice[6]+"</td>";
					MailBody+="<td>"+Invoice[7]+"</td>";
					MailBody+="<td>"+Invoice[8]+"</td>";
					MailBody+="<td>"+Invoice[9]+"</td>";
					MailBody+="<td>"+Invoice[10]+"</td>";
					MailBody+="<td>"+Invoice[11]+"</td>";
					MailBody+="<td align='right'>"+Invoice[12]+"</td>";
					if(contracttype[0].equals("R")) {
						MailBody+="<td>"+Invoice[13]+"</td>";
						MailBody+="<td>"+Invoice[14]+"</td>";
						MailBody+="<td>"+Invoice[15]+"</td>";
						MailBody+="<td align='right'>"+Invoice[16]+"</td>";
						MailBody+="<td align='right'>"+Invoice[17]+"</td>";
						MailBody+="<td>"+Invoice[18]+"</td>";
						MailBody+="<td>"+Invoice[19]+"</td>";
					} else {
						MailBody+="<td>"+Invoice[13]+"</td>";
						MailBody+="<td align='right'>"+Invoice[14]+"</td>";
						MailBody+="<td align='right'>"+Invoice[15]+"</td>";
						MailBody+="<td>"+Invoice[16]+"</td>";
						MailBody+="<td>"+Invoice[17]+"</td>";
					}
					MailBody+="</tr>";
				}
				MailBody+="</table><br><br><font style='font-size: x-small'>Thanks</font><br><font style='font-size: xx-small; color: gray'>- This is System Generated Email.</font>";
				int x =m1.sendMail1("Sales Invoice Payment",MailBody,"",v1.elementAt(i).toString(),"System","deep@barodainformatics.com","","mail.barodainformatics.com","deep@barodainformatics.com","Bipl_123",false);
				//System.out.println("x------"+x);
			}
			
			
			if(false)
			{
				if(false) {
					dbcon.commit();
				} else{
					
				}	
			}
			dbcon.rollback();
			msg="Data Submitted Successfully..!";
		}
		catch(Exception e)
		{
			try {
				dbcon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//System.out.println("Exception in insertInvoicePaymentDetails() "+e);
			msg="Data Not Submitted..!";
		}
		//response.sendRedirect( "../sales_invoice/frm_invoice_payment_dtl.jsp?year=2016&month=02&to_year=2016&to_month=02&segment=SALES&btnflag=Y";
		response.sendRedirect("../sales_invoice/frm_invoice_payment_dtl.jsp?year="+year+"&month="+month+"&to_year="+to_year+"&to_month="+to_month+"&segment="+segment+"&invstatus="+invstatus+"&cust_name="+cust_name+"&customer_cd="+cust_cd+"&msg="+msg+"&btnflag=Y&customer_abbr="+customer_abbr);
	}
	
	public void insertpartyadvancedtl(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		methodName = "insertpartyadvancedtl()";
		form_name = "frm_advance_payment.jsp";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
		String month=request.getParameter("month")==null?"":request.getParameter("month");
		String year=request.getParameter("year")==null?"":request.getParameter("year");
		String bill_cycle=request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");
		String cont_type=request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
		
		
		String partyname=request.getParameter("partyName")==null?"0":request.getParameter("partyName");
		String date=request.getParameter("eff_date")==null?"0":request.getParameter("eff_date");
		String validitydt=request.getParameter("validate_date")==null?"":request.getParameter("validate_date");
		String currency=request.getParameter("currency")==null?"0":request.getParameter("currency");
		String amt=request.getParameter("advance_amt")==null?"0":request.getParameter("advance_amt");
		/*
		String[] inv_seq_no=request.getParameterValues("inv_seq_no");
		String[] sn_no=request.getParameterValues("sn_no");
		String[] cargo_ref_no=request.getParameterValues("cargo_ref_no");
		String[] boe_no=request.getParameterValues("boe_no");
		String[] inv_dt=request.getParameterValues("inv_dt");
		String[] cust_abr=request.getParameterValues("cust_abr");
		String[] inv_qty=request.getParameterValues("inv_qty");
		String[] inv_amt=request.getParameterValues("inv_amt");
		String[] due_dt=request.getParameterValues("due_dt");
		String[] flag=request.getParameterValues("flag");
		String[] HLPL_INV_SEQ_NO=request.getParameterValues("HLPL_INV_SEQ_NO");
		String inv_financial_year=request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		*/
		////System.out.println("partyname:"+partyname);
		////System.out.println("date:"+date);
		////System.out.println("validitydt:"+validitydt);
		////System.out.println("currency:"+currency);
		////System.out.println("amt:"+amt);
		HttpSession hs=request.getSession();
		String empcd=""+hs.getAttribute("user_cd");
		
		try
		{
		/*
		queryString="insert into FMS7_INVOICE_TRACKING_DTL (SN_NO,INV_DT,PARTY_ABR,TOTAL_QTY,NET_AMT_INR,DUE_DT,CARGO_REF_NO,BOE_NO,INV_FLAG,FLAG,INV_SEQ_NO,MONTH,YEAR,BILL_CYCLE,CONTRACT_TYPE,FINANCIAL_YEAR,HLPL_INV_SEQ_NO)" +
					" VALUES ('',to_date('"+inv_dt[i]+"','dd/mm/yy'),'"+cust_abr[i]+"','"+inv_qty[i]+"','"+inv_amt[i]+"',to_date('"+due_dt[i]+"','dd/mm/yy'),'"+cargo_ref_no[i]+"','"+boe_no[i]+"','"+flag[i]+"','Y','"+inv_seq_no[i]+"','"+month+"','"+year+"','"+bill_cycle+"','"+cont_type+"','"+inv_financial_year+"','"+HLPL_INV_SEQ_NO[i]+"')";
			*/	
			int seq_no=0;
			queryString1="select max(adv_seq_no) from fms7_party_adv_dtl where customer_cd='"+partyname+"'";
			rset1=stmt1.executeQuery(queryString1);
			while(rset1.next())
			{
				seq_no=rset1.getInt(1);
				
			}
			seq_no++;
			//here...1
		queryString="insert into fms7_party_adv_dtl (customer_cd, recv_dt, cur_inr_usd, amt, validity_dt, emp_cd, ent_dt, flag,adv_seq_no) "+ 
					" values('"+partyname+"',to_date('"+date+"','dd/mm/yy'),'"+currency+"','"+amt+"',to_date('"+validitydt+"','dd/mm/yyyy')" +
							",'"+empcd+"',to_date(sysdate,'dd/mm/yyyy'),'Y','"+seq_no+"' )";
		
				////System.out.println("INSERT QUERY--"+queryString);
				stmt.executeUpdate(queryString);
				msg="Data Submitted Successfully..";
			
	
			
			dbcon.commit();
		}
		catch(Exception e)
		{
			msg="Data not Submitted";
			e.printStackTrace();
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
		}
	
		response.sendRedirect("../advance_payment/frm_advance_payment.jsp?msg="+msg+"&partyname="+partyname);
		
	
		
	}
	
	
	
	
	public void insertinvtracking(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		methodName = "submitPaySecurity()";
		form_name = "rpt_invoice_tracking.jsp";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
		String month=request.getParameter("month")==null?"":request.getParameter("month");
		String year=request.getParameter("year")==null?"":request.getParameter("year");
		String bill_cycle=request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");
		String cont_type=request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
		
		String[] inv_seq_no=request.getParameterValues("inv_seq_no");
		String[] sn_no=request.getParameterValues("sn_no");
		String[] cargo_ref_no=request.getParameterValues("cargo_ref_no");
		String[] boe_no=request.getParameterValues("boe_no");
		String[] inv_dt=request.getParameterValues("inv_dt");
		String[] cust_abr=request.getParameterValues("cust_abr");
		String[] inv_qty=request.getParameterValues("inv_qty");
		String[] inv_amt=request.getParameterValues("inv_amt");
		String[] due_dt=request.getParameterValues("due_dt");
		String[] flag=request.getParameterValues("flag");
		String[] HLPL_INV_SEQ_NO=request.getParameterValues("HLPL_INV_SEQ_NO");
		String inv_financial_year=request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		////System.out.println("inv_financial_year:"+inv_financial_year);
		try
		{
		for(int i=0;i<inv_seq_no.length;i++)
		{
			int count=0;
			queryString3="select count(*) from FMS7_INVOICE_TRACKING_DTL where month='"+month+"' and year='"+year+"' and BILL_CYCLE='"+bill_cycle+"' and INV_SEQ_NO='"+inv_seq_no[i]+"'";
			rset=stmt.executeQuery(queryString3);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			
			if(count>0)
			{
				if(cont_type.equalsIgnoreCase("S"))
				{
		queryString="Update FMS7_INVOICE_TRACKING_DTL set SN_NO='"+sn_no[i]+"',INV_DT=to_date('"+inv_dt[i]+"','dd/mm/yy'),PARTY_ABR='"+cust_abr[i]+"',TOTAL_QTY='"+inv_qty[i]+"',NET_AMT_INR='"+inv_amt[i]+"',DUE_DT=to_date('"+due_dt[i]+"','dd/mm/yy'),INV_FLAG='"+flag[i]+"' where " +
				"INV_SEQ_NO='"+inv_seq_no[i]+"' and MONTH='"+month+"' and YEAR='"+year+"' and BILL_CYCLE='"+bill_cycle+"' and FINANCIAL_YEAR='"+inv_financial_year+"'";
				}
				else
				{
		queryString="Update FMS7_INVOICE_TRACKING_DTL set INV_DT=to_date('"+inv_dt[i]+"','dd/mm/yy'),PARTY_ABR='"+cust_abr[i]+"',TOTAL_QTY='"+inv_qty[i]+"',NET_AMT_INR='"+inv_amt[i]+"',DUE_DT=to_date('"+due_dt[i]+"','dd/mm/yy'),CARGO_REF_NO='"+cargo_ref_no[i]+"',BOE_NO='"+boe_no[i]+"',INV_FLAG='"+flag[i]+"' where" +
				" INV_SEQ_NO='"+inv_seq_no[i]+"' and MONTH='"+month+"' and YEAR='"+year+"' and BILL_CYCLE='"+bill_cycle+"' and FINANCIAL_YEAR='"+inv_financial_year+"'";
				}
				stmt.executeUpdate(queryString);
				msg="Data Updated Successfully..";
			}
			else
			{
				if(cont_type.equalsIgnoreCase("S"))
				{
		queryString="insert into FMS7_INVOICE_TRACKING_DTL (SN_NO,INV_DT,PARTY_ABR,TOTAL_QTY,NET_AMT_INR,DUE_DT,CARGO_REF_NO,BOE_NO,INV_FLAG,FLAG,INV_SEQ_NO,MONTH,YEAR,BILL_CYCLE,CONTRACT_TYPE,FINANCIAL_YEAR,HLPL_INV_SEQ_NO)" +
					" VALUES ('"+sn_no[i]+"',to_date('"+inv_dt[i]+"','dd/mm/yy'),'"+cust_abr[i]+"','"+inv_qty[i]+"','"+inv_amt[i]+"',to_date('"+due_dt[i]+"','dd/mm/yy'),'','','"+flag[i]+"','Y','"+inv_seq_no[i]+"','"+month+"','"+year+"','"+bill_cycle+"','"+cont_type+"','"+inv_financial_year+"','"+HLPL_INV_SEQ_NO[i]+"')";
				}
				else
				{
		queryString="insert into FMS7_INVOICE_TRACKING_DTL (SN_NO,INV_DT,PARTY_ABR,TOTAL_QTY,NET_AMT_INR,DUE_DT,CARGO_REF_NO,BOE_NO,INV_FLAG,FLAG,INV_SEQ_NO,MONTH,YEAR,BILL_CYCLE,CONTRACT_TYPE,FINANCIAL_YEAR,HLPL_INV_SEQ_NO)" +
					" VALUES ('',to_date('"+inv_dt[i]+"','dd/mm/yy'),'"+cust_abr[i]+"','"+inv_qty[i]+"','"+inv_amt[i]+"',to_date('"+due_dt[i]+"','dd/mm/yy'),'"+cargo_ref_no[i]+"','"+boe_no[i]+"','"+flag[i]+"','Y','"+inv_seq_no[i]+"','"+month+"','"+year+"','"+bill_cycle+"','"+cont_type+"','"+inv_financial_year+"','"+HLPL_INV_SEQ_NO[i]+"')";
				}
				stmt.executeUpdate(queryString);
				msg="Data Submitted Successfully..";
			}
		}
			
			dbcon.commit();
		}
		catch(Exception e)
		{
			msg="Data not Submitted";
			e.printStackTrace();
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
		}
	
		response.sendRedirect("../sales_invoice/rpt_invoice_tracking.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&cont_type="+cont_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
		
	try
	{
		new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
	}
	catch(Exception infoLogger)
	{
		//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
		infoLogger.printStackTrace();
	}
		
	}
	
	//Following Method Is Defined By Achal On 12th January, 2010 ...
	public void submitPaySecurity(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "submitPaySecurity()";
		form_name = "frm_pay_security.jsp"+" frm_pay_security_acc.jsp";
		String pay_cd = "1";
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");						
		String pay_dt = request.getParameter("pay_dt")==null?"":request.getParameter("pay_dt");
		
		String pay_amt = request.getParameter("pay_amt")==null?"":request.getParameter("pay_amt");
		String pay_type = request.getParameter("pay_type")==null?"":request.getParameter("pay_type");
		String settle_flag = request.getParameter("settle_flag")==null?"":request.getParameter("settle_flag");
		String activity = request.getParameter("activity")==null?"":request.getParameter("activity");
		String operation = request.getParameter("operation")==null?"":request.getParameter("operation");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");				
		remark = obj.replaceSingleQuotes(remark);
		String from_dt = request.getParameter("from_dt")==null?"":request.getParameter("from_dt");
		String to_dt = request.getParameter("to_dt")==null?"":request.getParameter("to_dt");
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			if(operation.trim().equals("update"))
			{
				pay_cd = request.getParameter("pay_cd")==null?"":request.getParameter("pay_cd");									
				queryString1 = "DELETE FROM FMS7_PAYMENT_SECURITY WHERE customer_cd="+customer_cd+" AND PAY_CD='"+pay_cd+"' ";				
				////System.out.println("Query For Deleting Record From FMS7_PAYMENT_SECURITY Table = "+queryString1);				
				stmt1.executeUpdate(queryString1);												
			}
			else if(operation.trim().equals("insert"))
			{
				queryString2 = "SELECT MAX(PAY_CD) FROM FMS7_PAYMENT_SECURITY WHERE customer_cd="+customer_cd+" ";
				////System.out.println("FMS7_PAYMENT_SECURITY Fetch Query = "+queryString2);
				rset1 = stmt1.executeQuery(queryString2);				
				if(rset1.next())
				{
					if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
					{
						pay_cd = ""+(Integer.parseInt(rset1.getString(1))+1);
					}
					else
					{
						pay_cd = "1";
					}
				}
				else
				{
					pay_cd = "1";
				}
			}
			queryString1 = "INSERT INTO FMS7_PAYMENT_SECURITY(CUSTOMER_CD,PAY_DT,PAY_CD,PAY_AMT,PAY_TYPE," +
					"		ADV_TYPE,SETTLE_FLAG,REMARK,EMP_CD,ENT_DT)" +
					       "VALUES("+customer_cd+",TO_DATE('"+pay_dt+"','DD/MM/YYYY'),'"+pay_cd+"','"+pay_amt+"','"+pay_type+"'," +
					       	"'"+activity+"','"+settle_flag+"','"+remark+"', "+user_cd+", sysdate)";			
			////System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
			stmt1.executeUpdate(queryString1);			
			msg = "Received Payment Has Been Submitted Successfully !!!";
			if(activity.trim().equalsIgnoreCase("adv"))
			{
				response.sendRedirect("../sales_invoice/frm_pay_security.jsp?msg="+msg+"&bscode="+customer_cd+"&remark="+remark+"&pay_cd="+pay_cd+"&pay_dt="+pay_dt+"&settle_flag="+settle_flag+"&activity="+activity+"&operation="+operation+"&from_dt="+from_dt+"&to_dt="+to_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			}
			else if(activity.trim().equalsIgnoreCase("pay"))
			{
				response.sendRedirect("../accounting/frm_pay_security_acc.jsp?msg="+msg+"&bscode="+customer_cd+"&remark="+remark+"&pay_cd="+pay_cd+"&pay_dt="+pay_dt+"&settle_flag="+settle_flag+"&activity="+activity+"&operation="+operation+"&from_dt="+from_dt+"&to_dt="+to_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			}
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Payment and Security Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			if(activity.trim().equalsIgnoreCase("adv"))
			{
				response.sendRedirect("../sales_invoice/frm_pay_security.jsp?msg="+msg+"&bscode="+customer_cd+"&remark="+remark+"&pay_cd="+pay_cd+"&pay_dt="+pay_dt+"&settle_flag="+settle_flag+"&activity="+activity+"&operation="+operation+"&from_dt="+from_dt+"&to_dt="+to_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			}
			else if(activity.trim().equalsIgnoreCase("pay"))
			{
				response.sendRedirect("../accounting/frm_pay_security_acc.jsp?msg="+msg+"&bscode="+customer_cd+"&remark="+remark+"&pay_cd="+pay_cd+"&pay_dt="+pay_dt+"&settle_flag="+settle_flag+"&activity="+activity+"&operation="+operation+"&from_dt="+from_dt+"&to_dt="+to_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			}	
		}		
	}//End Of Method submitPaySecurity() ...
	
	
	//Following Method Is Defined By Achal On 8th December, 2010 ...
	public void submitCForm(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "submitCForm()";
		form_name = "frm_dr_cr_criteria1.jsp";
		String  qty_arr="";
		String  inv_no_arr="";
		String  inv_dt_arr="";
		
		String  gross_amt_arr ="";
		String  net_amt_arr ="";
		String  sale_rate_arr ="";
		String  fin_yr_arr ="";
		String  con_type_arr ="";
		String  hlpl_no_arr ="";
		
		String  plant_no_arr ="";
		String  tax_struc_cd_arr ="";
		String  fgsa_no_arr ="";
		String  sn_no_arr ="";
		String  fgsa_rev_no_arr ="";
		String  sn_rev_no_arr ="";
		String  gross_amt_usd_arr ="";
		
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String criteria = request.getParameter("criteria")==null?"":request.getParameter("criteria");
		String dr_cr_dt = request.getParameter("dr_cr_dt")==null?"":request.getParameter("dr_cr_dt");
		String dr_cr_doc_no=request.getParameter("dr_cr_doc_no")==null?"":request.getParameter("dr_cr_doc_no");
		String cr_dr   = request.getParameter("cr_dr")==null?"":request.getParameter("cr_dr");
		String dr_cr_fin_year= "";//request.getParameter("dr_cr_fin_yr")==null?"":request.getParameter("dr_cr_fin_yr");
		String dr_cr_no = "1";
		
		String [] fgsa_no = request.getParameterValues("fgsa_no");
		String [] fgsa_rev_no = request.getParameterValues("fgsa_rev_no");
		String sn_no[] = request.getParameterValues("sn_no");
		String sn_rev_no[] = request.getParameterValues("sn_rev_no");		
		String con_type[] = request.getParameterValues("con_type");
		String plant_no[] = request.getParameterValues("plant_no");
		String hlpl_no[] = request.getParameterValues("hlpl_no");
		String fin_yr[] = request.getParameterValues("fin_yr");
		String inv_no[] = request.getParameterValues("inv_no");
		String qty[] = request.getParameterValues("qty");		
		String gross_amt[] = request.getParameterValues("gross_amt");
		String net_amt[] = request.getParameterValues("net_amt");				
		String inv_dt[] = request.getParameterValues("inv_dt");	
		String sale_rate[] = request.getParameterValues("sale_rate");
		
		String gross_amt_usd[]= request.getParameterValues("gross_amt_usd");
		String tax_struc_cd[]= request.getParameterValues("tax_struc_cd");
				
		String cust_nm   = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");				
		remark = obj.replaceSingleQuotes(remark);
		
		String diff_qty = "";//request.getParameter("diff_qty")==null?"":request.getParameter("diff_qty");
		String day_diff = "";//request.getParameter("day_diff")==null?"":request.getParameter("day_diff");			
		String int_cd = "";//request.getParameter("int_cd")==null?"":request.getParameter("int_cd");
		String due_dt = "";//request.getParameter("due_dt")==null?"":request.getParameter("due_dt");										
		String exg_rt = "";//request.getParameter("exg_rt")==null?"":request.getParameter("exg_rt");
		String dr_cr_exg_rt = "";//request.getParameter("dr_cr_exg_rt")==null?"":request.getParameter("dr_cr_exg_rt");
		String dr_cr_gross_usd="";//request.getParameter("dr_cr_gross_usd")==null?"":request.getParameter("dr_cr_gross_usd");
		
		String dr_cr_gross_amt=request.getParameter("tot_gross_amt")==null?"":request.getParameter("tot_gross_amt");
		String dr_cr_net_amt=request.getParameter("tot_net_amt")==null?"":request.getParameter("tot_net_amt");		
		String int_rate = request.getParameter("int_rate")==null?"":request.getParameter("int_rate");
		String int_amt = request.getParameter("add_amt")==null?"":request.getParameter("add_amt");
		String add_pay_amt = request.getParameter("add_pay_amt")==null?"":request.getParameter("add_pay_amt");
		
		String form_id = request.getParameter("form_id")==null?"":request.getParameter("form_id");
		String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
		String dr_cr ="";
		if(cr_dr.trim().equalsIgnoreCase("dr"))
		{
			dr_cr ="Debit ";
		}
		else if(cr_dr.trim().equalsIgnoreCase("cr"))
		{
			dr_cr ="Credit ";
		}
		
		if(!(dr_cr_dt.trim().equals("")))
		{
			if(Integer.parseInt(dr_cr_dt.substring(3,5))<4)
			{
				dr_cr_fin_year=""+(Integer.parseInt(dr_cr_dt.substring(6))-1)+"-"+dr_cr_dt.substring(6);
			}
			else if(Integer.parseInt(dr_cr_dt.substring(3,5))>=4) 
			{
				dr_cr_fin_year=""+dr_cr_dt.substring(6)+"-"+(Integer.parseInt(dr_cr_dt.substring(6))+1);
			}			
		}
		
		for(int i=0;i<sn_no.length;i++)
	    {		    	
	    	qty_arr+=qty[i].trim()+"~~";
			inv_no_arr+=inv_no[i].trim()+"~~";
			inv_dt_arr+=inv_dt[i].trim()+"~~";
			
			gross_amt_arr+=gross_amt[i].trim()+"~~";
			net_amt_arr+=net_amt[i].trim()+"~~";
			sale_rate_arr+=sale_rate[i].trim()+"~~";
			fin_yr_arr+=fin_yr[i].trim()+"~~";
			con_type_arr+=con_type[i].trim()+"~~";
			hlpl_no_arr+=hlpl_no[i].trim()+"~~";
			
			plant_no_arr+=plant_no[i].trim()+"~~";
			tax_struc_cd_arr+=tax_struc_cd[i].trim()+"~~";
			fgsa_no_arr+=fgsa_no[i].trim()+"~~";
			sn_no_arr+=sn_no[i].trim()+"~~";
			fgsa_rev_no_arr+=fgsa_rev_no[i].trim()+"~~";
			sn_rev_no_arr+=sn_rev_no[i].trim()+"~~";
			gross_amt_usd_arr+=gross_amt_usd[i].trim()+"~~";
	    }
		int cnt1 = sn_no.length;
		////System.out.println("dr_cr_dt.substring(3,5) = "+dr_cr_dt.substring(3,5)+", dr_cr_fin_year ="+dr_cr_fin_year+", dr_cr_dt.substring(6)"+dr_cr_dt.substring(6));
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			//Logic for Creating DR_CR_NO
			for(int i=0;i<sn_no.length;i++)
			{
				queryString = "SELECT DR_CR_NO,DR_CR_FIN_YEAR FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no='"+plant_no[i]+"' AND " +
							  "fgsa_no="+fgsa_no[i]+" AND sn_no="+sn_no[i]+" AND " +
							  "contract_type='"+con_type[i]+"' AND financial_year='"+fin_yr[i]+"' AND " +
							  "hlpl_inv_seq_no='"+hlpl_no[i]+"' AND CRITERIA='"+criteria+"'  ";			
				////System.out.println("Query For Finding Out Existing FMS7_DR_CR_C_FORM With Same Data "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{									
					if(dr_cr_fin_year.equals(rset.getString(2).trim()))
					{
						if(rset.getString(1)!=null && !(rset.getString(1).trim().equals("")))
						{
							dr_cr_no = rset.getString(1);
						}
						else
						{
							dr_cr_no = "1";
						}
						queryString1 = "DELETE FROM FMS7_DR_CR_C_FORM WHERE DR_CR_DT=TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY') AND " +
						"DR_CR_FLAG='"+cr_dr+"' AND DR_CR_FIN_YEAR ='"+rset.getString(2)+"' AND DR_CR_NO='"+rset.getString(1)+"' ";				
						////System.out.println("Query For Deleting Record From FMS7_DR_CR_C_FORM Table = "+queryString1);				
						stmt1.executeUpdate(queryString1);
					}
					else
					{
						queryString1 = "DELETE FROM FMS7_DR_CR_C_FORM WHERE DR_CR_DT=TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY') AND " +
						"DR_CR_FLAG='"+cr_dr+"' AND DR_CR_FIN_YEAR ='"+rset.getString(2)+"' AND DR_CR_NO='"+dr_cr_no+"' ";				
						////System.out.println("Query For Deleting Record From FMS7_DR_CR_C_FORM Table = "+queryString1);				
						stmt1.executeUpdate(queryString1);
						
						queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' ";
						////System.out.println("MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE Fetch Query = "+queryString2);
						rset1 = stmt1.executeQuery(queryString2);				
						if(rset1.next())
						{
							if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
							{
								dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
							}
							else
							{
								dr_cr_no = "1";
							}
						}
						else
						{
							dr_cr_no = "1";
						}
					}
				}
				else
				{
					queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE WHERE " +
							"DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' ";
					////System.out.println("MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE Fetch Query = "+queryString2);
					rset1 = stmt1.executeQuery(queryString2);				
					if(rset1.next())
					{
						if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
						{
							dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
						}
						else
						{
							dr_cr_no = "1";
						}
					}
					else
					{
						dr_cr_no = "1";
					}
					queryString1 = "DELETE FROM FMS7_DR_CR_C_FORM WHERE DR_CR_DT=TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY') AND " +
					"DR_CR_FLAG='"+cr_dr+"' AND DR_CR_FIN_YEAR ='"+rset1.getString(1)+"' AND DR_CR_NO='"+dr_cr_no+"' ";				
					////System.out.println("Query For Deleting Record From FMS7_DR_CR_C_FORM Table = "+queryString1);				
					stmt1.executeUpdate(queryString1);
				}
			}
			//Logic for Inserting into C-Form Table
			queryString1 = "INSERT INTO FMS7_DR_CR_C_FORM(DR_CR_DT,DR_CR_NO,DR_CR_FIN_YEAR,DR_CR_FLAG ,DR_CR_DOC_NO," +
		       "TOTAL_GROSS_AMT_INR,TOTAL_NET_AMT_INR,DR_CR_TOTAL_AMT_INR,ADD_TAX_PAY,DR_CR_TAX_PERCENT,REMARK,EMP_CD,ENT_DT,FLAG)" +
		       "VALUES(TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY'),'"+dr_cr_no+"','"+dr_cr_fin_year+"','"+cr_dr+"','"+dr_cr_doc_no+"', " +
			   "'"+dr_cr_gross_amt+"','"+dr_cr_net_amt+"','"+int_amt+"','"+add_pay_amt+"','"+int_rate+"','"+remark+"', "+user_cd+", sysdate, 'Y')";			
			////System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
			stmt1.executeUpdate(queryString1);
			
			//Logic for Inserting into DR_CR_NOTE Table
		    for(int i=0;i<sn_no.length;i++)
		    {		    	
		    	/*qty_arr+=qty[i].trim()+"~~";
				inv_no_arr+=inv_no[i].trim()+"~~";
				inv_dt_arr+=inv_dt[i].trim()+"~~";
				
				gross_amt_arr+=gross_amt[i].trim()+"~~";
				net_amt_arr+=net_amt[i].trim()+"~~";
				sale_rate_arr+=sale_rate[i].trim()+"~~";
				fin_yr_arr+=fin_yr[i].trim()+"~~";
				con_type_arr+=con_type[i].trim()+"~~";
				hlpl_no_arr+=hlpl_no[i].trim()+"~~";
				
				plant_no_arr+=plant_no[i].trim()+"~~";
				tax_struc_cd_arr+=tax_struc_cd[i].trim()+"~~";
				fgsa_no_arr+=fgsa_no[i].trim()+"~~";
				sn_no_arr+=sn_no[i].trim()+"~~";
				fgsa_rev_no_arr+=fgsa_rev_no[i].trim()+"~~";
				sn_rev_no_arr+=sn_rev_no[i].trim()+"~~";
				gross_amt_usd_arr+=gross_amt_usd[i].trim()+"~~";
				//////System.out.println("dr_cr_no = "+dr_cr_no);*/
		    	
				queryString = "SELECT DR_CR_NO,DR_CR_FIN_YEAR FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no='"+plant_no[i].trim()+"' AND " +
							  "fgsa_no="+fgsa_no[i].trim()+" AND sn_no="+sn_no[i].trim()+" AND " +
							  "contract_type='"+con_type[i].trim()+"' AND financial_year='"+fin_yr[i].trim()+"' AND " +
							  "hlpl_inv_seq_no='"+hlpl_no[i].trim()+"' AND CRITERIA='C-FORM' ";			
				////System.out.println("Query For Finding Out Existing Invoice With Same Data "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{		
					if(dr_cr_fin_year.equals(rset.getString(2).trim()))
					{
						if(rset.getString(1)!=null && !(rset.getString(1).trim().equals("")))
						{
							dr_cr_no = rset.getString(1);
						}
						else
						{
							dr_cr_no = "1";
						}
						queryString1 = "DELETE FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no="+plant_no[i].trim()+" AND " +
			  			   "fgsa_no="+fgsa_no[i].trim()+" AND sn_no="+sn_no[i].trim()+" AND contract_type='"+con_type[i].trim()+"' AND CRITERIA='C-FORM' AND " +
			  			   "financial_year='"+fin_yr[i].trim()+"' AND hlpl_inv_seq_no='"+hlpl_no[i].trim()+"' ";				
						////System.out.println("Query For Deleting Record From Debit Credit Note Table = "+queryString1);				
						stmt1.executeUpdate(queryString1);
					}
					else
					{
						queryString1 = "DELETE FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no="+plant_no[i].trim()+" AND " +
			  			   "fgsa_no="+fgsa_no[i].trim()+" AND sn_no="+sn_no[i].trim()+" AND contract_type='"+con_type[i].trim()+"' AND CRITERIA='C-FORM' AND " +
			  			   "financial_year='"+fin_yr[i].trim()+"' AND hlpl_inv_seq_no='"+hlpl_no[i].trim()+"' ";				
						////System.out.println("Query For Deleting Record From Debit Credit Note Table = "+queryString1);				
						stmt1.executeUpdate(queryString1);
						queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' ";
						////System.out.println("MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE Fetch Query = "+queryString2);
						rset1 = stmt1.executeQuery(queryString2);				
						if(rset1.next())
						{
							if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
							{
								dr_cr_no = ""+(Integer.parseInt(rset1.getString(1)));
							}
							else
							{
								dr_cr_no = "1";
							}
						}
						else
						{
							dr_cr_no = "1";
						}
					}									
				}
				/*else
				{
					queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' ";
					//System.out.println("MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE Fetch Query = "+queryString2);
					rset1 = stmt1.executeQuery(queryString2);				
					if(rset1.next())
					{
						if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
						{
							dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
						}
						else
						{
							dr_cr_no = "1";
						}
					}
					else
					{
						dr_cr_no = "1";
					}
				}*/
				queryString1 = "INSERT INTO FMS7_DR_CR_NOTE(SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
						       "SN_REV_NO,FGSA_REV_NO,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,CRITERIA,DUE_DT,EXCHG_RATE_VALUE," +
						       "DR_CR_FLAG,DR_CR_NO,DR_CR_DT,DIFF_QTY,DIFF_AMT,DAY_DIFF,DR_CR_EXG_RATE,INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,EMP_CD," +
						       "ENT_DT,FLAG,GROSS_AMT_USD,TAX_STRUCT_CD,DR_CR_DOC_NO,TAX_AMT_INR,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
						       "DR_CR_GROSS_AMT_USD,DR_CR_FIN_YEAR)VALUES("+sn_no[i].trim()+","+fgsa_no[i].trim()+","+customer_cd+"," +
						       "'"+plant_no[i].trim()+"','"+con_type[i].trim()+"','"+hlpl_no[i].trim()+"'," +
							   "'"+fin_yr[i].trim()+"',"+fgsa_rev_no[i].trim()+","+sn_rev_no[i].trim()+"," +
							   "TO_DATE('"+inv_dt[i].trim()+"','DD/MM/YYYY'), " +
							   "'"+qty[i].trim()+"','"+sale_rate[i].trim()+"','"+gross_amt[i].trim()+"','"+net_amt[i].trim()+"'," +
							   "'"+criteria+"', TO_DATE('"+due_dt+"','DD/MM/YYYY'), '"+exg_rt+"'," +
							   "'"+cr_dr+"','"+dr_cr_no+"',TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY'),'"+diff_qty+"','"+dr_cr_gross_amt+"'," +
							   "'"+day_diff+"','"+dr_cr_exg_rt+"','"+int_cd+"','"+int_rate+"','"+int_amt+"'," +
							   "'"+remark+"', "+user_cd+", sysdate, 'Y','"+gross_amt_usd[i].trim()+"','"+tax_struc_cd[i].trim()+"'," +
							   "'"+dr_cr_doc_no+"','"+add_pay_amt+"','"+dr_cr_gross_amt+"','"+dr_cr_net_amt+"','"+dr_cr_gross_usd+"','"+dr_cr_fin_year+"')";			
				////System.out.println("Query For Inserting Record Into Dr./Cr. Note Table = "+queryString1);
				stmt1.executeUpdate(queryString1);
		    }		    
			msg = dr_cr+"Note Has Been Submitted Successfully For CForm Unavailability !!!";
			response.sendRedirect("../sales_invoice/frm_dr_cr_criteria1.jsp?msg="+msg+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&cust_nm="+cust_nm+"&fgsa_no="+fgsa_no_arr+"&fgsa_rev_no="+fgsa_rev_no_arr+"&sn_no="+sn_no_arr+"&sn_rev_no="+sn_rev_no_arr+"&con_type="+con_type_arr+"&plant_no="+plant_no_arr+"&hlpl_no="+hlpl_no_arr+"&inv_no="+inv_no_arr+"&inv_dt="+inv_dt_arr+"&fin_yr="+fin_yr_arr+"&criteria="+criteria+"&qty="+qty_arr+"&gross_amt="+gross_amt_arr+"&gross_amt_usd="+gross_amt_usd_arr+"&net_amt="+net_amt_arr+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate_arr+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&diff_qty="+diff_qty+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&day_diff="+day_diff+"&int_amt="+int_amt+"&int_rate="+int_rate+"&int_cd="+int_cd+"&dr_cr_no="+dr_cr_no+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&cnt1="+cnt1);
			//response.sendRedirect( "../sales_invoice/frm_dr_cr_criteria1.jsp?msg="+msg+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&cust_nm="+cust_nm+"&criteria="+criteria+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&diff_qty="+diff_qty+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&day_diff="+day_diff+"&int_amt="+int_amt+"&int_rate="+int_rate+"&int_cd="+int_cd+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = dr_cr+"Note Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_dr_cr_criteria1.jsp?msg="+msg+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&cust_nm="+cust_nm+"&fgsa_no="+fgsa_no_arr+"&fgsa_rev_no="+fgsa_rev_no_arr+"&sn_no="+sn_no_arr+"&sn_rev_no="+sn_rev_no_arr+"&con_type="+con_type_arr+"&plant_no="+plant_no_arr+"&hlpl_no="+hlpl_no_arr+"&inv_no="+inv_no_arr+"&inv_dt="+inv_dt_arr+"&fin_yr="+fin_yr_arr+"&criteria="+criteria+"&qty="+qty_arr+"&gross_amt="+gross_amt_arr+"&gross_amt_usd="+gross_amt_usd_arr+"&net_amt="+net_amt_arr+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate_arr+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&diff_qty="+diff_qty+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&day_diff="+day_diff+"&int_amt="+int_amt+"&int_rate="+int_rate+"&int_cd="+int_cd+"&dr_cr_no="+dr_cr_no+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&cnt1="+cnt1);
		}		
	}//End Of Method submitCForm() ...		
	
			
	//Following Method Is Defined By Achal On 2nd December, 2010 ...
	public void submitDebitCreditNote_Bhakti(HttpServletRequest request,HttpServletResponse response) throws IOException //SB20160401: By Bhakti20160331
	{
		methodName = "submitDebitCreditNote()";
		form_name = "frm_dr_cr_criteria.jsp";
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");		
		String con_type = request.getParameter("con_type")==null?"":request.getParameter("con_type");
		String plant_no = request.getParameter("plant_no")==null?"":request.getParameter("plant_no");
		String hlpl_no = request.getParameter("hlpl_no")==null?"":request.getParameter("hlpl_no");
		String fin_yr = request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
		
		String criteria = request.getParameter("criteria")==null?"":request.getParameter("criteria");					
		String due_dt = request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
									
		String inv_no = request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
		String dr_cr_no = "1";
		String dr_cr_dt = request.getParameter("dr_cr_dt")==null?"":request.getParameter("dr_cr_dt");
		String qty = request.getParameter("qty")==null?"":request.getParameter("qty");
		
		String gross_amt = request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
		String net_amt = request.getParameter("net_amt")==null?"":request.getParameter("net_amt");
		String exg_rt = request.getParameter("exg_rt")==null?"":request.getParameter("exg_rt");
		String dr_cr_exg_rt = request.getParameter("dr_cr_exg_rt")==null?"":request.getParameter("dr_cr_exg_rt");		
		String inv_dt = request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");//ADDED FOR LTCORA AND CN
				
		String diff_qty = request.getParameter("diff_qty")==null?"":request.getParameter("diff_qty");
		
		String day_diff = request.getParameter("day_diff")==null?"":request.getParameter("day_diff");
		String int_cd = request.getParameter("int_cd")==null?"":request.getParameter("int_cd");
		String int_rate = request.getParameter("int_rate")==null?"":request.getParameter("int_rate");
		String int_rate_cal = request.getParameter("int_rate_cal")==null?"":request.getParameter("int_rate_cal");
		String int_amt = request.getParameter("int_amt")==null?"":request.getParameter("int_amt");
		String sale_rate = request.getParameter("sale_rate")==null?"0":request.getParameter("sale_rate");				
		String cr_dr     = request.getParameter("cr_dr")==null?"":request.getParameter("cr_dr");
		String cust_nm   = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");				
		remark = obj.replaceSingleQuotes(remark);
		
		String gross_amt_usd=request.getParameter("gross_amt_usd")==null?"":request.getParameter("gross_amt_usd");
		String tax_struc_cd=request.getParameter("tax_struc_cd")==null?"":request.getParameter("tax_struc_cd");
		String dr_cr_doc_no=request.getParameter("dr_cr_doc_no")==null?"":request.getParameter("dr_cr_doc_no");
		String tax_amt_inr=request.getParameter("tax_amt_inr")==null?"":request.getParameter("tax_amt_inr");
		
		String dr_cr_gross_usd=request.getParameter("dr_cr_gross_usd")==null?"":request.getParameter("dr_cr_gross_usd");
		String dr_cr_gross_amt=request.getParameter("dr_cr_gross_amt")==null?"":request.getParameter("dr_cr_gross_amt");
		String dr_cr_net_amt=request.getParameter("dr_cr_net_amt")==null?"":request.getParameter("dr_cr_net_amt");
		
		String dr_cr_fin_year= request.getParameter("dr_cr_fin_year")==null?"":request.getParameter("dr_cr_fin_year");
		String form_id = request.getParameter("form_id")==null?"":request.getParameter("form_id");
		String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
		String dr_cr ="";
		if(cr_dr.trim().equalsIgnoreCase("dr"))
		{
			dr_cr ="Debit ";
		}
		else if(cr_dr.trim().equalsIgnoreCase("cr"))
		{
			dr_cr ="Credit ";
		}
		
		if(!(dr_cr_dt.trim().equals("")))
		{
			if(Integer.parseInt(dr_cr_dt.substring(3,5))<4)
			{
				dr_cr_fin_year=""+(Integer.parseInt(dr_cr_dt.substring(6))-1)+"-"+dr_cr_dt.substring(6);
			}
			else if(Integer.parseInt(dr_cr_dt.substring(3,5))>=4) 
			{
				dr_cr_fin_year=""+dr_cr_dt.substring(6)+"-"+(Integer.parseInt(dr_cr_dt.substring(6))+1);
			}			
		}
		////System.out.println("dr_cr_dt.substring(3,5) = "+dr_cr_dt.substring(3,5)+", dr_cr_fin_year ="+dr_cr_fin_year+", dr_cr_dt.substring(6)"+dr_cr_dt.substring(6));
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			////System.out.println("dr_cr_no = "+dr_cr_no);
			queryString = "SELECT DR_CR_NO,DR_CR_FIN_YEAR FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no="+plant_no+" AND " +
						  "fgsa_no="+fgsa_no+" AND sn_no="+sn_no+" AND " +
						  "contract_type='"+con_type+"' AND financial_year='"+fin_yr+"' AND " +
						  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_no)+" AND CRITERIA='"+criteria+"'  ";			
			////System.out.println("Query For Finding Out Existing Invoice With Same Data "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{			
				if(dr_cr_fin_year.equals(rset.getString(2).trim()))
				{			
					if(rset.getString(1)!=null && !(rset.getString(1).trim().equals("")))
					{
						dr_cr_no = rset.getString(1);
					}
					else
					{
						dr_cr_no = "1";
					}				
					queryString1 = "DELETE FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no='"+plant_no+"' AND " +
		  			   "fgsa_no='"+fgsa_no+"' AND sn_no='"+sn_no+"' AND contract_type='"+con_type+"' AND CRITERIA='"+criteria+"' AND " +
		  			   "financial_year='"+fin_yr+"' AND hlpl_inv_seq_no="+Integer.parseInt(hlpl_no)+"";				
					////System.out.println("Query For Deleting Record From Debit Credit Note Table = "+queryString1);				
					stmt1.executeUpdate(queryString1);	
				}
				/*else
				{
					queryString1 = "DELETE FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no='"+plant_no+"' AND " +
		  			   "fgsa_no='"+fgsa_no+"' AND sn_no='"+sn_no+"' AND contract_type='"+con_type+"' AND CRITERIA='"+criteria+"' AND " +
		  			   "financial_year='"+fin_yr+"' AND hlpl_inv_seq_no="+Integer.parseInt(hlpl_no)+"";				
					//System.out.println("Query For Deleting Record From Debit Credit Note Table = "+queryString1);				
					stmt1.executeUpdate(queryString1);
					
					queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' ";
					//System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString2);
					rset1 = stmt1.executeQuery(queryString2);				
					if(rset1.next())
					{
						if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
						{
							dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
						}
						else
						{
							dr_cr_no = "1";
						}
					}
					else
					{
						dr_cr_no = "1";
					}
				}*/
			}
			else
			{
				queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"'";
				////System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString2);
				rset1 = stmt1.executeQuery(queryString2);				
				if(rset1.next())
				{
					if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
					{
						dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
					}
					else
					{
						dr_cr_no = "1";
					}
				}
				else
				{
					dr_cr_no = "1";
				}
			}
			queryString1 = "INSERT INTO FMS7_DR_CR_NOTE(SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
					       "SN_REV_NO,FGSA_REV_NO,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,CRITERIA,DUE_DT,EXCHG_RATE_VALUE," +
					       "DR_CR_FLAG,DR_CR_NO,DR_CR_DT,DIFF_QTY,DIFF_AMT,DAY_DIFF,DR_CR_EXG_RATE,INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,EMP_CD," +
					       "ENT_DT,FLAG,GROSS_AMT_USD,TAX_STRUCT_CD,DR_CR_DOC_NO,TAX_AMT_INR,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
					       "DR_CR_GROSS_AMT_USD,DR_CR_FIN_YEAR)VALUES("+sn_no+","+fgsa_no+","+customer_cd+","+Integer.parseInt(plant_no)+",'"+con_type+"', "+Integer.parseInt(hlpl_no)+"," +
						   "'"+fin_yr+"',"+fgsa_rev_no+","+sn_rev_no+",TO_DATE('"+inv_dt+"','DD/MM/YYYY'), " +
						   "'"+qty+"','"+sale_rate+"','"+gross_amt+"','"+net_amt+"','"+criteria+"', TO_DATE('"+due_dt+"','DD/MM/YYYY'), '"+exg_rt+"'," +
						   "'"+cr_dr+"','"+dr_cr_no+"',TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY'),'"+diff_qty+"','"+dr_cr_gross_amt+"','"+day_diff+"','"+dr_cr_exg_rt+"', " +
						   "'"+int_cd+"','"+int_rate_cal+"','"+int_amt+"','"+remark+"', "+user_cd+", sysdate, 'Y','"+gross_amt_usd+"','"+tax_struc_cd+"'," +
						   "'"+dr_cr_doc_no+"','"+tax_amt_inr+"','"+dr_cr_gross_amt+"','"+dr_cr_net_amt+"','"+dr_cr_gross_usd+"','"+dr_cr_fin_year+"')";
			//System.out.println("Query For Inserting Record Into Debit Credit Table = "+queryString1);
			stmt1.executeUpdate(queryString1);
			
			String q1="UPDATE DLNG_INVOICE_MST SET FLAG='X' WHERE SN_NO='"+sn_no+"' AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_no)+"' AND CONTRACT_TYPE='"+con_type+"' AND FINANCIAL_YEAR='"+fin_yr+"'";
			//System.out.println("UPDATE DLNG_INVOICE_MST FLAG Q1==="+q1);
			stmt1.executeUpdate(q1);
			
			if(criteria.trim().equalsIgnoreCase("D-PAY"))
			{
				msg = dr_cr+"Note Has Been Submitted Successfully Due to Delayed Payment !!!";
			}
			else if(criteria.trim().equalsIgnoreCase("DIFF-EXG"))
			{
				msg = dr_cr+"Note Has Been Submitted Successfully Due to Difference In Exchange Rate !!!";
			}
			
			////System.out.println("dr_cr ="+dr_cr+",criteria ="+criteria+"msg ="+msg);
			response.sendRedirect("../sales_invoice/frm_dr_cr_criteria.jsp?msg="+msg+"&mapping_id="+mapping_id+"&tax_struc_cd="+tax_struc_cd+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&cust_nm="+cust_nm+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&con_type="+con_type+"&plant_no="+plant_no+"&due_dt="+due_dt+"&hlpl_no="+hlpl_no+"&inv_no="+inv_no+"&inv_dt="+inv_dt+"&fin_yr="+fin_yr+"&criteria="+criteria+"&qty="+qty+"&gross_amt="+gross_amt+"&gross_amt_usd="+gross_amt_usd+"&net_amt="+net_amt+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&diff_qty="+diff_qty+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&day_diff="+day_diff+"&int_amt="+int_amt+"&int_rate="+int_rate+"&int_rate_cal="+int_rate_cal+"&int_cd="+int_cd+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = dr_cr+"Note Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_dr_cr_criteria.jsp?msg="+msg+"&mapping_id="+mapping_id+"&tax_struc_cd="+tax_struc_cd+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&cust_nm="+cust_nm+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&con_type="+con_type+"&plant_no="+plant_no+"&due_dt="+due_dt+"&hlpl_no="+hlpl_no+"&inv_no="+inv_no+"&inv_dt="+inv_dt+"&fin_yr="+fin_yr+"&criteria="+criteria+"&qty="+qty+"&gross_amt="+gross_amt+"&gross_amt_usd="+gross_amt_usd+"&net_amt="+net_amt+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&diff_qty="+diff_qty+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&day_diff="+day_diff+"&int_amt="+int_amt+"&int_rate="+int_rate+"&int_rate_cal="+int_rate_cal+"&int_cd="+int_cd+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);	
		}		
	}//End Of Method submitDebitCreditNote() ...
	
	public void submitDebitCreditNote(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "submitDebitCreditNote()";
		form_name = "frm_dr_cr_criteria.jsp";
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");		
		String con_type = request.getParameter("con_type")==null?"":request.getParameter("con_type");
		String plant_no = request.getParameter("plant_no")==null?"":request.getParameter("plant_no");
		String hlpl_no = request.getParameter("hlpl_no")==null?"":request.getParameter("hlpl_no");
		String fin_yr = request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
		
		String criteria = request.getParameter("criteria")==null?"":request.getParameter("criteria");					
		String due_dt = request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
									
		String inv_no = request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
		//String dr_cr_no = "1";
		String dr_cr_no = request.getParameter("dr_cr_no")==null?"0":request.getParameter("dr_cr_no");
		String dr_cr_dt = request.getParameter("dr_cr_dt")==null?"":request.getParameter("dr_cr_dt");
		String qty = request.getParameter("qty")==null?"":request.getParameter("qty");
		
		String gross_amt = request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
		String net_amt = request.getParameter("net_amt")==null?"":request.getParameter("net_amt");
		String exg_rt = request.getParameter("exg_rt")==null?"":request.getParameter("exg_rt");
		String dr_cr_exg_rt = request.getParameter("dr_cr_exg_rt")==null?"":request.getParameter("dr_cr_exg_rt");		
		String inv_dt = request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");//ADDED FOR LTCORA AND CN
				
		String diff_qty = request.getParameter("diff_qty")==null?"":request.getParameter("diff_qty");
		
		String day_diff = request.getParameter("day_diff")==null?"":request.getParameter("day_diff");
		String int_cd = request.getParameter("int_cd")==null?"":request.getParameter("int_cd");
		String int_rate = request.getParameter("int_rate")==null?"":request.getParameter("int_rate");
		String int_rate_cal = request.getParameter("int_rate_cal")==null?"":request.getParameter("int_rate_cal");
		String int_amt = request.getParameter("int_amt")==null?"":request.getParameter("int_amt");
		String sale_rate = request.getParameter("sale_rate")==null?"0":request.getParameter("sale_rate");				
		String cr_dr     = request.getParameter("cr_dr")==null?"":request.getParameter("cr_dr");
		String cust_nm   = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");				
		remark = obj.replaceSingleQuotes(remark);
		
		String gross_amt_usd=request.getParameter("gross_amt_usd")==null?"":request.getParameter("gross_amt_usd");
		String tax_struc_cd=request.getParameter("tax_struc_cd")==null?"":request.getParameter("tax_struc_cd");
		String dr_cr_doc_no=request.getParameter("dr_cr_doc_no")==null?"":request.getParameter("dr_cr_doc_no");
		String tax_amt_inr=request.getParameter("tax_amt_inr")==null?"":request.getParameter("tax_amt_inr");
		
		String dr_cr_gross_usd=request.getParameter("dr_cr_gross_usd")==null?"":request.getParameter("dr_cr_gross_usd");
		String dr_cr_gross_amt=request.getParameter("dr_cr_gross_amt")==null?"":request.getParameter("dr_cr_gross_amt");
		String dr_cr_net_amt=request.getParameter("dr_cr_net_amt")==null?"":request.getParameter("dr_cr_net_amt");
		
		String dr_cr_fin_year= request.getParameter("dr_cr_fin_year")==null?"":request.getParameter("dr_cr_fin_year");
		String form_id = request.getParameter("form_id")==null?"":request.getParameter("form_id");
		String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
		String dr_cr ="";
		String FlagValue="Y"; //SB20160401
		String fin_yr_format = request.getParameter("fin_yr_format")==null?"":request.getParameter("fin_yr_format");
		
		if(cr_dr.trim().equalsIgnoreCase("dr"))
		{
			dr_cr ="Debit ";
		}
		else if(cr_dr.trim().equalsIgnoreCase("cr"))
		{
			dr_cr ="Credit ";
		}
		
		if(!(dr_cr_dt.trim().equals("")))
		{
			if(Integer.parseInt(dr_cr_dt.substring(3,5))<4)
			{
				dr_cr_fin_year=""+(Integer.parseInt(dr_cr_dt.substring(6))-1)+"-"+dr_cr_dt.substring(6);
			}
			else if(Integer.parseInt(dr_cr_dt.substring(3,5))>=4) 
			{
				dr_cr_fin_year=""+dr_cr_dt.substring(6)+"-"+(Integer.parseInt(dr_cr_dt.substring(6))+1);
			}			
		}
		////System.out.println("dr_cr_dt.substring(3,5) = "+dr_cr_dt.substring(3,5)+", dr_cr_fin_year ="+dr_cr_fin_year+", dr_cr_dt.substring(6)"+dr_cr_dt.substring(6));
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			////System.out.println("dr_cr_no = "+dr_cr_no);
			queryString = "SELECT DR_CR_NO,DR_CR_FIN_YEAR FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no="+plant_no+" AND " +
						  "fgsa_no="+fgsa_no+" AND sn_no="+sn_no+" AND " +
						  "contract_type='"+con_type+"' AND financial_year='"+fin_yr+"' AND " +
						  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_no)+" AND CRITERIA='"+criteria+"'  ";			
			////System.out.println("CR-DR: No#:SELECT: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{			
				if(dr_cr_fin_year.equals(rset.getString(2).trim()))
				{			
					if(rset.getString(1)!=null && !(rset.getString(1).trim().equals("")))
					{
						dr_cr_no = rset.getString(1);
					}
					else
					{
						dr_cr_no = "1";
					}				
					queryString1 = "DELETE FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no='"+plant_no+"' AND " +
		  			   "fgsa_no='"+fgsa_no+"' AND sn_no='"+sn_no+"' AND contract_type='"+con_type+"' AND CRITERIA='"+criteria+"' AND " +
		  			   "financial_year='"+fin_yr+"' AND hlpl_inv_seq_no="+Integer.parseInt(hlpl_no)+"";				
					//System.out.println("CR-DR: No#:DELETE: "+queryString1);				
					stmt1.executeUpdate(queryString1);	
				}
				
			}
			else
			{
				queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE " +
						" WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' " ;
					//SB20160611	" AND CONTRACT_TYPE='"+con_type+"' "; //SB20160502
				//System.out.println("CR-DR: MAX-No#:SELECT: "+queryString2);
				rset1 = stmt1.executeQuery(queryString2);				
				if(rset1.next())
				{
					if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
					{
						dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
					}
					else
					{
						dr_cr_no = "1";
					}
				}
				else
				{
					dr_cr_no = "1";
				}
			}
			/////SB20160502: DRCRDocNo//////////////////////
			String constant=""; String dr_cr_noFormat="";
			if(con_type.equalsIgnoreCase("S"))
				constant="SA";
			else
				constant="SE";
			if(cr_dr.equalsIgnoreCase("CR"))
				constant+="C";
			else
				constant+="D";
		/*SB20160611	if(Integer.parseInt(dr_cr_no)<10)
				dr_cr_noFormat ="00"+dr_cr_no;
			else if(Integer.parseInt(dr_cr_no)<100)
				dr_cr_noFormat ="0"+dr_cr_no;
			*/
			if(Integer.parseInt(dr_cr_no)<10)
				dr_cr_noFormat ="000"+dr_cr_no;  //SB20160611
			else if(Integer.parseInt(dr_cr_no)<100)
				dr_cr_noFormat ="00"+dr_cr_no; //SB20160611
			else if(Integer.parseInt(dr_cr_no)<1000) 
				dr_cr_noFormat ="0"+dr_cr_no; //SB20160611
			
			////SB20160601: For FY format in Doc No/////
			String t[]=fin_yr.split("-");
			String Fy1=t[0].substring(2,4);
			String Fy2=t[1].substring(2,4);
			
			if(!fin_yr.equals(fin_yr_format)) //SB20160611
			{
			String FYformat=Fy1+Fy2;
			dr_cr_doc_no = dr_cr_noFormat+"-"+constant+"-"+FYformat; //SB20160601
			}
			else //SB20160611
			{
				String FYformat=Fy1+"-"+Fy2; //SB20160611
				constant = "/"; //SB20160611
				dr_cr_doc_no = dr_cr_noFormat+constant+FYformat; //SB20160611
			}
			//SB20160601 dr_cr_doc_no = dr_cr_noFormat+"-"+constant+"-"+fin_yr; //SB20160502
			
			/////////////////////////////////////////////////
			
			queryString1 = "INSERT INTO FMS7_DR_CR_NOTE(SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
					       "SN_REV_NO,FGSA_REV_NO,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,CRITERIA,DUE_DT,EXCHG_RATE_VALUE," +
					       "DR_CR_FLAG,DR_CR_NO,DR_CR_DT,DIFF_QTY,DIFF_AMT,DAY_DIFF,DR_CR_EXG_RATE,INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,EMP_CD," +
					       "ENT_DT,FLAG,GROSS_AMT_USD,TAX_STRUCT_CD,DR_CR_DOC_NO,TAX_AMT_INR,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
					       "DR_CR_GROSS_AMT_USD,DR_CR_FIN_YEAR)VALUES("+sn_no+","+fgsa_no+","+customer_cd+","+Integer.parseInt(plant_no)+",'"+con_type+"', "+Integer.parseInt(hlpl_no)+"," +
						   "'"+fin_yr+"',"+fgsa_rev_no+","+sn_rev_no+",TO_DATE('"+inv_dt+"','DD/MM/YYYY'), " +
						   "'"+qty+"','"+sale_rate+"','"+gross_amt+"','"+net_amt+"','"+criteria+"', TO_DATE('"+due_dt+"','DD/MM/YYYY'), '"+exg_rt+"'," +
						   "'"+cr_dr+"','"+dr_cr_no+"',TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY'),'"+diff_qty+"','"+dr_cr_gross_amt+"','"+day_diff+"','"+dr_cr_exg_rt+"', " +
						   "'"+int_cd+"','"+int_rate_cal+"','"+int_amt+"','"+remark+"', "+user_cd+", sysdate, 'Y','"+gross_amt_usd+"','"+tax_struc_cd+"'," +
						   "'"+dr_cr_doc_no+"','"+tax_amt_inr+"','"+dr_cr_gross_amt+"','"+dr_cr_net_amt+"','"+dr_cr_gross_usd+"','"+dr_cr_fin_year+"')";			
			//System.out.println("CR-DR: No#:INSERT: "+queryString1);
			stmt1.executeUpdate(queryString1);
			
			if(criteria.trim().equalsIgnoreCase("D-PAY"))
			{				
				msg = dr_cr+"Note Has Been Submitted Successfully Due to Delayed Payment !!!";
				//FlagValue="D";
			}
			else if(criteria.trim().equalsIgnoreCase("DIFF-EXG"))
			{
				msg = dr_cr+"Note Has Been Submitted Successfully Due to Difference In Exchange Rate !!!";
				//FlagValue="E";
			}
			else if(criteria.trim().equalsIgnoreCase("REV_INV"))
			{
				msg = dr_cr+"CR Note: Submitted Successfully against Revarsal of Invoice !!!";
				FlagValue="X";
			}
			
			///////////////SB20160331: Entry into DLNG_INVOICE_MST //////////
			
			queryString1 = "UPDATE DLNG_INVOICE_MST SET FLAG='"+FlagValue+"' "+
				" WHERE CONTRACT_TYPE='"+con_type+"' AND  HLPL_INV_SEQ_NO='"+hlpl_no+"' "+
				" AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' AND SN_NO='"+sn_no+"' AND SN_REV_NO='"+sn_rev_no+"' AND CUSTOMER_CD='"+customer_cd+"' AND " +
				" PLANT_SEQ_NO='"+plant_no+"' AND FINANCIAL_YEAR='"+fin_yr+"'";			
				//System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
			stmt1.executeUpdate(queryString1);
		
				/////////////////////////////////////////////////////////////////
			
			////System.out.println("dr_cr ="+dr_cr+",criteria ="+criteria+"msg ="+msg);
			response.sendRedirect("../sales_invoice/frm_dr_cr_criteria.jsp?msg="+msg+"&mapping_id="+mapping_id+"&tax_struc_cd="+tax_struc_cd+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&cust_nm="+cust_nm+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&con_type="+con_type+"&plant_no="+plant_no+"&due_dt="+due_dt+"&hlpl_no="+hlpl_no+"&inv_no="+inv_no+"&inv_dt="+inv_dt+"&fin_yr="+fin_yr+"&criteria="+criteria+"&qty="+qty+"&gross_amt="+gross_amt+"&gross_amt_usd="+gross_amt_usd+"&net_amt="+net_amt+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&diff_qty="+diff_qty+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&day_diff="+day_diff+"&int_amt="+int_amt+"&int_rate="+int_rate+"&int_rate_cal="+int_rate_cal+"&int_cd="+int_cd+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = dr_cr+"Note Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_dr_cr_criteria.jsp?msg="+msg+"&mapping_id="+mapping_id+"&tax_struc_cd="+tax_struc_cd+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&cust_nm="+cust_nm+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&con_type="+con_type+"&plant_no="+plant_no+"&due_dt="+due_dt+"&hlpl_no="+hlpl_no+"&inv_no="+inv_no+"&inv_dt="+inv_dt+"&fin_yr="+fin_yr+"&criteria="+criteria+"&qty="+qty+"&gross_amt="+gross_amt+"&gross_amt_usd="+gross_amt_usd+"&net_amt="+net_amt+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&diff_qty="+diff_qty+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&day_diff="+day_diff+"&int_amt="+int_amt+"&int_rate="+int_rate+"&int_rate_cal="+int_rate_cal+"&int_cd="+int_cd+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);	
		}		
	}//End Of Method submitDebitCreditNote() ...
	
	public void submitDebitCreditNote_approval(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "submitDebitCreditNote_approval()";
		form_name = "frm_set_debit_credit_note.jsp";
		
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String con_type = request.getParameter("con_type")==null?"":request.getParameter("con_type");
		String hlpl_no[]		= request.getParameterValues("hlpl_seq_no");
		String fin_yr[] = request.getParameterValues("fin_yr");
		String inv_dt[] =  request.getParameterValues("inv_dt");
		String cust_nm[]   = request.getParameterValues("cust_nm");
		String reason[] = request.getParameterValues("reason_frm");				
		//remark = obj.replaceSingleQuotes(remark);
		String form_id = request.getParameter("form_id")==null?"":request.getParameter("form_id");
		String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
		String chkval[] = request.getParameterValues("chkval");
		
		String bill_dt = request.getParameter("bill_dt1")==null?"":request.getParameter("bill_dt1");
		String from_dt = request.getParameter("from_dt1")==null?"":request.getParameter("from_dt1");
		String to_dt = request.getParameter("to_dt1")==null?"":request.getParameter("to_dt1");
		String fy = request.getParameter("fy1")==null?"":request.getParameter("fy1");
		String comp_cd = request.getParameter("comp_cd")==null?"":request.getParameter("comp_cd");
		String emp_cd = request.getParameter("emp_cd")==null?"":request.getParameter("emp_cd");
		
		try
		{
			//RU20161113 For submitting reason of generating debit credit note.......
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			String msg="";
			
			for( int i=0;i<chkval.length;i++)
			{
				if(chkval[i].toString().equalsIgnoreCase("Y"))
				{
					queryString1 = "INSERT INTO FMS7_DR_CR_DTL(CUSTOMER_CD,CON_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
							       "INVOICE_DT,REMARK,EMP_CD," +
							       "ENT_DT)VALUES("+customer_cd+",'"+con_type+"', "+hlpl_no[i]+"," +
								   "'"+fin_yr[i]+"',TO_DATE('"+inv_dt[i]+"','DD/MM/YYYY'), " +
								   "'"+reason[i]+"', "+user_cd+", sysdate)";			
					//System.out.println("CR-DR: No#:INSERT:--RU-- "+queryString1);
					stmt1.executeUpdate(queryString1);
					msg="Submitted Successfully!!!";
				}
			}
			response.sendRedirect("../sales_invoice/frm_set_debit_credit_note.jsp?bscode="+customer_cd+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&user_cd="+user_cd+"&comp_cd="+comp_cd+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&fy="+fy+"&reasonfrm="+reason+"&msg="+msg);
			//System.out.println("url : "+url);
			dbcon.commit();
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Note Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_set_debit_credit_note.jsp?bscode="+customer_cd+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&user_cd="+emp_cd+"&comp_cd="+comp_cd+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&fy="+fy+"&reasonfrm="+reason+"&msg="+msg);
			////System.out.println("--11-->"+url);
		}		
	}//End Of Method submitDebitCreditNote_approval() ...
	
	public void submitDebitCreditNote_gen(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "submitDebitCreditNote_approval()";
		form_name = "frm_dr_cr_note.jsp";
		
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");		
		String con_type = request.getParameter("con_type")==null?"":request.getParameter("con_type");
		String plant_no = request.getParameter("plant_no")==null?"":request.getParameter("plant_no");
		String hlpl_no = request.getParameter("hlpl_no")==null?"":request.getParameter("hlpl_no");
		String fin_yr = request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
		String criteria = request.getParameter("criteria")==null?"":request.getParameter("criteria");					
		String due_dt = request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
		String inv_no = request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
		//String dr_cr_no = "1";
		String dr_cr_no = request.getParameter("dr_cr_no")==null?"1":request.getParameter("dr_cr_no");
		//System.out.println("---due_dt---"+due_dt);
		String dr_cr_dt = request.getParameter("dr_cr_dt")==null?"":request.getParameter("dr_cr_dt");
		String qty = request.getParameter("qty")==null?"":request.getParameter("qty");
		String gross_amt = request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
		String net_amt = request.getParameter("net_amt")==null?"":request.getParameter("net_amt");
		String exg_rt = request.getParameter("exg_rt")==null?"":request.getParameter("exg_rt");
		String dr_cr_exg_rt = request.getParameter("dr_cr_exg_rt")==null?"":request.getParameter("dr_cr_exg_rt");		
		String inv_dt = request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");//ADDED FOR LTCORA AND CN
		//String diff_qty = request.getParameter("diff_qty")==null?"":request.getParameter("diff_qty");
		//String day_diff = request.getParameter("day_diff")==null?"":request.getParameter("day_diff");
		//String int_cd = request.getParameter("int_cd")==null?"":request.getParameter("int_cd");
		//String int_rate = request.getParameter("int_rate")==null?"":request.getParameter("int_rate");
		//String int_rate_cal = request.getParameter("int_rate_cal")==null?"":request.getParameter("int_rate_cal");
		//String int_amt = request.getParameter("int_amt")==null?"":request.getParameter("int_amt");
		String sale_rate = request.getParameter("sale_rate")==null?"0":request.getParameter("sale_rate");				
		String cr_dr     = request.getParameter("cr_dr")==null?"":request.getParameter("cr_dr");
		//String cust_nm   = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");				
		remark = obj.replaceSingleQuotes(remark);
		String gross_amt_usd=request.getParameter("gross_amt_usd1")==null?"":request.getParameter("gross_amt_usd1");
		////System.out.println("gross_amt_usd-----"+gross_amt_usd);
		String tax_struc_cd=request.getParameter("tax_struc_cd")==null?"":request.getParameter("tax_struc_cd");
		String dr_cr_doc_no=request.getParameter("dr_cr_doc_no")==null?"":request.getParameter("dr_cr_doc_no");
		String tax_amt_inr=request.getParameter("tax_amt_inr")==null?"":request.getParameter("tax_amt_inr");
		String dr_cr_gross_usd=request.getParameter("dr_cr_gross_usd")==null?"":request.getParameter("dr_cr_gross_usd");
		String dr_cr_gross_amt=request.getParameter("dr_cr_gross_amt")==null?"":request.getParameter("dr_cr_gross_amt");
		String dr_cr_net_amt=request.getParameter("dr_cr_net_amt")==null?"":request.getParameter("dr_cr_net_amt");
		
		String dr_cr_fin_year= request.getParameter("dr_cr_fin_year")==null?"":request.getParameter("dr_cr_fin_year");
		String form_id = request.getParameter("form_id")==null?"":request.getParameter("form_id");
		String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
		String fin_yr_format = request.getParameter("fin_yr_format")==null?"":request.getParameter("fin_yr_format");
		String dr_cr_sale_rate = request.getParameter("dr_cr_sale_rate1")==null?"":request.getParameter("dr_cr_sale_rate1");
		String dr_cr_gross_amt_val = request.getParameter("dr_cr_gross_amt_val")==null?"":request.getParameter("dr_cr_gross_amt_val");
		String dr_cr_net_amt_val = request.getParameter("dr_cr_net_amt_val")==null?"":request.getParameter("dr_cr_net_amt_val");
		String chkval = request.getParameter("chkval")==null?"":request.getParameter("chkval");
		System.out.println("------dr_cr_sale_rate------->"+dr_cr_sale_rate);
		String FlagValue="Y";
		if(!(dr_cr_dt.trim().equals("")))
		{
			if(Integer.parseInt(dr_cr_dt.substring(3,5))<4)
			{
				dr_cr_fin_year=""+(Integer.parseInt(dr_cr_dt.substring(6))-1)+"-"+dr_cr_dt.substring(6);
			}
			else if(Integer.parseInt(dr_cr_dt.substring(3,5))>=4) 
			{
				dr_cr_fin_year=""+dr_cr_dt.substring(6)+"-"+(Integer.parseInt(dr_cr_dt.substring(6))+1);
			}			
		}
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			String msg="";
			
			
			String constant=""; String dr_cr_noFormat="";
			queryString = "SELECT DR_CR_NO,DR_CR_FIN_YEAR FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no="+plant_no+" AND " +
			  "fgsa_no="+fgsa_no+" AND sn_no="+sn_no+" AND " +
			  "contract_type='"+con_type+"' AND financial_year='"+fin_yr+"' AND " +
			  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_no)+" ";			
		//System.out.println("CR-DR: No#:SELECT: "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{			
			if(dr_cr_fin_year.equals(rset.getString(2).trim()))
			{			
				if(rset.getString(1)!=null && !(rset.getString(1).trim().equals("")))
				{
					dr_cr_no = rset.getString(1);
				}
				else
				{
					dr_cr_no = "1";
				}				
					queryString1 = "DELETE FROM FMS7_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no='"+plant_no+"' AND " +
				   "fgsa_no='"+fgsa_no+"' AND sn_no='"+sn_no+"' AND contract_type='"+con_type+"' AND " +
				   "financial_year='"+fin_yr+"' AND hlpl_inv_seq_no="+Integer.parseInt(hlpl_no)+"";				
					//System.out.println("CR-DR: No#:DELETE: "+queryString1);				
					stmt1.executeUpdate(queryString1);	
			}
		}
		else
		{
			if(cr_dr.equalsIgnoreCase("cr")){
				queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE " +
				" WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' " ; //RG20161229
				//SB20160611	" AND CONTRACT_TYPE='"+con_type+"' "; //SB20160502
				//System.out.println("CR-DR: MAX-No#:SELECT: "+queryString2);
				rset1 = stmt1.executeQuery(queryString2);
			}else{
			queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS7_DR_CR_NOTE " +
			" WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' AND contract_type='"+con_type+"' " ; //RG20161229
			//SB20160611	" AND CONTRACT_TYPE='"+con_type+"' "; //SB20160502
			//System.out.println("CR-DR: MAX-No#:SELECT: "+queryString2);
			rset1 = stmt1.executeQuery(queryString2);
			}
			if(rset1.next())
			{
				if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
				{
					dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
				}
				else
				{
					dr_cr_no = "1";
				}
			}
			else
			{
				dr_cr_no = "1";
			}
		}
		/////SB20160502: DRCRDocNo//////////////////////
		////COMMENTED BY RS FOR NEW GST INVOICE SEQUENCES...20/06/2017
//		if(con_type.equalsIgnoreCase("S"))
//			constant="SA";
//		else
//			constant="SE";
		if(cr_dr.equalsIgnoreCase("CR"))
			constant="C";
		else
			constant="D";
		
		if(Integer.parseInt(dr_cr_no)<10)
			dr_cr_noFormat ="00"+dr_cr_no;  
		else if(Integer.parseInt(dr_cr_no)<100)
			dr_cr_noFormat ="0"+dr_cr_no; 
		else if(Integer.parseInt(dr_cr_no)<1000) 
			dr_cr_noFormat = dr_cr_no; 
		
		String t[]=fin_yr.split("-");
		String Fy1=t[0].substring(2,4);
		String Fy2=t[1].substring(2,4);
		
//		if(!fin_yr.equals(fin_yr_format)) 
//		{
//			String FYformat=Fy1+Fy2;
//			dr_cr_doc_no = dr_cr_noFormat+"-"+constant+"-"+FYformat; 
//		}
//		else 
//		{
//			String FYformat=Fy1+"-"+Fy2; 
//			constant = "/"; 
//			dr_cr_doc_no = dr_cr_noFormat+constant+FYformat; 
//		}
		dr_cr_doc_no = constant+dr_cr_noFormat+"/"+t[0].substring(2,4)+"-"+t[1].substring(2,4); 
				
		if(criteria.equalsIgnoreCase("DIFF-EXG")){
			if(cr_dr.equalsIgnoreCase("cr")){
				FlagValue="X";
			}
		}
		if(criteria.equalsIgnoreCase("DIFF-QTY")){
			if(cr_dr.equalsIgnoreCase("cr")){
				FlagValue="X";
			}
		}
		else if(criteria.equalsIgnoreCase("DIFF-PRICE")){
			if(cr_dr.equalsIgnoreCase("cr")){
				FlagValue="X";
			}
		}
		if(criteria.equals("REV_INV"))
		{
			FlagValue="X"; // for diff-exg,diff-price,diff-qty,rev_inv ....
		}
			
			queryString1 = "INSERT INTO FMS7_DR_CR_NOTE(SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
		       "SN_REV_NO,FGSA_REV_NO,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,CRITERIA,DUE_DT,EXCHG_RATE_VALUE," +
		       "DR_CR_FLAG,DR_CR_NO,DR_CR_DT,DIFF_AMT,DR_CR_EXG_RATE,REMARK,EMP_CD," +
		       "ENT_DT,FLAG,GROSS_AMT_USD,TAX_STRUCT_CD,DR_CR_DOC_NO,TAX_AMT_INR,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
		       "DR_CR_GROSS_AMT_USD,DR_CR_FIN_YEAR,DR_CR_SALE_RATE)VALUES("+sn_no+","+fgsa_no+","+customer_cd+","+Integer.parseInt(plant_no)+",'"+con_type+"', "+Integer.parseInt(hlpl_no)+"," +
			   "'"+fin_yr+"',"+fgsa_rev_no+","+sn_rev_no+",TO_DATE('"+inv_dt+"','DD/MM/YYYY'), " +
			   "'"+qty+"','"+sale_rate+"','"+gross_amt+"','"+net_amt+"','"+criteria+"', TO_DATE('"+due_dt+"','DD/MM/YYYY'), '"+exg_rt+"'," +
			   "'"+cr_dr+"','"+dr_cr_no+"',TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY'),'"+dr_cr_gross_amt+"','"+dr_cr_exg_rt+"', " +
			   "'"+remark+"', "+user_cd+", sysdate, 'Y','"+gross_amt_usd+"','"+tax_struc_cd+"'," +
			   "'"+dr_cr_doc_no+"','"+tax_amt_inr+"','"+dr_cr_gross_amt+"','"+dr_cr_net_amt+"','"+dr_cr_gross_usd+"','"+dr_cr_fin_year+"','"+dr_cr_sale_rate+"')";			
			System.out.println("CR-DR: No#:INSERT: "+queryString1);
			stmt1.executeUpdate(queryString1);
			msg="Succesfully Submitted!!!";
			
			
			///////////////: Entry into DLNG_INVOICE_MST //////////
			queryString1 = "UPDATE DLNG_INVOICE_MST SET FLAG='"+FlagValue+"' "+
				" WHERE CONTRACT_TYPE='"+con_type+"' AND  HLPL_INV_SEQ_NO='"+hlpl_no+"' "+
				" AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' AND SN_NO='"+sn_no+"' AND SN_REV_NO='"+sn_rev_no+"' AND CUSTOMER_CD='"+customer_cd+"' AND " +
				" PLANT_SEQ_NO='"+plant_no+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND FLAG!='A'";			
				System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
			stmt1.executeUpdate(queryString1);
			
			String flag="Y";
			response.sendRedirect("../sales_invoice/frm_dr_cr_note.jsp?msg="+msg+"&tax_struc_cd="+tax_struc_cd+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&con_type="+con_type+"&plant_no="+plant_no+"&due_dt="+due_dt+"&hlpl_no="+hlpl_no+"&inv_no="+inv_no+"&inv_dt="+inv_dt+"&fin_yr="+fin_yr+"&criteria="+criteria+"&qty="+qty+"&gross_amt="+gross_amt+"&gross_amt_usd="+gross_amt_usd+"&net_amt="+net_amt+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&dr_cr_no="+dr_cr_no+"&dr_cr_doc_no="+dr_cr_doc_no+"&dr_cr_sale_rate="+dr_cr_sale_rate+"&dr_cr_net_amt2="+dr_cr_net_amt+"&dr_cr_gross_amt2="+dr_cr_gross_amt+"&chkval="+chkval+"&flag="+flag);
			////System.out.println("url : "+url);
			dbcon.commit();
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Note Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_dr_cr_note.jsp?msg="+msg+"&tax_struc_cd="+tax_struc_cd+"&dr_cr_fin_year="+dr_cr_fin_year+"&bscode="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&con_type="+con_type+"&plant_no="+plant_no+"&due_dt="+due_dt+"&hlpl_no="+hlpl_no+"&inv_no="+inv_no+"&inv_dt="+inv_dt+"&fin_yr="+fin_yr+"&criteria="+criteria+"&qty="+qty+"&gross_amt="+gross_amt+"&gross_amt_usd="+gross_amt_usd+"&net_amt="+net_amt+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			////System.out.println("--11-->"+url);
		}		
	}//End Of Method submitDebitCreditNote_approval() ...
	
	
	
	
	
	public void submitBillingDetails_tax_adj(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException
	{
		methodName = "submitBillingDetails()";
		form_name = "frm_invoice_dtl.jsp";
		
		String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");
		String customer_plant_seq_no = request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
		String bill_period_start_dt = request.getParameter("bill_period_start_date")==null?"":request.getParameter("bill_period_start_date");
		String bill_period_end_dt = request.getParameter("bill_period_end_date")==null?"":request.getParameter("bill_period_end_date");
		String due_dt = request.getParameter("due_date")==null?"":request.getParameter("due_date");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
		String customer_inv_seq_no = request.getParameter("customer_inv_seq_no")==null?"":request.getParameter("customer_inv_seq_no");
		String hlpl_Inv_Seq_No = request.getParameter("hlpl_Inv_Seq_No")==null?"0":request.getParameter("hlpl_Inv_Seq_No");
		String customer_Inv_Seq_No = request.getParameter("customer_Inv_Seq_No")==null?"":request.getParameter("customer_Inv_Seq_No");
		String financial_Year = request.getParameter("financial_Year")==null?"":request.getParameter("financial_Year");
		String contact_person_nm = request.getParameter("contact_person_nm")==null?"":request.getParameter("contact_person_nm");
		String contact_person = request.getParameter("contact_person")==null?"0":request.getParameter("contact_person");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String exch_rate_cd = request.getParameter("exch_rate_cd")==null?"0":request.getParameter("exch_rate_cd"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exch_rate_val = request.getParameter("exch_rate_val")==null?"0":request.getParameter("exch_rate_val"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exch_rate_dt = request.getParameter("exch_rate_dt")==null?"":request.getParameter("exch_rate_dt"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exchg_rate_cal_method = request.getParameter("exchg_rate_cal_method")==null?"0":request.getParameter("exchg_rate_cal_method");
		String invoice_date = request.getParameter("invoice_date")==null?bill_period_end_dt:request.getParameter("invoice_date");
		String particular_date = request.getParameter("particular_date")==null?bill_period_end_dt:request.getParameter("particular_date");
		String start_index = request.getParameter("start_index")==null?"0":request.getParameter("start_index"); //Only For Daily Basis Exchange Rate Based Customers ...
		String end_index = request.getParameter("end_index")==null?"0":request.getParameter("end_index"); //Only For Daily Basis Exchange Rate Based Customers ...
		String total_qty = request.getParameter("total_qty")==null?"0":request.getParameter("total_qty");
		String sale_price = request.getParameter("sale_price")==null?"0.0000":request.getParameter("sale_price");
		String gross_amt_usd = request.getParameter("gross_amt_usd")==null?"0":request.getParameter("gross_amt_usd");
		String gross_amt_inr = request.getParameter("gross_amt_inr")==null?"0":request.getParameter("gross_amt_inr");
		String remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
		String remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
		String remark_3 = request.getParameter("remark_3")==null?"":request.getParameter("remark_3");
		String offspec_flag = request.getParameter("offspec_flag")==null?"N":request.getParameter("offspec_flag");
		String offspec_qty = request.getParameter("offspec_qty")==null?"":request.getParameter("offspec_qty");
		String offspec_rate = request.getParameter("offspec_rate")==null?"":request.getParameter("offspec_rate");
		String mapping_id_LTCORA_CN = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id"); //ADDED FOR LTCORA AND CN
		String adv_inv_no = request.getParameter("adv_inv_no")==null?"":request.getParameter("adv_inv_no"); //ADDED FOR LTCORA AND CN
		String adv_inv_dt = request.getParameter("adv_inv_dt")==null?"":request.getParameter("adv_inv_dt"); //ADDED FOR LTCORA AND CN
		
		String pay_modee=request.getParameter("pay_type1")==null?"AP":request.getParameter("pay_type1"); //HS20160725
		String advadjFlag=request.getParameter("advflg")==null?"NA":request.getParameter("advflg"); //HS20160903
		String afteradjinramt=request.getParameter("inrAmt1")==null?"0.00":request.getParameter("inrAmt1"); //HS20160905
		
		remark_1 = obj.replaceSingleQuotes(remark_1);
		remark_2 = obj.replaceSingleQuotes(remark_2);
		remark_3 = obj.replaceSingleQuotes(remark_3);
		String remark_adj = request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
		remark_adj=obj.replaceSingleQuotes(remark_adj); 
		
		String inv_financial_year = "";
		String hlpl_invoice_sequence_no = "";
		String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
		String exchg_rate_ind = request.getParameter("exchg_rate_ind")==null?"0":request.getParameter("exchg_rate_ind");
		
		// SALES PRICE INR
		String salespriceINR = request.getParameter("salespriceINR")==null?"":request.getParameter("salespriceINR");
		String grosssalespriceINR = request.getParameter("grosssalespriceINR")==null?"":request.getParameter("grosssalespriceINR");
		
	//	//System.out.println("DISCOUNT----"+request.getParameter("Discount_flag_Setter"));
		String DiscountFlag = request.getParameter("Discount_flag_Setter")==null?"":request.getParameter("Discount_flag_Setter");
		String discount_value="";
		
		
		// Multiple currency
		String inv_cur_flag = request.getParameter("inv_cur_flag")==null?"":request.getParameter("inv_cur_flag");
		////System.out.println("INV_CUR_FLAG-->"+inv_cur_flag);
		
		
		if(DiscountFlag.equalsIgnoreCase("Y"))
		{
			discount_value=request.getParameter("discount_value")==null?"0":request.getParameter("discount_value");
		}
	//	//System.out.println("11"+discount_value);
		String TariffFlag = request.getParameter("Tariff_flag_Setter")==null?"":request.getParameter("Tariff_flag_Setter");
		String tariff="";
		String total_tariff="";
		String inv_inr_amt_tariff="";
	//	//System.out.println("TARIFF----"+TariffFlag);
		if(TariffFlag.equalsIgnoreCase("Y"))
		{
			tariff=request.getParameter("salespriceINR")==null?"0":request.getParameter("salespriceINR");
			total_tariff=request.getParameter("total_tariff")==null?"0":request.getParameter("total_tariff");
			inv_inr_amt_tariff=request.getParameter("grosssalespriceINR")==null?"0":request.getParameter("grosssalespriceINR");
		}
		
		String raw_amt_usd=request.getParameter("raw_amt_usd")==null?"0":request.getParameter("raw_amt_usd");
		String raw_amt_inr=request.getParameter("raw_amt_inr")==null?"0":request.getParameter("raw_amt_inr");
		
		
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			String [] exch_rate_value = request.getParameterValues("exch_rate_value"); //Only For Daily Basis Exchange Rate Based Customers ...
			String [] daily_inv_dt = request.getParameterValues("daily_inv_dt"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_qty = request.getParameterValues("daily_inv_qty"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_amt_usd = request.getParameterValues("daily_inv_amt_usd"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_amt_inr = request.getParameterValues("daily_inv_amt_inr"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			
			
			String adjustadvflag = request.getParameter("adjust")==null?"":request.getParameter("adjust");
			
			String tax_Structure_Dtl = "";
			String tax_Structure_Cd = "0";
			String tax_cd = "";
			double tax_amt = 0;
			double total_amt_inr = 0;
			double total_amt_usd = 0;
			double total_tax = 0;
			double total_net_amt_inr = 0;
			String tax_flag = ""; //'V' For VAT & 'C' For CST & 'S' For Service Tax Applicable For The Invoice ...
			
			String original_inv_dt="";
			//String original_inv_no="";
			/*queryString = "SELECT hlpl_inv_seq_no FROM DLNG_INVOICE_MST WHERE customer_cd="+customer_cd+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
						  "fgsa_no="+fgsa_no+" AND sn_no="+sn_no+" AND " +
						  "contract_type='"+contract_type+"' AND financial_year='"+financial_Year+"' AND " +
						  "period_end_dt=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') AND " +
						  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+"";*/
			
			queryString = "SELECT hlpl_inv_seq_no,to_char(invoice_dt,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE " +
						  "contract_type='"+contract_type+"' AND " +
						  "financial_year='"+financial_Year+"' AND " +
						  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+"";
			
			if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
					queryString+=" AND MAPPING_ID='"+mapping_id_LTCORA_CN+"'";
			}			
	//		//System.out.println("Query For Finding Out Existing Invoice With Same Data "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				original_inv_dt=rset.getString(2);
				//original_inv_no=rset.getString(3);
				queryString2 = "DELETE FROM DLNG_INVOICE_DTL WHERE " +
							   "financial_year='"+financial_Year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+" AND " +
				  			   "contract_type='"+contract_type+"'";
				
				/*queryString1 = "DELETE FROM DLNG_INVOICE_MST WHERE customer_cd="+customer_cd+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
				  			   "fgsa_no="+fgsa_no+" AND sn_no="+sn_no+" AND " +
				  			   "contract_type='"+contract_type+"' AND financial_year='"+financial_Year+"' AND " +
				  			   "period_end_dt=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+"";*/
				
				queryString1 = "DELETE FROM DLNG_INVOICE_MST WHERE " +
				  			   "contract_type='"+contract_type+"' AND " +
				  			   "financial_year='"+financial_Year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+"";
				if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
						queryString1+=" AND MAPPING_ID='"+mapping_id_LTCORA_CN+"'";
				}
				
//				//System.out.println("Query For Deleting Records From Invoice Details Table = "+queryString2);
//				//System.out.println("Query For Deleting Record From Invoice Master Table = "+queryString1);				
				stmt2.executeUpdate(queryString2);
				stmt1.executeUpdate(queryString1);						
			}
			else
			{
				original_inv_dt=invoice_date;
			}					
			/////////////////////////////////////////////////////////////////////////////////////////							
			String q2="SELECT COUNT(*) FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+sn_no+"' AND " +
			"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
			"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
			"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
			"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
			rset3=stmt3.executeQuery(q2);
			int count=0;
			if(rset3.next())
			{
				count=rset3.getInt(1);
			}
			if(count>0)
			{
				String q3="UPDATE DLNG_INVOICE_DEL_LOG SET RESTORE_FLAG='Y' WHERE SN_NO='"+sn_no+"' AND " +
			"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
			"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
			"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
			"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
				////System.out.println("QUERY to insert into delete log..Q3."+q3);
				stmt3.executeUpdate(q3);
			}
			
	////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			for(int i=0,j=Integer.parseInt(start_index); i<daily_inv_dt.length; i++,j=j+4)
			{
				if(exchg_rate_cal_method.equalsIgnoreCase("D"))
				{
					queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
								   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
								   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
								   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
								   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
								   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
				}
				else
				{
					if(exch_rate_value[j]==null || exch_rate_value[j].equals("") || exch_rate_value[j].equals(" ") || exch_rate_value[j].equalsIgnoreCase("N/A"))
					{
						exch_rate_value[j] = "0";
					}
					
					queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
								   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
								   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
								   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
								   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_value[j]))+", " +
								   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
				}
				
				total_amt_inr += Double.parseDouble(daily_inv_amt_inr[i]);
				total_amt_usd += Double.parseDouble(daily_inv_amt_usd[i]);
				
			//	////System.out.println("Query For Inserting Records Into Invoice Details Table = "+queryString2);
				
				stmt2.executeUpdate(queryString2);				
			}
			
			if(offspec_flag.trim().equalsIgnoreCase("Y"))
			{
				if(!offspec_qty.trim().equalsIgnoreCase("") && !offspec_rate.trim().equalsIgnoreCase(""))
				{
					total_amt_usd += Double.parseDouble(offspec_qty)*Double.parseDouble(offspec_rate);
					total_amt_inr = total_amt_usd*Double.parseDouble(nf2.format(Double.parseDouble(exch_rate_val)));
				}
			}
						
			double total_amount_inr_wid_tax=total_amt_inr;
			double total_amount_usd_wid_tax=total_amt_usd;
			////System.out.println("TOTAL INR------>>"+total_amt_inr);
			////System.out.println("TOTAL USD----->>"+total_amt_usd);
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
							  "A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				
		//		////System.out.println("Query For Finding Out Correct Tax Structure For "+customer_abbr+" = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_Structure_Cd = "0";
				}
			}
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							  "A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				
		//		////System.out.println("Query For Finding Out Correct Tax Structure For "+customer_abbr+" = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_Structure_Cd = "0";
				}
			}
			
			
		//NB20140904:Adjustment portion for tax calculation 
			String invgrossadjustedusd="";
			String invadjustmentamtinr="";
			String invgrossadjustedinr="";
			String invadjustmentamtusd="";
			double total_amt_inr1=total_amt_inr;
			String advance_price_usd="";
			String invadjustmentamtinr_tax="";
			String invgrossadjustedusd_tax="";
			String invgrossadjustedinr_tax="";
			
			String invadjustmentamtinr_sbc=""; //SB20160509
			String invgrossadjustedusd_sbc=""; //SB20160509
			String invgrossadjustedinr_sbc=""; //SB20160509
			
			String invadjustmentamtinr_kkc=""; //SB20160509
			String invgrossadjustedusd_kkc=""; //SB20160509
			String invgrossadjustedinr_kkc=""; //SB20160509
			
		
	
		//	////System.out.println("NET--------"+total_amt_inr);
			NumberFormat nf5=new DecimalFormat("0.00");
			
		//	//System.out.println("totall......"+total_amt_usd+"...."+total_amt_inr);			
		////////NB		
			Vector tax_code = new Vector();
			Vector tax_amount = new Vector();

			queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
						  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_Structure_Cd+" AND " +
	 					  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_Structure_Cd+" AND " +
	 					  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
		//	//System.out.println("Query For Finding Out Correct Tax Structure Details For "+customer_abbr+" = "+queryString);
			rset=stmt.executeQuery(queryString);
	 		while(rset.next())
	 		{
	 			tax_cd = rset.getString(1);
	 			
	 			if(rset.getString(3).equals("1"))
	 			{
	 				if(advadjFlag.equalsIgnoreCase("AA")){
	 					tax_amt = (Double.parseDouble(afteradjinramt)*Double.parseDouble(rset.getString(2)))/100;
	 				}else{
	 					tax_amt = (total_amt_inr*Double.parseDouble(rset.getString(2)))/100;
	 				}
	 			}
	 			else if(rset.getString(3).equals("2"))
	 			{
	 				queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_Structure_Cd+" AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_Structure_Cd+" AND " +
								  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
	 		//		//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value For "+customer_abbr+" = "+queryString1);
	 	 	 		rset1=stmt1.executeQuery(queryString1);
	 	 	 		if(rset1.next())
	 	 	 		{
 	 	 	 			if(rset1.getString(3).equals("1"))
 	 	 				{
 	 	 					tax_amt = (total_amt_inr*Double.parseDouble(rset1.getString(2)))/100;
 	 	 				}
 	 	 				
 	 	 	 			tax_amt = (tax_amt*Double.parseDouble(rset.getString(2)))/100;
	 	 	 		}
	 	 	 		else
	 	 	 		{
	 	 	 			tax_amt = 0;
	 	 	 		}
	 			}
	 			else
	 			{
	 				tax_amt = 0;
	 			}
	 			
	 			tax_code.add(tax_cd);
	 			tax_amount.add(nf.format(tax_amt));
	 		}
	 		
	 		for(int i=0; i<tax_code.size(); i++)
	 		{
	 		//JHP20120131	queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.elementAt(i)+"";	 			
	 		queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.firstElement()+"";	 		
	 	//		//System.out.println("Query For Fetching Tax Name = "+queryString);
	 			rset = stmt.executeQuery(queryString);
	 			if(rset.next())
	 			{
	 				String tax_nm = rset.getString(1).trim()==null?"":rset.getString(1).trim();
	 				
	 				if(tax_nm.contains("VAT"))
	 				{
	 					tax_flag = "V";
	 				}
	 				else if(tax_nm.contains("CST"))
	 				{
	 					tax_flag = "C";
	 				}
	 				else if(tax_nm.contains("ST"))
	 				{
	 					tax_flag = "S";
	 				}
	 				else 
	 				{
	 					tax_flag = "S";
	 				}
	 			}
	 			total_tax += Double.parseDouble(""+tax_amount.elementAt(i));
	 		}
	 		String tax_adjustment_flag="N";
	 		String priceINR2_advance_adjust_tax="0";
	 		String tax_adj_currency="INR";
	 		String tax_adj_adjustamt="0";
	 		String tax_adj_adjustsign ="-";
	 		
	 		String priceINR2_advance_adjust_sbc="0"; //SB20160509
	 		String sbc_adj_currency="INR"; //SB20160509
	 		String sbc_adj_adjustamt="0"; //SB20160509
	 		String sbc_adj_adjustsign ="-"; //SB20160509
	 		
	 		String priceINR2_advance_adjust_kkc="0"; //SB20160509
	 		String kkc_adj_currency="INR"; //SB20160509
	 		String kkc_adj_adjustamt="0"; //SB20160509
	 		String kkc_adj_adjustsign ="-"; //SB20160509
	 		
	 		double total_amt_inr2=0;
	 		double total_amt_usd2=0;
	 		if(adjustadvflag.equalsIgnoreCase("Y"))
	 		{
				String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
				String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
				String adjustsign = request.getParameter("adjustsign")==null?"-":request.getParameter("adjustsign");
				
				tax_adj_currency = request.getParameter("TAX_ADV_ADJUST_CUR")==null?"INR":request.getParameter("TAX_ADV_ADJUST_CUR");
				tax_adj_adjustamt = request.getParameter("adjustamt_tax")==null?"0":request.getParameter("adjustamt_tax");
				tax_adj_adjustsign = request.getParameter("adjustsign_tax")==null?"-":request.getParameter("adjustsign_tax");
				priceINR2_advance_adjust_tax = request.getParameter("gross_amt_inr_adjusted_tax")==null?"":request.getParameter("gross_amt_inr_adjusted_tax");
				
				//////SB20160509: SBC///////////////////////
				sbc_adj_currency = request.getParameter("SBC_ADV_ADJUST_CUR")==null?"INR":request.getParameter("SBC_ADV_ADJUST_CUR");
				sbc_adj_adjustamt = request.getParameter("adjustamt_sbc")==null?"0":request.getParameter("adjustamt_sbc");
				sbc_adj_adjustsign = request.getParameter("adjustsign_sbc")==null?"-":request.getParameter("adjustsign_sbc");
				priceINR2_advance_adjust_sbc = request.getParameter("gross_amt_inr_adjusted_sbc")==null?"":request.getParameter("gross_amt_inr_adjusted_sbc");

				////////////////////////////////////////////
				kkc_adj_currency = request.getParameter("KKC_ADV_ADJUST_CUR")==null?"INR":request.getParameter("KKC_ADV_ADJUST_CUR");
				kkc_adj_adjustamt = request.getParameter("adjustamt_kkc")==null?"0":request.getParameter("adjustamt_kkc");
				kkc_adj_adjustsign = request.getParameter("adjustsign_kkc")==null?"-":request.getParameter("adjustsign_kkc");
				priceINR2_advance_adjust_kkc = request.getParameter("gross_amt_inr_adjusted_kkc")==null?"":request.getParameter("gross_amt_inr_adjusted_kkc");

				
				//if(!tax_adj_adjustamt.trim().equalsIgnoreCase("") ){
					tax_adjustment_flag="Y";
				//}				
				//	////System.out.println("INSIDE IF----------222222222222---"+total_amt_inr+"++++"+total_amt_usd);				
				total_amt_inr=Double.parseDouble(nf5.format(total_amt_inr));
				total_amt_usd=Double.parseDouble(nf5.format(total_amt_usd));
			//	Tariff
			if(TariffFlag.equalsIgnoreCase("Y"))
	 		{
				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
				if(currency.equalsIgnoreCase("0"))
				{
			//		////System.out.println("first if..");
					invadjustmentamtinr=""+(Double.parseDouble(adjustamt)*Double.parseDouble(exch_rate_val));
					invadjustmentamtinr=nf5.format(Double.parseDouble(invadjustmentamtinr));
					invadjustmentamtusd=adjustamt;
				//	//System.out.println("............."+invadjustmentamtinr);
				}
				else
				{
					invadjustmentamtinr=adjustamt;
					invadjustmentamtusd=""+(Double.parseDouble(adjustamt)/Double.parseDouble(exch_rate_val));
					invadjustmentamtusd=nf5.format(Double.parseDouble(invadjustmentamtusd));
				}
				
				if(currency.equalsIgnoreCase("0"))
				{
					////System.out.println("first if.2."+currency);
					if(adjustsign.equalsIgnoreCase("1"))
					{
				//		//System.out.println("first if.3...");
						invgrossadjustedusd=""+((Double.parseDouble(advance_price_usd))-Double.parseDouble(invadjustmentamtusd));
						invgrossadjustedinr=""+((Double.parseDouble(inv_inr_amt_tariff))-Double.parseDouble(invadjustmentamtinr));
				//		//System.out.println("............."+inv_inr_amt_tariff);
					}
					else if(adjustsign.equalsIgnoreCase("2"))
					{
						invgrossadjustedusd=""+((Double.parseDouble(advance_price_usd))+Double.parseDouble(invadjustmentamtusd));
						invgrossadjustedinr=""+((Double.parseDouble(inv_inr_amt_tariff))+Double.parseDouble(invadjustmentamtinr));
					}
					//String tempcalamt=""+(Double.parseDouble(total_qty)*Double.parseDouble(tariff));
					String tempcalamt1=""+(Double.parseDouble(invgrossadjustedinr)); //+Double.parseDouble(tempcalamt));
			//		//System.out.println("NET@222222--"+tempcalamt1);
					total_amt_inr=Double.parseDouble(tempcalamt1);
				}
				else
				{
					double temp_inr=Double.parseDouble(inv_inr_amt_tariff);
					if(adjustsign.equalsIgnoreCase("1"))
					{
						//invgrossadjustedusd=""+((total_amt_usd)-Double.parseDouble(invadjustmentamtusd));
						invgrossadjustedinr=""+((temp_inr)-Double.parseDouble(invadjustmentamtinr));
					}
					else if(adjustsign.equalsIgnoreCase("2"))
					{
						//invgrossadjustedusd=""+((total_amt_usd)+Double.parseDouble(invadjustmentamtusd));
						invgrossadjustedinr=""+((temp_inr)+Double.parseDouble(invadjustmentamtinr));
					}
					invgrossadjustedusd=""+(Double.parseDouble(invgrossadjustedinr)/Double.parseDouble(exch_rate_val));
					invgrossadjustedusd=nf5.format(Double.parseDouble(invgrossadjustedusd));
					total_amt_inr=Double.parseDouble(invgrossadjustedinr);
				}
				
				total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
				total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
	 		}
			else
			{	
				
				if(currency.equalsIgnoreCase("0"))
				{
					invadjustmentamtinr=""+(Double.parseDouble(adjustamt)*Double.parseDouble(exch_rate_val));
					invadjustmentamtinr=nf5.format(Double.parseDouble(invadjustmentamtinr));
					invadjustmentamtusd=adjustamt;
				}
				else
				{
					invadjustmentamtinr=adjustamt;
					invadjustmentamtusd=""+(Double.parseDouble(adjustamt)/Double.parseDouble(exch_rate_val));
					invadjustmentamtusd=nf5.format(Double.parseDouble(invadjustmentamtusd));
				}
			//	//System.out.println("INSIDE IF----------222222222222---"+invadjustmentamtinr+"+++"+invadjustmentamtusd);
				if(adjustsign.equalsIgnoreCase("1"))
				{
					invgrossadjustedusd=""+((total_amount_usd_wid_tax)-Double.parseDouble(invadjustmentamtusd));
					//System.out.println("..."+total_amount_usd_wid_tax+".."+total_amount_inr_wid_tax);
					invgrossadjustedinr=""+((total_amount_inr_wid_tax)-Double.parseDouble(invadjustmentamtinr));
				}
				else if(adjustsign.equalsIgnoreCase("2"))
				{
					invgrossadjustedusd=""+((total_amount_usd_wid_tax)+Double.parseDouble(invadjustmentamtusd));
					invgrossadjustedinr=""+((total_amount_inr_wid_tax)+Double.parseDouble(invadjustmentamtinr));
				}
				
				
/////////////////////								
				if(tax_adj_currency.equalsIgnoreCase("USD"))
				{					
				}
				else
				{
					invadjustmentamtinr_tax=tax_adj_adjustamt;
					if(tax_adj_adjustsign.equalsIgnoreCase("1"))
					{
						invgrossadjustedinr_tax=""+((total_tax)-Double.parseDouble(invadjustmentamtinr_tax));
					}
					else if(tax_adj_adjustsign.equalsIgnoreCase("2"))
					{
						invgrossadjustedinr_tax=""+((total_tax)+Double.parseDouble(invadjustmentamtinr_tax));
					}
					
				}
///////////SB20160509:SBC Calculation://///////////
				//System.out.println("GROSS-STAX-ADJ-AMT:" +invgrossadjustedinr_tax);
		 		if(sbc_adj_currency.equalsIgnoreCase("USD"))
				{					
				}
				else
				{
					invadjustmentamtinr_sbc=sbc_adj_adjustamt;
					if(sbc_adj_adjustsign.equalsIgnoreCase("1"))
					{
						if(Double.parseDouble(invadjustmentamtinr_tax)==0)
							invgrossadjustedinr_sbc=""+((total_tax)-Double.parseDouble(invadjustmentamtinr_sbc));
						else
							invgrossadjustedinr_sbc=""+((Double.parseDouble(invgrossadjustedinr_tax))-Double.parseDouble(invadjustmentamtinr_sbc));
					}
					else if(sbc_adj_adjustsign.equalsIgnoreCase("2"))
					{
						if(Double.parseDouble(invadjustmentamtinr_tax)==0)
							invgrossadjustedinr_sbc=""+((total_tax)+Double.parseDouble(invadjustmentamtinr_sbc));
						else
							invgrossadjustedinr_sbc=""+((Double.parseDouble(invgrossadjustedinr_tax))+Double.parseDouble(invadjustmentamtinr_sbc));
					}					
				}
		 		if(sbc_adj_currency.equalsIgnoreCase("USD"))
				{					
				}
				else
				{
					invadjustmentamtinr_kkc=kkc_adj_adjustamt;
					if(kkc_adj_adjustsign.equalsIgnoreCase("1"))
					{
						if(Double.parseDouble(invadjustmentamtinr_tax)==0)
							invgrossadjustedinr_kkc=""+((total_tax)-Double.parseDouble(invadjustmentamtinr_kkc));
						else
							invgrossadjustedinr_kkc=""+((Double.parseDouble(invgrossadjustedinr_sbc))-Double.parseDouble(invadjustmentamtinr_kkc));
					}
					else if(kkc_adj_adjustsign.equalsIgnoreCase("2"))
					{
						if(Double.parseDouble(invadjustmentamtinr_tax)==0)
							invgrossadjustedinr_kkc=""+((total_tax)+Double.parseDouble(invadjustmentamtinr_kkc));
						else
							invgrossadjustedinr_kkc=""+((Double.parseDouble(invgrossadjustedinr_sbc))+Double.parseDouble(invadjustmentamtinr_kkc));
					}					
				}
				total_amt_inr2=Double.parseDouble(invgrossadjustedinr);
				total_amt_usd2=Double.parseDouble(invgrossadjustedusd);
				
//SB20160509				total_amt_inr=Double.parseDouble(invgrossadjustedinr)+Double.parseDouble(invgrossadjustedinr_tax);
				total_amt_inr=Double.parseDouble(invgrossadjustedinr)+Double.parseDouble(invgrossadjustedinr_kkc);//SB20160509
				total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
				total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
				//System.out.println("INSIDE IF----------222222222222---"+total_amt_inr);
			}
			total_net_amt_inr = total_amt_inr;
 		}
		else
		{
			if(TariffFlag.equalsIgnoreCase("Y"))
	 		{
				total_amt_inr=Double.parseDouble(inv_inr_amt_tariff);
				total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
				total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
				
	 		}
			
			
			total_amt_inr2=Double.parseDouble(raw_amt_inr);
			total_amt_usd2=Double.parseDouble(raw_amt_usd);
			
			total_net_amt_inr = total_amt_inr+total_tax;
		}
		
	 		// 02/09/2014 NB Adjust advance payment
	 		
	 		String mapping_id=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
 			String inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+invoice_date;
 			String del_inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+original_inv_dt;	
 			////System.out.println(del_inv_seq_no);
 			String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id+"' AND INV_SEQ_NO='"+del_inv_seq_no+"' ";
 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
 				String mapid[]=mapping_id_LTCORA_CN.split("-");
 				query_del+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
			}
 			//System.out.println("query_del..."+query_del);
 			stmt.executeUpdate(query_del);
	 		
	 		if(adjustadvflag.equalsIgnoreCase("Y") || TariffFlag.equalsIgnoreCase("Y") || DiscountFlag.equalsIgnoreCase("Y"))//NB2010902 Added If clause for advance clause
	 		{	 	
	 			//total_amt_inr=total_amt_inr1;
				String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
				String advrecevial = request.getParameter("recevial")==null?"":request.getParameter("recevial");
				String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
				String priceINR2_advance_adjust = request.getParameter("priceINR2")==null?"":request.getParameter("priceINR2");
				
				String hiddenadvnclength = request.getParameter("hiddenadvnclength")==null?"":request.getParameter("hiddenadvnclength");
				String adjustsign = request.getParameter("adjustsign")==null?"1":request.getParameter("adjustsign");

				String advamount[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advcur[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advadt[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advadjustedamt[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advseq_no[]=new String[Integer.parseInt(hiddenadvnclength)];
				
				for(int i=0;i<Integer.parseInt(hiddenadvnclength);i++)
				{
					advamount[i]= request.getParameter("advamount"+i)==null?"":request.getParameter("advamount"+i);
					advcur[i]= request.getParameter("advcur"+i)==null?"":request.getParameter("advcur"+i);
					advadt[i]= request.getParameter("advadt"+i)==null?"":request.getParameter("advadt"+i);
					advseq_no[i]= request.getParameter("advseq_no"+i)==null?"":request.getParameter("advseq_no"+i);
					advadjustedamt[i]= request.getParameter("advadjustedamt"+i)==null?"":request.getParameter("advadjustedamt"+i);
					
		//			//System.out.println("ADVNCE AMT-------1111-----"+advamount[i]);
				}
				//String remark_adj = request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
				//String remark_adj = request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
				String tmpcur="";
				int currency_id=1;
	 			String tmprecv="";
	 			int tax_curr=1;
	 			int sbc_curr=1; //SB20160509
	 			int kkc_curr=1; //SB20160509
	 			
	 			if(currency.equalsIgnoreCase("0"))
	 			{
	 				tmpcur="U";
	 				currency_id=2;
	 			}
	 			else
	 			{
	 				tmpcur="I";
	 				currency_id=1;
	 			}
	 			if(advrecevial.equalsIgnoreCase("0"))
	 			{
	 				tmprecv="Y";
	 			}
	 			else
	 			{
	 				tmprecv="N";
	 			}	
	 			
	 			if(tax_adj_currency.equalsIgnoreCase("USD"))
	 			{
	 				tax_curr=2;
	 			}else
	 			{
	 				tax_curr=1;
	 			}
	 			
	 			if(sbc_adj_currency.equalsIgnoreCase("USD")) //SB20160509
	 			{
	 				sbc_curr=2;
	 			}
	 			else
	 			{
	 				sbc_curr=1;
	 			}
	 			if(kkc_adj_currency.equalsIgnoreCase("USD")) //SB20160509
	 			{
	 				kkc_curr=2;
	 			}
	 			else
	 			{
	 				kkc_curr=1;
	 			}
	 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
	 				   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
	 				   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
	 				   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
	 				   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
	 				   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag, " +
	 				   "INV_AMT_USD, INV_AMT_INR, INV_CUR_FLAG," +
	 				   "MAPPING_ID" +//ADDED FOR LTCORA AND CN
	 				   ",ADV_INV_NO,ADV_INV_DT,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG) " + //ADDED FOR ADVANCE INVOICE NO---&& HS20160725 PAY_TYPE
	 				   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
	 				   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
	 				   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
	 				   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
	 				   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
	 				   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr2)+", "+nf.format(total_amt_usd2)+", " +
	 				   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
	 				   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
	 				   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
	 				   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), 'Y', " +
	 				   "'"+raw_amt_usd+"','"+raw_amt_inr+"','"+inv_cur_flag+"'," +
	 				   "'"+mapping_id_LTCORA_CN+"'" +//ADDED FOR LTCORA AND CN
	 				   ",'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+pay_modee+"' ,'"+advadjFlag+"')"; //ADDED FOR ADVANCE INVOICE NO
	 			
	 			//System.out.println("Query For Inserting Record Into Invoice Master Table Adjust= "+queryString1);
				
	 			stmt1.executeUpdate(queryString1);
	 			String tempseqno=fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no;
				
//	 			//System.out.println("value of Tariffflag..."+TariffFlag);
//	 			//System.out.println("value of DiscountFlag..."+DiscountFlag);
	 	//		//System.out.println("value of invoice_date..."+invoice_date);
	 			
	 			
	 			
	 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{
	 				String mapid[]=mapping_id_LTCORA_CN.split("-");
	 				if(adjustadvflag.equalsIgnoreCase("Y"))
		 			{
		 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust)/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				//System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(TariffFlag.equalsIgnoreCase("Y"))
		 			{
		 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(DiscountFlag.equalsIgnoreCase("Y"))
		 			{
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
		 				String advance_price_usd_tax=""+Double.parseDouble(priceINR2_advance_adjust_tax)/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','6','"+tax_adj_adjustamt+"','"+tax_curr+"','0','"+priceINR2_advance_adjust_tax+"','"+advance_price_usd_tax+"','Y','','Y','"+tax_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				//System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			///////////SB20160509:SBC Added
		 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
		 				String advance_price_usd_sbc=""+Double.parseDouble(priceINR2_advance_adjust_sbc)/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','7','"+sbc_adj_adjustamt+"','"+sbc_curr+"','0','"+priceINR2_advance_adjust_sbc+"','"+advance_price_usd_sbc+"','Y','','Y','"+sbc_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				//System.out.println("INV:PRICE_CD-7(SBC):INSERT:FMS7_INV_COMPO_DTL: "+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
	 				////////////^SB20160509////////////
		 			///////////KKC/////////////////////
		 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
		 				String advance_price_usd_kkc=""+Double.parseDouble(priceINR2_advance_adjust_kkc)/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','8','"+kkc_adj_adjustamt+"','"+kkc_curr+"','0','"+priceINR2_advance_adjust_kkc+"','"+advance_price_usd_kkc+"','Y','','Y','"+kkc_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				//System.out.println("INV:PRICE_CD-8(KKC):INSERT:FMS7_INV_COMPO_DTL: "+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			//////////////////////////////////
				}
	 			else
	 			{
	 				if(adjustadvflag.equalsIgnoreCase("Y"))
		 			{
		 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust)/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(TariffFlag.equalsIgnoreCase("Y"))
		 			{
		 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(DiscountFlag.equalsIgnoreCase("Y"))
		 			{
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			
	 			}
				msg="Adjustment Data Submitted Successfully..";
	 		}
	 		else
	 		{	//Before Addition of Adjustment clause (Original)
	 		
	 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
						   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
						   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
						   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
						   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
						   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag " +
						   ", INV_CUR_FLAG" +
						   ", MAPPING_ID,USER_DEFINED_DAY,INV_AMT_USD, INV_AMT_INR,PAY_TYPE,ADV_ADJ_FLG) " + //ADDED FOR LTCORA AND CN)--&&  HS20160725 PAY_TYPE
						   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
						   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
						   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
						   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
						   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
						   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
						   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
						   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
						   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
						   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", sysdate, 'Y' "+
						   ",'"+inv_cur_flag+"'," + ////20141016
						   "'"+mapping_id_LTCORA_CN+"',TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+raw_amt_usd+"','"+raw_amt_inr+"','"+pay_modee+"','"+advadjFlag+"')"; //ADDED FOR LTCORA AND CN) 
				
	 			stmt1.executeUpdate(queryString1);
	 			
	 		}
	 		String save_chk_mul_adv_inv = request.getParameter("chk_mul_adv_inv")==null?"N":request.getParameter("chk_mul_adv_inv");
	 		String save_adv_inv_value[] = request.getParameterValues("adv_inv");
	 		String save_adv_inv_date[] = request.getParameterValues("adv_inv_date");
	 		
	 		//ADDED FOR INSERTION OF ADVANCE INVOICE NOS.
	 		String query = "DELETE FROM FMS8_ADV_INV_DTL WHERE HLPL_INV_SEQ_NO = '"+hlpl_Inv_Seq_No+"' AND "
 					+ "FINANCIAL_YEAR = '"+financial_Year+"' AND CONTRACT_TYPE = '"+contract_type+"' ";
 			stmt.executeUpdate(query);
 			
 			if(save_chk_mul_adv_inv.equals("Y")) {
	 			query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
							+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
							+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
							+ "'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),"
							+ "'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
					stmt.executeUpdate(query);
	 			
	 			if(save_adv_inv_value.length > 0) {
	 				for(int i=0;i<save_adv_inv_value.length;i++) {
	 					query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
	 							+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
	 							+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
	 							+ "'"+save_adv_inv_value[i]+"',TO_DATE('"+save_adv_inv_date[i]+"','DD/MM/YYYY')"
	 							+ ",'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
	 					stmt.executeUpdate(query);
	 				}
	 			}
 			}
 			/////////////
			
			inv_financial_year = financial_Year;
			hlpl_invoice_sequence_no = hlpl_Inv_Seq_No+"/"+financial_Year;
			
			msg = "Billing Details Of "+hlpl_invoice_sequence_no+" For "+customer_abbr+" Has Been Submitted Successfully !!!";
			//response.sendRedirect( "../sales_invoice/frm_invoice_dtl.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&remark_1="+remark_1+"&remark_2="+remark_2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id_LTCORA_CN);
			response.sendRedirect("../sales_invoice/frm_invoice_dtl.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_invoice_sequence_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&remark_1="+remark_1+"&remark_2="+remark_2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id_LTCORA_CN);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Billing Details Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_invoice_dtl.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&remark_1="+remark_1+"&remark_2="+remark_2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate);	
		}		
	}//End Of Method submitBillingDetails() ...
	
	public void submitBillingDetails_tax_adj_ByRS20042017(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException
	{

		methodName = "submitBillingDetails()";
		form_name = "frm_invoice_dtl_tax_adjust.jsp";
		String msg1 = "";
		String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");
		String customer_plant_seq_no = request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
		String bill_period_start_dt = request.getParameter("bill_period_start_date")==null?"":request.getParameter("bill_period_start_date");
		String bill_period_end_dt = request.getParameter("bill_period_end_date")==null?"":request.getParameter("bill_period_end_date");
		String due_dt = request.getParameter("due_date")==null?"":request.getParameter("due_date");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
		String customer_inv_seq_no = request.getParameter("customer_inv_seq_no")==null?"":request.getParameter("customer_inv_seq_no");
		String hlpl_Inv_Seq_No = request.getParameter("hlpl_Inv_Seq_No")==null?"0":request.getParameter("hlpl_Inv_Seq_No");
		String customer_Inv_Seq_No = request.getParameter("customer_Inv_Seq_No")==null?"":request.getParameter("customer_Inv_Seq_No");
		String financial_Year = request.getParameter("financial_Year")==null?"":request.getParameter("financial_Year");
		String contact_person_nm = request.getParameter("contact_person_nm")==null?"":request.getParameter("contact_person_nm");
		String contact_person = request.getParameter("contact_person")==null?"0":request.getParameter("contact_person");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String exch_rate_cd = request.getParameter("exch_rate_cd")==null?"0":request.getParameter("exch_rate_cd"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exch_rate_val = request.getParameter("exch_rate_val")==null?"0":request.getParameter("exch_rate_val"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exch_rate_dt = request.getParameter("exch_rate_dt")==null?"":request.getParameter("exch_rate_dt"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exchg_rate_cal_method = request.getParameter("exchg_rate_cal_method")==null?"0":request.getParameter("exchg_rate_cal_method");
		String invoice_date = request.getParameter("invoice_date")==null?bill_period_end_dt:request.getParameter("invoice_date");
		String particular_date = request.getParameter("particular_date")==null?bill_period_end_dt:request.getParameter("particular_date");
		String start_index = request.getParameter("start_index")==null?"0":request.getParameter("start_index"); //Only For Daily Basis Exchange Rate Based Customers ...
		String end_index = request.getParameter("end_index")==null?"0":request.getParameter("end_index"); //Only For Daily Basis Exchange Rate Based Customers ...
		String total_qty = request.getParameter("total_qty")==null?"0":request.getParameter("total_qty");
		String sale_price = request.getParameter("sale_price")==null?"0.0000":request.getParameter("sale_price");
		String gross_amt_usd = request.getParameter("gross_amt_usd")==null?"0":request.getParameter("gross_amt_usd");
		String gross_amt_inr = request.getParameter("gross_amt_inr")==null?"0":request.getParameter("gross_amt_inr");
		String remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
		String remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
		String remark_3 = request.getParameter("remark_3")==null?"":request.getParameter("remark_3");
		String offspec_flag = request.getParameter("offspec_flag")==null?"N":request.getParameter("offspec_flag");
		String offspec_qty = request.getParameter("offspec_qty")==null?"":request.getParameter("offspec_qty");
		String offspec_rate = request.getParameter("offspec_rate")==null?"":request.getParameter("offspec_rate");
		String mapping_id_LTCORA_CN = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id"); //ADDED FOR LTCORA AND CN
		String adv_inv_no = request.getParameter("adv_inv_no")==null?"":request.getParameter("adv_inv_no"); //ADDED FOR LTCORA AND CN
		String adv_inv_dt = request.getParameter("adv_inv_dt")==null?"":request.getParameter("adv_inv_dt"); //ADDED FOR LTCORA AND CN
		
		String pay_modee=request.getParameter("pay_type1")==null?"AP":request.getParameter("pay_type1"); //HS20160725
		String advadjFlag=request.getParameter("advflg")==null?"NA":request.getParameter("advflg"); //HS20160903
		String afteradjinramt=request.getParameter("inrAmt1")==null?"0.00":request.getParameter("inrAmt1"); //HS20160905
		
		//NEW GST INVOICE SEQ NO
		String GST_INVOICE_SEQ_NO = request.getParameter("GST_INVOICE_SEQ_NO")==null?"":request.getParameter("GST_INVOICE_SEQ_NO");
				
				
		remark_1 = obj.replaceSingleQuotes(remark_1);
		remark_2 = obj.replaceSingleQuotes(remark_2);
		remark_3 = obj.replaceSingleQuotes(remark_3);
		String remark_adj = request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
		remark_adj=obj.replaceSingleQuotes(remark_adj); 
		
		String rec_remark = request.getParameter("rec_remark")==null?"":request.getParameter("rec_remark");
		rec_remark=obj.replaceSingleQuotes(rec_remark); 
		
		String inv_financial_year = "";
		String hlpl_invoice_sequence_no = "";
		String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
		String exchg_rate_ind = request.getParameter("exchg_rate_ind")==null?"0":request.getParameter("exchg_rate_ind");
		
		// SALES PRICE INR
		String salespriceINR = request.getParameter("salespriceINR")==null?"":request.getParameter("salespriceINR");
		String grosssalespriceINR = request.getParameter("grosssalespriceINR")==null?"":request.getParameter("grosssalespriceINR");
		
		String DiscountFlag = request.getParameter("Discount_flag_Setter")==null?"":request.getParameter("Discount_flag_Setter");
		String discount_value="";
		
		// Multiple currency
		String inv_cur_flag = request.getParameter("inv_cur_flag")==null?"":request.getParameter("inv_cur_flag");
		
		if(DiscountFlag.equalsIgnoreCase("Y"))
		{
			discount_value=request.getParameter("discount_value")==null?"0":request.getParameter("discount_value");
		}
		String TariffFlag = request.getParameter("Tariff_flag_Setter")==null?"":request.getParameter("Tariff_flag_Setter");
		String tariff="";
		String total_tariff="";
		String inv_inr_amt_tariff="";
		if(TariffFlag.equalsIgnoreCase("Y"))
		{
			tariff=request.getParameter("salespriceINR")==null?"0":request.getParameter("salespriceINR");
			total_tariff=request.getParameter("total_tariff")==null?"0":request.getParameter("total_tariff");
			inv_inr_amt_tariff=request.getParameter("grosssalespriceINR")==null?"0":request.getParameter("grosssalespriceINR");
		}
		
		String raw_amt_usd=request.getParameter("raw_amt_usd")==null?"0":request.getParameter("raw_amt_usd");
		String raw_amt_inr=request.getParameter("raw_amt_inr")==null?"0":request.getParameter("raw_amt_inr");
		
		String date_flag = request.getParameter("date_flag")==null?"false":request.getParameter("date_flag");
		
		form_id = "181";
		form_nm = "Invoice Preparation";
		
		try {
			String q = "SELECT FORM_NAME FROM SEC_FORM_MST WHERE FORM_CD='"+form_id+"' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			String [] exch_rate_value = request.getParameterValues("exch_rate_value"); //Only For Daily Basis Exchange Rate Based Customers ...
			String [] daily_inv_dt = request.getParameterValues("daily_inv_dt"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_qty = request.getParameterValues("daily_inv_qty"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_amt_usd = request.getParameterValues("daily_inv_amt_usd"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_amt_inr = request.getParameterValues("daily_inv_amt_inr"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...

			String adjustadvflag = request.getParameter("adjust")==null?"":request.getParameter("adjust");
			
			String tax_Structure_Dtl = "";
			String tax_Structure_Cd = "0";
			String tax_cd = "";
			double tax_amt = 0;
			double total_amt_inr = 0;
			double total_amt_usd = 0;
			double total_tax = 0;
			double total_net_amt_inr = 0;
			String tax_flag = ""; //'V' For VAT & 'C' For CST & 'S' For Service Tax Applicable For The Invoice ...
			
			String original_inv_dt="";
			if(activity.equals("update")) {
//				System.out.println("In Modify Invoice-----");
				queryString = "SELECT hlpl_inv_seq_no,to_char(invoice_dt,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE " +
						  "contract_type='"+contract_type+"' AND " +
						  "financial_year='"+financial_Year+"' AND " +
						  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+" AND FLAG='Y'";
			
			if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
					queryString+=" AND MAPPING_ID='"+mapping_id_LTCORA_CN+"'";
			}			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				original_inv_dt=rset.getString(2);
			}
			else
			{
				original_inv_dt=invoice_date;
			}					
			/////////////////////////////////////////////////////////////////////////////////////////							
			String q2="SELECT COUNT(*) FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+sn_no+"' AND " +
			"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
			"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
			"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
			"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
			rset3=stmt3.executeQuery(q2);
			int count=0;
			if(rset3.next())
			{
				count=rset3.getInt(1);
			}
			if(count>0)
			{
				String q3="UPDATE DLNG_INVOICE_DEL_LOG SET RESTORE_FLAG='Y' WHERE SN_NO='"+sn_no+"' AND " +
					"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
					"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
					"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
					"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
				////System.out.println("QUERY to insert into delete log..Q3."+q3);
				stmt3.executeUpdate(q3);
			}
			
			
			Vector temp_dates = new Vector();
			queryString2 = "SELECT DISTINCT TO_CHAR(ALLOCATION_DT,'DD/MM/YYYY') FROM DLNG_INVOICE_DTL WHERE "
					+ "FINANCIAL_YEAR = '"+financial_Year+"' AND HLPL_INV_SEQ_NO = '"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
					+ "AND CONTRACT_TYPE = '"+contract_type+"' ORDER BY TO_CHAR(ALLOCATION_DT,'DD/MM/YYYY')";
			rset = stmt2.executeQuery(queryString2);
			while(rset.next()) {
				temp_dates.add(rset.getString(1));
			}
			for(int i=0,j=Integer.parseInt(start_index); i<daily_inv_dt.length; i++,j=j+4)
			{
				if(temp_dates.contains(daily_inv_dt[i])) {
					temp_dates.removeElement(daily_inv_dt[i]);
				}
				if(exchg_rate_cal_method.equalsIgnoreCase("D"))
				{
//					queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
//								   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
//								   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
//								   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
//								   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
//								   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
					
					queryString2 = "UPDATE DLNG_INVOICE_DTL SET ALLOCATION_DT=TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY') ,"
							+ "DAILY_QTY='"+daily_inv_qty[i]+"', AMT_USD = '"+daily_inv_amt_usd[i]+"', AMT_INR = '"+daily_inv_amt_inr[i]+"', "
							+ "EXCHG_RATE_CD='"+exch_rate_cd+"', EXCHG_RATE_VALUE='"+nf2.format(Double.parseDouble(exch_rate_val))+"', "
							+ "EMP_CD='"+user_cd+"', ENT_DT=sysdate, FLAG='Y' "
							+ "WHERE CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
							+ "AND FINANCIAL_YEAR='"+financial_Year+"' AND ALLOCATION_DT=TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY')";
				}
				else
				{
					if(exch_rate_value[j]==null || exch_rate_value[j].equals("") || exch_rate_value[j].equals(" ") || exch_rate_value[j].equalsIgnoreCase("N/A"))
					{
						exch_rate_value[j] = "0";
					}
					
//					queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
//								   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
//								   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
//								   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
//								   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_value[j]))+", " +
//								   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
					
					queryString2 = "UPDATE DLNG_INVOICE_DTL SET ALLOCATION_DT=TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY') ,"
							+ "DAILY_QTY='"+daily_inv_qty[i]+"', AMT_USD = '"+daily_inv_amt_usd[i]+"', AMT_INR = '"+daily_inv_amt_inr[i]+"', "
							+ "EXCHG_RATE_CD='"+exch_rate_cd+"', EXCHG_RATE_VALUE='"+nf2.format(Double.parseDouble(exch_rate_value[j]))+"', "
							+ "EMP_CD='"+user_cd+"', ENT_DT=sysdate, FLAG='Y' "
							+ "WHERE CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
							+ "AND FINANCIAL_YEAR='"+financial_Year+"' AND ALLOCATION_DT=TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY')";
				}
//				System.out.println("Inser==="+queryString2);
				stmt2.executeUpdate(queryString2);				
			}
			
			if(temp_dates.size()!=0) {
				for(int i=0;i<temp_dates.size();i++) {
					queryString2 = "DELETE FROM DLNG_INVOICE_DTL WHERE FINANCIAL_YEAR='"+financial_Year+"' AND "
							+ "CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO = '"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
							+"AND ALLOCATION_DT = TO_DATE('"+temp_dates.elementAt(i)+"','DD/MM/YYYY') ";
					stmt2.executeUpdate(queryString2);
				}
			}
			
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
							  "A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				
		//		////System.out.println("Query For Finding Out Correct Tax Structure For "+customer_abbr+" = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_Structure_Cd = "0";
				}
			}
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							  "A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				
		//		////System.out.println("Query For Finding Out Correct Tax Structure For "+customer_abbr+" = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_Structure_Cd = "0";
				}
			}
			
			total_amt_inr = request.getParameter("gross_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_inr")));
			total_amt_usd = request.getParameter("gross_amt_usd")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_usd")));
			
			NumberFormat nf5=new DecimalFormat("0.00");
			String invadjustmentamtinr="";
			String invgrossadjustedinr="";
			String invadjustmentamtusd="";
			double total_amt_inr1=total_amt_inr;
			String advance_price_usd="";
	 		
	 		String tax_adjustment_flag="N";
	 		String priceINR2_advance_adjust_tax="0";
	 		String tax_adj_currency="INR";
	 		String tax_adj_adjustamt="0";
	 		String tax_adj_adjustsign ="-";
	 		
	 		String priceINR2_advance_adjust_sbc="0"; //SB20160509
	 		String sbc_adj_currency="INR"; //SB20160509
	 		String sbc_adj_adjustamt="0"; //SB20160509
	 		String sbc_adj_adjustsign ="-"; //SB20160509
	 		
	 		String priceINR2_advance_adjust_kkc="0"; //SB20160509
	 		String kkc_adj_currency="INR"; //SB20160509
	 		String kkc_adj_adjustamt="0"; //SB20160509
	 		String kkc_adj_adjustsign ="-"; //SB20160509
	 		
	 		String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
	 		if(adjustadvflag.equalsIgnoreCase("Y"))
	 		{
				
				String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
				String adjustsign = request.getParameter("adjustsign")==null?"-":request.getParameter("adjustsign");
				
				tax_adj_currency = request.getParameter("TAX_ADV_ADJUST_CUR")==null?"INR":request.getParameter("TAX_ADV_ADJUST_CUR");
				tax_adj_adjustamt = request.getParameter("adjustamt_tax")==null?"0":request.getParameter("adjustamt_tax");
				tax_adj_adjustsign = request.getParameter("adjustsign_tax")==null?"-":request.getParameter("adjustsign_tax");
				priceINR2_advance_adjust_tax = request.getParameter("gross_amt_inr_adjusted_tax")==null?"":request.getParameter("gross_amt_inr_adjusted_tax");
				
				//////SB20160509: SBC///////////////////////
				sbc_adj_currency = request.getParameter("SBC_ADV_ADJUST_CUR")==null?"INR":request.getParameter("SBC_ADV_ADJUST_CUR");
				sbc_adj_adjustamt = request.getParameter("adjustamt_sbc")==null?"0":request.getParameter("adjustamt_sbc");
				sbc_adj_adjustsign = request.getParameter("adjustsign_sbc")==null?"-":request.getParameter("adjustsign_sbc");
				priceINR2_advance_adjust_sbc = request.getParameter("gross_amt_inr_adjusted_sbc")==null?"":request.getParameter("gross_amt_inr_adjusted_sbc");

				////////////////////////////////////////////
				kkc_adj_currency = request.getParameter("KKC_ADV_ADJUST_CUR")==null?"INR":request.getParameter("KKC_ADV_ADJUST_CUR");
				kkc_adj_adjustamt = request.getParameter("adjustamt_kkc")==null?"0":request.getParameter("adjustamt_kkc");
				kkc_adj_adjustsign = request.getParameter("adjustsign_kkc")==null?"-":request.getParameter("adjustsign_kkc");
				priceINR2_advance_adjust_kkc = request.getParameter("gross_amt_inr_adjusted_kkc")==null?"":request.getParameter("gross_amt_inr_adjusted_kkc");
				tax_adjustment_flag="Y";
			
					total_amt_inr=request.getParameter("double_final_gross_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("double_final_gross_amt_inr")));
					if(currency.equalsIgnoreCase("0"))
					{
						total_amt_usd=request.getParameter("gross_amt_inr_adjusted")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_inr_adjusted")));
					}
					else {
						total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
						total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
					}
	 		}
			else
			{	
				if(TariffFlag.equalsIgnoreCase("Y"))
		 		{
					total_amt_usd = request.getParameter("gross_USD_salespriceINR")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_USD_salespriceINR")));
					total_amt_inr = request.getParameter("grosssalespriceINR")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("grosssalespriceINR")));
		 		}
			}
			
			Vector tax_code = new Vector();
			Vector tax_amount = new Vector();
			String tax_amts[] = request.getParameterValues("tax_amts");
			String tax_codes[] = request.getParameterValues("taxcodes");
			
			for(int i=0;i<tax_amts.length;i++)
			{
				tax_cd = tax_codes[i];
				tax_code.add(tax_cd);
				tax_amt = Double.parseDouble(""+NumberFormat.getInstance().parse(tax_amts[i]));
				tax_amount.add(nf.format(tax_amt));
				//System.out.println("tax amoutnss---"+tax_amt);
				total_tax += tax_amt;
			}
	 		
	 		total_net_amt_inr = request.getParameter("net_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("net_amt_inr")));
	 		//System.out.println("Net amoun--"+total_net_amt_inr);
	 		//System.out.println("Net total_tax--"+total_tax);
	 		
	 		for(int i=0; i<tax_code.size(); i++)
	 		{
	 		queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.firstElement()+"";	 		
	 			rset = stmt.executeQuery(queryString);
	 			if(rset.next())
	 			{
	 				String tax_nm = rset.getString(1).trim()==null?"":rset.getString(1).trim();
	 				
	 				if(tax_nm.contains("VAT"))
	 				{
	 					tax_flag = "V";
	 				}
	 				else if(tax_nm.contains("CST"))
	 				{
	 					tax_flag = "C";
	 				}
	 				else if(tax_nm.contains("ST"))
	 				{
	 					tax_flag = "S";
	 				}
	 				else 
	 				{
	 					tax_flag = "S";
	 				}
	 			}
	 		}
	 		
	 		
	 		if(adjustadvflag.equalsIgnoreCase("Y") || TariffFlag.equalsIgnoreCase("Y") || DiscountFlag.equalsIgnoreCase("Y"))//NB2010902 Added If clause for advance clause
	 		{	 	
				String advrecevial = request.getParameter("recevial")==null?"":request.getParameter("recevial");
				String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
				String priceINR2_advance_adjust = request.getParameter("priceINR2")==null?"":request.getParameter("priceINR2");
				
				String hiddenadvnclength = request.getParameter("hiddenadvnclength")==null?"":request.getParameter("hiddenadvnclength");
				String adjustsign = request.getParameter("adjustsign")==null?"1":request.getParameter("adjustsign");

				String advamount[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advcur[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advadt[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advadjustedamt[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advseq_no[]=new String[Integer.parseInt(hiddenadvnclength)];
				
				for(int i=0;i<Integer.parseInt(hiddenadvnclength);i++)
				{
					advamount[i]= request.getParameter("advamount"+i)==null?"":request.getParameter("advamount"+i);
					advcur[i]= request.getParameter("advcur"+i)==null?"":request.getParameter("advcur"+i);
					advadt[i]= request.getParameter("advadt"+i)==null?"":request.getParameter("advadt"+i);
					advseq_no[i]= request.getParameter("advseq_no"+i)==null?"":request.getParameter("advseq_no"+i);
					advadjustedamt[i]= request.getParameter("advadjustedamt"+i)==null?"":request.getParameter("advadjustedamt"+i);
				}
				String tmpcur="";
				int currency_id=1;
	 			String tmprecv="";
	 			int tax_curr=1;
	 			int sbc_curr=1; //SB20160509
	 			int kkc_curr=1; //SB20160509
	 			
	 			if(currency.equalsIgnoreCase("0"))
	 			{
	 				tmpcur="U";
	 				currency_id=2;
	 			}
	 			else
	 			{
	 				tmpcur="I";
	 				currency_id=1;
	 			}
	 			if(advrecevial.equalsIgnoreCase("0"))
	 			{
	 				tmprecv="Y";
	 			}
	 			else
	 			{
	 				tmprecv="N";
	 			}	
	 			
	 			if(tax_adj_currency.equalsIgnoreCase("USD"))
	 			{
	 				tax_curr=2;
	 			}else
	 			{
	 				tax_curr=1;
	 			}
	 			
	 			if(sbc_adj_currency.equalsIgnoreCase("USD")) //SB20160509
	 			{
	 				sbc_curr=2;
	 			}
	 			else
	 			{
	 				sbc_curr=1;
	 			}
	 			if(kkc_adj_currency.equalsIgnoreCase("USD")) //SB20160509
	 			{
	 				kkc_curr=2;
	 			}
	 			else
	 			{
	 				kkc_curr=1;
	 			}
	 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
	 				   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
	 				   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
	 				   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
	 				   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
	 				   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag, " +
	 				   "INV_AMT_USD, INV_AMT_INR, INV_CUR_FLAG," +
	 				   "MAPPING_ID" +//ADDED FOR LTCORA AND CN
	 				   ",ADV_INV_NO,ADV_INV_DT,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD) " + //ADDED FOR ADVANCE INVOICE NO---&& HS20160725 PAY_TYPE
	 				   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
	 				   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
	 				   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
	 				   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
	 				   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
	 				   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
	 				   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
	 				   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
	 				   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
	 				   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), 'Y', " +
	 				   "'"+raw_amt_usd+"','"+raw_amt_inr+"','"+inv_cur_flag+"'," +
	 				   "'"+mapping_id_LTCORA_CN+"'" +//ADDED FOR LTCORA AND CN
	 				   ",'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+pay_modee+"' ,'"+advadjFlag+"','"+GST_INVOICE_SEQ_NO+"','1')"; //ADDED FOR ADVANCE INVOICE NO
	 			
	 			queryString1 = "UPDATE DLNG_INVOICE_MST SET CONTACT_PERSON_CD='"+contact_person+"', "
	 					+ "INVOICE_DT = TO_DATE('"+invoice_date+"','DD/MM/YYYY'), "
	 					+ "DUE_DT = TO_DATE('"+due_dt+"','DD/MM/YYYY'), TOTAL_QTY = '"+nf.format(Double.parseDouble(total_qty))+"', "
	 					+ "SALE_PRICE= '"+nf2.format(Double.parseDouble(sale_price))+"' , "
	 					+ "GROSS_AMT_INR='"+nf.format(total_amt_inr)+"', GROSS_AMT_USD='"+nf.format(total_amt_usd)+"', "
	 					+ "NET_AMT_INR='"+nf.format(total_net_amt_inr)+"', TAX_AMT_INR='"+nf.format(total_tax)+"', "
	 					+ "TAX_STRUCT_CD='"+tax_Structure_Cd+"', TAX_FLAG='"+tax_flag+"', EXCHG_RATE_CD='"+exch_rate_cd+"', "
	 					+ "EXCHG_RATE_DT=TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "
	 					+ "EXCHG_RATE_VALUE='"+nf2.format(Double.parseDouble(exch_rate_val))+"', EXCHG_RATE_TYPE='"+exchg_rate_cal_method+"',"
	 					+ "REMARK_1='"+remark_1+"', REMARK_2='"+remark_2+"', REMARK_3='"+remark_3+"', "
	 					+ "EXCHG_RATE_INDEX='"+exchg_rate_ind+"', OFFSPEC_FLAG='"+offspec_flag+"', "
	 					+ "OFFSPEC_QTY='"+offspec_qty+"', OFFSPEC_RATE='"+offspec_rate+"', EMP_CD='"+user_cd+"', "
	 					+ "ENT_DT=SYSDATE, "
	 					+ "FLAG='Y', INV_AMT_USD='"+raw_amt_usd+"', INV_AMT_INR='"+raw_amt_inr+"', INV_CUR_FLAG='"+inv_cur_flag+"' ,"
	 					+ "MAPPING_ID='"+mapping_id_LTCORA_CN+"', ADV_INV_NO='"+adv_inv_no+"', ADV_INV_DT=TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'), "
	 					+ "USER_DEFINED_DAY=TO_DATE('"+particular_date+"','DD/MM/YYYY'), "
	 					+ "PAY_TYPE='"+pay_modee+"', ADV_ADJ_FLG='"+advadjFlag+"', NEW_INV_SEQ_NO='"+GST_INVOICE_SEQ_NO+"', SUPPLIER_CD='1',"
	 					+ "REMARK_SPECIFICATION='"+rec_remark+"' "
	 					+ "WHERE FINANCIAL_YEAR='"+financial_Year+"' AND CONTRACT_TYPE='"+contract_type+"' AND "
	 					+ "HLPL_INV_SEQ_NO = '"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FLAG!='A' "; 
	 			
	 			stmt1.executeUpdate(queryString1);
	 			
	 			String mapping_id=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
				String inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+invoice_date;
				String del_inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+original_inv_dt;	
				String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id+"' "
						+ "AND INV_SEQ_NO='"+del_inv_seq_no+"' ";
				if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{
					String mapid[]=mapping_id_LTCORA_CN.split("-");
					query_del+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
				}
				stmt.executeUpdate(query_del);
		 		
				
	 			String tempseqno=fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no;
	 			
	 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{
	 				String mapid[]=mapping_id_LTCORA_CN.split("-");
	 				if(adjustadvflag.equalsIgnoreCase("Y"))
		 			{
	 					priceINR2_advance_adjust = priceINR2_advance_adjust.replaceAll(",", "");
		 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				//System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(TariffFlag.equalsIgnoreCase("Y"))
		 			{
		 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(DiscountFlag.equalsIgnoreCase("Y"))
		 			{
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(date_flag.equals("false")) {
			 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
			 				priceINR2_advance_adjust_tax = priceINR2_advance_adjust_tax.replaceAll(",", "");
			 				String advance_price_usd_tax=""+Double.parseDouble(priceINR2_advance_adjust_tax.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
			 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','6','"+tax_adj_adjustamt+"','"+tax_curr+"','0','"+priceINR2_advance_adjust_tax+"','"+advance_price_usd_tax+"','Y','','Y','"+tax_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				//System.out.println(".............................................."+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
			 				priceINR2_advance_adjust_sbc = priceINR2_advance_adjust_sbc.replaceAll(",", "");
			 				String advance_price_usd_sbc=""+Double.parseDouble(priceINR2_advance_adjust_sbc.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
			 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','7','"+sbc_adj_adjustamt+"','"+sbc_curr+"','0','"+priceINR2_advance_adjust_sbc+"','"+advance_price_usd_sbc+"','Y','','Y','"+sbc_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				//System.out.println("INV:PRICE_CD-7(SBC):INSERT:FMS7_INV_COMPO_DTL: "+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
			 				priceINR2_advance_adjust_kkc = priceINR2_advance_adjust_kkc.replaceAll(",", "");
			 				String advance_price_usd_kkc=""+Double.parseDouble(priceINR2_advance_adjust_kkc.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
			 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','8','"+kkc_adj_adjustamt+"','"+kkc_curr+"','0','"+priceINR2_advance_adjust_kkc+"','"+advance_price_usd_kkc+"','Y','','Y','"+kkc_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				//System.out.println("INV:PRICE_CD-8(KKC):INSERT:FMS7_INV_COMPO_DTL: "+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
		 			}
		 			if(date_flag.equals("true")) {
		 				String adjustdetails = request.getParameter("adjustdetails")==null?"N":request.getParameter("adjustdetails");
		 				String tax_code_gst[] = request.getParameterValues("tax_codes_gst");
		 				String tax_adjust[] = request.getParameterValues("adjustamt_tax");
		 				String tax_adjust_sign[] = request.getParameterValues("adjustsign_tax");
		 				String priceINR2[] = request.getParameterValues("gross_amt_inr_adjusted_tax");
		 				if(adjustdetails.equalsIgnoreCase("BA")) {
			 				for(int i=0;i<tax_adjust.length;i++) {
			 					priceINR2[i] = priceINR2[i].replaceAll(",", "");
			 					String advance_price_usd_tax=nf2.format(Double.parseDouble(priceINR2[i].replaceAll(",", ""))/Double.parseDouble(exch_rate_val));
				 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
				 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','"+tax_code_gst[i]+"','"+tax_adjust[i]+"','1','0','"+priceINR2[i]+"','"+advance_price_usd_tax+"','Y','','Y','"+tax_adjust_sign[i]+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
				 				stmt.executeUpdate(queryString1);
			 				}
		 				}
		 			}
				}
	 			else
	 			{
	 				if(adjustadvflag.equalsIgnoreCase("Y"))
		 			{
		 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(TariffFlag.equalsIgnoreCase("Y"))
		 			{
		 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(DiscountFlag.equalsIgnoreCase("Y"))
		 			{
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			
	 			}
				msg="Adjustment Data Submitted Successfully..";
	 		}
	 		else
	 		{	//Before Addition of Adjustment clause (Original)
	 		
//	 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
//						   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
//						   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
//						   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
//						   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
//						   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag " +
//						   ", INV_CUR_FLAG" +
//						   ", MAPPING_ID,USER_DEFINED_DAY,INV_AMT_USD, INV_AMT_INR,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD) " + //ADDED FOR LTCORA AND CN)--&&  HS20160725 PAY_TYPE
//						   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
//						   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
//						   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
//						   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
//						   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
//						   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
//						   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
//						   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
//						   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
//						   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", sysdate, 'Y' "+
//						   ",'"+inv_cur_flag+"'," + ////20141016
//						   "'"+mapping_id_LTCORA_CN+"',TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+raw_amt_usd+"','"+raw_amt_inr+"','"+pay_modee+"','"+advadjFlag+"','"+GST_INVOICE_SEQ_NO+"','1')"; //ADDED FOR LTCORA AND CN)
	 			
	 			queryString1 = "UPDATE DLNG_INVOICE_MST SET CONTACT_PERSON_CD='"+contact_person+"', "
	 					+ "INVOICE_DT = TO_DATE('"+invoice_date+"','DD/MM/YYYY'), "
	 					+ "DUE_DT = TO_DATE('"+due_dt+"','DD/MM/YYYY'), TOTAL_QTY = '"+nf.format(Double.parseDouble(total_qty))+"', "
	 					+ "SALE_PRICE= '"+nf2.format(Double.parseDouble(sale_price))+"' , "
	 					+ "GROSS_AMT_INR='"+nf.format(total_amt_inr)+"', GROSS_AMT_USD='"+nf.format(total_amt_usd)+"', "
	 					+ "NET_AMT_INR='"+nf.format(total_net_amt_inr)+"', TAX_AMT_INR='"+nf.format(total_tax)+"', "
	 					+ "TAX_STRUCT_CD='"+tax_Structure_Cd+"', TAX_FLAG='"+tax_flag+"', EXCHG_RATE_CD='"+exch_rate_cd+"', "
	 					+ "EXCHG_RATE_DT=TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "
	 					+ "EXCHG_RATE_VALUE='"+nf2.format(Double.parseDouble(exch_rate_val))+"', EXCHG_RATE_TYPE='"+exchg_rate_cal_method+"',"
	 					+ "REMARK_1='"+remark_1+"', REMARK_2='"+remark_2+"', REMARK_3='"+remark_3+"', "
	 					+ "EXCHG_RATE_INDEX='"+exchg_rate_ind+"', OFFSPEC_FLAG='"+offspec_flag+"', "
	 					+ "OFFSPEC_QTY='"+offspec_qty+"', OFFSPEC_RATE='"+offspec_rate+"', EMP_CD='"+user_cd+"', "
	 					+ "ENT_DT=SYSDATE, "
	 					+ "FLAG='Y', INV_AMT_USD='"+raw_amt_usd+"', INV_AMT_INR='"+raw_amt_inr+"', INV_CUR_FLAG='"+inv_cur_flag+"' ,"
	 					+ "MAPPING_ID='"+mapping_id_LTCORA_CN+"', "
	 					+ "USER_DEFINED_DAY=TO_DATE('"+particular_date+"','DD/MM/YYYY'), "
	 					+ "PAY_TYPE='"+pay_modee+"', ADV_ADJ_FLG='"+advadjFlag+"', NEW_INV_SEQ_NO='"+GST_INVOICE_SEQ_NO+"', SUPPLIER_CD='1',"
	 					+ "REMARK_SPECIFICATION='"+rec_remark+"' "
	 					+ "WHERE FINANCIAL_YEAR='"+financial_Year+"' AND CONTRACT_TYPE='"+contract_type+"' AND "
	 					+ "HLPL_INV_SEQ_NO = '"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FLAG!='A' "; 
	 			
	 			stmt1.executeUpdate(queryString1);
	 			
	 		}
	 		String save_chk_mul_adv_inv = request.getParameter("chk_mul_adv_inv")==null?"N":request.getParameter("chk_mul_adv_inv");
	 		String save_adv_inv_value[] = request.getParameterValues("adv_inv");
	 		String save_adv_inv_date[] = request.getParameterValues("adv_inv_date");
	 		
	 		//ADDED FOR INSERTION OF ADVANCE INVOICE NOS.
	 		String query = "DELETE FROM FMS8_ADV_INV_DTL WHERE HLPL_INV_SEQ_NO = '"+hlpl_Inv_Seq_No+"' AND "
					+ "FINANCIAL_YEAR = '"+financial_Year+"' AND CONTRACT_TYPE = '"+contract_type+"' ";
			stmt.executeUpdate(query);
			
			if(save_chk_mul_adv_inv.equals("Y")) {
	 			query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
							+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
							+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
							+ "'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),"
							+ "'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
					stmt.executeUpdate(query);
	 			
	 			if(save_adv_inv_value.length > 0) {
	 				for(int i=0;i<save_adv_inv_value.length;i++) {
	 					query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
	 							+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
	 							+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
	 							+ "'"+save_adv_inv_value[i]+"',TO_DATE('"+save_adv_inv_date[i]+"','DD/MM/YYYY')"
	 							+ ",'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
	 					stmt.executeUpdate(query);
	 				}
	 			}
			}
			/////////////
			
			inv_financial_year = financial_Year;
			hlpl_invoice_sequence_no = hlpl_Inv_Seq_No+"/"+financial_Year;
			if(!GST_INVOICE_SEQ_NO.equals("")) {
				hlpl_invoice_sequence_no = GST_INVOICE_SEQ_NO;
			}
			} else {
				System.out.println("Prepare Invoice....");
				
				original_inv_dt=invoice_date;
			
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
							  "A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				
		//		////System.out.println("Query For Finding Out Correct Tax Structure For "+customer_abbr+" = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_Structure_Cd = "0";
				}
			}
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							  "A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				
		//		////System.out.println("Query For Finding Out Correct Tax Structure For "+customer_abbr+" = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_Structure_Cd = "0";
				}
			}
			
			total_amt_inr = request.getParameter("gross_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_inr")));
			total_amt_usd = request.getParameter("gross_amt_usd")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_usd")));
			
			NumberFormat nf5=new DecimalFormat("0.00");
			String invadjustmentamtinr="";
			String invgrossadjustedinr="";
			String invadjustmentamtusd="";
			double total_amt_inr1=total_amt_inr;
			String advance_price_usd="";
	 		
	 		String tax_adjustment_flag="N";
	 		String priceINR2_advance_adjust_tax="0";
	 		String tax_adj_currency="INR";
	 		String tax_adj_adjustamt="0";
	 		String tax_adj_adjustsign ="-";
	 		
	 		String priceINR2_advance_adjust_sbc="0"; //SB20160509
	 		String sbc_adj_currency="INR"; //SB20160509
	 		String sbc_adj_adjustamt="0"; //SB20160509
	 		String sbc_adj_adjustsign ="-"; //SB20160509
	 		
	 		String priceINR2_advance_adjust_kkc="0"; //SB20160509
	 		String kkc_adj_currency="INR"; //SB20160509
	 		String kkc_adj_adjustamt="0"; //SB20160509
	 		String kkc_adj_adjustsign ="-"; //SB20160509
	 		
	 		String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
	 		if(adjustadvflag.equalsIgnoreCase("Y"))
	 		{
				
				String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
				String adjustsign = request.getParameter("adjustsign")==null?"-":request.getParameter("adjustsign");
				
				tax_adj_currency = request.getParameter("TAX_ADV_ADJUST_CUR")==null?"INR":request.getParameter("TAX_ADV_ADJUST_CUR");
				tax_adj_adjustamt = request.getParameter("adjustamt_tax")==null?"0":request.getParameter("adjustamt_tax");
				tax_adj_adjustsign = request.getParameter("adjustsign_tax")==null?"-":request.getParameter("adjustsign_tax");
				priceINR2_advance_adjust_tax = request.getParameter("gross_amt_inr_adjusted_tax")==null?"":request.getParameter("gross_amt_inr_adjusted_tax");
				
				//////SB20160509: SBC///////////////////////
				sbc_adj_currency = request.getParameter("SBC_ADV_ADJUST_CUR")==null?"INR":request.getParameter("SBC_ADV_ADJUST_CUR");
				sbc_adj_adjustamt = request.getParameter("adjustamt_sbc")==null?"0":request.getParameter("adjustamt_sbc");
				sbc_adj_adjustsign = request.getParameter("adjustsign_sbc")==null?"-":request.getParameter("adjustsign_sbc");
				priceINR2_advance_adjust_sbc = request.getParameter("gross_amt_inr_adjusted_sbc")==null?"":request.getParameter("gross_amt_inr_adjusted_sbc");

				////////////////////////////////////////////
				kkc_adj_currency = request.getParameter("KKC_ADV_ADJUST_CUR")==null?"INR":request.getParameter("KKC_ADV_ADJUST_CUR");
				kkc_adj_adjustamt = request.getParameter("adjustamt_kkc")==null?"0":request.getParameter("adjustamt_kkc");
				kkc_adj_adjustsign = request.getParameter("adjustsign_kkc")==null?"-":request.getParameter("adjustsign_kkc");
				priceINR2_advance_adjust_kkc = request.getParameter("gross_amt_inr_adjusted_kkc")==null?"":request.getParameter("gross_amt_inr_adjusted_kkc");
				tax_adjustment_flag="Y";
			
					total_amt_inr=request.getParameter("double_final_gross_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("double_final_gross_amt_inr")));
					if(currency.equalsIgnoreCase("0"))
					{
						total_amt_usd=request.getParameter("gross_amt_inr_adjusted")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_inr_adjusted")));
					}
					else {
						total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
						total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
					}
	 		}
			else
			{	
				if(TariffFlag.equalsIgnoreCase("Y"))
		 		{
					total_amt_usd = request.getParameter("gross_USD_salespriceINR")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_USD_salespriceINR")));
					total_amt_inr = request.getParameter("grosssalespriceINR")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("grosssalespriceINR")));
		 		}
			}
			
			Vector tax_code = new Vector();
			Vector tax_amount = new Vector();
			String tax_amts[] = request.getParameterValues("tax_amts");
			String tax_codes[] = request.getParameterValues("taxcodes");
			
			for(int i=0;i<tax_amts.length;i++)
			{
				tax_cd = tax_codes[i];
				tax_code.add(tax_cd);
				tax_amt = Double.parseDouble(""+NumberFormat.getInstance().parse(tax_amts[i]));
				tax_amount.add(nf.format(tax_amt));
				//System.out.println("tax amoutnss---"+tax_amt);
				total_tax += tax_amt;
			}
	 		
	 		total_net_amt_inr = request.getParameter("net_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("net_amt_inr")));
	 		//System.out.println("Net amoun--"+total_net_amt_inr);
	 		//System.out.println("Net total_tax--"+total_tax);
	 		
	 		for(int i=0; i<tax_code.size(); i++)
	 		{
	 		queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.firstElement()+"";	 		
	 			rset = stmt.executeQuery(queryString);
	 			if(rset.next())
	 			{
	 				String tax_nm = rset.getString(1).trim()==null?"":rset.getString(1).trim();
	 				
	 				if(tax_nm.contains("VAT"))
	 				{
	 					tax_flag = "V";
	 				}
	 				else if(tax_nm.contains("CST"))
	 				{
	 					tax_flag = "C";
	 				}
	 				else if(tax_nm.contains("ST"))
	 				{
	 					tax_flag = "S";
	 				}
	 				else 
	 				{
	 					tax_flag = "S";
	 				}
	 			}
	 		}
	 		
	 		if(adjustadvflag.equalsIgnoreCase("Y") || TariffFlag.equalsIgnoreCase("Y") || DiscountFlag.equalsIgnoreCase("Y"))//NB2010902 Added If clause for advance clause
	 		{	 	
				String advrecevial = request.getParameter("recevial")==null?"":request.getParameter("recevial");
				String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
				String priceINR2_advance_adjust = request.getParameter("priceINR2")==null?"":request.getParameter("priceINR2");
				
				String hiddenadvnclength = request.getParameter("hiddenadvnclength")==null?"":request.getParameter("hiddenadvnclength");
				String adjustsign = request.getParameter("adjustsign")==null?"1":request.getParameter("adjustsign");

				String advamount[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advcur[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advadt[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advadjustedamt[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advseq_no[]=new String[Integer.parseInt(hiddenadvnclength)];
				
				for(int i=0;i<Integer.parseInt(hiddenadvnclength);i++)
				{
					advamount[i]= request.getParameter("advamount"+i)==null?"":request.getParameter("advamount"+i);
					advcur[i]= request.getParameter("advcur"+i)==null?"":request.getParameter("advcur"+i);
					advadt[i]= request.getParameter("advadt"+i)==null?"":request.getParameter("advadt"+i);
					advseq_no[i]= request.getParameter("advseq_no"+i)==null?"":request.getParameter("advseq_no"+i);
					advadjustedamt[i]= request.getParameter("advadjustedamt"+i)==null?"":request.getParameter("advadjustedamt"+i);
				}
				String tmpcur="";
				int currency_id=1;
	 			String tmprecv="";
	 			int tax_curr=1;
	 			int sbc_curr=1; //SB20160509
	 			int kkc_curr=1; //SB20160509
	 			
	 			if(currency.equalsIgnoreCase("0"))
	 			{
	 				tmpcur="U";
	 				currency_id=2;
	 			}
	 			else
	 			{
	 				tmpcur="I";
	 				currency_id=1;
	 			}
	 			if(advrecevial.equalsIgnoreCase("0"))
	 			{
	 				tmprecv="Y";
	 			}
	 			else
	 			{
	 				tmprecv="N";
	 			}	
	 			
	 			if(tax_adj_currency.equalsIgnoreCase("USD"))
	 			{
	 				tax_curr=2;
	 			}else
	 			{
	 				tax_curr=1;
	 			}
	 			
	 			if(sbc_adj_currency.equalsIgnoreCase("USD")) //SB20160509
	 			{
	 				sbc_curr=2;
	 			}
	 			else
	 			{
	 				sbc_curr=1;
	 			}
	 			if(kkc_adj_currency.equalsIgnoreCase("USD")) //SB20160509
	 			{
	 				kkc_curr=2;
	 			}
	 			else
	 			{
	 				kkc_curr=1;
	 			}
	 			String temp_inv_no = hlpl_Inv_Seq_No;
	 			
	 			boolean flag = fetchMaxInvoiceSeqNo(contract_type,hlpl_Inv_Seq_No,financial_Year,customer_Inv_Seq_No,GST_INVOICE_SEQ_NO);
	 			if(flag) {
	 				msg1 = "(During Preparation : Invoice Seq No - "+GST_INVOICE_SEQ_NO+" ,During Submission Invoice Seq No - "+gst_inv_seq_no_new+")";
	 				hlpl_Inv_Seq_No = hlpl_inv_seq_no_new;
	 				customer_Inv_Seq_No = customer_inv_seq_no_new;
	 				GST_INVOICE_SEQ_NO = gst_inv_seq_no_new;
	 				
	 				String q_del = "DELETE FROM FMS8_ADV_INV_DTL WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(temp_inv_no)+"' AND "
 		 					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' "
 		 					+ "AND MAPPING_ID='"+mapping_id_LTCORA_CN+"' ";
 		 			stmt.executeUpdate(q_del);
	 			}
	 			
	 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
	 				   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
	 				   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
	 				   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
	 				   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
	 				   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag, " +
	 				   "INV_AMT_USD, INV_AMT_INR, INV_CUR_FLAG," +
	 				   "MAPPING_ID" +//ADDED FOR LTCORA AND CN
	 				   ",ADV_INV_NO,ADV_INV_DT,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD,REMARK_SPECIFICATION) " + //ADDED FOR ADVANCE INVOICE NO---&& HS20160725 PAY_TYPE
	 				   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
	 				   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
	 				   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
	 				   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
	 				   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
	 				   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
	 				   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
	 				   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
	 				   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
	 				   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), 'Y', " +
	 				   "'"+raw_amt_usd+"','"+raw_amt_inr+"','"+inv_cur_flag+"'," +
	 				   "'"+mapping_id_LTCORA_CN+"'" +//ADDED FOR LTCORA AND CN
	 				   ",'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+pay_modee+"' ,'"+advadjFlag+"','"+GST_INVOICE_SEQ_NO+"','1','"+rec_remark+"')"; //ADDED FOR ADVANCE INVOICE NO
	 			
	 			stmt1.executeUpdate(queryString1);
	 			
	 			if(flag) {
	 				String mapping_id1=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
		 			String inv_seq_no1=contract_type+":"+financial_Year+":"+Integer.parseInt(temp_inv_no)+":"+invoice_date;
		 			String del_inv_seq_no1=contract_type+":"+financial_Year+":"+Integer.parseInt(temp_inv_no)+":"+original_inv_dt;	
		 			String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id1+"' "
		 					+ "AND INV_SEQ_NO='"+del_inv_seq_no1+"' ";
		 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
					{
		 				String mapid[]=mapping_id_LTCORA_CN.split("-");
		 				query_del+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
					}
		 			stmt.executeUpdate(query_del);
	 			}
	 			
	 			String mapping_id=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
	 			String inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+invoice_date;
	 			String del_inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+original_inv_dt;	
	 			String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id+"' "
	 					+ "AND INV_SEQ_NO='"+del_inv_seq_no+"' ";
	 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{
	 				String mapid[]=mapping_id_LTCORA_CN.split("-");
	 				query_del+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
				}
	 			stmt.executeUpdate(query_del);
	 			
	 			String tempseqno=fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no;
	 			
	 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{
	 				String mapid[]=mapping_id_LTCORA_CN.split("-");
	 				if(adjustadvflag.equalsIgnoreCase("Y"))
		 			{
	 					priceINR2_advance_adjust = priceINR2_advance_adjust.replaceAll(",", "");
		 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				//System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(TariffFlag.equalsIgnoreCase("Y"))
		 			{
		 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(DiscountFlag.equalsIgnoreCase("Y"))
		 			{
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(date_flag.equals("false")) {
			 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
			 				priceINR2_advance_adjust_tax = priceINR2_advance_adjust_tax.replaceAll(",", "");
			 				String advance_price_usd_tax=""+Double.parseDouble(priceINR2_advance_adjust_tax.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
			 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','6','"+tax_adj_adjustamt+"','"+tax_curr+"','0','"+priceINR2_advance_adjust_tax+"','"+advance_price_usd_tax+"','Y','','Y','"+tax_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				//System.out.println(".............................................."+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
			 				priceINR2_advance_adjust_sbc = priceINR2_advance_adjust_sbc.replaceAll(",", "");
			 				String advance_price_usd_sbc=""+Double.parseDouble(priceINR2_advance_adjust_sbc.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
			 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','7','"+sbc_adj_adjustamt+"','"+sbc_curr+"','0','"+priceINR2_advance_adjust_sbc+"','"+advance_price_usd_sbc+"','Y','','Y','"+sbc_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				//System.out.println("INV:PRICE_CD-7(SBC):INSERT:FMS7_INV_COMPO_DTL: "+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			if(tax_adjustment_flag.equalsIgnoreCase("Y")){
			 				priceINR2_advance_adjust_kkc = priceINR2_advance_adjust_kkc.replaceAll(",", "");
			 				String advance_price_usd_kkc=""+Double.parseDouble(priceINR2_advance_adjust_kkc.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
			 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','8','"+kkc_adj_adjustamt+"','"+kkc_curr+"','0','"+priceINR2_advance_adjust_kkc+"','"+advance_price_usd_kkc+"','Y','','Y','"+kkc_adj_adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				//System.out.println("INV:PRICE_CD-8(KKC):INSERT:FMS7_INV_COMPO_DTL: "+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
		 			}
		 			if(date_flag.equals("true")) {
		 				String adjustdetails = request.getParameter("adjustdetails")==null?"N":request.getParameter("adjustdetails");
		 				String tax_code_gst[] = request.getParameterValues("tax_codes_gst");
		 				String tax_adjust[] = request.getParameterValues("adjustamt_tax");
		 				String tax_adjust_sign[] = request.getParameterValues("adjustsign_tax");
		 				String priceINR2[] = request.getParameterValues("gross_amt_inr_adjusted_tax");
		 				if(adjustdetails.equalsIgnoreCase("BA")) {
			 				for(int i=0;i<tax_adjust.length;i++) {
			 					priceINR2[i] = priceINR2[i].replaceAll(",", "");
			 					String advance_price_usd_tax=nf2.format(Double.parseDouble(priceINR2[i].replaceAll(",", ""))/Double.parseDouble(exch_rate_val));
				 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
				 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','"+tax_code_gst[i]+"','"+tax_adjust[i]+"','1','0','"+priceINR2[i]+"','"+advance_price_usd_tax+"','Y','','Y','"+tax_adjust_sign[i]+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
				 				stmt.executeUpdate(queryString1);
			 				}
		 				}
		 			}
				}
	 			else
	 			{
	 				if(adjustadvflag.equalsIgnoreCase("Y"))
		 			{
		 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust.replaceAll(",", ""))/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(TariffFlag.equalsIgnoreCase("Y"))
		 			{
		 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(DiscountFlag.equalsIgnoreCase("Y"))
		 			{
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			
	 			}
				msg="Adjustment Data Submitted Successfully..";
	 		}
	 		else
	 		{	//Before Addition of Adjustment clause (Original)
	 			String temp_inv_no = hlpl_Inv_Seq_No;
	 			boolean flag = fetchMaxInvoiceSeqNo(contract_type,hlpl_Inv_Seq_No,financial_Year,customer_Inv_Seq_No,GST_INVOICE_SEQ_NO);
	 			if(flag) {
	 				msg1 = "(During Preparation : Invoice Seq No - "+GST_INVOICE_SEQ_NO+" ,During Submission Invoice Seq No - "+gst_inv_seq_no_new+")";
	 				hlpl_Inv_Seq_No = hlpl_inv_seq_no_new;
	 				customer_Inv_Seq_No = customer_inv_seq_no_new;
	 				GST_INVOICE_SEQ_NO = gst_inv_seq_no_new;
	 				
	 				String q_del = "DELETE FROM FMS8_ADV_INV_DTL WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(temp_inv_no)+"' AND "
 		 					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' "
 		 					+ "AND MAPPING_ID='"+mapping_id_LTCORA_CN+"' ";
 		 			stmt.executeUpdate(q_del);
	 			}
	 		
	 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
						   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
						   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
						   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
						   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
						   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag " +
						   ", INV_CUR_FLAG" +
						   ", MAPPING_ID,USER_DEFINED_DAY,INV_AMT_USD, INV_AMT_INR,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD,REMARK_SPECIFICATION) " + //ADDED FOR LTCORA AND CN)--&&  HS20160725 PAY_TYPE
						   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
						   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
						   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
						   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
						   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
						   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
						   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
						   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
						   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
						   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", sysdate, 'Y' "+
						   ",'"+inv_cur_flag+"'," + ////20141016
						   "'"+mapping_id_LTCORA_CN+"',TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+raw_amt_usd+"','"+raw_amt_inr+"','"+pay_modee+"','"+advadjFlag+"','"+GST_INVOICE_SEQ_NO+"','1','"+rec_remark+"')"; //ADDED FOR LTCORA AND CN) 
				
	 			stmt1.executeUpdate(queryString1);
	 			
	 		}
	 		
	 		String q2="SELECT COUNT(*) FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+sn_no+"' AND " +
	 				"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
	 				"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
	 				"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
	 				"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
	 				rset3=stmt3.executeQuery(q2);
	 				int count=0;
	 				if(rset3.next())
	 				{
	 					count=rset3.getInt(1);
	 				}
	 				if(count>0)
	 				{
	 					String q3="UPDATE DLNG_INVOICE_DEL_LOG SET RESTORE_FLAG='Y' WHERE SN_NO='"+sn_no+"' AND " +
			 				"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
			 				"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
			 				"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
			 				"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
			 					////System.out.println("QUERY to insert into delete log..Q3."+q3);
	 						stmt3.executeUpdate(q3);
	 				}
	 				
	 		////////////////////////////////////////////////////////////////////////////////////////////////////////////			
	 				for(int i=0,j=Integer.parseInt(start_index); i<daily_inv_dt.length; i++,j=j+4)
	 				{
	 					if(exchg_rate_cal_method.equalsIgnoreCase("D"))
	 					{
	 						queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
	 									   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
	 									   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
	 									   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
	 									   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
	 									   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
	 					}
	 					else
	 					{
	 						if(exch_rate_value[j]==null || exch_rate_value[j].equals("") || exch_rate_value[j].equals(" ") || exch_rate_value[j].equalsIgnoreCase("N/A"))
	 						{
	 							exch_rate_value[j] = "0";
	 						}
	 						
	 						queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
	 									   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
	 									   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
	 									   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
	 									   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_value[j]))+", " +
	 									   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
	 					}
	 					stmt2.executeUpdate(queryString2);				
	 				}
	 				
	 		String save_chk_mul_adv_inv = request.getParameter("chk_mul_adv_inv")==null?"N":request.getParameter("chk_mul_adv_inv");
	 		String save_adv_inv_value[] = request.getParameterValues("adv_inv");
	 		String save_adv_inv_date[] = request.getParameterValues("adv_inv_date");
	 		
	 		//ADDED FOR INSERTION OF ADVANCE INVOICE NOS.
	 		String query = "DELETE FROM FMS8_ADV_INV_DTL WHERE HLPL_INV_SEQ_NO = '"+hlpl_Inv_Seq_No+"' AND "
 					+ "FINANCIAL_YEAR = '"+financial_Year+"' AND CONTRACT_TYPE = '"+contract_type+"' ";
 			stmt.executeUpdate(query);
 			
 			if(save_chk_mul_adv_inv.equals("Y")) {
	 			query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
							+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
							+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
							+ "'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),"
							+ "'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
					stmt.executeUpdate(query);
	 			
	 			if(save_adv_inv_value.length > 0) {
	 				for(int i=0;i<save_adv_inv_value.length;i++) {
	 					query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
	 							+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
	 							+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
	 							+ "'"+save_adv_inv_value[i]+"',TO_DATE('"+save_adv_inv_date[i]+"','DD/MM/YYYY')"
	 							+ ",'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
	 					stmt.executeUpdate(query);
	 				}
	 			}
 			}
 			/////////////
			
			inv_financial_year = financial_Year;
			hlpl_invoice_sequence_no = hlpl_Inv_Seq_No+"/"+financial_Year;
			if(!GST_INVOICE_SEQ_NO.equals("")) {
				hlpl_invoice_sequence_no = GST_INVOICE_SEQ_NO;
			}
			}
			msg = "Billing Details Of "+hlpl_invoice_sequence_no+" For "+customer_abbr+" Has Been Submitted Successfully !!!"+msg1;
			//response.sendRedirect( "../sales_invoice/frm_invoice_dtl.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&remark_1="+remark_1+"&remark_2="+remark_2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id_LTCORA_CN);
			response.sendRedirect("../sales_invoice/frm_invoice_dtl.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_invoice_sequence_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&remark_1="+remark_1+"&remark_2="+remark_2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id_LTCORA_CN);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Billing Details Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_invoice_dtl.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&remark_1="+remark_1+"&remark_2="+remark_2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate);	
		}		
	
	}
	String hlpl_inv_seq_no_new = "", customer_inv_seq_no_new = "", gst_inv_seq_no_new = "";
	
	private boolean fetchMaxInvoiceSeqNo(String contract_type,String hlpl_Inv_Seq_No,String financial_Year,String customer_Inv_Seq_No,String GST_INVOICE_SEQ_NO)
	{
		System.out.println("Fetching Max Invoice Sequence On Submission");
		boolean invoice_seq_flag = false;
		try {
			String new_inv_no = "";
			if(contract_type.trim().equals("R"))
			{
				queryString = "	SELECT MAX(no) AS no FROM "+
					"("+
					" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_Year+"' AND contract_type='R' "+
					" )  NewTable ";
			}
			else if(contract_type.trim().equals("T") || contract_type.trim().equals("C")) //ADDED FOR LTCORA AND CN
			{
					queryString = "	SELECT MAX(no) AS no FROM "+
					"("+
					" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_Year+"' "
					+ "AND (contract_type in ('C','T','Y','Z')) AND FLAG!='A' AND SUPPLIER_CD!='2' "+
					" )  NewTable ";
			}
			else
			{
					queryString = "	SELECT MAX(no) AS no FROM "+
					"("+
					" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_Year+"' AND (contract_type='S' OR contract_type='L') "+
					" )  NewTable ";
			}
			System.out.println("Max invoice seq no---"+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				String temp_inv_no = rset.getString(1);
				temp_inv_no = ""+(Integer.parseInt(temp_inv_no)+1);
				System.out.println("=="+temp_inv_no+"=="+hlpl_Inv_Seq_No);
				
				String temp_no = hlpl_Inv_Seq_No;
				if(temp_no.startsWith("0")) {
					for(int i=0;i<hlpl_Inv_Seq_No.length();i++) {
						if(temp_no.startsWith("0")) {
							temp_no = temp_no.substring(1,temp_no.length());
						}
					}
				}
				
				hlpl_Inv_Seq_No = temp_no;
				if(temp_inv_no.equals(hlpl_Inv_Seq_No)) {
					invoice_seq_flag = false;
				} else {
					invoice_seq_flag = true;
					hlpl_inv_seq_no_new = ""+(Integer.parseInt(hlpl_Inv_Seq_No)+1);
					customer_inv_seq_no_new = ""+(Integer.parseInt(customer_Inv_Seq_No)+1);
					if(!GST_INVOICE_SEQ_NO.equals("")) {
						String gst_new_no[] = GST_INVOICE_SEQ_NO.split("/");
						String temp_gst_new_no = ""+(Integer.parseInt(gst_new_no[0])+1);
						
						if(contract_type.equals("C")) {
							if(temp_gst_new_no.length()==1) {
								temp_gst_new_no = "0000"+temp_gst_new_no;
							} else if(temp_gst_new_no.length()==2) {
								temp_gst_new_no = "000"+temp_gst_new_no;
							} else if(temp_gst_new_no.length()==3) {
								temp_gst_new_no = "00"+temp_gst_new_no;
							} else if(temp_gst_new_no.length()==4) {
								temp_gst_new_no = "0"+temp_gst_new_no;
							} else if(temp_gst_new_no.length()==5) {
								temp_gst_new_no = temp_gst_new_no;
							}
						} else {
							if(temp_gst_new_no.length()==1) {
								temp_gst_new_no = "000"+temp_gst_new_no;
							} else if(temp_gst_new_no.length()==2) {
								temp_gst_new_no = "00"+temp_gst_new_no;
							} else if(temp_gst_new_no.length()==3) {
								temp_gst_new_no = "0"+temp_gst_new_no;
							} else if(temp_gst_new_no.length()==4) {
								temp_gst_new_no = temp_gst_new_no;
							}
						}
						
						gst_inv_seq_no_new = temp_gst_new_no + "/" + gst_new_no[1];
					}
				}	
			}
//			System.out.println("=="+invoice_seq_flag+"--abcd--"+hlpl_inv_seq_no_new);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return invoice_seq_flag;
	}
	
	public void submitBillingDetails_ByRS19042017(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException
	{
		methodName = "submitBillingDetails_ByRS18042017()";
		form_name = "frm_invoice_dtl.jsp";
		String msg1 = "";
		String checkPost = request.getParameter("checkPost")==null?"":request.getParameter("checkPost");
		String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");
		String customer_plant_seq_no = request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
		String bill_period_start_dt = request.getParameter("bill_period_start_date")==null?"":request.getParameter("bill_period_start_date");
		String bill_period_end_dt = request.getParameter("bill_period_end_date")==null?"":request.getParameter("bill_period_end_date");
		String due_dt = request.getParameter("due_date")==null?"":request.getParameter("due_date");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
		String customer_inv_seq_no = request.getParameter("customer_inv_seq_no")==null?"":request.getParameter("customer_inv_seq_no");
		String hlpl_Inv_Seq_No = request.getParameter("hlpl_Inv_Seq_No")==null?"0":request.getParameter("hlpl_Inv_Seq_No");
		String customer_Inv_Seq_No = request.getParameter("customer_Inv_Seq_No")==null?"":request.getParameter("customer_Inv_Seq_No");
		String financial_Year = request.getParameter("financial_Year")==null?"":request.getParameter("financial_Year");
		String contact_person_nm = request.getParameter("contact_person_nm")==null?"":request.getParameter("contact_person_nm");
		String contact_person = request.getParameter("contact_person")==null?"0":request.getParameter("contact_person");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String exch_rate_cd = request.getParameter("exch_rate_cd")==null?"0":request.getParameter("exch_rate_cd"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exch_rate_val = request.getParameter("exch_rate_val")==null?"0":request.getParameter("exch_rate_val"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exch_rate_dt = request.getParameter("exch_rate_dt")==null?"":request.getParameter("exch_rate_dt"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exchg_rate_cal_method = request.getParameter("exchg_rate_cal_method")==null?"0":request.getParameter("exchg_rate_cal_method");
		String invoice_date = request.getParameter("invoice_date")==null?bill_period_end_dt:request.getParameter("invoice_date");
		String particular_date = request.getParameter("particular_date")==null?bill_period_end_dt:request.getParameter("particular_date");
		String start_index = request.getParameter("start_index")==null?"0":request.getParameter("start_index"); //Only For Daily Basis Exchange Rate Based Customers ...
		String end_index = request.getParameter("end_index")==null?"0":request.getParameter("end_index"); //Only For Daily Basis Exchange Rate Based Customers ...
		String total_qty = request.getParameter("total_qty")==null?"0":request.getParameter("total_qty");
		String sale_price = request.getParameter("sale_price")==null?"0.0000":request.getParameter("sale_price");
		String gross_amt_usd = request.getParameter("gross_amt_usd")==null?"0":request.getParameter("gross_amt_usd");
		String gross_amt_inr = request.getParameter("gross_amt_inr")==null?"0":request.getParameter("gross_amt_inr");
		String remark_1 = request.getParameter("remark_1_1")==null?"":request.getParameter("remark_1_1");
		String remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
		String remark_3 = request.getParameter("remark_3")==null?"":request.getParameter("remark_3");
		String offspec_flag = request.getParameter("offspec_flag")==null?"N":request.getParameter("offspec_flag");
		String offspec_qty = request.getParameter("offspec_qty")==null?"":request.getParameter("offspec_qty");
		String offspec_rate = request.getParameter("offspec_rate")==null?"":request.getParameter("offspec_rate");
		String mapping_id_LTCORA_CN = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id"); //ADDED FOR LTCORA AND CN
		String adv_inv_no = request.getParameter("adv_inv_no")==null?"":request.getParameter("adv_inv_no"); //ADDED FOR LTCORA AND CN
		String adv_inv_dt = request.getParameter("adv_inv_dt")==null?"":request.getParameter("adv_inv_dt"); //ADDED FOR LTCORA AND CN
		
		String pay_modee=request.getParameter("pay_type1")==null?"AP":request.getParameter("pay_type1"); //RG20161217
		String advadjFlag=request.getParameter("advflg")==null?"NA":request.getParameter("advflg"); //RG20161217
		String truck_cd=request.getParameter("truck_cd")==null?"0":request.getParameter("truck_cd"); //Hiren_20200318
		
		//NEW GST INVOICE SEQ NO
		String GST_INVOICE_SEQ_NO = request.getParameter("GST_INVOICE_SEQ_NO")==null?"":request.getParameter("GST_INVOICE_SEQ_NO");
		
		remark_1 = obj.replaceSingleQuotes(remark_1);
		remark_2 = obj.replaceSingleQuotes(remark_2);
		remark_3 = obj.replaceSingleQuotes(remark_3);
		String remark_adj = request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
		remark_adj=obj.replaceSingleQuotes(remark_adj); 
		
		String rec_remark = request.getParameter("rec_remark")==null?"":request.getParameter("rec_remark");
		rec_remark=obj.replaceSingleQuotes(rec_remark); 
		
		String inv_financial_year = "";
		String hlpl_invoice_sequence_no = "";
		String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
		String exchg_rate_ind = request.getParameter("exchg_rate_ind")==null?"0":request.getParameter("exchg_rate_ind");
		
		String salespriceINR = request.getParameter("salespriceINR")==null?"":request.getParameter("salespriceINR");
		String grosssalespriceINR = request.getParameter("grosssalespriceINR")==null?"":request.getParameter("grosssalespriceINR");
		
		String DiscountFlag = request.getParameter("Discount_flag_Setter")==null?"":request.getParameter("Discount_flag_Setter");
		String discount_value="";
		// Multiple currency
		String inv_cur_flag = request.getParameter("inv_cur_flag")==null?"":request.getParameter("inv_cur_flag");
		
		if(DiscountFlag.equalsIgnoreCase("Y"))
		{
			discount_value=request.getParameter("discount_value")==null?"0":request.getParameter("discount_value");
		}
		String TariffFlag = request.getParameter("Tariff_flag_Setter")==null?"":request.getParameter("Tariff_flag_Setter");
		String tariff="";
		String total_tariff="";
		String inv_inr_amt_tariff="";
		if(TariffFlag.equalsIgnoreCase("Y"))
		{
			tariff=request.getParameter("salespriceINR")==null?"0":request.getParameter("salespriceINR");
			total_tariff=request.getParameter("total_tariff")==null?"0":request.getParameter("total_tariff");
			inv_inr_amt_tariff=request.getParameter("grosssalespriceINR")==null?"0":request.getParameter("grosssalespriceINR");
		}
		
		String raw_amt_usd=request.getParameter("raw_amt_usd")==null?"0":request.getParameter("raw_amt_usd");
		String raw_amt_inr=request.getParameter("raw_amt_inr")==null?"0":request.getParameter("raw_amt_inr");
		
		String tcs_app_flag=request.getParameter("tcs_app_flag")==null?"0":request.getParameter("tcs_app_flag");
		String tax_tcs_amts=request.getParameter("tax_tcs_amts")==null?"0":request.getParameter("tax_tcs_amts");
		
		String tds_amt = request.getParameter("tds_amt")==null?"":request.getParameter("tds_amt");//Hiren_20210625 
		String tds_app_flag = request.getParameter("tds_app_flag")==null?"":request.getParameter("tds_app_flag");//Hiren_20210625
		String advAmt = request.getParameter("advAmt")==null?"":request.getParameter("advAmt");//Hiren_20210707
		String sn_start_dt = request.getParameter("sn_start_dt")==null?"":request.getParameter("sn_start_dt");//Hiren_20210707
		String sn_end_dt = request.getParameter("sn_end_dt")==null?"":request.getParameter("sn_end_dt");//Hiren_20210707
		String c_form_flg = request.getParameter("c_form_flg")==null?"":request.getParameter("c_form_flg");//Hiren_20211008
		
		form_id = "181";
		form_nm = "Invoice Preparation";
		
		try {
			String q = "SELECT FORM_NAME FROM SEC_FORM_MST WHERE FORM_CD='"+form_id+"' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			String [] exch_rate_value = request.getParameterValues("exch_rate_value"); //Only For Daily Basis Exchange Rate Based Customers ...
			String [] daily_inv_dt = request.getParameterValues("daily_inv_dt"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_qty = request.getParameterValues("daily_inv_qty"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_amt_usd = request.getParameterValues("daily_inv_amt_usd"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_amt_inr = request.getParameterValues("daily_inv_amt_inr"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			
			String adjustadvflag = request.getParameter("adjust")==null?"":request.getParameter("adjust");
			//System.out.println("START OF MODIFY: HLPL_SEQ_NO: "+hlpl_Inv_Seq_No);
			
			String tax_Structure_Dtl = "";
			String tax_Structure_Cd = "0";
			String tax_cd = "";
			double tax_amt = 0;
			double total_amt_inr = 0;
			double total_amt_usd = 0;
			double total_tax = 0;
			double total_net_amt_inr = 0;
			String tax_flag = ""; //'V' For VAT & 'C' For CST & 'S' For Service Tax Applicable For The Invoice ...
			
			String original_inv_dt="";
			
			/* Hiren_20220804 checking for already generated invoice  */
			int inv_gen = 0;
			String chkSql = "select count(*) from dlng_invoice_mst where "
					+ " customer_cd = '"+customer_cd+"' "
					+ " and fgsa_no = '"+fgsa_no+"' and fgsa_rev_no = '"+fgsa_rev_no+"' "
					+ " and sn_no = '"+sn_no+"' and sn_rev_no = '"+sn_rev_no+"'"
					+ " and contract_type = '"+contract_type+"'"
					+ " and invoice_dt = to_date('"+invoice_date+"','dd/mm/yyyy')"
					+ " and plant_seq_no = '"+customer_plant_seq_no+"'"
					+ " and truck_id = '"+truck_cd+"'";
			rset = stmt.executeQuery(chkSql);
			if(rset.next()) {
				inv_gen = rset.getInt(1);
			}
			
			msg = "";
			if(inv_gen == 0) { //if no invoice generated.
			
					if(activity.equals("update")) {
	//				System.out.println("Submission---In Modify Invoice---");
					
					queryString = "SELECT hlpl_inv_seq_no,to_char(invoice_dt,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE " +
							  "contract_type='"+contract_type+"' AND " +
							  "financial_year='"+financial_Year+"' AND " +
							  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+" AND FLAG='Y'";
				
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
							queryString+=" AND MAPPING_ID='"+mapping_id_LTCORA_CN+"'";
					}			
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						original_inv_dt=rset.getString(2);
					}
					else
					{
						original_inv_dt=invoice_date;
					}	
					
					String q2="SELECT COUNT(*) FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+sn_no+"' AND " +
					"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
					"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
					"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
					"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
					rset3=stmt3.executeQuery(q2);
					int count=0;
					if(rset3.next())
					{
						count=rset3.getInt(1);
					}
					if(count>0)
					{
						String q3="UPDATE DLNG_INVOICE_DEL_LOG SET RESTORE_FLAG='Y' WHERE SN_NO='"+sn_no+"' AND " +
					"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
					"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
					"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
					"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
						//System.out.println("STEP-2:DLNG_INVOICE_DEL_LOG: "+q3);
						stmt3.executeUpdate(q3);
					}
					
					Vector temp_dates = new Vector();
					queryString2 = "SELECT DISTINCT TO_CHAR(ALLOCATION_DT,'DD/MM/YYYY') FROM DLNG_INVOICE_DTL WHERE "
							+ "FINANCIAL_YEAR = '"+financial_Year+"' AND HLPL_INV_SEQ_NO = '"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
							+ "AND CONTRACT_TYPE = '"+contract_type+"' ORDER BY TO_CHAR(ALLOCATION_DT,'DD/MM/YYYY')";
					rset = stmt2.executeQuery(queryString2);
					while(rset.next()) {
						temp_dates.add(rset.getString(1));
					}
					
					for(int i=0,j=Integer.parseInt(start_index); i<daily_inv_dt.length; i++,j=j+4)
					{
						if(temp_dates.contains(daily_inv_dt[i])) {
							temp_dates.removeElement(daily_inv_dt[i]);
						}
						if(exchg_rate_cal_method.equalsIgnoreCase("D"))
						{
	//						queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
	//									   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
	//									   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
	//									   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
	//									   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
	//									   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
							
							queryString2 = "UPDATE DLNG_INVOICE_DTL SET ALLOCATION_DT=TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY') ,"
									+ "DAILY_QTY='"+daily_inv_qty[i]+"', AMT_USD = '"+daily_inv_amt_usd[i]+"', AMT_INR = '"+daily_inv_amt_inr[i]+"', "
									+ "EXCHG_RATE_CD='"+exch_rate_cd+"', EXCHG_RATE_VALUE='"+nf2.format(Double.parseDouble(exch_rate_val))+"', "
									+ "EMP_CD='"+user_cd+"', ENT_DT=sysdate, FLAG='Y' "
									+ "WHERE CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
									+ "AND FINANCIAL_YEAR='"+financial_Year+"' AND ALLOCATION_DT=TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY')";
						}
						else
						{
							if(exch_rate_value[j]==null || exch_rate_value[j].equals("") || exch_rate_value[j].equals(" ") || exch_rate_value[j].equalsIgnoreCase("N/A"))
							{
								exch_rate_value[j] = "0";
							}
							
	//						queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
	//									   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
	//									   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
	//									   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
	//									   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_value[j]))+", " +
	//									   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
							
							queryString2 = "UPDATE DLNG_INVOICE_DTL SET ALLOCATION_DT=TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY') ,"
									+ "DAILY_QTY='"+daily_inv_qty[i]+"', AMT_USD = '"+daily_inv_amt_usd[i]+"', AMT_INR = '"+daily_inv_amt_inr[i]+"', "
									+ "EXCHG_RATE_CD='"+exch_rate_cd+"', EXCHG_RATE_VALUE='"+nf2.format(Double.parseDouble(exch_rate_value[j]))+"', "
									+ "EMP_CD='"+user_cd+"', ENT_DT=sysdate, FLAG='Y' "
									+ "WHERE CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
									+ "AND FINANCIAL_YEAR='"+financial_Year+"' AND ALLOCATION_DT=TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY')";
						}
						stmt2.executeUpdate(queryString2);				
					}
					
					if(temp_dates.size()!=0) {
						for(int i=0;i<temp_dates.size();i++) {
							queryString2 = "DELETE FROM DLNG_INVOICE_DTL WHERE FINANCIAL_YEAR='"+financial_Year+"' AND "
									+ "CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO = '"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
									+"AND ALLOCATION_DT = TO_DATE('"+temp_dates.elementAt(i)+"','DD/MM/YYYY') ";
							stmt2.executeUpdate(queryString2);
						}
					}
					
					if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
					{
						queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
									  "A.customer_cd="+customer_cd+" AND " +
									  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
							 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
							 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
							 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
						
						//System.out.println("STEP-4:FMS7_CUSTOMER_TAX_STRUCT_DTL:  For "+customer_abbr+" = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
							tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
						}
						else
						{
							tax_Structure_Dtl = "";
							tax_Structure_Cd = "0";
						}
					}
					
					total_amt_inr = request.getParameter("gross_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_inr")));
					total_amt_usd = request.getParameter("gross_amt_usd")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_usd")));
				
					String advance_price_usd="";
					
					NumberFormat nf5=new DecimalFormat("0.00");
					if(adjustadvflag.equalsIgnoreCase("Y"))
			 		{
						String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
						total_amt_inr=request.getParameter("double_final_gross_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("double_final_gross_amt_inr")));
						if(currency.equalsIgnoreCase("0"))
						{
							total_amt_usd=request.getParameter("gross_amt_inr_adjusted")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_inr_adjusted")));
						}
						else {
							total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
							total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
						}
			 		}
					else
					{
						if(TariffFlag.equalsIgnoreCase("Y"))
				 		{
							total_amt_usd = request.getParameter("gross_USD_salespriceINR")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_USD_salespriceINR")));
							total_amt_inr = request.getParameter("grosssalespriceINR")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("grosssalespriceINR")));
				 		}
					}
					
					Vector tax_code = new Vector();
					Vector tax_amount = new Vector();
					String tax_amts[] = request.getParameterValues("tax_amts");
					String tax_codes[] = request.getParameterValues("taxcodes");
					
					for(int i=0;i<tax_amts.length;i++)
					{
						tax_cd = tax_codes[i];
						tax_code.add(tax_cd);
						tax_amt = Double.parseDouble(""+NumberFormat.getInstance().parse(tax_amts[i]));
						tax_amount.add(nf.format(tax_amt));
						//System.out.println("tax amoutnss---"+tax_amt);
						total_tax += tax_amt;
					}
			 		for(int i=0; i<tax_code.size(); i++)
			 		{
			 		queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.firstElement()+"";
			 		
			 			//System.out.println("STEP-6:FMS7_TAX_STRUCTURE_DTL: Tax Name = "+queryString);
			 			rset = stmt.executeQuery(queryString);
			 			if(rset.next())
			 			{
			 				String tax_nm = rset.getString(1).trim()==null?"":rset.getString(1).trim();
			 				
			 				if(tax_nm.contains("VAT"))
			 				{
			 					tax_flag = "V";
			 				}
			 				else if(tax_nm.contains("CST"))
			 				{
			 					tax_flag = "C";
			 				}
			 				else if(tax_nm.contains("ST"))
			 				{
			 					tax_flag = "S";
			 				}
			 				else 
			 				{
			 					tax_flag = "S";
			 				}
			 			}
			 		}
			 		
			 		total_net_amt_inr = request.getParameter("net_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("net_amt_inr")));
			 		
			 		String mapping_id=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
		 			String inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+invoice_date;
		 			String del_inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+original_inv_dt;	
		 			////System.out.println(del_inv_seq_no);
		 			String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id+"' AND INV_SEQ_NO='"+del_inv_seq_no+"' ";
		 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
					{
		 				String mapid[]=mapping_id_LTCORA_CN.split("-");
		 				query_del+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
					}
		 			////System.out.println(mapping_id+" :STEP-7:DELETE:FMS7_INV_COMPO_DTL: "+query_del);
		 			stmt.executeUpdate(query_del);	
		 			
			 		if(adjustadvflag.equalsIgnoreCase("Y") || TariffFlag.equalsIgnoreCase("Y") || DiscountFlag.equalsIgnoreCase("Y"))//NB2010902 Added If clause for advance clause
			 		{	 ////System.out.println("INV: IN ");
			 			//total_amt_inr=total_amt_inr1;
						String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
						String advrecevial = request.getParameter("recevial")==null?"":request.getParameter("recevial");
						String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
						String priceINR2_advance_adjust = request.getParameter("priceINR2")==null?"":request.getParameter("priceINR2");
						
						String hiddenadvnclength = request.getParameter("hiddenadvnclength")==null?"":request.getParameter("hiddenadvnclength");
						String adjustsign = request.getParameter("adjustsign")==null?"1":request.getParameter("adjustsign");
	
						String advamount[]=new String[Integer.parseInt(hiddenadvnclength)];
						String advcur[]=new String[Integer.parseInt(hiddenadvnclength)];
						String advadt[]=new String[Integer.parseInt(hiddenadvnclength)];
						String advadjustedamt[]=new String[Integer.parseInt(hiddenadvnclength)];
						String advseq_no[]=new String[Integer.parseInt(hiddenadvnclength)];
						
						for(int i=0;i<Integer.parseInt(hiddenadvnclength);i++)
						{
							advamount[i]= request.getParameter("advamount"+i)==null?"":request.getParameter("advamount"+i);
							advcur[i]= request.getParameter("advcur"+i)==null?"":request.getParameter("advcur"+i);
							advadt[i]= request.getParameter("advadt"+i)==null?"":request.getParameter("advadt"+i);
							advseq_no[i]= request.getParameter("advseq_no"+i)==null?"":request.getParameter("advseq_no"+i);
							advadjustedamt[i]= request.getParameter("advadjustedamt"+i)==null?"":request.getParameter("advadjustedamt"+i);
						}
						String tmpcur="";
						int currency_id=1;
			 			String tmprecv="";
			 			if(currency.equalsIgnoreCase("0"))
			 			{
			 				tmpcur="U";
			 				currency_id=2;
			 			}
			 			else
			 			{
			 				tmpcur="I";
			 				currency_id=1;
			 			}
			 			if(advrecevial.equalsIgnoreCase("0"))
			 			{
			 				tmprecv="Y";
			 			}
			 			else
			 			{
			 				tmprecv="N";
			 			}		
			 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
						   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
						   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
						   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
						   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
						   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag, " +
						   "INV_AMT_USD, INV_AMT_INR, INV_CUR_FLAG," +
						   "MAPPING_ID" +//ADDED FOR LTCORA AND CN
						   ",ADV_INV_NO,ADV_INV_DT,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD)"//ADDED FOR ADVANCE INVOICE NO
						   + " checked_flag,checked_by,checked_dt,authorized_flag,authorized_by,authorized_dt,approved_flag,approved_by,approved_dt,truck_id) " + //Hiren_20200311 skip modify,check and approve stages
						   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
						   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
						   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
						   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
						   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
						   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
						   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
						   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
						   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
						   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), 'Y', " +
						   "'"+raw_amt_usd+"','"+raw_amt_inr+"','"+inv_cur_flag+"'," +
						   "'"+mapping_id_LTCORA_CN+"'" +//ADDED FOR LTCORA AND CN
						   ",'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),TO_DATE('"+particular_date+"','DD/MM/YYYY'),"
						   	+ "'"+pay_modee+"' ,'"+advadjFlag+"','"+GST_INVOICE_SEQ_NO+"','1'," //ADDED FOR ADVANCE INVOICE NO
						   	+ " null,null,null,null,null,null,'Y','"+user_cd+"',sysdate,'"+truck_cd+"')";  
			 			
			 			queryString1 = "UPDATE DLNG_INVOICE_MST SET CONTACT_PERSON_CD='"+contact_person+"', "
			 					+ "INVOICE_DT = TO_DATE('"+invoice_date+"','DD/MM/YYYY'), "
			 					+ "DUE_DT = TO_DATE('"+due_dt+"','DD/MM/YYYY'), TOTAL_QTY = '"+nf.format(Double.parseDouble(total_qty))+"', "
			 					+ "SALE_PRICE= '"+nf2.format(Double.parseDouble(sale_price))+"' , "
			 					+ "GROSS_AMT_INR='"+nf.format(total_amt_inr)+"', GROSS_AMT_USD='"+nf.format(total_amt_usd)+"', "
			 					+ "NET_AMT_INR='"+nf.format(total_net_amt_inr)+"', TAX_AMT_INR='"+nf.format(total_tax)+"', "
			 					+ "TAX_STRUCT_CD='"+tax_Structure_Cd+"', TAX_FLAG='"+tax_flag+"', EXCHG_RATE_CD='"+exch_rate_cd+"', "
			 					+ "EXCHG_RATE_DT=TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "
			 					+ "EXCHG_RATE_VALUE='"+nf2.format(Double.parseDouble(exch_rate_val))+"', EXCHG_RATE_TYPE='"+exchg_rate_cal_method+"',"
			 					+ "REMARK_1='"+remark_1+"', REMARK_2='"+remark_2+"', REMARK_3='"+remark_3+"', "
			 					+ "EXCHG_RATE_INDEX='"+exchg_rate_ind+"', OFFSPEC_FLAG='"+offspec_flag+"', "
			 					+ "OFFSPEC_QTY='"+offspec_qty+"', OFFSPEC_RATE='"+offspec_rate+"', EMP_CD='"+user_cd+"', "
			 					+ "ENT_DT=SYSDATE, "
			 					+ "FLAG='Y', INV_AMT_USD='"+raw_amt_usd+"', INV_AMT_INR='"+raw_amt_inr+"', INV_CUR_FLAG='"+inv_cur_flag+"' ,"
			 					+ "MAPPING_ID='"+mapping_id_LTCORA_CN+"', ADV_INV_NO='"+adv_inv_no+"', ADV_INV_DT=TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'), "
			 					+ "USER_DEFINED_DAY=TO_DATE('"+particular_date+"','DD/MM/YYYY'), "
			 					+ "PAY_TYPE='"+pay_modee+"', ADV_ADJ_FLG='"+advadjFlag+"', NEW_INV_SEQ_NO='"+GST_INVOICE_SEQ_NO+"', SUPPLIER_CD='1',"
			 					+ "REMARK_SPECIFICATION='"+rec_remark+"' "
			 					+ "WHERE FINANCIAL_YEAR='"+financial_Year+"' AND CONTRACT_TYPE='"+contract_type+"' AND "
			 					+ "HLPL_INV_SEQ_NO = '"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FLAG!='A' "; 
	//		 			System.out.println("STEP-8:INSERT:DLNG_INVOICE_MST:Adv Adjust= "+queryString1);
						
			 			stmt1.executeUpdate(queryString1);
			 			String tempseqno=fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no;
						
			 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
						{
			 				String mapid[]=mapping_id_LTCORA_CN.split("-");
			 				if(adjustadvflag.equalsIgnoreCase("Y"))
				 			{
				 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust)/Double.parseDouble(exch_rate_val);
				 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
				 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
				 				////System.out.println(".............................................."+queryString1);
				 				stmt.executeUpdate(queryString1);
				 			}
				 			if(TariffFlag.equalsIgnoreCase("Y"))
				 			{
				 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
				 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
									" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
				 				////System.out.println(".............................................."+queryString1);
				 				stmt.executeUpdate(queryString1);
				 			}
				 			if(DiscountFlag.equalsIgnoreCase("Y"))
				 			{
				 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
									" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
				 				////System.out.println(".............................................."+queryString1);
				 				stmt.executeUpdate(queryString1);
				 			}
			 				
						}
			 			else
			 			{
			 				if(adjustadvflag.equalsIgnoreCase("Y"))
				 			{
				 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust)/Double.parseDouble(exch_rate_val);
				 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
				 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
				 				////System.out.println(".............................................."+queryString1);
				 				stmt.executeUpdate(queryString1);
				 			}
				 			if(TariffFlag.equalsIgnoreCase("Y"))
				 			{
				 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
				 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
									" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
				 				////System.out.println(".............................................."+queryString1);
				 				stmt.executeUpdate(queryString1);
				 			}
				 			if(DiscountFlag.equalsIgnoreCase("Y"))
				 			{
				 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
									" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
				 				////System.out.println(".............................................."+queryString1);
				 				stmt.executeUpdate(queryString1);
				 			}
				 			
			 			}
						msg="Data Submitted Successfully..";
			 		}
			 		else
			 		{	
			 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
								   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
								   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
								   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
								   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
								   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag " +
								   ", INV_CUR_FLAG" +
								   ", MAPPING_ID,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD," //ADDED FOR LTCORA AND CN)
								   + " checked_flag,checked_by,checked_dt,authorized_flag,authorized_by,authorized_dt,approved_flag,approved_by,approved_dt,truck_id) " + //Hiren_20200311 skip modify,check and approve stages
								   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
								   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
								   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
								   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
								   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
								   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
								   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
								   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
								   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
								   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", sysdate, 'Y' "+
								   ",'"+inv_cur_flag+"'," + ////20141016
								   "'"+mapping_id_LTCORA_CN+"',TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+pay_modee+"' ,"
								   	+ "'"+advadjFlag+"','"+GST_INVOICE_SEQ_NO+"','1',"//ADDED FOR LTCORA AND CN)
								   	+ " null,null,null,null,null,null,'Y','"+user_cd+"',sysdate,'"+truck_cd+"')";  			
	//		 			System.out.println("STEP-8A:INSERT:DLNG_INVOICE_MST: No Adjustment"+queryString1);
			 			
			 			queryString1 = "UPDATE DLNG_INVOICE_MST SET CONTACT_PERSON_CD='"+contact_person+"', "
			 					+ "INVOICE_DT = TO_DATE('"+invoice_date+"','DD/MM/YYYY'), "
			 					+ "DUE_DT = TO_DATE('"+due_dt+"','DD/MM/YYYY'), TOTAL_QTY = '"+nf.format(Double.parseDouble(total_qty))+"', "
			 					+ "SALE_PRICE= '"+nf2.format(Double.parseDouble(sale_price))+"' , "
			 					+ "GROSS_AMT_INR='"+nf.format(total_amt_inr)+"', GROSS_AMT_USD='"+nf.format(total_amt_usd)+"', "
			 					+ "NET_AMT_INR='"+nf.format(total_net_amt_inr)+"', TAX_AMT_INR='"+nf.format(total_tax)+"', "
			 					+ "TAX_STRUCT_CD='"+tax_Structure_Cd+"', TAX_FLAG='"+tax_flag+"', EXCHG_RATE_CD='"+exch_rate_cd+"', "
			 					+ "EXCHG_RATE_DT=TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "
			 					+ "EXCHG_RATE_VALUE='"+nf2.format(Double.parseDouble(exch_rate_val))+"', EXCHG_RATE_TYPE='"+exchg_rate_cal_method+"',"
			 					+ "REMARK_1='"+remark_1+"', REMARK_2='"+remark_2+"', REMARK_3='"+remark_3+"', "
			 					+ "EXCHG_RATE_INDEX='"+exchg_rate_ind+"', OFFSPEC_FLAG='"+offspec_flag+"', "
			 					+ "OFFSPEC_QTY='"+offspec_qty+"', OFFSPEC_RATE='"+offspec_rate+"', EMP_CD='"+user_cd+"', "
			 					+ "ENT_DT=SYSDATE, "
			 					+ "FLAG='Y', INV_CUR_FLAG='"+inv_cur_flag+"' ,"
			 					+ "MAPPING_ID='"+mapping_id_LTCORA_CN+"', "
			 					+ "USER_DEFINED_DAY=TO_DATE('"+particular_date+"','DD/MM/YYYY'), "
			 					+ "PAY_TYPE='"+pay_modee+"', ADV_ADJ_FLG='"+advadjFlag+"', NEW_INV_SEQ_NO='"+GST_INVOICE_SEQ_NO+"', SUPPLIER_CD='1' "
			 					+ "WHERE FINANCIAL_YEAR='"+financial_Year+"' AND CONTRACT_TYPE='"+contract_type+"' AND "
			 					+ "HLPL_INV_SEQ_NO = '"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FLAG!='A'"; 
	//		 			System.out.println("queryString1***"+queryString1);
			 			stmt1.executeUpdate(queryString1);	 			
			 		}	
			 		
			 		String save_chk_mul_adv_inv = request.getParameter("chk_mul_adv_inv")==null?"N":request.getParameter("chk_mul_adv_inv");
			 		String save_adv_inv_value[] = request.getParameterValues("adv_inv");
			 		String save_adv_inv_date[] = request.getParameterValues("adv_inv_date");
			 		
			 		//ADDED FOR INSERTION OF ADVANCE INVOICE NOS.
			 		String query = "DELETE FROM FMS8_ADV_INV_DTL WHERE HLPL_INV_SEQ_NO = '"+hlpl_Inv_Seq_No+"' AND "
		 					+ "FINANCIAL_YEAR = '"+financial_Year+"' AND CONTRACT_TYPE = '"+contract_type+"' ";
		 			stmt.executeUpdate(query);
		 			
		 			if(save_chk_mul_adv_inv.equals("Y")) {
			 			query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
									+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
									+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
									+ "'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),"
									+ "'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
							stmt.executeUpdate(query);
			 			
			 			if(save_adv_inv_value.length > 0) {
			 				for(int i=0;i<save_adv_inv_value.length;i++) {
			 					query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
			 							+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
			 							+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
			 							+ "'"+save_adv_inv_value[i]+"',TO_DATE('"+save_adv_inv_date[i]+"','DD/MM/YYYY')"
			 							+ ",'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
			 					stmt.executeUpdate(query);
			 				}
			 			}
		 			}
		 			/////////////
					inv_financial_year = financial_Year;
					hlpl_invoice_sequence_no = hlpl_Inv_Seq_No+"/"+financial_Year;
					if(!GST_INVOICE_SEQ_NO.equals("")) {
						hlpl_invoice_sequence_no = GST_INVOICE_SEQ_NO;
					}
					} else {
	//				System.out.println("Submission---In Prepare Invoice---");
					original_inv_dt=invoice_date;
					
				if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
				{
					queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
								  "A.customer_cd="+customer_cd+" AND " +
								  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
						 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
						 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
						 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
					
					//System.out.println("STEP-4:FMS7_CUSTOMER_TAX_STRUCT_DTL:  For "+customer_abbr+" = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
						tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
					}
					else
					{
						tax_Structure_Dtl = "";
						tax_Structure_Cd = "0";
					}
				}
				else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
					queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
								  "A.customer_cd="+customer_cd+" AND " +
								  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
						 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
						 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
						 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
					
					//System.out.println("STEP-4A:FMS7_CUSTOMER_TAX_STRUCT_DTL: For "+customer_abbr+" = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
						tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
					}
					else
					{
						tax_Structure_Dtl = "";
						tax_Structure_Cd = "0";
					}
				}
				
				total_amt_inr = request.getParameter("gross_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_inr")));
				total_amt_usd = request.getParameter("gross_amt_usd")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_usd")));
			
				String advance_price_usd="";
				
				NumberFormat nf5=new DecimalFormat("0.00");
				if(adjustadvflag.equalsIgnoreCase("Y"))
		 		{
					String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
					total_amt_inr=request.getParameter("double_final_gross_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("double_final_gross_amt_inr")));
					if(currency.equalsIgnoreCase("0"))
					{
						total_amt_usd=request.getParameter("gross_amt_inr_adjusted")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_amt_inr_adjusted")));
					}
					else {
						total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
						total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
					}
		 		}
				else
				{
					if(TariffFlag.equalsIgnoreCase("Y"))
			 		{
						total_amt_usd = request.getParameter("gross_USD_salespriceINR")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("gross_USD_salespriceINR")));
						total_amt_inr = request.getParameter("grosssalespriceINR")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("grosssalespriceINR")));
			 		}
				}
				
				Vector tax_code = new Vector();
				Vector tax_amount = new Vector();
				String tax_amts[] = request.getParameterValues("tax_amts");
				String tax_codes[] = request.getParameterValues("taxcodes");
				
				for(int i=0;i<tax_amts.length;i++)
				{
					tax_cd = tax_codes[i];
					tax_code.add(tax_cd);
					tax_amt = Double.parseDouble(""+NumberFormat.getInstance().parse(tax_amts[i]));
					tax_amount.add(nf.format(tax_amt));
					//System.out.println("tax amoutnss---"+tax_amt);
					total_tax += tax_amt;
				}
		 		for(int i=0; i<tax_code.size(); i++)
		 		{
		 		queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.firstElement()+"";
		 		
		 			//System.out.println("STEP-6:FMS7_TAX_STRUCTURE_DTL: Tax Name = "+queryString);
		 			rset = stmt.executeQuery(queryString);
		 			if(rset.next())
		 			{
		 				String tax_nm = rset.getString(1).trim()==null?"":rset.getString(1).trim();
		 				
		 				if(tax_nm.contains("VAT"))
		 				{
		 					tax_flag = "V";
		 				}
		 				else if(tax_nm.contains("CST"))
		 				{
		 					tax_flag = "C";
		 				}
		 				else if(tax_nm.contains("ST"))
		 				{
		 					tax_flag = "S";
		 				}
		 				else 
		 				{
		 					tax_flag = "S";
		 				}
		 			}
		 		}
		 		
		 		total_net_amt_inr = request.getParameter("net_amt_inr")==null?0:Double.parseDouble(""+NumberFormat.getInstance().parse(request.getParameter("net_amt_inr")));
		 		
		 		if(adjustadvflag.equalsIgnoreCase("Y") || TariffFlag.equalsIgnoreCase("Y") || DiscountFlag.equalsIgnoreCase("Y"))//NB2010902 Added If clause for advance clause
		 		{	 
					String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
					String advrecevial = request.getParameter("recevial")==null?"":request.getParameter("recevial");
					String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
					String priceINR2_advance_adjust = request.getParameter("priceINR2")==null?"":request.getParameter("priceINR2");
					
					String hiddenadvnclength = request.getParameter("hiddenadvnclength")==null?"":request.getParameter("hiddenadvnclength");
					String adjustsign = request.getParameter("adjustsign")==null?"1":request.getParameter("adjustsign");
	
					String advamount[]=new String[Integer.parseInt(hiddenadvnclength)];
					String advcur[]=new String[Integer.parseInt(hiddenadvnclength)];
					String advadt[]=new String[Integer.parseInt(hiddenadvnclength)];
					String advadjustedamt[]=new String[Integer.parseInt(hiddenadvnclength)];
					String advseq_no[]=new String[Integer.parseInt(hiddenadvnclength)];
					
					for(int i=0;i<Integer.parseInt(hiddenadvnclength);i++)
					{
						advamount[i]= request.getParameter("advamount"+i)==null?"":request.getParameter("advamount"+i);
						advcur[i]= request.getParameter("advcur"+i)==null?"":request.getParameter("advcur"+i);
						advadt[i]= request.getParameter("advadt"+i)==null?"":request.getParameter("advadt"+i);
						advseq_no[i]= request.getParameter("advseq_no"+i)==null?"":request.getParameter("advseq_no"+i);
						advadjustedamt[i]= request.getParameter("advadjustedamt"+i)==null?"":request.getParameter("advadjustedamt"+i);
					}
					String tmpcur="";
					int currency_id=1;
		 			String tmprecv="";
		 			if(currency.equalsIgnoreCase("0"))
		 			{
		 				tmpcur="U";
		 				currency_id=2;
		 			}
		 			else
		 			{
		 				tmpcur="I";
		 				currency_id=1;
		 			}
		 			if(advrecevial.equalsIgnoreCase("0"))
		 			{
		 				tmprecv="Y";
		 			}
		 			else
		 			{
		 				tmprecv="N";
		 			}		
		 			String temp_inv_no = hlpl_Inv_Seq_No;
		 			boolean flag = fetchMaxInvoiceSeqNo(contract_type,hlpl_Inv_Seq_No,financial_Year,customer_Inv_Seq_No,GST_INVOICE_SEQ_NO);
		 			if(flag) {
		 				msg1 = "(During Preparation : Invoice Seq No - "+GST_INVOICE_SEQ_NO+" ,During Submission Invoice Seq No - "+gst_inv_seq_no_new+")";
		 				hlpl_Inv_Seq_No = hlpl_inv_seq_no_new;
		 				customer_Inv_Seq_No = customer_inv_seq_no_new;
		 				GST_INVOICE_SEQ_NO = gst_inv_seq_no_new;
		 			}
		 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
					   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
					   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
					   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
					   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
					   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag, " +
					   "INV_AMT_USD, INV_AMT_INR, INV_CUR_FLAG," +
					   "MAPPING_ID" +//ADDED FOR LTCORA AND CN
					   ",ADV_INV_NO,ADV_INV_DT,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD,REMARK_SPECIFICATION," //ADDED FOR ADVANCE INVOICE NO
					   + " checked_flag,checked_by,checked_dt,authorized_flag,authorized_by,authorized_dt,approved_flag,approved_by,approved_dt,truck_id) " + //Hiren_20200311 skip modify,check and approve stages
					   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
					   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
					   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
					   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
					   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
					   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
					   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
					   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
					   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
					   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), 'Y', " +
					   "'"+raw_amt_usd+"','"+raw_amt_inr+"','"+inv_cur_flag+"'," +
					   "'"+mapping_id_LTCORA_CN+"'" +//ADDED FOR LTCORA AND CN
					   ",'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),TO_DATE('"+particular_date+"','DD/MM/YYYY'),"
					   	+ "'"+pay_modee+"' ,'"+advadjFlag+"','"+GST_INVOICE_SEQ_NO+"','1','"+rec_remark+"', "//ADDED FOR ADVANCE INVOICE NO
					   	+ " null,null,null,null,null,null,'Y','"+user_cd+"',sysdate,'"+truck_cd+"')";  
		 			
	//	 			System.out.println("STEP-8:INSERT:DLNG_INVOICE_MST:Adv Adjust= "+queryString1);
					
		 			stmt1.executeUpdate(queryString1);
		 			
		 			if(flag) {
		 				String mapping_id1=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
			 			String inv_seq_no1=contract_type+":"+financial_Year+":"+Integer.parseInt(temp_inv_no)+":"+invoice_date;
			 			String del_inv_seq_no1=contract_type+":"+financial_Year+":"+Integer.parseInt(temp_inv_no)+":"+original_inv_dt;	
			 			String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id1+"' "
			 					+ "AND INV_SEQ_NO='"+del_inv_seq_no1+"' ";
			 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
						{
			 				String mapid[]=mapping_id_LTCORA_CN.split("-");
			 				query_del+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
						}
			 			stmt.executeUpdate(query_del);
			 			
			 			String q_del = "DELETE FROM FMS8_ADV_INV_DTL WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(temp_inv_no)+"' AND "
	 		 					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' "
	 		 					+ "AND MAPPING_ID='"+mapping_id_LTCORA_CN+"' ";
	 		 			stmt.executeUpdate(q_del);
		 			}
		 			
		 			String mapping_id=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
		 			String inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+invoice_date;
		 			String del_inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+original_inv_dt;	
		 			String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id+"' AND INV_SEQ_NO='"+del_inv_seq_no+"' ";
		 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
					{
		 				String mapid[]=mapping_id_LTCORA_CN.split("-");
		 				query_del+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
					}
		 			////System.out.println(mapping_id+" :STEP-7:DELETE:FMS7_INV_COMPO_DTL: "+query_del);
		 			stmt.executeUpdate(query_del);	
		 			
		 			String tempseqno=fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no;
					
		 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
					{
		 				String mapid[]=mapping_id_LTCORA_CN.split("-");
		 				if(adjustadvflag.equalsIgnoreCase("Y"))
			 			{
			 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust)/Double.parseDouble(exch_rate_val);
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
			 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				////System.out.println(".............................................."+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			if(TariffFlag.equalsIgnoreCase("Y"))
			 			{
			 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
								" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				////System.out.println(".............................................."+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			if(DiscountFlag.equalsIgnoreCase("Y"))
			 			{
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
								" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
			 				////System.out.println(".............................................."+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
		 				
					}
		 			else
		 			{
		 				if(adjustadvflag.equalsIgnoreCase("Y"))
			 			{
			 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust)/Double.parseDouble(exch_rate_val);
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
			 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
			 				////System.out.println(".............................................."+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			if(TariffFlag.equalsIgnoreCase("Y"))
			 			{
			 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
								" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
			 				////System.out.println(".............................................."+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			if(DiscountFlag.equalsIgnoreCase("Y"))
			 			{
			 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
								" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
			 				////System.out.println(".............................................."+queryString1);
			 				stmt.executeUpdate(queryString1);
			 			}
			 			
		 			}
					msg="Data Submitted Successfully..";
		 		}
		 		else
		 		{	
		 			String temp_inv_no = hlpl_Inv_Seq_No;
		 			boolean flag = fetchMaxInvoiceSeqNo(contract_type,hlpl_Inv_Seq_No,financial_Year,customer_Inv_Seq_No,GST_INVOICE_SEQ_NO);
		 			if(flag) {
		 				msg1 = "(During Preparation : Invoice Seq No - "+GST_INVOICE_SEQ_NO+" ,During Submission Invoice Seq No - "+gst_inv_seq_no_new+")";
		 				hlpl_Inv_Seq_No = hlpl_inv_seq_no_new;
		 				customer_Inv_Seq_No = customer_inv_seq_no_new;
		 				GST_INVOICE_SEQ_NO = gst_inv_seq_no_new;
		 				
		 		 			String q_del = "DELETE FROM FMS8_ADV_INV_DTL WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(temp_inv_no)+"' AND "
		 		 					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' "
		 		 					+ "AND MAPPING_ID='"+mapping_id_LTCORA_CN+"' ";
		 		 			stmt.executeUpdate(q_del);
		 			}
	//	 			System.out.println("customer_Inv_Seq_No***"+customer_Inv_Seq_No+"**hlpl_Inv_Seq_No**"+hlpl_Inv_Seq_No);
		 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
							   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
							   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
							   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
							   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
							   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag " +
							   ", INV_CUR_FLAG" +
							   ", MAPPING_ID,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG,NEW_INV_SEQ_NO,SUPPLIER_CD,"//ADDED FOR LTCORA AND CN)
							   + " checked_flag,checked_by,checked_dt,authorized_flag,authorized_by,authorized_dt,approved_flag,approved_by,approved_dt,truck_id) " + //Hiren_20200311 skip modify,check and approve stages
							   
							   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
							   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
							   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
							   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
							   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
							   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
							   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
							   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
							   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
							   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", sysdate, 'Y' "+
							   ",'"+inv_cur_flag+"'," + ////20141016
							   "'"+mapping_id_LTCORA_CN+"',TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+pay_modee+"' ,"
							   	+ "'"+advadjFlag+"','"+GST_INVOICE_SEQ_NO+"','1',"//ADDED FOR LTCORA AND CN)
							   	+ " null,null,null,null,null,null,'Y','"+user_cd+"',sysdate,'"+truck_cd+"')";  
	//	 			System.out.println("STEP-8A:INSERT:DLNG_INVOICE_MST: No Adjustment"+queryString1);				
		 			stmt1.executeUpdate(queryString1);	 			
		 		}
		 		
		 		String q2="SELECT COUNT(*) FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+sn_no+"' AND " +
		 				"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
		 				"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
		 				"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
		 				"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
		 				rset3=stmt3.executeQuery(q2);
		 				int count=0;
		 				if(rset3.next())
		 				{
		 					count=rset3.getInt(1);
		 				}
		 				if(count>0)
		 				{
		 					String q3="UPDATE DLNG_INVOICE_DEL_LOG SET RESTORE_FLAG='Y' WHERE SN_NO='"+sn_no+"' AND " +
		 					"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
		 					"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
		 					"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
		 					"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
		 						//System.out.println("STEP-2:DLNG_INVOICE_DEL_LOG: "+q3);
		 						stmt3.executeUpdate(q3);
		 				}
		 				
		 				for(int i=0,j=Integer.parseInt(start_index); i<daily_inv_dt.length; i++,j=j+4)
		 				{
		 					if(exchg_rate_cal_method.equalsIgnoreCase("D"))
		 					{
		 						queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
		 									   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
		 									   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
		 									   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
		 									   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
		 									   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
		 					}
		 					else
		 					{
		 						if(exch_rate_value[j]==null || exch_rate_value[j].equals("") || exch_rate_value[j].equals(" ") || exch_rate_value[j].equalsIgnoreCase("N/A"))
		 						{
		 							exch_rate_value[j] = "0";
		 						}
		 						
		 						queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
		 									   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
		 									   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
		 									   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
		 									   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_value[j]))+", " +
		 									   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
		 					}
		 					stmt2.executeUpdate(queryString2);				
		 				}
		 		
		 		String save_chk_mul_adv_inv = request.getParameter("chk_mul_adv_inv")==null?"N":request.getParameter("chk_mul_adv_inv");
		 		String save_adv_inv_value[] = request.getParameterValues("adv_inv");
		 		String save_adv_inv_date[] = request.getParameterValues("adv_inv_date");
		 		
		 		//ADDED FOR INSERTION OF ADVANCE INVOICE NOS.
		 		
	 			if(save_chk_mul_adv_inv.equals("Y")) {
		 			String query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
								+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
								+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
								+ "'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),"
								+ "'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
						stmt.executeUpdate(query);
		 			
		 			if(save_adv_inv_value.length > 0) {
		 				for(int i=0;i<save_adv_inv_value.length;i++) {
		 					query = "INSERT INTO FMS8_ADV_INV_DTL(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,"
		 							+ "ADV_INV_NO,ADV_INV_DT,MAPPING_ID,ENTER_BY,ENTER_DT,FLAG) "
		 							+ "VALUES('"+hlpl_Inv_Seq_No+"','"+financial_Year+"','"+contract_type+"',"
		 							+ "'"+save_adv_inv_value[i]+"',TO_DATE('"+save_adv_inv_date[i]+"','DD/MM/YYYY')"
		 							+ ",'"+mapping_id_LTCORA_CN+"','',SYSDATE,'Y')";
		 					stmt.executeUpdate(query);
		 				}
		 			}
	 			}
	 			/////////////
				inv_financial_year = financial_Year;
				hlpl_invoice_sequence_no = hlpl_Inv_Seq_No+"/"+financial_Year;
				if(!GST_INVOICE_SEQ_NO.equals("")) {
					hlpl_invoice_sequence_no = GST_INVOICE_SEQ_NO;
				}
				
					/*Hiren_20210707 for Auto paid*/
				System.out.println("total_net_amt_inr******"+total_net_amt_inr+"-----advAmt*******"+advAmt);
	//			if(c_form_flg.equalsIgnoreCase("Y")) {
					
						if(advAmt.contains(",")) {
							advAmt = advAmt.replace(",", "");
						}
						if(!advAmt.equals("") &&  Double.parseDouble(advAmt+"") >= total_net_amt_inr ) {
							double tds_calc = 0;
							if(tds_app_flag.equalsIgnoreCase("Y") && !tds_amt.equalsIgnoreCase("")) {
								tds_percent = "0.1";
								tds_calc = (Double.parseDouble(tds_amt+"") * Double.parseDouble(tds_percent+""))/100;
								System.out.println("tds_calc----"+tds_calc);
							}
							
							double tax_perc = 0; 
							
							/*for HOLD amount calculation*/
							queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_Structure_Cd+" AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_Structure_Cd+" AND " +
									  "B.app_date<=TO_DATE('"+invoice_date+"','DD/MM/YYYY')) ORDER BY A.tax_code";
		//					System.out.println("tax factor-----"+queryString);
							rset = stmt1.executeQuery(queryString);
							while(rset.next()) {
								tax_perc+= rset.getDouble(2); 
							}
							
							double hold_amt = 0;
							double maxPerc = 15;
							
							double diffTax = maxPerc - tax_perc;
							
							if(diffTax > 0 && c_form_flg.equalsIgnoreCase("Y"))  { //calculate HOLD amount from Gross amount for this condition
								
								hold_amt = (total_amt_inr*diffTax) / 100;
		//						System.out.println("hold_amt--------"+hold_amt);
							}
							String mapping_id = customer_cd+"-"+fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no+"-"+contract_type;
							double recv_amt = total_net_amt_inr - tds_calc;
							
							queryString2="update DLNG_INVOICE_MST set "
									+ " pay_recv_amt='"+recv_amt+"',"
									+ " pay_recv_dt=sysdate,"
									+ " pay_remark='Auto Paid',"
									+ " pay_insert_by='"+user_cd+"',"
									+ " pay_insert_dt=to_date(sysdate,'dd/mm/yy HH24:MI'),"
									+ " tds_percent = '"+tds_percent+"'"
									+ " where hlpl_inv_seq_no='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
									+ " and financial_year='"+inv_financial_year+"'"
									+ " and contract_type='"+contract_type+"' ";
							System.out.println("Auto Paid--------"+queryString2);
							stmt1.executeUpdate(queryString2);
							
							queryString="INSERT INTO FMS8_PAY_RECV_DTL (NEW_INV_SEQ_NO,COMMODITY_TYPE,CONTRACT_TYPE,PAY_RECV_AMT,"
									+ "PAY_RECV_DT,PAY_REMARK,PAY_INSERT_BY,REV_NO,MAPPING_ID,SHORT_RECV_AMT,ADJUSTED_AMT,ADJUST_BALANCE_AMT,ADJUST_FLAG,HOLD_AMOUNT) VALUES "
									+ "('"+GST_INVOICE_SEQ_NO+"','DLNG','"+contract_type+"','"+nf.format(recv_amt)+"',"
									+ "to_date(sysdate,'dd/mm/yy HH24:MI'),'Auto Paid','"+user_cd+"','1','"+mapping_id+"',"
									+ " '0','"+nf.format(recv_amt)+"','0','Y','"+hold_amt+"')";
							System.out.println("FMS8_PAY_RECV_DTL ----"+queryString);
							stmt.executeUpdate(queryString);
							//
						}
	//				}
				}
				/* ****************** Hiren_20201109 for TCS calc*********************** */
				if(tcs_app_flag.equals("Y")) {
					String inv_typ="SALES";
					queryString1 = "SELECT COUNT(*) FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
							+ " AND FINANCIAL_YEAR='"+financial_Year+"' AND "
		                    + " HLPL_INV_SEQ_NO ='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' "
		                    + " AND CONTRACT_TYPE='"+contract_type+"' AND INVOICE_TYPE='"+inv_typ+"' ";                 
	//	        System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
		        rset1=stmt1.executeQuery(queryString1);
		        if(rset1.next()){
		              int count=rset1.getInt(1);
		              if(count==0){
		                    queryString1 = "INSERT INTO FMS7_INVOICE_TCS_DTL (CUSTOMER_CD,FINANCIAL_YEAR,HLPL_INV_SEQ_NO,CONTRACT_TYPE,INVOICE_TYPE,"
		                                + "INVOICE_NET_AMT,TCS_AMT,FLAG,EFF_DT,COMMODITY_TYPE) VALUES ('"+customer_cd+"','"+financial_Year+"','"+Integer.parseInt(hlpl_Inv_Seq_No)+"','"+contract_type+"','"+inv_typ+"',"
		                                + "'"+total_net_amt_inr+"','"+tax_tcs_amts+"','Y',TO_DATE(SYSDATE,'DD/MM/YYYY'),'DLNG')";             
		//                    System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
		                    stmt1.executeUpdate(queryString1);
		              }else{
		                    queryString1 = "update FMS7_INVOICE_TCS_DTL set INVOICE_NET_AMT='"+total_net_amt_inr+"',tcs_amt='"+tax_tcs_amts+"',flag='Y' WHERE CUSTOMER_CD='"+customer_cd+"' AND FINANCIAL_YEAR='"+financial_Year+"' AND "
		                                + "HLPL_INV_SEQ_NO ='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND CONTRACT_TYPE='"+contract_type+"' AND INVOICE_TYPE='"+inv_typ+"'";                 
		//                    System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
		                    rset1=stmt1.executeQuery(queryString1);
		              }
		        	}
				}else{
	 				String query="SELECT COUNT(*) FROM FMS7_INVOICE_TCS_DTL WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND "
		 					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' AND CUSTOMER_CD='"+customer_cd+"' AND INVOICE_TYPE='SALES' and commodity_type='DLNG'";
		 			rset=stmt.executeQuery(query);
		 			if(rset.next()){
		 				int cnt=rset.getInt(1);
		 				if(cnt>0){
		 					/*String query1="delete FROM FMS7_INVOICE_TCS_DTL WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND "
		 		 					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' AND CUSTOMER_CD='"+customer_cd+"' AND INVOICE_TYPE='SALES'";
		 					stmt1.executeUpdate(query1);*/
		 					queryString1="UPDATE FMS7_INVOICE_TCS_DTL SET FLAG='N' "
		 							+ " WHERE  HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND "
		 							+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' AND CUSTOMER_CD='"+customer_cd+"' AND INVOICE_TYPE='SALES' and commodity_type='DLNG'";
		 					stmt1.executeUpdate(queryString1);
		 				}
		 			}
	 			}
				//
				/*Hiren_20210624 for TDS Entry*/
	 			if(tds_app_flag.equalsIgnoreCase("Y") && !tds_amt.equalsIgnoreCase("")) {
	 				
	 				String query="SELECT COUNT(*) FROM FMS7_INVOICE_TDS_DTL WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND "
		 					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' AND CUSTOMER_CD='"+customer_cd+"' "
		 							+ " AND INVOICE_TYPE='SALES' and commodity_type='DLNG'";
//		 			System.out.println("TDS Count---"+query);
		 			rset=stmt.executeQuery(query);
		 			if(rset.next()){
		 				int cnt=rset.getInt(1);
		 				if(cnt==0){
		 					queryString1="INSERT INTO FMS7_INVOICE_TDS_DTL (HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,CUSTOMER_CD,TDS_AMT,"
		 							+ "INVOICE_GROSS_AMT,FLAG,EFF_DT,INVOICE_TYPE,COMMODITY_TYPE) VALUES ('"+Integer.parseInt(hlpl_Inv_Seq_No)+"','"+financial_Year+"',"
									+ "'"+contract_type+"','"+customer_cd+"','0','"+tds_amt+"','Y',TO_DATE(SYSDATE,'DD/MM/YYYY'),'SALES','DLNG') ";
		 					stmt1.executeUpdate(queryString1);
		 				}else{
		 					queryString1="UPDATE FMS7_INVOICE_TDS_DTL SET INVOICE_GROSS_AMT='"+tds_amt+"',"
		 								+ " EFF_DT=TO_DATE(SYSDATE,'DD/MM/YYYY'),flag='Y' "
			 							+ " WHERE  HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND "
			 							+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' AND CUSTOMER_CD='"+customer_cd+"' AND INVOICE_TYPE='SALES' and commodity_type='DLNG'";
		 					stmt1.executeUpdate(queryString1);
		 					//System.out.println("STEP-8:FMS7_INVOICE_TCS_DTL Adjust= "+queryString1);
		 				}
		 			}
	 			}else{
	 				String query="SELECT COUNT(*) FROM FMS7_INVOICE_TDS_DTL WHERE HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND "
		 					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' AND CUSTOMER_CD='"+customer_cd+"' AND INVOICE_TYPE='SALES' and commodity_type='DLNG'";
		 			rset=stmt.executeQuery(query);
		 			if(rset.next()){
		 				int cnt=rset.getInt(1);
		 				if(cnt>0){
		 					queryString1="UPDATE FMS7_INVOICE_TDS_DTL SET FLAG='N' "
		 							+ " WHERE  HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND "
		 							+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_Year+"' AND CUSTOMER_CD='"+customer_cd+"' AND INVOICE_TYPE='SALES' and commodity_type='DLNG'";
		 					stmt1.executeUpdate(queryString1);
		 				}
		 			}
	 			}	
	 			///////////
				
				String contact_Suppl_CST_NO =  request.getParameter("contact_Suppl_CST_NO")==null?"":request.getParameter("contact_Suppl_CST_NO");
				String contact_Customer_Name =  request.getParameter("contact_Customer_Name")==null?"":request.getParameter("contact_Customer_Name");
				String taxRate =  request.getParameter("taxRate")==null?"":request.getParameter("taxRate");
				String truck_trans_nm =  request.getParameter("truck_trans_nm")==null?"":request.getParameter("truck_trans_nm");
				String truck_driver =  request.getParameter("truck_driver")==null?"":request.getParameter("truck_driver");
				String truck_driver_addrs =  request.getParameter("truck_driver_addrs")==null?"":request.getParameter("truck_driver_addrs");
				String truck_nm =  request.getParameter("truck_nm")==null?"":request.getParameter("truck_nm");
				String truck_trans_addrs =  request.getParameter("truck_trans_addrs")==null?"":request.getParameter("truck_trans_addrs");
				String truckLinkedFlg =  request.getParameter("truckLinkedFlg")==null?"":request.getParameter("truckLinkedFlg");
				String truck_driver_lic_no =  request.getParameter("truck_driver_lic_no")==null?"":request.getParameter("truck_driver_lic_no");
				String truck_lic_state =  request.getParameter("truck_lic_state")==null?"":request.getParameter("truck_lic_state");
				
				
				
				DataBean_Sales_InvoiceV2 salesInv= new DataBean_Sales_InvoiceV2();
				
				salesInv.setCallFlag("SALES_INVOICE_REPORT_PREVIEW");
				salesInv.setCustomerCd(customer_cd);
				salesInv.setFgsaNo(fgsa_no);
				salesInv.setFgsaRevNo(fgsa_rev_no);
				salesInv.setSnNo(sn_no);
				salesInv.setSnRevNo(sn_rev_no);
				salesInv.setContractType(contract_type);
				salesInv.setCustomerPlantSeqNo(customer_plant_seq_no);
				salesInv.setBillPeriodStartDt(bill_period_start_dt);
				salesInv.setBillPeriodEndDt(bill_period_end_dt);
				salesInv.setContactPersonCd(contact_person);
				int temp_inv_no=Integer.parseInt(hlpl_Inv_Seq_No);
				salesInv.setHlplInvoiceNo(temp_inv_no+"");
				salesInv.setInvFinancialYear(inv_financial_year);
				salesInv.setBillCycle(bill_cycle);
				salesInv.setCustomer_Invoice_Gross_Amt_INR(gross_amt_inr);
				
				salesInv.init();
				
				String contact_Customer_Person_Address = salesInv.getContact_Customer_Person_Address();
				String contact_Customer_Person_City = salesInv.getContact_Customer_Person_City();
				String contact_Customer_Person_Pin = salesInv.getContact_Customer_Person_Pin();
				String contact_Customer_Person_State = salesInv.getContact_Customer_Person_State();
				
				
				Excel_Export_Form_402 excel = new Excel_Export_Form_402();
				
				excel.setTotal_net_amt_inr(total_net_amt_inr);
				excel.setContact_Suppl_CST_NO(contact_Suppl_CST_NO);
				excel.setContact_Customer_Name(contact_Customer_Name);
				excel.setContact_Customer_Person_City(contact_Customer_Person_City);
				excel.setContact_Customer_Person_Address(contact_Customer_Person_Address);
				excel.setContact_Customer_Person_Pin(contact_Customer_Person_Pin);
				excel.setContact_Customer_Person_State(contact_Customer_Person_State);
				excel.setNew_inv_seq_no(GST_INVOICE_SEQ_NO);
				excel.setInvoice_date(invoice_date);
				excel.setTotal_qty(total_qty);
				excel.setTaxRate(taxRate);
				excel.setTruck_trans_nm(truck_trans_nm);
				excel.setTruck_driver(truck_driver);
				excel.setTruck_driver_addrs(truck_driver_addrs);
				excel.setTruck_nm(truck_nm);
				excel.setTruck_trans_addrs(truck_trans_addrs);
				excel.setTruckLinkedFlg(truckLinkedFlg);
				excel.setTruck_driver_lic_no(truck_driver_lic_no);
				excel.setTruck_lic_state(truck_lic_state);
				excel.setCheck_post(checkPost);
				
				excel.setvSTAT_CD(salesInv.getVSTAT_CD());
				excel.setvSTAT_NM(salesInv.getVSTAT_NM());
				excel.setvSTAT_NO(salesInv.getVSTAT_NO());
				excel.setvSTAT_EFF_DT(salesInv.getVSTAT_EFF_DT());
				
				excel.setContact_Customer_CST_NO(salesInv.getContact_Customer_CST_NO());
				excel.setContact_Customer_CST_DT(salesInv.getContact_Customer_CST_DT());
				
				excel.generateExcelForForm402(request);
				
				msg = "Billing Details Of "+hlpl_invoice_sequence_no+" For "+customer_abbr+" Has Been Submitted Successfully !!!"+msg1;
				dbcon.commit();
			}else {
				dbcon.rollback();
				msg = "Invoice No. "+GST_INVOICE_SEQ_NO+" Already Generated";
			}
			response.sendRedirect("../sales_invoice/frm_invoice_dtl_gst.jsp?msg="+msg+"&customer_abbr="+customer_abbr+
					"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+
					"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+
					"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+
					"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+
					"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+
					"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+
					"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+
					"&year="+year+"&month="+month+"&write_permission="+write_permission+"&delete_permission="+delete_permission+
					"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+
					"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+
					"&mapping_id="+mapping_id_LTCORA_CN+"&sn_start_dt="+sn_start_dt+"&sn_end_dt="+sn_end_dt);
			
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				////System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Billing Details Submission Failed !!!";
			e.printStackTrace();
			////System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_invoice_dtl_gst.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&sn_start_dt="+sn_start_dt+"&sn_end_dt="+sn_end_dt);	
		}		
	}


	public void submitBillingDetails(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "submitBillingDetails()";
		form_name = "frm_invoice_dtl.jsp";
		
		String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");
		String customer_plant_seq_no = request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
		String bill_period_start_dt = request.getParameter("bill_period_start_date")==null?"":request.getParameter("bill_period_start_date");
		String bill_period_end_dt = request.getParameter("bill_period_end_date")==null?"":request.getParameter("bill_period_end_date");
		String due_dt = request.getParameter("due_date")==null?"":request.getParameter("due_date");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
		String customer_inv_seq_no = request.getParameter("customer_inv_seq_no")==null?"":request.getParameter("customer_inv_seq_no");
		String hlpl_Inv_Seq_No = request.getParameter("hlpl_Inv_Seq_No")==null?"0":request.getParameter("hlpl_Inv_Seq_No");
		String customer_Inv_Seq_No = request.getParameter("customer_Inv_Seq_No")==null?"":request.getParameter("customer_Inv_Seq_No");
		String financial_Year = request.getParameter("financial_Year")==null?"":request.getParameter("financial_Year");
		String contact_person_nm = request.getParameter("contact_person_nm")==null?"":request.getParameter("contact_person_nm");
		String contact_person = request.getParameter("contact_person")==null?"0":request.getParameter("contact_person");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String exch_rate_cd = request.getParameter("exch_rate_cd")==null?"0":request.getParameter("exch_rate_cd"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exch_rate_val = request.getParameter("exch_rate_val")==null?"0":request.getParameter("exch_rate_val"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exch_rate_dt = request.getParameter("exch_rate_dt")==null?"":request.getParameter("exch_rate_dt"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
		String exchg_rate_cal_method = request.getParameter("exchg_rate_cal_method")==null?"0":request.getParameter("exchg_rate_cal_method");
		String invoice_date = request.getParameter("invoice_date")==null?bill_period_end_dt:request.getParameter("invoice_date");
		String particular_date = request.getParameter("particular_date")==null?bill_period_end_dt:request.getParameter("particular_date");
		String start_index = request.getParameter("start_index")==null?"0":request.getParameter("start_index"); //Only For Daily Basis Exchange Rate Based Customers ...
		String end_index = request.getParameter("end_index")==null?"0":request.getParameter("end_index"); //Only For Daily Basis Exchange Rate Based Customers ...
		String total_qty = request.getParameter("total_qty")==null?"0":request.getParameter("total_qty");
		String sale_price = request.getParameter("sale_price")==null?"0.0000":request.getParameter("sale_price");
		String gross_amt_usd = request.getParameter("gross_amt_usd")==null?"0":request.getParameter("gross_amt_usd");
		String gross_amt_inr = request.getParameter("gross_amt_inr")==null?"0":request.getParameter("gross_amt_inr");
		String remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
		String remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
		String remark_3 = request.getParameter("remark_3")==null?"":request.getParameter("remark_3");
		String offspec_flag = request.getParameter("offspec_flag")==null?"N":request.getParameter("offspec_flag");
		String offspec_qty = request.getParameter("offspec_qty")==null?"":request.getParameter("offspec_qty");
		String offspec_rate = request.getParameter("offspec_rate")==null?"":request.getParameter("offspec_rate");
		String mapping_id_LTCORA_CN = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id"); //ADDED FOR LTCORA AND CN
		String adv_inv_no = request.getParameter("adv_inv_no")==null?"":request.getParameter("adv_inv_no"); //ADDED FOR LTCORA AND CN
		String adv_inv_dt = request.getParameter("adv_inv_dt")==null?"":request.getParameter("adv_inv_dt"); //ADDED FOR LTCORA AND CN
		
//		RG20161217		Following fields added for pay_type and adjustment flag.......
		String pay_modee=request.getParameter("pay_type1")==null?"AP":request.getParameter("pay_type1"); //RG20161217
		String advadjFlag=request.getParameter("advflg")==null?"NA":request.getParameter("advflg"); //RG20161217
		
		remark_1 = obj.replaceSingleQuotes(remark_1);
		remark_2 = obj.replaceSingleQuotes(remark_2);
		remark_3 = obj.replaceSingleQuotes(remark_3);
		String remark_adj = request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
		remark_adj=obj.replaceSingleQuotes(remark_adj); 
		
		String inv_financial_year = "";
		String hlpl_invoice_sequence_no = "";
		String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
		String exchg_rate_ind = request.getParameter("exchg_rate_ind")==null?"0":request.getParameter("exchg_rate_ind");
		
		// SALES PRICE INR
		String salespriceINR = request.getParameter("salespriceINR")==null?"":request.getParameter("salespriceINR");
		String grosssalespriceINR = request.getParameter("grosssalespriceINR")==null?"":request.getParameter("grosssalespriceINR");
		
	//	////System.out.println("DISCOUNT----"+request.getParameter("Discount_flag_Setter"));
		String DiscountFlag = request.getParameter("Discount_flag_Setter")==null?"":request.getParameter("Discount_flag_Setter");
		String discount_value="";
		
		
		// Multiple currency
		String inv_cur_flag = request.getParameter("inv_cur_flag")==null?"":request.getParameter("inv_cur_flag");
		////System.out.println("INV_CUR_FLAG-->"+inv_cur_flag);
		
		
		if(DiscountFlag.equalsIgnoreCase("Y"))
		{
			discount_value=request.getParameter("discount_value")==null?"0":request.getParameter("discount_value");
		}
	//	//System.out.println("11"+discount_value);
		String TariffFlag = request.getParameter("Tariff_flag_Setter")==null?"":request.getParameter("Tariff_flag_Setter");
		String tariff="";
		String total_tariff="";
		String inv_inr_amt_tariff="";
	//	//System.out.println("TARIFF----"+TariffFlag);
		if(TariffFlag.equalsIgnoreCase("Y"))
		{
			tariff=request.getParameter("salespriceINR")==null?"0":request.getParameter("salespriceINR");
			total_tariff=request.getParameter("total_tariff")==null?"0":request.getParameter("total_tariff");
			inv_inr_amt_tariff=request.getParameter("grosssalespriceINR")==null?"0":request.getParameter("grosssalespriceINR");
		}
		
		String raw_amt_usd=request.getParameter("raw_amt_usd")==null?"0":request.getParameter("raw_amt_usd");
		String raw_amt_inr=request.getParameter("raw_amt_inr")==null?"0":request.getParameter("raw_amt_inr");
		
		
	//	//System.out.println("11--"+tariff+"22--"+total_tariff+"33--"+inv_inr_amt_tariff);
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			String [] exch_rate_value = request.getParameterValues("exch_rate_value"); //Only For Daily Basis Exchange Rate Based Customers ...
			String [] daily_inv_dt = request.getParameterValues("daily_inv_dt"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_qty = request.getParameterValues("daily_inv_qty"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_amt_usd = request.getParameterValues("daily_inv_amt_usd"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			String [] daily_inv_amt_inr = request.getParameterValues("daily_inv_amt_inr"); //Both For Daily Basis & Particular Day Based Exchange Rate Based Customers ...
			
			
			String adjustadvflag = request.getParameter("adjust")==null?"":request.getParameter("adjust");
			////System.out.println("START OF MODIFY: HLPL_SEQ_NO: "+hlpl_Inv_Seq_No);
			
			String tax_Structure_Dtl = "";
			String tax_Structure_Cd = "0";
			String tax_cd = "";
			double tax_amt = 0;
			double total_amt_inr = 0;
			double total_amt_usd = 0;
			double total_tax = 0;
			double total_net_amt_inr = 0;
			String tax_flag = ""; //'V' For VAT & 'C' For CST & 'S' For Service Tax Applicable For The Invoice ...
			
			String original_inv_dt="";
			//String original_inv_no="";
			/*queryString = "SELECT hlpl_inv_seq_no FROM DLNG_INVOICE_MST WHERE customer_cd="+customer_cd+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
						  "fgsa_no="+fgsa_no+" AND sn_no="+sn_no+" AND " +
						  "contract_type='"+contract_type+"' AND financial_year='"+financial_Year+"' AND " +
						  "period_end_dt=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') AND " +
						  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+"";*/
			
			queryString = "SELECT hlpl_inv_seq_no,to_char(invoice_dt,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE " +
						  "contract_type='"+contract_type+"' AND " +
						  "financial_year='"+financial_Year+"' AND " +
						  "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+"";
			
			if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
					queryString+=" AND MAPPING_ID='"+mapping_id_LTCORA_CN+"'";
			}
			
			////System.out.println("STEP-1:DLNG_INVOICE_MST: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				original_inv_dt=rset.getString(2);
				//original_inv_no=rset.getString(3);
				queryString2 = "DELETE FROM DLNG_INVOICE_DTL WHERE " +
							   "financial_year='"+financial_Year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+" AND " +
				  			   "contract_type='"+contract_type+"'";
				
				/*queryString1 = "DELETE FROM DLNG_INVOICE_MST WHERE customer_cd="+customer_cd+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
				  			   "fgsa_no="+fgsa_no+" AND sn_no="+sn_no+" AND " +
				  			   "contract_type='"+contract_type+"' AND financial_year='"+financial_Year+"' AND " +
				  			   "period_end_dt=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+"";*/
				
				queryString1 = "DELETE FROM DLNG_INVOICE_MST WHERE " +
				  			   "contract_type='"+contract_type+"' AND " +
				  			   "financial_year='"+financial_Year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_Inv_Seq_No)+"";
				if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
						queryString1+=" AND MAPPING_ID='"+mapping_id_LTCORA_CN+"'";
				}
				
				////System.out.println("STEP-1A:DLNG_INVOICE_DTL: "+queryString2);
				////System.out.println("STEP-1B:DLNG_INVOICE_MST: "+queryString1);
				
				
				stmt2.executeUpdate(queryString2);
				stmt1.executeUpdate(queryString1);			
				
				
			}
			else
			{
				original_inv_dt=invoice_date;
			}
			
			
			/////////////////////////////////////////////////////////////////////////////////////////
			
				
			String q2="SELECT COUNT(*) FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+sn_no+"' AND " +
			"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
			"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
			"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
			"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
			rset3=stmt3.executeQuery(q2);
			int count=0;
			if(rset3.next())
			{
				count=rset3.getInt(1);
			}
			if(count>0)
			{
				String q3="UPDATE DLNG_INVOICE_DEL_LOG SET RESTORE_FLAG='Y' WHERE SN_NO='"+sn_no+"' AND " +
			"SN_REV_NO='"+sn_rev_no+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' " +
			"AND CUSTOMER_CD='"+customer_cd+"' AND "+//PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
			"CONTRACT_TYPE='"+contract_type+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','DD/MM/YYYY') " +
			"AND HLPL_INV_SEQ_NO='"+Integer.parseInt(hlpl_Inv_Seq_No)+"' AND FINANCIAL_YEAR='"+financial_Year+"' ";
				////System.out.println("STEP-2:DLNG_INVOICE_DEL_LOG: "+q3);
				stmt3.executeUpdate(q3);
			}
			
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
			
			
			
			for(int i=0,j=Integer.parseInt(start_index); i<daily_inv_dt.length; i++,j=j+4)
			{
				if(exchg_rate_cal_method.equalsIgnoreCase("D"))
				{
					queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
								   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
								   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
								   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
								   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
								   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
				}
				else
				{
					if(exch_rate_value[j]==null || exch_rate_value[j].equals("") || exch_rate_value[j].equals(" ") || exch_rate_value[j].equalsIgnoreCase("N/A"))
					{
						exch_rate_value[j] = "0";
					}
					
					queryString2 = "INSERT INTO DLNG_INVOICE_DTL(allocation_dt, hlpl_inv_seq_no, financial_year, " +
								   "daily_qty, amt_usd, amt_inr, exchg_rate_cd, exchg_rate_value, emp_cd, ent_dt, flag, contract_type) " +
								   "VALUES(TO_DATE('"+daily_inv_dt[i]+"','DD/MM/YYYY'), "+Integer.parseInt(hlpl_Inv_Seq_No)+", " +
								   "'"+financial_Year+"', "+daily_inv_qty[i]+", "+daily_inv_amt_usd[i]+", " +
								   ""+daily_inv_amt_inr[i]+", "+exch_rate_cd+", "+nf2.format(Double.parseDouble(exch_rate_value[j]))+", " +
								   ""+user_cd+", sysdate, 'Y', '"+contract_type+"')";
				}
				
				total_amt_inr += Double.parseDouble(daily_inv_amt_inr[i]);
				total_amt_usd += Double.parseDouble(daily_inv_amt_usd[i]);
				
			////System.out.println("STEP-3:DLNG_INVOICE_DTL: "+queryString2);
				
				stmt2.executeUpdate(queryString2);				
			}
			
			if(offspec_flag.trim().equalsIgnoreCase("Y"))
			{
				if(!offspec_qty.trim().equalsIgnoreCase("") && !offspec_rate.trim().equalsIgnoreCase(""))
				{
					total_amt_usd += Double.parseDouble(offspec_qty)*Double.parseDouble(offspec_rate);
					total_amt_inr = total_amt_usd*Double.parseDouble(nf2.format(Double.parseDouble(exch_rate_val)));
				}
			}
			//////System.out.println("TOTAL INR------>>"+total_amt_inr);
			//////System.out.println("TOTAL USD----->>"+total_amt_usd);
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
							  "A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				
				////System.out.println("STEP-4:FMS7_CUSTOMER_TAX_STRUCT_DTL:  For "+customer_abbr+" = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_Structure_Cd = "0";
				}
			}
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							  "A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
					 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
					 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				
				////System.out.println("STEP-4A:FMS7_CUSTOMER_TAX_STRUCT_DTL: For "+customer_abbr+" = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_Structure_Cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_Structure_Cd = "0";
				}
			}
			
			
		//NB20140904:Adjustment portion for tax calculation 
			String invgrossadjustedusd="";
			String invadjustmentamtinr="";
			String invgrossadjustedinr="";
			String invadjustmentamtusd="";
			double total_amt_inr1=total_amt_inr;
			String advance_price_usd="";
		
	
		//	////System.out.println("NET--------"+total_amt_inr);
			NumberFormat nf5=new DecimalFormat("0.00");
			if(adjustadvflag.equalsIgnoreCase("Y"))
	 		{
				String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
				String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
				String adjustsign = request.getParameter("adjustsign")==null?"-":request.getParameter("adjustsign");
			//	////System.out.println("INSIDE IF----------222222222222---"+total_amt_inr+"++++"+total_amt_usd);
				
				
				total_amt_inr=Double.parseDouble(nf5.format(total_amt_inr));
				total_amt_usd=Double.parseDouble(nf5.format(total_amt_usd));
				//	Tariff
				if(TariffFlag.equalsIgnoreCase("Y"))
		 		{
					advance_price_usd=request.getParameter("gross_USD_salespriceINR");
					if(currency.equalsIgnoreCase("0"))
					{
				//		////System.out.println("first if..");
						invadjustmentamtinr=""+(Double.parseDouble(adjustamt)*Double.parseDouble(exch_rate_val));
						invadjustmentamtinr=nf5.format(Double.parseDouble(invadjustmentamtinr));
						invadjustmentamtusd=adjustamt;
					//	//System.out.println("............."+invadjustmentamtinr);
					}
					else
					{
						invadjustmentamtinr=adjustamt;
						invadjustmentamtusd=""+(Double.parseDouble(adjustamt)/Double.parseDouble(exch_rate_val));
						invadjustmentamtusd=nf5.format(Double.parseDouble(invadjustmentamtusd));
					}
					
					if(currency.equalsIgnoreCase("0"))
					{
						////System.out.println("first if.2."+currency);
						if(adjustsign.equalsIgnoreCase("1"))
						{
					//		//System.out.println("first if.3...");
							invgrossadjustedusd=""+((Double.parseDouble(advance_price_usd))-Double.parseDouble(invadjustmentamtusd));
							invgrossadjustedinr=""+((Double.parseDouble(inv_inr_amt_tariff))-Double.parseDouble(invadjustmentamtinr));
					//		//System.out.println("............."+inv_inr_amt_tariff);
						}
						else if(adjustsign.equalsIgnoreCase("2"))
						{
							invgrossadjustedusd=""+((Double.parseDouble(advance_price_usd))+Double.parseDouble(invadjustmentamtusd));
							invgrossadjustedinr=""+((Double.parseDouble(inv_inr_amt_tariff))+Double.parseDouble(invadjustmentamtinr));
						}
						//String tempcalamt=""+(Double.parseDouble(total_qty)*Double.parseDouble(tariff));
						String tempcalamt1=""+(Double.parseDouble(invgrossadjustedinr)); //+Double.parseDouble(tempcalamt));
				//		//System.out.println("NET@222222--"+tempcalamt1);
						total_amt_inr=Double.parseDouble(tempcalamt1);
					}
					else
					{
						double temp_inr=Double.parseDouble(inv_inr_amt_tariff);
						if(adjustsign.equalsIgnoreCase("1"))
						{
							//invgrossadjustedusd=""+((total_amt_usd)-Double.parseDouble(invadjustmentamtusd));
							invgrossadjustedinr=""+((temp_inr)-Double.parseDouble(invadjustmentamtinr));
						}
						else if(adjustsign.equalsIgnoreCase("2"))
						{
							//invgrossadjustedusd=""+((total_amt_usd)+Double.parseDouble(invadjustmentamtusd));
							invgrossadjustedinr=""+((temp_inr)+Double.parseDouble(invadjustmentamtinr));
						}
						invgrossadjustedusd=""+(Double.parseDouble(invgrossadjustedinr)/Double.parseDouble(exch_rate_val));
						invgrossadjustedusd=nf5.format(Double.parseDouble(invgrossadjustedusd));
						total_amt_inr=Double.parseDouble(invgrossadjustedinr);
					}
					
					total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
					total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
		 		}
				else
				{	
					
					if(currency.equalsIgnoreCase("0"))
					{
						invadjustmentamtinr=""+(Double.parseDouble(adjustamt)*Double.parseDouble(exch_rate_val));
						invadjustmentamtinr=nf5.format(Double.parseDouble(invadjustmentamtinr));
						invadjustmentamtusd=adjustamt;
					}
					else
					{
						invadjustmentamtinr=adjustamt;
						invadjustmentamtusd=""+(Double.parseDouble(adjustamt)/Double.parseDouble(exch_rate_val));
						invadjustmentamtusd=nf5.format(Double.parseDouble(invadjustmentamtusd));
					}
					
				//	//System.out.println("INSIDE IF----------222222222222---"+invadjustmentamtinr+"+++"+invadjustmentamtusd);
					if(adjustsign.equalsIgnoreCase("1"))
					{
						invgrossadjustedusd=""+((total_amt_usd)-Double.parseDouble(invadjustmentamtusd));
					//	//System.out.println("..."+total_amt_usd+".."+total_amt_inr);
						invgrossadjustedinr=""+((total_amt_inr)-Double.parseDouble(invadjustmentamtinr));
					}
					else if(adjustsign.equalsIgnoreCase("2"))
					{
						invgrossadjustedusd=""+((total_amt_usd)+Double.parseDouble(invadjustmentamtusd));
						invgrossadjustedinr=""+((total_amt_inr)+Double.parseDouble(invadjustmentamtinr));
					}
					total_amt_inr=Double.parseDouble(invgrossadjustedinr);
					total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
					total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
			//		//System.out.println("INSIDE IF----------222222222222---"+total_amt_inr);
				}
				
	 		}
			else
			{
				if(TariffFlag.equalsIgnoreCase("Y"))
		 		{
					total_amt_inr=Double.parseDouble(inv_inr_amt_tariff);
					total_amt_usd=total_amt_inr/Double.parseDouble(exch_rate_val);
					total_amt_usd=Double.parseDouble(""+nf5.format(total_amt_usd));
					
		 		}
			}
			////System.out.println("double_final_gross_amt_inr"+request.getParameter("double_final_gross_amt_inr"));
			////System.out.println("total_amt_usd"+total_amt_usd+"==total_amt_inr=="+total_amt_inr);
			
		////////NB
			
			Vector tax_code = new Vector();
			Vector tax_amount = new Vector();

			queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
						  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_Structure_Cd+" AND " +
	 					  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_Structure_Cd+" AND " +
	 					  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
			////System.out.println("STEP-5:FMS7_TAX_STRUCTURE_DTL: For "+customer_abbr+" = "+queryString);
			rset=stmt.executeQuery(queryString);
	 		while(rset.next())
	 		{
	 			tax_cd = rset.getString(1);
	 			
	 			if(rset.getString(3).equals("1"))
	 			{
	 				tax_amt = (total_amt_inr*Double.parseDouble(rset.getString(2)))/100;
	 			}
	 			else if(rset.getString(3).equals("2"))
	 			{
	 				queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_Structure_Cd+" AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_Structure_Cd+" AND " +
								  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
	 				////System.out.println("STEP-5A:FMS7_TAX_STRUCTURE_DTL:  Is Dependent On Other Tax Value For "+customer_abbr+" = "+queryString1);
	 	 	 		rset1=stmt1.executeQuery(queryString1);
	 	 	 		if(rset1.next())
	 	 	 		{
 	 	 	 			if(rset1.getString(3).equals("1"))
 	 	 				{
 	 	 					tax_amt = (total_amt_inr*Double.parseDouble(rset1.getString(2)))/100;
 	 	 				}
 	 	 				
 	 	 	 			tax_amt = (tax_amt*Double.parseDouble(rset.getString(2)))/100;
	 	 	 		}
	 	 	 		else
	 	 	 		{
	 	 	 			tax_amt = 0;
	 	 	 		}
	 			}
	 			else
	 			{
	 				tax_amt = 0;
	 			}
	 			
	 			tax_code.add(tax_cd);
	 			tax_amount.add(nf.format(tax_amt));
	 		}
	 		
	 		for(int i=0; i<tax_code.size(); i++)
	 		{
	 		//JHP20120131	queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.elementAt(i)+"";
	 			
	 		queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+tax_code.firstElement()+"";
	 		
	 			////System.out.println("STEP-6:FMS7_TAX_STRUCTURE_DTL: Tax Name = "+queryString);
	 			rset = stmt.executeQuery(queryString);
	 			if(rset.next())
	 			{
	 				String tax_nm = rset.getString(1).trim()==null?"":rset.getString(1).trim();
	 				
	 				if(tax_nm.contains("VAT"))
	 				{
	 					tax_flag = "V";
	 				}
	 				else if(tax_nm.contains("CST"))
	 				{
	 					tax_flag = "C";
	 				}
	 				else if(tax_nm.contains("ST"))
	 				{
	 					tax_flag = "S";
	 				}
	 				else 
	 				{
	 					tax_flag = "S";
	 				}
	 			}
	 			total_tax += Double.parseDouble(""+tax_amount.elementAt(i));
	 		}
	 		
	 		total_net_amt_inr = total_amt_inr+total_tax;
	 		
	 		// 02/09/2014 NB Adjust advance payment
	 		
	 		String mapping_id=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
 			String inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+invoice_date;
 			String del_inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+original_inv_dt;	
 			////System.out.println(del_inv_seq_no);
 			String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id+"' AND INV_SEQ_NO='"+del_inv_seq_no+"' ";
 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
 				String mapid[]=mapping_id_LTCORA_CN.split("-");
 				query_del+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
			}
 			////System.out.println(mapping_id+" :STEP-7:DELETE:FMS7_INV_COMPO_DTL: "+query_del);
 			stmt.executeUpdate(query_del);	
 			
	 		if(adjustadvflag.equalsIgnoreCase("Y") || TariffFlag.equalsIgnoreCase("Y") || DiscountFlag.equalsIgnoreCase("Y"))//NB2010902 Added If clause for advance clause
	 		{	 ////System.out.println("INV: IN ");
	 			//total_amt_inr=total_amt_inr1;
				String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
				String advrecevial = request.getParameter("recevial")==null?"":request.getParameter("recevial");
				String adjustamt = request.getParameter("adjustamt")==null?"":request.getParameter("adjustamt");
				String priceINR2_advance_adjust = request.getParameter("priceINR2")==null?"":request.getParameter("priceINR2");
				
				String hiddenadvnclength = request.getParameter("hiddenadvnclength")==null?"":request.getParameter("hiddenadvnclength");
				String adjustsign = request.getParameter("adjustsign")==null?"1":request.getParameter("adjustsign");

				String advamount[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advcur[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advadt[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advadjustedamt[]=new String[Integer.parseInt(hiddenadvnclength)];
				String advseq_no[]=new String[Integer.parseInt(hiddenadvnclength)];
				
				for(int i=0;i<Integer.parseInt(hiddenadvnclength);i++)
				{
					advamount[i]= request.getParameter("advamount"+i)==null?"":request.getParameter("advamount"+i);
					advcur[i]= request.getParameter("advcur"+i)==null?"":request.getParameter("advcur"+i);
					advadt[i]= request.getParameter("advadt"+i)==null?"":request.getParameter("advadt"+i);
					advseq_no[i]= request.getParameter("advseq_no"+i)==null?"":request.getParameter("advseq_no"+i);
					advadjustedamt[i]= request.getParameter("advadjustedamt"+i)==null?"":request.getParameter("advadjustedamt"+i);
					
		//			//System.out.println("ADVNCE AMT-------1111-----"+advamount[i]);
				}
				//String remark_adj = request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
				//String remark_adj = request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
				String tmpcur="";
				int currency_id=1;
	 			String tmprecv="";
	 			if(currency.equalsIgnoreCase("0"))
	 			{
	 				tmpcur="U";
	 				currency_id=2;
	 			}
	 			else
	 			{
	 				tmpcur="I";
	 				currency_id=1;
	 			}
	 			if(advrecevial.equalsIgnoreCase("0"))
	 			{
	 				tmprecv="Y";
	 			}
	 			else
	 			{
	 				tmprecv="N";
	 			}		
	 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
				   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
				   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
				   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
				   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
				   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag, " +
				   "INV_AMT_USD, INV_AMT_INR, INV_CUR_FLAG," +
				   "MAPPING_ID" +//ADDED FOR LTCORA AND CN
				   ",ADV_INV_NO,ADV_INV_DT,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG) " + //ADDED FOR ADVANCE INVOICE NO
				   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
				   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
				   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
				   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
				   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
				   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
				   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
				   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
				   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
				   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), 'Y', " +
				   "'"+raw_amt_usd+"','"+raw_amt_inr+"','"+inv_cur_flag+"'," +
				   "'"+mapping_id_LTCORA_CN+"'" +//ADDED FOR LTCORA AND CN
				   ",'"+adv_inv_no+"',TO_DATE('"+adv_inv_dt+"','DD/MM/YYYY'),TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+pay_modee+"' ,'"+advadjFlag+"' )"; //ADDED FOR ADVANCE INVOICE NO
	 			
	 			////System.out.println("STEP-8:INSERT:DLNG_INVOICE_MST:Adv Adjust= "+queryString1);
				
	 			stmt1.executeUpdate(queryString1);
	 			String tempseqno=fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no;
				
//	 			////System.out.println("value of Tariffflag..."+TariffFlag);
//	 			////System.out.println("value of DiscountFlag..."+DiscountFlag);
	 	//		////System.out.println("value of invoice_date..."+invoice_date);
	 				 			
	 			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{
	 				String mapid[]=mapping_id_LTCORA_CN.split("-");
	 				if(adjustadvflag.equalsIgnoreCase("Y"))
		 			{
		 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust)/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 			//	////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(TariffFlag.equalsIgnoreCase("Y"))
		 			{
		 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(DiscountFlag.equalsIgnoreCase("Y"))
		 			{
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR,LTCORA_NO,LTCORA_REV_NO)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"','"+mapid[1]+"','"+mapid[2]+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
	 				
				}
	 			else
	 			{
	 				if(adjustadvflag.equalsIgnoreCase("Y"))
		 			{
		 				 advance_price_usd=""+Double.parseDouble(priceINR2_advance_adjust)/Double.parseDouble(exch_rate_val);
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
		 						" VALUES('"+mapping_id+"','"+inv_seq_no+"','1','"+adjustamt+"','"+currency_id+"','0','"+priceINR2_advance_adjust+"','"+advance_price_usd+"','"+tmprecv+"','"+remark_adj+"','"+adjustadvflag+"','"+adjustsign+"','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(TariffFlag.equalsIgnoreCase("Y"))
		 			{
		 				advance_price_usd=request.getParameter("gross_USD_salespriceINR");
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','3','"+tariff+"','"+currency_id+"','"+total_tariff+"','"+inv_inr_amt_tariff+"','"+advance_price_usd+"','','','"+TariffFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			if(DiscountFlag.equalsIgnoreCase("Y"))
		 			{
		 				queryString1="INSERT INTO FMS7_INV_COMPO_DTL(MAPPING_ID,INV_SEQ_NO,PRICE_CD,AMOUNT,CURRENCY,TOTAL_TARIFF,INV_AMT_INR,INV_AMT_USD,REC_FLAG,REMARK,FLAG,OPERATION,BASIC_INV_AMT_USD,BASIC_INV_AMT_INR)" +
							" VALUES('"+mapping_id+"','"+inv_seq_no+"','2','"+discount_value+"','"+currency_id+"','0','"+raw_amt_inr+"','"+raw_amt_usd+"','','','"+DiscountFlag+"','2','"+raw_amt_usd+"','"+raw_amt_inr+"') ";
		 				////System.out.println(".............................................."+queryString1);
		 				stmt.executeUpdate(queryString1);
		 			}
		 			
	 			}
	 			
//				for(int i=0;i<Integer.parseInt(hiddenadvnclength);i++)
//				{
//					
//					String query="INSERT INTO FMS7_PARTY_ADV_ADJ_DTL (inv_no, customer_cd, plant_seq_no, contract_type," +
//							" hlpl_inv_seq_no, adv_recv_dt, adv_cur_inr_usd, adv_adj_amt, adv_adj_dt,ADV_ADJ_SIGN,adv_seq_no,financial_year)" +
//							" VALUES ('"+tempseqno+"','"+customer_cd+"','"+customer_plant_seq_no+"','"+contract_type+"'," +
//							" '"+Integer.parseInt(hlpl_Inv_Seq_No)+"',TO_DATE('"+advadt[i]+"','DD/MM/YYYY'),'"+advcur[i].trim()+"', " +
//							"'"+advadjustedamt[i]+"',TO_DATE(SYSDATE,'DD/MM/YYYY'),'"+adjustsign+"','"+advseq_no[i]+"','"+financial_Year+"')";
//					
//			//		//System.out.println("QUERY FOR INSERTING ADJUSTMENT AMT"+query);
//					stmt.executeUpdate(query);
//						
//				}
				msg="Data Submitted Successfully..";
	 		}
	 		else
	 		{	//Before Addition of Adjustment clause (Original)
	 			// //System.out.println("INV: ELSE ");
	 			queryString1 = "INSERT INTO DLNG_INVOICE_MST(fgsa_no, fgsa_rev_no, sn_no, sn_rev_no, customer_cd, " +
						   "plant_seq_no, contract_type, hlpl_inv_seq_no, financial_year, cust_inv_seq_no, " +
						   "period_start_dt, period_end_dt, contact_person_cd, invoice_dt, due_dt, total_qty, sale_price, " +
						   "gross_amt_inr, gross_amt_usd, net_amt_inr, tax_amt_inr, tax_struct_cd, tax_flag, " +
						   "exchg_rate_cd, exchg_rate_dt, exchg_rate_value, exchg_rate_type, remark_1, remark_2, remark_3, " +
						   "exchg_rate_index, offspec_flag, offspec_qty, offspec_rate, emp_cd, ent_dt, flag " +
						   ", INV_CUR_FLAG" +
						   ", MAPPING_ID,USER_DEFINED_DAY,PAY_TYPE,ADV_ADJ_FLG) " + //ADDED FOR LTCORA AND CN)
						   "VALUES("+fgsa_no+", "+fgsa_rev_no+", "+sn_no+", "+sn_rev_no+", "+customer_cd+", " +
						   ""+customer_plant_seq_no+", '"+contract_type+"', "+Integer.parseInt(hlpl_Inv_Seq_No)+", '"+financial_Year+"', " +
						   ""+Integer.parseInt(customer_Inv_Seq_No)+", TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'), " +
						   "TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'), "+contact_person+", TO_DATE('"+invoice_date+"','DD/MM/YYYY'), " +
						   "TO_DATE('"+due_dt+"','DD/MM/YYYY'), "+nf.format(Double.parseDouble(total_qty))+", " +
						   ""+nf2.format(Double.parseDouble(sale_price))+", "+nf.format(total_amt_inr)+", "+nf.format(total_amt_usd)+", " +
						   ""+nf.format(total_net_amt_inr)+", "+nf.format(total_tax)+", "+tax_Structure_Cd+", '"+tax_flag+"', " +
						   ""+exch_rate_cd+", TO_DATE('"+exch_rate_dt+"','DD/MM/YYYY'), "+nf2.format(Double.parseDouble(exch_rate_val))+", " +
						   "'"+exchg_rate_cal_method+"', '"+remark_1+"', '"+remark_2+"', '"+remark_3+"', '"+exchg_rate_ind+"', " +
						   "'"+offspec_flag+"', '"+offspec_qty+"', '"+offspec_rate+"', "+user_cd+", sysdate, 'Y' "+
						   ",'"+inv_cur_flag+"'," + ////20141016
						   "'"+mapping_id_LTCORA_CN+"',TO_DATE('"+particular_date+"','DD/MM/YYYY'),'"+pay_modee+"' ,'"+advadjFlag+"')"; //ADDED FOR LTCORA AND CN) 			
	 			//System.out.println("STEP-8A:INSERT:DLNG_INVOICE_MST: No Adjustment"+queryString1);				
	 			stmt1.executeUpdate(queryString1);	 			
	 		}			
			inv_financial_year = financial_Year;
			hlpl_invoice_sequence_no = hlpl_Inv_Seq_No+"/"+financial_Year;
			//System.out.println("<<<<--------END OF MODIFY: HLPL_SEQ_NO: "+hlpl_invoice_sequence_no);
			msg = "Billing Details Of "+hlpl_invoice_sequence_no+" For "+customer_abbr+" Has Been Submitted Successfully !!!";
			response.sendRedirect("../sales_invoice/frm_invoice_dtl.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&remark_1="+remark_1+"&remark_2="+remark_2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&mapping_id="+mapping_id_LTCORA_CN);
//			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Billing Details Submission Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_invoice_dtl.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&customer_inv_seq_no="+customer_inv_seq_no+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&bill_cycle="+bill_cycle+"&year="+year+"&month="+month+"&remark_1="+remark_1+"&remark_2="+remark_2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate);	
		}		
	}//End Of Method submitBillingDetails() ...
	
	public void deleteInvoice(HttpServletRequest request,HttpServletResponse response) throws IOException	//BK20151124	
	{

		methodName = "deleteInvoice()";
		form_name = "frm_sales_invoice_preperation.jsp";
		//System.out.println("---FRM deleteInvoice() starts!---");
		
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no");
		String inv_seq_no = request.getParameter("inv_seq_no")==null?"0":request.getParameter("inv_seq_no");
		String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String mapping_id=request.getParameter("del_mapping_id")==null?"":request.getParameter("del_mapping_id");
		
		//		inv_financial_year
		//		hlpl_inv_seq_no
		//		hlpl_inv_no
		//		contract_type
		//		del_mapping_id
		
		//System.out.println("JAVA hlpl_inv_seq_no: "+hlpl_inv_seq_no+":");
		//System.out.println("JAVA inv_seq_no: "+inv_seq_no+":");
		//System.out.println("JAVA inv_financial_year: "+inv_financial_year+":");
		//System.out.println("JAVA month: "+month+":");
		//System.out.println("JAVA year: "+year+":");
		//System.out.println("JAVA bill_cycle: "+bill_cycle+":");
		//System.out.println("JAVA contract_type: "+contract_type+":");
		//System.out.println("JAVA mapping_id: "+mapping_id+":");
		
		try
		{
			
			//String mapping_id=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+sn_rev_no+":"+customer_plant_seq_no;
 			//String inv_seq_no=contract_type+":"+financial_Year+":"+Integer.parseInt(hlpl_Inv_Seq_No)+":"+invoice_date;
 				
 			//String query_del="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+mapping_id+"' AND INV_SEQ_NO='"+inv_seq_no+"' ";
 			//stmt.executeUpdate(query_del);
			
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			String net_amt_inr="";
			String inv_dt="";
			String inv_cust_cd="";
			String inv_plant_seq_no="";
			String inv_bill_strt_dt="";
			String inv_dt_bill_strt_dt="";
			
			//queryString="SELECT SN_NO,HLPL_INV_SEQ_NO,INVOICE_DT,TAX_STRUCT_CD FROM DLNG_INVOICE_MST where hlpl_inv_seq_no >= 301 and financial_year='2015-2016' and contract_type='C';";
			queryString="SELECT count(*) FROM DLNG_INVOICE_MST where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
			rset=stmt.executeQuery(queryString);
			//System.out.println("---FRM queryString---: "+queryString);
			while(rset.next())
			{
				int cnt=rset.getInt(1);
				if(cnt>0)
				{
					String query="DELETE FROM DLNG_INVOICE_MST where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
					//stmt1.executeUpdate(query);
				}
			}
			
			//queryString1="SELECT ALLOCATION_DT,daily_qty,HLPL_INV_SEQ_NO,FINANCIAL_YEAR FROM DLNG_INVOICE_DTL where hlpl_inv_seq_no >= 301 and financial_year='2015-2016' AND CONTRACT_TYPE='C' order by hlpl_inv_seq_no";
			queryString1="SELECT count(*) FROM DLNG_INVOICE_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
			rset1=stmt1.executeQuery(queryString1);
			//System.out.println("---FRM queryString1---: "+queryString1);
			while(rset1.next())
			{
				int cnt1=rset1.getInt(1);
				if(cnt1>0)
				{
					String query1="DELETE FROM DLNG_INVOICE_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
					//stmt2.executeUpdate(query1);
				}
			}
			
			queryString2="SELECT count(*) FROM FMS7_INV_COMPO_DTL where inv_seq_no = '"+inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
			rset2=stmt2.executeQuery(queryString2);
			//System.out.println("---FRM queryString2---: "+queryString2);
			while(rset2.next())
			{
				int cnt2=rset2.getInt(1);
				if(cnt2>0)
				{
					String query2="DELETE FROM FMS7_INV_COMPO_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
					//stmt2.executeUpdate(query2);
				}
			}
			
			queryString3="SELECT count(*) FROM LOG_DLNG_INVOICE_MST where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
			rset3=stmt3.executeQuery(queryString3);
			//System.out.println("---FRM queryString3---: "+queryString3);
			while(rset3.next())
			{
				int cnt3=rset3.getInt(1);
				if(cnt3>0)
				{
					String query3="DELETE FROM LOG_DLNG_INVOICE_MST where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
					//stmt3.executeUpdate(query3);
				}
			}
			
			queryString4="SELECT count(*) FROM LOG_DLNG_INVOICE_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
			rset4=stmt4.executeQuery(queryString4);
			//System.out.println("---FRM queryString4---: "+queryString4);
			while(rset4.next())
			{
				int cnt4=rset4.getInt(1);
				if(cnt4>0)
				{
					String query4="DELETE FROM LOG_DLNG_INVOICE_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
					//stmt4.executeUpdate(query4);
				}
			}
			
			String queryString5="SELECT count(*) FROM LOG_FMS7_INV_COMPO_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
			rset1=stmt1.executeQuery(queryString5);
			//System.out.println("---FRM queryString5---: "+queryString5);
			while(rset1.next())
			{
				int cnt5=rset1.getInt(1);
				if(cnt5>0)
				{
					String query5="DELETE FROM LOG_FMS7_INV_COMPO_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
					//stmt1.executeUpdate(query5);
				}
			}
			
			String queryString6="SELECT count(*) FROM LOG_FMS7_INV_FILE_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
			rset2=stmt2.executeQuery(queryString6);
			//System.out.println("---FRM queryString6---: "+queryString6);
			while(rset2.next())
			{
				int cnt6=rset2.getInt(1);
				if(cnt6>0)
				{
					String query6="DELETE FROM LOG_FMS7_INV_FILE_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
					//stmt2.executeUpdate(query6);
				}
			}
			
			String queryString7="SELECT count(*) FROM LOG_FMS7_INV_ADD_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
			rset3=stmt3.executeQuery(queryString7);
			//System.out.println("---FRM queryString7---: "+queryString7);
			while(rset3.next())
			{
				int cnt7=rset3.getInt(1);
				if(cnt7>0)
				{
					String query7="DELETE FROM LOG_FMS7_INV_ADD_DTL where hlpl_inv_seq_no = '"+hlpl_inv_seq_no+"' and financial_year='"+inv_financial_year+"' and contract_type='"+contract_type+"'";
					//stmt3.executeUpdate(query7);
				}
			}
			
			HttpSession sess = request.getSession();
			String invoice_pdf_path = sess.getAttribute("invoice_pdf_path").toString();
			String[] file_bunch_qtr= null;
			
			String invoice_bench_date="10/03/2015";
			
			//Date d1=new Date(invoice_bench_date);
			//Date d2=new Date(inv_dt);
			String tempD1[]=invoice_bench_date.split("/");
			String d1=tempD1[2]+tempD1[1]+tempD1[0];
			
			String tempD2[]=inv_dt.split("/");
			String d2=tempD2[2]+tempD2[1]+tempD2[0];
			
			if(Integer.parseInt(d2)>Integer.parseInt(d1))
			{
				Vector title_vect=new Vector();
				title_vect.add("O");
				title_vect.add("D");
				title_vect.add("T");
				title_vect.add("Q");
				
				for(int h=0;h<4;h++)
			    {
					String invoice_pdf_path1=invoice_pdf_path;
					File lst_qtr= new File(invoice_pdf_path1);
					////System.out.println("invoice_pdf_path-->>>>"+invoice_pdf_path1);
					file_bunch_qtr=lst_qtr.list();
					int count1=0;
					for(int j=0;j<file_bunch_qtr.length;j++)
				    {
						
						String file=file_bunch_qtr[j];
						////System.out.println("invoice_date1--"+inv_dt_bill_strt_dt);
						//String f1="INVOICE-"+inv_dt_bill_strt_dt.trim().substring(0,2)+inv_dt_bill_strt_dt.trim().substring(3,5)+inv_dt_bill_strt_dt.trim().substring(6)+"-"+customer_abbr+"-"+cust_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+title_vect.elementAt(h);
						String f1="INVOICE-"+inv_dt_bill_strt_dt.trim().substring(0,2)+inv_dt_bill_strt_dt.trim().substring(3,5)+inv_dt_bill_strt_dt.trim().substring(6)+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+title_vect.elementAt(h);
						////System.out.println("f1--"+f1);
						if(file.startsWith(f1))
						{
							count1++;
							
							invoice_pdf_path1=invoice_pdf_path1+"//"+file;
				            File f2=new File(invoice_pdf_path1);
				            if(f2.exists())
				            {
				            	//System.out.println("f2 exists: "+f2);
				        	    //f2.delete();
				            }
							/*String context_nm = request.getContextPath();
							String server_nm = request.getServerName();
							String server_port = ""+request.getServerPort();
							  //System.out.println("invoice_pdf_path--"+invoice_pdf_path);
							String url_start = "http://"+server_nm+":"+server_port+context_nm;
							
							String pdfpath = invoice_pdf_path;
							pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
							
							pdfpath = url_start+"/pdf_reports/sales_invoice/pdf_files"+pdfpath;*/
						}
					
			    }
			    }
			}
			else
			{
				File lst_qtr= new File(invoice_pdf_path);
				file_bunch_qtr=lst_qtr.list();
				int count1=0;
				for ( int j=0;j<file_bunch_qtr.length;j++ )
			    {
					
					String file=file_bunch_qtr[j];
					////System.out.println("invoice_date1--"+inv_dt_bill_strt_dt);
					//String f1="INVOICE-"+inv_dt_bill_strt_dt.trim().substring(0,2)+inv_dt_bill_strt_dt.trim().substring(3,5)+inv_dt_bill_strt_dt.trim().substring(6)+"-"+customer_abbr+"-"+cust_plant_nm;
					String f1="INVOICE-"+inv_dt_bill_strt_dt.trim().substring(0,2)+inv_dt_bill_strt_dt.trim().substring(3,5)+inv_dt_bill_strt_dt.trim().substring(6);
					////System.out.println("f1--"+f1);
					if(file.startsWith(f1))
					{
						count1++;
						
						invoice_pdf_path=invoice_pdf_path+"//"+file;
			           File f2=new File(invoice_pdf_path);
			           if(f2.exists())
			           {
			        	   //System.out.println("f2 exists: "+f2);
			        	   //f2.delete();
			           }
						/*String context_nm = request.getContextPath();
						String server_nm = request.getServerName();
						String server_port = ""+request.getServerPort();
						  //System.out.println("invoice_pdf_path--"+invoice_pdf_path);
						String url_start = "http://"+server_nm+":"+server_port+context_nm;
						
						String pdfpath = invoice_pdf_path;
						pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
						
						pdfpath = url_start+"/pdf_reports/sales_invoice/pdf_files"+pdfpath;*/
						
					}
					
			    }
			}
			
			/////////
			
			msg = "Billing Details Of "+hlpl_inv_no+" Has Been Deleted Successfully !!!";
			response.sendRedirect("../sales_invoice/frm_sales_invoice_preperation.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);
			dbcon.commit();
			//System.out.println("---FRM deleteInvoice() ends!---");
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Billing Details Deletion Process Failed For "+hlpl_inv_no+" Invoice !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_sales_invoice_preperation.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);	
		}		
	
	}
	
	//Following Method Is Defined By Samik Shah On 16th June, 2010 ...
	public void deleteInvoice_old_20151124(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "deleteInvoice()";
		form_name = "frm_sales_invoice_preperation.jsp";
		
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no");
		String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String mapping_id=request.getParameter("del_mapping_id")==null?"":request.getParameter("del_mapping_id");
		
		//System.out.println("JAVA hlpl_inv_seq_no: "+hlpl_inv_seq_no+":");
		//System.out.println("JAVA inv_financial_year: "+inv_financial_year+":");
		//System.out.println("JAVA month: "+month+":");
		//System.out.println("JAVA year: "+year+":");
		//System.out.println("JAVA bill_cycle: "+bill_cycle+":");
		//System.out.println("JAVA contract_type: "+contract_type+":");
		//System.out.println("JAVA mapping_id: "+mapping_id+":");
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			String net_amt_inr="";
			String inv_dt="";
			String inv_cust_cd="";
			String inv_plant_seq_no="";
			String inv_bill_strt_dt="";
			String inv_dt_bill_strt_dt="";
			
			queryString = "SELECT SN_NO,SN_REV_NO,FGSA_NO,FGSA_REV_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE" +
					",to_char(PERIOD_END_DT,'dd/mm/yyyy'),HLPL_INV_SEQ_NO,FINANCIAL_YEAR, NET_AMT_INR,MAPPING_ID,to_char(INVOICE_DT,'dd/mm/yyyy'),to_char(PERIOD_START_DT,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE " +
						"financial_year='"+inv_financial_year+"' AND " +
						"hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
						"contract_type='"+contract_type+"'";
			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
				queryString+=" AND MAPPING_ID='"+mapping_id+"'";
			}
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				inv_dt=rset.getString(13);
				inv_dt_bill_strt_dt=rset.getString(8);
				inv_cust_cd=rset.getString(5);
				inv_plant_seq_no=rset.getString(6);
				inv_bill_strt_dt=rset.getString(14);
				
				String map_id=rset.getString(5)+":"+rset.getString(3)+":"+rset.getString(4)+":"+rset.getString(1)+":" +
				""+rset.getString(2)+":"+rset.getString(6)+"";
				String inv_no=""+rset.getString(7)+":"+rset.getString(10)+":"+rset.getString(9)+":"+rset.getString(13);
		
				
				
				queryString3="DELETE FROM FMS7_INV_COMPO_DTL WHERE MAPPING_ID='"+map_id+"' and INV_SEQ_NO='"+inv_no+"'";
				////System.out.println("queryString3 delete..."+queryString3);
				if(rset.getString(7).equalsIgnoreCase("T") || rset.getString(7).equalsIgnoreCase("C"))
				{
					String temp_map_id_ltcora_cn[]=rset.getString(12).split("-");
					queryString3+=" AND LTCORA_NO='"+temp_map_id_ltcora_cn[1]+"' AND LTCORA_REV_NO='"+temp_map_id_ltcora_cn[2]+"'";
				}
				//stmt2.executeUpdate(queryString3);
				
				
				String q2="SELECT COUNT(*) FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+rset.getString(1)+"' AND " +
						"SN_REV_NO='"+rset.getString(2)+"' AND FGSA_NO='"+rset.getString(3)+"' AND FGSA_REV_NO='"+rset.getString(4)+"' " +
						"AND CUSTOMER_CD='"+rset.getString(5)+"' AND PLANT_SEQ_NO='"+rset.getString(6)+"' AND " +
						"CONTRACT_TYPE='"+rset.getString(7)+"' AND PERIOD_END_DT=to_date('"+rset.getString(8)+"','DD/MM/YYYY') " +
						"AND HLPL_INV_SEQ_NO='"+rset.getString(9)+"' AND FINANCIAL_YEAR='"+rset.getString(10)+"' ";
				rset3=stmt3.executeQuery(q2);
				int count=0;
				if(rset3.next())
				{
					count=rset3.getInt(1);
				}
				if(count>0)
				{
					String q3="DELETE FROM DLNG_INVOICE_DEL_LOG WHERE SN_NO='"+rset.getString(1)+"' AND " +
					"SN_REV_NO='"+rset.getString(2)+"' AND FGSA_NO='"+rset.getString(3)+"' AND FGSA_REV_NO='"+rset.getString(4)+"' " +
					"AND CUSTOMER_CD='"+rset.getString(5)+"' AND PLANT_SEQ_NO='"+rset.getString(6)+"' AND " +
					"CONTRACT_TYPE='"+rset.getString(7)+"' AND PERIOD_END_DT=to_date('"+rset.getString(8)+"','DD/MM/YYYY') " +
					"AND HLPL_INV_SEQ_NO='"+rset.getString(9)+"' AND FINANCIAL_YEAR='"+rset.getString(10)+"' ";
					////System.out.println("QUERY to insert into delete log..Q3."+q3);
					//stmt3.executeUpdate(q3);
				}
				
				
				
				String temp_id=rset.getString(12)==null?"":rset.getString(12);
				String q1="INSERT INTO DLNG_INVOICE_DEL_LOG(SN_NO,SN_REV_NO,FGSA_NO,FGSA_REV_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE" +
					",PERIOD_END_DT,HLPL_INV_SEQ_NO,FINANCIAL_YEAR, NET_AMT_INR,RESTORE_FLAG,ENT_BY,ENT_DT,FLAG,MAPPING_ID) " +
					"VALUES ('"+rset.getString(1)+"','"+rset.getString(2)+"','"+rset.getString(3)+"','"+rset.getString(4)+"'" +
							",'"+rset.getString(5)+"','"+rset.getString(6)+"','"+rset.getString(7)+"',to_date('"+rset.getString(8)+"','dd/mm/yyyy')" +
									",'"+rset.getString(9)+"','"+rset.getString(10)+"','"+rset.getString(11)+"'" +
											",'N','"+user_cd+"',sysdate,'Y','"+temp_id+"')";
				////System.out.println("QUERY to insert into delete log..."+q1);
				//stmt4.executeUpdate(q1);
				
				String hlpl_inv_no_disp="";
				if(Integer.parseInt(hlpl_inv_seq_no)<10)
				{
					hlpl_inv_no_disp="000"+hlpl_inv_seq_no+"/"+inv_financial_year;
				}
				else if(Integer.parseInt(hlpl_inv_seq_no)<100) 
				{
					hlpl_inv_no_disp="00"+hlpl_inv_seq_no+"/"+inv_financial_year;
				}
				else if(Integer.parseInt(hlpl_inv_seq_no)<1000) 
				{
					hlpl_inv_no_disp="0"+hlpl_inv_seq_no+"/"+inv_financial_year;
				}
				else
				{
					hlpl_inv_no_disp=hlpl_inv_seq_no+"/"+inv_financial_year;
				}
				
				String INVOICE_NO = hlpl_inv_no_disp;	
				
				queryString ="SELECT T_CODE FROM FMS7_ACCOUNT_APPROVED_DTL WHERE INV_CARGO_NO='"+INVOICE_NO+"' AND JOURNAL_TYPE='FMSSL' AND CONTRACT_TYPE='"+contract_type+"'";
				if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))//ADDED FOR LTCORA AND CN
				{
					queryString+=" AND MAPPING_ID='"+mapping_id+"'";
				}
				
				////System.out.println("SELECT FMS7_ACCOUNT_APPROVED_DTL: "+queryString);
				rset3=stmt3.executeQuery(queryString);
				if(rset3.next())
				{
					queryString="DELETE FROM FMS7_ACCOUNT_APPROVED_DTL WHERE INV_CARGO_NO='"+INVOICE_NO+"' AND JOURNAL_TYPE='FMSSL' AND CONTRACT_TYPE='"+contract_type+"'";
					if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))//ADDED FOR LTCORA AND CN
					{
						queryString+=" AND MAPPING_ID='"+mapping_id+"'";
					}
					
					////System.out.println("DELETE FMS7_ACCOUNT_APPROVED_DTL : "+queryString);
					//stmt3.executeUpdate(queryString);			
				}
				
			}
			
			
			queryString2 = "DELETE FROM DLNG_INVOICE_DTL WHERE " +
						   "financial_year='"+inv_financial_year+"' AND " +
				  		   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
				  		   "contract_type='"+contract_type+"'";
				
			queryString1 = "DELETE FROM DLNG_INVOICE_MST WHERE financial_year='"+inv_financial_year+"' AND " +
			  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
			  			   "contract_type='"+contract_type+"'";
				
			
			
			////System.out.println("Query For Deleting Records From Invoice Details Table = "+queryString2);
			////System.out.println("Query For Deleting Record From Invoice Master Table = "+queryString1);
				
			//stmt2.executeUpdate(queryString2);
			//stmt1.executeUpdate(queryString1);								
			
			
			//////////delete pdf
			String customer_abbr="";
			String cust_plant_nm="";
			queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+inv_cust_cd+"";
			////System.out.println("Query To FindOut Customer Abbriviation For CN = "+queryString3);
			rset3 = stmt3.executeQuery(queryString3);
			if(rset3.next())
			{
				customer_abbr = rset3.getString(1)==null?"":rset3.getString(1);
			}
			
			queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
			   "WHERE A.customer_cd="+inv_cust_cd+" AND A.seq_no="+inv_plant_seq_no+" " +
			   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
			   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
			   "AND B.eff_dt<=TO_DATE('"+inv_bill_strt_dt+"','DD/MM/YYYY'))";
			//////System.out.println("Customer Plant Name Fetch Query For LTCORA= "+queryString1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				cust_plant_nm=rset1.getString(1);
			}
			else
			{
				cust_plant_nm="";
			}
			
			HttpSession sess = request.getSession();
			String invoice_pdf_path = sess.getAttribute("invoice_pdf_path").toString();
			String[] file_bunch_qtr= null;
			
			String invoice_bench_date="10/03/2015";
			
			//Date d1=new Date(invoice_bench_date);
			//Date d2=new Date(inv_dt);
			String tempD1[]=invoice_bench_date.split("/");
			String d1=tempD1[2]+tempD1[1]+tempD1[0];
			
			String tempD2[]=inv_dt.split("/");
			String d2=tempD2[2]+tempD2[1]+tempD2[0];
			
			if(Integer.parseInt(d2)>Integer.parseInt(d1))
			{
				Vector title_vect=new Vector();
				title_vect.add("O");
				title_vect.add("D");
				title_vect.add("T");
				title_vect.add("Q");
				
				for ( int h=0;h<4;h++ )
			    {
					String invoice_pdf_path1=invoice_pdf_path;
				File lst_qtr= new File(invoice_pdf_path1);
				////System.out.println("invoice_pdf_path-->>>>"+invoice_pdf_path1);
				file_bunch_qtr=lst_qtr.list();
				int count1=0;
				for ( int j=0;j<file_bunch_qtr.length;j++ )
			    {
					
					String file=file_bunch_qtr[j];
					////System.out.println("invoice_date1--"+inv_dt_bill_strt_dt);
					String f1="INVOICE-"+inv_dt_bill_strt_dt.trim().substring(0,2)+inv_dt_bill_strt_dt.trim().substring(3,5)+inv_dt_bill_strt_dt.trim().substring(6)+"-"+customer_abbr+"-"+cust_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+title_vect.elementAt(h);
					////System.out.println("f1--"+f1);
					if(file.startsWith(f1))
					{
						count1++;
						
						invoice_pdf_path1=invoice_pdf_path1+"//"+file;
			           File f2=new File(invoice_pdf_path1);
			           if(f2.exists())
			           {
			        	   //System.out.println("f2 exists: "+f2);
			        	   //f2.delete();
			           }
						/*String context_nm = request.getContextPath();
						String server_nm = request.getServerName();
						String server_port = ""+request.getServerPort();
						  //System.out.println("invoice_pdf_path--"+invoice_pdf_path);
						String url_start = "http://"+server_nm+":"+server_port+context_nm;
						
						String pdfpath = invoice_pdf_path;
						pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
						
						pdfpath = url_start+"/pdf_reports/sales_invoice/pdf_files"+pdfpath;*/
						
						
					}
					
			    }
			    }
			}
			else
			{
				File lst_qtr= new File(invoice_pdf_path);
				file_bunch_qtr=lst_qtr.list();
				int count1=0;
				for ( int j=0;j<file_bunch_qtr.length;j++ )
			    {
					
					String file=file_bunch_qtr[j];
					////System.out.println("invoice_date1--"+inv_dt_bill_strt_dt);
					String f1="INVOICE-"+inv_dt_bill_strt_dt.trim().substring(0,2)+inv_dt_bill_strt_dt.trim().substring(3,5)+inv_dt_bill_strt_dt.trim().substring(6)+"-"+customer_abbr+"-"+cust_plant_nm;
					////System.out.println("f1--"+f1);
					if(file.startsWith(f1))
					{
						count1++;
						
						invoice_pdf_path=invoice_pdf_path+"//"+file;
			           File f2=new File(invoice_pdf_path);
			           if(f2.exists())
			           {
			        	   //System.out.println("f2 exists: "+f2);
			        	   //f2.delete();
			           }
						/*String context_nm = request.getContextPath();
						String server_nm = request.getServerName();
						String server_port = ""+request.getServerPort();
						  //System.out.println("invoice_pdf_path--"+invoice_pdf_path);
						String url_start = "http://"+server_nm+":"+server_port+context_nm;
						
						String pdfpath = invoice_pdf_path;
						pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
						
						pdfpath = url_start+"/pdf_reports/sales_invoice/pdf_files"+pdfpath;*/
						
					}
					
			    }
			}
			
			/////////
			
			msg = "Billing Details Of "+hlpl_inv_no+" Has Been Deleted Successfully !!!";
			response.sendRedirect("../sales_invoice/frm_sales_invoice_preperation.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			msg = "Billing Details Deletion Process Failed For "+hlpl_inv_no+" Invoice !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/frm_sales_invoice_preperation.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);	
		}		
	}//End Of Method deleteInvoice() ...
	
	
	//Following Method Is Defined By Samik Shah On 21st January, 2011 ...
	public void Check_Invoice(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "Check_Invoice()";
		form_name = "frm_sales_invoice_preperation.jsp";
		
		String check_flag = request.getParameter("check_flag")==null?"":request.getParameter("check_flag");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no");
		String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String invno_disp = request.getParameter("invno_disp") ==null?"":request.getParameter("invno_disp");
		
		form_id = "181";
		form_nm = "Invoice Preparation";
		
		try {
			String q = "SELECT FORM_NAME FROM SEC_FORM_MST WHERE FORM_CD='"+form_id+"' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			queryString1 = "UPDATE DLNG_INVOICE_MST SET checked_flag='"+check_flag+"', " +
						   "checked_by='"+user_cd+"', checked_dt=sysdate, " +
						   "authorized_flag=null, authorized_by=null, authorized_dt=null, " +
						   "approved_flag=null, approved_by=null, approved_dt=null " +
						   "WHERE financial_year='"+inv_financial_year+"' AND " +
			  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
			  			   "contract_type='"+contract_type+"' AND FLAG!='A'";
				
			//System.out.println("Query For Updating The Check Status of Invoice = "+queryString1);
				
			stmt1.executeUpdate(queryString1);	
			if(!invno_disp.equals(""))
			{
				hlpl_inv_no = invno_disp;
			}
					
			msg = "Invoice Checking Process Of "+hlpl_inv_no+" Has Been Successfully Completed !!!";
			response.sendRedirect("../sales_invoice/check_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);
			dbcon.commit();
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Invoice Checking Process Failed For "+hlpl_inv_no+" Invoice !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/check_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);	
		}
	}//End Of Check_Invoice() Method ...
	
	
	//Following Method Is Defined By Samik Shah On 21st January, 2011 ...
	public void Approve_Invoice(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		methodName = "Approve_Invoice()";
		form_name = "frm_sales_invoice_preperation.jsp";
		
		String approve_flag = request.getParameter("approve_flag")==null?"":request.getParameter("approve_flag");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no");
		String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String invno_disp = request.getParameter("invno_disp") ==null?"":request.getParameter("invno_disp");
		
		form_id = "181";
		form_nm = "Invoice Preparation";
		
		try {
			String q = "SELECT FORM_NAME FROM SEC_FORM_MST WHERE FORM_CD='"+form_id+"' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			if(approve_flag.trim().equalsIgnoreCase("N"))
			{
				queryString1 = "UPDATE DLNG_INVOICE_MST SET " +
							   "checked_flag=null, checked_by=null, checked_dt=null, " +
							   "authorized_flag=null, authorized_by=null, authorized_dt=null, " +
							   "approved_flag='"+approve_flag+"', " +
							   "approved_by='"+user_cd+"', approved_dt=sysdate " +
							   "WHERE financial_year='"+inv_financial_year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
				  			   "contract_type='"+contract_type+"' AND FLAG!='A'";
			}
			else
			{
				queryString1 = "UPDATE DLNG_INVOICE_MST SET " +
							   "approved_flag='"+approve_flag+"', " +
							   "approved_by='"+user_cd+"', approved_dt=sysdate " +
							   "WHERE financial_year='"+inv_financial_year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
				  			   "contract_type='"+contract_type+"' AND FLAG!='A'";
			}
				
			////System.out.println("Query For Updating The Approving Status of Invoice = "+queryString1);
			stmt1.executeUpdate(queryString1);			
			if(!invno_disp.equals(""))
			{
				hlpl_inv_no = invno_disp;
			}
					
			msg = "Invoice Approving Process Of "+hlpl_inv_no+" Has Been Successfully Completed !!!";
			response.sendRedirect("../sales_invoice/approve_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Invoice Approving Process Failed For "+hlpl_inv_no+" Invoice !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/approve_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);	
		}
	}//End Of Approve_Invoice() Method ...
	///////SB20160602
	public void Approve_Credit(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		////System.out.println("in method approve_credit");
		methodName = "Approve_Invoice()";
		form_name = "frm_sales_invoice_preperation.jsp";
		
		String approve_flag = request.getParameter("approve_flag")==null?"":request.getParameter("approve_flag");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no");
		String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		////System.out.println("------------------>"+approve_flag);
		String invno_disp = request.getParameter("invno_disp")==null?"":request.getParameter("invno_disp");
		
		form_id = "181";
		form_nm = "Invoice Preparation";
		
		try {
			String q = "SELECT FORM_NAME FROM SEC_FORM_MST WHERE FORM_CD='"+form_id+"' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			if(!invno_disp.equals("")) {
				hlpl_inv_no = invno_disp;
			}
			String dr_cr_flag = "",dr_cr_doc_no="";
			queryString1 = "SELECT DR_CR_FLAG,DR_CR_DOC_NO FROM FMS7_DR_CR_NOTE WHERE "
					+ "HLPL_INV_SEQ_NO='"+hlpl_inv_seq_no+"' AND "
					+ "CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+inv_financial_year+"' ";
			rset = stmt.executeQuery(queryString1);
			if(rset.next()) {
				dr_cr_flag = rset.getString(1)==null?"":rset.getString(1);
				dr_cr_doc_no = rset.getString(2)==null?"":rset.getString(2);
			}
			String msg1 = "";
			if(!dr_cr_flag.equals("")) {
				if(dr_cr_flag.equalsIgnoreCase("cr")) {
					msg1 = " Of Credit Note No "+dr_cr_doc_no+" For Invoice No "+hlpl_inv_no+" ";
				} else if(dr_cr_flag.equalsIgnoreCase("dr")) {
					msg1 = " Of Debit Note No "+dr_cr_doc_no+" For Invoice No "+hlpl_inv_no+" ";
				}
			} else {
				msg1 = "For Invoice No "+hlpl_inv_no;
			}
			if(approve_flag.trim().equalsIgnoreCase("N"))
			{
				queryString1 = "UPDATE FMS7_DR_CR_NOTE SET " +
							   "APRV_BY='0',APRV_dt=sysdate  "+//approved_dt=sysdate " +
							   "WHERE financial_year='"+inv_financial_year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
				  			   "contract_type='"+contract_type+"'";
				
				msg = " Approval "+msg1+" Not Done !!!";
			}
			else
			{
				queryString1 = "UPDATE FMS7_DR_CR_NOTE SET " +
							   //"approved_flag='"+approve_flag+"', " +
							   "APRV_BY='"+user_cd+"', APRV_dt=sysdate " +
							   "WHERE financial_year='"+inv_financial_year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
				  			   "contract_type='"+contract_type+"'";
				
				msg = "Approval "+msg1+" Done Successfully !!!";
			}
				
			////System.out.println("UPDATE:CREDIT: FMS7_DR_CR_NOTE:  "+queryString1);
				
			stmt1.executeUpdate(queryString1);								
				
			//msg = "Approved ["+hlpl_inv_no+"] Successfully !!!";
			response.sendRedirect("../sales_invoice/approve_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			msg = "Invoice Approving Process Failed For "+hlpl_inv_no+" Invoice !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			response.sendRedirect("../sales_invoice/approve_invoice_dtl.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission);	
		}
	}//End Of Approve_Invoice() Method ...
		
}//End Of Class Frm_Sales_Invoice ...