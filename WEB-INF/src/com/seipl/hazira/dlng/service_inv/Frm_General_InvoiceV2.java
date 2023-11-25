package com.seipl.hazira.dlng.service_inv;


import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.naming.*;
import javax.sql.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

@WebServlet("/servlet/Frm_General_InvoiceV2")
public class Frm_General_InvoiceV2 extends HttpServlet
{ 
	public static Connection dbcon;
	
	public String servletName = "Frm_Sales_Invoice";
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
				System.out.println("Data Source Not Found - Error In "+servletName+" Servlet !!!");
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
			System.out.println("option*****"+option);
			if(option.equalsIgnoreCase("Submit_Other_Invoice"))
			{
				Submit_Other_Invoice(req,res);
				SubmitGSTInvoiceXZ(req,res); //SB20170805
			}
			else if(option.equalsIgnoreCase("Submit_Other_Invoice_Y"))
			{
				Submit_Other_Invoice_Y(req,res); 
			}
			else if(option.equalsIgnoreCase("Submit_Other_Invoice_1"))
			{
				Submit_Other_Invoice_1(req,res); 
			}
			else if(option.equalsIgnoreCase("Submit_Other_Invoice_2"))
			{
				Submit_Other_Invoice_2(req,res); 
			}
			else if(option.equalsIgnoreCase("Submit_Other_Invoice_Z")) //SB20171108
			{
			//SB20180207	Submit_Other_Invoice_Z(req,res); //For FMS8 database
				SubmitGSTInvoiceXZ(req,res); //SB20170805
			}
			else if(option.equalsIgnoreCase("Submit_Check_Invoice"))
			{
			//SB20180207	Submit_Check_Invoice(req,res); 
				Submit_Check_InvoiceGST_XZ(req,res); //SB20180207
			}
			else if(option.equalsIgnoreCase("SUBMIT_DR_CR_REMARK"))
			{
				SUBMIT_DR_CR_REMARK(req,res); 
			}
			else if(option.equalsIgnoreCase("SUBMIT_DR_CR_NOTE"))
			{
				SUBMIT_DR_CR_NOTE(req,res); 
			}
			
			
			
		}		   
		catch(Exception e)
		{
			System.out.println("Exception In "+servletName+" - (doPost())-(CONNECTION) : "+e.getMessage());
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
					System.out.println("rset is not close "+e.getMessage());
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
					System.out.println("rset1 is not close "+e.getMessage());
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
					System.out.println("rset2 is not close "+e.getMessage());
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
					System.out.println("rset3 is not close "+e.getMessage());
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
					System.out.println("rset4 is not close "+e.getMessage());
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
					System.out.println("stmt is not close "+e.getMessage());
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
					System.out.println("stmt1 is not close "+e.getMessage());
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
					System.out.println("stmt2 is not close "+e.getMessage());
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
					System.out.println("stmt3 is not close "+e.getMessage());
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
					System.out.println("stmt4 is not close "+e.getMessage());
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
					System.out.println("dbcon is not close "+e.getMessage());
				}
			}
		}
		//System.out.println("url = "+url);
		res.sendRedirect(url);
	}
	
	public void Submit_Other_Invoice(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "submitBillingDetails()";
		form_name = "frm_other_invoices.jsp";
		String user_cd=request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String inv_dt=request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String pay_dt=request.getParameter("pay_dt")==null?"":request.getParameter("pay_dt");
		String t2=request.getParameter("t2")==null?"":request.getParameter("t2");  //Cust name
		String address=request.getParameter("address")==null?"":request.getParameter("address"); 
		String cust_stcd=request.getParameter("cust_stcd")==null?"":request.getParameter("cust_stcd");
		String sup_stcd=request.getParameter("sup_stcd")==null?"":request.getParameter("sup_stcd");
		String supp_cd_hid=request.getParameter("supp_cd_hid")==null?"":request.getParameter("supp_cd_hid");
		
		
		String t3=request.getParameter("t3")==null?"":request.getParameter("t3");
		String curr=request.getParameter("curr")==null?"":request.getParameter("curr");
		String amt=request.getParameter("amt")==null?"":request.getParameter("amt");
		String inv_type=request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String tax_sz=request.getParameter("tax_sz")==null?"":request.getParameter("tax_sz");
		String tax_amt=request.getParameter("tax_amt")==null?"":request.getParameter("tax_amt");
		String gross_amt=request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
		String CUST_CD11=request.getParameter("CUST_CD11")==null?"0":request.getParameter("CUST_CD11");
		String year=request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month=request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String net_inr=request.getParameter("net_inr")==null?"0":request.getParameter("net_inr");
		String sac_cd=request.getParameter("sac_cd")==null?"":request.getParameter("sac_cd");
		String sac_desc=request.getParameter("sac_desc")==null?"":request.getParameter("sac_desc");
		//String state=request.getParameter("state")==null?"":request.getParameter("state");
		String cust_pan_no=request.getParameter("cust_pan_no")==null?"":request.getParameter("cust_pan_no");
		String INVOICE_SEQ_NO=request.getParameter("INVOICE_SEQ_NO")==null?"":request.getParameter("INVOICE_SEQ_NO");
		String New_GST_INVOICE_SEQ_NO=request.getParameter("newinv_no")==null?"":request.getParameter("newinv_no");
		String cust_seq_no1=request.getParameter("cust_seq_no1")==null?"":request.getParameter("cust_seq_no1");
//		String Fin_yr=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String bill_end_dt=request.getParameter("bill_end_dt")==null?"":request.getParameter("bill_end_dt");
		String bill_start_dt=request.getParameter("bill_start_dt")==null?"":request.getParameter("bill_start_dt");
		String t4=request.getParameter("t4")==null?"":request.getParameter("t4");
		String t5=request.getParameter("t5")==null?"":request.getParameter("t5");
		String city=request.getParameter("city")==null?"":request.getParameter("city");
		String pin_code=request.getParameter("pin_code")==null?"":request.getParameter("pin_code");
		String country=request.getParameter("country")==null?"":request.getParameter("country");
		String hlpl_seq_no=request.getParameter("hlpl_seq_no1")==null?"":request.getParameter("hlpl_seq_no1");
		String seq_no=request.getParameter("seq_no1")==null?"":request.getParameter("seq_no1");
		String fin_yr_kk=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String cust_sgtin_no=request.getParameter("cust_sgtin_no")==null?"":request.getParameter("cust_sgtin_no");
		String pacer_no=request.getParameter("pacer_no")==null?"":request.getParameter("pacer_no");
		String Vendor_no=request.getParameter("Vendor")==null?"":request.getParameter("Vendor");
		String exchg_rate=request.getParameter("exchg_rate")==null?"":request.getParameter("exchg_rate");
		String exchg_dt=request.getParameter("exchg_dt")==null?"":request.getParameter("exchg_dt");
		//String CUST_CD11=request.getParameter("CUST_CD11")==null?"":request.getParameter("CUST_CD11");
		
		
		String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
		
		com.seipl.hazira.dlng.util.escapeSingleQuotes es=new com.seipl.hazira.dlng.util.escapeSingleQuotes();
		t2=es.replaceSingleQuotes(t2);
		sac_desc=es.replaceSingleQuotes(sac_desc);
		t3=es.replaceSingleQuotes(t3);
		t4=es.replaceSingleQuotes(t4);
		t5=es.replaceSingleQuotes(t5);
		city=es.replaceSingleQuotes(city);
		cust_pan_no=es.replaceSingleQuotes(cust_pan_no);
		address=es.replaceSingleQuotes(address);
		
		Vector sz=new Vector();
		Vector tax_nm=new Vector();
		Vector tax_fact=new Vector();
		String cgst="";String igst="";String sgst="";
		
	
		
		try
		{
			System.out.println("INVOICE_SEQ_NO=="+INVOICE_SEQ_NO+"=New_GST_INVOICE_SEQ_NO ="+New_GST_INVOICE_SEQ_NO);
			System.out.println("hlpl_seq_no=="+hlpl_seq_no+"=new seq_no="+seq_no);
			int count=0;
			HttpSession session = request.getSession();
			if(flag.equalsIgnoreCase("Modify")) {
				String queryString="UPDATE FMS7_INVOICE_MST SET INVOICE_DT=TO_DATE('"+inv_dt+"','DD/MM/YYYY'),"
						+ "DUE_DT=sysdate,TAX_AMT_INR='"+tax_amt+"',"
						+ "NET_AMT_INR='"+net_inr+"' ,GROSS_AMT_INR='"+gross_amt+"',gross_amt_usd='"+amt+"', "
						+ "INV_CUR_FLAG='"+curr+"',EMP_CD='"+user_cd+"',exchg_rate_dt=to_date('"+exchg_dt+"','dd/mm/yyyy'),exchg_rate_value='"+exchg_rate+"'  where financial_year='"+fin_yr_kk+"'  "
						+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+hlpl_seq_no+"' "
						+ "and new_inv_seq_no='"+seq_no+"' "
						+ "AND SUPPLIER_CD='"+supp_cd_hid+"' "; //ADDED BY RS04082017 FOR PRIMARY KEY.....
			System.out.println("hsdgf---"+queryString);
				stmt.executeUpdate(queryString);
			}else{
				String queryString1="SELECT COUNT(*) FROM FMS7_INVOICE_MST WHERE financial_year='"+fin_yr_kk+"'"
								+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+INVOICE_SEQ_NO+"' "
								+ "and new_inv_seq_no='"+New_GST_INVOICE_SEQ_NO+"' "
								+ "AND SUPPLIER_CD='"+supp_cd_hid+"' "; //ADDED BY RS04082017 FOR PRIMARY KEY.....
				System.out.println("queryString1--"+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					count=rset1.getInt(1);
				}
				if(count==0) {
			String queryString="INSERT INTO FMS7_INVOICE_MST (SN_NO,FGSA_NO,SN_REV_NO,FGSA_REV_NO,"
					+ "CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,HLPL_INV_SEQ_NO,"
					+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR"
					+ ",NET_AMT_INR,FLAG,INVOICE_DT,DUE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,"
					+ "SUPPLIER_CD,NEW_INV_SEQ_NO,EMP_CD,ENT_DT,INV_CUR_FLAG,EXCHG_RATE_DT,EXCHG_RATE_VALUE)"
					+ " VALUES ('0','0','0','0','"+CUST_CD11+"','0','"+inv_type+"',"
							+ "to_date('"+bill_end_dt+"','dd/mm/yyyy'),'"+INVOICE_SEQ_NO+"',"
							+ "'"+fin_yr_kk+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),"
							+ "'"+cust_seq_no1+"','"+gross_amt+"','"+tax_amt+"','"+net_inr+"',"
							+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),sysdate,"
							+ "'0','0','"+amt+"','"+supp_cd_hid+"','"+New_GST_INVOICE_SEQ_NO+"','"+user_cd+"',sysdate,'"+curr+"',to_date('"+exchg_dt+"','dd/mm/yyyy'),'"+exchg_rate+"')";
			stmt.executeUpdate(queryString);
			System.out.println("querey----"+queryString);
			}else {
				String queryString2="Delete FROM FMS7_INVOICE_MST WHERE financial_year='"+fin_yr_kk+"'"
						+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+INVOICE_SEQ_NO+"' "
						+ "and new_inv_seq_no='"+New_GST_INVOICE_SEQ_NO+"' "
						+ "AND SUPPLIER_CD='"+supp_cd_hid+"' ";
				System.out.println("querey----"+queryString2);
					stmt1.executeUpdate(queryString2);
					
					String queryString="INSERT INTO FMS7_INVOICE_MST (SN_NO,FGSA_NO,SN_REV_NO,FGSA_REV_NO,"
							+ "CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,HLPL_INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR"
							+ ",NET_AMT_INR,FLAG,INVOICE_DT,DUE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,"
							+ "SUPPLIER_CD,NEW_INV_SEQ_NO,EMP_CD,ENT_DT,INV_CUR_FLAG,EXCHG_RATE_DT,EXCHG_RATE_VALUE)"
							+ " VALUES ('0','0','0','0','"+CUST_CD11+"','0','"+inv_type+"',"
									+ "to_date('"+bill_end_dt+"','dd/mm/yyyy'),'"+INVOICE_SEQ_NO+"',"
									+ "'"+fin_yr_kk+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),"
									+ "'"+cust_seq_no1+"','"+gross_amt+"','"+tax_amt+"','"+net_inr+"',"
									+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),sysdate,"
									+ "'0','0','"+amt+"','"+supp_cd_hid+"','"+New_GST_INVOICE_SEQ_NO+"','"+user_cd+"',sysdate,'"+curr+"',to_date('"+exchg_dt+"','dd/mm/yyyy'),'"+exchg_rate+"')";
					stmt.executeUpdate(queryString);
					System.out.println("querey----"+queryString);
					
			}
				}
			
			
			String tt="";String am="";String nm="";String fact="";String tax_cd="";
			if(tax_sz.equalsIgnoreCase("2")) {
			for(int k=0;k<Integer.parseInt(tax_sz);k++) {
				String tax_str=request.getParameter("tax_str"+k)==null?"":request.getParameter("tax_str"+k);
				String tax_amount=request.getParameter("tax_amts"+k)==null?"":request.getParameter("tax_amts"+k);
				tax_cd="C";
				//System.out.println("--tax_amts--"+tax_amount);
				if(k==0){
					tt="CGST-"+tax_str+"%";
					nm="CGST";
					fact=tax_str;
					cgst=tax_str;
					
				}else{
					tt+="SGST-"+tax_str+"%";
					nm="SGST";
					fact=tax_str;
					sgst=tax_str;
				}
				sz.add(tt);
				tax_nm.add(nm);
				tax_fact.add(fact);
				
			}
			}else{
				tax_cd="I";
				String tax_str=request.getParameter("tax_str0")==null?"":request.getParameter("tax_str0");
				String tax_amts=request.getParameter("tax_amts")==null?"":request.getParameter("tax_amts");
				tt="IGST-"+tax_str+"%";
				nm="IGST";
				igst=tax_str;
				fact=tax_str;
				sz.add(tt);
			}
			//for(int i=0;i<sz.size();i++) {
			if(flag.equalsIgnoreCase("Modify")) {
				String queryString="UPDATE FMS8_OTHER_INVOICE_DTL SET tax_cd='"+tax_cd+"'"
						+ ",tax_details='"+tt+"',item_description='"+t3+"',rate_cgst='"+cgst+"',"
						+ "rate_sgst='"+sgst+"',rate_igst='"+igst+"',remark='"+t4+"',remark1='"+t5+"',pacer_no='"+pacer_no+"'"
						+ ",VENDOR_SUPP_INV_REF='"+Vendor_no+"' "+
						"where financial_year='"+fin_yr_kk+"'"
						+ " and contract_type='"+inv_type+"' and inv_seq_no='"+hlpl_seq_no+"' "
						+ "AND SUPPLIER_CD='"+supp_cd_hid+"' ";
				System.out.println("hsdgf-sd--"+queryString);
				stmt.executeUpdate(queryString);
				
			}else{
				String queryString1="SELECT COUNT(*) FROM FMS8_OTHER_INVOICE_DTL WHERE financial_year='"+fin_yr_kk+"'"
						+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' "
						+ "AND SUPPLIER_CD='"+supp_cd_hid+"'";
					rset1=stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						count=rset1.getInt(1);
					}
					if(count==0) {
					String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,tax_details,eff_dt,SAC_DESCRIPTION,SAC_CODE,REMARK,"
							+ "REMARK1,CUSTOMER_NM,CUSTOMER_ADDR,CUSTOMER_PAN_NO,CUSTOMER_STATE_CD,"
							+ "SUPPLIER_STATE_CD,RATE_CGST,RATE_IGST,RATE_SGST,ITEM_DESCRIPTION,CUST_CITY,CUST_PIN_CODE"
							+ ",TAX_CD,cust_gstin_no,VENDOR_SUPP_INV_REF,pacer_no,CUSTOMER_COUNTRY_NM,SUPPLIER_CD)"
							+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
							+ "'"+fin_yr_kk+"','"+tt+"',"
							+ "to_date('"+inv_dt+"','dd/mm/yyyy'),'"+sac_desc+"',"
							+ "'"+sac_cd+"','"+t4+"','"+t5+"','"+t2+"','"+address+"','"+cust_pan_no+"',"
							+ "'"+cust_stcd+"','"+sup_stcd+"','"+cgst+"','"+igst+"','"+sgst+"','"+t3+"',"
							+ "'"+city+"','"+pin_code+"','"+tax_cd+"','"+cust_sgtin_no+"','"+Vendor_no+"','"+pacer_no+"','"+country+"','"+supp_cd_hid+"')";
					System.out.println("---------"+queryString3);
					stmt.executeUpdate(queryString3);
			}else{
				String queryString2="Delete FROM FMS8_other_invoice_dtl WHERE financial_year='"+fin_yr_kk+"'"
						+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' "
						+ "AND SUPPLIER_CD='"+supp_cd_hid+"' ";
							
					stmt1.executeUpdate(queryString2);
					System.out.println("---------"+queryString2);
					
					
					String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,tax_details,eff_dt,SAC_DESCRIPTION,SAC_CODE,REMARK,"
							+ "REMARK1,CUSTOMER_NM,CUSTOMER_ADDR,CUSTOMER_PAN_NO,CUSTOMER_STATE_CD,"
							+ "SUPPLIER_STATE_CD,RATE_CGST,RATE_IGST,RATE_SGST,ITEM_DESCRIPTION,CUST_CITY,CUST_PIN_CODE"
							+ ",TAX_CD,cust_gstin_no,VENDOR_SUPP_INV_REF,pacer_no,CUSTOMER_COUNTRY_NM,SUPPLIER_CD)"
							+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
							+ "'"+fin_yr_kk+"','"+tt+"',"
							+ "to_date('"+inv_dt+"','dd/mm/yyyy'),'"+sac_desc+"',"
							+ "'"+sac_cd+"','"+t4+"','"+t5+"','"+t2+"','"+address+"','"+cust_pan_no+"',"
							+ "'"+cust_stcd+"','"+sup_stcd+"','"+cgst+"','"+igst+"','"+sgst+"','"+t3+"',"
							+ "'"+city+"','"+pin_code+"','"+tax_cd+"','"+cust_sgtin_no+"','"+Vendor_no+"','"+pacer_no+"','"+country+"','"+supp_cd_hid+"')";
					System.out.println("---------"+queryString3);
					stmt.executeUpdate(queryString3);
			}
			}
			
			if(flag.equals("Modify")) {
				if(supp_cd_hid.equals("1")) {
					msg = "Billing Details Of Invoice No- RCL"+seq_no+"  Has Been Modified Successfully !!!";
				} else {
					msg = "Billing Details Of Invoice No- RCP"+seq_no+"  Has Been Modified Successfully !!!";
				}
			} else {
				if(supp_cd_hid.equals("1")) {
					msg = "Billing Details Of Invoice No- RCL"+New_GST_INVOICE_SEQ_NO+"  Has Been Submitted Successfully !!!";
				} else {
					msg = "Billing Details Of Invoice No- RCP"+New_GST_INVOICE_SEQ_NO+"  Has Been Submitted Successfully !!!";
				}
			}
			
			
			dbcon.commit();
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
//				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			msg = "Invoice Details Submission Failed !!!";
			e.printStackTrace();
			dbcon.rollback();
