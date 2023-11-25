
package com.seipl.hazira.dlng.contract_master;
import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.*;

import java.util.*;
import java.util.concurrent.Exchanger;
import java.io.File;
import java.sql.*;
import java.text.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

//Code Reviewed by	:  
//CR Date			:  
//CR Status  		:

public class DataBean_Contract_MasterV2
{
	//SUPP_CD SUPP_NM BOE_QTY
    Connection conn; 
    Statement stmtU;
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	Statement stmt3;
	Statement stmt4;
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
	
	//Following NumberFormat Object is defined by Samik Shah ... On 4th March, 2010 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	
	//Following NumberFormat Object is defined by Samik Shah ... On 3rd August, 2010 ...
	NumberFormat nf1 = new DecimalFormat("###########0.0000");
	
	//Following NumberFormat Object is defined by Samik Shah ... On 15th September, 2010 ...
	NumberFormat nf2 = new DecimalFormat("###########0.0000");
	
	//Following NumberFormat Object is defined by Priyanka Sharma ... On 17th March, 2011 ...
	NumberFormat nf3 = new DecimalFormat("###########0");
	
	//Following util Object is defined by Samik Shah ... On 4th March, 2010 ...
	UtilBean util = new UtilBean();
	
	public String set_buyer_name="";
	public String bscode="0";
	public String sign_date="";
	
	//Following String is defined by Achal Pathak ... On 15th July, 2010 ...
	public String contract_capacity ="";
	public String contract_sent_rate ="";
	public String sys_gas = "";
	public String no_cargo = "";
	public String re_gas_tariff="";
	
	//Following (2) String Variables Has Been Defined By Samik Shah On 21st August, 2010 ...
	public String re_gas_no = "0";
	public String no_of_cargo = "1";
	
	//Following (15) RE-GAS Related Vectors Variables Has Been Declared By Samik Shah On 21st August, 2010 ...
	public Vector CARGO_REF_NO = new Vector();
	public Vector EDQ_FROM_DT = new Vector();
	public Vector EDQ_TO_DT = new Vector();
	public Vector ACTUAL_RECPT_DT = new Vector();
	public Vector CONTRACT_START_DT = new Vector();
	public Vector CONTRACT_END_DT = new Vector();
	public Vector SHIP_CD = new Vector();
	public Vector SHIP_NAME = new Vector();
	public Vector ADQ_QTY = new Vector();
	public Vector SYS_USE_GAS = new Vector();
	public Vector QTY_TO_BE_SUPPLY = new Vector();
	public Vector DCQ_QTY = new Vector();
	public Vector RE_GAS_TARIFF = new Vector();
	public Vector QTY_UNIT_CD = new Vector();
	public Vector QTY_UNIT_ABR = new Vector();
	
	//Following Vectors are defined By Samik Shah ... 10th March, 2010 ...
	public Vector transporter_cd = new Vector();
	public Vector transporter_nm = new Vector();
	public Vector FGSA_transporter_cd = new Vector(); //Introduced By Samik Shah On 17th July, 2010 ...
	public Vector FGSA_transporter_abbr = new Vector(); //Introduced By Samik Shah On 17th July, 2010 ...
	public Vector sn_transporter_cd = new Vector();
	public Vector LOA_transporter_cd = new Vector();//BY MANOJ
	public Vector vSN_CONT_Type = new Vector();//BY MANOJ
	public Vector vLOA_CONT_Type = new Vector();//BY MANOJ
	
	public Vector sn_transporter_nm = new Vector();
	public Vector transporter_abbr = new Vector();
	public Vector sn_transporter_abbr = new Vector();
	public Vector LOA_transporter_abbr = new Vector();// BY MANOJ
	
	public String price_per1=new String();
	public String price1=new String();
	public String promise_qty1=new String();
	public String lower_per1=new String();
	public String dcq1=new String();
	public String pndcq1=new String();
	public String mdcq1=new String();
	public String remarks1=new String();
	public String ld_from_dt="";
	public String ld_to_dt="";
	public String price_per2=new String();
	public String price2=new String();
	public String top_per=new String();
	public String actual_oblig=new String();
	public String promise_qty2=new String();
	public String remarks2=new String();
	public String top_from_dt="";
	public String top_to_dt="";
	
	public String makeup_period_per=new String();
	public String rec_period_per=new String();
	public String price_per3=new String();
	public String price3=new String();
	public String remarks3=new String();
	
	//Following Vectors are defined by manoj rana......07 may, 2010
	public Vector vLOA_No= new Vector();
	public Vector vLOA_rev_No= new Vector();
	public Vector vLOA_TCQ= new Vector();
	public Vector vLOA_DCQ= new Vector();
	public Vector vLOA_StartDate= new Vector();
	public Vector vLOA_EndDate= new Vector();
	public Vector vLOA_RATE= new Vector();
	public Vector vLOA_name= new Vector();
	public Vector Tender_no=new Vector();
	public Vector vLOA_verify=new Vector();
	public Vector vLOA_Approve=new Vector();
	
	//Following (2) Vectors Has Been Introduced By Samik Shah On 28th September, 2010 ...
	public Vector vSN_Ref_No = new Vector();
	public Vector vLOA_Ref_No = new Vector();
	
	//Following Vectors are defined by manoj rana......13 may, 2010
	public Vector vInt_cal=new Vector();
	public Vector vInt_cd=new Vector();
	public Vector vExc_rate=new Vector();
	public Vector vExc_cd=new Vector();
											//////15 may,2010
	public String Freq=new String();
	public String Due_Date=new String();
	public String Int_Cal_Cd=new String();
	public String Int_Cal_Sign=new String();
	public String Int_Cal_Per=new String();
	public String Exchng_Rate_Cd=new String();
	public String Exchng_Rate_Cal=new String();
	public String Invoice_Cur_Cd=new String();
	public String Payment_Cur_Cd=new String();
	public String Tax_Struct_Cd=new String();
	public String Exchg_Rate_Note=new String();
	public String Flag=new String(); //SB20111207
	
	//Introduce By Milan Dalsaniya 2011 nov 04 MD20111104
	public String lc_contract_type = "";
	//Following Vectors are defined By Samik Shah ... 10th March, 2010 ...
	public Vector buyer_cd = new Vector();
	public String Buyer_cd = "0";
	public Vector buyer_nm = new Vector();
	public Vector buyer_Credit_Rate = new Vector();
	public Vector buyer_abbr = new Vector();
	public Vector buyer_plant_nm = new Vector();
	public Vector buyer_plant_type = new Vector();
	public Vector buyer_plant_seq_no = new Vector();
	
	//Following Vectors are defined By Samik Shah ... 10th March, 2010 ...
	public Vector clause_cd = new Vector();
	public Vector clause_nm = new Vector();
	public Vector clause_abbr = new Vector();
	public Vector FGSA_clause_cd = new Vector(); //Introduced By Samik Shah On 17th July, 2010 ...
	public Vector FGSA_clause_nm = new Vector(); //Introduced By Samik Shah On 17th July, 2010 ...
	public Vector FGSA_clause_abbr = new Vector(); //Introduced By Samik Shah On 17th July, 2010 ...
	public Vector clause_mst_cd = new Vector();
	public Vector sn_clause_cd = new Vector();
	public Vector LOA_clause_cd = new Vector();//BY MANOJ
	public Vector LOA_clause_nm = new Vector();//BY MANOJ
	public Vector LOA_clause_abbr = new Vector();//BY MANOJ
	
	public Vector sn_clause_nm = new Vector();
	public Vector sn_clause_abbr = new Vector();
	
	//FGSA List
	public Vector FGSA_no = new Vector();
	public Vector vFGSA_REV_NO = new Vector();
	public Vector vSTART_DT = new Vector();
	public Vector vEND_DT = new Vector();
	public Vector vFGSA_BASE = new Vector();
	public Vector vFGSA_TYPE = new Vector();
	public Vector vSTATUS = new Vector();
	public Vector vREV_DT = new Vector(); //Introduced By Samik Shah On 9th July, 2010 ...
	public Vector vSTART_DT2 = new Vector(); //Introduced By Samik Shah On 2nd August, 2010 ...
	public Vector vEND_DT2 = new Vector(); //Introduced By Samik Shah On 2nd August, 2010 ...

	public String FGSA_cd = "0";		
	public String Start_Dt = "";
	public String bName = "";
	public String bAbbr = "";
	public String cust_nm = ""; //SB20111207
	public String cust_abr = ""; //SB20111207
	public String cont_type = ""; //Introduced By Samik Shah On 22nd November, 2010 ...
	
	public Vector vFGSA_Rev_No = new Vector(); //SB20140603
	public Vector vRev_No = new Vector();
	public String SIGNING_DT = "";
	public String START_DT = "";
	public String END_DT = "";
	public String FGSA_BASE = "";
	public String FGSA_TYPE = "";
	public String STATUS = "";
	public String BUYER_NOM = "";
	public String BUYER_MONTH_NOM = "";
	public String BUYER_WEEK_NOM = "";
	public String BUYER_DAILY_NOM = "";
	public String SELLER_NOM="";
	public String SELLER_MONTH_NOM="";
	public String SELLER_WEEK_NOM="";
	public String SELLER_DAILY_NOM="";
	public String DAY_DEF="";
	public String DAY_START_TIME="";
	public String DAY_END_TIME="";
	public String MDCQ="";
	public String MDCQ_PERCENTAGE="";
	public String MEASUREMENT="";
	public String MEAS_STANDARD="";
	public String MEAS_TEMPERATURE="";
	public String PRESSURE_MIN_BAR="";
	public String PRESSURE_MAX_BAR="";
	public String OFF_SPEC_GAS="";
	public String SPEC_GAS_ENERGY_BASE="";
	public String SPEC_GAS_MIN_ENERGY="";
	public String SPEC_GAS_MAX_ENERGY="";
	public String REV_NO ="";
	public String RENEWAL_DT ="";
	public String STORAGE_FROM_DT = ""; //Introduced By Samik Shah On 4th January, 2011 ...
	public String STORAGE_TO_DT = ""; //Introduced By Samik Shah On 4th January, 2011 ...
	
	public String REV_DT =""; //Introduced By Samik Shah On 9th July, 2010 ...
	public String SN_CLOSURE_FLAG = "N"; //Introduced By Samik Shah On 10th August, 2010 ...
	public String SN_CLOSURE_QTY = ""; //Introduced By Samik Shah On 10th August, 2010 ...
	public String SN_CLOSURE_DT = ""; //Introduced By Samik Shah On 10th August, 2010 ...
	
	public String VARIATION_QTY  = "";
	public String VARIATION_MODE = "";
	public String MDCQ_QTY_CD = "";  
	public String OBLIGATION = "";
	public String OBLIG_PERCENTAGE = ""; 
	public String OBLIG_QTY_CD = "";    
	public String ALLOCATED_QTY = ""; //Introduced By Samik Shah On 7th August, 2010 ...
	
	public Vector vTRANSPORTER_CD = new Vector();
	public Vector vPLANT_SEQ_NO = new Vector();
	public Vector vCLAUSE_CD = new Vector();
	public Vector vREMARK = new Vector();
	
	public Vector vTENDER_BASE = new Vector();
	public Vector TENDER_NO = new Vector();	
	public Vector TAX_Str_CD = new Vector();
	public Vector TAX_Remarks = new Vector();
	public Vector TAX_Plant_Nm = new Vector();
	
	public String Tender_BASE="";
	public String SALE_PRICE="";
	public String VERIFY_FLAG = "";
	public String APPROVE_FLAG = "";
	public String DCQ="";
	public String DCQ_MT=""; //SB20200701
	public String MT=""; //SB20200701
	public String TCQ ="";
	public String TENDER_SUBMIT_DT="";
	public String TENDER_CLOSING_DT ="";
	public String tender_cd = "0";	
	
	// VARIDABLES ADDED BY HITESH
	public Vector vLOAStatus = new Vector();
	public Vector vStatus = new Vector();
	public Vector vLC_SEQ_NO = new Vector();
	public Vector vLOALC_SEQ_NO = new Vector();
	public Vector vSN_No = new Vector();
	public Vector vTempSN_No = new Vector();
	public Vector vSN_rev_No = new Vector();
	public Vector vTempSN_rev_No = new Vector();
	public Vector vSN_TCQ = new Vector();
	public Vector vTempSN_TCQ = new Vector();
	public Vector vSN_DCQ = new Vector();
	public Vector vTempSN_DCQ = new Vector();
	public Vector vSN_StartDate = new Vector();
	public Vector vTempSN_StartDate = new Vector();
	public Vector vLC_AMT = new Vector();
	public Vector vLC_FINAL_AMT = new Vector();
	public Vector vTempLC_AMT = new Vector();
	public Vector vLOA_LC_AMT = new Vector();
	public Vector vSN_EndDate = new Vector();
	public Vector vTAX_TYPE = new Vector();
	public Vector vCONT_TYPE = new Vector();
	public Vector vTempSN_EndDate = new Vector(); 
	public Vector vSN_DATEDIFF = new Vector();
	public Vector vTempSN_DATEDIFF = new Vector();
	public Vector vSNTax_Type = new Vector();
	public Vector vSNTax_Rate = new Vector();
	public Vector vTempSNTax_Typ = new Vector();
	public Vector vLOATax_Type = new Vector();
	public Vector vLOATax_Rate = new Vector();
	public Vector vTempLOATax_Type = new Vector();
	public Vector vTempSN_Rate= new Vector();
	public Vector vLOA_DATEDIFF = new Vector();
	public Vector vSN_RATE = new Vector();
	public Vector vBUYER_ABBR = new Vector();
	public Vector vTCQ_UNIT = new Vector();
	public Vector vRATE_UNIT  = new Vector();
	
	//Following (10) Vectors Has Been Defined By Samik Shah On 30th November, 2010 ...
	public Vector vCREDIT_RATING = new Vector();
	public Vector vCREDIT_RATE_EFF_DT = new Vector();
	public Vector vLC_REF_DT = new Vector();
	public Vector vLC_START_DT = new Vector();
	public Vector vLC_END_DT = new Vector();
	public Vector vMANUAL_EXCHG_RATE_FLAG = new Vector();
	public Vector vMANUAL_EXCHG_RATE = new Vector();
	public Vector vUSER_DEFINED_FLAG = new Vector();
	public Vector vUSER_DEFINED_DCQ = new Vector();
	public Vector vLC_REMARKS = new Vector();
	
	//Following 13 Vectors Has Been Defined By Samik Shah On 23rd November, 2010 ...
	public Vector vSN_LC_EXCHG_RATE = new Vector();
	public Vector vSN_LC_BASE_REMARK = new Vector();
	public Vector vSN_LC_BASE = new Vector();
	public Vector vSN_LC_DCQ_TCQ_FLAG = new Vector();
	public Vector vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE = new Vector();
	public Vector vLOA_LC_EXCHG_RATE = new Vector();
	public Vector vLOA_LC_BASE_REMARK = new Vector();
	public Vector vLOA_LC_BASE = new Vector();
	public Vector vLOA_LC_DCQ_TCQ_FLAG = new Vector();
	public Vector vLOA_LC_DCQ_DAYS_TCQ_PERCENTAGE = new Vector();
	public Vector vLOA_buyer_cd = new Vector();
	public Vector vLOA_buyer_nm = new Vector();
	public Vector vLOA_BUYER_ABBR = new Vector();
	
	//For the setting the values
	public String SN_CD = "";
	public String LOA_CD = ""; // MANOJ
	public String LOA_REVNo = "";
	public String var_tcq = "0"; //Introduced By Samik Shah On 4th August, 2010 ...
	public String tcq_sign = "+"; //Introduced By Samik Shah On 6th August, 2010 ...
	public String tcq_approval_dt = ""; //Introduced By Samik Shah On 26th April, 2011 ...
	public String SN_REVNo = "";
	public String FGSA_REVNo = "";	
	public String QUANTITY_UNIT = "";
	public String GCV = "";
	public String NCV = "";
	public String RATE = "";
	public String RATE_UNIT  = "";
	public Vector vPLANT_NAME = new Vector();
	
	public String fgSIGNING_DT 	= "";
	public String fgSTART_DT 		= "";
	public String fgEND_DT 		= ""; 
	public String fgRENEWAL_DT		= "";
	public String fgFGSA_BASE		= ""; 
	public String fgFGSA_TYPE		= ""; 
	public String fgSTATUS		    = "";
	public String fgBUYER_NOM		= ""; 
	public String fgBUYER_MONTH_NOM= "";
	public String fgBUYER_WEEK_NOM	= ""; 
	public String fgBUYER_DAILY_NOM= "";
	public String fgSELLER_NOM		= "";
	public String fgSELLER_MONTH_NOM= ""; 
	public String fgSELLER_WEEK_NOM = "";
	public String fgSELLER_DAILY_NOM= "";
	public String fgDAY_DEF		= "";
	public String fgDAY_START_TIME	= "";
	public String fgDAY_END_TIME 	= "";
	public String fgMDCQ			= "";
	public String fgMDCQ_PERCENTAGE= "";
	public String fgMEASUREMENT	= "";
	public String fgMEAS_STANDARD	= "";
	public String fgMEAS_TEMPERATURE= "";
	public String fgPRESSURE_MIN_BAR= "";
	public String fgPRESSURE_MAX_BAR= "";
	public String fgOFF_SPEC_GAS			= "";
	public String fgSPEC_GAS_ENERGY_BASE	= "";
	public String fgSPEC_GAS_MIN_ENERGY	= "";
	public String fgSPEC_GAS_MAX_ENERGY	= "";
	String PRE_APPROVAL_FGSA="";
	//For tender 
	public String trSIGNING_DT 	= "";
	public String trSTART_DT 		= "";
	public String trEND_DT 		= ""; 
	public String trRENEWAL_DT		= "";
	public String trTENDER_BASE		= ""; 
	
	//String trFGSA_TYPE		= ""; 
	public String trSTATUS		    = "";
	public String trBUYER_NOM		= ""; 
	public String trBUYER_MONTH_NOM= "";
	public String trBUYER_WEEK_NOM	= ""; 
	public String trBUYER_DAILY_NOM= "";
	public String trSELLER_NOM		= "";
	public String trSELLER_MONTH_NOM= ""; 
	public String trSELLER_WEEK_NOM = "";
	public String trSELLER_DAILY_NOM= "";
	public String trDAY_DEF		= "";
	public String trDAY_START_TIME	= "";
	public String trDAY_END_TIME 	= "";
	public String trMDCQ			= "";
	public String trMDCQ_PERCENTAGE= "";
	public String trMEASUREMENT	= "";
	public String trMEAS_STANDARD	= "";
	public String trMEAS_TEMPERATURE= "";
	public String trPRESSURE_MIN_BAR= "";
	public String trPRESSURE_MAX_BAR= "";
	public String trOFF_SPEC_GAS			= "";
	public String trSPEC_GAS_ENERGY_BASE	= "";
	public String trSPEC_GAS_MIN_ENERGY	= "";
	public String trSPEC_GAS_MAX_ENERGY	= "";
	
	public Vector sn_vPLANT_SEQ_NO  = new Vector();
	public Vector LOA_vPLANT_SEQ_NO  = new Vector();//MANOJ	
	public Vector sn_vPLANT_NAME  = new Vector();
	public Vector LOA_vPLANT_NAME  = new Vector();//MANOJ
	
	public String SN_NAME = "";
	public String LOA_NAME = "";//MANOJ
	
	//Following (6) String Variables Has Been Introduced By Samik Shah On 28th September, 2010 ...
	public String SN_REF_NO = "";
	public String LOA_REF_NO = "";
	public String PRE_SN_REF_NO = "";
	public String PRE_LOA_REF_NO = "";
	public String TRANSPORTATION_CHARGE = "";
	public String REMARK = "";
	public String adv_amt = "";
	
	public Vector vSN_name = new Vector();
	public Vector vSN_verify = new Vector();
	public Vector vSN_approve = new Vector();
	
	//Following 4 Vectors Has Been Introduced By Samik Shah On 13th July, 2010 ...
	public Vector sn_Dcq_From_Dt = new Vector();
	public Vector sn_Dcq_To_Dt = new Vector();
	public Vector sn_Dcq_Value = new Vector();
	public Vector sn_Dcq_Remark = new Vector();
	
	//Following 12 Vectors Has Been Introduced By Samik Shah On 3rd August, 2010 ...
	public Vector cargo_Ref_No = new Vector();
	public Vector cargo_Seller_Nm = new Vector();
	public Vector cargo_Window_From_Dt = new Vector();
	public Vector cargo_Window_To_Dt = new Vector();
	public Vector cargo_Conf_Price = new Vector();
	public Vector cargo_Custom_Duty = new Vector();
	public Vector cargo_Cost_Per_Mmbtu = new Vector();
	public Vector cargo_Confirmed_Qty = new Vector();
	public Vector cargo_Available_For_Sale_Qty = new Vector();
	public Vector cargo_Consumed_Qty = new Vector();
	public Vector cargo_SN_Consumed_Qty = new Vector();
	public Vector cargo_Balance_Qty = new Vector();	
	
	//Following Vector Has Been Introduced By Samik Shah On 4th August, 2010 ...
	public Vector cargo_Type_Flag = new Vector();		
	
	//Following 4 String Variables Are Defined By Samik Shah On 4th August, 2010 ...
	public String dummy_cargo_liability_qty = "";
	public String dummy_cargo_surplus_qty = "";
	public String dummy_cargo_flag = "";
	public String dummy_cargo_sn_allocated_qty = "";	
	
	//Following 5 Vectors Has Been Introduced By Achal On 9th August, 2010 ...
	public Vector Tender_clause_cd = new Vector();
	public Vector Tender_clause_nm = new Vector();
	public Vector Tender_clause_abbr = new Vector();
	public Vector Tender_transporter_cd = new Vector();
	public Vector Tender_transporter_abbr = new Vector();	
	
	//Following 4 Vectors Has Been Introduced By Achal On 9th August, 2010 ...
	public Vector loa_Dcq_From_Dt = new Vector();
	public Vector loa_Dcq_To_Dt   = new Vector();
	public Vector loa_Dcq_Value   = new Vector();
	public Vector loa_Dcq_Remark  = new Vector();	
	
	public String DCQ_FLAG = "N"; //Introduced By Achal On 10th August, 2010 ...
	public String re_gas_remark = ""; //Introduced By Samik Shah On 27th August, 2010 ...	
	public Vector NO_OF_CARGO = new Vector(); //Introduced By Samik Shah On 20th August, 2010 ...
	public Vector CUST_CD = new Vector();
	public Vector CUST_NM = new Vector();
	
	
	//Following (12) String Array Variables Has Been Defined By Samik Shah On 9th November, 2010 ...
	public String [] bscode_arr = null;
	public String [] fgsa_no_arr = null;
	public String [] fgsa_rev_no_arr = null;
	public String [] sn_no_arr = null;
	public String [] sn_rev_no_arr = null;
	public String [] tcq_arr = null;
	public String [] dcq_arr = null;
	public String [] original_dcq_arr = null;
	public String [] datediff_arr = null;
	public String [] rate_arr = null;
	public String [] start_dt_arr = null;
	public String [] end_dt_arr = null;
	public String [] cont_type_arr = null;
	
	//Following (6) String Variables Has Been Defined By Samik Shah On 22nd November, 2010 ...
	public String lc_gen_dt = "";
	public String flag_lc_value = "";
	public String flag_dcq_tcq = "";
	public String dcqdays_tcqpercent_value = "0";
	public String exchg_rate = "";
	public String lc_remarks = "";
	
	//Following (6) String Array Variables Has Been Defined By Samik Shah On 23rd November, 2010 ...
	public String [] lc_exchg_rate_arr = null;
	public String [] original_lc_exchg_rate_arr = null;
	public String [] lc_base_remark_arr = null;
	public String [] flag_lc_value_arr = null;
	public String [] flag_dcq_tcq_arr = null;
	public String [] dcqdays_tcqpercent_value_arr = null;
	
	//Following (1) String Array Variable Has Been Defined By Samik Shah On 30th March, 2011 ...
	public String [] tax_rate_arr = null;
	
	//Following (6) String Variables Has Been Defined By Samik Shah On 24th November, 2010 ...
	public String lc_from_dt = "";
	public String lc_to_dt = "";
	public String lc_manual_exchg_rate = "";
	public String lc_manual_exchg_rate_flag = "";
	public String lc_method = "AUTO";
	public String user_defined_dcq = "";
	
	//Following (2) String Variables Has Been Defined By Samik Shah On 30th November, 2010 ...
	public String lc_from_dt_2 = "";
	public String lc_to_dt_2 = "";
	
	
	//Following String Variable Has Been Defined By Samik Shah On 26th November, 2010 ...
	public String total_lc_amount = "";
	
	public String FORMULA_REMARK = ""; //Introduced By Samik Shah 4th December, 2010 ...
	
	public Vector cargo_Reconciled_Qty = new Vector(); //Introduced By Samik Shah On 22nd December, 2010 ...
	
	//Following String Variable Has Been Defined By Priyanka Sharma On 29th December, 2010 ...
	public String BUYER_NOM_CLAUSE = "";
	public String SELLER_NOM_CLAUSE = "";
	public String LC_CLAUSE = "";
	public String BILLING_CLAUSE = "";
	public String LIABILITY_CLAUSE = "";
	public String ADJUST_FLAG = "";
	public String ADVANCE_ADJUST_CLAUSE = "";
	String PRE_APPROVAL="";
	
	public String TENDER_BUYER_NOM_CLAUSE = "";
	public String TENDER_SELLER_NOM_CLAUSE = "";
	public String TENDER_LC_CLAUSE = "";
	public String TENDER_BILLING_CLAUSE = "";
	public String TENDER_LIABILITY_CLAUSE = "";
	
	//Following 3 Vectors Has Been Introduced By Samik Shah On 11th January, 2011 ...
	public Vector fgsa_Deactivation_From_Dt = new Vector();
	public Vector fgsa_Deactivation_To_Dt = new Vector();
	public Vector fgsa_Deactivation_Remark = new Vector();
	
	//Following 3 Vectors Has Been Introduced By Priyanka Sharma On 12th January, 2011 ...
	public Vector V_lc_seq_no = new Vector();
	public Vector V_fin_year = new Vector();
	public Vector CUSTOMER_CD = new Vector();
	
	public Vector V_bank_nm = new Vector();
	public Vector V_bank_cd = new Vector();
	
	//Introduced By Priyanka Sharma On 19th January, 2011 ...
	public String bank_lc_no = "";
	public String amendment_no ="";
	public String fin_year =""; 
	public String lc_seq_no =""; 
	public String customer_cd  ="";
	public String bank_cd ="";
	public String customer_nm ="";
	public String bank_nm  ="";
	public String bank_rating  ="";
	public String rating_eff_date ="";
	public String validity_st_dt ="";
	public String validity_end_dt ="";
	public String mrkt_lc_amt = "";
	public String bank_lc_amt ="";
	public String ship_dt ="";
	public String amendment_dt ="";
	public String amendment_flag ="";
	public String remarks  ="";
	public String lc_fin_year =""; 
	public String bank_name  ="";
	
	public Vector V_FINANCIAL_YEAR = new Vector();
	public Vector V_LC_SEQ_NO = new Vector();
	public Vector V_CUSTOMER_CD = new Vector();
	public Vector V_BANK_CD = new Vector();
	public Vector V_CUSTOMER_NM = new Vector();
	public Vector V_BANK_NM = new Vector();
	public Vector V_BANK_RATING = new Vector();
	public Vector V_RATING_EFF_DATE = new Vector();
	public Vector V_VALIDITY_START_DATE = new Vector();
	public Vector V_VALIDITY_END_DATE = new Vector();
	public Vector V_MRKT_LC_AMOUNT = new Vector();
	public Vector V_BANK_LC_AMOUNT = new Vector();
	public Vector V_LAST_SHIPMENT_DATE = new Vector();
	public Vector V_AMENDMENT_DATE = new Vector();
	public Vector V_AMENDMENT_FLAG = new Vector();
	public Vector V_REMARKS = new Vector();
	public Vector V_BANK_LC_NO = new Vector();
	public Vector V_AMENDMENT_NO = new Vector();
	public Vector V_LC_CONTRACT_CAPACITY = new Vector();
	public Vector V_OTHER_BANK_NM = new Vector();
	
	//Vector Defined by Achal on 19/01/2011
	public Vector TCQ_REQUEST_FLAG = new Vector();
	public Vector TCQ_REQUEST_SIGN = new Vector();
	public Vector TCQ_REQUEST_QTY = new Vector();
	public String tcq_req_flag ="";
	public String tcq_req_close =""; //Defined on 04/05/2011
	
	public String FCC_flag ="";
	public String fcc_flag="";
	public String inv_criteria="";
	
	//Following 2 String Variables have been Defined by Priyanka on 21/03/2011
	public String Deactivation_from_dt = "";
	public String Deactivation_to_dt = "";
	public String FGSA_status = "";
	
	//Following Vectors have been Defined by Priyanka on 23/03/2011
	public Vector REGAS_CLOSURE_REQUEST = new Vector();
	public Vector REGAS_CLOSURE_CLOSE = new Vector();
	public Vector REGAS_CLOSURE_DT = new Vector();
	public Vector REGAS_CLOSURE_QTY = new Vector();
	
	String SN_CLOSURE_REQUEST = "";
	String SN_CLOSURE_CLOSE = "";
	String fcc_by = "";
	String fcc_date = "";
	String LOA_CLOSURE_REQUEST = "";
	String LOA_CLOSURE_CLOSE = "";
	public Vector CARGO_NO_EXIST = new Vector();
	
	//Following Vector Has Been Introduced By Samik Shah On 2nd April, 2011 ...
	public Vector vSN_TAX_RATE = new Vector();
	
	//Following (2) Vectors Has Been Introduced By Samik Shah On 12th May, 2011 ...
	public Vector BOE_QTY = new Vector(); //SB20110927
	public Vector SUPP_CD = new Vector(); //SB20110927
	public Vector SUPP_NM = new Vector(); //SB20110927
	public Vector BOE_NO = new Vector();
	public Vector BOE_DT = new Vector();
	
	// INTRODUCE BY MILAN 21 OCT 2011 MD20111021
	
	public Vector LC_REGAS_NO = new Vector(); 
	public Vector LC_REGAS_STR_DT= new Vector(); 
	public Vector LC_REGAS_END_DT = new Vector(); 
	public Vector LC_REGAS_CAPACITY = new Vector();
	public Vector LC_REGAS_REV_NO = new Vector();
	
	
	public Vector LC_SEQ_NO_REGAS = new Vector(); 
	public Vector LC_FIN_YEAR_REGAS= new Vector(); 
	public Vector LC_FLG_REGAS = new Vector(); 
	Vector LC_CONTRACT_CAP_REGAS = new Vector();
	
	public Vector LC_STR_DT_REGAS = new Vector(); 
	public Vector LC_END_DT_REGAS= new Vector(); 
	public Vector LC_FINAL_AMOUNT_REGAS = new Vector(); 
	public Vector LC_FLAG_REGAS = new Vector(); 
	public Vector LC_REMARKS_REGAS = new Vector(); 
	
//	 INTRODUCE BY MILAN 31 OCT 2011 MD20111031
	
	public Vector LC_FIN_YEAR_REGAS2= new Vector(); 
	public Vector LC_SEQ_NO_REGAS2= new Vector(); 
	public Vector LC_CUSTOMER_CD_REGAS2 = new Vector();
	
	public Vector LC_CREDIT_RATING_REGAS2= new Vector(); 
	public Vector LC_CREDIT_RATING_EFF_DT_REGAS2= new Vector(); 
	public Vector LC_LC_REF_DT_REGAS2 = new Vector(); 
	
	public Vector LC_STR_DT_REGAS2 = new Vector(); 
	public Vector LC_END_DT_REGAS2= new Vector(); 
	
	public Vector LC_MANUAL_EXCHG_RATE_REGAS2 = new Vector();
	public Vector LC_USER_DEFINED_DCQ_REGAS2 = new Vector();
	public Vector LC_CALC_AMT_REGAS2 = new Vector();
	public Vector LC_FINAL_AMT_REGAS2 = new Vector(); 
	public Vector LC_FLAG_REGAS2 = new Vector(); 
	public Vector LC_REMARKS_REGAS2 = new Vector();
	
	public Vector LC_REGAS_NO_REGAS2 = new Vector();
	public Vector LC_CONT_TYPE_REGAS2 = new Vector();
	public Vector LC_REGAS_REV_NO_REGAS2 = new Vector();
	public Vector LC_REGAS_STR_DT_REGAS2 = new Vector(); 
	public Vector LC_REGAS_END_DT_REGAS2 = new Vector(); 
	public Vector LC_REGAS_DURATION_REGAS2 = new Vector(); 
	
	public Vector LC_DCQ_REGAS2 = new Vector(); 
	
	public Vector LC_TCQ_REGAS2 = new Vector(); 
	public Vector LC_EXCHG_RATE_REGAS2 = new Vector(); 
	public Vector LC_CUST_CD_DTL = new Vector();
	
	//Followinf String Variable Introduce By Jaimin on 24th Dec 2011
	String SN_REMARK="";
	
	String ADVANCE_COLLECTION="";
	String ADVANCE_COLLECTION_FLAG="";
	String firm_qty = "0";
	String re_qty = "0";
	String split_tcq_flag = "N";
	
	// ADDED BY RG 20140830
	String adjust_flag="N";
	
////Following (2) String Variables Has Been Defined By Jaimin Patel On 23rd Nov, 2011 ...
	public String frm_dt = "";//JHP
	public String to_dt="";//JHP
	public Vector count=new Vector();//JHP
	public String re_gas_rev_no="";//JHP 

	public Vector QQ_NO=new Vector();
	public Vector QQ_DT=new Vector();
	
	// RG 20140830
	Vector adjust_advance_flag=new Vector();
	Vector SALES_RATE_INR_RE=new Vector();
	String SALES_RATE_INR="";
	
//Folling Vector Introduce By Jaimin on 06th Jan 2012 
	Vector V_bank_rating=new Vector();
	
	///////////////////CHANGE FGSA REV NO //////
	String SnNo=new String();
	String SnRevNo=new String();
	String FgsaNo=new String();
	String FgsaRevNo=new String();
	String SnRefNo=new String();
	String BuyerCd=new String();
	String NewRevNo=new String();

	//NEW REQUIREMENT OF ADVANCE
	String ADJUST_FLAG_FGSA="N";
	String ADJUST_FLAG_SN="N";
	String ADJUST_AMT_SN="";
	String TARIFF_INR_SN="";
	String TARIFF_FLAG_SN="N";
	String DISCOUNT_PRICE_SN="";
	String DISCOUNT_FLAG_SN="N";
	String ADJUST_CUR_SN="";
	
	Vector component_cd=new Vector();
	Vector component_nm=new Vector();
	
	String ADJUST_FLAG_TENDER="N";
	String ADJUST_FLAG_LOA="N";
	String ADJUST_AMT_LOA="";
	String TARIFF_INR_LOA="";
	String TARIFF_FLAG_LOA="N";
	String DISCOUNT_PRICE_LOA="";
	String DISCOUNT_FLAG_LOA="N";
	String ADJUST_CUR_LOA="";
	
	String ADJUST_FLAG_REGAS_MST="N";
	Vector ADJUST_FLAG_REGAS=new Vector();
	Vector ADJUST_AMT_REGAS=new Vector();
	Vector TARIFF_INR_REGAS=new Vector();
	Vector TARIFF_FLAG_REGAS=new Vector();
	Vector DISCOUNT_PRICE_REGAS=new Vector();
	Vector DISCOUNT_FLAG_REGAS=new Vector();
	Vector ADJUST_CUR_REGAS=new Vector();
	
	public Vector VLineFlagEnd = new Vector(); //SB20130808 
	public Vector VLineFlagStart = new Vector(); //SB20130808
	public Vector VSNstatus=new Vector();
	
//	pp20102014
	Vector custcd=new Vector();
	String functionflag="";
	String buyernm="";
	String plantseq1="";
	String planttype1="";
	String plantnm1="";
	Vector fccflag=new Vector();
	
	Vector plantseq=new Vector();
	Vector plnttype=new Vector();
	Vector plntnm=new Vector();
	//RG20200129
	int count_price =0;
	String agreement_base="";
	Vector trans_cd=new Vector();
	Vector plant_cd=new Vector();
	HashMap trans_plant_map=new HashMap();
	HashMap cont_mapp_id=new HashMap();
	HashMap trans_nm=new HashMap();
	HashMap plant_nm=new HashMap();
	HashMap trans_plant_map_cont_st_dt=new HashMap();
	HashMap trans_plant_map_cont_end_dt=new HashMap();
	HashMap trans_plant_map_dcq=new HashMap();
	HashMap trans_plant_map_mdq=new HashMap();
	String agr_no="";
	String agr_rev_no="";
	String cont_rev_no="";
	String cont_no="";
	String cont_agr_type="";
	
	Vector LOA_No=new Vector();
	Vector LOA_rev_No=new Vector();
	Vector buyer_cd_loa=new Vector();
	Vector TENDER_no=new Vector();
	//Vector vLOA_TCQ=new Vector();
	Vector vRev_No_tender=new Vector();
	Vector vloa_TCQ_UNIT=new Vector();
	Vector vLOA_RATE_UNIT=new Vector();
	Vector buyer_nm_LOA=new Vector();
	Vector vBUYER_ABBR_LOA=new Vector();
	Vector vLOA_approve=new Vector();
	Vector TCQ_REQUEST_FLAG_LOA=new Vector();
	Vector TCQ_REQUEST_SIGN_LOA=new Vector();
	Vector TCQ_REQUEST_QTY_LOA=new Vector();
	String cont_type_flag="";
	//
	

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
	    			stmtU = conn.createStatement();
	    			
	    			clearVector();
	    		//SB20200630	checkForTable();
	    			/////////////////////SB20200702////////////////////////////
	    			if(callFlag.equalsIgnoreCase("FLSA_MST")) //SB20200630
					{
	    				/*SB20200630 CHECKFORCOLUMN();
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					Customer_DTL_WITH_ACCESS();
	    				}
	    				else { */
	    					FLSA_Customer_DTL(); 	
	    			//	}
	    				
	    				fetchTransporters();
	    				fetchAllClauses();	    				
	    				if(!bscode.equals("0") && !FGSA_cd.equals(""))
	    				{
	    					fetchFLSAData();
	    					fetchFGSADeactivationPeriod(); 		//Added by Priyanka 210311
	    				}
					}	
	    			else if(callFlag.equalsIgnoreCase("BUYER_LIST"))  //SB20200630
	    			{
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					fetchBuyers_WITH_USER_ACCESS();
	    				} else {
	    					fetchBuyers();
	    				}
	    			}
	    			else if(callFlag.equalsIgnoreCase("FLSA_LIST")) //SB20200630
	    			{
	    				fetchFLSA();
	    			}
	    			else if(callFlag.equalsIgnoreCase("Billing_Dtl"))//SB20111207: //ADDED BY MANOJ
	    			{
	    			    fetch_Int_Cal_List();
	    			    fetch_Exchng_Rate_List();
	    			    fetch_Billing_Dtl(); //SB20200702 //SB20111207:
	    			    fetch_Customer_Name(); //SB20111207:new Fn
	    			    fetch_Tax_Struct();	    			   
	    			}
	    			
	    			else if(callFlag.equalsIgnoreCase("TENDER_MST"))//SB20111207:
					{
	    			/*SB20200703	if(Customer_access_flag.equals("Y"))
	    				{
	    					Customer_DTL_WITH_ACCESS();
	    				} else {*/
	    					TENDER_Customer_DTL();
	    			//	}
	    				
	    				fetchTransporters();
	    				fetchAllClauses();	    				
	    				if(!bscode.equals("") && !bscode.equals("0") && !tender_cd.equals("") && !tender_cd.equals("0"))
	    				{
	    					fetchTENDERData();	    					
	    				}
	    				
					}
	    			else if(callFlag.equalsIgnoreCase("Tender_LIST"))
	    			{
	    				fetchTender();
	    			}
	    			else if(callFlag.equalsIgnoreCase("Tender_Billing_Dtl"))//SB20111207: //ADDED BY MANOJ
	    			{
	    			    fetch_Int_Cal_List();
	    			    fetch_Exchng_Rate_List();
	    			    fetch_Tender_Billing_Dtl();//SB20111207:
	    			    fetch_Customer_Name(); //SB20111207:new Fn
	    			    fetch_Tax_Struct();	    			    
	    			}
	    			else if(callFlag.equalsIgnoreCase("Fetch_FGSA_Tender_LC_Dtl")) //SB20200717
	    			{
	    				fetch_LC_Dtl();
//	    				Fetch_FGSA_Tender_LC_Dtl();
	    			}
	    			///////////////////^SB20200702////////////////////////////
	    			else if(callFlag.equalsIgnoreCase("FGSA_MST2")) //SB20130807
					{
	    				Customer_DTL();
	    				fetchTransporters();
	    				fetchAllClauses();	    				
	    				if(!bscode.equals("0") && !FGSA_cd.equals(""))
	    				{
	    					//SB20200701	fetchFGSAData();
	    					fetchFLSAData(); //SB20200701: Yet to test
	    					fetchFGSADeactivationPeriod(); 		//Added by Priyanka 210311
	    				}
	    				else if(!bscode.equals("0") && FGSA_cd.equals("0")) //SB20130807
		    			{
		    				fetchBuyers();
		    			}
					}	
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_MST"))// Fetch Re_Gas Transporter,All Clauses,Re-Gas
					{
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					Customer_DTL_WITH_ACCESS();
	    				}
	    				else {
	    					Customer_DTL(); 	
	    				}
	    				fetchTransporters();
	    				fetchAllClauses();	    				
	    				if(!bscode.equals("0") && !FGSA_cd.equals(""))// Fetch Re-Gas from ViewList button
	    				{
	    					fetchRE_GASData();
	    				}
					}
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_MST2"))// pp20141106
					{
	    				//Customer_DTL();
	    				fetchTransporters();
	    				fetchAllClauses();	
	    				if(!bscode.equals("0") && !FGSA_cd.equals(""))// Fetch Re-Gas from ViewList button
	    				{
	    					fetchRE_GASData();
	    				}
					}	
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_LIST2"))//pp20141106 //foer new regas
	    			{
    					Customer_DTL();
    					fetchRE_GAS1();
	    			}    
	    			else if(callFlag.equalsIgnoreCase("fetch_name"))	//pp20141031
	    			{
	    				fetch_name2();
	    			}
	    			else if(callFlag.equalsIgnoreCase("FGSA_LIST2")) //SB20130806
	    			{
	    				fetchFGSA2();
	    			}
	    			
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_LIST"))
	    			{
	    				fetchRE_GAS();
	    			}
	    			else if(callFlag.equalsIgnoreCase("FGSA_LIABILITY_CLAUSE"))//ADDED BY MANOJ
	    			{
	    				FGSA_LIABILITY_CLAUSE_DTL();	    			    
	    			}
	    			else if(callFlag.equalsIgnoreCase("Re_Gas_LIABILITY_CLAUSE"))//ADDED BY MANOJ
	    			{
	    				RE_GAS_LIABILITY_CLAUSE_DTL();	    							   
	    			}
	    			else if(callFlag.equalsIgnoreCase("Re_Gas_Billing_Dtl"))//ADDED BY MANOJ
	    			{
	    			    fetch_Int_Cal_List();
	    			    fetch_Exchng_Rate_List();
	    			    fetch_Re_Gas_Billing_Dtl();
	    			    fetch_Tax_Struct();	    			   
	    			}
	    			
	    			
	    			else if(callFlag.equalsIgnoreCase("TENDER_MST2"))
					{
	    				Customer_DTL();
	    				fetchTransporters();
	    				fetchAllClauses();	    				
	    				if(!bscode.equals("") && !bscode.equals("0") && !tender_cd.equals("") && !tender_cd.equals("0"))
	    				{
	    					fetchTENDERData();	    					
	    				}
	    				else if(!bscode.equals("0") && tender_cd.equals("0")) //SB20130807
		    			{
		    				fetchBuyers2();
		    			}
					}
	    			
	    			else if(callFlag.equalsIgnoreCase("Tender_LIST2")) //SB20130910
	    			{
	    				fetchTender2();
	    			}
					else if(callFlag.equalsIgnoreCase("SN_LIST"))
	    			{
	    			    fetch_SN_List();	    
	    			 //   fetch_new_fgsa_rev_no();
	    			}
					else if(callFlag.equalsIgnoreCase("SN_LIST3"))//pp
	    			{
						
						Customer_DTL();    //PP20141104 
	    			    fetch_SN_List3();	//PP20141104    			    
	    			}
					else if(callFlag.equalsIgnoreCase("SN_LIST4")) //pp20141106
	    			{
	    			    fetch_SN_List4();	    			    
	    			}
					else if(callFlag.equalsIgnoreCase("SN_LIST2")) //SB20130810
	    			{
	    			    fetch_SN_List2();	    			    
	    			}
					else if(callFlag.equalsIgnoreCase("TCQ_LIST"))
	    			{
	    			    fetch_TCQ_List();	    			    
	    			}
					else if(callFlag.equalsIgnoreCase("SN_CLOSURE_LIST"))
	    			{
	    			    fetch_SN_Closure_List();	    			    
	    			}
	    			else if(callFlag.equalsIgnoreCase("LOA_CLOSURE_LIST"))
	    			{
	    				fetch_LOA_Closure_List();	    			    
	    			}
					else if(callFlag.equalsIgnoreCase("LC_LIST"))
	    			{
	    			    fetch_LC_List();
	    			}
					else if(callFlag.equalsIgnoreCase("LC_LIST_REGAS"))//ADD BY MILAN 21 OCT 2011
	    			{
						//System.out.println("From init >>>>>>>>>>");
						fetch_LC_RE_GAS_List();
	    			}
					else if(callFlag.equalsIgnoreCase("LC_SEQ_NO_LIST")) 
	    			{
						fetch_LC_SEQ_NO_List();
	    			}
					else if(callFlag.equalsIgnoreCase("LC_SEQ_NO_LIST_REGAS")) //add by Milan 31 OCT 2011
	    			{
						fetch_LC_SEQ_NO_REGAS_List();
	    			}
					else if(callFlag.equalsIgnoreCase("LOA_LIST"))//SB20130906 //ADDED BY MANOJ
	    			{
	    			    //SB20130906 fetch_LOA_List();	 
	    			    fetch_LOA_List2();	 //SB20130906
	    			}
					else if(callFlag.equalsIgnoreCase("LOA_LIST2"))
	    			{
						Customer_DTL(); //PP20141104
	    			    fetch_LOA_List3();	 //PP20141104
	    			}
	    			
	    			
	    			else if(callFlag.equalsIgnoreCase("SN_Billing_Dtl"))//SB20111207: //ADDED BY MANOJ
	    			{
	    			    fetch_Int_Cal_List();
	    			    fetch_Exchng_Rate_List();
	    			    fetch_SN_Billing_Dtl(); //SB20111207:
	    			    fetch_Customer_Name(); //SB20111207:new Fn
	    			    fetch_Tax_Struct();	    			    
	    			}
	    			else if(callFlag.equalsIgnoreCase("LOA_Billing_Dtl")) //SB20111207: //ADDED BY MANOJ
	    			{
	    			    fetch_Int_Cal_List();
	    			    fetch_Exchng_Rate_List();
	    			    fetch_LOA_Billing_Dtl();//SB20111207:
	    			    fetch_Customer_Name(); //SB20111207:new Fn
	    			    fetch_Tax_Struct();	    			    
	    			}
					else if(callFlag.equalsIgnoreCase("SN_MST"))
	    			{
						if(Customer_access_flag.equals("Y"))
	    				{
	    					Customer_DTL_WITH_ACCESS();
	    				} else {
	    					Customer_DTL();
	    				}
	    				fetch_Buyer_details();
	    				fetchAllClausesForSNDefination();
	    				fetchTransporters();
	    				//Data from the SN which are submitted
	    				fetch_SN_details();
	    				fetcg_SN_transporter_details();
	    				fetch_SN_clauses_details();
	    				fetch_SN_Plant_details();
	    				//Data from the FGSA
	    				fetch_governing_clauses();
	    				fetch_FGSA_Plant_details();
	    				fetch_FGSA_Transporter_Details_For_SN();
	    				fetch_FGSA_clauses_details();
	    				fetch_FGSA_advance_clause_details();	//RG20140923
	    				fetch_LC_details("S");
	    				fetch_SN_Billing_Dtl();
	    			//SB20200327	fetch_SN_approval_dtl("S");
	    				fetch_SN_approval_dtl2("S"); //SB20200327
	    			}
	    			else if(callFlag.equalsIgnoreCase("LOA_MST"))//ADDED BY MANOJ
	    			{
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					Customer_DTL_WITH_ACCESS();
	    				} else {
	    					Customer_DTL();
	    				}
	    				
	    				fetch_Buyer_details();
	    				fetchAllClausesForSNDefination();
	    				fetchTransporters();
	    				//Data from the LOA which are submitted
	    				fetch_LOA_details();
	    				fetch_LOA_transporter_details();
	    				fetch_LOA_clauses_details();
	    				fetch_LOA_Plant_details();
	    				//Data from the TENDER
	    				fetch_tender_governing_clauses();
	    				fetch_Tender_Plant_details();
	    				fetch_Tender_Transporter_details();
	    			    fetch_Tender_clauses_details();
	    			    fetch_Tender_advance_clause_details();	//RG20140923
	    			    fetch_LC_details("L");
	    			    fetch_LOA_Billing_Dtl();
	    			   //SB20200328 fetch_SN_approval_dtl("L");
	    			    fetch_SN_approval_dtl2("L"); //SB20200327
	    			}	    			
	    			else if(callFlag.equalsIgnoreCase("Tender_LIABILITY_CLAUSE"))//ADDED BY MANOJ
	    			{
	    				Tender_LIABILITY_CLAUSE_DTL();	    			   
	    			}
	    			else if(callFlag.equalsIgnoreCase("SN_LIABILITY_CLAUSE"))//ADDED BY MANOJ
	    			{
	    				SN_LIABILITY_CLAUSE_DTL();	    			    
	    			}
	    			else if(callFlag.equalsIgnoreCase("LOA_LIABILITY_CLAUSE"))//ADDED BY MANOJ
	    			{
	    				LOA_LIABILITY_CLAUSE_DTL();	    			    
	    			}
	    			else if(callFlag.equalsIgnoreCase("LC_MONITORING"))//ADDED BY MANOJ
	    			{
//	    				System.out.println("Customer_access_flag"+Customer_access_flag);
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					Fetch_LC_BUYERS_DTL_WITH_ACCESS();
	    				} else {
	    					Fetch_LC_BUYERS_DTL();
	    				}
	    				Calculate_LC_Amt();
	    			}
	    			else if(callFlag.equalsIgnoreCase("LC_MONITORING_REGAS"))//ADDED BY MILAN ON 21 OCT 2011
	    			{
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					Fetch_LC_BUYERS_DTL_WITH_ACCESS();
	    				} else {
	    					Fetch_LC_BUYERS_DTL();
	    				}
	    			}
	    			else if(callFlag.equalsIgnoreCase("SN_DCQ_LIST")) //SB20200716 //Introduced By Samik Shah On 13th July, 2010 ...
	    			{
	    				Fetch_SN_DCQ_LIST();
	    			}
	    			else if(callFlag.equalsIgnoreCase("FGSA_DEACTIVATION_LIST")) //Introduced By Samik Shah On 11th January, 2011 ...
	    			{
	    				Fetch_FGSA_DEACTIVATION_LIST();
	    			}
	    			else if(callFlag.equalsIgnoreCase("LOA_DCQ_LIST")) //Introduced By Achal On 9th August, 2010 ...
	    			{
	    				Fetch_LOA_DCQ_LIST();
	    			}
	    			else if(callFlag.equalsIgnoreCase("CARGO_LIST_FOR_TCQ_ADJUSTMENT")) //Introduced By Samik Shah On 3rd August, 2010 ...
	    			{
	    				Fetch_CARGO_LIST_FOR_TCQ_ADJUSTMENT(); //Modified By Samik Shah On 28th April, 2011... (For Year Wise Dummy Cargo Adjustment)
	    			}
	    			else if(callFlag.equalsIgnoreCase("CARGO_LIST_FOR_TCQ_LOA_ADJUSTMENT")) //Introduced By Achal On 22nd March, 2011 ...
	    			{
	    				Fetch_CARGO_LIST_FOR_TCQ_LOA_ADJUSTMENT(); //Modified By Samik Shah On 28th April, 2011... (For Year Wise Dummy Cargo Adjustment)
	    			}
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_CARGO_DTL"))
	    			{
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					Customer_DTL_WITH_ACCESS();
	    				} else {
	    					Customer_DTL();
	    				}
	    				
					    fetchcontarctDate();
	    				Fetch_RE_GAS_CARGO_DTL();
	    			}
	    			
	    			else if(callFlag.equalsIgnoreCase("Fetch_LC_SN_List"))
	    			{
	    				Fetch_LC_SN_List();
	    				View_LC_List();//why two methods
	    			}
	    			else if(callFlag.equalsIgnoreCase("Fetch_LC_SN_List_REGAS")) //Introduce By Milan Dalsaniya 2011 NOV 04 MD
	    			{
	    				Fetch_LC_REGAS_List();
	    				View_LC_REGAS_List();
	    			}
	    			else if(callFlag.equalsIgnoreCase("Fetch_LC_FINANCE_MST"))
	    			{
	    				Fetch_LC_FINANCE_MST();
	    			}
	    			
	    			else if(callFlag.equalsIgnoreCase("Fetch_LC_FINANCE_MST_REGAS"))
	    			{
	    				Fetch_LC_FINANCE_MST_REGAS();
	    			}
	    			
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_CARGO_CLOSURE"))	//PRIYANKA 230311
	    			{
	    				Fetch_RE_GAS_CARGO_DTL_CLOSURE();
	    			}
	    			else if(callFlag.equalsIgnoreCase("RE_GAS_CLOSURE_LIST"))	//PRIYANKA 240311
	    			{
	    				Fetch_RE_GAS_CARGO_DTL_LIST();
	    			}
	    			else if(callFlag.equalsIgnoreCase("ChangeFGSARevNO"))
	    			{
	    				fetch_new_fgsa_rev_no();
	    			}
	    			else if(callFlag.equalsIgnoreCase("AdvanceCollection"))
	    			{
	    				AdvanceCollection();
	    			}
	    			else if(callFlag.equalsIgnoreCase("FetchContractForAdvance"))
	    			{
	    				FetchContractForAdvance();
	    			}
	    			else if(callFlag.equalsIgnoreCase("FetchAllAdvance"))
	    			{
	    				FetchAllAdvance();		
	    			}
	    			else if(callFlag.equalsIgnoreCase("FetchExchangeRateDtls"))
	    			{
	    				FetchExchangeRateDtls();
	    			}
	    			else if(callFlag.equals("fetchLcDetails")) {
	    				fetch_LC_details("DONE");
	    			}
	    			else if(callFlag.equals("ContractDtl")) {
	    				Customer_DTL();
	    				fetch_Contract_Dtl();
	    			}
	    			else if(callFlag.equals("CargoAllocationDtl")) {
	    				Customer_DTL();
	    				fetchCargoDtl(); //SB20200326
	    			}else if(callFlag.equals("fetch_transporter_contract_list")){
	    				fetch_transporter_contract_list();
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
			if(stmt2 != null)
			{
				try
				{
					stmt2.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
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
				}
			}
			if(stmtU != null)
			{
				try
				{
					stmtU.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
	    }
	}	
	
	HttpServletRequest request = null;
	String lc_amt = "", lc_date = "", lc_bank_dtl = "", lc_remark = "", lc_file_nm = "", lc_flag = "", lc_customer_nm = "", fcc_note = "", fcc_apply = "";
	int countLc = 0;
	String filePath = "";
	
	public void fetch_LC_Dtl() throws Exception {
		try {
			String queryString = "select lc_amount,to_char(lc_valid_date,'dd/mm/yyyy'),bank_dtl,lc_file_nm,remark,flag,fcc_note,fcc_apply "
					+ "from DLNG_LC_MST where customer_cd='"+Buyer_cd+"' and flsa_no='"+FGSA_cd+"' "
					+ "and flsa_rev_no='"+FGSA_REVNo+"' and sn_no='"+SN_CD+"' and sn_rev_no='"+SN_REVNo+"' and contract_type='"+cont_type+"' ";
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				lc_amt = rset.getString(1)==null?"":rset.getString(1);
				lc_date = rset.getString(2)==null?"":rset.getString(2);
				lc_bank_dtl = rset.getString(3)==null?"":rset.getString(3);
				lc_file_nm = rset.getString(4)==null?"":rset.getString(4);
				lc_remark = rset.getString(5)==null?"":rset.getString(5);
				lc_flag = rset.getString(6)==null?"":rset.getString(6);
				fcc_note = rset.getString(7)==null?"":rset.getString(7);
				fcc_apply = rset.getString(8)==null?"":rset.getString(8);
				countLc++;
			}
			
			queryString = "select customer_name from fms7_customer_mst where customer_cd='"+Buyer_cd+"' ";
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				lc_customer_nm = rset.getString(1);
			}
			String filepath=request.getRealPath("/lc_files");
			String[] file_bunch_qtr= null;
			File lst_qtr= new File(filepath);
			file_bunch_qtr=lst_qtr.list();
			for ( int j=0;j<file_bunch_qtr.length;j++ )
		    {
				String file=file_bunch_qtr[j];
				String f=file.substring(0,file.indexOf("."));
				if(file.equals(""+lc_file_nm))
				{
					filePath = filepath+"//"+file;
					
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					String url_start = "http://"+server_nm+":"+server_port+context_nm;
					 filePath = filePath.substring(filePath.indexOf("//"));
					 filePath = url_start+"/lc_files/"+file;
				}
		    }	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	boolean approvalRequest = false;
	public void fetch_SN_approval_dtl(String cont_type) throws Exception {
		try { 
			if(cont_type.equals("S")) {
				queryString = "SELECT NVL(FLAG,'') FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE FGSA_NO='"+FGSA_cd+"' "
						+ "AND SN_NO='"+SN_CD+"' AND CUSTOMER_CD='"+bscode+"' AND CONTRACT_TYPE='"+cont_type+"' "
						+ "ORDER BY ENT_DT DESC";
			} else if(cont_type.equals("L")) {
				queryString = "SELECT NVL(FLAG,'') FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE FGSA_NO='"+tender_cd+"' "
						+ "AND SN_NO='"+LOA_CD+"' AND CUSTOMER_CD='"+bscode+"' AND CONTRACT_TYPE='"+cont_type+"' "
						+ "ORDER BY ENT_DT DESC";
			}
			System.out.println("QRY-SN001: FMS8_CARGO_ALLOC_REVISED_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				String flag = rset.getString(1)==null?"A":rset.getString(1);
				if(flag.equals("R")) {
					approvalRequest = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/////////////////////////SB20200327///////////////////////////////
	public void fetch_SN_approval_dtl2(String cont_type) throws Exception {
		try { 
			String start_dt =""; double ori_sale_price=0; double new_sale_price=0;
			if(cont_type.equals("S")) 
			{
				queryString = "SELECT TO_CHAR(START_DT,'DD/MM/YYYY') FROM FMS7_SN_MST WHERE FGSA_NO='"+FGSA_cd+"' "
						+ "AND SN_NO='"+SN_CD+"' AND CUSTOMER_CD='"+bscode+"'";
			//	System.out.println("QRY-SN000: FMS7_SN_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					start_dt = rset.getString(1)==null?"N.A.":rset.getString(1);
				}
				queryString = "SELECT DISTINCT FLAG, NEW_SALE_PRICE, TO_CHAR(NEW_PRICE_EFF_DT,'DD/MM/YYYY'), ORI_SALE_PRICE FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE FGSA_NO='"+FGSA_cd+"' "
						+ "AND SN_NO='"+SN_CD+"' AND CUSTOMER_CD='"+bscode+"' AND CONTRACT_TYPE='"+cont_type+"' ORDER BY TO_CHAR(NEW_PRICE_EFF_DT,'DD/MM/YYYY'),FLAG, NEW_SALE_PRICE ASC";
			} 
			else if(cont_type.equals("L")) 
			{
				queryString = "SELECT TO_CHAR(START_DT,'DD/MM/YYYY') FROM FMS7_LOA_MST WHERE TENDER_NO='"+tender_cd+"' "
						+ "AND LOA_NO='"+LOA_CD+"' AND CUSTOMER_CD='"+bscode+"'";
			//	System.out.println("QRY-SN000: FMS7_LOA_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					start_dt = rset.getString(1)==null?"N.A.":rset.getString(1);
				}
				queryString = "SELECT  FLAG, NEW_SALE_PRICE, TO_CHAR(NEW_PRICE_EFF_DT,'DD/MM/YYYY'), ORI_SALE_PRICE FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE FGSA_NO='"+tender_cd+"' "
						+ "AND SN_NO='"+LOA_CD+"' AND CUSTOMER_CD='"+bscode+"' AND CONTRACT_TYPE='"+cont_type+"' ORDER BY MODIFICATION_SEQ_NO ";//TO_CHAR(NEW_PRICE_EFF_DT,'DD/MM/YYYY'),FLAG, NEW_SALE_PRICE ASC";
			}
			System.out.println("QRY-SN001: FMS8_CARGO_ALLOC_REVISED_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next()) {
				String flag = rset.getString(1)==null?"A":rset.getString(1);
				String effDt = rset.getString(3)==null?"":rset.getString(3);
				ori_sale_price =  rset.getDouble(4);
				new_sale_price =  rset.getDouble(2);
				VNewPrice.add(nf1.format(new_sale_price));
				if(!effDt.equals(""))
					VNewPriceEffDt.add(rset.getString(3));
				else
					VNewPriceEffDt.add(start_dt);
				VNewPriceAprvFlag.add(rset.getString(1));
				if(flag.equals("R")) {
					approvalRequest = true;
				}
			}
			if(VNewPrice.size()>0)
			{
				VNewPrice.add(nf1.format(ori_sale_price));
				VNewPriceEffDt.add(start_dt);
				VNewPriceAprvFlag.add("A");
			}
			/////////////////////To Get the Price Eff Date////////////////////////////
			if(cont_type.equals("S")) 
			{
				queryString = "SELECT TO_CHAR(MAX(TO_DATE(NEW_PRICE_EFF_DT,'DD/MM/YYYY')),'DD/MM/YYYY') FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE FGSA_NO='"+FGSA_cd+"' "
						+ "AND SN_NO='"+SN_CD+"' AND CUSTOMER_CD='"+bscode+"' AND CONTRACT_TYPE='"+cont_type+"' AND FLAG='A' ";
				//System.out.println("QRY-SN001: FMS8_CARGO_ALLOC_REVISED_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					RATE_DATE = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(cont_type.equals("L")) 
			{
				queryString = "SELECT TO_CHAR(MAX(TO_DATE(NEW_PRICE_EFF_DT,'DD/MM/YYYY')),'DD/MM/YYYY') FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE FGSA_NO='"+tender_cd+"' "
						+ "AND SN_NO='"+LOA_CD+"' AND CUSTOMER_CD='"+bscode+"' AND CONTRACT_TYPE='"+cont_type+"' AND FLAG='A' ";
				//System.out.println("QRY-SN001: FMS8_CARGO_ALLOC_REVISED_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					RATE_DATE = rset.getString(1)==null?"":rset.getString(1);
				}
			}
		///////////////////////////////////////////////////////////////////////////
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////////////////
	
	public void checkForTable() throws Exception {
		try {
			int count = 0;
			String query="SELECT COUNT(TNAME) FROM TAB WHERE UPPER(TNAME)='FMS8_CARGO_ALLOC_REVISED_DTL'";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
				if(count==0)
				{
					query="CREATE TABLE FMS8_CARGO_ALLOC_REVISED_DTL ("
							+ "FGSA_NO NUMBER(6,0),"
							+ "FGSA_REV_NO NUMBER(2,0),"
							+ "SN_NO NUMBER(6,0),"
							+ "SN_REV_NO NUMBER(2,0),"
							+ "CONTRACT_TYPE CHAR(1),"
							+ "CUSTOMER_CD NUMBER(6,0),"
							+ "ORI_SALE_PRICE NUMBER(8,4),"
							+ "NEW_SALE_PRICE NUMBER(8,4),"
							+ "ORI_MARGIN NUMBER(6,4),"
							+ "NEW_MARGIN NUMBER(6,4),"
							+ "ORI_AVG_MARGIN NUMBER(6,4),"
							+ "NEW_AVG_MARGIN NUMBER(6,4),"
							+ "ORI_TOT_MARGIN NUMBER(12,4),"
							+ "NEW_TOT_MARGIN NUMBER(12,4),"
							+ "CARGO_REF_NO NUMBER(10,0),"
							+ "ENT_BY NUMBER(6,0),"
							+ "ENT_DT DATE,"
							+ "APPROVE_BY NUMBER(6,0),"
							+ "APPROVE_DT DATE,"
							+ "MODIFICATION_SEQ_NO NUMBER(3,0),"
							+ "FLAG CHAR(1),"
							+ "REMARK VARCHAR2(100 BYTE),"
							+ "ALLOC_QTY NUMBER(14,6),"
							+ "CONSTRAINT PK_FMS8_CAR_ALLOC_DTL PRIMARY KEY(FGSA_NO,FGSA_REV_NO,"
							+ "SN_NO,SN_REV_NO,CONTRACT_TYPE,CUSTOMER_CD,MODIFICATION_SEQ_NO,CARGO_REF_NO))";
//					System.out.println("Creaing Table.."+query);
					stmt.executeUpdate(query);
					conn.commit();
				}
			}
		} catch(Exception e) { 
			e.printStackTrace();
			conn.rollback();
		}
	}
	
	Vector CAR_FGSA_NO = new Vector();
	Vector CAR_FGSA_REV_NO = new Vector();
	Vector CAR_SN_NO = new Vector();
	Vector CAR_SN_REV_NO = new Vector();
	Vector CAR_START_DT = new Vector();
	Vector CAR_END_DT = new Vector();
	Vector CAR_TCQ = new Vector();
	Vector CAR_RATE = new Vector();
	Vector CAR_MAX_MODIFY_SEQ_NO = new Vector();
	Vector CAR_FLAG = new Vector();
	Vector CAR_ENT_BY = new Vector();
	Vector CAR_APPROVE_BY = new Vector();
	Map CAR_REF_NO = new HashMap();
	Map CAR_MARGIN = new HashMap();
	Map CAR_AVG_MARGIN = new HashMap();
	Map CAR_TOT_MARGIN = new HashMap();
	Map CAR_SALE_PRICE = new HashMap();
	Map CAR_ALLOC_QTY = new HashMap();
	Map CAR_COST_PRICE = new HashMap(); //SB20200407
	NumberFormat nfNew = new DecimalFormat("0.00");
	NumberFormat nfNew1 = new DecimalFormat("0.0000");
	Map CAR_NEW_SALE_PRICE = new HashMap();
	Map CAR_NEW_MARGIN = new HashMap();
	Map CAR_NEW_TOT_MARGIN = new HashMap();
	Map CAR_NEW_AVG_MARGIN = new HashMap();
	
	public void fetchCargoDtl() throws Exception {
		try {
			//Fetching Contracts which are not still started....
			if(contract_type.equals("S")) {
				String NextNewPriceDt =""; String NextNomMth="01"; String MaxNomDt ="";
				/*SB20200326 queryString = "SELECT FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),"
						+ "TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'),TCQ,RATE FROM "
						+ "FMS7_SN_MST WHERE CUSTOMER_CD='"+customer_cd_+"' ";
						*/
				queryString = "SELECT FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),"
						+ "TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'),TCQ,RATE, PRICE_REQUEST_FLAG, PRICE_APPROVE_FLAG FROM "
						+ "FMS7_SN_MST A WHERE A.CUSTOMER_CD='"+customer_cd_+"' AND (PRICE_REQUEST_FLAG='Y' OR PRICE_REQUEST_FLAG IS NULL) and END_DT >= SYSDATE "+
						" AND SN_NO NOT IN (SELECT SN_NO  FROM FMS7_SN_MST WHERE CUSTOMER_CD='"+customer_cd_+"' AND PRICE_REQUEST_FLAG='Y' AND PRICE_APPROVE_FLAG='Y' AND END_DT >= SYSDATE )"+
					    " AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
						" AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
				System.out.println("STEP-1:SN "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					////////////////////////////////////////////////////////////
					int	count=0;
					queryString = "SELECT COUNT(GAS_DT) FROM FMS7_DAILY_BUYER_NOM_DTL WHERE CONTRACT_TYPE='"+contract_type+"' "
							+ "AND FGSA_NO = '"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' "
							+ "AND CUSTOMER_CD = '"+customer_cd_+"' ";
					rset1 = stmt1.executeQuery(queryString);
					//	System.out.println("Step-2: "+queryString);		
					if(rset1.next()) {
							 count = rset1.getInt(1); 
					}
					////////////////////////////////////////////////////////////////////
					queryString = "SELECT TO_CHAR(MAX(GAS_DT),'DD/MM/YYYY'), TO_CHAR(MAX(GAS_DT),'DD'), TO_CHAR(MAX(GAS_DT),'MM'), TO_CHAR(MAX(GAS_DT),'YYYY') FROM FMS7_DAILY_BUYER_NOM_DTL WHERE CONTRACT_TYPE='"+contract_type+"' "
							+ "AND FGSA_NO = '"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' "
							+ "AND CUSTOMER_CD = '"+customer_cd_+"' ";
					rset1 = stmt1.executeQuery(queryString);
					System.out.println("Step-2:SN "+queryString);
					if(rset1.next()) {
						/*SB20200326	int count = rset1.getInt(1);*/
						MaxNomDt = rset1.getString(1);
						String MaxNomDay = (rset1.getString(2)==null?"1":rset1.getString(2));
						String MaxNomMth = (rset1.getString(3)==null?"1":rset1.getString(3));
						String MaxNomYr = (rset1.getString(4)==null?"2000":rset1.getString(4));
					//	System.out.println("Step-2: "+MaxNomDay); System.out.println("Step-2: "+MaxNomMth); System.out.println("Step-2: "+MaxNomYr);
						if(Integer.parseInt(MaxNomMth)==12) 
							{NextNomMth="01"; MaxNomYr=""+(Integer.parseInt(MaxNomYr)+1);}
						else if(Integer.parseInt(MaxNomMth)==11 || Integer.parseInt(MaxNomMth)==10 || Integer.parseInt(MaxNomMth)==9) 
							{NextNomMth=""+(Integer.parseInt(MaxNomMth)+1);}
						else if(Integer.parseInt(MaxNomMth)<9) 
							{NextNomMth="0"+(Integer.parseInt(MaxNomMth)+1);}
						
	///////////////////////SB20200407: To find out BILLING FREQ/////////////////////////
							String BillingFreq="F";
							queryString = "SELECT BILLING_FREQ FROM FMS7_SN_BILLING_DTL WHERE CONT_TYPE='"+contract_type+"' "
									+ "AND FGSA_NO = '"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' "
									+ "AND CUSTOMER_CD = '"+customer_cd_+"' ";
						//	System.out.println("Step-2: "+queryString);
							rset1 = stmt1.executeQuery(queryString);
							if(rset1.next()) {
								BillingFreq = rset1.getString(1);
							}
						//	System.out.println("BillingFreq: "+BillingFreq);
							////////////////////////////////////////////////////////////////////////////////////
							if(BillingFreq.equals("F"))
							{						
							if(Integer.parseInt(MaxNomDay)>15)
								NextNewPriceDt = "01/"+NextNomMth+"/"+MaxNomYr;
							else
								NextNewPriceDt = "16/"+MaxNomMth+"/"+MaxNomYr;
							}
							else if(BillingFreq.equals("M") || BillingFreq.equals("W"))
							{	
								NextNewPriceDt = "01/"+NextNomMth+"/"+MaxNomYr;
							}
							else if(BillingFreq.equals("Q"))
							{	
								if(Integer.parseInt(MaxNomMth)==12) 
								{NextNomMth="03"; MaxNomYr=""+(Integer.parseInt(MaxNomYr)+1);}
							else if(Integer.parseInt(MaxNomMth)==9 || Integer.parseInt(MaxNomMth)==8 || Integer.parseInt(MaxNomMth)==7) 
								{NextNomMth=""+(Integer.parseInt(MaxNomMth)+3);}
							else if(Integer.parseInt(MaxNomMth)<7) 
								{NextNomMth="0"+(Integer.parseInt(MaxNomMth)+3);}
								NextNewPriceDt = "01/"+NextNomMth+"/"+MaxNomYr;
							}
						if(MaxNomYr.equals("2000"))
							NextNewPriceDt =rset.getString(6);
					} //SB20200416: added
					//SB20200326	if(count==0) 
						{						
							String key = customer_cd_+":"+rset.getString(1)+":"+rset.getString(3);
							Vector temp_ref_no = new Vector();
							Vector temp_margin = new Vector();
							Vector temp_avg_margin = new Vector();
							Vector temp_tot_margin = new Vector();
							Vector temp_sale_price = new Vector();
							Vector temp_alloc_qty = new Vector();
							Vector temp_cargo_price = new Vector(); //SB20200407
							Vector temp_new_sale_price = new Vector();
							Vector temp_new_margin = new Vector();
							Vector temp_new_tot_margin = new Vector();
							Vector temp_new_avg_margin = new Vector();
							
							int countData = 0;
				/*SB20200329: showing twicw same Cargo 			queryString = "SELECT CARGO_REF_NO,MARGIN,TOTAL_MARGIN,AVG_MARGIN,SALE_PRICE,ALLOC_QTY FROM "
									+ "FMS7_SN_CARGO_DTL WHERE CUSTOMER_CD='"+customer_cd_+"' AND FGSA_NO='"+rset.getString(1)+"' "
									+ "AND SN_NO='"+rset.getString(3)+"' ";
									*/
					/*SB20200329		queryString = "SELECT CARGO_REF_NO,MARGIN,TOTAL_MARGIN,AVG_MARGIN,SALE_PRICE,ALLOC_QTY FROM "
									+ "FMS7_SN_CARGO_DTL WHERE CUSTOMER_CD='"+customer_cd_+"' AND FGSA_NO='"+rset.getString(1)+"' "
									+ "AND SN_NO='"+rset.getString(3)+"' ";
									*/
							queryString = "SELECT CARGO_REF_NO,MARGIN,TOTAL_MARGIN,AVG_MARGIN,SALE_PRICE,ALLOC_QTY, COST_PRICE FROM "
									+ "FMS7_SN_CARGO_DTL WHERE CUSTOMER_CD='"+customer_cd_+"' AND FGSA_NO='"+rset.getString(1)+"' "
									+ "AND SN_NO='"+rset.getString(3)+"' ";//AND SN_REV_NO='"+rset.getString(4)+"' "; //SB20200416 SN_REV_NO not require for this SN_NO ????
						System.out.println("STEP-3:SN..."+queryString);
							rset2 = stmt2.executeQuery(queryString);
							while(rset2.next()) {
								countData++;
								temp_ref_no.add(rset2.getString(1));
								temp_margin.add(rset2.getString(2)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(2))));
								temp_tot_margin.add(rset2.getString(3)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(3))));
								temp_avg_margin.add(rset2.getString(4)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(4))));
								temp_sale_price.add(rset2.getString(5)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(5))));
								temp_alloc_qty.add(rset2.getString(6)==null?"0":nfNew.format(Double.parseDouble(rset2.getString(6))));
								temp_cargo_price.add(rset2.getString(7)==null?"0":rset2.getString(7)); //SB20200407
								temp_new_sale_price.add(""); temp_new_margin.add(""); temp_new_tot_margin.add(""); temp_new_avg_margin.add("");
							}
							if(countData==0) {
								queryString = "SELECT ALLOC_QTY,SALE_PRICE,COST_PRICE FROM "
										+ "FMS7_DUMMY_CARGO_DTL WHERE CUSTOMER_CD='"+customer_cd_+"' AND FGSA_NO='"+rset.getString(1)+"' "
										+ "AND SN_NO='"+rset.getString(3)+"' AND CONTRACT_TYPE='S'";
								System.out.println("STEP-4:SN..."+queryString);
								rset2 = stmt2.executeQuery(queryString);
								while(rset2.next()) {
									countData++;
									temp_ref_no.add("0");
									temp_margin.add(rset2.getString(2)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(2))));
									temp_tot_margin.add(rset2.getString(2)==null?"0.00":nfNew1.format((Double.parseDouble(rset2.getString(2))*Double.parseDouble((rset2.getString(1)==null?"0":rset2.getString(1))))));
									temp_avg_margin.add(rset2.getString(2)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(2))));
									temp_sale_price.add(rset2.getString(2)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(2))));
									temp_alloc_qty.add(rset2.getString(1)==null?"0.00":nfNew.format(Double.parseDouble(rset2.getString(1))));
									temp_cargo_price.add(rset2.getString(3)==null?"0":rset2.getString(3)); //SB20200407
									temp_new_sale_price.add(""); temp_new_margin.add(""); temp_new_tot_margin.add(""); temp_new_avg_margin.add("");
								}
							}
							if(countData>0) {
								CAR_FGSA_NO.add(rset.getString(1));
								CAR_FGSA_REV_NO.add(rset.getString(2));
								CAR_SN_NO.add(rset.getString(3));
								CAR_SN_REV_NO.add(rset.getString(4));
								CAR_START_DT.add(rset.getString(6)==null?"":rset.getString(6));
								CAR_END_DT.add(rset.getString(7)==null?"":rset.getString(7));
								CAR_TCQ.add(rset.getString(8)==null?"":rset.getString(8));
								CAR_RATE.add(rset.getString(9)==null?"":nfNew1.format(Double.parseDouble(rset.getString(9))));
								VPRICE_REQ_FLAG.add(rset.getString(10)==null?"":rset.getString(10)); //SB20200327
								VPRICE_APRV_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); //SB20200327
								VMaxNomDt.add(MaxNomDt==null?"N.A.":MaxNomDt); //SB20200326
								VNextNewPriceDt.add(NextNewPriceDt==null?"N.A.":NextNewPriceDt); //SB20200326
								CAR_REF_NO.put(key,temp_ref_no);
								CAR_MARGIN.put(key,temp_margin);
								CAR_TOT_MARGIN.put(key,temp_tot_margin);
								CAR_AVG_MARGIN.put(key,temp_avg_margin);
								CAR_SALE_PRICE.put(key,temp_sale_price);
								CAR_ALLOC_QTY.put(key,temp_alloc_qty);
								CAR_COST_PRICE.put(key,temp_cargo_price); //SB20200407
								
							/*SB20200326	queryString = "SELECT NVL(MODIFICATION_SEQ_NO,'0'),NVL(FLAG,''),ENT_BY,APPROVE_BY,"
										+ "NEW_SALE_PRICE,NEW_MARGIN,NEW_AVG_MARGIN,NEW_TOT_MARGIN FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE "
										+ "FGSA_NO='"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' AND CUSTOMER_CD = '"+customer_cd_+"' AND "
										+ "CONTRACT_TYPE='"+contract_type+"' ORDER BY MODIFICATION_SEQ_NO DESC";
										*/
								queryString = "SELECT NVL(MODIFICATION_SEQ_NO,'0'),NVL(FLAG,''),ENT_BY,APPROVE_BY,"
										+ "NEW_SALE_PRICE,NEW_MARGIN,NEW_AVG_MARGIN,NEW_TOT_MARGIN, TO_CHAR(NEW_PRICE_EFF_DT,'DD/MM/YYYY') FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE "
										+ "FGSA_NO='"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' AND CUSTOMER_CD = '"+customer_cd_+"' AND "
										+ "CONTRACT_TYPE='"+contract_type+"' ORDER BY MODIFICATION_SEQ_NO DESC";
								System.out.println("STEP-5:SN..."+queryString);
								rset2 = stmt2.executeQuery(queryString);
								if(rset2.next()) {
									int seq_no = rset2.getInt(1);
									CAR_MAX_MODIFY_SEQ_NO.add(seq_no);
									CAR_FLAG.add(rset2.getString(2)==null?"":rset2.getString(2));
									CAR_ENT_BY.add(rset2.getString(3)==null?"":rset2.getString(3));
									CAR_APPROVE_BY.add(rset2.getString(4)==null?"":rset2.getString(4));
									VPriceEffDt.add(rset2.getString(9)==null?"":rset2.getString(9));
									
									for(int k=0;k<temp_ref_no.size();k++) {
										queryString = "SELECT NEW_SALE_PRICE,NEW_MARGIN,NEW_AVG_MARGIN,NEW_TOT_MARGIN "
												+ "FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE "
												+ "FGSA_NO='"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' "
												+ "AND CUSTOMER_CD = '"+customer_cd_+"' AND "
												+ "CONTRACT_TYPE='"+contract_type+"' AND MODIFICATION_SEQ_NO='"+rset2.getInt(1)+"' "
												+ "AND FLAG='"+rset2.getString(2)+"' AND CARGO_REF_NO='"+temp_ref_no.elementAt(k)+"' AND FLAG!='A'";
										System.out.println("STEP-6:SN..."+queryString);
										rset3 = stmt3.executeQuery(queryString);
										if(rset3.next()) {
											temp_new_sale_price.add(k,rset3.getString(1)==null?"0.00":rset3.getString(1));
											temp_new_margin.add(k,rset3.getString(2)==null?"0.00":rset3.getString(2));
											temp_new_avg_margin.add(k,rset3.getString(3)==null?"0.00":rset3.getString(3));
											temp_new_tot_margin.add(k,rset3.getString(4)==null?"0.00":rset3.getString(4));
										} 
									}
								} else {
									CAR_MAX_MODIFY_SEQ_NO.add("0");
									CAR_FLAG.add("");
									CAR_ENT_BY.add("");
									CAR_APPROVE_BY.add("");
									VPriceEffDt.add("");//SB20200327
								}
								CAR_NEW_SALE_PRICE.put(key,temp_new_sale_price);
								CAR_NEW_MARGIN.put(key,temp_new_margin);
								CAR_NEW_TOT_MARGIN.put(key,temp_new_tot_margin);
								CAR_NEW_AVG_MARGIN.put(key,temp_new_avg_margin);
							}
						}
					//SB20200416 }
				}
			} else if(contract_type.equals("L")) {
				String start_dt ="";
				String NextNewPriceDt =""; String NextNomMth="01"; String MaxNomDt ="";
			/*SB20200328	queryString = "SELECT TENDER_NO,TENDER_NO,LOA_NO,LOA_REV_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),"
						+ "TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'),TCQ,RATE FROM "
						+ "FMS7_LOA_MST WHERE CUSTOMER_CD='"+customer_cd_+"' ";
						*/
				queryString = "SELECT TENDER_NO,TENDER_NO,LOA_NO,LOA_REV_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),"
						+ "TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'),TCQ,RATE, PRICE_REQUEST_FLAG, PRICE_APPROVE_FLAG FROM "
						+ "FMS7_LOA_MST A WHERE A.CUSTOMER_CD='"+customer_cd_+"' AND (PRICE_REQUEST_FLAG='Y' OR PRICE_REQUEST_FLAG IS NULL) and END_DT >= SYSDATE "+
						" AND LOA_NO NOT IN (SELECT LOA_NO  FROM FMS7_LOA_MST WHERE CUSTOMER_CD='"+customer_cd_+"' AND PRICE_REQUEST_FLAG='Y' AND PRICE_APPROVE_FLAG='Y' AND END_DT >= SYSDATE)"+
						" AND LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
				System.out.println("STEP-1:LOA.."+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
				/*SB20200327	queryString = "SELECT COUNT(GAS_DT) FROM FMS7_DAILY_BUYER_NOM_DTL WHERE CONTRACT_TYPE='"+contract_type+"' "
							+ "AND FGSA_NO = '"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' "
							+ "AND CUSTOMER_CD = '"+customer_cd_+"' ";
							*/
					queryString = "SELECT TO_CHAR(MAX(GAS_DT),'DD/MM/YYYY'), TO_CHAR(MAX(GAS_DT),'DD'), TO_CHAR(MAX(GAS_DT),'MM'), TO_CHAR(MAX(GAS_DT),'YYYY') FROM FMS7_DAILY_BUYER_NOM_DTL WHERE CONTRACT_TYPE='"+contract_type+"' "
							+ "AND FGSA_NO = '"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' "
							+ "AND CUSTOMER_CD = '"+customer_cd_+"' ";
					System.out.println("Step-2:LOA "+queryString);
					rset1 = stmt1.executeQuery(queryString);
					if(rset1.next()) {
						/*SB20200326	int count = rset1.getInt(1);*/
						MaxNomDt = rset1.getString(1);
						String MaxNomDay = (rset1.getString(2)==null?"1":rset1.getString(2));
						String MaxNomMth = (rset1.getString(3)==null?"1":rset1.getString(3));
						String MaxNomYr = (rset1.getString(4)==null?"2000":rset1.getString(4));
					//	System.out.println("Step-2: "+MaxNomDay); System.out.println("Step-2: "+MaxNomMth); System.out.println("Step-2: "+MaxNomYr);
						if(Integer.parseInt(MaxNomMth)==12) 
							{NextNomMth="01"; MaxNomYr=""+(Integer.parseInt(MaxNomYr)+1);}
						else if(Integer.parseInt(MaxNomMth)==11 || Integer.parseInt(MaxNomMth)==10 || Integer.parseInt(MaxNomMth)==9) 
							{NextNomMth=""+(Integer.parseInt(MaxNomMth)+1);}
						else if(Integer.parseInt(MaxNomMth)<9) 
							{NextNomMth="0"+(Integer.parseInt(MaxNomMth)+1);}
						
						///////////////////////SB20200407: To find out BILLING FREQ/////////////////////////
						String BillingFreq="F";
						queryString = "SELECT BILLING_FREQ FROM FMS7_SN_BILLING_DTL WHERE CONT_TYPE='"+contract_type+"' "
								+ "AND FGSA_NO = '"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' "
								+ "AND CUSTOMER_CD = '"+customer_cd_+"' ";
					//	System.out.println("Step-2: "+queryString);
						rset1 = stmt1.executeQuery(queryString);
						if(rset1.next()) {
							BillingFreq = rset1.getString(1);
						}
					//	System.out.println("BillingFreq: "+BillingFreq);
						////////////////////////////////////////////////////////////////////////////////////
						if(BillingFreq.equals("F"))
						{						
						if(Integer.parseInt(MaxNomDay)>15)
							NextNewPriceDt = "01/"+NextNomMth+"/"+MaxNomYr;
						else
							NextNewPriceDt = "16/"+MaxNomMth+"/"+MaxNomYr;
						}
						else if(BillingFreq.equals("M") || BillingFreq.equals("W"))
						{	
							NextNewPriceDt = "01/"+NextNomMth+"/"+MaxNomYr;
						}
						else if(BillingFreq.equals("Q"))
						{	
							if(Integer.parseInt(MaxNomMth)==12) 
							{NextNomMth="03"; MaxNomYr=""+(Integer.parseInt(MaxNomYr)+1);}
						else if(Integer.parseInt(MaxNomMth)==9 || Integer.parseInt(MaxNomMth)==8 || Integer.parseInt(MaxNomMth)==7) 
							{NextNomMth=""+(Integer.parseInt(MaxNomMth)+3);}
						else if(Integer.parseInt(MaxNomMth)<7) 
							{NextNomMth="0"+(Integer.parseInt(MaxNomMth)+3);}
							NextNewPriceDt = "01/"+NextNomMth+"/"+MaxNomYr;
						}
						if(MaxNomYr.equals("2000"))
							NextNewPriceDt =rset.getString(6);
						int count = 0;
					//SB20200326	if(count==0) 
				//	if(rset1.next()) {
					/*	int count = rset1.getInt(1);*/
					//	if(count==0) 
						{
							CAR_FGSA_NO.add(rset.getString(1));
							CAR_FGSA_REV_NO.add("0");
							CAR_SN_NO.add(rset.getString(3));
							CAR_SN_REV_NO.add(rset.getString(4));
							CAR_START_DT.add(rset.getString(6)==null?"":rset.getString(6));
							CAR_END_DT.add(rset.getString(7)==null?"":rset.getString(7));
							CAR_TCQ.add(rset.getString(8)==null?"":rset.getString(8));
							CAR_RATE.add(rset.getString(9)==null?"":nfNew1.format(Double.parseDouble(rset.getString(9))));
							VPRICE_REQ_FLAG.add(rset.getString(10)==null?"":rset.getString(10)); //SB20200327
							VPRICE_APRV_FLAG.add(rset.getString(11)==null?"":rset.getString(11)); //SB20200327
							start_dt = rset.getString(6);
							String key = customer_cd_+":"+rset.getString(1)+":"+rset.getString(3);
							Vector temp_ref_no = new Vector();
							Vector temp_margin = new Vector();
							Vector temp_avg_margin = new Vector();
							Vector temp_tot_margin = new Vector();
							Vector temp_sale_price = new Vector();
							Vector temp_alloc_qty = new Vector();
							Vector temp_cargo_price = new Vector(); //SB20200407
							Vector temp_new_sale_price = new Vector();
							Vector temp_new_margin = new Vector();
							Vector temp_new_tot_margin = new Vector();
							Vector temp_new_avg_margin = new Vector();
							
							int countData = 0;
					/*SB20200329		queryString = "SELECT CARGO_REF_NO,MARGIN,TOTAL_MARGIN,AVG_MARGIN,SALE_PRICE,ALLOC_QTY FROM "
									+ "FMS7_LOA_CARGO_DTL WHERE CUSTOMER_CD='"+customer_cd_+"' AND TENDER_NO='"+rset.getString(1)+"' "
									+ "AND LOA_NO='"+rset.getString(3)+"' ";
									*/
							queryString = "SELECT CARGO_REF_NO,MARGIN,TOTAL_MARGIN,AVG_MARGIN,SALE_PRICE,ALLOC_QTY, COST_PRICE  FROM "
									+ "FMS7_LOA_CARGO_DTL WHERE CUSTOMER_CD='"+customer_cd_+"' AND TENDER_NO='"+rset.getString(1)+"' "
									+ "AND LOA_NO='"+rset.getString(3)+"' AND LOA_REV_NO='"+rset.getString(4)+"'";
							System.out.println("STEP-3:LOA......"+queryString);
							rset2 = stmt2.executeQuery(queryString);
							while(rset2.next()) {
								count++;
								temp_ref_no.add(rset2.getString(1));
								temp_margin.add(rset2.getString(2)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(2))));
								temp_tot_margin.add(rset2.getString(3)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(3))));
								temp_avg_margin.add(rset2.getString(4)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(4))));
								temp_sale_price.add(rset2.getString(5)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(5))));
								temp_alloc_qty.add(rset2.getString(6)==null?"0":nfNew.format(Double.parseDouble(rset2.getString(6))));
								temp_cargo_price.add(rset2.getString(7)==null?"0":rset2.getString(7)); //SB20200407
								temp_new_sale_price.add(""); temp_new_margin.add(""); temp_new_tot_margin.add(""); temp_new_avg_margin.add("");
							}
							if(countData==0) {
								queryString = "SELECT ALLOC_QTY,SALE_PRICE,COST_PRICE FROM "
										+ "FMS7_DUMMY_CARGO_DTL WHERE CUSTOMER_CD='"+customer_cd_+"' AND FGSA_NO='"+rset.getString(1)+"' "
										+ "AND SN_NO='"+rset.getString(3)+"' AND CONTRACT_TYPE='S'";
								System.out.println("STEP-4:LOA....."+queryString);
								rset2 = stmt2.executeQuery(queryString);
								while(rset2.next()) {
									temp_ref_no.add("0");
									temp_margin.add(rset2.getString(2)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(2))));
									temp_tot_margin.add(rset2.getString(2)==null?"0.00":nfNew1.format((Double.parseDouble(rset2.getString(2))*Double.parseDouble((rset2.getString(1)==null?"0":rset2.getString(1))))));
									temp_avg_margin.add(rset2.getString(2)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(2))));
									temp_sale_price.add(rset2.getString(2)==null?"0.00":nfNew1.format(Double.parseDouble(rset2.getString(2))));
									temp_alloc_qty.add(rset2.getString(1)==null?"0.00":nfNew.format(Double.parseDouble(rset2.getString(1))));
									temp_cargo_price.add(rset2.getString(3)==null?"0":rset2.getString(3)); //SB20200407
									temp_new_sale_price.add(""); temp_new_margin.add(""); temp_new_tot_margin.add(""); temp_new_avg_margin.add("");
								}
							}
							VMaxNomDt.add(MaxNomDt==null?"N.A.":MaxNomDt); //SB20200326
							VNextNewPriceDt.add(NextNewPriceDt); //SB20200326
							CAR_REF_NO.put(key,temp_ref_no);
							CAR_MARGIN.put(key,temp_margin);
							CAR_TOT_MARGIN.put(key,temp_tot_margin);
							CAR_AVG_MARGIN.put(key,temp_avg_margin);
							CAR_SALE_PRICE.put(key,temp_sale_price);
							CAR_ALLOC_QTY.put(key,temp_alloc_qty);
							CAR_COST_PRICE.put(key,temp_cargo_price); //SB20200407
							
							queryString = "SELECT NVL(MODIFICATION_SEQ_NO,'0'),NVL(FLAG,''),ENT_BY,APPROVE_BY,"
									+ "NEW_SALE_PRICE,NEW_MARGIN,NEW_AVG_MARGIN,NEW_TOT_MARGIN, TO_CHAR(NEW_PRICE_EFF_DT,'DD/MM/YYYY') FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE "
									+ "FGSA_NO='"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' AND CUSTOMER_CD = '"+customer_cd_+"' AND "
									+ "CONTRACT_TYPE='"+contract_type+"' ORDER BY MODIFICATION_SEQ_NO DESC";
							System.out.println("STEP-5:LOA......"+queryString);
							rset2 = stmt2.executeQuery(queryString);
							if(rset2.next()) {
								int seq_no = rset2.getInt(1);
								CAR_MAX_MODIFY_SEQ_NO.add(seq_no);
								CAR_FLAG.add(rset2.getString(2)==null?"":rset2.getString(2));
								CAR_ENT_BY.add(rset2.getString(3)==null?"":rset2.getString(3));
								CAR_APPROVE_BY.add(rset2.getString(4)==null?"":rset2.getString(4));
								VPriceEffDt.add(rset2.getString(9)==null?"":rset2.getString(9)); //SB20200327
								
								for(int k=0;k<temp_ref_no.size();k++) {
									queryString = "SELECT NEW_SALE_PRICE,NEW_MARGIN,NEW_AVG_MARGIN,NEW_TOT_MARGIN "
											+ "FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE "
											+ "FGSA_NO='"+rset.getString(1)+"' AND SN_NO = '"+rset.getString(3)+"' "
											+ "AND CUSTOMER_CD = '"+customer_cd_+"' AND "
											+ "CONTRACT_TYPE='"+contract_type+"' AND MODIFICATION_SEQ_NO='"+rset2.getInt(1)+"' "
											+ "AND FLAG='"+rset2.getString(2)+"' AND CARGO_REF_NO='"+temp_ref_no.elementAt(k)+"' AND FLAG!='A'";
									System.out.println("STEP-6:LOA......"+queryString);
									rset3 = stmt3.executeQuery(queryString);
									if(rset3.next()) {
										temp_new_sale_price.add(k,rset3.getString(1)==null?"0.00":rset3.getString(1));
										temp_new_margin.add(k,rset3.getString(2)==null?"0.00":rset3.getString(2));
										temp_new_avg_margin.add(k,rset3.getString(3)==null?"0.00":rset3.getString(3));
										temp_new_tot_margin.add(k,rset3.getString(4)==null?"0.00":rset3.getString(4));
									} 
								}
							} else {
								CAR_MAX_MODIFY_SEQ_NO.add("0");
								CAR_FLAG.add("");
								CAR_ENT_BY.add("");
								CAR_APPROVE_BY.add("");
								VPriceEffDt.add(""); //SB20200327
							}
							CAR_NEW_SALE_PRICE.put(key,temp_new_sale_price);
							CAR_NEW_MARGIN.put(key,temp_new_margin);
							CAR_NEW_TOT_MARGIN.put(key,temp_new_tot_margin);
							CAR_NEW_AVG_MARGIN.put(key,temp_new_avg_margin);
						}
					}
				}
			
			}
			/////////Get Past Price Change Record/////////////////////SB20200330////////////////////////////////
		//	System.out.println("SIZE  :"+CAR_SN_NO.size());
			for(int i=0; i<CAR_SN_NO.size(); i++)
			{
			String start_dt =""; double ori_sale_price=0; double new_sale_price=0;
			start_dt = ""+CAR_START_DT.elementAt(i);
			queryString = "SELECT FLAG, NEW_SALE_PRICE, TO_CHAR(NEW_PRICE_EFF_DT,'DD/MM/YYYY'), ORI_SALE_PRICE FROM FMS8_CARGO_ALLOC_REVISED_DTL WHERE FGSA_NO='"+CAR_FGSA_NO.elementAt(i)+"' "
					+ "AND SN_NO='"+CAR_SN_NO.elementAt(i)+"' AND CUSTOMER_CD='"+customer_cd_+"' AND CONTRACT_TYPE='"+contract_type+"' ORDER BY MODIFICATION_SEQ_NO";// TO_CHAR(NEW_PRICE_EFF_DT,'DD/MM/YYYY'),FLAG, NEW_SALE_PRICE ASC";
			System.out.println("QRY-SN003***: FMS8_CARGO_ALLOC_REVISED_DTL: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next()) {
				String flag = rset.getString(1)==null?"A":rset.getString(1);
				String effDt = rset.getString(3)==null?"":rset.getString(3);
				ori_sale_price =  rset.getDouble(4);
				new_sale_price =  rset.getDouble(2);
				VNewPrice.add(nf1.format(new_sale_price));
				if(!effDt.equals(""))
					VNewPriceEffDt.add(rset.getString(3));
				else
					VNewPriceEffDt.add(start_dt);
				VNewPriceAprvFlag.add(rset.getString(1));
				if(flag.equals("R")) {
						approvalRequest = true;
				}
				VPrice_Change_Dtl.add(CAR_SN_NO.elementAt(i));
			}
			
			if(VNewPrice.size()>0)
			{
				VNewPrice.add(nf1.format(ori_sale_price));
				VNewPriceEffDt.add(start_dt);
				VNewPriceAprvFlag.add("A");
				VPrice_Change_Dtl.add(CAR_SN_NO.elementAt(i));
			}
			
			}
			System.out.println("****: "+VNewPrice ); System.out.println("****: "+VNewPriceEffDt ); System.out.println("****: "+VNewPriceAprvFlag ); System.out.println("****: "+VPrice_Change_Dtl );
			////////////////////////////////////////////////////////////////////////
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	String contract_type = "", customer_cd_ = "";
	Vector APR_FGSA_NO = new Vector();
	Vector APR_FGSA_REV_NO = new Vector();
	Vector APR_SIGNING_DT = new Vector();
	Vector APR_START_DT = new Vector();
	Vector APR_END_DT = new Vector();
	Vector APR_FGSA_BASE = new Vector();
	Vector APR_FGSA_TYPE = new Vector();
	Vector APR_REOPEN_REQ_DT = new Vector();
	Vector APR_REOPEN_APPROVAL_FLAG = new Vector();
	Vector APR_REOPEN_APPROVAL_DT = new Vector();
	Vector APR_CN_NO = new Vector();
	Vector APR_CN_REV_NO = new Vector();
	Vector APR_REOPEN_REMARK = new Vector();
	
	public void fetch_Contract_Dtl() throws Exception {
		try {
			if(contract_type.equals("F")) {
				//Fetching FGSA Contract with Re-Open Request...
				queryString = "SELECT FGSA_NO,REV_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'), "
						+ "TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'), "
						+ "FGSA_BASE,FGSA_TYPE,TO_CHAR(REOPEN_REQUEST_DT,'DD/MM/YYYY'),"
						+ "NVL(REOPEN_APPROVAL_FLAG,''),TO_CHAR(REOPEN_APPROVAL_DT,'DD/MM/YYYY'),NVL(REMARK,'') "
						+ "FROM FMS7_FGSA_MST WHERE REOPEN_REQUEST_FLAG='Y' AND "
						+ "CUSTOMER_CD='"+customer_cd_+"' ORDER BY REOPEN_REQUEST_DT DESC";
//				System.out.println("Fetching FGSA Data..."+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					APR_FGSA_NO.add(rset.getString(1));
					APR_FGSA_REV_NO.add(rset.getString(2));
					APR_SIGNING_DT.add(rset.getString(3));
					APR_START_DT.add(rset.getString(4));
					APR_END_DT.add(rset.getString(5));
					APR_FGSA_BASE.add(rset.getString(6).equals("X")?"Ex-Terminal":"Delivery");
					APR_FGSA_TYPE.add(rset.getString(7).equals("S")?"Spot":"Term");
					APR_REOPEN_REQ_DT.add(rset.getString(8));
					APR_REOPEN_APPROVAL_FLAG.add(rset.getString(9)==null?"":rset.getString(9));
					APR_REOPEN_APPROVAL_DT.add(rset.getString(10)==null?"":rset.getString(10));
					APR_REOPEN_REMARK.add(rset.getString(11)==null?"":rset.getString(11));
				}
			} else if(contract_type.equals("LT")) {
				//Fetching LTCORA Contract with Re-Open Request...
				queryString = "SELECT AGREEMENT_NO,REV_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'), "
						+ "TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'), "
						+ "TO_CHAR(REOPEN_REQUEST_DT,'DD/MM/YYYY'),"
						+ "NVL(REOPEN_APPROVAL_FLAG,''),TO_CHAR(REOPEN_APPROVAL_DT,'DD/MM/YYYY'),NVL(REOPEN_REMARK,'') "
						+ "FROM FMS8_LNG_REGAS_MST WHERE REOPEN_REQUEST_FLAG='Y' AND "
						+ "CUSTOMER_CD='"+customer_cd_+"' AND CN_NO='0' AND CN_REV_NO='0'  ORDER BY REOPEN_REQUEST_DT DESC";
//				System.out.println("Fetching LTCORA Data..."+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					APR_FGSA_NO.add(rset.getString(1));
					APR_FGSA_REV_NO.add(rset.getString(2));
					APR_SIGNING_DT.add(rset.getString(3));
					APR_START_DT.add(rset.getString(4));
					APR_END_DT.add(rset.getString(5));
					APR_REOPEN_REQ_DT.add(rset.getString(6));
					APR_REOPEN_APPROVAL_FLAG.add(rset.getString(7)==null?"":rset.getString(7));
					APR_REOPEN_APPROVAL_DT.add(rset.getString(8)==null?"":rset.getString(8));
					APR_REOPEN_REMARK.add(rset.getString(9)==null?"":rset.getString(9));
				}
			} else if(contract_type.equals("T")) {
				//Fetching TENDER Contract with Re-Open Request...
				queryString = "SELECT TENDER_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'), "
						+ "TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'), "
						+ "TENDER_BASE,TO_CHAR(REOPEN_REQUEST_DT,'DD/MM/YYYY'),"
						+ "NVL(REOPEN_APPROVAL_FLAG,''),TO_CHAR(REOPEN_APPROVAL_DT,'DD/MM/YYYY'),NVL(REMARK,'') "
						+ "FROM FMS7_TENDER_MST WHERE REOPEN_REQUEST_FLAG='Y' AND "
						+ "CUSTOMER_CD='"+customer_cd_+"'  ORDER BY REOPEN_REQUEST_DT DESC";
//				System.out.println("Fetching TENDER Data..."+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					APR_FGSA_NO.add(rset.getString(1));
					APR_FGSA_REV_NO.add("0");
					APR_SIGNING_DT.add(rset.getString(2));
					APR_START_DT.add(rset.getString(3));
					APR_END_DT.add(rset.getString(4));
					APR_FGSA_BASE.add(rset.getString(5).equals("X")?"Ex-Terminal":"Delivery");
					APR_FGSA_TYPE.add("");
					APR_REOPEN_REQ_DT.add(rset.getString(6));
					APR_REOPEN_APPROVAL_FLAG.add(rset.getString(7)==null?"":rset.getString(7));
					APR_REOPEN_APPROVAL_DT.add(rset.getString(8)==null?"":rset.getString(8));
					APR_REOPEN_REMARK.add(rset.getString(9)==null?"":rset.getString(9));
				}
			} else if(contract_type.equals("C")) {
				//Fetching CN/PERIOD Contract with Re-Open Request...
				queryString = "SELECT AGREEMENT_NO,REV_NO,TO_CHAR(SIGNING_DT,'DD/MM/YYYY'), "
						+ "TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY'), "
						+ "TO_CHAR(REOPEN_REQUEST_DT,'DD/MM/YYYY'),"
						+ "NVL(REOPEN_APPROVAL_FLAG,''),TO_CHAR(REOPEN_APPROVAL_DT,'DD/MM/YYYY'),"
						+ "CN_NO,CN_REV_NO,NVL(REOPEN_REMARK,'') "
						+ "FROM FMS8_LNG_REGAS_MST WHERE REOPEN_REQUEST_FLAG='Y' AND "
						+ "CUSTOMER_CD='"+customer_cd_+"' AND CN_NO!='0'  ORDER BY REOPEN_REQUEST_DT DESC";
//				System.out.println("Fetching LTCORA Data..."+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next()) {
					APR_FGSA_NO.add(rset.getString(1));
					APR_FGSA_REV_NO.add(rset.getString(2));
					APR_SIGNING_DT.add(rset.getString(3));
					APR_START_DT.add(rset.getString(4));
					APR_END_DT.add(rset.getString(5));
					APR_REOPEN_REQ_DT.add(rset.getString(6));
					APR_REOPEN_APPROVAL_FLAG.add(rset.getString(7)==null?"":rset.getString(7));
					APR_REOPEN_APPROVAL_DT.add(rset.getString(8)==null?"":rset.getString(8));
					APR_CN_NO.add(rset.getString(9)==null?"":rset.getString(9));
					APR_CN_REV_NO.add(rset.getString(10)==null?"":rset.getString(10));
					APR_REOPEN_REMARK.add(rset.getString(11)==null?"":rset.getString(11));
				}
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	Vector LC_FINANCIAL_YEAR = new Vector();
	Vector LC_SEQ_NO = new Vector();
	Vector LC_BANK_CD = new Vector();
	Vector LC_BANK_NM = new Vector();
	Vector LC_BANK_RATING = new Vector();
	Vector LC_RATING_EFF_DATE = new Vector();
	Vector LC_VALIDITY_START_DATE = new Vector();
	Vector LC_VALIDITY_END_DATE = new Vector();
	Vector LC_MRKT_LC_AMOUNT = new Vector();
	Vector LC_BANK_LC_AMOUNT = new Vector();
	Vector LC_LAST_SHIPMENT_DATE = new Vector();
	Vector LC_AMENDMENT_DATE = new Vector();
	Vector LC_BANK_LC_NO = new Vector();
	Vector LC_AMENDMENT_NO = new Vector();
	String contract_tp = "";
	
	public void fetch_LC_details(String contract_type) throws Exception {
		try {
			//Fetching Approved LC Details...
			if(contract_type.equals("DONE"))
				contract_type = contract_tp;
			System.out.println("=="+contract_tp);
			NumberFormat nf3 = new DecimalFormat("###,###,##0.00");
			if(contract_type.equals("S")) {
				queryString="SELECT FINANCIAL_YEAR,LC_SEQ_NO,BANK_CD,BANK_NM," +
					      "BANK_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'), " +
					      "TO_CHAR(VALIDITY_START_DATE,'DD/MM/YYYY'),TO_CHAR(VALIDITY_END_DATE,'DD/MM/YYYY')," +
					      "MRKT_LC_AMOUNT,BANK_LC_AMOUNT,TO_CHAR(LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
					      "TO_CHAR(AMENDMENT_DATE,'DD/MM/YYYY'),BANK_LC_NO, AMENDMENT_NO FROM FMS7_LC_FINANCE_MST " +
					      " where upper(flag)='Y' and customer_cd='"+bscode+"' AND LC_SEQ_NO IN ("
				      	  + "SELECT LC_SEQ_NO FROM FMS7_LC_DTL WHERE " +
						  "CUSTOMER_CD='"+bscode+"' AND FGSA_NO='"+FGSA_cd+"' AND SN_NO='"+SN_CD+"' "
				   		  + "AND CONT_TYPE='"+contract_type+"' AND FGSA_REV_NO='"+FGSA_REVNo+"' AND SN_REV_NO='"+SN_REVNo+"') " +
						  "ORDER BY LC_SEQ_NO";
			} else if(contract_type.equals("L")) {
				queryString="SELECT FINANCIAL_YEAR,LC_SEQ_NO,BANK_CD,BANK_NM," +
					      "BANK_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'), " +
					      "TO_CHAR(VALIDITY_START_DATE,'DD/MM/YYYY'),TO_CHAR(VALIDITY_END_DATE,'DD/MM/YYYY')," +
					      "MRKT_LC_AMOUNT,BANK_LC_AMOUNT,TO_CHAR(LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
					      "TO_CHAR(AMENDMENT_DATE,'DD/MM/YYYY'),BANK_LC_NO, AMENDMENT_NO FROM FMS7_LC_FINANCE_MST " +
					      " where upper(flag)='Y' and customer_cd='"+bscode+"' AND LC_SEQ_NO IN ("
				      	  + "SELECT LC_SEQ_NO FROM FMS7_LC_DTL WHERE " +
						  "CUSTOMER_CD='"+bscode+"' AND FGSA_NO='"+tender_cd+"' AND SN_NO='"+LOA_CD+"' "
				   		  + "AND CONT_TYPE='"+contract_type+"' AND SN_REV_NO='"+LOA_REVNo+"') " +
						  "ORDER BY LC_SEQ_NO";
			}
			System.out.println("Fetching Mast..."+queryString);	      
			rset = stmt.executeQuery(queryString);
			while(rset.next()) {
				LC_FINANCIAL_YEAR.add(rset.getString(1)==null?"":rset.getString(1));
				String fin_year = rset.getString(1)==null?"":rset.getString(1);
				int lc_seq_no = (rset.getString(2)==null?"":rset.getString(2)).length();
				String tempNo = rset.getString(2)==null?"":rset.getString(2);
				if(lc_seq_no<10)
				{
					tempNo = "000"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<100)
				{
					tempNo = "00"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<1000)
				{
					tempNo = "0"+lc_seq_no+"/"+fin_year;
				}
				else
				{
					tempNo = ""+lc_seq_no+"/"+fin_year;
				}
				
				LC_SEQ_NO.add(tempNo);
				LC_BANK_CD.add(rset.getString(3)==null?"":rset.getString(3));
				LC_BANK_NM.add(rset.getString(4)==null?"":rset.getString(4));
				LC_BANK_RATING.add(rset.getString(5)==null?"":rset.getString(5));
				LC_RATING_EFF_DATE.add(rset.getString(6)==null?"":rset.getString(6));
				LC_VALIDITY_START_DATE.add(rset.getString(7)==null?"":rset.getString(7));
				LC_VALIDITY_END_DATE.add(rset.getString(8)==null?"":rset.getString(8));
				LC_MRKT_LC_AMOUNT.add(rset.getString(9)==null?"":nf3.format(Double.parseDouble(rset.getString(9))));
				LC_BANK_LC_AMOUNT.add(rset.getString(10)==null?"":nf3.format(Double.parseDouble(rset.getString(10))));
				LC_LAST_SHIPMENT_DATE.add(rset.getString(11)==null?"":rset.getString(11));
				LC_AMENDMENT_DATE.add(rset.getString(12)==null?"":rset.getString(12));
				LC_BANK_LC_NO.add(rset.getString(13)==null?"":rset.getString(13));
				LC_AMENDMENT_NO.add(rset.getString(14)==null?"":rset.getString(14));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	Vector fetch_exchg_rate1 = new Vector();
	Vector fetch_eff_dt1 = new Vector();
	boolean flag_fetch = false;
	public void FetchExchangeRateDtls()
	{
		try
		{
			String query="SELECT EXCHG_RATE,TO_CHAR(EFF_DT,'DD/MM/YYYY') FROM EXCHG_RATE_MST "
					+ "ORDER BY EFF_DT DESC";
			rset = stmt.executeQuery(query);
			while(rset.next())
			{
				fetch_exchg_rate1.add(rset.getString(1));
				fetch_eff_dt1.add(rset.getString(2));
			}
			
			/*FETCH NOMINATION DETAILS*/
			int count = 0;
			
			if(fetch_exchg_rate1.size()>0)
			{
				
				query = "SELECT FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,A.CUSTOMER_CD,CONTRACT_TYPE "
						+ "FROM FMS8_ADVANCE_AMT_MST A, FMS7_CUSTOMER_MST B "
						+ "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD "
						+ "AND B.EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST C WHERE "
						+ "C.CUSTOMER_CD=B.CUSTOMER_CD) "
						+ "GROUP BY A.CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONTRACT_TYPE "
						+ "ORDER BY A.CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONTRACT_TYPE ";
				//System.out.println("------"+query);
				rset = stmt.executeQuery(query);
				while(rset.next())
				{
					String queryString5 = "SELECT count(gas_dt) " +
							   "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
							   "WHERE "
							   + "A.sn_no='"+rset.getString(3)+"' " +
							   "AND A.fgsa_no='"+rset.getString(1)+"' " +
							   "AND A.customer_cd='"+rset.getString(5)+"' " +
							   "AND A.contract_type ='"+rset.getString(6).charAt(0)+"' " +
							   "AND A.nom_rev_no=" +
							   "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
							   "WHERE "
							   + "B.sn_no='"+rset.getString(3)+"' " +
							   "AND B.fgsa_no='"+rset.getString(1)+"' " +
							   "AND B.customer_cd='"+rset.getString(5)+"' " +
							   "AND B.contract_type = '"+rset.getString(6).charAt(0)+"' " +
							   ") AND GAS_DT>=TO_DATE('"+fetch_eff_dt1.elementAt(0)+"','DD/MM/YYYY')";
							//System.out.println("queryString5 Fetch Max NOM Dt  "+queryString5);
							rset1=stmt1.executeQuery(queryString5);
								if(rset1.next())
								{
									count = rset1.getInt(1);
								}
								if(count!=0)
								{
									flag_fetch = true;
									break;
								}
				}
			}
			//System.out.println("Flag_fetch-->"+flag_fetch);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	String fgsano_fetch = "";
	String snno_fetch = "";
	String snrevno_fetch = "";
	String fgsarevno_fetch = "";
	String customercd_fetch = "";
	String contracttype_fetch = "";
	
	Vector fetch_advance_amount = new Vector();
	Vector fetch_exchg_rate = new Vector();
	Vector fetch_eff_dt = new Vector();
	Vector fetch_fgsa_no = new Vector();
	Vector fetch_fgsa_rev_no = new Vector();
	Vector fetch_sn_no = new Vector();
	Vector fetch_sn_rev_no = new Vector();
	Vector fetch_customer_cd=new Vector();
	Vector fetch_currency = new Vector();
	Vector fetch_customer_name = new Vector();
	Vector fetch_contracttype = new Vector();
	Vector fetch_tax = new Vector();
	
	public void FetchAllAdvance()
	{
		try
		{
			String query="";
			if(fgsano_fetch.equals(""))
			{
				query = "SELECT FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,A.CUSTOMER_CD,ADVANCE_AMOUNT,"
						+ "TO_CHAR(A.EFF_DT,'DD/MM/YYYY'),CURRENCY,CURRENCY,B.CUSTOMER_NAME,A.CONTRACT_TYPE,TAX_AMOUNT "
						+ "FROM FMS8_ADVANCE_AMT_MST A, FMS7_CUSTOMER_MST B "
						+ "WHERE A.CUSTOMER_CD='"+customercd_fetch+"' "
						+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EFF_DT = (SELECT MAX(EFF_DT) "
						+ "FROM FMS7_CUSTOMER_MST C WHERE C.CUSTOMER_CD=B.CUSTOMER_CD) "
						+ "ORDER BY A.CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,A.EFF_DT DESC ";
			} else {
			 query = "SELECT FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,A.CUSTOMER_CD,ADVANCE_AMOUNT,"
					+ "TO_CHAR(A.EFF_DT,'DD/MM/YYYY'),CURRENCY,CURRENCY,B.CUSTOMER_NAME,"
					+ "A.CONTRACT_TYPE,TAX_AMOUNT "
					+ "FROM FMS8_ADVANCE_AMT_MST A, FMS7_CUSTOMER_MST B "
					+ "WHERE A.CUSTOMER_CD='"+customercd_fetch+"' AND A.CUSTOMER_CD=B.CUSTOMER_CD "
					+ "AND B.EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST C WHERE "
					+ "C.CUSTOMER_CD=B.CUSTOMER_CD) "
					+ "AND FGSA_NO='"+fgsano_fetch+"' AND FGSA_REV_NO='"+fgsarevno_fetch+"' "
					+ "AND SN_NO='"+snno_fetch+"' AND SN_REV_NO='"+snrevno_fetch+"' "
					+ "AND A.CONTRACT_TYPE='"+contracttype_fetch+"' "
					+ "ORDER BY A.CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,A.EFF_DT DESC ";
			}
			//System.out.println(query);
			rset = stmt.executeQuery(query);
			while(rset.next())
			{
				fetch_fgsa_no.add(rset.getString(1));
				fetch_fgsa_rev_no.add(rset.getString(2));
				fetch_sn_no.add(rset.getString(3));
				fetch_sn_rev_no.add(rset.getString(4));
				fetch_customer_cd.add(rset.getString(5));
				fetch_advance_amount.add(rset.getString(6));	//check here
				fetch_eff_dt.add(rset.getString(7));
				fetch_exchg_rate.add(rset.getString(8)==null?"":rset.getString(8));
				String cur = rset.getString(9)==null?"I":rset.getString(9);
				if(cur.equals("I"))
				{
					fetch_currency.add("Rs.");
				} else {
					fetch_currency.add("$");
				}
				fetch_customer_name.add(rset.getString(10));
				fetch_contracttype.add(rset.getString(11));
				fetch_tax.add(rset.getString(12)==null?"":rset.getString(12));
			}
			
			query="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A WHERE CUSTOMER_CD='"+customercd_fetch+"' "
					+ "AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST B WHERE "
					+ "A.CUSTOMER_CD=B.CUSTOMER_CD) ";
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				customer_nm_advance = rset.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	String customer_cd_advance="0";
	String customer_nm_advance="";
	
	public void FetchContractForAdvance()
	{
		try
		{
			/*Fetch SN Data*/
			String query="SELECT FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CUSTOMER_CD,"
					+ "TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),TO_CHAR(START_DT,'DD/MM/YYYY'), "
					+ "TO_CHAR(END_DT,'DD/MM/YYYY'), DCQ, TCQ FROM FMS7_SN_MST A WHERE "
					+ "ADVANCE_COLLECTION_FLAG='Y' AND SN_REV_NO = (SELECT MAX(B.SN_REV_NO) "
					+ "FROM FMS7_SN_MST B WHERE B.SN_NO=A.SN_NO AND B.FGSA_NO=A.FGSA_NO AND "
					+ "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FLAG=B.FLAG) AND A.FLAG='T' AND "
					+ "CUSTOMER_CD='"+customer_cd_advance+"' ";
			rset = stmt.executeQuery(query);
			while(rset.next())
			{
				fgsano.add(rset.getString(1));
				fgsarevno.add(rset.getString(2));
				snno.add(rset.getString(3));
				snrevno.add(rset.getString(4));
				customercd.add(rset.getString(5));
				signingdt.add(rset.getString(6));
				startdt.add(rset.getString(7));
				enddt.add(rset.getString(8));
				dcq.add(rset.getString(9));
				tcq.add(rset.getString(10));
				contracttype.add("SN");
			}
			
			/*Fetch LOA Data*/
			query="SELECT TENDER_NO,TENDER_NO,LOA_NO,LOA_REV_NO,CUSTOMER_CD,"
					+ "TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),TO_CHAR(START_DT,'DD/MM/YYYY'), "
					+ "TO_CHAR(END_DT,'DD/MM/YYYY'), DCQ, TCQ FROM FMS7_LOA_MST A WHERE "
					+ "ADVANCE_COLLECTION_FLAG='Y' AND LOA_REV_NO = (SELECT MAX(B.LOA_REV_NO) "
					+ "FROM FMS7_LOA_MST B WHERE B.LOA_NO=A.LOA_NO AND B.TENDER_NO=A.TENDER_NO AND "
					+ "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FLAG=B.FLAG) AND A.FLAG='T' AND "
					+ "CUSTOMER_CD='"+customer_cd_advance+"' ";
			rset = stmt.executeQuery(query);
			while(rset.next())
			{
				fgsano.add(rset.getString(1));
				fgsarevno.add("0");
				snno.add(rset.getString(3));
				snrevno.add(rset.getString(4));
				customercd.add(rset.getString(5));
				signingdt.add(rset.getString(6));
				startdt.add(rset.getString(7));
				enddt.add(rset.getString(8));
				dcq.add(rset.getString(9));
				tcq.add(rset.getString(10));
				contracttype.add("LOA");
			}
			
			query="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A WHERE CUSTOMER_CD='"+customer_cd_advance+"' "
					+ "AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST B WHERE "
					+ "A.CUSTOMER_CD=B.CUSTOMER_CD) ";
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				customer_nm_advance = rset.getString(1);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	Vector fgsano = new Vector();
	Vector snno = new Vector();
	Vector snrevno = new Vector();
	Vector fgsarevno = new Vector();
	Vector customercd = new Vector();
	Vector startdt = new Vector();
	Vector enddt = new Vector();
	Vector signingdt = new Vector();
	Vector dcq = new Vector();
	Vector tcq = new Vector();
	Vector contracttype = new Vector();
	
	Vector cust_cd = new Vector();
	Vector cust_name = new Vector();
	
	public void AdvanceCollection()
	{
		try
		{
			/*SN....*/
			String q="SELECT DISTINCT A.CUSTOMER_CD,CUSTOMER_NAME FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST B "
					+ "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND "
					+ "ADVANCE_COLLECTION_FLAG='Y' AND SN_REV_NO = (SELECT MAX(B.SN_REV_NO) "
					+ "FROM FMS7_SN_MST B WHERE B.SN_NO=A.SN_NO AND B.FGSA_NO=A.FGSA_NO AND "
					+ "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FLAG=B.FLAG) AND A.FLAG='T' "
					+ "AND B.EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST C WHERE "
					+ "C.CUSTOMER_CD=B.CUSTOMER_CD) "
					+ "ORDER BY CUSTOMER_NAME";
			rset = stmt.executeQuery(q);
			while(rset.next())
			{
				cust_cd.add(rset.getString(1));
				cust_name.add(rset.getString(2));
			}
			
			/*LOA...*/
			q="SELECT DISTINCT A.CUSTOMER_CD,CUSTOMER_NAME FROM FMS7_LOA_MST A, FMS7_CUSTOMER_MST B "
					+ "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND "
					+ "ADVANCE_COLLECTION_FLAG='Y' AND LOA_REV_NO = (SELECT MAX(B.LOA_REV_NO) "
					+ "FROM FMS7_LOA_MST B WHERE B.LOA_NO=A.LOA_NO AND B.TENDER_NO=A.TENDER_NO AND "
					+ "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FLAG=B.FLAG) AND A.FLAG='T' "
					+ "AND B.EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST C WHERE "
					+ "C.CUSTOMER_CD=B.CUSTOMER_CD) "
					+ "ORDER BY CUSTOMER_NAME";
			rset = stmt.executeQuery(q);
			while(rset.next())
			{
				String cd = rset.getString(1);
				if(!cust_cd.contains(cd))
				{
					cust_cd.add(cd);
					cust_name.add(rset.getString(2));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void CHECKFORCOLUMN()
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS7_FGSA_MST' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'PRE_APPROVAL_BY' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table fms7_fgsa_mst add pre_approval_date date";
				stmt.executeUpdate(query);
				query="alter table fms7_fgsa_mst add pre_approval varchar2(1)";
				stmt.executeUpdate(query);
				query="alter table fms7_fgsa_mst add pre_approval_by number(6,0)";
				stmt.executeUpdate(query);
				conn.commit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	public void fetch_Tender_advance_clause_details()
	{
		try
		{
			queryString="SELECT ADJUST_FLAG FROM FMS7_TENDER_MST WHERE " +
					"Tender_NO='"+tender_cd+"' AND CUSTOMER_CD='"+bscode+"' ";
		//	System.out.println("Fetching ...adjust Flag..."+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				ADJUST_FLAG_TENDER=rset.getString(1)==null?"N":rset.getString(1);
			}
			else
			{
				ADJUST_FLAG_TENDER="N";
			}
			
			queryString="select compo_cd,compo_nm from fms7_compo_mst where flag='Y' and compo_cd!='2'";
			//System.out.println("Fetching..component dtls..."+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				component_cd.add(rset.getString(1));
				component_nm.add(rset.getString(2));
			}
			
			String mapping_id=""+bscode+"-"+tender_cd+"-"+0+"-"+LOA_CD+"-"+LOA_REVNo+"-"+"L";
			for(int i=0;i<component_cd.size();i++)
			{
				queryString = "select price_rate,currency_cd,flag " +
						"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
						"price_cd='"+component_cd.elementAt(i)+"' AND FLAG='Y'";
				//System.out.println("Fetching flag of sn..."+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(component_cd.elementAt(i).equals("1"))
					{
						ADJUST_FLAG_LOA=rset.getString(3)==null?"N":rset.getString(3);
						ADJUST_AMT_LOA=rset.getString(1)==null?"":rset.getString(1);
						ADJUST_CUR_LOA=rset.getString(2);
					}
					else if(component_cd.elementAt(i).equals("2"))
					{
						DISCOUNT_FLAG_LOA=rset.getString(3)==null?"N":rset.getString(3);
						DISCOUNT_PRICE_LOA=rset.getString(1)==null?"":rset.getString(1);
					}
					else if(component_cd.elementAt(i).equals("3"))
					{
						TARIFF_FLAG_LOA=rset.getString(3)==null?"N":rset.getString(3);
						TARIFF_INR_LOA=rset.getString(1)==null?"":rset.getString(1);
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in Fetching data..."+e.getMessage());
			e.printStackTrace();
		}
	}

	
	public void fetch_FGSA_advance_clause_details()
	{
		try
		{
			/*queryString="SELECT ADJUST_FLAG FROM FMS7_FGSA_MST WHERE " +
					"FGSA_NO='"+FGSA_cd+"' AND REV_NO='"+FGSA_REVNo+"' AND CUSTOMER_CD='"+bscode+"' ";
		//	System.out.println("Fetching ...adjust Flag..."+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				ADJUST_FLAG_FGSA=rset.getString(1)==null?"N":rset.getString(1);
			}
			else
			{
				ADJUST_FLAG_FGSA="N";
			}*/
			
			queryString="select compo_cd,compo_nm from fms7_compo_mst where flag='Y' and compo_cd!='2' ";
			//System.out.println("Fetching..component dtls..."+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				component_cd.add(rset.getString(1));
				component_nm.add(rset.getString(2));
			}
			
			String mapping_id=""+bscode+"-"+FGSA_cd+"-"+FGSA_REVNo+"-"+SN_CD+"-"+SN_REVNo+"-"+"S";
			for(int i=0;i<component_cd.size();i++)
			{
				queryString = "select price_rate,currency_cd,flag " +
						"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
						"price_cd='"+component_cd.elementAt(i)+"' AND FLAG='Y' ";
			//	System.out.println("Fetching flag of sn..."+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(component_cd.elementAt(i).equals("1"))
					{
						ADJUST_FLAG_SN=rset.getString(3)==null?"N":rset.getString(3);
						ADJUST_AMT_SN=rset.getString(1)==null?"":rset.getString(1);
						ADJUST_CUR_SN=rset.getString(2);
					}
					else if(component_cd.elementAt(i).equals("2"))
					{
						DISCOUNT_FLAG_SN=rset.getString(3)==null?"N":rset.getString(3);
						DISCOUNT_PRICE_SN=rset.getString(1)==null?"":rset.getString(1);
					}
					else if(component_cd.elementAt(i).equals("3"))
					{
						TARIFF_FLAG_SN=rset.getString(3)==null?"N":rset.getString(3);
						TARIFF_INR_SN=rset.getString(1)==null?"":rset.getString(1);
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in Fetching data..."+e.getMessage());
			e.printStackTrace();
		}
	}

	public void Fetch_RE_GAS_CARGO_DTL_LIST()
	{		
		try 
		{	
			if(Customer_access_flag.equals("Y"))
			{
				queryString = "SELECT CARGO_REF_NO, A.CUSTOMER_CD, RE_GAS_NO, CARGO_SEQ_NO, " +
					       "to_char(CONTRACT_START_DT,'dd/mm/yyyy'), " +
					       "to_char(CONTRACT_END_DT,'dd/mm/yyyy') FROM " +
					       "FMS7_RE_GAS_CARGO_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B "
					       + "where REGAS_CLOSURE_REQUEST='Y' "
					       + "AND REGAS_CLOSURE_CLOSE IS NULL AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
					       + "B.EMP_CD='"+Emp_cd+"' ";
			} else {
				queryString = "SELECT CARGO_REF_NO, CUSTOMER_CD, RE_GAS_NO, CARGO_SEQ_NO, " +
					       "to_char(CONTRACT_START_DT,'dd/mm/yyyy'), " +
					       "to_char(CONTRACT_END_DT,'dd/mm/yyyy') FROM " +
					       "FMS7_RE_GAS_CARGO_DTL A "
					       + "where REGAS_CLOSURE_REQUEST='Y' "
					       + "AND REGAS_CLOSURE_CLOSE IS NULL "
					       + " ";
			}
			
			///System.out.println("RE_GAS Fetch DATA Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				CARGO_REF_NO.add(rset.getString(1)==null?"":rset.getString(1));
				//CARGO_SEQ_NO.add(rset.getString(2)==null?"":rset.getString(2));
				CONTRACT_START_DT.add(rset.getString(5)==null?"":rset.getString(5));
				CONTRACT_END_DT.add(rset.getString(6)==null?"":rset.getString(6));
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					buyer_cd.add(rset.getString(2));
					FGSA_no.add(rset.getString(3));
					queryString1 ="select max(rev_no) from FMS7_RE_GAS_MST  where Customer_cd='"+rset.getString(2)+"' and Re_Gas_NO='"+rset.getString(3)+"'";
					//System.out.println("FGSA REV NO Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						vRev_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
					}
					else
					{
						vRev_No.add("0");
					}
				}
				else
				{
					buyer_cd.add("");
					FGSA_no.add("");
					vRev_No.add("0");
				}
			}			
			for(int i=0;i<FGSA_no.size();i++)
			{
				queryString = "SELECT CUSTOMER_CD,RE_GAS_NO,to_char(START_DT,'dd/mm/yyyy'), " +
						      "to_char(END_DT,'dd/mm/yyyy'), RE_GAS_BASE,STATUS, " +
						      "to_char(REV_DT,'dd/mm/yyyy'), NO_OF_CARGO FROM FMS7_RE_GAS_MST " +
						      "where Customer_cd='"+buyer_cd.elementAt(i)+"' AND " +
						      "RE_GAS_NO='"+FGSA_no.elementAt(i)+"' AND REV_NO='"+vRev_No.elementAt(i)+"' ";
				//System.out.println("RE_GAS Fetch DATA Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vSTART_DT.add(rset.getString(3)==null?"":rset.getString(3));
					vEND_DT.add(rset.getString(4)==null?"":rset.getString(4));
					vFGSA_BASE.add((rset.getString(5)==null?"":rset.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"));					
					vSTATUS.add((rset.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"));
					vREV_DT.add(rset.getString(7)==null?"":rset.getString(7));
					NO_OF_CARGO.add(rset.getString(8)==null?"1":rset.getString(8));
				}
				else
				{
					vSTART_DT.add("");
					vEND_DT.add("");
					vFGSA_BASE.add("Ex-Terminal");					
					vSTATUS.add("Active");
					vREV_DT.add("");
					NO_OF_CARGO.add("1");
				}
			}
			for(int i=0;i<buyer_cd.size();i++)
			{
				queryString ="Select nvl(Customer_name,'') from FMS7_Customer_mst where Customer_cd='"+buyer_cd.elementAt(i)+"'";
				//System.out.println("Re-Gas Buyer Name Fetch Query  = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					buyer_nm.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					buyer_nm.add("");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchRE_GAS()--> "+e.getMessage());
			e.printStackTrace();
		}	
	}
	
	
	public void Fetch_RE_GAS_CARGO_DTL_CLOSURE()
	{
		try
		{
			Vector temp_REGAS_CLOSURE_QTY = new Vector();
			for(int i=0; i<Integer.parseInt(no_of_cargo); i++)
			{
				queryString1 = "SELECT SUM(QTY_MMBTU) FROM FMS7_DAILY_ALLOCATION_DTL " +
						      "WHERE SN_NO="+(i+1)+" AND FGSA_NO='"+re_gas_no+"' " +
						      "AND CUSTOMER_CD='"+Buyer_cd+"' AND CONTRACT_TYPE='R' ";
				//System.out.println("SELECT SUM(QTY_MMBTU) FROM FMS7_DAILY_ALLOCATION_DTL --->"+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					temp_REGAS_CLOSURE_QTY.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				else
				{
					temp_REGAS_CLOSURE_QTY.add("0");
				}
			}
				
			//System.out.println("temp_REGAS_CLOSURE_QTY --->"+temp_REGAS_CLOSURE_QTY); 
			
			for(int i=1; i<=Integer.parseInt(no_of_cargo); i++)
			{
				String regas_close_qty = ""+temp_REGAS_CLOSURE_QTY.elementAt(i-1);
				//System.out.println("regas_close_qty --->"+regas_close_qty);
				
				queryString = "SELECT cargo_ref_no, TO_CHAR(edq_from_dt,'dd/mm/yyyy'), " +
							  "TO_CHAR(edq_to_dt,'dd/mm/yyyy'), TO_CHAR(actual_recpt_dt,'dd/mm/yyyy'), " +
							  "TO_CHAR(contract_start_dt,'dd/mm/yyyy'), TO_CHAR(contract_end_dt,'dd/mm/yyyy'), " +
							  "ship_cd, adq_qty, sys_use_gas, qty_to_be_supply, dcq_qty, re_gas_tarif, " +
							  "qty_unit_cd, REGAS_CLOSURE_REQUEST, REGAS_CLOSURE_CLOSE, " +
							  "to_char(REGAS_CLOSURE_DT,'dd/mm/yyyy'), REGAS_CLOSURE_QTY " +
							  "FROM FMS7_RE_GAS_CARGO_DTL WHERE customer_cd="+Buyer_cd+" AND " +
							  "re_gas_no="+re_gas_no+" AND cargo_seq_no="+i+"";				
				//System.out.println("RE-GAS Cargo Contract Detail Fetch Query for Closure = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					String temp_cargo_ref_no = rset.getString(1)==null?"":rset.getString(1);
					String temp_sug = rset.getString(9)==null?"":rset.getString(9);
					String temp_teriff = rset.getString(12)==null?"":rset.getString(12);
					
					if(!temp_sug.trim().equals(""))
					{
						temp_sug = nf.format(Double.parseDouble(temp_sug.trim()));
					}
					
					if(!temp_teriff.trim().equals(""))
					{
						temp_teriff = nf2.format(Double.parseDouble(temp_teriff.trim()));
					}
					
					if(temp_cargo_ref_no.equals(""))
					{
						String cargo_ref_no = "";
						if(!Buyer_cd.trim().equals("") && !Buyer_cd.trim().equals("0") && !re_gas_no.trim().equals("") && !re_gas_no.trim().equals("0"))
						{
							if(Integer.parseInt(Buyer_cd.trim())<10)
							{
								cargo_ref_no += "RE0"+Buyer_cd.trim();
							}
							else
							{
								cargo_ref_no += "RE"+Buyer_cd.trim();
							}
							
							if(Integer.parseInt(re_gas_no.trim())<10)
							{
								cargo_ref_no += "0"+re_gas_no.trim();
							}
							else
							{
								cargo_ref_no += re_gas_no.trim();
							}
							
							if(i<10)
							{
								cargo_ref_no += "0"+i;
							}
							else
							{
								cargo_ref_no += ""+i;
							}
						}						
						CARGO_REF_NO.add(cargo_ref_no);
					}
					else
					{
						CARGO_REF_NO.add(rset.getString(1)==null?"":rset.getString(1));
					}
					double qty_supply = 0;
					
					EDQ_FROM_DT.add(rset.getString(2)==null?"":rset.getString(2));
					EDQ_TO_DT.add(rset.getString(3)==null?"":rset.getString(3));
					ACTUAL_RECPT_DT.add(rset.getString(4)==null?"":rset.getString(4));
					CONTRACT_START_DT.add(rset.getString(5)==null?"":rset.getString(5));
					CONTRACT_END_DT.add(rset.getString(6)==null?"":rset.getString(6));
					SHIP_CD.add(rset.getString(7)==null?"0":rset.getString(7));
					ADQ_QTY.add(rset.getString(8)==null?"":rset.getString(8));
					SYS_USE_GAS.add(temp_sug);
					qty_supply = Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10));
					//QTY_TO_BE_SUPPLY.add(rset.getString(10)==null?"":rset.getString(10));
					DCQ_QTY.add(rset.getString(11)==null?"":rset.getString(11));
					RE_GAS_TARIFF.add(temp_teriff);
					QTY_UNIT_CD.add(rset.getString(13)==null?"1":rset.getString(13));
					REGAS_CLOSURE_REQUEST.add(rset.getString(14)==null?"":rset.getString(14));
					REGAS_CLOSURE_CLOSE.add(rset.getString(15)==null?"":rset.getString(15));
					REGAS_CLOSURE_DT.add(rset.getString(16)==null?"":rset.getString(16));
					REGAS_CLOSURE_QTY.add(rset.getString(17)==null?regas_close_qty:rset.getString(17));
					if(qty_supply!=0)
					{
						QTY_TO_BE_SUPPLY.add(nf3.format(qty_supply));
					}
					else
					{
						QTY_TO_BE_SUPPLY.add("0");
					}
				}
				else
				{
					String cargo_ref_no = "";
					if(!Buyer_cd.trim().equals("") && !Buyer_cd.trim().equals("0") && !re_gas_no.trim().equals("") && !re_gas_no.trim().equals("0"))
					{
						if(Integer.parseInt(Buyer_cd.trim())<10)
						{
							cargo_ref_no += "RE0"+Buyer_cd.trim();
						}
						else
						{
							cargo_ref_no += "RE"+Buyer_cd.trim();
						}
						
						if(Integer.parseInt(re_gas_no.trim())<10)
						{
							cargo_ref_no += "0"+re_gas_no.trim();
						}
						else
						{
							cargo_ref_no += re_gas_no.trim();
						}
						
						if(i<10)
						{
							cargo_ref_no += "0"+i;
						}
						else
						{
							cargo_ref_no += ""+i;
						}
					}
					CARGO_REF_NO.add(cargo_ref_no);
					EDQ_FROM_DT.add("");
					EDQ_TO_DT.add("");
					ACTUAL_RECPT_DT.add("");
					CONTRACT_START_DT.add("");
					CONTRACT_END_DT.add("");
					SHIP_CD.add("0");
					ADQ_QTY.add("");
					SYS_USE_GAS.add("");
					QTY_TO_BE_SUPPLY.add("");
					DCQ_QTY.add("");
					RE_GAS_TARIFF.add("");
					QTY_UNIT_CD.add("1");
					REGAS_CLOSURE_REQUEST.add("");
					REGAS_CLOSURE_CLOSE.add("");
					REGAS_CLOSURE_DT.add("");
					REGAS_CLOSURE_QTY.add(regas_close_qty);
					
				}
			}
			//System.out.println("REGAS_CLOSURE_QTY --->"+REGAS_CLOSURE_QTY);
			for(int i=0; i<SHIP_CD.size(); i++)
			{
				queryString = "SELECT ship_name FROM FMS7_SHIP_MST WHERE ship_cd="+SHIP_CD.elementAt(i)+"";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					SHIP_NAME.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					SHIP_NAME.add("");
				}
				
				queryString = "SELECT unit_abr FROM FMS7_UNIT_MST WHERE unit_cd="+QTY_UNIT_CD.elementAt(i)+"";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					QTY_UNIT_ABR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					QTY_UNIT_ABR.add("");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void fetch_LOA_Closure_List()
	{
		try 
		{
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = "SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD,A.TENDER_NO," +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, " +
				"A.VERIFY_FLAG, A.APPROVE_FLAG, A.LOA_REF_NO,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY " +
				"FROM FMS7_LOA_MST A, FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D WHERE " +
				"A.FLAG='T' AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM " +
				"FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
				"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+bscode+"' AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
				"AND A.LOA_CLOSURE_REQUEST='Y' AND A.LOA_CLOSURE_CLOSE='N' " +
				"ORDER BY A.customer_cd,A.tender_no,A.loa_no";
			}
			else
			{
				if(Customer_access_flag.equals("Y"))
				{
					queryString = "SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD, A.TENDER_NO," +
							"A.TCQ, D.UNIT_ABR,A.RATE,A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, " +
							"A.VERIFY_FLAG, A.APPROVE_FLAG, A.LOA_REF_NO,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY " +
							"FROM FMS7_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D, SEC_EMP_CUSTOMER_ALLOC_MST E "
							+ "WHERE " +
							"A.FLAG='T' AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM " +
							"FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
							"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
							"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
							"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
							"AND A.LOA_CLOSURE_REQUEST='Y' AND A.LOA_CLOSURE_CLOSE='N' "
							+ "AND A.CUSTOMER_CD=E.CUSTOMER_CD AND E.EMP_CD='"+Emp_cd+"' " +
							"ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				} else {
					queryString = "SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD, A.TENDER_NO," +
							"A.TCQ, D.UNIT_ABR,A.RATE,A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, " +
							"A.VERIFY_FLAG, A.APPROVE_FLAG, A.LOA_REF_NO,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY " +
							"FROM FMS7_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D WHERE " +
							"A.FLAG='T' AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM " +
							"FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
							"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
							"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
							"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
							"AND A.LOA_CLOSURE_REQUEST='Y' AND A.LOA_CLOSURE_CLOSE='N' " +
							"ORDER BY A.customer_cd,A.tender_no,A.loa_no";
				}
			}
//			System.out.println("Query For Fetching LOA List = "+queryString);
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{
			    vSN_No.add(rset1.getString(1)==null?"":rset1.getString(1));
			    vSN_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
			    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
			    FGSA_no.add(rset1.getString(4)==null?"":rset1.getString(4));
				vSN_TCQ.add(rset1.getString(5)==null?"":rset1.getString(5));
				vTCQ_UNIT.add(rset1.getString(6)==null?"":rset1.getString(6));
				vSN_RATE.add(rset1.getString(7)==null?"":rset1.getString(7));
				vRATE_UNIT.add(rset1.getString(8)==null?"":rset1.getString(8));
				buyer_nm.add(rset1.getString(9)==null?"":rset1.getString(9));
				vBUYER_ABBR.add(rset1.getString(10)==null?"":rset1.getString(10));
				vSN_name.add(rset1.getString(11)==null?"":rset1.getString(11));  
				vSN_verify.add(rset1.getString(12)==null?"":rset1.getString(12)); 
				vSN_approve.add(rset1.getString(13)==null?"":rset1.getString(13));
				String sn_ref_no = rset1.getString(14)==null?"":rset1.getString(14);
				if(!sn_ref_no.trim().equals(""))
				{ vSN_Ref_No.add(" - ("+sn_ref_no+")"); }
				else
				{ vSN_Ref_No.add(""); }				
				TCQ_REQUEST_SIGN.add(rset1.getString(15)==null?"":rset1.getString(15));
				TCQ_REQUEST_QTY.add(rset1.getString(16)==null?"":rset1.getString(16));
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LOA_Closure_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_SN_Closure_List()
	{
		try 
		{
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
				"A.VERIFY_FLAG, A.APPROVE_FLAG, A.SN_REF_NO, " +
				"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+bscode+"' AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.SN_CLOSURE_REQUEST='Y' AND A.SN_CLOSURE_CLOSE='N' " +
				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
			}
			else
			{
				if(Customer_access_flag.equals("Y"))
				{
					queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
							"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
							"A.VERIFY_FLAG, A.APPROVE_FLAG, A.SN_REF_NO, " +
							"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
							"FMS7_UNIT_MST D, SEC_EMP_CUSTOMER_ALLOC_MST E "
							+ "WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
							"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
							"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
							"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.SN_CLOSURE_REQUEST='Y' AND "
							+ "A.SN_CLOSURE_CLOSE='N' AND A.CUSTOMER_CD=E.CUSTOMER_CD AND "
							+ "E.EMP_CD='"+Emp_cd+"' " +
							"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				} else {
					queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
							"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
							"A.VERIFY_FLAG, A.APPROVE_FLAG, A.SN_REF_NO, " +
							"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
							"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
							"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
							"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
							"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.SN_CLOSURE_REQUEST='Y' AND A.SN_CLOSURE_CLOSE='N' " +
							"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				}
			}
			//System.out.println("Query For Fetching SN List = "+queryString);
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{
			    vSN_No.add(rset1.getString(1)==null?"":rset1.getString(1));
			    vSN_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
			    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
			    FGSA_no.add(rset1.getString(4)==null?"":rset1.getString(4));
				vRev_No.add(rset1.getString(5)==null?"":rset1.getString(5));
				vSN_TCQ.add(rset1.getString(6)==null?"":rset1.getString(6));
				vTCQ_UNIT.add(rset1.getString(7)==null?"":rset1.getString(7));
				vSN_RATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				vRATE_UNIT.add(rset1.getString(9)==null?"":rset1.getString(9));
				buyer_nm.add(rset1.getString(10)==null?"":rset1.getString(10));
				vBUYER_ABBR.add(rset1.getString(11)==null?"":rset1.getString(11));
				vSN_name.add(rset1.getString(12)==null?"":rset1.getString(12));  
				vSN_verify.add(rset1.getString(13)==null?"":rset1.getString(13)); 
				vSN_approve.add(rset1.getString(14)==null?"":rset1.getString(14));
				String sn_ref_no = rset1.getString(15)==null?"":rset1.getString(15);
				if(!sn_ref_no.trim().equals(""))
				{
					vSN_Ref_No.add(" - ("+sn_ref_no+")");
				}
				else
				{
					vSN_Ref_No.add("");
				}
				TCQ_REQUEST_FLAG.add(rset1.getString(16)==null?"":rset1.getString(16));
				TCQ_REQUEST_SIGN.add(rset1.getString(17)==null?"":rset1.getString(17));
				TCQ_REQUEST_QTY.add(rset1.getString(18)==null?"":rset1.getString(18));
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_Closure_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetch_TCQ_List() 
	{
		try 
		{
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
				"A.VERIFY_FLAG, A.APPROVE_FLAG, A.SN_REF_NO, " +
				"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+bscode+"' AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.TCQ_REQUEST_FLAG='Y' AND A.TCQ_REQUEST_CLOSE='N' " +
				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
			}
			else
			{
				if(Customer_access_flag.equals("Y"))
				{
					queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
							"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
							"A.VERIFY_FLAG, A.APPROVE_FLAG, A.SN_REF_NO, " +
							"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
							"FMS7_UNIT_MST D, SEC_EMP_CUSTOMER_ALLOC_MST E "
							+ "WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
							"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
							"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
							"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.TCQ_REQUEST_FLAG='Y' AND A.TCQ_REQUEST_CLOSE='N' "
							+ "AND E.CUSTOMER_CD=A.CUSTOMER_CD AND E.EMP_CD='"+Emp_cd+"' " +
							"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				} else {
					queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
							"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
							"A.VERIFY_FLAG, A.APPROVE_FLAG, A.SN_REF_NO, " +
							"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
							"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
							"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
							"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
							"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.TCQ_REQUEST_FLAG='Y' AND A.TCQ_REQUEST_CLOSE='N' " +
							"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				}
			}
			//System.out.println("Query For Fetching SN List = "+queryString);
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{
			    vSN_No.add(rset1.getString(1)==null?"":rset1.getString(1));
			    vSN_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
			    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
			    FGSA_no.add(rset1.getString(4)==null?"":rset1.getString(4));
				vRev_No.add(rset1.getString(5)==null?"":rset1.getString(5));
				vSN_TCQ.add(rset1.getString(6)==null?"":rset1.getString(6));
				vTCQ_UNIT.add(rset1.getString(7)==null?"":rset1.getString(7));
				vSN_RATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				vRATE_UNIT.add(rset1.getString(9)==null?"":rset1.getString(9));
				buyer_nm.add(rset1.getString(10)==null?"":rset1.getString(10));
				vBUYER_ABBR.add(rset1.getString(11)==null?"":rset1.getString(11));
				vSN_name.add(rset1.getString(12)==null?"":rset1.getString(12));  
				vSN_verify.add(rset1.getString(13)==null?"":rset1.getString(13)); 
				vSN_approve.add(rset1.getString(14)==null?"":rset1.getString(14));
				String sn_ref_no = rset1.getString(15)==null?"":rset1.getString(15);
				if(!sn_ref_no.trim().equals(""))
				{
					vSN_Ref_No.add(" - ("+sn_ref_no+")");
				}
				else
				{
					vSN_Ref_No.add("");
				}
				TCQ_REQUEST_FLAG.add(rset1.getString(16)==null?"":rset1.getString(16));
				TCQ_REQUEST_SIGN.add(rset1.getString(17)==null?"":rset1.getString(17));
				TCQ_REQUEST_QTY.add(rset1.getString(18)==null?"":rset1.getString(18));
			}
			
			//For LOA
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = 	"SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD, A.TENDER_NO, " +
								"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, " +
								"A.VERIFY_FLAG, A.APPROVE_FLAG, A.LOA_REF_NO, " +
								"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_LOA_MST A, FMS7_CUSTOMER_MST C, " +
								"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM " +
								"FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
								"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+bscode+"' AND " +
								"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
								"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.TCQ_REQUEST_FLAG='Y' AND A.TCQ_REQUEST_CLOSE='N' " +
								"ORDER BY A.customer_cd,A.tender_no,A.loa_no";
			}
			else
			{
				if(Customer_access_flag.equals("Y"))
				{
					queryString =	"SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD, A.TENDER_NO, " +
									"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, " +
									"A.VERIFY_FLAG, A.APPROVE_FLAG, A.LOA_REF_NO, " +
									"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_LOA_MST A, FMS7_CUSTOMER_MST C, " +
									"FMS7_UNIT_MST D, SEC_EMP_CUSTOMER_ALLOC_MST E "
									+ "WHERE A.FLAG='T' AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM " +
									"FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
									"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
									"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
									"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.TCQ_REQUEST_FLAG='Y' AND A.TCQ_REQUEST_CLOSE='N' "
									+ "AND E.CUSTOMER_CD=A.CUSTOMER_CD AND E.EMP_CD='"+Emp_cd+"' " +
									"ORDER BY A.customer_cd,A.TENDER_NO,A.loa_no";
				} else {
					queryString = 	"SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD, A.TENDER_NO, " +
									"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, " +
									"A.VERIFY_FLAG, A.APPROVE_FLAG, A.LOA_REF_NO, " +
									"A.TCQ_REQUEST_FLAG,A.TCQ_REQUEST_SIGN,A.TCQ_REQUEST_QTY FROM FMS7_LOA_MST A, FMS7_CUSTOMER_MST C, " +
									"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM " +
									"FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
									"AND B.FLAG='T') AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
									"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
									"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND A.TCQ_REQUEST_FLAG='Y' AND A.TCQ_REQUEST_CLOSE='N' " +
									"ORDER BY A.customer_cd,A.TENDER_NO,A.loa_no";
				}
			}
			System.out.println("Query For Fetching SN List = "+queryString);
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{
			    LOA_No.add(rset1.getString(1)==null?"":rset1.getString(1));
			    LOA_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
			    buyer_cd_loa.add(rset1.getString(3)==null?"":rset1.getString(3));
			    TENDER_no.add(rset1.getString(4)==null?"":rset1.getString(4));
				vRev_No_tender.add("0");
				vLOA_TCQ.add(rset1.getString(5)==null?"":rset1.getString(5));
				vloa_TCQ_UNIT.add(rset1.getString(6)==null?"":rset1.getString(6));
				vLOA_RATE.add(rset1.getString(7)==null?"":rset1.getString(7));
				vLOA_RATE_UNIT.add(rset1.getString(8)==null?"":rset1.getString(8));
				buyer_nm_LOA.add(rset1.getString(9)==null?"":rset1.getString(9));
				vBUYER_ABBR_LOA.add(rset1.getString(10)==null?"":rset1.getString(10));
				vLOA_name.add(rset1.getString(11)==null?"":rset1.getString(11));  
				vLOA_verify.add(rset1.getString(12)==null?"":rset1.getString(12)); 
				vLOA_approve.add(rset1.getString(13)==null?"":rset1.getString(13));
				String loa_ref_no = rset1.getString(14)==null?"":rset1.getString(14);
				if(!loa_ref_no.trim().equals(""))
				{
					vLOA_Ref_No.add(" - ("+loa_ref_no+")");
				}
				else
				{
					vLOA_Ref_No.add("");
				}
				TCQ_REQUEST_FLAG_LOA.add(rset1.getString(15)==null?"":rset1.getString(15));
				TCQ_REQUEST_SIGN_LOA.add(rset1.getString(16)==null?"":rset1.getString(16));
				TCQ_REQUEST_QTY_LOA.add(rset1.getString(17)==null?"":rset1.getString(17));
			}
			System.out.println("-TENDER_no---"+vLOA_TCQ);
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	String Emp_cd = "";
	String Customer_access_flag = "N";
	
	public void Customer_DTL_WITH_ACCESS()
	{
		try
		{
			queryString = "SELECT A.CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR "
					+ "FROM FMS7_CUSTOMER_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B WHERE "
					+ "B.EMP_CD='"+Emp_cd+"' AND B.CUSTOMER_CD=A.CUSTOMER_CD "
					+ "and (A.DORMANT_FLAG!='Y' OR A.DORMANT_FLAG IS NULL) "
					+ "ORDER BY A.CUSTOMER_CD";
//			System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
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
	
	public void fetch_transporter_contract_list()//RG20200129
	{
		try
		{
			//System.out.println("in itttttttttttttttttttttt");
			
			for(int i=0;i<trans_cd.size();i++){
				for(int k=0;k<plant_cd.size();k++){
					String exit_point_cd=Buyer_cd+""+plant_cd.elementAt(k);
					queryString="SELECT MAPPING_ID,TO_CHAR(CONT_ST_DT,'DD/MM/YYYY'),TO_CHAR(CONT_END_DT,'DD/MM/YYYY'),DCQ,MDQ,cont_mapping_id FROM FMS_CONT_MST WHERE "
							+ "TRANS_CD='"+trans_cd.elementAt(i)+"' AND PARTY_CD='"+trans_cd.elementAt(i)+"' AND EXIT_PT_CD='"+exit_point_cd+"' AND CONT_AGR_NO='"+agr_no+"' "
							+ "AND CONT_AGR_REV_NO='"+agr_rev_no+"' AND CONTRACT_NO='"+cont_no+"' AND CONTRACT_REV_NO='"+cont_rev_no+"' AND CONT_AGR_TYPE='"+cont_agr_type+"'"
							+ "	AND CONT_CUST_CD='"+Buyer_cd+"'";
					System.out.println("---queryString---"+queryString);
					rset=stmt.executeQuery(queryString);
					while(rset.next()){
						trans_plant_map.put(trans_cd.elementAt(i)+"-"+plant_cd.elementAt(k),rset.getString(1));
						trans_plant_map_cont_st_dt.put(trans_cd.elementAt(i)+"-"+plant_cd.elementAt(k),rset.getString(2));
						trans_plant_map_cont_end_dt.put(trans_cd.elementAt(i)+"-"+plant_cd.elementAt(k),rset.getString(3));
						trans_plant_map_dcq.put(trans_cd.elementAt(i)+"-"+plant_cd.elementAt(k),rset.getString(4));
						trans_plant_map_mdq.put(trans_cd.elementAt(i)+"-"+plant_cd.elementAt(k),rset.getString(5));
						cont_mapp_id.put(trans_cd.elementAt(i)+"-"+plant_cd.elementAt(k),rset.getString(6)==null?"":rset.getString(6));
					}
					queryString="SELECT PARTY_ABR FROM FMS_PARTY_MST WHERE PARTY_CD='"+trans_cd.elementAt(i)+"'";
					rset=stmt.executeQuery(queryString);
					if(rset.next()){
						trans_nm.put(trans_cd.elementAt(i)+"-"+plant_cd.elementAt(k),rset.getString(1));
					}
					queryString="SELECT DELV_PT_ABR FROM FMS_DELV_MST WHERE DELV_PT_CD='"+exit_point_cd+"'";
					rset=stmt.executeQuery(queryString);
					if(rset.next()){
						plant_nm.put(trans_cd.elementAt(i)+"-"+plant_cd.elementAt(k),rset.getString(1));
					}
					
				}
			}
		//	System.out.println("--trans_plant_map--"+trans_plant_map);
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
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST "
					+ "where (DORMANT_FLAG!='Y' OR DORMANT_FLAG IS NULL) "//AND CUSTOMER_CD IN (SELECT CUSTOMER_CD FROM DLNG_TENDER_MST WHERE FLAG='Y') "
					+ "ORDER BY CUSTOMER_NAME";
			System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"":rset.getString(1));	
			//	CUST_NM.add(rset.getString(3)==null?"":rset.getString(3));
				CUST_NM.add(rset.getString(3)+" - "+rset.getString(2));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void TENDER_Customer_DTL()
	{
		try
		{
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST "
					+ "where (DORMANT_FLAG!='Y' OR DORMANT_FLAG IS NULL) AND CUSTOMER_CD IN (SELECT CUSTOMER_CD FROM DLNG_TENDER_MST WHERE FLAG='Y') "
					+ "ORDER BY CUSTOMER_NAME";
			System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"":rset.getString(1));	
			//	CUST_NM.add(rset.getString(3)==null?"":rset.getString(3));
				CUST_NM.add(rset.getString(3)+" - "+rset.getString(2));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void FLSA_Customer_DTL()
	{
		try
		{
			/*queryString = " SELECT distinctCUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST "
					+ " where (DORMANT_FLAG!='Y' OR DORMANT_FLAG IS NULL) AND CUSTOMER_CD IN (SELECT CUSTOMER_CD) FROM DLNG_FLSA_MST WHERE FLAG='Y') "
					+ " ORDER BY CUSTOMER_NAME";*/
			
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A where"
					+ " A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B WHERE A.CUSTOMER_CD=B.CUSTOMER_CD )"
					+ " AND (DORMANT_FLAG!='Y' OR DORMANT_FLAG IS NULL) AND "
					+ " CUSTOMER_CD IN (SELECT distinct(CUSTOMER_CD) FROM DLNG_FLSA_MST WHERE FLAG='Y')"
					+ "  ORDER BY CUSTOMER_NAME";
			
			System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"":rset.getString(1));	
			//	CUST_NM.add(rset.getString(3)==null?"":rset.getString(3));
				CUST_NM.add(rset.getString(3)+" - "+rset.getString(2));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void fetchcontarctDate()
	{
		try
		{
			//JHP start

			queryString = "SELECT to_char(START_DT,'dd/mm/yyyy'), to_char(END_DT,'dd/mm/yyyy')," +
						  "RE_GAS_BASE,STATUS, to_char(REV_DT,'dd/mm/yyyy'), NO_OF_CARGO " +
						  "FROM FMS7_RE_GAS_MST where Customer_cd='"+Buyer_cd+"' AND " +
						  "RE_GAS_NO='"+re_gas_no+"' AND REV_NO='"+re_gas_rev_no+"'";
		//	System.out.println("RE_GAS Fetch DATA Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				frm_dt=rset.getString(1)==null?"":rset.getString(1);
				to_dt=rset.getString(2)==null?"":rset.getString(2);
				
			}
			
			//JHP End
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public void Fetch_RE_GAS_CARGO_DTL()
	{
		try
		{
			/*ADDED BY RG 20140924 */
			
			queryString="SELECT ADJUST_FLAG FROM FMS7_RE_GAS_MST WHERE RE_GAS_NO='"+re_gas_no+"' AND CUSTOMER_CD='"+Buyer_cd+"' ";
		//	System.out.println(".......Fetching master value..."+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				ADJUST_FLAG_REGAS_MST=rset.getString(1)==null?"N":rset.getString(1);
			}
			
			queryString="select compo_cd,compo_nm from fms7_compo_mst where flag='Y' and compo_cd!='3' ";
			//System.out.println("Fetching..component dtls..."+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				component_cd.add(rset.getString(1));
				component_nm.add(rset.getString(2));
			}
			
			/*......................*/
			
			for(int i=1; i<=Integer.parseInt(no_of_cargo); i++)
			{
				queryString = "SELECT cargo_ref_no, " +
							"TO_CHAR(edq_from_dt,'dd/mm/yyyy'), " +
							  "TO_CHAR(edq_to_dt,'dd/mm/yyyy')," +
							  " TO_CHAR(actual_recpt_dt,'dd/mm/yyyy'), " +
							  "TO_CHAR(contract_start_dt,'dd/mm/yyyy')," +
							  " TO_CHAR(contract_end_dt,'dd/mm/yyyy'), " +
							  "ship_cd,adq_qty,sys_use_gas," +
							  "qty_to_be_supply,dcq_qty,re_gas_tarif, " +
							  "qty_unit_cd,REGAS_CLOSURE_REQUEST,REGAS_CLOSURE_CLOSE," +
							  "TO_CHAR(REGAS_CLOSURE_DT,'dd/mm/yyyy'),REGAS_CLOSURE_QTY,BOE_NO," +
							  "TO_CHAR(BOE_DT,'dd/mm/yyyy'),BOE_QTY, NVL(SUPP_CD,'0'), " +
							  "NVL(SUPP_NM,''),NVL(QQ_NO,''),TO_CHAR(QQ_DT,'DD/MM/YYYY') " +
							  "FROM FMS7_RE_GAS_CARGO_DTL WHERE customer_cd="+Buyer_cd+" AND " +
							  "re_gas_no="+re_gas_no+" AND cargo_seq_no="+i+"";
			//	System.out.println("REGAS:QRY-R2001:SELECT:FMS7_RE_GAS_CARGO_DTL: "+queryString);
				//System.out.println("RE-GAS Cargo Contract Detail Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					String temp_cargo_ref_no = rset.getString(1)==null?"":rset.getString(1);
					String temp_sug = rset.getString(9)==null?"":rset.getString(9);
					String temp_teriff = rset.getString(12)==null?"":rset.getString(12);
					
					if(!temp_sug.trim().equals(""))
					{
						temp_sug = nf.format(Double.parseDouble(temp_sug.trim()));
					}
					
					if(!temp_teriff.trim().equals(""))
					{
						temp_teriff = nf2.format(Double.parseDouble(temp_teriff.trim()));
					}
					
					if(temp_cargo_ref_no.equals(""))
					{
						String cargo_ref_no = "";
						if(!Buyer_cd.trim().equals("") && !Buyer_cd.trim().equals("0") && !re_gas_no.trim().equals("") && !re_gas_no.trim().equals("0"))
						{
							if(Integer.parseInt(Buyer_cd.trim())<10)
							{
								cargo_ref_no += "RE0"+Buyer_cd.trim();
							}
							else
							{
								cargo_ref_no += "RE"+Buyer_cd.trim();
							}
							
							if(Integer.parseInt(re_gas_no.trim())<10)
							{
								cargo_ref_no += "0"+re_gas_no.trim();
							}
							else
							{
								cargo_ref_no += re_gas_no.trim();
							}
							
							if(i<10)
							{
								cargo_ref_no += "0"+i;
							}
							else
							{
								cargo_ref_no += ""+i;
							}
						}						
						CARGO_REF_NO.add(cargo_ref_no);
						CARGO_NO_EXIST.add("N");
					}
					else
					{
						CARGO_REF_NO.add(rset.getString(1)==null?"":rset.getString(1));
						CARGO_NO_EXIST.add("Y");
					}
					double qty_supply = 0;
					EDQ_FROM_DT.add(rset.getString(2)==null?"":rset.getString(2));
					EDQ_TO_DT.add(rset.getString(3)==null?"":rset.getString(3));
					ACTUAL_RECPT_DT.add(rset.getString(4)==null?"":rset.getString(4));
					CONTRACT_START_DT.add(rset.getString(5)==null?"":rset.getString(5));
					CONTRACT_END_DT.add(rset.getString(6)==null?"":rset.getString(6));
					SHIP_CD.add(rset.getString(7)==null?"0":rset.getString(7));
					ADQ_QTY.add(rset.getString(8)==null?"":rset.getString(8));
					SYS_USE_GAS.add(temp_sug);
					qty_supply = Double.parseDouble(rset.getString(10)==null?"0":rset.getString(10));
					//QTY_TO_BE_SUPPLY.add(rset.getString(10)==null?"":rset.getString(10));
					DCQ_QTY.add(rset.getString(11)==null?"":rset.getString(11));
					RE_GAS_TARIFF.add(temp_teriff);
					QTY_UNIT_CD.add(rset.getString(13)==null?"1":rset.getString(13));
					REGAS_CLOSURE_REQUEST.add(rset.getString(14)==null?"":rset.getString(14));
					REGAS_CLOSURE_CLOSE.add(rset.getString(15)==null?"":rset.getString(15));
					REGAS_CLOSURE_DT.add(rset.getString(16)==null?"-":rset.getString(16));
					REGAS_CLOSURE_QTY.add(rset.getString(17)==null?"-":rset.getString(17));
					if(qty_supply!=0)
					{
						QTY_TO_BE_SUPPLY.add(nf3.format(qty_supply));
					}
					else
					{
						QTY_TO_BE_SUPPLY.add("0");
					}
					BOE_NO.add(rset.getString(18)==null?"":rset.getString(18));
					BOE_DT.add(rset.getString(19)==null?"":rset.getString(19));
					BOE_QTY.add(rset.getString(20)==null?"":rset.getString(20));
					SUPP_CD.add(rset.getString(21)==null?"":rset.getString(21));
					SUPP_NM.add(rset.getString(22)==null?"":rset.getString(22));
					QQ_NO.add(rset.getString(23)==null?"":rset.getString(23));
					QQ_DT.add(rset.getString(24)==null?"":rset.getString(24));
					
					
					String mapping_id=""+Buyer_cd+"-"+re_gas_no+"-"+0+"-"+i+"-"+0+"-"+"R";
					int countCompo=0;
					for(int ii=0;ii<component_cd.size();ii++)
					{
						queryString = "select price_rate,currency_cd,flag " +
								"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
								"price_cd='"+component_cd.elementAt(ii)+"'  AND FLAG='Y' ";
					//	System.out.println("Fetching flag of sn..."+queryString);
						rset1=stmt1.executeQuery(queryString);
						if(rset1.next())
						{
							countCompo++;
							if(component_cd.elementAt(ii).equals("1"))
							{
								ADJUST_FLAG_REGAS.add(rset1.getString(3)==null?"N":rset1.getString(3)); //RG20140924
								ADJUST_AMT_REGAS.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(""+rset1.getString(1))));	//RG20140924
								ADJUST_CUR_REGAS.add(rset1.getString(2)==null?"2":rset1.getString(2));	//RG20140924
							}
							else if(component_cd.elementAt(ii).equals("2"))
							{
								DISCOUNT_PRICE_REGAS.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(""+rset1.getString(1))));	//RG20140924
								DISCOUNT_FLAG_REGAS.add(rset1.getString(3)==null?"N":rset1.getString(3));	//RG20140924
							}
//							else if(component_cd.elementAt(ii).equals("3"))
//							{
//								TARIFF_FLAG_REGAS.add(rset1.getString(3)==null?"N":rset1.getString(3));	//RG20140924
//								TARIFF_INR_REGAS.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(""+rset1.getString(1))));	//RG20140924
//							}
						}
						else
						{
							if(component_cd.elementAt(ii).equals("1"))
							{
								ADJUST_FLAG_REGAS.add("N"); //RG20140924
								ADJUST_AMT_REGAS.add("");	//RG20140924
								ADJUST_CUR_REGAS.add("2");	//RG20140924
							}
							else if(component_cd.elementAt(ii).equals("2"))
							{
								DISCOUNT_PRICE_REGAS.add("");	//RG20140924
								DISCOUNT_FLAG_REGAS.add("N");	//RG20140924
							}
//							else if(component_cd.elementAt(ii).equals("3"))
//							{
//								TARIFF_FLAG_REGAS.add("N");	//RG20140924
//								TARIFF_INR_REGAS.add("");	//RG20140924
//							}
						}
					}
					
//					JHP start
					String temp_dt=rset.getString(5)==null?"":rset.getString(5);
					queryString = "select count(*) FROM FMS7_INVOICE_MST WHERE customer_cd='"+Buyer_cd+"' and  to_date('"+temp_dt+"','dd/mm/yyyy') between period_start_dt and period_start_dt";
			//		System.out.println("REGAS:QRY-R2004:SELECT:FMS7_INVOICE_MST: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						count.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						count.add("0");
					}
					//JHP END
				}
				else
				{
					String cargo_ref_no = "";
					if(!Buyer_cd.trim().equals("") && !Buyer_cd.trim().equals("0") && !re_gas_no.trim().equals("") && !re_gas_no.trim().equals("0"))
					{
						if(Integer.parseInt(Buyer_cd.trim())<10)
						{
							cargo_ref_no += "RE0"+Buyer_cd.trim();
						}
						else
						{
							cargo_ref_no += "RE"+Buyer_cd.trim();
						}
						
						if(Integer.parseInt(re_gas_no.trim())<10)
						{
							cargo_ref_no += "0"+re_gas_no.trim();
						}
						else
						{
							cargo_ref_no += re_gas_no.trim();
						}
						
						if(i<10)
						{
							cargo_ref_no += "0"+i;
						}
						else
						{
							cargo_ref_no += ""+i;
						}
					}
					CARGO_REF_NO.add(cargo_ref_no);
					EDQ_FROM_DT.add("");
					EDQ_TO_DT.add("");
					ACTUAL_RECPT_DT.add("");
					CONTRACT_START_DT.add("");
					CONTRACT_END_DT.add("");
					SHIP_CD.add("0");
					ADQ_QTY.add("");
					SYS_USE_GAS.add("");
					QTY_TO_BE_SUPPLY.add("");
					DCQ_QTY.add("");
					RE_GAS_TARIFF.add("");
					QTY_UNIT_CD.add("1");
					REGAS_CLOSURE_REQUEST.add("");
					REGAS_CLOSURE_CLOSE.add("");	
					CARGO_NO_EXIST.add("N");
					REGAS_CLOSURE_DT.add("-");
					REGAS_CLOSURE_QTY.add("-");
					BOE_NO.add("");
					BOE_DT.add("");
					BOE_QTY.add("");
					SUPP_CD.add("0");
					SUPP_NM.add("");
					count.add("0");
					QQ_NO.add("");
					QQ_DT.add("");
					ADJUST_FLAG_REGAS.add("N"); //RG20140924
					ADJUST_AMT_REGAS.add("");	//RG20140924
					ADJUST_CUR_REGAS.add("2");	//RG20140924
					DISCOUNT_PRICE_REGAS.add("");	//RG20140924
					DISCOUNT_FLAG_REGAS.add("N");	//RG20140924
					TARIFF_FLAG_REGAS.add("N");	//RG20140924
					TARIFF_INR_REGAS.add("");	//RG20140924
				}
			}
			
			for(int i=0; i<SHIP_CD.size(); i++)
			{
				queryString = "SELECT ship_name FROM FMS7_SHIP_MST WHERE ship_cd="+SHIP_CD.elementAt(i)+"";
		//		System.out.println("REGAS:QRY-R2002:SELECT:FMS7_SHIP_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					SHIP_NAME.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					SHIP_NAME.add("");
				}
				
				queryString = "SELECT unit_abr FROM FMS7_UNIT_MST WHERE unit_cd="+QTY_UNIT_CD.elementAt(i)+"";
			//	System.out.println("REGAS:QRY-R2003:SELECT:FMS7_UNIT_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					QTY_UNIT_ABR.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					QTY_UNIT_ABR.add("");
				}
			}
			
			/* SB20110930 for(int i=0; i<SUPP_CD.size(); i++)
			{
				queryString = "SELECT TRADER_NAME FROM FMS7_TRADER_MST WHERE TRADER_CD="+SUPP_CD.elementAt(i)+"";
				System.out.println("REGAS:QRY-R2004:SELECT:FMS7_TRADER_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					SUPP_NM.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					SUPP_NM.add("");
				}
			}*/
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	String own_cargo_sn_allocated_qty = "0";
	String own_cargo_liability_qty = "0";
	String own_cargo_surplus_qty = "0";
	String own_cargo_flag = "";
	
	public void Fetch_CARGO_LIST_FOR_TCQ_ADJUSTMENT()
	{
		try
		{
			double losses_percentage = 1.5;
			double total_balance_qty = 0;
			double total_allocated_qty = 0;
			double reconciled_qty = 0;
			
			queryString = "SELECT DISTINCT(CARGO_REF_NO) " +
						  "FROM FMS7_SN_CARGO_DTL " +
						  "WHERE CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" " +
						  //"AND FGSA_REV_NO="+FGSA_REVNo+" " +
						  "AND SN_NO="+SN_CD+" " +
						  "ORDER BY CARGO_REF_NO";			
			//System.out.println("SN Cargo Relationship Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String delv_to_dt = "";
				double total = 0;
				double total2 = 0;
				String temp_cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);				
				cargo_Ref_No.add(temp_cargo_ref_no);				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_SN_CARGO_DTL WHERE CARGO_REF_NO="+temp_cargo_ref_no+"";
				//System.out.println("Allocated Total Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_LOA_CARGO_DTL WHERE CARGO_REF_NO="+temp_cargo_ref_no+"";
				//System.out.println("Allocated Total Cargo Qty Query (LOA) = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));
				}				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_SN_CARGO_DTL WHERE " +
							   "CARGO_REF_NO="+temp_cargo_ref_no+" AND " +
							   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
							   "FGSA_REV_NO="+FGSA_REVNo+" AND SN_NO="+SN_CD+"";
				//System.out.println("Allocated Cargo-SN Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}				
				total_allocated_qty += Double.parseDouble(nf.format(total2));					
				cargo_Consumed_Qty.add(nf.format(total));
				cargo_SN_Consumed_Qty.add(nf.format(total2));				
				double actual_volume = 0;
			  	double l = 0;
			  	double conf_price = 0;
			  	double cd_charge_per_mmbtu = 0;
			  	double vol_available_for_sale = 0;
			  	
				queryString1 = "SELECT A.CARGO_REF_CD, A.MAN_CONF_CD, A.MAN_CD, B.TRADER_NAME, " +
							  "TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'), " +
							  "A.PRICE, A.CONFIRM_VOL, C.UNIT_ABR, D.CONFIRM_PRICE " +
							  "FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_TRADER_MST B, " +
							  "FMS7_UNIT_MST C, FMS7_MAN_CONFIRM_MST D, FMS7_MAN_REQ_MST E WHERE " +
							  "E.MAN_CD=A.MAN_CD AND A.CARGO_REF_CD="+temp_cargo_ref_no+" AND "+ 
							  "A.MAN_CONF_CD=D.MAN_CONF_CD AND E.TRD_CD=B.TRADER_CD AND " +
							  "A.UNIT_CD=C.UNIT_CD";				
				rset1 = stmt1.executeQuery(queryString1);
				//System.out.println(queryString1);
				if(rset1.next())
				{
					delv_to_dt = rset1.getString(6)==null?"":rset1.getString(6);
				  	cargo_Seller_Nm.add(rset1.getString(4)==null?"":rset1.getString(4));
				  	cargo_Window_From_Dt.add(rset1.getString(5)==null?"":rset1.getString(5));
				  	cargo_Window_To_Dt.add(rset1.getString(6)==null?"":rset1.getString(6));
				  	
				  	conf_price = Double.parseDouble(nf1.format(Double.parseDouble(rset1.getString(7)==null?"0":rset1.getString(7))));
				  	if(conf_price<=0.0001)
				  	{
				  		conf_price = Double.parseDouble(nf1.format(Double.parseDouble(rset1.getString(10)==null?"0":rset1.getString(10))));
				  	}
				  	
				  	cargo_Conf_Price.add(nf1.format(conf_price));
				  	
				  	losses_percentage = 1.5;				  	
				  					  	
				  	queryString2 = "SELECT A.INTERNAL_CONSUMPTION " +
								  "FROM FMS7_TANK_MASTER_DTL A WHERE " +
								  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
								  "B.TANK_DTL_DT<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";					
					//System.out.println("Tank Master Details Fetch Query = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						String internal_consumption = rset2.getString(1)==null?"":rset2.getString(1);
						if(!internal_consumption.trim().equals(""))
						{
							losses_percentage = Double.parseDouble(internal_consumption);
						}
					}
					
					queryString2 = "SELECT A.PERCENTAGE " +
								  "FROM FMS7_CONSUMPTION_MST A WHERE " +
								  "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
								  "B.EFF_DT<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";					
					//System.out.println("Internal Consumption Percentage Fetch Query = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						String internal_consumption = rset2.getString(1)==null?"":rset2.getString(1);
						if(!internal_consumption.trim().equals(""))
						{
							losses_percentage = Double.parseDouble(internal_consumption);
						}
					}
		
				  	if(rset1.getString(9).trim().equalsIgnoreCase("MMBTU"))
				  	{
				  		queryString2 = "SELECT SUM(A.RECONCIL_QTY) " +
									   "FROM FMS7_CARGO_RECONCIL_DTL A " +
									   "WHERE A.CARGO_REF_NO="+temp_cargo_ref_no+"";
						//System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString2); 
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							reconciled_qty = Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
						}
						else
						{
							reconciled_qty = 0;
						}
						cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
				  		actual_volume = Double.parseDouble(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8)))));
				  		vol_available_for_sale = Double.parseDouble(nf.format((actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100)));
				  		cargo_Confirmed_Qty.add(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8)))));
				  		cargo_Available_For_Sale_Qty.add(nf.format(vol_available_for_sale));
				  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
				  		if((vol_available_for_sale-total)>0)
				  		{
				  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
				  		}
				  	}
				  	else if(rset1.getString(9).trim().equalsIgnoreCase("TBTU"))
				  	{
				  		queryString2 = "SELECT SUM(A.RECONCIL_QTY) " +
									   "FROM FMS7_CARGO_RECONCIL_DTL A " +
									   "WHERE A.CARGO_REF_NO="+temp_cargo_ref_no+"";
						//System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString2); 
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							reconciled_qty = Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
						}
						else
						{
							reconciled_qty = 0;
						}
						cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
				  		l = Double.parseDouble(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8))*1000000)));
				  		actual_volume = l;
				  		vol_available_for_sale = Double.parseDouble(nf.format((actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100)));
				  		cargo_Confirmed_Qty.add(nf.format(l));
				  		cargo_Available_For_Sale_Qty.add(nf.format(vol_available_for_sale));
				  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
				  		if((vol_available_for_sale-total)>0)
				  		{
				  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
				  		}
				  	}
				  	else
				  	{
				  		reconciled_qty = 0;
				  		cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
				  		cargo_Confirmed_Qty.add(nf.format(l));
				  		cargo_Available_For_Sale_Qty.add(nf.format(l));
				  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
				  		if((vol_available_for_sale-total)>0)
				  		{
				  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
				  		}
				  	}
				}
				else
				{
					reconciled_qty = 0;
					cargo_Seller_Nm.add("");
				  	cargo_Window_From_Dt.add("");
				  	cargo_Window_To_Dt.add("");
				  	cargo_Conf_Price.add("0.0000");
				  	cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
				  	cargo_Confirmed_Qty.add(nf.format(l));
			  		cargo_Available_For_Sale_Qty.add(nf.format(l));
			  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
				}				
				//Following Logic Has Been Introduced By Samik Shah On 13th August, 2010 To Calculate Custom Tax Amount ...
			  	String tax_amt = "";
			  	String tax_str_cd = "0";				
				queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
							  "WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B WHERE " +
							  "B.APP_DATE<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
				//System.out.println("Custom Duty Details Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
				}				
				//System.out.println("tax_str_cd = "+tax_str_cd);				
				queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
							  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
							  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
							  "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
				//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					//String tax_cd = rset1.getString(1);
					//String tax_factor = nf.format(Double.parseDouble(rset1.getString(2)));										
					if(rset1.getString(3).equals("1"))
					{
						tax_amt = nf1.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset1.getString(2)))/100);
					}
					else if(rset1.getString(3).equals("2"))
					{
						queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
									   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
									   "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
						//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2);
				 		rset2=stmt2.executeQuery(queryString2);
				 		if(rset2.next())
				 		{
					 			if(rset2.getString(3).equals("1"))
								{
									tax_amt = nf1.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset2.getString(2)))/100);
								}
								
					 			tax_amt = nf1.format((Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100);
				 		}
				 		else
				 		{
				 			tax_amt = ""+0.00;
				 		}			 		
					}
					else
					{
						tax_amt = ""+0.00;
					}
					
					cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
				}				
				cargo_Custom_Duty.add(nf1.format(cd_charge_per_mmbtu));
				cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price+cd_charge_per_mmbtu));
				//Following Logic Has Been Commented By Samik Shah On 13th August, 2010 ...
				/*queryString1 = "SELECT EXCHG_RATE,TOTAL_CD_AMT,EXP_DELV_QTY " +
							   "FROM FMS7_CUSTOM_DUTY WHERE " +
							   "CARGO_REF_NO="+temp_cargo_ref_no+"";
				System.out.println(queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String exchg_rate = rset1.getString(1)==null?"1":rset1.getString(1);
					String custom_duty_amt = rset1.getString(2)==null?"0":rset1.getString(2);
					String exp_delv_qty = rset1.getString(3)==null?"1":rset1.getString(3);
					
					cd_charge_per_mmbtu = Double.parseDouble(nf1.format((Double.parseDouble(custom_duty_amt)/Double.parseDouble(exp_delv_qty))/Double.parseDouble(exchg_rate)));
					
					cargo_Custom_Duty.add(nf1.format((Double.parseDouble(custom_duty_amt)/Double.parseDouble(exp_delv_qty))/Double.parseDouble(exchg_rate)));
					cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price+cd_charge_per_mmbtu));
				}
				else
				{
					cargo_Custom_Duty.add(nf1.format(Double.parseDouble("0")));
					cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price));
				}*/
				cargo_Type_Flag.add("SELF");
			}			
			//System.out.println("var_tcq = "+var_tcq+",  total_balance_qty = "+total_balance_qty+",  And  tcq_sign = "+tcq_sign);
			
			//if(Double.parseDouble(var_tcq)>0 && Double.parseDouble(var_tcq)>total_balance_qty && tcq_sign.equals("+"))
			//{
			if(Double.parseDouble(var_tcq)>0 && tcq_sign.equals("+"))
			{
				queryString = "SELECT DISTINCT(A.CARGO_REF_NO) " +
							  "FROM FMS7_SN_CARGO_DTL A WHERE A.CARGO_REF_NO NOT IN " +
							  "(SELECT DISTINCT(B.CARGO_REF_NO) " +
							  "FROM FMS7_SN_CARGO_DTL B " +
							  "WHERE B.CUSTOMER_CD="+Buyer_cd+" And B.FGSA_NO="+FGSA_cd+" AND " +
							  "B.FGSA_REV_NO="+FGSA_REVNo+" AND B.SN_NO="+SN_CD+") " +
							  "ORDER BY A.CARGO_REF_NO";
				
				//System.out.println("SN Cargo Relationship Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String delv_to_dt = "";
					double total = 0;
					double total2 = 0;
					String temp_cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);					
					cargo_Ref_No.add(temp_cargo_ref_no);					
					queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_SN_CARGO_DTL WHERE CARGO_REF_NO="+temp_cargo_ref_no+"";
					//System.out.println("Allocated Cargo-SN Qty Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
						total += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
					}					
					queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_LOA_CARGO_DTL WHERE CARGO_REF_NO="+temp_cargo_ref_no+"";
					//System.out.println("Allocated Cargo-SN Qty Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
						total += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));
					}					
					cargo_Consumed_Qty.add(nf.format(total));
					cargo_SN_Consumed_Qty.add(nf.format(total2));					
					double actual_volume = 0;
				  	double l = 0;
				  	double conf_price = 0;
				  	double cd_charge_per_mmbtu = 0;
				  	double vol_available_for_sale = 0;				  	
					queryString1 = "SELECT A.CARGO_REF_CD, A.MAN_CONF_CD, A.MAN_CD, B.TRADER_NAME, " +
								  "TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'), " +
								  "A.PRICE, A.CONFIRM_VOL, C.UNIT_ABR, D.CONFIRM_PRICE " +
								  "FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_TRADER_MST B, " +
								  "FMS7_UNIT_MST C, FMS7_MAN_CONFIRM_MST D, FMS7_MAN_REQ_MST E WHERE " +
								  "E.MAN_CD=A.MAN_CD AND A.CARGO_REF_CD="+temp_cargo_ref_no+" AND "+ 
								  "A.MAN_CONF_CD= D.MAN_CONF_CD AND E.TRD_CD=B.TRADER_CD AND " +
								  "A.UNIT_CD=C.UNIT_CD";
					
					rset1 = stmt1.executeQuery(queryString1);
					//System.out.println(queryString1);
					if(rset1.next())
					{
						delv_to_dt = rset1.getString(6)==null?"":rset1.getString(6);
						cargo_Seller_Nm.add(rset1.getString(4)==null?"":rset1.getString(4));
					  	cargo_Window_From_Dt.add(rset1.getString(5)==null?"":rset1.getString(5));
					  	cargo_Window_To_Dt.add(rset1.getString(6)==null?"":rset1.getString(6));
					  	
					  	conf_price = Double.parseDouble(nf1.format(Double.parseDouble(rset1.getString(7)==null?"0":rset1.getString(7))));					  	
					  	if(conf_price<=0.0001)
					  	{
					  		conf_price = Double.parseDouble(nf1.format(Double.parseDouble(rset1.getString(10)==null?"0":rset1.getString(10))));
					  	}
					  	
					  	cargo_Conf_Price.add(nf1.format(conf_price));
					  	
					  	losses_percentage = 1.5;					  	
					  	
					  	queryString2 = "SELECT A.INTERNAL_CONSUMPTION " +
									  "FROM FMS7_TANK_MASTER_DTL A WHERE " +
									  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
									  "B.TANK_DTL_DT<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";						
						//System.out.println("Tank Master Details Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							String internal_consumption = rset2.getString(1)==null?"":rset2.getString(1);
							if(!internal_consumption.trim().equals(""))
							{
								losses_percentage = Double.parseDouble(internal_consumption);
							}
						}
						
						queryString2 = "SELECT A.PERCENTAGE " +
									  "FROM FMS7_CONSUMPTION_MST A WHERE " +
									  "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
									  "B.EFF_DT<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
						
						//System.out.println("Internal Consumption Percentage Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							String internal_consumption = rset2.getString(1)==null?"":rset2.getString(1);
							if(!internal_consumption.trim().equals(""))
							{
								losses_percentage = Double.parseDouble(internal_consumption);
							}
						}
						
					  	if(rset1.getString(9).trim().equalsIgnoreCase("MMBTU"))
					  	{
					  		queryString2 = "SELECT SUM(A.RECONCIL_QTY) " +
										   "FROM FMS7_CARGO_RECONCIL_DTL A " +
										   "WHERE A.CARGO_REF_NO="+temp_cargo_ref_no+"";
							//System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString2); 
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								reconciled_qty = Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
							}
							else
							{
								reconciled_qty = 0;
							}
							cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
					  		actual_volume = Double.parseDouble(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8)))));
					  		vol_available_for_sale = Double.parseDouble(nf.format((actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100)));
					  		cargo_Confirmed_Qty.add(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8)))));
					  		cargo_Available_For_Sale_Qty.add(nf.format(vol_available_for_sale));
					  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
					  		if((vol_available_for_sale-total)>0)
					  		{
					  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
					  		}
					  	}
					  	else if(rset1.getString(9).trim().equalsIgnoreCase("TBTU"))
					  	{
					  		queryString2 = "SELECT SUM(A.RECONCIL_QTY) " +
										   "FROM FMS7_CARGO_RECONCIL_DTL A " +
										   "WHERE A.CARGO_REF_NO="+temp_cargo_ref_no+"";
							//System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString2); 
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								reconciled_qty = Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
							}
							else
							{
								reconciled_qty = 0;
							}
							cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
					  		l = Double.parseDouble(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8))*1000000)));
					  		actual_volume = Double.parseDouble(nf.format(l));
					  		vol_available_for_sale = Double.parseDouble(nf.format((actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100)));
					  		cargo_Confirmed_Qty.add(nf.format(l));
					  		cargo_Available_For_Sale_Qty.add(nf.format(vol_available_for_sale));
					  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
					  		if((vol_available_for_sale-total)>0)
					  		{
					  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
					  		}
					  	}
					  	else
					  	{
					  		reconciled_qty = 0;
					  		cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
					  		cargo_Confirmed_Qty.add(nf.format(l));
					  		cargo_Available_For_Sale_Qty.add(nf.format(l));
					  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
					  		if((vol_available_for_sale-total)>0)
					  		{
					  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
					  		}
					  	}
					}
					else
					{
						reconciled_qty = 0;
				  		cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
						cargo_Seller_Nm.add("");
					  	cargo_Window_From_Dt.add("");
					  	cargo_Window_To_Dt.add("");
					  	cargo_Conf_Price.add("0.0000");
					  	cargo_Confirmed_Qty.add(nf.format(l));
				  		cargo_Available_For_Sale_Qty.add(nf.format(l));
				  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
					}

					//Following Logic Has Been Introduced By Samik Shah On 13th August, 2010 To Calculate Custom Tax Amount ...
				  	String tax_amt = "";
				  	String tax_str_cd = "0";					
					queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
								  "WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B WHERE " +
								  "B.APP_DATE<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
					//System.out.println("Custom Duty Details Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
					}
					//System.out.println("tax_str_cd = "+tax_str_cd);
					
					queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
								  "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
					//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						//String tax_cd = rset1.getString(1);
						//String tax_factor = nf.format(Double.parseDouble(rset1.getString(2)));										
						if(rset1.getString(3).equals("1"))
						{
							tax_amt = nf1.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset1.getString(2)))/100);
						}
						else if(rset1.getString(3).equals("2"))
						{
							queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
										   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
										   "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
							//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2);
					 		rset2=stmt2.executeQuery(queryString2);
					 		if(rset2.next())
					 		{
						 			if(rset2.getString(3).equals("1"))
									{
										tax_amt = nf1.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset2.getString(2)))/100);
									}
									
						 			tax_amt = nf1.format((Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100);
					 		}
					 		else
					 		{
					 			tax_amt = ""+0.000;
					 		}			 		
						}
						else
						{
							tax_amt = ""+0.000;
						}
						
						cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
					}					
					cargo_Custom_Duty.add(nf1.format(cd_charge_per_mmbtu));
					cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price+cd_charge_per_mmbtu));
					
					//Following Coding Has Been Commented By Samik Shah On 13th August, 2010 ...
					/*queryString1 = "SELECT EXCHG_RATE,TOTAL_CD_AMT,EXP_DELV_QTY " +
								   "FROM FMS7_CUSTOM_DUTY WHERE " +
								   "CARGO_REF_NO="+temp_cargo_ref_no+"";
					System.out.println(queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String exchg_rate = rset1.getString(1)==null?"1":rset1.getString(1);
						String custom_duty_amt = rset1.getString(2)==null?"0":rset1.getString(2);
						String exp_delv_qty = rset1.getString(3)==null?"1":rset1.getString(3);
						
						cd_charge_per_mmbtu = Double.parseDouble(nf1.format((Double.parseDouble(custom_duty_amt)/Double.parseDouble(exp_delv_qty))/Double.parseDouble(exchg_rate)));
						
						cargo_Custom_Duty.add(nf1.format((Double.parseDouble(custom_duty_amt)/Double.parseDouble(exp_delv_qty))/Double.parseDouble(exchg_rate)));
						cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price+cd_charge_per_mmbtu));
					}
					else
					{
						cargo_Custom_Duty.add(nf1.format(Double.parseDouble("0")));
						cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price));
					}*/
					cargo_Type_Flag.add("OTHERS");
				}
			}
			
			//Following Logic Has Been Modified By Samik Shah On 28th April, 2011... (For Year Wise Dummy Cargo Adjustment)
			String dummy_cargo_year = "";
			if(tcq_approval_dt.trim().length()>=10)
			{
				dummy_cargo_year = tcq_approval_dt.trim().substring(6);
			}
			
			/*DataBean_Cargo_Procurement cargo = new DataBean_Cargo_Procurement(); 
			cargo.setCallFlag("FetchAvailableQtyForHLPLOwnAccount");
			cargo.setAlloc_dt(tcq_approval_dt);
			cargo.setStart_dt(tcq_approval_dt);
			cargo.setDcq(var_tcq);
			cargo.init();
			
			Map avail_qty_map = cargo.getAvail_qty_map();
			String available_qty = ""+avail_qty_map.get(tcq_approval_dt) == null?"0":""+avail_qty_map.get(tcq_approval_dt);
			double hlpl_own_volume = Double.parseDouble(available_qty);*/
			double alloc_amt = 0;
			
			queryString = "SELECT NVL(SUM(B.ALLOC_QTY),'0') FROM FMS7_SN_MST A, FMS8_OWN_CARGO_DTL B "
			   		+ "WHERE A.SIGNING_DT IS NULL AND A.FGSA_NO = B.FGSA_NO AND A.SN_NO=B.SN_NO "
			   		+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_REV_NO=B.FGSA_REV_NO AND "
			   		+ "B.CONTRACT_TYPE='S'";
			   rset = stmt.executeQuery(queryString);
			   if(rset.next())
			   {
				   alloc_amt = rset.getDouble(1);
			   }
			   

//			System.out.println("dummy cargo year.."+dummy_cargo_year);
			//if(Double.parseDouble(var_tcq)>0 && Double.parseDouble(var_tcq)>total_balance_qty && tcq_sign.equals("+") && dummy_cargo_year.trim().length()==4)
			if(Double.parseDouble(var_tcq)>0 && tcq_sign.equals("+") && dummy_cargo_year.trim().length()==4)
			{
				double total = 0;
				double total2 = 0;									
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_DUMMY_CARGO_DTL WHERE " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_DUMMY_CARGO_DTL WHERE " +
							   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
							   "CONTRACT_TYPE='S' AND SN_NO="+SN_CD+" AND " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				dummy_cargo_sn_allocated_qty = nf.format(total2);				
				if(total>0)
				{
					dummy_cargo_liability_qty = nf.format(total);
					dummy_cargo_surplus_qty = "";
				}
				else if(total<0)
				{
					dummy_cargo_liability_qty = "";
					dummy_cargo_surplus_qty = nf.format(total);
				}
				else
				{
					dummy_cargo_liability_qty = "";
					dummy_cargo_surplus_qty = "";
				}
				dummy_cargo_flag = "DUMMY";	
				
				///RS 09052017 FOR TCQ MODIFICATION FROM HLPL OWN VOLUME ACCOUNT
				
				total = 0;
				total2 = 0;		
				
				total = alloc_amt;
				/*hlpl_own_volume = hlpl_own_volume - total;
				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS8_OWN_CARGO_DTL WHERE " +
							   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
							   "CONTRACT_TYPE='S' AND SN_NO="+SN_CD+" AND FLAG IN ('T','Y') AND " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				own_cargo_sn_allocated_qty = nf.format(total2);				
				if(hlpl_own_volume<0)
				{
					own_cargo_liability_qty = nf.format(hlpl_own_volume);
					own_cargo_surplus_qty = "";
				}
				else if(hlpl_own_volume>0)
				{
					own_cargo_liability_qty = "";
					own_cargo_surplus_qty = nf.format(hlpl_own_volume);
				}
				else
				{
					own_cargo_liability_qty = "";
					own_cargo_surplus_qty = "";
				}
				own_cargo_flag = "OWN VOLUME ACCOUNT";*/
				System.out.println("own_cargo_liability_qty"+own_cargo_liability_qty);
				System.out.println("own_cargo_liability_qty"+own_cargo_surplus_qty);
			}
			else if(Double.parseDouble(var_tcq)>0 && Double.parseDouble(var_tcq)>total_allocated_qty && tcq_sign.equals("-") && dummy_cargo_year.trim().length()==4)
			{
				double total = 0;
				double total2 = 0;									
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_DUMMY_CARGO_DTL WHERE " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_DUMMY_CARGO_DTL WHERE " +
							   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
							   "CONTRACT_TYPE='S' AND SN_NO="+SN_CD+" AND " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				
				if(total2>0)
				{
					dummy_cargo_sn_allocated_qty = nf.format(total2);					
					if(total>0)
					{
						dummy_cargo_liability_qty = nf.format(total);
						dummy_cargo_surplus_qty = "";
					}
					else if(total<0)
					{
						dummy_cargo_liability_qty = "";
						dummy_cargo_surplus_qty = nf.format(total);
					}
					else
					{
						dummy_cargo_liability_qty = "";
						dummy_cargo_surplus_qty = "";
					}
					dummy_cargo_flag = "DUMMY";
				}
			
			//RS09052017 FOR HLPL OWN VOLUME ACCOUNT
			
			total = 0;
			total2 = 0;	
			
			total = alloc_amt;
			/*hlpl_own_volume = hlpl_own_volume - total;
			
			queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS8_OWN_CARGO_DTL WHERE " +
						   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
						   "CONTRACT_TYPE='S' AND SN_NO="+SN_CD+" AND FLAG IN ('T','Y') AND " +
						   "YEAR="+dummy_cargo_year.trim()+"";
			//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
				total2 = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
			}
			
			if(total2>0)
			{
				own_cargo_sn_allocated_qty = nf.format(total2);					
				if(hlpl_own_volume<0)
				{
					own_cargo_liability_qty = nf.format(hlpl_own_volume);
					own_cargo_surplus_qty = "";
				}
				else if(hlpl_own_volume>0)
				{
					own_cargo_liability_qty = "";
					own_cargo_surplus_qty = nf.format(hlpl_own_volume);
				}
				else
				{
					own_cargo_liability_qty = "";
					own_cargo_surplus_qty = "";
				}
				own_cargo_flag = "OWN VOLUME ACCOUNT";
			} */
		}
			
//			System.out.println("....flag/...."+dummy_cargo_flag);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void Fetch_SN_DCQ_LIST()
	{
		try
		{
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK " +
						  "FROM DLNG_SN_DCQ_DTL " +
						  "WHERE CUSTOMER_CD="+Buyer_cd+" And FLSA_NO="+FGSA_cd+" AND " +
						  "FLSA_REV_NO="+FGSA_REVNo+" AND SN_NO="+SN_CD+" AND SN_REV_NO="+SN_REVNo+"";
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				sn_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
				sn_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
				sn_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
				sn_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	//Following Method Has Been Defined By Samik Shah On 11th January, 2011 ...
	public void Fetch_FGSA_DEACTIVATION_LIST()
	{
		try
		{
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), REMARK " +
						  "FROM DLNG_FLSA_DEACTIVATION_DTL " +
						  "WHERE CUSTOMER_CD="+Buyer_cd+" And FLSA_NO="+FGSA_cd+" AND " +
						  "FLSA_REV_NO="+FGSA_REVNo+" ORDER BY FROM_DT";
			rset = stmt.executeQuery(queryString);		
			while(rset.next())
			{
				fgsa_Deactivation_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
				fgsa_Deactivation_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
				fgsa_Deactivation_Remark.add(rset.getString(3)==null?"":rset.getString(3));				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	//Changes on 10th August by Achal	
	public void Fetch_LOA_DCQ_LIST()
	{
		try
		{
			queryString = "SELECT TO_CHAR(FROM_DT,'DD/MM/YYYY'), TO_CHAR(TO_DT,'DD/MM/YYYY'), DCQ, REMARK FROM FMS7_LOA_DCQ_DTL " +
					"WHERE CUSTOMER_CD="+Buyer_cd+" And TENDER_NO="+FGSA_cd+" AND LOA_NO="+LOA_CD+" AND LOA_REV_NO="+LOA_REVNo+"";
			//System.out.println("Query of Fetch_LOA_DCQ_LIST = "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				loa_Dcq_From_Dt.add(rset.getString(1)==null?"":rset.getString(1));
				loa_Dcq_To_Dt.add(rset.getString(2)==null?"":rset.getString(2));
				loa_Dcq_Value.add(rset.getString(3)==null?"":rset.getString(3));
				loa_Dcq_Remark.add(rset.getString(4)==null?"":rset.getString(4));				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void Fetch_LC_BUYERS_DTLOLD()
	{
		try
		{
			//for regas
			//for sn/LOA
			//queryString = "SELECT DISTINCT(BUYER_CD) FROM FMS7_FGSA_CLAUSE_MST WHERE CLAUSE_CD='5' And FLAG='Y'";
			
			//Introduce BY MIlan Dalsaniya 2011 November 04
			//MD20111104
			if(lc_contract_type.equals("SN/LOA"))
			{
				queryString = "SELECT DISTINCT(B.BUYER_CD) FROM FMS7_SN_CLAUSE_MST B WHERE B.CLAUSE_CD='5' And B.FLAG='T'";
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					//System.out.println("Buyers cd"+rset.getString(1));					
					queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
								   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						//System.out.println("Buyers Name"+rset1.getString(1));
						//System.out.println("CREDIT RATING"+rset1.getString(2));
								
						buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
						buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
						buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
					}
				}
				
				queryString = "SELECT DISTINCT(A.BUYER_CD) FROM FMS7_LOA_CLAUSE_MST A " +
							  "WHERE A.CLAUSE_CD='5' And A.FLAG='T' And A.BUYER_CD NOT IN " +
							  "(SELECT DISTINCT(B.BUYER_CD) FROM FMS7_SN_CLAUSE_MST B " +
							  "WHERE B.CLAUSE_CD='5' And B.FLAG='T')";
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					//System.out.println("Buyers cd"+rset.getString(1));					
					queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
								   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						//System.out.println("Buyers Name"+rset1.getString(1));
						//System.out.println("CREDIT RATING"+rset1.getString(2));
								
						buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
						buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
						buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
					}
				}
			}
			else if(lc_contract_type.equals("REGAS"))
			{
//(RE_GAS_NO, BUYER_CD, CLAUSE_CD,EMP_CD, ENT_DT, FLAG,Rev_no)
				queryString = "SELECT DISTINCT(B.BUYER_CD) FROM FMS7_RE_GAS_CLAUSE_MST B WHERE B.CLAUSE_CD='5' And B.FLAG='Y'";
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					//System.out.println("Buyers cd"+rset.getString(1));					
					queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
								   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						//System.out.println("Buyers Name"+rset1.getString(1));
						//System.out.println("CREDIT RATING"+rset1.getString(2));
								
						buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
						buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
						buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
					}
				}
				
				
			
			}
//			MD20111104 END
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
//	MD20120123 START
	
//	Introduce by Milan Dalsaniya 2011 nov 04 MD20111104
	public void Fetch_LC_REGAS_List() 
		{



			try
			{		
				queryString = "SELECT A.FINANCIAL_YEAR, A.LC_SEQ_NO, A.CUSTOMER_CD, " +
				  " A.CREDIT_RATING, TO_CHAR(A.RATING_EFF_DATE,'DD/MM/YYYY'), TO_CHAR(A.LC_REF_DATE,'DD/MM/YYYY'), " +
				  " TO_CHAR(A.START_DATE,'DD/MM/YYYY'), TO_CHAR(A.END_DATE,'DD/MM/YYYY'), " +
				  " A.MANUAL_EXCHG_FLAG, A.MANUAL_EXCHG_RATE, A.USER_DEFINED_FLAG, " +
				  " A.USER_DEFINED_DCQ, A.CALC_LC_AMOUNT, A.FINAL_LC_AMOUNT, A.REMARKS " +
				  " FROM FMS7_LC_MST A " +
				  " WHERE A.START_DATE <= TO_DATE('"+lc_to_dt+"','DD/MM/YYYY') " +
				  " and A.END_DATE >= TO_DATE('"+lc_from_dt+"','DD/MM/YYYY')" +
				  " AND (A.LC_SEQ_NO || A.FINANCIAL_YEAR || A.CUSTOMER_CD NOT IN " +
				  " (SELECT B.LC_SEQ_NO || B.FINANCIAL_YEAR || B.CUSTOMER_CD FROM FMS7_LC_FINANCE_MST B " +
				  " where B.flag = 'R' or B.flag = 'r')) " +
				  " AND (A.FLAG = 'R' or A.flag = 'r')" +
				  " ORDER BY A.FINANCIAL_YEAR,A.LC_SEQ_NO";
				
				//System.out.println("Query for fetching Master LC List = "+queryString);
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					V_lc_seq_no.add(rset.getString(2));
					V_fin_year.add(rset.getString(1));
					int lc_seq_no = rset.getInt(2);
					String fin_year = rset.getString(1);
					String LC_SEQ_NO = "";
					
					if(lc_seq_no<10)
					{
						LC_SEQ_NO = "000"+lc_seq_no+"/"+fin_year;
					}
					else if(lc_seq_no<100)
					{
						LC_SEQ_NO = "00"+lc_seq_no+"/"+fin_year;
					}
					else if(lc_seq_no<1000)
					{
						LC_SEQ_NO = "0"+lc_seq_no+"/"+fin_year;
					}
					else
					{
						LC_SEQ_NO = ""+lc_seq_no+"/"+fin_year;
					}
					
					vLC_SEQ_NO.add(LC_SEQ_NO);
					vStatus.add("N");
					CUST_CD.add(rset.getString(3)==null?"":rset.getString(3));
					vCREDIT_RATING.add(rset.getString(4)==null?"0":rset.getString(4));
					vCREDIT_RATE_EFF_DT.add(rset.getString(5)==null?"":rset.getString(5));
					vLC_REF_DT.add(rset.getString(6)==null?"":rset.getString(6));
					vLC_START_DT.add(rset.getString(7)==null?"":rset.getString(7));
					vLC_END_DT.add(rset.getString(8)==null?"":rset.getString(8));
					vMANUAL_EXCHG_RATE_FLAG.add(rset.getString(9)==null?"N":rset.getString(9));
					vMANUAL_EXCHG_RATE.add(rset.getString(10)==null?"":rset.getString(10));
					vUSER_DEFINED_FLAG.add(rset.getString(11)==null?"N":rset.getString(11));
					vUSER_DEFINED_DCQ.add(rset.getString(12)==null?"":rset.getString(12));
					vLC_AMT.add(rset.getString(13)==null?"":rset.getString(13));
					vLC_FINAL_AMT.add(rset.getString(14)==null?"":rset.getString(14));
					vLC_REMARKS.add(rset.getString(15)==null?"":rset.getString(15));
					
					String bscode = "";
					String FGSA_No = "";
					String Rev_No = "";
					String customer = "";
					String snNo = "";
					String snRev = "";
					String tcq = "";
					String dcq = "";
					String datediff = "";
					String rate = "";
					String START_DT = "";
					String END_DT = "";
					String tax_type = "";
					String cont_type = "";
					String lc_exchg_rate = "";
					String lc_base_remark = "";
					String flag_lc_value = "";
					String flag_dcq_tcq = "";
					String dcqdays_tcqpercent_value = "";
					
					queryString1 = "SELECT FGSA_NO, SN_NO, CONT_TYPE, FGSA_REV_NO, SN_REV_NO, " +
								   "TO_CHAR(SN_START_DATE,'DD/MM/YYYY'), TO_CHAR(SN_END_DATE,'DD/MM/YYYY'), " +
								   "SN_DURATION, DCQ, TCQ, SALES_RATE, EXCHG_RATE, " +
								   "TAX_PERCENTAGE, LC_METHOD_REMARK, LC_METHOD, LC_BASE, " +
								   "DCQ_DAYS_TCQ_PERCENTAGE " +
								   "FROM FMS7_LC_DTL WHERE FINANCIAL_YEAR='"+rset.getString(1)+"' " +
								   "AND LC_SEQ_NO="+rset.getString(2)+" AND CUSTOMER_CD="+rset.getString(3)+" " +
								   	"AND (CONT_TYPE = 'R' OR CONT_TYPE = 'r')" +
								   "ORDER BY SN_END_DATE";
					
					//System.out.println("Query for fetching Detailed LC Info = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						cont_type += (rset1.getString(3)==null?"S":rset1.getString(3))+"~~";
						tax_type += "VAT"+"~~";
						
						END_DT += (rset1.getString(7)==null?"-":rset1.getString(7))+"~~";
						START_DT += (rset1.getString(6)==null?"-":rset1.getString(6))+"~~";
						rate += ""+0+"~~";
						datediff += (rset1.getString(8)==null?"0":rset1.getString(8))+"~~";
						dcq += (rset1.getString(9)==null?"0":rset1.getString(9))+"~~";
						tcq += (rset1.getString(10)==null?"0":rset1.getString(10))+"~~";
						snRev += (rset1.getString(5)==null?"0":rset1.getString(5))+"~~";
						snNo += (rset1.getString(2)==null?"0":rset1.getString(2))+"~~";
						customer += rset.getString(3)+"~~";
						Rev_No += (rset1.getString(4)==null?"0":rset1.getString(4))+"~~";
						FGSA_No += (rset1.getString(1)==null?"0":rset1.getString(1))+"~~";
						bscode += rset.getString(3)+"~~";
							
						lc_exchg_rate += "-"+"~~";
						flag_lc_value += "-"+"~~";
						flag_dcq_tcq += "-"+"~~";
						dcqdays_tcqpercent_value += "-"+"~~";
						lc_base_remark += "-"+"~~";
					}
					
					/*System.out.println("bscode = "+bscode);
					System.out.println("FGSA_No = "+FGSA_No);
					System.out.println("Rev_No = "+Rev_No);
					System.out.println("snNo = "+snNo);
					System.out.println("snRev = "+snRev);
					System.out.println("dcq = "+dcq);
					System.out.println("tcq = "+tcq);
					System.out.println("START_DT = "+START_DT);
					System.out.println("END_DT = "+END_DT);
					System.out.println("datediff = "+datediff);
					System.out.println("rate = "+rate);
					System.out.println("cont_type = "+cont_type);
					System.out.println("tax_type = "+tax_type);
					System.out.println("lc_exchg_rate = "+lc_exchg_rate);
					System.out.println("flag_lc_value = "+flag_lc_value);
					System.out.println("flag_dcq_tcq = "+flag_dcq_tcq);
					System.out.println("dcqdays_tcqpercent_value = "+dcqdays_tcqpercent_value);
					System.out.println("lc_base_remark = "+lc_base_remark);*/
					
					CUSTOMER_CD.add(bscode);
					vSN_No.add(snNo);
					vSN_rev_No.add(snRev);
					vSN_DCQ.add(dcq);
					vSN_TCQ.add(tcq);	
					FGSA_no.add(FGSA_No);
					vFGSA_REV_NO.add(Rev_No);
					vSN_StartDate.add(START_DT);
					vSN_EndDate.add(END_DT);
					vSN_DATEDIFF.add(datediff);
					vSN_RATE.add(rate);
					vCONT_TYPE.add(cont_type);
					vTAX_TYPE.add(tax_type);
					vSN_LC_EXCHG_RATE.add(lc_exchg_rate);
					vSN_LC_BASE.add(flag_lc_value);
					vSN_LC_DCQ_TCQ_FLAG.add(flag_dcq_tcq);
					vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.add(dcqdays_tcqpercent_value);
					vSN_LC_BASE_REMARK.add(lc_base_remark);
				}
				
				for(int i=0; i<CUST_CD.size(); i++)
				{
					queryString1="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" ";
					//System.out.println("queryString----------"+queryString1);
					rset1= stmt1.executeQuery(queryString1);
					if(rset1.next())	
					{
						CUST_NM.add(rset1.getString(1)==null?"":rset1.getString(1));
					}
					else
					{
						CUST_NM.add("");
					}
				}
				
				queryString1="SELECT BANK_CD,BANK_NAME, CREDIT_RATING FROM FMS7_BANK_MST";
				//System.out.println("queryString----------"+queryString1);
				rset1= stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					V_bank_cd.add(rset1.getString(1)==null?"":rset1.getString(1));
					V_bank_nm.add(rset1.getString(2)==null?"":rset1.getString(2));
					V_BANK_RATING.add(rset1.getString(3)==null?"":rset1.getString(3));
				}
			}
			catch(Exception e)
			{
				System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_SEQ_NO_List() Method:\n"+e.getMessage());
				e.printStackTrace();
			}
		
		
	}

//	================================================


//	Introduce by Milan Dalsaniya MD20111104
		public void View_LC_REGAS_List() 
		{




			try
			{		
				queryString = "SELECT FINANCIAL_YEAR,LC_SEQ_NO,CUSTOMER_CD,BANK_CD,CUSTOMER_NM," +
						      "BANK_NM,BANK_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY')," +
						      "TO_CHAR(VALIDITY_START_DATE,'DD/MM/YYYY'),TO_CHAR(VALIDITY_END_DATE,'DD/MM/YYYY')," +
						      "MRKT_LC_AMOUNT,BANK_LC_AMOUNT,TO_CHAR(LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
						      "To_CHAR(AMENDMENT_DATE,'DD/MM/YYYY'),AMENDMENT_FLAG,REMARKS " +
						      "FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+bank_lc_no+"' AND AMENDMENT_NO='"+amendment_no+"' ";			
				//System.out.println("Query for fetching FMS7_LC_FINANCE_MST = "+queryString);
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					lc_fin_year = rset.getString(1)==null?"":rset.getString(1);
					lc_seq_no = rset.getString(2)==null?"":rset.getString(2);
					//customer_cd  = rset.getString(3)==null?"":rset.getString(3);
					bank_cd = rset.getString(4)==null?"":rset.getString(4);
					customer_nm = rset.getString(5)==null?"":rset.getString(5);
					bank_name = rset.getString(6)==null?"":rset.getString(6);
					bank_rating = rset.getString(7)==null?"":rset.getString(7);
					rating_eff_date = rset.getString(8)==null?"":rset.getString(8);
					validity_st_dt = rset.getString(9)==null?"":rset.getString(9);
					validity_end_dt = rset.getString(10)==null?"":rset.getString(10);
					mrkt_lc_amt = rset.getString(11)==null?"":rset.getString(11);
					bank_lc_amt = rset.getString(12)==null?"":rset.getString(12);
					ship_dt = rset.getString(13)==null?"":rset.getString(13);
					amendment_dt = rset.getString(14)==null?"":rset.getString(14);
					amendment_flag = rset.getString(15)==null?"":rset.getString(15);
					remarks = rset.getString(16)==null?"":rset.getString(16);
					//V_bank_cd.add(rset.getString(1)==null?"":rset.getString(1));
					//V_bank_nm.add(rset.getString(2)==null?"":rset.getString(2));
				}
			}
			catch(Exception e)
			{
				System.out.println("EXCEPTION:DataBean_Contract_Master --> View_LC_List() Method:\n"+e.getMessage());
				e.printStackTrace();
			}
		
		
	}

//	INTRODUCE BY MILAN DALSANIYA 2011 NOV 04 MD20111104

//	============================================


	public void Fetch_LC_FINANCE_MST_REGAS() 
		{



			try
			{
				String bnk_nm = "";
				
				queryString="SELECT A.FINANCIAL_YEAR, A.LC_SEQ_NO, A.CUSTOMER_CD, A.BANK_CD, A.CUSTOMER_NM, A.BANK_NM," +
						      "A.BANK_RATING, TO_CHAR(A.RATING_EFF_DATE,'DD/MM/YYYY'), " +
						      "TO_CHAR(A.VALIDITY_START_DATE, 'DD/MM/YYYY'), TO_CHAR(A.VALIDITY_END_DATE,'DD/MM/YYYY')," +
						      "A.MRKT_LC_AMOUNT, A.BANK_LC_AMOUNT, TO_CHAR(A.LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
						      "TO_CHAR(A.AMENDMENT_DATE,'DD/MM/YYYY'), A.BANK_LC_NO, A.AMENDMENT_NO, B.USER_DEFINED_DCQ, A.REMARKS " +
						      " FROM FMS7_LC_FINANCE_MST A, FMS7_LC_MST B" +
						      " WHERE (A.FLAG = 'R' OR A.FLAG = 'r') " +
						      " AND A.LC_SEQ_NO = B.LC_SEQ_NO AND A.FINANCIAL_YEAR = B.FINANCIAL_YEAR" +
						      " and A.VALIDITY_START_DATE <= to_date('"+lc_to_dt+"', 'DD/MM/YYYY')" +
						      " and A.VALIDITY_END_DATE >= to_date('"+lc_from_dt+"', 'DD/MM/YYYY')" +
						      " ORDER BY A.FINANCIAL_YEAR DESC, A.LC_SEQ_NO,A.AMENDMENT_NO";
				//System.out.println("Query for fetching FMS7_LC_FINANCE_MST = "+queryString);
				rset1 = stmt1.executeQuery(queryString);
				while(rset1.next())
				{
					V_FINANCIAL_YEAR.add(rset1.getString(1)==null?"":rset1.getString(1));
					V_LC_SEQ_NO.add(rset1.getString(2)==null?"":rset1.getString(2));
					V_CUSTOMER_CD.add(rset1.getString(3)==null?"":rset1.getString(3));
					V_BANK_CD.add(rset1.getString(4)==null?"":rset1.getString(4));
					V_CUSTOMER_NM.add(rset1.getString(5)==null?"":rset1.getString(5));
					
					bnk_nm = rset1.getString(6)==null?"":rset1.getString(6);
					V_BANK_RATING.add(rset1.getString(7)==null?"":rset1.getString(7));
					V_RATING_EFF_DATE.add(rset1.getString(8)==null?"":rset1.getString(8));
					V_VALIDITY_START_DATE.add(rset1.getString(9)==null?"":rset1.getString(9));
					V_VALIDITY_END_DATE.add(rset1.getString(10)==null?"":rset1.getString(10));
					V_MRKT_LC_AMOUNT.add(rset1.getString(11)==null?"":rset1.getString(11));
					V_BANK_LC_AMOUNT.add(rset1.getString(12)==null?"":rset1.getString(12));
					V_LAST_SHIPMENT_DATE.add(rset1.getString(13)==null?"":rset1.getString(13));
					V_AMENDMENT_DATE.add(rset1.getString(14)==null?"":rset1.getString(14));
					V_BANK_LC_NO.add(rset1.getString(15)==null?"":rset1.getString(15));	
					V_AMENDMENT_NO.add(rset1.getString(16)==null?"":rset1.getString(16));
					V_LC_CONTRACT_CAPACITY.add(rset1.getString(17)==null?"0":rset1.getString(17));
					V_REMARKS.add(rset1.getString(18)==null?"":rset1.getString(18));
					
					
					
					if (bnk_nm.trim().startsWith("Other", 0) || bnk_nm.trim().startsWith("other", 0) || bnk_nm.trim().startsWith("OTHER", 0))
					{
						String arr[] = bnk_nm.trim().split("#");
						for(int k =0; k<arr.length; k++)
						{
							//System.out.println("OTHE "+arr[k]);
						}
						String bk_nm = "";
						if(arr.length >= 2)
						{
							bk_nm = arr[1].trim();
						}
						else{
							bk_nm = arr[0].trim();
						}
						V_OTHER_BANK_NM.add(bk_nm);
						V_BANK_NM.add("");
					} 
					else {
						
						V_OTHER_BANK_NM.add("");
						V_BANK_NM.add(bnk_nm);
					}
					
					
					//V_OTHER_BANK_NM
				}
				//System.out.println(V_BANK_NM);
			}
			catch(Exception e)
			{
				System.out.println("EXCEPTION:DataBean_Contract_Master --> Fetch_LC_FINANCE_MST() Method:\n"+e.getMessage());
				e.printStackTrace();
			}
		
		

	}
//	===========================================
	//MD20120123 END
	
	
//	MD20120123 START
//	M & C REGAS LC ==================================================
	public void Fetch_LC_BUYERS_DTL_WITH_ACCESS() 
	{
		try
		{
			if(lc_contract_type.equals("SN/LOA"))
			{
				queryString = "SELECT DISTINCT(A.BUYER_CD) FROM FMS7_SN_CLAUSE_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B "
						+ "WHERE A.CLAUSE_CD='5' And A.FLAG='T' AND A.BUYER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ";
//				System.out.println("...."+queryString);
				rset = stmt.executeQuery(queryString);			
				
				while(rset.next())
				{
					//System.out.println("Buyers cd"+rset.getString(1));					
					queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
								   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						//System.out.println("Buyers Name"+rset1.getString(1));
						//System.out.println("CREDIT RATING"+rset1.getString(2));
								
						buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
						buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
						buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
					}
				}
				
				queryString = "SELECT DISTINCT(A.BUYER_CD) FROM FMS7_LOA_CLAUSE_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
							  "WHERE A.CLAUSE_CD='5' And A.FLAG='T' And A.BUYER_CD NOT IN " +
							  "(SELECT DISTINCT(B.BUYER_CD) FROM FMS7_SN_CLAUSE_MST B, SEC_EMP_CUSTOMER_ALLOC_MST C " +
							  "WHERE B.CLAUSE_CD='5' And B.FLAG='T' AND B.BUYER_CD=C.CUSTOMER_CD AND C.EMP_CD='"+Emp_cd+"' ) "
							  + "AND A.BUYER_CD=B.CUSTOMER_CD AND "
							  + "B.EMP_CD='"+Emp_cd+"' ";
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					//System.out.println("Buyers cd"+rset.getString(1));					
					queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
								   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						//System.out.println("Buyers Name"+rset1.getString(1));
						//System.out.println("CREDIT RATING"+rset1.getString(2));
								
						buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
						buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
						buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
					}
				}
			}
			else if(lc_contract_type.equals("REGAS"))
			{
//(RE_GAS_NO, BUYER_CD, CLAUSE_CD,EMP_CD, ENT_DT, FLAG,Rev_no)
				queryString = "SELECT DISTINCT(B.BUYER_CD) FROM FMS7_RE_GAS_CLAUSE_MST B, SEC_EMP_CUSTOMER_ALLOC_MST C  "
						+ "WHERE B.CLAUSE_CD='5' And B.FLAG='Y' AND B.BUYER_CD=C.CUSTOMER_CD AND C.EMP_CD='"+Emp_cd+"' ";
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					//System.out.println("Buyers cd"+rset.getString(1));					
					queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
								   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						//System.out.println("Buyers Name"+rset1.getString(1));
						//System.out.println("CREDIT RATING"+rset1.getString(2));
								
						buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
						buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
						buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}

	public void Fetch_LC_BUYERS_DTL() 
		{
			try
			{
				if(lc_contract_type.equals("SN/LOA"))
				{
					queryString = "SELECT DISTINCT(B.BUYER_CD) FROM FMS7_SN_CLAUSE_MST B "
							+ "WHERE B.CLAUSE_CD='5' And B.FLAG='T'";
					rset = stmt.executeQuery(queryString);			
					while(rset.next())
					{
						//System.out.println("Buyers cd"+rset.getString(1));					
						queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
									   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							//System.out.println("Buyers Name"+rset1.getString(1));
							//System.out.println("CREDIT RATING"+rset1.getString(2));
									
							buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
							buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
							buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
						}
					}
					
					queryString = "SELECT DISTINCT(A.BUYER_CD) FROM FMS7_LOA_CLAUSE_MST A " +
								  "WHERE A.CLAUSE_CD='5' And A.FLAG='T' And A.BUYER_CD NOT IN " +
								  "(SELECT DISTINCT(B.BUYER_CD) FROM FMS7_SN_CLAUSE_MST B " +
								  "WHERE B.CLAUSE_CD='5' And B.FLAG='T')";
					rset = stmt.executeQuery(queryString);			
					while(rset.next())
					{
						//System.out.println("Buyers cd"+rset.getString(1));					
						queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
									   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							//System.out.println("Buyers Name"+rset1.getString(1));
							//System.out.println("CREDIT RATING"+rset1.getString(2));
									
							buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
							buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
							buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
						}
					}
				}
				else if(lc_contract_type.equals("REGAS"))
				{
//	(RE_GAS_NO, BUYER_CD, CLAUSE_CD,EMP_CD, ENT_DT, FLAG,Rev_no)
					queryString = "SELECT DISTINCT(B.BUYER_CD) FROM FMS7_RE_GAS_CLAUSE_MST B WHERE B.CLAUSE_CD='5' And B.FLAG='Y'";
					rset = stmt.executeQuery(queryString);			
					while(rset.next())
					{
						//System.out.println("Buyers cd"+rset.getString(1));					
						queryString1 = "SELECT CUSTOMER_NAME,CREDIT_RATING FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset.getString(1)+"' and " +
									   "eff_dt=(select max(eff_dt) from fms7_customer_mst where customer_cd='"+rset.getString(1)+"') order by CUSTOMER_CD";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							//System.out.println("Buyers Name"+rset1.getString(1));
							//System.out.println("CREDIT RATING"+rset1.getString(2));
									
							buyer_cd.add(rset.getString(1)==null?"0":rset.getString(1));
							buyer_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
							buyer_Credit_Rate.add(rset1.getString(2)==null?"":rset1.getString(2));
						}
					}
					
					
				
				}
//				MD20111104 END
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}


//	==============================================================

	public void fetch_LC_RE_GAS_List()  
		{

				
			try 
			{		
				
					//System.out.println("From fetch_LC_RE_GAS_List >>>>>>>>>>");
					queryString = "SELECT A.CUSTOMER_CD,A.RE_GAS_NO,A.REV_NO," +
								"to_char(A.START_DT,'dd/mm/yyyy'), " +
								"to_char(A.END_DT,'dd/mm/yyyy')," +
								"A.CAPACITY,A.STATUS,to_char(A.REV_DT,'dd/mm/yyyy'),A.NO_OF_CARGO " +
								" FROM FMS7_RE_GAS_MST A where A.Customer_cd='"+Buyer_cd+"' AND " +
								" A.END_DT >= to_date('"+lc_gen_dt+"','dd/mm/yyyy') " +
								"and A.REV_NO IN (SELECT MAX(B.REV_NO) FROM FMS7_RE_GAS_MST B WHERE  B.RE_GAS_NO = A.RE_GAS_NO AND B.Customer_cd = A.Customer_cd )" +
								" order by A.RE_GAS_NO asc";
					//System.out.println("LC_RE_GAS_LIST Fetch DATA Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					while (rset.next())
					{
						LC_REGAS_NO.add(rset.getString(2)==null?"":rset.getString(2));
						LC_REGAS_REV_NO.add(rset.getString(3)==null?"":rset.getString(3));
						LC_REGAS_STR_DT.add(rset.getString(4)==null?"":rset.getString(4));
						LC_REGAS_END_DT.add(rset.getString(5)==null?"":rset.getString(5));					
						LC_REGAS_CAPACITY.add(rset.getString(6)==null?"":rset.getString(6));
					}
					//System.out.println();
					//System.out.println(LC_REGAS_NO);
					//System.out.println(LC_REGAS_STR_DT);
					//System.out.println(LC_REGAS_END_DT);
					//System.out.println(LC_REGAS_CAPACITY);
					//System.out.println();
					
					for(int i=0; i<LC_REGAS_NO.size(); i++)
					{
						
						Vector LC_CONTRACT_CAP_REGAS_tmp = new Vector();
						Vector LC_SEQ_NO_REGAS_tmp = new Vector();
						Vector LC_FIN_YEAR_REGAS_tmp = new Vector();
						Vector LC_FLG_REGAS_tmp = new Vector();
						
						//Vector LC_SEQ_NO_REGAS_tmp2 = new Vector();
						//Vector LC_FIN_YEAR_REGAS_tmp2 = new Vector();
						
						Vector LC_STR_DT_REGAS_tmp = new Vector();
						Vector LC_END_DT_REGAS_tmp = new Vector();
						Vector LC_FINAL_AMOUNT_REGAS_tmp = new Vector();
						Vector LC_REMARKS_REGAS_tmp = new Vector();
						
						queryString = "SELECT A.CUSTOMER_CD,A.FINANCIAL_YEAR,A.LC_SEQ_NO,B.USER_DEFINED_DCQ,A.FLAG, " +
								" TO_CHAR(B.START_DATE,'dd/mm/yyyy'), TO_CHAR(B.END_DATE,'dd/mm/yyyy'), " +
								" B.FINAL_LC_AMOUNT,B.REMARKS	" +
								" FROM FMS7_LC_DTL A, FMS7_LC_MST B " +
								" WHERE A.Customer_cd='"+Buyer_cd+"' AND  A.SN_NO = '"+LC_REGAS_NO.elementAt(i)+"' " +
								" AND A.SN_REV_NO = '"+LC_REGAS_REV_NO.elementAt(i)+"' " +
							//	" AND A.SN_END_DATE = TO_DATE('"+LC_REGAS_END_DT.elementAt(i)+"','dd/mm/yyyy') " +
							//	" AND  A.SN_START_DATE = TO_DATE('"+LC_REGAS_STR_DT.elementAt(i)+"','dd/mm/yyyy') " +
								" AND  (A.FLAG = 'R' OR A.FLAG = 'r')	AND A.Customer_cd = B.Customer_cd " +
								" AND A.LC_SEQ_NO = B.LC_SEQ_NO AND A.FINANCIAL_YEAR = B.FINANCIAL_YEAR AND	A.FLAG = B.FLAG";
						
						
						//System.out.println("LC_SEQ_NO Fetch DATA Query = "+queryString);
						rset = stmt.executeQuery(queryString);
						int chk =0;
						String lc_seq_no = "";
						String fin_yr = "";
						while(rset.next())
						{
							LC_CONTRACT_CAP_REGAS_tmp.add(rset.getString(4)==null?"-":rset.getString(4));
							LC_SEQ_NO_REGAS_tmp.add(rset.getString(3)==null?"-":rset.getString(3));
							//lc_seq_no = rset.getString(3)==null?"0":rset.getString(3);
							LC_FIN_YEAR_REGAS_tmp.add(rset.getString(2)==null?"-":rset.getString(2));
							//fin_yr = rset.getString(2)==null?"0":rset.getString(2);
							//LC_SEQ_NO_REGAS_tmp2.add(rset.getString(3)==null?"0":rset.getString(3));
							//LC_FIN_YEAR_REGAS_tmp2.add(rset.getString(2)==null?"0":rset.getString(2));
							
							LC_FLG_REGAS_tmp.add(rset.getString(5)==null?"-":rset.getString(5));
							
							LC_STR_DT_REGAS_tmp.add(rset.getString(6)==null?"":rset.getString(6)); 
							LC_END_DT_REGAS_tmp.add(rset.getString(7)==null?"":rset.getString(7));
							LC_FINAL_AMOUNT_REGAS_tmp.add(rset.getString(8)==null?"":rset.getString(8));
							LC_REMARKS_REGAS_tmp.add(rset.getString(9)==null?"":rset.getString(9));
							
							//System.out.println("TCQ >>>>> capacity >>>>> "+rset.getString(4)==null?"":rset.getString(4));
							
							//add below code here
							//
							chk++;
						}
						if(chk == 0)
						{
							LC_SEQ_NO_REGAS_tmp.add("-");
							LC_FIN_YEAR_REGAS_tmp.add("-");
							LC_FLG_REGAS_tmp.add("-");
							
							//LC_FIN_YEAR_REGAS_tmp2.add("0");
							//LC_SEQ_NO_REGAS_tmp2.add("0");
							
							
							
							LC_CONTRACT_CAP_REGAS_tmp.add("-");
							
							LC_STR_DT_REGAS_tmp.add("-"); 
							LC_END_DT_REGAS_tmp.add("-");
							LC_FINAL_AMOUNT_REGAS_tmp.add("-");
							LC_REMARKS_REGAS_tmp.add("-");
						}
						
						LC_SEQ_NO_REGAS.add(LC_SEQ_NO_REGAS_tmp);
						LC_FIN_YEAR_REGAS.add(LC_FIN_YEAR_REGAS_tmp);
						
						//LC_SEQ_NO_REGAS.add(LC_SEQ_NO_REGAS_tmp);
						//LC_FIN_YEAR_REGAS.add(LC_FIN_YEAR_REGAS_tmp);
						
						//LC_SEQ_NO_REGAS_tmp1.add(LC_SEQ_NO_REGAS_tmp2);
					///	LC_FIN_YEAR_REGAS_tmp1.add(LC_FIN_YEAR_REGAS_tmp2);
						
						
						LC_FLG_REGAS.add(LC_FLG_REGAS_tmp);
						LC_CONTRACT_CAP_REGAS.add(LC_CONTRACT_CAP_REGAS_tmp);
						
						LC_STR_DT_REGAS.add(LC_STR_DT_REGAS_tmp); 
						LC_END_DT_REGAS.add(LC_END_DT_REGAS_tmp);
						LC_FINAL_AMOUNT_REGAS.add(LC_FINAL_AMOUNT_REGAS_tmp);
						LC_REMARKS_REGAS.add(LC_REMARKS_REGAS_tmp); 
						
						/*System.out.println(LC_SEQ_NO_REGAS);
						System.out.println(LC_FIN_YEAR_REGAS);
						System.out.println(LC_FLG_REGAS);
						System.out.println(LC_CONTRACT_CAP_REGAS);
						
						System.out.println(LC_STR_DT_REGAS);
						System.out.println(LC_END_DT_REGAS);
						System.out.println(LC_FINAL_AMOUNT_REGAS);
						System.out.println(LC_REMARKS_REGAS);*/
						
					}
					
					/*for(int i=0; i<LC_SEQ_NO_REGAS_tmp1.size(); i++)
					{
						
						for(int j=0; j<((Vector)LC_SEQ_NO_REGAS_tmp1.elementAt(i)).size(); j++)
						{
							if(!LC_SEQ_NO_REGAS_tmp1.elementAt(i).toString().equals("-"))
							{
								
								queryString = "SELECT to_char(A.START_DATE,'dd/mm/yyyy'), " +
											"to_char(A.END_DATE,'dd/mm/yyyy')," +
											"A.FINAL_LC_AMOUNT,A.REMARKS" +
											" FROM FMS7_LC_MST A where A.Customer_cd='"+Buyer_cd+"' AND " +
											" A.LC_SEQ_NO = '"+((Vector)LC_SEQ_NO_REGAS_tmp1.elementAt(i)).elementAt(j)+"' AND " +
											" A.FINANCIAL_YEAR = '"+((Vector)LC_FIN_YEAR_REGAS_tmp1.elementAt(i)).elementAt(j)+"' AND " +
											" (A.FLAG = 'R' OR A.FLAG = 'r')";
								
								System.out.println("LC_SEQ_NO Fetch DATA Query 2 = "+queryString);
								rset = stmt.executeQuery(queryString);
								int chk =0;
								while(rset.next())
								{
									LC_STR_DT_REGAS_tmp.add(rset.getString(1)==null?"":rset.getString(1)); 
									LC_END_DT_REGAS_tmp.add(rset.getString(2)==null?"":rset.getString(2));
									LC_FINAL_AMOUNT_REGAS_tmp.add(rset.getString(3)==null?"":rset.getString(3));
									LC_REMARKS_REGAS_tmp.add(rset.getString(4)==null?"":rset.getString(4));
									chk++;
								}
								if(chk == 0)
								{
									LC_STR_DT_REGAS_tmp.add("-"); 
									LC_END_DT_REGAS_tmp.add("-");
									LC_FINAL_AMOUNT_REGAS_tmp.add("-");
									LC_REMARKS_REGAS_tmp.add("-");
								}
							}
							else
							{
								LC_STR_DT_REGAS_tmp.add("-"); 
								LC_END_DT_REGAS_tmp.add("-");
								LC_FINAL_AMOUNT_REGAS_tmp.add("-");
								LC_REMARKS_REGAS_tmp.add("-");
							}
						}
							
						LC_STR_DT_REGAS.add(LC_STR_DT_REGAS_tmp); 
						LC_END_DT_REGAS.add(LC_END_DT_REGAS_tmp);
						LC_FINAL_AMOUNT_REGAS.add(LC_FINAL_AMOUNT_REGAS_tmp);
						LC_REMARKS_REGAS.add(LC_REMARKS_REGAS_tmp); 
					}*/
					/**/
								
			}
			catch (Exception e)
			{
				System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_RE_GAS_List()--> "+e.getMessage());
				e.printStackTrace();
			}
		
	}

//	=============================================

	public void fetch_LC_SEQ_NO_REGAS_List()
		{



			try
			{		
				String t_LC_REGAS_NO_REGAS2 =  "";
				String t_LC_CONT_TYPE_REGAS2 =  "";
				String t_LC_REGAS_REV_NO_REGAS2 =  "";
				String t_LC_STR_DT_REGAS2 =  "";
				String t_LC_END_DT_REGAS2 =  "";
				String t_LC_REGAS_DURATION_REGAS2 =  "";
				String t_LC_DCQ_REGAS2 =  "";
				String t_LC_TCQ_REGAS2 =  "";
				String t_LC_EXCHG_RATE_REGAS2 =  "";
				String t_LC_CUST_CD_DTL =  "";
				
				queryString = "SELECT FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, " +
							  "CREDIT_RATING, TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'), TO_CHAR(LC_REF_DATE,'DD/MM/YYYY'), " +
							  "TO_CHAR(START_DATE,'DD/MM/YYYY'), TO_CHAR(END_DATE,'DD/MM/YYYY'), " +
							  "MANUAL_EXCHG_RATE, " +
							  "USER_DEFINED_DCQ, CALC_LC_AMOUNT, FINAL_LC_AMOUNT, REMARKS, FLAG  " +
							  "FROM FMS7_LC_MST WHERE (START_DATE BETWEEN " +
							  "TO_DATE('"+lc_from_dt_2+"','DD/MM/YYYY') AND TO_DATE('"+lc_to_dt_2+"','DD/MM/YYYY') or END_DATE BETWEEN " +
							  "TO_DATE('"+lc_from_dt_2+"','DD/MM/YYYY') AND TO_DATE('"+lc_to_dt_2+"','DD/MM/YYYY'))" +
							  "AND CUSTOMER_CD="+Buyer_cd+" AND FLAG = 'R' ORDER BY FINANCIAL_YEAR,LC_SEQ_NO" ;

				//System.out.println("Query for fetching Master Vector LC List = "+queryString);
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					t_LC_REGAS_NO_REGAS2 = "";
					t_LC_CONT_TYPE_REGAS2 = "";
					t_LC_REGAS_REV_NO_REGAS2 = "";
					t_LC_STR_DT_REGAS2 = "";
					t_LC_END_DT_REGAS2 = "";
					t_LC_REGAS_DURATION_REGAS2 = "";
					t_LC_DCQ_REGAS2 = "";
					t_LC_TCQ_REGAS2 = "";
					t_LC_EXCHG_RATE_REGAS2 = "";
					
					int lc_seq_no = rset.getInt(2);
					String fin_year = rset.getString(1);
					String LC_SEQ_NO = "";
					
					if(lc_seq_no<10)
					{
						LC_SEQ_NO = "000"+lc_seq_no+"/"+fin_year;
					}
					else if(lc_seq_no<100)
					{
						LC_SEQ_NO = "00"+lc_seq_no+"/"+fin_year;
					}
					else if(lc_seq_no<1000)
					{
						LC_SEQ_NO = "0"+lc_seq_no+"/"+fin_year;
					}
					else
					{
						LC_SEQ_NO = ""+lc_seq_no+"/"+fin_year;
					}
					LC_FIN_YEAR_REGAS2.add(rset.getString(1)==null?"":rset.getString(1));
					LC_SEQ_NO_REGAS2.add(LC_SEQ_NO);
					LC_CUSTOMER_CD_REGAS2.add(rset.getString(3)==null?"0":rset.getString(3));
					LC_CREDIT_RATING_REGAS2.add(rset.getString(4)==null?"0":rset.getString(4));
					LC_CREDIT_RATING_EFF_DT_REGAS2.add(rset.getString(5)==null?"":rset.getString(5));
					LC_LC_REF_DT_REGAS2.add(rset.getString(6)==null?"":rset.getString(6));
					LC_STR_DT_REGAS2.add(rset.getString(7)==null?"":rset.getString(7));
					LC_END_DT_REGAS2.add(rset.getString(8)==null?"":rset.getString(8));
					LC_MANUAL_EXCHG_RATE_REGAS2.add(rset.getString(9)==null?"0":rset.getString(9));
					//OK
					LC_USER_DEFINED_DCQ_REGAS2.add(rset.getString(10)==null?"0":rset.getString(10));
					LC_CALC_AMT_REGAS2.add(rset.getString(11)==null?"0":rset.getString(11));
					LC_FINAL_AMT_REGAS2.add(rset.getString(12)==null?"0":rset.getString(12));
					LC_REMARKS_REGAS2.add(rset.getString(13)==null?"0":rset.getString(13));
					LC_FLAG_REGAS2.add(rset.getString(14)==null?"0":rset.getString(14));
					
					
					queryString1 = "SELECT SN_NO, CONT_TYPE, SN_REV_NO, " +
						   "TO_CHAR(SN_START_DATE,'DD/MM/YYYY'), TO_CHAR(SN_END_DATE,'DD/MM/YYYY'), " +
						   "SN_DURATION, DCQ, TCQ, EXCHG_RATE, CUSTOMER_CD " +
						   "FROM FMS7_LC_DTL WHERE FINANCIAL_YEAR='"+rset.getString(1)+"' " +
						   "AND LC_SEQ_NO="+rset.getString(2)+" AND CUSTOMER_CD="+rset.getString(3)+" " +
						   " AND CONT_TYPE = 'R' AND FLAG = 'R'" +
						   "ORDER BY SN_END_DATE";
					
						//System.out.println("Query for fetching Detailed LC Info = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						while (rset1.next())
						{
							t_LC_REGAS_NO_REGAS2 += rset1.getString(1)==null?"0":rset1.getString(1) + "~~";
							t_LC_CONT_TYPE_REGAS2 += rset1.getString(2)==null?"0":rset1.getString(2) + "~~";
							t_LC_REGAS_REV_NO_REGAS2 += rset1.getString(3)==null?"0":rset1.getString(3) + "~~";
							t_LC_STR_DT_REGAS2 += rset1.getString(4)==null?"":rset1.getString(4) + "~~";
							t_LC_END_DT_REGAS2 += rset1.getString(5)==null?"":rset1.getString(5) + "~~";
							t_LC_REGAS_DURATION_REGAS2 += rset1.getString(6)==null?"0":rset1.getString(6) + "~~";
							t_LC_DCQ_REGAS2 += rset1.getString(7)==null?"0":rset1.getString(7) + "~~";
							t_LC_TCQ_REGAS2 += rset1.getString(8)==null?"0":rset1.getString(8) + "~~";
							t_LC_EXCHG_RATE_REGAS2 += rset1.getString(9)==null?"0":rset1.getString(9) + "~~";
							t_LC_CUST_CD_DTL += rset1.getString(10)==null?"0":rset1.getString(10) + "~~";
							
							
						}
						LC_REGAS_NO_REGAS2.add(t_LC_REGAS_NO_REGAS2);
						LC_CONT_TYPE_REGAS2.add(t_LC_CONT_TYPE_REGAS2);
						LC_REGAS_REV_NO_REGAS2.add(t_LC_REGAS_REV_NO_REGAS2);
						LC_REGAS_END_DT_REGAS2.add(t_LC_END_DT_REGAS2);
						LC_REGAS_STR_DT_REGAS2.add(t_LC_STR_DT_REGAS2);
						LC_REGAS_DURATION_REGAS2.add(t_LC_REGAS_DURATION_REGAS2);
						LC_DCQ_REGAS2.add(t_LC_DCQ_REGAS2);
						LC_TCQ_REGAS2.add(t_LC_TCQ_REGAS2);
						LC_EXCHG_RATE_REGAS2.add(t_LC_EXCHG_RATE_REGAS2); 
						LC_CUST_CD_DTL.add(t_LC_CUST_CD_DTL);
				}		
				
				//System.out.println(LC_SEQ_NO_REGAS2);
				//System.out.println(LC_REGAS_NO_REGAS2);
				//System.out.println(LC_REGAS_STR_DT_REGAS2);
				//System.out.println(LC_FINAL_AMT_REGAS2);
				
			}
			catch(Exception e)
			{
				System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_SEQ_NO_List() Method:\n"+e.getMessage());
				e.printStackTrace();
			}		
		
		

	}
//	===================================

//	MD20120123 END


	
	public void Calculate_LC_Amt()
	{
		try
		{
			String [] till_date_temp = new String[end_dt_arr.length];
			String [] sn_live_flag = new String[end_dt_arr.length];
			double total_lc_amt = 0;
			
			for(int i=0; i<end_dt_arr.length; i++)
			{
				if(lc_method.equalsIgnoreCase("USER_DEFINED"))
				{
					dcq_arr[i] = user_defined_dcq;
				}
				else
				{
					dcq_arr[i] = original_dcq_arr[i];
				}
				
				if(lc_manual_exchg_rate_flag.equalsIgnoreCase("Y"))
				{
					lc_exchg_rate_arr[i] = lc_manual_exchg_rate;
				}
				else
				{
					lc_exchg_rate_arr[i] = original_lc_exchg_rate_arr[i];
				}
								
				int days_diff = 0;
				queryString = "SELECT TO_DATE('"+lc_to_dt+"','DD/MM/YYYY')-TO_DATE('"+end_dt_arr[i]+"','DD/MM/YYYY') FROM DUAL";
				
				System.out.println("Days Difference Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					days_diff = rset.getInt(1);
				}
				
				if(days_diff>0)
				{
					till_date_temp[i] = end_dt_arr[i];
				}
				else
				{
					till_date_temp[i] = lc_to_dt;
				}
			}
			
			int date_counter = 0;
			int end_date_counter = 0;
						
			for(int i=0; i<till_date_temp.length; i++)
			{
				int days_diff = 0;
				queryString = "SELECT TO_DATE('"+lc_to_dt+"','DD/MM/YYYY')-TO_DATE('"+till_date_temp[i]+"','DD/MM/YYYY') FROM DUAL";
				
				//System.out.println("Days Difference Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					days_diff = rset.getInt(1);
				}
				
				if(days_diff>0)
				{
					++date_counter;
				}
				else if(days_diff==0)
				{
					end_date_counter = 1;
				}
			}
			
			date_counter += end_date_counter;
			String [] till_date = new String[date_counter];
			end_date_counter = 0;
			int index = 0;
			
			for(int i=0; i<till_date_temp.length; i++)
			{
				int days_diff = 0;
				
				queryString = "SELECT TO_DATE('"+lc_to_dt+"','DD/MM/YYYY')-TO_DATE('"+till_date_temp[i]+"','DD/MM/YYYY') FROM DUAL";
				
				//System.out.println("Days Difference Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					days_diff = rset.getInt(1);
				}
				
				if(days_diff>0)
				{
					till_date[index] = till_date_temp[i];
					++index;
				}
				else if(days_diff==0)
				{
					if(end_date_counter==0)
					{
						till_date[index] = till_date_temp[i];
						++index;
					}
					end_date_counter = 1;
				}
			}
			
			for(int i=0; i<till_date.length; i++)
			{
				Vector custCd = new Vector();
				Vector fgsaNo = new Vector();
				Vector fgsaRevNo = new Vector();
				Vector snNo = new Vector();
				Vector snRevNo = new Vector();
				Vector snDuration = new Vector();
				Vector dcq_tcq = new Vector();
				Vector lc_rule = new Vector();
				Vector lc_base = new Vector();
				Vector dcqDays_tcqPercent = new Vector();
				Vector selling_price = new Vector();
				Vector lc_exchg_rt = new Vector();
				
				double lc_amt = 0;
								
				for(int j=0; j<end_dt_arr.length; j++)
				{
					int count = 0;
					
					//System.out.println("end_dt_arr.length = "+end_dt_arr.length);
					
					queryString = "SELECT COUNT(*) FROM DUAL WHERE " +
								  "TO_DATE('"+start_dt_arr[j]+"','DD/MM/YYYY')<=TO_DATE('"+till_date[i]+"','DD/MM/YYYY') AND " +
								  "TO_DATE('"+end_dt_arr[j]+"','DD/MM/YYYY')>=TO_DATE('"+till_date[i]+"','DD/MM/YYYY')";
					
					//System.out.println("Days Difference Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						count = rset.getInt(1);
					}
					
					if(count>0)
					{
						custCd.add(bscode_arr[j]);
						fgsaNo.add(fgsa_no_arr[j]);
						fgsaRevNo.add(fgsa_rev_no_arr[j]);
						snNo.add(sn_no_arr[j]);
						snRevNo.add(sn_rev_no_arr[j]);
						lc_rule.add(flag_lc_value_arr[j]);
						selling_price.add(rate_arr[j]);
						lc_exchg_rt.add(lc_exchg_rate_arr[j]);
						snDuration.add(datediff_arr[j]);
						
						if(flag_lc_value_arr[j].equalsIgnoreCase("OPEN"))
						{
							dcq_tcq.add("0");
							lc_base.add("");
							dcqDays_tcqPercent.add("0");
						}
						else if(flag_dcq_tcq_arr[j].equalsIgnoreCase("DCQ"))
						{
							dcq_tcq.add(dcq_arr[j]);
							lc_base.add("DCQ");
							if(Integer.parseInt(dcqdays_tcqpercent_value_arr[j])<Integer.parseInt(datediff_arr[j]))
							{
								dcqDays_tcqPercent.add(dcqdays_tcqpercent_value_arr[j]);
								if(flag_lc_value_arr[j].equalsIgnoreCase("SN"))
								{
									lc_amt += Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(1+Double.parseDouble(tax_rate_arr[j]));
									//lc_amt += Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*1.15;
									//System.out.println("lc_amt = "+(Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(1+Double.parseDouble(tax_rate_arr[j]))));
								}
								else if(flag_lc_value_arr[j].equalsIgnoreCase("TAX"))
								{
									lc_amt += Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(Double.parseDouble(tax_rate_arr[j]));
									//lc_amt += Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*0.13;
									//System.out.println("lc_amt = "+(Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(Double.parseDouble(tax_rate_arr[j]))));
								}
							}
							else
							{
								dcqDays_tcqPercent.add(datediff_arr[j]);
								if(flag_lc_value_arr[j].equalsIgnoreCase("SN"))
								{
									lc_amt += Double.parseDouble(datediff_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(1+Double.parseDouble(tax_rate_arr[j]));
									//lc_amt += Double.parseDouble(datediff_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*1.15;
									//System.out.println("lc_amt = "+(Double.parseDouble(datediff_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(1+Double.parseDouble(tax_rate_arr[j]))));
								}
								else if(flag_lc_value_arr[j].equalsIgnoreCase("TAX"))
								{
									lc_amt += Double.parseDouble(datediff_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(Double.parseDouble(tax_rate_arr[j]));
									//lc_amt += Double.parseDouble(datediff_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*0.13;
									//System.out.println("lc_amt = "+(Double.parseDouble(datediff_arr[j])*Double.parseDouble(dcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(Double.parseDouble(tax_rate_arr[j]))));
								}
							}							
						}
						else if(flag_dcq_tcq_arr[j].equalsIgnoreCase("TCQ"))
						{
							dcq_tcq.add(tcq_arr[j]);
							lc_base.add("TCQ");
							dcqDays_tcqPercent.add(dcqdays_tcqpercent_value_arr[j]);
							if(flag_lc_value_arr[j].equalsIgnoreCase("SN"))
							{
								lc_amt += (Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(tcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(1+Double.parseDouble(tax_rate_arr[j])))/100;
								//lc_amt += (Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(tcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*1.15)/100;
								//System.out.println("lc_amt = "+((Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(tcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*(1+Double.parseDouble(tax_rate_arr[j])))/100));
							}
							else if(flag_lc_value_arr[j].equalsIgnoreCase("TAX"))
							{
								lc_amt += (Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(tcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*Double.parseDouble(tax_rate_arr[j]))/100;
								//lc_amt += (Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(tcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*0.13)/100;
								//System.out.println("lc_amt = "+((Double.parseDouble(dcqdays_tcqpercent_value_arr[j])*Double.parseDouble(tcq_arr[j])*Double.parseDouble(rate_arr[j])*Double.parseDouble(lc_exchg_rate_arr[j])*Double.parseDouble(tax_rate_arr[j]))/100));
							}
						}
						else
						{
							dcq_tcq.add("0");
							lc_base.add("");
							dcqDays_tcqPercent.add("0");
						}									
					}
				}
				total_lc_amt += lc_amt;
			}
			total_lc_amount = nf.format(total_lc_amt);
			//System.out.println("Total LC Amount = "+total_lc_amount);
			
			for(int i=0; i<end_dt_arr.length; i++)
			{
				lc_exchg_rate_arr[i] = original_lc_exchg_rate_arr[i];
				dcq_arr[i] = original_dcq_arr[i];
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void fetch_Tax_Struct()
	{
		try
		{
			//System.out.println("Check The date"+Start_Dt);		
			queryString = "SELECT DISTINCT(A.PLANT_SEQ_NO),A.PLANT_NAME," +
						  "A.CUSTOMER_CD,A.TAX_STRUCT_CD,A.TAX_STRUCT_DTL " +
						  "FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
						  "A.CUSTOMER_CD="+bscode+" AND " +
						  "A.TAX_STRUCT_DT=(SELECT MAX(B.TAX_STRUCT_DT) " +
						  "FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
						  "B.TAX_STRUCT_DT<=TO_DATE('"+Start_Dt+"','DD/MM/YYYY') AND " +
						  "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.PLANT_SEQ_NO) " +
						  "ORDER BY A.PLANT_SEQ_NO";			
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				//System.out.println("Check The date"+rset.getString(1));
				//System.out.println("Check The Remarks"+rset.getString(2));
				TAX_Plant_Nm.add(rset.getString(2)==null?"":rset.getString(2));
				TAX_Str_CD.add(rset.getString(4)==null?"":rset.getString(4));
				TAX_Remarks.add(rset.getString(5)==null?"":rset.getString(5));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	public void fetch_Customer_Name()
	{
		try
		{
			queryString = "select CUSTOMER_NAME, CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
					"WHERE CUSTOMER_CD='"+bscode+"' ";
			//System.out.println("FGSA: FLAG :"+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				cust_nm =rset.getString(1)==null?"":rset.getString(1).toUpperCase();
				cust_abr =rset.getString(2)==null?"":rset.getString(2).toUpperCase();
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void fetch_Billing_Dtl()
	{
		try
		{
			queryString = "select * from DLNG_FLSA_BILLING_DTL WHERE FLSA_NO='"+FGSA_cd+"' And FLSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
			System.out.println("FGSA: FLAG :"+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				Freq=rset.getString(5)==null?"":rset.getString(5).toUpperCase();
				Due_Date=rset.getString(6)==null?"":rset.getString(6);
				Int_Cal_Cd=rset.getString(7)==null?"":rset.getString(7);
				Int_Cal_Sign=rset.getString(8)==null?"":rset.getString(8);
				Int_Cal_Per=rset.getString(9)==null?"":rset.getString(9);
				Exchng_Rate_Cd=rset.getString(10)==null?"":rset.getString(10);
				Exchng_Rate_Cal=rset.getString(11)==null?"":rset.getString(11);
				Invoice_Cur_Cd=rset.getString(12)==null?"":rset.getString(12);
				Payment_Cur_Cd=rset.getString(13)==null?"":rset.getString(13);
				Tax_Struct_Cd=rset.getString(14)==null?"":rset.getString(14);
				Flag=rset.getString(17)==null?"":rset.getString(17); //SB20111207
				Exchg_Rate_Note=rset.getString(18)==null?"":rset.getString(18);
				inv_criteria=rset.getString(19)==null?"":rset.getString(19); 
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void fetch_Billing_Dtl2()
	{
		try
		{
			queryString = "select BILLING_FREQ, DUE_DT,INT_CAL_RATE_CD from FMS7_FGSA_BILLING_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
			//System.out.println("FGSA: FLAG :"+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				Freq=rset.getString(5)==null?"":rset.getString(5).toUpperCase();
				Due_Date=rset.getString(6)==null?"":rset.getString(6);
				Int_Cal_Cd=rset.getString(7)==null?"":rset.getString(7);
				Int_Cal_Sign=rset.getString(8)==null?"":rset.getString(8);
				Int_Cal_Per=rset.getString(9)==null?"":rset.getString(9);
				Exchng_Rate_Cd=rset.getString(10)==null?"":rset.getString(10);
				Exchng_Rate_Cal=rset.getString(11)==null?"":rset.getString(11);
				Invoice_Cur_Cd=rset.getString(12)==null?"":rset.getString(12);
				Payment_Cur_Cd=rset.getString(13)==null?"":rset.getString(13);
				Tax_Struct_Cd=rset.getString(14)==null?"":rset.getString(14);
				Exchg_Rate_Note=rset.getString(18)==null?"":rset.getString(18);
				inv_criteria=rset.getString(19)==null?"":rset.getString(19); 
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void SN_LIABILITY_CLAUSE_DTL()
	{
		boolean flag=true;
		try
		{
			queryString = "select CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,CONT_TYPE,SN_NO,SN_REV_NO,PRICE_PER,PRICE,PROMISE_QTY_FREQ,LIABILITY_PER,DCQ_FLAG,PNDCQ_FLAG ,MDCQ_FLAG,REMARKS,TO_CHAR(LD_FROM_DT,'dd/mm/yyyy'),TO_CHAR(LD_TO_DT,'dd/mm/yyyy') from FMS7_SN_LD_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'";
			rset = stmt.executeQuery(queryString);		
			while(rset.next())
			{
				price_per1=rset.getString(7)==null?"":rset.getString(7);
				price1=rset.getString(8)==null?"":rset.getString(8);
				promise_qty1=rset.getString(9)==null?"":rset.getString(9);
				lower_per1=rset.getString(10)==null?"":rset.getString(10);
				dcq1=rset.getString(11)==null?"":rset.getString(11);
				pndcq1=rset.getString(12)==null?"":rset.getString(12);
				mdcq1=rset.getString(13)==null?"":rset.getString(13);
				remarks1=rset.getString(14)==null?"":rset.getString(14);
				ld_from_dt=rset.getString(15)==null?"":rset.getString(15);
				ld_to_dt=rset.getString(16)==null?"":rset.getString(16);
				flag=false;
			}
			if(flag)
			{
				queryString = "select * from FMS7_FGSA_LD_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{				
					price_per1=rset.getString(5)==null?"":rset.getString(5);
					price1=rset.getString(6)==null?"":rset.getString(6);
					promise_qty1=rset.getString(7)==null?"":rset.getString(7);
					lower_per1=rset.getString(8)==null?"":rset.getString(8);
					dcq1=rset.getString(9)==null?"":rset.getString(9);
					pndcq1=rset.getString(10)==null?"":rset.getString(10);
					mdcq1=rset.getString(11)==null?"":rset.getString(11);
					remarks1=rset.getString(12)==null?"":rset.getString(12);					
				}				
			}			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
		
		flag=true;	
		try
		{		
			queryString = "select CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,CONT_TYPE,SN_NO,SN_REV_NO,PRICE_PER,PRICE,TOP_PER,ACTUAL_OBLIG_QTY,PROMISE_QTY_FREQ,REMARKS,TO_CHAR(TOP_FROM_DT,'dd/mm/yyyy'),TO_CHAR(TOP_TO_DT,'dd/mm/yyyy') from FMS7_SN_TOP_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'";
			rset = stmt.executeQuery(queryString);		
			while(rset.next())
			{		
				price_per2=rset.getString(7)==null?"":rset.getString(7);
				price2=rset.getString(8)==null?"":rset.getString(8);
				top_per=rset.getString(9)==null?"":rset.getString(9);
				actual_oblig=rset.getString(10)==null?"":rset.getString(10);
				promise_qty2=rset.getString(11)==null?"":rset.getString(11);
				remarks2=rset.getString(12)==null?"":rset.getString(12);
				top_from_dt=rset.getString(13)==null?"":rset.getString(13);
				top_to_dt=rset.getString(14)==null?"":rset.getString(14);
				flag=false;																											
			}
			if(flag)
			{
				queryString = "select * from FMS7_FGSA_TOP_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{			
					price_per2=rset.getString(5)==null?"":rset.getString(5);
					price2=rset.getString(6)==null?"":rset.getString(6);
					top_per=rset.getString(7)==null?"":rset.getString(7);
					actual_oblig=rset.getString(8)==null?"":rset.getString(8);
					promise_qty2=rset.getString(9)==null?"":rset.getString(9);
					remarks2=rset.getString(10)==null?"":rset.getString(10);																																	
				}
			}		
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}		
		flag=true;
		try
		{			
			queryString = "select * from FMS7_SN_MAKEUPGAS_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'";
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{			
				makeup_period_per=rset.getString(7)==null?"":rset.getString(7);
				rec_period_per=rset.getString(8)==null?"":rset.getString(8);
				price_per3=rset.getString(9)==null?"":rset.getString(9);
				price3=rset.getString(10)==null?"":rset.getString(10);
				remarks3=rset.getString(11)==null?"":rset.getString(11);
				flag=false;																								
			}
			if(flag)
			{
				queryString = "select * from FMS7_FGSA_MAKEUPGAS_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{				
					makeup_period_per=rset.getString(5)==null?"":rset.getString(5);
					rec_period_per=rset.getString(6)==null?"":rset.getString(6);
					price_per3=rset.getString(7)==null?"":rset.getString(7);
					price3=rset.getString(8)==null?"":rset.getString(8);
					remarks3=rset.getString(9)==null?"":rset.getString(9);																												
				}
			}			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
		
		if(price_per1!=null && !price_per1.equals("") && !price_per1.equals(" ") && RATE!=null && !RATE.equals("") && !RATE.equals(" "))
		{
			 price1 =nf.format(Double.parseDouble(price_per1)*Double.parseDouble(RATE)/100);			 
		}
		else
		{
			 price1 ="0";		
		}
		if(price_per2!=null && !price_per2.equals("") && !price_per2.equals(" ") && RATE!=null && !RATE.equals("") && !RATE.equals(" "))
		{			 
			price2 =nf.format(Double.parseDouble(price_per2)*Double.parseDouble(RATE)/100);			
		}
		else
		{			
			 price2 ="0";
		}
		if(price_per3!=null && !price_per3.equals("") && !price_per3.equals(" ") && RATE!=null && !RATE.equals("") && !RATE.equals(" "))
		{			 
			price3 =nf.format(Double.parseDouble(price_per3)*Double.parseDouble(RATE)/100);
		}
		else
		{			
			 price3 ="0";
		}
	}
	
	
	//Changed on 3rd August and 4th August
	public void LOA_LIABILITY_CLAUSE_DTL()
	{
		boolean flag=true;			
		try
		{			
			queryString = "select CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,CONT_TYPE,LOA_NO,LOA_REV_NO, PRICE_PER,PRICE,PROMISE_QTY_FREQ,LIABILITY_PER,DCQ_FLAG,PNDCQ_FLAG,MDCQ_FLAG,REMARKS,TO_CHAR(LD_FROM_DT,'dd/mm/yyyy'),TO_CHAR(LD_TO_DT,'dd/mm/yyyy') from FMS7_LOA_LD_DTL WHERE FGSA_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' And LOA_NO='"+SN_CD+"' And LOA_REV_NO='"+SN_REVNo+"'";
			rset = stmt.executeQuery(queryString);		
			while(rset.next())
			{		
				price_per1=rset.getString(7)==null?"":rset.getString(7);
				price1=rset.getString(8)==null?"":rset.getString(8);
				promise_qty1=rset.getString(9)==null?"":rset.getString(9);
				lower_per1=rset.getString(10)==null?"":rset.getString(10);
				dcq1=rset.getString(11)==null?"":rset.getString(11);
				pndcq1=rset.getString(12)==null?"":rset.getString(12);
				mdcq1=rset.getString(13)==null?"":rset.getString(13);
				remarks1=rset.getString(14)==null?"":rset.getString(14);
				ld_from_dt = rset.getString(15)==null?"":rset.getString(15);
				ld_to_dt = rset.getString(16)==null?"":rset.getString(16);
				flag=false;																								
			}
			if(flag)
			{
				queryString = "select CUSTOMER_CD,TENDER_NO,CONT_TYPE,PRICE_PER,PRICE,PROMISE_QTY_FREQ,LIABILITY_PER,DCQ_FLAG,PNDCQ_FLAG,MDCQ_FLAG,REMARKS from FMS7_TENDER_LD_DTL WHERE TENDER_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
				rset = stmt.executeQuery(queryString);			
				while(rset.next())
				{
					price_per1=rset.getString(4)==null?"":rset.getString(4);
					price1=rset.getString(5)==null?"":rset.getString(5);
					promise_qty1=rset.getString(6)==null?"":rset.getString(6);
					lower_per1=rset.getString(7)==null?"":rset.getString(7);
					dcq1=rset.getString(8)==null?"":rset.getString(8);
					pndcq1=rset.getString(9)==null?"":rset.getString(9);
					mdcq1=rset.getString(10)==null?"":rset.getString(10);
					remarks1=rset.getString(11)==null?"":rset.getString(11);																							
				}
			}				
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> LOA_LIABILITY_CLAUSE_DTL() --> "+e.getMessage());
			e.printStackTrace();
		}
		
		
		flag=true;
		try
		{		
			queryString = "select CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,CONT_TYPE,LOA_NO,LOA_REV_NO,PRICE_PER,PRICE,TOP_PER,ACTUAL_OBLIG_QTY,PROMISE_QTY_FREQ,REMARKS,TO_CHAR(TOP_FROM_DT,'dd/mm/yyyy'),TO_CHAR(TOP_TO_DT,'dd/mm/yyyy') from FMS7_LOA_TOP_DTL WHERE FGSA_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' And LOA_NO='"+SN_CD+"' And LOA_REV_NO='"+SN_REVNo+"'";
			rset = stmt.executeQuery(queryString);		
			while(rset.next())
			{		
				price_per2=rset.getString(7)==null?"":rset.getString(7);
				price2=rset.getString(8)==null?"":rset.getString(8);
				top_per=rset.getString(9)==null?"":rset.getString(9);
				actual_oblig=rset.getString(10)==null?"":rset.getString(10);
				promise_qty2=rset.getString(11)==null?"":rset.getString(11);
				remarks2=rset.getString(12)==null?"":rset.getString(12);
				top_from_dt = rset.getString(13)==null?"":rset.getString(13);
				top_to_dt = rset.getString(14)==null?"":rset.getString(14);
				flag=false;																								
			}
			if(flag)
			{
				queryString = "select CUSTOMER_CD,TENDER_NO,CONT_TYPE,PRICE_PER,PRICE,TOP_PER,ACTUAL_OBLIG_QTY,PROMISE_QTY_FREQ,REMARKS from FMS7_TENDER_TOP_DTL WHERE TENDER_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T' ";
				rset = stmt.executeQuery(queryString);		
				while(rset.next())
				{
					price_per2=rset.getString(4)==null?"":rset.getString(4);
					price2=rset.getString(5)==null?"":rset.getString(5);
					top_per=rset.getString(6)==null?"":rset.getString(6);
					actual_oblig=rset.getString(7)==null?"":rset.getString(7);
					promise_qty2=rset.getString(8)==null?"":rset.getString(8);
					remarks2=rset.getString(9)==null?"":rset.getString(9);																												
				}			
			}		
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
		flag=true;		
		try
		{			
			queryString = "select * from FMS7_LOA_MAKEUPGAS_DTL WHERE FGSA_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' And LOA_NO='"+SN_CD+"' And LOA_REV_NO='"+SN_REVNo+"'";
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{		
				makeup_period_per=rset.getString(7)==null?"":rset.getString(7);
				rec_period_per=rset.getString(8)==null?"":rset.getString(8);
				price_per3=rset.getString(9)==null?"":rset.getString(9);
				price3=rset.getString(10)==null?"":rset.getString(10);
				remarks3=rset.getString(11)==null?"":rset.getString(11);				
				flag=false;																									
			}
			if(flag)
			{
				queryString = "select CUSTOMER_CD,TENDER_NO,CONT_TYPE,MAKEUP_PERIOD,RECOVERY_PERIOD,PRICE_PER,PRICE,REMARKS from FMS7_TENDER_MAKEUPGAS_DTL WHERE TENDER_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{				
					makeup_period_per=rset.getString(4)==null?"":rset.getString(4);
					rec_period_per=rset.getString(5)==null?"":rset.getString(5);
					price_per3=rset.getString(6)==null?"":rset.getString(6);
					price3=rset.getString(7)==null?"":rset.getString(7);
					remarks3=rset.getString(8)==null?"":rset.getString(8);						
				}				
			}						
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
		
		if(price_per1!=null && !price_per1.equals("") && !price_per1.equals(" ") && RATE!=null && !RATE.equals("") && !RATE.equals(" "))
		{
			 price1 = nf.format(Double.parseDouble(price_per1)*Double.parseDouble(RATE)/100);			 
		}
		else
		{
			 price1 ="0";		
		}
		if(price_per2!=null && !price_per2.equals("") && !price_per2.equals(" ") && RATE!=null && !RATE.equals("") && !RATE.equals(" "))
		{			 
			price2 =nf.format(Double.parseDouble(price_per2)*Double.parseDouble(RATE)/100);			
		}
		else
		{			
			 price2 ="0";
		}
		if(price_per3!=null && !price_per3.equals("") && !price_per3.equals(" ") && RATE!=null && !RATE.equals("") && !RATE.equals(" "))
		{			 
			price3 =nf.format(Double.parseDouble(price_per3)*Double.parseDouble(RATE)/100);
		}
		else
		{			
			 price3 ="0";
		}
	}
	
	
	public void FGSA_LIABILITY_CLAUSE_DTL()
	{
		try
		{
			queryString = "select * from FMS7_FGSA_LD_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
			rset = stmt.executeQuery(queryString);	
			while(rset.next())
			{
				price_per1=rset.getString(5)==null?"":rset.getString(5);
				price1=rset.getString(6)==null?"":rset.getString(6);
				promise_qty1=rset.getString(7)==null?"":rset.getString(7);
				lower_per1=rset.getString(8)==null?"":rset.getString(8);
				dcq1=rset.getString(9)==null?"":rset.getString(9);
				pndcq1=rset.getString(10)==null?"":rset.getString(10);
				mdcq1=rset.getString(11)==null?"":rset.getString(11);
				remarks1=rset.getString(12)==null?"":rset.getString(12);																												
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
		try
		{	
			queryString = "select * from FMS7_FGSA_TOP_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
			rset = stmt.executeQuery(queryString);	
			while(rset.next())
			{
				price_per2=rset.getString(5)==null?"":rset.getString(5);
				price2=rset.getString(6)==null?"":rset.getString(6);
				top_per=rset.getString(7)==null?"":rset.getString(7);
				actual_oblig=rset.getString(8)==null?"":rset.getString(8);
				promise_qty2=rset.getString(9)==null?"":rset.getString(9);
				remarks2=rset.getString(10)==null?"":rset.getString(10);	
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}	
		try
		{		
			queryString = "select * from FMS7_FGSA_MAKEUPGAS_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
			rset = stmt.executeQuery(queryString);		
			while(rset.next())
			{		
				makeup_period_per=rset.getString(5)==null?"":rset.getString(5);
				rec_period_per=rset.getString(6)==null?"":rset.getString(6);
				price_per3=rset.getString(7)==null?"":rset.getString(7);
				price3=rset.getString(8)==null?"":rset.getString(8);
				remarks3=rset.getString(9)==null?"":rset.getString(9);																											
			}				
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Changed on 3rd August
	public void Tender_LIABILITY_CLAUSE_DTL()
	{
		try
		{
			queryString = "select CUSTOMER_CD,TENDER_NO,CONT_TYPE,PRICE_PER,PRICE,PROMISE_QTY_FREQ,LIABILITY_PER,DCQ_FLAG,PNDCQ_FLAG,MDCQ_FLAG,REMARKS "
					+ "from DLNG_TENDER_LD_DTL WHERE TENDER_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
			//System.out.println("queryString --> "+queryString);
			rset = stmt.executeQuery(queryString);		
			if(rset.next())
			{			
				price_per1=rset.getString(4)==null?"":rset.getString(4);
				price1=rset.getString(5)==null?"0":rset.getString(5);
				promise_qty1=rset.getString(6)==null?"":rset.getString(6);
				lower_per1=rset.getString(7)==null?"":rset.getString(7);
				dcq1=rset.getString(8)==null?"":rset.getString(8);
				pndcq1=rset.getString(9)==null?"":rset.getString(9);
				mdcq1=rset.getString(10)==null?"":rset.getString(10);
				remarks1=rset.getString(11)==null?"":rset.getString(11);				
				//System.out.println("rset.getString(11) --> "+rset.getString(11));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> Tender_LIABILITY_CLAUSE_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	
		try
		{		
			queryString = "select CUSTOMER_CD,TENDER_NO,CONT_TYPE,PRICE_PER,PRICE,TOP_PER,ACTUAL_OBLIG_QTY,PROMISE_QTY_FREQ,REMARKS  "
					+ "from DLNG_TENDER_TOP_DTL WHERE TENDER_NO='"+FGSA_cd+"'  And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
			//System.out.println("queryString --> "+queryString);
			rset = stmt.executeQuery(queryString);		
			if(rset.next())
			{		
				price_per2=rset.getString(4)==null?"":rset.getString(4);
				price2=rset.getString(5)==null?"0":rset.getString(5);
				top_per=rset.getString(6)==null?"":rset.getString(6);
				actual_oblig=rset.getString(7)==null?"":rset.getString(7);
				promise_qty2=rset.getString(8)==null?"":rset.getString(8);
				remarks2=rset.getString(9)==null?"":rset.getString(9);
			}				
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
		
		try
		{			
			queryString = "select CUSTOMER_CD,TENDER_NO,CONT_TYPE,MAKEUP_PERIOD,RECOVERY_PERIOD,PRICE_PER,PRICE,REMARKS "
					+ "from DLNG_TENDER_MAKEUPGAS_DTL WHERE TENDER_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
			//System.out.println("queryString --> "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{			
				makeup_period_per=rset.getString(4)==null?"":rset.getString(4);
				rec_period_per=rset.getString(5)==null?"":rset.getString(5);
				price_per3=rset.getString(6)==null?"":rset.getString(6);
				price3=rset.getString(7)==null?"0":rset.getString(7);
				remarks3=rset.getString(8)==null?"":rset.getString(8);																													
			}						
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Changed on 3rd August and 4th August
	public void fetch_Tender_Billing_Dtl()
	{
		try
		{
			queryString = "select * from DLNG_FLSA_BILLING_DTL WHERE FLSA_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
			//System.out.println("FMS7_FGSA_BILLING_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{			
				Freq=rset.getString(5)==null?"":rset.getString(5);
				Due_Date=rset.getString(6)==null?"":rset.getString(6);
				Int_Cal_Cd=rset.getString(7)==null?"":rset.getString(7);
				Int_Cal_Sign=rset.getString(8)==null?"":rset.getString(8);
				Int_Cal_Per=rset.getString(9)==null?"":rset.getString(9);
				Exchng_Rate_Cd=rset.getString(10)==null?"":rset.getString(10);
				Exchng_Rate_Cal=rset.getString(11)==null?"":rset.getString(11);
				Invoice_Cur_Cd=rset.getString(12)==null?"":rset.getString(12);
				Payment_Cur_Cd=rset.getString(13)==null?"":rset.getString(13);
				Tax_Struct_Cd=rset.getString(14)==null?"":rset.getString(14);
				Flag=rset.getString(17)==null?"":rset.getString(17); //SB20111207
				Exchg_Rate_Note=rset.getString(18)==null?"":rset.getString(18);
				inv_criteria=rset.getString(19)==null?"":rset.getString(19);
			}
			//System.out.println("FMS7_FGSA_BILLING_DTL of Tender Tax_Struct_Cd = "+Tax_Struct_Cd);
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
boolean billFlag = false;
	public void fetch_SN_Billing_Dtl()
	{
		try
		{
			boolean flag=true;
			queryString = "select * from FMS7_SN_BILLING_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='S' And SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'";
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				Freq=rset.getString(7)==null?"":rset.getString(7);
				Due_Date=rset.getString(8)==null?"":rset.getString(8);
				Int_Cal_Cd=rset.getString(9)==null?"":rset.getString(9);
				Int_Cal_Sign=rset.getString(10)==null?"":rset.getString(10);
				Int_Cal_Per=rset.getString(11)==null?"":rset.getString(11);
				Exchng_Rate_Cd=rset.getString(12)==null?"":rset.getString(12);
				Exchng_Rate_Cal=rset.getString(13)==null?"":rset.getString(13);
				Invoice_Cur_Cd=rset.getString(14)==null?"":rset.getString(14);
				Payment_Cur_Cd=rset.getString(15)==null?"":rset.getString(15);
				Tax_Struct_Cd=rset.getString(16)==null?"":rset.getString(16);
				Flag=rset.getString(19)==null?"":rset.getString(19); //SB20111207
				Exchg_Rate_Note=rset.getString(20)==null?"":rset.getString(20);
				inv_criteria=rset.getString(21)==null?"":rset.getString(21);
				flag=false;
				billFlag = true;
			}
			
			if(flag==true)
			{
				queryString = "select * from FMS7_FGSA_BILLING_DTL WHERE FGSA_NO='"+FGSA_cd+"' And FGSA_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					Freq=rset.getString(5)==null?"":rset.getString(5);
					Due_Date=rset.getString(6)==null?"":rset.getString(6);
					Int_Cal_Cd=rset.getString(7)==null?"":rset.getString(7);
					Int_Cal_Sign=rset.getString(8)==null?"":rset.getString(8);
					Int_Cal_Per=rset.getString(9)==null?"":rset.getString(9);
					Exchng_Rate_Cd=rset.getString(10)==null?"":rset.getString(10);
					Exchng_Rate_Cal=rset.getString(11)==null?"":rset.getString(11);
					Invoice_Cur_Cd=rset.getString(12)==null?"":rset.getString(12);
					Payment_Cur_Cd=rset.getString(13)==null?"":rset.getString(13);
					Tax_Struct_Cd=rset.getString(14)==null?"":rset.getString(14);
					Flag=rset.getString(17)==null?"":rset.getString(17); //SB20111207
					Exchg_Rate_Note=rset.getString(18)==null?"":rset.getString(18);	
					inv_criteria=rset.getString(19)==null?"":rset.getString(19);
				}
			}		
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}

	
	//CHANGE on the 4th August by Achal	
	String pageFlag = "";
	public void fetch_LOA_Billing_Dtl()
	{
		try
		{
			boolean flag=true;
			//System.out.println("bscode == "+bscode);
			queryString = "select CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONT_TYPE,BILLING_FREQ,DUE_DATE," +
					"INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD," +
					"PAYMENT_CUR_CD,TAX_STRUCT_CD,EXCHG_RATE_NOTE,INV_CRITERIA, FLAG from FMS7_SN_BILLING_DTL " +
					"WHERE FGSA_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' And " +
					"SN_NO='"+SN_CD+"' And SN_REV_NO='"+SN_REVNo+"'";	
			if(pageFlag.equals("Y")) {
				queryString = "select CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,CONT_TYPE,BILLING_FREQ,DUE_DATE," +
						"INT_CAL_RATE_CD,INT_CAL_SIGN,INT_CAL_PERCENTAGE,EXCHNG_RATE_CD,EXCHNG_RATE_CAL,INVOICE_CUR_CD," +
						"PAYMENT_CUR_CD,TAX_STRUCT_CD,EXCHG_RATE_NOTE,INV_CRITERIA, FLAG from FMS7_SN_BILLING_DTL " +
						"WHERE FGSA_NO='"+tender_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='L' And " +
						"SN_NO='"+LOA_CD+"' And SN_REV_NO='"+LOA_REVNo+"'";
			}
//			System.out.println("FMS7_SN_BILLING_DTL queryStringy = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				Freq=rset.getString(7)==null?"":rset.getString(7);
				Due_Date=rset.getString(8)==null?"":rset.getString(8);
				Int_Cal_Cd=rset.getString(9)==null?"":rset.getString(9);
				Int_Cal_Sign=rset.getString(10)==null?"":rset.getString(10);
				Int_Cal_Per=rset.getString(11)==null?"":rset.getString(11);
				Exchng_Rate_Cd=rset.getString(12)==null?"":rset.getString(12);
				Exchng_Rate_Cal=rset.getString(13)==null?"":rset.getString(13);
				Invoice_Cur_Cd=rset.getString(14)==null?"":rset.getString(14);
				Payment_Cur_Cd=rset.getString(15)==null?"":rset.getString(15);
				Tax_Struct_Cd=rset.getString(16)==null?"":rset.getString(16);
				Exchg_Rate_Note=rset.getString(17)==null?"":rset.getString(17);
				inv_criteria=rset.getString(18)==null?"":rset.getString(18);
				Flag=rset.getString(19)==null?"":rset.getString(19); //SB20111207
				flag=false;			
				billFlag = true;
			}
//			System.out.println("Tax_Struct_Cd = "+billFlag);
			if(flag)
			{
				queryString = "select CUSTOMER_CD,FGSA_NO,FGSA_REV_NO,CONT_TYPE,BILLING_FREQ,DUE_DATE,INT_CAL_RATE_CD," +
						"INT_CAL_SIGN,INT_CAL_PERCENTAGE,EXCHNG_RATE_CD, EXCHNG_RATE_CAL,INVOICE_CUR_CD,PAYMENT_CUR_CD," +
				//SB20111207		"TAX_STRUCT_CD,EXCHG_RATE_NOTE,INV_CRITERIA " +
						"TAX_STRUCT_CD,EXCHG_RATE_NOTE,INV_CRITERIA " +
						"from FMS7_FGSA_BILLING_DTL WHERE FGSA_NO='"+FGSA_cd+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='T'";
				//System.out.println("flag = "+flag);				
				//System.out.println("select from FMS7_FGSA_BILLING_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);							
				if(rset.next())
				{
					Freq=rset.getString(5)==null?"":rset.getString(5);
					Due_Date=rset.getString(6)==null?"":rset.getString(6);
					Int_Cal_Cd=rset.getString(7)==null?"":rset.getString(7);
					Int_Cal_Sign=rset.getString(8)==null?"":rset.getString(8);
					Int_Cal_Per=rset.getString(9)==null?"":rset.getString(9);
					Exchng_Rate_Cd=rset.getString(10)==null?"":rset.getString(10);
					Exchng_Rate_Cal=rset.getString(11)==null?"":rset.getString(11);
					Invoice_Cur_Cd=rset.getString(12)==null?"":rset.getString(12);
					Payment_Cur_Cd=rset.getString(13)==null?"":rset.getString(13);
					Tax_Struct_Cd=rset.getString(14)==null?"":rset.getString(14);
					Exchg_Rate_Note=rset.getString(15)==null?"":rset.getString(15);
					inv_criteria=rset.getString(16)==null?"":rset.getString(16);
					//Flag=rset.getString(17)==null?"":rset.getString(17); //SB20111207
					Flag="";
				}
				//System.out.println("FMS7_FGSA_BILLING_DTL Tax_Struct_Cd = "+Tax_Struct_Cd);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_Int_Cal_List()
	{
		try
		{
			queryString = "select INT_RATE_NM,INT_RATE_CD from FMS7_CONT_INT_RATE_MST order by INT_RATE_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				vInt_cal.add(rset.getString(1)==null?"":rset.getString(1));
				vInt_cd.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Int_Cal_List() --> "+e.getMessage());
			e.printStackTrace();
		}		
	}
		
	
	public void  fetch_Exchng_Rate_List()
	{
		try
		{
			queryString = "select EXC_RATE_NM,EXC_RATE_CD from FMS7_CONT_EXCHG_RATE_MST order by EXC_RATE_CD";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				vExc_rate.add(rset.getString(1)==null?"":rset.getString(1));
				vExc_cd.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Int_Cal_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_SN_clauses_details()
	{
		try
		{
			queryString = "select A.CLAUSE_CD, B.CLAUSE_NM, B.CLAUSE_ABBR FROM FMS7_SN_CLAUSE_MST A, FMS7_CLAUSE_MST B " +
					"WHERE A.CLAUSE_CD=B.CLAUSE_CD AND A.FGSA_NO='"+FGSA_cd+"' AND A.FGSA_REV_NO='"+FGSA_REVNo+"' AND " +
					"A.BUYER_CD='"+bscode+"' AND B.MST_CLAUSE_CD='0' AND A.SN_NO='"+SN_CD+"' AND A.SN_REV_NO='"+SN_REVNo+"'";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				sn_clause_cd.add(rset.getString(1)==null?"":rset.getString(1));
				sn_clause_nm.add(rset.getString(2)==null?"":rset.getString(2));
				sn_clause_abbr.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_clauses_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_LOA_clauses_details()
	{
		try
		{			  
			queryString = "select A.CLAUSE_CD, B.CLAUSE_NM, B.CLAUSE_ABBR FROM FMS7_LOA_CLAUSE_MST A, FMS7_CLAUSE_MST B" +
					" WHERE A.CLAUSE_CD = B.CLAUSE_CD AND  A.TENDER_NO='"+tender_cd+"' AND A.BUYER_CD='"+bscode+"'" +
					" and b.MST_CLAUSE_CD='0' and A.LOA_NO='"+LOA_CD+"' and A.LOA_REV_NO='"+LOA_REVNo+"' ";
			//System.out.println("FMS7_LOA_CLAUSE_MST :"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
			  LOA_clause_cd.add(rset.getString(1)==null?"":rset.getString(1));
			  LOA_clause_nm.add(rset.getString(2)==null?"":rset.getString(2));
			  LOA_clause_abbr.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_clauses_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_FGSA_clauses_details()
	{
		try
		{
			queryString = "select A.CLAUSE_CD, B.CLAUSE_NM, B.CLAUSE_ABBR FROM FMS7_FGSA_CLAUSE_MST A, FMS7_CLAUSE_MST B " +
					"WHERE A.CLAUSE_CD=B.CLAUSE_CD AND A.FGSA_NO='"+FGSA_cd+"' AND " +
					"A.REV_NO='"+FGSA_REVNo+"' AND A.BUYER_CD='"+bscode+"' AND B.MST_CLAUSE_CD='0'";
			//System.out.println("FMS7_FGSA_CLAUSE_MST :"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				FGSA_clause_cd.add(rset.getString(1)==null?"":rset.getString(1));
				FGSA_clause_nm.add(rset.getString(2)==null?"":rset.getString(2));
				FGSA_clause_abbr.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_clauses_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_Tender_clauses_details()
	{
		try
		{			  
			  queryString = "select A.CLAUSE_CD, B.CLAUSE_NM, B.CLAUSE_ABBR FROM FMS7_TENDER_CLAUSE_MST A," +
					"FMS7_CLAUSE_MST B WHERE A.CLAUSE_CD = B.CLAUSE_CD AND  A.TENDER_NO='"+tender_cd+"' " +
					"AND A.CUSTOMER_CD='"+bscode+"' and B.MST_CLAUSE_CD='0'";
			 // System.out.println("FMS7_TENDER_CLAUSE_MST :"+queryString);
			  rset = stmt.executeQuery(queryString);
			  while(rset.next())
			  {
				  Tender_clause_cd.add(rset.getString(1)==null?"":rset.getString(1));
				  Tender_clause_nm.add(rset.getString(2)==null?"":rset.getString(2));
				  Tender_clause_abbr.add(rset.getString(3)==null?"":rset.getString(3));
			  }
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Tender_clauses_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_governing_clauses()
	{
		try
		{
			queryString= "Select TO_CHAR(SIGNING_DT,'DD/MM/YYYY'), TO_CHAR(START_DT,'DD/MM/YYYY'), " +
					"TO_CHAR(END_DT,'DD/MM/YYYY'), TO_CHAR(RENEWAL_DT,'DD/MM/YYYY'), FGSA_BASE, " +
					"FGSA_TYPE, STATUS, BUYER_NOM, BUYER_MONTH_NOM, BUYER_WEEK_NOM, " +
					"BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
					"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
					"MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
					"PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, " +
					"SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY from FMS7_FGSA_MST WHERE " +
					"FGSA_NO='"+FGSA_cd+"' AND REV_NO='"+FGSA_REVNo+"' AND CUSTOMER_CD='"+bscode+"'";			
//			System.out.println("FGSA Governing Clauses Query = "+queryString);			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				 fgSIGNING_DT 		= rset.getString(1)==null?"":rset.getString(1);
				 fgSTART_DT 		= rset.getString(2)==null?"":rset.getString(2);
				 fgEND_DT 			= rset.getString(3)==null?"":rset.getString(3);
				 fgRENEWAL_DT		= rset.getString(4)==null?"":rset.getString(4);
				 fgFGSA_BASE		= rset.getString(5)==null?"":rset.getString(5);
				 fgFGSA_TYPE		= rset.getString(6)==null?"":rset.getString(6);
				 fgSTATUS		    = rset.getString(7)==null?"":rset.getString(7);
				 fgBUYER_NOM		= rset.getString(8)==null?"":rset.getString(8);
				 fgBUYER_MONTH_NOM  = rset.getString(9)==null?"":rset.getString(9);
				 fgBUYER_WEEK_NOM	= rset.getString(10)==null?"":rset.getString(10); 
				 fgBUYER_DAILY_NOM  = rset.getString(11)==null?"":rset.getString(11);
				 fgSELLER_NOM		= rset.getString(12)==null?"":rset.getString(12);
				 fgSELLER_MONTH_NOM = rset.getString(13)==null?"":rset.getString(13);
				 fgSELLER_WEEK_NOM  = rset.getString(14)==null?"":rset.getString(14);
				 fgSELLER_DAILY_NOM = rset.getString(15)==null?"":rset.getString(15);
				 fgDAY_DEF		    = rset.getString(16)==null?"":rset.getString(16);
				 fgDAY_START_TIME	= rset.getString(17)==null?"":rset.getString(17);
				 fgDAY_END_TIME 	= rset.getString(18)==null?"":rset.getString(18);
				 fgMDCQ			    = rset.getString(19)==null?"":rset.getString(19);
				 fgMDCQ_PERCENTAGE  = rset.getString(20)==null?"":rset.getString(20);
				 fgMEASUREMENT	    = rset.getString(21)==null?"":rset.getString(21);
				 fgMEAS_STANDARD	= rset.getString(22)==null?"":rset.getString(22);
				 fgMEAS_TEMPERATURE = rset.getString(23)==null?"":rset.getString(23);
				 fgPRESSURE_MIN_BAR = rset.getString(24)==null?"":rset.getString(24);
				 fgPRESSURE_MAX_BAR = rset.getString(25)==null?"":rset.getString(25);
				 fgOFF_SPEC_GAS			= rset.getString(26)==null?"":rset.getString(26);
				 fgSPEC_GAS_ENERGY_BASE	= rset.getString(27)==null?"":rset.getString(27);
				 fgSPEC_GAS_MIN_ENERGY	= rset.getString(28)==null?"":rset.getString(28);
				 fgSPEC_GAS_MAX_ENERGY	= rset.getString(29)==null?"":rset.getString(29);
				 //PRE_APPROVAL_FGSA = rset.getString(30)==null?"N":rset.getString(30);
			}
		
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_governing_clauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_tender_governing_clauses()
	{
		try
		{
			queryString= "Select TO_CHAR(SIGNING_DT,'DD/MM/YYYY'), TO_CHAR(START_DT,'DD/MM/YYYY') ," +
					" TO_CHAR(END_DT,'DD/MM/YYYY'), TENDER_BASE," +
					" STATUS, BUYER_NOM, BUYER_MONTH_NOM, BUYER_WEEK_NOM," +
					" BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
					" SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ," +
					" MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, " +
					" PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE," +
					" SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY from FMS7_TENDER_MST  WHERE " +
					" TENDER_NO='"+tender_cd+"' AND CUSTOMER_CD='"+bscode+"' ";			
			//System.out.println("tender governing"+queryString);			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				 trSIGNING_DT 		= rset.getString(1)==null?"":rset.getString(1);
				 trSTART_DT 		= rset.getString(2)==null?"":rset.getString(2);
				 trEND_DT 			= rset.getString(3)==null?"":rset.getString(3);
				// trRENEWAL_DT		= rset.getString(4)==null?"":rset.getString(4);
				 trTENDER_BASE		= rset.getString(4)==null?"":rset.getString(4);
				 //fgFGSA_TYPE		= rset.getString(6)==null?"":rset.getString(6);
				 trSTATUS		    = rset.getString(5)==null?"":rset.getString(5);
				 trBUYER_NOM		= rset.getString(6)==null?"":rset.getString(6);
				 trBUYER_MONTH_NOM  = rset.getString(7)==null?"":rset.getString(7);
				 trBUYER_WEEK_NOM	= rset.getString(8)==null?"":rset.getString(8); 
				 trBUYER_DAILY_NOM  = rset.getString(9)==null?"":rset.getString(9);
				 trSELLER_NOM		= rset.getString(10)==null?"":rset.getString(10);
				 trSELLER_MONTH_NOM = rset.getString(11)==null?"":rset.getString(11);
				 trSELLER_WEEK_NOM  = rset.getString(12)==null?"":rset.getString(12);
				 trSELLER_DAILY_NOM = rset.getString(13)==null?"":rset.getString(13);
				 trDAY_DEF		    = rset.getString(14)==null?"":rset.getString(14);
				 trDAY_START_TIME	= rset.getString(15)==null?"":rset.getString(15);
				 trDAY_END_TIME 	= rset.getString(16)==null?"":rset.getString(16);
				 trMDCQ			    = rset.getString(17)==null?"":rset.getString(17);
				 trMDCQ_PERCENTAGE  = rset.getString(18)==null?"":rset.getString(18);
				 trMEASUREMENT	    = rset.getString(19)==null?"":rset.getString(19);
				 trMEAS_STANDARD	= rset.getString(20)==null?"":rset.getString(20);
				 trMEAS_TEMPERATURE = rset.getString(21)==null?"":rset.getString(21);
				 trPRESSURE_MIN_BAR = rset.getString(22)==null?"":rset.getString(22);
				 trPRESSURE_MAX_BAR = rset.getString(23)==null?"":rset.getString(23);
				 trOFF_SPEC_GAS			= rset.getString(24)==null?"":rset.getString(24);
				 trSPEC_GAS_ENERGY_BASE	= rset.getString(25)==null?"":rset.getString(25);
				 trSPEC_GAS_MIN_ENERGY	= rset.getString(26)==null?"":rset.getString(26);
				 trSPEC_GAS_MAX_ENERGY	= rset.getString(27)==null?"":rset.getString(27);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_governing_clauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	public void fetcg_SN_transporter_details()
	{
		try
		{
			queryString= "Select distinct(A.TRANSPORTER_CD) , B.TRANSPORTER_ABBR  from FMS7_SN_TRANSPORTER_MST A , FMS7_TRANSPORTER_MST B WHERE " +
					"A.TRANSPORTER_CD = B.TRANSPORTER_CD AND  A.FGSA_NO='"+FGSA_cd+"' AND A.FGSA_REV_NO = '"+FGSA_REVNo+"' AND A.CUSTOMER_CD='"+bscode+"' and A.SN_NO='"+SN_CD+"' and A.SN_REV_NO='"+SN_REVNo+"'  ";
			//System.out.println("Transporter details"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				sn_transporter_cd.add(rset.getString(1)==null?"":rset.getString(1));
				sn_transporter_abbr.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Transporter_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetch_LOA_transporter_details()
	{
		try
		{
			queryString= "Select distinct(A.TRANSPORTER_CD) , B.TRANSPORTER_ABBR  from DLNG_LOA_TRANSPORTER_MST A , " +
					"FMS7_TRANSPORTER_MST B WHERE A.TRANSPORTER_CD = B.TRANSPORTER_CD AND A.TENDER_NO='"+tender_cd+"'" +
					"AND A.CUSTOMER_CD='"+bscode+"' and A.LOA_NO='"+LOA_CD+"' and A.LOA_REV_NO='"+LOA_REVNo+"' ";
			//System.out.println("Transporter details"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				LOA_transporter_cd.add(rset.getString(1)==null?"":rset.getString(1));
				LOA_transporter_abbr.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Transporter_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_FGSA_Transporter_details()
	{
		try
		{
			queryString= "Select distinct(A.TRANSPORTER_CD), B.TRANSPORTER_ABBR  from FMS7_FGSA_TRANSPORTER_MST A, FMS7_TRANSPORTER_MST B WHERE " +
					"A.TRANSPORTER_CD=B.TRANSPORTER_CD AND A.FGSA_NO='"+FGSA_cd+"' AND A.REV_NO='"+FGSA_REVNo+"' AND A.CUSTOMER_CD='"+bscode+"'";
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				transporter_cd.add(rset.getString(1)==null?"":rset.getString(1));
				transporter_abbr.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Transporter_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_FGSA_Transporter_Details_For_SN()
	{
		try
		{
			queryString= "Select distinct(A.TRANSPORTER_CD), B.TRANSPORTER_ABBR  from FMS7_FGSA_TRANSPORTER_MST A, FMS7_TRANSPORTER_MST B WHERE " +
					"A.TRANSPORTER_CD=B.TRANSPORTER_CD AND A.FGSA_NO='"+FGSA_cd+"' AND A.REV_NO='"+FGSA_REVNo+"' AND A.CUSTOMER_CD='"+bscode+"'";
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				FGSA_transporter_cd.add(rset.getString(1)==null?"":rset.getString(1));
				FGSA_transporter_abbr.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Transporter_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_Tender_Transporter_details()
	{
		try
		{
			queryString="Select distinct(A.TRANSPORTER_CD) , B.TRANSPORTER_ABBR  from FMS7_TENDER_TRANSPORTER_MST A, " +
			"FMS7_TRANSPORTER_MST B WHERE A.TRANSPORTER_CD = B.TRANSPORTER_CD AND  A.TENDER_NO='"+tender_cd+"'" +
			"AND A.CUSTOMER_CD='"+bscode+"' ";
			//System.out.println("TENDER TRANSPORTER :"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				Tender_transporter_cd.add(rset.getString(1)==null?"":rset.getString(1));
				Tender_transporter_abbr.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Transporter_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_SN_Plant_details()
	{
		try
		{
			queryString= "Select PLANT_SEQ_NO  from FMS7_SN_PLANT_MST  WHERE " +
					" FGSA_NO='"+FGSA_cd+"' AND FGSA_REV_NO = '"+FGSA_REVNo+"' AND CUSTOMER_CD='"+bscode+"'   and SN_NO='"+SN_CD+"' and SN_REV_NO='"+SN_REVNo+"'  ";
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
			  sn_vPLANT_SEQ_NO.add(rset.getString(1)==null?"":rset.getString(1));
			}
			
			for(int j=0;j<vPLANT_SEQ_NO.size();j++)
			{
				queryString= "Select PLANT_NAME from FMS7_CUSTOMER_PLANT_DTL A WHERE A.CUSTOMER_CD='"+bscode+"' AND " +
						"A.SEQ_NO='"+vPLANT_SEQ_NO.elementAt(j)+"' AND A.EFF_DT=(SELECT MAX(C.EFF_DT) FROM " +
						"FMS7_CUSTOMER_PLANT_DTL C WHERE C.CUSTOMER_CD='"+bscode+"' AND A.SEQ_NO = C.SEQ_NO ) ";
				//System.out.println(queryString);
				rset1 = stmt.executeQuery(queryString);
				if(rset1.next())
				{
				   sn_vPLANT_NAME.add(rset1.getString(1)==null?"":rset1.getString(1));
				}
			}			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Plant_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_LOA_Plant_details()
	{
		try
		{					
			queryString= "Select PLANT_SEQ_NO  from FMS7_LOA_PLANT_MST  WHERE " +
					" TENDER_NO='"+tender_cd+"' AND CUSTOMER_CD='"+bscode+"'   and LOA_NO='"+LOA_CD+"' " +
					" and LOA_REV_NO='"+LOA_REVNo+"'  ";
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
			  LOA_vPLANT_SEQ_NO.add(rset.getString(1)==null?"":rset.getString(1));
			}
			
			for(int j=0;j<vPLANT_SEQ_NO.size();j++)
			{
				queryString= "Select PLANT_NAME  from FMS7_CUSTOMER_PLANT_DTL  A WHERE " +
				" A.CUSTOMER_CD='"+bscode+"' AND A.SEQ_NO = '"+vPLANT_SEQ_NO.elementAt(j)+"'" +
				" AND A.EFF_DT = (SELECT MAX(C.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL C WHERE" +
				" C.CUSTOMER_CD='"+bscode+"' AND A.SEQ_NO = C.SEQ_NO )  ";
				rset1 = stmt.executeQuery(queryString);
				if(rset1.next())
				{
				   LOA_vPLANT_NAME.add(rset1.getString(1)==null?"":rset1.getString(1));
				}
			}			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Plant_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_FGSA_Plant_details()
	{
		try
		{
			queryString = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
						  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.flag='T' " +
						  "AND A.customer_cd="+bscode+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
						  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd="+bscode+" AND " +
						  "B.seq_no=A.seq_no) " +
						  "ORDER BY A.seq_no";
			//System.out.println("Customer Plant Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					buyer_plant_seq_no.add(rset.getString(1));
					buyer_plant_type.add(rset.getString(2));
					buyer_plant_nm.add(rset.getString(3));					
				}
			}
			
			queryString= "Select PLANT_SEQ_NO from FMS7_FGSA_PLANT_MST WHERE " +
						 "FGSA_NO='"+FGSA_cd+"' AND REV_NO='"+FGSA_REVNo+"' AND CUSTOMER_CD='"+bscode+"'";
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				vPLANT_SEQ_NO.add(rset.getString(1)==null?"":rset.getString(1));
			}			
			for(int j=0;j<vPLANT_SEQ_NO.size();j++)
			{
				queryString= "Select PLANT_NAME from FMS7_CUSTOMER_PLANT_DTL A WHERE " +
							 "A.CUSTOMER_CD='"+bscode+"' AND A.SEQ_NO='"+vPLANT_SEQ_NO.elementAt(j)+"' AND A.EFF_DT = (SELECT MAX(C.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL C WHERE C.CUSTOMER_CD='"+bscode+"' AND A.SEQ_NO = C.SEQ_NO )  ";
				rset1 = stmt.executeQuery(queryString);
				if(rset1.next())
				{
				   vPLANT_NAME.add(rset1.getString(1)==null?"":rset1.getString(1));
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Plant_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_Tender_Plant_details()
	{
		try
		{	
			queryString = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.flag='T' " +
			  "AND A.customer_cd="+bscode+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd="+bscode+" AND " +
			  "B.seq_no=A.seq_no) ORDER BY A.seq_no";
			//System.out.println("Customer Plant Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					buyer_plant_seq_no.add(rset.getString(1));
					buyer_plant_type.add(rset.getString(2));
					buyer_plant_nm.add(rset.getString(3));					
				}
			}
			
			queryString= "Select PLANT_SEQ_NO  from FMS7_TENDER_PLANT_MST  WHERE " +
						 "TENDER_NO='"+tender_cd+"'AND CUSTOMER_CD='"+bscode+"' ";
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				vPLANT_SEQ_NO.add(rset.getString(1)==null?"":rset.getString(1));
			}
			
			for(int j=0;j<vPLANT_SEQ_NO.size();j++)
			{
				queryString= "Select PLANT_NAME  from FMS7_CUSTOMER_PLANT_DTL  A WHERE " +
				" A.CUSTOMER_CD='"+bscode+"' AND A.SEQ_NO = '"+vPLANT_SEQ_NO.elementAt(j)+"' " +
				" AND A.EFF_DT = (SELECT MAX(C.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL C WHERE " +
				" C.CUSTOMER_CD='"+bscode+"' AND A.SEQ_NO = C.SEQ_NO )  ";
				//System.out.println(queryString);
				rset1 = stmt.executeQuery(queryString);
				if(rset1.next())
				{
				   vPLANT_NAME.add(rset1.getString(1)==null?"":rset1.getString(1));
				}
			}			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Plant_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_Buyer_details()
	{
		try{
			queryString = "select customer_name,customer_abbr from fms7_customer_mst where customer_cd = '"+bscode+"' and " +
					" eff_dt = (select max(eff_dt) from fms7_customer_mst where customer_cd = '"+bscode+"')";
			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				bName = rset.getString(1)==null?"":rset.getString(1);
				bAbbr = rset.getString(2)==null?"":rset.getString(2);
			}
			
		}catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Buyer_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	boolean invoiceFlag = false;
	public void fetch_SN_details()
	{	
		try
		{
			queryString = "SELECT TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),TO_CHAR(RENEWAL_DT,'DD/MM/YYYY'), " +
					"TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ, " +
					"QUANTITY_UNIT, GCV, NCV, RATE, RATE_UNIT, STATUS, BUYER_NOM, " +
					"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, " +
					"SELLER_MONTH_NOM, SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, " +
					"DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, " +
					"MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, " +
					"OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
					"SPEC_GAS_MAX_ENERGY, DCQ, VERIFY_FLAG , APPROVE_FLAG, " +
					"VARIATION_QTY, VARIATION_MODE, MDCQ_QTY_CD, OBLIGATION, " +
					"OBLIG_PERCENTAGE, OBLIG_QTY_CD, SN_NAME, TO_CHAR(REV_DT,'DD/MM/YYYY'), " +
					"SN_CLOSURE_FLAG, SN_CLOSURE_QTY, TO_CHAR(SN_CLOSURE_DT,'DD/MM/YYYY'), " +
					"SN_REF_NO, TRANSPORTATION_CHARGE, REMARK, ADV_AMT," +
					"TCQ_REQUEST_FLAG,TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY,FCC_FLAG," +
					"SN_CLOSURE_REQUEST,SN_CLOSURE_CLOSE,FCC_BY," +
					"TO_CHAR(FCC_DATE,'DD/MM/YYYY'),TCQ_REQUEST_CLOSE,SN_REMARK "
					+ ",ADVANCE_COLLECTION,ADVANCE_COLLECTION_FLAG,NVL(FIRM_QTY,''),NVL(RE_QTY,''),"
					+ "NVL(SPLIT_TCQ_FLAG,'N') "+
					"FROM FMS7_SN_MST WHERE SN_NO='"+SN_CD+"' AND " +
					"SN_REV_NO='"+SN_REVNo+"' AND CUSTOMER_CD='"+bscode+"' AND " +
					"FGSA_NO='"+FGSA_cd+"' AND FGSA_REV_NO = '"+FGSA_REVNo+"'";			
			//System.out.println("SN Master Query = "+queryString);
			rset = stmt.executeQuery(queryString);			
			if(rset.next())
			{
				SIGNING_DT  =rset.getString(1)==null?"":rset.getString(1);
				RENEWAL_DT  =rset.getString(2)==null?"":rset.getString(2);
				START_DT    =rset.getString(3)==null?"":rset.getString(3);
				END_DT      =rset.getString(4)==null?"":rset.getString(4);
				TCQ			=rset.getString(5)==null?"":rset.getString(5);
				QUANTITY_UNIT=rset.getString(6)==null?"":rset.getString(6);
				GCV			=rset.getString(7)==null?"":rset.getString(7);
				NCV         =rset.getString(8)==null?"":rset.getString(8);
				String rate = rset.getString(9)==null?"":rset.getString(9);
				if(!rate.equals(""))
				{
					rate = nf2.format(Double.parseDouble(rate));
				}
			//	System.out.println("In DataBean--> Rate"+rate);
				RATE			= rate;
				RATE_UNIT		=rset.getString(10)==null?"":rset.getString(10);
				STATUS			=rset.getString(11)==null?"":rset.getString(11);
				BUYER_NOM		=rset.getString(12)==null?"":rset.getString(12);
				BUYER_MONTH_NOM	=rset.getString(13)==null?"":rset.getString(13);
				BUYER_WEEK_NOM	=rset.getString(14)==null?"":rset.getString(14);
				BUYER_DAILY_NOM	=rset.getString(15)==null?"":rset.getString(15);
				SELLER_NOM		=rset.getString(16)==null?"":rset.getString(16);
                SELLER_MONTH_NOM=rset.getString(17)==null?"":rset.getString(17);
                SELLER_WEEK_NOM	=rset.getString(18)==null?"":rset.getString(18);
                SELLER_DAILY_NOM=rset.getString(19)==null?"":rset.getString(19);
                DAY_DEF			=rset.getString(20)==null?"":rset.getString(20);
                DAY_START_TIME	=rset.getString(21)==null?"":rset.getString(21);
                DAY_END_TIME	=rset.getString(22)==null?"":rset.getString(22);
                MDCQ			=rset.getString(23)==null?"":rset.getString(23);
                MDCQ_PERCENTAGE	=rset.getString(24)==null?"":rset.getString(24);
                MEASUREMENT		=rset.getString(25)==null?"":rset.getString(25);
                MEAS_STANDARD	=rset.getString(26)==null?"":rset.getString(26);
                MEAS_TEMPERATURE	=rset.getString(27)==null?"":rset.getString(27);
                PRESSURE_MIN_BAR	=rset.getString(28)==null?"":rset.getString(28);
                PRESSURE_MAX_BAR	=rset.getString(29)==null?"":rset.getString(29);
                OFF_SPEC_GAS		=rset.getString(30)==null?"":rset.getString(30);
                SPEC_GAS_ENERGY_BASE=rset.getString(31)==null?"":rset.getString(31);
                SPEC_GAS_MIN_ENERGY	=rset.getString(32)==null?"":rset.getString(32);
                SPEC_GAS_MAX_ENERGY =rset.getString(33)==null?"":rset.getString(33);
                DCQ = rset.getString(34)==null?"":rset.getString(34);
                VERIFY_FLAG = rset.getString(35)==null?"":rset.getString(35);
                APPROVE_FLAG =rset.getString(36)==null?"":rset.getString(36);
                VARIATION_QTY =rset.getString(37)==null?"":rset.getString(37);
                VARIATION_MODE =rset.getString(38)==null?"":rset.getString(38);
                MDCQ_QTY_CD=rset.getString(39)==null?"":rset.getString(39);
                OBLIGATION =rset.getString(40)==null?"":rset.getString(40);
                OBLIG_PERCENTAGE =rset.getString(41)==null?"":rset.getString(41);
                OBLIG_QTY_CD =rset.getString(42)==null?"":rset.getString(42);
                SN_NAME = rset.getString(43)==null?"":rset.getString(43);
                REV_DT = rset.getString(44)==null?"":rset.getString(44);
                SN_CLOSURE_FLAG =rset.getString(45)==null?"N":rset.getString(45);
                SN_CLOSURE_QTY = rset.getString(46)==null?"":rset.getString(46);
                SN_CLOSURE_DT = rset.getString(47)==null?"":rset.getString(47);
                SN_REF_NO = rset.getString(48)==null?"":rset.getString(48);
                String trans_charge = rset.getString(49)==null?"":rset.getString(49);
				if(!trans_charge.equals(""))
				{
					trans_charge = nf2.format(Double.parseDouble(trans_charge));
				}
				TRANSPORTATION_CHARGE = trans_charge;
				REMARK = rset.getString(50)==null?"":rset.getString(50);
				adv_amt = rset.getString(51)==null?"":rset.getString(51);
				tcq_req_flag = rset.getString(52)==null?"N":rset.getString(52);
				tcq_sign = rset.getString(53)==null?"+":rset.getString(53);
				var_tcq = rset.getString(54)==null?"0":rset.getString(54);
				
				fcc_flag = rset.getString(55)==null?"":rset.getString(55);
				if(fcc_flag.trim().equalsIgnoreCase("Y"))
				{
					FCC_flag="Approved";
				}
				else if(fcc_flag.trim().equalsIgnoreCase("N"))
				{
					FCC_flag="Disapproved";
				}
				else	
				{
					FCC_flag="Pending";
				}
				SN_CLOSURE_REQUEST = rset.getString(56)==null?"N":rset.getString(56);
				SN_CLOSURE_CLOSE = rset.getString(57)==null?"N":rset.getString(57);
				fcc_by = rset.getString(58)==null?"":rset.getString(58);
				fcc_date = rset.getString(59)==null?"":rset.getString(59);
				tcq_req_close = rset.getString(60)==null?"":rset.getString(60); 
				SN_REMARK = rset.getString(61)==null?"0":rset.getString(61);//JHP20111224
				ADVANCE_COLLECTION = rset.getString(62)==null?"N":rset.getString(62);
				ADVANCE_COLLECTION_FLAG = rset.getString(63)==null?"N":rset.getString(63);
				firm_qty = rset.getString(64)==null?"":rset.getString(64);
				re_qty = rset.getString(65)==null?"":rset.getString(65);
				split_tcq_flag = rset.getString(66)==null?"N":rset.getString(66);
//				adjust_flag = rset.getString(62)==null?"N":rset.getString(62); //RG20140830
//				String temprate = rset.getString(63)==null?"":rset.getString(63); //NB20140911
//				if(!rate.equals(""))
//				{
//					temprate = nf2.format(Double.parseDouble(temprate));
//				}
//				SALES_RATE_INR=temprate;
				
				//FETCHING INVOICE DETAILS
				String query = "SELECT COUNT(HLPL_INV_SEQ_NO) FROM FMS7_INVOICE_MST WHERE SN_NO='"+SN_CD+"' AND FGSA_NO='"+FGSA_cd+"' AND CUSTOMER_CD='"+bscode+"' AND CONTRACT_TYPE='S' ";
//				System.out.println("Fetching details--"+query);
				rset = stmt.executeQuery(query);
				if(rset.next()) {
					int count = rset.getInt(1);
					if(count>0) {
						invoiceFlag = true;
					}
				}
				
			}
			//RG20200129
			queryString="SELECT FGSA_BASE FROM FMS7_FGSA_MST WHERE FGSA_NO='"+FGSA_cd+"' AND REV_NO='"+FGSA_REVNo+"' AND CUSTOMER_CD='"+bscode+"'";
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				agreement_base=rset.getString(1)==null?"":rset.getString(1);
			}
			
			
			queryString="SELECT COUNT(GAS_DT) FROM FMS7_DAILY_BUYER_NOM_DTL WHERE CONTRACT_TYPE='S' "
					+ "AND FGSA_NO = '"+FGSA_cd+"' AND SN_NO = '"+SN_CD+"' "
					+ "AND CUSTOMER_CD = '"+bscode+"'";
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				count_price = rset.getInt(1);
			}
			//
			
			if(SN_CD!=null && !SN_CD.equals("") && !SN_CD.equals(" "))
			{
				if(bscode!=null && !bscode.equals("") && !bscode.equals(" "))
				{
					if(FGSA_cd!=null && !FGSA_cd.equals("") && !FGSA_cd.equals(" "))
					{
						if(FGSA_REVNo!=null && !FGSA_REVNo.equals("") && !FGSA_REVNo.equals(" "))
						{
							queryString = "SELECT SN_REF_NO " +
										  "FROM FMS7_SN_MST WHERE SN_NO='"+(Integer.parseInt(SN_CD)-1)+"' AND " +
										  "CUSTOMER_CD='"+bscode+"' AND " +
										  "FGSA_NO='"+FGSA_cd+"' AND SN_REF_NO IS NOT NULL";
					
							//System.out.println("Previous SN Master Query = "+queryString);
							rset = stmt.executeQuery(queryString);			
							if(rset.next())
							{
								PRE_SN_REF_NO = rset.getString(1);
							}						
							
							queryString = "SELECT SUM(QTY_MMBTU) " +
										"FROM FMS7_DAILY_ALLOCATION_DTL WHERE " +
										"SN_NO="+SN_CD+" AND " +
										"CUSTOMER_CD="+bscode+" AND " +
										"FGSA_NO="+FGSA_cd+" AND " +
										"CONTRACT_TYPE='S'";
							
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
							
							int cnt = 0;
							queryString = "SELECT COUNT(CUSTOMER_CD) FROM FMS7_SN_DCQ_DTL WHERE " +
										  "SN_NO="+SN_CD+" AND CUSTOMER_CD="+bscode+" AND " +
										  "FGSA_NO="+FGSA_cd+" AND SN_REV_NO="+SN_REVNo+" AND " +
										  "FGSA_REV_NO="+FGSA_REVNo+"";			
							//System.out.println("LOA Already Allocated QTY Query = "+queryString);
							rset = stmt.executeQuery(queryString);			
							if(rset.next())
							{
								cnt = Integer.parseInt(rset.getString(1));
							}
							
							if(cnt>0)
							{
								DCQ_FLAG = "Y";
							}
							else
							{
								DCQ_FLAG = "N";
							}
							
							queryString = "SELECT B.REMARK " +
										  "FROM FMS7_SN_CARGO_DTL A, FMS7_MAN_CONFIRM_CARGO_DTL B " +
										  "WHERE A.SN_NO="+SN_CD+" AND A.SN_REV_NO="+SN_REVNo+" AND " +
										  "A.CUSTOMER_CD="+bscode+" AND A.FGSA_NO="+FGSA_cd+" AND " +
										  "A.FGSA_REV_NO="+FGSA_REVNo+" AND A.CARGO_REF_NO=B.CARGO_REF_CD AND " +
										  "B.REMARK IS NOT NULL";			
							//System.out.println("Formula Remark Query = "+queryString);
							rset = stmt.executeQuery(queryString);			
							if(rset.next())
							{
								if(rset.getString(1)!=null)
								{
									if(!rset.getString(1).trim().equals(""))
									{
										FORMULA_REMARK = rset.getString(1).trim();
									}
								}
							}
						}
					}
				}
			}
		}
	    catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_LOA_details()
	{	
		try
		{
			queryString = "select TO_CHAR(SIGNING_DT,'DD/MM/YYYY'), TO_CHAR(RENEWAL_DT,'DD/MM/YYYY')," +
					" TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ," +
					" QUANTITY_UNIT, GCV, NCV, RATE, RATE_UNIT, STATUS, BUYER_NOM," +
					" BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM," +
					" SELLER_MONTH_NOM, SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF," +
					" DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT," +
					" MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, " +
					" OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY," +
					" SPEC_GAS_MAX_ENERGY, DCQ, VERIFY_FLAG , APPROVE_FLAG, " +
					" VARIATION_QTY,VARIATION_MODE,MDCQ_QTY_CD, LOA_NAME, LOA_REF_NO, " +
					" TRANSPORTATION_CHARGE, REMARK, ADV_AMT,FCC_FLAG," +
					" LOA_CLOSURE_REQUEST,LOA_CLOSURE_CLOSE,LOA_CLOSURE_FLAG,LOA_CLOSURE_QTY," +
					" TO_CHAR(LOA_CLOSURE_DT,'DD/MM/YYYY'),TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY,FCC_BY," +
					" TO_CHAR(FCC_DATE,'DD/MM/YYYY') "
					+ ",ADVANCE_COLLECTION,ADVANCE_COLLECTION_FLAG,FIRM_QTY,RE_QTY,SPLIT_TCQ_FLAG,TCQ_REQUEST_FLAG " +
					"from FMS7_LOA_MST " +
					" where LOA_NO='"+LOA_CD+"' and LOA_REV_NO='"+LOA_REVNo+"' AND CUSTOMER_CD='"+bscode+"' AND" +
					" Tender_NO='"+tender_cd+"'";
			//System.out.println("Query String"+queryString);
			rset = stmt.executeQuery(queryString);		
			if(rset.next())
			{
				SIGNING_DT  		=rset.getString(1)==null?"":rset.getString(1);
				RENEWAL_DT  		=rset.getString(2)==null?"":rset.getString(2);
				START_DT    		=rset.getString(3)==null?"":rset.getString(3);
				END_DT      		=rset.getString(4)==null?"":rset.getString(4);
				TCQ					=rset.getString(5)==null?"":rset.getString(5);
				QUANTITY_UNIT		=rset.getString(6)==null?"":rset.getString(6);
				GCV					=rset.getString(7)==null?"":rset.getString(7);
				NCV         		=rset.getString(8)==null?"":rset.getString(8);
				String rate = rset.getString(9)==null?"":rset.getString(9);
				if(!rate.equals(""))
				{
				  rate = nf2.format(Double.parseDouble(rate));
				}
				RATE				= rate;
				RATE_UNIT			=rset.getString(10)==null?"":rset.getString(10);
				STATUS				=rset.getString(11)==null?"":rset.getString(11);
				BUYER_NOM			=rset.getString(12)==null?"":rset.getString(12);
				BUYER_MONTH_NOM		=rset.getString(13)==null?"":rset.getString(13);
				BUYER_WEEK_NOM		=rset.getString(14)==null?"":rset.getString(14);
				BUYER_DAILY_NOM		=rset.getString(15)==null?"":rset.getString(15);
				SELLER_NOM			=rset.getString(16)==null?"":rset.getString(16);
                SELLER_MONTH_NOM	=rset.getString(17)==null?"":rset.getString(17);
                SELLER_WEEK_NOM		=rset.getString(18)==null?"":rset.getString(18);
                SELLER_DAILY_NOM	=rset.getString(19)==null?"":rset.getString(19);
                DAY_DEF				=rset.getString(20)==null?"":rset.getString(20);
                DAY_START_TIME		=rset.getString(21)==null?"":rset.getString(21);
                DAY_END_TIME		=rset.getString(22)==null?"":rset.getString(22);
                MDCQ				=rset.getString(23)==null?"":rset.getString(23);
                MDCQ_PERCENTAGE		=rset.getString(24)==null?"":rset.getString(24);
                MEASUREMENT			=rset.getString(25)==null?"":rset.getString(25);
                MEAS_STANDARD		=rset.getString(26)==null?"":rset.getString(26);
                MEAS_TEMPERATURE	=rset.getString(27)==null?"":rset.getString(27);
                PRESSURE_MIN_BAR	=rset.getString(28)==null?"":rset.getString(28);
                PRESSURE_MAX_BAR	=rset.getString(29)==null?"":rset.getString(29);
                OFF_SPEC_GAS		=rset.getString(30)==null?"":rset.getString(30);
                SPEC_GAS_ENERGY_BASE=rset.getString(31)==null?"":rset.getString(31);
                SPEC_GAS_MIN_ENERGY	=rset.getString(32)==null?"":rset.getString(32);
                SPEC_GAS_MAX_ENERGY =rset.getString(33)==null?"":rset.getString(33);
                DCQ 				=rset.getString(34)==null?"":rset.getString(34);
                VERIFY_FLAG 		=rset.getString(35)==null?"":rset.getString(35);
                APPROVE_FLAG 		=rset.getString(36)==null?"":rset.getString(36);
                VARIATION_QTY 		=rset.getString(37)==null?"":rset.getString(37);
                VARIATION_MODE 		=rset.getString(38)==null?"":rset.getString(38);
                MDCQ_QTY_CD			=rset.getString(39)==null?"":rset.getString(39);
                LOA_NAME 			=rset.getString(40)==null?"":rset.getString(40);
                LOA_REF_NO = rset.getString(41)==null?"":rset.getString(41);
                String trans_charge = rset.getString(42)==null?"":rset.getString(42);
                if(!trans_charge.equals(""))
				{
                	trans_charge = nf2.format(Double.parseDouble(trans_charge));
				}
                TRANSPORTATION_CHARGE = trans_charge;
                REMARK = rset.getString(43)==null?"":rset.getString(43);
                adv_amt = rset.getString(44)==null?"":rset.getString(44);
                //System.out.println("LOA NAME"+LOA_NAME);                
                fcc_flag = rset.getString(45)==null?"":rset.getString(45);
				if(fcc_flag.trim().equalsIgnoreCase("Y"))
				{
					FCC_flag="Approved";
				}
				else if(fcc_flag.trim().equalsIgnoreCase("N"))
				{
					FCC_flag="Disapproved";
				}
				else	
				{
					FCC_flag="Pending";
				}	
				LOA_CLOSURE_REQUEST = rset.getString(46)==null?"":rset.getString(46);
				LOA_CLOSURE_CLOSE = rset.getString(47)==null?"":rset.getString(47);
				SN_CLOSURE_FLAG = rset.getString(48)==null?"":rset.getString(48);
				SN_CLOSURE_QTY = rset.getString(49)==null?"":rset.getString(49);
				SN_CLOSURE_DT = rset.getString(50)==null?"":rset.getString(50);
				tcq_sign = rset.getString(51)==null?"":rset.getString(51);
				var_tcq = rset.getString(52)==null?"":rset.getString(52);
				fcc_by = rset.getString(53)==null?"":rset.getString(53);
				fcc_date = rset.getString(54)==null?"":rset.getString(54);
				ADVANCE_COLLECTION = rset.getString(55)==null?"N":rset.getString(55);
				ADVANCE_COLLECTION_FLAG = rset.getString(56)==null?"N":rset.getString(56);
				firm_qty = rset.getString(57)==null?"":rset.getString(57);
				re_qty = rset.getString(58)==null?"":rset.getString(58);
				split_tcq_flag = rset.getString(59)==null?"N":rset.getString(59);
				tcq_req_flag = rset.getString(60)==null?"N":rset.getString(60);
				
	//			adjust_flag = rset.getString(55)==null?"N":rset.getString(55);
//				String temprate = rset.getString(56)==null?"":rset.getString(56); //NB20140911
//				if(!rate.equals(""))
//				{
//					temprate = nf2.format(Double.parseDouble(temprate));
//				}
//				SALES_RATE_INR=temprate;
				
				//FETCHING INVOICE DETAILS
				invoiceFlag = false;
				String query = "SELECT COUNT(HLPL_INV_SEQ_NO) FROM FMS7_INVOICE_MST WHERE SN_NO='"+LOA_CD+"' AND FGSA_NO='"+tender_cd+"' AND CUSTOMER_CD='"+bscode+"' AND CONTRACT_TYPE='L' ";
//				System.out.println("Fetching details--"+query);
				rset = stmt.executeQuery(query);
				if(rset.next()) {
					int count = rset.getInt(1);
					if(count>0) {
						invoiceFlag = true;
					}
				}
			}
			queryString="SELECT TENDER_BASE FROM FMS7_TENDER_MST WHERE TENDER_NO='"+tender_cd+"' AND CUSTOMER_CD='"+bscode+"'";
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				agreement_base=rset.getString(1)==null?"":rset.getString(1);
			}
			queryString="SELECT COUNT(GAS_DT) FROM FMS7_DAILY_BUYER_NOM_DTL WHERE CONTRACT_TYPE='L' "
						+ "AND FGSA_NO = '"+tender_cd+"' AND SN_NO = '"+LOA_CD+"' "
						+ "AND CUSTOMER_CD = '"+bscode+"'";
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				count_price=rset.getInt(1);
			}
			
			//System.out.println("bscode= "+bscode+", FGSA_CD= "+tender_cd+", LOA_CD= "+LOA_CD+",LOA_REVNO= "+LOA_REVNo);
			
			if(LOA_CD!=null && !LOA_CD.equals("") && !LOA_CD.equals(" "))
			{
				if(bscode!=null && !bscode.equals("") && !bscode.equals(" "))
				{
					if(tender_cd!=null && !tender_cd.equals("") && !tender_cd.equals(" "))
					{
						queryString = "SELECT LOA_REF_NO " +
									  "FROM FMS7_LOA_MST WHERE LOA_NO='"+(Integer.parseInt(LOA_CD)-1)+"' AND " +
									  "CUSTOMER_CD='"+bscode+"' AND " +
									  "TENDER_NO='"+tender_cd+"' AND LOA_REF_NO IS NOT NULL";				
						//System.out.println("Previous SN Master Query = "+queryString);
						rset = stmt.executeQuery(queryString);			
						if(rset.next())
						{
							PRE_LOA_REF_NO = rset.getString(1);
						}
						
						queryString = "SELECT SUM(QTY_MMBTU) FROM FMS7_DAILY_ALLOCATION_DTL WHERE SN_NO="+LOA_CD+" AND " +
						"CUSTOMER_CD="+bscode+" AND FGSA_NO="+tender_cd+" AND FGSA_REV_NO=0 AND CONTRACT_TYPE='L'";			
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
						int cnt = 0;
						queryString = "SELECT COUNT(CUSTOMER_CD) FROM FMS7_LOA_DCQ_DTL WHERE LOA_NO="+LOA_CD+" AND " +
						"CUSTOMER_CD="+bscode+" AND TENDER_NO="+tender_cd+" AND LOA_REV_NO="+LOA_REVNo+"";			
						//System.out.println("LOA Already Allocated QTY Query = "+queryString);
						rset = stmt.executeQuery(queryString);			
						if(rset.next())
						{
							cnt = Integer.parseInt(rset.getString(1));
						}
						
						if(cnt>0)
						{
							DCQ_FLAG = "Y";
						}
						else
						{
							DCQ_FLAG = "N";
						}
						
						queryString = "SELECT B.REMARK " +
									  "FROM FMS7_LOA_CARGO_DTL A, FMS7_MAN_CONFIRM_CARGO_DTL B " +
									  "WHERE A.LOA_NO="+LOA_CD+" AND A.LOA_REV_NO="+LOA_REVNo+" AND " +
									  "A.CUSTOMER_CD="+bscode+" AND A.TENDER_NO="+tender_cd+" AND " +
									  "A.CARGO_REF_NO=B.CARGO_REF_CD AND " +
									  "B.REMARK IS NOT NULL";			
						//System.out.println("LOA Already Allocated QTY Query = "+queryString);
						rset = stmt.executeQuery(queryString);			
						if(rset.next())
						{
							if(rset.getString(1)!=null)
							{
								if(!rset.getString(1).trim().equals(""))
								{
									FORMULA_REMARK = rset.getString(1).trim();
								}
							}
						}
					}
				}				
			}			
		}
	    catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LOA_details() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	public void clearVector() 
	{
		vTRANSPORTER_CD.clear();
		vPLANT_SEQ_NO.clear();
		vCLAUSE_CD.clear();
		vREMARK.clear();
	}
	
	public void fetchBuyers2() 
	{
				
		try 
		{
			
				queryString ="Select CUSTOMER_NAME, CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
						" where CUSTOMER_CD='"+bscode+"'";
				//System.out.println("fetch FMS7_RE_GAS_TRANSPORTER_MST Query  : "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					  bName = rset.getString(1)==null?"":rset.getString(1);
					  bAbbr = rset.getString(2)==null?"":rset.getString(2);
				}
		
			
			
				int count = 0;
				Vector plant_seq = new Vector();
				Vector plant_nm = new Vector();
				Vector plant_type = new Vector();
				Vector temp_plant_seq = new Vector();
				Vector temp_plant_nm = new Vector();
				Vector temp_plant_type = new Vector();				
				temp_plant_nm.add("");
				temp_plant_type.add("");
				temp_plant_seq.add("0");
				
			/*SB20139811	queryString = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE " +
				  			  "A.customer_cd="+bscode+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd="+bscode+" AND " +
				  			  "B.seq_no=A.seq_no AND B.eff_dt<=TO_DATE('"+sign_date+"','DD/MM/YYYY')) " +
				  			  "ORDER BY A.seq_no";
				*/
				queryString = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
	  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE " +
	  			  "A.customer_cd="+bscode+" "+
	  			  "ORDER BY A.seq_no";
				//System.out.println("TENDER:PLANT:QRY:P001: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
					{
						plant_seq.add(rset.getString(1));
						plant_type.add(rset.getString(2));
						plant_nm.add(rset.getString(3));
						++count;
					}
				}
				if(count==0)
				{
					buyer_plant_seq_no.add(temp_plant_seq);
					buyer_plant_type.add(temp_plant_type);
					buyer_plant_nm.add(temp_plant_nm);
				}
				else
				{
					buyer_plant_seq_no.add(plant_seq);
					buyer_plant_type.add(plant_type);
					buyer_plant_nm.add(plant_nm);
				}
			
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchAllClauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetchTENDERData() 
	{		
		try 
		{
			//Get Data from  FMS7_Tender_MST
			queryString ="Select to_char(SIGNING_DT,'dd/mm/yyyy'), to_char(START_DT,'dd/mm/yyyy'), " +
					     "to_char(END_DT,'dd/mm/yyyy'), Tender_BASE, SALE_PRICE, STATUS, BUYER_NOM, " +
					     "BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM,SELLER_NOM, SELLER_MONTH_NOM, " +
					     "SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, " +
					     "MDCQ_PERCENTAGE, MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, " +
					     "PRESSURE_MAX_BAR, OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
					     "SPEC_GAS_MAX_ENERGY,DCQ,TCQ,to_char(TENDER_SUBMIT_DT,'dd/mm/yyyy')," +
					     "to_char(TENDER_CLOSING_DT,'dd/mm/yyyy'),REMARKS, BUYER_NOM_CLAUSE, " +
					     "SELLER_NOM_CLAUSE, LC_CLAUSE, BILLING_CLAUSE, LIABILITY_CLAUSE,"
					     + "ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG,REOPEN_REQUEST_FLAG,NVL(REOPEN_APPROVAL_FLAG,'') " +
					     "from DLNG_Tender_MST where " +
					     "CUSTOMER_CD='"+bscode+"' and  Tender_NO='"+tender_cd+"' and FLAG='Y'";
			System.out.println("fetch TenderData Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				  SIGNING_DT = rset.getString(1)==null?"":rset.getString(1);
				  START_DT = rset.getString(2)==null?"":rset.getString(2);
				  END_DT = rset.getString(3)==null?"":rset.getString(3);
				  Tender_BASE = rset.getString(4)==null?"":rset.getString(4);
				  String rate = rset.getString(5)==null?"":rset.getString(5);
				  if(!rate.equals(""))
				  {
					  rate = nf2.format(Double.parseDouble(rate));
				  }
				  SALE_PRICE = rate;
				  STATUS = rset.getString(6)==null?"":rset.getString(6);
				  BUYER_NOM=rset.getString(7)==null?"":rset.getString(7);
				  BUYER_MONTH_NOM = rset.getString(8)==null?"":rset.getString(8);
				  BUYER_WEEK_NOM = rset.getString(9)==null?"":rset.getString(9);
				  BUYER_DAILY_NOM = rset.getString(10)==null?"":rset.getString(10);
				  SELLER_NOM = rset.getString(11)==null?"":rset.getString(11);
				  SELLER_MONTH_NOM = rset.getString(12)==null?"":rset.getString(12);
				  SELLER_WEEK_NOM = rset.getString(13)==null?"":rset.getString(13);
				  SELLER_DAILY_NOM = rset.getString(14)==null?"":rset.getString(14);
				  DAY_DEF = rset.getString(15)==null?"":rset.getString(15);
				  DAY_START_TIME = rset.getString(16)==null?"":rset.getString(16);
				  DAY_END_TIME = rset.getString(17)==null?"":rset.getString(17);
				  MDCQ = rset.getString(18)==null?"":rset.getString(18);
				  MDCQ_PERCENTAGE = rset.getString(19)==null?"":rset.getString(19);
				  MEASUREMENT = rset.getString(20)==null?"":rset.getString(20);
				  MEAS_STANDARD = rset.getString(21)==null?"":rset.getString(21);
				  MEAS_TEMPERATURE = rset.getString(22)==null?"":rset.getString(22);
				  PRESSURE_MIN_BAR = rset.getString(23)==null?"":rset.getString(23);
				  PRESSURE_MAX_BAR = rset.getString(24)==null?"":rset.getString(24);
				  OFF_SPEC_GAS = rset.getString(25)==null?"":rset.getString(25);
				  SPEC_GAS_ENERGY_BASE = rset.getString(26)==null?"":rset.getString(26);
				  SPEC_GAS_MIN_ENERGY = rset.getString(27)==null?"":rset.getString(27);
				  SPEC_GAS_MAX_ENERGY = rset.getString(28)==null?"":rset.getString(28);
				  DCQ  = rset.getString(29)==null?"0":rset.getString(29);
				  TCQ = rset.getString(30)==null?"0":rset.getString(30); 
				  TENDER_SUBMIT_DT  = rset.getString(31)==null?"":rset.getString(31);
				  TENDER_CLOSING_DT = rset.getString(32)==null?"":rset.getString(32);
				  REMARK = rset.getString(33)==null?"":rset.getString(33);
				  TENDER_BUYER_NOM_CLAUSE = rset.getString(34)==null?"":rset.getString(34);
				  TENDER_SELLER_NOM_CLAUSE = rset.getString(35)==null?"":rset.getString(35);
				  TENDER_LC_CLAUSE = rset.getString(36)==null?"":rset.getString(36);
				  TENDER_BILLING_CLAUSE = rset.getString(37)==null?"":rset.getString(37);
				  TENDER_LIABILITY_CLAUSE = rset.getString(38)==null?"":rset.getString(38);
				  ADVANCE_ADJUST_CLAUSE = rset.getString(39)==null?"N":rset.getString(39);
				  ADJUST_FLAG = rset.getString(40)==null?"N":rset.getString(40);
				  REOPEN_REQUEST_FLAG = rset.getString(41)==null?"":rset.getString(41);
				  REOPEN_APPROVAL_FLAG = rset.getString(42)==null?"":rset.getString(42);
				  
				//Fetch Whether Contract Ended...
				  queryString = "select sysdate - to_date('"+END_DT+"','dd/mm/yyyy') from dual";
				  rset = stmt.executeQuery(queryString);
				  if(rset.next()) {
					  int data = rset.getInt(1);
					  if(data>0) {
						  contractEndFlag = true;
					  }
				  }
				 
			}
			//  Fetch Buyer Name and Abbr
			queryString ="Select CUSTOMER_NAME, CUSTOMER_ABBR from FMS7_CUstomer_MST where CUSTOMER_CD='"+bscode+"' and FLAG='T'";
			//System.out.println("fetch Tender_TRANSPORTER_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()){
				  bName = rset.getString(1);
				  bAbbr = rset.getString(2);
			}
			//	FMS7_Tender_TRANSPORTER_MST
			queryString ="Select TRANSPORTER_CD from DLNG_Tender_TRANSPORTER_MST where CUSTOMER_CD='"+bscode+"' and  Tender_NO='"+tender_cd+"' and FLAG='Y' ";
			//System.out.println("fetch Tender_TRANSPORTER_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vTRANSPORTER_CD.add(rset.getString(1));
			}
			//	FMS7_Tender_PLANT_MST
			queryString ="Select PLANT_SEQ_NO from DLNG_Tender_PLANT_MST where CUSTOMER_CD='"+bscode+"' and  Tender_NO='"+tender_cd+"' and FLAG='Y'";
    		//System.out.println("fetch Tender_PLANT_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vPLANT_SEQ_NO.add(rset.getString(1));
			}
			//	FMS7_Tender_CLAUSE_MST
			queryString ="Select CLAUSE_CD, REMARK from DLNG_Tender_CLAUSE_MST where CUSTOMER_CD='"+bscode+"' and  Tender_NO='"+tender_cd+"' ";
			//System.out.println("fetch Tender_CLAUSE_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vCLAUSE_CD.add(rset.getString(1));
				  vREMARK.add(rset.getString(1));
			}
			// Fetch Delivery point For Buyer
			int count = 0;
			Vector plant_seq = new Vector();
			Vector plant_nm = new Vector();
			Vector plant_type = new Vector();
			Vector temp_plant_seq = new Vector();
			Vector temp_plant_nm = new Vector();
			Vector temp_plant_type = new Vector();
			
			temp_plant_nm.add("");
			temp_plant_type.add("");
			temp_plant_seq.add("0");
			
			queryString = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
			  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.flag='T' " +
			  			  "AND A.customer_cd="+bscode+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
			  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd="+bscode+" AND " +
			  			  "B.seq_no=A.seq_no AND B.eff_dt<=TO_DATE('"+SIGNING_DT+"','DD/MM/YYYY')) " +
			  			  "ORDER BY A.seq_no";
			//System.out.println("Buyer Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					plant_seq.add(rset.getString(1));
					plant_type.add(rset.getString(2));
					plant_nm.add(rset.getString(3));
					++count;
				}
			}
			if(count==0)
			{
				buyer_plant_seq_no.add(temp_plant_seq);
				buyer_plant_type.add(temp_plant_type);
				buyer_plant_nm.add(temp_plant_nm);
			}
			else
			{
				buyer_plant_seq_no.add(plant_seq);
				buyer_plant_type.add(plant_type);
				buyer_plant_nm.add(plant_nm);
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchTenderData() --> "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void fetchTender2() 
	{	
		try 
		{			
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString="SELECT A.CUSTOMER_CD,TENDER_NO, CUSTOMER_NAME, CUSTOMER_ABBR " +
						" FROM FMS7_Tender_MST  A, FMS7_CUSTOMER_MST B " +
						" WHERE A.CUSTOMER_CD='"+bscode+"' AND A.CUSTOMER_CD=B.CUSTOMER_CD " +
						" ORDER BY CUSTOMER_NAME,TENDER_NO";
			}
			else
			{
				queryString="SELECT A.CUSTOMER_CD,TENDER_NO, CUSTOMER_NAME, CUSTOMER_ABBR " +
						" FROM FMS7_Tender_MST A, FMS7_CUSTOMER_MST B " +
						" WHERE A.CUSTOMER_CD=B.CUSTOMER_CD " +
						" ORDER BY CUSTOMER_NAME,TENDER_NO";
			}
			//System.out.println("TENDER:QRY:SELECT:T001: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(1).equals(" ") && !rset.getString(2).equals(" "))
				{
					buyer_cd.add(rset.getString(1));
					TENDER_NO.add(rset.getString(2));
					buyer_nm.add(rset.getString(3));
					buyer_abbr.add(rset.getString(4));
				}
			}
			
			for(int i=0;i<TENDER_NO.size();i++)
			{
				queryString = "SELECT CUSTOMER_CD, TENDER_NO, to_char(TENDER_SUBMIT_DT,'dd/mm/yyyy'), " +
							  "to_char(TENDER_CLOSING_DT,'dd/mm/yyyy'), TENDER_BASE, " +
							  "STATUS, to_char(START_DT,'dd/mm/yyyy'), to_char(END_DT,'dd/mm/yyyy'),REMARKS " +
							  "FROM FMS7_TENDER_MST where Customer_cd='"+buyer_cd.elementAt(i)+"' and TENDER_NO='"+TENDER_NO.elementAt(i)+"' ORDER BY customer_cd ,TENDER_NO";
			//	System.out.println("TENDER:QRY:SELECT:T002: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vSTART_DT.add(rset.getString(3)==null?"":rset.getString(3));
					vEND_DT.add(rset.getString(4)==null?"":rset.getString(4));
					vTENDER_BASE.add((rset.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"));
					vSTATUS.add((rset.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"));
					vSTART_DT2.add(rset.getString(7)==null?"":rset.getString(7));
					vEND_DT2.add(rset.getString(8)==null?"":rset.getString(8));					
				}
				else
				{
					vSTART_DT.add("");
					vEND_DT.add("");
					vTENDER_BASE.add("Ex-Terminal");
					vSTATUS.add("Inactive");
					vSTART_DT2.add("");
					vEND_DT2.add("");
				}
			}			
/////////////////
			queryString ="SELECT CUSTOMER_CD, customer_name, CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST " +
			" WHERE CUSTOMER_CD NOT IN (SELECT DISTINCT customer_cd FROM FMS7_TENDER_MST) " +
			" ORDER BY customer_name";
			//System.out.println("TENDERQRY:SELECT:T003: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				buyer_cd.add(rset.getString(1));
				buyer_nm.add(rset.getString(2));
				buyer_abbr.add(rset.getString(3));
				TENDER_NO.add("");
				
				vSTART_DT.add("");
				vEND_DT.add("");
				vTENDER_BASE.add("");
				vSTATUS.add("");
				vSTART_DT2.add("");
				vEND_DT2.add("");
			}
			int Start_days_diff=0; int end_days_diff=0;
			for(int i=0;i<buyer_cd.size();i++)
			{
				if(!vSTART_DT.elementAt(i).equals("")) 
				{
					queryString = "SELECT TO_DATE('"+vSTART_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";				
				//	System.out.println("COUNT-Days Difference: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						Start_days_diff = rset.getInt(1);
						if(Start_days_diff<0 )
							VLineFlagStart.add("RED");
						if(Start_days_diff>0 )
							VLineFlagStart.add("GREEN");
						if(Start_days_diff==0 )
							VLineFlagStart.add("BLUE");
					}
					queryString = "SELECT TO_DATE('"+vEND_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";					
				//	System.out.println("COUNT-Days Difference: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						end_days_diff = rset.getInt(1);
						if(end_days_diff<0)
							VLineFlagEnd.add("RED");
						if(end_days_diff>0)
							VLineFlagEnd.add("GREEN");
						if(end_days_diff==0)
							VLineFlagEnd.add("BLUE");					
					}					
				}
				else
				{
					VLineFlagEnd.add("BLACK");
					VLineFlagStart.add("BLACK");
				}
				Start_days_diff=0; end_days_diff=0;
			}
	
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchAllClauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetchTender() 
	{	
		try 
		{			
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString="SELECT distinct CUSTOMER_CD,TENDER_NO FROM "
						+ "DLNG_Tender_MST WHERE CUSTOMER_CD='"+bscode+"' "
						+ "ORDER BY customer_cd,TENDER_NO";
			}
			else
			{
				/*
				 * Hiren_20210226
				 * if(Customer_access_flag.equals("Y"))
				{
					queryString="SELECT distinct A.CUSTOMER_CD,TENDER_NO FROM DLNG_Tender_MST A,SEC_EMP_CUSTOMER_ALLOC_MST B "
							+ "WHERE B.CUSTOMER_CD=A.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' "
							+ "ORDER BY A.customer_cd,TENDER_NO";
				} else {
					queryString="SELECT distinct CUSTOMER_CD,TENDER_NO FROM DLNG_Tender_MST A "
							+ "ORDER BY customer_cd,TENDER_NO";
				}*/
				
				queryString="SELECT distinct CUSTOMER_CD,TENDER_NO FROM DLNG_Tender_MST A "
						+ "ORDER BY customer_cd,TENDER_NO";
			}
			System.out.println("DLNG-TENDER Distinct Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(1).equals(" ") && !rset.getString(2).equals(" "))
				{
					buyer_cd.add(rset.getString(1));
					TENDER_NO.add(rset.getString(2));
				}
			}
			
			for(int i=0;i<TENDER_NO.size();i++)
			{
				queryString = "SELECT CUSTOMER_CD, TENDER_NO, to_char(TENDER_SUBMIT_DT,'dd/mm/yyyy'), " +
							  "to_char(TENDER_CLOSING_DT,'dd/mm/yyyy'), TENDER_BASE, " +
							  "STATUS, to_char(START_DT,'dd/mm/yyyy'), to_char(END_DT,'dd/mm/yyyy'),REMARKS " +
							  "FROM DLNG_TENDER_MST where Customer_cd='"+buyer_cd.elementAt(i)+"' and TENDER_NO='"+TENDER_NO.elementAt(i)+"' ORDER BY customer_cd ,TENDER_NO";
				System.out.println("TENDER Fetch DATA Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vSTART_DT.add(rset.getString(3)==null?"":rset.getString(3));
					vEND_DT.add(rset.getString(4)==null?"":rset.getString(4));
					vTENDER_BASE.add((rset.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"));
					//vSTATUS.add((rset.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"));
					vSTART_DT2.add(rset.getString(7)==null?"":rset.getString(7));
					vEND_DT2.add(rset.getString(8)==null?"":rset.getString(8));					
					
					//Fetch Whether Contract Ended...
					  queryString1 = "select sysdate - to_date('"+rset.getString(4)+"','dd/mm/yyyy') from dual";
					  System.out.println("TENDER Fetch DATA Query = "+queryString1);
					  rset1 = stmt1.executeQuery(queryString1);
					  if(rset1.next()) {
						  int data = rset1.getInt(1);
						  if(data>0) {
							  vSTATUS.add("Archived");
						  } else {
							  vSTATUS.add((rset.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"));
						  }
					  } else {
						  vSTATUS.add((rset.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"));
					  }
				}
				else
				{
					vSTART_DT.add("");
					vEND_DT.add("");
					vTENDER_BASE.add("Ex-Terminal");
					vSTATUS.add("Inactive");
					vSTART_DT2.add("");
					vEND_DT2.add("");
				}
			}
			
			for(int i=0;i<buyer_cd.size();i++)
			{
				queryString ="Select nvl(Customer_name,'') from FMS7_Customer_mst where Customer_cd='"+buyer_cd.elementAt(i)+"'";
				//System.out.println("Tender Buyer Name Fetch Query  = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					buyer_nm.add(rset.getString(1));
				}
			}
	
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchAllClauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetchFGSA2() 
	{
		try 
		{
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString="SELECT DISTINCT customer_cd,fgsa_no FROM FMS7_FGSA_MST Where customer_cd='"+bscode+"' ORDER BY customer_cd,fgsa_no";
			}
			else
			{
				queryString="SELECT DISTINCT A.CUSTOMER_CD, fgsa_no, customer_name, CUSTOMER_ABBR " +
						" FROM FMS7_FGSA_MST A, FMS7_CUSTOMER_MST B " +
						" WHERE A.CUSTOMER_CD=B.CUSTOMER_CD " +
						//" ORDER BY customer_cd,fgsa_no";
						" ORDER BY customer_name,fgsa_no ";
			}
			//System.out.println("FGSA-LIST:QRY-FG001: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{				
				buyer_cd.add(rset.getString(1));
				FGSA_no.add(rset.getString(2));	
				buyer_nm.add(rset.getString(3));
				buyer_abbr.add(rset.getString(4));
				queryString1 = "SELECT MAX(rev_no) FROM FMS7_FGSA_MST WHERE " +
							   "customer_cd="+rset.getString(1)+" AND fgsa_no="+rset.getString(2)+"";
				//System.out.println("FGSA-LIST:QRY-FG001A: "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					vRev_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				else
				{
					vRev_No.add("0");
				}				
			}			
			for(int i=0;i<FGSA_no.size();i++)
			{
				queryString = "SELECT customer_cd, fgsa_no, TO_CHAR(start_dt,'dd/mm/yyyy'), " +
							  "TO_CHAR(end_dt,'dd/mm/yyyy'), NVL(fgsa_base,'X'), " +
							  "NVL(fgsa_type,'T'), NVL(status,'Y'), TO_CHAR(rev_dt,'dd/mm/yyyy') " +
							  "FROM FMS7_FGSA_MST WHERE customer_cd="+buyer_cd.elementAt(i)+" AND " +
							  "fgsa_no="+FGSA_no.elementAt(i)+" AND rev_no="+vRev_No.elementAt(i)+"";
				//System.out.println("FGSA-LIST:QRY-FG002: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vSTART_DT.add(rset.getString(3)==null?"":rset.getString(3));
					vEND_DT.add(rset.getString(4)==null?"":rset.getString(4));
					vFGSA_BASE.add((rset.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"));
					vFGSA_TYPE.add((rset.getString(6).equalsIgnoreCase("T")?"Term":"Spot"));
					vSTATUS.add((rset.getString(7).equalsIgnoreCase("Y")?"Active":"Inactive"));
					vREV_DT.add(rset.getString(8)==null?"":rset.getString(8));
				}
				else
				{
					vSTART_DT.add("");
					vEND_DT.add("");
					vFGSA_BASE.add("Ex-Terminal");
					vFGSA_TYPE.add("Term");
					vSTATUS.add("Active");
					vREV_DT.add("");
				}
			}	
			
				queryString ="SELECT CUSTOMER_CD, customer_name, CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST " +
						" WHERE CUSTOMER_CD NOT IN (SELECT DISTINCT customer_cd FROM FMS7_FGSA_MST) " +
						" ORDER BY customer_name";
				//System.out.println("FGSA-LIST:QRY-FG003: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					buyer_cd.add(rset.getString(1));
					buyer_nm.add(rset.getString(2));
					buyer_abbr.add(rset.getString(3));
					FGSA_no.add("");	
					vRev_No.add("");
					vSTART_DT.add("");
					vEND_DT.add("");
					vFGSA_BASE.add("");
					vFGSA_TYPE.add("");
					vSTATUS.add("");
					vREV_DT.add("");
				}
				/////////////////
				int Start_days_diff=0; int end_days_diff=0;
				for(int i=0;i<buyer_cd.size();i++)
				{
					if(!vSTART_DT.elementAt(i).equals("")) 
					{
					queryString = "SELECT TO_DATE('"+vSTART_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
				
				//	System.out.println("COUNT-Days Difference: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						Start_days_diff = rset.getInt(1);
						if(Start_days_diff<0 )
							VLineFlagStart.add("RED");
						if(Start_days_diff>0 )
							VLineFlagStart.add("GREEN");
						if(Start_days_diff==0 )
							VLineFlagStart.add("BLUE");
					}
					queryString = "SELECT TO_DATE('"+vEND_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
					
				//	System.out.println("COUNT-Days Difference: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						end_days_diff = rset.getInt(1);
						if(end_days_diff<0)
							VLineFlagEnd.add("RED");
						if(end_days_diff>0)
							VLineFlagEnd.add("GREEN");
						if(end_days_diff==0)
							VLineFlagEnd.add("BLUE");
						
					}
					
					}
					else
					{
						VLineFlagEnd.add("BLACK");
						VLineFlagStart.add("BLACK");
					}
					Start_days_diff=0; end_days_diff=0;
				}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION In DataBean_Contract_Master DataBean --> Under fetchFGSA() Method :\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetchFLSA() 
	{
		try 
		{
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
			//SB20200630	queryString="SELECT DISTINCT customer_cd,fgsa_no FROM FMS7_FGSA_MST Where customer_cd='"+bscode+"' ORDER BY customer_cd,fgsa_no";
				queryString="SELECT DISTINCT customer_cd,flsa_no FROM DLNG_FLSA_MST Where customer_cd='"+bscode+"' ORDER BY customer_cd,flsa_no";
			}
			else
			{
				/*
				 * by Hiren_20210226
				 * if(Customer_access_flag.equals("Y"))
				{
					queryString="SELECT DISTINCT A.customer_cd,fgsa_no "
							+ "FROM DLNG_FLSA_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B WHERE "
							+ "A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' "
							+ "ORDER BY A.customer_cd,fgsa_no";
				} else {
				//Sb2020060	queryString="SELECT DISTINCT customer_cd,fgsa_no FROM FMS7_FGSA_MST "
					//		+ "ORDER BY customer_cd,fgsa_no";
					queryString="SELECT DISTINCT customer_cd,fgsa_no FROM DLNG_FLSA_MST "
							+ "ORDER BY customer_cd,flsa_no";
				}*/
				queryString="SELECT DISTINCT customer_cd,flsa_no FROM DLNG_FLSA_MST "
						+ "ORDER BY customer_cd,flsa_no";
			}
//			System.out.println("queryString------"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{				
				buyer_cd.add(rset.getString(1));
				FGSA_no.add(rset.getString(2));			
				//Sb2020060		queryString1 = "SELECT MAX(rev_no) FROM FMS7_FGSA_MST WHERE " +
					//		   "customer_cd="+rset.getString(1)+" AND fgsa_no="+rset.getString(2)+"";
				queryString1 = "SELECT MAX(rev_no) FROM DLNG_FLSA_MST WHERE " +
						   "customer_cd="+rset.getString(1)+" AND flsa_no="+rset.getString(2)+"";
//				System.out.println("FGSA REV NO Query = "+queryString);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					vRev_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				else
				{
					vRev_No.add("0");
				}				
			}			
			for(int i=0;i<FGSA_no.size();i++)
			{
			/*SB20200630	queryString = "SELECT customer_cd, fgsa_no, TO_CHAR(start_dt,'dd/mm/yyyy'), " +
							  "TO_CHAR(end_dt,'dd/mm/yyyy'), NVL(fgsa_base,'X'), " +
							  "NVL(fgsa_type,'T'), NVL(status,'Y'), TO_CHAR(rev_dt,'dd/mm/yyyy') " +
							  "FROM FMS7_FGSA_MST WHERE customer_cd="+buyer_cd.elementAt(i)+" AND " +
							  "fgsa_no="+FGSA_no.elementAt(i)+" AND rev_no="+vRev_No.elementAt(i)+"";
							  */
				queryString = "SELECT customer_cd, flsa_no, TO_CHAR(start_dt,'dd/mm/yyyy'), " +
						  "TO_CHAR(end_dt,'dd/mm/yyyy'), NVL(flsa_base,'X'), " +
						  "NVL(flsa_type,'T'), NVL(status,'Y'), TO_CHAR(rev_dt,'dd/mm/yyyy') " +
						  "FROM DLNG_FLSA_MST WHERE customer_cd="+buyer_cd.elementAt(i)+" AND " +
						  "flsa_no="+FGSA_no.elementAt(i)+" AND rev_no="+vRev_No.elementAt(i)+"";
				System.out.println("FLSA Fetch DATA Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vSTART_DT.add(rset.getString(3)==null?"":rset.getString(3));
					vEND_DT.add(rset.getString(4)==null?"":rset.getString(4));
					vFGSA_BASE.add((rset.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"));
					vFGSA_TYPE.add((rset.getString(6).equalsIgnoreCase("T")?"Term":"Spot"));
					//vSTATUS.add((rset.getString(7).equalsIgnoreCase("Y")?"Active":"Inactive"));
					vREV_DT.add(rset.getString(8)==null?"":rset.getString(8));
					
					//Fetch Whether Contract Ended...
					  queryString = "select sysdate - to_date('"+rset.getString(4)+"','dd/mm/yyyy') from dual";
					  rset1 = stmt1.executeQuery(queryString);
					  if(rset1.next()) {
						  int data = rset1.getInt(1);
						  if(data>0) {
							  vSTATUS.add("Archived");
						  } else {
							  vSTATUS.add((rset.getString(7).equalsIgnoreCase("Y")?"Active":"Inactive"));
						  }
					  } else {
						  vSTATUS.add((rset.getString(7).equalsIgnoreCase("Y")?"Active":"Inactive"));
					  }
				}
				else
				{
					vSTART_DT.add("");
					vEND_DT.add("");
					vFGSA_BASE.add("Ex-Terminal");
					vFGSA_TYPE.add("Term");
					vSTATUS.add("Active");
					vREV_DT.add("");
				}
			}	
			for(int i=0;i<buyer_cd.size();i++)
			{
				queryString ="SELECT customer_name FROM FMS7_CUSTOMER_MST WHERE customer_cd='"+buyer_cd.elementAt(i)+"'";
				//System.out.println("FGSA Buyer Name Fetch Query  = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					buyer_nm.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					buyer_nm.add("");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION In DataBean_Contract_Master DataBean --> Under fetchFLSA() Method :\n"+e.getMessage());
			e.printStackTrace();
		}
	}

	public void fetch_LC_SEQ_NO_REGAS_ListOLD()
	{

		try
		{		
			String t_LC_REGAS_NO_REGAS2 =  "";
			String t_LC_CONT_TYPE_REGAS2 =  "";
			String t_LC_REGAS_REV_NO_REGAS2 =  "";
			String t_LC_STR_DT_REGAS2 =  "";
			String t_LC_END_DT_REGAS2 =  "";
			String t_LC_REGAS_DURATION_REGAS2 =  "";
			String t_LC_DCQ_REGAS2 =  "";
			String t_LC_TCQ_REGAS2 =  "";
			String t_LC_EXCHG_RATE_REGAS2 =  "";
			String t_LC_CUST_CD_DTL =  "";
			
			queryString = "SELECT FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, " +
						  "CREDIT_RATING, TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'), TO_CHAR(LC_REF_DATE,'DD/MM/YYYY'), " +
						  "TO_CHAR(START_DATE,'DD/MM/YYYY'), TO_CHAR(END_DATE,'DD/MM/YYYY'), " +
						  "MANUAL_EXCHG_RATE, " +
						  "USER_DEFINED_DCQ, CALC_LC_AMOUNT, FINAL_LC_AMOUNT, REMARKS, FLAG  " +
						  "FROM FMS7_LC_MST WHERE (START_DATE BETWEEN " +
						  "TO_DATE('"+lc_from_dt_2+"','DD/MM/YYYY') AND TO_DATE('"+lc_to_dt_2+"','DD/MM/YYYY') or END_DATE BETWEEN " +
						  "TO_DATE('"+lc_from_dt_2+"','DD/MM/YYYY') AND TO_DATE('"+lc_to_dt_2+"','DD/MM/YYYY'))" +
						  "AND CUSTOMER_CD="+Buyer_cd+" AND FLAG = 'R' ORDER BY FINANCIAL_YEAR,LC_SEQ_NO" ;

			//System.out.println("Query for fetching Master Vector LC List = "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				t_LC_REGAS_NO_REGAS2 = "";
				t_LC_CONT_TYPE_REGAS2 = "";
				t_LC_REGAS_REV_NO_REGAS2 = "";
				t_LC_STR_DT_REGAS2 = "";
				t_LC_END_DT_REGAS2 = "";
				t_LC_REGAS_DURATION_REGAS2 = "";
				t_LC_DCQ_REGAS2 = "";
				t_LC_TCQ_REGAS2 = "";
				t_LC_EXCHG_RATE_REGAS2 = "";
				
				int lc_seq_no = rset.getInt(2);
				String fin_year = rset.getString(1);
				String LC_SEQ_NO = "";
				
				if(lc_seq_no<10)
				{
					LC_SEQ_NO = "000"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<100)
				{
					LC_SEQ_NO = "00"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<1000)
				{
					LC_SEQ_NO = "0"+lc_seq_no+"/"+fin_year;
				}
				else
				{
					LC_SEQ_NO = ""+lc_seq_no+"/"+fin_year;
				}
				LC_FIN_YEAR_REGAS2.add(rset.getString(1)==null?"":rset.getString(1));
				LC_SEQ_NO_REGAS2.add(LC_SEQ_NO);
				LC_CUSTOMER_CD_REGAS2.add(rset.getString(3)==null?"0":rset.getString(3));
				LC_CREDIT_RATING_REGAS2.add(rset.getString(4)==null?"0":rset.getString(4));
				LC_CREDIT_RATING_EFF_DT_REGAS2.add(rset.getString(5)==null?"":rset.getString(5));
				LC_LC_REF_DT_REGAS2.add(rset.getString(6)==null?"":rset.getString(6));
				LC_STR_DT_REGAS2.add(rset.getString(7)==null?"":rset.getString(7));
				LC_END_DT_REGAS2.add(rset.getString(8)==null?"":rset.getString(8));
				LC_MANUAL_EXCHG_RATE_REGAS2.add(rset.getString(9)==null?"0":rset.getString(9));
				
				LC_USER_DEFINED_DCQ_REGAS2.add(rset.getString(10)==null?"0":rset.getString(10));
				LC_CALC_AMT_REGAS2.add(rset.getString(11)==null?"0":rset.getString(11));
				LC_FINAL_AMT_REGAS2.add(rset.getString(12)==null?"0":rset.getString(12));
				LC_REMARKS_REGAS2.add(rset.getString(13)==null?"0":rset.getString(13));
				LC_FLAG_REGAS2.add(rset.getString(14)==null?"0":rset.getString(14));
				
				
				queryString1 = "SELECT SN_NO, CONT_TYPE, SN_REV_NO, " +
					   "TO_CHAR(SN_START_DATE,'DD/MM/YYYY'), TO_CHAR(SN_END_DATE,'DD/MM/YYYY'), " +
					   "SN_DURATION, DCQ, TCQ, EXCHG_RATE, CUSTOMER_CD " +
					   "FROM FMS7_LC_DTL WHERE FINANCIAL_YEAR='"+rset.getString(1)+"' " +
					   "AND LC_SEQ_NO="+rset.getString(2)+" AND CUSTOMER_CD="+rset.getString(3)+" " +
					   " AND CONT_TYPE = 'R' AND FLAG = 'R'" +
					   "ORDER BY SN_END_DATE";
				
					//System.out.println("Query for fetching Detailed LC Info = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while (rset1.next())
					{
						t_LC_REGAS_NO_REGAS2 += rset1.getString(1)==null?"0":rset1.getString(1) + "~~";
						t_LC_CONT_TYPE_REGAS2 += rset1.getString(2)==null?"0":rset1.getString(2) + "~~";
						t_LC_REGAS_REV_NO_REGAS2 += rset1.getString(3)==null?"0":rset1.getString(3) + "~~";
						t_LC_STR_DT_REGAS2 += rset1.getString(4)==null?"":rset1.getString(4) + "~~";
						t_LC_END_DT_REGAS2 += rset1.getString(5)==null?"":rset1.getString(5) + "~~";
						t_LC_REGAS_DURATION_REGAS2 += rset1.getString(6)==null?"0":rset1.getString(6) + "~~";
						t_LC_DCQ_REGAS2 += rset1.getString(7)==null?"0":rset1.getString(7) + "~~";
						t_LC_TCQ_REGAS2 += rset1.getString(8)==null?"0":rset1.getString(8) + "~~";
						t_LC_EXCHG_RATE_REGAS2 += rset1.getString(9)==null?"0":rset1.getString(9) + "~~";
						t_LC_CUST_CD_DTL += rset1.getString(10)==null?"0":rset1.getString(10) + "~~";
						
						
					}
					LC_REGAS_NO_REGAS2.add(t_LC_REGAS_NO_REGAS2);
					LC_CONT_TYPE_REGAS2.add(t_LC_CONT_TYPE_REGAS2);
					LC_REGAS_REV_NO_REGAS2.add(t_LC_REGAS_REV_NO_REGAS2);
					LC_REGAS_END_DT_REGAS2.add(t_LC_END_DT_REGAS2);
					LC_REGAS_STR_DT_REGAS2.add(t_LC_STR_DT_REGAS2);
					LC_REGAS_DURATION_REGAS2.add(t_LC_REGAS_DURATION_REGAS2);
					LC_DCQ_REGAS2.add(t_LC_DCQ_REGAS2);
					LC_TCQ_REGAS2.add(t_LC_TCQ_REGAS2);
					LC_EXCHG_RATE_REGAS2.add(t_LC_EXCHG_RATE_REGAS2); 
					LC_CUST_CD_DTL.add(t_LC_CUST_CD_DTL);
			}		
			
			//System.out.println(LC_SEQ_NO_REGAS2);
			//System.out.println(LC_REGAS_NO_REGAS2);
			//System.out.println(LC_REGAS_STR_DT_REGAS2);
			//System.out.println(LC_FINAL_AMT_REGAS2);
			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_SEQ_NO_List() Method:\n"+e.getMessage());
			e.printStackTrace();
		}		
	
	}
	
	public void fetch_LC_SEQ_NO_List()
	{
		try
		{		
			queryString = "SELECT FINANCIAL_YEAR, LC_SEQ_NO, CUSTOMER_CD, " +
						  "CREDIT_RATING, TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'), TO_CHAR(LC_REF_DATE,'DD/MM/YYYY'), " +
						  "TO_CHAR(START_DATE,'DD/MM/YYYY'), TO_CHAR(END_DATE,'DD/MM/YYYY'), " +
						  "MANUAL_EXCHG_FLAG, MANUAL_EXCHG_RATE, USER_DEFINED_FLAG, " +
						  "USER_DEFINED_DCQ, CALC_LC_AMOUNT, FINAL_LC_AMOUNT, REMARKS " +
						  "FROM FMS7_LC_MST WHERE START_DATE BETWEEN " +
						  "TO_DATE('"+lc_from_dt_2+"','DD/MM/YYYY') AND TO_DATE('"+lc_to_dt_2+"','DD/MM/YYYY') " +
						  "AND CUSTOMER_CD="+Buyer_cd+" AND FLAG = 'Y' ORDER BY FINANCIAL_YEAR,LC_SEQ_NO" ;
			
			System.out.println("Query for fetching Master LC List = "+queryString);
			rset = stmt.executeQuery(queryString);		
			
			while(rset.next())
			{
				int lc_seq_no = rset.getInt(2);
				String fin_year = rset.getString(1);
				String LC_SEQ_NO = "";
				
				if(lc_seq_no<10)
				{
					LC_SEQ_NO = "000"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<100)
				{
					LC_SEQ_NO = "00"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<1000)
				{
					LC_SEQ_NO = "0"+lc_seq_no+"/"+fin_year;
				}
				else
				{
					LC_SEQ_NO = ""+lc_seq_no+"/"+fin_year;
				}
				
				vLC_SEQ_NO.add(LC_SEQ_NO);
				vStatus.add("N");
				vCREDIT_RATING.add(rset.getString(4)==null?"0":rset.getString(4));
				vCREDIT_RATE_EFF_DT.add(rset.getString(5)==null?"":rset.getString(5));
				vLC_REF_DT.add(rset.getString(6)==null?"":rset.getString(6));
				vLC_START_DT.add(rset.getString(7)==null?"":rset.getString(7));
				vLC_END_DT.add(rset.getString(8)==null?"":rset.getString(8));
				vMANUAL_EXCHG_RATE_FLAG.add(rset.getString(9)==null?"N":rset.getString(9));
				vMANUAL_EXCHG_RATE.add(rset.getString(10)==null?"":rset.getString(10));
				vUSER_DEFINED_FLAG.add(rset.getString(11)==null?"N":rset.getString(11));
				//vTOT_CONT_CAP.add(rset.getString(12)==null?"":rset.getString(12));
				vUSER_DEFINED_DCQ.add(rset.getString(12)==null?"":rset.getString(12));
				vLC_AMT.add(rset.getString(13)==null?"":rset.getString(13));
				vLC_FINAL_AMT.add(rset.getString(14)==null?"":rset.getString(14));
				vLC_REMARKS.add(rset.getString(15)==null?"":rset.getString(15));
				
				String bscode = "";
				String FGSA_No = "";
				String Rev_No = "";
				String customer = "";
				String snNo = "";
				String snRefNo = "";
				String snRev = "";
				String tcq = "";
				String dcq = "";
				String datediff = "";
				String rate = "";
				String START_DT = "";
				String END_DT = "";
				String tax_type = "";
				String cont_type = "";
				String lc_exchg_rate = "";
				String lc_base_remark = "";
				String flag_lc_value = "";
				String flag_dcq_tcq = "";
				String dcqdays_tcqpercent_value = "";
				String tax_rate = "";
				
				queryString1 = "SELECT FGSA_NO, SN_NO, CONT_TYPE, FGSA_REV_NO, SN_REV_NO, " +
							   "TO_CHAR(SN_START_DATE,'DD/MM/YYYY'), TO_CHAR(SN_END_DATE,'DD/MM/YYYY'), " +
							   "SN_DURATION, DCQ, TCQ, SALES_RATE, EXCHG_RATE, " +
							   "TAX_PERCENTAGE, LC_METHOD_REMARK, LC_METHOD, LC_BASE, " +
							   "DCQ_DAYS_TCQ_PERCENTAGE " +
							   "FROM FMS7_LC_DTL WHERE FINANCIAL_YEAR='"+rset.getString(1)+"' " +
							   "AND LC_SEQ_NO="+rset.getString(2)+" AND CUSTOMER_CD="+rset.getString(3)+" " +
			//MD20111031	   
							   " AND (CONT_TYPE = 'S' OR CONT_TYPE = 'L') AND FLAG = 'Y'" +
							   "ORDER BY SN_END_DATE";
				
				System.out.println("Query for fetching Detailed LC Info = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				while(rset1.next())
				{
					if(rset1.getString(3).equalsIgnoreCase("S"))
					{
						Vector plant_seq_no = new Vector();
						Vector tax_structure_cd = new Vector();
						Vector tax_structure_date = new Vector();
						Vector tax_structure_dtl = new Vector();
						Vector tax_rate_tmp = new Vector();
						
						queryString2 = "SELECT PLANT_SEQ_NO FROM FMS7_SN_PLANT_MST WHERE " +
									   "SN_NO="+rset1.getString(2)+" AND SN_REV_NO="+rset1.getString(5)+" AND " +
									   "CUSTOMER_CD="+rset.getString(3)+" AND FGSA_NO="+rset1.getString(1)+" AND " +
									   "FGSA_REV_NO="+rset1.getString(4)+"";
						//System.out.println("Query For Fetching Tax Structure CD "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							plant_seq_no.add(rset2.getString(1)==null?"0":rset2.getString(1));
						}
						
						for(int i=0; i<plant_seq_no.size(); i++)
						{
							queryString2 = "SELECT NVL(A.TAX_STRUCT_CD,'0'), TO_CHAR(A.TAX_STRUCT_DT,'DD/MM/YYYY'), " +
									       "A.TAX_STRUCT_DTL FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
							   			   "A.CUSTOMER_CD="+rset.getString(3)+" AND " +
							   			   "A.PLANT_SEQ_NO="+plant_seq_no.elementAt(i)+" AND " +
							   			   "A.TAX_STRUCT_DT=(SELECT MAX(B.TAX_STRUCT_DT) " +
							   			   "FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
							   			   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.PLANT_SEQ_NO AND " +
							   			   "A.TAX_STRUCT_DT<=TO_DATE('"+rset1.getString(7)+"','DD/MM/YYYY'))";
							//System.out.println("Query For Fetching Tax Structure CD = "+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								tax_structure_cd.add(rset2.getString(1)==null?"0":rset2.getString(1));
								tax_structure_date.add(rset2.getString(2)==null?"":rset2.getString(2));
								tax_structure_dtl.add(rset2.getString(3)==null?"":rset2.getString(3));							
								double tax_rate_sum = 0;
								String tax_factor = "0.00";
											
								queryString3 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
											  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+rset2.getString(1)+" AND " +
											  "A.app_date=TO_DATE('"+rset2.getString(2)+"','DD/MM/YYYY') ORDER BY A.tax_code";
								//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString3);
								rset3=stmt3.executeQuery(queryString3);
								while(rset3.next())
								{
									tax_factor = rset3.getString(2);								
									if(rset3.getString(3).equals("1"))
									{
										tax_rate_sum += Double.parseDouble(tax_factor);
									}
									else if(rset3.getString(3).equals("2"))
									{
										queryString4 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
													   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+rset2.getString(1)+" AND " +
													   "A.app_date=TO_DATE('"+rset2.getString(2)+"','DD/MM/YYYY') AND A.tax_code="+rset3.getString(4)+"";
										//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString4);
								 		rset4=stmt4.executeQuery(queryString4);
								 		if(rset4.next())
								 		{
								 			tax_rate_sum += (Double.parseDouble(rset4.getString(2))*Double.parseDouble(tax_factor))/100;
								 		}							 		
									}								
								}							
								tax_rate_tmp.add(nf.format(tax_rate_sum));
							}
						}					
						double max_tmp_rate = 0;
						String tmp_tax_dtl = "";					
						for(int i=0; i<tax_structure_cd.size(); i++)
						{
							if(max_tmp_rate<=Double.parseDouble(""+tax_rate_tmp.elementAt(i)))
							{
								max_tmp_rate = Double.parseDouble(""+tax_rate_tmp.elementAt(i));
								tmp_tax_dtl = ""+tax_structure_dtl.elementAt(i);
							}
						}
						
						tax_type += "VAT"+"~~";
						//tax_type += tmp_tax_dtl+"~~";
						tax_rate += nf.format(max_tmp_rate)+"~~";
					}
					else if(rset1.getString(3).equalsIgnoreCase("L"))
					{
						Vector plant_seq_no = new Vector();
						Vector tax_structure_cd = new Vector();
						Vector tax_structure_date = new Vector();
						Vector tax_structure_dtl = new Vector();
						Vector tax_rate_tmp = new Vector();
						
						queryString2 = "SELECT PLANT_SEQ_NO FROM FMS7_LOA_PLANT_MST WHERE " +
									   "LOA_NO="+rset1.getString(2)+" AND LOA_REV_NO="+rset1.getString(5)+" AND " +
									   "CUSTOMER_CD="+rset.getString(3)+" AND TENDER_NO="+rset1.getString(1)+"";
						//System.out.println("Query For Fetching Tax Structure CD "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						while(rset2.next())
						{
							plant_seq_no.add(rset2.getString(1)==null?"0":rset2.getString(1));
						}
						
						for(int i=0; i<plant_seq_no.size(); i++)
						{
							queryString2 = "SELECT NVL(A.TAX_STRUCT_CD,'0'), TO_CHAR(A.TAX_STRUCT_DT,'DD/MM/YYYY'), " +
									       "A.TAX_STRUCT_DTL FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
							   			   "A.CUSTOMER_CD="+rset.getString(3)+" AND " +
							   			   "A.PLANT_SEQ_NO="+plant_seq_no.elementAt(i)+" AND " +
							   			   "A.TAX_STRUCT_DT=(SELECT MAX(B.TAX_STRUCT_DT) " +
							   			   "FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
							   			   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.PLANT_SEQ_NO AND " +
							   			   "A.TAX_STRUCT_DT<=TO_DATE('"+rset1.getString(7)+"','DD/MM/YYYY'))";
							//System.out.println("Query For Fetching Tax Structure CD = "+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								tax_structure_cd.add(rset2.getString(1)==null?"0":rset2.getString(1));
								tax_structure_date.add(rset2.getString(2)==null?"":rset2.getString(2));
								tax_structure_dtl.add(rset2.getString(3)==null?"":rset2.getString(3));							
								double tax_rate_sum = 0;
								String tax_factor = "0.00";
											
								queryString3 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
											  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+rset2.getString(1)+" AND " +
											  "A.app_date=TO_DATE('"+rset2.getString(2)+"','DD/MM/YYYY') ORDER BY A.tax_code";
								//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString3);
								rset3=stmt3.executeQuery(queryString3);
								while(rset3.next())
								{
									tax_factor = rset3.getString(2);								
									if(rset3.getString(3).equals("1"))
									{
										tax_rate_sum += Double.parseDouble(tax_factor);
									}
									else if(rset3.getString(3).equals("2"))
									{
										queryString4 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
													   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+rset2.getString(1)+" AND " +
													   "A.app_date=TO_DATE('"+rset2.getString(2)+"','DD/MM/YYYY') AND A.tax_code="+rset3.getString(4)+"";
										//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString4);
								 		rset4=stmt4.executeQuery(queryString4);
								 		if(rset4.next())
								 		{
								 			tax_rate_sum += (Double.parseDouble(rset4.getString(2))*Double.parseDouble(tax_factor))/100;
								 		}							 		
									}								
								}							
								tax_rate_tmp.add(nf.format(tax_rate_sum));
							}
						}					
						double max_tmp_rate = 0;
						String tmp_tax_dtl = "";					
						for(int i=0; i<tax_structure_cd.size(); i++)
						{
							if(max_tmp_rate<=Double.parseDouble(""+tax_rate_tmp.elementAt(i)))
							{
								max_tmp_rate = Double.parseDouble(""+tax_rate_tmp.elementAt(i));
								tmp_tax_dtl = ""+tax_structure_dtl.elementAt(i);
							}
						}
						
						tax_type += "VAT"+"~~";
						//tax_type += tmp_tax_dtl+"~~";
						tax_rate += nf.format(max_tmp_rate)+"~~";
					}
					
					cont_type += (rset1.getString(3)==null?"S":rset1.getString(3))+"~~";
																
					if(rset1.getString(3).equalsIgnoreCase("S"))
					{
						queryString2 = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT, "+
									   "C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, A.VERIFY_FLAG, A.APPROVE_FLAG, A.DCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'), " +
									   "TO_CHAR(A.START_DT,'DD/MM/YYYY'), (A.END_DT - A.START_DT)+1, A.SN_REF_NO "+
									   "FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
									   "A.FGSA_NO="+rset1.getString(1)+" AND A.FGSA_REV_NO="+rset1.getString(4)+" AND " +
									   "A.CUSTOMER_CD="+rset.getString(3)+" AND A.STATUS='Y' " +
									   "AND A.SN_NO="+rset1.getString(2)+" AND A.SN_REV_NO="+rset1.getString(5)+" AND "+							
									   "A.CUSTOMER_CD=C.CUSTOMER_CD AND A.QUANTITY_UNIT=D.UNIT_CD";				
						//System.out.println("SN List Query For LC Generation (1) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							END_DT += (rset2.getString(16)==null?"-":rset2.getString(16))+"~~";
							START_DT += (rset2.getString(17)==null?"-":rset2.getString(17))+"~~";
							rate += (rset2.getString(8)==null?"0":rset2.getString(8))+"~~";
							datediff += (rset2.getString(18)==null?"0":rset2.getString(18))+"~~";
							dcq += (rset2.getString(15)==null?"0":rset2.getString(15))+"~~";
							tcq += (rset2.getString(6)==null?"0":rset2.getString(6))+"~~";
							snRev += (rset2.getString(2)==null?"0":rset2.getString(2))+"~~";
							snNo += (rset2.getString(1)==null?"0":rset2.getString(1))+"~~";
							customer += (rset2.getString(3)==null?"0":rset2.getString(3))+"~~";
							Rev_No += (rset2.getString(5)==null?"0":rset2.getString(5))+"~~";
							FGSA_No += (rset2.getString(4)==null?"0":rset2.getString(4))+"~~";
							bscode += (rset2.getString(3)==null?"0":rset2.getString(3))+"~~";
							String tmp_sn_ref = rset2.getString(19)==null?"":rset2.getString(19);
							
							if(tmp_sn_ref.trim().equals(""))
							{
								tmp_sn_ref = rset2.getString(1)==null?"0":rset2.getString(1);
							}
							
							snRefNo += (tmp_sn_ref)+"~~";
								
							queryString3 = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
										   "from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+rset1.getString(1)+" and " +
										   "FGSA_TENDER_REV_NO="+rset1.getString(4)+" and CUSTOMER_CD="+rset.getString(3)+" and " +
										   "CONT_TYPE='S' and SN_LOA_NO="+rset1.getString(2)+" and SN_LOA_REV_NO="+rset1.getString(5)+"";
							//System.out.println("SN/LOA LC Details Select Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);		
							if(rset3.next())
							{		
								lc_exchg_rate += (rset3.getString(1)==null?"-":rset3.getString(1))+"~~";
								flag_lc_value += (rset3.getString(2)==null?"-":rset3.getString(2))+"~~";
								flag_dcq_tcq += (rset3.getString(3)==null?"-":rset3.getString(3))+"~~";
								dcqdays_tcqpercent_value += (rset3.getString(4)==null?"-":rset3.getString(4))+"~~";
								
								if(rset3.getString(2).trim().equalsIgnoreCase("SN"))
								{
									lc_base_remark += "Against SN Value"+"~~";
								}
								else if(rset3.getString(2).trim().equalsIgnoreCase("TAX"))
								{
									lc_base_remark += "Against Tax and Advances"+"~~";
								}
								else if(rset3.getString(2).trim().equalsIgnoreCase("OPEN"))
								{
									lc_base_remark += "Against Open Account"+"~~";
								}
								else
								{
									lc_base_remark += "-"+"~~";
								}
							}
							else
							{
								queryString3 = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
											   "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+rset1.getString(1)+" and " +
											   "FGSA_TENDER_REV_NO="+rset1.getString(4)+" and CUSTOMER_CD="+rset.getString(3)+" and " +
											   "CONT_TYPE='F'";
								
								//System.out.println("(If SN/LOA LC Details is Absent, then ...) FGSA/Tender LC Details Select Query = "+queryString);
								rset3 = stmt3.executeQuery(queryString3);		
								if(rset3.next())
								{		
									lc_exchg_rate += (rset3.getString(1)==null?"-":rset3.getString(1))+"~~";
									flag_lc_value += (rset3.getString(2)==null?"-":rset3.getString(2))+"~~";
									flag_dcq_tcq += (rset3.getString(3)==null?"-":rset3.getString(3))+"~~";
									dcqdays_tcqpercent_value += (rset3.getString(4)==null?"-":rset3.getString(4))+"~~";
									
									if(rset3.getString(2).trim().equalsIgnoreCase("SN"))
									{
										lc_base_remark += "Against SN Value"+"~~";
									}
									else if(rset3.getString(2).trim().equalsIgnoreCase("TAX"))
									{
										lc_base_remark += "Against Tax and Advances"+"~~";
									}
									else if(rset3.getString(2).trim().equalsIgnoreCase("OPEN"))
									{
										lc_base_remark += "Against Open Account"+"~~";
									}
									else
									{
										lc_base_remark += "-"+"~~";
									}
								}
								else
								{
									lc_exchg_rate += "-"+"~~";
									flag_lc_value += "-"+"~~";
									flag_dcq_tcq += "-"+"~~";
									dcqdays_tcqpercent_value += "-"+"~~";
									lc_base_remark += "-"+"~~";
								}
							}
						}
					}
					else if(rset1.getString(3).equalsIgnoreCase("L"))
					{
						queryString2 = "SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD, A.TENDER_NO, A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT, "+
									  "C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, A.VERIFY_FLAG, A.APPROVE_FLAG, A.DCQ, " +
									  "TO_CHAR(A.END_DT,'DD/MM/YYYY'), TO_CHAR(A.START_DT,'DD/MM/YYYY'), (A.END_DT - A.START_DT)+1 , A.LOA_REF_NO "+
									  "from FMS7_LOA_MST A, FMS7_CUSTOMER_MST C , FMS7_UNIT_MST D WHERE A.FLAG='T' " +
									  "AND A.CUSTOMER_CD="+rset.getString(3)+" " +
									  "AND A.STATUS='Y' AND A.TENDER_NO='"+rset1.getString(1)+"' " +
									  "AND A.LOA_REV_NO="+rset1.getString(5)+" AND A.LOA_NO="+rset1.getString(2)+" "+
							          "AND C.CUSTOMER_CD=A.CUSTOMER_CD AND A.QUANTITY_UNIT=D.UNIT_CD";
						
						//System.out.println("LOA List Query For LC Generation (2) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							END_DT += (rset2.getString(15)==null?"-":rset2.getString(15))+"~~";
							START_DT += (rset2.getString(16)==null?"-":rset2.getString(16))+"~~";
							rate += (rset2.getString(7)==null?"0":rset2.getString(7))+"~~";
							datediff += (rset2.getString(17)==null?"0":rset2.getString(17))+"~~";
							dcq += (rset2.getString(14)==null?"0":rset2.getString(14))+"~~";
							tcq += (rset2.getString(5)==null?"0":rset2.getString(5))+"~~";
							snRev += (rset2.getString(2)==null?"0":rset2.getString(2))+"~~";
							snNo += (rset2.getString(1)==null?"0":rset2.getString(1))+"~~";
							customer += (rset2.getString(3)==null?"0":rset2.getString(3))+"~~";
							Rev_No += "0"+"~~";
							FGSA_No += (rset2.getString(4)==null?"0":rset2.getString(4))+"~~";
							bscode += (rset2.getString(3)==null?"0":rset2.getString(3))+"~~";
							
							String tmp_sn_ref = rset2.getString(18)==null?"":rset2.getString(18);
							
							if(tmp_sn_ref.trim().equals(""))
							{
								tmp_sn_ref = rset2.getString(1)==null?"0":rset2.getString(1);
							}
							
							snRefNo += (tmp_sn_ref)+"~~";
							
							queryString3 = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
										   "from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+rset1.getString(1)+" and " +
										   "FGSA_TENDER_REV_NO=0 and CUSTOMER_CD="+rset.getString(3)+" and " +
										   "CONT_TYPE='L' and SN_LOA_NO="+rset1.getString(2)+" and SN_LOA_REV_NO="+rset1.getString(5)+"";
							//System.out.println("SN/LOA LC Details Select Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);		
							if(rset3.next())
							{		
								lc_exchg_rate += (rset3.getString(1)==null?"-":rset3.getString(1))+"~~";
								flag_lc_value += (rset3.getString(2)==null?"-":rset3.getString(2))+"~~";
								flag_dcq_tcq += (rset3.getString(3)==null?"-":rset3.getString(3))+"~~";
								dcqdays_tcqpercent_value += (rset3.getString(4)==null?"-":rset3.getString(4))+"~~";
								
								if(rset3.getString(2).trim().equalsIgnoreCase("SN"))
								{
									lc_base_remark += "Against SN Value"+"~~";
								}
								else if(rset3.getString(2).trim().equalsIgnoreCase("TAX"))
								{
									lc_base_remark += "Against Tax and Advances"+"~~";
								}
								else if(rset3.getString(2).trim().equalsIgnoreCase("OPEN"))
								{
									lc_base_remark += "Against Open Account"+"~~";
								}
								else
								{
									lc_base_remark += "-"+"~~";
								}
							}
							else
							{
								queryString3 = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
											  "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+rset1.getString(1)+" and " +
											  "FGSA_TENDER_REV_NO=0 and CUSTOMER_CD="+rset.getString(3)+" and " +
											  "CONT_TYPE='T'";
								
								//System.out.println("(If SN/LOA LC Details is Absent, then ...) FGSA/Tender LC Details Select Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);		
								if(rset3.next())
								{		
									lc_exchg_rate += (rset3.getString(1)==null?"-":rset3.getString(1))+"~~";
									flag_lc_value += (rset3.getString(2)==null?"-":rset3.getString(2))+"~~";
									flag_dcq_tcq += (rset3.getString(3)==null?"-":rset3.getString(3))+"~~";
									dcqdays_tcqpercent_value += (rset3.getString(4)==null?"-":rset3.getString(4))+"~~";
									
									if(rset3.getString(2).trim().equalsIgnoreCase("SN"))
									{
										lc_base_remark += "Against SN Value"+"~~";
									}
									else if(rset3.getString(2).trim().equalsIgnoreCase("TAX"))
									{
										lc_base_remark += "Against Tax and Advances"+"~~";
									}
									else if(rset3.getString(2).trim().equalsIgnoreCase("OPEN"))
									{
										lc_base_remark += "Against Open Account"+"~~";
									}
									else
									{
										lc_base_remark += "-"+"~~";
									}
								}
								else
								{
									lc_exchg_rate += "-"+"~~";
									flag_lc_value += "-"+"~~";
									flag_dcq_tcq += "-"+"~~";
									dcqdays_tcqpercent_value += "-"+"~~";
									lc_base_remark += "-"+"~~";
								}
							}
						}
					}					
				}
				
				/*System.out.println("bscode = "+bscode);
				System.out.println("FGSA_No = "+FGSA_No);
				System.out.println("Rev_No = "+Rev_No);
				System.out.println("snNo = "+snNo);
				System.out.println("snRev = "+snRev);
				System.out.println("dcq = "+dcq);
				System.out.println("tcq = "+tcq);
				System.out.println("START_DT = "+START_DT);
				System.out.println("END_DT = "+END_DT);
				System.out.println("datediff = "+datediff);
				System.out.println("rate = "+rate);
				System.out.println("cont_type = "+cont_type);
				System.out.println("tax_type = "+tax_type);
				System.out.println("lc_exchg_rate = "+lc_exchg_rate);
				System.out.println("flag_lc_value = "+flag_lc_value);
				System.out.println("flag_dcq_tcq = "+flag_dcq_tcq);
				System.out.println("dcqdays_tcqpercent_value = "+dcqdays_tcqpercent_value);
				System.out.println("lc_base_remark = "+lc_base_remark);*/
				
				CUST_CD.add(bscode);
				vSN_No.add(snNo);
				vSN_Ref_No.add(snRefNo);
				vSN_rev_No.add(snRev);
				vSN_DCQ.add(dcq);
				vSN_TCQ.add(tcq);	
				FGSA_no.add(FGSA_No);
				vFGSA_REV_NO.add(Rev_No);
				vSN_StartDate.add(START_DT);
				vSN_EndDate.add(END_DT);
				vSN_DATEDIFF.add(datediff);
				vSN_RATE.add(rate);
				vCONT_TYPE.add(cont_type);
				vTAX_TYPE.add(tax_type);
				vSN_LC_EXCHG_RATE.add(lc_exchg_rate);
				vSN_LC_BASE.add(flag_lc_value);
				vSN_LC_DCQ_TCQ_FLAG.add(flag_dcq_tcq);
				vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.add(dcqdays_tcqpercent_value);
				vSN_LC_BASE_REMARK.add(lc_base_remark);
				vSN_TAX_RATE.add(tax_rate);
			}			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_SEQ_NO_List() Method:\n"+e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public void fetch_LC_List()
	{
		try 
		{		
			//System.out.println("Buyer CD = "+Buyer_cd);
			/*queryString = "SELECT A.FGSA_NO, A.REV_NO FROM FMS7_FGSA_CLAUSE_MST A WHERE " +
						  "A.CLAUSE_CD=5 AND A.FLAG='Y' AND A.BUYER_CD="+Buyer_cd+" AND A.REV_NO=" +
						  "(SELECT MAX(B.REV_NO) FROM FMS7_FGSA_CLAUSE_MST B WHERE A.CLAUSE_CD=B.CLAUSE_CD AND " +
						  "A.FLAG=B.FLAG AND A.BUYER_CD=B.BUYER_CD AND A.FGSA_NO=B.FGSA_NO) " +
						  "ORDER BY A.FGSA_NO,A.REV_NO";*/ //Commented By Samik Shah On 1st June, 2011 ...
			
			queryString = "SELECT DISTINCT A.FGSA_NO,A.SN_NO FROM FMS7_SN_CLAUSE_MST A WHERE " +
						  "A.CLAUSE_CD=5 AND A.FLAG='T' AND A.BUYER_CD="+Buyer_cd+" " +
						  "ORDER BY A.FGSA_NO,A.SN_NO";
			//System.out.println("queryString --> "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())	
			{
				//System.out.println("FGSA NO = "+rset.getString(1));
				//System.out.println("FGSA REV NO = "+rset.getString(2));				 
				queryString1 = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT, "+
							   "C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, A.VERIFY_FLAG, A.APPROVE_FLAG, A.DCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'), " +
							   "TO_CHAR(A.START_DT,'DD/MM/YYYY'), (A.END_DT - A.START_DT)+1, A.SN_REF_NO "+
							   "FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
							   "A.END_DT>=to_date('"+lc_gen_dt+"','DD/MM/YYYY') AND " +
							   "A.FGSA_NO="+rset.getString(1)+" AND " +
							   //"A.FGSA_REV_NO="+rset.getString(2)+" AND " + //Commented By Samik On 1st June, 2011 ...
							   "A.SN_NO="+rset.getString(2)+" AND " +
							   "A.CUSTOMER_CD="+Buyer_cd+" AND A.STATUS='Y' AND A.SN_REV_NO="+
							   "(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND "+
							   "B.FGSA_NO=A.FGSA_NO AND " +
							   //"B.FGSA_REV_NO=A.FGSA_REV_NO AND " + //Commented By Samik On 1st June, 2011 ...
							   "B.FLAG=A.FLAG AND B.CUSTOMER_CD=A.CUSTOMER_CD AND " +
							   "A.STATUS=B.STATUS AND A.END_DT=B.END_DT) AND "+							
							   "A.CUSTOMER_CD=C.CUSTOMER_CD AND A.QUANTITY_UNIT=D.UNIT_CD " +
							   "ORDER BY A.fgsa_no,A.sn_no";				
				//System.out.println("SN List Query For LC Generation (1) = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					/*System.out.println("buyers"+rset1.getString(3));
					System.out.println("FGSA NO"+rset1.getString(4));
					System.out.println("end Date"+rset1.getString(16));
					System.out.println("Start Date"+rset1.getString(17));
					System.out.println("difference Date"+rset1.getString(18));*/					
					queryString3 = "SELECT LC_SEQ_NO FROM FMS7_LC_DTL WHERE SN_NO='"+rset1.getString(1)+"' AND " +
								   "FGSA_NO='"+rset1.getString(4)+"' AND CUSTOMER_CD='"+rset1.getString(3)+"' AND " +
								   "CONT_TYPE='S' AND FLAG = 'Y'";
					//System.out.println("SN-LC SEQ NO queryString3 = "+queryString3);
					rset4 = stmt4.executeQuery(queryString3);
					if(rset4.next())
					{
						vLC_SEQ_NO.add(rset4.getString(1)==null?"-":rset4.getString(1));
						String sn_lc_no = rset4.getString(1)==null?"":rset4.getString(1);
						if(sn_lc_no.trim().equals(""))
						{
							vStatus.add("Y");
						}
						else
						{
							vStatus.add("N");
						}						
					}
					else
					{
						vLC_SEQ_NO.add("-");
						vStatus.add("Y");
					}
					    
					vSN_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
				    vSN_rev_No.add(rset1.getString(2)==null?"0":rset1.getString(2));
				    buyer_cd.add(rset1.getString(3)==null?"0":rset1.getString(3));
				    FGSA_no.add(rset1.getString(4)==null?"0":rset1.getString(4));
					vRev_No.add(rset1.getString(5)==null?"0":rset1.getString(5));
					vSN_TCQ.add(rset1.getString(6)==null?"0":rset1.getString(6));
					vTCQ_UNIT.add(rset1.getString(7)==null?"-":rset1.getString(7));
					vSN_RATE.add(rset1.getString(8)==null?"0":rset1.getString(8));
					vRATE_UNIT.add(rset1.getString(9)==null?"USD":rset1.getString(9));
					buyer_nm.add(rset1.getString(10)==null?"-":rset1.getString(10));
					vBUYER_ABBR.add(rset1.getString(11)==null?"-":rset1.getString(11));
					vSN_name.add(rset1.getString(12)==null?"-":rset1.getString(12));  
					vSN_verify.add(rset1.getString(13)==null?"N":rset1.getString(13)); 
					vSN_approve.add(rset1.getString(14)==null?"N":rset1.getString(14));
					vSN_DCQ.add(rset1.getString(15)==null?"0":rset1.getString(15));
					vSN_EndDate.add(rset1.getString(16)==null?"":rset1.getString(16));
					vSN_StartDate.add(rset1.getString(17)==null?"":rset1.getString(17));
					vSN_DATEDIFF.add(rset1.getString(18)==null?"0":rset1.getString(18));
					
					String tmp_sn_ref = rset1.getString(19)==null?"":rset1.getString(19);
					if(tmp_sn_ref.trim().equals(""))
					{
						vSN_Ref_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
					}
					else
					{
						vSN_Ref_No.add(tmp_sn_ref.trim());
					}
					
					vSN_CONT_Type.add("S");
					
					Vector plant_seq_no = new Vector();
					Vector tax_structure_cd = new Vector();
					Vector tax_structure_date = new Vector();
					Vector tax_structure_dtl = new Vector();
					Vector tax_rate_tmp = new Vector();
					
					queryString2 = "SELECT PLANT_SEQ_NO FROM FMS7_SN_PLANT_MST WHERE " +
								   "SN_NO="+rset1.getString(1)+" AND SN_REV_NO="+rset1.getString(2)+" AND " +
								   "CUSTOMER_CD="+rset1.getString(3)+" AND FGSA_NO="+rset1.getString(4)+" AND " +
								   "FGSA_REV_NO="+rset1.getString(5)+"";
					//System.out.println("Query For Fetching Tax Structure CD "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					while(rset2.next())
					{
						plant_seq_no.add(rset2.getString(1)==null?"0":rset2.getString(1));
					}
					
					for(int i=0; i<plant_seq_no.size(); i++)
					{
						queryString2 = "SELECT NVL(A.TAX_STRUCT_CD,'0'), TO_CHAR(A.TAX_STRUCT_DT,'DD/MM/YYYY'), " +
								       "A.TAX_STRUCT_DTL FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
						   			   "A.CUSTOMER_CD="+rset1.getString(3)+" AND " +
						   			   "A.PLANT_SEQ_NO="+plant_seq_no.elementAt(i)+" AND " +
						   			   "A.TAX_STRUCT_DT=(SELECT MAX(B.TAX_STRUCT_DT) " +
						   			   "FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
						   			   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.PLANT_SEQ_NO AND " +
						   			   "A.TAX_STRUCT_DT<=TO_DATE('"+rset1.getString(17)+"','DD/MM/YYYY'))";
						//System.out.println("Query For Fetching Tax Structure CD = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tax_structure_cd.add(rset2.getString(1)==null?"0":rset2.getString(1));
							tax_structure_date.add(rset2.getString(2)==null?"":rset2.getString(2));
							tax_structure_dtl.add(rset2.getString(3)==null?"":rset2.getString(3));							
							double tax_rate_sum = 0;
							String tax_factor = "0.00";
										
							queryString3 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+rset2.getString(1)+" AND " +
										  "A.app_date=TO_DATE('"+rset2.getString(2)+"','DD/MM/YYYY') ORDER BY A.tax_code";
							//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString3);
							rset3=stmt3.executeQuery(queryString3);
							while(rset3.next())
							{
								tax_factor = rset3.getString(2);								
								if(rset3.getString(3).equals("1"))
								{
									tax_rate_sum += Double.parseDouble(tax_factor);
								}
								else if(rset3.getString(3).equals("2"))
								{
									queryString4 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
												   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+rset2.getString(1)+" AND " +
												   "A.app_date=TO_DATE('"+rset2.getString(2)+"','DD/MM/YYYY') AND A.tax_code="+rset3.getString(4)+"";
									//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString4);
							 		rset4=stmt4.executeQuery(queryString4);
							 		if(rset4.next())
							 		{
							 			tax_rate_sum += (Double.parseDouble(rset4.getString(2))*Double.parseDouble(tax_factor))/100;
							 		}							 		
								}								
							}							
							tax_rate_tmp.add(nf.format(tax_rate_sum));
						}
					}					
					double max_tmp_rate = 0;
					String tmp_tax_dtl = "";					
					for(int i=0; i<tax_structure_cd.size(); i++)
					{
						if(max_tmp_rate<=Double.parseDouble(""+tax_rate_tmp.elementAt(i)))
						{
							max_tmp_rate = Double.parseDouble(""+tax_rate_tmp.elementAt(i));
							tmp_tax_dtl = ""+tax_structure_dtl.elementAt(i);
						}
					}
					if(tmp_tax_dtl.trim().equals(""))
					{
						vSNTax_Type.add("-"); //Introduced By Samik Shah On 17th August, 2011 ...
					}
					else
					{
						vSNTax_Type.add(tmp_tax_dtl); //Introduced By Samik Shah On 2nd April, 2011 ...
					}
					//vSNTax_Type.add("VAT"); //Commented By Samik Shah On 2nd April, 2011 ...
					vSNTax_Rate.add(nf.format(max_tmp_rate));
				}
			}
						
			for(int i=0; i<vSN_No.size(); i++)
			{
				queryString = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
							  "from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+FGSA_no.elementAt(i)+" and " +
							  "FGSA_TENDER_REV_NO="+vRev_No.elementAt(i)+" and CUSTOMER_CD="+buyer_cd.elementAt(i)+" and " +
							  "CONT_TYPE='S' and SN_LOA_NO="+vSN_No.elementAt(i)+" and SN_LOA_REV_NO="+vSN_rev_No.elementAt(i)+" AND FLAG = 'Y'";
				//System.out.println("SN/LOA LC Details Select Query = "+queryString);
				rset = stmt.executeQuery(queryString);		
				if(rset.next())
				{		
					vSN_LC_EXCHG_RATE.add(rset.getString(1)==null?"-":rset.getString(1));
					vSN_LC_BASE.add(rset.getString(2)==null?"-":rset.getString(2));
					vSN_LC_DCQ_TCQ_FLAG.add(rset.getString(3)==null?"-":rset.getString(3));
					vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.add(rset.getString(4)==null?"-":rset.getString(4));
					
					if(rset.getString(2).trim().equalsIgnoreCase("SN"))
					{
						vSN_LC_BASE_REMARK.add("Against SN Value");
					}
					else if(rset.getString(2).trim().equalsIgnoreCase("TAX"))
					{
						vSN_LC_BASE_REMARK.add("Against Tax and Advances");
					}
					else if(rset.getString(2).trim().equalsIgnoreCase("OPEN"))
					{
						vSN_LC_BASE_REMARK.add("Against Open Account");
					}
					else
					{
						vSN_LC_BASE_REMARK.add("-");
					}
				}
				else
				{
					queryString = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
								  "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+FGSA_no.elementAt(i)+" and " +
								  "FGSA_TENDER_REV_NO="+vRev_No.elementAt(i)+" and CUSTOMER_CD="+buyer_cd.elementAt(i)+" and " +
								  "CONT_TYPE='F' AND FLAG = 'Y'";
					
					//System.out.println("(If SN/LOA LC Details is Absent, then ...) FGSA/Tender LC Details Select Query = "+queryString);
					rset = stmt.executeQuery(queryString);		
					if(rset.next())
					{		
						vSN_LC_EXCHG_RATE.add(rset.getString(1)==null?"-":rset.getString(1));
						vSN_LC_BASE.add(rset.getString(2)==null?"-":rset.getString(2));
						vSN_LC_DCQ_TCQ_FLAG.add(rset.getString(3)==null?"-":rset.getString(3));
						vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.add(rset.getString(4)==null?"-":rset.getString(4));
						
						if(rset.getString(2).trim().equalsIgnoreCase("SN"))
						{
							vSN_LC_BASE_REMARK.add("Against SN Value");
						}
						else if(rset.getString(2).trim().equalsIgnoreCase("TAX"))
						{
							vSN_LC_BASE_REMARK.add("Against Tax and Advances");
						}
						else if(rset.getString(2).trim().equalsIgnoreCase("OPEN"))
						{
							vSN_LC_BASE_REMARK.add("Against Open Account");
						}
						else
						{
							vSN_LC_BASE_REMARK.add("-");
						}
					}
					else
					{
						vSN_LC_EXCHG_RATE.add("-");
						vSN_LC_BASE.add("-");
						vSN_LC_DCQ_TCQ_FLAG.add("-");
						vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.add("-");
						vSN_LC_BASE_REMARK.add("-");
					}
				}
			}
			
			/*for(int i=0; i<vSN_No.size(); i++)
			{
				System.out.println("vSNTax_Type.elementAt("+i+") = "+vSNTax_Type.elementAt(i));
				System.out.println("vSNTax_Rate.elementAt("+i+") = "+vSNTax_Rate.elementAt(i));
				System.out.println("vSN_LC_EXCHG_RATE.elementAt("+i+") = "+vSN_LC_EXCHG_RATE.elementAt(i));
				System.out.println("vSN_LC_BASE.elementAt("+i+") = "+vSN_LC_BASE.elementAt(i));
				System.out.println("vSN_LC_DCQ_TCQ_FLAG.elementAt("+i+") = "+vSN_LC_DCQ_TCQ_FLAG.elementAt(i));
				System.out.println("vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.elementAt("+i+") = "+vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.elementAt(i));
				System.out.println("vSN_LC_BASE_REMARK.elementAt("+i+") = "+vSN_LC_BASE_REMARK.elementAt(i));
			}*/
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_List() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
		
		//LOA LIST
		try 
		{
			/*queryString1 = "SELECT TENDER_NO FROM FMS7_TENDER_CLAUSE_MST " +
						   "WHERE CLAUSE_CD='5' And FLAG='Y' And " +
						   "CUSTOMER_CD='"+Buyer_cd+"'";*/ //Commented By Samik Shah On 1st June, 2011 ...
			
			queryString1 = "SELECT DISTINCT A.TENDER_NO,A.LOA_NO FROM FMS7_LOA_CLAUSE_MST A WHERE " +
						   "A.CLAUSE_CD=5 AND A.FLAG='T' AND A.BUYER_CD="+Buyer_cd+" " +
						   "ORDER BY A.TENDER_NO,A.LOA_NO";
			rset = stmt1.executeQuery(queryString1);			
			//System.out.println("queryString----------"+queryString1);			
			while(rset.next())	
			{
				queryString = "SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD, A.TENDER_NO, A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT, "+
							  "C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, A.VERIFY_FLAG, A.APPROVE_FLAG, A.DCQ, " +
							  "TO_CHAR(A.END_DT,'DD/MM/YYYY'), TO_CHAR(A.START_DT,'DD/MM/YYYY'), (A.END_DT - A.START_DT)+1, A.LOA_REF_NO "+
							  "from FMS7_LOA_MST A, FMS7_CUSTOMER_MST C , FMS7_UNIT_MST D WHERE A.FLAG='T' " +
							  "AND A.END_DT>=to_date('"+lc_gen_dt+"','DD/MM/YYYY') AND A.CUSTOMER_CD='"+Buyer_cd+"' " +
							  "AND A.STATUS='Y' AND A.TENDER_NO='"+rset.getString(1)+"' AND A.LOA_NO='"+rset.getString(2)+"' " +
							  "AND A.LOA_REV_NO=(SELECT MAX(A.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO "+
					          "AND A.TENDER_NO=B.TENDER_NO AND A.FLAG=B.FLAG AND A.STATUS=B.STATUS " +
					          "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND A.END_DT=B.END_DT) "+
					          "AND C.CUSTOMER_CD=A.CUSTOMER_CD " +
					          "AND A.QUANTITY_UNIT=D.UNIT_CD ORDER BY A.tender_no,A.loa_no";			 
			//	System.out.println("queryString = "+queryString);
				rset1 = stmt.executeQuery(queryString);
				while(rset1.next())
				{
					queryString3 = "SELECT LC_SEQ_NO FROM FMS7_LC_DTL WHERE SN_NO='"+rset1.getString(1)+"' AND " +
								   "FGSA_NO='"+rset1.getString(4)+"' AND CUSTOMER_CD='"+rset1.getString(3)+"' AND " +
								   "CONT_TYPE='L' AND FLAG = 'Y'";
					//System.out.println("LOA-LC SEQ NO queryString3 = "+queryString3);
					rset4=stmt4.executeQuery(queryString3);
					if(rset4.next())
					{
						String loa_lc_no = rset4.getString(1)==null?"":rset4.getString(1);
						if(loa_lc_no.trim().equals(""))
						{
							vLOAStatus.add("Y");
						}
						else
						{
							vLOAStatus.add("N");
						}
						vLOALC_SEQ_NO.add(rset4.getString(1)==null?"-":rset4.getString(1));
					}
					else
					{
						vLOALC_SEQ_NO.add("-");
						vLOAStatus.add("Y");
					}				    
					vLOA_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
				   	vLOA_rev_No.add(rset1.getString(2)==null?"0":rset1.getString(2));
				   	vLOA_buyer_cd.add(rset1.getString(3)==null?"0":rset1.getString(3));
				    Tender_no.add(rset1.getString(4)==null?"0":rset1.getString(4));
					//vRev_No.add(rset1.getString(5)==null?"":rset1.getString(5));
					vLOA_TCQ.add(rset1.getString(5)==null?"0":rset1.getString(5));
					vTCQ_UNIT.add(rset1.getString(6)==null?"-":rset1.getString(6));
				
					vLOA_RATE.add(rset1.getString(7)==null?"0":rset1.getString(7));
					vRATE_UNIT.add(rset1.getString(8)==null?"USD":rset1.getString(8));
					vLOA_buyer_nm.add(rset1.getString(9)==null?"-":rset1.getString(9));
					vLOA_BUYER_ABBR.add(rset1.getString(10)==null?"-":rset1.getString(10));
				
					vLOA_name.add(rset1.getString(11)==null?"-":rset1.getString(11));  
					vLOA_verify.add(rset1.getString(12)==null?"N":rset1.getString(12)); 
					vLOA_Approve.add(rset1.getString(13)==null?"N":rset1.getString(13)); 
					vLOA_DCQ.add(rset1.getString(14)==null?"0":rset1.getString(14));
					
					vLOA_EndDate.add(rset1.getString(15)==null?"":rset1.getString(15));
					vLOA_StartDate.add(rset1.getString(16)==null?"":rset1.getString(16));
					vLOA_DATEDIFF.add(rset1.getString(17)==null?"0":rset1.getString(17));
					
					String tmp_loa_ref = rset1.getString(18)==null?"":rset1.getString(18);
					if(tmp_loa_ref.trim().equals(""))
					{
						vLOA_Ref_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
					}
					else
					{
						vLOA_Ref_No.add(tmp_loa_ref.trim());
					}
					
					vLOA_CONT_Type.add("L");
					
					Vector plant_seq_no = new Vector();
					Vector tax_structure_cd = new Vector();
					Vector tax_structure_date = new Vector();
					Vector tax_structure_dtl = new Vector();
					Vector tax_rate_tmp = new Vector();
					
					queryString2 = "SELECT PLANT_SEQ_NO FROM FMS7_LOA_PLANT_MST WHERE " +
								   "LOA_NO="+rset1.getString(1)+" AND LOA_REV_NO="+rset1.getString(2)+" AND " +
								   "CUSTOMER_CD="+rset1.getString(3)+" AND TENDER_NO="+rset1.getString(4)+"";
					//System.out.println("Query For Fetching Tax Structure CD "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					while(rset2.next())
					{
						plant_seq_no.add(rset2.getString(1)==null?"0":rset2.getString(1));
					}
					
					for(int i=0; i<plant_seq_no.size(); i++)
					{
						queryString2 = "SELECT NVL(A.TAX_STRUCT_CD,'0'), TO_CHAR(A.TAX_STRUCT_DT,'DD/MM/YYYY'), " +
								       "A.TAX_STRUCT_DTL FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
						   			   "A.CUSTOMER_CD="+rset1.getString(3)+" AND " +
						   			   "A.PLANT_SEQ_NO="+plant_seq_no.elementAt(i)+" AND " +
						   			   "A.TAX_STRUCT_DT=(SELECT MAX(B.TAX_STRUCT_DT) " +
						   			   "FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
						   			   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.PLANT_SEQ_NO AND " +
						   			   "A.TAX_STRUCT_DT<=TO_DATE('"+rset1.getString(16)+"','DD/MM/YYYY'))";
						//System.out.println("Query For Fetching Tax Structure CD = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tax_structure_cd.add(rset2.getString(1)==null?"0":rset2.getString(1));
							tax_structure_date.add(rset2.getString(2)==null?"":rset2.getString(2));
							tax_structure_dtl.add(rset2.getString(3)==null?"":rset2.getString(3));							
							double tax_rate_sum = 0;
							String tax_factor = "0.00";
										
							queryString3 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+rset2.getString(1)+" AND " +
										  "A.app_date=TO_DATE('"+rset2.getString(2)+"','DD/MM/YYYY') ORDER BY A.tax_code";
							//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString3);
							rset3=stmt3.executeQuery(queryString3);
							while(rset3.next())
							{
								tax_factor = rset3.getString(2);								
								if(rset3.getString(3).equals("1"))
								{
									tax_rate_sum += Double.parseDouble(tax_factor);
								}
								else if(rset3.getString(3).equals("2"))
								{
									queryString4 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
												   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+rset2.getString(1)+" AND " +
												   "A.app_date=TO_DATE('"+rset2.getString(2)+"','DD/MM/YYYY') AND A.tax_code="+rset3.getString(4)+"";
									//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString4);
							 		rset4=stmt4.executeQuery(queryString4);
							 		if(rset4.next())
							 		{
							 			tax_rate_sum += (Double.parseDouble(rset4.getString(2))*Double.parseDouble(tax_factor))/100;
							 		}							 		
								}								
							}							
							tax_rate_tmp.add(nf.format(tax_rate_sum));
						}
					}					
					double max_tmp_rate = 0;
					String tmp_tax_dtl = "";					
					for(int i=0; i<tax_structure_cd.size(); i++)
					{
						if(max_tmp_rate<=Double.parseDouble(""+tax_rate_tmp.elementAt(i)))
						{
							max_tmp_rate = Double.parseDouble(""+tax_rate_tmp.elementAt(i));
							tmp_tax_dtl = ""+tax_structure_dtl.elementAt(i);
						}
					}
					
					if(tmp_tax_dtl.trim().equals(""))
					{
						vLOATax_Type.add("-"); //Introduced By Samik Shah On 17th August, 2011 ...
					}
					else
					{
						vLOATax_Type.add(tmp_tax_dtl); //Introduced By Samik Shah On 2nd April, 2011 ...
					}
					//vLOATax_Type.add("VAT"); //Commented By Samik Shah On 2nd April, 2011 ...
					vLOATax_Rate.add(nf.format(max_tmp_rate));
					
					/*String Tax_cd = "0";
					queryString2 = "SELECT TAX_STRUCT_CD FROM FMS7_SN_BILLING_DTL WHERE " +
								   "SN_NO='"+rset1.getString(1)+"' AND SN_REV_NO='"+rset1.getString(2)+"' AND " +
								   "CUSTOMER_CD="+rset1.getString(3)+" AND FGSA_NO="+rset1.getString(4)+" AND " +
								   "CONT_TYPE='L'";
					System.out.println("queryString----------"+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						Tax_cd = rset2.getString(1)==null?"0":rset2.getString(1);
						//System.out.println("Tax_cd"+Tax_cd);						
					}
					else
					{
						Tax_cd = "0";
					}
					
					queryString2="SELECT DISTINCT(A.TAX_CODE) FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.APP_DATE<=TO_DATE('"+rset1.getString(16)+"','DD/MM/YYYY') AND B.TAX_STR_CD='"+Tax_cd+"')";
					System.out.println("queryString----------"+queryString2);
					rset3 = stmt3.executeQuery(queryString2);
					if(rset3.next())
					{
						if(rset3.getString(1).equals("101"))
						{
							vLOATax_Type.add("VAT");
						}
						else if(rset3.getString(1).equals("101"))
						{
							vLOATax_Type.add("CST");
						}
					}
					else
					{
						vLOATax_Type.add("CST");
					}					
					double max_tmp_rate = 0;									
					vLOATax_Rate.add(nf.format(max_tmp_rate));*/
				}
			}
			
			for(int i=0; i<vLOA_No.size(); i++)
			{
				queryString = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
							  "from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+Tender_no.elementAt(i)+" and " +
							  "FGSA_TENDER_REV_NO=0 and CUSTOMER_CD="+vLOA_buyer_cd.elementAt(i)+" and " +
							  "CONT_TYPE='L' and SN_LOA_NO="+vLOA_No.elementAt(i)+" and SN_LOA_REV_NO="+vLOA_rev_No.elementAt(i)+" AND FLAG = 'Y'";
				//System.out.println("SN/LOA LC Details Select Query = "+queryString);
				rset = stmt.executeQuery(queryString);		
				if(rset.next())
				{		
					vLOA_LC_EXCHG_RATE.add(rset.getString(1)==null?"-":rset.getString(1));
					vLOA_LC_BASE.add(rset.getString(2)==null?"-":rset.getString(2));
					vLOA_LC_DCQ_TCQ_FLAG.add(rset.getString(3)==null?"-":rset.getString(3));
					vLOA_LC_DCQ_DAYS_TCQ_PERCENTAGE.add(rset.getString(4)==null?"-":rset.getString(4));
					
					if(rset.getString(2).trim().equalsIgnoreCase("SN"))
					{
						vLOA_LC_BASE_REMARK.add("Against SN Value");
					}
					else if(rset.getString(2).trim().equalsIgnoreCase("TAX"))
					{
						vLOA_LC_BASE_REMARK.add("Against Tax and Advances");
					}
					else if(rset.getString(2).trim().equalsIgnoreCase("OPEN"))
					{
						vLOA_LC_BASE_REMARK.add("Against Open Account");
					}
					else
					{
						vLOA_LC_BASE_REMARK.add("-");
					}
				}
				else
				{
					queryString = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
								  "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+Tender_no.elementAt(i)+" and " +
								  "FGSA_TENDER_REV_NO=0 and CUSTOMER_CD="+vLOA_buyer_cd.elementAt(i)+" and " +
								  "CONT_TYPE='T' AND FLAG = 'Y'";
					
					//System.out.println("(If SN/LOA LC Details is Absent, then ...) FGSA/Tender LC Details Select Query = "+queryString);
					rset = stmt.executeQuery(queryString);		
					if(rset.next())
					{		
						vLOA_LC_EXCHG_RATE.add(rset.getString(1)==null?"-":rset.getString(1));
						vLOA_LC_BASE.add(rset.getString(2)==null?"-":rset.getString(2));
						vLOA_LC_DCQ_TCQ_FLAG.add(rset.getString(3)==null?"-":rset.getString(3));
						vLOA_LC_DCQ_DAYS_TCQ_PERCENTAGE.add(rset.getString(4)==null?"-":rset.getString(4));
						
						if(rset.getString(2).trim().equalsIgnoreCase("SN"))
						{
							vLOA_LC_BASE_REMARK.add("Against SN Value");
						}
						else if(rset.getString(2).trim().equalsIgnoreCase("TAX"))
						{
							vLOA_LC_BASE_REMARK.add("Against Tax and Advances");
						}
						else if(rset.getString(2).trim().equalsIgnoreCase("OPEN"))
						{
							vLOA_LC_BASE_REMARK.add("Against Open Account");
						}
						else
						{
							vLOA_LC_BASE_REMARK.add("-");
						}
					}
					else
					{
						vLOA_LC_EXCHG_RATE.add("-");
						vLOA_LC_BASE.add("-");
						vLOA_LC_DCQ_TCQ_FLAG.add("-");
						vLOA_LC_DCQ_DAYS_TCQ_PERCENTAGE.add("-");
						vLOA_LC_BASE_REMARK.add("-");
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_List() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	

	public void fetch_SN_List3() 
	{
		try 
		{
			
			String vsnno="";
			String vsnrevno="";
			String buyercd="";
			String fgsano="";
			String revno="";
			String sntcq="";
			String tcqunit="";
			String snrate="";
			String rateunit="";
			String buyernm="";
			String buyerabr="";
			String snnm="";
			String snverify="";
			String snapprv="";
			String snrefno="";
			String fccflg="";
			
			
			for(int p=0;p<CUST_CD.size();p++)
			{
				vsnno="";
				vsnrevno="";
				buyercd="";
				fgsano="";
				revno="";
				sntcq="";
				tcqunit="";
				snrate="";
				rateunit="";
				buyernm="";
				buyerabr="";
				snnm="";
				snverify="";
				snapprv="";
				snrefno="";
				fccflg="";
				
				
				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
				"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+CUST_CD.elementAt(p)+"' AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				rset1 = stmt.executeQuery(queryString);
				while(rset1.next())
				{
					String sncd=rset1.getString(1)==null?"":rset1.getString(1);
					vsnno=vsnno+(rset1.getString(1)==null?" ":rset1.getString(1))+"#";
					String snrevno=rset1.getString(2)==null?"":rset1.getString(2);
				   vsnrevno=vsnrevno+(rset1.getString(2)==null?" ":rset1.getString(2))+"#";
				    buyercd=buyercd+(rset1.getString(3)==null?" ":rset1.getString(3))+"#";
				    String fgsanum=rset1.getString(4)==null?"":rset1.getString(4);
				    String fgsarev=rset1.getString(5)==null?"":rset1.getString(5);
				  fgsano=fgsano+(rset1.getString(4)==null?" ":rset1.getString(4))+"#";
				    revno=revno+(rset1.getString(5)==null?" ":rset1.getString(5))+"#";
					sntcq=sntcq+(rset1.getString(6)==null?" ":rset1.getString(6))+"#";
					tcqunit=tcqunit+(rset1.getString(7)==null?" ":rset1.getString(7))+"#";
					snrate=snrate+(rset1.getString(8)==null?" ":rset1.getString(8))+"#";
					rateunit=rateunit+(rset1.getString(9)==null?" ":rset1.getString(9))+"#";
				buyernm=buyernm+(rset1.getString(10)==null?" ":rset1.getString(10))+"#";
					buyerabr=buyerabr+(rset1.getString(11)==null?" ":rset1.getString(11))+"#";
					snnm=snnm+(rset1.getString(12)==null?" ":rset1.getString(12))+"#";
					snverify=snverify+(rset1.getString(13)==null?" ":rset1.getString(13))+"#";
					snapprv=snapprv+(rset1.getString(14)==null?" ":rset1.getString(14))+"#";
					
					String sn_ref_no = rset1.getString(15)==null?"":rset1.getString(15);
					if(!sn_ref_no.trim().equals(""))
					{
						snrefno=snrefno+" - ("+sn_ref_no+")"+"#";
						
					}
					else
					{
						snrefno=snrefno+" "+"#";
						
					}
					
					//for fcc_flag
					
					
					queryString = "SELECT TO_CHAR(SIGNING_DT,'DD/MM/YYYY'),TO_CHAR(RENEWAL_DT,'DD/MM/YYYY'), " +
					"TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ, " +
					"QUANTITY_UNIT, GCV, NCV, RATE, RATE_UNIT, STATUS, BUYER_NOM, " +
					"BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, " +
					"SELLER_MONTH_NOM, SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF, " +
					"DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT, " +
					"MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, " +
					"OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, " +
					"SPEC_GAS_MAX_ENERGY, DCQ, VERIFY_FLAG , APPROVE_FLAG, " +
					"VARIATION_QTY, VARIATION_MODE, MDCQ_QTY_CD, OBLIGATION, " +
					"OBLIG_PERCENTAGE, OBLIG_QTY_CD, SN_NAME, TO_CHAR(REV_DT,'DD/MM/YYYY'), " +
					"SN_CLOSURE_FLAG, SN_CLOSURE_QTY, TO_CHAR(SN_CLOSURE_DT,'DD/MM/YYYY'), " +
					"SN_REF_NO, TRANSPORTATION_CHARGE, REMARK, ADV_AMT," +
					"TCQ_REQUEST_FLAG,TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY,FCC_FLAG," +
					"SN_CLOSURE_REQUEST,SN_CLOSURE_CLOSE,FCC_BY," +
					"TO_CHAR(FCC_DATE,'DD/MM/YYYY'),TCQ_REQUEST_CLOSE,SN_REMARK " +
					"FROM FMS7_SN_MST WHERE SN_NO='"+sncd+"' AND " +
					"SN_REV_NO='"+snrevno+"' AND CUSTOMER_CD='"+CUST_CD.elementAt(p)+"' AND " +
					"FGSA_NO='"+fgsanum+"' AND FGSA_REV_NO = '"+fgsarev+"'";			
			//System.out.println("SN Master Query = "+queryString);
			rset = stmt1.executeQuery(queryString);			
			if(rset.next())
			{
				fccflg=fccflg+(rset.getString(55)==null?" ":rset.getString(55))+"#";
				
			}	
				}
				
				 vSN_No.add(vsnno);
				 vSN_rev_No.add(vsnrevno);
				  buyer_cd.add(buyercd);
				  FGSA_no.add(fgsano);
				  vRev_No.add(revno);
				  vSN_TCQ.add(sntcq);
				  vTCQ_UNIT.add(tcqunit);
				  vSN_RATE.add(snrate);
					vRATE_UNIT.add(rateunit);
					buyer_nm.add(buyernm);
					vBUYER_ABBR.add(buyerabr);
					vSN_name.add(snnm);  
					vSN_verify.add(snverify); 
					vSN_approve.add(snapprv);
					vSN_Ref_No.add(snrefno);
					fccflag.add(fccflg);
				
			}
			
			//start
			
			
//			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
//			{
//				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
//				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
//				"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
//				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
//				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
//				"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+bscode+"' AND " +
//				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
//				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
//				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
//			}
//			else
//			{
//				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
//				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
//				"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
//				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
//				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
//				"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
//				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
//				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
//				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
//			}
//			System.out.println("Query For Fetching SN List = "+queryString);
//			rset1 = stmt.executeQuery(queryString);
//			while(rset1.next())
//			{
//		    vSN_No.add(rset1.getString(1)==null?"":rset1.getString(1));
//		    vSN_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
//		    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
//		    FGSA_no.add(rset1.getString(4)==null?"":rset1.getString(4));
//			vRev_No.add(rset1.getString(5)==null?"":rset1.getString(5));
//			vSN_TCQ.add(rset1.getString(6)==null?"":rset1.getString(6));
//			vTCQ_UNIT.add(rset1.getString(7)==null?"":rset1.getString(7));
//			vSN_RATE.add(rset1.getString(8)==null?"":rset1.getString(8));
//			vRATE_UNIT.add(rset1.getString(9)==null?"":rset1.getString(9));
//			buyer_nm.add(rset1.getString(10)==null?"":rset1.getString(10));
//			vBUYER_ABBR.add(rset1.getString(11)==null?"":rset1.getString(11));
//			vSN_name.add(rset1.getString(12)==null?"":rset1.getString(12));  
//			vSN_verify.add(rset1.getString(13)==null?"":rset1.getString(13)); 
//			vSN_approve.add(rset1.getString(14)==null?"":rset1.getString(14));
//			String sn_ref_no = rset1.getString(15)==null?"":rset1.getString(15);
//			if(!sn_ref_no.trim().equals(""))
//			{
//				vSN_Ref_No.add(" - ("+sn_ref_no+")");
//			}
//			else
//			{
//				vSN_Ref_No.add("");
//			}
//		}
	}
	catch (Exception e)
	{
		System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_List() --> "+e.getMessage());
		e.printStackTrace();
	}
}

	public void fetch_SN_List2() 
	{
		try 
		{
			//System.out.println("bscode...."+bscode);
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
				"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO " +
				", TO_CHAR(START_DT,'dd/mm/yyyy'), TO_CHAR(END_DT,'dd/mm/yyyy')" +
				"FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+bscode+"' AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
			}
			else
			{
				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
				"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO " +
				", TO_CHAR(START_DT,'dd/mm/yyyy'), TO_CHAR(END_DT,'dd/mm/yyyy') " +
				"FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
			//	"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
			////////	" AND TO_DATE(SYSDATE,'DD/MM/YYYY') BETWEEN TO_DATE(START_DT,'dd/mm/yyyy') AND TO_DATE(END_DT,'dd/mm/yyyy') "+
				"ORDER BY C.CUSTOMER_NAME,A.fgsa_no, A.sn_no DESC";
			}
			//System.out.println("SN-List:QRY-SN001: "+queryString);
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{
			    vSN_No.add(rset1.getString(1)==null?"":rset1.getString(1));
			    vSN_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
			    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
			    FGSA_no.add(rset1.getString(4)==null?"":rset1.getString(4));
				vRev_No.add(rset1.getString(5)==null?"":rset1.getString(5));
				vSN_TCQ.add(rset1.getString(6)==null?"":rset1.getString(6));
				vTCQ_UNIT.add(rset1.getString(7)==null?"":rset1.getString(7));
				vSN_RATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				vRATE_UNIT.add(rset1.getString(9)==null?"":rset1.getString(9));
				buyer_nm.add(rset1.getString(10)==null?"":rset1.getString(10));
				vBUYER_ABBR.add(rset1.getString(11)==null?"":rset1.getString(11));
				vSN_name.add(rset1.getString(12)==null?"":rset1.getString(12));  
				vSN_verify.add(rset1.getString(13)==null?"":rset1.getString(13)); 
				vSN_approve.add(rset1.getString(14)==null?"":rset1.getString(14));
				String sn_ref_no = rset1.getString(15)==null?"":rset1.getString(15);
				
				if(!sn_ref_no.trim().equals(""))
				{
					vSN_Ref_No.add(" - ("+sn_ref_no+")");
				}
				else
				{
					vSN_Ref_No.add("-");
				}
				vSTART_DT.add(rset1.getString(16)==null?"":rset1.getString(16));
				vEND_DT.add(rset1.getString(17)==null?"":rset1.getString(17));
			}
			
			/////////////////SB20130810: For Line Color//////////
			int Start_days_diff=0; int end_days_diff=0;
			for(int i=0;i<buyer_cd.size();i++)
			{
				if(!vSTART_DT.elementAt(i).equals("")) 
				{
				queryString = "SELECT TO_DATE('"+vSTART_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
			
			//	System.out.println("COUNT-Days Difference: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Start_days_diff = rset.getInt(1);
					if(Start_days_diff<0 )
						VLineFlagStart.add("RED");
					if(Start_days_diff>0 )
						VLineFlagStart.add("GREEN");
					if(Start_days_diff==0 )
						VLineFlagStart.add("BLUE");
				}
				queryString = "SELECT TO_DATE('"+vEND_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
				
			//	System.out.println("COUNT-Days Difference: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					end_days_diff = rset.getInt(1);
					if(end_days_diff<0)
						VLineFlagEnd.add("RED");
					if(end_days_diff>0)
						VLineFlagEnd.add("GREEN");
					if(end_days_diff==0)
						VLineFlagEnd.add("BLUE");
					if(end_days_diff<0)
						VSNstatus.add("CLOSED");
					else
						VSNstatus.add("OPEN");
				}
				
				}
				else
				{
					VLineFlagEnd.add("BLACK");
					VLineFlagStart.add("BLACK");
					VSNstatus.add("OPEN");
				}
				Start_days_diff=0; end_days_diff=0;
			}
			//System.out.println("FLAG: "+vSN_approve);
		//	System.out.println("BUYER: "+buyer_cd.size());
		//	System.out.println("END: "+VLineFlagEnd.size());
		//	System.out.println("START: "+VLineFlagStart.size());
			//////////////////////////////////////////////////
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_SN_List4() 
	{
		try 
		{

			//System.out.println("bscode...."+bscode);
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
				"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO " +
				", TO_CHAR(START_DT,'dd/mm/yyyy'), TO_CHAR(END_DT,'dd/mm/yyyy')" +
				"FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+bscode+"' AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
			}
			else
			{
				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
				"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO " +
				", TO_CHAR(START_DT,'dd/mm/yyyy'), TO_CHAR(END_DT,'dd/mm/yyyy') " +
				"FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
			//	"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
			////////	" AND TO_DATE(SYSDATE,'DD/MM/YYYY') BETWEEN TO_DATE(START_DT,'dd/mm/yyyy') AND TO_DATE(END_DT,'dd/mm/yyyy') "+
				"ORDER BY C.CUSTOMER_NAME,A.fgsa_no, A.sn_no DESC";
			}
			//System.out.println("SN-List:QRY-SN001: "+queryString);
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{
			    vSN_No.add(rset1.getString(1)==null?"":rset1.getString(1));
			    vSN_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
			    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
			    FGSA_no.add(rset1.getString(4)==null?"":rset1.getString(4));
				vRev_No.add(rset1.getString(5)==null?"":rset1.getString(5));
				vSN_TCQ.add(rset1.getString(6)==null?"":rset1.getString(6));
				vTCQ_UNIT.add(rset1.getString(7)==null?"":rset1.getString(7));
				vSN_RATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				vRATE_UNIT.add(rset1.getString(9)==null?"":rset1.getString(9));
				buyer_nm.add(rset1.getString(10)==null?"":rset1.getString(10));
				vBUYER_ABBR.add(rset1.getString(11)==null?"":rset1.getString(11));
				vSN_name.add(rset1.getString(12)==null?"":rset1.getString(12));  
				vSN_verify.add(rset1.getString(13)==null?"":rset1.getString(13)); 
				vSN_approve.add(rset1.getString(14)==null?"":rset1.getString(14));
				String sn_ref_no = rset1.getString(15)==null?"":rset1.getString(15);
				
				if(!sn_ref_no.trim().equals(""))
				{
					vSN_Ref_No.add(" - ("+sn_ref_no+")");
				}
				else
				{
					vSN_Ref_No.add("-");
				}
				vSTART_DT.add(rset1.getString(16)==null?"":rset1.getString(16));
				vEND_DT.add(rset1.getString(17)==null?"":rset1.getString(17));
			}
			
			/////////////////SB20130810: For Line Color//////////
			int Start_days_diff=0; int end_days_diff=0;
			for(int i=0;i<buyer_cd.size();i++)
			{
				if(!vSTART_DT.elementAt(i).equals("")) 
				{
				queryString = "SELECT TO_DATE('"+vSTART_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
			
			//	System.out.println("COUNT-Days Difference: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Start_days_diff = rset.getInt(1);
					if(Start_days_diff<0 )
						VLineFlagStart.add("RED");
					if(Start_days_diff>0 )
						VLineFlagStart.add("GREEN");
					if(Start_days_diff==0 )
						VLineFlagStart.add("BLUE");
				}
				queryString = "SELECT TO_DATE('"+vEND_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
				
			//	System.out.println("COUNT-Days Difference: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					end_days_diff = rset.getInt(1);
					if(end_days_diff<0)
						VLineFlagEnd.add("RED");
					if(end_days_diff>0)
						VLineFlagEnd.add("GREEN");
					if(end_days_diff==0)
						VLineFlagEnd.add("BLUE");
					if(end_days_diff<0)
						VSNstatus.add("CLOSED");
					else
						VSNstatus.add("OPEN");
				}
				
				}
				else
				{
					VLineFlagEnd.add("BLACK");
					VLineFlagStart.add("BLACK");
					VSNstatus.add("OPEN");
				}
				Start_days_diff=0; end_days_diff=0;
			}
			//System.out.println("FLAG: "+vSN_approve);
		//	System.out.println("BUYER: "+buyer_cd.size());
		//	System.out.println("END: "+VLineFlagEnd.size());
		//	System.out.println("START: "+VLineFlagStart.size());
			//////////////////////////////////////////////////
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetch_SN_List() 
	{
		try 
		{
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
				"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
				"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
				"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
				"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND A.CUSTOMER_CD='"+bscode+"' AND " +
				"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
				"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
				"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
			}
			else
			{
				if(Customer_access_flag.equals("Y"))
				{
					queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
							"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
							"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
							"FMS7_UNIT_MST D, SEC_EMP_CUSTOMER_ALLOC_MST E "
							+ "WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
							"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
							"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
							"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD AND E.CUSTOMER_CD=A.CUSTOMER_CD AND "
							+ "E.EMP_CD='"+Emp_cd+"' " +
							"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				} else {
					queryString = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, " +
							"A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT,C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, " +
							"A.VERIFY_FLAG, A.FCC_FLAG, A.SN_REF_NO FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, " +
							"FMS7_UNIT_MST D WHERE A.FLAG='T' AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM " +
							"FMS7_SN_MST B WHERE A.SN_NO=B.SN_NO AND A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							"AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
							"C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) " +
							"AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
							"ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
				}
				
				
				
			}
		//	System.out.println("Query For Fetching SN List = "+queryString);
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{
			    vSN_No.add(rset1.getString(1)==null?"":rset1.getString(1));
			    vSN_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
			    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
			    FGSA_no.add(rset1.getString(4)==null?"":rset1.getString(4));
				vRev_No.add(rset1.getString(5)==null?"":rset1.getString(5));
				vSN_TCQ.add(rset1.getString(6)==null?"":rset1.getString(6));
				vTCQ_UNIT.add(rset1.getString(7)==null?"":rset1.getString(7));
				vSN_RATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				vRATE_UNIT.add(rset1.getString(9)==null?"":rset1.getString(9));
				buyer_nm.add(rset1.getString(10)==null?"":rset1.getString(10));
				vBUYER_ABBR.add(rset1.getString(11)==null?"":rset1.getString(11));
				vSN_name.add(rset1.getString(12)==null?"":rset1.getString(12));  
				vSN_verify.add(rset1.getString(13)==null?"":rset1.getString(13)); 
				vSN_approve.add(rset1.getString(14)==null?"":rset1.getString(14));
				String sn_ref_no = rset1.getString(15)==null?"":rset1.getString(15);
				if(!sn_ref_no.trim().equals(""))
				{
					vSN_Ref_No.add(" - ("+sn_ref_no+")");
				}
				else
				{
					vSN_Ref_No.add("");
				}
			}
			for(int i=0; i<FGSA_no.size(); i++)
			{
			//	System.out.println("value of bscode........"+bscode);
				if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
				{
					queryString = "SELECT MAX(REV_NO) FROM FMS7_FGSA_MST WHERE FGSA_NO='"+FGSA_no.elementAt(i)+"' AND CUSTOMER_CD='"+bscode+"' ";
			//		System.out.println("FGSA-RevNo:A  "+queryString);
					rset1 = stmt.executeQuery(queryString);
					while(rset1.next())
					{
					   if(vSN_approve.elementAt(i).equals("Y"))
						   vFGSA_Rev_No.add(vRev_No.elementAt(i));
					   else
					   {
						   vFGSA_Rev_No.add(rset1.getString(1));
						   	String query = "UPDATE FMS7_SN_MST SET FGSA_REV_NO='"+rset1.getString(1)+"' " +
						   		" WHERE FGSA_NO='"+FGSA_no.elementAt(i)+"' AND CUSTOMER_CD='"+bscode+"' AND SN_NO='"+vSN_No.elementAt(i)+"' ";
					//	   	stmtU.executeUpdate(query);
					//	   	System.out.println("FGSA-RevNo:UPDATE:FMS7_SN_MST  "+query);
						   	
						    query = "UPDATE FMS7_DUMMY_CARGO_DTL SET FGSA_REV_NO='"+rset1.getString(1)+"' " +
					   		"WHERE FGSA_NO='"+FGSA_no.elementAt(i)+"' AND CUSTOMER_CD='"+bscode+"' AND SN_NO='"+vSN_No.elementAt(i)+"' ";
					//	    System.out.println("FGSA-RevNo:UPDATE:FMS7_DUMMY_CARGO_DTL  "+query);
					//	    stmtU.executeUpdate(query);
						    
						    
						    query = "UPDATE FMS7_SN_CARGO_DTL SET FGSA_REV_NO='"+rset1.getString(1)+"' " +
					   		"WHERE FGSA_NO='"+FGSA_no.elementAt(i)+"' AND CUSTOMER_CD='"+bscode+"' AND SN_NO='"+vSN_No.elementAt(i)+"' ";
					//	    stmtU.executeUpdate(query);
					//	    System.out.println("FGSA-RevNo:UPDATE:FMS7_SN_CARGO_DTL  "+query);
					   }
					}
				}
				else
				{
					queryString = "SELECT MAX(REV_NO) FROM FMS7_FGSA_MST WHERE FGSA_NO='"+FGSA_no.elementAt(i)+"' ";
				//	System.out.println("FGSA-RevNo:B "+queryString);
					rset1 = stmt.executeQuery(queryString);
					while(rset1.next())
					{
						if(vSN_approve.elementAt(i).equals("Y"))
							   vFGSA_Rev_No.add(vRev_No.elementAt(i));
						   else
							   vFGSA_Rev_No.add(rset1.getString(1));
					}
				}
			}
			//System.out.println("FGSA-RevNo:C "+vFGSA_Rev_No);
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetch_new_fgsa_rev_no()
	{
		try
		{
			
			String query = "UPDATE FMS7_SN_MST SET FGSA_REV_NO='"+NewRevNo+"' " +
								   		" WHERE FGSA_NO='"+FgsaNo+"' AND CUSTOMER_CD='"+BuyerCd+"' AND SN_NO='"+SnNo+"' " +
								   		"and SN_REV_NO='"+SnRevNo+"' and FGSA_REV_NO='"+FgsaRevNo+"' ";
								   	stmtU.executeUpdate(query);
							//	   	System.out.println("FGSA-RevNo:UPDATE:FMS7_SN_MST  "+query);
								   	
								    query = "UPDATE FMS7_DUMMY_CARGO_DTL SET FGSA_REV_NO='"+NewRevNo+"' " +
							   		"WHERE FGSA_NO='"+FgsaNo+"' AND CUSTOMER_CD='"+BuyerCd+"' AND SN_NO='"+SnNo+"' ";
							//	    System.out.println("FGSA-RevNo:UPDATE:FMS7_DUMMY_CARGO_DTL  "+query);
								    stmtU.executeUpdate(query);
								    
								    
								    query = "UPDATE FMS7_SN_CARGO_DTL SET FGSA_REV_NO='"+NewRevNo+"' " +
							   		"WHERE FGSA_NO='"+FgsaNo+"' AND CUSTOMER_CD='"+BuyerCd+"' AND SN_NO='"+SnNo+"' ";
								    stmtU.executeUpdate(query);
							//	    System.out.println("FGSA-RevNo:UPDATE:FMS7_SN_CARGO_DTL  "+query);
								    
			fetch_SN_List();			   
											
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Contract_Master-->fetch_new_fgsa_rev_no()"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetch_LOA_List3() //ADDED BY MANOJ,Achal
	{
		try 
		{		

			String vlno="";
			String vlrevno="";
			String buyercd="";
			String tenderno="";
			String revno="";
			String vltcq="";
			String tcqunit="";
			String vlrate="";
			String rateunit="";
			String buyernm="";
			String buyerabr="";
			String vlnm="";
			String vlverify="";
			String vlapprv="";
			String vlrefno="";
			String fccflg="";
			
			
			for(int p=0;p<CUST_CD.size();p++)
			{
				vlno="";
				vlrevno="";
				buyercd="";
				tenderno="";
				revno="";
				vltcq="";
				tcqunit="";
				vlrate="";
				rateunit="";
				buyernm="";
				buyerabr="";
				vlnm="";
				vlverify="";
				vlapprv="";
				vlrefno="";
				fccflg="";
				queryString = "SELECT A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ,D.UNIT_ABR,A.RATE," +
				"A.RATE_UNIT,C.CUSTOMER_NAME,C.CUSTOMER_ABBR,A.LOA_NAME,A.VERIFY_FLAG,A.FCC_FLAG,A.LOA_REF_NO " +
				" ,TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY') "+	//RG 20140930
				"from FMS7_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND "+
	            "A.TENDER_NO=B.TENDER_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
	            "A.CUSTOMER_CD='"+CUST_CD.elementAt(p)+"' AND C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE " +
	            "E.CUSTOMER_CD=A.CUSTOMER_CD) AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
	         //SB20130916   " order by A.customer_cd,A.tender_no,A.loa_no";
				" order by C.customer_NAME,A.tender_no,A.loa_no";
				//System.out.println("LOA:QRY:L001: "+queryString);
				
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{			
				
				vlno=vlno+(rset1.getString(1)==null?" ":rset1.getString(1))+"#";
				String loacd=rset1.getString(1)==null?"":rset1.getString(1);
				String loarevno=rset1.getString(2)==null?"":rset1.getString(2);
				String tendno=rset1.getString(4)==null?"":rset1.getString(4);
				vlrevno=vlrevno+(rset1.getString(2)==null?" ":rset1.getString(2))+"#";
				buyercd=buyercd+(rset1.getString(3)==null?" ":rset1.getString(3))+"#";
				tenderno=tenderno+(rset1.getString(4)==null?" ":rset1.getString(4))+"#";
				vltcq=vltcq+(rset1.getString(5)==null?" ":rset1.getString(5))+"#";			
				tcqunit=tcqunit+(rset1.getString(6)==null?" ":rset1.getString(6))+"#";
				vlrate=vlrate+(rset1.getString(7)==null?" ":rset1.getString(7))+"#";
				rateunit=rateunit+(rset1.getString(8)==null?" ":rset1.getString(8))+"#";
				buyernm=buyernm+(rset1.getString(9)==null?" ":rset1.getString(9))+"#";
				buyerabr=buyerabr+(rset1.getString(10)==null?" ":rset1.getString(10))+"#";
				vlnm=vlnm+(rset1.getString(11)==null?" ":rset1.getString(11))+"#";
				vlverify=vlverify+(rset1.getString(12)==null?" ":rset1.getString(12))+"#";
				vlapprv=vlapprv+(rset1.getString(13)==null?" ":rset1.getString(13))+"#";
					
					String loa_ref_no = rset1.getString(14)==null?"-":rset1.getString(14);	//RG20140930
					if(!loa_ref_no.trim().equals(""))
					{
						vlrefno=vlrefno+" - ("+loa_ref_no+")"+"#";
						//vLOA_Ref_No.add(" - ("+loa_ref_no+")");
					}
					else
					{
						vlrefno=vlrefno+"-"+"#";
						//vLOA_Ref_No.add("-");	//RG20140930
					}
				 	//vSN_approve.add(loa_ref_no);	//RG20140930
				 	
					//vSTART_DT.add(rset1.getString(15)==null?"01/01/1900":rset1.getString(15));
					//vEND_DT.add(rset1.getString(16)==null?"01/01/1900":rset1.getString(16));
			
					queryString = "select TO_CHAR(SIGNING_DT,'DD/MM/YYYY'), TO_CHAR(RENEWAL_DT,'DD/MM/YYYY')," +
					" TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ," +
					" QUANTITY_UNIT, GCV, NCV, RATE, RATE_UNIT, STATUS, BUYER_NOM," +
					" BUYER_MONTH_NOM, BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM," +
					" SELLER_MONTH_NOM, SELLER_WEEK_NOM, SELLER_DAILY_NOM, DAY_DEF," +
					" DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, MEASUREMENT," +
					" MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, " +
					" OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY," +
					" SPEC_GAS_MAX_ENERGY, DCQ, VERIFY_FLAG , APPROVE_FLAG, " +
					" VARIATION_QTY,VARIATION_MODE,MDCQ_QTY_CD, LOA_NAME, LOA_REF_NO, " +
					" TRANSPORTATION_CHARGE, REMARK, ADV_AMT,FCC_FLAG," +
					" LOA_CLOSURE_REQUEST,LOA_CLOSURE_CLOSE,LOA_CLOSURE_FLAG,LOA_CLOSURE_QTY," +
					" TO_CHAR(LOA_CLOSURE_DT,'DD/MM/YYYY'),TCQ_REQUEST_SIGN,TCQ_REQUEST_QTY,FCC_BY," +
					" TO_CHAR(FCC_DATE,'DD/MM/YYYY') from FMS7_LOA_MST " +
					" where LOA_NO='"+loacd+"' and LOA_REV_NO='"+loarevno+"' AND CUSTOMER_CD='"+CUST_CD.elementAt(p)+"' AND" +
					" Tender_NO='"+tendno+"'";
			//System.out.println("Query String"+queryString);
			rset = stmt1.executeQuery(queryString);		
			if(rset.next())
			{
				fccflg=fccflg+(rset.getString(45)==null?" ":rset.getString(45))+"#";
                //fcc_flag = rset.getString(45)==null?"":rset.getString(45);
			
			}
			
			
			
			
			
			}
			
			vLOA_No.add(vlno);
			vLOA_rev_No.add(vlrevno);
			buyer_cd.add(buyercd);
			 Tender_no.add(tenderno);	
			 vLOA_TCQ.add(vltcq);
				vTCQ_UNIT.add(tcqunit);
				vLOA_RATE.add(vlrate);
				vRATE_UNIT.add(rateunit);
				buyer_nm.add(buyernm);
				vBUYER_ABBR.add(buyerabr);
				vLOA_name.add(vlnm);  
				vLOA_verify.add(vlverify); 
				vLOA_Approve.add(vlapprv);
				vLOA_Ref_No.add(vlrefno);	
				fccflag.add(fccflg);
			
			
			
			}
			//System.out.println("loafccflag no..."+fccflag);

		}
		
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetch_LOA_List2() //ADDED BY MANOJ,Achal
	{
		try 
		{		
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = "SELECT A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ,D.UNIT_ABR,A.RATE," +
				"A.RATE_UNIT,C.CUSTOMER_NAME,C.CUSTOMER_ABBR,A.LOA_NAME,A.VERIFY_FLAG,A.FCC_FLAG,A.LOA_REF_NO " +
				" ,TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY') "+	//RG 20140930
				"from FMS7_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND "+
	            "A.TENDER_NO=B.TENDER_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
	            "A.CUSTOMER_CD='"+bscode+"' AND C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE " +
	            "E.CUSTOMER_CD=A.CUSTOMER_CD) AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
	         //SB20130916   " order by A.customer_cd,A.tender_no,A.loa_no";
				" order by C.customer_NAME,A.tender_no,A.loa_no";
				//System.out.println("LOA:QRY:L001: "+queryString);
			}
			else
			{
				if(Customer_access_flag.equals("Y"))
				{
					/*QUERY MODIFIED TO FETCH DATA HAVING PARTICULAR USER ACCESS */ 
					
					queryString = "SELECT A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ,D.UNIT_ABR,A.RATE,"
							+ "A.RATE_UNIT,C.CUSTOMER_NAME,C.CUSTOMER_ABBR,A.LOA_NAME,A.VERIFY_FLAG,A.FCC_FLAG,"
							+ "A.LOA_REF_NO  ,TO_CHAR(START_DT,'DD/MM/YYYY'),TO_CHAR(END_DT,'DD/MM/YYYY') "
							+ "from FMS7_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D, SEC_EMP_PARTY_ALLOC_MST E "
							+ "WHERE A.FLAG='T' AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B "
							+ "WHERE A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO AND B.FLAG='T' "
							+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND C.EFF_DT=(SELECT MAX(E.EFF_DT) "
							+ "FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) AND A.STATUS='Y' "
							+ "AND A.QUANTITY_UNIT=D.UNIT_CD AND E.PARTY_CD = A.CUSTOMER_CD AND E.PARTY_TYP = 'C' "
							+ "AND E.EMP_CD='"+Emp_cd+"' "
							+ "order by C.customer_NAME,A.tender_no,A.loa_no";
				} else {
					queryString = "SELECT A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ,D.UNIT_ABR,A.RATE," +
							"A.RATE_UNIT,C.CUSTOMER_NAME,C.CUSTOMER_ABBR,A.LOA_NAME,A.VERIFY_FLAG,A.FCC_FLAG,A.LOA_REF_NO " +
							" ,TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY') "+
							"from FMS7_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
							"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND "+
				            "A.TENDER_NO=B.TENDER_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
				            "C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) AND " +
				            "A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
				          //SB20130916  " order by A.customer_cd,A.tender_no,A.loa_no";
							" order by C.customer_NAME,A.tender_no,A.loa_no";
				}
			}			
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{						   
					vLOA_No.add(rset1.getString(1)==null?"":rset1.getString(1));
				   	vLOA_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
				    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
				    Tender_no.add(rset1.getString(4)==null?"":rset1.getString(4));					
					vLOA_TCQ.add(rset1.getString(5)==null?"":rset1.getString(5));
					vTCQ_UNIT.add(rset1.getString(6)==null?"":rset1.getString(6));
				
					vLOA_RATE.add(rset1.getString(7)==null?"":rset1.getString(7));
					vRATE_UNIT.add(rset1.getString(8)==null?"":rset1.getString(8));
					buyer_nm.add(rset1.getString(9)==null?"":rset1.getString(9));
					vBUYER_ABBR.add(rset1.getString(10)==null?"":rset1.getString(10));
				
					vLOA_name.add(rset1.getString(11)==null?"":rset1.getString(11));  
					vLOA_verify.add(rset1.getString(12)==null?"":rset1.getString(12)); 
					vLOA_Approve.add(rset1.getString(13)==null?"":rset1.getString(13));
					String loa_ref_no = rset1.getString(14)==null?"-":rset1.getString(14);	//RG20140930
					if(!loa_ref_no.trim().equals(""))
					{
						vLOA_Ref_No.add(" - ("+loa_ref_no+")");
					}
					else
					{
						vLOA_Ref_No.add("-");	//RG20140930
					}
				 	vSN_approve.add(loa_ref_no);	//RG20140930
				 	
					vSTART_DT.add(rset1.getString(15)==null?"01/01/1900":rset1.getString(15));
					vEND_DT.add(rset1.getString(16)==null?"01/01/1900":rset1.getString(16));
			}
			//System.out.println(".........."+vLOA_Ref_No);
/////////////////SB20130810: For Line Color//////////
			int Start_days_diff=0; int end_days_diff=0;
			for(int i=0;i<buyer_cd.size();i++)
			{
				if(!vSTART_DT.elementAt(i).toString().trim().equals("01/01/1900")) 
				{
				queryString = "SELECT TO_DATE('"+vSTART_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
			
			//	System.out.println("COUNT-Days Difference: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					Start_days_diff = rset.getInt(1);
					if(Start_days_diff<0 )
						VLineFlagStart.add("RED");
					if(Start_days_diff>0 )
						VLineFlagStart.add("GREEN");
					if(Start_days_diff==0 )
						VLineFlagStart.add("BLUE");
				}
				else
				{
					VLineFlagEnd.add("BLACK");
					VLineFlagStart.add("BLACK");
					VSNstatus.add("OPEN");
				}
				queryString = "SELECT TO_DATE('"+vEND_DT.elementAt(i)+"','DD/MM/YYYY')-TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') FROM DUAL";
				
			//	System.out.println("COUNT-Days Difference: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					end_days_diff = rset.getInt(1);
					if(end_days_diff<0)
						VLineFlagEnd.add("RED");
					if(end_days_diff>0)
						VLineFlagEnd.add("GREEN");
					if(end_days_diff==0)
						VLineFlagEnd.add("BLUE");
					if(end_days_diff<0)
						VSNstatus.add("CLOSED");
					else
						VSNstatus.add("OPEN");
				}
				else
				{
					VLineFlagEnd.add("BLACK");
					VLineFlagStart.add("BLACK");
					VSNstatus.add("OPEN");
				}
				}
				else
				{
					VLineFlagEnd.add("BLACK");
					VLineFlagStart.add("BLACK");
					VSNstatus.add("OPEN");
				}
				Start_days_diff=0; end_days_diff=0;
			}
			/*System.out.println("FLAG: "+VSNstatus);
			System.out.println("BUYER: "+buyer_cd.size());
			System.out.println("END: "+VLineFlagEnd.size());
			System.out.println("START: "+VLineFlagStart.size());*/
			//////////////////////////////////////////////////
		}
		
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetch_LOA_List() //ADDED BY MANOJ,Achal
	{
		try 
		{		
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{
				queryString = "SELECT A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ,D.UNIT_ABR,A.RATE," +
				"A.RATE_UNIT,C.CUSTOMER_NAME,C.CUSTOMER_ABBR,A.LOA_NAME,A.VERIFY_FLAG,A.FCC_FLAG,A.LOA_REF_NO "+
				"from FMS7_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND "+
	            "A.TENDER_NO=B.TENDER_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
	            "A.CUSTOMER_CD='"+bscode+"' AND C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE " +
	            "E.CUSTOMER_CD=A.CUSTOMER_CD) AND A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD " +
	            "order by A.customer_cd,A.tender_no,A.loa_no";
			}
			else
			{
				queryString = "SELECT A.LOA_NO,A.LOA_REV_NO,A.CUSTOMER_CD,A.TENDER_NO,A.TCQ,D.UNIT_ABR,A.RATE," +
				"A.RATE_UNIT,C.CUSTOMER_NAME,C.CUSTOMER_ABBR,A.LOA_NAME,A.VERIFY_FLAG,A.FCC_FLAG,A.LOA_REF_NO "+
				"from FMS7_LOA_MST A,FMS7_CUSTOMER_MST C,FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
				"A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE A.LOA_NO=B.LOA_NO AND "+
	            "A.TENDER_NO=B.TENDER_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD) AND A.CUSTOMER_CD=C.CUSTOMER_CD AND " +
	            "C.EFF_DT=(SELECT MAX(E.EFF_DT) FROM FMS7_CUSTOMER_MST E WHERE E.CUSTOMER_CD=A.CUSTOMER_CD) AND " +
	            "A.STATUS='Y' AND A.QUANTITY_UNIT=D.UNIT_CD order by A.customer_cd,A.tender_no,A.loa_no";
			}
			//System.out.println(queryString);
			rset1 = stmt.executeQuery(queryString);
			while(rset1.next())
			{						   
					vLOA_No.add(rset1.getString(1)==null?"":rset1.getString(1));
				   	vLOA_rev_No.add(rset1.getString(2)==null?"":rset1.getString(2));
				    buyer_cd.add(rset1.getString(3)==null?"":rset1.getString(3));
				    Tender_no.add(rset1.getString(4)==null?"":rset1.getString(4));					
					vLOA_TCQ.add(rset1.getString(5)==null?"":rset1.getString(5));
					vTCQ_UNIT.add(rset1.getString(6)==null?"":rset1.getString(6));
				
					vLOA_RATE.add(rset1.getString(7)==null?"":rset1.getString(7));
					vRATE_UNIT.add(rset1.getString(8)==null?"":rset1.getString(8));
					buyer_nm.add(rset1.getString(9)==null?"":rset1.getString(9));
					vBUYER_ABBR.add(rset1.getString(10)==null?"":rset1.getString(10));
				
					vLOA_name.add(rset1.getString(11)==null?"":rset1.getString(11));  
					vLOA_verify.add(rset1.getString(12)==null?"":rset1.getString(12)); 
					vLOA_Approve.add(rset1.getString(13)==null?"":rset1.getString(13));
					String loa_ref_no = rset1.getString(14)==null?"":rset1.getString(14);
					if(!loa_ref_no.trim().equals(""))
					{
						vLOA_Ref_No.add(" - ("+loa_ref_no+")");
					}
					else
					{
						vLOA_Ref_No.add("");
					}
				 	vSN_approve.add(rset1.getString(14)==null?"":rset1.getString(14));
			}
		}
		
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_SN_List() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetchTransporters() 
	{
		try 
		{
		//	queryString = "SELECT NVL(transporter_cd,'0'),NVL(transporter_name,' '),NVL(transporter_abbr,' ') " +
		//				  "FROM FMS7_TRANSPORTER_MST WHERE flag='T' ORDER BY transporter_cd";
			queryString = "SELECT NVL(STATION_CD,'0'),NVL(STATION_NAME,' '),NVL(STATION_ABBR,' ') " +
					  "FROM DLNG_SUPPLY_STATION_MST WHERE flag='Y' ORDER BY STATION_NAME";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					transporter_cd.add(rset.getString(1));
					transporter_nm.add(rset.getString(2));
					transporter_abbr.add(rset.getString(3));
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchFGSA() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetchBuyers_WITH_USER_ACCESS() 
	{
		set_buyer_name = set_buyer_name.trim().toUpperCase();
				
		try 
		{
			queryString = "SELECT DISTINCT(A.customer_cd),NVL(A.customer_name,' '),NVL(A.customer_abbr,' ') " +
						  "FROM FMS7_CUSTOMER_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B "
						  + "WHERE A.flag='T' AND UPPER(TRIM(A.customer_name)) LIKE '"+set_buyer_name+"%' " +
						  "AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND B.EFF_DT<=TO_DATE('"+sign_date+"','DD/MM/YYYY')) "
						  	+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' " +
						  	"and (A.DORMANT_FLAG!='Y' OR A.DORMANT_FLAG IS NULL) " +
						  "ORDER BY A.customer_cd";
			//System.out.println("Customer List Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					buyer_cd.add(rset.getString(1));
					buyer_nm.add(rset.getString(2));
					buyer_abbr.add(rset.getString(3));
				}
			}
			
			for(int i=0;i<buyer_cd.size();i++)
			{
				int count = 0;
				Vector plant_seq = new Vector();
				Vector plant_nm = new Vector();
				Vector plant_type = new Vector();
				Vector temp_plant_seq = new Vector();
				Vector temp_plant_nm = new Vector();
				Vector temp_plant_type = new Vector();				
				temp_plant_nm.add("");
				temp_plant_type.add("");
				temp_plant_seq.add("0");
				
				queryString = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE " +
				  			  "A.customer_cd="+buyer_cd.elementAt(i)+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd="+buyer_cd.elementAt(i)+" AND " +
				  			  "B.seq_no=A.seq_no AND B.eff_dt<=TO_DATE('"+sign_date+"','DD/MM/YYYY')) " +
				  			  "ORDER BY A.seq_no";
				//System.out.println("Customer Plant Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
					{
						plant_seq.add(rset.getString(1));
						plant_type.add(rset.getString(2));
						plant_nm.add(rset.getString(3));
						++count;
					}
				}
				if(count==0)
				{
					buyer_plant_seq_no.add(temp_plant_seq);
					buyer_plant_type.add(temp_plant_type);
					buyer_plant_nm.add(temp_plant_nm);
				}
				else
				{
					buyer_plant_seq_no.add(plant_seq);
					buyer_plant_type.add(plant_type);
					buyer_plant_nm.add(plant_nm);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchAllClauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetchBuyers() 
	{
		set_buyer_name = set_buyer_name.trim().toUpperCase();
				
		try 
		{
			queryString = "SELECT (A.customer_cd),NVL(A.customer_name,' '),NVL(A.customer_abbr,' ') " +
						  "FROM FMS7_CUSTOMER_MST A WHERE A.flag='T' AND UPPER(TRIM(A.customer_name)) LIKE '"+set_buyer_name+"%' " +
						  "AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND B.EFF_DT<=TO_DATE('"+sign_date+"','DD/MM/YYYY')) " +
						   "and (A.DORMANT_FLAG!='Y' OR A.DORMANT_FLAG IS NULL) " +
						  "ORDER BY A.customer_name";
		//	System.out.println("Customer List Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					buyer_cd.add(rset.getString(1));
					buyer_nm.add(rset.getString(2));
					buyer_abbr.add(rset.getString(3));
				}
			}
			
			for(int i=0;i<buyer_cd.size();i++)
			{
				int count = 0;
				Vector plant_seq = new Vector();
				Vector plant_nm = new Vector();
				Vector plant_type = new Vector();
				Vector temp_plant_seq = new Vector();
				Vector temp_plant_nm = new Vector();
				Vector temp_plant_type = new Vector();				
				temp_plant_nm.add("");
				temp_plant_type.add("");
				temp_plant_seq.add("0");
				
				queryString = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE " +
				  			  "A.customer_cd="+buyer_cd.elementAt(i)+" AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
				  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd="+buyer_cd.elementAt(i)+" AND " +
				  			  "B.seq_no=A.seq_no AND B.eff_dt<=TO_DATE('"+sign_date+"','DD/MM/YYYY')) " +
				  			  "ORDER BY A.seq_no";
				//System.out.println("Customer Plant Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
					{
						plant_seq.add(rset.getString(1));
						plant_type.add(rset.getString(2));
						plant_nm.add(rset.getString(3));
						++count;
					}
				}
				if(count==0)
				{
					buyer_plant_seq_no.add(temp_plant_seq);
					buyer_plant_type.add(temp_plant_type);
					buyer_plant_nm.add(temp_plant_nm);
				}
				else
				{
					buyer_plant_seq_no.add(plant_seq);
					buyer_plant_type.add(plant_type);
					buyer_plant_nm.add(plant_nm);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchAllClauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetchAllClauses() 
	{
		try 
		{
			queryString = "SELECT NVL(clause_cd,'0'),NVL(clause_nm,' '),NVL(clause_abbr,' '),NVL(mst_clause_cd,'0') " +
						  "FROM FMS7_CLAUSE_MST WHERE flag='Y' ORDER BY clause_cd";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					clause_cd.add(rset.getString(1));
					clause_nm.add(rset.getString(2));
					clause_abbr.add(rset.getString(3));
					clause_mst_cd.add(rset.getString(4));
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchAllClauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetchAllClausesForSNDefination() 
	{
		try 
		{
			queryString = "SELECT NVL(clause_cd,'0'),NVL(clause_nm,' '),NVL(clause_abbr,' '),NVL(mst_clause_cd,'0') " +
						  "FROM FMS7_CLAUSE_MST WHERE flag='Y' AND mst_clause_cd=0 ORDER BY clause_cd";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					clause_cd.add(rset.getString(1));
					clause_nm.add(rset.getString(2));
					clause_abbr.add(rset.getString(3));
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchAllClauses() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	String flag = "", rev_no = "";
	boolean contractEndFlag = false;
	String REOPEN_REQUEST_FLAG = "N", REOPEN_APPROVAL_FLAG = "";
	
	public void fetchFLSAData() 
	{
		try 
		{
			//Get Data from  FMS7_FGSA_MST
			if(flag.equals("R")) {
				queryString ="Select to_char(SIGNING_DT,'dd/mm/yyyy'), to_char(START_DT,'dd/mm/yyyy'), " +
						"to_char(END_DT,'dd/mm/yyyy'), FLSA_BASE, FLSA_TYPE, STATUS, BUYER_NOM, BUYER_MONTH_NOM, " +
						"BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
						"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, " +
						"MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, " +
						"OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY,REV_NO, " +
						"to_char(RENEWAL_DT,'dd/mm/yyyy'), to_char(REV_DT,'dd/mm/yyyy'),REMARKS, " +
						"BUYER_NOM_CLAUSE,SELLER_NOM_CLAUSE,LC_CLAUSE,BILLING_CLAUSE,LIABILITY_CLAUSE,PRE_APPROVAL,"
					    + "REOPEN_REQUEST_FLAG,NVL(REOPEN_APPROVAL_FLAG,'') " +
						"FROM " +
						"DLNG_FLSA_MST WHERE CUSTOMER_CD='"+bscode+"' and  FLSA_NO='"+FGSA_cd+"' and FLAG='Y' AND " +
						"REV_NO='"+rev_no+"' ";
			} else {
			queryString ="Select to_char(SIGNING_DT,'dd/mm/yyyy'), to_char(START_DT,'dd/mm/yyyy'), " +
						"to_char(END_DT,'dd/mm/yyyy'), FLSA_BASE, FLSA_TYPE, STATUS, BUYER_NOM, BUYER_MONTH_NOM, " +
						"BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
						"SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, " +
						"MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, " +
						"OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY,REV_NO, " +
						"to_char(RENEWAL_DT,'dd/mm/yyyy'), to_char(REV_DT,'dd/mm/yyyy'),REMARKS, " +
						"BUYER_NOM_CLAUSE,SELLER_NOM_CLAUSE,LC_CLAUSE,BILLING_CLAUSE,LIABILITY_CLAUSE,PRE_APPROVAL"
						+ ",REOPEN_REQUEST_FLAG,NVL(REOPEN_APPROVAL_FLAG,'') " +
						//"," +
						//"ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG " +
						"FROM " +
						"DLNG_FLSA_MST WHERE CUSTOMER_CD='"+bscode+"' and  FLSA_NO='"+FGSA_cd+"' and FLAG='Y' AND " +
						"REV_NO=(select max(rev_no) from DLNG_FLSA_MST where CUSTOMER_CD='"+bscode+"' AND " +
						"FLSA_NO='"+FGSA_cd+"' and FLAG='Y') ";
			}
			//System.out.println("fetch FGSAData Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				  SIGNING_DT = rset.getString(1)==null?"":rset.getString(1);
				  START_DT = rset.getString(2)==null?"":rset.getString(2);
				  END_DT = rset.getString(3)==null?"":rset.getString(3);
				  FGSA_BASE = rset.getString(4)==null?"":rset.getString(4);
				  FGSA_TYPE = rset.getString(5)==null?"":rset.getString(5);
				  STATUS = rset.getString(6)==null?"":rset.getString(6);
				  BUYER_NOM=rset.getString(7)==null?"":rset.getString(7);
				  BUYER_MONTH_NOM = rset.getString(8)==null?"":rset.getString(8);
				  BUYER_WEEK_NOM = rset.getString(9)==null?"":rset.getString(9);
				  BUYER_DAILY_NOM = rset.getString(10)==null?"":rset.getString(10);
				  SELLER_NOM = rset.getString(11)==null?"":rset.getString(11);
				  SELLER_MONTH_NOM = rset.getString(12)==null?"":rset.getString(12);
				  SELLER_WEEK_NOM = rset.getString(13)==null?"":rset.getString(13);
				  SELLER_DAILY_NOM = rset.getString(14)==null?"":rset.getString(14);
				  DAY_DEF = rset.getString(15)==null?"":rset.getString(15);
				  DAY_START_TIME = rset.getString(16)==null?"":rset.getString(16);
				  DAY_END_TIME = rset.getString(17)==null?"":rset.getString(17);
				  MDCQ = rset.getString(18)==null?"":rset.getString(18);
				  MDCQ_PERCENTAGE = rset.getString(19)==null?"":rset.getString(19);
				  MEASUREMENT = rset.getString(20)==null?"":rset.getString(20);
				  MEAS_STANDARD = rset.getString(21)==null?"":rset.getString(21);
				  MEAS_TEMPERATURE = rset.getString(22)==null?"":rset.getString(22);
				  PRESSURE_MIN_BAR = rset.getString(23)==null?"":rset.getString(23);
				  PRESSURE_MAX_BAR = rset.getString(24)==null?"":rset.getString(24);
				  OFF_SPEC_GAS = rset.getString(25)==null?"":rset.getString(25);
				  SPEC_GAS_ENERGY_BASE = rset.getString(26)==null?"0":rset.getString(26);
				  SPEC_GAS_MIN_ENERGY = rset.getString(27)==null?"":rset.getString(27);
				  SPEC_GAS_MAX_ENERGY = rset.getString(28)==null?"":rset.getString(28);
				  REV_NO = rset.getString(29)==null?"0":rset.getString(29);
				  RENEWAL_DT = rset.getString(30)==null?"":rset.getString(30);
				  REV_DT = rset.getString(31)==null?"":rset.getString(31);
				  REMARK = rset.getString(32)==null?"":rset.getString(32);
				  BUYER_NOM_CLAUSE = rset.getString(33)==null?"":rset.getString(33);
				  SELLER_NOM_CLAUSE = rset.getString(34)==null?"":rset.getString(34);
				  LC_CLAUSE = rset.getString(35)==null?"":rset.getString(35);
				  BILLING_CLAUSE = rset.getString(36)==null?"":rset.getString(36);
				  LIABILITY_CLAUSE = rset.getString(37)==null?"":rset.getString(37);
				  PRE_APPROVAL = rset.getString(38)==null?"":rset.getString(38);
				  REOPEN_REQUEST_FLAG = rset.getString(39)==null?"":rset.getString(39);
				  REOPEN_APPROVAL_FLAG = rset.getString(40)==null?"":rset.getString(40);
				 // ADVANCE_ADJUST_CLAUSE = rset.getString(38)==null?"N":rset.getString(38);
				//  ADJUST_FLAG = rset.getString(39)==null?"N":rset.getString(39);
				  
				  //Fetch Whether Contract Ended...
				  queryString = "select sysdate - to_date('"+END_DT+"','dd/mm/yyyy') from dual";
				  rset = stmt.executeQuery(queryString);
				  if(rset.next()) {
					  int data = rset.getInt(1);
					  if(data>0) {
						  contractEndFlag = true;
					  }
				  }
			}
			// Fetch Buyer Name nd Abbr
			queryString ="Select CUSTOMER_NAME, CUSTOMER_ABBR from FMS7_CUstomer_MST where " +
					"CUSTOMER_CD='"+bscode+"' and FLAG='T'";
			//System.out.println("fetch FGSA_TRANSPORTER_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				  bName = rset.getString(1);
				  bAbbr = rset.getString(2);
			}			
			//FMS7_FGSA_TRANSPORTER_MST
			queryString ="Select TRANSPORTER_CD from DLNG_FLSA_TRANSPORTER_MST where CUSTOMER_CD='"+bscode+"' and  FLSA_NO='"+FGSA_cd+"' and FLAG='Y' and REV_NO = (select max(rev_no) from DLNG_FLSA_MST where CUSTOMER_CD='"+bscode+"' and  FLSA_NO='"+FGSA_cd+"' and FLAG='Y') ";
			//System.out.println("fetch FGSA_TRANSPORTER_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vTRANSPORTER_CD.add(rset.getString(1));
			}
			//FMS7_FGSA_PLANT_MST
			queryString ="Select PLANT_SEQ_NO from DLNG_FLSA_PLANT_MST where CUSTOMER_CD='"+bscode+"' and  FLSA_NO='"+FGSA_cd+"' and FLAG='Y' and REV_NO = (select max(rev_no) from DLNG_FLSA_MST where CUSTOMER_CD='"+bscode+"' and  FLSA_NO='"+FGSA_cd+"' and FLAG='Y') ";
			//System.out.println("fetch FGSA_PLANT_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vPLANT_SEQ_NO.add(rset.getString(1));
			}
			//FMS7_FGSA_CLAUSE_MST
			queryString ="Select CLAUSE_CD, REMARK from DLNG_FLSA_CLAUSE_MST where BUYER_CD='"+bscode+"' and  FLSA_NO='"+FGSA_cd+"' and FLAG='Y' and REV_NO = (select max(rev_no) from DLNG_FLSA_MST where CUSTOMER_CD='"+bscode+"' and  FLSA_NO='"+FGSA_cd+"' and FLAG='Y') ";
			System.out.println("fetch FGSA_CLAUSE_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vCLAUSE_CD.add(rset.getString(1));
				  vREMARK.add(rset.getString(1));
			}
			// Fetch Delivery point For Buyer
			int count = 0;
			Vector plant_seq = new Vector();
			Vector plant_nm = new Vector();
			Vector plant_type = new Vector();
			Vector temp_plant_seq = new Vector();
			Vector temp_plant_nm = new Vector();
			Vector temp_plant_type = new Vector();
			temp_plant_nm.add("");
			temp_plant_type.add("");
			temp_plant_seq.add("0");
			
			queryString = "SELECT DISTINCT(A.seq_no) " +
			  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.flag='T' " +
			  			  "AND A.customer_cd = "+bscode+" " +
			  			  //"AND A.eff_dt = (SELECT MAX(B.eff_dt) " +
			  			  //"FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd = "+bscode+" AND " +
			  			  //"B.seq_no=A.seq_no AND B.eff_dt <= TO_DATE('"+SIGNING_DT+"','DD/MM/YYYY')) " +
			  			  "ORDER BY A.seq_no";
		//	System.out.println("SAMIK --> Buyer Plant Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{				
					plant_seq.add(rset.getString(1));					
					queryString1 = "SELECT NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
		  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.flag='T' " +
		  			  "AND A.customer_cd = "+bscode+" AND A.seq_no='"+rset.getString(1)+"' " +
		  			  "AND A.eff_dt = (SELECT MAX(B.eff_dt) " +
		  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd = "+bscode+" AND " +
		  			  "B.seq_no=A.seq_no ) ";
					//System.out.println("plant_type Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						plant_type.add(rset1.getString(1));
						plant_nm.add(rset1.getString(2));
					}
					else
					{
						plant_type.add(" ");
						plant_nm.add(" ");
					}
										
					++count;
			}
			if(count==0)
			{
				buyer_plant_seq_no.add(temp_plant_seq);
				buyer_plant_type.add(temp_plant_type);
				buyer_plant_nm.add(temp_plant_nm);
			}
			else
			{
				buyer_plant_seq_no.add(plant_seq);
				buyer_plant_type.add(plant_type);
				buyer_plant_nm.add(plant_nm);
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchFGSAData() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
//	==
	public void fetch_Re_Gas_Billing_Dtl()
	{
		try
		{
			queryString = "select * from FMS7_RE_GAS_BILLING_DTL  WHERE RE_GAS_NO='"+FGSA_cd+"' And RE_GAS_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"' And CONT_TYPE='F'";
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				Freq=rset.getString(5)==null?"":rset.getString(5);
				Due_Date=rset.getString(6)==null?"":rset.getString(6);
				Int_Cal_Cd=rset.getString(7)==null?"":rset.getString(7);
				Int_Cal_Sign=rset.getString(8)==null?"":rset.getString(8);
				Int_Cal_Per=rset.getString(9)==null?"":rset.getString(9);
				Exchng_Rate_Cd=rset.getString(10)==null?"":rset.getString(10);
				Exchng_Rate_Cal=rset.getString(11)==null?"":rset.getString(11);
				Invoice_Cur_Cd=rset.getString(12)==null?"":rset.getString(12);
				Payment_Cur_Cd=rset.getString(13)==null?"":rset.getString(13);
				Tax_Struct_Cd=rset.getString(14)==null?"":rset.getString(14);
				Exchg_Rate_Note=rset.getString(18)==null?"":rset.getString(18);
				inv_criteria=rset.getString(19)==null?"":rset.getString(19);
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_Billing_Dtl --> "+e.getMessage());
			e.printStackTrace();
		}
	}
				
	public void fetch_LC_RE_GAS_ListOLD() 
	{			
		try 
		{		
			
			//System.out.println("From fetch_LC_RE_GAS_List >>>>>>>>>>");
				queryString = "SELECT A.CUSTOMER_CD,A.RE_GAS_NO,A.REV_NO," +
							"to_char(A.START_DT,'dd/mm/yyyy'), " +
							"to_char(A.END_DT,'dd/mm/yyyy')," +
							"A.CAPACITY,A.STATUS,to_char(A.REV_DT,'dd/mm/yyyy'),A.NO_OF_CARGO " +
							" FROM FMS7_RE_GAS_MST A where A.Customer_cd='"+Buyer_cd+"' AND " +
							" A.END_DT >= to_date('"+lc_gen_dt+"','dd/mm/yyyy') " +
							"and A.REV_NO IN (SELECT MAX(B.REV_NO) FROM FMS7_RE_GAS_MST B WHERE  B.RE_GAS_NO = A.RE_GAS_NO AND B.Customer_cd = A.Customer_cd )" +
							" order by A.RE_GAS_NO asc";
				//System.out.println("LC_RE_GAS_LIST Fetch DATA Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while (rset.next())
				{
					LC_REGAS_NO.add(rset.getString(2)==null?"":rset.getString(2));
					LC_REGAS_REV_NO.add(rset.getString(3)==null?"":rset.getString(3));
					LC_REGAS_STR_DT.add(rset.getString(4)==null?"":rset.getString(4));
					LC_REGAS_END_DT.add(rset.getString(5)==null?"":rset.getString(5));					
					LC_REGAS_CAPACITY.add(rset.getString(6)==null?"":rset.getString(6));
				}
				/*System.out.println();
				System.out.println(LC_REGAS_NO);
				System.out.println(LC_REGAS_STR_DT);
				System.out.println(LC_REGAS_END_DT);
				System.out.println();*/
				
				for(int i=0; i<LC_REGAS_NO.size(); i++)
				{
					queryString = "SELECT A.CUSTOMER_CD,A.FINANCIAL_YEAR,A.LC_SEQ_NO," +
								"A.TCQ,A.FLAG " +
								" FROM FMS7_LC_DTL A where A.Customer_cd='"+Buyer_cd+"' AND " +
								" A.SN_NO = '"+LC_REGAS_NO.elementAt(i)+"' AND A.SN_REV_NO = '"+LC_REGAS_REV_NO.elementAt(i)+"' AND" +
								" A.SN_END_DATE = to_date('"+LC_REGAS_END_DT.elementAt(i)+"','dd/mm/yyyy') AND " +
								" A.SN_START_DATE = to_date('"+LC_REGAS_STR_DT.elementAt(i)+"','dd/mm/yyyy') AND " +
								" (A.FLAG = 'R' OR A.FLAG = 'r')";
					//System.out.println("LC_SEQ_NO Fetch DATA Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						LC_SEQ_NO_REGAS.add(rset.getString(3)==null?"":rset.getString(3));
						LC_FIN_YEAR_REGAS.add(rset.getString(2)==null?"":rset.getString(2));
						LC_FLG_REGAS.add(rset.getString(5)==null?"":rset.getString(5));
						//System.out.println("TCQ >>>>> capacity >>>>> "+rset.getString(4)==null?"":rset.getString(4));
					}
					else
					{
						LC_SEQ_NO_REGAS.add("-");
						LC_FIN_YEAR_REGAS.add("-");
						LC_FLG_REGAS.add("-");
					}
				}
				
				for(int i=0; i<LC_SEQ_NO_REGAS.size(); i++)
				{
					if(!LC_SEQ_NO_REGAS.elementAt(i).toString().equals("-"))
					{
						queryString = "SELECT to_char(A.START_DATE,'dd/mm/yyyy'), " +
									"to_char(A.END_DATE,'dd/mm/yyyy')," +
									"A.FINAL_LC_AMOUNT,A.REMARKS" +
									" FROM FMS7_LC_MST A where A.Customer_cd='"+Buyer_cd+"' AND " +
									" A.LC_SEQ_NO = '"+LC_SEQ_NO_REGAS.elementAt(i)+"' AND " +
									" A.FINANCIAL_YEAR = '"+LC_FIN_YEAR_REGAS.elementAt(i)+"' AND " +
									" (A.FLAG = 'R' OR A.FLAG = 'r')";
						
						//System.out.println("LC_SEQ_NO Fetch DATA Query 2 = "+queryString);
						rset = stmt.executeQuery(queryString);
						
						if(rset.next())
						{
							LC_STR_DT_REGAS.add(rset.getString(1)==null?"":rset.getString(1)); 
							LC_END_DT_REGAS.add(rset.getString(2)==null?"":rset.getString(2));
							LC_FINAL_AMOUNT_REGAS.add(rset.getString(3)==null?"":rset.getString(3));
							LC_REMARKS_REGAS.add(rset.getString(4)==null?"":rset.getString(4));
						}
						else
						{
							LC_STR_DT_REGAS.add("-"); 
							LC_END_DT_REGAS.add("-");
							LC_FINAL_AMOUNT_REGAS.add("-");
							LC_REMARKS_REGAS.add("-");
						}
					}
					else
					{
						LC_STR_DT_REGAS.add("-"); 
						LC_END_DT_REGAS.add("-");
						LC_FINAL_AMOUNT_REGAS.add("-");
						LC_REMARKS_REGAS.add("-");
					}
				}
				/**/
			/*	System.out.println(">>>>>>>>>>>>>>> milan");
				System.out.println(LC_SEQ_NO_REGAS);
				System.out.println(LC_FIN_YEAR_REGAS);
				System.out.println(LC_FLG_REGAS);
				System.out.println(LC_STR_DT_REGAS);
				System.out.println(LC_END_DT_REGAS);
				System.out.println(LC_FINAL_AMOUNT_REGAS);
				System.out.println(LC_REMARKS_REGAS);
				System.out.println(">>>>>>>>>>>>>>> milan");*/
							
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_RE_GAS_List()--> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetch_name2()
	{		
		try 
		{	
			queryString = "select customer_name from FMS7_CUSTOMER_MST where customer_cd='"+Buyer_cd+"'";
			//System.out.println("query..."+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				buyernm=rset.getString(1);
				
			}
			
			
//			queryString1 = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
//			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE " +
//			  "A.customer_cd='"+Buyer_cd+"' AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
//			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd='"+Buyer_cd+"' AND " +
//			  "B.seq_no=A.seq_no AND B.eff_dt<=TO_DATE(sysdate,'DD/MM/YYYY')) " +
//			  "ORDER BY A.seq_no";
			
			queryString1 = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') "+
			"FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.customer_cd='"+Buyer_cd+"' AND A.eff_dt=(SELECT MAX(B.eff_dt) "+
			"FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd='"+Buyer_cd+"' AND B.seq_no=A.seq_no "+
			"AND to_date(B.eff_dt,'dd/mm/yyyy')<=TO_DATE(sysdate,'DD/MM/YYYY')) "+
			"ORDER BY A.seq_no";
//System.out.println("Customer Plant Fetch Query =123 "+queryString1);
rset1 = stmt1.executeQuery(queryString1);
while(rset1.next())
{
	
		 plantseq1=plantseq1+rset1.getString(1)+"#";;
		planttype1=planttype1+rset1.getString(2)+"#";
		plantnm1=plantnm1+rset1.getString(3)+"#";
			
}	
			plantseq.add(plantseq1);
			plnttype.add(planttype1);
			plntnm.add(plantnm1);
		//System.out.println("..plantseq1.."+plantseq1);
		//System.out.println("..planttype1.."+planttype1);
		//System.out.println("..plantnm1.."+plantnm1);
		
			
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_name()--> "+e.getMessage());
			e.printStackTrace();
		}
	}

	
	public void fetchRE_GAS1() 
	{			
		try 
		{	String buyercd="";
		String fgsano="";
		String revno="";
		String startdt="";
		String enddt="";
		String fgsabase="";
		String status="";
		
		String revdt="";
		String cargo="";
		String buyernm="";
		//System.out.println("..CUST_CD.size()..."+CUST_CD.size());
			if(CUST_CD.size()>0)
			{
			for(int j=0;j<CUST_CD.size();j++)
			{
				
				buyercd="";
				fgsano="";
				revno="";
				startdt="";
				enddt="";
				fgsabase="";
				status="";
				cargo="";
				
				revdt="";
				buyernm="";
				bscode=CUST_CD.elementAt(j).toString();
			//System.out.println("..bscode..."+bscode);
				
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{				
				queryString="SELECT DISTINCT CUSTOMER_CD,RE_GAS_NO FROM FMS7_RE_GAS_MST  Where customer_cd='"+bscode+"' ORDER BY CUSTOMER_CD,RE_GAS_NO";
			}
			else
			{
				queryString="SELECT DISTINCT CUSTOMER_CD,RE_GAS_NO FROM FMS7_RE_GAS_MST ORDER BY CUSTOMER_CD,RE_GAS_NO";
			}			
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				
				
				
				
				if(!rset.getString(1).equals(" ") && !rset.getString(2).equals(" "))
				{
					buyercd=buyercd+rset.getString(1)+"#";
					fgsano=fgsano+rset.getString(2)+"#";
					//buyer_cd.add(rset.getString(1));
					//FGSA_no.add(rset.getString(2));
					queryString1 ="select max(rev_no) from FMS7_RE_GAS_MST  where Customer_cd='"+rset.getString(1)+"' and Re_Gas_NO='"+rset.getString(2)+"'";
					//System.out.println("FGSA REV NO Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					String tmp="";
					if(rset1.next())
					{  revno=revno+(rset1.getString(1)==null?"0":rset1.getString(1))+"#";
					tmp=rset1.getString(1)==null?"0":rset1.getString(1);	
					//vRev_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
					}
					else
					{
						 revno=revno+"0"+"#";
							tmp="0";
						//vRev_No.add("0");
					}
					queryString = "SELECT CUSTOMER_CD,RE_GAS_NO,to_char(START_DT,'dd/mm/yyyy'), to_char(END_DT,'dd/mm/yyyy')," +
					  "RE_GAS_BASE,STATUS, to_char(REV_DT,'dd/mm/yyyy'), NO_OF_CARGO " +
					  "FROM FMS7_RE_GAS_MST where Customer_cd='"+rset.getString(1)+"' AND " +
					  "RE_GAS_NO='"+rset.getString(2)+"' AND REV_NO='"+tmp+"'";
		//System.out.println("RE_GAS Fetch DATA Query = "+queryString);
		rset2 = stmt2.executeQuery(queryString);
		if(rset2.next())
		{
			startdt=startdt+(rset2.getString(3)==null?"":rset2.getString(3))+"#";
			enddt=enddt+(rset2.getString(4)==null?"":rset2.getString(4))+"#";
			fgsabase=fgsabase+((rset2.getString(5)==null?"":rset2.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"))+"#";
			status=status+((rset2.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"))+"#";
			revdt=revdt+(rset2.getString(7)==null?"":rset2.getString(7))+"#";
			cargo=cargo+(rset2.getString(8)==null?"1":rset2.getString(8))+"#";
			
			
//			
//			vSTART_DT.add(rset2.getString(3)==null?"":rset2.getString(3));
//			vEND_DT.add(rset2.getString(4)==null?"":rset2.getString(4));
//			vFGSA_BASE.add((rset2.getString(5)==null?"":rset2.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"));					
//			vSTATUS.add((rset2.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"));
//			vREV_DT.add(rset2.getString(7)==null?"":rset2.getString(7));
//			NO_OF_CARGO.add(rset2.getString(8)==null?"1":rset2.getString(8));
		}
		else
		{
			startdt=startdt+" "+"#";
			enddt=enddt+" "+"#";
			fgsabase=fgsabase+"Ex-Terminal"+"#";
			status=status+"Active"+"#";
			revdt=revdt+" "+"#";
			cargo=cargo+"1"+"#";
			
//			vSTART_DT.add("");
//			vEND_DT.add("");
//			vFGSA_BASE.add("Ex-Terminal");					
//			vSTATUS.add("Active");
//			vREV_DT.add("");
//			NO_OF_CARGO.add("1");
		}
					
		queryString ="Select nvl(Customer_name,'') from FMS7_Customer_mst where Customer_cd='"+rset.getString(1)+"'";
		//System.out.println("Re-Gas Buyer Name Fetch Query  = "+queryString);
		rset3 = stmt3.executeQuery(queryString);
		if(rset3.next())
		{
			buyernm=buyernm+(rset3.getString(1)==null?"":rset3.getString(1))+"#";
			//buyer_nm.add(rset3.getString(1)==null?"":rset3.getString(1));
		}
		else
		{
			buyernm=buyernm+" "+"#";
			//buyer_nm.add("");
		}	
					
				}
			}
			//start
			buyer_cd.add(buyercd);
			FGSA_no.add(fgsano);
			vRev_No.add(revno);
			vSTART_DT.add(startdt);
			vEND_DT.add(enddt);
			vFGSA_BASE.add(fgsabase);
			vSTATUS.add(status);
			vREV_DT.add(revdt);
			NO_OF_CARGO.add(cargo);
			buyer_nm.add(buyernm);
			}
			}
			//System.out.println("..buyer_cd. java..."+buyer_cd);
			
			
			//end
//			for(int i=0;i<FGSA_no.size();i++)
//			{
//				queryString = "SELECT CUSTOMER_CD,RE_GAS_NO,to_char(START_DT,'dd/mm/yyyy'), to_char(END_DT,'dd/mm/yyyy')," +
//							  "RE_GAS_BASE,STATUS, to_char(REV_DT,'dd/mm/yyyy'), NO_OF_CARGO " +
//							  "FROM FMS7_RE_GAS_MST where Customer_cd='"+buyer_cd.elementAt(i)+"' AND " +
//							  "RE_GAS_NO='"+FGSA_no.elementAt(i)+"' AND REV_NO='"+vRev_No.elementAt(i)+"'";
//				System.out.println("RE_GAS Fetch DATA Query = "+queryString);
//				rset = stmt.executeQuery(queryString);
//				if(rset.next())
//				{
//					vSTART_DT.add(rset.getString(3)==null?"":rset.getString(3));
//					vEND_DT.add(rset.getString(4)==null?"":rset.getString(4));
//					vFGSA_BASE.add((rset.getString(5)==null?"":rset.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"));					
//					vSTATUS.add((rset.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"));
//					vREV_DT.add(rset.getString(7)==null?"":rset.getString(7));
//					NO_OF_CARGO.add(rset.getString(8)==null?"1":rset.getString(8));
//				}
//				else
//				{
//					vSTART_DT.add("");
//					vEND_DT.add("");
//					vFGSA_BASE.add("Ex-Terminal");					
//					vSTATUS.add("Active");
//					vREV_DT.add("");
//					NO_OF_CARGO.add("1");
//				}
//			}
//			for(int i=0;i<buyer_cd.size();i++)
//			{
//				queryString ="Select nvl(Customer_name,'') from FMS7_Customer_mst where Customer_cd='"+buyer_cd.elementAt(i)+"'";
//				System.out.println("Re-Gas Buyer Name Fetch Query  = "+queryString);
//				rset = stmt.executeQuery(queryString);
//				if(rset.next())
//				{
//					buyer_nm.add(rset.getString(1)==null?"":rset.getString(1));
//				}
//				else
//				{
//					buyer_nm.add("");
//				}
//			}
//			}
//			
//			//here
//		}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchRE_GAS()--> "+e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	public void fetchRE_GAS() 
	{			
		try 
		{		
			if(bscode!=null && !bscode.trim().equals("") && !bscode.trim().equals("0"))
			{				
				queryString="SELECT DISTINCT CUSTOMER_CD,RE_GAS_NO FROM FMS7_RE_GAS_MST "
						+ " Where customer_cd='"+bscode+"' ORDER BY CUSTOMER_CD,RE_GAS_NO";
			}
			else
			{
				if(Customer_access_flag.equals("Y"))
				{
					queryString="SELECT DISTINCT A.CUSTOMER_CD,RE_GAS_NO FROM FMS7_RE_GAS_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B  "
							+ "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' "
							+ "ORDER BY A.CUSTOMER_CD,RE_GAS_NO";
				} else {
					queryString="SELECT DISTINCT CUSTOMER_CD,RE_GAS_NO FROM FMS7_RE_GAS_MST A "
							+ " "
							+ "ORDER BY CUSTOMER_CD,RE_GAS_NO";
				}
			}			
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(1).equals(" ") && !rset.getString(2).equals(" "))
				{
					buyer_cd.add(rset.getString(1));
					FGSA_no.add(rset.getString(2));
					queryString1 ="select max(rev_no) from FMS7_RE_GAS_MST  where Customer_cd='"+rset.getString(1)+"' and Re_Gas_NO='"+rset.getString(2)+"'";
					//System.out.println("FGSA REV NO Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						vRev_No.add(rset1.getString(1)==null?"0":rset1.getString(1));
					}
					else
					{
						vRev_No.add("0");
					}
				}
			}			
			for(int i=0;i<FGSA_no.size();i++)
			{
				queryString = "SELECT CUSTOMER_CD,RE_GAS_NO,to_char(START_DT,'dd/mm/yyyy'), to_char(END_DT,'dd/mm/yyyy')," +
							  "RE_GAS_BASE,STATUS, to_char(REV_DT,'dd/mm/yyyy'), NO_OF_CARGO " +
							  "FROM FMS7_RE_GAS_MST where Customer_cd='"+buyer_cd.elementAt(i)+"' AND " +
							  "RE_GAS_NO='"+FGSA_no.elementAt(i)+"' AND REV_NO='"+vRev_No.elementAt(i)+"'";
				//System.out.println("RE_GAS Fetch DATA Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vSTART_DT.add(rset.getString(3)==null?"":rset.getString(3));
					vEND_DT.add(rset.getString(4)==null?"":rset.getString(4));
					vFGSA_BASE.add((rset.getString(5)==null?"":rset.getString(5).equalsIgnoreCase("X")?"Ex-Terminal":"Delivery"));					
					vSTATUS.add((rset.getString(6).equalsIgnoreCase("Y")?"Active":"Inactive"));
					vREV_DT.add(rset.getString(7)==null?"":rset.getString(7));
					NO_OF_CARGO.add(rset.getString(8)==null?"1":rset.getString(8));
				}
				else
				{
					vSTART_DT.add("");
					vEND_DT.add("");
					vFGSA_BASE.add("Ex-Terminal");					
					vSTATUS.add("Active");
					vREV_DT.add("");
					NO_OF_CARGO.add("1");
				}
			}
			for(int i=0;i<buyer_cd.size();i++)
			{
				queryString ="Select nvl(Customer_name,'') from FMS7_Customer_mst where Customer_cd='"+buyer_cd.elementAt(i)+"'";
				//System.out.println("Re-Gas Buyer Name Fetch Query  = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					buyer_nm.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					buyer_nm.add("");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchRE_GAS()--> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void fetchRE_GASData() 
	{
		try 
		{
		  //Get Data from  FMS7_RE_GAS_MST 
		  //System.out.println("RE_GAS_cd : "+FGSA_cd);
		  queryString ="Select to_char(SIGNING_DT,'dd/mm/yyyy'), to_char(START_DT,'dd/mm/yyyy'), " +
					   "to_char(END_DT,'dd/mm/yyyy'), RE_GAS_BASE, STATUS, BUYER_NOM, BUYER_MONTH_NOM, " +
					   "BUYER_WEEK_NOM, BUYER_DAILY_NOM, SELLER_NOM, SELLER_MONTH_NOM, SELLER_WEEK_NOM, " +
					   "SELLER_DAILY_NOM, DAY_DEF, DAY_START_TIME, DAY_END_TIME, MDCQ, MDCQ_PERCENTAGE, " +
					   "MEASUREMENT, MEAS_STANDARD, MEAS_TEMPERATURE, PRESSURE_MIN_BAR, PRESSURE_MAX_BAR, " +
					   "OFF_SPEC_GAS, SPEC_GAS_ENERGY_BASE, SPEC_GAS_MIN_ENERGY, SPEC_GAS_MAX_ENERGY, " +
					   "REV_NO, to_char(REV_DT,'dd/mm/yyyy'),CAPACITY, SEND_OUT_RATE,SYS_USE_GAS, " +
					   "NO_OF_CARGO, RE_GAS_TARIF, REMARK, to_char(STORAGE_FROM_DT,'dd/mm/yyyy'), to_char(STORAGE_TO_DT,'dd/mm/yyyy'), " +
					   "BUYER_NOM_CLAUSE, SELLER_NOM_CLAUSE, LIABILITY_CLAUSE, BILLING_CLAUSE, LC_CLAUSE,ADVANCE_ADJUST_CLAUSE,ADJUST_FLAG " +
					   "FROM FMS7_RE_GAS_MST WHERE CUSTOMER_CD='"+bscode+"' and  RE_GAS_NO='"+FGSA_cd+"' and FLAG='Y' AND " +
					   "REV_NO=(select max(rev_no) from FMS7_RE_GAS_MST  where CUSTOMER_CD='"+bscode+"' AND " +
					   "RE_GAS_NO='"+FGSA_cd+"' and FLAG='Y')";
			//System.out.println("fetch FGSAData Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				  SIGNING_DT = rset.getString(1)==null?"":rset.getString(1);
				  START_DT = rset.getString(2)==null?"":rset.getString(2);
				  END_DT = rset.getString(3)==null?"":rset.getString(3);
				  FGSA_BASE = rset.getString(4)==null?"":rset.getString(4);				  
				  STATUS = rset.getString(5)==null?"":rset.getString(5);
				  BUYER_NOM=rset.getString(6)==null?"":rset.getString(6);
				  BUYER_MONTH_NOM = rset.getString(7)==null?"":rset.getString(7);
				  BUYER_WEEK_NOM = rset.getString(8)==null?"":rset.getString(8);
				  BUYER_DAILY_NOM = rset.getString(9)==null?"":rset.getString(9);
				  SELLER_NOM = rset.getString(10)==null?"":rset.getString(10);
				  SELLER_MONTH_NOM = rset.getString(11)==null?"":rset.getString(11);
				  SELLER_WEEK_NOM = rset.getString(12)==null?"":rset.getString(12);
				  SELLER_DAILY_NOM = rset.getString(13)==null?"":rset.getString(13);
				  DAY_DEF = rset.getString(14)==null?"":rset.getString(14);
				  DAY_START_TIME = rset.getString(15)==null?"":rset.getString(15);
				  DAY_END_TIME = rset.getString(16)==null?"":rset.getString(16);
				  MDCQ = rset.getString(17)==null?"":rset.getString(17);
				  MDCQ_PERCENTAGE = rset.getString(18)==null?"":rset.getString(18);
				  MEASUREMENT = rset.getString(19)==null?"":rset.getString(19);
				  MEAS_STANDARD = rset.getString(20)==null?"":rset.getString(20);
				  MEAS_TEMPERATURE = rset.getString(21)==null?"":rset.getString(21);
				  PRESSURE_MIN_BAR = rset.getString(22)==null?"":rset.getString(22);
				  PRESSURE_MAX_BAR = rset.getString(23)==null?"":rset.getString(23);
				  OFF_SPEC_GAS = rset.getString(24)==null?"":rset.getString(24);
				  SPEC_GAS_ENERGY_BASE = rset.getString(25)==null?"":rset.getString(25);
				  SPEC_GAS_MIN_ENERGY = rset.getString(26)==null?"":rset.getString(26);
				  SPEC_GAS_MAX_ENERGY = rset.getString(27)==null?"":rset.getString(27);
				  REV_NO = rset.getString(28)==null?"0":rset.getString(28);				  
				  REV_DT = rset.getString(29)==null?"":rset.getString(29);
//MD20111104
				  contract_capacity = rset.getString(30)==null?"0":rset.getString(30);
//MD
				  contract_sent_rate = rset.getString(31)==null?"0":rset.getString(31);
				  sys_gas = rset.getString(32)==null?"1":rset.getString(32);
				  no_cargo = rset.getString(33)==null?"0":rset.getString(33);
				  re_gas_tariff = rset.getString(34)==null?"1":rset.getString(34);
				  re_gas_remark = rset.getString(35)==null?"":rset.getString(35);
				  STORAGE_FROM_DT = rset.getString(36)==null?"":rset.getString(36);
				  STORAGE_TO_DT = rset.getString(37)==null?"":rset.getString(37);
				  //System.out.println(" MDCQ  : "+MDCQ);
				  //System.out.println(" MEAS_STANDARD  : "+MEAS_STANDARD);
				  BUYER_NOM_CLAUSE = rset.getString(38)==null?"":rset.getString(38);
				  SELLER_NOM_CLAUSE = rset.getString(39)==null?"":rset.getString(39);
				  LC_CLAUSE = rset.getString(40)==null?"":rset.getString(40);
				  BILLING_CLAUSE = rset.getString(41)==null?"":rset.getString(41);
				  LIABILITY_CLAUSE = rset.getString(42)==null?"":rset.getString(42);
				  ADVANCE_ADJUST_CLAUSE = rset.getString(43)==null?"N":rset.getString(43);
				  ADJUST_FLAG = rset.getString(44)==null?"N":rset.getString(44);
			}
			
			//Fetch Contract Capacity As Total Of All Cargo ADQ Quantity ...
/*MD20111104 
 * 			queryString ="SELECT NVL(SUM(adq_qty),'0') FROM FMS7_RE_GAS_CARGO_DTL WHERE customer_cd="+bscode+" AND re_gas_no="+FGSA_cd+"";
			System.out.println("fetch Total RE-GAS Contract Qty from FMS7_RE_GAS_CARGO_DTL Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contract_capacity = rset.getString(1);
			}
		*/	
			//Fetch Buyer Name nd Abbr
			queryString ="Select CUSTOMER_NAME, CUSTOMER_ABBR from FMS7_CUSTOMER_MST where CUSTOMER_CD='"+bscode+"'";
			//System.out.println("fetch FMS7_RE_GAS_TRANSPORTER_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				  bName = rset.getString(1)==null?"":rset.getString(1);
				  bAbbr = rset.getString(2)==null?"":rset.getString(2);
			}
			
			//	FMS7_FGSA_TRANSPORTER_MST
			queryString ="Select TRANSPORTER_CD from FMS7_RE_GAS_TRANSPORTER_MST where CUSTOMER_CD='"+bscode+"' and  RE_GAS_NO='"+FGSA_cd+"' and REV_NO = (select max(rev_no) from FMS7_RE_GAS_MST  where CUSTOMER_CD='"+bscode+"' and  RE_GAS_NO='"+FGSA_cd+"')";
			//System.out.println("fetch FGSA_TRANSPORTER_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vTRANSPORTER_CD.add(rset.getString(1)==null?"0":rset.getString(1));
			}

			//FMS7_FGSA_PLANT_MST
			queryString ="Select PLANT_SEQ_NO from FMS7_RE_GAS_PLANT_MST where CUSTOMER_CD='"+bscode+"' and  RE_GAS_NO='"+FGSA_cd+"' and REV_NO = (select max(rev_no) from FMS7_RE_GAS_MST  where CUSTOMER_CD='"+bscode+"' and  RE_GAS_NO='"+FGSA_cd+"')";
			//System.out.println("fetch FGSA_PLANT_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vPLANT_SEQ_NO.add(rset.getString(1)==null?"0":rset.getString(1));
			}
			
			
			//FMS7_FGSA_CLAUSE_MST
			queryString ="Select CLAUSE_CD, REMARK from FMS7_RE_GAS_CLAUSE_MST where BUYER_CD='"+bscode+"' and  RE_GAS_NO='"+FGSA_cd+"' and REV_NO = (select max(rev_no) from FMS7_RE_GAS_MST  where CUSTOMER_CD='"+bscode+"' and  RE_GAS_NO='"+FGSA_cd+"')";
			//System.out.println("fetch FGSA_CLAUSE_MST Query  : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				  vCLAUSE_CD.add(rset.getString(1)==null?"0":rset.getString(1));
				  vREMARK.add(rset.getString(2)==null?"":rset.getString(2));
			}
			
			
			//Fetch Delivery point For Buyer			
			int count = 0;
			Vector plant_seq = new Vector();
			Vector plant_nm = new Vector();
			Vector plant_type = new Vector();
			Vector temp_plant_seq = new Vector();
			Vector temp_plant_nm = new Vector();
			Vector temp_plant_type = new Vector();
			
			temp_plant_nm.add("");
			temp_plant_type.add("");
			temp_plant_seq.add("0");
			
			/*queryString = "SELECT DISTINCT(A.seq_no),NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
			  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.flag='T' " +
			  			  "AND A.customer_cd="+bscode+" AND A.eff_dt = (SELECT MAX(B.eff_dt) " +
			  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd = "+bscode+" AND " +
			  			  "B.seq_no=A.seq_no AND B.eff_dt <= TO_DATE('"+SIGNING_DT+"','DD/MM/YYYY')) " +
			  			  "ORDER BY A.seq_no";
			System.out.println("Buyer Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(!rset.getString(2).equals(" ") && !rset.getString(3).equals(" "))
				{
					plant_seq.add(rset.getString(1)==null?"0":rset.getString(1));
					plant_type.add(rset.getString(2)==null?"":rset.getString(2));
					plant_nm.add(rset.getString(3)==null?"":rset.getString(3));
					++count;
				}
			}*/			
			queryString = "SELECT DISTINCT(A.seq_no) FROM FMS7_CUSTOMER_PLANT_DTL A " +
					      "WHERE A.flag='T' AND A.customer_cd="+bscode+" " +
			  //"AND A.eff_dt = (SELECT MAX(B.eff_dt) " +
			  //"FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd = "+bscode+" AND " +
			  //"B.seq_no=A.seq_no AND B.eff_dt <= TO_DATE('"+SIGNING_DT+"','DD/MM/YYYY')) " +
			              "ORDER BY A.seq_no";
			//System.out.println("Buyer Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{				
				plant_seq.add(rset.getString(1)==null?"0":rset.getString(1));
				queryString1 = "SELECT NVL(A.plant_type,' '),NVL(A.plant_name,' ') " +
	  			  "FROM FMS7_CUSTOMER_PLANT_DTL A WHERE A.flag='T' " +
	  			  "AND A.customer_cd = "+bscode+" AND A.seq_no='"+rset.getString(1)+"' " +
	  			  "AND A.eff_dt = (SELECT MAX(B.eff_dt) " +
	  			  "FROM FMS7_CUSTOMER_PLANT_DTL B WHERE B.customer_cd = "+bscode+" AND " +
	  			  "B.seq_no=A.seq_no ) ";
				//System.out.println("plant_type Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					plant_type.add(rset1.getString(1));
					plant_nm.add(rset1.getString(2));
				}
				else
				{
					plant_type.add(" ");
					plant_nm.add(" ");
				}
				++count;
			}
			
			if(count==0)
			{
				buyer_plant_seq_no.add(temp_plant_seq);
				buyer_plant_type.add(temp_plant_type);
				buyer_plant_nm.add(temp_plant_nm);
			}
			else
			{
				buyer_plant_seq_no.add(plant_seq);
				buyer_plant_type.add(plant_type);
				buyer_plant_nm.add(plant_nm);
			}
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchRE_GASData() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void RE_GAS_LIABILITY_CLAUSE_DTL()
	{
		try
		{
			queryString = "select CUSTOMER_CD,RE_GAS_NO,RE_GAS_REV_NO,CONT_TYPE,PRICE_PER,PRICE,PROMISE_QTY_FREQ,LIABILITY_PER,DCQ_FLAG,PNDCQ_FLAG,MDCQ_FLAG,REMARKS from FMS7_RE_GAS_LD_DTL WHERE RE_GAS_NO='"+FGSA_cd+"' And RE_GAS_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"'";
			rset = stmt.executeQuery(queryString);	
			while(rset.next())
			{			
				price_per1=rset.getString(5)==null?"":rset.getString(5);
				price1=rset.getString(6)==null?"":rset.getString(6);
				promise_qty1=rset.getString(7)==null?"":rset.getString(7);
				lower_per1=rset.getString(8)==null?"":rset.getString(8);
				dcq1=rset.getString(9)==null?"":rset.getString(9);
				pndcq1=rset.getString(10)==null?"":rset.getString(10);
				mdcq1=rset.getString(11)==null?"":rset.getString(11);
				remarks1=rset.getString(12)==null?"":rset.getString(12);																												
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION In :- DataBean_Contract_Master --> RE_GAS_LIABILITY_CLAUSE_DTL() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
		try
		{	
			queryString = "select CUSTOMER_CD,RE_GAS_NO,RE_GAS_REV_NO,CONT_TYPE,PRICE_PER,PRICE,TOP_PER,ACTUAL_OBLIG_QTY, PROMISE_QTY_FREQ ,REMARKS from FMS7_RE_GAS_TOP_DTL WHERE RE_GAS_NO='"+FGSA_cd+"' And RE_GAS_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"'";
			rset = stmt.executeQuery(queryString);	
			while(rset.next())
			{			
				price_per2=rset.getString(5)==null?"":rset.getString(5);
				price2=rset.getString(6)==null?"":rset.getString(6);
				top_per=rset.getString(7)==null?"":rset.getString(7);
				actual_oblig=rset.getString(8)==null?"":rset.getString(8);
				promise_qty2=rset.getString(9)==null?"":rset.getString(9);
				remarks2=rset.getString(10)==null?"":rset.getString(10);	
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION In :- DataBean_Contract_Master --> RE_GAS_LIABILITY_CLAUSE_DTL() Method:\n"+e.getMessage());
			e.printStackTrace();
		}	
		try
		{		
			queryString = "select CUSTOMER_CD,RE_GAS_NO,RE_GAS_REV_NO,CONT_TYPE,MAKEUP_PERIOD,RECOVERY_PERIOD,PRICE_PER,PRICE,REMARKS from FMS7_RE_GAS_MAKEUPGAS_DTL WHERE RE_GAS_NO='"+FGSA_cd+"' And RE_GAS_REV_NO='"+FGSA_REVNo+"' And CUSTOMER_CD='"+bscode+"'";
			rset = stmt.executeQuery(queryString);		
			while(rset.next())
			{		
				makeup_period_per=rset.getString(5)==null?"":rset.getString(5);
				rec_period_per=rset.getString(6)==null?"":rset.getString(6);
				price_per3=rset.getString(7)==null?"":rset.getString(7);
				price3=rset.getString(8)==null?"":rset.getString(8);
				remarks3=rset.getString(9)==null?"":rset.getString(9);																											
			}				
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION In :- DataBean_Contract_Master --> RE_GAS_LIABILITY_CLAUSE_DTL() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void Fetch_FGSA_Tender_LC_Dtl()
	{
		try
		{		
			if(cont_type.equalsIgnoreCase("F") || cont_type.equalsIgnoreCase("T"))
			{
				queryString = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
							  "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+FGSA_cd+" and " +
							  "FGSA_TENDER_REV_NO="+FGSA_REVNo+" and CUSTOMER_CD="+Buyer_cd+" and " +
							  "CONT_TYPE='"+cont_type+"'";
				//System.out.println("FGSA/Tender LC Details Select Query = "+queryString);
				rset = stmt.executeQuery(queryString);		
				if(rset.next())
				{		
					exchg_rate = rset.getString(1)==null?"":rset.getString(1);
					flag_lc_value = rset.getString(2)==null?"":rset.getString(2);
					flag_dcq_tcq = rset.getString(3)==null?"":rset.getString(3);
					dcqdays_tcqpercent_value = rset.getString(4)==null?"":rset.getString(4);
					lc_remarks = rset.getString(5)==null?"":rset.getString(5);																											
				}
			}
			else if(cont_type.equalsIgnoreCase("S") || cont_type.equalsIgnoreCase("L"))
			{
				queryString = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
							  "from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+FGSA_cd+" and " +
							  "FGSA_TENDER_REV_NO="+FGSA_REVNo+" and CUSTOMER_CD="+Buyer_cd+" and " +
							  "CONT_TYPE='"+cont_type+"' and SN_LOA_NO="+SN_CD+" and SN_LOA_REV_NO="+SN_REVNo+"";
				//System.out.println("SN/LOA LC Details Select Query = "+queryString);
				rset = stmt.executeQuery(queryString);		
				if(rset.next())
				{		
					exchg_rate = rset.getString(1)==null?"":rset.getString(1);
					flag_lc_value = rset.getString(2)==null?"":rset.getString(2);
					flag_dcq_tcq = rset.getString(3)==null?"":rset.getString(3);
					dcqdays_tcqpercent_value = rset.getString(4)==null?"":rset.getString(4);
					lc_remarks = rset.getString(5)==null?"":rset.getString(5);																											
				}
				else
				{
					if(cont_type.equalsIgnoreCase("S"))
					{
						queryString = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
									  "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+FGSA_cd+" and " +
									  "FGSA_TENDER_REV_NO="+FGSA_REVNo+" and CUSTOMER_CD="+Buyer_cd+" and " +
									  "CONT_TYPE='F'";
					}
					else if(cont_type.equalsIgnoreCase("L"))
					{
						queryString = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
									  "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+FGSA_cd+" and " +
									  "FGSA_TENDER_REV_NO="+FGSA_REVNo+" and CUSTOMER_CD="+Buyer_cd+" and " +
									  "CONT_TYPE='T'";
					}
					//System.out.println("(If SN/LOA LC Details is Absent, then ...) FGSA/Tender LC Details Select Query = "+queryString);
					rset = stmt.executeQuery(queryString);		
					if(rset.next())
					{		
						exchg_rate = rset.getString(1)==null?"":rset.getString(1);
						flag_lc_value = rset.getString(2)==null?"":rset.getString(2);
						flag_dcq_tcq = rset.getString(3)==null?"":rset.getString(3);
						dcqdays_tcqpercent_value = rset.getString(4)==null?"":rset.getString(4);
						lc_remarks = rset.getString(5)==null?"":rset.getString(5);																											
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION In :- DataBean_Contract_Master --> Fetch_FGSA_Tender_LC_Dtl() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Introduce by Milan Dalsaniya 2011 nov 04 MD20111104
	public void Fetch_LC_REGAS_ListOLD()
	{

		try
		{		
			queryString = "SELECT A.FINANCIAL_YEAR, A.LC_SEQ_NO, A.CUSTOMER_CD, " +
						  "A.CREDIT_RATING, TO_CHAR(A.RATING_EFF_DATE,'DD/MM/YYYY'), TO_CHAR(A.LC_REF_DATE,'DD/MM/YYYY'), " +
						  "TO_CHAR(A.START_DATE,'DD/MM/YYYY'), TO_CHAR(A.END_DATE,'DD/MM/YYYY'), " +
						  "A.MANUAL_EXCHG_FLAG, A.MANUAL_EXCHG_RATE, A.USER_DEFINED_FLAG, " +
						  "A.USER_DEFINED_DCQ, A.CALC_LC_AMOUNT, A.FINAL_LC_AMOUNT, A.REMARKS " +
						  "FROM FMS7_LC_MST A WHERE A.START_DATE BETWEEN " +
						  "TO_DATE('"+lc_from_dt+"','DD/MM/YYYY') AND TO_DATE('"+lc_to_dt+"','DD/MM/YYYY') " +
						  "AND (A.LC_SEQ_NO || A.FINANCIAL_YEAR NOT IN (SELECT B.LC_SEQ_NO || B.FINANCIAL_YEAR FROM FMS7_LC_FINANCE_MST B)) AND FLAG = 'R'" +
						  "ORDER BY A.FINANCIAL_YEAR,A.LC_SEQ_NO";
			
			System.out.println("Query for fetching Master LC List = "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				V_lc_seq_no.add(rset.getString(2));
				V_fin_year.add(rset.getString(1));
				int lc_seq_no = rset.getInt(2);
				String fin_year = rset.getString(1);
				String LC_SEQ_NO = "";
				
				if(lc_seq_no<10)
				{
					LC_SEQ_NO = "000"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<100)
				{
					LC_SEQ_NO = "00"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<1000)
				{
					LC_SEQ_NO = "0"+lc_seq_no+"/"+fin_year;
				}
				else
				{
					LC_SEQ_NO = ""+lc_seq_no+"/"+fin_year;
				}
				
				vLC_SEQ_NO.add(LC_SEQ_NO);
				vStatus.add("N");
				CUST_CD.add(rset.getString(3)==null?"":rset.getString(3));
				vCREDIT_RATING.add(rset.getString(4)==null?"0":rset.getString(4));
				vCREDIT_RATE_EFF_DT.add(rset.getString(5)==null?"":rset.getString(5));
				vLC_REF_DT.add(rset.getString(6)==null?"":rset.getString(6));
				vLC_START_DT.add(rset.getString(7)==null?"":rset.getString(7));
				vLC_END_DT.add(rset.getString(8)==null?"":rset.getString(8));
				vMANUAL_EXCHG_RATE_FLAG.add(rset.getString(9)==null?"N":rset.getString(9));
				vMANUAL_EXCHG_RATE.add(rset.getString(10)==null?"":rset.getString(10));
				vUSER_DEFINED_FLAG.add(rset.getString(11)==null?"N":rset.getString(11));
				vUSER_DEFINED_DCQ.add(rset.getString(12)==null?"":rset.getString(12));
				vLC_AMT.add(rset.getString(13)==null?"":rset.getString(13));
				vLC_FINAL_AMT.add(rset.getString(14)==null?"":rset.getString(14));
				vLC_REMARKS.add(rset.getString(15)==null?"":rset.getString(15));
				
				String bscode = "";
				String FGSA_No = "";
				String Rev_No = "";
				String customer = "";
				String snNo = "";
				String snRev = "";
				String tcq = "";
				String dcq = "";
				String datediff = "";
				String rate = "";
				String START_DT = "";
				String END_DT = "";
				String tax_type = "";
				String cont_type = "";
				String lc_exchg_rate = "";
				String lc_base_remark = "";
				String flag_lc_value = "";
				String flag_dcq_tcq = "";
				String dcqdays_tcqpercent_value = "";
				
				queryString1 = "SELECT FGSA_NO, SN_NO, CONT_TYPE, FGSA_REV_NO, SN_REV_NO, " +
							   "TO_CHAR(SN_START_DATE,'DD/MM/YYYY'), TO_CHAR(SN_END_DATE,'DD/MM/YYYY'), " +
							   "SN_DURATION, DCQ, TCQ, SALES_RATE, EXCHG_RATE, " +
							   "TAX_PERCENTAGE, LC_METHOD_REMARK, LC_METHOD, LC_BASE, " +
							   "DCQ_DAYS_TCQ_PERCENTAGE " +
							   "FROM FMS7_LC_DTL WHERE FINANCIAL_YEAR='"+rset.getString(1)+"' " +
							   "AND LC_SEQ_NO="+rset.getString(2)+" AND CUSTOMER_CD="+rset.getString(3)+" " +
							   	"AND (CONT_TYPE = 'R' OR CONT_TYPE = 'r')" +
							   "ORDER BY SN_END_DATE";
				
				System.out.println("Query for fetching Detailed LC Info = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					cont_type += (rset1.getString(3)==null?"S":rset1.getString(3))+"~~";
					tax_type += "VAT"+"~~";
					
					END_DT += (rset1.getString(7)==null?"-":rset1.getString(7))+"~~";
					START_DT += (rset1.getString(6)==null?"-":rset1.getString(6))+"~~";
					rate += ""+0+"~~";
					datediff += (rset1.getString(8)==null?"0":rset1.getString(8))+"~~";
					dcq += (rset1.getString(9)==null?"0":rset1.getString(9))+"~~";
					tcq += (rset1.getString(10)==null?"0":rset1.getString(10))+"~~";
					snRev += (rset1.getString(5)==null?"0":rset1.getString(5))+"~~";
					snNo += (rset1.getString(2)==null?"0":rset1.getString(2))+"~~";
					customer += rset.getString(3)+"~~";
					Rev_No += (rset1.getString(4)==null?"0":rset1.getString(4))+"~~";
					FGSA_No += (rset1.getString(1)==null?"0":rset1.getString(1))+"~~";
					bscode += rset.getString(3)+"~~";
						
					lc_exchg_rate += "-"+"~~";
					flag_lc_value += "-"+"~~";
					flag_dcq_tcq += "-"+"~~";
					dcqdays_tcqpercent_value += "-"+"~~";
					lc_base_remark += "-"+"~~";
				}
				
				/*System.out.println("bscode = "+bscode);
				System.out.println("FGSA_No = "+FGSA_No);
				System.out.println("Rev_No = "+Rev_No);
				System.out.println("snNo = "+snNo);
				System.out.println("snRev = "+snRev);
				System.out.println("dcq = "+dcq);
				System.out.println("tcq = "+tcq);
				System.out.println("START_DT = "+START_DT);
				System.out.println("END_DT = "+END_DT);
				System.out.println("datediff = "+datediff);
				System.out.println("rate = "+rate);
				System.out.println("cont_type = "+cont_type);
				System.out.println("tax_type = "+tax_type);
				System.out.println("lc_exchg_rate = "+lc_exchg_rate);
				System.out.println("flag_lc_value = "+flag_lc_value);
				System.out.println("flag_dcq_tcq = "+flag_dcq_tcq);
				System.out.println("dcqdays_tcqpercent_value = "+dcqdays_tcqpercent_value);
				System.out.println("lc_base_remark = "+lc_base_remark);*/
				
				CUSTOMER_CD.add(bscode);
				vSN_No.add(snNo);
				vSN_rev_No.add(snRev);
				vSN_DCQ.add(dcq);
				vSN_TCQ.add(tcq);	
				FGSA_no.add(FGSA_No);
				vFGSA_REV_NO.add(Rev_No);
				vSN_StartDate.add(START_DT);
				vSN_EndDate.add(END_DT);
				vSN_DATEDIFF.add(datediff);
				vSN_RATE.add(rate);
				vCONT_TYPE.add(cont_type);
				vTAX_TYPE.add(tax_type);
				vSN_LC_EXCHG_RATE.add(lc_exchg_rate);
				vSN_LC_BASE.add(flag_lc_value);
				vSN_LC_DCQ_TCQ_FLAG.add(flag_dcq_tcq);
				vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.add(dcqdays_tcqpercent_value);
				vSN_LC_BASE_REMARK.add(lc_base_remark);
			}
			
			for(int i=0; i<CUST_CD.size(); i++)
			{
				queryString1="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" ";
				System.out.println("queryString----------"+queryString1);
				rset1= stmt1.executeQuery(queryString1);
				if(rset1.next())	
				{
					CUST_NM.add(rset1.getString(1)==null?"":rset1.getString(1));
				}
				else
				{
					CUST_NM.add("");
				}
			}
			
			queryString1="SELECT BANK_CD,BANK_NAME FROM FMS7_BANK_MST";
			System.out.println("queryString----------"+queryString1);
			rset1= stmt1.executeQuery(queryString1);
			while(rset1.next())
			{
				V_bank_cd.add(rset1.getString(1)==null?"":rset1.getString(1));
				V_bank_nm.add(rset1.getString(2)==null?"":rset1.getString(2));
			}
			//Start JHP
			queryString1="SELECT DISTINCT(CREDIT_RATING) FROM FMS7_BANK_MST";
			System.out.println("queryString----------"+queryString1);
			rset1= stmt1.executeQuery(queryString1);
			while(rset1.next())
			{
				V_bank_rating.add(rset1.getString(1)==null?"":rset1.getString(1));
			}
			//JHP END
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_SEQ_NO_List() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	//Introduce by Milan Dalsaniya MD20111104
	public void View_LC_REGAS_ListOLD()
	{

		try
		{		
			queryString = "SELECT FINANCIAL_YEAR,LC_SEQ_NO,CUSTOMER_CD,BANK_CD,CUSTOMER_NM," +
					      "BANK_NM,BANK_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY')," +
					      "TO_CHAR(VALIDITY_START_DATE,'DD/MM/YYYY'),TO_CHAR(VALIDITY_END_DATE,'DD/MM/YYYY')," +
					      "MRKT_LC_AMOUNT,BANK_LC_AMOUNT,TO_CHAR(LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
					      "To_CHAR(AMENDMENT_DATE,'DD/MM/YYYY'),AMENDMENT_FLAG,REMARKS " +
					      "FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+bank_lc_no+"' AND AMENDMENT_NO='"+amendment_no+"' ";			
			System.out.println("Query for fetching FMS7_LC_FINANCE_MST = "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				lc_fin_year = rset.getString(1)==null?"":rset.getString(1);
				lc_seq_no = rset.getString(2)==null?"":rset.getString(2);
				//customer_cd  = rset.getString(3)==null?"":rset.getString(3);
				bank_cd = rset.getString(4)==null?"":rset.getString(4);
				customer_nm = rset.getString(5)==null?"":rset.getString(5);
				bank_name = rset.getString(6)==null?"":rset.getString(6);
				bank_rating = rset.getString(7)==null?"":rset.getString(7);
				rating_eff_date = rset.getString(8)==null?"":rset.getString(8);
				validity_st_dt = rset.getString(9)==null?"":rset.getString(9);
				validity_end_dt = rset.getString(10)==null?"":rset.getString(10);
				mrkt_lc_amt = rset.getString(11)==null?"":rset.getString(11);
				bank_lc_amt = rset.getString(12)==null?"":rset.getString(12);
				ship_dt = rset.getString(13)==null?"":rset.getString(13);
				amendment_dt = rset.getString(14)==null?"":rset.getString(14);
				amendment_flag = rset.getString(15)==null?"":rset.getString(15);
				remarks = rset.getString(16)==null?"":rset.getString(16);
				//V_bank_cd.add(rset.getString(1)==null?"":rset.getString(1));
				//V_bank_nm.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> View_LC_List() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	
	}
	public void Fetch_LC_SN_List()
	{
		try
		{		
			queryString = "SELECT A.FINANCIAL_YEAR, A.LC_SEQ_NO, A.CUSTOMER_CD, " +
						  "A.CREDIT_RATING, TO_CHAR(A.RATING_EFF_DATE,'DD/MM/YYYY'), TO_CHAR(A.LC_REF_DATE,'DD/MM/YYYY'), " +
						  "TO_CHAR(A.START_DATE,'DD/MM/YYYY'), TO_CHAR(A.END_DATE,'DD/MM/YYYY'), " +
						  "A.MANUAL_EXCHG_FLAG, A.MANUAL_EXCHG_RATE, A.USER_DEFINED_FLAG, " +
						  "A.USER_DEFINED_DCQ, A.CALC_LC_AMOUNT, A.FINAL_LC_AMOUNT, A.REMARKS " +
						  "FROM FMS7_LC_MST A WHERE A.START_DATE BETWEEN " +
						  "TO_DATE('"+lc_from_dt+"','DD/MM/YYYY') AND TO_DATE('"+lc_to_dt+"','DD/MM/YYYY') " +
						  "AND (A.LC_SEQ_NO || A.FINANCIAL_YEAR NOT IN (SELECT B.LC_SEQ_NO || B.FINANCIAL_YEAR FROM FMS7_LC_FINANCE_MST B)) AND FLAG = 'Y'" +
						  "ORDER BY A.FINANCIAL_YEAR,A.LC_SEQ_NO";
			
			//System.out.println("Query for fetching Master LC List = "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				V_lc_seq_no.add(rset.getString(2));
				V_fin_year.add(rset.getString(1));
				int lc_seq_no = rset.getInt(2);
				String fin_year = rset.getString(1);
				String LC_SEQ_NO = "";
				
				if(lc_seq_no<10)
				{
					LC_SEQ_NO = "000"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<100)
				{
					LC_SEQ_NO = "00"+lc_seq_no+"/"+fin_year;
				}
				else if(lc_seq_no<1000)
				{
					LC_SEQ_NO = "0"+lc_seq_no+"/"+fin_year;
				}
				else
				{
					LC_SEQ_NO = ""+lc_seq_no+"/"+fin_year;
				}
				
				vLC_SEQ_NO.add(LC_SEQ_NO);
				vStatus.add("N");
				CUST_CD.add(rset.getString(3)==null?"":rset.getString(3));
				vCREDIT_RATING.add(rset.getString(4)==null?"0":rset.getString(4));
				vCREDIT_RATE_EFF_DT.add(rset.getString(5)==null?"":rset.getString(5));
				vLC_REF_DT.add(rset.getString(6)==null?"":rset.getString(6));
				vLC_START_DT.add(rset.getString(7)==null?"":rset.getString(7));
				vLC_END_DT.add(rset.getString(8)==null?"":rset.getString(8));
				vMANUAL_EXCHG_RATE_FLAG.add(rset.getString(9)==null?"N":rset.getString(9));
				vMANUAL_EXCHG_RATE.add(rset.getString(10)==null?"":rset.getString(10));
				vUSER_DEFINED_FLAG.add(rset.getString(11)==null?"N":rset.getString(11));
				vUSER_DEFINED_DCQ.add(rset.getString(12)==null?"":rset.getString(12));
				vLC_AMT.add(rset.getString(13)==null?"":rset.getString(13));
				vLC_FINAL_AMT.add(rset.getString(14)==null?"":rset.getString(14));
				vLC_REMARKS.add(rset.getString(15)==null?"":rset.getString(15));
				
				String bscode = "";
				String FGSA_No = "";
				String Rev_No = "";
				String customer = "";
				String snNo = "";
				String snRev = "";
				String tcq = "";
				String dcq = "";
				String datediff = "";
				String rate = "";
				String START_DT = "";
				String END_DT = "";
				String tax_type = "";
				String cont_type = "";
				String lc_exchg_rate = "";
				String lc_base_remark = "";
				String flag_lc_value = "";
				String flag_dcq_tcq = "";
				String dcqdays_tcqpercent_value = "";
				
				queryString1 = "SELECT FGSA_NO, SN_NO, CONT_TYPE, FGSA_REV_NO, SN_REV_NO, " +
							   "TO_CHAR(SN_START_DATE,'DD/MM/YYYY'), TO_CHAR(SN_END_DATE,'DD/MM/YYYY'), " +
							   "SN_DURATION, DCQ, TCQ, SALES_RATE, EXCHG_RATE, " +
							   "TAX_PERCENTAGE, LC_METHOD_REMARK, LC_METHOD, LC_BASE, " +
							   "DCQ_DAYS_TCQ_PERCENTAGE " +
							   "FROM FMS7_LC_DTL WHERE FINANCIAL_YEAR='"+rset.getString(1)+"' " +
							   "AND LC_SEQ_NO="+rset.getString(2)+" AND CUSTOMER_CD="+rset.getString(3)+" " +
							   "ORDER BY SN_END_DATE";
				
				//System.out.println("Query for fetching Detailed LC Info = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					cont_type += (rset1.getString(3)==null?"S":rset1.getString(3))+"~~";
					tax_type += "VAT"+"~~";
											
					if(rset1.getString(3).equalsIgnoreCase("S"))
					{
						queryString2 = "SELECT A.SN_NO, A.SN_REV_NO, A.CUSTOMER_CD, A.FGSA_NO, A.FGSA_REV_NO, A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT, "+
									   "C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.SN_NAME, A.VERIFY_FLAG, A.APPROVE_FLAG, A.DCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'), " +
									   "TO_CHAR(A.START_DT,'DD/MM/YYYY'), (A.END_DT - A.START_DT)+1 "+
									   "FROM FMS7_SN_MST A, FMS7_CUSTOMER_MST C, FMS7_UNIT_MST D WHERE A.FLAG='T' AND " +
									   "A.FGSA_NO="+rset1.getString(1)+" AND A.FGSA_REV_NO="+rset1.getString(4)+" AND " +
									   "A.CUSTOMER_CD="+rset.getString(3)+" AND A.STATUS='Y' " +
									   "AND A.SN_NO="+rset1.getString(2)+" AND A.SN_REV_NO="+rset1.getString(5)+" AND "+							
									   "A.CUSTOMER_CD=C.CUSTOMER_CD AND A.QUANTITY_UNIT=D.UNIT_CD";				
						//System.out.println("SN List Query For LC Generation (1) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							END_DT += (rset2.getString(16)==null?"-":rset2.getString(16))+"~~";
							START_DT += (rset2.getString(17)==null?"-":rset2.getString(17))+"~~";
							rate += (rset2.getString(8)==null?"0":rset2.getString(8))+"~~";
							datediff += (rset2.getString(18)==null?"0":rset2.getString(18))+"~~";
							dcq += (rset2.getString(15)==null?"0":rset2.getString(15))+"~~";
							tcq += (rset2.getString(6)==null?"0":rset2.getString(6))+"~~";
							snRev += (rset2.getString(2)==null?"0":rset2.getString(2))+"~~";
							snNo += (rset2.getString(1)==null?"0":rset2.getString(1))+"~~";
							customer += (rset2.getString(3)==null?"0":rset2.getString(3))+"~~";
							Rev_No += (rset2.getString(5)==null?"0":rset2.getString(5))+"~~";
							FGSA_No += (rset2.getString(4)==null?"0":rset2.getString(4))+"~~";
							bscode += (rset2.getString(3)==null?"0":rset2.getString(3))+"~~";
								
							queryString3 = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
										   "from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+rset1.getString(1)+" and " +
										   "FGSA_TENDER_REV_NO="+rset1.getString(4)+" and CUSTOMER_CD="+rset.getString(3)+" and " +
										   "CONT_TYPE='S' and SN_LOA_NO="+rset1.getString(2)+" and SN_LOA_REV_NO="+rset1.getString(5)+"";
							//System.out.println("SN/LOA LC Details Select Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);		
							if(rset3.next())
							{		
								lc_exchg_rate += (rset3.getString(1)==null?"-":rset3.getString(1))+"~~";
								flag_lc_value += (rset3.getString(2)==null?"-":rset3.getString(2))+"~~";
								flag_dcq_tcq += (rset3.getString(3)==null?"-":rset3.getString(3))+"~~";
								dcqdays_tcqpercent_value += (rset3.getString(4)==null?"-":rset3.getString(4))+"~~";
								
								if(rset3.getString(2).trim().equalsIgnoreCase("SN"))
								{
									lc_base_remark += "Against SN Value"+"~~";
								}
								else if(rset3.getString(2).trim().equalsIgnoreCase("TAX"))
								{
									lc_base_remark += "Against Tax and Advances"+"~~";
								}
								else if(rset3.getString(2).trim().equalsIgnoreCase("OPEN"))
								{
									lc_base_remark += "Against Open Account"+"~~";
								}
								else
								{
									lc_base_remark += "-"+"~~";
								}
							}
							else
							{
								queryString3 = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
											   "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+rset1.getString(1)+" and " +
											   "FGSA_TENDER_REV_NO="+rset1.getString(4)+" and CUSTOMER_CD="+rset.getString(3)+" and " +
											   "CONT_TYPE='F'";
								
								//System.out.println("(If SN/LOA LC Details is Absent, then ...) FGSA/Tender LC Details Select Query = "+queryString);
								rset3 = stmt3.executeQuery(queryString3);		
								if(rset3.next())
								{		
									lc_exchg_rate += (rset3.getString(1)==null?"-":rset3.getString(1))+"~~";
									flag_lc_value += (rset3.getString(2)==null?"-":rset3.getString(2))+"~~";
									flag_dcq_tcq += (rset3.getString(3)==null?"-":rset3.getString(3))+"~~";
									dcqdays_tcqpercent_value += (rset3.getString(4)==null?"-":rset3.getString(4))+"~~";
									
									if(rset3.getString(2).trim().equalsIgnoreCase("SN"))
									{
										lc_base_remark += "Against SN Value"+"~~";
									}
									else if(rset3.getString(2).trim().equalsIgnoreCase("TAX"))
									{
										lc_base_remark += "Against Tax and Advances"+"~~";
									}
									else if(rset3.getString(2).trim().equalsIgnoreCase("OPEN"))
									{
										lc_base_remark += "Against Open Account"+"~~";
									}
									else
									{
										lc_base_remark += "-"+"~~";
									}
								}
								else
								{
									lc_exchg_rate += "-"+"~~";
									flag_lc_value += "-"+"~~";
									flag_dcq_tcq += "-"+"~~";
									dcqdays_tcqpercent_value += "-"+"~~";
									lc_base_remark += "-"+"~~";
								}
							}
						}
					}
					else if(rset1.getString(3).equalsIgnoreCase("L"))
					{
						queryString2 = "SELECT A.LOA_NO, A.LOA_REV_NO, A.CUSTOMER_CD, A.TENDER_NO, A.TCQ, D.UNIT_ABR, A.RATE, A.RATE_UNIT, "+
									  "C.CUSTOMER_NAME, C.CUSTOMER_ABBR, A.LOA_NAME, A.VERIFY_FLAG, A.APPROVE_FLAG, A.DCQ, " +
									  "TO_CHAR(A.END_DT,'DD/MM/YYYY'), TO_CHAR(A.START_DT,'DD/MM/YYYY'), (A.END_DT - A.START_DT)+1 "+
									  "from FMS7_LOA_MST A, FMS7_CUSTOMER_MST C , FMS7_UNIT_MST D WHERE A.FLAG='T' " +
									  "AND A.CUSTOMER_CD="+rset.getString(3)+" " +
									  "AND A.STATUS='Y' AND A.TENDER_NO='"+rset1.getString(1)+"' " +
									  "AND A.LOA_REV_NO="+rset1.getString(5)+" AND A.LOA_NO="+rset1.getString(2)+" "+
							          "AND C.CUSTOMER_CD=A.CUSTOMER_CD AND A.QUANTITY_UNIT=D.UNIT_CD";
						
						//System.out.println("LOA List Query For LC Generation (2) = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							END_DT += (rset2.getString(15)==null?"-":rset2.getString(15))+"~~";
							START_DT += (rset2.getString(16)==null?"-":rset2.getString(16))+"~~";
							rate += (rset2.getString(7)==null?"0":rset2.getString(7))+"~~";
							datediff += (rset2.getString(17)==null?"0":rset2.getString(17))+"~~";
							dcq += (rset2.getString(14)==null?"0":rset2.getString(14))+"~~";
							tcq += (rset2.getString(5)==null?"0":rset2.getString(5))+"~~";
							snRev += (rset2.getString(2)==null?"0":rset2.getString(2))+"~~";
							snNo += (rset2.getString(1)==null?"0":rset2.getString(1))+"~~";
							customer += (rset2.getString(3)==null?"0":rset2.getString(3))+"~~";
							Rev_No += "0"+"~~";
							FGSA_No += (rset2.getString(4)==null?"0":rset2.getString(4))+"~~";
							bscode += (rset2.getString(3)==null?"0":rset2.getString(3))+"~~";
							
							queryString3 = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
										   "from FMS7_SN_LOA_LC_DTL where FGSA_TENDER_NO="+rset1.getString(1)+" and " +
										   "FGSA_TENDER_REV_NO=0 and CUSTOMER_CD="+rset.getString(3)+" and " +
										   "CONT_TYPE='L' and SN_LOA_NO="+rset1.getString(2)+" and SN_LOA_REV_NO="+rset1.getString(5)+"";
							//System.out.println("SN/LOA LC Details Select Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);		
							if(rset3.next())
							{		
								lc_exchg_rate += (rset3.getString(1)==null?"-":rset3.getString(1))+"~~";
								flag_lc_value += (rset3.getString(2)==null?"-":rset3.getString(2))+"~~";
								flag_dcq_tcq += (rset3.getString(3)==null?"-":rset3.getString(3))+"~~";
								dcqdays_tcqpercent_value += (rset3.getString(4)==null?"-":rset3.getString(4))+"~~";
								
								if(rset3.getString(2).trim().equalsIgnoreCase("SN"))
								{
									lc_base_remark += "Against SN Value"+"~~";
								}
								else if(rset3.getString(2).trim().equalsIgnoreCase("TAX"))
								{
									lc_base_remark += "Against Tax and Advances"+"~~";
								}
								else if(rset3.getString(2).trim().equalsIgnoreCase("OPEN"))
								{
									lc_base_remark += "Against Open Account"+"~~";
								}
								else
								{
									lc_base_remark += "-"+"~~";
								}
							}
							else
							{
								queryString3 = "select EXCHG_RATE, LC_BASE, DCQ_TCQ_BASE, DCQ_DAYS_OR_TCQ_PERCENT, REMARKS " +
											  "from FMS7_FGSA_TENDER_LC_DTL where FGSA_TENDER_NO="+rset1.getString(1)+" and " +
											  "FGSA_TENDER_REV_NO=0 and CUSTOMER_CD="+rset.getString(3)+" and " +
											  "CONT_TYPE='T'";
								
								//System.out.println("(If SN/LOA LC Details is Absent, then ...) FGSA/Tender LC Details Select Query = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);		
								if(rset3.next())
								{		
									lc_exchg_rate += (rset3.getString(1)==null?"-":rset3.getString(1))+"~~";
									flag_lc_value += (rset3.getString(2)==null?"-":rset3.getString(2))+"~~";
									flag_dcq_tcq += (rset3.getString(3)==null?"-":rset3.getString(3))+"~~";
									dcqdays_tcqpercent_value += (rset3.getString(4)==null?"-":rset3.getString(4))+"~~";
									
									if(rset3.getString(2).trim().equalsIgnoreCase("SN"))
									{
										lc_base_remark += "Against SN Value"+"~~";
									}
									else if(rset3.getString(2).trim().equalsIgnoreCase("TAX"))
									{
										lc_base_remark += "Against Tax and Advances"+"~~";
									}
									else if(rset3.getString(2).trim().equalsIgnoreCase("OPEN"))
									{
										lc_base_remark += "Against Open Account"+"~~";
									}
									else
									{
										lc_base_remark += "-"+"~~";
									}
								}
								else
								{
									lc_exchg_rate += "-"+"~~";
									flag_lc_value += "-"+"~~";
									flag_dcq_tcq += "-"+"~~";
									dcqdays_tcqpercent_value += "-"+"~~";
									lc_base_remark += "-"+"~~";
								}
							}
						}
					}
				}
				
				/*System.out.println("bscode = "+bscode);
				System.out.println("FGSA_No = "+FGSA_No);
				System.out.println("Rev_No = "+Rev_No);
				System.out.println("snNo = "+snNo);
				System.out.println("snRev = "+snRev);
				System.out.println("dcq = "+dcq);
				System.out.println("tcq = "+tcq);
				System.out.println("START_DT = "+START_DT);
				System.out.println("END_DT = "+END_DT);
				System.out.println("datediff = "+datediff);
				System.out.println("rate = "+rate);
				System.out.println("cont_type = "+cont_type);
				System.out.println("tax_type = "+tax_type);
				System.out.println("lc_exchg_rate = "+lc_exchg_rate);
				System.out.println("flag_lc_value = "+flag_lc_value);
				System.out.println("flag_dcq_tcq = "+flag_dcq_tcq);
				System.out.println("dcqdays_tcqpercent_value = "+dcqdays_tcqpercent_value);
				System.out.println("lc_base_remark = "+lc_base_remark);*/
				
				CUSTOMER_CD.add(bscode);
				vSN_No.add(snNo);
				vSN_rev_No.add(snRev);
				vSN_DCQ.add(dcq);
				vSN_TCQ.add(tcq);	
				FGSA_no.add(FGSA_No);
				vFGSA_REV_NO.add(Rev_No);
				vSN_StartDate.add(START_DT);
				vSN_EndDate.add(END_DT);
				vSN_DATEDIFF.add(datediff);
				vSN_RATE.add(rate);
				vCONT_TYPE.add(cont_type);
				vTAX_TYPE.add(tax_type);
				vSN_LC_EXCHG_RATE.add(lc_exchg_rate);
				vSN_LC_BASE.add(flag_lc_value);
				vSN_LC_DCQ_TCQ_FLAG.add(flag_dcq_tcq);
				vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE.add(dcqdays_tcqpercent_value);
				vSN_LC_BASE_REMARK.add(lc_base_remark);
			}
			
			for(int i=0; i<CUST_CD.size(); i++)
			{
				queryString1="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" ";
				//System.out.println("queryString----------"+queryString1);
				rset1= stmt1.executeQuery(queryString1);
				if(rset1.next())	
				{
					CUST_NM.add(rset1.getString(1)==null?"":rset1.getString(1));
				}
				else
				{
					CUST_NM.add("");
				}
			}
			
			queryString1="SELECT BANK_CD,BANK_NAME FROM FMS7_BANK_MST";
			//System.out.println("queryString----------"+queryString1);
			rset1= stmt1.executeQuery(queryString1);
			while(rset1.next())
			{
				V_bank_cd.add(rset1.getString(1)==null?"":rset1.getString(1));
				V_bank_nm.add(rset1.getString(2)==null?"":rset1.getString(2));
			}
//			Start JHP
			queryString1="SELECT DISTINCT(CREDIT_RATING) FROM FMS7_BANK_MST";
			//System.out.println("queryString----------"+queryString1);
			rset1= stmt1.executeQuery(queryString1);
			while(rset1.next())
			{
				V_bank_rating.add(rset1.getString(1)==null?"":rset1.getString(1));
			}
			//JHP END
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetch_LC_SEQ_NO_List() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//	Introduced by Priyanka Sharma on 19th Jan.,2011
	public void View_LC_List()
	{
		try
		{		
			queryString = "SELECT FINANCIAL_YEAR,LC_SEQ_NO,CUSTOMER_CD,BANK_CD,CUSTOMER_NM," +
					      "BANK_NM,BANK_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY')," +
					      "TO_CHAR(VALIDITY_START_DATE,'DD/MM/YYYY'),TO_CHAR(VALIDITY_END_DATE,'DD/MM/YYYY')," +
					      "MRKT_LC_AMOUNT,BANK_LC_AMOUNT,TO_CHAR(LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
					      "To_CHAR(AMENDMENT_DATE,'DD/MM/YYYY'),AMENDMENT_FLAG,REMARKS " +
					      "FROM FMS7_LC_FINANCE_MST WHERE BANK_LC_NO='"+bank_lc_no+"' AND AMENDMENT_NO='"+amendment_no+"' ";			
			//System.out.println("Query for fetching FMS7_LC_FINANCE_MST = "+queryString);
			rset = stmt.executeQuery(queryString);			
			while(rset.next())
			{
				lc_fin_year = rset.getString(1)==null?"":rset.getString(1);
				lc_seq_no = rset.getString(2)==null?"":rset.getString(2);
				//customer_cd  = rset.getString(3)==null?"":rset.getString(3);
				bank_cd = rset.getString(4)==null?"":rset.getString(4);
				customer_nm = rset.getString(5)==null?"":rset.getString(5);
				bank_name = rset.getString(6)==null?"":rset.getString(6);
				bank_rating = rset.getString(7)==null?"":rset.getString(7);
				rating_eff_date = rset.getString(8)==null?"":rset.getString(8);
				validity_st_dt = rset.getString(9)==null?"":rset.getString(9);
				validity_end_dt = rset.getString(10)==null?"":rset.getString(10);
				mrkt_lc_amt = rset.getString(11)==null?"":rset.getString(11);
				bank_lc_amt = rset.getString(12)==null?"":rset.getString(12);
				ship_dt = rset.getString(13)==null?"":rset.getString(13);
				amendment_dt = rset.getString(14)==null?"":rset.getString(14);
				amendment_flag = rset.getString(15)==null?"":rset.getString(15);
				remarks = rset.getString(16)==null?"":rset.getString(16);
				//V_bank_cd.add(rset.getString(1)==null?"":rset.getString(1));
				//V_bank_nm.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> View_LC_List() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Fetch_LC_FINANCE_MST_REGASOLD()
	{

		try
		{
			String bnk_nm = "";
			queryString="SELECT A.FINANCIAL_YEAR, A.LC_SEQ_NO, A.CUSTOMER_CD, A.BANK_CD, A.CUSTOMER_NM, A.BANK_NM," +
					      "A.BANK_RATING, TO_CHAR(A.RATING_EFF_DATE,'DD/MM/YYYY'), " +
					      "TO_CHAR(A.VALIDITY_START_DATE, 'DD/MM/YYYY'), TO_CHAR(A.VALIDITY_END_DATE,'DD/MM/YYYY')," +
					      "A.MRKT_LC_AMOUNT, A.BANK_LC_AMOUNT, TO_CHAR(A.LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
					      "TO_CHAR(A.AMENDMENT_DATE,'DD/MM/YYYY'), A.BANK_LC_NO, A.AMENDMENT_NO, B.USER_DEFINED_DCQ, A.REMARKS " +
					      " FROM FMS7_LC_FINANCE_MST A, FMS7_LC_MST B" +
					      " WHERE (A.FLAG = 'R' OR A.FLAG = 'r') " +
					      "AND A.LC_SEQ_NO = B.LC_SEQ_NO AND A.FINANCIAL_YEAR = B.FINANCIAL_YEAR" +
					      " ORDER BY A.LC_SEQ_NO,A.AMENDMENT_NO";
		//	System.out.println("Query for fetching FMS7_LC_FINANCE_MST = "+queryString);
			rset1 = stmt1.executeQuery(queryString);
			while(rset1.next())
			{
				V_FINANCIAL_YEAR.add(rset1.getString(1)==null?"":rset1.getString(1));
				V_LC_SEQ_NO.add(rset1.getString(2)==null?"":rset1.getString(2));
				V_CUSTOMER_CD.add(rset1.getString(3)==null?"":rset1.getString(3));
				V_BANK_CD.add(rset1.getString(4)==null?"":rset1.getString(4));
				V_CUSTOMER_NM.add(rset1.getString(5)==null?"":rset1.getString(5));
				
				bnk_nm = rset1.getString(6)==null?"":rset1.getString(6);
				V_BANK_RATING.add(rset1.getString(7)==null?"":rset1.getString(7));
				V_RATING_EFF_DATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				V_VALIDITY_START_DATE.add(rset1.getString(9)==null?"":rset1.getString(9));
				V_VALIDITY_END_DATE.add(rset1.getString(10)==null?"":rset1.getString(10));
				V_MRKT_LC_AMOUNT.add(rset1.getString(11)==null?"":rset1.getString(11));
				V_BANK_LC_AMOUNT.add(rset1.getString(12)==null?"":rset1.getString(12));
				V_LAST_SHIPMENT_DATE.add(rset1.getString(13)==null?"":rset1.getString(13));
				V_AMENDMENT_DATE.add(rset1.getString(14)==null?"":rset1.getString(14));
				V_BANK_LC_NO.add(rset1.getString(15)==null?"":rset1.getString(15));	
				V_AMENDMENT_NO.add(rset1.getString(16)==null?"":rset1.getString(16));
				V_LC_CONTRACT_CAPACITY.add(rset1.getString(17)==null?"0":rset1.getString(17));
				V_REMARKS.add(rset1.getString(18)==null?"":rset1.getString(18));
				
				
				
				if (bnk_nm.trim().startsWith("Other", 0) || bnk_nm.trim().startsWith("other", 0) || bnk_nm.trim().startsWith("OTHER", 0))
				{
					String arr[] = bnk_nm.trim().split("#");
					String bk_nm = "";
					if(arr.length > 2)
					{
						bk_nm = arr[1].trim();
					}
					else{
						bk_nm = "";
					}
					V_OTHER_BANK_NM.add(bk_nm);
					V_BANK_NM.add("");
				} 
				else {
					
					V_OTHER_BANK_NM.add("");
					V_BANK_NM.add(bnk_nm);
				}
				
				
				//V_OTHER_BANK_NM
			}
			//System.out.println(V_BANK_NM);
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> Fetch_LC_FINANCE_MST() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	
	}
	//INTRODUCE BY MILAN DALSANIYA 2011 NOV 04 MD20111104
	public void Fetch_LC_FINANCE_MST_REGAS_2012012()
	{

		try
		{
			queryString="SELECT FINANCIAL_YEAR,LC_SEQ_NO,CUSTOMER_CD,BANK_CD,CUSTOMER_NM,BANK_NM," +
					      "BANK_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'), " +
					      "TO_CHAR(VALIDITY_START_DATE,'DD/MM/YYYY'),TO_CHAR(VALIDITY_END_DATE,'DD/MM/YYYY')," +
					      "MRKT_LC_AMOUNT,BANK_LC_AMOUNT,TO_CHAR(LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
					      "TO_CHAR(AMENDMENT_DATE,'DD/MM/YYYY'),BANK_LC_NO, AMENDMENT_NO FROM FMS7_LC_FINANCE_MST " +
					      " WHERE FLAG = 'R' OR FLAG = 'r' ";
			System.out.println("Query for fetching FMS7_LC_FINANCE_MST = "+queryString);
			rset1 = stmt1.executeQuery(queryString);
			while(rset1.next())
			{
				V_FINANCIAL_YEAR.add(rset1.getString(1)==null?"":rset1.getString(1));
				V_LC_SEQ_NO.add(rset1.getString(2)==null?"":rset1.getString(2));
				V_CUSTOMER_CD.add(rset1.getString(3)==null?"":rset1.getString(3));
				V_BANK_CD.add(rset1.getString(4)==null?"":rset1.getString(4));
				V_CUSTOMER_NM.add(rset1.getString(5)==null?"":rset1.getString(5));
				V_BANK_NM.add(rset1.getString(6)==null?"":rset1.getString(6));
				V_BANK_RATING.add(rset1.getString(7)==null?"":rset1.getString(7));
				V_RATING_EFF_DATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				V_VALIDITY_START_DATE.add(rset1.getString(9)==null?"":rset1.getString(9));
				V_VALIDITY_END_DATE.add(rset1.getString(10)==null?"":rset1.getString(10));
				V_MRKT_LC_AMOUNT.add(rset1.getString(11)==null?"":rset1.getString(11));
				V_BANK_LC_AMOUNT.add(rset1.getString(12)==null?"":rset1.getString(12));
				V_LAST_SHIPMENT_DATE.add(rset1.getString(13)==null?"":rset1.getString(13));
				V_AMENDMENT_DATE.add(rset1.getString(14)==null?"":rset1.getString(14));
				V_BANK_LC_NO.add(rset1.getString(15)==null?"":rset1.getString(15));	
				V_AMENDMENT_NO.add(rset1.getString(16)==null?"":rset1.getString(16));
				V_LC_CONTRACT_CAPACITY.add(rset1.getString(17)==null?"0":rset1.getString(17));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> Fetch_LC_FINANCE_MST() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	public void Fetch_LC_FINANCE_MST()
	{
		try
		{
			queryString="SELECT FINANCIAL_YEAR,LC_SEQ_NO,CUSTOMER_CD,BANK_CD,CUSTOMER_NM,BANK_NM," +
					      "BANK_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'), " +
					      "TO_CHAR(VALIDITY_START_DATE,'DD/MM/YYYY'),TO_CHAR(VALIDITY_END_DATE,'DD/MM/YYYY')," +
					      "MRKT_LC_AMOUNT,BANK_LC_AMOUNT,TO_CHAR(LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
					      "TO_CHAR(AMENDMENT_DATE,'DD/MM/YYYY'),BANK_LC_NO, AMENDMENT_NO FROM FMS7_LC_FINANCE_MST " +
					      " where FLAG = 'Y' OR FLAG = 'y'";
			//System.out.println("Query for fetching FMS7_LC_FINANCE_MST = "+queryString);
			rset1 = stmt1.executeQuery(queryString);
			while(rset1.next())
			{
				V_FINANCIAL_YEAR.add(rset1.getString(1)==null?"":rset1.getString(1));
				V_LC_SEQ_NO.add(rset1.getString(2)==null?"":rset1.getString(2));
				V_CUSTOMER_CD.add(rset1.getString(3)==null?"":rset1.getString(3));
				V_BANK_CD.add(rset1.getString(4)==null?"":rset1.getString(4));
				V_CUSTOMER_NM.add(rset1.getString(5)==null?"":rset1.getString(5));
				V_BANK_NM.add(rset1.getString(6)==null?"":rset1.getString(6));
				V_BANK_RATING.add(rset1.getString(7)==null?"":rset1.getString(7));
				V_RATING_EFF_DATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				V_VALIDITY_START_DATE.add(rset1.getString(9)==null?"":rset1.getString(9));
				V_VALIDITY_END_DATE.add(rset1.getString(10)==null?"":rset1.getString(10));
				V_MRKT_LC_AMOUNT.add(rset1.getString(11)==null?"":rset1.getString(11));
				V_BANK_LC_AMOUNT.add(rset1.getString(12)==null?"":rset1.getString(12));
				V_LAST_SHIPMENT_DATE.add(rset1.getString(13)==null?"":rset1.getString(13));
				V_AMENDMENT_DATE.add(rset1.getString(14)==null?"":rset1.getString(14));
				V_BANK_LC_NO.add(rset1.getString(15)==null?"":rset1.getString(15));	
				V_AMENDMENT_NO.add(rset1.getString(16)==null?"":rset1.getString(16));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> Fetch_LC_FINANCE_MST() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Fetch_LC_FINANCE_MST_20120102()
	{
		try
		{
			queryString="SELECT FINANCIAL_YEAR,LC_SEQ_NO,CUSTOMER_CD,BANK_CD,CUSTOMER_NM,BANK_NM," +
					      "BANK_RATING,TO_CHAR(RATING_EFF_DATE,'DD/MM/YYYY'), " +
					      "TO_CHAR(VALIDITY_START_DATE,'DD/MM/YYYY'),TO_CHAR(VALIDITY_END_DATE,'DD/MM/YYYY')," +
					      "MRKT_LC_AMOUNT,BANK_LC_AMOUNT,TO_CHAR(LAST_SHIPMENT_DATE,'DD/MM/YYYY')," +
					      "TO_CHAR(AMENDMENT_DATE,'DD/MM/YYYY'),BANK_LC_NO, AMENDMENT_NO FROM FMS7_LC_FINANCE_MST";
			System.out.println("Query for fetching FMS7_LC_FINANCE_MST = "+queryString);
			rset1 = stmt1.executeQuery(queryString);
			while(rset1.next())
			{
				V_FINANCIAL_YEAR.add(rset1.getString(1)==null?"":rset1.getString(1));
				V_LC_SEQ_NO.add(rset1.getString(2)==null?"":rset1.getString(2));
				V_CUSTOMER_CD.add(rset1.getString(3)==null?"":rset1.getString(3));
				V_BANK_CD.add(rset1.getString(4)==null?"":rset1.getString(4));
				V_CUSTOMER_NM.add(rset1.getString(5)==null?"":rset1.getString(5));
				V_BANK_NM.add(rset1.getString(6)==null?"":rset1.getString(6));
				V_BANK_RATING.add(rset1.getString(7)==null?"":rset1.getString(7));
				V_RATING_EFF_DATE.add(rset1.getString(8)==null?"":rset1.getString(8));
				V_VALIDITY_START_DATE.add(rset1.getString(9)==null?"":rset1.getString(9));
				V_VALIDITY_END_DATE.add(rset1.getString(10)==null?"":rset1.getString(10));
				V_MRKT_LC_AMOUNT.add(rset1.getString(11)==null?"":rset1.getString(11));
				V_BANK_LC_AMOUNT.add(rset1.getString(12)==null?"":rset1.getString(12));
				V_LAST_SHIPMENT_DATE.add(rset1.getString(13)==null?"":rset1.getString(13));
				V_AMENDMENT_DATE.add(rset1.getString(14)==null?"":rset1.getString(14));
				V_BANK_LC_NO.add(rset1.getString(15)==null?"":rset1.getString(15));	
				V_AMENDMENT_NO.add(rset1.getString(16)==null?"":rset1.getString(16));
				V_LC_CONTRACT_CAPACITY.add(rset1.getString(17)==null?"0":rset1.getString(17));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> Fetch_LC_FINANCE_MST() Method:\n"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fetchFGSADeactivationPeriod()
	{
		try
		{
			//Get Deactivation Period from FMS7_FGSA_DEACTIVATION_DTL
			queryString = "SELECT to_char(FROM_DT,'DD/MM/YYYY'), to_char(TO_DT,'DD/MM/YYYY'), STATUS FROM " +
					      "DLNG_FLSA_DEACTIVATION_DTL WHERE CUSTOMER_CD='"+bscode+"' " +
					      "AND  FLSA_NO='"+FGSA_cd+"' AND FLSA_REV_NO ='"+FGSA_REVNo+"' AND " +
					      "((sysdate BETWEEN FROM_DT AND TO_DT) OR (sysdate<FROM_DT)) " +
					      "ORDER BY FROM_DT";
			//System.out.println("SELECT QUERY FOR FROM_DT, TO_DT FROM FMS7_FGSA_DEACTIVATION_DTL --->  "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				Deactivation_from_dt = rset.getString(1)==null?"":rset.getString(1);
				Deactivation_to_dt = rset.getString(2)==null?"":rset.getString(2);
			}
			else
			{
				queryString1 = "SELECT to_char(FROM_DT,'DD/MM/YYYY'), to_char(TO_DT,'DD/MM/YYYY'), STATUS FROM " +
						      "DLNG_FLSA_DEACTIVATION_DTL WHERE CUSTOMER_CD='"+bscode+"' " +
						      "AND  FLSA_NO='"+FGSA_cd+"' AND FLSA_REV_NO ='"+FGSA_REVNo+"' " +
						      "ORDER BY TO_DT desc";
				//System.out.println("SELECT QUERY FOR FROM_DT, TO_DT FROM FMS7_FGSA_DEACTIVATION_DTL --->  "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					Deactivation_from_dt = rset1.getString(1)==null?"":rset1.getString(1);
					Deactivation_to_dt = rset1.getString(2)==null?"":rset1.getString(2);
				}
			}
			//System.out.println("Deactivation_from_dt FROM_DT = "+Deactivation_from_dt);
			//System.out.println("Deactivation_to_dt TO_DT = "+Deactivation_to_dt);
			
			queryString = "SELECT to_char(END_DT,'DD/MM/YYYY') FROM " +
		      "DLNG_FLSA_MST WHERE CUSTOMER_CD='"+bscode+"' " +
		      "AND  FLSA_NO='"+FGSA_cd+"' AND REV_NO ='"+FGSA_REVNo+"' AND END_DT<sysdate";
			//System.out.println("SELECT QUERY FOR END_DT FROM FMS7_FGSA_MST --->  "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				FGSA_status = "Agreement Over";
			}
			else
			{
				queryString = "SELECT to_char(FROM_DT,'DD/MM/YYYY'), to_char(TO_DT,'DD/MM/YYYY'), STATUS FROM " +
						      "DLNG_FLSA_DEACTIVATION_DTL WHERE CUSTOMER_CD='"+bscode+"' " +
						      "AND  FLSA_NO='"+FGSA_cd+"' AND FLSA_REV_NO ='"+FGSA_REVNo+"' AND " +
						      "(sysdate BETWEEN FROM_DT AND TO_DT)";
				//System.out.println("SELECT QUERY FOR FROM_DT, TO_DT FROM FMS7_FGSA_DEACTIVATION_DTL --->  "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					FGSA_status = "Inactive";
				}
				else
				{
					FGSA_status = "Active";
				}
			}			
		}
		catch (Exception e)
		{
			System.out.println("EXCEPTION:DataBean_Contract_Master --> fetchFGSADeactivationPeriod() --> "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Following Method Has Been Impproved By Samik Shah On 28th April, 2011 ... (For Year Wise Dummy Cargo Adjustment)
	public void Fetch_CARGO_LIST_FOR_TCQ_LOA_ADJUSTMENT()
	{
		try
		{
			double losses_percentage = 1.5;
			double total_balance_qty = 0;
			double total_allocated_qty = 0;
			double reconciled_qty = 0;
			
			queryString = "SELECT DISTINCT(CARGO_REF_NO) " +
						  "FROM FMS7_LOA_CARGO_DTL " +
						  "WHERE CUSTOMER_CD="+Buyer_cd+" And TENDER_NO="+FGSA_cd+" " +
						  "AND LOA_NO="+SN_CD+" " +
						  "ORDER BY CARGO_REF_NO";			
			//System.out.println("SN Cargo Relationship Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String delv_to_dt = "";
				double total = 0;
				double total2 = 0;
				String temp_cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);				
				cargo_Ref_No.add(temp_cargo_ref_no);				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_SN_CARGO_DTL WHERE CARGO_REF_NO="+temp_cargo_ref_no+"";
				//System.out.println("Allocated Total Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_LOA_CARGO_DTL WHERE CARGO_REF_NO="+temp_cargo_ref_no+"";
				//System.out.println("Allocated Total Cargo Qty Query (LOA) = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));
				}				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_LOA_CARGO_DTL WHERE " +
							   "CARGO_REF_NO="+temp_cargo_ref_no+" AND " +
							   "CUSTOMER_CD="+Buyer_cd+" AND TENDER_NO="+FGSA_cd+" AND " +
							   "LOA_NO="+SN_CD+"";
				//System.out.println("Allocated Cargo-LOA Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}				
				total_allocated_qty += Double.parseDouble(nf.format(total2));					
				cargo_Consumed_Qty.add(nf.format(total));
				cargo_SN_Consumed_Qty.add(nf.format(total2));				
				double actual_volume = 0;
			  	double l = 0;
			  	double conf_price = 0;
			  	double cd_charge_per_mmbtu = 0;
			  	double vol_available_for_sale = 0;
			  	
				queryString1 ="SELECT A.CARGO_REF_CD, A.MAN_CONF_CD, A.MAN_CD, B.TRADER_NAME, " +
							  "TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'), " +
							  "A.PRICE, A.CONFIRM_VOL, C.UNIT_ABR, D.CONFIRM_PRICE " +
							  "FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_TRADER_MST B, " +
							  "FMS7_UNIT_MST C, FMS7_MAN_CONFIRM_MST D, FMS7_MAN_REQ_MST E WHERE " +
							  "E.MAN_CD=A.MAN_CD AND A.CARGO_REF_CD="+temp_cargo_ref_no+" AND "+ 
							  "A.MAN_CONF_CD=D.MAN_CONF_CD AND E.TRD_CD=B.TRADER_CD AND " +
							  "A.UNIT_CD=C.UNIT_CD";
				//System.out.println("Join Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				//System.out.println(queryString1);
				if(rset1.next())
				{
					delv_to_dt = rset1.getString(6)==null?"":rset1.getString(6);
				  	cargo_Seller_Nm.add(rset1.getString(4)==null?"":rset1.getString(4));
				  	cargo_Window_From_Dt.add(rset1.getString(5)==null?"":rset1.getString(5));
				  	cargo_Window_To_Dt.add(rset1.getString(6)==null?"":rset1.getString(6));
				  	
				  	conf_price = Double.parseDouble(nf1.format(Double.parseDouble(rset1.getString(7)==null?"0":rset1.getString(7))));				  	
				  	if(conf_price<=0.0001)
				  	{
				  		conf_price = Double.parseDouble(nf1.format(Double.parseDouble(rset1.getString(10)==null?"0":rset1.getString(10))));
				  	}
				  	
				  	cargo_Conf_Price.add(nf1.format(conf_price));
				  	
				  	losses_percentage = 1.5;				  	
				  					  	
				  	queryString2 = "SELECT A.INTERNAL_CONSUMPTION " +
								  "FROM FMS7_TANK_MASTER_DTL A WHERE " +
								  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
								  "B.TANK_DTL_DT<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";					
				//	System.out.println("Tank Master Details Fetch Query = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						String internal_consumption = rset2.getString(1)==null?"":rset2.getString(1);
						if(!internal_consumption.trim().equals(""))
						{
							losses_percentage = Double.parseDouble(internal_consumption);
						}
					}
					
					queryString2 = "SELECT A.PERCENTAGE " +
								  "FROM FMS7_CONSUMPTION_MST A WHERE " +
								  "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
								  "B.EFF_DT<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";					
					//System.out.println("Internal Consumption Percentage Fetch Query = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						String internal_consumption = rset2.getString(1)==null?"":rset2.getString(1);
						if(!internal_consumption.trim().equals(""))
						{
							losses_percentage = Double.parseDouble(internal_consumption);
						}
					}
		
				  	if(rset1.getString(9).trim().equalsIgnoreCase("MMBTU"))
				  	{
				  		queryString2 = "SELECT SUM(A.RECONCIL_QTY) " +
									   "FROM FMS7_CARGO_RECONCIL_DTL A " +
									   "WHERE A.CARGO_REF_NO="+temp_cargo_ref_no+"";
						//System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString2); 
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							reconciled_qty = Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
						}
						else
						{
							reconciled_qty = 0;
						}
						cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
				  		actual_volume = Double.parseDouble(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8)))));
				  		vol_available_for_sale = Double.parseDouble(nf.format((actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100)));
				  		cargo_Confirmed_Qty.add(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8)))));
				  		cargo_Available_For_Sale_Qty.add(nf.format(vol_available_for_sale));
				  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
				  		if((vol_available_for_sale-total)>0)
				  		{
				  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
				  		}
				  	}
				  	else if(rset1.getString(9).trim().equalsIgnoreCase("TBTU"))
				  	{
				  		queryString2 = "SELECT SUM(A.RECONCIL_QTY) FROM FMS7_CARGO_RECONCIL_DTL A " +
									   "WHERE A.CARGO_REF_NO="+temp_cargo_ref_no+"";
						//System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString2); 
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							reconciled_qty = Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
						}
						else
						{
							reconciled_qty = 0;
						}
						cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
				  		l = Double.parseDouble(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8))*1000000)));
				  		actual_volume = l;
				  		vol_available_for_sale = Double.parseDouble(nf.format((actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100)));
				  		cargo_Confirmed_Qty.add(nf.format(l));
				  		cargo_Available_For_Sale_Qty.add(nf.format(vol_available_for_sale));
				  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
				  		if((vol_available_for_sale-total)>0)
				  		{
				  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
				  		}
				  	}
				  	else
				  	{
				  		reconciled_qty = 0;
				  		cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
				  		cargo_Confirmed_Qty.add(nf.format(l));
				  		cargo_Available_For_Sale_Qty.add(nf.format(l));
				  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
				  		if((vol_available_for_sale-total)>0)
				  		{
				  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
				  		}
				  	}
				}
				else
				{
					reconciled_qty = 0;
					cargo_Seller_Nm.add("");
				  	cargo_Window_From_Dt.add("");
				  	cargo_Window_To_Dt.add("");
				  	cargo_Conf_Price.add("0.0000");
				  	cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
				  	cargo_Confirmed_Qty.add(nf.format(l));
			  		cargo_Available_For_Sale_Qty.add(nf.format(l));
			  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
				}				
				//Following Logic Has Been Introduced By Samik Shah On 13th August, 2010 To Calculate Custom Tax Amount ...
			  	String tax_amt = "";
			  	String tax_str_cd = "0";				
				queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
							  "WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B WHERE " +
							  "B.APP_DATE<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
//				System.out.println("Custom Duty Details Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
				}				
				//System.out.println("tax_str_cd = "+tax_str_cd);				
				queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
							  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
							  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
							  "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
//				System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					//String tax_cd = rset1.getString(1);
					//String tax_factor = nf.format(Double.parseDouble(rset1.getString(2)));										
					if(rset1.getString(3).equals("1"))
					{
						tax_amt = nf1.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset1.getString(2)))/100);
					}
					else if(rset1.getString(3).equals("2"))
					{
						queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
									   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
									   "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
						//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2);
				 		rset2=stmt2.executeQuery(queryString2);
				 		if(rset2.next())
				 		{
					 			if(rset2.getString(3).equals("1"))
								{
									tax_amt = nf1.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset2.getString(2)))/100);
								}
								
					 			tax_amt = nf1.format((Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100);
				 		}
				 		else
				 		{
				 			tax_amt = ""+0.00;
				 		}			 		
					}
					else
					{
						tax_amt = ""+0.00;
					}
					
					cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
				}				
				cargo_Custom_Duty.add(nf1.format(cd_charge_per_mmbtu));
				cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price+cd_charge_per_mmbtu));
				//Following Logic Has Been Commented By Samik Shah On 13th August, 2010 ...
				/*queryString1 = "SELECT EXCHG_RATE,TOTAL_CD_AMT,EXP_DELV_QTY " +
							   "FROM FMS7_CUSTOM_DUTY WHERE " +
							   "CARGO_REF_NO="+temp_cargo_ref_no+"";
				System.out.println(queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String exchg_rate = rset1.getString(1)==null?"1":rset1.getString(1);
					String custom_duty_amt = rset1.getString(2)==null?"0":rset1.getString(2);
					String exp_delv_qty = rset1.getString(3)==null?"1":rset1.getString(3);
					
					cd_charge_per_mmbtu = Double.parseDouble(nf1.format((Double.parseDouble(custom_duty_amt)/Double.parseDouble(exp_delv_qty))/Double.parseDouble(exchg_rate)));
					
					cargo_Custom_Duty.add(nf1.format((Double.parseDouble(custom_duty_amt)/Double.parseDouble(exp_delv_qty))/Double.parseDouble(exchg_rate)));
					cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price+cd_charge_per_mmbtu));
				}
				else
				{
					cargo_Custom_Duty.add(nf1.format(Double.parseDouble("0")));
					cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price));
				}*/
				cargo_Type_Flag.add("SELF");
			}			
			//System.out.println("var_tcq = "+var_tcq+",  total_balance_qty = "+total_balance_qty+",  And  tcq_sign = "+tcq_sign);
			
			//if(Double.parseDouble(var_tcq)>0 && Double.parseDouble(var_tcq)>total_balance_qty && tcq_sign.equals("+"))
			//{
			if(Double.parseDouble(var_tcq)>0 && tcq_sign.equals("+"))
			{
				queryString = "SELECT DISTINCT(A.CARGO_REF_NO) " +
							  "FROM FMS7_LOA_CARGO_DTL A WHERE A.CARGO_REF_NO NOT IN " +
							  "(SELECT DISTINCT(B.CARGO_REF_NO) " +
							  "FROM FMS7_LOA_CARGO_DTL B " +
							  "WHERE B.CUSTOMER_CD="+Buyer_cd+" And B.TENDER_NO="+FGSA_cd+" " +
							  "AND B.LOA_NO="+SN_CD+") " +
							  "ORDER BY A.CARGO_REF_NO";
				//System.out.println("LOA Cargo Relationship Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String delv_to_dt = "";
					double total = 0;
					double total2 = 0;
					String temp_cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);					
					cargo_Ref_No.add(temp_cargo_ref_no);					
					queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_SN_CARGO_DTL WHERE CARGO_REF_NO="+temp_cargo_ref_no+"";
					//System.out.println("Allocated Cargo-SN Qty Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
						total += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
					}					
					queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_LOA_CARGO_DTL WHERE CARGO_REF_NO="+temp_cargo_ref_no+"";
					//System.out.println("Allocated Cargo-LOA Qty Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
						total += Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));
					}					
					cargo_Consumed_Qty.add(nf.format(total));
					cargo_SN_Consumed_Qty.add(nf.format(total2));					
					double actual_volume = 0;
				  	double l = 0;
				  	double conf_price = 0;
				  	double cd_charge_per_mmbtu = 0;
				  	double vol_available_for_sale = 0;				  	
					queryString1 = "SELECT A.CARGO_REF_CD, A.MAN_CONF_CD, A.MAN_CD, B.TRADER_NAME, " +
								  "TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY'), " +
								  "A.PRICE, A.CONFIRM_VOL, C.UNIT_ABR, D.CONFIRM_PRICE " +
								  "FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_TRADER_MST B, " +
								  "FMS7_UNIT_MST C, FMS7_MAN_CONFIRM_MST D, FMS7_MAN_REQ_MST E WHERE " +
								  "E.MAN_CD=A.MAN_CD AND A.CARGO_REF_CD="+temp_cargo_ref_no+" AND "+ 
								  "A.MAN_CONF_CD= D.MAN_CONF_CD AND E.TRD_CD=B.TRADER_CD AND " +
								  "A.UNIT_CD=C.UNIT_CD";
					//System.out.println("Join Query for Cargo reference No. = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					//System.out.println(queryString1);
					if(rset1.next())
					{
						delv_to_dt = rset1.getString(6)==null?"":rset1.getString(6);
						cargo_Seller_Nm.add(rset1.getString(4)==null?"":rset1.getString(4));
					  	cargo_Window_From_Dt.add(rset1.getString(5)==null?"":rset1.getString(5));
					  	cargo_Window_To_Dt.add(rset1.getString(6)==null?"":rset1.getString(6));
					  	
					  	conf_price = Double.parseDouble(nf1.format(Double.parseDouble(rset1.getString(7)==null?"0":rset1.getString(7))));					  	
					  	if(conf_price<=0.0001)
					  	{
					  		conf_price = Double.parseDouble(nf1.format(Double.parseDouble(rset1.getString(10)==null?"0":rset1.getString(10))));
					  	}
					  	
					  	cargo_Conf_Price.add(nf1.format(conf_price));
					  	
					  	losses_percentage = 1.5;					  	
					  	
					  	queryString2 = "SELECT A.INTERNAL_CONSUMPTION " +
									  "FROM FMS7_TANK_MASTER_DTL A WHERE " +
									  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
									  "B.TANK_DTL_DT<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";						
						//System.out.println("Tank Master Details Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							String internal_consumption = rset2.getString(1)==null?"":rset2.getString(1);
							if(!internal_consumption.trim().equals(""))
							{
								losses_percentage = Double.parseDouble(internal_consumption);
							}
						}
						
						queryString2 = "SELECT A.PERCENTAGE " +
									  "FROM FMS7_CONSUMPTION_MST A WHERE " +
									  "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
									  "B.EFF_DT<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";						
						//System.out.println("Internal Consumption Percentage Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							String internal_consumption = rset2.getString(1)==null?"":rset2.getString(1);
							if(!internal_consumption.trim().equals(""))
							{
								losses_percentage = Double.parseDouble(internal_consumption);
							}
						}
						
					  	if(rset1.getString(9).trim().equalsIgnoreCase("MMBTU"))
					  	{
					  		queryString2 = "SELECT SUM(A.RECONCIL_QTY) " +
										   "FROM FMS7_CARGO_RECONCIL_DTL A " +
										   "WHERE A.CARGO_REF_NO="+temp_cargo_ref_no+"";
							//System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString2); 
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								reconciled_qty = Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
							}
							else
							{
								reconciled_qty = 0;
							}
							cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
					  		actual_volume = Double.parseDouble(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8)))));
					  		vol_available_for_sale = Double.parseDouble(nf.format((actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100)));
					  		cargo_Confirmed_Qty.add(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8)))));
					  		cargo_Available_For_Sale_Qty.add(nf.format(vol_available_for_sale));
					  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
					  		if((vol_available_for_sale-total)>0)
					  		{
					  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
					  		}
					  	}
					  	else if(rset1.getString(9).trim().equalsIgnoreCase("TBTU"))
					  	{
					  		queryString2 = "SELECT SUM(A.RECONCIL_QTY) " +
										   "FROM FMS7_CARGO_RECONCIL_DTL A " +
										   "WHERE A.CARGO_REF_NO="+temp_cargo_ref_no+"";
							//System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString2); 
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								reconciled_qty = Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1));
							}
							else
							{
								reconciled_qty = 0;
							}
							cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
					  		l = Double.parseDouble(nf.format((Double.parseDouble(rset1.getString(8)==null?"0":rset1.getString(8))*1000000)));
					  		actual_volume = Double.parseDouble(nf.format(l));
					  		vol_available_for_sale = Double.parseDouble(nf.format((actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100)));
					  		cargo_Confirmed_Qty.add(nf.format(l));
					  		cargo_Available_For_Sale_Qty.add(nf.format(vol_available_for_sale));
					  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
					  		if((vol_available_for_sale-total)>0)
					  		{
					  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
					  		}
					  	}
					  	else
					  	{
					  		reconciled_qty = 0;
					  		cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
					  		cargo_Confirmed_Qty.add(nf.format(l));
					  		cargo_Available_For_Sale_Qty.add(nf.format(l));
					  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
					  		if((vol_available_for_sale-total)>0)
					  		{
					  			total_balance_qty += Double.parseDouble(nf.format((vol_available_for_sale-total)));
					  		}
					  	}
					}
					else
					{
						reconciled_qty = 0;
				  		cargo_Reconciled_Qty.add(nf.format(reconciled_qty));
						cargo_Seller_Nm.add("");
					  	cargo_Window_From_Dt.add("");
					  	cargo_Window_To_Dt.add("");
					  	cargo_Conf_Price.add("0.0000");
					  	cargo_Confirmed_Qty.add(nf.format(l));
				  		cargo_Available_For_Sale_Qty.add(nf.format(l));
				  		cargo_Balance_Qty.add(nf.format((vol_available_for_sale-total)));
					}

					//Following Logic Has Been Introduced By Achal On 22nd March, 2011 To Calculate Custom Tax Amount ...
				  	String tax_amt = "";
				  	String tax_str_cd = "0";					
					queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
								  "WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B WHERE " +
								  "B.APP_DATE<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
					//System.out.println("Custom Duty Details Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
					}
					//System.out.println("tax_str_cd = "+tax_str_cd);
					
					queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
								  "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
					//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						//String tax_cd = rset1.getString(1);
						//String tax_factor = nf.format(Double.parseDouble(rset1.getString(2)));										
						if(rset1.getString(3).equals("1"))
						{
							tax_amt = nf1.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset1.getString(2)))/100);
						}
						else if(rset1.getString(3).equals("2"))
						{
							queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										   "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
										   "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
										   "B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
							//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2);
					 		rset2=stmt2.executeQuery(queryString2);
					 		if(rset2.next())
					 		{
						 			if(rset2.getString(3).equals("1"))
									{
										tax_amt = nf1.format((Double.parseDouble(""+conf_price)*Double.parseDouble(rset2.getString(2)))/100);
									}
									
						 			tax_amt = nf1.format((Double.parseDouble(tax_amt)*Double.parseDouble(rset1.getString(2)))/100);
					 		}
					 		else
					 		{
					 			tax_amt = ""+0.000;
					 		}			 		
						}
						else
						{
							tax_amt = ""+0.000;
						}
						
						cd_charge_per_mmbtu += Double.parseDouble(tax_amt);
					}					
					cargo_Custom_Duty.add(nf1.format(cd_charge_per_mmbtu));
					cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price+cd_charge_per_mmbtu));
					
					//Following Coding Has Been Commented By Samik Shah On 13th August, 2010 ...
					/*queryString1 = "SELECT EXCHG_RATE,TOTAL_CD_AMT,EXP_DELV_QTY " +
								   "FROM FMS7_CUSTOM_DUTY WHERE " +
								   "CARGO_REF_NO="+temp_cargo_ref_no+"";
					System.out.println(queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						String exchg_rate = rset1.getString(1)==null?"1":rset1.getString(1);
						String custom_duty_amt = rset1.getString(2)==null?"0":rset1.getString(2);
						String exp_delv_qty = rset1.getString(3)==null?"1":rset1.getString(3);
						
						cd_charge_per_mmbtu = Double.parseDouble(nf1.format((Double.parseDouble(custom_duty_amt)/Double.parseDouble(exp_delv_qty))/Double.parseDouble(exchg_rate)));
						
						cargo_Custom_Duty.add(nf1.format((Double.parseDouble(custom_duty_amt)/Double.parseDouble(exp_delv_qty))/Double.parseDouble(exchg_rate)));
						cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price+cd_charge_per_mmbtu));
					}
					else
					{
						cargo_Custom_Duty.add(nf1.format(Double.parseDouble("0")));
						cargo_Cost_Per_Mmbtu.add(nf1.format(conf_price));
					}*/
					cargo_Type_Flag.add("OTHERS");
				}
			}
			
			//Following Logic Has Been Impproved By Samik Shah On 28th April, 2011 ... 
			//(For Year Wise Dummy Cargo Adjustment) ...
			String dummy_cargo_year = "";
			if(tcq_approval_dt.trim().length()>=10)
			{
				dummy_cargo_year = tcq_approval_dt.trim().substring(6);
			}
			
			//RS 09052017
			
			/*DataBean_Cargo_Procurement cargo = new DataBean_Cargo_Procurement(); 
			cargo.setCallFlag("FetchAvailableQtyForHLPLOwnAccount");
			cargo.setAlloc_dt(tcq_approval_dt);
			cargo.setStart_dt(tcq_approval_dt);
			cargo.setDcq(var_tcq);
			cargo.init();*/
			
//			Map avail_qty_map = cargo.getAvail_qty_map();
//			String available_qty = ""+avail_qty_map.get(tcq_approval_dt) == null?"0":""+avail_qty_map.get(tcq_approval_dt);
//			double hlpl_own_volume = Double.parseDouble(available_qty);
			double alloc_amt = 0;
			
		   queryString = "SELECT NVL(SUM(B.ALLOC_QTY),'0') FROM FMS7_LOA_MST A, FMS8_OWN_CARGO_DTL B "
				   		+ "WHERE A.SIGNING_DT IS NULL AND A.TENDER_NO = B.FGSA_NO AND A.LOA_NO=B.SN_NO "
				   		+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
				   		+ "B.CONTRACT_TYPE='L'";
				   rset = stmt.executeQuery(queryString);
				   if(rset.next())
				   {
					   alloc_amt = alloc_amt + rset.getDouble(1);
				   }
			
			
			if(Double.parseDouble(var_tcq)>0 && Double.parseDouble(var_tcq)>total_balance_qty && tcq_sign.equals("+") && dummy_cargo_year.trim().length()==4)
			{
				double total = 0;
				double total2 = 0;									
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_DUMMY_CARGO_DTL WHERE " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_DUMMY_CARGO_DTL WHERE " +
							   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
							   "CONTRACT_TYPE='L' AND SN_NO="+SN_CD+" AND " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				dummy_cargo_sn_allocated_qty = nf.format(total2);				
				if(total>0)
				{
					dummy_cargo_liability_qty = nf.format(total);
					dummy_cargo_surplus_qty = "";
				}
				else if(total<0)
				{
					dummy_cargo_liability_qty = "";
					dummy_cargo_surplus_qty = nf.format(total);
				}
				else
				{
					dummy_cargo_liability_qty = "";
					dummy_cargo_surplus_qty = "";
				}
				dummy_cargo_flag = "DUMMY";
				
				///RS 09052017 FOR TCQ MODIFICATION FROM HLPL OWN VOLUME ACCOUNT
				
				total = 0;
				total2 = 0;		
				
				total = alloc_amt;
			/*	hlpl_own_volume = hlpl_own_volume - total;
				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS8_OWN_CARGO_DTL WHERE " +
							   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
							   "CONTRACT_TYPE='L' AND SN_NO="+SN_CD+" AND FLAG IN ('T','Y') AND " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				own_cargo_sn_allocated_qty = nf.format(total2);				
				if(hlpl_own_volume<0)
				{
					own_cargo_liability_qty = nf.format(hlpl_own_volume);
					own_cargo_surplus_qty = "";
				}
				else if(hlpl_own_volume>0)
				{
					own_cargo_liability_qty = "";
					own_cargo_surplus_qty = nf.format(hlpl_own_volume);
				}
				else
				{
					own_cargo_liability_qty = "";
					own_cargo_surplus_qty = "";
				}
				own_cargo_flag = "OWN VOLUME ACCOUNT";*/
			}
			else if(Double.parseDouble(var_tcq)>0 && Double.parseDouble(var_tcq)>total_allocated_qty && tcq_sign.equals("-") && dummy_cargo_year.trim().length()==4)
			{
				double total = 0;
				double total2 = 0;									
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_DUMMY_CARGO_DTL WHERE " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS7_DUMMY_CARGO_DTL WHERE " +
							   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
							   "CONTRACT_TYPE='L' AND SN_NO="+SN_CD+" AND " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				
				if(total2>0)
				{
					dummy_cargo_sn_allocated_qty = nf.format(total2);					
					if(total>0)
					{
						dummy_cargo_liability_qty = nf.format(total);
						dummy_cargo_surplus_qty = "";
					}
					else if(total<0)
					{
						dummy_cargo_liability_qty = "";
						dummy_cargo_surplus_qty = nf.format(total);
					}
					else
					{
						dummy_cargo_liability_qty = "";
						dummy_cargo_surplus_qty = "";
					}
					dummy_cargo_flag = "DUMMY";
				}
				//RS09052017 FOR HLPL OWN VOLUME ACCOUNT
				
				total = 0;
				total2 = 0;	
				
				total = alloc_amt;
				/*hlpl_own_volume = hlpl_own_volume - total;
				
				queryString1 = "SELECT SUM(ALLOC_QTY) FROM FMS8_OWN_CARGO_DTL WHERE " +
							   "CUSTOMER_CD="+Buyer_cd+" And FGSA_NO="+FGSA_cd+" AND " +
							   "CONTRACT_TYPE='L' AND SN_NO="+SN_CD+" AND FLAG IN ('T','Y') AND " +
							   "YEAR="+dummy_cargo_year.trim()+"";
				//System.out.println("Allocated Dummy-Cargo Qty Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					String allocated_qty = rset1.getString(1)==null?"0":rset1.getString(1);
					total2 = Double.parseDouble(nf.format(Double.parseDouble(allocated_qty)));					
				}
				
				if(total2>0)
				{
					own_cargo_sn_allocated_qty = nf.format(total2);					
					if(hlpl_own_volume<0)
					{
						own_cargo_liability_qty = nf.format(hlpl_own_volume);
						own_cargo_surplus_qty = "";
					}
					else if(hlpl_own_volume>0)
					{
						own_cargo_liability_qty = "";
						own_cargo_surplus_qty = nf.format(hlpl_own_volume);
					}
					else
					{
						own_cargo_liability_qty = "";
						own_cargo_surplus_qty = "";
					}
					own_cargo_flag = "OWN VOLUME ACCOUNT";
				}*/
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//Following Setter Methods are defined by Samik Shah ... On 4th March, 2010 ...
	public void setCallFlag(String callFlag){this.callFlag = callFlag;}
	public void setBuyer_name(String set_buyer_name){this.set_buyer_name = set_buyer_name;}
	public void setBscode(String bscode) {this.bscode = bscode;}
	public void setSign_date(String sign_date) {this.sign_date = sign_date;}

	
	//Following 12 String Array Setter Methods Has Been Defined By Samik Shah On 9th November, 2010 ...
	public void setBscode_arr(String[] bscode_arr) {this.bscode_arr = bscode_arr;}
	public void setFgsa_no_arr(String[] fgsa_no_arr) {this.fgsa_no_arr = fgsa_no_arr;}
	public void setFgsa_rev_no_arr(String[] fgsa_rev_no_arr) {this.fgsa_rev_no_arr = fgsa_rev_no_arr;}
	public void setSn_no_arr(String[] sn_no_arr) {this.sn_no_arr = sn_no_arr;}
	public void setSn_rev_no_arr(String[] sn_rev_no_arr) {this.sn_rev_no_arr = sn_rev_no_arr;}
	public void setTcq_arr(String[] tcq_arr) {this.tcq_arr = tcq_arr;}
	public void setDcq_arr(String[] dcq_arr) {this.dcq_arr = dcq_arr;}
	public void setOriginal_dcq_arr(String[] original_dcq_arr) {this.original_dcq_arr = original_dcq_arr;}
	public void setDatediff_arr(String[] datediff_arr) {this.datediff_arr = datediff_arr;}
	public void setRate_arr(String[] rate_arr) {this.rate_arr = rate_arr;}
	public void setStart_dt_arr(String[] start_dt_arr) {this.start_dt_arr = start_dt_arr;}
	public void setEnd_dt_arr(String[] end_dt_arr) {this.end_dt_arr = end_dt_arr;}
	public void setCont_type_arr(String[] cont_type_arr) {this.cont_type_arr = cont_type_arr;}
	
	
	//Following (4) String Setter Methods Has Been Defined By Samik Shah On 9th November, 2010 ...
	public void setLc_gen_dt(String lc_gen_dt) {this.lc_gen_dt = lc_gen_dt;}
	public void setFlag_lc_value(String flag_lc_value) {this.flag_lc_value = flag_lc_value;}
	public void setFlag_dcq_tcq(String flag_dcq_tcq) {this.flag_dcq_tcq = flag_dcq_tcq;}
	public void setDcqdays_tcqpercent_value(String dcqdays_tcqpercent_value) {this.dcqdays_tcqpercent_value = dcqdays_tcqpercent_value;}
	
	//Following (5) String Array Setter Methods Has Been Defined By Samik Shah On 23rd November, 2010 ...
	public void setLc_exchg_rate_arr(String[] lc_exchg_rate_arr) {this.lc_exchg_rate_arr = lc_exchg_rate_arr;}
	public void setOriginal_lc_exchg_rate_arr(String[] original_lc_exchg_rate_arr) {this.original_lc_exchg_rate_arr = original_lc_exchg_rate_arr;}
	public void setLc_base_remark_arr(String[] lc_base_remark_arr) {this.lc_base_remark_arr = lc_base_remark_arr;}
	public void setFlag_lc_value_arr(String[] flag_lc_value_arr) {this.flag_lc_value_arr = flag_lc_value_arr;}
	public void setFlag_dcq_tcq_arr(String[] flag_dcq_tcq_arr) {this.flag_dcq_tcq_arr = flag_dcq_tcq_arr;}
	public void setDcqdays_tcqpercent_value_arr(String[] dcqdays_tcqpercent_value_arr) {this.dcqdays_tcqpercent_value_arr = dcqdays_tcqpercent_value_arr;}
	
	//Following (1) String Array Setter Method Has Been Introduced By Samik Shah On 30th March, 2011 ...
	public void setTax_rate_arr(String[] tax_rate_arr) {this.tax_rate_arr = tax_rate_arr;}
	
	
	//Following (6) String Setter Methods Has Been Defined By Samik Shah On 24th November, 2010 ...
	public void setLc_from_dt(String lc_from_dt) {this.lc_from_dt = lc_from_dt;}
	public void setLc_to_dt(String lc_to_dt) {this.lc_to_dt = lc_to_dt;}
	public void setLc_manual_exchg_rate(String lc_manual_exchg_rate) {this.lc_manual_exchg_rate = lc_manual_exchg_rate;}
	public void setLc_manual_exchg_rate_flag(String lc_manual_exchg_rate_flag) {this.lc_manual_exchg_rate_flag = lc_manual_exchg_rate_flag;}
	public void setLc_method(String lc_method) {this.lc_method = lc_method;}
	public void setUser_defined_dcq(String user_defined_dcq) {this.user_defined_dcq = user_defined_dcq;}
	
	//Following Vector Getter Methods are defined By Samik Shah ... 10th March, 2010 ...
	public Vector getTransporter_cd() {return transporter_cd;}
	public Vector getTransporter_nm() {return transporter_nm;}
	public Vector getTransporter_abbr() {return transporter_abbr;}
	
	//Following Vector Getter Methods are defined By Samik Shah ... 10th March, 2010 ...
	public Vector getBuyer_cd() {return buyer_cd;}
	public Vector getBuyer_nm() {return buyer_nm;}
	public Vector getBuyer_abbr() {return buyer_abbr;}
	public Vector getBuyer_plant_nm() {return buyer_plant_nm;}
	public Vector getBuyer_plant_type() {return buyer_plant_type;}
	public Vector getBuyer_plant_seq_no() {return buyer_plant_seq_no;}
	
	//Following Vector Getter Methods are defined By Samik Shah ... 10th March, 2010 ...
	public Vector getClause_cd() {return clause_cd;}
	public Vector getClause_nm() {return clause_nm;}
	public Vector getClause_abbr() {return clause_abbr;}
	public Vector getClause_mst_cd() {return clause_mst_cd;}

	public Vector getFGSA_no() {return FGSA_no;}
	public Vector getVFGSA_REV_NO() {return vFGSA_REV_NO;}
	
	public void setFGSA_no(Vector fgsa_no) {FGSA_no = fgsa_no;}
	public void setBuyer_cd(Vector buyer_cd) {this.buyer_cd = buyer_cd;}
	
	public void setBuyer_cd(String buyer_cd) {this.Buyer_cd = buyer_cd;}
	public void setFGSA_cd(String fgsa_cd) {FGSA_cd = fgsa_cd;}
	
	//Following (2) String Setter Methods Has Been Defined BY Samik Shah On 30th November, 2010 ...
	public void setLc_from_dt_2(String lc_from_dt_2) {this.lc_from_dt_2 = lc_from_dt_2;}
	public void setLc_to_dt_2(String lc_to_dt_2) {this.lc_to_dt_2 = lc_to_dt_2;}
	
	
	public void setStartDate(String st_dt) {Start_Dt = st_dt;}
	
	public void setCont_type(String cont_type) {this.cont_type = cont_type;} //Introduced By Samik Shah On 22nd November, 2010 ...
	
	//following (2) String Setter Methods Has Been Introduced By Samik Shah On 21st August, 2010 ...
	public void setRe_gas_no(String re_gas_no) {this.re_gas_no = re_gas_no;}
	public void setNo_of_cargo(String no_of_cargo) {this.no_of_cargo = no_of_cargo;}
		
	public Vector getVEND_DT() {return vEND_DT;}
	public Vector getVREV_DT() {return vREV_DT;} //Introduced By Samik Shah On 9th July, 2010 ...

	public Vector getVFGSA_BASE() {return vFGSA_BASE;	}
	public Vector getVFGSA_TYPE() {	return vFGSA_TYPE;	}
	public Vector getVSTART_DT() {return vSTART_DT;	}
	public Vector getVSTATUS() {return vSTATUS;	}
	public Vector getVLC_SEQ_NO() {return vLC_SEQ_NO;}
	public Vector getVLOALC_SEQ_NO() {return vLOALC_SEQ_NO;	}
	
	public String getBUYER_DAILY_NOM() {return BUYER_DAILY_NOM;	}
	public String getBUYER_MONTH_NOM() {return BUYER_MONTH_NOM;	}
	public String getBUYER_NOM() {	return BUYER_NOM;}
	public String getBUYER_WEEK_NOM() {	return BUYER_WEEK_NOM;}
	public String getDAY_DEF() {return DAY_DEF; }
	public String getDAY_END_TIME() {return DAY_END_TIME;}
	public String getDAY_START_TIME() {	return DAY_START_TIME;}
	public String getEND_DT() {return END_DT;	}
	public String getFGSA_BASE() {return FGSA_BASE;}
	public String getFGSA_TYPE() {return FGSA_TYPE;}
	public String getMDCQ() {return MDCQ;}
	public String getMDCQ_PERCENTAGE() {return MDCQ_PERCENTAGE;}
	public String getMEAS_STANDARD() {return MEAS_STANDARD;}
	public String getMEAS_TEMPERATURE() {return MEAS_TEMPERATURE;}
	public String getMEASUREMENT() {return MEASUREMENT;	}
	public String getOFF_SPEC_GAS() {return OFF_SPEC_GAS;}
	public String getPRESSURE_MAX_BAR() {return PRESSURE_MAX_BAR;}
	public String getPRESSURE_MIN_BAR() {return PRESSURE_MIN_BAR;}
	public String getSELLER_DAILY_NOM() {return SELLER_DAILY_NOM;}
	public String getSELLER_MONTH_NOM() {return SELLER_MONTH_NOM;}
	public String getSELLER_NOM() {return SELLER_NOM;}
	public String getSELLER_WEEK_NOM() {return SELLER_WEEK_NOM;}
	public String getSIGNING_DT() {return SIGNING_DT;}
	public String getSPEC_GAS_ENERGY_BASE() {return SPEC_GAS_ENERGY_BASE;}
	public String getSPEC_GAS_MAX_ENERGY() {return SPEC_GAS_MAX_ENERGY;	}
	public String getSPEC_GAS_MIN_ENERGY() {return SPEC_GAS_MIN_ENERGY;	}
	public String getSTART_DT() {return START_DT;	}
	public String getSTATUS() {return STATUS;}
	public String getBAbbr() {return bAbbr;}
	public String getBName() {return bName;}
	public Vector getVCLAUSE_CD() {return vCLAUSE_CD;}
	public Vector getVPLANT_SEQ_NO() {return vPLANT_SEQ_NO;}
	public Vector getVREMARK() {return vREMARK;	}
	public Vector getVTRANSPORTER_CD() {return vTRANSPORTER_CD;	}
	public String getREV_NO() {return REV_NO;}
	public String getSTORAGE_FROM_DT() {return STORAGE_FROM_DT;} //Introduced By Samik Shah On 4th January, 2011 ...
	public String getSTORAGE_TO_DT() {return STORAGE_TO_DT;} //Introduced By Samik Shah On 4th January, 2011 ...

	//Following Setter Method is defined by Samik Shah ... On 17th March, 2010 ...
	public void setTender_cd(String tender_cd) {this.tender_cd = tender_cd;}//MANOJ
	public String getRENEWAL_DT() {return RENEWAL_DT;	}
	public String getREV_DT() {return REV_DT;	} //Introduced By Samik Shah On 9th July, 2010 ...
	public Vector getVRev_No() {return vRev_No;	}
	public Vector getVFGSA_Rev_No() {return vFGSA_Rev_No;	} //SB20140603
	//Following Setter Method is defined by Nagendra Rathod... On 29th March, 2010 ...
	public Vector getTENDER_NO() {return TENDER_NO;	}
	public Vector getVTENDER_BASE() {return vTENDER_BASE;}
	public String getDCQ() {return DCQ;	}
	public String getMT() {return MT;	} //SB200701
	public String getDCQ_MT() {return DCQ_MT;	} //SB200701
	public String getSALE_PRICE() {return SALE_PRICE;	}
	public String getTCQ() {return TCQ;	}
	public String getTender_BASE() {return Tender_BASE;	}
	public String getTENDER_CLOSING_DT() {return TENDER_CLOSING_DT;	}
	public String getTENDER_SUBMIT_DT() {return TENDER_SUBMIT_DT;}
	public Vector getVBUYER_ABBR() {return vBUYER_ABBR;}
	public Vector getVRATE_UNIT() {return vRATE_UNIT;}
	public Vector getVSN_No() {return vSN_No;}
	public Vector getVSN_RATE() {return vSN_RATE;}
	public Vector getVLOA_RATE() {return vLOA_RATE;}
	public Vector getVSN_rev_No() {return vSN_rev_No;}
	public Vector getVSN_TCQ() {return vSN_TCQ;}
	public Vector getVSN_DATEDIFF() {return vSN_DATEDIFF;}
	public Vector getVLOA_DATEDIFF() {return vLOA_DATEDIFF;}
	public Vector getVSN_DCQ() {return vSN_DCQ;}
	public Vector getVLC_AMT() {return vLC_AMT;}
	public Vector getVLC_FINAL_AMT() {return vLC_FINAL_AMT;}
	public Vector getVLOA_LC_AMT() {return vLOA_LC_AMT;}
	public Vector getVTCQ_UNIT() {return vTCQ_UNIT;}
	public void setFGSA_REVNo(String no) {FGSA_REVNo = no;}
	public void setSN_CD(String sn_cd) {SN_CD = sn_cd;}
	public void setLOA_CD(String loa_cd) {LOA_CD = loa_cd;}
	public void setSN_REVNo(String no) {SN_REVNo = no;}
	public void setLOA_REVNo(String no) {LOA_REVNo = no;}
	public void setVar_tcq(String var_tcq) {this.var_tcq = var_tcq;} //Introduced By Samik Shah On 4th August, 2010 ...
	public void setTcq_sign(String tcq_sign) {this.tcq_sign = tcq_sign;} //Introduced By Samik Shah On 6th August, 2010 ...
	public void setTcq_approval_dt(String tcq_approval_dt) {this.tcq_approval_dt = tcq_approval_dt;} //Introduced By Samik Shah On 26th April, 2011 ...
	public String getGCV() {return GCV;}
	public String getNCV() {return NCV;}
	public String getRATE() {return RATE;}
	public String getRATE_UNIT() {return RATE_UNIT;}
	public String getQUANTITY_UNIT() {return QUANTITY_UNIT;}
	public Vector getVPLANT_NAME() {return vPLANT_NAME;}
	// START FG 
	public String getFgSIGNING_DT() {return fgSIGNING_DT;}
	public void setFgSIGNING_DT(String fgSIGNING_DT) {this.fgSIGNING_DT = fgSIGNING_DT;}
	public String getFgBUYER_DAILY_NOM() {return fgBUYER_DAILY_NOM;}
	public String getFgBUYER_MONTH_NOM() {return fgBUYER_MONTH_NOM;}
	public String getFgBUYER_NOM() {return fgBUYER_NOM;}
	public String getFgBUYER_WEEK_NOM() {return fgBUYER_WEEK_NOM;}
	public String getFgDAY_DEF() {return fgDAY_DEF;}
	public String getFgDAY_END_TIME() {return fgDAY_END_TIME;}
	public String getFgDAY_START_TIME() {return fgDAY_START_TIME;}
	public String getFgEND_DT() {return fgEND_DT;}
	public String getFgFGSA_BASE() {return fgFGSA_BASE;}
	public String getFgFGSA_TYPE() {return fgFGSA_TYPE;}
	public String getFgMDCQ() {return fgMDCQ;}
	public String getFgMDCQ_PERCENTAGE() {return fgMDCQ_PERCENTAGE;}
	public String getFgMEAS_STANDARD() {return fgMEAS_STANDARD;}
	public String getFgMEAS_TEMPERATURE() {return fgMEAS_TEMPERATURE;}
	public String getFgMEASUREMENT() {return fgMEASUREMENT;}
	public String getFgOFF_SPEC_GAS() {return fgOFF_SPEC_GAS;}
	public String getFgPRESSURE_MAX_BAR() {return fgPRESSURE_MAX_BAR;}
	public String getFgPRESSURE_MIN_BAR() {return fgPRESSURE_MIN_BAR;}
	public String getFgRENEWAL_DT() {return fgRENEWAL_DT;}
	public String getFgSELLER_DAILY_NOM() {return fgSELLER_DAILY_NOM;}
	public String getFgSELLER_MONTH_NOM() {return fgSELLER_MONTH_NOM;}
	public String getFgSELLER_NOM() {return fgSELLER_NOM;}
	public String getFgSELLER_WEEK_NOM() {return fgSELLER_WEEK_NOM;}
	public String getFgSPEC_GAS_ENERGY_BASE() {return fgSPEC_GAS_ENERGY_BASE;}
	public String getFgSPEC_GAS_MAX_ENERGY() {return fgSPEC_GAS_MAX_ENERGY;}
	public String getFgSPEC_GAS_MIN_ENERGY() {return fgSPEC_GAS_MIN_ENERGY;}
	public String getFgSTART_DT() {return fgSTART_DT;}
	public String getFgSTATUS() {return fgSTATUS;}//END OF FG	
	// START OF TR
	public String getTRSIGNING_DT() {return trSIGNING_DT;}
	public void settrSIGNING_DT(String fgSIGNING_DT) {this.fgSIGNING_DT = fgSIGNING_DT;}
	public String getTRBUYER_DAILY_NOM() {return trBUYER_DAILY_NOM;}
	public String getTRBUYER_MONTH_NOM() {return trBUYER_MONTH_NOM;}
	public String getTRBUYER_NOM() {return trBUYER_NOM;}
	public String getTRBUYER_WEEK_NOM() {return trBUYER_WEEK_NOM;}
	public String getTRDAY_DEF() {return trDAY_DEF;}
	public String getTRDAY_END_TIME() {return trDAY_END_TIME;}
	public String getTRDAY_START_TIME() {return trDAY_START_TIME;}
	public String getTREND_DT() {return trEND_DT;}
	public String getTRTENDER_BASE() {return trTENDER_BASE;}
	public String getTRFGSA_TYPE() {return fgFGSA_TYPE;}
	public String getTRMDCQ() {return trMDCQ;}
	public String getTRMDCQ_PERCENTAGE() {return trMDCQ_PERCENTAGE;}
	public String getTRMEAS_STANDARD() {return trMEAS_STANDARD;}
	public String getTRMEAS_TEMPERATURE() {return trMEAS_TEMPERATURE;}
	public String getTRMEASUREMENT() {return trMEASUREMENT;}
	public String getTROFF_SPEC_GAS() {return trOFF_SPEC_GAS;}
	public String getTRPRESSURE_MAX_BAR() {return trPRESSURE_MAX_BAR;}
	public String getTRPRESSURE_MIN_BAR() {return trPRESSURE_MIN_BAR;}
	public String getTRRENEWAL_DT() {return trRENEWAL_DT;}
	public String getTRSELLER_DAILY_NOM() {return trSELLER_DAILY_NOM;}
	public String getTRSELLER_MONTH_NOM() {return trSELLER_MONTH_NOM;}
	public String getTRSELLER_NOM() {return trSELLER_NOM;}
	public String getTRSELLER_WEEK_NOM() {return trSELLER_WEEK_NOM;}
	public String getTRSPEC_GAS_ENERGY_BASE() {return trSPEC_GAS_ENERGY_BASE;}
	public String getTRSPEC_GAS_MAX_ENERGY() {return trSPEC_GAS_MAX_ENERGY;}
	public String getTRSPEC_GAS_MIN_ENERGY() {return trSPEC_GAS_MIN_ENERGY;}
	public String getTRSTART_DT() {return trSTART_DT;}
	public String getTRSTATUS() {return trSTATUS;}// END OF TR
	public String getAPPROVE_FLAG() {return APPROVE_FLAG;}
	public String getVERIFY_FLAG() {return VERIFY_FLAG;}
	public void setDCQ(String dcq) {DCQ = dcq;}
	public String getVARIATION_MODE() {return VARIATION_MODE;}
	public String getVARIATION_QTY() {return VARIATION_QTY;}
	public Vector getSn_clause_abbr() {return sn_clause_abbr;}
	public Vector getLOA_clause_abbr() {return LOA_clause_abbr;}
	public Vector getSn_clause_cd() {return sn_clause_cd;}
	public Vector getLOA_clause_cd() {return LOA_clause_cd;}
	public Vector getSn_clause_nm() {return sn_clause_nm;}
	public Vector getLOA_clause_nm() {return LOA_clause_nm;}
	public Vector getSn_transporter_cd() {return sn_transporter_cd;}
	public Vector getLOA_transporter_cd() {return LOA_transporter_cd;}
	public Vector getSn_transporter_nm() {return sn_transporter_nm;}
	public Vector getSn_transporter_abbr() {return sn_transporter_abbr;}
	public Vector getLOA_transporter_abbr() {return LOA_transporter_abbr;}
	public Vector getSn_vPLANT_NAME() {return sn_vPLANT_NAME;}
	public Vector getLOA_vPLANT_NAME() {return LOA_vPLANT_NAME;}
	public Vector getSn_vPLANT_SEQ_NO() {return sn_vPLANT_SEQ_NO;}
	public Vector getLOA_vPLANT_SEQ_NO() {return LOA_vPLANT_SEQ_NO;}		
	public String getMDCQ_QTY_CD() {return MDCQ_QTY_CD;}
	public String getOBLIG_PERCENTAGE() {return OBLIG_PERCENTAGE;}
	public String getOBLIG_QTY_CD() {return OBLIG_QTY_CD;}
	public String getOBLIGATION() {return OBLIGATION;}
	public String getSN_NAME() {return SN_NAME;}
	public String getLOA_NAME() {return LOA_NAME;}
	public Vector getVSN_approve() {return vSN_approve;}
	public Vector getVSN_name() {return vSN_name;}
	public Vector getVInt_cal(){return vInt_cal;}
	public Vector getVInt_cd(){return vInt_cd;}
	public Vector getVExv_rt(){return vExc_rate;}
	public Vector getVExc_cd(){return vExc_cd;}
	public Vector getVSN_verify() {return vSN_verify;}
	public Vector getLOA_NO() {return vLOA_No;	}
	public Vector getLOA_Approve() {return vLOA_Approve;}
	public Vector getLOA_Rev_NO() {return vLOA_rev_No;}
	public Vector getTender_no() {return Tender_no;}
	public Vector getLOA_TCQ() {return vLOA_TCQ;}
	public Vector getLOA_DCQ() {return vLOA_DCQ;}
	public Vector getLOA_RATE() {return vLOA_RATE;}
	public Vector getLOA_name() {return vLOA_name;}
	public Vector getLOA_verify() {return vLOA_verify;}
	public Vector getTAX_Str_CD() {return TAX_Str_CD;}
	public Vector getTAX_Remarks() {return TAX_Remarks;}
	public Vector getTAX_Plant_Nm() {return TAX_Plant_Nm;}
	public Vector getbuyer_Credit_Rate() {return buyer_Credit_Rate;}

	public Vector getSN_Status() {return vStatus;}
	public Vector getLOA_Status() {return vLOAStatus;}
	
	public Vector getSN_StartDate() {return vSN_StartDate;}
	public Vector getSN_EndDate() {return vSN_EndDate;	}
	public Vector getLOA_StartDate() {return vLOA_StartDate;}
	public Vector getLOA_EndDate() {return vLOA_EndDate;}
	public Vector getVTAX_TYPE() {return vTAX_TYPE;}
	public Vector getVCONT_TYPE() {return vCONT_TYPE;}
	
	public Vector getLOA_TAXTYPE() {return vLOATax_Type;}
	public Vector getLOA_TAXRATE() {return vLOATax_Rate;}
	
	public Vector getSN_TAXTYPE() {return vSNTax_Type;}
	public Vector getSN_TAXRATE() {return vSNTax_Rate;}
	
	public Vector getSN_CONT_TYPE() {return vSN_CONT_Type;}
	public Vector getLOA_CONT_TYPE() {return vLOA_CONT_Type;}
		
	public Vector getTempSN_Rate(){return vTempSN_Rate;}
	public Vector getTempSN_No(){return vTempSN_No;}
	public Vector getTempSN_rev_No(){return vTempSN_rev_No;}
	public Vector getTempSN_DCQ(){return vTempSN_DCQ;}
	public Vector getTempSN_TCQ(){return vTempSN_TCQ;}
	public Vector getTempLC_AMT(){return vTempLC_AMT;}
	public Vector getTempSN_StartDate(){return vTempSN_StartDate;}
	public Vector getTempSN_EndDate(){return vTempSN_EndDate;}
	
	public String getFreq() {return Freq;}
	public String getDue_Date() {return Due_Date;}
	public String getInt_Cal_Cd() {return Int_Cal_Cd;}
	public String getInt_Cal_Sign() {return Int_Cal_Sign;}
	public String getInt_Cal_Per() {return Int_Cal_Per;}
	public String getExchng_Rate_Cd() {return Exchng_Rate_Cd;}
	public String getExchng_Rate_Cal() {return Exchng_Rate_Cal;}
	public String getInvoice_Cur_Cd() {return Invoice_Cur_Cd;}
	public String getPayment_Cur_Cd() {return Payment_Cur_Cd;}
	public String getTax_Struct_Cd() {return Tax_Struct_Cd;}
	public String getExchg_Rate_Note() {return Exchg_Rate_Note;}
	public String getFGSA_NO() {return FGSA_cd;}
	public String getFGSA_REV_NO() {return FGSA_REVNo;}
	public String getBUYERS_CD() {return bscode;}
	public String getFlag() {return Flag;} //SB20111207
	public String getcust_nm() {return cust_nm;} //SB20111207
	public String getcust_abr() {return cust_abr;} //SB20111207
	//for liability	
	public String getprice_per1() {return price_per1;}
	public String getprice1() {return price1;}
	public String getpromise_qty1() {return promise_qty1;}
	public String getlower_per1() {return lower_per1;}
	public String getdcq1() {return dcq1;}
	public String getpndcq1() {return pndcq1;}
	public String getmdcq1() {return mdcq1;}
	public String getremarks1() {return remarks1;}
	public String getprice_per2() {return price_per2;}
	public String getprice2() {return price2;}
	public String gettop_per() {return top_per;}
	public String getactual_oblig() {return actual_oblig;}
	public String getpromise_qty2() {return promise_qty2;}
	public String getremarks2() {return remarks2;}
	public String getmakeup_period_per() {return makeup_period_per;}
	public String getrec_period_per() {return rec_period_per;}
	public String getprice_per3() {return price_per3;}
	public String getprice3() {return price3;}
	public String getremarks3() {return remarks3;}
	//Following 4 Vector Getter Methods Has Been Introduced By Samik Shah On 13th July, 2010 ...
	public Vector getSn_Dcq_From_Dt() {return sn_Dcq_From_Dt;}
	public Vector getSn_Dcq_To_Dt() {return sn_Dcq_To_Dt;}
	public Vector getSn_Dcq_Value() {return sn_Dcq_Value;}
	public Vector getSn_Dcq_Remark() {return sn_Dcq_Remark;}

	//	Following Getters is defined by Achal Pathak ... On 15th July, 2010 ...
	public String getContract_capacity() {return contract_capacity;}
	public String getContract_sent_rate() {return contract_sent_rate;}
	public String getNo_cargo() {return no_cargo;}
	public String getRe_gas_tariff() {return re_gas_tariff;}
	public String getSys_gas() {return sys_gas;}

	//Following Five Vector Getter Methods Has Been Defined By Samik Shah On 17th July, 2010 ...
	public Vector getFGSA_transporter_cd() {return FGSA_transporter_cd;}
	public Vector getFGSA_transporter_abbr() {return FGSA_transporter_abbr;}
	public Vector getFGSA_clause_cd() {return FGSA_clause_cd;}
	public Vector getFGSA_clause_nm() {return FGSA_clause_nm;}
	public Vector getFGSA_clause_abbr() {return FGSA_clause_abbr;}
	//Following Two Vector Getter Methods Has Been Defined By Samik Shah On 2nd August, 2010 ...
	public Vector getVSTART_DT2() {return vSTART_DT2;}
	public Vector getVEND_DT2() {return vEND_DT2;}
	public String getLd_from_dt() {return ld_from_dt;}
	public String getLd_to_dt() {return ld_to_dt;}
	public String getTop_from_dt() {return top_from_dt;}
	public String getTop_to_dt() {return top_to_dt;}
	public void setRATE(String rate) {RATE = rate;}
	//Following 12 Vector Getter Methods Has Been Introduced By Samik Shah On 3rd August, 2010 ...
	public Vector getCargo_Ref_No() {return cargo_Ref_No;}
	public Vector getCargo_Seller_Nm() {return cargo_Seller_Nm;}
	public Vector getCargo_Window_From_Dt() {return cargo_Window_From_Dt;}
	public Vector getCargo_Window_To_Dt() {return cargo_Window_To_Dt;}
	public Vector getCargo_Conf_Price() {return cargo_Conf_Price;}
	public Vector getCargo_Custom_Duty() {return cargo_Custom_Duty;}
	public Vector getCargo_Cost_Per_Mmbtu() {return cargo_Cost_Per_Mmbtu;}
	public Vector getCargo_Confirmed_Qty() {return cargo_Confirmed_Qty;}
	public Vector getCargo_Available_For_Sale_Qty() {return cargo_Available_For_Sale_Qty;}
	public Vector getCargo_Consumed_Qty() {return cargo_Consumed_Qty;}
	public Vector getCargo_SN_Consumed_Qty() {return cargo_SN_Consumed_Qty;}
	public Vector getCargo_Balance_Qty() {return cargo_Balance_Qty;}
	//Following Vector Getter Method Has Been Introduced By Samik Shah On 4th August, 2010 ...
	public Vector getCargo_Type_Flag() {return cargo_Type_Flag;}
	//Following 5 String Getter Methods Has Been Introduced By Samik Shah On 4th August, 2010 ...
	public String getDummy_cargo_liability_qty() {return dummy_cargo_liability_qty;}
	public String getDummy_cargo_surplus_qty() {return dummy_cargo_surplus_qty;}
	public String getDummy_cargo_flag() {return dummy_cargo_flag;}
	public String getDummy_cargo_sn_allocated_qty() {return dummy_cargo_sn_allocated_qty;}
	public String getALLOCATED_QTY() {return ALLOCATED_QTY;}
	//Following 9 Vector Getter Methods Has Been Introduced By Achal On 9th August, 2010 ...
	public Vector getTender_clause_cd() {return Tender_clause_cd;}
	public Vector getTender_clause_nm() {return Tender_clause_nm;}
	public Vector getTender_clause_abbr() {return Tender_clause_abbr;}
	public Vector getTender_transporter_abbr() {return Tender_transporter_abbr;}
	public Vector getTender_transporter_cd() {return Tender_transporter_cd;}
	public Vector getLoa_Dcq_From_Dt() {return loa_Dcq_From_Dt;	}
	public Vector getLoa_Dcq_Remark() {return loa_Dcq_Remark;}
	public Vector getLoa_Dcq_To_Dt() {return loa_Dcq_To_Dt;}
	public Vector getLoa_Dcq_Value() {return loa_Dcq_Value;}
	//Following 3 String Getter Methods Has Been Introduced By Samik Shah On 10th August, 2010 ...
	public String getSN_CLOSURE_FLAG() {return SN_CLOSURE_FLAG;}
	public String getSN_CLOSURE_QTY() {return SN_CLOSURE_QTY;}
	public String getSN_CLOSURE_DT() {return SN_CLOSURE_DT;}
	//Following String Getter Method Has Been Introduced By Achal On 10th August, 2010 ...
	public String getDCQ_FLAG() {return DCQ_FLAG;}
	//Following Vector Getter Method Is Introduced By Samik Shah On 20th August, 2010 ...
	public Vector getNO_OF_CARGO() {return NO_OF_CARGO;}
	//Following (15) Vectors Getter Methods Has Been Introduced By Samik Shah On 21st August, 2010 ...
	public Vector getCARGO_REF_NO() {return CARGO_REF_NO;}
	public Vector getEDQ_FROM_DT() {return EDQ_FROM_DT;}
	public Vector getEDQ_TO_DT() {return EDQ_TO_DT;}
	public Vector getACTUAL_RECPT_DT() {return ACTUAL_RECPT_DT;}
	public Vector getCONTRACT_START_DT() {return CONTRACT_START_DT;}
	public Vector getCONTRACT_END_DT() {return CONTRACT_END_DT;}
	public Vector getSHIP_CD() {return SHIP_CD;}
	public Vector getSHIP_NAME() {return SHIP_NAME;}
	public Vector getADQ_QTY() {return ADQ_QTY;}
	public Vector getSYS_USE_GAS() {return SYS_USE_GAS;}
	public Vector getQTY_TO_BE_SUPPLY() {return QTY_TO_BE_SUPPLY;}
	public Vector getDCQ_QTY() {return DCQ_QTY;}
	public Vector getRE_GAS_TARIFF() {return RE_GAS_TARIFF;}
	public Vector getQTY_UNIT_CD() {return QTY_UNIT_CD;}
	public Vector getQTY_UNIT_ABR() {return QTY_UNIT_ABR;}
	//Introduced By Samik Shah On 27th August, 2010 ...
	public String getRe_gas_remark() {return re_gas_remark;}
	//Following (6) String Getter Methods Has Been Introduced By Samik Shah On 28th September, 2010 ...
	public String getSN_REF_NO() {return SN_REF_NO;}
	public String getLOA_REF_NO() {return LOA_REF_NO;}
	public String getPRE_SN_REF_NO() {return PRE_SN_REF_NO;}
	public String getPRE_LOA_REF_NO() {return PRE_LOA_REF_NO;}
	public String getTRANSPORTATION_CHARGE() {return TRANSPORTATION_CHARGE;}
	public String getREMARK() {return REMARK;}
	public String getAdv_amt() {return adv_amt;}
	//Following (2) Vectors Getter Methods Has Been Introduced By Samik Shah On 28th September, 2010 ...
	public Vector getVSN_Ref_No() {return vSN_Ref_No;}
	public Vector getVLOA_Ref_No() {return vLOA_Ref_No;}
	//Following (2) Vectors Getter Methods Has Been Introduced By Achal Pathak On 2nd Octber, 2010 ...
	public Vector getCUST_CD() {return CUST_CD;}
	public Vector getCUST_NM() {return CUST_NM;}

	
	//Following (5) String Getter Methods Has Been Defined By Samik Shah On 22nd November, 2010 ...
	public String getFlag_lc_value() {return flag_lc_value;}
	public String getFlag_dcq_tcq() {return flag_dcq_tcq;}
	public String getDcqdays_tcqpercent_value() {return dcqdays_tcqpercent_value;}
	public String getExchg_rate() {return exchg_rate;}
	public String getLc_remarks() {return lc_remarks;}


	//Following 13 Vector Getter Methods Has Been Defined By Samik Shah On 23rd November, 2010 ...
	public Vector getVSN_LC_EXCHG_RATE() {return vSN_LC_EXCHG_RATE;}
	public Vector getVSN_LC_BASE_REMARK() {return vSN_LC_BASE_REMARK;}
	public Vector getVSN_LC_BASE() {return vSN_LC_BASE;}
	public Vector getVSN_LC_DCQ_TCQ_FLAG() {return vSN_LC_DCQ_TCQ_FLAG;}
	public Vector getVSN_LC_DCQ_DAYS_TCQ_PERCENTAGE() {return vSN_LC_DCQ_DAYS_TCQ_PERCENTAGE;}
	public Vector getVLOA_LC_EXCHG_RATE() {return vLOA_LC_EXCHG_RATE;}
	public Vector getVLOA_LC_BASE_REMARK() {return vLOA_LC_BASE_REMARK;}
	public Vector getVLOA_LC_BASE() {return vLOA_LC_BASE;}
	public Vector getVLOA_LC_DCQ_TCQ_FLAG() {return vLOA_LC_DCQ_TCQ_FLAG;}
	public Vector getVLOA_LC_DCQ_DAYS_TCQ_PERCENTAGE() {return vLOA_LC_DCQ_DAYS_TCQ_PERCENTAGE;}
	public Vector getVLOA_buyer_cd() {return vLOA_buyer_cd;}
	public Vector getVLOA_buyer_nm() {return vLOA_buyer_nm;}
	public Vector getVLOA_BUYER_ABBR() {return vLOA_BUYER_ABBR;}


	//Following String Getter Method Has Been Defined By Samik Shah On 26th November, 2010 ...
	public String getTotal_lc_amount() {return total_lc_amount;}


	//Following (10) Vector Getter Methods Has Been Defined By Samik Shah On 30th November, 2010 ...
	public Vector getVCREDIT_RATING() {return vCREDIT_RATING;}
	public Vector getVCREDIT_RATE_EFF_DT() {return vCREDIT_RATE_EFF_DT;}
	public Vector getVLC_REF_DT() {return vLC_REF_DT;}
	public Vector getVLC_START_DT() {return vLC_START_DT;}
	public Vector getVLC_END_DT() {return vLC_END_DT;}
	public Vector getVMANUAL_EXCHG_RATE_FLAG() {return vMANUAL_EXCHG_RATE_FLAG;}
	public Vector getVMANUAL_EXCHG_RATE() {return vMANUAL_EXCHG_RATE;}
	public Vector getVUSER_DEFINED_FLAG() {return vUSER_DEFINED_FLAG;}
	public Vector getVUSER_DEFINED_DCQ() {return vUSER_DEFINED_DCQ;}
	public Vector getVLC_REMARKS() {return vLC_REMARKS;}

	
	//Following String Getter Method Has Been Defined By Samik Shah On 4th December, 2010 ...
	public String getFORMULA_REMARK() {return FORMULA_REMARK;}

	//Following Vector Getter Method Has Been Introduced By Samik Shah On 22nd December, 2010 ...
	public Vector getCargo_Reconciled_Qty() {return cargo_Reconciled_Qty;}



	public String getBILLING_CLAUSE() {
		return BILLING_CLAUSE;
	}



	public String getBUYER_NOM_CLAUSE() {
		return BUYER_NOM_CLAUSE;
	}



	public String getLC_CLAUSE() {
		return LC_CLAUSE;
	}



	public String getLIABILITY_CLAUSE() {
		return LIABILITY_CLAUSE;
	}



	public String getSELLER_NOM_CLAUSE() {
		return SELLER_NOM_CLAUSE;
	}



	public String getTENDER_BILLING_CLAUSE() {
		return TENDER_BILLING_CLAUSE;
	}



	public String getTENDER_BUYER_NOM_CLAUSE() {
		return TENDER_BUYER_NOM_CLAUSE;
	}



	public String getTENDER_LC_CLAUSE() {
		return TENDER_LC_CLAUSE;
	}



	public String getTENDER_LIABILITY_CLAUSE() {
		return TENDER_LIABILITY_CLAUSE;
	}



	public String getTENDER_SELLER_NOM_CLAUSE() {
		return TENDER_SELLER_NOM_CLAUSE;
	}


	//Following 3 Vector Getter Methods Has Been Introduced By Samik Shah On 11th January, 2011 ...
	public Vector getFgsa_Deactivation_From_Dt() {return fgsa_Deactivation_From_Dt;}
	public Vector getFgsa_Deactivation_To_Dt() {return fgsa_Deactivation_To_Dt;}
	public Vector getFgsa_Deactivation_Remark() {return fgsa_Deactivation_Remark;}


	//Following (3) Vector Getter Methods Has Been Introduced By Priyanka Sharma On 12th January, 2011 ...
	public Vector getV_lc_seq_no() {return V_lc_seq_no;}
	public Vector getV_fin_year() {return V_fin_year;}
	public Vector getCUSTOMER_CD() {return CUSTOMER_CD;}

	 //Following Vector Getter Methods Has Been Introduced By Priyanka Sharma On 17th January, 2011 ...
	public Vector getV_bank_nm() {return V_bank_nm;}
	public void setV_bank_nm(Vector v_bank_nm) {V_bank_nm = v_bank_nm;}
	public void setV_lc_seq_no(Vector v_lc_seq_no) {V_lc_seq_no = v_lc_seq_no;}
	public Vector getV_bank_cd() {return V_bank_cd;}	
	public Vector getTCQ_REQUEST_FLAG() {return TCQ_REQUEST_FLAG;}
	public Vector getTCQ_REQUEST_QTY() {return TCQ_REQUEST_QTY;}
	public Vector getTCQ_REQUEST_SIGN() {return TCQ_REQUEST_SIGN;}
	public String getTcq_req_flag() {return tcq_req_flag;}
	public String getVar_tcq() {return var_tcq;}
	public String getTcq_sign() {return tcq_sign;}
	
	
//	Following Vector Getter Methods Has Been Introduced By Priyanka Sharma On 19 th January, 2011 ...
	public void setBank_lc_no(String bank_lc_no) {this.bank_lc_no = bank_lc_no;}
	public void setAmendment_no(String amendment_no) {this.amendment_no = amendment_no;}
	public String getLc_fin_year() {return lc_fin_year;}
	public String getLc_seq_no() {return lc_seq_no;}
	public String getCustomer_cd() {return customer_cd;}
	public String getBank_cd() {return bank_cd;}
	public String getCustomer_nm() {return customer_nm;}
	public String getBank_name() {return bank_name;}
	public String getBank_rating() {return bank_rating;}
	public String getRating_eff_date() {return rating_eff_date;}
	public String getValidity_st_dt() {return validity_st_dt;}
	public String getValidity_end_dt() {return validity_end_dt;}
	public String getMrkt_lc_amt() {return mrkt_lc_amt;}
	public String getBank_lc_amt() {return bank_lc_amt;}
	public String getShip_dt() {return ship_dt;}
	public String getAmendment_dt() {return amendment_dt;}
	public String getAmendment_flag() {return amendment_flag;}
	public String getRemarks() {return remarks;}
	public Vector getV_FINANCIAL_YEAR() {return V_FINANCIAL_YEAR;}
	public Vector getV_LC_SEQ_NO() {return V_LC_SEQ_NO;}
	public Vector getV_CUSTOMER_CD() {return V_CUSTOMER_CD;}
	public Vector getV_BANK_CD() {return V_BANK_CD;}
	public Vector getV_BANK_NM() {return V_BANK_NM;}
	public Vector getV_BANK_RATING() {return V_BANK_RATING;}
	public Vector getV_RATING_EFF_DATE() {return V_RATING_EFF_DATE;}
	public Vector getV_VALIDITY_START_DATE() {return V_VALIDITY_START_DATE;}
	public Vector getV_VALIDITY_END_DATE() {return V_VALIDITY_END_DATE;}
	public Vector getV_MRKT_LC_AMOUNT() {return V_MRKT_LC_AMOUNT;}
	public Vector getV_BANK_LC_AMOUNT() {return V_BANK_LC_AMOUNT;}
	public Vector getV_LAST_SHIPMENT_DATE() {return V_LAST_SHIPMENT_DATE;}
	public Vector getV_AMENDMENT_DATE() {return V_AMENDMENT_DATE;}
	public Vector getV_AMENDMENT_FLAG() {return V_AMENDMENT_FLAG;}
	public Vector getV_REMARKS() {return V_REMARKS;}
	public Vector getV_BANK_LC_NO() {return V_BANK_LC_NO;}

	public Vector getV_CUSTOMER_NM() {return V_CUSTOMER_NM;}
	public Vector getV_AMENDMENT_NO() {return V_AMENDMENT_NO;}

	public String getFCC_flag() {
		return FCC_flag;
	}

	public String getFcc_flag() {
		return fcc_flag;
	}

	public String getInv_criteria() {
		return inv_criteria;
	}


	public String getDeactivation_from_dt() {
		return Deactivation_from_dt;
	}


	public String getDeactivation_to_dt() {
		return Deactivation_to_dt;
	}


	public String getFGSA_status() {
		return FGSA_status;
	}


	public Vector getREGAS_CLOSURE_CLOSE() {
		return REGAS_CLOSURE_CLOSE;
	}


	public Vector getREGAS_CLOSURE_DT() {
		return REGAS_CLOSURE_DT;
	}


	public Vector getREGAS_CLOSURE_QTY() {
		return REGAS_CLOSURE_QTY;
	}


	public Vector getREGAS_CLOSURE_REQUEST() {
		return REGAS_CLOSURE_REQUEST;
	}


	public String getSN_CLOSURE_CLOSE() {
		return SN_CLOSURE_CLOSE;
	}


	public String getSN_CLOSURE_REQUEST() {
		return SN_CLOSURE_REQUEST;
	}


	public String getFcc_by() {
		return fcc_by;
	}


	public void setFcc_by(String fcc_by) {
		this.fcc_by = fcc_by;
	}


	public String getFcc_date() {
		return fcc_date;
	}


	public String getLOA_CLOSURE_CLOSE() {
		return LOA_CLOSURE_CLOSE;
	}


	public String getLOA_CLOSURE_REQUEST() {
		return LOA_CLOSURE_REQUEST;
	}


	public Vector getCARGO_NO_EXIST() {
		return CARGO_NO_EXIST;
	}

	//Following Vector Getter Method Has Been Introduced By Samik Shah On 2nd April, 2011 ...
	public Vector getVSN_TAX_RATE() {return vSN_TAX_RATE;}


	public String getTcq_req_close() {
		return tcq_req_close;
	}

	
	//Following (2) Vector Getter Methods Has Been Introduced By Samik Shah On 12th May, 2011 ...
	public Vector getBOE_QTY() {return BOE_QTY;}  //SB20110927
	public Vector getSUPP_CD() {return SUPP_CD;}  //SB20110927
	public Vector getSUPP_NM() {return SUPP_NM;}  //SB20110927
	public Vector getBOE_NO() {return BOE_NO;}
	public Vector getBOE_DT() {return BOE_DT;}


	public Vector getLC_REGAS_CAPACITY() {
		return LC_REGAS_CAPACITY;
	}


	public Vector getLC_REGAS_END_DT() {
		return LC_REGAS_END_DT;
	}


	public Vector getLC_REGAS_NO() {
		return LC_REGAS_NO;
	}


	public Vector getLC_REGAS_REV_NO() {
		return LC_REGAS_REV_NO;
	}


	public Vector getLC_REGAS_STR_DT() {
		return LC_REGAS_STR_DT;
	}


	public Vector getLC_FIN_YEAR_REGAS() {
		return LC_FIN_YEAR_REGAS;
	}


	public Vector getLC_FINAL_AMOUNT_REGAS() {
		return LC_FINAL_AMOUNT_REGAS;
	}


	public Vector getLC_FLAG_REGAS() {
		return LC_FLAG_REGAS;
	}


	public Vector getLC_FLG_REGAS() {
		return LC_FLG_REGAS;
	}


	public Vector getLC_REMARKS_REGAS() {
		return LC_REMARKS_REGAS;
	}


	public Vector getLC_SEQ_NO_REGAS() {
		return LC_SEQ_NO_REGAS;
	}


	public Vector getLC_STR_DT_REGAS() {
		return LC_STR_DT_REGAS;
	}


	public Vector getLC_END_DT_REGAS() {
		return LC_END_DT_REGAS;
	}


	public Vector getLC_CALC_AMT_REGAS2() {
		return LC_CALC_AMT_REGAS2;
	}


	public Vector getLC_CONT_TYPE_REGAS2() {
		return LC_CONT_TYPE_REGAS2;
	}


	public Vector getLC_CREDIT_RATING_EFF_DT_REGAS2() {
		return LC_CREDIT_RATING_EFF_DT_REGAS2;
	}


	public Vector getLC_CREDIT_RATING_REGAS2() {
		return LC_CREDIT_RATING_REGAS2;
	}


	public Vector getLC_CUSTOMER_CD_REGAS2() {
		return LC_CUSTOMER_CD_REGAS2;
	}


	public Vector getLC_DCQ_REGAS2() {
		return LC_DCQ_REGAS2;
	}


	public Vector getLC_END_DT_REGAS2() {
		return LC_END_DT_REGAS2;
	}


	public Vector getLC_FIN_YEAR_REGAS2() {
		return LC_FIN_YEAR_REGAS2;
	}


	public Vector getLC_FINAL_AMT_REGAS2() {
		return LC_FINAL_AMT_REGAS2;
	}


	public Vector getLC_FLAG_REGAS2() {
		return LC_FLAG_REGAS2;
	}


	public Vector getLC_LC_REF_DT_REGAS2() {
		return LC_LC_REF_DT_REGAS2;
	}


	public Vector getLC_MANUAL_EXCHG_RATE_REGAS2() {
		return LC_MANUAL_EXCHG_RATE_REGAS2;
	}


	public Vector getLC_REGAS_DURATION_REGAS2() {
		return LC_REGAS_DURATION_REGAS2;
	}


	public Vector getLC_REGAS_END_DT_REGAS2() {
		return LC_REGAS_END_DT_REGAS2;
	}


	public Vector getLC_REGAS_NO_REGAS2() {
		return LC_REGAS_NO_REGAS2;
	}


	public Vector getLC_REGAS_REV_NO_REGAS2() {
		return LC_REGAS_REV_NO_REGAS2;
	}


	public Vector getLC_REGAS_STR_DT_REGAS2() {
		return LC_REGAS_STR_DT_REGAS2;
	}


	public Vector getLC_REMARKS_REGAS2() {
		return LC_REMARKS_REGAS2;
	}


	public Vector getLC_SEQ_NO_REGAS2() {
		return LC_SEQ_NO_REGAS2;
	}


	public Vector getLC_STR_DT_REGAS2() {
		return LC_STR_DT_REGAS2;
	}


	public Vector getLC_TCQ_REGAS2() {
		return LC_TCQ_REGAS2;
	}


	public Vector getLC_USER_DEFINED_DCQ_REGAS2() {
		return LC_USER_DEFINED_DCQ_REGAS2;
	}


	public Vector getLC_EXCHG_RATE_REGAS2() {
		return LC_EXCHG_RATE_REGAS2;
	}


	public Vector getLC_CUST_CD_DTL() {
		return LC_CUST_CD_DTL;
	}


	public void setLc_contract_type(String lc_contract_type) {
		this.lc_contract_type = lc_contract_type;
	}
	
	public Vector getLC_CONTRACT_CAP_REGAS() {
		return LC_CONTRACT_CAP_REGAS;
	}

	//	Followinf String Variable Introduce By Jaimin on 24th Dec 2011
	public String getSN_REMARK() {return SN_REMARK;}


	public Vector getV_LC_CONTRACT_CAPACITY() {
		return V_LC_CONTRACT_CAPACITY;
	}
	
////Following (4) String Variables Has Been Defined By Jaimin Patel On 23rd Nov, 2011 ...
	public void setFrm_dt(String frm_dt) {this.frm_dt = frm_dt;}
	public void setTo_dt(String to_dt) {this.to_dt = to_dt;	}
	public String getFrm_dt() {return frm_dt;}
	public String getTo_dt() {return to_dt;}
	public void setRe_gas_rev_no(String re_gas_rev_no) {this.re_gas_rev_no = re_gas_rev_no;}

	public Vector getCount() {
		return count;
	}


	public Vector getV_OTHER_BANK_NM() {
		return V_OTHER_BANK_NM;
	}
	//	Folling Vector getter Introduce By Jaimin on 06th Jan 2012
	public Vector getV_bank_rating() {return V_bank_rating;	}


	public Vector getQQ_DT() {
		return QQ_DT;
	}


	public Vector getQQ_NO() {
		return QQ_NO;
	}
	
	public void setBuyerCd(String buyerCd) {
		BuyerCd = buyerCd;
	}


	public void setFgsaNo(String fgsaNo) {
		FgsaNo = fgsaNo;
	}


	public void setFgsaRevNo(String fgsaRevNo) {
		FgsaRevNo = fgsaRevNo;
	}


	public void setNewRevNo(String newRevNo) {
		NewRevNo = newRevNo;
	}


	public void setSnNo(String snNo) {
		SnNo = snNo;
	}


	public void setSnRefNo(String snRefNo) {
		SnRefNo = snRefNo;
	}


	public void setSnRevNo(String snRevNo) {
		SnRevNo = snRevNo;
	}


	public String getAdjust_flag() {
		return adjust_flag;
	}


	public Vector getAdjust_advance_flag() {
		return adjust_advance_flag;
	}


	public Vector getSALES_RATE_INR_RE() {
		return SALES_RATE_INR_RE;
	}


	public void setSALES_RATE_INR_RE(Vector sales_rate_inr_re) {
		SALES_RATE_INR_RE = sales_rate_inr_re;
	}


	public String getSALES_RATE_INR() {
		return SALES_RATE_INR;
	}


	public void setSALES_RATE_INR(String sales_rate_inr) {
		SALES_RATE_INR = sales_rate_inr;
	}


	public String getADJUST_FLAG() {
		return ADJUST_FLAG;
	}


	public void setADJUST_FLAG(String adjust_flag) {
		ADJUST_FLAG = adjust_flag;
	}


	public String getADVANCE_ADJUST_CLAUSE() {
		return ADVANCE_ADJUST_CLAUSE;
	}


	public void setADVANCE_ADJUST_CLAUSE(String advance_adjust_clause) {
		ADVANCE_ADJUST_CLAUSE = advance_adjust_clause;
	}

	public String getADJUST_FLAG_FGSA() {
		return ADJUST_FLAG_FGSA;
	}

	public void setADJUST_FLAG_FGSA(String adjust_flag_fgsa) {
		ADJUST_FLAG_FGSA = adjust_flag_fgsa;
	}

	public String getADJUST_FLAG_SN() {
		return ADJUST_FLAG_SN;
	}

	public void setADJUST_FLAG_SN(String adjust_flag_sn) {
		ADJUST_FLAG_SN = adjust_flag_sn;
	}

	public String getADJUST_AMT_LOA() {
		return ADJUST_AMT_LOA;
	}

	public void setADJUST_AMT_LOA(String adjust_amt_loa) {
		ADJUST_AMT_LOA = adjust_amt_loa;
	}

	public String getADJUST_AMT_SN() {
		return ADJUST_AMT_SN;
	}

	public void setADJUST_AMT_SN(String adjust_amt_sn) {
		ADJUST_AMT_SN = adjust_amt_sn;
	}

	public String getADJUST_CUR_LOA() {
		return ADJUST_CUR_LOA;
	}

	public void setADJUST_CUR_LOA(String adjust_cur_loa) {
		ADJUST_CUR_LOA = adjust_cur_loa;
	}

	public String getADJUST_CUR_SN() {
		return ADJUST_CUR_SN;
	}

	public void setADJUST_CUR_SN(String adjust_cur_sn) {
		ADJUST_CUR_SN = adjust_cur_sn;
	}

	public String getADJUST_FLAG_LOA() {
		return ADJUST_FLAG_LOA;
	}

	public void setADJUST_FLAG_LOA(String adjust_flag_loa) {
		ADJUST_FLAG_LOA = adjust_flag_loa;
	}

	public String getADJUST_FLAG_TENDER() {
		return ADJUST_FLAG_TENDER;
	}

	public void setADJUST_FLAG_TENDER(String adjust_flag_tender) {
		ADJUST_FLAG_TENDER = adjust_flag_tender;
	}

	public String getDISCOUNT_FLAG_LOA() {
		return DISCOUNT_FLAG_LOA;
	}

	public void setDISCOUNT_FLAG_LOA(String discount_flag_loa) {
		DISCOUNT_FLAG_LOA = discount_flag_loa;
	}

	public String getDISCOUNT_FLAG_SN() {
		return DISCOUNT_FLAG_SN;
	}

	public void setDISCOUNT_FLAG_SN(String discount_flag_sn) {
		DISCOUNT_FLAG_SN = discount_flag_sn;
	}

	public String getDISCOUNT_PRICE_LOA() {
		return DISCOUNT_PRICE_LOA;
	}

	public void setDISCOUNT_PRICE_LOA(String discount_price_loa) {
		DISCOUNT_PRICE_LOA = discount_price_loa;
	}

	public String getDISCOUNT_PRICE_SN() {
		return DISCOUNT_PRICE_SN;
	}

	public void setDISCOUNT_PRICE_SN(String discount_price_sn) {
		DISCOUNT_PRICE_SN = discount_price_sn;
	}

	public String getTARIFF_FLAG_LOA() {
		return TARIFF_FLAG_LOA;
	}

	public void setTARIFF_FLAG_LOA(String tariff_flag_loa) {
		TARIFF_FLAG_LOA = tariff_flag_loa;
	}

	public String getTARIFF_FLAG_SN() {
		return TARIFF_FLAG_SN;
	}

	public void setTARIFF_FLAG_SN(String tariff_flag_sn) {
		TARIFF_FLAG_SN = tariff_flag_sn;
	}

	public String getTARIFF_INR_LOA() {
		return TARIFF_INR_LOA;
	}

	public void setTARIFF_INR_LOA(String tariff_inr_loa) {
		TARIFF_INR_LOA = tariff_inr_loa;
	}

	public String getTARIFF_INR_SN() {
		return TARIFF_INR_SN;
	}

	public void setTARIFF_INR_SN(String tariff_inr_sn) {
		TARIFF_INR_SN = tariff_inr_sn;
	}

	
	public String getADJUST_FLAG_REGAS_MST() {
		return ADJUST_FLAG_REGAS_MST;
	}

	public void setADJUST_FLAG_REGAS_MST(String adjust_flag_regas_mst) {
		ADJUST_FLAG_REGAS_MST = adjust_flag_regas_mst;
	}

	public Vector getADJUST_AMT_REGAS() {
		return ADJUST_AMT_REGAS;
	}

	public void setADJUST_AMT_REGAS(Vector adjust_amt_regas) {
		ADJUST_AMT_REGAS = adjust_amt_regas;
	}

	public Vector getADJUST_CUR_REGAS() {
		return ADJUST_CUR_REGAS;
	}

	public void setADJUST_CUR_REGAS(Vector adjust_cur_regas) {
		ADJUST_CUR_REGAS = adjust_cur_regas;
	}

	public Vector getADJUST_FLAG_REGAS() {
		return ADJUST_FLAG_REGAS;
	}

	public void setADJUST_FLAG_REGAS(Vector adjust_flag_regas) {
		ADJUST_FLAG_REGAS = adjust_flag_regas;
	}

	public Vector getDISCOUNT_FLAG_REGAS() {
		return DISCOUNT_FLAG_REGAS;
	}

	public void setDISCOUNT_FLAG_REGAS(Vector discount_flag_regas) {
		DISCOUNT_FLAG_REGAS = discount_flag_regas;
	}

	public Vector getDISCOUNT_PRICE_REGAS() {
		return DISCOUNT_PRICE_REGAS;
	}

	public void setDISCOUNT_PRICE_REGAS(Vector discount_price_regas) {
		DISCOUNT_PRICE_REGAS = discount_price_regas;
	}

	public Vector getTARIFF_FLAG_REGAS() {
		return TARIFF_FLAG_REGAS;
	}

	public void setTARIFF_FLAG_REGAS(Vector tariff_flag_regas) {
		TARIFF_FLAG_REGAS = tariff_flag_regas;
	}

	public Vector getTARIFF_INR_REGAS() {
		return TARIFF_INR_REGAS;
	}

	public void setTARIFF_INR_REGAS(Vector tariff_inr_regas) {
		TARIFF_INR_REGAS = tariff_inr_regas;
	}

	public Vector getComponent_cd() {
		return component_cd;
	}

	public void setComponent_cd(Vector component_cd) {
		this.component_cd = component_cd;
	}

	public Vector getComponent_nm() {
		return component_nm;
	}

	public void setComponent_nm(Vector component_nm) {
		this.component_nm = component_nm;
	}

	public Vector getVLineFlagEnd() {
		return VLineFlagEnd;
	}

	public void setVLineFlagEnd(Vector lineFlagEnd) {
		VLineFlagEnd = lineFlagEnd;
	}

	public Vector getVLineFlagStart() {
		return VLineFlagStart;
	}

	public void setVLineFlagStart(Vector lineFlagStart) {
		VLineFlagStart = lineFlagStart;
	}

	public Vector getVSNstatus() {
		return VSNstatus;
	}

	public void setVSNstatus(Vector nstatus) {
		VSNstatus = nstatus;
	}

	public Vector getCustcd() {
		return custcd;
	}

	public void setCustcd(Vector custcd) {
		this.custcd = custcd;
	}

	public String getFunctionflag() {
		return functionflag;
	}

	public void setFunctionflag(String functionflag) {
		this.functionflag = functionflag;
	}

	public String getPlantnm1() {
		return plantnm1;
	}

	public void setPlantnm1(String plantnm1) {
		this.plantnm1 = plantnm1;
	}

	public String getPlantseq1() {
		return plantseq1;
	}

	public void setPlantseq1(String plantseq1) {
		this.plantseq1 = plantseq1;
	}

	public String getPlanttype1() {
		return planttype1;
	}

	public void setPlanttype1(String planttype1) {
		this.planttype1 = planttype1;
	}

	public void setBuyer_nm(Vector buyer_nm) {
		this.buyer_nm = buyer_nm;
	}

	public void setFcc_flag(String fcc_flag) {
		this.fcc_flag = fcc_flag;
	}

	public String getBuyernm() {
		return buyernm;
	}

	public void setBuyernm(String buyernm) {
		this.buyernm = buyernm;
	}

	public Vector getPlantseq() {
		return plantseq;
	}

	public void setPlantseq(Vector plantseq) {
		this.plantseq = plantseq;
	}

	public Vector getPlntnm() {
		return plntnm;
	}

	public void setPlntnm(Vector plntnm) {
		this.plntnm = plntnm;
	}

	public Vector getPlnttype() {
		return plnttype;
	}

	public void setPlnttype(Vector plnttype) {
		this.plnttype = plnttype;
	}

	public Vector getFccflag() {
		return fccflag;
	}

	public void setFccflag(Vector fccflag) {
		this.fccflag = fccflag;
	}

	public String getPRE_APPROVAL() {
		return PRE_APPROVAL;
	}

	public void setPRE_APPROVAL(String pRE_APPROVAL) {
		PRE_APPROVAL = pRE_APPROVAL;
	}

	public String getPRE_APPROVAL_FGSA() {
		return PRE_APPROVAL_FGSA;
	}

	public void setPRE_APPROVAL_FGSA(String pRE_APPROVAL_FGSA) {
		PRE_APPROVAL_FGSA = pRE_APPROVAL_FGSA;
	}

	public String getADVANCE_COLLECTION() {
		return ADVANCE_COLLECTION;
	}

	public void setADVANCE_COLLECTION(String aDVANCE_COLLECTION) {
		ADVANCE_COLLECTION = aDVANCE_COLLECTION;
	}

	public String getADVANCE_COLLECTION_FLAG() {
		return ADVANCE_COLLECTION_FLAG;
	}

	public void setADVANCE_COLLECTION_FLAG(String aDVANCE_COLLECTION_FLAG) {
		ADVANCE_COLLECTION_FLAG = aDVANCE_COLLECTION_FLAG;
	}

	public Vector getCust_cd() {
		return cust_cd;
	}

	public void setCust_cd(Vector cust_cd) {
		this.cust_cd = cust_cd;
	}

	public Vector getCust_name() {
		return cust_name;
	}

	public void setCust_name(Vector cust_name) {
		this.cust_name = cust_name;
	}

	public String getCustomer_cd_advance() {
		return customer_cd_advance;
	}

	public void setCustomer_cd_advance(String customer_cd_advance) {
		this.customer_cd_advance = customer_cd_advance;
	}

	public Vector getFgsano() {
		return fgsano;
	}

	public void setFgsano(Vector fgsano) {
		this.fgsano = fgsano;
	}

	public Vector getSnno() {
		return snno;
	}

	public void setSnno(Vector snno) {
		this.snno = snno;
	}

	public Vector getSnrevno() {
		return snrevno;
	}

	public void setSnrevno(Vector snrevno) {
		this.snrevno = snrevno;
	}

	public Vector getFgsarevno() {
		return fgsarevno;
	}

	public void setFgsarevno(Vector fgsarevno) {
		this.fgsarevno = fgsarevno;
	}

	public Vector getCustomercd() {
		return customercd;
	}

	public void setCustomercd(Vector customercd) {
		this.customercd = customercd;
	}

	public Vector getStartdt() {
		return startdt;
	}

	public void setStartdt(Vector startdt) {
		this.startdt = startdt;
	}

	public Vector getEnddt() {
		return enddt;
	}

	public void setEnddt(Vector enddt) {
		this.enddt = enddt;
	}

	public Vector getSigningdt() {
		return signingdt;
	}

	public void setSigningdt(Vector signingdt) {
		this.signingdt = signingdt;
	}

	public Vector getDcq() {
		return dcq;
	}

	public void setDcq(Vector dcq) {
		this.dcq = dcq;
	}

	public Vector getTcq() {
		return tcq;
	}

	public void setTcq(Vector tcq) {
		this.tcq = tcq;
	}

	public String getCustomer_nm_advance() {
		return customer_nm_advance;
	}

	public void setCustomer_nm_advance(String customer_nm_advance) {
		this.customer_nm_advance = customer_nm_advance;
	}

	public String getFgsano_fetch() {
		return fgsano_fetch;
	}

	public void setFgsano_fetch(String fgsano_fetch) {
		this.fgsano_fetch = fgsano_fetch;
	}

	public String getSnno_fetch() {
		return snno_fetch;
	}

	public void setSnno_fetch(String snno_fetch) {
		this.snno_fetch = snno_fetch;
	}

	public String getSnrevno_fetch() {
		return snrevno_fetch;
	}

	public void setSnrevno_fetch(String snrevno_fetch) {
		this.snrevno_fetch = snrevno_fetch;
	}

	public String getFgsarevno_fetch() {
		return fgsarevno_fetch;
	}

	public void setFgsarevno_fetch(String fgsarevno_fetch) {
		this.fgsarevno_fetch = fgsarevno_fetch;
	}

	public String getCustomercd_fetch() {
		return customercd_fetch;
	}

	public void setCustomercd_fetch(String customercd_fetch) {
		this.customercd_fetch = customercd_fetch;
	}

	public Vector getFetch_advance_amount() {
		return fetch_advance_amount;
	}

	public void setFetch_advance_amount(Vector fetch_advance_amount) {
		this.fetch_advance_amount = fetch_advance_amount;
	}

	public Vector getFetch_exchg_rate() {
		return fetch_exchg_rate;
	}

	public void setFetch_exchg_rate(Vector fetch_exchg_rate) {
		this.fetch_exchg_rate = fetch_exchg_rate;
	}

	public Vector getFetch_eff_dt() {
		return fetch_eff_dt;
	}

	public void setFetch_eff_dt(Vector fetch_eff_dt) {
		this.fetch_eff_dt = fetch_eff_dt;
	}

	public Vector getFetch_fgsa_no() {
		return fetch_fgsa_no;
	}

	public void setFetch_fgsa_no(Vector fetch_fgsa_no) {
		this.fetch_fgsa_no = fetch_fgsa_no;
	}

	public Vector getFetch_fgsa_rev_no() {
		return fetch_fgsa_rev_no;
	}

	public void setFetch_fgsa_rev_no(Vector fetch_fgsa_rev_no) {
		this.fetch_fgsa_rev_no = fetch_fgsa_rev_no;
	}

	public Vector getFetch_sn_no() {
		return fetch_sn_no;
	}

	public void setFetch_sn_no(Vector fetch_sn_no) {
		this.fetch_sn_no = fetch_sn_no;
	}

	public Vector getFetch_sn_rev_no() {
		return fetch_sn_rev_no;
	}

	public void setFetch_sn_rev_no(Vector fetch_sn_rev_no) {
		this.fetch_sn_rev_no = fetch_sn_rev_no;
	}

	public Vector getFetch_customer_cd() {
		return fetch_customer_cd;
	}

	public void setFetch_customer_cd(Vector fetch_customer_cd) {
		this.fetch_customer_cd = fetch_customer_cd;
	}

	public Vector getFetch_currency() {
		return fetch_currency;
	}

	public void setFetch_currency(Vector fetch_currency) {
		this.fetch_currency = fetch_currency;
	}

	public Vector getFetch_customer_name() {
		return fetch_customer_name;
	}

	public void setFetch_customer_name(Vector fetch_customer_name) {
		this.fetch_customer_name = fetch_customer_name;
	}

	public Vector getContracttype() {
		return contracttype;
	}

	public void setContracttype(Vector contracttype) {
		this.contracttype = contracttype;
	}

	public Vector getFetch_contracttype() {
		return fetch_contracttype;
	}

	public void setFetch_contracttype(Vector fetch_contracttype) {
		this.fetch_contracttype = fetch_contracttype;
	}

	public String getContracttype_fetch() {
		return contracttype_fetch;
	}

	public void setContracttype_fetch(String contracttype_fetch) {
		this.contracttype_fetch = contracttype_fetch;
	}

	public Vector getFetch_tax() {
		return fetch_tax;
	}

	public void setFetch_tax(Vector fetch_tax) {
		this.fetch_tax = fetch_tax;
	}

	public Vector getFetch_exchg_rate1() {
		return fetch_exchg_rate1;
	}

	public void setFetch_exchg_rate1(Vector fetch_exchg_rate1) {
		this.fetch_exchg_rate1 = fetch_exchg_rate1;
	}

	public Vector getFetch_eff_dt1() {
		return fetch_eff_dt1;
	}

	public void setFetch_eff_dt1(Vector fetch_eff_dt1) {
		this.fetch_eff_dt1 = fetch_eff_dt1;
	}

	public void setFlag(boolean flag) {
		this.flag_fetch = flag;
	}

	public boolean isFlag_fetch() {
		return flag_fetch;
	}

	public void setFlag_fetch(boolean flag_fetch) {
		this.flag_fetch = flag_fetch;
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

	public String getFirm_qty() {
		return firm_qty;
	}

	public void setFirm_qty(String firm_qty) {
		this.firm_qty = firm_qty;
	}

	public String getRe_qty() {
		return re_qty;
	}

	public void setRe_qty(String re_qty) {
		this.re_qty = re_qty;
	}

	public String getSplit_tcq_flag() {
		return split_tcq_flag;
	}

	public void setSplit_tcq_flag(String split_tcq_flag) {
		this.split_tcq_flag = split_tcq_flag;
	}

	public String getOwn_cargo_sn_allocated_qty() {
		return own_cargo_sn_allocated_qty;
	}

	public void setOwn_cargo_sn_allocated_qty(String own_cargo_sn_allocated_qty) {
		this.own_cargo_sn_allocated_qty = own_cargo_sn_allocated_qty;
	}

	public String getOwn_cargo_liability_qty() {
		return own_cargo_liability_qty;
	}

	public void setOwn_cargo_liability_qty(String own_cargo_liability_qty) {
		this.own_cargo_liability_qty = own_cargo_liability_qty;
	}

	public String getOwn_cargo_surplus_qty() {
		return own_cargo_surplus_qty;
	}

	public void setOwn_cargo_surplus_qty(String own_cargo_surplus_qty) {
		this.own_cargo_surplus_qty = own_cargo_surplus_qty;
	}

	public String getOwn_cargo_flag() {
		return own_cargo_flag;
	}

	public void setOwn_cargo_flag(String own_cargo_flag) {
		this.own_cargo_flag = own_cargo_flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRev_no() {
		return rev_no;
	}

	public void setRev_no(String rev_no) {
		this.rev_no = rev_no;
	}

	public Vector getLC_FINANCIAL_YEAR() {
		return LC_FINANCIAL_YEAR;
	}

	public void setLC_FINANCIAL_YEAR(Vector lC_FINANCIAL_YEAR) {
		LC_FINANCIAL_YEAR = lC_FINANCIAL_YEAR;
	}

	public Vector getLC_SEQ_NO() {
		return LC_SEQ_NO;
	}

	public void setLC_SEQ_NO(Vector lC_SEQ_NO) {
		LC_SEQ_NO = lC_SEQ_NO;
	}

	public Vector getLC_BANK_CD() {
		return LC_BANK_CD;
	}

	public void setLC_BANK_CD(Vector lC_BANK_CD) {
		LC_BANK_CD = lC_BANK_CD;
	}

	public Vector getLC_BANK_NM() {
		return LC_BANK_NM;
	}

	public void setLC_BANK_NM(Vector lC_BANK_NM) {
		LC_BANK_NM = lC_BANK_NM;
	}

	public Vector getLC_BANK_RATING() {
		return LC_BANK_RATING;
	}

	public void setLC_BANK_RATING(Vector lC_BANK_RATING) {
		LC_BANK_RATING = lC_BANK_RATING;
	}

	public Vector getLC_RATING_EFF_DATE() {
		return LC_RATING_EFF_DATE;
	}

	public void setLC_RATING_EFF_DATE(Vector lC_RATING_EFF_DATE) {
		LC_RATING_EFF_DATE = lC_RATING_EFF_DATE;
	}

	public Vector getLC_VALIDITY_START_DATE() {
		return LC_VALIDITY_START_DATE;
	}

	public void setLC_VALIDITY_START_DATE(Vector lC_VALIDITY_START_DATE) {
		LC_VALIDITY_START_DATE = lC_VALIDITY_START_DATE;
	}

	public Vector getLC_VALIDITY_END_DATE() {
		return LC_VALIDITY_END_DATE;
	}

	public void setLC_VALIDITY_END_DATE(Vector lC_VALIDITY_END_DATE) {
		LC_VALIDITY_END_DATE = lC_VALIDITY_END_DATE;
	}

	public Vector getLC_MRKT_LC_AMOUNT() {
		return LC_MRKT_LC_AMOUNT;
	}

	public void setLC_MRKT_LC_AMOUNT(Vector lC_MRKT_LC_AMOUNT) {
		LC_MRKT_LC_AMOUNT = lC_MRKT_LC_AMOUNT;
	}

	public Vector getLC_BANK_LC_AMOUNT() {
		return LC_BANK_LC_AMOUNT;
	}

	public void setLC_BANK_LC_AMOUNT(Vector lC_BANK_LC_AMOUNT) {
		LC_BANK_LC_AMOUNT = lC_BANK_LC_AMOUNT;
	}

	public Vector getLC_LAST_SHIPMENT_DATE() {
		return LC_LAST_SHIPMENT_DATE;
	}

	public void setLC_LAST_SHIPMENT_DATE(Vector lC_LAST_SHIPMENT_DATE) {
		LC_LAST_SHIPMENT_DATE = lC_LAST_SHIPMENT_DATE;
	}

	public Vector getLC_AMENDMENT_DATE() {
		return LC_AMENDMENT_DATE;
	}

	public void setLC_AMENDMENT_DATE(Vector lC_AMENDMENT_DATE) {
		LC_AMENDMENT_DATE = lC_AMENDMENT_DATE;
	}

	public Vector getLC_BANK_LC_NO() {
		return LC_BANK_LC_NO;
	}

	public void setLC_BANK_LC_NO(Vector lC_BANK_LC_NO) {
		LC_BANK_LC_NO = lC_BANK_LC_NO;
	}

	public Vector getLC_AMENDMENT_NO() {
		return LC_AMENDMENT_NO;
	}

	public void setLC_AMENDMENT_NO(Vector lC_AMENDMENT_NO) {
		LC_AMENDMENT_NO = lC_AMENDMENT_NO;
	}

	public String getContract_tp() {
		return contract_tp;
	}

	public void setContract_tp(String contract_tp) {
		this.contract_tp = contract_tp;
	}

	public boolean isBillFlag() {
		return billFlag;
	}

	public void setBillFlag(boolean billFlag) {
		this.billFlag = billFlag;
	}

	public boolean isContractEndFlag() {
		return contractEndFlag;
	}

	public void setContractEndFlag(boolean contractEndFlag) {
		this.contractEndFlag = contractEndFlag;
	}

	public String getREOPEN_REQUEST_FLAG() {
		return REOPEN_REQUEST_FLAG;
	}

	public void setREOPEN_REQUEST_FLAG(String rEOPEN_REQUEST_FLAG) {
		REOPEN_REQUEST_FLAG = rEOPEN_REQUEST_FLAG;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

	public String getCustomer_cd_() {
		return customer_cd_;
	}

	public void setCustomer_cd_(String customer_cd_) {
		this.customer_cd_ = customer_cd_;
	}

	public Vector getAPR_FGSA_NO() {
		return APR_FGSA_NO;
	}

	public void setAPR_FGSA_NO(Vector aPR_FGSA_NO) {
		APR_FGSA_NO = aPR_FGSA_NO;
	}

	public Vector getAPR_FGSA_REV_NO() {
		return APR_FGSA_REV_NO;
	}

	public void setAPR_FGSA_REV_NO(Vector aPR_FGSA_REV_NO) {
		APR_FGSA_REV_NO = aPR_FGSA_REV_NO;
	}

	public Vector getAPR_SIGNING_DT() {
		return APR_SIGNING_DT;
	}

	public void setAPR_SIGNING_DT(Vector aPR_SIGNING_DT) {
		APR_SIGNING_DT = aPR_SIGNING_DT;
	}

	public Vector getAPR_START_DT() {
		return APR_START_DT;
	}

	public void setAPR_START_DT(Vector aPR_START_DT) {
		APR_START_DT = aPR_START_DT;
	}

	public Vector getAPR_END_DT() {
		return APR_END_DT;
	}

	public void setAPR_END_DT(Vector aPR_END_DT) {
		APR_END_DT = aPR_END_DT;
	}

	public Vector getAPR_FGSA_BASE() {
		return APR_FGSA_BASE;
	}

	public void setAPR_FGSA_BASE(Vector aPR_FGSA_BASE) {
		APR_FGSA_BASE = aPR_FGSA_BASE;
	}

	public Vector getAPR_FGSA_TYPE() {
		return APR_FGSA_TYPE;
	}

	public void setAPR_FGSA_TYPE(Vector aPR_FGSA_TYPE) {
		APR_FGSA_TYPE = aPR_FGSA_TYPE;
	}

	public Vector getAPR_REOPEN_REQ_DT() {
		return APR_REOPEN_REQ_DT;
	}

	public void setAPR_REOPEN_REQ_DT(Vector aPR_REOPEN_REQ_DT) {
		APR_REOPEN_REQ_DT = aPR_REOPEN_REQ_DT;
	}

	public Vector getAPR_REOPEN_APPROVAL_FLAG() {
		return APR_REOPEN_APPROVAL_FLAG;
	}

	public void setAPR_REOPEN_APPROVAL_FLAG(Vector aPR_REOPEN_APPROVAL_FLAG) {
		APR_REOPEN_APPROVAL_FLAG = aPR_REOPEN_APPROVAL_FLAG;
	}

	public Vector getAPR_REOPEN_APPROVAL_DT() {
		return APR_REOPEN_APPROVAL_DT;
	}

	public void setAPR_REOPEN_APPROVAL_DT(Vector aPR_REOPEN_APPROVAL_DT) {
		APR_REOPEN_APPROVAL_DT = aPR_REOPEN_APPROVAL_DT;
	}

	public String getREOPEN_APPROVAL_FLAG() {
		return REOPEN_APPROVAL_FLAG;
	}

	public void setREOPEN_APPROVAL_FLAG(String rEOPEN_APPROVAL_FLAG) {
		REOPEN_APPROVAL_FLAG = rEOPEN_APPROVAL_FLAG;
	}

	public Vector getAPR_CN_NO() {
		return APR_CN_NO;
	}

	public void setAPR_CN_NO(Vector aPR_CN_NO) {
		APR_CN_NO = aPR_CN_NO;
	}

	public Vector getAPR_CN_REV_NO() {
		return APR_CN_REV_NO;
	}

	public void setAPR_CN_REV_NO(Vector aPR_CN_REV_NO) {
		APR_CN_REV_NO = aPR_CN_REV_NO;
	}

	public Vector getCAR_FGSA_NO() {
		return CAR_FGSA_NO;
	}

	public void setCAR_FGSA_NO(Vector cAR_FGSA_NO) {
		CAR_FGSA_NO = cAR_FGSA_NO;
	}

	public Vector getCAR_FGSA_REV_NO() {
		return CAR_FGSA_REV_NO;
	}

	public void setCAR_FGSA_REV_NO(Vector cAR_FGSA_REV_NO) {
		CAR_FGSA_REV_NO = cAR_FGSA_REV_NO;
	}

	public Vector getCAR_SN_NO() {
		return CAR_SN_NO;
	}

	public void setCAR_SN_NO(Vector cAR_SN_NO) {
		CAR_SN_NO = cAR_SN_NO;
	}

	public Vector getCAR_SN_REV_NO() {
		return CAR_SN_REV_NO;
	}

	public void setCAR_SN_REV_NO(Vector cAR_SN_REV_NO) {
		CAR_SN_REV_NO = cAR_SN_REV_NO;
	}

	public Vector getCAR_START_DT() {
		return CAR_START_DT;
	}

	public void setCAR_START_DT(Vector cAR_START_DT) {
		CAR_START_DT = cAR_START_DT;
	}

	public Vector getCAR_END_DT() {
		return CAR_END_DT;
	}

	public void setCAR_END_DT(Vector cAR_END_DT) {
		CAR_END_DT = cAR_END_DT;
	}

	public Vector getCAR_TCQ() {
		return CAR_TCQ;
	}

	public void setCAR_TCQ(Vector cAR_TCQ) {
		CAR_TCQ = cAR_TCQ;
	}

	public Vector getCAR_RATE() {
		return CAR_RATE;
	}

	public void setCAR_RATE(Vector cAR_RATE) {
		CAR_RATE = cAR_RATE;
	}

	public Map getCAR_REF_NO() {
		return CAR_REF_NO;
	}

	public void setCAR_REF_NO(Map cAR_REF_NO) {
		CAR_REF_NO = cAR_REF_NO;
	}

	public Map getCAR_MARGIN() {
		return CAR_MARGIN;
	}

	public void setCAR_MARGIN(Map cAR_MARGIN) {
		CAR_MARGIN = cAR_MARGIN;
	}

	public Map getCAR_AVG_MARGIN() {
		return CAR_AVG_MARGIN;
	}

	public void setCAR_AVG_MARGIN(Map cAR_AVG_MARGIN) {
		CAR_AVG_MARGIN = cAR_AVG_MARGIN;
	}

	public Map getCAR_TOT_MARGIN() {
		return CAR_TOT_MARGIN;
	}

	public void setCAR_TOT_MARGIN(Map cAR_TOT_MARGIN) {
		CAR_TOT_MARGIN = cAR_TOT_MARGIN;
	}

	public Map getCAR_SALE_PRICE() {
		return CAR_SALE_PRICE;
	}

	public void setCAR_SALE_PRICE(Map cAR_SALE_PRICE) {
		CAR_SALE_PRICE = cAR_SALE_PRICE;
	}

	public Map getCAR_ALLOC_QTY() {
		return CAR_ALLOC_QTY;
	}

	public void setCAR_ALLOC_QTY(Map cAR_ALLOC_QTY) {
		CAR_ALLOC_QTY = cAR_ALLOC_QTY;
	}

	public Vector getCAR_MAX_MODIFY_SEQ_NO() {
		return CAR_MAX_MODIFY_SEQ_NO;
	}

	public void setCAR_MAX_MODIFY_SEQ_NO(Vector cAR_MAX_MODIFY_SEQ_NO) {
		CAR_MAX_MODIFY_SEQ_NO = cAR_MAX_MODIFY_SEQ_NO;
	}

	public Vector getCAR_FLAG() {
		return CAR_FLAG;
	}

	public void setCAR_FLAG(Vector cAR_FLAG) {
		CAR_FLAG = cAR_FLAG;
	}

	public Vector getCAR_ENT_BY() {
		return CAR_ENT_BY;
	}

	public void setCAR_ENT_BY(Vector cAR_ENT_BY) {
		CAR_ENT_BY = cAR_ENT_BY;
	}

	public Vector getCAR_APPROVE_BY() {
		return CAR_APPROVE_BY;
	}

	public void setCAR_APPROVE_BY(Vector cAR_APPROVE_BY) {
		CAR_APPROVE_BY = cAR_APPROVE_BY;
	}

	public Map getCAR_NEW_SALE_PRICE() {
		return CAR_NEW_SALE_PRICE;
	}

	public void setCAR_NEW_SALE_PRICE(Map cAR_NEW_SALE_PRICE) {
		CAR_NEW_SALE_PRICE = cAR_NEW_SALE_PRICE;
	}

	public Map getCAR_NEW_MARGIN() {
		return CAR_NEW_MARGIN;
	}

	public void setCAR_NEW_MARGIN(Map cAR_NEW_MARGIN) {
		CAR_NEW_MARGIN = cAR_NEW_MARGIN;
	}

	public Map getCAR_NEW_TOT_MARGIN() {
		return CAR_NEW_TOT_MARGIN;
	}

	public void setCAR_NEW_TOT_MARGIN(Map cAR_NEW_TOT_MARGIN) {
		CAR_NEW_TOT_MARGIN = cAR_NEW_TOT_MARGIN;
	}

	public Map getCAR_NEW_AVG_MARGIN() {
		return CAR_NEW_AVG_MARGIN;
	}

	public void setCAR_NEW_AVG_MARGIN(Map cAR_NEW_AVG_MARGIN) {
		CAR_NEW_AVG_MARGIN = cAR_NEW_AVG_MARGIN;
	}

	public boolean isApprovalRequest() {
		return approvalRequest;
	}

	public void setApprovalRequest(boolean approvalRequest) {
		this.approvalRequest = approvalRequest;
	}

	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	public Vector getAPR_REOPEN_REMARK() {
		return APR_REOPEN_REMARK;
	}

	public void setAPR_REOPEN_REMARK(Vector aPR_REOPEN_REMARK) {
		APR_REOPEN_REMARK = aPR_REOPEN_REMARK;
	}

	public boolean isInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(boolean invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}

	public String getLc_amt() {
		return lc_amt;
	}

	public void setLc_amt(String lc_amt) {
		this.lc_amt = lc_amt;
	}

	public String getLc_date() {
		return lc_date;
	}

	public void setLc_date(String lc_date) {
		this.lc_date = lc_date;
	}

	public String getLc_bank_dtl() {
		return lc_bank_dtl;
	}

	public void setLc_bank_dtl(String lc_bank_dtl) {
		this.lc_bank_dtl = lc_bank_dtl;
	}

	public String getLc_remark() {
		return lc_remark;
	}

	public void setLc_remark(String lc_remark) {
		this.lc_remark = lc_remark;
	}

	public String getLc_file_nm() {
		return lc_file_nm;
	}

	public void setLc_file_nm(String lc_file_nm) {
		this.lc_file_nm = lc_file_nm;
	}

	public String getLc_flag() {
		return lc_flag;
	}

	public void setLc_flag(String lc_flag) {
		this.lc_flag = lc_flag;
	}

	public String getLc_customer_nm() {
		return lc_customer_nm;
	}

	public void setLc_customer_nm(String lc_customer_nm) {
		this.lc_customer_nm = lc_customer_nm;
	}

	public int getCountLc() {
		return countLc;
	}

	public void setCountLc(int countLc) {
		this.countLc = countLc;
	}

	public String getFcc_note() {
		return fcc_note;
	}

	public void setFcc_note(String fcc_note) {
		this.fcc_note = fcc_note;
	}

	public String getFcc_apply() {
		return fcc_apply;
	}

	public void setFcc_apply(String fcc_apply) {
		this.fcc_apply = fcc_apply;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getAgreement_base() {
		return agreement_base;
	}

	public void setAgreement_base(String agreement_base) {
		this.agreement_base = agreement_base;
	}

	public Vector getTrans_cd() {
		return trans_cd;
	}

	public void setTrans_cd(Vector trans_cd) {
		this.trans_cd = trans_cd;
	}

	public Vector getPlant_cd() {
		return plant_cd;
	}

	public void setPlant_cd(Vector plant_cd) {
		this.plant_cd = plant_cd;
	}

	public HashMap getTrans_plant_map() {
		return trans_plant_map;
	}

	public void setTrans_plant_map(HashMap trans_plant_map) {
		this.trans_plant_map = trans_plant_map;
	}

	public HashMap getTrans_plant_map_cont_st_dt() {
		return trans_plant_map_cont_st_dt;
	}

	public void setTrans_plant_map_cont_st_dt(HashMap trans_plant_map_cont_st_dt) {
		this.trans_plant_map_cont_st_dt = trans_plant_map_cont_st_dt;
	}

	public HashMap getTrans_plant_map_cont_end_dt() {
		return trans_plant_map_cont_end_dt;
	}

	public void setTrans_plant_map_cont_end_dt(HashMap trans_plant_map_cont_end_dt) {
		this.trans_plant_map_cont_end_dt = trans_plant_map_cont_end_dt;
	}

	public HashMap getTrans_plant_map_dcq() {
		return trans_plant_map_dcq;
	}

	public void setTrans_plant_map_dcq(HashMap trans_plant_map_dcq) {
		this.trans_plant_map_dcq = trans_plant_map_dcq;
	}

	public HashMap getTrans_plant_map_mdq() {
		return trans_plant_map_mdq;
	}

	public void setTrans_plant_map_mdq(HashMap trans_plant_map_mdq) {
		this.trans_plant_map_mdq = trans_plant_map_mdq;
	}

	public HashMap getTrans_nm() {
		return trans_nm;
	}

	public void setTrans_nm(HashMap trans_nm) {
		this.trans_nm = trans_nm;
	}

	public HashMap getPlant_nm() {
		return plant_nm;
	}

	public void setPlant_nm(HashMap plant_nm) {
		this.plant_nm = plant_nm;
	}

	public HashMap getCont_mapp_id() {
		return cont_mapp_id;
	}

	public void setCont_mapp_id(HashMap cont_mapp_id) {
		this.cont_mapp_id = cont_mapp_id;
	}

	public String getAgr_no() {
		return agr_no;
	}

	public void setAgr_no(String agr_no) {
		this.agr_no = agr_no;
	}

	public String getAgr_rev_no() {
		return agr_rev_no;
	}

	public void setAgr_rev_no(String agr_rev_no) {
		this.agr_rev_no = agr_rev_no;
	}

	public String getCont_rev_no() {
		return cont_rev_no;
	}

	public void setCont_rev_no(String cont_rev_no) {
		this.cont_rev_no = cont_rev_no;
	}

	public String getCont_no() {
		return cont_no;
	}

	public void setCont_no(String cont_no) {
		this.cont_no = cont_no;
	}

	public String getCont_agr_type() {
		return cont_agr_type;
	}

	public void setCont_agr_type(String cont_agr_type) {
		this.cont_agr_type = cont_agr_type;
	}

	public int getCount_price() {
		return count_price;
	}

	public void setCount_price(int count_price) {
		this.count_price = count_price;
	}

	public Vector getLOA_No() {
		return LOA_No;
	}

	public void setLOA_No(Vector lOA_No) {
		LOA_No = lOA_No;
	}

	public Vector getLOA_rev_No() {
		return LOA_rev_No;
	}

	public void setLOA_rev_No(Vector lOA_rev_No) {
		LOA_rev_No = lOA_rev_No;
	}

	public Vector getBuyer_cd_loa() {
		return buyer_cd_loa;
	}

	public void setBuyer_cd_loa(Vector buyer_cd_loa) {
		this.buyer_cd_loa = buyer_cd_loa;
	}

	public Vector getTENDER_no() {
		return TENDER_no;
	}

	public void setTENDER_no(Vector tENDER_no) {
		TENDER_no = tENDER_no;
	}

	public Vector getVloa_TCQ_UNIT() {
		return vloa_TCQ_UNIT;
	}

	public void setVloa_TCQ_UNIT(Vector vloa_TCQ_UNIT) {
		this.vloa_TCQ_UNIT = vloa_TCQ_UNIT;
	}

	public Vector getvLOA_RATE_UNIT() {
		return vLOA_RATE_UNIT;
	}

	public void setvLOA_RATE_UNIT(Vector vLOA_RATE_UNIT) {
		this.vLOA_RATE_UNIT = vLOA_RATE_UNIT;
	}

	public Vector getBuyer_nm_LOA() {
		return buyer_nm_LOA;
	}

	public void setBuyer_nm_LOA(Vector buyer_nm_LOA) {
		this.buyer_nm_LOA = buyer_nm_LOA;
	}

	public Vector getvBUYER_ABBR_LOA() {
		return vBUYER_ABBR_LOA;
	}

	public void setvBUYER_ABBR_LOA(Vector vBUYER_ABBR_LOA) {
		this.vBUYER_ABBR_LOA = vBUYER_ABBR_LOA;
	}

	public Vector getvLOA_approve() {
		return vLOA_approve;
	}

	public void setvLOA_approve(Vector vLOA_approve) {
		this.vLOA_approve = vLOA_approve;
	}

	public Vector getTCQ_REQUEST_FLAG_LOA() {
		return TCQ_REQUEST_FLAG_LOA;
	}

	public void setTCQ_REQUEST_FLAG_LOA(Vector tCQ_REQUEST_FLAG_LOA) {
		TCQ_REQUEST_FLAG_LOA = tCQ_REQUEST_FLAG_LOA;
	}

	public Vector getTCQ_REQUEST_SIGN_LOA() {
		return TCQ_REQUEST_SIGN_LOA;
	}

	public void setTCQ_REQUEST_SIGN_LOA(Vector tCQ_REQUEST_SIGN_LOA) {
		TCQ_REQUEST_SIGN_LOA = tCQ_REQUEST_SIGN_LOA;
	}

	public Vector getTCQ_REQUEST_QTY_LOA() {
		return TCQ_REQUEST_QTY_LOA;
	}

	public void setTCQ_REQUEST_QTY_LOA(Vector tCQ_REQUEST_QTY_LOA) {
		TCQ_REQUEST_QTY_LOA = tCQ_REQUEST_QTY_LOA;
	}


	public Vector getvRev_No_tender() {
		return vRev_No_tender;
	}

	public void setvRev_No_tender(Vector vRev_No_tender) {
		this.vRev_No_tender = vRev_No_tender;
	}

	public Vector getvLOA_name() {
		return vLOA_name;
	}

	public void setvLOA_name(Vector vLOA_name) {
		this.vLOA_name = vLOA_name;
	}

	public Vector getvLOA_Ref_No() {
		return vLOA_Ref_No;
	}

	public void setvLOA_Ref_No(Vector vLOA_Ref_No) {
		this.vLOA_Ref_No = vLOA_Ref_No;
	}

	public Vector getvLOA_TCQ() {
		return vLOA_TCQ;
	}

	public void setvLOA_TCQ(Vector vLOA_TCQ) {
		this.vLOA_TCQ = vLOA_TCQ;
	}

	public String getCont_type_flag() {
		return cont_type_flag;
	}

	public void setCont_type_flag(String cont_type_flag) {
		this.cont_type_flag = cont_type_flag;
	}
	/////////////SB20200326//////////////////
	Vector VMaxNomDt = new Vector();
	public Vector getVMaxNomDt() {return VMaxNomDt;}
	Vector VPriceEffDt = new Vector();
	public Vector getVPriceEffDt() {return VPriceEffDt;}
	Vector VNextNewPriceDt = new Vector();
	public Vector getVNextNewPriceDt() {return VNextNewPriceDt;}
	
	public String RATE_DATE ="";
	public String getRATE_DATE() {return RATE_DATE;}
	Vector VNewPrice = new Vector();
	public Vector getVNewPrice() {return VNewPrice;}
	Vector VNewPriceEffDt = new Vector();
	public Vector getVNewPriceEffDt() {return VNewPriceEffDt;}
	Vector VNewPriceAprvFlag = new Vector();
	public Vector getVNewPriceAprvFlag() {return VNewPriceAprvFlag;}
	Vector VPRICE_REQ_FLAG = new Vector();
	public Vector getVPRICE_REQ_FLAG() {return VPRICE_REQ_FLAG;}
	Vector VPRICE_APRV_FLAG = new Vector();
	public Vector getVPRICE_APRV_FLAG() {return VPRICE_APRV_FLAG;}
	Vector VPrice_Change_Dtl = new Vector();
	public Vector getVPrice_Change_Dtl() {return VPrice_Change_Dtl;}
	
	public Map getCAR_COST_PRICE() {return CAR_COST_PRICE;} //SB20200407
	/////////////^20200326///////////////////
}//End Of Class DataBean_Contract_Master ...