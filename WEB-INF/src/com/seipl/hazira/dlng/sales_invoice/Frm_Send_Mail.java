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
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.*;
import javax.sql.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

@WebServlet("/servlet/Frm_Send_Mail")
public class Frm_Send_Mail extends HttpServlet
{ 
	public static Connection dbcon;
	
	public String servletName = "Frm_Send_Mail";
	public String methodName = "";
	public String option = "";
	public String url = "";
	public String form_name = "";
	public String msg = "";
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
			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);			
			if(ds != null)
			{
				dbcon = ds.getConnection();
			}
			else
			{
				////System.out.println("Data Source Not Found - Error In "+servletName+" Servlet !!!");
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
			
			if(option.equalsIgnoreCase("Send_Mail")) 
			{
				sendAll1(req,res); 
				Send_Mail(req,res);
			}
			
			if(option.equalsIgnoreCase("Form_402_Send_Mail")) 
			{
				sendAll1(req,res); 
				Send_Mail_402(req,res);
			}
			if(option.equalsIgnoreCase("Seller_Nom_Rpt_Send_Mail")) 
			{
				sendAll1(req,res); 
				Send_Mail_Seller_Nom_Rpt(req,res);
			}else if(option.equalsIgnoreCase("Advance_Advice_Rpt_Send_Mail")) {
				
				sendAll1(req,res); 
				Send_Mail_Advance_Advice_Rpt(req,res);
			}else if(option.equalsIgnoreCase("Transporter_Rpt_Send_Mail")) {
				
				sendAll1(req,res); 
				Send_Mail_Transporter_Rpt(req,res);
			}
			
			
		}		   
		catch(Exception e)
		{
			e.printStackTrace();
			url="../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=sales_invoice&formname="+form_name;
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
				}
			}
		}
		res.sendRedirect(url);
	}
	private void Send_Mail_Seller_Nom_Rpt(HttpServletRequest request, HttpServletResponse res)throws SQLException,IOException {
			
			methodName = "Send_Mail_Seller_Nom_Rpt()";
			
			HttpSession session = request.getSession();
			String pdfpath = request.getParameter("pdfpath")==null?"":request.getParameter("pdfpath");
			String pdf_nm = request.getParameter("attachment")==null?"":request.getParameter("attachment");
			String sub_dtl=request.getParameter("sub_dtl")==null?"":request.getParameter("sub_dtl");
			String email_to=request.getParameter("email_to")==null?"":request.getParameter("email_to");
			String email_body=request.getParameter("email_body")==null?"":request.getParameter("email_body"); 
			String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
			String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
			String email_CC = request.getParameter("email_cc")==null?"":request.getParameter("email_cc");
		
		try {	

			String txt1=email_body;
			String email_bCC="";
			
				int mail_status=sendSellerRptMailReminderToAllUsers(host,from_pwd,from_mail,email_to,txt1,email_bCC,sub_dtl,pdfpath,pdf_nm,request,email_CC);
				if(mail_status==1){
					
					msg="Email Sent Successfully for Daily Seller Nomination "+hlpl_inv_no;
				}else{
					msg = "Mail Not Sent!!";
				}
				
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
			
			url = "../reports/mail_seller_nom_rpt.jsp?msg="+msg+"&gas_date="+gas_date;
			System.out.println("url--"+url);
		} catch(Exception e) {
			dbcon.rollback();
			msg = "Mail Not Sent!!";
			url = "../reports/mail_seller_nom_rpt.jsp?msg="+msg+"&gas_date="+gas_date;
			e.printStackTrace();
		}
	}
	
	
	private void Send_Mail_Advance_Advice_Rpt(HttpServletRequest request, HttpServletResponse res)throws SQLException,IOException {
		
		methodName = "Send_Mail_Advance_Advice_Rpt()";
		
		HttpSession session = request.getSession();
		String pdfpath = request.getParameter("pdfpath")==null?"":request.getParameter("pdfpath");
		String pdf_nm = request.getParameter("attachment")==null?"":request.getParameter("attachment");
		String sub_dtl=request.getParameter("sub_dtl")==null?"":request.getParameter("sub_dtl");
		String email_to=request.getParameter("email_to")==null?"":request.getParameter("email_to");
		String email_body=request.getParameter("email_body")==null?"":request.getParameter("email_body"); 
//		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
//		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String email_CC = request.getParameter("email_cc")==null?"":request.getParameter("email_cc");
	
	try {	

		String txt1=email_body;
		String email_bCC="";
		
			int mail_status=sendSellerRptMailReminderToAllUsers(host,from_pwd,from_mail,email_to,txt1,email_bCC,sub_dtl,pdfpath,pdf_nm,request,email_CC);
			if(mail_status==1){
				
				msg="Email Sent Successfully for Advance Advice";
			}else{
				msg = "Mail Not Sent!!";
			}
			
		try
		{
			new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
		}
		catch(Exception infoLogger)
		{
			infoLogger.printStackTrace();
		}
		
		url = "../reports/mail_advance_advice.jsp?msg="+msg;
		System.out.println("url--"+url);
	} catch(Exception e) {
		dbcon.rollback();
		msg = "Mail Not Sent!!";
		url = "../reports/mail_advance_advice.jsp?msg="+msg;
		e.printStackTrace();
	}
}
private void Send_Mail_Transporter_Rpt(HttpServletRequest request, HttpServletResponse res)throws SQLException,IOException {
		
		methodName = "Send_Mail_Transporter_Rpt()";
		
		HttpSession session = request.getSession();
		String pdfpath = request.getParameter("pdfpath")==null?"":request.getParameter("pdfpath");
		String pdf_nm = request.getParameter("attachment")==null?"":request.getParameter("attachment");
		String sub_dtl=request.getParameter("sub_dtl")==null?"":request.getParameter("sub_dtl");
		String email_to=request.getParameter("email_to")==null?"":request.getParameter("email_to");
		String email_body=request.getParameter("email_body")==null?"":request.getParameter("email_body"); 
//		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String email_CC = request.getParameter("email_cc")==null?"":request.getParameter("email_cc");
	
	try {	

		String txt1=email_body;
		String email_bCC="";
		
			int mail_status=sendSellerRptMailReminderToAllUsers(host,from_pwd,from_mail,email_to,txt1,email_bCC,sub_dtl,pdfpath,pdf_nm,request,email_CC);
			if(mail_status==1){
				
				msg="Email Sent Successfully for Transporter Seller Nomination";
			}else{
				msg = "Mail Not Sent!!";
			}
			
		try
		{
			new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
		}
		catch(Exception infoLogger)
		{
			infoLogger.printStackTrace();
		}
		
		url = "../reports/mail_trans_daily_seller_nomination.jsp?msg="+msg+"&gas_date="+gas_date;
		System.out.println("url--"+url);
	} catch(Exception e) {
		dbcon.rollback();
		msg = "Mail Not Sent!!";
		url = "../reports/mail_trans_daily_seller_nomination.jsp?msg="+msg+"&gas_date="+gas_date;
		e.printStackTrace();
	}
}
	
	String host="";
    String from_mail="";
    String from_pwd="";
    String username="";
    String host1="";
    String dbline="";
    String port="";
    String context="";
    String bcc_mail="";
	private synchronized void sendAll1(HttpServletRequest request,HttpServletResponse res)throws SQLException,ServletException 
	{
		try
			{
			//stmt = dbcon.createStatement();
    	//stmt1 = dbcon.createStatement();
				String strline = "";
			    
			    File fsetup=new File(request.getRealPath("/sales_invoice/Setup.txt"));
				String mail_list_path=fsetup.getAbsolutePath();
				
				//System.out.println("mail_list_path--"+mail_list_path);
				FileInputStream f1 = new FileInputStream(mail_list_path);
				DataInputStream in = new DataInputStream(f1);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
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
						from_pwd = tmp[1].toString();
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
						password = tmp[1].toString();
					}
					if(strline.startsWith("iph"))
					{
						String  tmp[]=strline.split("iph:");
						host1 = tmp[1].toString();
						
					}
					if(strline.startsWith("port"))
					{
						String  tmp[]=strline.split("port:");
						port = tmp[1].toString();
					}
					if(strline.startsWith("context"))
					{
						String  tmp[]=strline.split("context:");
						context = tmp[1].toString();
					}
					if(strline.startsWith("bcc_mail"))
					{
						String  tmp[]=strline.split("bcc_mail:");
						bcc_mail = tmp[1].toString();
					}
				}
}	
	catch (Exception e) 
	{
		/*if(dbcon != null)
		  {
			dbcon.close();
			dbcon = null;
		  }*/
		e.printStackTrace();
		url="../hr/error_Page.jsp?msg="+e+"";
	}
		
}
	
	public void Send_Mail_402(HttpServletRequest request, HttpServletResponse response) throws Exception {
		methodName = "Send_Mail_402()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
		String pdfpath = request.getParameter("pdfpath")==null?"":request.getParameter("pdfpath");
		String pdf_nm = request.getParameter("attachment")==null?"":request.getParameter("attachment");
		String inv_type=request.getParameter("inv_type")==null?"":request.getParameter("inv_type");
		String sub_dtl=request.getParameter("sub_dtl")==null?"":request.getParameter("sub_dtl");
		String pdf_fullnm=request.getParameter("pdf_fullnm")==null?"":request.getParameter("pdf_fullnm");
		String email_to=request.getParameter("email_to")==null?"":request.getParameter("email_to");
		String inv_flag=request.getParameter("inv_flag")==null?"":request.getParameter("inv_flag");
		String email_body=request.getParameter("email_body")==null?"":request.getParameter("email_body"); 
		String email_finance=request.getParameter("email_finance")==null?"":request.getParameter("email_finance");
		//For Ltcora & Sales invoice
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String temp_email=request.getParameter("temp_email")==null?"":request.getParameter("temp_email");
		String contract_type=request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String email_CC_all=request.getParameter("email_cc")==null?"":request.getParameter("email_cc");
		String cr_dr_sign_flag=request.getParameter("cr_dr_sign_flag")==null?"":request.getParameter("cr_dr_sign_flag");
		//Fro Sug
		String cargo_no = request.getParameter("cargo_no")==null?"":request.getParameter("cargo_no");
		String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id");
		String index = request.getParameter("index")==null?"-1":request.getParameter("index");
		String selected_map_id = request.getParameter("selected_map_id")==null?"":request.getParameter("selected_map_id");
		String selected_customer_id = request.getParameter("selected_customer_id")==null?"0":request.getParameter("selected_customer_id");
		//
		//Fro advance
		String acc_hid= request.getParameter("acc_hid")==null?"":request.getParameter("acc_hid");
		String advance_type= request.getParameter("advance_type")==null?"":request.getParameter("advance_type");
		String customer_cd=request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String cn_no=request.getParameter("cn_no")==null?"":request.getParameter("cn_no");
		String cn_rev_no = request.getParameter("cn_rev_no")==null?"":request.getParameter("cn_rev_no");
		String customer_abbr=request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
		String sysdate="";
		String email_bCC=request.getParameter("mail_bcc")==null?"":request.getParameter("mail_bcc");
		String email_CC=request.getParameter("email_cc")==null?"":request.getParameter("email_cc");
		System.out.println("email_bCC----"+email_bCC);
		System.out.println("email_CC----"+email_CC);
		//
		try {
			String txt1=email_body;
			
			
			//Vector email_all=new Vector();
			String email_all="";
			if(!temp_email.equals("")){
				email_all=temp_email;
				
			}
			email_to=email_finance;
			
			queryString="SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM DUAL";
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				sysdate=rset.getString(1);
			}

//			email_bCC=email_all;
//			email_CC=email_CC_all;
				int mail_status=sendForm402MailReminderToAllUsers(host,from_pwd,from_mail,email_to,txt1,email_bCC,sub_dtl,pdfpath,pdf_nm,request,email_CC);
				if(mail_status==1){
					
					msg="Email Sent Successfully for Form-402 "+hlpl_inv_no;
				}else{
					msg = "Mail Not Sent!!";
				}
				
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
			
			url = "../sales_invoice/frm_form_402_mail.jsp?bill_cycle="+sysdate+"&form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg+"&month="+month+"&year="+year+"&inv_flag="+inv_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
//			System.out.println("url--"+url);
		} catch(Exception e) {
			dbcon.rollback();
			msg = "Mail Not Sent!!";
			//url = "../sales_invoice/frm_sign_pdf_mail.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg;
			url = "../sales_invoice/frm_form_402_mail.jsp?bill_cycle="+sysdate+"&msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			e.printStackTrace();
		}
	}
	
	public void Send_Mail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		methodName = "Send_Mail()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
		String pdfpath = request.getParameter("pdfpath")==null?"":request.getParameter("pdfpath");
		String pdf_nm = request.getParameter("pdf_nm")==null?"":request.getParameter("pdf_nm");
		String inv_type=request.getParameter("inv_type")==null?"":request.getParameter("inv_type");
		String sub_dtl=request.getParameter("sub_dtl")==null?"":request.getParameter("sub_dtl");
		String pdf_fullnm=request.getParameter("pdf_fullnm")==null?"":request.getParameter("pdf_fullnm");
		String email_to=request.getParameter("email_to")==null?"":request.getParameter("email_to");
		String inv_flag=request.getParameter("inv_flag")==null?"":request.getParameter("inv_flag");
		String email_body=request.getParameter("email_body")==null?"":request.getParameter("email_body"); 
		String email_finance=request.getParameter("email_finance")==null?"":request.getParameter("email_finance");
		//For Ltcora & Sales invoice
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String temp_email=request.getParameter("temp_email")==null?"":request.getParameter("temp_email");
		String contract_type=request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String email_CC_all=request.getParameter("email_cc")==null?"":request.getParameter("email_cc");
		String cr_dr_sign_flag=request.getParameter("cr_dr_sign_flag")==null?"":request.getParameter("cr_dr_sign_flag");
		//Fro Sug
		String cargo_no = request.getParameter("cargo_no")==null?"":request.getParameter("cargo_no");
		String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id");
		String index = request.getParameter("index")==null?"-1":request.getParameter("index");
		String selected_map_id = request.getParameter("selected_map_id")==null?"":request.getParameter("selected_map_id");
		String selected_customer_id = request.getParameter("selected_customer_id")==null?"0":request.getParameter("selected_customer_id");
		//
		//Fro advance
		String acc_hid= request.getParameter("acc_hid")==null?"":request.getParameter("acc_hid");
		String advance_type= request.getParameter("advance_type")==null?"":request.getParameter("advance_type");
		String customer_cd=request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String cn_no=request.getParameter("cn_no")==null?"":request.getParameter("cn_no");
		String cn_rev_no = request.getParameter("cn_rev_no")==null?"":request.getParameter("cn_rev_no");
		String customer_abbr=request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
		System.out.println("email_to*****"+email_to);
		try {
			String txt1=email_body;
			String email_CC="";
			String email_bCC="";
			//Vector email_all=new Vector();
			String email_all="";
			if(!temp_email.equals("")){
				email_all=temp_email;
				
			}
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L") ||  contract_type.equalsIgnoreCase("V")){
				/*if(inv_type.equals("O") || inv_type.equals("D") || cr_dr_sign_flag.equals("CO") || cr_dr_sign_flag.equals("SO") || cr_dr_sign_flag.equals("DEO") || cr_dr_sign_flag.equals("CD") || cr_dr_sign_flag.equals("CT") || cr_dr_sign_flag.equals("SD") || cr_dr_sign_flag.equals("DED") || cr_dr_sign_flag.equals("DET") ){
					email_to=email_to;
					email_bCC=email_all;
					email_CC=email_CC_all;
				}else if(inv_type.equals("T")){
					//email_to=email_all;
//					email_to=email_finance;
					email_to=email_to;
					email_bCC="";
					email_CC="";
				}*/
				
				/*Hiren_20210607*/
				email_bCC=email_all;
				email_CC=email_CC_all;
			}
				int mail_status=sendMailReminderToAllUsers(host,from_pwd,from_mail,email_to,txt1,email_bCC,sub_dtl,pdfpath,pdf_nm,request,email_CC);
				if(mail_status==1){
					String sysdate="";
					msg="Email Sent Successfully for "+hlpl_inv_no;
					queryString="SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM DUAL";
					rset=stmt.executeQuery(queryString);
					if(rset.next()){
						sysdate=rset.getString(1);
					}
					//String pdf_nm="";
					if(inv_flag.equals("SUG_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-4);
					}else if(inv_flag.equals("LTCORA_") || inv_flag.equals("LATEPAY_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-6);
					}else if(inv_flag.equals("SALES_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-6);
					}else if(inv_flag.equals("CREDIT_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-6);
					}else if(inv_flag.equals("DEBIT_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-6);
					}
					else if(inv_flag.equals("OTHER_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-6);
						pdf_fullnm=pdf_fullnm+".pdf";
					}else if(inv_flag.equals("Advance_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-6);
					}else if(inv_flag.equals("LOA_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-6);
					}else if(inv_flag.equals("SERVICE_")){
						pdf_fullnm=pdf_fullnm.substring(0,pdf_fullnm.length()-6);
					}
					if(!cr_dr_sign_flag.equals("")){
						String cr_dr_type="";
						if(cr_dr_sign_flag.equals("CO")){
							cr_dr_type="C";
						}else if(cr_dr_sign_flag.equals("SO")){
							cr_dr_type="S";
						}else if(cr_dr_sign_flag.equals("DEO")){
							cr_dr_type="d";
						}else if(cr_dr_sign_flag.equals("CD")){
							cr_dr_type="1";
						}else if(cr_dr_sign_flag.equals("CT")){
								cr_dr_type="2";	
						}else if(cr_dr_sign_flag.equals("SD")){
							cr_dr_type="1";
						}else if(cr_dr_sign_flag.equals("DED")){
							cr_dr_type="1";
						}else if(cr_dr_sign_flag.equals("DET")){
							cr_dr_type="2";
						}
						queryString="SELECT REMARKS FROM DLNG_INV_PDF_DTL WHERE PDF_INV_NM like '%"+pdf_fullnm+"%' AND INV_TYPE='"+cr_dr_type+"' AND PDF_SIGNED_FLAG='Y' ";
						rset=stmt.executeQuery(queryString);
						System.out.println("--queryString---"+queryString);
						if(rset.next()){
							String temp_rmk="";
							if(rset.getString(1)==null){
								temp_rmk=sysdate;
							}else{
								temp_rmk=rset.getString(1)==null?"":rset.getString(1)+","+sysdate;
							}
							String queryString1="UPDATE DLNG_INV_PDF_DTL SET MAIL_SENT_FLAG='Y' ,REMARKS='"+temp_rmk+"' ,MAIL_SENT_DT=to_date('"+sysdate+"','dd/mm/yyyy') WHERE PDF_INV_NM like '%"+pdf_fullnm+"%' AND INV_TYPE='"+cr_dr_type+"' ";
							stmt1.executeUpdate(queryString1);
							System.out.println("--query---"+queryString1);
							
							queryString1="SELECT mail_sent_dt FROM DLNG_INV_PDF_MAIL_DTL WHERE PDF_INV_NM like '%"+pdf_fullnm+"%' AND INV_TYPE='"+cr_dr_type+"' AND MAIL_SENT_DT=TO_DATE('"+sysdate+"','DD/MM/YYYY') ";
							rset=stmt.executeQuery(queryString1);
							//System.out.println("--queryString1---"+queryString1);
							if(rset.next()){
								String query1="UPDATE DLNG_INV_PDF_MAIL_DTL SET MAIL_SENT_DT=TO_DATE('"+sysdate+"','DD/MM/YYYY'),MAIL_SENT_TO='"+email_to+"' ,MAIL_SENT_CC='"+email_CC+"',MAIL_SENT_BCC='"+email_bCC+"',MAIL_SENT_BY_CD='"+user_cd+"' WHERE PDF_INV_NM like '%"+pdf_fullnm+"%' AND INV_TYPE='"+cr_dr_type+"' AND MAIL_SENT_DT=TO_DATE('"+sysdate+"','DD/MM/YYYY') ";
								stmt1.executeUpdate(query1);
							}else{
								
								String query2="INSERT INTO DLNG_INV_PDF_MAIL_DTL (PDF_INV_NM,INV_TYPE,MAIL_SENT_DT,MAIL_SENT_TO,MAIL_SENT_CC,MAIL_SENT_BCC,MAIL_SENT_BY_CD) VALUES ('"+pdf_fullnm+"','"+cr_dr_type+"',TO_DATE('"+sysdate+"','DD/MM/YYYY'),'"+email_to+"','"+email_CC+"','"+email_bCC+"','"+user_cd+"')";
								stmt1.executeUpdate(query2);
								System.out.println("query2--"+query2);
							}
							
						}
					}else{
						queryString="SELECT REMARKS FROM DLNG_INV_PDF_DTL WHERE PDF_INV_NM like '%"+pdf_fullnm+"%' AND INV_TYPE='"+inv_type+"' AND PDF_SIGNED_FLAG='Y' ";
						rset=stmt.executeQuery(queryString);
						System.out.println("--queryString---"+queryString);
						if(rset.next()){
							String temp_rmk="";
							if(rset.getString(1)==null){
								temp_rmk=sysdate;
							}else{
								temp_rmk=rset.getString(1)==null?"":rset.getString(1)+","+sysdate;
							}
							String queryString1="UPDATE DLNG_INV_PDF_DTL SET MAIL_SENT_FLAG='Y' ,REMARKS='"+temp_rmk+"' ,MAIL_SENT_DT=to_date('"+sysdate+"','dd/mm/yyyy') WHERE PDF_INV_NM like '%"+pdf_fullnm+"%' AND INV_TYPE='"+inv_type+"' ";
							stmt1.executeUpdate(queryString1);
							System.out.println("--query---"+queryString1);
							
							queryString1="SELECT mail_sent_dt FROM DLNG_INV_PDF_MAIL_DTL WHERE PDF_INV_NM like '%"+pdf_fullnm+"%' AND INV_TYPE='"+inv_type+"' AND MAIL_SENT_DT=TO_DATE('"+sysdate+"','DD/MM/YYYY') ";
							rset=stmt.executeQuery(queryString1);
							//System.out.println("--queryString1---"+queryString1);
							if(rset.next()){
								String query1="UPDATE DLNG_INV_PDF_MAIL_DTL SET MAIL_SENT_DT=TO_DATE('"+sysdate+"','DD/MM/YYYY'),MAIL_SENT_TO='"+email_to+"' ,MAIL_SENT_CC='"+email_CC+"',MAIL_SENT_BCC='"+email_bCC+"',MAIL_SENT_BY_CD='"+user_cd+"' WHERE PDF_INV_NM like '%"+pdf_fullnm+"%' AND INV_TYPE='"+inv_type+"' AND MAIL_SENT_DT=TO_DATE('"+sysdate+"','DD/MM/YYYY') ";
								stmt1.executeUpdate(query1);
							}else{
								
								String query2="INSERT INTO DLNG_INV_PDF_MAIL_DTL (PDF_INV_NM,INV_TYPE,MAIL_SENT_DT,MAIL_SENT_TO,MAIL_SENT_CC,MAIL_SENT_BCC,MAIL_SENT_BY_CD) VALUES ('"+pdf_fullnm+"','"+inv_type+"',TO_DATE('"+sysdate+"','DD/MM/YYYY'),'"+email_to+"','"+email_CC+"','"+email_bCC+"','"+user_cd+"')";
								stmt1.executeUpdate(query2);
								System.out.println("query2--"+query2);
							}
							
						}
					}
					dbcon.commit();
				}else{
					msg = "Mail Not Sent!!";
				}
				
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
			
			if(inv_flag.equals("SUG_")){
				url = "../sales_invoice/frm_sign_pdf_mail.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg+"&month="+month+"&year="+year+"&selected_map_id="+selected_map_id+"&selected_customer_id="+selected_customer_id+"&index="+index+"&visible_flag=Y&mapping_id="+mapping_id+"&cargo_no="+cargo_no+"&inv_flag="+inv_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			}else if(inv_flag.equalsIgnoreCase("LTCORA_") || inv_flag.equalsIgnoreCase("SALES_")|| inv_flag.equalsIgnoreCase("LOA_") || inv_flag.equalsIgnoreCase("CREDIT_") || inv_flag.equalsIgnoreCase("DEBIT_")){
				url = "../sales_invoice/frm_sign_pdf_mail.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&inv_flag="+inv_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			}else if(inv_flag.equalsIgnoreCase("OTHER_")){
				url = "../sales_invoice/frm_sign_pdf_mail.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg+"&month="+month+"&year="+year+"&contract_type="+contract_type+"&inv_flag="+inv_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			}else if(inv_flag.equalsIgnoreCase("Advance_")){
				url = "../sales_invoice/frm_sign_pdf_mail.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg+"&month="+month+"&year="+year+"&contract_type="+contract_type+"&inv_flag="+inv_flag+"&advance_type="+advance_type+"&acc_hid="+acc_hid+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+
						"&fgsa_rev_no="+fgsa_rev_no+"&cn_rev_no="+cn_rev_no+"&cn_no="+cn_no+"&mapping_id="+mapping_id+"&customer_abbr="+customer_abbr+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			}else if(inv_flag.equalsIgnoreCase("LATEPAY_")){
				url = "../sales_invoice/frm_sign_pdf_mail.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg+"&month="+month+"&year="+year+"&inv_flag="+inv_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			}else if(inv_flag.equalsIgnoreCase("SERVICE_")){
				url = "../sales_invoice/frm_sign_pdf_mail.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg+"&month="+month+"&year="+year+"&inv_flag="+inv_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			}
			System.out.println("url--"+url);
		} catch(Exception e) {
			dbcon.rollback();
			msg = "Mail Not Sent!!";
			//url = "../sales_invoice/frm_sign_pdf_mail.jsp?form_nm="+form_nm+"&form_id="+form_id+"&msg="+msg;
			url = "../sales_invoice/frm_sign_pdf_mail.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			e.printStackTrace();
		}
		
	}
	public synchronized int sendMailReminderToAllUsers(String host1,String pwd,String email_from,String email_to,String txt,String bcc,String sub_dtl,String att,String filename,HttpServletRequest request,String cc)
			throws MessagingException ,AuthenticationFailedException
			{
				  String host =host1;
				try
				{
					
					if(true)
					{
						  String to  = email_to;
					      String from =email_from;
					      final String username = email_from;
					      final String password = pwd; 
					      final String bcc1 = bcc;
					      Properties props = new Properties();
					      props.put("mail.smtp.auth", "true");
					      props.put("mail.smtp.host", host);
//					      System.out.println("--to--"+to);
//					      System.out.println("--from--"+from);
					      Session session = Session.getInstance(props,new javax.mail.Authenticator() 
					      {
					            protected PasswordAuthentication getPasswordAuthentication() 
					            {
					               return new PasswordAuthentication(username, password);
					            }
					      });
			    		    	  		    	  
				         Message message = new MimeMessage(session);
				         message.setFrom(new InternetAddress(from));
				         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
				         message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
				         message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bcc1));
				         message.setSubject(sub_dtl);
				         message.setSentDate(new Date());//RG20190706 
				         MimeMultipart multipart = new MimeMultipart();
			        	 BodyPart messageBodyPart = new MimeBodyPart();
				         String htmlText = "";
				         htmlText=txt;
				         messageBodyPart.setContent(htmlText, "text/html");
				         multipart.addBodyPart(messageBodyPart);
				         ///For attachment//
						messageBodyPart = new MimeBodyPart();
//						String path=request.getRealPath("unsigned_pdf\\signed");
//						String path_pdf=path+"\\"+filename;
						String path=request.getRealPath("unsigned_pdf//signed"); //RG20190103 for linux server
						String path_pdf=path+"//"+filename;
						FileDataSource fds = new FileDataSource(path_pdf);
						messageBodyPart.setDataHandler(new DataHandler(fds));
						messageBodyPart.setFileName(filename);
						multipart.addBodyPart(messageBodyPart);
				         //
				         message.setContent(multipart);
					       Transport.send(message);
				}
					return 1;
				}
				catch (AuthenticationFailedException e) {
					System.out.println("AuthenticationFailedException In send mail 1 = "+e.getMessage());
					e.printStackTrace();
					return 2;
			     } 
				catch(Exception e)
				{
					e.printStackTrace();
					return 3;
				}
				finally
				{}
			}
	
	public synchronized int sendForm402MailReminderToAllUsers(String host1,String pwd,String email_from,String email_to,String txt,String bcc,String sub_dtl,String att,String filename,HttpServletRequest request,String cc)
			throws MessagingException ,AuthenticationFailedException
			{
				
				  String host =host1;
				try
				{
					
					if(true)
					{
						  String to  = email_to;
					      String from =email_from;
					      final String username = email_from;
					      final String password = pwd; 
					      final String bcc1 = bcc;
					      Properties props = new Properties();
					      props.put("mail.smtp.auth", "true");
					      props.put("mail.smtp.host", host);
//					      System.out.println("--to--"+to);
//					      System.out.println("--from--"+from);
					      Session session = Session.getInstance(props,new javax.mail.Authenticator() 
					      {
					            protected PasswordAuthentication getPasswordAuthentication() 
					            {
					               return new PasswordAuthentication(username, password);
					            }
					      });
			    		    	  		    	  
				         Message message = new MimeMessage(session);
				         message.setFrom(new InternetAddress(from));
				         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
				         message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
				         message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bcc1));
				         message.setSubject(sub_dtl);
				         message.setSentDate(new Date());//RG20190706 
				         MimeMultipart multipart = new MimeMultipart();
			        	 BodyPart messageBodyPart = new MimeBodyPart();
				         String htmlText = "";
				         htmlText=txt;
				         messageBodyPart.setContent(htmlText, "text/html");
				         multipart.addBodyPart(messageBodyPart);
				         ///For attachment//
						messageBodyPart = new MimeBodyPart();
//						String path=request.getRealPath("unsigned_pdf\\signed");
//						String path_pdf=path+"\\"+filename;
						String path=request.getRealPath("Form402_Xls"); //RG20190103 for linux server
						String path_pdf=path+"//"+filename;
						System.out.println("path_pdf****"+path_pdf);
						FileDataSource fds = new FileDataSource(path_pdf);
						messageBodyPart.setDataHandler(new DataHandler(fds));
						messageBodyPart.setFileName(filename);
						multipart.addBodyPart(messageBodyPart);
				         //
				         message.setContent(multipart);
					       Transport.send(message);
				}
					return 1;
				}
				catch (AuthenticationFailedException e) {
					System.out.println("AuthenticationFailedException In send mail 1 = "+e.getMessage());
					e.printStackTrace();
					return 2;
			     } 
				catch(Exception e)
				{
					e.printStackTrace();
					return 3;
				}
				finally
				{}
			}
	
	public synchronized int sendSellerRptMailReminderToAllUsers(String host1,String pwd,String email_from,String email_to,String txt,String bcc,String sub_dtl,String att,String filename,HttpServletRequest request,String cc)
			throws MessagingException ,AuthenticationFailedException
			{
				
				  String host =host1;
				try
				{
					
					if(true)
					{
						  String to  = email_to;
					      String from =email_from;
					      final String username = email_from;
					      final String password = pwd; 
					      final String bcc1 = bcc;
					      Properties props = new Properties();
					      props.put("mail.smtp.auth", "true");
					      props.put("mail.smtp.host", host);
//					      System.out.println("--to--"+to);
//					      System.out.println("--from--"+from);
					      Session session = Session.getInstance(props,new javax.mail.Authenticator() 
					      {
					            protected PasswordAuthentication getPasswordAuthentication() 
					            {
					               return new PasswordAuthentication(username, password);
					            }
					      });
			    		    	  		    	  
				         Message message = new MimeMessage(session);
				         message.setFrom(new InternetAddress(from));
				         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
				         message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
				         message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(bcc1));
				         message.setSubject(sub_dtl);
				         message.setSentDate(new Date());//RG20190706 
				         MimeMultipart multipart = new MimeMultipart();
			        	 BodyPart messageBodyPart = new MimeBodyPart();
				         String htmlText = "";
				         htmlText=txt;
				         messageBodyPart.setContent(htmlText, "text/html");
				         multipart.addBodyPart(messageBodyPart);
				         ///For attachment//
						messageBodyPart = new MimeBodyPart();
//						String path=request.getRealPath("unsigned_pdf\\signed");
//						String path_pdf=path+"\\"+filename;
						String path = "";
						if(option.equalsIgnoreCase("Seller_Nom_Rpt_Send_Mail") || option.equalsIgnoreCase("Transporter_Rpt_Send_Mail")) 
						{
							path=request.getRealPath("/pdf_reports/daily_nomination/pdf_files");
						}else if(option.equalsIgnoreCase("Advance_Advice_Rpt_Send_Mail")) {
							
							path=request.getRealPath("/pdf_reports/advance_advice/pdf_files");
						}
						
						
						System.out.println("path*********"+path);
						String path_pdf=path+"//"+filename;
//						System.out.println("path_pdf****"+path_pdf);
						FileDataSource fds = new FileDataSource(path_pdf);
						messageBodyPart.setDataHandler(new DataHandler(fds));
						messageBodyPart.setFileName(filename);
						multipart.addBodyPart(messageBodyPart);
				         //
				         message.setContent(multipart);
					       Transport.send(message);
				}
					return 1;
				}
				catch (AuthenticationFailedException e) {
					System.out.println("AuthenticationFailedException In send mail 1 = "+e.getMessage());
					e.printStackTrace();
					return 2;
			     } 
				catch(Exception e)
				{
					e.printStackTrace();
					return 3;
				}
				finally
				{}
			}
}//End Of Class Frm_Sales_Invoice ...