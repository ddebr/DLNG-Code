package com.seipl.hazira.dlng.contract_mgmt;

import javax.naming.*;
import javax.sql.*;

import java.util.*;
import java.io.IOException;
import java.sql.*;
import java.text.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

@SuppressWarnings("rawtypes")
public class DataBean_MgmtV4 {
	
	 	Connection conn; 
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		Statement stmt5;
		Statement stmt6;
		Statement stmt7;
		Statement stmt8;
		Statement stmt9;
		Statement stmt10;
		Statement stmt11;
		ResultSet rset;
		ResultSet rset1;
		ResultSet rset2;
		ResultSet rset3;
		ResultSet rset4;
		ResultSet rset5;
		ResultSet rset6;
		ResultSet rset7;
		ResultSet rset8;
		ResultSet rset9;
		ResultSet rset10;
		ResultSet rset11;
		String queryString = "";
		String queryString1 = "";
		String queryString2 = "";
		String queryString3 = "";
		String queryString4 = "";
		String queryString5 = "";
		String queryString6 = "";
		String queryString7 = "";
		String queryString8 = "";
		String queryString9 = "";
		String queryString10= "";
		String callFlag = "";
		String gas_date = "";
		String gen_date = ""; //Introduced By Samik Shah On 30th July, 2010 ...
		String from_dt = "";
		String to_dt   ="";
		String week = "";
		String customer_cd = "0";
		String customer_contact_cd = "0";
		String methodName = "";
		String databeanName = "DataBean_Contract_Mgmt";
		
		//Following NumberFormat Object is defined by Samik Shah ... On 29th April, 2010 ...
		NumberFormat nf = new DecimalFormat("###########0.00");
		NumberFormat nf2 = new DecimalFormat("###########0.0000");
		NumberFormat nf3 = new DecimalFormat("###########0.000000000000");
		NumberFormat nf4 = new DecimalFormat("############.##");
		NumberFormat nf5 = new DecimalFormat("############");
		//Following util Object is defined by Samik Shah ... On 29th April, 2010 ...
		
		public Vector custIDAT = new Vector();
		public Vector truckIdAT = new Vector();		
		public Vector truckNameAT = new Vector();
		public Vector tankVolM3AT = new Vector();
		public Vector tankVolTonAT = new Vector();
		public Vector truckLoadedFlag = new Vector();
		public Vector VTruckTransCd = new Vector();//HA20200327
		
		public Vector getVTruckTransCd() {
			return VTruckTransCd;
		}

		public Vector TloadedVol = new Vector();
		public Vector TloadedEne = new Vector();
		public Vector TloadedDt = new Vector();
		public Vector TloadedTm = new Vector();
		public Vector TloadedRemark = new Vector();
		public Vector VLoadedTruck = new Vector();
		public Vector VLoadedNxt_avail_days = new Vector();
		public Vector VLoadedTruckNm = new Vector();
		
		public Vector Vnom_id = new Vector();
		
		
		public String custidForTruk ="";
		public String selCust_id = "";
		public String indx = "";
		public String buyer_mapping_id = "";
		public String nomId ="";
		public String nomDt ="";
		public String revNo ="";
		public String schRevNo = "";
		public String schId ="";
		public String selSchedulCust = "";
		
		String contract_type="";
		
		public Vector custidDST = new Vector();
		public Vector truckidDST = new Vector();
		public Vector trucknmDST = new Vector();
		public Vector truckvolM3DST = new Vector();
		public Vector truckvolTonDST = new Vector();
		
		public Vector TscheduleVol = new Vector();
		public Vector TscheduleEne = new Vector();
		public Vector TscheduleDt = new Vector();
		public Vector TscheduleTm = new Vector();
		public Vector TscheduleRemark = new Vector();
		public Vector TNominatedOrNt = new Vector();
		
		public Vector schedulVol = new Vector();
		public Vector schedulEne = new Vector();
		public Vector schedulDt = new Vector();
		public Vector schedulTm = new Vector();
		public Vector schedulRemark = new Vector();
		
		public Vector custPlant_cdFlag = new Vector();
		public Vector truckNmOnload = new Vector();
		public Vector OnlyLoadedTruck = new Vector();
		public String custPlant_cd ="";
		
		UtilBean util = new UtilBean();
		int truckNomCnt= 0;
		
		
		
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
		    			stmt8 = conn.createStatement();
		    			stmt9 = conn.createStatement();
		    			stmt10 = conn.createStatement();
		    			stmt11 = conn.createStatement();
		    			
//		    			addColumns(); //Hiren_20201007
		    			
		    			if(callFlag.equalsIgnoreCase("CUSTOMER_NOM"))  //SB20200702
						{
		    				fetchDailyBuyerNomDetails(); //SB20200702
		    				if(!selCust_id.equals("")) {
		    					fetchAvailableTruckDetails();
		    				}
						}
		    			else if(callFlag.equalsIgnoreCase("CUSTOMER_SCH"))   //SB20200702
						{
		    				fetchDailySchDetails();  //SB20200702
		    				if(!selSchedulCust.equals("")) {
		    					fetchTruckNomToSchedule();	
		    				}
						}
		    			else if(callFlag.equalsIgnoreCase("TRUCK_LOAD"))  //SB20200702
		    			{
		    				clearVectors();
		    				fetchDailySchDetails();  //SB20200702
		    				fetchTruckLoadingDetails(); //SB20200702
		    			}
		    			else if(callFlag.equalsIgnoreCase("GENERATE_GEN_DATE_FOR_NOMINATION"))
		    			{
		    				generateGenDateForNomination();
		    			}
		    			else if(callFlag.equalsIgnoreCase("CUSTOMER_WEEKLY_NOM"))  
						{
		    				fetchWeeklyBuyerNomDetails22();
		    				if(!selCust_id.equals("")) {
		    					fetchWeeklyAvailableTruckDetails22();
		    				}
						}
		    			else if(callFlag.equalsIgnoreCase("CUSTOMER_WEEKLY_SCH"))   //SB20200702
						{
		    				fetchWeeklySchDetails();  //SB20200702
		    				if(!selSchedulCust.equals("")) {
		    					fetchTruckWeeklyNomToSchedule();	
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
						e.printStackTrace();
						//////System.out.println("rset is not close "+e);
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
						//////System.out.println("rset1 is not close "+e);
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
						//////System.out.println("rset2 is not close "+e);
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
						//////System.out.println("rset3 is not close "+e);
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
						//////System.out.println("rset4 is not close "+e);
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
						e.printStackTrace();
						//////System.out.println("rset5 is not close "+e);
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
						e.printStackTrace();
						//////System.out.println("rset6 is not close "+e);
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
						e.printStackTrace();
						//////System.out.println("rset6 is not close "+e);
					}
				}
		    	if(rset8 != null)
		    	{
					try
					{
						rset8.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						//////System.out.println("rset6 is not close "+e);
					}
				}
		    	if(rset9 != null)
		    	{
					try
					{
						rset9.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						//////System.out.println("rset6 is not close "+e);
					}
				}
		    	if(rset10 != null)
		    	{
					try
					{
						rset10.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						//////System.out.println("rset6 is not close "+e);
					}
				}
		    	if(rset11 != null)
		    	{
					try
					{
						rset11.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						//////System.out.println("rset6 is not close "+e);
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
						//////System.out.println("stmt is not close "+e);
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
						//////System.out.println("stmt1 is not close "+e);
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
						//////System.out.println("stmt2 is not close "+e);
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
						//////System.out.println("stmt3 is not close "+e);
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
						//////System.out.println("stmt4 is not close "+e);
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
						e.printStackTrace();
						//////System.out.println("stmt5 is not close "+e);
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
						e.printStackTrace();
						//////System.out.println("stmt6 is not close "+e);
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
						e.printStackTrace();
						//////System.out.println("stmt6 is not close "+e);
					}
				}
				if(stmt8 != null)
				{
					try
					{
						stmt8.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						//////System.out.println("stmt6 is not close "+e);
					}
				}
				if(stmt9 != null)
				{
					try
					{
						stmt9.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						//////System.out.println("stmt6 is not close "+e);
					}
				}
				if(stmt10 != null)
				{
					try
					{
						stmt10.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						//////System.out.println("stmt6 is not close "+e);
					}
				}
				if(stmt11 != null)
				{
					try
					{
						stmt11.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						//////System.out.println("stmt6 is not close "+e);
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
						//////System.out.println("conn is not close "+e);
					}
				}
		    	
			}
		}
		
		String tax_cd = "0";
		String tax_factor = "0.00";
		private String fetchTaxRate(String customer_cd,String gas_date,String customer_plant_seq_no)throws SQLException,IOException {
			try {
				String tax_Structure_Dtl = "",tax_struct_cd="";
				tax_factor = "0.00";
				
				queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
						  "A.customer_cd="+customer_cd+" AND " +
						  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
				 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
				 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
				 		  "B.tax_struct_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//				//System.out.println("...HERE TAX_STRUCT_DTL..."+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
					tax_struct_cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_struct_cd = "0";
				}
				queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
							  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
							  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
							  "B.app_date<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ORDER BY A.tax_code";
//				//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					tax_cd = rset.getString(1);
					tax_factor = rset.getString(2);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return tax_factor;
		}
		
		String Exchg_Date = "",Exchg_Rate = "1";
		private String fetchExgRate(String customer_cd,String flsa_no,String sn_no,String cont_type,String gas_date)throws SQLException,IOException {
			try {
				
				String exchg_rate_cd = "1";
				Exchg_Rate = "1";
				Exchg_Date = "";
				
				queryString1 = "SELECT NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
						" FROM DLNG_SN_BILLING_DTL " +
						" WHERE cont_type='"+cont_type+"' "
						+ " AND customer_cd="+customer_cd+" AND " +
						" flsa_no="+flsa_no+" "
						+ " AND sn_no="+sn_no+" ";
//							 				
//				//System.out.println("STEP-2: DLNG_SN_BILLING_DTL: "+queryString1);
				rset = stmt.executeQuery(queryString1);
				if(rset.next()) {
					exchg_rate_cd = rset.getString(1)==null?"1":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(A.eff_dt,'DD/MM/YYYY') FROM FMS7_EXCHG_RATE_ENTRY A " +
						  "WHERE A.exchg_rate_cd="+exchg_rate_cd+" " +
						  "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_EXCHG_RATE_ENTRY B " +
						  "WHERE A.exchg_rate_cd=B.exchg_rate_cd AND " +
						  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
		////System.out.println("Query For Fetching Invoice Previous Available Exchange Day From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Exchg_Date = rset.getString(1);
				}
					
					if(Exchg_Date!=null && !Exchg_Date.equals("") && !Exchg_Date.equals(" "))
					{
						queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+exchg_rate_cd+" " +
						  			  "AND A.eff_dt=TO_DATE('"+Exchg_Date+"','DD/MM/YYYY')";
			//			//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);					
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							Exchg_Rate = nf2.format(Double.parseDouble(rset.getString(1)));
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			return Exchg_Rate;	
		}
		
		String invoice_Sales_Rate = "1";
		private String fetchSalesRate(String customer_cd,String flsa_no,String sn_no,String cont_type,String gas_date,String flsa_rev_no,String sn_rev_no)throws SQLException,IOException {
			
			try {
				
				invoice_Sales_Rate = "";
				String var_sales_rate = "0"; double ori_sale_price = 0;  
				queryString = "SELECT DISTINCT nvl(NEW_SALE_PRICE,0), ORI_SALE_PRICE FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsa_no+" AND SN_NO="+sn_no+" "
						  + " AND  CUSTOMER_CD="+customer_cd+" AND FLAG='A' AND MODIFICATION_SEQ_NO = (SELECT MAX(MODIFICATION_SEQ_NO) FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsa_no+" AND SN_NO="+sn_no+" "
						  + " AND  CUSTOMER_CD="+customer_cd+" AND FLAG='A' AND NEW_PRICE_EFF_DT <=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND CONTRACT_TYPE = '"+cont_type+"' )"
						  + " AND NEW_PRICE_EFF_DT <=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
						  + " AND CONTRACT_TYPE = '"+cont_type+"'";
//				//System.out.println("QRY-01: Variable Sales Rate: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					var_sales_rate = rset.getString(1)==null?"0":rset.getString(1);
					ori_sale_price =  rset.getDouble(2);
					invoice_Sales_Rate =var_sales_rate; 
				}else {
					var_sales_rate = "0";
					ori_sale_price =  0;
					invoice_Sales_Rate =var_sales_rate; 
				}
				if(var_sales_rate.equals("0")) //SB20200327
				{
				queryString = "SELECT DISTINCT nvl(NEW_SALE_PRICE,0), ORI_SALE_PRICE FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsa_no+" AND SN_NO="+sn_no+" "
						  + " AND  CUSTOMER_CD="+customer_cd+" AND FLAG='A'"
						  + " AND CONTRACT_TYPE = '"+cont_type+"'";
//				//System.out.println("QRY-01: Variable Sales Rate: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					var_sales_rate = rset.getString(1)==null?"0":rset.getString(1);
					ori_sale_price =  rset.getDouble(2);
					invoice_Sales_Rate =var_sales_rate; //SB20200327
				}
				}else {
					var_sales_rate = "0";
					ori_sale_price =  0;
					invoice_Sales_Rate =var_sales_rate; 
				}
				
//				//System.out.println("invoice_Sales_Rate------"+invoice_Sales_Rate);
				if(var_sales_rate.equals("0"))
				{
					if(cont_type.equalsIgnoreCase("L")) {
						
						queryString = "SELECT NVL(rate,'0') FROM DLNG_LOA_MST " +
								  "WHERE customer_cd="+customer_cd+" AND tender_no="+flsa_no+" AND " +
								  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+"";
					}else {
						
						queryString = "SELECT NVL(rate,'0') FROM DLNG_SN_MST " +
								  "WHERE customer_cd="+customer_cd+" AND flsa_no="+flsa_no+" AND " +
								  "flsa_rev_no="+flsa_rev_no+" AND sn_no="+sn_no+" AND " +
								  "sn_rev_no="+sn_rev_no+"";
					}
//					//System.out.println("Query For Fetching Sales Price Rate For Invoicing = "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						invoice_Sales_Rate = nf2.format(Double.parseDouble(rset.getString(1)));					
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return invoice_Sales_Rate;
			
		}

		private void addColumns() throws SQLException  {
			try {
				int count=0;
				String s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_WEEKLY_NOM' "+
						" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
				////System.out.println("s****"+s);
				rset=stmt.executeQuery(s);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				if(count==0)
				{
					s="ALTER TABLE DLNG_WEEKLY_NOM ADD CONTRACT_TYPE CHAR(1 BYTE)";
					stmt.executeUpdate(s);
					conn.commit();
				}
				
				int count1=0;
				String s1="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_WEEKLY_TRUCK_NOM_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
//				////System.out.println("s1****"+s1);
				rset=stmt.executeQuery(s1);
				if(rset.next())
				{
					count1=rset.getInt(1);
				}
				if(count1==0)
				{
					s1="ALTER TABLE DLNG_WEEKLY_TRUCK_NOM_DTL ADD CONTRACT_TYPE CHAR(1 BYTE)";
					stmt.executeUpdate(s1);
					conn.commit();
				}
				
				
				/* *******************************for scheduling **************************** */
				
				int count2=0;
				String s2="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_WEEKLY_SCH' "+
						" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
//				////System.out.println("s****"+s);
				rset=stmt.executeQuery(s2);
				if(rset.next())
				{
					count2=rset.getInt(1);
				}
				if(count2==0)
				{
					s="ALTER TABLE DLNG_WEEKLY_SCH ADD CONTRACT_TYPE CHAR(1 BYTE)";
					stmt.executeUpdate(s);
					conn.commit();
				}
				
				int count3=0;
				String s3="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_WEEKLY_TRUCK_SCH_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
//				////System.out.println("s1****"+s1);
				rset=stmt.executeQuery(s3);
				if(rset.next())
				{
					count3=rset.getInt(1);
				}
				if(count3==0)
				{
					s1="ALTER TABLE DLNG_WEEKLY_TRUCK_SCH_DTL ADD CONTRACT_TYPE CHAR(1 BYTE)";
					stmt.executeUpdate(s1);
					conn.commit();
				}
				
				/* *******************************for Daily Next Trucks available Days  **************************** */
				
				int count6=0;
				String s6="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DAILY_TRUCK_NOM_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'NEXT_AVAIL_DAYS'";
//				////System.out.println("s****"+s6);
				rset=stmt.executeQuery(s6);
				if(rset.next())
				{
					count6=rset.getInt(1);
				}
				if(count6==0)
				{
					s="ALTER TABLE DLNG_DAILY_TRUCK_NOM_DTL ADD NEXT_AVAIL_DAYS NUMBER(2,0)";
					stmt.executeUpdate(s);
					conn.commit();
				}
				
				int count7=0;
				String s7="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DAILY_TRUCK_SCH_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'NEXT_AVAIL_DAYS'";
//				////System.out.println("s1****"+s7);
				rset=stmt.executeQuery(s7);
				if(rset.next())
				{
					count7=rset.getInt(1);
				}
				if(count7==0)
				{
					s1="ALTER TABLE DLNG_DAILY_TRUCK_SCH_DTL ADD NEXT_AVAIL_DAYS NUMBER(2,0)";
					stmt.executeUpdate(s1);
					conn.commit();
				}
				
				int count8=0;
				String s8="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_ALLOC_MST' "+
						" AND UPPER(COLUMN_NAME) LIKE 'NEXT_AVAIL_DAYS'";
//				////System.out.println("s1****"+s7);
				rset=stmt.executeQuery(s8);
				if(rset.next())
				{
					count8=rset.getInt(1);
				}
				if(count8==0)
				{
					s1="ALTER TABLE DLNG_ALLOC_MST ADD NEXT_AVAIL_DAYS NUMBER(2,0)";
					stmt.executeUpdate(s1);
					conn.commit();
				}
				
				/* *******************************for Weekly Next Trucks available Days  **************************** */	
				int count9=0;
				String s9="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_WEEKLY_TRUCK_NOM_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'NEXT_AVAIL_DAYS'";
//				////System.out.println("s****"+s6);
				rset=stmt.executeQuery(s9);
				if(rset.next())
				{
					count9=rset.getInt(1);
				}
				if(count9==0)
				{
					s="ALTER TABLE DLNG_WEEKLY_TRUCK_NOM_DTL ADD NEXT_AVAIL_DAYS NUMBER(2,0)";
					stmt.executeUpdate(s);
					conn.commit();
				}
				
				int count10=0;
				String s10="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_WEEKLY_TRUCK_SCH_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'NEXT_AVAIL_DAYS'";
//				////System.out.println("s1****"+s7);
				rset=stmt.executeQuery(s10);
				if(rset.next())
				{
					count10=rset.getInt(1);
				}
				if(count10==0)
				{
					s1="ALTER TABLE DLNG_WEEKLY_TRUCK_SCH_DTL ADD NEXT_AVAIL_DAYS NUMBER(2,0)";
					stmt.executeUpdate(s1);
					conn.commit();
				}
				
				/* ***************** Adding CONTRACT_TYPE column in Daily Nomination ****************/
				int count11=0;
				String s11="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DAILY_NOM' "+
						" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
//				////System.out.println("s****"+s);
				rset=stmt.executeQuery(s11);
				if(rset.next())
				{
					count11=rset.getInt(1);
				}
				if(count11==0)
				{
					s="ALTER TABLE DLNG_DAILY_NOM ADD CONTRACT_TYPE CHAR(1 BYTE)";
					stmt.executeUpdate(s);
					conn.commit();
				}
				
				int count12=0;
				String s12="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DAILY_TRUCK_NOM_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
//				////System.out.println("s1****"+s1);
				rset=stmt.executeQuery(s12);
				if(rset.next())
				{
					count12=rset.getInt(1);
				}
				if(count12==0)
				{
					s1="ALTER TABLE DLNG_DAILY_TRUCK_NOM_DTL ADD CONTRACT_TYPE CHAR(1 BYTE)";
					stmt.executeUpdate(s1);
					conn.commit();
				}
				
				/* ***************** Adding CONTRACT_TYPE column in Daily Seller Nomination ****************/
				int count13=0;
				String s13="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DAILY_SCH' "+
						" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
//				////System.out.println("s****"+s);
				rset=stmt.executeQuery(s13);
				if(rset.next())
				{
					count13=rset.getInt(1);
				}
				if(count11==0)
				{
					s="ALTER TABLE DLNG_DAILY_SCH ADD CONTRACT_TYPE CHAR(1 BYTE)";
					stmt.executeUpdate(s);
					conn.commit();
				}
				
				int count14=0;
				String s14="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_DAILY_TRUCK_SCH_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'CONTRACT_TYPE'";
//				////System.out.println("s1****"+s1);
				rset=stmt.executeQuery(s14);
				if(rset.next())
				{
					count14=rset.getInt(1);
				}
				if(count14==0)
				{
					s1="ALTER TABLE DLNG_DAILY_TRUCK_SCH_DTL ADD CONTRACT_TYPE CHAR(1 BYTE)";
					stmt.executeUpdate(s1);
					conn.commit();
				}
				/* ***************** Adding CONTRACT_TYPE column in Daily Seller Nomination ****************/
				int count15=0;
				String s15="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_TANK_VOL_DTL' "+
						" AND UPPER(COLUMN_NAME) LIKE 'GCV_PER_MMBTU'";
//				////System.out.println("s****"+s);
				rset=stmt.executeQuery(s15);
				if(rset.next())
				{
					count15=rset.getInt(1);
				}
				if(count15==0)
				{
					s="ALTER TABLE DLNG_TANK_VOL_DTL ADD GCV_PER_MMBTU VARCHAR2(10)";
//					//System.out.println("s***"+s);
					stmt.executeUpdate(s);
					conn.commit();
				}
				
				
			}
			catch(Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		
		}
		
		public void clearVectors() {
			methodName = "clearVectors()";
			try {
				daily_Seller_Nom_Fgsa_No.clear();
				daily_Seller_Nom_Mapping_Id.clear();
				daily_Seller_Nom_Fgsa_Rev_No.clear();
				daily_Seller_Nom_Sn_No.clear();
				daily_Seller_Nom_Sn_Rev_No.clear();
				daily_Seller_Nom_Cd.clear();
				daily_Seller_Nom_Abbr.clear();
				daily_Seller_Nom_Dcq.clear();
				daily_Seller_Nom_Plant_Cd.clear();
				daily_Seller_Nom_Plant_Abbr.clear();
				daily_Total_Dcq_Seller_Nom = "";
				daily_Seller_Gen_Day_With_Rev_No.clear();
				daily_Seller_Gen_Day_Time.clear();
				daily_Seller_Nom_Plant_Seq_No.clear();
				daily_Seller_Nom_Plant_Seq_Abbr.clear();
				daily_Seller_Nom_Qty_Mmbtu.clear();
				daily_Seller_Nom_Qty_Scm.clear();
				daily_Total_Mmbtu_Seller_Nom = "";
				daily_Total_Scm_Seller_Nom = "";
				daily_Seller_Nom_Type.clear(); 
				daily_Seller_regas_cargo_boe_no.clear(); 
				daily_Seller_regas_cargo_boe_dt.clear(); 
				daily_Seller_Nom_Contract_Type.clear(); 
				 daily_Buyer_Nom_Fgsa_No.clear();
				 daily_Buyer_Nom_Fgsa_Rev_No.clear();
				 daily_Buyer_Nom_Sn_No.clear();
				 daily_Buyer_Nom_Sn_Rev_No.clear();
				 daily_Buyer_Nom_Cd.clear();
				 daily_Buyer_Nom_Abbr.clear();
				 daily_Buyer_Nom_Dcq.clear();
				 daily_Buyer_Nom_Plant_Cd.clear();
				 daily_Buyer_Nom_Plant_Abbr.clear();
				 daily_Buyer_Nom_Balance_Qty.clear(); 
				 Buyer_Contracted_Qty.clear(); 
				 Buyer_Allocated_Qty.clear(); 
				 Buyer_Nominated_Qty.clear(); 
				 daily_Buyer_Nom_Mdcq_Qty.clear(); 
				 daily_Buyer_Nom_LC_ADV_Flag.clear(); 
				 daily_Buyer_Nom_Current_Balance_Amt.clear(); 
				 daily_Total_Dcq = "";
				 daily_Buyer_Nom_Mapping_Id.clear(); 
				 daily_Buyer_Gen_Day_With_Rev_No.clear();
				 daily_Buyer_Gen_Day_Time.clear();
				 daily_Buyer_Nom_Plant_Seq_No.clear();
				 daily_Buyer_Nom_Plant_Seq_Abbr.clear();
				 daily_Buyer_Nom_Qty_Mmbtu.clear();
				 daily_Buyer_Nom_Qty_Scm.clear();
				 daily_Total_Mmbtu = "";
				 daily_Total_Scm = "";
				 daily_Buyer_regas_cargo_boe_no.clear();
				 daily_Buyer_regas_cargo_boe_dt.clear();
				 PRE_APPROVAL.clear();
				 CLOSURE_FLAG.clear();
				 COMM_PRE_APPROVAL.clear();
				 daily_Buyer_Nom_Type.clear(); 
				 daily_Buyer_Nom_Contract_Type.clear(); 
				 CUST_CD.clear();	
				 CUST_NM.clear();
				 GAS_DT.clear();	
				 gcv = "";
				 ncv = "";
				 qty_nomination.clear();
			 	 ALLOCATED_QTYV.clear();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void generateGenDateForNomination() throws Exception
		{
			methodName = "generateGenDateForNomination()";
			try 
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					gen_date = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		Vector total_inv_qty_mmbtu = new Vector();
		Vector total_inv_qty_scm = new Vector();
		Vector total_inv_qty_mt = new Vector();
		Vector max_nom_rev_no = new Vector();
		Vector all_inv_gen_flg  = new Vector();
		
		@SuppressWarnings("unchecked")
		public void fetchDailyBuyerNomDetails()throws SQLException,IOException
		{
			methodName = "fetchDailyBuyerNomDetails()";
			try 
			{
				String date_tomorrow="";
				rset = stmt.executeQuery("select to_char(to_date('"+gas_date+"','dd/mm/yyyy') - 1,'dd/mm/yyyy') date_tomorrow from dual");
			      if(rset.next()) {
			       date_tomorrow=rset.getString("date_tomorrow");
			      }
			      
				double final_daily_dcq = 0;
				double final_daily_mmbtu = 0;
				double final_daily_scm = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
					
					/*queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
					   "NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100'),NVL(A.cargo_ref_no,'0') " +
					   ",NVL(A.SYS_USE_GAS,'0'), NVL(A.BOE_QTY,'0'), " +
					   "NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY'),pre_approval,NVL(REGAS_CLOSURE_FLAG,'N') " + 
					   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
					   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
					   "AND A.mapping_id=B.mapping_id " +
					   "AND B.CN_NO!='0'  " +
					   "ORDER BY A.mapping_id,A.cargo_seq_no";
					////System.out.println("Fetching LTCORA COntracts.."+queryString1);*/
					int nom_cnt = 0,inv_cnt=0;
					/*queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " ,A.FCC_FLAG, SN_BASE "
							+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
						//SB20200702			+ "AND A.FCC_FLAG='Y' "
							+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";*/
					
						queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " ,A.FCC_FLAG, SN_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy'),"
							+ " SN_CLOSURE_CLOSE,SN_CLOSURE_REQUEST,ADVANCE_COLLECTION "
							+ " FROM DLNG_SN_MST A WHERE "
							+ " A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd)"
							+ " AND A.STATUS='Y' "
							+ " AND A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ " AND (A.SN_CLOSURE_DT >= TO_DATE('"+gas_date+"','DD/MM/YYYY') or A.SN_CLOSURE_DT is null) "
							+ " ORDER BY CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO";
					System.out.println("Fetching SN COntracts.."+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
						String temp_map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-%-"+rset1.getString(4)+"-%"; //Hiren_20210202
//						////System.out.println("rset1.getString(1)*******"+rset1.getString(1));
						if(!rset1.getString(1).equals("0"))
						{
							queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.flsa_no="+rset1.getString(2)+" " +
									   "AND A.sn_no="+rset1.getString(4)+"";
						//	////System.out.println("queryString2****"+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							while(rset2.next())
							{
								nom_cnt = 0;
								inv_cnt=0;
								
								cont_closure_close.add(rset1.getString(15)==null?"":rset1.getString(15));
								cont_closure_request.add(rset1.getString(16)==null?"":rset1.getString(16));
								cont_adv_collection.add(rset1.getString(17)==null?"":rset1.getString(17));
								daily_adv_mapping_id.add("S-"+rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5)+"-"+rset1.getString(13)+"-"+rset1.getString(14));
//								System.out.println("daily_adv_mapping_id***"+daily_adv_mapping_id);
								daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
								queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								
//								////System.out.println("Customer Plant Name Fetch Query (CN) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Plant_Abbr line no 265*******"+rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
								}
								///////////////////////////TAX Applicable///////////////////////////////////
								String tax_Structure_Dtl="";
								String tax_struct_cd="";
								
								queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
											  "A.customer_cd="+rset1.getString(1)+" AND " +
											  "A.plant_seq_no="+rset2.getString(1)+" AND " +
									 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
									 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
									 		  "B.tax_struct_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								
								rset3 = stmt3.executeQuery(queryString);
								if(rset3.next())
								{
									tax_Structure_Dtl = rset3.getString(1)==null?"":rset3.getString(1);
									tax_struct_cd = rset3.getString(2)==null?"0":rset3.getString(2);
								}
								else
								{
									tax_Structure_Dtl = "";
									tax_struct_cd = "0";
								}
								String tax_cd="";
								String tax_nm="";
								int cnt=0;
								queryString = "SELECT NVL(A.tax_code,'0'),B.sht_nm, NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A,FMS7_TAX_MST B WHERE " +
								  "A.tax_str_cd="+tax_struct_cd+" AND B.tax_code=a.tax_code and " +
			 					  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
			 					  "B.app_date<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) and a.tax_on='1' ORDER BY A.tax_code";
								rset3=stmt3.executeQuery(queryString);
								if(rset3.next())
						 		{
									if(cnt==0)
									{
										tax_cd += rset3.getString(1);
										tax_nm += rset3.getString(2);
									}
									else
									{
										tax_cd +=","+ rset3.getString(1);
										tax_nm +=","+rset3.getString(2);
									}
									cnt++;
						 		}
								daily_tax_struct_dtl.add(tax_Structure_Dtl);
								//////////////////////////
								daily_Buyer_Nom_Mapping_Id.add(map_id);
								////////System.out.println("daily_Buyer_Nom_Mapping_Id line no 273*******"+daily_Buyer_Nom_Mapping_Id);
								daily_Buyer_Nom_Fgsa_No.add(rset1.getString(2));
								daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(3));
								daily_Buyer_Nom_Sn_No.add(rset1.getString(4));
								daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(5));
								
								daily_Buyer_Nom_Cd.add(rset1.getString(1));
								//////System.out.println("daily_Buyer_Nom_Cd line no 279*******"+daily_Buyer_Nom_Cd);
								
								daily_Buyer_Nom_Type.add("S");
								daily_Buyer_Nom_Contract_Type.add("SN");
								String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(7))<=100?100:Double.parseDouble(rset1.getString(7)));
								///////////////////////Variable DCQ///////////////////
								double dcq_var=0; double dcq_var_MT=0; 	double convt_mmbtu_to_mt = 51.5;
								
								queryString = "SELECT NVL(dcq,'0') "
										+ " FROM DLNG_SN_DCQ_DTL A WHERE A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
										+ " AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.FLAG='Y' "
										+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_DCQ_DTL B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
										+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.FLAG='Y') ";
//										System.out.println("DLNG_SN_DCQ_DTL: "+queryString);
										rset = stmt.executeQuery(queryString);
										if(rset.next())
										{
//											dcq_var= rset.getDouble(1);
											dcq_var = 0;
										}
										if(dcq_var>0)
										{
											daily_Buyer_Nom_Dcq.add(nf.format(dcq_var));	
											daily_Buyer_Nom_Mdcq_Qty.add(nf.format((dcq_var*Double.parseDouble(temp_mdcq_percentage))/100));
											total_dcq += dcq_var;
											///dcq_var_MT= (dcq_var/Double.parseDouble(rset1.getString(6)))*Double.parseDouble(rset1.getString(10));
											dcq_var_MT= (dcq_var/convt_mmbtu_to_mt);
											daily_Buyer_Nom_Dcq_Mt.add(nf.format(dcq_var_MT));
										}
										else
										{
											daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));	
											daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
											total_dcq += Double.parseDouble(rset1.getString(6));
											daily_Buyer_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(10))));
										}
								//////////////////////////////////////////////////////
								VBuyer_Fcc_Flag.add(rset1.getString(11)==null?"N":rset1.getString(11)); //SB20200718
								VBuyer_Delv_Base.add(rset1.getString(12)==null?"":rset1.getString(12)); //SB20200804
								double ALLOCATED_QTY = 0;
								double ALLOCATED_QTY1 = 0;
								double DEBIT_QTY = 0;
								double CREDIT_QTY = 0;
								
								String CONTRACTED_QTY =nf5.format(Double.parseDouble(rset1.getString(8)) + (Double.parseDouble(rset1.getString(9))));
//								//System.out.println("rset1.getString(8)**"+rset1.getString(8)+"**rset1.getString(9)***"+rset1.getString(9));
//								daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
//								daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
								daily_Buyer_regas_cargo_boe_no.add("");
								daily_Buyer_regas_cargo_boe_dt.add("");
							
									/*queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
											+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='S')"
											+ " AND CONTRACT_TYPE='S' ";
									//System.out.println("line no 499....queryString For ALLOCATED_QTY*******"+queryString);
									rset = stmt.executeQuery(queryString);
												
									if(rset.next())
									{
										if(rset.getString(1)!=null)
										{
											if(!rset.getString(1).trim().equals(""))
											{
												ALLOCATED_QTY = rset.getString(1).trim();////System.out.println("rset from line no 499....ALLOCATED_QTY*******"+ALLOCATED_QTY);
											}
										}
									}*/
									
								queryString = "SELECT nvl(SUM(DAY_VOL),0), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
										+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='S')"
										+ " AND CONTRACT_TYPE='S' ";
//								//System.out.println("Daily Schedule Qty*******"+queryString);
								rset = stmt.executeQuery(queryString);////System.out.println("line no 510....queryString For ALLOCATED_QTY1*******"+queryString);
								if(rset.next())
								{
									if(rset.getString(1)!=null)
									{
										if(!rset.getString(1).trim().equals(""))
										{
											ALLOCATED_QTY1 = Double.parseDouble(rset.getString(1).trim()+"");
										} 
									} 
								} 
								
								queryString = "SELECT nvl(SUM(ENTRY_TOT_ENE),0), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
										  "FROM DLNG_ALLOC_MST WHERE "
										  + " MAPPING_ID like '"+temp_map_id+"' AND CONTRACT_TYPE='S' " +
										  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')";
//								//System.out.println("Allocated Qty******"+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									if(rset.getString(1)!=null)
									{
										if(!rset.getString(1).trim().equals(""))
										{
											ALLOCATED_QTY = Double.parseDouble(rset.getString(1).trim()+"");
										}
									}
								}
								
								/*Hiren_20210217 include dr Quantity Change Criteria*/
								queryString = "SELECT nvl(SUM(DIFF_QTY),0) FROM DLNG_DR_CR_NOTE WHERE "
										+ " CUSTOMER_CD='"+rset1.getString(1)+"'"
										+ " AND FGSA_NO = '"+rset1.getString(2)+"'"
										+ " AND SN_NO = '"+rset1.getString(4)+"'"
										+ " AND CONTRACT_TYPE='S' "
										+ " AND DR_CR_FLAG = 'dr' "
										+ " AND CRITERIA LIKE  '%DIFF-QTY%' "
										+ " AND DR_CR_DT <= TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')";
//								//System.out.println("DR SUM QTY----------"+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next()) {
									DEBIT_QTY = Double.parseDouble(rset.getString(1).trim()+"");
								}
								
								/*Hiren_20210217 include dr Quantity Change Criteria*/
								queryString = "SELECT nvl(SUM(DIFF_QTY),0) FROM DLNG_DR_CR_NOTE WHERE "
										+ " CUSTOMER_CD='"+rset1.getString(1)+"'"
										+ " AND FGSA_NO = '"+rset1.getString(2)+"'"
										+ " AND SN_NO = '"+rset1.getString(4)+"'"
										+ " AND CONTRACT_TYPE='S' "
										+ " AND DR_CR_FLAG = 'cr' "
										+ " AND CRITERIA LIKE  '%DIFF-QTY%' "
										+ " AND DR_CR_DT <= TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')";
//								//System.out.println("CR SUM QTY----------"+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next()) {
									CREDIT_QTY = Double.parseDouble(rset.getString(1).trim()+"");
								}
//								//System.out.println(CONTRACTED_QTY+"----"+ALLOCATED_QTY+"---"+ALLOCATED_QTY1+"---"+DEBIT_QTY+"---"+CREDIT_QTY);
								ALLOCATED_QTYV.add(ALLOCATED_QTY+ALLOCATED_QTY1+DEBIT_QTY-CREDIT_QTY);
//								//System.out.println("ALLOCATED_QTYV----"+ALLOCATED_QTYV);
								daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-((ALLOCATED_QTY-ALLOCATED_QTY1)+DEBIT_QTY-CREDIT_QTY)));
//								//System.out.println("daily_Buyer_Nom_Balance_Qty*****"+daily_Buyer_Nom_Balance_Qty);
								queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
										   	+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Abbr.add(rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Abbr from line no 350 "+daily_Buyer_Nom_Abbr);
								}
								else
								{
									daily_Buyer_Nom_Abbr.add(" ");
								}
								int count=0; String NomId="";
								queryString5 = "SELECT count(MAPPING_ID) FROM DLNG_DAILY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
										+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='S')"
										+ " AND CONTRACT_TYPE='S' ";
								
//								//System.out.println("DLNG_DAILY_NOM = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									count=rset5.getInt(1);
								}
								if(count==0)
								{
									queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
											+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_NOM WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
											+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S' )"
											+ " AND CONTRACT_TYPE='S'";
									
//									//System.out.println("WEEKLY-NOM:  DLNG_WEEKLY_NOM = "+queryString5);
									rset5 = stmt5.executeQuery(queryString5);
									if(rset5.next())
									{
										daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
										daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
										daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
										daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
										daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
										daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
										total_mmbtu += Double.parseDouble(rset5.getString(5));
										total_scm += Double.parseDouble(rset5.getString(6));
										NomId=temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);
										
										VweeklyNomFlag.add("Y");
									}
									else
									{
										daily_Buyer_Gen_Day_With_Rev_No.add("");
										daily_Buyer_Rev_No.add("0");
										daily_Buyer_Gen_Day_Time.add("");
										daily_Buyer_Nom_Plant_Seq_No.add("0");
										daily_Buyer_Nom_Qty_Mmbtu.add("");
										daily_Buyer_Nom_Qty_Scm.add("");
										VweeklyNomFlag.add("N");
									}
								}
								else
								{
								queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
										+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),REV_NO FROM DLNG_DAILY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
										+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S') "
										+ " AND CONTRACT_TYPE='S' ";
								
//								//System.out.println("DAILY-NOM:   = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
									daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
									daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
									daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
									
									/*Hiren_20210319 *** if nominated truck's invoice generated*/
									
									double truck_qty = 0;
									String checkSQl="select truck_nm from DLNG_DAILY_TRUCK_NOM_DTL "
											+ " where NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
											+ " AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
											+ " AND CONTRACT_TYPE='S' "
											+ " AND REV_NO = '"+rset5.getString(7)+"' ";
									//System.out.println("checkSQl----"+checkSQl);
									rset6 = stmt6.executeQuery(checkSQl);
									while (rset6.next()) {
										nom_cnt++;
										String invMstSql = "select TOTAL_QTY from DLNG_INVOICE_MST where "
												+ " MAPPING_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ " AND INVOICE_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
												+ " AND CONTRACT_TYPE='S' "
												+ " AND TRUCK_ID = (select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset6.getString(1)+"' and STATUS = 'Y')";
//										//System.out.println("invMstSql-----"+invMstSql);
										rset7 = stmt7.executeQuery(invMstSql);
										if(rset7.next()) {
											inv_cnt++;
											truck_qty+= rset7.getDouble(1);
										}
									}
									
									double nom_qty_mmbtu = Double.parseDouble(rset5.getString(5)=="0"?"":rset5.getString(5));
									double final_qty_mmbtu = nom_qty_mmbtu - truck_qty ; 
									
									double nom_qty_scm1 = Double.parseDouble(rset5.getString(6)=="0"?"":rset5.getString(6));
									double nom_qty_scm2 = Math.round((truck_qty*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
									double final_qty_scm = nom_qty_scm1 - nom_qty_scm2 ; 
									
									if(Integer.parseInt(rset5.getString(7)+"") > 1) {
										
										daily_Buyer_Nom_Qty_Mmbtu.add(final_qty_mmbtu);
										daily_Buyer_Nom_Qty_Scm.add(final_qty_scm);
									}else {
										
										daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
										daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
									}
									
								/////////////////////////	
									
									total_mmbtu += Double.parseDouble(rset5.getString(5));
									total_scm += Double.parseDouble(rset5.getString(6));
									NomId=temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);
									
									VweeklyNomFlag.add("N");
								}
								else
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Rev_No.add("0");
									daily_Buyer_Gen_Day_Time.add("");
									daily_Buyer_Nom_Plant_Seq_No.add("0");
									daily_Buyer_Nom_Qty_Mmbtu.add("");
									daily_Buyer_Nom_Qty_Scm.add("");
									VweeklyNomFlag.add("N");
								}
								}
//								System.out.println(inv_cnt+"------here------"+nom_cnt);
								if(nom_cnt == inv_cnt && nom_cnt > 0) {
									all_inv_gen_flg.add("Y");
								}else {
									all_inv_gen_flg.add("N");
								}
								
								////Check for Invoice generated////////
//								//System.out.println("multiplying_factor----"+multiplying_factor);
//								//System.out.println("ncv_gcv----"+ncv_gcv);
								/*queryString5 = "SELECT nvl(sum(TOTAL_QTY),0) FROM DLNG_INVOICE_MST "
										+ " WHERE PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ " AND MAPPING_ID  like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ " AND  CONTRACT_TYPE='S' ";	
//								//System.out.println("queryString5--SN--"+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next()) {
									total_inv_qty_mmbtu.add(rset5.getString(1) == null?"0":rset5.getString(1) );
									double total_inv_mmbtu = Double.parseDouble(rset5.getString(1) == null?"0":rset5.getString(1));
									
									double total_inv_scm = Math.round((total_inv_mmbtu*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
									total_inv_qty_scm.add(total_inv_scm);
									total_inv_qty_mt.add(total_inv_mmbtu/convt_mmbtu_to_mt );
									
								}else {
									total_inv_qty_mmbtu.add("0");
									total_inv_qty_scm.add("0");
									total_inv_qty_mt.add("0");
								}*/
								
								queryString5 = "SELECT nvl(sum(EXIT_TOT_ENE),0),nvl(max(NOM_REV_NO),0) FROM DLNG_ALLOC_MST "
										+ " WHERE GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ " AND ALLOC_ID  like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ " AND  CONTRACT_TYPE='S' ";	
//								System.out.println("queryString5--SN--"+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next()) {
									total_inv_qty_mmbtu.add(rset5.getString(1) == null?"0":rset5.getString(1) );
									double total_inv_mmbtu = Double.parseDouble(rset5.getString(1) == null?"0":rset5.getString(1));
									
									double total_inv_scm = Math.round((total_inv_mmbtu*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
									total_inv_qty_scm.add(total_inv_scm);
									total_inv_qty_mt.add(total_inv_mmbtu/convt_mmbtu_to_mt );
									max_nom_rev_no.add(rset5.getString(2) == null?"0":rset5.getString(2));
									
								}else {
									total_inv_qty_mmbtu.add("0");
									total_inv_qty_scm.add("0");
									total_inv_qty_mt.add("0");
									max_nom_rev_no.add("0");
								}
							}
						}
					}//end of while loop
					
//					////System.out.println("Fetching LoA COntracts.."+VBuyer_Delv_Base);
					/* **********************for LoA Contracts***************************** Hiren_20200428*/
					
					/*queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " ,A.FCC_FLAG, LOA_BASE "
							+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
						//SB20200705			+ "AND A.FCC_FLAG='Y' "
							+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
//						System.out.println("Fetching LoA COntracts.."+queryString1);
 * 						
*/					queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
						+ " ,A.FCC_FLAG, LOA_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy'),"
						+ " LOA_CLOSURE_CLOSE,LOA_CLOSURE_REQUEST,ADVANCE_COLLECTION "
						+ " FROM DLNG_LOA_MST A WHERE "
						+ " A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND A.customer_cd=B.customer_cd)"
						+ " AND A.STATUS='Y'"
						+ " AND A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
						+ " AND (A.LOA_CLOSURE_DT >= TO_DATE('"+gas_date+"','DD/MM/YYYY') or A.LOA_CLOSURE_DT is null) "
						+ " ORDER BY CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO";
//					System.out.println("queryString1----"+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						//mappaing_id = customer_cd-tender_no-0-loa_no-loa_rev_no
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4);
						String temp_map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-%-"+rset1.getString(3)+"-%"; //Hiren_20210202
//						////System.out.println("rset1.getString(1)*******"+rset1.getString(1));
						if(!rset1.getString(1).equals("0"))
						{
							queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.TENDER_NO="+rset1.getString(2)+" " +
									   "AND A.LOA_NO="+rset1.getString(3)+" AND LOA_REV_NO = '"+rset1.getString(4)+"'";
//							////System.out.println("LoA Plant No****"+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							while(rset2.next())
							{
								nom_cnt = 0;
								inv_cnt=0;
								
								cont_closure_close.add(rset1.getString(14)==null?"":rset1.getString(14));
								cont_closure_request.add(rset1.getString(15)==null?"":rset1.getString(15));
								cont_adv_collection.add(rset1.getString(16)==null?"":rset1.getString(16));
								daily_adv_mapping_id.add("L-"+rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(12)+"-"+rset1.getString(13));

								daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
								queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//								////System.out.println("LoA Plant Name = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Plant_Abbr line no 265*******"+rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
								}
								///////////////////////////TAX Applicable///////////////////////////////////
								String tax_Structure_Dtl="";
								String tax_struct_cd="";
								
								queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
											  "A.customer_cd="+rset1.getString(1)+" AND " +
											  "A.plant_seq_no="+rset2.getString(1)+" AND " +
									 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
									 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
									 		  "B.tax_struct_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//								////System.out.println("DLNG: TAX: FMS7_CUSTOMER_TAX_STRUCT_DTL: "+queryString);
								rset3 = stmt3.executeQuery(queryString);
								if(rset3.next())
								{
									tax_Structure_Dtl = rset3.getString(1)==null?"":rset3.getString(1);
									tax_struct_cd = rset3.getString(2)==null?"0":rset3.getString(2);
								}
								else
								{
									tax_Structure_Dtl = "";
									tax_struct_cd = "0";
								}
								String tax_cd="";
								String tax_nm="";
								int cnt=0;
								queryString = "SELECT NVL(A.tax_code,'0'),B.sht_nm, NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A,FMS7_TAX_MST B WHERE " +
								  "A.tax_str_cd="+tax_struct_cd+" AND B.tax_code=a.tax_code and " +
			 					  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
			 					  "B.app_date<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) and a.tax_on='1' ORDER BY A.tax_code";
								rset3=stmt3.executeQuery(queryString);
								if(rset3.next())
						 		{
									if(cnt==0)
									{
										tax_cd += rset3.getString(1);
										tax_nm += rset3.getString(2);
									}
									else
									{
										tax_cd +=","+ rset3.getString(1);
										tax_nm +=","+rset3.getString(2);
									}
									cnt++;
						 		}
								daily_tax_struct_dtl.add(tax_Structure_Dtl);
								//////////////////////////
								daily_Buyer_Nom_Mapping_Id.add(map_id);
//								//////System.out.println("daily_Buyer_Nom_Mapping_Id line no 273*******"+rset1.getString(3));
								daily_Buyer_Nom_Fgsa_No.add(rset1.getString(2));
								daily_Buyer_Nom_Fgsa_Rev_No.add("0");
								daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
								daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
								
								daily_Buyer_Nom_Cd.add(rset1.getString(1));
								//////System.out.println("daily_Buyer_Nom_Cd line no 279*******"+daily_Buyer_Nom_Cd);
								
								daily_Buyer_Nom_Type.add("L");
								daily_Buyer_Nom_Contract_Type.add("LoA");
								
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(5))));	
								String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(6))<=100?100:Double.parseDouble(rset1.getString(6)));
								daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(5))*Double.parseDouble(temp_mdcq_percentage))/100));
								total_dcq += Double.parseDouble(rset1.getString(5));
								
								daily_Buyer_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(9))));
								VBuyer_Fcc_Flag.add(rset1.getString(10)==null?"N":rset1.getString(10)); //SB20200718
								VBuyer_Delv_Base.add(rset1.getString(11)==null?"":rset1.getString(11)); //SB20200804
								double ALLOCATED_QTY = 0;
								double ALLOCATED_QTY1 = 0;
								double DEBIT_QTY = 0;
								double CREDIT_QTY = 0;
								
								String CONTRACTED_QTY =nf5.format(Double.parseDouble(rset1.getString(7)) + (Double.parseDouble(rset1.getString(8))));
								
//								daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
//								daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
								daily_Buyer_regas_cargo_boe_no.add("");
								daily_Buyer_regas_cargo_boe_dt.add("");
							
									/*queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
											+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='L')"
											+ " AND CONTRACT_TYPE='L' ";
									////System.out.println("--Fetching Data--"+queryString);
									rset = stmt.executeQuery(queryString);
												
									if(rset.next())
									{
										if(rset.getString(1)!=null)
										{
											if(!rset.getString(1).trim().equals(""))
											{
												ALLOCATED_QTY = rset.getString(1).trim();
												//////System.out.println("rset from line no 313*******"+ALLOCATED_QTY);
											}
										}
									}*/
								queryString = "SELECT nvl(SUM(ENTRY_TOT_ENE),0), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
										  "FROM DLNG_ALLOC_MST WHERE "
										  + " MAPPING_ID like '"+temp_map_id+"' AND CONTRACT_TYPE='L' " +
										  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')";
//								//System.out.println("Allocated Qty******"+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									if(rset.getString(1)!=null)
									{
										if(!rset.getString(1).trim().equals(""))
										{
											ALLOCATED_QTY = Double.parseDouble(rset.getString(1).trim()+"");
										}
									}
								}
									
								queryString = "SELECT nvl(SUM(DAY_VOL),0), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
										+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like'"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='L')"
										+ " AND CONTRACT_TYPE='L' ";
//								//System.out.println("ALLOCATED_QTY1*******"+queryString);	
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									if(rset.getString(1)!=null)
									{
										if(!rset.getString(1).trim().equals(""))
										{
											ALLOCATED_QTY1 = Double.parseDouble(rset.getString(1).trim()+"");
										} 
									} 
								} 
								
								/*Hiren_20210217 include dr Quantity Change Criteria*/
								queryString = "SELECT nvl(SUM(DIFF_QTY),0) FROM DLNG_DR_CR_NOTE WHERE "
										+ " CUSTOMER_CD='"+rset1.getString(1)+"'"
										+ " AND FGSA_NO = '"+rset1.getString(2)+"'"
										+ " AND SN_NO = '"+rset1.getString(4)+"'"
										+ " AND CONTRACT_TYPE='L' "
										+ " AND DR_CR_FLAG = 'dr' "
										+ " AND CRITERIA LIKE  '%DIFF-QTY%' "
										+ " AND DR_CR_DT <= TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')";
//								//System.out.println("DR SUM QTY----------"+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next()) {
									DEBIT_QTY = Double.parseDouble(rset.getString(1).trim()+"");
								}
								
								/*Hiren_20210217 include dr Quantity Change Criteria*/
								queryString = "SELECT nvl(SUM(DIFF_QTY),0) FROM DLNG_DR_CR_NOTE WHERE "
										+ " CUSTOMER_CD='"+rset1.getString(1)+"'"
										+ " AND FGSA_NO = '"+rset1.getString(2)+"'"
										+ " AND SN_NO = '"+rset1.getString(4)+"'"
										+ " AND CONTRACT_TYPE='L' "
										+ " AND DR_CR_FLAG = 'cr' "
										+ " AND CRITERIA LIKE  '%DIFF-QTY%' "
										+ " AND DR_CR_DT <= TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')";
//								//System.out.println("CR SUM QTY----------"+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next()) {
									CREDIT_QTY = Double.parseDouble(rset.getString(1).trim()+"");
								}
								
								ALLOCATED_QTYV.add(ALLOCATED_QTY+ALLOCATED_QTY1+DEBIT_QTY-CREDIT_QTY);
								
								daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-((ALLOCATED_QTY-ALLOCATED_QTY1)+DEBIT_QTY-CREDIT_QTY)));
								
								queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
										   	+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Abbr.add(rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Abbr from line no 350 "+daily_Buyer_Nom_Abbr);
								}
								else
								{
									daily_Buyer_Nom_Abbr.add(" ");
								}
								int count=0; String NomId="";
								queryString5 = "SELECT count(MAPPING_ID) FROM DLNG_DAILY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
										+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='L') "
										+ " AND CONTRACT_TYPE='L' ";
								
								//System.out.println("Daily-NOM:  DLNG_DAILY_NOM = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									count=rset5.getInt(1);
								}
								if(count==0)
								{
								queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
										+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
										+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='L') "
										+ " AND CONTRACT_TYPE='L'";	
//								//System.out.println(" DLNG_WEEKLY_NOM : "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
									daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
									daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
									daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
									daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
									daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
									total_mmbtu += Double.parseDouble(rset5.getString(5));
									total_scm += Double.parseDouble(rset5.getString(6));
									NomId=map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1);
									
									VweeklyNomFlag.add("Y");
								}
								else
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Rev_No.add("0");
									daily_Buyer_Gen_Day_Time.add("");
									daily_Buyer_Nom_Plant_Seq_No.add("0");
									daily_Buyer_Nom_Qty_Mmbtu.add("");
									daily_Buyer_Nom_Qty_Scm.add("");
									VweeklyNomFlag.add("N");
								}
								}
								else
								{
								queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
										+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),REV_NO FROM DLNG_DAILY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
										+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='L') "
										+ " AND CONTRACT_TYPE='L' ";										
								//System.out.println("DAILY-NOM:  DLNG_DAILY_NOM = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
									daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
									daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
									daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
									
									
									/*Hiren_20210319 *** if nominated truck's invoice generated*/
									double truck_qty = 0;
										String checkSQl="select truck_nm from DLNG_DAILY_TRUCK_NOM_DTL "
												+ " where NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
												+ " AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ " AND CONTRACT_TYPE='L' "
												+ " AND REV_NO = '"+rset5.getString(7)+"' ";
										//System.out.println("checkSQl----"+checkSQl);
										rset6 = stmt6.executeQuery(checkSQl);
										while (rset6.next()) {
											nom_cnt++;
											String invMstSql = "select TOTAL_QTY from DLNG_INVOICE_MST where "
													+ " MAPPING_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
													+ " AND INVOICE_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
													+ " AND CONTRACT_TYPE='S' "
													+ " AND TRUCK_ID = (select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset6.getString(1)+"' and STATUS = 'Y')";
											//System.out.println("invMstSql-----"+invMstSql);
											rset7 = stmt7.executeQuery(invMstSql);
											if(rset7.next()) {
												inv_cnt++;
												truck_qty+= rset7.getDouble(1);
											}
										}
										
										double nom_qty_mmbtu = Double.parseDouble(rset5.getString(5)=="0"?"":rset5.getString(5));
										double final_qty_mmbtu = nom_qty_mmbtu - truck_qty ; 
										
										double nom_qty_scm1 = Double.parseDouble(rset5.getString(6)=="0"?"":rset5.getString(6));
										double nom_qty_scm2 = Math.round((truck_qty*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
										double final_qty_scm = nom_qty_scm1 - nom_qty_scm2 ; 
										
										if(Integer.parseInt(rset5.getString(7)+"") > 1) {
											daily_Buyer_Nom_Qty_Mmbtu.add(final_qty_mmbtu);
											daily_Buyer_Nom_Qty_Scm.add(final_qty_scm);
										}else {
											
											daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
											daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
										}
									/////////////////////////
									
									total_mmbtu += Double.parseDouble(rset5.getString(5));
									total_scm += Double.parseDouble(rset5.getString(6));
									NomId=temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1);
									
									VweeklyNomFlag.add("N");
								}
								else
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Rev_No.add("0");
									daily_Buyer_Gen_Day_Time.add("");
									daily_Buyer_Nom_Plant_Seq_No.add("0");
									daily_Buyer_Nom_Qty_Mmbtu.add("");
									daily_Buyer_Nom_Qty_Scm.add("");
									
									VweeklyNomFlag.add("N");
								}
								}
								
								//System.out.println(inv_cnt+"------here- LoA-----"+nom_cnt);
								if(nom_cnt == inv_cnt && nom_cnt > 0) {
									all_inv_gen_flg.add("Y");
								}else {
									all_inv_gen_flg.add("N");
								}
							////Check for Invoice generated////////
								
								/*queryString5 = "SELECT nvl(sum(TOTAL_QTY),0) FROM DLNG_INVOICE_MST "
										+ " WHERE PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ " AND MAPPING_ID  like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ " AND  CONTRACT_TYPE='L' ";	
								//System.out.println("queryString5--LoA--"+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next()) {
									total_inv_qty_mmbtu.add(rset5.getString(1) == null?"0":rset5.getString(1) );
									double total_inv_mmbtu = Double.parseDouble(rset5.getString(1) == null?"0":rset5.getString(1));
									
									double total_inv_scm = Math.round((total_inv_mmbtu*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
//									//System.out.println("total_inv_scm==="+total_inv_scm);
									total_inv_qty_scm.add(total_inv_scm);
									total_inv_qty_mt.add(total_inv_mmbtu/convt_mmbtu_to_mt );
								}else {
									total_inv_qty_mmbtu.add("0");
									total_inv_qty_scm.add("0");
									total_inv_qty_mt.add("0");
								}*/
								queryString5 = "SELECT nvl(sum(EXIT_TOT_ENE),0),nvl(max(NOM_REV_NO),0) FROM DLNG_ALLOC_MST "
										+ " WHERE GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ " AND ALLOC_ID  like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ " AND  CONTRACT_TYPE='L' ";	
//								System.out.println("queryString5--SN--"+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next()) {
									total_inv_qty_mmbtu.add(rset5.getString(1) == null?"0":rset5.getString(1) );
									double total_inv_mmbtu = Double.parseDouble(rset5.getString(1) == null?"0":rset5.getString(1));
									
									double total_inv_scm = Math.round((total_inv_mmbtu*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
									total_inv_qty_scm.add(total_inv_scm);
									total_inv_qty_mt.add(total_inv_mmbtu/convt_mmbtu_to_mt );
									max_nom_rev_no.add(rset5.getString(2) == null?"0":rset5.getString(2));
								}else {
									total_inv_qty_mmbtu.add("0");
									total_inv_qty_scm.add("0");
									total_inv_qty_mt.add("0");
									max_nom_rev_no.add("0");
								}
								/*int NomIdCnt=0;
								queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST "
										+ " WHERE MAPPING_ID like '"+NomId+"' and PLANT_SEQ_NO = '"+rset2.getString(1)+"'  AND  "
										+ " CONTRACT_TYPE='L' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";							
//								////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
									NomIdCnt=rset5.getInt(1);
								if(NomIdCnt>0)
									VBuyer_Inv_Flag.add("Y");
								else
									VBuyer_Inv_Flag.add("N");*/
							}
						}
					}
					
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
				
				if(final_daily_mmbtu>0)
				{
					daily_Total_Mmbtu = nf.format(final_daily_mmbtu);
				}
				else
				{
					daily_Total_Mmbtu = "";
				}
				
				if(final_daily_scm>0)
				{
					daily_Total_Scm = nf.format(final_daily_scm);
				}
				else
				{
					daily_Total_Scm = "";
				}
				
				daily_Total_Dcq = nf.format(final_daily_dcq);
				
				queryString = "SELECT DISTINCT CUSTOMER_CD FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUST_CD.add(rset.getString(1)==null?"0":rset.getString(1));
					//////System.out.println("CUST_CD from line no 420 "+CUST_CD);
					
				}
				
				for(int i =0;i<daily_Buyer_Nom_Contract_Type.size();i++)
				{
					qty_nomination.add(daily_Buyer_Nom_Balance_Qty.elementAt(i)+"");
				}
				//System.out.println("daily_Buyer_Nom_Balance_Qty.."+daily_Buyer_Nom_Balance_Qty);	
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		public Vector daily_Buyer_Nom_Fgsa_No = new Vector();
		public Vector daily_Buyer_Nom_Fgsa_Rev_No = new Vector();
		public Vector daily_Buyer_Nom_Sn_No = new Vector();
		public Vector daily_Buyer_Nom_Sn_Rev_No = new Vector();
		public Vector daily_Buyer_Nom_Cd = new Vector();
		public Vector daily_Buyer_Nom_Abbr = new Vector();
		public Vector daily_Buyer_Nom_Dcq = new Vector();
		public Vector daily_Buyer_Nom_Dcq_Mt = new Vector();
		public Vector daily_Seller_Nom_Dcq_Mt = new Vector();
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
		public Vector daily_Buyer_Rev_No = new Vector();
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
		public Vector daily_adv_mapping_id = new Vector(); 
		public Vector cont_closure_close = new Vector(); 
		public Vector cont_closure_request = new Vector(); 
		public Vector cont_adv_collection = new Vector(); 
		
		String adv_collection_flg = "";
		String oa_flag = "N";
		String selMapId = "";
		String sysdate = "";
		double fsru_tank_vol = 0, int_tank_vol = 0, int_tank_ene = 0, int_tankCapacity = 0, int_tankVolAvl = 0, int_tankCapacityM3 = 0, int_tankVolAvlM3 = 0;
		String int_tankId = "";
		
		//Assumption : 1 M3 of LNG = 23.9 MMBTU
		double conversion_factor_from_m3_to_mmbtu = 23.9;
		
		
		@SuppressWarnings("unchecked")
		private void fetchTruckNomToSchedule()throws SQLException,IOException {
			methodName = "fetchTruckNomToSchedule()";
			int dailyNomiTrucknCnt = 0 ;
			tot_inv_mmbtu = "0";
			tot_inv_scm = "0";
			tot_inv_mt = "0";
			
			try {//EFF_DT = TO_DATE('"+nomDt+"','DD/MM/YYYY') Query Change By SUJIT05MARCH2020
				/*HA20200207 fetching truck nomination data*/ 	
				String tempMap [] = buyer_mapping_id.split("-");
				String temp_map_id =tempMap[0]+"-"+tempMap[1]+"-%-"+tempMap[3]+"-%";
				String tempNom [] = schId.split("-");
				String temp_nom_id = tempNom[0]+"-"+tempNom[1]+"-%-"+tempNom[3]+"-%-"+tempNom[5]+"-"+tempNom[6];
				
				int alloc_cnt = 0;
				queryString="SELECT count(*) from DLNG_TANK_TRUCK_MST A,DLNG_DAILY_TRUCK_NOM_DTL B"
						+ " WHERE A.STATUS ='Y' " //AND A.DEL_FLAG='Y' AND A.CUSTOMER_CD in ('"+selSchedulCust+"','100') "
						+ " AND B.MAPPING_ID like '"+temp_map_id+"' AND B.NOM_ID like '"+temp_nom_id+"'"
						+ " AND B.REV_NO='"+revNo+"'"
						+ " AND B.CONTRACT_TYPE='"+contract_type+"' "
						+ " AND B.NOM_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') "
						+ " AND A.TRUCK_NM = B.TRUCK_NM" ;
				//System.out.println("queryString***1"+queryString);
				rset8 = stmt8.executeQuery(queryString);
				if(rset8.next()) {
					dailyNomiTrucknCnt = rset8.getInt(1);
				}
//				//System.out.println("dailyNomiTrucknCnt***"+dailyNomiTrucknCnt);
				
				if(dailyNomiTrucknCnt > 0) {
					
					queryString="SELECT A.TRUCK_NM,A.TRUCK_ID,A.TANK_VOL_M3,A.TANK_VOL_TON,"
							+ " B.TRUCK_VOL,B.TRUCK_ENE,TO_CHAR(B.ARRIVAL_DT,'DD/MM/YYYY'),B.ARRIVAL_TIME,"
							+ " B.REMARKS,B.TRANS_CD, LOAD_CAP,NEXT_AVAIL_DAYS"
							+ " FROM DLNG_TANK_TRUCK_MST A,DLNG_DAILY_TRUCK_NOM_DTL B"
							+ " WHERE A.STATUS ='Y' "//AND A.DEL_FLAG='Y' AND A.CUSTOMER_CD in ('"+selSchedulCust+"','100') "
							+ " AND B.MAPPING_ID like '"+temp_map_id+"' AND B.NOM_ID like '"+temp_nom_id+"' "
							+ " AND B.REV_NO='"+revNo+"'"
							+ " AND CONTRACT_TYPE='"+contract_type+"'"
							+ " AND B.NOM_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') "
							+ " AND A.TRUCK_NM = B.TRUCK_NM " ;
				}else {
					
					queryString="SELECT A.TRUCK_NM,A.TRUCK_ID,A.TANK_VOL_M3,A.TANK_VOL_TON,"
							+ " B.TRUCK_VOL,B.TRUCK_ENE,TO_CHAR(B.ARRIVAL_DT,'DD/MM/YYYY'),B.ARRIVAL_TIME,B.REMARKS,B.TRANS_CD, LOAD_CAP,NEXT_AVAIL_DAYS"
							+ " FROM DLNG_TANK_TRUCK_MST A,DLNG_WEEKLY_TRUCK_NOM_DTL B"
							+ " WHERE A.STATUS ='Y' "
							+ " AND B.MAPPING_ID like '"+temp_map_id+"' AND B.NOM_ID like '"+temp_nom_id+"' AND B.REV_NO='"+revNo+"'"
							+ " AND CONTRACT_TYPE='"+contract_type+"'"
							+ " AND B.NOM_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') "
							+ " AND A.TRUCK_NM = B.TRUCK_NM " ;
				}
				
				//System.out.println("queryString***2"+queryString);
				rset8 = stmt8.executeQuery(queryString);
				while(rset8.next()) 
				{
					int invCnt=0;
					queryString5 = "SELECT count(*) FROM DLNG_INVOICE_MST "
							+ " WHERE INVOICE_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ " AND MAPPING_ID  like '"+temp_nom_id+"'  "
							+ " AND  CONTRACT_TYPE='"+contract_type+"' "
							+ " AND  TRUCK_ID = '"+rset8.getString(2)+"' ";	
					
					//System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next()) {
						invCnt = rset5.getInt(1);
					} 
					
					if(invCnt == 0 ) {
						
						Vtruck_Inv_Flag.add("N");
						
						truckNomCnt++;
						trucknmDST.add(rset8.getString(1)==null?"":rset8.getString(1));
						truckidDST.add(rset8.getString(2)==null?"":rset8.getString(2));
						TscheduleVol.add(rset8.getString(5)==null?"":rset8.getString(5));
						TscheduleEne.add(rset8.getString(6)==null?"":rset8.getString(6));
						TscheduleDt.add(rset8.getString(7)==null?"":rset8.getString(7));
						TscheduleTm.add(rset8.getString(8)==null?"":rset8.getString(8));
						TscheduleRemark.add(rset8.getString(9)==null?"":rset8.getString(9));
						String buyerComment = rset8.getString(9)==null?"N.A":rset8.getString(9);
						String buyerDt = rset8.getString(7)==null?"":rset8.getString(7);
						String buyerTm = rset8.getString(8)==null?"":rset8.getString(8);
	//					buyerComment="<font color='blue' >"+rset8.getString(9)+"</font>";
						
						VTruckTransCd.add(rset8.getString(10)==null?"":rset8.getString(10));//Hiren_20200329
						truck_load_capacity = rset8.getDouble(3)*(rset8.getDouble(11)/100); //SB20200730
						truckvolM3DST.add(nf.format(truck_load_capacity)); //SB20181219
						truckvolTonDST.add(nf.format(rset8.getDouble(4)*(rset8.getDouble(11)/100)));
						VLoadedNxt_avail_days.add(rset8.getString(12)==null?"":rset8.getString(12)); //Hiren_20201103
						
						
						if(dailyNomiTrucknCnt > 0) {
							
							queryString= "SELECT TRUCK_VOL,TRUCK_ENE,TO_CHAR(SCH_DT,'DD/MM/YYYY'),SCH_TIME,REMARKS FROM DLNG_DAILY_TRUCK_SCH_DTL WHERE MAPPING_ID like '"+temp_map_id+"'"
									+ " AND SCH_ID like '"+temp_nom_id+"' AND REV_NO='"+schRevNo.trim()+"' AND SCH_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') AND TRUCK_NM='"+rset8.getString(1).trim()+"'"
									+ " AND CONTRACT_TYPE='"+contract_type+"'";
						}else {
							
							queryString= "SELECT TRUCK_VOL,TRUCK_ENE,TO_CHAR(SCH_DT,'DD/MM/YYYY'),SCH_TIME,REMARKS FROM DLNG_WEEKLY_TRUCK_SCH_DTL WHERE MAPPING_ID like '"+temp_map_id+"'"
									+ " AND SCH_ID like '"+temp_nom_id+"' AND REV_NO='"+schRevNo.trim()+"' AND SCH_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') AND TRUCK_NM='"+rset8.getString(1).trim()+"'"
									+ " AND CONTRACT_TYPE='"+contract_type+"'";
						}
						//System.out.println("queryString***"+queryString);
						rset10 = stmt10.executeQuery(queryString);
						if(rset10.next()) 
						{
							schedulVol.add(rset10.getString(1)==null?"":rset10.getString(1));
							schedulEne.add(rset10.getString(2)==null?"":rset10.getString(2));
							schedulDt.add(rset10.getString(3)==null?"":rset10.getString(3));
							schedulTm.add(rset10.getString(4)==null?"":rset10.getString(4));
							schedulRemark.add(rset10.getString(5)==null?"":rset10.getString(5));
						}
						else 
						{
							schedulVol.add("");
							schedulEne.add("");
							schedulDt.add(buyerDt);
							schedulTm.add(buyerTm);
							schedulRemark.add(buyerComment);
						}
					//System.out.println("buyerComment*****"+buyerComment);
				}
					
					/*checking for truck Allocation*/
					String dailyAllocCnt = "SELECT LOADED_ENE,LOADED_SCM,LOADED_VOL FROM DLNG_TANK_VOL_DTL WHERE "
							+ " SCH_ID like '"+temp_nom_id+"' "
							+ " AND TRN_NM='"+rset8.getString(1)+"'"
							+ " AND EFF_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY')";
//					System.out.println("dailyAllocCnt******"+dailyAllocCnt);
					rset1 = stmt1.executeQuery(dailyAllocCnt);
					if(rset1.next()) {
						
						alloc_cnt++;  //= Integer.parseInt(rset.getString(1)==null?"0":rset.getString(1));
						Vtruck_alloc_mmbtu.add(rset1.getString(1)==null?"0":rset1.getString(1));
						Vtruck_alloc_scm.add(rset1.getString(2)==null?"0":rset1.getString(2));
						Vtruck_alloc_mt.add(rset1.getString(3)==null?"0":rset1.getString(3));
					}else {
						alloc_cnt = 0 ;
						Vtruck_alloc_mmbtu.add("0");
						Vtruck_alloc_scm.add("0");
						Vtruck_alloc_mt.add("0");
					}
					
					if(alloc_cnt > 0) {
						Vtruck_alloc_flag.add("Y");
					}else {
						Vtruck_alloc_flag.add("N");
					}
					
			}		
			/*fetching invoice Generated Trucks*/
				String invTruckSql = "select A.TRUCK_ID,B.TRUCK_NM,B.TANK_VOL_M3,B.TANK_VOL_TON,B.LOAD_CAP "
						+ " from DLNG_INVOICE_MST A,DLNG_TANK_TRUCK_MST B where"
						+ " INVOICE_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY')"
						+ " AND MAPPING_ID like '"+temp_nom_id+"' "
						+ " AND CONTRACT_TYPE='"+contract_type+"'"
						+ " AND A.TRUCK_ID = B.TRUCK_ID ";
				//System.out.println("invTruckSql----------"+invTruckSql);
				rset = stmt.executeQuery(invTruckSql);
				while (rset.next()) {
					
					Vtruck_Inv_Flag.add("Y");
					
					truckidDST.add(rset.getString(1)==null?"":rset.getString(1));
					trucknmDST.add(rset.getString(2)==null?"":rset.getString(2));
					TscheduleVol.add("");
					TscheduleEne.add("");
					TscheduleDt.add("");
					TscheduleTm.add("");
					TscheduleRemark.add("");
					VTruckTransCd.add("");//Hiren_20200329
					truck_load_capacity = rset.getDouble(3)*(rset.getDouble(5)/100); //SB20200730
					truckvolM3DST.add(nf.format(truck_load_capacity)); //SB20181219
					truckvolTonDST.add(nf.format(rset.getDouble(4)*(rset.getDouble(5)/100)));
					VLoadedNxt_avail_days.add(""); //Hiren_20201103
					
					queryString= "SELECT TRUCK_VOL,TRUCK_ENE,TO_CHAR(SCH_DT,'DD/MM/YYYY'),SCH_TIME,REMARKS FROM DLNG_DAILY_TRUCK_SCH_DTL WHERE MAPPING_ID like '"+temp_map_id+"'"
							+ " AND SCH_ID like '"+temp_nom_id+"' "
							+ " AND SCH_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') "
							+ " AND TRUCK_NM='"+rset.getString(2).trim()+"'"
							+ " AND CONTRACT_TYPE='"+contract_type+"'"
							+ " AND REV_NO = (select max(rev_no) from DLNG_DAILY_TRUCK_SCH_DTL where"
								+ " SCH_ID like '"+temp_nom_id+"' "
								+ " AND SCH_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') "
								+ " AND TRUCK_NM='"+rset.getString(2).trim()+"'"
								+ " AND CONTRACT_TYPE='"+contract_type+"' )";
					
					//System.out.println("queryString***"+queryString);
					rset1 = stmt1.executeQuery(queryString);
					if(rset1.next()) 
					{
						schedulVol.add(rset1.getString(1)==null?"":rset1.getString(1));
						schedulEne.add(rset1.getString(2)==null?"":rset1.getString(2));
						schedulDt.add(rset1.getString(3)==null?"":rset1.getString(3));
						schedulTm.add(rset1.getString(4)==null?"":rset1.getString(4));
						schedulRemark.add(rset1.getString(5)==null?"":rset1.getString(5));
						
					}else {
						schedulVol.add("");
						schedulEne.add("");
						schedulDt.add("");
						schedulTm.add("");
						schedulRemark.add("");
					}
					
					/*	checking invoice generated for the TRUCK */
					double NomIdCnt=0;
					
					queryString5 = "SELECT NVL(TOTAL_QTY,0) FROM DLNG_INVOICE_MST "
							+ " WHERE INVOICE_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ " AND MAPPING_ID  like '"+temp_nom_id+"'  "
							+ " AND  CONTRACT_TYPE='"+contract_type+"' "
							+ " AND  TRUCK_ID = '"+rset.getString(1)+"' ";							
					//System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next()) {
						NomIdCnt = rset5.getDouble(1);
					    total_all_inv_qty+= NomIdCnt;
					    
					} 
					if(NomIdCnt>0) {
						
						Vtruck_Inv_qty.add(nf5.format(NomIdCnt));
						Vtruck_Inv_qty_mt.add(nf5.format((NomIdCnt) / convt_mmbtu_to_mt));
						total_all_inv_qty_scm+=  Math.round((NomIdCnt*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
//						//System.out.println(NomIdCnt+"---"+multiplying_factor+"----"+ncv_gcv);
					}else {
						
						Vtruck_Inv_qty.add("");
					}
					
				}
				
				tot_inv_mmbtu = nf5.format(total_all_inv_qty);
				tot_inv_scm = nf5.format(total_all_inv_qty_scm);
				tot_inv_mt = nf5.format((total_all_inv_qty / convt_mmbtu_to_mt));
				
				if(truckNomCnt == 0) { // if no truck selection while nomination time
					
					queryString8 = "SELECT TRUCK_NM,TRUCK_ID,TANK_VOL_M3,TANK_VOL_TON,CUSTOMER_CD,TRANS_CD FROM DLNG_TANK_TRUCK_MST WHERE STATUS ='Y' AND DEL_FLAG='Y' AND CUSTOMER_CD in ('"+selSchedulCust+"','100')";
					//System.out.println("queryString8 : "+queryString8);
					rset8 = stmt8.executeQuery(queryString8);
					
					while(rset8.next()) 
					{
						Vtruck_Inv_Flag.add("N");
						trucknmDST.add(rset8.getString(1)==null?"":rset8.getString(1));
						truckidDST.add(rset8.getString(2)==null?"":rset8.getString(2));
						truckvolM3DST.add(rset8.getString(3)==null?"":rset8.getString(3));
						truckvolTonDST.add(rset8.getString(4)==null?"":rset8.getString(4));
						VTruckTransCd.add(rset8.getString(6)==null?"":rset8.getString(6));

						TscheduleVol.add("");
						TscheduleEne.add("");
						TscheduleDt.add("");
						TscheduleTm.add("");
						TscheduleRemark.add("");
						
						String queryString11 = "SELECT TRUCK_VOL,TRUCK_ENE,TO_CHAR(SCH_DT,'DD/MM/YYYY'),SCH_TIME,REMARKS FROM DLNG_DAILY_TRUCK_SCH_DTL WHERE MAPPING_ID like '"+temp_map_id+"'"
								+ " AND SCH_ID like '"+temp_nom_id+"' AND REV_NO='"+schRevNo.trim()+"' AND SCH_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') AND TRUCK_NM='"+rset8.getString(1).trim()+"' "
								+ " AND CONTRACT_TYPE='"+contract_type+"'";
						//System.out.println("queryString11***"+queryString11);
						rset10 = stmt10.executeQuery(queryString11);
						
						if(rset10.next()) 
						{
							schedulVol.add(rset10.getString(1)==null?"":rset10.getString(1));
							schedulEne.add(rset10.getString(2)==null?"":rset10.getString(2));
							schedulDt.add(rset10.getString(3)==null?"":rset10.getString(3));
							schedulTm.add(rset10.getString(4)==null?"":rset10.getString(4));
							schedulRemark.add(rset10.getString(5)==null?"":rset10.getString(5));
						}
						else 
						{
							schedulVol.add("");
							schedulEne.add("");
							schedulDt.add("");
							schedulTm.add("");
							schedulRemark.add("");
						}
						
					}	
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("unchecked")
		public void fetchDailySchDetails() throws SQLException,IOException  
		{
			methodName = "fetchDailySchDetails()";
			
			try 
			{
				double final_daily_buyer_mmbtu = 0;
				double final_daily_dcq = 0;
				double final_daily_mmbtu = 0;
				double final_daily_scm = 0;
				double total_buyer_mmbtu = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
				int nom_cnt = 0;
				int inv_cnt = 0;
				//Fetch Tank's Id..
				Vector fsru_tankId = new Vector();
				queryString1 = "SELECT DISTINCT TANK_ID FROM DLNG_TANK_MST WHERE UPPER(TANK_TYPE)='FSRU' ";
			//	////System.out.println("DLNG_TANK_MST: "+queryString1);
				rset = stmt.executeQuery(queryString1);
				while(rset.next()) {
					fsru_tankId.add(rset.getString(1));
				}
				if(fsru_tankId.size()>0) {
					//Tank's effective date's transactions ....
					double tank_vol = 0;
					for(int i=0;i<fsru_tankId.size();i++) {
						queryString1 = "SELECT NVL(EFF_VOL_AVL,'0') FROM DLNG_TANK_VOL_DTL A WHERE TANK_ID = '"+fsru_tankId.elementAt(i)+"' "
//								+ " AND EFF_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
								+ "AND SEQ_NO = (SELECT MAX(SEQ_NO) FROM DLNG_TANK_VOL_DTL B WHERE A.TANK_ID=B.TANK_ID AND A.EFF_DT=B.EFF_DT)  "; //AND SEQ_NO!='0'
//						////System.out.println("Fetching fsru vol avail on effIdt...."+queryString1);
						rset = stmt.executeQuery(queryString1);
						if(rset.next()) {
							tank_vol += rset.getDouble(1);
							//////System.out.println("line no 660 tank_vol...."+tank_vol);
						} else {
							queryString1 = "SELECT NVL(TANK_VOL,'0') FROM DLNG_TANK_MST A WHERE UPPER(TANK_TYPE)='FSRU' AND EFF_DT = (SELECT MAX(EFF_DT) FROM DLNG_TANK_MST B "
									+ "WHERE A.TANK_ID=B.TANK_ID AND A.TANK_TYPE=B.TANK_TYPE AND A.EFF_DT=B.EFF_DT) AND A.TANK_ID='"+fsru_tankId.elementAt(i)+"' ";
//							////System.out.println("Fetching fsru vol avail on effIdt...."+queryString1);
							rset = stmt.executeQuery(queryString1);
							if(rset.next()) {
								tank_vol += rset.getDouble(1);
							} 
						}
					}
					fsru_tank_vol = tank_vol;
				}
				queryString1 = "SELECT DISTINCT TANK_ID,TANK_VOL, TANK_VOL_AVL FROM DLNG_TANK_MST WHERE UPPER(TANK_TYPE)='INT' ";
				rset = stmt.executeQuery(queryString1);
				if(rset.next()) {
					int_tankId = rset.getString(1);
					int_tankCapacity = rset.getDouble(2)*conversion_factor_from_m3_to_mmbtu;
					int_tankCapacityM3 = rset.getDouble(2); //SB20181219
					int_tankVolAvl = rset.getDouble(3)*conversion_factor_from_m3_to_mmbtu;
					int_tankVolAvlM3 = rset.getDouble(3); //SB20181219
					//////System.out.println("line no 695...int_tankId "+int_tankId);
				}
				
				if(!int_tankId.equals("")) {
					queryString1 = "SELECT NVL(EFF_VOL_AVL,'0') FROM DLNG_TANK_VOL_DTL A WHERE TANK_ID = '"+int_tankId+"' "
					+ "AND SEQ_NO = (SELECT MAX(SEQ_NO) FROM DLNG_TANK_VOL_DTL B WHERE SEQ_NO!='0' " +
					"AND EFF_DT <= TO_DATE('"+gas_date+"','DD/MM/YYYY') )";
					//////System.out.println("Fetching int vol avail on effIdt...."+queryString1);
					rset = stmt.executeQuery(queryString1);
					if(rset.next()) {
						int_tank_vol = rset.getDouble(1);
						int_tank_ene = Double.parseDouble(nf2.format(int_tank_vol * conversion_factor_from_m3_to_mmbtu)); //to convert m3 volume to mmbtu
					} else {
						queryString1 = "SELECT NVL(TANK_VOL,'0') FROM DLNG_TANK_MST A WHERE UPPER(TANK_TYPE)='INT' AND EFF_DT = (SELECT MAX(EFF_DT) FROM DLNG_TANK_MST B "
								+ "WHERE A.TANK_ID=B.TANK_ID AND A.TANK_TYPE=B.TANK_TYPE AND A.EFF_DT=B.EFF_DT) AND TANK_ID = '"+int_tankId+"' ";
						//////System.out.println("queryString1***"+queryString1);
						rset = stmt.executeQuery(queryString1);
						if(rset.next()) {
							int_tank_vol = rset.getDouble(1);
							int_tank_ene =Double.parseDouble(nf2.format(int_tank_vol * conversion_factor_from_m3_to_mmbtu)); //to convert m3 volume to mmbtu
						} 
					}
				}
				
				/* **************************** for SN based contract************************** */
				 int dailyNominaionCnt = 0;
				queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0'),START_DT  "
						+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
						+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
					//SB20200702			+ "AND A.FCC_FLAG='Y' "
						+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
						+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') order by CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO";
//					////System.out.println("Fetching SN COntracts.."+queryString1);
					
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						dailyNominaionCnt= 0;
						
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
						String temp_map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-%-"+rset1.getString(4)+"-%"; //Hiren_20210202
						
						if(!rset1.getString(1).equals("0"))
						{
						queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.flsa_no="+rset1.getString(2)+" " +
									   "AND A.sn_no="+rset1.getString(4)+"";
//						////System.out.println("SELECT: DLNG_SN_PLANT_MST: "+queryString1);
							rset2 = stmt2.executeQuery(queryString2);
							while(rset2.next())
							{
								nom_cnt = 0;
								inv_cnt = 0;
								daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
								
								queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Abbr.add("");
								}
								
								queryString4 = "SELECT count(*) FROM DLNG_DAILY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
										+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S')"
										+ " AND CONTRACT_TYPE='S' ";
								//System.out.println("Nom Details****"+queryString4);
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									if(rset4.getInt(1) > 0) {
										dailyNominaionCnt++;
									}else {
										dailyNominaionCnt=0;
									}
									
								}else {
									dailyNominaionCnt = 0;
								}
								
								if(dailyNominaionCnt > 0) { //Hiren_20201015 if daily Buyer nomination done.... 
									
									queryString4 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
											+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),REV_NO FROM DLNG_DAILY_NOM WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
											+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S') "
											+ " AND CONTRACT_TYPE='S' ";
									
									//System.out.println("daily Nom Details****"+queryString4);
									rset4 = stmt4.executeQuery(queryString4);
								}else { //Hiren_20201015 if weekly Buyer nomination is done....
									
								if(!callFlag.equals("TRUCK_LOAD")) {
									
									queryString4 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
											+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),REV_NO FROM DLNG_WEEKLY_NOM WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
											+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S') "
											+ " AND CONTRACT_TYPE='S' ";
									
									////System.out.println("weekly Nom Details**SN**"+queryString4);
									rset4 = stmt4.executeQuery(queryString4);
									}
								}
								
								if(rset4.next())
								{
									
									if(dailyNominaionCnt == 0) {
										VweeklyNomFlag.add("Y");
									}else {
										VweeklyNomFlag.add("N");
									}
									
									daily_Buyer_Gen_Day_With_Rev_No.add(rset4.getString(2)==null?"0":rset4.getString(2));
									daily_Buyer_Rev_No.add(rset4.getString(2)==null?"":rset4.getString(2));
									daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
									daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
									
									/*Hiren_20210319 *** if nominated truck's invoice generated*/
									double truck_qty = 0;
										String checkSQl="select truck_nm from DLNG_DAILY_TRUCK_NOM_DTL "
												+ " where NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
												+ " AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ " AND CONTRACT_TYPE='S' "
												+ " AND REV_NO = '"+rset4.getString(7)+"' ";
										//System.out.println("checkSQl----"+checkSQl);
										rset6 = stmt6.executeQuery(checkSQl);
										while (rset6.next()) {
											nom_cnt++;
											
											String invMstSql = "select TOTAL_QTY from DLNG_INVOICE_MST where "
													+ " MAPPING_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
													+ " AND INVOICE_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
													+ " AND CONTRACT_TYPE='S' "
													+ " AND TRUCK_ID = (select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset6.getString(1)+"' and STATUS = 'Y')";
											//System.out.println("invMstSql-----"+invMstSql);
											rset7 = stmt7.executeQuery(invMstSql);
											if(rset7.next()) {
												inv_cnt++;
												truck_qty+= rset7.getDouble(1);
											}
											
										}
										double nom_qty_mmbtu = Double.parseDouble(rset4.getString(5)=="0"?"":rset4.getString(5));
										double final_qty_mmbtu = nom_qty_mmbtu - truck_qty ; 
										
										double nom_qty_scm1 = Double.parseDouble(rset4.getString(6)=="0"?"":rset4.getString(6));
										double nom_qty_scm2 = Math.round((truck_qty*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
										double final_qty_scm = nom_qty_scm1 - nom_qty_scm2 ; 
										
										//System.out.println("rset4.getString(7)-----"+rset4.getString(7)+"------"+rset4.getString(5));
										if(Integer.parseInt(rset4.getString(7)+"") > 1) {
											daily_Buyer_Nom_Qty_Mmbtu.add(final_qty_mmbtu);
											daily_Buyer_Nom_Qty_Scm.add(final_qty_scm);
										}else {
											
											daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
											daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
										}
									
									total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
									
									queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
												   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
												   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
												   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
												   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
									rset3 = stmt3.executeQuery(queryString3);
									if(rset3.next())
									{
										daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
//										////System.out.println("daily_Buyer_Nom_Plant_Seq_Abbr from line no 802 "+daily_Buyer_Nom_Plant_Seq_Abbr);
									}
									else
									{
										daily_Buyer_Nom_Plant_Seq_Abbr.add("");
									}
									///////////////////////////TAX Applicable///////////////////////////////////
									String tax_Structure_Dtl="";
									String tax_struct_cd="";
									
									queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
												  "A.customer_cd="+rset1.getString(1)+" AND " +
												  "A.plant_seq_no="+rset2.getString(1)+" AND " +
										 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
										 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
										 		  "B.tax_struct_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
									
									rset3 = stmt3.executeQuery(queryString);
									if(rset3.next())
									{
										tax_Structure_Dtl = rset3.getString(1)==null?"":rset3.getString(1);
										tax_struct_cd = rset3.getString(2)==null?"0":rset3.getString(2);
									}
									else
									{
										tax_Structure_Dtl = "";
										tax_struct_cd = "0";
									}
									String tax_cd="";
									String tax_nm="";
									int cnt=0;
									queryString = "SELECT NVL(A.tax_code,'0'),B.sht_nm, NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A,FMS7_TAX_MST B WHERE " +
									  "A.tax_str_cd="+tax_struct_cd+" AND B.tax_code=a.tax_code and " +
				 					  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
				 					  "B.app_date<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) and a.tax_on='1' ORDER BY A.tax_code";
									rset3=stmt3.executeQuery(queryString);
									if(rset3.next())
							 		{
										if(cnt==0)
										{
											tax_cd += rset3.getString(1);
											tax_nm += rset3.getString(2);
										}
										else
										{
											tax_cd +=","+ rset3.getString(1);
											tax_nm +=","+rset3.getString(2);
										}
										cnt++;
							 		}
									
									//System.out.println(inv_cnt+"------here- LoA-----"+nom_cnt);
									if(nom_cnt == inv_cnt && nom_cnt > 0) {
										all_inv_gen_flg.add("Y");
									}else {
										all_inv_gen_flg.add("N");
									}
									daily_tax_struct_dtl.add(tax_Structure_Dtl);
									//////////////////////////
									daily_Seller_Nom_Mapping_Id.add(map_id);
									daily_Seller_Nom_Fgsa_No.add(rset1.getString(2));
									daily_Seller_Nom_Fgsa_Rev_No.add(rset1.getString(3));
									daily_Seller_Nom_Sn_No.add(rset1.getString(4));
									daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(5));
									
									daily_Seller_Nom_Cd.add(rset1.getString(1));
//									////System.out.println("daily_Seller_Nom_Cd from line no 815 "+daily_Seller_Nom_Cd);
									
									daily_Seller_Nom_Type.add("S");
									daily_Seller_Nom_Contract_Type.add("SN");
				///////////////////////Variable DCQ///////////////////
									double dcq_var=0; double dcq_var_MT=0; 	double convt_mmbtu_to_mt = 51.5;
									queryString = "SELECT NVL(dcq,'0') "
											+ " FROM DLNG_SN_DCQ_DTL A WHERE A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
											+ " AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.FLAG='Y' "
											+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_DCQ_DTL B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
											+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.FLAG='Y') ";
//											////System.out.println("DLNG_SN_DCQ_DTL: "+queryString);
											rset = stmt.executeQuery(queryString);
											if(rset.next())
											{
//												dcq_var= rset.getDouble(1);
												dcq_var = 0;
											}
											if(dcq_var>0)
											{
												daily_Seller_Nom_Dcq.add(nf.format(dcq_var));	
											//	daily_Buyer_Nom_Mdcq_Qty.add(nf.format((dcq_var*Double.parseDouble(temp_mdcq_percentage))/100));
												total_dcq += dcq_var;
												///dcq_var_MT= (dcq_var/Double.parseDouble(rset1.getString(6)))*Double.parseDouble(rset1.getString(10));
												dcq_var_MT= (dcq_var/convt_mmbtu_to_mt);
												daily_Seller_Nom_Dcq_Mt.add(nf.format(dcq_var_MT));
											}
											else
											{
												daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));	
												//daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
												total_dcq += Double.parseDouble(rset1.getString(6));
												daily_Seller_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(10))));
											}
									//////////////////////////////////////////////////////
									daily_Seller_regas_cargo_boe_no.add("");
									daily_Seller_regas_cargo_boe_dt.add("");
									
									queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
								   			   		+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
									rset5 = stmt5.executeQuery(queryString5);
									if(rset5.next())
									{
										daily_Seller_Nom_Abbr.add(rset5.getString(1));
//										////System.out.println("daily_Seller_Nom_Abbr from line no 833 "+daily_Seller_Nom_Abbr);
									}
									else
									{
										daily_Seller_Nom_Abbr.add("");
									}
									double SchQty=0; //SB20200723
									/*queryString6 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''),NVL(SCH_ID,''),"
											+ "NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_DAILY_SCH WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+rset1.getString(1)+"' AND "
											+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+rset1.getString(1)+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID='"+rset1.getString(1)+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND SCH_ID='"+rset1.getString(1)+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";*/
									String NomId="";
									//System.out.println("dailyNominaionCnt----------"+dailyNominaionCnt);
									if(dailyNominaionCnt > 0) {
										
										queryString6 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''),NVL(SCH_ID,''),"
												+ "NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),REV_NO FROM DLNG_DAILY_SCH WHERE "
												+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
												+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ "AND REV_NO = ("
												+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
												+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S') "
												+ " AND CONTRACT_TYPE='S' ";
										//System.out.println("daily sch Details****"+queryString4);
										rset6 = stmt6.executeQuery(queryString6);
									}else {
										
										if(!callFlag.equals("TRUCK_LOAD")) {
											queryString6 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''),NVL(SCH_ID,''),"
												+ "NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_SCH WHERE "
												+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
												+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ "AND REV_NO = ("
												+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
												+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S') "
												+ " AND CONTRACT_TYPE='S' ";
										
											////System.out.println("weekly sch Details****"+queryString4);
											rset6 = stmt6.executeQuery(queryString6);
										}
									}
									//System.out.println("queryString60***"+queryString6);
									if(rset6.next())
									{
										daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
										daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
										daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
										SchQty = Double.parseDouble(rset6.getString(5)==null?"0":rset6.getString(5)); //SB20200723
										/*daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
										daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));*/
										//System.out.println("SchQty---------"+SchQty);
										/*Hiren_20210319 *** if nominated truck's invoice generated*/
											double truck_qty_sch = 0;
											String checkSQlSch="select truck_nm from DLNG_DAILY_TRUCK_SCH_DTL "
													+ " where SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
													+ " AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
													+ " AND CONTRACT_TYPE='S' "
													+ " AND REV_NO = '"+rset6.getString(7)+"' ";
											//System.out.println("checkSQl----"+checkSQlSch);
											rset7 = stmt7.executeQuery(checkSQlSch);
											while (rset7.next()) {
												
												/*String invMstSql = "select count(*) from DLNG_INVOICE_MST where "
														+ " MAPPING_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
														+ " AND INVOICE_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
														+ " AND CONTRACT_TYPE='L' "
														+ " AND TRUCK_ID = (select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset6.getString(1)+"' and STATUS = 'Y')";
												//System.out.println("invMstSql-----"+invMstSql);
												rset7 = stmt7.executeQuery(invMstSql);
												if(rset7.next()) {
													truck_cnt = rset7.getInt(1);
												}*/
												String invMstSql = "select TOTAL_QTY from DLNG_INVOICE_MST where "
														+ " MAPPING_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
														+ " AND INVOICE_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
														+ " AND CONTRACT_TYPE='S' "
														+ " AND TRUCK_ID = (select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset7.getString(1)+"' and STATUS = 'Y')";
												//System.out.println("invMstSql-----"+invMstSql);
												rset8 = stmt8.executeQuery(invMstSql);
												if(rset8.next()) {
													truck_qty+= rset8.getDouble(1);
												}
											}
											
											double nom_qty_mmbtu_sch = Double.parseDouble(rset6.getString(5)=="0"?"":rset6.getString(5));
											double final_qty_mmbtu_sch = nom_qty_mmbtu - truck_qty ; 
											
											double nom_qty_scm1_sch = Double.parseDouble(rset6.getString(6)=="0"?"":rset6.getString(6));
											double nom_qty_scm2_sch = Math.round((truck_qty*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
											double final_qty_scm_sch = nom_qty_scm1 - nom_qty_scm2 ; 
											
											if(Integer.parseInt(rset6.getString(2)) > 1) {
												
												daily_Seller_Nom_Qty_Mmbtu.add(final_qty_mmbtu);
												daily_Seller_Nom_Qty_Scm.add(final_qty_scm);	
											}else {
												
												daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
												daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
											}
											
										
										
										
										total_mmbtu += Double.parseDouble(rset6.getString(5));
										total_scm += Double.parseDouble(rset6.getString(6));
										daily_Sch_Mapping_Id.add(rset6.getString(4)==null?"":rset6.getString(4)); //SB20181229
										daily_Seller_Rev_No.add(rset6.getString(2)==null?"0":rset6.getString(2));
										NomId=temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);
									}
									else
									{
										
										daily_Seller_Gen_Day_With_Rev_No.add("");
										daily_Seller_Gen_Day_Time.add("");
										daily_Seller_Nom_Plant_Seq_No.add("0");
										daily_Seller_Nom_Qty_Mmbtu.add("0");
										daily_Seller_Nom_Qty_Scm.add("0");
										daily_Sch_Mapping_Id.add("");
										daily_Seller_Rev_No.add("0");
									}
								////Check for Invoice generated////////
									int NomIdCnt=0;
									queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID like '"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND CONTRACT_TYPE='S' ";							
//									////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
									rset5 = stmt5.executeQuery(queryString5);
									if(rset5.next())
										NomIdCnt=rset5.getInt(1);
									if(NomIdCnt>0)
										VBuyer_Inv_Flag.add("Y");
									else
										VBuyer_Inv_Flag.add("N");
									
									double AllocQty=0; //SB20200723
									/*queryString = "select SUM(QTY_MMBTU) from DLNG_DAILY_ALLOCATION_DTL where GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ " AND CUSTOMER_CD='"+rset1.getString(1)+"' and SN_REV_NO = '"+rset1.getString(5)+"'" + 
											  " AND SN_NO = '"+rset1.getString(4)+"' and FGSA_NO='"+rset1.getString(2)+"' "
											+ " AND FGSA_REV_NO='"+rset1.getString(3)+"' and MAPPING_ID='"+map_id+"' and PLANT_SEQ_NO='"+rset2.getString(1)+"'"
											+ " AND NOM_REV_NO='"+rset4.getString(2)+"' " ;*/
									
									queryString = "select SUM(ENTRY_TOT_ENE),nvl(max(NOM_REV_NO),0) from DLNG_ALLOC_MST where"
											+ " GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ " AND MAPPING_ID like '"+temp_map_id+"' "
											+ " AND ALLOC_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
											+ " AND NOM_REV_NO='"+rset4.getString(2)+"' AND CONTRACT_TYPE='S' " ;
									
//									//System.out.println("queryString****"+queryString);
									rset8 = stmt7.executeQuery(queryString);
									if(rset8.next()) {
										AllocQty=Double.parseDouble(rset8.getString(1)==null?"0":rset8.getString(1)); //SB20200723
										allocated_qty_mmbtu.add(rset8.getString(1)==null?"0":rset8.getString(1));
									}else {
										allocated_qty_mmbtu.add("0");
									}
//									System.out.println("SchQty-AllocQty--------"+SchQty+"----------"+AllocQty);
									balance_qty_mmbtu.add(nf2.format(AllocQty));
								
								
							////Check for Invoice generated////////
								//System.out.println("multiplying_factor----"+multiplying_factor);
								//System.out.println("ncv_gcv----"+ncv_gcv);
								/*queryString5 = "SELECT nvl(sum(TOTAL_QTY),0) FROM DLNG_INVOICE_MST "
										+ " WHERE PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ " AND MAPPING_ID  like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ " AND  CONTRACT_TYPE='S' ";	
								//System.out.println("queryString5--SN--"+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next()) {
									total_inv_qty_mmbtu.add(rset5.getString(1) == null?"0":rset5.getString(1) );
									double total_inv_mmbtu = Double.parseDouble(rset5.getString(1) == null?"0":rset5.getString(1));
									
									double total_inv_scm = Math.round((total_inv_mmbtu*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
									total_inv_qty_scm.add(total_inv_scm);
									total_inv_qty_mt.add(total_inv_mmbtu/convt_mmbtu_to_mt );
									
								}else {
									total_inv_qty_mmbtu.add("0");
									total_inv_qty_scm.add("0");
									total_inv_qty_mt.add("0");
								}*/
								queryString5 = "SELECT nvl(sum(EXIT_TOT_ENE),0),nvl(max(NOM_REV_NO),0) FROM DLNG_ALLOC_MST "
										+ " WHERE GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ " AND ALLOC_ID  like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ " AND  CONTRACT_TYPE='S' ";	
//								System.out.println("queryString5--SN--"+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next()) {
									total_inv_qty_mmbtu.add(rset5.getString(1) == null?"0":rset5.getString(1) );
									double total_inv_mmbtu = Double.parseDouble(rset5.getString(1) == null?"0":rset5.getString(1));
									
									double total_inv_scm = Math.round((total_inv_mmbtu*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
									total_inv_qty_scm.add(total_inv_scm);
									total_inv_qty_mt.add(total_inv_mmbtu/convt_mmbtu_to_mt );
									max_nom_rev_no.add(rset5.getString(2) == null?"0":rset5.getString(2));
								}else {
									total_inv_qty_mmbtu.add("0");
									total_inv_qty_scm.add("0");
									total_inv_qty_mt.add("0");
									max_nom_rev_no.add("0");
								}
							}
						}
					}
				}// end of sn while loop
					
					/* **********************for LoA Contracts***************************** Hiren_20200428*/
					
					queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
						//SB20200705			+ "AND A.FCC_FLAG='Y' "
							+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO";
//						////System.out.println("STEP-1: LOA: DLNG_LOA_MST: "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						dailyNominaionCnt=0;
						//mappaing_id = customer_cd-tender_no-0-loa_no-loa_rev_no
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4);
						String temp_map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-%-"+rset1.getString(3)+"-%"; //Hiren_20210202
							
							if(!rset1.getString(1).equals("0"))
							{
								queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.TENDER_NO="+rset1.getString(2)+" " +
										   "AND A.LOA_NO="+rset1.getString(3)+" AND LOA_REV_NO = '"+rset1.getString(4)+"'";
//								////System.out.println("STEP-2: LoA Plant No****"+queryString2);
								rset2 = stmt2.executeQuery(queryString2);
								while(rset2.next())
								{
									daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
									
									queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
												   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
												   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
												   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
												   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
									rset3 = stmt3.executeQuery(queryString3);
									if(rset3.next())
									{
										daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
									}
									else
									{
										daily_Seller_Nom_Plant_Abbr.add("");
									}
									
									
									queryString4 = "SELECT count(*) FROM DLNG_DAILY_NOM WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
											+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='L') "
											+ " AND CONTRACT_TYPE='L' ";
									////System.out.println("LoA Nom Count****"+queryString4);
									rset4 = stmt4.executeQuery(queryString4);
									if(rset4.next())
									{
										if(rset4.getInt(1) > 0) {
											dailyNominaionCnt++;
										}else {
											dailyNominaionCnt=0;
										}
										
									}else {
										dailyNominaionCnt = 0;
									}
									
									if(dailyNominaionCnt > 0) { //Hiren_20201015 if daily Buyer nomination done.... 
										
										queryString4 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
												+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),REV_NO FROM DLNG_DAILY_NOM WHERE "
												+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
												+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
												+ "AND REV_NO = ("
												+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
												+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
												+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='L')"
												+ " AND CONTRACT_TYPE='L' ";		
										////System.out.println("daily Nom Details****"+queryString4);
										rset4 = stmt4.executeQuery(queryString4);
										
									}else { //Hiren_20201015 if weekly Buyer nomination is done....
										
										if(!callFlag.equals("TRUCK_LOAD")) {	
											queryString4 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
													+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),REV_NO FROM DLNG_WEEKLY_NOM WHERE "
													+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
													+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
													+ "AND REV_NO = ("
													+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
													+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
													+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='L')"
													+ " AND CONTRACT_TYPE='L' ";
											
											////System.out.println("weekly Nom Details***LoA*"+queryString4);
											rset4 = stmt4.executeQuery(queryString4);
										}
									}
								
									if(rset4.next())
									{
										if(dailyNominaionCnt == 0) {
											VweeklyNomFlag.add("Y");
										}else {
											VweeklyNomFlag.add("N");
										}
										
										daily_Buyer_Gen_Day_With_Rev_No.add(rset4.getString(2)==null?"0":rset4.getString(2));
										daily_Buyer_Rev_No.add(rset4.getString(2)==null?"":rset4.getString(2));
										daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
										daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
										
										/*Hiren_20210319 *** if nominated truck's invoice generated*/
										double truck_qty = 0;
											String checkSQl="select truck_nm from DLNG_DAILY_TRUCK_NOM_DTL "
													+ " where NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
													+ " AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
													+ " AND CONTRACT_TYPE='L' "
													+ " AND REV_NO = '"+rset4.getString(7)+"' ";
											//System.out.println("checkSQl----"+checkSQl);
											rset6 = stmt6.executeQuery(checkSQl);
											while (rset6.next()) {
												nom_cnt++;
												String invMstSql = "select TOTAL_QTY from DLNG_INVOICE_MST where "
														+ " MAPPING_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
														+ " AND INVOICE_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
														+ " AND CONTRACT_TYPE='S' "
														+ " AND TRUCK_ID = (select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset6.getString(1)+"' and STATUS = 'Y')";
												//System.out.println("invMstSql-----"+invMstSql);
												rset7 = stmt7.executeQuery(invMstSql);
												if(rset7.next()) {
													inv_cnt++;
													truck_qty+= rset7.getDouble(1);
												}
											}
										
											double nom_qty_mmbtu = Double.parseDouble(rset4.getString(5)=="0"?"":rset4.getString(5));
											double final_qty_mmbtu = nom_qty_mmbtu - truck_qty ; 
											
											double nom_qty_scm1 = Double.parseDouble(rset4.getString(6)=="0"?"":rset4.getString(6));
											double nom_qty_scm2 = Math.round((truck_qty*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
											double final_qty_scm = nom_qty_scm1 - nom_qty_scm2 ; 
											
											if(Integer.parseInt(rset4.getString(7)+"") > 1) {
												daily_Buyer_Nom_Qty_Mmbtu.add(final_qty_mmbtu);
												daily_Buyer_Nom_Qty_Scm.add(final_qty_scm);
											}else {
												
												daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
												daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
											}
											//System.out.println(inv_cnt+"------here- LoA-----"+nom_cnt);
											if(nom_cnt == inv_cnt && nom_cnt > 0) {
												all_inv_gen_flg.add("Y");
											}else {
												all_inv_gen_flg.add("N");
											}	
										/////////////////////////	
//										daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
//										daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
										total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
										
										queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
													   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
													   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
													   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
													   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
//											////System.out.println("daily_Buyer_Nom_Plant_Seq_Abbr from line no 802 "+daily_Buyer_Nom_Plant_Seq_Abbr);
										}
										else
										{
											daily_Buyer_Nom_Plant_Seq_Abbr.add("");
										}
										///////////////////////////TAX Applicable///////////////////////////////////
										String tax_Structure_Dtl="";
										String tax_struct_cd="";
										
										queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
													  "A.customer_cd="+rset1.getString(1)+" AND " +
													  "A.plant_seq_no="+rset2.getString(1)+" AND " +
											 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
											 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
											 		  "B.tax_struct_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//										////System.out.println("DLNG: TAX: FMS7_CUSTOMER_TAX_STRUCT_DTL: "+queryString);
										rset3 = stmt3.executeQuery(queryString);
										if(rset3.next())
										{
											tax_Structure_Dtl = rset3.getString(1)==null?"":rset3.getString(1);
											tax_struct_cd = rset3.getString(2)==null?"0":rset3.getString(2);
										}
										else
										{
											tax_Structure_Dtl = "";
											tax_struct_cd = "0";
										}
										String tax_cd="";
										String tax_nm="";
										int cnt=0;
										queryString = "SELECT NVL(A.tax_code,'0'),B.sht_nm, NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A,FMS7_TAX_MST B WHERE " +
										  "A.tax_str_cd="+tax_struct_cd+" AND B.tax_code=a.tax_code and " +
					 					  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
					 					  "B.app_date<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) and a.tax_on='1' ORDER BY A.tax_code";
										rset3=stmt3.executeQuery(queryString);
										if(rset3.next())
								 		{
											if(cnt==0)
											{
												tax_cd += rset3.getString(1);
												tax_nm += rset3.getString(2);
											}
											else
											{
												tax_cd +=","+ rset3.getString(1);
												tax_nm +=","+rset3.getString(2);
											}
											cnt++;
								 		}
										daily_tax_struct_dtl.add(tax_Structure_Dtl);
										//////////////////////////
										daily_Seller_Nom_Mapping_Id.add(map_id);
										daily_Seller_Nom_Fgsa_No.add(rset1.getString(2));
										daily_Seller_Nom_Fgsa_Rev_No.add("0"); //SB20200706
								//SB20200706		daily_Seller_Nom_Sn_No.add(rset1.getString(4));
								//SB20200706		daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(5));
										daily_Seller_Nom_Sn_No.add(rset1.getString(3));
										daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(4));
										
										daily_Seller_Nom_Cd.add(rset1.getString(1));
//										////System.out.println("daily_Seller_Nom_Cd from line no 815 "+daily_Seller_Nom_Cd);
										
										daily_Seller_Nom_Type.add("L");
										daily_Seller_Nom_Contract_Type.add("LoA");
										
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(5))));	
										total_dcq += Double.parseDouble(rset1.getString(5));
										daily_Seller_regas_cargo_boe_no.add("");
										daily_Seller_regas_cargo_boe_dt.add("");
										daily_Seller_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(9))));
										
										queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
									   			   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
									   			   		+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
										rset5 = stmt5.executeQuery(queryString5);
										if(rset5.next())
										{
											daily_Seller_Nom_Abbr.add(rset5.getString(1));
//											////System.out.println("daily_Seller_Nom_Abbr from line no 833 "+daily_Seller_Nom_Abbr);
										}
										else
										{
											daily_Seller_Nom_Abbr.add("");
										}
										String NomId="";
										double SchQty=0; //SB20200723
										
										if(dailyNominaionCnt > 0) {
											
											queryString6 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''),NVL(SCH_ID,''),"
													+ "NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_DAILY_SCH WHERE "
													+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
													+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
													+ "AND REV_NO = ("
													+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
													+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
													+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='L') "
													+ " AND CONTRACT_TYPE='L' ";
											////System.out.println("daily sch Details****"+queryString4);
											rset6 = stmt6.executeQuery(queryString6);
										}else {
											if(!callFlag.equals("TRUCK_LOAD")) {	
												queryString6 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''),NVL(SCH_ID,''),"
														+ "NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_SCH WHERE "
														+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
														+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
														+ "AND REV_NO = ("
														+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
														+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
														+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='L')"
														+ " AND CONTRACT_TYPE='L' ";
												
												////System.out.println("weekly sch Details**LoA**"+queryString4);
												rset6 = stmt6.executeQuery(queryString6);
											}
										}
										
//										////System.out.println("STEP-3: DLNG_DAILY_SCH: "+queryString6);
										
										if(rset6.next())
										{
											daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
											daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
											daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
											SchQty = Double.parseDouble(rset6.getString(5)==null?"0":rset6.getString(5)); //SB20200723
											/*daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
											daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));*/
											//System.out.println("SchQty---------"+SchQty);
											/*Hiren_20210319 *** if nominated truck's invoice generated*/
												double truck_qty_sch = 0;
												String checkSQlSch="select truck_nm from DLNG_DAILY_TRUCK_SCH_DTL "
														+ " where SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
														+ " AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
														+ " AND CONTRACT_TYPE='L' "
														+ " AND REV_NO = '"+rset6.getString(2)+"' ";
												//System.out.println("checkSQl----"+checkSQlSch);
												rset7 = stmt7.executeQuery(checkSQlSch);
												while (rset7.next()) {
													
													String invMstSql = "select TOTAL_QTY from DLNG_INVOICE_MST where "
															+ " MAPPING_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
															+ " AND INVOICE_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
															+ " AND CONTRACT_TYPE='L' "
															+ " AND TRUCK_ID = (select truck_id from DLNG_TANK_TRUCK_MST where TRUCK_NM = '"+rset7.getString(1)+"' and STATUS = 'Y')";
													//System.out.println("invMstSql-----"+invMstSql);
													rset8 = stmt8.executeQuery(invMstSql);
													if(rset8.next()) {
														truck_qty+= rset8.getDouble(1);
													}
												}
												
												double nom_qty_mmbtu_sch = Double.parseDouble(rset6.getString(5)=="0"?"":rset6.getString(5));
												double final_qty_mmbtu_sch = nom_qty_mmbtu - truck_qty ; 
												
												double nom_qty_scm1_sch = Double.parseDouble(rset6.getString(6)=="0"?"":rset6.getString(6));
												double nom_qty_scm2_sch = Math.round((truck_qty*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
												double final_qty_scm_sch = nom_qty_scm1 - nom_qty_scm2 ; 
												
												if(Integer.parseInt(rset6.getString(2)) > 1) {
													
													daily_Seller_Nom_Qty_Mmbtu.add(final_qty_mmbtu);
													daily_Seller_Nom_Qty_Scm.add(final_qty_scm);	
												}else {
													
													daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
													daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
												}
												
											total_mmbtu += Double.parseDouble(rset6.getString(5));
											total_scm += Double.parseDouble(rset6.getString(6));
											daily_Sch_Mapping_Id.add(rset6.getString(4)==null?"":rset6.getString(4)); //SB20181229
											daily_Seller_Rev_No.add(rset6.getString(2)==null?"0":rset6.getString(2));
											NomId=temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);
										}
										else
										{
											daily_Seller_Gen_Day_With_Rev_No.add("");
											daily_Seller_Gen_Day_Time.add("");
											daily_Seller_Nom_Plant_Seq_No.add("0");
											daily_Seller_Nom_Qty_Mmbtu.add("0");
											daily_Seller_Nom_Qty_Scm.add("0");
											daily_Sch_Mapping_Id.add("");
											daily_Seller_Rev_No.add("0");
										}
									////Check for Invoice generated////////
										int NomIdCnt=0;
										queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID like '"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')  AND CONTRACT_TYPE='L' ";							
//										////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
										rset5 = stmt5.executeQuery(queryString5);
										if(rset5.next())
											NomIdCnt=rset5.getInt(1);
										if(NomIdCnt>0)
											VBuyer_Inv_Flag.add("Y");
										else
											VBuyer_Inv_Flag.add("N");
										
										double AllocQty=0; //SB20200723
										queryString = "select SUM(ENTRY_TOT_ENE) from DLNG_ALLOC_MST where"
												+ " GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
												+ " AND MAPPING_ID like '"+temp_map_id+"' "
												+ " AND ALLOC_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
												+ " AND NOM_REV_NO='"+rset4.getString(2)+"'  AND CONTRACT_TYPE='L' " ;
//										////System.out.println("STEP-4: DLNG_ALLOC_MST: "+queryString6);
//										////System.out.println("queryString****"+queryString);
										rset8 = stmt7.executeQuery(queryString);
										if(rset8.next()) {
											AllocQty=Double.parseDouble(rset8.getString(1)==null?"0":rset8.getString(1)); //SB20200723
											allocated_qty_mmbtu.add(rset8.getString(1)==null?"0":rset8.getString(1));
										}else {
											allocated_qty_mmbtu.add("0");
										}
										balance_qty_mmbtu.add(nf2.format(AllocQty)); //SB20200723
										
									////Check for Invoice generated////////
										//System.out.println("multiplying_factor----"+multiplying_factor);
										//System.out.println("ncv_gcv----"+ncv_gcv);
										/*queryString5 = "SELECT nvl(sum(TOTAL_QTY),0) FROM DLNG_INVOICE_MST "
												+ " WHERE PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
												+ " AND MAPPING_ID  like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ " AND  CONTRACT_TYPE='L' ";	
										//System.out.println("queryString5--LoA--"+queryString5);
										rset5 = stmt5.executeQuery(queryString5);
										if(rset5.next()) {
											total_inv_qty_mmbtu.add(rset5.getString(1) == null?"0":rset5.getString(1) );
											double total_inv_mmbtu = Double.parseDouble(rset5.getString(1) == null?"0":rset5.getString(1));
											
											double total_inv_scm = Math.round((total_inv_mmbtu*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
											total_inv_qty_scm.add(total_inv_scm);
											total_inv_qty_mt.add(total_inv_mmbtu/convt_mmbtu_to_mt );
											
										}else {
											total_inv_qty_mmbtu.add("0");
											total_inv_qty_scm.add("0");
											total_inv_qty_mt.add("0");
										}*/
										
										queryString5 = "SELECT nvl(sum(EXIT_TOT_ENE),0),nvl(max(NOM_REV_NO),0) FROM DLNG_ALLOC_MST "
												+ " WHERE GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
												+ " AND ALLOC_ID  like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ " AND  CONTRACT_TYPE='S' ";	
//										System.out.println("queryString5--SN--"+queryString5);
										rset5 = stmt5.executeQuery(queryString5);
										if(rset5.next()) {
											total_inv_qty_mmbtu.add(rset5.getString(1) == null?"0":rset5.getString(1) );
											double total_inv_mmbtu = Double.parseDouble(rset5.getString(1) == null?"0":rset5.getString(1));
											
											double total_inv_scm = Math.round((total_inv_mmbtu*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
											total_inv_qty_scm.add(total_inv_scm);
											total_inv_qty_mt.add(total_inv_mmbtu/convt_mmbtu_to_mt );
											max_nom_rev_no.add(rset5.getString(2) == null?"0":rset5.getString(2));
										}else {
											total_inv_qty_mmbtu.add("0");
											total_inv_qty_scm.add("0");
											total_inv_qty_mt.add("0");
											max_nom_rev_no.add("0");
										}
									}
								}
							}
						}// end of LoA while loop
					
					
					
				final_daily_buyer_mmbtu += total_buyer_mmbtu;
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
				
				if(final_daily_buyer_mmbtu>0)
				{
					daily_Total_Mmbtu = nf.format(final_daily_buyer_mmbtu);
				}
				else
				{
					daily_Total_Mmbtu = "";
				}
				
				if(final_daily_mmbtu>0)
				{
					daily_Total_Mmbtu_Seller_Nom = nf.format(final_daily_mmbtu);
				}
				else
				{
					daily_Total_Mmbtu_Seller_Nom = "";
				}
				
				if(final_daily_scm>0)
				{
					daily_Total_Scm_Seller_Nom = nf.format(final_daily_scm);
				}
				else
				{
					daily_Total_Scm_Seller_Nom = "";
				}
				
				daily_Total_Dcq_Seller_Nom = nf.format(final_daily_dcq);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		Vector tank_truck_id = new Vector(); 
		Vector tank_truck_nm = new Vector();
		Vector tank_truck_capacity = new Vector();
		Vector tank_truck_capacityM3 = new Vector();
		Vector tank_truck_trans_cd = new Vector();
		public Vector getTank_truck_trans_cd() {
			return tank_truck_trans_cd;
		}

		Map no_truck_required = new HashMap();
		int total_truck_avail = 0;
		double single_truck_capacity = 0; double truck_load_capacity = 0;
		Map <String, String> truck_load_start_day = new HashMap <String, String> ();
		Map <String, String> truck_load_start_tm = new HashMap <String, String> ();
		Map <String, String> truck_load_end_tm = new HashMap <String, String> ();
		Map <String, String> truck_load_end_day = new HashMap <String, String> ();
		Map <String, String> truck_loaded_vol = new HashMap <String, String> ();
		Map <String, String> truck_loaded_ene = new HashMap <String, String> (); //SB20181219
		Map <String, String> truck_loaded_scm = new HashMap <String, String> (); //ha20200221
		Map <String, String> truck_unloaded_vol = new HashMap <String, String> (); //SB20181219
		Map <String, String> truck_unloaded_ene = new HashMap <String, String> (); //SB20181219
		Map <String, String> truck_unloaded_scm = new HashMap <String, String> (); //ha20200221
		Map <String, String> gcv_per_mmbtu = new HashMap <String, String> (); //ha20200221
		Map <String, String> truck_status = new HashMap <String, String> (); //SB20181219
		Map <String, String> truck_flag = new HashMap <String, String> (); //HA20190101
		Map <String, String> last_eff_dt = new HashMap <String, String> (); //HA20190101
		
		
		Vector truckCnt = new Vector();//HA20200207
		Vector sch_truck_vol= new Vector();//HA20200210
		Vector sch_truck_scm= new Vector();//HA20200221
		Vector allocated_truck = new Vector();//Hiren_20200410
		
		Vector vTempMapId = new Vector(); 
		Vector vTempSchId = new Vector(); 
		Vector vTotal_Sch_mmbtu = new Vector(); 
		Vector vTotal_loaded_mmbtu = new Vector(); 
		Vector rev_bal_qty = new Vector(); 
		
		public void fetchTruckLoadingDetails() throws Exception {
			try {
				//Fetching Tank Truck Details ...
				/* *** customer code 100 (hardcoded) is to get own truck*/
				
				//System.out.println("daily_Sch_Mapping_Id***"+daily_Sch_Mapping_Id.size()+"------"+daily_Seller_Nom_Cd.size());
				for (int i = 0 ; i < daily_Seller_Nom_Cd.size() ; i++) {
					double tot_sch_mmbtu = 0 ;
					double tot_loaded_mmbtu = 0 ;
					int cnt=0;
					
					String tempMap [] = daily_Seller_Nom_Mapping_Id.elementAt(i).toString().split("-");
					String temp_map_id = tempMap[0]+"-"+tempMap[1]+"-%-"+tempMap[3]+"-%";
					String temp_sch_id="%-%-%-%-%-%-%";
					//System.out.println("daily_Sch_Mapping_Id.elementAt(i)------------------"+daily_Sch_Mapping_Id.elementAt(i));
					if(!daily_Sch_Mapping_Id.elementAt(i).equals("")) {
						String tempNom [] = daily_Sch_Mapping_Id.elementAt(i).toString().split("-");
						temp_sch_id = tempNom[0]+"-"+tempNom[1]+"-%-"+tempNom[3]+"-%-"+tempNom[5]+"-"+tempNom[6];
					}else {
						
					}
					
					//System.out.println("daily_Seller_Rev_No-------"+daily_Seller_Rev_No.elementAt(i));
				/*	queryString = "SELECT TRUCK_ID,TRUCK_NM,NVL(TANK_VOL_M3,'0'),TRANS_CD FROM DLNG_TANK_TRUCK_MST "
							+ " WHERE EFF_DT <= TO_DATE('"+gas_date+"','DD/MM/YYYY') AND CUSTOMER_CD in ('"+daily_Seller_Nom_Cd.elementAt(i)+"','100') AND DEL_FLAG='Y' AND STATUS='Y' ORDER BY TRUCK_ID";
					////System.out.println("Fetching distinct Truck Details..."+queryString);*/
					queryString = "SELECT TRUCK_ID,TRUCK_NM,NVL(TANK_VOL_M3,'0'),TRANS_CD, LOAD_CAP FROM DLNG_TANK_TRUCK_MST "+ 
					"WHERE EFF_DT <= TO_DATE('"+gas_date+"','DD/MM/YYYY')  AND STATUS='Y' "+
					" AND TRUCK_NM IN (SELECT TRUCK_NM FROM DLNG_DAILY_TRUCK_SCH_DTL WHERE SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')  "
					+ " AND SCH_ID='"+daily_Sch_Mapping_Id.elementAt(i)+"' )";
					
					//System.out.println("SCHEDULED TRUCK DTL: "+queryString);
					rset = stmt.executeQuery(queryString);
					while(rset.next()) {
						
						String query = "SELECT TRUCK_VOL,TRUCK_ENE,NEXT_AVAIL_DAYS FROM DLNG_DAILY_TRUCK_SCH_DTL WHERE "
								+ " SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
								+ " AND MAPPING_ID like '"+temp_map_id+"' "
								+ " AND SCH_ID like '"+temp_sch_id+"' "
								+ " AND REV_NO='"+daily_Seller_Rev_No.elementAt(i)+"'"
								+ " AND TRUCK_NM='"+rset.getString(2)+"'";
//						//System.out.println("query***"+query);
						rset4 = stmt4.executeQuery(query);
						if(rset4.next()){
							
							cnt++;
							tank_truck_id.add(rset.getString(1));
							tank_truck_nm.add(rset.getString(2));
							tank_truck_trans_cd.add(rset.getString(4));
							truck_load_capacity = rset.getDouble(3)*(rset.getDouble(5)/100); //SB20200730
							tank_truck_capacity.add(nf.format(truck_load_capacity*conversion_factor_from_m3_to_mmbtu));
							tank_truck_capacityM3.add(nf.format(truck_load_capacity)); //SB20181219
							
							single_truck_capacity = rset.getDouble(3)*conversion_factor_from_m3_to_mmbtu; //Assuming that all trucks have same capacity
							
							String allocData="select LOADED_ENE,LOADED_ENE from DLNG_TANK_VOL_DTL where "
									+ " EFF_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
									+ " AND SCH_ID like '"+temp_sch_id+"' "
									+ " and TRN_CD=(select TRUCK_ID from DLNG_TANK_TRUCK_MST where TRUCK_NM='"+rset.getString(2)+"' AND Status = 'Y')";
//							//System.out.println("allocData****"+allocData);//to filter loaded trucks
							rset3 = stmt2.executeQuery(allocData);
							if(rset3.next()) {
								tot_loaded_mmbtu+=Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1));
								tot_sch_mmbtu+=Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1));
								allocated_truck.add("Y");
								sch_truck_vol.add(rset3.getString(1)==null?"":rset3.getString(1));
								sch_truck_scm.add(rset3.getString(2)==null?"":rset3.getString(2));//HA20200221
								
								VLoadedNxt_avail_days.add(rset4.getString(3)==null?"":rset4.getString(3));
								
							}else {
								tot_sch_mmbtu+=Double.parseDouble(rset4.getString(1)==null?"":rset4.getString(1));
								sch_truck_vol.add(rset4.getString(1)==null?"":rset4.getString(1));
								sch_truck_scm.add(rset4.getString(2)==null?"":rset4.getString(2));//HA20200221
								VLoadedNxt_avail_days.add(rset4.getString(3)==null?"":rset4.getString(3)); //HA20201103
								allocated_truck.add("N");
							}
						}else {
							
							double invCnt = 0;
							queryString5 = "SELECT nvl(TOTAL_QTY,0) FROM DLNG_INVOICE_MST "
									+ " WHERE INVOICE_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
									+ " AND MAPPING_ID  like '"+temp_sch_id+"' "
									+ " AND  TRUCK_ID = '"+rset.getString(1)+"' ";							
//							//System.out.println("TLU --Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next()) {
								invCnt = Double.parseDouble(rset5.getString(1)==null?"0":rset5.getString(1));
								tot_sch_mmbtu+=invCnt;
								tot_loaded_mmbtu+=invCnt;
							} 
							if(invCnt > 0) {
								
								sch_truck_vol.add("");
								sch_truck_scm.add("");
								allocated_truck.add("Y");
								VLoadedNxt_avail_days.add("");
								
								cnt++;
								tank_truck_id.add(rset.getString(1));
								tank_truck_nm.add(rset.getString(2));
								tank_truck_trans_cd.add(rset.getString(4));
								truck_load_capacity = rset.getDouble(3)*(rset.getDouble(5)/100); //SB20200730
								
							//SB	tank_truck_capacity.add(nf.format(rset.getDouble(3)*conversion_factor_from_m3_to_mmbtu));
								tank_truck_capacity.add(nf.format(truck_load_capacity*conversion_factor_from_m3_to_mmbtu));
							//SB	tank_truck_capacityM3.add(nf.format(rset.getDouble(3))); //SB20181219
								tank_truck_capacityM3.add(nf.format(truck_load_capacity)); //SB20181219
								
								single_truck_capacity = rset.getDouble(3)*conversion_factor_from_m3_to_mmbtu; //Assuming that all trucks have same capacity
							}
						}
					}
					vTempMapId.add(temp_map_id);
					vTempSchId.add(temp_sch_id);
					truckCnt.add(cnt);
					vTotal_Sch_mmbtu.add(tot_sch_mmbtu);
					vTotal_loaded_mmbtu.add(tot_loaded_mmbtu);
				}
//				////System.out.println(allocated_truck.size()+"*****"+tank_truck_id.size());
				total_truck_avail = tank_truck_id.size();
//				////System.out.println("single_truck_capacity***"+single_truck_capacity);
				for(int i=0;i<daily_Seller_Nom_Plant_Seq_No.size();i++) {
					double total_sch_mmbtu = 0 ;
					double scheduled_qty = Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(i)+"");
					
					 int no_of_required_trucks = Integer.parseInt(""+Math.round(scheduled_qty/single_truck_capacity));
					
					 //HA20181224 due to round of gives 0 while no_of_required_trucks value is < 0.5
					 if(scheduled_qty<single_truck_capacity && scheduled_qty>0 && no_of_required_trucks<1){
						 no_of_required_trucks=1;
					 }	
					/* ********************************************* */
//					////System.out.println(""+daily_Seller_Nom_Mapping_Id.elementAt(i)+"==="+daily_Seller_Nom_Sn_No.elementAt(i)+"=="+daily_Seller_Nom_Plant_Seq_No.elementAt(i));
					String key = daily_Seller_Nom_Mapping_Id.elementAt(i)+"-"+daily_Seller_Nom_Sn_No.elementAt(i)+"-"+daily_Seller_Nom_Plant_Seq_No.elementAt(i);
					no_truck_required.put(key, no_of_required_trucks);
					
					String TruckStatus="";
					//Fetch already allocated data....
/*					queryString = "SELECT TO_CHAR(LOAD_START_TM,'DD/MM/YYYY'),TO_CHAR(LOAD_START_TM,'HH24:MI'),TO_CHAR(LOAD_END_TM,'DD/MM/YYYY')," + 
							"TO_CHAR(LOAD_END_TM,'HH24:MI'),NVL(LOADED_VOL,'0'),A.SUP_TRN_CD,NVL(LOADED_ENE,'0'),NVL(UNLOADED_VOL,'0'),NVL(UNLOADED_ENE,'0'), NVL(FLAG,'Y') " +
							" FROM DLNG_ALLOC_MST A,DLNG_TANK_VOL_DTL B WHERE "
							+ " A.MAPPING_ID='"+daily_Seller_Nom_Mapping_Id.elementAt(i)+"' AND A.ALLOC_ID='"+key+"' AND "
							+ "A.PARTY_CD='"+daily_Seller_Nom_Cd.elementAt(i)+"' AND A.ALLOC_DT=B.EFF_DT " +
							"AND (B.FLAG!='E') AND EFF_DT <= TO_DATE('"+gas_date+"','DD/MM/YYYY')";*/
					
					queryString = "SELECT TO_CHAR(LOAD_START_TM,'DD/MM/YYYY'),TO_CHAR(LOAD_START_TM,'HH24:MI'),TO_CHAR(LOAD_END_TM,'DD/MM/YYYY')," + 
							"TO_CHAR(LOAD_END_TM,'HH24:MI'),NVL(LOADED_VOL,'0'),TRN_CD,NVL(LOADED_ENE,'0'),NVL(UNLOADED_VOL,'0'),NVL(UNLOADED_ENE,'0'), NVL(FLAG,'Y'),NVL(LOADED_SCM,'0'),NVL(UNLOADED_SCM,'0'),NVL(GCV_PER_MMBTU,0) " +
							" FROM DLNG_TANK_VOL_DTL  WHERE "
							+ "  SCH_ID like '"+vTempSchId.elementAt(i)+"'  "
//							+ " AND (FLAG!='E')"
							+ " AND EFF_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')"; //HA20190101
//					//System.out.println("Fetching Truck Load Details: "+queryString);
					rset = stmt.executeQuery(queryString);
					while(rset.next()) {
						truck_load_start_day.put(key+"-"+rset.getString(6), rset.getString(1));
//						////System.out.println("key**rset.getString(6)****"+key+"-"+rset.getString(6));
						truck_load_start_tm.put(key+"-"+rset.getString(6), rset.getString(2));
						truck_load_end_tm.put(key+"-"+rset.getString(6), rset.getString(4));
						truck_load_end_day.put(key+"-"+rset.getString(6), rset.getString(3));
						truck_loaded_vol.put(key+"-"+rset.getString(6), rset.getString(5)==null?"0":nf.format(Double.parseDouble(rset.getString(5))));
						truck_loaded_ene.put(key+"-"+rset.getString(6), rset.getString(7)==null?"0":nf.format(Double.parseDouble(rset.getString(7))));
						truck_unloaded_vol.put(key+"-"+rset.getString(6), rset.getString(8)==null?"":rset.getString(8));
						truck_unloaded_ene.put(key+"-"+rset.getString(6), rset.getString(9)==null?"":rset.getString(9));
						TruckStatus = rset.getString(10)==null?"":rset.getString(10);
						truck_loaded_scm.put(key+"-"+rset.getString(6), rset.getString(11)==null?"":rset.getString(11));
						truck_unloaded_scm.put(key+"-"+rset.getString(6), rset.getString(12)==null?"":rset.getString(12));
						gcv_per_mmbtu.put(key+"-"+rset.getString(6), rset.getString(13)==null?"":rset.getString(13));
						////System.out.println(TruckStatus+" :STATUS: "+rset.getString(10));
						if(TruckStatus.trim().equals("Y"))
							TruckStatus="LOADING";
						else if(TruckStatus.equals("T"))
							TruckStatus="TRANSIT";
						else if(TruckStatus.equals("N"))
							TruckStatus="UNLOADED";
						else if(TruckStatus.equals("E"))
							TruckStatus="EMPTY";
						
						truck_status.put(key+"-"+rset.getString(6), TruckStatus);
					}

					//HA20190101 to check TRUCK previous Status
					for(int j=0;j<tank_truck_id.size();j++){
						
						String prevTruckData="select flag,TRN_CD,TO_CHAR(EFF_DT,'dd/mm/yyyy') from DLNG_tank_vol_dtl where seq_no=(select max(seq_no) from DLNG_tank_vol_dtl "
							+ "	where trn_cd='"+tank_truck_id.elementAt(j)+"' and eff_dt<TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
//						////System.out.println("prevTruckData****"+prevTruckData);
						rset=stmt.executeQuery(prevTruckData);
						if(rset.next()){
							truck_flag.put(key+"-"+rset.getString(2), rset.getString(1));
							last_eff_dt.put(key+"-"+rset.getString(2), rset.getString(3));
						}
					}
					//System.out.println("rev_bal_qty-----"+vTotal_Sch_mmbtu.elementAt(i)+"----------"+vTotal_loaded_mmbtu.elementAt(i));
					rev_bal_qty.add(Double.parseDouble(vTotal_Sch_mmbtu.elementAt(i)+"") - Double.parseDouble(vTotal_loaded_mmbtu.elementAt(i)+""));
				}
				//System.out.println("rev_bal_qty-----"+rev_bal_qty);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		
		
		public Vector getAllocated_truck() {
			return allocated_truck;
		}

		Vector prevTruckNomDt = new Vector();
		Vector prevTruckNomRevNo = new Vector();
		Vector prevTruckNm = new Vector();
		Vector prevNomID = new Vector();
		Vector prevCust_Nm = new Vector();
		Vector prevSn = new Vector();
		Vector prevContTyp = new Vector();
		Vector prevPlant = new Vector();
		Vector vNotNomi = new Vector();
		Vector Vtruck_Inv_Flag = new Vector();
		Vector Vtruck_Inv_qty = new Vector();
		Vector Vtruck_Inv_qty_mt = new Vector();
		Vector Vtruck_alloc_flag = new Vector();
		double total_all_inv_qty = 0;
		double total_all_inv_qty_scm = 0;
		String ncv_gcv = "9802.8";
		double multiplying_factor = 0.252*1000000;
		String tot_inv_mmbtu = "0";
		String tot_inv_scm = "0";
		String tot_inv_mt = "0";
		double convt_mmbtu_to_mt = 0;
		double total_nom_qty = 0 ;
		double total_truck_inv_qty = 0 ;
		String balance_adv_amt = "0";
		double sec_amt = 0;
		String inv_gen_flag = "N";
		Vector Vtruck_alloc_mmbtu = new Vector();
		Vector Vtruck_alloc_scm = new Vector();
		Vector Vtruck_alloc_mt = new Vector();
		String Sales_Rate = "0";
		String exg_rate = "0";
		String tax_rate = "0";
		String security= "Linked Securities : ";
		@SuppressWarnings("unchecked")
		private void fetchAvailableTruckDetails()throws SQLException,IOException {
			/*if(selSchedulCust.equals("")) {selCust_id = selSchedulCust;}*/
			String fetchTruckNomData = "";
			total_all_inv_qty = 0;
			total_all_inv_qty_scm = 0;
			sec_amt = 0;
			tot_inv_mmbtu = "0";
			tot_inv_scm = "0";
			tot_inv_mt = "0";
			total_nom_qty = 0 ;
			total_truck_inv_qty = 0 ;
			balance_adv_amt = "0" ;
			Sales_Rate = "0";
			exg_rate = "0";
			tax_rate = "0";
			
			try {
				String selCust_idForTruck=selCust_id; //SB20200801
				///if(selDelvBase.equals("D")) //SB20200801
				///	selCust_idForTruck="100";
				//String selTransForTruck="%"; //SB20200806
				double truck_load_capacity =0;
//				System.out.println("buyer_mapping_id**********"+buyer_mapping_id+"***Cont"+selContract_Type);
				String tempMap [] = buyer_mapping_id.split("-");
				String temp_map_id =tempMap[0]+"-"+tempMap[1]+"-%-"+tempMap[3]+"-%";
				String tempNom [] = nomId.split("-");
				String temp_nom_id = tempNom[0]+"-"+tempNom[1]+"-%-"+tempNom[3]+"-%-"+tempNom[5]+"-"+tempNom[6];
				
				Sales_Rate = fetchSalesRate(tempMap[0],tempMap[1],tempNom[3],selContract_Type,gas_date,tempNom[2],tempNom[4]);
				exg_rate = fetchExgRate(tempMap[0],tempMap[1],tempNom[3],selContract_Type,gas_date);
				tax_rate = fetchTaxRate(tempMap[0],gas_date,tempNom[6]);
				String dr_cr_amt = fetchDrCrAmt(tempMap[0],tempMap[1],tempNom[3],selContract_Type,gas_date,tempNom[2],tempNom[4]);
				
				
				String dr_amt = "0";
				String cr_amt = "0";
				
				if(dr_cr_amt.contains("@")) {
					String tempArr[] = dr_cr_amt.split("@");
					dr_amt = tempArr[0];
					cr_amt = tempArr[1];
				}
				//System.out.println("Sales_Rate--------"+Sales_Rate+"***exg_rate--------"+exg_rate+"********tax_rate------"+tax_rate+"******dr_cr_amt----"+dr_cr_amt);
				int cnt = 0;
				int alloc_cnt = 0;
				
				//EFF_DT = TO_DATE('"+nomDt+"','DD/MM/YYYY') Query Change By SUJIT05MARCH2020
				queryString7 = "SELECT  TRUCK_ID,TRUCK_NM,CUSTOMER_CD,A.TANK_VOL_M3,TANK_VOL_TON, A.TRANS_CD, LOAD_CAP, NEXT_LOAD_DAY,TRANS_NAME FROM DLNG_TANK_TRUCK_MST  A, DLNG_TRANS_MST B "
						+ "WHERE A.TRANS_CD=B.TRANS_CD AND A.STATUS ='Y' AND ( CUSTOMER_CD ='"+selCust_idForTruck+"' OR CUSTOMER_CD ='0' ) AND A.TRANS_CD IN "
						+ "(SELECT TRANS_CD FROM DLNG_CUST_TRANS_DTL WHERE CUST_CD ='"+selCust_idForTruck+"' AND FLAG='Y') "
						+ "ORDER BY TRUCK_ID ";
//				System.out.println("GET Truck List: DLNG_TANK_TRUCK_MST: "+queryString7);				
				rset7 = stmt7.executeQuery(queryString7);
				while (rset7.next()) {
					/////////////////////SB20200731///////////////////////////////
							String next_truck_avail_day="";
							String next_truck_avail_date="";
							String alloc_dt = "";
							String avail_truck="Y";
							
													
						if(avail_truck.equals("Y"))
						{
						/* **************** Filter Nominated Trucks on Next Avail Days basis **************** */
						String nom_dt="";
						String nomAvail ="select to_char(max(nom_dt),'dd/mm/yyyy') "
								+ " from DLNG_DAILY_TRUCK_NOM_DTL where TRUCK_NM='"+rset7.getString(2)+"'"
								+ " and NOM_DT <= TO_DATE('"+gas_date+"','DD/MM/YYYY') ";	
//						//System.out.println("nomAvail***"+nomAvail);
						rset3 = stmt1.executeQuery(nomAvail);
						if(rset3.next()) {
							nom_dt = rset3.getString(1)==null?"":rset3.getString(1);
						}
						
						String next_avail_days="";
						String next_avail_dt="";
						String nom_rev_no="";
						String nom_id="";
						String contTyp="";
						
						//System.out.println("nom_dt*********"+nom_dt);
						if(!nom_dt.equals("")) {
							
							String prevNomId="select NOM_ID,CONTRACT_TYPE from DLNG_DAILY_TRUCK_NOM_DTL where NOM_DT = TO_DATE('"+nom_dt+"','DD/MM/YYYY')"
									+ " and TRUCK_NM='"+rset7.getString(2)+"'";
//							//System.out.println("prevNomId****"+prevNomId);
							rset3 = stmt1.executeQuery(prevNomId);
							if(rset3.next()) {
									
								nom_id = rset3.getString(1)==null?"":rset3.getString(1);
								contTyp = rset3.getString(2)==null?"":rset3.getString(2);
							}
//							//System.out.println("nom_id***"+nom_id);
//							String nomAvailRev ="select max(REV_NO)  from DLNG_DAILY_TRUCK_NOM_DTL where NOM_DT = TO_DATE('"+nom_dt+"','DD/MM/YYYY') and NOM_ID='"+nom_id+"'";
							String nomAvailRev ="select max(REV_NO)  from DLNG_DAILY_NOM where NOM_DT = TO_DATE('"+nom_dt+"','DD/MM/YYYY') and NOM_ID='"+nom_id+"'";
//							//System.out.println("nomAvailRev***"+nomAvailRev);
							rset3 = stmt1.executeQuery(nomAvailRev);
							if(rset3.next()) {
								nom_rev_no = rset3.getString(1)==null?"":rset3.getString(1);
							}
//							//System.out.println("nom_rev_no***"+nom_rev_no);
							if(nom_rev_no!="") {
								
								String temp = fetchAdvDtl(temp_nom_id,selContract_Type,gas_date,rset7.getString(2),rset7.getString(1),nom_rev_no);
								
								String nomAvailDays ="select nvl(NEXT_AVAIL_DAYS,'0')  from DLNG_DAILY_TRUCK_NOM_DTL where "
										+ " NOM_DT = TO_DATE('"+nom_dt+"','DD/MM/YYYY') "
										+ " and TRUCK_NM='"+rset7.getString(2)+"' and REV_NO='"+nom_rev_no+"' ";	
//								//System.out.println("nomAvailDays***"+nomAvailDays);
								rset3 = stmt1.executeQuery(nomAvailDays);
								if(rset3.next()) {
									next_avail_days = rset3.getString(1)==null?"0":rset3.getString(1);
								}else {
									next_avail_days = "0";
								}
								
//								//System.out.println("next_avail_days*******"+next_avail_days);
								String next_avail_dt_sql = "select TO_CHAR(TO_DATE('"+nom_dt+"','DD/MM/YYYY') +  "+next_avail_days+",'DD/MM/YYYY') FROM DUAL";
//								System.out.println("next_avail_dt_sql***"+next_avail_dt_sql);
								rset1 = stmt1.executeQuery(next_avail_dt_sql);
								if(rset1.next()) {
									next_avail_dt = rset1.getString(1)==null?"":rset1.getString(1);
								}
								
								queryString = "select * from dual where TO_DATE('"+nomDt+"','DD/MM/YYYY') >= TO_DATE('"+next_avail_dt+"','DD/MM/YYYY')"; 
								//System.out.println("queryString******: "+queryString);	
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
//									System.out.println("******in if********");
									truckIdAT.add(rset7.getString(1)==null?"":rset7.getString(1));
									truckNameAT.add(rset7.getString(2)==null?"":rset7.getString(2));
									custIDAT.add(rset7.getString(3)==null?"":rset7.getString(3));
									VTruckTransCd.add(rset7.getString(6)==null?"":rset7.getString(6));
									truck_load_capacity = rset7.getDouble(4)*(rset7.getDouble(7)/100); //SB20200730
									tankVolM3AT.add(nf.format(truck_load_capacity));  //SB20200730
									tankVolTonAT.add(nf.format(rset7.getDouble(5)*(rset7.getDouble(7)/100))); 
									
									/*truckLoadedFlag.add("N");
									prevTruckNomDt.add("");
									prevTruckNm.add("");
									prevTruckNomRevNo.add("");
									prevNomID.add("");
									prevContTyp.add("");*/
									String allocCntSql = "select NOM_REV_NO from DLNG_ALLOC_MST where ALLOC_DT = TO_DATE('"+nomDt+"','DD/MM/YYYY')"
											+ " and SUP_TRN_CD='"+rset7.getString(1)+"' ";
//									System.out.println("allocCntSql---"+allocCntSql);
									rset1 = stmt1.executeQuery(allocCntSql);
									if(rset1.next()) {
										prevTruckNomDt.add(nom_dt);
										prevTruckNm.add(rset7.getString(2)==null?"":rset7.getString(2));
										prevTruckNomRevNo.add(rset1.getString(1)==null?"":rset1.getString(1));
										prevNomID.add(nom_id);
										prevContTyp.add(contTyp);
										truckLoadedFlag.add("Y");
									}else {
										truckLoadedFlag.add("N");
										prevTruckNomDt.add("");
										prevTruckNm.add("");
										prevTruckNomRevNo.add("");
										prevNomID.add("");
										prevContTyp.add("");
									}
								}else {
//									System.out.println("******in else********");
									prevTruckNomDt.add(nom_dt);
									prevTruckNm.add(rset7.getString(2)==null?"":rset7.getString(2));
									prevTruckNomRevNo.add(nom_rev_no);
									prevNomID.add(nom_id);
									prevContTyp.add(contTyp);
									truckLoadedFlag.add("Y");
									
									truckIdAT.add(rset7.getString(1)==null?"":rset7.getString(1));
									truckNameAT.add(rset7.getString(2)==null?"":rset7.getString(2));
									custIDAT.add(rset7.getString(3)==null?"":rset7.getString(3));
									VTruckTransCd.add(rset7.getString(6)==null?"":rset7.getString(6));
									truck_load_capacity = rset7.getDouble(4)*(rset7.getDouble(7)/100); //SB20200730
									tankVolM3AT.add(nf.format(truck_load_capacity));  //SB20200730
									tankVolTonAT.add(nf.format(rset7.getDouble(5)*(rset7.getDouble(7)/100))); 
									
									}	
							}else {
//								//System.out.println("***************no_rev_no else***********");
								
								truckIdAT.add(rset7.getString(1)==null?"":rset7.getString(1));
								truckNameAT.add(rset7.getString(2)==null?"":rset7.getString(2));
								custIDAT.add(rset7.getString(3)==null?"":rset7.getString(3));
								VTruckTransCd.add(rset7.getString(6)==null?"":rset7.getString(6));
								truck_load_capacity = rset7.getDouble(4)*(rset7.getDouble(7)/100); //SB20200730
								tankVolM3AT.add(nf.format(truck_load_capacity));  //SB20200730
								tankVolTonAT.add(nf.format(rset7.getDouble(5)*(rset7.getDouble(7)/100))); 
								truckLoadedFlag.add("N");
								
								prevTruckNomDt.add("");
								prevTruckNm.add("");
								prevTruckNomRevNo.add("");
								prevNomID.add("");
								prevContTyp.add("");
							}
						}else {
//							//System.out.println("***************nomi_dt else***********");
							truckIdAT.add(rset7.getString(1)==null?"":rset7.getString(1));
							truckNameAT.add(rset7.getString(2)==null?"":rset7.getString(2));
							custIDAT.add(rset7.getString(3)==null?"":rset7.getString(3));
							VTruckTransCd.add(rset7.getString(6)==null?"":rset7.getString(6));
							truck_load_capacity = rset7.getDouble(4)*(rset7.getDouble(7)/100); //SB20200730
							tankVolM3AT.add(nf.format(truck_load_capacity));  //SB20200730
							tankVolTonAT.add(nf.format(rset7.getDouble(5)*(rset7.getDouble(7)/100))); 
							truckLoadedFlag.add("N");
							
							prevTruckNomDt.add("");
							prevTruckNm.add("");
							prevTruckNomRevNo.add("");
							prevNomID.add("");
							prevContTyp.add("");
						}
					
						
					String dailyNomCnt = "SELECT COUNT(*) FROM DLNG_DAILY_TRUCK_NOM_DTL WHERE MAPPING_ID like '"+temp_map_id+"'"
							+ " AND NOM_ID like '"+temp_nom_id+"' AND REV_NO='"+revNo+"' AND NOM_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') AND TRUCK_NM='"+rset7.getString(2)+"' ";
//					System.out.println("dailyNomCnt******"+dailyNomCnt);
					rset = stmt1.executeQuery(dailyNomCnt);
					if(rset.next()) {
						int temp_cnt = rset.getInt(1);
						if(temp_cnt > 0) {
							cnt++;
						}
					}
					
					/*checking for truck Allocation*/
					String dailyAllocCnt = "SELECT LOADED_ENE,LOADED_SCM,LOADED_VOL FROM DLNG_TANK_VOL_DTL WHERE "
							+ " SCH_ID like '"+temp_nom_id+"' "
							+ " AND TRN_NM='"+rset7.getString(2)+"'"
							+ " AND EFF_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY')";
//					System.out.println("dailyAllocCnt******"+dailyAllocCnt);
					rset = stmt1.executeQuery(dailyAllocCnt);
					if(rset.next()) {
						
						alloc_cnt++;  //= Integer.parseInt(rset.getString(1)==null?"0":rset.getString(1));
						Vtruck_alloc_mmbtu.add(rset.getString(1)==null?"0":rset.getString(1));
						Vtruck_alloc_scm.add(rset.getString(2)==null?"0":rset.getString(2));
						Vtruck_alloc_mt.add(rset.getString(3)==null?"0":rset.getString(3));
					}else {
						alloc_cnt = 0 ;
						Vtruck_alloc_mmbtu.add("0");
						Vtruck_alloc_scm.add("0");
						Vtruck_alloc_mt.add("0");
					}
					
					if(alloc_cnt > 0) {
						Vtruck_alloc_flag.add("Y");
					}else {
						Vtruck_alloc_flag.add("N");
					}
					
//					//System.out.println("alloc_cnt------"+alloc_cnt);
					
					/*checking invoice generated for the TRUCK*/
					double NomIdCnt=0;
					
					queryString5 = "SELECT NVL(TOTAL_QTY,0) FROM DLNG_INVOICE_MST "
							+ " WHERE PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ " AND MAPPING_ID  like '"+temp_nom_id+"'  "
							+ " AND  CONTRACT_TYPE='"+selContract_Type+"' "
							+ " AND  TRUCK_ID = '"+rset7.getString(1)+"' ";							
//					System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next()) {
						NomIdCnt = rset5.getDouble(1);
					    total_all_inv_qty+= NomIdCnt;
					    
					} 
					if(NomIdCnt>0) {
//						System.out.println("alloc_cnt--1----"+alloc_cnt);
						Vtruck_Inv_Flag.add("Y");
						Vtruck_Inv_qty.add(nf5.format(NomIdCnt));
						Vtruck_Inv_qty_mt.add(nf5.format((NomIdCnt) / convt_mmbtu_to_mt));
						total_all_inv_qty_scm+=  Math.round((NomIdCnt*multiplying_factor)/(Double.parseDouble(ncv_gcv+"")));
//						System.out.println(NomIdCnt+"---"+multiplying_factor+"----"+ncv_gcv);
					}else {
//						System.out.println("alloc_cnt--2----"+alloc_cnt);
						
						Vtruck_Inv_Flag.add("N");
						Vtruck_Inv_qty.add("");
						Vtruck_Inv_qty_mt.add("");
					}
				}
			}
			
//			System.out.println("total_nom_qty--"+total_nom_qty);
			String nom_net_amt = "0";
			
			if(total_nom_qty > 0) {
				
				String nom_gross_amt = nf.format(total_nom_qty * Double.parseDouble(Sales_Rate+"") * Double.parseDouble(exg_rate+""));
				String nom_tax_amt = nf.format((Double.parseDouble(nom_gross_amt) * Double.parseDouble(tax_rate+""))/100);
				
				nom_net_amt = nf.format(Double.parseDouble(nom_gross_amt) + (Double.parseDouble(nom_tax_amt)));
				
			}
			
			String link = selContract_Type+""+tempMap [1]+"-"+tempMap [2]+"-"+tempMap [3]+"-%-DLNG";
//			System.out.println("adv_collection_flg*****"+adv_collection_flg+"***selCust_id***"+selCust_id);
			//for Security type Open Account
			String fetchOASecSql = "select count(*) from FMS9_SECURITY_POST_MST where SEC_TYPE = 'OA'"
					+ " and LINK like '"+link+"' and CUSTOMER_CD = '"+selCust_id+"'";
//			System.out.println("fetchOASecSql***"+fetchOASecSql);
			rset = stmt.executeQuery(fetchOASecSql);
			if(rset.next()) {
				if(rset.getInt(1) > 0) {
					oa_flag = "Y";
					security = security+"OA";
				}
			}
//			System.out.println("security**1**"+security);
			String fetchSBLCSecSql = "select sec_type,currency,nvl(value,0) from FMS9_SECURITY_POST_MST "
					+ " where "
					+ " customer_cd = '"+selCust_id+"'"
					+ " and LINK like '"+link+"' " //or MAPPING_ID like '"+link+"' )"
					+ " and status = 'In order' "
					+ " and exp_dt >= to_date('"+gas_date+"','dd/mm/yyyy')"
					+ " and issu_dt <= to_date('"+gas_date+"','dd/mm/yyyy')"
					+ " and sec_type != 'OA'"
					+ " and sec_type != 'ADV' "; 
//			System.out.println("fetchSBLCSecSql****"+fetchSBLCSecSql);
			rset = stmt.executeQuery(fetchSBLCSecSql);
			while (rset.next()) {
				
				String sec_type = rset.getString(1)==null?"":rset.getString(1);
				String currency = rset.getString(2)==null?"":rset.getString(2);
				double temp_amt = rset.getDouble(3);
				if(currency.equalsIgnoreCase("USD")) {
					temp_amt = (temp_amt * Double.parseDouble(exg_rate+""));
				}
				sec_amt+=temp_amt;
				security= security+", "+sec_type+" : "+temp_amt+" "+currency;
			}
			System.out.println("security**amount**"+sec_amt);
			/* for advance amount */
//			if(adv_collection_flg.equalsIgnoreCase("Y")) {
				balance_adv_amt = calculateInvoiceAmount(nom_net_amt);
//			}
			
			System.out.println("balance_adv_amt*****"+balance_adv_amt);
			//////////////////////////////////////////////////////////
			tot_inv_mmbtu = nf5.format(total_all_inv_qty);
			tot_inv_scm = nf5.format(total_all_inv_qty_scm);
			tot_inv_mt = nf5.format((total_all_inv_qty / convt_mmbtu_to_mt));
			
			
			if(cnt > 0) { //Hiren_20201008 if DN done for the day
				for (int i = 0 ; i < truckNameAT.size() ; i++) {
//					System.out.println("truckLoadedFlag.elementAt(i)******1**"+truckLoadedFlag.elementAt(i));
					if(truckLoadedFlag.elementAt(i).equals("N")){
						
						fetchTruckNomData = "SELECT TRUCK_VOL,TRUCK_ENE,TO_CHAR(ARRIVAL_DT,'DD/MM/YYYY'),ARRIVAL_TIME,REMARKS,NEXT_AVAIL_DAYS FROM DLNG_DAILY_TRUCK_NOM_DTL WHERE MAPPING_ID like '"+temp_map_id+"'"
							+ " AND NOM_ID like '"+temp_nom_id+"' AND REV_NO='"+revNo+"' AND NOM_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') AND TRUCK_NM='"+truckNameAT.elementAt(i)+"' ";
//						System.out.println("fetchTruckNomData***"+fetchTruckNomData);
						rset = stmt.executeQuery(fetchTruckNomData);
						if(rset.next()) {
							TloadedVol.add(rset.getString(1)==null?"":rset.getString(1));
							TloadedEne.add(rset.getString(2)==null?"":rset.getString(2));
							TloadedDt.add(rset.getString(3)==null?"":rset.getString(3));
							TloadedTm.add(rset.getString(4)==null?"":rset.getString(4));
							TloadedRemark.add(rset.getString(5)==null?"":rset.getString(5));
							VLoadedNxt_avail_days.add(rset.getString(6)==null?"":rset.getString(6));
							
							prevCust_Nm.add("");
							prevSn.add("");
							prevPlant.add("");
							
						}else {
							TloadedVol.add("");
							TloadedEne.add("");
							TloadedDt.add("");
							TloadedTm.add("");
							TloadedRemark.add("");
							VLoadedNxt_avail_days.add("");
							
							prevCust_Nm.add("");
							prevSn.add("");
							prevPlant.add("");
							
						}
					}else {
						String fetchPrevTruckNomData = "SELECT TRUCK_VOL,TRUCK_ENE,TO_CHAR(ARRIVAL_DT,'DD/MM/YYYY'),ARRIVAL_TIME,REMARKS,NEXT_AVAIL_DAYS FROM DLNG_DAILY_TRUCK_NOM_DTL WHERE "
								+ " TRUCK_NM='"+prevTruckNm.elementAt(i)+"'"
								+ " AND  REV_NO='"+prevTruckNomRevNo.elementAt(i)+"'"
								+ " AND NOM_DT = TO_DATE ('"+prevTruckNomDt.elementAt(i)+"','DD/MM/YYYY') ";
						
//							//System.out.println("fetchPrevTruckNomData**Daily*"+fetchPrevTruckNomData);
							rset = stmt.executeQuery(fetchPrevTruckNomData);
							if(rset.next()) {
								
								TloadedVol.add(rset.getString(1)==null?"":rset.getString(1));
								TloadedEne.add(rset.getString(2)==null?"":rset.getString(2));
								TloadedDt.add(rset.getString(3)==null?"":rset.getString(3));
								TloadedTm.add(rset.getString(4)==null?"":rset.getString(4));
								TloadedRemark.add(rset.getString(5)==null?"":rset.getString(5));
								VLoadedNxt_avail_days.add(rset.getString(6)==null?"":rset.getString(6));
							}
//							System.out.println("prevNomID.elementAt(i)******1**"+prevNomID.elementAt(i));
							if(prevNomID.elementAt(i).toString().contains("-")) {
//								//System.out.println("prevNomID.elementAt(i)******1**"+prevNomID.elementAt(i));
								String nomIdArr[] = prevNomID.elementAt(i).toString().split("-");
//								String cust_cd = nomIdArr[0];
								
								String custSQL="select CUSTOMER_ABBR from FMS7_CUSTOMER_MST where CUSTOMER_CD='"+nomIdArr[0]+"'";
								rset4 = stmt3.executeQuery(custSQL);
								if(rset4.next()) {
									prevCust_Nm.add(rset4.getString(1)==null?"":rset4.getString(1));
								}else {
									prevCust_Nm.add("");
								}
								
								String contType="";
								if(prevContTyp.elementAt(i).equals("S")) {
									contType ="SN - "+nomIdArr[3];
								}else if(prevContTyp.elementAt(i).equals("L")) {
									contType ="LoA - "+nomIdArr[3];
								}
								prevSn.add(contType);
								
								queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+nomIdArr[0]+" AND A.seq_no="+nomIdArr[6]+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+prevTruckNomDt.elementAt(i)+"','DD/MM/YYYY'))";
								
								rset5 = stmt5.executeQuery(queryString3);
								if(rset5.next()) {
									prevPlant.add(rset5.getString(1)==null?"":rset5.getString(1)); 
								}else {
									prevPlant.add("");
								}
								
							}
					}
					
				}
				}
			if(cnt == 0) { // Hiren_20201008 get weekly nomination data if daily nomination is not done for the day
				
				for (int i = 0 ; i < truckNameAT.size() ; i++) {
					
					if(truckLoadedFlag.elementAt(i).equals("N")){
						
						fetchTruckNomData = "SELECT TRUCK_VOL,TRUCK_ENE,TO_CHAR(ARRIVAL_DT,'DD/MM/YYYY'),ARRIVAL_TIME,REMARKS,NEXT_AVAIL_DAYS "
							+ " FROM DLNG_WEEKLY_TRUCK_NOM_DTL WHERE MAPPING_ID like '"+temp_map_id+"'"
							+ " AND NOM_ID like '"+temp_nom_id+"'  AND NOM_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') AND TRUCK_NM='"+truckNameAT.elementAt(i)+"'"
							+ " AND REV_NO = (select max(rev_no) from DLNG_WEEKLY_TRUCK_NOM_DTL where"
							+ "	 	MAPPING_ID like '"+temp_map_id+"' "
								+ " and NOM_ID like '"+temp_nom_id+"' "
								+ " and nom_dt = TO_DATE ('"+nomDt+"','DD/MM/YYYY'))";
						
	//					////System.out.println("fetchTruckNomData weekly***"+fetchTruckNomData);
						rset = stmt.executeQuery(fetchTruckNomData);
						if(rset.next()) {
							
							TloadedVol.add(rset.getString(1)==null?"":rset.getString(1));
							TloadedEne.add(rset.getString(2)==null?"":rset.getString(2));
							TloadedDt.add(rset.getString(3)==null?"":rset.getString(3));
							TloadedTm.add(rset.getString(4)==null?"":rset.getString(4));
							TloadedRemark.add(rset.getString(5)==null?"":rset.getString(5));
							VLoadedNxt_avail_days.add(rset.getString(6)==null?"":rset.getString(6));
							prevCust_Nm.add("");
							prevSn.add("");
							prevPlant.add("");
							
						}else {
							TloadedVol.add("");
							TloadedEne.add("");
							TloadedDt.add("");
							TloadedTm.add("");
							TloadedRemark.add("");
							VLoadedNxt_avail_days.add("");
							prevCust_Nm.add("");
							prevSn.add("");
							prevPlant.add("");
						}
					}else {
						
					String fetchPrevTruckNomData = "SELECT TRUCK_VOL,TRUCK_ENE,TO_CHAR(ARRIVAL_DT,'DD/MM/YYYY'),ARRIVAL_TIME,REMARKS,NEXT_AVAIL_DAYS FROM DLNG_DAILY_TRUCK_NOM_DTL WHERE "
							+ " TRUCK_NM='"+prevTruckNm.elementAt(i)+"'"
							+ " AND  REV_NO='"+prevTruckNomRevNo.elementAt(i)+"'"
							+ " AND NOM_DT = TO_DATE ('"+prevTruckNomDt.elementAt(i)+"','DD/MM/YYYY') ";
					
//						//System.out.println("fetchPrevTruckNomData*Weekly**"+fetchPrevTruckNomData);
						rset = stmt.executeQuery(fetchPrevTruckNomData);
						if(rset.next()) {
							
							TloadedVol.add(rset.getString(1)==null?"":rset.getString(1));
							TloadedEne.add(rset.getString(2)==null?"":rset.getString(2));
							TloadedDt.add(rset.getString(3)==null?"":rset.getString(3));
							TloadedTm.add(rset.getString(4)==null?"":rset.getString(4));
							TloadedRemark.add(rset.getString(5)==null?"":rset.getString(5));
							VLoadedNxt_avail_days.add(rset.getString(6)==null?"":rset.getString(6));
						}
						
						if(prevNomID.elementAt(i).toString().contains("-")) {
//							//System.out.println("prevNomID.elementAt(i)******2**"+prevNomID.elementAt(i));
							String nomIdArr[] = prevNomID.elementAt(i).toString().split("-");
							
							String custSQL="select CUSTOMER_ABBR from FMS7_CUSTOMER_MST where CUSTOMER_CD='"+nomIdArr[0]+"'";
							rset4 = stmt3.executeQuery(custSQL);
							if(rset4.next()) {
								prevCust_Nm.add(rset4.getString(1)==null?"":rset4.getString(1));
							}
							String contType="";
							if(prevContTyp.elementAt(i).equals("S")) {
								contType ="SN - "+nomIdArr[3];
							}else if(prevContTyp.elementAt(i).equals("L")) {
								contType ="LoA - "+nomIdArr[3];
							}
							prevSn.add(contType);
						
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+nomIdArr[0]+" AND A.seq_no="+nomIdArr[6]+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+prevTruckNomDt.elementAt(i)+"','DD/MM/YYYY'))";
							
							rset5 = stmt5.executeQuery(queryString3);
							if(rset5.next()) {
								prevPlant.add(rset5.getString(1)==null?"":rset5.getString(1)); 
							}else {
								prevPlant.add("");
							}
						}
					}
				}
			}
//			//System.out.println("buyer_mapping_id***"+nomId);
//				String tempMap [] = buyer_mapping_id.split("-");
				String tempMapid = tempMap[0]+"-%-%-%-%";
				String tsn = tempMap[3];
				
				String loaded_truck = "SELECT TRUCK_NM,NOM_ID FROM DLNG_DAILY_TRUCK_NOM_DTL WHERE NOM_ID like '"+temp_map_id+"'"
						+ " AND NOM_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') AND REV_NO=(select max(REV_NO) from DLNG_DAILY_TRUCK_NOM_DTL WHERE"
						+ " NOM_ID like '"+temp_nom_id+"'  AND NOM_DT = TO_DATE ('"+nomDt+"','DD/MM/YYYY') )" ;
//				//System.out.println("loaded_truck***"+loaded_truck);
				rset1 = stmt.executeQuery(loaded_truck);
				while (rset1.next()) {
					VLoadedTruck.add(rset1.getString(1)==null?"":rset1.getString(1));
					Vnom_id.add(rset1.getString(2)==null?"":rset1.getString(2));
					
					String loadCust[] = rset1.getString(2).split("-");
					String temp_cust = loadCust[0];
					String temp_plant = loadCust[6];
					String temp_sn = loadCust[3];
					
					if(selCust_id.equals(temp_cust) && custPlant_cd.equals(temp_plant) && (tsn.equals(temp_sn))) 
					{
//						//System.out.println("Loaded Trucks.. : "+rset1.getString(1)+"..len :"+rset1.getString(1).length());
					}
					else
					{
						if(selCust_id.equals(temp_cust) && (!custPlant_cd.equals(temp_plant))) 
						{
//							////System.out.println("Else if 1 OnlyLoadedTruck : "+rset1.getString(1)+"..len :"+rset1.getString(1).length());
							OnlyLoadedTruck.add(rset1.getString(1));
						}
						if(selCust_id.equals(temp_cust) && (custPlant_cd.equals(temp_plant)) && (!tsn.equals(temp_sn)))
						{
//							////System.out.println("Else if 2 (SN_no !=)  OnlyLoadedTruck : "+rset1.getString(1)+"..len :"+rset1.getString(1).length());
							OnlyLoadedTruck.add(rset1.getString(1));
						}
					}
				}//EoF cnt
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private String calculateInvoiceAmount(String nom_net_amt)throws SQLException,IOException {
			
			double balance_adv_amt = 0;
			double pay_recv_amt = 0;
			
			System.out.println("nom_net_amt***"+nom_net_amt);
			if(selMapId.contains("-")) {
				
				String temp_arr[] = selMapId.split("-");
				
				String contTyp = temp_arr[0];
				String custCd =  temp_arr[1];
				String flsaCd =  temp_arr[2];
				String snCd = temp_arr[4];
				String start_dt = temp_arr[6];
				String end_dt = temp_arr[7];
				
//				System.out.println("selMapId----"+selMapId);
				
				
				String compEndDt = "select * from dual where TO_DATE('"+end_dt+"','DD/MM/YYYY') > sysdate ";
//				System.out.println("compEndDt----"+compEndDt);
				rset = stmt.executeQuery(compEndDt);
				if(rset.next()) {
//					end_dt = sysdate ; 	
				}
				
				/*checking for Advance before SN Start Date*/
				String checkBefAdvSql = "select nvl(PAY_AMT,0),PAY_TYPE,to_char(PAY_DT,'DD/MM/YYYY'),nvl(DR_CR_FLAG,'') "
						+ " from DLNG_ADVC_PAY_MST"
						+ " where PAY_DT < to_date('"+start_dt+"','dd/mm/yyyy')"
						+ " AND CUSTOMER_CD = '"+custCd+"'"
						+ " AND FLSA_NO = '"+flsaCd+"'"
						+ " AND SN_NO = '"+snCd+"'"
						+ " AND CONTRACT_TYPE = '"+contTyp+"' "
						+ " AND APPROVED_FLAG = 'Y' ";
				
//				System.out.println("checkBefAdvSql*********"+checkBefAdvSql);
				rset1 = stmt1.executeQuery(checkBefAdvSql);
				while (rset1.next()) {
					
					String drcr = rset1.getString(4)==null?"":rset1.getString(4);
					if(drcr.equalsIgnoreCase("D")) {
						balance_adv_amt-=rset1.getDouble(1);
					}else {
						balance_adv_amt+=rset1.getDouble(1);
					}
				}
				
				String dateList = "SELECT to_char(to_date('"+start_dt+"', 'dd/mm/yyyy') + LEVEL - 1,'dd/mm/yyyy')"
						+ " FROM DUAL"
						+ " CONNECT BY LEVEL <= to_date('"+end_dt+"', 'dd/mm/yyyy') + 1 - to_date('"+start_dt+"', 'dd/mm/yyyy')";
//				System.out.println("dateList******"+dateList);
				rset = stmt.executeQuery(dateList);
				while (rset.next()) {
					
					String selDate = rset.getString(1)==null?"":rset.getString(1);
					if(!selDate.equals("")) {
						
						/*checking for Advance*/
						String checkAdvSql = "select nvl(PAY_AMT,0),PAY_TYPE,DR_CR_FLAG from DLNG_ADVC_PAY_MST"
								+ " where PAY_DT = to_date('"+selDate+"','dd/mm/yyyy')"
								+ " AND CUSTOMER_CD = '"+custCd+"'"
								+ " AND FLSA_NO = '"+flsaCd+"'"
								+ " AND SN_NO = '"+snCd+"'"
								+ " AND CONTRACT_TYPE = '"+contTyp+"' "
								+ " AND APPROVED_FLAG = 'Y' ";
						
//						System.out.println("checkAdvSql*********"+checkAdvSql);
						rset1 = stmt1.executeQuery(checkAdvSql);
						while (rset1.next()) {
							
							String drcr = rset1.getString(3)==null?"":rset1.getString(3);
							if(drcr.equalsIgnoreCase("D")) {
								balance_adv_amt-=rset1.getDouble(1);
							}else {
								balance_adv_amt+=rset1.getDouble(1);
							}
						}
//						System.out.println("balance_adv_amt--"+balance_adv_amt);
						/*checking for invoice*/
						String checkInvSql = "select NET_AMT_INR,NEW_INV_SEQ_NO,MAPPING_ID from DLNG_INVOICE_MST"
								+ " where  INVOICE_DT = to_date('"+selDate+"','dd/mm/yyyy')"
								+ " AND CUSTOMER_CD = '"+custCd+"'"
								+ " AND FGSA_NO = '"+flsaCd+"'"
								+ " AND SN_NO = '"+snCd+"'"
								+ " AND CONTRACT_TYPE = '"+contTyp+"' ";
//						System.out.println("checkInvSql-------"+checkInvSql);
						rset1 = stmt1.executeQuery(checkInvSql);
						while (rset1.next()) {
							balance_adv_amt-=rset1.getDouble(1);
							
							/*checking for Invoice HOLD Amount*/
							String map_id = rset1.getString(3)==null?"":rset1.getString(3);
							String temp_arr1[];
							String temp_mapId="";
							if(map_id.contains("-")) {
								
								temp_arr1 = map_id.split("-");
								temp_mapId = temp_arr1[0]+"-"+temp_arr1[1]+"-"+temp_arr1[2]+"-"+temp_arr1[3]+"-"+temp_arr1[4]+"-"+contTyp;
							}else {
								temp_mapId = "%-%-%-%-%-"+contTyp;
							}
	//						System.out.println("temp_mapId-------"+temp_mapId);
							String holdAmtSql = "select nvl(HOLD_AMOUNT,0),to_char(PAY_RECV_DT,'dd/mm/yyyy') from FMS8_PAY_RECV_DTL where NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'"
									+ " and MAPPING_ID like '"+temp_mapId+"' "
									+ " and COMMODITY_TYPE='DLNG' "
									+ " and REV_NO = (select max(REV_NO) from FMS8_PAY_RECV_DTL where"
										+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"' "
										+ " and MAPPING_ID like '"+temp_mapId+"'  and COMMODITY_TYPE='DLNG' )";
	//						System.out.println("holdAmtSql---Invoice---"+holdAmtSql);
							rset2 = stmt2.executeQuery(holdAmtSql);
							if(rset2.next()) {
								if(Double.parseDouble(rset2.getString(1)+"") > 0) {
									
									balance_adv_amt-=rset2.getDouble(1);
									
									/*checking for C-Form*/
									String CFormSql = "select A.CFORM_NO,to_char(B.CFORM_DT,'dd/mm/yyyy') "
											+ " from FMS7_CFORM_DTL A,FMS7_CFORM_MST B WHERE INVOICE_NO = '"+rset1.getString(2)+"' "
											+ " and A.CFORM_NO = B.CFORM_NO";
	//								System.out.println("CFormSql----"+CFormSql);
									rset3 = stmt3.executeQuery(CFormSql);
									if(rset3.next()) {
										
										balance_adv_amt+=rset2.getDouble(1);
									}
								}
							}
						}
						/*checking for debit/credit note*/
						String checkDrCrSql = "select DR_CR_NET_AMT_INR,DR_CR_DOC_NO,DR_CR_FLAG from "
								+ " DLNG_DR_CR_NOTE where"
								+ " DR_CR_DT = to_date('"+selDate+"','dd/mm/yyyy')"
								+ " AND CUSTOMER_CD = '"+custCd+"'"
								+ " AND FGSA_NO = '"+flsaCd+"'"
								+ " AND SN_NO = '"+snCd+"'"
								+ " AND CONTRACT_TYPE = '"+contTyp+"'"
								+ " AND APRV_BY > 0  ";
	//					System.out.println("checkDrCrSql----"+checkDrCrSql);
						rset1 = stmt1.executeQuery(checkDrCrSql);
						while (rset1.next()) {
							String drcr = "";
							if(rset1.getString(3).equalsIgnoreCase("dr")) {
								balance_adv_amt-=rset1.getDouble(1);
							}else {
								balance_adv_amt+=rset1.getDouble(1);
							}
							/*checking for Dr/Cr HOLD Amount*/
							
							String holdAmtSql = "select nvl(HOLD_AMOUNT,0),to_char(PAY_RECV_DT,'dd/mm/yyyy') from FMS8_PAY_RECV_DTL where "
									+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'  and COMMODITY_TYPE='DLNG' "
									+ " and REV_NO = (select max(REV_NO) from FMS8_PAY_RECV_DTL where"
										+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'  and COMMODITY_TYPE='DLNG' )";
	//						System.out.println("holdAmtSql--DR/CR----"+holdAmtSql);
							rset2 = stmt2.executeQuery(holdAmtSql);
							if(rset2.next()) {
								if(Double.parseDouble(rset2.getString(1)+"") > 0) {
									balance_adv_amt-=rset2.getDouble(1);
								}
							}
						}
					}
					
					/* to restore LC/BG/PCG amount */
					if(sec_amt > 0 ) {
						String sumInvSql = "select nvl(sum(PAY_RECV_AMT),0) from DLNG_INVOICE_MST"
								+ " where  INVOICE_DT = to_date('"+selDate+"','dd/mm/yyyy')"
								+ " AND CUSTOMER_CD = '"+custCd+"'"
								+ " AND FGSA_NO = '"+flsaCd+"'"
								+ " AND SN_NO = '"+snCd+"'"
								+ " AND CONTRACT_TYPE = '"+contTyp+"'"
								+ " AND PAY_REMARK != 'Auto Paid' ";
//						System.out.println("sumInvSql-------"+sumInvSql);
						rset1 = stmt1.executeQuery(sumInvSql);
						if(rset1.next()) {
							pay_recv_amt+=rset1.getDouble(1);
						}
					}
				}
				balance_adv_amt-= Double.parseDouble(nom_net_amt+"");
			}
			System.out.println("balance_adv_amt**"+nf.format(balance_adv_amt)+"**pay_recv_amt**"+pay_recv_amt);
			return nf.format(balance_adv_amt+sec_amt+pay_recv_amt);
		}

		private String fetchAdvDtl(String nom_id, String cont_typ, String gas_date,String truck_nm,String truck_id,String rev_no) {
			try {
				//System.out.println("**********************"+truck_nm+"*******************");
				int invCnt= 0,allocCnt = 0,schCnt = 0;
				Vector nomDtList = new Vector();
				String gasDtSql = "select distinct (to_char(NOM_DT,'dd/mm/yyyy')) from DLNG_DAILY_NOM"
						+ " where NOM_ID like '"+nom_id+"' "
						+ " and NOM_DT >= to_date('"+gas_date+"','dd/mm/yyyy')";
//				System.out.println("gasDtSql-----"+gasDtSql);
				rset1 = stmt1.executeQuery(gasDtSql);
				while (rset1.next()) {
					nomDtList.add(rset1.getString(1)==null?"":rset1.getString(1));
				}
				//System.out.println("nomDtList-----"+nomDtList);
				for(int i = 0 ; i < nomDtList.size() ; i++) {
					if(!nomDtList.elementAt(i).equals("")) {
						
						String invMstSql = "SELECT NVL(TOTAL_QTY,0) FROM DLNG_INVOICE_MST "
						+ " WHERE INVOICE_DT=TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY') "
						+ " AND MAPPING_ID  like '"+nom_id+"'  "
						+ " AND  CONTRACT_TYPE='"+cont_typ+"' "
						+ " AND  TRUCK_ID = '"+truck_id+"' ";
						//System.out.println("invMstSql ********** "+invMstSql);
						rset5 = stmt5.executeQuery(invMstSql);
						if(rset5.next()) {
							
							total_truck_inv_qty+=rset5.getDouble(1);
							invCnt++;
						}
						
						if(invCnt == 0) {
							
							String allocMstSql = "select EXIT_TOT_ENE from DLNG_ALLOC_MST where "
									+ " ALLOC_ID  like '"+nom_id+"'  "
									+ " AND  CONTRACT_TYPE='"+cont_typ+"' "
									+ " AND ALLOC_DT=TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY')"
									+ "  AND SUP_TRN_CD = '"+truck_id+"' ";
							//System.out.println("allocMstSql--------"+allocMstSql);
							rset5 = stmt5.executeQuery(allocMstSql);
							if(rset5.next()) {
								
								total_nom_qty+=rset5.getDouble(1);
								allocCnt++;
							}			
						
							if(allocCnt == 0) {
								
								String schMstSql = "select TRUCK_VOL from DLNG_DAILY_TRUCK_SCH_DTL where "
										+ " SCH_ID  like '"+nom_id+"'  "
										+ " AND  CONTRACT_TYPE='"+cont_typ+"' "
										+ " AND SCH_DT=TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY')"
										+ " AND TRUCK_NM = '"+truck_nm+"' "
										+ " AND REV_NO = (select max(REV_NO) from DLNG_DAILY_TRUCK_SCH_DTL where "
											+ " SCH_ID  like '"+nom_id+"'  "
											+ " AND  CONTRACT_TYPE='"+cont_typ+"' "
											+ " AND SCH_DT=TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY')"
											+ " AND TRUCK_NM = '"+truck_nm+"')";
								//System.out.println("schMstSql--------"+schMstSql);
								rset5 = stmt5.executeQuery(schMstSql);
								if(rset5.next()) {
									
//									total_nom_qty+=rset5.getDouble(1);
//									schCnt++;
								}
								
								if(schCnt == 0 ) {
									int nomRev=0;
									String maxSql = "select max(rev_no) from DLNG_DAILY_NOM where"
											+ " NOM_DT = TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY') "
											+ " AND NOM_ID  like '"+nom_id+"'";
									rset1 = stmt1.executeQuery(maxSql);
									if(rset1.next()) {
										nomRev = rset1.getInt(1);
									}
									
									String tot_nom_sql ="select nvl(TRUCK_VOL,0)  from DLNG_DAILY_TRUCK_NOM_DTL where "
											+ " NOM_DT = TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY') "
											+ " and TRUCK_NM='"+truck_nm+"' "
											+ " AND NOM_ID  like '"+nom_id+"'"
											+ " AND REV_NO = '"+nomRev+"'"
											+ " AND  CONTRACT_TYPE='"+cont_typ+"' ";	
									//System.out.println("tot_nom_sql***"+tot_nom_sql);
									rset3 = stmt1.executeQuery(tot_nom_sql);
									if(rset3.next()) {
										
										total_nom_qty+= Double.parseDouble(rset3.getString(1)==null?"":rset3.getString(1));
									}
								}
							}
						}	
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}

		String dr_cr_amt = "";
		private String fetchDrCrAmt(String customer_cd,String flsa_no,String sn_no,String cont_type,String gas_date,String flsa_rev_no,String sn_rev_no)throws SQLException,IOException {
			
			String dr_amt = "0",cr_amt = "0" ;
			try {
				
				String drSql = "select nvl(sum(DR_CR_NET_AMT_INR),0) from DLNG_DR_CR_NOTE where "
						+ " CUSTOMER_CD = '"+customer_cd+"'"
						+ " AND FGSA_NO='"+flsa_no+"'"
						+ " AND SN_NO = '"+sn_no+"' "
						+ " AND CONTRACT_TYPE = '"+cont_type+"'"
						+ " AND DR_CR_FLAG = 'dr' "
						+ " AND FLAG= 'Y'"
						+ " AND APRV_BY  is not null "
						+ " AND APRV_BY!=0 "
						+ " AND APRV_DT is not null" ;
				//System.out.println("drSql---------"+drSql);
				rset3 = stmt3.executeQuery(drSql);
				if(rset3.next()) {
					dr_amt = rset3.getString(1)==null?"0":rset3.getString(1);
				}
				
				String crSql = "select nvl(sum(DR_CR_NET_AMT_INR),0) from DLNG_DR_CR_NOTE where "
						+ " CUSTOMER_CD = '"+customer_cd+"'"
						+ " AND FGSA_NO='"+flsa_no+"'"
						+ " AND SN_NO = '"+sn_no+"' "
						+ " AND CONTRACT_TYPE = '"+cont_type+"'"
						+ " AND DR_CR_FLAG = 'cr' "
						+ " AND FLAG= 'Y'"
						+ " AND APRV_BY  is not null "
						+ " AND APRV_BY!=0 "
						+ " AND APRV_DT is not null" ;
				//System.out.println("crSql---------"+crSql);
				rset3 = stmt3.executeQuery(crSql);
				if(rset3.next()) {
					cr_amt = rset3.getString(1)==null?"0":rset3.getString(1);
				}
				
				
			} catch (Exception e) {
				
			}
			return dr_amt+"@"+cr_amt;
		}

/////////////////////////Added by Sujit on 20200819////////////////////////
		private void fetchWeeklyBuyerNomDetails() 
		{
			methodName = "fetchWeeklyBuyerNomDetails()";
			try {
					double final_daily_dcq = 0;
					double final_daily_mmbtu = 0;
					double final_daily_scm = 0;
					double total_dcq = 0;
					double total_mmbtu = 0;
					double total_scm = 0;
					
					queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " ,A.FCC_FLAG, SN_BASE "
							+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
						//SB20200702			+ "AND A.FCC_FLAG='Y' "
							+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
//					////System.out.println("Fetching SN COntracts.."+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
//						////System.out.println("rset1.getString(1)*******"+rset1.getString(1));
						if(!rset1.getString(1).equals("0"))
						{
							queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.flsa_no="+rset1.getString(2)+" " +
									   "AND A.sn_no="+rset1.getString(4)+"";
							////System.out.println("queryString2****"+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							while(rset2.next())
							{
								daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
								
								queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								
								////System.out.println("Customer Plant Name Fetch Query (CN) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Plant_Abbr line no 265*******"+rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
								}
								
								daily_Buyer_Nom_Mapping_Id.add(map_id);
								////////System.out.println("daily_Buyer_Nom_Mapping_Id line no 273*******"+daily_Buyer_Nom_Mapping_Id);
								daily_Buyer_Nom_Fgsa_No.add(rset1.getString(2));
								daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(3));
								daily_Buyer_Nom_Sn_No.add(rset1.getString(4));
								daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(5));
								
								daily_Buyer_Nom_Cd.add(rset1.getString(1));
								//////System.out.println("daily_Buyer_Nom_Cd line no 279*******"+daily_Buyer_Nom_Cd);
								
								daily_Buyer_Nom_Type.add("S");
								daily_Buyer_Nom_Contract_Type.add("SN");
								String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(7))<=100?100:Double.parseDouble(rset1.getString(7)));

							   /*-----------------------Variable DCQ-------------------*/
								
								double dcq_var=0; double dcq_var_MT=0; 	double convt_mmbtu_to_mt = 51.5;
								queryString = "SELECT NVL(dcq,'0') "
										+ " FROM DLNG_SN_DCQ_DTL A WHERE A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
										+ " AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.FLAG='Y' "
										+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_DCQ_DTL B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
										+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.FLAG='Y') ";
//										////System.out.println("DLNG_SN_DCQ_DTL: "+queryString);
										rset = stmt.executeQuery(queryString);
										if(rset.next())
										{
//											dcq_var= rset.getDouble(1);
											dcq_var = 0;
										}
										if(dcq_var>0)
										{
											daily_Buyer_Nom_Dcq.add(nf.format(dcq_var));	
											daily_Buyer_Nom_Mdcq_Qty.add(nf.format((dcq_var*Double.parseDouble(temp_mdcq_percentage))/100));
											total_dcq += dcq_var;
											dcq_var_MT= (dcq_var/convt_mmbtu_to_mt);
											daily_Buyer_Nom_Dcq_Mt.add(nf.format(dcq_var_MT));
										}
										else
										{
											daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));	
											daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
											total_dcq += Double.parseDouble(rset1.getString(6));
											daily_Buyer_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(10))));
										}

								VBuyer_Fcc_Flag.add(rset1.getString(11)==null?"N":rset1.getString(11)); //SB20200718
								VBuyer_Delv_Base.add(rset1.getString(12)==null?"":rset1.getString(12)); //SB20200804
								String ALLOCATED_QTY = "0";
								String ALLOCATED_QTY1 = "0";
								
								String CONTRACTED_QTY =nf5.format(Double.parseDouble(rset1.getString(8)) + (Double.parseDouble(rset1.getString(9))));
								
								daily_Buyer_regas_cargo_boe_no.add("");
								daily_Buyer_regas_cargo_boe_dt.add("");
							
									queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
											+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
//									////System.out.println("--Fetching Data--"+queryString);
									rset = stmt.executeQuery(queryString);
												
									if(rset.next())
									{
										if(rset.getString(1)!=null)
										{
											if(!rset.getString(1).trim().equals(""))
											{
												ALLOCATED_QTY = rset.getString(1).trim();
												//////System.out.println("rset from line no 313*******"+ALLOCATED_QTY);
											}
										}
									}
									
								queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
										+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
								rset = stmt.executeQuery(queryString);
												
								if(rset.next())
								{
									if(rset.getString(1)!=null)
									{
										if(!rset.getString(1).trim().equals(""))
										{
											ALLOCATED_QTY1 = rset.getString(1).trim();
										} 
									} 
								} 
								
								ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
								
								daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
								
								queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
										   	+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Abbr.add(rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Abbr from line no 350 "+daily_Buyer_Nom_Abbr);
								}
								else
								{
									daily_Buyer_Nom_Abbr.add(" ");
								}
								
								
								String NomId="";
								queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
										+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
										+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID='"+map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
								
//								////System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (CN) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
									daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
									daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
									daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
									daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
									daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
									total_mmbtu += Double.parseDouble(rset5.getString(5));
									total_scm += Double.parseDouble(rset5.getString(6));
									NomId=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);	
								}
								else
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Rev_No.add("0");
									daily_Buyer_Gen_Day_Time.add("");
									daily_Buyer_Nom_Plant_Seq_No.add("0");
									daily_Buyer_Nom_Qty_Mmbtu.add("");
									daily_Buyer_Nom_Qty_Scm.add("");
								}
								////Check for Invoice generated////////
								int NomIdCnt=0;
								queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID='"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";							
//								////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
									NomIdCnt=rset5.getInt(1);
								if(NomIdCnt>0)
									VBuyer_Inv_Flag.add("Y");
								else
									VBuyer_Inv_Flag.add("N");
							}
						}
					}//end of while loop
				
					/* **********************for LoA Contracts***************************** Hiren_20200428*/
				
					queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " ,A.FCC_FLAG, LOA_BASE "
							+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
						//SB20200705			+ "AND A.FCC_FLAG='Y' "
							+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
//						////System.out.println("Fetching LoA COntracts.."+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						//mappaing_id = customer_cd-tender_no-0-loa_no-loa_rev_no
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4);
//						////System.out.println("rset1.getString(1)*******"+rset1.getString(1));
						if(!rset1.getString(1).equals("0"))
						{
							/*///////////////////////////SB20200801/////////////////////////////////
							queryString2 = "SELECT TENDER_BASE FROM DLNG_TENDER_MST " +
									   "WHERE customer_cd="+rset1.getString(1)+" AND TENDER_NO="+rset1.getString(2)+" AND FLAG='Y' ";
//							////System.out.println("LoA Plant No****"+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							while(rset2.next())
							{
								VBuyer_Delv_Base.add(rset2.getString(1));
							}
							*////////////////////^^SB20200801/////////////////////////
							queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.TENDER_NO="+rset1.getString(2)+" " +
									   "AND A.LOA_NO="+rset1.getString(3)+" AND LOA_REV_NO = '"+rset1.getString(4)+"'";
//							////System.out.println("LoA Plant No****"+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							while(rset2.next())
							{
								daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
								
								queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//								////System.out.println("LoA Plant Name = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Plant_Abbr line no 265*******"+rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
								}
								
								daily_Buyer_Nom_Mapping_Id.add(map_id);
//								////System.out.println("daily_Buyer_Nom_Mapping_Id line no 273*******"+rset1.getString(3));
								daily_Buyer_Nom_Fgsa_No.add(rset1.getString(2));
								daily_Buyer_Nom_Fgsa_Rev_No.add("0");
								daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
								daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
								
								daily_Buyer_Nom_Cd.add(rset1.getString(1));
								//////System.out.println("daily_Buyer_Nom_Cd line no 279*******"+daily_Buyer_Nom_Cd);
								
								daily_Buyer_Nom_Type.add("L");
								daily_Buyer_Nom_Contract_Type.add("LoA");
								
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(5))));	
								String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(6))<=100?100:Double.parseDouble(rset1.getString(6)));
								daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(5))*Double.parseDouble(temp_mdcq_percentage))/100));
								total_dcq += Double.parseDouble(rset1.getString(5));
								
								daily_Buyer_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(9))));
								VBuyer_Fcc_Flag.add(rset1.getString(10)==null?"N":rset1.getString(10)); //SB20200718
								VBuyer_Delv_Base.add(rset1.getString(11)==null?"":rset1.getString(11)); //SB20200804
								String ALLOCATED_QTY = "0";
								String ALLOCATED_QTY1 = "0";
								
								String CONTRACTED_QTY =nf5.format(Double.parseDouble(rset1.getString(7)) + (Double.parseDouble(rset1.getString(8))));
								
//								daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
//								daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
								daily_Buyer_regas_cargo_boe_no.add("");
								daily_Buyer_regas_cargo_boe_dt.add("");
							
									queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
											+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
											+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
											+ "AND REV_NO = ("
											+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ "AND SCH_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
//									////System.out.println("--Fetching Data--"+queryString);
									rset = stmt.executeQuery(queryString);
												
									if(rset.next())
									{
										if(rset.getString(1)!=null)
										{
											if(!rset.getString(1).trim().equals(""))
											{
												ALLOCATED_QTY = rset.getString(1).trim();
												//////System.out.println("rset from line no 313*******"+ALLOCATED_QTY);
											}
										}
									}
									
								queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
										+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND SCH_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
								rset = stmt.executeQuery(queryString);
												
								if(rset.next())
								{
									if(rset.getString(1)!=null)
									{
										if(!rset.getString(1).trim().equals(""))
										{
											ALLOCATED_QTY1 = rset.getString(1).trim();
										} 
									} 
								} 
								
								ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
								
								daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
								
								queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
										   	+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Abbr.add(rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Abbr from line no 350 "+daily_Buyer_Nom_Abbr);
								}
								else
								{
									daily_Buyer_Nom_Abbr.add(" ");
								}
								String NomId="";
								queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
										+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),NOM_BASE_FLAG FROM DLNG_WEEKLY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
										+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID='"+map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ "AND NOM_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
								
								////System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (CN) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
									daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
									daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
									daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
									daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
									daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
									total_mmbtu += Double.parseDouble(rset5.getString(5));
									total_scm += Double.parseDouble(rset5.getString(6));
									NomId=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);
								}
								else
								{
									daily_Buyer_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Rev_No.add("0");
									daily_Buyer_Gen_Day_Time.add("");
									daily_Buyer_Nom_Plant_Seq_No.add("0");
									daily_Buyer_Nom_Qty_Mmbtu.add("");
									daily_Buyer_Nom_Qty_Scm.add("");
								}
							////Check for Invoice generated////////
								int NomIdCnt=0;
								queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID='"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";							
//								////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
									NomIdCnt=rset5.getInt(1);
								if(NomIdCnt>0)
									VBuyer_Inv_Flag.add("Y");
								else
									VBuyer_Inv_Flag.add("N");
							}
						}
					}
					
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
				
				if(final_daily_mmbtu>0)
				{
					daily_Total_Mmbtu = nf.format(final_daily_mmbtu);
				}
				else
				{
					daily_Total_Mmbtu = "";
				}
				
				if(final_daily_scm>0)
				{
					daily_Total_Scm = nf.format(final_daily_scm);
				}
				else
				{
					daily_Total_Scm = "";
				}
				
				daily_Total_Dcq = nf.format(final_daily_dcq);
				
				queryString = "SELECT DISTINCT CUSTOMER_CD FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUST_CD.add(rset.getString(1)==null?"0":rset.getString(1));
					//////System.out.println("CUST_CD from line no 420 "+CUST_CD);
					
				}
				
				for(int i =0;i<daily_Buyer_Nom_Contract_Type.size();i++)
				{
					qty_nomination.add(daily_Buyer_Nom_Balance_Qty.elementAt(i)+"");
				}
				
			} catch (Exception e) {
				
			}
		}
/////////////////////////Added by Sujit on 20200819////////////////////////
private void fetchWeeklyBuyerNomDetails2() 
{
methodName = "fetchWeeklyBuyerNomDetails()";
try {
	double final_daily_dcq = 0;
	double final_daily_mmbtu = 0;
	double final_daily_scm = 0;
	double total_dcq = 0;
	double total_mmbtu = 0;
	double total_scm = 0;
	
	queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
			+ " ,A.FCC_FLAG, SN_BASE "
			+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
			+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
		//SB20200702			+ "AND A.FCC_FLAG='Y' "
			+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
			+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
//	////System.out.println("Fetching SN COntracts.."+queryString1);
	rset1 = stmt1.executeQuery(queryString1);
	while(rset1.next())
	{
		String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
//		////System.out.println("rset1.getString(1)*******"+rset1.getString(1));
		if(!rset1.getString(1).equals("0"))
		{
			queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
					   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.flsa_no="+rset1.getString(2)+" " +
					   "AND A.sn_no="+rset1.getString(4)+"";
//			////System.out.println("queryString2****"+queryString2);
			rset2 = stmt2.executeQuery(queryString2);
			while(rset2.next())
			{
				daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
				
				queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				
				//////System.out.println("Customer Plant Name Fetch Query (CN) = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
					//////System.out.println("daily_Buyer_Nom_Plant_Abbr line no 265*******"+rset3.getString(1));
				}
				else
				{
					daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
				}
				
				daily_Buyer_Nom_Mapping_Id.add(map_id);
				////////System.out.println("daily_Buyer_Nom_Mapping_Id line no 273*******"+daily_Buyer_Nom_Mapping_Id);
				daily_Buyer_Nom_Fgsa_No.add(rset1.getString(2));
				daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(3));
				daily_Buyer_Nom_Sn_No.add(rset1.getString(4));
				daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(5));
				
				daily_Buyer_Nom_Cd.add(rset1.getString(1));
				//////System.out.println("daily_Buyer_Nom_Cd line no 279*******"+daily_Buyer_Nom_Cd);
				
				daily_Buyer_Nom_Type.add("S");
				daily_Buyer_Nom_Contract_Type.add("SN");
				String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(7))<=100?100:Double.parseDouble(rset1.getString(7)));

			   /*-----------------------Variable DCQ-------------------*/
				
				double dcq_var=0; double dcq_var_MT=0; 	double convt_mmbtu_to_mt = 51.5;
				queryString = "SELECT NVL(dcq,'0') "
						+ " FROM DLNG_SN_DCQ_DTL A WHERE A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
						+ " AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.FLAG='Y' "
						+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_DCQ_DTL B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
						+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.FLAG='Y') ";
//						////System.out.println("DLNG_SN_DCQ_DTL: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
//							dcq_var= rset.getDouble(1);
							dcq_var = 0;
						}
						if(dcq_var>0)
						{
							daily_Buyer_Nom_Dcq.add(nf.format(dcq_var));	
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((dcq_var*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += dcq_var;
							dcq_var_MT= (dcq_var/convt_mmbtu_to_mt);
							daily_Buyer_Nom_Dcq_Mt.add(nf.format(dcq_var_MT));
						}
						else
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));	
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += Double.parseDouble(rset1.getString(6));
							daily_Buyer_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(10))));
						}

				VBuyer_Fcc_Flag.add(rset1.getString(11)==null?"N":rset1.getString(11)); //SB20200718
				VBuyer_Delv_Base.add(rset1.getString(12)==null?"":rset1.getString(12)); //SB20200804
				String ALLOCATED_QTY = "0";
				String ALLOCATED_QTY1 = "0";
				
				String CONTRACTED_QTY =nf5.format(Double.parseDouble(rset1.getString(8)) + (Double.parseDouble(rset1.getString(9))));
				
				daily_Buyer_regas_cargo_boe_no.add("");
				daily_Buyer_regas_cargo_boe_dt.add("");
			
					queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
							+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
							+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ "AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
					//////System.out.println("--Fetching Data--"+queryString);
					rset = stmt.executeQuery(queryString);
								
					if(rset.next())
					{
						if(rset.getString(1)!=null)
						{
							if(!rset.getString(1).trim().equals(""))
							{
								ALLOCATED_QTY = rset.getString(1).trim();
								//////System.out.println("rset from line no 313*******"+ALLOCATED_QTY);
							}
						}
					}
					
				queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
						+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
						+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
						+ "AND REV_NO = ("
						+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
						+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
						+ "AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
				rset = stmt.executeQuery(queryString);
								
				if(rset.next())
				{
					if(rset.getString(1)!=null)
					{
						if(!rset.getString(1).trim().equals(""))
						{
							ALLOCATED_QTY1 = rset.getString(1).trim();
						} 
					} 
				} 
				
				ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
				
				daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
				
				queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
						   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
						   	+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Abbr.add(rset3.getString(1));
					//////System.out.println("daily_Buyer_Nom_Abbr from line no 350 "+daily_Buyer_Nom_Abbr);
				}
				else
				{
					daily_Buyer_Nom_Abbr.add(" ");
				}
				
				
				String NomId="";
				queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
						+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_NOM WHERE "
						+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
						+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
						+ "AND REV_NO = ("
						+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
						+ "MAPPING_ID='"+map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
						+ "AND NOM_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
				
//				////System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (CN) = "+queryString5);
				rset5 = stmt5.executeQuery(queryString5);
				if(rset5.next())
				{
					daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
					daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
					daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
					daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
					//daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
					//daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
				//	total_mmbtu += Double.parseDouble(rset5.getString(5));
				//	total_scm += Double.parseDouble(rset5.getString(6));
					NomId=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);	
					////////////////////WEEKLY Total Calculation/////////////////////
					queryString7 = "SELECT SUM(NVL(DAY_VOL,'0')),SUM(NVL(TOT_ENE,'0')) FROM DLNG_WEEKLY_NOM WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
							+ "NOM_DT>=TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') AND NOM_DT<=TO_DATE('"+nomModalDates.lastElement()+"','DD/MM/YYYY') AND NOM_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
							+ "MAPPING_ID='"+map_id+"' AND NOM_DT>=TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') AND NOM_DT<=TO_DATE('"+nomModalDates.lastElement()+"','DD/MM/YYYY') "
							+ "AND NOM_ID='"+map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
					
				//////System.out.println("SUM-QTY: DLNG_WEEKLY_NOM: "+queryString7);
					rset7 = stmt5.executeQuery(queryString7);
					if(rset7.next())
					{
						VWeekly_Buyer_Nom_Qty_Mmbtu.add(rset7.getString(1)==""?"0":rset7.getString(1));
						VWeekly_Buyer_Nom_Qty_scm.add(rset7.getString(2)==""?"0":rset7.getString(2));
						
						daily_Buyer_Nom_Qty_Mmbtu.add(rset7.getString(1)=="0"?"":rset7.getString(1));
						daily_Buyer_Nom_Qty_Scm.add(rset7.getString(2)==""?"0":rset7.getString(2));
						total_mmbtu += Double.parseDouble(rset7.getString(1)=="0"?"":rset7.getString(1));
						total_scm += Double.parseDouble(rset7.getString(2)==""?"0":rset7.getString(2));
					}
					else
					{
						VWeekly_Buyer_Nom_Qty_Mmbtu.add("0");
						VWeekly_Buyer_Nom_Qty_scm.add("0");
						daily_Buyer_Nom_Qty_Mmbtu.add("0");
						daily_Buyer_Nom_Qty_Scm.add("0");
					}
					//////System.out.println("SUM-QTY: VWeekly_Buyer_Nom_Qty_Mmbtu: "+VWeekly_Buyer_Nom_Qty_Mmbtu);
					/////////^^^/////////////////////////////////////////////////////////
				}
				else
				{
					daily_Buyer_Gen_Day_With_Rev_No.add("");
					daily_Buyer_Rev_No.add("0");
					daily_Buyer_Gen_Day_Time.add("");
					daily_Buyer_Nom_Plant_Seq_No.add("0");
					daily_Buyer_Nom_Qty_Mmbtu.add("");
					daily_Buyer_Nom_Qty_Scm.add("");
				}

				////Check for Invoice generated////////
				int NomIdCnt=0;
				queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID='"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";							
				//////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
				rset5 = stmt5.executeQuery(queryString5);
				if(rset5.next())
					NomIdCnt=rset5.getInt(1);
				if(NomIdCnt>0)
					VBuyer_Inv_Flag.add("Y");
				else
					VBuyer_Inv_Flag.add("N");
			}
		}
	}//end of while loop

	/* **********************for LoA Contracts***************************** Hiren_20200428*/

	queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
			+ " ,A.FCC_FLAG, LOA_BASE "
			+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
			+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
		//SB20200705			+ "AND A.FCC_FLAG='Y' "
			+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
			+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
		//////System.out.println("Fetching LoA COntracts.."+queryString1);
	rset1 = stmt1.executeQuery(queryString1);
	while(rset1.next())
	{
		//mappaing_id = customer_cd-tender_no-0-loa_no-loa_rev_no
		String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4);
//		////System.out.println("rset1.getString(1)*******"+rset1.getString(1));
		if(!rset1.getString(1).equals("0"))
		{
			/*///////////////////////////SB20200801/////////////////////////////////
			queryString2 = "SELECT TENDER_BASE FROM DLNG_TENDER_MST " +
					   "WHERE customer_cd="+rset1.getString(1)+" AND TENDER_NO="+rset1.getString(2)+" AND FLAG='Y' ";
//			////System.out.println("LoA Plant No****"+queryString2);
			rset2 = stmt2.executeQuery(queryString2);
			while(rset2.next())
			{
				VBuyer_Delv_Base.add(rset2.getString(1));
			}
			*////////////////////^^SB20200801/////////////////////////
			queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
					   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.TENDER_NO="+rset1.getString(2)+" " +
					   "AND A.LOA_NO="+rset1.getString(3)+" AND LOA_REV_NO = '"+rset1.getString(4)+"'";
//			////System.out.println("LoA Plant No****"+queryString2);
			rset2 = stmt2.executeQuery(queryString2);
			while(rset2.next())
			{
				daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
				
				queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//				////System.out.println("LoA Plant Name = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
					//////System.out.println("daily_Buyer_Nom_Plant_Abbr line no 265*******"+rset3.getString(1));
				}
				else
				{
					daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
				}
				
				daily_Buyer_Nom_Mapping_Id.add(map_id);
//				////System.out.println("daily_Buyer_Nom_Mapping_Id line no 273*******"+rset1.getString(3));
				daily_Buyer_Nom_Fgsa_No.add(rset1.getString(2));
				daily_Buyer_Nom_Fgsa_Rev_No.add("0");
				daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
				daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
				
				daily_Buyer_Nom_Cd.add(rset1.getString(1));
				//////System.out.println("daily_Buyer_Nom_Cd line no 279*******"+daily_Buyer_Nom_Cd);
				
				daily_Buyer_Nom_Type.add("L");
				daily_Buyer_Nom_Contract_Type.add("LoA");
				
				daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(5))));	
				String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(6))<=100?100:Double.parseDouble(rset1.getString(6)));
				daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(5))*Double.parseDouble(temp_mdcq_percentage))/100));
				total_dcq += Double.parseDouble(rset1.getString(5));
				
				daily_Buyer_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(9))));
				VBuyer_Fcc_Flag.add(rset1.getString(10)==null?"N":rset1.getString(10)); //SB20200718
				VBuyer_Delv_Base.add(rset1.getString(11)==null?"":rset1.getString(11)); //SB20200804
				String ALLOCATED_QTY = "0";
				String ALLOCATED_QTY1 = "0";
				
				String CONTRACTED_QTY =nf5.format(Double.parseDouble(rset1.getString(7)) + (Double.parseDouble(rset1.getString(8))));
				
//				daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
//				daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
				daily_Buyer_regas_cargo_boe_no.add("");
				daily_Buyer_regas_cargo_boe_dt.add("");
			
					queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
							+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
							+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ "AND SCH_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
//					////System.out.println("--Fetching Data--"+queryString);
					rset = stmt.executeQuery(queryString);
								
					if(rset.next())
					{
						if(rset.getString(1)!=null)
						{
							if(!rset.getString(1).trim().equals(""))
							{
								ALLOCATED_QTY = rset.getString(1).trim();
								//////System.out.println("rset from line no 313*******"+ALLOCATED_QTY);
							}
						}
					}
					
				queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
						+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
						+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
						+ "AND REV_NO = ("
						+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
						+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
						+ "AND SCH_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
				rset = stmt.executeQuery(queryString);
								
				if(rset.next())
				{
					if(rset.getString(1)!=null)
					{
						if(!rset.getString(1).trim().equals(""))
						{
							ALLOCATED_QTY1 = rset.getString(1).trim();
						} 
					} 
				} 
				
				ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
				
				daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
				
				queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
						   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
						   	+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Abbr.add(rset3.getString(1));
					//////System.out.println("daily_Buyer_Nom_Abbr from line no 350 "+daily_Buyer_Nom_Abbr);
				}
				else
				{
					daily_Buyer_Nom_Abbr.add(" ");
				}
				String NomId="";
				queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
						+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),NOM_BASE_FLAG FROM DLNG_WEEKLY_NOM WHERE "
						+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"' AND "
						+ "NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND NOM_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
						+ "AND REV_NO = ("
						+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
						+ "MAPPING_ID='"+map_id+"' AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
						+ "AND NOM_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
				
				//////System.out.println("Fetch Query FROM DLNG_WEEKLY_NOM Table (CN) = "+queryString5);
				rset5 = stmt5.executeQuery(queryString5);
				if(rset5.next())
				{
					daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
					daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
					daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
					daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
					//daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
				///	total_mmbtu += Double.parseDouble(rset5.getString(5));
				///	total_scm += Double.parseDouble(rset5.getString(6));
					NomId=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);
					//daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
					
					queryString6 = "SELECT SUM(DAY_VOL) FROM DLNG_WEEKLY_NOM WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"'  "
							+ "AND NOM_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
							+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
							+ "AND NOM_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"'  "
							+ "AND MAPPING_ID='"+map_id+"' "
							+ "AND NOM_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
							+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
							+ "AND NOM_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
					//////System.out.println("Fetch SUM of DAY_VOL Query FROM DLNG_WEEKLY_NOM Table  = "+queryString6);
					rset6 = stmt6.executeQuery(queryString6);
					if(rset6.next())
					{
						daily_Buyer_Nom_Qty_Mmbtu.add(rset6.getString(1)=="0"?"":rset6.getString(1));
						total_mmbtu += Double.parseDouble(rset6.getString(1)==""?"0":rset6.getString(1));
					}
					
					
					queryString7 = "SELECT SUM(TOT_ENE) FROM DLNG_WEEKLY_NOM WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID='"+map_id+"'  "
							+ "AND NOM_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
							+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
							+ "AND NOM_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"'  "
							+ "AND MAPPING_ID='"+map_id+"' "
							+ "AND NOM_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
							+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
							+ "AND NOM_ID='"+map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
					//////System.out.println("Fetch SUM of TOT_ENE Query FROM DLNG_WEEKLY_NOM Table  = "+queryString6);
					rset7 = stmt7.executeQuery(queryString7);
					if(rset7.next())
					{
						daily_Buyer_Nom_Qty_Scm.add(rset7.getString(1)=="0"?"":rset7.getString(1));
						total_scm += Double.parseDouble(rset7.getString(1)==""?"0":rset7.getString(1));
					}
				}
				else
				{
					daily_Buyer_Gen_Day_With_Rev_No.add("");
					daily_Buyer_Rev_No.add("0");
					daily_Buyer_Gen_Day_Time.add("");
					daily_Buyer_Nom_Plant_Seq_No.add("0");
					daily_Buyer_Nom_Qty_Scm.add("");
					daily_Buyer_Nom_Qty_Mmbtu.add("");
				}

			////Check for Invoice generated////////
				int NomIdCnt=0;
				queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID='"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";							
//				////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
				rset5 = stmt5.executeQuery(queryString5);
				if(rset5.next())
					NomIdCnt=rset5.getInt(1);
				if(NomIdCnt>0)
					VBuyer_Inv_Flag.add("Y");
				else
					VBuyer_Inv_Flag.add("N");
			}
		}
	}
	
final_daily_dcq += total_dcq;
final_daily_mmbtu += total_mmbtu;
final_daily_scm += total_scm;

if(final_daily_mmbtu>0)
{
	daily_Total_Mmbtu = nf.format(final_daily_mmbtu);
}
else
{
	daily_Total_Mmbtu = "";
}

if(final_daily_scm>0)
{
	daily_Total_Scm = nf.format(final_daily_scm);
}
else
{
	daily_Total_Scm = "";
}

daily_Total_Dcq = nf.format(final_daily_dcq);

queryString = "SELECT DISTINCT CUSTOMER_CD FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
rset = stmt.executeQuery(queryString);
while(rset.next())
{
	CUST_CD.add(rset.getString(1)==null?"0":rset.getString(1));
	//////System.out.println("CUST_CD from line no 420 "+CUST_CD);
	
}

for(int i =0;i<daily_Buyer_Nom_Contract_Type.size();i++)
{
	qty_nomination.add(daily_Buyer_Nom_Balance_Qty.elementAt(i)+"");
}

} catch (Exception e) {

}
}

private void fetchWeeklySchDetails() throws SQLException,IOException
{
	methodName = "fetchWeeklySchDetails()";
	try {
			
			//////System.out.println("gas_date*****"+gas_date);
			String weekEndDt="";
			String weekEndDtSql = "Select TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY')+6,'dd/mm/yyyy') from dual";
//			////System.out.println("weekEndDtSql**"+weekEndDtSql);
			rset = stmt.executeQuery(weekEndDtSql);
			if(rset.next()) {
				weekEndDt = rset.getString(1)==null?"":rset.getString(1);
			}
			//////System.out.println("weekEndDt*******"+weekEndDt);
			
			double final_daily_buyer_mmbtu = 0;
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			double total_buyer_mmbtu = 0;
			double total_dcq = 0;
			double total_mmbtu = 0;
			double total_scm = 0;
			
			//Fetch Tank's Id..
			Vector fsru_tankId = new Vector();
			queryString1 = "SELECT DISTINCT TANK_ID FROM DLNG_TANK_MST WHERE UPPER(TANK_TYPE)='FSRU' ";
			//////System.out.println("DLNG_TANK_MST: "+queryString1);
			rset = stmt.executeQuery(queryString1);
			while(rset.next()) {
				fsru_tankId.add(rset.getString(1));
			}
			if(fsru_tankId.size()>0) 
			{
				//Tank's effective date's transactions ....
				double tank_vol = 0;
				for(int i=0;i<fsru_tankId.size();i++) 
				{
					queryString1 = "SELECT NVL(EFF_VOL_AVL,'0') FROM DLNG_TANK_VOL_DTL A WHERE TANK_ID = '"+fsru_tankId.elementAt(i)+"' "
							//+ " AND EFF_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ "AND SEQ_NO = (SELECT MAX(SEQ_NO) FROM DLNG_TANK_VOL_DTL B WHERE A.TANK_ID=B.TANK_ID AND A.EFF_DT=B.EFF_DT)  "; //AND SEQ_NO!='0'
					//////System.out.println("Fetching fsru vol avail on effIdt...."+queryString1);
					rset = stmt.executeQuery(queryString1);
					if(rset.next()) {
						tank_vol += rset.getDouble(1);
						//////System.out.println("line no 660 tank_vol...."+tank_vol);
					} else {
						queryString1 = "SELECT NVL(TANK_VOL,'0') FROM DLNG_TANK_MST A WHERE UPPER(TANK_TYPE)='FSRU' AND EFF_DT = (SELECT MAX(EFF_DT) FROM DLNG_TANK_MST B "
								+ "WHERE A.TANK_ID=B.TANK_ID AND A.TANK_TYPE=B.TANK_TYPE AND A.EFF_DT=B.EFF_DT) AND A.TANK_ID='"+fsru_tankId.elementAt(i)+"' ";
						//////System.out.println("Fetching fsru vol avail on effIdt...."+queryString1);
						rset = stmt.executeQuery(queryString1);
						if(rset.next()) {
							tank_vol += rset.getDouble(1);
						} 
					}
				}
				fsru_tank_vol = tank_vol;
			}
			queryString1 = "SELECT DISTINCT TANK_ID,TANK_VOL, TANK_VOL_AVL FROM DLNG_TANK_MST WHERE UPPER(TANK_TYPE)='INT' ";
			rset = stmt.executeQuery(queryString1);
			if(rset.next()) {
				int_tankId = rset.getString(1);
				int_tankCapacity = rset.getDouble(2)*conversion_factor_from_m3_to_mmbtu;
				int_tankCapacityM3 = rset.getDouble(2); //SB20181219
				int_tankVolAvl = rset.getDouble(3)*conversion_factor_from_m3_to_mmbtu;
				int_tankVolAvlM3 = rset.getDouble(3); //SB20181219
				//////System.out.println("line no 695...int_tankId "+int_tankId);
			}
			
			if(!int_tankId.equals("")) 
			{
				queryString1 = "SELECT NVL(EFF_VOL_AVL,'0') FROM DLNG_TANK_VOL_DTL A WHERE TANK_ID = '"+int_tankId+"' "
				+ "AND SEQ_NO = (SELECT MAX(SEQ_NO) FROM DLNG_TANK_VOL_DTL B WHERE SEQ_NO!='0' " +
				"AND EFF_DT <= TO_DATE('"+gas_date+"','DD/MM/YYYY') )";
				//////System.out.println("Fetching int vol avail on effIdt...."+queryString1);
				rset = stmt.executeQuery(queryString1);
				if(rset.next()) {
					int_tank_vol = rset.getDouble(1);
					int_tank_ene = Double.parseDouble(nf2.format(int_tank_vol * conversion_factor_from_m3_to_mmbtu)); //to convert m3 volume to mmbtu
				} else {
					queryString1 = "SELECT NVL(TANK_VOL,'0') FROM DLNG_TANK_MST A WHERE UPPER(TANK_TYPE)='INT' AND EFF_DT = (SELECT MAX(EFF_DT) FROM DLNG_TANK_MST B "
							+ "WHERE A.TANK_ID=B.TANK_ID AND A.TANK_TYPE=B.TANK_TYPE AND A.EFF_DT=B.EFF_DT) AND TANK_ID = '"+int_tankId+"' ";
					//////System.out.println("queryString1***"+queryString1);
					rset = stmt.executeQuery(queryString1);
					if(rset.next()) {
						int_tank_vol = rset.getDouble(1);
						int_tank_ene =Double.parseDouble(nf2.format(int_tank_vol * conversion_factor_from_m3_to_mmbtu)); //to convert m3 volume to mmbtu
					} 
				}
			}
			
			/* **************************** for SN based contract************************** */
			
			queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0'),START_DT  "
					+ " ,A.FCC_FLAG, SN_BASE,TO_CHAR(SIGNING_DT,'DD/MM/YYYY') "
					+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
					+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
				//SB20200702			+ "AND A.FCC_FLAG='Y' "
					+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
					+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y')";
//				//System.out.println("Fetching SN COntracts.."+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
					String temp_map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-%-"+rset1.getString(4)+"-%";
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
								   " WHERE A.customer_cd="+rset1.getString(1)+" AND A.flsa_no="+rset1.getString(2)+" " +
								   " AND A.sn_no="+rset1.getString(4)+" ";
//								   + " and SN_REV_NO='"+rset1.getString(5)+"'"
//								   + " AND FLSA_REV_NO = '"+rset1.getString(3)+"'";
						
//						//System.out.println("SELECT: DLNG_SN_PLANT_MST: "+queryString1);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							queryString4 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
									+ " NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_NOM WHERE "
									+ " PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
									+ " NOM_DT between TO_DATE('"+gas_date+"','DD/MM/YYYY') and TO_DATE('"+weekEndDt+"','DD/MM/YYYY') "
									+ " AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
									+ " AND REV_NO = ("
									+ " SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
									+ " MAPPING_ID like '"+temp_map_id+"' AND "
									+ " NOM_DT between TO_DATE('"+gas_date+"','DD/MM/YYYY') and TO_DATE('"+weekEndDt+"','DD/MM/YYYY') "
//									+ " AND NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
									+ " AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S')"
									+ " AND CONTRACT_TYPE='S' ";
							//System.out.println("SCH: DLNG_WEEKLY_NOM****"+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
//								//System.out.println("****innnnn****");
								daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
								
								queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//								//System.out.println("queryString3****"+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Abbr.add("");
								}
								
								daily_Seller_Gen_Day_With_Rev_No.add(rset4.getString(2)==null?"0":rset4.getString(2));
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
									//////System.out.println("daily_Buyer_Nom_Plant_Seq_Abbr from line no 802 "+daily_Buyer_Nom_Plant_Seq_Abbr);
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Mapping_Id.add(map_id);
								daily_Seller_Nom_Fgsa_No.add(rset1.getString(2));
								daily_Seller_Nom_Fgsa_Rev_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_No.add(rset1.getString(4));
								daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(5));
								
								daily_Seller_Nom_Cd.add(rset1.getString(1));
								//////System.out.println("daily_Seller_Nom_Cd from line no 815 "+daily_Seller_Nom_Cd);
								
								daily_Seller_Nom_Type.add("S");
								daily_Seller_Nom_Contract_Type.add("SN");
								
								String NomIds="";
								NomIds=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);	
								VWeekly_Seller_Nom_ID.add(NomIds);
							
								/*------------------Variable DCQ---------------------*/
								
								double dcq_var=0; double dcq_var_MT=0; 	double convt_mmbtu_to_mt = 51.5;
								queryString = "SELECT NVL(dcq,'0') "
										+ " FROM DLNG_SN_DCQ_DTL A WHERE A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
										+ " AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.FLAG='Y' "
										+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_DCQ_DTL B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
										+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.FLAG='Y') ";
//								////System.out.println("DLNG_SN_DCQ_DTL: "+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
//									dcq_var= rset.getDouble(1);
									dcq_var = 0;
								}
								if(dcq_var>0)
								{
									daily_Seller_Nom_Dcq.add(nf.format(dcq_var));	
								//	daily_Buyer_Nom_Mdcq_Qty.add(nf.format((dcq_var*Double.parseDouble(temp_mdcq_percentage))/100));
									total_dcq += dcq_var;
									///dcq_var_MT= (dcq_var/Double.parseDouble(rset1.getString(6)))*Double.parseDouble(rset1.getString(10));
									dcq_var_MT= (dcq_var/convt_mmbtu_to_mt);
									daily_Seller_Nom_Dcq_Mt.add(nf.format(dcq_var_MT));
								}
								else
								{
									daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));	
									//daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
									total_dcq += Double.parseDouble(rset1.getString(6));
									daily_Seller_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(10))));
								}
								VBuyer_Fcc_Flag.add(rset1.getString(12)==null?"N":rset1.getString(12)); //SB20200718
								VBuyer_Delv_Base.add(rset1.getString(13)==null?"":rset1.getString(13)); //SB20200804
								VContSignDt.add(rset1.getString(14));
								//SB20200718		daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));	
								//SB20200718		total_dcq += Double.parseDouble(rset1.getString(6));
								daily_Seller_regas_cargo_boe_no.add("");
								daily_Seller_regas_cargo_boe_dt.add("");
								//SB20200718	daily_Seller_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(10))));
						
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
					   			   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
					   			   		+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
								rset5 = stmt5.executeQuery(queryString5);
								////System.out.println("queryString5*******"+queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
//									////System.out.println("daily_Seller_Nom_Abbr from line no 833 "+daily_Seller_Nom_Abbr);
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
								}
								
								double SchQty=0; //SB20200723
								
								 String NomId="";
								 queryString6 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''),NVL(SCH_ID,''),"
									 + " NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_SCH WHERE "
									 + " PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
									 + "  SCH_DT between TO_DATE('"+gas_date+"','DD/MM/YYYY') and TO_DATE('"+weekEndDt+"','DD/MM/YYYY') "
									 + " AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
									 + " AND REV_NO = ("
									 + " SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
									 + " MAPPING_ID like '"+temp_map_id+"'"
									 + " AND SCH_DT between TO_DATE('"+gas_date+"','DD/MM/YYYY') and TO_DATE('"+weekEndDt+"','DD/MM/YYYY') "
									 + " AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' AND CONTRACT_TYPE='S')"
									 + " AND CONTRACT_TYPE='S'";
//								  ////System.out.println("queryString60***"+queryString6);
								  rset6 = stmt6.executeQuery(queryString6);
								  if(rset6.next())
								  {
										daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
										daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
										daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
										SchQty=Double.parseDouble(rset6.getString(5)==null?"0":rset6.getString(5)); //SB20200723
										daily_Sch_Mapping_Id.add(rset6.getString(4)==null?"":rset6.getString(4)); //SB20181229
										daily_Seller_Rev_No.add(rset6.getString(2)==null?"0":rset6.getString(2));
										NomId=temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);
										queryString7 = "SELECT SUM(DAY_VOL) FROM DLNG_WEEKLY_SCH WHERE "
												+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' "
												+ "AND SCH_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
												+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ "AND REV_NO = ("
												+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
												+ "MAPPING_ID like '"+temp_map_id+"' "
												+ "AND SCH_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
												+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='S')"
												+ " AND CONTRACT_TYPE='S' ";
										
//										////System.out.println("Fetch SUM of DAY_VOL Query FROM DLNG_WEEKLY_NOM Table  = "+queryString6);
										rset7 = stmt6.executeQuery(queryString7);
										if(rset7.next())
										{
											daily_Seller_Nom_Qty_Mmbtu.add(rset7.getString(1)=="0"?"":rset7.getString(1));
											//total_mmbtu += Double.parseDouble(rset7.getString(1));
										}
										
										queryString7 = "SELECT SUM(TOT_ENE) FROM DLNG_WEEKLY_SCH WHERE "
												+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' "
												+ "AND SCH_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
												+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ "AND REV_NO = ("
												+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
												+ "MAPPING_ID like '"+temp_map_id+"' "
												+ "AND SCH_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
												+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='S') "
												+ "  AND CONTRACT_TYPE='S' ";

										////System.out.println("Fetch SUM of TOT_ENE Query FROM DLNG_WEEKLY_NOM Table  = "+queryString6);
										rset7 = stmt7.executeQuery(queryString7);
										if(rset7.next())
										{
											daily_Seller_Nom_Qty_Scm.add(rset7.getString(1)=="0"?"":rset7.getString(1));
											//total_scm += Double.parseDouble(rset7.getString(1));
										}
								   }
								   else
								   {
										/*daily_Seller_Gen_Day_With_Rev_No.add("");
										daily_Seller_Gen_Day_Time.add("");
										daily_Seller_Nom_Plant_Seq_No.add("0");
										daily_Seller_Nom_Qty_Mmbtu.add("0");
										daily_Seller_Nom_Qty_Scm.add("0");
										daily_Sch_Mapping_Id.add("");
										daily_Seller_Rev_No.add("");*/
								   }
										
								   /*-----------Check for Invoice generated----*/
										
									int NomIdCnt=0;
									queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID like '"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')  AND CONTRACT_TYPE='S' ";							
//									////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
									rset5 = stmt5.executeQuery(queryString5);
									if(rset5.next())
										NomIdCnt=rset5.getInt(1);
									if(NomIdCnt>0)
										VBuyer_Inv_Flag.add("Y");
									else
										VBuyer_Inv_Flag.add("N");
							
									double AllocQty=0; //SB20200723
									/*queryString = "select SUM(QTY_MMBTU) from DLNG_DAILY_ALLOCATION_DTL where GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ " AND CUSTOMER_CD='"+rset1.getString(1)+"' and SN_REV_NO = '"+rset1.getString(5)+"'" + 
											  " AND SN_NO = '"+rset1.getString(4)+"' and FGSA_NO='"+rset1.getString(2)+"' "
											+ " AND FGSA_REV_NO='"+rset1.getString(3)+"' and MAPPING_ID='"+map_id+"' and PLANT_SEQ_NO='"+rset2.getString(1)+"'"
											+ " AND NOM_REV_NO='"+rset4.getString(2)+"' " ;*/
									
									queryString = "select SUM(ENTRY_TOT_ENE) from DLNG_ALLOC_MST where"
											+ " GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ " AND MAPPING_ID like '"+temp_map_id+"' "
											+ " AND ALLOC_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
											+ " AND NOM_REV_NO='"+rset4.getString(2)+"'  AND CONTRACT_TYPE='S' " ;
									
									//////System.out.println("queryString****"+queryString);
									rset8 = stmt7.executeQuery(queryString);
									if(rset8.next()) {
										AllocQty=Double.parseDouble(rset8.getString(1)==null?"0":rset8.getString(1)); //SB20200723
										allocated_qty_mmbtu.add(rset8.getString(1)==null?"0":rset8.getString(1));
									}else {
										allocated_qty_mmbtu.add("0");
									}
									balance_qty_mmbtu.add(nf2.format(AllocQty));
							 }
						}
					}
				}// end of sn while loop
				//System.out.println("SN: daily_Seller_Nom_Qty_Mmbtu : "+daily_Seller_Nom_Qty_Mmbtu);
				
				/* **********************for LoA Contracts***************************** Hiren_20200428*/
				
				queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
						+ " ,A.FCC_FLAG, LOA_BASE,TO_CHAR(SIGNING_DT,'DD/MM/YYYY') "
						+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
						+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
					//SB20200705			+ "AND A.FCC_FLAG='Y' "
						+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
						+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
//					//System.out.println("STEP-1: LOA: DLNG_LOA_MST: "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					//mappaing_id = customer_cd-tender_no-0-loa_no-loa_rev_no
					String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4);
					String temp_map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-%-"+rset1.getString(3)+"-%";
						
						if(!rset1.getString(1).equals("0"))
						{
							queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.TENDER_NO="+rset1.getString(2)+" " +
									   "AND A.LOA_NO="+rset1.getString(3)+" AND LOA_REV_NO = '"+rset1.getString(4)+"'";
//							//System.out.println("STEP-2: LoA Plant No****"+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								queryString4 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
										+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_NOM WHERE "
										+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
//										+ " NOM_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
										+ " NOM_DT between TO_DATE('"+gas_date+"','DD/MM/YYYY') and TO_DATE('"+weekEndDt+"','DD/MM/YYYY') "
										+ " AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
										+ "AND REV_NO = ("
										+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
										+ "MAPPING_ID like '"+temp_map_id+"' AND "
										+ " NOM_DT between TO_DATE('"+gas_date+"','DD/MM/YYYY') and TO_DATE('"+weekEndDt+"','DD/MM/YYYY') "
										+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='L') "
										+ "  AND CONTRACT_TYPE='L'";
//								//System.out.println("LOA: Nom Details****"+queryString4);
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									
									daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
									
									queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
												   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
												   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
												   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
												   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
//									//System.out.println("queryString3 LoA***"+queryString3);
									rset3 = stmt3.executeQuery(queryString3);
									if(rset3.next())
									{
										daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
									}
									else
									{
										daily_Seller_Nom_Plant_Abbr.add("");
									}
									
//									//System.out.println("rset4.getString(5)*****"+rset4.getString(5));
									daily_Seller_Gen_Day_With_Rev_No.add(rset4.getString(2)==null?"0":rset4.getString(2));
									daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
									daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
									total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
									
									queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
												   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
												   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
												   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
												   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
									rset3 = stmt3.executeQuery(queryString3);
									if(rset3.next())
									{
										daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
										//////System.out.println("daily_Buyer_Nom_Plant_Seq_Abbr from line no 802 "+daily_Buyer_Nom_Plant_Seq_Abbr);
									}
									else
									{
										daily_Seller_Nom_Plant_Seq_Abbr.add("");
									}
									
									daily_Seller_Nom_Mapping_Id.add(map_id);
									daily_Seller_Nom_Fgsa_No.add(rset1.getString(2));
									daily_Seller_Nom_Fgsa_Rev_No.add("0"); //SB20200706
									//SB20200706		daily_Seller_Nom_Sn_No.add(rset1.getString(4));
									//SB20200706		daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(5));
									daily_Seller_Nom_Sn_No.add(rset1.getString(3));
									daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(4));
									
									daily_Seller_Nom_Cd.add(rset1.getString(1));
									//////System.out.println("daily_Seller_Nom_Cd from line no 815 "+daily_Seller_Nom_Cd);
									
									daily_Seller_Nom_Type.add("L");
									daily_Seller_Nom_Contract_Type.add("LoA");
									
									daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(5))));	
									total_dcq += Double.parseDouble(rset1.getString(5));
									
									String NomIds="";
									NomIds=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);	
									VWeekly_Seller_Nom_ID.add(NomIds);
									
									daily_Seller_regas_cargo_boe_no.add("");
									daily_Seller_regas_cargo_boe_dt.add("");
									daily_Seller_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(9))));
									VBuyer_Fcc_Flag.add(rset1.getString(10)==null?"N":rset1.getString(10)); //SB20200718
									VBuyer_Delv_Base.add(rset1.getString(11)==null?"":rset1.getString(11)); //SB20200804
									VContSignDt.add(rset1.getString(12));
									queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
								   			   		+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY')) ";
									rset5 = stmt5.executeQuery(queryString5);
									if(rset5.next())
									{
										daily_Seller_Nom_Abbr.add(rset5.getString(1));
										//////System.out.println("daily_Seller_Nom_Abbr from line no 833 "+daily_Seller_Nom_Abbr);
									}
									else
									{
										daily_Seller_Nom_Abbr.add("");
									}
									String NomId="";
									double SchQty=0; //SB20200723
									queryString6 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''),NVL(SCH_ID,''),"
											+ " NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_SCH WHERE "
											+ " PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
											+ " SCH_DT between TO_DATE('"+gas_date+"','DD/MM/YYYY') and TO_DATE('"+weekEndDt+"','DD/MM/YYYY') "
											+ " AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
											+ " AND REV_NO = ("
											+ " SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
											+ " MAPPING_ID like '"+temp_map_id+"' AND "
											+ " SCH_DT between TO_DATE('"+gas_date+"','DD/MM/YYYY') and TO_DATE('"+weekEndDt+"','DD/MM/YYYY') "
											+ " AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='L') "
											+ "  AND CONTRACT_TYPE='L'";
//									////System.out.println("STEP-3: DLNG_WEEKLY_SCH: "+queryString6);
									rset6 = stmt6.executeQuery(queryString6);
									if(rset6.next())
									{
										daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
										daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
										daily_Seller_Nom_Plant_Seq_No.add(rset2.getString(1));
										SchQty=Double.parseDouble(rset6.getString(5)==null?"0":rset6.getString(5)); //SB20200723
										//daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
										//daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
										total_mmbtu += Double.parseDouble(rset6.getString(5));
										total_scm += Double.parseDouble(rset6.getString(6));
										daily_Sch_Mapping_Id.add(rset6.getString(4)==null?"":rset6.getString(4)); //SB20181229
										daily_Seller_Rev_No.add(rset6.getString(2)==null?"0":rset6.getString(2));
										NomId=temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1);
										
										
										queryString6 = "SELECT SUM(DAY_VOL) FROM DLNG_WEEKLY_SCH WHERE "
												+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' "
												+ "AND SCH_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
												+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ "AND REV_NO = ("
												+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' "
											    + "AND MAPPING_ID like '"+temp_map_id+"' "
												+ "AND SCH_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
												+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='L')"
												+ " AND CONTRACT_TYPE='L' ";
										
//										////System.out.println("Fetch SUM of DAY_VOL Query FROM DLNG_WEEKLY_NOM Table  = "+queryString6);
										rset6 = stmt6.executeQuery(queryString6);
										if(rset6.next())
										{
											daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(1)==null?"0":rset6.getString(1));
										}
										
										queryString7 = "SELECT SUM(TOT_ENE) FROM DLNG_WEEKLY_SCH WHERE "
												+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' "
												+ "AND SCH_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
												+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
												+ "AND REV_NO = ("
												+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' "
												+ "AND MAPPING_ID like '"+temp_map_id+"' "
												+ "AND SCH_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
												+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
												+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"'  AND CONTRACT_TYPE='L') "
												+ "  AND CONTRACT_TYPE='L'";

//										////System.out.println("Fetch SUM of TOT_ENE Query FROM DLNG_WEEKLY_NOM Table  = "+queryString6);
										rset7 = stmt7.executeQuery(queryString7);
										if(rset7.next())
										{
											daily_Seller_Nom_Qty_Scm.add(rset7.getString(1)=="0"?"":rset7.getString(1));
										}
									}
									else
									{
										/*daily_Seller_Gen_Day_With_Rev_No.add("");
										daily_Seller_Gen_Day_Time.add("");
										daily_Seller_Nom_Plant_Seq_No.add("0");
										daily_Seller_Nom_Qty_Mmbtu.add("0");
										daily_Seller_Nom_Qty_Scm.add("0");
										daily_Sch_Mapping_Id.add("");
										daily_Seller_Rev_No.add("");*/
									}
								    
									/*----------Check for Invoice generated--------*/
									
									int NomIdCnt=0;
									queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID like '"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')  AND CONTRACT_TYPE='L' ";							
//									////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
									rset5 = stmt5.executeQuery(queryString5);
									if(rset5.next())
										NomIdCnt=rset5.getInt(1);
									if(NomIdCnt>0)
										VBuyer_Inv_Flag.add("Y");
									else
										VBuyer_Inv_Flag.add("N");
									
									double AllocQty=0; //SB20200723
									queryString = "select SUM(ENTRY_TOT_ENE) from DLNG_ALLOC_MST where"
											+ " GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
											+ " AND MAPPING_ID like '"+temp_map_id+"' "
											+ " AND ALLOC_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
											+ " AND NOM_REV_NO='"+rset4.getString(2)+"'  AND CONTRACT_TYPE='L' " ;
//									////System.out.println("STEP-4: DLNG_ALLOC_MST: "+queryString6);
									//////System.out.println("queryString****"+queryString);
									rset8 = stmt7.executeQuery(queryString);
									if(rset8.next()) {
										AllocQty=Double.parseDouble(rset8.getString(1)==null?"0":rset8.getString(1)); //SB20200723
										allocated_qty_mmbtu.add(rset8.getString(1)==null?"0":rset8.getString(1));
									}else {
										allocated_qty_mmbtu.add("0");
									}
									balance_qty_mmbtu.add(nf2.format(AllocQty)); //SB20200723
								}
							}
						}
//						////System.out.println("LOA: daily_Seller_Nom_Mapping_Id : "+daily_Seller_Nom_Mapping_Id);
					}// end of LoA while loop
				
			final_daily_buyer_mmbtu += total_buyer_mmbtu;
			final_daily_dcq += total_dcq;
			final_daily_mmbtu += total_mmbtu;
			final_daily_scm += total_scm;
			
			if(final_daily_buyer_mmbtu>0)
			{
				daily_Total_Mmbtu = nf.format(final_daily_buyer_mmbtu);
			}
			else
			{
				daily_Total_Mmbtu = "";
			}
			
			if(final_daily_mmbtu>0)
			{
				daily_Total_Mmbtu_Seller_Nom = nf.format(final_daily_mmbtu);
			}
			else
			{
				daily_Total_Mmbtu_Seller_Nom = "";
			}
			
			if(final_daily_scm>0)
			{
				daily_Total_Scm_Seller_Nom = nf.format(final_daily_scm);
			}
			else
			{
				daily_Total_Scm_Seller_Nom = "";
			}
			
			daily_Total_Dcq_Seller_Nom = nf.format(final_daily_dcq);
			////System.out.println("SCH: DLNG "+VBuyer_Delv_Base);
	} catch(Exception e){
		e.printStackTrace();
	}
}
private void fetchTruckWeeklyNomToSchedule() throws SQLException,IOException
{
	methodName = "fetchTruckWeeklyNomToSchedule()";
	
	try {
//		////System.out.println("selContract_Type****"+selContract_Type);
			double conversion_factor_from_m3_to_mmbtu = 23.9; //HA20191223
			double converssion_factor_from_m3_tonmmbtu = 0.3531466672;
			double convt_mmbtu_to_mt = 51.5;
	
			String tempMap [] = buyer_mapping_id.split("-");
			////System.out.println("buyer_mapping_id***"+buyer_mapping_id);
			///String tempMapid = tempMap[0]+"-%-%-%-%";
			String tempMapid = buyer_mapping_id;
			String tsn = tempMap[3];
			String flsaNo = tempMap[1];
			String flsaRevNo = tempMap[2];
			String snNo = tempMap[3];
			String snRevNo = tempMap[4];
			
			String temp_map_id = tempMap[0]+"-"+tempMap[1]+"-%-"+tempMap[3]+"-%";
			String tempNomId[]=schId.split("-");
			String temp_sch_id = temp_map_id+"-"+tsn+"-"+tempNomId[6];
			String ScheduledId=buyer_mapping_id+"-"+tsn+"-"+tempNomId[6];
//			//System.out.println("ScheduledId***"+schId);
			String EndWeekDate="";
			queryString = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY')+7,'DD/MM/YYYY') FROM DUAL";
		//	////System.out.println("queryString******: "+queryString);	
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				EndWeekDate = rset.getString(1);
			}	
			Vector VWeekly_Date_Temp = new Vector();
			Vector VWeekly_DCQ_Temp = new Vector();	
			for(int k=0; k<nomModalDates.size(); k++)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
//				////System.out.println("queryString******: "+queryString);	
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					EndWeekDate = rset.getString(1);
				}	
				if(selContract_Type.equals("S"))
				{
					queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " ,A.FCC_FLAG, SN_BASE "
							+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND A.status='Y' "
						//SB20200702			+ "AND A.FCC_FLAG='Y' "
							+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND B.status='Y' and B.CUSTOMER_CD =  '"+tempMap[0]+"') "
							+ " and A.CUSTOMER_CD =  '"+tempMap[0]+"' "
							+ " AND FLSA_NO='"+flsaNo+"' "
//							+ " AND FLSA_REV_NO='"+flsaRevNo+"' "
							+ " AND SN_NO='"+snNo+"' "
//							+ " AND SN_REV_NO='"+snRevNo+"' "	
							+ "ORDER BY CUSTOMER_CD";
//						//System.out.println("Fetching Weekly Truck SN COntracts Weekly.."+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
//							////System.out.println("nomModalDates.elementAt(k)***"+nomModalDates.elementAt(k));
							String DCQ=rset1.getString(6);
							VWeekly_DCQ_Temp.add(DCQ);
							VWeekly_Date_Temp.add(nomModalDates.elementAt(k));
						}
				 }
				 else if(selContract_Type.equals("L"))
				 {
					queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
							+ " ,A.FCC_FLAG, LOA_BASE "
							+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
						//SB20200705			+ "AND A.FCC_FLAG='Y' "
							+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND B.status='Y' and B.CUSTOMER_CD =  '"+tempMap[0]+"' ) "
							+ " and A.CUSTOMER_CD =  '"+tempMap[0]+"' "
							+ " AND TENDER_NO='"+flsaNo+"' "
							+ " AND LOA_NO='"+snNo+"' "
//							+ " AND LOA_REV_NO='"+snRevNo+"' "	
							+ "ORDER BY CUSTOMER_CD";
//						////System.out.println("Fetching Weekly Truck LoA COntracts.."+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String DCQ=rset1.getString(5);
						VWeekly_DCQ_Temp.add(DCQ);
						VWeekly_Date_Temp.add(nomModalDates.elementAt(k));
					}
				 }
			 }//for end
//			 ////System.out.println("VWeekly_Date_Temp.."+VWeekly_Date_Temp);
//			 ////System.out.println("VWeekly_DCQ_Temp.."+VWeekly_DCQ_Temp);
			
			// schId=buyer_mapping_id+"-1-1"; 
			 revNo="1";
			 int dtCnt=0;double totDcq=0;double truck_nomi_vol=0;double truck_sch_vol=0;
			 for(int m=0; m<VWeekly_Date_Temp.size(); m++)
			 {
				 	dtCnt=0;
					totDcq= 0;
					truck_nomi_vol=0;
					truck_sch_vol=0;
					
				 queryString="SELECT nvl(MAX(REV_NO),0) "
							+ " FROM DLNG_TANK_TRUCK_MST A,DLNG_WEEKLY_TRUCK_NOM_DTL B"
							+ " WHERE A.STATUS ='Y' "
							+ " AND B.MAPPING_ID like '"+temp_map_id+"' AND B.NOM_ID like '"+temp_sch_id+"' "
							+ " AND B.NOM_DT >= TO_DATE ('"+VWeekly_Date_Temp.elementAt(m)+"','DD/MM/YYYY') "
							+ " AND B.NOM_DT <= TO_DATE ('"+VWeekly_Date_Temp.elementAt(m)+"','DD/MM/YYYY')"
							+ " AND CONTRACT_TYPE = '"+selContract_Type+"'"
							+ " AND A.TRUCK_NM = B.TRUCK_NM  ORDER BY TRUCK_ID " ;
					rset8 = stmt8.executeQuery(queryString);
//					//System.out.println("Weekly Nominated REV NO : "+queryString);
					if(rset8.next()) 
					{
						revNo=rset8.getString(1);
					}
					
				 double truck_load_capacity =0;
				 queryString="SELECT A.TRUCK_NM,A.TRUCK_ID,A.TANK_VOL_M3,A.TANK_VOL_TON,"
							+ " B.TRUCK_VOL,B.TRUCK_ENE,TO_CHAR(B.ARRIVAL_DT,'DD/MM/YYYY'),"
							+ " B.ARRIVAL_TIME,B.REMARKS,B.TRANS_CD, LOAD_CAP,NEXT_AVAIL_DAYS"
							+ " FROM DLNG_TANK_TRUCK_MST A,DLNG_WEEKLY_TRUCK_NOM_DTL B"
							+ " WHERE A.STATUS ='Y' "
							+ " AND B.MAPPING_ID like '"+temp_map_id+"' AND B.NOM_ID like '"+temp_sch_id+"' AND B.REV_NO='"+revNo+"'"
							+ " AND B.NOM_DT >= TO_DATE ('"+VWeekly_Date_Temp.elementAt(m)+"','DD/MM/YYYY') "
							+ " AND B.NOM_DT <= TO_DATE ('"+VWeekly_Date_Temp.elementAt(m)+"','DD/MM/YYYY')"
							+ " AND CONTRACT_TYPE = '"+selContract_Type+"'"
							+ " AND A.TRUCK_NM = B.TRUCK_NM  ORDER BY TRUCK_ID " ;
				 
					rset8 = stmt8.executeQuery(queryString);
//					//System.out.println("Weekly Nominated Truck List : "+queryString);
					while(rset8.next()) 
					{
						dtCnt++;
						
						double capacity=rset8.getDouble(3);
						double loadPerc=rset8.getDouble(11);
						truck_load_capacity=(capacity*loadPerc/100);
						
							String scheduled_truck = "SELECT TRUCK_NM,SCH_ID, TRUCK_VOL, TRUCK_ENE,TO_CHAR(SCH_DT,'DD/MM/YYYY'),SCH_TIME,REMARKS,TRANS_CD,NEXT_AVAIL_DAYS FROM DLNG_WEEKLY_TRUCK_SCH_DTL "
									+ " WHERE MAPPING_ID LIKE '"+temp_map_id+"' AND TRUCK_NM LIKE '"+rset8.getString(1)+"' "
									+ " AND SCH_DT between TO_DATE ('"+VWeekly_Date_Temp.elementAt(m) +"','DD/MM/YYYY')"
									+ " AND TO_DATE ('"+VWeekly_Date_Temp.elementAt(m) +"','DD/MM/YYYY') "
									+ " AND REV_NO=(select max(REV_NO) from DLNG_WEEKLY_TRUCK_SCH_DTL "
									+ " WHERE MAPPING_ID LIKE '"+temp_map_id+"' "
									+ " AND SCH_DT between TO_DATE ('"+VWeekly_Date_Temp.elementAt(m) +"','DD/MM/YYYY')"
									+ " AND TO_DATE ('"+VWeekly_Date_Temp.elementAt(m) +"','DD/MM/YYYY') "
									+ " AND TRUCK_NM LIKE '"+rset8.getString(1)+"' AND CONTRACT_TYPE = '"+selContract_Type+"')"
									+ " AND CONTRACT_TYPE = '"+selContract_Type+"'";
								
//							//System.out.println("Weekly Scheduled Truck List : ***"+scheduled_truck);
							rset10 = stmt10.executeQuery(scheduled_truck);
							if (rset10.next()) 
							{
								VWeekly_Sch_ID.add(rset10.getString(2)==null?"":rset10.getString(2));
								VWeekly_DCQ.add(""+VWeekly_DCQ_Temp.elementAt(m));
								VWeekly_Date.add(""+VWeekly_Date_Temp.elementAt(m));
								VWeekly_Truck_ID.add("");
								VWeekly_Truck_Nm.add(rset10.getString(1)==null?"":rset10.getString(1));
							//SB: 100% Cap	VWeekly_Truck_Cap.add(rset8.getString(4)==null?"":rset8.getString(4));
								VWeekly_Truck_Cap.add(truck_load_capacity); //Pecr Cap
								VWeekly_Sch_VOL.add(rset10.getString(3)==null?"":rset10.getString(3));
								VWeekly_Sch_ENE.add(rset10.getString(4)==null?"":rset10.getString(4));
								VWeekly_Sch_MT.add(nf.format(Double.parseDouble(rset10.getString(3)) / convt_mmbtu_to_mt));
								VWeekly_Truck_SchDt.add(rset10.getString(5)==null?"":rset10.getString(5));
								VWeekly_Truck_SchTm.add(rset10.getString(6)==null?"":rset10.getString(6));
								VWeekly_Truck_Remark.add(rset10.getString(7)==null?"":rset10.getString(7));
								VWeekly_Trans_cd.add(rset10.getString(8)==null?"":rset10.getString(8));
								
								VWeekly_Nom_VOL.add(rset8.getString(5)==null?"":rset8.getString(5));
								VWeekly_Nom_MT.add(nf.format(Double.parseDouble(rset8.getString(5)) / convt_mmbtu_to_mt));
								
								truck_sch_vol+=Double.parseDouble(rset10.getString(3)==null?"":rset10.getString(3));
								VLoadedNxt_avail_days.add(rset10.getString(9)==null?"":rset10.getString(9));
							}
							else
							{
//								////System.out.println("in else ScheduledId : "+ScheduledId);
								VWeekly_Sch_ID.add(""+ScheduledId);
								VWeekly_DCQ.add(""+VWeekly_DCQ_Temp.elementAt(m));
								VWeekly_Date.add(""+VWeekly_Date_Temp.elementAt(m));
								VWeekly_Truck_ID.add(rset8.getString(2)==null?"":rset8.getString(2));
								VWeekly_Truck_Nm.add(rset8.getString(1)==null?"":rset8.getString(1));
								//SB: 100% Cap	VWeekly_Truck_Cap.add(rset8.getString(4)==null?"":rset8.getString(4));
								VWeekly_Truck_Cap.add(truck_load_capacity); //Pecr Cap
								VWeekly_Sch_VOL.add("0");
								VWeekly_Sch_ENE.add("0");
								VWeekly_Sch_MT.add("");
								VWeekly_Truck_SchDt.add("");
								VWeekly_Truck_SchTm.add(rset8.getString(8)==null?"":rset8.getString(8));
								VWeekly_Truck_Remark.add("");
								VWeekly_Trans_cd.add(rset8.getString(10)==null?"":rset8.getString(10));
								/*VWeekly_Nom_VOL.add("0");
								VWeekly_Nom_MT.add("");*/
								
								VWeekly_Nom_VOL.add(rset8.getString(5)==null?"":rset8.getString(5));
								VWeekly_Nom_MT.add(nf.format(Double.parseDouble(rset8.getString(5)) / convt_mmbtu_to_mt));
								
								VLoadedNxt_avail_days.add(rset8.getString(12)==null?"":rset8.getString(12));
							}
							
							truck_nomi_vol+=Double.parseDouble(rset8.getString(5)==null?"0":rset8.getString(5));
					}
					if(dtCnt > 0) {
						VdateCnt.add(dtCnt);
						VTruck_nomi_mmbtu.add(truck_nomi_vol);
						VTruck_sch_mmbtu.add(truck_sch_vol);
						VTruck_bal_mmbtu.add(truck_nomi_vol - truck_sch_vol);
						
					}
//					 ////System.out.println("truck_nomi_vol*****"+truck_nomi_vol);	
					
			 }
				
	} catch (Exception e) {
		e.printStackTrace();
	}
}

//////////////////////////>>>>>>>>>>>>>>>>>>>>>>>>>>>
/////////////////////////Added by Sujit on 20200819////////////////////////
private void fetchWeeklyBuyerNomDetails22() throws SQLException,IOException
{
	methodName = "fetchWeeklyBuyerNomDetails22()";
	try {
		double final_daily_dcq = 0;
		double final_daily_mmbtu = 0;
		double final_daily_scm = 0;
		double total_dcq = 0;
		double total_mmbtu = 0;
		double total_scm = 0;
		
		String week_last_dt = ""; 
		 String lasdt="select TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY') + '7','DD/MM/YYYY') from dual";
//           //System.out.println("lasdt****"+lasdt);
     	  rset5=stmt.executeQuery(lasdt);
            if(rset5.next())
            {
            	week_last_dt =rset5.getString(1);
            }
//        //System.out.println("week_last_dt****"+week_last_dt);
		queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
				+ " ,A.FCC_FLAG, SN_BASE,TO_CHAR(START_DT,'dd/mm/yyyy') "
				+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY')"
				+ " AND A.end_dt>=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND A.status='Y' "
				+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
				+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
		System.out.println("Fetching SN COntracts.."+queryString1);
		
		rset1 = stmt1.executeQuery(queryString1);
		while(rset1.next())
		{
			String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
			String temp_map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-%-"+rset1.getString(4)+"-%";
//			////System.out.println("rset1.getString(1)*******"+rset1.getString(1));
			if(!rset1.getString(1).equals("0"))
			{
				queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
						   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.flsa_no="+rset1.getString(2)+" " +
						   "AND A.sn_no="+rset1.getString(4)+"";
//				//System.out.println("queryS96.ring2****"+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				while(rset2.next())
				{
					daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
					
					queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
								   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
								   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
								   "AND B.eff_dt<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY'))";
					
//					//System.out.println("Customer Plant Name Fetch Query (CN) = "+queryString3);
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
						//////System.out.println("daily_Buyer_Nom_Plant_Abbr line no 265*******"+rset3.getString(1));
					}
					else
					{
						daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
					}
					
					daily_Buyer_Nom_Mapping_Id.add(map_id);		
			//		////System.out.println("daily_Buyer_Nom_Mapping_Id line no 273*******"+daily_Buyer_Nom_Mapping_Id);
					daily_Buyer_Nom_Fgsa_No.add(rset1.getString(2));
					daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(3));
					daily_Buyer_Nom_Sn_No.add(rset1.getString(4));
					daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(5));
					
					daily_Buyer_Nom_Cd.add(rset1.getString(1));
//					//System.out.println("daily_Buyer_Nom_Cd line no 279*******"+daily_Buyer_Nom_Cd);
					
					daily_Buyer_Nom_Type.add("S");
					daily_Buyer_Nom_Contract_Type.add("SN");
					String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(7))<=100?100:Double.parseDouble(rset1.getString(7)));

					String NomId="";
					NomId=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);	
					VWeekly_Buyer_Nom_ID.add(NomId);
					VWeekly_Buyer_Cont_st_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
//					//System.out.println("rset1.getString(13)****"+rset1.getString(13));
				   /*-----------------------Variable DCQ-------------------*/
					
					double dcq_var=0; double dcq_var_MT=0; 	double convt_mmbtu_to_mt = 51.5;
					queryString = "SELECT NVL(dcq,'0') "
							+ " FROM DLNG_SN_DCQ_DTL A WHERE A.from_dt<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY')"
							+ " AND A.to_dt>=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND A.FLAG='Y' "
							+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_DCQ_DTL B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
							+ " A.customer_cd=B.customer_cd AND  B.from_dt<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND B.to_dt>=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND B.FLAG='Y') "
							+ " AND CUSTOMER_CD='"+rset1.getString(1)+"' AND FLSA_NO='"+rset1.getString(2)+"' AND FLSA_REV_NO="+rset1.getString(3)+" AND SN_NO="+rset1.getString(4)+" AND SN_REV_NO="+rset1.getString(5)+"";
							////System.out.println("DLNG_SN_DCQ_DTL: "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
//								dcq_var= rset.getDouble(1);
								dcq_var = 0; //Hiren_20201209 
							}
							if(dcq_var>0)
							{
								daily_Buyer_Nom_Dcq.add(nf.format(dcq_var));	
								daily_Buyer_Nom_Mdcq_Qty.add(nf.format((dcq_var*Double.parseDouble(temp_mdcq_percentage))/100));
								total_dcq += dcq_var;
								dcq_var_MT= (dcq_var/convt_mmbtu_to_mt);
								daily_Buyer_Nom_Dcq_Mt.add(nf.format(dcq_var_MT));
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));	
								daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
								total_dcq += Double.parseDouble(rset1.getString(6));
								daily_Buyer_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(10))));
							}

					VBuyer_Fcc_Flag.add(rset1.getString(11)==null?"N":rset1.getString(11)); //SB20200718
					VBuyer_Delv_Base.add(rset1.getString(12)==null?"X":rset1.getString(12)); //SB20200804
					String ALLOCATED_QTY = "0";
					String ALLOCATED_QTY1 = "0";
					double DEBIT_QTY = 0;
					double CREDIT_QTY = 0;
					
					String CONTRACTED_QTY =nf5.format(Double.parseDouble(rset1.getString(8)) + (Double.parseDouble(rset1.getString(9))));
					
					daily_Buyer_regas_cargo_boe_no.add("");
					daily_Buyer_regas_cargo_boe_dt.add("");
				
						queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(NOM_DT,'DD/MM/YYYY')) FROM DLNG_WEEKLY_NOM WHERE "
								+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' "
								+ "AND NOM_DT>=TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') AND NOM_DT<=TO_DATE('"+nomModalDates.lastElement()+"','DD/MM/YYYY') "
								+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
								+ "AND REV_NO = ("
								+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
								+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT>=TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') AND NOM_DT<=TO_DATE('"+nomModalDates.lastElement()+"','DD/MM/YYYY') "
								+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
						//System.out.println("line no 4072....For ALLOCATED_QTY....queryString : "+queryString);
						rset = stmt.executeQuery(queryString);
									
						if(rset.next())
						{
							if(rset.getString(1)!=null)
							{
								if(!rset.getString(1).trim().equals(""))
								{
									ALLOCATED_QTY = rset.getString(1).trim();
//									////System.out.println("rset from line no 4082....ALLOCATED_QTY*******"+ALLOCATED_QTY);
								}
							}
						}
						
					queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_WEEKLY_SCH WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' "
							+ "AND SCH_DT>=TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') AND SCH_DT<=TO_DATE('"+nomModalDates.lastElement()+"','DD/MM/YYYY') "
							+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_SCH WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
							+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT>=TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') AND SCH_DT<=TO_DATE('"+nomModalDates.lastElement()+"','DD/MM/YYYY') "
							+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
					//System.out.println("line no 4094....For ALLOCATED_QTY1....queryString : "+queryString);
					rset = stmt.executeQuery(queryString);
									
					if(rset.next())
					{
						if(rset.getString(1)!=null)
						{
							if(!rset.getString(1).trim().equals(""))
							{
								ALLOCATED_QTY1 = rset.getString(1).trim();////System.out.println("rset from line no 4104....ALLOCATED_QTY1*******"+ALLOCATED_QTY1);
							} 
						} 
					} 
					
					
					/*Hiren_20210217 include dr Quantity Change Criteria*/
					queryString = "SELECT nvl(SUM(DIFF_QTY),0) FROM DLNG_DR_CR_NOTE WHERE "
							+ " CUSTOMER_CD='"+rset1.getString(1)+"'"
							+ " AND FGSA_NO = '"+rset1.getString(2)+"'"
							+ " AND SN_NO = '"+rset1.getString(4)+"'"
							+ " AND CONTRACT_TYPE='S' "
							+ " AND DR_CR_FLAG = 'dr' "
							+ " AND CRITERIA LIKE  '%DIFF-QTY%' "
							+ " AND DR_CR_DT <= TO_DATE('"+nomModalDates.elementAt(0)+"','dd/mm/yyyy')";
//					//System.out.println("DR SUM QTY----------"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						DEBIT_QTY = Double.parseDouble(rset.getString(1).trim()+"");
					}
					
					/*Hiren_20210217 include dr Quantity Change Criteria*/
					queryString = "SELECT nvl(SUM(DIFF_QTY),0) FROM DLNG_DR_CR_NOTE WHERE "
							+ " CUSTOMER_CD='"+rset1.getString(1)+"'"
							+ " AND FGSA_NO = '"+rset1.getString(2)+"'"
							+ " AND SN_NO = '"+rset1.getString(4)+"'"
							+ " AND CONTRACT_TYPE='S' "
							+ " AND DR_CR_FLAG = 'cr' "
							+ " AND CRITERIA LIKE  '%DIFF-QTY%' "
							+ " AND DR_CR_DT <= TO_DATE('"+nomModalDates.elementAt(0)+"','dd/mm/yyyy')";
//					//System.out.println("CR SUM QTY----------"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						CREDIT_QTY = Double.parseDouble(rset.getString(1).trim()+"");
					}
					
					ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1)+DEBIT_QTY-CREDIT_QTY);
					daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-(Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)+DEBIT_QTY-CREDIT_QTY)));
					
					queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
							   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
							   	+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY')) ";
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						daily_Buyer_Nom_Abbr.add(rset3.getString(1));
						//////System.out.println("daily_Buyer_Nom_Abbr from line no 350 "+daily_Buyer_Nom_Abbr);
					}
					else
					{
						daily_Buyer_Nom_Abbr.add(" ");
					}
					
					
					queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
							+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_WEEKLY_NOM WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
							+ "NOM_DT=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
							+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') "
							+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
					
//					////System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (CN) = "+queryString5);
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next())
					{
						daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
						daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
						daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
						daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
						//daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
						//daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
					//	total_mmbtu += Double.parseDouble(rset5.getString(5));
					//	total_scm += Double.parseDouble(rset5.getString(6));
						NomId=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);	

						////////////////////WEEKLY Total Calculation/////////////////////
						queryString7 = "SELECT SUM(NVL(DAY_VOL,'0')),SUM(NVL(TOT_ENE,'0')) FROM DLNG_WEEKLY_NOM WHERE "
								+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
								+ "NOM_DT>=TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') AND NOM_DT<=TO_DATE('"+nomModalDates.lastElement()+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"' "
								+ "AND REV_NO = ("
								+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
								+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT>=TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') AND NOM_DT<=TO_DATE('"+nomModalDates.lastElement()+"','DD/MM/YYYY') "
								+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1)+"') ";
						
//					////System.out.println("SUM-QTY: DLNG_WEEKLY_NOM: "+queryString7);
						rset7 = stmt5.executeQuery(queryString7);
						if(rset7.next())
						{
							VWeekly_Buyer_Nom_Qty_Mmbtu.add(rset7.getString(1)==""?"0":rset7.getString(1));
							VWeekly_Buyer_Nom_Qty_scm.add(rset7.getString(2)==""?"0":rset7.getString(2));
							
							daily_Buyer_Nom_Qty_Mmbtu.add(rset7.getString(1)=="0"?"":rset7.getString(1));
							daily_Buyer_Nom_Qty_Scm.add(rset7.getString(2)==""?"0":rset7.getString(2));
							total_mmbtu += Double.parseDouble(rset7.getString(1)=="0"?"":rset7.getString(1));
							total_scm += Double.parseDouble(rset7.getString(2)==""?"0":rset7.getString(2));
						}
						else
						{
							VWeekly_Buyer_Nom_Qty_Mmbtu.add("0");
							VWeekly_Buyer_Nom_Qty_scm.add("0");
							daily_Buyer_Nom_Qty_Mmbtu.add("0");
							daily_Buyer_Nom_Qty_Scm.add("0");
						}
//						////System.out.println("SUM-QTY: VWeekly_Buyer_Nom_Qty_Mmbtu: "+VWeekly_Buyer_Nom_Qty_Mmbtu);
						/////////^^^/////////////////////////////////////////////////////////
					}
					else
					{
						daily_Buyer_Gen_Day_With_Rev_No.add("");
						daily_Buyer_Rev_No.add("0");
						daily_Buyer_Gen_Day_Time.add("");
						daily_Buyer_Nom_Plant_Seq_No.add("0");
						daily_Buyer_Nom_Qty_Mmbtu.add("");
						daily_Buyer_Nom_Qty_Scm.add("");
					}
					int NomIdCnt=0;
					/*SB20200911///Check for Invoice generated////////
					
					queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID='"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";							
			//		////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next())
						NomIdCnt=rset5.getInt(1);
					else
						NomIdCnt=0;
						*/
					if(NomIdCnt>0)
						VBuyer_Inv_Flag.add("Y");
					else
						VBuyer_Inv_Flag.add("N");
				}
			}
		}//end of while loop

		/* **********************for LoA Contracts***************************** Hiren_20200428*/

		queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
				+ " ,A.FCC_FLAG, LOA_BASE,TO_CHAR(START_DT,'dd/mm/yyyy') "
				+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY')"
				+ " AND A.end_dt>=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND A.status='Y' "
			//SB20200705			+ "AND A.FCC_FLAG='Y' "
				+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
				+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND B.status='Y') ORDER BY CUSTOMER_CD";
			//System.out.println("Fetching LoA COntracts.."+queryString1);
		rset1 = stmt1.executeQuery(queryString1);
		while(rset1.next())
		{
			//mappaing_id = customer_cd-tender_no-0-loa_no-loa_rev_no
			String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-0-"+rset1.getString(3)+"-"+rset1.getString(4);
			String temp_map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-%-"+rset1.getString(3)+"-%";
//			////System.out.println("rset1.getString(1)*******"+rset1.getString(1));
			if(!rset1.getString(1).equals("0"))
			{
				/*///////////////////////////SB20200801/////////////////////////////////
				queryString2 = "SELECT TENDER_BASE FROM DLNG_TENDER_MST " +
						   "WHERE customer_cd="+rset1.getString(1)+" AND TENDER_NO="+rset1.getString(2)+" AND FLAG='Y' ";
//				////System.out.println("LoA Plant No****"+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				while(rset2.next())
				{
					VBuyer_Delv_Base.add(rset2.getString(1));
				}
				*////////////////////^^SB20200801/////////////////////////
				queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
						   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.TENDER_NO="+rset1.getString(2)+" " +
						   "AND A.LOA_NO="+rset1.getString(3)+" AND LOA_REV_NO = '"+rset1.getString(4)+"'";
//				////System.out.println("LoA Plant No****"+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				while(rset2.next())
				{
					daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
					
					queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
								   "WHERE A.customer_cd="+rset1.getString(1)+" AND A.seq_no="+rset2.getString(1)+" " +
								   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
								   "AND B.eff_dt<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY'))";
//					////System.out.println("LoA Plant Name = "+queryString3);
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
						//////System.out.println("daily_Buyer_Nom_Plant_Abbr line no 265*******"+rset3.getString(1));
					}
					else
					{
						daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
					}
					
					daily_Buyer_Nom_Mapping_Id.add(map_id);
//					////System.out.println("daily_Buyer_Nom_Mapping_Id line no 273*******"+rset1.getString(3));
					daily_Buyer_Nom_Fgsa_No.add(rset1.getString(2));
					daily_Buyer_Nom_Fgsa_Rev_No.add("0");
					daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
					daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
					
					daily_Buyer_Nom_Cd.add(rset1.getString(1));
					//////System.out.println("daily_Buyer_Nom_Cd line no 279*******"+daily_Buyer_Nom_Cd);
					
					daily_Buyer_Nom_Type.add("L");
					daily_Buyer_Nom_Contract_Type.add("LoA");
					
					daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(5))));	
					String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(6))<=100?100:Double.parseDouble(rset1.getString(6)));
					daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(5))*Double.parseDouble(temp_mdcq_percentage))/100));
					total_dcq += Double.parseDouble(rset1.getString(5));
					String NomId="";
					NomId=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);	
					VWeekly_Buyer_Nom_ID.add(NomId);
					
					daily_Buyer_Nom_Dcq_Mt.add(nf.format(Double.parseDouble(rset1.getString(9))));
					VBuyer_Fcc_Flag.add(rset1.getString(10)==null?"N":rset1.getString(10)); //SB20200718
					VBuyer_Delv_Base.add(rset1.getString(11)==null?"X":rset1.getString(11)); //SB20200804
					VWeekly_Buyer_Cont_st_dt.add(rset1.getString(12)==null?"":rset1.getString(12));
//					//System.out.println("rset1.getString(12)***LoA***"+rset1.getString(12));
					String ALLOCATED_QTY = "0";
					String ALLOCATED_QTY1 = "0";
					double DEBIT_QTY = 0;
					double CREDIT_QTY = 0;
					
					String CONTRACTED_QTY =nf5.format(Double.parseDouble(rset1.getString(7)) + (Double.parseDouble(rset1.getString(8))));
					
//					daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
//					daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
					daily_Buyer_regas_cargo_boe_no.add("");
					daily_Buyer_regas_cargo_boe_dt.add("");
				
						queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
								+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
								+ "SCH_DT=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
								+ "AND REV_NO = ("
								+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
								+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') "
								+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
//						////System.out.println("--Fetching Data--"+queryString);
						rset = stmt.executeQuery(queryString);
									
						if(rset.next())
						{
							if(rset.getString(1)!=null)
							{
								if(!rset.getString(1).trim().equals(""))
								{
									ALLOCATED_QTY = rset.getString(1).trim();
									//////System.out.println("rset from line no 313*******"+ALLOCATED_QTY);
								}
							}
						}
						
					queryString = "SELECT SUM(DAY_VOL), MAX(TO_CHAR(SCH_DT,'DD/MM/YYYY')) FROM DLNG_DAILY_SCH WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
							+ "SCH_DT=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
							+ "MAPPING_ID like '"+temp_map_id+"' AND SCH_DT=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') "
							+ "AND SCH_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
					rset = stmt.executeQuery(queryString);
									
					if(rset.next())
					{
						if(rset.getString(1)!=null)
						{
							if(!rset.getString(1).trim().equals(""))
							{
								ALLOCATED_QTY1 = rset.getString(1).trim();
							} 
						} 
					} 
					/*Hiren_20210217 include dr Quantity Change Criteria*/
					queryString = "SELECT nvl(SUM(DIFF_QTY),0) FROM DLNG_DR_CR_NOTE WHERE "
							+ " CUSTOMER_CD='"+rset1.getString(1)+"'"
							+ " AND FGSA_NO = '"+rset1.getString(2)+"'"
							+ " AND SN_NO = '"+rset1.getString(4)+"'"
							+ " AND CONTRACT_TYPE='L' "
							+ " AND DR_CR_FLAG = 'dr' "
							+ " AND CRITERIA LIKE  '%DIFF-QTY%' "
							+ " AND DR_CR_DT <= TO_DATE('"+week_last_dt+"','dd/mm/yyyy')";
//					//System.out.println("DR SUM QTY----------"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						DEBIT_QTY = Double.parseDouble(rset.getString(1).trim()+"");
					}
					
					/*Hiren_20210217 include dr Quantity Change Criteria*/
					queryString = "SELECT nvl(SUM(DIFF_QTY),0) FROM DLNG_DR_CR_NOTE WHERE "
							+ " CUSTOMER_CD='"+rset1.getString(1)+"'"
							+ " AND FGSA_NO = '"+rset1.getString(2)+"'"
							+ " AND SN_NO = '"+rset1.getString(4)+"'"
							+ " AND CONTRACT_TYPE='L' "
							+ " AND DR_CR_FLAG = 'cr' "
							+ " AND CRITERIA LIKE  '%DIFF-QTY%' "
							+ " AND DR_CR_DT <= TO_DATE('"+week_last_dt+"','dd/mm/yyyy')";
					//System.out.println("CR SUM QTY----------"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						CREDIT_QTY = Double.parseDouble(rset.getString(1).trim()+"");
					}
					
					ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1)+DEBIT_QTY-CREDIT_QTY);
					daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-(Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)+DEBIT_QTY-CREDIT_QTY)));
					
//					ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
					
//					daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
					
					queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
							   "WHERE A.customer_cd='"+rset1.getString(1)+"' AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
							   	+ "CUSTOMER_CD='"+rset1.getString(1)+"' AND EFF_DT<=TO_DATE('"+week_last_dt+"','DD/MM/YYYY')) ";
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						daily_Buyer_Nom_Abbr.add(rset3.getString(1));
						//////System.out.println("daily_Buyer_Nom_Abbr from line no 350 "+daily_Buyer_Nom_Abbr);
					}
					else
					{
						daily_Buyer_Nom_Abbr.add(" ");
					}
				
					queryString5 = "SELECT TO_CHAR(GEN_DT,'DD/MM/YYYY'),NVL(REV_NO,'0'),NVL(TIME_ST_DAY,''), "
							+ "NVL(NOM_ID,''),NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0'),NOM_BASE_FLAG FROM DLNG_WEEKLY_NOM WHERE "
							+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"' AND "
							+ "NOM_DT=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"' AND "
							+ "MAPPING_ID like '"+temp_map_id+"' AND NOM_DT=TO_DATE('"+week_last_dt+"','DD/MM/YYYY') "
							+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
					
			//		////System.out.println("Fetch Query FROM DLNG_WEEKLY_NOM Table (CN) = "+queryString5);
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next())
					{
						daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
						daily_Buyer_Rev_No.add(rset5.getString(2)==null?"":rset5.getString(2));
						daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
						daily_Buyer_Nom_Plant_Seq_No.add(rset2.getString(1));
						//daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
					///	total_mmbtu += Double.parseDouble(rset5.getString(5));
					///	total_scm += Double.parseDouble(rset5.getString(6));
						NomId=map_id+"-"+rset1.getString(4)+"-"+rset2.getString(1);
						//daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
						
						queryString6 = "SELECT SUM(DAY_VOL) FROM DLNG_WEEKLY_NOM WHERE "
								+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"'  "
								+ "AND NOM_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
								+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
								+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
								+ "AND REV_NO = ("
								+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"'  "
								+ "AND MAPPING_ID like '"+temp_map_id+"' "
								+ "AND NOM_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
								+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
								+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
						////System.out.println("Fetch SUM of DAY_VOL Query FROM DLNG_WEEKLY_NOM Table  = "+queryString6);
						rset6 = stmt6.executeQuery(queryString6);
						if(rset6.next())
						{
							daily_Buyer_Nom_Qty_Mmbtu.add(rset6.getString(1)=="0"?"":rset6.getString(1));
							total_mmbtu += Double.parseDouble(rset6.getString(1)==""?"0":rset6.getString(1));
						}
						
						
						queryString7 = "SELECT SUM(TOT_ENE) FROM DLNG_WEEKLY_NOM WHERE "
								+ "PARTY_CD='"+rset1.getString(1)+"' AND MAPPING_ID like '"+temp_map_id+"'  "
								+ "AND NOM_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
								+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
								+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"' "
								+ "AND REV_NO = ("
								+ "SELECT MAX(REV_NO) FROM DLNG_WEEKLY_NOM WHERE PARTY_CD='"+rset1.getString(1)+"'  "
								+ "AND MAPPING_ID like '"+temp_map_id+"' "
								+ "AND NOM_DT between TO_DATE('"+nomModalDates.elementAt(0)+"','DD/MM/YYYY') "
								+ "AND TO_DATE ('"+nomModalDates.lastElement() +"','DD/MM/YYYY') "
								+ "AND NOM_ID like '"+temp_map_id+"-"+rset1.getString(3)+"-"+rset2.getString(1)+"') ";
//						////System.out.println("Fetch SUM of TOT_ENE Query FROM DLNG_WEEKLY_NOM Table  = "+queryString6);
						rset7 = stmt7.executeQuery(queryString7);
						if(rset7.next())
						{
							daily_Buyer_Nom_Qty_Scm.add(rset7.getString(1)=="0"?"":rset7.getString(1));
							total_scm += Double.parseDouble(rset7.getString(1)==""?"0":rset7.getString(1));
						}
					}
					else
					{
						daily_Buyer_Gen_Day_With_Rev_No.add("");
						daily_Buyer_Rev_No.add("0");
						daily_Buyer_Gen_Day_Time.add("");
						daily_Buyer_Nom_Plant_Seq_No.add("0");
						daily_Buyer_Nom_Qty_Scm.add("");
						daily_Buyer_Nom_Qty_Mmbtu.add("");
					}

				////Check for Invoice generated////////
					int NomIdCnt=0;
				/*SB20200912	queryString5 = "SELECT COUNT(MAPPING_ID) FROM DLNG_INVOICE_MST WHERE MAPPING_ID='"+NomId+"' AND PERIOD_START_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";							
			//		////System.out.println("Fetch Query FROM DLNG_INVOICE_MST = "+queryString5);
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next())
						NomIdCnt=rset5.getInt(1);*/
					if(NomIdCnt>0)
						VBuyer_Inv_Flag.add("Y");
					else
						VBuyer_Inv_Flag.add("N");
				}
			}
		}
		
	final_daily_dcq += total_dcq;
	final_daily_mmbtu += total_mmbtu;
	final_daily_scm += total_scm;

	if(final_daily_mmbtu>0)
	{
		daily_Total_Mmbtu = nf.format(final_daily_mmbtu);
	}
	else
	{
		daily_Total_Mmbtu = "";
	}

	if(final_daily_scm>0)
	{
		daily_Total_Scm = nf.format(final_daily_scm);
	}
	else
	{
		daily_Total_Scm = "";
	}

	daily_Total_Dcq = nf.format(final_daily_dcq);

	queryString = "SELECT DISTINCT CUSTOMER_CD FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
	rset = stmt.executeQuery(queryString);
	while(rset.next())
	{
		CUST_CD.add(rset.getString(1)==null?"0":rset.getString(1));
		//////System.out.println("CUST_CD from line no 420 "+CUST_CD);
		
	}

	for(int i =0;i<daily_Buyer_Nom_Contract_Type.size();i++)
	{
		qty_nomination.add(daily_Buyer_Nom_Balance_Qty.elementAt(i)+"");
	}

	} catch (Exception e) {
		e.printStackTrace();
	}
	}

Vector VAvail_cont = new Vector(); 
private void fetchWeeklyAvailableTruckDetails22() throws SQLException,IOException
{	
	int counter = 0;
	try {
		String fetchTruckNomData = "";
		String selCust_idForTruck=selCust_id; //SB20200801
		///if(selDelvBase.equals("D")) //SB20200801
		///	selCust_idForTruck="100";
		//String selTransForTruck="%"; //SB20200806
		double conversion_factor_from_m3_to_mmbtu = 23.9; //HA20191223
		double converssion_factor_from_m3_tonmmbtu = 0.3531466672;
		double convt_mmbtu_to_mt = 51.5;
	
		String tempMap [] = buyer_mapping_id.split("-");
		///String tempMapid = tempMap[0]+"-%-%-%-%";
		String tempMapid = buyer_mapping_id;
		String tsn = tempMap[3];
		String flsaNo = tempMap[1];
		String flsaRevNo = tempMap[2];
		String snNo = tempMap[3];
		String snRevNo = tempMap[4];
		selCust_idForTruck = tempMap[0];
//		//System.out.println("custPlant_cd*****"+custPlant_cd);
		
		
		String temp_map_id = tempMap[0]+"-"+tempMap[1]+"-%-"+tempMap[3]+"-%";
		String tempNomId[] = nomId.split("-");
		String temp_nom_id = temp_map_id+"-"+tsn+"-"+tempNomId[6];
		
		String NominationId=buyer_mapping_id+"-"+tsn+"-"+tempNomId[6];
//		String temp_nom_id = temp_map_id+"-"+tsn+"-"+custPlant_cd;
//		//System.out.println("temp_nom_id********"+temp_nom_id);
		String EndWeekDate="";
		queryString = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY')+7,'DD/MM/YYYY') FROM DUAL";
	////	////System.out.println("queryString******: "+queryString);	
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			EndWeekDate = rset.getString(1);
		}	
		Vector VWeekly_Date_Temp = new Vector();
		Vector VWeekly_DCQ_Temp = new Vector();	
		for(int k=0; k<nomModalDates.size(); k++)
		{
			
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
//			////System.out.println("queryString******: "+queryString);	
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				EndWeekDate = rset.getString(1);
			}	
//			////System.out.println("*****Contracts: "+selContract_Type);
		///	selContract_Type="L";
			if(selContract_Type.equals("S"))
			{
			queryString1 = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
					+ " ,A.FCC_FLAG, SN_BASE "
					+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY')"
					+ " AND A.end_dt>=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND A.status='Y' "
					+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE B.CUSTOMER_CD='"+selCust_idForTruck+"' and A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
					+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND B.status='Y') "
					+ " AND A.CUSTOMER_CD='"+selCust_idForTruck+"' AND FLSA_NO='"+flsaNo+"'  AND SN_NO='"+snNo+"' "	
					+ "ORDER BY CUSTOMER_CD";
				//System.out.println("Fetching SN COntracts.."+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String DCQ=rset1.getString(6);
					VWeekly_DCQ_Temp.add(DCQ);
					VWeekly_Date_Temp.add(nomModalDates.elementAt(k));
				}
			}
			else if(selContract_Type.equals("L"))
			{
				queryString1 = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
						+ " ,A.FCC_FLAG, LOA_BASE "
						+ " FROM DLNG_LOA_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
						+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' "
					//SB20200705			+ "AND A.FCC_FLAG='Y' "
						+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE B.CUSTOMER_CD='"+selCust_idForTruck+"' AND A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
						+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+nomModalDates.elementAt(k)+"','DD/MM/YYYY') AND B.status='Y') "
						+ " AND A.CUSTOMER_CD='"+selCust_idForTruck+"' AND TENDER_NO='"+flsaNo+"' AND LOA_NO='"+snNo+"'  "	
						+ "ORDER BY CUSTOMER_CD";
//					////System.out.println("Fetching LoA COntracts.."+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String DCQ=rset1.getString(5);
					VWeekly_DCQ_Temp.add(DCQ);
					VWeekly_Date_Temp.add(nomModalDates.elementAt(k));
				}
			}
		}
		
		int dtCnt=0;double totDcq=0;double truck_loaded_vol=0;int nomTruckCnt=0;int availTruckCnt=0;
		
		String start_dt =  "",end_dt="";
		
		if(VWeekly_Date_Temp.size() > 0) {
			start_dt =  VWeekly_Date_Temp.elementAt(0)+"";
			end_dt =  VWeekly_Date_Temp.elementAt(VWeekly_Date_Temp.size()-1)+"";
		}
		
//		//System.out.println("start_dt******"+start_dt);
//		//System.out.println("end_dt******"+end_dt);
		
		for(int m=0; m<VWeekly_Date_Temp.size(); m++)
		{
			dtCnt=0;
			totDcq= 0;
			truck_loaded_vol=0;
			nomTruckCnt=0;
			availTruckCnt=0;
			
			String avail_contract="N";
			queryString = "select * from dual where TO_DATE('"+VWeekly_Date_Temp.elementAt(m)+"','DD/MM/YYYY') >= TO_DATE('"+cont_st_dt+"','DD/MM/YYYY')"; 
//			//System.out.println("Avail Contract Check******: "+queryString);	
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				avail_contract = "Y";
			}else {
				avail_contract = "N";
			}
			VAvail_cont.add(avail_contract);
//			//System.out.println("avail_contract*****"+avail_contract);
			
			String maxRev = "select max(rev_no) from DLNG_WEEKLY_NOM where "
						+ " nom_dt = to_date ('"+VWeekly_Date_Temp.elementAt(m)+"','dd/mm/yyyy') "
						+ " and mapping_id like '"+temp_map_id+"' "
						+ " and nom_id like  '"+temp_nom_id+"' "
						+ " and  contract_type='"+selContract_Type+"'";
//			//System.out.println("maxRev**"+maxRev);
			rset = stmt.executeQuery(maxRev);
			if(rset.next()) {
				revNo=rset.getString(1)==null?"":rset.getString(1);
			}
			
			double truck_load_capacity =0;
			queryString7 = "SELECT  TRUCK_ID,TRUCK_NM,CUSTOMER_CD,TANK_VOL_M3,TANK_VOL_TON, A.TRANS_CD, LOAD_CAP, NEXT_LOAD_DAY,TRANS_NAME FROM DLNG_TANK_TRUCK_MST  A, DLNG_TRANS_MST B "
					+ "WHERE A.TRANS_CD=B.TRANS_CD AND A.STATUS ='Y' AND ( CUSTOMER_CD ='"+selCust_idForTruck+"' OR CUSTOMER_CD ='0' ) AND A.TRANS_CD IN "
					+ "(SELECT TRANS_CD FROM DLNG_CUST_TRANS_DTL WHERE CUST_CD ='"+selCust_idForTruck+"' AND FLAG='Y') "
					+ "ORDER BY TRUCK_ID ";
//			//System.out.println("Weekly GET Truck List: DLNG_TANK_TRUCK_MST: "+queryString7);				
			rset7 = stmt7.executeQuery(queryString7);
			while (rset7.next()) {
				
				String next_truck_avail_day="";
				String next_truck_avail_date="";
				String alloc_dt = "";
				String avail_truck="Y";
				String loadedTruck="";
				
//				//System.out.println("avail_truck********"+avail_truck);
				if(avail_truck.equals("Y"))
				{
					
					/* **************** Filter Nominated Trucks on Next Avail Days basis **************** */
					String nom_dt="";
					String nomAvail ="select "
							+ " to_char(max(nom_dt),'dd/mm/yyyy') "
							+ " from DLNG_WEEKLY_TRUCK_NOM_DTL where TRUCK_NM='"+rset7.getString(2)+"'"
							+ " and NOM_DT between TO_DATE('"+start_dt+"','DD/MM/YYYY') and  TO_DATE('"+end_dt+"','DD/MM/YYYY') ";
//							+ " and NOM_DT = TO_DATE('"+VWeekly_Date_Temp.elementAt(m)+"','DD/MM/YYYY') ";	
//					//System.out.println("nomAvail***"+nomAvail);
					rset3 = stmt1.executeQuery(nomAvail);
					if(rset3.next()) {
						nom_dt = rset3.getString(1)==null?"":rset3.getString(1);
					}
					String next_avail_days="";
					String next_avail_dt="";
					String nom_rev_no="";
					String nom_id="";
					String contTyp="";
					String notNomTruck=""; 
//					//System.out.println("nom_dt****"+nom_dt);
					if(!nom_dt.equals("")) {
						
						String prevNomId="select NOM_ID,CONTRACT_TYPE from DLNG_WEEKLY_TRUCK_NOM_DTL where NOM_DT = TO_DATE('"+nom_dt+"','DD/MM/YYYY')"
								+ " and TRUCK_NM='"+rset7.getString(2)+"'";
//						//System.out.println("prevNomId****"+prevNomId);
						rset3 = stmt1.executeQuery(prevNomId);
						if(rset3.next()) {

							nom_id = rset3.getString(1)==null?"":rset3.getString(1);
							contTyp = rset3.getString(2)==null?"":rset3.getString(2);
						}
//						//System.out.println("nom_id***"+nom_id);
						String nomAvailRev ="select max(REV_NO)  from DLNG_WEEKLY_TRUCK_NOM_DTL where"
								+ " NOM_DT = TO_DATE('"+nom_dt+"','DD/MM/YYYY') "
								+ " and NOM_ID like '"+temp_nom_id+"' "
//								+ " AND TRUCK_NM='"+rset7.getString(2)+"'"
								+ " AND CONTRACT_TYPE='"+contTyp+"' ";	
//						//System.out.println("nomAvailRev***"+nomAvailRev);
						rset3 = stmt1.executeQuery(nomAvailRev);
						if(rset3.next()) {
							nom_rev_no = rset3.getString(1)==null?"":rset3.getString(1);
						}
//						//System.out.println("nom_rev_no***"+nom_rev_no);
						if(nom_rev_no!="") {
							
							String nomAvailDays ="select NEXT_AVAIL_DAYS  from DLNG_WEEKLY_TRUCK_NOM_DTL where NOM_DT = TO_DATE('"+nom_dt+"','DD/MM/YYYY') "
									+ " and TRUCK_NM='"+rset7.getString(2)+"' and REV_NO='"+nom_rev_no+"' ";	
//							//System.out.println("nomAvailDays***"+nomAvailDays);
							rset3 = stmt1.executeQuery(nomAvailDays);
							if(rset3.next()) {
								next_avail_days = rset3.getString(1)==null?"":rset3.getString(1);
							}else {
								next_avail_days = "0";
							}
							if(Integer.parseInt(next_avail_days) > 0) {
//								//System.out.println("next_avail_days*******"+next_avail_days);
								String next_avail_dt_sql = "select TO_CHAR(TO_DATE('"+nom_dt+"','DD/MM/YYYY') +  "+next_avail_days+",'DD/MM/YYYY') FROM DUAL";
//								//System.out.println("next_avail_dt_sql***"+next_avail_dt_sql);
								rset1 = stmt1.executeQuery(next_avail_dt_sql);
								if(rset1.next()) {
									next_avail_dt = rset1.getString(1)==null?"":rset1.getString(1);
								}
								queryString = "select * from dual where TO_DATE('"+VWeekly_Date_Temp.elementAt(m)+"','DD/MM/YYYY') >= TO_DATE('"+next_avail_dt+"','DD/MM/YYYY')"; 
//								//System.out.println("queryString******: "+queryString);	
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									loadedTruck = "N";
								}else {
									loadedTruck = "Y";
								}
							}else {
								loadedTruck = "N";
							}
						}else {
							loadedTruck = "N";
						}
					}else {
						loadedTruck = "N";
					}
					
//					//System.out.println("rset7.getString(2)******"+rset7.getString(2)+"****loadedTruck**"+loadedTruck);
					dtCnt++;
					double capacity=rset7.getDouble(4);
					double loadPerc=rset7.getDouble(7);
					VWeekly_Truck_Actual_Cap.add(rset7.getString(4)==null?"0":rset7.getString(4));
					truck_load_capacity=(capacity*loadPerc/100);
					
					if(!VWeekly_DCQ_Temp.elementAt(m).equals("")) {
						totDcq+= Double.parseDouble(VWeekly_DCQ_Temp.elementAt(m)+"");
					}
									
					String loaded_truck = "SELECT TRUCK_NM,NOM_ID, TRUCK_VOL, TRUCK_ENE,TO_CHAR(ARRIVAL_DT,'DD/MM/YYYY'),ARRIVAL_TIME,"
							+ " REMARKS,TRANS_CD,NEXT_AVAIL_DAYS FROM DLNG_WEEKLY_TRUCK_NOM_DTL "
							+ " WHERE MAPPING_ID like '"+temp_map_id+"' AND TRUCK_NM ='"+rset7.getString(2)+"' "
							+ " AND NOM_DT between TO_DATE ('"+VWeekly_Date_Temp.elementAt(m) +"','DD/MM/YYYY')"
							+ " AND TO_DATE ('"+VWeekly_Date_Temp.elementAt(m) +"','DD/MM/YYYY') "
							+ " AND REV_NO = '"+revNo+"'"
							+ " and nom_id like '"+temp_nom_id+"' "
							+ " and  contract_type='"+selContract_Type+"'";
		
	//					////System.out.println("OnlyLoadedTruck Query***"+loaded_truck);
						rset1 = stmt.executeQuery(loaded_truck);
						if (rset1.next()) 
						{
							if(loadedTruck.equals("Y")) {
								
								String fetchPrevTruckNomData = "SELECT TRUCK_NM,NOM_ID, TRUCK_VOL, TRUCK_ENE,TO_CHAR(ARRIVAL_DT,'DD/MM/YYYY'),ARRIVAL_TIME,"
										+ " REMARKS,TRANS_CD,NEXT_AVAIL_DAYS FROM DLNG_WEEKLY_TRUCK_NOM_DTL "
										+ " WHERE  TRUCK_NM ='"+rset7.getString(2)+"' "
										+ " AND NOM_DT = TO_DATE ('"+nom_dt+"','DD/MM/YYYY')"
										+ " AND REV_NO = '"+nom_rev_no+"'"
										+ " and nom_id like '"+temp_nom_id+"' "
										+ " and  contract_type='"+contTyp+"'";
								
					//				//System.out.println("fetchPrevTruckNomData**Daily*"+fetchPrevTruckNomData);
									rset4 = stmt4.executeQuery(fetchPrevTruckNomData);
									if(rset4.next()) {
										
										VWeekly_Nom_ID.add(rset4.getString(2)==null?"":rset4.getString(2));
										VWeekly_DCQ.add(""+VWeekly_DCQ_Temp.elementAt(m));
										VWeekly_Date.add(""+VWeekly_Date_Temp.elementAt(m));
										VWeekly_Truck_ID.add("");
										VWeekly_Truck_Nm.add(rset4.getString(1)==null?"":rset4.getString(1));
									//100% capacity	VWeekly_Truck_Cap.add(rset7.getString(4)==null?"":rset7.getString(4));
										VWeekly_Truck_Cap.add(truck_load_capacity); //Perc Capacity
										VWeekly_Nom_VOL.add(rset4.getString(3)==null?"":rset4.getString(3));
										VWeekly_Nom_ENE.add(rset4.getString(4)==null?"":rset4.getString(4));
										VWeekly_Nom_MT.add(nf.format(Double.parseDouble(rset4.getString(3)) / convt_mmbtu_to_mt));
										VWeekly_Truck_ArvDt.add(rset4.getString(5)==null?"":rset4.getString(5));
										VWeekly_Truck_ArvTm.add(rset4.getString(6)==null?"":rset4.getString(6));
										VWeekly_Truck_Remark.add(rset4.getString(7)==null?"":rset4.getString(7));
										VWeekly_Trans_cd.add(rset4.getString(8)==null?"":rset4.getString(8));
										VLoadedNxt_avail_days.add(rset4.getString(9)==null?"":rset4.getString(9));
										
										if(nomId.equals(nom_id) && selContract_Type.equals(contTyp) && VWeekly_Date_Temp.elementAt(m).equals(nom_dt)){
//											//System.out.println("******nominated trucks********"+rset7.getString(2)+"**********"+rset4.getString(3));
											truck_loaded_vol+= Double.parseDouble(rset4.getString(3)==null?"0":rset4.getString(3));
										}
										
										nomTruckCnt++;
										
										truckLoadedFlag.add(loadedTruck);
										prevTruckNomDt.add(nom_dt);
										prevTruckNomRevNo.add(nom_rev_no);
										prevNomID.add(nom_id);
										prevContTyp.add(contTyp);
										prevTruckNm.add(rset7.getString(2)==null?"":rset7.getString(2));
									}
							}else {
								
								VWeekly_Nom_ID.add(rset1.getString(2)==null?"":rset1.getString(2));
								VWeekly_DCQ.add(""+VWeekly_DCQ_Temp.elementAt(m));
								VWeekly_Date.add(""+VWeekly_Date_Temp.elementAt(m));
								VWeekly_Truck_ID.add("");
								VWeekly_Truck_Nm.add(rset1.getString(1)==null?"":rset1.getString(1));
							//100% capacity	VWeekly_Truck_Cap.add(rset7.getString(4)==null?"":rset7.getString(4));
								VWeekly_Truck_Cap.add(truck_load_capacity); //Perc Capacity
								VWeekly_Nom_VOL.add(rset1.getString(3)==null?"":rset1.getString(3));
								VWeekly_Nom_ENE.add(rset1.getString(4)==null?"":rset1.getString(4));
								VWeekly_Nom_MT.add(nf.format(Double.parseDouble(rset1.getString(3)) / convt_mmbtu_to_mt));
								VWeekly_Truck_ArvDt.add(rset1.getString(5)==null?"":rset1.getString(5));
								VWeekly_Truck_ArvTm.add(rset1.getString(6)==null?"":rset1.getString(6));
								VWeekly_Truck_Remark.add(rset1.getString(7)==null?"":rset1.getString(7));
								VWeekly_Trans_cd.add(rset1.getString(8)==null?"":rset1.getString(8));
								VLoadedNxt_avail_days.add(rset1.getString(9)==null?"":rset1.getString(9));
								
								if(nomId.equals(nom_id) && selContract_Type.equals(contTyp) && VWeekly_Date_Temp.elementAt(m).equals(nom_dt)){
//									//System.out.println("******nominated trucks********"+rset7.getString(2)+"*********"+rset4.getString(3));
//									truck_loaded_vol+= Double.parseDouble(rset4.getString(3)==null?"0":rset4.getString(3));
								}
								nomTruckCnt++;
								
								truckLoadedFlag.add("");
								prevTruckNomDt.add("");
								prevTruckNomRevNo.add("");
								prevNomID.add("");
								prevContTyp.add("");
								prevTruckNm.add("");
								
							}
						}
						else
						{
							
							if(loadedTruck.equals("Y")) {
								
								String fetchPrevTruckNomData = "SELECT TRUCK_NM,NOM_ID, TRUCK_VOL, TRUCK_ENE,TO_CHAR(ARRIVAL_DT,'DD/MM/YYYY'),ARRIVAL_TIME,"
										+ " REMARKS,TRANS_CD,NEXT_AVAIL_DAYS FROM DLNG_WEEKLY_TRUCK_NOM_DTL "
										+ " WHERE  TRUCK_NM ='"+rset7.getString(2)+"' "
										+ " AND NOM_DT = TO_DATE ('"+nom_dt+"','DD/MM/YYYY')"
										+ " AND REV_NO = '"+nom_rev_no+"'"
										+ " and nom_id like '"+temp_nom_id+"' "
										+ " and  contract_type='"+contTyp+"'";
								
					//				//System.out.println("fetchPrevTruckNomData**Daily*"+fetchPrevTruckNomData);
									rset4 = stmt4.executeQuery(fetchPrevTruckNomData);
									if(rset4.next()) {
										
										VWeekly_Nom_ID.add(rset4.getString(2)==null?"":rset4.getString(2));
										VWeekly_DCQ.add(""+VWeekly_DCQ_Temp.elementAt(m));
										VWeekly_Date.add(""+VWeekly_Date_Temp.elementAt(m));
										VWeekly_Truck_ID.add("");
										VWeekly_Truck_Nm.add(rset4.getString(1)==null?"":rset4.getString(1));
									//100% capacity	VWeekly_Truck_Cap.add(rset7.getString(4)==null?"":rset7.getString(4));
										VWeekly_Truck_Cap.add(truck_load_capacity); //Perc Capacity
										VWeekly_Nom_VOL.add(rset4.getString(3)==null?"":rset4.getString(3));
										VWeekly_Nom_ENE.add(rset4.getString(4)==null?"":rset4.getString(4));
										VWeekly_Nom_MT.add(nf.format(Double.parseDouble(rset4.getString(3)) / convt_mmbtu_to_mt));
										VWeekly_Truck_ArvDt.add(rset4.getString(5)==null?"":rset4.getString(5));
										VWeekly_Truck_ArvTm.add(rset4.getString(6)==null?"":rset4.getString(6));
										VWeekly_Truck_Remark.add(rset4.getString(7)==null?"":rset4.getString(7));
										VWeekly_Trans_cd.add(rset4.getString(8)==null?"":rset4.getString(8));
										VLoadedNxt_avail_days.add(rset4.getString(9)==null?"":rset4.getString(9));
										
										if(nomId.equals(nom_id) && selContract_Type.equals(contTyp) && VWeekly_Date_Temp.elementAt(m).equals(nom_dt)){
//											//System.out.println("******nominated trucks********"+rset7.getString(2)+"****"+rset4.getString(3));
											truck_loaded_vol+= Double.parseDouble(rset4.getString(3)==null?"0":rset4.getString(3));
										}
										nomTruckCnt++;
										
										truckLoadedFlag.add(loadedTruck);
										prevTruckNm.add(rset7.getString(2)==null?"":rset7.getString(2));
										prevTruckNomDt.add(nom_dt);
										prevTruckNomRevNo.add(nom_rev_no);
										prevNomID.add(nom_id);
										prevContTyp.add(contTyp);
									}
									
							}else {
							
								VWeekly_Nom_ID.add(""+NominationId);
								VWeekly_DCQ.add(""+VWeekly_DCQ_Temp.elementAt(m));
								VWeekly_Date.add(""+VWeekly_Date_Temp.elementAt(m));
								VWeekly_Truck_ID.add(rset7.getString(1)==null?"":rset7.getString(1));
								VWeekly_Truck_Nm.add(rset7.getString(2)==null?"":rset7.getString(2));
								//100% capacity	VWeekly_Truck_Cap.add(rset7.getString(4)==null?"":rset7.getString(4));
								VWeekly_Truck_Cap.add(truck_load_capacity); //Perc Capacity
								VWeekly_Nom_VOL.add("0");
								VWeekly_Nom_ENE.add("0");
								VWeekly_Nom_MT.add("");
								VWeekly_Truck_ArvDt.add("");
								VWeekly_Truck_ArvTm.add("");
								VWeekly_Truck_Remark.add("");
								VWeekly_Trans_cd.add(rset7.getString(6)==null?"":rset7.getString(6));
								VLoadedNxt_avail_days.add("");
								
								availTruckCnt++;
								
								truckLoadedFlag.add("");
								prevTruckNomDt.add("");
								prevTruckNomRevNo.add("");
								prevNomID.add("");
								prevContTyp.add("");
								prevTruckNm.add("");
							}	
						}
					}
				}
				VdateCnt.add(dtCnt);	
				VtotTruckLoadedVol.add(truck_loaded_vol);
				VavailTruckCnt.add(availTruckCnt);
				VnomTruckCnt.add(nomTruckCnt);
			}
		
		/* ******fetching Already Loaded truck Data********** */
			for(int i = 0 ; i < VWeekly_Nom_ID.size(); i++) {
			if(truckLoadedFlag.elementAt(i).equals("Y")) {
					if(prevNomID.elementAt(i).toString().contains("-")) {
//						//System.out.println("prevNomID.elementAt(i)******1**"+prevNomID.elementAt(i));
						String nomIdArr[] = prevNomID.elementAt(i).toString().split("-");
						
						String custSQL="select CUSTOMER_ABBR from FMS7_CUSTOMER_MST where CUSTOMER_CD='"+nomIdArr[0]+"'";
						rset4 = stmt3.executeQuery(custSQL);
						if(rset4.next()) {
							prevCust_Nm.add(rset4.getString(1)==null?"":rset4.getString(1));
						}else {
							prevCust_Nm.add("");
						}
						
						String contType="";
						if(prevContTyp.elementAt(i).equals("S")) {
							contType ="SN - "+nomIdArr[3];
						}else if(prevContTyp.elementAt(i).equals("L")) {
							contType ="LoA - "+nomIdArr[3];
						}
						prevSn.add(contType);
						
						queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
								   "WHERE A.customer_cd="+nomIdArr[0]+" AND A.seq_no="+nomIdArr[6]+" " +
								   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
								   "AND B.eff_dt<=TO_DATE('"+prevTruckNomDt.elementAt(i)+"','DD/MM/YYYY'))";
						
						rset5 = stmt5.executeQuery(queryString3);
						if(rset5.next()) {
							prevPlant.add(rset5.getString(1)==null?"":rset5.getString(1)); 
						}else {
							prevPlant.add("");
						}
					}else {
						prevPlant.add("");
						prevSn.add("");
						prevCust_Nm.add("");
					}
				}else {
					prevPlant.add("");
					prevSn.add("");
					prevCust_Nm.add("");
				}
			}
			/*//System.out.println("prevPlant****"+prevPlant.size());
			//System.out.println("prevSn****"+prevSn.size());
			//System.out.println("prevCust_Nm****"+prevCust_Nm.size());
			//System.out.println("truckLoadedFlag****"+truckLoadedFlag.size());
			//System.out.println("prevTruckNm****"+prevTruckNm.size());
			//System.out.println("prevTruckNomDt****"+prevTruckNomDt.size());
			//System.out.println("prevTruckNomRevNo****"+prevTruckNomRevNo.size());
			//System.out.println("prevNomID****"+prevNomID.size());
			//System.out.println("prevContTyp****"+prevContTyp.size());
			//System.out.println("VWeekly_Nom_ID****"+VWeekly_Nom_ID.size());*/
			
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
////////////////////////////////////////////>>>>>>>>>>>>>>>>>>>>>>>

		public Vector getVtotTruckLoadedVol() {
	return VtotTruckLoadedVol;
}

		public Vector getVdateCnt() {
	return VdateCnt;
}

		public Vector VweeklyNomFlag = new Vector();
		public Vector getVweeklyNomFlag() {
			return VweeklyNomFlag;
		}

		public String weeklyNomFlag ="";
		public Vector OnlyLoadedNom_id = new Vector();
		public Vector OnlyLoadedNomDt = new Vector();
		public Vector getVLoadedTruck() {return VLoadedTruck;}
		public Vector getOnlyLoadedNomDt() {return OnlyLoadedNomDt;}
		
		public void setWeeklyNomFlag(String weeklyNomFlag) {this.weeklyNomFlag = weeklyNomFlag;}
//////////////////////////////////////////////////////////////////////////
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
		public double getInt_tank_ene() {return int_tank_ene;} //SB20181219
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
		public double getInt_tankCapacityM3() {	return int_tankCapacityM3;} //SB20181219
		public double getInt_tankVolAvlM3() {	return int_tankVolAvlM3;} //SB20181219
		public double getInt_tankVolAvl() {	return int_tankVolAvl;} //SB20181219
		
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
		public Vector getTank_truck_capacityM3() {return tank_truck_capacityM3;	} //SB20181219
		
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
		public Map getTruck_loaded_ene() {return truck_loaded_ene;} //SB20181219
		public Map getTruck_unloaded_vol() {return truck_unloaded_vol;} //SB20181219
		public Map getTruck_unloaded_ene() {return truck_unloaded_ene;} //SB20181219
		public Map getTruck_status() {return truck_status;} //SB20181219
		
		public void setTruck_loaded_vol(Map <String, String> truck_loaded_vol) {
			this.truck_loaded_vol = truck_loaded_vol;
		}
		
		public Vector daily_Sch_Mapping_Id = new Vector(); //SB20181229
		public Vector daily_Seller_Rev_No= new Vector();
		public Vector allocated_qty_mmbtu= new Vector();//HA20200304
		public Vector balance_qty_mmbtu= new Vector();//HA20200304
		public Vector getDaily_Sch_Mapping_Id() {return daily_Sch_Mapping_Id;} //SB20181229

		public Map<String, String> getTruck_flag() {
			return truck_flag;
		}

		public Map<String, String> getLast_eff_dt() {
			return last_eff_dt;
		}

		public Vector getCustIDAT() {
			return custIDAT;
		}

		public void setCustIDAT(Vector custIDAT) {
			this.custIDAT = custIDAT;
		}

		public Vector getTruckIdAT() {
			return truckIdAT;
		}

		public void setTruckIdAT(Vector truckIdAT) {
			this.truckIdAT = truckIdAT;
		}

		public Vector getTruckNameAT() {
			return truckNameAT;
		}

		public void setTruckNameAT(Vector truckNameAT) {
			this.truckNameAT = truckNameAT;
		}

		public String getCustidForTruk() {
			return custidForTruk;
		}

		public void setCustidForTruk(String custidForTruk) {
			this.custidForTruk = custidForTruk;
		}



		public String getSelCust_id() {
			return selCust_id;
		}



		public void setSelCust_id(String selCust_id) {
			this.selCust_id = selCust_id;
		}



		public String getIndx() {
			return indx;
		}



		public void setIndx(String indx) {
			this.indx = indx;
		}



		public Vector getTankVolM3AT() {
			return tankVolM3AT;
		}



		public void setTankVolM3AT(Vector tankVolM3AT) {
			this.tankVolM3AT = tankVolM3AT;
		}



		public Vector getTankVolTonAT() {
			return tankVolTonAT;
		}



		public void setTankVolTonAT(Vector tankVolTonAT) {
			this.tankVolTonAT = tankVolTonAT;
		}



		public String getBuyer_mapping_id() {
			return buyer_mapping_id;
		}



		public void setBuyer_mapping_id(String buyer_mapping_id) {
			this.buyer_mapping_id = buyer_mapping_id;
		}



		public String getNomId() {
			return nomId;
		}



		public void setNomId(String nomId) {
			this.nomId = nomId;
		}



		public String getNomDt() {
			return nomDt;
		}



		public void setNomDt(String nomDt) {
			this.nomDt = nomDt;
		}



		public String getRevNo() {
			return revNo;
		}



		public void setRevNo(String revNo) {
			this.revNo = revNo;
		}



		public Vector getTloadedVol() {
			return TloadedVol;
		}

		public Vector getTloadedEne() {
			return TloadedEne;
		}


		public Vector getTloadedDt() {
			return TloadedDt;
		}

		public Vector getTloadedTm() {
			return TloadedTm;
		}

		public Vector getTloadedRemark() {
			return TloadedRemark;
		}

		public void setTloadedRemark(Vector tloadedRemark) {
			TloadedRemark = tloadedRemark;
		}

		
		
		
		public String getSelSchedulCust() {
			return selSchedulCust;
		}

		public void setSelSchedulCust(String selSchedulCust) {
			this.selSchedulCust = selSchedulCust;
		}
				

		public Vector getTruckidDST() {
			return truckidDST;
		}

		public void setTruckidDST(Vector truckidDST) {
			this.truckidDST = truckidDST;
		}

		public Vector getTrucknmDST() {
			return trucknmDST;
		}

		public void setTrucknmDST(Vector trucknmDST) {
			this.trucknmDST = trucknmDST;
		}

		public Vector getTruckvolM3DST() {
			return truckvolM3DST;
		}

		public void setTruckvolM3DST(Vector truckvolM3DST) {
			this.truckvolM3DST = truckvolM3DST;
		}

		public Vector getTruckvolTonDST() {
			return truckvolTonDST;
		}

		public void setTruckvolTonDST(Vector truckvolTonDST) {
			this.truckvolTonDST = truckvolTonDST;
		}
		
		public Vector getCustidDST() {
			return custidDST;
		}

		public void setCustidDST(Vector custidDST) {
			this.custidDST = custidDST;
		}

		public Vector getTscheduleVol() {
			return TscheduleVol;
		}

		public void setTscheduleVol(Vector tscheduleVol) {
			TscheduleVol = tscheduleVol;
		}

		public Vector getTscheduleEne() {
			return TscheduleEne;
		}

		public void setTscheduleEne(Vector tscheduleEne) {
			TscheduleEne = tscheduleEne;
		}

		public Vector getTscheduleDt() {
			return TscheduleDt;
		}

		public void setTscheduleDt(Vector tscheduleDt) {
			TscheduleDt = tscheduleDt;
		}

		public Vector getTscheduleTm() {
			return TscheduleTm;
		}

		public void setTscheduleTm(Vector tscheduleTm) {
			TscheduleTm = tscheduleTm;
		}

		public Vector getTscheduleRemark() {
			return TscheduleRemark;
		}

		public void setTscheduleRemark(Vector tscheduleRemark) {
			TscheduleRemark = tscheduleRemark;
		}

		
		public Vector getSchedulVol() {
			return schedulVol;
		}

		public void setSchedulVol(Vector schedulVol) {
			this.schedulVol = schedulVol;
		}

		public Vector getSchedulEne() {
			return schedulEne;
		}
		
		public void setSchedulEne(Vector schedulEne) {
			this.schedulEne = schedulEne;
		}

		public Vector getSchedulDt() {
			return schedulDt;
		}

		public void setSchedulDt(Vector schedulDt) {
			this.schedulDt = schedulDt;
		}

		public Vector getSchedulTm() {
			return schedulTm;
		}

		public void setSchedulTm(Vector schedulTm) {
			this.schedulTm = schedulTm;
		}

		public Vector getSchedulRemark() {
			return schedulRemark;
		}

		public void setSchedulRemark(Vector schedulRemark) {
			this.schedulRemark = schedulRemark;
		}

		public String getSchRevNo() {
			return schRevNo;
		}

		public void setSchRevNo(String schRevNo) {
			this.schRevNo = schRevNo;
		}

		public String getSchId() {
			return schId;
		}

		public void setSchId(String schId) {
			this.schId = schId;
		}

		public Vector getTNominatedOrNt() {
			return TNominatedOrNt;
		}


		public int getTruckNomCnt() {
			return truckNomCnt;
		}


		public Vector getTruckCnt() {
			return truckCnt;
		}


		public Vector getDaily_Seller_Rev_No() {
			return daily_Seller_Rev_No;
		}


		public void setDaily_Seller_Rev_No(Vector daily_Seller_Rev_No) {
			this.daily_Seller_Rev_No = daily_Seller_Rev_No;
		}

		public Vector getSch_truck_vol() {
			return sch_truck_vol;
		}

		public void setSch_truck_vol(Vector sch_truck_vol) {
			this.sch_truck_vol = sch_truck_vol;
		}

		public Map<String, String> getTruck_loaded_scm() {
			return truck_loaded_scm;
		}

		public Map<String, String> getTruck_unloaded_scm() {
			return truck_unloaded_scm;
		}
		public Vector getSch_truck_scm() {
			return sch_truck_scm;
		}
		public Vector getAllocated_qty_mmbtu() {
			return allocated_qty_mmbtu;
		}
		public Vector getBalance_qty_mmbtu() {return balance_qty_mmbtu;} //SB20200723	
//--------- SUJIT05MARCH2020 -------
		public Vector getDaily_Buyer_Nom_Dcq_Mt() {
			return daily_Buyer_Nom_Dcq_Mt;
		}

		public void setDaily_Buyer_Nom_Dcq_Mt(Vector daily_Buyer_Nom_Dcq_Mt) {
			this.daily_Buyer_Nom_Dcq_Mt = daily_Buyer_Nom_Dcq_Mt;
		}

		public Vector getDaily_Seller_Nom_Dcq_Mt() {
			return daily_Seller_Nom_Dcq_Mt;
		}

		public void setDaily_Seller_Nom_Dcq_Mt(Vector daily_Seller_Nom_Dcq_Mt) {
			this.daily_Seller_Nom_Dcq_Mt = daily_Seller_Nom_Dcq_Mt;
		}
//--------- SUJIT13MARCH2020 -------
		public String getCustPlant_cd() {
			return custPlant_cd;
		}
		public void setCustPlant_cd(String custPlant_cd) {
			this.custPlant_cd = custPlant_cd;
		}
		public Vector getCustPlant_cdFlag() {
			return custPlant_cdFlag;
		}
//--------- HA18MARCH2020 -------
		public Vector getOnlyLoadedTruck() {
			return OnlyLoadedTruck;
		}
		
		
		public Vector VContSequanceNo = new Vector();
		public Vector getVContSequanceNo() {return VContSequanceNo;}
		//////////////////////SB20200718/////////////////////////////////
		public Vector VContSignDt = new Vector();
		public Vector getVContSignDt() {return VContSignDt;}
		public Vector VBuyer_Fcc_Flag = new Vector();
		public Vector getVBuyer_Fcc_Flag() {return VBuyer_Fcc_Flag;}
		public Vector VBuyer_Inv_Flag = new Vector();
		public Vector getVBuyer_Inv_Flag() {return VBuyer_Inv_Flag;}
		public Vector VBuyer_Delv_Base = new Vector();
		public Vector getVBuyer_Delv_Base() {return VBuyer_Delv_Base;}
		public String selDelvBase ="";
		public void setselDelvBase(String selDelvBase) {this.selDelvBase = selDelvBase;}
		public Vector daily_tax_struct_dtl = new Vector();
		public Vector getDaily_tax_struct_dtl() {return daily_tax_struct_dtl;}
		public Vector nomModalDates = new Vector();  //SUJIT
		public void setNomModalDates(Vector nomModalDates) {this.nomModalDates = nomModalDates;} //SUJIT
		public Vector TloadedTruckNm = new Vector();//SUJIT
		public Vector getTloadedTruckNm() {	return TloadedTruckNm;}//SUJIT
		public Vector TloadedTransCd = new Vector();//SUJIT
		public Vector getTloadedTransCd() {	return TloadedTransCd;}//SUJIT
		
		public Vector VWeekly_Buyer_Nom_Qty_Mmbtu = new Vector();//SUJIT
		public Vector getVWeekly_Buyer_Nom_Qty_Mmbtu() {	return VWeekly_Buyer_Nom_Qty_Mmbtu;}//SUJIT
		public Vector VWeekly_Buyer_Nom_Qty_scm = new Vector();//SUJIT
		public Vector getVWeekly_Buyer_Nom_Qty_scm() {	return VWeekly_Buyer_Nom_Qty_scm;}//SUJIT
		public Vector schedulTruckNm = new Vector();//SUJIT
		public Vector getSchedulTruckNm() {return schedulTruckNm;}//SUJIT
		
		public Vector VWeekly_Buyer_Nom_ID = new Vector();//SUJIT
		Vector VWeekly_Buyer_Cont_st_dt = new Vector();
		String cont_st_dt="";
		public Vector getVWeekly_Buyer_Nom_ID() {	return VWeekly_Buyer_Nom_ID;}//SUJIT
		
		public Vector VWeekly_Nom_ID = new Vector();
		public Vector getVWeekly_Nom_ID() {	return VWeekly_Nom_ID;}//SB
		public Vector VWeekly_Nom_ENE = new Vector();
		public Vector getVWeekly_Nom_ENE() {	return VWeekly_Nom_ENE;}//SB
		public Vector VWeekly_Nom_MT = new Vector();
		public Vector getVWeekly_Nom_MT() {	return VWeekly_Nom_MT;}//SB
		public Vector VWeekly_Nom_VOL = new Vector();
		public Vector getVWeekly_Nom_VOL() {	return VWeekly_Nom_VOL;}//SB
		public Vector VWeekly_Date = new Vector();
		public Vector getVWeekly_Date() {	return VWeekly_Date;}//SB
		public Vector VWeekly_DCQ = new Vector();
		public Vector getVWeekly_DCQ() {	return VWeekly_DCQ;}//SB
		public Vector VWeekly_Truck_ID = new Vector();
		public Vector getVWeekly_Truck_ID() {	return VWeekly_Truck_ID;}//SB
		public Vector VWeekly_Truck_Nm = new Vector();
		public Vector getVWeekly_Truck_Nm() {	return VWeekly_Truck_Nm;}//SB
		public Vector VWeekly_Truck_Cap = new Vector();
		public Vector VWeekly_Truck_Actual_Cap = new Vector();
		public Vector getVWeekly_Truck_Cap() {	return VWeekly_Truck_Cap;}//SB
		public Vector VWeekly_Truck_ArvDt = new Vector();
		public Vector getVWeekly_Truck_ArvDt() {	return VWeekly_Truck_ArvDt;}//SB
		public Vector VWeekly_Truck_ArvTm = new Vector();
		public Vector getVWeekly_Truck_ArvTm() {	return VWeekly_Truck_ArvTm;}//SB
		public Vector VWeekly_Truck_Remark = new Vector();
		public Vector getVWeekly_Truck_Remark() {	return VWeekly_Truck_Remark;}//SB
		public Vector VWeekly_Trans_cd = new Vector();
		public Vector VdateCnt = new Vector();
		public Vector VTruck_nomi_mmbtu = new Vector();
		public Vector VTruck_sch_mmbtu = new Vector();
		public Vector VTruck_bal_mmbtu = new Vector();
		public Vector VnomTruckCnt = new Vector();
		public Vector getVnomTruckCnt() {
			return VnomTruckCnt;
		}

		public Vector VavailTruckCnt = new Vector();
		public Vector getVavailTruckCnt() {
			return VavailTruckCnt;
		}

		public Vector VtotTruckLoadedVol = new Vector();

		public Vector getVWeekly_Trans_cd() {return VWeekly_Trans_cd;}
		
		public String selContract_Type = "";
		public void setSelContType(String selContract_Type) {this.selContract_Type = selContract_Type;}

		public String getSelContract_Type() {
			return selContract_Type;
		}

		public Vector VWeekly_Sch_ID = new Vector();
		public Vector getVWeekly_Sch_ID() {return VWeekly_Sch_ID;}
		public Vector VWeekly_Sch_VOL = new Vector();
		public Vector getVWeekly_Sch_VOL() {return VWeekly_Sch_VOL;}
		public Vector VWeekly_Sch_ENE = new Vector();
		public Vector getVWeekly_Sch_ENE() {return VWeekly_Sch_ENE;}
		public Vector VWeekly_Sch_MT = new Vector();
		public Vector getVWeekly_Sch_MT() {return VWeekly_Sch_MT;}
		public Vector VWeekly_Truck_SchDt = new Vector();
		public Vector getVWeekly_Truck_SchDt() {return VWeekly_Truck_SchDt;}
		public Vector VWeekly_Truck_SchTm = new Vector();
		public Vector getVWeekly_Truck_SchTm() {return VWeekly_Truck_SchTm;}

		public Vector VWeekly_Seller_Nom_ID = new Vector();
		public Vector getVWeekly_Seller_Nom_ID() {return VWeekly_Seller_Nom_ID;}
		public Vector getVWeekly_Truck_Actual_Cap() {
			return VWeekly_Truck_Actual_Cap;
		}

		public Vector getVTruck_nomi_mmbtu() {
			return VTruck_nomi_mmbtu;
		}

		public Vector getVTruck_sch_mmbtu() {
			return VTruck_sch_mmbtu;
		}

		public Vector getVTruck_bal_mmbtu() {
			return VTruck_bal_mmbtu;
		}

		public Vector getVLoadedNxt_avail_days() {
			return VLoadedNxt_avail_days;
		}

		public Vector getTruckLoadedFlag() {
			return truckLoadedFlag;
		}

		public Vector getVLoadedTruckNm() {
			return VLoadedTruckNm;
		}

		public String getContract_type() {
			return contract_type;
		}

		public void setContract_type(String contract_type) {
			this.contract_type = contract_type;
		}

		public Vector getPrevCust_Nm() {
			return prevCust_Nm;
		}

		public Vector getPrevSn() {
			return prevSn;
		}

		public Vector getPrevContTyp() {
			return prevContTyp;
		}

		public Vector getPrevPlant() {
			return prevPlant;
		}

		public Vector getPrevNomID() {
			return prevNomID;
		}

		public Vector getPrevTruckNomDt() {
			return prevTruckNomDt;
		}

		public Map<String, String> getGcv_per_mmbtu() {
			return gcv_per_mmbtu;
		}

		public Vector getVWeekly_Buyer_Cont_st_dt() {
			return VWeekly_Buyer_Cont_st_dt;
		}

		public String getCont_st_dt() {
			return cont_st_dt;
		}

		public void setCont_st_dt(String cont_st_dt) {
			this.cont_st_dt = cont_st_dt;
		}

		public Vector getVAvail_cont() {
			return VAvail_cont;
		}

		public Vector getVtruck_Inv_Flag() {
			return Vtruck_Inv_Flag;
		}

		public Vector getVtruck_Inv_qty() {
			return Vtruck_Inv_qty;
		}

		public double getTotal_all_inv_qty() {
			return total_all_inv_qty;
		}

		public String getNcv_gcv() {
			return ncv_gcv;
		}

		public void setNcv_gcv(String ncv_gcv) {
			this.ncv_gcv = ncv_gcv;
		}

		public String getTot_inv_mmbtu() {
			return tot_inv_mmbtu;
		}

		public String getTot_inv_scm() {
			return tot_inv_scm;
		}

		public String getTot_inv_mt() {
			return tot_inv_mt;
		}

		public double getConvt_mmbtu_to_mt() {
			return convt_mmbtu_to_mt;
		}

		public void setConvt_mmbtu_to_mt(double convt_mmbtu_to_mt) {
			this.convt_mmbtu_to_mt = convt_mmbtu_to_mt;
		}

		public Vector getVtruck_Inv_qty_mt() {
			return Vtruck_Inv_qty_mt;
		}

		public Vector getDaily_Buyer_Rev_No() {
			return daily_Buyer_Rev_No;
		}

		public Vector getTotal_inv_qty_mmbtu() {
			return total_inv_qty_mmbtu;
		}

		public Vector getTotal_inv_qty_scm() {
			return total_inv_qty_scm;
		}

		public double getTotal_all_inv_qty_scm() {
			return total_all_inv_qty_scm;
		}

		public Vector getTotal_inv_qty_mt() {
			return total_inv_qty_mt;
		}

		public Vector getvTotal_Sch_mmbtu() {
			return vTotal_Sch_mmbtu;
		}

		public Vector getRev_bal_qty() {
			return rev_bal_qty;
		}

		public Vector getvTotal_loaded_mmbtu() {
			return vTotal_loaded_mmbtu;
		}

		public String getInv_gen_flag() {
			return inv_gen_flag;
		}

		public void setInv_gen_flag(String inv_gen_flag) {
			this.inv_gen_flag = inv_gen_flag;
		}

		public Vector getAll_inv_gen_flg() {
			return all_inv_gen_flg;
		}

		public Vector getVtruck_alloc_flag() {
			return Vtruck_alloc_flag;
		}

		public Vector getVtruck_alloc_mmbtu() {
			return Vtruck_alloc_mmbtu;
		}

		public Vector getVtruck_alloc_scm() {
			return Vtruck_alloc_scm;
		}

		public Vector getVtruck_alloc_mt() {
			return Vtruck_alloc_mt;
		}

		public String getBalance_adv_amt() {
			return balance_adv_amt;
		}

		public String getSales_Rate() {
			return Sales_Rate;
		}

		public String getTax_rate() {
			return tax_rate;
		}

		public String getExg_rate() {
			return exg_rate;
		}

		public Vector getMax_nom_rev_no() {
			return max_nom_rev_no;
		}

		public Vector getDaily_adv_mapping_id() {
			return daily_adv_mapping_id;
		}

		public String getSelMapId() {
			return selMapId;
		}

		public void setSelMapId(String selMapId) {
			this.selMapId = selMapId;
		}

		public void setSysdate(String sysdate) {
			this.sysdate = sysdate;
		}

		public Vector getCont_closure_close() {
			return cont_closure_close;
		}

		public Vector getCont_closure_request() {
			return cont_closure_request;
		}

		public Vector getCont_adv_collection() {
			return cont_adv_collection;
		}

		public String getAdv_collection_flg() {
			return adv_collection_flg;
		}

		public void setAdv_collection_flg(String adv_collection_flg) {
			this.adv_collection_flg = adv_collection_flg;
		}

		public String getOa_flag() {
			return oa_flag;
		}

		public double getSec_amt() {
			return sec_amt;
		}

		public String getSecurity() {
			return security;
		}
		
}