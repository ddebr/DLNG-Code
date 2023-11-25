package com.seipl.hazira.dlng.sales_invoice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.report.DataBean_Advacne_Tracker;
import com.seipl.hazira.dlng.util.RuntimeConf;

@WebServlet("/servlet/Frm_dr_cr_note")
public class Frm_dr_cr_note extends  HttpServlet{

	public String servletName = "Frm_dr_cr_note";
	public String methodName = "";
	public String option = "";
	public String url = "";
	public String form_name = "";
	public String msg = "",err_msg = "";
	int count = 0;
	public static  Connection dbcon;
	private static String queryString = null,queryString2="";
	private static String query = null;
	private static String query1 = null;
	private static String query2 = null;
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
	public String approve_permission = "N";
	public String audit_permission = "N";
	
	public String form_id = "0";
	public String form_nm = "";
	public String queryString1 = "";
	public String tds_percent = ""; //Hiren_20210709
	NumberFormat nf = new DecimalFormat("###########0.00");
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
			
			//System.out.println("Runtime config"+RuntimeConf.security_database);
			if(ds != null)
			{
				//System.out.println("Set Dbcon");
				dbcon = ds.getConnection();				
			}
			else
			{
				System.out.println("Data Source Not Found - Invoice Module Error");
			}
			if(dbcon != null)
			{
				dbcon.setAutoCommit(false);
				stmt = dbcon.createStatement(); 
				stmt1 = dbcon.createStatement();
			}
			
			option=req.getParameter("option")==null?"":req.getParameter("option");
			
			write_permission = req.getParameter("write_permission")==null?"N":req.getParameter("write_permission");
			delete_permission = req.getParameter("delete_permission")==null?"N":req.getParameter("delete_permission");
			print_permission = req.getParameter("print_permission")==null?"N":req.getParameter("print_permission");
			approve_permission = req.getParameter("approve_permission")==null?"N":req.getParameter("approve_permission");
			audit_permission = req.getParameter("audit_permission")==null?"N":req.getParameter("audit_permission");
			
