package com.seipl.hazira.dlng.contract_master;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

@WebServlet("/servlet/Frm_Holiday_Master")
public class Frm_Holiday_Master extends  HttpServlet{
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
	public String msg = "",err_msg = "";
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
			}
			
			option=req.getParameter("option")==null?"":req.getParameter("option");
			
			write_permission = req.getParameter("write_permission")==null?"N":req.getParameter("write_permission");
			delete_permission = req.getParameter("delete_permission")==null?"N":req.getParameter("delete_permission");
			print_permission = req.getParameter("print_permission")==null?"N":req.getParameter("print_permission");
			approve_permission = req.getParameter("approve_permission")==null?"N":req.getParameter("approve_permission");
			audit_permission = req.getParameter("audit_permission")==null?"N":req.getParameter("audit_permission");
			
			form_id = req.getParameter("form_id")==null?"0":req.getParameter("form_id");
			form_nm = req.getParameter("form_nm")==null?"":req.getParameter("form_nm");
			
			System.out.println("DLNG: option****: "+option);
			
			if(option.equals("Insert_Holiday")) {
				insertHolidayList(req,res);
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


	private void insertHolidayList(HttpServletRequest req, HttpServletResponse res)throws SQLException,IOException {
			
			err_msg = "";
			msg = "";
			
			String holi_nm= req.getParameter("holi_nm")==null?"":req.getParameter("holi_nm");
//			String holi_dt= req.getParameter("holi_dt")==null?"":req.getParameter("holi_dt");
			String holi_state= req.getParameter("holi_state")==null?"":req.getParameter("holi_state");
			String holi_typ = req.getParameter("holiday_type")==null?"":req.getParameter("holiday_type");
			System.out.println("holi_typ****"+holi_typ);
			String status_flag= req.getParameter("status_flag")==null?"":req.getParameter("status_flag");
			String user_cd= req.getParameter("user_cd")==null?"":req.getParameter("user_cd");
			
			String modCd = req.getParameter("modCd")==null?"":req.getParameter("modCd");
			String formId = req.getParameter("formId")==null?"":req.getParameter("formId");
			String subModUrl = req.getParameter("subModUrl")==null?"":req.getParameter("subModUrl");
			String save = req.getParameter("save")==null?"":req.getParameter("save");
			String holi_hid_dt = req.getParameter("holi_hid_dt")==null?"":req.getParameter("holi_hid_dt");
			System.out.println("save****"+save);
			int holi_cnt=0;
		if(holi_nm.contains("'")) {
			holi_nm = holi_nm.replace("'", " ");
		}
			System.out.println("holi_nm*******"+holi_nm);
		try {
			
			String check_sql = "select count(*) from DLNG_HOLIDAY_DTL where HOLIDAY_DT = to_date('"+holi_hid_dt+"','dd/mm/yyyy')";
			System.out.println("check_sql****"+check_sql);
			rset = stmt.executeQuery(check_sql);
			if(rset.next()) {
				holi_cnt = rset.getInt(1);
			}
			
			if(holi_cnt == 0) {
				
				String insert_sql = "Insert into DLNG_HOLIDAY_DTL (HOLIDAY_NM,HOLIDAY_DT,HOLIDAY_TYPE,STATE_CODE,FLAG,ENT_BY,ENT_DT)"
						+ " values ('"+holi_nm+"',to_date('"+holi_hid_dt+"','dd/mm/yyyy'),'"+holi_typ+"','"+holi_state+"','"+status_flag+"','"+user_cd+"',sysdate) ";
				System.out.println("insert_sql******"+insert_sql);
				stmt.executeUpdate(insert_sql);
				msg = "Holiday Successfully Inserted for : "+holi_hid_dt ;
				
				dbcon.commit();
				
			}else {
				
				/*for update record*/
				
				if(save.equals("modify")) {
					
					String update_sql = "update DLNG_HOLIDAY_DTL set HOLIDAY_NM = '"+holi_nm+"', HOLIDAY_TYPE = '"+holi_typ+"',"
							+ " STATE_CODE = '"+holi_state+"', FLAG = '"+status_flag+"' where HOLIDAY_DT = to_date('"+holi_hid_dt+"','dd/mm/yyyy')";
					System.out.println("update_sql******"+update_sql);
					stmt.execute(update_sql);
					msg = "Holiday Updated Successfully for : "+holi_hid_dt ;
					
					dbcon.commit();
					
				}else {
					err_msg = "Holiday Already Available for : "+holi_hid_dt;
				}
			}
			url="../contract_master/frm_mst_FLSA.jsp?msg="+msg+"&error_msg="+err_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		} catch (Exception e) {
			err_msg = "Error : Holiday Insertion/Updation failed due to: "+e.getMessage();
			dbcon.rollback();
			e.printStackTrace();
			url="../contract_master/frm_mst_FLSA.jsp?msg="+msg+"&error_msg="+err_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		}
		System.out.println("url****"+url);
	}
}
