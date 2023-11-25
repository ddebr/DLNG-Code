package com.seipl.hazira.dlng.market_risk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;

public class DB_VariablePricing {
	
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
	
	String queryString="";
	String queryString1="";
	String queryString2="";
	String queryString3="";
	String queryString4="";
	String queryString5="";
	String queryString6="";
	String queryString7="";
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf1 = new DecimalFormat("###########0.000");
	NumberFormat nf2 = new DecimalFormat("###########0.0000");
	NumberFormat nf3 = new DecimalFormat("###,###,###,##0");
	
	public String callFlag="";
	public void setCallFlag(String callFlag) {this.callFlag = callFlag;}
	
	String from_date ="";
	public void setFrom_date(String from_date) {this.from_date = from_date;}

	String to_date = "";
	public void setTo_date(String to_date) {this.to_date = to_date;}

	
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
	    			
	    			if(callFlag.equalsIgnoreCase("PriceInformation")) //THIS IS USED FOR SN/LOA DEAL FORMs //ALSO USED FOR CARGO BANK/MANDATE CONFIRM
	    			{
	    				System.out.println("Price_MappId=="+Price_MappId);
	    				System.out.println("Price_ContType=="+Price_ContType);
	    				System.out.println("Price_DelvStartDt=="+Price_DelvStartDt);
	    				System.out.println("Price_DelvEndDt=="+Price_DelvEndDt);
	    				System.out.println("Price_GasDate=="+Price_GasDate);
	    				getMonthWiseDate();
	    				
	    			}
	    			
	    			if(callFlag.equalsIgnoreCase("EXPOSURE_TRACKING_CR"))
	    			{
	    				int count=0;	int count2=0;	
	    				String query="select to_date('"+Price_GasDate+"','DD/MM/YY')- to_date('"+Price_DelvStartDt+"','DD/MM/YY'),"
	    						+ "to_date('"+Price_DelvEndDt+"','DD/MM/YY')- to_date('"+Price_GasDate+"','DD/MM/YY') "
	    						+ "from dual";
	    				//	System.out.println("Date Difference: dual: "+query);
	    				rset9=stmt9.executeQuery(query);
	    				if(rset9.next())
	    				{
	    					count=rset9.getInt(1);
	    					count2=rset9.getInt(2);
	    				}
	    				//System.out.println(count+" dual: "+count2);
	    				
	    				if(count>=0 && count2>=0) {
	    					Price_GasDate=Price_GasDate;
	    				}
	    				else if(count>=0 && count2<0) {
	    					Price_GasDate=Price_DelvEndDt; //Contract Expired = SETTLED
	    				}
	    				else if(count<0 && count2>=0) {
	    					Price_GasDate=Price_DelvStartDt; //Contract Yet to start = FWD
	    				}
	    				else if(count<0 && count2<0) {
	    					Price_GasDate=Price_DelvStartDt; //Contract Yet to start = FWD
	    				}
	    				
	    				//System.out.println("Price_MappId=="+Price_MappId);
	    				//System.out.println("Price_ContType=="+Price_ContType);
	    				//System.out.println("Price_DelvStartDt=="+Price_DelvStartDt);
	    				//System.out.println("Price_DelvEndDt=="+Price_DelvEndDt);
	    				//System.out.println("Price_GasDate=="+Price_GasDate);
	    				
	    				PRICE = FloatingPricingCalculation(Price_MappId, Price_ContType, Price_DelvStartDt, Price_DelvEndDt, Price_GasDate);
	    			}
	    	
