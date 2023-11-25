package com.seipl.hazira.dlng.contract_mgmt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import com.seipl.hazira.dlng.util.RuntimeConf;

//@WebServlet("/servlet/BuyerNomination")
public class Frm_BuyerNomination extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Connection conn;
    Statement stmt,stmt1,stmt2,stmt3,stmt4,stmt5,stmt6;
    ResultSet rset,rset1,rset2,rset3,rset4,rset5,rset6;
    String option="",methodName="",gas_date="",queryString="",queryString1="",queryString2="",queryString3="",queryString4="",queryString5="",queryString6="";
    
    NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("###########0.0000");
	NumberFormat nf3 = new DecimalFormat("###########0.000000000000");
	NumberFormat nf4 = new DecimalFormat("############.##");
	NumberFormat nf5 = new DecimalFormat("############");
	
	
    public Vector vdaily_Buyer_Nom_Name = new Vector();
	public Vector VTransporter_Name = new Vector();
	String LastMeterReadingDt="";
	String LastCustMeterReadingDt="";
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
	Vector ADVANCE_COLLECTION_FLAG = new Vector();
	Vector SALE_PRICE = new Vector();
	
	
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
  	
		Vector qty_nomination = new Vector();
		Vector ALLOCATED_QTYV = new Vector();
		
		String gen_date = ""; //Introduced By Samik Shah On 30th July, 2010 ...
		String from_dt = "";
		String to_dt   ="";
		String week = "";
		String customer_cd = "0";
		String customer_contact_cd = "0";
		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In to Post.............");
		  try
	        {
			  	option=request.getParameter("option")==null?"":request.getParameter("option");
			  	gas_date=request.getParameter("date")==null?"":request.getParameter("date");
			  	
	            InitialContext initialcontext = new InitialContext();
	            Context context = (Context)initialcontext.lookup("java:/comp/env");
	            //SB20091221 DataSource datasource = (DataSource)context.lookup(RuntimeConf.penom_database);
	            DataSource datasource = (DataSource)context.lookup(RuntimeConf.security_database);
	            if(datasource != null)
	            {
	                conn = datasource.getConnection();
	                if(conn != null)
	                {
	                    stmt = conn.createStatement();
	                    stmt1 = conn.createStatement();
	                    stmt2 = conn.createStatement();
	                    stmt3 = conn.createStatement();
	                    stmt4 = conn.createStatement();
	                    stmt5 = conn.createStatement();
	                    stmt6 = conn.createStatement();
	                    
	                    if(option.equals("DAILY_BUYER_NOM")){
	                    	fetchDailyBuyerNomDetails(request,response);
	                    }
	                   
	                }
	            }
	        }
	        catch(Exception exception)
	        {
//	            System.out.println(" Exception in LogicBean_LoginAlerter In " + methodName + " \nquery " + query + " \nException :" + exception);
	            exception.printStackTrace();
	        }finally{
	        	getServletConfig().getServletContext().getRequestDispatcher("/home/index3.jsp?SrvLoad=Y").forward(request, response); 
           	 /*try {
      				rd.forward(request, response);
      			} catch (ServletException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			} catch (IOException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			}*/
	        }
	}
	
	public void clear(){
		VTransporter_Name=new Vector();
		vdaily_Buyer_Nom_Name=new Vector(); 
		master_Transporter_Cd=new Vector(); 
		master_Transporter_Count=new Vector(); 
		master_Transporter_Abbr=new Vector(); 
		master_Transporter_Dcq=new Vector(); 
		daily_Buyer_Nom_Fgsa_No=new Vector(); 
		daily_Buyer_Nom_Mapping_Id=new Vector();
		daily_Buyer_Allocation_Fgsa_Rev_No=new Vector();
		daily_Buyer_Nom_Fgsa_Rev_No=new Vector();
		daily_Buyer_Nom_Sn_No=new Vector();
		daily_Buyer_Allocation_Sn_Rev_No=new Vector();
		daily_Buyer_Nom_Cd=new Vector();
		daily_Buyer_Nom_Abbr=new Vector();
		daily_Buyer_Nom_Dcq=new Vector();
		daily_Buyer_Nom_Plant_Cd=new Vector();
		daily_Buyer_Nom_Plant_Abbr=new Vector();
		daily_Transporter_Nom_Cd=new Vector();
		daily_Transporter_Nom_Abbr=new Vector();
		daily_Buyer_Nom_Balance_Qty=new Vector();
		daily_Buyer_Nom_Mdcq_Qty=new Vector();
		daily_Buyer_regas_cargo_boe_no=new Vector();
		daily_Buyer_regas_cargo_boe_dt=new Vector();
		PRE_APPROVAL=new Vector(); 
		COMM_PRE_APPROVAL=new Vector();
		daily_tax_struct_dtl=new Vector();
		daily_Buyer_Gen_Day_Time=new Vector();
		daily_Buyer_Gen_Day_With_Rev_No=new Vector();
		daily_Buyer_Nom_Plant_Seq_No=new Vector();
		daily_Buyer_Nom_Plant_Seq_Abbr=new Vector();
		daily_Buyer_Nom_Qty_Mmbtu=new Vector();
		daily_Buyer_Nom_Qty_Scm=new Vector();
		master_Transporter_Mmbtu=new Vector();
		master_Transporter_Scm=new Vector();
		daily_Total_Mmbtu="";
		daily_Total_Scm="";
		daily_Buyer_Nom_Type=new Vector();
		qty_nomination=new Vector(); 
		daily_Buyer_Nom_Contract_Type=new Vector(); 
		daily_Buyer_Nom_LC_ADV_Flag=new Vector(); 
		daily_Buyer_Nom_Current_Balance_Amt=new Vector();
		CUST_CD=new Vector();
		sn_ref_no=new Vector();
		daily_Buyer_Nom_Sn_Rev_No=new Vector(); 
	}
	
	public void fetchDailyBuyerNomDetails(HttpServletRequest request, HttpServletResponse response)
	{
	methodName = "fetchDailyBuyerNomDetails()";
	try 
	{
		clear();
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
		queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_abbr,' '), TRANSPORTER_NAME FROM FMS7_TRANSPORTER_MST ORDER BY transporter_abbr";
		//System.out.println("Master Transporter Fetch Query = "+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			master_Transporter_Cd.add(rset.getString(1)==null?"":rset.getString(1));
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
			
//			Following Code Is For SN Based Buyers ...
			queryString1 = "SELECT NVL(A.fgsa_no,'0'),NVL(A.fgsa_rev_no,'0'),NVL(A.sn_no,'0'),NVL(A.sn_rev_no,'0')," +
						   "NVL(A.customer_cd,'0'),NVL(A.dcq,'0'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0')," +
						   "NVL(A.mdcq_percentage,'100'),A.sn_ref_no "+
						   " ,TO_CHAR(A.START_DT,'DD/MM/YYYY'),TO_CHAR(A.END_DT,'DD/MM/YYYY'),"
						   + "NVL(ADVANCE_COLLECTION_FLAG,'N'),RATE " +  //SB20110926
						   "FROM FMS7_SN_MST A, FMS7_SN_TRANSPORTER_MST C WHERE A.start_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY') " +
						   "AND A.end_dt>=TO_DATE('"+gas_date+"','DD/MM/YYYY') AND A.status='Y' AND A.FCC_FLAG='Y' " + 
						   "AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM FMS7_SN_MST B WHERE A.fgsa_no=B.fgsa_no " +
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
						  "FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
						  "SN_NO="+rset1.getString(3)+" " +
						  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
						  "AND FGSA_NO="+rset1.getString(1)+" " +
						  "AND CONTRACT_TYPE='S' " +
						  "AND GAS_DT<TO_DATE('"+date_tomorrow+"','dd/mm/yyyy')" +
						  "";				
					//	System.out.println("SN Already Allocated QTY Query = "+queryString);
						//System.out.println("BUY-NOM:QRY-BN1002AAAA:SELECT:FMS7_DAILY_ALLOCATION_DTL: "+queryString);
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
						
//						JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
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
							vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
						}
						else
						{
							daily_Buyer_Nom_Abbr.add(" ");
							vdaily_Buyer_Nom_Name.add("*"); //SB20181005
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
						  "FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
						  "SN_NO="+rset1.getString(3)+" " +
						  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
						  "AND FGSA_NO="+rset1.getString(1)+" " +
						  "AND CONTRACT_TYPE='L' " +
						  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
						  "";
					     *///JHP20111015
							queryString = "SELECT SUM(QTY_MMBTU) " +
							  "FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
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
						
//						JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
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
							vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
						}
						else
						{
							daily_Buyer_Nom_Abbr.add(" ");
							vdaily_Buyer_Nom_Name.add("*"); //SB20181005
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
						  "FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
						  "SN_NO="+rset1.getString(3)+" " +
						  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
						  "AND FGSA_NO="+rset1.getString(1)+" " +
						  "AND CONTRACT_TYPE='R' " +
						  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
						  "";
						*///JHP20111015
							queryString = "SELECT SUM(QTY_MMBTU) " +
							  "FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
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
//						JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
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
							vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
						}
						else
						{
							daily_Buyer_Nom_Abbr.add(" ");
							vdaily_Buyer_Nom_Name.add("*"); //SB20181005
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
						  "FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
						  "SN_NO="+rset1.getString(3)+" " +
						  "AND CUSTOMER_CD="+rset1.getString(5)+" " +
						  "AND FGSA_NO="+rset1.getString(1)+" " +
						  "AND CONTRACT_TYPE='R' " +
						  "AND GAS_DT<TO_DATE('"+gas_date+"','dd/mm/yyyy')" +
						  "";
						*///JHP20111015
							queryString = "SELECT SUM(QTY_MMBTU) " +
							  "FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
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
//						JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
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
							vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
						}
						else
						{
							daily_Buyer_Nom_Abbr.add(" ");
							vdaily_Buyer_Nom_Name.add("*"); //SB20181005
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
							  "FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
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
//						JHP20111011: Intorduced Nominated Quantity of Seller for Calculation
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
							vdaily_Buyer_Nom_Name.add(rset3.getString(2)); //SB20181005
						}
						else
						{
							daily_Buyer_Nom_Abbr.add(" ");
							vdaily_Buyer_Nom_Name.add("*"); //SB20181005
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
			vdaily_Buyer_Nom_Name.add(rset.getString(2)); //SB20181005
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
			
			queryString = "SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A " +
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
			
			queryString = "SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A, FMS7_SN_CLAUSE_MST C " +
						  "WHERE A.customer_cd="+CUST_CD.elementAt(i)+" AND " +
						  "(A.gas_dt BETWEEN TO_DATE('"+month_start_date+"','dd/mm/yyyy') " +
						  "AND TO_DATE('"+gas_date+"','dd/mm/yyyy')) " +
						  "AND A.contract_type='S' AND A.customer_cd=C.buyer_cd " +
						  "AND A.fgsa_no=C.fgsa_no AND A.sn_no=C.sn_no " +
						  "AND C.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM FMS7_SN_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND A.fgsa_no=B.fgsa_no " +
						  "AND A.sn_no=B.sn_no) AND C.clause_cd=5";
			//System.out.println("SN LC Based Amount Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				month_allocated_amt -= rset.getDouble(1);
				//System.out.println("SN LC Based Amount = "+nf.format(rset.getDouble(1)));
			}
			
			queryString = "SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A " +
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
			
			queryString = "SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A, FMS7_LOA_CLAUSE_MST C " +
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
			
			queryString = "SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A " +
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
			
			queryString = "SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A, FMS7_RE_GAS_CLAUSE_MST C " +
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
			queryString = "SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A " +
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
			
			queryString="SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A, " +
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
			
