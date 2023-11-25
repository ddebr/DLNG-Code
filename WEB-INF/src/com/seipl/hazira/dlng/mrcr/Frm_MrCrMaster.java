package com.seipl.hazira.dlng.mrcr;
import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.MethodNotSupportedException;
import javax.naming.*;
import javax.sql.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

//Code Reviewed by	: 
//CR Date			: 
//CR Status  		:

public  class Frm_MrCrMaster extends HttpServlet
{ 
	public static  Connection dbcon;
	
	public String servletName = "Frm_Master";
	public String methodName = "";
	public String option = "";
	public String url = "";
	public String form_name = "";
	int count=0;
	public String FLAG = "T";		
	private static String query = null;
	private static String query1 = null;
	private static String query2 = null;
	private static Statement stmt = null;
	private static Statement stmt1=null;
	private static Statement stmt2 = null;
	private static Statement stmt3=null;
	private static Statement stmt4=null;
	private  ResultSet rset = null;
	private ResultSet rset1=null;
	private ResultSet rset2=null;
	private ResultSet rset3=null;
	private ResultSet rset4=null;
	
	public String write_permission = "N";
	public String delete_permission = "N";
	public String print_permission = "N";
	public String approve_permission = "N";
	public String audit_permission = "N";
	public String check_permission = "N";
	
	public String form_id = "0";
	public String form_nm = "";
	
