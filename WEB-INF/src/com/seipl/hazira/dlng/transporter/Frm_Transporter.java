package com.seipl.hazira.dlng.transporter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;

@WebServlet("/servlet/Frm_Transporter")
@MultipartConfig
public class Frm_Transporter extends HttpServlet {

	
	Connection conn = null;
	Statement stmt = null;
	Statement stmt1 = null;
	Statement stmt2 = null;
	PreparedStatement ps=null;
	ResultSet rset1 = null;
	public String url = "";
	public String msg = "";
	public String erro_msg = "";
	public String queryString ="";
	public String queryString1 ="";
	public String queryString2 = "";
	
	String veh_no1= "";
	String veh_no2="";
	String lr_no="";
	String lr_rec_dt="";
	
	String driver_nm = "";
	String driver_addr ="";
	String driver_lic_no = "";
	String driver_st_cd ="";
	String ajaxReq = "";
	
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Context intialCont = new InitialContext();
			if(intialCont == null) {
				throw new Exception("Boom - No Context");
			}
			
			Context envCont = (Context) intialCont.lookup("java:/comp/env");
			DataSource ds = (DataSource) envCont.lookup(RuntimeConf.security_database);
			if(ds != null)
			{
				conn = ds.getConnection();
				if(conn != null) 
				{
					stmt = conn.createStatement();
					stmt1 = conn.createStatement();
					stmt2 = conn.createStatement();
					
					 conn.setAutoCommit(false);
					ajaxReq = "";
					String flag = request.getParameter("submitType")==null?"":request.getParameter("submitType");
					String option = request.getParameter("option")==null?"":request.getParameter("option");
					ajaxReq = request.getParameter("ajaxReq")==null?"N":request.getParameter("ajaxReq");//Hiren_20210524
//					System.out.println("flag***"+flag+"**option**"+option);
					
					if(option.equals("saveDriverDtl")) {
						
						if(flag.equals("save")) {
							saveDriverDtl(request,response);
						}
						
						else if(flag.equals("modify")) {
							modifyDriverDtl(request,response);
						}
					}
					
					if(option.equals("savelinkData")) {
						
						if(flag.equals("save")) {
							savelinkData(request,response);
						}
						
						if(flag.equals("modify")) {
							modifylinkData(request,response);
						}
					}
					
					conn.close();
					conn = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
	    {    	
			if(stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt is not close "+e);
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
					System.out.println("stmt1 is not close "+e);
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
					System.out.println("stmt2 is not close "+e);
				}
			}
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					System.out.println("conn is not close "+e);
				}
			}
	     }
		if(ajaxReq.equalsIgnoreCase("Y")) {
			
			response.getWriter().write("refresh");
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				System.out.println("ServletException..."+e);
			}catch (IOException e) {
				System.out.println("IOException..."+e);
			}
		}	
	}

	private void modifylinkData(HttpServletRequest request, HttpServletResponse response) {
		
			msg = "";
			erro_msg = "";
			
			String trans_cd = request.getParameter("linked_trans")==null?"":request.getParameter("linked_trans");
			String truck_id = request.getParameter("linked_truck_no")==null?"":request.getParameter("linked_truck_no");
			String license_no = request.getParameter("linked_lic_no")==null?"":request.getParameter("linked_lic_no");
			String user_cd = request.getParameter("user_cd")==null?"":request.getParameter("user_cd");
			
			int rev_no=0;
			
			try {
				
			int link_cnt= 0 ;
			if(!trans_cd.equals("") && !truck_id.equals("") && !license_no.equals("")) {
				
				String max_rev = "select max(nvl(rev_no,0)) from DLNG_TRUCK_DRIVER_LINK_DTL where "
						+ " TRANS_CD='"+trans_cd+"'"
						+ " and TRUCK_ID = '"+truck_id+"' and LICENSE_NO='"+license_no+"'";
				rset1 = stmt.executeQuery(max_rev);
				if(rset1.next()) {
					rev_no = rset1.getInt(1)+1;
				}else {
					rev_no = 1;
				}
					
				String link_cnt_sql= "select count(*) from DLNG_TRUCK_DRIVER_LINK_MST where TRANS_CD='"+trans_cd+"'"
						+ " and TRUCK_ID = '"+truck_id+"' and LICENSE_NO='"+license_no+"' and status='Y' ";
				System.out.println("link_cnt_sql**"+link_cnt_sql);
				rset1 = stmt.executeQuery(link_cnt_sql);
				if(rset1.next()) {
					link_cnt = rset1.getInt(1); 
				}
				
				if(link_cnt > 0) {
					
					String link_dtl_sql = "insert into DLNG_TRUCK_DRIVER_LINK_DTL (TRUCK_ID,TRANS_CD,STATUS,LICENSE_NO,ENT_DT,ENT_BY,REV_NO)"
							+ "  Values ('"+truck_id+"','"+trans_cd+"','N','"+license_no+"',sysdate,'"+user_cd+"','"+rev_no+"')";
					int cnt1 = stmt.executeUpdate(link_dtl_sql);
					
					String unlink_sql = "update DLNG_TRUCK_DRIVER_LINK_MST set status='N'"
							+ " where TRANS_CD='"+trans_cd+"' and TRUCK_ID = '"+truck_id+"' and LICENSE_NO='"+license_no+"' ";
					int cnt = stmt.executeUpdate(unlink_sql);
					
					if(cnt > 0 && cnt1 > 0) {
						conn.commit();
						msg = "Un-Linking done successfully!";
					}else {
						erro_msg = "Un-Linking failed!";
					}
				}else {
					erro_msg = "No record found for un-linking !";
				}
			}else {
				erro_msg = "Please make sure proper selection of trcuk-transporter-driver combination!";
			}	
			
			url = "../master/frm_mst_transportation.jsp?msg="+msg+"&erro_msg="+erro_msg+"&selTrans="+trans_cd;
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			erro_msg = "Error in catch block modifylinkData() method!";
			url = "../master/frm_mst_transportation.jsp?msg="+msg+"&erro_msg="+erro_msg+"&selTrans="+trans_cd;
		}
		
	}

	private void savelinkData(HttpServletRequest request, HttpServletResponse response)throws SQLException,IOException  {
		
		String trans_cd="";
		try {
			
			msg = "";
			erro_msg = "";
			
			trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
			String truck_id = request.getParameter("truck_id")==null?"":request.getParameter("truck_id");
			String license_no = request.getParameter("license_no")==null?"":request.getParameter("license_no");
			String user_cd = request.getParameter("user_cd")==null?"":request.getParameter("user_cd");
			String status_flag = request.getParameter("status_flag")==null?"":request.getParameter("status_flag");
			
			int rev_no=0;
			int link_cnt= 0 ;
			if(!trans_cd.equals("") && !truck_id.equals("") && !license_no.equals("")) {
				
				String link_cnt_sql= "select count(*) from DLNG_TRUCK_DRIVER_LINK_MST where TRANS_CD='"+trans_cd+"'"
						+ " and TRUCK_ID = '"+truck_id+"' and LICENSE_NO='"+license_no+"'  ";
				rset1 = stmt.executeQuery(link_cnt_sql);
				if(rset1.next()) {
					link_cnt = rset1.getInt(1); 
				}
				
				String max_rev = "select max(nvl(rev_no,0)) from DLNG_TRUCK_DRIVER_LINK_DTL where "
						+ " TRANS_CD='"+trans_cd+"'"
						+ " and TRUCK_ID = '"+truck_id+"' and LICENSE_NO='"+license_no+"'";
				rset1 = stmt.executeQuery(max_rev);
				if(rset1.next()) {
					rev_no = rset1.getInt(1)+1;
				}else {
					rev_no = 1;
				}
				System.out.println("rev_no******"+rev_no);
//				System.out.println("link_cnt******"+link_cnt);
				if(link_cnt == 0) {
					
					String link_dtl_sql = "insert into DLNG_TRUCK_DRIVER_LINK_DTL (TRUCK_ID,TRANS_CD,STATUS,LICENSE_NO,ENT_DT,ENT_BY,REV_NO)"
							+ "  Values ('"+truck_id+"','"+trans_cd+"','"+status_flag+"','"+license_no+"',sysdate,'"+user_cd+"','"+rev_no+"')";
					int cnt1 = stmt.executeUpdate(link_dtl_sql);
					
					String insert_link_sql = "insert into DLNG_TRUCK_DRIVER_LINK_MST (TRUCK_ID,TRANS_CD,STATUS,LICENSE_NO,ENT_DT,ENT_BY)"
							+ "  Values ('"+truck_id+"','"+trans_cd+"','"+status_flag+"','"+license_no+"',sysdate,'"+user_cd+"')";
					int cnt = stmt.executeUpdate(insert_link_sql);
//					System.out.println("cnt******"+cnt);
					
					if(cnt > 0 && cnt1 > 0) {
						conn.commit();
						msg = "Linking done successfully!";
					}else {
						erro_msg = "Linking failed!";
					}
				}else {
					
					String link_dtl_sql = "insert into DLNG_TRUCK_DRIVER_LINK_DTL (TRUCK_ID,TRANS_CD,STATUS,LICENSE_NO,ENT_DT,ENT_BY,REV_NO)"
							+ "  Values ('"+truck_id+"','"+trans_cd+"','"+status_flag+"','"+license_no+"',sysdate,'"+user_cd+"','"+rev_no+"')";
					int cnt1 = stmt.executeUpdate(link_dtl_sql);
					
					String update_sql = "update DLNG_TRUCK_DRIVER_LINK_MST set status='"+status_flag+"'"
							+ " where TRANS_CD='"+trans_cd+"' and TRUCK_ID = '"+truck_id+"' and LICENSE_NO='"+license_no+"' ";
					System.out.println("update_sql"+update_sql);
					int cnt = stmt.executeUpdate(update_sql);
					if(cnt > 0) {
						conn.commit();
						msg = "Linking done successfully!";
					}else {
						erro_msg = "Linking failed!";
					}
//					erro_msg = "Linking already available for selected trcuk-transporter-driver combination!";
				}
			}else {
				erro_msg = "Please make sure proper selection of trcuk-transporter-driver combination!";
			}
			
			url = "../master/frm_mst_transportation.jsp?msg="+msg+"&erro_msg="+erro_msg+"&selTrans="+trans_cd;
			
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			erro_msg = "Error in catch block savelinkData() method!";
			url = "../master/frm_mst_transportation.jsp?msg="+msg+"&erro_msg="+erro_msg+"&selTrans="+trans_cd;
		}
		
	}


	private void modifyDriverDtl(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
		
		try {
			msg = "";
			erro_msg = "";
			
			response.setContentType("text/html;charset=UTF-8");
			 
			String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
			String driver_nm = request.getParameter("driver_nm")==null?"":request.getParameter("driver_nm");
			String dob = request.getParameter("dob")==null?"":request.getParameter("dob");
			String mob_no = request.getParameter("mob_no")==null?"0":request.getParameter("mob_no");
			String address = request.getParameter("address")==null?"":request.getParameter("address");
			
			String license_no = request.getParameter("license_no")==null?"":request.getParameter("license_no");
			String lic_typ = request.getParameter("lic_typ")==null?"":request.getParameter("lic_typ");
			String lic_from_dt = request.getParameter("lic_from_dt")==null?"":request.getParameter("lic_from_dt");
			String lic_to_dt = request.getParameter("lic_to_dt")==null?"":request.getParameter("lic_to_dt");
			String driver_stcd = request.getParameter("driver_st_cd")==null?"":request.getParameter("driver_st_cd");
			String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
			
			String user_cd = request.getParameter("user_cd")==null?"":request.getParameter("user_cd");
			
			Part photo =  request.getPart("license_photo");
			DataBean_Date_Formater df = new DataBean_Date_Formater();
			
			Date date = new Date(0);
			dob  = df.formatDate1(dob,"dd/mm/yyyy","yyyy-mm-dd");
			lic_from_dt  = df.formatDate2(lic_from_dt,"dd/mm/yyyy","yyyy-mm-dd");
			lic_to_dt  = df.formatDate3(lic_to_dt,"dd/mm/yyyy","yyyy-mm-dd");
			eff_dt  = df.formatDate4(eff_dt,"dd/mm/yyyy","yyyy-mm-dd");
				
			
			int lic_cnt = 0;
			if(!license_no.equals("")) {
				
				String validate_lic_sql = "select count(*) from DLNG_DRIVER_MST where license_no = '"+license_no+"' " ;
//				System.out.println("validate_lic_sql******"+validate_lic_sql);
				rset1 = stmt.executeQuery(validate_lic_sql);
				if(rset1.next()) {
					
					lic_cnt = rset1.getInt(1);
				}
				if(lic_cnt > 0) {
					
					ps = conn.prepareStatement("update  DLNG_DRIVER_MST set ADDRESS=?,CONTACT_NO=?,DOB=?,DRIVER_NAME=?,EFF_DT=?,ENT_BY=?,"
							+ " ENT_DT=?,LICENSE_END_DT=?,LICENSE_FROM_DT=?,LICENSE_ISSUE_ST_CD=?,LICENSE_TYPE=?,STATUS=?,"
							+ " TRANS_CD=?,LICENSE_IMG=? where LICENSE_NO=?");
					
					ps.setString(1, address);
					ps.setString(2, mob_no);
					ps.setDate(3, date.valueOf(dob));
					ps.setString(4, driver_nm);
					ps.setDate(5, date.valueOf(eff_dt));
					ps.setString(6, user_cd);
					ps.setDate(7, getCurrentDate()) ;
					ps.setDate(8, date.valueOf(lic_to_dt));
					ps.setDate(9, date.valueOf(lic_from_dt));
					ps.setString(10, driver_stcd);
//					ps.setString(11, license_no);
					ps.setString(11, lic_typ);
					ps.setString(12, "Y");
					ps.setString(13, trans_cd);
					ps.setBinaryStream(14, photo.getInputStream(), (int)  photo.getSize());
					ps.setString(15, license_no);
					ps.executeUpdate();
							/*+ " ('"+address+"','"+mob_no+"','"+dob+"','"+driver_nm+"','"+eff_dt+"','"+user_cd+"',sysdate,'"+lic_to_dt+"','"+lic_from_dt+"',"
							+ " '"+driver_stcd+"','"+license_no+"','"+lic_typ+"','Y','"+trans_cd+"','"+photo.getInputStream()+"') ";
					System.out.println("Driver_insert_sql****"+Driver_insert_sql);
					
					
					boolean flag = stmt.execute(Driver_insert_sql);*/
					
					msg = "Driver Details successfully Updated with the given License No."+license_no;		
				}else {
					erro_msg = "No Driver Details found for the given License No."+license_no;
				}
				
				
			}else {
				erro_msg = "Required License No.!!";
			}
			url = "../master/frm_mst_transportation.jsp?msg="+msg+"&erro_msg="+erro_msg;
			conn.commit();
			
		}catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				erro_msg = "Error in catch block...modifyDriverDtl() method";
				url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg;
			}
			
	}

	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	

	
	private void saveDriverDtl(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException {
	try {
		
		msg = "";
		erro_msg = "";
		
		response.setContentType("text/html;charset=UTF-8");
		 
		String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
		String driver_nm = request.getParameter("driver_nm")==null?"":request.getParameter("driver_nm");
		String dob = request.getParameter("dob")==null?"":request.getParameter("dob");
		String mob_no = request.getParameter("mob_no")==null?"0":request.getParameter("mob_no");
		String address = request.getParameter("address")==null?"":request.getParameter("address");
		
		String license_no = request.getParameter("license_no")==null?"":request.getParameter("license_no");
		String lic_typ = request.getParameter("lic_typ")==null?"":request.getParameter("lic_typ");
		String lic_from_dt = request.getParameter("lic_from_dt")==null?"":request.getParameter("lic_from_dt");
		String lic_to_dt = request.getParameter("lic_to_dt")==null?"":request.getParameter("lic_to_dt");
		String driver_stcd = request.getParameter("driver_st_cd")==null?"":request.getParameter("driver_st_cd");
		String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
		
		String user_cd = request.getParameter("user_cd")==null?"":request.getParameter("user_cd");
		DataBean_Date_Formater df = new DataBean_Date_Formater(); 
		Part photo =  request.getPart("license_photo");
//		java.util.Date sysdate = new java.util.Date();
		 Date date = new Date(0);
		dob  = df.formatDate1(dob,"dd/mm/yyyy","yyyy-mm-dd");
		System.out.println("dob****"+dob);
		lic_from_dt  = df.formatDate2(lic_from_dt,"dd/mm/yyyy","yyyy-mm-dd");
		lic_to_dt  = df.formatDate3(lic_to_dt,"dd/mm/yyyy","yyyy-mm-dd");
		eff_dt  = df.formatDate4(eff_dt,"dd/mm/yyyy","yyyy-mm-dd");
		
		int lic_cnt = 0;
		if(!license_no.equals("")) {
			
			String validate_lic_sql = "select count(*) from DLNG_DRIVER_MST where license_no = '"+license_no+"' " ;
//			System.out.println("validate_lic_sql******"+validate_lic_sql);
			rset1 = stmt.executeQuery(validate_lic_sql);
			if(rset1.next()) {
				
				lic_cnt = rset1.getInt(1);
			}
			if(lic_cnt == 0) {
				
				ps = conn.prepareStatement("insert into DLNG_DRIVER_MST (ADDRESS,CONTACT_NO,DOB,DRIVER_NAME,EFF_DT,ENT_BY,ENT_DT"
						+ ",LICENSE_END_DT,LICENSE_FROM_DT,LICENSE_ISSUE_ST_CD,LICENSE_NO,LICENSE_TYPE,STATUS,TRANS_CD,LICENSE_IMG)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				System.out.println("ps***"+ps.toString());	
				
				/*ps.setString(1, address);
				ps.setString(2, mob_no);
				ps.setString(3, dob);
				ps.setString(4, driver_nm);
				ps.setString(5, eff_dt);
				ps.setString(6, user_cd);
				ps.setDate(7, getCurrentDate()) ;
				ps.setString(8, lic_to_dt);
				ps.setString(9, lic_from_dt);
				ps.setString(10, driver_stcd);
				ps.setString(11, license_no);
				ps.setString(12, lic_typ);
				ps.setString(13, "Y");
				ps.setString(14, trans_cd);*/
				ps.setString(1, address);
				ps.setString(2, mob_no);
				ps.setDate(3, date.valueOf(dob));
				ps.setString(4, driver_nm);
				ps.setDate(5, date.valueOf(eff_dt));
				ps.setString(6, user_cd);
				ps.setDate(7, getCurrentDate()) ;
				ps.setDate(8, date.valueOf(lic_to_dt));
				ps.setDate(9, date.valueOf(lic_from_dt));
				ps.setString(10, driver_stcd);
				ps.setString(11, license_no);
				ps.setString(12, lic_typ);
				ps.setString(13, "Y");
				ps.setString(14, trans_cd);
				
				ps.setBinaryStream(15, photo.getInputStream(), (int)  photo.getSize());
				ps.executeUpdate();
						/*+ " ('"+address+"','"+mob_no+"','"+dob+"','"+driver_nm+"','"+eff_dt+"','"+user_cd+"',sysdate,'"+lic_to_dt+"','"+lic_from_dt+"',"
						+ " '"+driver_stcd+"','"+license_no+"','"+lic_typ+"','Y','"+trans_cd+"','"+photo.getInputStream()+"') ";
				System.out.println("Driver_insert_sql****"+Driver_insert_sql);
				
				
				boolean flag = stmt.execute(Driver_insert_sql);*/
				
				msg = "Driver Details successfully submitted with the given License No."+license_no;		
			}else {
				erro_msg = "Driver Details already available with the given License No."+license_no;
			}
			
			
		}else {
			erro_msg = "Required License No.!!";
		}
		url = "../master/frm_mst_transportation.jsp?msg="+msg+"&erro_msg="+erro_msg;
		conn.commit();
		
	}catch (Exception e) {
		e.printStackTrace();
		conn.rollback();
		erro_msg = "Error in catch block...saveDriverDtl() Method";
		url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg;
	}
		
	}
}
