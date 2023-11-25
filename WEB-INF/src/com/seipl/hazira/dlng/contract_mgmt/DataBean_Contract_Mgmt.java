package com.seipl.hazira.dlng.contract_mgmt;

//DataBean Introduced By    : Samik Shah ...
//DataBean Introduced On    : 30th April, 2010 ...
//Code Reviewed By			:  
//Code Reviewed Date		:  
//Code Review Status  		:
//Last Modified By			: Samik Shah ...
//Last Modified Date		: 28th August, 2011 ...

import javax.naming.*;
import javax.sql.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

import java.util.*;
import java.util.Date;
import java.util.Date.*;
import java.io.File;
import java.sql.*;
import java.text.*;

public class DataBean_Contract_Mgmt
{
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
	UtilBean util = new UtilBean();
	
	//Following Vectors & String Variables Are Defined By Samik Shah For Transporter Master Data Retrival Process On 29th April, 2010 ...
	public Vector master_Transporter_Cd = new Vector();
	public Vector master_Transporter_Abbr = new Vector();
	public Vector master_Transporter_Count = new Vector();
	public Vector master_Transporter_Dcq = new Vector();
	public Vector daily_Buyer_Nom_Fgsa_No = new Vector();
	public Vector daily_Buyer_Nom_Fgsa_Rev_No = new Vector();
	public Vector daily_Buyer_Nom_Sn_No = new Vector();
	public Vector daily_Buyer_Nom_Sn_Rev_No = new Vector();
	public Vector daily_Buyer_Nom_Cd = new Vector();
	public Vector daily_Buyer_Nom_Abbr = new Vector();
	public Vector daily_Buyer_Nom_Dcq = new Vector();
	public Vector daily_Buyer_Nom_Plant_Cd = new Vector();
	public Vector daily_Buyer_Nom_Plant_Abbr = new Vector();
	public Vector daily_Transporter_Nom_Cd = new Vector();
	public Vector daily_Transporter_Nom_Abbr = new Vector();
	public Vector daily_Buyer_Nom_Balance_Qty = new Vector(); //Introduced By Samik Shah On 23rd August, 2010 ...
	public Vector Buyer_Contracted_Qty = new Vector(); //SB20110924
	public Vector Buyer_Allocated_Qty = new Vector(); //SB20110924
	public Vector Buyer_Nominated_Qty = new Vector(); //SB20110924
	public Vector daily_Buyer_Nom_Mdcq_Qty = new Vector(); //Introduced By Samik Shah On 25th August, 2010 ...
	public Vector daily_Buyer_Nom_LC_ADV_Flag = new Vector(); //Introduced By Samik Shah On 22nd June, 2011 ...
	public Vector daily_Buyer_Nom_Current_Balance_Amt = new Vector(); //Introduced By Samik Shah On 22nd June, 2011 ...
	public String daily_Total_Dcq = "";
	
	public Vector daily_Buyer_Nom_Mapping_Id = new Vector(); // NB20141029 ADDDED FOR LTCORA
	public Vector daily_Seller_Nom_Mapping_Id = new Vector(); // NB20141029 ADDDED FOR LTCORA
	public Vector daily_Buyer_Allocation_Mapping_Id=new Vector(); // NB20141029 ADDDED FOR LTCORA
		
	//Following Vectors & String Variables Are Defined By Samik Shah On 1st May, 2010 ...
	public Vector daily_Buyer_Gen_Day_With_Rev_No = new Vector();
	public Vector daily_Buyer_Gen_Day_Time = new Vector();
	public Vector daily_Buyer_Nom_Plant_Seq_No = new Vector();
	public Vector daily_Buyer_Nom_Plant_Seq_Abbr = new Vector();
	public Vector daily_Buyer_Nom_Qty_Mmbtu = new Vector();
	public Vector daily_Buyer_Nom_Qty_Scm = new Vector();
	public Vector master_Transporter_Mmbtu = new Vector();
	public Vector master_Transporter_Scm = new Vector();
	public String daily_Total_Mmbtu = "";
	public String daily_Total_Scm = "";
	
	public Vector daily_Buyer_regas_cargo_boe_no=new Vector();
	public Vector daily_Buyer_regas_cargo_boe_dt=new Vector();
	Vector PRE_APPROVAL=new Vector();
	Vector COMM_PRE_APPROVAL=new Vector();
	
	//Following Vector Is Defined By Samik Shah On 3rd May, 2010 ...
	public Vector daily_Buyer_Nom_Type = new Vector(); //This Vector Stores Whether Buyer Is SN Based Or LOA Based Or RE-GAS Based ...
	
	//Following Vector Is Defined By Samik Shah On 25th August, 2010 ...
	public Vector daily_Buyer_Nom_Contract_Type = new Vector(); //This Vector Stores Wether Buyer Is SN Based Or LOA Based Or RE-GAS Based ...
	
	
	//Following Vectors & String Variables Are Defined By Samik Shah For Transporter Master Data Retrival Process On 3rd May, 2010 ...
	public Vector master_Transporter_Cd_Seller_Nom = new Vector();
	public Vector master_Transporter_Abbr_Seller_Nom = new Vector();
	public Vector master_Transporter_Count_Seller_Nom = new Vector();
	public Vector master_Transporter_Dcq_Seller_Nom = new Vector();
	public Vector daily_Seller_Nom_Fgsa_No = new Vector();
	public Vector daily_Seller_Nom_Fgsa_Rev_No = new Vector();
	public Vector daily_Seller_Nom_Sn_No = new Vector();
	public Vector daily_Seller_Nom_Sn_Rev_No = new Vector();
	public Vector daily_Seller_Nom_Cd = new Vector();
	public Vector daily_Seller_Nom_Abbr = new Vector();
	public Vector daily_Seller_Nom_Dcq = new Vector();
	public Vector daily_Seller_Nom_Plant_Cd = new Vector();
	public Vector daily_Seller_Nom_Plant_Abbr = new Vector();
	public Vector daily_Transporter_Nom_Cd_Seller_Nom = new Vector();
	public Vector daily_Transporter_Nom_Abbr_Seller_Nom = new Vector();
	public String daily_Total_Dcq_Seller_Nom = "";
	
	//Following Vectors & String Variables Are Defined By Samik Shah On 3rd May, 2010 ...
	public Vector daily_Seller_Gen_Day_With_Rev_No = new Vector();
	public Vector daily_Seller_Gen_Day_Time = new Vector();
	public Vector daily_Seller_Nom_Plant_Seq_No = new Vector();
	public Vector daily_Seller_Nom_Plant_Seq_Abbr = new Vector();
	public Vector daily_Seller_Nom_Qty_Mmbtu = new Vector();
	public Vector daily_Seller_Nom_Qty_Scm = new Vector();
	public Vector master_Transporter_Mmbtu_Seller_Nom = new Vector();
	public Vector master_Transporter_Scm_Seller_Nom = new Vector();
	public String daily_Total_Mmbtu_Seller_Nom = "";
	public String daily_Total_Scm_Seller_Nom = "";
	
	//Following Vector Is Defined By Samik Shah On 3rd May, 2010 ...
	public Vector daily_Seller_Nom_Type = new Vector(); //This Vector Stores Wether Buyer Is SN Based Or LOA Based Or RE-GAS Based ...
	public Vector daily_Seller_regas_cargo_boe_no = new Vector(); //RG
	public Vector daily_Seller_regas_cargo_boe_dt = new Vector(); //RG
	//Following Vector Is Defined By Samik Shah On 25th August, 2010 ...
	public Vector daily_Seller_Nom_Contract_Type = new Vector(); //This Vector Stores Wether Buyer Is SN Based Or LOA Based Or RE-GAS Based ...
	
	//Following Vectors & String Variables Are Defined By Samik Shah On 4th May, 2010 ...
	public Vector daily_Seller_Obligation_Qty = new Vector();
	public Vector daily_Seller_Asking_Rate_Qty = new Vector();
	public Vector daily_Transporter_Obligation_Qty = new Vector();
	public Vector daily_Transporter_Asking_Rate_Qty = new Vector();
	public String daily_Total_Obligation_Qty = "";
	public String daily_Total_Asking_Rate_Qty = "";
	
	
	//Following Vectors Are Defined By Samik Shah On 4th May, 2010 ...
	//For Transporter Meters ...
	public Vector daily_Meter_Reading_Transporter_Cd = new Vector();
	public Vector daily_Meter_Reading_Transporter_Abbr = new Vector();
	public Vector daily_Meter_Reading_Transporter_Seq_Cd = new Vector();
	
	//Following Vectors Are Defined By Samik Shah On 4th May, 2010 ...
	//For Customer Meters ...
	public Vector daily_Meter_Reading_Customer_Cd = new Vector();
	public Vector daily_Meter_Reading_Customer_Abbr = new Vector();
	public Vector daily_Meter_Reading_Customer_Seq_Cd = new Vector();
	public Vector daily_Meter_Reading_Customer_Transporter_Cd = new Vector();
	public Vector daily_Meter_Reading_Customer_Transporter_Abbr = new Vector();
	
	
	//Following Vectors & String Variables Are Defined By Samik Shah On 5th May, 2010 ...
	//For Fetching Daily Meter Reading Data For Transporter ...
	public Vector daily_Meter_Reading_Transporter_Mmbtu = new Vector();
	public Vector daily_Meter_Reading_Transporter_Scm = new Vector();
	public Vector daily_Meter_Reading_Transporter_Reconcil_Mmbtu = new Vector();
	public Vector daily_Meter_Reading_Transporter_Reconcil_Scm = new Vector();
	public Vector daily_Meter_Reading_Individual_Transporter_Mmbtu = new Vector(); //Declare By Samik Shah On 15th October, 2010 ...
	public Vector daily_Meter_Reading_Transporter_Calculated_Gcv = new Vector();
	public Vector daily_Meter_Reading_Transporter_Calculated_Ncv = new Vector();
	public Vector daily_Meter_Reading_Transporter_Define_Gcv = new Vector();
	public Vector daily_Meter_Reading_Transporter_Define_Ncv = new Vector();
	public String daily_Meter_Reading_Transporter_Mmbtu_Total = "";
	public String daily_Meter_Reading_Transporter_Scm_Total = "";
	public String daily_Meter_Reading_Transporter_Reconcil_Mmbtu_Total = "";
	public String daily_Meter_Reading_Transporter_Reconcil_Scm_Total = "";
	public String daily_Meter_Reading_Individual_Transporter_Mmbtu_Total = ""; //Declare By Samik Shah On 15th October, 2010 ...
	
	/*ADDED BY RG 20150903 FOR BTU ENTRY OF METER TICKET*/
	public Vector daily_Meter_Reading_Transporter_Btu = new Vector();
	public Vector daily_Meter_Reading_Transporter_Reconcil_Btu = new Vector();
	public String daily_Meter_Reading_Transporter_Btu_Total = "";
	public String daily_Meter_Reading_Transporter_Reconcil_Btu_Total = "";
	
	public Vector daily_Meter_Reading_Customer_Btu = new Vector();
	public Vector daily_Meter_Reading_Customer_Reconcil_Btu = new Vector();
	public String daily_Meter_Reading_Customer_Btu_Total = "";
	public String daily_Meter_Reading_Customer_Reconcil_Btu_Total = "";
	
	
	//Following Vectors & String Variables Are Defined By Samik Shah On 5th May, 2010 ...
	//For Fetching Daily Meter Reading Data For Customer ...
	public Vector daily_Meter_Reading_Customer_Mmbtu = new Vector();
	public Vector daily_Meter_Reading_Customer_Scm = new Vector();
	public Vector daily_Meter_Reading_Customer_Reconcil_Mmbtu = new Vector();
	public Vector daily_Meter_Reading_Customer_Reconcil_Scm = new Vector();
	public Vector daily_Meter_Reading_Individual_Customer_Mmbtu = new Vector(); //Declare By Samik Shah On 15th October, 2010 ...
	public Vector daily_Meter_Reading_Customer_Calculated_Gcv = new Vector();
	public Vector daily_Meter_Reading_Customer_Calculated_Ncv = new Vector();
	public Vector daily_Meter_Reading_Customer_Define_Gcv = new Vector();
	public Vector daily_Meter_Reading_Customer_Define_Ncv = new Vector();
	public String daily_Meter_Reading_Customer_Mmbtu_Total = "";
	public String daily_Meter_Reading_Customer_Scm_Total = "";
	public String daily_Meter_Reading_Customer_Reconcil_Mmbtu_Total = "";
	public String daily_Meter_Reading_Customer_Reconcil_Scm_Total = "";
	public String daily_Meter_Reading_Individual_Customer_Mmbtu_Total = ""; //Declare By Samik Shah On 15th October, 2010 ...
	
	//Following Two String Variables Are Defined By Samik Shah On 5th May, 2010 ...
	//For Storing Meter Reading Generation Day & Time ...
	public String daily_Meter_Reading_Gen_Date = null;
	public String daily_Meter_Reading_Gen_Time = null;
	
	
	//Following Vectors & String Variables Are Defined By Samik Shah For Transporter & SN Master Data Retrival Process On 6th May, 2010 ...
	public Vector master_Transporter_Cd_Buyer_Allocation = new Vector();
	public Vector master_Transporter_Abbr_Buyer_Allocation = new Vector();
	public Vector master_Transporter_Count_Buyer_Allocation = new Vector();
	public Vector master_Transporter_Dcq_Buyer_Allocation = new Vector();
	public Vector daily_Buyer_Allocation_Fgsa_No = new Vector();
	public Vector daily_Buyer_Allocation_Fgsa_Rev_No = new Vector();
	public Vector daily_Buyer_Allocation_Sn_No = new Vector();
	public Vector daily_Buyer_Allocation_Sn_Rev_No = new Vector();
	public Vector daily_Buyer_Allocation_Cd = new Vector();
	public Vector daily_Buyer_Allocation_Abbr = new Vector();
	public Vector daily_Buyer_Allocation_Dcq = new Vector();
	public Vector daily_Buyer_Allocation_Plant_Cd = new Vector();
	public Vector daily_Buyer_Allocation_Plant_Abbr = new Vector();
	public Vector daily_Transporter_Nom_Cd_Buyer_Allocation = new Vector();
	public Vector daily_Transporter_Nom_Abbr_Buyer_Allocation = new Vector();
	public String daily_Total_Dcq_Buyer_Allocation = "";
	
	//Following Vectors & String Variables Are Defined By Samik Shah On 6th May, 2010 ...
	public Vector daily_Buyer_Allocation_Gen_Day_With_Rev_No = new Vector();
	public Vector daily_Buyer_Allocation_Gen_Day_Time = new Vector();
	public Vector daily_Buyer_Allocation_Plant_Seq_No = new Vector();
	public Vector daily_Buyer_Allocation_Qty_Mmbtu = new Vector();
	public Vector daily_Buyer_Allocation_Qty_Scm = new Vector();
	public Vector master_Transporter_Mmbtu_Buyer_Allocation = new Vector();
	public Vector master_Transporter_Scm_Buyer_Allocation = new Vector();
	public String daily_Total_Mmbtu_Buyer_Allocation = "";
	public String daily_Total_Scm_Buyer_Allocation = "";
	
	//Following Vector Is Defined By Samik Shah On 6th May, 2010 ...
	public Vector daily_Buyer_Allocation_Type = new Vector(); //This Vector Stores Wether Buyer Is SN Based Or LOA Based Or RE-GAS Based ...
	
	//Following Vector Is Defined By Samik Shah On 25th August, 2010 ...
	public Vector daily_Buyer_Allocation_Contract_Type = new Vector(); //This Vector Stores Wether Buyer Is SN Based Or LOA Based Or RE-GAS Based ...
	
	//Following Vectors Are Defined By Samik Shah On 10th May, 2010 ...
	public Vector daily_Customer_Meter_Allocation_Qty_Mmbtu = new Vector();
	public Vector daily_Customer_Meter_Allocation_Qty_Scm = new Vector();
	public Vector daily_Customer_Meter_Allocation_Qty_Sug = new Vector();
	public Vector daily_Buyer_Allocation_Qty_Sug = new Vector();
	public Vector daily_Meter_Based_Customer_flag = new Vector();
	public String daily_Total_Sug_Buyer_Allocation = "";
	
	//Following Vectors Are Defined By Samik Shah On 18th June, 2010 ...
	public Vector daily_JT_Customer_Cd = new Vector();
	public Vector daily_JT_Customer_Nm = new Vector();
	public Vector daily_JT_Customer_Abbr = new Vector();
	public Vector daily_JT_Fgsa_No = new Vector();
	public Vector daily_JT_Fgsa_Rev_No = new Vector();
	public Vector daily_JT_Sn_No = new Vector();
	public Vector daily_JT_Sn_Rev_No = new Vector();
	public Vector daily_JT_Contract_Type = new Vector();
	public Vector daily_JT_Plant_Seq_No = new Vector();
	public Vector daily_JT_Plant_Nm = new Vector();
	public Vector daily_JT_Gcv = new Vector();
	public Vector daily_JT_Ncv = new Vector();
	public Vector daily_JT_Transporter_Cd = new Vector();
	public Vector daily_JT_Transporter_Nm = new Vector();
	public Vector daily_JT_Transporter_Abbr = new Vector();
	public Vector daily_JT_Qty_Mmbtu = new Vector();
	public Vector daily_JT_Qty_Scm = new Vector();
	public Vector daily_JT_Seller_Nom_Qty_Mmbtu = new Vector();
	public Vector daily_JT_Transporter_Qty_Mmbtu = new Vector();
	public Vector daily_JT_SN_Qty_Mmbtu = new Vector();
	public Vector daily_JT_SN_Ref_No = new Vector(); //Introduced By Samik Shah On 19th April, 2011 ...
	public Vector daily_JT_offspec_qty=new  Vector();//Introduced By JHP On 27th Sep,2011
	public Vector daily_JT_offspec_flag=new  Vector();//Introduced By JHP On 27th Sep,2011
	public Vector daily_JT_Mapping_Id=new Vector();
	Vector daily_JT_Sn_Signing_Dt=new Vector();

	//Following Vectors & String Variables Are Defined By Samik Shah For Joint Ticket Format Preperation On 21st June, 2010 ...
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
	
	//Following Vector Has Been Introduced By Samik Shah On 28th September, 2010 ...
	public Vector sn_ref_no = new Vector();
	
	//Following (4) String Variables Has Been Declared By Samik Shah On 23rd December, 2010 ...
	public String year = "";
	public String month = "";
	public String fgsa_sn_no = "0-0";
	public String plant_seq_no = "0";
	
	//Following (3) Vector Variables Has Been Declared By Samik Shah On 23rd December, 2010 ...
	public Vector CUSTOMER_CD = new Vector();
	public Vector CUSTOMER_ABBR = new Vector();
	public Vector CUSTOMER_NAME = new Vector();
	
	//Following (3) Vector Variables Has Been Declared By Samik Shah On 24th December, 2010 ...
	public Vector FGSA_SN_NO = new Vector();
	public Vector PLANT_SEQ_NO = new Vector();
	public Vector PLANT_NAME = new Vector();
	
	//Following (2) Vector Variables Has Been Declared By Samik Shah On 28th December, 2010 ...
	public Vector MONTH_DAYS = new Vector();
	public Vector MONTH_DATES = new Vector();
	
	public String transporter_cd = "";    //Introduced By Achal On 23rd Dec, 2010 ...
	public String contract_type ="";  //Introduced By Achal On 23rd Dec, 2010 ...
	public String buyer_fgsa_no ="";  //Introduced By Achal On 23rd Dec, 2010 ...
	public String buyer_sn_no =""; //Introduced By Achal On 23rd Dec, 2010 ...
	public String nom_rev_no ="";  //Introduced By Achal On 23rd Dec, 2010 ...
	public String total_mmbtu1="";  //Introduced By Achal On 24th Dec, 2010 ...
	public String total_scm1="";    //Introduced By Achal On 24th Dec, 2010 ...
	
	//Following Vectors & String Variables Are Defined By Achal For Weekly & Monthly Buyer Nomination On 22nd December, 2010 ...
	public Vector WEEKDAYS = new Vector();
	public Vector WEEKDATES = new Vector();
	public Vector START_DT = new Vector();
	public Vector END_DT = new Vector();
	public Vector QTY_MMBTU = new Vector();
	public Vector QTY_SCM = new Vector();
	public Vector NOM_REV_NO = new Vector();
	public Vector SUM_MMBTU = new Vector();
	public Vector SUM_SCM = new Vector();	
	public Vector GEN_DT = new Vector();
	public Vector CUST_CD = new Vector();	
	public Vector CUST_NM = new Vector();
	public Vector GAS_DT = new Vector();	
	public String gcv = "";
	public String ncv = "";
	
	//Following (6) Vector Variables Has Been Defined By Samik Shah On 3rd February, 2011 ...
	public Vector daily_Buyer_Allocation_Qty_Offspec = new Vector();
	public Vector daily_Buyer_Allocation_Offspec_Rate = new Vector();
	public Vector daily_Buyer_Allocation_Offspec_Flag = new Vector();
	public Vector daily_Buyer_Allocation_Qty_FM = new Vector();
	public Vector master_Transporter_Offspec_Buyer_Allocation = new Vector();
	public Vector master_Transporter_FM_Buyer_Allocation = new Vector();
	
	//Following (2) String Variables Has Been Defined By Samik Shah On 3rd February, 2011 ...
	public String daily_Total_Offspec_Buyer_Allocation = "";
	public String daily_Total_FM_Buyer_Allocation = "";
	
	public Vector QTY_OFFSPEC = new Vector();
	public Vector QTY_FM = new Vector();
	public Vector SELLER_OBLIG = new Vector();
	
	public Vector daily_Alloc_Nom_Qty_Mmbtu = new Vector();
	public Vector daily_Alloc_Nom_Qty_Scm = new Vector();
	
	//Variables Declared by Achal on 22/04/2011
	Vector PM_QTY = new Vector();
	Vector BUYER_DEFAULT_QTY = new Vector();
	Vector BUYER_DEFAULT_REASON = new Vector();	
	Vector SELLER_OR_PAY_QTY = new Vector();
	Vector BUYER_SUSPENSION_QTY = new Vector();
	Vector FAILURE_TO_DELIVER_QTY = new Vector();
	Vector SELLER_DEFAULT_REASON = new Vector();
	Vector TAKE_OR_PAY_QTY = new Vector();
	
	
	//Following Vector Has Been Introduced By Samik Shah On 26th May, 2011 ...
	public Vector seller_Nom_Weighted_Avg = new Vector();
	
//	Following Vector Has Been Introduced By Milan Dalsaniya On 17th Oct, 2011 ...
	Vector LC_END_DT_FIN = new Vector();
	Vector LC_EXP_FLG = new Vector();
	Vector LC_DAY_FLG = new Vector();
	//Introdcue By Milan 20111018
	String lc_from_dt = "";
	String lc_to_dt = "";
	String lc_customer_cd = "";
	String lc_seq_no = "";
	
	Vector LC_FGSA_NO = new Vector();
	Vector LC_SN_NO = new Vector();
	Vector LC_CONT_TYPE = new Vector();
	Vector LC_SN_START_DT = new Vector();
	Vector LC_SN_END_DT = new Vector();
	Vector LC_TCQ = new Vector();
	Vector LC_DCQ = new Vector();
	Vector LC_FINANCIAL_YR = new Vector();
	
	
//	Introdcue By Milan 20111018
	
//	Introdcue By Milan 20111018
	String lc_fin_yr = "";
	
	
	Vector LC_REGAS_NO = new Vector();
		Vector LC_REV_NO = new Vector();
		Vector LC_REGAS_CONT_TYPE = new Vector();
		Vector LC_REGAS_START_DT = new Vector();
		Vector LC_REGAS_END_DT = new Vector();
		Vector LC_REGAS_TCQ = new Vector();
		Vector LC_REGAS_DCQ = new Vector();
		Vector LC_REGAS_FINANCIAL_YR = new Vector();

		
		String nom_mapping_id=""; //NB20141111 ADDED FOR LTCORA AND CN
		
		public Vector daily_tax_struct_dtl = new Vector();
		
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
	    			
	    			clearVectorAndStringVariables();
	    			createTable();
	    			
	    			if(callFlag.equalsIgnoreCase("DAILY_BUYER_NOM"))  //SB20110915
					{
	    				fetchDailyBuyerNomDetails(); //SB20110915
	    				checkForAdvanceCollectionforNomination(); //RG20150919
					}
	    			else if(callFlag.equalsIgnoreCase("DAILY_BUYER_NOM_REPORT"))//SB20110915
					{
	    				fetchDailyBuyerNomReport();//SB20110915
					}
	    			else if(callFlag.equalsIgnoreCase("DAILY_BUYER_NOM_REPORT_CUSTOMER"))//SB20110915
					{
	    				Customer_DTL(); //SB20181005
	    				fetchDailyBuyerNomReportCustomer();	 //SB20110915   				
					}
	    			else if(callFlag.equalsIgnoreCase("DAILY_SELLER_NOM_REPORT"))
					{
	    				fetchDailySellerNomReport();
					}
	    			else if(callFlag.equalsIgnoreCase("MONTHLY_BUYER_NOM"))
					{
	    				fetchMonthlyBuyerNomDetails();
					}
	    			else if(callFlag.equalsIgnoreCase("WEEKLY_BUYER_NOM"))
					{
	    				fetchWeeklyBuyerNomDetails();
					}
	    			else if(callFlag.equalsIgnoreCase("DAILY_BUYER_NOM_WEEKLY_DTLS"))
					{
	    				fetchWeeklyBuyerNomDetailsDtls();
					}
	    			else if(callFlag.equalsIgnoreCase("DAILY_SELLER_NOM"))//SB20110915
					{
	    				fetchDailySellerNomDetails();//SB20110915
					}
	    			else if(callFlag.equalsIgnoreCase("DAILY_METER_READING"))
	    			{
	    				fetchDailyMeterReading();
	    				fetchLastMeterReading(); //SB20181008
	    			}
	    			else if(callFlag.equalsIgnoreCase("DAILY_ALLOCATION"))//SB20110915
					{
	    				fetchDailyAllocationDetails();//SB20110915
	    				fetchLastMeterReading(); //SB20181008
					}
	    			else if(callFlag.equalsIgnoreCase("MONTHLY_LIABILITY"))
					{
	    				MONTH_DAYS.clear();
	    				MONTH_DATES.clear();
	    				
	    				fetchCustomerList();
	    				
	    				if(year!=null && !year.trim().equals("") && !year.trim().equals("0") 
	    				&& month!=null && !month.trim().equals("") && !month.trim().equals("0") 
	    				&& customer_cd!=null && !customer_cd.trim().equals("") && !customer_cd.trim().equals("0") 
	    				&& contract_type!=null && !contract_type.trim().equals(""))
	    				{
	    					fetchFgsaSnListOfSelectedCustomer();
	    				}
	    				
	    				if(year!=null && !year.trim().equals("") && !year.trim().equals("0") 
	    				&& month!=null && !month.trim().equals("") && !month.trim().equals("0") 
	    				&& customer_cd!=null && !customer_cd.trim().equals("") && !customer_cd.trim().equals("0") 
	    				&& contract_type!=null && !contract_type.trim().equals("") 
	    				&& fgsa_sn_no!=null && !fgsa_sn_no.trim().equals("") && !fgsa_sn_no.trim().equals("0-0"))
	    				{
	    					//fetchPlantOfSelectedCustomer();
	    					fetchLiabilityDetails();
	    				}
					}
	    			else if(callFlag.equalsIgnoreCase("DAILY_JOINT_TICKET_LIST"))
	    			{
	    				fetchDailyJointTicketList();
	    			}
	    			else if(callFlag.equalsIgnoreCase("DAILY_JOINT_TICKET_LIST_OLD"))
	    			{
	    				fetchDailyJointTicketList_OLD();
	    			}
	    			else if(callFlag.equalsIgnoreCase("JOINT_TICKET_REPORT"))
	    			{
	    				fetchJointTicketReport();
	    				fetchTransporterDtl();
	    				SupplierDtl(); //SB20181008
	    			}
	    			else if(callFlag.equalsIgnoreCase("JOINT_TICKET_REPORT_OLD"))
	    			{
	    				fetchJointTicketReport_OLD();
	    			}
	    			else if(callFlag.equalsIgnoreCase("GENERATE_GAS_DATE_FOR_NOMINATION"))
	    			{
	    				generateGasDateForNomination();
	    			}
	    			else if(callFlag.equalsIgnoreCase("GENERATE_GEN_DATE_FOR_NOMINATION"))
	    			{
	    				generateGenDateForNomination();
	    			}
	    			else if(callFlag.equalsIgnoreCase("GENERATE_GAS_DATE_FOR_ALLOCATION"))
	    			{
	    				generateGasDateForAllocation();
	    			}
	    			else if(callFlag.equalsIgnoreCase("GENERATE_GEN_DATE_FOR_ALLOCATION"))
	    			{
	    				generateGenDateForAllocation();
	    			}
	    			else if(callFlag.equalsIgnoreCase("SN_LOA_PER_LC"))
	    			{
	    				getSNLOAperLC();
	    				getREGASperLC();
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
	
	public void createTable() throws Exception {
		try {
			String query = "SELECT COUNT(TNAME) FROM TAB WHERE UPPER(TNAME)='FMS7_DAILY_ALLOCATION_DEL' ";
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				int count = rset.getInt(1);
				
				if(count==0) {
					query = "CREATE TABLE FMS7_DAILY_ALLOCATION_DEL (	"
							+ "SN_NO NUMBER(6,0) NOT NULL , "
							+ "SN_REV_NO NUMBER(2,0) NOT NULL , "
							+ "FGSA_NO NUMBER(6,0) NOT NULL , "
							+ "FGSA_REV_NO NUMBER(2,0) NOT NULL , "
							+ "NOM_REV_NO NUMBER(2,0) NOT NULL , "
							+ "TRANSPORTER_CD NUMBER(6,0) NOT NULL , "
							+ "CUSTOMER_CD NUMBER(6,0) NOT NULL , "
							+ "PLANT_SEQ_NO NUMBER(2,0) NOT NULL , "
							+ "GAS_DT DATE NOT NULL , "
							+ "CONTRACT_TYPE CHAR(1 BYTE) NOT NULL , "
							+ "GEN_DT DATE NOT NULL , "
							+ "GEN_TIME VARCHAR2(6 BYTE) NOT NULL , "
							+ "GCV NUMBER(17,12) NOT NULL , "
							+ "NCV NUMBER(17,12) NOT NULL , "
							+ "QTY_MMBTU NUMBER(9,2) NOT NULL , "
							+ "QTY_SCM NUMBER(11,2) NOT NULL , "
							+ "QTY_SUG NUMBER(9,2), "
							+ "SN_DOC_NO VARCHAR2(10 BYTE), "
							+ "EMP_CD NUMBER(6,0), "
							+ "ENT_DT DATE, "
							+ "FLAG CHAR(1 BYTE), "
							+ "QTY_FM NUMBER(9,2), "
							+ "QTY_OFFSPEC NUMBER(9,2), "
							+ "OFFSPEC_FLAG CHAR(1 BYTE), "
							+ "OFFSPEC_RATE NUMBER(6,4), "
							+ "EXCHG_RATE NUMBER(7,4), "
							+ "SALES_RATE NUMBER(8,4), "
							+ "COST NUMBER(11,2), "
							+ "MAPPING_ID VARCHAR2(40 BYTE), "
							+ "CONSTRAINT FMS7_DAILY_ALLOC_DEL_PK PRIMARY KEY (SN_NO, SN_REV_NO, FGSA_NO, FGSA_REV_NO, NOM_REV_NO, TRANSPORTER_CD, CUSTOMER_CD, PLANT_SEQ_NO, GAS_DT, CONTRACT_TYPE)"
							+ ")";
					stmt.executeUpdate(query);
				}
			}
			int count_1 = 0;
			query = "SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME)='FMS7_METER_TICKET_READING' "
					+ "AND COLUMN_NAME='QTY_BTU' ";
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				count_1 = rset.getInt(1);
				if(count_1==0) {
					query = "ALTER TABLE FMS7_METER_TICKET_READING ADD QTY_BTU NUMBER(10,2) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE FMS7_METER_TICKET_READING ADD RECONCIL_QTY_BTU NUMBER(10,2) ";
					stmt.executeUpdate(query);
				}
			}
			count_1 = 0;
			query = "SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME)='DLNG_SN_MST' "
					+ "AND COLUMN_NAME='RE_QTY' ";
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				count_1 = rset.getInt(1);
				if(count_1==0) {
					query = "ALTER TABLE DLNG_SN_MST ADD RE_QTY NUMBER(10,2) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE DLNG_SN_MST ADD FIRM_QTY NUMBER(10,2) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE FMS7_LOA_MST ADD RE_QTY NUMBER(10,2) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE FMS7_LOA_MST ADD FIRM_QTY NUMBER(10,2) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE FMS7_LOA_MST ADD SPLIT_TCQ_FLAG CHAR(1) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE DLNG_SN_MST ADD SPLIT_TCQ_FLAG CHAR(1) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE FMS7_LOA_MST ADD ADVANCE_COLLECTION CHAR(1) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE DLNG_SN_MST ADD ADVANCE_COLLECTION CHAR(1) ";
					stmt.executeUpdate(query);
				}
			}
			
			count_1 = 0;
			query = "SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME)='DLNG_SN_MST' "
					+ "AND COLUMN_NAME='ADVANCE_COLLECTION_FLAG' ";
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				count_1 = rset.getInt(1);
				if(count_1==0) {
					query = "ALTER TABLE FMS7_LOA_MST ADD ADVANCE_COLLECTION_FLAG CHAR(1) ";
					stmt.executeUpdate(query);
					
					query = "ALTER TABLE DLNG_SN_MST ADD ADVANCE_COLLECTION_FLAG CHAR(1) ";
					stmt.executeUpdate(query);
				}
			}
			conn.commit();
			} 
			catch(Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
	}
	
	public void checkForAdvanceCollectionforNomination()
	{
		methodName = "checkForAdvanceCollectionforNomination()";
		try
		{
			String query = "";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void fetchJointTicketReport_OLD()
	{
		methodName = "fetchJointTicketReport()";
		try 
		{
			String contact_addr_flag = "";
			
			queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' ')," +
						  "addr_flag,phone,fax_1  " +
						  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
						  "WHERE A.customer_cd="+customer_cd+" AND A.def_jt_flag='Y' AND " +
						  "A.seq_no="+customer_contact_cd+" AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
						  "B.seq_no="+customer_contact_cd+" AND " +
						  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
			//System.out.println("Customer Contact Person Fetch Query = "+queryString);
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
							  "WHERE A.customer_cd="+customer_cd+" AND A.address_type='"+contact_addr_flag+"' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"' AND " +
							  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
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
							  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+plant_no+"' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
							  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
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
						  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
			//System.out.println("Supplier Details Fetch Query = "+queryString);
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
						  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
			//System.out.println("Supplier Address Fetch Query = "+queryString);
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
	Vector daily_JT_alloc_id2 = new Vector (); 
	public void fetchTransporterDtl()
	{
		try
		{
			
			int trans_count=0;
				queryString = "SELECT DISTINCT(A.trans_cd),NVL(A.gcv,'0'),NVL(A.ncv,'0'),alloc_id " +
				  			  " FROM DLNG_ALLOC_MST A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  " A.party_cd="+customer_cd+" AND " +
				  			  " A.ALLOC_ID like '"+customer_cd+"-%-%-%-%-%"+plant_seq_no+"' "
				  			  + " ORDER BY A.trans_cd";
				//System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					daily_JT_Transporter_Cd.add(rset.getString(1));
					daily_JT_Gcv.add(nf.format(Double.parseDouble(rset.getString(2))));
					daily_JT_Ncv.add(nf.format(Double.parseDouble(rset.getString(3))));
					daily_JT_alloc_id.add(rset.getString(4));
					trans_count++;
				}
				
				if(trans_count==0)
				{
					daily_JT_Transporter_Cd.add("0");
					daily_JT_Gcv.add("0");
					daily_JT_Ncv.add("0");
				}
				for(int j=0; j<daily_JT_Transporter_Cd.size(); j++)
				{
					queryString = "SELECT NVL(A.TRANS_NAME,' '),NVL(A.TRANS_ABBR,' ') " +
					  			  "FROM DLNG_TRANS_MST A WHERE A.trans_cd="+daily_JT_Transporter_Cd.elementAt(j)+" AND " +
					  			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM DLNG_TRANS_MST B WHERE " +
					  			  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.trans_cd=B.trans_cd)";
//					System.out.println("Query For Fetching Transporter Name & Abbriviation = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						daily_JT_Transporter_Nm.add(rset.getString(1));
						daily_JT_Transporter_Abbr.add(rset.getString(2));
					}
					else
					{
						daily_JT_Transporter_Nm.add(" ");
						daily_JT_Transporter_Abbr.add(" ");
					}
					
					queryString = "SELECT NVL(SUM(A.ENTRY_TOT_ENE),'0') " +
					  			  "FROM DLNG_ALLOC_MST A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  			  "A.party_cd="+customer_cd+" AND " +
					  			  "A.alloc_id='"+daily_JT_alloc_id.elementAt(j)+"' AND " +
					  			  "A.trans_cd="+daily_JT_Transporter_Cd.elementAt(j)+" ";
//					System.out.println("Query For Fetching Transporter QTY Value(s) In MMBTU(s) Details = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						daily_JT_Transporter_Qty_Mmbtu.add(nf.format(Double.parseDouble(rset.getString(1))));
					}
					else
					{
						daily_JT_Transporter_Qty_Mmbtu.add("0");
					}
					double seller_qty_mmbtu = 0;
					
					/*queryString1 = "SELECT CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0') "
							+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
							+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' AND A.FCC_FLAG='Y' AND customer_cd='"+customer_cd+"' "
							+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no AND A.sn_no=B.sn_no AND"
							+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y')";
//						System.out.println("Fetching SN COntracts.."+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);*/
//						
						queryString6 = "SELECT sum(NVL(TRUCK_VOL,'0')) FROM DLNG_DAILY_TRUCK_SCH_DTL WHERE "
							+ " SCH_ID='"+daily_JT_alloc_id.elementAt(j)+"' AND "
							+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ " AND TRANS_CD='"+daily_JT_Transporter_Cd.elementAt(j)+"'"
							+ "AND REV_NO = ("
							+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_TRUCK_SCH_DTL WHERE "
							+ " SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
							+ " AND SCH_ID='"+daily_JT_alloc_id.elementAt(j)+"' AND TRANS_CD='"+daily_JT_Transporter_Cd.elementAt(j)+"')  ";
								System.out.println("queryString6****"+queryString6);
						
						rset4 = stmt3.executeQuery(queryString6);
						if(rset4.next()) {
							seller_qty_mmbtu += Double.parseDouble(rset4.getString(1));	
							daily_JT_Seller_Nom_Qty_Mmbtu.add(nf.format(seller_qty_mmbtu));
						}else {
							daily_JT_Seller_Nom_Qty_Mmbtu.add("0");
						}		
//					}
					
					
					
					
					queryString = "SELECT NVL(SUM(A.EXIT_TOT_ENE),'0') " +
				  			  "FROM DLNG_ALLOC_MST A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.party_cd="+customer_cd+" AND " +
				  			  "A.alloc_id='"+daily_JT_alloc_id.elementAt(j)+"' "
				  			  + "AND a.trans_cd='"+daily_JT_Transporter_Cd.elementAt(j)+"' "; //RG
					//System.out.println("Query For Fetching QTY Value(s) In MMBTU(s) & SCM(s) = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble(rset.getString(1))));
//						daily_JT_Qty_Scm.add(nf.format(Double.parseDouble(rset.getString(2))));
					}
					else
					{
						daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble("0")));
//						daily_JT_Qty_Scm.add(nf.format(Double.parseDouble("0")));
					}
				}
				
				queryString = "SELECT distinct alloc_id,nom_rev_no,mapping_id " +
		  			  "FROM DLNG_ALLOC_MST A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
		  			  + " and party_cd='"+customer_cd+"' and alloc_id like '"+customer_cd+"-%-%-%-%-%"+plant_seq_no+"' "+
		  			  "ORDER BY alloc_id";
				
				//System.out.println("query for fetching all contract..."+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					String temp_alloc_id [] = rset.getString(1).split("-");
					daily_JT_Fgsa_No.add(temp_alloc_id [1]);
					daily_JT_Sn_No.add(temp_alloc_id [3]);
					daily_JT_Fgsa_Rev_No.add(temp_alloc_id [2]);
					daily_JT_Sn_Rev_No.add(temp_alloc_id [4]);
					daily_JT_Mapping_Id.add(rset.getString(3));
					daily_JT_alloc_id2.add(rset.getString(1));
				}
					
				for(int i=0;i<daily_JT_Fgsa_No.size();i++)
				{
				queryString = "SELECT NVL(SUM(A.ENTRY_TOT_ENE),'0') " +
			  			  "FROM DLNG_ALLOC_MST A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
			  			  "A.party_cd="+customer_cd+" AND " +
			  			  "A.alloc_id = '"+daily_JT_alloc_id2.elementAt(i)+"' ";
				if(contract_type.equals("C") || contract_type.equals("T"))
				{
					queryString+=" and mapping_id='"+daily_JT_Mapping_Id.elementAt(i)+"' ";
				}
					//System.out.println("Query For Fetching SN Wise QTY Value(s) In MMBTU(s) Details = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						daily_JT_SN_Qty_Mmbtu.add(rset.getString(1));
					}
					else
					{
						daily_JT_SN_Qty_Mmbtu.add("0");
					}
					queryString = "SELECT NVL(sum(A.qty_offspec),'0') " +
					  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  "A.customer_cd="+customer_cd+" AND " +
					  "A.plant_seq_no="+plant_seq_no+" AND " +
					  "A.fgsa_no='"+daily_JT_Fgsa_No.elementAt(i)+"' AND " +
			  			"A.sn_no='"+daily_JT_Sn_No.elementAt(i)+"' AND " +
			  			 "A.contract_type='"+contract_type+"' and A.offspec_flag='Y' ";
					if(contract_type.equals("C") || contract_type.equals("T"))
					{
						queryString+=" and mapping_id='"+daily_JT_Mapping_Id.elementAt(i)+"' ";
					}
				//System.out.println("Query For Fetching OFFSpec QTY Value(s) = "+queryString);
//				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_offspec_qty.add(nf.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					daily_JT_offspec_qty.add("0");
				}
				queryString = "SELECT NVL(sum(A.qty_offspec),'0') " +
					  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  "A.customer_cd="+customer_cd+" AND " +
					  "A.plant_seq_no="+plant_seq_no+" AND " +
					  "A.fgsa_no='"+daily_JT_Fgsa_No.elementAt(i)+"' AND " +
			  			"A.sn_no='"+daily_JT_Sn_No.elementAt(i)+"' AND " +
			  			 "A.contract_type='"+contract_type+"' and A.offspec_flag='N' ";
				if(contract_type.equals("C") || contract_type.equals("T"))
				{
					queryString+=" and mapping_id='"+daily_JT_Mapping_Id.elementAt(i)+"' ";
				}
					//System.out.println("Query For Fetching OFFSpec QTY Value(s) = "+queryString);
//					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						daily_JT_offspec_flag.add(nf.format(Double.parseDouble(rset.getString(1))));
					}
					else
					{
						daily_JT_offspec_flag.add("0");
					}
					if(contract_type.equalsIgnoreCase("S"))
					{
						queryString = "SELECT sn_ref_no " +
									  "FROM DLNG_SN_MST WHERE " +
						  			  "flsa_no='"+daily_JT_Fgsa_No.elementAt(i)+"' AND " +
						  			  "sn_no='"+daily_JT_Sn_No.elementAt(i)+"' AND " +
						  			  "sn_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+customer_cd+" " +
						  			  "ORDER BY sn_rev_no DESC";
						//System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							daily_JT_SN_Ref_No.add(rset.getString(1)==null?" ":rset.getString(1));
						}
						else
						{
							daily_JT_SN_Ref_No.add(" ");
						}
					}
					else if(contract_type.equalsIgnoreCase("L"))
					{
						queryString = "SELECT loa_ref_no " +
									  "FROM FMS7_LOA_MST WHERE " +
						  			  "tender_no='"+daily_JT_Fgsa_No.elementAt(i)+"' AND " +
						  			  "loa_no='"+daily_JT_Sn_No.elementAt(i)+"' AND " +
						  			  "loa_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+customer_cd+" " +
						  			  "ORDER BY loa_rev_no DESC";
						//System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							daily_JT_SN_Ref_No.add(rset.getString(1)==null?" ":rset.getString(1));
						}
						else
						{
							daily_JT_SN_Ref_No.add(" ");
						}
					}
					else
					{
						daily_JT_SN_Ref_No.add(" ");
					}
					
					if(contract_type.equals("C") || contract_type.equals("T"))
					{
						String query="SELECT TO_CHAR(TO_DATE(signing_dt,'DD/MM/YYYY'),'ddth') || ' ' || "
								+ "TO_CHAR(TO_DATE(signing_dt,'DD/MM/YYYY'),'Month') || ' ' || "
								+ "TO_CHAR(signing_dt,'YYYY') "
								+ "from fms8_lng_regas_mst where "
								+ "customer_cd='"+customer_cd+"' and mapping_id='"+daily_JT_Mapping_Id.elementAt(i)+"' "
								+ " ";
						//System.out.println("fetching sign dt.."+query);
						rset1=stmt1.executeQuery(query);
						if(rset1.next())
						{
							daily_JT_Sn_Signing_Dt.add(rset1.getString(1));
						}else {
							daily_JT_Sn_Signing_Dt.add("");
						}
					}
					else
					{
						daily_JT_Sn_Signing_Dt.add("");
					}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	Vector daily_JT_alloc_id = new Vector();
	
	public void fetchDailyJointTicketList()
	{	methodName = "fetchDailyJointTicketList()";
		try 
		{
//			Hiren_20200415 to check columns exist or not
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getColumns(null, null, "DLNG_ALLOC_MST", "NCV");
			 if (rs.next()) {
			      //Column in table exist
//				 System.out.println("*************exist****************");
			    }else {
//			    	System.out.println("*************not exist****************");
			    	String addColumn = "ALTER TABLE DLNG_ALLOC_MST ADD (NCV	NUMBER(17,12) NULL ,GCV	NUMBER(17,12) NULL)";
			    	stmt.executeQuery(addColumn);
			    }
			queryString = "SELECT distinct PARTY_CD,ALLOC_ID " +
		  			  "FROM DLNG_ALLOC_MST A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') "+
		  			  "ORDER BY PARTY_CD";
//			System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String alloc_id [] =  rset.getString(2).split("-");
				daily_JT_Contract_Type.add("S");
				daily_JT_Plant_Seq_No.add(alloc_id [6]);
				daily_JT_Customer_Cd.add(rset.getString(1));
				daily_JT_alloc_id.add(rset.getString(2));
			}
			
			for(int i=0;i<daily_JT_Customer_Cd.size();i++)
			{
				queryString = "SELECT NVL(A.customer_name,' '),NVL(A.customer_abbr,' ') " +
			  			  "FROM FMS7_CUSTOMER_MST A WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
			  			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B WHERE " +
			  			  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.customer_cd=B.customer_cd)";
				//System.out.println("Query For Fetching Customer Name & Abbriviation = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Customer_Nm.add(rset.getString(1));
					daily_JT_Customer_Abbr.add(rset.getString(2));
				}
				else
				{
					daily_JT_Customer_Nm.add(" ");
					daily_JT_Customer_Abbr.add(" ");
				}
				
				double seller_qty_mmbtu = 0;
				
				/*Re-write by HA20200221 
				queryString = "SELECT NVL(A.qty_mmbtu,'0') " +
				  			  "FROM FMS7_DAILY_SELLER_NOM_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND "
				  			  + "A.CONTRACT_TYPE='"+daily_JT_Contract_Type.elementAt(i)+"' AND " + //RG
				  			  "A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_DAILY_SELLER_NOM_DTL B WHERE " +
				  			  "B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "B.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "B.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  			  "A.fgsa_no=B.fgsa_no AND A.sn_no=B.sn_no AND " +
				  			  "A.contract_type=B.contract_type AND A.transporter_cd=B.transporter_cd)";
				System.out.println("queryString****"+queryString); */
				
				queryString1 = "SELECT CUSTOMER_CD,FlSA_NO,FlSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0') "
						+ " FROM DLNG_SN_MST A WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')"
						+ " AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' AND A.FCC_FLAG='Y' AND customer_cd='"+daily_JT_Customer_Cd.elementAt(i)+"' "
						+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
						+ " A.customer_cd=B.customer_cd AND  B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y')";
					System.out.println("Fetching SN COntracts.."+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)+"-"+rset1.getString(2)+"-"+rset1.getString(3)+"-"+rset1.getString(4)+"-"+rset1.getString(5);
//					
					queryString6 = "SELECT NVL(DAY_VOL,'0'),NVL(TOT_ENE,'0') FROM DLNG_DAILY_SCH WHERE "
						+ "PARTY_CD='"+daily_JT_Customer_Cd.elementAt(i)+"' AND MAPPING_ID='"+map_id+"' AND "
						+ "SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+daily_JT_Plant_Seq_No.elementAt(i)+"' "
						+ "AND REV_NO = ("
						+ "SELECT MAX(REV_NO) FROM DLNG_DAILY_SCH WHERE PARTY_CD='"+daily_JT_Customer_Cd.elementAt(i)+"' AND "
						+ "MAPPING_ID='"+map_id+"' AND SCH_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') "
						+ "AND SCH_ID='"+map_id+"-"+rset1.getString(4)+"-"+daily_JT_Plant_Seq_No.elementAt(i)+"') ";
							System.out.println("queryString6****"+queryString6);
					
					rset4 = stmt3.executeQuery(queryString6);
					if(rset4.next()) {
						seller_qty_mmbtu += Double.parseDouble(rset4.getString(1));		
					}		
				}
				daily_JT_Seller_Nom_Qty_Mmbtu.add(nf.format(seller_qty_mmbtu));
				
				queryString = "SELECT NVL(SUM(A.ENTRY_TOT_ENE),'0') " +
				  			  "FROM DLNG_ALLOC_MST A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.party_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND "
				  			  + "ALLOC_ID='"+daily_JT_alloc_id.elementAt(i)+"' ";
				System.out.println("Query For Fetching QTY Value(s) In MMBTU(s) & SCM(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble(rset.getString(1))));
//					daily_JT_Qty_Scm.add(nf.format(Double.parseDouble(rset.getString(2))));
				}
				else
				{
					daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble("0")));
//					daily_JT_Qty_Scm.add(nf.format(Double.parseDouble("0")));
				}
				
				queryString = "SELECT NVL(A.plant_name,' ') " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no)";
				//System.out.println("Query For Fetching Plant Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Plant_Nm.add(rset.getString(1));
				}
				else
				{
					daily_JT_Plant_Nm.add(" ");
				}
				
				Vector cont_cd = new Vector();
				Vector cont_nm = new Vector();
				Vector cont_desg = new Vector();
				int cont_count = 0;
				
				queryString2 = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' ') " +
							  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
							  "WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND A.def_jt_flag='Y' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
							  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				//System.out.println("Customer Contact Person Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				while(rset2.next())
				{
					cont_cd.add(rset2.getString(1));
					cont_nm.add(rset2.getString(2).trim());
					cont_desg.add(rset2.getString(3).trim());
					++cont_count;
				}
				
				if(cont_count==0)
				{
					cont_cd.add("0");
					cont_nm.add("");
					cont_desg.add("");
				}
				
				Customer_Contact_Cd.add(cont_cd);
				Customer_Contact_Nm.add(cont_nm);
				Customer_Contact_Desg.add(cont_desg);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void fetchDailyJointTicketList_OLD()
	{
		methodName = "fetchDailyJointTicketList()";
		try 
		{
			//Following Data Are Related To SN ...
			queryString = "SELECT A.customer_cd,A.plant_seq_no " +
						  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
						  "GROUP BY A.customer_cd,A.plant_seq_no ORDER BY A.customer_cd,A.plant_seq_no";
			//System.out.println("Query For Fetching Unique SN & Plant From Daily Allocation Table = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				daily_JT_Customer_Cd.add(rset.getString(1));
				daily_JT_Plant_Seq_No.add(rset.getString(2));
			}
			
			for(int i=0; i<daily_JT_Customer_Cd.size(); i++)
			{
				Vector temp_fgsa_no = new Vector();
				Vector temp_fgsa_rev_no = new Vector();
				Vector temp_sn_no = new Vector();
				Vector temp_sn_ref_no = new Vector();
				Vector temp_sn_rev_no = new Vector();
				Vector temp_contract_type = new Vector();
				Vector temp_sn_qty = new Vector();
				String tmp_fgsa_no = "";
				String tmp_fgsa_rev_no = "";
				String tmp_sn_no = "";
				String tmp_sn_ref_no = "";
				String tmp_sn_rev_no = "";
				String tmp_contract_type = "";
				String tmp_sn_qty = "";
				String tmp_sn_offspec_qty="";//JHP
				String tmp_sn_pffspec_flag="";//JHP
				int sn_count = 0;
								
				Vector temp_transporter_cd = new Vector();
				Vector temp_transporter_nm = new Vector();
				Vector temp_transporter_abbr = new Vector();
				Vector temp_transporter_qty = new Vector();
				Vector temp_gcv = new Vector();
				Vector temp_ncv = new Vector();
				String tmp_transporter_cd = "";
				String tmp_transporter_nm = "";
				String tmp_transporter_abbr = "";
				String tmp_transporter_qty = "";
				String tmp_gcv = "";
				String tmp_ncv = "";
				int trans_count = 0;
				
				queryString = "SELECT NVL(A.customer_name,' '),NVL(A.customer_abbr,' ') " +
	  			  			  "FROM FMS7_CUSTOMER_MST A WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
	  			  			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B WHERE " +
	  			  			  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.customer_cd=B.customer_cd)";
				//System.out.println("Query For Fetching Customer Name & Abbriviation = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Customer_Nm.add(rset.getString(1));
					daily_JT_Customer_Abbr.add(rset.getString(2));
				}
				else
				{
					daily_JT_Customer_Nm.add(" ");
					daily_JT_Customer_Abbr.add(" ");
				}
				
				/*queryString = "SELECT fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" " +
				  			  "GROUP BY fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type";
				//System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_fgsa_no.add(rset.getString(1));
					temp_fgsa_rev_no.add(rset.getString(2));
					temp_sn_no.add(rset.getString(3));
					temp_sn_rev_no.add(rset.getString(4));
					temp_contract_type.add(rset.getString(5));
					tmp_fgsa_no += rset.getString(1)+"~~";
					tmp_fgsa_rev_no += rset.getString(2)+"~~";
					tmp_sn_no += rset.getString(3)+"~~";
					tmp_sn_rev_no += rset.getString(4)+"~~";
					tmp_contract_type += rset.getString(5)+"~~";
					
					++sn_count;
				}
				
				if(sn_count==0)
				{
					temp_fgsa_no.add("0");
					temp_fgsa_rev_no.add("0");
					temp_sn_no.add("0");
					temp_sn_rev_no.add("0");
					temp_contract_type.add(" ");
					tmp_fgsa_no += "0"+"~~";
					tmp_fgsa_rev_no += "0"+"~~";
					tmp_sn_no += "0"+"~~";
					tmp_sn_rev_no += "0"+"~~";
					tmp_contract_type += " "+"~~";
				}*/
	
				queryString = "SELECT DISTINCT fgsa_no,sn_no,contract_type " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" " +
				  			  "ORDER BY fgsa_no,sn_no,contract_type";
				//System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_fgsa_no.add(rset.getString(1));
					temp_sn_no.add(rset.getString(2));
					temp_contract_type.add(rset.getString(3));
					tmp_fgsa_no += rset.getString(1)+"~~";
					tmp_sn_no += rset.getString(2)+"~~";
					tmp_contract_type += rset.getString(3)+"~~";
					
					++sn_count;
				}
				
				if(sn_count==0)
				{
					temp_fgsa_no.add("0");
					temp_sn_no.add("0");
					temp_contract_type.add(" ");
					tmp_fgsa_no += "0"+"~~";
					tmp_sn_no += "0"+"~~";
					tmp_contract_type += " "+"~~";
				}
				tmp_fgsa_rev_no += "0"+"~~";
				tmp_sn_rev_no += "0"+"~~";
				temp_fgsa_rev_no.add("0");
				temp_sn_rev_no.add("0");
				
				daily_JT_Fgsa_No.add(tmp_fgsa_no);
				daily_JT_Fgsa_Rev_No.add(tmp_fgsa_rev_no);
				daily_JT_Sn_No.add(tmp_sn_no);
				daily_JT_Sn_Rev_No.add(tmp_sn_rev_no);
				daily_JT_Contract_Type.add(tmp_contract_type);
				
				for(int j=0; j<temp_sn_no.size(); j++)
				{
					queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
					  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
					  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
					  			  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
					  			  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
					  			  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
					  			  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
					  			  "A.contract_type='"+temp_contract_type.elementAt(j)+"'";
					//System.out.println("Query For Fetching SN Wise QTY Value(s) In MMBTU(s) Details = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_sn_qty.add(nf.format(Double.parseDouble(rset.getString(1))));
						tmp_sn_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					}
					else
					{
						temp_sn_qty.add(nf.format(Double.parseDouble("0")));
						tmp_sn_qty += "0"+"~~";
					}
					queryString = "SELECT NVL(sum(A.qty_offspec),'0') " +
		  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
		  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
		  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
		  			  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
		  			  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
		  			  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
		  			  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
		  			  "A.contract_type='"+temp_contract_type.elementAt(j)+"' and A.offspec_flag='Y'";
				//System.out.println("Query For Fetching OFFSpec QTY Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tmp_sn_offspec_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					
				}
				else
				{
					
					tmp_sn_offspec_qty += " "+"~~";
					
				}
				queryString = "SELECT NVL(sum(A.qty_offspec),'0') " +
				  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
				  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
				  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
				  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
				  "A.contract_type='"+temp_contract_type.elementAt(j)+"' and A.offspec_flag='N'";
				//System.out.println("Query For Fetching OFFSpec QTY Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				tmp_sn_pffspec_flag += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
				
				}
				else
				{
				
				tmp_sn_pffspec_flag += " "+"~~";
				
				}
					if((""+temp_contract_type.elementAt(j)).trim().equalsIgnoreCase("S"))
					{
						queryString = "SELECT sn_ref_no " +
									  "FROM DLNG_SN_MST WHERE " +
						  			  "fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
						  			  "sn_no="+temp_sn_no.elementAt(j)+" AND " +
						  			  "sn_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" " +
						  			  "ORDER BY sn_rev_no DESC";
						//System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							tmp_sn_ref_no += (rset.getString(1)==null?" ":rset.getString(1))+"~~";
						}
						else
						{
							tmp_sn_ref_no += " "+"~~";
						}
					}
					else if((""+temp_contract_type.elementAt(j)).trim().equalsIgnoreCase("L"))
					{
						queryString = "SELECT loa_ref_no " +
									  "FROM FMS7_LOA_MST WHERE " +
						  			  "tender_no="+temp_fgsa_no.elementAt(j)+" AND " +
						  			  "loa_no="+temp_sn_no.elementAt(j)+" AND " +
						  			  "loa_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" " +
						  			  "ORDER BY loa_rev_no DESC";
						//System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							tmp_sn_ref_no += (rset.getString(1)==null?" ":rset.getString(1))+"~~";
						}
						else
						{
							tmp_sn_ref_no += " "+"~~";
						}
					}
					else
					{
						tmp_sn_ref_no += " "+"~~";
					}
				}
				daily_JT_offspec_qty.add(tmp_sn_offspec_qty); //JHP
				daily_JT_offspec_flag.add(tmp_sn_pffspec_flag);//JHP
				daily_JT_SN_Qty_Mmbtu.add(tmp_sn_qty);
				daily_JT_SN_Ref_No.add(tmp_sn_ref_no);
				
				queryString = "SELECT DISTINCT(A.transporter_cd),NVL(A.gcv,'0'),NVL(A.ncv,'0') " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" ORDER BY A.transporter_cd";
				//System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_transporter_cd.add(rset.getString(1));
					temp_gcv.add(nf.format(Double.parseDouble(rset.getString(2))));
					temp_ncv.add(nf.format(Double.parseDouble(rset.getString(3))));
					tmp_transporter_cd += rset.getString(1)+"~~";
					tmp_gcv += nf.format(Double.parseDouble(rset.getString(2)))+"~~";
					tmp_ncv += nf.format(Double.parseDouble(rset.getString(3)))+"~~";
					++trans_count;
				}
				
				if(trans_count==0)
				{
					temp_transporter_cd.add("0");
					temp_gcv.add(nf.format(Double.parseDouble("0")));
					temp_ncv.add(nf.format(Double.parseDouble("0")));
					tmp_transporter_cd += "0"+"~~";
					tmp_gcv += nf.format(Double.parseDouble("0"))+"~~";
					tmp_ncv += nf.format(Double.parseDouble("0"))+"~~";
				}
				
				for(int j=0; j<temp_transporter_cd.size(); j++)
				{
					queryString = "SELECT NVL(A.transporter_name,' '),NVL(A.transporter_abbr,' ') " +
					  			  "FROM DLNG_TRANS_MST A WHERE A.trans_cd="+temp_transporter_cd.elementAt(j)+" AND " +
					  			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM DLNG_TRANS_MST B WHERE " +
					  			  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.transporter_cd=B.transporter_cd)";
					//System.out.println("Query For Fetching Transporter Name & Abbriviation = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_transporter_nm.add(rset.getString(1));
						temp_transporter_abbr.add(rset.getString(2));
						tmp_transporter_nm += rset.getString(1)+"~~";
						tmp_transporter_abbr += rset.getString(2)+"~~";
					}
					else
					{
						temp_transporter_nm.add(" ");
						temp_transporter_abbr.add(" ");
						tmp_transporter_nm += " "+"~~";
						tmp_transporter_abbr += " "+"~~";
					}
				}
				
				daily_JT_Transporter_Cd.add(tmp_transporter_cd);
				daily_JT_Gcv.add(tmp_gcv);
				daily_JT_Ncv.add(tmp_ncv);
				daily_JT_Transporter_Nm.add(tmp_transporter_nm);
				daily_JT_Transporter_Abbr.add(tmp_transporter_abbr);
				
				for(int j=0; j<temp_transporter_cd.size(); j++)
				{
					queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
					  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
					  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
					  			  "A.transporter_cd="+temp_transporter_cd.elementAt(j)+"";
					//System.out.println("Query For Fetching Transporter QTY Value(s) In MMBTU(s) Details = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_transporter_qty.add(nf.format(Double.parseDouble(rset.getString(1))));
						tmp_transporter_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					}
					else
					{
						temp_transporter_qty.add(nf.format(Double.parseDouble("0")));
						tmp_transporter_qty += nf.format(Double.parseDouble("0"))+"~~";
					}
				}
				
				daily_JT_Transporter_Qty_Mmbtu.add(tmp_transporter_qty);
				
				double seller_qty_mmbtu = 0;
				
				queryString = "SELECT NVL(A.qty_mmbtu,'0') " +
				  			  "FROM FMS7_DAILY_SELLER_NOM_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  			  "A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_DAILY_SELLER_NOM_DTL B WHERE " +
				  			  "B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "B.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "B.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  			  "A.fgsa_no=B.fgsa_no AND A.sn_no=B.sn_no AND " +
				  			  "A.contract_type=B.contract_type AND A.transporter_cd=B.transporter_cd)";
				//System.out.println("Query For Fetching QTY Value(s) In MMBTU(s) From Seller Nomination Details = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					seller_qty_mmbtu += Double.parseDouble(rset.getString(1));
				}
				
				daily_JT_Seller_Nom_Qty_Mmbtu.add(nf.format(seller_qty_mmbtu));
				
				
				queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0'),NVL(SUM(A.qty_scm),'0') " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+"";
				//System.out.println("Query For Fetching QTY Value(s) In MMBTU(s) & SCM(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble(rset.getString(1))));
					daily_JT_Qty_Scm.add(nf.format(Double.parseDouble(rset.getString(2))));
				}
				else
				{
					daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble("0")));
					daily_JT_Qty_Scm.add(nf.format(Double.parseDouble("0")));
				}
				
				queryString = "SELECT NVL(A.plant_name,' ') " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no)";
				//System.out.println("Query For Fetching Plant Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Plant_Nm.add(rset.getString(1));
				}
				else
				{
					daily_JT_Plant_Nm.add(" ");
				}
				
				Vector cont_cd = new Vector();
				Vector cont_nm = new Vector();
				Vector cont_desg = new Vector();
				int cont_count = 0;
				
				queryString2 = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' ') " +
							  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
							  "WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND A.def_jt_flag='Y' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
							  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				//System.out.println("Customer Contact Person Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				while(rset2.next())
				{
					cont_cd.add(rset2.getString(1));
					cont_nm.add(rset2.getString(2).trim());
					cont_desg.add(rset2.getString(3).trim());
					++cont_count;
				}
				
				if(cont_count==0)
				{
					cont_cd.add("0");
					cont_nm.add("");
					cont_desg.add("");
				}
				
				Customer_Contact_Cd.add(cont_cd);
				Customer_Contact_Nm.add(cont_nm);
				Customer_Contact_Desg.add(cont_desg);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyJointTicketList() ...
		
	
	public void fetchDailyJointTicketList_old()
	{	methodName = "fetchDailyJointTicketList()";
		try 
		{
			//Following Data Are Related To SN ...
			queryString = "SELECT A.customer_cd,A.plant_seq_no " +
						  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
						  "GROUP BY A.customer_cd,A.plant_seq_no ORDER BY A.customer_cd,A.plant_seq_no";
			
			/*queryString = "SELECT A.customer_cd,A.plant_seq_no,contract_type FROM DLNG_DAILY_ALLOCATION_DTL A "
					+ "WHERE A.gas_dt=TO_DATE('01/02/2015','DD/MM/YYYY') "
					+ "GROUP BY A.customer_cd,A.plant_seq_no, contract_type "
					+ "ORDER BY A.customer_cd,A.plant_seq_no";
			*/
			//System.out.println("Query For Fetching Unique SN & Plant From Daily Allocation Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				daily_JT_Customer_Cd.add(rset.getString(1));
				daily_JT_Plant_Seq_No.add(rset.getString(2));
			}
			
			for(int i=0; i<daily_JT_Customer_Cd.size(); i++)
			{
				Vector temp_fgsa_no = new Vector();
				Vector temp_fgsa_rev_no = new Vector();
				Vector temp_sn_no = new Vector();
				Vector temp_sn_ref_no = new Vector();
				Vector temp_sn_rev_no = new Vector();
				Vector temp_contract_type = new Vector();
				Vector temp_sn_qty = new Vector();
				String tmp_fgsa_no = "";
				String tmp_fgsa_rev_no = "";
				String tmp_sn_no = "";
				String tmp_sn_ref_no = "";
				String tmp_sn_rev_no = "";
				String tmp_contract_type = "";
				String tmp_sn_qty = "";
				String tmp_sn_offspec_qty="";//JHP
				String tmp_sn_pffspec_flag="";//JHP
				int sn_count = 0;
								
				Vector temp_transporter_cd = new Vector();
				Vector temp_transporter_nm = new Vector();
				Vector temp_transporter_abbr = new Vector();
				Vector temp_transporter_qty = new Vector();
				Vector temp_gcv = new Vector();
				Vector temp_ncv = new Vector();
				String tmp_transporter_cd = "";
				String tmp_transporter_nm = "";
				String tmp_transporter_abbr = "";
				String tmp_transporter_qty = "";
				String tmp_gcv = "";
				String tmp_ncv = "";
				int trans_count = 0;
				
				queryString = "SELECT NVL(A.customer_name,' '),NVL(A.customer_abbr,' ') " +
	  			  			  "FROM FMS7_CUSTOMER_MST A WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
	  			  			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B WHERE " +
	  			  			  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.customer_cd=B.customer_cd)";
				//System.out.println("Query For Fetching Customer Name & Abbriviation = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Customer_Nm.add(rset.getString(1));
					daily_JT_Customer_Abbr.add(rset.getString(2));
				}
				else
				{
					daily_JT_Customer_Nm.add(" ");
					daily_JT_Customer_Abbr.add(" ");
				}
				
				queryString = "SELECT DISTINCT fgsa_no,sn_no,contract_type " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" " +
				  			  "ORDER BY fgsa_no,sn_no,contract_type";
				//System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_fgsa_no.add(rset.getString(1));
					temp_sn_no.add(rset.getString(2));
					temp_contract_type.add(rset.getString(3));
					tmp_fgsa_no = rset.getString(1);
					tmp_sn_no = rset.getString(2);
					tmp_contract_type = rset.getString(3);
					
					++sn_count;
					tmp_fgsa_rev_no = "0";
					tmp_sn_rev_no = "0";
					temp_fgsa_rev_no.add("0");
					temp_sn_rev_no.add("0");
					
					daily_JT_Fgsa_No.add(tmp_fgsa_no);
					daily_JT_Fgsa_Rev_No.add(tmp_fgsa_rev_no);
					daily_JT_Sn_No.add(tmp_sn_no);
					daily_JT_Sn_Rev_No.add(tmp_sn_rev_no);
					daily_JT_Contract_Type.add(tmp_contract_type);
				}
				
				if(sn_count==0)
				{
					temp_fgsa_no.add("0");
					temp_sn_no.add("0");
					temp_contract_type.add(" ");
					tmp_fgsa_no += "0";
					tmp_sn_no += "0";
					tmp_contract_type += " ";
					tmp_fgsa_rev_no = "0";
					tmp_sn_rev_no = "0";
					temp_fgsa_rev_no.add("0");
					temp_sn_rev_no.add("0");
					
					daily_JT_Fgsa_No.add(tmp_fgsa_no);
					daily_JT_Fgsa_Rev_No.add(tmp_fgsa_rev_no);
					daily_JT_Sn_No.add(tmp_sn_no);
					daily_JT_Sn_Rev_No.add(tmp_sn_rev_no);
					daily_JT_Contract_Type.add(tmp_contract_type);
				}
				
				
				
				
				
				for(int j=0; j<temp_sn_no.size(); j++)
				{
					queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
					  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
					  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
					  			  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
					  			  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
					  			  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
					  			  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
					  			  "A.contract_type='"+temp_contract_type.elementAt(j)+"'";
					//System.out.println("Query For Fetching SN Wise QTY Value(s) In MMBTU(s) Details = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_sn_qty.add(nf.format(Double.parseDouble(rset.getString(1))));
						tmp_sn_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					}
					else
					{
						temp_sn_qty.add(nf.format(Double.parseDouble("0")));
						tmp_sn_qty += "0"+"~~";
					}
					queryString = "SELECT NVL(sum(A.qty_offspec),'0') " +
		  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
		  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
		  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
		  			  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
		  			  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
		  			  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
		  			  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
		  			  "A.contract_type='"+temp_contract_type.elementAt(j)+"' and A.offspec_flag='Y'";
				//System.out.println("Query For Fetching OFFSpec QTY Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tmp_sn_offspec_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					
				}
				else
				{
					
					tmp_sn_offspec_qty += " "+"~~";
					
				}
				queryString = "SELECT NVL(sum(A.qty_offspec),'0') " +
				  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
				  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
				  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
				  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
				  "A.contract_type='"+temp_contract_type.elementAt(j)+"' and A.offspec_flag='N'";
				//System.out.println("Query For Fetching OFFSpec QTY Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				tmp_sn_pffspec_flag += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
				
				}
				else
				{
				
				tmp_sn_pffspec_flag += " "+"~~";
				
				}
					if((""+temp_contract_type.elementAt(j)).trim().equalsIgnoreCase("S"))
					{
						queryString = "SELECT sn_ref_no " +
									  "FROM DLNG_SN_MST WHERE " +
						  			  "fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
						  			  "sn_no="+temp_sn_no.elementAt(j)+" AND " +
						  			  "sn_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" " +
						  			  "ORDER BY sn_rev_no DESC";
						//System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							tmp_sn_ref_no += (rset.getString(1)==null?" ":rset.getString(1))+"~~";
						}
						else
						{
							tmp_sn_ref_no += " "+"~~";
						}
					}
					else if((""+temp_contract_type.elementAt(j)).trim().equalsIgnoreCase("L"))
					{
						queryString = "SELECT loa_ref_no " +
									  "FROM FMS7_LOA_MST WHERE " +
						  			  "tender_no="+temp_fgsa_no.elementAt(j)+" AND " +
						  			  "loa_no="+temp_sn_no.elementAt(j)+" AND " +
						  			  "loa_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" " +
						  			  "ORDER BY loa_rev_no DESC";
						//System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							tmp_sn_ref_no += (rset.getString(1)==null?" ":rset.getString(1))+"~~";
						}
						else
						{
							tmp_sn_ref_no += " "+"~~";
						}
					}
					else
					{
						tmp_sn_ref_no += " "+"~~";
					}
				}
				daily_JT_offspec_qty.add(tmp_sn_offspec_qty); //JHP
				daily_JT_offspec_flag.add(tmp_sn_pffspec_flag);//JHP
				daily_JT_SN_Qty_Mmbtu.add(tmp_sn_qty);
				daily_JT_SN_Ref_No.add(tmp_sn_ref_no);
				
				String arr_type[]=(""+daily_JT_Contract_Type.elementAt(i)).split("~~");
						
				for(int k=0;k<arr_type.length;k++) {
				queryString = "SELECT DISTINCT(A.transporter_cd),NVL(A.gcv,'0'),NVL(A.ncv,'0') " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" "
				  			  + " AND A.CONTRACT_TYPE='"+arr_type[k]+"' " //RG
				  			  + "ORDER BY A.transporter_cd";
				//System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_transporter_cd.add(rset.getString(1));
					temp_gcv.add(nf.format(Double.parseDouble(rset.getString(2))));
					temp_ncv.add(nf.format(Double.parseDouble(rset.getString(3))));
					tmp_transporter_cd += rset.getString(1)+"~~";
					tmp_gcv += nf.format(Double.parseDouble(rset.getString(2)))+"~~";
					tmp_ncv += nf.format(Double.parseDouble(rset.getString(3)))+"~~";
					++trans_count;
				}
				
				if(trans_count==0)
				{
					temp_transporter_cd.add("0");
					temp_gcv.add(nf.format(Double.parseDouble("0")));
					temp_ncv.add(nf.format(Double.parseDouble("0")));
					tmp_transporter_cd += "0"+"~~";
					tmp_gcv += nf.format(Double.parseDouble("0"))+"~~";
					tmp_ncv += nf.format(Double.parseDouble("0"))+"~~";
				}
				}
				for(int j=0; j<temp_transporter_cd.size(); j++)
				{
					queryString = "SELECT NVL(A.transporter_name,' '),NVL(A.transporter_abbr,' ') " +
					  			  "FROM DLNG_TRANS_MST A WHERE A.transporter_cd="+temp_transporter_cd.elementAt(j)+" AND " +
					  			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM DLNG_TRANS_MST B WHERE " +
					  			  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.transporter_cd=B.transporter_cd)";
					//System.out.println("Query For Fetching Transporter Name & Abbriviation = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_transporter_nm.add(rset.getString(1));
						temp_transporter_abbr.add(rset.getString(2));
						tmp_transporter_nm += rset.getString(1)+"~~";
						tmp_transporter_abbr += rset.getString(2)+"~~";
					}
					else
					{
						temp_transporter_nm.add(" ");
						temp_transporter_abbr.add(" ");
						tmp_transporter_nm += " "+"~~";
						tmp_transporter_abbr += " "+"~~";
					}
				}
				
				daily_JT_Transporter_Cd.add(tmp_transporter_cd);
				daily_JT_Gcv.add(tmp_gcv);
				daily_JT_Ncv.add(tmp_ncv);
				daily_JT_Transporter_Nm.add(tmp_transporter_nm);
				daily_JT_Transporter_Abbr.add(tmp_transporter_abbr);
				
				for(int j=0; j<temp_transporter_cd.size(); j++)
				{
					queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
					  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
					  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
					  			  "A.transporter_cd="+temp_transporter_cd.elementAt(j)+" "
					  			  + " AND A.CONTRACT_TYPE='"+daily_JT_Contract_Type.elementAt(i)+"' "; //RG
					//System.out.println("Query For Fetching Transporter QTY Value(s) In MMBTU(s) Details = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_transporter_qty.add(nf.format(Double.parseDouble(rset.getString(1))));
						tmp_transporter_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					}
					else
					{
						temp_transporter_qty.add(nf.format(Double.parseDouble("0")));
						tmp_transporter_qty += nf.format(Double.parseDouble("0"))+"~~";
					}
				}
				
				daily_JT_Transporter_Qty_Mmbtu.add(tmp_transporter_qty);
				
				double seller_qty_mmbtu = 0;
				
				queryString = "SELECT NVL(A.qty_mmbtu,'0') " +
				  			  "FROM FMS7_DAILY_SELLER_NOM_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND "
				  			  + "A.CONTRACT_TYPE='"+daily_JT_Contract_Type.elementAt(i)+"' AND " + //RG
				  			  "A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_DAILY_SELLER_NOM_DTL B WHERE " +
				  			  "B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "B.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "B.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  			  "A.fgsa_no=B.fgsa_no AND A.sn_no=B.sn_no AND " +
				  			  "A.contract_type=B.contract_type AND A.transporter_cd=B.transporter_cd)";
				//System.out.println("Query For Fetching QTY Value(s) In MMBTU(s) From Seller Nomination Details = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					seller_qty_mmbtu += Double.parseDouble(rset.getString(1));
				}
				
				daily_JT_Seller_Nom_Qty_Mmbtu.add(nf.format(seller_qty_mmbtu));
				
				
				queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0'),NVL(SUM(A.qty_scm),'0') " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" "
				  			  + "AND A.CONTRACT_TYPE='"+daily_JT_Contract_Type.elementAt(i)+"' "; //RG
				//System.out.println("Query For Fetching QTY Value(s) In MMBTU(s) & SCM(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble(rset.getString(1))));
					daily_JT_Qty_Scm.add(nf.format(Double.parseDouble(rset.getString(2))));
				}
				else
				{
					daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble("0")));
					daily_JT_Qty_Scm.add(nf.format(Double.parseDouble("0")));
				}
				
				queryString = "SELECT NVL(A.plant_name,' ') " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no)";
				//System.out.println("Query For Fetching Plant Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Plant_Nm.add(rset.getString(1));
				}
				else
				{
					daily_JT_Plant_Nm.add(" ");
				}
				
				Vector cont_cd = new Vector();
				Vector cont_nm = new Vector();
				Vector cont_desg = new Vector();
				int cont_count = 0;
				
				queryString2 = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' ') " +
							  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
							  "WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND A.def_jt_flag='Y' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
							  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				//System.out.println("Customer Contact Person Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				while(rset2.next())
				{
					cont_cd.add(rset2.getString(1));
					cont_nm.add(rset2.getString(2).trim());
					cont_desg.add(rset2.getString(3).trim());
					++cont_count;
				}
				
				if(cont_count==0)
				{
					cont_cd.add("0");
					cont_nm.add("");
					cont_desg.add("");
				}
				
				Customer_Contact_Cd.add(cont_cd);
				Customer_Contact_Nm.add(cont_nm);
				Customer_Contact_Desg.add(cont_desg);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyJointTicketList() ...
		

	
//	Introduce BY MD20111105
	public void getREGASperLC()
	{
		

		try
		{
			queryString = "SELECT SN_REV_NO, SN_NO,CONT_TYPE, " +
						"TO_CHAR(SN_START_DATE,'DD/MM/YYYY'), TO_CHAR(SN_END_DATE,'DD/MM/YYYY'), TCQ, DCQ, " +
						"FINANCIAL_YEAR " +
						" FROM FMS7_LC_DTL " +
						" WHERE CUSTOMER_CD = '"+lc_customer_cd+"'" +
						" AND LC_SEQ_NO = '"+lc_seq_no+"' and financial_year = '"+lc_fin_yr+"'";
			//System.out.println("FMS7_LC_DTL Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				//System.out.println("got data");
				LC_REV_NO.add(rset.getString(1)==null?"":rset.getString(1));
				LC_REGAS_NO.add(rset.getString(2)==null?"":rset.getString(2));
				LC_REGAS_CONT_TYPE.add(rset.getString(3)==null?"":rset.getString(3));
				LC_REGAS_START_DT.add(rset.getString(4)==null?"":rset.getString(4));
				LC_REGAS_END_DT.add(rset.getString(5)==null?"":rset.getString(5));
				LC_REGAS_TCQ.add(rset.getString(6)==null?"":rset.getString(6));
				LC_REGAS_DCQ.add(rset.getString(7)==null?"":rset.getString(7));
				LC_REGAS_FINANCIAL_YR.add(rset.getString(8)==null?"":rset.getString(8));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}



//MD20120123 end
	
	//Introduce BY MD20111018
	public void getSNLOAperLC()
	{
		try
		{
			queryString = "SELECT FGSA_NO, SN_NO,CONT_TYPE, " +
						"TO_CHAR(SN_START_DATE,'DD/MM/YYYY'), TO_CHAR(SN_END_DATE,'DD/MM/YYYY'), TCQ, DCQ, " +
						"FINANCIAL_YEAR " +
						" FROM FMS7_LC_DTL " +
						" WHERE CUSTOMER_CD = '"+lc_customer_cd+"'" +
						" AND LC_SEQ_NO = '"+lc_seq_no+"'";
			//System.out.println("FMS7_LC_DTL Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				//System.out.println("got data");
				LC_FGSA_NO.add(rset.getString(1)==null?"":rset.getString(1));
				LC_SN_NO.add(rset.getString(2)==null?"":rset.getString(2));
				LC_CONT_TYPE.add(rset.getString(3)==null?"":rset.getString(3));
				LC_SN_START_DT.add(rset.getString(4)==null?"":rset.getString(4));
				LC_SN_END_DT.add(rset.getString(5)==null?"":rset.getString(5));
				LC_TCQ.add(rset.getString(6)==null?"":rset.getString(6));
				LC_DCQ.add(rset.getString(7)==null?"":rset.getString(7));
				LC_FINANCIAL_YR.add(rset.getString(8)==null?"":rset.getString(8));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void Customer_DTL()
	{
		try
		{
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
			//System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"":rset.getString(1));	
				CUST_NM.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	//Following Function Is Introduced By Achal On 20th January, 2010 ...
	public void fetchDailyBuyerNomReportCustomer() 
	{
		methodName = "fetchDailyBuyerNomReportCustomer()";
		try 
		{
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd.add(rset.getString(1));
				master_Transporter_Abbr.add(rset.getString(2));
			}
			////System.out.println("master_Transporter_Cd.size() = "+master_Transporter_Cd.size());
			Vector mapping_id=new Vector();//ADDED FOR LTCORA AND CN
			
			for(int i=0;i<master_Transporter_Cd.size();i++)
			{
				int trans_count = 0;
				queryString1 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
				   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),gcv,ncv,NVL(A.fgsa_no,'0')," +
				   "NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0')," +
				   "NVL(A.contract_type,'S'),TO_CHAR(A.gas_dt,'DD/MM/YYYY'), " +
				   "MAPPING_ID " + //ADDED FOR LTCORA AND CN
				   "FROM FMS7_DAILY_BUYER_NOM_DTL A WHERE A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
	   			   "AND A.customer_cd='"+customer_cd+"' AND " +
	   			   "A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY') " +
	   			   "ORDER BY A.gas_dt,A.customer_cd,A.fgsa_no,A.sn_no,A.plant_seq_no,A.nom_rev_no ";							
				//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					++trans_count;
					daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
					GEN_DT.add(rset1.getString(1));
					NOM_REV_NO.add(rset1.getString(2));
					gcv = rset1.getString(7);
					ncv = rset1.getString(8);
					daily_Buyer_Gen_Day_Time.add(rset1.getString(3)==null?"":rset1.getString(3));
					daily_Buyer_Nom_Qty_Mmbtu.add(rset1.getString(5)=="0"?"":nf.format(Double.parseDouble(rset1.getString(5))));
					daily_Buyer_Nom_Qty_Scm.add(rset1.getString(6)=="0"?"":nf.format(Double.parseDouble(rset1.getString(6))));
					daily_Buyer_Nom_Plant_Cd.add(rset1.getString(4));	
					daily_Buyer_Nom_Fgsa_No.add(rset1.getString(9));
					daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(10));
					daily_Buyer_Nom_Sn_No.add(rset1.getString(11));
					daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(12));
					daily_Buyer_Nom_Cd.add(rset1.getString(13));
					daily_Buyer_Nom_Type.add(rset1.getString(14).trim());
					if(rset1.getString(14).trim().equalsIgnoreCase("S"))
					{
						daily_Buyer_Nom_Contract_Type.add("SN");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("L"))
					{
						daily_Buyer_Nom_Contract_Type.add("LOA");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("R"))
					{
						daily_Buyer_Nom_Contract_Type.add("RE");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("T"))//ADDED FOR LTCORA AND CN
					{
						daily_Buyer_Nom_Contract_Type.add("LTCORA");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("C"))//ADDED FOR LTCORA AND CN
					{
						daily_Buyer_Nom_Contract_Type.add("CN");
					}
					GAS_DT.add(rset1.getString(15));
					mapping_id.add(rset1.getString(16)==null?"":rset1.getString(16));//ADDED FOR LTCORA AND CN
				}
				
				master_Transporter_Count.add(""+trans_count);
				
				//System.out.println("daily_Transporter_Nom_Cd = "+trans_count);
			}
			
			
			for(int i=0;i<daily_Buyer_Nom_Plant_Cd.size();i++)
			{
				//Logic for the Daily Contracted Quantity
				if((""+daily_Buyer_Nom_Type.elementAt(i)).trim().equals("S"))
				{
					queryString1 = "SELECT A.sn_ref_no " +
					   "FROM DLNG_SN_MST A WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
					   "AND A.fgsa_no='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
					   "AND A.sn_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
					   "AND A.sn_ref_no is not null ORDER BY A.sn_rev_no desc";
					//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String sn_ref_number = rset1.getString(1)==null?"":rset1.getString(1);
						if(!sn_ref_number.trim().equals(""))
						{
							sn_ref_no.add(sn_ref_number);
						}
						else
						{
							sn_ref_no.add("");
						}
					}
					else
					{
						sn_ref_no.add("");
					}
					
					
					String dcq="0.00";
					queryString1 = "SELECT NVL(A.dcq,'0') " +
					   "FROM DLNG_SN_MST A WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
					   "AND A.fgsa_no='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
					   "AND A.fgsa_rev_no='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
					   "AND A.sn_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
					   "AND A.sn_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' ";
					//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{			
						dcq = rset1.getString(1)==null?"0.00":rset1.getString(1);
					}
					
					queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
			   			   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
			   			   "AND A.fgsa_no='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
			   			   "AND A.fgsa_rev_no='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
			   			   "AND A.sn_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
			   			   "AND A.sn_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' " +
			   			   "AND A.from_dt<=TO_DATE('"+GAS_DT.elementAt(i)+"','DD/MM/YYYY') " +
			   			   "AND A.to_dt>=TO_DATE('"+GAS_DT.elementAt(i)+"','DD/MM/YYYY')";
					//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
					rset4 = stmt4.executeQuery(queryString4);
					if(rset4.next())
					{
					//SB20110915	if(Double.parseDouble(rset4.getString(1))>0)
						if(Double.parseDouble(rset4.getString(1))>=0)
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));						
						}
						else
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(dcq)));					
						}
					}
					else
					{
						daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(dcq)));					
					}
				}
				else if((""+daily_Buyer_Nom_Type.elementAt(i)).trim().equals("L"))
				{
					queryString1 = "SELECT A.LOA_ref_no " +
					   "FROM FMS7_LOA_MST A WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
					   "AND A.TENDER_NO='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
					   "AND A.LOA_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
					   "AND A.LOA_ref_no is not null ORDER BY A.LOA_rev_no desc";
					//System.out.println("Transporter, Customer, Tender,LOA Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String sn_ref_number = rset1.getString(1)==null?"":rset1.getString(1);
						if(!sn_ref_number.trim().equals(""))
						{
							sn_ref_no.add(sn_ref_number);
						}
						else
						{
							sn_ref_no.add("");
						}
					}
					else
					{
						sn_ref_no.add("");
					}
					
					String dcq="0.00";
					queryString1 = "SELECT NVL(A.dcq,'0.00') " +
					   "FROM FMS7_LOA_MST A WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
					   "AND A.TENDER_NO='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
					   "AND A.LOA_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
					   "AND A.LOA_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"'";
					//System.out.println("LOA Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						dcq = rset1.getString(1)==null?"0.00":rset1.getString(1);
					}
					
					
					queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
		   			   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
		   			   "AND A.TENDER_NO='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +			   			  
		   			   "AND A.loa_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
		   			   "AND A.loa_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' " +
		   			   "AND A.from_dt<=TO_DATE('"+GAS_DT.elementAt(i)+"','DD/MM/YYYY') " +
		   			   "AND A.to_dt>=TO_DATE('"+GAS_DT.elementAt(i)+"','DD/MM/YYYY')";
					//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
					rset4 = stmt4.executeQuery(queryString4);
					if(rset4.next())
					{
						//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
						if(Double.parseDouble(rset4.getString(1))>0)
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));						
						}
						else
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(dcq)));					
						}
					}
					else
					{
						daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(dcq)));					
					}
				}
				else if((""+daily_Buyer_Nom_Type.elementAt(i)).trim().equals("R"))
				{
					queryString1 = "SELECT NVL(A.DCQ_QTY,'0') " +
					   "FROM FMS7_RE_GAS_CARGO_DTL A WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
					   "AND A.RE_GAS_NO='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
					   "AND A.CARGO_SEQ_NO ='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
					   "AND A.RE_GAS_REV_NO ='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"'";
					//System.out.println("LOA Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
					
					}
					else
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add("");
					}
				}
				else if((""+daily_Buyer_Nom_Type.elementAt(i)).trim().equals("T")) //ADDED FOR LTCORA AND CN
				{
					queryString1 = "SELECT NVL(A.DCQ_QTY,'0') " +
					   "FROM FMS8_LNG_REGAS_CARGO_DTL A WHERE A.MAPPING_ID='"+mapping_id.elementAt(i)+"' " +
					   "AND A.FLAG='T' " +
					   "AND A.CARGO_SEQ_NO ='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' ";
					//System.out.println("LTCORA Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
					
					}
					else
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add("");
					}
				}
				else if((""+daily_Buyer_Nom_Type.elementAt(i)).trim().equals("C")) //ADDED FOR LTCORA AND CN
				{
					queryString1 = "SELECT NVL(A.DCQ_QTY,'0') " +
					   "FROM FMS8_LNG_REGAS_CARGO_DTL A WHERE A.MAPPING_ID='"+mapping_id.elementAt(i)+"' " +
					   "AND A.FLAG='C' " +
					   "AND A.CARGO_SEQ_NO ='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' ";
					//System.out.println("LTCORA Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
					
					}
					else
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add("");
					}
				}
				else
				{
					sn_ref_no.add("");
					daily_Buyer_Nom_Dcq.add("");
				}
				
					
				
				
				queryString1 = "SELECT A.QTY_MMBTU,A.QTY_SCM FROM DLNG_DAILY_ALLOCATION_DTL A " +
				   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
				   "AND A.fgsa_no='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
				   //"AND A.fgsa_rev_no='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
				   "AND A.sn_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
				   //"AND A.sn_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' " +
				   "AND TRANSPORTER_CD='"+daily_Transporter_Nom_Cd.elementAt(i)+"' " +
				   "AND PLANT_SEQ_NO='"+daily_Buyer_Nom_Plant_Cd.elementAt(i)+"' " +
				   "AND GAS_DT=TO_DATE('"+GAS_DT.elementAt(i)+"','DD/MM/YYYY')  " +
				   "AND CONTRACT_TYPE='"+daily_Buyer_Nom_Type.elementAt(i)+"'";
				if(daily_Buyer_Nom_Type.elementAt(i).toString().equalsIgnoreCase("T") || daily_Buyer_Nom_Type.elementAt(i).toString().equalsIgnoreCase("C"))
				{
					queryString1+=" AND MAPPING_ID='"+mapping_id.elementAt(i)+"'";
				}
				
				//System.out.println("A.QTY_MMBTU,A.QTY_SCM FROM DLNG_DAILY_ALLOCATION_DTL Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					daily_Alloc_Nom_Qty_Mmbtu.add(rset1.getString(1)==null?"0.00":nf.format(Double.parseDouble(rset1.getString(1))));
					daily_Alloc_Nom_Qty_Scm.add(rset1.getString(2)==null?"0.00":nf.format(Double.parseDouble(rset1.getString(2))));
				}
				else
				{
					daily_Alloc_Nom_Qty_Mmbtu.add("0.00");
					daily_Alloc_Nom_Qty_Scm.add("0.00");
				}
				
				// Logic for the Plant Name
				queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
				   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
				   "AND A.seq_no="+daily_Buyer_Nom_Plant_Cd.elementAt(i)+" " +
				   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
				   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
				   "AND B.eff_dt<=TO_DATE('"+GAS_DT.elementAt(i)+"','DD/MM/YYYY'))";
				//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
				}
				else
				{
					daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
				}
				
				queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
				   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"'";
				//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Abbr.add(rset3.getString(1));
				}
				else
				{
					daily_Buyer_Nom_Abbr.add(" ");
				}
			}		
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyBuyerNomReportCustomer() ...
	
	//MD20120123 start
	public void fetchDailySellerNomReport() 
	{
		
		methodName = "fetchDailySellerNomReport()";
		try 
		{
			Vector contract = new Vector(); //MD20120111
			Vector mapping_id=new Vector();//ADDED FOR LTCORA AND CN
			
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd_Seller_Nom.add(rset.getString(1));
				master_Transporter_Abbr_Seller_Nom.add(rset.getString(2));
			}
			
			for(int i=0;i<master_Transporter_Cd_Seller_Nom.size();i++)
			{
				int trans_count = 0;
				queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
				"NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),A.GCV,A.NCV,NVL(A.fgsa_no,'0')," +
				"NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.contract_type,'S') " +
				",MAPPING_ID " +//ADDED FOR LTCORA AND CN
				"FROM FMS7_DAILY_SELLER_NOM_DTL A " +
	   			"WHERE A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
	   			"AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') ORDER BY A.customer_cd,A.plant_seq_no,A.nom_rev_no";								
				//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
				rset6 = stmt6.executeQuery(queryString6);
				while(rset6.next())
				{					
					++trans_count;
					daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
					GEN_DT.add(rset6.getString(1));
					NOM_REV_NO.add(rset6.getString(2));
					gcv = rset6.getString(7);
					ncv = rset6.getString(8);
					daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
					daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
					daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
					daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
					daily_Seller_Nom_Fgsa_No.add(rset6.getString(9));
					daily_Seller_Nom_Fgsa_Rev_No.add(rset6.getString(10));
					daily_Seller_Nom_Sn_No.add(rset6.getString(11));
					daily_Seller_Nom_Sn_Rev_No.add(rset6.getString(12));
					daily_Seller_Nom_Cd.add(rset6.getString(13));
					daily_Seller_Nom_Type.add(rset6.getString(14).trim());
					if(rset6.getString(14).trim().equalsIgnoreCase("S"))
					{
						daily_Seller_Nom_Contract_Type.add("SN");
					}
					else if(rset6.getString(14).trim().equalsIgnoreCase("L"))
					{
						daily_Seller_Nom_Contract_Type.add("LOA");
					}
					else if(rset6.getString(14).trim().equalsIgnoreCase("R"))
					{
						daily_Seller_Nom_Contract_Type.add("RE");
					}
					else if(rset6.getString(14).trim().equalsIgnoreCase("T"))//ADDED FOR LTCORA AND CN
					{
						daily_Seller_Nom_Contract_Type.add("LTCORA");
					}
					else if(rset6.getString(14).trim().equalsIgnoreCase("C"))//ADDED FOR LTCORA AND CN
					{
						daily_Seller_Nom_Contract_Type.add("CN");
					}
					
					contract.add(rset6.getString(14)==null?"":rset6.getString(14)); //MD20120111
					mapping_id.add(rset6.getString(15)==null?"":rset6.getString(15)); //ADDED FOR LTCORA AND CN
					
				}
				master_Transporter_Count_Seller_Nom.add(""+trans_count);				
				//daily_Transporter_Obligation_Qty.add(""); //Remaining For Logic Development ...				
			}
			//Logic for the DCQ,QTY_MMBTU,QTY_SCM
			for(int i=0;i<daily_Seller_Nom_Plant_Seq_No.size();i++)
			{
				if(contract.elementAt(i).toString().trim().equalsIgnoreCase("S")) //MD20120111
				{
				
					//Following Code Is For SN Based Buyers ...
					queryString1 = "SELECT NVL(A.dcq,'0'),A.sn_ref_no FROM DLNG_SN_MST A " +
					"WHERE A.fgsa_no='"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
					"AND A.fgsa_rev_no='"+daily_Seller_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
					"AND A.sn_no='"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
					"AND A.sn_rev_no='"+daily_Seller_Nom_Sn_Rev_No.elementAt(i)+"' " +
					"AND A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
					"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
					//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
				
						String sn_ref_number = rset1.getString(2)==null?"":rset1.getString(2);
						if(!sn_ref_number.trim().equals(""))
						{
							sn_ref_no.add(sn_ref_number);
						}
						else
						{
							sn_ref_no.add("");
						}
						queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
						"WHERE A.fgsa_no='"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
						"AND A.fgsa_rev_no='"+daily_Seller_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
						"AND A.sn_no='"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
						"AND A.sn_rev_no='"+daily_Seller_Nom_Sn_Rev_No.elementAt(i)+"' " +
						"AND A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
			   			"AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
			   			"AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
						//System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
						rset5 = stmt5.executeQuery(queryString5);
						if(rset5.next())
						{
						//SB20110915	if(Double.parseDouble(rset5.getString(1))>0)
							if(Double.parseDouble(rset5.getString(1))>=0)
							{
								daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));										
							}
							else
							{
								daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));										
							}
						}
						else
						{
							daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));									
						}
					}
					else
					{
						sn_ref_no.add("");
						daily_Seller_Nom_Dcq.add("");
					}
				}
						
//				MD20120111 Start
				else if(contract.elementAt(i).toString().trim().equalsIgnoreCase("L"))
				{
					queryString1 = "SELECT NVL(A.DCQ,'0'),A.LOA_REF_NO " +
					   "FROM FMS7_LOA_MST A WHERE A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
					   "AND A.TENDER_NO='"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
					   "AND A.LOA_NO='"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
					   "AND A.LOA_REV_NO='"+daily_Seller_Nom_Sn_Rev_No.elementAt(i)+"' ";
					////System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String sn_ref_number = rset1.getString(2)==null?"":rset1.getString(2);
						if(!sn_ref_number.trim().equals(""))
						{
							sn_ref_no.add(sn_ref_number);
						}
						else
						{
							sn_ref_no.add("");
						}
						queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
			   			   "WHERE A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
			   			   "AND A.TENDER_NO='"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
			   			   "AND A.LOA_NO='"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
			   			   "AND A.LOA_REV_NO='"+daily_Seller_Nom_Sn_Rev_No.elementAt(i)+"' " +
			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
						////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
						rset4 = stmt4.executeQuery(queryString4);
						if(rset4.next())
						{
							//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
							if(Double.parseDouble(rset4.getString(1))>=0)
							{
								daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));						
							}
							else
							{
								daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
							}
						}
						else
						{
							daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
						}
					}
					else
					{
						sn_ref_no.add("");
						daily_Seller_Nom_Dcq.add("");
					}
				}
				else if(contract.elementAt(i).toString().trim().equalsIgnoreCase("R"))
				{
						//mmm				
					queryString1 = "SELECT NVL(A.dcq_qty,'0') FROM FMS7_RE_GAS_CARGO_DTL A " +
							" WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							" AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							" AND A.re_gas_no= '"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
							//" AND A.re_gas_rev_no = '"+daily_Seller_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
							" AND A.customer_cd = '"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
							" AND A.cargo_seq_no  = '"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
							" ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
							
					//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					boolean flg = false;
					while(rset1.next())
					{
						sn_ref_no.add("");
						daily_Seller_Nom_Dcq.add(rset1.getString(1)==null?"-":rset1.getString(1));
						flg = true;
					}
					if(!flg)
					{
						sn_ref_no.add("");
						daily_Seller_Nom_Dcq.add("");
					}
				}
//				MD20120111 End
				//ADDED FOR LTCORA AND CN
				else if(contract.elementAt(i).toString().trim().equalsIgnoreCase("T"))//ADDED FOR LTCORA AND CN
				{
						//mmm				
					queryString1 = "SELECT NVL(A.dcq_qty,'0') FROM FMS8_LNG_REGAS_CARGO_DTL A " +
							" WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							" AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							" AND A.MAPPING_ID= '"+mapping_id.elementAt(i)+"' " +
							//" AND A.re_gas_rev_no = '"+daily_Seller_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
							" AND A.FLAG = 'T' " +
							" AND A.cargo_seq_no  = '"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
							" ORDER BY A.MAPPING_ID,A.cargo_seq_no";
							
					//System.out.println("Transporter, Customer, LTCORA, & Cargo Contract Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					boolean flg = false;
					while(rset1.next())
					{
						sn_ref_no.add("");
						daily_Seller_Nom_Dcq.add(rset1.getString(1)==null?"-":rset1.getString(1));
						flg = true;
					}
					if(!flg)
					{
						sn_ref_no.add("");
						daily_Seller_Nom_Dcq.add("");
					}
				}
				else if(contract.elementAt(i).toString().trim().equalsIgnoreCase("C"))//ADDED FOR LTCORA AND CN
				{
						//mmm				
					queryString1 = "SELECT NVL(A.dcq_qty,'0') FROM FMS8_LNG_REGAS_CARGO_DTL A " +
							" WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							" AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							" AND A.MAPPING_ID= '"+mapping_id.elementAt(i)+"' " +
							//" AND A.re_gas_rev_no = '"+daily_Seller_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
							" AND A.FLAG = 'C' " +
							" AND A.cargo_seq_no  = '"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
							" ORDER BY A.MAPPING_ID,A.cargo_seq_no";
							
					//System.out.println("Transporter, Customer, CN, & Cargo Contract Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					boolean flg = false;
					while(rset1.next())
					{
						sn_ref_no.add("");
						daily_Seller_Nom_Dcq.add(rset1.getString(1)==null?"-":rset1.getString(1));
						flg = true;
					}
					if(!flg)
					{
						sn_ref_no.add("");
						daily_Seller_Nom_Dcq.add("");
					}
				}
				
				
				queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
							   "AND A.seq_no='"+daily_Seller_Nom_Plant_Seq_No.elementAt(i)+"' " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
				}
				else
				{
					daily_Seller_Nom_Plant_Abbr.add("PLANT1");
				}
				
				queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
	   			   "WHERE A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"'";
				//System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
				rset5 = stmt5.executeQuery(queryString5);
				if(rset5.next())
				{
					daily_Seller_Nom_Abbr.add(rset5.getString(1));
				}
				else
				{
					daily_Seller_Nom_Abbr.add(" ");
				}
				
				queryString4 = "SELECT NVL(A.nom_rev_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.PLANT_SEQ_NO,'0') " +
				   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
				   			   "WHERE A.fgsa_no='"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
				   			   "AND A.sn_no='"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
				   			   "AND A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
				   			   "AND A.transporter_cd="+daily_Transporter_Nom_Cd_Seller_Nom.elementAt(i)+" " +
				   			   "AND A.contract_type='"+daily_Seller_Nom_Type.elementAt(i)+"' " +
				   			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   			   "AND A.plant_seq_no='"+daily_Seller_Nom_Plant_Seq_No.elementAt(i)+"' ";							
				//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString4);
				rset4 = stmt4.executeQuery(queryString4);
				if(rset4.next())
				{
					daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+")");
					daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(2)=="0"?"":nf.format(Double.parseDouble(rset4.getString(2))));
					daily_Buyer_Nom_Qty_Scm.add(rset4.getString(3)=="0"?"":nf.format(Double.parseDouble(rset4.getString(3))));
					//daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
				}
				else
				{
					daily_Buyer_Gen_Day_With_Rev_No.add("( )");
					daily_Buyer_Nom_Qty_Mmbtu.add("");
					daily_Buyer_Nom_Qty_Scm.add("");
					//daily_Buyer_Nom_Plant_Seq_No.add("");
				}
				daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	

	}
	//MD20120123 end
	//Following Function Only Shows Buyer Nominated Records Under Daily Seller Nomination Report ...
	//Following Function Has Been Introduced By Achal On 17th May, 2010 ...
	public void fetchDailySellerNomReportOLD() 
	{
		methodName = "fetchDailySellerNomReport()";
		try 
		{
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd_Seller_Nom.add(rset.getString(1));
				master_Transporter_Abbr_Seller_Nom.add(rset.getString(2));
			}
			
			for(int i=0;i<master_Transporter_Cd_Seller_Nom.size();i++)
			{
				int trans_count = 0;
				queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
				"NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),A.GCV,A.NCV,NVL(A.fgsa_no,'0')," +
				"NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.contract_type,'S') " +
				"FROM FMS7_DAILY_SELLER_NOM_DTL A " +
	   			"WHERE A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
	   			"AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') ORDER BY A.customer_cd,A.plant_seq_no,A.nom_rev_no";								
				//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
				rset6 = stmt6.executeQuery(queryString6);
				while(rset6.next())
				{					
					++trans_count;
					daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
					GEN_DT.add(rset6.getString(1));
					NOM_REV_NO.add(rset6.getString(2));
					gcv = rset6.getString(7);
					ncv = rset6.getString(8);
					daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
					daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
					daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
					daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
					daily_Seller_Nom_Fgsa_No.add(rset6.getString(9));
					daily_Seller_Nom_Fgsa_Rev_No.add(rset6.getString(10));
					daily_Seller_Nom_Sn_No.add(rset6.getString(11));
					daily_Seller_Nom_Sn_Rev_No.add(rset6.getString(12));
					daily_Seller_Nom_Cd.add(rset6.getString(13));
					daily_Seller_Nom_Type.add(rset6.getString(14).trim());
					if(rset6.getString(14).trim().equalsIgnoreCase("S"))
					{
						daily_Seller_Nom_Contract_Type.add("SN");
					}
					else if(rset6.getString(14).trim().equalsIgnoreCase("L"))
					{
						daily_Seller_Nom_Contract_Type.add("LOA");
					}
					else 
					{
						daily_Seller_Nom_Contract_Type.add("RE");
					}
				}
				master_Transporter_Count_Seller_Nom.add(""+trans_count);				
				//daily_Transporter_Obligation_Qty.add(""); //Remaining For Logic Development ...				
			}
			//Logic for the DCQ,QTY_MMBTU,QTY_SCM
			for(int i=0;i<daily_Seller_Nom_Plant_Seq_No.size();i++)
			{
				//Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.dcq,'0'),A.sn_ref_no FROM DLNG_SN_MST A " +
				"WHERE A.fgsa_no='"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
				"AND A.fgsa_rev_no='"+daily_Seller_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
				"AND A.sn_no='"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
				"AND A.sn_rev_no='"+daily_Seller_Nom_Sn_Rev_No.elementAt(i)+"' " +
				"AND A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String sn_ref_number = rset1.getString(2)==null?"":rset1.getString(2);
					if(!sn_ref_number.trim().equals(""))
					{
						sn_ref_no.add(sn_ref_number);
					}
					else
					{
						sn_ref_no.add("");
					}
					queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
					"WHERE A.fgsa_no='"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
					"AND A.fgsa_rev_no='"+daily_Seller_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
					"AND A.sn_no='"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
					"AND A.sn_rev_no='"+daily_Seller_Nom_Sn_Rev_No.elementAt(i)+"' " +
					"AND A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
		   			"AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
		   			"AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					//System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
					rset5 = stmt5.executeQuery(queryString5);
					if(rset5.next())
					{
					//SB20110915	if(Double.parseDouble(rset5.getString(1))>0)
						if(Double.parseDouble(rset5.getString(1))>=0)
						{
							daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));										
						}
						else
						{
							daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));										
						}
					}
					else
					{
						daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));									
					}
				}
				else
				{
					sn_ref_no.add("");
					daily_Seller_Nom_Dcq.add("");
				}
					
				queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
							   "AND A.seq_no='"+daily_Seller_Nom_Plant_Seq_No.elementAt(i)+"' " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
				}
				else
				{
					daily_Seller_Nom_Plant_Abbr.add("PLANT1");
				}
				
				queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
	   			   "WHERE A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"'";
				//System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
				rset5 = stmt5.executeQuery(queryString5);
				if(rset5.next())
				{
					daily_Seller_Nom_Abbr.add(rset5.getString(1));
				}
				else
				{
					daily_Seller_Nom_Abbr.add(" ");
				}
				
				queryString4 = "SELECT NVL(A.nom_rev_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.PLANT_SEQ_NO,'0') " +
				   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
				   			   "WHERE A.fgsa_no='"+daily_Seller_Nom_Fgsa_No.elementAt(i)+"' " +
				   			   "AND A.sn_no='"+daily_Seller_Nom_Sn_No.elementAt(i)+"' " +
				   			   "AND A.customer_cd='"+daily_Seller_Nom_Cd.elementAt(i)+"' " +
				   			   "AND A.transporter_cd="+daily_Transporter_Nom_Cd_Seller_Nom.elementAt(i)+" " +
				   			   "AND A.contract_type='"+daily_Seller_Nom_Type.elementAt(i)+"' " +
				   			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   			   "AND A.plant_seq_no='"+daily_Seller_Nom_Plant_Seq_No.elementAt(i)+"' ";							
				//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString4);
				rset4 = stmt4.executeQuery(queryString4);
				if(rset4.next())
				{
					daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+")");
					daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(2)=="0"?"":nf.format(Double.parseDouble(rset4.getString(2))));
					daily_Buyer_Nom_Qty_Scm.add(rset4.getString(3)=="0"?"":nf.format(Double.parseDouble(rset4.getString(3))));
					//daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
				}
				else
				{
					daily_Buyer_Gen_Day_With_Rev_No.add("( )");
					daily_Buyer_Nom_Qty_Mmbtu.add("");
					daily_Buyer_Nom_Qty_Scm.add("");
					//daily_Buyer_Nom_Plant_Seq_No.add("");
				}
				daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailySellerNomReport() ...
	
	//MD20120123 start
	public void fetchDailyBuyerNomReport() 
	{
		methodName = "fetchDailyBuyerNomReport()";
		try 
		{			
			Vector contract = new Vector(); //MD20120111
			Vector mapping_id=new Vector();//ADDED FOR LTCORA AND CN
			
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd.add(rset.getString(1));
				master_Transporter_Abbr.add(rset.getString(2));
			}
			
			for(int i=0;i<master_Transporter_Cd.size();i++)
			{
				int trans_count = 0;
				queryString1 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
				   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),gcv,ncv,NVL(A.fgsa_no,'0')," +
				   "NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.contract_type,'S') " +
				   ",MAPPING_ID " +//ADDED FOR LTCORA AND CN
				   "FROM FMS7_DAILY_BUYER_NOM_DTL A WHERE A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
	   			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
	   			   "ORDER BY A.customer_cd,A.plant_seq_no,A.nom_rev_no ";							
				////System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					++trans_count;
					daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
					GEN_DT.add(rset1.getString(1));
					NOM_REV_NO.add(rset1.getString(2));
					gcv = rset1.getString(7);
					ncv = rset1.getString(8);
					daily_Buyer_Gen_Day_Time.add(rset1.getString(3)==null?"":rset1.getString(3));
					daily_Buyer_Nom_Qty_Mmbtu.add(rset1.getString(5)=="0"?"":rset1.getString(5));
					daily_Buyer_Nom_Qty_Scm.add(rset1.getString(6)=="0"?"":rset1.getString(6));
					daily_Buyer_Nom_Plant_Cd.add(rset1.getString(4));	
					daily_Buyer_Nom_Fgsa_No.add(rset1.getString(9));
					daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(10));
					daily_Buyer_Nom_Sn_No.add(rset1.getString(11));
					daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(12));
					daily_Buyer_Nom_Cd.add(rset1.getString(13));
					daily_Buyer_Nom_Type.add(rset1.getString(14).trim());
					if(rset1.getString(14).trim().equalsIgnoreCase("S"))
					{
						daily_Buyer_Nom_Contract_Type.add("SN");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("L"))
					{
						daily_Buyer_Nom_Contract_Type.add("LOA");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("R"))
					{
						daily_Buyer_Nom_Contract_Type.add("RE");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("T"))
					{
						daily_Buyer_Nom_Contract_Type.add("LTCORA");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("C"))
					{
						daily_Buyer_Nom_Contract_Type.add("CN");
					}
					contract.add(rset1.getString(14).trim());//MD20120111
					mapping_id.add(rset1.getString(15)==null?"":rset1.getString(15));//ADDED FOR LTCORA AND CN
				}
				master_Transporter_Count.add(""+trans_count);
				
			}
			
			
			for(int i=0;i<daily_Buyer_Nom_Plant_Cd.size();i++)
			{
				
				if(contract.elementAt(i).toString().trim().equalsIgnoreCase("S"))//MD20120111
				{
					queryString1 = "SELECT NVL(A.dcq,'0'),A.sn_ref_no " +
					   "FROM DLNG_SN_MST A WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
					   "AND A.fgsa_no='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
					   "AND A.fgsa_rev_no='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
					   "AND A.sn_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
					   "AND A.sn_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' ";
					//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String sn_ref_number = rset1.getString(2)==null?"":rset1.getString(2);
						if(!sn_ref_number.trim().equals(""))
						{
							sn_ref_no.add(sn_ref_number);
						}
						else
						{
							sn_ref_no.add("");
						}
						//kkk
						queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
			   			   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
			   			   "AND A.fgsa_no='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
			   			   "AND A.fgsa_rev_no='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
			   			   "AND A.sn_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
			   			   "AND A.sn_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' " +
			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
						////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
						rset4 = stmt4.executeQuery(queryString4);
						if(rset4.next())
						{
							//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
							if(Double.parseDouble(rset4.getString(1))>=0)
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));						
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
							}
						}
						else
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
						}
					}
					else
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add("");
					}
				}
//				MD20120111 Start
				else if(contract.elementAt(i).toString().trim().equalsIgnoreCase("L"))
				{
					queryString1 = "SELECT NVL(A.DCQ,'0'),A.LOA_REF_NO " +
					   "FROM FMS7_LOA_MST A WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
					   "AND A.TENDER_NO='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
					   "AND A.LOA_NO='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
					   "AND A.LOA_REV_NO='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' ";
					////System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String sn_ref_number = rset1.getString(2)==null?"":rset1.getString(2);
						if(!sn_ref_number.trim().equals(""))
						{
							sn_ref_no.add(sn_ref_number);
						}
						else
						{
							sn_ref_no.add("");
						}
						queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
			   			   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
			   			   "AND A.TENDER_NO='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
			   			   "AND A.LOA_NO='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
			   			   "AND A.LOA_REV_NO='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' " +
			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
						////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
						rset4 = stmt4.executeQuery(queryString4);
						if(rset4.next())
						{
							//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
							if(Double.parseDouble(rset4.getString(1))>=0)
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));						
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
							}
						}
						else
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
						}
					}
					else
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add("");
					}
				}
				else if(contract.elementAt(i).toString().trim().equalsIgnoreCase("R"))
				{
										
					queryString1 = "SELECT NVL(A.dcq_qty,'0') FROM FMS7_RE_GAS_CARGO_DTL A" +
							" WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')" +
							" AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')" +
							" AND A.re_gas_no = '"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' "+
							//" AND A.re_gas_rev_no = '"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' "+
							" AND A.CARGO_SEQ_NO = '"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' "+
							" AND A.customer_cd = '"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
							" ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
							
					////System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					boolean flg = false;
					while(rset1.next())
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add(rset1.getString(1)==null?"-":rset1.getString(1));
						flg = true;
					}
					if(!flg)
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add("");
					}
				}
//				MD20120111 End
				else if(contract.elementAt(i).toString().trim().equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
				{
										
					queryString1 = "SELECT NVL(A.dcq_qty,'0') FROM FMS8_LNG_REGAS_CARGO_DTL A" +
							" WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')" +
							" AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')" +
							" AND A.MAPPING_ID = '"+mapping_id.elementAt(i)+"' "+
							//" AND A.re_gas_rev_no = '"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' "+
							" AND A.CARGO_SEQ_NO = '"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' "+
							" AND A.FLAG = 'T' " +
							" ORDER BY A.MAPPING_ID,A.cargo_seq_no";
							
					//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					boolean flg = false;
					while(rset1.next())
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add(rset1.getString(1)==null?"-":rset1.getString(1));
						flg = true;
					}
					if(!flg)
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add("");
					}
				}
				else if(contract.elementAt(i).toString().trim().equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
										
					queryString1 = "SELECT NVL(A.dcq_qty,'0') FROM FMS8_LNG_REGAS_CARGO_DTL A" +
							" WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY')" +
							" AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')" +
							" AND A.MAPPING_ID = '"+mapping_id.elementAt(i)+"' "+
							//" AND A.re_gas_rev_no = '"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' "+
							" AND A.CARGO_SEQ_NO = '"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' "+
							" AND A.FLAG = 'C' " +
							" ORDER BY A.MAPPING_ID,A.cargo_seq_no";
							
					//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					boolean flg = false;
					while(rset1.next())
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add(rset1.getString(1)==null?"-":rset1.getString(1));
						flg = true;
					}
					if(!flg)
					{
						sn_ref_no.add("");
						daily_Buyer_Nom_Dcq.add("");
					}
				}
				//LTCORA AND CN END
				queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
				   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
				   "AND A.seq_no="+daily_Buyer_Nom_Plant_Cd.elementAt(i)+" " +
				   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
				   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
				   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				////System.out.println("Customer Plant Name Fetch Query = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
				}
				else
				{
					daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
				}
				
				queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
				   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"'";
				////System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Abbr.add(rset3.getString(1));
				}
				else
				{
					daily_Buyer_Nom_Abbr.add(" ");
				}
			}
			
			//System.out.println(contract);
			//System.out.println(daily_Buyer_Nom_Fgsa_No.size());
			//System.out.println(daily_Buyer_Nom_Fgsa_Rev_No);
			//System.out.println(daily_Buyer_Nom_Sn_No);
			//System.out.println(daily_Buyer_Nom_Sn_Rev_No);
			//System.out.println(daily_Buyer_Nom_Cd);
			//System.out.println(daily_Buyer_Nom_Dcq);
			
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyBuyerNomReport() ...
	
	//MD20120123 end
	
	//	Following Function Is Introduced By Achal On 14th January, 2010 ...
	public void fetchDailyBuyerNomReportOLD() 
	{
		methodName = "fetchDailyBuyerNomReport()";
		try 
		{			
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd.add(rset.getString(1));
				master_Transporter_Abbr.add(rset.getString(2));
			}
			
			for(int i=0;i<master_Transporter_Cd.size();i++)
			{
				int trans_count = 0;
				queryString1 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
				   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),gcv,ncv,NVL(A.fgsa_no,'0')," +
				   "NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.contract_type,'S') " +
				   "FROM FMS7_DAILY_BUYER_NOM_DTL A WHERE A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
	   			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
	   			   "ORDER BY A.customer_cd,A.plant_seq_no,A.nom_rev_no ";							
				//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString5);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					++trans_count;
					daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
					GEN_DT.add(rset1.getString(1));
					NOM_REV_NO.add(rset1.getString(2));
					gcv = rset1.getString(7);
					ncv = rset1.getString(8);
					daily_Buyer_Gen_Day_Time.add(rset1.getString(3)==null?"":rset1.getString(3));
					daily_Buyer_Nom_Qty_Mmbtu.add(rset1.getString(5)=="0"?"":rset1.getString(5));
					daily_Buyer_Nom_Qty_Scm.add(rset1.getString(6)=="0"?"":rset1.getString(6));
					daily_Buyer_Nom_Plant_Cd.add(rset1.getString(4));	
					daily_Buyer_Nom_Fgsa_No.add(rset1.getString(9));
					daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(10));
					daily_Buyer_Nom_Sn_No.add(rset1.getString(11));
					daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(12));
					daily_Buyer_Nom_Cd.add(rset1.getString(13));
					daily_Buyer_Nom_Type.add(rset1.getString(14).trim());
					if(rset1.getString(14).trim().equalsIgnoreCase("S"))
					{
						daily_Buyer_Nom_Contract_Type.add("SN");
					}
					else if(rset1.getString(14).trim().equalsIgnoreCase("L"))
					{
						daily_Buyer_Nom_Contract_Type.add("LOA");
					}
					else 
					{
						daily_Buyer_Nom_Contract_Type.add("RE");
					}
				}
				master_Transporter_Count.add(""+trans_count);
			}
			
			
			for(int i=0;i<daily_Buyer_Nom_Plant_Cd.size();i++)
			{
				queryString1 = "SELECT NVL(A.dcq,'0'),A.sn_ref_no " +
				   "FROM DLNG_SN_MST A WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
				   "AND A.fgsa_no='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
				   "AND A.fgsa_rev_no='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
				   "AND A.sn_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
				   "AND A.sn_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' ";
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String sn_ref_number = rset1.getString(2)==null?"":rset1.getString(2);
					if(!sn_ref_number.trim().equals(""))
					{
						sn_ref_no.add(sn_ref_number);
					}
					else
					{
						sn_ref_no.add("");
					}
					queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
		   			   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
		   			   "AND A.fgsa_no='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' " +
		   			   "AND A.fgsa_rev_no='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' " +
		   			   "AND A.sn_no='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' " +
		   			   "AND A.sn_rev_no='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' " +
		   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
		   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
					rset4 = stmt4.executeQuery(queryString4);
					if(rset4.next())
					{
						//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
						if(Double.parseDouble(rset4.getString(1))>=0)
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));						
						}
						else
						{
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
						}
					}
					else
					{
						daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(1))));					
					}
				}
				else
				{
					sn_ref_no.add("");
					daily_Buyer_Nom_Dcq.add("");
				}
				
				
				queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"' " +
							   "AND A.seq_no="+daily_Buyer_Nom_Plant_Cd.elementAt(i)+" " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
				}
				else
				{
					daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
				}
				
				queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
				   "WHERE A.customer_cd='"+daily_Buyer_Nom_Cd.elementAt(i)+"'";
				//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Buyer_Nom_Abbr.add(rset3.getString(1));
				}
				else
				{
					daily_Buyer_Nom_Abbr.add(" ");
				}
			}		
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyBuyerNomReport() ...
	
	
	
	//Following Function Is Introduced By Achal Pathak  For Weekly Buyer Nomination Details On 22nd December, 2010 ...
	public void fetchDailyBuyerNomDetailsWeeklyDtls()
	{
		methodName = "fetchDailyBuyerNomDetailsWeeklyDtls()";
		try 
		{
			////System.out.println("In DataBean from_dt ="+from_dt);
			for(int i=0;i<7;i++)
		  	{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+i+",'DAY') , TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+i+",'DD/MM/YYYY') FROM DUAL";
				//System.out.println("Week Days Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					WEEKDAYS.add(rset.getString(1));
					WEEKDATES.add(rset.getString(2));
				}
		  	}
			for(int i=0;i<WEEKDATES.size();i++)
			{
				
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
//	Following Function Is Introduced By Achal Pathak On 21st December, 2010 ...	
	public void fetchWeeklyBuyerNomDetails() 
	{
		methodName = "fetchWeeklyBuyerNomDetails()";
		try 
		{
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			double final_sum_mmbtu =0;
			double final_sum_scm =0;
			////System.out.println("week = "+week);
			if(week.trim().equalsIgnoreCase("current"))
			{
				queryString = "SELECT TO_CHAR(NEXT_DAY(TO_DATE('"+gen_date+"','DD/MM/YYYY')-7,'MONDAY'),'DD/MM/YYYY') , TO_CHAR(NEXT_DAY(TO_DATE('"+gen_date+"','DD/MM/YYYY')-7,'MONDAY')+6,'DD/MM/YYYY') FROM DUAL";
				//System.out.println("current Week Query = "+queryString);
			}
			else if(week.trim().equalsIgnoreCase("next"))
			{
				queryString = "SELECT TO_CHAR(NEXT_DAY(TO_DATE('"+gen_date+"','DD/MM/YYYY'),'MONDAY'),'DD/MM/YYYY'), TO_CHAR(NEXT_DAY(TO_DATE('"+gen_date+"','DD/MM/YYYY'),'MONDAY')+6,'DD/MM/YYYY')  FROM DUAL" ;
				//System.out.println("Next Week Query = "+queryString);
			}
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				from_dt=rset.getString(1);
				to_dt=rset.getString(2);
			}
			//System.out.println("from_dt = "+from_dt+", to_dt = "+to_dt);
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd.add(rset.getString(1));
				master_Transporter_Abbr.add(rset.getString(2));
			}
			
			for(int i=0;i<master_Transporter_Cd.size();i++)
			{
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
				double sum_mmbtu =0;
				double sum_scm =0;
				String main_query = "";
				String inner_query = "";
				
				for(int z=0; z<7; z++)
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(z<6)
						{
							main_query += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.START_DT AND A.END_DT) OR ";
							inner_query += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN B.START_DT AND B.END_DT) OR ";
						}
						else if(z==6)
						{
							main_query += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.START_DT AND A.END_DT)";
							inner_query += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN B.START_DT AND B.END_DT)";
						}
					}
				}
				//Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.sn_ref_no,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY') " +
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE " +
							   "("+main_query+") " +
							   "AND A.status='Y' " + 
							   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   "AND A.fgsa_rev_no=B.fgsa_rev_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND A.START_DT=B.START_DT AND A.END_DT=B.END_DT AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_SN_PLANT_MST A WHERE " +
									   "A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
									   "AND A.sn_rev_no="+rset1.getString(4)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							
							daily_Buyer_Nom_Mapping_Id.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5)); //Customer CD
							daily_Buyer_Nom_Type.add("S");
							daily_Buyer_Nom_Contract_Type.add("SN");
							START_DT.add(rset1.getString(11));
							END_DT.add(rset1.getString(12));
							
							
							///////////////////////RG
							daily_Buyer_regas_cargo_boe_no.add("");
							daily_Buyer_regas_cargo_boe_dt.add("");
							
							////////////////
							
							
							queryString = "SELECT distinct A.nom_rev_no,TO_CHAR(A.GEN_DT,'DD/MM/YYYY'),GEN_TIME  FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							  "WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='S' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Query For Fetching Revision NO For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								NOM_REV_NO.add(rset.getString(1));
								GEN_DT.add(rset.getString(2));
								daily_Buyer_Gen_Day_Time.add(rset.getString(3));
							}
							else
							{
								NOM_REV_NO.add("");
								GEN_DT.add("");
								daily_Buyer_Gen_Day_Time.add("");
							}
							
							queryString = "SELECT SUM(A.QTY_MMBTU) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='S' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("SUM of MMBTU For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_MMBTU.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_mmbtu+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_mmbtu+=0;
								}
								
							}
							else
							{
								QTY_MMBTU.add("");
								sum_mmbtu+=0;
							}
							
							queryString = "SELECT SUM(A.QTY_SCM) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='S' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("SUM of SCM For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_SCM.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_scm+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_scm+=0;
								}
							}
							else
							{
								QTY_MMBTU.add("");
								sum_scm+=0;
							}
																					
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}							
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";
							
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
							   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
							   			   "AND ( (A.from_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							   			   "OR (A.to_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								if(Double.parseDouble(rset4.getString(1))>0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq));							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +							   			  
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +							   			   
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +										   
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_WEEKLY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								//daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								//daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}							
							++trans_count;
						}						
					}
				}//End Of Code For SN Based Buyers ...				
				//Following Code Is For LOA Based Buyers ...
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.loa_ref_no,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY') " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE " +
							   "("+main_query+") " +
							   "AND A.status='Y' " +
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt=A.start_dt AND B.end_dt=A.end_dt AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				//System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
									   "ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							daily_Buyer_Nom_Mapping_Id.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add("0");
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("L");
							daily_Buyer_Nom_Contract_Type.add("LOA");
							START_DT.add(rset1.getString(11));
							END_DT.add(rset1.getString(12));
							
							daily_Buyer_regas_cargo_boe_no.add("");
							daily_Buyer_regas_cargo_boe_dt.add("");
							
							queryString = "SELECT distinct A.nom_rev_no,TO_CHAR(A.GEN_DT,'DD/MM/YYYY'),GEN_TIME  FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							  "WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='L' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Query For Fetching Revision NO For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								NOM_REV_NO.add(rset.getString(1));
								GEN_DT.add(rset.getString(2));
								daily_Buyer_Gen_Day_Time.add(rset.getString(3));
							}
							else
							{
								NOM_REV_NO.add("");
								GEN_DT.add("");
								daily_Buyer_Gen_Day_Time.add("");
							}
							
							queryString = "SELECT SUM(A.QTY_MMBTU) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='L' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("SUM of MMBTU For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_MMBTU.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_mmbtu+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_mmbtu+=0;
								}
							}
							else
							{
								QTY_MMBTU.add("");
								sum_mmbtu+=0;
							}
							
							queryString = "SELECT SUM(A.QTY_SCM) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='L' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("SUM of SCM For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_SCM.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_scm+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_scm+=0;
								}
							}
							else
							{
								QTY_MMBTU.add("");
								sum_scm+=0;
							}
							
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND " +
										  "CUSTOMER_CD="+rset1.getString(5)+" AND " +
										  "FGSA_NO="+rset1.getString(1)+" AND CONTRACT_TYPE='L'";											
							//System.out.println("LOA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);										
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
							
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}							
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
				   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
				   			   			   "AND A.loa_no="+rset1.getString(3)+" " +
				   			   			   "AND A.loa_rev_no="+rset1.getString(4)+" " +
				   			   			   "AND A.from_dt<=TO_DATE('"+to_dt+"','DD/MM/YYYY') AND A.to_dt>=TO_DATE('"+from_dt+"','DD/MM/YYYY')";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								if(Double.parseDouble(rset4.getString(1))>0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq));							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +							   			   
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +							   			   
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +										   
										   "AND B.fgsa_no="+rset1.getString(1)+" " +										   
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_WEEKLY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								//daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								//daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							++trans_count;
						}
					}
				}//End Of Code For LOA Based Buyers ..
				
				//Following Code Is For RE-GAS Based Buyers ...
				String main_query_regas = "";
				String inner_query_regas = "";				
				for(int z=0; z<7; z++)
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(z<6)
						{
							main_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt) OR ";
							inner_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN B.contract_start_dt AND B.contract_end_dt) OR ";
						}
						else if(z==6)
						{
							main_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt)";
							inner_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN B.contract_start_dt AND B.contract_end_dt)";
						}
					}
				}
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100')," +
							   "NVL(A.cargo_ref_no,'0'),TO_CHAR(A.contract_start_dt,'DD/MM/YYYY'),TO_CHAR(A.contract_end_dt,'DD/MM/YYYY'), " +
							   "NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
							   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE " +
							   "("+main_query_regas+") " +
							   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
							   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
							   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
							   "AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
							   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}							
							sn_ref_no.add("");
							daily_Buyer_Nom_Mapping_Id.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("R");
							daily_Buyer_Nom_Contract_Type.add("RE-GAS");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += Double.parseDouble(rset1.getString(6));
							START_DT.add(rset1.getString(10));
							END_DT.add(rset1.getString(11));
							
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
							
							//System.out.println("value of boe no and date=============="+daily_Buyer_regas_cargo_boe_dt);
							
							queryString = "SELECT distinct A.nom_rev_no,TO_CHAR(A.GEN_DT,'DD/MM/YYYY'),GEN_TIME FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							  "WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='R' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Query For Fetching Revision NO For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								NOM_REV_NO.add(rset.getString(1));
								GEN_DT.add(rset.getString(2));
								daily_Buyer_Gen_Day_Time.add(rset.getString(3));
							}
							else
							{
								NOM_REV_NO.add("");
								GEN_DT.add("");
								daily_Buyer_Gen_Day_Time.add("");
							}
							
							queryString = "SELECT SUM(A.QTY_MMBTU) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='R' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("SUM of MMBTU For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_MMBTU.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_mmbtu+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_mmbtu+=0;
								}
							}
							else
							{
								QTY_MMBTU.add("");
								sum_mmbtu+=0;
							}
							queryString = "SELECT SUM(A.QTY_SCM) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+rset1.getString(1)+" " +
							  "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='R' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("SUM of SCM For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_SCM.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_scm+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_scm+=0;
								}
							}
							else
							{
								QTY_SCM.add("");
								sum_scm+=0;
							}
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = rset1.getString(7);							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND " +
										  "FGSA_NO="+rset1.getString(1)+" AND CONTRACT_TYPE='R'";
							//System.out.println("RE-GAS Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);										
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}							
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_WEEKLY_BUYER_NOM_DTL Table (RE-GAS) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								//daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								//daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}							
							++trans_count;
						}
					}
				}//End Of Code For RE-GAS Based Buyers ...
				
				
				
				
				//Following Code Is For LTCORA Based buyers ... NB20141029
				 main_query_regas = "";
				 inner_query_regas = "";				
				for(int z=0; z<7; z++)
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(z<6)
						{
							main_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt) OR ";
							inner_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN B.contract_start_dt AND B.contract_end_dt) OR ";
						}
						else if(z==6)
						{
							main_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt)";
							inner_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN B.contract_start_dt AND B.contract_end_dt)";
						}
					}
				}
				queryString1 = "SELECT NVL(A.MAPPING_ID,'0'),NVL(A.MAPPING_ID,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
							   "NVL(A.MAPPING_ID,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100')," +
							   "NVL(A.cargo_ref_no,'0'),TO_CHAR(A.contract_start_dt,'DD/MM/YYYY'),TO_CHAR(A.contract_end_dt,'DD/MM/YYYY'), " +
							   "NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
							   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, FMS8_LNG_REGAS_TRANS_DTL C WHERE " +
							   "("+main_query_regas+") " +
							   "AND A.mapping_id=B.mapping_id AND A.mapping_id=C.mapping_id AND B.mapping_id=C.mapping_id " +
							   "AND B.CN_NO='0' AND B.CN_REV_NO='0'" +
							   "AND C.trans_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
							   "ORDER BY A.MAPPING_ID,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)==null?"":rset1.getString(1);
					String tempmap_id[]=map_id.split("-");
					
					String temp_regas_no=tempmap_id[1];
					String temp_regas_rev_no=tempmap_id[2];
					String temp_cust_cd=tempmap_id[0];
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_no,'1') FROM FMS8_LNG_REGAS_PLANT_DTL A " +
						   " WHERE A.mapping_id='"+rset1.getString(1)+"'" +
						   " ORDER BY A.plant_no";
						/*queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";*/
						//System.out.println("Customer Plant Sequence NO Fetch Query (LTCORA) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}							
							sn_ref_no.add("");
							daily_Buyer_Nom_Mapping_Id.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_No.add(temp_regas_no);
							daily_Buyer_Nom_Fgsa_Rev_No.add(temp_regas_rev_no);
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(temp_cust_cd);
							daily_Buyer_Nom_Type.add("T");
							daily_Buyer_Nom_Contract_Type.add("LTCORA");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += Double.parseDouble(rset1.getString(6));
							START_DT.add(rset1.getString(10));
							END_DT.add(rset1.getString(11));
							
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
							
							//System.out.println("value of boe no and date=============="+daily_Buyer_regas_cargo_boe_dt);
							
							queryString = "SELECT distinct A.nom_rev_no,TO_CHAR(A.GEN_DT,'DD/MM/YYYY'),GEN_TIME FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							  "WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+temp_regas_no+" " +
							  "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='T' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Query For Fetching Revision NO For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								NOM_REV_NO.add(rset.getString(1));
								GEN_DT.add(rset.getString(2));
								daily_Buyer_Gen_Day_Time.add(rset.getString(3));
							}
							else
							{
								NOM_REV_NO.add("");
								GEN_DT.add("");
								daily_Buyer_Gen_Day_Time.add("");
							}
							
							queryString = "SELECT SUM(A.QTY_MMBTU) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+temp_regas_no+" " +
							  "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='T' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("SUM of MMBTU For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_MMBTU.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_mmbtu+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_mmbtu+=0;
								}
							}
							else
							{
								QTY_MMBTU.add("");
								sum_mmbtu+=0;
							}
							queryString = "SELECT SUM(A.QTY_SCM) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+temp_regas_no+" " +
							  "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='T' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("SUM of SCM For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_SCM.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_scm+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_scm+=0;
								}
							}
							else
							{
								QTY_SCM.add("");
								sum_scm+=0;
							}
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = rset1.getString(7);							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+temp_cust_cd+" AND " +
										  "FGSA_NO="+temp_regas_no+" AND CONTRACT_TYPE='T'";
							//System.out.println("LTCORA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);										
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}							
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+temp_cust_cd+"";
							//System.out.println("Buyer Abbreviation Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+temp_regas_no+" " +
							   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='T' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+temp_regas_no+" " +
										   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='T' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_WEEKLY_BUYER_NOM_DTL Table (LTCORA) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								//daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								//daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}							
							++trans_count;
						}
					}
				}//End Of Code For LTCORA Based Buyers ...
				
				
				
//				Following Code Is For CN Based buyers ... NB20141029
				 main_query_regas = "";
				 inner_query_regas = "";				
				for(int z=0; z<7; z++)
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(z<6)
						{
							main_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt) OR ";
							inner_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN B.contract_start_dt AND B.contract_end_dt) OR ";
						}
						else if(z==6)
						{
							main_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt)";
							inner_query_regas += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN B.contract_start_dt AND B.contract_end_dt)";
						}
					}
				}
				queryString1 = "SELECT NVL(A.MAPPING_ID,'0'),NVL(A.MAPPING_ID,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
							   "NVL(A.MAPPING_ID,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100')," +
							   "NVL(A.cargo_ref_no,'0'),TO_CHAR(A.contract_start_dt,'DD/MM/YYYY'),TO_CHAR(A.contract_end_dt,'DD/MM/YYYY'), " +
							   "NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
							   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, FMS8_LNG_REGAS_TRANS_DTL C WHERE " +
							   "("+main_query_regas+") " +
							   "AND A.mapping_id=B.mapping_id AND A.mapping_id=C.mapping_id AND B.mapping_id=C.mapping_id " +
							   "AND B.CN_NO!='0' " +
							   "AND C.trans_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
							   "ORDER BY A.MAPPING_ID,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)==null?"":rset1.getString(1);
					String tempmap_id[]=map_id.split("-");
					
					String temp_regas_no=tempmap_id[3];
					String temp_regas_rev_no=tempmap_id[4];
					String temp_cust_cd=tempmap_id[0];
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_no,'1') FROM FMS8_LNG_REGAS_PLANT_DTL A " +
						   " WHERE A.mapping_id='"+rset1.getString(1)+"'" +
						   " ORDER BY A.plant_no";
						/*queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";*/
						//System.out.println("Customer Plant Sequence NO Fetch Query (LTCORA) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}							
							sn_ref_no.add("");
							daily_Buyer_Nom_Mapping_Id.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_No.add(temp_regas_no);
							daily_Buyer_Nom_Fgsa_Rev_No.add(temp_regas_rev_no);
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(temp_cust_cd);
							daily_Buyer_Nom_Type.add("C");
							daily_Buyer_Nom_Contract_Type.add("CN");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += Double.parseDouble(rset1.getString(6));
							START_DT.add(rset1.getString(10));
							END_DT.add(rset1.getString(11));
							
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
							
							//System.out.println("value of boe no and date=============="+daily_Buyer_regas_cargo_boe_dt);
							
							queryString = "SELECT distinct A.nom_rev_no,TO_CHAR(A.GEN_DT,'DD/MM/YYYY'),GEN_TIME FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							  "WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+temp_regas_no+" " +
							  "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='C' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )" +
							  " AND MAPPING_ID='"+rset1.getString(1)+"'";
							//System.out.println("Query For Fetching Revision NO For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								NOM_REV_NO.add(rset.getString(1));
								GEN_DT.add(rset.getString(2));
								daily_Buyer_Gen_Day_Time.add(rset.getString(3));
							}
							else
							{
								NOM_REV_NO.add("");
								GEN_DT.add("");
								daily_Buyer_Gen_Day_Time.add("");
							}
							
							queryString = "SELECT SUM(A.QTY_MMBTU) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+temp_regas_no+" " +
							  "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='C' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) ) " +
							  " AND MAPPING_ID='"+rset1.getString(1)+"'";
							//System.out.println("SUM of MMBTU For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_MMBTU.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_mmbtu+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_mmbtu+=0;
								}
							}
							else
							{
								QTY_MMBTU.add("");
								sum_mmbtu+=0;
							}
							queryString = "SELECT SUM(A.QTY_SCM) FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							"WHERE A.sn_no="+rset1.getString(3)+"  AND A.fgsa_no="+temp_regas_no+" " +
							  "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							  "AND A.plant_seq_no="+rset2.getString(1)+" AND A.contract_type='C' " +
							  "AND (A.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							  "AND A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.transporter_cd=B.transporter_cd " +
							  "AND A.plant_seq_no=B.plant_seq_no AND A.contract_type=B.contract_type AND A.nom_rev_no=A.nom_rev_no " +
							  "AND (B.gas_dt BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )" +
							  " AND MAPPING_ID='"+rset1.getString(1)+"'";
							//System.out.println("SUM of SCM For WEEKLY Buyer Nomination = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								QTY_SCM.add(rset.getString(1));
								if(rset.getString(1)!=null && !rset.getString(1).equals(""))
								{
									sum_scm+=Double.parseDouble(rset.getString(1));
								}
								else
								{
									sum_scm+=0;
								}
							}
							else
							{
								QTY_SCM.add("");
								sum_scm+=0;
							}
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = rset1.getString(7);							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+temp_cust_cd+" AND " +
										  "FGSA_NO="+temp_regas_no+" AND CONTRACT_TYPE='C' AND MAPPING_ID='"+rset1.getString(1)+"'";
							//System.out.println("LTCORA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);										
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}							
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+temp_cust_cd+"";
							//System.out.println("Buyer Abbreviation Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_WEEKLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+temp_regas_no+" " +
							   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='C' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND MAPPING_ID='"+rset1.getString(1)+"' AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_WEEKLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+temp_regas_no+" " +
										   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='C' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+" AND MAPPING_ID='"+rset1.getString(1)+"')";
							
							//System.out.println("Fetch Query FROM FMS7_WEEKLY_BUYER_NOM_DTL Table (CN) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								//daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								//daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}							
							++trans_count;
						}
					}
				}//End Of Code For CN Based Buyers ...
				
				
				
				
				master_Transporter_Count.add(""+trans_count);
				master_Transporter_Dcq.add(nf.format(total_dcq));
				if(sum_mmbtu>0)
				{
					SUM_MMBTU.add(nf.format(sum_mmbtu));
				}
				else
				{
					SUM_MMBTU.add("");
				}
				if(sum_scm>0)
				{
					SUM_SCM.add(nf.format(sum_scm));
				}
				else
				{
					SUM_SCM.add("");
				}
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm.add("");
				}
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
				final_sum_mmbtu+=sum_mmbtu;
				final_sum_scm+=sum_scm;
			}
			if(final_sum_mmbtu>0)
			{
				total_mmbtu1=nf.format(final_sum_mmbtu);
			}
			if(final_sum_scm>0)
			{
				total_scm1=nf.format(final_sum_scm);
			}
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
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchWeeklyBuyerNomDetails() ...
	
	
	
	
	
	public void clearVectorAndStringVariables()
	{
		//Sart Of Daily Buyer Nomination Data ...
		
		master_Transporter_Cd.clear();
		master_Transporter_Abbr.clear();
		master_Transporter_Count.clear();
		master_Transporter_Dcq.clear();
		daily_Buyer_Nom_Mapping_Id.clear(); //NB20141029 ADDED FOR LTCORA
		daily_Buyer_Nom_Fgsa_No.clear();
		daily_Buyer_Nom_Fgsa_Rev_No.clear();
		daily_Buyer_Nom_Sn_No.clear();
		daily_Buyer_Nom_Sn_Rev_No.clear();
		daily_Buyer_Nom_Cd.clear();
		daily_Buyer_Nom_Abbr.clear();
		daily_Buyer_Nom_Dcq.clear();
		daily_Buyer_Nom_Mdcq_Qty.clear();
		daily_Buyer_Nom_Balance_Qty.clear();
		Buyer_Nominated_Qty.clear(); //SB20110926
		Buyer_Contracted_Qty.clear();//SB20110926
		Buyer_Allocated_Qty.clear();//SB20110926
		daily_Buyer_Nom_Plant_Cd.clear();
		daily_Buyer_Nom_Plant_Abbr.clear();
		daily_Transporter_Nom_Cd.clear();
		daily_Transporter_Nom_Abbr.clear();
		daily_Total_Dcq = "";
		
		daily_Buyer_Gen_Day_With_Rev_No.clear();
		daily_Buyer_Gen_Day_Time.clear();
		daily_Buyer_Nom_Plant_Seq_No.clear();
		daily_Buyer_Nom_Qty_Mmbtu.clear();
		daily_Buyer_Nom_Qty_Scm.clear();
		master_Transporter_Mmbtu.clear();
		master_Transporter_Scm.clear();
		daily_Total_Mmbtu = "";
		daily_Total_Scm = "";
		
		daily_Buyer_Nom_Type.clear();
		daily_Buyer_Nom_Contract_Type.clear();
		//End Of Daily Buyer Nomination Data ...
		
		
		//Sart Of Daily Seller Nomination Data ...
		master_Transporter_Cd_Seller_Nom.clear();
		master_Transporter_Abbr_Seller_Nom.clear();
		master_Transporter_Count_Seller_Nom.clear();
		master_Transporter_Dcq_Seller_Nom.clear();
		daily_Seller_Nom_Fgsa_No.clear();
		daily_Seller_Nom_Mapping_Id.clear(); //NB20141029 ADDED FOR LTCORA
		daily_Seller_Nom_Fgsa_Rev_No.clear();
		daily_Seller_Nom_Sn_No.clear();
		daily_Seller_Nom_Sn_Rev_No.clear();
		daily_Seller_Nom_Cd.clear();
		daily_Seller_Nom_Abbr.clear();
		daily_Seller_Nom_Dcq.clear();
		daily_Seller_Nom_Plant_Cd.clear();
		daily_Seller_Nom_Plant_Abbr.clear();
		daily_Transporter_Nom_Cd_Seller_Nom.clear();
		daily_Transporter_Nom_Abbr_Seller_Nom.clear();
		daily_Total_Dcq_Seller_Nom = "";
		
		daily_Seller_Gen_Day_With_Rev_No.clear();
		daily_Seller_Gen_Day_Time.clear();
		daily_Seller_Nom_Plant_Seq_No.clear();
		daily_Seller_Nom_Qty_Mmbtu.clear();
		daily_Seller_Nom_Qty_Scm.clear();
		master_Transporter_Mmbtu_Seller_Nom.clear();
		master_Transporter_Scm_Seller_Nom.clear();
		daily_Total_Mmbtu_Seller_Nom = "";
		daily_Total_Scm_Seller_Nom = "";
		
		daily_Seller_Nom_Type.clear();
		daily_Seller_Nom_Contract_Type.clear();
		
		daily_Seller_Obligation_Qty.clear();
		daily_Seller_Asking_Rate_Qty.clear();
		daily_Transporter_Obligation_Qty.clear();
		daily_Transporter_Asking_Rate_Qty.clear();
		daily_Total_Obligation_Qty = "";
		daily_Total_Asking_Rate_Qty = "";
		//End Of Daily Seller Nomination Data ...
		
		
		//Sart Of Daily Meter Ticket Reading Data ...
		//For Transporter Meters ...
		daily_Meter_Reading_Transporter_Cd.clear();
		daily_Meter_Reading_Transporter_Abbr.clear();
		daily_Meter_Reading_Transporter_Seq_Cd.clear();
		//For Customer Meters ...
		daily_Meter_Reading_Customer_Cd.clear();
		daily_Meter_Reading_Customer_Abbr.clear();
		daily_Meter_Reading_Customer_Seq_Cd.clear();
		daily_Meter_Reading_Customer_Transporter_Cd.clear();
		daily_Meter_Reading_Customer_Transporter_Abbr.clear();
		//End Of Daily Meter Ticket Reading Data ... 
		
		
		//Start Of Daily Meter Reading Data For Transporter ...
		daily_Meter_Reading_Transporter_Mmbtu.clear();
		daily_Meter_Reading_Transporter_Scm.clear();
		daily_Meter_Reading_Transporter_Reconcil_Mmbtu.clear();
		daily_Meter_Reading_Transporter_Reconcil_Scm.clear();
		daily_Meter_Reading_Transporter_Calculated_Gcv.clear();
		daily_Meter_Reading_Transporter_Calculated_Ncv.clear();
		daily_Meter_Reading_Transporter_Define_Gcv.clear();
		daily_Meter_Reading_Transporter_Define_Ncv.clear();
		daily_Meter_Reading_Transporter_Mmbtu_Total = "";
		daily_Meter_Reading_Transporter_Scm_Total = "";
		daily_Meter_Reading_Transporter_Reconcil_Mmbtu_Total = "";
		daily_Meter_Reading_Transporter_Reconcil_Scm_Total = "";
		//End Of Daily Meter Reading Data For Transporter ...

		//Start Of Daily Meter Reading Data For Customer ...
		daily_Meter_Reading_Customer_Mmbtu.clear();
		daily_Meter_Reading_Customer_Scm.clear();
		daily_Meter_Reading_Customer_Reconcil_Mmbtu.clear();
		daily_Meter_Reading_Customer_Reconcil_Scm.clear();
		daily_Meter_Reading_Customer_Calculated_Gcv.clear();
		daily_Meter_Reading_Customer_Calculated_Ncv.clear();
		daily_Meter_Reading_Customer_Define_Gcv.clear();
		daily_Meter_Reading_Customer_Define_Ncv.clear();
		daily_Meter_Reading_Customer_Mmbtu_Total = "";
		daily_Meter_Reading_Customer_Scm_Total = "";
		daily_Meter_Reading_Customer_Reconcil_Mmbtu_Total = "";
		daily_Meter_Reading_Customer_Reconcil_Scm_Total = "";
		//End Of Daily Meter Reading Data For Customer ...
		
		//Start Of Daily Meter Reading Generation Day & Time Variables Initialization ...
		daily_Meter_Reading_Gen_Date = null;
		daily_Meter_Reading_Gen_Time = null;
		//End Of Daily Meter Reading Generation Day & Time Variables Initialization ...
		
		//Start Of Daily Buyer Allocation Variables Initialization ...
		master_Transporter_Cd_Buyer_Allocation.clear();
		master_Transporter_Abbr_Buyer_Allocation.clear();
		master_Transporter_Count_Buyer_Allocation.clear();
		master_Transporter_Dcq_Buyer_Allocation.clear();
		daily_Buyer_Allocation_Fgsa_No.clear();
		daily_Buyer_Allocation_Mapping_Id.clear(); //NB20141029
		daily_Buyer_Allocation_Fgsa_Rev_No.clear();
		daily_Buyer_Allocation_Sn_No.clear();
		daily_Buyer_Allocation_Sn_Rev_No.clear();
		daily_Buyer_Allocation_Cd.clear();
		daily_Buyer_Allocation_Abbr.clear();
		daily_Buyer_Allocation_Dcq.clear();
		daily_Buyer_Allocation_Plant_Cd.clear();
		daily_Buyer_Allocation_Plant_Abbr.clear();
		daily_Transporter_Nom_Cd_Buyer_Allocation.clear();
		daily_Transporter_Nom_Abbr_Buyer_Allocation.clear();
		daily_Total_Dcq_Buyer_Allocation = "";
		daily_Buyer_Allocation_Gen_Day_With_Rev_No.clear();
		daily_Buyer_Allocation_Gen_Day_Time.clear();
		daily_Buyer_Allocation_Plant_Seq_No.clear();
		daily_Buyer_Allocation_Qty_Mmbtu.clear();
		daily_Buyer_Allocation_Qty_Scm.clear();
		master_Transporter_Mmbtu_Buyer_Allocation.clear();
		master_Transporter_Scm_Buyer_Allocation.clear();
		daily_Total_Mmbtu_Buyer_Allocation = "";
		daily_Total_Scm_Buyer_Allocation = "";
		daily_Buyer_Allocation_Type.clear(); //This Vector Stores Whether Buyer Is SN Based Or LOA Based Allocation ...
		//End Of Daily Buyer Allocation Variables Initialization ...
		

		//Joint Ticket Vectors Initialization Starts Here ...
		daily_JT_Customer_Cd.clear();
		daily_JT_Customer_Nm.clear();
		daily_JT_Customer_Abbr.clear();
		daily_JT_Fgsa_No.clear();
		daily_JT_Fgsa_Rev_No.clear();
		daily_JT_Sn_No.clear();
		daily_JT_Sn_Rev_No.clear();
		daily_JT_Plant_Seq_No.clear();
		daily_JT_Plant_Nm.clear();
		daily_JT_Gcv.clear();
		daily_JT_Ncv.clear();
		daily_JT_Transporter_Cd.clear();
		daily_JT_Transporter_Nm.clear();
		daily_JT_Transporter_Abbr.clear();
		daily_JT_Qty_Mmbtu.clear();
		daily_JT_Qty_Scm.clear();
		daily_JT_Seller_Nom_Qty_Mmbtu.clear();
		//Joint Ticket Vectors Initialization Ends Here ...
	}
	
	Vector ADVANCE_COLLECTION_FLAG = new Vector();
	Vector SALE_PRICE = new Vector();
	
	public void fetchDailyBuyerNomDetails()
	{
		methodName = "fetchDailyBuyerNomDetails()";
		try 
		{
//JHP20111014
			String date_tomorrow="";
			rset = stmt.executeQuery("select to_char(to_date('"+gas_date+"','dd/mm/yyyy') - 1,'dd/mm/yyyy') date_tomorrow from dual");
		      if(rset.next()) {
		       date_tomorrow=rset.getString("date_tomorrow");
		      }
//JHP20111014
			
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			//System.out.println("MIlan >>>> gen date : "+gen_date);
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' '), TRANSPORTER_NAME FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd.add(rset.getString(1));
				master_Transporter_Abbr.add(rset.getString(2));
				VTransporter_Name.add(rset.getString(3)); //SB20181005
			}
			
			for(int i=0; i<master_Transporter_Cd.size(); i++)
			{
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
				
				//Following Code Is For SN Based Buyers ...
				
//				Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.sn_ref_no "+
							   " ,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),"
							   + "NVL(ADVANCE_COLLECTION_FLAG,'N'),RATE " +  //SB20110926
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' AND A.FCC_FLAG='Y' " + 
							   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   //"AND A.fgsa_rev_no=B.fgsa_rev_no " +
							   "AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM FMS7_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 2nd March, 2011 ...
									   "AND A.sn_no="+rset1.getString(3)+"";
									   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 2nd March, 2011 ...
									   //"ORDER BY A.plant_seq_no";
						//System.out.println("SAMIK --> Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							 
							String tax_Structure_Dtl="";
							String tax_struct_cd="";
							
							queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
										  "A.customer_cd="+rset1.getString(5)+" AND " +
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
							daily_tax_struct_dtl.add(tax_nm);
						
							queryString3 = "SELECT NVL(A.clause_cd,'0') FROM FMS7_SN_CLAUSE_MST A " +
										   "WHERE A.buyer_cd="+rset1.getString(5)+" AND A.clause_cd=5 " +
										   "AND A.fgsa_no="+rset1.getString(1)+" AND A.sn_no="+rset1.getString(3)+" " +
										   "AND A.sn_rev_no="+rset1.getString(4)+"";
							//System.out.println("SN Clause Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(rset3.getString(1).trim().equals("5"))
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("LC");
								}
								else
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
								}
							}
							else
							{
								daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
							}
							
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							daily_Buyer_Nom_Mapping_Id.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("S");
							daily_Buyer_Nom_Contract_Type.add("SN");
													
							daily_Buyer_regas_cargo_boe_dt.add("");
							daily_Buyer_regas_cargo_boe_no.add("");
							PRE_APPROVAL.add("Y");
							COMM_PRE_APPROVAL.add("Y");
							ADVANCE_COLLECTION_FLAG.add(rset1.getString(13));
							SALE_PRICE.add(rset1.getString(14));
							
							String ALLOCATED_QTY = "0";
							String ALLOCATED_QTY1="0";
							String MAX_ALLOCATED_DT = gas_date;  //SB20110924
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));
						
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='S' " +
							  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  "";				
						//	System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("BUY-NOM:QRY-BN1002AAAA:SELECT:DLNG_DAILY_ALLOCATION_DTL: "+queryString);
							rset = stmt.executeQuery(queryString);
											
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
										MAX_ALLOCATED_DT = rset.getString(2);
									}
								}
							}
							
//							JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='S' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  " and NOM_REV_NO=(SELECT MAX(NOM_REV_NO) FROM FMS7_DAILY_SELLER_NOM_DTL " +
							  "WHERE SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  " AND FGSA_NO="+rset1.getString(1)+"" +
							  " AND CONTRACT_TYPE='S' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy'))";
								
							//System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("SELLER-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
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
							
							
							//SB20110924: Introduced Nominated Quantity for calculation
							String NOMINATED_QTY = "0";
							Buyer_Contracted_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)));
							Buyer_Allocated_Qty.add(nf.format(Double.parseDouble(ALLOCATED_QTY))+" as on "+MAX_ALLOCATED_DT);
							
							String NOM_DT_NOT_ALLOCATED = gas_date;  //SB20110924
							double NOM_QTY = 0;
							String no_days="0";
							queryString3 = " SELECT  (TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') - TO_DATE('"+MAX_ALLOCATED_DT+"','DD/MM/YYYY')) FROM DUAL ";
							//System.out.println("BUY-NOM:QRY-BN1002AB*:SELECT:DUAL: "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								no_days = rset3.getString(1);
							}
							
							//for(int z=1; z<=Integer.parseInt(no_days); z++)
							//{
								//queryString3 = "SELECT TO_CHAR(TO_DATE('"+MAX_ALLOCATED_DT+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
								queryString3 = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
							//	//System.out.println("BUY-NOM:QRY-BN1002AB:SELECT:DUAL: "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									NOM_DT_NOT_ALLOCATED = rset3.getString(1);
								}
								queryString = "SELECT NVL(SUM(QTY_MMBTU),'0') " +
								  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
								  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
								  "AND FGSA_NO="+rset1.getString(1)+" " +
								  "AND CONTRACT_TYPE='S' AND GAS_DT=TO_DATE('"+NOM_DT_NOT_ALLOCATED+"','dd/mm/yyyy')" +
								  " AND nom_rev_no=(SELECT NVL(MAX(B.nom_rev_no),'0') "+
								  " FROM FMS7_DAILY_BUYER_NOM_DTL B "+
								  "	WHERE B.sn_no="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND FGSA_NO="+rset1.getString(1)+" "+
								  "	AND CONTRACT_TYPE='S' AND GAS_DT=TO_DATE('"+NOM_DT_NOT_ALLOCATED+"','dd/mm/yyyy')) ";
						//		//System.out.println("BUY-NOM:QRY-BN1002AC:SELECT:FMS7_DAILY_BUYER_NOM_DTL: "+queryString);
								rset = stmt.executeQuery(queryString);								
								if (rset.next())
								{	
									if(!rset.getString(1).equals("0"))
									{
										if(!rset.getString(1).trim().equals(""))
										{
										NOM_QTY +=Double.parseDouble(rset.getString(1));
										NOMINATED_QTY = rset.getString(1).trim();	
										//Buyer_Nominated_Qty.add(nf.format(Double.parseDouble(NOMINATED_QTY))+"* on "+NOM_DT_NOT_ALLOCATED);
										}
									}
								}
								else //if(rset.getString(1).equals("0"))
								{
									queryString = "SELECT NVL(SUM(QTY_MMBTU),'0') " +
									  "FROM FMS7_DAILY_BUYER_NOM_DTL WHERE " +
									  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
									  "AND FGSA_NO="+rset1.getString(1)+" " +
									  "AND CONTRACT_TYPE='S' AND GAS_DT=TO_DATE('"+NOM_DT_NOT_ALLOCATED+"','dd/mm/yyyy')" +
									  " AND nom_rev_no=(SELECT NVL(MAX(B.nom_rev_no),'0') "+
									  " FROM FMS7_DAILY_BUYER_NOM_DTL B "+
									  "	WHERE B.sn_no="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND FGSA_NO="+rset1.getString(1)+" "+
									  "	AND CONTRACT_TYPE='S' AND GAS_DT=TO_DATE('"+NOM_DT_NOT_ALLOCATED+"','dd/mm/yyyy')) ";
						//			//System.out.println("BUY-NOM:QRY-BN1002AD:SELECT:FMS7_DAILY_BUYER_NOM_DTL: "+queryString);
									rset = stmt.executeQuery(queryString);								
									if (rset.next())
									{	
										if(!rset.getString(1).equals("0"))
										{
											if(!rset.getString(1).trim().equals(""))
											{
											NOM_QTY +=Double.parseDouble(rset.getString(1));
											NOMINATED_QTY = rset.getString(1).trim();	
											//Buyer_Nominated_Qty.add(nf.format(Double.parseDouble(NOMINATED_QTY))+" on "+NOM_DT_NOT_ALLOCATED);
											}
										}
									}
								}
							//else
								//Buyer_Nominated_Qty.add(nf.format(Double.parseDouble(NOMINATED_QTY))+" on "+gas_date);
							//}
							NOMINATED_QTY = ""+NOM_QTY;
							Buyer_Nominated_Qty.add(nf.format(Double.parseDouble(NOMINATED_QTY))+" upto "+rset1.getString(12));
							//^
							//if((Double.parseDouble(NOMINATED_QTY)-Double.parseDouble(ALLOCATED_QTY))>0)
								//daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-(Double.parseDouble(NOMINATED_QTY))));
							//else
							//JHPdaily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
							queryString3 = "SELECT NVL(A.customer_abbr,' '), A.CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
								Vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
								Vdaily_Buyer_Nom_Name.add("*"); //SB20181005
							}
							
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";
							
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
							   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
							   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
							//SB20110915	if(Double.parseDouble(rset4.getString(1))>0)
								if(Double.parseDouble(rset4.getString(1))>=0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));									
                                	total_dcq += Double.parseDouble(rset1.getString(6));									
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq));
							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							
							++trans_count;
						}						
					}
				}//End Of Code For SN Based Buyers ...
				
				//Following Code Is For LOA Based Buyers ...
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.loa_ref_no,"
							   + "NVL(ADVANCE_COLLECTION_FLAG,'N'),RATE " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' and A.FCC_FLAG='Y' " + 
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				//System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
									   "ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							String tax_Structure_Dtl="";
							String tax_struct_cd="";							
							queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
							  "A.customer_cd="+rset1.getString(5)+" AND " +
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
							daily_tax_struct_dtl.add(tax_nm);
							
							
							queryString3 = "SELECT NVL(A.clause_cd,'0') FROM FMS7_LOA_CLAUSE_MST A " +
										   "WHERE A.buyer_cd="+rset1.getString(5)+" AND A.clause_cd=5 " +
										   "AND A.tender_no="+rset1.getString(1)+" AND A.loa_no="+rset1.getString(3)+" " +
										   "AND A.loa_rev_no="+rset1.getString(4)+"";
							//System.out.println("LOA Clause Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(rset3.getString(1).trim().equals("5"))
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("LC");
								}
								else
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
								}
							}
							else
							{
								daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
							}
							
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							daily_Buyer_Nom_Mapping_Id.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add("0");
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("L");
							daily_Buyer_Nom_Contract_Type.add("LOA");
							
							daily_Buyer_regas_cargo_boe_dt.add("");
							daily_Buyer_regas_cargo_boe_no.add("");
							PRE_APPROVAL.add("Y");
							COMM_PRE_APPROVAL.add("Y");
							ADVANCE_COLLECTION_FLAG.add(rset1.getString(11));
							SALE_PRICE.add(rset1.getString(12));
							String ALLOCATED_QTY = "0";
							String ALLOCATED_QTY1 = "0";
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));
							
							/*	queryString = "SELECT SUM(QTY_MMBTU) " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='L' " +
							  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
							  "";
						     *///JHP20111015
								queryString = "SELECT SUM(QTY_MMBTU) " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
								  "SN_NO="+rset1.getString(3)+" " +
								  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
								  "AND FGSA_NO="+rset1.getString(1)+" " +
								  "AND CONTRACT_TYPE='L' " +
								  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
								  "";						
							//System.out.println("LOA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
											
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
							
//							JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='L' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  "and NOM_REV_NO=(SELECT MAX(NOM_REV_NO) FROM FMS7_DAILY_SELLER_NOM_DTL " +
							  "WHERE SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  " AND FGSA_NO="+rset1.getString(1)+"" +
							  " AND CONTRACT_TYPE='L' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy'))";
								
							////System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("SELLER-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
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
						//JHP	daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
							queryString3 = "SELECT NVL(A.customer_abbr,' '), A.CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
								Vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
								Vdaily_Buyer_Nom_Name.add("*"); //SB20181005
							}
							
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";
							
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
				   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
				   			   			   "AND A.loa_no="+rset1.getString(3)+" " +
				   			   			   "AND A.loa_rev_no="+rset1.getString(4)+" " +
				   			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								//SB20110915	if(Double.parseDouble(rset4.getString(1))>0)
								if(Double.parseDouble(rset4.getString(1))>=0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq));
							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							
							++trans_count;
						}
					}
				}//End Of Code For LOA Based Buyers ...
				
				
				//Following Code Is For RE-GAS Based Buyers ...
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
				   "NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100'),NVL(A.cargo_ref_no,'0') " +
				   ",NVL(A.SYS_USE_GAS,'0'), NVL(A.BOE_QTY,'0'), " +//SB20110927
				   "NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " + //RG
				   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
				   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
				   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
				   "AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
				   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						//System.out.println("===JAVA REGAS===");
						
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							
							String tax_Structure_Dtl="";
							String tax_struct_cd="";
							queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							  "A.customer_cd="+rset1.getString(5)+" AND " +
							  "A.plant_seq_no="+rset2.getString(1)+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
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
							daily_tax_struct_dtl.add(tax_nm);
							
							
							queryString3 = "SELECT NVL(A.clause_cd,'0') FROM FMS7_RE_GAS_CLAUSE_MST A " +
										   "WHERE A.buyer_cd="+rset1.getString(5)+" AND A.clause_cd=5 " +
										   "AND A.re_gas_no="+rset1.getString(1)+" " +
										   "AND A.rev_no="+rset1.getString(2)+"";
							//System.out.println("RE-GAS Clause Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(rset3.getString(1).trim().equals("5"))
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("LC");
								}
								else
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
								}
							}
							else
							{
								daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
							}
							
							sn_ref_no.add("");
							daily_Buyer_Nom_Mapping_Id.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("R");
							daily_Buyer_Nom_Contract_Type.add("RE-GAS");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += Double.parseDouble(rset1.getString(6));
							
							String ALLOCATED_QTY = "0";
							String ALLOCATED_QTY1 = "0";
							//SB20110927
							String CONTRACTED_QTY = nf5.format(Double.parseDouble(rset1.getString(11)) - (Double.parseDouble(rset1.getString(11))*((Double.parseDouble(rset1.getString(10)))/100)));
							
							//////////////////////////RG///////////////////
							
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
							PRE_APPROVAL.add("Y");
							COMM_PRE_APPROVAL.add("Y");
							ADVANCE_COLLECTION_FLAG.add("N");
							SALE_PRICE.add("0");
						//	//System.out.println("size of daily_Buyer_regas_cargo_boe_no==================="+daily_Buyer_regas_cargo_boe_no.size()+"and"+daily_Buyer_regas_cargo_boe_no);
							
							///////////////////////////////////
							
							//^
							//SB20110927 String CONTRACTED_QTY = rset1.getString(7);
							
							/*	queryString = "SELECT SUM(QTY_MMBTU) " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='R' " +
							  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
							  "";
							*///JHP20111015
								queryString = "SELECT SUM(QTY_MMBTU) " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
								  "SN_NO="+rset1.getString(3)+" " +
								  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
								  "AND FGSA_NO="+rset1.getString(1)+" " +
								  "AND CONTRACT_TYPE='R' " +
								  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
								  "";					
							//System.out.println("SAMIK --> RE-GAS Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
											
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
//							JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='R' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  "and NOM_REV_NO=(SELECT MAX(NOM_REV_NO) FROM FMS7_DAILY_SELLER_NOM_DTL " +
							  "WHERE SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  " AND FGSA_NO="+rset1.getString(1)+"" +
							  " AND CONTRACT_TYPE='R' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy'))";
								
							////System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("SELLER-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
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
							//System.out.println("SAMIK--> Re-Gas CONTRACTED_QTY = "+CONTRACTED_QTY+",  Re-Gas ALLOCATED_QTY = "+ALLOCATED_QTY);
						//JHP	daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
							daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
							queryString3 = "SELECT NVL(A.customer_abbr,' '), A.CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
								Vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
								Vdaily_Buyer_Nom_Name.add("*"); //SB20181005
							}
							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (RE-GAS) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							
							++trans_count;
						}
					}
				} //End Of Code For RE-GAS Based Buyers ...
				
					//Following Code Is For LTCORA Based Buyers ... NB20141029
				queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
				   "NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100'),NVL(A.cargo_ref_no,'0') " +
				   ",NVL(A.SYS_USE_GAS,'0'), NVL(A.BOE_QTY,'0'), " +
				   "NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY'),pre_approval " + 
				   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, FMS8_LNG_REGAS_TRANS_DTL C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   "AND A.mapping_id=B.mapping_id AND A.mapping_id=C.mapping_id AND B.mapping_id=C.mapping_id " +
				   "AND C.trans_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
				   "AND B.CN_NO='0' AND B.CN_REV_NO='0'  " +
				   "ORDER BY A.mapping_id,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, LTCORA, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)==null?"":rset1.getString(1);
					String tempmap_id[]=map_id.split("-");
					
					String temp_regas_no=tempmap_id[1];
					String temp_regas_rev_no=tempmap_id[2];
					String temp_cust_cd=tempmap_id[0];
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_no,'1') FROM FMS8_LNG_REGAS_PLANT_DTL A " +
									   " WHERE A.mapping_id='"+rset1.getString(1)+"'" +
									   " ORDER BY A.plant_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (LTCORA) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							String tax_Structure_Dtl="";
							String tax_struct_cd="";
							queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							  "A.customer_cd="+temp_cust_cd+" AND " +
							  "A.plant_seq_no="+rset2.getString(1)+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
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
							daily_tax_struct_dtl.add(tax_nm);
							
							queryString3 = "SELECT NVL(A.clause_cd,'0') FROM FMS8_LNG_REGAS_CLAUSE_MST A " +
										   "WHERE A.mapping_id='"+rset1.getString(1)+"' AND A.clause_cd=5 " ;
										   
							//System.out.println("LTCORA Clause Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(rset3.getString(1).trim().equals("5"))
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("LC");
								}
								else
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
								}
							}
							else
							{
								daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
							}
							
							sn_ref_no.add("");
							daily_Buyer_Nom_Mapping_Id.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_No.add(temp_regas_no);
							daily_Buyer_Nom_Fgsa_Rev_No.add(temp_regas_rev_no);
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(temp_cust_cd);
							daily_Buyer_Nom_Type.add("T");
							daily_Buyer_Nom_Contract_Type.add("LTCORA");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += Double.parseDouble(rset1.getString(6));
							
							String ALLOCATED_QTY = "0";
							String ALLOCATED_QTY1 = "0";
							
							String CONTRACTED_QTY = nf5.format(Double.parseDouble(rset1.getString(11)) - (Double.parseDouble(rset1.getString(11))*((Double.parseDouble(rset1.getString(10)))/100)));
							
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
						
							PRE_APPROVAL.add(rset1.getString(14)==null?"N":rset1.getString(14));
						//	//System.out.println("size of daily_Buyer_regas_cargo_boe_no==================="+daily_Buyer_regas_cargo_boe_no.size()+"and"+daily_Buyer_regas_cargo_boe_no);
							String mp_id=""+temp_cust_cd+"-"+temp_regas_no+"-"+temp_regas_rev_no+"-"+rset1.getString(3)+"-"+0+"-"+"T";
							queryString = "select price_rate,currency_cd,flag " +
							"FROM fms7_cont_price_dtl WHERE mapping_id='"+mp_id+"' AND " +
							"LTCORA_NO='"+temp_regas_no+"' AND LTCORA_REV_NO='"+temp_regas_rev_no+"'  AND FLAG='R' ";
							
								rset4=stmt4.executeQuery(queryString);
								if(rset4.next())
								{
									COMM_PRE_APPROVAL.add("N");
								}
								else
								{
									COMM_PRE_APPROVAL.add("Y");
								}
								ADVANCE_COLLECTION_FLAG.add("N");
								SALE_PRICE.add("0");
							
							//^
							//SB20110927 String CONTRACTED_QTY = rset1.getString(7);
							
							/*	queryString = "SELECT SUM(QTY_MMBTU) " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='R' " +
							  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
							  "";
							*///JHP20111015
								queryString = "SELECT SUM(QTY_MMBTU) " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
								  "SN_NO="+rset1.getString(3)+" " +
								  "AND CUSTOMER_CD="+temp_cust_cd+" " +
								  "AND FGSA_NO="+temp_regas_no+" " +
								  "AND CONTRACT_TYPE='T' " +
								  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
								  "";					
							//System.out.println("NEHA --> LTCORA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
											
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
//							JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+temp_cust_cd+" " +
							  "AND FGSA_NO="+temp_regas_no+" " +
							  "AND CONTRACT_TYPE='T' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  "and NOM_REV_NO=(SELECT MAX(NOM_REV_NO) FROM FMS7_DAILY_SELLER_NOM_DTL " +
							  "WHERE SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+temp_cust_cd+" " +
							  " AND FGSA_NO="+temp_regas_no+"" +
							  " AND CONTRACT_TYPE='T' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy'))";
								
							////System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("SELLER-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
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
							
							//System.out.println("NEHA--> LTCORA CONTRACTED_QTY = "+CONTRACTED_QTY+",  Re-Gas ALLOCATED_QTY = "+ALLOCATED_QTY);
							//JHP	daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							
							ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
							daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
							
							//System.out.println("===JAVA CONTRACTED_QTY==: "+CONTRACTED_QTY);
							//System.out.println("===JAVA ALLOCATED_QTY===: "+ALLOCATED_QTY);
							//System.out.println("===JAVA ALLOCATED_QTY1===: "+ALLOCATED_QTY1);
							//System.out.println("===JAVA daily_Buyer_Nom_Balance_Qty: "+daily_Buyer_Nom_Balance_Qty);
							
							queryString3 = "SELECT NVL(A.customer_abbr,' '), A.CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+temp_cust_cd+"";
							//System.out.println("Buyer Abbreviation Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
								Vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
								Vdaily_Buyer_Nom_Name.add("*"); //SB20181005
							}
							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+temp_regas_no+" " +
							   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='T' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+temp_regas_no+" " +
										   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='T' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (LTCORA) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							
							++trans_count;
						}
					}
				}//End Of Code For LTCORA Based Buyers ...
				
				
				//Following Code Is For CN Based Buyers ... NB20141029
				queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
				   "NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100'),NVL(A.cargo_ref_no,'0') " +
				   ",NVL(A.SYS_USE_GAS,'0'), NVL(A.BOE_QTY,'0'), " +
				   "NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY'),pre_approval " + 
				   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, FMS8_LNG_REGAS_TRANS_DTL C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   "AND A.mapping_id=B.mapping_id AND A.mapping_id=C.mapping_id AND B.mapping_id=C.mapping_id " +
				   "AND C.trans_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
				   "AND B.CN_NO!='0'  " +
				   "ORDER BY A.mapping_id,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, CN, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)==null?"":rset1.getString(1);
					String tempmap_id[]=map_id.split("-");
					
					String temp_regas_no=tempmap_id[3];
					String temp_regas_rev_no=tempmap_id[4];
					String temp_cust_cd=tempmap_id[0];
					String temp_ltcora_no=tempmap_id[1];
					String temp_ltcora_rev_no=tempmap_id[2];
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_no,'1') FROM FMS8_LNG_REGAS_PLANT_DTL A " +
									   " WHERE A.mapping_id='"+rset1.getString(1)+"'" +
									   " ORDER BY A.plant_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (CN) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (CN) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							String tax_Structure_Dtl="";
							String tax_struct_cd="";
							queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							  "A.customer_cd="+temp_cust_cd+" AND " +
							  "A.plant_seq_no="+rset2.getString(1)+" AND " +
					 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
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
							daily_tax_struct_dtl.add(tax_nm);
							
							
							queryString3 = "SELECT NVL(A.clause_cd,'0') FROM FMS8_LNG_REGAS_CLAUSE_MST A " +
										   "WHERE A.mapping_id='"+rset1.getString(1)+"' AND A.clause_cd=5 " ;
										   
							//System.out.println("CN Clause Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(rset3.getString(1).trim().equals("5"))
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("LC");
								}
								else
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
								}
							}
							else
							{
								daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
							}
							
							sn_ref_no.add("");
							daily_Buyer_Nom_Mapping_Id.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_No.add(temp_regas_no);
							daily_Buyer_Nom_Fgsa_Rev_No.add(temp_regas_rev_no);
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(temp_cust_cd);
							daily_Buyer_Nom_Type.add("C");
							daily_Buyer_Nom_Contract_Type.add("CN");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += Double.parseDouble(rset1.getString(6));
							
							String ALLOCATED_QTY = "0";
							String ALLOCATED_QTY1 = "0";
							
							String CONTRACTED_QTY = nf5.format(Double.parseDouble(rset1.getString(11)) - (Double.parseDouble(rset1.getString(11))*((Double.parseDouble(rset1.getString(10)))/100)));
							
							ADVANCE_COLLECTION_FLAG.add("N");
							SALE_PRICE.add("0");
							
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
						
							PRE_APPROVAL.add(rset1.getString(14)==null?"N":rset1.getString(14));
							String mp_id=""+temp_cust_cd+"-"+temp_regas_no+"-"+temp_regas_rev_no+"-"+rset1.getString(3)+"-"+0+"-"+"C";
							queryString = "select price_rate,currency_cd,flag " +
							"FROM fms7_cont_price_dtl WHERE mapping_id='"+mp_id+"' AND " +
							"LTCORA_NO='"+temp_ltcora_no+"' AND LTCORA_REV_NO='"+temp_ltcora_rev_no+"'  AND FLAG='R' ";
							//System.out.println("COmmercial aproval-- "+queryString);
								rset4=stmt4.executeQuery(queryString);
								if(rset4.next())
								{
									COMM_PRE_APPROVAL.add("N");
									
								}
								else
								{
									COMM_PRE_APPROVAL.add("Y");
								}
						
								queryString = "SELECT SUM(QTY_MMBTU) " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
								  "SN_NO="+rset1.getString(3)+" " +
								  "AND CUSTOMER_CD="+temp_cust_cd+" " +
								  "AND FGSA_NO="+temp_regas_no+" " +
								  "AND CONTRACT_TYPE='C' " +
								  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy') " +
								  "AND MAPPING_ID='"+rset1.getString(1)+"'";					
							//System.out.println("NEHA --> CN Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
											
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
//							JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+temp_cust_cd+" " +
							  "AND FGSA_NO="+temp_regas_no+" " +
							  "AND CONTRACT_TYPE='C' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy') " +
							  "AND MAPPING_ID='"+rset1.getString(1)+"' " +
							  "and NOM_REV_NO=(SELECT MAX(NOM_REV_NO) FROM FMS7_DAILY_SELLER_NOM_DTL " +
							  "WHERE SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+temp_cust_cd+" " +
							  " AND FGSA_NO="+temp_regas_no+"" +
							  " AND CONTRACT_TYPE='C' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')) " +
							  "AND MAPPING_ID='"+rset1.getString(1)+"'";
								
							////System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("SELLER-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
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
							
							//System.out.println("NEHA--> CN CONTRACTED_QTY = "+CONTRACTED_QTY+",  Re-Gas ALLOCATED_QTY = "+ALLOCATED_QTY);
							//JHP daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							
							ALLOCATED_QTYV.add(Double.parseDouble(ALLOCATED_QTY)+Double.parseDouble(ALLOCATED_QTY1));
							
							daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
							
							//System.out.println("JAVA CONTRACTED_QTY: "+CONTRACTED_QTY);
							//System.out.println("JAVA ALLOCATED_QTY: "+ALLOCATED_QTY);
							//System.out.println("JAVA ALLOCATED_QTY1: "+ALLOCATED_QTY1);
							//System.out.println("JAVA daily_Buyer_Nom_Balance_Qty: "+daily_Buyer_Nom_Balance_Qty);
							
							queryString3 = "SELECT NVL(A.customer_abbr,' '), A.CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+temp_cust_cd+"";
							//System.out.println("Buyer Abbreviation Fetch Query (CN) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
								Vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
								Vdaily_Buyer_Nom_Name.add("*"); //SB20181005
							}
							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+temp_regas_no+" " +
							   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='C' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" " +
							   			   "AND MAPPING_ID='"+rset1.getString(1)+"' AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+temp_regas_no+" " +
										   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='C' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+" " +
										   "AND MAPPING_ID='"+rset1.getString(1)+"')";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (CN) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							
							++trans_count;
						}
					}
				}//End Of Code For CN Based Buyers ...
				
				
				//System.out.println("********TOTAL DCQ:" +total_dcq);
				master_Transporter_Count.add(""+trans_count);
				master_Transporter_Dcq.add(nf.format(total_dcq));
				
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm.add("");
				}
				
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
			}
			
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
			
			String previous_month_end_date = "";
			String previous_month_start_date = "";
			String month_start_date = "01"+gas_date.trim().substring(2);
			String month = "";
			String year = "";
			
			if(gas_date.trim().length()==10)
			{
				if(gas_date.trim().substring(3,5).equals("01"))
				{
					month = "12";
					year = ""+(Integer.parseInt(gas_date.trim().substring(6))-1);
					previous_month_start_date = "01/"+month+"/"+year;
					previous_month_end_date = "31/"+month+"/"+year;
				}
				else
				{
					if((Integer.parseInt(gas_date.trim().substring(3,5))-1)<10)
					{
						month = "0"+(Integer.parseInt(gas_date.trim().substring(3,5))-1);
						year = ""+gas_date.trim().substring(6);
					}
					else
					{
						month = ""+(Integer.parseInt(gas_date.trim().substring(3,5))-1);
						year = ""+gas_date.trim().substring(6);
					}
					
					previous_month_start_date = "01/"+month+"/"+year;
					
					queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','dd/mm/yyyy')),'dd/mm/yyyy') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						previous_month_end_date = rset.getString(1);
					}
				}
			}
			
			//Following Whole Logic Has been written for Generating Advance Alerts ...
			//This Logic Has been developed By Samik Shah On 22nd June, 2011 ...
			queryString = "SELECT DISTINCT CUSTOMER_CD, CUSTOMER_NAME FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"0":rset.getString(1));
				Vdaily_Buyer_Nom_Name.add(rset.getString(2)); //SB20181005
			}
			
			for(int i=0; i<CUST_CD.size(); i++)
			{
				double current_bal_amt = 0;
				double previous_month_bal_amt = 0;
				double month_advance_received = 0;
				double month_allocated_amt = 0;				
				
				if(previous_month_end_date.trim().length()==10)
				{
					queryString = "SELECT NVL(bal_amt,'0') FROM FMS7_MONTHLY_BALANCE " +
								  "WHERE customer_cd="+CUST_CD.elementAt(i)+" AND " +
								  "eff_dt=TO_DATE('"+previous_month_end_date+"','dd/mm/yyyy')";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						previous_month_bal_amt = rset.getDouble(1);
					}
				}
				
				queryString = "SELECT NVL(SUM(pay_amt),'0') FROM FMS7_PAYMENT_DTL " +
							  "WHERE customer_cd="+CUST_CD.elementAt(i)+" AND pay_type='A' AND " +
							  "(pay_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy'))";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_advance_received = rset.getDouble(1);
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='S'";
				//System.out.println("SN (ADV+LC) Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt += rset.getDouble(1);
					//System.out.println("SN (ADV+LC) Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, FMS7_SN_CLAUSE_MST C " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='S' AND A.customer_cd=C.buyer_cd " +
							  "AND A.fgsa_no=C.fgsa_no AND A.sn_no=C.sn_no " +
							  "AND C.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.fgsa_no " +
							  "AND A.sn_no=B.sn_no) AND C.clause_cd=5";
				//System.out.println("SN LC Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt -= rset.getDouble(1);
					//System.out.println("SN LC Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='L'";
				//System.out.println("LOA (ADV+LC) Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt += rset.getDouble(1);
					//System.out.println("LOA (ADV+LC) Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, FMS7_LOA_CLAUSE_MST C " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='L' AND A.customer_cd=C.buyer_cd " +
							  "AND A.fgsa_no=C.tender_no AND A.sn_no=C.loa_no " +
							  "AND C.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.tender_no " +
							  "AND A.sn_no=B.loa_no) AND C.clause_cd=5";
				//System.out.println("LOA LC Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt -= rset.getDouble(1);
					//System.out.println("LOA LC Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='R'";
				//System.out.println("RE-GAS (ADV+LC) Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt += rset.getDouble(1);
					//System.out.println("RE-GAS (ADV+LC) Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, FMS7_RE_GAS_CLAUSE_MST C " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='R' AND A.customer_cd=C.buyer_cd " +
							  "AND A.fgsa_no=C.re_gas_no " +
							  "AND C.rev_no=(SELECT MAX(B.rev_no) FROM FMS7_RE_GAS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.re_gas_no) " +
							  "AND C.clause_cd=5";
				//System.out.println("RE-GAS LC Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt -= rset.getDouble(1);
					//System.out.println("RE-GAS LC Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				
				//ADDED FOR LTCORA NB20141029
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A " +
				  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
				  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
				  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
				  "AND A.contract_type='T'";
				//System.out.println("LTCORA (ADV+LC) Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt += rset.getDouble(1);
					//System.out.println("LTCORA (ADV+LC) Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				/*queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, FMS8_LNG_REGAS_CLAUSE_MST C " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='T' AND A.customer_cd=C.buyer_cd " +
							  "AND A.fgsa_no=C.re_gas_no " +
							  "AND C.rev_no=(SELECT MAX(B.rev_no) FROM FMS8_LNG_REGAS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.AGREEMENT_NO) " +
							  "AND C.clause_cd=5";*/
				
				queryString="SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, " +
						" FMS8_LNG_REGAS_CLAUSE_MST C, FMS8_LNG_REGAS_MST D WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
						" (A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
						" AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) AND A.contract_type='T' AND " +
						" C.mapping_id LIKE '"+CUST_CD.elementAt(i)+"%' AND D.rev_no=(SELECT MAX(B.rev_no) FROM " +
						" FMS8_LNG_REGAS_MST B WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.AGREEMENT_NO" +
						" AND B.mapping_id=D.mapping_id) AND C.clause_cd=5 AND A.customer_cd=D.customer_cd AND" +
						" C.mapping_id=D.mapping_id AND A.MAPPING_ID= c.mapping_id";
				
				//System.out.println("LTCORA LC Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt -= rset.getDouble(1);
					//System.out.println("LTCORA LC Based Amount = "+nf.format(rset.getDouble(1)));
				}
				////////////////////////////////////LTCORA END////////////////////////////////////////
				
//				ADDED FOR CN NB20141029
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A " +
				  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
				  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
				  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
				  "AND A.contract_type='C'";
				//System.out.println("LTCORA (ADV+LC) Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt += rset.getDouble(1);
					//System.out.println("CN (ADV+LC) Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				/*queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, FMS8_LNG_REGAS_CLAUSE_MST C " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='T' AND A.customer_cd=C.buyer_cd " +
							  "AND A.fgsa_no=C.re_gas_no " +
							  "AND C.rev_no=(SELECT MAX(B.rev_no) FROM FMS8_LNG_REGAS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.AGREEMENT_NO) " +
							  "AND C.clause_cd=5";*/
				
				queryString="SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, " +
						" FMS8_LNG_REGAS_CLAUSE_MST C, FMS8_LNG_REGAS_MST D WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
						" (A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
						" AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) AND A.contract_type='C' AND " +
						" C.mapping_id LIKE '"+CUST_CD.elementAt(i)+"%' AND D.cn_rev_no=(SELECT MAX(B.cn_rev_no) FROM " +
						" FMS8_LNG_REGAS_MST B WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.AGREEMENT_NO" +
						" AND B.mapping_id=D.mapping_id) AND C.clause_cd=5 AND A.customer_cd=D.customer_cd AND" +
						" C.mapping_id=D.mapping_id AND A.MAPPING_ID= c.mapping_id";
				
				//System.out.println("CN LC Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt -= rset.getDouble(1);
					//System.out.println("CN LC Based Amount = "+nf.format(rset.getDouble(1)));
				}
				////////////////////////////////////CN END////////////////////////////////////////
	
				current_bal_amt = previous_month_bal_amt+month_advance_received-month_allocated_amt;
				
				daily_Buyer_Nom_Current_Balance_Amt.add(nf.format(current_bal_amt));
			}
			
			
			
			Vector lc_cust_cd = new Vector();
			Vector lc_fgsa_no = new Vector();
			Vector lc_fgsa_rev_no = new Vector();
			Vector lc_sn_no = new Vector();
			Vector lc_sn_rev_no = new Vector();
			Vector lc_con_typ = new Vector();

			
			for (int i=0; i<daily_Buyer_Nom_Cd.size(); i++)
			{
					/*lc_cust_cd_fin.add(daily_Buyer_Nom_Cd.elementAt(i));
					lc_fgsa_no_fin.add(daily_Buyer_Nom_Fgsa_No.elementAt(i));
					lc_fgsa_rev_no_fin.add(daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i));
					lc_sn_no_fin.add(daily_Buyer_Nom_Sn_No.elementAt(i));
					lc_sn_rev_no_fin.add(daily_Buyer_Nom_Sn_Rev_No.elementAt(i));
					lc_con_typ_fin.add(daily_Buyer_Nom_Type.elementAt(i));*/
					
					lc_cust_cd.add(daily_Buyer_Nom_Cd.elementAt(i));
					lc_fgsa_no.add(daily_Buyer_Nom_Fgsa_No.elementAt(i));
					lc_fgsa_rev_no.add(daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i));
					lc_sn_no.add(daily_Buyer_Nom_Sn_No.elementAt(i));
					lc_sn_rev_no.add(daily_Buyer_Nom_Sn_Rev_No.elementAt(i));
					lc_con_typ.add(daily_Buyer_Nom_Type.elementAt(i));
			}
			
			
			Vector lc_cust_cd1 = new Vector();
			Vector lc_fgsa_no1 = new Vector();
			Vector lc_fgsa_rev_no1 =  new Vector();
			Vector lc_sn_no1 = new Vector();
			Vector lc_sn_rev_no1 = new Vector();
			Vector lc_con_typ1 = new Vector();
			
			//TO REMOVE DUPLICATE VALUES
			Vector cntm = new Vector();
			for (int i = 0; i<lc_sn_no.size(); i++)
			{
				String slc_cust_cd2 = lc_cust_cd.elementAt(i).toString();
				String slc_fgsa_no2 = lc_fgsa_no.elementAt(i).toString();
				String slc_fgsa_rev_no2 = lc_fgsa_rev_no.elementAt(i).toString();
				String slc_sn_no2 = lc_sn_no.elementAt(i).toString();
				String slc_sn_rev_no2 = lc_sn_rev_no.elementAt(i).toString();
				String slc_con_typ2 = lc_con_typ.elementAt(i).toString();
				String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
				for (int j = i+1; j<lc_sn_no.size(); j++)
				{
					String slc_cust_cd1 = lc_cust_cd.elementAt(j).toString();
					String slc_fgsa_no1 = lc_fgsa_no.elementAt(j).toString();
					String slc_fgsa_rev_no1 = lc_fgsa_rev_no.elementAt(j).toString();
					String slc_sn_no1 = lc_sn_no.elementAt(j).toString();
					String slc_sn_rev_no1 = lc_sn_rev_no.elementAt(j).toString();
					String slc_con_typ1 = lc_con_typ.elementAt(j).toString();
					 String combo = slc_cust_cd1+slc_fgsa_no1+slc_fgsa_rev_no1+slc_sn_no1+slc_sn_rev_no1+slc_con_typ1;
					if (combo2.trim().equals(combo.trim()))
					{
						cntm.add(""+j);
					}
				}
				if (!cntm.isEmpty())
				{
					for (int j =0;j<cntm.size();j++)
					{
						lc_cust_cd.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_fgsa_no.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_fgsa_rev_no.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_sn_no.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_sn_rev_no.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_con_typ.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
					}
					cntm.clear();
				}
			}
			
		
			for (int j = 0; j<lc_sn_no.size(); j++)
			{
				String slc_cust_cd2 = lc_cust_cd.elementAt(j).toString();
				String slc_fgsa_no2 = lc_fgsa_no.elementAt(j).toString();
				String slc_fgsa_rev_no2 = lc_fgsa_rev_no.elementAt(j).toString();
				String slc_sn_no2 = lc_sn_no.elementAt(j).toString();
				String slc_sn_rev_no2 = lc_sn_rev_no.elementAt(j).toString();
				String slc_con_typ2 = lc_con_typ.elementAt(j).toString();
				String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
				if (!combo2.trim().equals(""))
				{
					lc_cust_cd1.add(slc_cust_cd2);
					lc_fgsa_no1.add(slc_fgsa_no2);
					lc_fgsa_rev_no1.add(slc_fgsa_rev_no2);
					lc_sn_no1.add(slc_sn_no2);
					lc_sn_rev_no1.add(slc_sn_rev_no2);
					lc_con_typ1.add(slc_con_typ2);
				}
			}
			
			
			Vector LC_FIN_YEAR = new Vector();
			Vector LC_SEQ_NO = new Vector();
			Vector LC_CUST_CD = new Vector();
			Vector LC_END_DT = new Vector();
			
			
			for (int i=0; i<lc_cust_cd1.size(); i++)
			{
				queryString = "SELECT FINANCIAL_YEAR," +
							"LC_SEQ_NO," +
							"CUSTOMER_CD" +
							" FROM FMS7_LC_DTL" +
							" WHERE CUSTOMER_CD = '"+lc_cust_cd1.elementAt(i).toString().trim()+"' AND FGSA_NO = '"+lc_fgsa_no1.elementAt(i).toString().trim()+"'" +
							" AND FGSA_REV_NO = '"+lc_fgsa_rev_no1.elementAt(i).toString().trim()+"' AND SN_NO = '"+lc_sn_no1.elementAt(i).toString().trim()+"'" +
							" AND SN_REV_NO = '"+lc_sn_rev_no1.elementAt(i).toString().trim()+"' AND CONT_TYPE = '"+lc_con_typ1.elementAt(i).toString().trim()+"'";
				rset = stmt.executeQuery(queryString);
				if (rset.next())
				{
					LC_FIN_YEAR.add(rset.getString(1) == null ? "" : rset.getString(1));
					LC_SEQ_NO.add(rset.getString(2) == null ? "" : rset.getString(2));
					LC_CUST_CD.add(rset.getString(3) == null ? "" : rset.getString(3));
					
				}
				else
				{
					LC_FIN_YEAR.add("-");
					LC_SEQ_NO.add("-");
					LC_CUST_CD.add("-");
				}
			}
			
			for (int i=0; i<LC_SEQ_NO.size(); i++)
			{
				if (!LC_SEQ_NO.elementAt(i).toString().trim().equals("") && !LC_SEQ_NO.elementAt(i).toString().trim().equals("-"))
				{
					queryString = "SELECT LC_SEQ_NO," +
								"CUSTOMER_CD," +
								" TO_CHAR(END_DATE,'DD/MM/YYYY')" +
								" FROM FMS7_LC_MST" +
								" WHERE CUSTOMER_CD = '"+LC_CUST_CD.elementAt(i).toString().trim()+"'" +
								" AND FINANCIAL_YEAR = '"+LC_FIN_YEAR.elementAt(i).toString().trim()+"' " +
								" AND LC_SEQ_NO = '"+LC_SEQ_NO.elementAt(i).toString().trim()+"'";
					rset = stmt.executeQuery(queryString);
					if (rset.next())
					{
						LC_END_DT.add(rset.getString(3) == null ? "" : rset.getString(3));
						
					}
					else
					{
						LC_END_DT.add("-");
					}
				}
				else
				{
					LC_END_DT.add("-");
				}
			}
			
			for (int i =0; i<lc_con_typ.size(); i++)
			{
				LC_EXP_FLG.add(lc_con_typ.elementAt(i));
				LC_DAY_FLG.add(lc_con_typ.elementAt(i));
				LC_END_DT_FIN.add(lc_con_typ.elementAt(i));
			}
			
			boolean exp_flg = false;
			
			lc_cust_cd = daily_Buyer_Nom_Cd;
			lc_fgsa_no = daily_Buyer_Nom_Fgsa_No;
			lc_fgsa_rev_no = daily_Buyer_Nom_Fgsa_Rev_No;
			lc_sn_no = daily_Buyer_Nom_Sn_No;
			lc_sn_rev_no = daily_Buyer_Nom_Sn_Rev_No;
			lc_con_typ = daily_Buyer_Nom_Type;
			
			
			int day = 0;
			String yes ="NO";
			
			String myFormatString = "dd/MM/yyyy"; // for example
			SimpleDateFormat df = new SimpleDateFormat(myFormatString);
			
			//System.out.println("gen date : "+gen_date);
			for (int i = 0; i<LC_SEQ_NO.size(); i++)
			{
				if (!LC_SEQ_NO.elementAt(i).toString().trim().equals("-"))
				{
					
					Date end_dt = df.parse(LC_END_DT.elementAt(i).toString().trim());
					Date g_dt = df.parse(gen_date);
					int tmp = end_dt.compareTo(g_dt);
					if(tmp > 0)
					{
						//System.out.println("first date is later : "+end_dt+" "+g_dt);
						queryString = "SELECT TO_DATE('"+gen_date+"','DD/MM/YYYY')+3 FROM DUAL";
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							Date genration_dt = rset.getDate(1);
							if (genration_dt.equals(end_dt))
							{
								//System.out.println("LC end after 3 days : "+end_dt);
								exp_flg = true;
								day = 3;
								yes = "YES";
							}
							else if(genration_dt.compareTo(end_dt)>0)
							{
								queryString = "SELECT TO_DATE('"+gen_date+"','DD/MM/YYYY')+2 FROM DUAL";
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									genration_dt = rset.getDate(1);
								}
								if (genration_dt.equals(end_dt))
								{
									//System.out.println("LC end after 2 days : "+end_dt);
									exp_flg = true;
									day = 2;
									yes = "YES";
								}
								else if(genration_dt.compareTo(end_dt)>0)
								{
									queryString = "SELECT TO_DATE('"+gen_date+"','DD/MM/YYYY')+1 FROM DUAL";
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										genration_dt = rset.getDate(1);
									}
									if (genration_dt.equals(end_dt))
									{
										//System.out.println("LC end after 1 days : "+end_dt);
										exp_flg = true;
										day = 1;
										yes = "Y";
									}
								}
							}
								
						}
					}
					if(exp_flg)
					{
						String slc_cust_cd2 = lc_cust_cd1.elementAt(i).toString();
						String slc_fgsa_no2 = lc_fgsa_no1.elementAt(i).toString();
						String slc_fgsa_rev_no2 = lc_fgsa_rev_no1.elementAt(i).toString();
						String slc_sn_no2 = lc_sn_no1.elementAt(i).toString();
						String slc_sn_rev_no2 = lc_sn_rev_no1.elementAt(i).toString();
						String slc_con_typ2 = lc_con_typ1.elementAt(i).toString();
						
						String slc_end_dt = LC_END_DT.elementAt(i).toString();
						
						//System.out.println("test if i : "+i);
						String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
						for (int j = 0; j<lc_sn_no.size(); j++)
						{
							
							String slc_cust_cd1 = lc_cust_cd.elementAt(j).toString();
							String slc_fgsa_no1 = lc_fgsa_no.elementAt(j).toString();
							String slc_fgsa_rev_no1 = lc_fgsa_rev_no.elementAt(j).toString();
							String slc_sn_no1 = lc_sn_no.elementAt(j).toString();
							String slc_sn_rev_no1 = lc_sn_rev_no.elementAt(j).toString();
							String slc_con_typ1 = lc_con_typ.elementAt(j).toString();
							String combo = slc_cust_cd1+slc_fgsa_no1+slc_fgsa_rev_no1+slc_sn_no1+slc_sn_rev_no1+slc_con_typ1;
							if (combo2.trim().equals(combo.trim()))
							{
								//System.out.println("test if j : "+j);
								LC_EXP_FLG.setElementAt(yes,j);
								LC_DAY_FLG.setElementAt(""+day,j);
								LC_END_DT_FIN.setElementAt(slc_end_dt,j);
							}
						}
						exp_flg = false;
						day = 0;
						yes = "NO";
					}
					else
					{
						String slc_cust_cd2 = lc_cust_cd1.elementAt(i).toString();
						String slc_fgsa_no2 = lc_fgsa_no1.elementAt(i).toString();
						String slc_fgsa_rev_no2 = lc_fgsa_rev_no1.elementAt(i).toString();
						String slc_sn_no2 = lc_sn_no1.elementAt(i).toString();
						String slc_sn_rev_no2 = lc_sn_rev_no1.elementAt(i).toString();
						String slc_con_typ2 = lc_con_typ1.elementAt(i).toString();
						
						//System.out.println("test if i : "+i);
						String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
						for (int j = 0; j<lc_sn_no.size(); j++)
						{
							
							String slc_cust_cd1 = lc_cust_cd.elementAt(j).toString();
							String slc_fgsa_no1 = lc_fgsa_no.elementAt(j).toString();
							String slc_fgsa_rev_no1 = lc_fgsa_rev_no.elementAt(j).toString();
							String slc_sn_no1 = lc_sn_no.elementAt(j).toString();
							String slc_sn_rev_no1 = lc_sn_rev_no.elementAt(j).toString();
							String slc_con_typ1 = lc_con_typ.elementAt(j).toString();
							String combo = slc_cust_cd1+slc_fgsa_no1+slc_fgsa_rev_no1+slc_sn_no1+slc_sn_rev_no1+slc_con_typ1;
							if (combo2.trim().equals(combo.trim()))
							{
								//System.out.println("test if j : "+j);
								LC_EXP_FLG.setElementAt("-",j);
								LC_DAY_FLG.setElementAt("-",j);
								LC_END_DT_FIN.setElementAt("-",j);
							}
						}
					}
				}
				else
				{
					String slc_cust_cd2 = lc_cust_cd1.elementAt(i).toString();
					String slc_fgsa_no2 = lc_fgsa_no1.elementAt(i).toString();
					String slc_fgsa_rev_no2 = lc_fgsa_rev_no1.elementAt(i).toString();
					String slc_sn_no2 = lc_sn_no1.elementAt(i).toString();
					String slc_sn_rev_no2 = lc_sn_rev_no1.elementAt(i).toString();
					String slc_con_typ2 = lc_con_typ1.elementAt(i).toString();
					//System.out.println("test else i : "+i);
					String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
					for (int j = 0; j<lc_sn_no.size(); j++)
					{
						
						String slc_cust_cd1 = lc_cust_cd.elementAt(j).toString();
						String slc_fgsa_no1 = lc_fgsa_no.elementAt(j).toString();
						String slc_fgsa_rev_no1 = lc_fgsa_rev_no.elementAt(j).toString();
						String slc_sn_no1 = lc_sn_no.elementAt(j).toString();
						String slc_sn_rev_no1 = lc_sn_rev_no.elementAt(j).toString();
						String slc_con_typ1 = lc_con_typ.elementAt(j).toString();
						 String combo = slc_cust_cd1+slc_fgsa_no1+slc_fgsa_rev_no1+slc_sn_no1+slc_sn_rev_no1+slc_con_typ1;
						if (combo2.trim().equals(combo.trim()))
						{
							//System.out.println("test else j : "+j);
							LC_EXP_FLG.setElementAt("-",j);
							LC_DAY_FLG.setElementAt("-",j);
							LC_END_DT_FIN.setElementAt("-",j);
						}
					}
					
				}
			}
			//CODE FOR LC EXPIRE DATE ALERT ENDS HERE
			
			
			
			/*Fetch Advance Data.........*/
//			double exchg_rate = 0;
//			String q = "SELECT EXCHG_RATE FROM EXCHG_RATE_MST A "
//					+ "WHERE  EFF_DT = (SELECT MAX(EFF_DT) FROM EXCHG_RATE_MST B "
//					+ "WHERE  A.SEQ_NO=B.SEQ_NO AND EFF_DT <= TO_DATE('"+gas_date+"','DD/MM/YYYY')) "
//					+ "AND EFF_DT <= TO_DATE('"+gas_date+"','DD/MM/YYYY') "
//					+ "ORDER BY SEQ_NO DESC";
//			//System.out.println("query.."+q);
//			rset = stmt.executeQuery(q);
//			if(rset.next())
//			{
//				exchg_rate = rset.getDouble(1);
//			}
			for(int i =0;i<daily_Buyer_Nom_Contract_Type.size();i++)
			{
				double qty = 0;
				double advance_amount = 0;
				
				if(daily_Buyer_Nom_Contract_Type.elementAt(i).equals("SN") || daily_Buyer_Nom_Contract_Type.elementAt(i).equals("LOA"))
				{
//					String query="SELECT ADVANCE_AMOUNT,CURRENCY,TAX_AMOUNT FROM FMS8_ADVANCE_AMT_MST "
//							+ "WHERE FGSA_NO='"+daily_Buyer_Nom_Fgsa_No.elementAt(i)+"' AND "
//							+ "FGSA_REV_NO='"+daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i)+"' "
//							+ "AND SN_NO='"+daily_Buyer_Nom_Sn_No.elementAt(i)+"' AND "
//							+ "SN_REV_NO='"+daily_Buyer_Nom_Sn_Rev_No.elementAt(i)+"' "
//							+ "AND CUSTOMER_CD='"+daily_Buyer_Nom_Cd.elementAt(i)+"' "
//							+ "AND CONTRACT_TYPE='"+daily_Buyer_Nom_Contract_Type.elementAt(i)+"' "
//							+ "";
//					System.out.println("JAVA query: "+query);
//					rset = stmt.executeQuery(query);
//					while(rset.next())
//					{
//						if(rset.getString(2).equals("I"))
//						{
//							advance_amount += rset.getDouble(1);
//						}
//						else
//						{
//							advance_amount += (rset.getDouble(1)*exchg_rate);
//						}
//						System.out.println("---JAVA rset.getDouble(1)--- "+rset.getDouble(1));
//						advance_amount -= ((advance_amount * rset.getDouble(3))/100);
//					}
//					//check here
//					if(exchg_rate!=0)
//					{
//						//System.out.println("===JAVA if===");
//						qty = (advance_amount / exchg_rate) / (Double.parseDouble(SALE_PRICE.elementAt(i)+""));
//						//System.out.println("===JAVA if===qty: "+qty+" ALLOCATED_QTYV.elementAt(i): "+ALLOCATED_QTYV.elementAt(i));
//						qty_nomination.add(qty - Double.parseDouble(ALLOCATED_QTYV.elementAt(i)+""));
//						//System.out.println("===JAVA if=== qty_nomination: "+qty_nomination);
//						System.out.println("---JAVA ALLOCATED_QTYV---: "+ALLOCATED_QTYV);
//					} 
//					else 
//					{
//						//System.out.println("===JAVA else=== daily_Buyer_Nom_Balance_Qty.elementAt(i): "+daily_Buyer_Nom_Balance_Qty.elementAt(i));
//						qty_nomination.add(Double.parseDouble(daily_Buyer_Nom_Balance_Qty.elementAt(i)+""));
//						//System.out.println("===JAVA else=== qty_nomination: "+qty_nomination);
//					}
					qty_nomination.add(Double.parseDouble(daily_Buyer_Nom_Balance_Qty.elementAt(i)+""));
				} 
				else 
				{
					//System.out.println("===JAVA ELSE===daily_Buyer_Nom_Balance_Qty.elementAt(i): "+daily_Buyer_Nom_Balance_Qty.elementAt(i));
					qty_nomination.add(daily_Buyer_Nom_Balance_Qty.elementAt(i)+"");
					//System.out.println("===JAVA ELSE===qty_nomination: "+qty_nomination);
				}
//				System.out.println("....advance_amount"+advance_amount);
			}
			
//			System.out.println("==="+qty_nomination);
			
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
		
	}//End Of Method fetchDailyBuyerNomDetails() ...
	
	Vector qty_nomination = new Vector();
	Vector ALLOCATED_QTYV = new Vector();
	
	
	//Following Function Is Introduced By Samik Shah On 18th May, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 25th August, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 22nd June, 2011 For Advance Alerts ...
	public void fetchDailyBuyerNomDetails_OLD()
	{
		methodName = "fetchDailyBuyerNomDetails()";
		try 
		{
//JHP20111014
			String date_tomorrow="";
			rset = stmt.executeQuery("select to_char(to_date('"+gas_date+"','dd/mm/yyyy') - 1,'dd/mm/yyyy') date_tomorrow from dual");
		      if(rset.next()) {
		       date_tomorrow=rset.getString("date_tomorrow");
		      }
//JHP20111014
			
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			//System.out.println("MIlan >>>> gen date : "+gen_date);
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd.add(rset.getString(1));
				master_Transporter_Abbr.add(rset.getString(2));
			}
			
			for(int i=0; i<master_Transporter_Cd.size(); i++)
			{
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
				
				//Following Code Is For SN Based Buyers ...
				/*queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.sn_ref_no " +
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' AND A.FCC_FLAG='Y' " +
							   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   //"AND A.fgsa_rev_no=B.fgsa_rev_no " +
							   "AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				*/
//				Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.sn_ref_no "+
							   " ,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY') " +  //SB20110926
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' AND A.FCC_FLAG='Y' " + 
							   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   //"AND A.fgsa_rev_no=B.fgsa_rev_no " +
							   "AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM FMS7_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 2nd March, 2011 ...
									   "AND A.sn_no="+rset1.getString(3)+"";
									   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 2nd March, 2011 ...
									   //"ORDER BY A.plant_seq_no";
						//System.out.println("SAMIK --> Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							queryString3 = "SELECT NVL(A.clause_cd,'0') FROM FMS7_SN_CLAUSE_MST A " +
										   "WHERE A.buyer_cd="+rset1.getString(5)+" AND A.clause_cd=5 " +
										   "AND A.fgsa_no="+rset1.getString(1)+" AND A.sn_no="+rset1.getString(3)+" " +
										   "AND A.sn_rev_no="+rset1.getString(4)+"";
							//System.out.println("SN Clause Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(rset3.getString(1).trim().equals("5"))
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("LC");
								}
								else
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
								}
							}
							else
							{
								daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
							}
							
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("S");
							daily_Buyer_Nom_Contract_Type.add("SN");
													
							daily_Buyer_regas_cargo_boe_dt.add("");
							daily_Buyer_regas_cargo_boe_no.add("");
							
							String ALLOCATED_QTY = "0";
							String ALLOCATED_QTY1="0";
							String MAX_ALLOCATED_DT = gas_date;  //SB20110924
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));
							
//							SB20110924queryString = "SELECT SUM(QTY_MMBTU) " +
							/*	queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='S' " +
							  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
							  "";
			                *///JHP20111015
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='S' " +
							  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  "";				
							////System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("BUY-NOM:QRY-BN1002AAAA:SELECT:DLNG_DAILY_ALLOCATION_DTL: "+queryString);
							rset = stmt.executeQuery(queryString);
											
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
										MAX_ALLOCATED_DT = rset.getString(2);
									}
								}
							}
							
//							JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='S' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  " and NOM_REV_NO=(SELECT MAX(NOM_REV_NO) FROM FMS7_DAILY_SELLER_NOM_DTL " +
							  "WHERE SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  " AND FGSA_NO="+rset1.getString(1)+"" +
							  " AND CONTRACT_TYPE='S' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy'))";
								
							////System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("SELLER-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
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
							//SB20110924: Introduced Nominated Quantity for calculation
							String NOMINATED_QTY = "0";
							Buyer_Contracted_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)));
							Buyer_Allocated_Qty.add(nf.format(Double.parseDouble(ALLOCATED_QTY))+" as on "+MAX_ALLOCATED_DT);
							
							String NOM_DT_NOT_ALLOCATED = gas_date;  //SB20110924
							double NOM_QTY = 0;
							String no_days="0";
							queryString3 = " SELECT  (TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') - TO_DATE('"+MAX_ALLOCATED_DT+"','DD/MM/YYYY')) FROM DUAL ";
							//System.out.println("BUY-NOM:QRY-BN1002AB*:SELECT:DUAL: "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								no_days = rset3.getString(1);
							}
							
							//for(int z=1; z<=Integer.parseInt(no_days); z++)
							//{
								//queryString3 = "SELECT TO_CHAR(TO_DATE('"+MAX_ALLOCATED_DT+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
								queryString3 = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
							//	//System.out.println("BUY-NOM:QRY-BN1002AB:SELECT:DUAL: "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									NOM_DT_NOT_ALLOCATED = rset3.getString(1);
								}
								queryString = "SELECT NVL(SUM(QTY_MMBTU),'0') " +
								  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
								  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
								  "AND FGSA_NO="+rset1.getString(1)+" " +
								  "AND CONTRACT_TYPE='S' AND GAS_DT=TO_DATE('"+NOM_DT_NOT_ALLOCATED+"','dd/mm/yyyy')" +
								  " AND nom_rev_no=(SELECT NVL(MAX(B.nom_rev_no),'0') "+
								  " FROM FMS7_DAILY_BUYER_NOM_DTL B "+
								  "	WHERE B.sn_no="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND FGSA_NO="+rset1.getString(1)+" "+
								  "	AND CONTRACT_TYPE='S' AND GAS_DT=TO_DATE('"+NOM_DT_NOT_ALLOCATED+"','dd/mm/yyyy')) ";
						//		//System.out.println("BUY-NOM:QRY-BN1002AC:SELECT:FMS7_DAILY_BUYER_NOM_DTL: "+queryString);
								rset = stmt.executeQuery(queryString);								
								if (rset.next())
								{	
									if(!rset.getString(1).equals("0"))
									{
										if(!rset.getString(1).trim().equals(""))
										{
										NOM_QTY +=Double.parseDouble(rset.getString(1));
										NOMINATED_QTY = rset.getString(1).trim();	
										//Buyer_Nominated_Qty.add(nf.format(Double.parseDouble(NOMINATED_QTY))+"* on "+NOM_DT_NOT_ALLOCATED);
										}
									}
								}
								else //if(rset.getString(1).equals("0"))
								{
									queryString = "SELECT NVL(SUM(QTY_MMBTU),'0') " +
									  "FROM FMS7_DAILY_BUYER_NOM_DTL WHERE " +
									  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
									  "AND FGSA_NO="+rset1.getString(1)+" " +
									  "AND CONTRACT_TYPE='S' AND GAS_DT=TO_DATE('"+NOM_DT_NOT_ALLOCATED+"','dd/mm/yyyy')" +
									  " AND nom_rev_no=(SELECT NVL(MAX(B.nom_rev_no),'0') "+
									  " FROM FMS7_DAILY_BUYER_NOM_DTL B "+
									  "	WHERE B.sn_no="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND FGSA_NO="+rset1.getString(1)+" "+
									  "	AND CONTRACT_TYPE='S' AND GAS_DT=TO_DATE('"+NOM_DT_NOT_ALLOCATED+"','dd/mm/yyyy')) ";
						//			//System.out.println("BUY-NOM:QRY-BN1002AD:SELECT:FMS7_DAILY_BUYER_NOM_DTL: "+queryString);
									rset = stmt.executeQuery(queryString);								
									if (rset.next())
									{	
										if(!rset.getString(1).equals("0"))
										{
											if(!rset.getString(1).trim().equals(""))
											{
											NOM_QTY +=Double.parseDouble(rset.getString(1));
											NOMINATED_QTY = rset.getString(1).trim();	
											//Buyer_Nominated_Qty.add(nf.format(Double.parseDouble(NOMINATED_QTY))+" on "+NOM_DT_NOT_ALLOCATED);
											}
										}
									}
								}
							//else
								//Buyer_Nominated_Qty.add(nf.format(Double.parseDouble(NOMINATED_QTY))+" on "+gas_date);
							//}
							NOMINATED_QTY = ""+NOM_QTY;
							Buyer_Nominated_Qty.add(nf.format(Double.parseDouble(NOMINATED_QTY))+" upto "+rset1.getString(12));
							//^
							//if((Double.parseDouble(NOMINATED_QTY)-Double.parseDouble(ALLOCATED_QTY))>0)
								//daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-(Double.parseDouble(NOMINATED_QTY))));
							//else
							//JHPdaily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";
							
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
							   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
							   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
							//SB20110915	if(Double.parseDouble(rset4.getString(1))>0)
								if(Double.parseDouble(rset4.getString(1))>=0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));									
                                	total_dcq += Double.parseDouble(rset1.getString(6));									
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq));
							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							
							++trans_count;
						}						
					}
				}//End Of Code For SN Based Buyers ...
				
				//Following Code Is For LOA Based Buyers ...
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.loa_ref_no " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' and A.FCC_FLAG='Y' " + 
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				//System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
									   "ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							queryString3 = "SELECT NVL(A.clause_cd,'0') FROM FMS7_LOA_CLAUSE_MST A " +
										   "WHERE A.buyer_cd="+rset1.getString(5)+" AND A.clause_cd=5 " +
										   "AND A.tender_no="+rset1.getString(1)+" AND A.loa_no="+rset1.getString(3)+" " +
										   "AND A.loa_rev_no="+rset1.getString(4)+"";
							//System.out.println("LOA Clause Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(rset3.getString(1).trim().equals("5"))
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("LC");
								}
								else
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
								}
							}
							else
							{
								daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
							}
							
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add("0");
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("L");
							daily_Buyer_Nom_Contract_Type.add("LOA");
							
							daily_Buyer_regas_cargo_boe_dt.add("");
							daily_Buyer_regas_cargo_boe_no.add("");
							
							String ALLOCATED_QTY = "0";
							String ALLOCATED_QTY1 = "0";
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));
							
							/*	queryString = "SELECT SUM(QTY_MMBTU) " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='L' " +
							  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
							  "";
						     *///JHP20111015
								queryString = "SELECT SUM(QTY_MMBTU) " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
								  "SN_NO="+rset1.getString(3)+" " +
								  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
								  "AND FGSA_NO="+rset1.getString(1)+" " +
								  "AND CONTRACT_TYPE='L' " +
								  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
								  "";						
							//System.out.println("LOA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
											
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
							
//							JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='L' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  "and NOM_REV_NO=(SELECT MAX(NOM_REV_NO) FROM FMS7_DAILY_SELLER_NOM_DTL " +
							  "WHERE SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  " AND FGSA_NO="+rset1.getString(1)+"" +
							  " AND CONTRACT_TYPE='L' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy'))";
								
							////System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("SELLER-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
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
						//JHP	daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";
							
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
				   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
				   			   			   "AND A.loa_no="+rset1.getString(3)+" " +
				   			   			   "AND A.loa_rev_no="+rset1.getString(4)+" " +
				   			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								//SB20110915	if(Double.parseDouble(rset4.getString(1))>0)
								if(Double.parseDouble(rset4.getString(1))>=0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq));
							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							
							++trans_count;
						}
					}
				}//End Of Code For LOA Based Buyers ...
				
				
				//Following Code Is For RE-GAS Based Buyers ...
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
				   "NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100'),NVL(A.cargo_ref_no,'0') " +
				   ",NVL(A.SYS_USE_GAS,'0'), NVL(A.BOE_QTY,'0'), " +//SB20110927
				   "NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " + //RG
				   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
				   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
				   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
				   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
				   "AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
				   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							queryString3 = "SELECT NVL(A.clause_cd,'0') FROM FMS7_RE_GAS_CLAUSE_MST A " +
										   "WHERE A.buyer_cd="+rset1.getString(5)+" AND A.clause_cd=5 " +
										   "AND A.re_gas_no="+rset1.getString(1)+" " +
										   "AND A.rev_no="+rset1.getString(2)+"";
							//System.out.println("RE-GAS Clause Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								if(rset3.getString(1).trim().equals("5"))
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("LC");
								}
								else
								{
									daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
								}
							}
							else
							{
								daily_Buyer_Nom_LC_ADV_Flag.add("ADV");
							}
							
							sn_ref_no.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("R");
							daily_Buyer_Nom_Contract_Type.add("RE-GAS");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100));
							total_dcq += Double.parseDouble(rset1.getString(6));
							
							String ALLOCATED_QTY = "0";
							String ALLOCATED_QTY1 = "0";
							//SB20110927
							String CONTRACTED_QTY = nf5.format(Double.parseDouble(rset1.getString(11)) - (Double.parseDouble(rset1.getString(11))*((Double.parseDouble(rset1.getString(10)))/100)));
							
							//////////////////////////RG///////////////////
							
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
						
						//	//System.out.println("size of daily_Buyer_regas_cargo_boe_no==================="+daily_Buyer_regas_cargo_boe_no.size()+"and"+daily_Buyer_regas_cargo_boe_no);
							
							///////////////////////////////////
							
							//^
							//SB20110927 String CONTRACTED_QTY = rset1.getString(7);
							
							/*	queryString = "SELECT SUM(QTY_MMBTU) " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='R' " +
							  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
							  "";
							*///JHP20111015
								queryString = "SELECT SUM(QTY_MMBTU) " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
								  "SN_NO="+rset1.getString(3)+" " +
								  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
								  "AND FGSA_NO="+rset1.getString(1)+" " +
								  "AND CONTRACT_TYPE='R' " +
								  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
								  "";					
							//System.out.println("SAMIK --> RE-GAS Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
											
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
//							JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
							queryString = "SELECT SUM(QTY_MMBTU), MAX(TO_CHAR(GAS_DT,'dd/mm/yyyy')) " +
							  "FROM FMS7_DAILY_SELLER_NOM_DTL WHERE " +
							  "SN_NO="+rset1.getString(3)+" " +
							  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  "AND FGSA_NO="+rset1.getString(1)+" " +
							  "AND CONTRACT_TYPE='R' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
							  "and NOM_REV_NO=(SELECT MAX(NOM_REV_NO) FROM FMS7_DAILY_SELLER_NOM_DTL " +
							  "WHERE SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" " +
							  " AND FGSA_NO="+rset1.getString(1)+"" +
							  " AND CONTRACT_TYPE='R' " +
							  "AND GAS_DT=TO_DATE('"+date_tomorrow+"','dd/mm/yyyy'))";
								
							////System.out.println("SN Already Allocated QTY Query = "+queryString);
							//System.out.println("SELLER-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_SELLER_NOM_DTL: "+queryString);
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
							//System.out.println("SAMIK--> Re-Gas CONTRACTED_QTY = "+CONTRACTED_QTY+",  Re-Gas ALLOCATED_QTY = "+ALLOCATED_QTY);
						//JHP	daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));
							daily_Buyer_Nom_Balance_Qty.add(nf4.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)-Double.parseDouble(ALLOCATED_QTY1)));
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (RE-GAS) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							
							++trans_count;
						}
					}
				}//End Of Code For RE-GAS Based Buyers ...
				
				//System.out.println("********TOTAL DCQ:" +total_dcq);
				master_Transporter_Count.add(""+trans_count);
				master_Transporter_Dcq.add(nf.format(total_dcq));
				
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm.add("");
				}
				
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
			}
			
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
			
			String previous_month_end_date = "";
			String previous_month_start_date = "";
			String month_start_date = "01"+gas_date.trim().substring(2);
			String month = "";
			String year = "";
			
			if(gas_date.trim().length()==10)
			{
				if(gas_date.trim().substring(3,5).equals("01"))
				{
					month = "12";
					year = ""+(Integer.parseInt(gas_date.trim().substring(6))-1);
					previous_month_start_date = "01/"+month+"/"+year;
					previous_month_end_date = "31/"+month+"/"+year;
				}
				else
				{
					if((Integer.parseInt(gas_date.trim().substring(3,5))-1)<10)
					{
						month = "0"+(Integer.parseInt(gas_date.trim().substring(3,5))-1);
						year = ""+gas_date.trim().substring(6);
					}
					else
					{
						month = ""+(Integer.parseInt(gas_date.trim().substring(3,5))-1);
						year = ""+gas_date.trim().substring(6);
					}
					
					previous_month_start_date = "01/"+month+"/"+year;
					
					queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','dd/mm/yyyy')),'dd/mm/yyyy') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						previous_month_end_date = rset.getString(1);
					}
				}
			}
			
			//Following Whole Logic Has been written for Generating Advance Alerts ...
			//This Logic Has been developed By Samik Shah On 22nd June, 2011 ...
			queryString = "SELECT DISTINCT CUSTOMER_CD FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"0":rset.getString(1));
			}
			
			for(int i=0; i<CUST_CD.size(); i++)
			{
				double current_bal_amt = 0;
				double previous_month_bal_amt = 0;
				double month_advance_received = 0;
				double month_allocated_amt = 0;				
				
				if(previous_month_end_date.trim().length()==10)
				{
					queryString = "SELECT NVL(bal_amt,'0') FROM FMS7_MONTHLY_BALANCE " +
								  "WHERE customer_cd="+CUST_CD.elementAt(i)+" AND " +
								  "eff_dt=TO_DATE('"+previous_month_end_date+"','dd/mm/yyyy')";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						previous_month_bal_amt = rset.getDouble(1);
					}
				}
				
				queryString = "SELECT NVL(SUM(pay_amt),'0') FROM FMS7_PAYMENT_DTL " +
							  "WHERE customer_cd="+CUST_CD.elementAt(i)+" AND pay_type='A' AND " +
							  "(pay_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy'))";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_advance_received = rset.getDouble(1);
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='S'";
				//System.out.println("SN (ADV+LC) Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt += rset.getDouble(1);
					//System.out.println("SN (ADV+LC) Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, FMS7_SN_CLAUSE_MST C " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='S' AND A.customer_cd=C.buyer_cd " +
							  "AND A.fgsa_no=C.fgsa_no AND A.sn_no=C.sn_no " +
							  "AND C.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.fgsa_no " +
							  "AND A.sn_no=B.sn_no) AND C.clause_cd=5";
				//System.out.println("SN LC Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt -= rset.getDouble(1);
					//System.out.println("SN LC Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='L'";
				//System.out.println("LOA (ADV+LC) Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt += rset.getDouble(1);
					//System.out.println("LOA (ADV+LC) Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, FMS7_LOA_CLAUSE_MST C " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='L' AND A.customer_cd=C.buyer_cd " +
							  "AND A.fgsa_no=C.tender_no AND A.sn_no=C.loa_no " +
							  "AND C.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.tender_no " +
							  "AND A.sn_no=B.loa_no) AND C.clause_cd=5";
				//System.out.println("LOA LC Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt -= rset.getDouble(1);
					//System.out.println("LOA LC Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='R'";
				//System.out.println("RE-GAS (ADV+LC) Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt += rset.getDouble(1);
					//System.out.println("RE-GAS (ADV+LC) Based Amount = "+nf.format(rset.getDouble(1)));
				}
				
				queryString = "SELECT NVL(SUM(A.cost),'0') FROM DLNG_DAILY_ALLOCATION_DTL A, FMS7_RE_GAS_CLAUSE_MST C " +
							  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
							  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
							  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
							  "AND A.contract_type='R' AND A.customer_cd=C.buyer_cd " +
							  "AND A.fgsa_no=C.re_gas_no " +
							  "AND C.rev_no=(SELECT MAX(B.rev_no) FROM FMS7_RE_GAS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.re_gas_no) " +
							  "AND C.clause_cd=5";
				//System.out.println("RE-GAS LC Based Amount Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_allocated_amt -= rset.getDouble(1);
					//System.out.println("RE-GAS LC Based Amount = "+nf.format(rset.getDouble(1)));
				}
	
				current_bal_amt = previous_month_bal_amt+month_advance_received-month_allocated_amt;
				
				daily_Buyer_Nom_Current_Balance_Amt.add(nf.format(current_bal_amt));
			}
			
			//Size Of All Utilized Vectors ...
		/*	System.out.println("master_Transporter_Cd.size() = "+master_Transporter_Cd.size());
			System.out.println("master_Transporter_Abbr.size() = "+master_Transporter_Abbr.size());
			System.out.println("daily_Transporter_Nom_Cd.size() = "+daily_Transporter_Nom_Cd.size());
			System.out.println("daily_Transporter_Nom_Abbr.size() = "+daily_Transporter_Nom_Abbr.size());
			System.out.println("master_Transporter_Count.size() = "+master_Transporter_Count.size());
			System.out.println("master_Transporter_Dcq.size() = "+master_Transporter_Dcq.size());
			System.out.println("master_Transporter_Mmbtu.size() = "+master_Transporter_Mmbtu.size());
			System.out.println("master_Transporter_Scm.size() = "+master_Transporter_Scm.size());
			
			System.out.println("daily_Buyer_Nom_Fgsa_No.size() = "+daily_Buyer_Nom_Fgsa_No.size());
			System.out.println("daily_Buyer_Nom_Fgsa_Rev_No.size() = "+daily_Buyer_Nom_Fgsa_Rev_No.size());
			System.out.println("daily_Buyer_Nom_Sn_No.size() = "+daily_Buyer_Nom_Sn_No.size());
			System.out.println("daily_Buyer_Nom_Sn_Rev_No.size() = "+daily_Buyer_Nom_Sn_Rev_No.size());
			System.out.println("daily_Buyer_Nom_Cd.size() = "+daily_Buyer_Nom_Cd.size());
			System.out.println("daily_Buyer_Nom_Abbr.size() = "+daily_Buyer_Nom_Abbr.size());
			System.out.println("daily_Buyer_Nom_Dcq.size() = "+daily_Buyer_Nom_Dcq.size());
			System.out.println("daily_Buyer_Nom_Plant_Cd.size() = "+daily_Buyer_Nom_Plant_Cd.size());
			System.out.println("daily_Buyer_Nom_Plant_Abbr.size() = "+daily_Buyer_Nom_Plant_Abbr.size());
			System.out.println("daily_Buyer_Gen_Day_With_Rev_No.size() = "+daily_Buyer_Gen_Day_With_Rev_No.size());
			System.out.println("daily_Buyer_Gen_Day_Time.size() = "+daily_Buyer_Gen_Day_Time.size());
			System.out.println("daily_Buyer_Nom_Plant_Seq_No.size() = "+daily_Buyer_Nom_Plant_Seq_No.size());
			System.out.println("daily_Buyer_Nom_Qty_Mmbtu.size() = "+daily_Buyer_Nom_Qty_Mmbtu.size());
			System.out.println("daily_Buyer_Nom_Qty_Scm.size() = "+daily_Buyer_Nom_Qty_Scm.size());
			System.out.println("daily_Buyer_Nom_Type.size() = "+daily_Buyer_Nom_Type.size());
			
			//CHECVK
			System.out.println("daily_Buyer_Nom_Cd = "+daily_Buyer_Nom_Cd);
			System.out.println("daily_Buyer_Nom_Fgsa_No = "+daily_Buyer_Nom_Fgsa_No);
			System.out.println("daily_Buyer_Nom_Fgsa_Rev_No = "+daily_Buyer_Nom_Fgsa_Rev_No);
			System.out.println("daily_Buyer_Nom_Sn_No = "+daily_Buyer_Nom_Sn_No);
			System.out.println("daily_Buyer_Nom_Sn_Rev_No = "+daily_Buyer_Nom_Sn_Rev_No);
			System.out.println("daily_Buyer_Nom_Type.size() = "+daily_Buyer_Nom_Type);
			*/
			
			//FOLLOWING CODE IS INTRODUCE BY MILAN DALSANIYA ON 17th, OCT, 2011
			//FOLLOWING CODE IS INTENDED FOR THE ALERT ON THE FORM , 
			//LC FOR EACH CUSTOMER IS ABOUT TO EXPIRE IN THREE OR LESS DAYS FOR GIVEN GEN DATE,
			//THEN IT WILL START GIVING ALERT TO THE USER BEFORE THREE DAYS FROM THE DATE OF EXPIRE
			
			/*Vector lc_cust_cd_fin = new Vector();
			Vector lc_fgsa_no_fin = new Vector();
			Vector lc_fgsa_rev_no_fin = new Vector();
			Vector lc_sn_no_fin = new Vector();
			Vector lc_sn_rev_no_fin = new Vector();
			Vector lc_con_typ_fin = new Vector();*/
			
			Vector lc_cust_cd = new Vector();
			Vector lc_fgsa_no = new Vector();
			Vector lc_fgsa_rev_no = new Vector();
			Vector lc_sn_no = new Vector();
			Vector lc_sn_rev_no = new Vector();
			Vector lc_con_typ = new Vector();

			
			for (int i=0; i<daily_Buyer_Nom_Cd.size(); i++)
			{
					/*lc_cust_cd_fin.add(daily_Buyer_Nom_Cd.elementAt(i));
					lc_fgsa_no_fin.add(daily_Buyer_Nom_Fgsa_No.elementAt(i));
					lc_fgsa_rev_no_fin.add(daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i));
					lc_sn_no_fin.add(daily_Buyer_Nom_Sn_No.elementAt(i));
					lc_sn_rev_no_fin.add(daily_Buyer_Nom_Sn_Rev_No.elementAt(i));
					lc_con_typ_fin.add(daily_Buyer_Nom_Type.elementAt(i));*/
					
					lc_cust_cd.add(daily_Buyer_Nom_Cd.elementAt(i));
					lc_fgsa_no.add(daily_Buyer_Nom_Fgsa_No.elementAt(i));
					lc_fgsa_rev_no.add(daily_Buyer_Nom_Fgsa_Rev_No.elementAt(i));
					lc_sn_no.add(daily_Buyer_Nom_Sn_No.elementAt(i));
					lc_sn_rev_no.add(daily_Buyer_Nom_Sn_Rev_No.elementAt(i));
					lc_con_typ.add(daily_Buyer_Nom_Type.elementAt(i));
			}
			
			
			Vector lc_cust_cd1 = new Vector();
			Vector lc_fgsa_no1 = new Vector();
			Vector lc_fgsa_rev_no1 =  new Vector();
			Vector lc_sn_no1 = new Vector();
			Vector lc_sn_rev_no1 = new Vector();
			Vector lc_con_typ1 = new Vector();
			
			//TO REMOVE DUPLICATE VALUES
			Vector cntm = new Vector();
			for (int i = 0; i<lc_sn_no.size(); i++)
			{
				String slc_cust_cd2 = lc_cust_cd.elementAt(i).toString();
				String slc_fgsa_no2 = lc_fgsa_no.elementAt(i).toString();
				String slc_fgsa_rev_no2 = lc_fgsa_rev_no.elementAt(i).toString();
				String slc_sn_no2 = lc_sn_no.elementAt(i).toString();
				String slc_sn_rev_no2 = lc_sn_rev_no.elementAt(i).toString();
				String slc_con_typ2 = lc_con_typ.elementAt(i).toString();
				String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
				for (int j = i+1; j<lc_sn_no.size(); j++)
				{
					String slc_cust_cd1 = lc_cust_cd.elementAt(j).toString();
					String slc_fgsa_no1 = lc_fgsa_no.elementAt(j).toString();
					String slc_fgsa_rev_no1 = lc_fgsa_rev_no.elementAt(j).toString();
					String slc_sn_no1 = lc_sn_no.elementAt(j).toString();
					String slc_sn_rev_no1 = lc_sn_rev_no.elementAt(j).toString();
					String slc_con_typ1 = lc_con_typ.elementAt(j).toString();
					 String combo = slc_cust_cd1+slc_fgsa_no1+slc_fgsa_rev_no1+slc_sn_no1+slc_sn_rev_no1+slc_con_typ1;
					if (combo2.trim().equals(combo.trim()))
					{
						cntm.add(""+j);
					}
				}
				if (!cntm.isEmpty())
				{
					for (int j =0;j<cntm.size();j++)
					{
						lc_cust_cd.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_fgsa_no.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_fgsa_rev_no.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_sn_no.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_sn_rev_no.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						lc_con_typ.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
					}
					cntm.clear();
				}
			}
			
		
			for (int j = 0; j<lc_sn_no.size(); j++)
			{
				String slc_cust_cd2 = lc_cust_cd.elementAt(j).toString();
				String slc_fgsa_no2 = lc_fgsa_no.elementAt(j).toString();
				String slc_fgsa_rev_no2 = lc_fgsa_rev_no.elementAt(j).toString();
				String slc_sn_no2 = lc_sn_no.elementAt(j).toString();
				String slc_sn_rev_no2 = lc_sn_rev_no.elementAt(j).toString();
				String slc_con_typ2 = lc_con_typ.elementAt(j).toString();
				String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
				if (!combo2.trim().equals(""))
				{
					lc_cust_cd1.add(slc_cust_cd2);
					lc_fgsa_no1.add(slc_fgsa_no2);
					lc_fgsa_rev_no1.add(slc_fgsa_rev_no2);
					lc_sn_no1.add(slc_sn_no2);
					lc_sn_rev_no1.add(slc_sn_rev_no2);
					lc_con_typ1.add(slc_con_typ2);
				}
			}
			
			
			Vector LC_FIN_YEAR = new Vector();
			Vector LC_SEQ_NO = new Vector();
			Vector LC_CUST_CD = new Vector();
			Vector LC_END_DT = new Vector();
			
			
			for (int i=0; i<lc_cust_cd1.size(); i++)
			{
				queryString = "SELECT FINANCIAL_YEAR," +
							"LC_SEQ_NO," +
							"CUSTOMER_CD" +
							" FROM FMS7_LC_DTL" +
							" WHERE CUSTOMER_CD = '"+lc_cust_cd1.elementAt(i).toString().trim()+"' AND FGSA_NO = '"+lc_fgsa_no1.elementAt(i).toString().trim()+"'" +
							" AND FGSA_REV_NO = '"+lc_fgsa_rev_no1.elementAt(i).toString().trim()+"' AND SN_NO = '"+lc_sn_no1.elementAt(i).toString().trim()+"'" +
							" AND SN_REV_NO = '"+lc_sn_rev_no1.elementAt(i).toString().trim()+"' AND CONT_TYPE = '"+lc_con_typ1.elementAt(i).toString().trim()+"'";
				rset = stmt.executeQuery(queryString);
				if (rset.next())
				{
					LC_FIN_YEAR.add(rset.getString(1) == null ? "" : rset.getString(1));
					LC_SEQ_NO.add(rset.getString(2) == null ? "" : rset.getString(2));
					LC_CUST_CD.add(rset.getString(3) == null ? "" : rset.getString(3));
					
				}
				else
				{
					LC_FIN_YEAR.add("-");
					LC_SEQ_NO.add("-");
					LC_CUST_CD.add("-");
				}
			}
			
			for (int i=0; i<LC_SEQ_NO.size(); i++)
			{
				if (!LC_SEQ_NO.elementAt(i).toString().trim().equals("") && !LC_SEQ_NO.elementAt(i).toString().trim().equals("-"))
				{
					queryString = "SELECT LC_SEQ_NO," +
								"CUSTOMER_CD," +
								" TO_CHAR(END_DATE,'DD/MM/YYYY')" +
								" FROM FMS7_LC_MST" +
								" WHERE CUSTOMER_CD = '"+LC_CUST_CD.elementAt(i).toString().trim()+"'" +
								" AND FINANCIAL_YEAR = '"+LC_FIN_YEAR.elementAt(i).toString().trim()+"' " +
								" AND LC_SEQ_NO = '"+LC_SEQ_NO.elementAt(i).toString().trim()+"'";
					rset = stmt.executeQuery(queryString);
					if (rset.next())
					{
						LC_END_DT.add(rset.getString(3) == null ? "" : rset.getString(3));
						
					}
					else
					{
						LC_END_DT.add("-");
					}
				}
				else
				{
					LC_END_DT.add("-");
				}
			}
			
			for (int i =0; i<lc_con_typ.size(); i++)
			{
				LC_EXP_FLG.add(lc_con_typ.elementAt(i));
				LC_DAY_FLG.add(lc_con_typ.elementAt(i));
				LC_END_DT_FIN.add(lc_con_typ.elementAt(i));
			}
			
			boolean exp_flg = false;
			
			lc_cust_cd = daily_Buyer_Nom_Cd;
			lc_fgsa_no = daily_Buyer_Nom_Fgsa_No;
			lc_fgsa_rev_no = daily_Buyer_Nom_Fgsa_Rev_No;
			lc_sn_no = daily_Buyer_Nom_Sn_No;
			lc_sn_rev_no = daily_Buyer_Nom_Sn_Rev_No;
			lc_con_typ = daily_Buyer_Nom_Type;
			
			
			int day = 0;
			String yes ="NO";
			
			String myFormatString = "dd/MM/yyyy"; // for example
			SimpleDateFormat df = new SimpleDateFormat(myFormatString);
			
			//System.out.println("gen date : "+gen_date);
			for (int i = 0; i<LC_SEQ_NO.size(); i++)
			{
				if (!LC_SEQ_NO.elementAt(i).toString().trim().equals("-"))
				{
					
					Date end_dt = df.parse(LC_END_DT.elementAt(i).toString().trim());
					Date g_dt = df.parse(gen_date);
					int tmp = end_dt.compareTo(g_dt);
					if(tmp > 0)
					{
						//System.out.println("first date is later : "+end_dt+" "+g_dt);
						queryString = "SELECT TO_DATE('"+gen_date+"','DD/MM/YYYY')+3 FROM DUAL";
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							Date genration_dt = rset.getDate(1);
							if (genration_dt.equals(end_dt))
							{
								//System.out.println("LC end after 3 days : "+end_dt);
								exp_flg = true;
								day = 3;
								yes = "YES";
							}
							else if(genration_dt.compareTo(end_dt)>0)
							{
								queryString = "SELECT TO_DATE('"+gen_date+"','DD/MM/YYYY')+2 FROM DUAL";
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									genration_dt = rset.getDate(1);
								}
								if (genration_dt.equals(end_dt))
								{
									//System.out.println("LC end after 2 days : "+end_dt);
									exp_flg = true;
									day = 2;
									yes = "YES";
								}
								else if(genration_dt.compareTo(end_dt)>0)
								{
									queryString = "SELECT TO_DATE('"+gen_date+"','DD/MM/YYYY')+1 FROM DUAL";
									rset = stmt.executeQuery(queryString);
									if(rset.next())
									{
										genration_dt = rset.getDate(1);
									}
									if (genration_dt.equals(end_dt))
									{
										//System.out.println("LC end after 1 days : "+end_dt);
										exp_flg = true;
										day = 1;
										yes = "Y";
									}
								}
							}
								
						}
					}
					if(exp_flg)
					{
						String slc_cust_cd2 = lc_cust_cd1.elementAt(i).toString();
						String slc_fgsa_no2 = lc_fgsa_no1.elementAt(i).toString();
						String slc_fgsa_rev_no2 = lc_fgsa_rev_no1.elementAt(i).toString();
						String slc_sn_no2 = lc_sn_no1.elementAt(i).toString();
						String slc_sn_rev_no2 = lc_sn_rev_no1.elementAt(i).toString();
						String slc_con_typ2 = lc_con_typ1.elementAt(i).toString();
						
						String slc_end_dt = LC_END_DT.elementAt(i).toString();
						
						//System.out.println("test if i : "+i);
						String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
						for (int j = 0; j<lc_sn_no.size(); j++)
						{
							
							String slc_cust_cd1 = lc_cust_cd.elementAt(j).toString();
							String slc_fgsa_no1 = lc_fgsa_no.elementAt(j).toString();
							String slc_fgsa_rev_no1 = lc_fgsa_rev_no.elementAt(j).toString();
							String slc_sn_no1 = lc_sn_no.elementAt(j).toString();
							String slc_sn_rev_no1 = lc_sn_rev_no.elementAt(j).toString();
							String slc_con_typ1 = lc_con_typ.elementAt(j).toString();
							String combo = slc_cust_cd1+slc_fgsa_no1+slc_fgsa_rev_no1+slc_sn_no1+slc_sn_rev_no1+slc_con_typ1;
							if (combo2.trim().equals(combo.trim()))
							{
								//System.out.println("test if j : "+j);
								LC_EXP_FLG.setElementAt(yes,j);
								LC_DAY_FLG.setElementAt(""+day,j);
								LC_END_DT_FIN.setElementAt(slc_end_dt,j);
							}
						}
						exp_flg = false;
						day = 0;
						yes = "NO";
					}
					else
					{
						String slc_cust_cd2 = lc_cust_cd1.elementAt(i).toString();
						String slc_fgsa_no2 = lc_fgsa_no1.elementAt(i).toString();
						String slc_fgsa_rev_no2 = lc_fgsa_rev_no1.elementAt(i).toString();
						String slc_sn_no2 = lc_sn_no1.elementAt(i).toString();
						String slc_sn_rev_no2 = lc_sn_rev_no1.elementAt(i).toString();
						String slc_con_typ2 = lc_con_typ1.elementAt(i).toString();
						
						//System.out.println("test if i : "+i);
						String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
						for (int j = 0; j<lc_sn_no.size(); j++)
						{
							
							String slc_cust_cd1 = lc_cust_cd.elementAt(j).toString();
							String slc_fgsa_no1 = lc_fgsa_no.elementAt(j).toString();
							String slc_fgsa_rev_no1 = lc_fgsa_rev_no.elementAt(j).toString();
							String slc_sn_no1 = lc_sn_no.elementAt(j).toString();
							String slc_sn_rev_no1 = lc_sn_rev_no.elementAt(j).toString();
							String slc_con_typ1 = lc_con_typ.elementAt(j).toString();
							String combo = slc_cust_cd1+slc_fgsa_no1+slc_fgsa_rev_no1+slc_sn_no1+slc_sn_rev_no1+slc_con_typ1;
							if (combo2.trim().equals(combo.trim()))
							{
								//System.out.println("test if j : "+j);
								LC_EXP_FLG.setElementAt("-",j);
								LC_DAY_FLG.setElementAt("-",j);
								LC_END_DT_FIN.setElementAt("-",j);
							}
						}
					}
				}
				else
				{
					String slc_cust_cd2 = lc_cust_cd1.elementAt(i).toString();
					String slc_fgsa_no2 = lc_fgsa_no1.elementAt(i).toString();
					String slc_fgsa_rev_no2 = lc_fgsa_rev_no1.elementAt(i).toString();
					String slc_sn_no2 = lc_sn_no1.elementAt(i).toString();
					String slc_sn_rev_no2 = lc_sn_rev_no1.elementAt(i).toString();
					String slc_con_typ2 = lc_con_typ1.elementAt(i).toString();
					//System.out.println("test else i : "+i);
					String combo2 = slc_cust_cd2+slc_fgsa_no2+slc_fgsa_rev_no2+slc_sn_no2+slc_sn_rev_no2+slc_con_typ2;
					for (int j = 0; j<lc_sn_no.size(); j++)
					{
						
						String slc_cust_cd1 = lc_cust_cd.elementAt(j).toString();
						String slc_fgsa_no1 = lc_fgsa_no.elementAt(j).toString();
						String slc_fgsa_rev_no1 = lc_fgsa_rev_no.elementAt(j).toString();
						String slc_sn_no1 = lc_sn_no.elementAt(j).toString();
						String slc_sn_rev_no1 = lc_sn_rev_no.elementAt(j).toString();
						String slc_con_typ1 = lc_con_typ.elementAt(j).toString();
						 String combo = slc_cust_cd1+slc_fgsa_no1+slc_fgsa_rev_no1+slc_sn_no1+slc_sn_rev_no1+slc_con_typ1;
						if (combo2.trim().equals(combo.trim()))
						{
							//System.out.println("test else j : "+j);
							LC_EXP_FLG.setElementAt("-",j);
							LC_DAY_FLG.setElementAt("-",j);
							LC_END_DT_FIN.setElementAt("-",j);
						}
					}
					
				}
			}
			//CODE FOR LC EXPIRE DATE ALERT ENDS HERE
			
		/*	System.out.println("1");
			System.out.println("daily_Buyer_Nom_Cd = "+daily_Buyer_Nom_Cd);
			System.out.println("daily_Buyer_Nom_Fgsa_No = "+daily_Buyer_Nom_Fgsa_No);
			System.out.println("daily_Buyer_Nom_Fgsa_Rev_No = "+daily_Buyer_Nom_Fgsa_Rev_No);
			System.out.println("daily_Buyer_Nom_Sn_No = "+daily_Buyer_Nom_Sn_No);
			System.out.println("daily_Buyer_Nom_Sn_Rev_No = "+daily_Buyer_Nom_Sn_Rev_No);
			System.out.println("daily_Buyer_Nom_Type.size() = "+daily_Buyer_Nom_Type);
			System.out.println("LC_EXP_FLG = "+LC_EXP_FLG);
			System.out.println("LC_DAY_FLG = "+LC_DAY_FLG);
			System.out.println("LC_END_DT_FIN = "+LC_END_DT_FIN);
			
			System.out.println("2");
			System.out.println("daily_Buyer_Nom_Cd = "+lc_cust_cd1);
			System.out.println("daily_Buyer_Nom_Fgsa_No = "+lc_fgsa_no1);
			System.out.println("daily_Buyer_Nom_Fgsa_Rev_No = "+lc_fgsa_rev_no1);
			System.out.println("daily_Buyer_Nom_Sn_No = "+lc_sn_no1);
			System.out.println("daily_Buyer_Nom_Sn_Rev_No = "+lc_sn_rev_no1);
			System.out.println("daily_Buyer_Nom_Type.size() = "+lc_con_typ1);
			
			System.out.println("3");
			System.out.println("daily_Buyer_Nom_Cd = "+lc_cust_cd);
			System.out.println("daily_Buyer_Nom_Fgsa_No = "+lc_fgsa_no);
			System.out.println("daily_Buyer_Nom_Fgsa_Rev_No = "+lc_fgsa_rev_no);
			System.out.println("daily_Buyer_Nom_Sn_No = "+lc_sn_no);
			System.out.println("daily_Buyer_Nom_Sn_Rev_No = "+lc_sn_rev_no);
			System.out.println("daily_Buyer_Nom_Type.size() = "+lc_con_typ);
			
			System.out.println("4");
			System.out.println("LC_FIN_YEAR = "+LC_FIN_YEAR);
			System.out.println("LC_SEQ_NO = "+LC_SEQ_NO);
			System.out.println("LC_CUST_CD = "+LC_CUST_CD);
			System.out.println("LC_END_DT = "+LC_END_DT);*/
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
		
	}//End Of Method fetchDailyBuyerNomDetails() ...
	
	
	//Following Function Only Shows Buyer Nominated Records Under Daily Seller Nomination Form ...
	//Following Function Has Been Introduced By Samik Shah On 18th May, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 25th August, 2010 ...
	public void fetchDailySellerNomDetails() 
	{
		methodName = "fetchDailySellerNomDetails()";
		try 
		{
			double final_daily_buyer_mmbtu = 0;
			
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' '), TRANSPORTER_NAME FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd_Seller_Nom.add(rset.getString(1));
				master_Transporter_Abbr_Seller_Nom.add(rset.getString(2));
				VTransporter_Name.add(rset.getString(3)); //SB20181005
			}
			
			for(int i=0;i<master_Transporter_Cd_Seller_Nom.size();i++)
			{
				double total_buyer_mmbtu = 0;
				
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
				
				//Following Code Is For SN Based SELLERS ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),A.sn_ref_no " +
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' " +
							   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   "AND A.fgsa_rev_no=B.fgsa_rev_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd_Seller_Nom.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
									   "AND A.sn_rev_no="+rset1.getString(4)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Seller_Nom_Plant_Abbr.add("PLANT1");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
					
								daily_Seller_Nom_Mapping_Id.add(""); //NB20141029 ADDED FOR LTCORA
								daily_Seller_Nom_Fgsa_No.add(rset1.getString(1));
								daily_Seller_Nom_Fgsa_Rev_No.add(rset1.getString(2));
								daily_Seller_Nom_Sn_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(4));
								daily_Seller_Nom_Cd.add(rset1.getString(5));
								String sn_ref_number = rset1.getString(7)==null?"":rset1.getString(7);
								if(!sn_ref_number.trim().equals(""))
								{
									sn_ref_no.add(sn_ref_number);
								}
								else
								{
									sn_ref_no.add("");
								}
								daily_Seller_Nom_Type.add("S");
								daily_Seller_Nom_Contract_Type.add("SN");
								
									///////////////////////////RG//////////////
								
								daily_Seller_regas_cargo_boe_dt.add("");
								daily_Seller_regas_cargo_boe_no.add("");
								
								////////////////////////////////////////
								
								
								queryString5 = "SELECT NVL(A.customer_abbr,' '), CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
									Vdaily_Buyer_Nom_Name.add(rset5.getString(2));
								}
								else
								{
									daily_Seller_Nom_Abbr.add(" ");
									Vdaily_Buyer_Nom_Name.add("*");
								}
								
								queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
								   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
								//System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									//SB20110915 if(Double.parseDouble(rset5.getString(1))>0)
									if(Double.parseDouble(rset5.getString(1))>=0)
									{
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));
										total_dcq += Double.parseDouble(rset5.getString(1));
									}
									else
									{
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
										total_dcq += Double.parseDouble(rset1.getString(6));
									}
								}
								else
								{
									daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
								}
								
								daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
								daily_Transporter_Nom_Abbr_Seller_Nom.add(""+master_Transporter_Abbr_Seller_Nom.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
											   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
								   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
											   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
									daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
								}
								else
								{
									daily_Seller_Gen_Day_With_Rev_No.add("");
									daily_Seller_Gen_Day_Time.add("");
									daily_Seller_Nom_Plant_Seq_No.add("0");
									daily_Seller_Nom_Qty_Mmbtu.add("");
									daily_Seller_Nom_Qty_Scm.add("");
								}
								
								daily_Seller_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
								daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
														
								++trans_count;
							}
						}
					}
				}//End Of Code For SN Based SELLERS ...
				
				//Following Code Is For LOA Based SELLERS ...
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),A.loa_ref_no " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' " +
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd_Seller_Nom.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				//System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
									   "ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Seller_Nom_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
			
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Mapping_Id.add(""); //NB20141029 ADDED FOR LTCORA
								daily_Seller_Nom_Fgsa_No.add(rset1.getString(1));
								daily_Seller_Nom_Fgsa_Rev_No.add("0");
								daily_Seller_Nom_Sn_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(4));
								daily_Seller_Nom_Cd.add(rset1.getString(5));
								String sn_ref_number = rset1.getString(7)==null?"":rset1.getString(7);
								if(!sn_ref_number.trim().equals(""))
								{
									sn_ref_no.add(sn_ref_number);
								}
								else
								{
									sn_ref_no.add("");
								}
								daily_Seller_Nom_Type.add("L");
								daily_Seller_Nom_Contract_Type.add("LOA");
								
								///////////////////////////RG//////////////
								
								daily_Seller_regas_cargo_boe_dt.add("");
								daily_Seller_regas_cargo_boe_no.add("");
								
								////////////////////////////////////////
								
								queryString5 = "SELECT NVL(A.customer_abbr,' '), CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
									Vdaily_Buyer_Nom_Name.add(rset5.getString(2));
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
									Vdaily_Buyer_Nom_Name.add("*");
								}
								
								queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
					   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
					   			   			   "AND A.loa_no="+rset1.getString(3)+" " +
					   			   			   "AND A.loa_rev_no="+rset1.getString(4)+" " +
					   			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
					   			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
								//System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									//SB20110915 if(Double.parseDouble(rset5.getString(1))>0)
									if(Double.parseDouble(rset5.getString(1))>=0)
									{
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));
										total_dcq += Double.parseDouble(rset5.getString(1));
									}
									else
									{
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
										total_dcq += Double.parseDouble(rset1.getString(6));
									}
								}
								else
								{
									daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
								}
															
								daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
								daily_Transporter_Nom_Abbr_Seller_Nom.add(""+master_Transporter_Abbr_Seller_Nom.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
											   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
								   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
											   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
									daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
								}
								else
								{
									daily_Seller_Gen_Day_With_Rev_No.add("");
									daily_Seller_Gen_Day_Time.add("");
									daily_Seller_Nom_Plant_Seq_No.add("0");
									daily_Seller_Nom_Qty_Mmbtu.add("");
									daily_Seller_Nom_Qty_Scm.add("");
								}
								
								daily_Seller_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
								daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
															
								++trans_count;
							}
						}
					}
				}//End Of Code For LOA Based SELLERS ...
				
				//Following Code Is For RE-GAS Based SELLERS ...
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0')," +
								"NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
							   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
							   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
							   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
							   "AND C.transporter_cd='"+master_Transporter_Cd_Seller_Nom.elementAt(i)+"' " +
							   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query (RE-GAS) = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Seller_Nom_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
			
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (RE-GAS) = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Mapping_Id.add(""); //NB20141029 ADDED FOR LTCORA
								daily_Seller_Nom_Fgsa_No.add(rset1.getString(1));
								daily_Seller_Nom_Fgsa_Rev_No.add(rset1.getString(2));
								daily_Seller_Nom_Sn_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_Rev_No.add("0");
								daily_Seller_Nom_Cd.add(rset1.getString(5));
								sn_ref_no.add("");
								daily_Seller_Nom_Type.add("R");
								daily_Seller_Nom_Contract_Type.add("RE-GAS");
								daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								
								/////////////////////////RG////////////
								daily_Seller_regas_cargo_boe_no.add(rset1.getString(7)==null?"":rset1.getString(7));
								daily_Seller_regas_cargo_boe_dt.add(rset1.getString(8)==null?"":rset1.getString(8));
								
							//	//System.out.println("Size of daily_Seller_Nom_Contract_Type====================================="+daily_Seller_Nom_Contract_Type.size()+"and"+daily_Seller_Nom_Contract_Type);
							//	//System.out.println("Size of daily_Seller_regas_cargo_boe_no"+daily_Seller_regas_cargo_boe_no.size()+"and"+daily_Seller_regas_cargo_boe_no);
							//	//System.out.println("Size of daily_Seller_regas_cargo_boe_dt"+daily_Seller_regas_cargo_boe_dt.size()+"and"+daily_Seller_regas_cargo_boe_dt);
								
								//////////////////////////////////////
								
								queryString5 = "SELECT NVL(A.customer_abbr,' '), CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
									Vdaily_Buyer_Nom_Name.add(rset5.getString(2));
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
									Vdaily_Buyer_Nom_Name.add("*");
								}
								
								daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
								daily_Transporter_Nom_Abbr_Seller_Nom.add(""+master_Transporter_Abbr_Seller_Nom.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
											   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
								   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
											   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (RE-GAS) = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
									daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
								}
								else
								{
									daily_Seller_Gen_Day_With_Rev_No.add("");
									daily_Seller_Gen_Day_Time.add("");
									daily_Seller_Nom_Plant_Seq_No.add("0");
									daily_Seller_Nom_Qty_Mmbtu.add("");
									daily_Seller_Nom_Qty_Scm.add("");
								}
								
								daily_Seller_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
								daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
															
								++trans_count;
							}
						}
					}
				}//End Of Code For RE-GAS Based SELLERS ...
				
				
//				//Following Code Is For LTCORA Based SELLERS ... NB20141029
				queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0')," +
				"NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
			   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, FMS8_LNG_REGAS_TRANS_DTL C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
			   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
			   "AND A.mapping_id=B.mapping_id AND A.mapping_id=C.mapping_id AND B.mapping_id=C.mapping_id " +
			   "AND C.trans_cd='"+master_Transporter_Cd_Seller_Nom.elementAt(i)+"' " +
			   "AND B.CN_NO='0' AND B.CN_REV_NO='0'" +
			   "ORDER BY A.mapping_id,A.cargo_seq_no";

				//System.out.println("Transporter, Customer, LTCORA, & Cargo Contract Wise Fetch Query (LTCORA) = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)==null?"":rset1.getString(1);
					String tempmap_id[]=map_id.split("-");
					
					String temp_regas_no=tempmap_id[1];
					String temp_regas_rev_no=tempmap_id[2];
					String temp_cust_cd=tempmap_id[0];
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_no,'1') FROM FMS8_LNG_REGAS_PLANT_DTL A " +
									   "WHERE A.mapping_id='"+rset1.getString(1)+"' " +
									   "ORDER BY A.plant_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (LTCORA) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Seller_Nom_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+temp_regas_no+" " +
							   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND A.contract_type='T' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   "AND B.fgsa_no="+temp_regas_no+" " +
							   			   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND B.contract_type='T' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
				
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (LTCORA) = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Mapping_Id.add(rset1.getString(1));
								daily_Seller_Nom_Fgsa_No.add(temp_regas_no);
								daily_Seller_Nom_Fgsa_Rev_No.add(temp_regas_rev_no);
								daily_Seller_Nom_Sn_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_Rev_No.add("0");
								daily_Seller_Nom_Cd.add(temp_cust_cd);
								sn_ref_no.add("");
								daily_Seller_Nom_Type.add("T");
								daily_Seller_Nom_Contract_Type.add("LTCORA");
								daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								
								/////////////////////////RG////////////
								daily_Seller_regas_cargo_boe_no.add(rset1.getString(7)==null?"":rset1.getString(7));
								daily_Seller_regas_cargo_boe_dt.add(rset1.getString(8)==null?"":rset1.getString(8));
								
							//	//System.out.println("Size of daily_Seller_Nom_Contract_Type====================================="+daily_Seller_Nom_Contract_Type.size()+"and"+daily_Seller_Nom_Contract_Type);
							//	//System.out.println("Size of daily_Seller_regas_cargo_boe_no"+daily_Seller_regas_cargo_boe_no.size()+"and"+daily_Seller_regas_cargo_boe_no);
							//	//System.out.println("Size of daily_Seller_regas_cargo_boe_dt"+daily_Seller_regas_cargo_boe_dt.size()+"and"+daily_Seller_regas_cargo_boe_dt);
								
								//////////////////////////////////////
								
								queryString5 = "SELECT NVL(A.customer_abbr,' '), CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd="+temp_cust_cd+"";
								//System.out.println("Buyer Abbreviation Fetch Query (LTCORA) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
									Vdaily_Buyer_Nom_Name.add(rset5.getString(2));
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
									Vdaily_Buyer_Nom_Name.add("*");
								}
								
								daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
								daily_Transporter_Nom_Abbr_Seller_Nom.add(""+master_Transporter_Abbr_Seller_Nom.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
											   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.fgsa_no="+temp_regas_no+" " +
								   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
								   			   "AND A.contract_type='T' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   "AND B.fgsa_no="+temp_regas_no+" " +
											   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
											   "AND B.contract_type='T' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (LTCORA) = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
									daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
								}
								else
								{
									daily_Seller_Gen_Day_With_Rev_No.add("");
									daily_Seller_Gen_Day_Time.add("");
									daily_Seller_Nom_Plant_Seq_No.add("0");
									daily_Seller_Nom_Qty_Mmbtu.add("");
									daily_Seller_Nom_Qty_Scm.add("");
								}
								
								daily_Seller_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
								daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
															
								++trans_count;
							}
						}
					}
				}//End Of Code For LTCORA Based SELLERS ...

//				//Following Code Is For CN Based SELLERS ... NB20141029
				queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0')," +
				"NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
			   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, FMS8_LNG_REGAS_TRANS_DTL C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
			   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
			   "AND A.mapping_id=B.mapping_id AND A.mapping_id=C.mapping_id AND B.mapping_id=C.mapping_id " +
			   "AND C.trans_cd='"+master_Transporter_Cd_Seller_Nom.elementAt(i)+"' " +
			   "AND B.CN_NO!='0' " +
			   "ORDER BY A.mapping_id,A.cargo_seq_no";

				//System.out.println("Transporter, Customer, CN, & Cargo Contract Wise Fetch Query (CN) = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					String map_id=rset1.getString(1)==null?"":rset1.getString(1);
					String tempmap_id[]=map_id.split("-");
					
					String temp_regas_no=tempmap_id[3];
					String temp_regas_rev_no=tempmap_id[4];
					String temp_cust_cd=tempmap_id[0];
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_no,'1') FROM FMS8_LNG_REGAS_PLANT_DTL A " +
									   "WHERE A.mapping_id='"+rset1.getString(1)+"' " +
									   "ORDER BY A.plant_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (CN) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Seller_Nom_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+temp_regas_no+" " +
							   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND A.contract_type='C' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND " +
							   			   "MAPPING_ID='"+rset1.getString(1)+"' AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   "AND B.fgsa_no="+temp_regas_no+" " +
							   			   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND B.contract_type='C' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+" " +
							   			   "AND MAPPING_ID='"+rset1.getString(1)+"')";
				
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (CN) = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query (CN) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Mapping_Id.add(rset1.getString(1));
								daily_Seller_Nom_Fgsa_No.add(temp_regas_no);
								daily_Seller_Nom_Fgsa_Rev_No.add(temp_regas_rev_no);
								daily_Seller_Nom_Sn_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_Rev_No.add("0");
								daily_Seller_Nom_Cd.add(temp_cust_cd);
								sn_ref_no.add("");
								daily_Seller_Nom_Type.add("C");
								daily_Seller_Nom_Contract_Type.add("CN");
								daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								
								/////////////////////////RG////////////
								daily_Seller_regas_cargo_boe_no.add(rset1.getString(7)==null?"":rset1.getString(7));
								daily_Seller_regas_cargo_boe_dt.add(rset1.getString(8)==null?"":rset1.getString(8));
								
							//	//System.out.println("Size of daily_Seller_Nom_Contract_Type====================================="+daily_Seller_Nom_Contract_Type.size()+"and"+daily_Seller_Nom_Contract_Type);
							//	//System.out.println("Size of daily_Seller_regas_cargo_boe_no"+daily_Seller_regas_cargo_boe_no.size()+"and"+daily_Seller_regas_cargo_boe_no);
							//	//System.out.println("Size of daily_Seller_regas_cargo_boe_dt"+daily_Seller_regas_cargo_boe_dt.size()+"and"+daily_Seller_regas_cargo_boe_dt);
								
								//////////////////////////////////////
								
								queryString5 = "SELECT NVL(A.customer_abbr,' '), CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd="+temp_cust_cd+"";
								//System.out.println("Buyer Abbreviation Fetch Query (CN) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
									Vdaily_Buyer_Nom_Name.add(rset5.getString(2));
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
									Vdaily_Buyer_Nom_Name.add("*");
								}
								
								daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
								daily_Transporter_Nom_Abbr_Seller_Nom.add(""+master_Transporter_Abbr_Seller_Nom.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
											   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.fgsa_no="+temp_regas_no+" " +
								   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
								   			   "AND A.contract_type='C' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" " +
								   			   "AND MAPPING_ID='"+rset1.getString(1)+"' AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   "AND B.fgsa_no="+temp_regas_no+" " +
											   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
											   "AND B.contract_type='C' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+" " +
											   "AND MAPPING_ID='"+rset1.getString(1)+"')";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (CN) = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
									daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
								}
								else
								{
									daily_Seller_Gen_Day_With_Rev_No.add("");
									daily_Seller_Gen_Day_Time.add("");
									daily_Seller_Nom_Plant_Seq_No.add("0");
									daily_Seller_Nom_Qty_Mmbtu.add("");
									daily_Seller_Nom_Qty_Scm.add("");
								}
								
								daily_Seller_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
								daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
															
								++trans_count;
							}
						}
					}
				}//End Of Code For CN Based SELLERS ...
				
				
				
				//System.out.println("trans_count--------------->"+trans_count);
				master_Transporter_Count_Seller_Nom.add(""+trans_count);
				master_Transporter_Dcq_Seller_Nom.add(nf.format(total_dcq));
				
				if(total_buyer_mmbtu>0)
				{
					master_Transporter_Mmbtu.add(nf.format(total_buyer_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu.add("");
				}
				
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu_Seller_Nom.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu_Seller_Nom.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm_Seller_Nom.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm_Seller_Nom.add("");
				}
				
				daily_Transporter_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
				daily_Transporter_Obligation_Qty.add(""); //Remaining For Logic Development ...
				
				final_daily_buyer_mmbtu += total_buyer_mmbtu;
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
			}
			
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
			
			daily_Total_Obligation_Qty = ""; //Remaining For Logic Development ...
			daily_Total_Asking_Rate_Qty = ""; //Remaining For Logic Development ...
						
			//Size Of All Utilized Vectors ...
			/*System.out.println("master_Transporter_Cd_Seller_Nom.size() = "+master_Transporter_Cd_Seller_Nom.size());
			System.out.println("master_Transporter_Abbr_Seller_Nom.size() = "+master_Transporter_Abbr_Seller_Nom.size());
			System.out.println("daily_Transporter_Nom_Cd_Seller_Nom.size() = "+daily_Transporter_Nom_Cd_Seller_Nom.size());
			System.out.println("daily_Transporter_Nom_Abbr_Seller_Nom.size() = "+daily_Transporter_Nom_Abbr_Seller_Nom.size());
			System.out.println("master_Transporter_Count_Seller_Nom.size() = "+master_Transporter_Count_Seller_Nom.size());
			System.out.println("master_Transporter_Dcq_Seller_Nom.size() = "+master_Transporter_Dcq_Seller_Nom.size());
			System.out.println("master_Transporter_Mmbtu_Seller_Nom.size() = "+master_Transporter_Mmbtu_Seller_Nom.size());
			System.out.println("master_Transporter_Scm_Seller_Nom.size() = "+master_Transporter_Scm_Seller_Nom.size());
			
			System.out.println("daily_Seller_Nom_Fgsa_No.size() = "+daily_Seller_Nom_Fgsa_No.size());
			System.out.println("daily_Seller_Nom_Fgsa_Rev_No.size() = "+daily_Seller_Nom_Fgsa_Rev_No.size());
			System.out.println("daily_Seller_Nom_Sn_No.size() = "+daily_Seller_Nom_Sn_No.size());
			System.out.println("daily_Seller_Nom_Sn_Rev_No.size() = "+daily_Seller_Nom_Sn_Rev_No.size());
			System.out.println("daily_Seller_Nom_Cd.size() = "+daily_Seller_Nom_Cd.size());
			System.out.println("daily_Seller_Nom_Abbr.size() = "+daily_Seller_Nom_Abbr.size());
			System.out.println("daily_Seller_Nom_Dcq.size() = "+daily_Seller_Nom_Dcq.size());
			System.out.println("daily_Seller_Nom_Plant_Cd.size() = "+daily_Seller_Nom_Plant_Cd.size());
			System.out.println("daily_Seller_Nom_Plant_Abbr.size() = "+daily_Seller_Nom_Plant_Abbr.size());
			System.out.println("daily_Seller_Gen_Day_With_Rev_No.size() = "+daily_Seller_Gen_Day_With_Rev_No.size());
			System.out.println("daily_Seller_Gen_Day_Time.size() = "+daily_Seller_Gen_Day_Time.size());
			System.out.println("daily_Seller_Nom_Plant_Seq_No.size() = "+daily_Seller_Nom_Plant_Seq_No.size());
			System.out.println("daily_Seller_Nom_Qty_Mmbtu.size() = "+daily_Seller_Nom_Qty_Mmbtu.size());
			System.out.println("daily_Seller_Nom_Qty_Scm.size() = "+daily_Seller_Nom_Qty_Scm.size());
			System.out.println("daily_Seller_Nom_Type.size() = "+daily_Seller_Nom_Type.size());*/
			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailySellerNomDetails() ...
	
	public void fetchDailySellerNomDetails_OLD() 
	{
		methodName = "fetchDailySellerNomDetails()";
		try 
		{
			double final_daily_buyer_mmbtu = 0;
			
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd_Seller_Nom.add(rset.getString(1));
				master_Transporter_Abbr_Seller_Nom.add(rset.getString(2));
			}
			
			for(int i=0;i<master_Transporter_Cd_Seller_Nom.size();i++)
			{
				double total_buyer_mmbtu = 0;
				
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
				
				//Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),A.sn_ref_no " +
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' " +
							   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   "AND A.fgsa_rev_no=B.fgsa_rev_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd_Seller_Nom.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
									   "AND A.sn_rev_no="+rset1.getString(4)+" ORDER BY A.plant_seq_no";
						System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Seller_Nom_Plant_Abbr.add("PLANT1");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								System.out.println("Customer Plant Name Fetch Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
					
								daily_Seller_Nom_Fgsa_No.add(rset1.getString(1));
								daily_Seller_Nom_Fgsa_Rev_No.add(rset1.getString(2));
								daily_Seller_Nom_Sn_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(4));
								daily_Seller_Nom_Cd.add(rset1.getString(5));
								String sn_ref_number = rset1.getString(7)==null?"":rset1.getString(7);
								if(!sn_ref_number.trim().equals(""))
								{
									sn_ref_no.add(sn_ref_number);
								}
								else
								{
									sn_ref_no.add("");
								}
								daily_Seller_Nom_Type.add("S");
								daily_Seller_Nom_Contract_Type.add("SN");
								
									///////////////////////////RG//////////////
								
								daily_Seller_regas_cargo_boe_dt.add("");
								daily_Seller_regas_cargo_boe_no.add("");
								
								////////////////////////////////////////
								
								
								queryString5 = "SELECT NVL(A.customer_abbr,' '), CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+"";
								System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
									Vdaily_Buyer_Nom_Name.add(rset5.getString(2));
								}
								else
								{
									daily_Seller_Nom_Abbr.add(" ");
									daily_Seller_Nom_Abbr.add("*");
								}
								
								queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
								   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
								System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									//SB20110915 if(Double.parseDouble(rset5.getString(1))>0)
									if(Double.parseDouble(rset5.getString(1))>=0)
									{
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));
										total_dcq += Double.parseDouble(rset5.getString(1));
									}
									else
									{
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
										total_dcq += Double.parseDouble(rset1.getString(6));
									}
								}
								else
								{
									daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
								}
								
								daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
								daily_Transporter_Nom_Abbr_Seller_Nom.add(""+master_Transporter_Abbr_Seller_Nom.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
											   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
								   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
											   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
									daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
								}
								else
								{
									daily_Seller_Gen_Day_With_Rev_No.add("");
									daily_Seller_Gen_Day_Time.add("");
									daily_Seller_Nom_Plant_Seq_No.add("0");
									daily_Seller_Nom_Qty_Mmbtu.add("");
									daily_Seller_Nom_Qty_Scm.add("");
								}
								
								daily_Seller_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
								daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
														
								++trans_count;
							}
						}
					}
				}//End Of Code For SN Based Buyers ...
				
				//Following Code Is For LOA Based Buyers ...
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),A.loa_ref_no " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' " +
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd_Seller_Nom.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
									   "ORDER BY A.plant_seq_no";
						System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Seller_Nom_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
			
							System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								System.out.println("Customer Plant Name Fetch Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Fgsa_No.add(rset1.getString(1));
								daily_Seller_Nom_Fgsa_Rev_No.add("0");
								daily_Seller_Nom_Sn_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_Rev_No.add(rset1.getString(4));
								daily_Seller_Nom_Cd.add(rset1.getString(5));
								String sn_ref_number = rset1.getString(7)==null?"":rset1.getString(7);
								if(!sn_ref_number.trim().equals(""))
								{
									sn_ref_no.add(sn_ref_number);
								}
								else
								{
									sn_ref_no.add("");
								}
								daily_Seller_Nom_Type.add("L");
								daily_Seller_Nom_Contract_Type.add("LOA");
								
								///////////////////////////RG//////////////
								
								daily_Seller_regas_cargo_boe_dt.add("");
								daily_Seller_regas_cargo_boe_no.add("");
								
								////////////////////////////////////////
								
								queryString5 = "SELECT NVL(A.customer_abbr,' '), CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+"";
								System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
									Vdaily_Buyer_Nom_Name.add(rset5.getString(2));
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
									Vdaily_Buyer_Nom_Name.add("*");
								}
								
								queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
					   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
					   			   			   "AND A.loa_no="+rset1.getString(3)+" " +
					   			   			   "AND A.loa_rev_no="+rset1.getString(4)+" " +
					   			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
					   			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
								System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									//SB20110915 if(Double.parseDouble(rset5.getString(1))>0)
									if(Double.parseDouble(rset5.getString(1))>=0)
									{
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));
										total_dcq += Double.parseDouble(rset5.getString(1));
									}
									else
									{
										daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
										total_dcq += Double.parseDouble(rset1.getString(6));
									}
								}
								else
								{
									daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
								}
															
								daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
								daily_Transporter_Nom_Abbr_Seller_Nom.add(""+master_Transporter_Abbr_Seller_Nom.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
											   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
								   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
											   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
									daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
								}
								else
								{
									daily_Seller_Gen_Day_With_Rev_No.add("");
									daily_Seller_Gen_Day_Time.add("");
									daily_Seller_Nom_Plant_Seq_No.add("0");
									daily_Seller_Nom_Qty_Mmbtu.add("");
									daily_Seller_Nom_Qty_Scm.add("");
								}
								
								daily_Seller_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
								daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
															
								++trans_count;
							}
						}
					}
				}//End Of Code For LOA Based Buyers ...
				
				//Following Code Is For RE-GAS Based Buyers ...
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0')," +
								"NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
							   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
							   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
							   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
							   "AND C.transporter_cd='"+master_Transporter_Cd_Seller_Nom.elementAt(i)+"' " +
							   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				
				System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query (RE-GAS) = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";
						System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Seller_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Seller_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Seller_Nom_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
							   			   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
			
							System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table (RE-GAS) = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset4.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_buyer_mmbtu += Double.parseDouble(rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add(rset3.getString(1)==null?"":rset3.getString(1));
								}
								else
								{
									daily_Buyer_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Seller_Nom_Fgsa_No.add(rset1.getString(1));
								daily_Seller_Nom_Fgsa_Rev_No.add(rset1.getString(2));
								daily_Seller_Nom_Sn_No.add(rset1.getString(3));
								daily_Seller_Nom_Sn_Rev_No.add("0");
								daily_Seller_Nom_Cd.add(rset1.getString(5));
								sn_ref_no.add("");
								daily_Seller_Nom_Type.add("R");
								daily_Seller_Nom_Contract_Type.add("RE-GAS");
								daily_Seller_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								
								/////////////////////////RG////////////
								daily_Seller_regas_cargo_boe_no.add(rset1.getString(7)==null?"":rset1.getString(7));
								daily_Seller_regas_cargo_boe_dt.add(rset1.getString(8)==null?"":rset1.getString(8));
								
							//	System.out.println("Size of daily_Seller_Nom_Contract_Type====================================="+daily_Seller_Nom_Contract_Type.size()+"and"+daily_Seller_Nom_Contract_Type);
							//	System.out.println("Size of daily_Seller_regas_cargo_boe_no"+daily_Seller_regas_cargo_boe_no.size()+"and"+daily_Seller_regas_cargo_boe_no);
							//	System.out.println("Size of daily_Seller_regas_cargo_boe_dt"+daily_Seller_regas_cargo_boe_dt.size()+"and"+daily_Seller_regas_cargo_boe_dt);
								
								//////////////////////////////////////
								
								queryString5 = "SELECT NVL(A.customer_abbr,' '), CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Seller_Nom_Abbr.add(rset5.getString(1));
									Vdaily_Buyer_Nom_Name.add(rset5.getString(2));
								}
								else
								{
									daily_Seller_Nom_Abbr.add("");
									Vdaily_Buyer_Nom_Name.add("*");
								}
								
								daily_Transporter_Nom_Cd_Seller_Nom.add(""+master_Transporter_Cd_Seller_Nom.elementAt(i));
								daily_Transporter_Nom_Abbr_Seller_Nom.add(""+master_Transporter_Abbr_Seller_Nom.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
											   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
								   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Seller_Nom.elementAt(i)+" " +
											   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (RE-GAS) = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Seller_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+" - "+rset6.getString(2)+")");
									daily_Seller_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Seller_Nom_Plant_Seq_No.add(rset6.getString(4));
									daily_Seller_Nom_Qty_Mmbtu.add(rset6.getString(5)=="0"?"":rset6.getString(5));
									daily_Seller_Nom_Qty_Scm.add(rset6.getString(6)=="0"?"":rset6.getString(6));
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
								}
								else
								{
									daily_Seller_Gen_Day_With_Rev_No.add("");
									daily_Seller_Gen_Day_Time.add("");
									daily_Seller_Nom_Plant_Seq_No.add("0");
									daily_Seller_Nom_Qty_Mmbtu.add("");
									daily_Seller_Nom_Qty_Scm.add("");
								}
								
								daily_Seller_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
								daily_Seller_Obligation_Qty.add(""); //Remaining For Logic Development ...
															
								++trans_count;
							}
						}
					}
				}//End Of Code For RE-GAS Based Buyers ...
				
				master_Transporter_Count_Seller_Nom.add(""+trans_count);
				master_Transporter_Dcq_Seller_Nom.add(nf.format(total_dcq));
				
				if(total_buyer_mmbtu>0)
				{
					master_Transporter_Mmbtu.add(nf.format(total_buyer_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu.add("");
				}
				
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu_Seller_Nom.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu_Seller_Nom.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm_Seller_Nom.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm_Seller_Nom.add("");
				}
				
				daily_Transporter_Asking_Rate_Qty.add(""); //Remaining For Logic Development ...
				daily_Transporter_Obligation_Qty.add(""); //Remaining For Logic Development ...
				
				final_daily_buyer_mmbtu += total_buyer_mmbtu;
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
			}
			
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
			
			daily_Total_Obligation_Qty = ""; //Remaining For Logic Development ...
			daily_Total_Asking_Rate_Qty = ""; //Remaining For Logic Development ...
						
			//Size Of All Utilized Vectors ...
			/*System.out.println("master_Transporter_Cd_Seller_Nom.size() = "+master_Transporter_Cd_Seller_Nom.size());
			System.out.println("master_Transporter_Abbr_Seller_Nom.size() = "+master_Transporter_Abbr_Seller_Nom.size());
			System.out.println("daily_Transporter_Nom_Cd_Seller_Nom.size() = "+daily_Transporter_Nom_Cd_Seller_Nom.size());
			System.out.println("daily_Transporter_Nom_Abbr_Seller_Nom.size() = "+daily_Transporter_Nom_Abbr_Seller_Nom.size());
			System.out.println("master_Transporter_Count_Seller_Nom.size() = "+master_Transporter_Count_Seller_Nom.size());
			System.out.println("master_Transporter_Dcq_Seller_Nom.size() = "+master_Transporter_Dcq_Seller_Nom.size());
			System.out.println("master_Transporter_Mmbtu_Seller_Nom.size() = "+master_Transporter_Mmbtu_Seller_Nom.size());
			System.out.println("master_Transporter_Scm_Seller_Nom.size() = "+master_Transporter_Scm_Seller_Nom.size());
			
			System.out.println("daily_Seller_Nom_Fgsa_No.size() = "+daily_Seller_Nom_Fgsa_No.size());
			System.out.println("daily_Seller_Nom_Fgsa_Rev_No.size() = "+daily_Seller_Nom_Fgsa_Rev_No.size());
			System.out.println("daily_Seller_Nom_Sn_No.size() = "+daily_Seller_Nom_Sn_No.size());
			System.out.println("daily_Seller_Nom_Sn_Rev_No.size() = "+daily_Seller_Nom_Sn_Rev_No.size());
			System.out.println("daily_Seller_Nom_Cd.size() = "+daily_Seller_Nom_Cd.size());
			System.out.println("daily_Seller_Nom_Abbr.size() = "+daily_Seller_Nom_Abbr.size());
			System.out.println("daily_Seller_Nom_Dcq.size() = "+daily_Seller_Nom_Dcq.size());
			System.out.println("daily_Seller_Nom_Plant_Cd.size() = "+daily_Seller_Nom_Plant_Cd.size());
			System.out.println("daily_Seller_Nom_Plant_Abbr.size() = "+daily_Seller_Nom_Plant_Abbr.size());
			System.out.println("daily_Seller_Gen_Day_With_Rev_No.size() = "+daily_Seller_Gen_Day_With_Rev_No.size());
			System.out.println("daily_Seller_Gen_Day_Time.size() = "+daily_Seller_Gen_Day_Time.size());
			System.out.println("daily_Seller_Nom_Plant_Seq_No.size() = "+daily_Seller_Nom_Plant_Seq_No.size());
			System.out.println("daily_Seller_Nom_Qty_Mmbtu.size() = "+daily_Seller_Nom_Qty_Mmbtu.size());
			System.out.println("daily_Seller_Nom_Qty_Scm.size() = "+daily_Seller_Nom_Qty_Scm.size());
			System.out.println("daily_Seller_Nom_Type.size() = "+daily_Seller_Nom_Type.size());*/
			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailySellerNomDetails() ...
	
	
	
	
	public void fetchDailyMeterReading()
	{
		methodName = "fetchDailyMeterReading()";
		try 
		{
			double total_mmbtu = 0;
			double total_scm = 0;
			double total_reconcil_mmbtu = 0;
			double total_reconcil_scm = 0;
			double individual_total_mmbtu = 0;
			double total_reconcil_btu = 0;
			double total_btu = 0;
			
			//For Transporter Meter Listing ...
			queryString = "SELECT NVL(A.trans_cust_cd,'0'),NVL(A.meter_seq_no,'0') " +
						  "FROM FMS7_METER_MST A " +
						  "WHERE A.meter_type='T' " +
						  "ORDER BY A.trans_cust_cd,A.meter_seq_no";
			//System.out.println("Transporter Meter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				daily_Meter_Reading_Transporter_Cd.add(rset.getString(1));
				daily_Meter_Reading_Transporter_Seq_Cd.add(rset.getString(2));
				
				queryString2 = "SELECT NVL(A.transporter_abbr,' ') " +
	  			  			   "FROM DLNG_TRANS_MST A " +
	  			  			   "WHERE A.transporter_cd="+rset.getString(1)+"";
				//System.out.println("Transporter Abbriviation Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					daily_Meter_Reading_Transporter_Abbr.add(rset2.getString(1));
				}
				else
				{
					daily_Meter_Reading_Transporter_Abbr.add(" ");
				}				
			}
			
			//Following For Loop Is Introduced By Samik Shah On 5th May, 2010 ...
			//Following For Loop Fetches Daily Meter Reading Data For Transporter ... 
			for(int i=0; i<daily_Meter_Reading_Transporter_Cd.size(); i++)
			{
				queryString = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
							  "NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0')," +
							  "NVL(A.calc_gcv,'0'),NVL(A.calc_ncv,'0')," +
							  "NVL(A.define_gcv,'0'),NVL(A.define_ncv,'0')," +
							  "NVL(TO_CHAR(A.gen_dt,'DD/MM/YYYY'),''),NVL(A.gen_time,''), "
							  + "NVL(A.qty_btu,'0'), NVL(A.reconcil_qty_btu,'0') " + //ADDED FOR BTU ENTRY RG
				  			  "FROM FMS7_METER_TICKET_READING A " +
				  			  "WHERE A.meter_type='T' " +
				  			  "AND A.trans_cust_cd="+daily_Meter_Reading_Transporter_Cd.elementAt(i)+" " +
				  			  "AND A.transporter_cd=0 " +
				  			  "AND A.meter_seq_cd="+daily_Meter_Reading_Transporter_Seq_Cd.elementAt(i)+" " +
				  			  "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
				
				//System.out.println("---JAVA meter queryString---: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_Meter_Reading_Transporter_Mmbtu.add(rset.getString(1));
					daily_Meter_Reading_Transporter_Scm.add(rset.getString(2));
					daily_Meter_Reading_Transporter_Reconcil_Mmbtu.add(rset.getString(3));
					daily_Meter_Reading_Transporter_Reconcil_Scm.add(rset.getString(4));
					daily_Meter_Reading_Transporter_Calculated_Gcv.add(nf3.format(Double.parseDouble(rset.getString(5))));
					daily_Meter_Reading_Transporter_Calculated_Ncv.add(nf3.format(Double.parseDouble(rset.getString(6))));
					daily_Meter_Reading_Transporter_Define_Gcv.add(rset.getString(7));
					daily_Meter_Reading_Transporter_Define_Ncv.add(rset.getString(8));
					
					daily_Meter_Reading_Gen_Date = rset.getString(9)==null?"":rset.getString(9);
					daily_Meter_Reading_Gen_Time = rset.getString(10)==null?"":rset.getString(10);
					
					daily_Meter_Reading_Transporter_Btu.add(rset.getString(11));
					daily_Meter_Reading_Transporter_Reconcil_Btu.add(rset.getString(12));
					
					total_mmbtu += Double.parseDouble(rset.getString(1));
					total_scm += Double.parseDouble(rset.getString(2));
					total_reconcil_mmbtu += Double.parseDouble(rset.getString(3));
					total_reconcil_scm += Double.parseDouble(rset.getString(4));
					total_btu += Double.parseDouble(rset.getString(11));
					total_reconcil_btu += Double.parseDouble(rset.getString(12));
					
					
					
					daily_Meter_Reading_Individual_Transporter_Mmbtu.add(nf.format(Double.parseDouble(rset.getString(1))+Double.parseDouble(rset.getString(3))));
				}
				else
				{
					daily_Meter_Reading_Transporter_Mmbtu.add("");
					daily_Meter_Reading_Transporter_Scm.add("");
					daily_Meter_Reading_Transporter_Reconcil_Mmbtu.add("");
					daily_Meter_Reading_Transporter_Reconcil_Scm.add("");
					daily_Meter_Reading_Transporter_Calculated_Gcv.add("");
					daily_Meter_Reading_Transporter_Calculated_Ncv.add("");
					daily_Meter_Reading_Transporter_Define_Gcv.add("");
					daily_Meter_Reading_Transporter_Define_Ncv.add("");
					daily_Meter_Reading_Individual_Transporter_Mmbtu.add("");
					daily_Meter_Reading_Transporter_Btu.add("");
					daily_Meter_Reading_Transporter_Reconcil_Btu.add("");
				}
			}
			
			individual_total_mmbtu = total_mmbtu+total_reconcil_mmbtu;
			
			if(total_mmbtu>0)
			{
				daily_Meter_Reading_Transporter_Mmbtu_Total = ""+nf.format(total_mmbtu);
			}
			else
			{
				daily_Meter_Reading_Transporter_Mmbtu_Total = "";
			}
			
			if(total_scm>0)
			{
				daily_Meter_Reading_Transporter_Scm_Total = ""+nf.format(total_scm);
			}
			else
			{
				daily_Meter_Reading_Transporter_Scm_Total = "";
			}
			
			if(total_btu>0)
			{
				daily_Meter_Reading_Transporter_Btu_Total = ""+nf.format(total_btu);
			}
			else
			{
				daily_Meter_Reading_Transporter_Btu_Total = "";
			}
			
			if(total_reconcil_mmbtu>0 || total_reconcil_mmbtu<0)
			{
				daily_Meter_Reading_Transporter_Reconcil_Mmbtu_Total = ""+nf.format(total_reconcil_mmbtu);
			}
			else
			{
				daily_Meter_Reading_Transporter_Reconcil_Mmbtu_Total = "";
			}
			
			if(total_reconcil_scm>0 || total_reconcil_scm<0)
			{
				daily_Meter_Reading_Transporter_Reconcil_Scm_Total = ""+nf.format(total_reconcil_scm);
			}
			else
			{
				daily_Meter_Reading_Transporter_Reconcil_Scm_Total = "";
			}
			
			if(total_reconcil_btu>0 || total_reconcil_btu<0)
			{
				daily_Meter_Reading_Transporter_Reconcil_Btu_Total = ""+nf.format(total_reconcil_btu);
			}
			else
			{
				daily_Meter_Reading_Transporter_Reconcil_Btu_Total = "";
			}
			
			if(individual_total_mmbtu>0)
			{
				daily_Meter_Reading_Individual_Transporter_Mmbtu_Total = ""+nf.format(individual_total_mmbtu);				
			}
			else
			{
				daily_Meter_Reading_Individual_Transporter_Mmbtu_Total = "";
			}
			
			
			total_mmbtu = 0;
			total_scm = 0;
			total_reconcil_mmbtu = 0;
			total_reconcil_scm = 0;
			individual_total_mmbtu = 0;
			total_btu = 0;
			total_reconcil_btu = 0;
			
			//For Customer Meter Listing ...
			queryString = "SELECT NVL(A.trans_cust_cd,'0'),NVL(A.meter_seq_no,'0')," +
						  "NVL(A.transporter_cd,'0') " +
			  			  "FROM FMS7_METER_MST A " +
			  			  "WHERE A.meter_type='C' " +
			  			  "ORDER BY A.trans_cust_cd,A.transporter_cd,A.meter_seq_no";
			//System.out.println("Customer Meter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				daily_Meter_Reading_Customer_Cd.add(rset.getString(1));
				daily_Meter_Reading_Customer_Seq_Cd.add(rset.getString(2));
				daily_Meter_Reading_Customer_Transporter_Cd.add(rset.getString(3));
				
				queryString2 = "SELECT NVL(A.customer_abbr,' ') " +
	  			   			   "FROM FMS7_CUSTOMER_MST A " +
	  			   			   "WHERE A.customer_cd="+rset.getString(1)+"";
				//System.out.println("Customer Abbriviation Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					daily_Meter_Reading_Customer_Abbr.add(rset2.getString(1));
				}
				else
				{
					daily_Meter_Reading_Customer_Abbr.add(" ");
				}	
				
				queryString2 = "SELECT NVL(A.transporter_abbr,' ') " +
				  			   "FROM DLNG_TRANS_MST A " +
				  			   "WHERE A.transporter_cd="+rset.getString(3)+"";
				//System.out.println("Transporter Abbriviation Related To Customer Meter Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					daily_Meter_Reading_Customer_Transporter_Abbr.add(rset2.getString(1));
				}
				else
				{
					daily_Meter_Reading_Customer_Transporter_Abbr.add(" ");
				}
			}
			
			//Following For Loop Is Introduced By Samik Shah On 5th May, 2010 ...
			//Following For Loop Fetches Daily Meter Reading Data For Customer ... 
			for(int i=0; i<daily_Meter_Reading_Customer_Cd.size(); i++)
			{
				queryString = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
							  "NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0')," +
							  "NVL(A.calc_gcv,'0'),NVL(A.calc_ncv,'0')," +
							  "NVL(A.define_gcv,'0'),NVL(A.define_ncv,'0')," +
							  "NVL(TO_CHAR(A.gen_dt,'DD/MM/YYYY'),''),NVL(A.gen_time,''),"
							  + "NVL(A.qty_btu,'0'),NVL(A.reconcil_qty_btu,'0') " +
				  			  "FROM FMS7_METER_TICKET_READING A " +
				  			  "WHERE A.meter_type='C' " +
				  			  "AND A.trans_cust_cd="+daily_Meter_Reading_Customer_Cd.elementAt(i)+" " +
				  			  "AND A.transporter_cd="+daily_Meter_Reading_Customer_Transporter_Cd.elementAt(i)+" " +
				  			  "AND A.meter_seq_cd="+daily_Meter_Reading_Customer_Seq_Cd.elementAt(i)+" " +
				  			  "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_Meter_Reading_Customer_Mmbtu.add(rset.getString(1));
					daily_Meter_Reading_Customer_Scm.add(rset.getString(2));
					daily_Meter_Reading_Customer_Reconcil_Mmbtu.add(rset.getString(3));
					daily_Meter_Reading_Customer_Reconcil_Scm.add(rset.getString(4));
					daily_Meter_Reading_Customer_Calculated_Gcv.add(rset.getString(5));
					daily_Meter_Reading_Customer_Calculated_Ncv.add(rset.getString(6));
					daily_Meter_Reading_Customer_Define_Gcv.add(rset.getString(7));
					daily_Meter_Reading_Customer_Define_Ncv.add(rset.getString(8));
					
					daily_Meter_Reading_Gen_Date = rset.getString(9)==null?"":rset.getString(9);
					daily_Meter_Reading_Gen_Time = rset.getString(10)==null?"":rset.getString(10);
					
					total_mmbtu += Double.parseDouble(rset.getString(1));
					total_scm += Double.parseDouble(rset.getString(2));
					total_reconcil_mmbtu += Double.parseDouble(rset.getString(3));
					total_reconcil_scm += Double.parseDouble(rset.getString(4));
					total_btu += Double.parseDouble(rset.getString(11));
					total_reconcil_btu += Double.parseDouble(rset.getString(12));
					
					daily_Meter_Reading_Customer_Btu.add(rset.getString(11));
					daily_Meter_Reading_Customer_Reconcil_Btu.add(rset.getString(12));
					
					daily_Meter_Reading_Individual_Customer_Mmbtu.add(nf.format(Double.parseDouble(rset.getString(1))+Double.parseDouble(rset.getString(3))));
				}
				else
				{
					daily_Meter_Reading_Customer_Mmbtu.add("");
					daily_Meter_Reading_Customer_Scm.add("");
					daily_Meter_Reading_Customer_Reconcil_Mmbtu.add("");
					daily_Meter_Reading_Customer_Reconcil_Scm.add("");
					daily_Meter_Reading_Customer_Calculated_Gcv.add("");
					daily_Meter_Reading_Customer_Calculated_Ncv.add("");
					daily_Meter_Reading_Customer_Define_Gcv.add("");
					daily_Meter_Reading_Customer_Define_Ncv.add("");
					daily_Meter_Reading_Individual_Customer_Mmbtu.add("");
					daily_Meter_Reading_Customer_Btu.add("");
					daily_Meter_Reading_Customer_Reconcil_Btu.add("");
				}
			}
			
			individual_total_mmbtu = total_mmbtu+total_reconcil_mmbtu;
			
			if(total_mmbtu>0)
			{
				daily_Meter_Reading_Customer_Mmbtu_Total = ""+nf.format(total_mmbtu);
			}
			else
			{
				daily_Meter_Reading_Customer_Mmbtu_Total = "";
			}
			
			if(total_scm>0)
			{
				daily_Meter_Reading_Customer_Scm_Total = ""+nf.format(total_scm);
			}
			else
			{
				daily_Meter_Reading_Customer_Scm_Total = "";
			}
			
			if(total_btu>0)
			{
				daily_Meter_Reading_Customer_Btu_Total = ""+nf.format(total_btu);
			}
			else
			{
				daily_Meter_Reading_Customer_Btu_Total = "";
			}
			
			if(total_reconcil_mmbtu>0 || total_reconcil_mmbtu<0)
			{
				daily_Meter_Reading_Customer_Reconcil_Mmbtu_Total = ""+nf.format(total_reconcil_mmbtu);
			}
			else
			{
				daily_Meter_Reading_Customer_Reconcil_Mmbtu_Total = "";
			}
			
			if(total_reconcil_btu>0 || total_reconcil_btu<0)
			{
				daily_Meter_Reading_Customer_Reconcil_Btu_Total = ""+nf.format(total_reconcil_btu);
			}
			else
			{
				daily_Meter_Reading_Customer_Reconcil_Btu_Total = "";
			}
			
			if(total_reconcil_scm>0 || total_reconcil_scm<0)
			{
				daily_Meter_Reading_Customer_Reconcil_Scm_Total = ""+nf.format(total_reconcil_scm);
			}
			else
			{
				daily_Meter_Reading_Customer_Reconcil_Scm_Total = "";
			}
			
			if(individual_total_mmbtu>0)
			{
				daily_Meter_Reading_Individual_Customer_Mmbtu_Total = ""+nf.format(individual_total_mmbtu);				
			}
			else
			{
				daily_Meter_Reading_Individual_Customer_Mmbtu_Total = "";
			}
		}
		catch(Exception e)
		{
			//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyMeterReading() ...
////////////////SB20181008: Max date////////////////////////////////
public void fetchLastMeterReading()
{
		methodName = "fetchDailyMeterReading()";
		try 
		{
		queryString ="SELECT MAX(TO_CHAR(gas_dt,'DD/MM/YYYY')) FROM FMS7_METER_TICKET_READING WHERE meter_type='T' ";
		rset = stmt.executeQuery(queryString);
			if(rset.next())
				LastMeterReadingDt = rset.getString(1)==null?"N.A.":rset.getString(1);
			
		queryString ="SELECT MAX(TO_CHAR(gas_dt,'DD/MM/YYYY')) FROM FMS7_METER_TICKET_READING WHERE meter_type='C' ";
		rset = stmt.executeQuery(queryString);
		if(rset.next())
			LastCustMeterReadingDt = rset.getString(1)==null?"N.A.":rset.getString(1);
	}
	catch(Exception e)
	{
		//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
		e.printStackTrace();
	}
}//End Of Method fetchDailyMeterReading() ...
////////////////////////////////////////////////////////////////////
	Vector plant_seq_no1=new Vector();
	Vector Plant_Abbr=new Vector();
	Vector dates=new Vector();
	String remark="";
	Vector dr_cr_flag=new Vector();
	Vector sn_no_cr_dr=new Vector();
	Vector fgsa_no_cr_dr=new Vector();
	Vector contract_type_cr_dr= new Vector();
	public void fetchDailyAllocationDetails()
	{
		methodName = "fetchDailyAllocationDetails()";
		try 
		{
			double sug_percentage = 0.3;
			double final_daily_seller_mmbtu = 0;
			
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			double final_daily_offspec = 0;
			double final_daily_fm = 0;
			double final_daily_sug = 0;
			
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd_Buyer_Allocation.add(rset.getString(1));
				master_Transporter_Abbr_Buyer_Allocation.add(rset.getString(2));
			}
			
			for(int i=0;i<master_Transporter_Cd_Buyer_Allocation.size();i++)
			{
				double total_seller_mmbtu = 0;
				
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_offspec = 0;
				double total_customer_meter_mmbtu = 0;
				double total_scm = 0;
				double total_fm = 0;
				double total_customer_meter_scm = 0;
				double total_sug = 0;
				
				Vector seller_nom = new Vector();
				String customer_cd1="";				
				//Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),A.sn_ref_no " +
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' " +
							   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   "AND A.fgsa_rev_no=B.fgsa_rev_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" AND A.sn_rev_no="+rset1.getString(4)+"";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Allocation_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Allocation_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Allocation_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Seller_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+" - "+rset4.getString(2)+")");
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_seller_mmbtu += Double.parseDouble(rset4.getString(5));
								seller_nom.add(rset4.getString(5)==null?"0":rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								System.out.println("Customer Plant Name Fetch Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
					
								daily_Buyer_Allocation_Mapping_Id.add(""); //NB20141029 ADDED FOR LTCORA 
								daily_Buyer_Allocation_Fgsa_No.add(rset1.getString(1));
								daily_Buyer_Allocation_Fgsa_Rev_No.add(rset1.getString(2));
								daily_Buyer_Allocation_Sn_No.add(rset1.getString(3));
								daily_Buyer_Allocation_Sn_Rev_No.add(rset1.getString(4));
								daily_Buyer_Allocation_Cd.add(rset1.getString(5));
								String sn_ref_number = rset1.getString(7)==null?"":rset1.getString(7);
								if(!sn_ref_number.trim().equals(""))
								{
									sn_ref_no.add(sn_ref_number);
								}
								else
								{
									sn_ref_no.add("");
								}
								daily_Buyer_Allocation_Type.add("S");
								daily_Buyer_Allocation_Contract_Type.add("SN");
												
								
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Allocation_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Buyer_Allocation_Abbr.add(" ");
								}
								
								queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
								   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
								//System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									//SB20110915 if(Double.parseDouble(rset5.getString(1))>0)
									if(Double.parseDouble(rset5.getString(1))>=0)
									{
										daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));
										total_dcq += Double.parseDouble(rset5.getString(1));
									}
									else
									{
										daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
										total_dcq += Double.parseDouble(rset1.getString(6));
									}
								}
								else
								{
									daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
								}
														
								daily_Transporter_Nom_Cd_Buyer_Allocation.add(""+master_Transporter_Cd_Buyer_Allocation.elementAt(i));
								daily_Transporter_Nom_Abbr_Buyer_Allocation.add(""+master_Transporter_Abbr_Buyer_Allocation.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
											   "NVL(A.qty_sug,'0'),NVL(A.qty_offspec,'0')," +
											   "NVL(A.qty_fm,'0'),NVL(A.offspec_rate,'0'),NVL(A.offspec_flag,'N')  " +
											   "FROM DLNG_DAILY_ALLOCATION_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
								   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM DLNG_DAILY_ALLOCATION_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
											   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+")");
									daily_Buyer_Allocation_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Buyer_Allocation_Plant_Seq_No.add(rset6.getString(4));
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble(rset6.getString(5)))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble(rset6.getString(6)))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble(rset6.getString(7)))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble(rset6.getString(8)))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble(rset6.getString(9)))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble(rset6.getString(10)))));
									daily_Buyer_Allocation_Offspec_Flag.add(rset6.getString(11));
									daily_Meter_Based_Customer_flag.add("N");
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
									total_sug += Double.parseDouble(rset6.getString(7));
									total_offspec += Double.parseDouble(rset6.getString(8));
									total_fm += Double.parseDouble(rset6.getString(9));
								}
								else
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Allocation_Gen_Day_Time.add("");
									daily_Buyer_Allocation_Plant_Seq_No.add("0");
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Flag.add("N");
									daily_Meter_Based_Customer_flag.add("N");
								}
								
								queryString6 = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0') " +
					  			  			   "FROM FMS7_METER_TICKET_READING A " +
					  			  			   "WHERE A.meter_type='C' " +
					  			  			   "AND A.trans_cust_cd="+rset1.getString(5)+" " +
					  			  			   "AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
					  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					
								//System.out.println("TOTAL MMBTU FOR METER READING --> queryString6 = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									if((""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)).equals("0.00"))
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))+Double.parseDouble(rset6.getString(3))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))+Double.parseDouble(rset6.getString(4))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += Double.parseDouble(rset6.getString(1));
											total_scm += Double.parseDouble(rset6.getString(2));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
									else
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += (Double.parseDouble(rset6.getString(1))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)));
											total_scm += (Double.parseDouble(rset6.getString(2))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Scm.elementAt(daily_Buyer_Allocation_Qty_Scm.size()-1)));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
								}
								++trans_count;
							}
						}
					}
					
				}//End Of Code For SN Based Buyers ...
				//Following Code Is For LOA Based Buyers ...
				String customer_cd2="";
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),A.loa_ref_no " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' " +
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				//System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+"";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Allocation_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Allocation_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Allocation_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
				
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Seller_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+" - "+rset4.getString(2)+")");
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_seller_mmbtu += Double.parseDouble(rset4.getString(5));
								seller_nom.add(rset4.getString(5)==null?"0":rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
					
								daily_Buyer_Allocation_Mapping_Id.add(""); //NB20141029 ADDED FOR LTCORA 
								daily_Buyer_Allocation_Fgsa_No.add(rset1.getString(1));
								daily_Buyer_Allocation_Fgsa_Rev_No.add("0");
								daily_Buyer_Allocation_Sn_No.add(rset1.getString(3));
								daily_Buyer_Allocation_Sn_Rev_No.add(rset1.getString(4));
								daily_Buyer_Allocation_Cd.add(rset1.getString(5));
								String sn_ref_number = rset1.getString(7)==null?"":rset1.getString(7);
								if(!sn_ref_number.trim().equals(""))
								{
									sn_ref_no.add(sn_ref_number);
								}
								else
								{
									sn_ref_no.add("");
								}
								daily_Buyer_Allocation_Type.add("L");
								daily_Buyer_Allocation_Contract_Type.add("LOA");
																				
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Allocation_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Buyer_Allocation_Abbr.add(" ");
								}
								
								queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
					   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
					   			   			   "AND A.loa_no="+rset1.getString(3)+" " +
					   			   			   "AND A.loa_rev_no="+rset1.getString(4)+" " +
					   			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
					   			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
								//System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
								//SB20110915	if(Double.parseDouble(rset5.getString(1))>0)
									if(Double.parseDouble(rset5.getString(1))>=0)
									{
										daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));
										total_dcq += Double.parseDouble(rset5.getString(1));
									}
									else
									{
										daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
										total_dcq += Double.parseDouble(rset1.getString(6));
									}
								}
								else
								{
									daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
								}
															
								daily_Transporter_Nom_Cd_Buyer_Allocation.add(""+master_Transporter_Cd_Buyer_Allocation.elementAt(i));
								daily_Transporter_Nom_Abbr_Buyer_Allocation.add(""+master_Transporter_Abbr_Buyer_Allocation.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
											   "NVL(A.qty_sug,'0'),NVL(A.qty_offspec,'0'),NVL(A.qty_fm,'0')," +
											   "NVL(A.offspec_rate,'0'),NVL(A.offspec_flag,'N') " +
											   "FROM DLNG_DAILY_ALLOCATION_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
								   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM DLNG_DAILY_ALLOCATION_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
											   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+")");
									daily_Buyer_Allocation_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Buyer_Allocation_Plant_Seq_No.add(rset6.getString(4));
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble(rset6.getString(5)))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble(rset6.getString(6)))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble(rset6.getString(7)))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble(rset6.getString(8)))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble(rset6.getString(9)))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble(rset6.getString(10)))));
									daily_Buyer_Allocation_Offspec_Flag.add(rset6.getString(11));
									daily_Meter_Based_Customer_flag.add("N");
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
									total_sug += Double.parseDouble(rset6.getString(7));
									total_offspec += Double.parseDouble(rset6.getString(8));
									total_fm += Double.parseDouble(rset6.getString(9));
								}
								else
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Allocation_Gen_Day_Time.add("");
									daily_Buyer_Allocation_Plant_Seq_No.add("0");
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Flag.add("N");
									daily_Meter_Based_Customer_flag.add("N");
								}
								
								queryString6 = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0') " +
					  			  			   "FROM FMS7_METER_TICKET_READING A " +
					  			  			   "WHERE A.meter_type='C' " +
					  			  			   "AND A.trans_cust_cd="+rset1.getString(5)+" " +
					  			  			   "AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
					  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					
								//System.out.println("TOTAL MMBTU FOR METER READING --> queryString6 = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									if((""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)).equals("0.00"))
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))+Double.parseDouble(rset6.getString(3))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))+Double.parseDouble(rset6.getString(4))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += Double.parseDouble(rset6.getString(1));
											total_scm += Double.parseDouble(rset6.getString(2));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
									else
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += (Double.parseDouble(rset6.getString(1))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)));
											total_scm += (Double.parseDouble(rset6.getString(2))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Scm.elementAt(daily_Buyer_Allocation_Qty_Scm.size()-1)));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
								}
								
								++trans_count;
							}
						}
					}
						
				}//End Of Code For LOA Based Buyers ...
				
				
				//Following Code Is For RE-GAS Based Buyers ...
				String customer_cd3="";
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0') " +
							   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
							   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
							   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
							   "AND C.transporter_cd='"+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+"' " +
							   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+"";
						//System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Allocation_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Allocation_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Allocation_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
				
							//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (RE-GAS) = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Seller_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+" - "+rset4.getString(2)+")");
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_seller_mmbtu += Double.parseDouble(rset4.getString(5));
								seller_nom.add(rset4.getString(5)==null?"0":rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Buyer_Allocation_Mapping_Id.add(""); //NB20141029 ADDED FOR LTCORA 
								daily_Buyer_Allocation_Fgsa_No.add(rset1.getString(1));
								daily_Buyer_Allocation_Fgsa_Rev_No.add("0");
								daily_Buyer_Allocation_Sn_No.add(rset1.getString(3));
								daily_Buyer_Allocation_Sn_Rev_No.add(rset1.getString(4));
								daily_Buyer_Allocation_Cd.add(rset1.getString(5));
								daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								sn_ref_no.add("");
								daily_Buyer_Allocation_Type.add("R");
								daily_Buyer_Allocation_Contract_Type.add("RE-GAS");
																				
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Allocation_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Buyer_Allocation_Abbr.add(" ");
								}
								
								daily_Transporter_Nom_Cd_Buyer_Allocation.add(""+master_Transporter_Cd_Buyer_Allocation.elementAt(i));
								daily_Transporter_Nom_Abbr_Buyer_Allocation.add(""+master_Transporter_Abbr_Buyer_Allocation.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
											   "NVL(A.qty_sug,'0'),NVL(A.qty_offspec,'0'),NVL(A.qty_fm,'0')," +
											   "NVL(A.offspec_rate,'0'),NVL(A.offspec_flag,'N') " +
											   "FROM DLNG_DAILY_ALLOCATION_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
								   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM DLNG_DAILY_ALLOCATION_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
											   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (RE-GAS) = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+")");
									daily_Buyer_Allocation_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Buyer_Allocation_Plant_Seq_No.add(rset6.getString(4));
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble(rset6.getString(5)))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble(rset6.getString(6)))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble(rset6.getString(7)))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble(rset6.getString(8)))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble(rset6.getString(9)))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble(rset6.getString(10)))));
									daily_Buyer_Allocation_Offspec_Flag.add(rset6.getString(11));
									daily_Meter_Based_Customer_flag.add("N");
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
									total_sug += Double.parseDouble(rset6.getString(7));
									total_offspec += Double.parseDouble(rset6.getString(8));
									total_fm += Double.parseDouble(rset6.getString(9));
								}
								else
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Allocation_Gen_Day_Time.add("");
									daily_Buyer_Allocation_Plant_Seq_No.add("0");
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Flag.add("N");
									daily_Meter_Based_Customer_flag.add("N");
								}
								
								queryString6 = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0') " +
					  			  			   "FROM FMS7_METER_TICKET_READING A " +
					  			  			   "WHERE A.meter_type='C' " +
					  			  			   "AND A.trans_cust_cd="+rset1.getString(5)+" " +
					  			  			   "AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
					  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					
								//System.out.println("TOTAL MMBTU FOR METER READING --> queryString6 = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									if((""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)).equals("0.00"))
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))+Double.parseDouble(rset6.getString(3))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))+Double.parseDouble(rset6.getString(4))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += Double.parseDouble(rset6.getString(1));
											total_scm += Double.parseDouble(rset6.getString(2));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
									else
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += (Double.parseDouble(rset6.getString(1))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)));
											total_scm += (Double.parseDouble(rset6.getString(2))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Scm.elementAt(daily_Buyer_Allocation_Qty_Scm.size()-1)));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
								}
								
								++trans_count;
							}
						}
					}
					
				}//End Of Code For RE-GAS Based Buyers ...
				
				
//				Following Code Is For LTCORA Based Buyers ...
				
				//System.out.println("--plant_abbbr----"+Plant_Abbr);
				queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0') " +
							   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, FMS8_LNG_REGAS_TRANS_DTL C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.mapping_id=B.mapping_id AND A.mapping_id=C.mapping_id AND B.mapping_id=C.mapping_id " +
							   "AND C.trans_cd='"+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+"' " +
							   "AND B.CN_NO='0' AND B.CN_REV_NO='0'" +
							   "ORDER BY A.mapping_id,A.cargo_seq_no";
				
				//System.out.println("Transporter, Customer, LTCORA, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					
					String map_id=rset1.getString(1)==null?"":rset1.getString(1);
					String tempmap_id[]=map_id.split("-");
					String temp_regas_no=tempmap_id[1];
					String temp_regas_rev_no=tempmap_id[2];
					String temp_cust_cd=tempmap_id[0];
					
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_no,'1') FROM FMS8_LNG_REGAS_PLANT_DTL A " +
									   "WHERE A.mapping_id='"+rset1.getString(1)+"' " +
									   "";
						//System.out.println("Customer Plant Sequence NO Fetch Query (LTCORA) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Allocation_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Allocation_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Allocation_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+temp_regas_no+" " +
							   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND A.contract_type='T' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   "AND B.fgsa_no="+temp_regas_no+" " +
							   			   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND B.contract_type='T' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
				
							//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (LTCORA) = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Seller_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+" - "+rset4.getString(2)+")");
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_seller_mmbtu += Double.parseDouble(rset4.getString(5));
								seller_nom.add(rset4.getString(5)==null?"0":rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								////System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
								
								daily_Buyer_Allocation_Mapping_Id.add(rset1.getString(1));
								daily_Buyer_Allocation_Fgsa_No.add(temp_regas_no);
								daily_Buyer_Allocation_Fgsa_Rev_No.add(temp_regas_rev_no);
								daily_Buyer_Allocation_Sn_No.add(rset1.getString(3));
								daily_Buyer_Allocation_Sn_Rev_No.add("0");
								daily_Buyer_Allocation_Cd.add(temp_cust_cd);
								daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								sn_ref_no.add("");
								daily_Buyer_Allocation_Type.add("T");
								daily_Buyer_Allocation_Contract_Type.add("LTCORA");
																				
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
											   "WHERE A.customer_cd="+temp_cust_cd+"";
								////System.out.println("Buyer Abbreviation Fetch Query (LTCORA) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Allocation_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Buyer_Allocation_Abbr.add(" ");
								}
								
								daily_Transporter_Nom_Cd_Buyer_Allocation.add(""+master_Transporter_Cd_Buyer_Allocation.elementAt(i));
								daily_Transporter_Nom_Abbr_Buyer_Allocation.add(""+master_Transporter_Abbr_Buyer_Allocation.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
											   "NVL(A.qty_sug,'0'),NVL(A.qty_offspec,'0'),NVL(A.qty_fm,'0')," +
											   "NVL(A.offspec_rate,'0'),NVL(A.offspec_flag,'N') " +
											   "FROM DLNG_DAILY_ALLOCATION_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.fgsa_no="+temp_regas_no+" " +
								   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
								   			   "AND A.contract_type='T' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM DLNG_DAILY_ALLOCATION_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   "AND B.fgsa_no="+temp_regas_no+" " +
											   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
											   "AND B.contract_type='T' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (LTCORA) = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+")");
									daily_Buyer_Allocation_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									
									daily_Buyer_Allocation_Plant_Seq_No.add(rset6.getString(4));
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble(rset6.getString(5)))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble(rset6.getString(6)))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble(rset6.getString(7)))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble(rset6.getString(8)))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble(rset6.getString(9)))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble(rset6.getString(10)))));
									daily_Buyer_Allocation_Offspec_Flag.add(rset6.getString(11));
									daily_Meter_Based_Customer_flag.add("N");
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
									total_sug += Double.parseDouble(rset6.getString(7));
									total_offspec += Double.parseDouble(rset6.getString(8));
									total_fm += Double.parseDouble(rset6.getString(9));
								}
								else
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Allocation_Gen_Day_Time.add("");
									daily_Buyer_Allocation_Plant_Seq_No.add("0");
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Flag.add("N");
									daily_Meter_Based_Customer_flag.add("N");
								}
								
								queryString6 = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0') " +
					  			  			   "FROM FMS7_METER_TICKET_READING A " +
					  			  			   "WHERE A.meter_type='C' " +
					  			  			   "AND A.trans_cust_cd="+temp_cust_cd+" " +
					  			  			   "AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
					  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					
								//System.out.println("TOTAL MMBTU FOR METER READING --> queryString6 = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									if((""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)).equals("0.00"))
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))+Double.parseDouble(rset6.getString(3))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))+Double.parseDouble(rset6.getString(4))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += Double.parseDouble(rset6.getString(1));
											total_scm += Double.parseDouble(rset6.getString(2));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
									else
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += (Double.parseDouble(rset6.getString(1))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)));
											total_scm += (Double.parseDouble(rset6.getString(2))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Scm.elementAt(daily_Buyer_Allocation_Qty_Scm.size()-1)));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
								}
								
								++trans_count;
							}
						}
					}
					
					
				}//End Of Code For LTCORA Based Buyers ...
				
				
//				Following Code Is For CN Based Buyers...
				
				queryString1 = "SELECT NVL(A.mapping_id,'0'),NVL(A.mapping_id,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.mapping_id,'0'),NVL(A.dcq_qty,'0') " +
							   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, FMS8_LNG_REGAS_TRANS_DTL C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.mapping_id=B.mapping_id AND A.mapping_id=C.mapping_id AND B.mapping_id=C.mapping_id " +
							   "AND C.trans_cd='"+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+"' " +
							   "AND B.CN_NO!='0' " +
							   "ORDER BY A.mapping_id,A.cargo_seq_no";
				
				//System.out.println("Transporter, Customer, CN, & Cargo Contract Wise Fetch Query 11111= "+queryString1);// //query for fetching mapping id
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
				//	System.out.println("in cn code");
					String map_id=rset1.getString(1)==null?"":rset1.getString(1);
					String tempmap_id[]=map_id.split("-");
					
					String temp_regas_no=tempmap_id[3];
					String temp_regas_rev_no=tempmap_id[4];
					String temp_cust_cd=tempmap_id[0];
					
					if(!rset1.getString(1).equals("0"))
					{
						
						queryString2 = "SELECT NVL(A.plant_no,'1') FROM FMS8_LNG_REGAS_PLANT_DTL A " +
									   "WHERE A.mapping_id='"+rset1.getString(1)+"' " +
									   "";
						//System.out.println("Customer Plant Sequence NO Fetch Query (CN) = "+queryString2);  //query for fetching plant cd
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Allocation_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							///System.out.println("Customer Plant Name Fetch Query (CN) = "+queryString3);  //query for fetching plant name
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Allocation_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Allocation_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+temp_regas_no+" " +
							   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND A.contract_type='C' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" " +
							   			   "AND MAPPING_ID='"+rset1.getString(1)+"' AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   "AND B.fgsa_no="+temp_regas_no+" " +
							   			   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND B.contract_type='C' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+" " +
							   			   "AND MAPPING_ID='"+rset1.getString(1)+"' )";
				
							//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (CN) = "+queryString4);  // query for fetcing Nom data
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Seller_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+" - "+rset4.getString(2)+")");
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_seller_mmbtu += Double.parseDouble(rset4.getString(5));
								seller_nom.add(rset4.getString(5)==null?"0":rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+temp_cust_cd+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query (CN) RU= "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
					
								daily_Buyer_Allocation_Mapping_Id.add(rset1.getString(1));
								daily_Buyer_Allocation_Fgsa_No.add(temp_regas_no);
								daily_Buyer_Allocation_Fgsa_Rev_No.add(temp_regas_rev_no);
								daily_Buyer_Allocation_Sn_No.add(rset1.getString(3));
								daily_Buyer_Allocation_Sn_Rev_No.add("0");
								daily_Buyer_Allocation_Cd.add(temp_cust_cd);
								daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								sn_ref_no.add("");
								daily_Buyer_Allocation_Type.add("C");
								daily_Buyer_Allocation_Contract_Type.add("CN");
																				
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
											   "WHERE A.customer_cd="+temp_cust_cd+"";
								////System.out.println("Buyer Abbreviation Fetch Query (CN) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Allocation_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Buyer_Allocation_Abbr.add(" ");
								}
								
								daily_Transporter_Nom_Cd_Buyer_Allocation.add(""+master_Transporter_Cd_Buyer_Allocation.elementAt(i));
								daily_Transporter_Nom_Abbr_Buyer_Allocation.add(""+master_Transporter_Abbr_Buyer_Allocation.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
											   "NVL(A.qty_sug,'0'),NVL(A.qty_offspec,'0'),NVL(A.qty_fm,'0')," +
											   "NVL(A.offspec_rate,'0'),NVL(A.offspec_flag,'N') " +
											   "FROM DLNG_DAILY_ALLOCATION_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.fgsa_no="+temp_regas_no+" " +
								   			   "AND A.customer_cd="+temp_cust_cd+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
								   			   "AND A.contract_type='C' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" " +
								   			   "AND MAPPING_ID='"+rset1.getString(1)+"' AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM DLNG_DAILY_ALLOCATION_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   "AND B.fgsa_no="+temp_regas_no+" " +
											   "AND B.customer_cd="+temp_cust_cd+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
											   "AND B.contract_type='C' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+" " +
											   "AND MAPPING_ID='"+rset1.getString(1)+"')";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL TableRU (CN) = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+")");
									daily_Buyer_Allocation_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Buyer_Allocation_Plant_Seq_No.add(rset6.getString(4));
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble(rset6.getString(5)))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble(rset6.getString(6)))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble(rset6.getString(7)))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble(rset6.getString(8)))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble(rset6.getString(9)))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble(rset6.getString(10)))));
									daily_Buyer_Allocation_Offspec_Flag.add(rset6.getString(11));
									daily_Meter_Based_Customer_flag.add("N");
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
									total_sug += Double.parseDouble(rset6.getString(7));
									total_offspec += Double.parseDouble(rset6.getString(8));
									total_fm += Double.parseDouble(rset6.getString(9));
								}
								else
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Allocation_Gen_Day_Time.add("");
									daily_Buyer_Allocation_Plant_Seq_No.add("0");
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Flag.add("N");
									daily_Meter_Based_Customer_flag.add("N");
								}
								
								queryString6 = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0') " +
					  			  			   "FROM FMS7_METER_TICKET_READING A " +
					  			  			   "WHERE A.meter_type='C' " +
					  			  			   "AND A.trans_cust_cd="+temp_cust_cd+" " +
					  			  			   "AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
					  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					
								//System.out.println("TOTAL MMBTU FOR METER READING --> queryString6 = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									if((""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)).equals("0.00"))
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))+Double.parseDouble(rset6.getString(3))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))+Double.parseDouble(rset6.getString(4))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += Double.parseDouble(rset6.getString(1));
											total_scm += Double.parseDouble(rset6.getString(2));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
									else
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += (Double.parseDouble(rset6.getString(1))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)));
											total_scm += (Double.parseDouble(rset6.getString(2))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Scm.elementAt(daily_Buyer_Allocation_Qty_Scm.size()-1)));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
								}
								
								++trans_count;
							}
						}
					}
					//System.out.println("---temp_cust_cd0----"+temp_cust_cd);
				}//End Of Code For CN Based Buyers ...
				
				
				master_Transporter_Count_Buyer_Allocation.add(""+trans_count);
				master_Transporter_Dcq_Buyer_Allocation.add(nf.format(total_dcq));
				
				queryString3 = "SELECT NVL(SUM(A.qty_mmbtu),'0'), " +
							   "NVL(SUM(A.reconcil_qty_mmbtu),'0'), " +
							   "NVL(SUM(A.qty_scm),'0'), " +
							   "NVL(SUM(A.reconcil_qty_scm),'0') " +
	  			  			   "FROM FMS7_METER_TICKET_READING A " +
	  			  			   "WHERE A.meter_type='T' " +
	  			  			   "AND A.trans_cust_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
	  			  			   "AND A.transporter_cd=0 " +
	  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
				
				//System.out.println("TOTAL MMBTU FOR METER READING --> queryString3 = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Meter_Reading_Transporter_Mmbtu.add(nf.format(Double.parseDouble(rset3.getString(1))+Double.parseDouble(rset3.getString(2))));
					daily_Meter_Reading_Transporter_Scm.add(nf.format(Double.parseDouble(rset3.getString(3))+Double.parseDouble(rset3.getString(4))));
		
					int meter_count = 0;
					double meter_gcv = 0;
					double meter_ncv = 0;
					
					queryString1 = "SELECT NVL(A.calc_gcv,'0'),NVL(A.define_ncv,'0') " +
		  			   			   "FROM FMS7_METER_TICKET_READING A " +
		  			   			   "WHERE A.meter_type='T' " +
		  			   			   "AND A.trans_cust_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
		  			   			   "AND A.transporter_cd=0 " +
		  			   			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
		  			   			   "AND A.calc_gcv IS NOT NULL AND A.calc_gcv>0 ORDER BY A.meter_seq_cd";
					
					//System.out.println("TOTAL MMBTU FOR METER READING --> queryString1 = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						++meter_count;
						meter_gcv += Double.parseDouble(rset1.getString(1));
						meter_ncv += Double.parseDouble(rset1.getString(2));
					}
					
					if(meter_count==0)
					{
						daily_Meter_Reading_Transporter_Calculated_Gcv.add(nf3.format(Double.parseDouble("0")));
						daily_Meter_Reading_Transporter_Calculated_Ncv.add(nf3.format(Double.parseDouble("0")));
					}
					else
					{
						daily_Meter_Reading_Transporter_Calculated_Gcv.add(nf3.format(meter_gcv/meter_count));
						daily_Meter_Reading_Transporter_Calculated_Ncv.add(nf3.format(meter_ncv/meter_count));
					}
				}
				else
				{
					daily_Meter_Reading_Transporter_Mmbtu.add(nf.format(Double.parseDouble("0")));
					daily_Meter_Reading_Transporter_Scm.add(nf.format(Double.parseDouble("0")));
					daily_Meter_Reading_Transporter_Calculated_Gcv.add(nf3.format(Double.parseDouble("0")));
					daily_Meter_Reading_Transporter_Calculated_Ncv.add(nf3.format(Double.parseDouble("0")));
				}
				
				if(total_seller_mmbtu>0)
				{
					master_Transporter_Mmbtu_Seller_Nom.add(nf.format(total_seller_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu_Seller_Nom.add("");
				}

				////System.out.println("seller_nom.size() = "+seller_nom.size());
				
				for(int z=0; z<seller_nom.size(); z++)
				{
					if(total_seller_mmbtu>0)
					{
						seller_Nom_Weighted_Avg.add(nf3.format(Double.parseDouble(""+seller_nom.elementAt(z))/total_seller_mmbtu));
						////System.out.println("seller_Nom_Weighted_Avg = "+nf3.format(Double.parseDouble(""+seller_nom.elementAt(z))/total_seller_mmbtu));
					}
					else
					{
						seller_Nom_Weighted_Avg.add("0.000000000000");
						////System.out.println("seller_Nom_Weighted_Avg = 0.000000000000");
					}
				}
				
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu_Buyer_Allocation.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu_Buyer_Allocation.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm_Buyer_Allocation.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm_Buyer_Allocation.add("");
				}
				
				if(total_offspec>0)
				{
					master_Transporter_Offspec_Buyer_Allocation.add(nf.format(total_offspec));
				}
				else
				{
					master_Transporter_Offspec_Buyer_Allocation.add("");
				}
				
				if(total_fm>0)
				{
					master_Transporter_FM_Buyer_Allocation.add(nf.format(total_fm));
				}
				else
				{
					master_Transporter_FM_Buyer_Allocation.add("");
				}
				
				daily_Customer_Meter_Allocation_Qty_Mmbtu.add(nf.format(total_customer_meter_mmbtu));
				daily_Customer_Meter_Allocation_Qty_Scm.add(nf.format(total_customer_meter_scm));
				daily_Customer_Meter_Allocation_Qty_Sug.add(nf.format(total_sug));
				
				final_daily_seller_mmbtu += total_seller_mmbtu;
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
				final_daily_sug += total_sug;
				final_daily_offspec += total_offspec;
				final_daily_fm += total_fm;
			}
			
			if(final_daily_seller_mmbtu>0)
			{
				daily_Total_Mmbtu_Seller_Nom = nf.format(final_daily_seller_mmbtu);
			}
			else
			{
				daily_Total_Mmbtu_Seller_Nom = "";
			}
			
			if(final_daily_mmbtu>0)
			{
				daily_Total_Mmbtu_Buyer_Allocation = nf.format(final_daily_mmbtu);
			}
			else
			{
				daily_Total_Mmbtu_Buyer_Allocation = "";
			}
			
			if(final_daily_scm>0)
			{
				daily_Total_Scm_Buyer_Allocation = nf.format(final_daily_scm);
			}
			else
			{
				daily_Total_Scm_Buyer_Allocation = "";
			}
			
			if(final_daily_offspec>0)
			{
				daily_Total_Offspec_Buyer_Allocation = nf.format(final_daily_offspec);
			}
			else
			{
				daily_Total_Offspec_Buyer_Allocation = "";
			}
			
			if(final_daily_fm>0)
			{
				daily_Total_FM_Buyer_Allocation = nf.format(final_daily_fm);
			}
			else
			{
				daily_Total_FM_Buyer_Allocation = "";
			}
			
			daily_Total_Dcq_Buyer_Allocation = nf.format(final_daily_dcq);
			daily_Total_Sug_Buyer_Allocation = nf.format(final_daily_sug);
			
			//RG20171121 Following code is for change in quantity for debit credit note.......
			Vector customer_cd1=new Vector();
			if((!gas_date.equalsIgnoreCase("null")) || (!gas_date.equalsIgnoreCase(null))){
				String nxtyr="";
				
				String dt=gas_date.substring(6,10);
				int yr1;
				String yr="";
				
				///ADDED BY RS09022017 FOR FINANCIAL YEAR LOGIC
				
				String march_day = ""+gas_date.substring(6,10)+"0301";
				String gas_dt = ""+gas_date.substring(6,10)+""+gas_date.substring(3,5)+gas_date.substring(0,2);
//				System.out.println("gas_dt....."+gas_dt+"....march_dt"+march_day);
				if(Double.parseDouble(march_day)>Double.parseDouble(gas_dt))
				{
					yr1=Integer.parseInt(dt);
					yr=(yr1-1)+"-"+yr1;
				} else {
					yr1=Integer.parseInt(dt)+1;
					yr=dt+"-"+yr1;
				}
				
				////////////////////////////////////
				
				String reamrk1="";
				
				
				
				queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
						"FINANCIAL_YEAR,SN_REV_NO,FGSA_REV_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,SALE_PRICE," +
						"GROSS_AMT_INR,NET_AMT_INR,CRITERIA,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,DR_CR_FLAG," +
						"DR_CR_NO,TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),DR_CR_EXG_RATE,DIFF_QTY,DIFF_AMT,DAY_DIFF," +
						"INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,DR_CR_DOC_NO,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
						"DR_CR_GROSS_AMT_USD,GROSS_AMT_USD,TAX_AMT_INR,TAX_STRUCT_CD,DR_CR_FIN_YEAR FROM FMS7_DR_CR_NOTE Where " +
						" FINANCIAL_YEAR='"+yr+"' AND CRITERIA='DIFF-QTY' and (dr_cr_flag='cr' OR dr_cr_flag='dr') ";
						System.out.println("queryString----"+queryString);
						rset = stmt.executeQuery(queryString);
						while(rset.next()){
							 customer_cd1.add(rset.getString(3));
							 plant_seq_no1.add(rset.getString(4));
							 dr_cr_flag.add(rset.getString(18));
							 reamrk1=rset.getString(28);
							 remark=reamrk1.substring(48,reamrk1.length()-1);
							 sn_no_cr_dr.add(rset.getString(1));
							 fgsa_no_cr_dr.add(rset.getString(2));
							 String con = rset.getString(5);
							 if(con.equals("S")) 
								 con="SN";
							 else if(con.equals("C")) 
								 con="CN";
							 else if(con.equals("L")) 
								 con="LOA";
							 
							 contract_type_cr_dr.add(con);
							 dates.add(remark);
						}
						for(int j=0;j<plant_seq_no1.size();j++){
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
							   "WHERE A.customer_cd='"+customer_cd1.elementAt(j)+"' AND A.seq_no='"+plant_seq_no1.elementAt(j)+"' " +
							   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
							   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							System.out.println("Customer Plant Name Fetch Query (LTCORA) = "+queryString3);
							rset = stmt.executeQuery(queryString3);
							if(rset.next())
							{
								Plant_Abbr.add(rset.getString(1));
							}
							else
							{
								Plant_Abbr.add("");
							}
			}
		}   //RG20171121 code is for change in quantity for debit credit note.......
			
		}
		catch(Exception e)
		{
			//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean:\n"+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyAllocationDetails() ...
	
	//Following Function Only Shows Seller Nominated Records Under Daily Allocation Form ...
	//Following Function Has Been Introduced By Samik Shah On 18th May, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 25th August, 2010 ...
	public void fetchDailyAllocationDetails_OLD()
	{
		methodName = "fetchDailyAllocationDetails()";
		try 
		{
			double sug_percentage = 0.3;
			double final_daily_seller_mmbtu = 0;
			
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			double final_daily_offspec = 0;
			double final_daily_fm = 0;
			double final_daily_sug = 0;
			
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			////System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd_Buyer_Allocation.add(rset.getString(1));
				master_Transporter_Abbr_Buyer_Allocation.add(rset.getString(2));
			}
			
			for(int i=0;i<master_Transporter_Cd_Buyer_Allocation.size();i++)
			{
				double total_seller_mmbtu = 0;
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_offspec = 0;
				double total_customer_meter_mmbtu = 0;
				double total_scm = 0;
				double total_fm = 0;
				double total_customer_meter_scm = 0;
				double total_sug = 0;
				
				Vector seller_nom = new Vector();
								
				//Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),A.sn_ref_no " +
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' " +
							   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   "AND A.fgsa_rev_no=B.fgsa_rev_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				////System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" AND A.sn_rev_no="+rset1.getString(4)+"";
						////System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Allocation_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							////System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Allocation_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Allocation_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
							
							////System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Seller_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+" - "+rset4.getString(2)+")");
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_seller_mmbtu += Double.parseDouble(rset4.getString(5));
								seller_nom.add(rset4.getString(5)==null?"0":rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
					
								daily_Buyer_Allocation_Fgsa_No.add(rset1.getString(1));
								daily_Buyer_Allocation_Fgsa_Rev_No.add(rset1.getString(2));
								daily_Buyer_Allocation_Sn_No.add(rset1.getString(3));
								daily_Buyer_Allocation_Sn_Rev_No.add(rset1.getString(4));
								daily_Buyer_Allocation_Cd.add(rset1.getString(5));
								String sn_ref_number = rset1.getString(7)==null?"":rset1.getString(7);
								if(!sn_ref_number.trim().equals(""))
								{
									sn_ref_no.add(sn_ref_number);
								}
								else
								{
									sn_ref_no.add("");
								}
								daily_Buyer_Allocation_Type.add("S");
								daily_Buyer_Allocation_Contract_Type.add("SN");
												
								
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Allocation_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Buyer_Allocation_Abbr.add(" ");
								}
								
								queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
								   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
								   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
								//System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									//SB20110915 if(Double.parseDouble(rset5.getString(1))>0)
									if(Double.parseDouble(rset5.getString(1))>=0)
									{
										daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));
										total_dcq += Double.parseDouble(rset5.getString(1));
									}
									else
									{
										daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
										total_dcq += Double.parseDouble(rset1.getString(6));
									}
								}
								else
								{
									daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
								}
														
								daily_Transporter_Nom_Cd_Buyer_Allocation.add(""+master_Transporter_Cd_Buyer_Allocation.elementAt(i));
								daily_Transporter_Nom_Abbr_Buyer_Allocation.add(""+master_Transporter_Abbr_Buyer_Allocation.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
											   "NVL(A.qty_sug,'0'),NVL(A.qty_offspec,'0')," +
											   "NVL(A.qty_fm,'0'),NVL(A.offspec_rate,'0'),NVL(A.offspec_flag,'N')  " +
											   "FROM DLNG_DAILY_ALLOCATION_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   //"AND A.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
								   			   "AND A.contract_type='S' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM DLNG_DAILY_ALLOCATION_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   //"AND B.fgsa_rev_no="+rset1.getString(2)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
											   "AND B.contract_type='S' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+")");
									daily_Buyer_Allocation_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Buyer_Allocation_Plant_Seq_No.add(rset6.getString(4));
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble(rset6.getString(5)))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble(rset6.getString(6)))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble(rset6.getString(7)))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble(rset6.getString(8)))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble(rset6.getString(9)))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble(rset6.getString(10)))));
									daily_Buyer_Allocation_Offspec_Flag.add(rset6.getString(11));
									daily_Meter_Based_Customer_flag.add("N");
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
									total_sug += Double.parseDouble(rset6.getString(7));
									total_offspec += Double.parseDouble(rset6.getString(8));
									total_fm += Double.parseDouble(rset6.getString(9));
								}
								else
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Allocation_Gen_Day_Time.add("");
									daily_Buyer_Allocation_Plant_Seq_No.add("0");
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Flag.add("N");
									daily_Meter_Based_Customer_flag.add("N");
								}
								
								queryString6 = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0') " +
					  			  			   "FROM FMS7_METER_TICKET_READING A " +
					  			  			   "WHERE A.meter_type='C' " +
					  			  			   "AND A.trans_cust_cd="+rset1.getString(5)+" " +
					  			  			   "AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
					  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					
								//System.out.println("TOTAL MMBTU FOR METER READING --> queryString6 = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									if((""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)).equals("0.00"))
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))+Double.parseDouble(rset6.getString(3))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))+Double.parseDouble(rset6.getString(4))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += Double.parseDouble(rset6.getString(1));
											total_scm += Double.parseDouble(rset6.getString(2));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
									else
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += (Double.parseDouble(rset6.getString(1))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)));
											total_scm += (Double.parseDouble(rset6.getString(2))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Scm.elementAt(daily_Buyer_Allocation_Qty_Scm.size()-1)));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
								}
								++trans_count;
							}
						}
					}
				}//End Of Code For SN Based Buyers ...
				
				//Following Code Is For LOA Based Buyers ...
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),A.loa_ref_no " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' " +
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND B.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				//System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+"";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Allocation_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Allocation_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Allocation_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
				
							//System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Seller_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+" - "+rset4.getString(2)+")");
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_seller_mmbtu += Double.parseDouble(rset4.getString(5));
								seller_nom.add(rset4.getString(5)==null?"0":rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
					
								daily_Buyer_Allocation_Fgsa_No.add(rset1.getString(1));
								daily_Buyer_Allocation_Fgsa_Rev_No.add("0");
								daily_Buyer_Allocation_Sn_No.add(rset1.getString(3));
								daily_Buyer_Allocation_Sn_Rev_No.add(rset1.getString(4));
								daily_Buyer_Allocation_Cd.add(rset1.getString(5));
								String sn_ref_number = rset1.getString(7)==null?"":rset1.getString(7);
								if(!sn_ref_number.trim().equals(""))
								{
									sn_ref_no.add(sn_ref_number);
								}
								else
								{
									sn_ref_no.add("");
								}
								daily_Buyer_Allocation_Type.add("L");
								daily_Buyer_Allocation_Contract_Type.add("LOA");
																				
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Allocation_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Buyer_Allocation_Abbr.add(" ");
								}
								
								queryString5 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
					   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
					   			   			   "AND A.loa_no="+rset1.getString(3)+" " +
					   			   			   "AND A.loa_rev_no="+rset1.getString(4)+" " +
					   			   			   "AND A.from_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
					   			   			   "AND A.to_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
								//System.out.println("Customer SN DCQ Fetch Query = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
								//SB20110915	if(Double.parseDouble(rset5.getString(1))>0)
									if(Double.parseDouble(rset5.getString(1))>=0)
									{
										daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset5.getString(1))));
										total_dcq += Double.parseDouble(rset5.getString(1));
									}
									else
									{
										daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
										total_dcq += Double.parseDouble(rset1.getString(6));
									}
								}
								else
								{
									daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
								}
															
								daily_Transporter_Nom_Cd_Buyer_Allocation.add(""+master_Transporter_Cd_Buyer_Allocation.elementAt(i));
								daily_Transporter_Nom_Abbr_Buyer_Allocation.add(""+master_Transporter_Abbr_Buyer_Allocation.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
											   "NVL(A.qty_sug,'0'),NVL(A.qty_offspec,'0'),NVL(A.qty_fm,'0')," +
											   "NVL(A.offspec_rate,'0'),NVL(A.offspec_flag,'N') " +
											   "FROM DLNG_DAILY_ALLOCATION_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   //"AND A.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   //"AND A.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
								   			   "AND A.contract_type='L' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM DLNG_DAILY_ALLOCATION_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   //"AND B.sn_rev_no="+rset1.getString(4)+" " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   //"AND B.fgsa_rev_no=0 " + //Commented By Samik Shah On 27th August, 2010 ...
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
											   "AND B.contract_type='L' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+")");
									daily_Buyer_Allocation_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Buyer_Allocation_Plant_Seq_No.add(rset6.getString(4));
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble(rset6.getString(5)))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble(rset6.getString(6)))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble(rset6.getString(7)))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble(rset6.getString(8)))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble(rset6.getString(9)))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble(rset6.getString(10)))));
									daily_Buyer_Allocation_Offspec_Flag.add(rset6.getString(11));
									daily_Meter_Based_Customer_flag.add("N");
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
									total_sug += Double.parseDouble(rset6.getString(7));
									total_offspec += Double.parseDouble(rset6.getString(8));
									total_fm += Double.parseDouble(rset6.getString(9));
								}
								else
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Allocation_Gen_Day_Time.add("");
									daily_Buyer_Allocation_Plant_Seq_No.add("0");
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Flag.add("N");
									daily_Meter_Based_Customer_flag.add("N");
								}
								
								queryString6 = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0') " +
					  			  			   "FROM FMS7_METER_TICKET_READING A " +
					  			  			   "WHERE A.meter_type='C' " +
					  			  			   "AND A.trans_cust_cd="+rset1.getString(5)+" " +
					  			  			   "AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
					  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					
								//System.out.println("TOTAL MMBTU FOR METER READING --> queryString6 = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									if((""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)).equals("0.00"))
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))+Double.parseDouble(rset6.getString(3))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))+Double.parseDouble(rset6.getString(4))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += Double.parseDouble(rset6.getString(1));
											total_scm += Double.parseDouble(rset6.getString(2));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
									else
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += (Double.parseDouble(rset6.getString(1))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)));
											total_scm += (Double.parseDouble(rset6.getString(2))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Scm.elementAt(daily_Buyer_Allocation_Qty_Scm.size()-1)));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
								}
								
								++trans_count;
							}
						}
					}
				}//End Of Code For LOA Based Buyers ...
				
				
				//Following Code Is For RE-GAS Based Buyers ...
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0') " +
							   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE A.contract_start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.contract_end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
							   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
							   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
							   "AND C.transporter_cd='"+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+"' " +
							   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+"";
						//System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Allocation_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Allocation_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Allocation_Plant_Abbr.add("");
							}
							
							queryString4 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
							   			   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
							   			   "FROM FMS7_DAILY_SELLER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
							   			   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
							   			   "WHERE B.sn_no="+rset1.getString(3)+" " +
							   			   "AND B.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
							   			   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
							   			   "AND B.plant_seq_no="+rset2.getString(1)+")";
				
							//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (RE-GAS) = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								daily_Seller_Gen_Day_With_Rev_No.add("("+rset4.getString(1)+" - "+rset4.getString(2)+")");
								daily_Seller_Gen_Day_Time.add(rset4.getString(3)==null?"":rset4.getString(3));
								daily_Seller_Nom_Plant_Seq_No.add(rset4.getString(4));
								daily_Seller_Nom_Qty_Mmbtu.add(rset4.getString(5)=="0"?"":rset4.getString(5));
								daily_Seller_Nom_Qty_Scm.add(rset4.getString(6)=="0"?"":rset4.getString(6));
								total_seller_mmbtu += Double.parseDouble(rset4.getString(5));
								seller_nom.add(rset4.getString(5)==null?"0":rset4.getString(5));
								
								queryString3 = "SELECT NVL(A.plant_name,'') FROM FMS7_CUSTOMER_PLANT_DTL A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset4.getString(4)+" " +
											   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
											   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
											   "AND B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
								//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add(rset3.getString(1));
								}
								else
								{
									daily_Seller_Nom_Plant_Seq_Abbr.add("");
								}
					
								daily_Buyer_Allocation_Fgsa_No.add(rset1.getString(1));
								daily_Buyer_Allocation_Fgsa_Rev_No.add("0");
								daily_Buyer_Allocation_Sn_No.add(rset1.getString(3));
								daily_Buyer_Allocation_Sn_Rev_No.add(rset1.getString(4));
								daily_Buyer_Allocation_Cd.add(rset1.getString(5));
								daily_Buyer_Allocation_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								sn_ref_no.add("");
								daily_Buyer_Allocation_Type.add("R");
								daily_Buyer_Allocation_Contract_Type.add("RE-GAS");
																				
								queryString5 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
											   "WHERE A.customer_cd="+rset1.getString(5)+"";
								//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString5);
								rset5 = stmt5.executeQuery(queryString5);
								if(rset5.next())
								{
									daily_Buyer_Allocation_Abbr.add(rset5.getString(1));
								}
								else
								{
									daily_Buyer_Allocation_Abbr.add(" ");
								}
								
								daily_Transporter_Nom_Cd_Buyer_Allocation.add(""+master_Transporter_Cd_Buyer_Allocation.elementAt(i));
								daily_Transporter_Nom_Abbr_Buyer_Allocation.add(""+master_Transporter_Abbr_Buyer_Allocation.elementAt(i));
								
								queryString6 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
											   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0')," +
											   "NVL(A.qty_sug,'0'),NVL(A.qty_offspec,'0'),NVL(A.qty_fm,'0')," +
											   "NVL(A.offspec_rate,'0'),NVL(A.offspec_flag,'N') " +
											   "FROM DLNG_DAILY_ALLOCATION_DTL A " +
								   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
								   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
								   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
								   			   "AND A.contract_type='R' AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
								   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
											   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM DLNG_DAILY_ALLOCATION_DTL B " +
											   "WHERE B.sn_no="+rset1.getString(3)+" " +
											   "AND B.fgsa_no="+rset1.getString(1)+" " +
											   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
											   "AND B.contract_type='R' AND B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
											   "AND B.plant_seq_no="+rset2.getString(1)+")";
								
								//System.out.println("Fetch Query FROM FMS7_DAILY_SELLER_NOM_DTL Table (RE-GAS) = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("("+rset6.getString(1)+")");
									daily_Buyer_Allocation_Gen_Day_Time.add(rset6.getString(3)==null?"":rset6.getString(3));
									daily_Buyer_Allocation_Plant_Seq_No.add(rset6.getString(4));
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble(rset6.getString(5)))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble(rset6.getString(6)))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble(rset6.getString(7)))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble(rset6.getString(8)))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble(rset6.getString(9)))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble(rset6.getString(10)))));
									daily_Buyer_Allocation_Offspec_Flag.add(rset6.getString(11));
									daily_Meter_Based_Customer_flag.add("N");
									total_mmbtu += Double.parseDouble(rset6.getString(5));
									total_scm += Double.parseDouble(rset6.getString(6));
									total_sug += Double.parseDouble(rset6.getString(7));
									total_offspec += Double.parseDouble(rset6.getString(8));
									total_fm += Double.parseDouble(rset6.getString(9));
								}
								else
								{
									daily_Buyer_Allocation_Gen_Day_With_Rev_No.add("");
									daily_Buyer_Allocation_Gen_Day_Time.add("");
									daily_Buyer_Allocation_Plant_Seq_No.add("0");
									daily_Buyer_Allocation_Qty_Mmbtu.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Scm.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Sug.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_Offspec.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Qty_FM.add(nf.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Rate.add(nf2.format((Double.parseDouble("0"))));
									daily_Buyer_Allocation_Offspec_Flag.add("N");
									daily_Meter_Based_Customer_flag.add("N");
								}
								
								queryString6 = "SELECT NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0'),NVL(A.reconcil_qty_mmbtu,'0'),NVL(A.reconcil_qty_scm,'0') " +
					  			  			   "FROM FMS7_METER_TICKET_READING A " +
					  			  			   "WHERE A.meter_type='C' " +
					  			  			   "AND A.trans_cust_cd="+rset1.getString(5)+" " +
					  			  			   "AND A.transporter_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
					  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
					
								//System.out.println("TOTAL MMBTU FOR METER READING --> queryString6 = "+queryString6);
								rset6 = stmt6.executeQuery(queryString6);
								if(rset6.next())
								{
									if((""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)).equals("0.00"))
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))+Double.parseDouble(rset6.getString(3))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))+Double.parseDouble(rset6.getString(4))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += Double.parseDouble(rset6.getString(1));
											total_scm += Double.parseDouble(rset6.getString(2));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
									else
									{
										if(!rset6.getString(1).equals("0"))
										{
											daily_Buyer_Allocation_Qty_Mmbtu.setElementAt(rset6.getString(1)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(1))),daily_Buyer_Allocation_Qty_Mmbtu.size()-1);
											daily_Buyer_Allocation_Qty_Scm.setElementAt(rset6.getString(2)=="0"?"0.00":nf.format(Double.parseDouble(rset6.getString(2))),daily_Buyer_Allocation_Qty_Scm.size()-1);
											total_mmbtu += (Double.parseDouble(rset6.getString(1))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Mmbtu.elementAt(daily_Buyer_Allocation_Qty_Mmbtu.size()-1)));
											total_scm += (Double.parseDouble(rset6.getString(2))-Double.parseDouble(""+daily_Buyer_Allocation_Qty_Scm.elementAt(daily_Buyer_Allocation_Qty_Scm.size()-1)));
											total_customer_meter_mmbtu += Double.parseDouble(rset6.getString(1));
											total_customer_meter_scm += Double.parseDouble(rset6.getString(2));
											if((""+daily_Buyer_Allocation_Qty_Sug.elementAt(daily_Buyer_Allocation_Qty_Sug.size()-1)).equals("0.00"))
											{
												daily_Buyer_Allocation_Qty_Sug.setElementAt(nf.format((Double.parseDouble(rset6.getString(1))*sug_percentage)/100),daily_Buyer_Allocation_Qty_Sug.size()-1);
												total_sug += (Double.parseDouble(rset6.getString(1))*sug_percentage)/100;
											}	
											daily_Meter_Based_Customer_flag.setElementAt("Y",daily_Meter_Based_Customer_flag.size()-1);
										}
									}
								}
								
								++trans_count;
							}
						}
					}
				}//End Of Code For RE-GAS Based Buyers ...
				
				master_Transporter_Count_Buyer_Allocation.add(""+trans_count);
				master_Transporter_Dcq_Buyer_Allocation.add(nf.format(total_dcq));
				
				queryString3 = "SELECT NVL(SUM(A.qty_mmbtu),'0'), " +
							   "NVL(SUM(A.reconcil_qty_mmbtu),'0'), " +
							   "NVL(SUM(A.qty_scm),'0'), " +
							   "NVL(SUM(A.reconcil_qty_scm),'0') " +
	  			  			   "FROM FMS7_METER_TICKET_READING A " +
	  			  			   "WHERE A.meter_type='T' " +
	  			  			   "AND A.trans_cust_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
	  			  			   "AND A.transporter_cd=0 " +
	  			  			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY')";
				
				//System.out.println("TOTAL MMBTU FOR METER READING --> queryString3 = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					daily_Meter_Reading_Transporter_Mmbtu.add(nf.format(Double.parseDouble(rset3.getString(1))+Double.parseDouble(rset3.getString(2))));
					daily_Meter_Reading_Transporter_Scm.add(nf.format(Double.parseDouble(rset3.getString(3))+Double.parseDouble(rset3.getString(4))));
		
					int meter_count = 0;
					double meter_gcv = 0;
					double meter_ncv = 0;
					
					queryString1 = "SELECT NVL(A.calc_gcv,'0'),NVL(A.define_ncv,'0') " +
		  			   			   "FROM FMS7_METER_TICKET_READING A " +
		  			   			   "WHERE A.meter_type='T' " +
		  			   			   "AND A.trans_cust_cd="+master_Transporter_Cd_Buyer_Allocation.elementAt(i)+" " +
		  			   			   "AND A.transporter_cd=0 " +
		  			   			   "AND A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
		  			   			   "AND A.calc_gcv IS NOT NULL AND A.calc_gcv>0 ORDER BY A.meter_seq_cd";
					
					//System.out.println("TOTAL MMBTU FOR METER READING --> queryString1 = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						++meter_count;
						meter_gcv += Double.parseDouble(rset1.getString(1));
						meter_ncv += Double.parseDouble(rset1.getString(2));
					}
					
					if(meter_count==0)
					{
						daily_Meter_Reading_Transporter_Calculated_Gcv.add(nf3.format(Double.parseDouble("0")));
						daily_Meter_Reading_Transporter_Calculated_Ncv.add(nf3.format(Double.parseDouble("0")));
					}
					else
					{
						daily_Meter_Reading_Transporter_Calculated_Gcv.add(nf3.format(meter_gcv/meter_count));
						daily_Meter_Reading_Transporter_Calculated_Ncv.add(nf3.format(meter_ncv/meter_count));
					}
				}
				else
				{
					daily_Meter_Reading_Transporter_Mmbtu.add(nf.format(Double.parseDouble("0")));
					daily_Meter_Reading_Transporter_Scm.add(nf.format(Double.parseDouble("0")));
					daily_Meter_Reading_Transporter_Calculated_Gcv.add(nf3.format(Double.parseDouble("0")));
					daily_Meter_Reading_Transporter_Calculated_Ncv.add(nf3.format(Double.parseDouble("0")));
				}
				
				if(total_seller_mmbtu>0)
				{
					master_Transporter_Mmbtu_Seller_Nom.add(nf.format(total_seller_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu_Seller_Nom.add("");
				}

				//System.out.println("seller_nom.size() = "+seller_nom.size());
				
				for(int z=0; z<seller_nom.size(); z++)
				{
					if(total_seller_mmbtu>0)
					{
						seller_Nom_Weighted_Avg.add(nf3.format(Double.parseDouble(""+seller_nom.elementAt(z))/total_seller_mmbtu));
						//System.out.println("seller_Nom_Weighted_Avg = "+nf3.format(Double.parseDouble(""+seller_nom.elementAt(z))/total_seller_mmbtu));
					}
					else
					{
						seller_Nom_Weighted_Avg.add("0.000000000000");
						//System.out.println("seller_Nom_Weighted_Avg = 0.000000000000");
					}
				}
				
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu_Buyer_Allocation.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu_Buyer_Allocation.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm_Buyer_Allocation.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm_Buyer_Allocation.add("");
				}
				
				if(total_offspec>0)
				{
					master_Transporter_Offspec_Buyer_Allocation.add(nf.format(total_offspec));
				}
				else
				{
					master_Transporter_Offspec_Buyer_Allocation.add("");
				}
				
				if(total_fm>0)
				{
					master_Transporter_FM_Buyer_Allocation.add(nf.format(total_fm));
				}
				else
				{
					master_Transporter_FM_Buyer_Allocation.add("");
				}
				
				daily_Customer_Meter_Allocation_Qty_Mmbtu.add(nf.format(total_customer_meter_mmbtu));
				daily_Customer_Meter_Allocation_Qty_Scm.add(nf.format(total_customer_meter_scm));
				daily_Customer_Meter_Allocation_Qty_Sug.add(nf.format(total_sug));
				
				final_daily_seller_mmbtu += total_seller_mmbtu;
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
				final_daily_sug += total_sug;
				final_daily_offspec += total_offspec;
				final_daily_fm += total_fm;
			}
			
			if(final_daily_seller_mmbtu>0)
			{
				daily_Total_Mmbtu_Seller_Nom = nf.format(final_daily_seller_mmbtu);
			}
			else
			{
				daily_Total_Mmbtu_Seller_Nom = "";
			}
			
			if(final_daily_mmbtu>0)
			{
				daily_Total_Mmbtu_Buyer_Allocation = nf.format(final_daily_mmbtu);
			}
			else
			{
				daily_Total_Mmbtu_Buyer_Allocation = "";
			}
			
			if(final_daily_scm>0)
			{
				daily_Total_Scm_Buyer_Allocation = nf.format(final_daily_scm);
			}
			else
			{
				daily_Total_Scm_Buyer_Allocation = "";
			}
			
			if(final_daily_offspec>0)
			{
				daily_Total_Offspec_Buyer_Allocation = nf.format(final_daily_offspec);
			}
			else
			{
				daily_Total_Offspec_Buyer_Allocation = "";
			}
			
			if(final_daily_fm>0)
			{
				daily_Total_FM_Buyer_Allocation = nf.format(final_daily_fm);
			}
			else
			{
				daily_Total_FM_Buyer_Allocation = "";
			}
			
			daily_Total_Dcq_Buyer_Allocation = nf.format(final_daily_dcq);
			daily_Total_Sug_Buyer_Allocation = nf.format(final_daily_sug);
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean:\n"+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyAllocationDetails() ...
	
	String Emp_cd= "";
	String Customer_access_flag = "N";
	
	//Following Method Has Been Introduced By Samik Shah On 23rd December, 2010 ...
	public void fetchCustomerList()
	{
		methodName = "fetchCustomerList()";
		try
		{
			if(Customer_access_flag.equals("Y"))
			{
				queryString = "SELECT DISTINCT(A.customer_cd),customer_abbr,customer_name "
						+ "FROM FMS7_CUSTOMER_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B "
						+ "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' "
						+ "ORDER BY customer_abbr";
			} else {
				queryString = "SELECT DISTINCT(customer_cd),customer_abbr,customer_name FROM FMS7_CUSTOMER_MST ORDER BY customer_abbr";
			}
			
			//System.out.println("Master Customer Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				CUSTOMER_CD.add(rset.getString(1)==null?"0":rset.getString(1));
				CUSTOMER_ABBR.add(rset.getString(2)==null?" ":rset.getString(2));
				CUSTOMER_NAME.add(rset.getString(3)==null?" ":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Following Method Has Been Introduced By Samik Shah On 23rd December, 2010 ...
	public void fetchFgsaSnListOfSelectedCustomer()
	{
		methodName = "fetchFgsaSnListOfSelectedCustomer()";
		try
		{
			String query_part = "";
			from_dt = "01/"+month+"/"+year;
			
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+from_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				to_dt = rset.getString(1);
			}
			
			for(int z=0; z<Integer.parseInt(to_dt.substring(0,2)); z++)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(contract_type.trim().equalsIgnoreCase("S") || contract_type.trim().equalsIgnoreCase("L"))
					{
						if(z<(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN START_DT AND END_DT) OR ";
						}
						else if(z==(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN START_DT AND END_DT)";
						}
					}
					else if(contract_type.trim().equalsIgnoreCase("R"))
					{
						if(z<(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN CONTRACT_START_DT AND CONTRACT_END_DT) OR ";
						}
						else if(z==(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN CONTRACT_START_DT AND CONTRACT_END_DT)";
						}
					}
				}
			}
			
			if(contract_type.trim().equalsIgnoreCase("S"))
			{
				queryString = "SELECT DISTINCT fgsa_no,sn_no FROM DLNG_SN_MST " +
						  "WHERE customer_cd="+customer_cd+" AND ("+query_part+") AND " +
						  "START_DT IS NOT NULL AND END_DT IS NOT NULL " +
						  "ORDER BY fgsa_no,sn_no";
			}
			else if(contract_type.trim().equalsIgnoreCase("L"))
			{
				queryString = "SELECT DISTINCT tender_no,loa_no FROM FMS7_LOA_MST " +
				  "WHERE customer_cd="+customer_cd+" AND ("+query_part+") AND " +
				  "START_DT IS NOT NULL AND END_DT IS NOT NULL " +
				  "ORDER BY tender_no,loa_no";
			}
			else if(contract_type.trim().equalsIgnoreCase("R"))
			{
				queryString = "SELECT DISTINCT RE_GAS_NO,CARGO_SEQ_NO FROM FMS7_RE_GAS_CARGO_DTL " +
				  "WHERE customer_cd="+customer_cd+" AND ("+query_part+") AND " +
				  "CONTRACT_START_DT IS NOT NULL AND CONTRACT_END_DT IS NOT NULL " +
				  "ORDER BY RE_GAS_NO,CARGO_SEQ_NO";
			}
			//System.out.println("Fetch Query For Selected Customer = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				FGSA_SN_NO.add((rset.getString(1)==null?"0":rset.getString(1))+"-"+(rset.getString(2)==null?"0":rset.getString(2)));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Following Method Has Been Introduced By Samik Shah On 27th December, 2010 ...
	public void fetchPlantOfSelectedCustomer()
	{
		methodName = "fetchPlantOfSelectedCustomer()";
		try
		{
			String [] fgsasnno = fgsa_sn_no.split("-");
			from_dt = "01/"+month+"/"+year;
			
			queryString = "SELECT DISTINCT A.plant_seq_no,B.plant_name " +
						  "FROM FMS7_SN_PLANT_MST A, FMS7_CUSTOMER_PLANT_DTL B " +
						  "WHERE A.customer_cd="+customer_cd+" AND B.customer_cd=A.customer_cd AND " +
						  "A.fgsa_no="+fgsasnno[0]+" AND A.sn_no="+fgsasnno[1]+" AND " +
						  "A.plant_seq_no=B.seq_no AND B.eff_dt=(SELECT MAX(C.eff_dt) " +
						  "FROM FMS7_CUSTOMER_PLANT_DTL C WHERE B.customer_cd=C.customer_cd AND " +
						  "B.seq_no=C.seq_no AND C.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))" +
						  "ORDER BY A.plant_seq_no";
			//System.out.println("Master SN-Plant Fetch Query For Selected Customer = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				PLANT_SEQ_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				PLANT_NAME.add(rset.getString(2)==null?" ":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Following Function Shows Liability Details Records of Form which has Been Introduced By Achal Pathak On 28th October, 2010 ...	
	public void fetchLiabilityDetails()
	{
		methodName = "fetchLiabilityDetails()";
		try 
		{
			from_dt = "01/"+month+"/"+year;
			
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+from_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				to_dt = rset.getString(1);
			}
			
			for(int z=0; z<Integer.parseInt(to_dt.substring(0,2)); z++)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DAY'), " +
							  "TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					MONTH_DAYS.add(rset.getString(1));
					MONTH_DATES.add(rset.getString(2));
				}
			}
			
			String [] fgsa_sn_arr =  fgsa_sn_no.split("-");			
			String oblig_dcq="";
			String oblig_pndcq="";
			
			String liability_per = "100";
			String sn_rev_no = "";
			String fgsa_rev_no = "";
			if(fgsa_sn_arr.length==2)
			{											
				if(contract_type.trim().equalsIgnoreCase("S"))
				{
				   queryString = "SELECT A.LIABILITY_PER,A.SN_REV_NO,A.FGSA_REV_NO FROM FMS7_SN_LD_DTL A " +
					  "WHERE A.SN_NO='"+fgsa_sn_arr[1]+"' AND A.FGSA_NO='"+fgsa_sn_arr[0]+"' " +
					  "AND A.CUSTOMER_CD='"+customer_cd+"' AND " +			      
				      "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_LD_DTL B WHERE " +
				      "A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD )";
				}
				else if(contract_type.trim().equalsIgnoreCase("L"))
				{
					queryString = "SELECT A.LIABILITY_PER,A.LOA_REV_NO,A.FGSA_REV_NO FROM FMS7_LOA_LD_DTL A " +
					  "WHERE A.LOA_NO='"+fgsa_sn_arr[1]+"' AND A.FGSA_NO='"+fgsa_sn_arr[0]+"' " +
					  "AND A.CUSTOMER_CD='"+customer_cd+"' AND " +			      
				      "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_LD_DTL B WHERE " +
				      "A.LOA_NO=B.LOA_NO AND A.FGSA_NO=B.FGSA_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD )";
				}
				else if(contract_type.trim().equalsIgnoreCase("R"))
				{
					queryString = "SELECT A.LIABILITY_PER,A.RE_GAS_NO,A.RE_GAS_REV_NO FROM FMS7_RE_GAS_LD_DTL A " +
					  "WHERE A.RE_GAS_NO='"+fgsa_sn_arr[0]+"' " +
					  "AND A.CUSTOMER_CD='"+customer_cd+"' AND " +			      
				      "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_LD_DTL B WHERE " +
				      "A.RE_GAS_NO=B.RE_GAS_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD )";
				}
			    //System.out.println("LIABILITY_PER = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					liability_per = rset.getString(1)==null?"100":rset.getString(1);
					sn_rev_no = rset.getString(2)==null?"0":rset.getString(2);
					fgsa_rev_no = rset.getString(3)==null?"0":rset.getString(3);
				}
				
				
				for(int z=0; z<MONTH_DATES.size(); z++)
				{
					queryString = "SELECT SUM(QTY_FM) FROM DLNG_DAILY_ALLOCATION_DTL WHERE SN_NO='"+fgsa_sn_arr[1]+"' " +
								  "AND FGSA_NO='"+fgsa_sn_arr[0]+"' AND CUSTOMER_CD='"+customer_cd+"' " +
								  "AND GAS_DT=TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') " +
							      "AND CONTRACT_TYPE='"+contract_type+"'";
					////System.out.println("SUM(QTY_FM) = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						QTY_FM.add(rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1))));						
					}
					else
					{
						QTY_FM.add("");
					}
					
					queryString = "SELECT SUM(QTY_OFFSPEC) FROM DLNG_DAILY_ALLOCATION_DTL WHERE SN_NO='"+fgsa_sn_arr[1]+"' " +
					  "AND FGSA_NO='"+fgsa_sn_arr[0]+"' AND CUSTOMER_CD='"+customer_cd+"' " +
					  "AND GAS_DT=TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') " +
				      "AND CONTRACT_TYPE='"+contract_type+"' AND (OFFSPEC_FLAG is null OR OFFSPEC_FLAG='N')";
					//System.out.println("SUM(QTY_OFFSPEC) = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						QTY_OFFSPEC.add(rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1))));						
					}
					else
					{
						QTY_OFFSPEC.add("");
					}
								
					queryString = "SELECT SUM(A.QTY_MMBTU) FROM FMS7_DAILY_BUYER_NOM_DTL A WHERE A.SN_NO='"+fgsa_sn_arr[1]+"' " +
					  "AND A.FGSA_NO='"+fgsa_sn_arr[0]+"' AND A.CUSTOMER_CD='"+customer_cd+"' " +
					  "AND A.GAS_DT=TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') " +
				      "AND A.CONTRACT_TYPE='"+contract_type+"' AND A.NOM_REV_NO=(SELECT MAX(B.NOM_REV_NO) FROM FMS7_DAILY_BUYER_NOM_DTL B WHERE " +
				      "A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD AND A.GAS_DT=B.GAS_DT AND " +
				      "A.CONTRACT_TYPE=B.CONTRACT_TYPE AND A.PLANT_SEQ_NO=B.PLANT_SEQ_NO AND A.TRANSPORTER_CD=B.TRANSPORTER_CD)";
					//System.out.println("SUM(QTY_MMBTU) = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						oblig_pndcq = rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1)));										
					}
					else
					{
						oblig_pndcq = "";
					}	
					
					//Logic for DCQ and Seller Obligation Quantity by Contract Type
					if(contract_type.trim().equalsIgnoreCase("S"))
					{
						queryString = "SELECT A.ACTUAL_OBLIG_QTY FROM FMS7_SN_TOP_DTL A,DLNG_SN_MST C WHERE " +
						  "A.SN_NO='"+fgsa_sn_arr[1]+"' AND A.FGSA_NO='"+fgsa_sn_arr[0]+"' AND " +
						  "A.CUSTOMER_CD='"+customer_cd+"' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
						  "FMS7_SN_TOP_DTL B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND " +
						  "A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.SN_NO=C.SN_NO AND A.FGSA_NO=C.FGSA_NO AND " +
						  "A.CUSTOMER_CD=C.CUSTOMER_CD AND A.SN_REV_NO=C.SN_REV_NO AND A.FGSA_REV_NO=C.FGSA_REV_NO AND " +
					      "(TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') BETWEEN C.START_DT AND C.END_DT)";
						//System.out.println("A.ACTUAL_OBLIG_QTY = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							SELLER_OBLIG.add(rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							SELLER_OBLIG.add("");
						}
						queryString = "SELECT DCQ FROM FMS7_SN_DCQ_DTL WHERE SN_NO='"+fgsa_sn_arr[1]+"' " +
						  "AND FGSA_NO='"+fgsa_sn_arr[0]+"' AND CUSTOMER_CD='"+customer_cd+"' " +
						  "AND FGSA_REV_NO='"+fgsa_rev_no+"' AND SN_REV_NO='"+sn_rev_no+"' " +
						  "AND (TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') BETWEEN FROM_DT AND TO_DT) ";
						//System.out.println("DCQ = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{							
							oblig_dcq = rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1)) * Double.parseDouble(liability_per) / 100 );																							
						}
						else
						{
							queryString1 = "SELECT DCQ FROM DLNG_SN_MST WHERE SN_NO='"+fgsa_sn_arr[1]+"' " +
							  "AND FGSA_NO='"+fgsa_sn_arr[0]+"' AND CUSTOMER_CD='"+customer_cd+"' " +
							  "AND FGSA_REV_NO='"+fgsa_rev_no+"' AND SN_REV_NO='"+sn_rev_no+"' " +
							  "AND (TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') BETWEEN START_DT AND END_DT) ";
							//System.out.println("DCQ = "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{	
								oblig_dcq = rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(1)) * Double.parseDouble(liability_per) / 100 );
							}
							else
							{
								oblig_dcq = "";
							}
						}
					}
					else if(contract_type.trim().equalsIgnoreCase("L"))
					{
						queryString = "SELECT A.ACTUAL_OBLIG_QTY FROM FMS7_LOA_TOP_DTL A,FMS7_LOA_MST C WHERE " +
						  "A.LOA_NO='"+fgsa_sn_arr[1]+"' AND A.FGSA_NO='"+fgsa_sn_arr[0]+"' AND " +
						  "A.CUSTOMER_CD='"+customer_cd+"' AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM " +
						  "FMS7_LOA_TOP_DTL B WHERE A.LOA_NO=B.LOA_NO AND A.FGSA_NO=B.FGSA_NO AND " +
						  "A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.LOA_NO=C.LOA_NO AND A.FGSA_NO=C.TENDER_NO AND " +
						  "A.CUSTOMER_CD=C.CUSTOMER_CD AND A.LOA_REV_NO=C.LOA_REV_NO AND " +					
					      "(TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') BETWEEN C.START_DT AND C.END_DT)";
						System.out.println("A.ACTUAL_OBLIG_QTY = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							SELLER_OBLIG.add(rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							SELLER_OBLIG.add("");
						}
						queryString = "SELECT DCQ FROM FMS7_LOA_DCQ_DTL WHERE LOA_NO='"+fgsa_sn_arr[1]+"' " +
						  "AND TENDER_NO='"+fgsa_sn_arr[0]+"' AND CUSTOMER_CD='"+customer_cd+"' " +
						  "AND LOA_REV_NO='"+sn_rev_no+"' " +
						  "AND (TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') BETWEEN FROM_DT AND TO_DT) ";
						//System.out.println("DCQ = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{							
							oblig_dcq = rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1)) * Double.parseDouble(liability_per) / 100 );																							
						}
						else
						{
							queryString1 = "SELECT DCQ FROM FMS7_LOA_MST WHERE LOA_NO='"+fgsa_sn_arr[1]+"' " +
							  "AND TENDER_NO='"+fgsa_sn_arr[0]+"' AND CUSTOMER_CD='"+customer_cd+"' " +
							  "AND LOA_REV_NO='"+sn_rev_no+"' " +
							  "AND (TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') BETWEEN START_DT AND END_DT) ";
							//System.out.println("DCQ = "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{	
								oblig_dcq = rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(1)) * Double.parseDouble(liability_per) / 100 );
							}
							else
							{
								oblig_dcq = "";
							}
						}
					}
					else if(contract_type.trim().equalsIgnoreCase("R"))
					{
						queryString = "SELECT A.ACTUAL_OBLIG_QTY FROM FMS7_RE_GAS_TOP_DTL A,FMS7_RE_GAS_MST C WHERE " +
						  "A.RE_GAS_NO ='"+fgsa_sn_arr[0]+"' AND " +
						  "A.CUSTOMER_CD='"+customer_cd+"' AND A.RE_GAS_REV_NO =(SELECT MAX(B.RE_GAS_REV_NO) FROM " +
						  "FMS7_RE_GAS_TOP_DTL B WHERE A.RE_GAS_NO=B.RE_GAS_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND " +
						  "A.RE_GAS_NO=C.RE_GAS_NO AND " +
						  "A.CUSTOMER_CD=C.CUSTOMER_CD AND A.RE_GAS_REV_NO=C.REV_NO AND " +
					      "(TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') BETWEEN C.START_DT AND C.END_DT)";
						//System.out.println("A.ACTUAL_OBLIG_QTY = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							SELLER_OBLIG.add(rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							SELLER_OBLIG.add("");
						}
						
						queryString = "SELECT DCQ_QTY FROM FMS7_RE_GAS_CARGO_DTL WHERE CARGO_SEQ_NO='"+fgsa_sn_arr[1]+"' " +
						  "AND RE_GAS_NO='"+fgsa_sn_arr[0]+"' AND CUSTOMER_CD='"+customer_cd+"' " +
						  "AND RE_GAS_REV_NO ='"+fgsa_rev_no+"' " ;					  
						//System.out.println("DCQ = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{							
							oblig_dcq = rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1)) * Double.parseDouble(liability_per) / 100 );																							
						}
						else
						{
							oblig_dcq = "";
						}
					}
					
					
					if(oblig_dcq.equals("0.00") || oblig_dcq.trim().equals(""))
					{
						QTY_MMBTU.add(oblig_pndcq);
					}
					else if(oblig_pndcq.equals("0.00") || oblig_pndcq.trim().equals(""))
					{
						QTY_MMBTU.add(oblig_dcq);
					}
					else if(Double.parseDouble(oblig_dcq)<Double.parseDouble(oblig_pndcq))
					{
						QTY_MMBTU.add(oblig_dcq);
					}
					else
					{
						QTY_MMBTU.add(oblig_pndcq);
					}
					
					
					
					//Logic for fetching data from FMS7_LIABILITY_DTL
					queryString = "SELECT PM_QTY,LD_QTY,TOP_QTY,BUYER_DEF_QTY,BUY_SUSPEN_QTY," +
					  "FAIL_DELV_QTY,REMARK_BUYER,REMARK_SELLER FROM FMS7_LIABILITY_DTL " +
					  "WHERE SN_NO='"+fgsa_sn_arr[1]+"' " +
					  "AND FGSA_NO='"+fgsa_sn_arr[0]+"' AND CUSTOMER_CD='"+customer_cd+"' " +
					  "AND GAS_DT=TO_DATE('"+MONTH_DATES.elementAt(z)+"','DD/MM/YYYY') " +
				      "AND CONTRACT_TYPE='"+contract_type+"' ";
					//System.out.println("FMS7_LIABILITY_DTL = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						PM_QTY.add(rset.getString(1)==null?"":nf.format(Double.parseDouble(rset.getString(1))));
						SELLER_OR_PAY_QTY.add(rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2))));
						TAKE_OR_PAY_QTY.add(rset.getString(3)==null?"":nf.format(Double.parseDouble(rset.getString(3))));
						BUYER_DEFAULT_QTY.add(rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4))));
						BUYER_SUSPENSION_QTY.add(rset.getString(5)==null?"":nf.format(Double.parseDouble(rset.getString(5))));
						FAILURE_TO_DELIVER_QTY.add(rset.getString(6)==null?"":nf.format(Double.parseDouble(rset.getString(6))));
						BUYER_DEFAULT_REASON.add(rset.getString(7)==null?"":rset.getString(7));
						SELLER_DEFAULT_REASON.add(rset.getString(8)==null?"":rset.getString(8));
						daily_Buyer_Allocation_Offspec_Flag.add("Y");
					}
					else
					{
						PM_QTY.add("");
						SELLER_OR_PAY_QTY.add("");
						TAKE_OR_PAY_QTY.add("");
						BUYER_DEFAULT_QTY.add("");
						BUYER_SUSPENSION_QTY.add("");
						FAILURE_TO_DELIVER_QTY.add("");
						BUYER_DEFAULT_REASON.add("");
						SELLER_DEFAULT_REASON.add("");
						daily_Buyer_Allocation_Offspec_Flag.add("N");
					}
				}				
			}			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchLiabilityDetails() ...
	
	
	//Following Function Has Been Introduced By Samik Shah On 18th June, 2010 ...
	//Following Fuction Will Fetch Data For Preparing Daily Joint Tickets ...
	public void fetchDailyJointTicketList_Running()
	{
		methodName = "fetchDailyJointTicketList()";
		try 
		{
			//Following Data Are Related To SN ...
			queryString = "SELECT A.customer_cd,A.plant_seq_no " +
						  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
						  "GROUP BY A.customer_cd,A.plant_seq_no ORDER BY A.customer_cd,A.plant_seq_no";
			//System.out.println("Query For Fetching Unique SN & Plant From Daily Allocation Table = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				daily_JT_Customer_Cd.add(rset.getString(1));
				daily_JT_Plant_Seq_No.add(rset.getString(2));
			}
			
			for(int i=0; i<daily_JT_Customer_Cd.size(); i++)
			{
				Vector temp_fgsa_no = new Vector();
				Vector temp_fgsa_rev_no = new Vector();
				Vector temp_sn_no = new Vector();
				Vector temp_sn_ref_no = new Vector();
				Vector temp_sn_rev_no = new Vector();
				Vector temp_contract_type = new Vector();
				Vector temp_sn_qty = new Vector();
				String tmp_fgsa_no = "";
				String tmp_fgsa_rev_no = "";
				String tmp_sn_no = "";
				String tmp_sn_ref_no = "";
				String tmp_sn_rev_no = "";
				String tmp_contract_type = "";
				String tmp_sn_qty = "";
				String tmp_sn_offspec_qty="";//JHP
				String tmp_sn_pffspec_flag="";//JHP
				int sn_count = 0;
								
				Vector temp_transporter_cd = new Vector();
				Vector temp_transporter_nm = new Vector();
				Vector temp_transporter_abbr = new Vector();
				Vector temp_transporter_qty = new Vector();
				Vector temp_gcv = new Vector();
				Vector temp_ncv = new Vector();
				String tmp_transporter_cd = "";
				String tmp_transporter_nm = "";
				String tmp_transporter_abbr = "";
				String tmp_transporter_qty = "";
				String tmp_gcv = "";
				String tmp_ncv = "";
				int trans_count = 0;
				
				queryString = "SELECT NVL(A.customer_name,' '),NVL(A.customer_abbr,' ') " +
	  			  			  "FROM FMS7_CUSTOMER_MST A WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
	  			  			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B WHERE " +
	  			  			  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.customer_cd=B.customer_cd)";
				//System.out.println("Query For Fetching Customer Name & Abbriviation = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Customer_Nm.add(rset.getString(1));
					daily_JT_Customer_Abbr.add(rset.getString(2));
				}
				else
				{
					daily_JT_Customer_Nm.add(" ");
					daily_JT_Customer_Abbr.add(" ");
				}
				
				/*queryString = "SELECT fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" " +
				  			  "GROUP BY fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type";
				//System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_fgsa_no.add(rset.getString(1));
					temp_fgsa_rev_no.add(rset.getString(2));
					temp_sn_no.add(rset.getString(3));
					temp_sn_rev_no.add(rset.getString(4));
					temp_contract_type.add(rset.getString(5));
					tmp_fgsa_no += rset.getString(1)+"~~";
					tmp_fgsa_rev_no += rset.getString(2)+"~~";
					tmp_sn_no += rset.getString(3)+"~~";
					tmp_sn_rev_no += rset.getString(4)+"~~";
					tmp_contract_type += rset.getString(5)+"~~";
					
					++sn_count;
				}
				
				if(sn_count==0)
				{
					temp_fgsa_no.add("0");
					temp_fgsa_rev_no.add("0");
					temp_sn_no.add("0");
					temp_sn_rev_no.add("0");
					temp_contract_type.add(" ");
					tmp_fgsa_no += "0"+"~~";
					tmp_fgsa_rev_no += "0"+"~~";
					tmp_sn_no += "0"+"~~";
					tmp_sn_rev_no += "0"+"~~";
					tmp_contract_type += " "+"~~";
				}*/
	
				queryString = "SELECT DISTINCT fgsa_no,sn_no,contract_type " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" " +
				  			  "ORDER BY fgsa_no,sn_no,contract_type";
				System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_fgsa_no.add(rset.getString(1));
					temp_sn_no.add(rset.getString(2));
					temp_contract_type.add(rset.getString(3));
					tmp_fgsa_no += rset.getString(1)+"~~";
					tmp_sn_no += rset.getString(2)+"~~";
					tmp_contract_type += rset.getString(3)+"~~";
					
					++sn_count;
				}
				
				if(sn_count==0)
				{
					temp_fgsa_no.add("0");
					temp_sn_no.add("0");
					temp_contract_type.add(" ");
					tmp_fgsa_no += "0"+"~~";
					tmp_sn_no += "0"+"~~";
					tmp_contract_type += " "+"~~";
				}
				tmp_fgsa_rev_no += "0"+"~~";
				tmp_sn_rev_no += "0"+"~~";
				temp_fgsa_rev_no.add("0");
				temp_sn_rev_no.add("0");
				
				daily_JT_Fgsa_No.add(tmp_fgsa_no);
				daily_JT_Fgsa_Rev_No.add(tmp_fgsa_rev_no);
				daily_JT_Sn_No.add(tmp_sn_no);
				daily_JT_Sn_Rev_No.add(tmp_sn_rev_no);
				daily_JT_Contract_Type.add(tmp_contract_type);
				
				for(int j=0; j<temp_sn_no.size(); j++)
				{
					queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
					  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
					  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
					  			  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
					  			  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
					  			  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
					  			  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
					  			  "A.contract_type='"+temp_contract_type.elementAt(j)+"'";
					//System.out.println("Query For Fetching SN Wise QTY Value(s) In MMBTU(s) Details = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_sn_qty.add(nf.format(Double.parseDouble(rset.getString(1))));
						tmp_sn_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					}
					else
					{
						temp_sn_qty.add(nf.format(Double.parseDouble("0")));
						tmp_sn_qty += "0"+"~~";
					}
					queryString = "SELECT NVL(sum(A.qty_offspec),'0') " +
		  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
		  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
		  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
		  			  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
		  			  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
		  			  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
		  			  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
		  			  "A.contract_type='"+temp_contract_type.elementAt(j)+"' and A.offspec_flag='Y'";
				//System.out.println("Query For Fetching OFFSpec QTY Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tmp_sn_offspec_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					
				}
				else
				{
					
					tmp_sn_offspec_qty += " "+"~~";
					
				}
				queryString = "SELECT NVL(sum(A.qty_offspec),'0') " +
				  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  "A.fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
				  //"A.fgsa_rev_no="+temp_fgsa_rev_no.elementAt(j)+" AND " +
				  "A.sn_no="+temp_sn_no.elementAt(j)+" AND " +
				  //"A.sn_rev_no="+temp_sn_rev_no.elementAt(j)+" AND " +
				  "A.contract_type='"+temp_contract_type.elementAt(j)+"' and A.offspec_flag='N'";
				//System.out.println("Query For Fetching OFFSpec QTY Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				tmp_sn_pffspec_flag += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
				
				}
				else
				{
				
				tmp_sn_pffspec_flag += " "+"~~";
				
				}
					if((""+temp_contract_type.elementAt(j)).trim().equalsIgnoreCase("S"))
					{
						queryString = "SELECT sn_ref_no " +
									  "FROM DLNG_SN_MST WHERE " +
						  			  "fgsa_no="+temp_fgsa_no.elementAt(j)+" AND " +
						  			  "sn_no="+temp_sn_no.elementAt(j)+" AND " +
						  			  "sn_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" " +
						  			  "ORDER BY sn_rev_no DESC";
						//System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							tmp_sn_ref_no += (rset.getString(1)==null?" ":rset.getString(1))+"~~";
						}
						else
						{
							tmp_sn_ref_no += " "+"~~";
						}
					}
					else if((""+temp_contract_type.elementAt(j)).trim().equalsIgnoreCase("L"))
					{
						queryString = "SELECT loa_ref_no " +
									  "FROM FMS7_LOA_MST WHERE " +
						  			  "tender_no="+temp_fgsa_no.elementAt(j)+" AND " +
						  			  "loa_no="+temp_sn_no.elementAt(j)+" AND " +
						  			  "loa_ref_no IS NOT NULL AND " +
						  			  "customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" " +
						  			  "ORDER BY loa_rev_no DESC";
						//System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							tmp_sn_ref_no += (rset.getString(1)==null?" ":rset.getString(1))+"~~";
						}
						else
						{
							tmp_sn_ref_no += " "+"~~";
						}
					}
					else
					{
						tmp_sn_ref_no += " "+"~~";
					}
				}
				daily_JT_offspec_qty.add(tmp_sn_offspec_qty); //JHP
				daily_JT_offspec_flag.add(tmp_sn_pffspec_flag);//JHP
				daily_JT_SN_Qty_Mmbtu.add(tmp_sn_qty);
				daily_JT_SN_Ref_No.add(tmp_sn_ref_no);
				
				queryString = "SELECT DISTINCT(A.transporter_cd),NVL(A.gcv,'0'),NVL(A.ncv,'0') " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" ORDER BY A.transporter_cd";
				//System.out.println("Query For Fetching Transporter Code, Gcv & Ncv Value(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_transporter_cd.add(rset.getString(1));
					temp_gcv.add(nf.format(Double.parseDouble(rset.getString(2))));
					temp_ncv.add(nf.format(Double.parseDouble(rset.getString(3))));
					tmp_transporter_cd += rset.getString(1)+"~~";
					tmp_gcv += nf.format(Double.parseDouble(rset.getString(2)))+"~~";
					tmp_ncv += nf.format(Double.parseDouble(rset.getString(3)))+"~~";
					++trans_count;
				}
				
				if(trans_count==0)
				{
					temp_transporter_cd.add("0");
					temp_gcv.add(nf.format(Double.parseDouble("0")));
					temp_ncv.add(nf.format(Double.parseDouble("0")));
					tmp_transporter_cd += "0"+"~~";
					tmp_gcv += nf.format(Double.parseDouble("0"))+"~~";
					tmp_ncv += nf.format(Double.parseDouble("0"))+"~~";
				}
				
				for(int j=0; j<temp_transporter_cd.size(); j++)
				{
					queryString = "SELECT NVL(A.transporter_name,' '),NVL(A.transporter_abbr,' ') " +
					  			  "FROM DLNG_TRANS_MST A WHERE A.transporter_cd="+temp_transporter_cd.elementAt(j)+" AND " +
					  			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM DLNG_TRANS_MST B WHERE " +
					  			  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.transporter_cd=B.transporter_cd)";
					//System.out.println("Query For Fetching Transporter Name & Abbriviation = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_transporter_nm.add(rset.getString(1));
						temp_transporter_abbr.add(rset.getString(2));
						tmp_transporter_nm += rset.getString(1)+"~~";
						tmp_transporter_abbr += rset.getString(2)+"~~";
					}
					else
					{
						temp_transporter_nm.add(" ");
						temp_transporter_abbr.add(" ");
						tmp_transporter_nm += " "+"~~";
						tmp_transporter_abbr += " "+"~~";
					}
				}
				
				daily_JT_Transporter_Cd.add(tmp_transporter_cd);
				daily_JT_Gcv.add(tmp_gcv);
				daily_JT_Ncv.add(tmp_ncv);
				daily_JT_Transporter_Nm.add(tmp_transporter_nm);
				daily_JT_Transporter_Abbr.add(tmp_transporter_abbr);
				
				for(int j=0; j<temp_transporter_cd.size(); j++)
				{
					queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
					  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
					  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
					  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
					  			  "A.transporter_cd="+temp_transporter_cd.elementAt(j)+"";
					//System.out.println("Query For Fetching Transporter QTY Value(s) In MMBTU(s) Details = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						temp_transporter_qty.add(nf.format(Double.parseDouble(rset.getString(1))));
						tmp_transporter_qty += nf.format(Double.parseDouble(rset.getString(1)))+"~~";
					}
					else
					{
						temp_transporter_qty.add(nf.format(Double.parseDouble("0")));
						tmp_transporter_qty += nf.format(Double.parseDouble("0"))+"~~";
					}
				}
				
				daily_JT_Transporter_Qty_Mmbtu.add(tmp_transporter_qty);
				
				double seller_qty_mmbtu = 0;
				
				queryString = "SELECT NVL(A.qty_mmbtu,'0') " +
				  			  "FROM FMS7_DAILY_SELLER_NOM_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  			  "A.nom_rev_no=(SELECT MAX(B.nom_rev_no) FROM FMS7_DAILY_SELLER_NOM_DTL B WHERE " +
				  			  "B.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "B.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "B.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND " +
				  			  "A.fgsa_no=B.fgsa_no AND A.sn_no=B.sn_no AND " +
				  			  "A.contract_type=B.contract_type AND A.transporter_cd=B.transporter_cd)";
				//System.out.println("Query For Fetching QTY Value(s) In MMBTU(s) From Seller Nomination Details = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					seller_qty_mmbtu += Double.parseDouble(rset.getString(1));
				}
				
				daily_JT_Seller_Nom_Qty_Mmbtu.add(nf.format(seller_qty_mmbtu));
				
				
				queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0'),NVL(SUM(A.qty_scm),'0') " +
				  			  "FROM DLNG_DAILY_ALLOCATION_DTL A WHERE A.gas_dt=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.plant_seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+"";
				//System.out.println("Query For Fetching QTY Value(s) In MMBTU(s) & SCM(s) = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble(rset.getString(1))));
					daily_JT_Qty_Scm.add(nf.format(Double.parseDouble(rset.getString(2))));
				}
				else
				{
					daily_JT_Qty_Mmbtu.add(nf.format(Double.parseDouble("0")));
					daily_JT_Qty_Scm.add(nf.format(Double.parseDouble("0")));
				}
				
				queryString = "SELECT NVL(A.plant_name,' ') " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND " +
				  			  "A.seq_no="+daily_JT_Plant_Seq_No.elementAt(i)+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND " +
				  			  "A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no)";
				//System.out.println("Query For Fetching Plant Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					daily_JT_Plant_Nm.add(rset.getString(1));
				}
				else
				{
					daily_JT_Plant_Nm.add(" ");
				}
				
				Vector cont_cd = new Vector();
				Vector cont_nm = new Vector();
				Vector cont_desg = new Vector();
				int cont_count = 0;
				
				queryString2 = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' ') " +
							  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
							  "WHERE A.customer_cd="+daily_JT_Customer_Cd.elementAt(i)+" AND A.def_jt_flag='Y' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
							  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				//System.out.println("Customer Contact Person Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				while(rset2.next())
				{
					cont_cd.add(rset2.getString(1));
					cont_nm.add(rset2.getString(2).trim());
					cont_desg.add(rset2.getString(3).trim());
					++cont_count;
				}
				
				if(cont_count==0)
				{
					cont_cd.add("0");
					cont_nm.add("");
					cont_desg.add("");
				}
				
				Customer_Contact_Cd.add(cont_cd);
				Customer_Contact_Nm.add(cont_nm);
				Customer_Contact_Desg.add(cont_desg);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchDailyJointTicketList() ...
		

	//Following Function Has Been Introduced By Samik Shah On 21st June, 2010 ...
	//Following Fuction Will Fetch Data For Customer & Supplier For Preparing Joint Ticket's Format ...
	public void fetchJointTicketReport()
	{
		methodName = "fetchJointTicketReport()";
		try 
		{
			String contact_addr_flag = "";
			
			queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' ')," +
						  "addr_flag,phone,fax_1  " +
						  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
						  "WHERE A.customer_cd="+customer_cd+" AND A.def_jt_flag='Y' AND " +
						  "A.seq_no="+customer_contact_cd+" AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
						  "B.seq_no="+customer_contact_cd+" AND " +
						  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
			System.out.println("Customer Contact Person Fetch Query = "+queryString);
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
							  "WHERE A.customer_cd="+customer_cd+" AND A.address_type='"+contact_addr_flag+"' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"' AND " +
							  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
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
							  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+plant_no+"' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
							  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
							  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
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
			
			/*queryString = "SELECT plant_addr,plant_city,plant_pin,plant_name,plant_state " +
						  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
						  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+plant_seq_no+"' AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
						  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
						  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
			System.out.println("Customer Address Fetch Query = "+queryString);
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
			}*/

			queryString = "SELECT supplier_name " +
						  "FROM FMS7_SUPPLIER_MST A " +
						  "WHERE A.supplier_cd=1 AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND " +
						  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
			//System.out.println("Supplier Details Fetch Query = "+queryString);
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
						  "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
			//System.out.println("Supplier Address Fetch Query = "+queryString);
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

	
	//Following Function Has Been Introduced By Samik Shah On 30th July, 2010 ...
	public void generateGasDateForNomination()
	{
		methodName = "generateGasDateForNomination()";
		try 
		{
			queryString = "SELECT TO_CHAR(TO_DATE('"+gen_date+"','DD/MM/YYYY')+1,'DD/MM/YYYY') FROM DUAL";
			//System.out.println("Query For Finding Out Gas Date From Generation Date = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				gas_date = rset.getString(1)==null?"":rset.getString(1);
			}
		}
		catch(Exception e)
		{
			//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method generateGasDateForNomination() ...
	
	
	//Following Function Has Been Introduced By Samik Shah On 30th July, 2010 ...
	public void generateGenDateForNomination()
	{
		methodName = "generateGenDateForNomination()";
		try 
		{
			queryString = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
			//System.out.println("Query For Finding Out Generation Date From Gas Date = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				gen_date = rset.getString(1)==null?"":rset.getString(1);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method generateGenDateForNomination() ...
	
	
	//Following Function Has Been Introduced By Samik Shah On 25th May, 2011 ...
	public void generateGenDateForAllocation()
	{
		methodName = "generateGenDateForAllocation()";
		try 
		{
			queryString = "SELECT TO_CHAR(TO_DATE('"+gas_date+"','DD/MM/YYYY')+1,'DD/MM/YYYY') FROM DUAL";
			//System.out.println("Query For Finding Out Generation Date From Gas Date = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				gen_date = rset.getString(1)==null?"":rset.getString(1);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method generateGenDateForAllocation() ...
	
		
	//Following Function Has Been Introduced By Samik Shah On 31st July, 2010 ...
	public void generateGasDateForAllocation()
	{
		methodName = "generateGasDateForAllocation()";
		try 
		{
			queryString = "SELECT TO_CHAR(TO_DATE('"+gen_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
			//System.out.println("Query For Finding Out Gas Date From Generation Date = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				gas_date = rset.getString(1)==null?"":rset.getString(1);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method generateGasDateForAllocation() ...
	
	
	//Following Function Is Introduced By Achal On 27th December, 2010 ...
	public void fetchMonthlyBuyerNomDetails() 
	{
		methodName = "fetchMonthlyBuyerNomDetails()";
		try 
		{
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			int days=1;
			int temp_days=1;
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			////System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd.add(rset.getString(1));
				master_Transporter_Abbr.add(rset.getString(2));
			}
						
			from_dt = "01/"+month+"/"+year;			
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+from_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				to_dt = rset.getString(1);
				temp_days=Integer.parseInt(to_dt.substring(0,2));
			}			
			
			for(int i=0;i<master_Transporter_Cd.size();i++)
			{
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
				String query_part = "";
				for(int z=0; z<Integer.parseInt(to_dt.substring(0,2)); z++)
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(z<(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.START_DT AND A.END_DT) OR ";
						}
						else if(z==(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.START_DT AND A.END_DT)";
						}
					}
				}
				
				//Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.sn_ref_no,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY') " +
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE " +
							   " ("+query_part+") " +
							   "AND A.status='Y' AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   "AND A.fgsa_rev_no=B.fgsa_rev_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND A.start_dt=B.start_dt AND A.end_dt=B.end_dt AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
									   "AND A.sn_rev_no="+rset1.getString(4)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("S");
							daily_Buyer_Nom_Contract_Type.add("SN");
							START_DT.add(rset1.getString(11));
							END_DT.add(rset1.getString(12));
							
							//////////////////////////////
							daily_Buyer_regas_cargo_boe_dt.add("");
							daily_Buyer_regas_cargo_boe_no.add("");
							//////////////////////////////
							
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND " +
										  "FGSA_NO="+rset1.getString(1)+" AND CONTRACT_TYPE='S'";										
							//System.out.println("SN Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);			
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}							
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";							
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
							   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
							   			   "AND ( (A.from_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							   			   "OR (A.to_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								if(Double.parseDouble(rset4.getString(1))>0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								days = temp_days;
							}
							else
							{
								queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									days = Integer.parseInt(rset1.getString(12).substring(0,2)) - Integer.parseInt(rset1.getString(11).substring(0,2)) + 1;
								}
								else
								{
									queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
									rset4 = stmt4.executeQuery(queryString4);
									if(rset4.next())
									{
										days = Integer.parseInt(rset1.getString(12).substring(0,2)) - Integer.parseInt(from_dt.substring(0,2)) + 1;
									}
									else
									{
										queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
										rset4 = stmt4.executeQuery(queryString4);
										if(rset4.next())
										{
											days = Integer.parseInt(to_dt.substring(0,2)) - Integer.parseInt(rset1.getString(11).substring(0,2)) + 1;
										}
										else
										{
											days=1;
										}
									}
								}
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq*days));
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_MONTHLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.MONTH='"+month+"' AND A.year='"+year+"' " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_MONTHLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='S' AND B.MONTH='"+month+"' AND B.year='"+year+"'  " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";							
							//System.out.println("Fetch Query FROM FMS7_MONTHLY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							++trans_count;
						}						
					}
				}//End Of Code For SN Based Buyers ...
				
				//Following Code Is For LOA Based Buyers ...
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.loa_ref_no,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY') " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE " +
							   " ("+query_part+") AND A.status='Y' " +
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND A.start_dt=B.start_dt AND A.end_dt=B.end_dt AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				//System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
									   "ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add("0");
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("L");
							daily_Buyer_Nom_Contract_Type.add("LOA");
							START_DT.add(rset1.getString(11));
							END_DT.add(rset1.getString(12));
							
								//////////////////////////////
							daily_Buyer_regas_cargo_boe_dt.add("");
							daily_Buyer_regas_cargo_boe_no.add("");
							//////////////////////////////
							
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND " +
										  "FGSA_NO="+rset1.getString(1)+" AND CONTRACT_TYPE='L'";
							//System.out.println("LOA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
				   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
				   			   			   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
				   			   			   "AND ( (A.from_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							   			   "OR (A.to_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								if(Double.parseDouble(rset4.getString(1))>0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								days = temp_days;
							}
							else
							{
								queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									days = Integer.parseInt(rset1.getString(12).substring(0,2)) - Integer.parseInt(rset1.getString(11).substring(0,2)) + 1;
								}
								else
								{
									queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
									rset4 = stmt4.executeQuery(queryString4);
									if(rset4.next())
									{
										days = Integer.parseInt(rset1.getString(12).substring(0,2)) - Integer.parseInt(from_dt.substring(0,2)) + 1;
									}
									else
									{
										queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
										rset4 = stmt4.executeQuery(queryString4);
										if(rset4.next())
										{
											days = Integer.parseInt(to_dt.substring(0,2)) - Integer.parseInt(rset1.getString(11).substring(0,2)) + 1;
										}
										else
										{
											days=1;
										}
									}
								}
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq * days));
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_MONTHLY_BUYER_NOM_DTL A WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" AND A.customer_cd="+rset1.getString(5)+" " +
							   			   "AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.MONTH='"+month+"' AND A.year='"+year+"' " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" " +
							   			   "AND A.nom_rev_no=(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_MONTHLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='L' AND B.MONTH='"+month+"' AND B.year='"+year+"' " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							//System.out.println("Fetch Query FROM FMS7_MONTHLY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							++trans_count;
						}
					}
				}//End Of Code For LOA Based Buyers ...
				
				String query_part1="";
				//Following Code Is For RE-GAS Based Buyers ...
				for(int z=0; z<Integer.parseInt(to_dt.substring(0,2)); z++)
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(z<(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part1 += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt) OR ";
						}
						else if(z==(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part1 += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt)";
						}
					}
				}
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100')," +
							   "NVL(A.cargo_ref_no,'0'),TO_CHAR(A.contract_start_dt,'DD/MM/YYYY'),TO_CHAR(A.contract_end_dt,'DD/MM/YYYY') " +
							   ",NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
							   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE " +
							   "("+query_part1+") " +
							   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
							   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
							   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
							   "AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
							   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							sn_ref_no.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("R");
							daily_Buyer_Nom_Contract_Type.add("RE-GAS");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							
							//////////////////////////////
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							//////////////////////////////
							
							
							queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(10)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								days = temp_days;
							}
							else
							{
								queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(10)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									days = Integer.parseInt(rset1.getString(11).substring(0,2)) - Integer.parseInt(rset1.getString(10).substring(0,2)) + 1;
								}
								else
								{
									queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(10)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
									rset4 = stmt4.executeQuery(queryString4);
									if(rset4.next())
									{
										days = Integer.parseInt(rset1.getString(11).substring(0,2)) - Integer.parseInt(from_dt.substring(0,2)) + 1;
									}
									else
									{
										queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(10)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
										rset4 = stmt4.executeQuery(queryString4);
										if(rset4.next())
										{
											days = Integer.parseInt(to_dt.substring(0,2)) - Integer.parseInt(rset1.getString(10).substring(0,2)) + 1;
										}
										else
										{
											days=1;
										}
									}
								}
							}
							//System.out.println("days = "+days);
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100) * days ));
							total_dcq += Double.parseDouble(rset1.getString(6));
							START_DT.add(rset1.getString(10));
							END_DT.add(rset1.getString(11));
							
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = rset1.getString(7);
							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND " +
										  "FGSA_NO="+rset1.getString(1)+" AND CONTRACT_TYPE='R'";											
							//System.out.println("RE-GAS Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}							
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_MONTHLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='R'  AND A.MONTH='"+month+"' AND A.year='"+year+"' " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_MONTHLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='R' AND B.MONTH='"+month+"' AND B.year='"+year+"' " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							//System.out.println("Fetch Query FROM FMS7_MONTHLY_BUYER_NOM_DTL Table (RE-GAS) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							++trans_count;
						}
					}
				}//End Of Code For RE-GAS Based Buyers ...
				
				
				////////////////**********ltcora*************.////////
				
				
				///////////////////////end //////////////////////////////
				

				
				
				
				
				
				
				
				
				
				
				
				
				
				//System.out.println("daily_Buyer_Nom_Mdcq_Qty = "+daily_Buyer_Nom_Mdcq_Qty);
				master_Transporter_Count.add(""+trans_count);
				master_Transporter_Dcq.add(nf.format(total_dcq));
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm.add("");
				}
				
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
			}
			
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
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchMonthlyBuyerNomDetails () ...


	public void fetchMonthlyBuyerNomDetails_OLD() 
	{
		methodName = "fetchMonthlyBuyerNomDetails()";
		try 
		{
			double final_daily_dcq = 0;
			double final_daily_mmbtu = 0;
			double final_daily_scm = 0;
			int days=1;
			int temp_days=1;
			queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' ') FROM DLNG_TRANS_MST ORDER BY transporter_abbr";
			//System.out.println("Master Transporter Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				master_Transporter_Cd.add(rset.getString(1));
				master_Transporter_Abbr.add(rset.getString(2));
			}
						
			from_dt = "01/"+month+"/"+year;			
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+from_dt+"','DD/MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				to_dt = rset.getString(1);
				temp_days=Integer.parseInt(to_dt.substring(0,2));
			}			
			
			for(int i=0;i<master_Transporter_Cd.size();i++)
			{
				int trans_count = 0;
				double total_dcq = 0;
				double total_mmbtu = 0;
				double total_scm = 0;
				String query_part = "";
				for(int z=0; z<Integer.parseInt(to_dt.substring(0,2)); z++)
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(z<(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.START_DT AND A.END_DT) OR ";
						}
						else if(z==(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.START_DT AND A.END_DT)";
						}
					}
				}
				
				//Following Code Is For SN Based Buyers ...
				queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.sn_ref_no,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY') " +
							   "FROM DLNG_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE " +
							   " ("+query_part+") " +
							   "AND A.status='Y' AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
							   "AND A.fgsa_rev_no=B.fgsa_rev_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd " +
							   "AND A.start_dt=B.start_dt AND A.end_dt=B.end_dt AND B.status='Y') " +
							   "AND A.fgsa_no=C.fgsa_no AND A.fgsa_rev_no=C.fgsa_rev_no AND A.sn_no=C.sn_no AND A.sn_rev_no=C.sn_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				//System.out.println("Transporter, Customer, FGSA, & SN Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_SN_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
									   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
									   "AND A.sn_rev_no="+rset1.getString(4)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("S");
							daily_Buyer_Nom_Contract_Type.add("SN");
							START_DT.add(rset1.getString(11));
							END_DT.add(rset1.getString(12));
							
							//////////////////////////////
							daily_Buyer_regas_cargo_boe_dt.add("");
							daily_Buyer_regas_cargo_boe_no.add("");
							//////////////////////////////
							
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND " +
										  "FGSA_NO="+rset1.getString(1)+" AND CONTRACT_TYPE='S'";										
							//System.out.println("SN Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);			
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}							
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";							
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_SN_DCQ_DTL A " +
							   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.fgsa_rev_no="+rset1.getString(2)+" AND A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.sn_rev_no="+rset1.getString(4)+" " +
							   			   "AND ( (A.from_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							   			   "OR (A.to_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								if(Double.parseDouble(rset4.getString(1))>0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								days = temp_days;
							}
							else
							{
								queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									days = Integer.parseInt(rset1.getString(12).substring(0,2)) - Integer.parseInt(rset1.getString(11).substring(0,2)) + 1;
								}
								else
								{
									queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
									rset4 = stmt4.executeQuery(queryString4);
									if(rset4.next())
									{
										days = Integer.parseInt(rset1.getString(12).substring(0,2)) - Integer.parseInt(from_dt.substring(0,2)) + 1;
									}
									else
									{
										queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
										rset4 = stmt4.executeQuery(queryString4);
										if(rset4.next())
										{
											days = Integer.parseInt(to_dt.substring(0,2)) - Integer.parseInt(rset1.getString(11).substring(0,2)) + 1;
										}
										else
										{
											days=1;
										}
									}
								}
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq*days));
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));							
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_MONTHLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='S' AND A.MONTH='"+month+"' AND A.year='"+year+"' " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_MONTHLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='S' AND B.MONTH='"+month+"' AND B.year='"+year+"'  " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";							
							//System.out.println("Fetch Query FROM FMS7_MONTHLY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							++trans_count;
						}						
					}
				}//End Of Code For SN Based Buyers ...
				
				//Following Code Is For LOA Based Buyers ...
				queryString1 = "SELECT NVL(A.tender_no,'0'),NVL(A.tender_no,'0'),NVL(A.loa_no,'0'),NVL(A.loa_rev_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
							   "NVL(A.mdcq_percentage,'100'),A.loa_ref_no,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY') " +
							   "FROM FMS7_LOA_MST A, FMS7_LOA_TRANSPORTER_MST C WHERE " +
							   " ("+query_part+") AND A.status='Y' " +
							   "AND A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM FMS7_LOA_MST B WHERE A.tender_no=B.tender_no " +
							   "AND A.loa_no=B.loa_no AND A.customer_cd=B.customer_cd " +
							   "AND A.start_dt=B.start_dt AND A.end_dt=B.end_dt AND B.status='Y') " +
							   "AND A.tender_no=C.tender_no AND A.loa_no=C.loa_no AND A.loa_rev_no=C.loa_rev_no " +
							   "AND A.customer_cd=C.customer_cd AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' AND C.flag='T' " +
							   "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				//System.out.println("Transporter, Customer, Tender, & LOA Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_LOA_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
									   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
									   "ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							String sn_ref_number = rset1.getString(10)==null?"":rset1.getString(10);
							if(!sn_ref_number.trim().equals(""))
							{
								sn_ref_no.add(sn_ref_number);
							}
							else
							{
								sn_ref_no.add("");
							}
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add("0");
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add(rset1.getString(4));
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("L");
							daily_Buyer_Nom_Contract_Type.add("LOA");
							START_DT.add(rset1.getString(11));
							END_DT.add(rset1.getString(12));
							
								//////////////////////////////
							daily_Buyer_regas_cargo_boe_dt.add("");
							daily_Buyer_regas_cargo_boe_no.add("");
							//////////////////////////////
							
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = ""+(Double.parseDouble(rset1.getString(7))+Double.parseDouble(rset1.getString(8)));							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND " +
										  "FGSA_NO="+rset1.getString(1)+" AND CONTRACT_TYPE='L'";
							//System.out.println("LOA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							
							double temp_mdcq = 0;
							String temp_mdcq_percentage = "100";
							queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
				   			   			   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.tender_no="+rset1.getString(1)+" " +
				   			   			   "AND A.loa_no="+rset1.getString(3)+" AND A.loa_rev_no="+rset1.getString(4)+" " +
				   			   			   "AND ( (A.from_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) " +
							   			   "OR (A.to_dt Between TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY')) )";
							//System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								if(Double.parseDouble(rset4.getString(1))>0)
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset4.getString(1))));
									total_dcq += Double.parseDouble(rset4.getString(1));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset4.getString(1))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
								else
								{
									daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
									total_dcq += Double.parseDouble(rset1.getString(6));
									temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
									temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
								}
							}
							else
							{
								daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
								total_dcq += Double.parseDouble(rset1.getString(6));
								temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(9))<=100?100:Double.parseDouble(rset1.getString(9)));
								temp_mdcq = (Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100;
							}
							
							queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								days = temp_days;
							}
							else
							{
								queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									days = Integer.parseInt(rset1.getString(12).substring(0,2)) - Integer.parseInt(rset1.getString(11).substring(0,2)) + 1;
								}
								else
								{
									queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
									rset4 = stmt4.executeQuery(queryString4);
									if(rset4.next())
									{
										days = Integer.parseInt(rset1.getString(12).substring(0,2)) - Integer.parseInt(from_dt.substring(0,2)) + 1;
									}
									else
									{
										queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(12)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
										rset4 = stmt4.executeQuery(queryString4);
										if(rset4.next())
										{
											days = Integer.parseInt(to_dt.substring(0,2)) - Integer.parseInt(rset1.getString(11).substring(0,2)) + 1;
										}
										else
										{
											days=1;
										}
									}
								}
							}
							
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(temp_mdcq * days));
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_MONTHLY_BUYER_NOM_DTL A WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" AND A.customer_cd="+rset1.getString(5)+" " +
							   			   "AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='L' AND A.MONTH='"+month+"' AND A.year='"+year+"' " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" " +
							   			   "AND A.nom_rev_no=(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_MONTHLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='L' AND B.MONTH='"+month+"' AND B.year='"+year+"' " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							//System.out.println("Fetch Query FROM FMS7_MONTHLY_BUYER_NOM_DTL Table = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							++trans_count;
						}
					}
				}//End Of Code For LOA Based Buyers ...
				
				String query_part1="";
				//Following Code Is For RE-GAS Based Buyers ...
				for(int z=0; z<Integer.parseInt(to_dt.substring(0,2)); z++)
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+z+",'DD/MM/YYYY') FROM DUAL";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(z<(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part1 += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt) OR ";
						}
						else if(z==(Integer.parseInt(to_dt.substring(0,2))-1))
						{
							query_part1 += "(TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') BETWEEN A.contract_start_dt AND A.contract_end_dt)";
						}
					}
				}
				queryString1 = "SELECT NVL(A.re_gas_no,'0'),NVL(A.re_gas_rev_no,'0'),NVL(A.cargo_seq_no,'0'),NVL(A.cargo_seq_no,'0')," +
							   "NVL(A.customer_cd,'0'),NVL(A.dcq_qty,'0'),NVL(A.qty_to_be_supply,'0'),NVL(B.mdcq_percentage,'100')," +
							   "NVL(A.cargo_ref_no,'0'),TO_CHAR(A.contract_start_dt,'DD/MM/YYYY'),TO_CHAR(A.contract_end_dt,'DD/MM/YYYY') " +
							   ",NVL(BOE_NO,''),TO_CHAR(BOE_DT,'DD/MM/YYYY') " +
							   "FROM FMS7_RE_GAS_CARGO_DTL A, FMS7_RE_GAS_MST B, FMS7_RE_GAS_TRANSPORTER_MST C WHERE " +
							   "("+query_part1+") " +
							   "AND A.re_gas_no=B.re_gas_no AND A.re_gas_no=C.re_gas_no AND B.re_gas_no=C.re_gas_no " +
							   "AND A.customer_cd=B.customer_cd AND A.customer_cd=C.customer_cd AND B.customer_cd=C.customer_cd " +
							   "AND A.re_gas_rev_no=C.rev_no AND B.rev_no=C.rev_no AND A.re_gas_rev_no=B.rev_no " +
							   "AND C.transporter_cd='"+master_Transporter_Cd.elementAt(i)+"' " +
							   "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
				//System.out.println("Transporter, Customer, RE-GAS, & Cargo Contract Wise Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					if(!rset1.getString(1).equals("0"))
					{
						queryString2 = "SELECT NVL(A.plant_seq_no,'1') FROM FMS7_RE_GAS_PLANT_MST A " +
									   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.re_gas_no="+rset1.getString(1)+" " +
									   "AND A.rev_no="+rset1.getString(2)+" ORDER BY A.plant_seq_no";
						//System.out.println("Customer Plant Sequence NO Fetch Query (RE-GAS) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							daily_Buyer_Nom_Plant_Cd.add(rset2.getString(1));
							queryString3 = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+" AND A.seq_no="+rset2.getString(1)+" " +
										   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
										   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
										   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))";
							//System.out.println("Customer Plant Name Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Plant_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Plant_Abbr.add("PLANT1");
							}
							
							sn_ref_no.add("");
							daily_Buyer_Nom_Fgsa_No.add(rset1.getString(1));
							daily_Buyer_Nom_Fgsa_Rev_No.add(rset1.getString(2));
							daily_Buyer_Nom_Sn_No.add(rset1.getString(3));
							daily_Buyer_Nom_Sn_Rev_No.add("0");
							daily_Buyer_Nom_Cd.add(rset1.getString(5));
							daily_Buyer_Nom_Type.add("R");
							daily_Buyer_Nom_Contract_Type.add("RE-GAS");
							daily_Buyer_Nom_Dcq.add(nf.format(Double.parseDouble(rset1.getString(6))));
							String temp_mdcq_percentage = ""+(Double.parseDouble(rset1.getString(8))<=100?100:Double.parseDouble(rset1.getString(8)));
							
							//////////////////////////////
							daily_Buyer_regas_cargo_boe_dt.add(rset1.getString(13)==null?"":rset1.getString(13));
							daily_Buyer_regas_cargo_boe_no.add(rset1.getString(12)==null?"":rset1.getString(12));
							//////////////////////////////
							
							
							queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(10)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
							rset4 = stmt4.executeQuery(queryString4);
							if(rset4.next())
							{
								days = temp_days;
							}
							else
							{
								queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(10)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									days = Integer.parseInt(rset1.getString(11).substring(0,2)) - Integer.parseInt(rset1.getString(10).substring(0,2)) + 1;
								}
								else
								{
									queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(10)+"','DD/MM/YYYY') <= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
									rset4 = stmt4.executeQuery(queryString4);
									if(rset4.next())
									{
										days = Integer.parseInt(rset1.getString(11).substring(0,2)) - Integer.parseInt(from_dt.substring(0,2)) + 1;
									}
									else
									{
										queryString4 = "SELECT * FROM DUAL WHERE TO_DATE('"+rset1.getString(10)+"','DD/MM/YYYY') >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+rset1.getString(11)+"','DD/MM/YYYY') >= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
										rset4 = stmt4.executeQuery(queryString4);
										if(rset4.next())
										{
											days = Integer.parseInt(to_dt.substring(0,2)) - Integer.parseInt(rset1.getString(10).substring(0,2)) + 1;
										}
										else
										{
											days=1;
										}
									}
								}
							}
							System.out.println("days = "+days);
							daily_Buyer_Nom_Mdcq_Qty.add(nf.format(((Double.parseDouble(rset1.getString(6))*Double.parseDouble(temp_mdcq_percentage))/100) * days ));
							total_dcq += Double.parseDouble(rset1.getString(6));
							START_DT.add(rset1.getString(10));
							END_DT.add(rset1.getString(11));
							
							String ALLOCATED_QTY = "0";
							String CONTRACTED_QTY = rset1.getString(7);
							
							queryString = "SELECT SUM(QTY_MMBTU) FROM DLNG_DAILY_ALLOCATION_DTL WHERE " +
										  "SN_NO="+rset1.getString(3)+" AND CUSTOMER_CD="+rset1.getString(5)+" AND " +
										  "FGSA_NO="+rset1.getString(1)+" AND CONTRACT_TYPE='R'";											
							//System.out.println("RE-GAS Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										ALLOCATED_QTY = rset.getString(1).trim();
									}
								}
							}							
							daily_Buyer_Nom_Balance_Qty.add(nf.format(Double.parseDouble(CONTRACTED_QTY)-Double.parseDouble(ALLOCATED_QTY)));							
							queryString3 = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A " +
										   "WHERE A.customer_cd="+rset1.getString(5)+"";
							//System.out.println("Buyer Abbreviation Fetch Query (RE-GAS) = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								daily_Buyer_Nom_Abbr.add(rset3.getString(1));
							}
							else
							{
								daily_Buyer_Nom_Abbr.add(" ");
							}
							daily_Transporter_Nom_Cd.add(""+master_Transporter_Cd.elementAt(i));
							daily_Transporter_Nom_Abbr.add(""+master_Transporter_Abbr.elementAt(i));
							queryString5 = "SELECT TO_CHAR(A.gen_dt,'DD/MM/YYYY'),NVL(A.nom_rev_no,'0'),NVL(A.gen_time,'')," +
										   "NVL(A.plant_seq_no,'0'),NVL(A.qty_mmbtu,'0'),NVL(A.qty_scm,'0') " +
										   "FROM FMS7_MONTHLY_BUYER_NOM_DTL A " +
							   			   "WHERE A.sn_no="+rset1.getString(3)+" " +
							   			   "AND A.fgsa_no="+rset1.getString(1)+" " +
							   			   "AND A.customer_cd="+rset1.getString(5)+" AND A.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
							   			   "AND A.contract_type='R'  AND A.MONTH='"+month+"' AND A.year='"+year+"' " +
							   			   "AND A.plant_seq_no="+rset2.getString(1)+" AND A.nom_rev_no=" +
										   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_MONTHLY_BUYER_NOM_DTL B " +
										   "WHERE B.sn_no="+rset1.getString(3)+" " +
										   "AND B.fgsa_no="+rset1.getString(1)+" " +
										   "AND B.customer_cd="+rset1.getString(5)+" AND B.transporter_cd="+master_Transporter_Cd.elementAt(i)+" " +
										   "AND B.contract_type='R' AND B.MONTH='"+month+"' AND B.year='"+year+"' " +
										   "AND B.plant_seq_no="+rset2.getString(1)+")";
							//System.out.println("Fetch Query FROM FMS7_MONTHLY_BUYER_NOM_DTL Table (RE-GAS) = "+queryString5);
							rset5 = stmt5.executeQuery(queryString5);
							if(rset5.next())
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("("+rset5.getString(1)+" - "+rset5.getString(2)+")");
								daily_Buyer_Gen_Day_Time.add(rset5.getString(3)==null?"":rset5.getString(3));
								daily_Buyer_Nom_Plant_Seq_No.add(rset5.getString(4));
								daily_Buyer_Nom_Qty_Mmbtu.add(rset5.getString(5)=="0"?"":rset5.getString(5));
								daily_Buyer_Nom_Qty_Scm.add(rset5.getString(6)=="0"?"":rset5.getString(6));
								total_mmbtu += Double.parseDouble(rset5.getString(5));
								total_scm += Double.parseDouble(rset5.getString(6));
							}
							else
							{
								daily_Buyer_Gen_Day_With_Rev_No.add("");
								daily_Buyer_Gen_Day_Time.add("");
								daily_Buyer_Nom_Plant_Seq_No.add("0");
								daily_Buyer_Nom_Qty_Mmbtu.add("");
								daily_Buyer_Nom_Qty_Scm.add("");
							}
							++trans_count;
						}
					}
				}//End Of Code For RE-GAS Based Buyers ...
				System.out.println("daily_Buyer_Nom_Mdcq_Qty = "+daily_Buyer_Nom_Mdcq_Qty);
				master_Transporter_Count.add(""+trans_count);
				master_Transporter_Dcq.add(nf.format(total_dcq));
				if(total_mmbtu>0)
				{
					master_Transporter_Mmbtu.add(nf.format(total_mmbtu));
				}
				else
				{
					master_Transporter_Mmbtu.add("");
				}
				
				if(total_scm>0)
				{
					master_Transporter_Scm.add(nf.format(total_scm));
				}
				else
				{
					master_Transporter_Scm.add("");
				}
				
				final_daily_dcq += total_dcq;
				final_daily_mmbtu += total_mmbtu;
				final_daily_scm += total_scm;
			}
			
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
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchMonthlyBuyerNomDetails () ...


	
	
	
	//Following Function Is Introduced By Achal Pathak  For Weekly Buyer Nomination Details On 22nd December, 2010 ...
	public void fetchWeeklyBuyerNomDetailsDtls()
	{
		methodName = "fetchWeeklyBuyerNomDetailsDtls()";
		try 
		{
			////System.out.println("In DataBean from_dt ="+from_dt);
			for(int i=0;i<7;i++)
		  	{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+i+",'DAY') , TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+i+",'DD/MM/YYYY') FROM DUAL";
				//System.out.println("Week Days Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					WEEKDAYS.add(rset.getString(1));
					WEEKDATES.add(rset.getString(2));
				}
		  	}
			queryString = "SELECT NVL(MAX(nom_rev_no),'0') FROM FMS7_WEEKLY_BUYER_NOM_DTL " +
			  "WHERE sn_no="+buyer_sn_no+"  AND fgsa_no="+buyer_fgsa_no+" " +
			  "AND customer_cd="+customer_cd+" AND transporter_cd="+transporter_cd+" " +
			  "AND plant_seq_no="+plant_seq_no+" AND contract_type='"+contract_type+"' " +
			  "AND gas_dt=TO_DATE('"+from_dt+"','DD/MM/YYYY')";
			if(contract_type.equalsIgnoreCase("C") || contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
			{
				queryString+=" AND MAPPING_ID='"+nom_mapping_id+"'";
			}
				
			
			
			
			//System.out.println("Query For Fetching MAX Revision NO For WEEKLY Buyer Nomination = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				nom_rev_no = rset.getString(1);
			}
			for(int i=0;i<WEEKDATES.size();i++)
			{
				//System.out.println("nom_rev_no = "+nom_rev_no);
				queryString = "SELECT QTY_MMBTU,QTY_SCM FROM FMS7_WEEKLY_BUYER_NOM_DTL WHERE " +
				"SN_NO='"+buyer_sn_no+"' AND FGSA_NO='"+buyer_fgsa_no+"' AND NOM_REV_NO='"+nom_rev_no+"' AND " +
				"TRANSPORTER_CD='"+transporter_cd+"' AND CUSTOMER_CD='"+customer_cd+"' AND  " +
				"PLANT_SEQ_NO='"+plant_seq_no+"' AND  " +
				"GAS_DT=TO_DATE('"+WEEKDATES.elementAt(i)+"','DD/MM/YYYY') AND CONTRACT_TYPE='"+contract_type+"' ";
				if(contract_type.equalsIgnoreCase("C") || contract_type.equalsIgnoreCase("T"))  //ADDED FOR LTCORA AND CN
				{
					queryString+=" AND MAPPING_ID='"+nom_mapping_id+"'";
				}
				
				//System.out.println("QTY_MMBTU,QTY_SCM from FMS7_WEEKLY_BUYER_NOM_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					QTY_MMBTU.add(rset.getString(1));
					QTY_SCM.add(rset.getString(2));
				}
				else
				{
					QTY_MMBTU.add("");
					QTY_SCM.add("");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End of Method fetchWeeklyBuyerNomDetailsDtls() ...
	
	
	public void fetchWeeklyBuyerNomDetailsDtls_OLD()
	{
		methodName = "fetchWeeklyBuyerNomDetailsDtls()";
		try 
		{
			//System.out.println("In DataBean from_dt ="+from_dt);
			for(int i=0;i<7;i++)
		  	{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+i+",'DAY') , TO_CHAR(TO_DATE('"+from_dt+"','DD/MM/YYYY')+"+i+",'DD/MM/YYYY') FROM DUAL";
				System.out.println("Week Days Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					WEEKDAYS.add(rset.getString(1));
					WEEKDATES.add(rset.getString(2));
				}
		  	}
			queryString = "SELECT NVL(MAX(nom_rev_no),'0') FROM FMS7_WEEKLY_BUYER_NOM_DTL " +
			  "WHERE sn_no="+buyer_sn_no+"  AND fgsa_no="+buyer_fgsa_no+" " +
			  "AND customer_cd="+customer_cd+" AND transporter_cd="+transporter_cd+" " +
			  "AND plant_seq_no="+plant_seq_no+" AND contract_type='"+contract_type+"' " +
			  "AND gas_dt=TO_DATE('"+from_dt+"','DD/MM/YYYY')";
			
			System.out.println("Query For Fetching MAX Revision NO For WEEKLY Buyer Nomination = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				nom_rev_no = rset.getString(1);
			}
			for(int i=0;i<WEEKDATES.size();i++)
			{
				System.out.println("nom_rev_no = "+nom_rev_no);
				queryString = "SELECT QTY_MMBTU,QTY_SCM FROM FMS7_WEEKLY_BUYER_NOM_DTL WHERE " +
				"SN_NO='"+buyer_sn_no+"' AND FGSA_NO='"+buyer_fgsa_no+"' AND NOM_REV_NO='"+nom_rev_no+"' AND " +
				"TRANSPORTER_CD='"+transporter_cd+"' AND CUSTOMER_CD='"+customer_cd+"' AND  " +
				"PLANT_SEQ_NO='"+plant_seq_no+"' AND  " +
				"GAS_DT=TO_DATE('"+WEEKDATES.elementAt(i)+"','DD/MM/YYYY') AND CONTRACT_TYPE='"+contract_type+"' ";
				
				
				System.out.println("QTY_MMBTU,QTY_SCM from FMS7_WEEKLY_BUYER_NOM_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					QTY_MMBTU.add(rset.getString(1));
					QTY_SCM.add(rset.getString(2));
				}
				else
				{
					QTY_MMBTU.add("");
					QTY_SCM.add("");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End of Method fetchWeeklyBuyerNomDetailsDtls() ...
///////////////SB20180926/////////////////////
	public void SupplierDtl()
	{
		try 
		{
			String queryString1 = " SELECT SUPPLIER_ABBR, SUPPLIER_NAME, PLANT_STATE  FROM FMS7_SUPPLIER_MST A, FMS7_SUPPLIER_PLANT_DTL B WHERE A.SUPPLIER_CD=B.SUPPLIER_CD ";
//			System.out.println("Min. Yr "+queryString1);
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
	
	String Supl_abr="";
	public String getSupl_abr() {return Supl_abr;}
	String Supl_nm="";
	public String getSupl_nm() {return Supl_nm;}
	String Supl_plant_state="";
	public String getSupl_plant_state() {return Supl_plant_state;}

	///////////////////////////////////////////////		
	
	//Following String Setter Methods Are Defined By Samik Shah On 29th April, 2010 ...
	public void setCallFlag(String callFlag) {this.callFlag = callFlag;} //Introduced By Samik Shah On 29th April, 2010 ...
	public void setGas_date(String gas_date) {this.gas_date = gas_date;} //Introduced By Samik Shah On 29th April, 2010 ...
	public void setCustomer_cd(String customer_cd) {this.customer_cd = customer_cd;} //Introduced By Samik Shah On 21st June, 2010 ...
	public void setCustomer_contact_cd(String customer_contact_cd) {this.customer_contact_cd = customer_contact_cd;} //Introduced By Samik Shah On 21st June, 2010 ...
	public void setGen_date(String gen_date) {this.gen_date = gen_date;} //Introduced By Samik Shah On 30th July, 2010 ...
	
	//Following (4) String Setter Methods Has Been Declared By Samik Shah On 23rd December, 2010 ...
	public void setYear(String year) {this.year = year;}
	public void setMonth(String month) {this.month = month;}
	public void setFgsa_sn_no(String fgsa_sn_no) {this.fgsa_sn_no = fgsa_sn_no;}
	public void setPlant_seq_no(String plant_seq_no) {this.plant_seq_no = plant_seq_no;}
	
	//Following (3) Setter Methods Has Been Defined By Achal Pathak On 22nd December, 2010 ...
	public void setFrom_dt(String from_dt) {this.from_dt = from_dt;}
	public void setTo_dt(String to_dt) {this.to_dt = to_dt;}
	public void setWeek(String week) {this.week = week;}
	
	//Following (7) Setter Methods Has Been Defined By Achal Pathak On 24th December, 2010 ...
	public void setTransporter_cd(String transporter_cd) {this.transporter_cd = transporter_cd;}
	public void setContract_type(String contract_type) {this.contract_type = contract_type;}
	public void setBuyer_fgsa_no(String buyer_fgsa_no) {this.buyer_fgsa_no = buyer_fgsa_no;}
	public void setBuyer_sn_no(String buyer_sn_no) {this.buyer_sn_no = buyer_sn_no;}
	public void setNom_rev_no(String nom_rev_no) {this.nom_rev_no = nom_rev_no;}
	public void setTotal_mmbtu1(String total_mmbtu1) {this.total_mmbtu1 = total_mmbtu1;}
	public void setTotal_scm1(String total_scm1) {this.total_scm1 = total_scm1;}
	
	
	//Following Vector & String Getter Methods Are Defined By Samik Shah On 29th April, 2010 ...
	public Vector getMaster_Transporter_Cd() {return master_Transporter_Cd;}
	public Vector getMaster_Transporter_Abbr() {return master_Transporter_Abbr;}
	public Vector getMaster_Transporter_Count() {return master_Transporter_Count;}
	public Vector getMaster_Transporter_Dcq() {return master_Transporter_Dcq;}
	public Vector getDaily_Buyer_Nom_Fgsa_No() {return daily_Buyer_Nom_Fgsa_No;}
	public Vector getDaily_Buyer_Nom_Fgsa_Rev_No() {return daily_Buyer_Nom_Fgsa_Rev_No;}
	public Vector getDaily_Buyer_Nom_Sn_No() {return daily_Buyer_Nom_Sn_No;}
	public Vector getDaily_Buyer_Nom_Sn_Rev_No() {return daily_Buyer_Nom_Sn_Rev_No;}
	public Vector getDaily_Buyer_Nom_Cd() {return daily_Buyer_Nom_Cd;}
	public Vector getDaily_Buyer_Nom_Abbr() {return daily_Buyer_Nom_Abbr;}
	public Vector getDaily_Buyer_Nom_Dcq() {return daily_Buyer_Nom_Dcq;}
	public Vector getDaily_Buyer_Nom_Plant_Cd() {return daily_Buyer_Nom_Plant_Cd;}
	public Vector getDaily_Buyer_Nom_Plant_Abbr() {return daily_Buyer_Nom_Plant_Abbr;}
	public Vector getDaily_Transporter_Nom_Cd() {return daily_Transporter_Nom_Cd;}
	public Vector getDaily_Transporter_Nom_Abbr() {return daily_Transporter_Nom_Abbr;}
	public Vector getDaily_Buyer_Nom_Balance_Qty() {return daily_Buyer_Nom_Balance_Qty;} //Introduced By Samik Shah On 23rd August, 2010 ...
	public Vector getBuyer_Allocated_Qty() {return Buyer_Allocated_Qty;} //SB20110924
	public Vector getBuyer_Contracted_Qty() {return Buyer_Contracted_Qty;} //SB20110924
	public Vector getBuyer_Nominated_Qty() {return Buyer_Nominated_Qty;} //SB20110924
	public Vector getDaily_Buyer_Nom_Mdcq_Qty() {return daily_Buyer_Nom_Mdcq_Qty;} //Introduced By Samik Shah On 25th August, 2010 ...
	public String getDaily_Total_Dcq() {return daily_Total_Dcq;}

	//Following Vector & String Getter Methods Are Defined By Samik Shah On 1st May, 2010 ...
	public Vector getDaily_Buyer_Gen_Day_With_Rev_No() {return daily_Buyer_Gen_Day_With_Rev_No;}
	public Vector getDaily_Buyer_Gen_Day_Time() {return daily_Buyer_Gen_Day_Time;}
	public Vector getDaily_Buyer_Nom_Plant_Seq_No() {return daily_Buyer_Nom_Plant_Seq_No;}
	public Vector getDaily_Buyer_Nom_Plant_Seq_Abbr() {return daily_Buyer_Nom_Plant_Seq_Abbr;}
	public Vector getDaily_Buyer_Nom_Qty_Mmbtu() {return daily_Buyer_Nom_Qty_Mmbtu;}
	public Vector getDaily_Buyer_Nom_Qty_Scm() {return daily_Buyer_Nom_Qty_Scm;}
	public Vector getMaster_Transporter_Mmbtu() {return master_Transporter_Mmbtu;}
	public Vector getMaster_Transporter_Scm() {return master_Transporter_Scm;}
	public String getDaily_Total_Mmbtu() {return daily_Total_Mmbtu;}
	public String getDaily_Total_Scm() {return daily_Total_Scm;}

	//Following Vector Getter Method Is Defined By Samik Shah On 3rd May, 2010 ...
	public Vector getDaily_Buyer_Nom_Type() {return daily_Buyer_Nom_Type;}
	
	//Following Vector Getter Method Is Defined By Samik Shah On 25th August, 2010 ...
	public Vector getDaily_Buyer_Nom_Contract_Type() {return daily_Buyer_Nom_Contract_Type;}

	
	//Following Vector & String Getter Methods Are Defined By Samik Shah On 3rd May, 2010 ...
	public Vector getMaster_Transporter_Cd_Seller_Nom() {return master_Transporter_Cd_Seller_Nom;}
	public Vector getMaster_Transporter_Abbr_Seller_Nom() {return master_Transporter_Abbr_Seller_Nom;}
	public Vector getMaster_Transporter_Count_Seller_Nom() {return master_Transporter_Count_Seller_Nom;}
	public Vector getMaster_Transporter_Dcq_Seller_Nom() {return master_Transporter_Dcq_Seller_Nom;}
	public Vector getDaily_Seller_Nom_Fgsa_No() {return daily_Seller_Nom_Fgsa_No;}
	public Vector getDaily_Seller_Nom_Fgsa_Rev_No() {return daily_Seller_Nom_Fgsa_Rev_No;}
	public Vector getDaily_Seller_Nom_Sn_No() {return daily_Seller_Nom_Sn_No;}
	public Vector getDaily_Seller_Nom_Sn_Rev_No() {return daily_Seller_Nom_Sn_Rev_No;}
	public Vector getDaily_Seller_Nom_Cd() {return daily_Seller_Nom_Cd;}
	public Vector getDaily_Seller_Nom_Abbr() {return daily_Seller_Nom_Abbr;}
	public Vector getDaily_Seller_Nom_Dcq() {return daily_Seller_Nom_Dcq;}
	public Vector getDaily_Seller_Nom_Plant_Cd() {return daily_Seller_Nom_Plant_Cd;}
	public Vector getDaily_Seller_Nom_Plant_Abbr() {return daily_Seller_Nom_Plant_Abbr;}
	public Vector getDaily_Transporter_Nom_Cd_Seller_Nom() {return daily_Transporter_Nom_Cd_Seller_Nom;}
	public Vector getDaily_Transporter_Nom_Abbr_Seller_Nom() {return daily_Transporter_Nom_Abbr_Seller_Nom;}
	public String getDaily_Total_Dcq_Seller_Nom() {return daily_Total_Dcq_Seller_Nom;}

	//Following Vector & String Getter Methods Are Defined By Samik Shah On 3rd May, 2010 ...
	public Vector getDaily_Seller_Gen_Day_With_Rev_No() {return daily_Seller_Gen_Day_With_Rev_No;}
	public Vector getDaily_Seller_Gen_Day_Time() {return daily_Seller_Gen_Day_Time;}
	public Vector getDaily_Seller_Nom_Plant_Seq_No() {return daily_Seller_Nom_Plant_Seq_No;}
	public Vector getDaily_Seller_Nom_Plant_Seq_Abbr() {return daily_Seller_Nom_Plant_Seq_Abbr;}
	public Vector getDaily_Seller_Nom_Qty_Mmbtu() {return daily_Seller_Nom_Qty_Mmbtu;}
	public Vector getDaily_Seller_Nom_Qty_Scm() {return daily_Seller_Nom_Qty_Scm;}
	public Vector getMaster_Transporter_Mmbtu_Seller_Nom() {return master_Transporter_Mmbtu_Seller_Nom;}
	public Vector getMaster_Transporter_Scm_Seller_Nom() {return master_Transporter_Scm_Seller_Nom;}
	public String getDaily_Total_Mmbtu_Seller_Nom() {return daily_Total_Mmbtu_Seller_Nom;}
	public String getDaily_Total_Scm_Seller_Nom() {return daily_Total_Scm_Seller_Nom;}

	//Following Vector Getter Method Is Defined By Samik Shah On 3rd May, 2010 ...
	public Vector getDaily_Seller_Nom_Type() {return daily_Seller_Nom_Type;}
	
	//Following Vector Getter Method Is Defined By Samik Shah On 25th August, 2010 ...
	public Vector getDaily_Seller_Nom_Contract_Type() {return daily_Seller_Nom_Contract_Type;}

	//Following Vector & String Getter Methods Are Defined By Samik Shah On 4th May, 2010 ...
	public Vector getDaily_Seller_Obligation_Qty() {return daily_Seller_Obligation_Qty;}
	public Vector getDaily_Seller_Asking_Rate_Qty() {return daily_Seller_Asking_Rate_Qty;}
	public Vector getDaily_Transporter_Obligation_Qty() {return daily_Transporter_Obligation_Qty;}
	public Vector getDaily_Transporter_Asking_Rate_Qty() {return daily_Transporter_Asking_Rate_Qty;}
	public String getDaily_Total_Obligation_Qty() {return daily_Total_Obligation_Qty;}
	public String getDaily_Total_Asking_Rate_Qty() {return daily_Total_Asking_Rate_Qty;}

	
	//Following Vector & String Getter Methods Are Defined By Samik Shah On 4th May, 2010 ...
	//For Transporter Meters ...
	public Vector getDaily_Meter_Reading_Transporter_Cd() {return daily_Meter_Reading_Transporter_Cd;}
	public Vector getDaily_Meter_Reading_Transporter_Abbr() {return daily_Meter_Reading_Transporter_Abbr;}
	public Vector getDaily_Meter_Reading_Transporter_Seq_Cd() {return daily_Meter_Reading_Transporter_Seq_Cd;}

	//Following Vector & String Getter Methods Are Defined By Samik Shah On 4th May, 2010 ...
	//For Customer Meters ...
	public Vector getDaily_Meter_Reading_Customer_Cd() {return daily_Meter_Reading_Customer_Cd;}
	public Vector getDaily_Meter_Reading_Customer_Abbr() {return daily_Meter_Reading_Customer_Abbr;}
	public Vector getDaily_Meter_Reading_Customer_Seq_Cd() {return daily_Meter_Reading_Customer_Seq_Cd;}
	public Vector getDaily_Meter_Reading_Customer_Transporter_Cd() {return daily_Meter_Reading_Customer_Transporter_Cd;}
	public Vector getDaily_Meter_Reading_Customer_Transporter_Abbr() {return daily_Meter_Reading_Customer_Transporter_Abbr;}

	
	//Following Vector & String Getter Methods Are Defined By Samik Shah On 5th May, 2010 ...
	//For Transporter Daily Meter Reading ...
	public Vector getDaily_Meter_Reading_Transporter_Mmbtu() {return daily_Meter_Reading_Transporter_Mmbtu;}
	public Vector getDaily_Meter_Reading_Transporter_Scm() {return daily_Meter_Reading_Transporter_Scm;}
	public Vector getDaily_Meter_Reading_Transporter_Reconcil_Mmbtu() {return daily_Meter_Reading_Transporter_Reconcil_Mmbtu;}
	public Vector getDaily_Meter_Reading_Transporter_Reconcil_Scm() {return daily_Meter_Reading_Transporter_Reconcil_Scm;}
	public Vector getDaily_Meter_Reading_Individual_Transporter_Mmbtu() {return daily_Meter_Reading_Individual_Transporter_Mmbtu;} //Defined By Samik Shah On 15th October, 2010 ...
	public Vector getDaily_Meter_Reading_Transporter_Calculated_Gcv() {return daily_Meter_Reading_Transporter_Calculated_Gcv;}
	public Vector getDaily_Meter_Reading_Transporter_Calculated_Ncv() {return daily_Meter_Reading_Transporter_Calculated_Ncv;}
	public Vector getDaily_Meter_Reading_Transporter_Define_Gcv() {return daily_Meter_Reading_Transporter_Define_Gcv;}
	public Vector getDaily_Meter_Reading_Transporter_Define_Ncv() {return daily_Meter_Reading_Transporter_Define_Ncv;}
	public String getDaily_Meter_Reading_Transporter_Mmbtu_Total() {return daily_Meter_Reading_Transporter_Mmbtu_Total;}
	public String getDaily_Meter_Reading_Transporter_Scm_Total() {return daily_Meter_Reading_Transporter_Scm_Total;}
	public String getDaily_Meter_Reading_Transporter_Reconcil_Mmbtu_Total() {return daily_Meter_Reading_Transporter_Reconcil_Mmbtu_Total;}
	public String getDaily_Meter_Reading_Transporter_Reconcil_Scm_Total() {return daily_Meter_Reading_Transporter_Reconcil_Scm_Total;}
	public String getDaily_Meter_Reading_Individual_Transporter_Mmbtu_Total() {return daily_Meter_Reading_Individual_Transporter_Mmbtu_Total;} //Defined By Samik Shah On 15th October, 2010 ...
	
	//Following Vector & String Getter Methods Are Defined By Samik Shah On 5th May, 2010 ...
	//For Customer Daily Meter Reading ...
	public Vector getDaily_Meter_Reading_Customer_Mmbtu() {return daily_Meter_Reading_Customer_Mmbtu;}
	public Vector getDaily_Meter_Reading_Customer_Scm() {return daily_Meter_Reading_Customer_Scm;}
	public Vector getDaily_Meter_Reading_Customer_Reconcil_Mmbtu() {return daily_Meter_Reading_Customer_Reconcil_Mmbtu;}
	public Vector getDaily_Meter_Reading_Customer_Reconcil_Scm() {return daily_Meter_Reading_Customer_Reconcil_Scm;}
	public Vector getDaily_Meter_Reading_Individual_Customer_Mmbtu() {return daily_Meter_Reading_Individual_Customer_Mmbtu;} //Defined By Samik Shah On 15th October, 2010 ...
	public Vector getDaily_Meter_Reading_Customer_Calculated_Gcv() {return daily_Meter_Reading_Customer_Calculated_Gcv;}
	public Vector getDaily_Meter_Reading_Customer_Calculated_Ncv() {return daily_Meter_Reading_Customer_Calculated_Ncv;}
	public Vector getDaily_Meter_Reading_Customer_Define_Gcv() {return daily_Meter_Reading_Customer_Define_Gcv;}
	public Vector getDaily_Meter_Reading_Customer_Define_Ncv() {return daily_Meter_Reading_Customer_Define_Ncv;}
	public String getDaily_Meter_Reading_Customer_Mmbtu_Total() {return daily_Meter_Reading_Customer_Mmbtu_Total;}
	public String getDaily_Meter_Reading_Customer_Scm_Total() {return daily_Meter_Reading_Customer_Scm_Total;}
	public String getDaily_Meter_Reading_Customer_Reconcil_Mmbtu_Total() {return daily_Meter_Reading_Customer_Reconcil_Mmbtu_Total;}
	public String getDaily_Meter_Reading_Customer_Reconcil_Scm_Total() {return daily_Meter_Reading_Customer_Reconcil_Scm_Total;}
	public String getDaily_Meter_Reading_Individual_Customer_Mmbtu_Total() {return daily_Meter_Reading_Individual_Customer_Mmbtu_Total;} //Defined By Samik Shah On 15th October, 2010 ...
	
	//Following String Getter Methods Are Defined By Samik Shah On 5th May, 2010 ...
	//For Daily Meter Reading Generation Day & Time ...
	public String getDaily_Meter_Reading_Gen_Date() {return daily_Meter_Reading_Gen_Date;}
	public String getDaily_Meter_Reading_Gen_Time() {return daily_Meter_Reading_Gen_Time;}

	
	//Following Vector & String Getter Methods Are Defined By Samik Shah On 6th May, 2010 ...
	//Storing Transporter & SN Details For Daily Buyer Allocation ...
	public Vector getMaster_Transporter_Cd_Buyer_Allocation() {return master_Transporter_Cd_Buyer_Allocation;}
	public Vector getMaster_Transporter_Abbr_Buyer_Allocation() {return master_Transporter_Abbr_Buyer_Allocation;}
	public Vector getMaster_Transporter_Count_Buyer_Allocation() {return master_Transporter_Count_Buyer_Allocation;}
	public Vector getMaster_Transporter_Dcq_Buyer_Allocation() {return master_Transporter_Dcq_Buyer_Allocation;}
	public Vector getDaily_Buyer_Allocation_Fgsa_No() {return daily_Buyer_Allocation_Fgsa_No;}
	public Vector getDaily_Buyer_Allocation_Fgsa_Rev_No() {return daily_Buyer_Allocation_Fgsa_Rev_No;}
	public Vector getDaily_Buyer_Allocation_Sn_No() {return daily_Buyer_Allocation_Sn_No;}
	public Vector getDaily_Buyer_Allocation_Sn_Rev_No() {return daily_Buyer_Allocation_Sn_Rev_No;}
	public Vector getDaily_Buyer_Allocation_Cd() {return daily_Buyer_Allocation_Cd;}
	public Vector getDaily_Buyer_Allocation_Abbr() {return daily_Buyer_Allocation_Abbr;}
	public Vector getDaily_Buyer_Allocation_Dcq() {return daily_Buyer_Allocation_Dcq;}
	public Vector getDaily_Buyer_Allocation_Plant_Cd() {return daily_Buyer_Allocation_Plant_Cd;}
	public Vector getDaily_Buyer_Allocation_Plant_Abbr() {return daily_Buyer_Allocation_Plant_Abbr;}
	public Vector getDaily_Transporter_Nom_Cd_Buyer_Allocation() {return daily_Transporter_Nom_Cd_Buyer_Allocation;}
	public Vector getDaily_Transporter_Nom_Abbr_Buyer_Allocation() {return daily_Transporter_Nom_Abbr_Buyer_Allocation;}
	public String getDaily_Total_Dcq_Buyer_Allocation() {return daily_Total_Dcq_Buyer_Allocation;}

	//Following Vector & String Getter Methods Are Defined By Samik Shah On 6th May, 2010 ...
	//Storing Details For Daily Buyer Allocation ...
	public Vector getDaily_Buyer_Allocation_Gen_Day_With_Rev_No() {return daily_Buyer_Allocation_Gen_Day_With_Rev_No;}
	public Vector getDaily_Buyer_Allocation_Gen_Day_Time() {return daily_Buyer_Allocation_Gen_Day_Time;}
	public Vector getDaily_Buyer_Allocation_Plant_Seq_No() {return daily_Buyer_Allocation_Plant_Seq_No;}
	public Vector getDaily_Buyer_Allocation_Qty_Mmbtu() {return daily_Buyer_Allocation_Qty_Mmbtu;}
	public Vector getDaily_Buyer_Allocation_Qty_Scm() {return daily_Buyer_Allocation_Qty_Scm;}
	public Vector getDaily_Buyer_Allocation_Qty_Offspec() {return daily_Buyer_Allocation_Qty_Offspec;}
	public Vector getDaily_Buyer_Allocation_Offspec_Rate() {return daily_Buyer_Allocation_Offspec_Rate;}
	public Vector getDaily_Buyer_Allocation_Offspec_Flag() {return daily_Buyer_Allocation_Offspec_Flag;}
	public Vector getDaily_Buyer_Allocation_Qty_FM() {return daily_Buyer_Allocation_Qty_FM;}
	public Vector getMaster_Transporter_Mmbtu_Buyer_Allocation() {return master_Transporter_Mmbtu_Buyer_Allocation;}
	public Vector getMaster_Transporter_Scm_Buyer_Allocation() {return master_Transporter_Scm_Buyer_Allocation;}
	public Vector getMaster_Transporter_Offspec_Buyer_Allocation() {return master_Transporter_Offspec_Buyer_Allocation;}
	public Vector getMaster_Transporter_FM_Buyer_Allocation() {return master_Transporter_FM_Buyer_Allocation;}
	public String getDaily_Total_Mmbtu_Buyer_Allocation() {return daily_Total_Mmbtu_Buyer_Allocation;}
	public String getDaily_Total_Scm_Buyer_Allocation() {return daily_Total_Scm_Buyer_Allocation;}
	public String getDaily_Total_Offspec_Buyer_Allocation() {return daily_Total_Offspec_Buyer_Allocation;}
	public String getDaily_Total_FM_Buyer_Allocation() {return daily_Total_FM_Buyer_Allocation;}
	public Vector getDaily_Buyer_Allocation_Type() {return daily_Buyer_Allocation_Type;}
	public Vector getDaily_Buyer_Allocation_Contract_Type() {return daily_Buyer_Allocation_Contract_Type;} //Introduced By Samik Shah On 25th August, 2010 ...

	//Following Vector & String Getter Methods Are Defined By Samik Shah On 10th May, 2010 ...
	public Vector getDaily_Customer_Meter_Allocation_Qty_Mmbtu() {return daily_Customer_Meter_Allocation_Qty_Mmbtu;}
	public Vector getDaily_Customer_Meter_Allocation_Qty_Scm() {return daily_Customer_Meter_Allocation_Qty_Scm;}
	public Vector getDaily_Customer_Meter_Allocation_Qty_Sug() {return daily_Customer_Meter_Allocation_Qty_Sug;}
	public Vector getDaily_Buyer_Allocation_Qty_Sug() {return daily_Buyer_Allocation_Qty_Sug;}
	public Vector getDaily_Meter_Based_Customer_flag() {return daily_Meter_Based_Customer_flag;}
	public String getDaily_Total_Sug_Buyer_Allocation() {return daily_Total_Sug_Buyer_Allocation;}

	//Following Vectors Getter Methods Are Defined By Samik Shah On 18th June, 2010 ...
	public Vector getDaily_JT_Customer_Cd() {return daily_JT_Customer_Cd;}
	public Vector getDaily_JT_Customer_Nm() {return daily_JT_Customer_Nm;}
	public Vector getDaily_JT_Customer_Abbr() {return daily_JT_Customer_Abbr;}
	public Vector getDaily_JT_Fgsa_No() {return daily_JT_Fgsa_No;}
	public Vector getDaily_JT_Fgsa_Rev_No() {return daily_JT_Fgsa_Rev_No;}
	public Vector getDaily_JT_Sn_No() {return daily_JT_Sn_No;}
	public Vector getDaily_JT_Sn_Rev_No() {return daily_JT_Sn_Rev_No;}
	public Vector getDaily_JT_Contract_Type() {return daily_JT_Contract_Type;}
	public Vector getDaily_JT_Plant_Seq_No() {return daily_JT_Plant_Seq_No;}
	public Vector getDaily_JT_Plant_Nm() {return daily_JT_Plant_Nm;}
	public Vector getDaily_JT_Gcv() {return daily_JT_Gcv;}
	public Vector getDaily_JT_Ncv() {return daily_JT_Ncv;}
	public Vector getDaily_JT_Transporter_Cd() {return daily_JT_Transporter_Cd;}
	public Vector getDaily_JT_Transporter_Nm() {return daily_JT_Transporter_Nm;}
	public Vector getDaily_JT_Transporter_Abbr() {return daily_JT_Transporter_Abbr;}
	public Vector getDaily_JT_Qty_Mmbtu() {return daily_JT_Qty_Mmbtu;}
	public Vector getDaily_JT_Qty_Scm() {return daily_JT_Qty_Scm;}
	public Vector getDaily_JT_Seller_Nom_Qty_Mmbtu() {return daily_JT_Seller_Nom_Qty_Mmbtu;}
	public Vector getDaily_JT_Transporter_Qty_Mmbtu() {return daily_JT_Transporter_Qty_Mmbtu;}
	public Vector getDaily_JT_SN_Qty_Mmbtu() {return daily_JT_SN_Qty_Mmbtu;}
	public Vector getDaily_JT_SN_Ref_No() {return daily_JT_SN_Ref_No;} //Introduced By Samik Shah On 19th April, 2011 ...
	
	//Following Vectors & String Getter Methods Are Defined By Samik Shah For Joint Ticket Format Preperation On 21st June, 2010 ...
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

	//Following String Getter Method Has Been Introduced By Samik Shah On 30th July, 2010 ...
	public String getGas_date() {return gas_date;}

	//Following Vector Getter Method Has Been Introduced By Samik Shah On 28th September, 2010 ...
	public Vector getSn_ref_no() {return sn_ref_no;}
	
	//Following (5) Getter Methods Has Been Defined By Achal Pathak On 24th December, 2010 ...
	public String getFrom_dt() {return from_dt;}
	public String getTo_dt() {return to_dt;}
	public String getNom_rev_no() {return nom_rev_no;}
	public String getTotal_mmbtu1() {return total_mmbtu1;}
	public String getTotal_scm1() {return total_scm1;}
	
	//Following (3) Vector Getter Methods Has Been Declared By Samik Shah On 23rd December, 2010 ...
	public Vector getCUSTOMER_CD() {return CUSTOMER_CD;}
	public Vector getCUSTOMER_ABBR() {return CUSTOMER_ABBR;}
	public Vector getCUSTOMER_NAME() {return CUSTOMER_NAME;}

	//Following (3) Vector Getter Methods Has Been Defined By Samik Shah On 24th December, 2010 ...
	public Vector getFGSA_SN_NO() {return FGSA_SN_NO;}
	public Vector getPLANT_SEQ_NO() {return PLANT_SEQ_NO;}
	public Vector getPLANT_NAME() {return PLANT_NAME;}

	//Following (2) Vector Getter Methods Has Been Defined By Samik Shah On 28th December, 2010 ...
	public Vector getMONTH_DAYS() {return MONTH_DAYS;}
	public Vector getMONTH_DATES() {return MONTH_DATES;}	

	//Following (10) Vector Getter Methods Has Been Defined By Achal Pathak On 24th December, 2010 ...
	public Vector getWEEKDATES() {return WEEKDATES;}
	public Vector getWEEKDAYS() {return WEEKDAYS;}
	public Vector getEND_DT() {return END_DT;}
	public Vector getSTART_DT() {return START_DT;}
	public Vector getQTY_MMBTU() {return QTY_MMBTU;}
	public Vector getQTY_SCM() {return QTY_SCM;}
	public Vector getNOM_REV_NO() {return NOM_REV_NO;}
	public Vector getSUM_MMBTU() {return SUM_MMBTU;}
	public Vector getSUM_SCM() {return SUM_SCM;}
	public Vector getGEN_DT() {return GEN_DT;}


	public String getGcv() {return gcv;}
	public String getNcv() {return ncv;}

	public Vector getCUST_CD() {
		return CUST_CD;
	}

	public Vector getCUST_NM() {
		return CUST_NM;
	}

	public Vector getGAS_DT() {
		return GAS_DT;
	}

	public Vector getQTY_FM() {
		return QTY_FM;
	}

	public Vector getQTY_OFFSPEC() {
		return QTY_OFFSPEC;
	}

	public Vector getSELLER_OBLIG() {
		return SELLER_OBLIG;
	}

	//Following String Getter Method Has Been Introduced By Samik Shah On 11th April, 2011 ... 
	public String getGen_date() {return gen_date;}

	public Vector getDaily_Alloc_Nom_Qty_Mmbtu() {
		return daily_Alloc_Nom_Qty_Mmbtu;
	}

	public Vector getDaily_Alloc_Nom_Qty_Scm() {
		return daily_Alloc_Nom_Qty_Scm;
	}

	public Vector getPM_QTY() {
		return PM_QTY;
	}

	public Vector getBUYER_DEFAULT_QTY() {
		return BUYER_DEFAULT_QTY;
	}

	public Vector getBUYER_DEFAULT_REASON() {
		return BUYER_DEFAULT_REASON;
	}

	public Vector getBUYER_SUSPENSION_QTY() {
		return BUYER_SUSPENSION_QTY;
	}

	public Vector getSELLER_DEFAULT_REASON() {
		return SELLER_DEFAULT_REASON;
	}

	public Vector getSELLER_OR_PAY_QTY() {
		return SELLER_OR_PAY_QTY;
	}

	public Vector getFAILURE_TO_DELIVER_QTY() {
		return FAILURE_TO_DELIVER_QTY;
	}

	public Vector getTAKE_OR_PAY_QTY() {
		return TAKE_OR_PAY_QTY;
	}

	//Following Vector Getter Method Has Been Introduced By Samik Shah On 26th May, 2011 ...
	public Vector getSeller_Nom_Weighted_Avg() {return seller_Nom_Weighted_Avg;}

	//Following (2) Vector Getter Methods Has Been Introduced By Samik Shah On 22nd June, 2011 ...
	public Vector getDaily_Buyer_Nom_LC_ADV_Flag() {return daily_Buyer_Nom_LC_ADV_Flag;}
	public Vector getDaily_Buyer_Nom_Current_Balance_Amt() {return daily_Buyer_Nom_Current_Balance_Amt;}

	public Vector getLC_DAY_FLG() {
		return LC_DAY_FLG;
	}

	public Vector getLC_END_DT_FIN() {
		return LC_END_DT_FIN;
	}

	public Vector getLC_EXP_FLG() {
		return LC_EXP_FLG;
	}
	public void setLc_customer_cd(String lc_customer_cd) {
		this.lc_customer_cd = lc_customer_cd;
	}
	public void setLc_from_dt(String lc_from_dt) {
		this.lc_from_dt = lc_from_dt;
	}
	public void setLc_seq_no(String lc_seq_no) {
		this.lc_seq_no = lc_seq_no;
	}
	public void setLc_to_dt(String lc_to_dt) {
		this.lc_to_dt = lc_to_dt;
	}
	public Vector getLC_FGSA_NO() {
		return LC_FGSA_NO;
	}
	public Vector getLC_SN_END_DT() {
		return LC_SN_END_DT;
	}
	public Vector getLC_SN_NO() {
		return LC_SN_NO;
	}
	public Vector getLC_SN_START_DT() {
		return LC_SN_START_DT;
	}
	public Vector getLC_DCQ() {
		return LC_DCQ;
	}
	public Vector getLC_TCQ() {
		return LC_TCQ;
	}
	public Vector getLC_FINANCIAL_YR() {
		return LC_FINANCIAL_YR;
	}
	public Vector getLC_CONT_TYPE() {
		return LC_CONT_TYPE;
	}
	public Vector getDaily_JT_offspec_qty() {
		return daily_JT_offspec_qty;
	}

	public Vector getDaily_JT_offspec_flag() {
		return daily_JT_offspec_flag;
	}
	
	public Vector getLC_REGAS_CONT_TYPE() {
		return LC_REGAS_CONT_TYPE;
	}
	public Vector getLC_REGAS_DCQ() {
		return LC_REGAS_DCQ;
	}
	public Vector getLC_REGAS_END_DT() {
		return LC_REGAS_END_DT;
	}
	public Vector getLC_REGAS_FINANCIAL_YR() {
		return LC_REGAS_FINANCIAL_YR;
	}
	public Vector getLC_REGAS_NO() {
		return LC_REGAS_NO;
	}
	public Vector getLC_REGAS_START_DT() {
		return LC_REGAS_START_DT;
	}
	public Vector getLC_REGAS_TCQ() {
		return LC_REGAS_TCQ;
	}
	public Vector getLC_REV_NO() {
		return LC_REV_NO;
	}
	
	public void setLc_fin_yr(String lc_fin_yr) {
		this.lc_fin_yr = lc_fin_yr;
	}



	public Vector getDaily_Seller_regas_cargo_boe_dt() {
		return daily_Seller_regas_cargo_boe_dt;
	}



	public void setDaily_Seller_regas_cargo_boe_dt(
			Vector daily_Seller_regas_cargo_boe_dt) {
		this.daily_Seller_regas_cargo_boe_dt = daily_Seller_regas_cargo_boe_dt;
	}



	public Vector getDaily_Seller_regas_cargo_boe_no() {
		return daily_Seller_regas_cargo_boe_no;
	}



	public void setDaily_Seller_regas_cargo_boe_no(
			Vector daily_Seller_regas_cargo_boe_no) {
		this.daily_Seller_regas_cargo_boe_no = daily_Seller_regas_cargo_boe_no;
	}



	public Vector getDaily_Buyer_regas_cargo_boe_dt() {
		return daily_Buyer_regas_cargo_boe_dt;
	}



	public void setDaily_Buyer_regas_cargo_boe_dt(
			Vector daily_Buyer_regas_cargo_boe_dt) {
		this.daily_Buyer_regas_cargo_boe_dt = daily_Buyer_regas_cargo_boe_dt;
	}



	public Vector getDaily_Buyer_regas_cargo_boe_no() {
		return daily_Buyer_regas_cargo_boe_no;
	}



	public void setDaily_Buyer_regas_cargo_boe_no(
			Vector daily_Buyer_regas_cargo_boe_no) {
		this.daily_Buyer_regas_cargo_boe_no = daily_Buyer_regas_cargo_boe_no;
	}



	public Vector getDaily_Buyer_Nom_Mapping_Id() {
		return daily_Buyer_Nom_Mapping_Id;
	}



	public void setDaily_Buyer_Nom_Mapping_Id(Vector daily_Buyer_Nom_Mapping_Id) {
		this.daily_Buyer_Nom_Mapping_Id = daily_Buyer_Nom_Mapping_Id;
	}



	public Vector getDaily_Seller_Nom_Mapping_Id() {
		return daily_Seller_Nom_Mapping_Id;
	}



	public void setDaily_Seller_Nom_Mapping_Id(Vector daily_Seller_Nom_Mapping_Id) {
		this.daily_Seller_Nom_Mapping_Id = daily_Seller_Nom_Mapping_Id;
	}



	public Vector getDaily_Buyer_Allocation_Mapping_Id() {
		return daily_Buyer_Allocation_Mapping_Id;
	}



	public void setDaily_Buyer_Allocation_Mapping_Id(
			Vector daily_Buyer_Allocation_Mapping_Id) {
		this.daily_Buyer_Allocation_Mapping_Id = daily_Buyer_Allocation_Mapping_Id;
	}



	public String getNom_mapping_id() {
		return nom_mapping_id;
	}



	public void setNom_mapping_id(String nom_mapping_id) {
		this.nom_mapping_id = nom_mapping_id;
	}



	public Vector getPRE_APPROVAL() {
		return PRE_APPROVAL;
	}



	public void setPRE_APPROVAL(Vector pRE_APPROVAL) {
		PRE_APPROVAL = pRE_APPROVAL;
	}

	public Vector getDaily_JT_Mapping_Id() {
		return daily_JT_Mapping_Id;
	}

	public void setDaily_JT_Mapping_Id(Vector daily_JT_Mapping_Id) {
		this.daily_JT_Mapping_Id = daily_JT_Mapping_Id;
	}

	public Vector getDaily_JT_Sn_Signing_Dt() {
		return daily_JT_Sn_Signing_Dt;
	}

	public void setDaily_JT_Sn_Signing_Dt(Vector daily_JT_Sn_Signing_Dt) {
		this.daily_JT_Sn_Signing_Dt = daily_JT_Sn_Signing_Dt;
	}

	public Vector getCOMM_PRE_APPROVAL() {
		return COMM_PRE_APPROVAL;
	}

	public void setCOMM_PRE_APPROVAL(Vector comm_pre_approval) {
		COMM_PRE_APPROVAL = comm_pre_approval;
	}

	public Vector getDaily_tax_struct_dtl() {
		return daily_tax_struct_dtl;
	}

	public void setDaily_tax_struct_dtl(Vector daily_tax_struct_dtl) {
		this.daily_tax_struct_dtl = daily_tax_struct_dtl;
	}
	public Vector getDaily_Meter_Reading_Transporter_Btu() {
		return daily_Meter_Reading_Transporter_Btu;
	}

	public void setDaily_Meter_Reading_Transporter_Btu(Vector daily_Meter_Reading_Transporter_Btu) {
		this.daily_Meter_Reading_Transporter_Btu = daily_Meter_Reading_Transporter_Btu;
	}

	public Vector getDaily_Meter_Reading_Transporter_Reconcil_Btu() {
		return daily_Meter_Reading_Transporter_Reconcil_Btu;
	}

	public void setDaily_Meter_Reading_Transporter_Reconcil_Btu(Vector daily_Meter_Reading_Transporter_Reconcil_Btu) {
		this.daily_Meter_Reading_Transporter_Reconcil_Btu = daily_Meter_Reading_Transporter_Reconcil_Btu;
	}

	public String getDaily_Meter_Reading_Transporter_Btu_Total() {
		return daily_Meter_Reading_Transporter_Btu_Total;
	}

	public void setDaily_Meter_Reading_Transporter_Btu_Total(String daily_Meter_Reading_Transporter_Btu_Total) {
		this.daily_Meter_Reading_Transporter_Btu_Total = daily_Meter_Reading_Transporter_Btu_Total;
	}

	public String getDaily_Meter_Reading_Transporter_Reconcil_Btu_Total() {
		return daily_Meter_Reading_Transporter_Reconcil_Btu_Total;
	}

	public void setDaily_Meter_Reading_Transporter_Reconcil_Btu_Total(
			String daily_Meter_Reading_Transporter_Reconcil_Btu_Total) {
		this.daily_Meter_Reading_Transporter_Reconcil_Btu_Total = daily_Meter_Reading_Transporter_Reconcil_Btu_Total;
	}

	public Vector getDaily_Meter_Reading_Customer_Btu() {
		return daily_Meter_Reading_Customer_Btu;
	}

	public void setDaily_Meter_Reading_Customer_Btu(Vector daily_Meter_Reading_Customer_Btu) {
		this.daily_Meter_Reading_Customer_Btu = daily_Meter_Reading_Customer_Btu;
	}

	public Vector getDaily_Meter_Reading_Customer_Reconcil_Btu() {
		return daily_Meter_Reading_Customer_Reconcil_Btu;
	}

	public void setDaily_Meter_Reading_Customer_Reconcil_Btu(Vector daily_Meter_Reading_Customer_Reconcil_Btu) {
		this.daily_Meter_Reading_Customer_Reconcil_Btu = daily_Meter_Reading_Customer_Reconcil_Btu;
	}

	public String getDaily_Meter_Reading_Customer_Btu_Total() {
		return daily_Meter_Reading_Customer_Btu_Total;
	}

	public void setDaily_Meter_Reading_Customer_Btu_Total(String daily_Meter_Reading_Customer_Btu_Total) {
		this.daily_Meter_Reading_Customer_Btu_Total = daily_Meter_Reading_Customer_Btu_Total;
	}

	public String getDaily_Meter_Reading_Customer_Reconcil_Btu_Total() {
		return daily_Meter_Reading_Customer_Reconcil_Btu_Total;
	}

	public void setDaily_Meter_Reading_Customer_Reconcil_Btu_Total(String daily_Meter_Reading_Customer_Reconcil_Btu_Total) {
		this.daily_Meter_Reading_Customer_Reconcil_Btu_Total = daily_Meter_Reading_Customer_Reconcil_Btu_Total;
	}

	public Vector getQty_nomination() {
		return qty_nomination;
	}

	public void setQty_nomination(Vector qty_nomination) {
		this.qty_nomination = qty_nomination;
	}

	public String getEmp_cd() {
		return Emp_cd;
	}

	public void setEmp_cd(String emp_cd) {
		Emp_cd = emp_cd;
	}

	public String getCustomer_access_flag() {
		return Customer_access_flag;
	}

	public void setCustomer_access_flag(String customer_access_flag) {
		Customer_access_flag = customer_access_flag;
	}

	
	public Vector getPlant_Abbr() {
		return Plant_Abbr;
	}

	public void setPlant_Abbr(Vector plant_Abbr) {
		Plant_Abbr = plant_Abbr;
	}

	public Vector getPlant_seq_no1() {
		return plant_seq_no1;
	}

	public Vector getDates() {
		return dates;
	}

	public String getRemark() {
		return remark;
	}

	public Vector getDr_cr_flag() {
		return dr_cr_flag;
	}

	public Vector getSn_no_cr_dr() {
		return sn_no_cr_dr;
	}

	public void setSn_no_cr_dr(Vector sn_no_cr_dr) {
		this.sn_no_cr_dr = sn_no_cr_dr;
	}

	public Vector getFgsa_no_cr_dr() {
		return fgsa_no_cr_dr;
	}

	public void setFgsa_no_cr_dr(Vector fgsa_no_cr_dr) {
		this.fgsa_no_cr_dr = fgsa_no_cr_dr;
	}

	public Vector getContract_type_cr_dr() {
		return contract_type_cr_dr;
	}

	public void setContract_type_cr_dr(Vector contract_type_cr_dr) {
		this.contract_type_cr_dr = contract_type_cr_dr;
	}
///////////////SB20181005////////////////////////////////////////
	public Vector Vdaily_Buyer_Nom_Name = new Vector();
	public Vector getVdaily_Buyer_Nom_Name() {return Vdaily_Buyer_Nom_Name;}
	public Vector VTransporter_Name = new Vector();
	public Vector getVTransporter_Name() {return VTransporter_Name;}
	String LastMeterReadingDt="";
	public String getLastMeterReadingDt() {return LastMeterReadingDt;}
	String LastCustMeterReadingDt="";
	public String getCustLastMeterReadingDt() {return LastCustMeterReadingDt;}
///////////////////////////////////////////////////////////////////	
	
}//End Of Class DataBean_Contract_Mgmt ...