	//Following NumberFormat Object is defined by Samik Shah ... On 30th July, 2010 ...
	NumberFormat nf = new DecimalFormat("###########0.0000");
	
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
				stmt1 = dbcon.createStatement();
			}
			
			option=req.getParameter("option")==null?"":req.getParameter("option");
			
			write_permission = req.getParameter("write_permission")==null?"N":req.getParameter("write_permission");
			delete_permission = req.getParameter("delete_permission")==null?"N":req.getParameter("delete_permission");
			print_permission = req.getParameter("print_permission")==null?"N":req.getParameter("print_permission");
			approve_permission = req.getParameter("approve_permission")==null?"N":req.getParameter("approve_permission");
			audit_permission = req.getParameter("audit_permission")==null?"N":req.getParameter("audit_permission");
			check_permission = req.getParameter("check_permission")==null?"N":req.getParameter("check_permission");
			
			form_id = req.getParameter("form_id")==null?"0":req.getParameter("form_id");
			form_nm = req.getParameter("form_nm")==null?"":req.getParameter("form_nm");
			
			if(option.equalsIgnoreCase("ProductInsertModifyDetails"))
			{
				ProductInsertModifyDetails(req,res);
			}
			else if(option.equalsIgnoreCase("DocumentInsertModifyDetails"))
			{
				DocumentInsertModifyDetails(req,res);
			}
			else if(option.equalsIgnoreCase("RateMaster"))
	        {
				insertRates(req);
	        }
			else if(option.equals("int_exchg_entry"))
			{ 
				saveDailyIntExchgRateEntry(req);
		    }
			else if(option.equals("InsertModifyShipDetails"))
			{ 
				InsertModifyShipDetails(req);
		    }
			else if(option.equals("BankInsertModifyDetails"))
			{ 
				BankInsertModifyDetails(req,res);
		    }
			else if(option.equals("GovtStatTaxNamesInsertDetails"))
			{
				GovtStatTaxNamesInsertDetails(req,res);
			}
			else if(option.equals("GovtStatTaxNamesModifyDetails"))
			{
				GovtStatTaxNamesModifyDetails(req,res);
			}
			else if(option.equals("SectorNamesInsertDetails"))
			{
				SectorNamesInsertDetails(req,res);
			}
			else if(option.equals("SectorNamesModifyDetails"))
			{
				SectorNamesModifyDetails(req,res);
			}
			else if(option.equals("Submit_Delegation_Invoice"))
			{
				SubmitDelegationInvoice(req,res);
			}
			else if(option.equals("RemoveAccess"))
			{
				RemoveAccess(req,res);
			}
			else if(option.equals("submitSunAccountMapping")) {
				submitSunAccountMapping(req,res);
			}
		}		   
		catch(Exception e)
		{
			System.out.println("Exception:(Frm_Master)-(doPost())-(CONNECTION): "+e);
			url="../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=master&formname="+form_name;
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
//		System.out.println("url = "+url);
		res.sendRedirect(url);
	}
	
	public void submitSunAccountMapping(HttpServletRequest request, HttpServletResponse res) throws Exception {
		methodName = "submitSunAccountMapping()";
		form_name = "frm_sun_map_with_gstin.jsp";
		String msg = "";
		try {
			HttpSession sess = request.getSession();
			String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd"); 
			String gstin[] = request.getParameterValues("gstin");
			String gstin_no[] = request.getParameterValues("gstin_no");
			String eff_dt[] = request.getParameterValues("eff_dt");
			String customer_cd[] = request.getParameterValues("customer_cd");
			
			for(int i=0;i<gstin.length;i++) {
				if(!gstin_no[i].trim().equals("") && !eff_dt[i].trim().equals("")) {
					query = "SELECT COUNT(GSTIN_NO) FROM FMS8_GSTIN_SUN_MAPPING_MST WHERE CUSTOMER_CD='"+customer_cd[i]+"' "
							+ "AND GSTIN_NO = '"+gstin[i]+"'";
					rset = stmt.executeQuery(query);
					if(rset.next()) {
						int count = rset.getInt(1);
						if(count>0) {
							//UPDATE DATA..
							query = "UPDATE FMS8_GSTIN_SUN_MAPPING_MST SET SUN_CODE='"+gstin_no[i]+"',"
									+ "EFF_DT = TO_DATE('"+eff_dt[i]+"','DD/MM/YYYY'), ENTER_DT = SYSDATE, "
									+ "ENTER_BY='"+user_cd+"' WHERE CUSTOMER_CD='"+customer_cd[i]+"' AND "
									+ "GSTIN_NO = '"+gstin[i]+"' ";
						} else {
							//INSERT DATA..
								query = "INSERT INTO FMS8_GSTIN_SUN_MAPPING_MST(CUSTOMER_CD,GSTIN_NO,SUN_CODE,"
										+ "EFF_DT,ENTER_BY,ENTER_DT,FLAG) VALUES('"+customer_cd[i]+"','"+gstin[i]+"',"
										+ "'"+gstin_no[i]+"',TO_DATE('"+eff_dt[i]+"','DD/MM/YYYY'),'"+user_cd+"',"
										+ "SYSDATE,'Y') ";
						}
						stmt.executeUpdate(query);
					}
				}
			}
			msg = "Sun Account Codes Are Successfully Linked with GSTIN Numbers!!!";
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+sess.getAttribute("username")+"/"+sess.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
			dbcon.commit();
		} catch(Exception e) {
			msg = "Data Not Submitted Successfully!!!";
			dbcon.rollback();
			e.printStackTrace();
		}
		url = "../master/frm_sun_map_with_gstin.jsp?msg="+msg+"&write_permission="+write_permission+"&check_permission="+check_permission+"&approve_permission="+approve_permission;
	}
	
	/***ADDED FOR DELEGATION OF ACCESS RIGHTS FOR INVOICE****RS07022017**/
	
	public void RemoveAccess(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "RemoveAccess()";
		form_name = "frm_delegation_invoice_access_rights.jsp";
		String msg="";
		String from_dt = request.getParameter("start_date")==null?"":request.getParameter("start_date");
		String to_dt = request.getParameter("end_date")==null?"":request.getParameter("end_date");
		
		try
		{
			String remove_id = request.getParameter("remove_id")==null?"0":request.getParameter("remove_id");
			HttpSession sess = request.getSession();
			String user_cd_login = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
			
			String query = "UPDATE FMS8_INVOICE_RIGHTS_DELEGATION SET FLAG = 'N',ENTER_BY='"+user_cd_login+"', "
					+ "ENTER_DT = TO_DATE(SYSDATE,'DD/MM/YYYY')  WHERE "
					+ "EMP_CD = '"+remove_id+"' AND  ( (FROM_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') "
					+ "AND from_dt <= TO_DATE('"+from_dt+"','DD/MM/YYYY') ) or "
					+ "to_dt >= TO_DATE('"+to_dt+"','DD/MM/YYYY') AND to_dt <= TO_DATE('"+to_dt+"','DD/MM/YYYY') )";
//			System.out.println("Delete Query..."+query);
			stmt.executeUpdate(query);
			dbcon.commit();
			msg="ACCESS RIGHT DELEGATION FOR INVOICE IS REMOVED!!!";
			
			url="../master/frm_delegation_invoice_access_rights.jsp?&start_date="+from_dt+"&end_date="+to_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&message="+msg;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+sess.getAttribute("username")+"/"+sess.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
//				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg="ACCESS RIGHT DELEGATION FOR INVOICE NOT REMOVED!!!";
			url="../master/frm_delegation_invoice_access_rights.jsp?&start_date="+from_dt+"&end_date="+to_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&message="+msg;
//			System.out.println("Exception in "+methodName+".."+e);
			e.printStackTrace();
		}
	}
	
	public void SubmitDelegationInvoice(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "SubmitDelegationInvoice()";
		form_name = "frm_delegation_invoice_access_rights.jsp";
		String msg="";
		String from_dt = request.getParameter("start_date")==null?"":request.getParameter("start_date");
		String to_dt = request.getParameter("end_date")==null?"":request.getParameter("end_date");

		try
		{
			String user_cd[] = request.getParameterValues("user_code");
			
			String temp="";
			String temp_val="",check="",check_val="",approve="",approve_val="";
			
			HttpSession sess = request.getSession();
			String user_cd_login = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");

			for(int i=0;i<user_cd.length;i++)
			{
				temp = "user"+i;
				temp_val = request.getParameter(temp)==null?"N":request.getParameter(temp);
				if(temp_val.equalsIgnoreCase("Y"))
				{
					check = "check"+i;
					check_val = request.getParameter(check)==null?"N":request.getParameter(check);
					
					approve = "approve"+i;
					approve_val = request.getParameter(approve)==null?"N":request.getParameter(approve);
					
					String q = "SELECT COUNT(EMP_CD) FROM FMS8_INVOICE_RIGHTS_DELEGATION WHERE "
							+ "EMP_CD='"+user_cd[i]+"' AND FROM_DT = TO_DATE('"+from_dt+"','DD/MM/YYYY') "
							+ "AND TO_DT = TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
//					System.out.println("Select Query.."+q);
					rset = stmt1.executeQuery(q);
					if(rset.next())
					{
						int count = rset.getInt(1);
						if(count>0) {
						String query = "DELETE FROM FMS8_INVOICE_RIGHTS_DELEGATION WHERE EMP_CD='"+user_cd[i]+"' "
								+ "AND  FROM_DT = TO_DATE('"+from_dt+"','DD/MM/YYYY') AND "
								+ "TO_DT = TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
//						System.out.println("Delete Query.."+query);
						stmt1.executeUpdate(query);
						}
					} 
					String query = "INSERT INTO FMS8_INVOICE_RIGHTS_DELEGATION(EMP_CD,CHECK_PER,APPRV_PER,"
							+ "FROM_DT,TO_DT,ENTER_BY,ENTER_DT,FLAG) VALUES('"+user_cd[i]+"','"+check_val+"',"
							+ "'"+approve_val+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),"
							+ "TO_DATE('"+to_dt+"','DD/MM/YYYY'),'"+user_cd_login+"',SYSDATE,'Y')";
//					System.out.println("Insert Query.."+query);
					stmt.executeUpdate(query);
					
					msg="ACCESS RIGHT DELEGATION FOR INVOICE SUBMITTED SUCCESSFULLY...!!";
				} 
			}
			
			dbcon.commit();
			url="../master/frm_delegation_invoice_access_rights.jsp?&start_date="+from_dt+"&end_date="+to_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&message="+msg;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+sess.getAttribute("username")+"/"+sess.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
//				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg="ACCESS RIGHT DELEGATION FOR INVOICE NOT SUBMITTED!!!";
			url="../master/frm_delegation_invoice_access_rights.jsp?&start_date="+from_dt+"&end_date="+to_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&message="+msg;
//			System.out.println("Exception in "+methodName+".."+e);
			e.printStackTrace();
		}
	}
		
	//Data From frm_product_mst.jsp
	public void ProductInsertModifyDetails(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "ProductInsertModifyDetails()";
		form_name = "frm_product_mst.jsp";
		
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession sess = request.getSession();
		HttpSession session = request.getSession();
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String cd = request.getParameter("cd")==null?"0":request.getParameter("cd");
		String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String abrv = request.getParameter("abrv")==null?"":request.getParameter("abrv");
		String notes = request.getParameter("notes")==null?"":request.getParameter("notes");
		String activity = "update";
		
		name = obj.replaceSingleQuotes(name);
		notes = obj.replaceSingleQuotes(notes);
		abrv = obj.replaceSingleQuotes(abrv).trim();
		
		int p_cd = 0;
		String msg = "";
		String product_cd = "0";
		boolean abrv_flag = true;
		String code="0";
		
		try
		{
			String queryString="SELECT NVL(MAX(prod_cd),'0') FROM FMS7_PRODUCT_MST";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				p_cd = Integer.parseInt(rset.getString(1)) + 1;
				product_cd = ""+p_cd;
			}
			else
			{
				p_cd = 1;
				product_cd = ""+p_cd;
			}

			if(flg.equals("insert"))
			{
				queryString="SELECT NVL(prod_cd,'0') FROM FMS7_PRODUCT_MST WHERE TRIM(prod_abrv)='"+abrv+"'";
				System.out.println("Product Abbreviation Comparission Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1).equals("0"))
					{
						abrv_flag = true;
					}
					else
					{
						abrv_flag = false;
					}
				}
				else
				{
					abrv_flag = true;
				}
				
				if(abrv_flag)
				{
					queryString="INSERT INTO FMS7_PRODUCT_MST(prod_cd,prod_nm,prod_abrv,prod_desc,emp_cd,ent_dt) VALUES('"+product_cd+"','"+name+"','"+abrv+"','"+notes+"','"+user_cd+"',sysdate)";
					System.out.println("Product Insert Query = "+queryString);
					stmt.executeUpdate(queryString);
					msg="Product Details Submitted Successfully !!!";
					code=product_cd;
				}
				else
				{
					activity = "insert";
					msg="Product Details NOT Submitted Due To Duplicate Abbreviation Insertion !!!";
					code="0";
				}
			}
			else if(flg.equals("update"))
			{
				queryString="SELECT NVL(prod_cd,'0') FROM FMS7_PRODUCT_MST WHERE TRIM(prod_abrv)='"+abrv+"' AND prod_cd!="+cd+"";
				System.out.println("Product Abbreviation Comparission Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1).equals("0"))
					{
						abrv_flag = true;
					}
					else
					{
						abrv_flag = false;
					}
				}
				else
				{
					abrv_flag = true;
				}
				
				if(abrv_flag)
				{
					queryString="UPDATE FMS7_PRODUCT_MST SET prod_nm='"+name+"',prod_abrv='"+abrv+"',prod_desc='"+notes+"',emp_cd='"+user_cd+"',ent_dt=sysdate WHERE prod_cd="+cd+"";
					System.out.println("Product Modify Query = "+queryString);
					stmt.executeUpdate(queryString);
					msg="Product Details Modified Successfully !!!";
					code=cd;
				}
				else
				{
					activity = "update";
					msg="Product Details NOT Modified Due To Duplicate Abbreviation Insertion !!!";
					code=cd;
				}
			}
			
			url="../master/frm_product_mst.jsp?msg="+msg+"&bscode="+code+"&flg="+activity+"&activity="+activity+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			dbcon.commit();
			
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
			msg="Product Details Not Submitted !!!";
			e.printStackTrace();
			System.out.println("Exception in the "+methodName+" of "+servletName+"\n"+e.getMessage());
			url="../master/frm_product_mst.jsp?msg="+msg+"&flg=insert&activity=insert&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}//end of method ProductInsertModifyDetails() ...
	
	
	//Data From frm_document_mst.jsp
	private void DocumentInsertModifyDetails(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "DocumentInsertModifyDetails()";
		form_name = "frm_document_mst.jsp";
		
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession sess = request.getSession();
		HttpSession session = request.getSession();
		String msg = "";
		int d_cd = 0;
		String doc_cd = "0";
		
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String cd = request.getParameter("cd")==null?"0":request.getParameter("cd");
		String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String doc_nm = request.getParameter("doc_nm")==null?"":request.getParameter("doc_nm");
		String doc_abrv = request.getParameter("doc_abrv")==null?"":request.getParameter("doc_abrv");
		String doc_desc = request.getParameter("doc_desc")==null?"":request.getParameter("doc_desc");
		String doc_type = request.getParameter("doc_type")==null?"R":request.getParameter("doc_type");
		String seq_no = request.getParameter("seq_no")==null?"0":request.getParameter("seq_no");

		doc_nm = obj.replaceSingleQuotes(doc_nm.trim());
		doc_desc = obj.replaceSingleQuotes(doc_desc);
		doc_abrv = obj.replaceSingleQuotes(doc_abrv.trim());

		boolean abrv_flag = true;
		
		try
		{
			String queryString="SELECT NVL(MAX(doc_cd),'800000') FROM FMS7_DOCUMENT_MST";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				d_cd = Integer.parseInt(rset.getString(1)) + 1;
				doc_cd = ""+d_cd;
			}
			else
			{
				doc_cd = "800001";
			}
			
			if(flg.equals("insert"))
			{
				queryString="SELECT NVL(doc_cd,'0') FROM FMS7_DOCUMENT_MST WHERE TRIM(doc_abrv)='"+doc_abrv+"'";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1).equals("0"))
					{
						abrv_flag = true;
					}
					else
					{
						abrv_flag = false;
					}
				}
				else
				{
					abrv_flag = true;
				}
				
				if(abrv_flag)
				{
					queryString="INSERT INTO FMS7_DOCUMENT_MST(doc_cd,doc_nm,doc_abrv,doc_desc,emp_cd,ent_dt,seq_no,doc_type) VALUES('"+doc_cd+"','"+doc_nm+"','"+doc_abrv+"','"+doc_desc+"','"+user_cd+"',sysdate,'"+seq_no+"','"+doc_type+"')";
					System.out.println(queryString);
					stmt.executeUpdate(queryString);
					msg="Data Submitted Successfully For The Document : "+doc_nm+" ...";
				}
				else
				{
					msg="Data NOT Submitted For The Document Due To Duplicate Abbreviation Insertion !!!";
				}
			}
			else if(flg.equals("update"))
			{
				queryString="SELECT NVL(doc_cd,'0') FROM FMS7_DOCUMENT_MST WHERE TRIM(doc_abrv)='"+doc_abrv+"' AND doc_cd!="+cd+"";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1).equals("0"))
					{
						abrv_flag = true;
					}
					else
					{
						abrv_flag = false;
					}
				}
				else
				{
					abrv_flag = true;
				}
				
				if(abrv_flag)
				{
					queryString="UPDATE FMS7_DOCUMENT_MST SET doc_nm='"+doc_nm+"',doc_abrv='"+doc_abrv+"',doc_desc='"+doc_desc+"'," +
								"doc_type='"+doc_type+"',emp_cd='"+user_cd+"',ent_dt=sysdate,seq_no='"+seq_no+"' WHERE doc_cd="+cd+"";
					System.out.println(queryString);
					stmt.executeUpdate(queryString);
					msg="Data Modified Successfully For The Document : "+doc_nm+" ...";
				}
				else
				{
					msg="Data NOT Modified For The Document Master Due To Duplicate Abbreviation Insertion !!!";
				}
			}
			
			url="../master/frm_document_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			dbcon.commit();
			
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
			msg="Document Data Not Submitted ...";
			e.printStackTrace();
			System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
			url="../master/frm_document_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}//end of method DocumentInsertModifyDetails()

	
	//Data From frm_rate_master.jsp ...
	public void insertRates(HttpServletRequest req) throws ServletException, IOException, SQLException
    {
		methodName = "insertRates()";
		form_name = "frm_rate_master.jsp";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		String flagupdate=req.getParameter("flag");
     	String rate=req.getParameter("rate");
     	HttpSession session = req.getSession();
     	String user_cd = (String)session.getAttribute("user_cd");
     	String status="";
     	String [] bank_name = req.getParameterValues("bank_name");
     	String bank_nameA = req.getParameter("bank_nameA");
      
     	if(rate.equalsIgnoreCase("Instrate"))
     	{
	    	String [] int_rate_cd=req.getParameterValues("intcd");
	    	String [] int_rate_desc=req.getParameterValues("intdesc");
	    	String [] int_flag=req.getParameterValues("intflag");
	    	String int_rate_cdA=req.getParameter("intcdA");
	    	String int_rate_descA=req.getParameter("intdescA");
	    	String int_rate_flag=req.getParameter("intflag");
	    	
	    	int_rate_descA = obj.replaceSingleQuotes(int_rate_descA);
   	
	   	    if(flagupdate.equalsIgnoreCase("M"))
	   	    {
	   	    	if(rate!= null)
	   	    	{
	   	    		for(int i = 0 ; i <int_rate_desc.length ; i++)
	   	    		{
	   	    			int_rate_desc[i] = obj.replaceSingleQuotes(int_rate_desc[i]);
	   	    			
	   	    			try
	   	    			{
	   	    			/*SB20200427	query="UPDATE FMS7_CONT_INT_RATE_MST "+
	   	    					  "SET INT_RATE_NM='"+int_rate_desc[i]+"',FLAG='"+int_rate_flag+"', " +
	   	    					  "ENT_DT=sysdate, EMP_CD="+user_cd+", BANK_ABBR = '"+bank_name[i]+"' " +
	   	    					  "WHERE INT_RATE_CD='"+int_rate_cd[i]+"' ";
	   	    					  */
	   	    				query="UPDATE FMS7_CONT_INT_RATE_MST "+
		   	    					  "SET INT_RATE_NM='"+int_rate_desc[i]+"',FLAG='"+int_flag[i]+"', " +
		   	    					  "ENT_DT=sysdate, EMP_CD="+user_cd+", BANK_ABBR = '"+bank_name[i]+"' " +
		   	    					  "WHERE INT_RATE_CD='"+int_rate_cd[i]+"' ";
	   	    				stmt.executeUpdate(query);
	   	    				dbcon.commit();
	   	    			}
	   	    			catch(Exception e)
	   	    			{
	   	    				dbcon.rollback();
//	   	    				System.out.println("Exception:(Frm_Master)-(insertRates())-(FMS7_CONT_INT_RATE_MST): "+e.getMessage());
	   	    				url="../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=master&formname=frm_rate_master.jsp&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
	   	    			}
	   	    		}
	   	    	}
	   	    	status="Master Entry: Interest Rate Name Successfully Updated !!!";
	   	    	try
				{
					new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+status);
				}
				catch(Exception infoLogger)
				{
//					System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
					infoLogger.printStackTrace();
				}
	   	    }
	   	    else if(flagupdate.equalsIgnoreCase("A"))
	   	    {
	   	    	if(rate!= null)
	   	    	{
	   	    		int count=0;
	   	    		try
	   	    		{ 
	   	    			query="Select count(int_rate_cd) from FMS7_CONT_INT_RATE_MST ";
	   	    			rset=stmt.executeQuery(query);
	   	    			if(rset.next())
	   	    			{
	   	    				count=rset.getInt(1);
	   	    				count++;
	   	    			}
 	   	    	    
	   	    			query="INSERT INTO FMS7_CONT_INT_RATE_MST (INT_RATE_CD,INT_RATE_NM,FLAG,ENT_DT,EMP_CD,BANK_ABBR) "
	   	    					+ "VALUES ('"+count+"','"+int_rate_descA+"','Y',sysdate,"+user_cd+",'"+bank_nameA+"')";
	   	    			stmt.executeUpdate(query);
	   	    			dbcon.commit();
	   	    		}
 	       	       	catch(Exception e)
 	       	       	{
 	       	       		dbcon.rollback();
// 	       	       		System.out.println("Exception:(Frm_Master)-(insertRates())-(FMS7_CONT_INT_RATE_MST): "+e.getMessage());
 	       	       		url="../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=master&formname=frm_rate_master.jsp&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
 	       	       	}
	   	    	}
	   	    	status="Master Entry: Interest Rate Name Successfully Inserted !!!";
	   	    	try
				{
					new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+status);
				}
				catch(Exception infoLogger)
				{
//					System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
					infoLogger.printStackTrace();
				} 
	   	    }
     	}
     	else if(rate.equalsIgnoreCase("Exgrate"))
     	{
	    	String [] exg_rate_cd=req.getParameterValues("exgcd");
	    	String [] exg_rate_desc=req.getParameterValues("exgdesc");
	    	String [] exch_flag=req.getParameterValues("exchflag");
	    	String exg_rate_cdA=req.getParameter("exgcdA");
	    	String exg_rate_descA=req.getParameter("exgdescA");
	    	String exg_rate_flag=req.getParameter("exgflag");
	    	
	    	exg_rate_descA = obj.replaceSingleQuotes(exg_rate_descA);
     	
	        if(flagupdate.equalsIgnoreCase("M"))
	        {
	        	if(rate!= null)
	        	{
	        		for(int i = 0 ; i < exg_rate_desc.length ; i++)
	        		{
	        			exg_rate_desc[i] = obj.replaceSingleQuotes(exg_rate_desc[i]);
	        			try
	        			{
	        			/*SB20200427	query="UPDATE FMS7_CONT_EXCHG_RATE_MST "+
	        					  "SET EXC_RATE_NM='"+exg_rate_desc[i]+"',FLAG='"+exg_rate_flag+"', " +
	        					  "ENT_DT=sysdate, EMP_CD="+user_cd+", BANK_ABBR = '"+bank_name[i]+"' " +
	        					  "WHERE EXC_RATE_CD='"+exg_rate_cd[i]+"'";
	        					  */
	        				query="UPDATE FMS7_CONT_EXCHG_RATE_MST "+
		        					  "SET EXC_RATE_NM='"+exg_rate_desc[i]+"',FLAG='"+exch_flag[i]+"', " +
		        					  "ENT_DT=sysdate, EMP_CD="+user_cd+", BANK_ABBR = '"+bank_name[i]+"' " +
		        					  "WHERE EXC_RATE_CD='"+exg_rate_cd[i]+"'";
	        				
	        				stmt.executeUpdate(query);
	        				dbcon.commit();
	        			}
	        			catch(Exception e)
	        			{
	        				dbcon.rollback();
//	        				System.out.println("Exception:(Frm_Master)-(insertRates())-(FMS7_CONT_EXCHG_RATE_MST): "+e.getMessage());
	        				url="../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=master&formname=frm_rate_master.jsp&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
	        			}
	        		}
	        	}
	        	status="Master Entry: Exchange Rate Name Successfully Updated !!!";
	        	try
				{
					new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+status);
				}
				catch(Exception infoLogger)
				{
//					System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
					infoLogger.printStackTrace();
				}
	        }
	        else if(flagupdate.equalsIgnoreCase("A"))
	        {
	        	if(rate!= null)
	        	{
	        		int count=0;
	        		try
 	       	    	{ 
 	   	    	    	query="Select count(exc_rate_cd) from FMS7_CONT_EXCHG_RATE_MST";
 	   	    	    	rset=stmt.executeQuery(query);
 	   	    	    	if(rset.next())
 	   	    	    	{
 	   	    	    		count=rset.getInt(1);
 	   	    	    		count++;
 	   	    	    	}
 	   	    	    	query="INSERT INTO FMS7_CONT_EXCHG_RATE_MST(EXC_RATE_CD,EXC_RATE_NM,FLAG,ENT_DT,EMP_CD,BANK_ABBR) "
 	   	    	    			+ "VALUES ('"+count+"','"+exg_rate_descA+"','Y',sysdate,"+user_cd+",'"+bank_nameA+"')";
 	   	    	    	stmt.executeUpdate(query);
 	   	    	    	dbcon.commit();
 	       	    	}
 	       	      	catch(Exception e)
 	       	      	{
 	       	      		dbcon.rollback();
// 	       	      		System.out.println("Exception:(Frm_Master)-(insertRates())-(FMS7_CONT_EXCHG_RATE_MST): "+e.getMessage());
 	       	      		url="../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=master&formname=frm_rate_master.jsp&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
 	       	      	}
	        	}
	        	status="Master Entry: Exchange Rate Name Successfully Inserted !!!";
	        	try
				{
					new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+status);
				}
				catch(Exception infoLogger)
				{
//					System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
					infoLogger.printStackTrace();
				}
     	   	}
     	}
     	url="../master/frm_rate_master.jsp?flag=M&opt=7&rate="+rate+"&status="+status+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;     	
    }
	
	
	//Data From frm_Int_Exchg_Rate_Entry.jsp ...
	public void saveDailyIntExchgRateEntry(HttpServletRequest request) throws SQLException
	{
		methodName = "saveDailyIntExchgRateEntry()";
		form_name = "frm_Int_Exchg_Rate_Entry.jsp";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
				
		String month = request.getParameter("month")==null?"0":request.getParameter("month");
	    String year = request.getParameter("year")==null?"0":request.getParameter("year");
		String rate = request.getParameter("rate")==null?"":request.getParameter("rate");
		String comp_cd = request.getParameter("intcd")==null?"":request.getParameter("intcd");
		
		String [] eff_dt_arr = request.getParameterValues("effdtA");
	    String [] rate_val_arr = request.getParameterValues("comp_val");
	    String [] currency_arr = request.getParameterValues("currency");
	    String [] currency_from_arr = request.getParameterValues("currency_from");
	    String [] remark_arr = request.getParameterValues("remark");
	    
	    String ent_by = (String)session.getAttribute("user_cd");
	    String message = "";	    
	    
		try
		{
		    if(rate.equalsIgnoreCase("Instrate"))
		    {
		    	for(int i=0; i<eff_dt_arr.length; i++)
		    	{
			    	String eff_dt = eff_dt_arr[i];
				    String rate_val = rate_val_arr[i];
				    String currency = "1";
				    String remark = remark_arr[i];
				    
				    remark = obj.replaceSingleQuotes(remark);
				    
			    	query = "select count(*) from fms7_int_pay_rate_entry where INT_RATE_CD='"+comp_cd+"' and EFF_DT=to_date('"+eff_dt+"','dd/mm/yyyy')";
			    	rset = stmt.executeQuery(query);
			    	int cnt = 0;
			    	if(rset.next())
			    	{
			    		cnt = rset.getInt(1);
			    	}
			    	if(cnt==0)
			    	{
			    		if(rate_val!=null && !rate_val.trim().equals(""))
			    		{
			    			if(Double.parseDouble(rate_val)>0)
			    			{
					    		query = "insert into fms7_int_pay_rate_entry(INT_RATE_CD, EFF_DT, INT_VAL, CURRENCY_CD, FLAG, REMARK, EMP_CD, ENT_DT) " +
							    		" values('"+comp_cd+"',to_date('"+eff_dt+"','dd/mm/yyyy'),'"+rate_val+"','"+currency+"','Y','"+remark+"','"+ent_by+"',sysdate)" ; 
							    stmt.executeUpdate(query);
							    message = "Interest Rate Successfully Inserted For The Selected Month: "+month+" And Year: "+year+" !!!";
			    			}
			    		}
					}
			    	else
			    	{
			    		if(rate_val!=null && !rate_val.trim().equals(""))
			    		{
			    			if(Double.parseDouble(rate_val)>0)
			    			{
					    		query = "update fms7_int_pay_rate_entry set INT_VAL='"+rate_val+"',  CURRENCY_CD='"+currency+"', FLAG='Y', REMARK='"+remark+"', EMP_CD='"+ent_by+"', ENT_DT=sysdate " +
					    		  		" where INT_RATE_CD= '"+comp_cd+"' and EFF_DT = to_date('"+eff_dt+"','dd/mm/yyyy') ";
					    		stmt.executeUpdate(query);
					    		message = "Interest Rate Successfully Modified For The Selected Month: "+month+" And Year: "+year+" !!!";
			    			}
			    		}
			    	}
		    	}
		    }
		    else if(rate.equalsIgnoreCase("Exgrate"))
		    {
		    	for(int i=0; i<eff_dt_arr.length; i++)
		    	{
			    	String eff_dt = eff_dt_arr[i];
				    String rate_val = rate_val_arr[i];
				    String currency = currency_arr[i];
				    String currency_from = currency_from_arr[i];
				    String remark = remark_arr[i];
				    
				    remark = obj.replaceSingleQuotes(remark);
				    
			    	String tt_selling_cd = "2";
			    	String tt_buying_cd = "3";
			    	String avg_tt_buy_sell_cd = "6";
			    	
			    	//////////////////////////SB20200428: Find the Last Rate of SBI(after 3PM)///////////////////
			    	int TodayTime=0; String CurrTime=""; String AVR_TT_BUY_SELL_CD_LastRate="6"; String AVR_TT_BUY_SELL_CD_FirstRate="9";
			    	String BankAbr=""; String ExchCode="";
			    	String queryString="SELECT exc_rate_cd,exc_rate_nm,flag,nvl(bank_abbr,'') FROM fms7_cont_exchg_rate_mst where flag!='X' order by exc_rate_cd";
					rset=stmt.executeQuery(queryString);
					while(rset.next())
					{
						ExchCode=rset.getString(1);
						BankAbr=rset.getString(4)==null?"":rset.getString(4);
						if(BankAbr.contains("Last Rate"))
						{
							AVR_TT_BUY_SELL_CD_LastRate = ExchCode;//System.out.println("AVR_TT_BUY_SELL_CD_LastRate: "+AVR_TT_BUY_SELL_CD_LastRate); 
						}
						else if(BankAbr.contains("First Rate"))
						{
							AVR_TT_BUY_SELL_CD_FirstRate = ExchCode;//System.out.println("AVR_TT_BUY_SELL_CD_FirstRate: "+AVR_TT_BUY_SELL_CD_FirstRate); 
						}
						ExchCode="";
					}
			    	
			    	queryString = "SELECT TO_CHAR(sysdate,'HH24:MI') FROM DUAL";
				//	System.out.println("Query for generating Last Date of the selected Month = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						CurrTime = rset.getString(1);//System.out.println("CurrTime: "+CurrTime); 
					}
					String CurrTimeTemp[]=CurrTime.split(":");
					TodayTime=Integer.parseInt(CurrTimeTemp[0]);//System.out.println("TodayTime: "+TodayTime); 
			    	if(TodayTime>15)
			    		avg_tt_buy_sell_cd = AVR_TT_BUY_SELL_CD_LastRate;
			    	else
			    		avg_tt_buy_sell_cd = AVR_TT_BUY_SELL_CD_FirstRate;
			    	/////////////////////////////////////////////////////////////////////////////////////////////
			    	String tt_selling_rate = "0";
			    	String tt_buying_rate = "0";
			    	
			    	query = "select count(*) from fms7_exchg_rate_entry where EXCHG_RATE_CD='"+comp_cd+"' " +
			    			"and EFF_DT=to_date('"+eff_dt+"','dd/mm/yyyy') " +
			    			"and CURRENCY_CD='"+currency+"' and CURRENCY_CD_FROM='"+currency_from+"'";
			   // 	System.out.println("STEP-1:EXCHG: "+query);
			    	rset = stmt.executeQuery(query);
			    	int cnt =0;
			    	if(rset.next())
			    	{
			    		cnt = rset.getInt(1);
			    	}
			    	if(cnt==0)
			    	{
			    		if(rate_val!=null && !rate_val.trim().equals(""))
			    		{
			    			if(Double.parseDouble(rate_val)>0)
			    			{
					    		query = "insert into fms7_exchg_rate_entry(EXCHG_RATE_CD, EFF_DT, EXCHG_VAL, CURRENCY_CD, FLAG, REMARK, CURRENCY_CD_FROM, ENT_BY, ENT_DT) " +
					    			    "values('"+comp_cd+"',to_date('"+eff_dt+"','dd/mm/yyyy'),"+nf.format(Double.parseDouble(rate_val))+",'"+currency+"','Y','"+remark+"','"+currency_from+"','"+ent_by+"',sysdate)" ; 
					    		//System.out.println("STEP-2:EXCHG:insert: "+query);
					    		stmt.executeUpdate(query); 
					    	    message = "Exchange Rate Successfully Inserted For The Selected Month: "+month+" And Year: "+year+" !!!";
			    			}
			    		}
			    	}
			    	else
			    	{
			    		if(rate_val!=null && !rate_val.trim().equals(""))
			    		{
			    			if(Double.parseDouble(rate_val)>0)
			    			{
					    		query = "update fms7_exchg_rate_entry set EXCHG_VAL="+nf.format(Double.parseDouble(rate_val))+",  FLAG='Y', REMARK='"+remark+"', ENT_BY='"+ent_by+"', ENT_DT=sysdate " +
				  		  			    "where EXCHG_RATE_CD='"+comp_cd+"' and EFF_DT=to_date('"+eff_dt+"','dd/mm/yyyy') and CURRENCY_CD='"+currency+"' and CURRENCY_CD_FROM='"+currency_from+"'";
					    		//System.out.println("STEP-2A:EXCHG:update: "+query);
					    		stmt.executeUpdate(query);
				  		        message = "Exchange Rate Successfully Modified For The Selected Month: "+month+" And Year: "+year+" !!!";
			    			}
			    		}
		  		    }
			    	
			    	
			    	if(rate_val!=null && !rate_val.trim().equals(""))
		    		{
		    			if(Double.parseDouble(rate_val)>0)
		    			{
					    	if(comp_cd.equals(tt_selling_cd) || comp_cd.equals(tt_buying_cd))
					    	{
					    		tt_selling_rate = "0";
					    		tt_buying_rate = "0";
					    		
					    		query = "SELECT NVL(exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY WHERE EXCHG_RATE_CD="+tt_selling_cd+" " +
			    						"AND eff_dt=to_date('"+eff_dt+"','dd/mm/yyyy')";
					    	//	System.out.println("STEP-3:EXCHG:SELECT: "+query);
					    		rset = stmt.executeQuery(query);
					    		if(rset.next())
						    	{
					    			tt_selling_rate = rset.getString(1);
						    	}
					    		
					    		query = "SELECT NVL(exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY WHERE EXCHG_RATE_CD="+tt_buying_cd+" " +
										"AND eff_dt=to_date('"+eff_dt+"','dd/mm/yyyy')";
					    	//	System.out.println("STEP-3A:EXCHG:SELECT: "+query);
					    		rset = stmt.executeQuery(query);
					    		if(rset.next())
						    	{
					    			tt_buying_rate = rset.getString(1);
						    	}
					    		
					    		if(Double.parseDouble(tt_selling_rate)>0 && Double.parseDouble(tt_buying_rate)>0)
					    		{
					    			double avg_tt_sell_buy_amt = (Double.parseDouble(tt_selling_rate)+Double.parseDouble(tt_buying_rate))/2.0;
					    			
					    			if(avg_tt_sell_buy_amt>0)
					    			{
					    				rate_val = nf.format(avg_tt_sell_buy_amt);
					    			
						    			query = "select count(*) from fms7_exchg_rate_entry where EXCHG_RATE_CD="+avg_tt_buy_sell_cd+" " +
								    			"and EFF_DT=to_date('"+eff_dt+"','dd/mm/yyyy') " +
								    			"and CURRENCY_CD='"+currency+"' and CURRENCY_CD_FROM='"+currency_from+"'";
						    		//	System.out.println("STEP-3A:EXCHG:SELECT: "+query);
								    	rset = stmt.executeQuery(query);
								    	int count =0;
								    	if(rset.next())
								    	{
								    		count = rset.getInt(1);
								    	}
								    	
								    	if(count==0)
								    	{
								    		remark = "Avg. Of TT Selling-Buying Rate On "+eff_dt;
								    		query = "insert into fms7_exchg_rate_entry(EXCHG_RATE_CD, EFF_DT, EXCHG_VAL, CURRENCY_CD, FLAG, REMARK, CURRENCY_CD_FROM, ENT_BY, ENT_DT) " +
								    			    "values("+avg_tt_buy_sell_cd+",to_date('"+eff_dt+"','dd/mm/yyyy'),"+rate_val+",'"+currency+"','Y','"+remark+"','"+currency_from+"',"+ent_by+",sysdate)" ; 
								    	//	System.out.println("STEP-4:EXCHG:insert: "+query);
								    		stmt.executeUpdate(query); 
								    	    message = "Exchange Rate Successfully Inserted For The Selected Month: "+month+" And Year: "+year+" !!!";
								    	}
								    	else
								    	{
								    		remark = "Avg. Of TT Selling-Buying Rate On "+eff_dt;
								    		query = "update fms7_exchg_rate_entry set EXCHG_VAL="+rate_val+",  FLAG='Y', REMARK='"+remark+"', ENT_BY="+ent_by+", ENT_DT=sysdate " +
							  		  			    "where EXCHG_RATE_CD="+avg_tt_buy_sell_cd+" and EFF_DT=to_date('"+eff_dt+"','dd/mm/yyyy') and CURRENCY_CD='"+currency+"' and CURRENCY_CD_FROM='"+currency_from+"'";
								    //		System.out.println("STEP-4A:EXCHG:update: "+query);
								    		stmt.executeUpdate(query);
							  		        message = "Exchange Rate Successfully Modified For The Selected Month: "+month+" And Year: "+year+" !!!";
							  		    }
					    			}
					    		}
					    	}
		    			}
		    		}
		    	}
		    }
		    
		    url="../master/frm_Int_Exchg_Rate_Entry.jsp?rate="+rate+"&comp_cd="+comp_cd+"&month="+month+"&year="+year+"&message="+message+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		    dbcon.commit();
		    
		    try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+message);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			message = "Rate NOT Inserted/Modified !!!";
			System.out.println("Exception:(Frm_Master)-(saveDailyIntExchgRateEntry())-(FMS7_INT_PAY_RATE_ENTRY/FMS7_EXCHG_RATE_ENTRY): "+e.getMessage());
			e.printStackTrace();
			url="../master/frm_Int_Exchg_Rate_Entry.jsp?rate="+rate+"&comp_cd="+comp_cd+"&month="+month+"&year="+year+"&message="+message+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	
	//Data From frm_ship_mst.jsp ...
	public void InsertModifyShipDetails(HttpServletRequest request) throws SQLException
	{
		form_name = "frm_ship_mst.jsp";
		methodName="InsertModifyShipDetails()";
		String ship_cd=request.getParameter("ship_cd")==null?"0":request.getParameter("ship_cd");
		String message = "";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		
		try
		{
			HttpSession session = request.getSession();
			int ship_code = 0;
			
			String activity=request.getParameter("activity")==null?"insert":request.getParameter("activity");
			String ship_nm=request.getParameter("ship_nm")==null?"":request.getParameter("ship_nm");
			String ship_call_sign=request.getParameter("ship_call_sign")==null?"":request.getParameter("ship_call_sign");
			String ship_flag=request.getParameter("ship_flag")==null?"":request.getParameter("ship_flag");
			String ship_imo_no=request.getParameter("ship_imo_no")==null?"":request.getParameter("ship_imo_no");
			String ship_class_soc=request.getParameter("ship_class_soc")==null?"":request.getParameter("ship_class_soc");
			String ship_inmarsat_no=request.getParameter("ship_inmarsat_no")==null?"":request.getParameter("ship_inmarsat_no");
			String ship_owner_nm=request.getParameter("ship_owner_nm")==null?"":request.getParameter("ship_owner_nm");
			String ship_operator_nm=request.getParameter("ship_operator_nm")==null?"":request.getParameter("ship_operator_nm");
			String ship_fax_no=request.getParameter("ship_fax_no")==null?"":request.getParameter("ship_fax_no");
			String ship_telex_no=request.getParameter("ship_telex_no")==null?"":request.getParameter("ship_telex_no");
			String ship_email=request.getParameter("ship_email")==null?"":request.getParameter("ship_email");
			String ship_gross_tonnage=request.getParameter("ship_gross_tonnage");
			String ship_cargo_capacity=request.getParameter("ship_cargo_capacity");
			String ship_volume_unit=request.getParameter("ship_volume_unit")==null?"0":request.getParameter("ship_volume_unit");
			String ship_item=request.getParameter("ship_item")==null?"LNG":request.getParameter("ship_item");
			String ship_liquefac_plant=request.getParameter("ship_liquefac_plant")==null?"":request.getParameter("ship_liquefac_plant");
			String ship_liquefac_country=request.getParameter("ship_liquefac_country")==null?" ":request.getParameter("ship_liquefac_country");
			String ship_liquefac_promotor=request.getParameter("ship_liquefac_promotor")==null?"":request.getParameter("ship_liquefac_promotor");
			String ship_remark=request.getParameter("ship_remark")==null?"":request.getParameter("ship_remark");
			String percentage_capacity = request.getParameter("percentage_capacity");
			
			ship_nm = obj.replaceSingleQuotes(ship_nm);
			ship_call_sign = obj.replaceSingleQuotes(ship_call_sign);
			ship_flag = obj.replaceSingleQuotes(ship_flag);
			ship_class_soc = obj.replaceSingleQuotes(ship_class_soc);
			ship_owner_nm = obj.replaceSingleQuotes(ship_owner_nm);
			ship_operator_nm = obj.replaceSingleQuotes(ship_operator_nm);
			ship_item = obj.replaceSingleQuotes(ship_item);
			ship_liquefac_plant = obj.replaceSingleQuotes(ship_liquefac_plant);
			ship_liquefac_promotor = obj.replaceSingleQuotes(ship_liquefac_promotor);
			ship_remark = obj.replaceSingleQuotes(ship_remark);
			
			String ent_by=(String)session.getAttribute("user_cd");
		    		    
		    if(ship_gross_tonnage.equals("") || ship_gross_tonnage.equals(" ") || ship_gross_tonnage.equals("0"))
		    {
		    	ship_gross_tonnage = null;
		    }
		    if(ship_cargo_capacity.equals("") || ship_cargo_capacity.equals(" ") || ship_cargo_capacity.equals("0"))
		    {
		    	ship_cargo_capacity = null;
		    }
		    if(ship_volume_unit.equals("") || ship_volume_unit.equals(" "))
		    {
		    	ship_volume_unit = "0";
		    }
		    if(percentage_capacity.equals("") || percentage_capacity.equals(" ") || percentage_capacity.equals("0"))
		    {
		    	percentage_capacity = null;
		    }
		    
		    if(activity.equalsIgnoreCase("insert"))
		    {
		    	query = "SELECT NVL(MAX(ship_cd),'100000') FROM FMS7_SHIP_MST";
		    	rset = stmt.executeQuery(query);
		    	if(rset.next())
		    	{
		    		ship_code = rset.getInt(1)+1;
		    	}
		    	else
		    	{
		    		ship_code = 100000+1;
		    	}
		    	
		    	query = "INSERT INTO FMS7_SHIP_MST(ship_cd, ship_name, ship_call_sign, ship_flag, ship_imo_no, ship_class_soc, " +
		    			"inmarsat_no, ship_owner_name, ship_operator_name, ship_fax_no, ship_telex_no, ship_email, " +
		    			"gross_tonnage, cargo_capacity, volume_unit, ship_item, liquefaction_plant, liquefaction_country, " +
		    			"liquefaction_promotor, percentage_capacity, remark, flag, emp_cd, ent_dt) " +
				   		"VALUES("+ship_code+",'"+ship_nm+"','"+ship_call_sign+"','"+ship_flag+"','"+ship_imo_no+"','"+ship_class_soc+"'," +
				   		"'"+ship_inmarsat_no+"','"+ship_owner_nm+"','"+ship_operator_nm+"','"+ship_fax_no+"','"+ship_telex_no+"'," +
				   		"'"+ship_email+"',"+ship_gross_tonnage+","+ship_cargo_capacity+","+ship_volume_unit+",'"+ship_item+"'," +
				   		"'"+ship_liquefac_plant+"','"+ship_liquefac_country+"','"+ship_liquefac_promotor+"',"+percentage_capacity+"," +
				   		"'"+ship_remark+"','Y','"+ent_by+"',sysdate)"; 
				stmt.executeUpdate(query);
				message = "Ship Details Successfully Inserted For Ship: "+ship_nm+" !!!";
			}
		    else if(activity.equalsIgnoreCase("update"))
		    {
		    	ship_code = Integer.parseInt(ship_cd);
		    	
		    	query = "UPDATE FMS7_SHIP_MST SET ship_name='"+ship_nm+"', ship_call_sign='"+ship_call_sign+"', ship_flag='"+ship_flag+"', " +
		    			"ship_imo_no='"+ship_imo_no+"', ship_class_soc='"+ship_class_soc+"', " +
		    			"inmarsat_no='"+ship_inmarsat_no+"', ship_owner_name='"+ship_owner_nm+"', " +
		    			"ship_operator_name='"+ship_operator_nm+"', ship_fax_no='"+ship_fax_no+"', " +
		    			"ship_telex_no='"+ship_telex_no+"', ship_email='"+ship_email+"', " +
		    			"gross_tonnage="+ship_gross_tonnage+", cargo_capacity="+ship_cargo_capacity+", volume_unit="+ship_volume_unit+", " +
		    			"ship_item='"+ship_item+"', liquefaction_plant='"+ship_liquefac_plant+"', liquefaction_country='"+ship_liquefac_country+"', " +
		    			"liquefaction_promotor='"+ship_liquefac_promotor+"',percentage_capacity="+percentage_capacity+"," +
		    			"remark='"+ship_remark+"', flag='Y', emp_cd="+ent_by+", ent_dt=sysdate " +
	  		  		    "WHERE ship_cd="+ship_code+"";
	  		    stmt.executeUpdate(query);
	  		    message = "Ship Details Successfully Modified For Ship: "+ship_nm+" !!!";
	  		}
		    
		    url="../master/frm_ship_mst.jsp?activity=update&bscode="+ship_code+"&msg="+message+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		    dbcon.commit();
		    
		    try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+message);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			message = "Ship Details NOT Inserted/Modified !!!";
			System.out.println("Exception:(Frm_Master)-(InsertModifyShipDetails())-(FMS7_SHIP_MST): "+e.getMessage());
			e.printStackTrace();
			url="../master/frm_ship_mst.jsp?activity=update&bscode="+ship_cd+"&msg="+message+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	public void BankInsertModifyDetails(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName="BankInsertModifyDetails()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession sess = request.getSession();
		HttpSession session = request.getSession();
					 
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String comp_cd = (String)sess.getAttribute("comp_cd")==null?"":(String)sess.getAttribute("comp_cd");

		String cd = request.getParameter("cd")==null?"0":request.getParameter("cd");
		String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
		String credit_rating = request.getParameter("credit_rating")==null?"":request.getParameter("credit_rating");
		String branch_name = request.getParameter("branch_name")==null?"":request.getParameter("branch_name");
		String addr = request.getParameter("addr")==null?"":request.getParameter("addr");
		String city = request.getParameter("city")==null?"":request.getParameter("city");
		String state = request.getParameter("state")==null?"":request.getParameter("state");
		String pin = request.getParameter("pin")==null?"":request.getParameter("pin");
		String country = request.getParameter("country")==null?"":request.getParameter("country");
		String phone = request.getParameter("phone")==null?"":request.getParameter("phone");
		String alt_phone = request.getParameter("alt_phone")==null?"":request.getParameter("alt_phone");
		String cell = request.getParameter("cell")==null?"":request.getParameter("cell");
		String fax1 = request.getParameter("fax1")==null?"":request.getParameter("fax1");
		String fax2 = request.getParameter("fax2")==null?"":request.getParameter("fax2");
		String email = request.getParameter("email")==null?"":request.getParameter("email");
		String remarks = request.getParameter("notes")==null?"":request.getParameter("notes");
		String branch_ifsc_cd = request.getParameter("branch_ifsc_cd")==null?"":request.getParameter("branch_ifsc_cd");
		
		name = obj.replaceSingleQuotes(name);
		branch_name = obj.replaceSingleQuotes(branch_name);
		addr = obj.replaceSingleQuotes(addr);
		city = obj.replaceSingleQuotes(city);
		state = obj.replaceSingleQuotes(state);
		remarks = obj.replaceSingleQuotes(remarks);
		
		int tempBankCode=1001;
		query="select nvl(max(BANK_CD),'1000') from FMS7_BANK_MST";	
		rset = stmt.executeQuery(query);
		if(rset.next())
		{
			tempBankCode =rset.getInt(1)+1;
		}
		
		String msg = "";
		
		try
		{
			if(flg.equals("insert"))
			{
				query="INSERT INTO FMS7_BANK_MST (BANK_CD,BANK_NAME,EFF_DT,BRANCH_NAME,CREDIT_RATING,ADDR,CITY,PIN,STATE,COUNTRY,PHONE,MOBILE,ALT_PHONE,FAX_1,FAX_2,EMAIL,REMARK,EMP_CD,ENT_DT,BRANCH_IFSC_CD) VALUES('"+tempBankCode+"','"+name+"',to_date('"+eff_dt+"','dd/mm/yyyy'),'"+branch_name+"','"+credit_rating+"','"+addr+"','"+city+"','"+pin+"','"+state+"','"+country+"','"+phone+"','"+cell+"','"+alt_phone+"','"+fax1+"','"+fax2+"','"+email+"','"+remarks+"','"+user_cd+"',to_date(sysdate,'dd/mm/yyyy'),'"+branch_ifsc_cd+"')";
				System.out.println("Bank Insert Query = "+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Bank Details Submitted Successfully For The Bank: "+name+" !!!";
				url="../master/frm_bank_mst.jsp?msg="+msg+"&bscode="+tempBankCode+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			else if(flg.equals("update"))
			{
				query="UPDATE FMS7_BANK_MST SET " +
				"BANK_NAME='"+name+"',EFF_DT = to_date('"+eff_dt+"','dd/mm/yyyy'),BRANCH_NAME='"+branch_name+"',CREDIT_RATING='"+credit_rating+"',ADDR='"+addr+"',CITY='"+city+"',PIN='"+pin+"',STATE='"+state+"',COUNTRY='"+country+"',PHONE='"+phone+"',MOBILE='"+cell+"',ALT_PHONE='"+alt_phone+"',FAX_1='"+fax1+"',FAX_2='"+fax2+"',EMAIL='"+email+"',REMARK='"+remarks+"',EMP_CD='"+user_cd+"',ENT_DT=to_date(sysdate,'dd/mm/yyyy'),BRANCH_IFSC_CD='"+branch_ifsc_cd+"' WHERE BANK_CD='"+cd+"'";
				System.out.println("Bank Modify Query :"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Bank Details Modified Successfully For The Bank: "+name+" !!!";
				url="../master/frm_bank_mst.jsp?msg="+msg+"&bscode="+cd+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;				
			}
			
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
			msg="Bank Details Not Submitted !!!";
			e.printStackTrace();
			System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
			url="../master/frm_bank_mst.jsp?msg="+msg+"&flg=insert&activity=insert&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}
	
	
	public void GovtStatTaxNamesInsertDetails(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "GovtStatTaxNamesInsertDetails()";
		escapeSingleQuotes obj = new escapeSingleQuotes();
		HttpSession sess = request.getSession();
		HttpSession session = request.getSession();
					 
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String comp_cd = (String)sess.getAttribute("comp_cd")==null?"":(String)sess.getAttribute("comp_cd");

		String stat_nm = request.getParameter("stat_nm")==null?"":request.getParameter("stat_nm");
		String stat_type = request.getParameter("stat_type")==null?"S":request.getParameter("stat_type");
		String stat_status = request.getParameter("stat_status")==null?"Y":request.getParameter("stat_status");
		String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");
		
		stat_nm = obj.replaceSingleQuotes(stat_nm);
		remarks = obj.replaceSingleQuotes(remarks);
		
		int statCode = 1001;
		
		query = "select nvl(max(STAT_CD),'1000') from FMS7_GOVT_STAT_NO";	
		rset = stmt.executeQuery(query);
		if(rset.next())
		{
			statCode = rset.getInt(1)+1;
		}
		
		String msg = "";
		
		try
		{
			query = "INSERT INTO FMS7_GOVT_STAT_NO(STAT_CD,STAT_NM,STAT_TYPE,FLAG,REMARK,EMP_CD,ENT_DT) " +
					"VALUES('"+statCode+"','"+stat_nm+"','"+stat_type+"','"+stat_status+"','"+remarks+"','"+user_cd+"',sysdate)";
			System.out.println("Govt. Statutory Tax Name Insert Query = "+query);
			stmt.executeUpdate(query);
			dbcon.commit();
			msg = "New Govt. Statutory Tax Name --> "+stat_nm+" Created Successfully !!!";
			url = "../master/frm_govt_stat_no_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
						
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
			msg = "Creation Of New Govt. Statutory Tax Name Failed !!!";
			System.out.println("Exception in the "+methodName+" of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();
			url="../master/frm_govt_stat_no_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}
	
	
	public void GovtStatTaxNamesModifyDetails(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName="GovtStatTaxNamesModifyDetails()";
		
		escapeSingleQuotes obj = new escapeSingleQuotes();
		HttpSession sess = request.getSession();
		HttpSession session = request.getSession();
					 
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String comp_cd = (String)sess.getAttribute("comp_cd")==null?"":(String)sess.getAttribute("comp_cd");

		String [] stat_cd = request.getParameterValues("stat_cd_arr");
		String [] stat_nm = request.getParameterValues("stat_nm_arr");
		String [] stat_type = request.getParameterValues("stat_type_arr");
		String [] stat_status = request.getParameterValues("stat_status_arr");
		String [] remarks = request.getParameterValues("remarks_arr");
		
		String msg = "";
		
		try
		{
			for(int i=0; i<stat_cd.length; i++)
			{
				stat_nm[i] = obj.replaceSingleQuotes(stat_nm[i]);
				remarks[i] = obj.replaceSingleQuotes(remarks[i]);
				
				query = "UPDATE FMS7_GOVT_STAT_NO SET STAT_NM='"+stat_nm[i]+"',STAT_TYPE='"+stat_type[i]+"'," +
						"FLAG='"+stat_status[i]+"',REMARK='"+remarks[i]+"'," +
						"EMP_CD='"+user_cd+"',ENT_DT=sysdate WHERE STAT_CD='"+stat_cd[i]+"'";
				System.out.println("Govt. Statutory Tax Name Insert Query = "+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg = "Govt. Statutory Tax Name(s) Updated Successfully !!!";
				url = "../master/frm_govt_stat_no_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			
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
			msg="Govt. Statutory Tax Name(s) Updation Failed !!!";
			System.out.println("Exception in the "+methodName+" of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();
			url="../master/frm_govt_stat_no_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}
	public void SectorNamesInsertDetails(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "SectorNamesInsertDetails()";
		escapeSingleQuotes obj = new escapeSingleQuotes();
		HttpSession sess = request.getSession();
		HttpSession session = request.getSession();
					 
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String comp_cd = (String)sess.getAttribute("comp_cd")==null?"":(String)sess.getAttribute("comp_cd");

		String stat_nm = request.getParameter("sec_nm")==null?"":request.getParameter("sec_nm");
		String stat_type = request.getParameter("sec_abr")==null?"S":request.getParameter("sec_abr");
		String stat_status = request.getParameter("sec_status")==null?"Y":request.getParameter("sec_status");
		String ent_dt= request.getParameter("sec_dt")==null?"":request.getParameter("sec_dt");
		
		stat_nm = obj.replaceSingleQuotes(stat_nm);
			
		int statCode = 1001;
		
		query = "select max(SECTOR_CD) from FMS7_SECTOR_MST";	
		rset = stmt.executeQuery(query);
		if(rset.next())
		{
			statCode = rset.getInt(1)+1;
		}
		
		String msg = "";
		
		try
		{
			query = "INSERT INTO FMS7_SECTOR_MST(SECTOR_CD,SECTOR_NAME,SECTOR_ABBR,FLAG,EMP_CD,ENT_DT) " +
					"VALUES('"+statCode+"','"+stat_nm+"','"+stat_type+"','"+stat_status+"','"+user_cd+"', to_date('"+ent_dt+"','dd/mm/yyyy'))";
			System.out.println("Sector Insert Query = "+query);
			stmt.executeUpdate(query);
			dbcon.commit();
			msg = "New Sector Name --> "+stat_nm+" Created Successfully !!!";
			url = "../master/frm_sector_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
						
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
			msg = "Creation Of New Sector Name Failed !!!";
			System.out.println("Exception in the "+methodName+" of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();
			url="../master/frm_sector_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}
	
	public void SectorNamesModifyDetails(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName="SectorNamesModifyDetails()";
		
		escapeSingleQuotes obj = new escapeSingleQuotes();
		HttpSession sess = request.getSession();
		HttpSession session = request.getSession();
					 
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String comp_cd = (String)sess.getAttribute("comp_cd")==null?"":(String)sess.getAttribute("comp_cd");

		String [] stat_cd = request.getParameterValues("sec_cd_arr");
		String [] stat_nm = request.getParameterValues("sec_nm_arr");
		String [] stat_type = request.getParameterValues("sec_abr_arr");
		String [] stat_status = request.getParameterValues("sec_status_arr");
		String [] ent_dt = request.getParameterValues("ent_dt_arr");
		String [] priority = request.getParameterValues("priority");
		
		String msg = "";
		String message1="";
		try
		{
			for(int i=0; i<stat_nm.length; i++)
			{
				stat_nm[i] = obj.replaceSingleQuotes(stat_nm[i]);
				
				query = "UPDATE FMS7_SECTOR_MST SET SECTOR_NAME='"+stat_nm[i]+"',SECTOR_ABBR='"+stat_type[i]+"'," +
						"FLAG='"+stat_status[i]+"',ENT_DT = to_date('"+ent_dt[i]+"','dd/mm/yyyy')," +
						"EMP_CD='"+user_cd+"',PRIORITY='"+priority[i]+"' WHERE SECTOR_CD='"+stat_cd[i]+"'";
				System.out.println("Sector Name Modify Query = "+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				
				msg = "Data Successfully Updated ";	
				url = "../master/frm_sector_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			
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
			msg="Sector Name(s) Updation Failed !!!";
			System.out.println("Exception in the "+methodName+" of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();
			url="../master/frm_sector_mst.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}
	
}//End Of Class Frm_Master ...