//			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
	public void Submit_Check_Invoice(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "Submit_Check_Invoice()";
		form_name = "frm_other_invoices.jsp";
		String user_cd=request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String inv_type_hid=request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String Fin_yr_hid=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String hlpl_seq_no=request.getParameter("hlpl_seq_no")==null?"":request.getParameter("hlpl_seq_no");
		String seq_no=request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
		String year=request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month=request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
		String rd=request.getParameter("rd")==null?"":request.getParameter("rd");
//		String seq_no=request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
		String supp_cd = request.getParameter("supp_cd")==null?"":request.getParameter("supp_cd");
		
		try
		{
			HttpSession session = request.getSession();
			//if(rd.equalsIgnoreCase("Y")) {
				if(flag.equalsIgnoreCase("C")) {
					String queryString="UPDATE FMS7_INVOICE_MST SET CHECKED_FLAG='"+rd+"',CHECKED_BY='"+user_cd+"',"
							+ "checked_dt=sysdate WHERE "
							+ "HLPL_INV_SEQ_NO='"+hlpl_seq_no+"' and financial_year='"+Fin_yr_hid+"' and contract_type='"+inv_type_hid+"' "
							+ "and  new_inv_seq_no='"+seq_no+"' and supplier_cd='"+supp_cd+"' ";
					System.out.println("----->>>>"+queryString);
					stmt.executeUpdate(queryString);
					msg = "Invoice Checking Process  Has Been Successfully Completed !!!";
				}else if(flag.equalsIgnoreCase("A")) {
					if(rd.equalsIgnoreCase("N")) {
					queryString1 = "UPDATE FMS7_INVOICE_MST SET " +
							   "checked_flag=null, checked_by=null, checked_dt=null, " +
							   "authorized_flag=null, authorized_by=null, authorized_dt=null, " +
							   "approved_flag='"+rd+"', " +
							   "approved_by='"+user_cd+"', approved_dt=sysdate " +
							   "WHERE financial_year='"+Fin_yr_hid+"' AND " +
				  			   "hlpl_inv_seq_no="+hlpl_seq_no+" AND " +
				  			   "contract_type='"+inv_type_hid+"' and supplier_cd='"+supp_cd+"' ";
					stmt.executeUpdate(queryString1);
					}else{
					
					String queryString="UPDATE FMS7_INVOICE_MST SET Approved_flag='"+rd+"',approved_by='"+user_cd+"',"
							+ "approved_dt=sysdate WHERE HLPL_INV_SEQ_NO='"+hlpl_seq_no+"' and "
							+ "financial_year='"+Fin_yr_hid+"' and contract_type='"+inv_type_hid+"' and  "
							+ "new_inv_seq_no='"+seq_no+"' and supplier_cd='"+supp_cd+"' ";
					stmt.executeUpdate(queryString);
					//msg = "Invoice Approved Successfully !!!";
					}
					msg = "Invoice Approving Process  Has Been Successfully Completed !!!";
				}
			//}
			
			dbcon.commit();
			//url = "../other_invoices/frm_other_invoices_check_X.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type_hid+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&flag="+flag;
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type_hid+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&flag="+flag;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		
		catch(Exception e)
		{
			msg = "Invoice Details Submission Failed !!!";
			e.printStackTrace();
			dbcon.rollback();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../other_invoices/frm_other_invoices_check_X.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
	public void Submit_Check_InvoiceGST_XZ(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "Submit_Check_Invoice()";
		form_name = "frm_other_invoices.jsp";
		String user_cd=request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String inv_type_hid=request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String Fin_yr_hid=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String hlpl_seq_no=request.getParameter("hlpl_seq_no")==null?"":request.getParameter("hlpl_seq_no");
		String seq_no=request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
		String year=request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month=request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
		String rd=request.getParameter("rd")==null?"":request.getParameter("rd");
//		String seq_no=request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
		String supp_cd = request.getParameter("supp_cd")==null?"":request.getParameter("supp_cd");
		
		try
		{
			HttpSession session = request.getSession();
			//if(rd.equalsIgnoreCase("Y")) {
				if(flag.equalsIgnoreCase("C")) {
					String queryString="UPDATE DLNG_SERVICE_INVOICE_MST SET CHECKED_FLAG='"+rd+"',CHECKED_BY='"+user_cd+"',"
							+ "checked_dt=sysdate WHERE "
							+ "INV_SEQ_NO='"+hlpl_seq_no+"' and financial_year='"+Fin_yr_hid+"' and contract_type='"+inv_type_hid+"' "
							+ "and  new_inv_seq_no='"+seq_no+"' and supplier_cd='"+supp_cd+"' ";
					System.out.println("----->>>>"+queryString);
					stmt.executeUpdate(queryString);
					msg = "Invoice Checking Process  Has Been Successfully Completed !!!";
				}else if(flag.equalsIgnoreCase("A")) {
					if(rd.equalsIgnoreCase("N")) {
					queryString1 = "UPDATE DLNG_SERVICE_INVOICE_MST SET " +
							   "checked_flag=null, checked_by=null, checked_dt=null, " +
							   "authorized_flag=null, authorized_by=null, authorized_dt=null, " +
							   "approved_flag='"+rd+"', " +
							   "approved_by='"+user_cd+"', approved_dt=sysdate " +
							   "WHERE financial_year='"+Fin_yr_hid+"' AND " +
				  			   "inv_seq_no="+hlpl_seq_no+" AND " +
				  			   "contract_type='"+inv_type_hid+"' and supplier_cd='"+supp_cd+"' ";
					stmt.executeUpdate(queryString1);
					}else{
					
					String queryString="UPDATE DLNG_SERVICE_INVOICE_MST SET Approved_flag='"+rd+"',approved_by='"+user_cd+"',"
							+ "approved_dt=sysdate WHERE INV_SEQ_NO='"+hlpl_seq_no+"' and "
							+ "financial_year='"+Fin_yr_hid+"' and contract_type='"+inv_type_hid+"' and  "
							+ "new_inv_seq_no='"+seq_no+"' and supplier_cd='"+supp_cd+"' ";
					System.out.println("---Approve-->>>>"+queryString);
					stmt.executeUpdate(queryString);
					//msg = "Invoice Approved Successfully !!!";
					}
					msg = "Invoice Approving Process  Has Been Successfully Completed !!!";
				}
			//}
			
			dbcon.commit();
			//url = "../other_invoices/frm_other_invoices_check_X.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type_hid+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&flag="+flag;
			url = "../general_inv/frm_other_invoices_dtl.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type_hid+"&supplier_cd="+supp_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&flag="+flag;
		//	System.out.println("---url-->>>>"+url);
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		
		catch(Exception e)
		{
			msg = "Invoice Details Submission Failed !!!";
			e.printStackTrace();
			dbcon.rollback();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../other_invoices/frm_other_invoices_check_X.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
	public void SUBMIT_DR_CR_REMARK(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "SUBMIT_DR_CR_REMARK()";
		form_name = "frm_other_invoices.jsp";
		String inv_type_hid = request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String fin_yr =request.getParameter("fin_yr_hid")==null?"":request.getParameter("fin_yr_hid");
		String year =request.getParameter("year")==null?"":request.getParameter("year");
		String month =request.getParameter("month")==null?"":request.getParameter("month");
		String hlpl_no[]= request.getParameterValues("inv_no");
		String CUST_CD[]= request.getParameterValues("CUST_CD");
		String inv_dt[] =  request.getParameterValues("inv_dt");
		String chkval[] = request.getParameterValues("chkval");
		String reason[] = request.getParameterValues("reason_frm");
		String criteria_hid[] = request.getParameterValues("criteria_hid");
		String criteria[] = request.getParameterValues("criteria_hid1");
		String user_cd = request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String temp_cri="";
		try
		{
			HttpSession session = request.getSession();
			String msg="";
			
			for( int i=0;i<chkval.length;i++)
			{
				
				if(chkval[i].toString().equalsIgnoreCase("Y"))
				{
					System.out.println(""+criteria);
				
					String query="	SELECT COUNT(*) FROM FMS8_OTHINV_DR_CR_NOTE WHERE CONTRACT_TYPE='"+inv_type_hid+"' AND FINANCIAL_YEAR='"+fin_yr+"'"
							+ "AND HLPL_INV_SEQ_NO='"+hlpl_no[i]+"' ";
					rset=stmt.executeQuery(query);
					if(rset.next()){
						count=rset.getInt(1);
					}
					if(count==0) {
						
						queryString1 = "INSERT INTO FMS8_OTHINV_DR_CR_NOTE(CUSTOMER_CD,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
								       "INVOICE_DT,CRITERIA,ENT_BY_DTL," +
								       "ENT_DT_DTL,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,GROSS_AMT_INR,NET_AMT_INR,DR_CR_NO,DR_CR_FIN_YEAR,DR_CR_DT,REMARK_DTL)VALUES("+CUST_CD[i]+",'"+inv_type_hid+"', "+hlpl_no[i]+"," +
									   "'"+fin_yr+"',TO_DATE('"+inv_dt[i]+"','DD/MM/YYYY'), " +
									   "'"+criteria[i]+"', '0', sysdate,'0','0','0','0','0','0','0',sysdate,'"+criteria_hid[i]+"')";			
						System.out.println("CR-DR: No#:INSERT:--RU-- "+queryString1);
						stmt1.executeUpdate(queryString1);
						msg="Remark Submitted Successfully!!!";
					}else{
						String query1="	Delete FROM FMS8_OTHINV_DR_CR_NOTE WHERE CONTRACT_TYPE='"+inv_type_hid+"' AND FINANCIAL_YEAR='"+fin_yr+"'"
								+ "AND HLPL_INV_SEQ_NO='"+hlpl_no[i]+"' ";
						stmt.executeUpdate(query1);
						
						queryString1 ="INSERT INTO FMS8_OTHINV_DR_CR_NOTE(CUSTOMER_CD,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
							       "INVOICE_DT,CRITERIA,ENT_BY_DTL," +
							       "ENT_DT_DTL,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,GROSS_AMT_INR,NET_AMT_INR,DR_CR_NO,DR_CR_FIN_YEAR,DR_CR_DT,remark_dtl)VALUES("+CUST_CD[i]+",'"+inv_type_hid+"', "+hlpl_no[i]+"," +
								   "'"+fin_yr+"',TO_DATE('"+inv_dt[i]+"','DD/MM/YYYY'), " +
								   "'"+criteria[i]+"', "+user_cd+", sysdate,'0','0','0','0','0','0','0',SYSDATE,'"+criteria_hid[i]+"')";				
					System.out.println("CR-DR: No#:INSERT:--RU-- "+queryString1);
					stmt1.executeUpdate(queryString1);
					msg="Remark Submitted Successfully!!!";
					}
				}
			}
		
			dbcon.commit();
			url = "../other_invoices/frm_otherinv_set_dr_cr_note.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type_hid+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		
		catch(Exception e)
		{
			msg = "CR DR Details Submission Failed !!!";
			e.printStackTrace();
			dbcon.rollback();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../other_invoices/frm_otherinv_set_dr_cr_note.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
	
	public void SUBMIT_DR_CR_NOTE(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "SUBMIT_DR_CR_NOTE()";
		form_name = "frm_other_invoices.jsp";
		String inv_type_hid = request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String fin_yr =request.getParameter("fin_yr_hid")==null?"":request.getParameter("fin_yr_hid");
		String criteria_hid =request.getParameter("criteria_hid")==null?"":request.getParameter("criteria_hid");
		String year_hid =request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month_hid =request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String hlpl_seq_no_hid =request.getParameter("hlpl_seq_no_hid")==null?"":request.getParameter("hlpl_seq_no_hid");
		String inv_no =request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
		String nw_grt =request.getParameter("nw_grt")==null?"":request.getParameter("nw_grt");
		String grt =request.getParameter("grt")==null?"":request.getParameter("grt");
		String sale_rate =request.getParameter("sale_rate")==null?"":request.getParameter("sale_rate");
		String gross_amt =request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
		String dr_cr_gross_amt =request.getParameter("dr_cr_gross_amt")==null?"":request.getParameter("dr_cr_gross_amt");
		String dr_cr_tax_amt =request.getParameter("dr_cr_tax_amt")==null?"":request.getParameter("dr_cr_tax_amt");
		String tax_amt =request.getParameter("tax_amt")==null?"":request.getParameter("tax_amt");
		String net_amt =request.getParameter("net_amt")==null?"":request.getParameter("net_amt");
		String dr_cr_net_amt =request.getParameter("dr_cr_net_amt")==null?"":request.getParameter("dr_cr_net_amt");
		String cr_dr_flag =request.getParameter("cr_dr_flag")==null?"":request.getParameter("cr_dr_flag");
		String dr_cr_dt =request.getParameter("dr_cr_dt")==null?"":request.getParameter("dr_cr_dt");
		String due_dt =request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
		String user_cd = request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String cust_cd_hid = request.getParameter("cust_cd_hid")==null?"":request.getParameter("cust_cd_hid");
		String nw_sale_rate = request.getParameter("nw_sale_rate")==null?"":request.getParameter("nw_sale_rate");
		String dr_cr_fin_year = request.getParameter("dr_cr_fin_year")==null?"":request.getParameter("dr_cr_fin_year");
		String diff_slots_berth = request.getParameter("diff_slots_berth")==null?"":request.getParameter("diff_slots_berth");
		String diff_hrs_berth = request.getParameter("diff_hrs_berth")==null?"":request.getParameter("diff_hrs_berth");
		String flag_sub = request.getParameter("flag_sub")==null?"":request.getParameter("flag_sub");
		String Sdr_cr_no_hid=request.getParameter("Sdr_cr_no_hid")==null?"":request.getParameter("Sdr_cr_no_hid");
	//	String Sdr_cr_finyr_hid=request.getParameter("Sdr_cr_finyr_hid")==null?"":request.getParameter("Sdr_cr_finyr_hid");
		
		
		
		String temp_cri="";String dr_cr_no="";String FlagValue="Y";String dr_cr_doc_no="";
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
		
		String temp_dr_cr_no="";
		
		try
		{
			HttpSession session = request.getSession();
			String msg="";
			if(!flag_sub.equalsIgnoreCase("Modify")) {
				
			
					if(cr_dr_flag.equalsIgnoreCase("cr")){
						queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS8_OTHINV_DR_CR_NOTE " +
						" WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr_flag+"' "
						+ " AND CONTRACT_TYPE in ('1','2') "; //SB20160502
						System.out.println("CR-DR: MAX-No#:SELECT: "+queryString2);
						rset1 = stmt1.executeQuery(queryString2);
					}else{
					queryString2 = "SELECT MAX(DR_CR_NO) FROM FMS8_OTHINV_DR_CR_NOTE " +
					" WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr_flag+"' AND contract_type in ('1','2')  " ; //RG20161229
				
					System.out.println("CR-DR: MAX-No#:SELECT: "+queryString2);
					rset1 = stmt1.executeQuery(queryString2);
					}
					if(rset1.next())
					{
						if(rset1.getString(1)!=null && !(rset1.getString(1).trim().equals("")))
						{
							dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
							temp_dr_cr_no = ""+(Integer.parseInt(rset1.getString(1))+1);
							
						}
						else
						{
							dr_cr_no = "1";
							temp_dr_cr_no = "1";
						}
					}
					else
					{
						dr_cr_no = "1";
						temp_dr_cr_no = "1";
					}
				
				if(dr_cr_no.length()==1) {
					dr_cr_no = "0000"+dr_cr_no;
				} else if(dr_cr_no.length()==2) {
					dr_cr_no = "000"+dr_cr_no;
				} else if(dr_cr_no.length()==3) {
					dr_cr_no = "00"+dr_cr_no;
				} else if(dr_cr_no.length()==4) {
					dr_cr_no = "0"+dr_cr_no;
				} else if(dr_cr_no.length()==5) {
					dr_cr_no = dr_cr_no;
				}
				String t[]=fin_yr.split("-");
				System.out.println("sdbnfin year-"+fin_yr);
				String Fy1=t[0].substring(0,4);
				String Fy2=t[1].substring(2,4);
				
					String FYformat=Fy1+"-"+Fy2;
					String constant = "/";
					dr_cr_doc_no = dr_cr_no+constant+FYformat; 
					if(cr_dr_flag.equals("cr")){
						dr_cr_doc_no += "/CNP"; 
					}else{
						dr_cr_doc_no += "/DNP"; 
					}
		}	
				
				if(cr_dr_flag.equals("cr"))
				{
					FlagValue="P";
				}
			if(!flag_sub.equalsIgnoreCase("Modify")) {
				String qurey="UPDATE FMS8_OTHINV_DR_CR_NOTE SET TOTAL_QTY='"+grt+"', DIFF_QTY='"+nw_grt+"' , "
					+ "gross_amt_usd='"+gross_amt+"',net_amt_inr='"+net_amt+"',tax_Amt_inr='"+tax_amt+"'"
					+ ",DR_CR_GROSS_AMT_USD='"+dr_cr_gross_amt+"',DR_CR_NET_AMT_INR='"+dr_cr_net_amt+"'"
					+ ", DR_CR_TAX_AMT_INR='"+dr_cr_tax_amt+"',dr_cr_flag='"+cr_dr_flag+"',customer_cd='"+cust_cd_hid+"'"
					+ ",sale_price='"+sale_rate+"' ,dr_cr_dt=to_date('"+dr_cr_dt+"','dd/mm/yyyy'),"
					+ "due_dt=to_date('"+due_dt+"','dd/mm/yyyy'),"
					+ "flag='Y',EMP_CD='"+user_cd+"',ENT_DT=sysdate,dr_cr_no='"+temp_dr_cr_no+"',dr_cr_doc_no='"+dr_cr_doc_no+"',"
					+ "dr_cr_fin_year='"+dr_cr_fin_year+"',dr_cr_sale_rate='"+nw_sale_rate+"',DR_CR_HRS_BERTHING='"+diff_hrs_berth+"',DR_CR_SLOTS_BERTHING='"+diff_slots_berth+"'  where hlpl_inv_seq_no='"+hlpl_seq_no_hid+"'"
					+ " and financial_year='"+fin_yr+"' and contract_type='"+inv_type_hid+"'"
					+ " and criteria='"+criteria_hid+"' ";
					//System.out.println("sdhgquery--"+qurey);
					stmt.executeUpdate(qurey);
					msg="Submitted Successfully...";
					
					queryString1 = "UPDATE FMS7_INVOICE_MST SET FLAG='"+FlagValue+"' "+
							" WHERE CONTRACT_TYPE='"+inv_type_hid+"' AND  HLPL_INV_SEQ_NO='"+hlpl_seq_no_hid+"' "+
							"AND FINANCIAL_YEAR='"+fin_yr+"' and new_inv_seq_no='"+inv_no+"'  and flag!='A' ";			
							//System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
						stmt1.executeUpdate(queryString1);
			}else{
				String qurey="UPDATE FMS8_OTHINV_DR_CR_NOTE SET TOTAL_QTY='"+grt+"', DIFF_QTY='"+nw_grt+"' , "
						+ "gross_amt_usd='"+gross_amt+"',net_amt_inr='"+net_amt+"',tax_Amt_inr='"+tax_amt+"'"
						+ ",DR_CR_GROSS_AMT_USD='"+dr_cr_gross_amt+"',DR_CR_NET_AMT_INR='"+dr_cr_net_amt+"'"
						+ ", DR_CR_TAX_AMT_INR='"+dr_cr_tax_amt+"',customer_cd='"+cust_cd_hid+"'"
						+ ",sale_price='"+sale_rate+"' ,dr_cr_dt=to_date('"+dr_cr_dt+"','dd/mm/yyyy'),"
						+ "due_dt=to_date('"+due_dt+"','dd/mm/yyyy'),dr_cr_fin_year='"+dr_cr_fin_year+"',"
						+ "flag='Y',EMP_CD='"+user_cd+"',ENT_DT=sysdate,"
						+ "dr_cr_sale_rate='"+nw_sale_rate+"',DR_CR_HRS_BERTHING='"+diff_hrs_berth+"',DR_CR_SLOTS_BERTHING='"+diff_slots_berth+"'  where hlpl_inv_seq_no='"+hlpl_seq_no_hid+"'"
						+ " and financial_year='"+fin_yr+"' and contract_type='"+inv_type_hid+"'"
						+ " and criteria='"+criteria_hid+"' and dr_cr_no='"+Sdr_cr_no_hid+"'  ";
				
						//System.out.println("sdhgquery--"+qurey);
						stmt.executeUpdate(qurey);
						msg="Submitted Successfully...";
						
			}
		
			dbcon.commit();
			url = "../other_invoices/frm_gen_dr_cr_note.jsp?msg="+msg+"&inv_no1="+hlpl_seq_no_hid+"&year="+year_hid+"&month="+month_hid+"&inv_type="+inv_type_hid+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			//System.out.println("url--"+url);
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		
		catch(Exception e)
		{
			msg = "CR DR Details Submission Failed !!!";
			e.printStackTrace();
			dbcon.rollback();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../other_invoices/frm_otherinv_set_dr_cr_note.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
	public void Submit_Other_Invoice_Y(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "submitBillingDetails()";
		form_name = "frm_other_invoices.jsp";
		
		
		String user_cd=request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String inv_dt=request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String bill_to=request.getParameter("bill_to")==null?"":request.getParameter("bill_to");
		String bill_addr=request.getParameter("bill_addr")==null?"":request.getParameter("bill_addr");
		String bill_city=request.getParameter("bill_city")==null?"":request.getParameter("bill_city");
		String cust_stcd=request.getParameter("cust_stcd")==null?"":request.getParameter("cust_stcd");
		String sup_stcd=request.getParameter("sup_stcd")==null?"":request.getParameter("sup_stcd");
		//String t2=request.getParameter("t2")==null?"":request.getParameter("t2");
		//String t3=request.getParameter("t3")==null?"":request.getParameter("t3");
		String curr=request.getParameter("curr")==null?"":request.getParameter("curr");
		String amt=request.getParameter("amt")==null?"":request.getParameter("amt");
		String inv_type=request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String tax_sz=request.getParameter("tax_sz")==null?"":request.getParameter("tax_sz");
		String tax_amt=request.getParameter("tax_amt")==null?"":request.getParameter("tax_amt");
		String gross_amt=request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
		String CUST_CD11=request.getParameter("CUST_CD11")==null?"0":request.getParameter("CUST_CD11");
		String year=request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month=request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String net_inr=request.getParameter("net_inr")==null?"0":request.getParameter("net_inr");
		String sac_cd=request.getParameter("sac_cd")==null?"":request.getParameter("sac_cd");
		//String hsn_cd=request.getParameter("hsn_cd")==null?"":request.getParameter("hsn_cd");
		String sac_desc=request.getParameter("sac_desc")==null?"":request.getParameter("sac_desc");
//		String pan_no=request.getParameter("pan_no")==null?"":request.getParameter("pan_no");
//		String ST_NO=request.getParameter("ST_NO")==null?"":request.getParameter("ST_NO");
		String state=request.getParameter("state")==null?"":request.getParameter("state");
		//String cust_pan_no=request.getParameter("cust_pan_no")==null?"":request.getParameter("cust_pan_no");
		String INVOICE_SEQ_NO=request.getParameter("INVOICE_SEQ_NO")==null?"":request.getParameter("INVOICE_SEQ_NO");
		String New_GST_INVOICE_SEQ_NO=request.getParameter("New_GST_INVOICE_SEQ_NO")==null?"":request.getParameter("New_GST_INVOICE_SEQ_NO");
		String old_inv_no=request.getParameter("New_GST_INVOICE_SEQ_NO")==null?"":request.getParameter("New_GST_INVOICE_SEQ_NO");
		String cust_seq_no1=request.getParameter("cust_seq_no1")==null?"":request.getParameter("cust_seq_no1");
		String Fin_yr=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String bill_end_dt=request.getParameter("bill_end_dt")==null?"":request.getParameter("bill_end_dt");
		String bill_start_dt=request.getParameter("bill_start_dt")==null?"":request.getParameter("bill_start_dt");
		String supp_cd_hid=request.getParameter("supp_cd_hid")==null?"":request.getParameter("supp_cd_hid");
		String pur_no=request.getParameter("pur_no")==null?"":request.getParameter("pur_no");
		String ref_no=request.getParameter("ref_no")==null?"":request.getParameter("ref_no");
		String desc=request.getParameter("desc")==null?"":request.getParameter("desc");
		String supp_cd_11=request.getParameter("supp_cd_11")==null?"":request.getParameter("supp_cd_11");
		String seq_no=request.getParameter("seq_no1")==null?"":request.getParameter("seq_no1");
		String hlpl_seq_no=request.getParameter("hlpl_seq_no1")==null?"":request.getParameter("hlpl_seq_no1");
		String fin_yr_kk=request.getParameter("fin_yr_kk")==null?"":request.getParameter("fin_yr_kk");
		String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
		String cust_st_no1=request.getParameter("cust_st_no1")==null?"":request.getParameter("cust_st_no1");
		String cust_pan_no=request.getParameter("cust_pan_no")==null?"":request.getParameter("cust_pan_no");
		String cust_pin=request.getParameter("cust_pin")==null?"":request.getParameter("cust_pin");
		
		
		
		com.seipl.hazira.dlng.util.escapeSingleQuotes es=new com.seipl.hazira.dlng.util.escapeSingleQuotes();
		pur_no=es.replaceSingleQuotes(pur_no);
		sac_cd=es.replaceSingleQuotes(sac_cd);
		sac_desc=es.replaceSingleQuotes(sac_desc);
		//pur_no=es.replaceSingleQuotes(pur_no);
		ref_no=es.replaceSingleQuotes(ref_no);
		bill_addr=es.replaceSingleQuotes(bill_addr);
		desc=es.replaceSingleQuotes(desc);
		
		
		Vector sz=new Vector();String inv_seq_no="";String cust_seq_no="";String new_inv_no="";String GST_INVOICE_SEQ_NO="";
		String msg1="";
	
		
		try
		{
			if(!flag.equalsIgnoreCase("Modify")) {
				
			if(Integer.parseInt(month)<4){
				Fin_yr=(Integer.parseInt(year)-1)+"-"+year;
			}else{
				Fin_yr=year+"-"+(Integer.parseInt(year)+1);
			}
			//System.out.println("fin_yr--"+fin_yr);
			queryString = "SELECT NVL(MAX(cust_inv_seq_no),'0') FROM FMS7_INVOICE_MST " +
				  	  "WHERE financial_year='"+Fin_yr+"' " +
				  	  "AND contract_type in ('C','Y','Z') and supplier_cd='"+supp_cd_hid+"' and flag!='A' ";
			System.out.println("------"+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				if(rset.getString(1)!=null && !(rset.getString(1).trim().equals(""))){
					cust_seq_no = ""+(Integer.parseInt(rset.getString(1))+1);
					}else{
						cust_seq_no = "1";
					}
			}else{
				cust_seq_no = "1";
			}
			queryString = "SELECT NVL(MAX(hlpl_inv_seq_no),'0') FROM FMS7_INVOICE_MST " +
				  	  "WHERE financial_year='"+Fin_yr+"' " +
				  	  "AND contract_type in ('C','Y','Z') and supplier_cd='"+supp_cd_hid+"' and flag!='A' ";
			System.out.println("------"+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				if(rset.getString(1)!=null && !(rset.getString(1).trim().equals(""))){
					inv_seq_no =rset.getString(1);
					}else{
						inv_seq_no = "0";
					}
			}else{
				inv_seq_no = "0";
			}
			String temp_inv_no =inv_seq_no;
				inv_seq_no = ""+(Integer.parseInt(rset.getString(1))+1);
				
				if(inv_seq_no.equals(INVOICE_SEQ_NO)){
				}else{
					inv_seq_no = ""+(Integer.parseInt(rset.getString(1))+1);
					cust_seq_no = ""+(Integer.parseInt(cust_seq_no)+1);
					
				//	GST_INVOICE_SEQ_NO = new_inv_no + "/" + Fin_yr.substring(2,5)+Fin_yr.substring(7,9);
					String temp[]=New_GST_INVOICE_SEQ_NO.split("/");
					String temp1=(Integer.parseInt(temp[0])+1)+"";
					//New_GST_INVOICE_SEQ_NO=temp1+temp[1];
					if(temp1.length()==1) {
						temp1 = "0000"+temp1;
					} else if(temp1.length()==2) {
						temp1 = "000"+temp1;
					} else if(temp1.length()==3) {
						temp1 = "00"+temp1;
					} else if(temp1.length()==4) {
						temp1 = "0"+temp1;
					} else if(temp1.length()==5) {
						temp1 = temp1;
					}
					
					New_GST_INVOICE_SEQ_NO=temp1+"/"+temp[1];
					INVOICE_SEQ_NO=inv_seq_no;
					cust_seq_no1=cust_seq_no;
					msg1="(During preparation : Inv Sequence No - "+old_inv_no+" , During Submission :  Inv Sequence No - "+New_GST_INVOICE_SEQ_NO+")";
				//	System.out.println("--cust_seq_no1--"+cust_seq_no1+"invoice_"+INVOICE_SEQ_NO+"new_gst_seq_no"+New_GST_INVOICE_SEQ_NO);
					
				}
			}
				
				//////////////////////////
			
			HttpSession session = request.getSession();
			if(flag.equalsIgnoreCase("Modify")) {
				String queryString="UPDATE FMS7_INVOICE_MST SET INVOICE_DT=TO_DATE('"+inv_dt+"','DD/MM/YYYY'),"
						+ "TAX_AMT_INR='"+tax_amt+"',"
						+ "NET_AMT_INR='"+net_inr+"' ,GROSS_AMT_INR='"+gross_amt+"', "
						+ "INV_CUR_FLAG='"+curr+"',EMP_CD='"+user_cd+"'  where financial_year='"+Fin_yr+"'  "
						+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+INVOICE_SEQ_NO+"' and new_inv_seq_no='"+New_GST_INVOICE_SEQ_NO+"' ";
			System.out.println("hsdgf---"+queryString);
				stmt.executeUpdate(queryString);
			}else{
//				String queryString1="SELECT COUNT(*) FROM FMS7_INVOICE_MST WHERE financial_year='"+Fin_yr+"'"
//								+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+INVOICE_SEQ_NO+"' "
//										+ "and new_inv_seq_no='"+New_GST_INVOICE_SEQ_NO+"'";
//				System.out.println("---query--"+queryString1);
//				rset1=stmt1.executeQuery(queryString1);
//				if(rset1.next())
//				{
//					count=rset1.getInt(1);
//				}
//				if(count>0) {
//					
//					String queryString2="Delete FROM FMS7_INVOICE_MST WHERE financial_year='"+Fin_yr+"'"
//							+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+INVOICE_SEQ_NO+"' "
//									+ "and new_inv_seq_no='"+New_GST_INVOICE_SEQ_NO+"'";
//						stmt1.executeUpdate(queryString2);
//			
//					
//				}
				String queryString="INSERT INTO FMS7_INVOICE_MST (SN_NO,FGSA_NO,SN_REV_NO,FGSA_REV_NO,"
						+ "CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,HLPL_INV_SEQ_NO,"
						+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR,NET_AMT_INR,FLAG,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,SUPPLIER_CD,NEW_INV_SEQ_NO,EMP_CD,ENT_DT,inv_cur_flag)"
						+ " VALUES ('0','0','0','0','"+supp_cd_hid+"','0','"+inv_type+"',"
								+ "to_date('"+bill_end_dt+"','dd/mm/yyyy'),'"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),"
								+ "'"+cust_seq_no1+"','"+gross_amt+"','"+tax_amt+"','"+net_inr+"',"
								+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),"
								+ "'0','0','0','"+supp_cd_11+"','"+New_GST_INVOICE_SEQ_NO+"','"+user_cd+"',sysdate,'"+curr+"')";
				System.out.println("querey----"+queryString);
				stmt.executeUpdate(queryString);
			}
			String tt="";String am="";String cgst="";String sgst="";String igst="";String tax_cd="";
			if(tax_sz.equalsIgnoreCase("2")) {
			for(int k=0;k<Integer.parseInt(tax_sz);k++) {
				String tax_str=request.getParameter("tax_str"+k)==null?"":request.getParameter("tax_str"+k);
				String tax_amount=request.getParameter("tax_amts"+k)==null?"":request.getParameter("tax_amts"+k);
				tax_cd="C";
				//System.out.println("--tax_amts--"+tax_amount);
				if(k==0){
					tt="CGST-"+tax_str+"%";
					cgst=tax_str;
					
				}else{
					tt+="SGST-"+tax_str+"%";
					sgst=tax_str;
				}
				sz.add(tt);
				
			}
			}else{
				tax_cd="I";
				String tax_str=request.getParameter("tax_str0")==null?"":request.getParameter("tax_str0");
				String tax_amts=request.getParameter("tax_amts")==null?"":request.getParameter("tax_amts");
				tt="IGST-"+tax_str+"%";
				igst=tax_str;
				sz.add(tt);
			}
		//	for(int i=0;i<sz.size();i++) {
			if(flag.equalsIgnoreCase("Modify")) {
				String queryString="UPDATE FMS8_OTHER_INVOICE_DTL SET tax_cd='"+tax_cd+"'"
						+ ",tax_details='"+tt+"',item_description='"+desc+"',rate_cgst='"+cgst+"',"
								+ "rate_sgst='"+sgst+"',rate_igst='"+igst+"',PURCHASE_NO='"+pur_no+"',REFERENCE_NO='"+ref_no+"' "+
						"where financial_year='"+Fin_yr+"'"
								+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' ";
				System.out.println("hsdgf-sd--"+queryString);
				stmt.executeUpdate(queryString);
				
			}else{
//				String queryString1="SELECT COUNT(*) FROM FMS8_OTHER_INVOICE_DTL WHERE financial_year='"+Fin_yr+"'"
//						+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' ";
//								
//					rset1=stmt1.executeQuery(queryString1);
//					if(rset1.next())
//					{
//						count=rset1.getInt(1);
//					}
//					if(count>0) {
//						
//						String queryString2="Delete FROM FMS8_other_invoice_dtl WHERE financial_year='"+Fin_yr+"'"
//								+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' ";
//									//	+ "and new_inv_seq_no='"+seq_no+"'";
//							stmt1.executeUpdate(queryString2);
//				
//				
//					}	
					String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,tax_details,eff_dt,SAC_DESCRIPTION,SAC_CODE,PURCHASE_NO,REFERENCE_NO,"
							+ "ITEM_DESCRIPTION,CUSTOMER_STATE_CD,CUSTOMER_ADDR,CUSTOMER_NM,SUPPLIER_STATE_CD,"
							+ "RATE_CGST,RATE_SGST,RATE_IGST,tax_cd,CUST_CITY,cust_gstin_no,CUSTOMER_PAN_NO,Cust_pin_code,SUPPLIER_CD)"
							+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
							+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),'"+sac_desc+"',"
							+ "'"+sac_cd+"','"+pur_no+"','"+ref_no+"','"+desc+"','"+cust_stcd+"','"+bill_addr+"'"
							+ ",'"+bill_to+"','"+sup_stcd+"','"+cgst+"','"+sgst+"','"+igst+"','"+tax_cd+"',"
							+ "'"+bill_city+"','"+cust_st_no1+"','"+cust_pan_no+"','"+cust_pin+"','"+supp_cd_11+"')";
					System.out.println("---------"+queryString3);
					stmt.executeUpdate(queryString3);
					}
			//}
//			msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Submitted Successfully !!!";
			if(!flag.equals("Modify")) {	
				
				msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Submitted Successfully !!! "+msg1;
			
				}else{
					msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Modified Successfully !!!";
				}
			
			
			dbcon.commit();
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			msg = "Invoice Details Submission Failed !!!";
			e.printStackTrace();
			dbcon.rollback();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
	public void Submit_Other_Invoice_1(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "submitBillingDetails()";
		form_name = "frm_other_invoices.jsp";
		
		
		String user_cd=request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String inv_dt=request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String pay_dt=request.getParameter("pay_dt")==null?"":request.getParameter("pay_dt");
		String curr=request.getParameter("curr")==null?"":request.getParameter("curr");
		
		
		
		String tax_sz=request.getParameter("tax_sz")==null?"":request.getParameter("tax_sz");
		String tax_amt=request.getParameter("tax_amt")==null?"":request.getParameter("tax_amt");
		String net_inr=request.getParameter("net_inr")==null?"":request.getParameter("net_inr");
		String Gross_amt=request.getParameter("Gross_amt")==null?"":request.getParameter("Gross_amt");
		
		
		String CUST_CD11=request.getParameter("CUST_CD11")==null?"0":request.getParameter("CUST_CD11");
		String cust_nm=request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		String cust_addr=request.getParameter("cust_addr")==null?"":request.getParameter("cust_addr");
		String state=request.getParameter("cust_stcd")==null?"":request.getParameter("cust_stcd");
		String cust_pan_no1=request.getParameter("cust_pan_no1")==null?"":request.getParameter("cust_pan_no1");
		String country=request.getParameter("country11")==null?"":request.getParameter("country11");
		String vessel_ag=request.getParameter("vessel_ag")==null?"":request.getParameter("vessel_ag");
		String vessel_cd=request.getParameter("vessel_cd")==null?"":request.getParameter("vessel_cd");
		String vessel_flg=request.getParameter("vessel_flg")==null?"":request.getParameter("vessel_flg");
		String vessel_itm=request.getParameter("vessel_itm")==null?"":request.getParameter("vessel_itm");
		String importer=request.getParameter("importer")==null?"":request.getParameter("importer");
		String grt=request.getParameter("grt")==null?"":request.getParameter("grt");
		String qty=request.getParameter("qty")==null?"":request.getParameter("qty");
		String rate=request.getParameter("rate")==null?"":request.getParameter("rate");
		String t3=request.getParameter("t3")==null?"":request.getParameter("t3");
		String t31=request.getParameter("t31")==null?"":request.getParameter("t31");
		String t4=request.getParameter("t4")==null?"":request.getParameter("t4");
		String t5=request.getParameter("t5")==null?"":request.getParameter("t5");
		String t6=request.getParameter("t6")==null?"":request.getParameter("t6");
		
		
		String year=request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month=request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String inv_type=request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		
		String INVOICE_SEQ_NO=request.getParameter("INVOICE_SEQ_NO")==null?"":request.getParameter("INVOICE_SEQ_NO");
		String New_GST_INVOICE_SEQ_NO=request.getParameter("New_GST_INVOICE_SEQ_NO")==null?"":request.getParameter("New_GST_INVOICE_SEQ_NO");
		String cust_seq_no1=request.getParameter("cust_seq_no1")==null?"":request.getParameter("cust_seq_no1");
		String Fin_yr=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String bill_end_dt=request.getParameter("bill_end_dt")==null?"":request.getParameter("bill_end_dt");
		String bill_start_dt=request.getParameter("bill_start_dt")==null?"":request.getParameter("bill_start_dt");
		String supp_cd_hid=request.getParameter("supp_cd_hid")==null?"":request.getParameter("supp_cd_hid");
		String sup_stcd=request.getParameter("sup_stcd")==null?"":request.getParameter("sup_stcd");
		String berthing_slts=request.getParameter("berthing_slts")==null?"":request.getParameter("berthing_slts");
		String berthing_hrs=request.getParameter("berthing_hrs")==null?"":request.getParameter("berthing_hrs");
		String city=request.getParameter("city")==null?"":request.getParameter("city");
		String pin_code=request.getParameter("pin_code")==null?"":request.getParameter("pin_code");
		String seq_no=request.getParameter("seq_no1")==null?"":request.getParameter("seq_no1");
		String hlpl_seq_no=request.getParameter("hlpl_seq_no1")==null?"":request.getParameter("hlpl_seq_no1");
		String fin_yr_kk=request.getParameter("fin_yr_kk")==null?"":request.getParameter("fin_yr_kk");
		String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
		//String hsn_cd=request.getParameter("hsn_cd")==null?"":request.getParameter("hsn_cd");
		String sac_desc=request.getParameter("sac_desc")==null?"":request.getParameter("sac_desc");
		String sac_cd=request.getParameter("sac_cd")==null?"":request.getParameter("sac_cd");
		String gstin_no=request.getParameter("gstin_no")==null?"":request.getParameter("gstin_no");
		String cgst="";String sgst="";String igst="";
		
		
		
		
		com.seipl.hazira.dlng.util.escapeSingleQuotes es=new com.seipl.hazira.dlng.util.escapeSingleQuotes();
		cust_addr=es.replaceSingleQuotes(cust_addr);
		sac_desc=es.replaceSingleQuotes(sac_desc);
		cust_nm=es.replaceSingleQuotes(cust_nm);
		importer=es.replaceSingleQuotes(importer);
		vessel_flg=es.replaceSingleQuotes(vessel_flg);
		t3=es.replaceSingleQuotes(t3);
		t4=es.replaceSingleQuotes(t4);
		t5=es.replaceSingleQuotes(t5);
		t6=es.replaceSingleQuotes(t6);
		t31=es.replaceSingleQuotes(t31);
		vessel_ag=es.replaceSingleQuotes(vessel_ag);
		cust_pan_no1=es.replaceSingleQuotes(cust_pan_no1);
		Vector sz=new Vector();
		Vector tax_nm=new Vector();
		Vector tax_fact=new Vector();
		
		try
		{
			HttpSession session = request.getSession();
			if(flag.equalsIgnoreCase("Modify")) {
				String queryString="UPDATE FMS7_INVOICE_MST SET INVOICE_DT=TO_DATE('"+inv_dt+"','DD/MM/YYYY'),DUE_DT=TO_DATE('"+pay_dt+"','DD/MM/YYYY'),"
						+ "TAX_AMT_INR='"+tax_amt+"',"
						+ "NET_AMT_INR='"+net_inr+"' ,GROSS_AMT_USD='"+Gross_amt+"', "
						+ "INV_CUR_FLAG='"+curr+"',EMP_CD='"+user_cd+"' where financial_year='"+fin_yr_kk+"'  "
								+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+hlpl_seq_no+"' and new_inv_seq_no='"+seq_no+"' ";
			System.out.println("hsdgf---"+queryString);
				stmt.executeUpdate(queryString);
			}else{
				String queryString1="SELECT COUNT(*) FROM FMS7_INVOICE_MST WHERE financial_year='"+Fin_yr+"'"
								+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+hlpl_seq_no+"' "
										+ "and new_inv_seq_no='"+seq_no+"'";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					count=rset1.getInt(1);
				}
				if(count==0) {
			String queryString="INSERT INTO FMS7_INVOICE_MST (SN_NO,FGSA_NO,SN_REV_NO,FGSA_REV_NO,"
								+ "CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,HLPL_INV_SEQ_NO,"
								+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR,"
								+ "NET_AMT_INR,FLAG,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,SUPPLIER_CD,"
								+ "NEW_INV_SEQ_NO,EMP_CD,ENT_DT,DUE_DT)"
								+ " VALUES ('0','0','0','0','"+CUST_CD11+"','0','"+inv_type+"',"
								+ "to_date('"+bill_end_dt+"','dd/mm/yyyy'),'"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),"
								+ "'"+cust_seq_no1+"','0','"+tax_amt+"','"+net_inr+"',"
								+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),"
								+ "'0','0','"+Gross_amt+"','"+supp_cd_hid+"','"+New_GST_INVOICE_SEQ_NO+"',"
										+ "'"+user_cd+"',sysdate,to_date('"+pay_dt+"','dd/mm/yyyy'))";
			System.out.println("querey----"+queryString);
			stmt.executeUpdate(queryString);
				}else {
					String queryString2="Delete FROM FMS7_INVOICE_MST WHERE financial_year='"+Fin_yr+"'"
							+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+hlpl_seq_no+"' "
									+ "and new_inv_seq_no='"+seq_no+"'";
						stmt1.executeUpdate(queryString2);
						
						String queryString="INSERT INTO FMS7_INVOICE_MST (SN_NO,FGSA_NO,SN_REV_NO,FGSA_REV_NO,"
								+ "CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,HLPL_INV_SEQ_NO,"
								+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR,"
								+ "NET_AMT_INR,FLAG,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,SUPPLIER_CD,"
								+ "NEW_INV_SEQ_NO,EMP_CD,ENT_DT,DUE_DT)"
								+ " VALUES ('0','0','0','0','"+CUST_CD11+"','0','"+inv_type+"',"
								+ "to_date('"+bill_end_dt+"','dd/mm/yyyy'),'"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),"
								+ "'"+cust_seq_no1+"','0','"+tax_amt+"','"+net_inr+"',"
								+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),"
								+ "'0','0','"+Gross_amt+"','"+supp_cd_hid+"','"+New_GST_INVOICE_SEQ_NO+"',"
										+ "'"+user_cd+"',sysdate,to_date('"+pay_dt+"','dd/mm/yyyy'))";
					System.out.println("querey----"+queryString);
					stmt.executeUpdate(queryString);
				}
			}
			String tt="";String am="";String nm="";String fact="";String tax_cd="";
			if(tax_sz.equalsIgnoreCase("2")) {
			for(int k=0;k<Integer.parseInt(tax_sz);k++) {
				String tax_str=request.getParameter("tax_str"+k)==null?"":request.getParameter("tax_str"+k);
				String tax_amount=request.getParameter("tax_amts"+k)==null?"":request.getParameter("tax_amts"+k);
				tax_cd="C";
				
				if(k==0){
					tt="CGST-"+tax_str+"%";
					nm="CGST";
					fact=tax_str;
					cgst=tax_str;
				}else{
					tt+="+SGST-"+tax_str+"%";
					nm="SGST";
					fact=tax_str;
					sgst=tax_str;
				}
				sz.add(tt);
				tax_nm.add(nm);
				tax_fact.add(fact);
				
			}
			}else{
				tax_cd="I";
				String tax_str=request.getParameter("tax_str0")==null?"":request.getParameter("tax_str0");
				String tax_amts=request.getParameter("tax_amts")==null?"":request.getParameter("tax_amts");
				tt="IGST-"+tax_str+"%";
				nm="IGST";
				fact=tax_str;
				igst=tax_str;
				sz.add(tt);
				tax_nm.add(nm);
				tax_fact.add(fact);
			}
			//for(int i=0;i<sz.size();i++) {
			if(flag.equalsIgnoreCase("Modify")) {
				String queryString="UPDATE FMS8_OTHER_INVOICE_DTL SET tax_cd='"+tax_cd+"'"
						+ ",tax_details='"+tt+"',item_description='"+t3+"',rate_cgst='"+cgst+"',"
								+ "rate_sgst='"+sgst+"',rate_igst='"+igst+"',IMPORTER='"+importer+"',"
								+ "VESSEL_AGENT='"+vessel_ag+"',VESSEL_FLAG='"+vessel_flg+"',"
								+ "VESSEL_CD='"+vessel_cd+"',HRS_BERTHING='"+berthing_hrs+"',"
								+ " TIME_SLOTS_BERTHING='"+berthing_slts+"',GRT='"+grt+"',quantity='"+qty+"',"
								+ "rate='"+rate+"' ,REMARK='"+t31+"',REMARK1='"+t4+"',REMARK2='"+t5+"',"
								+ "REMARK3='"+t6+"',VESSEL_ITEM='"+vessel_itm+"',CUSTOMER_NM='"+cust_nm+"',CUSTOMER_ADDR='"+cust_addr+"'"
								+ ",CUSTOMER_PAN_NO='"+cust_pan_no1+"',Cust_gstin_no='"+gstin_no+"',cust_city='"+city+"',cust_pin_code='"+pin_code+"' "+
						"where financial_year='"+fin_yr_kk+"'"
								+ " and contract_type='"+inv_type+"' and inv_seq_no='"+hlpl_seq_no+"' ";
				System.out.println("hsdgf-sd--"+queryString);
				stmt.executeUpdate(queryString);
				
			}else{
				String queryString1="SELECT COUNT(*) FROM FMS8_OTHER_INVOICE_DTL WHERE financial_year='"+Fin_yr+"'"
						+ " and contract_type='"+inv_type+"' and inv_seq_no='"+hlpl_seq_no+"' ";
								
					rset1=stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						count=rset1.getInt(1);
					}
					if(count==0) {
			
					String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
							+ "VESSEL_AGENT,VESSEL_CD,VESSEL_FLAG,VESSEL_ITEM,CUSTOMER_NM,"
							+ "CUSTOMER_ADDR,CUSTOMER_STATE_CD,CUSTOMER_COUNTRY_NM,IMPORTER,"
							+ "CUSTOMER_PAN_NO,SUPPLIER_STATE_CD,TIME_SLOTS_BERTHING,HRS_BERTHING,"
							+ "GRT,QUANTITY,RATE,REMARK,REMARK1,REMARK2,REMARK3,RATE_CGST,RATE_SGST"
							+ ",RATE_IGST,cust_city,cust_pin_code,tax_cd,sac_description,sac_code,cust_gstin_no,SUPPLIER_CD)"
							+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
							+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),"
							+ "'"+t3+"','"+vessel_ag+"','"+vessel_cd+"','"+vessel_flg+"','"+vessel_itm+"',"
							+ "'"+cust_nm+"','"+cust_addr+"','"+state+"','"+country+"','"+importer+"',"
							+ "'"+cust_pan_no1+"','"+sup_stcd+"','"+berthing_slts+"',"
							+ "'"+berthing_hrs+"','"+grt+"','"+qty+"','"+rate+"','"+t31+"','"+t4+"','"+t5+"','"+t6+"','"+cgst+"','"+sgst+"','"+igst+"','"+city+"','"+pin_code+"','"+tax_cd+"','"+sac_desc+"','"+sac_cd+"','"+gstin_no+"','"+supp_cd_hid+"')";
					System.out.println("---------"+queryString3);
					stmt.executeUpdate(queryString3);
					}else{
						String queryString2="Delete FROM FMS8_other_invoice_dtl WHERE financial_year='"+Fin_yr+"'"
								+ " and contract_type='"+inv_type+"' and inv_seq_no='"+hlpl_seq_no+"' ";
										
							stmt1.executeUpdate(queryString2);
							
							
							String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
									+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
									+ "VESSEL_AGENT,VESSEL_CD,VESSEL_FLAG,VESSEL_ITEM,CUSTOMER_NM,"
									+ "CUSTOMER_ADDR,CUSTOMER_STATE_CD,CUSTOMER_COUNTRY_NM,IMPORTER,"
									+ "CUSTOMER_PAN_NO,SUPPLIER_STATE_CD,TIME_SLOTS_BERTHING,HRS_BERTHING,"
									+ "GRT,QUANTITY,RATE,REMARK,REMARK1,REMARK2,REMARK3,RATE_CGST,RATE_SGST"
									+ ",RATE_IGST,cust_city,cust_pin_code,tax_cd,sac_description,sac_code,cust_gstin_no,SUPPLIER_CD)"
									+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
									+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),"
									+ "'"+t3+"','"+vessel_ag+"','"+vessel_cd+"','"+vessel_flg+"','"+vessel_itm+"',"
									+ "'"+cust_nm+"','"+cust_addr+"','"+state+"','"+country+"','"+importer+"',"
									+ "'"+cust_pan_no1+"','"+sup_stcd+"','"+berthing_slts+"',"
									+ "'"+berthing_hrs+"','"+grt+"','"+qty+"','"+rate+"','"+t31+"','"+t4+"','"+t5+"','"+t6+"','"+cgst+"','"+sgst+"','"+igst+"','"+city+"','"+pin_code+"','"+tax_cd+"','"+sac_desc+"','"+sac_cd+"','"+gstin_no+"','"+supp_cd_hid+"')";
							System.out.println("---------"+queryString3);
							stmt.executeUpdate(queryString3);
					}
					}
					
		//	}
			msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Submitted Successfully !!!";
			
			dbcon.commit();
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			msg = "Invoice Details Submission Failed !!!";
			dbcon.rollback();
			e.printStackTrace();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
	public void Submit_Other_Invoice_2(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "submitBillingDetails()";
		form_name = "frm_other_invoices.jsp";
		
		
		String user_cd=request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String inv_dt=request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String pay_dt=request.getParameter("pay_dt")==null?"":request.getParameter("pay_dt");
		String curr=request.getParameter("curr")==null?"":request.getParameter("curr");
		
		String tax_sz=request.getParameter("tax_sz")==null?"":request.getParameter("tax_sz");
		String tax_amt=request.getParameter("tax_amt")==null?"":request.getParameter("tax_amt");
		String net_inr=request.getParameter("net_inr")==null?"":request.getParameter("net_inr");
		String Gross_amt=request.getParameter("Gross_amt")==null?"":request.getParameter("Gross_amt");
		String cust_cd_hid=request.getParameter("cust_cd_hid")==null?"0":request.getParameter("cust_cd_hid");
		String supp_cd_hid=request.getParameter("supp_cd_hid")==null?"0":request.getParameter("supp_cd_hid");
		String sup_stcd=request.getParameter("sup_stcd")==null?"0":request.getParameter("sup_stcd");
		String cust_nm=request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		String cust_addr=request.getParameter("cust_addr")==null?"":request.getParameter("cust_addr");
		String state=request.getParameter("cust_stcd")==null?"":request.getParameter("cust_stcd");
		String cust_pan_no1=request.getParameter("cust_pan_no1")==null?"":request.getParameter("cust_pan_no1");
		String year=request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month=request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String inv_type=request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String INVOICE_SEQ_NO=request.getParameter("INVOICE_SEQ_NO")==null?"":request.getParameter("INVOICE_SEQ_NO");
		String New_GST_INVOICE_SEQ_NO=request.getParameter("New_GST_INVOICE_SEQ_NO")==null?"":request.getParameter("New_GST_INVOICE_SEQ_NO");
		String cust_seq_no1=request.getParameter("cust_seq_no1")==null?"":request.getParameter("cust_seq_no1");
		String Fin_yr=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String bill_end_dt=request.getParameter("bill_end_dt")==null?"":request.getParameter("bill_end_dt");
		String bill_start_dt=request.getParameter("bill_start_dt")==null?"":request.getParameter("bill_start_dt");
		String exchange_rate=request.getParameter("exchange_rate")==null?"":request.getParameter("exchange_rate");
		String amount_inr=request.getParameter("amount_inr")==null?"":request.getParameter("amount_inr");
		String rd_cd=request.getParameter("rd_cd")==null?"":request.getParameter("rd_cd");
		String rd_val=request.getParameter("rd_val")==null?"":request.getParameter("rd_val");
		String user_dt=request.getParameter("user_dt")==null?"":request.getParameter("user_dt");
		String t2=request.getParameter("t2")==null?"":request.getParameter("t2");
		String t3=request.getParameter("t3")==null?"":request.getParameter("t3");
		String t31=request.getParameter("t31")==null?"":request.getParameter("t31");
		String t4=request.getParameter("t4")==null?"":request.getParameter("t4");
		String t6=request.getParameter("t6")==null?"":request.getParameter("t6");
		String Vship_cd_hid[]=request.getParameterValues("Vship_cd_hid");
		String Vcargo_dt_hid[]=request.getParameterValues("Vcargo_dt_hid");
		String Vcargo_qty[]=request.getParameterValues("Vcargo_qty");
		//String amt[]=request.getParameterValues("amt");
		String rate[]=request.getParameterValues("rate");
		String amt[]=request.getParameterValues("amt");
		String seq_no=request.getParameter("seq_no1")==null?"":request.getParameter("seq_no1");
		String hlpl_seq_no=request.getParameter("hlpl_seq_no1")==null?"":request.getParameter("hlpl_seq_no1");
		String fin_yr_kk=request.getParameter("fin_yr_kk")==null?"":request.getParameter("fin_yr_kk");
		String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
		String sac_cd=request.getParameter("sac_cd")==null?"":request.getParameter("sac_cd");
		String sac_desc=request.getParameter("sac_desc")==null?"":request.getParameter("sac_desc");
		String cargo_typ=request.getParameter("Scargo_typ")==null?"":request.getParameter("Scargo_typ");
		
		//System.out.println("-----dvfhj----"+inv_dt);
		
		
		com.seipl.hazira.dlng.util.escapeSingleQuotes es=new com.seipl.hazira.dlng.util.escapeSingleQuotes();
		cust_addr=es.replaceSingleQuotes(cust_addr);
		t3=es.replaceSingleQuotes(t3);
		t31=es.replaceSingleQuotes(t31);
		t4=es.replaceSingleQuotes(t4);
		t6=es.replaceSingleQuotes(t6);
		sac_desc=es.replaceSingleQuotes(sac_desc);
	//	cust_nm=es.replaceSingleQuotes(cust_nm);
		//cust_pan_no1=es.replaceSingleQuotes(cust_pan_no1);
		Vector sz=new Vector();
		Vector tax_nm=new Vector();
		Vector tax_fact=new Vector();
		
		try
		{
			HttpSession session = request.getSession();
			if(flag.equalsIgnoreCase("Modify")) {
				String queryString="UPDATE FMS7_INVOICE_MST SET INVOICE_DT=TO_DATE('"+inv_dt+"','DD/MM/YYYY'),DUE_DT=TO_DATE('"+pay_dt+"','DD/MM/YYYY'),"
						+ "TAX_AMT_INR='"+tax_amt+"',"
						+ "NET_AMT_INR='"+net_inr+"' ,GROSS_AMT_USD='"+Gross_amt+"',GROSS_AMT_INR='"+amount_inr+"',EXCHG_RATE_CD='"+rd_cd+"',"
								+ "EXCHG_RATE_VALUE='"+rd_val+"', "
						+ "INV_CUR_FLAG='"+curr+"',EMP_CD='"+user_cd+"',USER_DEFINED_DAY=TO_DATE('"+user_dt+"','dd/mm/yyyy') where financial_year='"+fin_yr_kk+"'  "
								+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+hlpl_seq_no+"' and new_inv_seq_no='"+seq_no+"' ";
			System.out.println("hsdgf---"+queryString);
				stmt.executeUpdate(queryString);
			}else{
				String queryString1="SELECT COUNT(*) FROM FMS7_INVOICE_MST WHERE financial_year='"+Fin_yr+"'"
								+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+hlpl_seq_no+"' "
										+ "and new_inv_seq_no='"+seq_no+"'";
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					count=rset1.getInt(1);
				}
				if(count==0) {
			String queryString="INSERT INTO FMS7_INVOICE_MST (SN_NO,FGSA_NO,SN_REV_NO,FGSA_REV_NO,"
								+ "CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,HLPL_INV_SEQ_NO,"
								+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR,"
								+ "NET_AMT_INR,FLAG,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,SUPPLIER_CD,"
								+ "NEW_INV_SEQ_NO,EMP_CD,ENT_DT,EXCHG_RATE_CD,EXCHG_RATE_VALUE,DUE_DT,USER_DEFINED_DAY)"
								+ " VALUES ('0','0','0','0','"+cust_cd_hid+"','0','"+inv_type+"',"
								+ "to_date('"+bill_end_dt+"','dd/mm/yyyy'),'"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),"
								+ "'"+cust_seq_no1+"','"+amount_inr+"','"+tax_amt+"','"+net_inr+"',"
								+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),"
								+ "'0','0','"+Gross_amt+"','"+supp_cd_hid+"','"+New_GST_INVOICE_SEQ_NO+"',"
								+ "'"+user_cd+"',sysdate,'"+rd_cd+"','"+rd_val+"',to_date('"+pay_dt+"','dd/mm/yyyy'),to_date('"+user_dt+"','dd/mm/yyyy'))";
			System.out.println("querey----"+queryString);
			stmt.executeUpdate(queryString);
				}else {
					String queryString2="Delete FROM FMS7_INVOICE_MST WHERE financial_year='"+Fin_yr+"'"
							+ " and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+hlpl_seq_no+"' "
									+ "and new_inv_seq_no='"+seq_no+"'";
						stmt1.executeUpdate(queryString2);
						
						String queryString="INSERT INTO FMS7_INVOICE_MST (SN_NO,FGSA_NO,SN_REV_NO,FGSA_REV_NO,"
								+ "CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,HLPL_INV_SEQ_NO,"
								+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR,"
								+ "NET_AMT_INR,FLAG,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_USD,SUPPLIER_CD,"
								+ "NEW_INV_SEQ_NO,EMP_CD,ENT_DT,EXCHG_RATE_CD,EXCHG_RATE_VALUE,DUE_DT,USER_DEFINED_DAY)"
								+ " VALUES ('0','0','0','0','"+cust_cd_hid+"','0','"+inv_type+"',"
								+ "to_date('"+bill_end_dt+"','dd/mm/yyyy'),'"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),"
								+ "'"+cust_seq_no1+"','"+amount_inr+"','"+tax_amt+"','"+net_inr+"',"
								+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),"
								+ "'0','0','"+Gross_amt+"','"+supp_cd_hid+"','"+New_GST_INVOICE_SEQ_NO+"',"
										+ "'"+user_cd+"',sysdate,'"+rd_cd+"','"+rd_val+"',to_date('"+pay_dt+"','dd/mm/yyyy'),to_date('"+user_dt+"','dd/mm/yyyy'))";
			System.out.println("querey----"+queryString);
			stmt.executeUpdate(queryString);
				}
			}
			
			String tt="";String am="";String cgst="";String sgst="";String igst="";String tax_cd="";
			if(tax_sz.equalsIgnoreCase("2")) {
			for(int k=0;k<Integer.parseInt(tax_sz);k++) {
				String tax_str=request.getParameter("tax_str"+k)==null?"":request.getParameter("tax_str"+k);
				String tax_amount=request.getParameter("tax_amts"+k)==null?"":request.getParameter("tax_amts"+k);
				tax_cd="C";
				//System.out.println("--tax_amts--"+tax_amount);
				if(k==0){
					tt="CGST-"+tax_str+"%";
					cgst=tax_str;
					
				}else{
					tt+="SGST-"+tax_str+"%";
					sgst=tax_str;
				}
			
				
			}
			}else{
				tax_cd="I";
				String tax_str=request.getParameter("tax_str0")==null?"":request.getParameter("tax_str0");
				String tax_amts=request.getParameter("tax_amts")==null?"":request.getParameter("tax_amts");
				tt="IGST-"+tax_str+"%";
				igst=tax_str;
				
				
			}
			//for(int i=0;i<sz.size();i++) {
				//System.out.println("------Vship_cd---"+Vship_cd_hid.length);
//			if(flag.equalsIgnoreCase("Modify")) {
//				for(int k=0;k<Vship_cd_hid.length;k++) {
//				String queryString="UPDATE FMS8_OTHER_INVOICE_DTL SET tax_cd='"+tax_cd+"'"
//						+ ",tax_details='"+tt+"',item_description='"+t3+"',rate_cgst='"+cgst+"',"
//								+ "rate_sgst='"+sgst+"',rate_igst='"+igst+"',"
//								+ "REMARK='"+t31+"',REMARK1='"+t4+"',"
//							+ "REMARK2='"+t6+"',VESSEL_CD='"+Vship_cd_hid[k]+"',cargo_amount='"+amt[k]+"',"
//						+ "cargo_dt=to_date('"+Vcargo_dt_hid[k]+"','dd/mm/yyyy'),quantity='"+Vcargo_qty[k]+"',"
//								+ "rate='"+rate[k]+"' "+
//						"where financial_year='"+fin_yr_kk+"'"
//								+ " and contract_type='"+inv_type+"' and inv_seq_no='"+hlpl_seq_no+"' ";
//				System.out.println("hsdgf-sd--"+queryString);
//				stmt.executeUpdate(queryString);
//				}
//				
//			}else{
				String queryString1="SELECT COUNT(*) FROM FMS8_OTHER_INVOICE_DTL WHERE financial_year='"+Fin_yr+"'"
						+ " and contract_type='"+inv_type+"' and inv_seq_no='"+hlpl_seq_no+"' ";
								
					rset1=stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						count=rset1.getInt(1);
					}
					if(count==0) {
				for(int k=0;k<Vship_cd_hid.length;k++) {
					
					String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
							+ "CUSTOMER_NM,"
							+ "CUSTOMER_ADDR,CUSTOMER_STATE_CD,"
							+ "CUSTOMER_PAN_NO,SUPPLIER_STATE_CD,QUANTITY,RATE,REMARK,REMARK1,REMARK2"
							+ ",cargo_amount,VESSEL_CD,rate_cgst,rate_sgst,rate_igst,tax_cd,cargo_dt,sac_description,sac_code,cargo_type,SUPPLIER_CD)"
							+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
							+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),"
							+ "'"+t3+"',"
							+ "'"+cust_nm+"','"+cust_addr+"','"+state+"',"
							+ "'"+cust_pan_no1+"','"+sup_stcd+"','"+Vcargo_qty[k]+"','"+rate[k]+"',"
							+ "'"+t31+"','"+t4+"','"+t6+"','"+amt[k]+"','"+Vship_cd_hid[k]+"','"+cgst+"','"+sgst+"','"+igst+"','"+tax_cd+"',to_date('"+Vcargo_dt_hid[k]+"','dd/mm/yyyy'),'"+sac_desc+"','"+sac_cd+"','"+cargo_typ+"','"+supp_cd_hid+"')";
					System.out.println("---------"+queryString3);
					stmt.executeUpdate(queryString3);
				}
					}else{
						String queryString2="Delete FROM FMS8_other_invoice_dtl WHERE financial_year='"+Fin_yr+"'"
								+ " and contract_type='"+inv_type+"' and inv_seq_no='"+hlpl_seq_no+"' ";
										
							stmt1.executeUpdate(queryString2);
							
							for(int k=0;k<Vship_cd_hid.length;k++) {
								
								
								String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
										+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
										+ "CUSTOMER_NM,"
										+ "CUSTOMER_ADDR,CUSTOMER_STATE_CD,"
										+ "CUSTOMER_PAN_NO,SUPPLIER_STATE_CD,QUANTITY,RATE,REMARK,REMARK1,REMARK2"
										+ ",cargo_amount,VESSEL_CD,rate_cgst,rate_sgst,rate_igst,tax_cd,cargo_dt,sac_description,sac_code,cargo_type,SUPPLIER_CD)"
										+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
										+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),"
										+ "'"+t3+"',"
										+ "'"+cust_nm+"','"+cust_addr+"','"+state+"',"
										+ "'"+cust_pan_no1+"','"+sup_stcd+"','"+Vcargo_qty[k]+"','"+rate[k]+"',"
										+ "'"+t31+"','"+t4+"','"+t6+"','"+amt[k]+"','"+Vship_cd_hid[k]+"','"+cgst+"','"+sgst+"','"+igst+"','"+tax_cd+"',to_date('"+Vcargo_dt_hid[k]+"','dd/mm/yyyy'),'"+sac_desc+"','"+sac_cd+"','"+cargo_typ+"','"+supp_cd_hid+"')";
								System.out.println("---------"+queryString3);
								stmt.executeUpdate(queryString3);
							}
					}
					//}  //
			//}
			msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Submitted Successfully !!!";
			
			dbcon.commit();
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			msg = "Invoice Details Submission Failed !!!";
			e.printStackTrace();
			dbcon.rollback();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../other_invoices/frm_other_invoices_dtl.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
	
	public void Submit_Other_Invoice_Z(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "submitBillingDetails()";
		form_name = "frm_other_invoices.jsp";
		
		String pdf_format_type=request.getParameter("pdf_format_type")==null?"0":request.getParameter("pdf_format_type"); //SB20171108
		String user_cd=request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String inv_dt=request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String curr=request.getParameter("curr")==null?"":request.getParameter("curr");
		
		String tax_sz=request.getParameter("tax_sz")==null?"":request.getParameter("tax_sz");
		String tax_amt=request.getParameter("tax_amt")==null?"":request.getParameter("tax_amt");
		String net_inr=request.getParameter("net_inr")==null?"":request.getParameter("net_inr");
		String gross_amt=request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
		String CUST_CD11=request.getParameter("CUST_CD11")==null?"0":request.getParameter("CUST_CD11");
		String supp_cd=request.getParameter("supp_cd")==null?"0":request.getParameter("supp_cd");
		String supp_state_cd=request.getParameter("supp_state_cd")==null?"0":request.getParameter("supp_state_cd");
		String cust_nm=request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		String address=request.getParameter("address")==null?"":request.getParameter("address");
		String state=request.getParameter("cust_stcd")==null?"":request.getParameter("cust_stcd");
		String city=request.getParameter("city")==null?"":request.getParameter("city");
		String cust_pan_no1=request.getParameter("cust_pan_no1")==null?"":request.getParameter("cust_pan_no1");
		String year=request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month=request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String inv_type=request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String INVOICE_SEQ_NO=request.getParameter("INVOICE_SEQ_NO")==null?"":request.getParameter("INVOICE_SEQ_NO");
		String New_GST_INVOICE_SEQ_NO=request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
		String old_inv_no=request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
		String cust_seq_no1=request.getParameter("cust_seq_no1")==null?"":request.getParameter("cust_seq_no1");
		String Fin_yr=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String bill_end_dt=request.getParameter("bill_end_dt")==null?"":request.getParameter("bill_end_dt");
		String bill_start_dt=request.getParameter("bill_start_dt")==null?"":request.getParameter("bill_start_dt");
		String exchange_rate=request.getParameter("exchange_rate")==null?"":request.getParameter("exchange_rate");
	//	String amount_inr=request.getParameter("amount_inr")==null?"":request.getParameter("amount_inr");
		//String hsn_cd=request.getParameter("hsn_cd")==null?"":request.getParameter("hsn_cd");
		//String sac_cd=request.getParameter("sac_cd")==null?"":request.getParameter("sac_cd");
	//	String sac_desc=request.getParameter("sac_desc")==null?"":request.getParameter("sac_desc");
		String pin_code=request.getParameter("pin_code")==null?"":request.getParameter("pin_code");
		String cust_gstin_no1=request.getParameter("cust_gstin_no1")==null?"":request.getParameter("cust_gstin_no1");
		String rowno=request.getParameter("rowno")==null?"":request.getParameter("rowno");
//		String rd_cd=request.getParameter("rd_cd")==null?"":request.getParameter("rd_cd");
//		String rd_val=request.getParameter("rd_val")==null?"":request.getParameter("rd_val");
		String t2=request.getParameter("t2")==null?"":request.getParameter("t2");
		String flag_radio_hid=request.getParameter("flag_radio_hid")==null?"":request.getParameter("flag_radio_hid");
		String sale_no=request.getParameter("sale_no")==null?"":request.getParameter("sale_no");
		//String UAM_no=request.getParameter("UAM_no")==null?"":request.getParameter("UAM_no");
		
		String queoption[]=request.getParameterValues("queoption");
		String Amt[]=request.getParameterValues("Amt");
		String rate[]=request.getParameterValues("rate");
		String Qty[]=request.getParameterValues("Qty");
		String hsn_cd[]=request.getParameterValues("hsn_cd");
		String UOM[]=request.getParameterValues("UOM");
		
		//String seq_no=request.getParameter("seq_no1")==null?"":request.getParameter("seq_no1");
		String hlpl_seq_no=request.getParameter("hlpl_seq_no1")==null?"":request.getParameter("hlpl_seq_no1");
//		String fin_yr_kk=request.getParameter("fin_yr_kk")==null?"":request.getParameter("fin_yr_kk");
		String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
		String gate_no=request.getParameter("gate_pass_no")==null?"":request.getParameter("gate_pass_no");
		//System.out.println("-----dvfhj----"+inv_dt);
		
		
		com.seipl.hazira.dlng.util.escapeSingleQuotes es=new com.seipl.hazira.dlng.util.escapeSingleQuotes();
		address=es.replaceSingleQuotes(address);
		t2=es.replaceSingleQuotes(t2);
		city=es.replaceSingleQuotes(city);
//	//	cust_nm=es.replaceSingleQuotes(cust_nm);
		//cust_pan_no1=es.replaceSingleQuotes(cust_pan_no1);
		Vector sz=new Vector();
		Vector tax_nm=new Vector();
		Vector tax_fact=new Vector();
		Vector Vsac_desc=new Vector();
		String inv_seq_no="";String cust_seq_no="";String new_inv_no="";String GST_INVOICE_SEQ_NO="";
		String abc_msg="";
		
		try
		{
			HttpSession session = request.getSession();
			String msg1="";
		//	System.out.println("--flag----: "+flag);
			if(!flag.equalsIgnoreCase("Modify")) 
			{
				if(Integer.parseInt(month)<4){
					Fin_yr=(Integer.parseInt(year)-1)+"-"+year;
				}else{
					Fin_yr=year+"-"+(Integer.parseInt(year)+1);
				}
				
				//System.out.println("fin_yr--"+fin_yr);
				queryString = "SELECT NVL(MAX(cust_inv_seq_no),'0') FROM FMS7_INVOICE_MST " +
				//SB20171025	  	  "WHERE financial_year='"+Fin_yr+"' AND contract_type in ('C','Y','Z') and customer_cd='"+CUST_CD11+"' and flag!='A' ";
				"WHERE financial_year='"+Fin_yr+"' AND contract_type='"+inv_type+"' and customer_cd='"+CUST_CD11+"' and flag!='A' "; //SB20171025
				System.out.println("------"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					cust_seq_no = ""+(rset.getInt(1)+1);
				}else{
					cust_seq_no = "1";
				}
				queryString = "SELECT NVL(MAX(hlpl_inv_seq_no),'0') FROM FMS7_INVOICE_MST " +
				//SB20171025	  	  "WHERE financial_year='"+Fin_yr+"' AND contract_type in ('C','Y','Z') and supplier_cd='"+supp_cd+"' and flag!='A' ";
				 "WHERE financial_year='"+Fin_yr+"' AND contract_type='"+inv_type+"' and supplier_cd='"+supp_cd+"' and flag!='A' "; //SB20171025
				System.out.println("--MAX SeqNo: "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
					{
						inv_seq_no = ""+(rset.getInt(1)+1);
					}
				else
					{
						inv_seq_no = "1";
					}
				}
			else
			{
				inv_seq_no=New_GST_INVOICE_SEQ_NO;
			}
//			System.out.println(New_GST_INVOICE_SEQ_NO+" New INV-NO: "+inv_seq_no); //NO ACTION
			
			if(inv_seq_no.equals(New_GST_INVOICE_SEQ_NO))
			{
				System.out.println("NO CHANGE IN INV-NO: "); //NO ACTION
				System.out.println(INVOICE_SEQ_NO+" New INV-NO: "+inv_seq_no); //NO ACTION
			}
			else
			{				
				System.out.println("CHANGE IN INV-NO: "+New_GST_INVOICE_SEQ_NO); //NO ACTION
				String temp[]=New_GST_INVOICE_SEQ_NO.split("/");
				String temp1=inv_seq_no;
				//New_GST_INVOICE_SEQ_NO=temp1+temp[1];
			/*	if(temp1.length()==1) {
					temp1 = "0000"+temp1;
				} else if(temp1.length()==2) {
					temp1 = "000"+temp1;
				} else if(temp1.length()==3) {
					temp1 = "00"+temp1;
				} else if(temp1.length()==4) {
					temp1 = "0"+temp1;
				} else if(temp1.length()==5) {
					temp1 = temp1;
				}*/
				if(temp1.length()==1) {
					temp1 = "00"+temp1;
				} else if(temp1.length()==2) {
					temp1 = "0"+temp1;
				} else if(temp1.length()==3) {
					temp1 = temp1;
				}
				String CustCode="";
				if(CUST_CD11.length()==1) {
					CustCode = "00"+CUST_CD11;
				} else if(CUST_CD11.length()==2) {
					CustCode = "0"+CUST_CD11;
				} else if(CUST_CD11.length()==3) {
					CustCode = CUST_CD11;
				}
				String FY[]=temp[1].split("-");
	//SB			New_GST_INVOICE_SEQ_NO=temp1+"/"+temp[1];
				if(inv_type.equals("Z")) //SB20171025
					New_GST_INVOICE_SEQ_NO="BIPL"+CustCode+flag_radio_hid+"-"+FY[0]+FY[1]+temp1;
				else if(inv_type.equals("A")) 
				///	New_GST_INVOICE_SEQ_NO="BIPL"+CustCode+inv_type+"-"+FY[0]+FY[1]+temp1; //SB20171025
					New_GST_INVOICE_SEQ_NO="BI"+CustCode+flag_radio_hid+"-A"+temp1+"-"+FY[0]+FY[1]; //SB20171025
				
				INVOICE_SEQ_NO=inv_seq_no;
				cust_seq_no1=cust_seq_no;
			
				//System.out.println("--cust_seq_no1--"+cust_seq_no1+"invoice_"+INVOICE_SEQ_NO+"new_gst_seq_no"+New_GST_INVOICE_SEQ_NO);		
			}
			if(!old_inv_no.equals(New_GST_INVOICE_SEQ_NO))
				msg1="(During preparation : Inv Sequence No - "+old_inv_no+" , During Submission :  Inv Sequence No - "+New_GST_INVOICE_SEQ_NO+")";
			if(flag.equalsIgnoreCase("Modify")) 
			{
				String queryString="UPDATE FMS7_INVOICE_MST SET INVOICE_DT=TO_DATE('"+inv_dt+"','DD/MM/YYYY'),"
						+ "TAX_AMT_INR='"+tax_amt+"',"
						+ "NET_AMT_INR='"+net_inr+"' ,GROSS_AMT_INR='"+gross_amt+"', "
							+ "INV_CUR_FLAG='0',EMP_CD='"+user_cd+"' " +
						//rt+ "INV_CUR_FLAG='"+curr+"',EMP_CD='"+user_cd+"' " +
						" where financial_year='"+Fin_yr+"' and contract_type='"+inv_type+"' and hlpl_inv_seq_no='"+hlpl_seq_no+"' and new_inv_seq_no='"+New_GST_INVOICE_SEQ_NO+"' ";
			System.out.println("GST-Modify: "+queryString);
				stmt.executeUpdate(queryString);
			}
			else
			{
					String queryString="INSERT INTO FMS7_INVOICE_MST (SN_NO,FGSA_NO,SN_REV_NO,FGSA_REV_NO,"
							+ "CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,HLPL_INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR,"
							+ "NET_AMT_INR,FLAG,INVOICE_DT,TOTAL_QTY,SALE_PRICE,SUPPLIER_CD,"
							+ "NEW_INV_SEQ_NO,EMP_CD,ENT_DT,gross_amt_usd)"
							+ " VALUES ('0','0','0','0','"+CUST_CD11+"','0','"+inv_type+"',"
							+ "to_date('"+bill_end_dt+"','dd/mm/yyyy'),'"+INVOICE_SEQ_NO+"',"
							+ "'"+Fin_yr+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),"
							+ "'"+cust_seq_no1+"','"+gross_amt+"','"+tax_amt+"','"+net_inr+"',"
							+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),"
							+ "'0','0','"+supp_cd+"','"+New_GST_INVOICE_SEQ_NO+"',"
									+ "'"+user_cd+"',sysdate,'0')";
					System.out.println("GST-INSERT: "+queryString);
					stmt.executeUpdate(queryString);
			}
			System.out.println("TAX CALCULATION: >>>>>>>>:Size: "+tax_sz);
			String tt="";String am="";String cgst="";String sgst="";String igst="";String tax_cd="";
			if(tax_sz.equalsIgnoreCase("2")) 
			{
				for(int k=0;k<Integer.parseInt(tax_sz);k++) 
				{
					String tax_str=request.getParameter("tax_str"+k)==null?"":request.getParameter("tax_str"+k);
					String tax_amount=request.getParameter("tax_amts"+k)==null?"":request.getParameter("tax_amts"+k);
					tax_cd="C";
					//System.out.println("--tax_amts--"+tax_amount);
					if(k==0){
						tt="CGST-"+tax_str+"%";
						cgst=tax_str;
						
					}else{
						tt+="SGST-"+tax_str+"%";
						sgst=tax_str;
					}
				}
			}
			else
			{
				tax_cd="I";
				String tax_str=request.getParameter("tax_str0")==null?"":request.getParameter("tax_str0");
				String tax_amts=request.getParameter("tax_amts")==null?"":request.getParameter("tax_amts");
				tt="IGST-"+tax_str+"%";
				igst=tax_str;
			}
			System.out.println("OTHER INVOICE: >>>>>>>>:INV-SEQ-NO: "+INVOICE_SEQ_NO);
				String queryString1="SELECT COUNT(*) FROM FMS8_OTHER_INVOICE_DTL WHERE financial_year='"+Fin_yr+"'"
						+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' ";
								
					rset1=stmt1.executeQuery(queryString1);
					System.out.println("querydjsd---"+queryString1);
					if(rset1.next())
					{
						count=rset1.getInt(1);
					}
					if(count==0) {
				for(int k=0;k<Qty.length;k++) 
				{
					String ab=queoption[k];
					String abc=es.replaceSingleQuotes(ab);
					
					String ab_1=UOM[k];
					String abc_1=es.replaceSingleQuotes(ab_1);
					if(flag_radio_hid.equalsIgnoreCase("P")) {
					String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
							+ "CUSTOMER_NM,"
							+ "CUSTOMER_ADDR,CUSTOMER_STATE_CD,"
							+ "CUSTOMER_PAN_NO,SUPPLIER_STATE_CD,QUANTITY,RATE"
							+ ",cargo_amount,rate_cgst,rate_sgst,rate_igst,tax_cd,hsn_code"
							+ ",cust_pin_code,cust_city,gate_pass_no,cust_gstin_no,flag_sac,sale_no,UAM_NO,SUPPLIER_CD)"
							+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
							+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),"
							+ "'"+abc+"',"
							+ "'"+t2+"','"+address+"','"+state+"',"
							+ "'"+cust_pan_no1+"','"+supp_state_cd+"','"+Qty[k]+"','"+rate[k]+"','"+Amt[k]+"','"+cgst+"'"
									+ ",'"+sgst+"','"+igst+"','"+tax_cd+"','"+hsn_cd[k]+"','"+pin_code+"','"+city+"','"+gate_no+"','"+cust_gstin_no1+"','"+flag_radio_hid+"','"+sale_no+"','"+abc_1+"','"+supp_cd+"')";
//					System.out.println("---------"+queryString3);
					stmt.executeUpdate(queryString3);
					}
					else 
					{
						String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
								+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
								+ "CUSTOMER_NM,"
								+ "CUSTOMER_ADDR,CUSTOMER_STATE_CD,"
								+ "CUSTOMER_PAN_NO,SUPPLIER_STATE_CD,QUANTITY,RATE"
								+ ",cargo_amount,rate_cgst,rate_sgst,rate_igst,tax_cd,sac_code"
								+ ",cust_pin_code,cust_city,gate_pass_no,cust_gstin_no,flag_sac,sale_no,UAM_NO,SUPPLIER_CD)"
								+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),"
								+ "'"+abc+"',"
								+ "'"+t2+"','"+address+"','"+state+"',"
								+ "'"+cust_pan_no1+"','"+supp_state_cd+"','"+Qty[k]+"','"+rate[k]+"','"+Amt[k]+"','"+cgst+"'"
										+ ",'"+sgst+"','"+igst+"','"+tax_cd+"','"+hsn_cd[k]+"','"+pin_code+"','"+city+"','"+gate_no+"','"+cust_gstin_no1+"','"+flag_radio_hid+"','"+sale_no+"','"+abc_1+"','"+supp_cd+"')";
//						System.out.println("---------"+queryString3);
						stmt.executeUpdate(queryString3);
					}
				}
					}
					else
					{
						String queryString2="Delete FROM FMS8_OTHER_INVOICE_DTL WHERE financial_year='"+Fin_yr+"'"
								+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' ";
							System.out.println("---s-ds-d-"+queryString2);			
							stmt1.executeUpdate(queryString2);
							
							for(int k=0;k<Qty.length;k++) {
								String ab=queoption[k];
								String abc=es.replaceSingleQuotes(ab);
								
								String ab_1=UOM[k];
								String abc_1=es.replaceSingleQuotes(ab_1);
								
				if(flag_radio_hid.equalsIgnoreCase("P")) {
					String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
							+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
							+ "CUSTOMER_NM,"
							+ "CUSTOMER_ADDR,CUSTOMER_STATE_CD,"
							+ "CUSTOMER_PAN_NO,SUPPLIER_STATE_CD,QUANTITY,RATE"
							+ ",cargo_amount,rate_cgst,rate_sgst,rate_igst,tax_cd,hsn_code"
							+ ",cust_pin_code,cust_city,gate_pass_no,cust_gstin_no,flag_sac,sale_no,UAM_NO,SUPPLIER_CD)"
							+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
							+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),"
							+ "'"+abc+"',"
							+ "'"+t2+"','"+address+"','"+state+"',"
							+ "'"+cust_pan_no1+"','"+supp_state_cd+"','"+Qty[k]+"','"+rate[k]+"','"+Amt[k]+"','"+cgst+"'"
									+ ",'"+sgst+"','"+igst+"','"+tax_cd+"','"+hsn_cd[k]+"','"+pin_code+"','"+city+"','"+gate_no+"','"+cust_gstin_no1+"','"+flag_radio_hid+"','"+sale_no+"','"+abc_1+"','"+supp_cd+"')";
					System.out.println("---HSN----:"+queryString3);
					stmt.executeUpdate(queryString3);
					}else {
						String queryString3="INSERT INTO FMS8_OTHER_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
								+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
								+ "CUSTOMER_NM,"
								+ "CUSTOMER_ADDR,CUSTOMER_STATE_CD,"
								+ "CUSTOMER_PAN_NO,SUPPLIER_STATE_CD,QUANTITY,RATE"
								+ ",cargo_amount,rate_cgst,rate_sgst,rate_igst,tax_cd,sac_code"
								+ ",cust_pin_code,cust_city,gate_pass_no,cust_gstin_no,flag_sac,sale_no,UAM_NO,SUPPLIER_CD)"
								+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"','"+tt+"',to_date('"+inv_dt+"','dd/mm/yyyy'),"
								+ "'"+abc+"',"
								+ "'"+t2+"','"+address+"','"+state+"',"
								+ "'"+cust_pan_no1+"','"+supp_state_cd+"','"+Qty[k]+"','"+rate[k]+"','"+Amt[k]+"','"+cgst+"'"
										+ ",'"+sgst+"','"+igst+"','"+tax_cd+"','"+hsn_cd[k]+"','"+pin_code+"','"+city+"','"+gate_no+"','"+cust_gstin_no1+"','"+flag_radio_hid+"','"+sale_no+"','"+abc_1+"','"+supp_cd+"')";
						System.out.println("--SAC--: "+queryString3);
						stmt.executeUpdate(queryString3);
					}
							}					
							
					}  ///else

				if(!flag.equals("Modify")) 
				{				
					msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Submitted Successfully !!! "+msg1;			
				}
				else
				{
					msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Modified Successfully !!!";
				}			
			dbcon.commit();
			
			url = "../general_inv/frm_other_invoices_dtl.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;
			System.out.println("1. LAST LINE : "+url);
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			System.out.println("2. LAST LINE : "+url);
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Invoice Details Submission Failed !!!";
			e.printStackTrace();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../general_inv/frm_other_invoices_dtl.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission;	
		}		
	}//End Of Method submitBillingDetails() ...
