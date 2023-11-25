package com.seipl.hazira.dlng.master;
import javax.naming.*;
import javax.sql.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

import java.util.*;
import java.sql.*;
import java.text.*;

//Coded  by			  :   Hitesh Lad
//CR Date			  :   11.03.2010
//CR Status  		  :
//Last Modified By    :   Samik Shah
//Last Modified Date  :   23.04.2010
//Modification Remark :   Due to Insertion of two more Fields in Entity Master form.(Effective date , Credit Rating)
public class DataBean_Entity
{
    Connection conn; 
	Statement stmt;
	Statement stmt1;
	ResultSet rset;
	ResultSet rset1;
	String queryString="";
	String queryString1="";
	
	//Following NumberFormat Object is defined by Samik Shah ... On 8th Dec., 2009 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	
	//Following util Object is defined by Samik Shah ... On 1st Dec., 2009 ...
	UtilBean util = new UtilBean();
		
	//Following (3) String Variables are defined by Darshil BAHAYNI ... On 2nd Nov., 2009 ...
	public String callFlag="";
	public String bscode = "";
	public String searchString = "";
	
	String set_group_id = "";
	String set_emp_id = "";
	
	public static int tot_size = 0;
	
	HashMap m1 = new HashMap();
	List l1  = new ArrayList();
	
	//Following String Variable is defined by Samik Shah ... On 6th Nov., 2009 ...
	public String set_sr_no = "1";
	
	//BOTH The Following String Variables are defined by Samik Shah ... On 1st Dec., 2009 ...
	public String dt_from = util.getDate_first();
	public String dt_to = util.getGen_dt();
	
	
	public String EntityFlag = "";
	public String ActiveFlag = "";
	String Entity_Id = "";
	String plant_seq_no = ""; //Introduced By Samik Shah On 14th July, 2010 ...
	String month = "";
	String year = "";
	Vector Entity_code = new Vector();
	Vector Entity_name = new Vector();
	Vector Entity_abr = new Vector();
	Vector Entity_flag = new Vector(); //SB20130711
	Vector Effect_date = new Vector();
	Vector Entity_tot_size = new Vector();
	
	// Variables for the Entity Master details
	String ENTITY_CD = ""; 
	String ENTITY_NAME= "";  
	String ENTITY_ABBR= "";  
	String GST_TIN_NO= ""; 
	String GST_TIN_DT  = ""; 
	String CST_TIN_NO = ""; 
	String CST_TIN_DT = ""; 
	String PAN_NO = ""; 
	String PAN_ISSUE_DT = ""; 
	String TAN_NO= ""; 
	String TAN_ISSUE_DT = ""; 
	String ADDL_NO = ""; 
	String ADDL_ISSUE_DT = ""; 
	String WEB_ADDR = ""; 
	String NOTES= ""; 
	String ENTITY_EFF_DT="";  //added on 11/03/2010
	String CREDIT_RATING="";  //added on 11/03/2010

	String GSTIN_NO = ""; //RS01062017 FOR GST
	String GSTIN_DT = ""; //RS01062017
	
	//Variables for the Entity Address Details
	String[] EFF_DT = new String[3]; 
	String[] ADDR= new String[3];
	String[] CITY= new String[3];
	String[] PIN= new String[3];
	String[] STATE= new String[3];
	String[] ZONE = new String[3];
	String[] COUNTRY= new String[3];
	String[] PHONE= new String[3];
	String[] MOBILE= new String[3];
	String[] ALT_PHONE= new String[3];
	String[] FAX_1= new String[3];
	String[] FAX_2= new String[3];
	String[] EMAIL= new String[3];
  
	//Variables for the Entity for the Plant Details
	String[]    SEQ_NO ; // = new String[15]; 
    String[]    PEFF_DT; // = new String[15]; 
    String[]  	PLANT_TYPE; //= new String[15]; 
    String[]   	PLANT_NAME; //= new String[15]; 
    String[]   	PLANT_ABBR; //= new String[15]; 
    String[]   	PLANT_ADDR; //= new String[15]; 
    String[]   	PLANT_STATE; //= new String[15]; 
    String[]   	PLANT_ZONE; //= new String[15]; 
    String[]   	PLANT_CITY; //= new String[15]; 
    String[]   	PLANT_PIN; //= new String[15]; 
    String[]   	PLANT_SECTOR; //= new String[15];
    String[]    PLANT_SHORT_ABBR; // = new String[15];
    String[]	PLANT_STATE_CODE; // = new String[15]; //RS29052017
    
    //Following Four String Array Variables Has Been Defined By Samik Shah ... On 29th May, 2010 ...
    String[]    TAX_STRUCT_CD; // = new String[15]; 
    String[]    TAX_STRUCT_DT; // = new String[15]; 
    String[]  	TAX_STRUCT_DTL; // = new String[15]; 
    String[]   	TAX_STRUCT_REMARK; // = new String[15];
    
    //Following Four String Array Variables Has Been Defined By Samik Shah ... On 26th August, 2010 ...
    String[]    SERVICE_TAX_STRUCT_CD; // = new String[15]; 
    String[]    SERVICE_TAX_STRUCT_DT; // = new String[15]; 
    String[]  	SERVICE_TAX_STRUCT_DTL; // = new String[15]; 
    String[]   	SERVICE_TAX_STRUCT_REMARK; // = new String[15]; 
	    
	//Variable for the Sector details
    Vector SECTOR_CD = new Vector();
    Vector SECTOR_NM = new Vector();
	
    Vector  CSEQ_NO= new Vector();
    Vector  CEFF_DT= new Vector();
    Vector  CONTACT_PERSON= new Vector();
    Vector  CPHONE= new Vector();
    Vector  CMOBILE= new Vector();
    Vector  CFAX_1= new Vector();
    Vector  CFAX_2 = new Vector();
    Vector  CEMAIL= new Vector();
    Vector  ADDR_FLAG= new Vector();
    Vector   ADDL_ADDR_LINE= new Vector();
    Vector   NOM_FLAG = new Vector();
    Vector  INV_FLAG = new Vector();
    Vector  FM_FLAG= new Vector();
    Vector  PM_FLAG = new Vector();
    Vector  JT_FLAG = new Vector();
    Vector  OTHER_FLAG= new Vector();
    Vector   DNOM_FLAG = new Vector();
    Vector  DINV_FLAG = new Vector();
    Vector  DFM_FLAG= new Vector();
    Vector  DPM_FLAG = new Vector();
    Vector  DJT_FLAG = new Vector();
    Vector  DOTHER_FLAG= new Vector();
    
    Vector  ACTIVE_FLAG = new Vector();
    Vector  CONTACT_DESIG = new Vector();
 
    Vector PLANT_SEQ_NO = new Vector();
    Vector PLANT_TYP = new Vector();
    Vector PLANT_NM = new Vector();
    Vector PLANT_ABR = new Vector();
	
    Vector CALENDAR_FLAG = new Vector();
    Vector GIFT_FLAG= new Vector();
    Vector DIARY_FLAG= new Vector();
    Vector LEAFLET_FLAG = new Vector();
    Vector OTHER_1= new Vector();
    Vector OTHER_2= new Vector();
    Vector DESCRIPTION= new Vector();
    
    //Variable for the meter 
    int new_meter_seq = 1;
    String meterSeqNo="";
    String meterId = "";
    
    Vector TRANS_CUST_CD = new Vector();
    Vector TRANS_CUST_NAME = new Vector();
    Vector TRANS_CUST_ABBR = new Vector();
    Vector METER_SEQ_NO= new Vector(); 
    Vector METER_TYPE= new Vector();
    Vector METER_ID= new Vector();
    Vector SPECIFICATION= new Vector();
    Vector TRANSPORTER_CD= new Vector();
    Vector NOTE= new Vector(); 
    
    String meter_speci ="";
    String trans_cd = "";
    String trans_abr = "";
    String meter_note = "";
    
    String[] CONT_EFF_DT  = new String[3];
	String[] CONT_CONTACT_PERSON  = new String[3];
	String[] CONT_CONTACT_DESIG  = new String[3];
	String[] CONT_CONTACT_DEPT  = new String[3];
	String[] CONT_PHONE = new String[3];
	String[] CONT_MOBILE = new String[3];
	String[] CONT_FAX_1 = new String[3];
	String[] CONT_FAX_2 = new String[3];
	String[] EMAIL_1 = new String[3];
	String[] EMAIL_2  = new String[3];
	 
    Vector CONTACT_TYPE= new Vector();
    
    //Following 4 String Variables are defined by Samik Shah On 7th July, 2010 ...
    //Following 4 String Variables are used to fetch master data regarding Cargo BCD Tax Details ...
    String cargo_tax_str_cd = "";
    String cargo_tax_app_dt = "";
    String cargo_tax_desc = "";
    String cargo_tax_remark = "";
    
    //Following 10 String Variables are defined by Samik Shah On 14th July, 2010 ...
    //Following 10 String Variables are used to fetch Plant Related Tax Number's Information ...
    String gst_tin_no = "";
	String gst_tin_dt = "";
	String cst_tin_no = "";
	String cst_tin_dt = "";
	String pan_no = "";
	String pan_issue_dt = "";
	String tan_no = "";
	String tan_issue_dt = "";
	String service_tax_no = "";
	String service_tax_dt = "";
	
	String app_date 	= "";				
	String cif_percent = "";
	String remarks 	= "";
	
	//Following (4) Vectors Has Been Defined By Samik Shah On 13th January, 2011 ...
	public Vector Entity_code2 = new Vector();
	public Vector Entity_name2 = new Vector();
	public Vector Entity_abr2 = new Vector();
	public Vector Entity_type2 = new Vector();
	
    //Following 2 String Variables are defined by Priyanka On 15th April, 2011 ...
	String gvat_tin_no = "";
	String gvat_tin_dt = "";
	
	//Following (4) Vector Variables Have Been Introduced By Samik Shah On 4th August, 2011 ...
	public Vector Entity_sun_account_code = new Vector();
	public Vector Purchase_sun_account_code = new Vector();
	public Vector Custom_duty_sun_account_code = new Vector();
	public Vector Shell_group_flag = new Vector();
	
	//Following (6) Vectors Have Been Introduced By Samik Shah On 3rd September, 2011 ...
	public Vector vSTAT_CD = new Vector();
	public Vector vSTAT_NM = new Vector();
	public Vector vSTAT_TYPE = new Vector();
	public Vector vSTAT_NO = new Vector();
	public Vector vSTAT_EFF_DT = new Vector();
	public Vector vSTAT_REMARKS = new Vector();
    
