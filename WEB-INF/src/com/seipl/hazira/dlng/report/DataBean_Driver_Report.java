package com.seipl.hazira.dlng.report;

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

public class DataBean_Driver_Report {

	Connection conn; 
	Statement stmt;
	Statement stmt1;
	ResultSet rset;
	ResultSet rset1;
	ResultSet rset2;
	ResultSet rset3;
	String queryString = "";
	String queryString1 = "";
	String queryString2 = "";
	String queryString3 = "";
	String queryString4 = "";
	String queryString5 = "";
	String queryString6 = "";
	String queryString7 = "";
	String callFlag = "";
	String methodName = "";
	String databeanName = "DataBean_Driver_Report";
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
	    			
	    			if(callFlag.equalsIgnoreCase("Driver_Report")) 
					{
	    				fetchDriverReport();
					}
	    		}
	    	}
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }finally {

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
			}
	    	if(rset1 != null)
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
	    	if(rset2 != null)
	    	{
				try
				{
					rset2.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset2 is not close "+e);
				}
			}
	    	if(rset3 != null)
	    	{
				try
				{
					rset3.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset3 is not close "+e);
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
	
	Vector  address = new Vector();
	Vector  contact_no = new Vector();
	Vector  dob = new Vector();
	Vector  driver_name = new Vector();
	Vector  eff_dt = new Vector();
	Vector  ent_by = new Vector();
	Vector  ent_dt = new Vector();
	Vector  license_end_dt = new Vector();
	Vector  license_from_dt = new Vector();
	Vector  license_img = new Vector();
	Vector  license_issue_st_cd = new Vector();
	Vector  license_no = new Vector();
	Vector 	license_type = new Vector();
	Vector  status = new Vector();
	Vector  trans_cd = new Vector();
	Vector  state_code = new Vector();
	Vector  state_nm = new Vector();
	Vector  driver_state_nm = new Vector();
	
	String state = "";
	private void fetchDriverReport()throws SQLException,IOException {
		try {
			
			String stateSql = "select state_code,state_nm from STATE_MST order by state_nm";
			rset1 = stmt1.executeQuery(stateSql);
			while (rset1.next()) {
				state_code.add(rset1.getString(1) == null?"":rset1.getString(1));
				state_nm.add(rset1.getString(2) == null?"":rset1.getString(2));
			}
			String appendSql=" where LICENSE_ISSUE_ST_CD like '%' ";
			if(state.equals("all")) {
			}else {
				appendSql = " where LICENSE_ISSUE_ST_CD='"+state+"' ";
			}
			String driverSql = "select address,contact_no,to_char(dob,'dd/mm/yyyy'),driver_name,to_char(eff_dt,'dd/mm/yyyy'),ent_by,to_char(ent_dt,'dd/mm/yyyy'),"
					+ " to_char(license_end_dt,'dd/mm/yyyy'),to_char(license_from_dt,'dd/mm/yyyy'),"
					+ " license_issue_st_cd,license_no,license_type,status,trans_cd from DLNG_DRIVER_MST "+appendSql+" order by driver_name";
			System.out.println("driverSql------"+driverSql);
			rset = stmt.executeQuery(driverSql);
			while (rset.next()) {
				address.add(rset.getString(1) == null?"":rset.getString(1));
				contact_no.add(rset.getString(2) == null?"":rset.getString(2));
				dob.add(rset.getString(3) == null?"":rset.getString(3));
				driver_name.add(rset.getString(4) == null?"":rset.getString(4));
				eff_dt.add(rset.getString(5) == null?"":rset.getString(5));
				ent_by.add(rset.getString(6) == null?"":rset.getString(6));
				ent_dt.add(rset.getString(7) == null?"":rset.getString(7));
				license_end_dt.add(rset.getString(8) == null?"":rset.getString(8));
				license_from_dt.add(rset.getString(9) == null?"":rset.getString(9));
//				license_img.add(rset.getString(10) == null?"":rset.getString(10));
				license_issue_st_cd.add(rset.getString(10) == null?"":rset.getString(10));
				license_no.add(rset.getString(11) == null?"":rset.getString(11));
				license_type.add(rset.getString(12) == null?"":rset.getString(12));

				if(rset.getString(13).toString().equalsIgnoreCase("Y")) {
					status.add("Active");
				}else {
					status.add("In-Active");
				}
				String transCd = rset.getString(14) == null?"0":rset.getString(14);
				String transSQL = "select trans_abbr,trans_name from DLNG_TRANS_MST where trans_cd = '"+transCd+"'";
				rset1 = stmt1.executeQuery(transSQL);
				if(rset1.next() ) {
					trans_cd.add(rset1.getString(1) == null?"":rset1.getString(1));
				}else {
					trans_cd.add("N.A");
				}
				
				String stateCd = rset.getString(10) == null?"0":rset.getString(10);
				String StateSQL = "select state_nm from STATE_MST where state_code = '"+stateCd+"'";
				rset1 = stmt1.executeQuery(StateSQL);
				if(rset1.next() ) {
					driver_state_nm.add(rset1.getString(1) == null?"":rset1.getString(1));
				}else {
					driver_state_nm.add("");
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String getCallFlag() {
		return callFlag;
	}
	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}
	
	public Vector getAddress() {
		return address;
	}
	public Vector getContact_no() {
		return contact_no;
	}
	public Vector getDob() {
		return dob;
	}
	public Vector getDriver_name() {
		return driver_name;
	}
	public Vector getEff_dt() {
		return eff_dt;
	}
	public Vector getEnt_by() {
		return ent_by;
	}
	public Vector getEnt_dt() {
		return ent_dt;
	}
	public Vector getLicense_end_dt() {
		return license_end_dt;
	}
	public Vector getLicense_from_dt() {
		return license_from_dt;
	}
	public Vector getLicense_img() {
		return license_img;
	}
	public Vector getLicense_issue_st_cd() {
		return license_issue_st_cd;
	}
	public Vector getLicense_no() {
		return license_no;
	}
	public Vector getLicense_type() {
		return license_type;
	}
	public Vector getStatus() {
		return status;
	}
	public Vector getTrans_cd() {
		return trans_cd;
	}
	
	public void setContact_no(Vector contact_no) {
		this.contact_no = contact_no;
	}
	public void setDob(Vector dob) {
		this.dob = dob;
	}
	public void setDriver_name(Vector driver_name) {
		this.driver_name = driver_name;
	}
	public void setEff_dt(Vector eff_dt) {
		this.eff_dt = eff_dt;
	}
	public void setEnt_by(Vector ent_by) {
		this.ent_by = ent_by;
	}
	public void setEnt_dt(Vector ent_dt) {
		this.ent_dt = ent_dt;
	}
	public void setLicense_end_dt(Vector license_end_dt) {
		this.license_end_dt = license_end_dt;
	}
	public void setLicense_from_dt(Vector license_from_dt) {
		this.license_from_dt = license_from_dt;
	}
	public void setLicense_img(Vector license_img) {
		this.license_img = license_img;
	}
	public void setLicense_issue_st_cd(Vector license_issue_st_cd) {
		this.license_issue_st_cd = license_issue_st_cd;
	}
	public void setLicense_no(Vector license_no) {
		this.license_no = license_no;
	}
	public void setLicense_type(Vector license_type) {
		this.license_type = license_type;
	}
	public void setStatus(Vector status) {
		this.status = status;
	}
	public void setTrans_cd(Vector trans_cd) {
		this.trans_cd = trans_cd;
	}
	public Vector getState_code() {
		return state_code;
	}
	public Vector getState_nm() {
		return state_nm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Vector getDriver_state_nm() {
		return driver_state_nm;
	}
	public void setDriver_state_nm(Vector driver_state_nm) {
		this.driver_state_nm = driver_state_nm;
	}
}
