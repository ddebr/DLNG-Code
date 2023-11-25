package com.seipl.hazira.dlng.service_inv;

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

public class Databean_Service_InvV2 {
	 Connection conn; 
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		Statement stmt5;
		Statement stmt6;
		ResultSet rset;
		ResultSet rset1;
		ResultSet rset2;
		ResultSet rset3;
		ResultSet rset4;
		ResultSet rset5;
		ResultSet rset6;
		String queryString = "";
		String queryString1 = "";
		String queryString2 = "";
		String queryString3 = "";
		String queryString4 = "";
		String queryString5 = "";
		String queryString6 = "";
		
		String callFlag = "";
		String month = "";
		String year = "";
		String from_dt = "";
		String to_dt ="";
		String activity="";
		String operation ="";
		String pay_cd = "";
		String databeanName = "Databean_Service_Inv";
		String trans_cd="";
		String truckSize="";
		
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
	    			stmt3 = conn.createStatement();
	    			stmt4 = conn.createStatement();
	    			
	    			if(callFlag.equalsIgnoreCase("transporter_list")) {
	    				fetchTransporter();
	    				if(!trans_cd.equals("")) {
	    					fetchTransDtl();
	    					if(!truckSize.equals("")) {
	    						fetchTruckDtl();
	    					}
	    				}
	    				
					}
	    			
	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Exception In : ("+databeanName+") - (init()): "+e.getMessage());
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
	    	if(rset4 != null)
	    	{
				try
				{
					rset4.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset4 is not close "+e);
				}
			}
	    	if(rset5 != null)
	    	{
				try
				{
					rset5.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset5 is not close "+e);
				}
			}
	    	if(rset6 != null)
	    	{
				try
				{
					rset6.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset6 is not close "+e);
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
			if(stmt3 != null)
			{
				try
				{
					stmt3.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt3 is not close "+e);
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
					System.out.println("stmt4 is not close "+e);
				}
			}
			if(stmt5 != null)
			{
				try
				{
					stmt5.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt5 is not close "+e);
				}
			}
			if(stmt6 != null)
			{
				try
				{
					stmt6.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt6 is not close "+e);
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
		Vector Vtruck_id = new Vector ();
		Vector Vtruck_reg_no = new Vector ();
		Vector Valloc_dt = new Vector ();
		Vector VLoading_dt = new Vector ();
		Vector InvGenflag = new Vector();
		String sup_cd = "";
		private void fetchTruckDtl()throws SQLException,IOException {
			
			try {
				
				String start_dt = "01/"+month+"/"+year;
				String end_dt = "";
//				System.out.println("start_dt***"+start_dt);
				String last_day = "SELECT to_char(LAST_DAY(to_date('"+start_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') from dual";
//				System.out.println("last_day***"+last_day);
				rset1 = stmt1.executeQuery(last_day);
				if(rset1.next()) {
					end_dt = rset1.getString(1)==null?"":rset1.getString(1);
				}
				int cnt = 0;
				String truck_sql = "SELECT A.SUP_TRN_CD,B.TRUCK_NM,to_char(A.ALLOC_DT,'dd/mm/yyyy') FROM DLNG_ALLOC_MST A,DLNG_TANK_TRUCK_MST B WHERE A.TRANS_CD='"+trans_cd+"' "
						+ " AND A.ALLOC_DT BETWEEN TO_DATE ('"+start_dt+"','DD/MM/YYYY') AND TO_DATE ('"+end_dt+"','DD/MM/YYYY')"
						+ " AND A.SUP_TRN_CD = B.TRUCK_ID AND  A.TRANS_CD = B.TRANS_CD and SUP_TRN_CD NOT IN (SELECT TRUCK_ID FROM DLNG_AC_INVOICE_DTL "
						+ " WHERE  LOADING_DT BETWEEN TO_DATE('"+start_dt+"','DD/MM/YYYY') AND TO_DATE ('"+end_dt+"','DD/MM/YYYY') AND SUPPLIER_CD = '"+sup_cd+"')"
						+ " order by A.ALLOC_DT";
				System.out.println("truck_sql****"+truck_sql);
				rset = stmt.executeQuery(truck_sql);
				while (rset.next()) {
					cnt++;System.out.println("truckSize:  "+truckSize);
					if(Integer.parseInt(truckSize) >= cnt) {
						Vtruck_id.add(rset.getString(1)==null?"":rset.getString(1));
						Vtruck_reg_no.add("Truck No.: "+rset.getString(2));
						Valloc_dt.add("Loading Dt.: "+rset.getString(3));
						VLoading_dt.add(rset.getString(3));
						queryString = "SELECT COUNT(*) FROM DLNG_AC_INVOICE_DTL WHERE TRUCK_ID = '"+rset.getString(1)+"' AND "
								+ " LOADING_DT = TO_DATE('"+rset.getString(3)+"','DD/MM/YYYY')"
								+ " AND SUPPLIER_CD = '"+sup_cd+"'";
						System.out.println("queryString****"+queryString);
						rset1 = stmt1.executeQuery(queryString);
						if(rset1.next()) {
							if(rset1.getInt(1) > 0) {
								InvGenflag.add("Y");
							}else {
								InvGenflag.add("N");
							}
						}else {
							InvGenflag.add("N");
						}
						//InvGenflag.add("N");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String Vtrans_gst_no = "";
		String Vtrans_state = "";
		String Vtrans_abbr = "";
		String Vtrans_pan = "";
		String Vtrans_addr = "";
		String Vtrans_city = "";
		int no_of_trucks = 0;
	
		private void fetchTransDtl()throws SQLException,IOException {
			try {
				String trans_mst_sql = "SELECT TRANS_ABBR,GSTIN_NO,STATE,PAN_NO,WEB_ADDR,CITY FROM DLNG_TRANS_MST WHERE"
						+ " STATUS = 'Y' AND TRANS_CD='"+trans_cd+"' ";
				
//				System.out.println("trans_mst_sql***"+trans_mst_sql);
				rset = stmt.executeQuery(trans_mst_sql);
				if (rset.next()) {
					Vtrans_abbr=rset.getString(1)==null?"":rset.getString(1);
					Vtrans_gst_no=rset.getString(2)==null?"":rset.getString(2);
					Vtrans_state=rset.getString(3)==null?"":rset.getString(3);
					Vtrans_pan=rset.getString(4)==null?"":rset.getString(4);
					Vtrans_addr=rset.getString(5)==null?"":rset.getString(5);
					Vtrans_city=rset.getString(6)==null?"":rset.getString(6);
				}
				
				/* *********************** fetching truck count for selected transporter******************** */
				String start_dt = "01/"+month+"/"+year;
				String end_dt = "";
//				System.out.println("start_dt***"+start_dt);
				String last_day = "SELECT to_char(LAST_DAY(to_date('"+start_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') from dual";
//				System.out.println("last_day***"+last_day);
				rset1 = stmt1.executeQuery(last_day);
				if(rset1.next()) {
					end_dt = rset1.getString(1)==null?"":rset1.getString(1);
				}
//					System.out.println("v****"+end_dt);	
				if(!end_dt.equals("")) {
					String truck_cnt_sql = "SELECT COUNT(*) FROM DLNG_ALLOC_MST WHERE TRANS_CD='"+trans_cd+"' "
							+ " AND ALLOC_DT BETWEEN TO_DATE ('"+start_dt+"','DD/MM/YYYY') AND TO_DATE ('"+end_dt+"','DD/MM/YYYY')";
//					System.out.println("truck_cnt_sql****"+truck_cnt_sql);
					rset1 = stmt1.executeQuery(truck_cnt_sql);
					if(rset1.next()) {
						no_of_trucks = rset1.getInt(1);
					}
				}
//				System.out.println("no_of_trucks***"+no_of_trucks);
				
			}catch (Exception e) {
				
			}
		}

	Vector Vtrans_cd = new Vector ();
	Vector Vtrans_nm = new Vector ();
	
	
	private void fetchTransporter() throws SQLException,IOException{
		try {
			
			String trans_mst_sql = "SELECT TRANS_CD,TRANS_NAME FROM DLNG_TRANS_MST WHERE"
					+ " STATUS = 'Y' ORDER BY TRANS_NAME";
//			System.out.println("trans_mst_sql***"+trans_mst_sql);
			rset = stmt.executeQuery(trans_mst_sql);
			while (rset.next()) {
				Vtrans_cd.add(rset.getString(1)==null?"":rset.getString(1));
				Vtrans_nm.add(rset.getString(2)==null?"":rset.getString(2));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Vector getVtrans_cd() {
		return Vtrans_cd;
	}


	public Vector getVtrans_nm() {
		return Vtrans_nm;
	}


	public String getVtrans_gst_no() {
		return Vtrans_gst_no;
	}


	public String getVtrans_state() {
		return Vtrans_state;
	}


	public String getVtrans_abbr() {
		return Vtrans_abbr;
	}


	public String getVtrans_pan() {
		return Vtrans_pan;
	}


	public String getVtrans_addr() {
		return Vtrans_addr;
	}


	public String getCallFlag() {
		return callFlag;
	}


	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}


	public String getTrans_cd() {
		return trans_cd;
	}


	public void setTrans_cd(String trans_cd) {
		this.trans_cd = trans_cd;
	}


	public String getVtrans_city() {
		return Vtrans_city;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public int getNo_of_trucks() {
		return no_of_trucks;
	}


	public String getTruckSize() {
		return truckSize;
	}


	public void setTruckSize(String truckSize) {
		this.truckSize = truckSize;
	}


	public Vector getVTruck_id() {
		return Vtruck_id;
	}


	public Vector getVTruck_reg_no() {
		return Vtruck_reg_no;
	}


	public Vector getValloc_dt() {
		return Valloc_dt;
	}


	public Vector getInvGenflag() {
		return InvGenflag;
	}


	public Vector getVLoading_dt() {
		return VLoading_dt;
	}


	public String getSup_cd() {
		return sup_cd;
	}


	public void setSup_cd(String sup_cd) {
		this.sup_cd = sup_cd;
	}
}
