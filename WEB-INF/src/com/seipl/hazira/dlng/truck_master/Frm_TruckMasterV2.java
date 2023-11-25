package com.seipl.hazira.dlng.truck_master;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.seipl.hazira.dlng.util.RuntimeConf;

@WebServlet("/servlet/Frm_TruckMasterV2")
public class Frm_TruckMasterV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	Connection conn = null;
	Statement stmt = null;
	Statement stmt1 = null;
	Statement stmt2 = null;
	ResultSet rset1 = null;
	public String url = "";
	public String msg = "";
	public String erro_msg = "";
	public String queryString ="";
	public String queryString1 ="";
	public String queryString2 = "";
	public Vector frm_truck_name = new Vector();
	public Vector frm_truck_type = new Vector();
	public Vector frm_effective_date = new Vector();
	public Vector frm_tank_volume = new Vector();
	public Vector frm_status = new Vector();
	public Vector truck_name = new Vector();
	
	
    public Frm_TruckMasterV2() { super();}
   
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.print("I'm in do Post......"+"\n");
		
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
					String flag = request.getParameter("btnflag")==null?"":request.getParameter("btnflag");
					String btn = request.getParameter("modifiers")==null?"":request.getParameter("modifiers");
					String truck_nm = request.getParameter("truck_nm")==null?"":request.getParameter("truck_nm");
					System.out.println("flag : "+flag);
					
					if(flag.equals("Y"))
					{
						if(btn.equals("delete")) 
						{
							removeFromAvailbleTruck(request,response);
						}
						else
						{
//							System.out.println("Update btn : "+btn);
							modifyInAvailbleTruck(request,response);
						}
					}else if(flag.equals("checkTruckStatus")) {
						response.setContentType("text/html");
						String trans_cd = checkTruckStatus(truck_nm);
						
						response.getWriter().write(trans_cd); 
					}else
					{
						storInAvailbleTruck(request,response);
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
		if(!url.equals("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				//e.printStackTrace();
				System.out.println("ServletException..."+e);
			}catch (IOException e) {
				//e.printStackTrace();
				System.out.println("IOException..."+e);
			}
		}
	}
	
	private String checkTruckStatus(String truck_nm)throws SQLException,IOException{
		String trans_cd = "";
		try {
			
			String fetchTruckDtl="select TRANS_CD from DLNG_TANK_TRUCK_MST where TRUCK_NM='"+truck_nm+"' and  STATUS = 'Y' ";
			rset1 = stmt1.executeQuery(fetchTruckDtl);
			if(rset1.next()) {
				trans_cd = rset1.getString(1)==null?"":rset1.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trans_cd;
	}

	private void storInAvailbleTruck(HttpServletRequest request, HttpServletResponse response) {

		
		String cust_id = request.getParameter("customer_name")==null?"":request.getParameter("customer_name");
		String truck_nm = request.getParameter("truck_name")==null?"":request.getParameter("truck_name");
		
		String truck_typ = request.getParameter("truck_type")==null?"":request.getParameter("truck_type");
		String effect_date = request.getParameter("eff_date")==null?"":request.getParameter("eff_date");
		String tank_vol = request.getParameter("truck_volume3")==null?"":request.getParameter("truck_volume3");
		String tank_vol_tone = request.getParameter("truck_volumeTone")==null?"":request.getParameter("truck_volumeTone");
		String status = request.getParameter("status_flag")==null?"":request.getParameter("status_flag");
		String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
		String truck_Loadfactor = request.getParameter("truck_Loadfactor")==null?"100":request.getParameter("truck_Loadfactor");
		String truck_nextLoadDays = request.getParameter("truck_nextLoadDays")==null?"1":request.getParameter("truck_nextLoadDays");
		String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
		System.out.println("truck_name*****"+truck_nm);
		
		try {
			
			int truck_id = 101;
			
			String maxTruck_id = "select max(truck_id) from DLNG_TANK_TRUCK_MST";
			rset1 = stmt.executeQuery(maxTruck_id);
			if(rset1.next()) {
				truck_id=rset1.getInt(1)+1;
			}
			  queryString = "INSERT INTO DLNG_TANK_TRUCK_MST(TRUCK_ID,TRUCK_NM,TRUCK_TYPE,EFF_DT,TANK_VOL_M3,STATUS,CUSTOMER_CD,TANK_VOL_TON,TRANS_CD,LOAD_CAP,NEXT_LOAD_DAY) "
			  		+ "VALUES('"+truck_id+"','"+ truck_nm+"','"+ truck_typ +"',TO_DATE('"+effect_date+"','dd-MM-YYYY'),'"+ tank_vol +"','"+ status+"','"+cust_id+"','"+tank_vol_tone+"','"+trans_cd+"','"+truck_Loadfactor+"','"+truck_nextLoadDays+"')";
			  System.out.print("***Query String**"+queryString+"\n");
			  stmt.executeUpdate(queryString);
			  msg = "Truck details inserterd successfully"+truck_nm;
			  url = "../master/frm_mst_transportation.jsp?msg="+msg+"&flag=I&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
			  
			  conn.commit();
			  
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			erro_msg = "Truck details insertion failed..:"+truck_nm;
			url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		}
	}
	
	private void modifyInAvailbleTruck(HttpServletRequest request, HttpServletResponse response) {
		
		String truck_id = request.getParameter("tid")==null?"":request.getParameter("tid");
		String truck_nm = request.getParameter("tnm")==null?"":request.getParameter("tnm");
		String truck_typ = request.getParameter("truck_type")==null?"":request.getParameter("truck_type");
		String effect_date = request.getParameter("eff_date")==null?"":request.getParameter("eff_date");
		String tank_vol = request.getParameter("truck_volume3")==null?"":request.getParameter("truck_volume3");
		String tank_vol_tone = request.getParameter("truck_volumeTone")==null?"":request.getParameter("truck_volumeTone");
		String status = request.getParameter("status_flag")==null?"":request.getParameter("status_flag");
		String flags=request.getParameter("btnflag")==null?"":request.getParameter("btnflag");
		String hid_cust_cd = request.getParameter("hid_cust_cd")==null?"":request.getParameter("hid_cust_cd");
		String hid_trans_cd = request.getParameter("hid_trans_cd")==null?"":request.getParameter("hid_trans_cd");
		String truck_Loadfactor = request.getParameter("truck_Loadfactor")==null?"100":request.getParameter("truck_Loadfactor");
		String truck_nextLoadDays = request.getParameter("truck_nextLoadDays")==null?"1":request.getParameter("truck_nextLoadDays");
		
		String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
		
//		System.out.println("trans_cd****"+hid_trans_cd);
		try {
			String trans_cd = "";
			String trans_nm = "";
			
			/*checking truck status with transporter*/
			String sql="select A.trans_cd,B.trans_name from DLNG_TRANS_MST B, DLNG_TANK_TRUCK_MST A " + 
					"where A.TRUCK_NM = '"+truck_nm+"' and A.STATUS='Y' and A.trans_cd = B.trans_cd ";
			System.out.println("sql*****"+sql);
			rset1 = stmt1.executeQuery(sql);
			if(rset1.next()) {
				
				trans_cd = rset1.getString(1)==null?"":rset1.getString(1);
				trans_nm = rset1.getString(2)==null?"":rset1.getString(2);
			}
			
			if(trans_cd.equals(hid_trans_cd) || trans_cd.equals("")) { // updation allowed only for Active & same transporter  
				
				queryString1 = "UPDATE DLNG_TANK_TRUCK_MST SET TRUCK_TYPE='"+truck_typ+"',EFF_DT=TO_DATE('"+effect_date+"','dd-MM-YYYY'),TANK_VOL_M3='"+tank_vol+"',STATUS='"+status+"',TANK_VOL_TON='"+tank_vol_tone+"',LOAD_CAP='"+truck_Loadfactor+"',NEXT_LOAD_DAY='"+truck_nextLoadDays+"' "
						+ "WHERE CUSTOMER_CD='"+hid_cust_cd+"' AND TRANS_CD='"+hid_trans_cd+"' AND TRUCK_ID='"+truck_id+"'";
	//			System.out.print("***Query String**"+queryString1+"\n");
				stmt1.executeUpdate(queryString1);
				msg = "Truck details updation done successfully..!";
				
				conn.commit();
				url = "../master/frm_mst_transportation.jsp?modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&msg="+msg+"&flag="+flags+"&trans_cd="+hid_trans_cd;
			}else {
				
				erro_msg = "Truck already Linked with : "+trans_nm;
				url = "../master/frm_mst_transportation.jsp?modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&erro_msg="+erro_msg+"&flag="+flags+"&trans_cd="+hid_trans_cd;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			erro_msg = "Truck details modification failed..!";
//			url = "../master/frm_mst_truck_master.jsp?erro_msg="+erro_msg+"&trans_cd="+hid_trans_cd;
			url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&trans_cd="+hid_trans_cd;
		}
		
	}
	
	private void removeFromAvailbleTruck(HttpServletRequest request, HttpServletResponse response) {
		String truck_id = request.getParameter("tid")==null?"":request.getParameter("tid");
		String flags=request.getParameter("btnflag")==null?"":request.getParameter("btnflag");
		String hid_cust_cd = request.getParameter("hid_cust_cd")==null?"":request.getParameter("hid_cust_cd");
		String hid_trans_cd = request.getParameter("hid_trans_cd")==null?"":request.getParameter("hid_trans_cd");
//		System.out.println("truck id " + truck_id);
		
		try {
			queryString2 = "UPDATE DLNG_TANK_TRUCK_MST SET DEL_FLAG='N' WHERE "
					+ " CUSTOMER_CD='"+hid_cust_cd+"' AND TRANS_CD='"+hid_trans_cd+"' AND TRUCK_ID='"+truck_id+"'";
//			System.out.print("***Query String 2**"+queryString2+"\n");
			stmt2.executeUpdate(queryString2);
			msg = "Deletion done for the selected truck";
			url = "../master/frm_mst_truck_master.jsp?msg="+msg+"&flag="+flags;
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			erro_msg = "Truck details deletion failed..!";
			url = "../master/frm_mst_truck_master.jsp?erro_msg="+erro_msg;
		}
		
		
	}
}