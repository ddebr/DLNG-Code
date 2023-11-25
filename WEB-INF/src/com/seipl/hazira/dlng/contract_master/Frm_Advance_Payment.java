package com.seipl.hazira.dlng.contract_master;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;

@WebServlet("/servlet/Frm_Advance_Payment")
public class Frm_Advance_Payment extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String servletName = "Frm_Advance_Payment";
	public String methodName = "";
	public String option = "";
	public String url = "";
	public String form_name = "";
	public String msg = "",err_msg = "";
	int count = 0;
	
	public static  Connection dbcon;
	
	private static String queryString = null;
	private static String query = null;
	private static String query1 = null;
	private static String query2 = null;
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
	public String approve_permission = "N";
	public String audit_permission = "N";
	
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
			
			write_permission = req.getParameter("write_permission")==null?"N":req.getParameter("write_permission");
			delete_permission = req.getParameter("delete_permission")==null?"N":req.getParameter("delete_permission");
			print_permission = req.getParameter("print_permission")==null?"N":req.getParameter("print_permission");
			approve_permission = req.getParameter("approve_permission")==null?"N":req.getParameter("approve_permission");
			audit_permission = req.getParameter("audit_permission")==null?"N":req.getParameter("audit_permission");
			
			option=req.getParameter("option")==null?"":req.getParameter("option");
			System.out.println("---------"+option+"-----------");
			if(option.equalsIgnoreCase("AdvancePaymentEntry")) {
				
				url = advancePaymentEntry(req,res);
			}else if(option.equalsIgnoreCase("ApproveAdvancePayment")) {
				
				url = approveAdvancePayment(req,res);
			}else if(option.equalsIgnoreCase("MultiAdvancePaymentEntry")) {
				url = multiAdvancePaymentEntry(req,res);
			}else if(option.equalsIgnoreCase("MultiApproveAdvancePayment")) {
				url = multiApproveAdvancePayment(req,res);
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
			if(ps != null)
			{
				try
				{
					ps.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
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
					e.printStackTrace();
				}
			}
		}