//			ADDED FOR CN NB20141029
			queryString = "SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A " +
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
			
			
			queryString="SELECT NVL(SUM(A.cost),'0') FROM FMS7_DAILY_ALLOCATION_DTL A, " +
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
		for(int i =0;i<daily_Buyer_Nom_Contract_Type.size();i++)
		{
			double qty = 0;
			double advance_amount = 0;
			
			if(daily_Buyer_Nom_Contract_Type.elementAt(i).equals("SN") || daily_Buyer_Nom_Contract_Type.elementAt(i).equals("LOA"))
			{
				qty_nomination.add(Double.parseDouble(daily_Buyer_Nom_Balance_Qty.elementAt(i)+""));
			} 
			else 
			{
				qty_nomination.add(daily_Buyer_Nom_Balance_Qty.elementAt(i)+"");
			}
		}
		
		/* ****************Set the Bean property values************************* */
		
		/*HttpSession request = request.getSession();*/
		
//		JSONObject json = new JSONObject();
//		json.put("VTransporter_Name", VTransporter_Name);
//		
		request.setAttribute("VTransporter_Name", VTransporter_Name);
		request.setAttribute("vdaily_Buyer_Nom_Name", vdaily_Buyer_Nom_Name);
		request.setAttribute("Transporter_Cd", master_Transporter_Cd);
		request.setAttribute("master_Transporter_Count", master_Transporter_Count);
		request.setAttribute("master_Transporter_Abbr", master_Transporter_Abbr);
		request.setAttribute("master_Transporter_Dcq", master_Transporter_Dcq);
		request.setAttribute("daily_Buyer_Nom_Fgsa_No", daily_Buyer_Nom_Fgsa_No);
		request.setAttribute("daily_Buyer_Nom_Mapping_Id", daily_Buyer_Nom_Mapping_Id);
		request.setAttribute("daily_Buyer_Allocation_Fgsa_Rev_No", daily_Buyer_Allocation_Fgsa_Rev_No);
		request.setAttribute("daily_Buyer_Nom_Fgsa_Rev_No", daily_Buyer_Nom_Fgsa_Rev_No);
		request.setAttribute("daily_Buyer_Nom_Sn_No", daily_Buyer_Nom_Sn_No);
		request.setAttribute("daily_Buyer_Allocation_Sn_Rev_No", daily_Buyer_Allocation_Sn_Rev_No);
		request.setAttribute("daily_Buyer_Nom_Cd", daily_Buyer_Nom_Cd);
		request.setAttribute("daily_Buyer_Nom_Abbr", daily_Buyer_Nom_Abbr);
		request.setAttribute("daily_Buyer_Nom_Dcq", daily_Buyer_Nom_Dcq);
		request.setAttribute("daily_Buyer_Nom_Plant_Cd", daily_Buyer_Nom_Plant_Cd);
		request.setAttribute("daily_Buyer_Nom_Plant_Abbr", daily_Buyer_Nom_Plant_Abbr);
		request.setAttribute("daily_Transporter_Nom_Cd", daily_Transporter_Nom_Cd);
		request.setAttribute("daily_Transporter_Nom_Abbr", daily_Transporter_Nom_Abbr);
		request.setAttribute("daily_Buyer_Nom_Balance_Qty", daily_Buyer_Nom_Balance_Qty);
		request.setAttribute("daily_Buyer_Nom_Mdcq_Qty", daily_Buyer_Nom_Mdcq_Qty);
		request.setAttribute("daily_Buyer_boe_dt", daily_Buyer_regas_cargo_boe_dt);
		request.setAttribute("daily_Buyer_boe_no", daily_Buyer_regas_cargo_boe_no);
		request.setAttribute("PRE_APPROVAL", PRE_APPROVAL);
		request.setAttribute("COMM_PRE_APPROVAL", COMM_PRE_APPROVAL);
		request.setAttribute("daily_tax_struct_dtl", daily_tax_struct_dtl);
		request.setAttribute("daily_Buyer_Gen_Day_Time", daily_Buyer_Gen_Day_Time);
		request.setAttribute("daily_Buyer_Gen_Day_With_Rev_No", daily_Buyer_Gen_Day_With_Rev_No);
		request.setAttribute("daily_Buyer_Nom_Plant_Seq_No", daily_Buyer_Nom_Plant_Seq_No);
		request.setAttribute("daily_Buyer_Nom_Plant_Seq_Abbr", daily_Buyer_Nom_Plant_Seq_Abbr);
		request.setAttribute("daily_Buyer_Nom_Qty_Mmbtu", daily_Buyer_Nom_Qty_Mmbtu);
		request.setAttribute("daily_Buyer_Nom_Qty_Scm", daily_Buyer_Nom_Qty_Scm);
		request.setAttribute("master_Transporter_Mmbtu", master_Transporter_Mmbtu);
		request.setAttribute("master_Transporter_Scm", master_Transporter_Scm);
		request.setAttribute("daily_Total_Mmbtu", daily_Total_Mmbtu);
		request.setAttribute("daily_Total_Scm", daily_Total_Scm);
		request.setAttribute("daily_Buyer_Nom_Type", daily_Buyer_Nom_Type);
		request.setAttribute("qty_nomination", qty_nomination);
		request.setAttribute("daily_Buyer_Nom_Contract_Type", daily_Buyer_Nom_Contract_Type);
		request.setAttribute("daily_Buyer_Nom_LC_ADV_Flag", daily_Buyer_Nom_LC_ADV_Flag);
		request.setAttribute("daily_Buyer_Nom_Current_Balance_Amt", daily_Buyer_Nom_Current_Balance_Amt);
		request.setAttribute("CUST_CD", CUST_CD);
		request.setAttribute("sn_ref_no", sn_ref_no);
		request.setAttribute("daily_Buyer_Nom_Sn_Rev_No", daily_Buyer_Nom_Sn_Rev_No);
		
		
		/*DataBean_DailyBuyer_Nomination dailyNomination=new DataBean_DailyBuyer_Nomination();
		
//		System.out.println("VTransporter_Name***frm"+VTransporter_Name);
		dailyNomination.setVTransporter_Name(VTransporter_Name);
//		System.out.println(dailyNomination.getVTransporter_Name());
		dailyNomination.setVdaily_Buyer_Nom_Name(vdaily_Buyer_Nom_Name);
		dailyNomination.setMaster_Transporter_Cd(master_Transporter_Cd);
		dailyNomination.setMaster_Transporter_Count(master_Transporter_Count);
		dailyNomination.setMaster_Transporter_Abbr(master_Transporter_Abbr);
		dailyNomination.setMaster_Transporter_Dcq(master_Transporter_Dcq);
		dailyNomination.setDaily_Buyer_Nom_Fgsa_No(daily_Buyer_Nom_Fgsa_No);
		dailyNomination.setDaily_Buyer_Nom_Mapping_Id(daily_Buyer_Nom_Mapping_Id);
		dailyNomination.setDaily_Buyer_Nom_Fgsa_Rev_No(daily_Buyer_Allocation_Fgsa_Rev_No);
		dailyNomination.setDaily_Buyer_Nom_Sn_No(daily_Buyer_Nom_Sn_No);
		dailyNomination.setDaily_Buyer_Nom_Sn_Rev_No(daily_Buyer_Allocation_Sn_Rev_No);
		dailyNomination.setDaily_Buyer_Nom_Cd(daily_Buyer_Nom_Cd);
		dailyNomination.setDaily_Buyer_Nom_Abbr(daily_Buyer_Nom_Abbr);
		dailyNomination.setDaily_Buyer_Nom_Dcq(daily_Buyer_Nom_Dcq);
		dailyNomination.setDaily_Buyer_Nom_Plant_Cd(daily_Buyer_Nom_Plant_Cd);
		dailyNomination.setDaily_Buyer_Nom_Plant_Abbr(daily_Buyer_Nom_Plant_Abbr);
		dailyNomination.setDaily_Transporter_Nom_Cd(daily_Transporter_Nom_Cd);
		dailyNomination.setDaily_Transporter_Nom_Abbr(daily_Transporter_Nom_Abbr);
		dailyNomination.setDaily_Buyer_Nom_Balance_Qty(daily_Buyer_Nom_Balance_Qty);
		dailyNomination.setDaily_Buyer_Nom_Mdcq_Qty(daily_Buyer_Nom_Mdcq_Qty);
		dailyNomination.setDaily_Buyer_regas_cargo_boe_dt(daily_Buyer_regas_cargo_boe_dt);
		dailyNomination.setDaily_Buyer_regas_cargo_boe_no(daily_Buyer_regas_cargo_boe_no);
		dailyNomination.setPRE_APPROVAL(PRE_APPROVAL);
		dailyNomination.setCOMM_PRE_APPROVAL(COMM_PRE_APPROVAL);
		dailyNomination.setDaily_tax_struct_dtl(daily_tax_struct_dtl);
		dailyNomination.setDaily_Buyer_Gen_Day_Time(daily_Buyer_Gen_Day_Time);
		dailyNomination.setDaily_Buyer_Gen_Day_With_Rev_No(daily_Buyer_Gen_Day_With_Rev_No);
		dailyNomination.setDaily_Buyer_Nom_Plant_Seq_No(daily_Buyer_Nom_Plant_Seq_No);
		dailyNomination.setDaily_Buyer_Nom_Plant_Seq_Abbr(daily_Buyer_Nom_Plant_Seq_Abbr);
		dailyNomination.setDaily_Buyer_Nom_Qty_Mmbtu(daily_Buyer_Nom_Qty_Mmbtu);
		dailyNomination.setDaily_Buyer_Nom_Qty_Scm(daily_Buyer_Nom_Qty_Scm);
		dailyNomination.setMaster_Transporter_Mmbtu(master_Transporter_Mmbtu);
		dailyNomination.setMaster_Transporter_Scm(master_Transporter_Scm);
		dailyNomination.setDaily_Total_Mmbtu(daily_Total_Mmbtu);
		dailyNomination.setDaily_Total_Scm(daily_Total_Scm);
		dailyNomination.setDaily_Buyer_Nom_Type(daily_Buyer_Nom_Type);
		dailyNomination.setQty_nomination(qty_nomination);
		dailyNomination.setDaily_Buyer_Nom_Contract_Type(daily_Buyer_Nom_Contract_Type);
		dailyNomination.setDaily_Buyer_Nom_LC_ADV_Flag(daily_Buyer_Nom_LC_ADV_Flag);
		dailyNomination.setDaily_Buyer_Nom_Current_Balance_Amt(daily_Buyer_Nom_Current_Balance_Amt);
		dailyNomination.setCUST_CD(CUST_CD);
		dailyNomination.setSn_ref_no(sn_ref_no);*/
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
}//End Of Method fetchDailyBuyerNomDetails() ...


}
