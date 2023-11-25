package com.seipl.hazira.dlng.service_inv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

@WebServlet("/servlet/Frm_Service_Invoice")
public class Frm_Service_Invoice extends HttpServlet{

public static Connection dbcon;
	
	public String servletName = "Frm_Service_Invoice";
	public String methodName = "";
	public String option = "";
	public String url = "";
	public String form_name = "";
	public String msg = "";
	public String error_msg = "";
	int count = 0;
	static escapeSingleQuotes obj = new escapeSingleQuotes();
	
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("##0.0000");
	NumberFormat nf4 = new DecimalFormat("##0.000"); 
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
	private static PreparedStatement ps = null;
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
				System.out.println("Data Source Not Found - Error In "+servletName+" Servlet !!!");
			}
			
			if(dbcon != null)
			{
				dbcon.setAutoCommit(false);
				stmt = dbcon.createStatement(); 
				stmt1 = dbcon.createStatement();
				/*stmt2 = dbcon.createStatement(); 
				stmt3 = dbcon.createStatement();
				stmt4 = dbcon.createStatement();*/
			}			
			option=req.getParameter("option")==null?"":req.getParameter("option");
			write_permission = req.getParameter("write_permission")==null?"N":req.getParameter("write_permission");
			delete_permission = req.getParameter("delete_permission")==null?"N":req.getParameter("delete_permission");
			print_permission = req.getParameter("print_permission")==null?"N":req.getParameter("print_permission");
			check_permission = req.getParameter("check_permission")==null?"N":req.getParameter("check_permission");
			authorize_permission = req.getParameter("authorize_permission")==null?"N":req.getParameter("authorize_permission");
			approve_permission = req.getParameter("approve_permission")==null?"N":req.getParameter("approve_permission");
			audit_permission = req.getParameter("audit_permission")==null?"N":req.getParameter("audit_permission");
			
			form_id = req.getParameter("formId")==null?"0":req.getParameter("formId");
			form_nm = req.getParameter("form_nm")==null?"":req.getParameter("form_nm");