			form_id = req.getParameter("form_id")==null?"0":req.getParameter("form_id");
			form_nm = req.getParameter("form_nm")==null?"":req.getParameter("form_nm");
			
//			System.out.println("DLNG: option****: "+option);
			createColumn();
			if(option.equals("SetDrCrNote")) {
				InsertSetDrCrNote(req);
			}
			else if(option.equalsIgnoreCase("submitDebitCreditNote_gen"))
			{
				submitDebitCreditNote_gen(req,res); 
			}else if(option.equalsIgnoreCase("Approve_Credit")) //SB20160602
			{
				Approve_Credit(req,res); //Defined By Samik Shah On 21st January, 2011 ...
			}
		}		   
		catch(Exception e)
		{
			e.printStackTrace();
			url="../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=contract_master&formname="+form_name;
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
			if(rset1!=null)
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
			if(rset2!=null)
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
			if(rset3!=null)
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
			if(rset4!=null)
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

	public void createColumn()throws SQLException
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'DR_CR_TCS_FLAG' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table DLNG_DR_CR_NOTE add DR_CR_TCS_FLAG CHAR(1 BYTE)";
				stmt.executeUpdate(query);
				dbcon.commit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void InsertSetDrCrNote(HttpServletRequest req)throws SQLException,IOException {
		msg = "";
		err_msg = "";
		methodName = "InsertSetDrCrNote()";
		form_name = "frm_set_debit_credit_note.jsp";
		
		String customer_cd = req.getParameter("customer_cd")==null?"":req.getParameter("customer_cd");
		String con_type[] = req.getParameterValues("con_type");
		String hlpl_no[]		= req.getParameterValues("hlpl_seq_no");
		String fin_yr[] = req.getParameterValues("fin_yr");
		String inv_dt[] =  req.getParameterValues("inv_dt");
		String cust_nm[]   = req.getParameterValues("cust_nm");
//		String reason[] = req.getParameterValues("reason_frm");				
		//remark = obj.replaceSingleQuotes(remark);
		String form_id = req.getParameter("form_id")==null?"":req.getParameter("form_id");
		String form_nm = req.getParameter("form_nm")==null?"":req.getParameter("form_nm");
		String chkval[] = req.getParameterValues("chkval");
		String criteria[] = req.getParameterValues("criteria");
		String new_inv_no [] = req.getParameterValues("new_inv_no");
		
		String bill_dt = req.getParameter("bill_dt1")==null?"":req.getParameter("bill_dt1");
		String from_dt = req.getParameter("from_dt1")==null?"":req.getParameter("from_dt1");
		String to_dt = req.getParameter("to_dt1")==null?"":req.getParameter("to_dt1");
		String fy = req.getParameter("fy1")==null?"":req.getParameter("fy1");
		String comp_cd = req.getParameter("comp_cd")==null?"":req.getParameter("comp_cd");
		String emp_cd = req.getParameter("emp_cd")==null?"":req.getParameter("emp_cd");
		
		String modCd = req.getParameter("modCd")==null?"":req.getParameter("modCd");
		String formId = req.getParameter("formId")==null?"":req.getParameter("formId");
		String subModUrl = req.getParameter("subModUrl")==null?"":req.getParameter("subModUrl");
		
		//for multi dr/cr notes
		String dr_cr_cnt [] = req.getParameterValues("dr_cr_cnt");
		int insCnt = 0 , subInsCnt = 0 ;
		try {
			HttpSession session = req.getSession();
			int k = 0; 
			for( int i=0;i<chkval.length;i++)
			{
				if(chkval[i].toString().equalsIgnoreCase("Y"))
				{
					int seq_no = 1 ;
					String maxSeqSql="select nvl(max(seq_no+1),1) from DLNG_DR_CR_DTL where "
							+ " CUSTOMER_CD = '"+customer_cd+"' and CON_TYPE = '"+con_type[i]+"' and HLPL_INV_SEQ_NO = '"+hlpl_no[i]+"' and FINANCIAL_YEAR = '"+fin_yr[i]+"'";
					rset = stmt.executeQuery(maxSeqSql);
					if(rset.next()) {
						seq_no = rset.getInt(1);
					}
					
					queryString1 = "INSERT INTO DLNG_DR_CR_DTL(CUSTOMER_CD,CON_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
							       "INVOICE_DT,REMARK,EMP_CD," +
							       "ENT_DT,SEQ_NO)VALUES("+customer_cd+",'"+con_type[i]+"', "+hlpl_no[i]+"," +
								   "'"+fin_yr[i]+"',TO_DATE('"+inv_dt[i]+"','DD/MM/YYYY'), " +
								   " '"+criteria[i]+"', "+emp_cd+", sysdate,'"+seq_no+"')";			
//					System.out.println("CR-DR: No#:INSERT:--Hiren-- "+queryString1);
					insCnt = stmt1.executeUpdate(queryString1);
				}
				
				for(int j = 0 ; j < Integer.parseInt(dr_cr_cnt[i]+""); j ++) {
					
					String drcr_chkval = req.getParameter("drcr_chkval"+k)==null?"":req.getParameter("drcr_chkval"+k);
					String drcr_criteria = req.getParameter("drcr_criteria"+k)==null?"":req.getParameter("drcr_criteria"+k);
					String dr_cr_no = req.getParameter("dr_cr_no"+k)==null?"":req.getParameter("dr_cr_no"+k);
					String dr_cr_fin_year = req.getParameter("dr_cr_fin_year"+k)==null?"":req.getParameter("dr_cr_fin_year"+k);
					String dr_cr_dt = req.getParameter("dr_cr_dt"+k)==null?"":req.getParameter("dr_cr_dt"+k);
					String dr_cr_doc_no = req.getParameter("dr_cr_doc_no"+k)==null?"":req.getParameter("dr_cr_doc_no"+k);
					
					System.out.println("drcr_chkval*******"+drcr_chkval);
					if(drcr_chkval.equalsIgnoreCase("Y") && !drcr_criteria.equalsIgnoreCase("")) {
						
						int seq_no = 1 ;
						String maxSeqSql="select nvl(max(seq_no+1),1) from DLNG_DR_CR_DTL where "
								+ " CUSTOMER_CD = '"+customer_cd+"' "
								+ " and CON_TYPE = '"+con_type[i]+"'"
								+ " and HLPL_INV_SEQ_NO = '"+dr_cr_no+"' "
								+ " and FINANCIAL_YEAR = '"+dr_cr_fin_year+"' "
								+ " and NEW_INV_SEQ_NO = '"+new_inv_no[i]+"'";
						rset = stmt.executeQuery(maxSeqSql);
						if(rset.next()) {
							seq_no = rset.getInt(1);
						}
						
						queryString1 = "INSERT INTO DLNG_DR_CR_DTL(CUSTOMER_CD,CON_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
							       "INVOICE_DT,REMARK,EMP_CD," +
							       "ENT_DT,SEQ_NO,NEW_INV_SEQ_NO,DR_CR_DOC_NO)VALUES("+customer_cd+",'"+con_type[i]+"', "+dr_cr_no+"," +
								   "'"+dr_cr_fin_year+"',TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY'), " +
								   " '"+drcr_criteria+"', "+emp_cd+", sysdate,'"+seq_no+"','"+new_inv_no[i]+"','"+dr_cr_doc_no+"')";			
						System.out.println("CR-DR: No#:INSERT:--SUB DR/CR-- "+queryString1);
						subInsCnt = stmt1.executeUpdate(queryString1);
					}
				
				k++;
				}
			}
			if(subInsCnt > 0 || insCnt > 0) {
				dbcon.commit();
				msg="Dr/Cr Note Submitted Successfully!!!";
			}else {
				dbcon.rollback();
				err_msg = "Error : No Record Found";
			}
			url = "../sales_invoice/frm_mst_prepareInvoice.jsp?bscode="+customer_cd+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&fy="+fy+"&msg="+msg+"&error_msg="+err_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;

			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		}catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
//			msg = "Note Submission Failed !!!";
			e.printStackTrace();
			err_msg = "Error : Submission Failed due to: "+e.getMessage();
			////System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../sales_invoice/frm_mst_prepareInvoice.jsp?bscode="+customer_cd+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&fy="+fy+"&msg="+msg+"&error_msg="+err_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
			//////System.out.println("--11-->"+url);
		}
	}	
	
	public void submitDebitCreditNote_gen(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException
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
		////System.out.println("---due_dt---"+due_dt);
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
//		remark = obj.replaceSingleQuotes(remark);
		String gross_amt_usd=request.getParameter("gross_amt_usd1")==null?"":request.getParameter("gross_amt_usd1");
		//////System.out.println("gross_amt_usd-----"+gross_amt_usd);
		String tax_struc_cd=request.getParameter("tax_struc_cd")==null?"":request.getParameter("tax_struc_cd");
		String dr_cr_doc_no=request.getParameter("dr_cr_doc_no")==null?"":request.getParameter("dr_cr_doc_no");
		String tax_amt_inr=request.getParameter("tax_amt_inr")==null?"":request.getParameter("tax_amt_inr");
		String dr_cr_gross_usd=request.getParameter("dr_cr_gross_usd")==null?"":request.getParameter("dr_cr_gross_usd");
		String dr_cr_gross_amt=request.getParameter("dr_cr_gross_amt")==null?"":request.getParameter("dr_cr_gross_amt");
		String dr_cr_net_amt=request.getParameter("dr_cr_net_amt")==null?"0":request.getParameter("dr_cr_net_amt");
		
		String dr_cr_fin_year= request.getParameter("dr_cr_fin_year")==null?"":request.getParameter("dr_cr_fin_year");
		String bill_dt= request.getParameter("bill_dt")==null?"":request.getParameter("bill_dt");
		String from_dt= request.getParameter("from_dt")==null?"":request.getParameter("from_dt");
		String to_dt= request.getParameter("to_dt")==null?"":request.getParameter("to_dt");
		String form_id = request.getParameter("form_id")==null?"":request.getParameter("form_id");
		String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
		String fin_yr_format = request.getParameter("fin_yr_format")==null?"":request.getParameter("fin_yr_format");
		String dr_cr_sale_rate = request.getParameter("dr_cr_sale_rate1")==null?"":request.getParameter("dr_cr_sale_rate1");
		String dr_cr_gross_amt_val = request.getParameter("dr_cr_gross_amt_val")==null?"":request.getParameter("dr_cr_gross_amt_val");
		String dr_cr_net_amt_val = request.getParameter("dr_cr_net_amt_val")==null?"":request.getParameter("dr_cr_net_amt_val");
		String chkval = request.getParameter("chkval")==null?"":request.getParameter("chkval");
		
		String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
		
		//for TCS Part
		String tcs_flag = request.getParameter("tcs_app_flag")==null?"":request.getParameter("tcs_app_flag");
		String tcs_amt = request.getParameter("tcs_amt")==null?"0":request.getParameter("tcs_amt");
		String payduedt = request.getParameter("payduedt")==null?"":request.getParameter("payduedt");
		String split_qty=request.getParameter("split_qty")==null?"":request.getParameter("split_qty");
		String Stax_str=request.getParameter("Stax_str")==null?"":request.getParameter("Stax_str");
		String item_desc=request.getParameter("item_desc")==null?"":request.getParameter("item_desc");
		
		String dr_cr_tax_amt=request.getParameter("dr_cr_tax_amt")==null?"":request.getParameter("dr_cr_tax_amt");//Hiren_2020119
		String chkTcs = request.getParameter("chkTcs")==null?"":request.getParameter("chkTcs");//Hiren_20210209
		String dr_cr_qty = request.getParameter("dr_cr_qty")==null?"":request.getParameter("dr_cr_qty");//Hiren_20210212
		String diff_qty = request.getParameter("diff_qty")==null?"":request.getParameter("diff_qty");//Hiren_20210212
		String diff_tax= request.getParameter("tax_")==null?"":request.getParameter("tax_");//Hiren_20210217
		
		String tds_amt = request.getParameter("tds_amt")==null?"":request.getParameter("tds_amt");//Hiren_20210625 
		String tds_app_flag = request.getParameter("tds_app_flag")==null?"":request.getParameter("tds_app_flag");//Hiren_20210625
		
		String sysdate = request.getParameter("sysdate")==null?"":request.getParameter("sysdate");//Hiren_20210709
		//for TMS invoices
		String dr_cr_inr_mmbtu = request.getParameter("dr_cr_inr_mmbtu")==null?"":request.getParameter("dr_cr_inr_mmbtu");
		String diff_inr_mmbtu = request.getParameter("diff_inr_mmbtu")==null?"":request.getParameter("diff_inr_mmbtu");
		String dr_cr_inr_km = request.getParameter("dr_cr_inr_km")==null?"":request.getParameter("dr_cr_inr_km");
		String diff_inr_km = request.getParameter("diff_inr_km")==null?"":request.getParameter("diff_inr_km");
		String dr_cr_tax_rate = request.getParameter("dr_cr_tax")==null?"":request.getParameter("dr_cr_tax");
		String diff_tax_str = request.getParameter("diff_tax_str")==null?"":request.getParameter("diff_tax_str");
		String dr_cr_seq_no = request.getParameter("dr_cr_seq_no")==null?"":request.getParameter("dr_cr_seq_no");
		String dr_cr_ref_inv_no = request.getParameter("dr_cr_ref_inv_no")==null?"":request.getParameter("dr_cr_ref_inv_no");
		////////////////
		if(criteria.contains("DIFF-TAX") && !con_type.equalsIgnoreCase("V")) {
			diff_qty = split_qty;
		}
		
		
		if(!Stax_str.equals("")){
			Stax_str=Stax_str.substring(0,Stax_str.length()-1);
		}
		String modify = "N";
//		System.out.println("------dr_cr_seq_no------->"+dr_cr_seq_no);
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
			//RG20200401 changed for new sequence number
			String finyear="";String date_flag="N";
			if(!dr_cr_fin_year.equals("")){
				finyear=dr_cr_fin_year.replace("-","");
			}
			String date="20202021";
			 int newdt=Integer.parseInt(finyear);
			 int date_latest=20202021;
			 if(newdt<date_latest){
				 date_flag="Y";
			 }
			//
			
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");		
			String msg="";
			
			
			String constant=""; String dr_cr_noFormat="";
			queryString = "SELECT DR_CR_NO,DR_CR_FIN_YEAR FROM DLNG_DR_CR_NOTE "
					+ " WHERE customer_cd="+customer_cd+" "
					+ " AND plant_seq_no="+plant_no+""
					+ " AND fgsa_no="+fgsa_no+" AND sn_no="+sn_no+" "
					+ " AND contract_type='"+con_type+"'"
					+ " AND financial_year='"+fin_yr+"' "
					+ " AND hlpl_inv_seq_no='"+Integer.parseInt(hlpl_no)+"' "
					+ " AND seq_no = '"+dr_cr_seq_no+"' "
					+ " AND (NEW_INV_SEQ_NO = '"+dr_cr_ref_inv_no+"' or NEW_INV_SEQ_NO is null )";			
//		System.out.println("CR-DR: No#:SELECT: "+queryString);
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
				modify = "Y" ; //Hiren_20210212
					queryString1 = "DELETE FROM DLNG_DR_CR_NOTE WHERE customer_cd="+customer_cd+" AND plant_seq_no='"+plant_no+"' AND " +
				   "fgsa_no='"+fgsa_no+"' AND sn_no='"+sn_no+"' AND contract_type='"+con_type+"' AND " +
				   "financial_year='"+fin_yr+"' AND hlpl_inv_seq_no='"+Integer.parseInt(hlpl_no)+"' "
				   + " and seq_no = '"+dr_cr_seq_no+"' "
				  + "  AND (NEW_INV_SEQ_NO = '"+dr_cr_ref_inv_no+"' or NEW_INV_SEQ_NO is null) ";		
//					System.out.println("CR-DR: No#:DELETE: "+queryString1);				
					stmt1.executeUpdate(queryString1);	
			}
		}
		else
		{
			queryString2 = "SELECT MAX(DR_CR_NO) FROM DLNG_DR_CR_NOTE"
						+ " WHERE DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' "
						+ " AND DR_CR_FLAG='"+cr_dr+"'"
						+ " AND CONTRACT_TYPE ='"+con_type+"' " ; 
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
		if(cr_dr.equalsIgnoreCase("CR") && (con_type.equalsIgnoreCase("S") || con_type.equalsIgnoreCase("L"))){
			constant="CT";
		}else if(cr_dr.equalsIgnoreCase("DR") && (con_type.equalsIgnoreCase("S") || con_type.equalsIgnoreCase("L"))){
			constant="DT";
		}else if(cr_dr.equalsIgnoreCase("CR") && con_type.equalsIgnoreCase("V")){
			constant="CTMS";
		}else if(cr_dr.equalsIgnoreCase("DR") && con_type.equalsIgnoreCase("V")){
			constant="DTMS";
		}
		
		if(con_type.equals("S") || con_type.equals("L")){
			if(Integer.parseInt(dr_cr_no)<10)
				dr_cr_noFormat ="00"+dr_cr_no;  
			else if(Integer.parseInt(dr_cr_no)<100)
				dr_cr_noFormat ="0"+dr_cr_no; 
			else if(Integer.parseInt(dr_cr_no)<1000) 
				dr_cr_noFormat = dr_cr_no; 
			
		}else{
			if(date_flag.equalsIgnoreCase("Y")){
				if(Integer.parseInt(dr_cr_no)<10)
					dr_cr_noFormat ="00"+dr_cr_no;  
				else if(Integer.parseInt(dr_cr_no)<100)
					dr_cr_noFormat ="0"+dr_cr_no; 
				else if(Integer.parseInt(dr_cr_no)<1000) 
					dr_cr_noFormat = dr_cr_no; 
				
			}else{
				if(Integer.parseInt(dr_cr_no)<10)
					dr_cr_noFormat ="000"+dr_cr_no;  
				else if(Integer.parseInt(dr_cr_no)<100)
					dr_cr_noFormat ="00"+dr_cr_no; 
				else if(Integer.parseInt(dr_cr_no)<1000) 
					dr_cr_noFormat = "0"+dr_cr_no;
				else if(Integer.parseInt(dr_cr_no)<10000) 
					dr_cr_noFormat = dr_cr_no;
				
			}
		}
		
		
		String t[]=fin_yr.split("-");
		String Fy1=t[0].substring(2,4);
		String Fy2=t[1].substring(2,4);
		
		dr_cr_doc_no = constant+dr_cr_noFormat+"/"+t[0].substring(2,4)+"-"+t[1].substring(2,4); 
				
		if(criteria.contains("DIFF-EXG")){
			if(cr_dr.equalsIgnoreCase("cr")){
				FlagValue="X";
			}
		}
		if(criteria.contains("DIFF-QTY")){
			if(cr_dr.equalsIgnoreCase("cr")){
				FlagValue="X";
			}
		}
		if(criteria.contains("DIFF-PRICE")){
			if(cr_dr.equalsIgnoreCase("cr")){
				FlagValue="X";
			}
		}
		if(criteria.contains("DIFF-TAX") || criteria.contains("DIFF-INRMMBTU") || criteria.contains("DIFF-INRKM") || criteria.contains("DIFF-LUMPSUM") || criteria.contains("DIFF-KM")){
			if(cr_dr.equalsIgnoreCase("cr")){
				FlagValue="X";
			}
		}
		if(criteria.contains("REV_INV"))
		{
			FlagValue="X"; // for diff-exg,diff-price,diff-qty,rev_inv ....
			
			dr_cr_gross_amt = gross_amt;
			dr_cr_exg_rt = exg_rt;
			dr_cr_qty = qty;
			dr_cr_tax_amt = tax_amt_inr;
			dr_cr_net_amt = net_amt;
			
		}
		String duedt="";
//		System.out.println("duedt****"+cr_dr+"****"+payduedt);
		if(cr_dr.equalsIgnoreCase("dr")){
			duedt=payduedt;
		}else{
			duedt=due_dt;
		}
		String selDrCr="";
//		System.out.println("dr_cr_seq_no****"+dr_cr_seq_no);
		
			queryString1 = "INSERT INTO DLNG_DR_CR_NOTE(SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR," +
		       "SN_REV_NO,FGSA_REV_NO,INVOICE_DT,TOTAL_QTY,SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,CRITERIA,DUE_DT,EXCHG_RATE_VALUE," +
		       "DR_CR_FLAG,DR_CR_NO,DR_CR_DT,DIFF_AMT,DR_CR_EXG_RATE,REMARK,EMP_CD," +
		       "ENT_DT,FLAG,GROSS_AMT_USD,TAX_STRUCT_CD,DR_CR_DOC_NO,TAX_AMT_INR,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
		       "DR_CR_GROSS_AMT_USD,DR_CR_FIN_YEAR,DR_CR_SALE_RATE,TAX_REMARK,DR_CR_TCS_FLAG,DR_CR_QTY,DIFF_QTY,"
		       + " DIFF_TAX,ITEM_DESCRIPTION,DR_CR_INR_MMBTU,DR_CR_INR_KM,DIFF_INR_MMBTU,DIFF_INR_KM,DR_CR_TAX_RATE,DIFF_TAX_STR,SEQ_NO,NEW_INV_SEQ_NO)"
		       + " VALUES("+sn_no+","+fgsa_no+","+customer_cd+","+Integer.parseInt(plant_no)+",'"+con_type+"', "+Integer.parseInt(hlpl_no)+"," +
			   "'"+fin_yr+"',"+fgsa_rev_no+","+sn_rev_no+",TO_DATE('"+inv_dt+"','DD/MM/YYYY'), " +
			   "'"+qty+"','"+sale_rate+"','"+gross_amt+"','"+net_amt+"','"+criteria+"', TO_DATE('"+duedt+"','DD/MM/YYYY'), '"+exg_rt+"'," +
			   "'"+cr_dr+"','"+dr_cr_no+"',TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY'),'"+dr_cr_gross_amt+"','"+dr_cr_exg_rt+"', " +
			   "'"+remark+"', "+user_cd+", sysdate, 'Y','"+gross_amt_usd+"','"+tax_struc_cd+"'," +
			   "'"+dr_cr_doc_no+"','"+tax_amt_inr+"','"+dr_cr_gross_amt+"','"+dr_cr_net_amt+"','"+dr_cr_gross_usd+"',"
			  + " '"+dr_cr_fin_year+"','"+dr_cr_sale_rate+"','"+dr_cr_tax_amt+"','"+chkTcs+"','"+dr_cr_qty+"',"
			  + " '"+diff_qty+"','"+diff_tax+"','"+item_desc+"','"+dr_cr_inr_mmbtu+"','"+dr_cr_inr_km+"',"
			  + " '"+diff_inr_mmbtu+"','"+diff_inr_km+"','"+dr_cr_tax_rate+"','"+diff_tax_str+"','"+dr_cr_seq_no+"','"+dr_cr_ref_inv_no+"')";			
			System.out.println("CR-DR: No#:INSERT: "+queryString1);
			stmt1.executeUpdate(queryString1);
			if(cr_dr.equalsIgnoreCase("cr")) {
				selDrCr = "Credit";
			}else {
				selDrCr = "Debit";
				
				/*Hiren_20210709 for Auto paid*/
				if((criteria.contains("DIFF-EXG") || criteria.contains("DIFF-QTY") || criteria.contains("DIFF-PRICE")) && !con_type.equalsIgnoreCase("V")) {
					
					String sn_start_dt = "",sn_end_dt = "",snDtl = "";	
					if(con_type.equalsIgnoreCase("S")) {
						snDtl = "select TO_CHAR(start_dt,'DD/MM/YYYY'),TO_CHAR(end_dt,'DD/MM/YYYY')"
								+ "  from DLNG_SN_MST where CUSTOMER_CD = '"+customer_cd+"'"
								+ " and FLSA_NO='"+fgsa_no+"' and FLSA_REV_NO='"+fgsa_rev_no+"' and SN_NO= '"+sn_no+"' and SN_REV_NO='"+sn_rev_no+"'";
					}else if(con_type.equalsIgnoreCase("L")) {
						
						snDtl = "select TO_CHAR(start_dt,'DD/MM/YYYY'),TO_CHAR(end_dt,'DD/MM/YYYY')"
								+ "  from DLNG_LOA_MST where CUSTOMER_CD = '"+customer_cd+"'"
								+ " and TENDER_NO='"+fgsa_no+"'  and LOA_NO= '"+sn_no+"' and "
								+ " LOA_REV_NO='"+sn_rev_no+"'";
					}
					if(!snDtl.equals("")) {
						rset4 = stmt.executeQuery(snDtl);
						if (rset4.next()) {
							sn_start_dt = rset4.getString(1)==null?"":rset4.getString(1);
							sn_end_dt =rset4.getString(2)==null?"":rset4.getString(2);
						}
					}
					String advAmt = "0";
					String advMapping_id = con_type+"-"+customer_cd+"-"+fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no+"-"+sn_start_dt+"-"+sn_end_dt;//Hiren_20210707
					mapping_id = customer_cd+"-"+fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no+"-"+con_type;
//					System.out.println("advMapping_id----"+advMapping_id);
					DataBean_Advacne_Tracker dat = new DataBean_Advacne_Tracker();
					dat.setSelMapId(advMapping_id);
					dat.setSysdate(sysdate);
					dat.setCallFlag("fetchAdvanceTrackerDtl");
					dat.init();
					advAmt = (String) dat.getVclosing_bal().elementAt(dat.getVclosing_bal().size()-1);
//					System.out.println("advAmt***** "+advAmt);
					
//					System.out.println("dr_cr_net_amt******"+dr_cr_net_amt+"-----advAmt*******"+advAmt);
					if(advAmt.contains(",")) {
						advAmt = advAmt.replace(",", "");
					}
					if(!advAmt.equals("") && !dr_cr_net_amt.equals("") &&  Double.parseDouble(advAmt+"") >= Double.parseDouble(dr_cr_net_amt+"") ) {
						String c_form_flg= "";
						
						//C-form related changes by Hiren_20220505		
						String cFormSql ="SELECT CFORM_FLAG FROM FMS7_CFORM_CONTRACT_DTL WHERE AGR_NO='"+fgsa_no+"' And AGR_REV_NO='"+fgsa_rev_no+"' And "
								+ "CUSTOMER_CD='"+customer_cd+"' And CONTRACT_TYPE='"+con_type+"' And CONTRACT_NO='"+sn_no+"' And CONTRACT_REV_NO='"+sn_rev_no+"'"
								+ " and plant_seq_no='"+plant_no+"'  AND COMMODITY_TYPE='DLNG'";
//						System.out.println("cFormSql---"+cFormSql);
						rset=stmt.executeQuery(cFormSql);
						if(rset.next())
						{
							c_form_flg = rset.getString(1)==null?"":rset.getString(1);
						}
						
						double tds_calc = 0;
						if(tds_app_flag.equalsIgnoreCase("Y") && !tds_amt.equalsIgnoreCase("")) {
							tds_percent = "0.1";
							tds_calc = (Double.parseDouble(tds_amt+"") * Double.parseDouble(tds_percent+""))/100;
//							System.out.println("tds_calc----"+tds_calc);
						}
						
						double tax_perc = 0; 
						
						/*for HOLD amount calculation*/
						queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd+" AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd+" AND " +
								  "B.app_date<=TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
	//					System.out.println("tax factor-----"+queryString);
						rset = stmt1.executeQuery(queryString);
						while(rset.next()) {
							tax_perc+= rset.getDouble(2); 
						}
						double hold_amt = 0;
						double maxPerc = 15;
						double diffTax = maxPerc - tax_perc;
						
						if(diffTax > 0 && c_form_flg.equalsIgnoreCase("Y") && !criteria.contains("DIFF-TAX")) { //calculate HOLD amount from Gross amount for this condition
							
							hold_amt = (Double.parseDouble(dr_cr_gross_amt+"") * diffTax) / 100;
//							System.out.println("hold_amt--------"+hold_amt);
						}
						
						double recv_amt = Double.parseDouble(dr_cr_net_amt+"") - tds_calc;
						queryString2="update DLNG_DR_CR_NOTE set "
								+ " pay_recv_amt='"+nf.format(recv_amt)+"',"
								+ " pay_recv_dt=sysdate,"
								+ " pay_remark='Auto Paid',"
								+ " pay_insert_by='"+user_cd+"',"
								+ " pay_insert_dt=to_date(sysdate,'dd/mm/yy HH24:MI'),"
								+ " tds_percent = '"+tds_percent+"'"
								+ " where hlpl_inv_seq_no='"+Integer.parseInt(hlpl_no)+"' "
								+ " and financial_year='"+fin_yr+"'"
								+ " and contract_type='"+con_type+"' "
								+ " and seq_no = '"+dr_cr_seq_no+"'"
								+ " and (NEW_INV_SEQ_NO = '"+dr_cr_ref_inv_no+"'  or NEW_INV_SEQ_NO is null)";
//							System.out.println("Auto Paid--------"+queryString2);
						stmt1.executeUpdate(queryString2);
						
						queryString="INSERT INTO FMS8_PAY_RECV_DTL (NEW_INV_SEQ_NO,COMMODITY_TYPE,CONTRACT_TYPE,PAY_RECV_AMT,"
								+ "PAY_RECV_DT,PAY_REMARK,PAY_INSERT_BY,REV_NO,MAPPING_ID,SHORT_RECV_AMT,ADJUSTED_AMT,ADJUST_BALANCE_AMT,ADJUST_FLAG,HOLD_AMOUNT) VALUES "
								+ "('"+dr_cr_doc_no+"','DLNG','"+con_type+"','"+nf.format(recv_amt)+"',"
								+ "to_date(sysdate,'dd/mm/yy HH24:MI'),'Auto Paid','"+user_cd+"','1','"+mapping_id+"',"
								+ " '0','"+nf.format(recv_amt)+"','0','Y','"+nf.format(hold_amt)+"')";
//						System.out.println("HOLD amt ----"+queryString);
						stmt.executeUpdate(queryString);
						
						
					}
				}
			}
			if(modify.equals("Y")){
				msg=selDrCr+" Note : "+dr_cr_doc_no+" Modified Successfully!!!";
			}else {
				msg=selDrCr+" Note : "+dr_cr_doc_no+" Generated Successfully!!!";
			}
			
			///////////////: Entry into DLNG_INVOICE_MST //////////
			if(dr_cr_ref_inv_no.equalsIgnoreCase("")) {
				queryString1 = "UPDATE DLNG_INVOICE_MST SET FLAG='"+FlagValue+"' "+
					" WHERE CONTRACT_TYPE='"+con_type+"' AND  HLPL_INV_SEQ_NO='"+hlpl_no+"' "+
					" AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' AND SN_NO='"+sn_no+"' AND SN_REV_NO='"+sn_rev_no+"' AND CUSTOMER_CD='"+customer_cd+"' AND " +
					" PLANT_SEQ_NO='"+plant_no+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND FLAG!='A' ";			
//					System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
				stmt1.executeUpdate(queryString1);
			}
//			System.out.println("cr_dr*******"+cr_dr);
			String inv_typ="";
			if(cr_dr.equalsIgnoreCase("CR")){
				inv_typ="CREDIT";
			}else if(cr_dr.equalsIgnoreCase("DR")){
				inv_typ="DEBIT";
			}
			
//			System.out.println("inv_typ*****"+inv_typ+"**cr_dr***"+cr_dr+"******"+criteria);
			if(tcs_flag.equalsIgnoreCase("Y") && (cr_dr.equalsIgnoreCase("DR") || cr_dr.equalsIgnoreCase("CR")) && chkTcs.equals("Y")){
				if(!criteria.contains("REV_INV")){
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND "
							+ "HLPL_INV_SEQ_NO ='"+dr_cr_no+"' AND CONTRACT_TYPE='"+con_type+"' AND INVOICE_TYPE='"+inv_typ+"' and commodity_type='DLNG'";			
//					System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					if(rset1.next()){
						int count=rset1.getInt(1);
						if(count==0){
							queryString1 = "INSERT INTO FMS7_INVOICE_TCS_DTL (CUSTOMER_CD,FINANCIAL_YEAR,HLPL_INV_SEQ_NO,CONTRACT_TYPE,INVOICE_TYPE,"
									+ "INVOICE_NET_AMT,TCS_AMT,FLAG,EFF_DT,COMMODITY_TYPE) VALUES ('"+customer_cd+"','"+fin_yr+"','"+dr_cr_no+"','"+con_type+"','"+inv_typ+"',"
									+ "'"+dr_cr_net_amt+"','"+tcs_amt+"','Y',TO_DATE(SYSDATE,'DD/MM/YYYY'),'DLNG')";			
//							System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
							stmt1.executeUpdate(queryString1);
						}else{
							queryString1 = "update FMS7_INVOICE_TCS_DTL set INVOICE_NET_AMT='"+dr_cr_net_amt+"',tcs_amt='"+tcs_amt+"',flag='Y' WHERE CUSTOMER_CD='"+customer_cd+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND "
									+ "HLPL_INV_SEQ_NO ='"+dr_cr_no+"' AND CONTRACT_TYPE='"+con_type+"' AND INVOICE_TYPE='"+inv_typ+"' and commodity_type='DLNG'";			
//							System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
							rset1=stmt1.executeQuery(queryString1);
						}
					}
				}
			}else{
				queryString1 = "update FMS7_INVOICE_TCS_DTL set flag='N' WHERE CUSTOMER_CD='"+customer_cd+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND "
						+ "HLPL_INV_SEQ_NO ='"+dr_cr_no+"' AND CONTRACT_TYPE='"+con_type+"' AND INVOICE_TYPE='"+inv_typ+"' and commodity_type='DLNG'";			
//				System.out.println("Query For Inserting Record Into Invoice Master Table = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
			}
			/*Hiren_20210624 for TDS Entry*/
 			if(tds_app_flag.equalsIgnoreCase("Y") && !tds_amt.equalsIgnoreCase("")) {
 				
 				String query="SELECT COUNT(*) FROM FMS7_INVOICE_TDS_DTL WHERE HLPL_INV_SEQ_NO='"+dr_cr_no+"' AND "
	 					+ "CONTRACT_TYPE='"+con_type+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND CUSTOMER_CD='"+customer_cd+"' "
	 							+ " AND INVOICE_TYPE='"+inv_typ+"' and commodity_type='DLNG'";
//	 			System.out.println("TDS Count---"+query);
	 			rset=stmt.executeQuery(query);
	 			if(rset.next()){
	 				int cnt=rset.getInt(1);
	 				if(cnt==0){
	 					queryString1="INSERT INTO FMS7_INVOICE_TDS_DTL (HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,CUSTOMER_CD,TDS_AMT,"
	 							+ "INVOICE_GROSS_AMT,FLAG,EFF_DT,INVOICE_TYPE,COMMODITY_TYPE) VALUES ('"+dr_cr_no+"','"+fin_yr+"',"
								+ "'"+con_type+"','"+customer_cd+"','0','"+tds_amt+"','Y',TO_DATE(SYSDATE,'DD/MM/YYYY'),'"+inv_typ+"','DLNG') ";
	 					stmt1.executeUpdate(queryString1);
	 				}else{
	 					queryString1="UPDATE FMS7_INVOICE_TDS_DTL SET INVOICE_NET_AMT='"+tds_amt+"',"
	 								+ " EFF_DT=TO_DATE(SYSDATE,'DD/MM/YYYY'),flag='Y' "
		 							+ " WHERE  HLPL_INV_SEQ_NO='"+dr_cr_no+"' AND "
		 							+ "CONTRACT_TYPE='"+con_type+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND CUSTOMER_CD='"+customer_cd+"' "
		 							+ " AND INVOICE_TYPE='"+inv_typ+"' and commodity_type='DLNG'";
	 					stmt1.executeUpdate(queryString1);
	 					//System.out.println("STEP-8:FMS7_INVOICE_TCS_DTL Adjust= "+queryString1);
	 				}
	 			}
 			}else{
 				String query="SELECT COUNT(*) FROM FMS7_INVOICE_TDS_DTL WHERE HLPL_INV_SEQ_NO='"+dr_cr_no+"' AND "
	 					+ "CONTRACT_TYPE='"+con_type+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND CUSTOMER_CD='"+customer_cd+"' AND INVOICE_TYPE='"+inv_typ+"' and commodity_type='DLNG'";
	 			rset=stmt.executeQuery(query);
	 			if(rset.next()){
	 				int cnt=rset.getInt(1);
	 				if(cnt>0){
	 					queryString1="UPDATE FMS7_INVOICE_TDS_DTL SET FLAG='N' "
	 							+ " WHERE  HLPL_INV_SEQ_NO='"+dr_cr_no+"' AND "
	 							+ "CONTRACT_TYPE='"+con_type+"' AND FINANCIAL_YEAR='"+fin_yr+"' AND CUSTOMER_CD='"+customer_cd+"' AND INVOICE_TYPE='"+inv_typ+"' and commodity_type='DLNG'";
	 					stmt1.executeUpdate(queryString1);
	 				}
	 			}
 			}	
 			
