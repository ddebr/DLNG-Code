package com.seipl.hazira.dlng.master;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

@WebServlet("/servlet/Frm_Email_Setup")
public class Frm_Email_Setup extends HttpServlet {

	public String servletName = "Frm_admin";
	public String methodName = "";
	
	
	
	public static Connection dbcon;
	public escapeSingleQuotes obj = new escapeSingleQuotes();
    public String url = "";
    private static String query = null;
    private static String query1 = null;
    private static Statement stmt = null;
    private static Statement stmt1 = null;
    private ResultSet rset = null;
    String modCd = "";
    String formId = "";
    String subModUrl = "";
    String option = "";
    
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
				System.out.println("Data Source Not Found - Invoice Module Error");
			}
			if(dbcon != null)
			{
				dbcon.setAutoCommit(false);
				stmt = dbcon.createStatement(); 
			}
			modCd = req.getParameter("modCd")==null?"":req.getParameter("modCd");
			formId = req.getParameter("formId")==null?"":req.getParameter("formId");
			subModUrl = req.getParameter("subModUrl")==null?"":req.getParameter("subModUrl");
			option=req.getParameter("option")==null?"":req.getParameter("option");
			
			System.out.println("---------"+option+"-----------");
			if(option.equalsIgnoreCase("insertEmailSetup")) {
				
				String radFlg=req.getParameter("radFlg")==null?"":req.getParameter("radFlg");
				if(radFlg.equalsIgnoreCase("cust")) {
					url = insertCustEmailSetup(req,res);
				}else if(radFlg.equalsIgnoreCase("sup")) {
					url = insertEmailSetup(req,res);
				}else if(radFlg.equalsIgnoreCase("trans")) {
					url = insertTransEmailSetup(req,res);
				}
			}
		}		   
		catch(Exception e)
		{
			e.printStackTrace();
			url="../home/ExceptionHandler.jsp?excp="+e.getMessage();
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
//		System.out.println("url-----"+url);
		res.sendRedirect(url);
	}

	private String insertTransEmailSetup(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		
			String msg ="";
			String err_msg ="";
			String com_msg="";
			HttpSession session = req.getSession();
			
			String Vseq_no [] = req.getParameterValues("Vseq_no"); 
			String toFlg [] = req.getParameterValues("toFlg"); 
			String ccFlg [] = req.getParameterValues("ccFlg");
			
			String trans_cd = req.getParameter("trans_cd")==null?"":req.getParameter("trans_cd");
			String addrs = req.getParameter("addrs")==null?"":req.getParameter("addrs");
			String radFlg = req.getParameter("radFlg")==null?"":req.getParameter("radFlg");
			
			try {
				int cnt = 0;
				for(int i = 0 ; i < Vseq_no.length; i ++) {
//					System.out.println("toFlg[i]--------"+toFlg[i]);
					if(toFlg[i].equalsIgnoreCase("Y")) {
						int avil_cnt= 0;
						String cust_cnt = "select count(*) from DLNG_TRANSPORTER_CONTACT_MST where "
								+ " TRANS_CD = '"+trans_cd+"' "
								+ " and DEF_NOM_TO_FLAG = 'Y' "
								+ " and ADDR_FLAG='"+addrs+"' "
								+ " and active_flag = 'Y' ";
//						System.out.println("cust_cnt---"+cust_cnt);
						rset = stmt.executeQuery(cust_cnt);
						if(rset.next()) {
							avil_cnt = rset.getInt(1);
						}
						if(avil_cnt > 0) {
							
							String update_sql1 = "update DLNG_TRANSPORTER_CONTACT_MST set DEF_NOM_TO_FLAG = '' "
									+ " where TRANS_CD = '"+trans_cd+"' and ADDR_FLAG='"+addrs+"' "
									+ " and active_flag = 'Y' ";
//							System.out.println("update_sql1----"+update_sql1);
							stmt.execute(update_sql1);
						}
						String update_sql = "update DLNG_TRANSPORTER_CONTACT_MST set DEF_NOM_TO_FLAG = 'Y' "
								+ " where TRANS_CD = '"+trans_cd+"' and ADDR_FLAG='"+addrs+"' and SEQ_NO = '"+Vseq_no[i]+"'"
								+ " and active_flag = 'Y' ";
//						System.out.println("update_sql----"+update_sql);
						stmt.execute(update_sql);
						cnt++;
					}
				}
//				System.out.println("cnt---"+cnt);
				if(cnt > 0) {
					dbcon.commit();
					msg = "Successfully Configured!!";
					com_msg = msg;
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				dbcon.rollback();
				err_msg = "Email Configuration Failed !! ";
				com_msg = err_msg;
			}
			url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+err_msg+"&modCd="+modCd+"&subModUrl="+subModUrl+"&formId="+formId+"&radFlg="+radFlg+"&trans_cd="+trans_cd+"&addrs="+addrs;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@frm_email_setup.jsp~: "+com_msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
			return url;
	}

	private String insertCustEmailSetup(HttpServletRequest req, HttpServletResponse res)throws SQLException,IOException {
		
		String msg ="";
		String err_msg ="";
		String com_msg="";
		HttpSession session = req.getSession();
		
		String Vseq_no [] = req.getParameterValues("Vseq_no"); 
		String toFlg [] = req.getParameterValues("toFlg"); 
		String ccFlg [] = req.getParameterValues("ccFlg");
		
		String cust_cd = req.getParameter("cust_cd")==null?"":req.getParameter("cust_cd");
		String plant_cd = req.getParameter("plant_cd")==null?"":req.getParameter("plant_cd");
		String radFlg = req.getParameter("radFlg")==null?"":req.getParameter("radFlg");
		
		try {
			int cnt = 0;
			for(int i = 0 ; i < Vseq_no.length; i ++) {
//				System.out.println("toFlg[i]--------"+toFlg[i]);
				if(toFlg[i].equalsIgnoreCase("Y")) {
					int avil_cnt= 0;
					String cust_cnt = "select count(*) from FMS7_CUSTOMER_CONTACT_MST where "
							+ " CUSTOMER_CD = '"+cust_cd+"' "
							+ " and DEF_INV_TO_FLAG = 'Y' "
							+ " and ADDR_FLAG='"+plant_cd+"' "
							+ " and active_flag = 'Y' ";
//					System.out.println("cust_cnt---"+cust_cnt);
					rset = stmt.executeQuery(cust_cnt);
					if(rset.next()) {
						avil_cnt = rset.getInt(1);
					}
					if(avil_cnt > 0) {
						
						String update_sql1 = "update FMS7_CUSTOMER_CONTACT_MST set DEF_INV_TO_FLAG = '' "
								+ " where CUSTOMER_CD = '"+cust_cd+"' and ADDR_FLAG='"+plant_cd+"' "
								+ " and active_flag = 'Y' ";
//						System.out.println("update_sql1----"+update_sql1);
						stmt.execute(update_sql1);
					}
					String update_sql = "update FMS7_CUSTOMER_CONTACT_MST set DEF_INV_TO_FLAG = 'Y' "
							+ " where CUSTOMER_CD = '"+cust_cd+"' and ADDR_FLAG='"+plant_cd+"' and SEQ_NO = '"+Vseq_no[i]+"'"
							+ " and active_flag = 'Y' ";
					System.out.println("update_sql----"+update_sql);
					stmt.execute(update_sql);
					cnt++;
				}
			}
//			System.out.println("cnt---"+cnt);
			if(cnt > 0) {
				dbcon.commit();
				msg = "Successfully Configured!!";
				com_msg = msg;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			dbcon.rollback();
			err_msg = "Email Configuration Failed !! ";
			com_msg = err_msg;
		}
		url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+err_msg+"&modCd="+modCd+"&subModUrl="+subModUrl+"&formId="+formId+"&radFlg="+radFlg+"&cust_cd="+cust_cd+"&plant_cd="+plant_cd;
		
		try
		{
			new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@frm_email_setup.jsp~: "+com_msg);
		}
		catch(Exception infoLogger)
		{
			//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
			infoLogger.printStackTrace();
		}
		return url;
	}

	private String insertEmailSetup(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		
		String msg ="";
		String err_msg ="";
		String com_msg="";
		HttpSession session = req.getSession();
			
			String user_cd = req.getParameter("user_cd")==null?"":req.getParameter("user_cd");
			String emp_cd = req.getParameter("emp_cd")==null?"":req.getParameter("emp_cd");
			
			String o_inv_cc = req.getParameter("hid_o_inv_cc")==null?"N":req.getParameter("hid_o_inv_cc");
			String o_inv_bcc = req.getParameter("hid_o_inv_bcc")==null?"N":req.getParameter("hid_o_inv_bcc");
			String form_to = req.getParameter("hid_form_to")==null?"N":req.getParameter("hid_form_to");
			String form_cc = req.getParameter("hid_form_cc")==null?"N":req.getParameter("hid_form_cc");
			String form_bcc = req.getParameter("hid_form_bcc")==null?"N":req.getParameter("hid_form_bcc");
			
			String d_inv_cc = req.getParameter("hid_d_inv_cc")==null?"N":req.getParameter("hid_d_inv_cc");
			String d_inv_bcc = req.getParameter("hid_d_inv_bcc")==null?"N":req.getParameter("hid_d_inv_bcc");
//			String d_inv_to = req.getParameter("hid_d_inv_to")==null?"N":req.getParameter("hid_d_inv_to");
			
			String t_inv_cc = req.getParameter("hid_t_inv_cc")==null?"N":req.getParameter("hid_t_inv_cc");
			String t_inv_bcc = req.getParameter("hid_t_inv_bcc")==null?"N":req.getParameter("hid_t_inv_bcc");
			String t_inv_to = req.getParameter("hid_t_inv_to")==null?"N":req.getParameter("hid_t_inv_to");
			
			String avail_flg = req.getParameter("avail_flg")==null?"N":req.getParameter("avail_flg");
			
		try {	
			if(!emp_cd.equalsIgnoreCase("")) {
				
				int availRec = 0 ;
				String chkrecExist = "select count(*) from DLNG_MAIL_SETUP_MST where SEQ_NO = '"+emp_cd+"' and COMMODITY_TYPE = 'DLNG'";
				rset = stmt.executeQuery(chkrecExist);
				if(rset.next()) {
					availRec = rset.getInt(1); 
				}
						
				if(availRec == 0) {
					
					String insQuery = "insert into DLNG_MAIL_SETUP_MST (COMMODITY_TYPE,FORM402_BCC,FORM402_CC,FORM402_TO,"
							+ " D_INV_BCC,D_INV_CC,EFF_DT,ENT_BY,ENT_DT,O_INV_BCC,O_INV_CC,"
							+ " T_INV_BCC,T_INV_CC,SEQ_NO,T_INV_TO) "
							+ " VALUES ('DLNG','"+form_bcc+"','"+form_cc+"','"+form_to+"','"+d_inv_bcc+"','"+d_inv_cc+"',"
							+ " sysdate,'"+user_cd+"',sysdate,'"+o_inv_bcc+"','"+o_inv_cc+"','"+t_inv_bcc+"','"+t_inv_cc+"','"+emp_cd+"','"+t_inv_to+"' )";
					
//					System.out.println("insQuery----"+insQuery);
					stmt.execute(insQuery);
					msg = "Successfully Configured!!";
					com_msg = msg;
					
				}else {
					
					String updQuery = "update DLNG_MAIL_SETUP_MST set FORM402_BCC ='"+form_bcc+"',FORM402_CC='"+form_cc+"',FORM402_TO='"+form_to+"',"
							+ " D_INV_BCC='"+d_inv_bcc+"',D_INV_CC='"+d_inv_cc+"',EFF_DT=sysdate,ENT_BY='"+user_cd+"',ENT_DT=sysdate,"
							+ " O_INV_BCC='"+o_inv_bcc+"',O_INV_CC='"+o_inv_cc+"',"
							+ " T_INV_BCC='"+t_inv_bcc+"',T_INV_CC='"+t_inv_cc+"',T_INV_TO='"+t_inv_to+"' "
							+ " where SEQ_NO='"+emp_cd+"' ";
//					System.out.println("updQuery----"+updQuery);
					stmt.execute(updQuery);
					msg = "Configuration Successfully Modified!!";
					com_msg = msg;
				}
				dbcon.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			dbcon.rollback();
			err_msg = "Email Configuration Failed !! ";
			com_msg = err_msg;
		}
		url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+err_msg+"&modCd="+modCd+"&subModUrl="+subModUrl+"&formId="+formId+"&emp_cd="+emp_cd;
		
		try
		{
			new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@frm_email_setup.jsp~: "+com_msg);
		}
		catch(Exception infoLogger)
		{
			//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
			infoLogger.printStackTrace();
		}
		
		return url;
	}
}