package com.seipl.hazira.dlng.mrcr;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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

@WebServlet("/servlet/Frm_LC_Monitoring")
public class Frm_LC_Monitoring extends HttpServlet{
	
public static  Connection dbcon;
	
	//Following NumberFormat Object is defined by Samik Shah ... On 4th August, 2010 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("###########0.000");
	NumberFormat nf3 = new DecimalFormat("###########0.0000");
	
	//Following Object Has Been Defined By Achal On 4th August, 2010 ...
	public escapeSingleQuotes snglQuot = new escapeSingleQuotes();
	
	
	public String servletName = "Frm_Contract_Master";
	public String methodName = "";
	public String option = "";
	public String url = "";
	public String form_name = "";
	public String msg = "";
	int count = 0;
	
	private static String queryString = null;
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
	//For Mailing HARSH20201214
	String host="mail.barodainformatics.com";
    String from_mail="";
    String to_mail="";
    String from_pwd="";
    String dbline="";
    String username="";
    String password="";
    String encrypted="";
    ////
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
				stmt2 = dbcon.createStatement(); 
				stmt3 = dbcon.createStatement();
				stmt4 = dbcon.createStatement();
				//System.out.println("Set Dbcon");
			}
			
			option=req.getParameter("option")==null?"":req.getParameter("option");
			
			write_permission = req.getParameter("write_permission")==null?"N":req.getParameter("write_permission");
			delete_permission = req.getParameter("delete_permission")==null?"N":req.getParameter("delete_permission");
			print_permission = req.getParameter("print_permission")==null?"N":req.getParameter("print_permission");
			approve_permission = req.getParameter("approve_permission")==null?"N":req.getParameter("approve_permission");
			audit_permission = req.getParameter("audit_permission")==null?"N":req.getParameter("audit_permission");
			
			form_id = req.getParameter("form_id")==null?"0":req.getParameter("form_id");
			form_nm = req.getParameter("form_nm")==null?"":req.getParameter("form_nm");
			
			if(option.equalsIgnoreCase("LC_DETAILS_ENTRY"))
			{
				InsertCRSecurityMonitoring(req,res);
			}	
			if(option.equalsIgnoreCase("DEAL_CAPTURING"))
			{
				InsertDealCapturing(req,res);
			}
			if(option.equalsIgnoreCase("LIMIT_RATING_DETAILS_ENTRY"))
			{
				InsertLimitRating(req,res);
			}
			if(option.equalsIgnoreCase("APPROVAL_REPORT"))
			{
				Update_approval_report(req,res);
			}
			if(option.equalsIgnoreCase("CREDIT_LINE_DETAILS_ENTRY"))
			{
				UpdateCreditlineDetail(req,res);
			}
			if(option.equalsIgnoreCase("SECURITY_POST_DTL"))
			{
				InsertSecurityPostDtl(req,res);
			}
			if(option.equalsIgnoreCase("CR_SECURITY_POST_DTL"))
			{
				InsertCRSecurityPostDtl(req,res);
			}
			if(option.equalsIgnoreCase("HOLIDAY_SHEDUAL"))
			{
				InsertHolidayShedual(req,res);
			}
			if(option.equalsIgnoreCase("EDIT_HOLIDAY"))
			{
				Update_Curve_Holiday(req,res);
			}
			if(option.equalsIgnoreCase("PRE_DEAL_CREDIT_CHECK_REQ"))//HARSH20201212
			{
				InsertPreDealCreditCheckReq(req,res);
			}
			if(option.equalsIgnoreCase("PRE_DEAL_CREDIT_APPROVAL"))//HARSH20201224
			{
				InsertPreDealCreditApproval(req,res);
			}
			if(option.equalsIgnoreCase("DELETE_REOPEN_SECURITY")) //HARSH20210616
			{
				deleteReopenSecurity(req,res);
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
					System.out.println("rset is not close " + e);
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
					System.out.println("rset1 is not close " + e);
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
					System.out.println("rset1 is not close " + e);
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
					System.out.println("rset1 is not close " + e);
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
					System.out.println("rset1 is not close " + e);
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
					System.out.println("stmt is not close " + e);
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
					System.out.println("stmt1 is not close " + e);
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
					System.out.println("stmt1 is not close " + e);
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
					System.out.println("stmt1 is not close " + e);
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
					System.out.println("stmt1 is not close " + e);
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
					System.out.println("conn is not close " + e);
				}
			}
		}
