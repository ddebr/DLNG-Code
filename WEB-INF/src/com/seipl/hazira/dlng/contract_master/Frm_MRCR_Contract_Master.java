package com.seipl.hazira.dlng.contract_master;
import java.io.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
//Code Reviewed by	: 
//CR Date			: 
//CR Status  		: 
@WebServlet("/servlet/Frm_MRCR_Contract_Master")
public class Frm_MRCR_Contract_Master extends HttpServlet
{ 
	public static  Connection dbcon;
	
	//Following NumberFormat Object is defined by Samik Shah ... On 4th August, 2010 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("###########0.000");
	NumberFormat nf3 = new DecimalFormat("###########0.0000");
	
	//Following Object Has Been Defined By Achal On 4th August, 2010 ...
	public escapeSingleQuotes snglQuot = new escapeSingleQuotes();
	
	
	public String servletName = "Frm_MRCR_Contract_Master";
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
			
			boolean isMultipart=ServletFileUpload.isMultipartContent(req);
			  if(isMultipart)
				{
				  createTable();
				  LC_MANUAL_ENTRY(req);
				}
			if(option.equalsIgnoreCase("LcMasterDtlFCC")) {
				LcMasterDtlFCC(req);
			}
			if(option.equalsIgnoreCase("TenderMaster"))
			{
				TenderMaster(req,res);
			}	
			if(option.equalsIgnoreCase("SNMaster"))
			{
				SNMaster(req);
			}
			if(option.equalsIgnoreCase("Request_For_TCQ_Modification"))
			{
				SN_TCQ_Modification_Request(req);
			}
			if(option.equalsIgnoreCase("LOA_Request_For_TCQ_Modification"))
			{
				LOA_TCQ_Modification_Request(req);
			}
			if(option.equalsIgnoreCase("Request_For_SN_Closure"))
			{
				SN_Closure_Request(req);
			}
			if(option.equalsIgnoreCase("Request_For_LOA_Closure"))//Added By Achal
			{
				LOA_Closure_Request(req);
			}
			if(option.equalsIgnoreCase("LOAMaster"))//Added By Manoj and Achal
			{
				LOAMaster(req);
			}			
			if(option.equalsIgnoreCase("Tender_BillingDetail"))//SB20111207  //Added By Manoj and Achal
			{
				Tender_BillingEntry(req);//SB20111207 
			}
			if(option.equalsIgnoreCase("SN_BillingDetail")) //SB20111207 //Added By Manoj and Achal
			{
				SN_BillingEntry(req); //SB20111207
			}
			if(option.equalsIgnoreCase("LOA_BillingDetail"))//SB20111207 //Added By Manoj and Achal
			{
				LOA_BillingEntry(req);//SB20111207
			}					
			if(option.equalsIgnoreCase("TENDER_LIABILITY"))//Added By Manoj and Achal
			{
				TENDER_LD_Liablility_Entry(req);
				TENDER_TOP_Liablility_Entry(req);
				TENDER_MUG_Liablility_Entry(req);
			}
			if(option.equalsIgnoreCase("SN_LIABILITY")) //Added By Manoj and Achal
			{
				SN_LD_Liablility_Entry(req);
				SN_TOP_Liablility_Entry(req);
				SN_MUG_Liablility_Entry(req);
			}
			if(option.equalsIgnoreCase("LOA_LIABILITY")) //Added By Manoj and Achal
			{
				LOA_LD_Liablility_Entry(req);
				LOA_TOP_Liablility_Entry(req);
				LOA_MUG_Liablility_Entry(req);
			}
			if(option.equalsIgnoreCase("LC_DETAILS_ENTRY_REGAS")) //Milan Dalsaniya 2011 oct 22
			{
			//System.out.println("OK");
				LC_DTL_ENTRY_REGAS(req);
			}
			if(option.equalsIgnoreCase("LC_DETAILS_ENTRY")) //Added By Manoj and Achal
			{
				LC_DTL_ENTRY(req);
			}
			if(option.equalsIgnoreCase("insertModifyMRPriceCurveDetails"))//SB20200908  //Introduced By Samik Shah On 13th July, 2010 ...
			{
				//HARSH20201201 insertModifySnDcqDetails(req);
				insertModifyDeleteSnDcqDetails(req);
			}
			if(option.equalsIgnoreCase("insertModifyLoaDcqDetails")) //Introduced By Achal On 9th August, 2010 ...
			{
				insertModifyLoaDcqDetails(req);
			}
			if(option.equalsIgnoreCase("FGSAAgreementMaster"))
			{
				FGSAAgreementMaster(req,res);
			}
			if(option.equalsIgnoreCase("BillingDetail"))//SB20111207 //Added By Manoj //FGSA Billing Details ...
			{				
				FGSA_BillingEntry(req); //SB20111207
			}
			if(option.equalsIgnoreCase("FGSA_LIABILITY"))//Added By Manoj
			{
				FGSA_LD_Liablility_Entry(req);
				FGSA_TOP_Liablility_Entry(req);
				FGSA_MUG_Liablility_Entry(req);
			}
			if(option.equalsIgnoreCase("Re_Gas_AgreementMaster"))
			{				
				Re_Gas_AgreementMaster(req,res);
			}
			if(option.equalsIgnoreCase("RE_GAS_LIABILITY"))//Added By ACHAL
			{
				RE_GAS_LD_Liablility_Entry(req);
				RE_GAS_TOP_Liablility_Entry(req);
				RE_GAS_MUG_Liablility_Entry(req);
			}
			if(option.equalsIgnoreCase("RE_GAS_BillingDetail"))//Added By ACHAL
			{
				//System.out.println("RE-GAS  ===>");
				RE_GAS_BillingEntry(req);				
			}
			if(option.equalsIgnoreCase("modifySnTcqCargoDetails")) //Introduced By Samik Shah On 4th August, 2010 ...
			{
				modifySnTcqCargoDetails(req); //SB20111103
			}
			if(option.equalsIgnoreCase("modifyLoaTcqCargoDetails")) //Introduced By Achal On 22nd March, 2011 ...
			{
				modifyLoaTcqCargoDetails(req);
			}
			
			if(option.equalsIgnoreCase("insertModifyReGasCargoDetails"))  //SB20110927
			{
				insertModifyReGasCargoDetails(req); //SB20110927
			}
			if(option.equalsIgnoreCase("LC_MASTER_DETAILS_ENTRY"))
			{
				insert_modify_LC_MASTER_DETAILS_ENTRY(req);
			}
			if(option.equalsIgnoreCase("insertModifyFgsaDeactivationDetails"))
			{
				insertModifyFgsaDeactivationDetails(req);
			}
			else if(option.equalsIgnoreCase("ACTUAL_LC_DETAILS"))
			{
				//System.out.println("ACTUAL_LC_DETAILS");
				insertModifyLCFinanceMaster(req);
			}
			else if(option.equalsIgnoreCase("ACTUAL_LC_DETAILS_REAGS")) //MILAN MD20111104
			{
				//System.out.println("ACTUAL_LC_DETAILS");
				insertModifyLCFinanceMasterREGAS(req);
			}
			else if(option.equalsIgnoreCase("SNMaster_FCC"))		//Priyanka 280111
			{
				updateSNMaster_FCC(req);
			}
			else if(option.equalsIgnoreCase("LOAMaster_FCC"))		//ACHAL 050311
			{
				updateLOAMaster_FCC(req);
			}
			else if(option.equalsIgnoreCase("Request_For_REGAS_Closure"))   //PRIYANKA 230311
			{
				update_REGAS_CARGO_DTL(req);
			}
			else if(option.equalsIgnoreCase("REGAS_Apply_Closure"))   //PRIYANKA 230311
			{
				close_REGAS_CARGO_DTL(req);
			}
			else if(option.equalsIgnoreCase("Delete_FGSA_Deactivation"))   //PRIYANKA 050411
			{
				delete_FGSA_deactivation(req);
			}
			else if(option.equalsIgnoreCase("Request_For_REGAS_Open"))   //JHP 20120502
			{
				update_REGAS_CARGO_DTL_REV(req);
			}
			else if(option.equalsIgnoreCase("FGSAAgreementMaster_Approval"))
			{
				FGSAAgreementMaster_Approval(req);
			}
			else if(option.equalsIgnoreCase("AdvanceCollection"))
			{
				AdvanceCollection(req);			//here Advance Collection
			}
			else if(option.equalsIgnoreCase("ExchangeRateEntry"))
			{
				ExchangeRateEntry(req);
			}
			else if(option.equals("ReOpenFGSA")) {
				ReOpenFGSA(req);
			} else if(option.equals("approveContract")) {
				approveContract(req);
			} else if(option.equals("ReOpenTender")) {
				ReOpenTender(req);
			}
			else if(option.equals("cargoModificationDtl")) {
				cargoModificationDtl(req); //SB20200326
			} 
			else if(option.equals("cargoApprovalDtl")) {
				cargoApprovalDtl(req);
				//SB20200401 	cargoApprovalDtl2(req); //SB20200401: On Price Change Approval RATE is made to ZERO to indicate the ChangeInPrice
			}
			else if(option.equals("cargoModificationDtl2")) //SB20200509
			{
				cargoModificationDtl2(req); //SB20200326
			} 
			else if(option.equals("cargoApprovalDtl2")) 
			{
				cargoApprovalDtl2(req);
				//SB20200401 	cargoApprovalDtl2(req); //SB20200401: On Price Change Approval RATE is made to ZERO to indicate the ChangeInPrice
			}
			else if(option.equals("requestChangePriceSN")) {
				requestChangePriceSN(req);
			}
			else if(option.equals("requestChangePriceLOA")) {
				requestChangePriceLOA(req);
			}
			else if(option.equals("Insert_Update_Transporter_Contract")) {
				Insert_Update_Transporter_Contract(req);
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
	
	public void createTable() throws Exception
	{
		try
		{
			int count=0;
			String query="select count(*) from tab where lower(tname)='fms8_lc_mst' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				String create="CREATE TABLE FMS8_LC_MST(CUSTOMER_CD NUMERIC(6,0) NOT NULL," +
						"FGSA_NO NUMBER(6) NOT NULL," +
						"FGSA_REV_NO NUMBER(3) NOT NULL," +
						"SN_NO NUMBER(6) NOT NULL," +
						"SN_REV_NO NUMBER(3) NOT NULL," +
						"CONTRACT_TYPE CHAR(1) NOT NULL,"
						+ "LC_AMOUNT NUMBER(18,2),"
						+ "LC_VALID_DATE DATE,"
						+ "BANK_DTL VARCHAR2(50),"
						+ "LC_FILE_NM VARCHAR2(100),"
						+ "REMARK VARCHAR2(100),"
						+ "ENT_BY NUMBER(6),"
						+ "ENT_DT DATE,"
						+ "FLAG CHAR(1),"
						+ "FCC_NOTE VARCHAR2(100),"
						+ "FCC_APPLY CHAR(1),"
						+ "FCC_ENT_BY NUMBER(6),"
						+ "FCC_ENT_DT DATE "+
						")";
				stmt.executeUpdate(create);
				dbcon.commit();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
		}
	}
	
	public void LcMasterDtlFCC(HttpServletRequest request) throws Exception {
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String note = request.getParameter("note")==null?"":request.getParameter("note");
		String lc_apply = request.getParameter("lc_apply")==null?"":request.getParameter("lc_apply");
		String contract_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
		
		try {
			methodName = "LcMasterDtlFCC()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			escapeSingleQuotes es = new escapeSingleQuotes();
			note = es.replaceSingleQuotes(note);
			
			queryString = "SELECT COUNT(FCC_NOTE) FROM FMS8_LC_MST WHERE CUSTOMER_CD='"+customer_cd+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' "
					+ "AND SN_NO='"+sn_no+"' AND SN_REV_NO='"+sn_rev_no+"' AND CONTRACT_TYPE='"+contract_type+"' ";
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				int count = rset.getInt(1);
				if(count>0) {
					 queryString = "UPDATE FMS8_LC_MST SET FCC_NOTE='"+note+"', FCC_APPLY='"+lc_apply+"', FCC_ENT_BY='"+user_cd+"',FCC_ENT_DT=SYSDATE "
						 		+ "WHERE CUSTOMER_CD='"+customer_cd+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' "
							 	+ "AND SN_NO='"+sn_no+"' AND SN_REV_NO='"+sn_rev_no+"' AND CONTRACT_TYPE='"+contract_type+"' ";
						 stmt.executeUpdate(queryString);
				} else {
					queryString = "INSERT INTO FMS8_LC_MST (CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONTRACT_TYPE,FCC_NOTE,FCC_APPLY,FCC_ENT_BY,FCC_ENT_DT) "
							+ "VALUES('"+customer_cd+"','"+fgsa_no+"','"+fgsa_rev_no+"','"+sn_no+"','"+sn_rev_no+"','"+contract_type+"','"+note+"','"+lc_apply+"','"+user_cd+"',SYSDATE) ";
					stmt.executeUpdate(queryString);
				}
			} else {
				queryString = "INSERT INTO FMS8_LC_MST (CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONTRACT_TYPE,FCC_NOTE,FCC_APPLY,FCC_ENT_BY,FCC_ENT_DT) "
						+ "VALUES('"+customer_cd+"','"+fgsa_no+"','"+fgsa_rev_no+"','"+sn_no+"','"+sn_rev_no+"','"+contract_type+"','"+note+"','"+lc_apply+"','"+user_cd+"',SYSDATE) ";
				stmt.executeUpdate(queryString);
			}
			 dbcon.commit();
			 msg="LC Details Updated Successfully!!";
			 url="../contract_master/frm_FGSA_LC_monitoring.jsp?flag=R&msg="+msg+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&cont_type="+contract_type+"&customer_cd="+customer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		} catch(Exception e) {
			dbcon.rollback();
			msg = "LC Details Not Updated !!";
			 url="../contract_master/frm_FGSA_LC_monitoring.jsp?flag=R&msg="+msg+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&cont_type="+contract_type+"&customer_cd="+customer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			e.printStackTrace();
		}
	}
	
public void LC_MANUAL_ENTRY(HttpServletRequest request) throws Exception { 
	String queryString = "";
	String sn_no="0",fgsa_no="0",fgsa_rev_no="0",sn_rev_no="0",customer_cd="",lc_amt="0",bankdtl="",lc_valid_date="",remark="",contract_type="",filePath="",fnm="",flag="N";
	try
	{
		methodName = "LC_MANUAL_ENTRY()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		escapeSingleQuotes es = new escapeSingleQuotes();
		
		List items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);  
		Iterator itr=items.iterator();
		 
		String filepath=request.getRealPath("/lc_files");
		int count=0;
		
		String[] file_bunch_qtr= null;
		
		FileItem item=null; FileItem item1 = null;
		 while(itr.hasNext()) {
			 count++;
			 item=(FileItem)itr.next();
			  String fieldname = item.getFieldName();
                String itemName=item.getName();
	            	if (item.isFormField()) {
	            			if(fieldname.equals("customer_cd")) {
	            				customer_cd = item.getString();
	            			}
		            		if(fieldname.equals("sn_no"))
			            	{
		            			sn_no = item.getString();
			            	}
		            		if(fieldname.equals("sn_rev_no"))
			            	{
		            			sn_rev_no = item.getString();
			            	}
		            		if(fieldname.equals("fgsa_no"))
			            	{
		            			fgsa_no = item.getString();
			            	}
		            		if(fieldname.equals("fgsa_rev_no"))
			            	{
		            			fgsa_rev_no = item.getString();
			            	}
		            		if(fieldname.equals("cont_type"))
			            	{
		            			contract_type = item.getString();
			            	}
		            		if(fieldname.equals("lc_amount"))
			            	{
		            			lc_amt = item.getString();
			            	}
		            		if(fieldname.equals("lc_valid_date"))
			            	{
		            			lc_valid_date = item.getString();
			            	}
		            		if(fieldname.equals("issue_bank"))
			            	{
		            			bankdtl = item.getString();
		            			bankdtl = es.replaceSingleQuotes(bankdtl);
			            	}
		            		if(fieldname.equals("remark"))
			            	{
		            			remark = item.getString();
		            			remark = es.replaceSingleQuotes(remark);
			            	}
	            } 
	            else {
	            	fieldname = item.getFieldName();
	                itemName=item.getName();
	            		if(item.getFieldName().equals("lc_file") && !itemName.equals(""))
		            	{
	            			filePath = item.getString();
			                int p1=itemName.lastIndexOf('/');
			    			fnm=itemName.substring(p1+1);
			    			fnm=fnm.substring(fnm.lastIndexOf("."),fnm.length());
		            	}
	            		item1 = item;
	            	}
	            }
		 if(count>0) {
			 flag="Y";
			 String uniqueFileNm = "";
			 if(!filePath.equals("")) {
				uniqueFileNm = fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no+"-"+customer_cd+"-"+contract_type+""+fnm;
				File savedFile=new File(filepath+"/"+uniqueFileNm);
				File lst_qtr= new File(filepath);
				file_bunch_qtr=lst_qtr.list();
				for ( int j=0;j<file_bunch_qtr.length;j++ )
			    {
					String file=file_bunch_qtr[j];
					String f=file.substring(0,file.indexOf("."));
					if(f.equals(""+uniqueFileNm+"-"+fnm))
					{
						File f1=new File(filepath+"/"+file);
						if(f1.exists())
						{
							f1.delete();
						}
					}
			    }	
				item1.write(savedFile);
			 }
			 
			 int avail = 0;
			 queryString = "SELECT COUNT(CUSTOMER_CD) FROM FMS8_LC_MST WHERE CUSTOMER_CD='"+customer_cd+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' AND "
				 		+ "SN_NO='"+sn_no+"' AND SN_REV_NO='"+sn_rev_no+"' AND CONTRACT_TYPE='"+contract_type+"' ";
			 rset = stmt.executeQuery(queryString);
			 if(rset.next()) {
				 avail = rset.getInt(1);
			 }
			 if(avail>0) {
				 queryString = "UPDATE FMS8_LC_MST SET LC_AMOUNT='"+lc_amt+"', LC_VALID_DATE=TO_DATE('"+lc_valid_date+"','DD/MM/YYYY'), BANK_DTL='"+bankdtl+"', "
				 		+ "LC_FILE_NM='"+uniqueFileNm+"',REMARK='"+remark+"' WHERE CUSTOMER_CD='"+customer_cd+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' "
				 		+ "AND SN_NO='"+sn_no+"' AND SN_REV_NO='"+sn_rev_no+"' AND CONTRACT_TYPE='"+contract_type+"' ";
				 msg="LC Details Updated Successfully!!";
			 } else {
				 queryString = "INSERT INTO FMS8_LC_MST(CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONTRACT_TYPE,LC_AMOUNT,LC_VALID_DATE,BANK_DTL,LC_FILE_NM,REMARK,ENT_BY,ENT_DT,FLAG) "
					+ "VALUES('"+customer_cd+"','"+fgsa_no+"','"+fgsa_rev_no+"','"+sn_no+"','"+sn_rev_no+"','"+contract_type+"','"+lc_amt+"',TO_DATE('"+lc_valid_date+"','DD/MM/YYYY'),'"+bankdtl+"',"
					+ "'"+uniqueFileNm+"','"+remark+"','"+user_cd+"',SYSDATE,'"+flag+"') ";
				 msg="LC Details Inserted Successfully!!";
			 }
			 stmt.executeUpdate(queryString);
		 }
		 url="../contract_master/frm_FGSA_LC_monitoring.jsp?msg="+msg+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&cont_type="+contract_type+"&customer_cd="+customer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		dbcon.commit();
	} 
	catch(Exception e)
	{
		String msg="LC Details Not Updated!!!";
		dbcon.rollback();
		e.printStackTrace();
		url="../contract_master/frm_FGSA_LC_monitoring.jsp?msg="+msg+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&cont_type="+contract_type+"&customer_cd="+customer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
	}
}
	
	public void requestChangePriceSN(HttpServletRequest request) throws Exception {
		try {
			methodName = "requestChangePriceSN()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			String FGSANO = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
			String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
			String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
			String FGSA_REVNO = request.getParameter("FGSA_REVNO")==null?"":request.getParameter("FGSA_REVNO");
		    String buyer_cd = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");	    	    
		    			
			query = "UPDATE FMS7_SN_MST SET PRICE_REQUEST_FLAG='Y',PRICE_APPROVE_FLAG='N' " +
					"WHERE CUSTOMER_CD='"+buyer_cd+"' AND FGSA_NO='"+FGSANO+"' AND FGSA_REV_NO='"+FGSA_REVNO+"' AND SN_NO='"+SNNO+"' AND SN_REV_NO='"+SN_REVNO+"' ";
//			System.out.println("SN_FCC Update Query = "+query);
			stmt.executeUpdate(query);
			msg = "Request For Price Change has been Initiated!!";
			url="../contract_master/frm_SN_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
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
			e.printStackTrace();
			dbcon.rollback();
		}
	}
	
	public void Insert_Update_Transporter_Contract(HttpServletRequest request) throws Exception {
		try {
			methodName = "Insert_Update_Transporter_Contract()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			String agree_no = request.getParameter("agree_no")==null?"0":request.getParameter("agree_no");
			String agr_rev_no= request.getParameter("agr_rev_no")==null?"0":request.getParameter("agr_rev_no");
			String cont_sn_loa_no = request.getParameter("cont_sn_loa_no")==null?"":request.getParameter("cont_sn_loa_no");
			String cont_sn_loa_rev_no = request.getParameter("cont_sn_loa_rev_no")==null?"":request.getParameter("cont_sn_loa_rev_no");
		    String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");	
		    String mapp_id_cont=request.getParameter("mapp_id_cont")==null?"":request.getParameter("mapp_id_cont");
		    String cont_type=request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
		    String plant_cont=request.getParameter("plant_cont")==null?"":request.getParameter("plant_cont");
		    String trans_cont=request.getParameter("trans_cont")==null?"":request.getParameter("trans_cont");
		    String agr_type=request.getParameter("agr_type")==null?"":request.getParameter("agr_type");
		    			
		    String temp[]=mapp_id_cont.split(",");
		    for(int i=0;i<temp.length;i++){
		    	String temp_map=temp[i]+"";
		    	String mapp_id[]=temp_map.split("-");
		    	String exit_pt_cd=mapp_id[5];
		    	String cust_cd="";String plant_seq_no="",final_cont_mappid="";
		    	if(cont_type.equals("L")){
		    		agr_rev_no="0";
		    	}
		    	if(Integer.parseInt(exit_pt_cd)>100){
		    		if(exit_pt_cd.substring(0,1).equals(buyer_cd)){
		    			
		    			final_cont_mappid=cont_type+"-"+exit_pt_cd.substring(0,1)+"-"+agree_no+"-"+agr_rev_no+"-"+cont_sn_loa_no+"-"+cont_sn_loa_rev_no+"-"+exit_pt_cd.substring(1,3);
		    			//System.out.println("--final_cont_mappid--"+final_cont_mappid);
		    		}else if(exit_pt_cd.substring(0,2).equals(buyer_cd)){
		    			final_cont_mappid=cont_type+"-"+exit_pt_cd.substring(0,2)+"-"+agree_no+"-"+agr_rev_no+"-"+cont_sn_loa_no+"-"+cont_sn_loa_rev_no+"-"+exit_pt_cd.substring(2,3);
		    			//System.out.println("--final_cont_mappid-in else if-"+final_cont_mappid);
		    		}
		    	}else{
		    		final_cont_mappid=cont_type+"-"+exit_pt_cd.substring(0,1)+"-"+agree_no+"-"+agr_rev_no+"-"+cont_sn_loa_no+"-"+cont_sn_loa_rev_no+"-"+exit_pt_cd.substring(1,2);
		    		//System.out.println("--final_cont_mappid-in else if-"+final_cont_mappid);
		    	}
		    	queryString="SELECT CONT_MAPPING_ID FROM FMS_CONT_MST WHERE MAPPING_ID='"+temp[i]+"' AND CONT_AGR_NO='"+agree_no+"' "
							+ "AND CONT_AGR_REV_NO='"+agr_rev_no+"' AND CONTRACT_NO='"+cont_sn_loa_no+"' AND CONTRACT_REV_NO='"+cont_sn_loa_rev_no+"' AND CONT_AGR_TYPE='"+agr_type+"'"
							+ "	AND CONT_CUST_CD='"+buyer_cd+"'" ;
		    	//System.out.println("---queryString--"+queryString);
		    	rset=stmt.executeQuery(queryString);
		    	if(rset.next()){
		    		String cont_mapp_id=rset.getString(1)==null?"":rset.getString(1);
		    			queryString="UPDATE FMS_CONT_MST SET CONT_MAPPING_ID='"+final_cont_mappid+"' WHERE MAPPING_ID='"+temp[i]+"' AND CONT_AGR_NO='"+agree_no+"' "
							+ "AND CONT_AGR_REV_NO='"+agr_rev_no+"' AND CONTRACT_NO='"+cont_sn_loa_no+"' AND CONTRACT_REV_NO='"+cont_sn_loa_rev_no+"' AND CONT_AGR_TYPE='"+agr_type+"'"
							+ "	AND CONT_CUST_CD='"+buyer_cd+"'";
		    			
		    			//System.out.println("queryString--"+queryString);
		    			stmt.executeUpdate(queryString);
		    	}
		    }
			
			msg = "Transporter Linked Succesfully!!";
			url="../contract_master/frm_transporter_contract_list.jsp?msg="+msg+"&plant_cont="+plant_cont+"&trans_cont="+trans_cont+"&buyer_cd="+buyer_cd+"&agr_no="+agree_no+"&agr_rev_no="+agr_rev_no+"&cont_sn_loa_no="+cont_sn_loa_no+"&cont_sn_loa_rev_no="+cont_sn_loa_rev_no+"&cont_type="+cont_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
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
			e.printStackTrace();
			dbcon.rollback();
			msg="Failed to Submit";
			url="../contract_master/frm_transporter_contract_list.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	public void requestChangePriceLOA(HttpServletRequest request) throws Exception {
		try {
			methodName = "requestChangePriceLOA()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			String FGSANO = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
			String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
			String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		    String buyer_cd = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");	    	    
		    			
			query = "UPDATE FMS7_LOA_MST SET PRICE_REQUEST_FLAG='Y',PRICE_APPROVE_FLAG='N' " +
					"WHERE CUSTOMER_CD='"+buyer_cd+"' AND TENDER_NO='"+FGSANO+"' AND LOA_NO='"+SNNO+"' AND LOA_REV_NO='"+SN_REVNO+"' ";
//			System.out.println("SN_FCC Update Query = "+query);
			stmt.executeUpdate(query);
			msg = "Request For Price Change has been Initiated!!";
			url="../contract_master/frm_LOA_master.jsp?msg="+msg+"&flg=update&activity=update&LOA_CD="+SNNO+"&LOA_REVNO="+SN_REVNO+"&TENDER_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
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
			e.printStackTrace();
			dbcon.rollback();
		}
	}
	
	public void cargoApprovalDtl(HttpServletRequest request) throws Exception {
		String contract_type = request.getParameter("contract_type")==null?"0":request.getParameter("contract_type");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		try {
			methodName = "cargoApprovalDtl()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			String fgsa_no[] = request.getParameterValues("fgsa_no");
			String fgsa_rev_no[] = request.getParameterValues("fgsa_rev_no");
			String sn_no[] = request.getParameterValues("sn_no");
			String sn_rev_no[] = request.getParameterValues("sn_rev_no");
			String seq_no[] = request.getParameterValues("seq_no");
			String selIndex = request.getParameter("selIndex")==null?"0":request.getParameter("selIndex");
			int i = Integer.parseInt(selIndex);
			String newMargin[] = request.getParameterValues("newMargin"+i);
			String newTotMargin[] = request.getParameterValues("newTotMargin"+i);
			String cargo_no[] = request.getParameterValues("cargo_no"+i);
			String newRate[] = request.getParameterValues("newRate"+i);
			String newAvgMargin[] = request.getParameterValues("newAvgMargin");
			
			query = "UPDATE FMS8_CARGO_ALLOC_REVISED_DTL SET APPROVE_BY='"+user_cd+"', APPROVE_DT=SYSDATE, FLAG='A' WHERE FGSA_NO='"+fgsa_no[i]+"' AND "
					+ "SN_NO='"+sn_no[i]+"' AND CUSTOMER_CD='"+customer_cd+"' AND CONTRACT_TYPE='"+contract_type+"' AND MODIFICATION_SEQ_NO='"+seq_no[i]+"' "
					+ "AND FLAG='R'";
//			System.out.println("Update Query--"+query);
			stmt.executeUpdate(query);
			
			if(contract_type.equals("S")) {
				for(int j=0;j<cargo_no.length;j++) {
					if(j==0) {
						query = "UPDATE FMS7_SN_MST SET RATE='"+newRate[j]+"',PRICE_APPROVE_FLAG='Y' WHERE FGSA_NO='"+fgsa_no[i]+"' AND FGSA_REV_NO='"+fgsa_rev_no[i]+"' AND "
								+ "SN_NO='"+sn_no[i]+"' AND SN_REV_NO='"+sn_rev_no[i]+"' AND CUSTOMER_CD='"+customer_cd+"' ";
						stmt.executeUpdate(query);
					}
					
					if(cargo_no[j].equals("0")) {
						query1 = "UPDATE FMS7_DUMMY_CARGO_DTL SET SALE_PRICE='"+newRate[j]+"' WHERE FGSA_NO='"+fgsa_no[i]+"' AND FGSA_REV_NO='"+fgsa_rev_no[i]+"' "
								+ "AND SN_NO='"+sn_no[i]+"' AND "
								+ "CUSTOMER_CD='"+customer_cd+"' AND CARGO_REF_NO='"+cargo_no[j]+"' AND CONTRACT_TYPE='"+contract_type+"'";
					} else {
						query1 = "UPDATE FMS7_SN_CARGO_DTL SET SALE_PRICE='"+newRate[j]+"', MARGIN='"+newMargin[j]+"', AVG_MARGIN='"+newAvgMargin[i]+"',"
							+ "TOTAL_MARGIN='"+newTotMargin[j]+"' WHERE FGSA_NO='"+fgsa_no[i]+"' AND FGSA_REV_NO='"+fgsa_rev_no[i]+"' AND SN_NO='"+sn_no[i]+"' AND "
							+ "CUSTOMER_CD='"+customer_cd+"' AND CARGO_REF_NO='"+cargo_no[j]+"' ";
					}
					stmt.executeUpdate(query1);
				}
				dbcon.commit();
			} else if(contract_type.equals("L")) {
				for(int j=0;j<cargo_no.length;j++) {
					if(j==0) {
						query = "UPDATE FMS7_LOA_MST SET RATE='"+newRate[j]+"',PRICE_APPROVE_FLAG='Y' WHERE TENDER_NO='"+fgsa_no[i]+"' AND "
								+ "LOA_NO='"+sn_no[i]+"' AND CUSTOMER_CD='"+customer_cd+"' AND LOA_REV_NO='"+sn_rev_no[i]+"' ";
						stmt.executeUpdate(query);
					}
					if(cargo_no[j].equals("0")) {
						query1 = "UPDATE FMS7_DUMMY_CARGO_DTL SET SALE_PRICE='"+newRate[i]+"' WHERE FGSA_NO='"+fgsa_no[i]+"' AND FGSA_REV_NO='"+fgsa_rev_no[i]+"' "
								+ "AND SN_NO='"+sn_no[i]+"' AND "
								+ "CUSTOMER_CD='"+customer_cd+"' AND CARGO_REF_NO='"+cargo_no[j]+"' AND CONTRACT_TYPE='"+contract_type+"'";
					} else {
						query1 = "UPDATE FMS7_LOA_CARGO_DTL SET SALE_PRICE='"+newRate[j]+"', MARGIN='"+newMargin[j]+"', AVG_MARGIN='"+newAvgMargin[i]+"',"
							+ "TOTAL_MARGIN='"+newTotMargin[j]+"' WHERE TENDER_NO='"+fgsa_no[i]+"' AND LOA_NO='"+sn_no[i]+"' AND "
							+ "CUSTOMER_CD='"+customer_cd+"' AND CARGO_REF_NO='"+cargo_no[j]+"' ";
					}
					stmt.executeUpdate(query1);
				}
				dbcon.commit();
			}
			
			msg = "Sale Price Data Has Been Approved Successfully!!!";
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
			e.printStackTrace();
			dbcon.rollback();
			msg = "Data Not Approved Successfully!!";
		}
		url="../cargo_procurement/frm_cargo_allocation_modify.jsp?msg="+msg+"&write_permission="+write_permission
				+"&approve_permission="+approve_permission+"&contract_type="+contract_type
				+"&customer_cd="+customer_cd+"&formId="+form_id+"&FormName="+form_nm;
	}
	
	public void cargoModificationDtl(HttpServletRequest request) throws Exception {
		String contract_type = request.getParameter("contract_type")==null?"0":request.getParameter("contract_type");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		try {
			msg="";
			methodName = "cargoModificationDtl()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			String fgsa_no[] = request.getParameterValues("fgsa_no");
			String fgsa_rev_no[] = request.getParameterValues("fgsa_rev_no");
			String sn_no[] = request.getParameterValues("sn_no");
			String sn_rev_no[] = request.getParameterValues("sn_rev_no");
			String flag[] = request.getParameterValues("flag");
			String seq_no[] = request.getParameterValues("seq_no");
			String oldRate[] = request.getParameterValues("oldRate");
			String newAvgMargin[] = request.getParameterValues("newAvgMargin");
			String newPriceEffDt[] = request.getParameterValues("newPriceEffDt"); //SB20200326
			String selIndex = request.getParameter("selIndex")==null?"0":request.getParameter("selIndex");
			
			int i = Integer.parseInt(selIndex);
			int new_seq_no = Integer.parseInt(seq_no[i])+1;
			String alloc_qty[] = request.getParameterValues("allocQty"+i);
			String oldMargin[] = request.getParameterValues("oldMargin"+i);
			String newMargin[] = request.getParameterValues("newMargin"+i);
			String oldTotMargin[] = request.getParameterValues("oldTotMargin"+i);
			String newTotMargin[] = request.getParameterValues("newTotMargin"+i);
			String cargo_no[] = request.getParameterValues("cargo_no"+i);
			String oldAvgMargin[] = request.getParameterValues("oldAvgMargin"+i);
			String newRate[] = request.getParameterValues("newRate"+i);
			
			if(flag[i].equals("") || flag[i].equals("A")) { //NEW ENTRY DOES NOT EXIST OR ENTRY EXISTS ALSO APPROVED..
				for(int j=0;j<cargo_no.length;j++) {
				/*SB20200326	query = "INSERT INTO FMS8_CARGO_ALLOC_REVISED_DTL(FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CUSTOMER_CD,"
							+ "CONTRACT_TYPE,CARGO_REF_NO,MODIFICATION_SEQ_NO,FLAG,ENT_BY,ENT_DT,"
							+ "ORI_SALE_PRICE,NEW_SALE_PRICE,ORI_MARGIN,NEW_MARGIN,ORI_AVG_MARGIN,"
							+ "NEW_AVG_MARGIN,ORI_TOT_MARGIN,NEW_TOT_MARGIN,ALLOC_QTY) VALUES('"+fgsa_no[i]+"'"
							+ ",'"+fgsa_rev_no[i]+"','"+sn_no[i]+"','"+sn_rev_no[i]+"','"+customer_cd+"','"+contract_type+"',"
							+ "'"+cargo_no[j]+"','"+new_seq_no+"','R','"+user_cd+"',SYSDATE,'"+oldRate[i]+"','"+newRate[j]+"',"
							+ "'"+oldMargin[j]+"','"+newMargin[j]+"','"+oldAvgMargin[j]+"','"+newAvgMargin[i]+"','"+oldTotMargin[j]+"',"
							+ "'"+newTotMargin[j]+"','"+alloc_qty[j]+"') ";
							*/
					query = "INSERT INTO FMS8_CARGO_ALLOC_REVISED_DTL(FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CUSTOMER_CD,"
							+ "CONTRACT_TYPE,CARGO_REF_NO,MODIFICATION_SEQ_NO,FLAG,ENT_BY,ENT_DT,"
							+ "ORI_SALE_PRICE,NEW_SALE_PRICE,ORI_MARGIN,NEW_MARGIN,ORI_AVG_MARGIN,"
							+ "NEW_AVG_MARGIN,ORI_TOT_MARGIN,NEW_TOT_MARGIN,ALLOC_QTY, NEW_PRICE_EFF_DT) VALUES('"+fgsa_no[i]+"'"
							+ ",'"+fgsa_rev_no[i]+"','"+sn_no[i]+"','"+sn_rev_no[i]+"','"+customer_cd+"','"+contract_type+"',"
							+ "'"+cargo_no[j]+"','"+new_seq_no+"','R','"+user_cd+"',SYSDATE,'"+oldRate[i]+"','"+newRate[j]+"',"
							+ "'"+oldMargin[j]+"','"+newMargin[j]+"','"+oldAvgMargin[j]+"','"+newAvgMargin[i]+"','"+oldTotMargin[j]+"',"
							+ "'"+newTotMargin[j]+"','"+alloc_qty[j]+"',TO_DATE('"+newPriceEffDt[i]+"','DD/MM/YYYY')) ";
					System.out.println("QRY-1: INSERT: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
					stmt.executeUpdate(query);
					msg += "Sale Price Changed For SN/LOA "+sn_no[i]+" From "+oldRate[i]+" To "+newRate[j]+" Eff dt: "+newPriceEffDt[i]+" Successfully!!!\n";
					dbcon.commit();
				}
			} else if(flag[i].equals("R")) { //ENTRY EXISTS..BUT NOT APPROVED..
				for(int j=0;j<cargo_no.length;j++) {
					query = "UPDATE FMS8_CARGO_ALLOC_REVISED_DTL SET ORI_SALE_PRICE='"+oldRate[i]+"', NEW_SALE_PRICE='"+newRate[j]+"',"
							+ "ORI_MARGIN='"+oldMargin[j]+"',NEW_MARGIN='"+newMargin[j]+"',ORI_AVG_MARGIN='"+oldAvgMargin[j]+"',NEW_AVG_MARGIN='"+newAvgMargin[i]+"',"
							+ "ORI_TOT_MARGIN='"+oldTotMargin[j]+"',NEW_TOT_MARGIN='"+newTotMargin[j]+"',ENT_BY='"+user_cd+"',ENT_DT=SYSDATE, NEW_PRICE_EFF_DT=TO_DATE('"+newPriceEffDt[i]+"','DD/MM/YYYY') WHERE FGSA_NO='"+fgsa_no[i]+"' AND "
							+ "SN_NO='"+sn_no[i]+"' AND CUSTOMER_CD='"+customer_cd+"' AND CONTRACT_TYPE='"+contract_type+"' AND MODIFICATION_SEQ_NO='"+seq_no[i]+"' "
							+ "AND FLAG='R'";
					System.out.println("QRY-1A:Update: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
					stmt.executeUpdate(query);
					msg += "Sale Price Approved For SN/LOA "+sn_no[i]+" From "+oldRate[i]+" To "+newRate[j]+" Eff dt: "+newPriceEffDt[i]+" Successfully!!!\n";
					dbcon.commit();
				}
			} 
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
			msg = "Data Not Submitted Successfully!!";
			dbcon.rollback();
		}
		url="../cargo_procurement/frm_cargo_allocation_modify.jsp?msg="+msg+"&write_permission="+write_permission
				+"&approve_permission="+approve_permission+"&contract_type="+contract_type
				+"&customer_cd="+customer_cd+"&formId="+form_id+"&FormName="+form_nm;
	}
	
	public void approveContract(HttpServletRequest request) throws Exception {
		String contract_type = request.getParameter("contract_type")==null?"0":request.getParameter("contract_type");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		try {
			methodName = "approveContract()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		    			
			if(contract_type.equals("F")) {
				String fgsa_no[] = request.getParameterValues("fgsa_no");
				String fgsa_rev_no[] = request.getParameterValues("fgsa_rev_no");
				String sel[] = request.getParameterValues("selHidden");
				String aprvFlag[] = request.getParameterValues("aprvFlagHidden");
				String remark[] = request.getParameterValues("remark");
				
				for(int i=0;i<fgsa_no.length;i++) {
					if(sel[i].equals("Y")) {
						query = "UPDATE FMS7_FGSA_MST SET REOPEN_APPROVAL_FLAG='"+aprvFlag[i]+"', REOPEN_APPROVAL_DT=SYSDATE, REOPEN_APPROVE_BY='"+user_cd+"',REMARK='"+remark[i]+"' " +
								"WHERE CUSTOMER_CD='"+customer_cd+"' AND FGSA_NO='"+fgsa_no[i]+"' AND REV_NO='"+fgsa_rev_no[i]+"' "
								+ "AND REOPEN_REQUEST_FLAG='Y' ";
						//System.out.println("SN_FCC Update Query = "+query);
						stmt.executeUpdate(query);
						dbcon.commit();
					}
				}
			} else if(contract_type.equals("LT")) {
				String fgsa_no[] = request.getParameterValues("fgsa_no");
				String fgsa_rev_no[] = request.getParameterValues("fgsa_rev_no");
				String sel[] = request.getParameterValues("selHidden");
				String aprvFlag[] = request.getParameterValues("aprvFlagHidden");
				String remark[] = request.getParameterValues("remark");
				
				for(int i=0;i<fgsa_no.length;i++) {
					if(sel[i].equals("Y")) {
						query = "UPDATE FMS8_LNG_REGAS_MST SET REOPEN_APPROVAL_FLAG='"+aprvFlag[i]+"', REOPEN_APPROVAL_DT=SYSDATE, REOPEN_APPROVE_BY='"+user_cd+"',REOPEN_REMARK='"+remark[i]+"' " +
								"WHERE CUSTOMER_CD='"+customer_cd+"' AND AGREEMENT_NO='"+fgsa_no[i]+"' AND REV_NO='"+fgsa_rev_no[i]+"' "
								+ "AND REOPEN_REQUEST_FLAG='Y' AND CN_NO='0' AND CN_REV_NO='0' ";
						//System.out.println("SN_FCC Update Query = "+query);
						stmt.executeUpdate(query);
						dbcon.commit();
					}
				}
			} else if(contract_type.equals("T")) {
				String fgsa_no[] = request.getParameterValues("fgsa_no");
				String sel[] = request.getParameterValues("selHidden");
				String aprvFlag[] = request.getParameterValues("aprvFlagHidden");
				String remark[] = request.getParameterValues("remark");
				
				for(int i=0;i<fgsa_no.length;i++) {
					if(sel[i].equals("Y")) {
						query = "UPDATE FMS7_TENDER_MST SET REOPEN_APPROVAL_FLAG='"+aprvFlag[i]+"', REOPEN_APPROVAL_DT=SYSDATE, REOPEN_APPROVE_BY='"+user_cd+"',REMARK='"+remark[i]+"' " +
								"WHERE CUSTOMER_CD='"+customer_cd+"' AND TENDER_NO='"+fgsa_no[i]+"' "
								+ "AND REOPEN_REQUEST_FLAG='Y' ";
						//System.out.println("SN_FCC Update Query = "+query);
						stmt.executeUpdate(query);
						dbcon.commit();
					}
				}
			} else if(contract_type.equals("C")) {
				String fgsa_no[] = request.getParameterValues("fgsa_no");
				String fgsa_rev_no[] = request.getParameterValues("fgsa_rev_no");
				String cn_no[] = request.getParameterValues("cn_no");
				String cn_rev_no[] = request.getParameterValues("cn_rev_no");
				String sel[] = request.getParameterValues("selHidden");
				String aprvFlag[] = request.getParameterValues("aprvFlagHidden");
				String remark[] = request.getParameterValues("remark");
				
				for(int i=0;i<fgsa_no.length;i++) {
					if(sel[i].equals("Y")) {
						query = "UPDATE FMS8_LNG_REGAS_MST SET REOPEN_APPROVAL_FLAG='"+aprvFlag[i]+"', REOPEN_APPROVAL_DT=SYSDATE, REOPEN_APPROVE_BY='"+user_cd+"',REOPEN_REMARK='"+remark[i]+"' " +
								"WHERE CUSTOMER_CD='"+customer_cd+"' AND AGREEMENT_NO='"+fgsa_no[i]+"' AND REV_NO='"+fgsa_rev_no[i]+"' "
								+ "AND REOPEN_REQUEST_FLAG='Y' AND CN_NO='"+cn_no[i]+"' AND CN_REV_NO='"+cn_rev_no[i]+"' ";
						//System.out.println("SN_FCC Update Query = "+query);
						stmt.executeUpdate(query);
						dbcon.commit();
					}
				}
			}
			msg = "Re-Activate Request Has Been Approved Successfully!!!";
			try
			{
				//System.out.println(form_id+"=="+form_nm);
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		} catch(Exception e) {
			msg = "Re-Activate Request Has Not Been Approved Successfully !!!";
			e.printStackTrace();
			dbcon.rollback();
		}
		url="../contract_master/frm_aprv_reopen_contract.jsp?msg="+msg+"&write_permission="+write_permission
				+"&delete_permission="+delete_permission+"&print_permission="+print_permission
				+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission
				+"&contract_type="+contract_type+"&customer_cd="+customer_cd+"&formId="+form_id
				+"&FormName="+form_nm;
	}
	
	
	public void ReOpenFGSA(HttpServletRequest request) throws Exception {
		try {
			methodName = "ReOpenFGSA()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			String FGSANO = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
			String FGSA_REVNO = request.getParameter("revNo")==null?"":request.getParameter("revNo");
		    String buyer_cd = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");	    	    
		    			
			query = "UPDATE FMS7_FGSA_MST SET REOPEN_REQUEST_FLAG='Y', REOPEN_REQUEST_DT=SYSDATE, REOPEN_REQUEST_BY='"+user_cd+"' " +
					"WHERE CUSTOMER_CD='"+buyer_cd+"' AND FGSA_NO='"+FGSANO+"' AND REV_NO='"+FGSA_REVNO+"' ";
//			System.out.println("SN_FCC Update Query = "+query);
			stmt.executeUpdate(query);
			msg = "Re-Activate Request Sent For Approval For FGSA "+FGSANO+" ";
			url="../contract_master/frm_FGSA_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
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
			e.printStackTrace();
			dbcon.rollback();
		}
	}
	
	public void ReOpenTender(HttpServletRequest request) throws Exception {
		String tender_no = request.getParameter("tender_no")==null?"0":request.getParameter("tender_no");
	    String buyer_cd = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
		try {
			methodName = "ReOpenTender()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			query = "UPDATE FMS7_TENDER_MST SET REOPEN_REQUEST_FLAG='Y', REOPEN_REQUEST_DT=SYSDATE, REOPEN_REQUEST_BY='"+user_cd+"' " +
					"WHERE CUSTOMER_CD='"+buyer_cd+"' AND TENDER_NO='"+tender_no+"' ";
//			System.out.println("Update Tendf..."+query);
			stmt.executeUpdate(query);
			msg = "Re-Activate Request Sent For Approval For Tender "+tender_no+" ";
			
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
			e.printStackTrace();
			dbcon.rollback();
		}
		url="../contract_master/frm_tender_master.jsp?msg="+msg+"&flg=update&activity=update&tender_cd="+tender_no+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
	}
	
	public void ExchangeRateEntry(HttpServletRequest request) throws SQLException
	{
		try
		{
			methodName = "ExchangeRateEntry()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			String eff_dt = request.getParameter("eff_dt1")==null?"":request.getParameter("eff_dt1");
			String exch_rate = request.getParameter("exchg_rate")==null?"":request.getParameter("exchg_rate");
			String sel = request.getParameter("sel")==null?"2":request.getParameter("sel");
			
			int count = 0;
			String query="SELECT COUNT(SEQ_NO) FROM EXCHG_RATE_MST WHERE EFF_DT=TO_DATE('"+eff_dt+"','DD/MM/YYYY')";
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				count = rset.getInt(1);
			}
			
			if(count==0)
			{
				query="SELECT MAX(SEQ_NO) FROM EXCHG_RATE_MST ";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					count = rset.getInt(1);
				}
				
				count++;
				
				query="INSERT INTO EXCHG_RATE_MST(SEQ_NO,EXCHG_RATE,EFF_DT,ENTER_BY,ENTER_DT,FLAG) "
						+ "VALUES('"+count+"','"+exch_rate+"',TO_DATE('"+eff_dt+"','DD/MM/YYYY'),'"+user_cd+"',sysdate,'Y')";
				stmt.executeUpdate(query);
			}
			else
			{
				query = "UPDATE EXCHG_RATE_MST SET EXCHG_RATE='"+exch_rate+"' WHERE EFF_DT=TO_DATE('"+eff_dt+"','DD/MM/YYYY')";
				stmt.executeUpdate(query);
			}
			msg="Data Submitted Successfully!!!";
			url="../contract_master/frm_advance_amount_collection.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&sel="+sel;
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
			msg="Data Not Submitted!!!";
			url="../contract_master/frm_advance_amount_collection.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	public void AdvanceCollection(HttpServletRequest request) throws SQLException
	{
		try
		{
			methodName = "AdvanceCollection()";
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			String fgsano = request.getParameter("fgsano")==null?"":request.getParameter("fgsano");
			String snno = request.getParameter("snno")==null?"":request.getParameter("snno");
			String fgsarevno = request.getParameter("fgsarevno")==null?"":request.getParameter("fgsarevno");
			String snrevno = request.getParameter("snrevno")==null?"":request.getParameter("snrevno");
			String startdt = request.getParameter("startdt")==null?"":request.getParameter("startdt");
			String enddt = request.getParameter("enddt")==null?"":request.getParameter("enddt");
			String signingdt = request.getParameter("signingdt")==null?"":request.getParameter("signingdt");
			String customer = request.getParameter("customer")==null?"":request.getParameter("customer");
			String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
			String tcq = request.getParameter("tcq")==null?"":request.getParameter("tcq");
			String advance_amount = request.getParameter("advance_amount")==null?"":request.getParameter("advance_amount");
			String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
			String qty = request.getParameter("qty")==null?"":request.getParameter("qty");
			String currency = request.getParameter("currency") == null?"I":request.getParameter("currency");
			String contract_type = request.getParameter("contract_type") == null?"":request.getParameter("contract_type");
			String tax = request.getParameter("tax") == null?"":request.getParameter("tax");
			String sel = request.getParameter("sel")==null?"1":request.getParameter("sel");
			
			int count=0;
			
			String query="SELECT MAX(SEQ_NO) FROM FMS8_ADVANCE_AMT_MST WHERE FGSA_NO='"+fgsano+"' AND "
					+ "FGSA_REV_NO='"+fgsarevno+"' AND SN_NO='"+snno+"' AND SN_REV_NO='"+snrevno+"' AND "
					+ "CUSTOMER_CD='"+customer+"' AND CONTRACT_TYPE='"+contract_type+"'";
			System.out.println("query.."+query);
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				count = rset.getInt(1);
			}
			
			count++;
			
			query="INSERT INTO FMS8_ADVANCE_AMT_MST(FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CUSTOMER_CD,SEQ_NO,ADVANCE_AMOUNT,"
					+ "EFF_DT,ENTER_BY,ENTER_DT,FLAG,CURRENCY,CONTRACT_TYPE,TAX_AMOUNT) "
					+ "VALUES('"+fgsano+"','"+fgsarevno+"',"
					+ "'"+snno+"','"+snrevno+"','"+customer+"','"+count+"','"+advance_amount+"',"
					+ "to_date('"+eff_dt+"','dd/mm/yyyy'),'"+user_cd+"',sysdate,'Y','"+currency+"','"+contract_type+"','"+tax+"') ";
			System.out.println(query+"--");
			stmt.executeUpdate(query);
			
			dbcon.commit();
			msg="Data Submitted Successfully!!!";
			url="../contract_master/frm_advance_amount_collection.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&customer="+customer+"&fgsano="+fgsano+"&fgsarevno="+fgsarevno+"&snno="+snno+"&snrevno="+snrevno+"&contract_type="+contract_type+"&sel="+sel;
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
			msg="Data Not Submitted!!!";
			url="../contract_master/frm_advance_amount_collection.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			e.printStackTrace();
		}
	}
	
	public void FGSAAgreementMaster_Approval(HttpServletRequest request) throws Exception
	{
		try
		{
			methodName = "FGSAAgreementMaster_Approval()";
			escapeSingleQuotes obj = new  escapeSingleQuotes();
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
			
			String FGSANO = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
			String FGSA_REVNO = request.getParameter("revNo")==null?"":request.getParameter("revNo");
			
		    String buyer_cd = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");	    	    
		    String FCC_flag= request.getParameter("FCC_flag")==null?"":request.getParameter("FCC_flag");
			
		    			
				query = "UPDATE FMS7_FGSA_MST SET PRE_APPROVAL='"+FCC_flag+"', PRE_APPROVAL_DATE=sysdate, PRE_APPROVAL_BY='"+user_cd+"' " +
				"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
				"FGSA_NO='"+FGSANO+"' AND REV_NO='"+FGSA_REVNO+"'";
				System.out.println("SN_FCC Update Query = "+query);
				stmt.executeUpdate(query);
				if(FCC_flag.trim().equalsIgnoreCase("Y"))
				{
					msg="Approval of FGSA Done Successfully !!!!";
				}
				else if(FCC_flag.trim().equalsIgnoreCase("N"))
				{
					msg="FGSA Successfully Disapproved !!!!";
				}			
				
				url="../pre_approve/frm_fgsa_aprval.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&FCC_flag="+FCC_flag;
				//System.out.println(url);
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
			dbcon.rollback();
			e.printStackTrace();
		}
	}
	
	 //Following method has been added by JHP on 2nd May, 2012.......
	public void update_REGAS_CARGO_DTL_REV(HttpServletRequest request) throws Exception
	{
		methodName = "update_REGAS_CARGO_DTL_REV()";
		escapeSingleQuotes obj = new escapeSingleQuotes();
		HttpSession session = request.getSession();
		
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");	    	    
		String buyer_nm = request.getParameter("buyer_nm")==null?"":request.getParameter("buyer_nm");	    	    
		String re_gas_no = request.getParameter("re_gas_no")==null?"":request.getParameter("re_gas_no");	    	    
		String cargo_seq_no = request.getParameter("cargo_seq_no")==null?"":request.getParameter("cargo_seq_no");	    	    
		String no_of_cargo = request.getParameter("no_of_cargo")==null?"":request.getParameter("no_of_cargo");	    	    		
		try
		{
			//query = "UPDATE FMS7_RE_GAS_CARGO_DTL SET " +
			//		"REGAS_CLOSURE_REQUEST='' WHERE CUSTOMER_CD='"+buyer_cd+"' " +
			//		"AND RE_GAS_NO='"+re_gas_no+"' AND CARGO_SEQ_NO='"+cargo_seq_no+"' ";
			
			query = "UPDATE FMS7_RE_GAS_CARGO_DTL SET " +
			"REGAS_CLOSURE_REQUEST='',REGAS_CLOSURE_CLOSE='',REGAS_CLOSURE_FLAG=''," +
			"REGAS_CLOSURE_DT='',REGAS_CLOSURE_QTY='' WHERE CUSTOMER_CD='"+buyer_cd+"' " +
			"AND RE_GAS_NO='"+re_gas_no+"' AND CARGO_SEQ_NO='"+cargo_seq_no+"' ";
	
			//System.out.println("FMS7_REGAS_CARGO_DTL Update Query for Re-open Closure Request = "+query);
			stmt.executeUpdate(query);
			
			msg="Re-open RE-GAS Closure Request Submitted for Cargo No. "+cargo_seq_no+"!!!";
			url="../contract_master/frm_RE_GAS_Cargo_dtl.jsp?buyer_cd="+buyer_cd+"&buyer_nm="+buyer_nm+"&re_gas_no="+re_gas_no+"&no_of_cargo="+no_of_cargo+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="Re-open RE-GAS Closure Request Not Submitted !!!";
			e.printStackTrace();			
			url="../contract_master/frm_RE_GAS_Cargo_dtl.jsp?buyer_cd="+buyer_cd+"&buyer_nm="+buyer_nm+"&re_gas_no="+re_gas_no+"&no_of_cargo="+no_of_cargo+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	
    //Following method has been added by Priyanka on 5th April, 2011.......
	public void delete_FGSA_deactivation(HttpServletRequest request) throws Exception
	{
		methodName = "delete_FGSA_deactivation()";
		//escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
		String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_revno = request.getParameter("fgsa_revno")==null?"":request.getParameter("fgsa_revno");
		String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");		
		String del_from_dt = request.getParameter("del_from_dt")==null?"0":request.getParameter("del_from_dt");		
		String del_to_dt = request.getParameter("del_to_dt")==null?"":request.getParameter("del_to_dt");		
	    try
		{
	    	query1 = "DELETE FROM FMS7_FGSA_DEACTIVATION_DTL " +
					 "WHERE FROM_DT=to_date('"+del_from_dt+"','dd/mm/yyyy') AND " +
					 "TO_DT=to_date('"+del_to_dt+"','dd/mm/yyyy') ";
			//System.out.println("Delete Query For FGSA Deactivation Details = "+query1);
			stmt1.executeUpdate(query1);
			msg = "Deactivation Details Deleted Successfully For Customer "+buyer_name+" of FGSA "+fgsa_cd+" !!!";
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
			dbcon.rollback();
			e.printStackTrace();
			msg="Deactivation Details NOT Deleted For Customer "+buyer_name+" of FGSA "+fgsa_cd+" !!!";
		}		
		url="../contract_master/frm_fgsa_active_deactive.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}//End Of 
	
	
	//	Following method has been added by Achal Pathak on 28th January, 2011.......
	public void updateLOAMaster_FCC(HttpServletRequest request) throws Exception
	{
		methodName = "updateLOAMaster_FCC()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		//String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String FGSANO = request.getParameter("FGSANO")==null?"0":request.getParameter("FGSANO");		
		String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
		String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		String BUYER_NO = request.getParameter("BUYER_NO")==null?"":request.getParameter("BUYER_NO");	   	    
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");	    	    
	    String FCC_flag= request.getParameter("FCC_flag")==null?"":request.getParameter("FCC_flag");
		
	    try
		{
			query = "UPDATE FMS7_LOA_MST SET FCC_FLAG='"+FCC_flag+"', FCC_DATE=sysdate, FCC_BY='"+user_cd+"' " +
			"WHERE LOA_NO='"+SNNO+"' AND LOA_REV_NO='"+SN_REVNO+"' AND CUSTOMER_CD='"+BUYER_NO+"' AND " +
			"TENDER_NO='"+FGSANO+"' ";
			//System.out.println("LOA_FCC Update Query = "+query);
			stmt.executeUpdate(query);
			if(FCC_flag.trim().equalsIgnoreCase("Y"))
			{
				msg="Financial Checks and Control - LOA Successfully Approved !!!!";
			}
			else if(FCC_flag.trim().equalsIgnoreCase("N"))
			{
				msg="Financial Checks and Control - LOA Successfully Disapproved !!!!";
			}			
			
			url="../contract_master/frm_LOA_master_FCC.jsp?msg="+msg+"&flg=update&activity=update&LOA_CD="+SNNO+"&LOA_REVNO="+SN_REVNO+"&TENDER_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&FCC_flag="+FCC_flag;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="LOA Approval Process Failed !!!";
			e.printStackTrace();			
			url="../contract_master/frm_LOA_master_FCC.jsp?msg="+msg+"&flg=update&activity=update&LOA_CD="+SNNO+"&LOA_REVNO="+SN_REVNO+"&TENDER_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&FCC_flag="+FCC_flag;	
		}
	}
		
	public void insertModifyReGasCargoDetails(HttpServletRequest request) throws Exception
	{
		methodName = "insertModifyReGasCargoDetails()";
//		System.out.println("Milan >>>>>>>>>>>RE gas insertModifyReGasCargoDetails() start");
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
								
		String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
		String re_gas_no = request.getParameter("re_gas_no")==null?"0":request.getParameter("re_gas_no");
		String re_gas_rev_no = request.getParameter("re_gas_rev_no")==null?"0":request.getParameter("re_gas_rev_no");
		String no_of_cargo = request.getParameter("no_of_cargo")==null?"1":request.getParameter("no_of_cargo");
		String buyer_nm = request.getParameter("buyer_nm")==null?"":request.getParameter("buyer_nm");//MD20111019
					
		try
		{
			String [] cargo_ref_no_tmp = request.getParameterValues("cargo_ref_no");
			String [] supp_cd_tmp = request.getParameterValues("supp_cd"); //SB20110927
			String [] supp_nm_tmp = request.getParameterValues("supp_name"); //SB20110930
			String [] ship_cd_tmp = request.getParameterValues("ship_cd");
			String [] ship_name_tmp = request.getParameterValues("ship_name");
			String [] edq_from_dt_tmp = request.getParameterValues("edq_from_dt");
			String [] edq_to_dt_tmp = request.getParameterValues("edq_to_dt");
			String [] actual_receipt_dt_tmp = request.getParameterValues("actual_receipt_dt");
			String [] contract_start_dt_tmp = request.getParameterValues("contract_start_dt");
			String [] contract_end_dt_tmp = request.getParameterValues("contract_end_dt");
			String [] adq_qty_tmp = request.getParameterValues("adq_qty");
			String [] sug_percentage_tmp = request.getParameterValues("sug_percentage");
			String [] qty_to_be_supplied_tmp = request.getParameterValues("qty_to_be_supplied");
			String [] dcq_qty_tmp = request.getParameterValues("dcq_qty");
			String [] service_tariff_tmp = request.getParameterValues("service_tariff");
			String [] regas_request_arr = request.getParameterValues("regas_request");            
			String [] regas_close_arr = request.getParameterValues("regas_close");
			String [] chk_flag_arr = request.getParameterValues("chk_flag");
			String [] boe_qty_arr = request.getParameterValues("boe_qty"); //SB20110927
			String [] boe_no_arr = request.getParameterValues("boe_no");
			String [] boe_dt_arr = request.getParameterValues("boe_dt");
			String [] qq_no_arr = request.getParameterValues("qq_no");
			String [] qq_dt_arr = request.getParameterValues("qq_dt");
			
			String spl_msg ="";
			for(int i=0; i<chk_flag_arr.length; i++)
			{
				int cargo_seq_no = i+1;
				
				if(chk_flag_arr[i].equalsIgnoreCase("Y"))
				{
					queryString = "SELECT cargo_ref_no FROM FMS7_RE_GAS_CARGO_DTL " +
					  			  "WHERE customer_cd="+buyer_cd+" AND " +
					  			  "re_gas_no="+re_gas_no+" AND CARGO_SEQ_NO="+cargo_seq_no+"";			
					//System.out.println("Checking Query For RE-GAS Cargo Details Existance = "+queryString);
				//	System.out.println("REGAS:QRY-R1001:SELECT:FMS7_RE_GAS_CARGO_DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						query1 = "DELETE FROM FMS7_RE_GAS_CARGO_DTL WHERE customer_cd="+buyer_cd+" AND " +
								 "re_gas_no="+re_gas_no+" AND CARGO_SEQ_NO="+cargo_seq_no+"";
						//System.out.println("Delete Query For RE-GAS Cargo Details = "+query1);
				//		System.out.println("REGAS:QRY-R1001A:DELETE:FMS7_RE_GAS_CARGO_DTL: "+query1);
						stmt1.executeUpdate(query1);
					}
					String cargo_ref_no = cargo_ref_no_tmp[i]==null?"":cargo_ref_no_tmp[i];
					String ship_cd = ship_cd_tmp[i]==null?"":ship_cd_tmp[i];//System.out.println("*********"+supp_cd_tmp[i]);
					String supp_cd = supp_cd_tmp[i]==null?"":supp_cd_tmp[i]; //System.out.println("*********"+supp_cd);
					String supp_nm = supp_nm_tmp[i]==null?"":supp_nm_tmp[i];//MD20111020
					String ship_name = ship_name_tmp[i]==null?"":ship_name_tmp[i];
					String edq_from_dt = edq_from_dt_tmp[i]==null?"":edq_from_dt_tmp[i];
					String edq_to_dt = edq_to_dt_tmp[i]==null?"":edq_to_dt_tmp[i];
					String actual_receipt_dt = actual_receipt_dt_tmp[i]==null?"":actual_receipt_dt_tmp[i];
					String contract_start_dt = contract_start_dt_tmp[i]==null?"":contract_start_dt_tmp[i];
					String contract_end_dt = contract_end_dt_tmp[i]==null?"":contract_end_dt_tmp[i];
					String adq_qty = adq_qty_tmp[i]==null?"":adq_qty_tmp[i];
					String sug_percentage = sug_percentage_tmp[i]==null?"":sug_percentage_tmp[i];
					String qty_to_be_supplied = qty_to_be_supplied_tmp[i]==null?"":qty_to_be_supplied_tmp[i];
					String dcq_qty = dcq_qty_tmp[i]==null?"":dcq_qty_tmp[i];
					String service_tariff = service_tariff_tmp[i]==null?"":service_tariff_tmp[i];
					String regas_request = regas_request_arr[i]==null?"":regas_request_arr[i];            
					String regas_close = regas_close_arr[i]==null?"":regas_close_arr[i];
					String boe_qty = boe_qty_arr[i]==null?"":boe_qty_arr[i]; //SB20110927
					String boe_no = boe_no_arr[i]==null?"":boe_no_arr[i];            
					String boe_dt = boe_dt_arr[i]==null?"":boe_dt_arr[i];
					String qq_no = qq_no_arr[i]==null?"":qq_no_arr[i];            
					String qq_dt = qq_dt_arr[i]==null?"":qq_dt_arr[i];
									
				
					String tempAdvanceCheck = "advance"+i;	//RG20140924
					String adjust_advance = request.getParameter(tempAdvanceCheck)==null?"N":request.getParameter(tempAdvanceCheck);
					
					String advance_amt="advance_amount"+i;
					String adv_amt = request.getParameter(advance_amt)==null?"":request.getParameter(advance_amt);
					
					String discount_amt="discount_amount"+i;
					String dis_amt = request.getParameter(discount_amt)==null?"":request.getParameter(discount_amt);
					
					String tariff_amt="tariff_inr_amount"+i;
					String trf_amt = request.getParameter(tariff_amt)==null?"":request.getParameter(tariff_amt);
					
					String tempAdvanceCur = "advance_cur"+i;	//RG20140924
					String advance_cur = request.getParameter(tempAdvanceCur)==null?"":request.getParameter(tempAdvanceCur);
					
					String tempDiscountCheck = "discount"+i;	//RG20140924
					String discount = request.getParameter(tempDiscountCheck)==null?"N":request.getParameter(tempDiscountCheck);
					
					String tempTariffCheck = "tariff_inr"+i;	//RG20140924
					String tariff_inr = request.getParameter(tempTariffCheck)==null?"N":request.getParameter(tempTariffCheck);
					
					//////////////////////*****RG 07102014 For advance clause
					String mapping_id=""+buyer_cd+"-"+re_gas_no+"-"+0+"-"+cargo_seq_no+"-"+0+"-"+"R";
					query = "DELETE FROM FMS7_CONT_PRICE_DTL WHERE MAPPING_ID='"+mapping_id+"' ";
					//System.out.println("Deleting data from conract_price_dtl..."+query);
					stmt.executeUpdate(query);
					
					if(adjust_advance.equalsIgnoreCase("Y")) 
					{
						query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
								"VALUES('"+mapping_id+"','1','"+adv_amt+"','"+advance_cur+"','Y')";
						//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
						stmt.executeUpdate(query);
					}
					if(discount.equalsIgnoreCase("Y"))
					{
						query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
								"VALUES('"+mapping_id+"','2','"+dis_amt+"','2','Y')";
						//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
						stmt.executeUpdate(query);
					}
					if(tariff_inr.equalsIgnoreCase("Y"))
					{
						query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
								"VALUES('"+mapping_id+"','3','"+trf_amt+"','1','Y')";
						//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
						stmt.executeUpdate(query);
					}
					/*********************************************************/
					
					queryString = "INSERT INTO FMS7_RE_GAS_CARGO_DTL(cargo_ref_no, customer_cd, " +
								  "re_gas_no, re_gas_rev_no, cargo_seq_no, edq_from_dt, edq_to_dt, " +
								  "actual_recpt_dt, contract_start_dt, contract_end_dt, ship_cd, " +
								  "ship_name, adq_qty, sys_use_gas, qty_to_be_supply, dcq_qty, " +
								  "re_gas_tarif, qty_unit_cd, emp_cd, ent_dt, flag," +
								  "REGAS_CLOSURE_REQUEST, REGAS_CLOSURE_CLOSE, boe_no, boe_dt, BOE_QTY, SUPP_CD, SUPP_NM,QQ_NO,QQ_DT " +
								  ") " +		
								  "VALUES('"+cargo_ref_no+"', '"+buyer_cd+"', '"+re_gas_no+"', " +
								  "'"+re_gas_rev_no+"', '"+cargo_seq_no+"', TO_DATE('"+edq_from_dt+"','DD/MM/YYYY'), " +
								  "TO_DATE('"+edq_to_dt+"','DD/MM/YYYY'), TO_DATE('"+actual_receipt_dt+"','DD/MM/YYYY'), " +
								  "TO_DATE('"+contract_start_dt+"','DD/MM/YYYY'), TO_DATE('"+contract_end_dt+"','DD/MM/YYYY'), " +
								  "'"+ship_cd+"', '"+ship_name+"', '"+adq_qty+"', '"+sug_percentage+"', '"+qty_to_be_supplied+"', " +
								  "'"+dcq_qty+"','"+service_tariff+"','1','"+user_cd+"',sysdate,'Y'," +
								  "'"+regas_request+"','"+regas_close+"','"+boe_no+"',TO_DATE('"+boe_dt+"','dd/mm/yyyy')," +
								  "'"+boe_qty+"','"+supp_cd+"','"+supp_nm+"','"+qq_no+"',TO_DATE('"+qq_dt+"','dd/mm/yyyy')" +
								  ")";					
				//	//System.out.println("Insert Query For RE-GAS Cargo Details = "+queryString);
			//		System.out.println("REGAS:QRY-R1002:INSERT:FMS7_RE_GAS_CARGO_DTL: "+queryString);
					stmt.executeUpdate(queryString);
					spl_msg += ship_name +" ";
				}
			}
			
			dbcon.commit();			
			//msg = "RE-GAS Cargo Details Submited Successfully For The Selected RE-GAS Contract !!!";
			msg = "RE-GAS Cargo Details of "+spl_msg +"Submitted "; //SB20110927
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		//	System.out.println("Milan >>>>>>>RE gas insertModifyReGasCargoDetails() end");
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="RE-GAS Cargo Details NOT Submited For The Selected RE-GAS Contract !!!";
		}
		
		url="../contract_master/frm_RE_GAS_Cargo_dtl.jsp?buyer_nm="+buyer_nm+"&buyer_cd="+buyer_cd+"&re_gas_no="+re_gas_no+"&re_gas_rev_no="+re_gas_rev_no+"&no_of_cargo="+no_of_cargo+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;	
	}//End Of Method insertModifyReGasCargoDetails() ...
	
	
	public void modifySnTcqCargoDetails(HttpServletRequest request) throws Exception
	{
		methodName = "modifySnTcqCargoDetails()";
		
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
								
		String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
		String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_revno = request.getParameter("fgsa_revno")==null?"":request.getParameter("fgsa_revno");
		String sn_cd = request.getParameter("sn_cd")==null?"":request.getParameter("sn_cd");
		String sn_revno = request.getParameter("sn_revno")==null?"":request.getParameter("sn_revno");
		String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String dummy_cargo_flag = request.getParameter("dummy_cargo_flag")==null?"":request.getParameter("dummy_cargo_flag");
		//String variable_tcq = request.getParameter("var_tcq")==null?"0":request.getParameter("var_tcq");
		String sale_price = request.getParameter("sale_price")==null?"0":request.getParameter("sale_price");
		String tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
		String tcq_sign = request.getParameter("tcq_sign")==null?"+":request.getParameter("tcq_sign");
		String sn_closure_flag = request.getParameter("sn_closure_flag")==null?"N":request.getParameter("sn_closure_flag");
		String sn_closure_dt = request.getParameter("sn_closure_dt")==null?"":request.getParameter("sn_closure_dt");
		String tcq_approval_dt = request.getParameter("tcq_approval_dt")==null?"":request.getParameter("tcq_approval_dt");
		
		String split_tcq_flag = request.getParameter("split_tcq_flag")==null?"N":request.getParameter("split_tcq_flag");
		String firm_qty = request.getParameter("firm_qty")==null?"0":request.getParameter("firm_qty");
		String re_qty = request.getParameter("re_qty")==null?"0":request.getParameter("re_qty");
		String own_cargo_flag = request.getParameter("own_cargo_flag")==null?"":request.getParameter("own_cargo_flag");
		
		try
		{
			
			String dummy_cargo_year = "";
			//System.out.println(">>>>>>>>>>>>");
			if(tcq_approval_dt.trim().length()>=10)
			{
				dummy_cargo_year = tcq_approval_dt.trim().substring(6);
			}
			//System.out.println(">>>>>>>>>>>>");
			String [] chk_flg = request.getParameterValues("chk_flg");
			String [] cargo_ref_no = request.getParameterValues("cargo_ref_no");
			String [] tcq_qty = request.getParameterValues("tcq_qty");
			String [] cost_price = request.getParameterValues("cost_price");
			//System.out.println(">>>>>>>>>>>>");
			String dummy_tcq_qty = request.getParameter("dummy_tcq_qty")==null?"0":request.getParameter("dummy_tcq_qty");
			String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
			
			String hlpl_own_tcq_qty = request.getParameter("hlpl_own_tcq_qty")==null?"0":request.getParameter("hlpl_own_tcq_qty");
			String eff_dt_1 = request.getParameter("eff_dt_1")==null?"":request.getParameter("eff_dt_1");
			
			//System.out.println(">>>>>>>>>>>>");
			int sn_rev_no = 0;
			int count = 0;
			double var_tcq = 0;
			double margin = 0; //SB20111103
			int seq_no = 1; //For Generating Max SEQ NO If Dummy Cargo Volume Needs To Be Utilized ... 
			//System.out.println(">>>>>>>>>>>> "+chk_flg);	
			
			String only_dummy_cargo_flg = request.getParameter("only_dummy_cargo_flg")==null?"0":request.getParameter("only_dummy_cargo_flg");
			String only_own_cargo_flg = request.getParameter("only_own_cargo_flg")==null?"0":request.getParameter("only_own_cargo_flg");
			
			
			if(!only_dummy_cargo_flg.equals("ONLY") && !only_own_cargo_flg.equals("OWN"))
			{
				for(int i=0; i<chk_flg.length; i++)
				{
					//System.out.println(">>>>>>>>>>>>");
					if(chk_flg[i].equalsIgnoreCase("Y"))
					{
						//System.out.println(">>>>>>>>>>>>");
						sn_rev_no = 0;
						queryString = "SELECT MAX(SN_REV_NO) " +
									  "FROM FMS7_SN_CARGO_DTL " +
									  "WHERE CUSTOMER_CD="+buyer_cd+" And FGSA_NO="+fgsa_cd+" AND " +
									  "FGSA_REV_NO="+fgsa_revno+" AND SN_NO="+sn_cd+" AND " +
									  "CARGO_REF_NO="+cargo_ref_no[i]+"";
						//System.out.println(">>>>>>>>>>>>");
						//System.out.println("Fetching Max SN Rev NO for Cargo Allocation SN TCQ Details Modification = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							if(rset.getString(1)!=null && !rset.getString(1).equals(""))
							{
								sn_rev_no = Integer.parseInt(rset.getString(1))+1;
							}
						}
					
						if(tcq_sign.equals("-"))
						{
							tcq_qty[i] = "-"+tcq_qty[i];
						}
						//System.out.println(">>>>>>>>>> "+buyer_cd+" "+buyer_name+" "+fgsa_cd+" "+sn_cd+" "+dummy_cargo_flag);
						query = "INSERT INTO FMS7_SN_CARGO_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, " +
								"SN_NO, SN_REV_NO, CARGO_REF_NO, ALLOC_QTY, QTY_UNIT, SALE_PRICE, " +
								"COST_PRICE, RATE_UNIT, STATUS, EMP_CD, ENT_DT, FLAG) " +
								"VALUES("+buyer_cd+","+fgsa_cd+","+fgsa_revno+","+sn_cd+","+sn_rev_no+"," +
								""+cargo_ref_no[i]+","+tcq_qty[i]+",1,"+sale_price+"," +
								""+cost_price[i]+",'2','Y',"+user_cd+",sysdate,'T') ";
						
						//SB20111103: Added Margin while allocating Cargo i.e., (Sale - Cost)
						/*SB20111103			margin = Double.parseDouble(sale_price) - Double.parseDouble(cost_price[i]); 
						query = "INSERT INTO FMS7_SN_CARGO_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, " +
						"SN_NO, SN_REV_NO, CARGO_REF_NO, ALLOC_QTY, QTY_UNIT, SALE_PRICE, " +
						"COST_PRICE, RATE_UNIT, STATUS, EMP_CD, ENT_DT, FLAG, MARGIN) " +
						"VALUES("+buyer_cd+","+fgsa_cd+","+fgsa_revno+","+sn_cd+","+sn_rev_no+"," +
						""+cargo_ref_no[i]+","+tcq_qty[i]+",1,"+sale_price+"," +
						""+cost_price[i]+",'2','Y',"+user_cd+",sysdate,'T', '"+margin+"') ";
						*/
						//System.out.println(">>>>>>>>>>>>MARGIN ADDED:SB");
						//System.out.println("Query For Inserting Modified SN TCQ Details = "+query);
						stmt.executeUpdate(query);
						//System.out.println(">>>>>>>>>> "+buyer_cd+" "+buyer_name+" "+fgsa_cd+" "+sn_cd+" "+dummy_cargo_flag);
						var_tcq += Double.parseDouble(tcq_qty[i]);
						++count;
					}
				}
			}
			if(dummy_tcq_qty.trim().equals(""))
			{
				dummy_tcq_qty = "0";
			}
			
			if(hlpl_own_tcq_qty.trim().equals(""))
			{
				hlpl_own_tcq_qty = "0";
			}
			
			if(dummy_cargo_flag.equalsIgnoreCase("DUMMY") && Double.parseDouble(dummy_tcq_qty)>=0.99 && dummy_cargo_year.trim().length()==4)
			{
				//System.out.println(">>>>>>>>>>>>");
				queryString = "SELECT MAX(SEQ_NO) FROM FMS7_DUMMY_CARGO_DTL " +
							  "WHERE YEAR="+dummy_cargo_year.trim()+"";				
				//System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1)!=null && !rset.getString(1).equals(""))
					{
						seq_no = Integer.parseInt(rset.getString(1))+1;
					}
				}
				
				String dummy_cost_price = null;
				
				if(tcq_sign.equals("-"))
				{
					dummy_tcq_qty = "-"+dummy_tcq_qty;
				}
				
				String dummy_price = "0";
				double conf_price = 0;
				double conf_vol = 0;
				double total_conf_vol = 0;
				double total_of_product_vol_price = 0;
				
				query = "SELECT NVL(B.confirm_price,'0'),NVL(A.price,'0')," +
						"NVL(A.confirm_vol,'0'),NVL(A.unit_cd,'1')," +
						"NVL(A.cargo_ref_cd,'0'),TO_CHAR(A.delv_from_dt,'dd/mm/yyyy') " +
						"FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST B " +
						"WHERE A.man_conf_cd=B.man_conf_cd AND A.man_cd=B.man_cd AND " +
						"A.cargo_status='T' AND A.delv_from_dt BETWEEN " +
						"TO_DATE('01/01/"+dummy_cargo_year.trim()+"','dd/mm/yyyy') AND " +
						"TO_DATE('"+tcq_approval_dt.trim()+"','dd/mm/yyyy')";
				rset = stmt.executeQuery(query);
				
				while(rset.next())
				{
					String delv_to_dt = rset.getString(6)==null?"":rset.getString(6);
					int cargo_ref_number = rset.getInt(5);
					conf_price = rset.getDouble(2);
					if(conf_price<=0.01)
					{
						conf_price = rset.getDouble(1);
					}
					
					queryString = "SELECT NVL(qty_mmbtu,'0') FROM FMS7_CARGO_QQ_DTL " +
								  "WHERE cargo_ref_no="+cargo_ref_number+" AND SPLIT_SEQ='0'";
					rset1 = stmt1.executeQuery(queryString);
					
					if(rset1.next())
					{
						conf_vol = rset1.getDouble(1);
						
						if(conf_vol<=0.99)
						{
							conf_vol = rset.getDouble(3);
							
							if(rset.getString(4).trim().equals("2"))
							{
								conf_vol *= 1000000;
							}
						}
					}
					else
					{
						conf_vol = rset.getDouble(3);
						
						if(rset.getString(4).trim().equals("2"))
						{
							conf_vol *= 1000000;
						}
					}
					
					//Following Logic Has Been Introduced By Samik Shah On 28th April, 2011 ...
					//Following Logic Has Been Introduced To Calculate Custom Tax Amount ...
				  	String tax_amt = "";
				  	String tax_str_cd = "0";
				  	double cd_charge_per_mmbtu = 0;
					
					String queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
								  		  "WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B " +
								  		  "WHERE B.APP_DATE<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
					////System.out.println("Custom Duty Details Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
					}
					
					//System.out.println("tax_str_cd = "+tax_str_cd);
					
					queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
								   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
								   "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
					//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						if(rset1.getString(3).equals("1"))
						{
							tax_amt = nf3.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset1.getString(2)))/100);
						}
						else if(rset1.getString(3).equals("2"))
						{
							String queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										   		  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
										   		  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
										   		  "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
							//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2);
					 		rset2=stmt2.executeQuery(queryString2);
					 		if(rset2.next())
					 		{
					 			if(rset2.getString(3).equals("1"))
								{
									tax_amt = nf3.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset2.getString(2)))/100);
								}
								
					 			tax_amt = nf3.format((Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100);
					 		}
					 		else
					 		{
					 			tax_amt = ""+0.00;
					 		}			 		
						}
						else
						{
							tax_amt = ""+0.00;
						}
						
						cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
					}
					
					total_conf_vol += conf_vol;
					total_of_product_vol_price += (conf_vol*(conf_price+cd_charge_per_mmbtu));
				}
				
				if(total_conf_vol>0)
				{
					dummy_price = nf2.format(total_of_product_vol_price/total_conf_vol);
				}	
				
				if(Double.parseDouble(dummy_price)<=0.01)
				{
					int prev_year_max_seq_no = 0;
					queryString = "SELECT MAX(SEQ_NO) FROM FMS7_DUMMY_CARGO_DTL " +
								  "WHERE YEAR="+(Integer.parseInt(dummy_cargo_year.trim())-1)+"";				
					//System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).trim().equals(""))
						{
							prev_year_max_seq_no = Integer.parseInt(rset.getString(1).trim());
						}
					}
					
					queryString = "SELECT NVL(DUMMY_PRICE,'0') FROM FMS7_DUMMY_CARGO_DTL " +
								  "WHERE YEAR="+(Integer.parseInt(dummy_cargo_year.trim())-1)+" AND " +
								  "SEQ_NO="+prev_year_max_seq_no+"";				
					//System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).trim().equals(""))
						{
							dummy_price = nf2.format(Double.parseDouble(rset.getString(1).trim()));
						}
					}
				}
				
				query = "INSERT INTO FMS7_DUMMY_CARGO_DTL(SEQ_NO, CUSTOMER_CD, FGSA_NO, " +
						"FGSA_REV_NO, SN_NO, ALLOC_QTY, QTY_UNIT, SALE_PRICE, " +
						"COST_PRICE, RATE_UNIT, STATUS, EFF_DT, EMP_CD, ENT_DT, FLAG, " +
						"YEAR, CONTRACT_TYPE, DUMMY_PRICE, TRANSACTION_DT) " +
						"VALUES("+seq_no+","+buyer_cd+","+fgsa_cd+","+fgsa_revno+"," +
						""+sn_cd+","+dummy_tcq_qty+",1,"+sale_price+"," +
						""+dummy_cost_price+",'2','Y',TO_DATE('"+eff_dt+"','DD/MM/YYYY')," +
						""+user_cd+",sysdate,'T',"+dummy_cargo_year.trim()+",'S'," +
						""+dummy_price+",TO_DATE('"+tcq_approval_dt.trim()+"','dd/mm/yyyy'))";
				
				//System.out.println("Query For Inserting Modified SN TCQ Details Under Dummy Cargo = "+query);
				stmt.executeUpdate(query);
				
				var_tcq += Double.parseDouble(dummy_tcq_qty);
				++count;
			}

			//RS 09052017
			
			if(own_cargo_flag.equalsIgnoreCase("OWN VOLUME ACCOUNT") && Double.parseDouble(hlpl_own_tcq_qty)>=0.99 && dummy_cargo_year.trim().length()==4)
			{
				queryString = "SELECT MAX(SEQ_NO) FROM FMS8_OWN_CARGO_DTL " +
							  "WHERE YEAR="+dummy_cargo_year.trim()+"";				
//				System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1)!=null && !rset.getString(1).equals(""))
					{
						seq_no = Integer.parseInt(rset.getString(1))+1;
					}
				}
				
				if(tcq_sign.equals("-"))
				{
					hlpl_own_tcq_qty = "-"+hlpl_own_tcq_qty;
				}
				
				String dummy_price = "0";
				
				if(Double.parseDouble(dummy_price)<=0.01)
				{
					int prev_year_max_seq_no = 0;
					queryString = "SELECT MAX(SEQ_NO) FROM FMS7_DUMMY_CARGO_DTL " +
								  "WHERE YEAR="+(Integer.parseInt(dummy_cargo_year.trim())-1)+"";				
//					System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).trim().equals(""))
						{
							prev_year_max_seq_no = Integer.parseInt(rset.getString(1).trim());
						}
					}
					
					queryString = "SELECT NVL(DUMMY_PRICE,'0') FROM FMS7_DUMMY_CARGO_DTL " +
								  "WHERE YEAR="+(Integer.parseInt(dummy_cargo_year.trim())-1)+" AND " +
								  "SEQ_NO="+prev_year_max_seq_no+"";				
//					System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).trim().equals(""))
						{
							dummy_price = nf2.format(Double.parseDouble(rset.getString(1).trim()));
						}
					}
				}
				
				query = "INSERT INTO FMS8_OWN_CARGO_DTL(SEQ_NO, CUSTOMER_CD, FGSA_NO, " +
						"FGSA_REV_NO, SN_NO, ALLOC_QTY, QTY_UNIT, SALE_PRICE, " +
						"COST_PRICE, RATE_UNIT, STATUS, EFF_DT, EMP_CD, ENT_DT, FLAG, " +
						"YEAR, CONTRACT_TYPE, DUMMY_PRICE, TRANSACTION_DT) " +
						"VALUES("+seq_no+","+buyer_cd+","+fgsa_cd+","+fgsa_revno+"," +
						""+sn_cd+","+hlpl_own_tcq_qty+",1,"+sale_price+"," +
						"'0','2','Y',TO_DATE('"+eff_dt_1+"','DD/MM/YYYY')," +
						""+user_cd+",sysdate,'T',"+dummy_cargo_year.trim()+",'S'," +
						""+dummy_price+",TO_DATE('"+tcq_approval_dt.trim()+"','dd/mm/yyyy'))";
				
//				System.out.println("Query For Inserting Modified SN TCQ Details Under Dummy Cargo = "+query);
				stmt.executeUpdate(query);
				
				var_tcq += Double.parseDouble(hlpl_own_tcq_qty);
				++count;
			}
			
			
			if(tcq.trim().equals(""))
			{
				tcq = "0";
			}
			
			if(tcq_sign.equals("-"))
			{
				firm_qty = "-"+firm_qty;
				re_qty = "-"+re_qty;
			}
			
			var_tcq += Double.parseDouble(tcq);
			
			String modified_tcq = nf.format(var_tcq);
			
			if(sn_closure_flag.equalsIgnoreCase("N"))
			{
				queryString = "UPDATE FMS7_SN_MST SET TCQ="+modified_tcq+", SN_CLOSURE_FLAG='N', TCQ_REQUEST_CLOSE='Y' " +
							  "" +
							  " ,TCQ_APPROVAL_DT = to_date('"+tcq_approval_dt.trim()+"', 'dd/mm/yyyy'), "
							  + "FIRM_QTY = FIRM_QTY + '"+firm_qty+"', RE_QTY = RE_QTY + '"+re_qty+"' " +
						  	  " WHERE CUSTOMER_CD="+buyer_cd+" And FGSA_NO="+fgsa_cd+" AND " +
						  	  " FGSA_REV_NO="+fgsa_revno+" AND SN_NO="+sn_cd+" AND " +
						  	  " SN_REV_NO="+sn_revno+"";
				
				if(count>0)
				{
					msg = "TCQ Modification Details Submited Successfully For SN "+sn_cd+" Of Customer: "+buyer_name;
				}
				else
				{
					msg = "TCQ Modification Details NOT Submited Because: Any One Of The Mandatory Information Is Missing For SN "+sn_cd+" Of Customer: "+buyer_name;
				}
			}
			else
			{
//				SB20111011 queryString = "UPDATE FMS7_SN_MST SET TCQ="+modified_tcq+", SN_CLOSURE_QTY="+tcq+", " +
				queryString = "UPDATE FMS7_SN_MST SET TCQ="+tcq+", SN_CLOSURE_QTY="+modified_tcq+", " +
							  "SN_CLOSURE_FLAG='Y', SN_CLOSURE_DT=TO_DATE('"+sn_closure_dt+"','DD/MM/YYYY'), SN_CLOSURE_CLOSE='Y', " +	//MD20120202
							 " TCQ_APPROVAL_DT = to_date('"+tcq_approval_dt.trim()+"', 'dd/mm/yyyy'), " +
							  "FIRM_QTY = FIRM_QTY + '"+firm_qty+"', RE_QTY = RE_QTY + '"+re_qty+"' " +
						  	  "WHERE CUSTOMER_CD="+buyer_cd+" And FGSA_NO="+fgsa_cd+" AND " +
						  	  "FGSA_REV_NO="+fgsa_revno+" AND SN_NO="+sn_cd+" AND " +
						  	  "SN_REV_NO="+sn_revno+"";				
				if(count>0)
				{
					msg = "SN Closed Successfully For SN "+sn_cd+" Of Customer: "+buyer_name;
				}
				else
				{
					msg = "SN Not Closed Because: Any One Of The Mandatory Information Is Missing For SN "+sn_cd+" Of Customer: "+buyer_name;
				}
			}			
			//System.out.println("Updation Query To Modify TCQ Of SN Master = "+queryString);
			stmt.executeUpdate(queryString);
			//System.out.println(">>>>>>>>>>>> end");
			if(tcq_sign.equals("-"))
			{
				tcq_sign = "MINUS";
			}
			else if(tcq_sign.equals("+"))
			{
				tcq_sign = "PLUS";				
			}
			else
			{
				tcq_sign = "PLUS";
			}
			
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
			e.printStackTrace();
			dbcon.rollback();
			msg="TCQ Details NOT Submited For The Selected SN !!!";
		}
		
		//url="../contract_master/frm_SN_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+fgsa_revno+"&SN_CD="+sn_cd+"&SN_REVNO="+sn_revno+"&FGSA_CD="+fgsa_cd+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"";
		url="../contract_master/frm_tcq_modification.jsp?msg="+msg+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&fgsa_cd="+fgsa_cd+"&var_tcq=0&buyer_cd="+buyer_cd+"&buyer_name="+buyer_name+"&start_dt="+start_dt+"&end_dt="+end_dt+"&sale_price="+sale_price+"&tcq="+tcq+"&tcq_sign="+tcq_sign+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
	}//End Of Method modifySnTcqCargoDetails() ...
	
	
	
	public void insertModifySnDcqDetails(HttpServletRequest request) throws Exception
	{
		methodName = "insertModifySnDcqDetails()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
								
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_revno = request.getParameter("fgsa_revno")==null?"":request.getParameter("fgsa_revno");
		String sn_cd = request.getParameter("sn_cd")==null?"":request.getParameter("sn_cd");
		String sn_revno = request.getParameter("sn_revno")==null?"":request.getParameter("sn_revno");
		String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String contract_type = request.getParameter("contract_type")==null?"S":request.getParameter("contract_type");
	//	String price_type = request.getParameter("price_type")==null?"":request.getParameter("price_type");
	//	String slope = request.getParameter("slope")==null?"1":request.getParameter("slope");
	//	String constant = request.getParameter("constant")==null?"0":request.getParameter("constant");
		String mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno;		
		try
		{
			String [] from_dt = request.getParameterValues("from_dt");
			String [] to_dt = request.getParameterValues("to_dt");
			String [] dcq = request.getParameterValues("dcq");
			String [] remark = request.getParameterValues("remark");
			String [] price_type = request.getParameterValues("price_type");
			String [] slope = request.getParameterValues("slope");
			String [] constant = request.getParameterValues("constant");
			String [] curve_nm = request.getParameterValues("curve_nm");
			String [] price_range = request.getParameterValues("price_range");
			String [] price_range_days = request.getParameterValues("price_range_days");
			String [] price_start_dt = request.getParameterValues("price_start_dt");
			String [] price_end_dt = request.getParameterValues("price_end_dt");
			
		
			int count = 0;
			String priceType = "";
			for(int i=0; i<from_dt.length; i++)
			{
				if(from_dt[i]!=null && to_dt[i]!=null && dcq[i]!=null)
				{
					if(!from_dt[i].trim().equals("") && !from_dt[i].trim().equals("0") 
						&& !to_dt[i].trim().equals("") && !to_dt[i].trim().equals("0") 
						&& !dcq[i].trim().equals(""))
					{
						++count;
					//	String rem = obj.replaceSingleQuotes(remark[i]);
					if(price_type[i].equalsIgnoreCase("Float"))	
						priceType ="M";
					else
						priceType ="F";
					
					
					/*	queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ, PRICE_TYPE, CURVE_NM, SLOPE, CONST, QUANTITY_UNIT, " +
									  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
									  "('"+mapp_id+"','"+contract_type+"',"+count+",TO_DATE('"+from_dt[i]+"','DD/MM/YYYY'),TO_DATE('"+to_dt[i]+"','DD/MM/YYYY')," +
									  ""+dcq[i]+",'"+price_type[i]+"','"+curve_nm[i]+"',"+slope[i]+","+constant[i]+", 1,'"+remark[i]+"',"+user_cd+",sysdate,'Y')";
									  */
					int Cnt=0; //String PriceAvg=price_range[i]+price_range_days[i];
					queryString = "SELECT COUNT(DCQ) " +
							  "FROM FMS9_MRCR_CONT_PRICE_DTL " +
							  "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' AND FROM_DT=TO_DATE('"+from_dt[i]+"','DD/MM/YYYY') AND TO_DT=TO_DATE('"+to_dt[i]+"','DD/MM/YYYY') ";				
								System.out.println("Checking Query For SN DCQ Details Existance = "+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									Cnt=rset.getInt(1);
								}
								if(Cnt==0)
								{
									queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE,  CURVE_NM, SLOPE, CONST,  QUANTITY_UNIT, " +
											  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
											  "('"+mapp_id+"','"+contract_type+"',"+count+",TO_DATE('"+from_dt[i]+"','DD/MM/YYYY'),TO_DATE('"+to_dt[i]+"','DD/MM/YYYY')," +
											  ""+dcq[i]+",'"+priceType+"', '"+curve_nm[i]+"',"+slope[i]+","+constant[i]+", 1,'"+remark[i]+"',"+user_cd+",sysdate,'Y')";						
									System.out.println("Insert Query For SN DCQ Details = "+queryString);
									stmt.executeUpdate(queryString);
								}
								else
								{
									if(dcq[i].equals("0"))
									{
										query1 = "DELETE FROM FMS9_MRCR_CONT_PRICE_DTL  " +
												 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' AND FROM_DT=TO_DATE('"+from_dt[i]+"','DD/MM/YYYY') AND TO_DT=TO_DATE('"+to_dt[i]+"','DD/MM/YYYY') ";						
										System.out.println("Delete Query For SN DCQ Details = "+query1);
										stmt1.executeUpdate(query1);
									}
									else
									{
						/*SB20200930			query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET DCQ="+dcq[i]+", SLOPE="+slope[i]+", CONST="+constant[i]+", PRICE_TYPE='"+priceType+"', CURVE_NM='"+curve_nm[i]+"', REMARKS='"+remark[i]+"' " +
											 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' AND FROM_DT=TO_DATE('"+from_dt[i]+"','DD/MM/YYYY') AND TO_DT=TO_DATE('"+to_dt[i]+"','DD/MM/YYYY') ";	
									*/
									query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET SLOPE="+slope[i]+", CONST="+constant[i]+", PRICE_TYPE='"+priceType+"', CURVE_NM='"+curve_nm[i]+"', REMARKS='"+remark[i]+"' "
								///			+ ", price_range='"+price_range[i]+"', price_start_dt=TO_DATE('"+price_start_dt[i]+"','DD/MM/YYYY'), price_end_dt=TO_DATE('"+price_end_dt[i]+"','DD/MM/YYYY') " + //SB20201129
											 +"WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+contract_type+"' AND FROM_DT=TO_DATE('"+from_dt[i]+"','DD/MM/YYYY') AND TO_DT=TO_DATE('"+to_dt[i]+"','DD/MM/YYYY') ";	
									System.out.println("Delete Query For SN DCQ Details = "+query1);
									stmt1.executeUpdate(query1);
									}
								}
					}
				}
			}
			
			dbcon.commit();
			
			if(count>0)
			{
				msg = "Price Details Submited Successfully For The Selected Contract !!!";
			}
			else
			{
				msg = "Price Details NOT Submited Because: Any One Of The Mandatory Information Is Missing !!!";
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
			dbcon.rollback();
			e.printStackTrace();
			msg="Price Details NOT Submited For The Selected SN !!!";
		}
		
		url="../contract_master/frm_MR_var_dcq.jsp?buyer_cd="+buyer_cd+"&cont_type="+contract_type+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
/////////////////////////NEW Function/////////////////////////////////////////
////HARSH20201201/////////
	public void insertModifyDeleteSnDcqDetails(HttpServletRequest request) throws Exception
	{
		methodName = "insertModifySnDcqDetails()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
		String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name"); //HARSH20210809
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_revno = request.getParameter("fgsa_revno")==null?"":request.getParameter("fgsa_revno");
		String sn_cd = request.getParameter("sn_cd")==null?"":request.getParameter("sn_cd");
		String sn_revno = request.getParameter("sn_revno")==null?"":request.getParameter("sn_revno");
		String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String cont_type = request.getParameter("cont_type")==null?"E":request.getParameter("cont_type");
		
		try
		{
			String optional = request.getParameter("optional")==null?"":request.getParameter("optional");
			String seq_no = request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
			
			String mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno;
			
			String from_dt = request.getParameter("from_dt");
			String to_dt = request.getParameter("to_dt");
			String dcq = request.getParameter("dcq");
			String remark = request.getParameter("remark");
			String price_type = request.getParameter("price_type");
			String slope = request.getParameter("slope");
			String constant = request.getParameter("constant");
			String curve_nm[] = request.getParameterValues("curve_nm"); //HARSH20210728
			String phys_curve_nm = request.getParameter("phys_curve_nm"); //SB2021008
			String price_range = request.getParameter("price_range");
			String days = request.getParameter("days"); //HARSH20210729
			
			String MIN_price_range[] = request.getParameterValues("MIN_price_range"); //FOR MIN ONLY HARSH20210820
			String MIN_days[] = request.getParameterValues("MIN_days"); //FOR MIN ONLY HARSH20210820
			String MIN_price_start_dt[] = request.getParameterValues("MIN_price_start_dt");//FOR MIN ONLY HARSH20210820
			String MIN_price_end_dt[] = request.getParameterValues("MIN_price_end_dt");
			
			//HARSH20210729
			if(days != null)
			{
				if(price_range.equals("O"))
				{
					price_range="A"+days;
				}
			}
			
			if(price_range.equals("D")) //HARSH20210809
			{
				price_range="A";
			}
			//////////////////
			
			String Min_slope[] = request.getParameterValues("MIN_slope"); //HARSH20210728
			String Min_constant[] = request.getParameterValues("MIN_constant"); //HARSH20210728
			
			String opal_min = request.getParameter("opal_min")==null?"":request.getParameter("opal_min");//HARSH20210728
			
			String PPAC_Price = request.getParameter("ppac_price")==null?"":request.getParameter("ppac_price"); //HARSH20210426
			String PPAC_Validity_dt = request.getParameter("ppac_validity_dt")==null?"":request.getParameter("ppac_validity_dt"); //HARSH20210426
			String price_start_dt = request.getParameter("price_start_dt")==null?"":request.getParameter("price_start_dt"); //SB20210511
			String price_end_dt = request.getParameter("price_end_dt")==null?"":request.getParameter("price_end_dt"); //SB20210511
			
			String priceType = ""; int count = 0;
			if(price_type.equalsIgnoreCase("Float") || price_type.equalsIgnoreCase("Float(Complex)")) //HARSH20210809 FLOAT(COMPLEX) ADDED	
				priceType ="M";
			else
				priceType ="F";
			
			String mm_id= buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno; //HARSH20210930 MAPPING ID WITHOUT %
			
			if(optional.equalsIgnoreCase("DELETE"))
			{
				if(!cont_type.equals("B")) //SB20210528
					mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-%";	 //SB20210528
				
				if(!seq_no.equals(""))
				{
					queryString="DELETE FROM FMS9_MRCR_CONT_PRICE_DTL  " +
							//SB20210528		"WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";	
					"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";	 //SB20210528
					System.out.println("Delete Query For SN DCQ Details = "+queryString);
					stmt1.executeUpdate(queryString);
					
					//HARSH20210729 /////////////
					if(priceType.equals("M"))
					{
						if(opal_min.equals("MIN"))
						{
							queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
							stmt.executeUpdate(queryString);
						}
					}
					msg="Delete Successfully";
				}
				else
				{
					msg="Data not found for delete!!";
				}
			}
			else if(optional.equalsIgnoreCase("MODIFY"))
			{
				if(!cont_type.equals("B")) //SB20210528
					mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-%";	 //SB20210528
				if(!seq_no.equals(""))
				{
					if(priceType.equals("F"))
					{
						queryString="select nvl(count(*),0) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
						System.out.println("Checking update--"+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							count=rset.getInt(1);
							if(count>0)
							{
								slope="1";
								constant="0";
								
								queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								//HARSH20210803 ONE MORE CONDITION ADDED LIKE SEQ_NO 
								stmt.executeUpdate(queryString);
								
								query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET FROM_DT=TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DT=TO_DATE('"+to_dt+"','DD/MM/YYYY'),SLOPE="+slope+", CONST="+constant+", PRICE_TYPE='"+priceType+"', REMARKS='"+remark+"' "
										+ ",  PHYS_CURVE_NM='"+phys_curve_nm+"', MAPPING_ID='"+mm_id+"' "+ //SB20210308  //HARSH20210426 RATE AND VARIFY_DT ADDED //HARSH20210930 MPPING ID ADDED IN UPDATE
										//SB20210528		 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";  //SB20210528
								System.out.println("Update Query For SN DCQ Details = "+query1);
								stmt1.executeUpdate(query1);
								msg="Modification Done Successfully!!";
							}
							else
							{
								msg="Data not found for Modification!!";
							}
						}
					}
					else if(priceType.equals("M"))
					{
						if(opal_min.equals("MULTI_LEG")) //HARSH20210728
						{
							if(curve_nm != null)
							{
								queryString="select nvl(count(*),0) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								System.out.println("Checking update--"+queryString);
								rset=stmt.executeQuery(queryString);
								if(rset.next())
								{
									count=rset.getInt(1);
									if(count>0)
									{
										queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
										//HARSH20210803 ONE MORE CONDITION ADDED LIKE SEQ_NO 
										stmt.executeUpdate(queryString);
										
										query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET FROM_DT=TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DT=TO_DATE('"+to_dt+"','DD/MM/YYYY'),SLOPE="+slope+", CONST="+constant+", PRICE_TYPE='"+priceType+"', CURVE_NM='"+curve_nm[0]+"', REMARKS='"+remark+"' "
												+ ",  PHYS_CURVE_NM='"+phys_curve_nm+"',RATE='"+PPAC_Price+"', VERY_DT=TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY') "+ //SB20210308  //HARSH20210426 RATE AND VARIFY_DT ADDED
												", PRICE_RANGE='"+price_range+"', PRICE_START_DT=TO_DATE('"+price_start_dt+"','DD/MM/YYYY') , PRICE_END_DT=TO_DATE('"+price_end_dt+"','DD/MM/YYYY'), MAPPING_ID='"+mm_id+"'  "+ //HARSH20210930 MAPPING_ID//SB20210507
												//SB20210528		 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
										"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";  //SB20210528
										System.out.println("Delete Query For SN DCQ Details = "+query1);
										stmt1.executeUpdate(query1);
										msg="Modification Done Successfully!!";
									}
									else
									{
										msg="Data not found for Modification!!";
									}
								}
							}
						}
						else if(opal_min.equals("MIN"))//HARSH20210728
						{
							queryString="select nvl(count(*),0) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
							System.out.println("Checking update--"+queryString);
							rset=stmt.executeQuery(queryString);
							if(rset.next())
							{
								count=rset.getInt(1);
								if(count>0)
								{
									query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET FROM_DT=TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DT=TO_DATE('"+to_dt+"','DD/MM/YYYY'),SLOPE="+slope+", CONST="+constant+", PRICE_TYPE='"+priceType+"', REMARKS='"+remark+"',CURVE_NM='' "
											+ ",  PHYS_CURVE_NM='"+phys_curve_nm+"',RATE='"+PPAC_Price+"', VERY_DT=TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY') "+ //SB20210308  //HARSH20210426 RATE AND VARIFY_DT ADDED
											", PRICE_RANGE='"+price_range+"', PRICE_START_DT=TO_DATE('"+price_start_dt+"','DD/MM/YYYY') , PRICE_END_DT=TO_DATE('"+price_end_dt+"','DD/MM/YYYY'),MAPPING_ID='"+mm_id+"'  "+ //HARSH20210930//SB20210507
											//SB20210528		 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
									"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";  //SB20210528
									System.out.println("Delete Query For SN DCQ Details = "+query1);
									stmt1.executeUpdate(query1);
									msg="Modification Done Successfully!!";
								}
								else
								{
									msg="Data not found for Modification!!";
								}
							}
							if(curve_nm != null && Min_slope != null && Min_constant != null)
							{
								queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								//HARSH20210803 ONE MORE CONDITION ADDED LIKE SEQ_NO 
								stmt.executeUpdate(queryString);
								
								for(int i=0; i<curve_nm.length;i++)
								{
									/*HARSH20210803
									 String new_seq_no="";
									queryString="select max(nvl(seq_no,1)+1) from FMS9_MRCR_CONT_FIN_PRICE_DTL where MAPPING_ID='"+mm_id+"' AND CONTRACT_TYPE='"+cont_type+"'";
									rset=stmt.executeQuery(queryString);
									if(rset.next())
									{
										new_seq_no=rset.getString(1)==null?"1":rset.getString(1);
									}*/
									
									//HARSH20210820 FOR MIN ONLY
									String min_price_range = "";
									if(MIN_price_range[i].equals("D")) //HARSH20210809
									{
										min_price_range="A";
									}
									else if(MIN_price_range[i].equals("O"))
									{
										if(MIN_days[i] != null)
										{
											min_price_range="A"+MIN_days[i];
										}
									}
									else
									{
										min_price_range = MIN_price_range[i];
									}
									if(min_price_range.equals(""))
										min_price_range = "A";
									queryString = "INSERT INTO FMS9_MRCR_CONT_FIN_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,CURVE_NM, SLOPE, CONST, " +
											  "REMARKS, EMP_CD, ENT_DT, FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
											  "('"+mm_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
											  "'"+dcq+"', '"+curve_nm[i]+"','"+Min_slope[i]+"','"+Min_constant[i]+"','"+remark+"','"+user_cd+"',sysdate,'Y','"+min_price_range+"',to_date('"+MIN_price_start_dt[i]+"','dd/mm/yyyy'),to_date('"+MIN_price_end_dt[i]+"','dd/mm/yyyy'))";						
									System.out.println("Insert Query For SN DCQ Details = "+queryString);
									stmt.executeUpdate(queryString);
									msg="Data Inserted Successfully";
								}
							}
						}
						else
						{
							if(curve_nm != null)
							{
								queryString="select nvl(count(*),0) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								System.out.println("Checking update--"+queryString);
								rset=stmt.executeQuery(queryString);
								if(rset.next())
								{
									count=rset.getInt(1);
									if(count>0)
									{
										queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
										//HARSH20210803 ONE MORE CONDITION ADDED LIKE SEQ_NO 
										stmt.executeUpdate(queryString);
										
										query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET FROM_DT=TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DT=TO_DATE('"+to_dt+"','DD/MM/YYYY'),SLOPE="+slope+", CONST="+constant+", PRICE_TYPE='"+priceType+"', CURVE_NM='"+curve_nm[0]+"', REMARKS='"+remark+"' "
												+ ",  PHYS_CURVE_NM='"+phys_curve_nm+"',RATE='"+PPAC_Price+"', VERY_DT=TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY') "+ //SB20210308  //HARSH20210426 RATE AND VARIFY_DT ADDED
												", PRICE_RANGE='"+price_range+"', PRICE_START_DT=TO_DATE('"+price_start_dt+"','DD/MM/YYYY') , PRICE_END_DT=TO_DATE('"+price_end_dt+"','DD/MM/YYYY'),MAPPING_ID='"+mm_id+"'  "+//HARSH20210930 //SB20210507
												//SB20210528		 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
										"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";  //SB20210528
										System.out.println("Delete Query For SN DCQ Details = "+query1);
										stmt1.executeUpdate(query1);
										msg="Modification Done Successfully!!";
									}
									else
									{
										msg="Data not found for Modification!!";
									}
								}
							}
						}
					}
				}
				else
				{
					msg="Data not found for Modification!!";
				}
			}
			else
			{
				if(!cont_type.equals("") && !mapp_id.equals(""))
				{
					if(dcq.equals(""))
						dcq="1";
					
					queryString="select max(nvl(seq_no,1)+1) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"'";
					rset=stmt.executeQuery(queryString);
					if(rset.next())
					{
						seq_no=rset.getString(1)==null?"1":rset.getString(1);
					}
					
					if(priceType.equals("F"))//HARSH20210729
					{
						slope="1";
						constant="0";
						queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE, SLOPE, CONST,  QUANTITY_UNIT, " +
								//SB20210308	  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
								  "REMARKS, EMP_CD, ENT_DT, FLAG, PHYS_CURVE_NM) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
								  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
								  "'"+dcq+"','"+priceType+"','"+slope+"','"+constant+"', 1,'"+remark+"','"+user_cd+"',sysdate,'Y', '"+phys_curve_nm+"')";						
						System.out.println("Insert Query For SN DCQ Details = "+queryString);
						stmt.executeUpdate(queryString);
						msg="Data Inserted Successfully";
					}
					else if(priceType.equals("M"))//HARSH20210729
					{
						if(opal_min.equals("MULTI_LEG")) //HARSH20210728
						{
							if(curve_nm != null)
							{
								queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE,  CURVE_NM, SLOPE, CONST,  QUANTITY_UNIT, " +
										//SB20210308	  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
										  "REMARKS, EMP_CD, ENT_DT, FLAG, PHYS_CURVE_NM,RATE,VERY_DT, PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
										  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
										  "'"+dcq+"','"+priceType+"', '"+curve_nm[0]+"','"+slope+"','"+constant+"', 1,'"+remark+"','"+user_cd+"',sysdate,'Y', '"+phys_curve_nm+"','"+PPAC_Price+"',TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY'),'"+price_range+"',to_date('"+price_start_dt+"','dd/mm/yyyy'),to_date('"+price_end_dt+"','dd/mm/yyyy'))";						
								System.out.println("Insert Query For SN DCQ Details = "+queryString);
								stmt.executeUpdate(queryString);
								msg="Data Inserted Successfully";
							}
						}
						else if(opal_min.equals("MIN"))//HARSH20210728
						{
							
							queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE, SLOPE, CONST,  QUANTITY_UNIT, " +
									//SB20210308	  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
									  "REMARKS, EMP_CD, ENT_DT, FLAG, PHYS_CURVE_NM,RATE,VERY_DT, PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
									  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
									  "'"+dcq+"','"+priceType+"','"+slope+"','"+constant+"', 1,'"+remark+"','"+user_cd+"',sysdate,'Y', '"+phys_curve_nm+"','"+PPAC_Price+"',TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY'),'"+price_range+"',to_date('"+price_start_dt+"','dd/mm/yyyy'),to_date('"+price_end_dt+"','dd/mm/yyyy'))";						
							System.out.println("Insert Query For SN DCQ Details = "+queryString);
							stmt.executeUpdate(queryString);
							msg="Data Inserted Successfully";
							
							if(curve_nm != null && Min_slope != null && Min_constant != null)
							{
								for(int i=0; i<curve_nm.length;i++)
								{
									/*String new_seq_no="";
									queryString="select max(nvl(seq_no,1)+1) from FMS9_MRCR_CONT_FIN_PRICE_DTL where MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"'";
									rset=stmt.executeQuery(queryString);
									if(rset.next())
									{
										new_seq_no=rset.getString(1)==null?"1":rset.getString(1);
									}*/
									
									//HARSH20210820 FOR MIN ONLY
									String min_price_range = "";
									if(MIN_price_range[i].equals("D")) //HARSH20210809
									{
										min_price_range="A";
									}
									else if(MIN_price_range[i].equals("O"))
									{
										if(MIN_days[i] != null)
										{
											min_price_range="A"+MIN_days[i];
										}
									}
									else
									{
										min_price_range = MIN_price_range[i];
									}
									
									queryString = "INSERT INTO FMS9_MRCR_CONT_FIN_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,CURVE_NM, SLOPE, CONST, " +
											  "REMARKS, EMP_CD, ENT_DT, FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
											  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
											  "'"+dcq+"', '"+curve_nm[i]+"','"+Min_slope[i]+"','"+Min_constant[i]+"','"+remark+"','"+user_cd+"',sysdate,'Y','"+min_price_range+"',to_date('"+MIN_price_start_dt[i]+"','dd/mm/yyyy'),to_date('"+MIN_price_end_dt[i]+"','dd/mm/yyyy'))";						
									System.out.println("Insert Query For SN DCQ Details = "+queryString);
									stmt.executeUpdate(queryString);
									msg="Data Inserted Successfully";
								}
							}
						}
						else //HARSH20210729
						{
							if(curve_nm != null)
							{
								queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE,  CURVE_NM, SLOPE, CONST,  QUANTITY_UNIT, " +
										//SB20210308	  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
										  "REMARKS, EMP_CD, ENT_DT, FLAG, PHYS_CURVE_NM,RATE,VERY_DT, PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
										  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
										  "'"+dcq+"','"+priceType+"', '"+curve_nm[0]+"','"+slope+"','"+constant+"', 1,'"+remark+"','"+user_cd+"',sysdate,'Y', '"+phys_curve_nm+"','"+PPAC_Price+"',TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY'),'"+price_range+"',to_date('"+price_start_dt+"','dd/mm/yyyy'),to_date('"+price_end_dt+"','dd/mm/yyyy'))";						
								System.out.println("Insert Query For SN DCQ Details = "+queryString);
								stmt.executeUpdate(queryString);
								msg="Data Inserted Successfully";
							}
						}
					}
				}
				else
				{
					msg="Data not Inserted!!";
				}
			}
			/////LOG FOR CONT PRICE DTL HARSH20210226////////////////
			if(!cont_type.equals("B")) //SB20210528
				mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-%";	 //SB20210528
			if(optional.equalsIgnoreCase("DELETE"))
			{
				if(!seq_no.equals(""))
				{
					queryString="DELETE FROM LOG_FMS9_MRCR_CONT_PRICE_DTL  " +
								"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";						
					//System.out.println("DELETE: SN LOG_FMS9_MRCR_CONT_PRICE_DTL Details = "+queryString);
					stmt1.executeUpdate(queryString);
					
					//HARSH20210930
					queryString="DELETE FROM LOG_FMS9_CONT_FIN_PRICE_DTL  " +
							"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";						
					//System.out.println("DELETE: SN LOG_FMS9_MRCR_CONT_PRICE_DTL Details = "+queryString);
					stmt1.executeUpdate(queryString);
				}
			}
			else
			{
				String log_seq_no = "";
				queryString="select max(nvl(log_seq_no,1)+1) from LOG_FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO = '"+seq_no+"'";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					log_seq_no=rset.getString(1)==null?"1":rset.getString(1);
				}
				
				queryString = "INSERT INTO LOG_FMS9_MRCR_CONT_PRICE_DTL SELECT MAPPING_ID,CONTRACT_TYPE,SEQ_NO,'"+log_seq_no+"',"
						+ "FROM_DT,TO_DT,DCQ,PRICE_TYPE,CURVE_NM,SLOPE,CONST,QUANTITY_UNIT,RATE,RATE_UNIT,REMARKS,'"+user_cd+"',sysdate,"
						+ "VERY_BY,VERY_DT,APPR_BY,APPR_DT,FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT,PHYS_CURVE_NM FROM FMS9_MRCR_CONT_PRICE_DTL "
						+ "WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO = '"+seq_no+"'";
				//System.out.println("INSERT: SN LOG_FMS9_MRCR_CONT_PRICE_DTL Details = "+queryString);
				stmt.executeUpdate(queryString);
				
				if(opal_min.equals("MIN"))//HARSH20210930
				{
					if(curve_nm != null && Min_slope != null && Min_constant != null)
					{
						for(int i=0; i<curve_nm.length;i++)
						{
							int fin_seq_no= i+1;
							
							/*String fin_log_seq_no = "";
							queryString="select max(nvl(log_seq_no,1)+1) from LOG_FMS9_CONT_FIN_PRICE_DTL where "
									+ "MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO = '"+seq_no+"' AND FIN_SEQ_NO='"+fin_seq_no+"'";
							rset=stmt.executeQuery(queryString);
							if(rset.next())
							{
								fin_log_seq_no=rset.getString(1)==null?"1":rset.getString(1);
							}*/
							
							queryString = "INSERT INTO LOG_FMS9_CONT_FIN_PRICE_DTL SELECT MAPPING_ID,CONTRACT_TYPE,SEQ_NO,'"+fin_seq_no+"','"+log_seq_no+"',"
									+ "FROM_DT,TO_DT,DCQ,CURVE_NM,SLOPE,CONST,REMARKS,'"+user_cd+"',sysdate,"
									+ "VERY_BY,VERY_DT,APPR_BY,APPR_DT,FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT FROM FMS9_MRCR_CONT_FIN_PRICE_DTL "
									+ "WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO = '"+seq_no+"' AND CURVE_NM='"+curve_nm[i]+"' ";
							//System.out.println("FIN :: "+queryString);
							stmt.executeUpdate(queryString);
						}
					}
				}
			}
			//////////////////////////////////////////////////////////
			dbcon.commit();
			url="../contract_master/frm_MR_var_dcq.jsp?buyer_name="+buyer_name+"&buyer_cd="+buyer_cd+"&cont_type="+cont_type+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Price Details NOT Submited For The Selected DEAL !!!";
			url="../contract_master/frm_MR_var_dcq.jsp?buyer_name="+buyer_name+"&buyer_cd="+buyer_cd+"&cont_type="+cont_type+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
		}	
	}
///////////////////////////////////////////////////////////////////////////////
	
	//NEW FUNCATION HARSH20210903
	public void insertModifyDeleteSnDcqDetails_V2(HttpServletRequest request) throws Exception
	{
		methodName = "insertModifySnDcqDetails()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
		String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name"); //HARSH20210809
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_revno = request.getParameter("fgsa_revno")==null?"":request.getParameter("fgsa_revno");
		String sn_cd = request.getParameter("sn_cd")==null?"":request.getParameter("sn_cd");
		String sn_revno = request.getParameter("sn_revno")==null?"":request.getParameter("sn_revno");
		String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String cont_type = request.getParameter("cont_type")==null?"S":request.getParameter("cont_type");
		
		try
		{
			String optional = request.getParameter("optional")==null?"":request.getParameter("optional");
			String seq_no = request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
			
			String mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno;
			
			String from_dt = request.getParameter("from_dt");
			String to_dt = request.getParameter("to_dt");
			String dcq = request.getParameter("dcq");
			String remark = request.getParameter("remark");
			String price_type = request.getParameter("price_type");
			String slope = request.getParameter("slope");
			String constant = request.getParameter("constant");
			String curve_nm[] = request.getParameterValues("curve_nm"); //HARSH20210728
			String phys_curve_nm = request.getParameter("phys_curve_nm"); //SB2021008
			String price_range = request.getParameter("price_range");
			String days = request.getParameter("days"); //HARSH20210729
			
			String phys_slope[] = request.getParameterValues("phys_slope"); //HARSH20210902
			String phys_constant[] = request.getParameterValues("phys_constant"); //HARSH20210902
			String phys_fin_curve_nm[] = request.getParameterValues("phys_fin_curve_nm"); //HARSH20210902
			String phys_price_range[] = request.getParameterValues("phys_price_range"); //HARSH20210902
			String phys_days[] = request.getParameterValues("phys_days"); //HARSH20210902
			String phys_price_start_dt[] = request.getParameterValues("phys_price_start_dt");//HARSH20210902
			String phys_price_end_dt[] = request.getParameterValues("phys_price_end_dt");//HARSH20210902
			
			String MIN_price_range[] = request.getParameterValues("MIN_price_range"); //FOR MIN ONLY HARSH20210820
			String MIN_days[] = request.getParameterValues("MIN_days"); //FOR MIN ONLY HARSH20210820
			String MIN_price_start_dt[] = request.getParameterValues("MIN_price_start_dt");//FOR MIN ONLY HARSH20210820
			String MIN_price_end_dt[] = request.getParameterValues("MIN_price_end_dt");
			
			//HARSH20210729
			if(days != null)
			{
				if(price_range.equals("O"))
				{
					price_range="A"+days;
				}
			}
			
			if(price_range.equals("D")) //HARSH20210809
			{
				price_range="A";
			}
			//////////////////
			
			String Min_slope[] = request.getParameterValues("MIN_slope"); //HARSH20210728
			String Min_constant[] = request.getParameterValues("MIN_constant"); //HARSH20210728
			
			String opal_min = request.getParameter("opal_min")==null?"":request.getParameter("opal_min");//HARSH20210728
			
			String PPAC_Price = request.getParameter("ppac_price")==null?"":request.getParameter("ppac_price"); //HARSH20210426
			String PPAC_Validity_dt = request.getParameter("ppac_validity_dt")==null?"":request.getParameter("ppac_validity_dt"); //HARSH20210426
			String price_start_dt = request.getParameter("price_start_dt")==null?"":request.getParameter("price_start_dt"); //SB20210511
			String price_end_dt = request.getParameter("price_end_dt")==null?"":request.getParameter("price_end_dt"); //SB20210511
			
			String priceType = ""; int count = 0;
			if(price_type.equalsIgnoreCase("Float") || price_type.equalsIgnoreCase("Float(Complex)")) //HARSH20210809 FLOAT(COMPLEX) ADDED	
				priceType ="M";
			else
				priceType ="F";
			
			if(optional.equalsIgnoreCase("DELETE"))
			{
				if(!cont_type.equals("B")) //SB20210528
					mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-%";	 //SB20210528
				
				if(!seq_no.equals(""))
				{
					queryString="DELETE FROM FMS9_MRCR_CONT_PRICE_DTL  " +
							//SB20210528		"WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";	
					"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";	 //SB20210528
					System.out.println("Delete Query For SN DCQ Details = "+queryString);
					stmt1.executeUpdate(queryString);
					
					//HARSH20210729 /////////////
					if(priceType.equals("M"))
					{
						if(opal_min.equals("MIN"))
						{
							queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
							stmt.executeUpdate(queryString);
						}
					}
					
					//HARSH20210902
					queryString="DELETE FROM FMS9_MRCR_CONT_PHY_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
					stmt.executeUpdate(queryString);
					
					msg="Delete Successfully";
				}
				else
				{
					msg="Data not found for delete!!";
				}
			}
			else if(optional.equalsIgnoreCase("MODIFY"))
			{
				if(!cont_type.equals("B")) //SB20210528
					mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-%";	 //SB20210528
				if(!seq_no.equals(""))
				{
					if(priceType.equals("F"))
					{
						queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
						//HARSH20210803 ONE MORE CONDITION ADDED LIKE SEQ_NO 
						stmt.executeUpdate(queryString);
						
						queryString="select nvl(count(*),0) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
						System.out.println("Checking update--"+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							count=rset.getInt(1);
							if(count>0)
							{
								slope="1";
								constant="0";
								query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET FROM_DT=TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DT=TO_DATE('"+to_dt+"','DD/MM/YYYY'),SLOPE="+slope+", CONST="+constant+", PRICE_TYPE='"+priceType+"', REMARKS='"+remark+"' "
										+ ",  PHYS_CURVE_NM='"+phys_curve_nm+"' "+ //SB20210308  //HARSH20210426 RATE AND VARIFY_DT ADDED
										//SB20210528		 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";  //SB20210528
								System.out.println("Update Query For SN DCQ Details = "+query1);
								stmt1.executeUpdate(query1);
								msg="Modification Done Successfully!!";
							}
							else
							{
								msg="Data not found for Modification!!";
							}
						}
					}
					else if(priceType.equals("M"))
					{
						//HARSH20210903 if(opal_min.equals("OPAL")) //HARSH20210728
						if(opal_min.equals("MULTI_LEG"))
						{
							if(curve_nm != null)
							{
								queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								//HARSH20210803 ONE MORE CONDITION ADDED LIKE SEQ_NO 
								stmt.executeUpdate(queryString);
								
								queryString="select nvl(count(*),0) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								System.out.println("Checking update--"+queryString);
								rset=stmt.executeQuery(queryString);
								if(rset.next())
								{
									count=rset.getInt(1);
									if(count>0)
									{
										query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET FROM_DT=TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DT=TO_DATE('"+to_dt+"','DD/MM/YYYY'),SLOPE="+slope+", CONST="+constant+", PRICE_TYPE='"+priceType+"', CURVE_NM='"+curve_nm[0]+"', REMARKS='"+remark+"' "
												+ ",  PHYS_CURVE_NM='"+phys_curve_nm+"',RATE='"+PPAC_Price+"', VERY_DT=TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY') "+ //SB20210308  //HARSH20210426 RATE AND VARIFY_DT ADDED
												", PRICE_RANGE='"+price_range+"', PRICE_START_DT=TO_DATE('"+price_start_dt+"','DD/MM/YYYY') , PRICE_END_DT=TO_DATE('"+price_end_dt+"','DD/MM/YYYY')  "+ //SB20210507
												//SB20210528		 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
										"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";  //SB20210528
										System.out.println("Delete Query For SN DCQ Details = "+query1);
										stmt1.executeUpdate(query1);
										msg="Modification Done Successfully!!";
									}
									else
									{
										msg="Data not found for Modification!!";
									}
								}
							}
						}
						else if(opal_min.equals("MIN"))//HARSH20210728
						{
							queryString="select nvl(count(*),0) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
							System.out.println("Checking update--"+queryString);
							rset=stmt.executeQuery(queryString);
							if(rset.next())
							{
								count=rset.getInt(1);
								if(count>0)
								{
									query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET FROM_DT=TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DT=TO_DATE('"+to_dt+"','DD/MM/YYYY'),SLOPE="+slope+", CONST="+constant+", PRICE_TYPE='"+priceType+"', REMARKS='"+remark+"' "
											+ ",  PHYS_CURVE_NM='"+phys_curve_nm+"',RATE='"+PPAC_Price+"', VERY_DT=TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY') "+ //SB20210308  //HARSH20210426 RATE AND VARIFY_DT ADDED
											", PRICE_RANGE='"+price_range+"', PRICE_START_DT=TO_DATE('"+price_start_dt+"','DD/MM/YYYY') , PRICE_END_DT=TO_DATE('"+price_end_dt+"','DD/MM/YYYY')  "+ //SB20210507
											//SB20210528		 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
									"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";  //SB20210528
									System.out.println("Delete Query For SN DCQ Details = "+query1);
									stmt1.executeUpdate(query1);
									msg="Modification Done Successfully!!";
								}
								else
								{
									msg="Data not found for Modification!!";
								}
							}
							if(curve_nm != null && Min_slope != null && Min_constant != null)
							{
								String mm_id= buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno;
								queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								//HARSH20210803 ONE MORE CONDITION ADDED LIKE SEQ_NO 
								stmt.executeUpdate(queryString);
								
								for(int i=0; i<curve_nm.length;i++)
								{
									/*HARSH20210803
									 String new_seq_no="";
									queryString="select max(nvl(seq_no,1)+1) from FMS9_MRCR_CONT_FIN_PRICE_DTL where MAPPING_ID='"+mm_id+"' AND CONTRACT_TYPE='"+cont_type+"'";
									rset=stmt.executeQuery(queryString);
									if(rset.next())
									{
										new_seq_no=rset.getString(1)==null?"1":rset.getString(1);
									}*/
									
									//HARSH20210820 FOR MIN ONLY
									String min_price_range = "";
									if(MIN_price_range[i].equals("D")) //HARSH20210809
									{
										min_price_range="A";
									}
									else if(MIN_price_range[i].equals("O"))
									{
										if(MIN_days[i] != null)
										{
											min_price_range="A"+MIN_days[i];
										}
									}
									else
									{
										min_price_range = MIN_price_range[i];
									}
									if(min_price_range.equals(""))
										min_price_range = "A";
									queryString = "INSERT INTO FMS9_MRCR_CONT_FIN_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,CURVE_NM, SLOPE, CONST, " +
											  "REMARKS, EMP_CD, ENT_DT, FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
											  "('"+mm_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
											  "'"+dcq+"', '"+curve_nm[i]+"','"+Min_slope[i]+"','"+Min_constant[i]+"','"+remark+"','"+user_cd+"',sysdate,'Y','"+min_price_range+"',to_date('"+MIN_price_start_dt[i]+"','dd/mm/yyyy'),to_date('"+MIN_price_end_dt[i]+"','dd/mm/yyyy'))";						
									System.out.println("Insert Query For SN DCQ Details = "+queryString);
									stmt.executeUpdate(queryString);
									msg="Data Inserted Successfully";
								}
							}
						}
						else
						{
							if(curve_nm != null)
							{
								queryString="DELETE FROM FMS9_MRCR_CONT_FIN_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								//HARSH20210803 ONE MORE CONDITION ADDED LIKE SEQ_NO 
								stmt.executeUpdate(queryString);
								
								queryString="select nvl(count(*),0) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
								System.out.println("Checking update--"+queryString);
								rset=stmt.executeQuery(queryString);
								if(rset.next())
								{
									count=rset.getInt(1);
									if(count>0)
									{
										query1 = "UPDATE FMS9_MRCR_CONT_PRICE_DTL SET FROM_DT=TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DT=TO_DATE('"+to_dt+"','DD/MM/YYYY'),SLOPE="+slope+", CONST="+constant+", PRICE_TYPE='"+priceType+"', CURVE_NM='"+curve_nm[0]+"', REMARKS='"+remark+"' "
												+ ",  PHYS_CURVE_NM='"+phys_curve_nm+"',RATE='"+PPAC_Price+"', VERY_DT=TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY') "+ //SB20210308  //HARSH20210426 RATE AND VARIFY_DT ADDED
												", PRICE_RANGE='"+price_range+"', PRICE_START_DT=TO_DATE('"+price_start_dt+"','DD/MM/YYYY') , PRICE_END_DT=TO_DATE('"+price_end_dt+"','DD/MM/YYYY')  "+ //SB20210507
												//SB20210528		 "WHERE MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
										"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";  //SB20210528
										System.out.println("Delete Query For SN DCQ Details = "+query1);
										stmt1.executeUpdate(query1);
										msg="Modification Done Successfully!!";
									}
									else
									{
										msg="Data not found for Modification!!";
									}
								}
							}
						}
					}
					//HARSH20210902
					queryString="DELETE FROM FMS9_MRCR_CONT_PHY_PRICE_DTL WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";
					stmt.executeUpdate(queryString);
					
					if(phys_fin_curve_nm != null && phys_slope != null && phys_constant != null)
					{
						String mm_id= buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno;
						for(int i=0; i<phys_fin_curve_nm.length; i++)
						{
							String phy_price_range = "";
							if(phys_price_range[i].equals("D")) 
							{
								phy_price_range="A";
							}
							else if(phys_price_range[i].equals("O"))
							{
								if(phys_days[i] != null)
								{
									phy_price_range="A"+phys_days[i];
								}
							}
							else
							{
								phy_price_range = phys_price_range[i];
							}
							queryString = "INSERT INTO FMS9_MRCR_CONT_PHY_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,CURVE_NM, SLOPE, CONST, " +
									  "REMARKS, EMP_CD, ENT_DT, FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + 
									  "('"+mm_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
									  "'"+dcq+"', '"+phys_fin_curve_nm[i]+"','"+phys_slope[i]+"','"+phys_constant[i]+"','"+remark+"','"+user_cd+"',sysdate,'Y','"+phy_price_range+"',to_date('"+phys_price_start_dt[i]+"','dd/mm/yyyy'),to_date('"+phys_price_end_dt[i]+"','dd/mm/yyyy'))";						
							//System.out.println("Insert Query For SN DCQ Details = "+queryString);
							stmt.executeUpdate(queryString);
							msg="Data Inserted Successfully";
						}
					}
				}
				else
				{
					msg="Data not found for Modification!!";
				}
			}
			else
			{
				if(!cont_type.equals("") && !mapp_id.equals(""))
				{
					if(dcq.equals(""))
						dcq="1";
					
					queryString="select max(nvl(seq_no,1)+1) from FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"'";
					rset=stmt.executeQuery(queryString);
					if(rset.next())
					{
						seq_no=rset.getString(1)==null?"1":rset.getString(1);
					}
					
					if(priceType.equals("F"))//HARSH20210729
					{
						slope="1";
						constant="0";
						queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE, SLOPE, CONST,  QUANTITY_UNIT, " +
								//SB20210308	  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
								  "REMARKS, EMP_CD, ENT_DT, FLAG, PHYS_CURVE_NM) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
								  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
								  "'"+dcq+"','"+priceType+"','"+slope+"','"+constant+"', 1,'"+remark+"','"+user_cd+"',sysdate,'Y', '"+phys_curve_nm+"')";						
						//System.out.println("Insert Query For SN DCQ Details = "+queryString);
						stmt.executeUpdate(queryString);
						msg="Data Inserted Successfully";
					}
					else if(priceType.equals("M"))//HARSH20210729
					{
						//HARSH20210903 if(opal_min.equals("OPAL")) //HARSH20210728
						if(opal_min.equals("MULTI_LEG"))
						{
							if(curve_nm != null)
							{
								queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE,  CURVE_NM, SLOPE, CONST,  QUANTITY_UNIT, " +
										//SB20210308	  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
										  "REMARKS, EMP_CD, ENT_DT, FLAG, PHYS_CURVE_NM,RATE,VERY_DT, PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
										  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
										  "'"+dcq+"','"+priceType+"', '"+curve_nm[0]+"','"+slope+"','"+constant+"', 1,'"+remark+"','"+user_cd+"',sysdate,'Y', '"+phys_curve_nm+"','"+PPAC_Price+"',TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY'),'"+price_range+"',to_date('"+price_start_dt+"','dd/mm/yyyy'),to_date('"+price_end_dt+"','dd/mm/yyyy'))";						
								//System.out.println("Insert Query For SN DCQ Details = "+queryString);
								stmt.executeUpdate(queryString);
								msg="Data Inserted Successfully";
							}
						}
						else if(opal_min.equals("MIN"))//HARSH20210728
						{
							
							queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE, SLOPE, CONST,  QUANTITY_UNIT, " +
									//SB20210308	  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
									  "REMARKS, EMP_CD, ENT_DT, FLAG, PHYS_CURVE_NM,RATE,VERY_DT, PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
									  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
									  "'"+dcq+"','"+priceType+"','"+slope+"','"+constant+"', 1,'"+remark+"','"+user_cd+"',sysdate,'Y', '"+phys_curve_nm+"','"+PPAC_Price+"',TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY'),'"+price_range+"',to_date('"+price_start_dt+"','dd/mm/yyyy'),to_date('"+price_end_dt+"','dd/mm/yyyy'))";						
							//System.out.println("Insert Query For SN DCQ Details = "+queryString);
							stmt.executeUpdate(queryString);
							msg="Data Inserted Successfully";
							
							if(curve_nm != null && Min_slope != null && Min_constant != null)
							{
								for(int i=0; i<curve_nm.length;i++)
								{
									/*String new_seq_no="";
									queryString="select max(nvl(seq_no,1)+1) from FMS9_MRCR_CONT_FIN_PRICE_DTL where MAPPING_ID='"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"'";
									rset=stmt.executeQuery(queryString);
									if(rset.next())
									{
										new_seq_no=rset.getString(1)==null?"1":rset.getString(1);
									}*/
									
									//HARSH20210820 FOR MIN ONLY
									String min_price_range = "";
									if(MIN_price_range[i].equals("D")) //HARSH20210809
									{
										min_price_range="A";
									}
									else if(MIN_price_range[i].equals("O"))
									{
										if(MIN_days[i] != null)
										{
											min_price_range="A"+MIN_days[i];
										}
									}
									else
									{
										min_price_range = MIN_price_range[i];
									}
									
									queryString = "INSERT INTO FMS9_MRCR_CONT_FIN_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,CURVE_NM, SLOPE, CONST, " +
											  "REMARKS, EMP_CD, ENT_DT, FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
											  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
											  "'"+dcq+"', '"+curve_nm[i]+"','"+Min_slope[i]+"','"+Min_constant[i]+"','"+remark+"','"+user_cd+"',sysdate,'Y','"+min_price_range+"',to_date('"+MIN_price_start_dt[i]+"','dd/mm/yyyy'),to_date('"+MIN_price_end_dt[i]+"','dd/mm/yyyy'))";						
									//System.out.println("Insert Query For SN DCQ Details = "+queryString);
									stmt.executeUpdate(queryString);
									msg="Data Inserted Successfully";
								}
							}
						}
						else //HARSH20210729
						{
							if(curve_nm != null)
							{
								queryString = "INSERT INTO FMS9_MRCR_CONT_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,  PRICE_TYPE,  CURVE_NM, SLOPE, CONST,  QUANTITY_UNIT, " +
										//SB20210308	  "REMARKS, EMP_CD, ENT_DT, FLAG) VALUES" +
										  "REMARKS, EMP_CD, ENT_DT, FLAG, PHYS_CURVE_NM,RATE,VERY_DT, PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + //SB20210308 //HARSH20210426 RATE AND VARIFY_DT ADDED
										  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
										  "'"+dcq+"','"+priceType+"', '"+curve_nm[0]+"','"+slope+"','"+constant+"', 1,'"+remark+"','"+user_cd+"',sysdate,'Y', '"+phys_curve_nm+"','"+PPAC_Price+"',TO_DATE('"+PPAC_Validity_dt+"','DD/MM/YYYY'),'"+price_range+"',to_date('"+price_start_dt+"','dd/mm/yyyy'),to_date('"+price_end_dt+"','dd/mm/yyyy'))";						
								//System.out.println("Insert Query For SN DCQ Details = "+queryString);
								stmt.executeUpdate(queryString);
								msg="Data Inserted Successfully";
							}
						}
					}
					
					//HARSH20210902
					if(phys_fin_curve_nm != null && phys_slope != null && phys_constant != null)
					{
						for(int i=0; i<phys_fin_curve_nm.length; i++)
						{
							String phy_price_range = "";
							if(phys_price_range[i].equals("D")) 
							{
								phy_price_range="A";
							}
							else if(phys_price_range[i].equals("O"))
							{
								if(phys_days[i] != null)
								{
									phy_price_range="A"+phys_days[i];
								}
							}
							else
							{
								phy_price_range = phys_price_range[i];
							}
							queryString = "INSERT INTO FMS9_MRCR_CONT_PHY_PRICE_DTL(MAPPING_ID, CONTRACT_TYPE, SEQ_NO, FROM_DT, TO_DT, DCQ,CURVE_NM, SLOPE, CONST, " +
									  "REMARKS, EMP_CD, ENT_DT, FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT) VALUES" + 
									  "('"+mapp_id+"','"+cont_type+"','"+seq_no+"',TO_DATE('"+from_dt+"','DD/MM/YYYY'),TO_DATE('"+to_dt+"','DD/MM/YYYY')," +
									  "'"+dcq+"', '"+phys_fin_curve_nm[i]+"','"+phys_slope[i]+"','"+phys_constant[i]+"','"+remark+"','"+user_cd+"',sysdate,'Y','"+phy_price_range+"',to_date('"+phys_price_start_dt[i]+"','dd/mm/yyyy'),to_date('"+phys_price_end_dt[i]+"','dd/mm/yyyy'))";						
							//System.out.println("Insert Query For SN DCQ Details = "+queryString);
							stmt.executeUpdate(queryString);
							msg="Data Inserted Successfully";
						}
					}
				}
				else
				{
					msg="Data not Inserted!!";
				}
			}
			/////LOG FOR CONT PRICE DTL HARSH20210226////////////////
			if(!cont_type.equals("B")) //SB20210528
				mapp_id = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-%";	 //SB20210528
			if(optional.equalsIgnoreCase("DELETE"))
			{
				if(!seq_no.equals(""))
				{
					queryString="DELETE FROM LOG_FMS9_MRCR_CONT_PRICE_DTL  " +
								"WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO='"+seq_no+"'";						
					System.out.println("DELETE: SN LOG_FMS9_MRCR_CONT_PRICE_DTL Details = "+queryString);
					stmt1.executeUpdate(queryString);
				}
			}
			else
			{
				String log_seq_no = "";
				queryString="select max(nvl(log_seq_no,1)+1) from LOG_FMS9_MRCR_CONT_PRICE_DTL where MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO = '"+seq_no+"'";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					log_seq_no=rset.getString(1)==null?"1":rset.getString(1);
				}
				
				queryString = "INSERT INTO LOG_FMS9_MRCR_CONT_PRICE_DTL SELECT MAPPING_ID,CONTRACT_TYPE,SEQ_NO,'"+log_seq_no+"',"
						+ "FROM_DT,TO_DT,DCQ,PRICE_TYPE,CURVE_NM,SLOPE,CONST,QUANTITY_UNIT,RATE,RATE_UNIT,REMARKS,'"+user_cd+"',sysdate,"
						+ "VERY_BY,VERY_DT,APPR_BY,APPR_DT,FLAG,PRICE_RANGE,PRICE_START_DT,PRICE_END_DT,PHYS_CURVE_NM FROM FMS9_MRCR_CONT_PRICE_DTL "
						+ "WHERE MAPPING_ID LIKE '"+mapp_id+"' AND CONTRACT_TYPE='"+cont_type+"' AND SEQ_NO = '"+seq_no+"'";
				System.out.println("INSERT: SN LOG_FMS9_MRCR_CONT_PRICE_DTL Details = "+queryString);
				stmt.executeUpdate(queryString);
			}
			//////////////////////////////////////////////////////////
			dbcon.commit();
			url="../contract_master/frm_MR_var_dcq.jsp?buyer_name="+buyer_name+"&buyer_cd="+buyer_cd+"&cont_type="+cont_type+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Price Details NOT Submited For The Selected DEAL !!!";
			url="../contract_master/frm_MR_var_dcq.jsp?buyer_name="+buyer_name+"&buyer_cd="+buyer_cd+"&cont_type="+cont_type+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
		}	
	}
	
	//Changes on 9th August by Achal	
	public void insertModifyLoaDcqDetails(HttpServletRequest request) throws Exception
	{
		methodName = "insertModifyLoaDcqDetails()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
								
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");		
		String sn_cd = request.getParameter("sn_cd")==null?"":request.getParameter("sn_cd");
		String sn_revno = request.getParameter("sn_revno")==null?"":request.getParameter("sn_revno");
		String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");				
		try
		{
			String [] from_dt = request.getParameterValues("from_dt");
			String [] to_dt = request.getParameterValues("to_dt");
			String [] dcq = request.getParameterValues("dcq");
			String [] remark = request.getParameterValues("remark");
			
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY') FROM FMS7_LOA_DCQ_DTL " +
						  "WHERE CUSTOMER_CD="+buyer_cd+" And TENDER_NO="+fgsa_cd+"  AND LOA_NO="+sn_cd+" AND LOA_REV_NO="+sn_revno+"";			
			//System.out.println("Checking Query For LOA DCQ Details Existance = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				query1 = "DELETE FROM FMS7_LOA_DCQ_DTL " +
						 "WHERE CUSTOMER_CD="+buyer_cd+" And TENDER_NO="+fgsa_cd+" AND LOA_NO="+sn_cd+" AND LOA_REV_NO="+sn_revno+"";				
				//System.out.println("Delete Query For LOA DCQ Details = "+query1);
				stmt1.executeUpdate(query1);
			}			
			int count = 0;			
			for(int i=0; i<from_dt.length; i++)
			{
				if(from_dt[i]!=null && to_dt[i]!=null && dcq[i]!=null)
				{
					if(!from_dt[i].trim().equals("") && !from_dt[i].trim().equals("0") 
						&& !to_dt[i].trim().equals("") && !to_dt[i].trim().equals("0") 
						&& !dcq[i].trim().equals(""))
					{
						++count;
						String rem = obj.replaceSingleQuotes(remark[i]);
						
						queryString = "INSERT INTO FMS7_LOA_DCQ_DTL(CUSTOMER_CD, TENDER_NO,  " +
									  "LOA_NO, LOA_REV_NO, SEQ_NO, FROM_DT, TO_DT, DCQ, QUANTITY_UNIT, " +
									  "REMARK, EMP_CD, ENT_DT, FLAG) VALUES" +
									  "("+buyer_cd+","+fgsa_cd+","+sn_cd+","+sn_revno+","+count+"," +
									  "TO_DATE('"+from_dt[i]+"','DD/MM/YYYY'),TO_DATE('"+to_dt[i]+"','DD/MM/YYYY')," +
									  ""+dcq[i]+",1,'"+rem+"',"+user_cd+",sysdate,'Y')";						
						//System.out.println("Insert Query For LOA DCQ Details = "+queryString);
						stmt.executeUpdate(queryString);
					}
				}
			}			
			dbcon.commit();
			
			if(count>0)
			{
				msg = "DCQ Details Submited Successfully For The Selected LOA !!!";
			}
			else
			{
				msg = "DCQ Details NOT Submited Because: Any One Of The Mandatory Information Is Missing !!!";
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
			dbcon.rollback();
			e.printStackTrace();
			msg="DCQ Details NOT Submited For The Selected LOA !!!";
		}		
		url="../contract_master/frm_loa_var_dcq.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	
	//MD20120123 Start
//	Introduce by Milan MD20111022
	public void LC_DTL_ENTRY_REGAS(HttpServletRequest request) throws Exception
	{



		methodName = "LC_DTL_ENTRY_REGAS()";
		//System.out.println("OK");
		HttpSession session = request.getSession();
		//FOR_LC_MST 
		String LC_SEQ_NO = request.getParameter("LC_SEQ_NO")==null?"0":request.getParameter("LC_SEQ_NO");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
		escapeSingleQuotes obj = new  escapeSingleQuotes();
						
		String lc_manual_exchg_rate = request.getParameter("manualExchangeRate")==null?"0":request.getParameter("manualExchangeRate");
		String lc_manual_exchg_rate_flag = "Y";
		String lc_method = request.getParameter("lc_method")==null?"AUTO":request.getParameter("lc_method");
		String user_defined_dcq = "0";
		String lc_from_dt = request.getParameter("lc_from_dt")==null?"":request.getParameter("lc_from_dt");
		String lc_to_dt = request.getParameter("lc_to_dt")==null?"":request.getParameter("lc_to_dt");
		String lc_gen_dt = request.getParameter("lc_gen_dt")==null?"":request.getParameter("lc_gen_dt");
		
		String credit_eff_dt1 = request.getParameter("credit_eff_dt1")==null?"":request.getParameter("credit_eff_dt1");
		String credit = request.getParameter("credit")==null?"0":request.getParameter("credit");
		
		String lc_cal_amt = request.getParameter("lc_cal_amt")==null?"0":request.getParameter("lc_cal_amt");
		String final_lc_amount = request.getParameter("final_lc_amount")==null?"0":request.getParameter("final_lc_amount");
		String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");
		String totcontcap = request.getParameter("totcontcap")==null?"":request.getParameter("totcontcap");
		
		//extra
		String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
		String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");

		String msg = request.getParameter("msg")==null?"":request.getParameter("msg");

		String cCD = request.getParameter("cCD")==null?"":request.getParameter("cCD");
		String from_dt = request.getParameter("from_dt")==null?"":request.getParameter("from_dt");
		String to_dt = request.getParameter("to_dt")==null?"":request.getParameter("to_dt");
		String lc_fin_year = request.getParameter("lc_fin_year")==null?"":request.getParameter("lc_fin_year");
		String lc_flag = request.getParameter("lc_flag")==null?"":request.getParameter("lc_flag");
		//extra end 
				
		String rem = obj.replaceSingleQuotes(remarks);
		//System.out.println("OK donnnn");
		String user_defined_flag = "N";
		
		if(lc_manual_exchg_rate.trim().equals(""))
		{
			lc_manual_exchg_rate = "0";
		}
		
		if(!lc_method.equalsIgnoreCase("AUTO"))
		{
			user_defined_flag = "Y";
		}
		
		int month = 0;
		String fin_year = "";
		
		if(lc_from_dt.length()>=10)
		{
			month = Integer.parseInt(lc_from_dt.substring(3,5));
			if(month<4 && month>0)
			{
				fin_year = ""+(Integer.parseInt(lc_from_dt.substring(6))-1)+"-"+lc_from_dt.substring(6);
			}
			else if(month>=4)
			{
				fin_year = lc_from_dt.substring(6)+"-"+(Integer.parseInt(lc_from_dt.substring(6))+1);
			}
		}
		
		
		//for FMS_LC_DTL
		//System.out.println("OK "+cCD);
		String [] cCD_arr = request.getParameterValues("cCD_arr");
		String [] cont_type_arr = request.getParameterValues("cont_type_arr");
		String [] lc_exchg_rate_arr = request.getParameterValues("lc_exchg_rate_arr");
		String [] lc_base_remark_arr = request.getParameterValues("lc_base_remark_arr");
		String [] regasCD_arr = request.getParameterValues("regasCD_arr");
		String [] regasRev_arr = request.getParameterValues("regasRev_arr");
		String [] sn_st_dt_arr = request.getParameterValues("sn_st_dt_arr");
		String [] sn_end_dt_arr = request.getParameterValues("sn_end_dt_arr");
		String [] capacity_arr = request.getParameterValues("capacity_arr");
		//String [] dcq_arr = request.getParameterValues("dcq_arr");
		String [] datediff_arr = request.getParameterValues("datediff_arr");
		//System.out.println("OK");
		String ScCD_arr = "";
		String Scont_type_arr = "";
		String Slc_exchg_rate_arr = "";
		String Slc_base_remark_arr = "";
		String SregasCD_arr = "";
		String SregasRev_arr = "";
		String Ssn_st_dt_arr = "";
		String Ssn_end_dt_arr = "";
		String Scapacity_arr = "";
		String Sdcq_arr = "";
		String Sdatediff_arr = "";
		//System.out.println("From >>>>>>>>>>>>>>>>>>>>> Sucseeeeee 1"+Sdatediff_arr+" "+Sdcq_arr+" "+Ssn_st_dt_arr);
		for(int i=0; i<cCD_arr.length; i++)
		{
			/*System.out.println("From for loop OK : "+cont_type_arr[i]);
			System.out.println("From for loop OK : "+regasCD_arr[i]);
			System.out.println("From for loop OK : "+cCD_arr[i]);
			System.out.println("From for loop OK : "+sn_st_dt_arr[i]);
			System.out.println("From for loop OK : "+sn_st_dt_arr[i]);
			System.out.println("From for loop OK : "+sn_end_dt_arr[i]);
			System.out.println("From for loop OK : "+capacity_arr[i]);
			System.out.println("From for loop OK : "+capacity_arr[i]);
		//	System.out.println("From for loop OK : "+dcq_arr[i]);
			System.out.println("From for loop OK : "+datediff_arr[i]);*/
			
			ScCD_arr += cCD_arr[i]+"~~";
			Scont_type_arr += cont_type_arr[i]+"~~";
			Slc_exchg_rate_arr += "0"+"~~"; //lc_exchg_rate_arr[i]+"~~";
			Slc_base_remark_arr += "0"+"~~"; //lc_base_remark_arr[i]+"~~";
			SregasCD_arr += regasCD_arr[i]+"~~";
			SregasRev_arr += regasRev_arr[i]+"~~";
			Ssn_st_dt_arr += sn_st_dt_arr[i]+"~~";
			Ssn_end_dt_arr += sn_end_dt_arr[i]+"~~";
			Scapacity_arr += capacity_arr[i]+"~~";
			//Sdcq_arr += dcq_arr[i]+"~~";
			Sdatediff_arr += datediff_arr[i]+"~~";
			
		}
		/*System.out.println("From >>>>>>>>>>>>>>>>>>>>> Sucseeeeee 2"+Sdatediff_arr+" "+Sdcq_arr+" "+Ssn_st_dt_arr);
		System.out.println("From servlet >>>>>>>>>>>>>>");
		System.out.println(Scont_type_arr);
		System.out.println(Slc_exchg_rate_arr);
		System.out.println(Slc_base_remark_arr);
		System.out.println(SregasCD_arr);
		System.out.println(SregasRev_arr);
		System.out.println(Ssn_st_dt_arr);
		System.out.println(Ssn_end_dt_arr);
		System.out.println(Scapacity_arr);
		System.out.println(LC_SEQ_NO);
		System.out.println(user_cd);
		System.out.println(activity);
		System.out.println(lc_manual_exchg_rate);
		System.out.println("lc_manual_exchg_rate_flag : "+lc_manual_exchg_rate_flag);
		System.out.println("form lc_method  "+lc_method);
		System.out.println(user_defined_dcq);
		System.out.println(lc_from_dt);
		System.out.println(lc_to_dt);
		System.out.println(lc_gen_dt);
		System.out.println(" credit_eff_dt1 "+credit_eff_dt1);
		System.out.println(credit);
		System.out.println(lc_to_dt);
		System.out.println(lc_cal_amt);
		System.out.println(Sdcq_arr);
		System.out.println(Sdatediff_arr);*/
		
		try
		{
			int lc_seq_no = 0;
				
			if(activity.equalsIgnoreCase("insert"))
			{
				if(fin_year.length()==9)
				{
					Sdcq_arr = "";
					Sdatediff_arr = "";
					queryString = "SELECT MAX(LC_SEQ_NO) FROM FMS7_LC_MST WHERE FINANCIAL_YEAR='"+fin_year+"'" +
								" AND (FLAG = 'R' OR FLAG = 'r' OR FLAG = 'T' OR FLAG = 'C')";
					//System.out.println("Finding Max LC Sequence NO for Particular Financial Year AND RE-GAS = "+queryString);
					rset = stmt.executeQuery(queryString);
					//System.out.println("Helooo from  : "+lc_seq_no);
					if(rset.next())
					{
						lc_seq_no = rset.getString(1)==null?9001:rset.getInt(1);
						lc_seq_no+=1;
						//System.out.println("Helooo from if  : "+lc_seq_no);
					}
					else
					{
						lc_seq_no = 9001;
						//System.out.println("Helooo from else  : "+lc_seq_no);
					}
					
				}
			}
			else if(activity.equalsIgnoreCase("update"))
			{
				//System.out.println("LC_SEQ_NO = "+LC_SEQ_NO);
				if(LC_SEQ_NO.length()>=14)
				{
					fin_year = LC_SEQ_NO.substring(5);
					lc_seq_no = Integer.parseInt(LC_SEQ_NO.substring(0,4));
					
					queryString = "DELETE FROM FMS7_LC_MST WHERE FINANCIAL_YEAR='"+fin_year+"' AND LC_SEQ_NO="+lc_seq_no+" AND CUSTOMER_CD="+cCD_arr[0]+"" +
									" AND FLAG = 'R'";
					//System.out.println("Delete Master Record Of LC = "+queryString);
					stmt.executeUpdate(queryString);
					
					queryString = "DELETE FROM FMS7_LC_DTL WHERE FINANCIAL_YEAR='"+fin_year+"' AND LC_SEQ_NO="+lc_seq_no+" AND CUSTOMER_CD="+cCD_arr[0]+"" +
									" AND FLAG = 'R' AND CONT_TYPE = 'R'";
					//System.out.println("Delete Dependent Records Of LC from LC Details = "+queryString);
					stmt.executeUpdate(queryString);
				}
				
			}
			//lc_seq_no=lc_seq_no+9001;
			if(lc_seq_no>=0 && fin_year.length()==9)
			{
				
				//System.out.println("Helooo from if  : "+fin_year);
				//System.out.println("Helooo from if  : "+lc_seq_no);
				
				//System.out.println("Helooo from if  : "+user_cd);
				//System.out.println("Helooo from if  : ");
				//System.out.println("Helooo from if  : "+capacity_arr);
				//System.out.println("Helooo from if  : "+lc_manual_exchg_rate);
				for(int i=0; i<regasCD_arr.length; i++)
				{
					////System.out.println("From the Inset for loop = "+regasCD_arr[i]+" "+cCD_arr[i]+" "+cont_type_arr[i]+" "+regasRev_arr[i]+" "+sn_st_dt_arr[i]+" "+sn_end_dt_arr[i]+" "+capacity_arr[i]+" "+lc_exchg_rate_arr[i]);
					long Date_Diff = 0;
					long DCQ = 0;
					if(activity.equalsIgnoreCase("insert"))
					{
						query2 = "select (to_date('"+sn_end_dt_arr[i]+"','dd/mm/yyyy')-to_date('"+sn_st_dt_arr[i]+"','dd/mm/yyyy')) from dual";
						//System.out.println("Helooo from Date_Diff  : "+query2+" "+capacity_arr[i]);
						rset2 = stmt2.executeQuery(query2);
						rset2.next();
						Date_Diff = rset2.getLong(1);
						//DCQ = Long.parseLong(capacity_arr[i])/Date_Diff;
						
						Sdcq_arr += DCQ+"~~";
						Sdatediff_arr += Date_Diff+"~~";
						//System.out.println("Helooo from Date_Diff  : "+Date_Diff);
						//System.out.println("Helooo from DCQ  : "+DCQ);
					}
					else
					{
//						 dcq_arr datediff_arr
						Date_Diff = Long.parseLong(datediff_arr[i]);
						//DCQ = Long.parseLong(dcq_arr[i]);
						
					}
					
					queryString = "";
					queryString = "INSERT INTO FMS7_LC_DTL (FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, " +
								  "FGSA_NO, SN_NO, CONT_TYPE, FGSA_REV_NO, SN_REV_NO, SN_START_DATE, " +
								  "SN_END_DATE, SN_DURATION, TCQ,  EXCHG_RATE, " +
								  "EMP_CD, ENT_DT, FLAG) " +
								  "VALUES ('"+fin_year+"', "+lc_seq_no+", "+cCD_arr[i]+", "+"0"+", " +
								  ""+regasCD_arr[i]+", '"+cont_type_arr[i]+"', "+"0"+", "+regasRev_arr[i]+", " +
								  "TO_DATE('"+sn_st_dt_arr[i]+"','DD/MM/YYYY'), TO_DATE('"+sn_end_dt_arr[i]+"','DD/MM/YYYY'), " +
								  ""+Date_Diff+", "+capacity_arr[i]+", " +
								  ""+lc_manual_exchg_rate+"," +
								  ""+user_cd+", sysdate, 'R')";
					//System.out.println("Submitting Each SN Related to Particular LC = "+queryString);
					stmt.executeUpdate(queryString);
				}
					
			}
				
//NOTE : HERE totcontcap IS REPRESENT THE TOTAL CONTRACT CAPACITY OF LC, AND AS ASSUMED ADJUSTMENT 
//		 WE ARE STRORING VALUE IN THE USER_DEFINED_DCQ COLUMN (BY MILAN DALSANIYA 2011, NOV 7TH )			
				queryString = "INSERT INTO FMS7_LC_MST (FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, " +
							  "CREDIT_RATING, RATING_EFF_DATE, LC_REF_DATE, START_DATE, END_DATE, " +
							  "MANUAL_EXCHG_FLAG, MANUAL_EXCHG_RATE, USER_DEFINED_FLAG, " +
							  "USER_DEFINED_DCQ, CALC_LC_AMOUNT, FINAL_LC_AMOUNT, REMARKS, EMP_CD, ENT_DT, FLAG) " +
							  "VALUES ('"+fin_year+"', "+lc_seq_no+", "+cCD_arr[0]+", '"+credit+"', " +
							  "TO_DATE('"+credit_eff_dt1+"','DD/MM/YYYY'), TO_DATE('"+lc_gen_dt+"','DD/MM/YYYY'), " +
							  "TO_DATE('"+lc_from_dt+"','DD/MM/YYYY'), TO_DATE('"+lc_to_dt+"','DD/MM/YYYY'), " +
							  "'"+lc_manual_exchg_rate_flag+"', "+lc_manual_exchg_rate+", '"+user_defined_flag+"', " +
							  ""+totcontcap+", "+lc_cal_amt+", "+final_lc_amount+", '"+rem+"', " +
							  ""+user_cd+", sysdate, 'R')";
				//System.out.println("Submitting LC Master Details = "+queryString);
				stmt.executeUpdate(queryString);
				
				if(activity.equalsIgnoreCase("insert"))
				{
					msg = "LC Details Inserted Successfully !!!";
				}
				else if(activity.equalsIgnoreCase("update"))
				{
					msg = "LC Details Modified Successfully !!!";
				}
				
				if(lc_seq_no<10)
				{
					LC_SEQ_NO = "000"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<100)
				{
					LC_SEQ_NO = "00"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<1000)
				{
					LC_SEQ_NO = "0"+lc_seq_no+"/"+fin_year;
				}
				else
				{
					LC_SEQ_NO = ""+lc_seq_no+"/"+fin_year;
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
			
			if(user_defined_dcq.equals("0") || user_defined_dcq.equals("0.00") || user_defined_dcq.equals("0.0"))
			{
				user_defined_dcq = "";
			}
			
			if(lc_manual_exchg_rate.equals("0") || lc_manual_exchg_rate.equals("0.00") || lc_manual_exchg_rate.equals("0.0"))
			{
				lc_manual_exchg_rate = "";
			}
			if(activity.equalsIgnoreCase("insert"))
			{
				msg = "LC Details of </i><u>"+LC_SEQ_NO+"</u> <i>for RE-GAS Submitted Successfully !!!";
			}
			else if(activity.equalsIgnoreCase("update"))
			{
				msg = "LC Details of </i>"+LC_SEQ_NO+"<i> for RE-GAS Updated Successfully !!!";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			msg = "LC Details for RE-GAS NOT Submitted !!!";
		}
		url = "../contract_mgmt/frm_LC_monitoring_regas.jsp?activity=update&LC_SEQ_NO="+LC_SEQ_NO+"&cCD="+ScCD_arr+"" +
		"&regasCD="+SregasCD_arr+"&regasRev="+SregasRev_arr+""+
		"&credit="+credit+""+ 
		"&credit_eff_dt1="+credit_eff_dt1+""+ 
		"&START_DT="+Ssn_st_dt_arr+"" +
		"&END_DT="+Ssn_end_dt_arr+"" +
		"&capacity="+Scapacity_arr+"&cont_type="+Scont_type_arr+"&" +
		"lc_exchg_rate="+Slc_exchg_rate_arr+""+
		"&lc_base_remark="+Slc_base_remark_arr+"" +
		"&lc_gen_dt="+lc_gen_dt+"&lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"" +
		"&lc_manual_exchg_rate="+lc_manual_exchg_rate+"" +
		"&lc_manual_exchg_rate_flag="+lc_manual_exchg_rate_flag+"" +
		"&lc_method="+lc_method+"&user_defined_dcq="+user_defined_dcq+"" +
		"&LC_SEQ_NO="+LC_SEQ_NO+"&credit_eff_dt1="+credit_eff_dt1+"" +
		"&final_lc_amount="+final_lc_amount+"" +
		"&remarks="+remarks+"&msg="+msg+"&form_id="+form_id+"" +
		"&datediff="+Sdatediff_arr+"&dcq="+Sdcq_arr+"&lc_cal_amt="+lc_cal_amt+"" +
		"&from_dt="+from_dt+"&to_dt="+to_dt+""+
		"&user_cd="+emp_cd+"&username="+emp_name+""+
		"&comp_cd="+comp_cd+"&lc_fin_year="+lc_fin_year+""+
		"&lc_flag="+lc_flag+""+
		"&form_nm="+form_nm+"&totcontcap="+totcontcap+"";	
		
	

}

//====================================
	
	//MD20120123 END
	
	public void LC_DTL_ENTRY(HttpServletRequest request) throws Exception
	{
		methodName = "LC_DTL_ENTRY()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
		escapeSingleQuotes obj = new  escapeSingleQuotes();
				
		String lc_manual_exchg_rate = request.getParameter("manualExchangeRate")==null?"0":request.getParameter("manualExchangeRate");
		String lc_manual_exchg_rate_flag = request.getParameter("lc_manual_exchg_rate_flag")==null?"N":request.getParameter("lc_manual_exchg_rate_flag");
		String lc_method = request.getParameter("lc_method")==null?"AUTO":request.getParameter("lc_method");
		String user_defined_dcq = request.getParameter("user_defined_dcq")==null?"0":request.getParameter("user_defined_dcq");
		String from_dt = request.getParameter("from_dt")==null?"":request.getParameter("from_dt");
		String to_dt = request.getParameter("to_dt")==null?"":request.getParameter("to_dt");
		String lc_gen_dt = request.getParameter("lc_gen_dt")==null?"":request.getParameter("lc_gen_dt");
		String LC_SEQ_NO = request.getParameter("LC_SEQ_NO")==null?"0":request.getParameter("LC_SEQ_NO");
		String eff_dt1 = request.getParameter("eff_dt1")==null?"":request.getParameter("eff_dt1");
		String credit = request.getParameter("credit")==null?"0":request.getParameter("credit");
		
		String lc_amount = request.getParameter("lc_amount")==null?"0":request.getParameter("lc_amount");
		String final_lc_amount = request.getParameter("final_lc_amount")==null?"0":request.getParameter("final_lc_amount");
		String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");
		
		String rem = obj.replaceSingleQuotes(remarks);
		
		String user_defined_flag = "N";
		
		if(user_defined_dcq.trim().equals(""))
		{
			user_defined_dcq = "0";
		}
		
		if(lc_manual_exchg_rate.trim().equals(""))
		{
			lc_manual_exchg_rate = "0";
		}
		
		if(!lc_method.equalsIgnoreCase("AUTO"))
		{
			user_defined_flag = "Y";
		}
		
		int month = 0;
		String fin_year = "";
		
		if(from_dt.length()>=10)
		{
			month = Integer.parseInt(from_dt.substring(3,5));
			if(month<4 && month>0)
			{
				fin_year = ""+(Integer.parseInt(from_dt.substring(6))-1)+"-"+from_dt.substring(6);
			}
			else if(month>=4)
			{
				fin_year = from_dt.substring(6)+"-"+(Integer.parseInt(from_dt.substring(6))+1);
			}
		}
						
		String [] bscode_arr = request.getParameterValues("bscode_arr");
		String [] fgsa_no_arr = request.getParameterValues("fgsa_no_arr");
		String [] rev_no_arr = request.getParameterValues("rev_no_arr");
		String [] sn_no_arr = request.getParameterValues("sn_no_arr");
		String [] sn_ref_no_arr = request.getParameterValues("sn_ref_no_arr");
		String [] sn_rev_no_arr = request.getParameterValues("sn_rev_no_arr");
		String [] tcq_arr = request.getParameterValues("tcq_arr");
		String [] dcq_arr = request.getParameterValues("dcq_arr");
		String [] original_dcq_arr = request.getParameterValues("original_dcq_arr");
		String [] datediff_arr = request.getParameterValues("datediff_arr");
		String [] rate_arr = request.getParameterValues("rate_arr");
		String [] start_dt_arr = request.getParameterValues("start_dt_arr");
		String [] end_dt_arr = request.getParameterValues("end_dt_arr");
		String [] tax_type_arr = request.getParameterValues("tax_type_arr");
		String [] tax_rate_arr = request.getParameterValues("tax_rate_arr");
		String [] cont_type_arr = request.getParameterValues("cont_type_arr");
		String [] lc_exchg_rate_arr = request.getParameterValues("lc_exchg_rate_arr");
		String [] original_lc_exchg_rate_arr = request.getParameterValues("original_lc_exchg_rate_arr");
		String [] lc_base_remark_arr = request.getParameterValues("lc_base_remark_arr");
		String [] flag_lc_value_arr = request.getParameterValues("flag_lc_value_arr");
		String [] flag_dcq_tcq_arr = request.getParameterValues("flag_dcq_tcq_arr");
		String [] dcqdays_tcqpercent_value_arr = request.getParameterValues("dcqdays_tcqpercent_value_arr");
		
		String bCD = "";
		String fCD = "";
		String fgsa_rev = "";
		String snNo = "";
		String snRefNo = "";
		String snRev = "";
		String flag = "";
		String tcq = "";
		String dcq = "";
		String original_dcq = "";
		String rate = "";
		String st_dt = "";
		String end_dt = "";
		String datediff = "";
		String tax_type = "";
		String tax_rate = "";
		String cont_type = "";
		String lc_exchg_rate = "";
		String original_lc_exchg_rate = "";
		String lc_base_remark = "";
		String flag_lc_value = "";
		String flag_dcq_tcq = "";
		String dcqdays_tcqpercent_value = "";
		
		for(int i=0; i<bscode_arr.length; i++)
		{
			bCD += bscode_arr[i]+"~~";
			fCD += fgsa_no_arr[i]+"~~";
			fgsa_rev += rev_no_arr[i]+"~~";
			snNo += sn_no_arr[i]+"~~";
			snRefNo += sn_ref_no_arr[i]+"~~";
			snRev += sn_rev_no_arr[i]+"~~";
			flag += cont_type_arr[i]+"~~";
			tcq += tcq_arr[i]+"~~";
			dcq += dcq_arr[i]+"~~";
			original_dcq += original_dcq_arr[i]+"~~";
			rate += rate_arr[i]+"~~";
			st_dt += start_dt_arr[i]+"~~";
			end_dt += end_dt_arr[i]+"~~";
			datediff += datediff_arr[i]+"~~";
			tax_type += tax_type_arr[i]+"~~";
			tax_rate += tax_rate_arr[i]+"~~";
			cont_type += cont_type_arr[i]+"~~";
			lc_exchg_rate += lc_exchg_rate_arr[i]+"~~";
			original_lc_exchg_rate += original_lc_exchg_rate_arr[i]+"~~";
			lc_base_remark += lc_base_remark_arr[i]+"~~";
			flag_lc_value += flag_lc_value_arr[i]+"~~";
			flag_dcq_tcq += flag_dcq_tcq_arr[i]+"~~";
			dcqdays_tcqpercent_value += dcqdays_tcqpercent_value_arr[i]+"~~";
		}
		
		try
		{
			int lc_seq_no = 0;
				
			if(activity.equalsIgnoreCase("insert"))
			{
				if(fin_year.length()==9)
				{
					queryString = "SELECT MAX(LC_SEQ_NO) FROM FMS7_LC_MST WHERE FINANCIAL_YEAR='"+fin_year+"'";
					//System.out.println("Finding Max LC Sequence NO for Particular Financial Year = "+queryString);
					rset = stmt.executeQuery(queryString);
					
					if(rset.next())
					{
						lc_seq_no = rset.getInt(1) + 1;
					}
					else
					{
						lc_seq_no = 1;
					}
				}
			}
			else if(activity.equalsIgnoreCase("update"))
			{
				//System.out.println("LC_SEQ_NO = "+LC_SEQ_NO);
				if(LC_SEQ_NO.length()>=14)
				{
					fin_year = LC_SEQ_NO.substring(5);
					lc_seq_no = Integer.parseInt(LC_SEQ_NO.substring(0,4));
					
					queryString = "DELETE FROM FMS7_LC_MST WHERE FINANCIAL_YEAR='"+fin_year+"' AND LC_SEQ_NO="+lc_seq_no+" AND CUSTOMER_CD="+bscode_arr[0]+"";
					//System.out.println("Delete Master Record Of LC = "+queryString);
					stmt.executeUpdate(queryString);
					
					queryString = "DELETE FROM FMS7_LC_DTL WHERE FINANCIAL_YEAR='"+fin_year+"' AND LC_SEQ_NO="+lc_seq_no+" AND CUSTOMER_CD="+bscode_arr[0]+"";
					//System.out.println("Delete Dependent Records Of LC from LC Details = "+queryString);
					stmt.executeUpdate(queryString);
				}
			}
			
			if(lc_seq_no>0 && fin_year.length()==9)
			{
				for(int i=0; i<bscode_arr.length; i++)
				{
					if(lc_method.equalsIgnoreCase("USER_DEFINED"))
					{
						dcq_arr[i] = user_defined_dcq;
					}
					else
					{
						dcq_arr[i] = original_dcq_arr[i];
					}
					
					if(lc_manual_exchg_rate_flag.equalsIgnoreCase("Y"))
					{
						lc_exchg_rate_arr[i] = lc_manual_exchg_rate;
					}
					else
					{
						lc_exchg_rate_arr[i] = original_lc_exchg_rate_arr[i];
					}
					
					queryString = "INSERT INTO FMS7_LC_DTL (FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, " +
								  "FGSA_NO, SN_NO, CONT_TYPE, FGSA_REV_NO, SN_REV_NO, SN_START_DATE, " +
								  "SN_END_DATE, SN_DURATION, DCQ, TCQ, SALES_RATE, EXCHG_RATE, " +
								  "TAX_PERCENTAGE, LC_METHOD_REMARK, LC_METHOD, LC_BASE, " +
								  "DCQ_DAYS_TCQ_PERCENTAGE, EMP_CD, ENT_DT, FLAG) " +
								  "VALUES ('"+fin_year+"', "+lc_seq_no+", "+bscode_arr[i]+", "+fgsa_no_arr[i]+", " +
								  ""+sn_no_arr[i]+", '"+cont_type_arr[i]+"', "+rev_no_arr[i]+", "+sn_rev_no_arr[i]+", " +
								  "TO_DATE('"+start_dt_arr[i]+"','DD/MM/YYYY'), TO_DATE('"+end_dt_arr[i]+"','DD/MM/YYYY'), " +
								  ""+datediff_arr[i]+", "+dcq_arr[i]+", "+tcq_arr[i]+", "+rate_arr[i]+", " +
								  ""+lc_exchg_rate_arr[i]+", 15, '"+lc_base_remark_arr[i]+"', '"+flag_lc_value_arr[i]+"', " +
								  "'"+flag_dcq_tcq_arr[i]+"', "+dcqdays_tcqpercent_value_arr[i]+", " +
								  ""+user_cd+", sysdate, 'Y')";
					//System.out.println("Submitting Each SN Related to Particular LC = "+queryString);
					stmt.executeUpdate(queryString);
					
					dcq_arr[i] = original_dcq_arr[i];
					lc_exchg_rate_arr[i] = original_lc_exchg_rate_arr[i];
				}
				
				queryString = "INSERT INTO FMS7_LC_MST (FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, " +
							  "CREDIT_RATING, RATING_EFF_DATE, LC_REF_DATE, START_DATE, END_DATE, " +
							  "MANUAL_EXCHG_FLAG, MANUAL_EXCHG_RATE, USER_DEFINED_FLAG, " +
							  "USER_DEFINED_DCQ, CALC_LC_AMOUNT, FINAL_LC_AMOUNT, REMARKS, EMP_CD, ENT_DT, FLAG) " +
							  "VALUES ('"+fin_year+"', "+lc_seq_no+", "+bscode_arr[0]+", '"+credit+"', " +
							  "TO_DATE('"+eff_dt1+"','DD/MM/YYYY'), TO_DATE('"+lc_gen_dt+"','DD/MM/YYYY'), " +
							  "TO_DATE('"+from_dt+"','DD/MM/YYYY'), TO_DATE('"+to_dt+"','DD/MM/YYYY'), " +
							  "'"+lc_manual_exchg_rate_flag+"', "+lc_manual_exchg_rate+", '"+user_defined_flag+"', " +
							  ""+user_defined_dcq+", "+lc_amount+", "+final_lc_amount+", '"+rem+"', " +
							  ""+user_cd+", sysdate, 'Y')";
				//System.out.println("Submitting LC Master Details = "+queryString);
				stmt.executeUpdate(queryString);
				
				if(activity.equalsIgnoreCase("insert"))
				{
					msg = "LC Details Inserted Successfully !!!";
				}
				else if(activity.equalsIgnoreCase("update"))
				{
					msg = "LC Details Modified Successfully !!!";
				}
				
				if(lc_seq_no<10)
				{
					LC_SEQ_NO = "000"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<100)
				{
					LC_SEQ_NO = "00"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<1000)
				{
					LC_SEQ_NO = "0"+lc_seq_no+"/"+fin_year;
				}
				else
				{
					LC_SEQ_NO = ""+lc_seq_no+"/"+fin_year;
				}
				
				dbcon.commit();
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
			
			if(user_defined_dcq.equals("0") || user_defined_dcq.equals("0.00") || user_defined_dcq.equals("0.0"))
			{
				user_defined_dcq = "";
			}
			
			if(lc_manual_exchg_rate.equals("0") || lc_manual_exchg_rate.equals("0.00") || lc_manual_exchg_rate.equals("0.0"))
			{
				lc_manual_exchg_rate = "";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			msg = "LC Details NOT Submitted !!!";
		}
		url="../contract_mgmt/frm_LC_monitoring.jsp?activity=update&bscode="+bCD+"&FGSA_CD="+fCD+"&rev_no="+fgsa_rev+"&SN_NO="+snNo+"&SN_REF_NO="+snRefNo+"&SN_REV_NO="+snRev+"&Flag="+flag+"&tcq="+tcq+"&dcq="+dcq+"&original_dcq="+original_dcq+"&credit="+credit+"&rate="+rate+"&START_DT="+st_dt+"&END_DT="+end_dt+"&datediff="+datediff+"&tax_type="+tax_type+"&cont_type="+flag+"&lc_exchg_rate="+lc_exchg_rate+"&original_lc_exchg_rate="+original_lc_exchg_rate+"&lc_base_remark="+lc_base_remark+"&flag_lc_value="+flag_lc_value+"&flag_dcq_tcq="+flag_dcq_tcq+"&dcqdays_tcqpercent_value="+dcqdays_tcqpercent_value+"&lc_gen_dt="+lc_gen_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&lc_manual_exchg_rate="+lc_manual_exchg_rate+"&lc_manual_exchg_rate_flag="+lc_manual_exchg_rate_flag+"&lc_method="+lc_method+"&user_defined_dcq="+user_defined_dcq+"&LC_SEQ_NO="+LC_SEQ_NO+"&eff_dt1="+eff_dt1+"&final_lc_amount="+final_lc_amount+"&remarks="+remarks+"&msg="+msg+"&form_id="+form_id+"&form_nm="+form_nm+"&tax_rate="+tax_rate;	
	}
	
		
	//Changed on 4th August	 by Achal
	public void Tender_BillingEntry(HttpServletRequest request) throws Exception
	{
		methodName = "Tender_BillingEntry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String PayCur = request.getParameter("payment_currency")==null?"":request.getParameter("payment_currency");
		String Freq = request.getParameter("freq_flag")==null?"":request.getParameter("freq_flag");
		String rate = request.getParameter("rate")==null?"":request.getParameter("rate");
		String period = request.getParameter("period")==null?"":request.getParameter("period");
		String sign = request.getParameter("plusmin")==null?"0":request.getParameter("plusmin");
		String ExchngRef = request.getParameter("rbiref")==null?"":request.getParameter("rbiref");
		String ExchngCal = request.getParameter("exch_calc_base_flag")==null?"":request.getParameter("exch_calc_base_flag");
		String InvR1 = request.getParameter("inv_currency")==null?"":request.getParameter("inv_currency");
		String modeper = request.getParameter("modeper")==null?"":request.getParameter("modeper");
		String fgsa_cd= request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String TAX_STR_CD = request.getParameter("TAX_STR_CD")==null?"":request.getParameter("TAX_STR_CD");
	    String Start_Dt = request.getParameter("Start_Dt")==null?"":request.getParameter("Start_Dt");
	    String exchg_rate_note = request.getParameter("exchg_rate_note")==null?"":request.getParameter("exchg_rate_note");	    
	    String bill_end_dt_clause = request.getParameter("bill_end_dt_clause")==null?"Y":request.getParameter("bill_end_dt_clause");
	    
	    exchg_rate_note = snglQuot.replaceSingleQuotes(exchg_rate_note);
	    String inv_criteria = request.getParameter("inv_criteria")==null?"":request.getParameter("inv_criteria");
		try
		{		
			int seq_no=1;
			queryString="select count(*) from LOG_FMS8_CONTRACT_BILLING_DTL where " +
					"CONT_TYPE='N' AND FGSA_NO='"+fgsa_cd+"' AND CUSTOMER_CD='"+bscode+"' ";
			rset3=stmt3.executeQuery(queryString);
			if(rset3.next())
			{
				String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
				seq_no=Integer.parseInt(seq_no1);
				seq_no++;
			}
			
			query = "INSERT INTO LOG_FMS8_CONTRACT_BILLING_DTL SELECT CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,'0','0',"
					+ "'N',BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,"
					+ "EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD,EMP_CD,"
					+ "ENT_DT,FLAG,EXCHG_RATE_NOTE,INV_CRITERIA,'"+seq_no+"','' FROM FMS7_FGSA_BILLING_DTL WHERE "
					+ "FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T' ";
			stmt.executeUpdate(query);
			
			query = "SELECT customer_cd FROM FMS7_FGSA_BILLING_DTL WHERE FGSA_NO="+fgsa_cd+" And CUSTOMER_CD="+bscode+" AND CONT_TYPE='T'";
			rset=stmt.executeQuery(query);	
			
			if(rset.next())
			{
				if(rset.getString(1)!=null)
				{
					if(!rset.getString(1).trim().equals(""))
					{
						query1 ="DELETE FROM FMS7_FGSA_BILLING_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
						stmt1.executeUpdate(query1);
					}
				}
			}
			query ="insert into FMS7_FGSA_BILLING_DTL(CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD,EXCHG_RATE_NOTE,EMP_CD,ENT_DT,FLAG,inv_criteria)" +
					"values('"+bscode+"','"+fgsa_cd+"','0','T','"+Freq+"','"+period+"','"+rate+"','"+sign+"','"+modeper+"','"+ExchngRef+"','"+ExchngCal+"','"+InvR1+"','"+PayCur+"','"+TAX_STR_CD+"','"+exchg_rate_note+"','"+user_cd+"',sysdate,'"+bill_end_dt_clause+"','"+inv_criteria+"' )";
			stmt.executeUpdate(query);			
			msg="FGSA-REVNO "+fgsa_cd+"-0 UPDATED with BILLING PERIOD CLAUSE as "+bill_end_dt_clause+" ";	

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
			e.printStackTrace();
			msg="Billing Details Are not Inserted!!!";	
		}
		url="../contract_master/frm_Tender_bill_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&Start_Dt="+Start_Dt+"&TAX_STR_CD="+TAX_STR_CD+"&inv_criteria="+inv_criteria+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}


	public void SN_BillingEntry(HttpServletRequest request) throws Exception
	{
		methodName = "SN_BillingEntry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String PayCur = request.getParameter("payment_currency")==null?"":request.getParameter("payment_currency");
		String Freq = request.getParameter("freq_flag")==null?"":request.getParameter("freq_flag");
		String rate = request.getParameter("rate")==null?"":request.getParameter("rate");
		String period = request.getParameter("period")==null?"":request.getParameter("period");
		String sign = request.getParameter("plusmin")==null?"0":request.getParameter("plusmin");
		String ExchngRef = request.getParameter("rbiref")==null?"":request.getParameter("rbiref");
		String ExchngCal = request.getParameter("exch_calc_base_flag")==null?"":request.getParameter("exch_calc_base_flag");
		String InvR1 = request.getParameter("inv_currency")==null?"":request.getParameter("inv_currency");
		String modeper = request.getParameter("modeper")==null?"":request.getParameter("modeper");
		String fgsa_cd= request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String TAX_STR_CD = request.getParameter("TAX_STR_CD")==null?"":request.getParameter("TAX_STR_CD");
	    String Start_Dt = request.getParameter("Start_Dt")==null?"":request.getParameter("Start_Dt");
	    String SN_CD= request.getParameter("SN_No")==null?"":request.getParameter("SN_No");
		String SN_REVNo = request.getParameter("SN_Rev_No")==null?"":request.getParameter("SN_Rev_No");
		String exchg_rate_note = request.getParameter("exchg_rate_note")==null?"":request.getParameter("exchg_rate_note");
		exchg_rate_note = snglQuot.replaceSingleQuotes(exchg_rate_note);
		String inv_criteria = request.getParameter("inv_criteria")==null?"":request.getParameter("inv_criteria");
		String bill_end_dt_clause = request.getParameter("bill_end_dt_clause")==null?"Y":request.getParameter("bill_end_dt_clause");
		/*
		Remark: SB
		Billing Clause: 
		1) T : On Completion of TCQ
		2) B : On Billing Period
		3) Y : default value treated as no clause. 
		*/	
		
		try
		{		
			int seq_no=1;
			queryString="select count(*) from LOG_FMS8_CONTRACT_BILLING_DTL where " +
					"CONT_TYPE='S' AND FGSA_NO='"+fgsa_cd+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' AND SN_NO='"+SN_CD+"' "
					+ "AND SN_REV_NO='"+SN_REVNo+"' AND CUSTOMER_CD='"+bscode+"' ";
			rset3=stmt3.executeQuery(queryString);
			if(rset3.next())
			{
				String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
				seq_no=Integer.parseInt(seq_no1);
				seq_no++;
			}
			
			query = "INSERT INTO LOG_FMS8_CONTRACT_BILLING_DTL SELECT CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,"
					+ "CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,"
					+ "EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD,EMP_CD,"
					+ "ENT_DT,FLAG,EXCHG_RATE_NOTE,INV_CRITERIA,'"+seq_no+"','' FROM FMS7_SN_BILLING_DTL WHERE "
					+ "FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' "
					+ "And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"' ";
			stmt.executeUpdate(query);
			
			query ="SELECT customer_cd FROM FMS7_SN_BILLING_DTL WHERE FGSA_NO='"+fgsa_cd+"' And "
					+ "FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				if(rset.getString(1)!=null)
				{
					if(!rset.getString(1).trim().equals(""))
					{
						query1 ="DELETE FROM FMS7_SN_BILLING_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'And CONT_TYPE='S' ";
						stmt1.executeUpdate(query1);						
					}
				}
			}	
			query ="insert into FMS7_SN_BILLING_DTL(CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE, EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD,EXCHG_RATE_NOTE,EMP_CD,ENT_DT,FLAG,inv_Criteria) " +
					"values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','"+SN_CD+"','"+SN_REVNo+"','S','"+Freq+"','"+period+"','"+rate+"','"+sign+"','"+modeper+"','"+ExchngRef+"','"+ExchngCal+"','"+InvR1+"','"+PayCur+"','"+TAX_STR_CD+"','"+exchg_rate_note+"','"+user_cd+"',sysdate,'"+bill_end_dt_clause+"','"+inv_criteria+"' ) ";					
			stmt.executeUpdate(query);
			dbcon.commit();
			msg="FGSA-REVNO-SN-REVNO "+fgsa_cd+"-"+fgsa_rev_no+"-"+SN_CD+"-"+SN_REVNo+" UPDATED with BILLING PERIOD CLAUSE as "+bill_end_dt_clause+" ";	
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
			e.printStackTrace();
			msg="FGSA-REVNO[ "+fgsa_cd+"-"+fgsa_rev_no+"]: NOT UPDATED with BILLING PERIOD CLAUSE as "+bill_end_dt_clause+" !!!";
		}
		url="../contract_master/frm_SN_bill_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&Start_Dt="+Start_Dt+"&TAX_STR_CD="+TAX_STR_CD+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REVNo+"&inv_criteria="+inv_criteria+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	public void LOA_BillingEntry(HttpServletRequest request) throws Exception
	{
		methodName = "LOA_BillingEntry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String PayCur = request.getParameter("payment_currency")==null?"":request.getParameter("payment_currency");
		String Freq = request.getParameter("freq_flag")==null?"":request.getParameter("freq_flag");
		String rate = request.getParameter("rate")==null?"":request.getParameter("rate");
		String period = request.getParameter("period")==null?"":request.getParameter("period");
		String sign = request.getParameter("plusmin")==null?"0":request.getParameter("plusmin");
		String ExchngRef = request.getParameter("rbiref")==null?"":request.getParameter("rbiref");
		String ExchngCal = request.getParameter("exch_calc_base_flag")==null?"":request.getParameter("exch_calc_base_flag");
		String InvR1 = request.getParameter("inv_currency")==null?"":request.getParameter("inv_currency");
		String modeper = request.getParameter("modeper")==null?"":request.getParameter("modeper");
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String TAX_STR_CD = request.getParameter("TAX_STR_CD")==null?"":request.getParameter("TAX_STR_CD");
	    String Start_Dt = request.getParameter("Start_Dt")==null?"":request.getParameter("Start_Dt");
	    String SN_CD= request.getParameter("SN_No")==null?"":request.getParameter("SN_No");
		String SN_REVNo = request.getParameter("SN_Rev_No")==null?"":request.getParameter("SN_Rev_No");
		String exchg_rate_note = request.getParameter("exchg_rate_note")==null?"":request.getParameter("exchg_rate_note");
		String bill_end_dt_clause = request.getParameter("bill_end_dt_clause")==null?"Y":request.getParameter("bill_end_dt_clause"); 
		
		exchg_rate_note = snglQuot.replaceSingleQuotes(exchg_rate_note);
		String inv_criteria = request.getParameter("inv_criteria")==null?"":request.getParameter("inv_criteria");
		try
		{		
			int seq_no=1;
			queryString="select count(*) from LOG_FMS8_CONTRACT_BILLING_DTL where " +
					"CONT_TYPE='L' AND FGSA_NO='"+fgsa_cd+"' AND SN_NO='"+SN_CD+"' "
					+ "AND SN_REV_NO='"+SN_REVNo+"' AND CUSTOMER_CD='"+bscode+"' ";
			rset3=stmt3.executeQuery(queryString);
			if(rset3.next())
			{
				String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
				seq_no=Integer.parseInt(seq_no1);
				seq_no++;
			}
			
			query = "INSERT INTO LOG_FMS8_CONTRACT_BILLING_DTL SELECT CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,"
					+ "CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,"
					+ "EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD,EMP_CD,"
					+ "ENT_DT,FLAG,EXCHG_RATE_NOTE,INV_CRITERIA,'"+seq_no+"','' FROM FMS7_SN_BILLING_DTL WHERE "
					+ "FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' "
					+ "And CONT_TYPE='L' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"' ";
			stmt.executeUpdate(query);
			
			query ="SELECT customer_cd FROM FMS7_SN_BILLING_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'";
			rset=stmt.executeQuery(query);			
			if(rset.next())
			{
				if(rset.getString(1)!=null)
				{
					if(!rset.getString(1).trim().equals(""))
					{
						query1 = "DELETE FROM FMS7_SN_BILLING_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'And CONT_TYPE='L' ";
						stmt1.executeUpdate(query1);
					}
				}
			}	
			query ="insert into FMS7_SN_BILLING_DTL(CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONT_TYPE," +
					"BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,EXCHNG_RATE_CD," +
					"EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD,EXCHG_RATE_NOTE,EMP_CD,ENT_DT," +
					"FLAG,INV_CRITERIA) values('"+bscode+"','"+fgsa_cd+"','0','"+SN_CD+"','"+SN_REVNo+"','L'," +
					"'"+Freq+"','"+period+"','"+rate+"','"+sign+"','"+modeper+"','"+ExchngRef+"','"+ExchngCal+"'," +
					"'"+InvR1+"','"+PayCur+"','"+TAX_STR_CD+"','"+exchg_rate_note+"','"+user_cd+"',sysdate,'"+bill_end_dt_clause+"','"+inv_criteria+"') ";					
			stmt.executeUpdate(query);
			dbcon.commit();
			msg="FGSA-REVNO-LOA-REVNO "+fgsa_cd+"-"+fgsa_rev_no+"-"+SN_CD+"-"+SN_REVNo+" UPDATED with BILLING PERIOD CLAUSE as "+bill_end_dt_clause+" ";
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
			e.printStackTrace();
			msg="Billing Details of List of Agreement is not Inserted!!";	
		}
		url="../contract_master/frm_LOA_bill_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&Start_Dt="+Start_Dt+"&TAX_STR_CD="+TAX_STR_CD+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REVNo+"&inv_criteria="+inv_criteria+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}

	
	public void FGSA_LD_Liablility_Entry(HttpServletRequest request) throws Exception
	{
		methodName = "FGSA_LD_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage1")==null?"":request.getParameter("percentage1");
		String price1 = request.getParameter("price1")==null?"":request.getParameter("price1");
		String promiseOn1 = request.getParameter("promiseOn1")==null?"":request.getParameter("promiseOn1");
		String low_percentage = request.getParameter("low_percentage")==null?"":request.getParameter("low_percentage");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
		String DCQ_FLG = request.getParameter("DCQ_FLG")==null?"":request.getParameter("DCQ_FLG");
		String PNDCQ_FLG = request.getParameter("PNDCQ_FLG")==null?"":request.getParameter("PNDCQ_FLG");
		String MDCQ_FLG = request.getParameter("MDCQ_FLG")==null?"":request.getParameter("MDCQ_FLG");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String ld_flg = request.getParameter("ld_flg")==null?"":request.getParameter("ld_flg");
		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		/*System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+low_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+DCQ_FLG);
		System.out.println("PNDCQ_FLG"+PNDCQ_FLG);
		System.out.println("MDCQ_FLG "+MDCQ_FLG);
		//System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/

		try
		{
			if(ld_flg.equals("Y") || ld.equals("Y"))
			{				
				query ="SELECT customer_cd FROM FMS7_FGSA_LD_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset=stmt.executeQuery(query);
				//System.out.println("customer_cd = "+query);			
				if(rset.next())
				{
					query ="DELETE FROM FMS7_FGSA_LD_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F' ";
					stmt.executeUpdate(query);
					////System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				}	
			    query ="insert into FMS7_FGSA_LD_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, PROMISE_QTY_FREQ, LIABILITY_PER, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','F','"+percentage1+"','"+price1+"','"+promiseOn1+"','"+low_percentage+"','"+DCQ_FLG+"','"+PNDCQ_FLG+"','"+MDCQ_FLG+"','"+remark+"','"+user_cd+"',sysdate,'Y') ";
				//System.out.println("Insert query"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Liability Details Successfully Saved For Selected FGSA !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);			
		}
		url="../contract_master/frm_FGSA_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	
	
	public void FGSA_TOP_Liablility_Entry(HttpServletRequest request) throws Exception
	{
		methodName = "FGSA_TOP_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage2")==null?"":request.getParameter("percentage2");
		String price1 = request.getParameter("price2")==null?"":request.getParameter("price2");
		String promiseOn1 = request.getParameter("promiseOn2")==null?"":request.getParameter("promiseOn2");
		String remark = request.getParameter("remark2")==null?"":request.getParameter("remark2");
		String TOP_percentage = request.getParameter("top_percentage")==null?"":request.getParameter("top_percentage");
		String obligation = request.getParameter("obligation")==null?"":request.getParameter("obligation");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String top_flg = request.getParameter("top_flg")==null?"":request.getParameter("top_flg");
		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		/*System.out.println("top_flg"+top_flg);		
		System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+TOP_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+obligation);
		//System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/

		try
		{
			if(top_flg.equals("Y") || top.equals("Y"))
			{
				//System.out.println("Checkpoint2");
				query ="SELECT customer_cd FROM FMS7_FGSA_TOP_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset=stmt.executeQuery(query);
				//System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);			
				if(rset.next())
				{
					query ="DELETE FROM FMS7_FGSA_TOP_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F' ";
					stmt.executeUpdate(query);
					//System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				}	
			    query ="insert into FMS7_FGSA_TOP_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','F','"+percentage1+"','"+price1+"','"+TOP_percentage+"','"+obligation+"','"+promiseOn1+"','"+remark+"','"+user_cd+"',sysdate,'Y') ";
			    //System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Take or Pay Details Successfully Saved For Selected FGSA !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liablity Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
			
		}
		url="../contract_master/frm_FGSA_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	
	
	public void FGSA_MUG_Liablility_Entry(HttpServletRequest request) throws Exception
	{		
		methodName = "FGSA_MUG_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage3")==null?"":request.getParameter("percentage3");
		String price1 = request.getParameter("price3")==null?"":request.getParameter("price3");
		String rec_percentage = request.getParameter("rec_percentage")==null?"":request.getParameter("rec_percentage");
		String remark = request.getParameter("remark3")==null?"":request.getParameter("remark3");
		String mug_percentage = request.getParameter("mug_percentage")==null?"":request.getParameter("mug_percentage");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String mug_flg = request.getParameter("mug_flg")==null?"":request.getParameter("mug_flg");
		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		/*//System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("rec_percentage"+rec_percentage);
		System.out.println("mug_percentage"+mug_percentage);
		System.out.println("remark"+remark);		
		//	System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/

		try
		{
			if(mug_flg.equals("Y") || mug.equals("Y"))
			{
				query ="SELECT customer_cd FROM FMS7_FGSA_MAKEUPGAS_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset=stmt.executeQuery(query);
				//System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);				
				if(rset.next())
				{
					query ="DELETE FROM FMS7_FGSA_MAKEUPGAS_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F' ";
					stmt.executeUpdate(query);
					////System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				}	
				query ="insert into FMS7_FGSA_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','F','"+mug_percentage+"','"+rec_percentage+"','"+percentage1+"','"+price1+"','"+remark+"','"+user_cd+"',sysdate,'Y') ";
		        ////System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Make Up Gas Details Successfully Saved For Selected FGSA !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
		}
		url="../contract_master/frm_FGSA_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
	}
	
	
	public void SN_LD_Liablility_Entry(HttpServletRequest request) throws Exception
	{		
		methodName = "SN_LD_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage1")==null?"":request.getParameter("percentage1");
		String price1 = request.getParameter("price1")==null?"":request.getParameter("price1");
		String promiseOn1 = request.getParameter("promiseOn1")==null?"":request.getParameter("promiseOn1");
		String low_percentage = request.getParameter("low_percentage")==null?"":request.getParameter("low_percentage");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
		String DCQ_FLG = request.getParameter("DCQ_FLG")==null?"":request.getParameter("DCQ_FLG");
		String PNDCQ_FLG = request.getParameter("PNDCQ_FLG")==null?"":request.getParameter("PNDCQ_FLG");
		String MDCQ_FLG = request.getParameter("MDCQ_FLG")==null?"":request.getParameter("MDCQ_FLG");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String ld_flg = request.getParameter("ld_flg")==null?"":request.getParameter("ld_flg");
		String SN_CD=request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");//this will pass code
		String SN_REV_NO=request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");//this will pass code
		String ld_from_dt = request.getParameter("ld_from_dt")==null?"":request.getParameter("ld_from_dt");
		String ld_to_dt = request.getParameter("ld_to_dt")==null?"":request.getParameter("ld_to_dt");
		String rate = request.getParameter("sales_gas_price")==null?"":request.getParameter("sales_gas_price");		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		String dcq = request.getParameter("dcq")==null?"0":request.getParameter("dcq");
		/*System.out.println("dcq = "+dcq);
		//System.out.println("ld_from_dt"+ld_from_dt);
		System.out.println("ld_to_dt"+ld_to_dt);	
		System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+low_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+DCQ_FLG);
		System.out.println("PNDCQ_FLG"+PNDCQ_FLG);
		System.out.println("MDCQ_FLG "+MDCQ_FLG);
		System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/

		try
		{
			if(ld_flg.equals("Y") || ld.equals("Y"))
			{				
				query ="SELECT customer_cd FROM FMS7_SN_LD_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REV_NO+"'";
				rset=stmt.executeQuery(query);
				//System.out.println("queryyy"+query);				
				if(rset.next())
				{
					query ="DELETE FROM FMS7_SN_LD_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REV_NO+"'";
					stmt.executeUpdate(query);
					//System.out.println("query"+query);
				}		
			    query ="insert into FMS7_SN_LD_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, SN_NO, SN_REV_NO, PRICE_PER, PRICE, PROMISE_QTY_FREQ, LIABILITY_PER, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG,LD_FROM_DT,LD_TO_DT) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','S','"+SN_CD+"','"+SN_REV_NO+"','"+percentage1+"','"+price1+"','"+promiseOn1+"','"+low_percentage+"','"+DCQ_FLG+"','"+PNDCQ_FLG+"','"+MDCQ_FLG+"','"+remark+"','"+user_cd+"',sysdate,'Y',to_date('"+ld_from_dt+"','dd/mm/yyyy'),to_date('"+ld_to_dt+"','dd/mm/yyyy') )";
				//System.out.println("query"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Liability Details Successfully Saved For Selected SN !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);			
		}
		url="../contract_master/frm_SN_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&rate="+rate+"&dcq="+dcq+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	
	
	public void SN_TOP_Liablility_Entry(HttpServletRequest request) throws Exception
	{
		methodName = "SN_TOP_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage2")==null?"":request.getParameter("percentage2");
		String price1 = request.getParameter("price2")==null?"":request.getParameter("price2");
		String promiseOn1 = request.getParameter("promiseOn2")==null?"":request.getParameter("promiseOn2");
		String remark = request.getParameter("remark2")==null?"":request.getParameter("remark2");
		String TOP_percentage = request.getParameter("top_percentage")==null?"":request.getParameter("top_percentage");
		String obligation = request.getParameter("obligation")==null?"":request.getParameter("obligation");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String top_flg = request.getParameter("top_flg")==null?"":request.getParameter("top_flg");
		//System.out.println("top_flg"+top_flg);
		String SN_CD=request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");//this will pass code
		String SN_REV_NO=request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");//this will pass code
		String top_from_dt = request.getParameter("top_from_dt")==null?"":request.getParameter("top_from_dt");
		String top_to_dt = request.getParameter("top_to_dt")==null?"":request.getParameter("top_to_dt");
		String rate = request.getParameter("sales_gas_price")==null?"":request.getParameter("sales_gas_price");
		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		String dcq = request.getParameter("dcq")==null?"0":request.getParameter("dcq");
		/*//System.out.println("dcq = "+dcq);
		System.out.println("top_from_dt"+top_from_dt);
		System.out.println("top_to_dt"+top_to_dt);		    	
		System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+TOP_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+obligation);
		System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/
		
		try
		{
			if(top_flg.equals("Y") || top.equals("Y"))
			{				
				query ="SELECT customer_cd FROM FMS7_SN_TOP_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REV_NO+"'";
				rset=stmt.executeQuery(query);
				//System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);				
				if(rset.next())
				{
					query ="DELETE FROM FMS7_SN_TOP_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REV_NO+"'";
					stmt.executeUpdate(query);
					//System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				}
			    query ="insert into FMS7_SN_TOP_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, SN_NO, SN_REV_NO, PRICE_PER, PRICE, TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, EMP_CD, ENT_DT, FLAG,TOP_FROM_DT,TOP_TO_DT) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','S','"+SN_CD+"','"+SN_REV_NO+"','"+percentage1+"','"+price1+"','"+TOP_percentage+"','"+obligation+"','"+promiseOn1+"','"+remark+"','"+user_cd+"',sysdate,'Y',to_date('"+top_from_dt+"','dd/mm/yyyy'),to_date('"+top_to_dt+"','dd/mm/yyyy')) ";
			    //System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				
				msg="Take or Pay Details Successfully Saved For Selected SN !!!";
				
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			msg="Liablity Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.printlnception in the "+methodName+" of "+servletName+" "+e);
		}
		url="../contract_master/frm_SN_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&rate="+rate+"&dcq="+dcq+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	
	
	public void SN_MUG_Liablility_Entry(HttpServletRequest request) throws Exception
	{
		methodName = "SN_MUG_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage3")==null?"":request.getParameter("percentage3");
		String price1 = request.getParameter("price3")==null?"":request.getParameter("price3");
		String rec_percentage = request.getParameter("rec_percentage")==null?"":request.getParameter("rec_percentage");
		String remark = request.getParameter("remark3")==null?"":request.getParameter("remark3");
		String mug_percentage = request.getParameter("mug_percentage")==null?"":request.getParameter("mug_percentage");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String mug_flg = request.getParameter("mug_flg")==null?"":request.getParameter("mug_flg");
		String SN_CD=request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");//this will pass code
		String SN_REV_NO=request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");//this will pass code
		String rate = request.getParameter("sales_gas_price")==null?"":request.getParameter("sales_gas_price");
		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		String dcq = request.getParameter("dcq")==null?"0":request.getParameter("dcq");
		
		/*//System.out.println("dcq = "+dcq);
		System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("rec_percentage"+rec_percentage);
		System.out.println("mug_percentage"+mug_percentage);
		System.out.println("remark"+remark);		
		//System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/		
		try
		{
			if(mug_flg.equals("Y") || mug.equals("Y"))
			{
				query ="SELECT customer_cd FROM FMS7_SN_MAKEUPGAS_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REV_NO+"'";
				rset=stmt.executeQuery(query);
				//System.out.println("queryy"+query);				
				if(rset.next())
				{
					query ="DELETE FROM FMS7_SN_MAKEUPGAS_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REV_NO+"'";
					stmt.executeUpdate(query);
					//System.out.println("queryy"+query);
				}	
				query ="insert into FMS7_SN_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, SN_NO, SN_REV_NO, MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','S','"+SN_CD+"','"+SN_REV_NO+"','"+mug_percentage+"','"+rec_percentage+"','"+percentage1+"','"+price1+"','"+remark+"','"+user_cd+"',sysdate,'Y') ";
		        //System.out.println("query"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Make Up Gas Details Successfully Saved For Selected SN !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);			
		}
		url="../contract_master/frm_SN_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&rate="+rate+"&dcq="+dcq+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	
	
	//Changed on 3rd August and 	4th August
	public void LOA_LD_Liablility_Entry(HttpServletRequest request) throws Exception
	{	
		methodName = "LOA_LD_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String loa_no = request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage1")==null?"":request.getParameter("percentage1");
		String price1 = request.getParameter("price1")==null?"":request.getParameter("price1");
		String promiseOn1 = request.getParameter("promiseOn1")==null?"":request.getParameter("promiseOn1");
		String low_percentage = request.getParameter("low_percentage")==null?"":request.getParameter("low_percentage");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
		String DCQ_FLG = request.getParameter("DCQ_FLG")==null?"":request.getParameter("DCQ_FLG");
		String PNDCQ_FLG = request.getParameter("PNDCQ_FLG")==null?"":request.getParameter("PNDCQ_FLG");
		String MDCQ_FLG = request.getParameter("MDCQ_FLG")==null?"":request.getParameter("MDCQ_FLG");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String ld_flg = request.getParameter("ld_flg")==null?"":request.getParameter("ld_flg");
		String SN_CD=request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");//this will pass code
		String SN_REV_NO=request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");//this will pass code
		String ld_from_dt = request.getParameter("ld_from_dt")==null?"":request.getParameter("ld_from_dt");
		String ld_to_dt = request.getParameter("ld_to_dt")==null?"":request.getParameter("ld_to_dt");
		String rate = request.getParameter("sales_gas_price")==null?"":request.getParameter("sales_gas_price");		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		String dcq = request.getParameter("dcq")==null?"0":request.getParameter("dcq");
		/*//System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+low_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+DCQ_FLG);
		System.out.println("PNDCQ_FLG"+PNDCQ_FLG);
		System.out.println("MDCQ_FLG "+MDCQ_FLG);
		System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/
		try
		{
			if(ld_flg.equals("Y") || ld.equals("Y"))
			{
				query ="SELECT customer_cd FROM FMS7_LOA_LD_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' AND LOA_NO='"+loa_no+"' ";
				rset=stmt.executeQuery(query);
				//System.out.println("queryyy"+query);
				
				if(rset.next())
				{
					query ="DELETE FROM FMS7_LOA_LD_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' AND LOA_NO='"+loa_no+"' ";
					stmt.executeUpdate(query);
					//System.out.println("queryyy"+query);
				}
			    query ="insert into FMS7_LOA_LD_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, LOA_NO,LOA_REV_NO, PRICE_PER, PRICE, PROMISE_QTY_FREQ, LIABILITY_PER, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG,LD_FROM_DT,LD_TO_DT) values('"+bscode+"','"+fgsa_cd+"','0','L','"+SN_CD+"','"+SN_REV_NO+"','"+percentage1+"','"+price1+"','"+promiseOn1+"','"+low_percentage+"','"+DCQ_FLG+"','"+PNDCQ_FLG+"','"+MDCQ_FLG+"','"+remark+"','"+user_cd+"',sysdate,'Y',to_date('"+ld_from_dt+"','dd/mm/yyyy'), to_date('"+ld_to_dt+"','dd/mm/yyyy')) ";
				//System.out.println("queryyy"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Liability Details Successfully Saved For Selected LOA !!!";
				
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
			dbcon.rollback();
			msg="Liability Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
		}
		url="../contract_master/frm_LOA_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&rate="+rate+"&dcq="+dcq+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;			
	}
	
	
	//Changed on 3rd August and 	4th August	
	public void LOA_TOP_Liablility_Entry(HttpServletRequest request) throws Exception
	{		
		methodName = "LOA_TOP_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String loa_no = request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage2")==null?"":request.getParameter("percentage2");
		String price1 = request.getParameter("price2")==null?"":request.getParameter("price2");
		String promiseOn1 = request.getParameter("promiseOn2")==null?"":request.getParameter("promiseOn2");
		String remark = request.getParameter("remark2")==null?"":request.getParameter("remark2");
		String TOP_percentage = request.getParameter("top_percentage")==null?"":request.getParameter("top_percentage");
		String obligation = request.getParameter("obligation")==null?"":request.getParameter("obligation");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String top_flg = request.getParameter("top_flg")==null?"":request.getParameter("top_flg");
		//System.out.println("top_flg"+top_flg);
		String SN_CD=request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");//this will pass code
		String SN_REV_NO=request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");//this will pass code
		String top_from_dt = request.getParameter("top_from_dt")==null?"":request.getParameter("top_from_dt");
		String top_to_dt = request.getParameter("top_to_dt")==null?"":request.getParameter("top_to_dt");
		String rate = request.getParameter("sales_gas_price")==null?"":request.getParameter("sales_gas_price");
		String dcq = request.getParameter("dcq")==null?"0":request.getParameter("dcq");
		remark = snglQuot.replaceSingleQuotes(remark);
		
		/*//System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+TOP_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+obligation);
		System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/	
		try
		{
			if(top_flg.equals("Y") || top.equals("Y"))
			{				
				query ="SELECT customer_cd FROM FMS7_LOA_TOP_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' AND LOA_NO='"+loa_no+"' ";				
				//System.out.println("query"+query);		
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					query ="DELETE FROM FMS7_LOA_TOP_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' AND LOA_NO='"+loa_no+"' ";					
					//System.out.println("query"+query);
					stmt.executeUpdate(query);
				}
			    query ="insert into FMS7_LOA_TOP_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, LOA_NO,LOA_REV_NO, PRICE_PER, PRICE, TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, EMP_CD, ENT_DT, FLAG,TOP_FROM_DT,TOP_TO_DT) values('"+bscode+"','"+fgsa_cd+"','0','L','"+SN_CD+"','"+SN_REV_NO+"','"+percentage1+"','"+price1+"','"+TOP_percentage+"','"+obligation+"','"+promiseOn1+"','"+remark+"','"+user_cd+"',sysdate,'Y',to_date('"+top_from_dt+"','dd/mm/yyyy'), to_date('"+top_to_dt+"','dd/mm/yyyy')) ";
			    //System.out.println("query"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Take or Pay Details Successfully Saved For Selected LOA !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liablity Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
		}
		url="../contract_master/frm_LOA_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&rate="+rate+"&dcq="+dcq+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	
	
	//Changed on 3rd August and 4th August	
	public void LOA_MUG_Liablility_Entry(HttpServletRequest request) throws Exception
	{		
		methodName = "LOA_MUG_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String loa_no = request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");
		//String loa_rev_no = request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage3")==null?"":request.getParameter("percentage3");
		String price1 = request.getParameter("price3")==null?"":request.getParameter("price3");
		String rec_percentage = request.getParameter("rec_percentage")==null?"":request.getParameter("rec_percentage");
		String remark = request.getParameter("remark3")==null?"":request.getParameter("remark3");
		String mug_percentage = request.getParameter("mug_percentage")==null?"":request.getParameter("mug_percentage");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String mug_flg = request.getParameter("mug_flg")==null?"":request.getParameter("mug_flg");
		String SN_CD=request.getParameter("SN_CD")==null?"":request.getParameter("SN_CD");//this will pass code
		String SN_REV_NO=request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");//this will pass code
		String rate = request.getParameter("sales_gas_price")==null?"":request.getParameter("sales_gas_price");		
		
		remark = snglQuot.replaceSingleQuotes(remark);
		String dcq = request.getParameter("dcq")==null?"0":request.getParameter("dcq");
		/*//System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("rec_percentage"+rec_percentage);
		System.out.println("mug_percentage"+mug_percentage);
		System.out.println("remark"+remark);		
		System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/

		try
		{
			if(mug_flg.equals("Y") || mug.equals("Y"))
			{
				query ="SELECT customer_cd FROM FMS7_LOA_MAKEUPGAS_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' AND LOA_NO='"+loa_no+"'";
				rset=stmt.executeQuery(query);
				//System.out.println("query"+query);				
				if(rset.next())
				{
					query ="DELETE FROM FMS7_LOA_MAKEUPGAS_DTL WHERE FGSA_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L'AND LOA_NO='"+loa_no+"' ";
					stmt.executeUpdate(query);
					////System.out.printlnln("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				}			
				query ="insert into FMS7_LOA_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, CONT_TYPE, LOA_NO,LOA_REV_NO, MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','0','L','"+SN_CD+"','"+SN_REV_NO+"','"+mug_percentage+"','"+rec_percentage+"','"+percentage1+"','"+price1+"','"+remark+"','"+user_cd+"',sysdate,'Y') ";
		        ////System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Make Up Gas Details Successfully Saved For Selected LOA !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details Are not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
		}
		url="../contract_master/frm_LOA_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&rate="+rate+"&dcq="+dcq+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}

	
	//Changed on 3rd August		
	public void TENDER_LD_Liablility_Entry(HttpServletRequest request) throws Exception
	{
		methodName = "TENDER_LD_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		//String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage1")==null?"":request.getParameter("percentage1");
		String price1 = request.getParameter("price1")==null?"":request.getParameter("price1");
		String promiseOn1 = request.getParameter("promiseOn1")==null?"":request.getParameter("promiseOn1");
		String low_percentage = request.getParameter("low_percentage")==null?"":request.getParameter("low_percentage");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
		String DCQ_FLG = request.getParameter("DCQ_FLG")==null?"":request.getParameter("DCQ_FLG");
		String PNDCQ_FLG = request.getParameter("PNDCQ_FLG")==null?"":request.getParameter("PNDCQ_FLG");
		String MDCQ_FLG = request.getParameter("MDCQ_FLG")==null?"":request.getParameter("MDCQ_FLG");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String ld_flg = request.getParameter("ld_flg")==null?"":request.getParameter("ld_flg");
		//System.out.println("price1 (LD) = "+price1);
		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		/*System.out.println("percentage1"+percentage1);			
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+low_percentage);
		System.out.println("remark"+remark);
		//System.out.println("DCQ_FLG"+DCQ_FLG);
		System.out.println("PNDCQ_FLG"+PNDCQ_FLG);
		System.out.println("MDCQ_FLG "+MDCQ_FLG);
		//	System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/

		try
		{
			if(ld_flg.equals("Y") || ld.equals("Y"))
			{
	
				query ="SELECT customer_cd FROM FMS7_TENDER_LD_DTL WHERE TENDER_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
				rset=stmt.executeQuery(query);
				//System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				
				if(rset.next())
				{
					query ="DELETE FROM FMS7_TENDER_LD_DTL WHERE TENDER_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T' ";
					stmt.executeUpdate(query);
					//System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				}
		
			    query ="insert into FMS7_TENDER_LD_DTL(CUSTOMER_CD, TENDER_NO, CONT_TYPE, PRICE_PER, PRICE, PROMISE_QTY_FREQ, LIABILITY_PER, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','T','"+percentage1+"','"+price1+"','"+promiseOn1+"','"+low_percentage+"','"+DCQ_FLG+"','"+PNDCQ_FLG+"','"+MDCQ_FLG+"','"+remark+"','"+user_cd+"',sysdate,'Y') ";
				//System.out.println("query"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Liability Details Successfully Saved For Selected Tender !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details of Liquidated Damages is not Inserted.Reason May be->Existance of Same Data.";	
			////System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
			
		}
		url="../contract_master/frm_Tender_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	

	//Changed on 3rd August		
	public void TENDER_TOP_Liablility_Entry(HttpServletRequest request) throws Exception
	{
		methodName = "TENDER_TOP_Liablility_Entry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage2")==null?"":request.getParameter("percentage2");
		String price1 = request.getParameter("price2")==null?"":request.getParameter("price2");
		String promiseOn1 = request.getParameter("promiseOn2")==null?"":request.getParameter("promiseOn2");
		String remark = request.getParameter("remark2")==null?"":request.getParameter("remark2");
		String TOP_percentage = request.getParameter("top_percentage")==null?"":request.getParameter("top_percentage");
		String obligation = request.getParameter("obligation")==null?"":request.getParameter("obligation");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String top_flg = request.getParameter("top_flg")==null?"":request.getParameter("top_flg");
		//System.out.println("top_flg"+top_flg);
		//System.out.println("price1 (TOP) = "+price1);
		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		/*System.out.println("percentage1"+percentage1);			
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+TOP_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+obligation);
		//System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/

		try
		{
			if(top_flg.equals("Y") || top.equals("Y"))
			{			
				query ="SELECT customer_cd FROM FMS7_TENDER_TOP_DTL WHERE TENDER_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";				
				//System.out.println("query"+query);
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					query ="DELETE FROM FMS7_TENDER_TOP_DTL WHERE TENDER_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T' ";					
					//System.out.println("quer"+query);
					stmt.executeUpdate(query);
				}			
			    query ="insert into FMS7_TENDER_TOP_DTL(CUSTOMER_CD, TENDER_NO, CONT_TYPE, PRICE_PER, PRICE, TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','T','"+percentage1+"','"+price1+"','"+TOP_percentage+"','"+obligation+"','"+promiseOn1+"','"+remark+"','"+user_cd+"',sysdate,'Y') ";
			    //System.out.println("query"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Take or Pay Details Successfully Saved For Selected Tender !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liablity Details of Take or Pay is not Inserted.Reason May be->Existance of Same Data.";						
		}
		url="../contract_master/frm_Tender_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}
	
	
	//Changed on 3rd August	
	public void TENDER_MUG_Liablility_Entry(HttpServletRequest request) throws Exception
	{
		methodName = "TENDER_MUG_Liablility_Entry()";
		HttpSession session = request.getSession();
		
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		//String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage3")==null?"":request.getParameter("percentage3");
		String price1 = request.getParameter("price3")==null?"":request.getParameter("price3");
		String rec_percentage = request.getParameter("rec_percentage")==null?"":request.getParameter("rec_percentage");
		String remark = request.getParameter("remark3")==null?"":request.getParameter("remark3");
		String mug_percentage = request.getParameter("mug_percentage")==null?"":request.getParameter("mug_percentage");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String mug_flg = request.getParameter("mug_flg")==null?"":request.getParameter("mug_flg");		

		////System.out.println("price1 (MUG) = "+price1);
		
		remark = snglQuot.replaceSingleQuotes(remark);
		
		/*System.out.println("percentage1"+percentage1);
		System.out.println("rec_percentage"+rec_percentage);
		System.out.println("mug_percentage"+mug_percentage);
		System.out.println("remark"+remark);		
		//System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/

		try
		{
			if(mug_flg.equals("Y") || mug.equals("Y"))
			{
				query ="SELECT customer_cd FROM FMS7_TENDER_MAKEUPGAS_DTL WHERE TENDER_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
				//System.out.println("query"+query);	
				rset=stmt.executeQuery(query);					
				if(rset.next())
				{
					query ="DELETE FROM FMS7_TENDER_MAKEUPGAS_DTL WHERE TENDER_NO='"+fgsa_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T' ";
					//System.out.println("query"+query);
					stmt.executeUpdate(query);					
				}			
				query ="insert into FMS7_TENDER_MAKEUPGAS_DTL(CUSTOMER_CD, TENDER_NO,CONT_TYPE, MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','T','"+mug_percentage+"','"+rec_percentage+"','"+percentage1+"','"+price1+"','"+remark+"','"+user_cd+"',sysdate,'Y') ";
		       // System.out.println("query"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				msg="Make Up Gas Details Successfully Saved For Selected Tender !!!";
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details Of Make-Up Gas is not Inserted.Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
		}
		url="../contract_master/frm_Tender_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}

	//here fms7_cont_price_dtl
	public void SN_TCQ_Modification_Request(HttpServletRequest request) throws Exception
	{
		methodName = "SN_TCQ_Modification_Request()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		//String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String FGSANO = request.getParameter("FGSANO")==null?"0":request.getParameter("FGSANO");
		String FGSA_REVNO = request.getParameter("FGSA_REVNO")==null?"":request.getParameter("FGSA_REVNO");
		String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
		String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		String BUYER_NO = request.getParameter("BUYER_NO")==null?"":request.getParameter("BUYER_NO");
	    String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	    String buyer_abr = request.getParameter("buyer_abr")==null?"":request.getParameter("buyer_abr");
	    String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
	    String fcc_flag = request.getParameter("fcc_flag")==null?"":request.getParameter("fcc_flag");
	    String fcc_by = request.getParameter("fcc_by")==null?"":request.getParameter("fcc_by");
	    String fcc_date = request.getParameter("fcc_date")==null?"":request.getParameter("fcc_date");
		try
		{
			String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
			String sn_name = request.getParameter("sn_name")==null?"0":request.getParameter("sn_name");
			String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
			String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
			String rev_dt = request.getParameter("rev_dt")==null?"":request.getParameter("rev_dt");
			
			String salesPrice = request.getParameter("salesPrice")==null?"":request.getParameter("salesPrice");
			String tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
			String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
			
			String transportation_charge = request.getParameter("transportation_charge")==null?"":request.getParameter("transportation_charge");
			String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
			String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
			String adv_amt = request.getParameter("adv_amt")==null?"":request.getParameter("adv_amt").trim();
			
			sn_ref_no = obj.replaceSingleQuotes(sn_ref_no);
			remark = obj.replaceSingleQuotes(remark);
			
			//String var_mode = request.getParameter("var_mode")==null?"":request.getParameter("var_mode");
			String variance_qty = request.getParameter("variance_qty")==null?"":request.getParameter("variance_qty");
			
			String buyer_nom = request.getParameter("buyer_nom")==null?"N":request.getParameter("buyer_nom");
			String seller_nom = request.getParameter("seller_nom")==null?"N":request.getParameter("seller_nom");
			
			String day_def = request.getParameter("day_def")==null?"N":request.getParameter("day_def");
			String day_time_from = request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
			String day_time_to = request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");
			
			String mdcq = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
			String mdcqPer = request.getParameter("mdcq_percent")==null?"":request.getParameter("mdcq_percent");
			String mdcq_qty_unit = request.getParameter("mdcq_qty_unit")==null?"":request.getParameter("mdcq_qty_unit");
			
			String obligation = request.getParameter("obligation")==null?"N":request.getParameter("obligation");
			String obg_percent = request.getParameter("obg_percent")==null?"":request.getParameter("obg_percent");
			String obg_qty_unit = request.getParameter("obg_qty_unit")==null?"":request.getParameter("obg_qty_unit");
			
			String measurement = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
			String standard = request.getParameter("standard")==null?"":request.getParameter("standard");
			String temprature = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			String rate_min_bar = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			String rate_max_bar = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");
		
			
			String off_spec_gas_chk = request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
			String energy_off_spec = request.getParameter("energy_off_spec")==null?"":request.getParameter("energy_off_spec");
			String min_energy = request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			String max_energy = request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
			String var_tcq = request.getParameter("var_tcq")==null?"":request.getParameter("var_tcq");
			String tcq_sign = request.getParameter("tcq_sign")==null?"":request.getParameter("tcq_sign");
			
			String[] clause_nm = request.getParameterValues("clause_nm");
			String submitFlag = request.getParameter("submitFlag")==null?"":request.getParameter("submitFlag");
			
			String[] chk_trans = request.getParameterValues("chk_trans");
			String[] chk_delv = request.getParameterValues("chk_delv");
			
			String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
			String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
			
			String buy_m = "N";
			String buy_w ="N";
			String buy_d = "N";
			
			/* ADDED BY RG 24-09-2014 */
			String advance_flag=request.getParameter("advance")==null?"N":request.getParameter("advance");
			String advance_amount=request.getParameter("advance_amount")==null?"":request.getParameter("advance_amount");
			String advance_cur=request.getParameter("advance_cur")==null?"":request.getParameter("advance_cur");
			String discount_amount=request.getParameter("discount_amount")==null?"":request.getParameter("discount_amount");
			String discount_flag=request.getParameter("discount")==null?"N":request.getParameter("discount");
			String tariff_price=request.getParameter("tariff_inr_amount")==null?"":request.getParameter("tariff_inr_amount");
			String tariff_flag=request.getParameter("tariff_inr")==null?"N":request.getParameter("tariff_inr");
			/* ADDED BY RG 24-09-2014 */

			String ADVANCE_COLLECTION = request.getParameter("advance_collection")==null?"N":request.getParameter("advance_collection");
			String ADVANCE_COLLECTION_FLAG = request.getParameter("advance_collection_flag")==null?"N":request.getParameter("advance_collection_flag");
			String firm_qty = request.getParameter("firm_qty")==null?"":request.getParameter("firm_qty");
			String re_qty = request.getParameter("re_qty")==null?"":request.getParameter("re_qty");
			String split_tcq = request.getParameter("split_tcq")==null?"N":request.getParameter("split_tcq");
			
			
			if(buyer_nom.equalsIgnoreCase("Y"))
			{
				if(chk_buyer_nom!=null)
				{
					for(int i=0;i<chk_buyer_nom.length;i++)
					{
						if(chk_buyer_nom[i].equalsIgnoreCase("M"))
						{
							buy_m = "Y"; 
			    		}
						else if(chk_buyer_nom[i].equalsIgnoreCase("W"))
						{
							buy_w = "Y";
						}
						else if(chk_buyer_nom[i].equalsIgnoreCase("D"))
						{
							buy_d = "Y";
						}
					}
				}
			}
			
			String sel_m = "N";
			String sel_w ="N";
			String sel_d = "N";
			
			if(seller_nom.equalsIgnoreCase("Y"))
			{
				if(chk_seller_nom!=null)
				{
					for(int i=0;i<chk_seller_nom.length;i++)
					{
						if(chk_seller_nom[i].equalsIgnoreCase("M"))
						{
							sel_m = "Y"; 
			    		}
						else if(chk_seller_nom[i].equalsIgnoreCase("W"))
						{
							sel_w = "Y";
						}
						else if(chk_seller_nom[i].equalsIgnoreCase("D"))
						{
							sel_d = "Y";
						}
					}
				}	
			}
			//System.out.println("buy_m = "+buy_m+",  buy_w = "+buy_w+",  buy_d = "+buy_d);
			//System.out.println("sel_m = "+sel_m+",  sel_w = "+sel_w+",  sel_d = "+sel_d);			
			if(!mdcq.equalsIgnoreCase("Y"))
			{
				mdcqPer = "";
				mdcq_qty_unit = "1";
			}
			 
			if(!measurement.equalsIgnoreCase("Y"))
			{  
				standard = "";
				temprature = "";
				rate_min_bar = "";
				rate_max_bar = "";
			}
			
			if(!off_spec_gas_chk.equalsIgnoreCase("Y"))
			{
				energy_off_spec = "0";
				min_energy = "";
				max_energy = "";
			}
			
			if(!obligation.equalsIgnoreCase("Y"))
			{
				obg_percent = "";
				obg_qty_unit = "1";
			}
			
			//insert entry in the master
			//System.out.println("off_spec_gas_chk = "+off_spec_gas_chk);				
			String message = "";			
			int count = 0;			
			query = "SELECT MAX(SN_REV_NO) FROM FMS7_SN_MST " +
					"WHERE SN_NO='"+SNNO+"' AND " +
					"CUSTOMER_CD='"+BUYER_NO+"' AND " +
					"FGSA_NO='"+FGSANO+"' AND " +
					"FGSA_REV_NO='"+FGSA_REVNO+"'";			
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				SN_REVNO = ""+(rset.getInt(1)+1);
				++count;
			}
			
			if(count>0)
			{
				//////////////////////*****RG 07102014 For advance clause
				String mapping_id=""+BUYER_NO+"-"+FGSANO+"-"+FGSA_REVNO+"-"+SNNO+"-"+SN_REVNO+"-"+"S";
				query = "DELETE FROM FMS7_CONT_PRICE_DTL WHERE MAPPING_ID='"+mapping_id+"' ";
				//System.out.println("Deleting data from conract_price_dtl..."+query);
				stmt.executeUpdate(query);
				
				if(advance_flag.equalsIgnoreCase("Y")) 
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','1','"+advance_amount+"','"+advance_cur+"','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(discount_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','2','"+discount_amount+"','2','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(tariff_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','3','"+tariff_price+"','1','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				/*********************************************************/
				
				String tmp_SN_REVNO = ""+(Integer.parseInt(SN_REVNO)-1);				
				sn_name = buyer_abr+"-FG"+FGSANO+"-FGREV"+SN_REVNO+"-SN"+SNNO+"-SNREV"+SN_REVNO;				
				query = "INSERT INTO FMS7_SN_MST(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
						"SN_NAME, SIGNING_DT, START_DT, END_DT, REV_DT, TCQ, DCQ, RATE, BUYER_NOM, " +
						"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, " +
						"SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
						"MDCQ_PERCENTAGE, MDCQ_QTY_CD, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
						"PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
						"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, VARIATION_QTY, EMP_CD, ENT_DT, " +
						"FLAG, STATUS, QUANTITY_UNIT, RATE_UNIT, SN_REF_NO, TRANSPORTATION_CHARGE, " +
						"REMARK,ADV_AMT,TCQ_REQUEST_FLAG,TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY,TCQ_REQUEST_CLOSE" +
						//",FCC_FLAG,FCC_BY,FCC_DATE" +
						",ADVANCE_COLLECTION,ADVANCE_COLLECTION_FLAG,FIRM_QTY,RE_QTY,SPLIT_TCQ_FLAG) " +  //RG20140830
						"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", " +
						"'"+sn_name+"', TO_DATE('"+sign_dt+"','dd/mm/yyyy'), TO_DATE('"+st_dt+"','dd/mm/yyyy'), " +
						"TO_DATE('"+end_dt+"','dd/mm/yyyy'), TO_DATE('"+rev_dt+"','DD/MM/YYYY'), '"+tcq+"', " +
						"'"+dcq+"', '"+salesPrice+"', '"+buyer_nom+"', '"+buy_m+"', '"+buy_w+"', '"+buy_d+"', " +
						"'"+seller_nom+"', '"+sel_m+"', '"+sel_w+"', '"+sel_d+"', '"+day_def+"', '"+day_time_from+"', " +
						"'"+day_time_to+"', '"+mdcq+"', '"+mdcqPer+"', '"+mdcq_qty_unit+"', '"+measurement+"', " +
						"'"+standard+"', '"+temprature+"', '"+rate_min_bar+"', '"+rate_max_bar+"', " +
						"'"+off_spec_gas_chk+"', '"+energy_off_spec+"', '"+min_energy+"', " +
						"'"+max_energy+"', '"+variance_qty+"', '"+user_cd+"', " +
						"sysdate, 'T', 'Y', '1', '2', '"+sn_ref_no+"', '"+transportation_charge+"', " +
						"'"+remark+"', '"+adv_amt+"','Y','"+tcq_sign+"','"+var_tcq+"','N' " +
						//",'"+fcc_flag+"','"+fcc_by+"',TO_DATE('"+fcc_date+"','DD/MM/YYYY') " +
						",'"+ADVANCE_COLLECTION+"','"+ADVANCE_COLLECTION_FLAG+"','"+firm_qty+"','"+re_qty+"','"+split_tcq+"')";  
				//System.out.println("TCQ Modification for FMS7_SN_MST query = "+query);
				stmt.executeUpdate(query);
				
				//For Fetching SN LD Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
						"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
						"REMARKS, FLAG FROM FMS7_SN_LD_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";				
				//System.out.println("Liquidated Damages SN query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
							 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
							 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 "'"+rset.getString(4)+"', '"+rset.getString(5)+"', " +
							 "'"+rset.getString(6)+"', '"+rset.getString(7)+"', " +
							 "'"+obj.replaceSingleQuotes(rset.getString(8))+"', " +
							 "'"+rset.getString(9)+"', '"+user_cd+"', sysdate)";
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
							"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
							"REMARKS, FLAG FROM FMS7_FGSA_LD_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";
					//System.out.println("Liquidated Damages FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
								 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 "'"+rset2.getString(4)+"', '"+rset2.getString(5)+"', " +
								 "'"+rset2.getString(6)+"', '"+rset2.getString(7)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(8))+"', " +
								 "'"+rset2.getString(9)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Take Or Pay Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT PRICE_PER, PRICE, TOP_PER, " +
						"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
						"FLAG FROM FMS7_SN_TOP_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";				
				//System.out.println("Take Or Pay SN query = "+query);
				rset = stmt.executeQuery(query);
				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
							 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							 "FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 ""+rset.getString(4)+", '"+rset.getString(5)+"', " +
							 "'"+obj.replaceSingleQuotes(rset.getString(6))+"', " +
							 "'"+rset.getString(7)+"', '"+user_cd+"', sysdate)";			
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, TOP_PER, " +
							"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							"FLAG FROM FMS7_FGSA_TOP_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";					
					//System.out.println("Take Or Pay FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
								 "FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+rset2.getString(5)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(6))+"', " +
								 "'"+rset2.getString(7)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Make Up Gas Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
						"REMARKS, FLAG FROM FMS7_SN_MAKEUPGAS_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";				
				//System.out.println("MAKEUP GAS SN query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
							 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 ""+rset.getString(4)+", '"+obj.replaceSingleQuotes(rset.getString(5))+"', " +
							 "'"+rset.getString(6)+"', '"+user_cd+"', sysdate)";			
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
							"REMARKS, FLAG FROM FMS7_FGSA_MAKEUPGAS_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";			
					//System.out.println("MAKEUP GAS FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
								 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+obj.replaceSingleQuotes(rset2.getString(5))+"', " +
								 "'"+rset2.getString(6)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Billing Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
						"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
						"FLAG, EXCHG_RATE_NOTE FROM FMS7_SN_BILLING_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";				
				//System.out.println("BILLING SN query = "+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
							"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
							"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
							"'"+rset.getString(1)+"', '"+rset.getString(2)+"', "+rset.getString(3)+", " +
							"'"+rset.getString(4)+"', "+rset.getString(5)+", "+rset.getString(6)+", " +
							"'"+rset.getString(7)+"', "+rset.getString(8)+", "+rset.getString(9)+", " +
							""+rset.getString(10)+", '"+user_cd+"', sysdate, '"+rset.getString(11)+"', " +
							"'"+obj.replaceSingleQuotes(rset.getString(12))+"')";			
					//System.out.println("Insert Query For SN Billing Details = "+query1);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"FLAG, EXCHG_RATE_NOTE FROM FMS7_FGSA_BILLING_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";					
					//System.out.println("BILLING FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
								"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
								"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
								"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
								"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								"'"+rset2.getString(1)+"', '"+rset2.getString(2)+"', "+rset2.getString(3)+", " +
								"'"+rset2.getString(4)+"', "+rset2.getString(5)+", "+rset2.getString(6)+", " +
								"'"+rset2.getString(7)+"', "+rset2.getString(8)+", "+rset2.getString(9)+", " +
								""+rset2.getString(10)+", '"+user_cd+"', sysdate, '"+rset2.getString(11)+"', " +
								"'"+obj.replaceSingleQuotes(rset2.getString(12))+"')";				
						//System.out.println("SN Billing Details Insert Query = "+query1);
						stmt1.executeUpdate(query1);
					}
				}
				msg = "Revised SN Detail Submitted - SN No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";
				message = msg;
			}
						
			//Data submission for the transporter
			query = "select count(*) from FMS7_SN_TRANSPORTER_MST where " +
					"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_SN_TRANSPORTER_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_trans!=null)
			{
				for(int i=0;i<chk_trans.length;i++)
				{
					query = "insert into FMS7_SN_TRANSPORTER_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG) " +
							"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				}
			}	
			//msg = "Transporter Details Submitted For SN No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";
			
			//Data submissin for the delivery points
			query = "select count(*) from FMS7_SN_PLANT_MST where " +
					"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_SN_PLANT_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_delv!=null)
			{
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_SN_PLANT_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, CUSTOMER_CD, PLANT_SEQ_NO,  EMP_CD, ENT_DT, FLAG) " +
							" values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				 }
			}	
			//msg  = "Delivery Points Details Submitted For SN No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";			
			//Data submissin for the Clauses
			query = "select count(*) from FMS7_SN_CLAUSE_MST where " +
					"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_SN_CLAUSE_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt.executeUpdate(query);
			}
			if(clause_nm!=null)
			{
				for(int i=0;i<clause_nm.length; i++)
				{
					query = "insert into FMS7_SN_CLAUSE_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, BUYER_CD, CLAUSE_CD,   EMP_CD, ENT_DT, FLAG) " +
							"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+clause_nm[i]+"','"+user_cd+"',sysdate,'T') ";
					//System.out.println(query);
					stmt.executeUpdate(query);
				}
			}	
			//msg  = "Clauses Details Submitted For SN No = "+SNNO+", Name = "+sn_name+" For The Customer "+buyer_name+" !!!";			
			msg = "New SN Revision Submitted Along With TCQ Modification Request !!!";
			url="../contract_master/frm_SN_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="SN Details With TCQ Modification Request Not Submitted !!!";
			System.out.println("Exception in the SNMaster() Method of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();			
			url="../contract_master/frm_SN_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;	
		}
	}
	
	public void LOA_TCQ_Modification_Request(HttpServletRequest request) throws Exception
	{
		methodName = "LOA_TCQ_Modification_Request()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		//String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String FGSANO = request.getParameter("FGSANO")==null?"0":request.getParameter("FGSANO");
		String FGSA_REVNO = request.getParameter("FGSA_REVNO")==null?"":request.getParameter("FGSA_REVNO");
		String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
		String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		String BUYER_NO = request.getParameter("BUYER_NO")==null?"":request.getParameter("BUYER_NO");
	    String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	    String buyer_abr = request.getParameter("buyer_abr")==null?"":request.getParameter("buyer_abr");
	    String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
	    String fcc_flag = request.getParameter("fcc_flag")==null?"":request.getParameter("fcc_flag");
	    String fcc_by = request.getParameter("fcc_by")==null?"":request.getParameter("fcc_by");
	    String fcc_date = request.getParameter("fcc_date")==null?"":request.getParameter("fcc_date");
		try
		{
			String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
			String sn_name = request.getParameter("sn_name")==null?"0":request.getParameter("sn_name");
			String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
			String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
			String rev_dt = request.getParameter("rev_dt")==null?"":request.getParameter("rev_dt");
			
			String salesPrice = request.getParameter("salesPrice")==null?"":request.getParameter("salesPrice");
			String tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
			String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
			
			String transportation_charge = request.getParameter("transportation_charge")==null?"":request.getParameter("transportation_charge");
			String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
			String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
			String adv_amt = request.getParameter("adv_amt")==null?"":request.getParameter("adv_amt").trim();
			
			sn_ref_no = obj.replaceSingleQuotes(sn_ref_no);
			remark = obj.replaceSingleQuotes(remark);
			
			//String var_mode = request.getParameter("var_mode")==null?"":request.getParameter("var_mode");
			String variance_qty = request.getParameter("variance_qty")==null?"":request.getParameter("variance_qty");
			
			String buyer_nom = request.getParameter("buyer_nom")==null?"N":request.getParameter("buyer_nom");
			String seller_nom = request.getParameter("seller_nom")==null?"N":request.getParameter("seller_nom");
			
			String day_def = request.getParameter("day_def")==null?"N":request.getParameter("day_def");
			String day_time_from = request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
			String day_time_to = request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");
			
			String mdcq = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
			String mdcqPer = request.getParameter("mdcq_percent")==null?"":request.getParameter("mdcq_percent");
			String mdcq_qty_unit = request.getParameter("mdcq_qty_unit")==null?"":request.getParameter("mdcq_qty_unit");
			
			String obligation = request.getParameter("obligation")==null?"N":request.getParameter("obligation");
			String obg_percent = request.getParameter("obg_percent")==null?"":request.getParameter("obg_percent");
			String obg_qty_unit = request.getParameter("obg_qty_unit")==null?"":request.getParameter("obg_qty_unit");
			
			String measurement = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
			String standard = request.getParameter("standard")==null?"":request.getParameter("standard");
			String temprature = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			String rate_min_bar = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			String rate_max_bar = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");
		
			
			String off_spec_gas_chk = request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
			String energy_off_spec = request.getParameter("energy_off_spec")==null?"":request.getParameter("energy_off_spec");
			String min_energy = request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			String max_energy = request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
			String var_tcq = request.getParameter("var_tcq")==null?"":request.getParameter("var_tcq");
			String tcq_sign = request.getParameter("tcq_sign")==null?"":request.getParameter("tcq_sign");
			
			String[] clause_nm = request.getParameterValues("clause_nm");
			String submitFlag = request.getParameter("submitFlag")==null?"":request.getParameter("submitFlag");
			
			String[] chk_trans = request.getParameterValues("chk_trans");
			String[] chk_delv = request.getParameterValues("chk_delv");
			
			String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
			String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
			
			String buy_m = "N";
			String buy_w ="N";
			String buy_d = "N";
			
			/* ADDED BY RG 24-09-2014 */
			String advance_flag=request.getParameter("advance")==null?"N":request.getParameter("advance");
			String advance_amount=request.getParameter("advance_amount")==null?"":request.getParameter("advance_amount");
			String advance_cur=request.getParameter("advance_cur")==null?"":request.getParameter("advance_cur");
			String discount_amount=request.getParameter("discount_amount")==null?"":request.getParameter("discount_amount");
			String discount_flag=request.getParameter("discount")==null?"N":request.getParameter("discount");
			String tariff_price=request.getParameter("tariff_inr_amount")==null?"":request.getParameter("tariff_inr_amount");
			String tariff_flag=request.getParameter("tariff_inr")==null?"N":request.getParameter("tariff_inr");
			/* ADDED BY RG 24-09-2014 */

			String ADVANCE_COLLECTION = request.getParameter("advance_collection")==null?"N":request.getParameter("advance_collection");
			String ADVANCE_COLLECTION_FLAG = request.getParameter("advance_collection_flag")==null?"N":request.getParameter("advance_collection_flag");
			String firm_qty = request.getParameter("firm_qty")==null?"":request.getParameter("firm_qty");
			String re_qty = request.getParameter("re_qty")==null?"":request.getParameter("re_qty");
			String split_tcq = request.getParameter("split_tcq")==null?"N":request.getParameter("split_tcq");
			
			
			if(buyer_nom.equalsIgnoreCase("Y"))
			{
				if(chk_buyer_nom!=null)
				{
					for(int i=0;i<chk_buyer_nom.length;i++)
					{
						if(chk_buyer_nom[i].equalsIgnoreCase("M"))
						{
							buy_m = "Y"; 
			    		}
						else if(chk_buyer_nom[i].equalsIgnoreCase("W"))
						{
							buy_w = "Y";
						}
						else if(chk_buyer_nom[i].equalsIgnoreCase("D"))
						{
							buy_d = "Y";
						}
					}
				}
			}
			
			String sel_m = "N";
			String sel_w ="N";
			String sel_d = "N";
			
			if(seller_nom.equalsIgnoreCase("Y"))
			{
				if(chk_seller_nom!=null)
				{
					for(int i=0;i<chk_seller_nom.length;i++)
					{
						if(chk_seller_nom[i].equalsIgnoreCase("M"))
						{
							sel_m = "Y"; 
			    		}
						else if(chk_seller_nom[i].equalsIgnoreCase("W"))
						{
							sel_w = "Y";
						}
						else if(chk_seller_nom[i].equalsIgnoreCase("D"))
						{
							sel_d = "Y";
						}
					}
				}	
			}
			//System.out.println("buy_m = "+buy_m+",  buy_w = "+buy_w+",  buy_d = "+buy_d);
			//System.out.println("sel_m = "+sel_m+",  sel_w = "+sel_w+",  sel_d = "+sel_d);			
			if(!mdcq.equalsIgnoreCase("Y"))
			{
				mdcqPer = "";
				mdcq_qty_unit = "1";
			}
			 
			if(!measurement.equalsIgnoreCase("Y"))
			{  
				standard = "";
				temprature = "";
				rate_min_bar = "";
				rate_max_bar = "";
			}
			
			if(!off_spec_gas_chk.equalsIgnoreCase("Y"))
			{
				energy_off_spec = "0";
				min_energy = "";
				max_energy = "";
			}
			
			if(!obligation.equalsIgnoreCase("Y"))
			{
				obg_percent = "";
				obg_qty_unit = "1";
			}
			
			//insert entry in the master
			//System.out.println("off_spec_gas_chk = "+off_spec_gas_chk);				
			String message = "";			
			int count = 0;			
			query = "SELECT MAX(LOA_REV_NO) FROM FMS7_LOA_MST " +
					"WHERE LOA_NO='"+SNNO+"' AND " +
					"CUSTOMER_CD='"+BUYER_NO+"' AND " +
					"TENDER_NO='"+FGSANO+"'  " ;
				//	"FGSA_REV_NO='"+FGSA_REVNO+"'";			
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				SN_REVNO = ""+(rset.getInt(1)+1);
				++count;
			}
			
			if(count>0)
			{
				//////////////////////*****RG 07102014 For advance clause
				String mapping_id=""+BUYER_NO+"-"+FGSANO+"-"+FGSA_REVNO+"-"+SNNO+"-"+SN_REVNO+"-"+"L";
				query = "DELETE FROM FMS7_CONT_PRICE_DTL WHERE MAPPING_ID='"+mapping_id+"' ";
				//System.out.println("Deleting data from conract_price_dtl..."+query);
				stmt.executeUpdate(query);
				
				if(advance_flag.equalsIgnoreCase("Y")) 
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','1','"+advance_amount+"','"+advance_cur+"','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(discount_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','2','"+discount_amount+"','2','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(tariff_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','3','"+tariff_price+"','1','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				/*********************************************************/
				
				String tmp_SN_REVNO = ""+(Integer.parseInt(SN_REVNO)-1);				
				sn_name = buyer_abr+"-TN"+FGSANO+"-LOA"+SNNO+"-LOAREV"+SN_REVNO;	
				query = "INSERT INTO FMS7_LOA_MST(CUSTOMER_CD, TENDER_NO, LOA_NO, LOA_REV_NO, " +
						"LOA_NAME, SIGNING_DT, START_DT, END_DT, TCQ, DCQ, RATE, BUYER_NOM, " +
						"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, " +
						"SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
						"MDCQ_PERCENTAGE, MDCQ_QTY_CD, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
						"PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
						"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, VARIATION_QTY, EMP_CD, ENT_DT, " +
						"FLAG, STATUS, QUANTITY_UNIT, RATE_UNIT, LOA_REF_NO, TRANSPORTATION_CHARGE, " +
						"REMARK,ADV_AMT,TCQ_REQUEST_FLAG,TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY,TCQ_REQUEST_CLOSE" +
						//",FCC_FLAG,FCC_BY,FCC_DATE" +
						",ADVANCE_COLLECTION,ADVANCE_COLLECTION_FLAG,FIRM_QTY,RE_QTY,SPLIT_TCQ_FLAG) " +  //RG20140830
						"VALUES("+BUYER_NO+", "+FGSANO+", "+SNNO+", "+SN_REVNO+", " +
						"'"+sn_name+"', TO_DATE('"+sign_dt+"','dd/mm/yyyy'), TO_DATE('"+st_dt+"','dd/mm/yyyy'), " +
						"TO_DATE('"+end_dt+"','dd/mm/yyyy'), '"+tcq+"', " +
						"'"+dcq+"', '"+salesPrice+"', '"+buyer_nom+"', '"+buy_m+"', '"+buy_w+"', '"+buy_d+"', " +
						"'"+seller_nom+"', '"+sel_m+"', '"+sel_w+"', '"+sel_d+"', '"+day_def+"', '"+day_time_from+"', " +
						"'"+day_time_to+"', '"+mdcq+"', '"+mdcqPer+"', '"+mdcq_qty_unit+"', '"+measurement+"', " +
						"'"+standard+"', '"+temprature+"', '"+rate_min_bar+"', '"+rate_max_bar+"', " +
						"'"+off_spec_gas_chk+"', '"+energy_off_spec+"', '"+min_energy+"', " +
						"'"+max_energy+"', '"+variance_qty+"', '"+user_cd+"', " +
						"sysdate, 'T', 'Y', '1', '2', '"+sn_ref_no+"', '"+transportation_charge+"', " +
						"'"+remark+"', '"+adv_amt+"','Y','"+tcq_sign+"','"+var_tcq+"','N' " +
						//",'"+fcc_flag+"','"+fcc_by+"',TO_DATE('"+fcc_date+"','DD/MM/YYYY') " +
						",'"+ADVANCE_COLLECTION+"','"+ADVANCE_COLLECTION_FLAG+"','"+firm_qty+"','"+re_qty+"','"+split_tcq+"')";  
				//System.out.println("TCQ Modification for FMS7_SN_MST query = "+query);
				stmt.executeUpdate(query);
				
				//For Fetching SN LD Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
						"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
						"REMARKS, FLAG FROM FMS7_LOA_LD_DTL " +
						"WHERE LOA_NO='"+SNNO+"' AND " +
						"LOA_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='L'";				
				//System.out.println("Liquidated Damages SN query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_LOA_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, LOA_NO, LOA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
							 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
							 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'L', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 "'"+rset.getString(4)+"', '"+rset.getString(5)+"', " +
							 "'"+rset.getString(6)+"', '"+rset.getString(7)+"', " +
							 "'"+obj.replaceSingleQuotes(rset.getString(8))+"', " +
							 "'"+rset.getString(9)+"', '"+user_cd+"', sysdate)";
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
							"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
							"REMARKS, FLAG FROM FMS7_TENDER_LD_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"TENDER_NO='"+FGSANO+"' AND  " +
							"CONT_TYPE='T'";
					//System.out.println("Liquidated Damages FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_LOA_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, LOA_NO, LOA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
								 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'L', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 "'"+rset2.getString(4)+"', '"+rset2.getString(5)+"', " +
								 "'"+rset2.getString(6)+"', '"+rset2.getString(7)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(8))+"', " +
								 "'"+rset2.getString(9)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Take Or Pay Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT PRICE_PER, PRICE, TOP_PER, " +
						"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
						"FLAG FROM FMS7_LOA_TOP_DTL " +
						"WHERE LOA_NO='"+SNNO+"' AND " +
						"LOA_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='L'";				
				//System.out.println("Take Or Pay SN query = "+query);
				rset = stmt.executeQuery(query);
				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_LOA_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, LOA_NO, LOA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
							 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							 "FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'L', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 ""+rset.getString(4)+", '"+rset.getString(5)+"', " +
							 "'"+obj.replaceSingleQuotes(rset.getString(6))+"', " +
							 "'"+rset.getString(7)+"', '"+user_cd+"', sysdate)";			
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, TOP_PER, " +
							"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							"FLAG FROM FMS7_TENDER_TOP_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"TENDER_NO='"+FGSANO+"' AND " +
							//"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='T'";					
					//System.out.println("Take Or Pay FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_LOA_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, LOA_NO, LOA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
								 "FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'L', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+rset2.getString(5)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(6))+"', " +
								 "'"+rset2.getString(7)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Make Up Gas Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
						"REMARKS, FLAG FROM FMS7_LOA_MAKEUPGAS_DTL " +
						"WHERE LOA_NO='"+SNNO+"' AND " +
						"LOA_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='L'";				
				//System.out.println("MAKEUP GAS SN query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_LOA_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, LOA_NO, LOA_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
							 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'L', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 ""+rset.getString(4)+", '"+obj.replaceSingleQuotes(rset.getString(5))+"', " +
							 "'"+rset.getString(6)+"', '"+user_cd+"', sysdate)";			
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
							"REMARKS, FLAG FROM FMS7_TENDER_MAKEUPGAS_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"TENDER_NO='"+FGSANO+"' AND " +
							//"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='T'";			
					//System.out.println("MAKEUP GAS FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_LOA_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, LOA_NO, LOA_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
								 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'L', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+obj.replaceSingleQuotes(rset2.getString(5))+"', " +
								 "'"+rset2.getString(6)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Billing Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
						"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
						"FLAG, EXCHG_RATE_NOTE FROM FMS7_SN_BILLING_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='L'";				
				//System.out.println("BILLING SN query = "+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
							"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
							"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'L', " +
							"'"+rset.getString(1)+"', '"+rset.getString(2)+"', "+rset.getString(3)+", " +
							"'"+rset.getString(4)+"', "+rset.getString(5)+", "+rset.getString(6)+", " +
							"'"+rset.getString(7)+"', "+rset.getString(8)+", "+rset.getString(9)+", " +
							""+rset.getString(10)+", '"+user_cd+"', sysdate, '"+rset.getString(11)+"', " +
							"'"+obj.replaceSingleQuotes(rset.getString(12))+"')";			
					//System.out.println("Insert Query For SN Billing Details = "+query1);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"FLAG, EXCHG_RATE_NOTE FROM FMS7_FGSA_BILLING_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='T'";					
					//System.out.println("BILLING FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
								"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
								"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
								"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
								"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'L', " +
								"'"+rset2.getString(1)+"', '"+rset2.getString(2)+"', "+rset2.getString(3)+", " +
								"'"+rset2.getString(4)+"', "+rset2.getString(5)+", "+rset2.getString(6)+", " +
								"'"+rset2.getString(7)+"', "+rset2.getString(8)+", "+rset2.getString(9)+", " +
								""+rset2.getString(10)+", '"+user_cd+"', sysdate, '"+rset2.getString(11)+"', " +
								"'"+obj.replaceSingleQuotes(rset2.getString(12))+"')";				
						//System.out.println("SN Billing Details Insert Query = "+query1);
						stmt1.executeUpdate(query1);
					}
				}
				msg = "Revised LOA Detail Submitted - LOA No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";
				message = msg;
			}
						
			//Data submission for the transporter
			query = "select count(*) from FMS7_LOA_TRANSPORTER_MST where " +
					"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_LOA_TRANSPORTER_MST where " +
						"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_trans!=null)
			{
				for(int i=0;i<chk_trans.length;i++)
				{
					query = "insert into FMS7_LOA_TRANSPORTER_MST(LOA_NO, LOA_REV_NO, TENDER_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG) " +
							"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				}
			}	
			//msg = "Transporter Details Submitted For SN No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";
			
			//Data submissin for the delivery points
			query = "select count(*) from FMS7_LOA_PLANT_MST where " +
					"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_LOA_PLANT_MST where " +
						"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_delv!=null)
			{
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_LOA_PLANT_MST(LOA_NO, LOA_REV_NO, TENDER_NO, CUSTOMER_CD, PLANT_SEQ_NO,  EMP_CD, ENT_DT, FLAG) " +
							" values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				 }
			}	
			//msg  = "Delivery Points Details Submitted For SN No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";			
			//Data submissin for the Clauses
			query = "select count(*) from FMS7_LOA_CLAUSE_MST where " +
					"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"'  ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_LOA_CLAUSE_MST where " +
						"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
				stmt.executeUpdate(query);
			}
			if(clause_nm!=null)
			{
				for(int i=0;i<clause_nm.length; i++)
				{
					query = "insert into FMS7_LOA_CLAUSE_MST(LOA_NO, LOA_REV_NO, TENDER_NO, BUYER_CD, CLAUSE_CD,   EMP_CD, ENT_DT, FLAG) " +
							"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+clause_nm[i]+"','"+user_cd+"',sysdate,'T') ";
					//System.out.println(query);
					stmt.executeUpdate(query);
				}
			}	
			//msg  = "Clauses Details Submitted For SN No = "+SNNO+", Name = "+sn_name+" For The Customer "+buyer_name+" !!!";			
			msg = "New LOA Revision Submitted Along With TCQ Modification Request !!!";
			url="../contract_master/frm_LOA_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="LOA Details With TCQ Modification Request Not Submitted !!!";
			System.out.println("Exception in the SNMaster() Method of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();			
			url="../contract_master/frm_LOA_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;	
		}
	}
	
	//here fms7_cont_price_dtl
	public void SNMaster(HttpServletRequest request) throws Exception
	{
		methodName = "SNMaster()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		//String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String FGSANO = request.getParameter("FGSANO")==null?"0":request.getParameter("FGSANO");
		String FGSA_REVNO = request.getParameter("FGSA_REVNO")==null?"":request.getParameter("FGSA_REVNO");
		String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
		String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		String BUYER_NO = request.getParameter("BUYER_NO")==null?"":request.getParameter("BUYER_NO");
	    String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	    String buyer_abr = request.getParameter("buyer_abr")==null?"":request.getParameter("buyer_abr");
	    String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
	    
		try
		{
			String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
			String sn_name = request.getParameter("sn_name")==null?"0":request.getParameter("sn_name");
			String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
			String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
			String rev_dt = request.getParameter("rev_dt")==null?"":request.getParameter("rev_dt");
			
			String salesPrice = request.getParameter("salesPrice")==null?"":request.getParameter("salesPrice");
			String tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
			String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
			
			String transportation_charge = request.getParameter("transportation_charge")==null?"":request.getParameter("transportation_charge");
			String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
			String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
			String sn_remark = request.getParameter("sn_remark")==null?"":request.getParameter("sn_remark");//JHP20111224
			String adv_amt = request.getParameter("adv_amt")==null?"":request.getParameter("adv_amt").trim();
			
			sn_ref_no = obj.replaceSingleQuotes(sn_ref_no);
			remark = obj.replaceSingleQuotes(remark);
			
			//String var_mode = request.getParameter("var_mode")==null?"":request.getParameter("var_mode");
			String variance_qty = request.getParameter("variance_qty")==null?"":request.getParameter("variance_qty");
			
			String buyer_nom = request.getParameter("buyer_nom")==null?"N":request.getParameter("buyer_nom");
			String seller_nom = request.getParameter("seller_nom")==null?"N":request.getParameter("seller_nom");
			
			String day_def = request.getParameter("day_def")==null?"N":request.getParameter("day_def");
			String day_time_from = request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
			String day_time_to = request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");
			
			String mdcq = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
			String mdcqPer = request.getParameter("mdcq_percent")==null?"":request.getParameter("mdcq_percent");
			String mdcq_qty_unit = request.getParameter("mdcq_qty_unit")==null?"":request.getParameter("mdcq_qty_unit");
			
			String obligation = request.getParameter("obligation")==null?"N":request.getParameter("obligation");
			String obg_percent = request.getParameter("obg_percent")==null?"":request.getParameter("obg_percent");
			String obg_qty_unit = request.getParameter("obg_qty_unit")==null?"":request.getParameter("obg_qty_unit");
			
			String measurement = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
			String standard = request.getParameter("standard")==null?"":request.getParameter("standard");
			String temprature = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			String rate_min_bar = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			String rate_max_bar = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");
		
			
			String off_spec_gas_chk = request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
			String energy_off_spec = request.getParameter("energy_off_spec")==null?"":request.getParameter("energy_off_spec");
			String min_energy = request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			String max_energy = request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
			
			String fcc_flag = request.getParameter("fcc_flag")==null?"":request.getParameter("fcc_flag");
			String fcc_by = request.getParameter("fcc_by")==null?"":request.getParameter("fcc_by");
			String fcc_date = request.getParameter("fcc_date")==null?"":request.getParameter("fcc_date");
			
			String[] clause_nm = request.getParameterValues("clause_nm");
			String submitFlag = request.getParameter("submitFlag")==null?"":request.getParameter("submitFlag");
			
			String[] chk_trans = request.getParameterValues("chk_trans");
			String[] chk_delv = request.getParameterValues("chk_delv");
			
			String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
			String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
			
			String buy_m = "N";
			String buy_w ="N";
			String buy_d = "N";
			
			/* ADDED BY RG 30-08-2014 */
			String advance_flag=request.getParameter("advance")==null?"N":request.getParameter("advance");
			String advance_amount=request.getParameter("advance_amount")==null?"":request.getParameter("advance_amount");
			String advance_cur=request.getParameter("advance_cur")==null?"":request.getParameter("advance_cur");
			String discount_amount=request.getParameter("discount_amount")==null?"":request.getParameter("discount_amount");
			String discount_flag=request.getParameter("discount")==null?"N":request.getParameter("discount");
			String tariff_price=request.getParameter("tariff_inr_amount")==null?"":request.getParameter("tariff_inr_amount");
			String tariff_flag=request.getParameter("tariff_inr")==null?"N":request.getParameter("tariff_inr");
			/* ADDED BY RG 30-08-2014 */
			
			String ADVANCE_COLLECTION = request.getParameter("advance_collection")==null?"N":request.getParameter("advance_collection");
			String ADVANCE_COLLECTION_FLAG = request.getParameter("advance_collection_flag")==null?"N":request.getParameter("advance_collection_flag");
			String firm_qty = request.getParameter("firm_qty")==null?"":request.getParameter("firm_qty");
			String re_qty = request.getParameter("re_qty")==null?"":request.getParameter("re_qty");
			String split_tcq = request.getParameter("split_tcq")==null?"N":request.getParameter("split_tcq");
			
			if(buyer_nom.equalsIgnoreCase("Y"))
			{
				if(chk_buyer_nom!=null)
				{
					for(int i=0;i<chk_buyer_nom.length;i++)
					{
						if(chk_buyer_nom[i].equalsIgnoreCase("M"))
						{
							buy_m = "Y"; 
			    		}
						else if(chk_buyer_nom[i].equalsIgnoreCase("W"))
						{
							buy_w = "Y";
						}
						else if(chk_buyer_nom[i].equalsIgnoreCase("D"))
						{
							buy_d = "Y";
						}
					}
				}
			}
			
			String sel_m = "N";
			String sel_w ="N";
			String sel_d = "N";
			
			if(seller_nom.equalsIgnoreCase("Y"))
			{
				if(chk_seller_nom!=null)
				{
					for(int i=0;i<chk_seller_nom.length;i++)
					{
						if(chk_seller_nom[i].equalsIgnoreCase("M"))
						{
							sel_m = "Y"; 
			    		}
						else if(chk_seller_nom[i].equalsIgnoreCase("W"))
						{
							sel_w = "Y";
						}
						else if(chk_seller_nom[i].equalsIgnoreCase("D"))
						{
							sel_d = "Y";
						}
					}
				}	
			}
			
			if(!mdcq.equalsIgnoreCase("Y"))
			{
				mdcqPer = "";
				mdcq_qty_unit = "1";
			}
			 
			if(!measurement.equalsIgnoreCase("Y"))
			{  
				standard = "";
				temprature = "";
				rate_min_bar = "";
				rate_max_bar = "";
			}
			
			if(!off_spec_gas_chk.equalsIgnoreCase("Y"))
			{
				energy_off_spec = "0";
				min_energy = "";
				max_energy = "";
			}
			
			if(!obligation.equalsIgnoreCase("Y"))
			{
				obg_percent = "";
				obg_qty_unit = "1";
			}
			
			boolean updateFlag = false;
			query = "SELECT NVL(SIGNING_DT,'') FROM FMS7_SN_MST WHERE SN_NO='"+SNNO+"' AND SN_REV_NO='"+SN_REVNO+"' AND CUSTOMER_CD='"+BUYER_NO+"' AND " +
					"FGSA_NO='"+FGSANO+"' AND FGSA_REV_NO='"+FGSA_REVNO+"' ";
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				String signDt = rset.getString(1)==null?"":rset.getString(1);
				if(!signDt.equals("")) {
					updateFlag = true;
				}
			}
			
			String message = "";
			if(submitFlag.equals("1"))
			{
				query = "UPDATE FMS7_SN_MST SET SN_NAME='"+sn_name+"', SIGNING_DT=TO_DATE('"+sign_dt+"','dd/mm/yyyy'), RENEWAL_DT='', START_DT=to_date('"+st_dt+"','dd/mm/yyyy'), " +
						"END_DT=to_date('"+end_dt+"','dd/mm/yyyy'), TCQ='"+tcq+"', DCQ='"+dcq+"', RATE='"+salesPrice+"',  BUYER_NOM='"+buyer_nom+"', " +
						"BUYER_MONTH_NOM='"+buy_m+"', BUYER_WEEK_NOM='"+buy_w+"', BUYER_DAILY_NOM='"+buy_d+"', REV_DT=TO_DATE('"+rev_dt+"','DD/MM/YYYY'), " +
						"SELLER_NOM='"+seller_nom+"', SELLER_MONTH_NOM='"+sel_m+"', SELLER_WEEK_NOM='"+sel_w+"', SELLER_DAILY_NOM='"+sel_d+"', " +
						"DAY_DEF='"+day_def+"', DAY_START_TIME='"+day_time_from+"', DAY_END_TIME='"+day_time_to+"', MDCQ='"+mdcq+"', " +
						"MDCQ_PERCENTAGE='"+mdcqPer+"', MDCQ_QTY_CD='"+mdcq_qty_unit+"', MEASUREMENT='"+measurement+"', MEAS_STANDARD='"+standard+"', " +
						"MEAS_TEMPERATURE='"+temprature+"', PRESSURE_MIN_BAR='"+rate_min_bar+"', PRESSURE_MAX_BAR='"+rate_max_bar+"', " +
						"OFF_SPEC_GAS='"+off_spec_gas_chk+"', SPEC_GAS_ENERGY_BASE='"+energy_off_spec+"', SPEC_GAS_MIN_ENERGY='"+min_energy+"', " +
						"SPEC_GAS_MAX_ENERGY='"+max_energy+"', VARIATION_QTY='"+variance_qty+"', " +
						"EMP_CD='"+user_cd+"', ENT_DT=sysdate, FLAG='T', STATUS='Y', QUANTITY_UNIT='1', RATE_UNIT='2', " +
						"sn_ref_no='"+sn_ref_no+"', transportation_charge='"+transportation_charge+"', " +
						"remark='"+remark+"',sn_remark='"+sn_remark+"', adv_amt='"+adv_amt+"'," +
						"FCC_FLAG='"+fcc_flag+"',FCC_BY='"+fcc_by+"',FCC_DATE=TO_DATE('"+fcc_date+"','DD/MM/YYYY'),"
						+ "ADVANCE_COLLECTION='"+ADVANCE_COLLECTION+"',ADVANCE_COLLECTION_FLAG='"+ADVANCE_COLLECTION_FLAG+"',"
						+ "FIRM_QTY='"+firm_qty+"',RE_QTY='"+re_qty+"',SPLIT_TCQ_FLAG='"+split_tcq+"' " +
						"WHERE SN_NO='"+SNNO+"' AND SN_REV_NO='"+SN_REVNO+"' AND CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND FGSA_REV_NO='"+FGSA_REVNO+"'";			 
				//System.out.println("SN Update Query = "+query);
				stmt.executeUpdate(query);
				
				if(updateFlag) {
					int seq_no=1;
					String queryString2="SELECT COUNT(*) FROM LOG_FMS8_CONTRACT_MST WHERE CONTRACT_TYPE='S' AND " +
							" CUSTOMER_CD='"+BUYER_NO+"' and  FGSA_NO='"+FGSANO+"' AND FGSA_REV_NO='"+FGSA_REVNO+"' "
							+ "AND SN_NO='"+SNNO+"' AND SN_REV_NO='"+SN_REVNO+"' ";
					rset3=stmt3.executeQuery(queryString2);
					if(rset3.next())
					{
						String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
						seq_no=Integer.parseInt(seq_no1);
						seq_no++;
					}
					
					query = "INSERT INTO LOG_FMS8_CONTRACT_MST SELECT SN_NO, SN_REV_NO,CUSTOMER_CD,FGSA_NO,FGSA_REV_NO, " +
							"SIGNING_DT, RENEWAL_DT, START_DT, END_DT, TCQ, QUANTITY_UNIT, GCV,NCV, RATE, RATE_UNIT, STATUS, BUYER_NOM, " +
							"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, " +
							"SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
							"MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
							"PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
							"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, " +
							"FLAG, SN_NAME, DCQ, MDCQ_QTY_CD, OBLIGATION, OBLIG_PERCENTAGE, OBLIG_QTY_CD, VARIATION_QTY, "
							+ "VARIATION_MODE,VERIFY_FLAG,VERIFY_DT, APPROVE_FLAG, APPROVE_DT,REV_DT, SN_CLOSURE_FLAG,"
							+ "SN_CLOSURE_DT,SN_CLOSURE_QTY, SN_REF_NO, TRANSPORTATION_CHARGE, REMARK,ADV_AMT " +
							",FORMULA_REMARK, TCQ_REQUEST_FLAG,TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY,TCQ_REQUEST_CLOSE, "
							+ "FCC_FLAG,FCC_BY,FCC_DATE,SN_CLOSURE_REQUEST,SN_CLOSURE_CLOSE,TCQ_APPROVAL_DT,"
							+ "SN_REMARK,CUSTOM_DUTY_BENEFIT,SPLIT_TCQ_FLAG,FIRM_QTY,RE_QTY,ADVANCE_COLLECTION_FLAG,"
							+ "ADVANCE_COLLECTION,'S','"+seq_no+"','' FROM "
							+ "FMS7_SN_MST WHERE  CUSTOMER_CD='"+BUYER_NO+"' and  FGSA_NO='"+FGSANO+"' AND FGSA_REV_NO='"+FGSA_REVNO+"' "
							+ "AND SN_NO='"+SNNO+"' AND SN_REV_NO='"+SN_REVNO+"' ";	
					stmt.executeUpdate(query);
				}
				
				//For Fetching SN LD Details Of ZERO Revision And 
				//If NOT Present Then Inserting All These Values Into ZERO Revision FROM FGSA LD Details ...
				query = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
						"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
						"REMARKS, FLAG FROM FMS7_SN_LD_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";				
				//System.out.println("Liquidated Damages SN (ZERO Revision) query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{					
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
							"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
							"REMARKS, FLAG FROM FMS7_FGSA_LD_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";					
					//System.out.println("Liquidated Damages FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
								 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 "'"+rset2.getString(4)+"', '"+rset2.getString(5)+"', " +
								 "'"+rset2.getString(6)+"', '"+rset2.getString(7)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(8))+"', " +
								 "'"+rset2.getString(9)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Take Or Pay Details Of ZERO Revision And 
				//If NOT Present Then Inserting All These Values Into ZERO Revision FROM FGSA Take Or Pay Details ...
				query = "SELECT PRICE_PER, PRICE, TOP_PER, " +
						"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
						"FLAG FROM FMS7_SN_TOP_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";
				//System.out.println("Take Or Pay SN (ZERO Revision) query = "+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, TOP_PER, " +
							"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							"FLAG FROM FMS7_FGSA_TOP_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";
					//System.out.println("Take Or Pay FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
								 "FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+rset2.getString(5)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(6))+"', " +
								 "'"+rset2.getString(7)+"', '"+user_cd+"', sysdate)";
						stmt1.executeUpdate(query1);
					}
				}
			      
				//For Fetching SN MakeUp Gas Details Of ZERO Revision And 
				//If NOT Present Then Inserting All These Values Into ZERO Revision FROM FGSA MakeUp Gas Details ...
				query = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
						"REMARKS, FLAG FROM FMS7_SN_MAKEUPGAS_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";
				//System.out.println("MAKEUP GAS SN (ZERO Revision) query = "+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
				}
				else
				{
					query2 = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
							"REMARKS, FLAG FROM FMS7_FGSA_MAKEUPGAS_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";
					//System.out.println("MAKEUP GAS FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
								 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+obj.replaceSingleQuotes(rset2.getString(5))+"', " +
								 "'"+rset2.getString(6)+"', '"+user_cd+"', sysdate)";
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Billing Details Of ZERO Revision And 
				//If NOT Present Then Inserting All These Values Into ZERO Revision FROM FGSA Billing Details ...
				query = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
						"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
						"FLAG, EXCHG_RATE_NOTE FROM FMS7_SN_BILLING_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";
				//System.out.println("BILLING SN (ZERO Revision) query = "+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
				}
				else
				{
					query2 = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"FLAG, EXCHG_RATE_NOTE FROM FMS7_FGSA_BILLING_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";
					//System.out.println("BILLING FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
						"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
						"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
						"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
						"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
						"'"+rset2.getString(1)+"', "+rset2.getString(2)+", "+rset2.getString(3)+", " +
						"'"+rset2.getString(4)+"', "+rset2.getString(5)+", "+rset2.getString(6)+", " +
						"'"+rset2.getString(7)+"', "+rset2.getString(8)+", "+rset2.getString(9)+", " +
						""+rset2.getString(10)+", '"+user_cd+"', sysdate, '"+rset2.getString(11)+"', " +
						"'"+obj.replaceSingleQuotes(rset2.getString(12))+"')";
						//System.out.println("FMS7_SN_BILLING_DTL FGSA query2 = "+query1);
						stmt1.executeUpdate(query1);
					}
				}
				
				msg  = "SN Detail Modified - SN No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";
				message = msg;
			}
			else if(submitFlag.equals("2")) //Whole Following Loop Is Introduced By Samik Shah On 19th July, 2010 ...
			{
				int count = 0;
				query = "SELECT MAX(SN_REV_NO) FROM FMS7_SN_MST " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"'";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					SN_REVNO = ""+(rset.getInt(1)+1);
					++count;
				}
				
				if(count>0)
				{
					String tmp_SN_REVNO = ""+(Integer.parseInt(SN_REVNO)-1);					
					sn_name = buyer_abr+"-FG"+FGSANO+"-FGREV"+FGSA_REVNO+"-SN"+SNNO+"-SNREV"+SN_REVNO;
					
					query = "INSERT INTO FMS7_SN_MST(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
							"SN_NAME, SIGNING_DT, START_DT, END_DT, REV_DT, TCQ, DCQ, RATE, BUYER_NOM, " +
							"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, " +
							"SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
							"MDCQ_PERCENTAGE, MDCQ_QTY_CD, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
							"PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
							"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, VARIATION_QTY, EMP_CD, ENT_DT, " +
							"FLAG, STATUS, QUANTITY_UNIT, RATE_UNIT, SN_REF_NO, TRANSPORTATION_CHARGE, REMARK,SN_REMARK, ADV_AMT " +
							",ADVANCE_COLLECTION,ADVANCE_COLLECTION_FLAG,FIRM_QTY,RE_QTY,SPLIT_TCQ_FLAG) " +		
							"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", " +
							"'"+sn_name+"', TO_DATE('"+sign_dt+"','dd/mm/yyyy'), TO_DATE('"+st_dt+"','dd/mm/yyyy'), " +
							"TO_DATE('"+end_dt+"','dd/mm/yyyy'), TO_DATE('"+rev_dt+"','DD/MM/YYYY'), '"+tcq+"', " +
							"'"+dcq+"', '"+salesPrice+"', '"+buyer_nom+"', '"+buy_m+"', '"+buy_w+"', '"+buy_d+"', " +
							"'"+seller_nom+"', '"+sel_m+"', '"+sel_w+"', '"+sel_d+"', '"+day_def+"', '"+day_time_from+"', " +
							"'"+day_time_to+"', '"+mdcq+"', '"+mdcqPer+"', '"+mdcq_qty_unit+"', '"+measurement+"', " +
							"'"+standard+"', '"+temprature+"', '"+rate_min_bar+"', '"+rate_max_bar+"', " +
							"'"+off_spec_gas_chk+"', '"+energy_off_spec+"', '"+min_energy+"', " +
							"'"+max_energy+"', '"+variance_qty+"', '"+user_cd+"', " +
							"sysdate, 'T', 'Y', '1', '2', '"+sn_ref_no+"', '"+transportation_charge+"', '"+remark+"', '"+sn_remark+"', '"+adv_amt+"' " +
							",'"+ADVANCE_COLLECTION+"','"+ADVANCE_COLLECTION_FLAG+"','"+firm_qty+"','"+re_qty+"','"+split_tcq+"')";		 
					stmt.executeUpdate(query);
					
					//For Fetching SN LD Details Of Previous Revision And Inserting All These Values Into New Revision ...
					query = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
							"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
							"REMARKS, FLAG FROM FMS7_SN_LD_DTL " +
							"WHERE SN_NO='"+SNNO+"' AND " +
							"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
							"CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='S'";
					//System.out.println("Liquidated Damages SN query = "+query);
					rset = stmt.executeQuery(query);
					if(rset.next())
					{
						query1 = "INSERT INTO FMS7_SN_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
								 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
								 "'"+rset.getString(4)+"', '"+rset.getString(5)+"', " +
								 "'"+rset.getString(6)+"', '"+rset.getString(7)+"', " +
								 "'"+obj.replaceSingleQuotes(rset.getString(8))+"', " +
								 "'"+rset.getString(9)+"', '"+user_cd+"', sysdate)";
						stmt1.executeUpdate(query1);
					}
					else
					{
						query2 = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
								"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
								"REMARKS, FLAG FROM FMS7_FGSA_LD_DTL " +
								"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
								"FGSA_NO='"+FGSANO+"' AND " +
								"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
								"CONT_TYPE='F'";
						//System.out.println("Liquidated Damages FGSA query2 = "+query2);
						rset2 = stmt2.executeQuery(query2);
						if(rset2.next())
						{
							query1 = "INSERT INTO FMS7_SN_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
									 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
									 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
									 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
									 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
									 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
									 "'"+rset2.getString(4)+"', '"+rset2.getString(5)+"', " +
									 "'"+rset2.getString(6)+"', '"+rset2.getString(7)+"', " +
									 "'"+obj.replaceSingleQuotes(rset2.getString(8))+"', " +
									 "'"+rset2.getString(9)+"', '"+user_cd+"', sysdate)";
							stmt1.executeUpdate(query1);
						}
					}
					
					//For Fetching SN Take Or Pay Details Of Previous Revision And Inserting All These Values Into New Revision ...
					query = "SELECT PRICE_PER, PRICE, TOP_PER, " +
							"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							"FLAG FROM FMS7_SN_TOP_DTL " +
							"WHERE SN_NO='"+SNNO+"' AND " +
							"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
							"CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='S'";
					//System.out.println("Take Or Pay SN query = "+query);
					rset = stmt.executeQuery(query);
					if(rset.next())
					{
						query1 = "INSERT INTO FMS7_SN_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
								 "FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
								 ""+rset.getString(4)+", '"+rset.getString(5)+"', " +
								 "'"+obj.replaceSingleQuotes(rset.getString(6))+"', " +
								 "'"+rset.getString(7)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
					else
					{
						query2 = "SELECT PRICE_PER, PRICE, TOP_PER, " +
								"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
								"FLAG FROM FMS7_FGSA_TOP_DTL " +
								"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
								"FGSA_NO='"+FGSANO+"' AND " +
								"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
								"CONT_TYPE='F'";
						//System.out.println("Take Or Pay FGSA query2 = "+query2);
						rset2 = stmt2.executeQuery(query2);
						if(rset2.next())
						{
							query1 = "INSERT INTO FMS7_SN_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
									 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
									 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
									 "FLAG, EMP_CD, ENT_DT) " +
									 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
									 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
									 ""+rset2.getString(4)+", '"+rset2.getString(5)+"', " +
									 "'"+obj.replaceSingleQuotes(rset2.getString(6))+"', " +
									 "'"+rset2.getString(7)+"', '"+user_cd+"', sysdate)";
					
							stmt1.executeUpdate(query1);
						}
					}
					
					//For Fetching SN Make Up Gas Details Of Previous Revision And Inserting All These Values Into New Revision ...
					query = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
							"REMARKS, FLAG FROM FMS7_SN_MAKEUPGAS_DTL " +
							"WHERE SN_NO='"+SNNO+"' AND " +
							"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
							"CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='S'";					
					//System.out.println("MAKEUP GAS SN query = "+query);
					rset = stmt.executeQuery(query);					
					if(rset.next())
					{
						query1 = "INSERT INTO FMS7_SN_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
								 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
								 ""+rset.getString(4)+", '"+obj.replaceSingleQuotes(rset.getString(5))+"', " +
								 "'"+rset.getString(6)+"', '"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
					else
					{
						query2 = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
								"REMARKS, FLAG FROM FMS7_FGSA_MAKEUPGAS_DTL " +
								"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
								"FGSA_NO='"+FGSANO+"' AND " +
								"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
								"CONT_TYPE='F'";
						//System.out.println("MAKEUP GAS FGSA query2 = "+query2);
						rset2 = stmt2.executeQuery(query2);
						if(rset2.next())
						{
							query1 = "INSERT INTO FMS7_SN_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
									 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
									 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
									 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
									 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
									 ""+rset2.getString(4)+", '"+obj.replaceSingleQuotes(rset2.getString(5))+"', " +
									 "'"+rset2.getString(6)+"', '"+user_cd+"', sysdate)";
					
							stmt1.executeUpdate(query1);
						}
					}
					
					//For Fetching SN Billing Details Of Previous Revision And Inserting All These Values Into New Revision ...
					query = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"FLAG, EXCHG_RATE_NOTE FROM FMS7_SN_BILLING_DTL " +
							"WHERE SN_NO='"+SNNO+"' AND " +
							"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
							"CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='S'";					
					System.out.println("BILLING SN query = "+query);
					rset = stmt.executeQuery(query);					
					if(rset.next())
					{
						query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
								"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
								"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
								"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
								"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								"'"+rset.getString(1)+"', '"+rset.getString(2)+"', "+rset.getString(3)+", " +
								"'"+rset.getString(4)+"', "+rset.getString(5)+", "+rset.getString(6)+", " +
								"'"+rset.getString(7)+"', "+rset.getString(8)+", "+rset.getString(9)+", " +
								""+rset.getString(10)+", '"+user_cd+"', sysdate, '"+rset.getString(11)+"', " +
								"'"+obj.replaceSingleQuotes(rset.getString(12))+"')";				
						System.out.println("Insert Query For SN Billing Details = "+query1);
						stmt1.executeUpdate(query1);
					}
					else
					{
						query2 = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
								"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
								"FLAG, EXCHG_RATE_NOTE FROM FMS7_FGSA_BILLING_DTL " +
								"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
								"FGSA_NO='"+FGSANO+"' AND " +
								"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
								"CONT_TYPE='F'";
						//System.out.println("BILLING FGSA query2 = "+query2);
						rset2 = stmt2.executeQuery(query2);
						if(rset2.next())
						{
							query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
									"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
									"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
									"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
									"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
									"'"+rset2.getString(1)+"', '"+rset2.getString(2)+"', "+rset2.getString(3)+", " +
									"'"+rset2.getString(4)+"', "+rset2.getString(5)+", "+rset2.getString(6)+", " +
									"'"+rset2.getString(7)+"', "+rset2.getString(8)+", "+rset2.getString(9)+", " +
									""+rset2.getString(10)+", '"+user_cd+"', sysdate, '"+rset2.getString(11)+"', " +
									"'"+obj.replaceSingleQuotes(rset2.getString(12))+"')";					
							//System.out.println("SN Billing Details Insert Query = "+query1);
							stmt1.executeUpdate(query1);
						}
					}
					
					msg = "Revised SN Detail Submitted - SN No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";
					message = msg;
				}
			}
			
			if(submitFlag.equals("1") || submitFlag.equals("2"))
			{
				//////////////////////*****RG 07102014 For advance clause
				String mapping_id=""+BUYER_NO+"-"+FGSANO+"-"+FGSA_REVNO+"-"+SNNO+"-"+SN_REVNO+"-"+"S";
				query = "DELETE FROM FMS7_CONT_PRICE_DTL WHERE MAPPING_ID='"+mapping_id+"' ";
				//System.out.println("Deleting data from conract_price_dtl..."+query);
				stmt.executeUpdate(query);
				
				if(advance_flag.equalsIgnoreCase("Y")) 
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','1','"+advance_amount+"','"+advance_cur+"','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(discount_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) VALUES('"+mapping_id+"','2','"+discount_amount+"','2','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(tariff_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','3','"+tariff_price+"','1','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				/*********************************************************/
				
				//Data submission for the transporter
				
				String mapid = BUYER_NO+"-"+FGSANO+"-"+FGSA_REVNO+"-"+SNNO+"-"+SN_REVNO;
				int seq_no=1;
				query="select count(*) from LOG_FMS8_LNG_REGAS_TRANS_DTL where " +
						"CONTRACT_TYPE='S' AND MAPPING_ID='"+mapid+"' ";
				rset3=stmt3.executeQuery(query);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				
				query="insert into LOG_FMS8_LNG_REGAS_TRANS_DTL select '"+mapid+"',TRANSPORTER_CD,  EMP_CD," +
				"  ENT_DT,  '',  '',  '',  '',  FLAG ,'"+seq_no+"','S' " +
				"from FMS7_SN_TRANSPORTER_MST " +
				"where SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt2.executeUpdate(query);
				
				query = "select count(*) from FMS7_SN_TRANSPORTER_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				rset = stmt.executeQuery(query);
				int count = 0;
				if(rset.next())
				{
					 count = rset.getInt(1);
				}
				if(count>0)
				{
					query = "delete from FMS7_SN_TRANSPORTER_MST where " +
							"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
					stmt.executeUpdate(query);
				}
				if(chk_trans!=null)
				{
					for(int i=0;i<chk_trans.length;i++)
					{
						query = "insert into FMS7_SN_TRANSPORTER_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG) " +
								"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'T') ";
						stmt.executeUpdate(query);
					}
				}	
				
				//Data submissOn for the delivery points
				seq_no=1;
				query="select count(*) from LOG_FMS8_LNG_REGAS_PLANT_DTL where " +
						"MAPPING_ID='"+mapid+"' AND CONTRACT_TYPE='S' ";
				rset3=stmt3.executeQuery(query);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				query="insert into LOG_FMS8_LNG_REGAS_PLANT_DTL select '"+mapid+"',  PLANT_SEQ_NO,  EMP_CD, ENT_DT,  FLAG " +
				",'"+seq_no+"','S' from FMS7_SN_PLANT_MST " +
				"where SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt2.executeUpdate(query);
				
				query = "select count(*) from FMS7_SN_PLANT_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				rset = stmt.executeQuery(query);
				count = 0;
				if(rset.next())
				{
					 count = rset.getInt(1);
				}
				if(count>0)
				{
					query = "delete from FMS7_SN_PLANT_MST where " +
							"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
					stmt.executeUpdate(query);
				}
				if(chk_delv!=null)
				{
					for(int i=0;i<chk_delv.length;i++)
					{
						query ="insert into FMS7_SN_PLANT_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, CUSTOMER_CD, PLANT_SEQ_NO,  EMP_CD, ENT_DT, FLAG) " +
								" values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'T') ";
						stmt.executeUpdate(query);
					 }
				}	
				
				//Data submissOn for the Clauses
				seq_no=1;
				query="select count(*) from LOG_FMS8_LNG_REGAS_CLAUSE_MST where " +
						"MAPPING_ID='"+mapid+"' AND CONTRACT_TYPE='S' ";
				rset3=stmt3.executeQuery(query);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				query="insert into LOG_FMS8_LNG_REGAS_CLAUSE_MST select '"+mapid+"',CLAUSE_CD,FLAG,EMP_CD,ENT_DT " +
					",'"+seq_no+"','S' " +
					"from FMS7_SN_CLAUSE_MST " +
					"where SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt2.executeUpdate(query);
				
				query = "select count(*) from FMS7_SN_CLAUSE_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				rset = stmt.executeQuery(query);
				count = 0;
				if(rset.next())
				{
					 count = rset.getInt(1);
				}
				if(count>0)
				{
					query = "delete from FMS7_SN_CLAUSE_MST where " +
							"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
					stmt.executeUpdate(query);
				}
				if(clause_nm!=null)
				{
					for(int i=0;i<clause_nm.length; i++)
					{
						query = "insert into FMS7_SN_CLAUSE_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, BUYER_CD, CLAUSE_CD,   EMP_CD, ENT_DT, FLAG) " +
								"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+clause_nm[i]+"','"+user_cd+"',sysdate,'T') ";
						stmt.executeUpdate(query);
					}
				}	
			}
			msg = message;
			url="../contract_master/frm_SN_master.jsp?msg="+message+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
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
			msg="SN Details Not Submitted !!!";
			e.printStackTrace();			
			url="../contract_master/frm_SN_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;	
		}
	}
	
	
	//Following Method Has Been Introduced By Manoj & Modified By Achal Pathak ...
	public void LOAMaster(HttpServletRequest request) throws Exception
	{
		methodName = "LOAMaster()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String FGSANO = request.getParameter("FGSANO")==null?"0":request.getParameter("FGSANO");
		String FGSA_REVNO = request.getParameter("FGSA_REVNO")==null?"":request.getParameter("FGSA_REVNO");
		String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
		String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		String BUYER_NO = request.getParameter("BUYER_NO")==null?"":request.getParameter("BUYER_NO");
	    String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	    String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
	    
		String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
		String sn_name = request.getParameter("sn_name")==null?"0":request.getParameter("sn_name");
		String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		
		try
		{
			String salesPrice = request.getParameter("salesPrice")==null?"":request.getParameter("salesPrice");
			String tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
			String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
			
			String var_mode = request.getParameter("var_mode")==null?"":request.getParameter("var_mode");
			String variance_qty = request.getParameter("variance_qty")==null?"":request.getParameter("variance_qty");
			
			String transportation_charge = request.getParameter("transportation_charge")==null?"":request.getParameter("transportation_charge");
			String loa_ref_no = request.getParameter("loa_ref_no")==null?"":request.getParameter("loa_ref_no");
			String adv_amt = request.getParameter("adv_amt")==null?"":request.getParameter("adv_amt").trim();
			String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
			
			loa_ref_no = obj.replaceSingleQuotes(loa_ref_no);
			remark = obj.replaceSingleQuotes(remark);
			
			String buyer_nom = request.getParameter("buyer_nom")==null?"N":request.getParameter("buyer_nom");
			String seller_nom = request.getParameter("seller_nom")==null?"N":request.getParameter("seller_nom");
			
			String day_def = request.getParameter("day_def")==null?"N":request.getParameter("day_def");
			String day_time_from = request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
			String day_time_to = request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");
			
			String mdcq = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
			String mdcqPer = request.getParameter("mdcq_percent")==null?"":request.getParameter("mdcq_percent");
			String mdcq_qty_unit = request.getParameter("mdcq_qty_unit")==null?"":request.getParameter("mdcq_qty_unit");
			
			String obligation = request.getParameter("obligation")==null?"N":request.getParameter("obligation");
			String obg_percent = request.getParameter("obg_percent")==null?"":request.getParameter("obg_percent");
			String obg_qty_unit = request.getParameter("obg_qty_unit")==null?"":request.getParameter("obg_qty_unit");
			
			String measurement = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
			String standard = request.getParameter("standard")==null?"":request.getParameter("standard");
			String temprature = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			String rate_min_bar = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			String rate_max_bar = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");
		
			
			String off_spec_gas_chk = request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
			String energy_off_spec = request.getParameter("energy_off_spec")==null?"":request.getParameter("energy_off_spec");
			String min_energy = request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			String max_energy = request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
			
			String[] clause_nm = request.getParameterValues("clause_nm");
			String submitFlag = request.getParameter("submitFlag")==null?"":request.getParameter("submitFlag");
			
			String[] chk_trans = request.getParameterValues("chk_trans");
			String[] chk_delv = request.getParameterValues("chk_delv");
			
			String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
			String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
			
			String buy_w ="N";
			String buy_m = "N";
			String buy_d = "N";
			
			/* ADDED BY RG 24-09-2014 */
			String advance_flag=request.getParameter("advance")==null?"N":request.getParameter("advance");
			String advance_amount=request.getParameter("advance_amount")==null?"":request.getParameter("advance_amount");
			String advance_cur=request.getParameter("advance_cur")==null?"":request.getParameter("advance_cur");
			String discount_amount=request.getParameter("discount_amount")==null?"":request.getParameter("discount_amount");
			String discount_flag=request.getParameter("discount")==null?"N":request.getParameter("discount");
			String tariff_price=request.getParameter("tariff_inr_amount")==null?"":request.getParameter("tariff_inr_amount");
			String tariff_flag=request.getParameter("tariff_inr")==null?"N":request.getParameter("tariff_inr");
			/* ADDED BY RG 24-09-2014 */
			
			String ADVANCE_COLLECTION = request.getParameter("advance_collection")==null?"N":request.getParameter("advance_collection");
			String ADVANCE_COLLECTION_FLAG = request.getParameter("advance_collection_flag")==null?"N":request.getParameter("advance_collection_flag");
			String firm_qty = request.getParameter("firm_qty")==null?"":request.getParameter("firm_qty");
			String re_qty = request.getParameter("re_qty")==null?"":request.getParameter("re_qty");
			String split_tcq_flag = request.getParameter("split_tcq")==null?"N":request.getParameter("split_tcq");

			
			if(buyer_nom.equalsIgnoreCase("Y")){
				if(chk_buyer_nom!=null){
				for(int i=0;i<chk_buyer_nom.length;i++)
				{
			      if(chk_buyer_nom[i].equalsIgnoreCase("M"))
			      {
			    	  buy_m = "Y"; 
			    		  
			      }else if(chk_buyer_nom[i].equalsIgnoreCase("W"))
			      {
			    	  buy_w = "Y";
			      }
			      else if(chk_buyer_nom[i].equalsIgnoreCase("D"))
			      {
			    	  buy_d = "Y";
			      }
				 }
			  }
			}
			String sel_w ="N";
			String sel_m = "N";
			String sel_d = "N";
			
			if(seller_nom.equalsIgnoreCase("Y")){
			  if(chk_seller_nom!=null)	{
				for(int i=0;i<chk_seller_nom.length;i++)
				{
			      if(chk_seller_nom[i].equalsIgnoreCase("M"))
			      {
			    	  sel_m = "Y"; 
			    		  
			      }else if(chk_seller_nom[i].equalsIgnoreCase("W"))
			      {
			    	  sel_w = "Y";
			      }
			      else if(chk_seller_nom[i].equalsIgnoreCase("D"))
			      {
			    	  sel_d = "Y";
			      }
				}
			  }	
			}
			if(!mdcq.equalsIgnoreCase("Y"))
			{
				mdcqPer = "";
				mdcq_qty_unit = "1";
			}
			 
			if(!measurement.equalsIgnoreCase("Y"))
			{  
				standard = "";
				temprature = "";
				rate_min_bar = "";
				rate_max_bar = "";
			}
			if(!off_spec_gas_chk.equalsIgnoreCase("Y"))
			{
				energy_off_spec = "0";
				min_energy = "";
				max_energy = "";
			}
			if(!obligation.equalsIgnoreCase("Y"))
			{
				obg_percent = "";
				obg_qty_unit = "1";
			}
			
			// insert entry in the master
		
			// Here 1 is for the submit the data, no sending for the approval.
			String message = "";
			
			/////////////////////*****RG 07102014 For advance clause
			String mapping_id=""+BUYER_NO+"-"+FGSANO+"-"+0+"-"+SNNO+"-"+SN_REVNO+"-"+"L";
			query = "DELETE FROM FMS7_CONT_PRICE_DTL WHERE MAPPING_ID='"+mapping_id+"' ";
			//System.out.println("Deleting data from conract_price_dtl..."+query);
			stmt.executeUpdate(query);
			
			if(advance_flag.equalsIgnoreCase("Y")) 
			{
				query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
						"VALUES('"+mapping_id+"','1','"+advance_amount+"','"+advance_cur+"','Y')";
				//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
				stmt.executeUpdate(query);
			}
			if(discount_flag.equalsIgnoreCase("Y"))
			{
				query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
						"VALUES('"+mapping_id+"','2','"+discount_amount+"','2','Y')";
				//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
				stmt.executeUpdate(query);
			}
			if(tariff_flag.equalsIgnoreCase("Y"))
			{
				query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
						"VALUES('"+mapping_id+"','3','"+tariff_price+"','1','Y')";
				//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
				stmt.executeUpdate(query);
			}
			
			boolean updateFlag = false;
			query = "SELECT NVL(SIGNING_DT,'') FROM FMS7_LOA_MST WHERE LOA_NO='"+SNNO+"' AND LOA_REV_NO='"+SN_REVNO+"' AND CUSTOMER_CD='"+BUYER_NO+"' AND " +
					"TENDER_NO='"+FGSANO+"' ";
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				String signDt = rset.getString(1)==null?"":rset.getString(1);
				if(!signDt.equals("")) {
					updateFlag = true;
				}
			}
			/*********************************************************/
			if(submitFlag.equalsIgnoreCase("1"))
			{
				query = "update fms7_loa_mst set LOA_NAME='"+sn_name+"', SIGNING_DT=to_date('"+sign_dt+"','dd/mm/yyyy'), RENEWAL_DT='', START_DT=to_date('"+st_dt+"','dd/mm/yyyy')," +
					" END_DT=to_date('"+end_dt+"','dd/mm/yyyy'), TCQ='"+tcq+"', DCQ='"+dcq+"', GCV='', NCV='', RATE='"+salesPrice+"',  BUYER_NOM='"+buyer_nom+"' , " +
					" BUYER_MONTH_NOM='"+buy_m+"', BUYER_WEEK_NOM='"+buy_w+"', BUYER_DAILY_NOM='"+buy_d+"', " +
					" SELLER_NOM='"+seller_nom+"', SELLER_MONTH_NOM='"+sel_m+"', SELLER_WEEK_NOM='"+sel_w+"', SELLER_DAILY_NOM='"+sel_d+"', " +
					" DAY_DEF='"+day_def+"', DAY_START_TIME='"+day_time_from+"', DAY_END_TIME='"+day_time_to+"', MDCQ='"+mdcq+"', " +
					" MDCQ_PERCENTAGE='"+mdcqPer+"', MDCQ_QTY_CD='"+mdcq_qty_unit+"', MEASUREMENT='"+measurement+"', MEAS_STANDARD='"+standard+"', " +
					" MEAS_TEMPERATURE='"+temprature+"', PRESSURE_MIN_BAR='"+rate_min_bar+"', PRESSURE_MAX_BAR='"+rate_max_bar+"', " +
					" OFF_SPEC_GAS='"+off_spec_gas_chk+"', SPEC_GAS_ENERGY_BASE='"+energy_off_spec+"', SPEC_GAS_MIN_ENERGY='"+min_energy+"'," +
					" SPEC_GAS_MAX_ENERGY='"+max_energy+"', " +
					" VARIATION_QTY='"+variance_qty+"', EMP_CD ='"+user_cd+"', ENT_DT=sysdate, " +
					" loa_ref_no='"+loa_ref_no+"', transportation_charge='"+transportation_charge+"', remark='"+remark+"', adv_amt='"+adv_amt+"' " +
					"  ,ADVANCE_COLLECTION='"+ADVANCE_COLLECTION+"',ADVANCE_COLLECTION_FLAG='"+ADVANCE_COLLECTION_FLAG+"',"
					+ "FIRM_QTY='"+firm_qty+"', RE_QTY='"+re_qty+"', SPLIT_TCQ_FLAG='"+split_tcq_flag+"' " +  
					" where  LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"'";
			        stmt.executeUpdate(query);
			        
			  msg  = "LoA Detail Submitted - LOA No = "+SNNO+" , Name="+sn_name+" for the Customer "+buyer_name+" !!!";
			  message = msg ;
			}
			else
			{
				query = "update fms7_loa_mst set LOA_NAME='"+sn_name+"', SIGNING_DT=to_date('"+sign_dt+"','dd/mm/yyyy'), RENEWAL_DT='', START_DT=to_date('"+st_dt+"','dd/mm/yyyy')," +
				" END_DT=to_date('"+end_dt+"','dd/mm/yyyy'), TCQ='"+tcq+"', DCQ='"+dcq+"', GCV='', NCV='', RATE='"+salesPrice+"',  BUYER_NOM='"+buyer_nom+"' , " +
				" BUYER_MONTH_NOM='"+buy_m+"', BUYER_WEEK_NOM='"+buy_w+"', BUYER_DAILY_NOM='"+buy_d+"', " +
				" SELLER_NOM='"+seller_nom+"', SELLER_MONTH_NOM='"+sel_m+"', SELLER_WEEK_NOM='"+sel_w+"', SELLER_DAILY_NOM='"+sel_d+"', " +
				" DAY_DEF='"+day_def+"', DAY_START_TIME='"+day_time_from+"', DAY_END_TIME='"+day_time_to+"', MDCQ='"+mdcq+"', " +
				" MDCQ_PERCENTAGE='"+mdcqPer+"', MDCQ_QTY_CD='"+mdcq_qty_unit+"', MEASUREMENT='"+measurement+"', MEAS_STANDARD='"+standard+"', " +
				" MEAS_TEMPERATURE='"+temprature+"', PRESSURE_MIN_BAR='"+rate_min_bar+"', PRESSURE_MAX_BAR='"+rate_max_bar+"', " +
				" OFF_SPEC_GAS='"+off_spec_gas_chk+"', SPEC_GAS_ENERGY_BASE='"+energy_off_spec+"', SPEC_GAS_MIN_ENERGY='"+min_energy+"'," +
				" SPEC_GAS_MAX_ENERGY='"+max_energy+"', " +
				" VARIATION_QTY='"+variance_qty+"', EMP_CD ='"+user_cd+"', ENT_DT=sysdate," +
				" VERIFY_FLAG='Y', VERIFY_DT=sysdate, " +
				" loa_ref_no='"+loa_ref_no+"', transportation_charge='"+transportation_charge+"', remark='"+remark+"', adv_amt='"+adv_amt+"' " +
				"  ,ADVANCE_COLLECTION='"+ADVANCE_COLLECTION+"',ADVANCE_COLLECTION_FLAG='"+ADVANCE_COLLECTION_FLAG+"', "
				+ "FIRM_QTY='"+firm_qty+"', RE_QTY='"+re_qty+"', SPLIT_TCQ_FLAG='"+split_tcq_flag+"' "+  
				" where  LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"'";
				stmt.executeUpdate(query);
				msg = "LoA Detail Submitted - LoA No = "+SNNO+" , Name="+sn_name+" for the Customer "+buyer_name+" !!!";
				message = msg ;
			}
			
			if(updateFlag) {
				int seq_no=1;
				String queryString2="SELECT COUNT(*) FROM LOG_FMS8_CONTRACT_MST WHERE CONTRACT_TYPE='L' AND " +
						" CUSTOMER_CD='"+BUYER_NO+"' and  FGSA_NO='"+FGSANO+"' "
						+ "AND SN_NO='"+SNNO+"' AND SN_REV_NO='"+SN_REVNO+"' ";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				
				query = "INSERT INTO LOG_FMS8_CONTRACT_MST SELECT LOA_NO, LOA_REV_NO,CUSTOMER_CD,TENDER_NO,'0', " +
						"SIGNING_DT, RENEWAL_DT, START_DT, END_DT, TCQ, QUANTITY_UNIT, GCV,NCV, RATE, RATE_UNIT, STATUS, BUYER_NOM, " +
						"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, " +
						"SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
						"MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
						"PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
						"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, " +
						"FLAG, LOA_NAME, DCQ, MDCQ_QTY_CD, '', '', '', VARIATION_QTY, "
						+ "VARIATION_MODE,VERIFY_FLAG,VERIFY_DT, APPROVE_FLAG, APPROVE_DATE,'', LOA_CLOSURE_FLAG,"
						+ "LOA_CLOSURE_DT,LOA_CLOSURE_QTY, LOA_REF_NO, TRANSPORTATION_CHARGE, REMARK,ADV_AMT " +
						",FORMULA_REMARK, '',TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY,'', "
						+ "FCC_FLAG,FCC_BY,FCC_DATE,LOA_CLOSURE_REQUEST,LOA_CLOSURE_CLOSE,'',"
						+ "'',CUSTOM_DUTY_BENEFIT,SPLIT_TCQ_FLAG,FIRM_QTY,RE_QTY,ADVANCE_COLLECTION_FLAG,"
						+ "ADVANCE_COLLECTION,'L','"+seq_no+"','' FROM "
						+ "FMS7_LOA_MST WHERE  CUSTOMER_CD='"+BUYER_NO+"' and  TENDER_NO='"+FGSANO+"' "
						+ "AND LOA_NO='"+SNNO+"' AND LOA_REV_NO='"+SN_REVNO+"' ";	
				stmt.executeUpdate(query);
			}
			
			// Data submission for the transporter
			String mapid = BUYER_NO+"-"+FGSANO+"-0-"+SNNO+"-"+SN_REVNO;
			int seq_no=1;
			query="select count(*) from LOG_FMS8_LNG_REGAS_TRANS_DTL where " +
					"CONTRACT_TYPE='L' AND MAPPING_ID='"+mapid+"' ";
			rset3=stmt3.executeQuery(query);
			if(rset3.next())
			{
				String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
				seq_no=Integer.parseInt(seq_no1);
				seq_no++;
			}
			
			query="insert into LOG_FMS8_LNG_REGAS_TRANS_DTL select '"+mapid+"',TRANSPORTER_CD,  EMP_CD," +
			"  ENT_DT,  '',  '',  '',  '',  FLAG ,'"+seq_no+"','L' " +
			"from FMS7_LOA_TRANSPORTER_MST " +
			"where LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			stmt2.executeUpdate(query);
			
			query = "select count(*) from FMS7_LOA_TRANSPORTER_MST where " +
					" LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"'";
			rset = stmt.executeQuery(query);
			int count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0){
				query = "delete from FMS7_LOA_TRANSPORTER_MST where " +
						"  LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"'";
				stmt.executeUpdate(query);
			}
			if(chk_trans!=null){
			for(int i=0;i<chk_trans.length;i++)
			{
				query ="insert into FMS7_LOA_TRANSPORTER_MST(LOA_NO, LOA_REV_NO, TENDER_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG) " +
						" values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'T') ";
				stmt.executeUpdate(query);
			 }
			}	
			msg  = "Transporter Details Submitted For LOA No = "+SNNO+" , Name="+sn_name+" for the Customer "+buyer_name+" , (Not send for the Approval) ";
			
			// Data submissOn for the delivery points
			seq_no=1;
			query="select count(*) from LOG_FMS8_LNG_REGAS_PLANT_DTL where " +
					"MAPPING_ID='"+mapid+"' AND CONTRACT_TYPE='L' ";
			rset3=stmt3.executeQuery(query);
			if(rset3.next())
			{
				String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
				seq_no=Integer.parseInt(seq_no1);
				seq_no++;
			}
			query="insert into LOG_FMS8_LNG_REGAS_PLANT_DTL select '"+mapid+"',  PLANT_SEQ_NO,  EMP_CD, ENT_DT,  FLAG " +
			",'"+seq_no+"','L' from FMS7_LOA_PLANT_MST " +
			"where LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			stmt2.executeUpdate(query);
			
			
			query = "select count(*) from FMS7_LOA_PLANT_MST where " +
			" LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			rset = stmt.executeQuery(query);
			 count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0){
				query = "delete from FMS7_LOA_PLANT_MST where " +
						"  LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_delv!=null){
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_LOA_PLANT_MST(LOA_NO, LOA_REV_NO, TENDER_NO, CUSTOMER_CD, PLANT_SEQ_NO,  EMP_CD, ENT_DT, FLAG) " +
							" values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				 }
			}	
			msg  = " Delivery Points Details Submitted For LOA No = "+SNNO+" , Name="+sn_name+" for the Customer "+buyer_name+" !!!";
			
//			 Data submissin for the Clauses
			seq_no=1;
			query="select count(*) from LOG_FMS8_LNG_REGAS_CLAUSE_MST where " +
					"MAPPING_ID='"+mapid+"' AND CONTRACT_TYPE='L' ";
			rset3=stmt3.executeQuery(query);
			if(rset3.next())
			{
				String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
				seq_no=Integer.parseInt(seq_no1);
				seq_no++;
			}
			query="insert into LOG_FMS8_LNG_REGAS_CLAUSE_MST select '"+mapid+"',CLAUSE_CD,FLAG,EMP_CD,ENT_DT " +
				",'"+seq_no+"','L' " +
				"from FMS7_LOA_CLAUSE_MST " +
				"where LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			stmt2.executeUpdate(query);
			
			query = "select count(*) from FMS7_LOA_CLAUSE_MST where " +
			" LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			rset = stmt.executeQuery(query);
			 count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_LOA_CLAUSE_MST where " +
						"  LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
				stmt.executeUpdate(query);
			}
			if(clause_nm!=null)
			{
				for(int i=0;i<clause_nm.length; i++)
				{
					query ="insert into FMS7_LOA_CLAUSE_MST(LOA_NO, LOA_REV_NO, TENDER_NO, BUYER_CD, CLAUSE_CD, EMP_CD, ENT_DT, FLAG) " +
							" values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+clause_nm[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				}
			}	
			msg = "Details Submitted For LOA No = "+SNNO+" , Name="+sn_name+" for the Customer "+buyer_name+" !!!";
			url="../contract_master/frm_LOA_master.jsp?msg="+msg+"&flg=update&activity=update&LOA_CD="+SNNO+"&LOA_REVNO="+SN_REVNO+"&TENDER_CD="+FGSANO+"&bscode="+BUYER_NO+"&buyer_cd="+BUYER_NO+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
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
			msg="LOA Details Not Submitted !!!";
			e.printStackTrace();
			url="../contract_master/frm_LOA_master.jsp?msg="+msg+"&flg=update&activity=update&LOA_CD="+SNNO+"&LOA_REVNO="+SN_REVNO+"&TENDER_CD="+FGSANO+"&bscode="+BUYER_NO+"&buyer_cd="+BUYER_NO+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;	
		}		
	}//end of loa master
	
	
	public void TenderMaster(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "TenderMaster()";
		form_name = "frm_tender_master.jsp";		
		HttpSession session = request.getSession();
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String tender_no = request.getParameter("tender_no")==null?"0":request.getParameter("tender_no");
		String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
		String agrtyp = request.getParameter("agrtyp")==null?"":request.getParameter("agrtyp");//RG20200110
		String[] agrtype = request.getParameterValues("agrtype");
		String submission_dt = request.getParameter("submission_dt")==null?"":request.getParameter("submission_dt");
		String closing_dt = request.getParameter("closing_dt")==null?"":request.getParameter("closing_dt");
		String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
		String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String[] status = request.getParameterValues("status");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");		
		remark = obj.replaceSingleQuotes(remark);
		String sale_price = request.getParameter("sale_price")==null?"":request.getParameter("sale_price");
		String tcq = request.getParameter("tcq")==null?"":request.getParameter("tcq");
		String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
		String price_unit_cd = "2";  // 2 for $
		String qty_unit_cd = "1";  // 1 for MMBTU
		String remarks ="";
		String agrBase="";
		String agrStatus="";
		
		for(int i=0;i<agrtype.length;i++)
		{
			//agrBase =(agrtype[i].equals("0")?"X":"D");
			agrBase =agrtyp;
		}
		
		for(int i=0;i<status.length;i++)
		{
			agrStatus=(status[i].equals("0")?"Y":"N");
		}
		
		String buyer_nom = request.getParameter("buyer_nom")==null?"":request.getParameter("buyer_nom");
		String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
		String BUYER_DAILY_NOM="N";
		String BUYER_MONTH_NOM="N";
		String BUYER_WEEK_NOM="N";
		
		String BUYER_NOM_CLAUSE = request.getParameter("buyer_nom_clause")==null?"":request.getParameter("buyer_nom_clause");
		String SELLER_NOM_CLAUSE = request.getParameter("seller_nom_clause")==null?"":request.getParameter("seller_nom_clause");
		String LC_CLAUSE = request.getParameter("lc_clause")==null?"":request.getParameter("lc_clause");
		String BILLING_CLAUSE = request.getParameter("billing_clause")==null?"":request.getParameter("billing_clause");
		String LIABILITY_CLAUSE = request.getParameter("liability_clause")==null?"":request.getParameter("liability_clause");
		
		String advance_adjust_clause=request.getParameter("advance")==null?"N":request.getParameter("advance");
		String advance_adjust=request.getParameter("advance_adjust")==null?"N":request.getParameter("advance_adjust");
		
		for(int i=0;i<chk_buyer_nom.length;i++)
		{
			if(chk_buyer_nom[i].equals("D"))
			{
				BUYER_DAILY_NOM = "Y";
			}
			if(chk_buyer_nom[i].equals("W"))
			{
				BUYER_WEEK_NOM = "Y";
			}
			if(chk_buyer_nom[i].equals("M"))
			{
				BUYER_MONTH_NOM = "Y";
			}
		}
		
		String seller_nom = request.getParameter("seller_nom")==null?"":request.getParameter("seller_nom");
		String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
		String SELLER_DAILY_NOM="N";
		String SELLER_WEEK_NOM="N";
		String SELLER_MONTH_NOM="N";
		
		for(int i=0;i<chk_seller_nom.length;i++)
		{
			if(chk_seller_nom[i].equals("D"))
			{
				SELLER_DAILY_NOM ="Y";
			}
			if(chk_seller_nom[i].equals("W"))
			{
				SELLER_WEEK_NOM ="Y";
			}
			if(chk_seller_nom[i].equals("M"))
			{
				SELLER_MONTH_NOM ="Y";
			}
		}
		
		String day_def = request.getParameter("day_def")==null?"":request.getParameter("day_def");
		String DAY_START_TIME = request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
		String DAY_END_TIME= request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");
		
		String MDCQ_PERCENTAGE="";
		String MDCQ = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
		if(MDCQ.equals("Y"))
		{
			MDCQ_PERCENTAGE= request.getParameter("mdcq_percent");	
		}
		
		String MEASUREMENT = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
		String MEAS_STANDARD="";
		String MEAS_TEMPERATURE="";
		String PRESSURE_MIN_BAR="";
		String PRESSURE_MAX_BAR="";
		
		if(MEASUREMENT.equals("Y"))
		{
			MEAS_STANDARD = request.getParameter("standard")==null?"":request.getParameter("standard");
			MEAS_TEMPERATURE = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			PRESSURE_MIN_BAR = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			PRESSURE_MAX_BAR = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");
		}
		
		String OFF_SPEC_GAS = request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
		String SPEC_GAS_ENERGY_BASE ="";
		String SPEC_GAS_MIN_ENERGY="";
		String SPEC_GAS_MAX_ENERGY="";
		
		if(OFF_SPEC_GAS.equals("Y"))
		{
			SPEC_GAS_ENERGY_BASE = request.getParameter("energy_off_spec")==null?"0":request.getParameter("energy_off_spec");
			SPEC_GAS_MIN_ENERGY = request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			SPEC_GAS_MAX_ENERGY = request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
		}

		int tempTenderCode=1;
		query="select nvl(max(TENDER_NO),'0') from FMS7_TENDER_MST where CUSTOMER_CD='"+buyer_cd+"'";	
		rset = stmt.executeQuery(query);
		if(rset.next())
		{
			tempTenderCode =rset.getInt(1);
		}
		
		try
		{
			if(flg.equals("insert"))
			{
				tempTenderCode +=1;
				
				query ="insert into FMS7_TENDER_MST (CUSTOMER_CD, TENDER_NO, SIGNING_DT, START_DT, END_DT, TENDER_SUBMIT_DT, TENDER_CLOSING_DT, " +
				"SALE_PRICE, PRICE_UNIT_CD, DCQ, TCQ, QTY_UNIT_CD, TENDER_BASE, STATUS, BUYER_NOM, BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, " +
				"SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, " +
				"MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
				"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT,FLAG,REMARKS, BUYER_NOM_CLAUSE,SELLER_NOM_CLAUSE,LC_CLAUSE,BILLING_CLAUSE,LIABILITY_CLAUSE,ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG)" + 
				"values('"+buyer_cd+"','"+tempTenderCode+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy'),to_date('"+submission_dt+"','dd/mm/yyyy'),to_date('"+closing_dt+"','dd/mm/yyyy')," +
				"'"+sale_price+"','"+price_unit_cd+"','"+dcq+"','"+tcq+"','"+qty_unit_cd+"','"+agrBase+"','"+agrStatus+"','"+buyer_nom+"','"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"'," +
				"'"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM+"','"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"'," +
				"'"+MEASUREMENT+"','"+MEAS_STANDARD+"','"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"'," +
				"'"+SPEC_GAS_MIN_ENERGY+"','"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y','"+remark+"', '"+BUYER_NOM_CLAUSE+"', '"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"','"+advance_adjust_clause+"','"+advance_adjust+"')";
				stmt.executeUpdate(query);
				
				String[] chk_trans = request.getParameterValues("chk_trans");
				
				for(int i=0;i<chk_trans.length;i++)
				{
					query ="insert into FMS7_TENDER_TRANSPORTER_MST (CUSTOMER_CD,TENDER_NO, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG) values" +
					"('"+buyer_cd+"','"+tempTenderCode+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'Y')";
					stmt.executeUpdate(query);
				}
				
				String[] chk_delv = request.getParameterValues("chk_delv");
				
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_TENDER_PLANT_MST (CUSTOMER_CD,TENDER_NO ,PLANT_SEQ_NO, EMP_CD, ENT_DT, FLAG) values" +
					"('"+buyer_cd+"','"+tempTenderCode+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'Y')";
					stmt.executeUpdate(query);
				}
				
				String[] clause = request.getParameterValues("cls");
				
				if(clause!=null)
				{
					for(int i=0;i<clause.length;i++)
					{
						query ="insert into FMS7_Tender_CLAUSE_MST (CUSTOMER_CD, TENDER_NO, CLAUSE_CD, REMARK, EMP_CD, ENT_DT, FLAG) values" +
						"('"+buyer_cd+"','"+tempTenderCode+"','"+clause[i]+"','"+remarks+"','"+user_cd+"',sysdate,'Y')";
						stmt.executeUpdate(query);
					}
				}
				
				msg="Tender Details Inserted Successfully !!!";
				url="../contract_master/frm_tender_master.jsp?msg="+msg+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			else if(flg.equals("update"))
			{
				
				int seq_no=1;
				String queryString2="select count(*) from LOG_FMS8_LNG_REGAS_MST where CONTRACT_TYPE='N' AND " +
						" CUSTOMER_CD='"+buyer_cd+"' and  AGREEMENT_NO='"+tender_no+"' and FLAG='Y'";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				
				String queryString1="insert into LOG_FMS8_LNG_REGAS_MST select " +
						"CUSTOMER_CD,  TENDER_NO,  '0', '0',  '0', '0', 'T', TENDER_CLOSING_DT," +
						"  TENDER_SUBMIT_DT,  SIGNING_DT,  START_DT,  END_DT,  '',  '',  TCQ," +
						"  '',  PRICE_UNIT_CD, QTY_UNIT_CD,  SALE_PRICE,  TENDER_BASE,  STATUS,  BUYER_NOM, " +
						" BUYER_MONTH_NOM,  BUYER_WEEK_NOM,  BUYER_DAILY_NOM,  SELLER_NOM,  " +
						"SELLER_MONTH_NOM,  SELLER_WEEK_NOM,  SELLER_DAILY_NOM,  DAY_DEF,  DAY_START_TIME,  " +
						"DAY_END_TIME,  MDCQ,  MDCQ_PERCENTAGE,  MEASUREMENT,  MEAS_STANDARD,  MEAS_TEMPERATURE," +
						"  PRESSURE_MIN_BAR,  PRESSURE_MAX_BAR,  OFF_SPEC_GAS,  SPEC_GAS_ENERGY_BASE,  SPEC_GAS_MIN_ENERGY," +
						"  SPEC_GAS_MAX_ENERGY,  BUYER_NOM_CLAUSE,  SELLER_NOM_CLAUSE,  LIABILITY_CLAUSE,  BILLING_CLAUSE,  " +
						"LC_CLAUSE,  REMARKS, EMP_CD,  to_date(to_char(ENT_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),  '',  '',  '',  '',  FLAG, ADVANCE_ADJUST_CLAUSE," +
						"  ADJUST_FLAG,  '',  '',  '',  '',  '',  DCQ," +
						"  '',  '',  '',  '',  '',  '' " +
						" ,'"+seq_no+"','','', " +
						"'','','','','N',REOPEN_REQUEST_FLAG,REOPEN_REQUEST_DT,REOPEN_APPROVAL_FLAG,REOPEN_APPROVAL_DT,"
						+ "REOPEN_REQUEST_BY,REOPEN_APPROVE_BY,REMARK,'','','','','','' " + 
						" from FMS7_Tender_MST " +
						"where CUSTOMER_CD='"+buyer_cd+"' and  Tender_NO='"+tender_no+"' and FLAG='Y'";
				stmt2.executeUpdate(queryString1);
				
				queryString ="delete from FMS7_Tender_MST where CUSTOMER_CD='"+buyer_cd+"' and  Tender_NO='"+tender_no+"' and FLAG='Y'";
				stmt.executeUpdate(queryString)	;
				
				String mapid = buyer_cd+"-"+tender_no+"-0";
				seq_no=1;
				queryString2="select count(*) from LOG_FMS8_LNG_REGAS_TRANS_DTL where " +
						"CONTRACT_TYPE='N' AND MAPPING_ID='"+mapid+"' ";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				
				queryString1="insert into LOG_FMS8_LNG_REGAS_TRANS_DTL select '"+mapid+"',TRANSPORTER_CD,  EMP_CD," +
				"  ENT_DT,  '',  '',  '',  '',  FLAG ,'"+seq_no+"','N' " +
				"from FMS7_Tender_TRANSPORTER_MST " +
				"where CUSTOMER_CD='"+buyer_cd+"' and  Tender_NO='"+tender_no+"' and FLAG='Y'";
				stmt2.executeUpdate(queryString1);
				
				queryString ="delete  from FMS7_Tender_TRANSPORTER_MST  where CUSTOMER_CD='"+buyer_cd+"' and  Tender_NO='"+tender_no+"' and FLAG='Y'";
				stmt.executeUpdate(queryString)	;
				
				seq_no=1;
				queryString2="select count(*) from LOG_FMS8_LNG_REGAS_PLANT_DTL where " +
						"MAPPING_ID='"+mapid+"' AND CONTRACT_TYPE='N' ";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				queryString1="insert into LOG_FMS8_LNG_REGAS_PLANT_DTL select '"+mapid+"',  PLANT_SEQ_NO,  EMP_CD, ENT_DT,  FLAG " +
				",'"+seq_no+"','N' from FMS7_Tender_PLANT_MST " +
				"where CUSTOMER_CD='"+buyer_cd+"' and  Tender_NO='"+tender_no+"' and FLAG='Y'";
				stmt2.executeUpdate(queryString1);
				
				queryString ="delete  from FMS7_Tender_PLANT_MST where CUSTOMER_CD='"+buyer_cd+"' and  Tender_NO='"+tender_no+"' and FLAG='Y'";
				stmt.executeUpdate(queryString)	;
				
				seq_no=1;
				queryString2="select count(*) from LOG_FMS8_LNG_REGAS_CLAUSE_MST where " +
						"MAPPING_ID='"+mapid+"' AND CONTRACT_TYPE='N' ";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				queryString1="insert into LOG_FMS8_LNG_REGAS_CLAUSE_MST select '"+mapid+"',CLAUSE_CD,FLAG,EMP_CD,ENT_DT " +
				",'"+seq_no+"','N' " +
				"from FMS7_Tender_CLAUSE_MST " +
				"where CUSTOMER_CD ='"+buyer_cd+"' and  Tender_NO='"+tender_no+"' and FLAG='Y'";
				stmt2.executeUpdate(queryString1);
				
				queryString ="delete  from FMS7_Tender_CLAUSE_MST where CUSTOMER_CD ='"+buyer_cd+"' and  Tender_NO='"+tender_no+"' and FLAG='Y'";
				stmt.executeUpdate(queryString)	;
								
				query ="insert into FMS7_TENDER_MST (CUSTOMER_CD, TENDER_NO, SIGNING_DT, START_DT, END_DT, TENDER_SUBMIT_DT, TENDER_CLOSING_DT, " +
				"SALE_PRICE, PRICE_UNIT_CD, DCQ, TCQ, QTY_UNIT_CD, TENDER_BASE, STATUS, BUYER_NOM, BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, " +
				"SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, " +
				"MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
				"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, FLAG,REMARKS, BUYER_NOM_CLAUSE,SELLER_NOM_CLAUSE,LC_CLAUSE,BILLING_CLAUSE,LIABILITY_CLAUSE,ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG)" + 
				"values('"+buyer_cd+"','"+tender_no+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy'),to_date('"+submission_dt+"','dd/mm/yyyy'),to_date('"+closing_dt+"','dd/mm/yyyy')," +
				"'"+sale_price+"','"+price_unit_cd+"','"+dcq+"','"+tcq+"','"+qty_unit_cd+"','"+agrBase+"','"+agrStatus+"','"+buyer_nom+"','"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"'," +
				"'"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM+"','"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"'," +
				"'"+MEASUREMENT+"','"+MEAS_STANDARD+"','"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"'," +
				"'"+SPEC_GAS_MIN_ENERGY+"','"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y','"+remark+"', '"+BUYER_NOM_CLAUSE+"', '"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"','"+advance_adjust_clause+"','"+advance_adjust+"')";
				stmt.executeUpdate(query);
				
				String[] chk_trans = request.getParameterValues("chk_trans");
				
				for(int i=0;i<chk_trans.length;i++)
				{
					query ="insert into FMS7_TENDER_TRANSPORTER_MST (CUSTOMER_CD,TENDER_NO, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG) values" +
					"('"+buyer_cd+"','"+tender_no+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'Y')";
					stmt.executeUpdate(query);
				}
				
				String[] chk_delv = request.getParameterValues("chk_delv");
				
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_TENDER_PLANT_MST (CUSTOMER_CD,TENDER_NO ,PLANT_SEQ_NO, EMP_CD, ENT_DT, FLAG) values" +
					"('"+buyer_cd+"','"+tender_no+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'Y')";
					stmt.executeUpdate(query);
				}
				
				String[] clause = request.getParameterValues("cls");
				
				if(clause!=null)
				{
					for(int i=0;i<clause.length;i++)
					{
						query ="insert into FMS7_Tender_CLAUSE_MST (CUSTOMER_CD, TENDER_NO, CLAUSE_CD, REMARK, EMP_CD, ENT_DT, FLAG) values" +
						"('"+buyer_cd+"','"+tender_no+"','"+clause[i]+"','"+remarks+"','"+user_cd+"',sysdate,'Y')";
						stmt.executeUpdate(query);
					}
				}
				msg="Tender Details Updated Successfully !!!";
				url="../contract_master/frm_tender_master.jsp?buyer_cd="+buyer_cd+"&bscode="+buyer_cd+"&tender_cd="+tender_no+"&tender_cd="+tender_no+"&msg="+msg+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg="Tender Details Not Submitted !!!";
			e.printStackTrace();
			url="../contract_master/frm_tender_master.jsp?msg="+msg+"&flg=insert&activity=insert&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}
		
		
	//Data From frm_FGSA_master.jsp
	public void FGSAAgreementMaster(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "FGSAAgreementMaster()";
		form_name = "frm_FGSA_master.jsp";		
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String methodName="FGSAAgreementMaster()";		
		
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
		String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
		String agrtyp = request.getParameter("agrtyp")==null?"":request.getParameter("agrtyp"); //RG20200110
		String[] agrtype = request.getParameterValues("agrtype");
		String renewal_notice_dt = request.getParameter("renewal_notice_dt")==null?"":request.getParameter("renewal_notice_dt");
		//String buyer_abr = request.getParameter("buyer_abr")==null?"":request.getParameter("buyer_abr");
		//String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
		String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
		String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String[] termspottype = request.getParameterValues("termspottype");
		String[] status = request.getParameterValues("status");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");		
		
		String revNo = request.getParameter("revNo")==null?"":request.getParameter("revNo");//JHP20111231 	
		remark = obj.replaceSingleQuotes(remark);
		
		String agrBase="";
		String agrType="";
		String agrStatus="";
		
		String BUYER_NOM_CLAUSE = request.getParameter("buyer_nom_clause")==null?"":request.getParameter("buyer_nom_clause").trim();
		String SELLER_NOM_CLAUSE = request.getParameter("seller_nom_clause")==null?"":request.getParameter("seller_nom_clause").trim();
		String LC_CLAUSE = request.getParameter("lc_clause")==null?"":request.getParameter("lc_clause").trim();
		String BILLING_CLAUSE = request.getParameter("billing_clause")==null?"":request.getParameter("billing_clause").trim();
		String LIABILITY_CLAUSE = request.getParameter("liability_clause")==null?"":request.getParameter("liability_clause").trim();
		//String advance_adjust_clause = request.getParameter("advance")==null?"N":request.getParameter("advance").trim();
		//String advance_adjust = request.getParameter("advance_adjust")==null?"N":request.getParameter("advance_adjust");
		
	//	System.out.println("................."+advance_adjust);
		
		for(int i=0;i<agrtype.length;i++)
		{
			//System.out.println("agrtype = "+agrtype[i]);
			//agrBase =(agrtype[i].equals("0")?"X":"D"); //RG20200110 commented due to inserting exterminal or delivery
			 agrBase=	agrtyp;
		}
		for(int i=0;i<termspottype.length;i++)
		{
			//System.out.println("termspottype = "+termspottype[i]);
			agrType=(termspottype[i].equals("0")?"T":"S");
		}
		
		agrStatus="Y";
		
		String buyer_nom = request.getParameter("buyer_nom")==null?"":request.getParameter("buyer_nom");
		String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
		String BUYER_DAILY_NOM="N";
		String BUYER_MONTH_NOM="N";
		String BUYER_WEEK_NOM="N";
		
		//System.out.println("Buyer Nom = "+buyer_nom);		
		
		for(int i=0;i<chk_buyer_nom.length;i++)
		{
			//System.out.println("chk_buyer_nom = "+chk_buyer_nom[i]);
			if(chk_buyer_nom[i].equals("D"))
			{
				BUYER_DAILY_NOM ="Y";
			}
			if(chk_buyer_nom[i].equals("W"))
			{
				BUYER_WEEK_NOM ="Y";
			}
			if(chk_buyer_nom[i].equals("M"))
			{
				BUYER_MONTH_NOM ="Y";
			}
		}
		
		String seller_nom = request.getParameter("seller_nom")==null?"":request.getParameter("seller_nom");
		String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
		String SELLER_DAILY_NOM="N";
		String SELLER_WEEK_NOM="N";
		String SELLER_MONTH_NOM="N";
		
		//System.out.println("seller_nom Nom = "+seller_nom);		
		
		for(int i=0;i<chk_seller_nom.length;i++)
		{
			//System.out.println("chk_buyer_nom = "+chk_seller_nom[i]);
			if(chk_seller_nom[i].equals("D"))
			{
				SELLER_DAILY_NOM ="Y";
			}
			if(chk_seller_nom[i].equals("W"))
			{
				SELLER_WEEK_NOM ="Y";
			}
			if(chk_seller_nom[i].equals("M"))
			{
				SELLER_MONTH_NOM ="Y";
			}
		}
		
		String day_def = request.getParameter("day_def")==null?"":request.getParameter("day_def");
		//System.out.println("Day_def : "+day_def);
		String DAY_START_TIME = request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
		String DAY_END_TIME= request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");
		
		String MDCQ_PERCENTAGE="";
		String MDCQ = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
		if(MDCQ.equals("Y"))
		{
			MDCQ_PERCENTAGE= request.getParameter("mdcq_percent");	
		}
		
		String MEASUREMENT = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
		String MEAS_STANDARD="";
		String MEAS_TEMPERATURE="";
		String PRESSURE_MIN_BAR="";
		String PRESSURE_MAX_BAR="";
		
		if(MEASUREMENT.equals("Y"))
		{
			MEAS_STANDARD = request.getParameter("standard")==null?"":request.getParameter("standard");
			MEAS_TEMPERATURE = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			PRESSURE_MIN_BAR = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			PRESSURE_MAX_BAR = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");
		}
		
		String OFF_SPEC_GAS = request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
		String SPEC_GAS_ENERGY_BASE="0";
		String SPEC_GAS_MIN_ENERGY="";
		String SPEC_GAS_MAX_ENERGY="";
		
		if(OFF_SPEC_GAS.equals("Y"))
		{
			SPEC_GAS_ENERGY_BASE = request.getParameter("energy_off_spec")==null?"0":request.getParameter("energy_off_spec");
			SPEC_GAS_MIN_ENERGY = request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			SPEC_GAS_MAX_ENERGY = request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
		}
		
		
		String rev_flag = request.getParameter("rev_flag")==null?"N":request.getParameter("rev_flag");
		String renew_flag = request.getParameter("renew_flag")==null?"N":request.getParameter("renew_flag");
		String rev_dt = request.getParameter("rev_dt")==null?"":request.getParameter("rev_dt");
		
		int tempFGSACode=1;
		query="select nvl(max(FGSA_NO),'0') from FMS7_FGSA_MST where CUSTOMER_CD='"+buyer_cd+"'";	
		rset = stmt.executeQuery(query);
		if(rset.next())
		{
			tempFGSACode = rset.getInt(1);
		}
		else
		{
			tempFGSACode = 0;
		}
		
		try
		{
			if(flg.equals("insert") || renew_flag.equalsIgnoreCase("Y"))
			{
//				JHP Start
				if(renew_flag.equalsIgnoreCase("Y"))
				{
					query="Update FMS7_FGSA_MST SET RENEWAL_FLAG='Y' WHERE CUSTOMER_CD='"+buyer_cd+"' AND FGSA_NO='"+tempFGSACode+"' AND REV_NO='"+revNo+"' " ;
					//System.out.println("Update into FMS7_FGSA_MST Renewal Flag"+query);			
					stmt.executeUpdate(query);		
				}
				
				//JHP End
				
				tempFGSACode += 1;				
				//Insert into FMS7_FGSA_MST ...				
				query = "insert into FMS7_FGSA_MST(CUSTOMER_CD, FGSA_NO, SIGNING_DT, START_DT, END_DT, RENEWAL_DT, FGSA_BASE, FGSA_TYPE, STATUS, BUYER_NOM, " +
				"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
				"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, " +
				"MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
				"SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, FLAG, Rev_no, REV_DT, REMARKS, BUYER_NOM_CLAUSE, SELLER_NOM_CLAUSE, " +
				"LC_CLAUSE, BILLING_CLAUSE, LIABILITY_CLAUSE" +
				//",ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG" +
				") " +
				"values('"+buyer_cd+"','"+tempFGSACode+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy')," +
				"to_date('"+renewal_notice_dt+"','dd/mm/yyyy'),'"+agrBase+"','"+agrType+"','"+agrStatus+"','"+buyer_nom+"'," +
				"'"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"','"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM+"'," +
				"'"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"','"+MEASUREMENT+"','"+MEAS_STANDARD+"'," +
				"'"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"','"+SPEC_GAS_MIN_ENERGY+"'," +
				"'"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y','0', to_date('"+rev_dt+"','dd/mm/yyyy'),'"+remark+"'," +
				"'"+BUYER_NOM_CLAUSE+"', '"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"'" +
				//",'"+advance_adjust_clause+"','"+advance_adjust+"'" +
				")";
				//System.out.println("Insert into FMS7_FGSA_MST"+query);			
				stmt.executeUpdate(query);				
				
				//Insert into FMS7_FGSA_TRANSPORTER_MST ...
				
				String[] chk_trans = request.getParameterValues("chk_trans");
				
				for(int i=0;i<chk_trans.length;i++)
				{
					//System.out.println("chk_trans ["+i+"] = "+chk_trans[i]);
					query ="insert into FMS7_FGSA_TRANSPORTER_MST (FGSA_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG,rev_no) values" +
					"('"+tempFGSACode+"','"+buyer_cd+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'Y','0')";
					////System.out.println("Insert into FGSA_TRANSPORTER_MST ("+i+") = "+query);	
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_FGSA_PLANT_MST ...
				
				String[] chk_delv = request.getParameterValues("chk_delv");
				
				for(int i=0;i<chk_delv.length;i++)
				{
					//System.out.println("chk_delv ["+i+"] = "+chk_delv[i]);
					query ="insert into FMS7_FGSA_PLANT_MST (FGSA_NO, CUSTOMER_CD, PLANT_SEQ_NO, EMP_CD, ENT_DT, FLAG,Rev_no) values" +
					"('"+tempFGSACode+"','"+buyer_cd+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'Y','0')";
					//System.out.println("Insert into FGSA_PLANT_MST ("+i+") = "+query);
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_FGSA_CLAUSE_MST ... 
				
				String[] clause = request.getParameterValues("cls");
				
				if(clause!=null)
				{
					for(int i=0;i<clause.length;i++)
					{
						//System.out.println("Clause ["+i+"] = "+clause[i]);
						query ="insert into FMS7_FGSA_CLAUSE_MST (FGSA_NO, BUYER_CD, CLAUSE_CD,  EMP_CD, ENT_DT, FLAG,Rev_no) values" +
						"('"+tempFGSACode+"','"+buyer_cd+"','"+clause[i]+"','"+user_cd+"',sysdate,'Y','0')";
						////System.out.println("Insert into FGSA_CLAUSE_MST	("+i+") = "+query);
						stmt.executeUpdate(query);
					}
				}
				
				msg="FGSA Details Inserted Successfully !!!";
				url="../contract_master/frm_FGSA_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_CD="+tempFGSACode+"&rev_no=0&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&fgsa_no="+tempFGSACode+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			else if(flg.equals("update") && renew_flag.equalsIgnoreCase("N") && rev_flag.equalsIgnoreCase("N"))
			{
				//String temp_renew_dt = "0";
				int temp_rev_no = 0;
				//int count=0;
								 
				queryString ="Select max(rev_no) from FMS7_FGSA_MST where CUSTOMER_CD='"+buyer_cd+"' and FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				//System.out.println("fetch Max Rev No : "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					temp_rev_no = rset.getInt(1);
				}				

				
				int seq_no=1;
				String queryString2="select count(*) from LOG_FMS8_LNG_REGAS_MST where CONTRACT_TYPE='F' AND " +
						" CUSTOMER_CD='"+buyer_cd+"' and  AGREEMENT_NO='"+fgsa_no+"' AND REV_NO='"+temp_rev_no+"' ";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				
				String queryString1="insert into LOG_FMS8_LNG_REGAS_MST select " +
						"CUSTOMER_CD,  FGSA_NO,  REV_NO, '0',  '0', '0', 'F', REV_DT," +
						"  RENEWAL_DT,  SIGNING_DT,  START_DT,  END_DT,  '',  '',  ''," +
						"  '',  '',  '',  '',  FGSA_BASE,  STATUS,  BUYER_NOM, " +
						" BUYER_MONTH_NOM,  BUYER_WEEK_NOM,  BUYER_DAILY_NOM,  SELLER_NOM,  " +
						"SELLER_MONTH_NOM,  SELLER_WEEK_NOM,  SELLER_DAILY_NOM,  DAY_DEF,  DAY_START_TIME,  " +
						"DAY_END_TIME,  MDCQ,  MDCQ_PERCENTAGE,  MEASUREMENT,  MEAS_STANDARD,  MEAS_TEMPERATURE," +
						"  PRESSURE_MIN_BAR,  PRESSURE_MAX_BAR,  OFF_SPEC_GAS,  SPEC_GAS_ENERGY_BASE,  SPEC_GAS_MIN_ENERGY," +
						"  SPEC_GAS_MAX_ENERGY,  BUYER_NOM_CLAUSE,  SELLER_NOM_CLAUSE,  LIABILITY_CLAUSE,  BILLING_CLAUSE,  " +
						"LC_CLAUSE,  REMARKS, EMP_CD,  to_date(to_char(ENT_DT,'dd/mm/yyyy'),'dd/mm/yyyy'),  '',  '',  '',  '',  FLAG,  ''," +
						"  '',  '',  PRE_APPROVAL_DATE,  PRE_APPROVAL,  PRE_APPROVAL_BY,  FGSA_TYPE,  '0'," +
						"  '',  '',  '',  '',  '',  '' " +
						" ,'"+seq_no+"','','', " +
						"'','','','','F','','','','','','','','','','','','','' " + //RG20200116 added double quote for sequnece matching//
						" from FMS7_FGSA_MST " +
						"where CUSTOMER_CD='"+buyer_cd+"' and  FGSA_NO='"+fgsa_no+"' AND FLAG='Y' and rev_no='"+temp_rev_no+"'";
				System.out.println("Inserting Data..."+queryString1);
				stmt2.executeUpdate(queryString1);
				
				//Delete Records ... 
				queryString ="delete from FMS7_FGSA_MST where rev_no='"+temp_rev_no+"' and  CUSTOMER_CD='"+buyer_cd+"' and  FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				stmt.executeUpdate(queryString);
				
				String mapid = buyer_cd+"-"+fgsa_no+"-"+temp_rev_no;
				seq_no=1;
				queryString2="select count(*) from LOG_FMS8_LNG_REGAS_TRANS_DTL where " +
						"CONTRACT_TYPE='F' AND MAPPING_ID='"+mapid+"' ";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				
				queryString1="insert into LOG_FMS8_LNG_REGAS_TRANS_DTL select '"+mapid+"',TRANSPORTER_CD,  EMP_CD," +
				"  ENT_DT,  '',  '',  '',  '',  FLAG ,'"+seq_no+"','F' " +
				"from FMS7_FGSA_TRANSPORTER_MST " +
				"where rev_no='"+temp_rev_no+"' and  CUSTOMER_CD='"+buyer_cd+"' and  FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				stmt2.executeUpdate(queryString1);
				
				
				queryString ="delete from FMS7_FGSA_TRANSPORTER_MST  where rev_no='"+temp_rev_no+"' and  CUSTOMER_CD='"+buyer_cd+"' and  FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				stmt.executeUpdate(queryString)	;
				
				
				seq_no=1;
				queryString2="select count(*) from LOG_FMS8_LNG_REGAS_PLANT_DTL where " +
						"MAPPING_ID='"+mapid+"' AND CONTRACT_TYPE='F' ";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				queryString1="insert into LOG_FMS8_LNG_REGAS_PLANT_DTL select '"+mapid+"',  PLANT_SEQ_NO,  EMP_CD, ENT_DT,  FLAG " +
				",'"+seq_no+"','F' from FMS7_FGSA_PLANT_MST " +
				"where rev_no='"+temp_rev_no+"' and  CUSTOMER_CD='"+buyer_cd+"' and  FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				stmt2.executeUpdate(queryString1);
				
				queryString ="delete from FMS7_FGSA_PLANT_MST where rev_no='"+temp_rev_no+"' and  CUSTOMER_CD='"+buyer_cd+"' and  FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				stmt.executeUpdate(queryString)	;
				
				
				seq_no=1;
				queryString2="select count(*) from LOG_FMS8_LNG_REGAS_CLAUSE_MST where " +
						"MAPPING_ID='"+mapid+"' AND CONTRACT_TYPE='F' ";
				rset3=stmt3.executeQuery(queryString2);
				if(rset3.next())
				{
					String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
					seq_no=Integer.parseInt(seq_no1);
					seq_no++;
				}
				queryString1="insert into LOG_FMS8_LNG_REGAS_CLAUSE_MST select '"+mapid+"',CLAUSE_CD,FLAG,EMP_CD,ENT_DT " +
				",'"+seq_no+"','F' " +
				"from FMS7_FGSA_CLAUSE_MST " +
				"where rev_no='"+temp_rev_no+"' and  BUYER_CD ='"+buyer_cd+"' and  FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				stmt2.executeUpdate(queryString1);
				
				
				queryString ="delete from FMS7_FGSA_CLAUSE_MST where rev_no='"+temp_rev_no+"' and  BUYER_CD ='"+buyer_cd+"' and  FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				stmt.executeUpdate(queryString)	;
									
				//Insert Records ...
				query ="insert into FMS7_FGSA_MST (CUSTOMER_CD, FGSA_NO, SIGNING_DT, START_DT, END_DT, RENEWAL_DT, FGSA_BASE, FGSA_TYPE, STATUS, BUYER_NOM, " +
				"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
				"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, " +
				"MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
				"SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, FLAG, Rev_no, REV_DT, REMARKS, BUYER_NOM_CLAUSE, SELLER_NOM_CLAUSE, LC_CLAUSE, BILLING_CLAUSE, LIABILITY_CLAUSE" +
				//",ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG" +
				") " +
				"values('"+buyer_cd+"','"+fgsa_no+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy')," +
				"to_date('"+renewal_notice_dt+"','dd/mm/yyyy'),'"+agrBase+"','"+agrType+"','"+agrStatus+"','"+buyer_nom+"'," +
				"'"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"','"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM+"'," +
				"'"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"','"+MEASUREMENT+"','"+MEAS_STANDARD+"'," +
				"'"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"','"+SPEC_GAS_MIN_ENERGY+"'," +
				"'"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"', to_date('"+rev_dt+"','dd/mm/yyyy'),'"+remark+"','"+BUYER_NOM_CLAUSE+"', " +
				"'"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"'" +
				//",'"+advance_adjust_clause+"','"+advance_adjust+"'" +
				")";
				//System.out.println("Insert into FMS7_FGSA_MST Query = "+query);			
				stmt.executeUpdate(query);
				
				//Insert into FMS7_FGSA_TRANSPORTER_MST ...
				
				String[] chk_trans = request.getParameterValues("chk_trans");
				
				for(int i=0;i<chk_trans.length;i++)
				{
					//System.out.println("chk_trans ["+i+"] = "+chk_trans[i]);
					query ="insert into FMS7_FGSA_TRANSPORTER_MST (FGSA_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG,rev_no) values" +
					"('"+fgsa_no+"','"+buyer_cd+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
					//System.out.println("Insert into FGSA_TRANSPORTER_MST ("+i+") = "+query);	
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_FGSA_PLANT_MST ...
				
				String[] chk_delv = request.getParameterValues("chk_delv");
				
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_FGSA_PLANT_MST (FGSA_NO, CUSTOMER_CD, PLANT_SEQ_NO, EMP_CD, ENT_DT, FLAG,Rev_no) values" +
					"('"+fgsa_no+"','"+buyer_cd+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
					//System.out.println("Insert into FGSA_PLANT_MST ("+i+") = "+query);
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_FGSA_CLAUSE_MST ...
				
				String[] clause = request.getParameterValues("cls");
				
				if(clause!=null)
				{
					for(int i=0;i<clause.length;i++)
					{
						query ="insert into FMS7_FGSA_CLAUSE_MST (FGSA_NO, BUYER_CD, CLAUSE_CD,  EMP_CD, ENT_DT, FLAG,Rev_no) values" +
						"('"+fgsa_no+"','"+buyer_cd+"','"+clause[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
						//System.out.println("Insert into FGSA_CLAUSE_MST	("+i+") = "+query);
						stmt.executeUpdate(query);
					}
				}
				
				msg="FGSA Details Updated Successfully !!!";
				url="../contract_master/frm_FGSA_master.jsp?buyer_cd="+buyer_cd+"&bscode="+buyer_cd+"&fgsa_no="+fgsa_no+"&FGSA_CD="+fgsa_no+"&rev_no="+temp_rev_no+"&msg="+msg+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			else if(flg.equals("update") && renew_flag.equalsIgnoreCase("N") && rev_flag.equalsIgnoreCase("Y"))
			{
				int temp_rev_no = 0;
				int tmp_rev_no = 0;
								 
				queryString ="Select max(rev_no) from FMS7_FGSA_MST where CUSTOMER_CD='"+buyer_cd+"' and FGSA_NO='"+fgsa_no+"' and FLAG='Y'";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					temp_rev_no = rset.getInt(1);
					tmp_rev_no = rset.getInt(1);
				}
				
				temp_rev_no += 1;

		//		System.out.println("Value of rev no....."+temp_rev_no);

				///////////////////////////////ADDED BY RG 02/06/2014//////////////////
				
				//Insert Records ...
				query ="insert into FMS7_FGSA_MST (CUSTOMER_CD, FGSA_NO, SIGNING_DT, START_DT, END_DT, RENEWAL_DT, FGSA_BASE, FGSA_TYPE, STATUS, BUYER_NOM, " +
						"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
						"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, " +
						"MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
						"SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, FLAG, Rev_no, REV_DT, REMARKS, BUYER_NOM_CLAUSE, SELLER_NOM_CLAUSE, LC_CLAUSE, BILLING_CLAUSE, LIABILITY_CLAUSE" +
						//",ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG" +
						") " +
						"values('"+buyer_cd+"','"+fgsa_no+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy')," +
						"to_date('"+renewal_notice_dt+"','dd/mm/yyyy'),'"+agrBase+"','"+agrType+"','"+agrStatus+"','"+buyer_nom+"'," +
						"'"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"','"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM+"'," +
						"'"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"','"+MEASUREMENT+"','"+MEAS_STANDARD+"'," +
						"'"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"','"+SPEC_GAS_MIN_ENERGY+"'," +
						"'"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"', to_date('"+rev_dt+"','dd/mm/yyyy'),'"+remark+"','"+BUYER_NOM_CLAUSE+"', '"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"'" +
						//",'"+advance_adjust_clause+"','"+advance_adjust+"'" +
						")";
				
				//System.out.println("Insert into FMS7_FGSA_MST Query With New Revision NO = "+query);
				stmt.executeUpdate(query);
				
				//Fetching Previous Revision LD Details And Inserting Into New Revision LD Details 
				//(In Case Of New Revision LD Details Is Absent) ...
				query = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
						"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
						"REMARKS, FLAG FROM FMS7_FGSA_LD_DTL " +
						"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
						"FGSA_NO='"+fgsa_no+"' AND " +
						"FGSA_REV_NO='"+temp_rev_no+"' AND " +
						"CONT_TYPE='F'";
				
				//System.out.println("Liquidated Damages FGSA query = "+query);
				rset = stmt.executeQuery(query);
				
				if(rset.next())
				{
					
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
							"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
							"REMARKS, FLAG FROM FMS7_FGSA_LD_DTL " +
							"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
							"FGSA_NO='"+fgsa_no+"' AND " +
							"FGSA_REV_NO='"+tmp_rev_no+"' AND " +
							"CONT_TYPE='F'";
					
					//System.out.println("Liquidated Damages FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
			
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_FGSA_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
								 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+buyer_cd+", "+fgsa_no+", "+temp_rev_no+", 'F', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 "'"+rset2.getString(4)+"', '"+rset2.getString(5)+"', " +
								 "'"+rset2.getString(6)+"', '"+rset2.getString(7)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(8))+"', " +
								 "'"+rset2.getString(9)+"', '"+user_cd+"', sysdate)";
						stmt1.executeUpdate(query1);
					}					
				}
				
				//Fetching Previous Revision TOP Details And Inserting Into New Revision TOP Details 
				//(In Case Of New Revision TOP Details Is Absent) ...
				query = "SELECT PRICE_PER, PRICE, TOP_PER, " +
						"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
						"FLAG FROM FMS7_FGSA_TOP_DTL " +
						"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
						"FGSA_NO='"+fgsa_no+"' AND " +
						"FGSA_REV_NO='"+temp_rev_no+"' AND " +
						"CONT_TYPE='F'";
				
				//System.out.println("Take Or Pay FGSA query = "+query);
				rset = stmt.executeQuery(query);
				
				if(rset.next())
				{
					
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, TOP_PER, " +
							"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							"FLAG FROM FMS7_FGSA_TOP_DTL " +
							"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
							"FGSA_NO='"+fgsa_no+"' AND " +
							"FGSA_REV_NO='"+tmp_rev_no+"' AND " +
							"CONT_TYPE='F'";
					
					//System.out.println("Take Or Pay FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_FGSA_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
								 "FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+buyer_cd+", "+fgsa_no+", "+temp_rev_no+", 'F', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+rset2.getString(5)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(6))+"', " +
								 "'"+rset2.getString(7)+"', '"+user_cd+"', sysdate)";
				
						stmt1.executeUpdate(query1);
					}
				}
				
				//Fetching Previous Revision MakeUp Gas Details And Inserting Into New Revision MakeUp Gas Details 
				//(In Case Of New Revision MakeUp Gas Details Is Absent) ...
				query = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
						"REMARKS, FLAG FROM FMS7_FGSA_MAKEUPGAS_DTL " +
						"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
						"FGSA_NO='"+fgsa_no+"' AND " +
						"FGSA_REV_NO='"+temp_rev_no+"' AND " +
						"CONT_TYPE='F'";
				
				//System.out.println("MAKEUP GAS FGSA query = "+query);
				rset = stmt.executeQuery(query);
				
				if(rset.next())
				{
					
				}
				else
				{
					query2 = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
							"REMARKS, FLAG FROM FMS7_FGSA_MAKEUPGAS_DTL " +
							"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
							"FGSA_NO='"+fgsa_no+"' AND " +
							"FGSA_REV_NO='"+tmp_rev_no+"' AND " +
							"CONT_TYPE='F'";
			
					//System.out.println("MAKEUP GAS FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_FGSA_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
								 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+buyer_cd+", "+fgsa_no+", "+temp_rev_no+", 'F', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+obj.replaceSingleQuotes(rset2.getString(5))+"', " +
								 "'"+rset2.getString(6)+"', '"+user_cd+"', sysdate)";
				
						stmt1.executeUpdate(query1);
					}
				}
				
				//Fetching Previous Revision Billing Details And Inserting Into New Revision Billing Details 
				//(In Case Of New Revision Billing Details Is Absent) ...
				query = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
						"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
						"FLAG, EXCHG_RATE_NOTE FROM FMS7_FGSA_BILLING_DTL " +
						"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
						"FGSA_NO='"+fgsa_no+"' AND " +
						"FGSA_REV_NO='"+temp_rev_no+"' AND " +
						"CONT_TYPE='F'";
				
				//System.out.println("BILLING FGSA query = "+query);
				rset = stmt.executeQuery(query);
				
				if(rset.next())
				{
					
				}
				else
				{
					query2 = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"FLAG, EXCHG_RATE_NOTE FROM FMS7_FGSA_BILLING_DTL " +
							"WHERE CUSTOMER_CD='"+buyer_cd+"' AND " +
							"FGSA_NO='"+fgsa_no+"' AND " +
							"FGSA_REV_NO='"+tmp_rev_no+"' AND " +
							"CONT_TYPE='F'";
					
					//System.out.println("BILLING FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);
					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_FGSA_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, " +
								"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
								"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
								"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
								"VALUES("+buyer_cd+", "+fgsa_no+", "+temp_rev_no+", 'F', " +
								"'"+rset2.getString(1)+"', '"+rset2.getString(2)+"', "+rset2.getString(3)+", " +
								"'"+rset2.getString(4)+"', "+rset2.getString(5)+", "+rset2.getString(6)+", " +
								"'"+rset2.getString(7)+"', "+rset2.getString(8)+", "+rset2.getString(9)+", " +
								""+rset2.getString(10)+", '"+user_cd+"', sysdate, '"+rset2.getString(11)+"', " +
								"'"+obj.replaceSingleQuotes(rset2.getString(12))+"')";
				
						//System.out.println("FGSA Billing Details Insert Query = "+query1);
						stmt1.executeUpdate(query1);
					}
				}
		
				
				//Insert into FMS7_FGSA_TRANSPORTER_MST ...
				
				String[] chk_trans = request.getParameterValues("chk_trans");
				
				for(int i=0;i<chk_trans.length;i++)
				{
					//System.out.println("chk_trans ["+i+"] = "+chk_trans[i]);
					query ="insert into FMS7_FGSA_TRANSPORTER_MST (FGSA_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG,rev_no) values" +
					"('"+fgsa_no+"','"+buyer_cd+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
					////System.out.println("Insert into FGSA_TRANSPORTER_MST ("+i+") = "+query);	
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_FGSA_PLANT_MST ...
				
				String[] chk_delv = request.getParameterValues("chk_delv");
				
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_FGSA_PLANT_MST (FGSA_NO, CUSTOMER_CD, PLANT_SEQ_NO, EMP_CD, ENT_DT, FLAG,Rev_no) values" +
					"('"+fgsa_no+"','"+buyer_cd+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
					//System.out.println("Insert into FGSA_PLANT_MST ("+i+") = "+query);
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_FGSA_CLAUSE_MST ...				
				String[] clause = request.getParameterValues("cls");				
				if(clause!=null)
				{
					for(int i=0;i<clause.length;i++)
					{
						query ="insert into FMS7_FGSA_CLAUSE_MST (FGSA_NO, BUYER_CD, CLAUSE_CD,  EMP_CD, ENT_DT, FLAG,Rev_no) values" +
						"('"+fgsa_no+"','"+buyer_cd+"','"+clause[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
						//System.out.println("Insert into FGSA_CLAUSE_MST	("+i+") = "+query);
						stmt.executeUpdate(query);
					}
				}
				
				msg="FGSA Details Updated Successfully With New Revision NO !!!";
				url="../contract_master/frm_FGSA_master.jsp?buyer_cd="+buyer_cd+"&bscode="+buyer_cd+"&fgsa_no="+fgsa_no+"&FGSA_CD="+fgsa_no+"&rev_no="+temp_rev_no+"&msg="+msg+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			else
			{
				msg="FGSA Details Not Submitted !!!";
				url="../contract_master/frm_FGSA_master.jsp?buyer_cd="+buyer_cd+"&bscode="+buyer_cd+"&fgsa_no="+fgsa_no+"&FGSA_CD="+fgsa_no+"&msg="+msg+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			
			dbcon.commit();
			//System.out.println("URL   ==============>"+url);
			
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
			msg="FGSA Details Not Submitted !!!";
			e.printStackTrace();
			System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
			url="../contract_master/frm_FGSA_master.jsp?msg="+msg+"&flg=insert&activity=insert&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}//end of method FGSAAgreementMaster() ...
	
	
	public void FGSA_BillingEntry(HttpServletRequest request) throws Exception
	{
		methodName = "FGSA_BillingEntry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String PayCur = request.getParameter("payment_currency")==null?"":request.getParameter("payment_currency");
		String Freq = request.getParameter("freq_flag")==null?"F":request.getParameter("freq_flag");
		String rate = request.getParameter("rate")==null?"":request.getParameter("rate");
		String period = request.getParameter("period")==null?"":request.getParameter("period");
		String sign = request.getParameter("plusmin")==null?"0":request.getParameter("plusmin");
		String ExchngRef = request.getParameter("rbiref")==null?"":request.getParameter("rbiref");
		String ExchngCal = request.getParameter("exch_calc_base_flag")==null?"":request.getParameter("exch_calc_base_flag");
		String InvR1 = request.getParameter("inv_currency")==null?"":request.getParameter("inv_currency");
		String modeper = request.getParameter("modeper")==null?"":request.getParameter("modeper");
		String fgsa_cd= request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String TAX_STR_CD = request.getParameter("TAX_STR_CD")==null?"":request.getParameter("TAX_STR_CD");
	    String Start_Dt = request.getParameter("Start_Dt")==null?"":request.getParameter("Start_Dt");
		String exchg_rate_note = request.getParameter("exchg_rate_note")==null?"":request.getParameter("exchg_rate_note");	    
		exchg_rate_note = snglQuot.replaceSingleQuotes(exchg_rate_note); 
		String inv_criteria = request.getParameter("inv_criteria")==null?"":request.getParameter("inv_criteria");	
		String bill_end_dt_clause = request.getParameter("bill_end_dt_clause")==null?"Y":request.getParameter("bill_end_dt_clause");
		/*
		Remark: SB
		Billing Clause: 
		1) T : On Completion of TCQ
		2) B : On Billing Period
		3) Y : default value treated as no clause. 
		*/	
		try
		{		
			int seq_no=1;
			queryString="select count(*) from LOG_FMS8_CONTRACT_BILLING_DTL where " +
					"CONT_TYPE='F' AND FGSA_NO='"+fgsa_cd+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' "
					+ "AND CUSTOMER_CD='"+bscode+"' ";
			rset3=stmt3.executeQuery(queryString);
			if(rset3.next())
			{
				String seq_no1=rset3.getString(1)==null?"0":rset3.getString(1);
				seq_no=Integer.parseInt(seq_no1);
				seq_no++;
			}
			
			query = "INSERT INTO LOG_FMS8_CONTRACT_BILLING_DTL SELECT CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,'0','0',"
					+ "CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,"
					+ "EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD,EMP_CD,"
					+ "ENT_DT,FLAG,EXCHG_RATE_NOTE,INV_CRITERIA,'"+seq_no+"','' FROM FMS7_FGSA_BILLING_DTL WHERE "
					+ "FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' "
					+ "And CONT_TYPE='F' ";
			stmt.executeUpdate(query);
			
			query ="SELECT customer_cd FROM FMS7_FGSA_BILLING_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
			rset=stmt.executeQuery(query);	
			if(rset.next())
			{
				if(rset.getString(1)!=null)
				{
					if(!rset.getString(1).trim().equals(""))
					{
						query1 ="DELETE FROM FMS7_FGSA_BILLING_DTL WHERE FGSA_NO='"+fgsa_cd+"' And FGSA_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
						stmt1.executeUpdate(query1);
					}
				}
			}
			query ="insert into FMS7_FGSA_BILLING_DTL(CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD,EXCHG_RATE_NOTE,EMP_CD,ENT_DT,FLAG,inv_criteria) " +
					"values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','F','"+Freq+"','"+period+"','"+rate+"','"+sign+"','"+modeper+"','"+ExchngRef+"','"+ExchngCal+"','"+InvR1+"','"+PayCur+"','"+TAX_STR_CD+"','"+exchg_rate_note+"','"+user_cd+"',sysdate,'"+bill_end_dt_clause+"','"+inv_criteria+"') ";
			stmt.executeUpdate(query);			
			msg="FGSA-REVNO "+fgsa_cd+"-"+fgsa_rev_no+" UPDATED with BILLING PERIOD CLAUSE as "+bill_end_dt_clause+" ";	
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
			e.printStackTrace();
			msg="Billing Details Are not Inserted!!";	
		}
		url="../contract_master/frm_FGSA_bill_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&Start_Dt="+Start_Dt+"&TAX_STR_CD="+TAX_STR_CD+"&inv_criteria="+inv_criteria+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}//End of Billing of FGSA
	
	
	//==Changes done by Achal on 17-July-2010 and 17-August-2010
	public void RE_GAS_BillingEntry(HttpServletRequest request) throws Exception
	{
		methodName = "RE_GAS_BillingEntry()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String PayCur = request.getParameter("payment_currency")==null?"0":request.getParameter("payment_currency");
		String Freq = request.getParameter("freq_flag")==null?"":request.getParameter("freq_flag");
		String rate = request.getParameter("rate")==null?"0":request.getParameter("rate");
		String period = request.getParameter("period")==null?"":request.getParameter("period");
		String sign = request.getParameter("plusmin")==null?"":request.getParameter("plusmin");
		String ExchngRef = request.getParameter("rbiref")==null?"":request.getParameter("rbiref");
		String ExchngCal = request.getParameter("exch_calc_base_flag")==null?"":request.getParameter("exch_calc_base_flag");
		String InvR1 = request.getParameter("inv_currency")==null?"0":request.getParameter("inv_currency");
		String modeper = request.getParameter("modeper")==null?"0":request.getParameter("modeper");
		String fgsa_cd= request.getParameter("fgsa_cd")==null?"0":request.getParameter("fgsa_cd");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"0":request.getParameter("fgsa_rev_no");
		String bscode = request.getParameter("bscode")==null?"0":request.getParameter("bscode");
		String TAX_STR_CD = request.getParameter("TAX_STR_CD")==null?"0":request.getParameter("TAX_STR_CD");
	    String Start_Dt = request.getParameter("Start_Dt")==null?"":request.getParameter("Start_Dt");
		String exchg_rate_note = request.getParameter("exchg_rate_note")==null?"":request.getParameter("exchg_rate_note");	    
		
		String methodName="RE_GAS_BillingEntry()";
		exchg_rate_note = snglQuot.replaceSingleQuotes(exchg_rate_note); 
		String inv_criteria = request.getParameter("inv_criteria")==null?"":request.getParameter("inv_criteria");
		try
		{		
			//System.out.println("In try fgsa_rev_no = "+fgsa_rev_no);
			if(fgsa_rev_no.equals("") && fgsa_rev_no.equals(" ") && fgsa_rev_no==null && fgsa_rev_no.equals("  "))
			{
				fgsa_rev_no="0";
				query ="SELECT customer_cd,RE_GAS_REV_NO FROM FMS7_RE_GAS_BILLING_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";			
				rset=stmt.executeQuery(query);	
				if(rset.next())
				{				
					fgsa_rev_no = rset.getString(2)==null?"0":rset.getString(2);									
					query ="DELETE FROM FMS7_RE_GAS_BILLING_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";					
					stmt.executeUpdate(query);
				}
			}
			else
			{							
				query ="SELECT customer_cd,RE_GAS_REV_NO FROM FMS7_RE_GAS_BILLING_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";			
				rset=stmt.executeQuery(query);	
				//System.out.println("Else FMS7_RE_GAS_BILLING_DTL = "+query);
				if(rset.next())
				{				
					fgsa_rev_no = rset.getString(2)==null?"0":rset.getString(2);									
					//System.out.println("FGSA REV NO"+fgsa_rev_no);
					query ="DELETE FROM FMS7_RE_GAS_BILLING_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
					//System.out.println("Delete Query in if(next)"+query);
					stmt.executeUpdate(query);					
				}				
			}					
			query ="insert into FMS7_RE_GAS_BILLING_DTL(CUSTOMER_CD,RE_GAS_NO,RE_GAS_REV_NO,CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD,EXCHG_RATE_NOTE,EMP_CD,ENT_DT,FLAG,inv_criteria) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','F','"+Freq+"','"+period+"','"+rate+"','"+sign+"','"+modeper+"','"+ExchngRef+"','"+ExchngCal+"','"+InvR1+"','"+PayCur+"','"+TAX_STR_CD+"','"+exchg_rate_note+"','"+user_cd+"',sysdate,'Y','"+inv_criteria+"') ";					
			//System.out.println("queryyy"+query);
			stmt.executeUpdate(query);		
			msg="Billing Details Successfully Saved For Selected Re-Gas Contract !!!";	
			url="../contract_master/frm_Re_Gas_Bill_Clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&Start_Dt="+Start_Dt+"&TAX_STR_CD="+TAX_STR_CD+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
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
			dbcon.rollback();
			e.printStackTrace();
			msg="Re-Gas Billing Details Are not Inserted. Reason May be->Existance of Same Data.";	
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);			
			url="../contract_master/frm_Re_Gas_Bill_Clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&Start_Dt="+Start_Dt+"&TAX_STR_CD="+TAX_STR_CD+"&inv_criteria="+inv_criteria+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
		}			
	}//End of Re_Gas Billing Details
	
		
	//Data From frm_Re_Gas_Master.jsp
	public void Re_Gas_AgreementMaster(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		methodName = "Re_Gas_AgreementMaster()";
		form_name = "frm_Re_Gas_Master.jsp";
		
		//escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String methodName="Re_Gas_AgreementMaster()";		
		String[] sel_no = request.getParameterValues("chk_buyer_nom");		
		for(int i=0;i<sel_no.length;i++)
		{
			//System.out.println("Sel Nom		:"+sel_no[i]);  
		}
		
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_no = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
		String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");		
		String st_dt_storage = request.getParameter("st_dt_storage")==null?"":request.getParameter("st_dt_storage");
		String end_dt_storage = request.getParameter("end_dt_storage")==null?"":request.getParameter("end_dt_storage");
		
		String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
		String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String[] status = request.getParameterValues("status");
		String[] base = request.getParameterValues("agrtype");
		
		String contract_capacity =request.getParameter("contract_capacity")==null?"0":request.getParameter("contract_capacity");
		String contract_sent_rate =request.getParameter("contract_sent_rate")==null?"0":request.getParameter("contract_sent_rate");
		String sys_gas = request.getParameter("sys_gas")==null?"1":request.getParameter("sys_gas");
		String no_cargo = request.getParameter("no_cargo")==null?"":request.getParameter("no_cargo");
		String re_gas_tariff = request.getParameter("re_gas_tariff")==null?"1":request.getParameter("re_gas_tariff");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
		remark = snglQuot.replaceSingleQuotes(remark);
		
		String BUYER_NOM_CLAUSE = request.getParameter("buyer_nom_clause")==null?"":request.getParameter("buyer_nom_clause").trim();
		String SELLER_NOM_CLAUSE = request.getParameter("seller_nom_clause")==null?"":request.getParameter("seller_nom_clause").trim();
		String LC_CLAUSE = request.getParameter("lc_clause")==null?"":request.getParameter("lc_clause").trim();
		String BILLING_CLAUSE = request.getParameter("billing_clause")==null?"":request.getParameter("billing_clause").trim();
		String LIABILITY_CLAUSE = request.getParameter("liability_clause")==null?"":request.getParameter("liability_clause").trim();
		
		String advance_adjust_clause=request.getParameter("advance")==null?"N":request.getParameter("advance");
		String advance_adjust=request.getParameter("advance_adjust")==null?"N":request.getParameter("advance_adjust");
		
		if(contract_capacity.trim().equals(""))
		{
			contract_capacity = "0";
		}
		
		String agrBase="";		
		String agrStatus="";
		
		for(int i=0;i<base.length;i++)
		{
			//System.out.println("agrtype = "+agrtype[i]);
			agrBase =(base[i].equals("0")?"X":"D");
		}
		
		for(int i=0;i<status.length;i++)
		{
			//System.out.println("status = "+status[i]);
			agrStatus=(status[i].equals("0")?"Y":"N");
		}		
		String buyer_nom = request.getParameter("buyer_nom")==null?"":request.getParameter("buyer_nom");
		String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
		String BUYER_DAILY_NOM="N";
		String BUYER_MONTH_NOM="N";
		String BUYER_WEEK_NOM="N";		
		//System.out.println("Buyer Nom = "+buyer_nom);		
		
		for(int i=0;i<chk_buyer_nom.length;i++)
		{
			//System.out.println("chk_buyer_nom = "+chk_buyer_nom[i]);
			if(chk_buyer_nom[i].equals("D"))
			{
				BUYER_DAILY_NOM ="Y";
			}
			if(chk_buyer_nom[i].equals("W"))
			{
				BUYER_WEEK_NOM ="Y";
			}
			if(chk_buyer_nom[i].equals("M"))
			{
				BUYER_MONTH_NOM ="Y";
			}
		}		
		String seller_nom = request.getParameter("seller_nom")==null?"":request.getParameter("seller_nom");
		String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
		String SELLER_DAILY_NOM="N";
		String SELLER_WEEK_NOM="N";
		String SELLER_MONTH_NOM="N";
		
		//System.out.println("seller_nom Nom = "+seller_nom);				
		for(int i=0;i<chk_seller_nom.length;i++)
		{
			//System.out.println("chk_buyer_nom = "+chk_seller_nom[i]);
			if(chk_seller_nom[i].equals("D"))
			{
				SELLER_DAILY_NOM ="Y";
			}
			if(chk_seller_nom[i].equals("W"))
			{
				SELLER_WEEK_NOM ="Y";
			}
			if(chk_seller_nom[i].equals("M"))
			{
				SELLER_MONTH_NOM ="Y";
			}
		}
		
		String day_def = request.getParameter("day_def")==null?"":request.getParameter("day_def");
		//System.out.println("Day_def : "+day_def);
		String DAY_START_TIME =request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
		String DAY_END_TIME= request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");		
		String MDCQ_PERCENTAGE="";
		String MDCQ = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
		if(MDCQ.equals("Y"))
		{
			MDCQ_PERCENTAGE= request.getParameter("mdcq_percent");	
		}
		
		String MEASUREMENT = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
		String MEAS_STANDARD="";
		String MEAS_TEMPERATURE="";
		String PRESSURE_MIN_BAR="";
		String PRESSURE_MAX_BAR="";
		
		if(MEASUREMENT.equals("Y"))
		{
			MEAS_STANDARD = request.getParameter("standard")==null?"":request.getParameter("standard");
			MEAS_TEMPERATURE = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			PRESSURE_MIN_BAR = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			PRESSURE_MAX_BAR = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");
		}
		
		String OFF_SPEC_GAS =request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
		String SPEC_GAS_ENERGY_BASE="";
		String SPEC_GAS_MIN_ENERGY="";
		String SPEC_GAS_MAX_ENERGY="";
		
		if(OFF_SPEC_GAS.equals("Y"))
		{
			SPEC_GAS_ENERGY_BASE =request.getParameter("energy_off_spec")==null?"0":request.getParameter("energy_off_spec");
			SPEC_GAS_MIN_ENERGY =request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			SPEC_GAS_MAX_ENERGY =request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
		}			
		String rev_flag = request.getParameter("rev_flag")==null?"N":request.getParameter("rev_flag");		
		String rev_dt = request.getParameter("rev_dt")==null?"":request.getParameter("rev_dt");
		
		int tempFGSACode=1;
		query="select nvl(max(RE_GAS_NO),'0') from FMS7_RE_GAS_MST where CUSTOMER_CD='"+buyer_cd+"'";	
		rset = stmt.executeQuery(query);
		if(rset.next())
		{
			tempFGSACode = rset.getInt(1);
		}
		else
		{
			tempFGSACode = 0;
		}	
		try
		{			
			if(flg.equals("insert"))
			{
				tempFGSACode += 1;			
				//Insert into FMS7_RE_GAS_MST ...			
				/*
				//MD20120302
				query ="insert into FMS7_RE_GAS_MST(CUSTOMER_CD,RE_GAS_NO,SIGNING_DT, START_DT, END_DT, STORAGE_FROM_DT, STORAGE_TO_DT, RE_GAS_BASE, STATUS, BUYER_NOM, " +
				"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
				"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, " +
				"MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
				"SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, FLAG, Rev_no, REV_DT ,CAPACITY, SEND_OUT_RATE, SYS_USE_GAS, NO_OF_CARGO, RE_GAS_TARIF, REMARK, " +
				"BUYER_NOM_CLAUSE, SELLER_NOM_CLAUSE, LIABILITY_CLAUSE, BILLING_CLAUSE, LC_CLAUSE,) " +				
				"values('"+buyer_cd+"','"+tempFGSACode+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy')," +			    
				"to_date('"+st_dt_storage+"','dd/mm/yyyy'),to_date('"+end_dt_storage+"','dd/mm/yyyy'),'"+agrBase+"','"+agrStatus+"','"+buyer_nom+"'," +				
				"'"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"','"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM+"'," +
				"'"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"','"+MEASUREMENT+"','"+MEAS_STANDARD+"'," +				
				"'"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"','"+SPEC_GAS_MIN_ENERGY+"'," +
				"'"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y','0', to_date('"+rev_dt+"','dd/mm/yyyy'),"+contract_capacity+", " +
				"'"+contract_sent_rate+"','"+sys_gas+"','"+no_cargo+"','"+re_gas_tariff+"','"+remark+"'," +
				"'"+BUYER_NOM_CLAUSE+"', '"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"')";

				*/

				query ="insert into FMS7_RE_GAS_MST(CUSTOMER_CD,RE_GAS_NO,SIGNING_DT, START_DT, END_DT, STORAGE_FROM_DT, STORAGE_TO_DT, RE_GAS_BASE, STATUS, BUYER_NOM, " +
				"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
				"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, " +
				"MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
				"SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, FLAG, Rev_no, REV_DT ,CAPACITY, SEND_OUT_RATE, SYS_USE_GAS, NO_OF_CARGO, RE_GAS_TARIF, REMARK, " +
				"BUYER_NOM_CLAUSE, SELLER_NOM_CLAUSE, LIABILITY_CLAUSE, BILLING_CLAUSE, LC_CLAUSE,ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG) " +	//MD20120302			
				"values('"+buyer_cd+"','"+tempFGSACode+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy')," +			    
				"to_date('"+st_dt_storage+"','dd/mm/yyyy'),to_date('"+end_dt_storage+"','dd/mm/yyyy'),'"+agrBase+"','"+agrStatus+"','"+buyer_nom+"'," +				
				"'"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"','"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM+"'," +
				"'"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"','"+MEASUREMENT+"','"+MEAS_STANDARD+"'," +				
				"'"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"','"+SPEC_GAS_MIN_ENERGY+"'," +
				"'"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y','0', to_date('"+rev_dt+"','dd/mm/yyyy'),"+contract_capacity+", " +
				"'"+contract_sent_rate+"','"+sys_gas+"','"+no_cargo+"','"+re_gas_tariff+"','"+remark+"'," +
				"'"+BUYER_NOM_CLAUSE+"', '"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"','"+advance_adjust_clause+"','"+advance_adjust+"')";


				//System.out.println("Insert into FMS7_RE_GAS_MST Query = "+query);			
				stmt.executeUpdate(query);							
				//Insert into FMS7_FGSA_TRANSPORTER_MST ...				
				String[] chk_trans = request.getParameterValues("chk_trans");				
				for(int i=0;i<chk_trans.length;i++)
				{
					//System.out.println("chk_trans ["+i+"] = "+chk_trans[i]);
					query ="insert into FMS7_RE_GAS_TRANSPORTER_MST (RE_GAS_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG,rev_no) values" +	
						   "('"+tempFGSACode+"','"+buyer_cd+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'Y','0')";
					//System.out.println("Insert into RE_GAS_TRANSPORTER_MST ("+i+") = "+query);	
					stmt.executeUpdate(query);
				}				
				//Insert into FMS7_RE_GAS_PLANT_MST				
				String[] chk_delv = request.getParameterValues("chk_delv");				
				for(int i=0;i<chk_delv.length;i++)
				{
					//System.out.println("chk_delv ["+i+"] = "+chk_delv[i]);
					query ="insert into FMS7_RE_GAS_PLANT_MST (RE_GAS_NO, CUSTOMER_CD, PLANT_SEQ_NO, EMP_CD, ENT_DT, FLAG,Rev_no) values" +	
						   "('"+tempFGSACode+"','"+buyer_cd+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'Y','0')";
					//System.out.println("Insert into FGSA_PLANT_MST ("+i+") = "+query);
					stmt.executeUpdate(query);
				}				
				//Insert into FMS7_RE_GAS_CLAUSE_MST 				
				String[] clause = request.getParameterValues("cls");				
				if(clause!=null)
				{
					for(int i=0;i<clause.length;i++)
					{
						//System.out.println("Clause ["+i+"] = "+clause[i]);
						query ="insert into FMS7_RE_GAS_CLAUSE_MST (RE_GAS_NO, BUYER_CD, CLAUSE_CD, EMP_CD, ENT_DT, FLAG,Rev_no) values" +						
						       "('"+tempFGSACode+"','"+buyer_cd+"','"+clause[i]+"','"+user_cd+"',sysdate,'Y','0')";
						//System.out.println("Insert into FGSA_CLAUSE_MST	("+i+") = "+query);
						stmt.executeUpdate(query);
					}
				}			
				msg="RE_GAS Details Inserted Successfully !!!";
				url="../contract_master/frm_Re_Gas_Master.jsp?buyer_cd="+buyer_cd+"&bscode="+buyer_cd+"&fgsa_no="+tempFGSACode+"&FGSA_CD="+tempFGSACode+"&msg="+msg+"&rev_no=0&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			else if(flg.equals("update") && rev_flag.equalsIgnoreCase("N"))
			{				
				int temp_rev_no = 0;								
				queryString ="Select max(rev_no) from FMS7_RE_GAS_MST where CUSTOMER_CD='"+buyer_cd+"' and RE_GAS_NO='"+fgsa_no+"'";
				//System.out.println("fetch Max Rev No : "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					temp_rev_no  = rset.getInt(1);
					//tempFGSACode = rset.getInt(2);
					//buyer_cd     = rset.getString(3)==null?"0":rset.getString(3);
				}	
				else
				{
					temp_rev_no = 0;
				}				
				//System.out.println("tempFGSACode : "+tempFGSACode);
				//System.out.println("Re_Gas_no : "+fgsa_no);
				//System.out.println("Rev No : "+temp_rev_no);
				//System.out.println("buyer_cd : "+buyer_cd);
				//Delete Records ... 
				queryString ="delete from FMS7_RE_GAS_MST where rev_no='"+temp_rev_no+"' and  CUSTOMER_CD='"+buyer_cd+"' and  RE_GAS_NO='"+fgsa_no+"'";					
				stmt.executeUpdate(queryString)	;
				//System.out.println("Delete FMS7_RE_GAS_MST Query (1) = "+queryString);
				queryString ="delete  from FMS7_RE_GAS_TRANSPORTER_MST where rev_no='"+temp_rev_no+"' and  CUSTOMER_CD='"+buyer_cd+"' and  RE_GAS_NO='"+fgsa_no+"'";				
				stmt.executeUpdate(queryString)	;
				//System.out.println("Delete FMS7_RE_GAS_TRANSPORTER_MST Query (2) = "+queryString);
				queryString ="delete  from FMS7_RE_GAS_PLANT_MST where rev_no='"+temp_rev_no+"' and  CUSTOMER_CD='"+buyer_cd+"' and  RE_GAS_NO='"+fgsa_no+"'";				
				stmt.executeUpdate(queryString)	;
				//System.out.println("Delete FMS7_RE_GAS_PLANT_MST Query (3) = "+queryString);
				queryString ="delete  from FMS7_RE_GAS_CLAUSE_MST where rev_no='"+temp_rev_no+"' and  BUYER_CD ='"+buyer_cd+"' and  RE_GAS_NO='"+fgsa_no+"'";				
				stmt.executeUpdate(queryString)	;
				//System.out.println("Delete FMS7_RE_GAS_CLAUSE_MST Query (4) = "+queryString);
									
				//Insert Records ...
				query ="insert into FMS7_RE_GAS_MST(CUSTOMER_CD,RE_GAS_NO,SIGNING_DT, START_DT, END_DT, STORAGE_FROM_DT, STORAGE_TO_DT, RE_GAS_BASE, STATUS, BUYER_NOM, " +
				"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
				"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, " +
				"MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
				"SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, FLAG, Rev_no, REV_DT ,CAPACITY, SEND_OUT_RATE, SYS_USE_GAS, NO_OF_CARGO, RE_GAS_TARIF, REMARK, " +
				"BUYER_NOM_CLAUSE, SELLER_NOM_CLAUSE, LIABILITY_CLAUSE, BILLING_CLAUSE, LC_CLAUSE,ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG) " +				
				"values('"+buyer_cd+"','"+fgsa_no+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy')," +			    
				"to_date('"+st_dt_storage+"','dd/mm/yyyy'),to_date('"+end_dt_storage+"','dd/mm/yyyy'),'"+agrBase+"','"+agrStatus+"','"+buyer_nom+"'," +				
				"'"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"','"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM
				+"'," +"'"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"','"+MEASUREMENT+"','"+MEAS_STANDARD+"'," +				
				"'"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"','"+SPEC_GAS_MIN_ENERGY+"'," +
				"'"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y',"+temp_rev_no+", to_date('"+rev_dt+"','dd/mm/yyyy'),'"+contract_capacity+"'," +
				"'"+contract_sent_rate+"','"+sys_gas+"','"+no_cargo+"','"+re_gas_tariff+"','"+remark+"'," +
				"'"+BUYER_NOM_CLAUSE+"', '"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"','"+advance_adjust_clause+"','"+advance_adjust+"')";
				//System.out.println("Insert into FMS7_RE_GAS_MST Query = "+query);			
				stmt.executeUpdate(query);
				
				//Insert into FMS7_FGSA_TRANSPORTER_MST ...				
				String[] chk_trans = request.getParameterValues("chk_trans");				
				for(int i=0;i<chk_trans.length;i++)
				{
					//System.out.println("chk_trans ["+i+"] = "+chk_trans[i]);
					query ="insert into FMS7_RE_GAS_TRANSPORTER_MST (RE_GAS_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG,rev_no) values" +					
					"('"+fgsa_no+"','"+buyer_cd+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
					//System.out.println("Insert into FGSA_TRANSPORTER_MST ("+i+") = "+query);	
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_RE_GAS_PLANT_MST					
				String[] chk_delv = request.getParameterValues("chk_delv");				
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_RE_GAS_PLANT_MST (RE_GAS_NO, CUSTOMER_CD, PLANT_SEQ_NO, EMP_CD, ENT_DT, FLAG,Rev_no) values" +					
					"('"+fgsa_no+"','"+buyer_cd+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
					//System.out.println("Insert into FGSA_PLANT_MST ("+i+") = "+query);
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_RE_GAS_CLAUSE_MST...				
				String[] clause = request.getParameterValues("cls");				
				if(clause!=null)
				{
					for(int i=0;i<clause.length;i++)
					{
						query ="insert into FMS7_RE_GAS_CLAUSE_MST (RE_GAS_NO, BUYER_CD, CLAUSE_CD,  EMP_CD, ENT_DT, FLAG,Rev_no) values" +						
							   "('"+fgsa_no+"','"+buyer_cd+"','"+clause[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
						//System.out.println("Insert into FGSA_CLAUSE_MST	("+i+") = "+query);
						stmt.executeUpdate(query);
					}
				}				
				msg="Re-Gas Details Updated Successfully !!!";				
				url="../contract_master/frm_Re_Gas_Master.jsp?buyer_cd="+buyer_cd+"&bscode="+buyer_cd+"&fgsa_no="+fgsa_no+"&FGSA_CD="+fgsa_no+"&msg="+msg+"&rev_no="+temp_rev_no+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;						
			}
			else if(flg.equals("update") && rev_flag.equalsIgnoreCase("Y"))
			{
				//String temp_renew_dt = "0";
				int temp_rev_no = 0;
				//int count=0;								
				queryString ="Select max(rev_no) from FMS7_RE_GAS_MST where CUSTOMER_CD='"+buyer_cd+"' and RE_GAS_NO='"+fgsa_no+"'";
				//System.out.println("fetch Max Rev No : "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					temp_rev_no = rset.getInt(1);
				}				
				temp_rev_no += 1;

				//Insert Records ...
				query ="insert into FMS7_RE_GAS_MST (CUSTOMER_CD, RE_GAS_NO, SIGNING_DT, START_DT, END_DT,STORAGE_FROM_DT, STORAGE_TO_DT , RE_GAS_BASE, STATUS, BUYER_NOM, " +
				"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
				"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, " +
				"MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
				"SPEC_GAS_MAX_ENERGY, EMP_CD, ENT_DT, FLAG, Rev_no, REV_DT,CAPACITY, SEND_OUT_RATE, SYS_USE_GAS, NO_OF_CARGO, RE_GAS_TARIF, REMARK, " +
				"BUYER_NOM_CLAUSE, SELLER_NOM_CLAUSE, LIABILITY_CLAUSE, BILLING_CLAUSE, LC_CLAUSE,ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG) " +				
				"values('"+buyer_cd+"','"+fgsa_no+"',to_date('"+sign_dt+"','dd/mm/yyyy'),to_date('"+st_dt+"','dd/mm/yyyy'),to_date('"+end_dt+"','dd/mm/yyyy')," +				
				"to_date('"+st_dt_storage+"','dd/mm/yyyy'),to_date('"+end_dt_storage+"','dd/mm/yyyy'),'"+agrBase+"','"+agrStatus+"','"+buyer_nom+"'," +
				"'"+BUYER_MONTH_NOM+"','"+BUYER_WEEK_NOM+"','"+BUYER_DAILY_NOM+"','"+seller_nom+"','"+SELLER_MONTH_NOM+"','"+SELLER_WEEK_NOM+"'," +
				"'"+SELLER_DAILY_NOM+"','"+day_def+"','"+DAY_START_TIME+"','"+DAY_END_TIME+"','"+MDCQ+"','"+MDCQ_PERCENTAGE+"','"+MEASUREMENT+"','"+MEAS_STANDARD+"'," +
				"'"+MEAS_TEMPERATURE+"','"+PRESSURE_MIN_BAR+"','"+PRESSURE_MAX_BAR+"','"+OFF_SPEC_GAS+"','"+SPEC_GAS_ENERGY_BASE+"','"+SPEC_GAS_MIN_ENERGY+"'," +
				"'"+SPEC_GAS_MAX_ENERGY+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"',to_date('"+rev_dt+"','dd/mm/yyyy'),'"+contract_capacity+"'," +
				"'"+contract_sent_rate+"','"+sys_gas+"','"+no_cargo+"','"+re_gas_tariff+"','"+remark+"'," +
				"'"+BUYER_NOM_CLAUSE+"', '"+SELLER_NOM_CLAUSE+"', '"+LC_CLAUSE+"', '"+BILLING_CLAUSE+"', '"+LIABILITY_CLAUSE+"','"+advance_adjust_clause+"','"+advance_adjust+"')";
				//System.out.println("Insert into FMS7_RE_GAS_MST Query = "+query);			
				stmt.executeUpdate(query);
				
				//Insert into FMS7_RE_GAS_TRANSPORTER_MST				
				String[] chk_trans = request.getParameterValues("chk_trans");				
				for(int i=0;i<chk_trans.length;i++)
				{
					//System.out.println("chk_trans ["+i+"] = "+chk_trans[i]);
					query ="insert into FMS7_RE_GAS_TRANSPORTER_MST (RE_GAS_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG,rev_no) values" +
						   "('"+fgsa_no+"','"+buyer_cd+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
					//System.out.println("Insert into FGSA_TRANSPORTER_MST ("+i+") = "+query);	
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_RE_GAS_PLANT_MST					
				String[] chk_delv = request.getParameterValues("chk_delv");				
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_RE_GAS_PLANT_MST (RE_GAS_NO, CUSTOMER_CD, PLANT_SEQ_NO, EMP_CD,ENT_DT, FLAG,Rev_no) values" +					
					       "('"+fgsa_no+"','"+buyer_cd+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
					//System.out.println("Insert into FGSA_PLANT_MST ("+i+") = "+query);
					stmt.executeUpdate(query);
				}
				
				//Insert into FMS7_RE_GAS_CLAUSE_MST...				
				String[] clause = request.getParameterValues("cls");				
				if(clause!=null)
				{
					for(int i=0;i<clause.length;i++)
					{
						query ="insert into FMS7_RE_GAS_CLAUSE_MST (RE_GAS_NO, BUYER_CD, CLAUSE_CD,EMP_CD, ENT_DT, FLAG,Rev_no) values" +						
							   "('"+fgsa_no+"','"+buyer_cd+"','"+clause[i]+"','"+user_cd+"',sysdate,'Y','"+temp_rev_no+"')";
						//System.out.println("Insert into RE_GAS_CLAUSE_MST	("+i+") = "+query);
						stmt.executeUpdate(query);
					}
				}				
				msg="Re-Gas Details Updated Successfully With New Revision NO !!!";				
				url="../contract_master/frm_Re_Gas_Master.jsp?buyer_cd="+buyer_cd+"&bscode="+buyer_cd+"&fgsa_no="+fgsa_no+"&FGSA_CD="+fgsa_no+"&msg="+msg+"&rev_no="+temp_rev_no+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}
			else
			{
				msg="Re-Gas Details Not Submitted !!!";				
				url="../contract_master/frm_Re_Gas_Master.jsp?buyer_cd="+buyer_cd+"&bscode="+buyer_cd+"&fgsa_no="+fgsa_no+"&FGSA_CD="+fgsa_no+"&msg="+msg+"&flg=update&activity=update&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			}			
			dbcon.commit();
			//System.out.println("URL   ======>"+url);			
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
			msg="Re-Gas Details Not Submitted !!!";
			e.printStackTrace();
			System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
			url="../contract_master/frm_Re_Gas_Master.jsp?msg="+msg+"&flg=insert&activity=insert&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;			
		}
	}//end of method Re_GasAgreementMaster() ...
	
	
	public void RE_GAS_LD_Liablility_Entry(HttpServletRequest request) throws Exception
	{		
		methodName = "RE_GAS_LD_Liablility_Entry()";
		HttpSession session = request.getSession();
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage1")==null?"":request.getParameter("percentage1");
		String price1 = request.getParameter("price1")==null?"":request.getParameter("price1");
		String promiseOn1 = request.getParameter("promiseOn1")==null?"":request.getParameter("promiseOn1");
		String low_percentage = request.getParameter("low_percentage")==null?"":request.getParameter("low_percentage");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
		String DCQ_FLG = request.getParameter("DCQ_FLG")==null?"":request.getParameter("DCQ_FLG");
		String PNDCQ_FLG = request.getParameter("PNDCQ_FLG")==null?"":request.getParameter("PNDCQ_FLG");
		String MDCQ_FLG = request.getParameter("MDCQ_FLG")==null?"":request.getParameter("MDCQ_FLG");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String ld_flg = request.getParameter("ld_flg")==null?"":request.getParameter("ld_flg");		
		String methodName="RE_GAS_LD_Liablility_Entry()";
		
		String rem = obj.replaceSingleQuotes(remark);					
		/*System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+low_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+DCQ_FLG);
		System.out.println("PNDCQ_FLG"+PNDCQ_FLG);
		System.out.println("MDCQ_FLG "+MDCQ_FLG);	
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/
		try
		{			
			if(ld_flg.equals("Y") || ld.equals("Y"))
			{				
				query ="SELECT customer_cd FROM FMS7_RE_GAS_LD_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset=stmt.executeQuery(query);
				//System.out.println("customer_cd = "+query);			
				if(rset.next())
				{
					query ="DELETE FROM FMS7_RE_GAS_LD_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F' ";
					stmt.executeUpdate(query);
					//System.out.println("queryyyyyyyyyyyyyyyyyyyyyyyy"+query);
				}	
			    query ="insert into FMS7_RE_GAS_LD_DTL(CUSTOMER_CD, RE_GAS_NO, RE_GAS_REV_NO, CONT_TYPE, PRICE_PER, PRICE, PROMISE_QTY_FREQ, LIABILITY_PER, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','F','"+percentage1+"','"+price1+"','"+promiseOn1+"','"+low_percentage+"','"+DCQ_FLG+"','"+PNDCQ_FLG+"','"+MDCQ_FLG+"','"+rem+"','"+user_cd+"',sysdate,'Y') ";
			    //System.out.println("fgsa_rev_no"+fgsa_rev_no);
				//System.out.println("Insert query"+query);
				stmt.executeUpdate(query);				
				dbcon.commit();
				
				msg="Liability Details Successfully Saved For The Selected Re-Gas Contract";
				url="../contract_master/frm_Re_Gas_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
								
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details Are not Inserted.Reason May be->Existance of Same Data.";
			url="../contract_master/frm_Re_Gas_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);			
		}			
	}//End of Re_Gas_Liability of LD
	
	
	public void RE_GAS_TOP_Liablility_Entry(HttpServletRequest request) throws Exception
	{		
		methodName = "RE_GAS_TOP_Liablility_Entry()";
		HttpSession session = request.getSession();
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage2")==null?"":request.getParameter("percentage2");
		String price1 = request.getParameter("price2")==null?"":request.getParameter("price2");
		String promiseOn1 = request.getParameter("promiseOn2")==null?"":request.getParameter("promiseOn2");
		String remark = request.getParameter("remark2")==null?"":request.getParameter("remark2");
		String TOP_percentage = request.getParameter("top_percentage")==null?"":request.getParameter("top_percentage");
		String obligation = request.getParameter("obligation")==null?"":request.getParameter("obligation");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String top_flg = request.getParameter("top_flg")==null?"":request.getParameter("top_flg");
		//System.out.println("top_flg"+top_flg);		
		String methodName="RE_GAS_TOP_Liablility_Entry()";
		
		String rem = obj.replaceSingleQuotes(remark);	
		/*System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("promiseOn1"+promiseOn1);
		System.out.println("low_percentage"+TOP_percentage);
		System.out.println("remark"+remark);
		System.out.println("DCQ_FLG"+obligation);
		//System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/
		try
		{
			if(top_flg.equals("Y") || top.equals("Y"))
			{				
				//System.out.println("Checkpoint2");
				query ="SELECT customer_cd FROM FMS7_RE_GAS_TOP_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset=stmt.executeQuery(query);
				//System.out.println("query"+query);			
				if(rset.next())
				{
					query ="DELETE FROM FMS7_RE_GAS_TOP_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F' ";
					stmt.executeUpdate(query);
					//System.out.println("query"+query);
				}	
			    query ="insert into FMS7_RE_GAS_TOP_DTL(CUSTOMER_CD, RE_GAS_NO, RE_GAS_REV_NO, CONT_TYPE, PRICE_PER, PRICE, TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','F','"+percentage1+"','"+price1+"','"+TOP_percentage+"','"+obligation+"','"+promiseOn1+"','"+rem+"','"+user_cd+"',sysdate,'Y') ";
			    //System.out.println("fgsa_rev_no"+fgsa_rev_no);
			    //System.out.println("query"+query);
				stmt.executeUpdate(query);
				dbcon.commit();
				
				msg="Take or Pay Details Successfully Saved For The Selected Re-Gas Contract !!!";
				url="../contract_master/frm_Re_Gas_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liablity Details Are not Inserted.Reason May be->Existance of Same Data.";
			url="../contract_master/frm_Re_Gas_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);			
		}
	}//End of Re_Gas Top
	
		
	public void RE_GAS_MUG_Liablility_Entry(HttpServletRequest request) throws Exception
	{		
		methodName = "RE_GAS_MUG_Liablility_Entry()";
		HttpSession session = request.getSession();
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		String fgsa_cd = request.getParameter("FGSA_NO")==null?"":request.getParameter("FGSA_NO");
		String fgsa_rev_no = request.getParameter("FGSA_REVNo")==null?"":request.getParameter("FGSA_REVNo");
		String bscode = request.getParameter("bscode")==null?"":request.getParameter("bscode");
		String percentage1 = request.getParameter("percentage3")==null?"":request.getParameter("percentage3");
		String price1 = request.getParameter("price3")==null?"":request.getParameter("price3");
		String rec_percentage = request.getParameter("rec_percentage")==null?"":request.getParameter("rec_percentage");
		String remark = request.getParameter("remark3")==null?"":request.getParameter("remark3");
		String mug_percentage = request.getParameter("mug_percentage")==null?"":request.getParameter("mug_percentage");
		String ld = request.getParameter("ld")==null?"":request.getParameter("ld");
		String top = request.getParameter("top")==null?"":request.getParameter("top");
		String mug = request.getParameter("mug")==null?"":request.getParameter("mug");
		String mug_flg = request.getParameter("mug_flg")==null?"":request.getParameter("mug_flg");
		
		String methodName="RE_GAS_MUG_Liablility_Entry()";
			
		String rem = obj.replaceSingleQuotes(remark);
		/*System.out.println("percentage1"+percentage1);
		System.out.println("price1"+price1);		
		System.out.println("rec_percentage"+rec_percentage);
		System.out.println("mug_percentage"+mug_percentage);
		System.out.println("remark"+remark);		
		//	System.out.println("Echange cal"+ExchngCal);
		System.out.println("FGSA NO"+fgsa_cd);
		System.out.println("FGSA REV NO"+fgsa_rev_no);
		System.out.println("BSCODE"+bscode);	
		System.out.println("user_cd"+user_cd);*/
		try
		{
			if(mug_flg.equals("Y") || mug.equals("Y"))
			{
				query ="SELECT customer_cd FROM FMS7_RE_GAS_MAKEUPGAS_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset=stmt.executeQuery(query);
				//System.out.println("query"+query);					
				if(rset.next())
				{
					query ="DELETE FROM FMS7_RE_GAS_MAKEUPGAS_DTL WHERE RE_GAS_NO='"+fgsa_cd+"' And RE_GAS_REV_NO='"+fgsa_rev_no+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F' ";
					stmt.executeUpdate(query);
					//System.out.println("query"+query);
				}	
				query ="insert into FMS7_RE_GAS_MAKEUPGAS_DTL(CUSTOMER_CD, RE_GAS_NO, RE_GAS_REV_NO, CONT_TYPE, MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, EMP_CD, ENT_DT, FLAG) values('"+bscode+"','"+fgsa_cd+"','"+fgsa_rev_no+"','F','"+mug_percentage+"','"+rec_percentage+"','"+percentage1+"','"+price1+"','"+rem+"','"+user_cd+"',sysdate,'Y')";
				//System.out.println("fgsa_rev_no"+fgsa_rev_no);
				//System.out.println("query"+query);
				stmt.executeUpdate(query);				
				dbcon.commit();
				
				msg="Make Up Gas Details Successfully Saved For The Selected Re-Gas Contract !!!";	
				url="../contract_master/frm_Re_Gas_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
				
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
		}
		catch(Exception e)
		{
			dbcon.rollback();
			e.printStackTrace();
			msg="Liability Details Are not Inserted.Reason May be->Existance of Same Data.";
			url="../contract_master/frm_Re_Gas_liability_clause.jsp?msg="+msg+"&flg=update&activity=update&bscode="+bscode+"&FGSA_No="+fgsa_cd+"&Rev_No="+fgsa_rev_no+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
			//System.out.println("Exception in the "+methodName+" of "+servletName+" "+e);
		}		
	}// End of Re_gas Liability of MUG
	
	
	public void insert_modify_LC_MASTER_DETAILS_ENTRY(HttpServletRequest request) throws Exception
	{
		methodName = "insert_modify_LC_MASTER_DETAILS_ENTRY()";
		String methodName = "insert_modify_LC_MASTER_DETAILS_ENTRY()";
		
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		
		String fgsa_no = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"0":request.getParameter("fgsa_rev_no");
		String sn_no = request.getParameter("sn_no")==null?"0":request.getParameter("sn_no");
		String sn_rev_no = request.getParameter("sn_rev_no")==null?"0":request.getParameter("sn_rev_no");
		String customer_cd = request.getParameter("customer_cd")==null?"0":request.getParameter("customer_cd");
		String customer_nm = request.getParameter("customer_nm")==null?"":request.getParameter("customer_nm");
		String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
		String cont_type = request.getParameter("cont_type")==null?"F":request.getParameter("cont_type");
		
		String exchg_rate = request.getParameter("exchg_rate")==null?"":request.getParameter("exchg_rate");
		String flag_lc_value = request.getParameter("flag_lc_value")==null?"":request.getParameter("flag_lc_value");
		String flag_dcq_tcq = request.getParameter("flag_dcq_tcq")==null?"":request.getParameter("flag_dcq_tcq");
		String dcqdays_tcqpercent_value = request.getParameter("dcqdays_tcqpercent_value")==null?"":request.getParameter("dcqdays_tcqpercent_value");
		String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");
		
		String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
		String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
		String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
		String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
		String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
		
		remarks = obj.replaceSingleQuotes(remarks);
		
		try
		{
			int count = 0;
			
			if(cont_type.equalsIgnoreCase("F") || cont_type.equalsIgnoreCase("T"))
			{
				queryString = "select COUNT(*) from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+fgsa_no+" " +
							  "and FGSA_TENDER_REV_NO="+fgsa_rev_no+" and CUSTOMER_CD="+customer_cd+" and " +
							  "CONT_TYPE='"+cont_type+"'";
				//System.out.println("Select Query for FGSA/Tender of FMS7_FGSA_TENDER_LC_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);		
				if(rset.next())
				{
					count = rset.getInt(1);
				}
				
				if(count>0)
				{
					queryString = "delete from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+fgsa_no+" " +
								  "and FGSA_TENDER_REV_NO="+fgsa_rev_no+" and CUSTOMER_CD="+customer_cd+" and " +
								  "CONT_TYPE='"+cont_type+"'";
					//System.out.println("Delete Query for FGSA/Tender of FMS7_FGSA_TENDER_LC_DTL = "+queryString);
					stmt.executeUpdate(queryString);
				}
				
				queryString = "insert into FMS7_FGSA_TENDER_LC_DTL (FGSA_TENDER_NO, FGSA_TENDER_REV_NO, " +
							  "CUSTOMER_CD, CONT_TYPE, EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, " +
							  "DCQ_DAYS_OR_TCQ_PERCENT, REMARKS, EMP_CD, ENT_DT, FLAG) " +
							  "values("+fgsa_no+","+fgsa_rev_no+","+customer_cd+",'"+cont_type+"'," +
							  ""+exchg_rate+",'"+flag_lc_value+"','"+flag_dcq_tcq+"'," +
							  ""+dcqdays_tcqpercent_value+",'"+remarks+"','"+user_cd+"',sysdate,'Y')";
				//System.out.println("Delete Query for FGSA/Tender of FMS7_FGSA_TENDER_LC_DTL = "+queryString);
				stmt.executeUpdate(queryString);
			}
			else if(cont_type.equalsIgnoreCase("S") || cont_type.equalsIgnoreCase("L"))
			{
				queryString = "select COUNT(*) from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+fgsa_no+" " +
							  "and FGSA_TENDER_REV_NO="+fgsa_rev_no+" and CUSTOMER_CD="+customer_cd+" and " +
							  "CONT_TYPE='"+cont_type+"' and SN_LOA_NO="+sn_no+" and SN_LOA_REV_NO="+sn_rev_no+"";
				//System.out.println("Select Query for SN/LOA of FMS7_SN_LOA_LC_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);		
				if(rset.next())
				{
					count = rset.getInt(1);
				}
				
				if(count>0)
				{
					queryString = "delete from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+fgsa_no+" " +
								  "and FGSA_TENDER_REV_NO="+fgsa_rev_no+" and CUSTOMER_CD="+customer_cd+" and " +
								  "CONT_TYPE='"+cont_type+"' and SN_LOA_NO="+sn_no+" and SN_LOA_REV_NO="+sn_rev_no+"";
					//System.out.println("Delete Query for SN/LOA of FMS7_SN_LOA_LC_DTL = "+queryString);
					stmt.executeUpdate(queryString);
				}
				
				queryString = "insert into FMS7_SN_LOA_LC_DTL (FGSA_TENDER_NO, FGSA_TENDER_REV_NO, " +
							  "CUSTOMER_CD, CONT_TYPE, SN_LOA_NO, SN_LOA_REV_NO, EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, " +
							  "DCQ_DAYS_OR_TCQ_PERCENT, REMARKS, EMP_CD, ENT_DT, FLAG) " +
							  "values("+fgsa_no+","+fgsa_rev_no+","+customer_cd+"," +
							  "'"+cont_type+"',"+sn_no+","+sn_rev_no+"," +
							  ""+exchg_rate+",'"+flag_lc_value+"','"+flag_dcq_tcq+"'," +
							  ""+dcqdays_tcqpercent_value+",'"+remarks+"','"+user_cd+"',sysdate,'Y')";
				//System.out.println("Delete Query for SN/LOA of FMS7_SN_LOA_LC_DTL = "+queryString);
				stmt.executeUpdate(queryString);
			}
			
			if(cont_type.equalsIgnoreCase("F"))
			{
				msg = "Master LC Details Related to FGSA of "+customer_nm+" Inserted/Modified Successfully !!!";
			}
			else if(cont_type.equalsIgnoreCase("T"))
			{
				msg = "Master LC Details Related to Tender of "+customer_nm+" Inserted/Modified Successfully !!!";
			}
			else if(cont_type.equalsIgnoreCase("S"))
			{
				msg = "Master LC Details Related to SN of "+customer_nm+" Inserted/Modified Successfully !!!";
			}
			else if(cont_type.equalsIgnoreCase("L"))
			{
				msg = "Master LC Details Related to LOA of "+customer_nm+" Inserted/Modified Successfully !!!";
			}
			
			url = "../contract_master/frm_FGSA_LC_monitoring.jsp?msg="+msg+"&cont_type="+cont_type+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&customer_cd="+customer_cd+"&customer_nm="+customer_nm+"&customer_abbr="+customer_abbr+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
			
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
			dbcon.rollback();
			msg = "LC Details Not Inserted/Modified !!!";
			url = "../contract_master/frm_FGSA_LC_monitoring.jsp?msg="+msg+"&cont_type="+cont_type+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&customer_cd="+customer_cd+"&customer_nm="+customer_nm+"&customer_abbr="+customer_abbr+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
			System.out.println("Exception in the insert_modify_LC_MASTER_DETAILS_ENTRY() Method of "+servletName+" Servlet:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void insertModifyFgsaDeactivationDetails(HttpServletRequest request) throws Exception
	{
		methodName = "insertModifyFgsaDeactivationDetails()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
								
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
		String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_revno = request.getParameter("fgsa_revno")==null?"":request.getParameter("fgsa_revno");
		String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
				
		try
		{
			String [] from_dt = request.getParameterValues("from_dt");
			String [] to_dt = request.getParameterValues("to_dt");
			String [] remark = request.getParameterValues("remark");
			
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY') " +
						  "FROM FMS7_FGSA_DEACTIVATION_DTL " +
						  "WHERE CUSTOMER_CD="+buyer_cd+" And " +
						  "FGSA_NO="+fgsa_cd+" AND " +
						  "FGSA_REV_NO="+fgsa_revno+"";
			
			//System.out.println("Checking Query For FGSA Deactivation Details Existance = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				query1 = "DELETE FROM FMS7_FGSA_DEACTIVATION_DTL " +
						 "WHERE CUSTOMER_CD="+buyer_cd+" And " +
						 "FGSA_NO="+fgsa_cd+" AND " +
						 "FGSA_REV_NO="+fgsa_revno+"";
				
				//System.out.println("Delete Query For FGSA Deactivation Details = "+query1);
				stmt1.executeUpdate(query1);
			}
			
			int count = 0;
			int count2 = 0;
			
			String sn_remark = "";
			
			for(int i=0; i<from_dt.length; i++)
			{
				if(from_dt[i]!=null && to_dt[i]!=null)
				{
					if(!from_dt[i].trim().equals("") && !from_dt[i].trim().equals("0") 
						&& !to_dt[i].trim().equals("") && !to_dt[i].trim().equals("0"))
					{
						String rem = obj.replaceSingleQuotes(remark[i]);
						
						int cnt = 0;
						
						queryString = "SELECT SN_NO,TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') " +
									  "FROM FMS7_SN_MST WHERE CUSTOMER_CD="+buyer_cd+" AND " +
									  "FGSA_NO="+fgsa_cd+" AND FGSA_REV_NO="+fgsa_revno+" AND " +
									  "((TO_DATE('"+to_dt[i].trim()+"','DD/MM/YYYY') BETWEEN START_DT AND END_DT) OR " +
									  "(TO_DATE('"+from_dt[i].trim()+"','DD/MM/YYYY') BETWEEN START_DT AND END_DT) OR " +
									  "(TO_DATE('"+from_dt[i].trim()+"','DD/MM/YYYY')<START_DT AND " +
									  "TO_DATE('"+to_dt[i].trim()+"','DD/MM/YYYY')>END_DT))";
						
						//System.out.println("Checking for SN Existance During Specified Period = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							cnt = 1;
							++count2;
							sn_remark += "Deactivation Duration --> "+from_dt[i].trim()+" - "+to_dt[i].trim()+" Not Submitted Because --> SN: "+rset.getString(1)+" Having Duration From: "+rset.getString(2)+" To: "+rset.getString(3)+"<br>";
						}
						
						if(cnt==0)
						{
							queryString = "INSERT INTO FMS7_FGSA_DEACTIVATION_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, " +
										  "SEQ_NO, FROM_DT, TO_DT, REMARK, EMP_CD, ENT_DT, FLAG) VALUES" +
										  "("+buyer_cd+","+fgsa_cd+","+fgsa_revno+","+count+"," +
										  "TO_DATE('"+from_dt[i]+"','DD/MM/YYYY'),TO_DATE('"+to_dt[i]+"','DD/MM/YYYY')," +
										  "'"+rem+"',"+user_cd+",sysdate,'Y')";
							
							//System.out.println("Insert Query For FGSA Deactivation Details = "+queryString);
							stmt.executeUpdate(queryString);
							++count;
						}
					}
				}
			}
			
			dbcon.commit();
			
			if(count2==0)
			{
				if(count>0)
				{
					msg = "Deactivation Details Submited Successfully For The Selected FGSA !!!";
				}
				else
				{
					msg = "Deactivation Details NOT Submited For The Selected FGSA Because:<br>Any One Of The Mandatory Information Is Missing !!!";
				}
			}
			else
			{
				msg = sn_remark;
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
			dbcon.rollback();
			System.out.println("Exception in insertModifyFgsaDeactivationDetails() Method of Frm_Contract_Master Servlet:\n"+e.getMessage());
			e.printStackTrace();
			msg="Deactivation Details NOT Submited For The Selected FGSA !!!";
		}		
		url="../contract_master/frm_fgsa_active_deactive.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;	
	}//End Of 
	
	
	//Following Method Has Been Introduced by Priyanka Sharma on 18/01/2011 ...
	public void insertModifyLCFinanceMaster(HttpServletRequest request) throws Exception
	{
		methodName = "insertModifyLCFinanceMaster()";
		//System.out.println("inside insertModifyLCFinanceMaster");
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String lc_fin_yr = request.getParameter("lc_fin_yr")==null?"":request.getParameter("lc_fin_yr");
		String lc_seq_no  = request.getParameter("lc_seq_no")==null?"":request.getParameter("lc_seq_no"); 		
		String seq_no = request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String lc_ref_no = request.getParameter("LC_Ref_No")==null?"":request.getParameter("LC_Ref_No");
		String customer_nm = request.getParameter("customer_name")==null?"":request.getParameter("customer_name");
		String bank_name = request.getParameter("bank_name")==null?"":request.getParameter("bank_name");
		String bank_cd = request.getParameter("bank_cd")==null?"":request.getParameter("bank_cd");
		String bank_rating = request.getParameter("bank_rating")==null?"":request.getParameter("bank_rating");
		String validity_st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
		String validity_end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String final_LC_Amount = request.getParameter("Final_LC_Amount")==null?"":request.getParameter("Final_LC_Amount");
		String actual_LC_Amount = request.getParameter("Actual_LC_Amount")==null?"":request.getParameter("Actual_LC_Amount");
		//String other_bank_dtl  = request.getParameter("other_bank_dtl")==null?"":request.getParameter("other_bank_dtl");
		
		String lc_from_dt = request.getParameter("lc_from_dt")==null?"":request.getParameter("lc_from_dt");
		String lc_to_dt = request.getParameter("lc_to_dt")==null?"":request.getParameter("lc_to_dt");
		
		String rating_eff_dt = request.getParameter("rating_eff_dt")==null?"":request.getParameter("rating_eff_dt");
		String ship_dt = request.getParameter("ship_dt")==null?"":request.getParameter("ship_dt");
		String amendment_flag = request.getParameter("amendment_flag")==null?"":request.getParameter("amendment_flag");
		String amend_dt = request.getParameter("amend_dt")==null?"":request.getParameter("amend_dt");
		String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");		
		String amendment_no = request.getParameter("amendment_no")==null?"0":request.getParameter("amendment_no");
		remarks = obj.replaceSingleQuotes(remarks);
		//System.out.println("amendment_flag = "+amendment_flag);
		//System.out.println("Previous amendment_no = "+amendment_no);
		//System.out.println("fROM SERVLET insertModifyLCFinanceMaster() CUSTOMER NAME = "+customer_cd);
		try
		{
			if(amendment_flag.trim().equalsIgnoreCase("Y"))
			{
				queryString = "SELECT MAX(AMENDMENT_NO) FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+lc_ref_no+"'   ";
				//System.out.println("Select Query For FMS7_LC_FINANCE_MASTER = "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					amendment_no= ""+(Integer.parseInt(rset.getString(1))+1);
				}
				//System.out.println("amendment_no = "+amendment_no);
				
					query2 = "INSERT INTO FMS7_LC_FINANCE_MST(BANK_LC_NO, AMENDMENT_NO, " +
					"FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, BANK_CD, CUSTOMER_NM, BANK_NM, " +
					"BANK_RATING, RATING_EFF_DATE, VALIDITY_START_DATE, VALIDITY_END_DATE, " +
					"MRKT_LC_AMOUNT, BANK_LC_AMOUNT, LAST_SHIPMENT_DATE, AMENDMENT_DATE, " +
					"AMENDMENT_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG) VALUES ('"+lc_ref_no+"'" +
					" ,'"+amendment_no+"','"+lc_fin_yr+"','"+lc_seq_no+"','"+customer_cd+"','"+bank_cd+"'," +
					"'"+customer_nm+"','"+bank_name+"','"+bank_rating+"', " +
					"to_date('"+rating_eff_dt+"','dd/mm/yyyy'),  to_date('"+validity_st_dt+"','dd/mm/yyyy'), " +
					"to_date('"+validity_end_dt+"','dd/mm/yyyy'), "+final_LC_Amount+", "+actual_LC_Amount+", " +
					"to_date('"+ship_dt+"','dd/mm/yyyy'),to_date('"+amend_dt+"','dd/mm/yyyy'), '"+amendment_flag+"', " +
					"'"+remarks+"', "+user_cd+",sysdate,'Y') ";
					//System.out.println("Insert Query For FMS7_LC_FINANCE_MASTER = "+query2);
					stmt1.executeUpdate(query2);
				
				dbcon.commit();
				msg="LC Details Submited Successfully !!!";
				url="../accounting/frm_LC_monitoring.jsp?lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"&amendment_dt="+amend_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&msg="+msg;
			}
			else
			{
				queryString = "SELECT * FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+lc_ref_no+"' AND AMENDMENT_NO='"+amendment_no+"'  ";
				//System.out.println("Select Query For FMS7_LC_FINANCE_MASTER = "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					query1 = "DELETE FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+lc_ref_no+"'AND AMENDMENT_NO='"+amendment_no+"'  ";
					//System.out.println("Delete Query For FMS7_LC_FINANCE_MASTER = "+query1);
					stmt1.executeUpdate(query1);
					msg="LC Details Updated Successfully From Accounts !!!";
				}	
				
				queryString = "INSERT INTO FMS7_LC_FINANCE_MST(BANK_LC_NO, AMENDMENT_NO, " +
								"FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, BANK_CD, CUSTOMER_NM, BANK_NM, " +
								"BANK_RATING, RATING_EFF_DATE, VALIDITY_START_DATE, VALIDITY_END_DATE, " +
								"MRKT_LC_AMOUNT, BANK_LC_AMOUNT, LAST_SHIPMENT_DATE, AMENDMENT_DATE, " +
								"AMENDMENT_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG) VALUES ('"+lc_ref_no+"'" +
								" ,'"+amendment_no+"','"+lc_fin_yr+"','"+lc_seq_no+"','"+customer_cd+"','"+bank_cd+"'," +
								"'"+customer_nm+"','"+bank_name+"','"+bank_rating+"', " +
								"to_date('"+rating_eff_dt+"','dd/mm/yyyy'),  to_date('"+validity_st_dt+"','dd/mm/yyyy'), " +
								"to_date('"+validity_end_dt+"','dd/mm/yyyy'), "+final_LC_Amount+", "+actual_LC_Amount+", " +
								"to_date('"+ship_dt+"','dd/mm/yyyy'),to_date('"+amend_dt+"','dd/mm/yyyy'), '"+amendment_flag+"', " +
								"'"+remarks+"', "+user_cd+",sysdate,'Y') ";
				//System.out.println("Insert Query For FMS7_LC_FINANCE_MASTER = "+queryString);
				stmt.executeUpdate(queryString);
				dbcon.commit();					
				msg="LC Details Submited Successfully From Accounts !!!";
				url="../accounting/frm_LC_monitoring.jsp?lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"&amendment_dt="+amend_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&msg="+msg;				
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
			dbcon.rollback();
			System.out.println("Exception in insertModifyLC_FINANCE_MASTER() Method of Frm_Contract_Master Servlet:\n"+e.getMessage());
			e.printStackTrace();
			msg="LC Details NOT Submited Successfully !!!";
			url="../accounting/frm_LC_monitoring.jsp?lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"&amendment_dt="+amend_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&msg="+msg;
		}
	}
	
	//Following method has been added by Priyanka Sharma on 28th January, 2011.......
	public void updateSNMaster_FCC(HttpServletRequest request) throws Exception
	{
		methodName = "updateSNMaster_FCC()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		//String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String FGSANO = request.getParameter("FGSANO")==null?"0":request.getParameter("FGSANO");
		String FGSA_REVNO = request.getParameter("FGSA_REVNO")==null?"":request.getParameter("FGSA_REVNO");
		String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
		String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		String BUYER_NO = request.getParameter("BUYER_NO")==null?"":request.getParameter("BUYER_NO");
	    //String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	    //String buyer_abr = request.getParameter("buyer_abr")==null?"":request.getParameter("buyer_abr");
	    String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");	    	    
	    String FCC_flag= request.getParameter("FCC_flag")==null?"":request.getParameter("FCC_flag");
		
	    try
		{			
			query = "UPDATE FMS7_SN_MST SET FCC_FLAG='"+FCC_flag+"', FCC_DATE=sysdate, FCC_BY='"+user_cd+"' " +
			"WHERE SN_NO='"+SNNO+"' AND SN_REV_NO='"+SN_REVNO+"' AND CUSTOMER_CD='"+BUYER_NO+"' AND " +
			"FGSA_NO='"+FGSANO+"' AND FGSA_REV_NO='"+FGSA_REVNO+"'";
			//System.out.println("SN_FCC Update Query = "+query);
			stmt.executeUpdate(query);
			if(FCC_flag.trim().equalsIgnoreCase("Y"))
			{
				msg="Financial Checks and Control - SN Successfully Approved !!!!";
			}
			else if(FCC_flag.trim().equalsIgnoreCase("N"))
			{
				msg="Financial Checks and Control - SN Successfully Disapproved !!!!";
			}			
			
			url="../contract_master/frm_SN_master_FCC.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&FCC_flag="+FCC_flag;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="SN Approval Process Failed !!!";
			System.out.println("Exception in the updateSNMaster_FCC() Method of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();			
			url="../contract_master/frm_SN_master_FCC.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&FCC_flag="+FCC_flag;	
		}
	}
	
	//here fms7_cont_price_dtl
	public void SN_Closure_Request(HttpServletRequest request) throws Exception
	{
		methodName = "SN_Closure_Request()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		//String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String FGSANO = request.getParameter("FGSANO")==null?"0":request.getParameter("FGSANO");
		String FGSA_REVNO = request.getParameter("FGSA_REVNO")==null?"":request.getParameter("FGSA_REVNO");
		String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
		String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		String BUYER_NO = request.getParameter("BUYER_NO")==null?"":request.getParameter("BUYER_NO");
	    String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	    String buyer_abr = request.getParameter("buyer_abr")==null?"":request.getParameter("buyer_abr");
	    String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
	    //String fcc_flag = request.getParameter("fcc_flag")==null?"":request.getParameter("fcc_flag");
	    //String fcc_by = request.getParameter("fcc_by")==null?"":request.getParameter("fcc_by");
	    //String fcc_date = request.getParameter("fcc_date")==null?"":request.getParameter("fcc_date");	    
		try
		{
			String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
			String sn_name = request.getParameter("sn_name")==null?"0":request.getParameter("sn_name");
			String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
			String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
			String rev_dt = request.getParameter("rev_dt")==null?"":request.getParameter("rev_dt");
			
			String salesPrice = request.getParameter("salesPrice")==null?"":request.getParameter("salesPrice");
			String tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
			String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
			
			String transportation_charge = request.getParameter("transportation_charge")==null?"":request.getParameter("transportation_charge");
			String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
			String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
			String adv_amt = request.getParameter("adv_amt")==null?"":request.getParameter("adv_amt").trim();
			String sn_closure_dt = request.getParameter("sn_closure_dt")==null?"":request.getParameter("sn_closure_dt");
			sn_ref_no = obj.replaceSingleQuotes(sn_ref_no);
			remark = obj.replaceSingleQuotes(remark);
			
			//String var_mode = request.getParameter("var_mode")==null?"":request.getParameter("var_mode");
			String variance_qty = request.getParameter("variance_qty")==null?"":request.getParameter("variance_qty");
			
			String buyer_nom = request.getParameter("buyer_nom")==null?"N":request.getParameter("buyer_nom");
			String seller_nom = request.getParameter("seller_nom")==null?"N":request.getParameter("seller_nom");
			
			String day_def = request.getParameter("day_def")==null?"N":request.getParameter("day_def");
			String day_time_from = request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
			String day_time_to = request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");
			
			String mdcq = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
			String mdcqPer = request.getParameter("mdcq_percent")==null?"":request.getParameter("mdcq_percent");
			String mdcq_qty_unit = request.getParameter("mdcq_qty_unit")==null?"":request.getParameter("mdcq_qty_unit");
			
			String obligation = request.getParameter("obligation")==null?"N":request.getParameter("obligation");
			String obg_percent = request.getParameter("obg_percent")==null?"":request.getParameter("obg_percent");
			String obg_qty_unit = request.getParameter("obg_qty_unit")==null?"":request.getParameter("obg_qty_unit");
			
			String measurement = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
			String standard = request.getParameter("standard")==null?"":request.getParameter("standard");
			String temprature = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			String rate_min_bar = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			String rate_max_bar = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");
					
			String off_spec_gas_chk = request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
			String energy_off_spec = request.getParameter("energy_off_spec")==null?"":request.getParameter("energy_off_spec");
			String min_energy = request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			String max_energy = request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
			String var_tcq = request.getParameter("var_tcq")==null?"":request.getParameter("var_tcq");
			String tcq_sign = request.getParameter("tcq_sign")==null?"":request.getParameter("tcq_sign");
			
			String[] clause_nm = request.getParameterValues("clause_nm");
			//String submitFlag = request.getParameter("submitFlag")==null?"":request.getParameter("submitFlag");
			
			String[] chk_trans = request.getParameterValues("chk_trans");
			String[] chk_delv = request.getParameterValues("chk_delv");
			
			String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
			String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
			
			String buy_m = "N";
			String buy_w ="N";
			String buy_d = "N";
			
			/* ADDED BY RG 24-09-2014 */
			String advance_flag=request.getParameter("advance")==null?"N":request.getParameter("advance");
			String advance_amount=request.getParameter("advance_amount")==null?"":request.getParameter("advance_amount");
			String advance_cur=request.getParameter("advance_cur")==null?"":request.getParameter("advance_cur");
			String discount_amount=request.getParameter("discount_amount")==null?"":request.getParameter("discount_amount");
			String discount_flag=request.getParameter("discount")==null?"N":request.getParameter("discount");
			String tariff_price=request.getParameter("tariff_inr_amount")==null?"":request.getParameter("tariff_inr_amount");
			String tariff_flag=request.getParameter("tariff_inr")==null?"N":request.getParameter("tariff_inr");
			/* ADDED BY RG 24-09-2014 */

			String ADVANCE_COLLECTION = request.getParameter("advance_collection")==null?"N":request.getParameter("advance_collection");
			String ADVANCE_COLLECTION_FLAG = request.getParameter("advance_collection_flag")==null?"N":request.getParameter("advance_collection_flag");
			String firm_qty = request.getParameter("firm_qty")==null?"":request.getParameter("firm_qty");
			String re_qty = request.getParameter("re_qty")==null?"":request.getParameter("re_qty");
			String split_tcq = request.getParameter("split_tcq")==null?"N":request.getParameter("split_tcq");
			
			
			if(buyer_nom.equalsIgnoreCase("Y"))
			{
				if(chk_buyer_nom!=null)
				{
					for(int i=0;i<chk_buyer_nom.length;i++)
					{
						if(chk_buyer_nom[i].equalsIgnoreCase("M"))
						{
							buy_m = "Y"; 
			    		}
						else if(chk_buyer_nom[i].equalsIgnoreCase("W"))
						{
							buy_w = "Y";
						}
						else if(chk_buyer_nom[i].equalsIgnoreCase("D"))
						{
							buy_d = "Y";
						}
					}
				}
			}
			
			String sel_m = "N";
			String sel_w ="N";
			String sel_d = "N";
			if(seller_nom.equalsIgnoreCase("Y"))
			{
				if(chk_seller_nom!=null)
				{
					for(int i=0;i<chk_seller_nom.length;i++)
					{
						if(chk_seller_nom[i].equalsIgnoreCase("M"))
						{
							sel_m = "Y"; 
			    		}
						else if(chk_seller_nom[i].equalsIgnoreCase("W"))
						{
							sel_w = "Y";
						}
						else if(chk_seller_nom[i].equalsIgnoreCase("D"))
						{
							sel_d = "Y";
						}
					}
				}	
			}			
			//System.out.println("buy_m = "+buy_m+",  buy_w = "+buy_w+",  buy_d = "+buy_d);
			//System.out.println("sel_m = "+sel_m+",  sel_w = "+sel_w+",  sel_d = "+sel_d);			
			if(!mdcq.equalsIgnoreCase("Y"))
			{
				mdcqPer = "";
				mdcq_qty_unit = "1";
			}
			 
			if(!measurement.equalsIgnoreCase("Y"))
			{  
				standard = "";
				temprature = "";
				rate_min_bar = "";
				rate_max_bar = "";
			}
			
			if(!off_spec_gas_chk.equalsIgnoreCase("Y"))
			{
				energy_off_spec = "0";
				min_energy = "";
				max_energy = "";
			}
			
			if(!obligation.equalsIgnoreCase("Y"))
			{
				obg_percent = "";
				obg_qty_unit = "1";
			}
			//insert entry in the master
			//System.out.println("off_spec_gas_chk = "+off_spec_gas_chk);
			String message = "";			
			int count = 0;			
			query = "SELECT MAX(SN_REV_NO) FROM FMS7_SN_MST " +
					"WHERE SN_NO='"+SNNO+"' AND " +
					"CUSTOMER_CD='"+BUYER_NO+"' AND " +
					"FGSA_NO='"+FGSANO+"' AND " +
					"FGSA_REV_NO='"+FGSA_REVNO+"'";			
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				SN_REVNO = ""+(rset.getInt(1)+1);
				++count;
			}
			
			if(count>0)
			{
				//////////////////////*****RG 07102014 For advance clause
				String mapping_id=""+BUYER_NO+"-"+FGSANO+"-"+FGSA_REVNO+"-"+SNNO+"-"+SN_REVNO+"-"+"S";
				query = "DELETE FROM FMS7_CONT_PRICE_DTL WHERE MAPPING_ID='"+mapping_id+"' ";
				//System.out.println("Deleting data from conract_price_dtl..."+query);
				stmt.executeUpdate(query);
				
				if(advance_flag.equalsIgnoreCase("Y")) 
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','1','"+advance_amount+"','"+advance_cur+"','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(discount_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','2','"+discount_amount+"','2','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(tariff_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','3','"+tariff_price+"','1','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				/*********************************************************/
				
				
				String tmp_SN_REVNO = ""+(Integer.parseInt(SN_REVNO)-1);
				sn_name = buyer_abr+"-FG"+FGSANO+"-FGREV"+SN_REVNO+"-SN"+SNNO+"-SNREV"+SN_REVNO;
				
				query = "INSERT INTO FMS7_SN_MST(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
						"SN_NAME, SIGNING_DT, START_DT, END_DT, REV_DT, TCQ, DCQ, RATE, BUYER_NOM, " +
						"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, " +
						"SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
						"MDCQ_PERCENTAGE, MDCQ_QTY_CD, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
						"PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
						"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, VARIATION_QTY, EMP_CD, ENT_DT, " +
						"FLAG, STATUS, QUANTITY_UNIT, RATE_UNIT, SN_REF_NO, TRANSPORTATION_CHARGE, " +
						"REMARK,ADV_AMT,SN_CLOSURE_REQUEST,TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY," +
						"SN_CLOSURE_CLOSE,SN_CLOSURE_DT" +
						//"FCC_FLAG,FCC_BY,FCC_DATE," +
						",ADVANCE_COLLECTION,ADVANCE_COLLECTION_FLAG,FIRM_QTY,RE_QTY,SPLIT_TCQ_FLAG ) " + 	
						"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", " +
						"'"+sn_name+"', TO_DATE('"+sign_dt+"','dd/mm/yyyy'), TO_DATE('"+st_dt+"','dd/mm/yyyy'), " +
						"TO_DATE('"+end_dt+"','dd/mm/yyyy'), TO_DATE('"+rev_dt+"','DD/MM/YYYY'), '"+tcq+"', " +
						"'"+dcq+"', '"+salesPrice+"', '"+buyer_nom+"', '"+buy_m+"', '"+buy_w+"', '"+buy_d+"', " +
						"'"+seller_nom+"', '"+sel_m+"', '"+sel_w+"', '"+sel_d+"', '"+day_def+"', '"+day_time_from+"', " +
						"'"+day_time_to+"', '"+mdcq+"', '"+mdcqPer+"', '"+mdcq_qty_unit+"', '"+measurement+"', " +
						"'"+standard+"', '"+temprature+"', '"+rate_min_bar+"', '"+rate_max_bar+"', " +
						"'"+off_spec_gas_chk+"', '"+energy_off_spec+"', '"+min_energy+"', " +
						"'"+max_energy+"', '"+variance_qty+"', '"+user_cd+"', " +
						"sysdate, 'T', 'Y', '1', '2', '"+sn_ref_no+"', '"+transportation_charge+"', " +
						"'"+remark+"', '"+adv_amt+"','Y','"+tcq_sign+"','"+var_tcq+"','N'," +
						"TO_DATE('"+sn_closure_dt+"','DD/MM/YYYY') " +
						//"'"+fcc_flag+"','"+fcc_by+"',TO_DATE('"+fcc_date+"','DD/MM/YYYY')," +
						",'"+ADVANCE_COLLECTION+"','"+ADVANCE_COLLECTION_FLAG+"','"+firm_qty+"','"+re_qty+"','"+split_tcq+"')"; 
				//System.out.println("SN Master query = "+query);
				stmt.executeUpdate(query);
				
				//For Fetching SN LD Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
						"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
						"REMARKS, FLAG FROM FMS7_SN_LD_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";
				//System.out.println("Liquidated Damages SN query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
							 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
							 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 "'"+rset.getString(4)+"', '"+rset.getString(5)+"', " +
							 "'"+rset.getString(6)+"', '"+rset.getString(7)+"', " +
							 "'"+obj.replaceSingleQuotes(rset.getString(8))+"', " +
							 "'"+rset.getString(9)+"', '"+user_cd+"', sysdate)";
					//System.out.println("Insert Liquidated Damages SN query = "+query);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
							"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
							"REMARKS, FLAG FROM FMS7_FGSA_LD_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";					
					//System.out.println("Liquidated Damages FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_LD_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
								 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 "'"+rset2.getString(4)+"', '"+rset2.getString(5)+"', " +
								 "'"+rset2.getString(6)+"', '"+rset2.getString(7)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(8))+"', " +
								 "'"+rset2.getString(9)+"', '"+user_cd+"', sysdate)";
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Take Or Pay Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT PRICE_PER, PRICE, TOP_PER, " +
						"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
						"FLAG FROM FMS7_SN_TOP_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";				
				//System.out.println("Take Or Pay SN query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
							 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							 "FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 ""+rset.getString(4)+", '"+rset.getString(5)+"', " +
							 "'"+obj.replaceSingleQuotes(rset.getString(6))+"', " +
							 "'"+rset.getString(7)+"', '"+user_cd+"', sysdate)";
					//System.out.println("SN TOP query = "+query);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, TOP_PER, " +
							"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							"FLAG FROM FMS7_FGSA_TOP_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";					
					//System.out.println("Take Or Pay FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_TOP_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
								 "FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+rset2.getString(5)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(6))+"', " +
								 "'"+rset2.getString(7)+"', '"+user_cd+"', sysdate)";
						//System.out.println("TOP INSERT SN query = "+query);
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Make Up Gas Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
						"REMARKS, FLAG FROM FMS7_SN_MAKEUPGAS_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";				
				//System.out.println("MAKEUP GAS SN query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
							 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
							 ""+rset.getString(1)+", "+rset.getString(2)+", "+rset.getString(3)+", " +
							 ""+rset.getString(4)+", '"+obj.replaceSingleQuotes(rset.getString(5))+"', " +
							 "'"+rset.getString(6)+"', '"+user_cd+"', sysdate)";
					//System.out.println("Insert MUG SN query = "+query);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT MAKEUP_PERIOD, RECOVERY_PERIOD, PRICE_PER, PRICE, " +
							"REMARKS, FLAG FROM FMS7_FGSA_MAKEUPGAS_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";			
					//System.out.println("MAKEUP GAS FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
								 "FGSA_REV_NO, SN_NO, SN_REV_NO, CONT_TYPE, MAKEUP_PERIOD, " +
								 "RECOVERY_PERIOD, PRICE_PER, PRICE, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								 ""+rset2.getString(1)+", "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+", '"+obj.replaceSingleQuotes(rset2.getString(5))+"', " +
								 "'"+rset2.getString(6)+"', '"+user_cd+"', sysdate)";
						//System.out.println("INSERT MUG SN query = "+query);
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching SN Billing Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
						"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
						"FLAG, EXCHG_RATE_NOTE FROM FMS7_SN_BILLING_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
						"CONT_TYPE='S'";				
				//System.out.println("BILLING SN query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
							"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
							"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
							"'"+rset.getString(1)+"', "+rset.getString(2)+", "+rset.getString(3)+", " +
							"'"+rset.getString(4)+"', "+rset.getString(5)+", "+rset.getString(6)+", " +
							"'"+rset.getString(7)+"', "+rset.getString(8)+", "+rset.getString(9)+", " +
							""+rset.getString(10)+", '"+user_cd+"', sysdate, '"+rset.getString(11)+"', " +
							"'"+obj.replaceSingleQuotes(rset.getString(12))+"')";			
					//System.out.println("Insert Query For SN Billing Details = "+query1);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"FLAG, EXCHG_RATE_NOTE FROM FMS7_FGSA_BILLING_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='"+FGSA_REVNO+"' AND " +
							"CONT_TYPE='F'";					
					//System.out.println("BILLING FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
								"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
								"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
								"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
								"VALUES("+BUYER_NO+", "+FGSANO+", "+FGSA_REVNO+", "+SNNO+", "+SN_REVNO+", 'S', " +
								"'"+rset2.getString(1)+"', "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								"'"+rset2.getString(4)+"', "+rset2.getString(5)+", "+rset2.getString(6)+", " +
								"'"+rset2.getString(7)+"', "+rset2.getString(8)+", "+rset2.getString(9)+", " +
								""+rset2.getString(10)+", '"+user_cd+"', sysdate, '"+rset2.getString(11)+"', " +
								"'"+obj.replaceSingleQuotes(rset2.getString(12))+"')";				
						//System.out.println("SN Billing Details Insert Query = "+query1);
						stmt1.executeUpdate(query1);
					}
				}				
				msg = "Revised SN Detail Submitted - SN No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";
				message = msg;
			}
						
			//Data submission for the transporter
			query = "select count(*) from FMS7_SN_TRANSPORTER_MST where " +
					"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_SN_TRANSPORTER_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_trans!=null)
			{
				for(int i=0;i<chk_trans.length;i++)
				{
					query = "insert into FMS7_SN_TRANSPORTER_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, CUSTOMER_CD, TRANSPORTER_CD, EMP_CD, ENT_DT, FLAG) " +
							"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				}
			}				
			
			//Data submissin for the delivery points
			query = "select count(*) from FMS7_SN_PLANT_MST where " +
					"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_SN_PLANT_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and CUSTOMER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_delv!=null)
			{
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_SN_PLANT_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, CUSTOMER_CD, PLANT_SEQ_NO,  EMP_CD, ENT_DT, FLAG) " +
							" values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				 }
			}				
			
			//Data submissin for the Clauses
			query = "select count(*) from FMS7_SN_CLAUSE_MST where " +
					"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_SN_CLAUSE_MST where " +
						"SN_NO='"+SNNO+"' and SN_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and FGSA_NO='"+FGSANO+"' and FGSA_REV_NO='"+FGSA_REVNO+"' ";
				stmt.executeUpdate(query);
			}
			if(clause_nm!=null)
			{
				for(int i=0;i<clause_nm.length; i++)
				{
					query = "insert into FMS7_SN_CLAUSE_MST(SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, BUYER_CD, CLAUSE_CD,   EMP_CD, ENT_DT, FLAG) " +
							"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+FGSA_REVNO+"','"+BUYER_NO+"','"+clause_nm[i]+"','"+user_cd+"',sysdate,'T') ";
					//System.out.println(query);
					stmt.executeUpdate(query);
				}
			}	
			msg = "New SN Revision Submitted Along With Closure Request !!!";
			url="../contract_master/frm_SN_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="SN Closure Request Not Submitted !!!";
			System.out.println("Exception in the SNMaster() Method of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();			
			url="../contract_master/frm_SN_master.jsp?msg="+msg+"&flg=update&activity=update&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SNNO+"&SN_REVNO="+SN_REVNO+"&FGSA_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;	
		}
	}
	
	//here fms7_cont_price_dtl
	public void LOA_Closure_Request(HttpServletRequest request) throws Exception
	{
		methodName = "LOA_Closure_Request()";
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		//String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		
		String FGSANO = request.getParameter("FGSANO")==null?"0":request.getParameter("FGSANO");		
		String SNNO = request.getParameter("SNNO")==null?"":request.getParameter("SNNO");
		String SN_REVNO = request.getParameter("SN_REVNO")==null?"":request.getParameter("SN_REVNO");
		String BUYER_NO = request.getParameter("BUYER_NO")==null?"":request.getParameter("BUYER_NO");
	    String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	    String buyer_abr = request.getParameter("buyer_abr")==null?"":request.getParameter("buyer_abr");
	    String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");
	    //String fcc_flag = request.getParameter("fcc_flag")==null?"":request.getParameter("fcc_flag");
	    //String fcc_by = request.getParameter("fcc_by")==null?"":request.getParameter("fcc_by");
	    //String fcc_date = request.getParameter("fcc_date")==null?"":request.getParameter("fcc_date");
		try
		{
			String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
			String sn_name = request.getParameter("sn_name")==null?"0":request.getParameter("sn_name");
			String st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
			String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
			String sn_closure_dt = request.getParameter("sn_closure_dt")==null?"":request.getParameter("sn_closure_dt");
			
			String salesPrice = request.getParameter("salesPrice")==null?"":request.getParameter("salesPrice");
			String tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
			String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
			
			String transportation_charge = request.getParameter("transportation_charge")==null?"":request.getParameter("transportation_charge");
			String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
			String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
			String adv_amt = request.getParameter("adv_amt")==null?"":request.getParameter("adv_amt").trim();
			
			sn_ref_no = obj.replaceSingleQuotes(sn_ref_no);
			remark = obj.replaceSingleQuotes(remark);
			
			//String var_mode = request.getParameter("var_mode")==null?"":request.getParameter("var_mode");
			String variance_qty = request.getParameter("variance_qty")==null?"":request.getParameter("variance_qty");
			
			String buyer_nom = request.getParameter("buyer_nom")==null?"N":request.getParameter("buyer_nom");
			String seller_nom = request.getParameter("seller_nom")==null?"N":request.getParameter("seller_nom");
			
			String day_def = request.getParameter("day_def")==null?"N":request.getParameter("day_def");
			String day_time_from = request.getParameter("day_time_from")==null?"":request.getParameter("day_time_from");
			String day_time_to = request.getParameter("day_time_to")==null?"":request.getParameter("day_time_to");
			
			String mdcq = request.getParameter("mdcq")==null?"N":request.getParameter("mdcq");
			String mdcqPer = request.getParameter("mdcq_percent")==null?"":request.getParameter("mdcq_percent");
			String mdcq_qty_unit = request.getParameter("mdcq_qty_unit")==null?"":request.getParameter("mdcq_qty_unit");
			
			String obligation = request.getParameter("obligation")==null?"N":request.getParameter("obligation");
			String obg_percent = request.getParameter("obg_percent")==null?"":request.getParameter("obg_percent");
			String obg_qty_unit = request.getParameter("obg_qty_unit")==null?"":request.getParameter("obg_qty_unit");
			
			String measurement = request.getParameter("measurement")==null?"N":request.getParameter("measurement");
			String standard = request.getParameter("standard")==null?"":request.getParameter("standard");
			String temprature = request.getParameter("temprature")==null?"":request.getParameter("temprature");
			String rate_min_bar = request.getParameter("rate_min_bar")==null?"":request.getParameter("rate_min_bar");
			String rate_max_bar = request.getParameter("rate_max_bar")==null?"":request.getParameter("rate_max_bar");		
			
			String off_spec_gas_chk = request.getParameter("off_spec_gas_chk")==null?"N":request.getParameter("off_spec_gas_chk");
			String energy_off_spec = request.getParameter("energy_off_spec")==null?"":request.getParameter("energy_off_spec");
			String min_energy = request.getParameter("min_energy")==null?"":request.getParameter("min_energy");
			String max_energy = request.getParameter("max_energy")==null?"":request.getParameter("max_energy");
			String var_tcq = request.getParameter("var_tcq")==null?"":request.getParameter("var_tcq");
			String tcq_sign = request.getParameter("tcq_sign")==null?"":request.getParameter("tcq_sign");
			
			String[] clause_nm = request.getParameterValues("clause_nm");
			String submitFlag = request.getParameter("submitFlag")==null?"":request.getParameter("submitFlag");
			
			String[] chk_trans = request.getParameterValues("chk_trans");
			String[] chk_delv = request.getParameterValues("chk_delv");
			
			String[] chk_buyer_nom = request.getParameterValues("chk_buyer_nom");
			String[] chk_seller_nom = request.getParameterValues("chk_seller_nom");
			
			String buy_m = "N";
			String buy_w ="N";
			String buy_d = "N";
			
			/* ADDED BY RG 23-09-2014 */
			String advance_flag=request.getParameter("advance")==null?"N":request.getParameter("advance");
			String advance_amount=request.getParameter("advance_amount")==null?"":request.getParameter("advance_amount");
			String advance_cur=request.getParameter("advance_cur")==null?"":request.getParameter("advance_cur");
			String discount_amount=request.getParameter("discount_amount")==null?"":request.getParameter("discount_amount");
			String discount_flag=request.getParameter("discount")==null?"N":request.getParameter("discount");
			String tariff_price=request.getParameter("tariff_inr_amount")==null?"":request.getParameter("tariff_inr_amount");
			String tariff_flag=request.getParameter("tariff_inr")==null?"N":request.getParameter("tariff_inr");
			/* ADDED BY RG 23-09-2014 */

			String ADVANCE_COLLECTION = request.getParameter("advance_collection")==null?"N":request.getParameter("advance_collection");
			String ADVANCE_COLLECTION_FLAG = request.getParameter("advance_collection_flag")==null?"N":request.getParameter("advance_collection_flag");
			String firm_qty = request.getParameter("firm_qty")==null?"":request.getParameter("firm_qty");
			String re_qty = request.getParameter("re_qty")==null?"":request.getParameter("re_qty");
			String split_tcq_flag = request.getParameter("split_tcq")==null?"N":request.getParameter("split_tcq");

			
			if(buyer_nom.equalsIgnoreCase("Y"))
			{
				if(chk_buyer_nom!=null)
				{
					for(int i=0;i<chk_buyer_nom.length;i++)
					{
						if(chk_buyer_nom[i].equalsIgnoreCase("M"))
						{
							buy_m = "Y"; 
			    		}
						else if(chk_buyer_nom[i].equalsIgnoreCase("W"))
						{
							buy_w = "Y";
						}
						else if(chk_buyer_nom[i].equalsIgnoreCase("D"))
						{
							buy_d = "Y";
						}
					}
				}
			}
			
			String sel_m = "N";
			String sel_w ="N";
			String sel_d = "N";
			if(seller_nom.equalsIgnoreCase("Y"))
			{
				if(chk_seller_nom!=null)
				{
					for(int i=0;i<chk_seller_nom.length;i++)
					{
						if(chk_seller_nom[i].equalsIgnoreCase("M"))
						{
							sel_m = "Y"; 
			    		}
						else if(chk_seller_nom[i].equalsIgnoreCase("W"))
						{
							sel_w = "Y";
						}
						else if(chk_seller_nom[i].equalsIgnoreCase("D"))
						{
							sel_d = "Y";
						}
					}
				}	
			}			
			//System.out.println("buy_m = "+buy_m+",  buy_w = "+buy_w+",  buy_d = "+buy_d);
			//System.out.println("sel_m = "+sel_m+",  sel_w = "+sel_w+",  sel_d = "+sel_d);			
			if(!mdcq.equalsIgnoreCase("Y"))
			{
				mdcqPer = "";
				mdcq_qty_unit = "1";
			}
			 
			if(!measurement.equalsIgnoreCase("Y"))
			{  
				standard = "";
				temprature = "";
				rate_min_bar = "";
				rate_max_bar = "";
			}
			
			if(!off_spec_gas_chk.equalsIgnoreCase("Y"))
			{
				energy_off_spec = "0";
				min_energy = "";
				max_energy = "";
			}
			
			if(!obligation.equalsIgnoreCase("Y"))
			{
				obg_percent = "";
				obg_qty_unit = "1";
			}
			//insert entry in the master
			
			String message = "";			
			int count = 0;			
			query = "SELECT MAX(LOA_REV_NO) FROM FMS7_LOA_MST " +
					"WHERE LOA_NO='"+SNNO+"' AND " +
					"CUSTOMER_CD='"+BUYER_NO+"' AND TENDER_NO='"+FGSANO+"' ";			
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				SN_REVNO = ""+(rset.getInt(1)+1);
				++count;
			}
			if(count>0)
			{
				/////////////////////*****RG 07102014 For advance clause
				String mapping_id=""+BUYER_NO+"-"+FGSANO+"-"+0+"-"+SNNO+"-"+SN_REVNO+"-"+"L";
				query = "DELETE FROM FMS7_CONT_PRICE_DTL WHERE MAPPING_ID='"+mapping_id+"' ";
				//System.out.println("Deleting data from conract_price_dtl..."+query);
				stmt.executeUpdate(query);
				
				if(advance_flag.equalsIgnoreCase("Y")) 
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','1','"+advance_amount+"','"+advance_cur+"','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(discount_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','2','"+discount_amount+"','2','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				if(tariff_flag.equalsIgnoreCase("Y"))
				{
					query = "INSERT INTO FMS7_CONT_PRICE_DTL(MAPPING_ID,PRICE_CD,PRICE_RATE,CURRENCY_CD,FLAG) " +
							"VALUES('"+mapping_id+"','3','"+tariff_price+"','1','Y')";
					//System.out.println("INSERTING DATA IN TO CONTRACT DTL.."+query);
					stmt.executeUpdate(query);
				}
				/*********************************************************/
				
				String tmp_SN_REVNO = ""+(Integer.parseInt(SN_REVNO)-1);
				sn_name = buyer_abr+"-TENDER"+FGSANO+"-LOA"+SNNO+"-LOAREV"+SN_REVNO;				
				query = "INSERT INTO FMS7_LOA_MST(CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO, " +
						"LOA_NAME, SIGNING_DT, START_DT, END_DT,LOA_CLOSURE_DT, TCQ, DCQ, RATE, BUYER_NOM, " +
						"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, " +
						"SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
						"MDCQ_PERCENTAGE, MDCQ_QTY_CD, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
						"PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
						"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, VARIATION_QTY, EMP_CD, ENT_DT, " +
						"FLAG, STATUS, QUANTITY_UNIT, RATE_UNIT,LOA_REF_NO, TRANSPORTATION_CHARGE, " +
						"REMARK,ADV_AMT,LOA_CLOSURE_REQUEST,TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY," +
						"LOA_CLOSURE_CLOSE" +
						//",FCC_FLAG,FCC_BY,FCC_DATE" +
						",ADVANCE_COLLECTION,ADVANCE_COLLECTION_FLAG,FIRM_QTY,RE_QTY,SPLIT_TCQ_FLAG) " +	
						"VALUES("+BUYER_NO+", "+FGSANO+","+SNNO+", "+SN_REVNO+", " +
						"'"+sn_name+"', TO_DATE('"+sign_dt+"','dd/mm/yyyy'), TO_DATE('"+st_dt+"','dd/mm/yyyy'), " +
						"TO_DATE('"+end_dt+"','dd/mm/yyyy'),TO_DATE('"+sn_closure_dt+"','dd/mm/yyyy'), '"+tcq+"', " +
						"'"+dcq+"', '"+salesPrice+"', '"+buyer_nom+"', '"+buy_m+"', '"+buy_w+"', '"+buy_d+"', " +
						"'"+seller_nom+"', '"+sel_m+"', '"+sel_w+"', '"+sel_d+"', '"+day_def+"', '"+day_time_from+"', " +
						"'"+day_time_to+"', '"+mdcq+"', '"+mdcqPer+"', '"+mdcq_qty_unit+"', '"+measurement+"', " +
						"'"+standard+"', '"+temprature+"', '"+rate_min_bar+"', '"+rate_max_bar+"', " +
						"'"+off_spec_gas_chk+"', '"+energy_off_spec+"', '"+min_energy+"', " +
						"'"+max_energy+"', '"+variance_qty+"', '"+user_cd+"', " +
						"sysdate, 'T', 'Y', '1', '2', '"+sn_ref_no+"', '"+transportation_charge+"', " +
						"'"+remark+"', '"+adv_amt+"','Y','"+tcq_sign+"','"+var_tcq+"','N' " +
						//",'"+fcc_flag+"','"+fcc_by+"',To_DATE('"+fcc_date+"','DD/MM/YYYY')" +
						",'"+ADVANCE_COLLECTION+"','"+ADVANCE_COLLECTION_FLAG+"','"+firm_qty+"','"+re_qty+"','"+split_tcq_flag+"')";  
				//System.out.println("LOA Master query = "+query);
				stmt.executeUpdate(query);				
				//For Fetching LOA LD Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT PRICE_PER, PRICE, LIABILITY_PER, " +
						"PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, MDCQ_FLAG, " +
						"REMARKS, FLAG FROM FMS7_LOA_LD_DTL " +
						"WHERE LOA_NO='"+SNNO+"' AND " +
						"LOA_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='0' AND CONT_TYPE='L'";
				//System.out.println("Liquidated Damages LOA query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_LOA_LD_DTL(CUSTOMER_CD,FGSA_NO, " +
							 "FGSA_REV_NO,LOA_NO,LOA_REV_NO,CONT_TYPE,PRICE_PER,PRICE, " +
							 "LIABILITY_PER,PROMISE_QTY_FREQ,DCQ_FLAG,PNDCQ_FLAG, " +
							 "MDCQ_FLAG,REMARKS,FLAG,EMP_CD,ENT_DT) " +
							 "VALUES("+BUYER_NO+", "+FGSANO+",'0',"+SNNO+","+SN_REVNO+",'L', " +
							 ""+rset.getString(1)+","+rset.getString(2)+","+rset.getString(3)+", " +
							 "'"+rset.getString(4)+"', '"+rset.getString(5)+"', " +
							 "'"+rset.getString(6)+"', '"+rset.getString(7)+"', " +
							 "'"+obj.replaceSingleQuotes(rset.getString(8))+"', " +
							 "'"+rset.getString(9)+"', '"+user_cd+"', sysdate)";
					//System.out.println("Insert Liquidated Damages LOA query = "+query);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 ="SELECT PRICE_PER,PRICE,LIABILITY_PER," +
							"PROMISE_QTY_FREQ,DCQ_FLAG,PNDCQ_FLAG,MDCQ_FLAG,REMARKS,FLAG " +
							"FROM FMS7_TENDER_LD_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"TENDER_NO='"+FGSANO+"' AND " +
							"CONT_TYPE='T'";					
					//System.out.println("Liquidated Damages FGSA query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_LOA_LD_DTL(CUSTOMER_CD, TENDER_NO, " +
								 "LOA_NO,LOA_REV_NO, CONT_TYPE, PRICE_PER, PRICE, " +
								 "LIABILITY_PER, PROMISE_QTY_FREQ, DCQ_FLAG, PNDCQ_FLAG, " +
								 "MDCQ_FLAG, REMARKS, FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+","+SNNO+","+SN_REVNO+",'L', " +
								 ""+rset2.getString(1)+","+rset2.getString(2)+","+rset2.getString(3)+", " +
								 "'"+rset2.getString(4)+"','"+rset2.getString(5)+"'," +
								 "'"+rset2.getString(6)+"','"+rset2.getString(7)+"'," +
								 "'"+obj.replaceSingleQuotes(rset2.getString(8))+"', " +
								 "'"+rset2.getString(9)+"','"+user_cd+"', sysdate)";				
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching LOA Take Or Pay Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT PRICE_PER,PRICE,TOP_PER, " +
						"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ,REMARKS, " +
						"FLAG FROM FMS7_LOA_TOP_DTL " +
						"WHERE LOA_NO='"+SNNO+"' AND " +
						"LOA_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='0' AND CONT_TYPE='L'";				
				//System.out.println("Take Or Pay LOA query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_LOA_TOP_DTL(CUSTOMER_CD,FGSA_NO, " +
							 "FGSA_REV_NO,LOA_NO,LOA_REV_NO,CONT_TYPE,PRICE_PER,PRICE,TOP_PER," +
							 "ACTUAL_OBLIG_QTY,PROMISE_QTY_FREQ,REMARKS,FLAG,EMP_CD,ENT_DT) " +
							 "VALUES("+BUYER_NO+","+FGSANO+",'0',"+SNNO+","+SN_REVNO+",'L', " +
							 ""+rset.getString(1)+","+rset.getString(2)+","+rset.getString(3)+", " +
							 ""+rset.getString(4)+",'"+rset.getString(5)+"'," +
							 "'"+obj.replaceSingleQuotes(rset.getString(6))+"'," +
							 "'"+rset.getString(7)+"', '"+user_cd+"', sysdate)";
					//System.out.println("LOA TOP query = "+query);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT PRICE_PER, PRICE, TOP_PER, " +
							"ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
							"FLAG FROM FMS7_TENDER_TOP_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"TENDER_NO='"+FGSANO+"' AND " +							
							"CONT_TYPE='T'";					
					//System.out.println("Take Or Pay Tender query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_LOA_TOP_DTL(CUSTOMER_CD,FGSA_NO, " +
								 "FGSA_REV_NO,LOA_NO,LOA_REV_NO,CONT_TYPE,PRICE_PER,PRICE, " +
								 "TOP_PER, ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ, REMARKS, " +
								 "FLAG, EMP_CD, ENT_DT) " +
								 "VALUES("+BUYER_NO+","+FGSANO+",'0',"+SNNO+","+SN_REVNO+",'L', " +
								 ""+rset2.getString(1)+","+rset2.getString(2)+","+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+",'"+rset2.getString(5)+"', " +
								 "'"+obj.replaceSingleQuotes(rset2.getString(6))+"', " +
								 "'"+rset2.getString(7)+"','"+user_cd+"', sysdate)";
						//System.out.println("TOP INSERT LOA query = "+query);
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching LOA Make Up Gas Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT MAKEUP_PERIOD,RECOVERY_PERIOD,PRICE_PER,PRICE,REMARKS,FLAG " +
						"FROM FMS7_LOA_MAKEUPGAS_DTL " +
						"WHERE LOA_NO='"+SNNO+"' AND " +
						"LOA_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='0' AND CONT_TYPE='L'";				
				//System.out.println("MAKEUP GAS LOA query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_LOA_MAKEUPGAS_DTL(CUSTOMER_CD, FGSA_NO, " +
							 "FGSA_REV_NO,LOA_NO,LOA_REV_NO,CONT_TYPE,MAKEUP_PERIOD, " +
							 "RECOVERY_PERIOD,PRICE_PER,PRICE,REMARKS,FLAG,EMP_CD,ENT_DT) " +
							 "VALUES("+BUYER_NO+","+FGSANO+",'0',"+SNNO+","+SN_REVNO+", 'L', " +
							 ""+rset.getString(1)+","+rset.getString(2)+", "+rset.getString(3)+", " +
							 ""+rset.getString(4)+",'"+obj.replaceSingleQuotes(rset.getString(5))+"', " +
							 "'"+rset.getString(6)+"','"+user_cd+"', sysdate)";
					//System.out.println("Insert MUG LOA query = "+query);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT MAKEUP_PERIOD,RECOVERY_PERIOD,PRICE_PER,PRICE,REMARKS,FLAG " +
							"FROM FMS7_TENDER_MAKEUPGAS_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND TENDER_NO='"+FGSANO+"' AND " +
							"CONT_TYPE='T'";			
					//System.out.println("MAKEUP GAS TENDER query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_LOA_MAKEUPGAS_DTL(CUSTOMER_CD,FGSA_NO, " +
								 "FGSA_REV_NO,LOA_NO,LOA_REV_NO,CONT_TYPE,MAKEUP_PERIOD, " +
								 "RECOVERY_PERIOD, PRICE_PER, PRICE,REMARKS,FLAG,EMP_CD,ENT_DT) " +
								 "VALUES("+BUYER_NO+", "+FGSANO+",'0',"+SNNO+", "+SN_REVNO+",'L'," +
								 ""+rset2.getString(1)+","+rset2.getString(2)+", "+rset2.getString(3)+", " +
								 ""+rset2.getString(4)+",'"+obj.replaceSingleQuotes(rset2.getString(5))+"', " +
								 "'"+rset2.getString(6)+"','"+user_cd+"',sysdate)";
						//System.out.println("INSERT MUG LOA query = "+query);
						stmt1.executeUpdate(query1);
					}
				}
				
				//For Fetching LOA Billing Details Of Previous Revision And Inserting All These Values Into New Revision ...
				query = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
						"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
						"FLAG, EXCHG_RATE_NOTE FROM FMS7_SN_BILLING_DTL " +
						"WHERE SN_NO='"+SNNO+"' AND " +
						"SN_REV_NO='"+tmp_SN_REVNO+"' AND " +
						"CUSTOMER_CD='"+BUYER_NO+"' AND " +
						"FGSA_NO='"+FGSANO+"' AND " +
						"FGSA_REV_NO='0' AND CONT_TYPE='L'";				
				//System.out.println("BILLING LOA query = "+query);
				rset = stmt.executeQuery(query);				
				if(rset.next())
				{
					query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, SN_NO, SN_REV_NO, " +
							"CONT_TYPE, BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"EMP_CD, ENT_DT, FLAG, EXCHG_RATE_NOTE) " +
							"VALUES("+BUYER_NO+", "+FGSANO+", '0', "+SNNO+", "+SN_REVNO+", 'S', " +
							"'"+rset.getString(1)+"', "+rset.getString(2)+", "+rset.getString(3)+", " +
							"'"+rset.getString(4)+"', "+rset.getString(5)+", "+rset.getString(6)+", " +
							"'"+rset.getString(7)+"', "+rset.getString(8)+", "+rset.getString(9)+", " +
							""+rset.getString(10)+", '"+user_cd+"', sysdate, '"+rset.getString(11)+"', " +
							"'"+obj.replaceSingleQuotes(rset.getString(12))+"')";			
					//System.out.println("Insert Query For LOA Billing Details = "+query1);
					stmt1.executeUpdate(query1);
				}
				else
				{
					query2 = "SELECT BILLING_FREQ, DUE_DATE, INT_CAL_RATE_CD, INT_CAL_SIGN, INT_CAL_PERCENTAGE, " +
							"EXCHNG_RATE_CD, EXCHNG_RATE_CAL, INVOICE_CUR_CD, PAYMENT_CUR_CD, TAX_STRUCT_CD, " +
							"FLAG, EXCHG_RATE_NOTE FROM FMS7_FGSA_BILLING_DTL " +
							"WHERE CUSTOMER_CD='"+BUYER_NO+"' AND " +
							"FGSA_NO='"+FGSANO+"' AND " +
							"FGSA_REV_NO='0' AND CONT_TYPE='T'";					
					//System.out.println("LOA BILLING TENDER query2 = "+query2);
					rset2 = stmt2.executeQuery(query2);					
					if(rset2.next())
					{
						query1 = "INSERT INTO FMS7_SN_BILLING_DTL(CUSTOMER_CD,FGSA_NO,SN_NO,SN_REV_NO,FGSA_REV_NO, " +
								"CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE, " +
								"EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD,TAX_STRUCT_CD, " +
								"EMP_CD,ENT_DT,FLAG,EXCHG_RATE_NOTE) " +
								"VALUES("+BUYER_NO+", "+FGSANO+","+SNNO+","+SN_REVNO+",'0','L', " +
								"'"+rset2.getString(1)+"', "+rset2.getString(2)+", "+rset2.getString(3)+", " +
								"'"+rset2.getString(4)+"', "+rset2.getString(5)+", "+rset2.getString(6)+", " +
								"'"+rset2.getString(7)+"', "+rset2.getString(8)+", "+rset2.getString(9)+", " +
								""+rset2.getString(10)+", '"+user_cd+"', sysdate, '"+rset2.getString(11)+"', " +
								"'"+obj.replaceSingleQuotes(rset2.getString(12))+"')";				
						//System.out.println("LOA Billing Details Insert Query = "+query1);
						stmt1.executeUpdate(query1);
					}
				}				
				msg = "Revised LOA Detail Submitted - LOA No = "+SNNO+", Name="+sn_name+" For The Customer "+buyer_name+" !!!";
				message = msg;
			}
						
			//Data submission for the transporter
			query = "select count(*) from FMS7_LOA_TRANSPORTER_MST where " +
					"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and " +
					"CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_LOA_TRANSPORTER_MST where " +
						"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and " +
						"CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_trans!=null)
			{
				for(int i=0;i<chk_trans.length;i++)
				{
					query = "insert into FMS7_LOA_TRANSPORTER_MST(LOA_NO,LOA_REV_NO,TENDER_NO,CUSTOMER_CD,TRANSPORTER_CD,EMP_CD,ENT_DT,FLAG) " +
							"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+chk_trans[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				}
			}				
			
			//Data submissin for the delivery points
			query = "select count(*) from FMS7_LOA_PLANT_MST where " +
					"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and " +
					"CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_LOA_PLANT_MST where " +
						"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and " +
						"CUSTOMER_CD='"+BUYER_NO+"' and TENDER_NO='"+FGSANO+"' ";
				stmt.executeUpdate(query);
			}
			if(chk_delv!=null)
			{
				for(int i=0;i<chk_delv.length;i++)
				{
					query ="insert into FMS7_LOA_PLANT_MST(LOA_NO,LOA_REV_NO,TENDER_NO,CUSTOMER_CD,PLANT_SEQ_NO,EMP_CD,ENT_DT,FLAG) " +
							" values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+chk_delv[i]+"','"+user_cd+"',sysdate,'T') ";
					stmt.executeUpdate(query);
				 }
			}				
			
			//Data submissin for the Clauses
			query = "select count(*) from FMS7_LOA_CLAUSE_MST where " +
					"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and " +
					"TENDER_NO='"+FGSANO+"' ";
			rset = stmt.executeQuery(query);
			count = 0;
			if(rset.next())
			{
				 count = rset.getInt(1);
			}
			if(count>0)
			{
				query = "delete from FMS7_LOA_CLAUSE_MST where " +
						"LOA_NO='"+SNNO+"' and LOA_REV_NO='"+SN_REVNO+"' and BUYER_CD='"+BUYER_NO+"' and " +
						"TENDER_NO='"+FGSANO+"' ";
				stmt.executeUpdate(query);
			}
			if(clause_nm!=null)
			{
				for(int i=0;i<clause_nm.length; i++)
				{
					query = "insert into FMS7_LOA_CLAUSE_MST(LOA_NO, LOA_REV_NO,TENDER_NO,BUYER_CD,CLAUSE_CD,EMP_CD,ENT_DT,FLAG) " +
							"values('"+SNNO+"','"+SN_REVNO+"','"+FGSANO+"','"+BUYER_NO+"','"+clause_nm[i]+"','"+user_cd+"',sysdate,'T') ";
					//System.out.println(query);
					stmt.executeUpdate(query);
				}
			}	
			msg = "New LOA Revision Submitted Along With Closure Request !!!";
			url="../contract_master/frm_LOA_master.jsp?msg="+msg+"&flg=update&activity=update&LOA_CD="+SNNO+"&LOA_REVNO="+SN_REVNO+"&TENDER_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="LOA Closure Request Not Submitted !!!";
			System.out.println("Exception in the LOAMaster() Method of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();			
			url="../contract_master/frm_LOA_master.jsp?msg="+msg+"&flg=update&activity=update&LOA_CD="+SNNO+"&LOA_REVNO="+SN_REVNO+"&TENDER_CD="+FGSANO+"&bscode="+buyer_cd+"&buyer_cd="+buyer_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;	
		}
	}
	
	
	public void modifyLoaTcqCargoDetails(HttpServletRequest request) throws SQLException
	{
		methodName = "modifySnTcqCargoDetails()";
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
								
		String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
		String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
		String fgsa_cd = request.getParameter("fgsa_cd")==null?"":request.getParameter("fgsa_cd");
		String fgsa_revno = request.getParameter("fgsa_revno")==null?"0":request.getParameter("fgsa_revno");
		String sn_cd = request.getParameter("sn_cd")==null?"":request.getParameter("sn_cd");
		String sn_revno = request.getParameter("sn_revno")==null?"":request.getParameter("sn_revno");
		String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
		String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String dummy_cargo_flag = request.getParameter("dummy_cargo_flag")==null?"":request.getParameter("dummy_cargo_flag");
		
		String sale_price = request.getParameter("sale_price")==null?"0":request.getParameter("sale_price");
		String tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
		String tcq_sign = request.getParameter("tcq_sign")==null?"+":request.getParameter("tcq_sign");
		String sn_closure_flag = request.getParameter("sn_closure_flag")==null?"N":request.getParameter("sn_closure_flag");
		String sn_closure_dt = request.getParameter("sn_closure_dt")==null?"":request.getParameter("sn_closure_dt");
		String tcq_approval_dt = request.getParameter("tcq_approval_dt")==null?"":request.getParameter("tcq_approval_dt");
		String split_tcq_flag = request.getParameter("split_tcq_flag")==null?"N":request.getParameter("split_tcq_flag");
		String firm_qty = request.getParameter("firm_qty")==null?"0":request.getParameter("firm_qty");
		String re_qty = request.getParameter("re_qty")==null?"0":request.getParameter("re_qty");
		String hlpl_own_tcq_qty = request.getParameter("hlpl_own_tcq_qty")==null?"0":request.getParameter("hlpl_own_tcq_qty");
		String eff_dt_1 = request.getParameter("eff_dt_1")==null?"":request.getParameter("eff_dt_1");
		String own_cargo_flag = request.getParameter("own_cargo_flag")==null?"":request.getParameter("own_cargo_flag");
		
		try
		{
			String dummy_cargo_year = "";
			if(tcq_approval_dt.trim().length()>=10)
			{
				dummy_cargo_year = tcq_approval_dt.trim().substring(6);
			}
			String [] chk_flg = request.getParameterValues("chk_flg");
			String [] cargo_ref_no = request.getParameterValues("cargo_ref_no");
			String [] tcq_qty = request.getParameterValues("tcq_qty");
			String [] cost_price = request.getParameterValues("cost_price");
			
			String dummy_tcq_qty = request.getParameter("dummy_tcq_qty")==null?"0":request.getParameter("dummy_tcq_qty");
			String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
			
			int sn_rev_no = 0;
			int count = 0;
			double var_tcq = 0;
			int seq_no = 1; //For Generating Max SEQ NO If Dummy Cargo Volume Needs To Be Utilized ... 
						
			for(int i=0; i<chk_flg.length; i++)
			{
				if(chk_flg[i].equalsIgnoreCase("Y"))
				{
					sn_rev_no = 0;
					queryString = "SELECT MAX(LOA_REV_NO) " +
								  "FROM FMS7_LOA_CARGO_DTL WHERE CUSTOMER_CD="+buyer_cd+" And TENDER_NO="+fgsa_cd+" AND "+
								  "LOA_NO="+sn_cd+" AND CARGO_REF_NO="+cargo_ref_no[i]+"";					
					//System.out.println("Fetching Max LOA Rev NO for Cargo Allocation LOA TCQ Details Modification = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).equals(""))
						{
							sn_rev_no = Integer.parseInt(rset.getString(1))+1;
						}
					}
				
					if(tcq_sign.equals("-"))
					{
						tcq_qty[i] = "-"+tcq_qty[i];
					}
					
					query = "INSERT INTO FMS7_LOA_CARGO_DTL(CUSTOMER_CD,TENDER_NO," +
							"LOA_NO,LOA_REV_NO,CARGO_REF_NO,ALLOC_QTY,QTY_UNIT,SALE_PRICE, " +
							"COST_PRICE,RATE_UNIT,STATUS,EMP_CD,ENT_DT,FLAG) " +
							"VALUES("+buyer_cd+","+fgsa_cd+","+sn_cd+","+sn_rev_no+"," +
							""+cargo_ref_no[i]+","+tcq_qty[i]+",1,"+sale_price+"," +
							""+cost_price[i]+",'2','Y',"+user_cd+",sysdate,'T') ";
					//System.out.println("Query For Inserting Modified LOA TCQ Details = "+query);
					stmt.executeUpdate(query);
					var_tcq += Double.parseDouble(tcq_qty[i]);
					++count;
				}
			}
			
			if(dummy_tcq_qty.trim().equals(""))
			{
				dummy_tcq_qty = "0";
			}
			
			if(dummy_cargo_flag.equalsIgnoreCase("DUMMY") && Double.parseDouble(dummy_tcq_qty)>0.99 && dummy_cargo_year.trim().length()==4)
			{
				queryString = "SELECT MAX(SEQ_NO) FROM FMS7_DUMMY_CARGO_DTL " +
							  "WHERE YEAR="+dummy_cargo_year.trim()+"";				
				//System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1)!=null && !rset.getString(1).equals(""))
					{
						seq_no = Integer.parseInt(rset.getString(1))+1;
					}
				}
				
				String dummy_cost_price = null;
				
				if(tcq_sign.equals("-"))
				{
					dummy_tcq_qty = "-"+dummy_tcq_qty;
				}
				
				String dummy_price = "0";
				double conf_price = 0;
				double conf_vol = 0;
				double total_conf_vol = 0;
				double total_of_product_vol_price = 0;
				
				query = "SELECT NVL(B.confirm_price,'0'),NVL(A.price,'0')," +
						"NVL(A.confirm_vol,'0'),NVL(A.unit_cd,'1')," +
						"NVL(A.cargo_ref_cd,'0'),TO_CHAR(A.delv_from_dt,'dd/mm/yyyy') " +
						"FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_MAN_CONFIRM_MST B " +
						"WHERE A.man_conf_cd=B.man_conf_cd AND A.man_cd=B.man_cd AND " +
						"A.cargo_status='T' AND A.delv_from_dt BETWEEN " +
						"TO_DATE('01/01/"+dummy_cargo_year.trim()+"','dd/mm/yyyy') AND " +
						"TO_DATE('"+tcq_approval_dt.trim()+"','dd/mm/yyyy')";
				rset = stmt.executeQuery(query);
				
				while(rset.next())
				{
					String delv_to_dt = rset.getString(6)==null?"":rset.getString(6);
					int cargo_ref_number = rset.getInt(5);
					conf_price = rset.getDouble(2);
					if(conf_price<=0.01)
					{
						conf_price = rset.getDouble(1);
					}
					
					queryString = "SELECT NVL(qty_mmbtu,'0') FROM FMS7_CARGO_QQ_DTL " +
								  "WHERE cargo_ref_no="+cargo_ref_number+" AND SPLIT_SEQ='0'";
					rset1 = stmt1.executeQuery(queryString);
					
					if(rset1.next())
					{
						conf_vol = rset1.getDouble(1);
						
						if(conf_vol<=0.99)
						{
							conf_vol = rset.getDouble(3);
							
							if(rset.getString(4).trim().equals("2"))
							{
								conf_vol *= 1000000;
							}
						}
					}
					else
					{
						conf_vol = rset.getDouble(3);
						
						if(rset.getString(4).trim().equals("2"))
						{
							conf_vol *= 1000000;
						}
					}
					
					//Following Logic Has Been Introduced By Samik Shah On 28th April, 2011 ...
					//Following Logic Has Been Introduced To Calculate Custom Tax Amount ...
				  	String tax_amt = "";
				  	String tax_str_cd = "0";
				  	double cd_charge_per_mmbtu = 0;
					
					String queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
								  		  "WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B " +
								  		  "WHERE B.APP_DATE<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
					//System.out.println("Custom Duty Details Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
					}
					
					//System.out.println("tax_str_cd = "+tax_str_cd);
					
					queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
								   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
								   "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
					//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						if(rset1.getString(3).equals("1"))
						{
							tax_amt = nf3.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset1.getString(2)))/100);
						}
						else if(rset1.getString(3).equals("2"))
						{
							String queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										   		  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
										   		  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
										   		  "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
							//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2);
					 		rset2=stmt2.executeQuery(queryString2);
					 		if(rset2.next())
					 		{
					 			if(rset2.getString(3).equals("1"))
								{
									tax_amt = nf3.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset2.getString(2)))/100);
								}
								
					 			tax_amt = nf3.format((Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100);
					 		}
					 		else
					 		{
					 			tax_amt = ""+0.00;
					 		}			 		
						}
						else
						{
							tax_amt = ""+0.00;
						}
						
						cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
					}
					
					total_conf_vol += conf_vol;
					total_of_product_vol_price += (conf_vol*(conf_price+cd_charge_per_mmbtu));
				}
				
				if(total_conf_vol>0)
				{
					dummy_price = nf2.format(total_of_product_vol_price/total_conf_vol);
				}
				
				if(Double.parseDouble(dummy_price)<=0.01)
				{
					int prev_year_max_seq_no = 0;
					queryString = "SELECT MAX(SEQ_NO) FROM FMS7_DUMMY_CARGO_DTL " +
								  "WHERE YEAR="+(Integer.parseInt(dummy_cargo_year.trim())-1)+"";				
					//System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).trim().equals(""))
						{
							prev_year_max_seq_no = Integer.parseInt(rset.getString(1).trim());
						}
					}
					
					queryString = "SELECT NVL(DUMMY_PRICE,'0') FROM FMS7_DUMMY_CARGO_DTL " +
								  "WHERE YEAR="+(Integer.parseInt(dummy_cargo_year.trim())-1)+" AND " +
								  "SEQ_NO="+prev_year_max_seq_no+"";				
					//System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).trim().equals(""))
						{
							dummy_price = nf2.format(Double.parseDouble(rset.getString(1).trim()));
						}
					}
				}
				
				query = "INSERT INTO FMS7_DUMMY_CARGO_DTL(SEQ_NO, CUSTOMER_CD, FGSA_NO, FGSA_REV_NO, " +
						"SN_NO, ALLOC_QTY, QTY_UNIT, SALE_PRICE, " +
						"COST_PRICE, RATE_UNIT, STATUS, EFF_DT, EMP_CD, ENT_DT, FLAG, " +
						"YEAR, CONTRACT_TYPE, DUMMY_PRICE, TRANSACTION_DT) " +
						"VALUES("+seq_no+","+buyer_cd+","+fgsa_cd+","+fgsa_revno+"," +
						""+sn_cd+","+dummy_tcq_qty+",1,"+sale_price+"," +
						""+dummy_cost_price+",'2','Y',TO_DATE('"+eff_dt+"','DD/MM/YYYY')," +
						""+user_cd+",sysdate,'T',"+dummy_cargo_year.trim()+"," +
						"'L',"+dummy_price+",TO_DATE('"+tcq_approval_dt.trim()+"','dd/mm/yyyy'))";
				
				//System.out.println("Query For Inserting Modified LOA TCQ Details Under Dummy Cargo = "+query);
				stmt.executeUpdate(query);
				
				var_tcq += Double.parseDouble(dummy_tcq_qty);
				++count;
			}		
			
			//RS 09052017
			
			if(own_cargo_flag.equalsIgnoreCase("OWN VOLUME ACCOUNT") && Double.parseDouble(hlpl_own_tcq_qty)>=0.99 && dummy_cargo_year.trim().length()==4)
			{
				queryString = "SELECT MAX(SEQ_NO) FROM FMS8_OWN_CARGO_DTL " +
							  "WHERE YEAR="+dummy_cargo_year.trim()+"";				
//				System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1)!=null && !rset.getString(1).equals(""))
					{
						seq_no = Integer.parseInt(rset.getString(1))+1;
					}
				}
				
				if(tcq_sign.equals("-"))
				{
					hlpl_own_tcq_qty = "-"+hlpl_own_tcq_qty;
				}
				
				String dummy_price = "0";
				
				if(Double.parseDouble(dummy_price)<=0.01)
				{
					int prev_year_max_seq_no = 0;
					queryString = "SELECT MAX(SEQ_NO) FROM FMS7_DUMMY_CARGO_DTL " +
								  "WHERE YEAR="+(Integer.parseInt(dummy_cargo_year.trim())-1)+"";				
//					System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).trim().equals(""))
						{
							prev_year_max_seq_no = Integer.parseInt(rset.getString(1).trim());
						}
					}
					
					queryString = "SELECT NVL(DUMMY_PRICE,'0') FROM FMS7_DUMMY_CARGO_DTL " +
								  "WHERE YEAR="+(Integer.parseInt(dummy_cargo_year.trim())-1)+" AND " +
								  "SEQ_NO="+prev_year_max_seq_no+"";				
//					System.out.println("Dummy Cargo Max SEQ NO Generation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1)!=null && !rset.getString(1).trim().equals(""))
						{
							dummy_price = nf2.format(Double.parseDouble(rset.getString(1).trim()));
						}
					}
				}
				
				query = "INSERT INTO FMS8_OWN_CARGO_DTL(SEQ_NO, CUSTOMER_CD, FGSA_NO, " +
						"FGSA_REV_NO, SN_NO, ALLOC_QTY, QTY_UNIT, SALE_PRICE, " +
						"COST_PRICE, RATE_UNIT, STATUS, EFF_DT, EMP_CD, ENT_DT, FLAG, " +
						"YEAR, CONTRACT_TYPE, DUMMY_PRICE, TRANSACTION_DT) " +
						"VALUES("+seq_no+","+buyer_cd+","+fgsa_cd+","+fgsa_revno+"," +
						""+sn_cd+","+hlpl_own_tcq_qty+",1,"+sale_price+"," +
						"'0','2','Y',TO_DATE('"+eff_dt_1+"','DD/MM/YYYY')," +
						""+user_cd+",sysdate,'T',"+dummy_cargo_year.trim()+",'L'," +
						""+dummy_price+",TO_DATE('"+tcq_approval_dt.trim()+"','dd/mm/yyyy'))";
				
//				System.out.println("Query For Inserting Modified SN TCQ Details Under Dummy Cargo = "+query);
				stmt.executeUpdate(query);
				
				var_tcq += Double.parseDouble(hlpl_own_tcq_qty);
				++count;
			}
			
			if(tcq.trim().equals(""))
			{
				tcq = "0";
			}			
			var_tcq += Double.parseDouble(tcq);			
			String modified_tcq = nf.format(var_tcq);	
			if(tcq_sign.equals("-"))
			{
				firm_qty = "-"+firm_qty;
				re_qty = "-"+re_qty;
			}
			if(sn_closure_flag.equalsIgnoreCase("N"))
			{
				queryString = "UPDATE FMS7_LOA_MST SET TCQ="+modified_tcq+", LOA_CLOSURE_FLAG='N',"
						+ "FIRM_QTY = FIRM_QTY + '"+firm_qty+"', RE_QTY= RE_QTY + '"+re_qty+"' " +
						  	  "WHERE CUSTOMER_CD="+buyer_cd+" And TENDER_NO="+fgsa_cd+" AND " +
						  	  "LOA_NO="+sn_cd+" AND LOA_REV_NO="+sn_revno+"";
				if(count>0)
				{
					msg = "TCQ Modification Details Submited Successfully For LOA "+sn_cd+" Of Customer: "+buyer_name;
				}
				else
				{
					msg = "TCQ Modification Details NOT Submited Because: Any One Of The Mandatory Information Is Missing For LOA "+sn_cd+" Of Customer: "+buyer_name;
				}
			}
			else
			{
				queryString = "UPDATE FMS7_LOA_MST SET TCQ="+modified_tcq+", LOA_CLOSURE_QTY="+tcq+", " +
							  "LOA_CLOSURE_FLAG='Y', LOA_CLOSURE_DT=TO_DATE('"+sn_closure_dt+"','DD/MM/YYYY')," +
							  "LOA_CLOSURE_CLOSE='Y', " 
							  + "FIRM_QTY = FIRM_QTY + '"+firm_qty+"', RE_QTY= RE_QTY + '"+re_qty+"' " +
						  	  "WHERE CUSTOMER_CD="+buyer_cd+" And TENDER_NO="+fgsa_cd+" AND " +
						  	  "LOA_NO="+sn_cd+" AND LOA_REV_NO="+sn_revno+"";
				if(count>0)
				{
					msg = "LOA Closed Successfully For LOA "+sn_cd+" Of Customer: "+buyer_name;
				}
				else
				{
					msg = "LOA Not Closed Because: Any One Of The Mandatory Information Is Missing For LOA "+sn_cd+" Of Customer: "+buyer_name;
				}
			}			
			//System.out.println("Updation Query To Modify TCQ Of LOA Master = "+queryString);
			stmt.executeUpdate(queryString);			
			if(tcq_sign.equals("-"))
			{
				tcq_sign = "MINUS";
			}
			else if(tcq_sign.equals("+"))
			{
				tcq_sign = "PLUS";				
			}
			else
			{
				tcq_sign = "PLUS";
			}			
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
			e.printStackTrace();
			msg="Loa TCQ Details NOT Submited For The Selected LOA !!!";
			dbcon.rollback();
		}			
		url="../contract_master/frm_loa_tcq_modification.jsp?msg="+msg+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&fgsa_cd="+fgsa_cd+"&var_tcq=0&buyer_cd="+buyer_cd+"&buyer_name="+buyer_name+"&start_dt="+start_dt+"&end_dt="+end_dt+"&sale_price="+sale_price+"&tcq="+tcq+"&tcq_sign="+tcq_sign+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&form_id="+form_id+"&form_nm="+form_nm;
	}//End Of Method modifyLoaTcqCargoDetails() ...
	
	
	//Following method has been added by Priyanka on 23rd March, 2011.......
	public void close_REGAS_CARGO_DTL(HttpServletRequest request) throws Exception
	{
		methodName = "close_REGAS_CARGO_DTL()";
		escapeSingleQuotes obj = new escapeSingleQuotes();
		HttpSession session = request.getSession();
		
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");	    	    
		String buyer_nm = request.getParameter("buyer_nm")==null?"":request.getParameter("buyer_nm");	    	    
		String re_gas_no = request.getParameter("re_gas_no")==null?"":request.getParameter("re_gas_no");	    	    
		String cargo_seq_no = request.getParameter("cargo_seq_no")==null?"":request.getParameter("cargo_seq_no");	    	    
		String no_of_cargo = request.getParameter("no_of_cargo")==null?"":request.getParameter("no_of_cargo");	    	    
		//String closure_dt[] = request.getParameterValues("closure_dt");//==null?"sysdate":request.getParameter("closure_dt");	    	    
		//String closure_qty[] = request.getParameterValues("closure_qty");//==null?"0":request.getParameter("closure_qty");
		
		int cargo_no = Integer.parseInt(cargo_seq_no);
		String close_dt = request.getParameter("closure_dt")==null?"sysdate":request.getParameter("closure_dt");
		String close_qty = request.getParameterValues("closure_qty")==null?"0":request.getParameter("closure_qty");
		/*for(int i=0;i<closure_dt.length;i++)
		{
			if((cargo_no-1)==i)
			{
				close_dt = closure_dt[i];
				close_qty = closure_qty[i];
			}
		}*/
		
		//System.out.println("close_dt"+close_dt);
		//System.out.println("close_qty"+close_qty);
		
		try
		{
			//System.out.println("bEFORE CLOSURE APPLY QUERY >>>>>>>>>>>>>>>>"+close_dt);
			query = "UPDATE FMS7_RE_GAS_CARGO_DTL SET " +
					"REGAS_CLOSURE_REQUEST='Y', REGAS_CLOSURE_CLOSE='Y', REGAS_CLOSURE_FLAG='Y'," +
					"REGAS_CLOSURE_DT=TO_DATE('"+close_dt+"','DD/MM/YYYY'), REGAS_CLOSURE_QTY='"+close_qty+"' WHERE CUSTOMER_CD='"+buyer_cd+"' " +
					"AND RE_GAS_NO='"+re_gas_no+"' AND CARGO_SEQ_NO='"+cargo_seq_no+"' ";
			//System.out.println("FMS7_REGAS_CARGO_DTL Update Query for Closure Request = "+query);
			stmt.executeUpdate(query);			
			msg="RE-GAS for Cargo No. "+cargo_seq_no+" Closed Successfully!!!";
			url="../contract_master/frm_RE_GAS_Closure_req.jsp?buyer_cd="+buyer_cd+"&buyer_nm="+buyer_nm+"&re_gas_no="+re_gas_no+"&no_of_cargo="+no_of_cargo+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="RE-GAS for Cargo No. "+cargo_seq_no+" Not Closed !!!";
			System.out.println("Exception in the close_REGAS_CARGO_DTL() Method of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();			
			url="../contract_master/frm_RE_GAS_Closure_req.jsp?buyer_cd="+buyer_cd+"&buyer_nm="+buyer_nm+"&re_gas_no="+re_gas_no+"&no_of_cargo="+no_of_cargo+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}		
	}
	
    //Following method has been added by Priyanka on 23rd March, 2011.......
	public void update_REGAS_CARGO_DTL(HttpServletRequest request) throws Exception
	{
		methodName = "update_REGAS_CARGO_DTL()";
		escapeSingleQuotes obj = new escapeSingleQuotes();
		HttpSession session = request.getSession();
		
		String buyer_cd = request.getParameter("buyer_cd")==null?"":request.getParameter("buyer_cd");	    	    
		String buyer_nm = request.getParameter("buyer_nm")==null?"":request.getParameter("buyer_nm");	    	    
		String re_gas_no = request.getParameter("re_gas_no")==null?"":request.getParameter("re_gas_no");	    	    
		String cargo_seq_no = request.getParameter("cargo_seq_no")==null?"":request.getParameter("cargo_seq_no");	    	    
		String no_of_cargo = request.getParameter("no_of_cargo")==null?"":request.getParameter("no_of_cargo");	    	    		
		try
		{
			query = "UPDATE FMS7_RE_GAS_CARGO_DTL SET " +
					"REGAS_CLOSURE_REQUEST='Y' WHERE CUSTOMER_CD='"+buyer_cd+"' " +
					"AND RE_GAS_NO='"+re_gas_no+"' AND CARGO_SEQ_NO='"+cargo_seq_no+"' ";
			//System.out.println("FMS7_REGAS_CARGO_DTL Update Query for Closure Request = "+query);
			stmt.executeUpdate(query);
			
			msg="RE-GAS Closure Request Submitted for Cargo No. "+cargo_seq_no+"!!!";
			url="../contract_master/frm_RE_GAS_Cargo_dtl.jsp?buyer_cd="+buyer_cd+"&buyer_nm="+buyer_nm+"&re_gas_no="+re_gas_no+"&no_of_cargo="+no_of_cargo+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			//System.out.println(url);
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
			dbcon.rollback();
			msg="RE-GAS Closure Request Not Submitted !!!";
			System.out.println("Exception in the update_REGAS_CARGO_DTL() Method of "+servletName+" :\n"+e.getMessage());
			e.printStackTrace();			
			url="../contract_master/frm_RE_GAS_Cargo_dtl.jsp?buyer_cd="+buyer_cd+"&buyer_nm="+buyer_nm+"&re_gas_no="+re_gas_no+"&no_of_cargo="+no_of_cargo+"&msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}
	}
	
	//MD20120123 START
//Introduce by Milan Dalsaniya on 2011 nov 04 MD20111104
	
	public void insertModifyLCFinanceMasterREGAS(HttpServletRequest request) throws Exception
	{
		methodName = "insertModifyLCFinanceMasterREGAS()";
		//System.out.println("inside insertModifyLCFinanceMasterREGAS");
		escapeSingleQuotes obj = new  escapeSingleQuotes();
		HttpSession session = request.getSession();
		String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
		//Mflg = "ROMOVE_tabpending"
		String Mflg = request.getParameter("Mflg")==null?"":request.getParameter("Mflg");
		String lc_fin_yr = request.getParameter("lc_fin_yr")==null?"":request.getParameter("lc_fin_yr");
		String lc_seq_no  = request.getParameter("lc_seq_no")==null?"":request.getParameter("lc_seq_no"); 		
		String seq_no = request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		String lc_ref_no = request.getParameter("LC_Ref_No")==null?"":request.getParameter("LC_Ref_No");
		String customer_nm = request.getParameter("customer_name")==null?"":request.getParameter("customer_name");
		String bank_name = request.getParameter("bank_name")==null?"":request.getParameter("bank_name");
		String bank_cd = request.getParameter("bank_cd")==null?"":request.getParameter("bank_cd");
		String bank_rating = request.getParameter("bank_rating")==null?"":request.getParameter("bank_rating");
		String validity_st_dt = request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
		String validity_end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
		String mrkt_lc_amt = request.getParameter("Final_LC_Amount")==null?"":request.getParameter("Final_LC_Amount");
		String bank_lc_amt = request.getParameter("Actual_LC_Amount")==null?"":request.getParameter("Actual_LC_Amount");
		//String other_bank_dtl  = request.getParameter("other_bank_dtl")==null?"":request.getParameter("other_bank_dtl");
		
		String lc_from_dt = request.getParameter("lc_from_dt")==null?"":request.getParameter("lc_from_dt");
		String lc_to_dt = request.getParameter("lc_to_dt")==null?"":request.getParameter("lc_to_dt");
		
		String rating_eff_date = request.getParameter("rating_eff_dt")==null?"":request.getParameter("rating_eff_dt");
		String ship_dt = request.getParameter("ship_dt")==null?"":request.getParameter("ship_dt");
		String amendment_flag = request.getParameter("amendment_flag")==null?"":request.getParameter("amendment_flag");
		String amendment_dt = request.getParameter("amend_dt")==null?"":request.getParameter("amend_dt");
		String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");		
		String amendment_no = request.getParameter("amendment_no")==null?"0":request.getParameter("amendment_no");
		String lc_contract_cap = request.getParameter("lc_contract_cap")==null?"0":request.getParameter("lc_contract_cap");
		remarks = obj.replaceSingleQuotes(remarks);
		//customer_cd = "10";
		//System.out.println(">>>>>>>>>>>>> milan customer_cd = "+customer_cd);
		//System.out.println(">>>>>>>>>>>>> milan customer_nm amendment_no = "+customer_nm);
		//System.out.println("fROM SERVLET insertModifyLCFinanceMasterREGAS() CUSTOMER NAME = "+customer_cd);
		
		
		try
		{
			if(amendment_flag.trim().equalsIgnoreCase("Y"))
			{
				queryString = "SELECT MAX(AMENDMENT_NO) FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+lc_ref_no+"'   ";
				//System.out.println("Select Query For FMS7_LC_FINANCE_MASTER = "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					amendment_no= ""+(Integer.parseInt(rset.getString(1))+1);
				}
				//System.out.println("amendment_no = "+amendment_no);
				
					query2 = "INSERT INTO FMS7_LC_FINANCE_MST(BANK_LC_NO, AMENDMENT_NO, " +
					"FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, BANK_CD, CUSTOMER_NM, BANK_NM, " +
					"BANK_RATING, RATING_EFF_DATE, VALIDITY_START_DATE, VALIDITY_END_DATE, " +
					"MRKT_LC_AMOUNT, BANK_LC_AMOUNT, LAST_SHIPMENT_DATE, AMENDMENT_DATE, " +
					"AMENDMENT_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG) VALUES ('"+lc_ref_no+"'" +
					" ,'"+amendment_no+"','"+lc_fin_yr+"','"+lc_seq_no+"','"+customer_cd+"','"+bank_cd+"'," +
					"'"+customer_nm+"','"+bank_name+"','"+bank_rating+"', " +
					"to_date('"+rating_eff_date+"','dd/mm/yyyy'),  to_date('"+validity_st_dt+"','dd/mm/yyyy'), " +
					"to_date('"+validity_end_dt+"','dd/mm/yyyy'), "+mrkt_lc_amt+", "+bank_lc_amt+", " +
					"to_date('"+ship_dt+"','dd/mm/yyyy'),to_date('"+amendment_dt+"','dd/mm/yyyy'), '"+amendment_flag+"', " +
					"'"+remarks+"', "+user_cd+",sysdate,'R') ";
					//System.out.println("Insert Query For FMS7_LC_FINANCE_MASTER = "+query2);
					stmt1.executeUpdate(query2);
				bank_name = bank_name.replaceAll("#","_");
				dbcon.commit();
				msg="LC Details Submited Successfully !!!";
				//url="../accounting/frm_LC_monitoring_regas.jsp?lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"&amendment_dt="+amendment_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&msg="+msg;
				url="../accounting/frm_LC_monitoring_regas.jsp?" +
				"lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"" +
				"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"" +
				"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"" +
				"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"" +
				"&amendment_dt="+amendment_dt+"&write_permission="+write_permission+"" +
				"&delete_permission="+delete_permission+"&print_permission="+print_permission+"" +
				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"" +
				
				"&lc_fin_yr="+lc_fin_yr+"&lc_seq_no="+lc_seq_no+"" +
				"&customer_name="+customer_nm+"&bank_name="+bank_name+"" +
				"&bank_rating="+bank_rating+"" +
				"&validity_st_dt="+validity_st_dt+"&mrkt_lc_amt="+mrkt_lc_amt+"" +
				"&validity_end_dt="+validity_end_dt+"&rating_eff_date="+rating_eff_date+"" +
				"&bank_lc_amt="+bank_lc_amt+"&amendment_flag="+amendment_flag+"" +
				"&amend_dt="+amendment_dt+"&amendment_no="+amendment_no+"" +
				"&remarks="+remarks+"&user_cd="+user_cd+"" +
				"&msg="+msg+"&Mflg="+Mflg+"&ship_dt="+ship_dt+"&lc_contract_cap="+lc_contract_cap;
			}
			else
			{
				queryString = "SELECT * FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+lc_ref_no+"' AND AMENDMENT_NO='"+amendment_no+"'  ";
				//System.out.println("Select Query For FMS7_LC_FINANCE_MASTER = "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					query1 = "DELETE FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+lc_ref_no+"'AND AMENDMENT_NO='"+amendment_no+"'  ";
					//System.out.println("Delete Query For FMS7_LC_FINANCE_MASTER = "+query1);
					stmt1.executeUpdate(query1);
					msg="LC Details Updated Successfully From Accounts !!!";
				}	
				
				queryString = "INSERT INTO FMS7_LC_FINANCE_MST(BANK_LC_NO, AMENDMENT_NO, " +
								"FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, BANK_CD, CUSTOMER_NM, BANK_NM, " +
								"BANK_RATING, RATING_EFF_DATE, VALIDITY_START_DATE, VALIDITY_END_DATE, " +
								"MRKT_LC_AMOUNT, BANK_LC_AMOUNT, LAST_SHIPMENT_DATE, AMENDMENT_DATE, " +
								"AMENDMENT_FLAG, REMARKS, EMP_CD, ENT_DT, FLAG) VALUES ('"+lc_ref_no+"'" +
								" ,'"+amendment_no+"','"+lc_fin_yr+"','"+lc_seq_no+"','"+customer_cd+"','"+bank_cd+"'," +
								"'"+customer_nm+"','"+bank_name+"','"+bank_rating+"', " +
								"to_date('"+rating_eff_date+"','dd/mm/yyyy'),  to_date('"+validity_st_dt+"','dd/mm/yyyy'), " +
								"to_date('"+validity_end_dt+"','dd/mm/yyyy'), "+mrkt_lc_amt+", "+bank_lc_amt+", " +
								"to_date('"+ship_dt+"','dd/mm/yyyy'),to_date('"+amendment_dt+"','dd/mm/yyyy'), '"+amendment_flag+"', " +
								"'"+remarks+"', "+user_cd+",sysdate,'R') ";
				
				bank_name = bank_name.replaceAll("#","_");
				//System.out.println("Insert Query For FMS7_LC_FINANCE_MASTER = "+queryString+" "+bank_name);
				stmt.executeUpdate(queryString);
				dbcon.commit();					
				msg="LC Details for REGAS Submited Successfully From Accounts !!!";
				//url="../accounting/frm_LC_monitoring_regas.jsp?lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"&amendment_dt="+amendment_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&msg="+msg+"&Mflg="+Mflg;
				url="../accounting/frm_LC_monitoring_regas.jsp?" +
				"lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"" +
				"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"" +
				"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"" +
				"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"" +
				"&amendment_dt="+amendment_dt+"&write_permission="+write_permission+"" +
				"&delete_permission="+delete_permission+"&print_permission="+print_permission+"" +
				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"" +
				
				"&lc_fin_yr="+lc_fin_yr+"&lc_seq_no="+lc_seq_no+"" +
				"&customer_name="+customer_nm+"&bank_name="+bank_name+"" +
				"&bank_rating="+bank_rating+"" +
				"&validity_st_dt="+validity_st_dt+"&mrkt_lc_amt="+mrkt_lc_amt+"" +
				"&validity_end_dt="+validity_end_dt+"&rating_eff_date="+rating_eff_date+"" +
				"&bank_lc_amt="+bank_lc_amt+"&amendment_flag="+amendment_flag+"" +
				"&amendment_dt="+amendment_dt+"&amendment_no="+amendment_no+"" +
				"&remarks="+remarks+"&user_cd="+user_cd+"" +
				"&msg="+msg+"&Mflg="+Mflg+"&ship_dt="+ship_dt+"&lc_contract_cap="+lc_contract_cap;
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
			dbcon.rollback();
			System.out.println("Exception in insertModifyLC_FINANCE_MASTER() Method of Frm_Contract_Master Servlet:\n"+e.getMessage());
			e.printStackTrace();
			bank_name = bank_name.replaceAll("#","_");
			msg="LC Details NOT Submited Successfully !!!";
			//url="../accounting/frm_LC_monitoring_regas.jsp?lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"&amendment_dt="+amend_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&msg="+msg+"&Mflg="+Mflg;
			url="../accounting/frm_LC_monitoring_regas.jsp?" +
			"lc_from_dt="+lc_from_dt+"&lc_to_dt="+lc_to_dt+"" +
			"&bank_lc_no="+lc_ref_no+"&seq_no="+seq_no+"" +
			"&chkamend="+amendment_flag+"&customer_cd="+customer_cd+"" +
			"&amendment_no"+amendment_no+"&bank_cd="+bank_cd+"" +
			"&amendment_dt="+amendment_dt+"&write_permission="+write_permission+"" +
			"&delete_permission="+delete_permission+"&print_permission="+print_permission+"" +
			"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"" +
			
			"&lc_fin_yr="+lc_fin_yr+"&lc_seq_no="+lc_seq_no+"" +
			"&customer_name="+customer_nm+"&bank_name="+bank_name+"" +
			"&bank_rating="+bank_rating+"" +
			"&validity_st_dt="+validity_st_dt+"&mrkt_lc_amt="+mrkt_lc_amt+"" +
			"&validity_end_dt="+validity_end_dt+"&rating_eff_date="+rating_eff_date+"" +
			"&bank_lc_amt="+bank_lc_amt+"&amendment_flag="+amendment_flag+"" +
			"&amend_dt="+amendment_dt+"&amendment_no="+amendment_no+"" +
			"&remarks="+remarks+"&user_cd="+user_cd+"" +
			"&msg="+msg+"&Mflg="+Mflg+"&ship_dt="+ship_dt+"&lc_contract_cap="+lc_contract_cap;
		}
	
	
}
/////////////////////////////SB20200509: Cargo Price Change/////////////////
public void cargoModificationDtl2(HttpServletRequest request) throws Exception {
String contract_type = request.getParameter("contract_type")==null?"O":request.getParameter("contract_type");
String month = request.getParameter("month")==null?"0":request.getParameter("month");
String year = request.getParameter("year")==null?"0":request.getParameter("year");
String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
try {
msg="";
methodName = "cargoModificationDtl2()";
HttpSession session = request.getSession();
String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");

String selIndex = request.getParameter("selIndex")==null?"0":request.getParameter("selIndex");

int i = Integer.parseInt(selIndex);

String cargoSelected = request.getParameter("cargoSelected")==null?"0":request.getParameter("cargoSelected");
String cargoNewPrice = request.getParameter("cargoNewPrice")==null?"0":request.getParameter("cargoNewPrice");
String cargoProvPrice = request.getParameter("cargoProvPrice")==null?"0":request.getParameter("cargoProvPrice");
String cargoNewPriceDt = request.getParameter("cargoNewPriceDt")==null?"20":request.getParameter("cargoNewPriceDt");
String cargoNewPriceNoCD=request.getParameter("cargoNewPrice")==null?"0":request.getParameter("cargoNewPrice");
/*query = "UPDATE FMS7_MAN_CONFIRM_CARGO_DTL SET PRICE='"+cargoNewPrice+"' WHERE CARGO_REF_CD='"+cargoSelected+"'";
System.out.println("QRY-1A:Update: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
////SB		stmt.executeUpdate(query);*/
msg = "Cargo-"+cargoSelected+" Price Change ";
String fgsaNo=""; String fgsaRevNo=""; String snNo=""; String snRevNo=""; String allocQty=""; String salePrice=""; 
String costPrice=""; String margin=""; String avgMargin=""; String totMargin=""; String customerCd="";
double NewMargin=0; double NewTotMargin=0;
Vector VCAR_REF_NO_temp= new Vector();
Vector VfgsaNo= new Vector();
Vector VfgsaRevNo= new Vector();
Vector VsnNo= new Vector();
Vector VsnRevNo= new Vector();
Vector VcustCd= new Vector();
Vector VallocQty= new Vector();
Vector VcostPrice= new Vector();
Vector Vmargin= new Vector();
Vector VtotMargin= new Vector();
Vector VNewMargin= new Vector();
Vector VNewTotMargin= new Vector();
Vector VContType= new Vector();
Vector VaprvDt= new Vector();
Vector VContStartDt= new Vector();
Vector VContEndDt= new Vector();

/////////////////////////////Tax Calculation//////////////////////////////////
//Logic: On Price Change Request put the New Price(Final) to PRICE_TO. After Approval swap PRICE to PRICE_TO and PRICE_TO(Prov) to PRICE(Final).
query = "UPDATE FMS7_MAN_CONFIRM_CARGO_DTL SET PRICE_TO='"+cargoNewPrice+"', FINAL_PRICE_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY'), FINAL_PRICE_APRV='N' "
+ " WHERE CARGO_REF_CD='"+cargoSelected+"'";
System.out.println("QRY-1A:Update: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
stmt.executeUpdate(query);
String tax_str_cd=""; String tax_amt="0"; double cd_charge_per_mmbtu=0;
String queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
"WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B WHERE " +
"B.APP_DATE<=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY'))";
rset1 = stmt1.executeQuery(queryString1);
if(rset1.next())
{
tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
}				
queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
  "B.app_date<=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1); // MCL 20190307 Temp
rset1=stmt1.executeQuery(queryString1);
while(rset1.next())
{
String tax_cd = rset1.getString(1);
String tax_factor = nf.format(Double.parseDouble(rset1.getString(2)));
					
if(rset1.getString(3).equals("1"))
{
	tax_amt = ((Double.parseDouble(""+cargoNewPrice)*Double.parseDouble(rset1.getString(2)))/100)+"";

}
else if(rset1.getString(3).equals("2"))
{
 String	queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
				   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
				   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
				   "B.app_date<=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
	//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2); // MCL 20190307 Temp
		rset2=stmt2.executeQuery(queryString2);
		if(rset2.next())
		{
 			if(rset2.getString(3).equals("1"))
			{
				tax_amt = ((Double.parseDouble(""+cargoNewPrice)*Double.parseDouble(rset2.getString(2)))/100)+"";		
			}								
 			tax_amt = ((Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100)+"";
		}
		else
		{
			tax_amt = ""+0.00;
		}			 		
}
else
{
	tax_amt = ""+0.00;
}			
cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
}
System.out.println("CUSOM DUTY: "+cd_charge_per_mmbtu);System.out.println("cargoNewPrice "+cargoNewPrice);
cargoNewPriceNoCD =cargoNewPrice;
cargoNewPrice=nf2.format(Double.parseDouble(cargoNewPrice)+cd_charge_per_mmbtu);
//////////////////////////////////////////////////////////////////////////////
queryString = "SELECT B.FGSA_NO,B.FGSA_REV_NO,B.SN_NO,B.SN_REV_NO,ALLOC_QTY, SALE_PRICE, COST_PRICE, MARGIN, AVG_MARGIN,TOTAL_MARGIN,B.CUSTOMER_CD,TO_DATE(APRV_DT,'DD/MM/YYYY') "
+ ", TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') "+
" FROM FMS7_SN_CARGO_DTL B, FMS7_SN_MST C WHERE B.CARGO_REF_NO='"+cargoSelected+"' "//DELV_TO_DT >=TO_DATE('"+SearchDt+"','DD/MM/YYYY') "
+ "AND B.FGSA_NO=C.FGSA_NO AND B.FGSA_REV_NO=C.FGSA_REV_NO AND B.SN_NO=C.SN_NO AND B.SN_REV_NO=C.SN_REV_NO AND B.CUSTOMER_CD=C.CUSTOMER_CD ORDER BY SN_NO";
System.out.println("STEP-1:SN "+queryString);
rset = stmt.executeQuery(queryString);
while(rset.next()) {
fgsaNo=rset.getString(1)==null?"0":rset.getString(1);	
VfgsaNo.add(rset.getString(1)==null?"0":rset.getString(1));
fgsaRevNo=rset.getString(2)==null?"0":rset.getString(2);	
VfgsaRevNo.add(rset.getString(2)==null?"0":rset.getString(2));
snNo=rset.getString(3)==null?"0":rset.getString(3);
VsnNo.add(rset.getString(3)==null?"0":rset.getString(3));
snRevNo=rset.getString(4)==null?"0":rset.getString(4);
VsnRevNo.add(rset.getString(4)==null?"0":rset.getString(4));
allocQty=rset.getString(5)==null?"0":rset.getString(5);
VallocQty.add(rset.getString(5)==null?"0":rset.getString(5));
salePrice=rset.getString(6)==null?"0":rset.getString(6);
costPrice=rset.getString(7)==null?"0":rset.getString(7);
VcostPrice.add(rset.getString(7)==null?"0":rset.getString(7));
margin=rset.getString(8)==null?"0":rset.getString(8);
Vmargin.add(rset.getString(8)==null?"0":rset.getString(8));
avgMargin=rset.getString(9)==null?"0":rset.getString(9);
totMargin=rset.getString(10)==null?"0":rset.getString(10);
VtotMargin.add(rset.getString(10)==null?"0":rset.getString(10));
customerCd=rset.getString(11)==null?"0":rset.getString(11);
VcustCd.add(rset.getString(11)==null?"0":rset.getString(11));
VaprvDt.add(rset.getString(12)==null?"":rset.getString(12));
VContStartDt.add(rset.getString(13)==null?"":rset.getString(13));
VContEndDt.add(rset.getString(14)==null?"":rset.getString(14));
NewMargin=Double.parseDouble(salePrice)-Double.parseDouble(cargoNewPrice);
VNewMargin.add(NewMargin);
NewTotMargin=Double.parseDouble(allocQty)*NewMargin;
VNewTotMargin.add(NewTotMargin);
VContType.add("S");
}
///////////////////////////////////For LoA Contract ///////////////////////////////////////////////
queryString = "SELECT B.TENDER_NO,B.TENDER_NO,B.LOA_NO,B.LOA_REV_NO,ALLOC_QTY, SALE_PRICE, COST_PRICE, MARGIN, AVG_MARGIN,TOTAL_MARGIN,B.CUSTOMER_CD,TO_DATE(APRV_DT,'DD/MM/YYYY') "+
", TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') "+
" FROM FMS7_LOA_CARGO_DTL B, FMS7_LOA_MST C WHERE B.CARGO_REF_NO='"+cargoSelected+"' "//DELV_TO_DT >=TO_DATE('"+SearchDt+"','DD/MM/YYYY') "
+ "AND B.TENDER_NO=C.TENDER_NO AND B.LOA_NO=C.LOA_NO AND B.LOA_REV_NO=C.LOA_REV_NO AND B.CUSTOMER_CD=C.CUSTOMER_CD ORDER BY LOA_NO";
System.out.println("STEP-1:LOA_NO "+queryString);
rset = stmt.executeQuery(queryString);
while(rset.next()) {
fgsaNo=rset.getString(1)==null?"0":rset.getString(1);	
VfgsaNo.add(rset.getString(1)==null?"0":rset.getString(1));
fgsaRevNo="0";	
VfgsaRevNo.add(rset.getString(2)==null?"0":rset.getString(2));
snNo=rset.getString(3)==null?"0":rset.getString(3);
VsnNo.add(rset.getString(3)==null?"0":rset.getString(3));
snRevNo=rset.getString(4)==null?"0":rset.getString(4);
VsnRevNo.add(rset.getString(4)==null?"0":rset.getString(4));
allocQty=rset.getString(5)==null?"0":rset.getString(5);
VallocQty.add(rset.getString(5)==null?"0":rset.getString(5));
salePrice=rset.getString(6)==null?"0":rset.getString(6);
costPrice=rset.getString(7)==null?"0":rset.getString(7);
VcostPrice.add(rset.getString(7)==null?"0":rset.getString(7));
margin=rset.getString(8)==null?"0":rset.getString(8);
Vmargin.add(rset.getString(8)==null?"0":rset.getString(8));
avgMargin=rset.getString(9)==null?"0":rset.getString(9);
totMargin=rset.getString(10)==null?"0":rset.getString(10);
VtotMargin.add(rset.getString(10)==null?"0":rset.getString(10));
customerCd=rset.getString(11)==null?"0":rset.getString(11);
VcustCd.add(rset.getString(11)==null?"0":rset.getString(11));
VaprvDt.add(rset.getString(12)==null?"0":rset.getString(12));
VContStartDt.add(rset.getString(13)==null?"":rset.getString(13));
VContEndDt.add(rset.getString(14)==null?"":rset.getString(14));
NewMargin=Double.parseDouble(salePrice)-Double.parseDouble(cargoNewPrice);
VNewMargin.add(NewMargin);
NewTotMargin=Double.parseDouble(allocQty)*NewMargin;
VNewTotMargin.add(NewTotMargin);
VContType.add("L");
}
////////////////////////////In case of not having SN/LOA//////////////////////////////////////////////////////////
System.out.println("VfgsaNo "+VfgsaNo);

if(VfgsaNo.size()==0) { //NEW ENTRY DOES NOT EXIST OR ENTRY EXISTS ALSO APPROVED..
int ModSeQNo=0;
fgsaNo="0"; fgsaRevNo="0"; snNo="0"; snRevNo="0"; customerCd="0";
costPrice=cargoProvPrice; margin="0"; totMargin="0"; 
NewMargin=0; NewTotMargin=0; allocQty="0"; contract_type="X";
String queryString11 = "SELECT MAX(MODIFICATION_SEQ_NO) "+
" FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE CARGO_REF_NO='"+cargoSelected+"' "//AND NEW_PRICE_EFF_DT =TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') "
+ "AND FGSA_NO='"+fgsaNo+"' AND FGSA_REV_NO='"+fgsaRevNo+"' AND SN_NO='"+snNo+"' AND SN_REV_NO='"+snRevNo+"' AND CUSTOMER_CD='"+customerCd+"' "
+ "AND CONTRACT_TYPE='"+contract_type+"' AND NEW_PRICE_EFF_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')";
System.out.println("STEP-1B:FMS8_CARGO_ALLOC_REVISED_DTL "+queryString11);
rset1 = stmt.executeQuery(queryString11);
if(rset1.next()) {
ModSeQNo=rset1.getInt(1);
}
else
{
ModSeQNo=0;
}
if(ModSeQNo==0) 
{
queryString1 = "SELECT MAX(MODIFICATION_SEQ_NO) "+
		" FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE CARGO_REF_NO='"+cargoSelected+"' "//AND NEW_PRICE_EFF_DT =TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') "
		+ "AND FGSA_NO='"+fgsaNo+"' AND FGSA_REV_NO='"+fgsaRevNo+"' AND SN_NO='"+snNo+"' AND SN_REV_NO='"+snRevNo+"' AND CUSTOMER_CD='"+customerCd+"' "
				+ "AND CONTRACT_TYPE='"+contract_type+"'";
//		System.out.println("STEP-1B:FMS8_CARGO_ALLOC_REVISED_DTL "+queryString1);
	rset1 = stmt.executeQuery(queryString1);
	if(rset1.next()) {
		ModSeQNo=rset1.getInt(1);
	}
	else
	{
		ModSeQNo=0;
	}
	ModSeQNo++;
query = "INSERT INTO FMS8_CARGO_ALLOC_REVISED_DTL(FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CUSTOMER_CD,"
		+ "CONTRACT_TYPE,CARGO_REF_NO,MODIFICATION_SEQ_NO,FLAG,ENT_BY,ENT_DT,"
		+ "ORI_SALE_PRICE,NEW_SALE_PRICE,ORI_MARGIN,NEW_MARGIN,ORI_AVG_MARGIN,"
		+ "NEW_AVG_MARGIN,ORI_TOT_MARGIN,NEW_TOT_MARGIN,ALLOC_QTY, NEW_PRICE_EFF_DT) VALUES('"+fgsaNo+"'"
		+ ",'"+fgsaRevNo+"','"+snNo+"','"+snRevNo+"','"+customerCd+"','"+contract_type+"',"
		+ "'"+cargoSelected+"','"+ModSeQNo+"','R','"+user_cd+"',SYSDATE,'"+costPrice+"','"+cargoNewPrice+"',"
		+ "'"+margin+"','"+NewMargin+"','"+margin+"','"+NewMargin+"','"+totMargin+"',"
		+ "'"+NewTotMargin+"','"+allocQty+"',TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')) ";
		System.out.println("QRY-2: INSERT: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
		stmt.executeUpdate(query);
		msg = "Cargo-"+cargoSelected+" ";
		msg += "Change in Price Requested, From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!!\n";
		dbcon.commit();
}
else if(ModSeQNo>0) { //ENTRY EXISTS..BUT NOT APPROVED..
query = "UPDATE FMS8_CARGO_ALLOC_REVISED_DTL SET  NEW_SALE_PRICE='"+cargoNewPrice+"',"
			+ "ENT_BY='"+user_cd+"',ENT_DT=SYSDATE, NEW_PRICE_EFF_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') WHERE FGSA_NO='"+fgsaNo+"' AND "
			+ "SN_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' AND CONTRACT_TYPE='"+contract_type+"' AND MODIFICATION_SEQ_NO='"+ModSeQNo+"' "
			+ "AND FLAG='R' AND CARGO_REF_NO='"+cargoSelected+"'";
		System.out.println("QRY-2A:Update: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
		stmt.executeUpdate(query);
		msg = "Cargo-"+cargoSelected+" ";
		msg += "Final Price UPDATED To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!!\n";
		dbcon.commit();
}
} 
//////////////////////^SB20200525: /////////////////////////////////////
for(int ii=0; ii<VfgsaNo.size(); ii++)
{
/////////Date Limit check//////////////////
int Date_Diff = 0; int Date_Diff2 = 0;
query2 = "select (to_date('"+cargoNewPriceDt+"','dd/mm/yyyy')-to_date('"+VContStartDt.elementAt(ii)+"','dd/mm/yyyy')) from dual";
//System.out.println(" from Date_Diff  : "+query2+" "+capacity_arr[i]);
rset2 = stmt2.executeQuery(query2);
rset2.next();
Date_Diff = rset2.getInt(1); System.out.println("DayDiff: "+Date_Diff);
query2 = "select (to_date('"+VContEndDt.elementAt(ii)+"','dd/mm/yyyy')-to_date('"+cargoNewPriceDt+"','dd/mm/yyyy')) from dual";
//System.out.println("from Date_Diff  : "+query2+" "+capacity_arr[i]);
rset2 = stmt2.executeQuery(query2);
rset2.next();
Date_Diff2 = rset2.getInt(1); System.out.println("DayDiff: "+Date_Diff2);
///////////////////////////////////////////////////
//SB20200525:Blocked as per Rohiton Final price it should change all, w/o Sale Price effecting	if(Date_Diff>=0 && Date_Diff2>=0)
{
int count=0;  int countDays=0; int ModSeQNo=0; 
fgsaNo=""+VfgsaNo.elementAt(ii); fgsaRevNo=""+VfgsaRevNo.elementAt(ii); snNo=""+VsnNo.elementAt(ii); snRevNo=""+VsnRevNo.elementAt(ii); customerCd=""+VcustCd.elementAt(ii);
costPrice=""+VcostPrice.elementAt(ii); margin=""+Vmargin.elementAt(ii); totMargin=""+VtotMargin.elementAt(ii); 
NewMargin=Double.parseDouble(""+VNewMargin.elementAt(ii)); NewTotMargin=Double.parseDouble(""+VNewTotMargin.elementAt(ii)); allocQty=""+VallocQty.elementAt(ii);
if(VContType.elementAt(ii).equals("S"))
{
if(!VaprvDt.elementAt(ii).equals(""))
{
contract_type ="O";
/*query = "UPDATE FMS7_SN_CARGO_DTL SET COST_PRICE='"+cargoNewPrice+"', MARGIN='"+NewMargin+"',"
+ "AVG_MARGIN='"+NewMargin+"',TOTAL_MARGIN='"+NewTotMargin+"', ENT_DT=TO_DATE(sysdate,'DD/MM/YYYY'), STATUS='N', AUTH_BY='"+user_cd+"', AUTH_DT=TO_DATE(sysdate,'DD/MM/YYYY') WHERE FGSA_NO='"+fgsaNo+"' AND "
+ "SN_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' and CARGO_REF_NO='"+cargoSelected+"' "; */
query = "UPDATE FMS7_SN_CARGO_DTL SET STATUS='N', AUTH_BY='"+user_cd+"', AUTH_DT=sysdate WHERE FGSA_NO='"+fgsaNo+"' AND "
+ "SN_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' and CARGO_REF_NO='"+cargoSelected+"' AND TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')>TO_DATE(APRV_DT,'DD/MM/YYYY')";
stmt.executeUpdate(query);
}
else if(VaprvDt.elementAt(ii).equals(""))
{
query = "UPDATE FMS7_SN_CARGO_DTL SET STATUS='N', AUTH_BY='"+user_cd+"', AUTH_DT=sysdate WHERE FGSA_NO='"+fgsaNo+"' AND "
	+ "SN_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' and CARGO_REF_NO='"+cargoSelected+"'";
	stmt.executeUpdate(query);
}
//		System.out.println("QRY-1A:Update: FMS7_SN_CARGO_DTL: "+query);
//msg += "Cargo-"+cargoSelected+" New Price Set For SN "+snNo+" From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!! \n";
//	dbcon.commit();

}
else if(VContType.elementAt(ii).equals("L"))
{
contract_type ="G";
if(!VaprvDt.elementAt(ii).equals(""))
{
query = "UPDATE FMS7_LOA_CARGO_DTL SET STATUS='N', AUTH_BY='"+user_cd+"', AUTH_DT=sysdate WHERE TENDER_NO='"+fgsaNo+"' AND "
+ "LOA_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' and CARGO_REF_NO='"+cargoSelected+"' AND TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')>TO_DATE(APRV_DT,'DD/MM/YYYY')";
stmt.executeUpdate(query);
}
else if(VaprvDt.elementAt(ii).equals(""))
{
query = "UPDATE FMS7_LOA_CARGO_DTL SET STATUS='N', AUTH_BY='"+user_cd+"', AUTH_DT=sysdate WHERE TENDER_NO='"+fgsaNo+"' AND "
	+ "LOA_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' and CARGO_REF_NO='"+cargoSelected+"' ";
	stmt.executeUpdate(query);
}
//	System.out.println("QRY-1A:Update: FMS7_SN_CARGO_DTL: "+query);
//	msg += "Cargo-"+cargoSelected+" New Price Set For LOA "+snNo+" From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!! \n";
//	dbcon.commit();

}

/*String queryString1 = "SELECT MODIFICATION_SEQ_NO, (NEW_PRICE_EFF_DT - TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')) "+
" FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE CARGO_REF_NO='"+cargoSelected+"' "//AND NEW_PRICE_EFF_DT =TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') "
+ "AND FGSA_NO='"+fgsaNo+"' AND FGSA_REV_NO='"+fgsaRevNo+"' AND SN_NO='"+snNo+"' AND SN_REV_NO='"+snRevNo+"' AND CUSTOMER_CD='"+customerCd+"' AND CONTRACT_TYPE='"+contract_type+"' AND NEW_PRICE_EFF_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')";
*/
if(VContType.elementAt(ii).equals("S"))
contract_type ="O";
else
contract_type ="G";
queryString1 = "SELECT Count(MODIFICATION_SEQ_NO) "+
" FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE CARGO_REF_NO='"+cargoSelected+"' "//AND NEW_PRICE_EFF_DT =TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') "
+ "AND FGSA_NO='"+fgsaNo+"' AND FGSA_REV_NO='"+fgsaRevNo+"' AND SN_NO='"+snNo+"' AND SN_REV_NO='"+snRevNo+"' AND CUSTOMER_CD='"+customerCd+"' "
	+ "AND CONTRACT_TYPE='"+contract_type+"' AND NEW_PRICE_EFF_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') ORDER BY MODIFICATION_SEQ_NO";
//		System.out.println("STEP-1B:FMS8_CARGO_ALLOC_REVISED_DTL "+queryString1);
rset1 = stmt.executeQuery(queryString1);
if(rset1.next()) {
count=rset1.getInt(1);
//	ModSeQNo=rset1.getInt(1);
//	countDays=rset1.getInt(2);
}
queryString1 = "SELECT MAX(MODIFICATION_SEQ_NO) "+
	" FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE CARGO_REF_NO='"+cargoSelected+"' "//AND NEW_PRICE_EFF_DT =TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') "
	+ "AND FGSA_NO='"+fgsaNo+"' AND FGSA_REV_NO='"+fgsaRevNo+"' AND SN_NO='"+snNo+"' AND SN_REV_NO='"+snRevNo+"' AND CUSTOMER_CD='"+customerCd+"' "
			+ "AND CONTRACT_TYPE='"+contract_type+"'";
//		System.out.println("STEP-1B:FMS8_CARGO_ALLOC_REVISED_DTL "+queryString1);
rset1 = stmt.executeQuery(queryString1);
if(rset1.next()) {
	ModSeQNo=rset1.getInt(1);
//	countDays=rset1.getInt(2);
}
else
{
	ModSeQNo=0;
	//countDays=-1;  //Not available in Table
}

///	if(countDays !=0) { //NEW ENTRY DOES NOT EXIST OR ENTRY EXISTS ALSO APPROVED..
if(count==0) { //NEW ENTRY DOES NOT EXIST OR ENTRY EXISTS ALSO APPROVED..
ModSeQNo++;
query = "INSERT INTO FMS8_CARGO_ALLOC_REVISED_DTL(FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CUSTOMER_CD,"
	+ "CONTRACT_TYPE,CARGO_REF_NO,MODIFICATION_SEQ_NO,FLAG,ENT_BY,ENT_DT,"
	+ "ORI_SALE_PRICE,NEW_SALE_PRICE,ORI_MARGIN,NEW_MARGIN,ORI_AVG_MARGIN,"
	+ "NEW_AVG_MARGIN,ORI_TOT_MARGIN,NEW_TOT_MARGIN,ALLOC_QTY, NEW_PRICE_EFF_DT) VALUES('"+fgsaNo+"'"
	+ ",'"+fgsaRevNo+"','"+snNo+"','"+snRevNo+"','"+customerCd+"','"+contract_type+"',"
	+ "'"+cargoSelected+"','"+ModSeQNo+"','R','"+user_cd+"',SYSDATE,'"+costPrice+"','"+cargoNewPrice+"',"
	+ "'"+margin+"','"+NewMargin+"','"+margin+"','"+NewMargin+"','"+totMargin+"',"
	+ "'"+NewTotMargin+"','"+allocQty+"',TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')) ";
//	System.out.println("QRY-2: INSERT: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
	stmt.executeUpdate(query);
	if(VContType.elementAt(ii).equals("S"))
		msg += "Requested for SN "+snNo+" From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!!\n";
	else
		msg += "Requested for LOA "+snNo+" From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!!\n";
	dbcon.commit();
} 
else if(count>0) { //ENTRY EXISTS..BUT NOT APPROVED..
query = "UPDATE FMS8_CARGO_ALLOC_REVISED_DTL SET  NEW_SALE_PRICE='"+cargoNewPrice+"',"
		+ "NEW_MARGIN='"+NewMargin+"',NEW_AVG_MARGIN='"+NewMargin+"',"
		+ "NEW_TOT_MARGIN='"+NewTotMargin+"',ENT_BY='"+user_cd+"',ENT_DT=SYSDATE, NEW_PRICE_EFF_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') WHERE FGSA_NO='"+fgsaNo+"' AND "
		+ "SN_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' AND CONTRACT_TYPE='"+contract_type+"' AND MODIFICATION_SEQ_NO='"+ModSeQNo+"' "
		+ "AND FLAG='R' AND CARGO_REF_NO='"+cargoSelected+"'";
//	System.out.println("QRY-2A:Update: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
	stmt.executeUpdate(query);
	if(VContType.elementAt(ii).equals("S"))
		msg += "Updated For SN "+snNo+" From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!!\n";
	else
		msg += "Updated For LOA "+snNo+" From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!!\n";
	dbcon.commit();
}
}//EoF if
/*SB20200525	else
{
msg += cargoNewPriceDt+" Out of Range !!["+VsnNo.elementAt(ii)+"] ";
}*/
}
try
{
new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
}
catch(Exception infoLogger)
{
infoLogger.printStackTrace();
}
} catch(Exception e) {
e.printStackTrace();
msg = "Data Not Submitted Successfully!!";
dbcon.rollback();
}
url="../cargo_procurement/frm_cargo_price_modify.jsp?msg="+msg+"&write_permission="+write_permission
+"&approve_permission="+approve_permission+"&contract_type="+contract_type
+"&customer_cd="+customer_cd+"&month="+month+"&year="+year+"&formId="+form_id+"&FormName="+form_nm;
}
public void cargoApprovalDtl2(HttpServletRequest request) throws Exception {
String contract_type = request.getParameter("contract_type")==null?"0":request.getParameter("contract_type");
String month = request.getParameter("month")==null?"0":request.getParameter("month");
String year = request.getParameter("year")==null?"0":request.getParameter("year");
String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
try {
methodName = "cargoApprovalDtl()";
HttpSession session = request.getSession();
String user_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");

String selIndex = request.getParameter("selIndex")==null?"0":request.getParameter("selIndex");
int i = Integer.parseInt(selIndex);

String cargoSelected = request.getParameter("cargoSelected")==null?"0":request.getParameter("cargoSelected");
String cargoNewPrice = request.getParameter("cargoNewPrice")==null?"20":request.getParameter("cargoNewPrice");
String cargoProvPrice = request.getParameter("cargoProvPrice")==null?"20":request.getParameter("cargoProvPrice");
String cargoNewPriceDt = request.getParameter("cargoNewPriceDt")==null?"20":request.getParameter("cargoNewPriceDt");
String cargoConfirmDt = request.getParameter("cargoConfirmDt")==null?"":request.getParameter("cargoConfirmDt");
msg = "Cargo-"+cargoSelected+" Price Approved ";

query = "UPDATE FMS7_MAN_CONFIRM_CARGO_DTL SET PRICE='"+cargoNewPrice+"', PRICE_TO='"+cargoProvPrice+"', FINAL_PRICE_APRV='Y' "
+ "WHERE CARGO_REF_CD='"+cargoSelected+"'";
System.out.println("QRY-1A:Update: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
stmt.executeUpdate(query);

String fgsaNo=""; String fgsaRevNo=""; String snNo=""; String snRevNo=""; String allocQty=""; String salePrice=""; 
String costPrice=""; String margin=""; String avgMargin=""; String totMargin=""; String customerCd="";
double NewMargin=0; double NewTotMargin=0;
Vector VCAR_REF_NO_temp= new Vector();
Vector VfgsaNo= new Vector();
Vector VfgsaRevNo= new Vector();
Vector VsnNo= new Vector();
Vector VsnRevNo= new Vector();
Vector VcustCd= new Vector();
Vector VallocQty= new Vector();
Vector VcostPrice= new Vector();
Vector Vmargin= new Vector();
Vector VtotMargin= new Vector();
Vector VNewMargin= new Vector();
Vector VNewTotMargin= new Vector();
Vector VContType= new Vector();
Vector VaprvDt= new Vector();
Vector VContStartDt= new Vector();
Vector VContEndDt= new Vector();
/////////////////////////////Tax Calculation//////////////////////////////////
String cargoNewPriceNoCD=request.getParameter("cargoNewPrice")==null?"0":request.getParameter("cargoNewPrice");
String tax_str_cd=""; String tax_amt="0"; double cd_charge_per_mmbtu=0;
String queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
"WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B WHERE " +
"B.APP_DATE<=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY'))";
rset1 = stmt1.executeQuery(queryString1);
if(rset1.next())
{
tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
}				
queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
"TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
"A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
"B.app_date<=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1); // MCL 20190307 Temp
rset1=stmt1.executeQuery(queryString1);
while(rset1.next())
{
String tax_cd = rset1.getString(1);
String tax_factor = nf.format(Double.parseDouble(rset1.getString(2)));
			
if(rset1.getString(3).equals("1"))
{
tax_amt = ((Double.parseDouble(""+cargoNewPrice)*Double.parseDouble(rset1.getString(2)))/100)+"";

}
else if(rset1.getString(3).equals("2"))
{
String	queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
		   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
		   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
		   "B.app_date<=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2); // MCL 20190307 Temp
rset2=stmt2.executeQuery(queryString2);
if(rset2.next())
{
		if(rset2.getString(3).equals("1"))
	{
		tax_amt = ((Double.parseDouble(""+cargoNewPrice)*Double.parseDouble(rset2.getString(2)))/100)+"";		
	}								
		tax_amt = ((Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100)+"";
}
else
{
	tax_amt = ""+0.00;
}			 		
}
else
{
tax_amt = ""+0.00;
}			
cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
}
System.out.println("CUSOM DUTY: "+cd_charge_per_mmbtu);System.out.println("cargoNewPrice "+cargoNewPrice);
cargoNewPriceNoCD =cargoNewPrice;
cargoNewPrice=nf2.format(Double.parseDouble(cargoNewPrice)+cd_charge_per_mmbtu);
//////////////////////////////////////////////////////////////////////////////
queryString = "SELECT B.FGSA_NO,B.FGSA_REV_NO,B.SN_NO,B.SN_REV_NO,ALLOC_QTY, SALE_PRICE, COST_PRICE, MARGIN, AVG_MARGIN,TOTAL_MARGIN,B.CUSTOMER_CD,TO_DATE(APRV_DT,'DD/MM/YYYY') "
+ ", TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') "+
" FROM FMS7_SN_CARGO_DTL B, FMS7_SN_MST C WHERE B.CARGO_REF_NO='"+cargoSelected+"' "//DELV_TO_DT >=TO_DATE('"+SearchDt+"','DD/MM/YYYY') "
+ "AND B.FGSA_NO=C.FGSA_NO AND B.FGSA_REV_NO=C.FGSA_REV_NO AND B.SN_NO=C.SN_NO AND B.SN_REV_NO=C.SN_REV_NO AND B.CUSTOMER_CD=C.CUSTOMER_CD ORDER BY SN_NO";
//		System.out.println("STEP-1:SN "+queryString);
rset = stmt.executeQuery(queryString);
while(rset.next()) {
fgsaNo=rset.getString(1)==null?"0":rset.getString(1);	
VfgsaNo.add(rset.getString(1)==null?"0":rset.getString(1));
fgsaRevNo=rset.getString(2)==null?"0":rset.getString(2);	
VfgsaRevNo.add(rset.getString(2)==null?"0":rset.getString(2));
snNo=rset.getString(3)==null?"0":rset.getString(3);
VsnNo.add(rset.getString(3)==null?"0":rset.getString(3));
snRevNo=rset.getString(4)==null?"0":rset.getString(4);
VsnRevNo.add(rset.getString(4)==null?"0":rset.getString(4));
allocQty=rset.getString(5)==null?"0":rset.getString(5);
VallocQty.add(rset.getString(5)==null?"0":rset.getString(5));
salePrice=rset.getString(6)==null?"0":rset.getString(6);
costPrice=rset.getString(7)==null?"0":rset.getString(7);
VcostPrice.add(rset.getString(7)==null?"0":rset.getString(7));
margin=rset.getString(8)==null?"0":rset.getString(8);
Vmargin.add(rset.getString(8)==null?"0":rset.getString(8));
avgMargin=rset.getString(9)==null?"0":rset.getString(9);
totMargin=rset.getString(10)==null?"0":rset.getString(10);
VtotMargin.add(rset.getString(10)==null?"0":rset.getString(10));
customerCd=rset.getString(11)==null?"0":rset.getString(11);
VcustCd.add(rset.getString(11)==null?"0":rset.getString(11));
VaprvDt.add(rset.getString(12)==null?"0":rset.getString(12));
VContStartDt.add(rset.getString(13)==null?"":rset.getString(13));
VContEndDt.add(rset.getString(14)==null?"":rset.getString(14));
NewMargin=Double.parseDouble(salePrice)-Double.parseDouble(cargoNewPrice);
VNewMargin.add(NewMargin);
NewTotMargin=Double.parseDouble(allocQty)*NewMargin;
VNewTotMargin.add(NewTotMargin);
VContType.add("S");
}

///////////////////////////////////For LoA Contract ///////////////////////////////////////////////
queryString = "SELECT B.TENDER_NO,B.TENDER_NO,B.LOA_NO,B.LOA_REV_NO,ALLOC_QTY, SALE_PRICE, COST_PRICE, MARGIN, AVG_MARGIN,TOTAL_MARGIN,B.CUSTOMER_CD ,TO_DATE(APRV_DT,'DD/MM/YYYY') "
+ ", TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') "+
" FROM FMS7_LOA_CARGO_DTL B, FMS7_LOA_MST C WHERE B.CARGO_REF_NO='"+cargoSelected+"' "//DELV_TO_DT >=TO_DATE('"+SearchDt+"','DD/MM/YYYY') "
+ "AND B.TENDER_NO=C.TENDER_NO AND B.LOA_NO=C.LOA_NO AND B.LOA_REV_NO=C.LOA_REV_NO AND B.CUSTOMER_CD=C.CUSTOMER_CD ORDER BY LOA_NO";
//System.out.println("STEP-1:LOA_NO "+queryString);
rset = stmt.executeQuery(queryString);
while(rset.next()) {
fgsaNo=rset.getString(1)==null?"0":rset.getString(1);	
VfgsaNo.add(rset.getString(1)==null?"0":rset.getString(1));
fgsaRevNo="0";	
VfgsaRevNo.add(rset.getString(2)==null?"0":rset.getString(2));
snNo=rset.getString(3)==null?"0":rset.getString(3);
VsnNo.add(rset.getString(3)==null?"0":rset.getString(3));
snRevNo=rset.getString(4)==null?"0":rset.getString(4);
VsnRevNo.add(rset.getString(4)==null?"0":rset.getString(4));
allocQty=rset.getString(5)==null?"0":rset.getString(5);
VallocQty.add(rset.getString(5)==null?"0":rset.getString(5));
salePrice=rset.getString(6)==null?"0":rset.getString(6);
costPrice=rset.getString(7)==null?"0":rset.getString(7);
VcostPrice.add(rset.getString(7)==null?"0":rset.getString(7));
margin=rset.getString(8)==null?"0":rset.getString(8);
Vmargin.add(rset.getString(8)==null?"0":rset.getString(8));
avgMargin=rset.getString(9)==null?"0":rset.getString(9);
totMargin=rset.getString(10)==null?"0":rset.getString(10);
VtotMargin.add(rset.getString(10)==null?"0":rset.getString(10));
customerCd=rset.getString(11)==null?"0":rset.getString(11);
VcustCd.add(rset.getString(11)==null?"0":rset.getString(11));
VaprvDt.add(rset.getString(12)==null?"0":rset.getString(12));
VContStartDt.add(rset.getString(13)==null?"":rset.getString(13));
VContEndDt.add(rset.getString(14)==null?"":rset.getString(14));
NewMargin=Double.parseDouble(salePrice)-Double.parseDouble(cargoNewPrice);
VNewMargin.add(NewMargin);
NewTotMargin=Double.parseDouble(allocQty)*NewMargin;
VNewTotMargin.add(NewTotMargin);
VContType.add("L");
}
///////////////////////////////////UPdate Approval///////////////////////////////////////////////////
System.out.println("VfgsaNo "+VfgsaNo);
if(VfgsaNo.size()==0) { 
query = "UPDATE FMS8_CARGO_ALLOC_REVISED_DTL SET APPROVE_BY='"+user_cd+"', APPROVE_DT=SYSDATE, FLAG='A' WHERE CARGO_REF_NO='"+cargoSelected+"' "
+ "AND FGSA_NO='0' AND SN_NO='0' AND CUSTOMER_CD='0' AND CONTRACT_TYPE='X' AND NEW_PRICE_EFF_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') AND FLAG='R'";
System.out.println("Cargo Final Price Update: FMS8_CARGO_ALLOC_REVISED_DTL: "+query);
stmt.executeUpdate(query);
msg += " To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!! \n";
}
for(int ii=0; ii<VfgsaNo.size(); ii++)
{
/////////Date Limit check//////////////////
int Date_Diff = 0; int Date_Diff2 = 0;
query2 = "select (to_date('"+cargoNewPriceDt+"','dd/mm/yyyy')-to_date('"+VContStartDt.elementAt(ii)+"','dd/mm/yyyy')) from dual";
//System.out.println("Helooo from Date_Diff  : "+query2+" "+capacity_arr[i]);
rset2 = stmt2.executeQuery(query2);
rset2.next();
Date_Diff = rset2.getInt(1); System.out.println("DayDiff: "+Date_Diff);
query2 = "select (to_date('"+VContEndDt.elementAt(ii)+"','dd/mm/yyyy')-to_date('"+cargoNewPriceDt+"','dd/mm/yyyy')) from dual";
//System.out.println("Helooo from Date_Diff  : "+query2+" "+capacity_arr[i]);
rset2 = stmt2.executeQuery(query2);
rset2.next();
Date_Diff2 = rset2.getInt(1); System.out.println("DayDiff: "+Date_Diff2);
///////////////////////////////////////////////////
if(Date_Diff>=0 && Date_Diff2>=0)
{
int count=0;  int countDays=0; int ModSeQNo=0; contract_type ="O";
fgsaNo=""+VfgsaNo.elementAt(ii); fgsaRevNo=""+VfgsaRevNo.elementAt(ii); snNo=""+VsnNo.elementAt(ii); snRevNo=""+VsnRevNo.elementAt(ii); customerCd=""+VcustCd.elementAt(ii);
costPrice=""+VcostPrice.elementAt(ii); margin=""+Vmargin.elementAt(ii); totMargin=""+VtotMargin.elementAt(ii); 
NewMargin=Double.parseDouble(""+VNewMargin.elementAt(ii)); NewTotMargin=Double.parseDouble(""+VNewTotMargin.elementAt(ii)); allocQty=""+VallocQty.elementAt(ii);
if(VContType.elementAt(ii).equals("S"))
{
contract_type ="O";
query = "UPDATE FMS7_SN_CARGO_DTL SET COST_PRICE='"+cargoNewPrice+"', MARGIN='"+NewMargin+"',"
	+ "AVG_MARGIN='"+NewMargin+"',TOTAL_MARGIN='"+NewTotMargin+"', STATUS='Y', APRV_BY='"+user_cd+"', APRV_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') WHERE FGSA_NO='"+fgsaNo+"' AND "
	+ "SN_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' and CARGO_REF_NO='"+cargoSelected+"' AND STATUS='N'";
//		System.out.println("QRY-1A:Update: FMS7_SN_CARGO_DTL: "+query);
	stmt.executeUpdate(query);
	msg += " for SN "+snNo+" From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!! \n";
//	dbcon.commit();
 
}
else if(VContType.elementAt(ii).equals("L"))
{
contract_type ="G";
query = "UPDATE FMS7_LOA_CARGO_DTL SET COST_PRICE='"+cargoNewPrice+"', MARGIN='"+NewMargin+"',"
		+ "AVG_MARGIN='"+NewMargin+"',TOTAL_MARGIN='"+NewTotMargin+"',STATUS='Y', APRV_BY='"+user_cd+"', APRV_DT=TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') WHERE TENDER_NO='"+fgsaNo+"' AND "
		+ "LOA_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' and CARGO_REF_NO='"+cargoSelected+"'  AND STATUS='N'";
//		System.out.println("QRY-1A:Update: FMS7_SN_CARGO_DTL: "+query);
		stmt.executeUpdate(query);
		msg += " for LOA "+snNo+" From "+costPrice+" To "+cargoNewPrice+" Eff dt: "+cargoNewPriceDt+" Successfully!!! \n";
	//	dbcon.commit();
	 
}
ModSeQNo=0;
queryString1 = "SELECT MAX(MODIFICATION_SEQ_NO) "+
" FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE CARGO_REF_NO='"+cargoSelected+"' "//AND NEW_PRICE_EFF_DT =TO_DATE('"+cargoNewPriceDt+"','DD/MM/YYYY') "
+ "AND FGSA_NO='"+fgsaNo+"' AND FGSA_REV_NO='"+fgsaRevNo+"' AND SN_NO='"+snNo+"' AND SN_REV_NO='"+snRevNo+"' AND CUSTOMER_CD='"+customerCd+"' "
	+ "AND CONTRACT_TYPE='"+contract_type+"'";
//	System.out.println("STEP-1B:FMS8_CARGO_ALLOC_REVISED_DTL "+queryString1);
rset1 = stmt.executeQuery(queryString1);
if(rset1.next()) {
ModSeQNo=rset1.getInt(1);
//	countDays=rset1.getInt(2);
}

query = "UPDATE FMS8_CARGO_ALLOC_REVISED_DTL SET APPROVE_BY='"+user_cd+"', APPROVE_DT=SYSDATE, FLAG='A' WHERE FGSA_NO='"+fgsaNo+"' AND "
+ "SN_NO='"+snNo+"' AND CUSTOMER_CD='"+customerCd+"' AND CONTRACT_TYPE='"+contract_type+"' AND MODIFICATION_SEQ_NO='"+ModSeQNo+"' "
+ "AND FLAG='R'";
//	System.out.println("Update Query--"+query);
stmt.executeUpdate(query);
}//EoF if
else
{
	msg += cargoNewPriceDt+" Out of Range !!["+VsnNo.elementAt(ii)+"] ";
}
}
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
e.printStackTrace();
dbcon.rollback();
msg = "Data Not Approved Successfully!!";
}
url="../cargo_procurement/frm_cargo_price_modify.jsp?msg="+msg+"&write_permission="+write_permission
+"&approve_permission="+approve_permission+"&contract_type="+contract_type
+"&customer_cd="+customer_cd+"&month="+month+"&year="+year+"&formId="+form_id+"&FormName="+form_nm;
}
////////////////////////////////////////////////////////////////////////////
}//End Of Class Frm_Contract_Master ...