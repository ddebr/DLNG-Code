package com.seipl.hazira.dlng.contract_master;

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

public class DataBean_Holiday_Master {

	Connection conn; 
	Statement stmt;
	Statement stmt1;
	ResultSet rset;
	ResultSet rset1;
	ResultSet rset2;
	ResultSet rset3;
	ResultSet rset4;
	String queryString="";
	String queryString1="";
	String queryString2="";
	String queryString3="";
	String queryString4="";
	String callFlag="";
	
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
	    			
	    			
	    			if(callFlag.equals("fetchHolidayDtl")) {
	    				fetchHolidayDtl();
	    			}
	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }
	    catch(Exception e)
	    {
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
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
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
					e.printStackTrace();
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
					e.printStackTrace();
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
					e.printStackTrace();
				}
			}
	    	if(rset4 != null)
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
	    }
	}

	Vector state_nm = new Vector();
	Vector state_cd = new Vector();
	
	Vector holiday_nm = new Vector();
	Vector holiday_type = new Vector();
	Vector holiday_dt = new Vector();
	Vector flag = new Vector();
	Vector ent_dt = new Vector();
	Vector state_name = new Vector();
	Vector state_code = new Vector();
	Vector user_nm = new Vector();
	
	private void fetchHolidayDtl()throws SQLException,IOException {
		
		try {
			
			String state_sql = "select distinct state_code,state_nm  from STATE_MST where type='S' and flag='Y' order by state_nm";
			rset = stmt.executeQuery(state_sql);
			while(rset.next()) {
				state_cd.add(rset.getString(1) == null?"":rset.getString(1));
				state_nm.add(rset.getString(2) == null?"":rset.getString(2));
				
			} 
			
			String holiday_list_sql = "select holiday_nm,holiday_type,to_char(holiday_dt,'dd/mm/yyyy'),nvl(state_code,0),flag,nvl(ent_by,0),to_char(ent_dt,'dd/mm/yyyy') from dlng_holiday_dtl order by to_char(HOLIDAY_DT,'dd/mm/yyyy') ";
			rset = stmt.executeQuery(holiday_list_sql);
			while(rset.next()) {
				
				holiday_nm.add(rset.getString(1)==null?"":rset.getString(1));
				holiday_type.add(rset.getString(2)==null?"":rset.getString(2));
				holiday_dt.add(rset.getString(3)==null?"":rset.getString(3));
				state_code.add(rset.getString(4)==null?"":rset.getString(4));
				flag.add(rset.getString(5)==null?"":rset.getString(5));
				ent_dt.add(rset.getString(7)==null?"":rset.getString(7));
				
				String state_sql1 = "select state_nm from state_mst where state_code = '"+rset.getString(4)+"' ";
				rset1 = stmt1.executeQuery(state_sql1);
				if(rset1.next()) {
					state_name.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else {
					state_name.add("NA");
				}
				
				String user_sql = "select emp_nm from hr_emp_mst where emp_cd = '"+rset.getString(6)+"' ";
				System.out.println("user_sql****"+user_sql);
				rset1 = stmt1.executeQuery(user_sql);
				if(rset1.next()) {
					user_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else {
					user_nm.add("NA");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}

	public Vector getState_nm() {
		return state_nm;
	}

	public Vector getState_cd() {
		return state_cd;
	}

	public Vector getHoliday_nm() {
		return holiday_nm;
	}

	public Vector getHoliday_type() {
		return holiday_type;
	}

	public Vector getHoliday_dt() {
		return holiday_dt;
	}

	public Vector getFlag() {
		return flag;
	}

	public Vector getEnt_dt() {
		return ent_dt;
	}

	public Vector getState_name() {
		return state_name;
	}

	public Vector getUser_nm() {
		return user_nm;
	}

	public void setHoliday_nm(Vector holiday_nm) {
		this.holiday_nm = holiday_nm;
	}

	public void setHoliday_type(Vector holiday_type) {
		this.holiday_type = holiday_type;
	}

	public void setHoliday_dt(Vector holiday_dt) {
		this.holiday_dt = holiday_dt;
	}

	public void setFlag(Vector flag) {
		this.flag = flag;
	}

	public void setEnt_dt(Vector ent_dt) {
		this.ent_dt = ent_dt;
	}

	public void setState_name(Vector state_name) {
		this.state_name = state_name;
	}

	public void setUser_nm(Vector user_nm) {
		this.user_nm = user_nm;
	}

	public Vector getState_code() {
		return state_code;
	}

	public void setState_code(Vector state_code) {
		this.state_code = state_code;
	}	
	
}
