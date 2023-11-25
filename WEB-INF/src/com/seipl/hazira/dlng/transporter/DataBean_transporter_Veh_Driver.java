package com.seipl.hazira.dlng.transporter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

public class DataBean_transporter_Veh_Driver {

	Connection conn = null;
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	ResultSet rset;
	ResultSet rset1;
	ResultSet rset2;
	String quryString = "";
	String quryString1 = "";
	String quryString2 = "";
	String methodName = "";
	
	String call_flag="";
	String trans_cd="";
	private Vector transporter_cd = new Vector();
	public Vector transporter_name = new Vector();
	public Vector transporter_abbr = new Vector();
	//public Vector effective_date = new Vector();
	
	public Vector state = new Vector();
	public Vector city = new Vector();
	public Vector pincode = new Vector();
	public Vector status_flag = new Vector();
	
	public Vector statecode = new Vector();//11MAY2020
	public Vector statename = new Vector();//11MAY2020
	
	Vector transporter_eff_date = new Vector(); 
	
	Vector partner_name  = new Vector();
	Vector room_no = new Vector();  
	
	Vector building_nm = new Vector(); 
	Vector street_nm  = new Vector();
	Vector locality  = new Vector();
	Vector district = new Vector();
	Vector veh_flag = new Vector();
	Vector driver_flag = new Vector();
	Vector phone = new Vector();
	Vector fax = new Vector();
		
	UtilBean utilBean = new UtilBean();
	String transporterFlg = "";
	String transporter_nm ="";
	String transporterAvail="false";
	String transporterid = "";
	String Call_Var="";
	
	String address="";
	String contact_no="";
	String dob="";
	String driver_nm="";
	String eff_dt="";
	String license_end_dt="";
	String license_from_dt="";
	String license_issue_st_cd="";
	String license_type="";
	String status="";
	
	
	Vector vehicle_no1 = new Vector(); 
	Vector vehicle_no2= new Vector(); 
	Vector lr_no= new Vector(); 
	Vector lr_rec_dt= new Vector(); 
	Vector driver_name= new Vector(); 
	Vector driver_addr= new Vector(); 
	Vector licence_no= new Vector(); 
	Vector licence_issue_st= new Vector(); 
	Vector driver_cd= new Vector();
	Vector state_nm = new Vector();//SUJIT17SEP2020
	
	Vector license_no  = new Vector();
	String lic_no="";
	
	Vector Vtruck_id = new Vector();
	Vector Vtruck_nm = new Vector();
	
	Vector Vlicense_no = new Vector();
	Vector Vdriver_nm = new Vector();
	
	Vector VLinked_driver_nm= new Vector();
	Vector VLinked_truck_nm= new Vector();
	Vector VLinked_license_no= new Vector();
	Vector VLinked_status= new Vector();
	Vector VLinked_date= new Vector();
	Vector VLinked_truck_id= new Vector();
	
