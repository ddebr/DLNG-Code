package com.seipl.hazira.dlng.report;

import javax.naming.*;
import javax.sql.*;


import java.util.*;
import java.util.Date;
import java.io.IOException;
import java.sql.*;
import java.text.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

@SuppressWarnings("rawtypes")
public class DataBean_Report_GenerationV3 {
	
		Connection conn; 
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		Statement stmt5;
		Statement stmt6;
		Statement stmt7;
		ResultSet rset;
		ResultSet rset1;
		ResultSet rset2;
		ResultSet rset3;
		ResultSet rset4;
		ResultSet rset5;
		ResultSet rset6;
		ResultSet rset7;
		String queryString = "";
		String queryString1 = "";
		String queryString2 = "";
		String queryString3 = "";
		String queryString4 = "";
		String queryString5 = "";
		String queryString6 = "";
		String queryString7 = "";
		String callFlag = "";
		String gas_date = "";
		String gen_date = ""; 
		String from_dt = "";
		String to_dt   ="";
		String week = "";
		String customer_cd = "0";
		String customer_contact_cd = "0";
		String methodName = "";
		String databeanName = "DataBean_Contract_Mgmt";
		String cust_id="";
		
		NumberFormat nf = new DecimalFormat("###########0.00");
		NumberFormat nf2 = new DecimalFormat("###########0.0000");
		NumberFormat nf3 = new DecimalFormat("###########0.000000000000");
		NumberFormat nf4 = new DecimalFormat("############.##");
		NumberFormat nf5 = new DecimalFormat("############");
		UtilBean util = new UtilBean();
		
		public Vector allCustAbbr = new Vector();
		public Vector allCustid = new Vector();
		
		public Vector rpt_buy_week_customer_nm = new Vector();
		public Vector rpt_buy_week_plant_nm = new Vector();
		public Vector rpt_buy_week_mmbtu = new Vector();
		public Vector rpt_buy_week_mt = new Vector();
		public Vector rpt_buy_week_truck_nm = new Vector();
		public Vector rpt_buy_week_arrival_tm = new Vector();
		public Vector rpt_buy_week_delivery_point = new Vector();
		public Vector rpt_buy_week_remarks = new Vector();
		
		public Vector rpt_sel_week_customer_nm = new Vector();
		public Vector rpt_sel_week_plant_nm = new Vector();
		public Vector rpt_sel_week_mmbtu = new Vector();
		public Vector rpt_sel_week_mt = new Vector();
		public Vector rpt_sel_week_truck_nm = new Vector();
		public Vector rpt_sel_week_schedule_tm = new Vector();
		public Vector rpt_sel_week_remarks = new Vector();
		public Vector rpt_sel_week_schDate = new Vector();
		
		
		String cust_cd="";
		String cont_typ="";
		double convt_mmbtu_to_mt = 0;//HA20210112
		
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
		    			stmt3 = conn.createStatement();
		    			stmt4 = conn.createStatement();
		    			stmt5 = conn.createStatement();
		    			stmt6 = conn.createStatement();
		    			stmt7 = conn.createStatement();
		    			