	public void init()
	{
	    try
	    {
	    	//System.out.println("callFlag (1) = "+callFlag);
	    	
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
	    			
	    			//System.out.println("callFlag (2) = "+callFlag);
	    			createTable_State_Code(); 
	    			createTable_Vendor_Mst();
	    			if(callFlag.equals("Entity_Details_Search")) 
	    			{
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					Fetch_All_Entity_Detail_List_WITH_ACCESS(); //SB20130711
	    				} else {
	    					Fetch_All_Entity_Detail_List(); //SB20130711
	    				}
	    			}
	    			else if(callFlag.equals("Entity_Details_all_Search")) 
	    			{
	    				Fetch_All_Entity_Detail_all_List(); //DCB201411
	    			}
	    			else if(callFlag.equals("Supplier_Details_Search"))
	    			{
	    				//System.out.println("From init >>>>");
	    				Fetch_All_Entity_Detail_List();
	    			}
	    			else if(callFlag.equals("Entity_Details")) 
	    			{
	    				checkColumn();
	    				fetch_State_Codes();
	    				fetch_Sector_details();
	    				Fetch_All_Entity_Detail_List();
	    				Fetch_All_Entity_Detail_List2();
	    				if(ActiveFlag.equalsIgnoreCase("update"))
	    				{
	    					Fetch_Selected_Entity_Details();
	    				}
	    				else
	    				{
	    					Fill_Array_Variables();
	    				}
	    			}
	    			else if(callFlag.equals("Entity_contact_mst"))
	    			{
	    				Entity_master_details();
	    				Fetch_Entity_Plant_Details();
		    		    Fetch_Entity_Contact_Details();
	    			}
	    			else if(callFlag.equalsIgnoreCase("Customer_Gift_Tracking"))
	    			{
	    				Entity_master_details();        // Getting name of the customer
	    				Fetch_Entity_Contact_Details2(); // Getting no of contacts of the customer
	    				Fetch_Entity_Gifts_Details();
	    			}
	    			else if(callFlag.equalsIgnoreCase("Entity_Details_for_meter"))
	    			{
	    				if(ActiveFlag.equalsIgnoreCase("insert"))
	    				{
	    				   Fetch_All_Entity_Detail_List();  // Fetch list of Entity from the masters when flag is new or Insert
	    				}
	    				else
	    				{
	    				   Fetch_All_Entity_Detail_List();  
	    				   Fetch_Cust_Trans_Meter_Details();	 
	    				}
	    			}
	    			else if(callFlag.equalsIgnoreCase("Entity_Meter_Details"))
	    			{
	    				if(ActiveFlag.equalsIgnoreCase("insert"))
	    				{
	    					Entity_master_details();   
	    					getMeterSequenceNo();
	    				}
	    				else
	    				{
	    					Entity_master_details();   
	    					getExisitingMeterDetails();
	    				}
	    			}
	    			else if(callFlag.equalsIgnoreCase("Meter_Trans_list"))
	    			{
	    				   get_Transporter_List_fromMeter();
	    			}
	    			else if(callFlag.equalsIgnoreCase("Other_Entity_Details"))
	    			{
	    				 Fetch_All_Entity_Detail_List();
	    				 fetch_other_entity_details();
	    			}
	    			else if(callFlag.equalsIgnoreCase("Cargo_Tax_Master"))
	    			{
	    				 Fetch_Cargo_Tax_Master_Detail();
	    			}
	    			else if(callFlag.equalsIgnoreCase("Fetch_Plant_Tax_Info"))
	    			{
	    				Fetch_Plant_Tax_Information();
	    				fetchGovtStatNoList();
	    			}
	    		}
	    	}
	    }
	    
	    catch(Exception e)
	    {
	    	//System.out.println("Exception:(DataBean_Entity)-(init()): "+e);
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
					//System.out.println("rset is not close " + e);
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
					//System.out.println("rset is not close " + e);
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
					//System.out.println("stmt is not close " + e);
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
					//System.out.println("stmt1 is not close " + e);
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
					//System.out.println("conn is not close " + e);
				}
			}
	    }
	}
	
		
	//RS 29052017
		Vector state_code = new Vector();
		Vector state_name = new Vector();
		
		public void fetch_State_Codes() throws Exception {
			try {
				String query = "SELECT STATE_CODE,STATE_NM FROM STATE_MST WHERE FLAG='Y' ORDER BY TYPE,STATE_NM";
				rset = stmt.executeQuery(query);
				while(rset.next()) {
					state_code.add(rset.getString(1));
					state_name.add(rset.getString(2));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void createTable_Vendor_Mst() throws Exception
		{
			try {
				String q = "SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) = 'FMS8_VENDOR_MST' ";
				rset = stmt.executeQuery(q);
				if(rset.next()) {
					int count = rset.getInt(1);
					if(count==0) {
						q = "CREATE TABLE FMS8_VENDOR_MST (VENDOR_CD NUMBER(6,0) NOT NULL,"
								+ "EFF_DT DATE NOT NULL,"
								+ "VENDOR_NAME VARCHAR2(100),"
								+ "VENDOR_ABBR VARCHAR2(20),"
								+ "GST_TIN_NO VARCHAR2(20 BYTE),"
								+ "GST_TIN_DT DATE,"
								+ "CST_TIN_NO VARCHAR2(20 BYTE),"
								+ "CST_TIN_DT DATE,"
								+ "PAN_NO VARCHAR2(20 BYTE),"
								+ "PAN_ISSUE_DT DATE,"
								+ "TAN_NO VARCHAR2(20 BYTE),"
								+ "TAN_ISSUE_DT DATE,"
								+ "ADDL_NO VARCHAR2(20 BYTE),"
								+ "ADDL_ISSUE_DT DATE,"
								+ "GSTIN_NO VARCHAR2(20 BYTE),"
								+ "GSTIN_DT DATE,"
								+ "WEB_ADDR VARCHAR2(100 BYTE),"
								+ "NOTES VARCHAR2(2048 BYTE),"
								+ "EMP_CD NUMBER(6,0),"
								+ "ENT_DT DATE,"
								+ "FLAG CHAR(1),"
								+ "CONSTRAINT PK_FMS8_VEN_MST PRIMARY KEY(VENDOR_CD,EFF_DT) "
								+ ")";
						stmt.executeUpdate(q);
						
						q = "CREATE TABLE FMS8_VENDOR_ADDRESS_MST (VENDOR_CD NUMBER(6,0) NOT NULL,"
								+ "EFF_DT DATE NOT NULL,"
								+ "ADDRESS_TYPE CHAR(1) NOT NULL,"
								+ "ADDR VARCHAR2(300 BYTE),"
								+ "CITY VARCHAR2(40 BYTE),"
								+ "PIN NUMBER(6,0),"
								+ "STATE VARCHAR2(40 BYTE),"
								+ "ZONE CHAR(1),"
								+ "COUNTRY VARCHAR2(40 BYTE),"
								+ "PHONE VARCHAR2(20 BYTE),"
								+ "MOBILE VARCHAR2(20 BYTE),"
								+ "ALT_PHONE VARCHAR2(20 BYTE),"
								+ "FAX_1 VARCHAR2(20 BYTE),"
								+ "FAX_2 VARCHAR2(20 BYTE),"
								+ "EMAIL VARCHAR2(40 BYTE),"
								+ "EMP_CD NUMBER(6,0),"
								+ "FLAG CHAR(1),"
								+ "ENT_DT DATE,"
								+ "CONSTRAINT PK_FMS8_VEN_ADDR_MST PRIMARY KEY(VENDOR_CD,ADDRESS_TYPE,EFF_DT) "
								+ ")";
						stmt.executeUpdate(q);
						
						q = "CREATE TABLE FMS8_VENDOR_PLANT_DTL (VENDOR_CD NUMBER(6,0) NOT NULL,"
								+ "EFF_DT DATE NOT NULL,"
								+ "SEQ_NO NUMBER(2,0) NOT NULL,"
								+ "PLANT_TYPE VARCHAR2(3 BYTE),"
								+ "PLANT_NAME VARCHAR2(100 BYTE),"
								+ "PLANT_ABBR VARCHAR2(10 BYTE),"
								+ "PLANT_ADDR VARCHAR2(300 BYTE),"
								+ "PLANT_STATE VARCHAR2(40 BYTE),"
								+ "PLANT_ZONE CHAR(1),"
								+ "PLANT_CITY VARCHAR2(40 BYTE),"
								+ "PLANT_PIN VARCHAR2(8 BYTE),"
								+ "PLANT_SHORT_ABBR VARCHAR2(4 BYTE),"
								+ "PLANT_SECTOR NUMBER(2,0),"
								+ "EMP_CD NUMBER(6,0),"
								+ "FLAG CHAR(1),"
								+ "ENT_DT DATE,"
								+ "CONSTRAINT PK_FMS8_VEN_PLNT_DTL PRIMARY KEY(VENDOR_CD,SEQ_NO,EFF_DT) "
								+ ")";
						stmt.executeUpdate(q);
						conn.commit();
					}
				}
			} catch(Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		}
		
		public void createTable_State_Code() throws Exception
		{
			try {
				String q = "SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) = 'STATE_MST' ";
				rset = stmt.executeQuery(q);
				if(rset.next()) {
					int count = rset.getInt(1);
					if(count==0) {
						q = "CREATE TABLE STATE_MST (STATE_CODE VARCHAR2(5 BYTE) NOT NULL,"
								+ "STATE_NM VARCHAR2(50 BYTE) NOT NULL,"
								+ "TYPE CHAR(2),"
								+ "FLAG CHAR(1),"
								+ "REMARK VARCHAR2(200 BYTE)"
								+ ")";
						stmt.executeUpdate(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('01','Jammu and Kashmir','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('02','Himachal Pradesh','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('03','Punjab','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('04','Chandigarh','U','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('05','Uttarakhand','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('06','Haryana','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('07','Delhi','U','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('08','Rajasthan','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('09','Uttar Pradesh','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('10','Bihar','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('11','Sikkim','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('12','Arunachal Pradesh','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('13','Nagaland','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('14','Manipur','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('15','Mizoram','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('16','Tripura','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('17','Meghalaya','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('18','Assam','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('19','West Bengal','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('20','Jharkhand','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('21','Orissa','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('22','Chhattisgarh','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('23','Madhya Pradesh','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('24','Gujarat','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('25','Daman and Diu','U','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('26','Dadra and Nagar Haveli','U','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('27','Maharashtra','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('37','Andhra Pradesh','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('29','Karnataka','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('30','Goa','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('31','Lakshadweep','U','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('32','Kerala','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('33','Tamil Nadu','S','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('34','Puducherry','U','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('35','Andaman and Nicobar Islands','U','Y')";
						stmt.executeQuery(q);
						
						q = "INSERT INTO STATE_MST(STATE_CODE,STATE_NM,TYPE,FLAG) "
								+ "VALUES('36','Telangana','S','Y')";
						stmt.executeQuery(q);
						
						conn.commit();
					}
				}
			} catch(Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		}
	//RS 20052017
		public void checkColumn() throws SQLException
		{
			try
			{
				String query = "SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) = 'FMS7_CUSTOMER_PLANT_DTL' "
						+ "AND UPPER(COLUMN_NAME) = 'PLANT_SHORT_ABBR'";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					int count = rset.getInt(1);
					if(count==0)
					{
						query = "ALTER TABLE FMS7_CUSTOMER_PLANT_DTL ADD PLANT_SHORT_ABBR VARCHAR(4)";
						stmt.executeUpdate(query);
						
						query = "ALTER TABLE FMS7_SUPPLIER_PLANT_DTL ADD PLANT_SHORT_ABBR VARCHAR(4)";
						stmt.executeUpdate(query);
						
						query = "ALTER TABLE FMS7_TRADER_PLANT_DTL ADD PLANT_SHORT_ABBR VARCHAR(4)";
						stmt.executeUpdate(query);
						
						query = "ALTER TABLE FMS7_TRANSPORTER_PLANT_DTL ADD PLANT_SHORT_ABBR VARCHAR(4)";
						stmt.executeUpdate(query);
					}
				}
				
				//ADDED FOR GST
				query = "SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) = 'FMS7_SUPPLIER_MST' "
						+ "AND UPPER(COLUMN_NAME) = 'GSTIN_NO'";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					int count = rset.getInt(1);
					if(count==0)
					{
						query = "ALTER TABLE FMS7_SUPPLIER_MST ADD GSTIN_NO VARCHAR(15)";
						stmt.executeUpdate(query);
						
						query = "ALTER TABLE FMS7_SUPPLIER_MST ADD GSTIN_DT DATE";
						stmt.executeUpdate(query);
						
						query = "ALTER TABLE FMS7_TRANSPORTER_MST ADD GSTIN_NO VARCHAR(15)";
						stmt.executeUpdate(query);
						
						query = "ALTER TABLE FMS7_TRANSPORTER_MST ADD GSTIN_DT DATE";
						stmt.executeUpdate(query);
						
						query = "ALTER TABLE FMS7_TRADER_MST ADD GSTIN_NO VARCHAR(15)";
						stmt.executeUpdate(query);
						
						query = "ALTER TABLE FMS7_TRADER_MST ADD GSTIN_DT DATE";
						stmt.executeUpdate(query);
					}
				}
				conn.commit();	
			}
			catch(Exception e)
			{
				conn.rollback();
				e.printStackTrace();
			}
		}
	
	public void Fetch_All_Entity_Detail_all_List() {
		try
		{
//				 System.out.println("set_emp_id...."+set_emp_id);
			if(!set_emp_id.equals("")) { 
				 if(set_group_id.equals(""))
				 {
					 queryString = "SELECT DISTINCT A.group_cd, B.group_name " +
								  "FROM SEC_EMP_GROUP_DTL A, SEC_ACCESS_GROUP_MST B " +
								  "WHERE A.group_cd=B.group_cd AND " +
								  "A.emp_cd IS NOT NULL AND A.emp_cd='"+set_emp_id+"' ";
					//System.out.println("................."+queryString);
					rset=stmt.executeQuery(queryString);
					while(rset.next())
					{
						set_group_id = rset.getString(1)==null?"0":rset.getString(1);
					}
				 }	
				
				//System.out.println("setSet_group_id...."+set_group_id);
			
				 queryString = "Select distinct(a.CUSTOMER_CD), a.CUSTOMER_NAME, a.CUSTOMER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T')  " +
				 		" from FMS7_CUSTOMER_MST a " +
				 		" where a.eff_dt = (select max(b.Eff_dt) from FMS7_CUSTOMER_MST b where a.customer_cd=b.customer_cd) " +
				 	//SB20130711	" ORDER BY a.CUSTOMER_CD ";	
				 		" ORDER BY A.CUSTOMER_NAME ";
				 //System.out.println("......!!!.........."+queryString);
				 rset = stmt.executeQuery(queryString);
				 int d = 0;
					while(rset.next())
					{
						d++;
						Entity_code.add(rset.getString(1)==null?"0":rset.getString(1));
						Entity_name.add(rset.getString(2)==null?"":rset.getString(2));
						Entity_abr.add(rset.getString(3)==null?"":rset.getString(3));
						Entity_flag.add(rset.getString(5)); //SB20130711
					}	
					Entity_tot_size.add(d+"");
					
					//System.out.println("-----------1--------"+Entity_name.size());

				queryString = "Select distinct(a.SUPPLIER_CD), a.SUPPLIER_NAME, a.SUPPLIER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_SUPPLIER_MST  a " +
						" where a.eff_dt = (select max(b.eff_Dt) from FMS7_SUPPLIER_MST b where a.supplier_cd=b.supplier_cd) " +
						//SB20130711 " ORDER BY a.SUPPLIER_CD";	
						" ORDER BY a.SUPPLIER_NAME";
				 rset = stmt.executeQuery(queryString);
				 int e = 0;
					while(rset.next())
					{
						e++;
						Entity_code.add(rset.getString(1)==null?"0":rset.getString(1));
						Entity_name.add(rset.getString(2)==null?"":rset.getString(2));
						Entity_abr.add(rset.getString(3)==null?"":rset.getString(3));
						Entity_flag.add(rset.getString(5)); //SB20130711
					}	
					Entity_tot_size.add(e+"");
					
					//System.out.println("-----------2--------"+Entity_name.size());

				queryString = "Select distinct(a.TRANSPORTER_CD), a.TRANSPORTER_NAME, a.TRANSPORTER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_TRANSPORTER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_TRANSPORTER_MST b where a.TRANSPORTER_CD=b.TRANSPORTER_CD) " +
						//SB20130711 "ORDER BY a.TRANSPORTER_CD";	
						"ORDER BY a.TRANSPORTER_NAME";
				rset = stmt.executeQuery(queryString);
				int f = 0;
				while(rset.next())
				{
					f++;
					Entity_code.add(rset.getString(1)==null?"0":rset.getString(1));
					Entity_name.add(rset.getString(2)==null?"":rset.getString(2));
					Entity_abr.add(rset.getString(3)==null?"":rset.getString(3));
					Entity_flag.add(rset.getString(5)); //SB20130711
				}	
				Entity_tot_size.add(f+"");
				
				//System.out.println("-----------2--------"+Entity_name.size());
			
				queryString = "Select distinct(a.TRADER_CD), a.TRADER_NAME, a.TRADER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_TRADER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_TRADER_MST b where a.TRADER_CD=b.TRADER_CD) " +
				//SB20130711 " ORDER BY a.TRADER_CD";
				" ORDER BY a.TRADER_NAME ";
				int g = 0;
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					g++;
					Entity_code.add(rset.getString(1)==null?"0":rset.getString(1));
					Entity_name.add(rset.getString(2)==null?"":rset.getString(2));
					Entity_abr.add(rset.getString(3)==null?"":rset.getString(3));
					Entity_flag.add(rset.getString(5)); //SB20130711
				}	
				Entity_tot_size.add(g+"");
				
				//System.out.println("-----------2--------"+Entity_name.size());

				queryString = "Select distinct(a.OTHER_CD), a.OTHER_NAME, a.OTHER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_OTHER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_OTHER_MST b where a.OTHER_CD=b.OTHER_CD) " +
				//SB20130711 " ORDER BY a.OTHER_CD";
				" ORDER BY a.OTHER_NAME";
				int h = 0;
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					h++;
					Entity_code.add(rset.getString(1)==null?"0":rset.getString(1));
					Entity_name.add(rset.getString(2)==null?"":rset.getString(2));
					Entity_abr.add(rset.getString(3)==null?"":rset.getString(3));
					Entity_flag.add(rset.getString(5)); //SB20130711
				}	
				Entity_tot_size.add(h+"");
				
				//System.out.println("-----------2--------"+Entity_name.size());
				
				queryString = "select PARTY_CD,PARTY_TYP from SEC_EMP_PARTY_ALLOC_MST where " +
						      " EMP_CD = '"+set_emp_id+"' ";
//						      + "AND GROUP_CD = '"+set_group_id+"' ";
				//System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					//System.out.println(rset.getString(1));
					//System.out.println(rset.getString(2));
					l1.add(rset.getString(2)+rset.getString(1));
				}
//				System.out.println("list......"+l1);
				 tot_size = Entity_code.size();
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Fetch_All_Entity_Detail_List()-->"+e);
			e.printStackTrace();
		}
	}


	public void Fetch_Plant_Tax_Information()
	{
		try
		{
			queryString = "SELECT gst_tin_no, TO_CHAR(gst_tin_dt,'DD/MM/YYYY'), cst_tin_no, " +
						  "TO_CHAR(cst_tin_dt,'DD/MM/YYYY'), pan_no, TO_CHAR(pan_issue_dt,'DD/MM/YYYY'), " +
						  "tan_no, TO_CHAR(tan_issue_dt,'DD/MM/YYYY'), service_tax_no, " +
						  "TO_CHAR(service_tax_dt,'DD/MM/YYYY'),GVAT_TIN_NO, " +
						  "TO_CHAR(GVAT_TIN_DT,'DD/MM/YYYY') FROM FMS7_CUSTOMER_PLANT_TAX_NOS " +
						  "WHERE customer_cd="+Entity_Id+" AND plant_seq_no="+plant_seq_no+"";
			
			//System.out.println("Select Query For FMS7_CUSTOMER_PLANT_TAX_NOS Table = "+queryString);
			rset = stmt.executeQuery(queryString);
			
			if(rset.next())
			{
				//System.out.println("JAVA if");
				gst_tin_no = rset.getString(1)==null?"":rset.getString(1);
				gst_tin_dt = rset.getString(2)==null?"":rset.getString(2);
				cst_tin_no = rset.getString(3)==null?"":rset.getString(3);
				cst_tin_dt = rset.getString(4)==null?"":rset.getString(4);
				pan_no = rset.getString(5)==null?"":rset.getString(5);
				pan_issue_dt = rset.getString(6)==null?"":rset.getString(6);
				tan_no = rset.getString(7)==null?"":rset.getString(7);
				tan_issue_dt = rset.getString(8)==null?"":rset.getString(8);
				service_tax_no = rset.getString(9)==null?"":rset.getString(9);
				service_tax_dt = rset.getString(10)==null?"":rset.getString(10);
				gvat_tin_no = rset.getString(11)==null?"":rset.getString(11);
				gvat_tin_dt = rset.getString(12)==null?"":rset.getString(12);				
			}
			//System.out.println("JAVA cst_tin_no: "+cst_tin_no);
			//System.out.println("JAVA gvat_tin_no: "+gvat_tin_no);
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity Servlet ---> Under Fetch_Plant_Tax_Information() Method -->\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetchGovtStatNoList()
	{
		try 
		{
			queryString = "SELECT NVL(STAT_CD,'0'),STAT_NM,NVL(STAT_TYPE,'S') " +
						  "FROM FMS7_GOVT_STAT_NO WHERE FLAG='Y' " +
						  "ORDER BY STAT_TYPE DESC,STAT_CD";
			
			//System.out.println("Bank Master Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				vSTAT_CD.add(rset.getString(1));
				vSTAT_NM.add(rset.getString(2)==null?"":rset.getString(2));
				
				String stat_type = rset.getString(3);
				
				if(stat_type.trim().equals("S"))
				{
					stat_type = "Sales Invoice";
				}
				else if(stat_type.trim().equals("R"))
				{
					stat_type = "Re-Gas Invoice";
				}
				else if(stat_type.trim().equals("G"))
				{
					stat_type = "General Purpose ID";
				}
				
				vSTAT_TYPE.add(stat_type);
				
				queryString1 = "SELECT STAT_NO, TO_CHAR(EFF_DT,'dd/mm/yyyy'), REMARK " +
							   "FROM FMS7_CUSTOMER_PLANT_TAX_CDS " +
							   "WHERE customer_cd="+Entity_Id+" AND " +
							   "plant_seq_no="+plant_seq_no+" AND stat_cd="+rset.getString(1)+"";
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					vSTAT_NO.add(rset1.getString(1)==null?"":rset1.getString(1));
					vSTAT_EFF_DT.add(rset1.getString(2)==null?"":rset1.getString(2));
					vSTAT_REMARKS.add(rset1.getString(3)==null?"":rset1.getString(3));
				}
				else
				{
					vSTAT_NO.add("");
					vSTAT_EFF_DT.add("");
					vSTAT_REMARKS.add("");
				}
			}
		}
		catch (Exception e)
		{
			//System.out.println("EXCEPTION In DataBean_Entity, Method Name --> fetchGovtStatNoList():\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void Fetch_Cargo_Tax_Master_Detail()
	{
		try
		{
			queryString = "SELECT A.TAX_STR_CD,TO_CHAR(A.APP_DATE,'DD/MM/YYYY'),A.TAX_DESC,A.REMARK FROM FMS7_CARGO_TAX_MST A " +
						  "WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B)";
			//System.out.println("Cargo Tax Master Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				cargo_tax_str_cd = rset.getString(1)==null?"":rset.getString(1);
				cargo_tax_app_dt = rset.getString(2)==null?"":rset.getString(2);
				cargo_tax_desc = rset.getString(3)==null?"":rset.getString(3);
				cargo_tax_remark = rset.getString(4)==null?"":rset.getString(4);
			}
			
			queryString = "SELECT TO_CHAR(MAX(EFF_DT),'DD/MM/YYYY') FROM FMS7_LADING_CHARGE_MST";
			//System.out.println("Lading Charges Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				app_date = rset.getString(1)==null?"":rset.getString(1);
				
				queryString1 = "SELECT TO_CHAR(EFF_DT,'DD/MM/YYYY'),CIF_PERCENT,REMARKS FROM FMS7_LADING_CHARGE_MST  " +
				  			   "WHERE EFF_DT=TO_DATE('"+app_date+"','dd/mm/yyyy')";
				//System.out.println("Lading Charges Detail Query = "+queryString);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					cif_percent = rset1.getString(2)==null?"":rset1.getString(2);
					remarks = rset1.getString(3)==null?"":rset1.getString(3);
				}
			}
			
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity Servlet ---> Under Fetch_Cargo_Tax_Master_Detail() Method -->\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetch_other_entity_details()
	{
		try
		{
			if(EntityFlag.equalsIgnoreCase("other"))
			{
				fetch_other_basic_data();
				fetch_other_address_data();
				fetch_other_contact_data();
			} 
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->fetch_other_entity_details()-->"+e);
			e.printStackTrace();
		}
	}
	
	public void fetch_other_basic_data(){
		try{
			queryString = "SELECT to_char(EFF_DT,'dd/mm/yyyy'), OTHER_NAME, OTHER_ABBR,  " +
					" WEB_ADDR, NOTES FROM FMS7_OTHER_MST " +
					" WHERE OTHER_CD = '"+Entity_Id+"' and eff_dt = (select max(eff_Dt) from FMS7_OTHER_MST where OTHER_CD = '"+Entity_Id+"' and flag = 'T') and FLAG = 'T' ";
			rset = stmt.executeQuery(queryString);
		    if(rset.next()){
		    	ENTITY_EFF_DT = rset.getString(1)==null?"":rset.getString(1);
		    	ENTITY_NAME = rset.getString(2)==null?"":rset.getString(2);
		    	ENTITY_ABBR= rset.getString(3)==null?"":rset.getString(3);
		    	WEB_ADDR= rset.getString(4)==null?"":rset.getString(4);
		    	NOTES = rset.getString(5)==null?"":rset.getString(5);
		    }else{
		    	ENTITY_EFF_DT = "";
		    	ENTITY_NAME = "";
		    	ENTITY_ABBR= "";
		    	WEB_ADDR= "";
		    	NOTES = "";
		    }
		    
		     
		}catch(Exception e){
			//System.out.println("Exception in Databean_Entity--->fetch_other_basic_data()-->"+e);
			e.printStackTrace();
		}
	}
	
	public void fetch_other_address_data(){
		try{
			 queryString = " SELECT ADDRESS_TYPE, to_char(EFF_DT,'dd/mm/yyyy'), ADDR, CITY, PIN, STATE," +
	    		" ZONE, COUNTRY, PHONE, MOBILE, ALT_PHONE, FAX_1, FAX_2, EMAIL FROM FMS7_OTHER_ADDRESS_MST" +
	    		" where OTHER_CD = '"+Entity_Id+"' and eff_dt = (select max(eff_Dt) from FMS7_OTHER_ADDRESS_MST where OTHER_CD = '"+Entity_Id+"' and flag = 'T') and FLAG = 'T' ";
			 rset = stmt.executeQuery(queryString);
			    
			 if(rset.next()){
				String ADDRESS_TYPE = rset.getString(1)==null?"":rset.getString(1);
				EFF_DT[0] = rset.getString(2)==null?"":rset.getString(2);
				ADDR[0]= rset.getString(3)==null?"":rset.getString(3);
				CITY[0]= rset.getString(4)==null?"":rset.getString(4);
				PIN[0]= rset.getString(5)==null?"":rset.getString(5);
				STATE[0]= rset.getString(6)==null?"":rset.getString(6);
				ZONE[0]= rset.getString(7)==null?"":rset.getString(7);
				COUNTRY[0]= rset.getString(8)==null?"":rset.getString(8);
				PHONE[0]= rset.getString(9)==null?"":rset.getString(9);
				MOBILE[0]= rset.getString(10)==null?"":rset.getString(10);
				ALT_PHONE[0]= rset.getString(11)==null?"":rset.getString(11);
				FAX_1[0] = rset.getString(12)==null?"":rset.getString(12);
				FAX_2[0]= rset.getString(13)==null?"":rset.getString(13);
				EMAIL[0] = rset.getString(14)==null?"":rset.getString(14);
			 }else{
				 String ADDRESS_TYPE ="";
					EFF_DT[0] = "";
					ADDR[0]= "";
					CITY[0]= "";
					PIN[0]= "";
					STATE[0]= "";
					ZONE[0]= "";
					COUNTRY[0]= "";
					PHONE[0]= "";
					MOBILE[0]= "";
					ALT_PHONE[0]= "";
					FAX_1[0] = "";
					FAX_2[0]= "";
					EMAIL[0] = "";
			 }
		}catch(Exception e){
			//System.out.println("Exception in Databean_Entity--->fetch_other_address_data()-->"+e);
			e.printStackTrace();
		}
	}
	
	public void fetch_other_contact_data(){
		try{
			 queryString = " SELECT to_char(EFF_DT,'dd/mm/yyyy'), CONTACT_PERSON, CONTACT_DESIG, PHONE, MOBILE, FAX_1, FAX_2, EMAIL_1, EMAIL_2, CONTACT_DEPT " +
	    		" FROM FMS7_OTHER_CONTACT_MST " +
	    		" where OTHER_CD = '"+Entity_Id+"' and  contact_type='A' and eff_dt = (select max(eff_Dt) from FMS7_OTHER_CONTACT_MST where OTHER_CD = '"+Entity_Id+"' and  contact_type='A'  and flag = 'T') and FLAG = 'T' ";
			 rset = stmt.executeQuery(queryString);
			 if(rset.next()){
				 CONT_EFF_DT[0] = rset.getString(1)==null?"":rset.getString(1);
				 CONT_CONTACT_PERSON[0] = rset.getString(2)==null?"":rset.getString(2);
				 CONT_CONTACT_DESIG[0]= rset.getString(3)==null?"":rset.getString(3);
				 CONT_PHONE[0]= rset.getString(4)==null?"":rset.getString(4);
				 CONT_MOBILE[0]= rset.getString(5)==null?"":rset.getString(5);
				 CONT_FAX_1[0]= rset.getString(6)==null?"":rset.getString(6);
				 CONT_FAX_2[0]= rset.getString(7)==null?"":rset.getString(7);
				 EMAIL_1[0]= rset.getString(8)==null?"":rset.getString(8);
				 EMAIL_2[0]= rset.getString(9)==null?"":rset.getString(9);
				 CONT_CONTACT_DEPT[0]= rset.getString(10)==null?"":rset.getString(10);
			 }else{
				 CONT_EFF_DT[0] ="";
				 CONT_CONTACT_PERSON[0] = "";
				 CONT_CONTACT_DESIG[0]= "";
				 CONT_PHONE[0]= "";
				 CONT_MOBILE[0]= "";
				 CONT_FAX_1[0]= "";
				 CONT_FAX_2[0]= "";
				 EMAIL_1[0]= "";
				 EMAIL_2[0]= "";
				 CONT_CONTACT_DEPT[0]= "";
			 }
			 
			 queryString = " SELECT to_char(EFF_DT,'dd/mm/yyyy'), CONTACT_PERSON, CONTACT_DESIG, PHONE, MOBILE, FAX_1, FAX_2, EMAIL_1, EMAIL_2, CONTACT_DEPT " +
	    		" FROM FMS7_OTHER_CONTACT_MST " +
	    		" where OTHER_CD = '"+Entity_Id+"' and  contact_type='B' and eff_dt = (select max(eff_Dt) from FMS7_OTHER_CONTACT_MST where OTHER_CD = '"+Entity_Id+"' and  contact_type='B'  and flag = 'T') and FLAG = 'T' ";
			 rset = stmt.executeQuery(queryString);
			 if(rset.next()){
				 CONT_EFF_DT[1] = rset.getString(1)==null?"":rset.getString(1);
				 CONT_CONTACT_PERSON[1] = rset.getString(2)==null?"":rset.getString(2);
				 CONT_CONTACT_DESIG[1]= rset.getString(3)==null?"":rset.getString(3);
				 CONT_PHONE[1]= rset.getString(4)==null?"":rset.getString(4);
				 CONT_MOBILE[1]= rset.getString(5)==null?"":rset.getString(5);
				 CONT_FAX_1[1]= rset.getString(6)==null?"":rset.getString(6);
				 CONT_FAX_2[1]= rset.getString(7)==null?"":rset.getString(7);
				 EMAIL_1[1]= rset.getString(8)==null?"":rset.getString(8);
				 EMAIL_2[1]= rset.getString(9)==null?"":rset.getString(9);
				 CONT_CONTACT_DEPT[1]= rset.getString(10)==null?"":rset.getString(10);
			 }else{
				 CONT_EFF_DT[1] ="";
				 CONT_CONTACT_PERSON[1] = "";
				 CONT_CONTACT_DESIG[1]= "";
				 CONT_PHONE[1]= "";
				 CONT_MOBILE[1]= "";
				 CONT_FAX_1[1]= "";
				 CONT_FAX_2[1]= "";
				 EMAIL_1[1]= "";
				 EMAIL_2[1]= "";
				 CONT_CONTACT_DEPT[1]= "";
			 }	
			 
			 queryString = " SELECT to_char(EFF_DT,'dd/mm/yyyy'), CONTACT_PERSON, CONTACT_DESIG, PHONE, MOBILE, FAX_1, FAX_2, EMAIL_1, EMAIL_2, CONTACT_DEPT " +
	    		" FROM FMS7_OTHER_CONTACT_MST " +
	    		" where OTHER_CD = '"+Entity_Id+"' and  contact_type='C' and eff_dt = (select max(eff_Dt) from FMS7_OTHER_CONTACT_MST where OTHER_CD = '"+Entity_Id+"' and  contact_type='C'  and flag = 'T') and FLAG = 'T' ";
			 rset = stmt.executeQuery(queryString);
			 if(rset.next()){
				 CONT_EFF_DT[2] = rset.getString(1)==null?"":rset.getString(1);
				 CONT_CONTACT_PERSON[2] = rset.getString(2)==null?"":rset.getString(2);
				 CONT_CONTACT_DESIG[2]= rset.getString(3)==null?"":rset.getString(3);
				 CONT_PHONE[2]= rset.getString(4)==null?"":rset.getString(4);
				 CONT_MOBILE[2]= rset.getString(5)==null?"":rset.getString(5);
				 CONT_FAX_1[2]= rset.getString(6)==null?"":rset.getString(6);
				 CONT_FAX_2[2]= rset.getString(7)==null?"":rset.getString(7);
				 EMAIL_1[2]= rset.getString(8)==null?"":rset.getString(8);
				 EMAIL_2[2]= rset.getString(9)==null?"":rset.getString(9);
				 CONT_CONTACT_DEPT[2]= rset.getString(10)==null?"":rset.getString(10);
			 }else{
				 CONT_EFF_DT[2] ="";
				 CONT_CONTACT_PERSON[2] = "";
				 CONT_CONTACT_DESIG[2]= "";
				 CONT_PHONE[2]= "";
				 CONT_MOBILE[2]= "";
				 CONT_FAX_1[2]= "";
				 CONT_FAX_2[2]= "";
				 EMAIL_1[2]= "";
				 EMAIL_2[2]= "";
				 CONT_CONTACT_DEPT[2]= "";
			 }
		}catch(Exception e){
			//System.out.println("Exception in Databean_Entity--->fetch_other_contact_data()-->"+e);
			e.printStackTrace();
		}
	}
	
	
	public void get_Transporter_List_fromMeter()
	{
		try{
			queryString = "select distinct(a.TRANS_CUST_CD) , b.TRANSPORTER_NAME,  b.TRANSPORTER_ABBR " +
					" from fms7_meter_mst a , fms7_transporter_mst b where a.trans_cust_cd = b.transporter_cd " +
					" and a.meter_type = 'T'";
			rset = stmt.executeQuery(queryString);
			while(rset.next()){
				 Entity_code.add(rset.getString(1)==null?"":rset.getString(1));  
				 Entity_name.add(rset.getString(2)==null?"":rset.getString(2));  
				 Entity_abr.add(rset.getString(3)==null?"":rset.getString(3));  
			}
		}catch(Exception e){
			//System.out.println("Exception in Databean_Entity--->get_Transporter_List_fromMeter()-->"+e);
			e.printStackTrace();
		}
	}
	
	public void getExisitingMeterDetails(){
		try{
			String meter_type ="";
			if(EntityFlag.equalsIgnoreCase("cus")){
				meter_type = "C";
				
				queryString = "select  " +
				"  SPECIFICATION, a.TRANSPORTER_CD, NOTE, b.TRANSPORTER_ABBR " +
				"  from FMS7_METER_MST  a , FMS7_TRANSPORTER_MST b where METER_TYPE ='"+meter_type+"' " +
				"  and a.FLAG = 'T' and TRANS_CUST_CD='"+Entity_Id+"' and  METER_SEQ_NO='"+meterSeqNo+"' " +
						" AND a.transporter_cd = b.transporter_cd ";
				rset = stmt.executeQuery(queryString);
				if(rset.next()){
					
					     meter_speci =rset.getString(1)==null?"":rset.getString(1);
					     trans_cd = rset.getString(2)==null?"":rset.getString(2);
					     meter_note = rset.getString(3)==null?"":rset.getString(3);
					     trans_abr = rset.getString(4)==null?"":rset.getString(4);
				}
				
			}else if(EntityFlag.equalsIgnoreCase("tran")){
				meter_type = "T";
				
				queryString = "select  " +
				"  SPECIFICATION, TRANSPORTER_CD, NOTE  " +
				"  from FMS7_METER_MST  where METER_TYPE ='"+meter_type+"' " +
				"  and FLAG = 'T' and TRANS_CUST_CD='"+Entity_Id+"' and  METER_SEQ_NO='"+meterSeqNo+"' " ;
				
				rset = stmt.executeQuery(queryString);
				if(rset.next()){
					     meter_speci =rset.getString(1)==null?"":rset.getString(1);
					     trans_cd = rset.getString(2)==null?"":rset.getString(2);
					     meter_note = rset.getString(3)==null?"":rset.getString(3);
				}
			}
		}catch(Exception e){
			//System.out.println("Exception in Databean_Entity--->getExisitingMeterDetails()-->"+e);
			e.printStackTrace();
		}
	}
	

	public void Fetch_Cust_Trans_Meter_Details()
	{
		try
		{
			String meter_type ="";
			if(EntityFlag.equalsIgnoreCase("cus"))
			{
				meter_type = "C";
			}
			else if(EntityFlag.equalsIgnoreCase("tran"))
			{
				meter_type = "T";
			}
			queryString = "select TRANS_CUST_CD, METER_SEQ_NO,  METER_ID, " +
			"  SPECIFICATION, TRANSPORTER_CD, NOTE " +
			"  from FMS7_METER_MST where METER_TYPE ='"+meter_type+"' " +
			"  and FLAG = 'T' order by TRANS_CUST_CD, METER_SEQ_NO";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				TRANS_CUST_CD.add(rset.getString(1)==null?"":rset.getString(1));  
				METER_SEQ_NO.add(rset.getString(2)==null?"":rset.getString(2));  
				METER_ID.add(rset.getString(3)==null?"":rset.getString(3));  
				SPECIFICATION.add(rset.getString(4)==null?"":rset.getString(4));  
				TRANSPORTER_CD.add(rset.getString(5)==null?"":rset.getString(5));  
				NOTE.add(rset.getString(6)==null?"":rset.getString(6));
			     
			    if(meter_type.equals("T"))
			    {
			    	queryString1 = "SELECT transporter_name, transporter_abbr FROM FMS7_TRANSPORTER_MST WHERE transporter_cd="+rset.getString(1)+"";
			    	rset1 = stmt1.executeQuery(queryString1);
			    	if(rset1.next())
			    	{
			    		TRANS_CUST_NAME.add(rset1.getString(1)==null?"":rset1.getString(1));
			    		TRANS_CUST_ABBR.add(rset1.getString(2)==null?"":rset1.getString(2));
			    	}
			    	else
			    	{
			    		TRANS_CUST_NAME.add("");
			    		TRANS_CUST_ABBR.add("");
			    	}
			    }
			    else
			    {
			    	queryString1 = "SELECT customer_name, customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+rset.getString(1)+"";
			    	rset1 = stmt1.executeQuery(queryString1);
			    	if(rset1.next())
			    	{
			    		TRANS_CUST_NAME.add(rset1.getString(1)==null?"":rset1.getString(1));
			    		TRANS_CUST_ABBR.add(rset1.getString(2)==null?"":rset1.getString(2));
			    	}
			    	else
			    	{
			    		TRANS_CUST_NAME.add("");
			    		TRANS_CUST_ABBR.add("");
			    	}
			    }
			}		
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Fetch_Cust_Trans_Meter_Details()-->"+e);
			e.printStackTrace();
		}
	}
	
public void getMeterSequenceNo()
{
	try{
		String meter_type ="";
		if(EntityFlag.equalsIgnoreCase("cus")){
			meter_type = "C";
		}else if(EntityFlag.equalsIgnoreCase("tran")){
			meter_type = "T";
		}
		queryString = "select max(meter_seq_no) from fms7_meter_mst where TRANS_CUST_CD = '"+Entity_Id+"'" +
		    	" and METER_TYPE = '"+meter_type+"'";
		rset = stmt.executeQuery(queryString);
		
		if(rset.next()){
		   	int temp = Integer.parseInt(rset.getString(1)==null?"0":rset.getString(1));
		   	new_meter_seq = temp + 1 ;
		}else{
		     new_meter_seq = 1;	
		}	
	}catch(Exception e){
		//System.out.println("Exception in Databean_Entity--->getMeterSequenceNo()-->"+e);
		e.printStackTrace();
	}
}

public void Fetch_Entity_Gifts_Details()
{
	try{
		if(EntityFlag.equalsIgnoreCase("cus")){
		for(int i=0;i<CSEQ_NO.size();i++){
		 queryString = "SELECT  CALENDAR_FLAG, GIFT_FLAG, DIARY_FLAG, LEAFLET_FLAG," +
		 		" OTHER_1, OTHER_2, DESCRIPTION FROM FMS7_CUSTOMER_GIFT_TRACK_DTL" +
		 		" WHERE CUSTOMER_CD = '"+Entity_Id+"' AND MONTH ='"+month+"'  AND YEAR = '"+year+"' AND SEQ_NO = '"+CSEQ_NO.elementAt(i)+"'";
         rset = stmt.executeQuery(queryString);  		
		 if(rset.next()){ 
	          CALENDAR_FLAG.add(rset.getString(1)==null?"":rset.getString(1));  
		      GIFT_FLAG.add(rset.getString(2)==null?"":rset.getString(2)); 
		      DIARY_FLAG.add(rset.getString(3)==null?"":rset.getString(3));
		      LEAFLET_FLAG.add(rset.getString(4)==null?"":rset.getString(4));  
		      OTHER_1.add(rset.getString(5)==null?"":rset.getString(5)); 
		      OTHER_2.add(rset.getString(6)==null?"":rset.getString(6)); 
		      DESCRIPTION.add(rset.getString(7)==null?"":rset.getString(7)); 
		 }else{
			  CALENDAR_FLAG.add("N");  
		      GIFT_FLAG.add("N"); 
		      DIARY_FLAG.add("N");
		      LEAFLET_FLAG.add("N");  
		      OTHER_1.add("N"); 
		      OTHER_2.add("N"); 
		      DESCRIPTION.add(""); 
		  }
		 }
		}else if(EntityFlag.equalsIgnoreCase("tran")){
			for(int i=0;i<CSEQ_NO.size();i++){
				 queryString = "SELECT  CALENDAR_FLAG, GIFT_FLAG, DIARY_FLAG, LEAFLET_FLAG," +
				 		" OTHER_1, OTHER_2, DESCRIPTION FROM FMS7_TRANS_GIFT_TRACK_DTL" +
				 		" WHERE TRANSPORTER_CD = '"+Entity_Id+"' AND MONTH ='"+month+"'  AND YEAR = '"+year+"' AND SEQ_NO = '"+CSEQ_NO.elementAt(i)+"'";
		         rset = stmt.executeQuery(queryString);  		
				 if(rset.next()){ 
			          CALENDAR_FLAG.add(rset.getString(1)==null?"":rset.getString(1));  
				      GIFT_FLAG.add(rset.getString(2)==null?"":rset.getString(2)); 
				      DIARY_FLAG.add(rset.getString(3)==null?"":rset.getString(3));
				      LEAFLET_FLAG.add(rset.getString(4)==null?"":rset.getString(4));  
				      OTHER_1.add(rset.getString(5)==null?"":rset.getString(5)); 
				      OTHER_2.add(rset.getString(6)==null?"":rset.getString(6)); 
				      DESCRIPTION.add(rset.getString(7)==null?"":rset.getString(7)); 
				 }else{
					  CALENDAR_FLAG.add("N");  
				      GIFT_FLAG.add("N"); 
				      DIARY_FLAG.add("N");
				      LEAFLET_FLAG.add("N");  
				      OTHER_1.add("N"); 
				      OTHER_2.add("N"); 
				      DESCRIPTION.add(""); 
				  }
				 }
		}else if(EntityFlag.equalsIgnoreCase("trd")){
			for(int i=0;i<CSEQ_NO.size();i++){
				 queryString = "SELECT  CALENDAR_FLAG, GIFT_FLAG, DIARY_FLAG, LEAFLET_FLAG," +
				 		" OTHER_1, OTHER_2, DESCRIPTION FROM FMS7_TRADER_GIFT_TRACK_DTL" +
				 		" WHERE TRADER_CD = '"+Entity_Id+"' AND MONTH ='"+month+"'  AND YEAR = '"+year+"' AND SEQ_NO = '"+CSEQ_NO.elementAt(i)+"'";
		         rset = stmt.executeQuery(queryString);  		
				 if(rset.next()){ 
			          CALENDAR_FLAG.add(rset.getString(1)==null?"":rset.getString(1));  
				      GIFT_FLAG.add(rset.getString(2)==null?"":rset.getString(2)); 
				      DIARY_FLAG.add(rset.getString(3)==null?"":rset.getString(3));
				      LEAFLET_FLAG.add(rset.getString(4)==null?"":rset.getString(4));  
				      OTHER_1.add(rset.getString(5)==null?"":rset.getString(5)); 
				      OTHER_2.add(rset.getString(6)==null?"":rset.getString(6)); 
				      DESCRIPTION.add(rset.getString(7)==null?"":rset.getString(7)); 
				 }else{
					  CALENDAR_FLAG.add("N");  
				      GIFT_FLAG.add("N"); 
				      DIARY_FLAG.add("N");
				      LEAFLET_FLAG.add("N");  
				      OTHER_1.add("N"); 
				      OTHER_2.add("N"); 
				      DESCRIPTION.add(""); 
				  }
				 }
		}else if(EntityFlag.equalsIgnoreCase("other")){
			for(int i=0;i<CSEQ_NO.size();i++){
				 queryString = "SELECT  CALENDAR_FLAG, GIFT_FLAG, DIARY_FLAG, LEAFLET_FLAG," +
				 		" OTHER_1, OTHER_2, DESCRIPTION FROM FMS7_OTHER_GIFT_TRACK_DTL" +
				 		" WHERE OTHER_CD = '"+Entity_Id+"' AND MONTH ='"+month+"'  AND YEAR = '"+year+"' AND SEQ_NO = '"+CSEQ_NO.elementAt(i)+"' AND CONTACT_TYPE='"+CONTACT_TYPE.elementAt(i)+"'";
		         rset = stmt.executeQuery(queryString);  		
				 if(rset.next()){ 
			          CALENDAR_FLAG.add(rset.getString(1)==null?"":rset.getString(1));  
				      GIFT_FLAG.add(rset.getString(2)==null?"":rset.getString(2)); 
				      DIARY_FLAG.add(rset.getString(3)==null?"":rset.getString(3));
				      LEAFLET_FLAG.add(rset.getString(4)==null?"":rset.getString(4));  
				      OTHER_1.add(rset.getString(5)==null?"":rset.getString(5)); 
				      OTHER_2.add(rset.getString(6)==null?"":rset.getString(6)); 
				      DESCRIPTION.add(rset.getString(7)==null?"":rset.getString(7)); 
				 }else{
					  CALENDAR_FLAG.add("N");  
				      GIFT_FLAG.add("N"); 
				      DIARY_FLAG.add("N");
				      LEAFLET_FLAG.add("N");  
				      OTHER_1.add("N"); 
				      OTHER_2.add("N"); 
				      DESCRIPTION.add(""); 
				  }
				 }
		}
	}catch(Exception e){
		    //System.out.println("Exception in Databean_Entity--->Fetch_Entity_Gifts_Details()-->"+e);
			e.printStackTrace();
	}
}
	
public void Fetch_Entity_Contact_Details()
{
  try
  {
	  Vector CODE = new Vector();
	  Vector DT  = new Vector();
	  	  
	  if(!Entity_Id.equalsIgnoreCase(""))
	  {
		  if(EntityFlag.equalsIgnoreCase("cus"))
		  {
			  queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from fms7_customer_contact_mst where " +
			   				"customer_cd='"+Entity_Id+"' group by seq_no";
			  
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
		  
			  for(int j=0;j<CODE.size();j++)
			  { 		  
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
				  				" CONTACT_PERSON,  PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG,CONTACT_DESIG ,JT_FLAG,DEF_NOM_FLAG," +
						  		" DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG " +
						  		" FROM FMS7_CUSTOMER_CONTACT_MST WHERE CUSTOMER_CD = '"+Entity_Id+"' " +
						  		" AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
			   	       CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
				       CONTACT_TYPE.add("");
				  } 
			  }
		  }
		  else if(EntityFlag.equalsIgnoreCase("sup"))
		  {
			  queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from fms7_supplier_contact_mst where " +
			 			    "supplier_cd='"+Entity_Id+"' group by seq_no";
			  
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
			  for(int j=0;j<CODE.size();j++)
			  { 		
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
						  		" CONTACT_PERSON, PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG, CONTACT_DESIG ,JT_FLAG,DEF_NOM_FLAG," +
						  		" DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG " +
						  		" FROM FMS7_SUPPLIER_CONTACT_MST WHERE SUPPLIER_CD = '"+Entity_Id+"' " +
						  		" AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
					   CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
				       CONTACT_TYPE.add("");
		          } 
	          }
		  }
		  else if(EntityFlag.equalsIgnoreCase("tran"))
		  {
			  queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from fms7_transporter_contact_mst where " +
			  				"transporter_cd='"+Entity_Id+"' group by seq_no";
			  
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
			  for(int j=0;j<CODE.size();j++)
			  { 		
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
						  		" CONTACT_PERSON, PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG,CONTACT_DESIG ,JT_FLAG,DEF_NOM_FLAG, " +
						  		"DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG " +
						  		" FROM FMS7_TRANSPORTER_CONTACT_MST WHERE TRANSPORTER_CD = '"+Entity_Id+"' " +
						  		" AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
			   	       CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
				       CONTACT_TYPE.add("");
		          } 
	          }
		  }
		  else if(EntityFlag.equalsIgnoreCase("trd"))
		  {
			  queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from fms7_trader_contact_mst where " +
			  				"trader_cd='"+Entity_Id+"' group by seq_no";
			  
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
			  for(int j=0;j<CODE.size();j++)
			  { 		
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
						  		" CONTACT_PERSON, PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG,CONTACT_DESIG ,JT_FLAG ,DEF_NOM_FLAG," +
						  		" DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG" +
						  		" FROM FMS7_TRADER_CONTACT_MST WHERE TRADER_CD = '"+Entity_Id+"' " +
						  		"AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
			   	       CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
				       CONTACT_TYPE.add("");
		          } 
	          } 
		  }
		  else if(EntityFlag.equalsIgnoreCase("other"))
		  {
			  queryString = "SELECT A.OTHER_CD, to_char(A.EFF_DT,'dd/mm/yyyy'), " +
			  				"A.CONTACT_PERSON,A.CONTACT_DESIG , A.CONTACT_DEPT,A.CONTACT_TYPE " +
			  				"FROM FMS7_OTHER_CONTACT_MST A WHERE A.OTHER_CD = '"+Entity_Id+"' " +
			  				"AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_OTHER_CONTACT_MST B WHERE " +
			  				"A.OTHER_CD=B.OTHER_CD AND A.CONTACT_TYPE=B.CONTACT_TYPE) order by a.contact_type";
			  //System.out.println(queryString);
			  rset = stmt.executeQuery(queryString);
			  int count = 0;
			  while(rset.next())
			  {
				  CSEQ_NO.add((++count)+""); 
				  CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	  CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	  CONTACT_DESIG.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	  CONTACT_TYPE.add(rset.getString(6)==null?"":rset.getString(6));
			  }
		  }
	  }
  }
  catch(Exception e)
  {
	  //System.out.println("Exception in Databean_Entity--->Fetch_Entity_Contact_Details()-->"+e);
	  e.printStackTrace();
  }
}
		
public void Fetch_Entity_Contact_Details2()
{
  try
  {
	  Vector CODE = new Vector();
	  Vector DT  = new Vector();
	  String validate_dt = "28/"+month+"/"+year;
	  
	  if(!Entity_Id.equalsIgnoreCase(""))
	  {
		  if(EntityFlag.equalsIgnoreCase("cus"))
		  {
			  //Following Query Is Commented By Samik Shah On 5th June, 2010 ...
			  //queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from fms7_customer_contact_mst where " +
			   				//"customer_cd='"+Entity_Id+"' group by seq_no";
			  
			  //Following Query Has Been Introduced By Samik Shah On 5th June, 2010 ...
			  queryString = "SELECT DISTINCT(A.seq_no), TO_CHAR(A.eff_dt,'DD/MM/YYYY') FROM " +
			  				"FMS7_CUSTOMER_CONTACT_MST A WHERE A.customer_cd="+Entity_Id+" AND " +
			  				"A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B WHERE " +
			  				"B.customer_cd=A.customer_cd AND B.seq_no=A.seq_no AND " +
			  				"B.eff_dt<=TO_DATE('"+validate_dt+"','DD/MM/YYYY')) ORDER BY A.seq_no";
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
		  
			  for(int j=0;j<CODE.size();j++)
			  { 		  
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
				  				" CONTACT_PERSON,  PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG,CONTACT_DESIG ,JT_FLAG,DEF_NOM_FLAG," +
						  		" DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG " +
						  		" FROM FMS7_CUSTOMER_CONTACT_MST WHERE CUSTOMER_CD = '"+Entity_Id+"' " +
						  		" AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
			   	       CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
				       CONTACT_TYPE.add("");
				  } 
			  }
		  }
		  else if(EntityFlag.equalsIgnoreCase("sup"))
		  {
			  //Following Query Is Commented By Samik Shah On 5th June, 2010 ...
			  //queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from fms7_supplier_contact_mst where " +
			 			   //"supplier_cd='"+Entity_Id+"' group by seq_no";
			  
			  //Following Query Has Been Introduced By Samik Shah On 5th June, 2010 ...
			  queryString = "SELECT DISTINCT(A.seq_no), TO_CHAR(A.eff_dt,'DD/MM/YYYY') FROM " +
							"FMS7_SUPPLIER_CONTACT_MST A WHERE A.supplier_cd="+Entity_Id+" AND " +
							"A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_CONTACT_MST B WHERE " +
							"B.supplier_cd=A.supplier_cd AND B.seq_no=A.seq_no AND " +
							"B.eff_dt<=TO_DATE('"+validate_dt+"','DD/MM/YYYY')) ORDER BY A.seq_no";
			 			  
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
			  for(int j=0;j<CODE.size();j++)
			  { 		
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
						  		" CONTACT_PERSON, PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG, CONTACT_DESIG ,JT_FLAG,DEF_NOM_FLAG," +
						  		" DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG " +
						  		" FROM FMS7_SUPPLIER_CONTACT_MST WHERE SUPPLIER_CD = '"+Entity_Id+"' " +
						  		" AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
					   CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
				       CONTACT_TYPE.add("");
		          } 
	          }
		  }
		  else if(EntityFlag.equalsIgnoreCase("tran"))
		  {
			  //Following Query Is Commented By Samik Shah On 5th June, 2010 ...
			  //queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from fms7_transporter_contact_mst where " +
			  				//"transporter_cd='"+Entity_Id+"' group by seq_no";
			  
			  //Following Query Has Been Introduced By Samik Shah On 5th June, 2010 ...
			  queryString = "SELECT DISTINCT(A.seq_no), TO_CHAR(A.eff_dt,'DD/MM/YYYY') FROM " +
							"FMS7_TRANSPORTER_CONTACT_MST A WHERE A.transporter_cd="+Entity_Id+" AND " +
							"A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_TRANSPORTER_CONTACT_MST B WHERE " +
							"B.transporter_cd=A.transporter_cd AND B.seq_no=A.seq_no AND " +
							"B.eff_dt<=TO_DATE('"+validate_dt+"','DD/MM/YYYY')) ORDER BY A.seq_no";
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
			  for(int j=0;j<CODE.size();j++)
			  { 		
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
						  		" CONTACT_PERSON, PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG,CONTACT_DESIG ,JT_FLAG,DEF_NOM_FLAG, " +
						  		"DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG " +
						  		" FROM FMS7_TRANSPORTER_CONTACT_MST WHERE TRANSPORTER_CD = '"+Entity_Id+"' " +
						  		" AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
			   	       CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
				       CONTACT_TYPE.add("");
		          } 
	          }
		  }
		  else if(EntityFlag.equalsIgnoreCase("trd"))
		  {
			  //Following Query Is Commented By Samik Shah On 5th June, 2010 ...
			  //queryString = "select seq_no, to_char(max(eff_dt),'dd/mm/yyyy') from fms7_trader_contact_mst where " +
			  				//"trader_cd='"+Entity_Id+"' group by seq_no";
			  
			  //Following Query Has Been Introduced By Samik Shah On 5th June, 2010 ...
			  queryString = "SELECT DISTINCT(A.seq_no), TO_CHAR(A.eff_dt,'DD/MM/YYYY') FROM " +
							"FMS7_TRADER_CONTACT_MST A WHERE A.trader_cd="+Entity_Id+" AND " +
							"A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_TRADER_CONTACT_MST B WHERE " +
							"B.trader_cd=A.trader_cd AND B.seq_no=A.seq_no AND " +
							"B.eff_dt<=TO_DATE('"+validate_dt+"','DD/MM/YYYY')) ORDER BY A.seq_no";
			  
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  CODE.add(rset.getString(1)==null?"":rset.getString(1));
				  DT.add(rset.getString(2)==null?"":rset.getString(2));
			  }
			  for(int j=0;j<CODE.size();j++)
			  { 		
				  queryString = "SELECT SEQ_NO, to_char(EFF_DT,'dd/mm/yyyy'), " +
						  		" CONTACT_PERSON, PHONE, MOBILE," +
						  		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						  		" ADDL_ADDR_LINE, NOM_FLAG, INV_FLAG," +
						  		" FM_FLAG, PM_FLAG, OTHER_FLAG, ACTIVE_FLAG,CONTACT_DESIG ,JT_FLAG ,DEF_NOM_FLAG," +
						  		" DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG,  DEF_OTHER_FLAG, DEF_JT_FLAG" +
						  		" FROM FMS7_TRADER_CONTACT_MST WHERE TRADER_CD = '"+Entity_Id+"' " +
						  		"AND EFF_DT = to_date('"+DT.elementAt(j)+"','dd/mm/yyyy')  AND SEQ_NO = '"+CODE.elementAt(j)+"' " ;
				  rset = stmt.executeQuery(queryString);
				  if(rset.next())
				  {
			   	       CSEQ_NO.add(rset.getString(1)==null?"":rset.getString(1)); 
			   	       CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	       CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	       CPHONE.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	       CMOBILE.add(rset.getString(5)==null?"":rset.getString(5)); 
			   	       CFAX_1.add(rset.getString(6)==null?"":rset.getString(6)); 
			   	       CFAX_2.add(rset.getString(7)==null?"":rset.getString(7)); 
			   	       CEMAIL.add(rset.getString(8)==null?"":rset.getString(8)); 
			   	       ADDR_FLAG.add(rset.getString(9)==null?"":rset.getString(9)); 
			   	       ADDL_ADDR_LINE.add(rset.getString(10)==null?"":rset.getString(10)); 
			   	       NOM_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); 
			   	       INV_FLAG.add(rset.getString(12)==null?"":rset.getString(12)); 
			   	       FM_FLAG.add(rset.getString(13)==null?"":rset.getString(13)); 
			   	       PM_FLAG.add(rset.getString(14)==null?"":rset.getString(14)); 
			   	       OTHER_FLAG.add(rset.getString(15)==null?"":rset.getString(15)); 
			   	       ACTIVE_FLAG.add(rset.getString(16)==null?"":rset.getString(16)); 
			   	       CONTACT_DESIG.add(rset.getString(17)==null?"":rset.getString(17)); 
			   	       JT_FLAG.add(rset.getString(18)==null?"":rset.getString(18));
			   	       DNOM_FLAG.add(rset.getString(19)==null?"":rset.getString(19)); 
				       DINV_FLAG.add(rset.getString(20)==null?"":rset.getString(20)); 
				       DFM_FLAG.add(rset.getString(21)==null?"":rset.getString(21)); 
				       DPM_FLAG.add(rset.getString(22)==null?"":rset.getString(22)); 
				       DOTHER_FLAG.add(rset.getString(23)==null?"":rset.getString(23)); 
				       DJT_FLAG.add(rset.getString(24)==null?"":rset.getString(24));
				       CONTACT_TYPE.add("");
		          } 
	          } 
		  }
		  else if(EntityFlag.equalsIgnoreCase("other"))
		  {
			  queryString = "SELECT A.OTHER_CD, to_char(A.EFF_DT,'dd/mm/yyyy'), " +
			  				"A.CONTACT_PERSON,A.CONTACT_DESIG , A.CONTACT_DEPT,A.CONTACT_TYPE " +
			  				"FROM FMS7_OTHER_CONTACT_MST A WHERE A.OTHER_CD = '"+Entity_Id+"' " +
			  				"AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_OTHER_CONTACT_MST B WHERE " +
			  				"A.OTHER_CD=B.OTHER_CD AND A.CONTACT_TYPE=B.CONTACT_TYPE AND " +
			  				"B.eff_dt<=TO_DATE('"+validate_dt+"','DD/MM/YYYY')) order by a.contact_type";
			  //System.out.println(queryString);
			  rset = stmt.executeQuery(queryString);
			  int count = 0;
			  while(rset.next())
			  {
				  CSEQ_NO.add((++count)+""); 
				  CEFF_DT.add(rset.getString(2)==null?"":rset.getString(2)); 
			   	  CONTACT_PERSON.add(rset.getString(3)==null?"":rset.getString(3)); 
			   	  CONTACT_DESIG.add(rset.getString(4)==null?"":rset.getString(4)); 
			   	  CONTACT_TYPE.add(rset.getString(6)==null?"":rset.getString(6));
			  }
		  }
	  }
  }
  catch(Exception e)
  {
	  //System.out.println("Exception in Databean_Entity--->Fetch_Entity_Contact_Details()-->"+e);
	  e.printStackTrace();
  }
}

   public void Fill_Array_Variables()
   {
	   try{
		    for(int i=0;i<3;i++){
		    	  EFF_DT[i] = "";
	    		  ADDR[i] =  "";
	    		  CITY[i] =  "";
	    		  PIN[i]  =  "";
	    		  STATE[i]=  "";
	    		  ZONE[i] = "";
	    		  COUNTRY[i]= "";
	    		  PHONE[i] =  "";
	    		  MOBILE[i]=  "";
	    		  ALT_PHONE[i]= "";
	    		  FAX_1[i]=  "";
	    		  FAX_2[i]=  "";
	    		  EMAIL[i]=  "";
		    }
//		    for(int i=0;i<15;i++){ 	//CHANGED BY RS28022017 FOR RIL ISD PLANT ENTRY
//	            SEQ_NO[i]  = "";
//    		   	PEFF_DT[i]  = "";
//    		   	PLANT_TYPE[i]= "";
//    		   	PLANT_NAME[i]= "";
//    		   	PLANT_ABBR[i]= "";
//    		   	PLANT_ADDR[i]= "";
//    		   	PLANT_STATE[i]= "";
//    		   	PLANT_ZONE[i]= "";
//    		   	PLANT_CITY[i]= "";
//    		   	PLANT_PIN[i]= "";
//    		   	PLANT_SECTOR[i]= "";
//    		   	PLANT_STATE_CODE[i]= "";
//    		   	PLANT_SHORT_ABBR[i]= "";
//		    }
		    FillArray(0);
	    	
	   }catch(Exception e){
//		    System.out.println("Exception in Databean_Entity--->Fill_Array_Variables()-->"+e);
			e.printStackTrace();
	   }
    }
	
   	public void fetch_Sector_details()
	{
		try
		{
			queryString = "select SECTOR_CD, SECTOR_NAME FROM FMS7_SECTOR_MST ORDER BY SECTOR_CD";
			//System.out.println("Sector Selection Master Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				SECTOR_CD.add(rset.getString(1)==null?"":rset.getString(1));
				SECTOR_NM.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->fetch_Sector_details()-->"+e);
			e.printStackTrace();
		}
	}
	
   	
   	public void Fetch_All_Entity_Detail_List2()
	{
		try
		{
			queryString = "Select distinct(a.CUSTOMER_CD), a.CUSTOMER_NAME, a.CUSTOMER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY')  from FMS7_CUSTOMER_MST a where " +
	 					  "a.eff_dt = (select max(b.Eff_dt) from FMS7_CUSTOMER_MST b where a.customer_cd=b.customer_cd) ORDER BY a.CUSTOMER_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				Entity_code2.add(rset.getString(1)==null?"":rset.getString(1));
				Entity_name2.add(rset.getString(2)==null?"":rset.getString(2));
				Entity_abr2.add(rset.getString(3)==null?"":rset.getString(3));
				Entity_type2.add("cus");
			}
			
			queryString = "Select distinct(a.SUPPLIER_CD), a.SUPPLIER_NAME, a.SUPPLIER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY') from FMS7_SUPPLIER_MST a where " +
						  "a.eff_dt = (select max(b.eff_Dt) from FMS7_SUPPLIER_MST b where a.supplier_cd=b.supplier_cd) ORDER BY a.SUPPLIER_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				Entity_code2.add(rset.getString(1)==null?"":rset.getString(1));
				Entity_name2.add(rset.getString(2)==null?"":rset.getString(2));
				Entity_abr2.add(rset.getString(3)==null?"":rset.getString(3));
				Entity_type2.add("sup");
			}
			
			queryString = "Select distinct(a.TRANSPORTER_CD), a.TRANSPORTER_NAME, a.TRANSPORTER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY') from FMS7_TRANSPORTER_MST a where " +
						  "a.eff_dt = (select max(b.eff_Dt) from FMS7_TRANSPORTER_MST b where a.TRANSPORTER_CD=b.TRANSPORTER_CD) ORDER BY a.TRANSPORTER_CD";			
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				Entity_code2.add(rset.getString(1)==null?"":rset.getString(1));
				Entity_name2.add(rset.getString(2)==null?"":rset.getString(2));
				Entity_abr2.add(rset.getString(3)==null?"":rset.getString(3));
				Entity_type2.add("tran");
			}
			
			queryString = "Select distinct(a.TRADER_CD), a.TRADER_NAME, a.TRADER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY') from FMS7_TRADER_MST a where " +
						  "a.eff_dt = (select max(b.eff_Dt) from FMS7_TRADER_MST b where a.TRADER_CD=b.TRADER_CD) ORDER BY a.TRADER_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				Entity_code2.add(rset.getString(1)==null?"":rset.getString(1));
				Entity_name2.add(rset.getString(2)==null?"":rset.getString(2));
				Entity_abr2.add(rset.getString(3)==null?"":rset.getString(3));
				Entity_type2.add("trd");
			}
			
			queryString = "Select distinct(a.OTHER_CD), a.OTHER_NAME, a.OTHER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY') from FMS7_OTHER_MST a where " +
						  "a.eff_dt = (select max(b.eff_Dt) from FMS7_OTHER_MST b where a.OTHER_CD=b.OTHER_CD) ORDER BY a.OTHER_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				Entity_code2.add(rset.getString(1)==null?"":rset.getString(1));
				Entity_name2.add(rset.getString(2)==null?"":rset.getString(2));
				Entity_abr2.add(rset.getString(3)==null?"":rset.getString(3));
				Entity_type2.add("other");
			}
			
			queryString = "Select distinct(a.VENDOR_CD), a.VENDOR_NAME, a.VENDOR_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY') from FMS8_VENDOR_MST a where " +
					  "a.eff_dt = (select max(b.eff_Dt) from FMS8_VENDOR_MST b where a.VENDOR_CD=b.VENDOR_CD) ORDER BY a.VENDOR_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				Entity_code2.add(rset.getString(1)==null?"":rset.getString(1));
				Entity_name2.add(rset.getString(2)==null?"":rset.getString(2));
				Entity_abr2.add(rset.getString(3)==null?"":rset.getString(3));
				Entity_type2.add("trd");
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Fetch_All_Entity_Detail_List()-->"+e);
			e.printStackTrace();
		}
	}
   	
   	public void Fetch_All_Entity_Detail_List()
	{
		try
		{
			List l1 = new ArrayList();
			boolean flag = false;
			
//			System.out.println("From Fetch_All_Entity_Detail_List  >>>>>>> "+EntityFlag);
			
			if(EntityFlag.equalsIgnoreCase("cus"))
			{
				 queryString = "Select distinct(a.CUSTOMER_CD), a.CUSTOMER_NAME, a.CUSTOMER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T')  " +
				 		" from FMS7_CUSTOMER_MST a" +
				 		" where a.eff_dt = (select max(b.Eff_dt) from FMS7_CUSTOMER_MST b where a.customer_cd=b.customer_cd) "
				 		+ " " +
				 		" ORDER BY A.CUSTOMER_NAME ";	
			}
			else if(EntityFlag.equalsIgnoreCase("sup"))
			{
				queryString = "Select distinct(a.SUPPLIER_CD), a.SUPPLIER_NAME, a.SUPPLIER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_SUPPLIER_MST  a " +
						" where a.eff_dt = (select max(b.eff_Dt) from FMS7_SUPPLIER_MST b where a.supplier_cd=b.supplier_cd) " +
						//SB20130711 " ORDER BY a.SUPPLIER_CD";	
						" ORDER BY a.SUPPLIER_NAME";	
			}
			else if(EntityFlag.equalsIgnoreCase("tran"))
			{
				queryString = "Select distinct(a.TRANSPORTER_CD), a.TRANSPORTER_NAME, a.TRANSPORTER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_TRANSPORTER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_TRANSPORTER_MST b where a.TRANSPORTER_CD=b.TRANSPORTER_CD) " +
						//SB20130711 "ORDER BY a.TRANSPORTER_CD";	
						"ORDER BY a.TRANSPORTER_NAME";	
			}
			else if(EntityFlag.equalsIgnoreCase("trd"))
			{
				queryString = "Select distinct(a.TRADER_CD), a.TRADER_NAME, a.TRADER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_TRADER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_TRADER_MST b where a.TRADER_CD=b.TRADER_CD) " +
				//SB20130711 " ORDER BY a.TRADER_CD";
				" ORDER BY a.TRADER_NAME ";
			}
			else if(EntityFlag.equalsIgnoreCase("other"))
			{
				queryString = "Select distinct(a.OTHER_CD), a.OTHER_NAME, a.OTHER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_OTHER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_OTHER_MST b where a.OTHER_CD=b.OTHER_CD) " +
				//SB20130711 " ORDER BY a.OTHER_CD";
				" ORDER BY a.OTHER_NAME";
				
				
					//System.out.println("..l1..."+l1);
			} else if(EntityFlag.equalsIgnoreCase("ven")) {
				queryString = "SELECT DISTINCT(A.VENDOR_CD),A.VENDOR_NAME,A.VENDOR_ABBR,TO_CHAR(A.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'Y') "
						+ "FROM FMS8_VENDOR_MST A WHERE A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM FMS8_VENDOR_MST B WHERE B.VENDOR_CD=A.VENDOR_CD) "
						+ "ORDER BY A.VENDOR_NAME";
			}
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				//System.out.println("rset.getString(1)....."+rset.getString(1));
					
				//System.out.println(flag);
					Entity_code.add(rset.getString(1)==null?"0":rset.getString(1));
					Entity_name.add(rset.getString(2)==null?"":rset.getString(2));
					Entity_abr.add(rset.getString(3)==null?"":rset.getString(3));
					Entity_flag.add(rset.getString(5)); //SB20130711
					
					if(EntityFlag.equalsIgnoreCase("cus"))
					{
						String cust_cd = rset.getString(1)==null?"0":rset.getString(1);
						queryString1 = "SELECT ACCOUNT_CD FROM FMS7_CUSTOMER_MAPPING WHERE CUSTOMER_CD="+cust_cd+"";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							Entity_sun_account_code.add(rset1.getString(1)==null?"":rset1.getString(1));
						}
						else
						{
							Entity_sun_account_code.add("");
						}
						Shell_group_flag.add("");
						Purchase_sun_account_code.add("");
						Custom_duty_sun_account_code.add("");
					}
					else if(EntityFlag.equalsIgnoreCase("trd"))
					{
						String trd_cd = rset.getString(1)==null?"0":rset.getString(1);
						queryString1 = "SELECT ACCOUNT_CD FROM FMS7_TRADER_MAPPING WHERE TRADER_CD="+trd_cd+"";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							Entity_sun_account_code.add(rset1.getString(1)==null?"":rset1.getString(1));
						}
						else
						{
							Entity_sun_account_code.add("");
						}
						
						queryString1 = "SELECT ACCOUNT_CD,NVL(SHELL_GRP_FLAG,'N') FROM FMS7_LNG_PURCHASE_MAPPING WHERE TRADER_CD="+trd_cd+"";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							String shell_grp_flag = rset1.getString(2);
							Purchase_sun_account_code.add(rset1.getString(1)==null?"":rset1.getString(1));
							if(shell_grp_flag.trim().equalsIgnoreCase("N"))
							{
								Shell_group_flag.add("");
							}
							else if(shell_grp_flag.trim().equalsIgnoreCase("Y"))
							{
								Shell_group_flag.add("Checked");
							}
							else
							{
								Shell_group_flag.add("");
							}
						}
						else
						{
							Purchase_sun_account_code.add("");
							Shell_group_flag.add("");
						}
						
						queryString1 = "SELECT ACCOUNT_CD FROM FMS7_CUSTOM_DUTY_MAPPING WHERE TRADER_CD="+trd_cd+"";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							Custom_duty_sun_account_code.add(rset1.getString(1)==null?"":rset1.getString(1));
						}
						else
						{
							Custom_duty_sun_account_code.add("");
						}
					}
					else
					{
						Entity_sun_account_code.add("");
						Shell_group_flag.add("");
						Purchase_sun_account_code.add("");
						Custom_duty_sun_account_code.add("");
					}
				
			}
			 
			for(int i=0; i<Entity_code.size(); i++)
			{
				if(EntityFlag.equalsIgnoreCase("cus"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_CUSTOMER_MST a WHERE " +
						 		  "a.CUSTOMER_CD="+Entity_code.elementAt(i)+" AND " +
						 		  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_CUSTOMER_MST b  " +
						 		  "WHERE a.CUSTOMER_CD=b.CUSTOMER_CD)";		
				}
				else if(EntityFlag.equalsIgnoreCase("sup"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_SUPPLIER_MST a WHERE " +
			 		  			  "a.SUPPLIER_CD="+Entity_code.elementAt(i)+" AND " +
			 		  			  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_SUPPLIER_MST b  " +
			 		  			  "WHERE a.SUPPLIER_CD=b.SUPPLIER_CD)";			
				}
				else if(EntityFlag.equalsIgnoreCase("tran"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_TRANSPORTER_MST a WHERE " +
		  			  			  "a.TRANSPORTER_CD="+Entity_code.elementAt(i)+" AND " +
		  			  			  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_TRANSPORTER_MST b  " +
		  			  			  "WHERE a.TRANSPORTER_CD=b.TRANSPORTER_CD)";
				}
				else if(EntityFlag.equalsIgnoreCase("trd"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_TRADER_MST a WHERE " +
		  			  			  "a.TRADER_CD="+Entity_code.elementAt(i)+" AND " +
		  			  			  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_TRADER_MST b  " +
		  			  			  "WHERE a.TRADER_CD=b.TRADER_CD)";
				}
				else if(EntityFlag.equalsIgnoreCase("other"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_OTHER_MST a WHERE " +
		  			  			  "a.OTHER_CD="+Entity_code.elementAt(i)+" AND " +
		  			  			  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_OTHER_MST b  " +
		  			  			  "WHERE a.OTHER_CD=b.OTHER_CD)";
				}
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Effect_date.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					Effect_date.add("");
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Fetch_All_Entity_Detail_List()-->"+e);
			e.printStackTrace();
		}
	}
	
   	String Customer_access_flag="N";
   	
	public void Fetch_All_Entity_Detail_List_WITH_ACCESS()
	{
		try
		{
			//System.out.println("From Fetch_All_Entity_Detail_List  >>>>>>> "+EntityFlag);
			
			if(EntityFlag.equalsIgnoreCase("cus"))
			{
				 queryString = "Select distinct(a.CUSTOMER_CD), a.CUSTOMER_NAME, a.CUSTOMER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T')  " +
				 		" from FMS7_CUSTOMER_MST a, sec_emp_customer_alloc_mst c " +
				 		" where a.eff_dt = (select max(b.Eff_dt) from FMS7_CUSTOMER_MST b where a.customer_cd=b.customer_cd) "
				 		+ "and a.customer_cd=c.customer_cd and c.emp_cd='"+set_emp_id+"' " +
				 		" ORDER BY A.CUSTOMER_NAME ";	
			}
			else if(EntityFlag.equalsIgnoreCase("sup"))
			{
				queryString = "Select distinct(a.SUPPLIER_CD), a.SUPPLIER_NAME, a.SUPPLIER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_SUPPLIER_MST  a " +
						" where a.eff_dt = (select max(b.eff_Dt) from FMS7_SUPPLIER_MST b where a.supplier_cd=b.supplier_cd) " +
						//SB20130711 " ORDER BY a.SUPPLIER_CD";	
						" ORDER BY a.SUPPLIER_NAME";	
			}
			else if(EntityFlag.equalsIgnoreCase("tran"))
			{
				queryString = "Select distinct(a.TRANSPORTER_CD), a.TRANSPORTER_NAME, a.TRANSPORTER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_TRANSPORTER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_TRANSPORTER_MST b where a.TRANSPORTER_CD=b.TRANSPORTER_CD) " +
						//SB20130711 "ORDER BY a.TRANSPORTER_CD";	
						"ORDER BY a.TRANSPORTER_NAME";	
			}
			else if(EntityFlag.equalsIgnoreCase("trd"))
			{
				queryString = "Select distinct(a.TRADER_CD), a.TRADER_NAME, a.TRADER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_TRADER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_TRADER_MST b where a.TRADER_CD=b.TRADER_CD) " +
				//SB20130711 " ORDER BY a.TRADER_CD";
				" ORDER BY a.TRADER_NAME ";
			}
			else if(EntityFlag.equalsIgnoreCase("other"))
			{
				queryString = "Select distinct(a.OTHER_CD), a.OTHER_NAME, a.OTHER_ABBR, TO_CHAR(a.EFF_DT,'DD/MM/YYYY'), NVL(A.FLAG,'T') " +
						" from FMS7_OTHER_MST a where a.eff_dt = (select max(b.eff_Dt) from FMS7_OTHER_MST b where a.OTHER_CD=b.OTHER_CD) " +
				//SB20130711 " ORDER BY a.OTHER_CD";
				" ORDER BY a.OTHER_NAME";
				
				
					//System.out.println("..l1..."+l1);
			}
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				//System.out.println("rset.getString(1)....."+rset.getString(1));
					
				//System.out.println(flag);
					Entity_code.add(rset.getString(1)==null?"0":rset.getString(1));
					Entity_name.add(rset.getString(2)==null?"":rset.getString(2));
					Entity_abr.add(rset.getString(3)==null?"":rset.getString(3));
					Entity_flag.add(rset.getString(5)); //SB20130711
					
					if(EntityFlag.equalsIgnoreCase("cus"))
					{
						String cust_cd = rset.getString(1)==null?"0":rset.getString(1);
						queryString1 = "SELECT ACCOUNT_CD FROM FMS7_CUSTOMER_MAPPING WHERE CUSTOMER_CD="+cust_cd+"";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							Entity_sun_account_code.add(rset1.getString(1)==null?"":rset1.getString(1));
						}
						else
						{
							Entity_sun_account_code.add("");
						}
						Shell_group_flag.add("");
						Purchase_sun_account_code.add("");
						Custom_duty_sun_account_code.add("");
					}
					else if(EntityFlag.equalsIgnoreCase("trd"))
					{
						String trd_cd = rset.getString(1)==null?"0":rset.getString(1);
						queryString1 = "SELECT ACCOUNT_CD FROM FMS7_TRADER_MAPPING WHERE TRADER_CD="+trd_cd+"";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							Entity_sun_account_code.add(rset1.getString(1)==null?"":rset1.getString(1));
						}
						else
						{
							Entity_sun_account_code.add("");
						}
						
						queryString1 = "SELECT ACCOUNT_CD,NVL(SHELL_GRP_FLAG,'N') FROM FMS7_LNG_PURCHASE_MAPPING WHERE TRADER_CD="+trd_cd+"";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							String shell_grp_flag = rset1.getString(2);
							Purchase_sun_account_code.add(rset1.getString(1)==null?"":rset1.getString(1));
							if(shell_grp_flag.trim().equalsIgnoreCase("N"))
							{
								Shell_group_flag.add("");
							}
							else if(shell_grp_flag.trim().equalsIgnoreCase("Y"))
							{
								Shell_group_flag.add("Checked");
							}
							else
							{
								Shell_group_flag.add("");
							}
						}
						else
						{
							Purchase_sun_account_code.add("");
							Shell_group_flag.add("");
						}
						
						queryString1 = "SELECT ACCOUNT_CD FROM FMS7_CUSTOM_DUTY_MAPPING WHERE TRADER_CD="+trd_cd+"";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							Custom_duty_sun_account_code.add(rset1.getString(1)==null?"":rset1.getString(1));
						}
						else
						{
							Custom_duty_sun_account_code.add("");
						}
					}
					else
					{
						Entity_sun_account_code.add("");
						Shell_group_flag.add("");
						Purchase_sun_account_code.add("");
						Custom_duty_sun_account_code.add("");
					}
				
			}
			 
			for(int i=0; i<Entity_code.size(); i++)
			{
				if(EntityFlag.equalsIgnoreCase("cus"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_CUSTOMER_MST a WHERE " +
						 		  "a.CUSTOMER_CD="+Entity_code.elementAt(i)+" AND " +
						 		  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_CUSTOMER_MST b  " +
						 		  "WHERE a.CUSTOMER_CD=b.CUSTOMER_CD)";		
				}
				else if(EntityFlag.equalsIgnoreCase("sup"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_SUPPLIER_MST a WHERE " +
			 		  			  "a.SUPPLIER_CD="+Entity_code.elementAt(i)+" AND " +
			 		  			  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_SUPPLIER_MST b  " +
			 		  			  "WHERE a.SUPPLIER_CD=b.SUPPLIER_CD)";			
				}
				else if(EntityFlag.equalsIgnoreCase("tran"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_TRANSPORTER_MST a WHERE " +
		  			  			  "a.TRANSPORTER_CD="+Entity_code.elementAt(i)+" AND " +
		  			  			  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_TRANSPORTER_MST b  " +
		  			  			  "WHERE a.TRANSPORTER_CD=b.TRANSPORTER_CD)";
				}
				else if(EntityFlag.equalsIgnoreCase("trd"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_TRADER_MST a WHERE " +
		  			  			  "a.TRADER_CD="+Entity_code.elementAt(i)+" AND " +
		  			  			  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_TRADER_MST b  " +
		  			  			  "WHERE a.TRADER_CD=b.TRADER_CD)";
				}
				else if(EntityFlag.equalsIgnoreCase("other"))
				{
					queryString = "SELECT TO_CHAR(a.EFF_DT,'DD/MM/YYYY') FROM FMS7_OTHER_MST a WHERE " +
		  			  			  "a.OTHER_CD="+Entity_code.elementAt(i)+" AND " +
		  			  			  "a.eff_dt=(SELECT MIN(b.Eff_dt) from FMS7_OTHER_MST b  " +
		  			  			  "WHERE a.OTHER_CD=b.OTHER_CD)";
				}
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Effect_date.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					Effect_date.add("");
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Fetch_All_Entity_Detail_List()-->"+e);
			e.printStackTrace();
		}
	}
	
	public void Fetch_Selected_Entity_Details()
	{
		try
		{
			Entity_master_details();
			Entity_Address_details();
			Entity_Plant_Details();
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Fetch_Selected_Entity_Details()-->"+e);
			e.printStackTrace();
		}		
	}
	 
	public void Entity_master_details()
	{
		try
		{
			if(EntityFlag.equalsIgnoreCase("cus"))
			{
				 queryString = "Select CUSTOMER_CD, CUSTOMER_NAME, CUSTOMER_ABBR, " +
				 " WEB_ADDR, NOTES, EMP_CD, to_char(ENT_DT,'dd/mm/yyyy'), " +
				 " to_char(EFF_DT,'dd/mm/yyyy'), " +
				 " GST_TIN_NO, to_char(GST_TIN_DT,'dd/mm/yyyy'), " +
				 " CST_TIN_NO, to_char(CST_TIN_DT,'dd/mm/yyyy'), " +
				 " PAN_NO, to_char(PAN_ISSUE_DT,'dd/mm/yyyy'), " +
				 " TAN_NO, to_char(TAN_ISSUE_DT,'dd/mm/yyyy'), " +
				 " ADDL_NO, to_char(ADDL_ISSUE_DT,'dd/mm/yyyy'), " +
				 " CREDIT_RATING from FMS7_CUSTOMER_MST " +
				 " WHERE CUSTOMER_CD='"+Entity_Id+"' and FLAG='T' AND EFF_DT = " +
				 "(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+Entity_Id+"' and FLAG='T' ) ";
			}
			else if(EntityFlag.equalsIgnoreCase("sup"))
			{
				queryString = "Select SUPPLIER_CD, SUPPLIER_NAME, SUPPLIER_ABBR, " +
				" WEB_ADDR, NOTES, EMP_CD, to_char(ENT_DT,'dd/mm/yyyy'), " +
				" to_char(EFF_DT,'dd/mm/yyyy'), " +
				" GST_TIN_NO, to_char(GST_TIN_DT,'dd/mm/yyyy'), " +
				" CST_TIN_NO, to_char(CST_TIN_DT,'dd/mm/yyyy'), " +
				" PAN_NO, to_char(PAN_ISSUE_DT,'dd/mm/yyyy'), " +
				" TAN_NO, to_char(TAN_ISSUE_DT,'dd/mm/yyyy'), " +
				" ADDL_NO, to_char(ADDL_ISSUE_DT,'dd/mm/yyyy') "
				+ ", GSTIN_NO, TO_CHAR(GSTIN_DT,'DD/MM/YYYY') "+ //RS01062017 FOR GST" +
				" from FMS7_SUPPLIER_MST " +
		 		" WHERE SUPPLIER_CD = '"+Entity_Id+"' and FLAG='T' AND EFF_DT = " +
		 		" (SELECT MAX(EFF_DT) FROM FMS7_SUPPLIER_MST WHERE SUPPLIER_CD = '"+Entity_Id+"' and FLAG='T') ";
			}
			else if(EntityFlag.equalsIgnoreCase("tran"))
			{
				queryString = "Select TRANSPORTER_CD, TRANSPORTER_NAME, TRANSPORTER_ABBR, " +
				" WEB_ADDR, NOTES, EMP_CD, to_char(ENT_DT,'dd/mm/yyyy'), " +
				" to_char(EFF_DT,'dd/mm/yyyy'), " +
				" GST_TIN_NO, to_char(GST_TIN_DT,'dd/mm/yyyy'), " +
				" CST_TIN_NO, to_char(CST_TIN_DT,'dd/mm/yyyy'), " +
				" PAN_NO, to_char(PAN_ISSUE_DT,'dd/mm/yyyy'), " +
				" TAN_NO, to_char(TAN_ISSUE_DT,'dd/mm/yyyy'), " +
				" ADDL_NO, to_char(ADDL_ISSUE_DT,'dd/mm/yyyy')" 
				+ ", GSTIN_NO, TO_CHAR(GSTIN_DT,'DD/MM/YYYY') " +//RS01062017 FOR GST " +
				" from FMS7_TRANSPORTER_MST " +
		 		" WHERE TRANSPORTER_CD='"+Entity_Id+"' and FLAG='T' AND EFF_DT = " +
		 		" (SELECT MAX(EFF_DT) FROM FMS7_TRANSPORTER_MST WHERE TRANSPORTER_CD='"+Entity_Id+"' and FLAG='T' ) ";
			}
			else if(EntityFlag.equalsIgnoreCase("trd"))
			{
				queryString = "Select TRADER_CD, TRADER_NAME, TRADER_ABBR, " +
				" WEB_ADDR, NOTES, EMP_CD, to_char(ENT_DT,'dd/mm/yyyy'), " +
				" to_char(EFF_DT,'dd/mm/yyyy'), " +
				" GST_TIN_NO, to_char(GST_TIN_DT,'dd/mm/yyyy'), " +
				" CST_TIN_NO, to_char(CST_TIN_DT,'dd/mm/yyyy'), " +
				" PAN_NO, to_char(PAN_ISSUE_DT,'dd/mm/yyyy'), " +
				" TAN_NO, to_char(TAN_ISSUE_DT,'dd/mm/yyyy'), " +
				" ADDL_NO, to_char(ADDL_ISSUE_DT,'dd/mm/yyyy'), "
				+ " GSTIN_NO, TO_CHAR(GSTIN_DT,'DD/MM/YYYY') "+ //RS01062017 FOR GST" +
				" from FMS7_TRADER_MST " +
		 		" WHERE TRADER_CD='"+Entity_Id+"' and FLAG='T' AND EFF_DT = "+
				" (SELECT MAX(EFF_DT) FROM FMS7_TRADER_MST WHERE TRADER_CD='"+Entity_Id+"' and FLAG='T' ) ";
			}
			else if(EntityFlag.equalsIgnoreCase("other"))
			{
				queryString = "Select OTHER_CD, OTHER_NAME, OTHER_ABBR,  " +
		 		" WEB_ADDR, NOTES, EMP_CD, to_char(ENT_DT,'dd/mm/yyyy'), " +
		 		" to_char(EFF_DT,'dd/mm/yyyy') from FMS7_OTHER_MST " +
		 		" WHERE OTHER_CD='"+Entity_Id+"' and FLAG='T' AND EFF_DT = "+
				" (SELECT MAX(EFF_DT) FROM FMS7_OTHER_MST WHERE OTHER_CD='"+Entity_Id+"' and FLAG='T' ) ";
			} 
			else if(EntityFlag.equalsIgnoreCase("ven"))
			{
				queryString = "Select VENDOR_CD, VENDOR_NAME, VENDOR_ABBR, " +
						" WEB_ADDR, NOTES, EMP_CD, to_char(ENT_DT,'dd/mm/yyyy'), " +
						" to_char(EFF_DT,'dd/mm/yyyy'), " +
						" GST_TIN_NO, to_char(GST_TIN_DT,'dd/mm/yyyy'), " +
						" CST_TIN_NO, to_char(CST_TIN_DT,'dd/mm/yyyy'), " +
						" PAN_NO, to_char(PAN_ISSUE_DT,'dd/mm/yyyy'), " +
						" TAN_NO, to_char(TAN_ISSUE_DT,'dd/mm/yyyy'), " +
						" ADDL_NO, to_char(ADDL_ISSUE_DT,'dd/mm/yyyy'), "
						+ " GSTIN_NO, TO_CHAR(GSTIN_DT,'DD/MM/YYYY') "+ //RS01062017 FOR GST" +
						" from FMS8_VENDOR_MST " +
				 		" WHERE VENDOR_CD='"+Entity_Id+"' and FLAG='T' AND EFF_DT = "+
						" (SELECT MAX(EFF_DT) FROM FMS8_VENDOR_MST WHERE VENDOR_CD='"+Entity_Id+"' and FLAG='T' ) ";
			}
			
			rset = stmt.executeQuery(queryString);
		    if(rset.next())
		    {
			    ENTITY_CD = rset.getString(1)==null?"":rset.getString(1);
				ENTITY_NAME = rset.getString(2)==null?"":rset.getString(2); 
				ENTITY_ABBR = rset.getString(3)==null?"":rset.getString(3);
				WEB_ADDR = rset.getString(4)==null?"":rset.getString(4);
				NOTES = rset.getString(5)==null?"":rset.getString(5);
				ENTITY_EFF_DT = rset.getString(8)==null?"":rset.getString(8);
				 
				if(!EntityFlag.equalsIgnoreCase("other"))
				{
					GST_TIN_NO = rset.getString(9)==null?"":rset.getString(9);
					GST_TIN_DT = rset.getString(10)==null?"":rset.getString(10); 
					CST_TIN_NO = rset.getString(11)==null?"":rset.getString(11);
					CST_TIN_DT = rset.getString(12)==null?"":rset.getString(12);
					PAN_NO = rset.getString(13)==null?"":rset.getString(13);
					PAN_ISSUE_DT = rset.getString(14)==null?"":rset.getString(14);
					TAN_NO = rset.getString(15)==null?"":rset.getString(15);
					TAN_ISSUE_DT = rset.getString(16)==null?"":rset.getString(16); 
					ADDL_NO = rset.getString(17)==null?"":rset.getString(17);
					ADDL_ISSUE_DT = rset.getString(18)==null?"":rset.getString(18);
					
					if(!EntityFlag.equalsIgnoreCase("cus")) { //FOR GST RS 01062017
						GSTIN_NO = rset.getString(19)==null?"":rset.getString(19);
						GSTIN_DT = 	rset.getString(20)==null?"":rset.getString(20);	
					}
				}
				
				if(EntityFlag.equalsIgnoreCase("cus"))
				{
					CREDIT_RATING = rset.getString(19)==null?"":rset.getString(19);
				}
			}
		    else
		    {
		    	ENTITY_CD = "";
				ENTITY_NAME = ""; 
				ENTITY_ABBR = "";
				WEB_ADDR = "";
				NOTES = "";
				ENTITY_EFF_DT = "";
				GST_TIN_NO = ""; 
				GST_TIN_DT  = ""; 
				CST_TIN_NO = ""; 
				CST_TIN_DT = ""; 
				PAN_NO = ""; 
				PAN_ISSUE_DT = ""; 
				TAN_NO = ""; 
				TAN_ISSUE_DT = ""; 
				ADDL_NO = ""; 
				ADDL_ISSUE_DT = "";
				CREDIT_RATING = "";
				GSTIN_NO = ""; //RS01062017
				GSTIN_DT = "";  //RS01062017
		    }		     
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Entity_master_details()-->"+e);
			e.printStackTrace();
		}
	}
	
	
	public void Fetch_Entity_Plant_Details()
	{
		try
		{
			if(EntityFlag.equalsIgnoreCase("cus"))
			{
				 queryString = "Select DISTINCT(A.SEQ_NO), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR  " +
				 " from FMS7_CUSTOMER_PLANT_DTL A " +
				 " WHERE A.CUSTOMER_CD='"+Entity_Id+"' and A.FLAG='T' and  " +
				 " A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B WHERE " +
				 " B.CUSTOMER_CD='"+Entity_Id+"' and A.SEQ_NO=B.SEQ_NO and A.FLAG=B.FLAG)" +
				 " ORDER BY A.SEQ_NO";
				 
			}
			else if(EntityFlag.equalsIgnoreCase("sup"))
			{
				 queryString = "Select DISTINCT(A.SEQ_NO), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR  " +
				 " from FMS7_SUPPLIER_PLANT_DTL A " +
				 " WHERE A.SUPPLIER_CD='"+Entity_Id+"' and A.FLAG='T' and  " +
				 " A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_PLANT_DTL B WHERE " +
				 " B.SUPPLIER_CD='"+Entity_Id+"' and A.SEQ_NO=B.SEQ_NO and A.FLAG=B.FLAG)" +
				 " ORDER BY A.SEQ_NO";
				
			}
			else if(EntityFlag.equalsIgnoreCase("tran"))
			{
				 queryString = "Select DISTINCT(A.SEQ_NO), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR  " +
				 " from FMS7_TRANSPORTER_PLANT_DTL A " +
				 " WHERE A.TRANSPORTER_CD='"+Entity_Id+"' and A.FLAG='T' and  " +
				 " A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM FMS7_TRANSPORTER_PLANT_DTL B WHERE " +
				 " B.TRANSPORTER_CD='"+Entity_Id+"' and A.SEQ_NO=B.SEQ_NO and A.FLAG=B.FLAG)" +
				 " ORDER BY A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("trd"))
			{
				 queryString = "Select DISTINCT(A.SEQ_NO), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR " +
				 " from FMS7_TRADER_PLANT_DTL A " +
				 " WHERE A.TRADER_CD='"+Entity_Id+"' and A.FLAG='T' and  " +
				 " A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM FMS7_TRADER_PLANT_DTL B WHERE " +
				 " B.TRADER_CD='"+Entity_Id+"' and A.SEQ_NO=B.SEQ_NO and A.FLAG=B.FLAG)" +
				 " ORDER BY A.SEQ_NO";
			}
			 
			rset =  stmt.executeQuery(queryString);
			while(rset.next())
			{
			   PLANT_SEQ_NO.add(rset.getString(1)==null?"":rset.getString(1));
			   PLANT_TYP.add(rset.getString(2)==null?"":rset.getString(2));
			   PLANT_NM.add(rset.getString(3)==null?"":rset.getString(3));
			   PLANT_ABR.add(rset.getString(4)==null?"":rset.getString(4));
			}			
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Fetch_Entity_Plant_Details()-->"+e);
			e.printStackTrace();
		}
	}
	
	
	public void Entity_Address_details()
	{
		try
		{
			String queryString1 = "";
			String queryString2 = "";
			String queryString3 = "";
			 
			if(EntityFlag.equalsIgnoreCase("cus"))
			{
				queryString1 = "Select   to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_CUSTOMER_ADDRESS_MST  " +
					 		" WHERE CUSTOMER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R' " +
					 		" and EFF_DT=(select max(eff_dt) from fms7_customer_address_mst where customer_cd='"+Entity_Id+"' AND ADDRESS_TYPE='R')";
				 
				queryString2 = "Select  to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_CUSTOMER_ADDRESS_MST  " +
					 		" WHERE CUSTOMER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C' " +
					 		" and EFF_DT=(select max(eff_dt) from fms7_customer_address_mst where customer_cd='"+Entity_Id+"' AND ADDRESS_TYPE='C')";
								 
				queryString3 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_CUSTOMER_ADDRESS_MST  " +
					 		" WHERE CUSTOMER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B' " +
					 		" and EFF_DT=(select max(eff_dt) from fms7_customer_address_mst where customer_cd='"+Entity_Id+"' AND ADDRESS_TYPE='B')";
			}
			else if(EntityFlag.equalsIgnoreCase("sup"))
			{
				queryString1 = "Select   to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_SUPPLIER_ADDRESS_MST  " +
					 		" WHERE SUPPLIER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_SUPPLIER_ADDRESS_MST where SUPPLIER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R')";
				 
				 queryString2 = "Select   to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_SUPPLIER_ADDRESS_MST  " +
					 		" WHERE SUPPLIER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_SUPPLIER_ADDRESS_MST where SUPPLIER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C')";
							 
				queryString3 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_SUPPLIER_ADDRESS_MST  " +
					 		" WHERE SUPPLIER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_SUPPLIER_ADDRESS_MST where SUPPLIER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B')";
			}
			else if(EntityFlag.equalsIgnoreCase("tran"))
			{
				queryString1 = "Select  to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_TRANSPORTER_ADDRESS_MST " +
					 		" WHERE TRANSPORTER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_TRANSPORTER_ADDRESS_MST where TRANSPORTER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R')";
				 
				 queryString2 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE,ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_TRANSPORTER_ADDRESS_MST  " +
					 		" WHERE TRANSPORTER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_TRANSPORTER_ADDRESS_MST where TRANSPORTER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C')";
			 				 
				 queryString3 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE,ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_TRANSPORTER_ADDRESS_MST  " +
					 		" WHERE TRANSPORTER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_TRANSPORTER_ADDRESS_MST where TRANSPORTER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B')";
			}
			else if(EntityFlag.equalsIgnoreCase("trd"))
			{
				queryString1 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE,COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_TRADER_ADDRESS_MST  " +
					 		" WHERE TRADER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_TRADER_ADDRESS_MST where TRADER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R')";
							 
				queryString2 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_TRADER_ADDRESS_MST  " +
					 		" WHERE TRADER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_TRADER_ADDRESS_MST where TRADER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C')";
			 
				queryString3 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS7_TRADER_ADDRESS_MST  " +
					 		" WHERE TRADER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS7_TRADER_ADDRESS_MST where TRADER_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B')";
			}
			else if(EntityFlag.equalsIgnoreCase("ven"))
			{
				queryString1 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE,COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS8_VENDOR_ADDRESS_MST  " +
					 		" WHERE VENDOR_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS8_VENDOR_ADDRESS_MST where VENDOR_CD='"+Entity_Id+"' AND ADDRESS_TYPE='R')";
							 
				queryString2 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS8_VENDOR_ADDRESS_MST  " +
					 		" WHERE VENDOR_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS8_VENDOR_ADDRESS_MST where VENDOR_CD='"+Entity_Id+"' AND ADDRESS_TYPE='C')";
			 
				queryString3 = "Select to_char(EFF_DT,'dd/mm/yyyy'), " +
					 		" ADDR, CITY, PIN, STATE, ZONE, COUNTRY, " +
					 		" PHONE, MOBILE, ALT_PHONE, " +
					 		" FAX_1, FAX_2, EMAIL, EMP_CD," +
					 		" to_char(ENT_DT,'dd/mm/yyyy'), FLAG " +
					 		" from FMS8_VENDOR_ADDRESS_MST  " +
					 		" WHERE VENDOR_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B' " +
					 		" and EFF_DT=(select max(eff_dt) from FMS8_VENDOR_ADDRESS_MST where VENDOR_CD='"+Entity_Id+"' AND ADDRESS_TYPE='B')";
			}
			
			for(int i=0; i<3; i++)
			{
				if(i==0)
				{ 			
				    rset = stmt.executeQuery(queryString1);
                }
				else if(i==1)
				{
                	rset = stmt.executeQuery(queryString2);
                }
				else if(i==2)
				{
                	rset = stmt.executeQuery(queryString3);
                }
				if(rset.next())
				{
					EFF_DT[i] = rset.getString(1)==null?"":rset.getString(1); 
		    		ADDR[i]=  rset.getString(2)==null?"":rset.getString(2);
		    		CITY[i]=  rset.getString(3)==null?"":rset.getString(3);
		    		PIN[i]=  rset.getString(4)==null?"":rset.getString(4);
		    		STATE[i]=  rset.getString(5)==null?"":rset.getString(5);
		    		ZONE[i]=  rset.getString(6)==null?"":rset.getString(6);
		    		COUNTRY[i]=  rset.getString(7)==null?"":rset.getString(7);
		    		PHONE[i]=  rset.getString(8)==null?"":rset.getString(8);
		    		MOBILE[i]=  rset.getString(9)==null?"":rset.getString(9);
		    		ALT_PHONE[i]= rset.getString(10)==null?"":rset.getString(10);
		    		FAX_1[i]=  rset.getString(11)==null?"":rset.getString(11);
		    		FAX_2[i]= rset.getString(12)==null?"":rset.getString(12);
		    		EMAIL[i]= rset.getString(13)==null?"":rset.getString(13);
				}
				else
				{
					EFF_DT[i] = "";
					ADDR[i] =  "";
					CITY[i] =  "";
					PIN[i]  =  "";
					STATE[i]=  "";
					ZONE[i] = "";
					COUNTRY[i]= "";
					PHONE[i] =  "";
					MOBILE[i]=  "";
					ALT_PHONE[i]= "";
					FAX_1[i]=  "";
					FAX_2[i]=  "";
					EMAIL[i]=  "";
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Entity_master_details()-->"+e);
			e.printStackTrace();
		}
	}
	
	
	public void Entity_Plant_Details()
	{
		try
		{
			if(EntityFlag.equalsIgnoreCase("cus"))
			{
				queryString1 = "Select COUNT(DISTINCT(A.SEQ_NO)) from FMS7_CUSTOMER_PLANT_DTL A"+ 
					" WHERE A.CUSTOMER_CD='"+Entity_Id+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+Entity_Id+"')" +
					" order by A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("sup"))
			{
				queryString1 = "Select COUNT(DISTINCT(A.SEQ_NO)) from FMS7_SUPPLIER_PLANT_DTL A"+ 
				 	" WHERE A.SUPPLIER_CD='"+Entity_Id+"' and"+ 
				 	" A.EFF_DT=(select max(B.eff_dt) from FMS7_SUPPLIER_PLANT_DTL B where A.seq_no=B.seq_no and B.SUPPLIER_CD='"+Entity_Id+"')" +
				 	" order by A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("tran"))
			{
				queryString1 = "Select COUNT(DISTINCT(A.SEQ_NO)) from FMS7_TRANSPORTER_PLANT_DTL A"+ 
					" WHERE A.TRANSPORTER_CD='"+Entity_Id+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS7_TRANSPORTER_PLANT_DTL B where A.seq_no=B.seq_no and B.TRANSPORTER_CD='"+Entity_Id+"')" +
					" order by A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("trd"))
			{
				queryString1 = "Select COUNT(DISTINCT(A.SEQ_NO)) from FMS7_TRADER_PLANT_DTL A"+ 
					" WHERE A.TRADER_CD='"+Entity_Id+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS7_TRADER_PLANT_DTL B where A.seq_no=B.seq_no and B.TRADER_CD='"+Entity_Id+"')" +
					" order by A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("ven"))
			{
				queryString1 = "Select COUNT(DISTINCT(A.SEQ_NO)) from FMS8_VENDOR_PLANT_DTL A"+ 
					" WHERE A.VENDOR_CD='"+Entity_Id+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS8_VENDOR_PLANT_DTL B where A.seq_no=B.seq_no and B.VENDOR_CD='"+Entity_Id+"')" +
					" order by A.SEQ_NO";
			}
			rset = stmt.executeQuery(queryString1);
			if(rset.next()) {
				FillArray(Integer.parseInt(""+rset.getInt(1)));
			}
			
			
			if(EntityFlag.equalsIgnoreCase("cus"))
			{
				queryString1 = "Select DISTINCT(A.SEQ_NO), to_char(A.EFF_DT,'dd/mm/yyyy'), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR, A.PLANT_ADDR,"+  
					" A.PLANT_STATE, A.PLANT_ZONE, A.PLANT_CITY,  A.PLANT_PIN, A.PLANT_SECTOR, A.PLANT_SHORT_ABBR from FMS7_CUSTOMER_PLANT_DTL A"+ 
					" WHERE A.CUSTOMER_CD='"+Entity_Id+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS7_CUSTOMER_PLANT_DTL B where A.seq_no=B.seq_no and B.CUSTOMER_CD='"+Entity_Id+"')" +
					" order by A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("sup"))
			{
				queryString1 = "Select DISTINCT(A.SEQ_NO), to_char(A.EFF_DT,'dd/mm/yyyy'), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR, A.PLANT_ADDR,"+  
				 	" A.PLANT_STATE, A.PLANT_ZONE, A.PLANT_CITY,  A.PLANT_PIN, A.PLANT_SECTOR, A.PLANT_SHORT_ABBR from FMS7_SUPPLIER_PLANT_DTL A"+ 
				 	" WHERE A.SUPPLIER_CD='"+Entity_Id+"' and"+ 
				 	" A.EFF_DT=(select max(B.eff_dt) from FMS7_SUPPLIER_PLANT_DTL B where A.seq_no=B.seq_no and B.SUPPLIER_CD='"+Entity_Id+"')" +
				 	" order by A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("tran"))
			{
				queryString1 = "Select DISTINCT(A.SEQ_NO), to_char(A.EFF_DT,'dd/mm/yyyy'), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR, A.PLANT_ADDR,"+  
					" A.PLANT_STATE, A.PLANT_ZONE, A.PLANT_CITY,  A.PLANT_PIN, A.PLANT_SECTOR, A.PLANT_SHORT_ABBR from FMS7_TRANSPORTER_PLANT_DTL A"+ 
					" WHERE A.TRANSPORTER_CD='"+Entity_Id+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS7_TRANSPORTER_PLANT_DTL B where A.seq_no=B.seq_no and B.TRANSPORTER_CD='"+Entity_Id+"')" +
					" order by A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("trd"))
			{
				queryString1 = "Select DISTINCT(A.SEQ_NO), to_char(A.EFF_DT,'dd/mm/yyyy'), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR, A.PLANT_ADDR,"+  
					" A.PLANT_STATE, A.PLANT_ZONE, A.PLANT_CITY, A.PLANT_PIN, A.PLANT_SECTOR, A.PLANT_SHORT_ABBR from FMS7_TRADER_PLANT_DTL A"+ 
					" WHERE A.TRADER_CD='"+Entity_Id+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS7_TRADER_PLANT_DTL B where A.seq_no=B.seq_no and B.TRADER_CD='"+Entity_Id+"')" +
					" order by A.SEQ_NO";
			}
			else if(EntityFlag.equalsIgnoreCase("ven"))
			{
				queryString1 = "Select DISTINCT(A.SEQ_NO), to_char(A.EFF_DT,'dd/mm/yyyy'), A.PLANT_TYPE, A.PLANT_NAME, A.PLANT_ABBR, A.PLANT_ADDR,"+  
					" A.PLANT_STATE, A.PLANT_ZONE, A.PLANT_CITY, A.PLANT_PIN, A.PLANT_SECTOR, A.PLANT_SHORT_ABBR from FMS8_VENDOR_PLANT_DTL A"+ 
					" WHERE A.VENDOR_CD='"+Entity_Id+"' and"+ 
					" A.EFF_DT=(select max(B.eff_dt) from FMS8_VENDOR_PLANT_DTL B where A.seq_no=B.seq_no and B.VENDOR_CD='"+Entity_Id+"')" +
					" order by A.SEQ_NO";
			}
			
			for(int i=0;i<15;i++)				//CHANGED BY RS28022017 FOR RIL ISD PLANT
			{
//				SEQ_NO[i]  = "";
//			    PEFF_DT[i]  = "";
//			    PLANT_TYPE[i]= "";
//			    PLANT_NAME[i]= "";
//			    PLANT_ABBR[i]= "";
//			    PLANT_ADDR[i]= "";
//			    PLANT_STATE[i]= "";
//			    PLANT_ZONE[i]= "";
//			    PLANT_CITY[i]= "";
//			    PLANT_PIN[i]= "";
//			    PLANT_SECTOR[i]= "";
//			    TAX_STRUCT_CD[i] = ""; 
//			    TAX_STRUCT_DT[i] = ""; 
//			    TAX_STRUCT_DTL[i] = ""; 
//			    TAX_STRUCT_REMARK[i] = "";
//			    SERVICE_TAX_STRUCT_CD[i] = ""; 
//			    SERVICE_TAX_STRUCT_DT[i] = ""; 
//			    SERVICE_TAX_STRUCT_DTL[i] = ""; 
//			    SERVICE_TAX_STRUCT_REMARK[i] = "";
//			    PLANT_STATE_CODE[i] = "";
//			    PLANT_SHORT_ABBR[i] = "";
			}
			
			//System.out.println("SAMIK --> Master Plant Selection Query = "+queryString1);
            rset = stmt.executeQuery(queryString1);
            int j = 0;
            //System.out.println("in while..11111111.."+j);
			while(rset.next())
			{
				SEQ_NO[j] = rset.getString(1)==null?"":rset.getString(1);
			    PEFF_DT[j] = rset.getString(2)==null?"":rset.getString(2);
			    PLANT_TYPE[j] = rset.getString(3)==null?"":rset.getString(3);
			    PLANT_NAME[j] = rset.getString(4)==null?"":rset.getString(4);
			    PLANT_ABBR[j] = rset.getString(5)==null?"":rset.getString(5);
			    PLANT_ADDR[j] = rset.getString(6)==null?"":rset.getString(6);
			    PLANT_STATE[j] = rset.getString(7)==null?"":rset.getString(7);
			    PLANT_ZONE[j] = rset.getString(8)==null?"":rset.getString(8);
			    PLANT_CITY[j] = rset.getString(9)==null?"":rset.getString(9);
			    PLANT_PIN[j] = rset.getString(10)==null?"":rset.getString(10);
			    PLANT_SECTOR[j] = rset.getString(11)==null?"":rset.getString(11);
			    PLANT_SHORT_ABBR[j] = rset.getString(12)==null?"":rset.getString(12);
			    
			  //RS29052017 TO FIND OUT CODE OF SELECTED STATE
			    String query = "SELECT STATE_CODE FROM STATE_MST WHERE UPPER(STATE_NM)=UPPER('"+PLANT_STATE[j]+"') ";
			    rset1 = stmt1.executeQuery(query);
			    if(rset1.next()) {
			    	PLANT_STATE_CODE[j] = rset1.getString(1)==null?"":rset1.getString(1);
			    } 
			    
			    String queryString3 = "SELECT A.tax_struct_cd,TO_CHAR(A.tax_struct_dt,'DD/MM/YYYY')," +
			    					  "A.tax_struct_dtl,A.tax_struct_remark " +
			    					  "FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
			    					  "A.customer_cd="+Entity_Id+" AND A.plant_seq_no="+SEQ_NO[j]+" AND " +
			    					  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
			    					  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no)";
			    
//			    System.out.println("---PLANT TAX QUERY---"+queryString3);
			    
	            rset1 = stmt1.executeQuery(queryString3);
	            if(rset1.next())
	            {
	            	TAX_STRUCT_CD[j] = rset1.getString(1)==null?"":rset1.getString(1); 
				    TAX_STRUCT_DT[j] = rset1.getString(2)==null?"":rset1.getString(2); 
				    TAX_STRUCT_DTL[j] = rset1.getString(3)==null?"":rset1.getString(3); 
				    TAX_STRUCT_REMARK[j] = rset1.getString(4)==null?"":rset1.getString(4);
	            }
	            else
	            {
	            	TAX_STRUCT_CD[j] = ""; 
				    TAX_STRUCT_DT[j] = ""; 
				    TAX_STRUCT_DTL[j] = ""; 
				    TAX_STRUCT_REMARK[j] = "";
	            }
	            
	            queryString3 = "SELECT A.tax_struct_cd,TO_CHAR(A.tax_struct_dt,'DD/MM/YYYY')," +
							   "A.tax_struct_dtl,A.tax_struct_remark " +
							   "FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
							   "A.customer_cd="+Entity_Id+" AND A.plant_seq_no="+SEQ_NO[j]+" AND " +
							   "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
							   "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no)";
				
				//System.out.println(queryString3);
				rset1 = stmt1.executeQuery(queryString3);
				if(rset1.next())
				{
					SERVICE_TAX_STRUCT_CD[j] = rset1.getString(1)==null?"":rset1.getString(1); 
					SERVICE_TAX_STRUCT_DT[j] = rset1.getString(2)==null?"":rset1.getString(2); 
					SERVICE_TAX_STRUCT_DTL[j] = rset1.getString(3)==null?"":rset1.getString(3); 
					SERVICE_TAX_STRUCT_REMARK[j] = rset1.getString(4)==null?"":rset1.getString(4);
				}
				else
				{
					SERVICE_TAX_STRUCT_CD[j] = ""; 
					SERVICE_TAX_STRUCT_DT[j] = ""; 
					SERVICE_TAX_STRUCT_DTL[j] = ""; 
					SERVICE_TAX_STRUCT_REMARK[j] = "";
				}
			    j++;
		    }	
			if(j==0) {
				FillArray(j);
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Entity--->Entity_Plant_Details()-->"+e);
			e.printStackTrace();
		}
	}
	
	public void FillArray(int j) throws Exception {
		try {
			SEQ_NO = new String[j];
			PEFF_DT = new String[j];
			PLANT_TYPE = new String[j];
			PLANT_NAME = new String[j];
			PLANT_ABBR = new String[j];
			PLANT_ADDR = new String[j];
			PLANT_STATE = new String[j];
			PLANT_ZONE = new String[j];
			PLANT_CITY = new String[j];
			PLANT_PIN = new String[j];
			PLANT_SECTOR = new String[j];
			TAX_STRUCT_CD = new String[j];
			TAX_STRUCT_DT = new String[j];
			TAX_STRUCT_DTL = new String[j];
			TAX_STRUCT_REMARK = new String[j];
			SERVICE_TAX_STRUCT_CD = new String[j];
			SERVICE_TAX_STRUCT_DT = new String[j];
			SERVICE_TAX_STRUCT_DTL = new String[j];
			SERVICE_TAX_STRUCT_REMARK = new String[j];
			PLANT_STATE_CODE = new String[j];
			PLANT_SHORT_ABBR = new String[j];
			
			if(j!=0) {
				for(int i=0;i<j;i++) {
					SEQ_NO[i] = new String();
					PEFF_DT[i] = new String();
					PLANT_TYPE[i] = new String();
					PLANT_NAME[i] = new String();
					PLANT_ABBR[i] = new String();
					PLANT_ADDR[i] = new String();
					PLANT_STATE[i] = new String();
					PLANT_ZONE[i] = new String();
					PLANT_CITY[i] = new String();
					PLANT_PIN[i] = new String();
					PLANT_SECTOR[i] = new String();
					TAX_STRUCT_CD[i] = new String();
					TAX_STRUCT_DT[i] = new String();
					TAX_STRUCT_DTL[i] = new String();
					TAX_STRUCT_REMARK[i] = new String();
					SERVICE_TAX_STRUCT_CD[i] = new String();
					SERVICE_TAX_STRUCT_DT[i] = new String();
					SERVICE_TAX_STRUCT_DTL[i] = new String();
					SERVICE_TAX_STRUCT_REMARK[i] = new String();
					PLANT_STATE_CODE[i] = new String();
					PLANT_SHORT_ABBR[i] = new String();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCallFlag(String callFlag){this.callFlag = callFlag;}
	public void setBscode(String bscode){this.bscode = bscode;}
	public void setSearchString(String searchString){this.searchString = searchString;}

	public void setEntityFlag(String entityFlag) {
		EntityFlag = entityFlag;
	}

	public Vector getEntity_abr() {
		return Entity_abr;
	}

	public Vector getEntity_code() {
		return Entity_code;
	}

	public Vector getEntity_name() {
		return Entity_name;
	}

	public Vector getEffect_date() {return Effect_date;}
	
	
	public void setActiveFlag(String activeFlag) {
		ActiveFlag = activeFlag;
	}

	public void setEntity_Id(String entity_Id) {
		Entity_Id = entity_Id;
	}

	public void setPlant_seq_no(String plant_seq_no) {this.plant_seq_no = plant_seq_no;} //Introduced By Samik Shah On 14th July, 2010 ...
	
	public String getADDL_ISSUE_DT() {
		return ADDL_ISSUE_DT;
	}

	public String getADDL_NO() {
		return ADDL_NO;
	}

	public String[] getADDR() {
		return ADDR;
	}

	public String[] getALT_PHONE() {
		return ALT_PHONE;
	}

	public String[] getCITY() {
		return CITY;
	}

	public String[] getCOUNTRY() {
		return COUNTRY;
	}

	public String getCST_TIN_DT() {
		return CST_TIN_DT;
	}

	public String getCST_TIN_NO() {
		return CST_TIN_NO;
	}

	public String[] getEFF_DT() {
		return EFF_DT;
	}

	public String[] getEMAIL() {
		return EMAIL;
	}

	public String getENTITY_ABBR() {
		return ENTITY_ABBR;
	}

	public String getENTITY_CD() {
		return ENTITY_CD;
	}

	public String getENTITY_NAME() {
		return ENTITY_NAME;
	}

	public String[] getFAX_1() {
		return FAX_1;
	}

	public String[] getFAX_2() {
		return FAX_2;
	}

	public String getGST_TIN_DT() {
		return GST_TIN_DT;
	}

	public String getGST_TIN_NO() {
		return GST_TIN_NO;
	}

	public String[] getMOBILE() {
		return MOBILE;
	}

	public String getNOTES() {
		return NOTES;
	}

	public String getPAN_ISSUE_DT() {
		return PAN_ISSUE_DT;
	}

	public String[] getPEFF_DT() {
		return PEFF_DT;
	}

	public String[] getPHONE() {
		return PHONE;
	}

	public String[] getPIN() {
		return PIN;
	}

	public String[] getPLANT_ABBR() {
		return PLANT_ABBR;
	}

	public String[] getPLANT_ADDR() {
		return PLANT_ADDR;
	}

	public String[] getPLANT_CITY() {
		return PLANT_CITY;
	}

	public String[] getPLANT_NAME() {
		return PLANT_NAME;
	}

	public String[] getPLANT_PIN() {
		return PLANT_PIN;
	}

	public String[] getPLANT_SECTOR() {
		return PLANT_SECTOR;
	}

	public String[] getPLANT_STATE() {
		return PLANT_STATE;
	}

	public String[] getPLANT_TYPE() {
		return PLANT_TYPE;
	}

	public String[] getPLANT_ZONE() {
		return PLANT_ZONE;
	}

	public String[] getSEQ_NO() {
		return SEQ_NO;
	}

	public String[] getSTATE() {
		return STATE;
	}

	public String getTAN_ISSUE_DT() {
		return TAN_ISSUE_DT;
	}

	public String getTAN_NO() {
		return TAN_NO;
	}

	public String getWEB_ADDR() {
		return WEB_ADDR;
	}

	public Vector getSECTOR_CD() {
		return SECTOR_CD;
	}

	public Vector getSECTOR_NM() {
		return SECTOR_NM;
	}
	public String[] getZONE() {
		return ZONE;
	}
	public String getPAN_NO() {
		return PAN_NO;
	}

	public Vector getACTIVE_FLAG() {
		return ACTIVE_FLAG;
	}

	public Vector getADDL_ADDR_LINE() {
		return ADDL_ADDR_LINE;
	}

	public Vector getADDR_FLAG() {
		return ADDR_FLAG;
	}

	public Vector getCEFF_DT() {
		return CEFF_DT;
	}

	public Vector getCEMAIL() {
		return CEMAIL;
	}

	public Vector getCFAX_1() {
		return CFAX_1;
	}

	public Vector getCFAX_2() {
		return CFAX_2;
	}

	public Vector getCMOBILE() {
		return CMOBILE;
	}

	public Vector getCONTACT_PERSON() {
		return CONTACT_PERSON;
	}

	public Vector getCPHONE() {
		return CPHONE;
	}

	public Vector getCSEQ_NO() {
		return CSEQ_NO;
	}

	public Vector getFM_FLAG() {
		return FM_FLAG;
	}

	public Vector getINV_FLAG() {
		return INV_FLAG;
	}

	public Vector getNOM_FLAG() {
		return NOM_FLAG;
	}

	public Vector getOTHER_FLAG() {
		return OTHER_FLAG;
	}

	public Vector getPM_FLAG() {
		return PM_FLAG;
	}

	public Vector getPLANT_ABR() {
		return PLANT_ABR;
	}

	public Vector getPLANT_NM() {
		return PLANT_NM;
	}

	public Vector getPLANT_SEQ_NO() {
		return PLANT_SEQ_NO;
	}

	public Vector getPLANT_TYP() {
		return PLANT_TYP;
	}

	public Vector getCONTACT_DESIG() {
		return CONTACT_DESIG;
	}

	public Vector getJT_FLAG() {
		return JT_FLAG;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Vector getCALENDAR_FLAG() {
		return CALENDAR_FLAG;
	}

	public Vector getDESCRIPTION() {
		return DESCRIPTION;
	}

	public Vector getDIARY_FLAG() {
		return DIARY_FLAG;
	}

	public Vector getGIFT_FLAG() {
		return GIFT_FLAG;
	}

	public Vector getLEAFLET_FLAG() {
		return LEAFLET_FLAG;
	}

	public Vector getOTHER_1() {
		return OTHER_1;
	}

	public Vector getOTHER_2() {
		return OTHER_2;
	}

	public int getNew_meter_seq() {
		return new_meter_seq;
	}

	public Vector getMETER_ID() {
		return METER_ID;
	}

	public Vector getMETER_SEQ_NO() {
		return METER_SEQ_NO;
	}

	public Vector getMETER_TYPE() {
		return METER_TYPE;
	}

	public Vector getNOTE() {
		return NOTE;
	}

	public Vector getSPECIFICATION() {
		return SPECIFICATION;
	}

	public Vector getTRANS_CUST_CD() {
		return TRANS_CUST_CD;
	}
	
	public Vector getTRANS_CUST_NAME() {
		return TRANS_CUST_NAME;
	}
	
	public Vector getTRANS_CUST_ABBR() {
		return TRANS_CUST_ABBR;
	}

	public Vector getTRANSPORTER_CD() {
		return TRANSPORTER_CD;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public void setMeterSeqNo(String meterSeqNo) {
		this.meterSeqNo = meterSeqNo;
	}


	public String getMeter_note() {
		return meter_note;
	}


	public String getMeter_speci() {
		return meter_speci;
	}


	public String getTrans_abr() {
		return trans_abr;
	}


	public String getTrans_cd() {
		return trans_cd;
	}


	public Vector getDFM_FLAG() {
		return DFM_FLAG;
	}


	public Vector getDINV_FLAG() {
		return DINV_FLAG;
	}


	public Vector getDJT_FLAG() {
		return DJT_FLAG;
	}


	public Vector getDNOM_FLAG() {
		return DNOM_FLAG;
	}


	public Vector getDOTHER_FLAG() {
		return DOTHER_FLAG;
	}


	public Vector getDPM_FLAG() {
		return DPM_FLAG;
	}


	public String getCREDIT_RATING() {
		return CREDIT_RATING;
	}


	public String getENTITY_EFF_DT() {
		return ENTITY_EFF_DT;
	}

	public String[] getCONT_CONTACT_DEPT() {
		return CONT_CONTACT_DEPT;
	}

	public String[] getCONT_CONTACT_DESIG() {
		return CONT_CONTACT_DESIG;
	}

	public String[] getCONT_CONTACT_PERSON() {
		return CONT_CONTACT_PERSON;
	}

	public String[] getCONT_EFF_DT() {
		return CONT_EFF_DT;
	}

	public String[] getCONT_FAX_1() {
		return CONT_FAX_1;
	}

	public String[] getCONT_FAX_2() {
		return CONT_FAX_2;
	}

	public String[] getCONT_MOBILE() {
		return CONT_MOBILE;
	}

	public String[] getCONT_PHONE() {
		return CONT_PHONE;
	}

	public String[] getEMAIL_1() {
		return EMAIL_1;
	}

	public String[] getEMAIL_2() {
		return EMAIL_2;
	}

	public Vector getCONTACT_TYPE() {
		return CONTACT_TYPE;
	}

	//Following Four String Array Getter Methods Has Been Defined By Samik Shah ... On 29th May, 2010 ...
	public String[] getTAX_STRUCT_CD() {return TAX_STRUCT_CD;}
	public String[] getTAX_STRUCT_DT() {return TAX_STRUCT_DT;}
	public String[] getTAX_STRUCT_DTL() {return TAX_STRUCT_DTL;}
	public String[] getTAX_STRUCT_REMARK() {return TAX_STRUCT_REMARK;}
	
	//Following Four String Array Getter Methods Has Been Defined By Samik Shah ... On 26th August, 2010 ...
	public String[] getSERVICE_TAX_STRUCT_CD() {return SERVICE_TAX_STRUCT_CD;}
	public String[] getSERVICE_TAX_STRUCT_DT() {return SERVICE_TAX_STRUCT_DT;}
	public String[] getSERVICE_TAX_STRUCT_DTL() {return SERVICE_TAX_STRUCT_DTL;}
	public String[] getSERVICE_TAX_STRUCT_REMARK() {return SERVICE_TAX_STRUCT_REMARK;}

	//Following 4 String Getter Methods are defined by Samik Shah On 7th July, 2010 ...
    //Following 4 String Getter Methods are used to fetch master data regarding Cargo BCD Tax Details ...
	public String getCargo_tax_str_cd() {return cargo_tax_str_cd;}
	public String getCargo_tax_app_dt() {return cargo_tax_app_dt;}
	public String getCargo_tax_desc() {return cargo_tax_desc;}
	public String getCargo_tax_remark() {return cargo_tax_remark;}

	//Following 10 String Getter Methods are defined by Samik Shah On 14th July, 2010 ...
    //Following 10 String Getter Methods are used to fetch Plant Related Tax Number's Information ...
	public String getGst_tin_no() {return gst_tin_no;}
	public String getGst_tin_dt() {return gst_tin_dt;}
	public String getCst_tin_no() {return cst_tin_no;}
	public String getCst_tin_dt() {return cst_tin_dt;}
	public String getPan_no() {return pan_no;}
	public String getPan_issue_dt() {return pan_issue_dt;}
	public String getTan_no() {return tan_no;}
	public String getTan_issue_dt() {return tan_issue_dt;}
	public String getService_tax_no() {return service_tax_no;}
	public String getService_tax_dt() {return service_tax_dt;}


	public String getApp_date() {
		return app_date;
	}


	public String getCif_percent() {
		return cif_percent;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}

	//Following (4) Vector Getter Methods Have Been Defined By Samik Shah On 13th January, 2011 ...
	public Vector getEntity_code2() {return Entity_code2;}
	public Vector getEntity_name2() {return Entity_name2;}
	public Vector getEntity_abr2() {return Entity_abr2;}
	public Vector getEntity_type2() {return Entity_type2;}


	public String getGvat_tin_dt() {return gvat_tin_dt;}
	public String getGvat_tin_no() {return gvat_tin_no;}

	//Following (4) Vector Getter Methods Have Been Introduced By Samik Shah On 4th August, 2011 ...
	public Vector getEntity_sun_account_code() {return Entity_sun_account_code;}
	public Vector getPurchase_sun_account_code() {return Purchase_sun_account_code;}
	public Vector getCustom_duty_sun_account_code() {return Custom_duty_sun_account_code;}
	public Vector getShell_group_flag() {return Shell_group_flag;}
	
	//Following (6) Vector Getter Methods Have Been Introduced By Samik Shah On 3rd September, 2011 ...
	public Vector getVSTAT_CD() {return vSTAT_CD;}
	public Vector getVSTAT_NM() {return vSTAT_NM;}
	public Vector getVSTAT_TYPE() {return vSTAT_TYPE;}
	public Vector getVSTAT_NO() {return vSTAT_NO;}
	public Vector getVSTAT_EFF_DT() {return vSTAT_EFF_DT;}
	public Vector getVSTAT_REMARKS() {return vSTAT_REMARKS;}
	
	///SB20130711 ////////////////////////////
	public Vector getEntity_flag() {return Entity_flag;}
	//////////////////////////////////////////


	public Vector getEntity_tot_size() {
		return Entity_tot_size;
	}


	public void setSet_group_id(String set_group_id) {
		this.set_group_id = set_group_id;
	}


	public void setSet_emp_id(String set_emp_id) {
		this.set_emp_id = set_emp_id;
	}


	public HashMap getM1() {
		return m1;
	}


	public List getL1() {
		return l1;
	}


	public String getSet_group_id() {
		return set_group_id;
	}


	public String getCustomer_access_flag() {
		return Customer_access_flag;
	}


	public void setCustomer_access_flag(String customer_access_flag) {
		Customer_access_flag = customer_access_flag;
	}

	public String getGSTIN_NO() {
		return GSTIN_NO;
	}

	public void setGSTIN_NO(String gSTIN_NO) {
		GSTIN_NO = gSTIN_NO;
	}

	public String getGSTIN_DT() {
		return GSTIN_DT;
	}

	public void setGSTIN_DT(String gSTIN_DT) {
		GSTIN_DT = gSTIN_DT;
	}

	public Vector getState_code() {
		return state_code;
	}

	public void setState_code(Vector state_code) {
		this.state_code = state_code;
	}

	public Vector getState_name() {
		return state_name;
	}

	public void setState_name(Vector state_name) {
		this.state_name = state_name;
	}

	public String[] getPLANT_STATE_CODE() {
		return PLANT_STATE_CODE;
	}

	public void setPLANT_STATE_CODE(String[] pLANT_STATE_CODE) {
		PLANT_STATE_CODE = pLANT_STATE_CODE;
	}

	public String[] getPLANT_SHORT_ABBR() {
		return PLANT_SHORT_ABBR;
	}

	public void setPLANT_SHORT_ABBR(String[] pLANT_SHORT_ABBR) {
		PLANT_SHORT_ABBR = pLANT_SHORT_ABBR;
	}
  		
}//End Of Class DataBean_Entity ...