	@SuppressWarnings("unused")
	public void init()
	{
	    try
	    {
	    	Context initContext = new InitialContext();
	    	if(initContext == null)
	    	{
	    		throw new Exception("Boom - No Context");
	    	}
		  
	    	Context envContext  = (Context)initContext.lookup("java:/comp/env");
	    	DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
	    	if(ds != null) 
	    	{
	    		conn = ds.getConnection();       
	    		if(conn != null)  
	    		{
	    			stmt = conn.createStatement();	
	    			stmt1 = conn.createStatement(); 
	    			stmt2 = conn.createStatement();
	    			
	    			if(call_flag.equals("driverList")) {
	    				
	    				fetchDriverList();
	    			}
	    			
	    			if(!lic_no.equals("")) {
	    				fetchSelDriverDtl();
	    			}
		    		if(call_flag.equals("fetchLinkDtl")) {
		    			fetchLinkDtl();
		    		}
	    			
	    			fetchStates();
//		    		fetchExistTransporterLst();	
	    			
		    			
	    			
	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Exception In : (DataBean_transporterMaster) - (init()): "+e.getMessage());
	    	e.printStackTrace();
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
					System.out.println("rset is not close "+e);
				}
			}if(rset1 != null)
	    	{
				try
				{
					rset1.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset1 is not close "+e);
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
	}
	
	private void fetchLinkDtl()throws SQLException,IOException{
		
		try {
			
			if(!trans_cd.equals("")) {
				String truck_sql = "select truck_id,truck_nm from DLNG_TANK_TRUCK_MST where trans_cd = '"+trans_cd+"' and "
						+ " truck_id not in (select truck_id from DLNG_TRUCK_DRIVER_LINK_MST where trans_cd = '"+trans_cd+"' and status='Y')";
				System.out.println("truck_sql**"+truck_sql);
				rset = stmt.executeQuery(truck_sql);
				while (rset.next()) {
					
					Vtruck_id.add(rset.getString(1)==null?"":rset.getString(1));
					Vtruck_nm.add(rset.getString(2)==null?"":rset.getString(2));
				}
				
				String driver_sql= "select LICENSE_NO,DRIVER_NAME from DLNG_DRIVER_MST where trans_cd = '"+trans_cd+"'"
						+ " AND LICENSE_NO not in (select LICENSE_NO from DLNG_TRUCK_DRIVER_LINK_MST where trans_cd = '"+trans_cd+"' and status='Y') ";
				System.out.println("driver_sql****"+driver_sql);
				rset = stmt.executeQuery(driver_sql);
				while (rset.next()) {
					
					Vlicense_no.add(rset.getString(1)==null?"":rset.getString(1));
					Vdriver_nm.add(rset.getString(2)==null?"":rset.getString(2));
				}
				
				/*fetching linked truck-driver*/
				
				String linked_sql= "select TRUCK_ID,STATUS,LICENSE_NO,to_char(ENT_DT,'dd/mm/yyyy') from DLNG_TRUCK_DRIVER_LINK_MST where trans_cd='"+trans_cd+"' order by ENT_DT"; 
				System.out.println("linked_sql**"+linked_sql);
				rset = stmt.executeQuery(linked_sql);
				while(rset.next()) {
					
					VLinked_truck_id.add(rset.getString(1)==null?"":rset.getString(1));
					VLinked_license_no.add(rset.getString(3)==null?"":rset.getString(3));
					VLinked_status.add(rset.getString(2)==null?"":rset.getString(2));
					VLinked_date.add(rset.getString(4)==null?"":rset.getString(4));
					
					String truck_nm_sql = "select truck_nm from DLNG_TANK_TRUCK_MST where TRUCK_ID = '"+rset.getString(1)+"' and trans_cd='"+trans_cd+"'  ";
					rset1 = stmt1.executeQuery(truck_nm_sql);
					if(rset1.next()) {
						VLinked_truck_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
					}else {
						VLinked_truck_nm.add("-");
					}
					
					String driver_nm_sql = "select driver_name from DLNG_DRIVER_MST where LICENSE_NO = '"+rset.getString(3)+"' and trans_cd='"+trans_cd+"'  ";
					rset1 = stmt1.executeQuery(driver_nm_sql);
					if(rset1.next()) {
						VLinked_driver_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
					}else {
						VLinked_driver_nm.add("-");
					}
				}
			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void fetchSelDriverDtl() throws SQLException,IOException {
		
		try {
			
			String selDriver_sql = "select ADDRESS,CONTACT_NO,to_char(DOB,'dd/mm/yyyy'),DRIVER_NAME,to_char(EFF_DT,'dd/mm/yyyy'),"
					+ " to_char(LICENSE_END_DT,'dd/mm/yyyy'),to_char(LICENSE_FROM_DT,'dd/mm/yyyy'),LICENSE_ISSUE_ST_CD,"
					+ " LICENSE_TYPE,STATUS from DLNG_DRIVER_MST  where LICENSE_NO='"+lic_no+"' and TRANS_CD='"+trans_cd+"' ";
			rset = stmt.executeQuery(selDriver_sql);
			System.out.println("selDriver_sql*****"+selDriver_sql);
			if(rset.next()) {
				address = rset.getString(1)==null?"":rset.getString(1);
				contact_no = rset.getString(2)==null?"":rset.getString(2);
				dob = rset.getString(3)==null?"":rset.getString(3);
				driver_nm = rset.getString(4)==null?"":rset.getString(4);
				eff_dt = rset.getString(5)==null?"":rset.getString(5);
				license_end_dt = rset.getString(6)==null?"":rset.getString(6);
				license_from_dt = rset.getString(7)==null?"":rset.getString(7);
				license_issue_st_cd = rset.getString(8)==null?"":rset.getString(8);
				license_type = rset.getString(9)==null?"":rset.getString(9);
				status = rset.getString(10)==null?"":rset.getString(10);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void fetchDriverList()throws SQLException,IOException {
	try {
		String getDriver_sql = "select DRIVER_NAME,LICENSE_NO from DLNG_DRIVER_MST where TRANS_CD= '"+trans_cd+"' order by DRIVER_NAME";
//		System.out.println("getDriver_sql***"+getDriver_sql);
		rset = stmt.executeQuery(getDriver_sql);
		while (rset.next()) {
			driver_name.add(rset.getString(1)==null?"":rset.getString(1));
			license_no.add(rset.getString(2)==null?"":rset.getString(2));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}	
		
	}
	private void fetchStates()throws SQLException,IOException {
		methodName = "fetchStates()";
		try {
			
			quryString1 = "SELECT STATE_CODE,STATE_NM From STATE_MST ORDER BY STATE_NM";
			rset1 = stmt1.executeQuery(quryString1);
			while(rset1.next()) {
				statecode.add(rset1.getString(1)==null?"":rset1.getString(1));
				statename.add(rset1.getString(2)==null?"":rset1.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*private void fetchExistTransporterLst()throws SQLException,IOException {
		methodName = "fetchExistTransporterLst()";
		try {
			quryString = "SELECT TRANSPORTER_CD,TRANSPORTER_NAME, TRANSPORTER_ABBR, to_char(TRANSPORTER_EFF_DATE,'dd/mm/yyyy') ,nvl(PARTNER_NAME,'') ,ADDR_ROOM_NO ,ADDR_BUILDING_NM,ADDR_STREET_NM,ADDR_LOCALITY,ADDR_CITY,nvl(ADDR_DISTRICT,''),ADDR_STATE, ADDR_PINCODE , STATUS_FLAG,VEH_FLG,DRIVER_FLG,PHONE,FAX  "
					   + " From DLNG_TRANS_VEH_DRIVER_MST WHERE STATUS_FLAG='Y' ";
			//System.out.println("quryString***"+quryString);
			
			rset = stmt.executeQuery(quryString);
			
			while(rset.next()) {
				
				transporter_cd.add(rset.getString(1)==null?"":rset.getString(1));
				transporter_name.add(rset.getString(2)==null?"":rset.getString(2));  
				transporter_abbr.add(rset.getString(3)==null?"":rset.getString(3));   
				transporter_eff_date.add(rset.getString(4)==null?"":rset.getString(4)); 
				partner_name.add(rset.getString(5)==null?"":rset.getString(5)); 
				room_no.add(rset.getString(6)==null?"":rset.getString(6));  
				building_nm.add(rset.getString(7)==null?"":rset.getString(7)); 
				street_nm.add(rset.getString(8)==null?"":rset.getString(8)); 
				locality.add(rset.getString(9)==null?"":rset.getString(9)); 
				city.add(rset.getString(10)==null?"":rset.getString(10)); 
				district.add(rset.getString(11)==null?"":rset.getString(11)); 
				state.add(rset.getString(12)==null?"":rset.getString(12));
				pincode.add(rset.getString(13)==null?"":rset.getString(13)); 
				status_flag.add(rset.getString(14)==null?"":rset.getString(14));  
				veh_flag.add(rset.getString(15)==null?"":rset.getString(15));  
				driver_flag.add(rset.getString(16)==null?"":rset.getString(16)); 
				phone.add(rset.getString(17)==null?"":rset.getString(17)); 
				fax.add(rset.getString(18)==null?"":rset.getString(18)); 
			
			--------- SUJIT17SEP2020---------------
			quryString1 = "SELECT STATE_CODE,STATE_NM From STATE_MST WHERE STATE_CODE = '"+rset.getString(12)+"' ";
//			System.out.println("quryString1 : "+quryString1); 
			rset1 = stmt1.executeQuery(quryString1);
			if(rset1.next()) {
				state_nm.add(rset1.getString(2)==null?"":rset1.getString(2));
			}
			
			---------END SUJIT17SEP2020---------------
			
			if(rset.getString(15).toString().equalsIgnoreCase("Y")){
				quryString1 = "SELECT VEHICLE_NO1,VEHICLE_NO2, LR_NO, to_char(LR_RECEIPT_DATE,'dd/mm/yyyy')  "
						   + "From DLNG_TRANS_VEHICLE_DTL WHERE transporter_cd='"+rset.getString(1)+"'";
				//System.out.println("quryString***"+quryString1);
				rset1 = stmt1.executeQuery(quryString1);
				if(rset1.next()){
						vehicle_no1.add(rset1.getString(1));
						vehicle_no2.add(rset1.getString(2)==null?"":rset1.getString(2));
						lr_no.add(rset1.getString(3));
						lr_rec_dt.add(rset1.getString(4));
					}else{
						vehicle_no1.add("");
						vehicle_no2.add("");
						lr_no.add("");
						lr_rec_dt.add("");
						
					}
			}else{
					vehicle_no1.add("");
					vehicle_no2.add("");
					lr_no.add("");
					lr_rec_dt.add("");
			}
			
			if(rset.getString(16).toString().equalsIgnoreCase("Y")){
				quryString2 = "SELECT DRIVER_NAME,nvl(DRIVER_ADDR,''), LICENCE_NO, LICENCE_ISSUE_STATE,DRIVER_CD  "
						   + " From DLNG_TRANS_DRIVER_DTL WHERE transporter_cd='"+rset.getString(1)+"'";
				//System.out.println("quryString***"+quryString2);
				rset2 = stmt2.executeQuery(quryString2);
				if(rset2.next()){
					driver_name.add(rset2.getString(1));
					driver_addr.add(rset2.getString(2)==null?"":rset2.getString(2));
					licence_no.add(rset2.getString(3));
					licence_issue_st.add(rset2.getString(4));
					driver_cd.add(rset2.getString(5));
					}
				else{
					driver_name.add("");
					driver_addr.add("");
					licence_no.add("");
					licence_issue_st.add("");
					driver_cd.add("");
					}
				
			 }else{
				 	driver_name.add("");
					driver_addr.add("");
					licence_no.add("");
					licence_issue_st.add("");
					driver_cd.add("");
			 }
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	public Vector getTransporter_abbr() {return transporter_abbr;}
	public void setCall_Var(String Call_Var) {this.Call_Var = Call_Var;	}
	public Vector getTransporter_id() {	return transporter_cd;}
	public Vector getTransporter_name() {return transporter_name;}
	 
	public Vector getState() {return state;	}
	public Vector getCity() {return city;}
	public Vector getPincode() {return pincode;	}
	public String getTransporter_nm() {return transporter_nm;}
	public String getTransporterid() {return transporterid;	}

	public Vector getStatecode() {	return statecode;}
	public Vector getStatename() {	return statename;}
	
	public Vector getTransporter_eff_date() {
		return transporter_eff_date;
	}
	public void setTransporter_eff_date(Vector transporter_eff_date) {
		this.transporter_eff_date = transporter_eff_date;
	}
	public Vector getPartner_name() {
		return partner_name;
	}
	public void setPartner_name(Vector partner_name) {
		this.partner_name = partner_name;
	}
	public Vector getRoom_no() {
		return room_no;
	}
	public void setRoom_no(Vector room_no) {
		this.room_no = room_no;
	}
	public Vector getBuilding_nm() {
		return building_nm;
	}
	public void setBuilding_nm(Vector building_nm) {
		this.building_nm = building_nm;
	}
	public Vector getStreet_nm() {
		return street_nm;
	}
	public void setStreet_nm(Vector street_nm) {
		this.street_nm = street_nm;
	}
	public Vector getLocality() {
		return locality;
	}
	public void setLocality(Vector locality) {
		this.locality = locality;
	}
	public Vector getDistrict() {
		return district;
	}
	public void setDistrict(Vector district) {
		this.district = district;
	}
	public Vector getStatus_flag() {
		return status_flag;
	}
	public Vector getVehicle_no1() {
		return vehicle_no1;
	}
	public Vector getVehicle_no2() {
		return vehicle_no2;
	}
	public Vector getLr_no() {
		return lr_no;
	}
	public Vector getLr_rec_dt() {
		return lr_rec_dt;
	}
	public Vector getDriver_name() {
		return driver_name;
	}
	public Vector getDriver_addr() {
		return driver_addr;
	}
	public Vector getLicence_no() {
		return licence_no;
	}
	public Vector getLicence_issue_st() {
		return licence_issue_st;
	}
	public Vector getVeh_flag() {
		return veh_flag;
	}
	public Vector getDriver_flag() {
		return driver_flag;
	}
	public Vector getPhone() {
		return phone;
	}
	public Vector getFax() {
		return fax;
	}
	public Vector getDriver_cd() {
		return driver_cd;
	}
	public Vector getState_nm() {//SUJIT17SEP2020
		return state_nm;
	}
	public void setCall_flag(String call_flag) {
		this.call_flag = call_flag;
	}

	public Vector getLicense_no() {
		return license_no;
	}

	public String getTrans_cd() {
		return trans_cd;
	}

	public void setTrans_cd(String trans_cd) {
		this.trans_cd = trans_cd;
	}

	public String getLic_no() {
		return lic_no;
	}

	public void setLic_no(String lic_no) {
		this.lic_no = lic_no;
	}

	public String getAddress() {
		return address;
	}

	public String getContact_no() {
		return contact_no;
	}

	public String getDob() {
		return dob;
	}

	public String getDriver_nm() {
		return driver_nm;
	}

	public String getEff_dt() {
		return eff_dt;
	}

	public String getLicense_end_dt() {
		return license_end_dt;
	}

	public String getLicense_from_dt() {
		return license_from_dt;
	}

	public String getLicense_issue_st_cd() {
		return license_issue_st_cd;
	}

	public String getLicense_type() {
		return license_type;
	}

	public String getStatus() {
		return status;
	}

	public Vector getVtruck_id() {
		return Vtruck_id;
	}

	public Vector getVtruck_nm() {
		return Vtruck_nm;
	}

	public Vector getVlicense_no() {
		return Vlicense_no;
	}

	public Vector getVdriver_nm() {
		return Vdriver_nm;
	}

	public Vector getVLinked_driver_nm() {
		return VLinked_driver_nm;
	}

	public Vector getVLinked_truck_nm() {
		return VLinked_truck_nm;
	}

	public Vector getVLinked_license_no() {
		return VLinked_license_no;
	}

	public Vector getVLinked_status() {
		return VLinked_status;
	}

	public Vector getVLinked_date() {
		return VLinked_date;
	}

	public Vector getVLinked_truck_id() {
		return VLinked_truck_id;
	}
}