		    			if(callFlag.equalsIgnoreCase("customerWiseRpt")) {
		    				fetchDailySchDetails_rpt();
		    			}
		    			if(callFlag.equalsIgnoreCase("TerminalTruckWiseRpt")) {
		    				fetchDailySchTruckDetails_rpt();
		    			}
		    			if(callFlag.equalsIgnoreCase("invoice_stmt")) {
		    				fetchInvoiceCust();
		    				if(!cust_cd.equals("") && !cont_typ.equals("")) {
		    					fetchCustomerWiseInvoiceList();
			    				fatchCustnm_ContractTyp();
		    				}
		    			}
		    			if(callFlag.equalsIgnoreCase("contractWisecustAllocationRpt")) {
		    				fetchCustomerNames();
		    				fetchContractWiseCustAllocat_rpt();
		    			}
		    			if(callFlag.equalsIgnoreCase("buyerNomVsTruckLoadingRpt")) {
		    				fetchNominatedCustomers();
		    				fetchBuyerNomVsTruckLoading_rpt();
		    			}
		    			if(callFlag.equalsIgnoreCase("Seller_Weekly_Nom_Rpt")) {
		    				fetchSellerWeeklyNomination_rpt();
//		    				System.out.println("Sch_Mapping_id : "+Sch_Mapping_id);
		    				if(!Sch_Mapping_id.equals("-%-")) {
		    					fetchContactDtl();
		    				}
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
		    	if(rset7 != null)
		    	{
					try
					{
						rset7.close();
					}
					catch(SQLException e)
					{
						System.out.println("rset7 is not close "+e);
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
				if(stmt7 != null)
				{
					try
					{
						stmt7.close();
					}
					catch(SQLException e)
					{
						System.out.println("stmt7 is not close "+e);
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
		public Vector weeklytenderNo = new Vector();
		public Vector weeklyLOANo = new Vector();
		public Vector weeklyLOARevNo = new Vector();
		public Vector weeklyFgsa_Rev_No = new Vector();
		
		private void fetchCustomerNamesForNominations() 
		{
			methodName = "fetchCustomerNamesForNominations()";
			try 
			{	
				String custCdSql = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO FROM DLNG_LOA_MST A "
						 + "WHERE A.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY') "
						 + "AND A.end_dt>=TO_DATE('"+from_date+"','DD/MM/YYYY') "
						 + "AND A.status='Y' "
						 + "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B "
						 + "WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO "
						 + "AND A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY') "
						 + "AND B.end_dt>=TO_DATE('"+from_date+"','DD/MM/YYYY') AND B.status='Y') "
						 + "ORDER BY CUSTOMER_CD";
				
//				System.out.println("custCdSql.."+custCdSql);
				rset = stmt.executeQuery(custCdSql);
				while(rset.next()) 
				{	
					String map_id=rset.getString(1)+"-"+rset.getString(2)+"-0-"+rset.getString(3)+"-"+rset.getString(4);
					
					
					if(!rset.getString(1).equals("0"))
					{
						weeklytenderNo.add(rset.getString(2));
						weeklyLOANo.add(rset.getString(3));
						weeklyLOARevNo.add(rset.getString(4));
						weeklyFgsa_Rev_No.add("0");
						String custAbrSql = "SELECT CUSTOMER_CD,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST "
								   + "WHERE CUSTOMER_CD ='"+rset.getString(1)+"' ";
//						System.out.println("custAbrSql***"+custAbrSql);
						rset1 = stmt1.executeQuery(custAbrSql);
						if(rset1.next()) {
							allCustid.add(rset1.getString(1));
							allCustAbbr.add(rset1.getString(2));
						}
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		private void fetchBuyerWeeklyNomination_rpt() 
		{
			try 
			{
				
				 /*queryString1 = "SELECT TRUCK_VOL,ARRIVAL_TIME,REMARKS,TRUCK_NM FROM DLNG_WEEKLY_TRUCK_NOM_DTL WHERE"
					     + "MAPPING_ID = '"+buyer_mapping_id+"' "
					     + "AND NOM_ID = '"+nomId+"' AND REV_NO='"+revNo+"' AND "
					     + "  NOM_DT between TO_DATE ('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
					     + "AND TO_DATE ('"+nomModalDates.lastElement()+"','DD/MM/YYYY')";*/

					/*String custCdSql = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO FROM DLNG_LOA_MST A "
							 + "WHERE A.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY')"
							 + "AND A.end_dt>=TO_DATE('"+from_date+"','DD/MM/YYYY') "
							 + "AND A.status='Y' "
							 + "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B "
							 + "WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO "
							 + "AND A.customer_cd=B.customer_cd "
							 + "AND  B.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY') "
							 + "AND B.end_dt>=TO_DATE('"+from_date+"','DD/MM/YYYY') AND B.status='Y') "
							 + "ORDER BY CUSTOMER_CD";
					System.out.println("custCdSql.."+custCdSql);
					rset = stmt.executeQuery(custCdSql);
					while(rset.next()) 
					{
						String week_customer_nm = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A "
								   + "WHERE A.customer_cd='"+rset.getString(1)+"' ";
						
						System.out.println("week_customer_nm***"+week_customer_nm);
						rset1 = stmt1.executeQuery(week_customer_nm);
						if(rset1.next()) {
							rpt_buy_week_customer_nm.add(rset1.getString(1));
						}
						queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
								   "WHERE A.customer_cd="+rset.getString(1)+" AND A.TENDER_NO="+rset.getString(2)+" " +
								   "AND A.LOA_NO="+rset.getString(3)+" AND LOA_REV_NO = '"+rset.getString(4)+"'";
						//System.out.println("LoA Plant No****"+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " 
										 + "WHERE A.customer_cd="+rset.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " 
										 + "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " 
										 + "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " 
										 + "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
							rset3 = stmt3.executeQuery(queryString3);
							while(rset3.next())
							{
								rpt_buy_week_plant_nm.add(rset3.getString(1));
							}
						}
						
						queryString1 = "SELECT  TRUCK_ID,TRUCK_NM,CUSTOMER_CD,TANK_VOL_M3,TANK_VOL_TON, A.TRANS_CD, LOAD_CAP, NEXT_LOAD_DAY,TRANS_NAME FROM DLNG_TANK_TRUCK_MST  A, DLNG_TRANS_MST B "
								+ "WHERE A.TRANS_CD=B.TRANS_CD AND A.STATUS ='Y' AND ( CUSTOMER_CD ='"+rset.getString(1)+"' OR CUSTOMER_CD ='0' ) AND A.TRANS_CD IN "
								+ "(SELECT TRANS_CD FROM DLNG_CUST_TRANS_DTL WHERE CUST_CD ='"+rset.getString(1)+"' AND FLAG='Y') "
								+ "ORDER BY TRUCK_ID ";
					}
					
					System.out.println("nomModalDates : "+nomModalDates.size()+"...."+nomModalDates);
					
					for(int i=0;i<nomModalDates.size();i++) 
					{
						queryString1 = "SELECT TRUCK_VOL,ARRIVAL_TIME,REMARKS,TRUCK_NM FROM DLNG_WEEKLY_TRUCK_NOM_DTL "
								 + "WHERE"
								 + " MAPPING_ID='"+buyer_mapping_id+"' "
								 + "AND NOM_ID='"+nomId+"' AND REV_NO='"+revNo+"' "
								 + "AND "
								 + " NOM_DT = TO_DATE ('"+nomModalDates.elementAt(i)+"','DD/MM/YYYY') ";
					
						System.out.println("Weekly GET Truck List: DLNG_TANK_TRUCK_MST: "+queryString1);				
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next()) {
							rpt_buy_week_truck_nm.add(rset1.getString(2)==null?"":rset1.getString(2));
						}
					}
					
					queryString1 = "SELECT TRUCK_VOL,ARRIVAL_TIME,REMARKS,TRUCK_NM FROM DLNG_WEEKLY_TRUCK_NOM_DTL "
							 + "WHERE"
							 + " MAPPING_ID='"+buyer_mapping_id+"' "
							 + "AND NOM_ID='"+nomId+"' AND REV_NO='"+revNo+"' "
							 + "AND "
							 + " NOM_DT between TO_DATE ('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
							 + " AND TO_DATE ('"+nomModalDates.lastElement()+"','DD/MM/YYYY')"; 
				
					System.out.println("Weekly GET Truck List: DLNG_TANK_TRUCK_MST: "+queryString1);				
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next()) {
						rpt_buy_week_truck_nm.add(rset1.getString(2)==null?"":rset1.getString(2));
					}*/
			
			
					/*System.out.println("Truck name rpt_buy_week_truck_nm : "+rpt_buy_week_truck_nm.size()+"...."+rpt_buy_week_truck_nm);
					System.out.println("Customer name rpt_buy_week_customer_nm : "+rpt_buy_week_customer_nm.size()+"...."+rpt_buy_week_customer_nm);		
					System.out.println("Plant name rpt_buy_week_plant_nm : "+rpt_buy_week_plant_nm.size()+"...."+rpt_buy_week_plant_nm);*/
				
				queryString4=""; String wild="%";
				queryString4 = "SELECT TRUCK_NM,ARRIVAL_TIME, TRUCK_VOL, TRUCK_ENE,MAPPING_ID, NOM_ID,NVL(REMARKS,'') "
							 + "FROM DLNG_WEEKLY_TRUCK_NOM_DTL "
							 + "WHERE  MAPPING_ID LIKE '"+wild+"' "
							 + "AND NOM_DT between TO_DATE('"+from_date+"','DD/MM/YYYY')  AND TO_DATE('"+to_date+"','DD/MM/YYYY')  "
							 + "AND NOM_ID LIKE '"+wild+"' "
							 + "ORDER BY NOM_DT, ARRIVAL_TIME DESC";
//				System.out.println("DLNG_WEEKLY_TRUCK_NOM_DTL**: "+queryString4);
				rset2 = stmt.executeQuery(queryString4);
				while(rset2.next()) {
					
					rpt_buy_week_truck_nm.add(rset2.getString(1)==null?"":rset2.getString(1));
					rpt_buy_week_remarks.add(rset2.getString(7)==null?"":rset2.getString(7));
					rpt_buy_week_mmbtu.add(rset2.getString(3)==null?"":rset2.getString(3));
					rpt_buy_week_arrival_tm.add(rset2.getString(2)==null?"":rset2.getString(2));
					rpt_buy_week_mt.add(nf.format(Double.parseDouble(rset2.getString(3)) / convt_mmbtu_to_mt));
					/*rpt_buy_week_delivery_point.add(rset2.getString()==null?"":rset2.getString());*/
					
					totMMBTU+= Double.parseDouble(rset2.getString(3)+"");
					totMT+= Double.parseDouble(rset2.getString(3))/ convt_mmbtu_to_mt;
					
					String mapp_id2[] = rset2.getString(6).split("-");
					
					queryString5 = "SELECT NVL(A.customer_Name,' ') FROM FMS7_CUSTOMER_MST A " +
				   			   "WHERE A.customer_cd='"+mapp_id2[0]+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
				   			   		+ "CUSTOMER_CD='"+mapp_id2[0]+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next())
					{
						rpt_buy_week_customer_nm.add(rset5.getString(1));
					}
					else
					{
						rpt_buy_week_customer_nm.add("");
					}
					queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd="+mapp_id2[0]+" AND A.seq_no="+mapp_id2[6]+" " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						rpt_buy_week_plant_nm.add(rset3.getString(1)==null?"":rset3.getString(1));
					}
					else
					{
						rpt_buy_week_plant_nm.add("");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void fetchSellerWeeklyNomination_rpt() 
		{
			try 
			{
				
				queryString4=""; String wild="%"; 
				queryString4 = "SELECT TRUCK_NM,SCH_TIME, TRUCK_VOL, TRUCK_ENE,MAPPING_ID, SCH_ID,NVL(REMARKS,''),TO_CHAR(SCH_DT,'DD/MM/YYYY') "
							 + "FROM DLNG_WEEKLY_TRUCK_SCH_DTL "
							 + "WHERE  MAPPING_ID LIKE '"+Mapping_id+"' "
							 + "AND SCH_DT between TO_DATE('"+from_date+"','DD/MM/YYYY')  AND TO_DATE('"+to_date+"','DD/MM/YYYY')  "
							 + "AND SCH_ID LIKE '"+Sch_Mapping_id+"' "
							 + "ORDER BY SCH_DT, SCH_TIME DESC";
//				System.out.println("Report DLNG_WEEKLY_TRUCK_SCH_DTL**: "+queryString4);
				rset2 = stmt.executeQuery(queryString4);
				while(rset2.next()) 
				{	
					rpt_sel_week_truck_nm.add(rset2.getString(1)==null?"":rset2.getString(1));
					rpt_sel_week_remarks.add(rset2.getString(7)==null?"":rset2.getString(7));
					rpt_sel_week_mmbtu.add(rset2.getString(3)==null?"":rset2.getString(3));
					rpt_sel_week_schedule_tm.add(rset2.getString(2)==null?"":rset2.getString(2));
					rpt_sel_week_mt.add(nf.format(Double.parseDouble(rset2.getString(3)) / convt_mmbtu_to_mt));
					rpt_sel_week_schDate.add(rset2.getString(8)==null?"":rset2.getString(8));
					
					totMMBTU+= Double.parseDouble(rset2.getString(3)+"");
					totMT+= Double.parseDouble(rset2.getString(3)) / convt_mmbtu_to_mt;
					
					String mapp_id2[] = rset2.getString(6).split("-");
					
					queryString5 = "SELECT NVL(A.customer_Name,' ') FROM FMS7_CUSTOMER_MST A " +
				   			   "WHERE A.customer_cd='"+mapp_id2[0]+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
				   			   		+ "CUSTOMER_CD='"+mapp_id2[0]+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next())
					{
						rpt_sel_week_customer_nm.add(rset5.getString(1));
					}
					else
					{
						rpt_sel_week_customer_nm.add("");
					}
					queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd="+mapp_id2[0]+" AND A.seq_no="+mapp_id2[6]+" " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						rpt_sel_week_plant_nm.add(rset3.getString(1)==null?"":rset3.getString(1));
					}
					else
					{
						rpt_sel_week_plant_nm.add("");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		private void fatchCustnm_ContractTyp() {
			try {
				String custSql="SELECT CUSTOMER_ABBR,CUSTOMER_NAME FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+cust_cd+"'";
				rset = stmt.executeQuery(custSql);
				if(rset.next()) {
				cust_nm=rset.getString(1)==null?"":rset.getString(1);
				custName=rset.getString(2)==null?"":rset.getString(2);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		Vector Vnew_inv_seq_no = new Vector();
		Vector Vinv_dt = new Vector();
		Vector Vtot_qty = new Vector();
		Vector Vgross_amt = new Vector();
		Vector Vtax_amt = new Vector();
		Vector Vnet_payble = new Vector();
		Vector Vdue_dt = new Vector();
		
		private void fetchCustomerWiseInvoiceList() {
		try {
			
			  String invDtlSql = "SELECT NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,GROSS_AMT_INR,TAX_AMT_INR,NET_AMT_INR,TO_CHAR(DUE_DT,'DD/MM/YYYY')"
			  		+ " FROM DLNG_INVOICE_MST "
			  		+ " WHERE CUSTOMER_CD="+cust_cd+""
			  		+ " AND CONTRACT_TYPE='"+cont_typ+"' "
			  		+ " AND PERIOD_START_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
			  		+ " AND FLAG ='Y'"
		  			+ " AND TRUCK_ID IS NOT NULL ORDER BY INVOICE_DT";
//			  System.out.println("invDtlSql***"+invDtlSql);
			  rset = stmt.executeQuery(invDtlSql);
			  while (rset.next()) {
				  Vnew_inv_seq_no.add(rset.getString(1)==null?"":rset.getString(1));
				  Vinv_dt.add(rset.getString(2)==null?"":rset.getString(2));
				  Vtot_qty.add(rset.getString(3)==null?"":rset.getString(3));
				  Vgross_amt.add(rset.getString(4)==null?"":rset.getString(4));
				  Vtax_amt.add(rset.getString(5)==null?"":rset.getString(5));
				  Vnet_payble.add(rset.getString(6)==null?"":rset.getString(6));
				  Vdue_dt.add(rset.getString(7)==null?"":rset.getString(7));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		}
		
		
		Vector Vcust_cd = new Vector ();
		Vector Vcust_nm = new Vector ();
		Vector Vcont_typ = new Vector ();
		private void fetchInvoiceCust()throws SQLException,IOException
		{
			try {
				
				String custCdSql="SELECT DISTINCT(CUSTOMER_CD) FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE in ('S','L')  AND TRUCK_ID IS NOT NULL ORDER BY CUSTOMER_CD";
//				System.out.println("custCdSql***"+custCdSql);
				rset = stmt.executeQuery(custCdSql);
				while (rset.next()) {
					
					String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"'";
					rset1 = stmt1.executeQuery(custAbrSql);
					if(rset1.next()) {
						Vcust_cd.add(rset.getString(1) == null?"":rset.getString(1));
						Vcust_nm.add(rset1.getString(1)  == null?"":rset1.getString(1));
					}
				}
				
				String contTyprSql="SELECT DISTINCT(CONTRACT_TYPE) FROM DLNG_INVOICE_MST WHERE CONTRACT_TYPE in ('S','L')  AND TRUCK_ID IS NOT NULL";
//				System.out.println("contTyprSql***"+contTyprSql);
				rset = stmt.executeQuery(contTyprSql);
				while (rset.next()) {
					Vcont_typ.add(rset.getString(1)==null?"":rset.getString(1));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//Added For Report
		String from_date = "", to_date = "";
		Vector tank_truck_capacityM3=new Vector();//HA20181229
		Vector nomRev=new Vector();
		Vector NomDt=new Vector();
		
		public void fetchDailySchDetails_rpt() 
		{
			methodName = "fetchDailySchDetails_rpt()";
			try 
			{
				Map tempMap = new HashMap();
				/*	queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0'), " + 
							"NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B WHERE " + 
							"((TO_DATE('"+from_date+"','DD/MM/YYYY') BETWEEN A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR " + 
							"(TO_DATE('"+to_date+"','DD/MM/YYYY') BETWEEN A.CONTRACT_START_DT AND A.CONTRACT_END_DT)) " + 
							"AND A.mapping_id=B.mapping_id AND B.CN_NO!='0' ORDER BY A.mapping_id,A.cargo_seq_no";*/
				
				/* *************** for SN based contract*********************** */
				
				queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0') "
						+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY')"
						+ " AND A.end_dt>=TO_DATE('"+to_date+"','DD/MM/YYYY') AND A.status='Y' "
				//SB20200707				+ "AND A.FCC_FLAG='Y' "
						+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
						+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+to_date+"','DD/MM/YYYY') AND B.status='Y')";
//					System.out.println("Fetching SN COntracts.."+queryString1);
					
		//			System.out.println("---"+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
						
						if(!rset1.getString(1).equals("0"))
						{
							queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.flsa_no="+rset1.getString(2)+" " +
									   "AND A.sn_no="+rset1.getString(4)+"";
							rset2 = stmt2.executeQuery(queryString2);
							while(rset2.next())
							{
								queryString4 = "SELECT SUM(NVL(DAY_VOL,'0')) FROM DLNG_DAILY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
										+ "NOM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
										+ "NOM_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"'  and contract_type='S' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID='"+map_id+"' AND NOM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"'  and contract_type='S') ";
								
								
//								System.out.println("daily_Buyer_Nom_Qty_M3******"+queryString4);
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									double qty = rset4.getDouble(1);
									if(qty>0) {
										String map_idTemp = map_id+"-"+rset2.getString(1);
										if(!tempMap.containsKey(map_idTemp)) {
											daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
										
									daily_Buyer_Nom_Qty_Mmbtu.add(nf.format(qty));
									
									queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
												   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
												   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
												   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
												   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
									rset3 = stmt3.executeQuery(queryString3);
									if(rset3.next())
									{
										daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
									}
									else
									{
										daily_Buyer_Nom_Plant_Seq_Abbr.add("");
									}
									
									daily_Seller_Nom_Mapping_Id.add(map_id);
									daily_Seller_Nom_Sn_No.add(rset1.getString(4));
									daily_Seller_Nom_Cd.add(rset1.getString(1));
									
									queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
								   			   		+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
									rset5 = stmt5.executeQuery(queryString5);
									if(rset5.next())
									{
										daily_Seller_Nom_Abbr.add(rset5.getString(1));
									}
									else
									{
										daily_Seller_Nom_Abbr.add("");
									}
									
									
									queryString6 = "SELECT SUM(NVL(DAY_VOL,'0')) FROM DLNG_DAILY_SCH WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
											+ "SCH_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
											+ "SCH_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' and contract_type='S' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID='"+map_id+"' AND SCH_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
											+ "AND SCH_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"'  and contract_type='S') ";
//									System.out.println("*****"+queryString6);
									rset6 = stmt6.executeQuery(queryString6);
									if(rset6.next())
									{
										daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(1)=="0"?"":nf.format(rset6.getDouble(1)));
									}
									else
									{
										daily_Seller_Nom_Qty_Mmbtu.add("");
									}
									
									daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
										}
										tempMap.put(map_idTemp, "");
								}
								}
								
								/*String nomData="select max(rev_no),nom_dt  from  DLNG_DAILY_NOM WHERE PARTY_CD='"+temp_cust_cd+"' AND MAPPING_ID='"+rset1.getString(1)+"'"
										+ " AND NOM_DT"
										+ "  in( select distinct nom_dt from DLNG_DAILY_NOM where nom_dt between  TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY')"
										+ " AND NOM_ID LIKE '"+rset1.getString(1)+"-%-"+rset2.getString(1)+"') group by nom_dt";
								System.out.println("nomData****"+nomData);	
								rset=stmt.executeQuery(nomData);
									while(rset.next()){
										nomRev.add(rset.getString(1));
										NomDt.add(rset.getString(2));
									}*/
							}
						}
					}
					
					/* *************** for LoA based contract******************* */
					
					queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+to_date+"','DD/MM/YYYY') AND A.status='Y' AND A.FCC_FLAG='Y' "
							+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+to_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+from_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
//					System.out.println("LoA Contract****"+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4);
						
						if(!rset1.getString(1).equals("0"))
						{
							queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.tender_no="+rset1.getString(2)+" " +
									   "AND A.loa_no="+rset1.getString(3)+"";
							rset2 = stmt2.executeQuery(queryString2);
							while(rset2.next())
							{
								queryString4 = "SELECT SUM(NVL(DAY_VOL,'0')) FROM DLNG_DAILY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
										+ "NOM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
										+ "NOM_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' and contract_type='L' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID='"+map_id+"' AND NOM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' and contract_type='L') ";
								
								
//								System.out.println("daily_Buyer_Nom_Qty_M3******"+queryString4);
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									double qty = rset4.getDouble(1);
									if(qty>0) {
										String map_idTemp = map_id+"-"+rset2.getString(1);
										if(!tempMap.containsKey(map_idTemp)) {
											daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
										
									daily_Buyer_Nom_Qty_Mmbtu.add(nf.format(qty));
									
									queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
												   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
												   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
												   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
												   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
									rset3 = stmt3.executeQuery(queryString3);
									if(rset3.next())
									{
										daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
									}
									else
									{
										daily_Buyer_Nom_Plant_Seq_Abbr.add("");
									}
									
									daily_Seller_Nom_Mapping_Id.add(map_id);
									daily_Seller_Nom_Sn_No.add(rset1.getString(4));
									daily_Seller_Nom_Cd.add(rset1.getString(1));
									
									queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
								   			   		+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
									rset5 = stmt5.executeQuery(queryString5);
									if(rset5.next())
									{
										daily_Seller_Nom_Abbr.add(rset5.getString(1));
									}
									else
									{
										daily_Seller_Nom_Abbr.add("");
									}
									
									
									queryString6 = "SELECT SUM(NVL(DAY_VOL,'0')) FROM DLNG_DAILY_SCH WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
											+ "SCH_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
											+ "SCH_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' and contract_type='L'"
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID='"+map_id+"' AND SCH_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
											+ "AND SCH_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' and contract_type='L') ";
//									System.out.println("*****"+queryString6);
									rset6 = stmt6.executeQuery(queryString6);
									if(rset6.next())
									{
										daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(1)=="0"?"":nf.format(rset6.getDouble(1)));
									}
									else
									{
										daily_Seller_Nom_Qty_Mmbtu.add("");
									}
									
									daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
										}
										tempMap.put(map_idTemp, "");
								}
								}
								
							}
						}
					}
					
					/* ********************************************************** */
					//Fetching Tank Truck Details ...
					
					queryString = "SELECT DISTINCT TRUCK_ID,TRUCK_NM,NVL(TANK_VOL_M3,'0') FROM DLNG_TANK_TRUCK_MST WHERE EFF_DT <= TO_DATE('"+to_date+"','DD/MM/YYYY') ";
//					System.out.println("Fetching distinct Truck Details..."+queryString);
					rset = stmt.executeQuery(queryString);
					while(rset.next()) {
						tank_truck_id.add(rset.getString(1));
						tank_truck_nm.add(rset.getString(2));
						tank_truck_capacity.add(nf.format(rset.getDouble(3)*conversion_factor_from_m3_to_mmbtu));
						tank_truck_capacityM3.add(rset.getDouble(3));//HA20181229
					}
					
					int no_of_days = -1;
					queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+from_date+"','DD/MM/YYYY')+1 FROM DUAL" ;
//					System.out.println("==="+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						no_of_days = rset.getInt(1);
					}
					
					for(int i=no_of_days;i>=0;i--) {
						queryString = "SELECT TO_CHAR(TO_DATE('"+to_date+"','DD/MM/YYYY')-"+i+",'DD/MM/YYYY') FROM DUAL";
//						System.out.println("Fetching all dates..."+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next()) {
							all_dates.add(rset.getString(1));
						} 
					}
					
					
					for(int i=0;i<daily_Seller_Nom_Plant_Seq_No.size();i++) {
						String key = daily_Seller_Nom_Mapping_Id.elementAt(i)+"-%-"+daily_Seller_Nom_Plant_Seq_No.elementAt(i);
						
						String date = "";//HA20181231
						
						for(int j=0;j<all_dates.size();j++) {
							String map_key = key +"-"+ all_dates.elementAt(j);
						
							//Fetch already allocated data....
							/*queryString = "SELECT TO_CHAR(LOAD_START_TM,'DD/MM/YYYY'),TO_CHAR(LOAD_START_TM,'HH24:MI'),TO_CHAR(LOAD_END_TM,'DD/MM/YYYY')," + 
									"TO_CHAR(LOAD_END_TM,'HH24:MI'),NVL(LOADED_VOL,'0'),A.SUP_TRN_CD,NVL(A.EXIT_ALLOC_VOL,'0') FROM DLNG_ALLOC_MST A,DLNG_TANK_VOL_DTL B "
									+ "WHERE A.SUP_TRN_CD=B.TRN_CD AND A.MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND A.ALLOC_ID LIKE '"+key+"' AND "
									+ "A.PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND A.ALLOC_DT=B.EFF_DT AND A.ALLOC_DT = TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY')";*/
							
							/*queryString = "SELECT TO_CHAR(LOAD_START_TM,'DD/MM/YYYY'),TO_CHAR(LOAD_START_TM,'HH24:MI'),TO_CHAR(LOAD_END_TM,'DD/MM/YYYY')," + 
									"TO_CHAR(LOAD_END_TM,'HH24:MI'),NVL(LOADED_VOL,'0'),A.SUP_TRN_CD,NVL(B.UNLOADED_VOL,'0') FROM DLNG_ALLOC_MST A,DLNG_TANK_VOL_DTL B "
									+ "WHERE A.SUP_TRN_CD=B.TRN_CD AND A.MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND A.ALLOC_ID LIKE '"+key+"' AND "
									+ "A.PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND A.ALLOC_DT=B.EFF_DT AND A.ALLOC_DT = TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY')";
							*/
							queryString="SELECT TO_CHAR(LOAD_START_TM,'DD/MM/YYYY'),TO_CHAR(LOAD_START_TM,'HH24:MI'),"
									+ " TO_CHAR(LOAD_END_TM,'DD/MM/YYYY'),TO_CHAR(LOAD_END_TM,'HH24:MI'),NVL(LOADED_VOL,'0'),"
									+ " TRN_CD,NVL(UNLOADED_VOL,'0'),NVL(LOADED_ENE,'0')  FROM DLNG_TANK_VOL_DTL WHERE  SCH_ID LIKE '"+key+"' and"
									+ "	EFF_DT = TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY')";						
							
//							System.out.println("--Fetching Truck Data--"+queryString);
							rset = stmt.executeQuery(queryString);
							while(rset.next()) {
								truck_load_start_day.put(map_key+"-"+rset.getString(6), rset.getString(1));
								truck_load_start_tm.put(map_key+"-"+rset.getString(6), rset.getString(2));
								truck_load_end_tm.put(map_key+"-"+rset.getString(6), rset.getString(4));
								truck_load_end_day.put(map_key+"-"+rset.getString(6), rset.getString(3));
								
//								double loaded_vol = rset.getDouble(5);
								double loaded_vol = rset.getDouble(5);
								double unloaded_vol = rset.getDouble(7);
								if(truck_loaded_vol.containsKey(map_key+"-"+rset.getString(6))) {
									loaded_vol += Double.parseDouble(""+truck_loaded_vol.get(map_key+"-"+rset.getString(6)));
								}
								
								if(truck_unloaded_vol.containsKey(map_key+"-"+rset.getString(6))) {
									unloaded_vol += Double.parseDouble(""+truck_unloaded_vol.get(map_key+"-"+rset.getString(6)));
								}
								truck_loaded_vol.put(map_key+"-"+rset.getString(6), rset.getString(5)==null?"0.00":nf.format(loaded_vol));
								truck_unloaded_vol.put(map_key+"-"+rset.getString(6), rset.getString(7)==null?"0.00":nf.format(unloaded_vol));
								truck_loaded_ene.put(map_key+"-"+rset.getString(6), rset.getString(8));
							}
							
							queryString4 = "SELECT NVL(DAY_VOL,'0'),NOM_DT FROM DLNG_DAILY_NOM WHERE "
									+ "PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND "
									+ "NOM_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') AND "
									+ "NOM_ID LIKE '"+key+"' "
									+ "AND REV_NO = ("
									+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND "
									+ "MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND NOM_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') "
									+ "AND NOM_ID LIKE '"+key+"') ";
							
//							System.out.println("queryString4****"+queryString4);
							rset1 = stmt.executeQuery(queryString4);
							while(rset1.next()) {
								
								nomDt.put(map_key,rset1.getString(2));
								nomQty.put(map_key,rset1.getString(1));
							}
							queryString4="";
							queryString4 = "SELECT NVL(DAY_VOL,'0'),SCH_DT FROM DLNG_DAILY_SCH WHERE "
									+ "PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND "
									+ "SCH_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') AND "
									+ "SCH_ID LIKE '"+key+"' "
									+ "AND REV_NO = ("
									+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND "
									+ "MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND SCH_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') "
									+ "AND SCH_ID LIKE '"+key+"') ";
//							System.out.println("queryString4******"+queryString4);
							rset2 = stmt.executeQuery(queryString4);
							while(rset2.next()) {
								
								schDt.put(map_key,rset2.getString(2));
								schQty.put(map_key,rset2.getString(1));
							}
							
							queryString4="";
							queryString4 = "SELECT NVL(EXIT_TOT_ENE,'0'),ALLOC_DT,NVL(ENTRY_ALLOC_VOL,0) FROM DLNG_ALLOC_MST WHERE "
									+ " PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND "
									+ " ALLOC_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') AND "
									+ " ALLOC_ID LIKE '"+key+"' "
									+ " AND NOM_REV_NO = ("
									+ " SELECT MAX(NOM_REV_NO) FROM DLNG_ALLOC_MST WHERE PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' "
									+ " AND MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND ALLOC_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') "
									+ " AND ALLOC_ID LIKE '"+key+"') ";
//							System.out.println("queryString4******"+queryString4);
							rset2 = stmt.executeQuery(queryString4);
							while(rset2.next()) {
								System.out.println("rset2.getString(1)****"+rset2.getString(1));
								loadedDt.put(map_key,rset2.getString(2));
								loadedQty.put(map_key,rset2.getString(1));
								loadedQtyMt.put(map_key,rset2.getString(3));
							}
						}
					}
					
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		////////////////////////////
	public void fetchDailySchTruckDetails_rpt()
	{
		methodName = "fetchDailySchTruckDetails_rpt()";
		try 
		{
			Map tempMap = new HashMap();
			/*	queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0'), " + 
						"NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B WHERE " + 
						"((TO_DATE('"+from_date+"','DD/MM/YYYY') BETWEEN A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR " + 
						"(TO_DATE('"+to_date+"','DD/MM/YYYY') BETWEEN A.CONTRACT_START_DT AND A.CONTRACT_END_DT)) " + 
						"AND A.mapping_id=B.mapping_id AND B.CN_NO!='0' ORDER BY A.mapping_id,A.cargo_seq_no";*/
			
			/* *************** for SN based contract*********************** */
		/*	
			queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0') "
					+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY')"
					+ " AND A.end_dt>=TO_DATE('"+to_date+"','DD/MM/YYYY') AND A.status='Y' "
			//SB20200707				+ "AND A.FCC_FLAG='Y' "
					+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
					+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+to_date+"','DD/MM/YYYY') AND B.status='Y')";
				System.out.println("Fetching SN COntracts.."+queryString1);
				
	//			System.out.println("---"+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
								   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.flsa_no="+rset1.getString(2)+" " +
								   "AND A.sn_no="+rset1.getString(4)+"";
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							queryString4 = "SELECT SUM(NVL(DAY_VOL,'0')) FROM DLNG_DAILY_NOM WHERE "
									+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
									+ "NOM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
									+ "NOM_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' "
									+ "AND REV_NO = ("
									+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
									+ "MAPPING_ID='"+map_id+"' AND NOM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
									+ "AND NOM_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"') ";
							
							
//							System.out.println("daily_Buyer_Nom_Qty_M3******"+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								double qty = rset4.getDouble(1);
								if(qty>0) {
									String map_idTemp = map_id+"-"+rset2.getString(1);
									if(!tempMap.containsKey(map_idTemp)) {
										daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
									
								daily_Buyer_Nom_Qty_Mmbtu.add(nf.format(qty));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Mapping_Id.add(map_id);
								daily_Seller_Nom_Sn_No.add(rset1.getString(4));
								daily_Seller_Nom_Cd.add(rset1.getString(1));
								
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
							   			   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
							   			   		+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
								}
								
								
								queryString6 = "SELECT SUM(NVL(DAY_VOL,'0')) FROM DLNG_DAILY_SCH WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
										+ "SCH_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
										+ "SCH_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID='"+map_id+"' AND SCH_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
										+ "AND SCH_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"') ";
//								System.out.println("*****"+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(1)=="0"?"":nf.format(rset6.getDouble(1)));
								}
								else
								{
									daily_Seller_Nom_Qty_Mmbtu.add("");
								}
								
								daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
									}
									tempMap.put(map_idTemp, "");
							}
							}
							
						}
					}
				}
				*/
				/* *************** for LoA based contract******************* */
			/*	
				queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
						+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY')"
						+ " AND A.end_dt>=TO_DATE('"+to_date+"','DD/MM/YYYY') AND A.status='Y' AND A.FCC_FLAG='Y' "
						+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
						+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+to_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+from_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
				System.out.println("LoA Contract****"+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4);
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
								   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.tender_no="+rset1.getString(2)+" " +
								   "AND A.loa_no="+rset1.getString(3)+"";
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							queryString4 = "SELECT SUM(NVL(DAY_VOL,'0')) FROM DLNG_DAILY_NOM WHERE "
									+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
									+ "NOM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
									+ "NOM_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' "
									+ "AND REV_NO = ("
									+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
									+ "MAPPING_ID='"+map_id+"' AND NOM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
									+ "AND NOM_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"') ";
							
							
//							System.out.println("daily_Buyer_Nom_Qty_M3******"+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								double qty = rset4.getDouble(1);
								if(qty>0) {
									String map_idTemp = map_id+"-"+rset2.getString(1);
									if(!tempMap.containsKey(map_idTemp)) {
										daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
									
								daily_Buyer_Nom_Qty_Mmbtu.add(nf.format(qty));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Mapping_Id.add(map_id);
								daily_Seller_Nom_Sn_No.add(rset1.getString(4));
								daily_Seller_Nom_Cd.add(rset1.getString(1));
								
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
							   			   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
							   			   		+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
								}
								
								
								queryString6 = "SELECT SUM(NVL(DAY_VOL,'0')) FROM DLNG_DAILY_SCH WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
										+ "SCH_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
										+ "SCH_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID='"+map_id+"' AND SCH_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND TO_DATE('"+to_date+"','DD/MM/YYYY') "
										+ "AND SCH_ID LIKE '"+map_id+"-%-"+rset2.getString(1)+"') ";
//								System.out.println("*****"+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(1)=="0"?"":nf.format(rset6.getDouble(1)));
								}
								else
								{
									daily_Seller_Nom_Qty_Mmbtu.add("");
								}
								
								daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
									}
									tempMap.put(map_idTemp, "");
							}
							}
							
						}
					}
				}
				*/
				/* ********************************************************** */
				//Fetching Tank Truck Details ...
		/*		
				queryString = "SELECT DISTINCT TRUCK_ID,TRUCK_NM,NVL(TANK_VOL_M3,'0') FROM DLNG_TANK_TRUCK_MST WHERE EFF_DT <= TO_DATE('"+to_date+"','DD/MM/YYYY') ";
//				System.out.println("Fetching distinct Truck Details..."+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					tank_truck_id.add(rset.getString(1));
					tank_truck_nm.add(rset.getString(2));
					tank_truck_capacity.add(nf.format(rset.getDouble(3)*conversion_factor_from_m3_to_mmbtu));
					tank_truck_capacityM3.add(rset.getDouble(3));//HA20181229
				}
				
				int no_of_days = -1;
				queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+from_date+"','DD/MM/YYYY')+1 FROM DUAL" ;
//				System.out.println("==="+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					no_of_days = rset.getInt(1);
				}
				
				for(int i=no_of_days;i>=0;i--) {
					queryString = "SELECT TO_CHAR(TO_DATE('"+to_date+"','DD/MM/YYYY')-"+i+",'DD/MM/YYYY') FROM DUAL";
//					System.out.println("Fetching all dates..."+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						all_dates.add(rset.getString(1));
					} 
				}
			*/	
			/*	
				for(int i=0;i<daily_Seller_Nom_Plant_Seq_No.size();i++) {
					String key = daily_Seller_Nom_Mapping_Id.elementAt(i)+"-%-"+daily_Seller_Nom_Plant_Seq_No.elementAt(i);
					
					String date = "";//HA20181231
					
					for(int j=0;j<all_dates.size();j++) {
						String map_key = key +"-"+ all_dates.elementAt(j);
					
						//Fetch already allocated data....
						
						queryString="SELECT TO_CHAR(LOAD_START_TM,'DD/MM/YYYY'),TO_CHAR(LOAD_START_TM,'HH24:MI'),"
								+ " TO_CHAR(LOAD_END_TM,'DD/MM/YYYY'),TO_CHAR(LOAD_END_TM,'HH24:MI'),NVL(LOADED_VOL,'0'),"
								+ " TRN_CD,NVL(UNLOADED_VOL,'0')  FROM DLNG_TANK_VOL_DTL WHERE  SCH_ID LIKE '"+key+"' and"
								+ "	EFF_DT = TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY')";						
						
//						System.out.println("--Fetching Truck Data--"+queryString);
						rset = stmt.executeQuery(queryString);
						while(rset.next()) {
							truck_load_start_day.put(map_key+"-"+rset.getString(6), rset.getString(1));
							truck_load_start_tm.put(map_key+"-"+rset.getString(6), rset.getString(2));
							truck_load_end_tm.put(map_key+"-"+rset.getString(6), rset.getString(4));
							truck_load_end_day.put(map_key+"-"+rset.getString(6), rset.getString(3));
							
//							double loaded_vol = rset.getDouble(5);
							double loaded_vol = rset.getDouble(5);
							double unloaded_vol = rset.getDouble(7);
							if(truck_loaded_vol.containsKey(map_key+"-"+rset.getString(6))) {
								loaded_vol += Double.parseDouble(""+truck_loaded_vol.get(map_key+"-"+rset.getString(6)));
							}
							
							if(truck_unloaded_vol.containsKey(map_key+"-"+rset.getString(6))) {
								unloaded_vol += Double.parseDouble(""+truck_unloaded_vol.get(map_key+"-"+rset.getString(6)));
							}
							truck_loaded_vol.put(map_key+"-"+rset.getString(6), rset.getString(5)==null?"0.00":nf.format(loaded_vol));
							truck_unloaded_vol.put(map_key+"-"+rset.getString(6), rset.getString(7)==null?"0.00":nf.format(unloaded_vol));
						}
						
						queryString4 = "SELECT NVL(DAY_VOL,'0'),NOM_DT FROM DLNG_DAILY_NOM WHERE "
								+ "PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND "
								+ "NOM_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') AND "
								+ "NOM_ID LIKE '"+key+"' "
								+ "AND REV_NO = ("
								+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND "
								+ "MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND NOM_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') "
								+ "AND NOM_ID LIKE '"+key+"') ";
						
//						System.out.println("queryString4****"+queryString4);
						rset1 = stmt.executeQuery(queryString4);
						while(rset1.next()) {
							
							nomDt.put(map_key,rset1.getString(2));
							nomQty.put(map_key,rset1.getString(1));
						}
						queryString4="";
						queryString4 = "SELECT NVL(DAY_VOL,'0'),SCH_DT FROM DLNG_DAILY_SCH WHERE "
								+ "PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND "
								+ "SCH_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') AND "
								+ "SCH_ID LIKE '"+key+"' "
								+ "AND REV_NO = ("
								+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND "
								+ "MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND SCH_DT =TO_DATE('"+all_dates.elementAt(j)+"','DD/MM/YYYY') "
								+ "AND SCH_ID LIKE '"+key+"') ";
//						System.out.println("queryString4******"+queryString4);
						rset2 = stmt.executeQuery(queryString4);
						while(rset2.next()) {
							
							schDt.put(map_key,rset2.getString(2));
							schQty.put(map_key,rset2.getString(1));
						}
					}
				}
	*/
				///////////////////////////////////////SB20200818////////////////////////////////
				queryString4=""; String wild="%"; //HA20200403
				Vector dist_sch_Id = new Vector();
				Vector hidden_recd = new Vector();
				Vector Vcnt = new Vector();
				Vector VdateList = new Vector();
				
				/*Hiren_20210206 fetching date list*/
				if(!from_date.equals("") && !to_date.equals("")) {
					String dateListSql = "SELECT TO_DATE('"+from_date+"', 'DD/MM/YYYY') - 1 + rownum AS d"
							+ " FROM all_objects"
							+ " WHERE TO_DATE('"+from_date+"', 'DD/MM/YYYY') - 1 + rownum <= TO_DATE('"+to_date+"', 'DD/MM/YYYY')";
					System.out.println("dateListSql*******"+dateListSql);
					rset = stmt.executeQuery(dateListSql);
					while (rset.next()) {
						VdateList.add(rset.getString(1)==null?"":rset.getString(1));
					}
				}
				System.out.println("VdateList****"+VdateList);
				
				
				
				String schId_sql = "select distinct(SCH_ID) from DLNG_DAILY_TRUCK_SCH_DTL "
						+ " where SCH_DT >=TO_DATE('"+from_date+"','DD/MM/YYYY')  AND SCH_DT <=TO_DATE('"+to_date+"','DD/MM/YYYY')";
				System.out.println("schId_sql**********"+schId_sql);
				rset = stmt.executeQuery(schId_sql);
				while (rset.next()) {
					
					dist_sch_Id.add(rset.getString(1)==null?"":rset.getString(1));
				}
				int cnt=0;
				for (int i = 0 ; i < dist_sch_Id.size() ; i++) {
					
					for(int j = 0 ; j < VdateList.size() ; j++) {
						
						cnt=0;
						DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
				        Date date = originalFormat.parse(VdateList.elementAt(j)+"");
				        String formattedDate = targetFormat.format(date); 
//				        System.out.println(formattedDate);
						
				        String rev_no="";
				        /*for header data*/
				        queryString4 = "SELECT TRUCK_NM,TO_CHAR(SCH_DT,'DD/MM/YYYY'), SCH_TIME, TRUCK_VOL, UNIT_VOL, TRUCK_ENE, UNIT_ENE, MAPPING_ID, SCH_ID, TRANS_NAME, NVL(REMARKS,''),REV_NO"
								+ " FROM DLNG_DAILY_TRUCK_SCH_DTL A, DLNG_TRANS_MST B "
								+ " WHERE A.TRANS_Cd=B.TRANS_CD AND "
								+ " SCH_ID = '"+dist_sch_Id.elementAt(i)+"' "
								+ " AND SCH_DT =TO_DATE('"+formattedDate+"','DD/MM/YYYY') "
								+ " AND REV_NO  = (select max(rev_no) from DLNG_DAILY_TRUCK_SCH_DTL C where SCH_ID = '"+dist_sch_Id.elementAt(i)+"' and SCH_DT =TO_DATE('"+formattedDate+"','DD/MM/YYYY')) "
								+ " ORDER BY SCH_DT, SCH_TIME,REV_NO DESC";
						System.out.println("MAX REV NO DLNG_DAILY_TRUCK_SCH_DTL DATA****"+queryString4);
						rset = stmt.executeQuery(queryString4);
						if(rset.next()) {
							rev_no = rset.getString(12)==null?"":rset.getString(12);
							
							HTruck_Nm.add(rset.getString(1));
							HTruck_Sch_Dt.add(rset.getString(2));
							HTruck_Sch_Tm.add(rset.getString(3));
							HTruck_Vol.add(rset.getString(4));
							HTruck_Vol_Unit.add(rset.getString(5));
							HTruck_Ene.add(rset.getString(6));
							HTruck_Ene_Unit.add(rset.getString(7));
							HMapp_Id.add(rset.getString(8));
							HSch_Id.add(rset.getString(9));
							HTruck_Trans_Nm.add(rset.getString(10));
							HTruck_Seller_Remarks.add(rset.getString(11)==null?"-":rset.getString(11));
							HTruck_Load_Dt.add("");
							HTruck_Load_Tm.add("");
							
							HTruck_Wt.add(nf.format(Double.parseDouble(rset.getString(4)) / convt_mmbtu_to_mt));
							HTruck_Wt_Unit.add("MT");
							String mapp_id[] = rset.getString(9).split("-");
							
							queryString5 = "SELECT NVL(A.customer_Name,' ') FROM FMS7_CUSTOMER_MST A " +
						   			   "WHERE A.customer_cd='"+mapp_id[0]+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
						   			   		+ "CUSTOMER_CD='"+mapp_id[0]+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
		//					System.out.println("queryString5*****"+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								HCust_Nm.add(rset5.getString(1));
							}
							else
							{
								HCust_Nm.add("");
							}
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+mapp_id[0]+" AND A.seq_no="+mapp_id[6]+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
//							System.out.println("queryString3*****"+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								HCust_Plant_Nm.add(rset3.getString(1)==null?"":rset3.getString(1));
							}
							else
							{
								HCust_Plant_Nm.add("");
							}
							
							String truckIdSql="select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset.getString(1)+"' ";
		//					System.out.println("truckIdSql****"+truckIdSql);
							rset1 = stmt1.executeQuery(truckIdSql);
							if(rset1.next()) {
								Htruck_driver = fetchtruckTransDriverDtl(rset1.getString(1));
								Hdriver_nm.add(truck_driver);
							}
						}
				     if(!rev_no.equals("")) {  
				    	 System.out.println("rev_no********"+rev_no);
							queryString4 = "SELECT TRUCK_NM,TO_CHAR(SCH_DT,'DD/MM/YYYY'), SCH_TIME, TRUCK_VOL, UNIT_VOL, TRUCK_ENE, UNIT_ENE, MAPPING_ID, SCH_ID, TRANS_NAME, NVL(REMARKS,''),REV_NO"
									+ " FROM DLNG_DAILY_TRUCK_SCH_DTL A, DLNG_TRANS_MST B "
									+ " WHERE A.TRANS_Cd=B.TRANS_CD AND "
									+ " SCH_ID = '"+dist_sch_Id.elementAt(i)+"' "
									+ " AND SCH_DT =TO_DATE('"+formattedDate+"','DD/MM/YYYY') "
									+ " AND rev_no!='"+rev_no+"' "
									+ " ORDER BY SCH_DT, SCH_TIME,REV_NO DESC";
							System.out.println("DLNG_DAILY_TRUCK_SCH_DTL****"+queryString4);
							rset2 = stmt.executeQuery(queryString4);
							while(rset2.next()) {
								
								cnt++;
								VTruck_Nm.add(rset2.getString(1));
								VTruck_Sch_Dt.add(rset2.getString(2));
								VTruck_Sch_Tm.add(rset2.getString(3));
								VTruck_Vol.add(rset2.getString(4));
								VTruck_Vol_Unit.add(rset2.getString(5));
								VTruck_Ene.add(rset2.getString(6));
								VTruck_Ene_Unit.add(rset2.getString(7));
								VMapp_Id.add(rset2.getString(8));
								VSch_Id.add(rset2.getString(9));
								VTruck_Trans_Nm.add(rset2.getString(10));
								VTruck_Seller_Remarks.add(rset2.getString(11)==null?"-":rset2.getString(11));
								VTruck_Load_Dt.add("");
								VTruck_Load_Tm.add("");
								
								VTruck_Wt.add(nf.format(Double.parseDouble(rset2.getString(4)) / convt_mmbtu_to_mt));
								VTruck_Wt_Unit.add("MT");
								String mapp_id[] = rset2.getString(9).split("-");
								
								queryString5 = "SELECT NVL(A.customer_Name,' ') FROM FMS7_CUSTOMER_MST A " +
							   			   "WHERE A.customer_cd='"+mapp_id[0]+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
							   			   		+ "CUSTOMER_CD='"+mapp_id[0]+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
			//					System.out.println("queryString5*****"+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									VCust_Nm.add(rset5.getString(1));
								}
								else
								{
									VCust_Nm.add("");
								}
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+mapp_id[0]+" AND A.seq_no="+mapp_id[6]+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
//								System.out.println("queryString3*****"+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									VCust_Plant_Nm.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									VCust_Plant_Nm.add("");
								}
								
								String truckIdSql="select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset2.getString(1)+"' ";
			//					System.out.println("truckIdSql****"+truckIdSql);
								rset1 = stmt1.executeQuery(truckIdSql);
								if(rset1.next()) {
									truck_driver = fetchtruckTransDriverDtl(rset1.getString(1));
									driver_nm.add(truck_driver);
								}
							}
					     }
				     VCnt.add(cnt);
					}
				}
				System.out.println("Vcnt**: "+Vcnt);
					////////////////////Truck List which have already loaded ////////////////////
					queryString4 = "SELECT TRUCK_NM,TO_CHAR(SCH_DT,'DD/MM/YYYY'), SCH_TIME, TRUCK_VOL, UNIT_VOL, TRUCK_ENE, UNIT_ENE, MAPPING_ID, SCH_ID, TRANS_NAME, NVL(REMARKS,'')"
							+ " FROM DLNG_DAILY_TRUCK_SCH_DTL A, DLNG_TRANS_MST B "
							+ " WHERE A.TRANS_Cd=B.TRANS_CD AND "
							+ " MAPPING_ID LIKE '"+wild+"' AND SCH_DT >=TO_DATE('"+from_date+"','DD/MM/YYYY')  AND SCH_DT <=TO_DATE('"+to_date+"','DD/MM/YYYY') AND "
							+ "SCH_ID LIKE '"+wild+"' "
	//Hiren_20201223        + "AND TRUCK_NM IN (SELECT TRUCK_NM FROM DLNG_TANK_TRUCK_MST A, DLNG_ALLOC_MST B WHERE B.SUP_TRN_CD=A.TRUCK_ID) "
							+ " ORDER BY SCH_DT, SCH_TIME DESC";
					System.out.println("All Ready loaded DLNG_DAILY_TRUCK_SCH_DTL**: "+queryString4);
					rset2 = stmt.executeQuery(queryString4);
					while(rset2.next()) {/*
						
						VTruck_Nm.add(rset2.getString(1));
						VTruck_Sch_Dt.add(rset2.getString(2));
						VTruck_Sch_Tm.add(rset2.getString(3));
						VTruck_Vol.add(rset2.getString(4));
						VTruck_Vol_Unit.add(rset2.getString(5));
						VTruck_Ene.add(rset2.getString(6));
						VTruck_Ene_Unit.add(rset2.getString(7));
						VMapp_Id.add(rset2.getString(8));
						VSch_Id.add(rset2.getString(9));
						VTruck_Trans_Nm.add(rset2.getString(10));
						VTruck_Seller_Remarks.add(rset2.getString(11)==null?"-":rset2.getString(11));
						VTruck_Load_Dt.add("Already Loaded");
						VTruck_Load_Tm.add("");
						VTruck_Wt.add(nf.format(Double.parseDouble(rset2.getString(4)) / convt_mmbtu_to_mt));
						VTruck_Wt_Unit.add("MT");
						String mapp_id2[] = rset2.getString(9).split("-");
						
						queryString5 = "SELECT NVL(A.customer_Name,' ') FROM FMS7_CUSTOMER_MST A " +
					   			   "WHERE A.customer_cd='"+mapp_id2[0]+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
					   			   		+ "CUSTOMER_CD='"+mapp_id2[0]+"' AND EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
						rset5 = stmt5.executeQuery(queryString5);
						if(rset5.next())
						{
							VCust_Nm.add(rset5.getString(1));
						}
						else
						{
							VCust_Nm.add("");
						}
						queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
								   "WHERE A.customer_cd="+mapp_id2[0]+" AND A.seq_no="+mapp_id2[6]+" " +
								   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
								   "AND B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
//						System.out.println("queryString32*****"+queryString3);
						rset3 = stmt3.executeQuery(queryString3);
						if(rset3.next())
						{
							VCust_Plant_Nm.add(rset3.getString(1)==null?"":rset3.getString(1));
						}
						else
						{
							VCust_Plant_Nm.add("");
						}
						
						String truckIdSql="select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset2.getString(1)+"' ";
//						System.out.println("truckIdSql****"+truckIdSql);
						rset1 = stmt1.executeQuery(truckIdSql);
						if(rset1.next()) {
							truck_driver = fetchtruckTransDriverDtl(rset1.getString(1));
							driver_nm.add(truck_driver);
						}
						
					*/}
					System.out.println(driver_nm.size()+"**********"+VTruck_Nm.size());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
	
	String truck_trans_nm="";
	String truck_driver="";
	String truck_driver_addrs="";
	String truck_lic_state="";
	String truck_nm="";
	String truck_trans_addrs="";
	String truckLinkedFlg="N";
	String truck_driver_lic_no="";
	Vector driver_nm = new Vector();
	Vector Hdriver_nm = new Vector();
	String Htruck_driver = "";
	
	private String fetchtruckTransDriverDtl(String truck_cd)throws SQLException,IOException {
		try {
			truck_driver = "";
			String truck_sql="select a.TRANS_CD,a.LICENSE_NO,b.TRUCK_NM  from DLNG_TRUCK_DRIVER_LINK_MST a, DLNG_TANK_TRUCK_MST b"
					+ " where a.TRUCK_ID='"+truck_cd+"' and a.TRUCK_ID=b.TRUCK_ID";
//			System.out.println("truck_sql*****"+truck_sql);
			rset = stmt3.executeQuery(truck_sql);
			if(rset.next()) {
				truck_nm = rset.getString(3)==null?"":rset.getString(3);
				truck_driver_lic_no= rset.getString(2)==null?"":rset.getString(2);
				
				String trans_sql="select TRANS_NAME,WEB_ADDR from DLNG_TRANS_MST where TRANS_CD='"+rset.getString(1)+"'";
//				System.out.println("trans_sql****"+trans_sql);
				rset3 = stmt4.executeQuery(trans_sql);
				if(rset3.next()) {
					truck_trans_nm = rset3.getString(1)==null?"":rset3.getString(1);
					truck_trans_addrs = rset3.getString(2)==null?"":rset3.getString(2);
				}
				
				
				String driver_sql="select a.DRIVER_NAME,a.ADDRESS from DLNG_DRIVER_MST a"
						+ " where LICENSE_NO = '"+rset.getString(2)+"' ";
//				System.out.println("driver_sql****"+driver_sql);
				rset3 = stmt4.executeQuery(driver_sql);
				if(rset3.next()) {
					truck_driver = rset3.getString(1)==null?"":rset3.getString(1);
					truck_driver_addrs = rset3.getString(2)==null?"":rset3.getString(2);
//					truck_lic_state = rset1.getString(3)==null?"":rset1.getString(3);
				}
//				System.out.println("truck_driver****"+truck_driver);
				truckLinkedFlg ="Y";
			}else {
				truckLinkedFlg ="N";
			}
			
			/*System.out.println("truckLinkedFlg****"+truckLinkedFlg);
			System.out.println("truck_nm****"+truck_nm);
			System.out.println("truck_trans_nm****"+truck_trans_nm);
			System.out.println("truck_trans_addrs****"+truck_trans_addrs);
			System.out.println("truck_driver****"+truck_driver);
			System.out.println("truck_driver_addrs****"+truck_driver_addrs);*/
		}catch (Exception e) {
			e.printStackTrace();
		}
		return truck_driver;
	}
	//////////////////////////////////////////////////
		
		Map rpt_map_id = new HashMap();
		Map rpt_party_cd = new HashMap();
		Map rpt_day_vol = new HashMap();
		Map rpt_day_unit = new HashMap();
		Map rpt_party_nm = new HashMap();
		Map rpt_allocation_qty = new HashMap();
		Map rpt_cust_nm = new HashMap();
		Map rpt_fsru_vol = new HashMap();
		Map rpt_int_vol = new HashMap();
		Vector all_dates = new Vector();
		Vector rpt_sch_mapping_id = new Vector();
		Vector rpt_sch_party_cd = new Vector();
		Vector rpt_sch_day_vol = new Vector();
		Vector rpt_sch_unit_vol = new Vector();
		Vector rpt_sch_party_nm = new Vector();
		Vector rpt_alloc_qty = new Vector();
		Vector rpt_sch_cust_nm = new Vector();
		Vector rpt_sch_id = new Vector();
		String rpt_dt = "18/12/2018";
		String rpt_fsru_tank_vol = "0.00", rpt_int_tank_vol = "0.00";
		
		
		public void checkColumn() throws Exception {
			try {
				String query = "SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME)='DLNG_ALLOC_MST' AND UPPER(COLUMN_NAME)='INVOICE_FLAG' ";
				rset = stmt.executeQuery(query);
				if(rset.next()) {
					int count = rset.getInt(1);
					if(count==0) {
						query = "ALTER TABLE DLNG_ALLOC_MST ADD INVOICE_FLAG CHAR(1)";
						stmt.executeUpdate(query);
						conn.commit();
					}
				}
			} catch(Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		}
		
		
		Vector contract_wise_gas_dt = new Vector();
		Vector contract_wise_cust_alloc_gas_dt = new Vector();
		Vector contract_wise_cust_alloc_CustAbbr = new Vector();
		Vector contract_wise_cust_alloc_sn_no = new Vector();
		Vector contract_wise_cust_alloc_mmbtu = new Vector();
		Vector contract_wise_cust_alloc_mmscm = new Vector();
		Vector contract_wise_cust_alloc_start_dt = new Vector();
		Vector contract_wise_cust_alloc_end_dt = new Vector();
		Vector contract_wise_cust_alloc_plant_seq_no = new Vector();
		Vector contract_wise_cust_alloc_plant_nm = new Vector();
		Vector tmp_contract_wise_mmbtu = new Vector();
		Vector tmp_contract_wise_mmscm = new Vector();
		
		Vector Vsn_no = new Vector ();
		Vector Vqty_mmbtu = new Vector ();
		Vector Vqty_scm = new Vector ();
		Vector Vqty_mt = new Vector ();
		Vector Vplant_seq_no = new Vector ();
		Vector Vplant_nm = new Vector ();
		Vector Vcnt = new Vector ();
		String cust_nm="",custName="";
		double totMMBTU=0,totSCM=0,totMT=0;
		Vector Vcont_st_dt = new Vector();
		Vector Vcont_end_dt = new Vector();
		
		Vector alloc_mmbtu = new Vector ();
		Vector alloc_mt = new Vector ();
		double alloc_totMMBTU=0,alloc_totMT=0;

		private void fetchContractWiseCustAllocat_rpt() 
		{
			try {
					double count = 0;
					String query="select to_date('"+to_date+"','dd/mm/yyyy')- to_date('"+from_date+"','dd/mm/yyyy')+1 from dual";
					rset = stmt.executeQuery(query);
					
					if(rset.next()) {
						count = rset.getDouble(1);
					}
					
					for(int i=0;i<count;i++) 
					{
						String query2 = "select to_char(to_date('"+from_date+"','dd/mm/yyyy')+ "+i+",'dd/mm/yyyy') from dual";
						rset = stmt.executeQuery(query2);
						
						if(rset.next()) {
							contract_wise_gas_dt.add(rset.getString(1));
						}
						
					}
					
					String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+cust_id+"'";
					rset1 = stmt1.executeQuery(custAbrSql);
					if(rset1.next()) {
						cust_nm=rset1.getString(1)==null?"":rset1.getString(1);
					}
					
					for (int i = 0 ; i < contract_wise_gas_dt.size() ; i++) {
						int cnt=0;
						
						String allocSQL="SELECT ALLOC_ID,ENTRY_TOT_ENE,CONTRACT_TYPE FROM DLNG_ALLOC_MST WHERE "
								+ " PARTY_CD='"+cust_id+"' "
								+ " AND GAS_DT = TO_DATE('"+contract_wise_gas_dt.elementAt(i)+"','DD/MM/YYYY') ";
//						System.out.println("allocSQL****"+allocSQL);
						rset = stmt.executeQuery(allocSQL);
						while (rset.next()) {
							cnt++;
//							System.out.println("cnt*****"+cnt);
							String alloc_id[] = rset.getString(1).split("-");
							Vsn_no.add(alloc_id[3]);
							Vqty_mmbtu.add(rset.getString(2)==null?"0":rset.getString(2));
//							Vqty_scm.add(rset.getString(4)==null?"0":rset.getString(4));
							double mmbtu_to_mt = Double.parseDouble(rset.getString(2))/convt_mmbtu_to_mt;
							Vqty_mt.add(mmbtu_to_mt);
							Vcont_typ.add(rset.getString(3)==null?"":rset.getString(3));
							String cont_typ = rset.getString(3)==null?"":rset.getString(3);//Hiren_20200430
							
							queryString= "Select PLANT_NAME from FMS7_CUSTOMER_PLANT_DTL A WHERE A.CUSTOMER_CD='"+cust_id+"' AND " +
									"A.SEQ_NO='"+alloc_id[6]+"' AND A.EFF_DT=(SELECT MAX(C.EFF_DT) FROM " +
									"FMS7_CUSTOMER_PLANT_DTL C WHERE C.CUSTOMER_CD='"+cust_id+"' AND A.SEQ_NO = C.SEQ_NO ) ";
//							System.out.println(queryString);
							rset1 = stmt1.executeQuery(queryString);
							if(rset1.next())
							{
								Vplant_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
							}else {
								Vplant_nm.add("");
							}
							totMMBTU+= Double.parseDouble(rset.getString(2)+"");
//							totSCM+= Double.parseDouble(rset.getString(4)+"");
							totMT+= mmbtu_to_mt;
							String contDt="";
							if(cont_typ.equals("L")) {
								contDt="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') FROM DLNG_LOA_MST WHERE "
										+ " CUSTOMER_CD='"+cust_id+"' AND LOA_NO='"+alloc_id[3]+"' AND LOA_REV_NO='"+alloc_id[4]+"'"
										+ " AND TENDER_NO='"+alloc_id[1]+"' ";
							}else {
								contDt="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') FROM DLNG_SN_MST WHERE "
									+ " CUSTOMER_CD='"+cust_id+"' AND SN_NO='"+alloc_id[3]+"' AND SN_REV_NO='"+alloc_id[4]+"'"
									+ " AND FLSA_NO='"+alloc_id[1]+"' AND FLSA_REV_NO='"+alloc_id[2]+"' ";
							}
//							System.out.println("contDt&&&&"+contDt);
							rset3 = stmt3.executeQuery(contDt);
							if (rset3.next()) {
								Vcont_st_dt.add(rset3.getString(1)==null?"":rset3.getString(1));
								Vcont_end_dt.add(rset3.getString(2)==null?"":rset3.getString(2));
							}else {
								Vcont_st_dt.add("");
								Vcont_end_dt.add("");
							}
						}
						Vcnt.add(cnt);
					}
						
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public double getTotMT() {
			return totMT;
		}

		public Vector getVqty_mt() {
			return Vqty_mt;
		}

		public Vector getContract_wise_gas_dt() {
			return contract_wise_gas_dt;
		}

		public Vector bnVstl_gasDt = new Vector();
		public Vector tmp_nom_mmbtu = new Vector();
		public Vector tmp_trucload_mmbtu = new Vector();
		public Vector buyer_nom_vs_truck_loading_gasDt = new Vector();
		public Vector buyer_nom_vs_truck_loading_startDt = new Vector();
		public Vector buyer_nom_vs_truck_loading_endDt = new Vector();
		public Vector buyer_nom_vs_truck_loading_CustAbbr = new Vector();
		public Vector buyer_nom_vs_truck_loading_sn_no = new Vector();
		public Vector buyer_nom_vs_truck_loading_nom_mmscm = new Vector();
		public Vector buyer_nom_vs_truck_loading_nom_mmbtu = new Vector();
		public Vector buyer_nom_vs_truck_loading_trukLoad_mmscm = new Vector();
		public Vector buyer_nom_vs_truck_loading_trukLoad_mmbtu = new Vector();
		public Vector buyer_nom_vs_truck_loading_plant_nm = new Vector();
		
		
		@SuppressWarnings("unchecked")
		private void fetchCustomerNames() {
			methodName = "fetchCustomerNames()";
			try {
				
				String custCdSql="SELECT DISTINCT(PARTY_CD) FROM DLNG_ALLOC_MST ORDER BY PARTY_CD";
//				System.out.println("custCdSql***"+custCdSql);
				rset = stmt.executeQuery(custCdSql);
				while (rset.next()) {
					
					String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"'";
					rset1 = stmt1.executeQuery(custAbrSql);
					if(rset1.next()) {
						allCustid.add(rset.getString(1));
						allCustAbbr.add(rset1.getString(1));
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("unchecked")
		private void fetchNominatedCustomers() {
			methodName = "fetchNominatedCustomers()";
			try {
				
				String custCdSql="SELECT DISTINCT(PARTY_CD) FROM DLNG_DAILY_NOM  ORDER BY PARTY_CD";
//				System.out.println("custCdSql***"+custCdSql);
				rset = stmt.executeQuery(custCdSql);
				while (rset.next()) {
					
					String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"'";
					rset1 = stmt1.executeQuery(custAbrSql);
					if(rset1.next()) {
						allCustid.add(rset.getString(1));
						allCustAbbr.add(rset1.getString(1));
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		private void fetchBuyerNomVsTruckLoading_rpt() {
			try {
				
				double count = 0;
				String query="select to_date('"+to_date+"','dd/mm/yyyy')- to_date('"+from_date+"','dd/mm/yyyy')+1 from dual";
				rset = stmt.executeQuery(query);
				
				if(rset.next()) {
					count = rset.getDouble(1);
				}
				
				for(int i=0;i<count;i++) 
				{
					String query2 = "select to_char(to_date('"+from_date+"','dd/mm/yyyy')+ "+i+",'dd/mm/yyyy') from dual";
					rset = stmt.executeQuery(query2);
					
					if(rset.next()) {
						contract_wise_gas_dt.add(rset.getString(1));
					}
					
				}
				String custAbrSql="SELECT CUSTOMER_ABBR FROM  FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+cust_id+"'";
				rset1 = stmt1.executeQuery(custAbrSql);
				if(rset1.next()) {
					cust_nm=rset1.getString(1)==null?"":rset1.getString(1);
				}
//				System.out.println("contract_wise_gas_dt****"+contract_wise_gas_dt);
				for (int i = 0 ; i < contract_wise_gas_dt.size() ; i++) {
					int cnt=0;
					
					String nomSQL="SELECT DISTINCT(NOM_ID) FROM DLNG_DAILY_NOM WHERE "
							+ " PARTY_CD='"+cust_id+"' AND NOM_DT = TO_DATE('"+contract_wise_gas_dt.elementAt(i)+"','DD/MM/YYYY')";
//					System.out.println("nomSQL***"+nomSQL);
					rset = stmt.executeQuery(nomSQL);
					while(rset.next()) {
//						cnt=0;
						String nomDtlSQL = "SELECT DAY_VOL,TOT_ENE FROM DLNG_DAILY_NOM WHERE "
								+ " PARTY_CD='"+cust_id+"' "
								+ " AND NOM_DT = TO_DATE('"+contract_wise_gas_dt.elementAt(i)+"','DD/MM/YYYY')"
								+ " AND NOM_ID='"+rset.getString(1)+"'"
								+ " AND REV_NO=(SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE "
								+ " PARTY_CD='"+cust_id+"'"
								+ " AND NOM_DT = TO_DATE('"+contract_wise_gas_dt.elementAt(i)+"','DD/MM/YYYY')"
								+ " AND NOM_ID='"+rset.getString(1)+"')";
//						System.out.println("nomDtlSQL****"+nomDtlSQL);
						rset1 = stmt1.executeQuery(nomDtlSQL);
						while (rset1.next()) {
							String nom_id [] = rset.getString(1).toString().split("-");
							cnt++;
							Vsn_no.add(nom_id[3]);
							Vqty_mmbtu.add(rset1.getString(1)==null?"0":rset1.getString(1));
							Vqty_scm.add(rset1.getString(2)==null?"0":rset1.getString(2));
							double mmbtu_to_mt = Double.parseDouble(rset1.getString(1))/convt_mmbtu_to_mt;
							Vqty_mt.add(mmbtu_to_mt);
							
							
							queryString= "Select PLANT_NAME from FMS7_CUSTOMER_PLANT_DTL A WHERE A.CUSTOMER_CD='"+cust_id+"' AND " +
									"A.SEQ_NO='"+nom_id[6]+"' AND A.EFF_DT=(SELECT MAX(C.EFF_DT) FROM " +
									"FMS7_CUSTOMER_PLANT_DTL C WHERE C.CUSTOMER_CD='"+cust_id+"' AND A.SEQ_NO = C.SEQ_NO ) ";
//							System.out.println(queryString);
							rset2 = stmt2.executeQuery(queryString);
							if(rset2.next())
							{
								Vplant_nm.add(rset2.getString(1)==null?"":rset2.getString(1));
							}else {
								Vplant_nm.add("");
							}
							totMMBTU+= Double.parseDouble(rset1.getString(1)+"");
							totSCM+= Double.parseDouble(rset1.getString(2)+"");
							totMT+= mmbtu_to_mt;
							String  contDt="";
							String allocSQL="SELECT NVL(CONTRACT_TYPE,'S') FROM DLNG_ALLOC_MST WHERE "
									+ " PARTY_CD='"+cust_id+"' AND GAS_DT = TO_DATE('"+contract_wise_gas_dt.elementAt(i)+"','DD/MM/YYYY')"
									+ " AND ALLOC_ID = '"+rset.getString(1)+"'";
//							System.out.println("allocSQL***"+allocSQL);
							rset3 = stmt2.executeQuery(allocSQL);
							if(rset3.next()) {
								Vcont_typ.add(rset3.getString(1));
								if(rset3.getString(1).equals("L")) {
									contDt="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') FROM DLNG_LOA_MST WHERE "
											+ " CUSTOMER_CD='"+cust_id+"' AND LOA_NO='"+nom_id[2]+"' AND LOA_REV_NO='"+nom_id[3]+"'"
											+ " AND TENDER_NO='"+nom_id[1]+"' ";
									rset4 = stmt4.executeQuery(contDt);
									if (rset4.next()) {
										Vcont_st_dt.add(rset4.getString(1)==null?"":rset4.getString(1));
										Vcont_end_dt.add(rset4.getString(2)==null?"":rset4.getString(2));
									}else {
										Vcont_st_dt.add("");
										Vcont_end_dt.add("");
									}
								}else {
									contDt="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') FROM DLNG_SN_MST WHERE "
											+ " CUSTOMER_CD='"+cust_id+"' AND SN_NO='"+nom_id[3]+"' AND SN_REV_NO='"+nom_id[4]+"'"
											+ " AND FLSA_NO='"+nom_id[1]+"' AND FLSA_REV_NO='"+nom_id[2]+"' ";
									rset4 = stmt4.executeQuery(contDt);
									if (rset4.next()) {
										Vcont_st_dt.add(rset4.getString(1)==null?"":rset4.getString(1));
										Vcont_end_dt.add(rset4.getString(2)==null?"":rset4.getString(2));
									}else {
										Vcont_st_dt.add("");
										Vcont_end_dt.add("");
									}
								}
							}else {
								Vcont_typ.add("S");
								contDt="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') FROM DLNG_SN_MST WHERE "
										+ " CUSTOMER_CD='"+cust_id+"' AND SN_NO='"+nom_id[3]+"' AND SN_REV_NO='"+nom_id[4]+"'"
										+ " AND FLSA_NO='"+nom_id[1]+"' AND FLSA_REV_NO='"+nom_id[2]+"' ";
								rset4 = stmt4.executeQuery(contDt);
								if (rset4.next()) {
									Vcont_st_dt.add(rset4.getString(1)==null?"":rset4.getString(1));
									Vcont_end_dt.add(rset4.getString(2)==null?"":rset4.getString(2));
								}else {
									Vcont_st_dt.add("");
									Vcont_end_dt.add("");
								}
							}
							/* ************ Fetching allocation data***************** */
							String allcDtlSQL = "SELECT SUM(EXIT_TOT_ENE) FROM DLNG_ALLOC_MST WHERE "
									+ " PARTY_CD='"+cust_id+"' "
									+ " AND GAS_DT = TO_DATE('"+contract_wise_gas_dt.elementAt(i)+"','DD/MM/YYYY')"
									+ " AND ALLOC_ID='"+rset.getString(1)+"'" ;
//							System.out.println("allcDtlSQL****"+allcDtlSQL);
							rset2 = stmt2.executeQuery(allcDtlSQL);
							if (rset2.next()) {
								alloc_mmbtu.add(rset2.getString(1)==null?"0":rset2.getString(1));
								alloc_mt.add(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))/convt_mmbtu_to_mt);
								alloc_totMMBTU+=Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
								alloc_totMT+=Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))/convt_mmbtu_to_mt;
//								Vcont_typ.add(rset2.getString(2)==null?"":rset2.getString(2));
							}else {
								alloc_mmbtu.add("0");
								alloc_mt.add("0");
//								Vcont_typ.add("");
							}
						}
						
					}Vcnt.add(cnt);		
//					
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		//////////////////////SB20200824///////////////////////
		public void fetchContactDtl()
		{
			methodName = "fetchContactDtl()";
			try 
			{
				String contact_addr_flag = "C"; //SB20200824
				String mapp_id2[] = Sch_Mapping_id.split("-");
				
//				System.out.println("In fetchContactDtl() Sch_Mapping_id : "+Sch_Mapping_id);
				queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' ')," +
							  "addr_flag,phone,fax_1  " +
							  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
							  "WHERE A.customer_cd='"+mapp_id2[0]+"' AND A.def_jt_flag='Y' AND " +
						//SB20200824	  "A.seq_no="+customer_contact_cd+" AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
							//SB20200824	  "B.seq_no="+customer_contact_cd+" AND " +
							  "B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
//				System.out.println("Customer Contact Person Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					contact_Person_Name = rset.getString(2).trim();
					contact_Person_Desg = rset.getString(3).trim();
					contact_addr_flag = rset.getString(4)==null?"":rset.getString(4);
					contact_Customer_Person_Phone = rset.getString(5)==null?"":rset.getString(5);
					contact_Customer_Person_Fax = rset.getString(6)==null?"":rset.getString(6);
				}
				
				if(contact_addr_flag.trim().equalsIgnoreCase("R") || contact_addr_flag.trim().equalsIgnoreCase("C") || contact_addr_flag.trim().equalsIgnoreCase("B"))
				{
					queryString = "SELECT addr,city,pin,phone,fax_1,state,country " +
								  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
								  "WHERE A.customer_cd="+mapp_id2[0]+" AND A.address_type='"+contact_addr_flag+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"' AND " +
								  "B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
					//System.out.println("Customer Address Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
						contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
						contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
						contact_Customer_Person_Phone = rset.getString(4)==null?"":rset.getString(4);
						contact_Customer_Person_Fax = rset.getString(5)==null?"":rset.getString(5);
						contact_Customer_Person_State = rset.getString(6)==null?"":rset.getString(6);
						contact_Customer_Person_Country = rset.getString(7)==null?"":rset.getString(7);
					}
				}
				else if(contact_addr_flag.trim().length()>1)
				{
					String plant_no = contact_addr_flag.trim().substring(1);
					
					queryString = "SELECT plant_addr,plant_city,plant_pin,plant_name,plant_state " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd="+mapp_id2[0]+" AND A.seq_no='"+plant_no+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
								  "B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
					//System.out.println("Customer Address Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						String plant_nm = rset.getString(4)==null?"":rset.getString(4);
						if(plant_nm.trim().equals(""))
						{
							contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
						}
						else
						{
							contact_Customer_Person_Address = plant_nm+", "+(rset.getString(1)==null?"":rset.getString(1));
						}
						contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
						contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
						contact_Customer_Person_State = rset.getString(5)==null?"":rset.getString(5);
					}
				}
				
			

				queryString = "SELECT supplier_name " +
							  "FROM FMS7_SUPPLIER_MST A " +
							  "WHERE A.supplier_cd=1 AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
							  "WHERE A.supplier_cd=B.supplier_cd AND " +
							  "B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
//				System.out.println("Supplier Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					contact_Suppl_Name = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT addr,city,pin,phone,fax_1,state,country " +
							  "FROM FMS7_SUPPLIER_ADDRESS_MST A " +
							  "WHERE A.supplier_cd=1 AND A.address_type='R' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
							  "WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND " +
							  "B.eff_dt<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
//				System.out.println("Supplier Address Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					contact_Suppl_Person_Address = rset.getString(1)==null?"":rset.getString(1);
					contact_Suppl_Person_City = rset.getString(2)==null?"":rset.getString(2);
					contact_Suppl_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
					contact_Suppl_Person_Phone = rset.getString(4)==null?"":rset.getString(4);
					contact_Suppl_Person_Fax = rset.getString(5)==null?"":rset.getString(5);
					contact_Suppl_Person_State = rset.getString(6)==null?"":rset.getString(6);
					contact_Suppl_Person_Country = rset.getString(7)==null?"":rset.getString(7);
				}
			}
			catch(Exception e)
			{
				System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
				e.printStackTrace();
			}
		}//End Of Method fetchJointTicketReport() ...

		///////////////SB20200824: Supplier Mast////////////////////////////////		
		public void SupplierDtl()
		{
			try 
			{
				String queryString1 = " SELECT SUPPLIER_ABBR, SUPPLIER_NAME, PLANT_STATE  FROM FMS7_SUPPLIER_MST A, FMS7_SUPPLIER_PLANT_DTL B WHERE A.SUPPLIER_CD=B.SUPPLIER_CD ";
//				System.out.println("Min. Yr "+queryString1);
				rset = stmt.executeQuery(queryString1);
				if(rset.next())
				{
					Supl_abr = rset.getString(1);
					Supl_nm = rset.getString(2);
					Supl_plant_state = rset.getString(3);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public Vector Customer_Contact_Cd = new Vector();
		public Vector Customer_Contact_Nm = new Vector();
		public Vector Customer_Contact_Desg = new Vector();
		public String contact_Person_Name = "";
		public String contact_Person_Desg = "";
		public String contact_Customer_Name = "";
		public String contact_Customer_Person_Address = "";
		public String contact_Customer_Person_City = "";
		public String contact_Customer_Person_Pin = "";
		public String contact_Customer_Person_Phone = "";
		public String contact_Customer_Person_Fax = "";
		public String contact_Customer_Person_State = "";
		public String contact_Customer_Person_Country = "";
		public String contact_Suppl_Name = "";
		public String contact_Suppl_Person_Address = "";
		public String contact_Suppl_Person_City = "";
		public String contact_Suppl_Person_Pin = "";
		public String contact_Suppl_Person_Phone = "";
		public String contact_Suppl_Person_Fax = "";
		public String contact_Suppl_Person_State = "";
		public String contact_Suppl_Person_Country = "";
		public Vector getCustomer_Contact_Cd() {return Customer_Contact_Cd;}
		public Vector getCustomer_Contact_Nm() {return Customer_Contact_Nm;}
		public Vector getCustomer_Contact_Desg() {return Customer_Contact_Desg;}
		public String getContact_Person_Name() {return contact_Person_Name;}
		public String getContact_Person_Desg() {return contact_Person_Desg;}
		public String getContact_Customer_Name() {return contact_Customer_Name;}
		public String getContact_Customer_Person_Address() {return contact_Customer_Person_Address;}
		public String getContact_Customer_Person_City() {return contact_Customer_Person_City;}
		public String getContact_Customer_Person_Pin() {return contact_Customer_Person_Pin;}
		public String getContact_Customer_Person_Phone() {return contact_Customer_Person_Phone;}
		public String getContact_Customer_Person_Fax() {return contact_Customer_Person_Fax;}
		public String getContact_Customer_Person_State() {return contact_Customer_Person_State;}
		public String getContact_Customer_Person_Country() {return contact_Customer_Person_Country;}
		public String getContact_Suppl_Name() {return contact_Suppl_Name;}
		public String getContact_Suppl_Person_Address() {return contact_Suppl_Person_Address;}
		public String getContact_Suppl_Person_City() {return contact_Suppl_Person_City;}
		public String getContact_Suppl_Person_Pin() {return contact_Suppl_Person_Pin;}
		public String getContact_Suppl_Person_Phone() {return contact_Suppl_Person_Phone;}
		public String getContact_Suppl_Person_Fax() {return contact_Suppl_Person_Fax;}
		public String getContact_Suppl_Person_State() {return contact_Suppl_Person_State;}
		public String getContact_Suppl_Person_Country() {return contact_Suppl_Person_Country;}
		
		String Supl_abr="";
		public String getSupl_abr() {return Supl_abr;}
		String Supl_nm="";
		public String getSupl_nm() {return Supl_nm;}
		String Supl_plant_state="";
		public String getSupl_plant_state() {return Supl_plant_state;}
		///////////////////////////////////////////////			

		public double getAlloc_totMT() {
			return alloc_totMT;
		}

		public void setAlloc_totMT(double alloc_totMT) {
			this.alloc_totMT = alloc_totMT;
		}

		public double getAlloc_totMMBTU() {
			return alloc_totMMBTU;
		}

		public Vector getAlloc_mmbtu() {
			return alloc_mmbtu;
		}

		public Vector daily_Buyer_Nom_Fgsa_No = new Vector();
		public Vector daily_Buyer_Nom_Fgsa_Rev_No = new Vector();
		public Vector daily_Buyer_Nom_Sn_No = new Vector();
		public Vector daily_Buyer_Nom_Sn_Rev_No = new Vector();
		public Vector daily_Buyer_Nom_Cd = new Vector();
		public Vector daily_Buyer_Nom_Abbr = new Vector();
		public Vector daily_Buyer_Nom_Dcq = new Vector();
		public Vector daily_Buyer_Nom_Plant_Cd = new Vector();
		public Vector daily_Buyer_Nom_Plant_Abbr = new Vector();
		public Vector daily_Buyer_Nom_Balance_Qty = new Vector(); 
		public Vector Buyer_Contracted_Qty = new Vector(); 
		public Vector Buyer_Allocated_Qty = new Vector(); 
		public Vector Buyer_Nominated_Qty = new Vector(); 
		public Vector daily_Buyer_Nom_Mdcq_Qty = new Vector(); 
		public Vector daily_Buyer_Nom_LC_ADV_Flag = new Vector(); 
		public Vector daily_Buyer_Nom_Current_Balance_Amt = new Vector(); 
		public String daily_Total_Dcq = "";
		public Vector daily_Buyer_Nom_Mapping_Id = new Vector(); 
		public Vector daily_Buyer_Gen_Day_With_Rev_No = new Vector();
		public Vector daily_Buyer_Gen_Day_Time = new Vector();
		public Vector daily_Buyer_Nom_Plant_Seq_No = new Vector();
		public Vector daily_Buyer_Nom_Plant_Seq_Abbr = new Vector();
		public Vector daily_Buyer_Nom_Qty_Mmbtu = new Vector();
		public Vector daily_Buyer_Nom_Qty_Scm = new Vector();
		public String daily_Total_Mmbtu = "";
		public String daily_Total_Scm = "";
		public Vector daily_Buyer_regas_cargo_boe_no=new Vector();
		public Vector daily_Buyer_regas_cargo_boe_dt=new Vector();
		Vector PRE_APPROVAL=new Vector();
		Vector CLOSURE_FLAG=new Vector();
		Vector COMM_PRE_APPROVAL=new Vector();
		public Vector daily_Buyer_Nom_Type = new Vector(); 
		public Vector daily_Buyer_Nom_Contract_Type = new Vector(); 
		public Vector CUST_CD = new Vector();	
		public Vector CUST_NM = new Vector();
		public Vector GAS_DT = new Vector();	
		public String gcv = "";
		public String ncv = "";
		Vector qty_nomination = new Vector();
		Vector ALLOCATED_QTYV = new Vector();
		
		public Vector daily_Seller_Nom_Fgsa_No = new Vector();
		public Vector daily_Seller_Nom_Mapping_Id = new Vector();
		public Vector daily_Seller_Nom_Fgsa_Rev_No = new Vector();
		public Vector daily_Seller_Nom_Sn_No = new Vector();
		public Vector daily_Seller_Nom_Sn_Rev_No = new Vector();
		public Vector daily_Seller_Nom_Cd = new Vector();
		public Vector daily_Seller_Nom_Abbr = new Vector();
		public Vector daily_Seller_Nom_Dcq = new Vector();
		public Vector daily_Seller_Nom_Plant_Cd = new Vector();
		public Vector daily_Seller_Nom_Plant_Abbr = new Vector();
		public String daily_Total_Dcq_Seller_Nom = "";
		public Vector daily_Seller_Gen_Day_With_Rev_No = new Vector();
		public Vector daily_Seller_Gen_Day_Time = new Vector();
		public Vector daily_Seller_Nom_Plant_Seq_No = new Vector();
		public Vector daily_Seller_Nom_Plant_Seq_Abbr = new Vector();
		public Vector daily_Seller_Nom_Qty_Mmbtu = new Vector();
		public Vector daily_Seller_Nom_Qty_Scm = new Vector();
		public String daily_Total_Mmbtu_Seller_Nom = "";
		public String daily_Total_Scm_Seller_Nom = "";
		public Vector daily_Seller_Nom_Type = new Vector(); 
		public Vector daily_Seller_regas_cargo_boe_no = new Vector(); 
		public Vector daily_Seller_regas_cargo_boe_dt = new Vector(); 
		public Vector daily_Seller_Nom_Contract_Type = new Vector(); 
		double fsru_tank_vol = 0, int_tank_vol = 0, int_tankCapacity = 0;
		String int_tankId = "";
		
		//Assumption : 1 M3 of LNG = 23.9 MMBTU
		double conversion_factor_from_m3_to_mmbtu = 23.9;
		
		Vector tank_truck_id = new Vector(); 
		Vector tank_truck_nm = new Vector();
		Vector tank_truck_capacity = new Vector();
		Map no_truck_required = new HashMap();
		int total_truck_avail = 0;
		double single_truck_capacity = 0;
		
		Map <String, String> truck_load_start_day = new HashMap <String, String> ();
		Map <String, String> truck_load_start_tm = new HashMap <String, String> ();
		Map <String, String> truck_load_end_tm = new HashMap <String, String> ();
		Map <String, String> truck_load_end_day = new HashMap <String, String> ();
		Map <String, String> truck_loaded_vol = new HashMap <String, String> ();
		Map <String, String> truck_loaded_ene = new HashMap <String, String> ();
		Map <String, String> truck_unloaded_vol = new HashMap <String, String> ();
		
		Map <String, String> nomDt = new HashMap <String, String> ();
		Map <String, String> nomQty = new HashMap <String, String> ();
		Map <String, String> schDt = new HashMap <String, String> ();
		Map <String, String> schQty = new HashMap <String, String> ();
		
		Map <String, String> loadedDt = new HashMap <String, String> ();
		Map <String, String> loadedQty = new HashMap <String, String> ();
		Map <String, String> loadedQtyMt = new HashMap <String, String> ();
		public void fetchTruckLoadingDetails() throws Exception {}


		public String getCallFlag() {
			return callFlag;
		}

		public void setCallFlag(String callFlag) {
			this.callFlag = callFlag;
		}

		public String getGas_date() {
			return gas_date;
		}

		public void setGas_date(String gas_date) {
			this.gas_date = gas_date;
		}

		public String getGen_date() {
			return gen_date;
		}

		public void setGen_date(String gen_date) {
			this.gen_date = gen_date;
		}

		public String getFrom_dt() {
			return from_dt;
		}

		public void setFrom_dt(String from_dt) {
			this.from_dt = from_dt;
		}

		public String getTo_dt() {
			return to_dt;
		}

		public void setTo_dt(String to_dt) {
			this.to_dt = to_dt;
		}

		public String getCustomer_cd() {
			return customer_cd;
		}

		public void setCustomer_cd(String customer_cd) {
			this.customer_cd = customer_cd;
		}

		public String getCustomer_contact_cd() {
			return customer_contact_cd;
		}

		public void setCustomer_contact_cd(String customer_contact_cd) {
			this.customer_contact_cd = customer_contact_cd;
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		public Vector getDaily_Buyer_Nom_Fgsa_No() {
			return daily_Buyer_Nom_Fgsa_No;
		}

		public void setDaily_Buyer_Nom_Fgsa_No(Vector daily_Buyer_Nom_Fgsa_No) {
			this.daily_Buyer_Nom_Fgsa_No = daily_Buyer_Nom_Fgsa_No;
		}

		public Vector getDaily_Buyer_Nom_Fgsa_Rev_No() {
			return daily_Buyer_Nom_Fgsa_Rev_No;
		}

		public void setDaily_Buyer_Nom_Fgsa_Rev_No(Vector daily_Buyer_Nom_Fgsa_Rev_No) {
			this.daily_Buyer_Nom_Fgsa_Rev_No = daily_Buyer_Nom_Fgsa_Rev_No;
		}

		public Vector getDaily_Buyer_Nom_Sn_No() {
			return daily_Buyer_Nom_Sn_No;
		}

		public void setDaily_Buyer_Nom_Sn_No(Vector daily_Buyer_Nom_Sn_No) {
			this.daily_Buyer_Nom_Sn_No = daily_Buyer_Nom_Sn_No;
		}

		public Vector getDaily_Buyer_Nom_Sn_Rev_No() {
			return daily_Buyer_Nom_Sn_Rev_No;
		}

		public void setDaily_Buyer_Nom_Sn_Rev_No(Vector daily_Buyer_Nom_Sn_Rev_No) {
			this.daily_Buyer_Nom_Sn_Rev_No = daily_Buyer_Nom_Sn_Rev_No;
		}

		public Vector getDaily_Buyer_Nom_Cd() {
			return daily_Buyer_Nom_Cd;
		}

		public void setDaily_Buyer_Nom_Cd(Vector daily_Buyer_Nom_Cd) {
			this.daily_Buyer_Nom_Cd = daily_Buyer_Nom_Cd;
		}

		public Vector getDaily_Buyer_Nom_Abbr() {
			return daily_Buyer_Nom_Abbr;
		}

		public void setDaily_Buyer_Nom_Abbr(Vector daily_Buyer_Nom_Abbr) {
			this.daily_Buyer_Nom_Abbr = daily_Buyer_Nom_Abbr;
		}

		public Vector getDaily_Buyer_Nom_Dcq() {
			return daily_Buyer_Nom_Dcq;
		}

		public void setDaily_Buyer_Nom_Dcq(Vector daily_Buyer_Nom_Dcq) {
			this.daily_Buyer_Nom_Dcq = daily_Buyer_Nom_Dcq;
		}

		public Vector getDaily_Buyer_Nom_Plant_Cd() {
			return daily_Buyer_Nom_Plant_Cd;
		}

		public void setDaily_Buyer_Nom_Plant_Cd(Vector daily_Buyer_Nom_Plant_Cd) {
			this.daily_Buyer_Nom_Plant_Cd = daily_Buyer_Nom_Plant_Cd;
		}

		public Vector getDaily_Buyer_Nom_Plant_Abbr() {
			return daily_Buyer_Nom_Plant_Abbr;
		}

		public void setDaily_Buyer_Nom_Plant_Abbr(Vector daily_Buyer_Nom_Plant_Abbr) {
			this.daily_Buyer_Nom_Plant_Abbr = daily_Buyer_Nom_Plant_Abbr;
		}

		public Vector getDaily_Buyer_Nom_Balance_Qty() {
			return daily_Buyer_Nom_Balance_Qty;
		}

		public void setDaily_Buyer_Nom_Balance_Qty(Vector daily_Buyer_Nom_Balance_Qty) {
			this.daily_Buyer_Nom_Balance_Qty = daily_Buyer_Nom_Balance_Qty;
		}

		public Vector getBuyer_Contracted_Qty() {
			return Buyer_Contracted_Qty;
		}

		public void setBuyer_Contracted_Qty(Vector buyer_Contracted_Qty) {
			Buyer_Contracted_Qty = buyer_Contracted_Qty;
		}

		public Vector getBuyer_Allocated_Qty() {
			return Buyer_Allocated_Qty;
		}

		public void setBuyer_Allocated_Qty(Vector buyer_Allocated_Qty) {
			Buyer_Allocated_Qty = buyer_Allocated_Qty;
		}

		public Vector getBuyer_Nominated_Qty() {
			return Buyer_Nominated_Qty;
		}

		public void setBuyer_Nominated_Qty(Vector buyer_Nominated_Qty) {
			Buyer_Nominated_Qty = buyer_Nominated_Qty;
		}

		public Vector getDaily_Buyer_Nom_Mdcq_Qty() {
			return daily_Buyer_Nom_Mdcq_Qty;
		}

		public void setDaily_Buyer_Nom_Mdcq_Qty(Vector daily_Buyer_Nom_Mdcq_Qty) {
			this.daily_Buyer_Nom_Mdcq_Qty = daily_Buyer_Nom_Mdcq_Qty;
		}

		public Vector getDaily_Buyer_Nom_LC_ADV_Flag() {
			return daily_Buyer_Nom_LC_ADV_Flag;
		}

		public void setDaily_Buyer_Nom_LC_ADV_Flag(Vector daily_Buyer_Nom_LC_ADV_Flag) {
			this.daily_Buyer_Nom_LC_ADV_Flag = daily_Buyer_Nom_LC_ADV_Flag;
		}

		public Vector getDaily_Buyer_Nom_Current_Balance_Amt() {
			return daily_Buyer_Nom_Current_Balance_Amt;
		}

		public void setDaily_Buyer_Nom_Current_Balance_Amt(Vector daily_Buyer_Nom_Current_Balance_Amt) {
			this.daily_Buyer_Nom_Current_Balance_Amt = daily_Buyer_Nom_Current_Balance_Amt;
		}

		public String getDaily_Total_Dcq() {
			return daily_Total_Dcq;
		}

		public void setDaily_Total_Dcq(String daily_Total_Dcq) {
			this.daily_Total_Dcq = daily_Total_Dcq;
		}

		public Vector getDaily_Buyer_Nom_Mapping_Id() {
			return daily_Buyer_Nom_Mapping_Id;
		}

		public void setDaily_Buyer_Nom_Mapping_Id(Vector daily_Buyer_Nom_Mapping_Id) {
			this.daily_Buyer_Nom_Mapping_Id = daily_Buyer_Nom_Mapping_Id;
		}

		public Vector getDaily_Buyer_Gen_Day_With_Rev_No() {
			return daily_Buyer_Gen_Day_With_Rev_No;
		}

		public void setDaily_Buyer_Gen_Day_With_Rev_No(Vector daily_Buyer_Gen_Day_With_Rev_No) {
			this.daily_Buyer_Gen_Day_With_Rev_No = daily_Buyer_Gen_Day_With_Rev_No;
		}

		public Vector getDaily_Buyer_Gen_Day_Time() {
			return daily_Buyer_Gen_Day_Time;
		}

		public void setDaily_Buyer_Gen_Day_Time(Vector daily_Buyer_Gen_Day_Time) {
			this.daily_Buyer_Gen_Day_Time = daily_Buyer_Gen_Day_Time;
		}

		public Vector getDaily_Buyer_Nom_Plant_Seq_No() {
			return daily_Buyer_Nom_Plant_Seq_No;
		}

		public void setDaily_Buyer_Nom_Plant_Seq_No(Vector daily_Buyer_Nom_Plant_Seq_No) {
			this.daily_Buyer_Nom_Plant_Seq_No = daily_Buyer_Nom_Plant_Seq_No;
		}

		public Vector getDaily_Buyer_Nom_Plant_Seq_Abbr() {
			return daily_Buyer_Nom_Plant_Seq_Abbr;
		}

		public void setDaily_Buyer_Nom_Plant_Seq_Abbr(Vector daily_Buyer_Nom_Plant_Seq_Abbr) {
			this.daily_Buyer_Nom_Plant_Seq_Abbr = daily_Buyer_Nom_Plant_Seq_Abbr;
		}

		public Vector getDaily_Buyer_Nom_Qty_Mmbtu() {
			return daily_Buyer_Nom_Qty_Mmbtu;
		}

		public void setDaily_Buyer_Nom_Qty_Mmbtu(Vector daily_Buyer_Nom_Qty_Mmbtu) {
			this.daily_Buyer_Nom_Qty_Mmbtu = daily_Buyer_Nom_Qty_Mmbtu;
		}

		public Vector getDaily_Buyer_Nom_Qty_Scm() {
			return daily_Buyer_Nom_Qty_Scm;
		}

		public void setDaily_Buyer_Nom_Qty_Scm(Vector daily_Buyer_Nom_Qty_Scm) {
			this.daily_Buyer_Nom_Qty_Scm = daily_Buyer_Nom_Qty_Scm;
		}

		public String getDaily_Total_Mmbtu() {
			return daily_Total_Mmbtu;
		}

		public void setDaily_Total_Mmbtu(String daily_Total_Mmbtu) {
			this.daily_Total_Mmbtu = daily_Total_Mmbtu;
		}

		public String getDaily_Total_Scm() {
			return daily_Total_Scm;
		}

		public void setDaily_Total_Scm(String daily_Total_Scm) {
			this.daily_Total_Scm = daily_Total_Scm;
		}

		public Vector getDaily_Buyer_regas_cargo_boe_no() {
			return daily_Buyer_regas_cargo_boe_no;
		}

		public void setDaily_Buyer_regas_cargo_boe_no(Vector daily_Buyer_regas_cargo_boe_no) {
			this.daily_Buyer_regas_cargo_boe_no = daily_Buyer_regas_cargo_boe_no;
		}

		public Vector getDaily_Buyer_regas_cargo_boe_dt() {
			return daily_Buyer_regas_cargo_boe_dt;
		}

		public void setDaily_Buyer_regas_cargo_boe_dt(Vector daily_Buyer_regas_cargo_boe_dt) {
			this.daily_Buyer_regas_cargo_boe_dt = daily_Buyer_regas_cargo_boe_dt;
		}

		public Vector getPRE_APPROVAL() {
			return PRE_APPROVAL;
		}

		public void setPRE_APPROVAL(Vector pRE_APPROVAL) {
			PRE_APPROVAL = pRE_APPROVAL;
		}

		public Vector getCLOSURE_FLAG() {
			return CLOSURE_FLAG;
		}

		public void setCLOSURE_FLAG(Vector cLOSURE_FLAG) {
			CLOSURE_FLAG = cLOSURE_FLAG;
		}

		public Vector getCOMM_PRE_APPROVAL() {
			return COMM_PRE_APPROVAL;
		}

		public void setCOMM_PRE_APPROVAL(Vector cOMM_PRE_APPROVAL) {
			COMM_PRE_APPROVAL = cOMM_PRE_APPROVAL;
		}

		public Vector getDaily_Buyer_Nom_Type() {
			return daily_Buyer_Nom_Type;
		}

		public void setDaily_Buyer_Nom_Type(Vector daily_Buyer_Nom_Type) {
			this.daily_Buyer_Nom_Type = daily_Buyer_Nom_Type;
		}

		public Vector getDaily_Buyer_Nom_Contract_Type() {
			return daily_Buyer_Nom_Contract_Type;
		}

		public void setDaily_Buyer_Nom_Contract_Type(Vector daily_Buyer_Nom_Contract_Type) {
			this.daily_Buyer_Nom_Contract_Type = daily_Buyer_Nom_Contract_Type;
		}

		public Vector getCUST_CD() {
			return CUST_CD;
		}

		public void setCUST_CD(Vector cUST_CD) {
			CUST_CD = cUST_CD;
		}

		public Vector getCUST_NM() {
			return CUST_NM;
		}

		public void setCUST_NM(Vector cUST_NM) {
			CUST_NM = cUST_NM;
		}

		public Vector getGAS_DT() {
			return GAS_DT;
		}

		public void setGAS_DT(Vector gAS_DT) {
			GAS_DT = gAS_DT;
		}

		public String getGcv() {
			return gcv;
		}

		public void setGcv(String gcv) {
			this.gcv = gcv;
		}

		public String getNcv() {
			return ncv;
		}

		public void setNcv(String ncv) {
			this.ncv = ncv;
		}

		public Vector getQty_nomination() {
			return qty_nomination;
		}

		public void setQty_nomination(Vector qty_nomination) {
			this.qty_nomination = qty_nomination;
		}

		public Vector getALLOCATED_QTYV() {
			return ALLOCATED_QTYV;
		}

		public void setALLOCATED_QTYV(Vector aLLOCATED_QTYV) {
			ALLOCATED_QTYV = aLLOCATED_QTYV;
		}

		public Vector getDaily_Seller_Nom_Fgsa_No() {
			return daily_Seller_Nom_Fgsa_No;
		}

		public void setDaily_Seller_Nom_Fgsa_No(Vector daily_Seller_Nom_Fgsa_No) {
			this.daily_Seller_Nom_Fgsa_No = daily_Seller_Nom_Fgsa_No;
		}

		public Vector getDaily_Seller_Nom_Mapping_Id() {
			return daily_Seller_Nom_Mapping_Id;
		}

		public void setDaily_Seller_Nom_Mapping_Id(Vector daily_Seller_Nom_Mapping_Id) {
			this.daily_Seller_Nom_Mapping_Id = daily_Seller_Nom_Mapping_Id;
		}

		public Vector getDaily_Seller_Nom_Fgsa_Rev_No() {
			return daily_Seller_Nom_Fgsa_Rev_No;
		}

		public void setDaily_Seller_Nom_Fgsa_Rev_No(Vector daily_Seller_Nom_Fgsa_Rev_No) {
			this.daily_Seller_Nom_Fgsa_Rev_No = daily_Seller_Nom_Fgsa_Rev_No;
		}

		public Vector getDaily_Seller_Nom_Sn_No() {
			return daily_Seller_Nom_Sn_No;
		}

		public void setDaily_Seller_Nom_Sn_No(Vector daily_Seller_Nom_Sn_No) {
			this.daily_Seller_Nom_Sn_No = daily_Seller_Nom_Sn_No;
		}

		public Vector getDaily_Seller_Nom_Sn_Rev_No() {
			return daily_Seller_Nom_Sn_Rev_No;
		}

		public void setDaily_Seller_Nom_Sn_Rev_No(Vector daily_Seller_Nom_Sn_Rev_No) {
			this.daily_Seller_Nom_Sn_Rev_No = daily_Seller_Nom_Sn_Rev_No;
		}

		public Vector getDaily_Seller_Nom_Cd() {
			return daily_Seller_Nom_Cd;
		}

		public void setDaily_Seller_Nom_Cd(Vector daily_Seller_Nom_Cd) {
			this.daily_Seller_Nom_Cd = daily_Seller_Nom_Cd;
		}

		public Vector getDaily_Seller_Nom_Abbr() {
			return daily_Seller_Nom_Abbr;
		}

		public void setDaily_Seller_Nom_Abbr(Vector daily_Seller_Nom_Abbr) {
			this.daily_Seller_Nom_Abbr = daily_Seller_Nom_Abbr;
		}

		public Vector getDaily_Seller_Nom_Dcq() {
			return daily_Seller_Nom_Dcq;
		}

		public void setDaily_Seller_Nom_Dcq(Vector daily_Seller_Nom_Dcq) {
			this.daily_Seller_Nom_Dcq = daily_Seller_Nom_Dcq;
		}

		public Vector getDaily_Seller_Nom_Plant_Cd() {
			return daily_Seller_Nom_Plant_Cd;
		}

		public void setDaily_Seller_Nom_Plant_Cd(Vector daily_Seller_Nom_Plant_Cd) {
			this.daily_Seller_Nom_Plant_Cd = daily_Seller_Nom_Plant_Cd;
		}

		public Vector getDaily_Seller_Nom_Plant_Abbr() {
			return daily_Seller_Nom_Plant_Abbr;
		}

		public void setDaily_Seller_Nom_Plant_Abbr(Vector daily_Seller_Nom_Plant_Abbr) {
			this.daily_Seller_Nom_Plant_Abbr = daily_Seller_Nom_Plant_Abbr;
		}

		public String getDaily_Total_Dcq_Seller_Nom() {
			return daily_Total_Dcq_Seller_Nom;
		}

		public void setDaily_Total_Dcq_Seller_Nom(String daily_Total_Dcq_Seller_Nom) {
			this.daily_Total_Dcq_Seller_Nom = daily_Total_Dcq_Seller_Nom;
		}

		public Vector getDaily_Seller_Gen_Day_With_Rev_No() {
			return daily_Seller_Gen_Day_With_Rev_No;
		}

		public void setDaily_Seller_Gen_Day_With_Rev_No(Vector daily_Seller_Gen_Day_With_Rev_No) {
			this.daily_Seller_Gen_Day_With_Rev_No = daily_Seller_Gen_Day_With_Rev_No;
		}

		public Vector getDaily_Seller_Gen_Day_Time() {
			return daily_Seller_Gen_Day_Time;
		}

		public void setDaily_Seller_Gen_Day_Time(Vector daily_Seller_Gen_Day_Time) {
			this.daily_Seller_Gen_Day_Time = daily_Seller_Gen_Day_Time;
		}

		public Vector getDaily_Seller_Nom_Plant_Seq_No() {
			return daily_Seller_Nom_Plant_Seq_No;
		}

		public void setDaily_Seller_Nom_Plant_Seq_No(Vector daily_Seller_Nom_Plant_Seq_No) {
			this.daily_Seller_Nom_Plant_Seq_No = daily_Seller_Nom_Plant_Seq_No;
		}

		public Vector getDaily_Seller_Nom_Plant_Seq_Abbr() {
			return daily_Seller_Nom_Plant_Seq_Abbr;
		}

		public void setDaily_Seller_Nom_Plant_Seq_Abbr(Vector daily_Seller_Nom_Plant_Seq_Abbr) {
			this.daily_Seller_Nom_Plant_Seq_Abbr = daily_Seller_Nom_Plant_Seq_Abbr;
		}

		public Vector getDaily_Seller_Nom_Qty_Mmbtu() {
			return daily_Seller_Nom_Qty_Mmbtu;
		}

		public void setDaily_Seller_Nom_Qty_Mmbtu(Vector daily_Seller_Nom_Qty_Mmbtu) {
			this.daily_Seller_Nom_Qty_Mmbtu = daily_Seller_Nom_Qty_Mmbtu;
		}

		public Vector getDaily_Seller_Nom_Qty_Scm() {
			return daily_Seller_Nom_Qty_Scm;
		}

		public void setDaily_Seller_Nom_Qty_Scm(Vector daily_Seller_Nom_Qty_Scm) {
			this.daily_Seller_Nom_Qty_Scm = daily_Seller_Nom_Qty_Scm;
		}

		public String getDaily_Total_Mmbtu_Seller_Nom() {
			return daily_Total_Mmbtu_Seller_Nom;
		}

		public void setDaily_Total_Mmbtu_Seller_Nom(String daily_Total_Mmbtu_Seller_Nom) {
			this.daily_Total_Mmbtu_Seller_Nom = daily_Total_Mmbtu_Seller_Nom;
		}

		public String getDaily_Total_Scm_Seller_Nom() {
			return daily_Total_Scm_Seller_Nom;
		}

		public void setDaily_Total_Scm_Seller_Nom(String daily_Total_Scm_Seller_Nom) {
			this.daily_Total_Scm_Seller_Nom = daily_Total_Scm_Seller_Nom;
		}

		public Vector getDaily_Seller_Nom_Type() {
			return daily_Seller_Nom_Type;
		}

		public void setDaily_Seller_Nom_Type(Vector daily_Seller_Nom_Type) {
			this.daily_Seller_Nom_Type = daily_Seller_Nom_Type;
		}

		public Vector getDaily_Seller_regas_cargo_boe_no() {
			return daily_Seller_regas_cargo_boe_no;
		}

		public void setDaily_Seller_regas_cargo_boe_no(Vector daily_Seller_regas_cargo_boe_no) {
			this.daily_Seller_regas_cargo_boe_no = daily_Seller_regas_cargo_boe_no;
		}

		public Vector getDaily_Seller_regas_cargo_boe_dt() {
			return daily_Seller_regas_cargo_boe_dt;
		}

		public void setDaily_Seller_regas_cargo_boe_dt(Vector daily_Seller_regas_cargo_boe_dt) {
			this.daily_Seller_regas_cargo_boe_dt = daily_Seller_regas_cargo_boe_dt;
		}

		public Vector getDaily_Seller_Nom_Contract_Type() {
			return daily_Seller_Nom_Contract_Type;
		}

		public void setDaily_Seller_Nom_Contract_Type(Vector daily_Seller_Nom_Contract_Type) {
			this.daily_Seller_Nom_Contract_Type = daily_Seller_Nom_Contract_Type;
		}

		public double getFsru_tank_vol() {
			return fsru_tank_vol;
		}

		public void setFsru_tank_vol(double fsru_tank_vol) {
			this.fsru_tank_vol = fsru_tank_vol;
		}

		public double getInt_tank_vol() {
			return int_tank_vol;
		}

		public void setInt_tank_vol(double int_tank_vol) {
			this.int_tank_vol = int_tank_vol;
		}

		public String getInt_tankId() {
			return int_tankId;
		}

		public void setInt_tankId(String int_tankId) {
			this.int_tankId = int_tankId;
		}

		public double getInt_tankCapacity() {
			return int_tankCapacity;
		}

		public void setInt_tankCapacity(double int_tankCapacity) {
			this.int_tankCapacity = int_tankCapacity;
		}

		public Vector getTank_truck_id() {
			return tank_truck_id;
		}

		public void setTank_truck_id(Vector tank_truck_id) {
			this.tank_truck_id = tank_truck_id;
		}

		public Vector getTank_truck_nm() {
			return tank_truck_nm;
		}

		public void setTank_truck_nm(Vector tank_truck_nm) {
			this.tank_truck_nm = tank_truck_nm;
		}

		public Vector getTank_truck_capacity() {
			return tank_truck_capacity;
		}

		public void setTank_truck_capacity(Vector tank_truck_capacity) {
			this.tank_truck_capacity = tank_truck_capacity;
		}

		public Map getNo_truck_required() {
			return no_truck_required;
		}

		public void setNo_truck_required(Map no_truck_required) {
			this.no_truck_required = no_truck_required;
		}

		public int getTotal_truck_avail() {
			return total_truck_avail;
		}

		public void setTotal_truck_avail(int total_truck_avail) {
			this.total_truck_avail = total_truck_avail;
		}

		public double getSingle_truck_capacity() {
			return single_truck_capacity;
		}

		public void setSingle_truck_capacity(double single_truck_capacity) {
			this.single_truck_capacity = single_truck_capacity;
		}

		public Map getTruck_load_start_day() {
			return truck_load_start_day;
		}

		public void setTruck_load_start_day(Map <String, String> truck_load_start_day) {
			this.truck_load_start_day = truck_load_start_day;
		}

		public Map getTruck_load_start_tm() {
			return truck_load_start_tm;
		}

		public void setTruck_load_start_tm(Map <String, String> truck_load_start_tm) {
			this.truck_load_start_tm = truck_load_start_tm;
		}

		public Map getTruck_load_end_tm() {
			return truck_load_end_tm;
		}

		public void setTruck_load_end_tm(Map <String, String> truck_load_end_tm) {
			this.truck_load_end_tm = truck_load_end_tm;
		}

		public Map getTruck_load_end_day() {
			return truck_load_end_day;
		}

		public void setTruck_load_end_day(Map <String, String> truck_load_end_day) {
			this.truck_load_end_day = truck_load_end_day;
		}

		public Map getTruck_loaded_vol() {
			return truck_loaded_vol;
		}

		public void setTruck_loaded_vol(Map <String, String> truck_loaded_vol) {
			this.truck_loaded_vol = truck_loaded_vol;
		}



		public Map getRpt_map_id() {
			return rpt_map_id;
		}

		public void setRpt_map_id(Map rpt_map_id) {
			this.rpt_map_id = rpt_map_id;
		}

		public Map getRpt_party_cd() {
			return rpt_party_cd;
		}

		public void setRpt_party_cd(Map rpt_party_cd) {
			this.rpt_party_cd = rpt_party_cd;
		}

		public Map getRpt_day_vol() {
			return rpt_day_vol;
		}

		public void setRpt_day_vol(Map rpt_day_vol) {
			this.rpt_day_vol = rpt_day_vol;
		}

		public Map getRpt_day_unit() {
			return rpt_day_unit;
		}

		public void setRpt_day_unit(Map rpt_day_unit) {
			this.rpt_day_unit = rpt_day_unit;
		}

		public Map getRpt_party_nm() {
			return rpt_party_nm;
		}

		public void setRpt_party_nm(Map rpt_party_nm) {
			this.rpt_party_nm = rpt_party_nm;
		}

		public Map getRpt_allocation_qty() {
			return rpt_allocation_qty;
		}

		public void setRpt_allocation_qty(Map rpt_allocation_qty) {
			this.rpt_allocation_qty = rpt_allocation_qty;
		}

		public Map getRpt_cust_nm() {
			return rpt_cust_nm;
		}

		public void setRpt_cust_nm(Map rpt_cust_nm) {
			this.rpt_cust_nm = rpt_cust_nm;
		}

		public Vector getAll_dates() {
			return all_dates;
		}

		public void setAll_dates(Vector all_dates) {
			this.all_dates = all_dates;
		}

		public String getRpt_dt() {
			return rpt_dt;
		}

		public void setRpt_dt(String rpt_dt) {
			this.rpt_dt = rpt_dt;
		}

		public Map getRpt_fsru_vol() {
			return rpt_fsru_vol;
		}

		public void setRpt_fsru_vol(Map rpt_fsru_vol) {
			this.rpt_fsru_vol = rpt_fsru_vol;
		}

		public Map getRpt_int_vol() {
			return rpt_int_vol;
		}

		public void setRpt_int_vol(Map rpt_int_vol) {
			this.rpt_int_vol = rpt_int_vol;
		}

		public Map<String, String> getTruck_unloaded_vol() {
			return truck_unloaded_vol;
		}

		public void setTruck_unloaded_vol(Map<String, String> truck_unloaded_vol) {
			this.truck_unloaded_vol = truck_unloaded_vol;
		}

		public String getFrom_date() {
			return from_date;
		}

		public void setFrom_date(String from_date) {
			this.from_date = from_date;
		}

		public String getTo_date() {
			return to_date;
		}

		public void setTo_date(String to_date) {
			this.to_date = to_date;
		}

		public Vector getTank_truck_capacityM3() {
			return tank_truck_capacityM3;
		}

		public Map<String, String> getNomDt() {
			return nomDt;
		}

		public Map<String, String> getNomQty() {
			return nomQty;
		}

		public Map<String, String> getSchDt() {
			return schDt;
		}

		public Map<String, String> getSchQty() {
			return schQty;
		}


		public Vector getVcust_cd() {
			return Vcust_cd;
		}


		public Vector getVcust_nm() {
			return Vcust_nm;
		}


		public Vector getVcont_typ() {
			return Vcont_typ;
		}


		public String getCust_cd() {
			return cust_cd;
		}


		public void setCust_cd(String cust_cd) {
			this.cust_cd = cust_cd;
		}


		public String getCont_typ() {
			return cont_typ;
		}


		public void setCont_typ(String cont_typ) {
			this.cont_typ = cont_typ;
		}


		public Vector getVnew_inv_seq_no() {
			return Vnew_inv_seq_no;
		}


		public Vector getVinv_dt() {
			return Vinv_dt;
		}


		public Vector getVtot_qty() {
			return Vtot_qty;
		}


		public Vector getVgross_amt() {
			return Vgross_amt;
		}


		public Vector getVtax_amt() {
			return Vtax_amt;
		}


		public Vector getVnet_payble() {
			return Vnet_payble;
		}


		public Vector getVdue_dt() {
			return Vdue_dt;
		}
//-------SUJIT21MARCH2020------------------

		public Vector getContract_wise_cust_alloc_gas_dt() {
			return contract_wise_cust_alloc_gas_dt;
		}

		public void setContract_wise_cust_alloc_gas_dt(Vector contract_wise_cust_alloc_gas_dt) {
			this.contract_wise_cust_alloc_gas_dt = contract_wise_cust_alloc_gas_dt;
		}

		public Vector getContract_wise_cust_alloc_CustAbbr() {
			return contract_wise_cust_alloc_CustAbbr;
		}

		public void setContract_wise_cust_alloc_CustAbbr(Vector contract_wise_cust_alloc_CustAbbr) {
			this.contract_wise_cust_alloc_CustAbbr = contract_wise_cust_alloc_CustAbbr;
		}

		public Vector getContract_wise_cust_alloc_sn_no() {
			return contract_wise_cust_alloc_sn_no;
		}

		public void setContract_wise_cust_alloc_sn_no(Vector contract_wise_cust_alloc_sn_no) {
			this.contract_wise_cust_alloc_sn_no = contract_wise_cust_alloc_sn_no;
		}

		public Vector getContract_wise_cust_alloc_mmbtu() {
			return contract_wise_cust_alloc_mmbtu;
		}

		public void setContract_wise_cust_alloc_mmbtu(Vector contract_wise_cust_alloc_mmbtu) {
			this.contract_wise_cust_alloc_mmbtu = contract_wise_cust_alloc_mmbtu;
		}

		public Vector getContract_wise_cust_alloc_mmscm() {
			return contract_wise_cust_alloc_mmscm;
		}

		public void setContract_wise_cust_alloc_mmscm(Vector contract_wise_cust_alloc_mmscm) {
			this.contract_wise_cust_alloc_mmscm = contract_wise_cust_alloc_mmscm;
		}

		public Vector getContract_wise_cust_alloc_start_dt() {
			return contract_wise_cust_alloc_start_dt;
		}

		public void setContract_wise_cust_alloc_start_dt(Vector contract_wise_cust_alloc_start_dt) {
			this.contract_wise_cust_alloc_start_dt = contract_wise_cust_alloc_start_dt;
		}

		public Vector getContract_wise_cust_alloc_end_dt() {
			return contract_wise_cust_alloc_end_dt;
		}
		
		public void setContract_wise_cust_alloc_end_dt(Vector contract_wise_cust_alloc_end_dt) {
			this.contract_wise_cust_alloc_end_dt = contract_wise_cust_alloc_end_dt;
		}

		public Vector getContract_wise_cust_alloc_plant_seq_no() {
			return contract_wise_cust_alloc_plant_seq_no;
		}
		
		public void setContract_wise_cust_alloc_plant_seq_no(Vector contract_wise_cust_alloc_plant_seq_no) {
			this.contract_wise_cust_alloc_plant_seq_no = contract_wise_cust_alloc_plant_seq_no;
		}

		public Vector getContract_wise_cust_alloc_plant_nm() {
			return contract_wise_cust_alloc_plant_nm;
		}

		public void setContract_wise_cust_alloc_plant_nm(Vector contract_wise_cust_alloc_plant_nm) {
			this.contract_wise_cust_alloc_plant_nm = contract_wise_cust_alloc_plant_nm;
		}
		
		public Vector getBuyer_nom_vs_truck_loading_gasDt() {
			return buyer_nom_vs_truck_loading_gasDt;
		}


		public void setBuyer_nom_vs_truck_loading_gasDt(Vector buyer_nom_vs_truck_loading_gasDt) {
			this.buyer_nom_vs_truck_loading_gasDt = buyer_nom_vs_truck_loading_gasDt;
		}


		public Vector getBuyer_nom_vs_truck_loading_startDt() {
			return buyer_nom_vs_truck_loading_startDt;
		}


		public void setBuyer_nom_vs_truck_loading_startDt(Vector buyer_nom_vs_truck_loading_startDt) {
			this.buyer_nom_vs_truck_loading_startDt = buyer_nom_vs_truck_loading_startDt;
		}


		public Vector getBuyer_nom_vs_truck_loading_endDt() {
			return buyer_nom_vs_truck_loading_endDt;
		}

		public void setBuyer_nom_vs_truck_loading_endDt(Vector buyer_nom_vs_truck_loading_endDt) {
			this.buyer_nom_vs_truck_loading_endDt = buyer_nom_vs_truck_loading_endDt;
		}

		public Vector getBuyer_nom_vs_truck_loading_CustAbbr() {
			return buyer_nom_vs_truck_loading_CustAbbr;
		}

		public void setBuyer_nom_vs_truck_loading_CustAbbr(Vector buyer_nom_vs_truck_loading_CustAbbr) {
			this.buyer_nom_vs_truck_loading_CustAbbr = buyer_nom_vs_truck_loading_CustAbbr;
		}

		public Vector getBuyer_nom_vs_truck_loading_sn_no() {
			return buyer_nom_vs_truck_loading_sn_no;
		}

		public void setBuyer_nom_vs_truck_loading_sn_no(Vector buyer_nom_vs_truck_loading_sn_no) {
			this.buyer_nom_vs_truck_loading_sn_no = buyer_nom_vs_truck_loading_sn_no;
		}

		public Vector getBuyer_nom_vs_truck_loading_nom_mmscm() {
			return buyer_nom_vs_truck_loading_nom_mmscm;
		}

		public void setBuyer_nom_vs_truck_loading_nom_mmscm(Vector buyer_nom_vs_truck_loading_nom_mmscm) {
			this.buyer_nom_vs_truck_loading_nom_mmscm = buyer_nom_vs_truck_loading_nom_mmscm;
		}

		public Vector getBuyer_nom_vs_truck_loading_nom_mmbtu() {
			return buyer_nom_vs_truck_loading_nom_mmbtu;
		}

		public void setBuyer_nom_vs_truck_loading_nom_mmbtu(Vector buyer_nom_vs_truck_loading_nom_mmbtu) {
			this.buyer_nom_vs_truck_loading_nom_mmbtu = buyer_nom_vs_truck_loading_nom_mmbtu;
		}

		public Vector getBuyer_nom_vs_truck_loading_trukLoad_mmscm() {
			return buyer_nom_vs_truck_loading_trukLoad_mmscm;
		}

		public void setBuyer_nom_vs_truck_loading_trukLoad_mmscm(Vector buyer_nom_vs_truck_loading_trukLoad_mmscm) {
			this.buyer_nom_vs_truck_loading_trukLoad_mmscm = buyer_nom_vs_truck_loading_trukLoad_mmscm;
		}

		public Vector getBuyer_nom_vs_truck_loading_trukLoad_mmbtu() {
			return buyer_nom_vs_truck_loading_trukLoad_mmbtu;
		}

		public void setBuyer_nom_vs_truck_loading_trukLoad_mmbtu(Vector buyer_nom_vs_truck_loading_trukLoad_mmbtu) {
			this.buyer_nom_vs_truck_loading_trukLoad_mmbtu = buyer_nom_vs_truck_loading_trukLoad_mmbtu;
		}

		public Vector getAllCustAbbr() {
			return allCustAbbr;
		}
		
		public void setAllCustAbbr(Vector allCustAbbr) {
			this.allCustAbbr = allCustAbbr;
		}

		public Vector getAllCustid() {
			return allCustid;
		}

		public void setAllCustid(Vector allCustid) {
			this.allCustid = allCustid;
		}

		public String getCust_id() {
			return cust_id;
		}

		public void setCust_id(String cust_id) {
			this.cust_id = cust_id;
		}

		public Vector getBuyer_nom_vs_truck_loading_plant_nm() {
			return buyer_nom_vs_truck_loading_plant_nm;
		}

		public void setBuyer_nom_vs_truck_loading_plant_nm(Vector buyer_nom_vs_truck_loading_plant_nm) {
			this.buyer_nom_vs_truck_loading_plant_nm = buyer_nom_vs_truck_loading_plant_nm;
		}	
		public Vector getVcnt() {
			return Vcnt;
		}

		public Vector getVplant_nm() {
			return Vplant_nm;
		}

		public Vector getVsn_no() {
			return Vsn_no;
		}

		public Vector getVqty_mmbtu() {
			return Vqty_mmbtu;
		}

		public Vector getVqty_scm() {
			return Vqty_scm;
		}

		public String getCust_nm() {
			return cust_nm;
		}

		public double getTotMMBTU() {
			return totMMBTU;
		}

		public double getTotSCM() {
			return totSCM;
		}

		public Vector getVcont_st_dt() {
			return Vcont_st_dt;
		}

		public Vector getVcont_end_dt() {
			return Vcont_end_dt;
		}
		public Vector getAlloc_mt() {
			return alloc_mt;
		}
		public String getCustName() {
			return custName;
		}
		
		Vector HTruck_Gas_Dt = new Vector();
		public Vector getHTruck_Gas_Dt() {return HTruck_Gas_Dt;}
		Vector HMapp_Id = new Vector();
		public Vector getHMapp_Id() {return HMapp_Id;}
		Vector HSch_Id = new Vector();
		public Vector getHSch_Id() {return HSch_Id;}
		Vector HTruck_Trans_Nm = new Vector();
		public Vector getHTruck_Trans_Nm() {return HTruck_Trans_Nm;}
		Vector HCust_Nm = new Vector();
		public Vector getHCust_Nm() {return HCust_Nm;}
		Vector HCust_Plant_Nm = new Vector();
		public Vector getHCust_Plant_Nm() {return HCust_Plant_Nm;}
		Vector HTruck_Nm = new Vector();
		public Vector getHTruck_Nm() {return HTruck_Nm;}
		Vector HTruck_Sch_Dt = new Vector();
		public Vector getHTruck_Sch_Dt() {return HTruck_Sch_Dt;}
		Vector HTruck_Sch_Tm = new Vector();
		public Vector getHTruck_Sch_Tm() {return HTruck_Sch_Tm;}
		Vector HTruck_Seller_Remarks = new Vector();
		public Vector getHTruck_Seller_Remarks() {return HTruck_Seller_Remarks;}
		Vector HTruck_Load_Dt = new Vector();
		public Vector getHTruck_Load_Dt() {return HTruck_Load_Dt;}
		Vector HTruck_Load_Tm = new Vector();
		public Vector getHTruck_Load_Tm() {return HTruck_Load_Tm;}
		Vector HTruck_Vol = new Vector();
		public Vector getHTruck_Vol() {return HTruck_Vol;}
		Vector HTruck_Vol_Unit = new Vector();
		public Vector getHTruck_Vol_Unit() {return HTruck_Vol_Unit;}
		Vector HTruck_Ene = new Vector();
		public Vector getHTruck_Ene() {return HTruck_Ene;}
		Vector HTruck_Ene_Unit = new Vector();
		public Vector getHTruck_Ene_Unit() {return HTruck_Ene_Unit;}
		Vector HTruck_Wt = new Vector();
		public Vector getHTruck_Wt() {return HTruck_Wt;}
		Vector HTruck_Wt_Unit = new Vector();
		public Vector getHTruck_Wt_Unit() {return HTruck_Wt_Unit;}
		
		//////////////////////////////SB20200818////////////////////////
		Vector VTruck_Gas_Dt = new Vector();
		public Vector getVTruck_Gas_Dt() {return VTruck_Gas_Dt;}
		Vector VMapp_Id = new Vector();
		public Vector getVMapp_Id() {return VMapp_Id;}
		Vector VSch_Id = new Vector();
		public Vector getVSch_Id() {return VSch_Id;}
		Vector VTruck_Trans_Nm = new Vector();
		public Vector getVTruck_Trans_Nm() {return VTruck_Trans_Nm;}
		Vector VCust_Nm = new Vector();
		public Vector getVCust_Nm() {return VCust_Nm;}
		Vector VCust_Plant_Nm = new Vector();
		public Vector getVCust_Plant_Nm() {return VCust_Plant_Nm;}
		Vector VTruck_Nm = new Vector();
		Vector VCnt = new Vector();
		public Vector getVTruck_Nm() {return VTruck_Nm;}
		Vector VTruck_Sch_Dt = new Vector();
		public Vector getVTruck_Sch_Dt() {return VTruck_Sch_Dt;}
		Vector VTruck_Sch_Tm = new Vector();
		public Vector getVTruck_Sch_Tm() {return VTruck_Sch_Tm;}
		Vector VTruck_Seller_Remarks = new Vector();
		public Vector getVTruck_Seller_Remarks() {return VTruck_Seller_Remarks;}
		Vector VTruck_Load_Dt = new Vector();
		public Vector getVTruck_Load_Dt() {return VTruck_Load_Dt;}
		Vector VTruck_Load_Tm = new Vector();
		public Vector getVTruck_Load_Tm() {return VTruck_Load_Tm;}
		Vector VTruck_Vol = new Vector();
		public Vector getVTruck_Vol() {return VTruck_Vol;}
		Vector VTruck_Vol_Unit = new Vector();
		public Vector getVTruck_Vol_Unit() {return VTruck_Vol_Unit;}
		Vector VTruck_Ene = new Vector();
		public Vector getVTruck_Ene() {return VTruck_Ene;}
		Vector VTruck_Ene_Unit = new Vector();
		public Vector getVTruck_Ene_Unit() {return VTruck_Ene_Unit;}
		Vector VTruck_Wt = new Vector();
		public Vector getVTruck_Wt() {return VTruck_Wt;}
		Vector VTruck_Wt_Unit = new Vector();
		public Vector getVTruck_Wt_Unit() {return VTruck_Wt_Unit;}
		Vector nomModalDates = new Vector();
		public Vector getNomModalDates() {return nomModalDates;}
		public void setNomModalDates(Vector nomModalDates) {this.nomModalDates = nomModalDates;}


		public Vector getWeeklytenderNo() {return weeklytenderNo;}
		public void setWeeklytenderNo(Vector weeklytenderNo) {this.weeklytenderNo = weeklytenderNo;}
		public Vector getWeeklyLOANo() {return weeklyLOANo;}
		public void setWeeklyLOANo(Vector weeklyLOANo) {this.weeklyLOANo = weeklyLOANo;}
		public Vector getWeeklyLOARevNo() {return weeklyLOARevNo;}
		public void setWeeklyLOARevNo(Vector weeklyLOARevNo) {this.weeklyLOARevNo = weeklyLOARevNo;}
		public Vector getWeeklyFgsa_Rev_No() {return weeklyFgsa_Rev_No;}
		public void setWeeklyFgsa_Rev_No(Vector weeklyFgsa_Rev_No) {this.weeklyFgsa_Rev_No = weeklyFgsa_Rev_No;}
		
		public Vector getRpt_buy_week_customer_nm() {return rpt_buy_week_customer_nm;}
		public Vector getRpt_buy_week_plant_nm() {return rpt_buy_week_plant_nm;}
		public Vector getRpt_buy_week_mmbtu() {return rpt_buy_week_mmbtu;}
		public Vector getRpt_buy_week_mt() {return rpt_buy_week_mt;}
		public Vector getRpt_buy_week_truck_nm() {return rpt_buy_week_truck_nm;}
		public Vector getRpt_buy_week_arrival_tm() {return rpt_buy_week_arrival_tm;}
		public Vector getRpt_buy_week_delivery_point() {return rpt_buy_week_delivery_point;}
		public Vector getRpt_buy_week_remarks() {return rpt_buy_week_remarks;}

		public Vector getRpt_sel_week_customer_nm() {return rpt_sel_week_customer_nm;}
		public Vector getRpt_sel_week_plant_nm() {return rpt_sel_week_plant_nm;}
		public Vector getRpt_sel_week_mmbtu() {return rpt_sel_week_mmbtu;}
		public Vector getRpt_sel_week_mt() {return rpt_sel_week_mt;}
		public Vector getRpt_sel_week_truck_nm() {return rpt_sel_week_truck_nm;}
		public Vector getRpt_sel_week_schedule_tm() {return rpt_sel_week_schedule_tm;}
		public Vector getRpt_sel_week_remarks() {return rpt_sel_week_remarks;}
		public Vector getRpt_sel_week_schDate() {return rpt_sel_week_schDate;}
		
		String Sch_Mapping_id = "";
		public void setSchMappId(String Sch_Mapping_id) {this.Sch_Mapping_id = Sch_Mapping_id;}
		String Mapping_id = "";
		public void setMappId(String Mapping_id) {this.Mapping_id = Mapping_id;}


		public Map<String, String> getLoadedDt() {
			return loadedDt;
		}


		public Map<String, String> getLoadedQty() {
			return loadedQty;
		}


		public Map<String, String> getLoadedQtyMt() {
			return loadedQtyMt;
		}


		public Map<String, String> getTruck_loaded_ene() {
			return truck_loaded_ene;
		}


		public void setTruck_loaded_ene(Map<String, String> truck_loaded_ene) {
			this.truck_loaded_ene = truck_loaded_ene;
		}


		public double getConvt_mmbtu_to_mt() {
			return convt_mmbtu_to_mt;
		}


		public void setConvt_mmbtu_to_mt(double convt_mmbtu_to_mt) {
			this.convt_mmbtu_to_mt = convt_mmbtu_to_mt;
		}


		public Vector getDriver_nm() {
			return driver_nm;
		}
		public String getHtruck_driver() {
			return Htruck_driver;
		}
		public void setHtruck_driver(String htruck_driver) {
			Htruck_driver = htruck_driver;
		}
		public Vector getVCnt() {
			return VCnt;
		}
		public void setVCnt(Vector vCnt) {
			VCnt = vCnt;
		}

}