// 			for DIFF-KM & DIFF-LUMPSUM &  DIFF-QTY criteria detailed entry
 			
 			String diff_qty_arr [] = request.getParameterValues("diff_qty");
			String dr_cr_qty_arr [] = request.getParameterValues("dr_cr_qty");
			String supply_dt [] = request.getParameterValues("supply_dt");
			String truck_no_arr [] =  request.getParameterValues("truck_no");
			
			if(truck_no_arr!=null && truck_no_arr.length > 0) {
				int maxRev = 0 ;
				String maxRevSql = "select nvl(max(rev_no),0) from DLNG_DR_CR_SERVICE_DTL  where DR_CR_DOC_NO = '"+dr_cr_doc_no+"' ";
//				System.out.println("maxRevSql***"+maxRevSql);
				rset = stmt.executeQuery(maxRevSql);
				if(rset.next()) {
					maxRev = rset.getInt(1)+1;
				}
				
				for(int i = 0 ; i < truck_no_arr.length ; i ++) {
					
					String insSql = "insert into DLNG_DR_CR_SERVICE_DTL (DIFF_QTY,DR_CR_DOC_NO,DR_CR_QTY,ENT_BY,ENT_DT,REV_NO,SUPPLY_DT,TRUCK_NO)"
							+ " VALUES ('"+diff_qty_arr[i]+"','"+dr_cr_doc_no+"','"+dr_cr_qty_arr[i]+"','"+user_cd+"',sysdate,'"+maxRev+"',to_date('"+supply_dt[i]+"','dd/mm/yyyy'),'"+truck_no_arr[i]+"')";
//					System.out.println("insSql***"+insSql);
					stmt.executeUpdate(insSql);
				}
			}
 			///////////
			
			String flag="Y";
			url = "../sales_invoice/frm_dr_cr_note.jsp?msg="+msg+"&tax_struc_cd="+tax_struc_cd+"&dr_cr_fin_year="+dr_cr_fin_year+"&bill_dt="+bill_dt+"&to_dt="+to_dt+"&from_dt="+from_dt+"&bscode="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&con_type="+con_type+"&plant_no="+plant_no+"&due_dt="+due_dt+"&hlpl_no="+hlpl_no+"&inv_no="+inv_no+"&inv_dt="+inv_dt+"&fin_yr="+fin_yr+"&criteria="+criteria+"&qty="+qty+"&gross_amt="+gross_amt+"&gross_amt_usd="+gross_amt_usd+"&net_amt="+net_amt+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&dr_cr_no="+dr_cr_no+"&dr_cr_doc_no="+dr_cr_doc_no+"&dr_cr_sale_rate="+dr_cr_sale_rate+"&dr_cr_net_amt2="+dr_cr_net_amt+"&dr_cr_gross_amt2="+dr_cr_gross_amt+"&chkval="+chkval+"&flag="+flag+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