	    			conn.close();
	    		}
	    	}
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	if(rset != null){try{rset.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset1 != null){try{rset1.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset2 != null){try{rset2.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset3 != null){try{rset3.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset4 != null){try{rset4.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset5 != null){try{rset5.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset6 != null){try{rset6.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset7 != null){try{rset7.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset8 != null){try{rset8.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset9 != null){try{rset9.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt != null){try{stmt.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt1 != null){try{stmt1.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt2 != null){try{stmt2.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt3 != null){try{stmt3.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt4 != null){try{stmt4.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt5 != null){try{stmt5.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt6 != null){try{stmt6.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt7 != null){try{stmt7.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt8 != null){try{stmt8.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt9 != null){try{stmt9.close();}catch(SQLException e){e.printStackTrace();}}
			if(conn != null){try{conn.close();}catch(SQLException e){e.printStackTrace();}}
	    }
	}
	
	//FOLLOWING FUNCATION IS USED FOR AUTOMATION PROCESS
	public void init_v2(String dbline,String username, String password)
	{
	    try
	    {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(dbline,username,password);
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
    			
    			if(callFlag.equalsIgnoreCase("EXPOSURE_TRACKING_CR"))
    			{
    				int count=0;	int count2=0;	
    				String query="select to_date('"+Price_GasDate+"','DD/MM/YY')- to_date('"+Price_DelvStartDt+"','DD/MM/YY'),"
    						+ "to_date('"+Price_DelvEndDt+"','DD/MM/YY')- to_date('"+Price_GasDate+"','DD/MM/YY') "
    						+ "from dual";
    				//	System.out.println("Date Difference: dual: "+query);
    				rset9=stmt9.executeQuery(query);
    				if(rset9.next())
    				{
    					count=rset9.getInt(1);
    					count2=rset9.getInt(2);
    				}
    				//System.out.println(count+" dual: "+count2);
    				
    				if(count>=0 && count2>=0) {
    					Price_GasDate=Price_GasDate;
    				}
    				else if(count>=0 && count2<0) {
    					Price_GasDate=Price_DelvEndDt; //Contract Expired = SETTLED
    				}
    				else if(count<0 && count2>=0) {
    					Price_GasDate=Price_DelvStartDt; //Contract Yet to start = FWD
    				}
    				else if(count<0 && count2<0) {
    					Price_GasDate=Price_DelvStartDt; //Contract Yet to start = FWD
    				}
    				
    				//System.out.println("Price_MappId=="+Price_MappId);
    				//System.out.println("Price_ContType=="+Price_ContType);
    				//System.out.println("Price_DelvStartDt=="+Price_DelvStartDt);
    				//System.out.println("Price_DelvEndDt=="+Price_DelvEndDt);
    				//System.out.println("Price_GasDate=="+Price_GasDate);
    				
    				PRICE = FloatingPricingCalculation(Price_MappId, Price_ContType, Price_DelvStartDt, Price_DelvEndDt, Price_GasDate);
    			}
    	
    			conn.close();
    		}
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	if(rset != null){try{rset.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset1 != null){try{rset1.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset2 != null){try{rset2.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset3 != null){try{rset3.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset4 != null){try{rset4.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset5 != null){try{rset5.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset6 != null){try{rset6.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset7 != null){try{rset7.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset8 != null){try{rset8.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(rset9 != null){try{rset9.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt != null){try{stmt.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt1 != null){try{stmt1.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt2 != null){try{stmt2.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt3 != null){try{stmt3.close();}catch(SQLException e){e.printStackTrace();}}
	    	if(stmt4 != null){try{stmt4.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt5 != null){try{stmt5.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt6 != null){try{stmt6.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt7 != null){try{stmt7.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt8 != null){try{stmt8.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt9 != null){try{stmt9.close();}catch(SQLException e){e.printStackTrace();}}
			if(conn != null){try{conn.close();}catch(SQLException e){e.printStackTrace();}}
	    }
	}
	
	public void getMonthWiseDate()
	{
		try
		{
			String price_type="";
			queryString ="SELECT PRICE_TYPE "
					+ " FROM FMS9_MRCR_CONT_PRICE_DTL " 
					+ "WHERE MAPPING_ID LIKE '"+Price_MappId+"' AND CONTRACT_TYPE='"+Price_ContType+"' AND FLAG='Y' "; 
			//System.out.println("FMS9_CURVE_PRICE_DTL: "+queryString);
			rset9 = stmt9.executeQuery(queryString);
			if(rset9.next())
			{
				price_type = rset9.getString(1)==null?"":rset9.getString(1);
			}
			if(price_type.equals("M"))
			{
				int count=0;	int count2=0; String final_start_dt="",final_end_dt="";	
				String query="select to_date('"+Price_GasDate+"','DD/MM/YY')- to_date('"+Price_DelvStartDt+"','DD/MM/YY'),"
						+ "to_date('"+Price_GasDate+"','DD/MM/YY')- to_date('"+Price_DelvEndDt+"','DD/MM/YY') "
						+ "from dual";
				rset9=stmt9.executeQuery(query);
				if(rset9.next())
				{
					count=rset9.getInt(1);
					count2=rset9.getInt(2);
				}
				System.out.println(query);
				System.out.println(count+"==="+count2);
				if(count <= 0 && count2 <= 0)
				{
					Price_GasDate=Price_DelvStartDt;
					FloatingPricingCalculation(Price_MappId, Price_ContType, Price_DelvStartDt, Price_DelvEndDt, Price_GasDate);
				}
				else
				{
					if(count >= 0 && count2 <=0)
					{
						final_start_dt=Price_DelvStartDt;
						final_end_dt=Price_GasDate;
					}
					else if(count >= 0 && count2 >=0)
					{
						final_start_dt=Price_DelvStartDt;
						final_end_dt=Price_DelvEndDt;
					}
					
					int index=0;
					queryString="SELECT DISTINCT(TO_CHAR(LAST_DAY(TO_DATE(TD.END_DATE + 1 - ROWNUM)),'DD/MM/YYYY')) MONTH_DATE "
							+ "FROM ALL_OBJECTS,(SELECT TO_DATE('"+final_start_dt+"','DD/MM/YYYY') START_DATE,TO_DATE('"+final_end_dt+"','DD/MM/YYYY') END_DATE FROM DUAL) TD "
							+ "WHERE TRUNC(TD.END_DATE + 1 - ROWNUM, 'MM') >= TRUNC(TD.START_DATE,'MM') "
							+ "ORDER BY TO_DATE(MONTH_DATE,'DD/MM/YYYY')";
					System.out.println(queryString);
					rset8=stmt8.executeQuery(queryString);
					while(rset8.next())
					{
						Price_GasDate=rset8.getString(1)==null?Price_GasDate:rset8.getString(1);
						
						queryString1 = "SELECT TO_DATE('"+Price_GasDate+"','DD/MM/YYYY')-TO_DATE('"+Price_DelvEndDt+"','DD/MM/YYYY') FROM DUAL";
						rset7=stmt7.executeQuery(queryString1);
						if(rset7.next())
						{
							if(rset7.getInt(1) > 0)
							{
								Price_GasDate=Price_DelvEndDt;
							}
						}
						//System.out.println(Price_GasDate);
					
						FloatingPricingCalculation(Price_MappId, Price_ContType, Price_DelvStartDt, Price_DelvEndDt, Price_GasDate);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public double FloatingPricingCalculation(String MappId, String ContType, String DelvStartDt, String DelvEndDt, String GasDate)
	{
		double price=0;
		try
		{
			////////////////////For Calculation of MIN, OPAL/MULTI_LEG, Normal//////////////////////////////////////
			//////////////////////To Find Out Cont Dt & Report Dty////////////////////
			int count=0;	int count2=0;	
			String query="select to_date('"+GasDate+"','DD/MM/YY')- to_date('"+DelvStartDt+"','DD/MM/YY'),"
					+ "to_date('"+DelvEndDt+"','DD/MM/YY')- to_date('"+GasDate+"','DD/MM/YY') "
					+ "from dual";
			//	System.out.println("Date Difference: dual: "+query);
			rset9=stmt9.executeQuery(query);
			if(rset9.next())
			{
				count=rset9.getInt(1);
				count2=rset9.getInt(2);
			}
			//System.out.println(count+" dual: "+count2);
			/*
			if(count>=0 && count2>=0) {
				GasDate=GasDate;
			}
			else if(count>=0 && count2<0) {
				GasDate=DelvEndDt; //Contract Expired = SETTLED
			}
			else if(count<0 && count2>=0) {
				GasDate=DelvStartDt; //Contract Yet to start = FWD
			}
			else if(count<0 && count2<0) {
				GasDate=DelvStartDt; //Contract Yet to start = FWD
			}
			*/
			VPriceDate.add(GasDate);//FOR SN/LOA DEAL FORMS
			
			//////////////////////^^^^^To Find Out Cont Dt & Report Dty////////////////////
			String CurveName=""; String PriceFormulaRemark=""; 
			String UserAvgPriceStartDt=""; String UserAvgPriceEndDt="";
			String PriceRange=""; String DealPriceHead=""; 
			double Slope=0; double Const=0;
			
			String price_type="";
			
			queryString ="SELECT PRICE_TYPE, CURVE_NM, RATE, SEQ_NO, PHYS_CURVE_NM, REMARKS, SLOPE, CONST, "
					+ "PRICE_RANGE, TO_CHAR(PRICE_START_DT,'DD/MM/YYYY'), TO_CHAR(PRICE_END_DT,'DD/MM/YYYY')  FROM FMS9_MRCR_CONT_PRICE_DTL " //SB20210311
					//SB20210528		+ "WHERE MAPPING_ID='"+Mapp_Id+"' AND CONTRACT_TYPE='"+VContType.elementAt(i)+"' AND FLAG='Y' ";
			+ "WHERE MAPPING_ID LIKE '"+MappId+"' AND CONTRACT_TYPE='"+ContType+"' AND FLAG='Y' "
			+ "AND FROM_DT<=TO_DATE('"+GasDate+"','DD/MM/YYYY') AND TO_DT>=TO_DATE('"+GasDate+"','DD/MM/YYYY')"; //SB20220128
			//sb20200128: does not work this line		+ "AND TO_DATE(FROM_DT,'DD/MM/YYYY') <= TO_DATE('"+GasDate+"','DD/MM/YYYY') AND TO_DATE(TO_DT,'DD/MM/YYYY') >= TO_DATE('"+GasDate+"','DD/MM/YYYY') "; //SB20210528
		//	System.out.println("FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
			rset9 = stmt9.executeQuery(queryString);
			if(rset9.next())
			{
				price_type=rset9.getString(1)==null?"":rset9.getString(1);
				CurveName=rset9.getString(2)==null?"":rset9.getString(2);
				PricePHYSName=rset9.getString(5)==null?"":rset9.getString(5);  //SB20210310	 //SB20210311
				PPACPriceFlag=rset9.getString(3)==null?"0":rset9.getString(3);//SB20210913
				///////////////////SB20210909////////////////////////////////
				PriceFormulaRemark=rset9.getString(6)==null?"":rset9.getString(6); 
				DealPriceHead=PriceFormulaRemark; //SB20211230
				if(PriceFormulaRemark.equals(""))
					PriceFormulaRemark="("+rset9.getString(2)+"*"+rset9.getString(7)+"+"+rset9.getString(8)+")";				
				String tempPriceFormulaRemark[]=PriceFormulaRemark.split("@");//MULTI_LEG //OPAL@0@3  : TypeOfDeal@PreviousMonth(Zero)@UptoPreviousMonth(3) 			
				if(tempPriceFormulaRemark[0].equals("MULTI_LEG")) //It was OPAL
				{
					PriceFormulaRemark=PriceFormulaRemark.replace("@", " Leg -M");	
				}
				else if(tempPriceFormulaRemark[0].equals("MIN"))
				{
					PriceFormulaRemark=PriceFormulaRemark.replace("@", ", ");	
					PriceFormulaRemark="Min. of "+PriceFormulaRemark.substring(4);
				}
				else
				{
					DealPriceHead=rset9.getString(2);
				}
				Slope=rset9.getDouble(7);
				Const=rset9.getDouble(8);
				///////////////////^^^^SB20210909////////////////////////////////	
				PriceRange=rset9.getString(9)==null?"":rset9.getString(9); 
				UserAvgPriceStartDt=rset9.getString(10)==null?"":rset9.getString(10); 
				UserAvgPriceEndDt=rset9.getString(11)==null?"":rset9.getString(11); 
			}
			
			if(price_type.equals("M"))
			{
				VPriceFormulaRemark.add("Float : "+PriceFormulaRemark);//FOR SN/LOA DEAL FORMS
			}
			else
			{
				VPriceFormulaRemark.add("Fixed");
			}
			
			Vector VMutiCurveNm_Temp = new Vector();
			Vector VFin_ContMth_Temp = new Vector();
			Vector VFin_SettleStDt_Temp = new Vector();
			Vector VFin_SettleEndDt_Temp = new Vector();
			Vector VFin_SettlePrice_Temp = new Vector();
			Vector VFin_PriceRange_Temp = new Vector();
			Vector VSett_Fwd_Temp = new Vector(); //HARSH20220124
			
			//IndexSettlePrice=priceSettle; 
			//System.out.println(CurveName+" :DealPriceHead: "+DealPriceHead);
			FinPriceFormula=DealPriceHead;
			String tempPriceFormula[]=DealPriceHead.split("@");//Now it MULTI_LEG //OPAL@0@3  : TypeOfDeal@PreviousMonth(Zero)@UptoPreviousMonth(3) 
			DealPriceHead=tempPriceFormula[0];
			
			int NoMth=0;int StartingPrevMth=1; int NoMth_FinCurve=3;
			if(!FinPriceFormula.equals(""))
			{  //System.out.println("PriceFormula: "+PriceFormula);
				if(DealPriceHead.equals("MULTI_LEG"))
				{
					if(tempPriceFormula.length>2)
					{				
						NoMth=Integer.parseInt(tempPriceFormula[2]);
						StartingPrevMth=Integer.parseInt(tempPriceFormula[1]);
						NoMth_FinCurve=NoMth;
					//	NoMth=1;
					}
				}
				else if(DealPriceHead.equals("MIN"))
				{
					queryString ="SELECT COUNT(FROM_DT) FROM FMS9_MRCR_CONT_FIN_PRICE_DTL "
							+ "WHERE  MAPPING_ID LIKE '"+MappId+"' AND TO_DATE(TO_DATE('"+GasDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') "
							+ "AND TO_DATE(TO_DATE('"+GasDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY') AND FLAG='Y' "; //SB20210528
						//	System.out.println("FMS9_MRCR_CONT_PRICE_DTL: "+queryString);
							rset9 = stmt9.executeQuery(queryString);			
							if(rset9.next())
							{
								NoMth=rset9.getInt(1);  //LNG_ICE_JKM
							}
							StartingPrevMth=0;
							NoMth_FinCurve=1; 
				}
				else 
				{
					NoMth=1;
					StartingPrevMth=0;
					NoMth_FinCurve=NoMth;
				}
			}
			else
			{
				NoMth=1;
				StartingPrevMth=0;
				NoMth_FinCurve=NoMth;
			}
			
			//VMutiCurveNm_Temp.clear();
			String IndexSettlePrice=PriceRange; 
			String tempGen_Dt[]=GasDate.split("/");
			String GenMth=tempGen_Dt[1]; String GenYr=tempGen_Dt[2]; String GenMthYr=GenMth+"/"+GenYr;
			String CurrContMthYr="01/"+GenMthYr; 
			String PrevContMthYr="01/"+GenMthYr;
			String ContMthYr2="01/"+GenMthYr;
			
			PriceFlagR_U=""; //TO DISTINGUISH REALIZED OR UNREALIZED
			
			for(int j=0; j<NoMth; j++)
			{
				int BackMth=StartingPrevMth+j;	
				if(DealPriceHead.equals("MIN"))
				{
					CurveName=tempPriceFormula[j+1];
					BackMth=StartingPrevMth;
					VMutiCurveNm_Temp.add(CurveName);
					///////////////SB20210825: To get the Avg Calculation type//////////////////////////////////////////
					queryString ="SELECT PRICE_RANGE, SLOPE, CONST FROM FMS9_MRCR_CONT_FIN_PRICE_DTL "
							+ "WHERE  MAPPING_ID LIKE '"+MappId+"' AND TO_DATE(TO_DATE('"+GasDate+"','DD/MM/YYYY'),'DD-MON-YY') >=TO_DATE(FROM_DT,'DD-MON-YY') "
							+ "AND TO_DATE(TO_DATE('"+GasDate+"','DD/MM/YYYY'),'DD-MON-YY') <=TO_DATE(TO_DT,'DD-MON-YY') AND FLAG='Y' AND CURVE_NM='"+CurveName+"'"; //SB20210528
					//System.out.println("FMS9_MRCR_CONT_FIN_PRICE_DTL: "+queryString);
					rset9 = stmt9.executeQuery(queryString);			
					if(rset9.next())
					{
						//NoMth=rset2.getInt(1);  //LNG_ICE_JKM
						IndexSettlePrice=rset9.getString(1)==null?"A":rset9.getString(1);
						Slope=rset9.getDouble(2);
						Const=rset9.getDouble(3);
					}
					else
					{
						IndexSettlePrice="";
						Slope=1;
						Const=0;
					}			
					///////////////^^^To get the Avg Calculation type//////////////////////////////////////////
				}
				else
				{
					VMutiCurveNm_Temp.add(CurveName);
				}
				
				//////////////////Settle Price //////////////////////////////////
				
				String PrevContMthYrTemp="";  String PriceBegMthDt=""; String PriceEndMthDt="";
				String ContMthSettleDt=""; String Fin_RU_Flag="";//String UserAvgPriceStartDt=""; String UserAvgPriceEndDt="";
				double avgCommodityValue=0;
				/////////////////////SB20201116: Prev Settlement Date: to find the start date for Contract Month////////////////////////////////////////
				String PriceIndex_SettleMth="";	String PriceIndex_SettleMONYYYY="";		
				String PrevMthSettleEndDtPlusOne=""; 
				int NoOfDays=1; String IndexSettlePriceAvgStr=""; 
				if(IndexSettlePrice.length()>1)
				{
					NoOfDays=Integer.parseInt(IndexSettlePrice.substring(1,IndexSettlePrice.length())); //System.out.println("NoOfDays Avg. Days: "+NoOfDays);
				}
				
				queryString = "SELECT TO_CHAR(TO_DATE('"+ContMthYr2+"','DD/MM/YYYY')-"+BackMth+",'DD/MM/YYYY') FROM DUAL";
				//System.out.println(j+"  :Get DAY Difference: "+queryString);
				rset9 = stmt9.executeQuery(queryString);
				if(rset9.next())
				{
					PrevContMthYrTemp = rset9.getString(1)==null?"":rset9.getString(1); //SB20201128;
					String tempPrevContMthYr[]=PrevContMthYrTemp.split("/");
					PrevContMthYrTemp="01/"+tempPrevContMthYr[1]+"/"+tempPrevContMthYr[2];
					//System.out.println(j+"  :Get Month : "+PrevContMthYrTemp);
				}				
				ContMthYr2=PrevContMthYrTemp;
				
				//HARSH20220317 queryString = "SELECT TO_CHAR(TO_DATE(SETTLE_DT,'DD-MON-YY'),'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
				queryString = "SELECT TO_CHAR(SETTLE_DT,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
						//SB20210708		+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr2+"','DD/MM/YYYY') AND SETTLE_TYPE='"+HolidayType+"' ";
						//HARSH20220317 + "WHERE CONT_MM_YYYY=TO_DATE(TO_DATE('"+ContMthYr2+"','DD/MM/YYYY'),'DD-MON-YY') AND CURVE_NM='"+CurveName+"' ";
						+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr2.trim()+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"' ";
				System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
				rset9 = stmt9.executeQuery(queryString);
				if(rset9.next()) 
				{
					ContMthSettleDt = rset9.getString(1)==null?"":rset9.getString(1);	
					//VFin_SettleEndDt_Temp.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else //SB20210425
				{
					ContMthSettleDt = ContMthYr2; //SB20210425
					//	VFin_SettleEndDt_Temp.add(""); ///sb
				}
				
				String PrevContMthYrTemp2="";
				queryString = "SELECT TO_CHAR(TO_DATE('"+PrevContMthYrTemp+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
				//		System.out.println(j+"  :Get DAY Difference: "+queryString);
				rset9 = stmt9.executeQuery(queryString);
				if(rset9.next())
				{
					PrevContMthYrTemp2 = rset9.getString(1)==null?"":rset9.getString(1); //SB20201128;
					//System.out.println(j+"  :Get Month : "+PrevContMthYrTemp2);
				}
				String tempPrevContMthYr2[]=PrevContMthYrTemp2.split("/");
				PrevContMthYrTemp2="01/"+tempPrevContMthYr2[1]+"/"+tempPrevContMthYr2[2];
				
				////////////////////////////////////////////////////////////////////
				
				if(IndexSettlePrice.equals("A") || IndexSettlePrice.equals(""))
				{
					queryString = "SELECT TO_CHAR(TO_DATE(SETTLE_END_DT,'DD/MM/YY')+1,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
							+ "WHERE CONT_MM_YYYY=TO_DATE('"+PrevContMthYrTemp2+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"' ";
					//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
					rset9 = stmt9.executeQuery(queryString);
					if(rset9.next()) {
						PrevMthSettleEndDtPlusOne = rset9.getString(1)==null?"":rset9.getString(1); //SB20201128;	
					}
					else //SB20210425
					{
						PrevMthSettleEndDtPlusOne = ContMthYr2; //SB20210425
					}
					if(!UserAvgPriceStartDt.equals(""))  //SB20201128
					{
						PrevMthSettleEndDtPlusOne=UserAvgPriceStartDt; //SB20201128
					}
					if(!UserAvgPriceEndDt.equals(""))  //SB20201128
					{
						ContMthSettleDt=UserAvgPriceEndDt; //SB20210511
					}
					
				}
				///else if(IndexSettlePrice.equals("A7"))
				else if(IndexSettlePrice.equals("A"+NoOfDays))
				{
					int NoOfDays2=1;
					NoOfDays2=NoOfDays-1;
					queryString = "SELECT TO_CHAR(TO_DATE('"+ContMthSettleDt+"','DD/MM/YYYY')-"+NoOfDays2+",'DD/MM/YYYY') FROM DUAL";
					//	System.out.println("Get DAY Name: "+queryString);
					rset9 = stmt9.executeQuery(queryString);
					if(rset9.next())
					{
						PrevMthSettleEndDtPlusOne = rset9.getString(1)==null?"":rset9.getString(1); //SB20201128;
					}
				}
				else if(IndexSettlePrice.equals("F")) //SB20201027: For BRENT Settlement  Date
				{
					String ContMthYr="01/"+GenMthYr;
					queryString = "SELECT TO_CHAR(SETTLE_DT,'DD/MM/YYYY'), TO_CHAR(SETTLE_END_DT,'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
							+ "WHERE CONT_MM_YYYY=TO_DATE('"+ContMthYr+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"' ";
					//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
					rset9 = stmt9.executeQuery(queryString);
					if(rset9.next()) {
						PrevMthSettleEndDtPlusOne = rset9.getString(1)==null?"":rset9.getString(1);					
					}
					else
					{
						PrevMthSettleEndDtPlusOne=ContMthYr;
					}
					PriceBegMthDt = PrevMthSettleEndDtPlusOne;
				}
				
				if(CurveName.equals("PPAC"))
				{
					queryString = "SELECT TO_CHAR(TO_DATE(SETTLE_START_DT,'DD-MON-YY'),'DD/MM/YYYY') FROM FMS9_SETTLE_CALND_DTL "
							+ "WHERE CONT_MM_YYYY=TO_DATE('"+CurrContMthYr+"','DD/MM/YYYY') AND CURVE_NM='"+CurveName+"' ";
					//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
					rset9 = stmt9.executeQuery(queryString);
					if(rset9.next()) 
					{
						PrevMthSettleEndDtPlusOne = rset9.getString(1)==null?"":rset9.getString(1); //SB20201128;	
					}
					else //SB20210425
					{
						PrevMthSettleEndDtPlusOne = ContMthYr2; //SB20210425
					}
				}
				
				//////////////////////To Find Out SAT/SUN/Holiday////////////////////
				count=0;		
				query="select to_date('"+ContMthSettleDt+"','DD/MM/YY')- to_date('"+PrevMthSettleEndDtPlusOne+"','DD/MM/YY')+1 from dual";
				//	System.out.println("Date Difference: dual: "+query);
				rset9=stmt9.executeQuery(query);
				if(rset9.next())
				{
					count=rset9.getInt(1);
				}
				//PriceBegMthDt =ContMthYr;
				int StratDayCount=0; int StratDayCount2=0; //SB20201017: to check Starting SAT/SUN/HOLIDAY
				String SettleStartDate="";
				PriceBegMthDt =PrevMthSettleEndDtPlusOne;
				
				if(!CurveName.equals("PPAC"))
				{	
					for(int m=0;m<count;m++)
					{
						String query1="select to_char(to_date('"+PrevMthSettleEndDtPlusOne+"','dd/mm/yy')+ "+m+",'dd/mm/yyyy') from dual";
						///	System.out.println(">>>>>>>dual: "+query1);
						rset9=stmt9.executeQuery(query1);
						if(rset9.next())
						{
							SettleStartDate=rset9.getString(1)==null?"":rset9.getString(1); //SB20201128
						}		
						///////////////////////SB20201017: to check Starting SAT/SUN/HOLIDAY//////////////////////////////					
						if(StratDayCount==StratDayCount2)
						{
							queryString ="SELECT to_char(HOLIDAY_DT,'dd/mm/yyyy') FROM FMS9_CURVE_HOLIDAY_DTL "
									//SB20210708			+ " WHERE HOLIDAY_DT =to_date('"+date_arr+"','dd/mm/yyyy') AND FLAG='Y' AND HOLIDAY_TYPE='"+HolidayType+"' ORDER BY HOLIDAY_DT";
									+ " WHERE HOLIDAY_DT =to_date('"+SettleStartDate+"','dd/mm/yyyy') AND FLAG='Y' AND CURVE_NM='"+CurveName+"' ORDER BY HOLIDAY_DT";
							rset9 = stmt9.executeQuery(queryString);
							//	System.out.println(">>>>>>>PRICE PUULING: "+queryString);
							if(rset9.next())
							{
								StratDayCount++; //System.out.println("Get HOLI DAY Name: ");		
							}
							else
							{
								String WeeklyOff="";
								queryString = "SELECT TO_CHAR(TO_DATE('"+SettleStartDate+"','DD/MM/YYYY'),'DAY') FROM DUAL";
								//	System.out.println("Get DAY Name: "+queryString);
								rset9 = stmt9.executeQuery(queryString);
								if(rset9.next())
								{
									WeeklyOff = rset9.getString(1)==null?"":rset9.getString(1); //SB20201128;
								}
								if(WeeklyOff.trim().equals("SATURDAY") || WeeklyOff.trim().equals("SUNDAY")) 
								{
									StratDayCount++;
								}
								else
								{							
								//	Veff_dt.add(SettleStartDate);
									PriceBegMthDt=SettleStartDate; //System.out.println("Get DAY Name: "+WeeklyOff);							
								}
							}
						}
						//else
							//Veff_dt.add(SettleStartDate); //Ori Line
						StratDayCount2++; //System.out.println(StratDayCount+" : "+StratDayCount2);
						//////////////////////////////////////////////////////////////////////////////////////////
						//SB20201017	Veff_dt.add(date_arr);
					}
				} //EoF Logic
				
				//				VFin_SettleStDt_Temp.add(PriceBegMthDt);	
				//System.out.println("Get PriceBegMthDt: "+PriceBegMthDt);
				/////////////////^^^^^To Find Out SAT/SUN/Holiday/////////////////////////////////////////////////////
				
				queryString = "SELECT TO_DATE('"+to_date+"','DD/MM/YYYY')-TO_DATE('"+ContMthSettleDt+"','DD/MM/YYYY')+1 FROM DUAL" ;
				//System.out.println(" DUAL: LAST: "+queryString);
				rset9 = stmt9.executeQuery(queryString);
				if(rset9.next()) {
					NoOfDays = rset9.getInt(1);
				}
				
				if(NoOfDays>0)
				{
					Fin_RU_Flag="R";
					PriceColor="#0000ff";//HARSH20220104
				}
				else
				{
					Fin_RU_Flag="U";
					//PriceColor="#6600cc";//HARSH20220104
					PriceColor="e69500";//HARSH20220104
				}
				
				PriceFlagR_U=Fin_RU_Flag; //HARSH20220104 TO DISTINGUISH REALIZED OR UNREALIZED
				
				if(Fin_RU_Flag.equals("R")) //Incase of Price Realised/Settled
				{
					/////////////////////SB20201116: Prev Settlement Date: to find the start date for Contract Month////////////////////////////////////////
					queryString = "SELECT TO_CHAR(MAX(TO_DATE(CONT_MM_YYYY,'DD/MM/YYYY')),'MM/YYYY'), TO_CHAR(MAX(TO_DATE(CONT_MM_YYYY,'DD/MM/YYYY')),'MON-YYYY') FROM FMS9_SETTLE_CALND_DTL "
							+ "WHERE SETTLE_DT BETWEEN TO_DATE('"+PrevMthSettleEndDtPlusOne+"','DD/MM/YYYY') AND TO_DATE('"+ContMthSettleDt+"','DD/MM/YYYY') "
						//Remove this checking from every where	+ "AND SETTLE_TYPE='"+HolidayType+"' ";
							+ "AND CURVE_NM='"+CurveName+"' ";
					//	System.out.println(" SETTLE: FMS9_SETTLE_CALND_DTL: "+queryString);
					rset9 = stmt9.executeQuery(queryString);
					if(rset9.next()) {
						PriceIndex_SettleMth = rset9.getString(1)==null?"":rset9.getString(1);//System.out.println(" INSIDE: PriceIndex_SettleMth: "+PriceIndex_SettleMth); //SB20201128;
						PriceIndex_SettleMONYYYY = rset9.getString(2)==null?"":rset9.getString(2);
					}
					else
					{
						PriceIndex_SettleMth=ContMthSettleDt;  ////This line to be corrected SB20210911
						PriceIndex_SettleMONYYYY=ContMthSettleDt;
					}
					/////////////SB20210702/////////////////	
					PriceIndex_SettleMth="01/"+PriceIndex_SettleMth; 
					///////////////////////////SB20201006: New Logic To Calculate Pricinging Start-End Date and No. of Days in between ///////////////////////////////		
					if(!CurveName.equals("PPAC"))  
					{
						queryString = "select AVG(SETTLE_PRICE) from FMS9_CURVE2_PRICE_DTL "
							+ " where SETTLE_PRICE>0 AND CURVE_DD_MM_YR BETWEEN TO_DATE('"+PrevMthSettleEndDtPlusOne+"','dd/mm/yyyy') AND TO_DATE('"+ContMthSettleDt+"','dd/mm/yyyy') "
							//SB20210409		+ "AND CURVE_TYPE='Spot' AND CURVE_NM='"+CurveName+"' ";
							+ "AND CURVE_TYPE='Spot' AND PHYS_FIN='"+CurveName+"' "; //SB20210409
						//System.out.println("Eff. AVG: FM S9_CURVE_PRICE_DTL: "+queryString);
						rset9 = stmt9.executeQuery(queryString);
						if(rset9.next())
						{
							avgCommodityValue = rset9.getDouble(1);
						}
						else
						{
							avgCommodityValue = 0;
						}
					}
					/////////////SB20210506: Get PPAC Price from FORWARD2///////////////////////
					else if(CurveName.equals("PPAC"))  //SB20210814
					{
						PriceIndex_SettleMth=CurrContMthYr;
						queryString ="SELECT SETTLE_PRICE, TO_CHAR(CURVE_DD_MM_YR,'MON-YYYY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
								" WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=(SELECT TO_DATE(MAX(ENT_DT),'DD-MON-YY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
								" WHERE CURVE_DD_MM_YR=TO_DATE('"+PriceIndex_SettleMth+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT<=TO_DATE('"+to_date+"','DD/MM/YYYY') "
								+ "AND CURVE_NM='PPAC') "
								+ "AND CURVE_NM='PPAC' ";
						//	System.out.println(" :PPAC PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
						rset9 = stmt9.executeQuery(queryString);			
						if(rset9.next())
						{
							avgCommodityValue=rset9.getDouble(1);
							PriceIndex_SettleMONYYYY = rset9.getString(2)==null?"*":rset9.getString(2);
						}
						else
						{
							avgCommodityValue=0;
						}
					}
					//////////////^^SB20210506: Get PPAC Price from FORWARD2////////////////////
					//	System.out.println("AVG: avgCommodityValue: "+avgCommodityValue);
					avgCommodityValue=(avgCommodityValue*Slope)+Const;//System.out.println("Eff. AVG: avgCommodityValue: "+avgCommodityValue);
					
					/////////////////////////////////////////////////////////////////
					if(IndexSettlePrice.equals("A")) {
						PriceRange="Avg.";
					}
					else if(IndexSettlePrice.startsWith("A")) {
						PriceRange="Avg."+PriceRange;
					}
					else if(IndexSettlePrice.equals("F")) {
						PriceRange="Final";
					}
					else {
						PriceRange="Avg.";
					}
					
					//PriceRange=PriceRange+" Settle Dt.";
					if(!DealPriceHead.equals("MIN") && !DealPriceHead.equals("MULTI_LEG"))
					{
						if(UserAvgPriceStartDt.equals(""))
						{
							VPriceRange.add(PriceRange+":"+" "+nf2.format(avgCommodityValue)); //SB20211228
							VPriceRange_A.add(nf2.format(avgCommodityValue)); //FOR SN/LOA DEAL FORMS
						}
						else 
						{
							VPriceRange.add(PriceRange+":"+" "+nf2.format(avgCommodityValue)+" of "+UserAvgPriceStartDt+":"+UserAvgPriceEndDt); //SB20211228
							VPriceRange_A.add(nf2.format(avgCommodityValue)); //FOR SN/LOA DEAL FORMS
						}
						VPriceStartDt.add("Start Dt:"+PriceBegMthDt); //SB20211228
						VPriceEndDt.add("Settle Dt:"+ContMthSettleDt); //SB20211228
						VFinContMth.add(DealPriceHead+": "+PriceIndex_SettleMONYYYY);
						VSETT_FWD.add("(Settle Price)");
					}
					else if(DealPriceHead.equals("MIN") || DealPriceHead.equals("MULTI_LEG"))
					{		
						VFin_SettlePrice_Temp.add(avgCommodityValue);
						VFin_PriceRange_Temp.add(PriceRange+":"+" "+nf2.format(avgCommodityValue));
						VFin_SettleStDt_Temp.add("Start Dt:"+PriceBegMthDt);
						VFin_SettleEndDt_Temp.add("Settle Dt:"+ContMthSettleDt);
						VFin_ContMth_Temp.add(DealPriceHead+": "+PriceIndex_SettleMONYYYY);
						VSett_Fwd_Temp.add("(Settle Price)");
					}
					
					price = avgCommodityValue; //HARSH20220103
				}
				else //Incase of Price UnRealised/UnSettled i.e. Forward
				{
					String PlattEntDt="";
					queryString = "SELECT TO_CHAR(MAX(ENT_DT), 'dd/mm/yyyy')  from FMS9_FORWARD2_PRICE_DTL "
							+ " where ENT_DT<= to_date('"+to_date+"','dd/mm/yyyy') AND CURVE_TYPE='Forward' AND FLAG='Y' ";
					//	System.out.println("B. FMS9_FORWARD2_PRICE_DTL: "+queryString);
					rset9 = stmt9.executeQuery(queryString);
					if(rset9.next())
					{
						PlattEntDt = rset9.getString(1)==null?"":rset9.getString(1);
					}
					queryString ="SELECT SETTLE_PRICE, TO_CHAR(CURVE_DD_MM_YR,'MON-YYYY') FROM FMS9_FORWARD2_PRICE_DTL "+ 
							" WHERE CURVE_DD_MM_YR=TO_DATE('"+CurrContMthYr+"','DD/MM/YYYY') AND CURVE_TYPE='Forward' AND FLAG='Y' AND ENT_DT=TO_DATE('"+PlattEntDt+"','DD/MM/YYYY') "
							+ "AND CURVE_NM='"+CurveName+"' ";	
					//System.out.println(" :Incase of Forward Price of Curve Nm not in latest date "+to_date+":FORWARD2 PRICE: FMS9_FORWARD2_PRICE_DTL: "+queryString);
					rset9 = stmt9.executeQuery(queryString);			
					if(rset9.next())
					{
						avgCommodityValue = rset9.getDouble(1);
						PriceIndex_SettleMONYYYY = rset9.getString(2)==null?"*":rset9.getString(2);
					}
					else /////////////////////^^^^^SB20210703: Incase of Forward Price of Curve Nm not in latest date///////////////////////
					{
						avgCommodityValue = 0;
						//PriceIndex_SettleMONYYYY=GenMthYr;
					}
					avgCommodityValue=(avgCommodityValue*Slope)+Const;//System.out.println("Eff. AVG: avgCommodityValue: "+avgCommodityValue);
					PriceRange="";
					PriceRange=PriceRange+"Fwd Price Dt.";
					
					if(!DealPriceHead.equals("MIN") && !DealPriceHead.equals("MULTI_LEG"))
					{
						VPriceRange.add(nf2.format(avgCommodityValue)); //SB20211228
						VPriceRange_A.add(nf2.format(avgCommodityValue)); //FOR SN/LOA DEAL FORMS
						VPriceStartDt.add("Start Dt:"+PriceBegMthDt); //SB20211228
						VPriceEndDt.add(PriceRange+":"+PlattEntDt); //SB20211228
						VFinContMth.add(DealPriceHead+": "+PriceIndex_SettleMONYYYY);
						VSETT_FWD.add("(Forward Price)");
					}
					else if(DealPriceHead.equals("MIN") || DealPriceHead.equals("MULTI_LEG"))
					{		
						VFin_SettlePrice_Temp.add(nf2.format(avgCommodityValue));
						VFin_PriceRange_Temp.add(""+nf2.format(avgCommodityValue));
						VFin_SettleStDt_Temp.add("Start Dt:"+PriceBegMthDt);
						VFin_SettleEndDt_Temp.add(PriceRange+":"+PlattEntDt);
						VFin_ContMth_Temp.add(DealPriceHead+": "+PriceIndex_SettleMONYYYY);
						VSett_Fwd_Temp.add("(Forward Price)");
						//System.out.println(nf2.format(avgCommodityValue));
					}
					
					price = avgCommodityValue; //HARSH20220103
				}
			}
			
			if(DealPriceHead.equals("MIN"))
			{ 
			 	/*System.out.println(VFin_SettleStDt_Temp+" **************** VFin_SettlePrice_Temp: "+VFin_SettleStDt_Temp.size());
				System.out.println(VFin_SettleEndDt_Temp+" **************** VFin_SettleEndDt_Temp: "+VFin_SettleEndDt_Temp.size());
				System.out.println(VFin_PriceRange_Temp+" **************** VFin_PriceRange_Temp: "+VFin_PriceRange_Temp.size());
				System.out.println(VMutiCurveNm_Temp+" **************** VMutiCurveNm_Temp: "+VMutiCurveNm_Temp.size());*/
				///////////////////Find out the Min FinCurve Pricing(Avg Pricing)////////////////////
				double Min_Eff_avgCommodityValue=500; 
				for(int m=0; m<VFin_SettlePrice_Temp.size(); m++)
				{
					if(Double.parseDouble(""+VFin_SettlePrice_Temp.elementAt(m))>0)
					{
						if(Double.parseDouble(""+VFin_SettlePrice_Temp.elementAt(m))<Min_Eff_avgCommodityValue)
						{
							Min_Eff_avgCommodityValue=Double.parseDouble(""+VFin_SettlePrice_Temp.elementAt(m)); //SB20200929
						}
					}
				}
				/////////////////////////////PICK UP MIN PRICE////////////////////////////////////////////
				for(int i=0; i<VFin_SettlePrice_Temp.size(); i++)
				{
					if(Double.parseDouble(""+VFin_SettlePrice_Temp.elementAt(i))==Min_Eff_avgCommodityValue)
					{
						VPriceStartDt.add(""+VFin_SettleStDt_Temp.elementAt(i)); //SB20211228
						VPriceEndDt.add(""+VFin_SettleEndDt_Temp.elementAt(i)); //SB20211228
						VPriceRange.add(nf2.format(Min_Eff_avgCommodityValue));
						VPriceRange_A.add(nf2.format(Min_Eff_avgCommodityValue)); //FOR SN/LOA DEAL FORMS
						VFinContMth.add(VMutiCurveNm_Temp.elementAt(i)+":"+VFin_SettleStDt_Temp.elementAt(i));
						VSETT_FWD.add(""+VSett_Fwd_Temp.elementAt(i));
						
						PriceColor="";//HARSH20220104
						PriceFlagR_U="";//HARSH20220104
						price = Min_Eff_avgCommodityValue; //HARSH20220103
						break;
					}
				}
			 }
			 ////////////////////MULTI-LEG Avg Calculation///////////////////////////////
			 else if(DealPriceHead.equals("MULTI_LEG"))
			 { 
				 /*System.out.println(VFin_SettleStDt_Temp+" **************** VFin_SettlePrice_Temp: "+VFin_SettleStDt_Temp.size());
					System.out.println(VFin_SettleEndDt_Temp+" **************** VFin_SettleEndDt_Temp: "+VFin_SettleEndDt_Temp.size());
					System.out.println(VFin_PriceRange_Temp+" **************** VFin_PriceRange_Temp: "+VFin_PriceRange_Temp.size());
					System.out.println(VMutiCurveNm_Temp+" **************** VMutiCurveNm_Temp: "+VMutiCurveNm_Temp.size());*/
				 double Min_Eff_avgCommodityValue=0;
				 for(int i=0; i<VFin_SettlePrice_Temp.size(); i++)
				 {
					 Min_Eff_avgCommodityValue+=Double.parseDouble(""+VFin_SettlePrice_Temp.elementAt(i));
				 }
				 Min_Eff_avgCommodityValue=Min_Eff_avgCommodityValue/VFin_SettlePrice_Temp.size();
				 VPriceStartDt.add(VFin_SettleStDt_Temp.elementAt(0));
				 VPriceEndDt.add(""+VFin_SettleEndDt_Temp.elementAt(0));
				 VPriceRange.add(""+nf2.format(Min_Eff_avgCommodityValue));
				 VPriceRange_A.add(nf2.format(Min_Eff_avgCommodityValue)); //FOR SN/LOA DEAL FORMS
				 VFinContMth.add(VMutiCurveNm_Temp.elementAt(0)+":"+VFin_SettleStDt_Temp.elementAt(0));
				 VSETT_FWD.add(""+VSett_Fwd_Temp.elementAt(0));
				 
				 price = Min_Eff_avgCommodityValue; //HARSH20220103
				 PriceColor="";//HARSH20220104
				 PriceFlagR_U="";//HARSH20220104
			 }
			
			VCOLOR.add(PriceColor);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return price;
	}
	
	String PricePHYSName="";
	String PPACPriceFlag="";
	String FinPriceFormula="";
	String PriceFlagR_U=""; //HARSH20220104
	String PriceColor=""; //HARSH20220104
	
	//FOLLOWING SETTER METHODS ARE USED FOR SN/LOA DEAL FORMS
	String Price_MappId="";
	public void setPrice_MappId(String Price_MappId) {
		this.Price_MappId = Price_MappId;
	}
	String Price_ContType="";
	public void setPrice_ContType(String Price_ContType) {
		this.Price_ContType = Price_ContType;
	}
	String Price_DelvStartDt="";
	public void setPrice_DelvStartDt(String Price_DelvStartDt) {
		this.Price_DelvStartDt = Price_DelvStartDt;
	}
	String Price_DelvEndDt="";
	public void setPrice_DelvEndDt(String Price_DelvEndDt) {
		this.Price_DelvEndDt = Price_DelvEndDt;
	}
	String Price_GasDate="";
	public void setPrice_GasDate(String Price_GasDate) {
		this.Price_GasDate = Price_GasDate;
	}
	////////////////////////////////////////////////////////////
	
	Vector VPriceRange = new Vector(); //SB20211228
	public Vector getVPriceRange() {return VPriceRange;} //SB20211228
	Vector VPriceStartDt = new Vector(); //SB20211228
	public Vector getVPriceStartDt() {return VPriceStartDt;} //SB20211228
	Vector VPriceEndDt = new Vector(); //SB20211228
	public Vector getVPriceEndDt() {return VPriceEndDt;} //SB20211228
	Vector VFinContMth = new Vector(); //SB20211228
	public Vector getVFinContMth() {return VFinContMth;} //SB20211228
	
	Vector VPriceDate = new Vector(); 
	public Vector getVPriceDate() {return VPriceDate;} 
	Vector VPriceRange_A = new Vector(); 
	public Vector getVPriceRange_A() {return VPriceRange_A;}
	
	Vector VPriceFormulaRemark = new Vector(); 
	public Vector getVPriceFormulaRemark() {return VPriceFormulaRemark;}
	
	Vector VSETT_FWD = new Vector(); 
	public Vector getVSETT_FWD() {return VSETT_FWD;}
	Vector VCOLOR = new Vector(); 
	public Vector getVCOLOR() {return VCOLOR;}
	
	double PRICE = 0;
	public double getPRICE() {return PRICE;}
	
}
