package com.seipl.hazira.dlng.truck_master;

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

public class DataBean_truckMaster {

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

	private Vector truck_id = new Vector();
	public Vector truck_name = new Vector();
	public Vector truck_type = new Vector();
	public Vector effective_date = new Vector();
	public Vector tank_volume_M3 = new Vector();
	public Vector tank_volume_Ton = new Vector();
	public Vector status = new Vector();
	public Vector customer_abbr = new Vector();
	public Vector customer_id = new Vector();
	public Vector truck_customer_id= new Vector();
	public Vector truck_cust_abbr= new Vector();
	public Vector Vtrans_cd= new Vector();//HA20200327
	public Vector Vtrans_abr= new Vector();//HA20200327
	public Vector Vtrans_name= new Vector();
	
	public Vector VTruck_trans_cd= new Vector();//HA20200327
	public Vector VTruck_trans_abr= new Vector();//HA20200327
	
	UtilBean utilBean = new UtilBean();
	String truckFlg = "";
	String cust_id="";
	String truck_nm ="";
	String truckAvail="false";
	String truckid = "";
	String trans_cd = "";

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
					
	    			if(truckFlg.equals("Y")) {
						fetchTruckNameOnCustId(cust_id, truck_nm);
					}
	    			
	    			fetchAvailableTrucks();
	    			fetchCustomerNames();
	    			fetchTransporterDtl();
	    			//addTrucksToMakeAvailable();	
	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Exception In : (DataBean_truckMaster) - (init()): "+e.getMessage());
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
	
	
	private void fetchTransporterDtl()throws SQLException,IOException {
		try {
			String transDtlSql = "SELECT TRANS_CD,TRANS_ABBR,TRANS_NAME FROM DLNG_TRANS_MST ORDER BY TRANS_CD";
			System.out.println("transDtlSql****"+transDtlSql);
			rset = stmt.executeQuery(transDtlSql);
			while (rset.next()) {
				Vtrans_cd.add(rset.getString(1)==null?"":rset.getString(1));
				Vtrans_abr.add(rset.getString(2)==null?"":rset.getString(2));
				Vtrans_name.add(rset.getString(3)==null?"":rset.getString(3));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unused")
	private void fetchTruckNameOnCustId(String cust_id, String truck_nm) {
				
//		System.out.println("In fetchTruckNameOnCustId ...."+"\n");			
		try {
			int  truck_cnt=0;
			String queryString1 = "SELECT count(*) From DLNG_TANK_TRUCK_MST WHERE DEL_FLAG='Y' AND CUSTOMER_CD ='"+cust_id+"' and TRUCK_NM = '"+truck_nm+"'";
//			System.out.println("quryString 1 :***"+queryString1);
			
			rset1 = stmt1.executeQuery(queryString1);
			 if(rset1.next()){
				 truck_cnt = rset1.getInt(1);
			 }
			if(truck_cnt > 0) {
				 truckAvail="true";
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@SuppressWarnings({ "unchecked" })
	private void fetchAvailableTrucks()throws SQLException,IOException {
		methodName = "fetchAvailableTrucks()";
		String temp_trns_cd=trans_cd;
		if(temp_trns_cd.equals("")) {
			temp_trns_cd="%";
		}
		String temp_cust_cd = cust_id;
		if(temp_cust_cd.equals("") || temp_cust_cd.equals("0")) {
			temp_cust_cd = "%";
		}
		try {
			quryString = "SELECT TRUCK_NM,TRUCK_TYPE,TO_CHAR(EFF_DT,'DD/MM/YYYY'),TANK_VOL_M3,STATUS,ROUND(TANK_VOL_TON,4),CUSTOMER_CD,TRUCK_ID,TRANS_CD, LOAD_CAP, NEXT_LOAD_DAY"
					+ "  From DLNG_TANK_TRUCK_MST WHERE DEL_FLAG='Y' AND TRANS_CD like '"+temp_trns_cd+"' AND CUSTOMER_CD like '"+temp_cust_cd+"' order by CUSTOMER_CD,TRANS_CD";
	//		System.out.println("quryString***"+quryString);
			rset = stmt.executeQuery(quryString);
			while(rset.next()) {
				
				truck_name.add(rset.getString(1)==null?"":rset.getString(1));
				truck_type.add(rset.getString(2)==null?"":rset.getString(2));
				effective_date.add(rset.getString(3)==null?"":rset.getString(3));
				tank_volume_M3.add(rset.getString(4)==null?"":rset.getString(4));
				status.add(rset.getString(5)==null?"":rset.getString(5));
				tank_volume_Ton.add(rset.getString(6)==null?"":rset.getString(6));
				truck_customer_id.add(rset.getString(7)==null?"":rset.getString(7));
				truck_id.add(rset.getString(8)==null?"":rset.getString(8));
				VTruck_trans_cd.add(rset.getString(9)==null?"":rset.getString(9));	
				VTruck_Load_Cap.add(rset.getString(10)==null?"":rset.getString(10));	//SB20200730
				VTruck_Next_Load_Day.add(rset.getString(11)==null?"1":rset.getString(11));	//SB20200730
				if(!rset.getString(7).equals("")) {	
					quryString1 = "SELECT CUSTOMER_ABBR From FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(7)+"' ";
//					System.out.println("quryString1***"+quryString1);
					rset1 = stmt1.executeQuery(quryString1);
					if(rset1.next()) {
						truck_cust_abbr.add(rset1.getString(1)==null?"":rset1.getString(1));
					}else {
						//SB20200805 truck_cust_abbr.add("Own");
						truck_cust_abbr.add("");
					}
				}else {
					truck_cust_abbr.add("");
				}
				
				String getTrans_abr_sql="SELECT TRANS_ABBR FROM DLNG_TRANS_MST WHERE TRANS_CD='"+rset.getString(9)+"'";
//				System.out.println("getTrans_abr_sql****"+getTrans_abr_sql);
				rset1 = stmt1.executeQuery(getTrans_abr_sql);
				if(rset1.next()) {
					VTruck_trans_abr.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else {
					VTruck_trans_abr.add("");
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void fetchCustomerNames()throws IOException,SQLException {
		methodName = "fetchCustomerNames()";
		
		try {
			quryString1 = "SELECT CUSTOMER_CD,CUSTOMER_ABBR From FMS7_CUSTOMER_MST WHERE CUSTOMER_CD IN (SELECT DISTINCT CUSTOMER_CD FROM DLNG_FLSA_MST WHERE FLAG='Y') order by CUSTOMER_CD";
//			System.out.println("quryString***"+quryString1);
			rset1 = stmt1.executeQuery(quryString1);
			while(rset1.next()) {
				customer_id.add(rset1.getString(1)==null?"":rset1.getString(1));
				customer_abbr.add(rset1.getString(2)==null?"":rset1.getString(2));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Vector getTruck_name() {
		return truck_name;
	}

	public void setTruck_name(Vector truck_name) {
		this.truck_name = truck_name;
	}

	public Vector getTruck_type() {
		return truck_type;
	}

	public void setTruck_type(Vector truck_type) {
		this.truck_type = truck_type;
	}

	public Vector getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(Vector effective_date) {
		this.effective_date = effective_date;
	}

	public Vector getTank_volume_M3() {
		return tank_volume_M3;
	}

	public void setTank_volume_M3(Vector tank_volume_M3) {
		this.tank_volume_M3 = tank_volume_M3;
	}

	public Vector getTank_volume_Ton() {
		return tank_volume_Ton;
	}

	public void setTank_volume_Ton(Vector tank_volume_Ton) {
		this.tank_volume_Ton = tank_volume_Ton;
	}

	public Vector getStatus() {
		return status;
	}

	public void setStatus(Vector status) {
		this.status = status;
	}

	public Vector getCustomer_abbr() {
		return customer_abbr;
	}

	public void setCustomer_abbr(Vector customer_abbr) {
		this.customer_abbr = customer_abbr;
	}

	public Vector getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Vector customer_id) {
		this.customer_id = customer_id;
	}

	public Vector getTruck_id() {
		return truck_id;
	}

	public void setTruck_id(Vector truck_id) {
		this.truck_id = truck_id;
	}
	

	public Vector getTruck_customer_id() {
		return truck_customer_id;
	}


	public Vector getTruck_cust_abbr() {
		return truck_cust_abbr;
	}
	
	public String getTruckFlg() {
		return truckFlg;
	}

	public void setTruckFlg(String truckFlg) {
		this.truckFlg = truckFlg;
	}

	public String getTruck_nm() {
		return truck_nm;
	}

	public void setTruck_nm(String truck_nm) {
		this.truck_nm = truck_nm;
	}
	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getTruckAvail() {
		return truckAvail;
	}

	public void setTruckAvail(String truckAvail) {
		this.truckAvail = truckAvail;
	}


	public String getTruckid() {
		return truckid;
	}

	public void setTruckid(String truckid) {
		this.truckid = truckid;
	}
	public Vector getVtrans_cd() {
		return Vtrans_cd;
	}
	public Vector getVtrans_abr() {
		return Vtrans_abr;
	}
	public String getTrans_cd() {
		return trans_cd;
	}

	public void setTrans_cd(String trans_cd) {
		this.trans_cd = trans_cd;
	}
	public Vector getVTruck_trans_cd() {
		return VTruck_trans_cd;
	}
	
	public Vector getVTruck_trans_abr() {
		return VTruck_trans_abr;
	}
	public Vector VTruck_Load_Cap= new Vector();//SB20200730
	public Vector getVTruck_Load_Cap() {return VTruck_Load_Cap;	}//SB20200730
	public Vector VTruck_Next_Load_Day= new Vector();//SB20200730
	public Vector getVTruck_Next_Load_Day() {return VTruck_Next_Load_Day;	}//SB20200730


	public Vector getVtrans_name() {
		return Vtrans_name;
	}
}