//			System.out.println("option*****"+option);
			if(option.equalsIgnoreCase("Submit_Service_Invoice"))
			{
				insertServiceInvoiceData(req);
			}
			else if(option.equalsIgnoreCase("Modify_Service_Invoice")) {
				modifyServiceInvoiceData(req);
				
			}else if(option.equalsIgnoreCase("Check_Approve")) {
				
				checkApproveServiceInv(req);
				
			}else if(option.equalsIgnoreCase("DR_CR_Approve")) {
				
				approveDrCrNote(req);
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
			}if(ps != null)
			{
				try
				{
					ps.close();
				}
				catch(SQLException e)
				{
					System.out.println("ps is not close "+e.getMessage());
				}
				ps = null;
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

	private String approveDrCrNote(HttpServletRequest request)throws SQLException,IOException {
		msg = "";
		String log_msg = "";
		String temp_msg = "";
			String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
			String invDt = request.getParameter("invDt")==null?"":request.getParameter("invDt");	 
			String drcr_doc_no = request.getParameter("drcr_doc_no")==null?"":request.getParameter("drcr_doc_no");
			String approve_flag = request.getParameter("approve_flag")==null?"":request.getParameter("approve_flag");
			String month = request.getParameter("month")==null?"0":request.getParameter("month");
			String year = request.getParameter("year")==null?"0":request.getParameter("year");
			String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
		
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
		try {	
			String q = "SELECT FORM_NAME FROM SEC_FORM_MST WHERE FORM_CD='"+form_id+"' ";
			rset = stmt.executeQuery(q);
			if(rset.next()) {
				form_nm = rset.getString(1);
			}
			String dr_cr_flag = "";
			String financial_year = "",hlpl_inv_seq_no="",contract_type = "";
			
			String drcrSql = "select A.dr_cr_flag,nvl(A.aprv_by,0),A.financial_year,A.hlpl_inv_seq_no,A.contract_type "
					+ " from DLNG_DR_CR_NOTE A,DLNG_INVOICE_MST B"
					+ " where "
					+ " a.dr_cr_doc_no = '"+drcr_doc_no+"'"
					+ " and B.new_inv_seq_no = '"+new_inv_seq_no+"' "
					+ " and B.invoice_dt = to_date('"+invDt+"','dd/mm/yyyy') "
					+ " and B.HLPL_INV_SEQ_NO = A.HLPL_INV_SEQ_NO  and B.SN_NO = A.SN_NO and B.FGSA_NO = A.FGSA_NO"
					+ " and B.CUSTOMER_CD = A.CUSTOMER_CD and B.FINANCIAL_YEAR = A.FINANCIAL_YEAR "
					+ " and B.PLANT_SEQ_NO = A.PLANT_SEQ_NO"
					+ " and B.CONTRACT_TYPE = A.CONTRACT_TYPE ";
//			System.out.println("drcrSql***"+drcrSql);
			rset = stmt.executeQuery(drcrSql);
			if(rset.next()) {
				dr_cr_flag = rset.getString(1)==null?"":rset.getString(1);
//				apr_by = rset.getInt(2);
				financial_year = rset.getString(3)==null?"":rset.getString(3);
				hlpl_inv_seq_no= rset.getString(4)==null?"":rset.getString(4);
				contract_type = rset.getString(5)==null?"":rset.getString(5);
			}
			
			if(dr_cr_flag.equalsIgnoreCase("cr")) {
				temp_msg = "Credit Note : "+drcr_doc_no;
			}else if(dr_cr_flag.equalsIgnoreCase("dr")) {
				temp_msg = "Debit Note : "+drcr_doc_no;
			}
		
			if(approve_flag.equalsIgnoreCase("N")) {
				
				queryString1 = "UPDATE DLNG_DR_CR_NOTE SET " +
					   "APRV_BY='',APRV_dt=null  "+
					   "WHERE financial_year='"+financial_year+"' AND " +
		  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
		  			   "contract_type='"+contract_type+"'";
			
				msg=temp_msg+" Disapproved Successfully";
				log_msg = temp_msg+" Disapproved Successfully";
				
			}else if(approve_flag.equalsIgnoreCase("Y")) {
				
				queryString1 = "UPDATE DLNG_DR_CR_NOTE SET " +
						   "APRV_BY='"+user_cd+"', APRV_dt=sysdate " +
						   "WHERE financial_year='"+financial_year+"' AND " +
			  			   "hlpl_inv_seq_no="+Integer.parseInt(hlpl_inv_seq_no)+" AND " +
			  			   "contract_type='"+contract_type+"'";
				
				msg=temp_msg+" Approved Successfully";
				log_msg = temp_msg+" Approved Successfully";
			}
			int insCnt = stmt.executeUpdate(queryString1);
			if(insCnt > 0 ) {
				dbcon.commit();
			}else {
				dbcon.rollback();
				error_msg = temp_msg+" Approval Failed";
				log_msg = temp_msg+" Approval Failed";
			}
			
		} catch (Exception e) {
			dbcon.rollback();
			e.printStackTrace();
			error_msg = "Exception occured while approving "+temp_msg;
			log_msg = "Exception occured while approving "+temp_msg;
		}
		
		url = "../general_inv/frm_view_chk_apr_dr_cr.jsp?msg="+msg+"&error_msg="+error_msg+"&year="+year+"&month="+month+"&bill_cycle="+bill_cycle+"&backFrm=Y";
		try
		{
			new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+log_msg);
		}
		catch(Exception infoLogger)
		{
			////System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
			infoLogger.printStackTrace();
		}
		return url;
	}

	private void checkApproveServiceInv(HttpServletRequest request)throws SQLException {
		msg = "";
		error_msg = "";
		String inv_flag = request.getParameter("inv_flag")==null?"":request.getParameter("inv_flag");
		String check_flag = request.getParameter("check_flag")==null?"":request.getParameter("check_flag");
		String approve_flag = request.getParameter("approve_flag")==null?"":request.getParameter("approve_flag");
		String emp_cd = request.getParameter("emp_cd")==null?"":request.getParameter("emp_cd");
		String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
		String modUrl = request.getParameter("modUrl")==null?"":request.getParameter("modUrl");
		String year = request.getParameter("year")==null?"":request.getParameter("year");
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		String bill_cycle = request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");
		String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
		String invDt = request.getParameter("invDt")==null?"":request.getParameter("invDt");
		
		String chk_apr = "";
		try {
			
			String chkApprSql = "";
			if(inv_flag.equalsIgnoreCase("C")) {
				
				if(check_flag.equalsIgnoreCase("Y")) {
					chk_apr = "Invoice No."+new_inv_seq_no+" Successfully Checked.";
					chkApprSql = "update  DLNG_INVOICE_MST set "
							+ " CHECKED_BY = '"+emp_cd+"',"
							+ " CHECKED_DT = sysdate,"
							+ " CHECKED_FLAG = 'Y', "
							+ " EMP_CD = '"+emp_cd+"',"
							+ " ENT_DT = sysdate"
							+ " where "
							+ " NEW_INV_SEQ_NO = '"+new_inv_seq_no+"' "
							+ " and INVOICE_DT = to_date('"+invDt+"','dd/mm/yyyy')";
				}else {
					chk_apr = "Invoice No."+new_inv_seq_no+" Successfully Un-Checked.";
					chkApprSql = "update  DLNG_INVOICE_MST set "
							+ " CHECKED_BY = '',"
							+ " CHECKED_DT = '',"
							+ " CHECKED_FLAG = 'N',"
							+ " EMP_CD = '"+emp_cd+"',"
							+ " ENT_DT = sysdate"
							+ " where "
							+ " NEW_INV_SEQ_NO = '"+new_inv_seq_no+"' "
							+ " and INVOICE_DT = to_date('"+invDt+"','dd/mm/yyyy')";
				}
			}else if(inv_flag.equalsIgnoreCase("A")) {
				if(approve_flag.equalsIgnoreCase("Y")) {
					chk_apr = "Invoice No."+new_inv_seq_no+" Successfully Approved.";
					chkApprSql = "update  DLNG_INVOICE_MST set "
							+ " APPROVED_BY = '"+emp_cd+"',"
							+ " APPROVED_DT = sysdate,"
							+ " APPROVED_FLAG = 'Y', "
							+ " EMP_CD = '"+emp_cd+"',"
							+ " ENT_DT = sysdate"
							+ " where "
							+ " NEW_INV_SEQ_NO = '"+new_inv_seq_no+"' "
							+ " and INVOICE_DT = to_date('"+invDt+"','dd/mm/yyyy')";
				}else {
					chk_apr = "Invoice No."+new_inv_seq_no+" Successfully Un-Approved.";
					chkApprSql = "update  DLNG_INVOICE_MST set "
							+ " APPROVED_BY = '',"
							+ " APPROVED_DT = '',"
							+ " APPROVED_FLAG = 'N',"
							+ " EMP_CD = '"+emp_cd+"',"
							+ " ENT_DT = sysdate,"
							+ " CHECKED_BY = '',"
							+ " CHECKED_DT = '',"
							+ " CHECKED_FLAG = 'N'"
							+ " where "
							+ " NEW_INV_SEQ_NO = '"+new_inv_seq_no+"' "
							+ " and INVOICE_DT = to_date('"+invDt+"','dd/mm/yyyy')";
				}
			}
			
			System.out.println("chkApprSql---"+chkApprSql);
			if(!chkApprSql.equals("")) {
				ps = dbcon.prepareStatement(chkApprSql);
				int insCnt = ps.executeUpdate();
				System.out.println("insCnt---"+insCnt);
				if(insCnt > 0) {
					dbcon.commit();
					msg = chk_apr;
				}else {
					dbcon.rollback();
					error_msg = "No record found for Invoice No."+new_inv_seq_no;
				}
			}
		} catch (Exception e) {
			dbcon.rollback();
			e.printStackTrace();
			error_msg = "Error: Insertion Failed";
		}
		System.out.println("msg----"+msg);
		System.out.println("error_msg----"+error_msg);
		url = "../general_inv/view_service_invoice.jsp?formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&year="+year+"&month="+month+"&bill_cycle="+bill_cycle+"&msg="+msg+"&error_msg="+error_msg+"&backFrm=Y";
	}

	private void modifyServiceInvoiceData(HttpServletRequest request) {
		
		msg = "";
		error_msg = "";
		
		String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
		String modUrl = request.getParameter("modUrl")==null?"":request.getParameter("modUrl");
		String year = request.getParameter("year")==null?"":request.getParameter("year");
		String month = request.getParameter("month")==null?"":request.getParameter("month");
		String bill_cycle = request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");
		
		String contact_person_cd = request.getParameter("contact_person_cd")==null?"":request.getParameter("contact_person_cd");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String customer_cd = request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
		String cust_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
		String due_dt = request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
		String emp_cd = request.getParameter("emp_cd")==null?"":request.getParameter("emp_cd");
		String financial_year = request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
		String gross_amt_inr = request.getParameter("grossAmt")==null?"":request.getParameter("grossAmt");
		String invoice_dt = request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
		int inv_seq_no = Integer.parseInt(request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no"));
		String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
		String net_amt_inr = request.getParameter("netAmt")==null?"":request.getParameter("netAmt");
		String period_start_dt = request.getParameter("period_start_dt")==null?"":request.getParameter("period_start_dt");
		String period_end_dt = request.getParameter("period_end_dt")==null?"":request.getParameter("period_end_dt");
		String plant_seq_no = request.getParameter("cust_plant_cd")==null?"":request.getParameter("cust_plant_cd");
		String remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
		String remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
		String supplier_cd = request.getParameter("supp_cd")==null?"":request.getParameter("supp_cd");
		String supplier_state_cd = request.getParameter("supp_state_cd")==null?"":request.getParameter("supp_state_cd");
		String calcBase = request.getParameter("calcBase")==null?"":request.getParameter("calcBase");
		String tax_amt_inr = request.getParameter("totalTax")==null?"":request.getParameter("totalTax");
		String tax_flag = request.getParameter("tax_flag")==null?"":request.getParameter("tax_flag");
		String tax_struct_cd = request.getParameter("tax_structure")==null?"":request.getParameter("tax_structure");
		String total_qty = request.getParameter("total_qty")==null?"0":request.getParameter("total_qty");
	
		String sac_cd[] =  request.getParameterValues("sac_cd");
		String line_item[] =  request.getParameterValues("line_item");
		String qty[] =  request.getParameterValues("qty");
		String rate[] =  request.getParameterValues("rate");
		String amt[] =  request.getParameterValues("amt");
		
		String tax_sz = request.getParameter("tax_sz")==null?"":request.getParameter("tax_sz");
		String tax_amt[] =  request.getParameterValues("taxAmt");
		
		int truckInvSz = Integer.parseInt(request.getParameter("truckInvSz")==null?"0":request.getParameter("truckInvSz"));
		String rate_mmbtu = request.getParameter("rate_mmbtu")==null?"":request.getParameter("rate_mmbtu");
		String rate_km = request.getParameter("rate_km")==null?"":request.getParameter("rate_km");
		
		String truckInvDt[] = request.getParameterValues("truckInvDt");
		String truckInvNo[] = request.getParameterValues("truckInvNo");
		String truckNm[] = request.getParameterValues("truckNm");
		String truckInvQty[] = request.getParameterValues("truckInvQty");
		String km[] = request.getParameterValues("km");
		String servInvQtyAmt[] = request.getParameterValues("servInvQtyAmt");
		String inv_km_inr[] = request.getParameterValues("inv_km_inr");
		String lumpsum[] = request.getParameterValues("lumpsum");
		String chkBoxFlg[] = request.getParameterValues("chkBoxFlg");
		try {
			if(!cust_inv_seq_no.equals("")) {
				
				String tt="";String am="";String cgst="";String sgst="";String igst="";String tax_cd="";
				String cgst_amt= "0",sgst_amt= "0",igst_amt ="0";
				
				if(tax_sz.equalsIgnoreCase("2")) {
					
					for(int k=0;k<Integer.parseInt(tax_sz);k++) {
						tax_cd="C";
						//System.out.println("--tax_amts--"+tax_amount);
						if(k==0){
							tt="CGST-"+tax_struct_cd+"%";
							cgst=tax_struct_cd;
							cgst_amt = tax_amt[k];
						}else{
							tt+="SGST-"+tax_struct_cd+"%";
							sgst=tax_struct_cd;
							sgst_amt = tax_amt[k];
						}
					}
					}else{
						tax_cd="I";
						tt="IGST-"+tax_struct_cd+"%";
						igst=tax_struct_cd;
						igst_amt=request.getParameter("taxAmt")==null?"":request.getParameter("taxAmt");
					}
				
				String sn_no="",sn_rev_no = "",flsa_no = "", flsa_rev_no = "",cont_typ = "";
				
				if(mapping_id.contains("-")) {
					
					String tempMap [] = mapping_id.split("-");
					
					sn_no = tempMap[3];
					sn_rev_no = tempMap[4];
					flsa_no = tempMap[1];
					flsa_rev_no = tempMap[2];
					cont_typ = tempMap[0];
				}
				int invCnt = 0;
				String chkInvExitst = "SELECT COUNT(*) FROM DLNG_INVOICE_MST "
						+ " WHERE CUSTOMER_CD = '"+customer_cd+"' AND MAPPING_ID LIKE '"+cont_typ+"-"+flsa_no+"-"+flsa_rev_no+"-"+sn_no+"-%'"
						+ " AND PDF_INV_DTL IS NULL AND  NEW_INV_SEQ_NO = '"+cust_inv_seq_no+"' ";
				rset = stmt.executeQuery(chkInvExitst);
//				System.out.println("chkInvExitst---"+chkInvExitst);
				if(rset.next()) {
					invCnt = rset.getInt(1);
				}
				
				if(invCnt > 0) {
					
					String updInvSql = "UPDATE DLNG_INVOICE_MST SET "
							+ " CONTACT_PERSON_CD = '"+contact_person_cd+"', "
							+ " DUE_DT = to_date('"+due_dt+"','dd/mm/yyyy'),"
							+ " EMP_CD = '"+emp_cd+"',"
							+ " ENT_DT = sysdate,"
							+ " FINANCIAL_YEAR = '"+financial_year+"',"
							+ " FLAG = 'Y' ,"
							+ " GROSS_AMT_INR = '"+gross_amt_inr+"', "
							+ " INVOICE_DT = to_date('"+invoice_dt+"','dd/mm/yyyy'), "
							+ " MAPPING_ID = '"+mapping_id+"', "
							+ " NET_AMT_INR = '"+net_amt_inr+"', "
							+ " PERIOD_END_DT = to_date('"+period_end_dt+"','dd/mm/yyyy') ,"
							+ " PERIOD_START_DT = to_date('"+period_start_dt+"','dd/mm/yyyy') ,"
							+ " REMARK_1 = '"+remark_1+"', "
							+ " REMARK_2 = '"+remark_2+"', "
							+ " TAX_AMT_INR = '"+tax_amt_inr+"', "
							+ " TAX_FLAG = '"+tax_cd+"', "
							+ " TAX_STRUCT_CD = '"+tax_struct_cd+"', "
							+ " TOTAL_QTY = '"+total_qty+"', "
							+ " APPROVED_BY = '', "
							+ " APPROVED_DT = '' ,"
							+ " APPROVED_FLAG = '', "
							+ " CHECKED_BY = '', "
							+ " CHECKED_DT = '', "
							+ " CHECKED_FLAG = '', "
							+ " INR_BASE = '"+calcBase+"', "
							+ " BILLING_CYCLE = '"+bill_cycle+"', "
							+ " SN_REV_NO = '"+sn_rev_no+"', "
							+ " SALE_PRICE = '0', "
							+ " GROSS_AMT_USD = '0' "
							+ " WHERE "
							+ " SUPPLIER_CD = '"+supplier_cd+"' "
							+ " AND PLANT_SEQ_NO = '"+plant_seq_no+"' "
							+ " AND HLPL_INV_SEQ_NO = '"+inv_seq_no+"' "
							+ " AND CONTRACT_TYPE = '"+contract_type+"' "
							+ " AND CUSTOMER_CD = '"+customer_cd+"' "
							+ " AND NEW_INV_SEQ_NO = '"+cust_inv_seq_no+"' "
							+ " AND CUST_INV_SEQ_NO = '"+inv_seq_no+"' "
							+ " AND SN_NO = '"+sn_no+"' "
							+ " AND FGSA_NO = '"+flsa_no+"' "
							+ " AND FGSA_REV_NO = '"+flsa_rev_no+"' ";
//					System.out.println("updInvSql---"+updInvSql);
					stmt.executeUpdate(updInvSql);
					
				/*delete detail table data*/
					String delDtlSql = "DELETE FROM DLNG_SERVICE_INVOICE_DTL WHERE"
							+ " SUPPLIER_CD = '"+supplier_cd+"' "
							+ " AND INV_SEQ_NO = '"+inv_seq_no+"'"
							+ " AND CONTRACT_TYPE = '"+contract_type+"'";
//					System.out.println("delDtlSql---"+delDtlSql);
					stmt.execute(delDtlSql);
					
					for(int i = 0 ; i < sac_cd.length ; i++) {
						
						String  insDtlSql = "INSERT INTO DLNG_SERVICE_INVOICE_DTL (CONTRACT_TYPE,EFF_DT,ENTER_BY,ENTER_DT,FINANCIAL_YEAR,FLAG"
								+ " , INV_SEQ_NO,ITEM_DESCRIPTION,LINE_NO,MAPPING_ID,QUANTITY,RATE,SAC_CODE,SUPPLIER_CD,TAX_CD,TAX_DETAILS,"
								+ " RATE_CGST,RATE_IGST,RATE_SGST,CARGO_AMOUNT,CGST_AMT,IGST_AMT,SGST_AMT)"
								+ " VALUES ('"+contract_type+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),'"+emp_cd+"',sysdate,'"+financial_year+"','Y',"
										+ " '"+inv_seq_no+"','"+line_item[i]+"','"+i+"','"+mapping_id+"','"+qty[i]+"','"+rate[i]+"','"+sac_cd[i]+"',"
										+ " '"+supplier_cd+"','"+tax_cd+"','"+tt+"','"+cgst+"','"+igst+"','"+sgst+"','"+amt[i]+"','"+cgst_amt+"','"+igst_amt+"','"+sgst_amt+"') ";
//						System.out.println("insDtlSql----"+insDtlSql);
						stmt.execute(insDtlSql);
					}
					
					/*delete detail table data*/
					String delAttachSql = "DELETE FROM DLNG_SERVICE_INVOICE_ATTACH WHERE"
							+ " SERVICE_INVOICE_NO = '"+cust_inv_seq_no+"'";
//					System.out.println("delAttachSql----"+delAttachSql);
					stmt.execute(delAttachSql);
					
					for(int i = 0 ; i < truckInvSz ; i++) {
						String insAttachSql = "";
						if(chkBoxFlg[i].equals("Y")) {
							if(calcBase.equals("1")) {
								
								insAttachSql = "INSERT INTO DLNG_SERVICE_INVOICE_ATTACH (AMOUNT,CALC_BASE,INVOICE_QTY,KM,RATE,SERVICE_INVOICE_NO,SERVICE_INV_DT,TRUCK_INV_DT,TRUCK_INV_NO,TRUCK_NM)"
										+ " VALUES ('"+servInvQtyAmt[i]+"','"+calcBase+"','"+truckInvQty[i]+"','','"+rate_mmbtu+"','"+cust_inv_seq_no+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),to_date('"+truckInvDt[i]+"','dd/mm/yyyy'),'"+truckInvNo[i]+"','"+truckNm[i]+"')";
							}else if(calcBase.equals("2")) {
								
								insAttachSql = "INSERT INTO DLNG_SERVICE_INVOICE_ATTACH (AMOUNT,CALC_BASE,INVOICE_QTY,KM,RATE,SERVICE_INVOICE_NO,SERVICE_INV_DT,TRUCK_INV_DT,TRUCK_INV_NO,TRUCK_NM)"
										+ " VALUES ('"+inv_km_inr[i]+"','"+calcBase+"','"+truckInvQty[i]+"','"+km[i]+"','"+rate_km+"','"+cust_inv_seq_no+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),to_date('"+truckInvDt[i]+"','dd/mm/yyyy'),'"+truckInvNo[i]+"','"+truckNm[i]+"')";
							}else if(calcBase.equals("3")) {
								
								insAttachSql = "INSERT INTO DLNG_SERVICE_INVOICE_ATTACH (AMOUNT,CALC_BASE,INVOICE_QTY,KM,RATE,SERVICE_INVOICE_NO,SERVICE_INV_DT,TRUCK_INV_DT,TRUCK_INV_NO,TRUCK_NM)"
										+ " VALUES ('"+lumpsum[i]+"','"+calcBase+"','"+truckInvQty[i]+"','','1','"+cust_inv_seq_no+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),to_date('"+truckInvDt[i]+"','dd/mm/yyyy'),'"+truckInvNo[i]+"','"+truckNm[i]+"')";
							}
	//						System.out.println("insAttachSql---"+insAttachSql);
							if(!insAttachSql.equals("")) {
								stmt.execute(insAttachSql);
							}
						}
					}
					
					msg = "Invoice Number : "+cust_inv_seq_no+" Successfully Modified";
					dbcon.commit();
					
				}else {
					error_msg = "PRINT PDF Completed/Invoice details not found to modify";
				}
			}
		}catch (Exception e) {
			try {
				dbcon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			error_msg = "Failed : Invoice Number : "+inv_seq_no+ " Modification Unsuccessfull";
		}
		url = "../general_inv/frm_modify_service_invoice.jsp?formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&year="+year+"&month="+month+"&bill_cycle="+bill_cycle+"&msg="+msg+"&error_msg="+error_msg+"&modFlg=Y";
//		System.out.println("url-----"+url);
	}

	private void insertServiceInvoiceData(HttpServletRequest request) {
		
			msg = "";
			error_msg = "";
			String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
			String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
			String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
			String modUrl = request.getParameter("modUrl")==null?"":request.getParameter("modUrl");
			String year = request.getParameter("year")==null?"":request.getParameter("year");
			String month = request.getParameter("month")==null?"":request.getParameter("month");
			String bill_cycle = request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");
			
			String contact_person_cd = request.getParameter("contact_person_cd")==null?"":request.getParameter("contact_person_cd");
			String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
			String customer_cd = request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
			String cust_inv_seq_no = request.getParameter("inv_seq_no")==null?"":request.getParameter("inv_seq_no");
			String due_dt = request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
			String emp_cd = request.getParameter("emp_cd")==null?"":request.getParameter("emp_cd");
			String financial_year = request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
			String gross_amt_inr = request.getParameter("grossAmt")==null?"":request.getParameter("grossAmt");
			String invoice_dt = request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
			int inv_seq_no = Integer.parseInt(request.getParameter("hlpl_inv_seq_no")==null?"0":request.getParameter("hlpl_inv_seq_no"));
			String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
			String net_amt_inr = request.getParameter("netAmt")==null?"":request.getParameter("netAmt");
			String period_start_dt = request.getParameter("period_start_dt")==null?"":request.getParameter("period_start_dt");
			String period_end_dt = request.getParameter("period_end_dt")==null?"":request.getParameter("period_end_dt");
			String plant_seq_no = request.getParameter("cust_plant_cd")==null?"":request.getParameter("cust_plant_cd");
			String remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
			String remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
			String supplier_cd = request.getParameter("supp_cd")==null?"":request.getParameter("supp_cd");
			String supplier_state_cd = request.getParameter("supp_state_cd")==null?"":request.getParameter("supp_state_cd");
			String calcBase = request.getParameter("calcBase")==null?"":request.getParameter("calcBase");
			String tax_amt_inr = request.getParameter("totalTax")==null?"":request.getParameter("totalTax");
			String tax_flag = request.getParameter("tax_flag")==null?"":request.getParameter("tax_flag");
			String tax_struct_cd = request.getParameter("tax_structure")==null?"":request.getParameter("tax_structure");
			String total_qty = request.getParameter("total_qty")==null?"0":request.getParameter("total_qty");
		
			String sac_cd[] =  request.getParameterValues("sac_cd");
			String line_item[] =  request.getParameterValues("line_item");
			String qty[] =  request.getParameterValues("qty");
			String rate[] =  request.getParameterValues("rate");
			String amt[] =  request.getParameterValues("amt");
			
			String tax_sz = request.getParameter("tax_sz")==null?"":request.getParameter("tax_sz");
			String tax_amt[] =  request.getParameterValues("taxAmt");
			
			int truckInvSz = Integer.parseInt(request.getParameter("truckInvSz")==null?"0":request.getParameter("truckInvSz"));
			String rate_mmbtu = request.getParameter("rate_mmbtu")==null?"":request.getParameter("rate_mmbtu");
			String rate_km = request.getParameter("rate_km")==null?"":request.getParameter("rate_km");
			
			String truckInvDt[] = request.getParameterValues("truckInvDt");
			String truckInvNo[] = request.getParameterValues("truckInvNo");
			String truckNm[] = request.getParameterValues("truckNm");
			String truckInvQty[] = request.getParameterValues("truckInvQty");
			String km[] = request.getParameterValues("km");
			String servInvQtyAmt[] = request.getParameterValues("servInvQtyAmt");
			String inv_km_inr[] = request.getParameterValues("inv_km_inr");
			String lumpsum[] = request.getParameterValues("lumpsum");
			String chkBoxFlg[] = request.getParameterValues("chkBoxFlg");
			
			try {
			
			if(!cust_inv_seq_no.equals("") ) {
				
				String tt="";String am="";String cgst="";String sgst="";String igst="";String tax_cd="";
				String cgst_amt= "0",sgst_amt= "0",igst_amt ="0";
				
				if(tax_sz.equalsIgnoreCase("2")) {
					for(int k=0;k<Integer.parseInt(tax_sz);k++) {
						tax_cd="C";
						//System.out.println("--tax_amts--"+tax_amount);
						if(k==0){
							tt="CGST-"+tax_struct_cd+"%";
							cgst=tax_struct_cd;
							cgst_amt = tax_amt[k];
						}else{
							tt+="SGST-"+tax_struct_cd+"%";
							sgst=tax_struct_cd;
							sgst_amt = tax_amt[k];
						}
					}
					}else{
						tax_cd="I";
						tt="IGST-"+tax_struct_cd+"%";
						igst=tax_struct_cd;
						igst_amt=request.getParameter("taxAmt")==null?"":request.getParameter("taxAmt");
					}
				
				String sn_no="",sn_rev_no = "",flsa_no = "", flsa_rev_no = "";
				
				if(mapping_id.contains("-")) {
					
					String tempMap [] = mapping_id.split("-");
					
					sn_no = tempMap[3];
					sn_rev_no = tempMap[4];
					flsa_no = tempMap[1];
					flsa_rev_no = tempMap[2];
				}
//				System.out.println("--total_qty--"+total_qty);
				String insMstSql = "INSERT INTO DLNG_INVOICE_MST (CONTACT_PERSON_CD,CONTRACT_TYPE,CUSTOMER_CD,NEW_INV_SEQ_NO,"
						+ " DUE_DT,EMP_CD,ENT_DT,FINANCIAL_YEAR,FLAG,GROSS_AMT_INR,INVOICE_DT,HLPL_INV_SEQ_NO,MAPPING_ID,NET_AMT_INR,"
						+ " PERIOD_END_DT,PERIOD_START_DT,PLANT_SEQ_NO,REMARK_1,REMARK_2,SUPPLIER_CD,SUP_STATE_CODE,TAX_AMT_INR,TAX_FLAG,"
						+ "TAX_STRUCT_CD,TOTAL_QTY,CUST_INV_SEQ_NO,APPROVED_BY,APPROVED_DT,APPROVED_FLAG,CHECKED_BY,CHECKED_DT,CHECKED_FLAG,"
						+ " INR_BASE,BILLING_CYCLE,SN_NO,SN_REV_NO,FGSA_NO,FGSA_REV_NO,SALE_PRICE,GROSS_AMT_USD) VALUES"
						+ " ('"+contact_person_cd+"','"+contract_type+"','"+customer_cd+"','"+cust_inv_seq_no+"',to_date('"+due_dt+"','dd/mm/yyyy')"
								+ " ,'"+emp_cd+"',sysdate,'"+financial_year+"','Y','"+gross_amt_inr+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),"
								+ " '"+inv_seq_no+"','"+mapping_id+"','"+net_amt_inr+"',to_date('"+period_end_dt+"','dd/mm/yyyy'),to_date('"+period_start_dt+"','dd/mm/yyyy'), "
								+ " '"+plant_seq_no+"','"+remark_1+"','"+remark_2+"','"+supplier_cd+"','"+supplier_state_cd+"','"+tax_amt_inr+"',"
								+ " '"+tax_cd+"','"+tax_struct_cd+"','"+total_qty+"','"+inv_seq_no+"','','','','','','','"+calcBase+"','"+bill_cycle+"',"
								+ " '"+sn_no+"','"+sn_rev_no+"','"+flsa_no+"','"+flsa_rev_no+"','0','0')";				
//				System.out.println("insMstSql----"+insMstSql);
				stmt.execute(insMstSql);
					
				for(int i = 0 ; i < sac_cd.length ; i++) {
					
					String  insDtlSql = "INSERT INTO DLNG_SERVICE_INVOICE_DTL (CONTRACT_TYPE,EFF_DT,ENTER_BY,ENTER_DT,FINANCIAL_YEAR,FLAG"
							+ " , INV_SEQ_NO,ITEM_DESCRIPTION,LINE_NO,MAPPING_ID,QUANTITY,RATE,SAC_CODE,SUPPLIER_CD,TAX_CD,TAX_DETAILS,"
							+ " RATE_CGST,RATE_IGST,RATE_SGST,CARGO_AMOUNT,CGST_AMT,IGST_AMT,SGST_AMT)"
							+ " VALUES ('"+contract_type+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),'"+emp_cd+"',sysdate,'"+financial_year+"','Y',"
									+ " '"+inv_seq_no+"','"+line_item[i]+"','"+i+"','"+mapping_id+"','"+qty[i]+"','"+rate[i]+"','"+sac_cd[i]+"',"
									+ " '"+supplier_cd+"','"+tax_cd+"','"+tt+"','"+cgst+"','"+igst+"','"+sgst+"','"+amt[i]+"','"+cgst_amt+"','"+igst_amt+"','"+sgst_amt+"') ";
//					System.out.println("insDtlSql----"+insDtlSql);
					stmt.execute(insDtlSql);
				}
				
				for(int i = 0 ; i < truckInvSz ; i++) {
					String insAttachSql = "";
					if(chkBoxFlg[i].equals("Y")) {
						
						if(calcBase.equals("1")) {
							
							insAttachSql = "INSERT INTO DLNG_SERVICE_INVOICE_ATTACH (AMOUNT,CALC_BASE,INVOICE_QTY,KM,RATE,SERVICE_INVOICE_NO,SERVICE_INV_DT,TRUCK_INV_DT,TRUCK_INV_NO,TRUCK_NM)"
									+ " VALUES ('"+servInvQtyAmt[i]+"','"+calcBase+"','"+truckInvQty[i]+"','','"+rate_mmbtu+"','"+cust_inv_seq_no+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),to_date('"+truckInvDt[i]+"','dd/mm/yyyy'),'"+truckInvNo[i]+"','"+truckNm[i]+"')";
						}else if(calcBase.equals("2")) {
							
							insAttachSql = "INSERT INTO DLNG_SERVICE_INVOICE_ATTACH (AMOUNT,CALC_BASE,INVOICE_QTY,KM,RATE,SERVICE_INVOICE_NO,SERVICE_INV_DT,TRUCK_INV_DT,TRUCK_INV_NO,TRUCK_NM)"
									+ " VALUES ('"+inv_km_inr[i]+"','"+calcBase+"','"+truckInvQty[i]+"','"+km[i]+"','"+rate_km+"','"+cust_inv_seq_no+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),to_date('"+truckInvDt[i]+"','dd/mm/yyyy'),'"+truckInvNo[i]+"','"+truckNm[i]+"')";
						}else if(calcBase.equals("3")) {
							
							insAttachSql = "INSERT INTO DLNG_SERVICE_INVOICE_ATTACH (AMOUNT,CALC_BASE,INVOICE_QTY,KM,RATE,SERVICE_INVOICE_NO,SERVICE_INV_DT,TRUCK_INV_DT,TRUCK_INV_NO,TRUCK_NM)"
									+ " VALUES ('"+lumpsum[i]+"','"+calcBase+"','"+truckInvQty[i]+"','','1','"+cust_inv_seq_no+"',to_date('"+invoice_dt+"','dd/mm/yyyy'),to_date('"+truckInvDt[i]+"','dd/mm/yyyy'),'"+truckInvNo[i]+"','"+truckNm[i]+"')";
						}
//						System.out.println("insAttachSql---"+insAttachSql);
						if(!insAttachSql.equals("")) {
							stmt.execute(insAttachSql);
						}
					}
				}
				
				msg = "Invoice Number : "+cust_inv_seq_no+" Successfully Generated";
				dbcon.commit();
			}
			
		} catch (Exception e) {
			try {
				dbcon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			error_msg = "Failed : Invoice Number : "+inv_seq_no+ " Generation unsuccessfull";
		}
		url = "../general_inv/frm_prepare_service_invoice.jsp?formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&year="+year+"&month="+month+"&bill_cycle="+bill_cycle+"&msg="+msg+"&error_msg="+error_msg;
//		System.out.println("url-----"+url);
	}
}
