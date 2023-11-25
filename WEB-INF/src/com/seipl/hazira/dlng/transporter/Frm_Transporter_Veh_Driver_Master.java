package com.seipl.hazira.dlng.transporter;

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

@WebServlet("/servlet/Frm_Transporter_Veh_Driver_Master")

public class Frm_Transporter_Veh_Driver_Master extends HttpServlet {
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
	
	String veh_no1= "";
	String veh_no2="";
	String lr_no="";
	String lr_rec_dt="";
	
	String driver_nm = "";
	String driver_addr ="";
	String driver_lic_no = "";
	String driver_st_cd ="";
	
	public Frm_Transporter_Veh_Driver_Master() { super();}
	
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
					
					String flag = request.getParameter("btnflag")==null?"":request.getParameter("btnflag");
					String btn = request.getParameter("modifiers")==null?"":request.getParameter("modifiers");
					
					if(flag.equals("Y"))
					{
						
//							System.out.println("Update btn : "+btn);
							modifyTransporterVD(request,response);
						
					}
					else
					{
							createTransporterVD(request,response);
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
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				System.out.println("ServletException..."+e);
			}catch (IOException e) {
				System.out.println("IOException..."+e);
			}
	}

	
	private void modifyTransporterVD(HttpServletRequest request, HttpServletResponse response) {
		String transporter_name = request.getParameter("transporter_name")==null?"":request.getParameter("transporter_name");
		String transporter_abbr = request.getParameter("transporter_abbr")==null?"":request.getParameter("transporter_abbr");
		String transporter_eff_date = request.getParameter("transporter_eff_date")==null?"":request.getParameter("transporter_eff_date");
		String transporter_partner_nm = request.getParameter("transporter_partner_nm")==null?"":request.getParameter("transporter_partner_nm");
		String room_no = request.getParameter("room_no")==null?"":request.getParameter("room_no");
		String building_nm = request.getParameter("building_nm")==null?"":request.getParameter("building_nm");
		String street_nm = request.getParameter("street_nm")==null?"":request.getParameter("street_nm");
		String locality = request.getParameter("locality")==null?"":request.getParameter("locality");
		String city = request.getParameter("city")==null?"":request.getParameter("city");
		String district = request.getParameter("district")==null?"":request.getParameter("district");
		String state = request.getParameter("state")==null?"":request.getParameter("state");
		String pincode = request.getParameter("pincode")==null?"":request.getParameter("pincode");
		String phone = request.getParameter("phone_no")==null?"":request.getParameter("phone_no");
		String fax = request.getParameter("fax")==null?"":request.getParameter("fax");
		String status = request.getParameter("status_flag")==null?"":request.getParameter("status_flag");
		String user_cd = request.getParameter("user_cd")==null?"":request.getParameter("user_cd");
		String transporter_cd = request.getParameter("transporter_name")==null?"":request.getParameter("transporter_cd");
		
		String vehicle_chkflg = request.getParameter("vehicle_chkflg")==null?"":request.getParameter("vehicle_chkflg");
		String driver_chkflg = request.getParameter("driver_chkflg")==null?"":request.getParameter("driver_chkflg");

		int driver_cd = 0;//SUJIT17SEP2020
		msg="";
		
			
		if(vehicle_chkflg.equalsIgnoreCase("Y")){
			
			 veh_no1 = request.getParameter("veh_no1")==null?"":request.getParameter("veh_no1");
			 veh_no2 = request.getParameter("veh_no2")==null?"":request.getParameter("veh_no2");
			 lr_no = request.getParameter("lr_no")==null?"":request.getParameter("lr_no");
			 lr_rec_dt = request.getParameter("lr_rec_date")==null?"":request.getParameter("lr_rec_date");
		}
		
		
		if(driver_chkflg.equalsIgnoreCase("Y")){
			
			 driver_nm = request.getParameter("driver_nm")==null?"":request.getParameter("driver_nm");
			 driver_addr = request.getParameter("driver_addr")==null?"":request.getParameter("driver_addr");
			 driver_lic_no = request.getParameter("driver_lic_no")==null?"":request.getParameter("driver_lic_no");
			 driver_st_cd = request.getParameter("driver_st_cd")==null?"":request.getParameter("driver_st_cd");
			 driver_cd = Integer.parseInt(request.getParameter("driver_cd")==null?"":request.getParameter("driver_cd"));//SUJIT17SEP2020
//				System.out.println("driver_cd : "+driver_cd);
			}
		
		
		try {
			
			queryString = "UPDATE DLNG_TRANS_VEH_DRIVER_MST SET  TRANSPORTER_NAME = '"+ transporter_name+"', TRANSPORTER_ABBR='"+ transporter_abbr +"', TRANSPORTER_EFF_DATE=TO_DATE('"+transporter_eff_date+"','dd/MM/YYYY'), PARTNER_NAME='"+transporter_partner_nm +"',"
							+ "ADDR_ROOM_NO ='"+room_no+"', ADDR_BUILDING_NM ='"+building_nm+"', ADDR_STREET_NM = '"+street_nm+"', ADDR_LOCALITY ='"+locality+"', ADDR_CITY ='"+city+"', ADDR_DISTRICT = '"+district+"' , ADDR_STATE = '"+state+"', ADDR_PINCODE='"+pincode+"' ,"
							+ " PHONE ='"+phone+"',FAX ='"+fax+"', STATUS_FLAG ='"+status+"' , MOD_DT=sysdate,VEH_FLG='"+vehicle_chkflg+"',DRIVER_FLG ='"+driver_chkflg+"' "
							+ " WHERE TRANSPORTER_CD='"+transporter_cd+"'";
	  			
			System.out.print("***Query String**"+queryString);
			stmt.executeUpdate(queryString);
			
			  if(vehicle_chkflg.equalsIgnoreCase("Y")){
				  int cnt=0;
					
					String cntqry = "select count(TRANSPORTER_CD) from DLNG_TRANS_VEHICLE_DTL  WHERE TRANSPORTER_CD='"+transporter_cd+"' ";
					System.out.println("cntqry***"+cntqry);
					rset1 = stmt1.executeQuery(cntqry);
					if(rset1.next()) {
						cnt=rset1.getInt(1);
					}
//					System.out.println("vehicle cnt...."+cnt);
				  if(cnt>0){
					  queryString = "UPDATE DLNG_TRANS_VEHICLE_DTL SET  VEHICLE_NO1='"+veh_no1+"', VEHICLE_NO2='"+veh_no2+"', "
					  		 		+ " LR_NO ='"+lr_no +"', LR_RECEIPT_DATE= TO_DATE('"+lr_rec_dt+"','dd/MM/YYYY'), "
					  				+ " ENT_BY ='"+user_cd+"', ENT_DT =sysdate WHERE TRANSPORTER_CD='"+transporter_cd+"'";  
				  }else{
					  queryString = "INSERT INTO  DLNG_TRANS_VEHICLE_DTL ( TRANSPORTER_CD , VEHICLE_NO1,VEHICLE_NO2, LR_NO, LR_RECEIPT_DATE , "
				  				+ " ENT_BY , ENT_DT) "
				  		      + " VALUES('"+transporter_cd+"','"+veh_no1+"','"+veh_no2+"','"+lr_no +"',"
				  		      + " TO_DATE('"+lr_rec_dt+"','dd/MM/YYYY'),'"+user_cd+"',sysdate)";
					  }
				  System.out.print("***Query Stringvehicle**"+queryString+"\n"); 
				  
			  stmt.executeUpdate(queryString);
			  msg=msg+", Vehicle ";
 
			  }
			  if(driver_chkflg.equalsIgnoreCase("Y"))
			  {
//				  System.out.println("driver_cd : "+driver_cd);
				  //start limit
//				  System.out.println("after driver_cd : "+driver_cd);
				  
				  if(driver_cd == 0 )//SUJIT17SEP2020
				  {
					  driver_cd = 1;  
				  }
				  
				  int cntd=0;
					
					String cntqry = "select count(TRANSPORTER_CD) from DLNG_TRANS_DRIVER_DTL  WHERE TRANSPORTER_CD='"+transporter_cd+"' ";
					rset1 = stmt1.executeQuery(cntqry);
					if(rset1.next()) {
						cntd=rset1.getInt(1);
					}
//					System.out.println("driver cntd...."+cntd);
					  if(cntd>0){
						  queryString = "UPDATE  DLNG_TRANS_DRIVER_DTL SET DRIVER_NAME='"+driver_nm +"', DRIVER_ADDR ='"+driver_addr+"',"
						  			+ " LICENCE_NO='"+driver_lic_no+"',LICENCE_ISSUE_STATE ='"+driver_st_cd+"', DRIVER_STATUS='Y'"
						  			+"	WHERE TRANSPORTER_CD ='"+transporter_cd+"' AND DRIVER_CD='"+driver_cd+"'";
					  			
					  }else{
						  
						String drvquery = "select max(DRIVER_CD) from DLNG_TRANS_DRIVER_DTL";
						rset1 = stmt1.executeQuery(drvquery);
						if(rset1.next()) {
							driver_cd=rset1.getInt(1)+1;
						}
				  
						queryString = "INSERT INTO  DLNG_TRANS_DRIVER_DTL ( TRANSPORTER_CD , DRIVER_CD, DRIVER_NAME, DRIVER_ADDR, LICENCE_NO , "
			  				+ "LICENCE_ISSUE_STATE, DRIVER_STATUS,ENT_BY , ENT_DT) "
			  		      + " VALUES('"+transporter_cd+"','"+driver_cd+"','"+driver_nm+"','"+driver_addr+"',"
			  		      + " '"+driver_lic_no+"','"+driver_st_cd+"', 'Y','"+user_cd+"',sysdate)";
					  }
					  
//				  System.out.print("***Query Stringdriver**"+queryString+"\n");
				  stmt.executeUpdate(queryString);
				  msg=msg+", Driver ";
			  }
			  
			msg = "Transporter details updation done successfully..!";
			url = "../master/frm_mst_transportation.jsp?msg="+msg+"&flag=I";
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			erro_msg = "Transporter details modification failed..!";
			url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg;
		}
		
	}

	private void createTransporterVD(HttpServletRequest request, HttpServletResponse response) {
		
		
		String transporter_name = request.getParameter("transporter_name")==null?"":request.getParameter("transporter_name");
		String transporter_abbr = request.getParameter("transporter_abbr")==null?"":request.getParameter("transporter_abbr");
		String transporter_eff_date = request.getParameter("transporter_eff_date")==null?"":request.getParameter("transporter_eff_date");
		String transporter_partner_nm = request.getParameter("transporter_partner_nm")==null?"":request.getParameter("transporter_partner_nm");
		String room_no = request.getParameter("room_no")==null?"":request.getParameter("room_no");
		String building_nm = request.getParameter("building_nm")==null?"":request.getParameter("building_nm");
		String street_nm = request.getParameter("street_nm")==null?"":request.getParameter("street_nm");
		String locality = request.getParameter("locality")==null?"":request.getParameter("locality");
		String city = request.getParameter("city")==null?"":request.getParameter("city");
		String district = request.getParameter("district")==null?"":request.getParameter("district");
		String state = request.getParameter("state")==null?"":request.getParameter("state");
		String pincode = request.getParameter("pincode")==null?"":request.getParameter("pincode");
		String phone = request.getParameter("phone_no")==null?"":request.getParameter("phone_no");
		String fax = request.getParameter("fax")==null?"":request.getParameter("fax");
		String status = request.getParameter("status_flag")==null?"":request.getParameter("status_flag");
		String user_cd = request.getParameter("user_cd")==null?"":request.getParameter("user_cd");
		
		String vehicle_chkflg = request.getParameter("vehicle_chkflg")==null?"":request.getParameter("vehicle_chkflg");
		String driver_chkflg = request.getParameter("driver_chkflg")==null?"":request.getParameter("driver_chkflg");
		msg="";
		
//		System.out.println("vehicld.."+vehicle_chkflg);
//		System.out.println("driver_chkflg.."+driver_chkflg);
		
		
		if(vehicle_chkflg.equalsIgnoreCase("Y")){
		 veh_no1 = request.getParameter("veh_no1")==null?"":request.getParameter("veh_no1");
		 veh_no2 = request.getParameter("veh_no2")==null?"":request.getParameter("veh_no2");
		 lr_no = request.getParameter("lr_no")==null?"":request.getParameter("lr_no");
		 lr_rec_dt = request.getParameter("lr_rec_date")==null?"":request.getParameter("lr_rec_date");
		}
		
		
		if(driver_chkflg.equalsIgnoreCase("Y")){
			 driver_nm = request.getParameter("driver_nm")==null?"":request.getParameter("driver_nm");
			 driver_addr = request.getParameter("driver_addr")==null?"":request.getParameter("driver_addr");
			 driver_lic_no = request.getParameter("driver_lic_no")==null?"":request.getParameter("driver_lic_no");
			 driver_st_cd = request.getParameter("driver_st_cd")==null?"":request.getParameter("driver_st_cd");
			}
		
		
	
			try {
				int transporter_id = 10001;//start limit 
				int cnt=0;
				
				String cntqry = "select count(TRANSPORTER_CD) from DLNG_TRANS_VEH_DRIVER_MST";
				rset1 = stmt.executeQuery(cntqry);
				if(rset1.next()) {
					cnt=rset1.getInt(1);
				}
				if(!(cnt==0)){
				String maxTrans_id = "select max(TRANSPORTER_CD) from DLNG_TRANS_VEH_DRIVER_MST";
				rset1 = stmt.executeQuery(maxTrans_id);
				if(rset1.next()) {
					
					transporter_id=rset1.getInt(1)+1;
				}
				
				}else{
					transporter_id=transporter_id;
					
				}
				
				//System.out.print("***transporter_id**"+transporter_id+"\n");
			queryString = "INSERT INTO  DLNG_TRANS_VEH_DRIVER_MST ( TRANSPORTER_CD , TRANSPORTER_NAME, TRANSPORTER_ABBR, TRANSPORTER_EFF_DATE, PARTNER_NAME , "
				  				+ "ADDR_ROOM_NO, ADDR_BUILDING_NM, ADDR_STREET_NM , ADDR_LOCALITY , ADDR_CITY , ADDR_DISTRICT , ADDR_STATE , ADDR_PINCODE ,PHONE,FAX, "
				  				+ " STATUS_FLAG , ENT_BY , ENT_DT,VEH_FLG,DRIVER_FLG) "
				  		      + " VALUES('"+transporter_id+"','"+ transporter_name+"','"+ transporter_abbr +"',"
				  		      + " TO_DATE('"+transporter_eff_date+"','dd/MM/YYYY'),'"+transporter_partner_nm +"',"
				  		      + "'"+room_no+"','"+building_nm+"','"+street_nm+"','"+locality+"','"+city+"',"
				  		      + " '"+district+"','"+state+"','"+pincode+"','"+phone+"','"+fax+"','"+status+"','"+user_cd+"',sysdate,'"+vehicle_chkflg+"','"+driver_chkflg+"')";
 
//				  System.out.print("***Query String**"+queryString+"\n");
				  stmt.executeUpdate(queryString);
				  msg=msg+" Transporter ";

				  if(vehicle_chkflg.equalsIgnoreCase("Y")){
	
					  queryString = "INSERT INTO  DLNG_TRANS_VEHICLE_DTL ( TRANSPORTER_CD , VEHICLE_NO1,VEHICLE_NO2, LR_NO, LR_RECEIPT_DATE , "
				  				+ " ENT_BY , ENT_DT) "
				  		      + " VALUES('"+transporter_id+"','"+veh_no1+"','"+veh_no2+"','"+lr_no +"',"
				  		      + " TO_DATE('"+lr_rec_dt+"','dd/MM/YYYY'),'"+user_cd+"',sysdate)";
//					  System.out.print("***Query Stringvehicle**"+queryString+"\n");
				  stmt.executeUpdate(queryString);
				  msg=msg+", Vehicle ";
				  
				  }
				  if(driver_chkflg.equalsIgnoreCase("Y")){
					  int driver_cd = 1;//start limit
						
						String drvquery = "select max(DRIVER_CD) from DLNG_TRANS_DRIVER_DTL";
						rset1 = stmt1.executeQuery(drvquery);
						if(rset1.next()) {
							driver_cd=rset1.getInt(1)+1;
						}
					  
					  queryString = "INSERT INTO  DLNG_TRANS_DRIVER_DTL ( TRANSPORTER_CD , DRIVER_CD, DRIVER_NAME, DRIVER_ADDR, LICENCE_NO , "
				  				+ "LICENCE_ISSUE_STATE, DRIVER_STATUS,ENT_BY , ENT_DT) "
				  		      + " VALUES('"+transporter_id+"','"+driver_cd+"','"+driver_nm +"','"+driver_addr +"',"
				  		      + " '"+driver_lic_no +"','"+driver_st_cd+"', 'Y','"+user_cd+"',sysdate)";
				  
//				  System.out.print("***Query Stringdriver**"+queryString+"\n");
				  stmt.executeUpdate(queryString);
				  msg=msg+", Driver ";
				  }
				  msg = msg+" details inserterd successfully "+transporter_name;
				  url = "../master/frm_mst_transportation.jsp?msg="+msg+"&flag=I";
				  
				  conn.commit();
				  
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				erro_msg = "Transportation details insertion failed..:"+transporter_name;
				url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg;
			}
	}
}