//			System.out.println("url : "+url);
			dbcon.commit();
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try { dbcon.rollback(); } catch(Exception e1) { e1.printStackTrace(); }
			err_msg = "Error : Submission Failed due to: "+e.getMessage();
			e.printStackTrace();
			////System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../sales_invoice/frm_dr_cr_note.jsp?tax_struc_cd="+tax_struc_cd+"&dr_cr_fin_year="+dr_cr_fin_year+"&bill_dt="+bill_dt+"&bscode="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&con_type="+con_type+"&plant_no="+plant_no+"&due_dt="+due_dt+"&hlpl_no="+hlpl_no+"&inv_no="+inv_no+"&inv_dt="+inv_dt+"&fin_yr="+fin_yr+"&criteria="+criteria+"&qty="+qty+"&gross_amt="+gross_amt+"&gross_amt_usd="+gross_amt_usd+"&net_amt="+net_amt+"&exg_rt="+exg_rt+"&cr_dr="+cr_dr+"&sale_rate="+sale_rate+"&remark="+remark+"&dr_cr_no="+dr_cr_no+"&dr_cr_dt="+dr_cr_dt+"&dr_cr_exg_rt="+dr_cr_exg_rt+"&dr_cr_gross_amt="+dr_cr_gross_amt+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&error_msg="+err_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
			//////System.out.println("--11-->"+url);
		}		
	}//End Of Method submitDebitCreditNote_approval() ...
	
	public void Approve_Credit(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException
	{
		//////System.out.println("in method approve_credit");
		methodName = "Approve_Invoice()";
		form_name = "frm_approve_cr_dr_note.jsp";
		
		String approve_flag = request.getParameter("approve_flag")==null?"":request.getParameter("approve_flag");
		String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no");
		String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
		String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
		String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		System.out.println("------------------>"+bill_cycle);
		String invno_disp = request.getParameter("invno_disp")==null?"":request.getParameter("invno_disp");
		String billing_dt = request.getParameter("billing_dt")==null?"":request.getParameter("billing_dt");
		String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
		
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
			queryString1 = "SELECT DR_CR_FLAG,DR_CR_DOC_NO FROM DLNG_DR_CR_NOTE WHERE "
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
				queryString1 = "UPDATE DLNG_DR_CR_NOTE SET " +
							   "APRV_BY='0',APRV_dt=sysdate  "+//approved_dt=sysdate " +
							   "WHERE financial_year='"+inv_financial_year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
				  			   "contract_type='"+contract_type+"'";
				
				msg = " Approval "+msg1+" Not Done !!!";
			}
			else
			{
				queryString1 = "UPDATE DLNG_DR_CR_NOTE SET " +
							   //"approved_flag='"+approve_flag+"', " +
							   "APRV_BY='"+user_cd+"', APRV_dt=sysdate " +
							   "WHERE financial_year='"+inv_financial_year+"' AND " +
				  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
				  			   "contract_type='"+contract_type+"'";
				
				msg = "Approval "+msg1+" Done Successfully !!!";
			}
				
			System.out.println("UPDATE:CREDIT: DLNG_DR_CR_NOTE:  "+queryString1);
				
			stmt1.executeUpdate(queryString1);								
				
			//msg = "Approved ["+hlpl_inv_no+"] Successfully !!!";
			url = "../sales_invoice/frm_approve_cr_dr_note.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&error_msg="+err_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&bill_period_start_dt="+billing_dt;
			dbcon.commit();
			
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
			err_msg = "Invoice Approving Process Failed For "+hlpl_inv_no+" Invoice !!!";
			e.printStackTrace();
			////System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url = "../sales_invoice/frm_approve_cr_dr_note.jsp?msg="+msg+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&error_msg="+err_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&bill_period_start_dt="+billing_dt;	
		}
	}//End Of Approve_Invoice() Method ...
	
}