//		System.out.println("url-----"+url);
		res.sendRedirect(url);
	}

	private String multiApproveAdvancePayment(HttpServletRequest req, HttpServletResponse res)throws Exception {

		err_msg = "";
		msg="";
		
		String modCd = req.getParameter("modCd")==null?"":req.getParameter("modCd");
		String formId = req.getParameter("formId")==null?"":req.getParameter("formId");
		String subModUrl = req.getParameter("subModUrl")==null?"":req.getParameter("subModUrl");
		String modUrl = req.getParameter("modUrl")==null?"":req.getParameter("modUrl");
		String cont_typ = req.getParameter("cont_typ")==null?"":req.getParameter("cont_typ");
		String bscode = req.getParameter("cust_cd")==null?"0":req.getParameter("cust_cd");
		String selInd = req.getParameter("selInd")==null?"0":req.getParameter("selInd");
		
		String fgsa_revno[] = req.getParameterValues("fgsa_revno_mod");
		String sn_cd[] = req.getParameterValues("sn_cd_mod");
		String sn_revno[] = req.getParameterValues("sn_revno_mod");
		String fgsa_cd[] = req.getParameterValues("fgsa_cd_mod");
		String user_cd = req.getParameter("user_cd");
		double total_amt = 0,final_total=0;
		int cnt = 0;
		
		String[] seq_no = req.getParameterValues("mod_seq_no");
		String[] currency  = req.getParameterValues("mod_currency");
		String[] pay_typ  = req.getParameterValues("pay_typ_cd");
		String[] adv_amt  = req.getParameterValues("mod_adv_amt");
		String[] dr_cr_flag  = req.getParameterValues("dr_cr_flag");
		
		String temp_map="";
		String mapping_id="";
		String selCurrency = "";
		String payType="";
		String crdr = "";
		
		String contNo = "";
		if(cont_typ.equals("S")) {
			contNo = "SN";
		}else if(cont_typ.equals("L")) {
			contNo = "LoA";
		}
	try {
		
		for(int i = 0 ; i < seq_no.length ; i++) {
			if(i == Integer.parseInt(selInd+"")) {
				
				selCurrency = currency[i] ;
				temp_map = bscode+"-"+fgsa_cd[i]+"-%-"+sn_cd[i]+"-%-"+cont_typ;
				mapping_id=bscode+"-"+fgsa_cd[i]+"-"+fgsa_revno[i]+"-"+sn_cd[i]+"-"+sn_revno[i]+"-"+cont_typ;
				
				if(pay_typ[i].equals("AP")) {
					payType = "Advance Payment";
				}else if (pay_typ[i].equals("SP")) {
					payType = "Security Payment";
				}else if(pay_typ[i].equals("LP")) {
					payType = "Lump-sum Payment";
				}
				
				String updateSql = "update DLNG_ADVC_PAY_MST set "
						+ " APPROVED_BY='"+user_cd+"',APPROVED_DT=sysdate,APPROVED_FLAG='Y' "
						+ " where"
						+ " CUSTOMER_CD = '"+bscode+"' and FLSA_NO='"+fgsa_cd[i]+"'"
						+ " and  SN_NO = '"+sn_cd[i]+"' "
						+ " and  seq_no='"+seq_no[i]+"'"
						+ " and CONTRACT_TYPE = '"+cont_typ+"' and PAY_TYPE = '"+pay_typ[i]+"'";
//				System.out.println("updateSql-------"+updateSql);
				stmt.executeUpdate(updateSql);
				
				String cntSql = "select count(*) from DLNG_CONT_PRICE_DTL where MAPPING_ID like '"+temp_map+"'"
						+ " and PRICE_CD='1' and CURRENCY_CD = '"+currency[i]+"' and PAY_TYPE ='"+pay_typ[i]+"' ";
//				System.out.println("cntSql-----"+cntSql);
				rset = stmt.executeQuery(cntSql);
				if(rset.next()) {
					cnt = rset.getInt(1);
				}
				
				String priceSum = "select sum(PRICE_RATE) from DLNG_CONT_PRICE_DTL where MAPPING_ID like '"+temp_map+"'"
						+ " and PRICE_CD='1' and CURRENCY_CD = '"+currency[i]+"' and PAY_TYPE ='"+pay_typ[i]+"' ";
//				System.out.println("priceSum-----"+priceSum);
				rset = stmt.executeQuery(priceSum);
				if(rset.next()) {
					total_amt = rset.getDouble(1);
				}
				if(dr_cr_flag[i].equalsIgnoreCase("D")) {
					crdr = "Debit Note ";
					final_total = (Double.parseDouble(total_amt+"") - Double.parseDouble(adv_amt[i]+"")) ;
				}else {
					if(dr_cr_flag[i].equalsIgnoreCase("C")) {
						crdr = "Credit Note ";
					}
					
					final_total = (Double.parseDouble(total_amt+"") + Double.parseDouble(adv_amt[i]+"")) ;
				}
				
				String dlng_cont_price_dtl_sql = "";
				
				if(cnt > 0) {
					dlng_cont_price_dtl_sql = "update DLNG_CONT_PRICE_DTL "
							+ " set PRICE_RATE='"+final_total+"' "
							+ " where MAPPING_ID like '"+temp_map+"'"
							+ " and PRICE_CD='1' and  PAY_TYPE ='"+pay_typ[i]+"' ";
				}else {
					dlng_cont_price_dtl_sql = "insert into DLNG_CONT_PRICE_DTL (CURRENCY_CD,FLAG,MAPPING_ID,PRICE_CD,PRICE_RATE,PAY_TYPE)"
							+ " values ('"+currency[i]+"','Y','"+mapping_id+"','1','"+final_total+"','"+pay_typ[i]+"')";
					
				}
//				System.out.println("dlng_cont_price_dtl_sql-------"+dlng_cont_price_dtl_sql);
				stmt.execute(dlng_cont_price_dtl_sql);
				msg = crdr+""+payType+" Details Successfully Approved for "+contNo+":"+sn_cd[i];
				
				dbcon.commit();
			}
		}
	} catch (Exception e) {
		msg = "";
		try {
			dbcon.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
		err_msg = payType+" Approval Failed : Please contact IT Administration";
	}
	url=modUrl+"?msg="+msg+"&err_msg="+err_msg+"&cust_cd="+bscode+"&subModUrl="+subModUrl+"&formId="+formId+"&modCd="+modCd+
			"&write_permission="+write_permission+"&delete_permission="+delete_permission+
			"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+
			"&cont_typ="+cont_typ+"&modUrl="+modUrl;
	
	return url;

}


	private String multiAdvancePaymentEntry(HttpServletRequest req, HttpServletResponse res) throws SQLException {
		
		err_msg = "";
		msg="";
		String modCd = req.getParameter("modCd")==null?"":req.getParameter("modCd");
		String formId = req.getParameter("formId")==null?"":req.getParameter("formId");
		String subModUrl = req.getParameter("subModUrl")==null?"":req.getParameter("subModUrl");
		String modUrl = req.getParameter("modUrl")==null?"":req.getParameter("modUrl");
		String cont_typ = req.getParameter("cont_typ")==null?"":req.getParameter("cont_typ");
		String bscode = req.getParameter("cust_cd")==null?"0":req.getParameter("cust_cd");
		String selInd = req.getParameter("selInd")==null?"0":req.getParameter("selInd");
		
		String fgsa_revno[] = req.getParameterValues("fgsa_revno");
		String sn_cd[] = req.getParameterValues("sn_cd");
		String sn_revno[] = req.getParameterValues("sn_revno");
		String fgsa_cd[] = req.getParameterValues("fgsa_cd");
		String currency[] = req.getParameterValues("hid_currency");
		String user_cd = req.getParameter("user_cd");
		String exg_date[] = req.getParameterValues("exg_dt");
		String exg_rt[] = req.getParameterValues("exg_rt");
		String recv_dt[] = req.getParameterValues("recv_dt");
		String rmk[] = req.getParameterValues("rmk");
		
		String subFlg[] = req.getParameterValues("subFlg");
		String pay_typ[] = req.getParameterValues("pay_typ");
		
		String AP[] = req.getParameterValues("AP");
		String SP[] = req.getParameterValues("SP");
		String LP[] = req.getParameterValues("LP");
		String seq_no[] = req.getParameterValues("seq_no");
		String crdrtd[] = req.getParameterValues("crdrtd");
//		System.out.println("crdrtd******"+crdrtd.length);
		double final_total = 0;
		String contNo = "";
		if(cont_typ.equals("S")) {
			contNo = "SN";
		}else if(cont_typ.equals("L")) {
			contNo = "LoA";
		}
//		final_total = (Double.parseDouble(total_amt+"") + Double.parseDouble(adv_amt+"")) - Double.parseDouble(sel_amt+"") ; 
		
		int maxSeq=1;
	try {
		for(int i = 0; i < sn_cd.length; i++) {
//			System.out.println("i******"+i+"****selInd***"+selInd);
			if(i == Integer.parseInt(selInd+"")) {
//				System.out.println("SP[i]***"+SP[i]);
				String adv_amt="";
				if(pay_typ[i].equals("AP")) {
					adv_amt = AP[i];
				}else if (pay_typ[i].equals("SP")) {
					adv_amt = SP[i];
				}else if(pay_typ[i].equals("LP")) {
					adv_amt = LP[i];
				}
				if(!fgsa_cd[i].equals("") && !fgsa_revno[i].equals("") && !sn_cd[i].equals("") && !sn_revno[i].equals("")) {
				
					if(subFlg[i].equalsIgnoreCase("entry")) { // for new entry
						/*fetching max seq no.*/
						String maxSeqSql="select nvl(max(seq_no+1),1) from DLNG_ADVC_PAY_MST where CUSTOMER_CD='"+bscode+"' "
								+ " and FLSA_NO='"+fgsa_cd[i]+"' and  SN_NO='"+sn_cd[i]+"'  and CONTRACT_TYPE = '"+cont_typ+"' ";
//						System.out.println("maxSeqSql******"+maxSeqSql);
						rset = stmt.executeQuery(maxSeqSql);
						if(rset.next()) {
							maxSeq = rset.getInt(1);
						}
						
						/*
						 * String insertSql =
						 * "insert into DLNG_ADVC_PAY_MST (SEQ_NO,CURRENCY,CUSTOMER_CD,EMP_CD,ENT_DT,EXG_DATE,EXG_RATE,FLSA_NO,FLSA_REV_NO,"
						 * + " PAY_AMT,PAY_DT,REMARK,SN_NO,SN_REV_NO,CONTRACT_TYPE,PAY_TYPE)" +
						 * " values ('"+maxSeq+"','"+currency[i]+"','"+bscode+"','"+user_cd+
						 * "',sysdate,to_date('"+exg_date[i]+"','dd/mm/yyyy')," +
						 * " '"+exg_rt[i]+"','"+fgsa_cd[i]+"','"+fgsa_revno[i]+"','"+adv_amt+
						 * "',to_date('"+recv_dt[i]+"','dd/mm/yyyy'),'"+rmk[i]+"','"+sn_cd[i]+"','"+
						 * sn_revno[i]+"','"+cont_typ+"','"+pay_typ[i]+"')";
						 */
						String insertSql = "insert into DLNG_ADVC_PAY_MST (SEQ_NO,CURRENCY,CUSTOMER_CD,EMP_CD,"
								+ " ENT_DT,EXG_DATE,EXG_RATE,FLSA_NO,FLSA_REV_NO,"
								+ " PAY_AMT,PAY_DT,REMARK,SN_NO,SN_REV_NO,CONTRACT_TYPE,PAY_TYPE)"
								+ " values ('"+maxSeq+"','"+currency[i]+"','"+bscode+"','"+user_cd+"',sysdate,to_date('"+exg_date[i]+"','dd/mm/yyyy'),"
									+ " '"+exg_rt[i]+"','"+fgsa_cd[i]+"','"+fgsa_revno[i]+"','"+adv_amt+"',"
									+ " to_date('"+recv_dt[i]+"','dd/mm/yyyy'),'"+rmk[i]+"','"+sn_cd[i]+"',"
									+ " '"+sn_revno[i]+"','"+cont_typ+"','"+pay_typ[i]+"')";
//						System.out.println("insertSql------"+insertSql);
						ps = dbcon.prepareStatement(insertSql);
						/*
						 * ps.setInt(1,maxSeq); ps.setString(2,currency[i]); ps.setString(3,bscode);
						 * ps.setString(4,user_cd); ps.setString(5,exg_rt[i]);
						 * ps.setString(6,fgsa_cd[i]); ps.setString(7,fgsa_revno[i]);
						 * ps.setString(8,adv_amt); ps.setString(9,rmk[i]); ps.setString(10,sn_cd[i]);
						 * ps.setString(11,sn_revno[i]); ps.setString(12,cont_typ);
						 * ps.setString(13,pay_typ[i]);
						 */
						
						int updCnt = ps.executeUpdate();
						if(updCnt > 0) {
							msg = "Advance amount collection entry successfully done for "+contNo+":"+sn_cd[i];
							dbcon.commit();
						}else {
							err_msg = "Submission Failed : Please contact IT Administration";
						}
						
					}else if(subFlg[i].equalsIgnoreCase("modify")) { //for modification
						
						/*
						 * String updateSql =
						 * "update DLNG_ADVC_PAY_MST set CURRENCY='"+currency[i]+"',ENT_DT=sysdate," +
						 * " EXG_DATE=to_date('"+exg_date[i]+"','dd/mm/yyyy'),EXG_RATE='"+exg_rt[i]+"',"
						 * + " PAY_AMT='"+adv_amt+"',PAY_DT=to_date('"+recv_dt[i]+"','dd/mm/yyyy')," +
						 * " REMARK='"+rmk[i]+"',EMP_CD = '"+user_cd+"'" + " where" +
						 * " CUSTOMER_CD = '"+bscode+"' and FLSA_NO='"+fgsa_cd[i]+"'" +
						 * " and FLSA_REV_NO = '"+fgsa_revno[i]+"' and SN_NO = '"+sn_cd[i]+"'" +
						 * " and seq_no='"+seq_no[i]+"' " +
						 * " and CONTRACT_TYPE = '"+cont_typ+"' and PAY_TYPE = '"+pay_typ[i]+"'"; //
						 * System.err.println("updateSql-------"+updateSql);
						 * stmt.executeUpdate(updateSql);
						 */
						
						String updateSql = "update DLNG_ADVC_PAY_MST set CURRENCY=?,ENT_DT=sysdate,"
								+ " EXG_DATE=to_date('"+exg_date[i]+"','dd/mm/yyyy'),EXG_RATE=?,"
								+ " PAY_AMT=?,PAY_DT=to_date('"+recv_dt[i]+"','dd/mm/yyyy'),"
								+ " REMARK=?,EMP_CD = ?"
								+ " where"
								+ " CUSTOMER_CD = ? and FLSA_NO=? "
								+ " and FLSA_REV_NO = ? and SN_NO = ? "
								+ " and seq_no=? "
								+ " and CONTRACT_TYPE = ? and PAY_TYPE = ? ";
						
						ps = dbcon.prepareStatement(updateSql);
						ps.setString(1,currency[i]);
						ps.setString(2,exg_rt[i]);
						ps.setString(3,adv_amt);
						ps.setString(4,rmk[i]);
						ps.setString(5,user_cd);
						ps.setString(6,bscode);
						ps.setString(7,fgsa_cd[i]);
						ps.setString(8,fgsa_revno[i]);
						ps.setString(9,sn_cd[i]);
						ps.setString(10,seq_no[i]);
						ps.setString(11,cont_typ);
						ps.setString(12,pay_typ[i]);
						
						int updCnt = ps.executeUpdate();
						if(updCnt > 0) {
							msg = "Advance amount collection modification successfully done "+contNo+":"+sn_cd[i];
							dbcon.commit();
						}else {
							err_msg = "Modification Failed : Please contact IT Administration";
						}
						
					}else if(subFlg[i].equalsIgnoreCase("generate_cr_dr_note")) {
						
//						System.out.println("crdrtd---"+crdrtd[i]);
						
						String maxDrCrSeq = "";
						String maxDrCrSeqSql = "select nvl(max(SEQ_NO+.1),'"+seq_no[i]+".1') from DLNG_ADVC_PAY_MST "
								+ " where CUSTOMER_CD = '"+bscode+"' and FLSA_NO='"+fgsa_cd[i]+"'"
								+ " and FLSA_REV_NO = '"+fgsa_revno[i]+"' and SN_NO = '"+sn_cd[i]+"'"
								+ " and seq_no like '"+seq_no[i]+".%' "
								+ " and CONTRACT_TYPE = '"+cont_typ+"' and PAY_TYPE = '"+pay_typ[i]+"'";
						System.out.println("maxDrCrSeqSql---"+maxDrCrSeqSql);
						rset = stmt.executeQuery(maxDrCrSeqSql);
						if(rset.next()) {
							maxDrCrSeq = rset.getString(1)==null?"":rset.getString(1);
						}
//						System.out.println("maxDrCrSeq----"+maxDrCrSeq);
						
						/*
						 * String insertSql =
						 * "insert into DLNG_ADVC_PAY_MST (SEQ_NO,CURRENCY,CUSTOMER_CD,EMP_CD,ENT_DT,EXG_DATE,EXG_RATE,FLSA_NO,FLSA_REV_NO,"
						 * + " PAY_AMT,PAY_DT,REMARK,SN_NO,SN_REV_NO,CONTRACT_TYPE,PAY_TYPE,DR_CR_FLAG)"
						 * + " values ('"+maxDrCrSeq+"','"+currency[i]+"','"+bscode+"','"+user_cd+
						 * "',sysdate,to_date('"+exg_date[i]+"','dd/mm/yyyy')," +
						 * " '"+exg_rt[i]+"','"+fgsa_cd[i]+"','"+fgsa_revno[i]+"','"+adv_amt+
						 * "',to_date('"+recv_dt[i]+"','dd/mm/yyyy'),'"+rmk[i]+"','"+sn_cd[i]+"','"+
						 * sn_revno[i]+"','"+cont_typ+"','"+pay_typ[i]+"','"+crdrtd[i]+"')"; //
						 * System.out.println("insertSql------"+insertSql); stmt.execute(insertSql);
						 */
						
						String insertSql = "insert into DLNG_ADVC_PAY_MST (SEQ_NO,CURRENCY,CUSTOMER_CD,EMP_CD,"
								+ " ENT_DT,EXG_DATE,EXG_RATE,FLSA_NO,FLSA_REV_NO,"
								+ " PAY_AMT,PAY_DT,REMARK,SN_NO,SN_REV_NO,CONTRACT_TYPE,PAY_TYPE,DR_CR_FLAG)"
								+ " values (?,?,?,?,sysdate,to_date('"+exg_date[i]+"','dd/mm/yyyy'),"
									+ " ?,?,?,?,"
									+ " to_date('"+recv_dt[i]+"','dd/mm/yyyy'),?,?,"
									+ " ?,?,?,?)";
						
						System.out.println("insertSql------"+insertSql);
						ps = dbcon.prepareStatement(insertSql);
						ps.setString(1,maxDrCrSeq);
						ps.setString(2,currency[i]);
						ps.setString(3,bscode);
						ps.setString(4,user_cd);
						ps.setString(5,exg_rt[i]);
						ps.setString(6,fgsa_cd[i]);
						ps.setString(7,fgsa_revno[i]);
						ps.setString(8,adv_amt);
						ps.setString(9,rmk[i]);
						ps.setString(10,sn_cd[i]);
						ps.setString(11,sn_revno[i]);
						ps.setString(12,cont_typ);
						ps.setString(13,pay_typ[i]);
						ps.setString(14,crdrtd[i]);
						
						int updCnt = ps.executeUpdate();
						if(updCnt > 0) {
							String dr_cr = "";
							if(crdrtd[i].equals("D")) {
								dr_cr = "Debit";
							}else if(crdrtd[i].equals("C")) {
								dr_cr = "Credit";
							}
							msg = "New "+dr_cr+" Note with Sequence No. "+maxDrCrSeq+" generated successfully for "+contNo+":"+sn_cd[i];
							
							dbcon.commit();
						}else {
							err_msg = "Submission Failed : Please contact IT Administration";
						}
					}
				}	
			}
		}
	} catch (Exception e) {
		msg = "";
		dbcon.rollback();
		e.printStackTrace();
		err_msg = "Submission/Modification Failed : Please contact IT Administration";
	}
	
	url=modUrl+"?msg="+msg+"&err_msg="+err_msg+"&cust_cd="+bscode+"&subModUrl="+subModUrl+"&formId="+formId+"&modCd="+modCd+"&modUrl="+modUrl+"&cont_typ="+cont_typ;
//	System.out.println("ulr---"+url);
	return url;

}


	private String approveAdvancePayment(HttpServletRequest req, HttpServletResponse res)throws SQLException,IOException {
		err_msg = "";
		msg="";
			String modCd = req.getParameter("modCd")==null?"":req.getParameter("modCd");
			String formId = req.getParameter("formId")==null?"":req.getParameter("formId");
			String subModUrl = req.getParameter("subModUrl")==null?"":req.getParameter("subModUrl");
			String cont_typ = req.getParameter("cont_typ")==null?"":req.getParameter("cont_typ");
			String fgsa_revno = req.getParameter("fgsa_revno")==null?"0":req.getParameter("fgsa_revno");
			String sn_cd = req.getParameter("sn_cd")== null?"":req.getParameter("sn_cd");
			String sn_revno = req.getParameter("sn_revno")==null?"0":req.getParameter("sn_revno");
			String fgsa_cd = req.getParameter("fgsa_cd")==null?"":req.getParameter("fgsa_cd");
			String bscode = req.getParameter("bscode")==null?"0":req.getParameter("bscode");
			String user_cd = req.getParameter("user_cd")==null?"0":req.getParameter("user_cd");
			String fgsa_st_dt = req.getParameter("fgsa_st_dt")==null?"":req.getParameter("fgsa_st_dt");
			String fgsa_end_dt = req.getParameter("fgsa_end_dt")==null?"":req.getParameter("fgsa_end_dt");
			
			String selIndx = req.getParameter("selIndx")==null?"0":req.getParameter("selIndx");
			String[] seq_no = req.getParameterValues("mod_seq_no");
			String[] currency  = req.getParameterValues("mod_currency");
			String[] pay_typ  = req.getParameterValues("pay_typ_cd");
			String[] adv_amt  = req.getParameterValues("mod_adv_amt");
			
			double total_amt = 0,final_total=0;
			int cnt = 0;
			
			String temp_map=bscode+"-"+fgsa_cd+"-%-"+sn_cd+"-%-"+cont_typ;
			String mapping_id=bscode+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno+"-"+cont_typ;
			String selCurrency = "";
			String payType="";
		try {
			
			for(int i = 0 ; i < seq_no.length ; i++) {
				
				if(i == Integer.parseInt(selIndx+"")) {
					selCurrency = currency[i] ;
					
					if(pay_typ[i].equals("AP")) {
						payType = "Advance Payment";
					}else if (pay_typ[i].equals("SP")) {
						payType = "Security Payment";
					}else if(pay_typ[i].equals("LP")) {
						payType = "Lump-sum Payment";
					}
					
					String updateSql = "update DLNG_ADVC_PAY_MST set "
							+ " APPROVED_BY='"+user_cd+"',APPROVED_DT=sysdate,APPROVED_FLAG='Y' "
							+ " where"
							+ " CUSTOMER_CD = '"+bscode+"' and FLSA_NO='"+fgsa_cd+"'"
							+ " and  SN_NO = '"+sn_cd+"' "
							+ " and  seq_no='"+seq_no[i]+"'"
							+ " and CONTRACT_TYPE = '"+cont_typ+"' and PAY_TYPE = '"+pay_typ[i]+"'";
					System.out.println("updateSql-------"+updateSql);
					stmt.executeUpdate(updateSql);
					
					
					
					String cntSql = "select count(*) from DLNG_CONT_PRICE_DTL where MAPPING_ID like '"+temp_map+"'"
							+ " and PRICE_CD='1' and CURRENCY_CD = '"+currency[i]+"' and PAY_TYPE ='"+pay_typ[i]+"' ";
					System.out.println("cntSql-----"+cntSql);
					rset = stmt.executeQuery(cntSql);
					if(rset.next()) {
						cnt = rset.getInt(1);
					}
					
					String priceSum = "select sum(PRICE_RATE) from DLNG_CONT_PRICE_DTL where MAPPING_ID like '"+temp_map+"'"
							+ " and PRICE_CD='1' and CURRENCY_CD = '"+currency[i]+"' and PAY_TYPE ='"+pay_typ[i]+"' ";
					System.out.println("priceSum-----"+priceSum);
					rset = stmt.executeQuery(priceSum);
					if(rset.next()) {
						total_amt = rset.getDouble(1);
					}
					
					final_total = (Double.parseDouble(total_amt+"") + Double.parseDouble(adv_amt[i]+"")) ;
					
					String dlng_cont_price_dtl_sql = "";
					
					
					if(cnt > 0) {
						dlng_cont_price_dtl_sql = "update DLNG_CONT_PRICE_DTL "
								+ " set PRICE_RATE='"+final_total+"' "
								+ " where MAPPING_ID like '"+temp_map+"'"
								+ " and PRICE_CD='1' and  PAY_TYPE ='"+pay_typ[i]+"' ";
					}else {
						dlng_cont_price_dtl_sql = "insert into DLNG_CONT_PRICE_DTL (CURRENCY_CD,FLAG,MAPPING_ID,PRICE_CD,PRICE_RATE,PAY_TYPE)"
								+ " values ('"+currency[i]+"','Y','"+mapping_id+"','1','"+final_total+"','"+pay_typ[i]+"')";
						
					}
					System.out.println("dlng_cont_price_dtl_sql-------"+dlng_cont_price_dtl_sql);
					stmt.execute(dlng_cont_price_dtl_sql);
					msg = payType+" Successfully Approved";
					dbcon.commit();
				}
			}
		} catch (Exception e) {
			msg = "";
			dbcon.rollback();
			e.printStackTrace();
			err_msg = payType+" Approval Failed : Please contact IT Administration";
		}
		url="../contract_master/frm_adv_payment_entry.jsp?msg="+msg+"&err_msg="+err_msg+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&fgsa_cd="+fgsa_cd+"&bscode="+bscode+"&subModUrl="+subModUrl+"&formId="+formId+"&modCd="+modCd+"&selCurrency="+selCurrency+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&fgsa_st_dt="+fgsa_st_dt+"&fgsa_end_dt="+fgsa_end_dt+"&cont_typ="+cont_typ;
		return url;
	}


	private String advancePaymentEntry(HttpServletRequest req, HttpServletResponse res)throws SQLException,IOException  {
		
			err_msg = "";
			msg="";
			String modCd = req.getParameter("modCd")==null?"":req.getParameter("modCd");
			String formId = req.getParameter("formId")==null?"":req.getParameter("formId");
			String subModUrl = req.getParameter("subModUrl")==null?"":req.getParameter("subModUrl");
			String cont_typ = req.getParameter("cont_typ")==null?"":req.getParameter("cont_typ");
			String fgsa_revno = req.getParameter("fgsa_revno")==null?"0":req.getParameter("fgsa_revno");
			String sn_cd = req.getParameter("sn_cd")== null?"":req.getParameter("sn_cd");
			String sn_revno = req.getParameter("sn_revno")==null?"0":req.getParameter("sn_revno");
			String fgsa_cd = req.getParameter("fgsa_cd")==null?"":req.getParameter("fgsa_cd");
			String bscode = req.getParameter("bscode")==null?"0":req.getParameter("bscode");
			String currency = req.getParameter("hid_currency")==null?"":req.getParameter("hid_currency");
			String user_cd = req.getParameter("user_cd")==null?"0":req.getParameter("user_cd");
			String exg_date = req.getParameter("exg_dt")==null?"":req.getParameter("exg_dt");
			String exg_rt = req.getParameter("exg_rt")==null?"0":req.getParameter("exg_rt");
			String adv_amt = req.getParameter("adv_amt")==null?"0":req.getParameter("adv_amt");
			String recv_dt = req.getParameter("recv_dt")==null?"":req.getParameter("recv_dt");
			String rmk = req.getParameter("rmk")==null?"":req.getParameter("rmk");
			String subFlg = req.getParameter("subFlg")==null?"entry":req.getParameter("subFlg");
//			String total_amt = req.getParameter("total_amt")==null?"0":req.getParameter("total_amt");
			
			String fgsa_st_dt = req.getParameter("fgsa_st_dt")==null?"":req.getParameter("fgsa_st_dt");
			String fgsa_end_dt = req.getParameter("fgsa_end_dt")==null?"":req.getParameter("fgsa_end_dt");
			String sel_amt = req.getParameter("sel_amt")==null?"":req.getParameter("sel_amt");
			String pay_typ = req.getParameter("pay_typ")==null?"":req.getParameter("pay_typ");
			
			if(pay_typ.equals("AP")) {
				adv_amt = req.getParameter("AP")==null?"":req.getParameter("AP");
			}else if (pay_typ.equals("SP")) {
				adv_amt = req.getParameter("SP")==null?"":req.getParameter("SP");
			}else if(pay_typ.equals("LP")) {
				adv_amt = req.getParameter("LP")==null?"":req.getParameter("LP");
			}
			
			double final_total = 0;
//			final_total = (Double.parseDouble(total_amt+"") + Double.parseDouble(adv_amt+"")) - Double.parseDouble(sel_amt+"") ; 
			
			int maxSeq=1;
		try {
			if(!fgsa_cd.equals("") && !fgsa_revno.equals("") && !sn_cd.equals("") && !sn_revno.equals("")) {
				
					if(subFlg.equalsIgnoreCase("entry")) { // for new entry
						/*fetching max seq no.*/
						String maxSeqSql="select nvl(max(seq_no+1),1) from DLNG_ADVC_PAY_MST where CUSTOMER_CD='"+bscode+"' "
								+ " and FLSA_NO='"+fgsa_cd+"' and  SN_NO='"+sn_cd+"' and CONTRACT_TYPE = '"+cont_typ+"' ";
						System.out.println("maxSeqSql******"+maxSeqSql);
						rset = stmt.executeQuery(maxSeqSql);
						if(rset.next()) {
							maxSeq = rset.getInt(1);
						}
						
						String insertSql = "insert into DLNG_ADVC_PAY_MST (SEQ_NO,CURRENCY,CUSTOMER_CD,EMP_CD,ENT_DT,EXG_DATE,EXG_RATE,FLSA_NO,FLSA_REV_NO,"
								+ " PAY_AMT,PAY_DT,REMARK,SN_NO,SN_REV_NO,CONTRACT_TYPE,PAY_TYPE)"
								+ " values ('"+maxSeq+"','"+currency+"','"+bscode+"','"+user_cd+"',sysdate,to_date('"+exg_date+"','dd/mm/yyyy'),"
										+ " '"+exg_rt+"','"+fgsa_cd+"','"+fgsa_revno+"','"+adv_amt+"',to_date('"+recv_dt+"','dd/mm/yyyy'),'"+rmk+"','"+sn_cd+"','"+sn_revno+"','"+cont_typ+"','"+pay_typ+"')";
						
						System.out.println("insertSql------"+insertSql);
						stmt.execute(insertSql);
						
						
						msg = "Advance amount collection entry successfully done";
								
					}else if(subFlg.equalsIgnoreCase("modify")) { //for modification
						
						String seq_no = req.getParameter("seq_no")==null?"0":req.getParameter("seq_no");
						
						String updateSql = "update DLNG_ADVC_PAY_MST set CURRENCY='"+currency+"',ENT_DT=sysdate,"
								+ " EXG_DATE=to_date('"+exg_date+"','dd/mm/yyyy'),EXG_RATE='"+exg_rt+"',"
								+ " PAY_AMT='"+adv_amt+"',PAY_DT=to_date('"+recv_dt+"','dd/mm/yyyy'),"
								+ " REMARK='"+rmk+"',EMP_CD = '"+user_cd+"'"
								+ " where"
								+ " CUSTOMER_CD = '"+bscode+"' and FLSA_NO='"+fgsa_cd+"'"
								+ "  and FLSA_REV_NO = '"+fgsa_revno+"' and SN_NO = '"+sn_cd+"' and SN_REV_NO ='"+sn_revno+"' and seq_no='"+seq_no+"' and CONTRACT_TYPE = '"+cont_typ+"' and PAY_TYPE = '"+pay_typ+"'";
						System.err.println("updateSql-------"+updateSql);
						stmt.executeUpdate(updateSql);
						
						msg = "Advance amount collection modification successfully done";
					}
					
					/*for total DLNG_CONT_PRICE_DTL*/
				/*	double total_amt = 0;
					int cnt = 0;
					
					String temp_map=bscode+"-"+fgsa_cd+"-%-"+sn_cd+"-%-"+cont_typ;
					String mapping_id=bscode+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno+"-"+cont_typ;
					
					String cntSql = "select count(*) from DLNG_CONT_PRICE_DTL where MAPPING_ID like '"+temp_map+"'"
							+ " and PRICE_CD='1' and CURRENCY_CD = '"+currency+"' and PAY_TYPE ='"+pay_typ+"' ";
					System.out.println("cntSql-----"+cntSql);
					rset = stmt.executeQuery(cntSql);
					if(rset.next()) {
						cnt = rset.getInt(1);
					}
					
					String priceSum = "select sum(PRICE_RATE) from DLNG_CONT_PRICE_DTL where MAPPING_ID like '"+temp_map+"'"
							+ " and PRICE_CD='1' and CURRENCY_CD = '"+currency+"' and PAY_TYPE ='"+pay_typ+"' ";
					System.out.println("priceSum-----"+priceSum);
					rset = stmt.executeQuery(priceSum);
					if(rset.next()) {
						total_amt = rset.getDouble(1);
					}
					
					final_total = (Double.parseDouble(total_amt+"") + Double.parseDouble(adv_amt+"")) - Double.parseDouble(sel_amt+"") ;
					
					String dlng_cont_price_dtl_sql = "";
					
					
					if(cnt > 0) {
						dlng_cont_price_dtl_sql = "update DLNG_CONT_PRICE_DTL "
								+ " set PRICE_RATE='"+final_total+"' "
								+ " where MAPPING_ID like '"+temp_map+"'"
								+ " and PRICE_CD='1' and  PAY_TYPE ='"+pay_typ+"' ";
					}else {
						dlng_cont_price_dtl_sql = "insert into DLNG_CONT_PRICE_DTL (CURRENCY_CD,FLAG,MAPPING_ID,PRICE_CD,PRICE_RATE,PAY_TYPE)"
								+ " values ('"+currency+"','N','"+mapping_id+"','1','"+final_total+"','"+pay_typ+"')";
						
					}
					System.out.println("dlng_cont_price_dtl_sql-------"+dlng_cont_price_dtl_sql);
					stmt.execute(dlng_cont_price_dtl_sql);*/
					////////////////////////////////////////////////////////////////////////////////
					
				dbcon.commit();
				
			}else {
				err_msg = "Please select proper customer details!!";
			}
			
		} catch (Exception e) {
			msg = "";
			dbcon.rollback();
			e.printStackTrace();
			err_msg = "Submission/Modification Failed : Please contact IT Administration";
		}
		url="../contract_master/frm_adv_payment_entry.jsp?msg="+msg+"&err_msg="+err_msg+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&fgsa_cd="+fgsa_cd+"&bscode="+bscode+"&subModUrl="+subModUrl+"&formId="+formId+"&modCd="+modCd+"&selCurrency="+currency+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&fgsa_st_dt="+fgsa_st_dt+"&fgsa_end_dt="+fgsa_end_dt+"&cont_typ="+cont_typ;
		return url;
	}
}