//		System.out.println("url*****"+url);
		res.sendRedirect(url);
	}


	private void InsertCRSecurityMonitoring(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException {
		String err_msg=""; 
		try {
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			String buyer_cd = req.getParameter("buyer_cd")==null?"":req.getParameter("buyer_cd");
			String lc_gen_dt = req.getParameter("lc_gen_dt")==null?"":req.getParameter("lc_gen_dt");
			String secTyp = req.getParameter("secTyp")==null?"":req.getParameter("secTyp");
			String fgca_no = req.getParameter("fgca_no")==null?"":req.getParameter("fgca_no");
			String fgca_rev_no = req.getParameter("fgca_rev_no")==null?"":req.getParameter("fgca_rev_no");
			String sn_no = req.getParameter("sn_no")==null?"":req.getParameter("sn_no");
			String sn_rev_no = req.getParameter("sn_rev_no")==null?"":req.getParameter("sn_rev_no");
			String cont_type = req.getParameter("cont_type")==null?"":req.getParameter("cont_type");
			String cust_rate = req.getParameter("cust_rate")==null?"":req.getParameter("cust_rate");
			String eff_dt3 = req.getParameter("eff_dt3")==null?"":req.getParameter("eff_dt3");
			String operation = req.getParameter("operation")==null?"":req.getParameter("operation");
			String modify_fin_yr = req.getParameter("fin_yr")==null?"":req.getParameter("fin_yr");
			String modify_lc_seq_no = req.getParameter("lc_seq_no")==null?"":req.getParameter("lc_seq_no");
			String ref_no = req.getParameter("ref_no")==null?"":req.getParameter("ref_no");
			
			String Existing_deal = req.getParameter("existing_dealcd")==null?"":req.getParameter("existing_dealcd");
			
			String credit = "";
			String eff_dt = "";
			String security_flag="";
			int record_found=0;
			
			if(secTyp.equals("cust")) {
				
//				System.out.println("req.getParameter(credit)****"+req.getParameter("credit"));
				
				credit = req.getParameter("credit")==null?"":req.getParameter("credit");
				if(credit==null || credit.equals("null") || credit.equals("")) {
					credit = req.getParameter("hid_credit")==null?"":req.getParameter("hid_credit");
				}
				eff_dt = req.getParameter("eff_dt1")==null?"":req.getParameter("eff_dt1");
				security_flag="Y";
			}
			if(secTyp.equals("issuance")) {
				credit = req.getParameter("credit1")==null?"":req.getParameter("credit1");
				if(credit==null || credit.equals("null") || credit.equals("")) {
					credit = req.getParameter("hid_credit1")==null?"":req.getParameter("hid_credit1");
				}
				eff_dt = req.getParameter("eff_dt2")==null?"":req.getParameter("eff_dt2");
				security_flag="O";
			}
			if(credit.equals("PCG")) {
				/*fgca_no="0";
				fgca_rev_no="0";
				sn_no="0";
				sn_rev_no="0";*/
				cont_type="A";
			//	security_flag="A";
			} else {
			///	security_flag="Y";
			}
//			System.out.println("req.getParameter(credit)****"+credit);
			String from_dt = req.getParameter("lc_from_dt")==null?"":req.getParameter("lc_from_dt");
			String to_dt = req.getParameter("lc_to_dt")==null?"":req.getParameter("lc_to_dt");
			String final_lc_amount = req.getParameter("final_lc_amount")==null?"":req.getParameter("final_lc_amount");
			String lc_amount = req.getParameter("lc_amount")==null?"":req.getParameter("lc_amount");
			String remarks = req.getParameter("remarks")==null?"":req.getParameter("remarks");
			
			String fin_yr = "";
			String lc_seq_no="";
			
			
			if(!buyer_cd.equals("") && !lc_gen_dt.equals("") && !secTyp.equals("")) {
				
				if(operation.equals("MODIFY")) {
					
					if(!modify_fin_yr.equals("") && !modify_lc_seq_no.equals("")) {
						
						String checkAvail="select nvl(count(*),0) from FMS7_LC_MST where CUSTOMER_CD='"+buyer_cd+"' and FINANCIAL_YEAR='"+modify_fin_yr+"'"
								+ " and LC_SEQ_NO='"+modify_lc_seq_no+"'";
//						System.out.println("checkAvail*****"+checkAvail);
						rset = stmt.executeQuery(checkAvail);
						if(rset.next()) {
							record_found = rset.getInt(1);
							
							if(record_found > 0) {
								if(Double.parseDouble(final_lc_amount)>=0)
								{
								String FMS7_LC_MST_UPDATE = "update FMS7_LC_MST set CREDIT_RATING='"+cust_rate+"', RATING_EFF_DATE= TO_DATE('"+eff_dt3+"','DD/MM/YYYY'),"
										+ " LC_REF_DATE=TO_DATE('"+lc_gen_dt+"','DD/MM/YYYY'), START_DATE=TO_DATE('"+from_dt+"','DD/MM/YYYY'),"
										+ " END_DATE=TO_DATE('"+to_dt+"','DD/MM/YYYY'),FINAL_LC_AMOUNT='"+final_lc_amount+"',REMARKS='"+remarks+"',EMP_CD='"+user_cd+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',"
										+ " FLAG='"+security_flag+"',SEC_DT=TO_DATE('"+eff_dt+"','DD/MM/YYYY'),SEC_REF_NO='"+ref_no+"'"
										+ " where CUSTOMER_CD='"+buyer_cd+"' and LC_SEQ_NO='"+modify_lc_seq_no+"' and FINANCIAL_YEAR='"+modify_fin_yr+"'";
//								System.out.println("FMS7_LC_MST_UPDATE****"+FMS7_LC_MST_UPDATE);	
								stmt1.execute(FMS7_LC_MST_UPDATE);
								
								
								String FMS7_LC_DTL_UPDATE="update FMS7_LC_DTL set LC_METHOD='"+credit+"',FLAG= '"+security_flag+"',CONT_TYPE='"+cont_type+"' "
										+ " WHERE FINANCIAL_YEAR='"+modify_fin_yr+"' and LC_SEQ_NO='"+modify_lc_seq_no+"' and CUSTOMER_CD='"+buyer_cd+"' and FGSA_NO='"+fgca_no+"' and "
										+ " SN_NO='"+sn_no+"'  and FGSA_REV_NO='"+fgca_rev_no+"' and SN_REV_NO='"+sn_rev_no+"' and "
										+ " SN_START_DATE=TO_DATE('"+from_dt+"','DD/MM/YYYY') and SN_END_DATE=TO_DATE('"+to_dt+"','DD/MM/YYYY')";
//								System.out.println("FMS7_LC_DTL_UPDATE****"+FMS7_LC_DTL_UPDATE);
								stmt1.execute(FMS7_LC_DTL_UPDATE);
								msg = "Modification Done Successfully for "+credit;
								}
								else
								{
									String FMS7_LC_MST_UPDATE = "DELETE FROM FMS7_LC_MST "
											+ " where CUSTOMER_CD='"+buyer_cd+"' and LC_SEQ_NO='"+modify_lc_seq_no+"' and FINANCIAL_YEAR='"+modify_fin_yr+"'";
//									System.out.println("FMS7_LC_MST_UPDATE****"+FMS7_LC_MST_UPDATE);	
									stmt1.execute(FMS7_LC_MST_UPDATE);
									String FMS7_LC_DTL_UPDATE="DELETE FROM FMS7_LC_DTL "
											+ " WHERE FINANCIAL_YEAR='"+modify_fin_yr+"' and LC_SEQ_NO='"+modify_lc_seq_no+"' and CUSTOMER_CD='"+buyer_cd+"' and FGSA_NO='"+fgca_no+"' and "
											+ " SN_NO='"+sn_no+"'  and FGSA_REV_NO='"+fgca_rev_no+"' and SN_REV_NO='"+sn_rev_no+"' and "
											+ " SN_START_DATE=TO_DATE('"+from_dt+"','DD/MM/YYYY') and SN_END_DATE=TO_DATE('"+to_dt+"','DD/MM/YYYY')";
//									System.out.println("FMS7_LC_DTL_UPDATE****"+FMS7_LC_DTL_UPDATE);
									stmt1.execute(FMS7_LC_DTL_UPDATE);
									msg = "Deletion Done Successfully for "+credit;
								}
								
							}else {
								err_msg="No Record Found For Modification!!";
								
							}
						}
					}else {
						err_msg="Financial Year/LC Sequence No. Not Available!!";
					}
					
				}else {
					/*get current fin year*/
					String fin_yrSQL = "SELECT EXTRACT (YEAR FROM ADD_MONTHS (SYSDATE, -3))"
							+ " || '-'"
							+ " || EXTRACT (YEAR FROM ADD_MONTHS (SYSDATE, 9)) FROM DUAL";
//					System.out.println("fin_yrSQL********"+fin_yrSQL);
					rset = stmt.executeQuery(fin_yrSQL);
					if(rset.next()) {
						fin_yr = rset.getString(1)==null?"":rset.getString(1);
					}
					
					String max_lc_seq_noSQL = "select max(nvl(LC_SEQ_NO,1)+1) from FMS7_LC_MST where CUSTOMER_CD='"+buyer_cd+"' "
							+ " and FINANCIAL_YEAR='"+fin_yr+"' ";	
//					System.out.println("max_lc_seq_noSQL********"+max_lc_seq_noSQL);
					rset = stmt.executeQuery(max_lc_seq_noSQL);
					if(rset.next()) {
						lc_seq_no = rset.getString(1)==null?"1":rset.getString(1);
					}
					
					String FMS7_LC_MST="insert into FMS7_LC_MST (CUSTOMER_CD,EMP_CD,ENT_DT,FINAL_LC_AMOUNT,"
							+ " FINANCIAL_YEAR,FLAG,LC_REF_DATE,LC_SEQ_NO,REMARKS,START_DATE,END_DATE,CREDIT_RATING,RATING_EFF_DATE,SEC_DT,SEC_REF_NO)"
							+ " values ('"+buyer_cd+"','"+user_cd+"',sysdate,'"+final_lc_amount+"',"
							+ " '"+fin_yr+"','"+security_flag+"',TO_DATE('"+lc_gen_dt+"','DD/MM/YYYY'),"
							+ " '"+lc_seq_no+"','"+remarks+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY'),'"+cust_rate+"',TO_DATE('"+eff_dt3+"','DD/MM/YYYY'),TO_DATE('"+eff_dt+"','DD/MM/YYYY'),'"+ref_no+"')";
					
//					System.out.println("FMS7_LC_MST********"+FMS7_LC_MST);
					stmt.execute(FMS7_LC_MST);
					
					String FMS7_LC_DTL="insert into FMS7_LC_DTL (FINANCIAL_YEAR,LC_SEQ_NO,CUSTOMER_CD,FGSA_NO,SN_NO,CONT_TYPE,FGSA_REV_NO,SN_REV_NO,"
							+ " SN_START_DATE,SN_END_DATE,LC_METHOD,EMP_CD,ENT_DT,FLAG,EXISTING)"
							+ " VALUES "
							+ " ( '"+fin_yr+"','"+lc_seq_no+"','"+buyer_cd+"','"+fgca_no+"','"+sn_no+"','"+cont_type+"','"+fgca_rev_no+"','"+sn_rev_no+"',"
							+ " TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY'),'"+credit+"','"+user_cd+"',sysdate,'"+security_flag+"','"+Existing_deal+"')";  //HARSH_20201020 Added Existing column
//					System.out.println("FMS7_LC_DTL********"+FMS7_LC_DTL);
					stmt.execute(FMS7_LC_DTL);
					msg=credit+" Data Submitted Successfully!!!";
					
				}
			}
			
			
			url="../mrcr/frm_CR_SecurityLC_monitoring.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			dbcon.commit();
		}catch (Exception e) {
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_CR_SecurityLC_monitoring.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}		
	}
	///Insertion Function
	private void InsertDealCapturing(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException {
			String err_msg=""; 
			try {
				HttpSession session = req.getSession();
				String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
				
//HARSH20201015	//String trans_cd = req.getParameter("trans_cd")==null?"":req.getParameter("trans_cd");
				String buyer_cd = req.getParameter("buyer_cd")==null?"":req.getParameter("buyer_cd");
				String customer_abbr = req.getParameter("customer_abbr")==null?"":req.getParameter("customer_abbr"); //HARSH20210212
				String lc_gen_dt = req.getParameter("lc_gen_dt")==null?"":req.getParameter("lc_gen_dt");
				String secTyp = req.getParameter("secTyp")==null?"":req.getParameter("secTyp");
				String fgca_no = req.getParameter("fgca_no")==null?"":req.getParameter("fgca_no");
				String fgca_rev_no = req.getParameter("fgca_rev_no")==null?"":req.getParameter("fgca_rev_no");
				String sn_no = req.getParameter("sn_no")==null?"":req.getParameter("sn_no");
				String sn_rev_no = req.getParameter("sn_rev_no")==null?"":req.getParameter("sn_rev_no");
				String cont_type = req.getParameter("cont_type")==null?"":req.getParameter("cont_type");
				//String cust_rate = req.getParameter("cust_rate")==null?"":req.getParameter("cust_rate");
				//String eff_dt3 = req.getParameter("eff_dt3")==null?"":req.getParameter("eff_dt3");
				String operation = req.getParameter("operation")==null?"":req.getParameter("operation");
				String ref_no = req.getParameter("ref_no")==null?"":req.getParameter("ref_no");
				String modify_fin_yr = req.getParameter("fin_yr")==null?"":req.getParameter("fin_yr");
				String modify_lc_seq_no = req.getParameter("lc_seq_no")==null?"":req.getParameter("lc_seq_no");
				String currency = req.getParameter("currency")==null?"":req.getParameter("currency");
				String remarks = req.getParameter("remarks")==null?"":req.getParameter("remarks");
				remarks=remarks.replaceAll("'", "''"); //LIVE ISSUE //SINGLE QUORT IN REMARK HARSH20210430
				String sec_ref_no = req.getParameter("ref_no")==null?"":req.getParameter("ref_no");
				String cust_abbr = req.getParameter("cust_abbr")==null?"":req.getParameter("cust_abbr");
				
				String oldValues = req.getParameter("old_value")==null?"":req.getParameter("old_value"); //HARSH20210108
				String newValues=""; //HARSH20210107
				
							
				if(currency.equals(""))
				{
					currency=req.getParameter("hid_currency")==null?"":req.getParameter("hid_currency");
				}
				
				String i_dt = req.getParameter("i_dt")==null?"":req.getParameter("i_dt");
				String e_dt = req.getParameter("e_dt")==null?"":req.getParameter("e_dt");
			
				String mappingID = req.getParameter("existing_dealcd")==null?"":req.getParameter("existing_dealcd"); // use for existing deal no
				String deal_no = req.getParameter("deal_no")==null?"":req.getParameter("deal_no"); // deal no
				if(deal_no.equals("")) //AS REQUESTED BY JAYASRAI, AS PER INCIDENT#216 //HARSH20211218  
				{
					deal_no="0";
				}
				String credit = "";
				String eff_dt = "";
				String security_flag="";
				String Existing_deal ="";
				String issued="";
				String received="";
				String btn_type="";//HARSH2020216
				
				if(secTyp.equals("cust")) {
					credit = req.getParameter("credit")==null?"":req.getParameter("credit");
					if(credit==null || credit.equals("null") || credit.equals("")) {
						credit = req.getParameter("hid_credit")==null?"":req.getParameter("hid_credit");
					}
					eff_dt = req.getParameter("eff_dt1")==null?"":req.getParameter("eff_dt1");
					//security_flag="N";
					received="R";
					issued="";
					security_flag="R"; //HARSH20210112
					btn_type="Incoming";//HARSH2020216
				}
				if(secTyp.equals("issuance")) {
					credit = req.getParameter("credit1")==null?"":req.getParameter("credit1");
					if(credit==null || credit.equals("null") || credit.equals("")) {
						credit = req.getParameter("hid_credit1")==null?"":req.getParameter("hid_credit1");
					}
					eff_dt = req.getParameter("eff_dt2")==null?"":req.getParameter("eff_dt2");
					//security_flag="O";
					received="";
					issued="I";
					security_flag="I"; //HARSH20210112
					btn_type="Outgoing";//HARSH2020216
				}
				//HARSH20210130 AS PER CUSTOMER FEEDBACK ON 29/01/2021
				/*if(credit.equals("PCG")) {
					cont_type="A";
					Existing_deal = cont_type+deal_no.substring(1,deal_no.length()); //use for deal no
				}else{*/
					Existing_deal = deal_no;
				//}
				
				
				String from_dt = req.getParameter("from_dt")==null?"":req.getParameter("from_dt");
				String to_dt = req.getParameter("to_dt")==null?"":req.getParameter("to_dt");
				String tcq = req.getParameter("tcq")==null?"":req.getParameter("tcq"); //HARSH20210216
				String dcq = req.getParameter("dcq")==null?"":req.getParameter("dcq"); //HARSH20210216
				
				String final_lc_amount = req.getParameter("final_lc_amount")==null?"":req.getParameter("final_lc_amount");
				
				String deal_type = req.getParameter("deal_type")==null?"":req.getParameter("deal_type");
				String vari_value = req.getParameter("vari_value")==null?"":req.getParameter("vari_value");
				String value_fluc = req.getParameter("value_fluc")==null?"":req.getParameter("value_fluc");
				String iss_b_cd = req.getParameter("iss_b_cd")==null?"":req.getParameter("iss_b_cd");
				String iss_b_ref = req.getParameter("iss_b_ref")==null?"":req.getParameter("iss_b_ref");
				String adv_b_cd = req.getParameter("adv_b_cd")==null?"":req.getParameter("adv_b_cd");
				String adv_b_ref = req.getParameter("adv_b_ref")==null?"":req.getParameter("adv_b_ref");
				String con_b_cd = req.getParameter("con_b_cd")==null?"":req.getParameter("con_b_cd");
				String con_b_ref = req.getParameter("con_b_ref")==null?"":req.getParameter("con_b_ref");
				String tenor = req.getParameter("tenor")==null?"":req.getParameter("tenor");
				//,,,,,,,
				String fin_yr = "";
				String lc_seq_no="";
				int record_found=0;
				
				
				//HARSH20210107
				newValues="CustABBR="+customer_abbr+"#SecRefNo="+sec_ref_no+"#DealNo="+deal_no+"#SecTy="+credit+"#DueDt="+eff_dt+"#Currency="+currency+"#Amt="+final_lc_amount+"#Rmk="+remarks+"#InOut="+security_flag;
				//
				//HARSH20210212 if(!buyer_cd.equals("") && !secTyp.equals(""))
				if(!customer_abbr.equals("") && !secTyp.equals(""))
				{	
					if(operation.equals("MODIFY")) 
					{
						if(!modify_lc_seq_no.equals("")) 
						{
							//HARSH20210212 String checkAvail="select nvl(count(*),0) from FMS9_SECURITY_POST_MST where CUSTOMER_CD='"+buyer_cd+"' and SEQ_NO='"+modify_lc_seq_no+"'";
							String checkAvail="select nvl(count(*),0) from FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+modify_lc_seq_no+"'"; //HARSH20210212
							rset1 = stmt1.executeQuery(checkAvail);
							if(rset1.next()) 
							{
								record_found = rset1.getInt(1);
								if(record_found > 0) 
								{
									//HARSH20210213 String FMS9_SECURITY_POST_MST_UPDATE = "update FMS9_SECURITY_POST_MST set SEC_TYPE='"+credit+"',VALUE='"+final_lc_amount+"',REC_DT=TO_DATE('"+eff_dt+"','DD/MM/YYYY'),ISSU_DT=TO_DATE('"+i_dt+"','DD/MM/YYYY'),EXP_DT=TO_DATE('"+e_dt+"','DD/MM/YYYY'),LINK='"+Existing_deal+"',ISSUED='"+issued+"',RECEIVED='"+received+"',REMARKS='"+remarks+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',currency='"+currency+"' "
									//		+ "where CUSTOMER_CD='"+buyer_cd+"' and SEQ_NO='"+modify_lc_seq_no+"'";
									
									String FMS9_SECURITY_POST_MST_UPDATE = "update FMS9_SECURITY_POST_MST set SEC_TYPE='"+credit+"',VALUE='"+final_lc_amount+"',REC_DT=TO_DATE('"+eff_dt+"','DD/MM/YYYY'),ISSU_DT=TO_DATE('"+i_dt+"','DD/MM/YYYY'),EXP_DT=TO_DATE('"+e_dt+"','DD/MM/YYYY'),LINK='"+Existing_deal+"',ISSUED='"+issued+"',RECEIVED='"+received+"',REMARKS='"+remarks+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',currency='"+currency+"' "
													+ "where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+modify_lc_seq_no+"'";
									//HARSH20210213 ADDED CUSTOMER_ABBR //NOW WE USE CUSTOMER_ABBR INSTAED OF CUSTOMER_CD
									stmt1.executeUpdate(FMS9_SECURITY_POST_MST_UPDATE);
									msg = "Modification Done Successfully for "+credit;
								}
								else 
								{
									err_msg="No Record Found For Modification!!";
								}
							}
						}
						else 
						{
							err_msg="Sequence No. Not Available!!";
						}	
					}
					else
					{	
						//HARSH20210213 String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_SECURITY_POST_MST where CUSTOMER_CD='"+buyer_cd+"'";
						
						String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"'";
						rset = stmt.executeQuery(max_seq_no);
						if(rset.next()) {
							lc_seq_no = rset.getString(1)==null?"1":rset.getString(1);
						}
						if(sec_ref_no.equals(""))
						{
							//HARSH20210215 sec_ref_no = customer_abbr+"-"+buyer_cd+"-"+lc_seq_no;
							sec_ref_no = customer_abbr+"-S-"+lc_seq_no;
						}
						else
						{
							sec_ref_no = req.getParameter("ref_no")==null?"":req.getParameter("ref_no");
						}
						newValues="CustABBR="+customer_abbr+"#SecRefNo="+sec_ref_no+"#DealNo="+deal_no+"#SecTy="+credit+"#DueDt="+eff_dt+"#Currency="+currency+"#Amt="+final_lc_amount+"#Rmk="+remarks+"#InOut="+security_flag;
						
						String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,MAPPING_ID,SEQ_NO,SEC_TYPE,VALUE,REC_DT,LINK,ISSUED,RECEIVED,ENT_CD,ENT_DT,ISSU_DT,EXP_DT,ISS_BANK_REF,CURRENCY,DEAL_TYPE,VARIATION_VALUE,VALUE_FLUC,ISS_BANK_CD,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,TENOR,REMARKS,STATUS,REF_NO) "
								+ "values('"+buyer_cd+"','"+customer_abbr+"','"+mappingID+"','"+lc_seq_no+"','"+credit+"','"+final_lc_amount+"',TO_DATE('"+eff_dt+"','DD/MM/YYYY'),'"+Existing_deal+"','"+issued+"','"+received+"','"+user_cd+"',sysdate,TO_DATE('"+i_dt+"','DD/MM/YYYY'),TO_DATE('"+e_dt+"','DD/MM/YYYY'),'"+iss_b_ref+"','"+currency+"','"+deal_type+"','"+vari_value+"','"+value_fluc+"','"+iss_b_cd+"','"+adv_b_cd+"','"+adv_b_ref+"','"+con_b_cd+"','"+con_b_ref+"','"+tenor+"','"+remarks+"','Pending','"+sec_ref_no+"')";
						stmt.executeUpdate(FMS9_SECURITY_POST_MST);
						msg=credit+" Data Submitted Successfully!!!";
					}
					
					///GENERATE LOG FOR PRE-RECEIPT HARSH20210315
					String dtl_seq_no = ""; String seq_no = "";
					if(operation.equals("MODIFY")) 
					{
						seq_no = modify_lc_seq_no;
						String max_dtl_seq_no = "select max(nvl(DTL_SEQ_NO,1)+1) from LOG_FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+modify_lc_seq_no+"'";
						rset = stmt.executeQuery(max_dtl_seq_no);
						if(rset.next()) {
							dtl_seq_no = rset.getString(1)==null?"1":rset.getString(1);
						}
					}
					else
					{
						seq_no = lc_seq_no;
						String max_dtl_seq_no = "select max(nvl(DTL_SEQ_NO,1)+1) from LOG_FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+lc_seq_no+"'";
						rset = stmt.executeQuery(max_dtl_seq_no);
						if(rset.next()) {
							dtl_seq_no = rset.getString(1)==null?"1":rset.getString(1);
						}
					}
					
					String LOG_FMS9_SECURITY_POST_MST = "INSERT INTO LOG_FMS9_SECURITY_POST_MST SELECT CUSTOMER_CD,SEQ_NO,'"+dtl_seq_no+"',sysdate,"
							+ "SEC_TYPE,DEAL_TYPE,GUARANTOR_NM,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,"
							+ "ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,"
							+ "EXP_DT,RENEW_DT,TENOR,STATUS,REMARKS,LINK,ISSUED,RECEIVED,MAPPING_ID,"
							+ "REVIEW_DT,REF_NO,ENT_CD,ENT_DT,MODIFY_DT,"
							+ "MODIFY_BY,APRV_DT,APRV_BY,FLAG,CUSTOMER_ABBR,GUARANTOR_ABBR,CANCEL_DT,INORDER_HIST,COUNTERPARTY_CD "
							+ "FROM FMS9_SECURITY_POST_MST "
							+ "WHERE CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO='"+seq_no+"'";
					stmt.executeUpdate(LOG_FMS9_SECURITY_POST_MST);
				}
				//msg=msg.replaceAll(" ", "");
				//url="../mrcr/frm_CR_Deal_Capturing.jsp?msg="+msg+"&customer_cd="+buyer_cd+"&cust_abbr="+customer_abbr+"&cont_type="+Existing_deal+"&from_dt="+from_dt+"&to_dt="+to_dt+"&tcq="+tcq+"&dcq="+dcq+"&form_id="+form_id+"&form_nm="+form_nm+"&btn_type="+btn_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
				url="../mrcr/frm_CR_Deal_Capturing.jsp?msg="+msg+"&flg=update&activity=update&rev_no="+fgca_rev_no+"&SN_NO="+sn_no+"&SN_REV_NO="+sn_rev_no+"&FGSA_CD="+fgca_no+"&bscode="+buyer_cd+"&customer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
				try
				{
					//HARSH20210110 new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg+"$NEW$"+newValues+"%OLD%"+oldValues);
					InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,oldValues,newValues,msg); //HARSH20210110
				}
				catch(Exception infoLogger)
				{
					System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
					infoLogger.printStackTrace();
				}
				dbcon.commit();
			}catch (Exception e) {
				e.printStackTrace();
				dbcon.rollback();
				err_msg="Data Not Submitted!!!";
				msg="Data Not Submitted!!!";
				url="../mrcr/frm_CR_Deal_Capturing.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}		
		}
////////////MALVIKA20201017////////////////////////////
private void Update_approval_report(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException 
{
	String err_msg=""; 
	try {
		HttpSession session = req.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String financial_y = req.getParameter("financial_y")==null?"":req.getParameter("financial_y");
		String lc_seq_no = req.getParameter("lc_seq_no")==null?"":req.getParameter("lc_seq_no");
		String cust_cd = req.getParameter("cust_cd")==null?"":req.getParameter("cust_cd");
		//String R_fgsa_no = req.getParameter("R_fgsa_no")==null?"":req.getParameter("R_fgsa_no");
		//String R_sn_no = req.getParameter("R_sn_no")==null?"":req.getParameter("R_sn_no");
		//String R_fgsa_rev_no = req.getParameter("R_fgsa_rev_no")==null?"":req.getParameter("R_fgsa_rev_no");
		//String R_sn_rev_no = req.getParameter("R_sn_rev_no")==null?"":req.getParameter("R_sn_rev_no");
		String aprv_dt = req.getParameter("aprv_dt")==null?"":req.getParameter("aprv_dt");
		String remark = req.getParameter("remark")==null?"":req.getParameter("remark");
		
		String UPDATE_approval_report ="update FMS7_LC_MST set APRV_DT=TO_DATE('"+aprv_dt+"','DD/MM/YYYY'), APRV_BY='"+user_cd+"', REMARKS='"+remark+"' "
				+ "where FINANCIAL_YEAR = '"+financial_y+"' and LC_SEQ_NO = '"+lc_seq_no+"' and CUSTOMER_CD='"+cust_cd+"'  ";
		
//		System.out.println("UPDATE_approval_report   : "+UPDATE_approval_report);
		stmt1.execute(UPDATE_approval_report);
		msg = "Approval Done Successfully...";
		url="../mrcr/frm_CR_Approval.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		
		dbcon.commit();
		
	}
	catch (Exception e) {
		e.printStackTrace();
		dbcon.rollback();
		err_msg="Data Not Submitted!!!";
		url="../mrcr/frm_CR_Approval.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
	}

	
}

//////////////////////////////////////////////////////

	private void InsertLimitRating(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException {
		String err_msg="",mesge="",old_mesge="";
		msg="";String buyer_cd = "";String bank_cd = "";String parent_flag="";
		try
		{
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			/////
			String index = req.getParameter("index")==null?"":req.getParameter("index"); //HARSH20210105 Only use for select customer name dropdown
			/////
			//String buyer_cd = "";//req.getParameter("buyer_cd")==null?"":req.getParameter("buyer_cd");
			//String bank_cd = "";//req.getParameter("bank_cd")==null?"":req.getParameter("bank_cd");
			String limit_id_mst = req.getParameter("limit_id_mst")==null?"":req.getParameter("limit_id_mst");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String credit_rating = req.getParameter("credit_rating")==null?"":req.getParameter("credit_rating");
			
			String parent_ow_cd = req.getParameter("parent_cd")==null?"":req.getParameter("parent_cd");
			String disabledParentCd = req.getParameter("disabledParentCd")==null?"":req.getParameter("disabledParentCd");
			String percent_own = req.getParameter("percent_own")==null?"":req.getParameter("percent_own");
			String parentEntryDt = req.getParameter("parentEntryDt")==null?"":req.getParameter("parentEntryDt");//HARSH20201204
			String parentExitDt = req.getParameter("parentExitDt")==null?"":req.getParameter("parentExitDt");//HARSH20201204
			String remarks = req.getParameter("remarks")==null?"":req.getParameter("remarks");
			String buy_sell = req.getParameter("buy_sell")==null?"":req.getParameter("buy_sell");//HARSH20201207
			String ref_no = req.getParameter("ref_no")==null?"":req.getParameter("ref_no");//HARSH20210127
			
			parent_flag = req.getParameter("parent_flag")==null?"":req.getParameter("parent_flag");//HARSH20210302
			
			String parent_cd = ""; //HARSH20210302
			String parent_abbr = ""; //HARSH20210302
			
			String optional = req.getParameter("optional")==null?"":req.getParameter("optional");
			String cust_agmt = "Y";
			String flag = "Y";
			int record_found=0;
			
			String limit_id = "";
			String seq_no = "";
			//String limit_type = "Adjust Limit";
			//String MappingID = buyer_cd+"-"+fgca_no+"-"+fgca_rev_no+"-"+sn_no+"-"+sn_rev_no;
			String flagg1 = ""; 
			if(credit_rating != "" || !credit_rating.equals("0"))
			{
				flagg1="C";
			}
			if(!disabledParentCd.equals("") && parent_ow_cd.equals(""))
			{
				parent_ow_cd=disabledParentCd;
			}
			if(percent_own != "" && (!parent_ow_cd.equals("") || !parent_ow_cd.equals("0")))
			{
				flagg1="P";
			}
			//System.out.println(bank_cd);
			String bank_abbr = "";//HARSH20210128
			if(req.getParameter("buyer_cd")==null || req.getParameter("bank_cd")!="")
			{
				buyer_cd="BANK"; //HARSH20210107
				bank_cd=req.getParameter("bank_cd");
				
				query="select bank_abbr from fms7_bank_mst where bank_cd='"+bank_cd+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					bank_abbr=rset.getString(1)==null?"":rset.getString(1);
				}
				
				query="select bank_abbr from fms7_bank_mst where bank_cd='"+parent_ow_cd+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					parent_abbr = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(req.getParameter("buyer_cd")!="" || req.getParameter("bank_cd")==null)
			{
				buyer_cd = req.getParameter("buyer_cd");
				bank_cd="0";
			}
			String MappingID = buyer_cd+"-0-0-0-0";
			
			/////////////////////////////// Old Value // HARSH20210105////////////////////////////////////
			String old_credit_rating = req.getParameter("old_credit_rating")==null?"":req.getParameter("old_credit_rating");
			String old_parent_cd = req.getParameter("old_parent_cd")==null?"":req.getParameter("old_parent_cd");
			String old_status = req.getParameter("old_status")==null?"":req.getParameter("old_status");
			String old_remarks = req.getParameter("old_remarks")==null?"":req.getParameter("old_remarks");
			String old_percent_own = req.getParameter("old_percent_own")==null?"":req.getParameter("old_percent_own");
			String old_parentEntryDt = req.getParameter("old_parentEntryDt")==null?"":req.getParameter("old_parentEntryDt");//HARSH20201204
			String old_parentExitDt = req.getParameter("old_parentExitDt")==null?"":req.getParameter("old_parentExitDt");//HARSH20201204
			String old_ref_no = req.getParameter("old_ref_no")==null?"":req.getParameter("old_ref_no");//HARSH20210127
			//////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////HARSH20210109/////Fetch Cust cd and parent cd using abbr//////////////
			String temp_cust_cd="0",temp_parent_cd="0";
			query="select customer_cd from fms7_customer_mst where customer_abbr='"+buyer_cd+"'";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				temp_cust_cd=rset.getString(1)==null?"":rset.getString(1);
			}else{
				query="select trader_cd from fms7_trader_mst where trader_abbr='"+buyer_cd+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					temp_cust_cd=rset.getString(1)==null?"":rset.getString(1);
				}
			}
			query="select customer_cd from fms7_customer_mst where customer_abbr='"+parent_ow_cd+"'";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				temp_parent_cd=rset.getString(1)==null?"":rset.getString(1);
			}else{
				query="select trader_cd from fms7_trader_mst where trader_abbr='"+parent_ow_cd+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					temp_parent_cd=rset.getString(1)==null?"":rset.getString(1);
				}
			}
			///////////////////////////////////////////////////////////////////////////////////////
			if(optional.equalsIgnoreCase("MODIFY"))
			{
//				System.out.println(buyer_cd+"---"+limit_id_mst);
				if(!buyer_cd.equals("") && !limit_id_mst.equals(""))
				{
					/////HARSH20201221////////create history for limit and credit rating///////
					String dtl_limit_id = ""; 
					/*HARSH20210107 queryString="SELECT CREDIT_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'),PARENT_OWNSHIP_CD,PARENT_OWNSHIP,CUST_AGMT_FLAG,REMARKS,"
							+ "STATUS,ENT_CD,TO_CHAR(ENT_DT,'DD/MM/YYYY HH12:MI:SS AM'),TO_CHAR(MODIFY_DT,'DD/MM/YYYY HH12:MI:SS AM'),MODIFY_BY,TO_CHAR(APRV_DT,'DD/MM/YYYY HH12:MI:SS AM'),"
							+ "APRV_BY,FLAG,BANK_CD,TO_CHAR(PARENT_ENT_DT,'DD/MM/YYYY HH12:MI:SS AM'),TO_CHAR(PARENT_EXIT_DT,'DD/MM/YYYY HH12:MI:SS AM'),BUY_SELL FROM FMS9_LIMIT_MST WHERE CUST_CD='"+buyer_cd+"' AND LIMIT_ID='"+limit_id_mst+"'";
					rset=stmt.executeQuery(queryString);
					if(rset.next())
					{
						String creditRating = rset.getString(1)==null?"":rset.getString(1);
						String ratingEffDt = rset.getString(2)==null?"":rset.getString(2);
						String parentOwCd = rset.getString(3)==null?"":rset.getString(3);
						String parentOw = rset.getString(4)==null?"":rset.getString(4);
						String custAgmtFlag = rset.getString(5)==null?"":rset.getString(5);
						String Remark = rset.getString(6)==null?"":rset.getString(6);
						String Status = rset.getString(7)==null?"":rset.getString(7);
						String EntCd = rset.getString(8)==null?"":rset.getString(8);
						String EntDt = rset.getString(9)==null?"":rset.getString(9);
						String modifyDt = rset.getString(10)==null?"":rset.getString(10);
						String modifyBy = rset.getString(11)==null?"":rset.getString(11);
						String aprvDt = rset.getString(12)==null?"":rset.getString(12);
						String aprvBy = rset.getString(13)==null?"":rset.getString(13);
						String oldFlag = rset.getString(14)==null?"":rset.getString(14);
						String BankCd = rset.getString(15)==null?"":rset.getString(15);
						String old_ParentEntryDt = rset.getString(16)==null?"":rset.getString(16);
						String old_ParentExitDt = rset.getString(17)==null?"":rset.getString(17);
						String old_buySell = rset.getString(18)==null?"":rset.getString(18);
						String check="select max(nvl(DTL_LIMIT_ID,1)+1) from fms9_limit_mst_dtl where cust_cd='"+buyer_cd+"' and limit_id='"+limit_id_mst+"'";
						rset=stmt.executeQuery(check);
						if(rset.next())
						{
							dtl_limit_id = rset.getString(1)==null?"1":rset.getString(1);
						}
						String FMS9_LIMIT_MST_DTL = "INSERT INTO FMS9_LIMIT_MST_DTL(CUST_CD,LIMIT_ID,DTL_LIMIT_ID,DTL_ENT_DT,CREDIT_RATING,RATING_EFF_DATE,PARENT_OWNSHIP_CD,PARENT_OWNSHIP,CUST_AGMT_FLAG,REMARKS,STATUS,"
								+ "ENT_CD,ENT_DT,MODIFY_DT,MODIFY_BY,APRV_DT,APRV_BY,FLAG,BANK_CD,PARENT_ENT_DT,PARENT_EXIT_DT,BUY_SELL) VALUES('"+buyer_cd+"','"+limit_id_mst+"','"+dtl_limit_id+"',sysdate,'"+creditRating+"',TO_DATE('"+ratingEffDt+"','DD/MM/YYYY HH12:MI:SS AM'),'"+parentOwCd+"',"
										+ "'"+parentOw+"','"+custAgmtFlag+"','"+Remark+"','"+Status+"','"+EntCd+"',TO_DATE('"+EntDt+"','DD/MM/YYYY HH12:MI:SS AM'),TO_DATE('"+modifyDt+"','DD/MM/YYYY HH12:MI:SS AM'),'"+modifyBy+"',TO_DATE('"+aprvDt+"','DD/MM/YYYY HH12:MI:SS AM'),"
												+ "'"+aprvBy+"','"+oldFlag+"','"+BankCd+"',TO_DATE('"+old_ParentEntryDt+"','DD/MM/YYYY HH12:MI:SS AM'),TO_DATE('"+old_ParentExitDt+"','DD/MM/YYYY HH12:MI:SS AM'),'"+old_buySell+"')";
						stmt.execute(FMS9_LIMIT_MST_DTL);
					}*/
					///////////////////////////////// following code is for check parent expired or not if yes then create new entry and if no then update existing data////////////////////
					int checkpoint = 0;
					//String checkParenetExpired = "select nvl(count(*),0) from fms9_limit_mst where cust_cd='"+buyer_cd+"' and limit_id='"+limit_id_mst+"' and buy_sell='"+buy_sell+"' and parent_ownship_cd is not null and parent_exit_dt < to_date((select to_char(sysdate,'dd/mm/yyyy') from dual),'dd/mm/yyyy')"; //HARSH20201207 added new column buy_sell //buy_sell='"+buy_sell+"' and 
					String checkParenetExpired = "select nvl(count(*),0) from fms9_limit_mst where cust_abbr='"+buyer_cd+"' and limit_id='"+limit_id_mst+"' and parent_ownship_abbr is not null and parent_exit_dt < to_date((select to_char(sysdate,'dd/mm/yyyy') from dual),'dd/mm/yyyy')"; //HARSH20201207 added new column buy_sell
					//System.out.println("checkParenetExpired--"+checkParenetExpired); 
					rset=stmt.executeQuery(checkParenetExpired);
					if(rset.next())
					{
						checkpoint=rset.getInt(1);
						if(checkpoint>0)
						{
							String max_limit_id_for_new = "select max(nvl(LIMIT_ID,1)+1) from FMS9_LIMIT_MST where CUST_ABBR='"+buyer_cd+"'"; //HARSH20201207 added new column buy_sell
							rset = stmt.executeQuery(max_limit_id_for_new);
							if(rset.next()) {
								limit_id = rset.getString(1)==null?"1":rset.getString(1);
							}
							if(buyer_cd.equals("BANK") && !bank_cd.equals("0")){
								ref_no = bank_abbr+"-R"+limit_id; //HARSH20210219
								//parent_abbr = bank_abbr; //only for bank parent HARSH20210302
								parent_cd = parent_ow_cd; //only for bank parent HARSH20210302
							}else{
								ref_no=buyer_cd+"-R"+limit_id; //HARSH20210219
								parent_abbr = parent_ow_cd; //HARSH20210302
								parent_cd = temp_parent_cd; //HARSH20210302
							}
							String FMS9_LIMIT_MST="";
							if(flagg1.equals("P"))
							{	String creditR = "";
								queryString="select credit_rating from fms9_limit_mst where cust_abbr='"+buyer_cd+"' and limit_id='"+limit_id_mst+"'"; //HARSH20201207 added new column buy_sell // and buy_sell='"+buy_sell+"'
								rset=stmt.executeQuery(queryString);
								if(rset.next())
								{
									creditR=rset.getString(1)==null?"":rset.getString(1);
								}
								FMS9_LIMIT_MST = "insert into FMS9_LIMIT_MST(cust_abbr,limit_id,credit_rating,rating_eff_date,parent_ownship_abbr,parent_ownship,cust_agmt_flag,remarks,status,ent_cd,ent_dt,flag,bank_cd,parent_ent_dt,parent_exit_dt,buy_sell,cust_cd,parent_ownship_cd,ref_no) values"  //HARSH20201207 added new column buy_sell
										+ "('"+buyer_cd+"','"+limit_id+"','"+creditR+"',sysdate,'"+parent_abbr+"',"+percent_own+",'"+cust_agmt+"','"+remarks+"','"+status+"','"+user_cd+"',sysdate,'"+flag+"','"+bank_cd+"',to_date('"+parentEntryDt+"','dd/mm/yyyy'),to_date('"+parentExitDt+"','dd/mm/yyyy'),'"+buy_sell+"','"+temp_cust_cd+"','"+parent_cd+"','"+ref_no+"')";
//								System.out.println("MST-----"+FMS9_LIMIT_MST);
							}
							else
							{
								FMS9_LIMIT_MST = "insert into FMS9_LIMIT_MST(cust_abbr,limit_id,credit_rating,rating_eff_date,cust_agmt_flag,remarks,status,ent_cd,ent_dt,flag,bank_cd,buy_sell,cust_cd,ref_no) values" //HARSH20201207 added new column buy_sell
										+ "('"+buyer_cd+"','"+limit_id+"','"+credit_rating+"',sysdate,'"+cust_agmt+"','"+remarks+"','"+status+"','"+user_cd+"',sysdate,'"+flag+"','"+bank_cd+"','"+buy_sell+"','"+temp_cust_cd+"','"+ref_no+"')";
//								System.out.println("MST-----"+FMS9_LIMIT_MST);
							}
							stmt.execute(FMS9_LIMIT_MST);
							msg="Data Submitted Successfully!!!";
						}
						else
						{
							String checkExist="select nvl(count(*),0) from FMS9_LIMIT_MST where CUST_ABBR='"+buyer_cd+"' and LIMIT_ID='"+limit_id_mst+"'"; //and BUY_SELL='"+buy_sell+"'
							rset= stmt.executeQuery(checkExist);
							if(rset.next())
							{
								record_found = rset.getInt(1);
								
								if(record_found > 0) {
									
									String UPDATE_FMS9_LIMIT_MST="";
									if(flagg1.equals("P"))
									{
										if(buyer_cd.equalsIgnoreCase("BANK")){
										//	parent_abbr = bank_abbr; //only for bank parent HARSH20210302
											parent_cd = parent_ow_cd; //only for bank parent HARSH20210302
										}else{
											parent_abbr = parent_ow_cd; //HARSH20210302
											parent_cd = temp_parent_cd; //HARSH20210302
										}
										mesge="Cust_abbr="+buyer_cd+"#Bank_cd="+bank_cd+"#Status="+status+"#Remarks="+remarks+"#Parent_Legal_Entity="+parent_abbr+"#Percentage_Own="+percent_own+"#Parent_Entry_Dt="+parentEntryDt+"#Parent_Exit_Dt="+parentExitDt+"#Ref_no="+ref_no;
										old_mesge="Cust_abbr="+buyer_cd+"#Bank_cd="+bank_cd+"#Status="+old_status+"#Remarks="+old_remarks+"#Parent_Legal_Entity="+old_parent_cd+"#Percentage_Own="+old_percent_own+"#Parent_Entry_Dt="+old_parentEntryDt+"#Parent_Exit_Dt="+old_parentExitDt+"#Ref_no="+old_ref_no;
										UPDATE_FMS9_LIMIT_MST = "update fms9_limit_mst set parent_ownship_abbr='"+parent_abbr+"',parent_ownship="+percent_own+",parent_ent_dt=to_date('"+parentEntryDt+"','dd/mm/yyyy'),parent_exit_dt=to_date('"+parentExitDt+"','dd/mm/yyyy'),remarks='"+remarks+"',status='"+status+"',bank_cd='"+bank_cd+"',modify_dt=sysdate,modify_by='"+user_cd+"',parent_ownship_cd='"+parent_cd+"' where cust_abbr='"+buyer_cd+"' and limit_id = '"+limit_id_mst+"'"; //HARSH20201207 added new column buy_sell // and buy_sell = '"+buy_sell+"'
									}
									else
									{
										mesge="Cust_abbr="+buyer_cd+"#Bank_cd="+bank_cd+"#Status="+status+"#Credit_rating="+credit_rating+"#Remarks="+remarks+"#Ref_no="+ref_no;
										old_mesge="Cust_abbr="+buyer_cd+"#Bank_cd="+bank_cd+"#Status="+old_status+"#Credit_rating="+old_credit_rating+"#Remarks="+old_remarks+"#Ref_no="+old_ref_no;
										UPDATE_FMS9_LIMIT_MST = "update fms9_limit_mst set credit_rating='"+credit_rating+"',rating_eff_date=sysdate,remarks='"+remarks+"',status='"+status+"',bank_cd='"+bank_cd+"',modify_dt=sysdate,modify_by='"+user_cd+"' where cust_abbr='"+buyer_cd+"' and limit_id = '"+limit_id_mst+"'";  //HARSH20201207 added new column buy_sell // and buy_sell = '"+buy_sell+"'
									}
									stmt.execute(UPDATE_FMS9_LIMIT_MST);
									msg="Modification Done Successfully!!";
								}
								else
								{
									err_msg="No Record Found For Modification!!";
								}
							}
							else
							{
								err_msg="No Record Found For Modification!!";
							}
						}
					}
				}
				else
				{
					err_msg="Customer name and Limit Id not Available!!";
				}
			}
			else
			{
				String checkExist="select nvl(count(*),0) from FMS9_LIMIT_MST where CUST_ABBR='"+buyer_cd+"' and bank_cd='"+bank_cd+"'"; //HARSH20201207 added new column buy_sell // and buy_sell='"+buy_sell+"'
				rset= stmt.executeQuery(checkExist);
				if(rset.next())
				{
					record_found = rset.getInt(1);
					//System.out.println(record_found > 0);
					
					if(record_found == 0) {
						String max_limit_id = "select max(nvl(LIMIT_ID,1)+1) from FMS9_LIMIT_MST where CUST_ABBR='"+buyer_cd+"'";
						rset = stmt.executeQuery(max_limit_id);
						if(rset.next()) {
							limit_id = rset.getString(1)==null?"1":rset.getString(1);
						}
						/////////HARSH20210128///////////////////
						if(buyer_cd.equals("BANK") && !bank_cd.equals("0")){
							ref_no = bank_abbr+"-R"+limit_id;
							//parent_abbr = bank_abbr; //only for bank parent HARSH20210302
							parent_cd = parent_ow_cd; //only for bank parent HARSH20210302
						}else{
							ref_no = buyer_cd+"-R"+limit_id; //HARSH20210127 AS PER FEEDBACK ON 27/01/2021
							parent_abbr = parent_ow_cd;
							parent_cd = temp_parent_cd;
						}
						//////////////////////////////////////////
						String FMS9_LIMIT_MST="";
						if(flagg1.equals("P"))
						{
							mesge="Cust_abbr="+buyer_cd+"#Bank_cd="+bank_cd+"#Status="+status+"#Remarks="+remarks+"#Parent_Legal_Entity="+parent_abbr+"#Percentage_Own="+percent_own+"#Parent_Entry_Dt="+parentEntryDt+"#Parent_Exit_Dt="+parentExitDt+"#Ref_no="+ref_no;
							FMS9_LIMIT_MST = "insert into FMS9_LIMIT_MST(cust_abbr,limit_id,parent_ownship_abbr,parent_ownship,cust_agmt_flag,remarks,status,ent_cd,ent_dt,flag,bank_cd,parent_ent_dt,parent_exit_dt,buy_sell,cust_cd,parent_ownship_cd,ref_no) values"  //HARSH20201207 added new column buy_sell
									+ "('"+buyer_cd+"','"+limit_id+"','"+parent_abbr+"',"+percent_own+",'"+cust_agmt+"','"+remarks+"','"+status+"','"+user_cd+"',sysdate,'"+flag+"','"+bank_cd+"',to_date('"+parentEntryDt+"','dd/mm/yyyy'),to_date('"+parentExitDt+"','dd/mm/yyyy'),'"+buy_sell+"','"+temp_cust_cd+"','"+parent_cd+"','"+ref_no+"')";
//							System.out.println("MST-----"+FMS9_LIMIT_MST);
						}
						else
						{
							mesge="Cust_abbr="+buyer_cd+"#Bank_cd="+bank_cd+"#Status="+status+"#Credit_rating="+credit_rating+"#Remarks="+remarks+"#Ref_no="+ref_no;
							FMS9_LIMIT_MST = "insert into FMS9_LIMIT_MST(cust_abbr,limit_id,credit_rating,rating_eff_date,cust_agmt_flag,remarks,status,ent_cd,ent_dt,flag,bank_cd,buy_sell,cust_cd,ref_no) values" //HARSH20201207 added new column buy_sell
									+ "('"+buyer_cd+"','"+limit_id+"','"+credit_rating+"',sysdate,'"+cust_agmt+"','"+remarks+"','"+status+"','"+user_cd+"',sysdate,'"+flag+"','"+bank_cd+"','"+buy_sell+"','"+temp_cust_cd+"','"+ref_no+"')";
//							System.out.println("MST-----"+FMS9_LIMIT_MST);
						}
						stmt.execute(FMS9_LIMIT_MST);
						msg="Data Submitted Successfully!!!";
					}
					else
					{
						err_msg="Customer is already Exist!!";
					}
				}
				else
				{
					err_msg="Customer is already Exist!!";
				}			
			}
			if(buyer_cd.equals("BANK"))
			{
				buyer_cd="";
			}
		//	msg=msg.replaceAll(" ", "");
		//	err_msg=err_msg.replaceAll(" ", "");
			url="../mrcr/frm_CR_Credit_Limit.jsp?msg="+msg+"&customer_cd="+buyer_cd+"&bank_cd="+bank_cd+"&index="+index+"&sell_buy="+buy_sell+"&parent_flag="+parent_flag+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			try
			{
				//HARSH20210105 new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg+"$NEW$"+mesge+"%OLD%"+old_mesge);
				InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,old_mesge,mesge,msg);//HARSH20210105
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			dbcon.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_CR_Credit_Limit.jsp?msg="+msg+"&err_msg="+err_msg+"&customer_cd="+buyer_cd+"&bank_cd="+bank_cd+"&parent_flag="+parent_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
		
	}
	
	private void UpdateCreditlineDetail(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException {
		String err_msg ="";
		msg="";String parent_flag=""; String cust_cd=""; String bank_cd="";
		try
		{
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			/////
			String index = req.getParameter("index")==null?"":req.getParameter("index"); //HARSH20210105 Only use for select customer name dropdown
			/////
			cust_cd = req.getParameter("cust_cd")==null?"":req.getParameter("cust_cd");
			bank_cd = req.getParameter("bank_cd")==null?"":req.getParameter("bank_cd");
			String limit_id = req.getParameter("limit_id_dtl")==null?"":req.getParameter("limit_id_dtl");
			String limit_type = req.getParameter("limit_type")==null?"":req.getParameter("limit_type");
			String limit_action = req.getParameter("limit_action")==null?"":req.getParameter("limit_action");
			String user_type = req.getParameter("user_type")==null?"":req.getParameter("user_type");
			String amt = req.getParameter("amt")==null?"":req.getParameter("amt");
			String eff_dt = req.getParameter("eff_dt")==null?"":req.getParameter("eff_dt");
			String exp_dt = req.getParameter("exp_dt")==null?"":req.getParameter("exp_dt");
			String review_dt = req.getParameter("review_dt")==null?"":req.getParameter("review_dt");
			String seq_no = req.getParameter("seq_no")==null?"":req.getParameter("seq_no");
			String remarks = req.getParameter("remarks")==null?"":req.getParameter("remarks");
			String optional = req.getParameter("optional")==null?"":req.getParameter("optional");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String buy_sell = req.getParameter("buy_sell")==null?"":req.getParameter("buy_sell");//HARSH20201207
			String SysDate = req.getParameter("sysdate_temp")==null?"":req.getParameter("sysdate_temp");//HARSH20210127
			String ref_no = req.getParameter("ref_no")==null?"":req.getParameter("ref_no");//HARSH20210127
			
			parent_flag = req.getParameter("parent_flag")==null?"":req.getParameter("parent_flag");//HARSH20210302
			
			String old_value = req.getParameter("old_value")==null?"":req.getParameter("old_value");//HARSH20210107
			String new_value = "Cust_abbr="+cust_cd+"#Bank_cd="+bank_cd+"#LimitType="+limit_type+"#LimitAction="+limit_action+"#UserType="+user_type+"#Amount="+amt+"#Eff_DT="+eff_dt+"#Exp_DT="+exp_dt+"#Review_DT="+review_dt+"#Remarks="+remarks+"#Ref_no="+ref_no;
			String flag="";
			if(status.equals("Active"))
			{
				flag="Y";
			}
			else if(status.equals("Inactive"))
			{
				flag="N";
			}
			else
			{
				flag="Y";
			}
			//double amt_exist = 0;
			//double total_amt = 0;
			int record_found=0;
			String insert_seq_no = "";
			//////////////////HARSH20210109/////Fetch Cust cd//////////////
			String temp_cust_cd="0",temp_parent_cd="0";
			query="select customer_cd from fms7_customer_mst where customer_abbr='"+cust_cd+"'";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				temp_cust_cd=rset.getString(1)==null?"":rset.getString(1);
			}else{
				query="select trader_cd from fms7_trader_mst where trader_abbr='"+cust_cd+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					temp_cust_cd=rset.getString(1)==null?"":rset.getString(1);
				}
			}
			///////////////////////////////////////////////////////////////////////////////////////
			if(optional.equalsIgnoreCase("MODIFY"))
			{
				if(!cust_cd.equals("") && !limit_id.equals("") && !seq_no.equals(""))
				{//System.out.println(cust_cd+"----"+limit_id+"----"+seq_no);
					String checkExist="select nvl(count(*),0) from FMS9_LIMIT_DTL where CUST_ABBR='"+cust_cd+"' and LIMIT_ID='"+limit_id+"' and SEQ_NO='"+seq_no+"'"; //HARSH20201207 added new column buy_sell // and buy_sell='"+buy_sell+"'
					rset= stmt.executeQuery(checkExist);
					if(rset.next())
					{
						record_found = rset.getInt(1);
						if(record_found > 0) 
						{
							if(limit_action.equals("Adjust Usage") && Double.parseDouble(amt)>0) //SB20201031: 
								amt=""+(Double.parseDouble(amt)*(-1));  //SB20201031: incase of Adjustment
							String update_credit_line = "update FMS9_LIMIT_DTL set limit_type='"+limit_type+"',action_type='"+limit_action+"',amt='"+amt+"',cat_user_type='"+user_type+"',eff_dt=TO_DATE('"+eff_dt+"','dd/mm/yyyy'),exp_dt=TO_DATE('"+exp_dt+"','dd/mm/yyyy'),review_dt=TO_DATE('"+review_dt+"','dd/mm/yyyy'),remarks='"+remarks+"' "
									+ ",APRV_BY='"+user_cd+"', APRV_DT=sysdate,modify_dt=sysdate,modify_by='"+user_cd+"'"
									+ " where cust_abbr = '"+cust_cd+"' and limit_id = '"+limit_id+"' and seq_no = '"+seq_no+"'"; //HARSH20201207 added new column buy_sell // and buy_sell='"+buy_sell+"'
							stmt.execute(update_credit_line);
							msg="Modification Done Successfully!!!";
						}
						else
						{
							err_msg="No Record Found For Modification!!";
						}
					}
				}
				else
				{
					err_msg="Customer name and Limit Id not Available!!";
				}
				
			}
			else if(optional.equalsIgnoreCase("UPDATE_STATUS"))
			{
				new_value="Cust_abbr="+cust_cd+"#Bank_cd="+bank_cd+"#Status="+flag+"#Ref_no="+ref_no+"#InactDt="+SysDate;
				String checkExist="select nvl(count(*),0) from FMS9_LIMIT_DTL where CUST_ABBR='"+cust_cd+"' and LIMIT_ID='"+limit_id+"' and SEQ_NO='"+seq_no+"'"; //HARSH20201207 added new column buy_sell // and buy_sell='"+buy_sell+"'
				rset= stmt.executeQuery(checkExist);
				if(rset.next())
				{
					record_found = rset.getInt(1);
					if(record_found > 0) 
					{
						///HARSH20210122
						/*HARSH20210127 query="select count(*) from FMS9_LIMIT_DTL where cust_abbr = '"+cust_cd+"' and limit_id = '"+limit_id+"' and seq_no = '"+seq_no+"' and exp_dt is null";
						System.out.println(query);
						rset=stmt.executeQuery(query);
						if(rset.next())
						{
							int chk=rset.getInt(1);
							if(chk==1)
							{
								String update_status = "update FMS9_LIMIT_DTL set flag='"+flag+"',exp_dt=sysdate,inactivation_dt=sysdate,modify_dt=sysdate,modify_by='"+user_cd+"' where cust_abbr = '"+cust_cd+"' and limit_id = '"+limit_id+"' and seq_no = '"+seq_no+"'"; 
								stmt.execute(update_status);
								msg="Modification Done Successfully!!!";
							}
							else 
							{*/
								String update_status = "update FMS9_LIMIT_DTL set flag='"+flag+"',inactivation_dt=sysdate,modify_dt=sysdate,modify_by='"+user_cd+"' where cust_abbr = '"+cust_cd+"' and limit_id = '"+limit_id+"' and seq_no = '"+seq_no+"'"; 
								stmt.execute(update_status);
								msg="Modification Done Successfully!!!";
							//}
						//}
						///
					}
					else
					{
						err_msg="No Record Found For Modification!!";
					}
				}
			}
			else
			{
				if((!cust_cd.equals("") || !bank_cd.equals("")) && !limit_id.equals(""))
				{
					if(req.getParameter("cust_cd")==null || req.getParameter("bank_cd")!="")
					{
						cust_cd="BANK"; //HARSH20210107
						bank_cd=req.getParameter("bank_cd");
					}
					else if(req.getParameter("cust_cd")!="" || req.getParameter("bank_cd")==null)
					{
						cust_cd = req.getParameter("cust_cd");
						bank_cd="0";
					}
					
					//System.out.println(cust_cd+"---"+bank_cd);
					String mapping_id = cust_cd+"-0-0-0-0";
					String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_LIMIT_DTL where CUST_ABBR='"+cust_cd+"' and LIMIT_ID='"+limit_id+"'";
					rset=stmt.executeQuery(max_seq_no);
					if(rset.next())
					{
						insert_seq_no=rset.getString(1)==null?"1":rset.getString(1);
					}
					if(limit_action.equals("Adjust Usage") && Double.parseDouble(amt)>0) //SB20201031: 
						amt=""+(Double.parseDouble(amt)*(-1));  //SB20201031: incase of Adjustment
					
					/////////HARSH20210128///////////////////
					if(cust_cd.equals("BANK") && !bank_cd.equals("0")){
						String bank_abbr = "";
						query="select bank_abbr from fms7_bank_mst where bank_cd='"+bank_cd+"'";
						rset=stmt.executeQuery(query);
						if(rset.next())
						{
							bank_abbr=rset.getString(1)==null?"":rset.getString(1);
						}
						ref_no = bank_abbr+"-R"+limit_id+"-L"+insert_seq_no;
						
					}else{
						ref_no = cust_cd+"-R"+limit_id+"-L"+insert_seq_no; //HARSH20210127 AS PER FEEDBACK ON 27/01/2021
					}
					//////////////////////////////
					new_value = "Cust_abbr="+cust_cd+"#Bank_cd="+bank_cd+"#LimitType="+limit_type+"#LimitAction="+limit_action+"#UserType="+user_type+"#Amount="+amt+"#Eff_DT="+eff_dt+"#Exp_DT="+exp_dt+"#Review_DT="+review_dt+"#Remarks="+remarks+"#Ref_no="+ref_no;
					
					String FMS9_LIMIT_DTL ="insert into FMS9_LIMIT_DTL(CUST_ABBR,LIMIT_ID,MAPPING_ID,SEQ_NO,LIMIT_TYPE,ACTION_TYPE,CAT_USER_TYPE,AMT,EFF_DT,EXP_DT,REVIEW_DT,REMARKS,ENT_CD,ENT_DT,APRV_BY,APRV_DT,FLAG,BUY_SELL,CUST_CD,REF_NO)" //HARSH20201207 added new column buy_sell
							+ " values('"+cust_cd+"','"+limit_id+"','"+mapping_id+"','"+insert_seq_no+"','"+limit_type+"','"+limit_action+"','"+user_type+"','"+amt+"',TO_DATE('"+eff_dt+"','DD/MM/YYYY'),TO_DATE('"+exp_dt+"','dd/mm/yyyy'),TO_DATE('"+review_dt+"','dd/mm/yyyy'),'"+remarks+"','"+user_cd+"',sysdate,'"+user_cd+"',sysdate,'"+flag+"','"+buy_sell+"','"+temp_cust_cd+"','"+ref_no+"')";
//					System.out.println("FMS9_LIMIT_DTL---"+FMS9_LIMIT_DTL);
					stmt.execute(FMS9_LIMIT_DTL);
					msg="Data Submitted Successfully!!!";
					
				}
				else
				{
					err_msg = "Select Credit Rating/Parent Ownership! Data not submitted!!";
				}
			}
			if(cust_cd.equals("BANK"))
			{
				cust_cd="";
			}
		//	msg=msg.replaceAll(" ", "");
		//	err_msg=err_msg.replaceAll(" ", "");
			url="../mrcr/frm_CR_Credit_Limit.jsp?msg="+msg+"&err_msg="+err_msg+"&customer_cd="+cust_cd+"&bank_cd="+bank_cd+"&index="+index+"&sell_buy="+buy_sell+"&err_msg="+err_msg+"&parent_flag="+parent_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			try
			{
				//HARSH20210110 new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg+"$NEW$"+new_value+"%OLD%"+old_value);
				InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,old_value,new_value,msg); //HARSH20210110
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			dbcon.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_CR_Credit_Limit.jsp?msg="+msg+"&err_msg="+err_msg+"&customer_cd="+cust_cd+"&bank_cd="+bank_cd+"&parent_flag="+parent_flag+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	private void InsertSecurityPostDtl(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException {
		String err_msg="";
		try
		{
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			//column name for LC or BG
			String buyer_cd = req.getParameter("buyer_cd")==null?"":req.getParameter("buyer_cd");
			String deal_type = req.getParameter("deal_type")==null?"":req.getParameter("deal_type");
			String selection_I = req.getParameter("selection_I")==null?"":req.getParameter("selection_I");
			String selection_R = req.getParameter("selection_R")==null?"":req.getParameter("selection_R");
			String currency = req.getParameter("currency")==null?"":req.getParameter("currency");
			String value_vari = req.getParameter("value_vari")==null?"":req.getParameter("value_vari");
			String value = req.getParameter("value")==null?"":req.getParameter("value");
			String value_fluc = req.getParameter("value_fluc")==null?"":req.getParameter("value_fluc");
			String issu_bank_cd= req.getParameter("issu_bank_cd")==null?"":req.getParameter("issu_bank_cd");
			String issu_bank_ref = req.getParameter("issu_bank_ref")==null?"":req.getParameter("issu_bank_ref");
			String advi_bank_cd = req.getParameter("advi_bank_cd")==null?"":req.getParameter("advi_bank_cd");
			String advi_bank_ref = req.getParameter("advi_bank_ref")==null?"":req.getParameter("advi_bank_ref");
			String confirm_bank_cd = req.getParameter("confirm_bank_cd")==null?"":req.getParameter("confirm_bank_cd");
			String confirm_bank_ref = req.getParameter("confirm_bank_ref")==null?"":req.getParameter("confirm_bank_ref");
			String received_dt = req.getParameter("received_dt")==null?"":req.getParameter("received_dt");
			String issuance_dt = req.getParameter("issuance_dt")==null?"":req.getParameter("issuance_dt");
			String e_date = req.getParameter("e_date")==null?"":req.getParameter("e_date");
			String tenor = req.getParameter("tenor")==null?"":req.getParameter("tenor");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String remarks = req.getParameter("remarks")==null?"":req.getParameter("remarks");
			String sec_type = req.getParameter("sec_type")==null?"":req.getParameter("sec_type");
			String operation = req.getParameter("operation")==null?"":req.getParameter("operation");
			String lc_seq_no = req.getParameter("lc_seq_no")==null?"":req.getParameter("lc_seq_no");
			String mapping_id_ch=req.getParameter("link")==null?"":req.getParameter("link");
			String link_ch = req.getParameter("mappingID")==null?"":req.getParameter("mappingID");
			String mapping_id="";
			String link = "";
						
			//column name for PCG
			String selection_I1 = req.getParameter("selection_I1")==null?"":req.getParameter("selection_I1");
			String selection_R1 = req.getParameter("selection_R1")==null?"":req.getParameter("selection_R1");
			String sec_type1 = req.getParameter("sec_type1")==null?"":req.getParameter("sec_type1");
			String buyer_cd1 = req.getParameter("cust_cd1")==null?"":req.getParameter("cust_cd1");
			String deal_type1 = req.getParameter("deal_type1")==null?"":req.getParameter("deal_type1");
			String value1 = req.getParameter("value1")==null?"":req.getParameter("value1");
			String gurantor_nm1 = req.getParameter("gurantor_nm1")==null?"":req.getParameter("gurantor_nm1");
			String received_dt1 = req.getParameter("received_dt1")==null?"":req.getParameter("received_dt1");
			String issuance_dt1 = req.getParameter("issuance_dt1")==null?"":req.getParameter("issuance_dt1");
			String e_date1 = req.getParameter("e_date1")==null?"":req.getParameter("e_date1");
			String renew_dt1 = req.getParameter("renew_dt1")==null?"":req.getParameter("renew_dt1");
			String tenor1 = req.getParameter("tenor1")==null?"":req.getParameter("tenor1");
			String remarks1 = req.getParameter("remarks1")==null?"":req.getParameter("remarks1");
			
			/*String column = "";
			if(selection.equals("I") || selection1.equals("I"))
			{
				column="ISSUED";
			}
			else if(selection.equals("R")|| selection1.equals("R"))
			{
				column="RECEIVED";
			}*/
			if(mapping_id_ch.equals("All"))
			{
				mapping_id = "A0-0-0-0";
			}
			else
			{
				mapping_id = req.getParameter("link")==null?"":req.getParameter("link");
			}
			
			if(link_ch.equals("All") || sec_type1.equals("PCG"))
			{
				link = "A0-0-0-0";
			}
			else
			{
				link = req.getParameter("mappingID")==null?"":req.getParameter("mappingID");
			}
			
			String seq_no = "";
			String seq_no_update = req.getParameter("seq_no")==null?"":req.getParameter("seq_no");
			
			if(sec_type1.equals("PCG"))
			{
				if(operation.equalsIgnoreCase("MODIFY"))
				{
					if(!buyer_cd1.equals("") && !seq_no_update.equals(""))
					{
						String checkExist="select nvl(count(*),0) from FMS9_SECURITY_POST_MST where CUSTOMER_CD='"+buyer_cd1+"'";
						rset= stmt.executeQuery(checkExist);
						if(rset.next())
						{
							String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type1+"',DEAL_TYPE='"+deal_type1+"',VALUE='"+value1+"',"
									+ "REC_DT=to_date('"+received_dt1+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt1+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date1+"','dd/mm/yyyy'),RENEW_DT=to_date('"+renew_dt1+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor1+"',REMARKS='"+remarks1+"',issued='"+selection_I1+"',received='"+selection_R1+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',LINK='"+link+"',GUARANTOR_NM='"+gurantor_nm1+"' where CUSTOMER_CD='"+buyer_cd1+"' and SEQ_NO='"+seq_no_update+"'";
								stmt.execute(UPDATE_FMS9_SECURITY_POST_MST);
								msg="Modification Done Successfully!!";
						}
						else
						{
							err_msg="Data Not Found for Modification!!";
						}
					}
					else
					{
						err_msg="Customer name and sequence no not Available!!";
					}
				}
				else
				{
					if(!buyer_cd1.equals(""))
					{
						String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_SECURITY_POST_MST where CUSTOMER_CD='"+buyer_cd1+"'";
						rset = stmt.executeQuery(max_seq_no);
						if(rset.next()) {
							seq_no = rset.getString(1)==null?"1":rset.getString(1);
						}
						//System.out.println(seq_no);
						String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,SEQ_NO,SEC_TYPE,DEAL_TYPE,VALUE,REC_DT,ISSU_DT,EXP_DT,RENEW_DT,TENOR,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,GUARANTOR_NM,MAPPING_ID,STATUS,APRV_DT,APRV_BY) values"
								+ "('"+buyer_cd1+"','"+seq_no+"','"+sec_type1+"','"+deal_type1+"','"+value1+"',to_date('"+received_dt1+"','dd/mm/yyyy'),to_date('"+issuance_dt1+"','dd/mm/yyyy'),to_date('"+e_date1+"','dd/mm/yyyy'),to_date('"+renew_dt1+"','dd/mm/yyyy'),'"+tenor1+"','"+remarks1+"','"+selection_I1+"','"+selection_R1+"','"+user_cd+"',sysdate,'"+link+"','"+gurantor_nm1+"','"+mapping_id+"','In order',sysdate,'"+user_cd+"')";
//						System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
						stmt.execute(FMS9_SECURITY_POST_MST);
						msg="Data Submitted Successfully!!!";
					}
					else
					{
						err_msg="Customer Not Found";
					}
				}
			}
			else
			{
			if(operation.equalsIgnoreCase("MODIFY"))
			{
				if(!buyer_cd.equals("") && !seq_no_update.equals(""))
				{
					String checkExist="select nvl(count(*),0) from FMS9_SECURITY_POST_MST where CUSTOMER_CD='"+buyer_cd+"'";
					rset= stmt.executeQuery(checkExist);
					if(rset.next())
					{
						/*String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
							+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
									+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"' where CUSTOMER_CD='"+buyer_cd+"' and SEQ_NO='"+seq_no_update+"'";
						stmt.execute(UPDATE_FMS9_SECURITY_POST_MST);*/
						
						if(status.equals("In order"))
						{
							String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
									+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',APRV_DT=sysdate,APRV_BY='"+user_cd+"',FLAG='Y' where CUSTOMER_CD='"+buyer_cd+"' and SEQ_NO='"+seq_no_update+"'";
							stmt.execute(UPDATE_FMS9_SECURITY_POST_MST);
								
							//String UPDATE_FMS7_LC_MST = "update FMS7_LC_MST set APRV_DT=sysdate,APRV_BY='"+user_cd+"' where CUSTOMER_CD='"+buyer_cd+"' and LC_SEQ_NO='"+lc_seq_no+"' ";
							//stmt.execute(UPDATE_FMS7_LC_MST);
						}
						else if(status.equals("Cancelled"))
						{
							String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
									+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',FLAG='X' where CUSTOMER_CD='"+buyer_cd+"' and SEQ_NO='"+seq_no_update+"'";
							stmt.execute(UPDATE_FMS9_SECURITY_POST_MST);
						}
						else if(status.equals("Expired"))
						{
							String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
									+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',FLAG='N' where CUSTOMER_CD='"+buyer_cd+"' and SEQ_NO='"+seq_no_update+"'";
							stmt.execute(UPDATE_FMS9_SECURITY_POST_MST);
						}
						else
						{
							String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
									+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"' where CUSTOMER_CD='"+buyer_cd+"' and SEQ_NO='"+seq_no_update+"'";
							stmt.execute(UPDATE_FMS9_SECURITY_POST_MST);
						}
						msg="Modification Done Successfully!!";
					}
					else
					{
						err_msg="Data Not Found for Modification!!";
					}
				}
				else
				{
					err_msg="Customer name and sequence no not Available!!";
				}
			}
			else
			{
				if(!buyer_cd.equals(""))
				{
					String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_SECURITY_POST_MST where CUSTOMER_CD='"+buyer_cd+"'";
					rset = stmt.executeQuery(max_seq_no);
					if(rset.next()) {
						seq_no = rset.getString(1)==null?"1":rset.getString(1);
					}
					//System.out.println(seq_no);
					if(status.equals("In order"))
					{
						String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,APRV_DT,APRV_BY,FLAG,MAPPING_ID) values"
								+ "('"+buyer_cd+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date('"+issuance_dt+"','dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','"+status+"','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"',sysdate,'"+user_cd+"','Y','"+mapping_id+"')";
//						System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
						stmt.execute(FMS9_SECURITY_POST_MST);
						
						//String UPDATE_FMS7_LC_MST = "update FMS7_LC_MST set APRV_DT=sysdate,APRV_BY='"+user_cd+"' where CUSTOMER_CD='"+buyer_cd+"' and LC_SEQ_NO='"+lc_seq_no+"' ";
						//stmt.execute(UPDATE_FMS7_LC_MST);
					}
					else if(status.equals("Cancelled"))
					{
						String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,MODIFY_DT,MODIFY_BY,FLAG,MAPPING_ID) values"
								+ "('"+buyer_cd+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date('"+issuance_dt+"','dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','"+status+"','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"',sysdate,'"+user_cd+"','X','"+mapping_id+"')";
//						System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
						stmt.execute(FMS9_SECURITY_POST_MST);
					}
					else if(status.equals("Expired"))
					{
						String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,MODIFY_DT,MODIFY_BY,FLAG,MAPPING_ID) values"
								+ "('"+buyer_cd+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date('"+issuance_dt+"','dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','"+status+"','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"',sysdate,'"+user_cd+"','N','"+mapping_id+"')";
//						System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
						stmt.execute(FMS9_SECURITY_POST_MST);
					}
					else
					{
						String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,MAPPING_ID) values"
								+ "('"+buyer_cd+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date('"+issuance_dt+"','dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','"+status+"','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"','"+mapping_id+"')";
//						System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
						stmt.execute(FMS9_SECURITY_POST_MST);
					}
					
					msg="Data Submitted Successfully!!!";
				}
				else
				{
					err_msg="Customer Not Found";
				}
			}
			}
			url="../mrcr/frm_Post_Deal_Capturing.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			dbcon.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_Post_Deal_Capturing.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	private void InsertCRSecurityPostDtl(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException {
		String err_msg="";String customer_abbr="";
		msg="";
		try
		{
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			/////
			String index = req.getParameter("index")==null?"":req.getParameter("index"); //HARSH20210105 Only use for select customer name dropdown
			String sell_buy = req.getParameter("sell_buy")==null?"":req.getParameter("sell_buy");
			//column name for LC or BG
			String buyer_cd = req.getParameter("buyer_cd")==null?"":req.getParameter("buyer_cd");
			customer_abbr = req.getParameter("customer_abbr")==null?"":req.getParameter("customer_abbr");//HARSH20210213
			String cust_abbr = req.getParameter("cust_abbr")==null?"":req.getParameter("cust_abbr");
			String deal_type = req.getParameter("deal_type")==null?"":req.getParameter("deal_type");
			if(deal_type.equals("") || deal_type == "")//HARSH20210326
			{
				deal_type = req.getParameter("hid_deal_type")==null?"":req.getParameter("hid_deal_type");
			}
			String selection_I = req.getParameter("selection_I")==null?"":req.getParameter("selection_I");
			String selection_R = req.getParameter("selection_R")==null?"":req.getParameter("selection_R");
			String currency = req.getParameter("currency")==null?"":req.getParameter("currency");
			if(currency.equals("") || currency == "")//HARSH20210326
			{
				currency = req.getParameter("hid_currency")==null?"":req.getParameter("hid_currency");
			}
			String value_vari = req.getParameter("value_vari")==null?"":req.getParameter("value_vari");
			if(value_vari.equals("") || value_vari == "")//HARSH20210326
			{
				value_vari = req.getParameter("hid_value_vari")==null?"":req.getParameter("hid_value_vari");
			}
			String value = req.getParameter("value")==null?"":req.getParameter("value");
			String value_fluc = req.getParameter("value_fluc")==null?"":req.getParameter("value_fluc");
			String issu_bank_cd= req.getParameter("issu_bank_cd")==null?"":req.getParameter("issu_bank_cd");
			if(issu_bank_cd.equals("") || issu_bank_cd == "")//HARSH20210326
			{
				issu_bank_cd = req.getParameter("hid_issu_bank_cd")==null?"":req.getParameter("hid_issu_bank_cd");
			}
			String issu_bank_ref = req.getParameter("issu_bank_ref")==null?"":req.getParameter("issu_bank_ref");
			String advi_bank_cd = req.getParameter("advi_bank_cd")==null?"":req.getParameter("advi_bank_cd");
			if(advi_bank_cd.equals("") || advi_bank_cd == "")//HARSH20210326
			{
				advi_bank_cd = req.getParameter("hid_advi_bank_cd")==null?"":req.getParameter("hid_advi_bank_cd");
			}
			String advi_bank_ref = req.getParameter("advi_bank_ref")==null?"":req.getParameter("advi_bank_ref");
			String confirm_bank_cd = req.getParameter("confirm_bank_cd")==null?"":req.getParameter("confirm_bank_cd");
			if(confirm_bank_cd.equals("") || confirm_bank_cd == "")//HARSH20210326
			{
				confirm_bank_cd = req.getParameter("hid_confirm_bank_cd")==null?"":req.getParameter("hid_confirm_bank_cd");
			}
			String confirm_bank_ref = req.getParameter("confirm_bank_ref")==null?"":req.getParameter("confirm_bank_ref");
			String received_dt = req.getParameter("received_dt")==null?"":req.getParameter("received_dt");
			String issuance_dt = req.getParameter("issuance_dt")==null?"":req.getParameter("issuance_dt");
			String e_date = req.getParameter("e_date")==null?"":req.getParameter("e_date");
			String review_date = req.getParameter("review_date")==null?"":req.getParameter("review_date");
			String tenor = req.getParameter("tenor")==null?"":req.getParameter("tenor");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String remarks = req.getParameter("remarks")==null?"":req.getParameter("remarks");
			remarks=remarks.replaceAll("'", "''"); //LIVE ISSUE //SINGLE QUORT IN REMARK HARSH20210430
			String sec_type = req.getParameter("sec_type")==null?"":req.getParameter("sec_type");
			if(sec_type.equals("") || sec_type == "")//HARSH20210412
			{
				sec_type = req.getParameter("hid_sec_type")==null?"":req.getParameter("hid_sec_type");
			}
			String operation = req.getParameter("operation")==null?"":req.getParameter("operation");
			String lc_seq_no = req.getParameter("lc_seq_no")==null?"":req.getParameter("lc_seq_no");
			String mapping_id_ch=req.getParameter("link")==null?"":req.getParameter("link");
			String link_ch = req.getParameter("cont_deal_id")==null?"":req.getParameter("cont_deal_id");
			String ref_no = req.getParameter("ref_no")==null?"":req.getParameter("ref_no");
			String mapping_id="";
			String link = "";
						
			//column name for PCG
			String selection_I1 = req.getParameter("selection_I1")==null?"":req.getParameter("selection_I1");
			String selection_R1 = req.getParameter("selection_R1")==null?"":req.getParameter("selection_R1");
			String sec_type1 = req.getParameter("sec_type1")==null?"":req.getParameter("sec_type1");
			String buyer_cd1 = req.getParameter("cust_cd1")==null?"":req.getParameter("cust_cd1");
			String deal_type1 = req.getParameter("deal_type1")==null?"":req.getParameter("deal_type1");
			String value1 = req.getParameter("value1")==null?"":req.getParameter("value1");
			String gurantor_nm1 = req.getParameter("gurantor_nm1")==null?"":req.getParameter("gurantor_nm1");
			String received_dt1 = req.getParameter("received_dt1")==null?"":req.getParameter("received_dt1");
			String issuance_dt1 = req.getParameter("issuance_dt1")==null?"":req.getParameter("issuance_dt1");
			String e_date1 = req.getParameter("e_date1")==null?"":req.getParameter("e_date1");
			String renew_dt1 = req.getParameter("renew_dt1")==null?"":req.getParameter("renew_dt1");
			String tenor1 = req.getParameter("tenor1")==null?"":req.getParameter("tenor1");
			String remarks1 = req.getParameter("remarks1")==null?"":req.getParameter("remarks1");
			remarks1=remarks1.replaceAll("'", "''"); //LIVE ISSUE //SINGLE QUORT IN REMARK HARSH20210430
			String currency1 = req.getParameter("currency1")==null?"":req.getParameter("currency1");
			String review_date1 = req.getParameter("review_date1")==null?"":req.getParameter("review_date1");
			
			//For Linking Existing
			String sref_no = req.getParameter("sref_no")==null?"":req.getParameter("sref_no");
			
			mapping_id = req.getParameter("link")==null?"":req.getParameter("link");
			
			//HARSH20210130 AS PER CUSTOMER FEEDBACK ON 29/01/2021
			/*HARSH20210130 if(mapping_id_ch.equals("All"))
			{
				/*if(!mapping_id.equals(""))
				{
					String split_map_id[] = mapping_id.split("-");
					mapping_id = "A"+split_map_id[0].charAt(1)+"-"+split_map_id[1].charAt(0)+"-"+split_map_id[2].charAt(0)+"-"+split_map_id[3].charAt(0);
				}*/
				//System.out.println("Mapping ID For PCG:-"+mapping_id);
				//mapping_id = "A0-0-0-0";
				/*mapping_id = req.getParameter("link")==null?"":req.getParameter("link");
			}
			else
			{
				mapping_id = req.getParameter("link")==null?"":req.getParameter("link");
			}*/
			link = req.getParameter("cont_deal_id")==null?"":req.getParameter("cont_deal_id");
			if(link.equals("") || link == "")//HARSH20210326
			{
				link = req.getParameter("hid_cont_deal_id")==null?"":req.getParameter("hid_cont_deal_id");
				if(link.equals("")) //AS REQUESTED BY JAYASARI //AS PER INCIDENT#216 //HARSH20211218
				{
					link="0";
				}
			}
			
			//HARSH20210130 AS PER CUSTOMER FEEDBACK ON 29/01/2021
			/*HARSH20210130 if(link_ch.equals("All") || sec_type1.equals("PCG"))
			{
				if(!link.equals(""))
				{
					link = "A"+link.substring(1,link.length());
				}
				//link = "A0-0-0-0";
			}
			else
			{
				link = req.getParameter("cont_deal_id")==null?"":req.getParameter("cont_deal_id");
			}*/
			//System.out.println("LInk----"+link+"---"+mapping_id);
				
			String dtl_seq_no = "";
			int record_found = 0;
			String seq_no = "";
			String seq_no_update = req.getParameter("seq_no")==null?"":req.getParameter("seq_no");
			if(ref_no.equals(""))
			{
				//ref_no = buyer_cd+"-"+seq_no_update+"/"+issu_bank_ref;
			}
			
			///////////HARSH20210109/////////OLD VALUE////////////
			String oldValue = req.getParameter("old_value")==null?"":req.getParameter("old_value");
			String newValue = "";
			//////////////////////////////////////////////////////
			String guarantor_cd = "";
			query = "select customer_cd from fms7_customer_mst where customer_abbr='"+customer_abbr+"'";
			rset=stmt.executeQuery(query);
			if(rset.next()){
				guarantor_cd=rset.getString(1)==null?"":rset.getString(1);
			}else{
				query = "select trader_cd from fms7_trader_mst where trader_abbr='"+customer_abbr+"'";
				rset=stmt.executeQuery(query);
				if(rset.next()){
					guarantor_cd=rset.getString(1)==null?"":rset.getString(1);
				}
			}
			///////////////////////////////////////////////////////
			if(sec_type1.equals("PCG"))
			{
				if(operation.equalsIgnoreCase("MODIFY"))
				{
					if(!customer_abbr.equals("") && !seq_no_update.equals(""))
					{
						String checkExist="select nvl(count(*),0) from FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+seq_no_update+"'";
						rset= stmt.executeQuery(checkExist);
						if(rset.next())
						{
							record_found = rset.getInt(1);
							if(record_found > 0) 
							{
								String dtl_cust_cd = "";
								String dtl_seqNo = "";
							
								//HARSH20210109
								String SecRefNo=req.getParameter("ref_no")==null?"":req.getParameter("ref_no");
								newValue="CustABBR="+customer_abbr+"#SecRefNo="+SecRefNo+"#Dealty="+deal_type1+"#DealNo="+link+"#SecTy="+sec_type1+"#DueDt="+received_dt1+"#IssDt="+issuance_dt1+"#ExpDt="+e_date1+"#RenewDt="+renew_dt1+"#Tenor="+tenor1+"#Status=In order#ReviewDt="+review_date1+"#Currency="+currency1+"#Amt="+value1+"#Rmk="+remarks1+"#In="+selection_R1+"#Out="+selection_I1+"#GuarantorAbbr="+gurantor_nm1;
								////
								String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type1+"',DEAL_TYPE='"+deal_type1+"',VALUE='"+value1+"',"
										+ "REC_DT=to_date('"+received_dt1+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt1+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date1+"','dd/mm/yyyy'),RENEW_DT=to_date('"+renew_dt1+"','dd/mm/yyyy'),"
										+ "TENOR='"+tenor1+"',REMARKS='"+remarks1+"',issued='"+selection_I1+"',received='"+selection_R1+"',CURRENCY='"+currency1+"',REVIEW_DT=TO_DATE('"+review_date1+"','dd/mm/yyyy'),FLAG='Y',STATUS='In order',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',APRV_DT=sysdate,APRV_BY='"+user_cd+"',GUARANTOR_ABBR='"+gurantor_nm1+"',GUARANTOR_NM='"+guarantor_cd+"' "
										+ "where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+seq_no_update+"'";
//								System.out.println(UPDATE_FMS9_SECURITY_POST_MST);	
								stmt.executeUpdate(UPDATE_FMS9_SECURITY_POST_MST);
								msg="Modification Done Successfully!!";
							}
							else
							{
								err_msg="No Record Found For Modification!!";
							}
						}
						else
						{
							err_msg="Data Not Found for Modification!!";
						}
					}
					else
					{
						err_msg="Customer name and sequence no not Available!!";
					}
				}
				else
				{
					if(!customer_abbr.equals(""))
					{
						String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"'";
						rset = stmt.executeQuery(max_seq_no);
						if(rset.next()) {
							seq_no = rset.getString(1)==null?"1":rset.getString(1);
							//ref_no = cust_abbr+"-"+buyer_cd1+"-"+seq_no;
							//HARSH20210215 ref_no = customer_abbr+"-"+buyer_cd1+"-"+seq_no;
							ref_no = customer_abbr+"-S-"+seq_no;
						}
						//HARSH20210109
						newValue="CustABBR="+customer_abbr+"#SecRefNo="+ref_no+"#Dealty="+deal_type1+"#DealNo="+link+"#SecTy="+sec_type1+"#DueDt="+received_dt1+"#IssDt="+issuance_dt1+"#ExpDt="+e_date1+"#RenewDt="+renew_dt1+"#Tenor="+tenor1+"#Status=In order#ReviewDt="+review_date1+"#Currency="+currency1+"#Amt="+value1+"#Rmk="+remarks1+"#In="+selection_R1+"#Out="+selection_I1+"#GuarantorAbbr="+gurantor_nm1;
						////
						String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,SEC_TYPE,DEAL_TYPE,VALUE,REC_DT,ISSU_DT,EXP_DT,RENEW_DT,TENOR,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,GUARANTOR_ABBR,MAPPING_ID,CURRENCY,STATUS,APRV_DT,APRV_BY,REVIEW_DT,REF_NO,FLAG,GUARANTOR_NM) values"
								+ "('"+buyer_cd1+"','"+customer_abbr+"','"+seq_no+"','"+sec_type1+"','"+deal_type1+"','"+value1+"',to_date('"+received_dt1+"','dd/mm/yyyy'),to_date('"+issuance_dt1+"','dd/mm/yyyy'),to_date('"+e_date1+"','dd/mm/yyyy'),to_date('"+renew_dt1+"','dd/mm/yyyy'),'"+tenor1+"','"+remarks1+"','"+selection_I1+"','"+selection_R1+"','"+user_cd+"',sysdate,'"+link+"','"+gurantor_nm1+"','"+mapping_id+"','"+currency1+"','In order',sysdate,'"+user_cd+"',TO_DATE('"+review_date1+"','dd/mm/yyyy'),'"+ref_no+"','Y','"+guarantor_cd+"')";
//						System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
						stmt.executeUpdate(FMS9_SECURITY_POST_MST);
						msg="Data Submitted Successfully!!!";
					}
					else
					{
						err_msg="Customer Not Found";
					}
				}
			}
			else
			{
				if(operation.equalsIgnoreCase("MODIFY"))
				{
					if(!customer_abbr.equals("") && !seq_no_update.equals(""))
					{
						String checkExist="select nvl(count(*),0) from FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+seq_no_update+"'";
						rset1= stmt1.executeQuery(checkExist);
						if(rset1.next())
						{
							record_found = rset1.getInt(1);
							if(record_found > 0) 
							{
								String dtl_cust_cd = "";
								String dtl_seqNo = "";
								
								//HARSH20210109
								newValue="CustABBR="+customer_abbr+"#SecRefNo="+ref_no+"#Dealty="+deal_type+"#DealNo="+link+"#SecTy="+sec_type+"#ValueVari="+value_vari+"#ValueFlcu="+value_fluc+"#IssBk="+issu_bank_cd+"#IssBkRef="+issu_bank_ref+"#AdvBk="+advi_bank_cd+"#AdvBkRef="+advi_bank_ref+"#ConfBk="+confirm_bank_cd+"#ConfBkRef="+confirm_bank_ref+"#DueDt="+received_dt+"#IssDt="+issuance_dt+"#ExpDt="+e_date+"#Tenor="+tenor+"#Status="+status+"#ReviewDt="+review_date+"#Currency="+currency+"#Amt="+value+"#Rmk="+remarks+"#In="+selection_R+"#Out="+selection_I;
								////
								if(status.equals("In order"))
								{
									String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
											+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',REF_NO='"+ref_no+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',REVIEW_DT=TO_DATE('"+review_date+"','dd/mm/yyyy'),MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',APRV_DT=sysdate,APRV_BY='"+user_cd+"',FLAG='Y',INORDER_HIST='Y' "
											+ "where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+seq_no_update+"'";
									stmt.executeUpdate(UPDATE_FMS9_SECURITY_POST_MST);
									if(!ref_no.equals("") && mapping_id.equals(""))
									{
										String quray = "select customer_abbr,seq_no from FMS9_SECURITY_POST_MST where REF_NO='"+ref_no+"' and MAPPING_ID IS NOT NULL AND STATUS NOT IN ('Pending','Cancelled')";
										rset1=stmt1.executeQuery(quray);
										while(rset1.next())
										{   
											String cust_abbr_R = rset1.getString(1);
											String seqno = rset1.getString(2);
											String quray1 = "update FMS9_SECURITY_POST_MST set SEC_TYPE='"+sec_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',"
														+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
														+ "TENOR='"+tenor+"',STATUS='"+status+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',APRV_DT=sysdate,APRV_BY='"+user_cd+"',FLAG='Y',INORDER_HIST='Y' where CUSTOMER_ABBR='"+cust_abbr_R+"' and SEQ_NO='"+seqno+"'";
											stmt.executeUpdate(quray1);
										}
									}
								}
								else if(status.equals("Cancelled"))
								{
									String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
											+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',REF_NO='"+ref_no+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',REVIEW_DT=TO_DATE('"+review_date+"','dd/mm/yyyy'),MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',FLAG='X',CANCEL_DT=SYSDATE "
											+ "where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+seq_no_update+"'";
									stmt.executeUpdate(UPDATE_FMS9_SECURITY_POST_MST);
									if(!ref_no.equals("") && mapping_id.equals(""))
									{
										String quray = "select customer_abbr,seq_no from FMS9_SECURITY_POST_MST where REF_NO='"+ref_no+"' and MAPPING_ID IS NOT NULL AND STATUS NOT IN ('Pending','Cancelled')";
										rset1=stmt1.executeQuery(quray);
										while(rset1.next())
										{   
											String cust_abbr_R = rset1.getString(1);
											String seqno = rset1.getString(2);
											String quray1 = "update FMS9_SECURITY_POST_MST set SEC_TYPE='"+sec_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',"
														+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
														+ "TENOR='"+tenor+"',STATUS='"+status+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',FLAG='X',CANCEL_DT=SYSDATE where CUSTOMER_ABBR='"+cust_abbr_R+"' and SEQ_NO='"+seqno+"'";
											stmt.executeUpdate(quray1);
										}
									}
									
								}
								else if(status.equals("Expired"))
								{
									String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
											+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',REF_NO='"+ref_no+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',REVIEW_DT=TO_DATE('"+review_date+"','dd/mm/yyyy'),MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',FLAG='N' "
											+ "where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+seq_no_update+"'";
									stmt.executeUpdate(UPDATE_FMS9_SECURITY_POST_MST);
									if(!ref_no.equals("") && mapping_id.equals(""))
									{
										String quray = "select customer_abbr,seq_no from FMS9_SECURITY_POST_MST where REF_NO='"+ref_no+"' and MAPPING_ID IS NOT NULL AND STATUS NOT IN ('Pending','Cancelled')";
										rset1=stmt1.executeQuery(quray);
										while(rset1.next())
										{   
											String cust_abbr_R = rset1.getString(1);
											String seqno = rset1.getString(2);
											String quray1 = "update FMS9_SECURITY_POST_MST set SEC_TYPE='"+sec_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',"
														+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
														+ "TENOR='"+tenor+"',STATUS='"+status+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',FLAG='N' where CUSTOMER_ABBR='"+cust_abbr_R+"' and SEQ_NO='"+seqno+"'";
											stmt.executeUpdate(quray1);
										}
									}
									
								}
								else
								{
									String UPDATE_FMS9_SECURITY_POST_MST = "update FMS9_SECURITY_POST_MST set MAPPING_ID='"+mapping_id+"',SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',ISS_BANK_REF='"+issu_bank_ref+"',"
											+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
											+ "TENOR='"+tenor+"',STATUS='"+status+"',REMARKS='"+remarks+"',REF_NO='"+ref_no+"',LINK='"+link+"',issued='"+selection_I+"',received='"+selection_R+"',REVIEW_DT=TO_DATE('"+review_date+"','dd/mm/yyyy'),MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',APRV_DT=TO_DATE('','DD/MM/YYYY'),APRV_BY='',FLAG='' "
											+ "where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+seq_no_update+"'";
									stmt.executeUpdate(UPDATE_FMS9_SECURITY_POST_MST);
									if(!ref_no.equals("") && mapping_id.equals(""))
									{
										String quray = "select customer_abbr,seq_no from FMS9_SECURITY_POST_MST where REF_NO='"+ref_no+"' and MAPPING_ID IS NOT NULL AND STATUS NOT IN ('Pending','Cancelled')";
										rset1=stmt1.executeQuery(quray);
										while(rset1.next())
										{   
											String cust_abbr_R = rset1.getString(1);
											String seqno = rset1.getString(2);
											String quray1 = "update FMS9_SECURITY_POST_MST set SEC_TYPE='"+sec_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+value_vari+"',VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+issu_bank_cd+"',"
														+ "ADV_BANK_CD='"+advi_bank_cd+"',ADV_BANK_REF='"+advi_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',REC_DT=to_date('"+received_dt+"','dd/mm/yyyy'),ISSU_DT=to_date('"+issuance_dt+"','dd/mm/yyyy'),EXP_DT=to_date('"+e_date+"','dd/mm/yyyy'),"
														+ "TENOR='"+tenor+"',STATUS='"+status+"',MODIFY_DT=sysdate,MODIFY_BY='"+user_cd+"',APRV_DT=TO_DATE('','DD/MM/YYYY'),APRV_BY='',FLAG='' where CUSTOMER_ABBR='"+cust_abbr_R+"' and SEQ_NO='"+seqno+"'";
											stmt.executeUpdate(quray1);
										}
									}
								}
								msg="Modification Done Successfully!!";
							}
							else
							{
								err_msg="No Record Found For Modification!!";
							}
						}
						else
						{
							err_msg="Data Not Found for Modification!!";
						}
					}
					else
					{
						err_msg="Customer name and sequence no not Available!!";
					}
				}
				else if(operation.equalsIgnoreCase("INSERT_LINK"))
				{
					if(!link.equals("") && !sref_no.equals(""))
					{
						String FMS9_SECURITY_POST_MST_FETCH="SELECT CUSTOMER_CD,SEQ_NO,SEC_TYPE,DEAL_TYPE,GUARANTOR_NM,CURRENCY,VARIATION_VALUE,"
								+ "VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,TO_CHAR(REC_DT,'DD/MM/YYYY'),"
								+ "TO_CHAR(ISSU_DT,'DD/MM/YYYY'),TO_CHAR(EXP_DT,'DD/MM/YYYY'),TO_CHAR(RENEW_DT,'DD/MM/YYYY'),TENOR,STATUS,REMARKS,LINK, ISSUED,RECEIVED,"
								+ "MAPPING_ID,TO_CHAR(REVIEW_DT,'DD/MM/YYYY'),REF_NO,ENT_CD,TO_CHAR(ENT_DT,'DD/MM/YYYY'),TO_CHAR(MODIFY_DT,'DD/MM/YYYY'),MODIFY_BY,"
								+ "TO_CHAR(APRV_DT,'DD/MM/YYYY'),APRV_BY,FLAG,STATUS,INORDER_HIST "
								+ "FROM FMS9_SECURITY_POST_MST WHERE REF_NO='"+sref_no+"'";
//						System.out.println("FMS9_SECURITY_POST_MST_FETCH--"+FMS9_SECURITY_POST_MST_FETCH);
						rset=stmt.executeQuery(FMS9_SECURITY_POST_MST_FETCH);
						if(rset.next())
						{
							String old_secType=rset.getString(3)==null?"":rset.getString(3);
							String old_dealType=rset.getString(4)==null?"":rset.getString(4);
							String old_currency = rset.getString(6)==null?"":rset.getString(6);
							String old_variValue = rset.getString(7)==null?"":rset.getString(7);
							String old_value = rset.getString(8)==null?"":rset.getString(8);
							String old_valueFluc = rset.getString(9)==null?"":rset.getString(9);
							String old_issBankCd = rset.getString(10)==null?"":rset.getString(10);
							String old_issBankRef = rset.getString(11)==null?"":rset.getString(11);
							String old_advBankCd = rset.getString(12)==null?"":rset.getString(12);
							String old_advBankRef = rset.getString(13)==null?"":rset.getString(13);
							String old_confBankCd = rset.getString(14)==null?"":rset.getString(14);
							String old_confBankRef = rset.getString(15)==null?"":rset.getString(15);
							String old_receDt = rset.getString(16)==null?"":rset.getString(16);
							String old_issuDt = rset.getString(17)==null?"":rset.getString(17);
							String old_expDt = rset.getString(18)==null?"":rset.getString(18);
							String old_renewDt = rset.getString(19)==null?"":rset.getString(19);
							String old_tenor = rset.getString(20)==null?"":rset.getString(20);
							String old_link = rset.getString(23)==null?"":rset.getString(23);
							String old_issued = rset.getString(24)==null?"":rset.getString(24);
							String old_received = rset.getString(25)==null?"":rset.getString(25);
							String old_Status = rset.getString(36)==null?"":rset.getString(36); //HARSH20210521
							String old_Inorder_Hist = rset.getString(37)==null?"":rset.getString(37); //HARSH20210521
							
							String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"'";
							rset1 = stmt1.executeQuery(max_seq_no);
							if(rset1.next()) {
								seq_no = rset1.getString(1)==null?"1":rset1.getString(1);
							}
							//HARSH20210109
							newValue="CustABBR="+customer_abbr+"#SecRefNo="+sref_no+"#Dealty="+old_dealType+"#DealNo="+link+"#SecTy="+old_secType+"#ValueVari="+old_variValue+"#ValueFlcu="+old_valueFluc+"#IssBk="+old_issBankCd+"#IssBkRef="+old_issBankRef+"#AdvBk="+old_advBankCd+"#AdvBkRef="+old_advBankRef+"#ConfBk="+old_confBankCd+"#ConfBkRef="+old_confBankRef+"#DueDt="+old_receDt+"#IssDt="+old_issuDt+"#ExpDt="+old_expDt+"#Tenor="+old_tenor+"#Status="+old_Status+"#ReviewDt="+review_date+"#Currency="+old_currency+"#Amt="+old_value+"#Rmk="+remarks1+"#In="+old_received+"#Out="+old_issued;
							////
							
							//HARSH20210521 DUMMY TO BE LINKED AS REQUESTED BY JENNIE ON 21/05/2021
							if(old_Status.equals("In order"))
							{
								String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,REF_NO,MAPPING_ID,STATUS,REMARKS,APRV_DT,APRV_BY,FLAG,INORDER_HIST) values"
										+ "('"+buyer_cd1+"','"+customer_abbr+"','"+seq_no+"','"+old_secType+"','"+old_dealType+"','"+old_currency+"','"+old_variValue+"','"+old_value+"','"+old_valueFluc+"','"+old_issBankCd+"','"+old_issBankRef+"','"+old_advBankCd+"','"+old_advBankRef+"','"+old_confBankCd+"','"+old_confBankRef+"',to_date('"+old_receDt+"','dd/mm/yyyy'),to_date('"+old_issuDt+"','dd/mm/yyyy'),to_date('"+old_expDt+"','dd/mm/yyyy'),'"+old_tenor+"','"+old_issued+"','"+old_received+"','"+user_cd+"',sysdate,'"+link+"','"+sref_no+"','"+old_link+"','In order','"+remarks1+"',SYSDATE,'"+user_cd+"','Y','Y')";
//								System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
								stmt2.executeUpdate(FMS9_SECURITY_POST_MST);
							}
							else if(old_Status.equals("Dummy"))
							{
								String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,REF_NO,MAPPING_ID,STATUS,REMARKS,APRV_DT,APRV_BY,FLAG,INORDER_HIST) values"
										+ "('"+buyer_cd1+"','"+customer_abbr+"','"+seq_no+"','"+old_secType+"','"+old_dealType+"','"+old_currency+"','"+old_variValue+"','"+old_value+"','"+old_valueFluc+"','"+old_issBankCd+"','"+old_issBankRef+"','"+old_advBankCd+"','"+old_advBankRef+"','"+old_confBankCd+"','"+old_confBankRef+"',to_date('"+old_receDt+"','dd/mm/yyyy'),to_date('"+old_issuDt+"','dd/mm/yyyy'),to_date('"+old_expDt+"','dd/mm/yyyy'),'"+old_tenor+"','"+old_issued+"','"+old_received+"','"+user_cd+"',sysdate,'"+link+"','"+sref_no+"','"+old_link+"','"+old_Status+"','"+remarks1+"','','','','"+old_Inorder_Hist+"')";
//								System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
								stmt2.executeUpdate(FMS9_SECURITY_POST_MST);
							}
							
							msg="Linked Exsiting Securiry with "+sref_no;
						}
						
					}
				}
				else if(operation.equalsIgnoreCase("RESTATE"))//HARSH20210410
				{
					if(!customer_abbr.equals("") && !seq_no_update.equals(""))
					{
						String INORDER_HIST = "";
						if(status.equalsIgnoreCase("In order"))
						{
							INORDER_HIST="Y";
						}
						String sysdt = "",issdt = "",canceldt="";
						query = "SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY'),TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM DUAL";
						rset=stmt.executeQuery(query);
						if(rset.next())
						{
							sysdt = rset.getString(2)==null?"":rset.getString(2);
							issdt = rset.getString(2)==null?"":rset.getString(2);
							canceldt = rset.getString(1)==null?"":rset.getString(1); 
						}
						
						String temp_ref = ref_no;
						String dtlseqno="";
						query = "UPDATE FMS9_SECURITY_POST_MST SET STATUS='Restated',CANCEL_DT=SYSDATE,FLAG='X',INORDER_HIST='"+INORDER_HIST+"' WHERE CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO='"+seq_no_update+"'";
						//System.out.println(ref_no);
						stmt.executeUpdate(query);
						
						if(!ref_no.equalsIgnoreCase(""))
						{
							query = "UPDATE FMS9_SECURITY_POST_MST SET STATUS='Restated',CANCEL_DT=SYSDATE,FLAG='X',INORDER_HIST='"+INORDER_HIST+"' WHERE REF_NO = '"+ref_no+"' and mapping_id is not null";
							stmt.executeUpdate(query);
						}
						//newValue="CustABBR="+customer_abbr+"#SecRefNo="+ref_no+"#Dealty="+deal_type+"#DealNo="+link+"#Status=Restated#CancelDt="+canceldt;
						//oldValue ="CustABBR="+customer_abbr+"#SecRefNo="+ref_no+"#Dealty="+deal_type+"#DealNo="+link+"#Status="+status+"#CancelDt=";
						
						newValue="CustABBR="+customer_abbr+"#SecRefNo="+ref_no+"#Dealty="+deal_type+"#DealNo="+link+"#Status=Restated";
						oldValue ="CustABBR="+customer_abbr+"#SecRefNo="+ref_no+"#Dealty="+deal_type+"#DealNo="+link+"#Status="+status;
						msg="Modification Done Successfully!!";
						try
						{
							//HARSH20210110 new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg+"$NEW$"+newValue+"%OLD%"+oldValue);
							InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,oldValue,newValue,msg); //HARSH20210110
						}
						catch(Exception infoLogger)
						{
							System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
							infoLogger.printStackTrace();
						}
						
						/////for log
						String max_dtl_seq_no = "select max(nvl(DTL_SEQ_NO,1)+1) from LOG_FMS9_SECURITY_POST_MST "
								+ "where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+seq_no_update+"'";
						rset = stmt.executeQuery(max_dtl_seq_no);
						if(rset.next()) {
							dtlseqno = rset.getString(1)==null?"1":rset.getString(1);
						}
						String LOG_FMS9_SECURITY_POST_MST = "INSERT INTO LOG_FMS9_SECURITY_POST_MST SELECT CUSTOMER_CD,SEQ_NO,'"+dtlseqno+"',sysdate,"
								+ "SEC_TYPE,DEAL_TYPE,GUARANTOR_NM,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,"
								+ "ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,"
								+ "EXP_DT,RENEW_DT,TENOR,STATUS,REMARKS,LINK,ISSUED,RECEIVED,MAPPING_ID,"
								+ "REVIEW_DT,REF_NO,ENT_CD,ENT_DT,MODIFY_DT,"
								+ "MODIFY_BY,APRV_DT,APRV_BY,FLAG,CUSTOMER_ABBR,GUARANTOR_ABBR,CANCEL_DT,INORDER_HIST "
								+ "FROM FMS9_SECURITY_POST_MST "
								+ "WHERE CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO='"+seq_no_update+"'";
						stmt.executeUpdate(LOG_FMS9_SECURITY_POST_MST);
						
						
						String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"'";
						rset = stmt.executeQuery(max_seq_no);
						if(rset.next())
						{
							seq_no = rset.getString(1)==null?"1":rset.getString(1);
							if(!ref_no.equals(""))
							{
								String split[] = ref_no.split("-");
								if(split[split.length-1].startsWith("V"))
								{
									String version = split[split.length-1].substring(1,split[split.length-1].length());
									int v = Integer.parseInt(version);
									v=v+1;
									ref_no = ref_no.substring(0,ref_no.length()-1)+v;
								}
								else
								{
									ref_no = ref_no+"-V1";
								}
							}
						}
						
						newValue="CustABBR="+customer_abbr+"#SecRefNo="+ref_no+"#Dealty="+deal_type+"#DealNo="+link+"#SecTy="+sec_type+"#ValueVari="+value_vari+"#ValueFlcu="+value_fluc+"#IssBk="+issu_bank_cd+"#IssBkRef="+issu_bank_ref+"#AdvBk="+advi_bank_cd+"#AdvBkRef="+advi_bank_ref+"#ConfBk="+confirm_bank_cd+"#ConfBkRef="+confirm_bank_ref+"#DueDt="+received_dt+"#IssDt="+issdt+"#ExpDt="+e_date+"#Tenor="+tenor+"#Status=Pending for amendment#ReviewDt="+review_date+"#Currency="+currency+"#Amt="+value+"#Rmk="+remarks+"#In="+selection_R+"#Out="+selection_I;
						oldValue="";
						
						String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,REVIEW_DT,REF_NO,RENEW_DT,INORDER_HIST) values"
								+ "('"+buyer_cd+"','"+customer_abbr+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date(to_char(sysdate,'dd/mm/yyyy'),'dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','Pending for amendment','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"',TO_DATE('"+review_date+"','dd/mm/yyyy'),'"+ref_no+"',to_date(to_char(SYSDATE,'dd/mm/yyyy'),'dd/mm/yyyy'),'"+INORDER_HIST+"')";