/////////////SB20170805: New General Table for Invoice submission//////////////////
	public void SubmitGSTInvoiceXZ(HttpServletRequest request,HttpServletResponse response) throws SQLException
	{
		methodName = "SubmitGSTInvoice()";
		form_name = "frm_other_invoices.jsp";
		///////////////////SB20170804: NEW Dynamic Codes////////////////////////////////////////////////////////
		String line_item_chk[]=request.getParameterValues("chk_flg");//Hiren_20200520
		System.out.println("-----line_item_chk----"+line_item_chk.length);
		String modCd=request.getParameter("modCd")==null?"":request.getParameter("modCd"); //Hiren_20200514
		String formId=request.getParameter("formId")==null?"":request.getParameter("formId"); //Hiren_20200514
		String subModUrl=request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl"); //Hiren_20200514
		
		String Supp_Abr=request.getParameter("Supp_Abr")==null?"":request.getParameter("Supp_Abr"); //SB20180316
		String comp_cd=request.getParameter("comp_cd")==null?"101":request.getParameter("comp_cd");
		String supp_cd=request.getParameter("supp_cd")==null?"1":request.getParameter("supp_cd");
		String trans_cd=request.getParameter("trans_cd")==null?"0":request.getParameter("trans_cd");
		String agrmt_cd=request.getParameter("agrmt_cd")==null?"1":request.getParameter("agrmt_cd");
		String cont_cd=request.getParameter("cont_cd")==null?"1":request.getParameter("cont_cd");
		String mapping_id=request.getParameter("mapping_id")==null?"1":request.getParameter("mapping_id");
		String entry_state_cd=request.getParameter("supp_state_cd")==null?"0":request.getParameter("supp_state_cd");
		String exit_state_cd=request.getParameter("cust_stcd")==null?"0":request.getParameter("cust_stcd");
		String cust_cd=request.getParameter("trans_cd")==null?"0":request.getParameter("trans_cd");
		String cust_plant_cd=request.getParameter("cust_plant_cd")==null?"0":request.getParameter("cust_plant_cd");
		String New_seq_no="";
		mapping_id = supp_cd+"-"+cust_cd+"-"+agrmt_cd+"-"+cont_cd+"-"+trans_cd+"-"+entry_state_cd+"-"+exit_state_cd;
//		System.out.println("MAPPING_ID: "+mapping_id);
		String supp_state_cd=request.getParameter("supp_state_cd")==null?"0":request.getParameter("supp_state_cd");
		////////////////////////////////////////////////////////////////////////////////////////////
		String pdf_format_type=request.getParameter("pdf_format_type")==null?"0":request.getParameter("pdf_format_type"); //SB20171108
		String user_cd=request.getParameter("user_cd_hid")==null?"":request.getParameter("user_cd_hid");
		String inv_dt=request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		String curr=request.getParameter("curr")==null?"":request.getParameter("curr");
		
		String tax_sz=request.getParameter("tax_sz")==null?"":request.getParameter("tax_sz");
		String tax_amt=request.getParameter("tax_amt")==null?"":request.getParameter("tax_amt");
		String net_inr=request.getParameter("net_inr")==null?"":request.getParameter("net_inr");
		String gross_amt=request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
		String CUST_CD11=request.getParameter("trans_cd")==null?"0":request.getParameter("trans_cd");
		String supp_cdhid=request.getParameter("supp_cd_hid")==null?"0":request.getParameter("supp_cd_hid");
		String sup_stcd=request.getParameter("sup_stcd")==null?"0":request.getParameter("sup_stcd");
		String cust_nm=request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		String cust_abr=request.getParameter("cust_abr")==null?"":request.getParameter("cust_abr");
		String address=request.getParameter("address")==null?"":request.getParameter("address");
		String state=request.getParameter("cust_stcd")==null?"":request.getParameter("cust_stcd");
		String city=request.getParameter("city")==null?"":request.getParameter("city");
		String cust_pan_no1=request.getParameter("cust_pan_no1")==null?"":request.getParameter("cust_pan_no1");
		String year=request.getParameter("year_hid")==null?"":request.getParameter("year_hid");
		String month=request.getParameter("month_hid")==null?"":request.getParameter("month_hid");
		String inv_type=request.getParameter("inv_type_hid")==null?"":request.getParameter("inv_type_hid");
		String INVOICE_SEQ_NO=request.getParameter("INVOICE_SEQ_NO")==null?"":request.getParameter("INVOICE_SEQ_NO");
		String New_GST_INVOICE_SEQ_NO=request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
		String old_inv_no=request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
		String cust_seq_no1=request.getParameter("cust_seq_no1")==null?"":request.getParameter("cust_seq_no1");
		String Fin_yr=request.getParameter("Fin_yr_hid")==null?"":request.getParameter("Fin_yr_hid");
		String bill_end_dt=request.getParameter("bill_end_dt")==null?"":request.getParameter("bill_end_dt");
		String bill_start_dt=request.getParameter("bill_start_dt")==null?"":request.getParameter("bill_start_dt");
		String exchange_rate=request.getParameter("exchange_rate")==null?"":request.getParameter("exchange_rate");
	//	String amount_inr=request.getParameter("amount_inr")==null?"":request.getParameter("amount_inr");
		//String hsn_cd=request.getParameter("hsn_cd")==null?"":request.getParameter("hsn_cd");
		//String sac_cd=request.getParameter("sac_cd")==null?"":request.getParameter("sac_cd");
	//	String sac_desc=request.getParameter("sac_desc")==null?"":request.getParameter("sac_desc");
		String pin_code=request.getParameter("pin_code")==null?"":request.getParameter("pin_code");
		String cust_gstin_no1=request.getParameter("cust_gstin_no1")==null?"":request.getParameter("cust_gstin_no1");
		String rowno=request.getParameter("rowno")==null?"":request.getParameter("rowno");
//		String rd_cd=request.getParameter("rd_cd")==null?"":request.getParameter("rd_cd");
//		String rd_val=request.getParameter("rd_val")==null?"":request.getParameter("rd_val");
		String t2=request.getParameter("t2")==null?"":request.getParameter("t2");
		String flag_radio_hid=request.getParameter("flag_radio_hid")==null?"":request.getParameter("flag_radio_hid");
		String sale_no=request.getParameter("sale_no")==null?"":request.getParameter("sale_no");
		//String UAM_no=request.getParameter("UAM_no")==null?"":request.getParameter("UAM_no");
		
	/*SB20180301	String queoption[]=request.getParameterValues("queoption");
		String Amt[]=request.getParameterValues("Amt");
		String rate[]=request.getParameterValues("rate");
		String Qty[]=request.getParameterValues("Qty");
		String hsn_cd[]=request.getParameterValues("hsn_cd");
		String UOM[]=request.getParameterValues("UOM");
		*/
		String igst_tax_amt_hid[]=request.getParameterValues("igst_tax_amt_hid");
		String cgst_tax_amt_hid[]=request.getParameterValues("cgst_tax_amt_hid");
		String sgst_tax_amt_hid[]=request.getParameterValues("cgst_tax_amt_hid");  //SB20180407:same as CGST
		String queoption[]=request.getParameterValues("description");
		String Amt[]=request.getParameterValues("amt");
		String rate[]=request.getParameterValues("rate");
		String Qty[]=request.getParameterValues("qty");
		String hsn_cd[]=request.getParameterValues("hsn_cd");
		String UOM[]=request.getParameterValues("unit");
		String IGST[]=request.getParameterValues("igst");
		String CGST[]=request.getParameterValues("cgst");
		String SGST[]=request.getParameterValues("sgst");
		//String seq_no=request.getParameter("seq_no1")==null?"":request.getParameter("seq_no1");
		String hlpl_seq_no=request.getParameter("hlpl_seq_no1")==null?"":request.getParameter("hlpl_seq_no1");
//		String fin_yr_kk=request.getParameter("fin_yr_kk")==null?"":request.getParameter("fin_yr_kk");
		String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
		String gate_no=request.getParameter("gate_pass_no")==null?"":request.getParameter("gate_pass_no");
		String loading_dt[]=request.getParameterValues("loading_dt");//Hiren_20200520
		String truck_id[]=request.getParameterValues("truck_id");//Hiren_20200520
		
		com.seipl.hazira.dlng.util.escapeSingleQuotes es=new com.seipl.hazira.dlng.util.escapeSingleQuotes();
		address=es.replaceSingleQuotes(address);
		t2=es.replaceSingleQuotes(t2);
		city=es.replaceSingleQuotes(city);
//	//	cust_nm=es.replaceSingleQuotes(cust_nm);
		//cust_pan_no1=es.replaceSingleQuotes(cust_pan_no1);
		Vector sz=new Vector();
		Vector tax_nm=new Vector();
		Vector tax_fact=new Vector();
		Vector Vsac_desc=new Vector();
		String inv_seq_no="";String cust_seq_no="";String new_inv_no="";String GST_INVOICE_SEQ_NO="";
		String abc_msg="";
		String CustAbr=cust_abr; //SB20180406
		try
		{
			HttpSession session = request.getSession();
			String msg1="";
			if(!flag.equalsIgnoreCase("Modify")) 
			{
				if(Integer.parseInt(month)<4){
					Fin_yr=(Integer.parseInt(year)-1)+"-"+year;
				}else{
					Fin_yr=year+"-"+(Integer.parseInt(year)+1);
				}
				//System.out.println("fin_yr--"+fin_yr);
				queryString = "SELECT NVL(MAX(cust_inv_seq_no),'0') FROM DLNG_AC_INVOICE_MST " +
				//SB20171025	  	  "WHERE financial_year='"+Fin_yr+"' AND contract_type in ('C','Y','Z') and customer_cd='"+CUST_CD11+"' and flag!='A' ";
				"WHERE financial_year='"+Fin_yr+"' AND contract_type='"+inv_type+"' and customer_cd='"+CUST_CD11+"' and flag!='A' "; //SB20171025
				System.out.println("STEP-1:DLNG_AC_INVOICE_MST: "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					cust_seq_no = ""+(rset.getInt(1)+1);
				}else{
					cust_seq_no = "1";
				}
				queryString = "SELECT NVL(MAX(inv_seq_no),'0') FROM DLNG_AC_INVOICE_MST " +
				//SB20171025	  	  "WHERE financial_year='"+Fin_yr+"' AND contract_type in ('C','Y','Z') and supplier_cd='"+supp_cd+"' and flag!='A' ";
				  "WHERE financial_year='"+Fin_yr+"' AND contract_type='"+inv_type+"' and supplier_cd='"+supp_cd+"' and flag!='A' "; //SB20171025
				System.out.println("STEP-2:DLNG_AC_INVOICE_MST: "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
					{
						inv_seq_no = ""+(rset.getInt(1)+1);
					}
				else
					{
						inv_seq_no = "1";
					}
			
				String temp[]=New_GST_INVOICE_SEQ_NO.split("/");
				String temp1=inv_seq_no;
				//New_GST_INVOICE_SEQ_NO=temp1+temp[1];

				if(temp1.length()==1) {
					temp1 = "00"+temp1;
				} else if(temp1.length()==2) {
					temp1 = "0"+temp1;
				} else if(temp1.length()==3) {
					temp1 = temp1;
				}
				String CustCode="";
				if(CUST_CD11.length()==1) {
					CustCode = "00"+CUST_CD11;
				} else if(CUST_CD11.length()==2) {
					CustCode = "0"+CUST_CD11;
				} else if(CUST_CD11.length()==3) {
					CustCode = CUST_CD11;
				}
				String FY[]=Fin_yr.split("-");
				FY[0]=FY[0].substring(2,4);
				FY[1]=FY[1].substring(2,4);
				String Adv="";
				if(Supp_Abr.length()>2) 
				{
					Adv=Supp_Abr.substring(1,2);
				}
				if(Supp_Abr.length()>4) 
				{
					Supp_Abr=Supp_Abr.substring(0,3);
				}
				if(inv_type.equals("Z")) //SB20171025
				//SB20180316	New_GST_INVOICE_SEQ_NO="BIPL"+CustCode+flag_radio_hid+"-"+FY[0]+FY[1]+temp1;
					New_GST_INVOICE_SEQ_NO=Supp_Abr+CustCode+flag_radio_hid+"-"+FY[0]+FY[1]+temp1;
				else if(inv_type.equals("A")) 
					//SB20180316 New_GST_INVOICE_SEQ_NO="BI"+CustCode+flag_radio_hid+"-A"+temp1+"-"+FY[0]+FY[1]; //SB20171025
					New_GST_INVOICE_SEQ_NO=Adv+CustCode+flag_radio_hid+"-A"+temp1+"-"+FY[0]+FY[1]; //SB20171025
				System.out.println(Adv+" : "+Supp_Abr);
				INVOICE_SEQ_NO=inv_seq_no;
				cust_seq_no1=cust_seq_no;
			
				if(inv_seq_no.equals(""+Integer.parseInt(temp[0])))
				{
				//	System.out.println("NO CHANGE IN INV-NO: "); //NO ACTION
					System.out.println(INVOICE_SEQ_NO+" New INV-NO: "+inv_seq_no); //NO ACTION
				}
				else
				{				
				//	System.out.println("CHANGE IN INV-NO: "+New_GST_INVOICE_SEQ_NO); //NO ACTION
					msg1="(During preparation : Inv Sequence No - "+old_inv_no+" , During Submission :  Inv Sequence No - "+New_GST_INVOICE_SEQ_NO+")";
				}
		//	}
			} //End of Insert Mode
			if(flag.equalsIgnoreCase("Modify")) 
			{
				if(!net_inr.equals("0"))
				{
				String queryString="UPDATE DLNG_AC_INVOICE_MST SET INVOICE_DT=TO_DATE('"+inv_dt+"','DD/MM/YYYY'),"
						+ "GATE_PASS_NO='"+gate_no+"',SALE_NO='"+sale_no+"', " //SB20180405
						+ "TAX_AMT_INR='"+tax_amt+"',NET_AMT_INR='"+net_inr+"' ,GROSS_AMT_INR='"+gross_amt+"',EMP_CD='"+user_cd+"' " +
						", INV_FORMAT_TYPE='"+pdf_format_type+"' , CUST_GSTIN_NO='"+cust_gstin_no1+"' " +
						" where financial_year='"+Fin_yr+"' AND MAPPING_ID='"+mapping_id+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' and new_inv_seq_no='"+New_GST_INVOICE_SEQ_NO+"' ";
					System.out.println("INV-QRY0001A:UPDATE: "+queryString);
					stmt.executeUpdate(queryString);
				}
				else
				{
					String queryString="DELETE FROM DLNG_AC_INVOICE_MST " +
					" where financial_year='"+Fin_yr+"' AND MAPPING_ID='"+mapping_id+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' and new_inv_seq_no='"+New_GST_INVOICE_SEQ_NO+"' ";
					System.out.println("INV-QRY0001A:UPDATE: "+queryString);
					stmt.executeUpdate(queryString);
				}
			}
			else
			{				
				String queryString="INSERT INTO DLNG_AC_INVOICE_MST (COMP_CD,INV_SEQ_NO,SUPPLIER_CD,CUSTOMER_CD,MAPPING_ID, PLANT_SEQ_NO,CONTRACT_TYPE,PERIOD_END_DT,"
						+ "FINANCIAL_YEAR,PERIOD_START_DT,CUST_INV_SEQ_NO,GROSS_AMT_INR,TAX_AMT_INR,NET_AMT_INR," +
						" FLAG,INVOICE_DT,TOTAL_QTY,SALE_PRICE,NEW_INV_SEQ_NO,EMP_CD,ENT_DT,gross_amt_usd,INV_FORMAT_TYPE, CUST_GSTIN_NO"+
						" ,CUSTOMER_ABR,CUSTOMER_NM,CUSTOMER_ADDR,CUSTOMER_STATE_CD,CUSTOMER_PAN_NO,SUPPLIER_STATE_CD " +
						" ,CUST_PIN_CODE,CUST_CITY,GATE_PASS_NO,SALE_NO,CHECKED_FLAG,CHECKED_BY,CHECKED_DT,APPROVED_FLAG,APPROVED_BY,APPROVED_DT )"  //SB20180405
						+ " VALUES ('"+comp_cd+"','"+INVOICE_SEQ_NO+"','"+supp_cd+"','"+CUST_CD11+"','"+mapping_id+"','"+cust_plant_cd+"','"+inv_type+"',to_date('"+bill_end_dt+"','dd/mm/yyyy'),"
						+ "'"+Fin_yr+"',to_date('"+bill_start_dt+"','dd/mm/yyyy'),'"+cust_seq_no1+"','"+gross_amt+"','"+tax_amt+"','"+net_inr+"',"
						+ "'O',to_date('"+inv_dt+"','dd/mm/yyyy'),'0','0','"+New_GST_INVOICE_SEQ_NO+"',"
						+ "'"+user_cd+"',sysdate,'0', '"+pdf_format_type+"', '"+cust_gstin_no1+"'" 
						+ " ,'"+CustAbr+"','"+t2+"','"+address+"','"+state+"','"+cust_pan_no1+"','"+supp_state_cd+"'," + //SB20180405
						"'"+pin_code+"','"+city+"','"+gate_no+"','"+sale_no+"','Y','"+user_cd+"',sysdate,'Y','"+user_cd+"',sysdate)"; 
				System.out.println("STEP-3: INV-QRY0002B:INSERT: "+queryString);
				stmt.executeUpdate(queryString);	
			}
			
			String tt="";String am="";String cgst="";String sgst="";String igst="";String tax_cd="";
			if(tax_sz.equalsIgnoreCase("2")) 
			{
				for(int k=0;k<Integer.parseInt(tax_sz);k++) {
					String tax_str=request.getParameter("tax_str"+k)==null?"":request.getParameter("tax_str"+k);
					String tax_amount=request.getParameter("tax_amts"+k)==null?"":request.getParameter("tax_amts"+k);
					tax_cd="C";
					System.out.println(" (2) tax_amts--"+tax_amount);
					if(k==0){
						tt="CGST-"+tax_str+"%";
						cgst=tax_str;
						
					}else{
						tt+="SGST-"+tax_str+"%";
						sgst=tax_str;
					}	
				}
			}
			else
			{
				tax_cd="I";
				String tax_str=request.getParameter("tax_str0")==null?"":request.getParameter("tax_str0");
				String tax_amts=request.getParameter("tax_amts")==null?"":request.getParameter("tax_amts");
				
				tt="IGST-"+tax_str+"%";
				igst=tax_str;	
				System.out.println(" (1) tax_amts--"+tax_amts);
			}

			///////////////////////////////////DLNG_SERVICE_INVOICE_DTL entry///////////////////////////////////////////////////////////////////
			System.out.println("OTHER INVOICE: >>>>>>>>:INV-SEQ-NO: "+INVOICE_SEQ_NO);
			String queryString1="SELECT COUNT(*) FROM DLNG_AC_INVOICE_DTL WHERE financial_year='"+Fin_yr+"'"
					+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' ";		
			System.out.println("STEP-4:DLNG_AC_INVOICE_DTL: "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					count=rset1.getInt(1);
				}
		int line_no=0;
		if(count==0) 
		{
			System.out.println(Qty.length+" :No of Items: "+line_item_chk.length);
			for(int k=0;k<Qty.length;k++) 
				{ System.out.println("LineItemChecked: "+line_item_chk[k]);
					if(line_item_chk[k].equals("Y")) {
						line_no++;
						if(tax_sz.equalsIgnoreCase("2")) 
							tt="CGST-"+CGST[k]+"%SGST-"+SGST[k]+"%";
						else
							tt="IGST-"+IGST[k]+"%";
						String ab=queoption[k];
						String abc=es.replaceSingleQuotes(ab);
						
						String ab_1=UOM[k];
						String abc_1=es.replaceSingleQuotes(ab_1);
						if(flag_radio_hid.equalsIgnoreCase("P")) {
							String queryString3="INSERT INTO DLNG_AC_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
								+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
								+" QUANTITY,RATE"
								+ ",cargo_amount,rate_cgst,rate_sgst,rate_igst,tax_cd,hsn_code"
								+"flag_sac,UAM_NO,SUPPLIER_CD, MAPPING_ID" +
								", IGST_AMT, CGST_AMT, SGST_AMT, LINE_NO,TRUCK_ID,LOADING_DT)" //SB20180220
								+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"','"+tt+"',TO_DATE('"+inv_dt+"','dd/mm/yyyy'),'"+abc+"',"
								+"'"+Qty[k]+"','"+rate[k]+"','"+Amt[k]+"','"+CGST[k]+"'"
								+ ",'"+SGST[k]+"','"+IGST[k]+"','"+tax_cd+"','"+hsn_cd[k]+"'," 
								+" '"+flag_radio_hid+"','"+abc_1+"','"+supp_cd+"','"+mapping_id+"'" +
								",'"+igst_tax_amt_hid[k]+"','"+cgst_tax_amt_hid[k]+"','"+sgst_tax_amt_hid[k]+"',"+line_no+",'"+truck_id[k]+"',to_date('"+loading_dt[k]+"','dd/mm/yyyy'))";  //SB20180220
					System.out.println("STEP-5P:INSERT: DLNG_AC_INVOICE_DTL:"+queryString3);
						stmt.executeUpdate(queryString3);
						}
						else 
						{
							String queryString3="INSERT INTO DLNG_AC_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
								+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
								+" QUANTITY,RATE"
								+ ",cargo_amount,rate_cgst,rate_sgst,rate_igst,tax_cd,sac_code"
								+ ",flag_sac,UAM_NO,SUPPLIER_CD, MAPPING_ID" +
								", IGST_AMT, CGST_AMT, SGST_AMT, LINE_NO,TRUCK_ID,LOADING_DT)" //SB20180220
								+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
								+ "'"+Fin_yr+"','"+tt+"',TO_DATE('"+inv_dt+"','dd/mm/yyyy'),'"+abc+"'," +
								"'"+Qty[k]+"','"+rate[k]+"','"+Amt[k]+"','"+CGST[k]+"'"
								+ ",'"+SGST[k]+"','"+IGST[k]+"','"+tax_cd+"','"+hsn_cd[k]+"'," +
								"'"+flag_radio_hid+"','"+abc_1+"','"+supp_cd+"','"+mapping_id+"'" +
								",'"+igst_tax_amt_hid[k]+"','"+cgst_tax_amt_hid[k]+"','"+sgst_tax_amt_hid[k]+"',"+line_no+",'"+truck_id[k]+"',to_date('"+loading_dt[k]+"','dd/mm/yyyy'))";  //SB20180220
							System.out.println("STEP-5S:INSERT: DLNG_AC_INVOICE_DTL:"+queryString3);
							stmt.executeUpdate(queryString3);
						}
					}
				}
			}
			else
			{
				String queryString2="Delete FROM DLNG_AC_INVOICE_DTL WHERE financial_year='"+Fin_yr+"'"
						+ " and contract_type='"+inv_type+"' and inv_seq_no='"+INVOICE_SEQ_NO+"' ";
						System.out.println("STEP-6:DELETE: DLNG_AC_INVOICE_DTL: "+queryString2);			
						stmt1.executeUpdate(queryString2);
			if(!net_inr.equals("0"))
			{		
				for(int k=0;k<Qty.length;k++) 
				{
					if(line_item_chk[k].equals("Y")) {
						
					
						line_no++;
						String ab=queoption[k];
						String abc=es.replaceSingleQuotes(ab);
									
						String ab_1=UOM[k];
						String abc_1=es.replaceSingleQuotes(ab_1);
						if(tax_sz.equalsIgnoreCase("2")) 
							tt="CGST-"+CGST[k]+"%SGST-"+SGST[k]+"%";
						else
							tt="IGST-"+IGST[k]+"%";			
						if(flag_radio_hid.equalsIgnoreCase("P")) 
						{
							if(!rate[k].equals("0"))
							{
								String queryString3="INSERT INTO DLNG_AC_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
									+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
									+" QUANTITY,RATE"
									+ ",cargo_amount,rate_cgst,rate_sgst,rate_igst,tax_cd,hsn_code"
									+"flag_sac,UAM_NO,SUPPLIER_CD, MAPPING_ID" +
									", IGST_AMT, CGST_AMT, SGST_AMT, LINE_NO,TRUCK_ID,LOADING_DT)" //SB20180220
									+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
									+ "'"+Fin_yr+"','"+tt+"',TO_DATE('"+inv_dt+"','dd/mm/yyyy'),'"+abc+"',"
									+"'"+Qty[k]+"','"+rate[k]+"','"+Amt[k]+"','"+CGST[k]+"'"
									+ ",'"+SGST[k]+"','"+IGST[k]+"','"+tax_cd+"','"+hsn_cd[k]+"'," 
									+" '"+flag_radio_hid+"','"+abc_1+"','"+supp_cd+"','"+mapping_id+"'" +
									",'"+igst_tax_amt_hid[k]+"','"+cgst_tax_amt_hid[k]+"','"+sgst_tax_amt_hid[k]+"',"+line_no+",'"+truck_id[k]+"',to_date('"+loading_dt[k]+"','dd/mm/yyyy'))";  //SB20180220
								System.out.println("STEP-7:INSERT:DLNG_AC_INVOICE_DTL: "+queryString3);
								stmt.executeUpdate(queryString3);
							}
						}
						else 
						{
							if(!rate[k].equals("0"))
							{
								String queryString3="INSERT INTO DLNG_AC_INVOICE_DTL (CONTRACT_TYPE,INV_SEQ_NO,"
									+ "FINANCIAL_YEAR,tax_details,eff_dt,ITEM_DESCRIPTION,"
									+" QUANTITY,RATE"
									+ ",cargo_amount,rate_cgst,rate_sgst,rate_igst,tax_cd,sac_code"
									+ ",flag_sac,UAM_NO,SUPPLIER_CD, MAPPING_ID" +
									", IGST_AMT, CGST_AMT, SGST_AMT, LINE_NO,TRUCK_ID,LOADING_DT)" //SB20180220
									+ " VALUES ('"+inv_type+"','"+INVOICE_SEQ_NO+"',"
									+ "'"+Fin_yr+"','"+tt+"',TO_DATE('"+inv_dt+"','dd/mm/yyyy'),'"+abc+"'," +
									"'"+Qty[k]+"','"+rate[k]+"','"+Amt[k]+"','"+CGST[k]+"'"
									+ ",'"+SGST[k]+"','"+IGST[k]+"','"+tax_cd+"','"+hsn_cd[k]+"'," +
									"'"+flag_radio_hid+"','"+abc_1+"','"+supp_cd+"','"+mapping_id+"'" +
									",'"+igst_tax_amt_hid[k]+"','"+cgst_tax_amt_hid[k]+"','"+sgst_tax_amt_hid[k]+"',"+line_no+",'"+truck_id[k]+"',to_date('"+loading_dt[k]+"','dd/mm/yyyy'))";  //SB20180220
								System.out.println("STEP-7A:INSERT:DLNG_AC_INVOICE_DTL: "+queryString3);
								stmt.executeUpdate(queryString3);
							}
						}
					}
				}
			}  ///else
			}

			//////////////////////////////////////////////////////////////////////////////////////////////////////
				if(!flag.equals("Modify")) 
				{	
				msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Submitted Successfully !!! "+msg1;			
				}
				else
				{
					msg = "Billing Details Of Invoice No-"+New_GST_INVOICE_SEQ_NO+"  Has Been Modified Successfully !!!";
				}
			
			dbcon.commit();
			url = "../general_inv/frm_other_invoices_Z.jsp?msg="+msg+"&year="+year+"&month="+month+"&inv_type="+inv_type+"&supplier_cd="+supp_cd+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;;	
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Invoice Details Submission Failed !!!";
			e.printStackTrace();
			System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../general_inv/frm_other_invoices_Z.jsp?msg="+msg+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;;	
		}		
	}//End Of Method submitBillingDetails() ...
///////////////////////////////////////////////////////////////////////////////////
	
	
		
}//End Of Class Frm_Sales_Invoice ...