//						System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
						stmt.executeUpdate(FMS9_SECURITY_POST_MST);
						
						msg=temp_ref+" is restated as "+ref_no;
					}
				}
				else
				{
					if(!customer_abbr.equals(""))
					{
						String max_seq_no = "select max(nvl(SEQ_NO,1)+1) from FMS9_SECURITY_POST_MST where CUSTOMER_ABBR='"+customer_abbr+"'";
						rset = stmt.executeQuery(max_seq_no);
						if(rset.next()) {
							seq_no = rset.getString(1)==null?"1":rset.getString(1);
							//ref_no = cust_abbr+"-"+buyer_cd+"-"+seq_no;
							//ref_no = customer_abbr+"-"+buyer_cd+"-"+seq_no; //HARSH20210213
							ref_no = customer_abbr+"-S-"+seq_no;
						}
						//HARSH20210109
						newValue="CustABBR="+customer_abbr+"#SecRefNo="+ref_no+"#Dealty="+deal_type+"#DealNo="+link+"#SecTy="+sec_type+"#ValueVari="+value_vari+"#ValueFlcu="+value_fluc+"#IssBk="+issu_bank_cd+"#IssBkRef="+issu_bank_ref+"#AdvBk="+advi_bank_cd+"#AdvBkRef="+advi_bank_ref+"#ConfBk="+confirm_bank_cd+"#ConfBkRef="+confirm_bank_ref+"#DueDt="+received_dt+"#IssDt="+issuance_dt+"#ExpDt="+e_date+"#Tenor="+tenor+"#Status="+status+"#ReviewDt="+review_date+"#Currency="+currency+"#Amt="+value+"#Rmk="+remarks+"#In="+selection_R+"#Out="+selection_I;
						////
						if(status.equals("In order"))
						{
							String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,APRV_DT,APRV_BY,REVIEW_DT,REF_NO,FLAG,INORDER_HIST) values"
									+ "('"+buyer_cd+"','"+customer_abbr+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date('"+issuance_dt+"','dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','"+status+"','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"',sysdate,'"+user_cd+"',to_date('"+review_date+"','dd/mm/yyyy'),'"+ref_no+"','Y','Y')";
//							System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
							stmt.executeUpdate(FMS9_SECURITY_POST_MST);
						}
						else if(status.equals("Cancelled"))
						{
							String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,MODIFY_DT,MODIFY_BY,REVIEW_DT,REF_NO,FLAG,CANCEL_DT) values"
									+ "('"+buyer_cd+"','"+customer_abbr+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date('"+issuance_dt+"','dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','"+status+"','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"',sysdate,'"+user_cd+"',TO_DATE('"+review_date+"','dd/mm/yyyy'),'"+ref_no+"','X',sysdate)";
//							System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
							stmt.executeUpdate(FMS9_SECURITY_POST_MST);
						}
						else if(status.equals("Expired"))
						{
							String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,MODIFY_DT,MODIFY_BY,REVIEW_DT,REF_N0,FLAG) values"
									+ "('"+buyer_cd+"','"+customer_abbr+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date('"+issuance_dt+"','dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','"+status+"','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"',sysdate,'"+user_cd+"',TO_DATE('"+review_date+"','dd/mm/yyyy'),'"+ref_no+"','N')";
//							System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
							stmt.executeUpdate(FMS9_SECURITY_POST_MST);
						}
						else
						{
							String FMS9_SECURITY_POST_MST = "insert into FMS9_SECURITY_POST_MST(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,EXP_DT,TENOR,STATUS,REMARKS,ISSUED,RECEIVED,ENT_CD,ENT_DT,LINK,REVIEW_DT,REF_NO) values"
									+ "('"+buyer_cd+"','"+customer_abbr+"','"+seq_no+"','"+sec_type+"','"+deal_type+"','"+currency+"','"+value_vari+"','"+value+"','"+value_fluc+"','"+issu_bank_cd+"','"+issu_bank_ref+"','"+advi_bank_cd+"','"+advi_bank_ref+"','"+confirm_bank_cd+"','"+confirm_bank_ref+"',to_date('"+received_dt+"','dd/mm/yyyy'),to_date('"+issuance_dt+"','dd/mm/yyyy'),to_date('"+e_date+"','dd/mm/yyyy'),'"+tenor+"','"+status+"','"+remarks+"','"+selection_I+"','"+selection_R+"','"+user_cd+"',sysdate,'"+link+"',TO_DATE('"+review_date+"','dd/mm/yyyy'),'"+ref_no+"')";
//							System.out.println("FMS9_SECURITY_POST_MST--"+FMS9_SECURITY_POST_MST);
							stmt.executeUpdate(FMS9_SECURITY_POST_MST);
						}
						msg="Data Submitted Successfully!!!";
					}
					else
					{
						err_msg="Customer Not Found";
					}
				}
			}
			
			//GENERATE LOG FOR COLLATERAL MGMT HARSH20210316
			String log_seq_no = "";
			if(sec_type1.equals("PCG"))
			{
				if(operation.equalsIgnoreCase("MODIFY"))
				{
					log_seq_no = seq_no_update;
				}
				else
				{
					log_seq_no = seq_no;
				}
			}
			else
			{
				if(operation.equalsIgnoreCase("MODIFY"))
				{
					log_seq_no = seq_no_update;
				}
				else if(operation.equalsIgnoreCase("INSERT_LINK"))
				{
					log_seq_no = seq_no;
				}
				else if(operation.equalsIgnoreCase("RESTATE"))
				{
					log_seq_no = seq_no;
				}
				else
				{
					log_seq_no = seq_no;
				}
				
			}
			String max_dtl_seq_no = "select max(nvl(DTL_SEQ_NO,1)+1) from LOG_FMS9_SECURITY_POST_MST "
					+ "where CUSTOMER_ABBR='"+customer_abbr+"' and SEQ_NO='"+log_seq_no+"'";
			rset = stmt.executeQuery(max_dtl_seq_no);
			if(rset.next()) {
				dtl_seq_no = rset.getString(1)==null?"1":rset.getString(1);
			}
			String LOG_FMS9_SECURITY_POST_MST = "INSERT INTO LOG_FMS9_SECURITY_POST_MST SELECT CUSTOMER_CD,SEQ_NO,'"+dtl_seq_no+"',sysdate,"
					+ "SEC_TYPE,DEAL_TYPE,GUARANTOR_NM,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,"
					+ "ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,"
					+ "EXP_DT,RENEW_DT,TENOR,STATUS,REMARKS,LINK,ISSUED,RECEIVED,MAPPING_ID,"
					+ "REVIEW_DT,REF_NO,ENT_CD,ENT_DT,MODIFY_DT,"
					+ "MODIFY_BY,APRV_DT,APRV_BY,FLAG,CUSTOMER_ABBR,GUARANTOR_ABBR,CANCEL_DT,INORDER_HIST "
					+ "FROM FMS9_SECURITY_POST_MST "
					+ "WHERE CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO='"+log_seq_no+"'";
			stmt.executeUpdate(LOG_FMS9_SECURITY_POST_MST);
			
			
			//LOG FOR LINK EXISTING
			/*if(!sec_type1.equals("PCG") && operation.equalsIgnoreCase("MODIFY"))
			{
				if(!ref_no.equals(""))
				{
					String quray = "select customer_abbr,seq_no from FMS9_SECURITY_POST_MST where REF_NO='"+ref_no+"'";
					rset1=stmt1.executeQuery(quray);
					while(rset1.next())
					{   
						String cust_abbr_R = rset1.getString(1);
						String seqno = rset1.getString(2);
						String link_max_dtl_seq_no = "select max(nvl(DTL_SEQ_NO,1)+1) from LOG_FMS9_SECURITY_POST_MST "
								+ "where CUSTOMER_ABBR='"+cust_abbr_R+"' and SEQ_NO='"+seqno+"'";
						rset = stmt.executeQuery(link_max_dtl_seq_no);
						if(rset.next()) {
							dtl_seq_no = rset.getString(1)==null?"1":rset.getString(1);
						}
						LOG_FMS9_SECURITY_POST_MST = "INSERT INTO LOG_FMS9_SECURITY_POST_MST SELECT CUSTOMER_CD,SEQ_NO,'"+dtl_seq_no+"',sysdate,"
								+ "SEC_TYPE,DEAL_TYPE,GUARANTOR_NM,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,"
								+ "ADV_BANK_REF,CONFIRM_BANK_CD,CONFIRM_BANK_REF,REC_DT,ISSU_DT,"
								+ "EXP_DT,RENEW_DT,TENOR,STATUS,REMARKS,LINK,ISSUED,RECEIVED,MAPPING_ID,"
								+ "REVIEW_DT,REF_NO,ENT_CD,ENT_DT,MODIFY_DT,"
								+ "MODIFY_BY,APRV_DT,APRV_BY,FLAG,CUSTOMER_ABBR,GUARANTOR_ABBR "
								+ "FROM FMS9_SECURITY_POST_MST "
								+ "WHERE CUSTOMER_ABBR='"+cust_abbr_R+"' AND SEQ_NO='"+seqno+"'";
						stmt.executeUpdate(LOG_FMS9_SECURITY_POST_MST);
						
					}
				}
			}*/
			
			//msg=msg.replaceAll(" ", "");
			//err_msg=err_msg.replaceAll(" ", "");
			url="../mrcr/frm_CR_Post_Deal_Capturing.jsp?msg="+msg+"&err_msg="+err_msg+"&customer_abbr="+customer_abbr+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			try
			{
				//HARSH20210110 new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg+"$NEW$"+newValue+"%OLD%"+oldValue);
				InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,oldValue,newValue,msg); //HARSH20210110
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			dbcon.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_CR_Post_Deal_Capturing.jsp?msg="+msg+"&err_msg="+err_msg+"&customer_abbr="+customer_abbr+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	////////////MALVIKA20201017////////////////////////////
	private void InsertHolidayShedual(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException 
	{
		String err_msg=""; 
		try {
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			String holiday_dt = req.getParameter("holiday_dt")==null?"":req.getParameter("holiday_dt");
			String holidayname = req.getParameter("holidayname")==null?"":req.getParameter("holidayname");
			String HolidayType = req.getParameter("HolidayType")==null?"":req.getParameter("HolidayType");
			String ac_status = req.getParameter("ac_status")==null?"":req.getParameter("ac_status");
			String sysdt = req.getParameter("sysdt")==null?"":req.getParameter("sysdt");
	
	
			String queryCheck = "SELECT * from FMS9_CURVE_HOLIDAY_DTL WHERE HOLIDAY_DT = TO_DATE('"+holiday_dt+"','DD/MM/YYYY') and HOLIDAY_TYPE = '"+HolidayType+"' ";
//			System.out.println("queryCheck---" + queryCheck);
			rset = stmt.executeQuery(queryCheck);
	
			if(rset.next()) {
			
				dbcon.close();
				err_msg="DATE IS ALRADY EXIST!!!";
				url="../mrcr/frm_mst_ADD_CurveHoliday.jsp?&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
//				System.out.println("Alrady Exist...");
			}//END IF
			else
			{
				String  FMS9_CURVE_HOLIDAY_DTL="insert into FMS9_CURVE_HOLIDAY_DTL (HOLIDAY_DT,HOLIDAY_TYPE,HOLIDAY_NM,ENT_BY,ENT_DT,FLAG)"
					  + " VALUES " +
					  " ( TO_DATE('"+holiday_dt+"','DD/MM/YYYY'),'"+HolidayType+"','"+holidayname+
					  "','"+user_cd+"',TO_DATE('"+sysdt+"','DD/MM/YYYY'),'"+ac_status+"')";
//					  System.out.println("FMS9_CURVE_HOLIDAY_DTL********"+FMS9_CURVE_HOLIDAY_DTL);
					  stmt.execute(FMS9_CURVE_HOLIDAY_DTL);
					  msg = "Data Submitted Successfully!!!";
					  url="../mrcr/frm_mst_ADD_CurveHoliday.jsp?msg="+msg+"&write_permission="+
					  write_permission+"&delete_permission="+delete_permission+"&print_permission="
					  +print_permission+"&approve_permission="+approve_permission+
					  "&audit_permission="+audit_permission;
					 
//				System.out.println("NEW Entery...");
				dbcon.commit();
			}//END ELSE
		}//END TRY
		catch (Exception e) {
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_mst_ADD_CurveHoliday.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}//END CATCH
	}//END InsertHolidayShedual

	////////////MALVIKA20201017////////////////////////////
	private void Update_Curve_Holiday(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException 
	{
		String err_msg=""; 
		try {
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			String holi_name = req.getParameter("holi_name")==null?"":req.getParameter("holi_name");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String holi_type = req.getParameter("holi_type")==null?"":req.getParameter("holi_type");
			String holi_dt = req.getParameter("holi_dt")==null?"":req.getParameter("holi_dt");
			
			
			
			String UPDATE_curve_holiday_dtl ="update FMS9_CURVE_HOLIDAY_DTL set  HOLIDAY_NM='"+holi_name+"', FLAG='"+status+"'"
			+ "where HOLIDAY_DT=TO_DATE('"+holi_dt+"','DD/MM/YYYY') and HOLIDAY_TYPE='"+holi_type+"' ";
			
//			System.out.println("UPDATE_curve_holiday_dtl   : "+UPDATE_curve_holiday_dtl);
			stmt1.execute(UPDATE_curve_holiday_dtl);
			msg = "Modification Done Successfully...";
			url="../mrcr/frm_mst_UPDATE_CurveHoliday.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			
			dbcon.commit();
		
		}
		catch (Exception e) {
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_mst_UPDATE_CurveHoliday.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	//////////////////////////////////////////////////////
	//HARSH20201212
	private void InsertPreDealCreditCheckReq(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException
	{	
		String err_msg=""; String MailBody="";
		msg="";
		try
		{
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			String sell_buy = req.getParameter("sell_buy")==null?"":req.getParameter("sell_buy");
			//////HARSH20201231/// customer cd like "1-S" so we split
//HARSH20210112			String customer_cd = req.getParameter("customer_cd")==null?"":req.getParameter("customer_cd");
//HARSH20210112			String cust_cd[] = customer_cd.split("-");
//HARSH20210112			customer_cd=cust_cd[0];
			///////////////////////////////////////////
			
			String customer_abbr = req.getParameter("customer_abbr")==null?"":req.getParameter("customer_abbr");//HARSH20210112
			if(customer_abbr == null || customer_abbr.equals("") || customer_abbr=="")//HARSH20210112
			{
				customer_abbr = req.getParameter("hid_customer_abbr")==null?"":req.getParameter("hid_customer_abbr");//HARSH20210112
			}
			String deliverly_start_dt = req.getParameter("deliverly_start_dt")==null?"":req.getParameter("deliverly_start_dt");
			String deliverly_end_dt = req.getParameter("deliverly_end_dt")==null?"":req.getParameter("deliverly_end_dt");
			String value = req.getParameter("value")==null?"":req.getParameter("value");
			String currency = req.getParameter("currency")==null?"":req.getParameter("currency");
			String dlv_terms = req.getParameter("dlv_terms")==null?"":req.getParameter("dlv_terms");
			String spot_terms = req.getParameter("spot_terms")==null?"":req.getParameter("spot_terms");
			String payment_terms = req.getParameter("payment_terms")==null?"":req.getParameter("payment_terms");
			String comments = req.getParameter("comments")==null?"":req.getParameter("comments");
			String shell_cont = req.getParameter("shell_cont")==null?"":req.getParameter("shell_cont");
			String operation = req.getParameter("operation")==null?"":req.getParameter("operation");
			String status="Pending";
			
			String mail_list = req.getParameter("mail_list")==null?"":req.getParameter("mail_list");
			
			int count=0;
			///Fetching customer name only for mailbody
			String Cust_Nm="",Cust_Cd="0"; //Fetch customer_cd using customer_abbr
			query1="select CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_ABBR='"+customer_abbr+"'";
			rset2=stmt2.executeQuery(query1);
			if(rset2.next())
			{
				Cust_Nm=rset2.getString(1)==null?"":rset2.getString(1);
				Cust_Cd=rset2.getString(2)==null?"0":rset2.getString(2);//HARSH20210112
			}else{
				query1 ="select TRADER_NAME,TRADER_CD from FMS7_TRADER_MST where TRADER_ABBR='"+customer_abbr+"'";
				rset2=stmt2.executeQuery(query1);
				if(rset2.next())
				{
					Cust_Nm=rset2.getString(1)==null?"":rset2.getString(1);
					Cust_Cd=rset2.getString(2)==null?"0":rset2.getString(2);//HARSH20210112
				}
			}
			///
			//////HARSH20210112///////For Audit Trail//////////
			String old_value = req.getParameter("old_value")==null?"":req.getParameter("old_value");
			String new_value="";
			new_value="CustABBR="+customer_abbr+"#Value="+value+"#Currency="+currency+"#BuySell="+sell_buy+"#StDt="+deliverly_start_dt+"#EnDt="+deliverly_end_dt+"#DTerms="+dlv_terms+"#STerms="+spot_terms+"#PTerms="+payment_terms+"#Rmk="+comments+"#ShellCo="+shell_cont;
			//////////////////////////////////////////////////
			if(operation.equalsIgnoreCase("Modify"))
			{
				String modify_SeqNo = req.getParameter("seq_no")==null?"":req.getParameter("seq_no");
				String modify_RequestId = req.getParameter("request_id")==null?"":req.getParameter("request_id");
				if(!customer_abbr.equals("") && !modify_SeqNo.equals("") && !modify_RequestId.equals(""))
				{
					queryString="select nvl(count(*),0) from FMS9_DEAL_REQUEST_DTL where customer_abbr='"+customer_abbr+"' and seq_no='"+modify_SeqNo+"' and request_id='"+modify_RequestId+"'";
					rset=stmt.executeQuery(queryString);
					if(rset.next())
					{
						count=rset.getInt(1);
						if(count>0)
						{
							query="UPDATE FMS9_DEAL_REQUEST_DTL SET START_DT=TO_DATE('"+deliverly_start_dt+"','DD/MM/YYYY'),END_DT=TO_DATE('"+deliverly_end_dt+"','DD/MM/YYYY'),VALUE='"+value+"',CURRENCY='"+currency+"',DLV_TERMS='"+dlv_terms+"',SPOT_TERMS='"+spot_terms+"',SHELL_CONT='"+shell_cont+"',BUY_SELL='"+sell_buy+"',"
									+ "PAYMENT_TERMS='"+payment_terms+"',REQUEST_MSG='"+comments+"',MODIFY_BY='"+user_cd+"',MODIFY_DT=sysdate WHERE CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO='"+modify_SeqNo+"' AND REQUEST_ID='"+modify_RequestId+"'"; //STATUS='"+status+"', HARSH20201229
							int update = stmt.executeUpdate(query);
							msg="Modification Done Successfully!!";
//							System.out.println(update);
							if(update == 1)
							{	
								if(!mail_list.equalsIgnoreCase(""))//HARSH20210805
								{
									MailBody+="<table style='font-size: x-middle; font-family:Calibri;' border='1' cellpadding='0' cellspacing='0'>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Request ID</b></font></td><td>&nbsp;"+modify_RequestId+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Customer Name</b></font></td><td>&nbsp;"+Cust_Nm+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Deal Value</b></font></td><td>&nbsp;"+value+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Deal Currency</b></font></td><td>&nbsp;"+currency+"</td></tr>";
									//MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Trader Name</b></font></td><td></td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Request Message</b></font></td><td>&nbsp;"+comments+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Delivery Date</b></font></td><td>&nbsp;"+deliverly_start_dt+" to "+deliverly_end_dt+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Shell Contracting Co.</b></font></td><td>&nbsp;"+shell_cont+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Buy/Sell</b></font></td><td>&nbsp;"+sell_buy+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Payment Terms</b></font></td><td>&nbsp;"+payment_terms+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Delivery Terms</b></font></td><td>&nbsp;"+dlv_terms+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Spot/Terms</b></font></td><td>&nbsp;"+spot_terms+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Requester Name</b></font></td><td>&nbsp;"+session.getAttribute("username")+"</td></tr>";
									MailBody+="</table>";
									getmail_list(req);
									postMailReport(mail_list, "Pre-Deal Credit Request submitted for Counterparty: "+Cust_Nm, MailBody,from_mail, "", "", "");
								}
								else
								{
									System.out.println("Pre-Deal Credit Check :: Mail Recipient List Not Found!! ");
								}
							}
							//System.out.println(MailBody);
						}
					}
				}
				else
				{
					err_msg="Data Not Found for Modification!!";
				}
			}
			else
			{
				//generate seq_no as per customer_cd
				String seq_no="";
				queryString = "select max(nvl(SEQ_NO,1)+1) from FMS9_DEAL_REQUEST_DTL where CUSTOMER_ABBR='"+customer_abbr+"'";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					seq_no=rset.getString(1)==null?"1":rset.getString(1);
				}
				//generate request_id
				String request_id="";
				queryString = "select max(nvl(REQUEST_ID,1)+1) from FMS9_DEAL_REQUEST_DTL";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					request_id=rset.getString(1)==null?"1":rset.getString(1);
				}
				String FMS9_DEAL_REQUEST_DTL="insert into FMS9_DEAL_REQUEST_DTL(CUSTOMER_CD,CUSTOMER_ABBR,SEQ_NO,VALUE,CURRENCY,START_DT,END_DT,BUY_SELL,DLV_TERMS,SPOT_TERMS,PAYMENT_TERMS,REQUEST_ID,REQUEST_BY,REQUEST_DT,REQUEST_MSG,STATUS,ENT_DT,ENT_BY,SHELL_CONT) "
						+ "values('"+Cust_Cd+"','"+customer_abbr+"','"+seq_no+"','"+value+"','"+currency+"',TO_DATE('"+deliverly_start_dt+"','DD/MM/YYYY'),TO_DATE('"+deliverly_end_dt+"','DD/MM/YYYY'),'"+sell_buy+"','"+dlv_terms+"','"+spot_terms+"','"+payment_terms+"','"+request_id+"','"+user_cd+"',sysdate,'"+comments+"','"+status+"',sysdate,'"+user_cd+"','"+shell_cont+"')";
				int insert = stmt.executeUpdate(FMS9_DEAL_REQUEST_DTL);
				msg="Data Successfully Submitted!!";
				if(insert == 1)
				{
					if(!mail_list.equalsIgnoreCase(""))//HARSH20210805
					{
						MailBody+="<table style='font-size: x-middle; font-family:Calibri;' border='1' cellpadding='0' cellspacing='0'>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Request ID</b></font></td><td>&nbsp;"+request_id+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Customer Name</b></font></td><td>&nbsp;"+Cust_Nm+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Deal Value</b></font></td><td>&nbsp;"+value+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Deal Currency</b></font></td><td>&nbsp;"+currency+"</td></tr>";
						//MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Trader Name</b></font></td><td></td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Request Message</b></font></td><td>&nbsp;"+comments+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Delivery Date</b></font></td><td>&nbsp;"+deliverly_start_dt+" to "+deliverly_end_dt+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Shell Contracting Co.</b></font></td><td>&nbsp;"+shell_cont+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Buy/Sell</b></font></td><td>&nbsp;"+sell_buy+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Payment Terms</b></font></td><td>&nbsp;"+payment_terms+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Delivery Terms</b></font></td><td>&nbsp;"+dlv_terms+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Spot/Terms</b></font></td><td>&nbsp;"+spot_terms+"</td></tr>";
						MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Requester Name</b></font></td><td>&nbsp;"+session.getAttribute("username")+"</td></tr>";
						MailBody+="</table>";
						getmail_list(req);
						postMailReport(mail_list, "Pre-Deal Credit Request submitted for Counterparty: "+Cust_Nm, MailBody,from_mail, "", "", "");
					}
					else
					{
						System.out.println("Pre-Deal Credit Check :: Mail Recipient List Not Found!! ");
					}
				}
				//System.out.println("Insert--"+MailBody);
			}
			//msg=msg.replaceAll(" ", "");
			//err_msg=err_msg.replaceAll(" ", "");
			url="../mrcr/frm_CR_PreDeal_CreditCheck.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			try
			{
				//HARSH20210112 new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
				InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,old_value,new_value,msg); //HARSH20210112
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			dbcon.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_CR_PreDeal_CreditCheck.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	//HARSH20201224/////////////PRE DEAL CREDIT APPROVAL//////////////
	private void InsertPreDealCreditApproval(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException
	{
		String err_msg=""; msg=""; String MailBody="";//HARSH20210127
		try
		{
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			//HARSH20210112 String customer_cd = req.getParameter("hidden_cust_cd")==null?"":req.getParameter("hidden_cust_cd");
			String customer_abbr = req.getParameter("hidden_cust_abbr")==null?"":req.getParameter("hidden_cust_abbr"); //HARSH20210112
			String seqNo = req.getParameter("seq_no")==null?"":req.getParameter("seq_no");
			String requestId = req.getParameter("request_id")==null?"":req.getParameter("request_id");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String approve_msg = req.getParameter("approval_msg")==null?"":req.getParameter("approval_msg");
			
			String mail_list = req.getParameter("mail_list")==null?"":req.getParameter("mail_list");
			
			//////HARSH20210112///////For Audit Trail//////////
			String old_value = req.getParameter("old_value")==null?"":req.getParameter("old_value");
			String new_value="";
			new_value="CustABBR="+customer_abbr+"#Status="+status+"#AppMsg="+approve_msg;
					//////////////////////////////////////////////////
					
			int count=0;
			if(!customer_abbr.equals("") && !seqNo.equals("") && !requestId.equals(""))
			{
				query="select nvl(count(*),0) from FMS9_DEAL_REQUEST_DTL where customer_abbr='"+customer_abbr+"' and seq_no='"+seqNo+"' and request_id='"+requestId+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getInt(1);
					if(count>0)
					{
						String UPDATE_FMS9_DEAL_REQUEST_DTL = "UPDATE FMS9_DEAL_REQUEST_DTL SET APRV_DT=SYSDATE, APRV_BY='"+user_cd+"', APRV_MSG='"+approve_msg+"', STATUS='"+status+"',MODIFY_BY='"+user_cd+"',MODIFY_DT=sysdate WHERE CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO='"+seqNo+"' AND REQUEST_ID='"+requestId+"'";
						int update = stmt.executeUpdate(UPDATE_FMS9_DEAL_REQUEST_DTL);
						if(update == 1)//HARSH20210127
						{
							if(!mail_list.equalsIgnoreCase(""))//HARSH20210805
							{
								query="select customer_abbr,value,currency,to_char(start_dt,'dd/mm/yyyy'),to_char(end_dt,'dd/mm/yyyy'),buy_sell,dlv_terms,spot_terms,shell_cont,payment_terms,request_by,request_msg,to_char(aprv_dt,'dd/mm/yyyy HH12:MI:SS AM') from FMS9_DEAL_REQUEST_DTL WHERE CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO='"+seqNo+"' AND REQUEST_ID='"+requestId+"'";
								rset=stmt.executeQuery(query);
								if(rset.next()){
									String custabbr = rset.getString(1)==null?"":rset.getString(1);
									String requestBy = rset.getString(11)==null?"":rset.getString(11);
									String cust_nm="",requester_nm="";
									
									query1="select CUSTOMER_NAME from FMS7_CUSTOMER_MST where CUSTOMER_ABBR='"+custabbr+"'";
									rset1=stmt1.executeQuery(query1);
									if(rset1.next())
									{
										cust_nm=rset1.getString(1)==null?"":rset1.getString(1);
									}else{
										query1 ="select TRADER_NAME from FMS7_TRADER_MST where TRADER_ABBR='"+custabbr+"'";
										rset1=stmt1.executeQuery(query1);
										if(rset1.next())
										{
											cust_nm=rset1.getString(1)==null?"":rset1.getString(1);
										}
									}
									//HARSH20210130	
									query1="select emp_nm from hr_emp_mst where emp_cd='"+requestBy+"'";
									rset1=stmt1.executeQuery(query1);
									if(rset1.next())
									{
										requester_nm=rset1.getString(1)==null?"":rset1.getString(1);
									}
									
									MailBody+="<table style='font-size: x-middle; font-family:Calibri;' border='1' cellpadding='0' cellspacing='0'>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Request ID</b></font></td><td>&nbsp;"+requestId+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Customer Name</b></font></td><td>&nbsp;"+cust_nm+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Deal Value</b></font></td><td>&nbsp;"+rset.getString(2)+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Deal Currency</b></font></td><td>&nbsp;"+rset.getString(3)+"</td></tr>";
									//MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Trader Name</b></font></td><td></td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Request Message</b></font></td><td>&nbsp;"+rset.getString(12)+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Delivery Date</b></font></td><td>&nbsp;"+rset.getString(4)+" To "+rset.getString(5)+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Shell Contracting Co.</b></font></td><td>&nbsp;"+rset.getString(9)+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Buy/Sell</b></font></td><td>&nbsp;"+rset.getString(6)+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Payment Terms</b></font></td><td>&nbsp;"+rset.getString(10)+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Delivery Terms</b></font></td><td>&nbsp;"+rset.getString(7)+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Spot/Terms</b></font></td><td>&nbsp;"+rset.getString(8)+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Requester Name</b></font></td><td>&nbsp;"+requester_nm+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Approver Name</b></font></td><td>&nbsp;"+session.getAttribute("username")+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Approval Status</b></font></td><td>&nbsp;"+status+"</td></tr>";
									MailBody+="<tr><td style='background-color:#ffd966'><font size='2' color='black'><b>Approval/Rejection Message</b></font></td><td>&nbsp;"+approve_msg+"</td></tr>";
									MailBody+="</table>";
//									System.out.println(MailBody);
									
									getmail_list(req);
									postMailReport(mail_list, "Pre-Deal Credit Request "+status+" for the Request Id:"+requestId+" with Counterparty: "+cust_nm, MailBody,from_mail, "", "", "");
								}
								else
								{
									System.out.println("Pre-Deal Credit Approval :: Mail Recipient List Not Found!! ");
								}
							}
						}
						msg="Data Submitted Successfuly";
					}
					else
					{
						err_msg="Data Not Found!!";
					}
				}
			}
			else
			{
				err_msg="Data Not Found!!";
			}
		//	msg=msg.replaceAll(" ", "");
		//	err_msg=err_msg.replaceAll(" ", "");
			url="../mrcr/frm_CR_PreDeal_Credit_Approval.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			try
			{
				//HARSH20210112 new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
				InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,old_value,new_value,msg); //HARSH20210112
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			dbcon.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			err_msg="Data Not Submitted!!!";
			url="../mrcr/frm_CR_PreDeal_Credit_Approval.jsp?msg="+msg+"&err_msg="+err_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	public void getmail_list(HttpServletRequest req) {
		try
		{
			String strline = "";
			
			File fsetup=new File(req.getRealPath("/mrcr/Setup.txt"));
			String mail_list_path=fsetup.getAbsolutePath();
			//mail_list_path = request.getRealPath("/amc/SETUP.txt");
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
				if(strline.startsWith("to"))
				{
					String  tmp[]=strline.split("to:");
					to_mail = tmp[1].toString();
				}
				if(strline.startsWith("pwd"))
				{
					String  tmp[]=strline.split("pwd:");
					encrypted = tmp[1].toString();
                   //CipherEncrypter encrypter = new CipherEncrypter("CompEncryptedDataSourceFactory");
                  // from_pwd = encrypter.decrypt(encrypted);
					from_pwd = encrypted;

//					from_pwd = tmp[1].toString();
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
                     //CipherEncrypter encrypter = new CipherEncrypter("CompEncryptedDataSourceFactory");
                     //password = encrypter.decrypt(encrypted);
					 password = encrypted;

//					password = tmp[1].toString();
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("E.getmassage..."+e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean postMailReport(String recipientsTo, String subject, String message, String from,  String fileName,String recipientsCC,String recipientsBCC)
			throws MessagingException ,AuthenticationFailedException {
		try 
		{
			Properties props = new Properties();
			//Properties props = System.getProperties();
			props.put("mail.smtp.host",host);
		//	props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		//	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		   	props.put("mail.smtp.auth","true");
			props.put("mail.smtp.port", "25");
			
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from_mail, from_pwd);
				}
			};
				//Session session = Session.getInstance(props,new SMTPAuthenticator());
				Session session = Session.getDefaultInstance(props, auth);
				//session.setDebug(true);
				MimeMessage msg = new MimeMessage(session);
				
//				System.out.println("session : "+session);
//				System.out.println("msg : "+msg);

				InternetAddress addressFrom = new InternetAddress(from);			// set the from and to address 
				msg.setFrom(addressFrom);

							
//				System.out.println("addressFrom : "+from);
//				System.out.println("addressTo : "+recipientsTo);
//				System.out.println("addressCC : "+Arrays.toString(addressCC));
//				System.out.println("message : "+message);
				
				
				msg.setRecipients(Message.RecipientType.TO, recipientsTo);
				//msg.setRecipients(Message.RecipientType.CC, addressCC);
				//msg.setRecipients(Message.RecipientType.BCC, addressBCC);
				msg.setSubject(subject);
	 
				Multipart multipart = new MimeMultipart();   // Setting the   Subject and Content Type 
				BodyPart textPart = new MimeBodyPart();      // Add the Subject Line to the Mail.
				
				textPart.setContent(message, "text/html");
				msg.saveChanges();
				multipart.addBodyPart(textPart);
				
				/*BodyPart imgPart = new MimeBodyPart();
				
				String filename = logoUrl+"/images/logo/company_Logo.png";
				javax.activation.DataSource source = new FileDataSource(filename);
				imgPart.setDataHandler(new DataHandler(source));
				imgPart.setFileName(filename);
				imgPart.setHeader("Content-ID", "<image>");
				multipart.addBodyPart(imgPart);*/
				
				msg.setContent(multipart);
				
				/*for(int i=0;i<fileName.length;i++)  	// Add the file attachment. Make sure this file exists.	// Attach the file to the message
				  {  
					mbp_file = new MimeBodyPart(); 
					FileDataSource fds = new FileDataSource(fileName[i]);
					mbp_file.setDataHandler(new DataHandler(fds));
					mbp_file.setFileName(fds.getName());
					multipart.addBodyPart(mbp_file);
				  }*/
				
				msg.setSentDate(new Date());
				msg.saveChanges();
				Transport.send(msg,msg.getAllRecipients());
			
			return true;
		}		
		catch (Exception ex) 
		{			
			ex.printStackTrace();
			return false;
		}
	}
	
	//HARSH20210616 NEW FUNCTION FOR DELETING OR REOPEN THE SCEURITY AS REQUESTED BY JENNIE ON 16/06/2021
	private void deleteReopenSecurity(HttpServletRequest req, HttpServletResponse res)throws IOException,SQLException
	{
		String customer_abbr="";String delete_ref_no="";String reopen_ref_no="";String newValue="";String oldValue="";String log_msg="";
		try
		{
			HttpSession session = req.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			customer_abbr = req.getParameter("customer_abbr")==null?"":req.getParameter("customer_abbr");
			delete_ref_no = req.getParameter("delete_ref_no")==null?"":req.getParameter("delete_ref_no");
			reopen_ref_no = req.getParameter("reopen_ref_no")==null?"":req.getParameter("reopen_ref_no");
			String operation = req.getParameter("operation")==null?"":req.getParameter("operation");
			String flag_del_re = req.getParameter("flag_del_re")==null?"":req.getParameter("flag_del_re");
			
			String link = req.getParameter("cont_deal_id")==null?"":req.getParameter("cont_deal_id");
			if(link.equals("") || link == "")
			{
				link = req.getParameter("hid_cont_deal_id")==null?"":req.getParameter("hid_cont_deal_id");
			}
			
			if(operation.equalsIgnoreCase("DELETE_SECURITY"));
			{
				//System.out.println(customer_abbr);
				//System.out.println(delete_ref_no);
				//System.out.println(reopen_ref_no);
				
				int delete_count=0,reopen_count=0;
				query = "SELECT COUNT(*) FROM FMS9_SECURITY_POST_MST WHERE REF_NO='"+delete_ref_no+"' AND CUSTOMER_ABBR='"+customer_abbr+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					delete_count=rset.getInt(1);
				}
				
				query = "SELECT COUNT(*) FROM FMS9_SECURITY_POST_MST WHERE REF_NO='"+reopen_ref_no+"' AND CUSTOMER_ABBR='"+customer_abbr+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					reopen_count=rset.getInt(1);
				}
//				System.out.println("flag_del_re "+flag_del_re);
				if(flag_del_re.equals("DR"))
				{
					if(delete_count >= 1 && reopen_count == 1)
					{
						String status="";
						if(delete_count >= 1)
						{
							query = "DELETE FMS9_SECURITY_POST_MST WHERE REF_NO = '"+delete_ref_no+"'";
							stmt.executeUpdate(query);
							
							query = "DELETE LOG_FMS9_SECURITY_POST_MST WHERE REF_NO = '"+delete_ref_no+"'";
							stmt.executeUpdate(query);
						
							newValue="CustABBR="+customer_abbr+"#SecRefNo="+delete_ref_no+"#DealNo="+link+"#Rmk=The security("+delete_ref_no+") is Deleted";
							oldValue="";
							log_msg="The security("+delete_ref_no+") is Deleted";
							try
							{
								InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,oldValue,newValue,log_msg); //HARSH20210110
							}
							catch(Exception infoLogger)
							{
								System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
								infoLogger.printStackTrace();
							}
						}
						
						String mst_seq_no="";
						query="SELECT SEQ_NO FROM FMS9_SECURITY_POST_MST WHERE REF_NO='"+reopen_ref_no+"' AND CUSTOMER_ABBR='"+customer_abbr+"'";
						rset=stmt.executeQuery(query);
						if(rset.next())
						{
							mst_seq_no = rset.getString(1)==null?"":rset.getString(1);
						}
						if(!mst_seq_no.equals(""))
						{
							query="SELECT TO_CHAR(ENT_DT,'DD/MM/YYYY HH24:MI:SS'),ENT_CD,"
								+ "TO_CHAR(MODIFY_DT,'DD/MM/YYYY HH24:MI:SS'),MODIFY_BY,"
								+ "TO_CHAR(APRV_DT,'DD/MM/YYYY HH24:MI:SS'),APRV_BY,"
								+ "FLAG,TO_CHAR(CANCEL_DT,'DD/MM/YYYY HH24:MI:SS'),INORDER_HIST,"
								+ "SEC_TYPE,DEAL_TYPE,CURRENCY,VARIATION_VALUE,VALUE,VALUE_FLUC,"
								+ "ISS_BANK_CD,ISS_BANK_REF,ADV_BANK_CD,ADV_BANK_REF,CONFIRM_BANK_CD,"
								+ "CONFIRM_BANK_REF,TO_CHAR(REC_DT,'DD/MM/YYYY'),TO_CHAR(ISSU_DT,'DD/MM/YYYY'),"
								+ "TO_CHAR(EXP_DT,'DD/MM/YYYY'),TO_CHAR(RENEW_DT,'DD/MM/YYYY'),TENOR,STATUS,REMARKS,"
								+ "ISSUED,RECEIVED,TO_CHAR(REVIEW_DT,'DD/MM/YYYY') "
								+ "FROM LOG_FMS9_SECURITY_POST_MST "
								+ "WHERE REF_NO='"+reopen_ref_no+"' AND CUSTOMER_ABBR='"+customer_abbr+"' "
								+ "AND SEQ_NO='"+mst_seq_no+"' AND DTL_SEQ_NO='1'";
							rset=stmt.executeQuery(query);
							if(rset.next())
							{
								String ent_dt = rset.getString(1)==null?"":rset.getString(1);
								String ent_cd = rset.getString(2)==null?"":rset.getString(2);
								String modify_dt = rset.getString(3)==null?"":rset.getString(3);
								String modify_by = rset.getString(4)==null?"":rset.getString(4);
								String aprv_dt = rset.getString(5)==null?"":rset.getString(5);
								String aprv_by = rset.getString(6)==null?"":rset.getString(6);
								String flag = rset.getString(7)==null?"":rset.getString(7);
								String cancel_dt = rset.getString(8)==null?"":rset.getString(8);
								String inorder_hist = rset.getString(9)==null?"":rset.getString(9);
								String sec_type = rset.getString(10)==null?"":rset.getString(10);
								String deal_type = rset.getString(11)==null?"":rset.getString(11);
								String currency = rset.getString(12)==null?"":rset.getString(12);
								String variation_value = rset.getString(13)==null?"":rset.getString(13);
								String value = rset.getString(14)==null?"":rset.getString(14);
								String value_fluc = rset.getString(15)==null?"":rset.getString(15);
								String iss_bank_cd = rset.getString(16)==null?"":rset.getString(16);
								String iss_bank_ref = rset.getString(17)==null?"":rset.getString(17);
								String adv_bank_cd = rset.getString(18)==null?"":rset.getString(18);
								String adv_bank_ref = rset.getString(19)==null?"":rset.getString(19);
								String confirm_bank_cd = rset.getString(20)==null?"":rset.getString(20);
								String confirm_bank_ref = rset.getString(21)==null?"":rset.getString(21);
								String rec_dt = rset.getString(22)==null?"":rset.getString(22);
								String issu_dt = rset.getString(23)==null?"":rset.getString(23);
								String exp_dt = rset.getString(24)==null?"":rset.getString(24);
								String renew_dt = rset.getString(25)==null?"":rset.getString(25);
								String tenor = rset.getString(26)==null?"":rset.getString(26);
								status = rset.getString(27)==null?"":rset.getString(27);
								String remarks = rset.getString(28)==null?"":rset.getString(28);
								remarks=remarks.replaceAll("'", "''");
								String issued = rset.getString(29)==null?"":rset.getString(29);
								String received = rset.getString(30)==null?"":rset.getString(30);
								String review_dt = rset.getString(31)==null?"":rset.getString(31);
								
								
								query1 = "UPDATE FMS9_SECURITY_POST_MST SET MODIFY_DT=TO_DATE('"+modify_dt+"','DD/MM/YYYY HH24:MI:SS'),MODIFY_BY='"+modify_by+"',"
									+ "APRV_DT=TO_DATE('"+aprv_dt+"','DD/MM/YYYY HH24:MI:SS'),APRV_BY='"+aprv_by+"',FLAG='"+flag+"',"
									+ "CANCEL_DT=TO_DATE('"+cancel_dt+"','DD/MM/YYYY HH24:MI:SS'),STATUS='"+status+"',"
									+ "ENT_DT=TO_DATE('"+ent_dt+"','DD/MM/YYYY HH24:MI:SS'),ENT_CD='"+ent_cd+"',INORDER_HIST='"+inorder_hist+"',"
									+ "SEC_TYPE='"+sec_type+"',DEAL_TYPE='"+deal_type+"',CURRENCY='"+currency+"',VARIATION_VALUE='"+variation_value+"',"
									+ "VALUE='"+value+"',VALUE_FLUC='"+value_fluc+"',ISS_BANK_CD='"+iss_bank_cd+"',ISS_BANK_REF='"+iss_bank_ref+"',"
									+ "ADV_BANK_CD='"+adv_bank_cd+"',ADV_BANK_REF='"+adv_bank_ref+"',CONFIRM_BANK_CD='"+confirm_bank_cd+"',CONFIRM_BANK_REF='"+confirm_bank_ref+"',"
									+ "REC_DT=TO_DATE('"+rec_dt+"','DD/MM/YYYY'),ISSU_DT=TO_DATE('"+issu_dt+"','DD/MM/YYYY'),EXP_DT=TO_DATE('"+exp_dt+"','DD/MM/YYYY'),"
									+ "RENEW_DT=TO_DATE('"+renew_dt+"','DD/MM/YYYY'),TENOR='"+tenor+"',REMARKS='"+remarks+"',ISSUED='"+issued+"',"
									+ "RECEIVED='"+received+"',REVIEW_DT=TO_DATE('"+review_dt+"','DD/MM/YYYY') "
									+ "WHERE REF_NO = '"+reopen_ref_no+"' AND CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO ='"+mst_seq_no+"'";
								stmt1.executeUpdate(query1);
								
								query1 = "DELETE LOG_FMS9_SECURITY_POST_MST WHERE REF_NO = '"+reopen_ref_no+"' AND CUSTOMER_ABBR='"+customer_abbr+"' AND SEQ_NO='"+mst_seq_no+"' AND DTL_SEQ_NO !='1'";
								stmt1.executeUpdate(query1);
								
								newValue="CustABBR="+customer_abbr+"#SecRefNo="+reopen_ref_no+"#DealNo="+link+"#Rmk=The security("+reopen_ref_no+") is Reopen from 1st "+status;
								oldValue="";
								log_msg="The security("+reopen_ref_no+") is Reopen from 1st "+status;
								try
								{
									InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,oldValue,newValue,log_msg); //HARSH20210110
								}
								catch(Exception infoLogger)
								{
									System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
									infoLogger.printStackTrace();
								}
							}
						}
						dbcon.commit();
						msg="The Security("+delete_ref_no+") is deleted Sucessfully and The Security("+reopen_ref_no+") is Reopen from 1st "+status+"!!";
					}
					else
					{
						if(delete_count == 0)
						{
							msg="The Security("+delete_ref_no+") not found for Delete And ";
						}
						
						if(delete_count >= 1)
						{
							msg="The Security("+delete_ref_no+") Data found for Delete but ";
						}
						
						if(reopen_count > 1)
						{
							msg+="The Security("+reopen_ref_no+") can not be Reopen because having a Link Security";
						}
						
						if(reopen_count == 0)
						{
							msg+="The Security("+reopen_ref_no+") not found for Delete";
						}
						//msg="The Security("+delete_ref_no+") not found for Delete and The Security("+reopen_ref_no+") not found for Reopen!!";
					}
				}
				else if(flag_del_re.equals("D"))
				{
					if(delete_count >= 1 && reopen_count == 0)
					{
						query = "DELETE FMS9_SECURITY_POST_MST WHERE REF_NO = '"+delete_ref_no+"'";
						stmt.executeUpdate(query);
						
						query = "DELETE LOG_FMS9_SECURITY_POST_MST WHERE REF_NO = '"+delete_ref_no+"'";
						stmt.executeUpdate(query);
					
						newValue="CustABBR="+customer_abbr+"#SecRefNo="+delete_ref_no+"#DealNo="+link+"#Rmk=The security("+delete_ref_no+") is Deleted";
						oldValue="";
						log_msg="The security("+delete_ref_no+") is Deleted";
						try
						{
							InsertInfoLogger(user_cd,(String)session.getAttribute("username"),(String)session.getAttribute("ip"),form_id,form_nm,oldValue,newValue,log_msg); //HARSH20210110
						}
						catch(Exception infoLogger)
						{
							System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
							infoLogger.printStackTrace();
						}
					}
					
					dbcon.commit();
					msg="The Security related to ("+delete_ref_no+") has been deleted Sucessfully!!";
				}
			}
			//msg=msg.replaceAll(" ", "");
			url="../mrcr/frm_CR_Post_Deal_Capturing.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			msg="The Security("+delete_ref_no+") not found for Delete and The Security("+reopen_ref_no+") not found for Reopen!!";
			url="../mrcr/frm_CR_Post_Deal_Capturing.jsp?msg="+msg+"&customer_abbr="+customer_abbr+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	/////Info Logger. WriteLog for Audit trail/////HARSH20210105////////////////////
	private void InsertInfoLogger(String user_cd,String username,String ip,String form_id,String form_nm,String old_value,String new_value,String msg)
	{
		try
		{
			////////////HARSH20210106//////////////////////////////////
	    	int count1=0;
			String s1="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'SEC_LOG_DETAILS' "+
			" AND UPPER(COLUMN_NAME) LIKE 'NEW_VALUE'";
			rset=stmt.executeQuery(s1);
			if(rset.next()){
				count1=rset.getInt(1);
			}
			if(count1==0){
				s1="ALTER TABLE SEC_LOG_DETAILS ADD NEW_VALUE VARCHAR2(1024 BYTE)";
				stmt.executeUpdate(s1);
				dbcon.commit();
			}
			int count2=0;
			String s2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'SEC_LOG_DETAILS' "+
			" AND UPPER(COLUMN_NAME) LIKE 'OLD_VALUE'";
			rset=stmt.executeQuery(s2);
			if(rset.next()){
				count2=rset.getInt(1);
			}
			if(count2==0){
				s2="ALTER TABLE SEC_LOG_DETAILS ADD OLD_VALUE VARCHAR2(1024 BYTE)";
				stmt.executeUpdate(s2);
				dbcon.commit();
			}
			///////////////////////////////////////////////////////////////
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
	        String tsString = ts.toString().substring(0, 19);
	        String tsDate = tsString.substring(0, 10);
	        String time = tsString.substring(11, 19);
	        
	        queryString = "insert into SEC_LOG_DETAILS(LOG_DT, LOG_TIME, LOG_UID, LOG_MACH_ID, REMARKS, EMP_CD, FORM_CD, FORM_NAME,OLD_VALUE, NEW_VALUE) " +
							  "values(to_date('"+tsDate+"','yyyy-mm-dd'), '"+time+"', '"+username+"', '"+ip+"', '"+msg+"', '"+user_cd+"', '"+form_id+"', '"+form_nm+"','"+old_value+"','"+new_value+"')";
//				System.out.println("INFO-QRY0002 : SELECT : SEC_LOG_DETAILS : InfoLogger.java : "+queryString);
	       stmt.executeUpdate(queryString);
	        
	       dbcon.commit();
	        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
