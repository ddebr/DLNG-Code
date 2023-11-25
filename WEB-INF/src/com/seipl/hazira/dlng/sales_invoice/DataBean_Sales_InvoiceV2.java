package com.seipl.hazira.dlng.sales_invoice;

//DataBean Introduced By    : Samik Shah ...
//DataBean Introduced On    : 20th May, 2010 ...
//Code Reviewed By			:  
//Code Reviewed Date		:  
//Code Review Status  		:
//Last Modified By			: Samik Shah ...
//Last Modified Date		: 12th May, 2011 ...

import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.*;

import java.util.*;
import java.util.Date;
import java.io.*;
import java.sql.*;
import java.text.*;

import com.seipl.hazira.dlng.master.DataBean_Entity;
import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

public class DataBean_Sales_InvoiceV2
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
	String month = "0";
	String year = "0";
	String from_dt = "";
	String to_dt ="";
	String activity="";
	String operation ="";
	String pay_cd = "";
	
	String billCycle = "0";
	String methodName = "";
	String databeanName = "DataBean_Sales_Invoice";
	
	//Following NumberFormat Object is defined by Samik Shah ... On 20th May, 2010 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("##0.0000"); //For Currency Purpose ... Defined By Samik Shah On 1st June, 2010 ...
	NumberFormat nf3 = new DecimalFormat("###,###,###,##0.00");
	NumberFormat nf4 = new DecimalFormat("##0.000");
	NumberFormat nf5 = new DecimalFormat("##########0");
	
	//Following util Object is defined by Samik Shah ... On 20th May, 2010 ...
	UtilBean util = new UtilBean();
	
	//Following String Variable Used For Setter Methods Are Defined By Samik Shah ... On 21st May, 2010 ...
	String customer_cd = "";
	String fgsa_no = "";
	String fgsa_rev_no = "";
	String sn_no = "";
	String sn_rev_no = "";
	String contract_type = "";
	String customer_plant_seq_no = "";
	String bill_period_start_dt = "";
	String bill_period_end_dt = "";
	String exchg_rate_cd = "";
	String invoice_date = "";
	String particular_date = "";
	String exchg_rate_cal_method = "D";
	String rbi_ref_cd = "1";
	String sbi_tt_selling_cd = "2";
	String sbi_tt_buying_cd = "3";
	String sbi_avg_tt_selling_buying_cd = "6";
	String contactPersonCd = "0";	
	String invFinancialYear = "";
	
	//Following String Variable Used For Setter Methods Are Defined By Achal Pathak ... On 3rd Dec, 2010 ...
	String plant_no ="0";
	String hlplInvoiceNo = "0";
	String fin_year= "";
	String dr_cr_fin_year= "";
	String criteria = "";
	String cr_dr = "";
	//Following String Variable Used For getter Methods Are Defined By Achal Pathak ... On 3rd Dec, 2010 ...	
	String due_dt = "";	
	String dr_cr_exg_rt = "";
	String dr_cr_no = "";
	String dr_cr_no_disp = "";
	String dr_cr_dt = "";
	String diff_qty = "";
	String diff_tax="";
	String item_desc="";
	String diff_amt = "";
	String day_diff = "";
	String exg_rt = "";	
	String gross_amt = "";
	String net_amt = "";
	String qty = "";		
	String inv_dt = "";		
	String inv_no = "";
	String int_type = "";
	String int_rate = "";
	String int_sign ="";
	String int_amt = "";
	String int_rate1 = "";
	String int_rate_cal = "";
	String int_cd = "";
	String sale_price = "";					
	String remark = "";
	String dr_cr_doc_no="";
	String tax_amt_inr ="";
	String dr_cr_due_dt="";
	String dr_cr_tcs_flag="";
	String dr_cr_qty = "";
	
	
	public String getDr_cr_tcs_flag() {
		return dr_cr_tcs_flag;
	}
	public void setDr_cr_tcs_flag(String dr_cr_tcs_flag) {
		this.dr_cr_tcs_flag = dr_cr_tcs_flag;
	}


	String dr_cr_gross_amt = "";
	String dr_cr_net_amt  = "";
	String dr_cr_gross_usd = "";
	String gross_amt_usd = "";
	String tax_struc_cd = "";
	
	///Debit credit note //RG20171121
	String diff_exg_rt1="";
	String applicable_qty1="";
	String dr_cr_net_amt_inr1="";
	String criteria1="";							
	String due_dt1 = "";				
	String exg_rt1 = "";
	String cr_dr2= "";
	String dr_cr_no1 ="";
	String dr_cr_dt1="";
	String dr_cr_exg_rt1="";
	String dr_cr_gross_amt1="";
	String remark1="";
	String dr_cr_net_amt1="";
	String dr_cr_doc_no1="";
	String inv_dt1="";
	String qty1="";
	String sale_price1="";
	String gross_amt1="";
	String net_amt1="";
	String tax_amt_inr1="";
	String dr_cr_gross_usd1 ="";
	String gross_amt_usd1 = "";
	String tax_struc_cd1 = "";
	String diff_sale_rt1="";
	String dr_cr_sales_rate="";
	String accepted_Offspec_Qty1 = "";
	String accepted_FM_Qty1 = "";
	String total_Invoice_Qty1="";
	String diff_qty1="";
	String flag_drcr="";
	
	Vector TAX_ON_TITLE1= new Vector();
	Vector TAX_NAME1= new Vector();
	Vector customer_Invoice_Tax_Code1= new Vector();
	 Vector customer_Invoice_Tax_Rate1= new Vector();
	 Vector customer_Invoice_Tax_Amt1= new Vector();
	 
	 Vector TAX_ON_TITLE2= new Vector();
	 Vector TAX_NAME2= new Vector();
	 Vector customer_Invoice_Tax_Code2= new Vector();
	 Vector customer_Invoice_Tax_Rate2= new Vector();
	 Vector customer_Invoice_Tax_Amt2= new Vector();
	 String net_amt_inr1="";
	 String flag1="";
	 
	 String st_dt="";
	String end_dt="";
	Vector Invoice_period_date1=new Vector();
	String cr_dr1="";
	String drcrgrossamt="";
	Vector Dr_cr_hlpl_inv_no=new Vector();
	String pan_no="";
	String gst_tin_no="";
	String cst_tin_no="";
	Vector invoice_period_date=new Vector();
	String dr_cr_year="";
	Vector REMARK_1_DR_CR=new Vector();
	Vector REMARK_2_DR_CR=new Vector();
	Vector REMARK_3_DR_CR=new Vector();
	Vector flag_dr_cr=new Vector();
	Vector Contact_person_cd_DR_CR=new Vector();
	Vector contact_Person_Name_And_Designation1=new Vector();
	Vector contact_addr_flag1= new Vector(); 
	Vector contact_Customer_Person_Address1=new Vector();
	Vector contact_Customer_Person_City1=new Vector();
	Vector contact_Customer_Person_Pin1=new Vector();
	Vector dr_cr_no2=new Vector();
	
	/////RG20171121
	
	
	//Following Vectors Variables Are Defined By Samik Shah For Invoice Generation Process List On 20th May, 2010 ...
	public Vector invoice_Customer_Cd = new Vector();
	public Vector invoice_Customer_Abbr = new Vector();
	public Vector invoice_Customer_Plant_Seq_No = new Vector();
	public Vector invoice_Customer_Plant_Nm = new Vector();
	public Vector invoice_HLPL_Seq_No = new Vector();
	public Vector invoice_FGSA_No = new Vector();
	public Vector invoice_FGSA_Rev_No = new Vector();
	public Vector invoice_SN_No = new Vector();
	public Vector invoice_SN_Ref_No = new Vector(); //Introduced By Samik Shah On 18th April, 2011 ...
	public Vector invoice_SN_Rev_No = new Vector();
	public Vector invoice_Contract_Type = new Vector();
	public Vector invoice_SN_Start_Dt = new Vector();
	public Vector invoice_SN_End_Dt = new Vector();
	public Vector invoice_Bill_Period_Start_Dt = new Vector();
	public Vector invoice_Bill_Period_End_Dt = new Vector();
	public Vector invoice_Due_Dt = new Vector();
	public Vector invoice_Exchg_Rate_Calculation_Method = new Vector();
	public Vector invoice_Exchg_Rate_Cd = new Vector();
	public Vector invoice_total_qty_mmbtu = new Vector(); //SB20111207
	public Vector invoice_temp_Tcq_mmbtu  = new Vector(); //SB20111207
	
	//Following Vectors & String Variables Are Defined By Samik Shah For Invoice Details Fetching On 21st May, 2010 ...
	public Vector invoice_Customer_Contact_Cd = new Vector();
	public Vector invoice_Customer_Contact_Nm = new Vector();
	public String total_Invoice_Qty = "";
	public String invoice_Sales_Rate = "";
	public String invoice_Previous_Available_Exchg_Date = "";
	public String invoice_Exchg_Rate_RBI_Ref = "";
	public String invoice_Exchg_Rate_TT_Selling = "";
	public String invoice_Exchg_Rate_TT_Buying = "";
	public String invoice_Exchg_Rate_Avg_TT_Buy_Sell = "";
	public String invoice_Previous_Exchg_Rate_RBI_Ref = "";
	public String invoice_Previous_Exchg_Rate_TT_Selling = "";
	public String invoice_Previous_Exchg_Rate_TT_Buying = "";
	public String invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell = "";
	public String invoice_Particular_Day_Exchg_Rate_RBI_Ref = "";
	public String invoice_Particular_Day_Exchg_Rate_TT_Selling = "";
	public String invoice_Particular_Day_Exchg_Rate_TT_Buying = "";
	public String invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell = "";
	
	//Following Vectors Are Defined By Samik Shah For Fetching Exchange Rates For Specified Period On 27th May, 2010 ...
	public Vector invoice_Period_Dates = new Vector();
	public Vector invoice_Period_Exchg_Rate_RBI_Ref = new Vector();
	public Vector invoice_Period_Exchg_Rate_TT_Selling = new Vector();
	public Vector invoice_Period_Exchg_Rate_TT_Buying = new Vector();
	public Vector invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell = new Vector();
	
	//Following Vector & String Variables Has Been Defined By Samik Shah On 2nd June, 2010 ...
	public Vector daily_Invoice_QTY = new Vector();
	public String hlpl_Invoice_Seq_No = "";
	public String customer_Invoice_Seq_No = "";
	public Vector hlpl_Invoice_Seq_No_Arr = new Vector();
	public String tax_Structure_Dtl = "";
	public String seq_no="";
	String tax_struct_cd="0";
	
	//Following Vector Variables Has Been Defined By Samik Shah On 3rd June, 2010 ...
	public Vector hlpl_Invoice_Financial_Year_Arr = new Vector();
	public Vector hlpl_Invoice_Actual_Seq_No = new Vector();
	public Vector customer_Invoice_Actual_Seq_No = new Vector();
	
	Vector new_Invoice_Seq_No = new Vector(); // RS19062017
	
	//Following String Variables Has Been Defined By Samik Shah On 4th June, 2010 ...
	public String contact_Person_Name_And_Designation = "";
	public String contact_Customer_Name = "";
	public String contact_Customer_Person_Address = "";
	public String contact_Customer_Person_City = "";
	public String contact_Customer_Person_Pin = "";	
	public String contact_Customer_Person_State = "";
	public String contact_Customer_GST_NO = "";
	public String contact_Customer_CST_NO = "";
	public String contact_Customer_GST_DT = "";
	public String contact_Customer_CST_DT = "";
	public String contact_Customer_Service_Tax_NO = "";
	public String contact_Customer_Service_Tax_DT = "";
	
	public String contact_Suppl_Name = "";
	public String contact_Suppl_Person_Address = "";
	public String contact_Suppl_Person_City = "";
	public String contact_Suppl_Person_Pin = "";	
	public String contact_Suppl_GST_NO = "";
	public String contact_Suppl_CST_NO = "";
	public String contact_Suppl_GST_DT = "";
	public String contact_Suppl_CST_DT = "";
	public String contact_Suppl_Service_Tax_NO = "";
	public String contact_Suppl_Service_Tax_DT = "";
	
	public String contact_Suppl_PAN_NO = "";		//BK20160211
	public String contact_Suppl_PAN_DT = "";		//BK20160211
	
	String contact_Suppl_GSTIN_NO = "";	//RS01062017
	String contact_Suppl_GSTIN_DT = ""; //RS01062017
	
	public String customer_Invoice_DT = "";
	public String customer_Invoice_DT1 = "";
	public String customer_Invoice_Due_DT = "";
	public String customer_Invoice_Start_DT = "";
	public String customer_Invoice_End_DT = "";
	
	//Following String & Vector Variables Has Been Defined By Samik Shah On 5th June, 2010 ...
	public String customer_Invoice_Tax_Flag = "";
	public String customer_Invoice_SN_Dt = "";
	public String customer_Invoice_FGSA_Dt = "";
	public String customer_Invoice_Gross_Amt_USD = "";
	public String customer_Invoice_Gross_Amt_INR = "";
	public String customer_Invoice_Net_Amt_INR = "";
	public String customer_Invoice_Exchg_Rate = "";
	public String customer_Invoice_Exchg_Rate2 = "";
	public String customer_Invoice_Tax_Amt_INR = ""; //This String Variable has been defined by Samik Shah On 13th May, 2011 ...
	public Vector customer_Invoice_Tax_Code = new Vector();
	public Vector customer_Invoice_Tax_Abbr = new Vector();
	public Vector customer_Invoice_Tax_Name = new Vector();
	public Vector customer_Invoice_Tax_Amt = new Vector();
	public Vector customer_Invoice_Tax_Rate = new Vector();
	double customer_Invoice_Tax_Amt_INR1=0;
	
	//Following String & Vector Variables Has Been Defined By Samik Shah On 7th June, 2010 ...
	public String customer_Invoice_Exchg_Rate_Cd = "6";
	public Vector customer_Invoice_Exchg_Rate_Code = new Vector();
	public Vector customer_Invoice_Exchg_Rate_Name = new Vector();
	public Vector customer_Invoice_Exchg_Rate_Date = new Vector();
	public Vector customer_Invoice_Exchg_Rate_Value = new Vector();
	
	//Following Vectors Has Been Defined By Samik Shah On 8th June, 2010 ...
	public Vector invoice_Period_DCQ = new Vector();
	public Vector invoice_Period_Buyer_Nom_Qty = new Vector();
	public Vector invoice_Period_Seller_Nom_PNQ = new Vector();
	public Vector invoice_Period_Seller_Nom_RE_Qty = new Vector();
	public Vector invoice_Period_Gas_Delivered_PNQ = new Vector();
	public Vector invoice_Period_Gas_Delivered_Re_Qty = new Vector();
	public Vector invoice_Period_Gas_Delivered_Total_Qty = new Vector();
	public Vector invoice_Period_Cumulative_Qty = new Vector();
	public Vector invoice_Period_Cumulative_SN_Qty = new Vector();
	public Vector invoice_Period_Buyer_Shortfall_Qty = new Vector();
	public Vector invoice_Period_Buyer_Off_Spec_Qty = new Vector();
	public Vector invoice_Period_Buyer_Suspension_Qty = new Vector();
	public Vector invoice_Period_Delv_Failure_Qty = new Vector();
	public Vector invoice_Period_Total_Shortfall_Qty = new Vector();
	public Vector invoice_Period_LD_Credit_Payable = new Vector();
	public Vector invoice_Period_FM_Qty = new Vector();
	//Following String Variable Has Been Defined By Achal Pathak On 29th Nov, 2010 ...
	public Vector CUST_CD = new Vector();
	public Vector CUST_NM = new Vector();
	public Vector SALE_PRICE = new Vector();	
	public Vector GROSS_AMT_INR = new Vector();
	public Vector NET_AMT_INR = new Vector();
	public Vector TOTAL_QTY = new Vector();
	public Vector INVOICE_DT = new Vector();
	public Vector PERIOD_START_DT = new Vector();
	public Vector PERIOD_END_DT = new Vector();
	
	public Vector PLANT_SEQ_NO = new Vector();
	public Vector HLPL_INV_SEQ_NO = new Vector();
	public Vector CONTRACT_TYPE = new Vector();
	public Vector FINANCIAL_YEAR = new Vector();
	public Vector INVOICE_NO = new Vector();
	
	public Vector DUE_DT = new Vector();
	public Vector EXCHG_RATE_VALUE = new Vector();
	public Vector SN_NO = new Vector();
	public Vector SN_REV_NO = new Vector();
	public Vector FGSA_NO = new Vector();
	public Vector FGSA_REV_NO = new Vector();
	
	public Vector GROSS_AMT_USD = new Vector();
	public Vector TAX_STRUCT_CD = new Vector();
	String cust_nm="";
	
	public Vector DR_CR_NO= new Vector();
	public Vector DR_CR_FIN_YEAR= new Vector();
	public Vector DR_CR_NO_DISP= new Vector();
	public Vector DR_CR_DOC_NO= new Vector(); //SB20160502
	
	public Vector PAY_CD= new Vector();
	public Vector PAY_DT= new Vector();		
	public Vector PAY_AMT= new Vector();
	public Vector PAY_TYPE= new Vector();
	public Vector ADV_TYPE= new Vector();
	public Vector SETTLE_FLAG= new Vector();
	//Following String Variable Has Been Defined By Samik Shah On 10th June, 2010 ...
	public String invoice_Exchg_Rate_Note = "";
	
	//Following String Variables Has Been Defined By Samik Shah On 17th June, 2010 ...
	public String remark_1 = "";
	public String remark_2 = "";
	
	String pay_dt 	 = "";
	String pay_amt 	 = "";				
	String pay_type  = "";
	String settle_flag = "";
	
	//Following (6) Vectors Has Been Defined By Samik Shah On 20th January, 2011 ...
	public Vector inv_Checked_Flag = new Vector();
	public Vector inv_Checked_By = new Vector();
	public Vector inv_Authorized_Flag = new Vector();
	public Vector inv_Authorized_By = new Vector();
	public Vector inv_Approved_Flag = new Vector();
	public Vector inv_Approved_By = new Vector();
	
	//Following (3) Vectors Has Been Defined By Samik Shah On 22nd January, 2011 ...
	public Vector inv_Gross_Amt_USD = new Vector();
	public Vector inv_Gross_Amt_INR = new Vector();
	public Vector inv_Exchg_Rate_CD = new Vector();
	
	//Following int Variable Has Been Defined By Samik Shah On 25th January, 2011 ...
	public int exchg_rate_ind = -1;
	
	//Following String Variable Has Been Defined By Samik Shah On 25th January, 2011 ...
	public String page_refresh_flag = "N";
	
	//Following String Variable Has Been Defined By Achal Pathak On 5th February, 2011 ...
	public String add_amt = "";
	
	//Following (2) Vector Variables Has Been Defined By Samik Shah On 8th February, 2011 ...
	public Vector daily_Buyer_Allocation_Offspec_Rate = new Vector();
	public Vector daily_Buyer_Allocation_Offspec_Flag = new Vector();
		
	//Following (2) String Variables Has Been Defined By Samik Shah On 8th February, 2011 ...
	public String accepted_Offspec_Qty = "";
	public String accepted_FM_Qty = "";
	
	//Following (6) String Variables Has Been Defined By Samik Shah On 9th February, 2011 ...
	public String total_Offspec_Qty = "";
	public String offspec_Sales_Rate = "";
	public String offspec_Flag = "";
	public String offspec_Amt_USD = "";
	public String total_Gas_Delivered = "";
	public String gas_Delivered_Amt_USD = "0";
	
	//Following Vector Has Been Defined By Samik Shah On 10th February, 2011 ...
	public Vector inv_Offspec_Rate = new Vector();
	
	//Following String Variable Has Been Defined By Achal Pathak On 5th April, 2011 ...
	public String [] plant_no_arr = null;
	public String [] fin_yr_arr = null;
	public String [] fgsa_no_arr = null;
	public String [] sn_no_arr = null;
	public String [] con_type_arr = null;
	public String [] hlpl_no_arr = null;
	public String [] inv_dt_arr = null;
	
	//Following (2) String Variables Has Been Defined By Samik Shah On 15th April, 2011 ...
	public String contact_addr_flag = "";
	public String sn_ref_no = "";
	
	//Following (2) String Variables Has Been Defined By Samik Shah On 16th April, 2011 ...
	public String contact_Customer_GVAT_NO = "";
	public String contact_Customer_GVAT_DT = "";
	
	//Following (3) String Variables Has Been Defined By Samik Shah On 12th May, 2011 ...
	public String boe_no = "";
	public String boe_dt = "";
	public String remark_3 = "";
	public String modifyadjremark="";
	String rec_remark = "";
	
	//Following String Variable Has Been Defined By Samik Shah On 4th July, 2011 ...
	public String liability_exist_flag = "N";
	
	//Following (4) Vectors Have Been Introduced By Samik Shah On 3rd September, 2011 ...
	public Vector vSTAT_CD = new Vector();
	public Vector vSTAT_NM = new Vector();
	public Vector vSTAT_NO = new Vector();
	public Vector vSTAT_EFF_DT = new Vector();
	
	//Following Vector defined for Invoice tracking report
	public Vector inv_net_Amt_INR=new Vector();
	public Vector VCUSTOMER_CD=new Vector();
	public Vector Vcust_abr=new Vector();
	public Vector Vinv_qty=new Vector();
	public Vector Vdue_dt=new Vector();
	public Vector Vinv_dt=new Vector();
	public Vector Vcont_type=new Vector();
	public Vector Vremarks=new Vector();
	public Vector Vsn_no=new Vector();
	public Vector Vsn_no_rev=new Vector();
	public Vector Vcargo_ref_no=new Vector();
	public Vector Vfgsa_no=new Vector();
	public String cont_type="";
	public Vector Vinv_flag=new Vector();
	
	public Vector TCQ=new Vector();
	public Vector AllocatedQty=new Vector();
	public Vector RemainingQty=new Vector();
	public String cutoffDate="";
	public String attachment1flag="";
	public String tcqflag="";
	
	Vector cuttDate=new Vector();
	
	// INR SALES RATE
	//String invoice_Grossamt_INR_On_SalesRate_INR="";
	//String invoice_Sales_Rate_INR="";
	
//	NEW REQUIREMENT OF ADVANCE
	
	String ADJUST_FLAG_MST="N";
	String ADJUST_AMT="0";
	String TARIFF_INR="0";
	String TARIFF_FLAG_MST="N";
	String DISCOUNT_PRICE="0";
	String DISCOUNT_FLAG_MST="N";
	String ADJUST_CUR="";
	String AdjusttotalavailbalMst="0";
	public Vector invoice_adjust_flag = new Vector();  	//RG 20140830
	
	String inv_cur_flag="";//20141610
	String customer_inv_mapping_id="";//20141030 ADED FOR LTCORA AND CN
	String advance_inv_no="";//ADDED FOR ADVANCE INV NO
	String advance_inv_dt="";
	String customer_ADV_INV_NO="";
	String customer_ADV_INV_DT="";
	
	public Vector invoice_Mapping_Id = new Vector(); //NB20141031
	Vector invoice_pre_aprv=new Vector();
	public Vector Invoice_Pending_approval=new Vector();
	Vector invoice_tax_adj=new Vector();
	Vector tax_adj_flag_pdf=new Vector();
	
	public Vector DR_CR_MAPPING_ID=new Vector(); 
	
	public String DR_CR_MAPPING_ID_STR="";
	
	String maxYear="";
	
	String invOldFinancialYear="";
	Vector DelFlag=new Vector();
	
	Vector customer_invoice_pdf_path=new Vector();
	Vector customer_invoice_pdf_lock_flag=new Vector();
	Vector customer_invoice_pdf_path_flag=new Vector();
	Vector pdf_color= new Vector();
	public HttpServletRequest request = null;
	
	String invoice_bench_date="10/03/2015";
	String invoice_bench_date1="04/07/2015";
	Vector invoice_inv_date=new Vector();
	Vector invoice_inv_period_dt=new Vector(); //HS20160620
	Vector Vdrcrflag=new Vector();		//BK20160330
	
	public Vector SALE_PRICE_DR_CR = new Vector();	//RU20161111
	public Vector GROSS_AMT_INR_DR_CR = new Vector();
	public Vector NET_AMT_INR_DR_CR = new Vector();
	public Vector TOTAL_QTY_DR_CR = new Vector();
	public Vector INVOICE_DT_DR_CR = new Vector();
	public Vector PERIOD_START_DT_DR_CR = new Vector();
	public Vector PERIOD_END_DT_DR_CR = new Vector();
	
	public Vector PLANT_SEQ_NO_DR_CR = new Vector();
	public Vector HLPL_INV_SEQ_NO_DR_CR = new Vector();
	public Vector CONTRACT_TYPE_DR_CR = new Vector();
	public Vector FINANCIAL_YEAR_DR_CR = new Vector();
	public Vector INVOICE_NO_DR_CR = new Vector();
	
	public Vector DUE_DT_DR_CR = new Vector();
	public Vector EXCHG_RATE_VALUE_DR_CR = new Vector();
	public Vector SN_NO_DR_CR = new Vector();
	public Vector SN_REV_NO_DR_CR = new Vector();
	public Vector FGSA_NO_DR_CR = new Vector();
	public Vector FGSA_REV_NO_DR_CR = new Vector();
	
	public Vector GROSS_AMT_USD_DR_CR = new Vector();
	public Vector TAX_STRUCT_CD_DR_CR = new Vector();
	public Vector DR_CR_MAPPING_ID_DR_CR = new Vector();
	public Vector TAX_AMT_INR_DR_CR = new Vector();
	public Vector FLAG_DR_CR = new Vector();
	
	public Vector REMARK_DR_CR = new Vector();
	Vector Vinvseqno=new Vector();
	Vector Vctype=new Vector();
	Vector VDrcrCriteria=new Vector();
	String drcrcriteria="";
	
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
	    			EnterData();
	    			CHECKFORCOLUMN_PDF();
	    			AllocationDataFYwise(); //SB20180926
	    			if(callFlag.equalsIgnoreCase("SALES_INVOICE_PREPERATION")) //SB20110919
					{
	    				//AllocationDataFYwise(); //SB20180926
	    				CreateTable_FMS8_ADV_INV_DTL(); //RS20170412
	    				//fetchInvoicePreperationDetails(); //SB20110919
	    				AddColumnFMS7_DR_CR_NOTE(); //SB20160602: For New Column addition
	    				
	    				CreateTable_dlng_inv_pdf_dtl(); //SB20160613
	    				fetchInvoicePreperationDetails(); //NB20141030 MODIFIED FOR LTCORA AND CN
	    				if(tcqflag.equalsIgnoreCase("Calculate"))
	    				{
	    					fetchTotalQuantity();
	    				}
	    				PDFFileNmForInvoice(); //SB20160527
					}
	    			else if(callFlag.equalsIgnoreCase("SALES_INVOICE_DETAIL"))
					{
	    				createColumn();
	    				CHECKFORCOLUMN();
	    				fetchInvoiceDetails(); //BK20160211
	    				fetch_advance_clause_details();
					}
	    			else if(callFlag.equalsIgnoreCase("SALES_INVOICE_REPORT")) 
					{
	    				fetchInvoiceReport(); //BK20160211
	    				fetchDatesForQuantityDtl(); //SB20190104
					}
	    			else if(callFlag.equalsIgnoreCase("SALES_INVOICE_REPORT_PREVIEW")) 
					{
	    				fetchInvoiceReport_PR(); 
	    				
					}
	    			else if(callFlag.equalsIgnoreCase("dr_cr_note"))
					{
	    				//fetchInvAvailableFY(); //SB20160527
	    				fetchDr_CrDetails();
	    				Customer_DTL();
					}
	    			else if(callFlag.equalsIgnoreCase("pay_security"))
					{	    				
	    				Customer_DTL();
	    				fetchPaySecurity();
					}
	    			else if(callFlag.equalsIgnoreCase("Search_Pay_Security"))  //Defined by Achal Pathak on 12/01/2011
					{	    				
	    				Customer_DTL();
	    				fetchPaySecuritySearch();
					}
	    			else if(callFlag.equalsIgnoreCase("DIFF-QTY"))
					{
	    				Customer_NM();
	    				fetchCrDrNote();
					}
	    			else if(callFlag.equalsIgnoreCase("DIFF-EXG"))
					{
	    				Customer_NM();
	    				fetchCrDrNote();
					}
	    			else if(callFlag.equalsIgnoreCase("D-PAY"))
					{
	    				Customer_NM();
	    				fetchCrDrNote();
					}
	    			else if(callFlag.equalsIgnoreCase("C-FORM"))
					{
	    				Customer_NM();
	    				fetchCrDrNoteCForm();
					}
	    			else if(callFlag.equalsIgnoreCase("REV_INV")) //SB20160329
					{
	    				Customer_NM(); //SB20160329
	    				ReverseInvoice_CrNote(); //SB20160329
					}
	    			else if(callFlag.equalsIgnoreCase("payment_tracking"))
					{	    				
	    				Customer_DTL();
	    				fetchPaymentTrackingDetails();
					}
	    			else if(callFlag.equalsIgnoreCase("INVOICE_TRACKING")) 
					{
	    				fetchInvoicetracking(); 
					}
	    			else if(callFlag.equalsIgnoreCase("FetchMaxYear"))
	    			{
	    				fetchMaxYear();
	    			}
	    			else if(callFlag.equalsIgnoreCase("SALES_INVOICE_REPORT_TRACK"))
	    			{
	    				fetchInvoiceRecordsTrack();
	    			}
	    			else if(callFlag.equalsIgnoreCase("InvAvailableFY"))
					{
	    				fetchInvAvailableFY(); //SB20160527
					}
	    			else if(callFlag.equalsIgnoreCase("fetchINV_Details"))
					{
	    				fetchINV_Details(); 
	    				Customer_DTL();
	    				Customer_NM();
					}
	    			else if(callFlag.equalsIgnoreCase("Inv_dates"))
	    			{
	    				Inv_dates();
	    				////System.out.println("after init");
	    			}
	    			else if(callFlag.equalsIgnoreCase("Customer_Dtl_Rpt")) {
	    				Customer_DTL_Rpt();
	    				Fetch_Invoice_data();
	    			}
	    			else if(callFlag.equalsIgnoreCase("FetchAllInvoiceDetails")) {
	    				FetchAllInvoiceDetails();
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
					//System.out.println("rset is not close "+e);
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
					//System.out.println("rset1 is not close "+e);
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
					//System.out.println("rset2 is not close "+e);
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
					//System.out.println("rset3 is not close "+e);
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
					//System.out.println("rset4 is not close "+e);
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
					//System.out.println("rset5 is not close "+e);
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
					//System.out.println("rset6 is not close "+e);
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
					//System.out.println("stmt is not close "+e);
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
					//System.out.println("stmt1 is not close "+e);
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
					//System.out.println("stmt2 is not close "+e);
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
					//System.out.println("stmt3 is not close "+e);
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
					//System.out.println("stmt4 is not close "+e);
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
					//System.out.println("stmt5 is not close "+e);
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
					//System.out.println("stmt6 is not close "+e);
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
					//System.out.println("conn is not close "+e);
				}
			}
	    }
	}
	
	
/////////////////////SB20190103//////////////////////////
	
	Vector VGas_Day=new Vector();
	Vector VNom_Qty=new Vector();
	Vector VGHV=new Vector();
	Vector VSch_Qty=new Vector();
	Vector VTruck_Id=new Vector();
	Vector VTruck_Nm=new Vector();
	Vector VLoaded_Vol=new Vector();
	Vector VLoaded_Ene=new Vector();
	Vector VLoaded_Dt=new Vector();
	Vector VUnloaded_Vol=new Vector();
	Vector VUnloaded_Ene=new Vector();
	Vector VUnloaded_Dt=new Vector();
	public Vector getVGas_Day() {return VGas_Day;}
	public Vector getVGHV() {return VGHV;}
	public Vector getVNom_Qty() {return VNom_Qty;}
	public Vector getVSch_Qty() {return VSch_Qty;}
	public Vector getVTruck_Id() {return VTruck_Id;}
	public Vector getVTruck_Nm() {return VTruck_Nm;}
	public Vector getVLoaded_Vol() {return VLoaded_Vol;}
	public Vector getVLoaded_Ene() {return VLoaded_Ene;}
	public Vector getVLoaded_Dt() {return VLoaded_Dt;}
	public Vector getVUnloaded_Vol() {return VUnloaded_Vol;}
	public Vector getVUnloaded_Ene() {return VUnloaded_Ene;}
	public Vector getVUnloaded_Dt() {return VUnloaded_Dt;}
	Vector vDtCnt=new Vector();
	Map dtMap=new HashMap();
	
public void fetchDatesForQuantityDtl() throws SQLException,IOException{	
try
	{	
	String TCQ1="";
	String AllocatedQty1="";
	String SNStartdate="16/12/2018";
	String SNEnddate="";
	Vector BetweenDates=new Vector();
	Vector BetweenQty=new Vector();
	int cutoff=0;
//	String sch_id=customer_inv_mapping_id+"-"+customer_cd+"-"+customer_plant_seq_no; Hiren_20200305
	String sch_id=customer_inv_mapping_id;
	int datecount=0;
//	System.out.println("line no...963...."+customer_inv_mapping_id+" BILL PERIOD END DATE=="+sch_id);
	if(billCycle.equals("2") || billCycle.equals("1")) //1st-Fortnightly or 2nd-Fortnightly Invoice ...
	{
	if(billCycle.equals("2")) //2nd-Fortnightly Invoice ...
	{
		period_start_dt = "16"+"/"+month+"/"+year;
	}
	else  //1st-Fortnightly Invoice ...
	{
		period_start_dt = "01"+"/"+month+"/"+year;
	}
	
	if(billCycle.equals("1"))  //1st-Fortnightly Invoice ...
	{
		period_end_dt = "15"+"/"+month+"/"+year;
	}
	else  //2nd-Fortnightly Invoice ...
	{
		queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
		////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			period_end_dt = rset.getString(1);
		}
	}
	}
	bill_period_end_dt=period_end_dt;
	
	String q1="select to_date('"+bill_period_end_dt+"','dd/mm/yyyy')-to_date('"+period_start_dt+"','dd/mm/yyyy')+1 from dual";
	
	//system.out.println("query for date count--"+q1);
	rset=stmt.executeQuery(q1);
	
	if(rset.next())
	{
		datecount=rset.getInt(1);
	}
	 
	Vector VGas_Day_temp=new Vector();
	 String q2="SELECT DISTINCT  to_char(TO_date(eff_dt,'DD/MM/YY'),'dd/mm/yy') FROM DLNG_tank_vol_dtl "
	 		+ " WHERE eff_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YY') AND eff_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YY') AND SCH_ID='"+sch_id+"' "
	 		+ " ORDER BY to_char(TO_date(eff_dt,'DD/MM/YY'),'dd/mm/yy')";
//	 System.out.println("1006 ....q2***"+q2);
		rset2=stmt2.executeQuery(q2);
		while(rset2.next())
		{
			VGas_Day_temp.add(rset2.getString(1));
		}
		System.out.println("VGas_Day: "+VGas_Day);
	String tmpdate=period_start_dt;
	boolean flag=false;
	
	for(int i=0;i<VGas_Day_temp.size();i++)
	{int cnt=0;
		BetweenDates.add(tmpdate);
		
		/*String query22="SELECT TANK_ID, LOADED_VOL, LOADED_ENE, TO_CHAR(LOAD_END_TM,'DD/MM/YY'), UNLOADED_VOL, UNLOADED_ENE, TO_CHAR(UNLOAD_DT,'DD/MM/YY'), b.truck_nm, TO_CHAR(a.eff_dt,'DD/MM/YY') "+
		" FROM DLNG_tank_vol_dtl a, DLNG_tank_truck_mst b "+
		" WHERE a.trn_cd=b.truck_id and a.eff_dt=TO_DATE('"+VGas_Day_temp.elementAt(i)+"','DD/MM/YY') AND SCH_ID='"+sch_id+"' and a.FLAG='E' "+
		" ORDER BY SEQ_NO ";*/
		
		String query22="SELECT TANK_ID, LOADED_VOL, LOADED_ENE, TO_CHAR(LOAD_END_TM,'DD/MM/YY'), UNLOADED_VOL, UNLOADED_ENE, TO_CHAR(UNLOAD_DT,'DD/MM/YY'), b.truck_nm, TO_CHAR(a.eff_dt,'DD/MM/YY') "+
				" FROM DLNG_tank_vol_dtl a, DLNG_tank_truck_mst b "+
				" WHERE a.trn_cd=b.truck_id and a.eff_dt=TO_DATE('"+VGas_Day_temp.elementAt(i)+"','DD/MM/YY') AND SCH_ID='"+sch_id+"' "+
				" ORDER BY SEQ_NO ";
		
		System.out.println("TANK-LOADED DATA: "+query22);
		rset2=stmt2.executeQuery(query22);
		while(rset2.next())
		{
			VTruck_Id.add(rset2.getString(1));
			VLoaded_Vol.add(rset2.getString(2));
			VLoaded_Ene.add(rset2.getString(3));
			VLoaded_Dt.add(rset2.getString(4));
			VUnloaded_Vol.add(rset2.getString(5));
			VUnloaded_Ene.add(rset2.getString(6));
			VUnloaded_Dt.add(rset2.getString(7));
			VTruck_Nm.add(rset2.getString(8));
			VGas_Day.add(rset2.getString(9));
					
			String query23="SELECT count(TO_CHAR(a.eff_dt,'DD/MM/YY')) "+
					" FROM DLNG_tank_vol_dtl a, DLNG_tank_truck_mst b "+
					" WHERE a.trn_cd=b.truck_id and a.eff_dt=TO_DATE('"+rset2.getString(9)+"','DD/MM/YY') AND SCH_ID='"+sch_id+"'  "+
					" ORDER BY SEQ_NO ";
//			System.out.println("query23****"+query23);
			rset1=stmt.executeQuery(query23);
			if(rset1.next()){
				dtMap.put(rset2.getString(9), rset1.getInt(1));
			}
			
			String query2="SELECT a.DAY_VOL, b.DAY_VOL, a.GHV FROM DLNG_daily_nom a, DLNG_daily_sch b "+
					" WHERE NOM_DT=TO_DATE('"+VGas_Day_temp.elementAt(i)+"','DD/MM/YY') AND NOM_ID=SCH_ID AND NOM_DT=SCH_DT AND NOM_ID='"+sch_id+"' ";
//					System.out.println("TANK-LOADED DATA: "+query2);
					rset1=stmt1.executeQuery(query2);
					if(rset1.next())
					{
						VNom_Qty.add(rset1.getString(1));
						VSch_Qty.add(rset1.getString(2));
						VGHV.add(rset1.getString(3));
					}
		}
		
		
		
	}
	/*System.out.println("BILL PERIOD START DATE=="+vDtCnt);
	System.out.println("DATE COUNT--"+datecount);
	System.out.println("VTruck_Id: "+VTruck_Id);
	System.out.println("VLoaded_Dt: "+VLoaded_Dt);
	System.out.println("VGas_Day: "+VGas_Day);
	System.out.println("VLoaded_Vol: "+VLoaded_Vol);*/
}
catch(Exception e)
{
e.printStackTrace();
}
}
	//ADDED BY RS04082017 FOR INVOICE REPORT
	
	Vector HLPL_INV_SEQ_NO_RPT = new Vector();
	Vector NEW_INV_SEQ_NO_RPT = new Vector();
	Vector CONTRACT_TYPE_RPT = new Vector();
	Vector CUSTOMER_CD_RPT = new Vector();
	Vector GROSS_AMT_INR_RPT = new Vector();
	Vector NET_AMT_INR_RPT = new Vector();
	Vector FLAG_RPT = new Vector();
	Vector INVOICE_DT_RPT = new Vector();
	Vector CUSTOMER_ABBR_RPT = new Vector();
	Vector INVOICE_TYPE_RPT = new Vector();
	Vector ENT_BY_RPT = new Vector();
	Vector TAX_AMT_RPT = new Vector();
	Vector APPROVED_FLAG_RPT = new Vector();
	Vector CHECKED_FLAG_RPT = new Vector();
	Vector APPROVED_DT_RPT = new Vector();
	Vector CHECKED_DT_RPT = new Vector();
	String MONTH_RPT = "";
	String YEAR_RPT = "";
	
	Vector OTH_HLPL_INV_SEQ_NO_RPT = new Vector();
	Vector OTH_NEW_INV_SEQ_NO_RPT = new Vector();
	Vector OTH_CONTRACT_TYPE_RPT = new Vector();
	Vector OTH_CUSTOMER_CD_RPT = new Vector();
	Vector OTH_GROSS_AMT_INR_RPT = new Vector();
	Vector OTH_NET_AMT_INR_RPT = new Vector();
	Vector OTH_FLAG_RPT = new Vector();
	Vector OTH_INVOICE_DT_RPT = new Vector();
	Vector OTH_CUSTOMER_ABBR_RPT = new Vector();
	Vector OTH_INVOICE_TYPE_RPT = new Vector();
	Vector OTH_ENT_BY_RPT = new Vector();
	Vector OTH_TAX_AMT_RPT = new Vector();
	Vector OTH_GROSS_AMT_CUR_RPT = new Vector();
	Vector OTH_APPROVED_FLAG_RPT = new Vector();
	Vector OTH_CHECKED_FLAG_RPT = new Vector();
	Vector OTH_APPROVED_DT_RPT = new Vector();
	Vector OTH_CHECKED_DT_RPT = new Vector();
	
	Vector ADV_HLPL_INV_SEQ_NO_RPT = new Vector();
	Vector ADV_NEW_INV_SEQ_NO_RPT = new Vector();
	Vector ADV_CONTRACT_TYPE_RPT = new Vector();
	Vector ADV_CUSTOMER_CD_RPT = new Vector();
	Vector ADV_GROSS_AMT_INR_RPT = new Vector();
	Vector ADV_NET_AMT_INR_RPT = new Vector();
	Vector ADV_FLAG_RPT = new Vector();
	Vector ADV_INVOICE_DT_RPT = new Vector();
	Vector ADV_CUSTOMER_ABBR_RPT = new Vector();
	Vector ADV_INVOICE_TYPE_RPT = new Vector();
	Vector ADV_ENT_BY_RPT = new Vector();
	Vector ADV_TAX_AMT_RPT = new Vector();
	Vector ADV_APPROVED_FLAG_RPT = new Vector();
	Vector ADV_CHECKED_FLAG_RPT = new Vector();
	Vector ADV_APPROVED_DT_RPT = new Vector();
	Vector ADV_CHECKED_DT_RPT = new Vector();
	
	Vector SN_HLPL_INV_SEQ_NO_RPT = new Vector();
	Vector SN_NEW_INV_SEQ_NO_RPT = new Vector();
	Vector SN_CONTRACT_TYPE_RPT = new Vector();
	Vector SN_CUSTOMER_CD_RPT = new Vector();
	Vector SN_GROSS_AMT_INR_RPT = new Vector();
	Vector SN_NET_AMT_INR_RPT = new Vector();
	Vector SN_FLAG_RPT = new Vector();
	Vector SN_INVOICE_DT_RPT = new Vector();
	Vector SN_CUSTOMER_ABBR_RPT = new Vector();
	Vector SN_INVOICE_TYPE_RPT = new Vector();
	Vector SN_ENT_BY_RPT = new Vector();
	Vector SN_TAX_AMT_RPT = new Vector();
	Vector SN_APPROVED_FLAG_RPT = new Vector();
	Vector SN_CHECKED_FLAG_RPT = new Vector();
	Vector SN_APPROVED_DT_RPT = new Vector();
	Vector SN_CHECKED_DT_RPT = new Vector();
	
	Vector CR_HLPL_INV_SEQ_NO_RPT = new Vector();
	Vector CR_NEW_INV_SEQ_NO_RPT = new Vector();
	Vector CR_CONTRACT_TYPE_RPT = new Vector();
	Vector CR_CUSTOMER_CD_RPT = new Vector();
	Vector CR_GROSS_AMT_INR_RPT = new Vector();
	Vector CR_NET_AMT_INR_RPT = new Vector();
	Vector CR_FLAG_RPT = new Vector();
	Vector CR_INVOICE_DT_RPT = new Vector();
	Vector CR_CUSTOMER_ABBR_RPT = new Vector();
	Vector CR_INVOICE_TYPE_RPT = new Vector();
	Vector CR_ENT_BY_RPT = new Vector();
	Vector CR_TAX_AMT_RPT = new Vector();
	Vector CR_CRITERIA_RPT = new Vector();
	Vector CR_DOC_NO_RPT = new Vector();
	Vector CR_DT_RPT = new Vector();
	Vector CR_APPROVED_FLAG_RPT = new Vector();
	Vector CR_APPROVED_DT_RPT = new Vector();
	
	public void FetchAllInvoiceDetails() throws Exception
	{
		try {
			String start_dt = "01/"+MONTH_RPT+"/"+YEAR_RPT;
			String end_dt = "";
			String end_dt_query = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+MONTH_RPT+"/"+YEAR_RPT+"','MM/YYYY')),'DD/MM/YYYY') "
					+ "FROM DUAL";
			rset = stmt.executeQuery(end_dt_query);
			if(rset.next()) {
				end_dt = rset.getString(1);
			}
					
			String query = "SELECT HLPL_INV_SEQ_NO,NEW_INV_SEQ_NO,CONTRACT_TYPE,CUSTOMER_CD,"
					+ "GROSS_AMT_INR,NET_AMT_INR,FLAG,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),EMP_CD,TAX_AMT_INR,"
					+ "FINANCIAL_YEAR,NVL(CHECKED_FLAG,'N'),NVL(APPROVED_FLAG,'N'),"
					+ "NVL(TO_CHAR(CHECKED_DT,'DD/MM/YYYY'),''),NVL(TO_CHAR(APPROVED_DT,'DD/MM/YYYY'),'') "
					+ "FROM DLNG_INVOICE_MST WHERE INVOICE_DT "
					+ "BETWEEN TO_DATE('"+start_dt+"','DD/MM/YYYY') AND TO_DATE('"+end_dt+"','DD/MM/YYYY') "
					+ "AND CONTRACT_TYPE IN ('C','Y','Z') AND (SUPPLIER_CD!='2' OR SUPPLIER_CD IS NULL) AND FLAG!='A' "
					+ "ORDER BY NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')";
//			System.out.println("Fetching Data--"+query);
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				HLPL_INV_SEQ_NO_RPT.add(rset.getString(1));
				String temp_seq_no = rset.getString(2)==null?"":rset.getString(2);
				if(temp_seq_no.equals("")) {
					temp_seq_no = rset.getString(1);
					if(temp_seq_no.length()==1) {
						temp_seq_no = "000"+temp_seq_no;
					} else if(temp_seq_no.length()==2) {
						temp_seq_no = "00"+temp_seq_no;
					} else if(temp_seq_no.length()==3) {
						temp_seq_no = "0"+temp_seq_no;
					} else {
						temp_seq_no = ""+temp_seq_no;
					} 
					temp_seq_no = temp_seq_no+"/"+rset.getString(11).substring(2,5)+rset.getString(11).substring(7,9);
				}
				NEW_INV_SEQ_NO_RPT.add(rset.getString(2)==null?temp_seq_no:rset.getString(2));
				CONTRACT_TYPE_RPT.add(rset.getString(3));
				CUSTOMER_CD_RPT.add(rset.getString(4));
				GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(5))));
				NET_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(6))));
				FLAG_RPT.add(rset.getString(7));
				INVOICE_DT_RPT.add(rset.getString(8));
				if(rset.getString(3).equals("C")) {
					if(rset.getString(7).equals("U")) {
						INVOICE_TYPE_RPT.add("SUG");
					} else {
						INVOICE_TYPE_RPT.add("LTCORA");
					}
				} else if(rset.getString(3).equals("Y")) {
					INVOICE_TYPE_RPT.add("COST RECHARGE");
				} else if(rset.getString(3).equals("Z")) {
					INVOICE_TYPE_RPT.add("SCRAP/FIXED ASSEST");
				}
				
				String emp_cd = rset.getString(9)==null?"0":rset.getString(9);
				String query_emp_nm = "SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+emp_cd+"' ";
				rset1 = stmt1.executeQuery(query_emp_nm);
				if(rset1.next()) {
					ENT_BY_RPT.add(rset1.getString(1));
				} else {
					ENT_BY_RPT.add("");
				}
				
				TAX_AMT_RPT.add(rset.getString(10)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(10))));
				CHECKED_FLAG_RPT.add(rset.getString(12)==null?"N":rset.getString(12));
				APPROVED_FLAG_RPT.add(rset.getString(13)==null?"N":rset.getString(13));
				CHECKED_DT_RPT.add(rset.getString(14)==null?"":rset.getString(14));
				APPROVED_DT_RPT.add(rset.getString(15)==null?"":rset.getString(15));
				
				if(rset.getString(3).equals("C")) {
					String query_cust_nm = "SELECT NVL(CUSTOMER_ABBR,'') FROM FMS7_CUSTOMER_MST WHERE "
							+ "CUSTOMER_CD='"+rset.getString(4)+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST "
							+ "WHERE EFF_DT <= TO_DATE('"+rset.getString(8)+"','DD/MM/YYYY') AND CUSTOMER_CD='"+rset.getString(4)+"') ";
					rset1 = stmt1.executeQuery(query_cust_nm);
					if(rset1.next()) {
						CUSTOMER_ABBR_RPT.add(rset1.getString(1));
					} else {
						CUSTOMER_ABBR_RPT.add("");
					}
				} else {
					if(rset.getString(3).equals("Z")) {
						String query_cust_nm = "SELECT NVL(VENDOR_ABBR,'') FROM FMS8_VENDOR_MST WHERE "
								+ "VENDOR_CD='"+rset.getString(4)+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS8_VENDOR_MST "
								+ "WHERE EFF_DT <= TO_DATE('"+rset.getString(8)+"','DD/MM/YYYY') AND VENDOR_CD='"+rset.getString(4)+"') ";
						rset1 = stmt1.executeQuery(query_cust_nm);
						if(rset1.next()) {
							CUSTOMER_ABBR_RPT.add(rset1.getString(1));
						} else {
							CUSTOMER_ABBR_RPT.add("");
						}
					} else {
						String query_cust_nm = "SELECT NVL(SUPPLIER_ABBR,'') FROM FMS7_SUPPLIER_MST WHERE "
								+ "SUPPLIER_CD='"+rset.getString(4)+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_SUPPLIER_MST "
								+ "WHERE EFF_DT <= TO_DATE('"+rset.getString(8)+"','DD/MM/YYYY') AND SUPPLIER_CD='"+rset.getString(4)+"') ";
						rset1 = stmt1.executeQuery(query_cust_nm);
						if(rset1.next()) {
							CUSTOMER_ABBR_RPT.add(rset1.getString(1));
						} else {
							CUSTOMER_ABBR_RPT.add("");
						}
					}
				}
			}
			
			//RECEIPT VOUCHER....
			
			query = "SELECT HLPL_INV_SEQ_NO,NEW_INV_SEQ_NO,CONTRACT_TYPE,CUSTOMER_CD,"
					+ "GROSS_AMT_INR,NET_AMT_INR,FLAG,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),EMP_CD,TAX_AMT_INR,"
					+ "FINANCIAL_YEAR,NVL(CHECKED_FLAG,'N'),NVL(APPROVED_FLAG,'N'),"
					+ "NVL(TO_CHAR(CHECKED_DT,'DD/MM/YYYY'),''),NVL(TO_CHAR(APPROVED_DT,'DD/MM/YYYY'),'') "
					+ "FROM DLNG_INVOICE_MST WHERE INVOICE_DT "
					+ "BETWEEN TO_DATE('"+start_dt+"','DD/MM/YYYY') AND TO_DATE('"+end_dt+"','DD/MM/YYYY') "
					+ "AND CONTRACT_TYPE IN ('C') AND FLAG='A' "
					+ "ORDER BY NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')";
//			System.out.println("Fetching Data--"+query);
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				ADV_HLPL_INV_SEQ_NO_RPT.add(rset.getString(1));
				ADV_NEW_INV_SEQ_NO_RPT.add(rset.getString(2)==null?"":rset.getString(2));
				ADV_CONTRACT_TYPE_RPT.add(rset.getString(3));
				ADV_CUSTOMER_CD_RPT.add(rset.getString(4));
				ADV_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(5))));
				ADV_NET_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(6))));
				ADV_FLAG_RPT.add(rset.getString(7));
				ADV_INVOICE_DT_RPT.add(rset.getString(8));
				ADV_INVOICE_TYPE_RPT.add("RECEIPT VOUCHER");
				
				String emp_cd = rset.getString(9)==null?"0":rset.getString(9);
				String query_emp_nm = "SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+emp_cd+"' ";
				rset1 = stmt1.executeQuery(query_emp_nm);
				if(rset1.next()) {
					ADV_ENT_BY_RPT.add(rset1.getString(1));
				} else {
					ADV_ENT_BY_RPT.add("");
				}
				
				ADV_TAX_AMT_RPT.add(rset.getString(10)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(10))));
				ADV_CHECKED_FLAG_RPT.add(rset.getString(12)==null?"N":rset.getString(12));
				ADV_APPROVED_FLAG_RPT.add(rset.getString(13)==null?"N":rset.getString(13));
				ADV_CHECKED_DT_RPT.add(rset.getString(14)==null?"":rset.getString(14));
				ADV_APPROVED_DT_RPT.add(rset.getString(15)==null?"":rset.getString(15));
				
				String query_cust_nm = "SELECT NVL(CUSTOMER_ABBR,'') FROM FMS7_CUSTOMER_MST WHERE "
						+ "CUSTOMER_CD='"+rset.getString(4)+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST "
						+ "WHERE EFF_DT <= TO_DATE('"+rset.getString(8)+"','DD/MM/YYYY') AND CUSTOMER_CD='"+rset.getString(4)+"') ";
				rset1 = stmt1.executeQuery(query_cust_nm);
				if(rset1.next()) {
					ADV_CUSTOMER_ABBR_RPT.add(rset1.getString(1));
				} else {
					ADV_CUSTOMER_ABBR_RPT.add("");
				}
			}
			
			//SALES INVOICES
			
			query = "SELECT HLPL_INV_SEQ_NO,NEW_INV_SEQ_NO,CONTRACT_TYPE,CUSTOMER_CD,"
					+ "GROSS_AMT_INR,NET_AMT_INR,FLAG,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),EMP_CD,TAX_AMT_INR,"
					+ "FINANCIAL_YEAR,NVL(CHECKED_FLAG,'N'),NVL(APPROVED_FLAG,'N'),"
					+ "NVL(TO_CHAR(CHECKED_DT,'DD/MM/YYYY'),''),NVL(TO_CHAR(APPROVED_DT,'DD/MM/YYYY'),'') "
					+ "FROM DLNG_INVOICE_MST WHERE INVOICE_DT "
					+ "BETWEEN TO_DATE('"+start_dt+"','DD/MM/YYYY') AND TO_DATE('"+end_dt+"','DD/MM/YYYY') "
					+ "AND CONTRACT_TYPE IN ('S','L','K') "
					+ "ORDER BY NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')";
//			System.out.println("Fetching Data--"+query);
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				SN_HLPL_INV_SEQ_NO_RPT.add(rset.getString(1));
				String temp_seq_no = rset.getString(2)==null?"":rset.getString(2);
				if(temp_seq_no.equals("")) {
					temp_seq_no = rset.getString(1);
					if(temp_seq_no.length()==1) {
						temp_seq_no = "000"+temp_seq_no;
					} else if(temp_seq_no.length()==2) {
						temp_seq_no = "00"+temp_seq_no;
					} else if(temp_seq_no.length()==3) {
						temp_seq_no = "0"+temp_seq_no;
					} else {
						temp_seq_no = ""+temp_seq_no;
					} 
					temp_seq_no = temp_seq_no+"/"+rset.getString(11).substring(2,5)+rset.getString(11).substring(7,9);
				}
				SN_NEW_INV_SEQ_NO_RPT.add(rset.getString(2)==null?temp_seq_no:rset.getString(2));
				SN_CONTRACT_TYPE_RPT.add(rset.getString(3));
				SN_CUSTOMER_CD_RPT.add(rset.getString(4));
				SN_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(5))));
				SN_NET_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(6))));
				SN_FLAG_RPT.add(rset.getString(7));
				SN_INVOICE_DT_RPT.add(rset.getString(8));
				if(rset.getString(3).equals("S")) {
					SN_INVOICE_TYPE_RPT.add("SN");
				} else if(rset.getString(3).equals("L")) {
					SN_INVOICE_TYPE_RPT.add("LOA");
				}
				
				String emp_cd = rset.getString(9)==null?"0":rset.getString(9);
				String query_emp_nm = "SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+emp_cd+"' ";
				rset1 = stmt1.executeQuery(query_emp_nm);
				if(rset1.next()) {
					SN_ENT_BY_RPT.add(rset1.getString(1));
				} else {
					SN_ENT_BY_RPT.add("");
				}
				
				SN_TAX_AMT_RPT.add(rset.getString(10)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(10))));
				SN_CHECKED_FLAG_RPT.add(rset.getString(12)==null?"N":rset.getString(12));
				SN_APPROVED_FLAG_RPT.add(rset.getString(13)==null?"N":rset.getString(13));
				SN_CHECKED_DT_RPT.add(rset.getString(14)==null?"":rset.getString(14));
				SN_APPROVED_DT_RPT.add(rset.getString(15)==null?"":rset.getString(15));
				
				String query_cust_nm = "SELECT NVL(CUSTOMER_ABBR,'') FROM FMS7_CUSTOMER_MST WHERE "
						+ "CUSTOMER_CD='"+rset.getString(4)+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST "
						+ "WHERE EFF_DT <= TO_DATE('"+rset.getString(8)+"','DD/MM/YYYY') AND CUSTOMER_CD='"+rset.getString(4)+"') ";
				rset1 = stmt1.executeQuery(query_cust_nm);
				if(rset1.next()) {
					SN_CUSTOMER_ABBR_RPT.add(rset1.getString(1));
				} else {
					SN_CUSTOMER_ABBR_RPT.add("");
				}
			}
			
			// CREDIT-DEBIT DATA
			
			query = " SELECT A.HLPL_INV_SEQ_NO,A.NEW_INV_SEQ_NO,A.CONTRACT_TYPE,A.CUSTOMER_CD,"
					+ "NVL(B.DR_CR_GROSS_AMT_INR,'0'),NVL(B.DR_CR_NET_AMT_INR,'0'),A.FLAG, "
					+ "TO_CHAR(A.INVOICE_DT,'DD/MM/YYYY'),B.EMP_CD,B.TAX_AMT_INR,A.FINANCIAL_YEAR,"
					+ "B.DR_CR_DOC_NO,"
					+ "TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),B.CRITERIA,B.DR_CR_FLAG,"
					+ "TO_CHAR(A.PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(A.PERIOD_END_DT,'DD/MM/YYYY'),"
					+ "A.PLANT_SEQ_NO,A.FGSA_NO,A.SN_NO,A.MAPPING_ID,B.TOTAL_QTY,B.SALE_PRICE,"
					+ "NVL(B.EXCHG_RATE_VALUE,'0'),B.TAX_STRUCT_CD,NVL(B.APRV_BY,'0'),"
					+ "NVL(TO_CHAR(APRV_DT,'DD/MM/YYYY'),'') "
					+ "FROM DLNG_INVOICE_MST A,FMS7_DR_CR_NOTE B WHERE A.INVOICE_DT "
					+ "BETWEEN TO_DATE('"+start_dt+"','DD/MM/YYYY') AND TO_DATE('"+end_dt+"','DD/MM/YYYY') "
					+ "AND A.CONTRACT_TYPE IN ('C','S','L') AND A.FLAG NOT IN ('A','U') AND "
					+ "A.CONTRACT_TYPE = B.CONTRACT_TYPE AND A.HLPL_INV_SEQ_NO = B.HLPL_INV_SEQ_NO "
					+ "AND A.FINANCIAL_YEAR = B.FINANCIAL_YEAR AND A.CUSTOMER_CD=B.CUSTOMER_CD "
					+ "ORDER BY B.DR_CR_DOC_NO,TO_CHAR(A.INVOICE_DT,'DD/MM/YYYY')";
//			System.out.println("Fetching Data--"+query);
			rset = stmt.executeQuery(query);
			while(rset.next()) {}
			
			//OTHER INVOICES
			//FETCHING TYPE X == REVERSE CHARGE
			query = "SELECT HLPL_INV_SEQ_NO,NEW_INV_SEQ_NO,CONTRACT_TYPE,CUSTOMER_CD,"
					+ "GROSS_AMT_INR,NET_AMT_INR,FLAG,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),EMP_CD,TAX_AMT_INR,"
					+ "FINANCIAL_YEAR,NVL(INV_CUR_FLAG,''),GROSS_AMT_USD,NVL(CHECKED_FLAG,'N'),"
					+ "NVL(APPROVED_FLAG,'N'),NVL(TO_CHAR(CHECKED_DT,'DD/MM/YYYY'),''),"
					+ "NVL(TO_CHAR(APPROVED_DT,'DD/MM/YYYY'),'') "
					+ "FROM DLNG_INVOICE_MST WHERE INVOICE_DT "
					+ "BETWEEN TO_DATE('"+start_dt+"','DD/MM/YYYY') AND TO_DATE('"+end_dt+"','DD/MM/YYYY') "
					+ "AND CONTRACT_TYPE IN ('X') AND (SUPPLIER_CD!='2' OR SUPPLIER_CD IS NULL) AND FLAG!='A' "
					+ "ORDER BY NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')";
//			System.out.println("Fetching Data--"+query);
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				OTH_HLPL_INV_SEQ_NO_RPT.add(rset.getString(1));
				OTH_NEW_INV_SEQ_NO_RPT.add(rset.getString(2)==null?"":"RCL"+rset.getString(2));
				OTH_CONTRACT_TYPE_RPT.add(rset.getString(3));
				OTH_CUSTOMER_CD_RPT.add(rset.getString(4));
				
				OTH_NET_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(6))));
				OTH_FLAG_RPT.add(rset.getString(7));
				OTH_INVOICE_DT_RPT.add(rset.getString(8));
				OTH_INVOICE_TYPE_RPT.add("REVERSE CHARGE");
				String temp_flag = rset.getString(12)==null?"":rset.getString(12);
				if(temp_flag.equals("2")) {
					OTH_GROSS_AMT_CUR_RPT.add("USD");
					OTH_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(13))));
				} else if(temp_flag.equals("3")) {
					OTH_GROSS_AMT_CUR_RPT.add("EURO");
					OTH_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(13))));
				} else {
					OTH_GROSS_AMT_CUR_RPT.add("INR");
					OTH_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(5))));
				}
				
				String emp_cd = rset.getString(9)==null?"0":rset.getString(9);
				String query_emp_nm = "SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+emp_cd+"' ";
				rset1 = stmt1.executeQuery(query_emp_nm);
				if(rset1.next()) {
					OTH_ENT_BY_RPT.add(rset1.getString(1));
				} else {
					OTH_ENT_BY_RPT.add("");
				}
				
				OTH_TAX_AMT_RPT.add(rset.getString(10)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(10))));
				OTH_CHECKED_FLAG_RPT.add(rset.getString(14)==null?"N":rset.getString(14));
				OTH_APPROVED_FLAG_RPT.add(rset.getString(15)==null?"N":rset.getString(15));
				OTH_CHECKED_DT_RPT.add(rset.getString(16)==null?"":rset.getString(16));
				OTH_APPROVED_DT_RPT.add(rset.getString(17)==null?"":rset.getString(17));
				
				String query_cust_nm = "SELECT NVL(VENDOR_ABBR,'') FROM FMS8_VENDOR_MST WHERE "
						+ "VENDOR_CD='"+rset.getString(4)+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS8_VENDOR_MST "
						+ "WHERE EFF_DT <= TO_DATE('"+rset.getString(8)+"','DD/MM/YYYY') AND VENDOR_CD='"+rset.getString(4)+"') ";
				rset1 = stmt1.executeQuery(query_cust_nm);
				if(rset1.next()) {
					OTH_CUSTOMER_ABBR_RPT.add(rset1.getString(1));
				} else {
					OTH_CUSTOMER_ABBR_RPT.add("");
				}
			}
			
			//FETCHING TYPE = Z,1,2,X ==> SCRAP,HPPL SHIPPING AGENT, HPPL-HLPL, REVERSE CHARGE
			query = "SELECT HLPL_INV_SEQ_NO,NEW_INV_SEQ_NO,CONTRACT_TYPE,CUSTOMER_CD,"
					+ "GROSS_AMT_INR,NET_AMT_INR,FLAG,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),EMP_CD,TAX_AMT_INR,"
					+ "GROSS_AMT_USD,NVL(CHECKED_FLAG,'N'),NVL(APPROVED_FLAG,'N'),"
					+ "NVL(TO_CHAR(CHECKED_DT,'DD/MM/YYYY'),''),NVL(TO_CHAR(APPROVED_DT,'DD/MM/YYYY'),'') "
					+ "FROM DLNG_INVOICE_MST WHERE INVOICE_DT "
					+ "BETWEEN TO_DATE('"+start_dt+"','DD/MM/YYYY') AND TO_DATE('"+end_dt+"','DD/MM/YYYY') "
					+ "AND CONTRACT_TYPE IN ('Z','1','2','X') AND SUPPLIER_CD='2' AND FLAG!='A' "
					+ "ORDER BY CONTRACT_TYPE,NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')";
//			System.out.println("Fetching Data--"+query);
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				OTH_HLPL_INV_SEQ_NO_RPT.add(rset.getString(1));
				if(rset.getString(3).equals("1") || rset.getString(3).equals("2")) {
					OTH_NEW_INV_SEQ_NO_RPT.add(rset.getString(2)==null?"":"HPPL/"+rset.getString(2)+"/P");
				} else if(rset.getString(3).equals("X")) {
					OTH_NEW_INV_SEQ_NO_RPT.add(rset.getString(2)==null?"":"RCP"+rset.getString(2));
				} else {
					OTH_NEW_INV_SEQ_NO_RPT.add(rset.getString(2)==null?"":rset.getString(2));
				}
				OTH_CONTRACT_TYPE_RPT.add(rset.getString(3));
				OTH_CUSTOMER_CD_RPT.add(rset.getString(4));
				if(rset.getString(3).equals("1")) {
					OTH_GROSS_AMT_CUR_RPT.add("USD");
					OTH_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(11))));
				} else {
					if(rset.getString(3).equals("2")) {
						OTH_GROSS_AMT_CUR_RPT.add("USD");
						OTH_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(11))));
					} else if(rset.getString(3).equals("3")) {
						OTH_GROSS_AMT_CUR_RPT.add("EURO");
						OTH_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(11))));
					} else {
						OTH_GROSS_AMT_CUR_RPT.add("INR");
						OTH_GROSS_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(5))));
					}
				}
				
				OTH_NET_AMT_INR_RPT.add(nf3.format(Double.parseDouble(rset.getString(6))));
				OTH_FLAG_RPT.add(rset.getString(7));
				OTH_INVOICE_DT_RPT.add(rset.getString(8));
				if(rset.getString(3).equals("Z")) {
					OTH_INVOICE_TYPE_RPT.add("SCRAP (HPPL)");
				} else if(rset.getString(3).equals("1")) {
					OTH_INVOICE_TYPE_RPT.add("HPPL SHIPPING AGENT");
				} else if(rset.getString(3).equals("2")) {
					OTH_INVOICE_TYPE_RPT.add("HPPL - HLPL");
				} else if(rset.getString(3).equals("X")) {
					OTH_INVOICE_TYPE_RPT.add("REVERSE CHARGE");
				}
				
				String emp_cd = rset.getString(9)==null?"0":rset.getString(9);
				String query_emp_nm = "SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+emp_cd+"' ";
				rset1 = stmt1.executeQuery(query_emp_nm);
				if(rset1.next()) {
					OTH_ENT_BY_RPT.add(rset1.getString(1));
				} else {
					OTH_ENT_BY_RPT.add("");
				}
				
				OTH_TAX_AMT_RPT.add(rset.getString(10)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(10))));
				OTH_CHECKED_FLAG_RPT.add(rset.getString(12)==null?"N":rset.getString(12));
				OTH_APPROVED_FLAG_RPT.add(rset.getString(13)==null?"N":rset.getString(13));
				OTH_CHECKED_DT_RPT.add(rset.getString(14)==null?"":rset.getString(14));
				OTH_APPROVED_DT_RPT.add(rset.getString(15)==null?"":rset.getString(15));
				
				if(rset.getString(3).equals("2")) {
					String query_cust_nm = "SELECT NVL(SUPPLIER_ABBR,'') FROM FMS7_SUPPLIER_MST WHERE "
							+ "SUPPLIER_CD='"+rset.getString(4)+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_SUPPLIER_MST "
							+ "WHERE EFF_DT <= TO_DATE('"+rset.getString(8)+"','DD/MM/YYYY') AND SUPPLIER_CD='"+rset.getString(4)+"') ";
					rset1 = stmt1.executeQuery(query_cust_nm);
					if(rset1.next()) {
						OTH_CUSTOMER_ABBR_RPT.add(rset1.getString(1));
					} else {
						OTH_CUSTOMER_ABBR_RPT.add("");
					}
				} else {
					String query_cust_nm = "SELECT NVL(VENDOR_ABBR,'') FROM FMS8_VENDOR_MST WHERE "
							+ "VENDOR_CD='"+rset.getString(4)+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS8_VENDOR_MST "
							+ "WHERE EFF_DT <= TO_DATE('"+rset.getString(8)+"','DD/MM/YYYY') AND VENDOR_CD='"+rset.getString(4)+"') ";
					rset1 = stmt1.executeQuery(query_cust_nm);
					if(rset1.next()) {
						OTH_CUSTOMER_ABBR_RPT.add(rset1.getString(1));
					} else {
						OTH_CUSTOMER_ABBR_RPT.add("");
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	} //FUNCTION ENDED...
	
	String cust_nm_rpt = "";
	String year_rpt = "";
	String to_year_rpt = "";
	String month_rpt = "";
	String to_month_rpt = "";
	String invoice_type_rpt = "";
	
	
	public void Fetch_Invoice_data() throws Exception 
	{
		try {
			if(!invoice_type_rpt.equals("0")) {
				if(invoice_type_rpt.equals("AA")) {
					
				} else if(invoice_type_rpt.equals("C")) {
					
				} else if(invoice_type_rpt.equals("A")) {
					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// ADDED BY RS20170412 FOR ADVANCE INOVOICE DETIALS ///////
	
		public void CreateTable_FMS8_ADV_INV_DTL() throws SQLException
		{
			try
			{
				int count=0;
				String query="SELECT COUNT(*) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS8_ADV_INV_DTL' ";					
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				if(count==0)
				{
					query="CREATE TABLE FMS8_ADV_INV_DTL (HLPL_INV_SEQ_NO NUMBER(4,0) NOT NULL, "
							+ "FINANCIAL_YEAR VARCHAR2(9), "
							+ "CONTRACT_TYPE CHAR(1), "
							+ "ADV_INV_NO VARCHAR2(40) NOT NULL, "
							+ "ADV_INV_DT DATE, "
							+ "MAPPING_ID VARCHAR2(40), "
							+ "ENTER_BY NUMBER(6,0), "
							+ "ENTER_DT DATE,"
							+ "FLAG CHAR(1), "
							+ "CONSTRAINT PK_ADV_INV_DTLS PRIMARY KEY "
							+ "(HLPL_INV_SEQ_NO,FINANCIAL_YEAR,CONTRACT_TYPE,ADV_INV_NO) ) ";
					stmt.executeUpdate(query);
					conn.commit();
				}
			}
			catch(Exception e)
			{
				conn.rollback();
				e.printStackTrace();
			}
		}
		///////////////////
	
	Vector rpt_trk_sn_customer_cd=new Vector();
	Vector rpt_trk_sn_customer_nm=new Vector();
	Vector rpt_trk_sn_inv_no=new Vector();
	Vector rpt_trk_sn_inv_dt=new Vector();
	Vector rpt_trk_sn_inv_qty=new Vector();
	Vector rpt_trk_sn_inv_amt=new Vector();
	Vector rpt_trk_sn_due_dt=new Vector();
	Vector rpt_trk_sn_boe_no=new Vector();
	Vector rpt_trk_sn_contrct_no=new Vector();
	
	Vector rpt_trk_re_customer_cd=new Vector();
	Vector rpt_trk_re_customer_nm=new Vector();
	Vector rpt_trk_re_inv_no=new Vector();
	Vector rpt_trk_re_inv_dt=new Vector();
	Vector rpt_trk_re_inv_qty=new Vector();
	Vector rpt_trk_re_inv_amt=new Vector();
	Vector rpt_trk_re_due_dt=new Vector();
	Vector rpt_trk_re_boe_no=new Vector();
	Vector rpt_trk_re_contrct_no=new Vector();
	
	
	Vector rpt_trk_lt_customer_cd=new Vector();
	Vector rpt_trk_lt_customer_nm=new Vector();
	Vector rpt_trk_lt_inv_no=new Vector();
	Vector rpt_trk_lt_inv_dt=new Vector();
	Vector rpt_trk_lt_inv_qty=new Vector();
	Vector rpt_trk_lt_inv_amt=new Vector();
	Vector rpt_trk_lt_due_dt=new Vector();
	Vector rpt_trk_lt_boe_no=new Vector();
	Vector rpt_trk_lt_contrct_no=new Vector();
	
	
	
	public void fetchInvoiceRecordsTrack()
	{
		methodName = "fetchInvoiceRecordsTrack()";
		try 
		{
			/* FUNCTION SAME AS INVOICE PREPARATION DETAIL
			 * IF BACK DATE INVOICE IS MADE, IT WILL FETCH ALL DATA...WITHOUT ORDERING...
			 * 
			 * fetchInvoicePreperationDetails(); 
			
			
			for(int i=0;i<invoice_Customer_Cd.size();i++)
			{
				if(invoice_Contract_Type.elementAt(i).equals("S") || invoice_Contract_Type.elementAt(i).equals("L"))
				{
					if((""+hlpl_Invoice_Seq_No_Arr.elementAt(i)).equalsIgnoreCase(""))
					{
						
					}
					else
					{
						queryString2 = "SELECT A.customer_cd,A.hlpl_inv_seq_no,to_char(A.invoice_dt,'dd-mm-yy'),A.total_qty," +
						   "A.net_amt_inr,to_char(A.due_dt,'dd-mm-yy'),B.customer_name " +
						   ",contract_type,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,MAPPING_ID,financial_year " +
						   "FROM DLNG_INVOICE_MST A,FMS7_CUSTOMER_MST B " +
						   "WHERE " +
						   "contract_type = '"+invoice_Contract_Type.elementAt(i)+"' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
						   "HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' " +
						   	" and FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' " +
						   	"order by contract_type,hlpl_inv_seq_no ";
						//System.out.println("SN LOA-- "+queryString2);
						rset=stmt.executeQuery(queryString2);
						while(rset.next())
						{
						rpt_trk_sn_customer_cd.add(rset.getString(1)==null?"":rset.getString(1));
						rpt_trk_sn_customer_nm.add(rset.getString(7)==null?"":rset.getString(7));
						//rpt_trk_sn_inv_no.add(rset.getString(2)==null?"":rset.getString(2));
						rpt_trk_sn_inv_dt.add(rset.getString(3)==null?"":rset.getString(3));
						rpt_trk_sn_inv_qty.add(rset.getString(4)==null?"0":rset.getString(4));
						rpt_trk_sn_inv_amt.add(rset.getString(5)==null?"0":rset.getString(5));
						rpt_trk_sn_due_dt.add(rset.getString(6)==null?"":rset.getString(6));
						rpt_trk_sn_boe_no.add("");
						
						String cont_type1=rset.getString(8)==null?"S":rset.getString(8);
						String tmp_cust_cd = rset.getString(1)==null?"0":rset.getString(1);
						String tmp_fgsa_no = rset.getString(9)==null?"0":rset.getString(9);
						String tmp_sn_no = rset.getString(11)==null?"0":rset.getString(11);
						String contract_no = "";
						
						String inv_seq_number = "";
						
						if(rset.getString(2)!=null && rset.getString(14)!=null)
						{
							int seq_no = Integer.parseInt(rset.getString(2));
							
							if(seq_no<10)
							{
								inv_seq_number = "000"+seq_no+"/"+rset.getString(14);
							}
							else if(seq_no<100)
							{
								inv_seq_number = "00"+seq_no+"/"+rset.getString(14);
							}
							else if(seq_no<1000)
							{
								inv_seq_number = "0"+seq_no+"/"+rset.getString(14);
							}
							else
							{
								inv_seq_number = ""+seq_no+"/"+rset.getString(14);
							}
						}
						else
						{
							inv_seq_number = "";
						}
						rpt_trk_sn_inv_no.add(inv_seq_number);
						
						if(cont_type1.equalsIgnoreCase("S"))
						{
							String dt="";
							queryString2 = "SELECT SN_REF_NO,to_char(signing_dt,'dd-mm-yy') FROM DLNG_SN_MST WHERE CUSTOMER_CD="+tmp_cust_cd+" AND " +
									   	   "FGSA_NO="+tmp_fgsa_no+" AND SN_NO="+tmp_sn_no+" ";
							//System.out.println(""+queryString2);
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								contract_no = rset2.getString(1)==null?"":rset2.getString(1);
								if(contract_no.trim().equals(""))
								{
									contract_no = tmp_sn_no;
								}
								dt=rset2.getString(2);
							}
							else
							{
								contract_no = tmp_sn_no;
							}
							contract_no="SN "+contract_no+" - "+dt;
						}
						else if(cont_type1.equalsIgnoreCase("L"))
						{
							String dt="";
							queryString2 = "SELECT LOA_REF_NO,to_char(signing_dt,'dd-mm-yy') FROM DLNG_LOA_MST WHERE CUSTOMER_CD="+tmp_cust_cd+" AND " +
									   	   "TENDER_NO="+tmp_fgsa_no+" AND LOA_NO="+tmp_sn_no+" ";
							rset2 = stmt2.executeQuery(queryString2);
							if(rset2.next())
							{
								contract_no = rset2.getString(1)==null?"":rset2.getString(1);
								if(contract_no.trim().equals(""))
								{
									contract_no = tmp_sn_no;
								}
								dt=rset2.getString(2);
							}
							else
							{
								contract_no = tmp_sn_no;
							}
							contract_no="LOA "+contract_no+" - "+dt;
						}
						rpt_trk_sn_contrct_no.add(contract_no);
						
						}
						
					}
				}
				else if(invoice_Contract_Type.elementAt(i).equals("R"))
				{
					if((""+hlpl_Invoice_Seq_No_Arr.elementAt(i)).equalsIgnoreCase(""))
					{
						
					}
					else
					{
					
					queryString2 = "SELECT A.customer_cd,A.hlpl_inv_seq_no,to_char(A.invoice_dt,'dd-mm-yy'),A.total_qty," +
					   "A.net_amt_inr,to_char(A.due_dt,'dd-mm-yy'),B.customer_name " +
					   ",contract_type,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,MAPPING_ID,financial_year " +
					   "FROM DLNG_INVOICE_MST A,FMS7_CUSTOMER_MST B " +
					   "WHERE " +
					   "contract_type = '"+invoice_Contract_Type.elementAt(i)+"' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
					   "HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' " +
					   	" and FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' " +
					   	"order by contract_type,hlpl_inv_seq_no ";
					////System.out.println("Regas-- "+queryString2);
					rset=stmt.executeQuery(queryString2);
					while(rset.next())
					{
						rpt_trk_re_customer_cd.add(rset.getString(1)==null?"":rset.getString(1));
						rpt_trk_re_customer_nm.add(rset.getString(7)==null?"":rset.getString(7));
						//rpt_trk_re_inv_no.add(rset.getString(2)==null?"":rset.getString(2));
						rpt_trk_re_inv_dt.add(rset.getString(3)==null?"":rset.getString(3));
						rpt_trk_re_inv_qty.add(rset.getString(4)==null?"0":rset.getString(4));
						rpt_trk_re_inv_amt.add(rset.getString(5)==null?"0":rset.getString(5));
						rpt_trk_re_due_dt.add(rset.getString(6)==null?"":rset.getString(6));
						
						String cont_type1=rset.getString(8)==null?"R":rset.getString(8);
						
						//String temp_map_id=rset.getString(13)==null?"":rset.getString(13);
						String tmp_fgsa_no = rset.getString(9)==null?"0":rset.getString(9);
						String tmp_sn_no = rset.getString(11)==null?"0":rset.getString(11);
						String tmp_cust_cd = rset.getString(1)==null?"0":rset.getString(1);
						
						String contract_no="";
						contract_no+=tmp_fgsa_no+" - "+tmp_sn_no;
						
						String inv_seq_number = "";
						
						if(rset.getString(2)!=null && rset.getString(14)!=null)
						{
							int seq_no = Integer.parseInt(rset.getString(2));
							
							if(seq_no<10)
							{
								inv_seq_number = "000"+seq_no+"/"+rset.getString(14);
							}
							else if(seq_no<100)
							{
								inv_seq_number = "00"+seq_no+"/"+rset.getString(14);
							}
							else if(seq_no<1000)
							{
								inv_seq_number = "0"+seq_no+"/"+rset.getString(14);
							}
							else
							{
								inv_seq_number = ""+seq_no+"/"+rset.getString(14);
							}
						}
						else
						{
							inv_seq_number = "";
						}
						rpt_trk_re_inv_no.add(inv_seq_number);
						
						rpt_trk_re_contrct_no.add(contract_no);
						
						queryString = "SELECT BOE_NO " +
						"FROM FMS7_RE_GAS_CARGO_DTL WHERE CUSTOMER_CD='"+tmp_cust_cd+"' " +
						"AND RE_GAS_NO="+tmp_fgsa_no+" "+
						"AND CARGO_SEQ_NO="+tmp_sn_no+"";
						////System.out.println(queryString);
						rset2 = stmt2.executeQuery(queryString);
						if(rset2.next())
						{
							rpt_trk_re_boe_no.add(rset2.getString(1)==null?"":rset2.getString(1));
						}
						else
						{
							rpt_trk_re_boe_no.add("");
						}
					}
					}
				}
				else if(invoice_Contract_Type.elementAt(i).equals("T") || invoice_Contract_Type.elementAt(i).equals("C"))
				{
					
					if((""+hlpl_Invoice_Seq_No_Arr.elementAt(i)).equalsIgnoreCase(""))
					{
						
					}
					else
					{
					queryString2 = "SELECT A.customer_cd,A.hlpl_inv_seq_no,to_char(A.invoice_dt,'dd-mm-yy'),A.total_qty," +
					   "A.net_amt_inr,to_char(A.due_dt,'dd-mm-yy'),B.customer_name " +
					   ",contract_type,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,MAPPING_ID,financial_year " +
					   "FROM DLNG_INVOICE_MST A,FMS7_CUSTOMER_MST B " +
					   "WHERE " +
					   "contract_type = '"+invoice_Contract_Type.elementAt(i)+"' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
					   "HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' " +
					   	" and FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' " +
					   	"order by contract_type,hlpl_inv_seq_no ";
					//System.out.println("LTCORA & CN-- "+queryString2);
					rset=stmt.executeQuery(queryString2);
					while(rset.next())
					{
						rpt_trk_lt_customer_cd.add(rset.getString(1)==null?"":rset.getString(1));
						rpt_trk_lt_customer_nm.add(rset.getString(7)==null?"":rset.getString(7));
						//rpt_trk_lt_inv_no.add(rset.getString(2)==null?"":rset.getString(2));
						rpt_trk_lt_inv_dt.add(rset.getString(3)==null?"":rset.getString(3));
						rpt_trk_lt_inv_qty.add(rset.getString(4)==null?"0":rset.getString(4));
						rpt_trk_lt_inv_amt.add(rset.getString(5)==null?"0":rset.getString(5));
						rpt_trk_lt_due_dt.add(rset.getString(6)==null?"":rset.getString(6));
						
						String cont_type1=rset.getString(8)==null?"S":rset.getString(8);
						
						String temp_map_id=rset.getString(13)==null?"":rset.getString(13);
						String tmp_sn_no = rset.getString(11)==null?"0":rset.getString(11);
						
						
						String contract_no="";
						
						if(!temp_map_id.equalsIgnoreCase(""))
						{
							String tmp_map[]=temp_map_id.split("-");
							contract_no+=tmp_map[1]+" - "+tmp_map[3]+" - "+tmp_sn_no;
						}
						rpt_trk_lt_contrct_no.add(contract_no);
						
						String inv_seq_number = "";
						
						if(rset.getString(2)!=null && rset.getString(14)!=null)
						{
							int seq_no = Integer.parseInt(rset.getString(2));
							
							if(seq_no<10)
							{
								inv_seq_number = "000"+seq_no+"/"+rset.getString(14);
							}
							else if(seq_no<100)
							{
								inv_seq_number = "00"+seq_no+"/"+rset.getString(14);
							}
							else if(seq_no<1000)
							{
								inv_seq_number = "0"+seq_no+"/"+rset.getString(14);
							}
							else
							{
								inv_seq_number = ""+seq_no+"/"+rset.getString(14);
							}
						}
						else
						{
							inv_seq_number = "";
						}
						rpt_trk_lt_inv_no.add(inv_seq_number);
						
						queryString = "SELECT BOE_NO " +
						"FROM FMS8_LNG_REGAS_CARGO_DTL WHERE MAPPING_ID='"+temp_map_id+"' " +
						"AND FLAG='C' AND CARGO_SEQ_NO="+tmp_sn_no+"";
						////System.out.println(queryString);
						rset2 = stmt2.executeQuery(queryString);
						if(rset2.next())
						{
							rpt_trk_lt_boe_no.add(rset2.getString(1)==null?"":rset2.getString(1));
						}
						else
						{
							rpt_trk_lt_boe_no.add("");
						}
					}
					}
				}
			}*/
			
			
			String period_start_dt = "";
			String period_end_dt = "";
			String period_end_dt_FIXED = ""; 
			
			//////System.out.println("billCycle = "+billCycle);
			
			if(billCycle.equals("2") || billCycle.equals("1")) //1st-Fortnightly or 2nd-Fortnightly Invoice ...
			{
				if(billCycle.equals("2")) //2nd-Fortnightly Invoice ...
				{
					period_start_dt = "16"+"/"+month+"/"+year;
				}
				else  //1st-Fortnightly Invoice ...
				{
					period_start_dt = "01"+"/"+month+"/"+year;
				}
				
				if(billCycle.equals("1"))  //1st-Fortnightly Invoice ...
				{
					period_end_dt = "15"+"/"+month+"/"+year;
				}
				else  //2nd-Fortnightly Invoice ...
				{
					queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
					//systemout.println("FMS7-INV-LIST:QRY1001:SELECT:DUAL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						period_end_dt = rset.getString(1);
					}
				}
			}
			else if(billCycle.equals("3")) //1st-Weekly Invoice ...
			{
				period_start_dt = "01"+"/"+month+"/"+year;
				period_end_dt = "07"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("4")) //2nd-Weekly Invoice ...
			{
				period_start_dt = "08"+"/"+month+"/"+year;
				period_end_dt = "14"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("5")) //3rd-Weekly Invoice ...
			{
				period_start_dt = "15"+"/"+month+"/"+year;
				period_end_dt = "21"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("6")) //4th-Weekly Invoice ...
			{
				period_start_dt = "22"+"/"+month+"/"+year;
				period_end_dt = "28"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("9")) //5th-Weekly Invoice ...
			{
				period_start_dt = "29"+"/"+month+"/"+year;
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
				////systemout.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					period_end_dt = rset.getString(1);
				}
			}
			else if(billCycle.equals("7"))  //Monthly Invoice ...
			{
				period_start_dt = "01"+"/"+month+"/"+year;
				
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
				////systemout.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					period_end_dt = rset.getString(1);
				}
			}
			
			queryString2 = "SELECT A.customer_cd,A.hlpl_inv_seq_no,to_char(A.invoice_dt,'dd-mm-yy'),A.total_qty," +
			   "A.net_amt_inr,to_char(A.due_dt,'dd-mm-yy'),B.customer_name " +
			   ",contract_type,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,MAPPING_ID,financial_year " +
			   "FROM DLNG_INVOICE_MST A,FMS7_CUSTOMER_MST B " +
			   "WHERE " +
			   "CONTRACT_TYPE IN ('S','L','K') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
			   "period_start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') " +
			   	" and period_end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY') " +
			   	"order by contract_type,hlpl_inv_seq_no ";
			////System.out.println("SN LOA-- "+queryString2);
			rset=stmt.executeQuery(queryString2);
			while(rset.next())
			{
				rpt_trk_sn_customer_cd.add(rset.getString(1)==null?"":rset.getString(1));
				rpt_trk_sn_customer_nm.add(rset.getString(7)==null?"":rset.getString(7));
				//rpt_trk_sn_inv_no.add(rset.getString(2)==null?"":rset.getString(2));
				rpt_trk_sn_inv_dt.add(rset.getString(3)==null?"":rset.getString(3));
				rpt_trk_sn_inv_qty.add(rset.getString(4)==null?"0":rset.getString(4));
				rpt_trk_sn_inv_amt.add(rset.getString(5)==null?"0":rset.getString(5));
				rpt_trk_sn_due_dt.add(rset.getString(6)==null?"":rset.getString(6));
				rpt_trk_sn_boe_no.add("");
				
				String cont_type1=rset.getString(8)==null?"S":rset.getString(8);
				String tmp_cust_cd = rset.getString(1)==null?"0":rset.getString(1);
				String tmp_fgsa_no = rset.getString(9)==null?"0":rset.getString(9);
				String tmp_sn_no = rset.getString(11)==null?"0":rset.getString(11);
				String contract_no = "";
				
				String inv_seq_number = "";
				
				if(rset.getString(2)!=null && rset.getString(14)!=null)
				{
					int seq_no = Integer.parseInt(rset.getString(2));
					
					if(seq_no<10)
					{
						inv_seq_number = "000"+seq_no+"/"+rset.getString(14);
					}
					else if(seq_no<100)
					{
						inv_seq_number = "00"+seq_no+"/"+rset.getString(14);
					}
					else if(seq_no<1000)
					{
						inv_seq_number = "0"+seq_no+"/"+rset.getString(14);
					}
					else
					{
						inv_seq_number = ""+seq_no+"/"+rset.getString(14);
					}
				}
				else
				{
					inv_seq_number = "";
				}
				rpt_trk_sn_inv_no.add(inv_seq_number);
				
				if(cont_type1.equalsIgnoreCase("S"))
				{
					String dt="";
					queryString2 = "SELECT SN_REF_NO,to_char(signing_dt,'dd-mm-yy') FROM DLNG_SN_MST WHERE CUSTOMER_CD="+tmp_cust_cd+" AND " +
							   	   "FLSA_NO="+tmp_fgsa_no+" AND SN_NO="+tmp_sn_no+" ";
					////System.out.println(""+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						contract_no = rset2.getString(1)==null?"":rset2.getString(1);
						if(contract_no.trim().equals(""))
						{
							contract_no = tmp_sn_no;
						}
						dt=rset2.getString(2);
					}
					else
					{
						contract_no = tmp_sn_no;
					}
					contract_no="SN "+contract_no+" - "+dt;
				}
				else if(cont_type1.equalsIgnoreCase("L"))
				{
					String dt="";
					queryString2 = "SELECT LOA_REF_NO,to_char(signing_dt,'dd-mm-yy') FROM DLNG_LOA_MST WHERE CUSTOMER_CD="+tmp_cust_cd+" AND " +
							   	   "TENDER_NO="+tmp_fgsa_no+" AND LOA_NO="+tmp_sn_no+" ";
					rset2 = stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						contract_no = rset2.getString(1)==null?"":rset2.getString(1);
						if(contract_no.trim().equals(""))
						{
							contract_no = tmp_sn_no;
						}
						dt=rset2.getString(2);
					}
					else
					{
						contract_no = tmp_sn_no;
					}
					contract_no="LOA "+contract_no+" - "+dt;
				}
				rpt_trk_sn_contrct_no.add(contract_no);
			}

			
			queryString2 = "SELECT A.customer_cd,A.hlpl_inv_seq_no,to_char(A.invoice_dt,'dd-mm-yy'),A.total_qty," +
			   "A.net_amt_inr,to_char(A.due_dt,'dd-mm-yy'),B.customer_name " +
			   ",contract_type,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,MAPPING_ID,financial_year " +
			   "FROM DLNG_INVOICE_MST A,FMS7_CUSTOMER_MST B " +
			   "WHERE " +
			   "contract_type IN ('R') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
			   "period_start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') " +
			   	" and period_end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY') " +
			   	"order by contract_type,hlpl_inv_seq_no ";
			////System.out.println("Regas-- "+queryString2);
			rset=stmt.executeQuery(queryString2);
			while(rset.next())
			{
				rpt_trk_re_customer_cd.add(rset.getString(1)==null?"":rset.getString(1));
				rpt_trk_re_customer_nm.add(rset.getString(7)==null?"":rset.getString(7));
				//rpt_trk_re_inv_no.add(rset.getString(2)==null?"":rset.getString(2));
				rpt_trk_re_inv_dt.add(rset.getString(3)==null?"":rset.getString(3));
				rpt_trk_re_inv_qty.add(rset.getString(4)==null?"0":rset.getString(4));
				rpt_trk_re_inv_amt.add(rset.getString(5)==null?"0":rset.getString(5));
				rpt_trk_re_due_dt.add(rset.getString(6)==null?"":rset.getString(6));
				
				String cont_type1=rset.getString(8)==null?"R":rset.getString(8);
				
				//String temp_map_id=rset.getString(13)==null?"":rset.getString(13);
				String tmp_fgsa_no = rset.getString(9)==null?"0":rset.getString(9);
				String tmp_sn_no = rset.getString(11)==null?"0":rset.getString(11);
				String tmp_cust_cd = rset.getString(1)==null?"0":rset.getString(1);
				
				String contract_no="";
				contract_no+=tmp_fgsa_no+" - "+tmp_sn_no;
				
				String inv_seq_number = "";
				
				if(rset.getString(2)!=null && rset.getString(14)!=null)
				{
					int seq_no = Integer.parseInt(rset.getString(2));
					
					if(seq_no<10)
					{
						inv_seq_number = "000"+seq_no+"/"+rset.getString(14);
					}
					else if(seq_no<100)
					{
						inv_seq_number = "00"+seq_no+"/"+rset.getString(14);
					}
					else if(seq_no<1000)
					{
						inv_seq_number = "0"+seq_no+"/"+rset.getString(14);
					}
					else
					{
						inv_seq_number = ""+seq_no+"/"+rset.getString(14);
					}
				}
				else
				{
					inv_seq_number = "";
				}
				rpt_trk_re_inv_no.add(inv_seq_number);
				
				rpt_trk_re_contrct_no.add(contract_no);
				
				queryString = "SELECT BOE_NO " +
				"FROM FMS7_RE_GAS_CARGO_DTL WHERE CUSTOMER_CD='"+tmp_cust_cd+"' " +
				"AND RE_GAS_NO="+tmp_fgsa_no+" "+
				"AND CARGO_SEQ_NO="+tmp_sn_no+"";
				////System.out.println(queryString);
				rset2 = stmt2.executeQuery(queryString);
				if(rset2.next())
				{
					rpt_trk_re_boe_no.add(rset2.getString(1)==null?"":rset2.getString(1));
				}
				else
				{
					rpt_trk_re_boe_no.add("");
				}
			}
			

			queryString2 = "SELECT A.customer_cd,A.hlpl_inv_seq_no,to_char(A.invoice_dt,'dd-mm-yy'),A.total_qty," +
			   "A.net_amt_inr,to_char(A.due_dt,'dd-mm-yy'),B.customer_name " +
			   ",contract_type,FGSA_NO,FGSA_REV_NO,SN_NO,SN_REV_NO,MAPPING_ID,financial_year " +
			   "FROM DLNG_INVOICE_MST A,FMS7_CUSTOMER_MST B " +
			   "WHERE " +
			   "contract_type IN ('C') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
			   "period_start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') " +
			   	" and period_end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND FLAG='Y' AND (NEW_INV_SEQ_NO NOT LIKE 'D%' OR NEW_INV_SEQ_NO IS NULL) " +
			   	"order by contract_type,hlpl_inv_seq_no ";
			////System.out.println("LTCORA & CN-- "+queryString2);
			rset=stmt.executeQuery(queryString2);
			while(rset.next())
			{
				rpt_trk_lt_customer_cd.add(rset.getString(1)==null?"":rset.getString(1));
				rpt_trk_lt_customer_nm.add(rset.getString(7)==null?"":rset.getString(7));
				//rpt_trk_lt_inv_no.add(rset.getString(2)==null?"":rset.getString(2));
				rpt_trk_lt_inv_dt.add(rset.getString(3)==null?"":rset.getString(3));
				rpt_trk_lt_inv_qty.add(rset.getString(4)==null?"0":rset.getString(4));
				rpt_trk_lt_inv_amt.add(rset.getString(5)==null?"0":rset.getString(5));
				rpt_trk_lt_due_dt.add(rset.getString(6)==null?"":rset.getString(6));
				
				String cont_type1=rset.getString(8)==null?"S":rset.getString(8);
				
				String temp_map_id=rset.getString(13)==null?"":rset.getString(13);
				String tmp_sn_no = rset.getString(11)==null?"0":rset.getString(11);
				
				
				String contract_no="";
				
				if(!temp_map_id.equalsIgnoreCase(""))
				{
					String tmp_map[]=temp_map_id.split("-");
					contract_no+=tmp_map[1]+" - "+tmp_map[3]+" - "+tmp_sn_no;
				}
				rpt_trk_lt_contrct_no.add(contract_no);
				
				String inv_seq_number = "";
				
				if(rset.getString(2)!=null && rset.getString(14)!=null)
				{
					int seq_no = Integer.parseInt(rset.getString(2));
					
					if(seq_no<10)
					{
						inv_seq_number = "000"+seq_no+"/"+rset.getString(14);
					}
					else if(seq_no<100)
					{
						inv_seq_number = "00"+seq_no+"/"+rset.getString(14);
					}
					else if(seq_no<1000)
					{
						inv_seq_number = "0"+seq_no+"/"+rset.getString(14);
					}
					else
					{
						inv_seq_number = ""+seq_no+"/"+rset.getString(14);
					}
				}
				else
				{
					inv_seq_number = "";
				}
				rpt_trk_lt_inv_no.add(inv_seq_number);
				
				queryString = "SELECT BOE_NO " +
				"FROM FMS8_LNG_REGAS_CARGO_DTL WHERE MAPPING_ID='"+temp_map_id+"' " +
				"AND FLAG='C' AND CARGO_SEQ_NO="+tmp_sn_no+"";
				////System.out.println(queryString);
				rset2 = stmt2.executeQuery(queryString);
				if(rset2.next())
				{
					rpt_trk_lt_boe_no.add(rset2.getString(1)==null?"":rset2.getString(1));
				}
				else
				{
					rpt_trk_lt_boe_no.add("");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void fetchMaxYear()
	{
		try
		{
			String query="SELECT MAX(A.DT) FROM "
					+ "( SELECT TO_CHAR(MAX(END_DT),'YYYY') AS DT FROM DLNG_SN_MST UNION ALL "
					+ "SELECT TO_CHAR(MAX(contract_end_dt),'YYYY') FROM fms7_re_gas_cargo_dtl UNION ALL "
					+ "SELECT TO_CHAR(MAX(contract_end_dt),'YYYY') FROM FMS8_LNG_REGAS_CARGO_DTL UNION ALL "
					+ "SELECT TO_CHAR(MAX(END_DT),'YYYY') FROM DLNG_LOA_MST ) A "
					+ "ORDER BY A.DT DESC";
			rset=stmt.executeQuery(query);
			////System.out.println("JAVA fetchMaxYear query: "+query);
			if(rset.next())
			{
				maxYear=rset.getString(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	String TAX_ADV_ADJUST_FLAG_MST="N";
	String TAX_ADV_ADJUST_AMT="0";
	String TAX_ADV_ADJUST_CUR="";
	
	String MODIFY_TAX_ADV_ADJUSTMENT="0";
	String Adjust_total_avail_bal_tax="0";
	String MODIFY_TAX_ADV_CURR="INR";
	String MODIFY_TAX_INV_AMT_INR="0";
	String MODIFY_TAX_ADV_FLAG="N";
	String payment_type="AP";//HS20160722
	
	public String getPayment_type() {
		return payment_type;
	}
	
	Vector TAX_FLAG_SERVICE = new Vector();
	Vector TAX_AMT_SERVICE = new Vector();
	Vector TAX_CUR_SERVICE = new Vector();
	Vector TAX_CODE = new Vector();
	Map tax_avail_balance = new HashMap();
	Map tax_adv_flag = new HashMap();
	Map tax_adv_adjustment = new HashMap();
	Map tax_adv_curr = new HashMap();
	Map tax_inv_amt_inr = new HashMap();
	Vector TAX_ABBR = new Vector();

	public void fetch_advance_clause_details()
	{
		try
		{
			Vector component_cd=new Vector();
			Vector component_nm=new Vector();
			Vector component_abbr=new Vector();
			
			if(date_flag) {
				queryString="select compo_cd,compo_nm,NVL(compo_abr,'') from fms7_compo_mst where flag='Y' and "
						+ "compo_cd not in ('6','7','8','9')";
			} else {
				queryString="select compo_cd,compo_nm,NVL(compo_abr,'') from fms7_compo_mst where flag='Y' and "
						+ "compo_cd not in ('10','11','12','13') ";
			}
			////System.out.println("Fetching..component dtls..."+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				component_cd.add(rset.getString(1));
				component_nm.add(rset.getString(2));
				component_abbr.add(rset.getString(3));
			}
			////System.out.println("Size of Compo_cd: "+component_cd.size());
			////System.out.println("Size of Compo_cd: "+component_cd);
			
			String mapping_id="";
			String transportaion_tariff="0";
			
			if(contract_type.equalsIgnoreCase("S"))
			{
				mapping_id=customer_cd+"-"+fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no+"-"+"S";
				
				/**ADDED FOR TRANSPORTAION TARIFF FOR SN ON 13-05-2015*/
				
				queryString="select TRANSPORTATION_CHARGE from fms7_sn_mst "
						+ "WHERE SN_NO='"+sn_no+"' AND " +
						"SN_REV_NO='"+sn_rev_no+"' AND CUSTOMER_CD='"+customer_cd+"' AND " +
						"FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO = '"+fgsa_rev_no+"'";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					transportaion_tariff=rset.getString(1)==null?"0":rset.getString(1);
				}
				
				for(int ii=0;ii<component_cd.size();ii++)
				{
					queryString = "select price_rate,currency_cd,flag " +
							"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
							"price_cd='"+component_cd.elementAt(ii)+"' AND FLAG='Y' ";
					////System.out.println("Fetching flag of sn..."+queryString);
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next())
					{
						if(component_cd.elementAt(ii).equals("1"))
						{
							ADJUST_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							ADJUST_AMT=rset1.getString(1)==null?"0":rset1.getString(1);
							ADJUST_CUR=rset1.getString(2);
							ADJUST_AMT=nf.format(Double.parseDouble(ADJUST_AMT));
							
						}
						else if(component_cd.elementAt(ii).equals("2"))
						{
							DISCOUNT_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							DISCOUNT_PRICE=rset1.getString(1)==null?"0":rset1.getString(1);
							DISCOUNT_PRICE=nf.format(Double.parseDouble(DISCOUNT_PRICE));
						}
						else if(component_cd.elementAt(ii).equals("3"))
						{
							TARIFF_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							TARIFF_INR=rset1.getString(1)==null?"0":rset1.getString(1);
							TARIFF_INR=nf2.format(Double.parseDouble(TARIFF_INR)+Double.parseDouble(transportaion_tariff));
						}
					}
				}
			}
			else if(contract_type.equalsIgnoreCase("L"))
			{
				mapping_id=customer_cd+"-"+fgsa_no+"-"+0+"-"+sn_no+"-"+sn_rev_no+"-"+"L";
				
				/**ADDED FOR TRANSPORTAION TARIFF FOR SN ON 13-05-2015*/
				
				queryString="select TRANSPORTATION_CHARGE from fms7_loa_mst "
						+ "WHERE LOA_NO='"+sn_no+"' and LOA_REV_NO='"+sn_rev_no+"' "
						+ "AND CUSTOMER_CD='"+customer_cd+"' AND" +
						" Tender_NO='"+fgsa_no+"'";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					transportaion_tariff=rset.getString(1)==null?"0":rset.getString(1);
				}
				
				for(int ii=0;ii<component_cd.size();ii++)
				{
					queryString = "select price_rate,currency_cd,flag " +
							"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
							"price_cd='"+component_cd.elementAt(ii)+"'  AND FLAG='Y' ";
				//	//System.out.println("Fetching flag of sn..."+queryString);
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next())
					{
						if(component_cd.elementAt(ii).equals("1"))
						{
							ADJUST_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							ADJUST_AMT=rset1.getString(1)==null?"0":rset1.getString(1);
							ADJUST_CUR=rset1.getString(2);
							ADJUST_AMT=nf.format(Double.parseDouble(ADJUST_AMT));
						}
						else if(component_cd.elementAt(ii).equals("2"))
						{
							DISCOUNT_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							DISCOUNT_PRICE=rset1.getString(1)==null?"0":rset1.getString(1);
							DISCOUNT_PRICE=nf.format(Double.parseDouble(DISCOUNT_PRICE));
						}
						else if(component_cd.elementAt(ii).equals("3"))
						{
							TARIFF_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							TARIFF_INR=rset1.getString(1)==null?"0":rset1.getString(1);
							TARIFF_INR=nf2.format(Double.parseDouble(TARIFF_INR)+Double.parseDouble(transportaion_tariff));
						}
					}
				}
			}
			else if(contract_type.equalsIgnoreCase("R"))
			{
				mapping_id=customer_cd+"-"+fgsa_no+"-"+0+"-"+sn_no+"-"+0+"-"+"R";
				for(int ii=0;ii<component_cd.size();ii++)
				{
					queryString = "select price_rate,currency_cd,flag " +
							"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
							"price_cd='"+component_cd.elementAt(ii)+"'  AND FLAG='Y' ";
				//	//System.out.println("Fetching flag of sn..."+queryString);
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next())
					{
						if(component_cd.elementAt(ii).equals("1"))
						{
							ADJUST_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							ADJUST_AMT=rset1.getString(1)==null?"0":rset1.getString(1);
							ADJUST_CUR=rset1.getString(2);
							ADJUST_AMT=nf.format(Double.parseDouble(ADJUST_AMT));
						}
						else if(component_cd.elementAt(ii).equals("2"))
						{
							DISCOUNT_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							DISCOUNT_PRICE=rset1.getString(1)==null?"0":rset1.getString(1);
							DISCOUNT_PRICE=nf.format(Double.parseDouble(DISCOUNT_PRICE));
						}
						else if(component_cd.elementAt(ii).equals("3"))
						{
							TARIFF_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							TARIFF_INR=rset1.getString(1)==null?"0":rset1.getString(1);
							TARIFF_INR=nf2.format(Double.parseDouble(TARIFF_INR));
						}
					}
				}
			}
			else if(contract_type.equalsIgnoreCase("T"))
			{
				String tempmap[]=customer_inv_mapping_id.split("-");
				mapping_id=customer_cd+"-"+fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+0+"-"+"T";
				//system.out.println("customer_inv_mapping_id..."+customer_inv_mapping_id);
				for(int ii=0;ii<component_cd.size();ii++)
				{
					queryString = "select price_rate,currency_cd,flag " +
							"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
							"price_cd='"+component_cd.elementAt(ii)+"' and ltcora_no='"+fgsa_no+"' and ltcora_rev_no='"+tempmap[2]+"'  AND FLAG='Y' ";
					////System.out.println("INV:LTCORA-T:SELECT:fms7_cont_price_dtl:"+queryString);
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next())
					{
						if(component_cd.elementAt(ii).equals("1"))
						{
							ADJUST_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							ADJUST_AMT=rset1.getString(1)==null?"0":rset1.getString(1);
							ADJUST_CUR=rset1.getString(2);
							ADJUST_AMT=nf.format(Double.parseDouble(ADJUST_AMT));
						}
						else if(component_cd.elementAt(ii).equals("2"))
						{
							DISCOUNT_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							DISCOUNT_PRICE=rset1.getString(1)==null?"0":rset1.getString(1);
							DISCOUNT_PRICE=nf.format(Double.parseDouble(DISCOUNT_PRICE));
						}
						else if(component_cd.elementAt(ii).equals("3"))
						{
							TARIFF_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							TARIFF_INR=rset1.getString(1)==null?"0":rset1.getString(1);
							TARIFF_INR=nf2.format(Double.parseDouble(TARIFF_INR));
						}
					}
				}
			}
			else if(contract_type.equalsIgnoreCase("C"))
			{
				////System.out.println("---customer_inv_mapping_id--- "+customer_inv_mapping_id);
				String tempmap[]=customer_inv_mapping_id.split("-");
				////System.out.println("---tempmap--- "+tempmap.length);
				mapping_id=customer_cd+"-"+fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+0+"-"+"C";
				for(int ii=0;ii<component_cd.size();ii++)
				{
					queryString = "select price_rate,currency_cd,flag,pay_type " +
							"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
							"price_cd='"+component_cd.elementAt(ii)+"' and ltcora_no='"+tempmap[1]+"' and ltcora_rev_no='"+tempmap[2]+"'  AND FLAG='Y' ";
					
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next())
					{
						if(component_cd.elementAt(ii).equals("1"))
						{
							ADJUST_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							ADJUST_AMT=rset1.getString(1)==null?"0":rset1.getString(1);
							ADJUST_CUR=rset1.getString(2);
							ADJUST_AMT=nf.format(Double.parseDouble(ADJUST_AMT));
						}
						else if(component_cd.elementAt(ii).equals("2"))
						{
							DISCOUNT_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							DISCOUNT_PRICE=rset1.getString(1)==null?"0":rset1.getString(1);
							DISCOUNT_PRICE=nf.format(Double.parseDouble(DISCOUNT_PRICE));
						}
						else if(component_cd.elementAt(ii).equals("3"))
						{
							TARIFF_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							TARIFF_INR=rset1.getString(1)==null?"0":rset1.getString(1);
							TARIFF_INR=nf2.format(Double.parseDouble(TARIFF_INR));
						}
						else if(component_cd.elementAt(ii).equals("6"))
						{
							TAX_ADV_ADJUST_FLAG_MST=rset1.getString(3)==null?"N":rset1.getString(3);
							TAX_ADV_ADJUST_AMT=rset1.getString(1)==null?"0":rset1.getString(1);
							TAX_ADV_ADJUST_CUR=rset1.getString(2);
							TAX_ADV_ADJUST_AMT=nf.format(Double.parseDouble(TAX_ADV_ADJUST_AMT));
						}
						else if(component_cd.elementAt(ii).equals("7")) //SB20160505
						{
							SBC_Flag_Service=rset1.getString(3)==null?"N":rset1.getString(3); //SB20160505
							SBC_AMT_Service=rset1.getString(1)==null?"0":rset1.getString(1);	//SB20160505
							SBC_CUR_Service=rset1.getString(2)==null?"1":rset1.getString(2);	//SB20160505
							SBC_AMT_Service=nf.format(Double.parseDouble(SBC_AMT_Service)); //SB20160505
							
							if(SBC_CUR_Service.equalsIgnoreCase("2"))
								SBC_CUR_Service="USD";
							else if(SBC_CUR_Service.equalsIgnoreCase("1"))
								SBC_CUR_Service="INR";
						}
						else if(component_cd.elementAt(ii).equals("8")) //SB20160505
						{
							KKC_Flag_Service=rset1.getString(3)==null?"N":rset1.getString(3); //SB20160505
							KKC_AMT_Service=rset1.getString(1)==null?"0":rset1.getString(1);	//SB20160505
							KKC_CUR_Service=rset1.getString(2)==null?"1":rset1.getString(2);	//SB20160505
							KKC_AMT_Service=nf.format(Double.parseDouble(KKC_AMT_Service)); //SB20160505
							
							if(KKC_CUR_Service.equalsIgnoreCase("2"))
								KKC_CUR_Service="USD";
							else if(KKC_CUR_Service.equalsIgnoreCase("1"))
								KKC_CUR_Service="INR";
						} else if(component_cd.elementAt(ii).equals("10")) {
							if(tax_Structure_Dtl.contains(""+component_abbr.elementAt(ii))) {
								TAX_CODE.add(component_cd.elementAt(ii));
								TAX_ABBR.add(component_abbr.elementAt(ii));
								TAX_FLAG_SERVICE.add(rset1.getString(3)==null?"N":rset1.getString(3));
								TAX_AMT_SERVICE.add(rset1.getString(1)==null?"N":rset1.getString(1));
								String cur = rset1.getString(2)==null?"N":rset1.getString(2);
								if(rset1.getString(2).equalsIgnoreCase("2")) {
									cur = "USD";
								} else if(rset1.getString(2).equals("1")) {
									cur = "INR";
								}
								TAX_CUR_SERVICE.add(cur);
							}
						} else if(component_cd.elementAt(ii).equals("11")) {
							if(tax_Structure_Dtl.contains(""+component_abbr.elementAt(ii))) {
								TAX_CODE.add(component_cd.elementAt(ii));
								TAX_ABBR.add(component_abbr.elementAt(ii));
								TAX_FLAG_SERVICE.add(rset1.getString(3)==null?"N":rset1.getString(3));
								TAX_AMT_SERVICE.add(rset1.getString(1)==null?"N":rset1.getString(1));
								String cur = rset1.getString(2)==null?"N":rset1.getString(2);
								if(rset1.getString(2).equalsIgnoreCase("2")) {
									cur = "USD";
								} else if(rset1.getString(2).equals("1")) {
									cur = "INR";
								}
								TAX_CUR_SERVICE.add(cur);
							}
						} else if(component_cd.elementAt(ii).equals("12")) {
							if(tax_Structure_Dtl.contains(""+component_abbr.elementAt(ii))) {
								TAX_CODE.add(component_cd.elementAt(ii));
								TAX_ABBR.add(component_abbr.elementAt(ii));
								TAX_FLAG_SERVICE.add(rset1.getString(3)==null?"N":rset1.getString(3));
								TAX_AMT_SERVICE.add(rset1.getString(1)==null?"N":rset1.getString(1));
								String cur = rset1.getString(2)==null?"N":rset1.getString(2);
								if(rset1.getString(2).equalsIgnoreCase("2")) {
									cur = "USD";
								} else if(rset1.getString(2).equals("1")) {
									cur = "INR";
								}
								TAX_CUR_SERVICE.add(cur);
							}
						} else if(component_cd.elementAt(ii).equals("13")) {
							if(tax_Structure_Dtl.contains(""+component_abbr.elementAt(ii))) {
								TAX_CODE.add(component_cd.elementAt(ii));
								TAX_ABBR.add(component_abbr.elementAt(ii));
								TAX_FLAG_SERVICE.add(rset1.getString(3)==null?"N":rset1.getString(3));
								TAX_AMT_SERVICE.add(rset1.getString(1)==null?"N":rset1.getString(1));
								String cur = rset1.getString(2)==null?"N":rset1.getString(2);
								if(rset1.getString(2).equalsIgnoreCase("2")) {
									cur = "USD";
								} else if(rset1.getString(2).equals("1")) {
									cur = "INR";
								}
								TAX_CUR_SERVICE.add(cur);
							}
						}
						payment_type=rset1.getString(4)==null?"AP":rset1.getString(4);
					}
				}
			}
			
			if(ADJUST_CUR.equalsIgnoreCase("2"))
				ADJUST_CUR="USD";
			else if(ADJUST_CUR.equalsIgnoreCase("1"))
				ADJUST_CUR="INR";
			
			if(TAX_ADV_ADJUST_CUR.equalsIgnoreCase("2"))
				TAX_ADV_ADJUST_CUR="USD";
			else if(TAX_ADV_ADJUST_CUR.equalsIgnoreCase("1"))
				TAX_ADV_ADJUST_CUR="INR";
			
			String map_id=customer_cd+":"+fgsa_no+":"+fgsa_rev_no+":"+sn_no+":"+"%";
			
			
			double modifyadjustamtmst1=Double.parseDouble(ADJUST_AMT);
			
			String tempmap[]=customer_inv_mapping_id.split("-");
			String queryString2="select flag, amount,currency, operation  from fms7_inv_compo_dtl where mapping_id LIKE '"+map_id+"' "+ 
					" and inv_seq_no LIKE '"+contract_type+"%' and price_cd='1'  and (flag_temp not like 'T' or flag_temp is null)";
			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
				queryString2+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
			}
			rset2=stmt2.executeQuery(queryString2);
			while(rset2.next())
			{
				String ADJUST_FLAG_MST=rset2.getString(1)==null?"N":rset2.getString(1);
				
			
					if(ADJUST_FLAG_MST.equalsIgnoreCase("Y"))
					{
						String adjust_sign_mst=rset2.getString(4)==null?"1":rset2.getString(4);
						if(adjust_sign_mst.equalsIgnoreCase("1"))
						{
							String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
							modifyadjustamtmst1=modifyadjustamtmst1-Double.parseDouble(adjust_amt_mst);
						}
						else if(adjust_sign_mst.equalsIgnoreCase("2"))
						{
							String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
							modifyadjustamtmst1=modifyadjustamtmst1+Double.parseDouble(adjust_amt_mst);
						}
					}
			}
			AdjusttotalavailbalMst=""+nf.format(Double.parseDouble(nf.format(modifyadjustamtmst1)));
			//AdjusttotalavailbalMst="5000000";
			String Mapping_seq_no=contract_type+":"+invFinancialYear+":"+hlplInvoiceNo+":"+"%";
		//SB20160506	double modifyadjustamtmst2=Double.parseDouble(nf.format(TAX_ADV_ADJUST_AMT));
			
///////////SB20160507: For ST Adjustment : Balance Amount last updated & approved
			double modifyadjustamtmst2=Double.parseDouble((TAX_ADV_ADJUST_AMT));
			////System.out.println("TAX_ADV_ADJUST_AMT: "+TAX_ADV_ADJUST_AMT);
			////System.out.println("modifyadjustamtmst2: "+modifyadjustamtmst2);
			
			//if(activity.equalsIgnoreCase("update"))
			{
				String str_text="select amount,currency,INV_AMT_INR from FMS7_INV_COMPO_DTL where mapping_id LIKE '"+map_id+"' "+ 
					" and inv_seq_no LIKE '"+Mapping_seq_no+"' and price_cd='6'  and (flag_temp not like 'T' or flag_temp is null)";
				if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{
					str_text+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
				}
				////System.out.println("str-TExt.. "+str_text);
				rset=stmt.executeQuery(str_text);
				while(rset.next())
				{
					MODIFY_TAX_ADV_FLAG="Y";
					MODIFY_TAX_ADV_ADJUSTMENT=rset.getString(1)==null?"0":rset.getString(1);
					MODIFY_TAX_ADV_CURR=rset.getString(2)==null?"0":rset.getString(2);
					MODIFY_TAX_INV_AMT_INR=rset.getString(3)==null?"0":rset.getString(3);
				}
				
				String queryString3="select flag, amount,currency, operation  from fms7_inv_compo_dtl where mapping_id LIKE '"+map_id+"' "+ 
				" and inv_seq_no LIKE '"+contract_type+"%' and price_cd='6'  and (flag_temp not like 'T' or flag_temp is null)";
				if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{
					queryString3+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
				}
				rset2=stmt2.executeQuery(queryString3);
				while(rset2.next())
				{
					String ADJUST_FLAG_MST=rset2.getString(1)==null?"N":rset2.getString(1);
					
				
						if(ADJUST_FLAG_MST.equalsIgnoreCase("Y"))
						{
							String adjust_sign_mst=rset2.getString(4)==null?"1":rset2.getString(4);
							if(adjust_sign_mst.equalsIgnoreCase("1"))
							{
								String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
								////System.out.println("Balance Tax Adj Amt:fms7_inv_compo_dtl: "+rset2.getString(2));
								modifyadjustamtmst2=modifyadjustamtmst2-Double.parseDouble(adjust_amt_mst);
							}
							else if(adjust_sign_mst.equalsIgnoreCase("2"))
							{
								String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
								modifyadjustamtmst2=modifyadjustamtmst2+Double.parseDouble(adjust_amt_mst);
							}
						}
				}
			}
			Adjust_total_avail_bal_tax=""+nf.format(Double.parseDouble(nf.format(modifyadjustamtmst2)));
///////////^SB20160507: For ST Adjustment : Balance Amount last updated & approved
			
///////////SB20160507: For SBC Adjustment : Balance Amount last updated & approved
			double SBC_amt_adj=Double.parseDouble((SBC_AMT_Service));
			////System.out.println("SBC_ADV_ADJUST_AMT: "+SBC_AMT_Service);
			////System.out.println("SBC_amt_adj: "+SBC_amt_adj);
			
			String str_text="select amount,currency,INV_AMT_INR from FMS7_INV_COMPO_DTL where mapping_id LIKE '"+map_id+"' "+ 
			" and inv_seq_no LIKE '"+Mapping_seq_no+"' and price_cd='7'  and (flag_temp not like 'T' or flag_temp is null)";
			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
				str_text+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
			}
			////System.out.println("1. INV-BAL-SBC-ADJ: "+str_text);
			rset=stmt.executeQuery(str_text);
			while(rset.next())
			{
				MODIFY_SBC_ADV_FLAG="Y";
				MODIFY_SBC_ADV_ADJUSTMENT=rset.getString(1)==null?"0":rset.getString(1);
				MODIFY_SBC_ADV_CURR=rset.getString(2)==null?"0":rset.getString(2);
				MODIFY_SBC_INV_AMT_INR=rset.getString(3)==null?"0":rset.getString(3);				
			}
			
			String queryString3="select flag, amount,currency, operation  from fms7_inv_compo_dtl where mapping_id LIKE '"+map_id+"' "+ 
			" and inv_seq_no LIKE '"+contract_type+"%' and price_cd='7'  and (flag_temp not like 'T' or flag_temp is null)";
			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
				queryString3+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
			}
			////System.out.println("2. INV-BAL-SBC-ADJ:fms7_inv_compo_dtl: "+queryString3);
			rset2=stmt2.executeQuery(queryString3);
			while(rset2.next())
			{
				String ADJUST_FLAG_MST=rset2.getString(1)==null?"N":rset2.getString(1);
				
			
					if(ADJUST_FLAG_MST.equalsIgnoreCase("Y"))
					{
						String adjust_sign_mst=rset2.getString(4)==null?"1":rset2.getString(4);
						if(adjust_sign_mst.equalsIgnoreCase("1"))
						{
							String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
							////System.out.println("Balance SBC Adj Amt:fms7_inv_compo_dtl: "+rset2.getString(2));
							SBC_amt_adj=SBC_amt_adj-Double.parseDouble(adjust_amt_mst);
						}
						else if(adjust_sign_mst.equalsIgnoreCase("2"))
						{
							String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
							SBC_amt_adj=SBC_amt_adj+Double.parseDouble(adjust_amt_mst);
						}
					}
			}
		////System.out.println("Balance SBC Adj Amt: "+SBC_amt_adj);
		////System.out.println("SBC Adj Amt: "+MODIFY_SBC_ADV_ADJUSTMENT);
		Adjust_total_avail_bal_sbc=""+Double.parseDouble(nf.format(SBC_amt_adj));		
///////////^SB20160507: For SBC Adjustment : Balance Amount last updated & approved
		
//////////KKC20160609 KKC Adjustment////
		double KKC_amt_adj=Double.parseDouble((KKC_AMT_Service));
		////System.out.println("SBC_ADV_ADJUST_AMT: "+KKC_AMT_Service);
		////System.out.println("SBC_amt_adj: "+KKC_amt_adj);
		
		str_text="select amount,currency,INV_AMT_INR from FMS7_INV_COMPO_DTL where mapping_id LIKE '"+map_id+"' "+ 
		" and inv_seq_no LIKE '"+Mapping_seq_no+"' and price_cd='8'  and (flag_temp not like 'T' or flag_temp is null)";
		if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{
			str_text+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
		}
		////System.out.println("1. INV-BAL-SBC-ADJ: "+str_text);
		rset=stmt.executeQuery(str_text);
		while(rset.next())
		{
			MODIFY_KKC_ADV_FLAG="Y";
			MODIFY_KKC_ADV_ADJUSTMENT=rset.getString(1)==null?"0":rset.getString(1);
			MODIFY_KKC_ADV_CURR=rset.getString(2)==null?"0":rset.getString(2);
			MODIFY_KKC_INV_AMT_INR=rset.getString(3)==null?"0":rset.getString(3);				
		}
		
		queryString3="select flag, amount,currency, operation  from fms7_inv_compo_dtl where mapping_id LIKE '"+map_id+"' "+ 
		" and inv_seq_no LIKE '"+contract_type+"%' and price_cd='8'  and (flag_temp not like 'T' or flag_temp is null)";
		if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{
			queryString3+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
		}
		////System.out.println("2. INV-BAL-KKC-ADJ:fms7_inv_compo_dtl: "+queryString3);
		rset2=stmt2.executeQuery(queryString3);
		while(rset2.next())
		{
			String ADJUST_FLAG_MST=rset2.getString(1)==null?"N":rset2.getString(1);
				if(ADJUST_FLAG_MST.equalsIgnoreCase("Y"))
				{
					String adjust_sign_mst=rset2.getString(4)==null?"1":rset2.getString(4);
					if(adjust_sign_mst.equalsIgnoreCase("1"))
					{
						String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
						////System.out.println("Balance KKC Adj Amt:fms7_inv_compo_dtl: "+rset2.getString(2));
						KKC_amt_adj=KKC_amt_adj-Double.parseDouble(adjust_amt_mst);
					}
					else if(adjust_sign_mst.equalsIgnoreCase("2"))
					{
						String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
						KKC_amt_adj=KKC_amt_adj+Double.parseDouble(adjust_amt_mst);
					}
				}
		}
		////System.out.println("Balance KKC Adj Amt: "+KKC_amt_adj);
		////System.out.println("KKC Adj Amt: "+MODIFY_KKC_ADV_ADJUSTMENT);
		Adjust_total_avail_bal_kkc=""+Double.parseDouble(nf.format(KKC_amt_adj));
		/////////////KKC20160609 KKC Adjustment///////////////////////////		
		
		////////////////////////////////////////
		
		for(int i=0;i<TAX_CODE.size();i++) {
			String tax_text="select amount,currency,INV_AMT_INR from FMS7_INV_COMPO_DTL where mapping_id LIKE '"+map_id+"' "+ 
			" and inv_seq_no LIKE '"+Mapping_seq_no+"' and price_cd='"+TAX_CODE.elementAt(i)+"'  and (flag_temp not like 'T' or flag_temp is null)";
			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
				tax_text+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
			}
			rset=stmt.executeQuery(tax_text);
			while(rset.next())
			{
				tax_adv_flag.put(""+TAX_CODE.elementAt(i),"Y");
				tax_adv_adjustment.put(""+TAX_CODE.elementAt(i),rset.getString(1)==null?"0":rset.getString(1));
				tax_adv_curr.put(""+TAX_CODE.elementAt(i),rset.getString(2)==null?"0":rset.getString(2));
				tax_inv_amt_inr.put(""+TAX_CODE.elementAt(i),rset.getString(3)==null?"0":rset.getString(3));
			}
			double amt_adj=Double.parseDouble(""+TAX_AMT_SERVICE.elementAt(i));
			String queryString="select flag, amount,currency, operation  from fms7_inv_compo_dtl where mapping_id LIKE '"+map_id+"' "+ 
								" and inv_seq_no LIKE '"+contract_type+"%' and price_cd='"+TAX_CODE.elementAt(i)+"'  and (flag_temp not like 'T' or flag_temp is null)";
			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
				queryString+="AND LTCORA_NO='"+tempmap[1]+"' AND LTCORA_REV_NO='"+tempmap[2]+"'";
			}
			rset2=stmt2.executeQuery(queryString);
			while(rset2.next())
			{
				String ADJUST_FLAG_MST=rset2.getString(1)==null?"N":rset2.getString(1);
				if(ADJUST_FLAG_MST.equalsIgnoreCase("Y"))
				{
					String adjust_sign_mst=rset2.getString(4)==null?"1":rset2.getString(4);
					if(adjust_sign_mst.equalsIgnoreCase("1"))
					{
						String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
						amt_adj=amt_adj-Double.parseDouble(adjust_amt_mst);
					}
					else if(adjust_sign_mst.equalsIgnoreCase("2"))
					{
						String adjust_amt_mst=rset2.getString(2)==null?"0":rset2.getString(2);
						amt_adj=amt_adj+Double.parseDouble(adjust_amt_mst);
					}
				}
			}
			tax_avail_balance.put(""+TAX_CODE.elementAt(i), ""+nf.format(Double.parseDouble(nf.format(amt_adj))));
		}
		///////////////////////////////////////
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void fetchDatesForTotalQuantity()
	{	
		try
		{	String TCQ1="";
			String AllocatedQty1="";
			String SNStartdate="";
			String SNEnddate="";
			Vector BetweenDates=new Vector();
			Vector BetweenQty=new Vector();
			int cutoff=0;
			
			int datecount=0;
			//system.out.println("BILL PERIOD END DATE=="+bill_period_end_dt);
			
				if(contract_type.equalsIgnoreCase("S"))
				{
					
					String query="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ , RATE, " +
							" nvl(TCQ_REQUEST_FLAG,'N'), nvl(TCQ_REQUEST_SIGN,'+'), nvl(TCQ_REQUEST_QTY,'0') FROM DLNG_SN_MST " +
							" WHERE CUSTOMER_CD='"+customer_cd+"'  AND SN_REV_NO='"+sn_rev_no+"' " +
							" AND SN_NO='"+sn_no+"' " +
							" AND FlSA_REV_NO='"+fgsa_rev_no+"' AND FlSA_NO='"+fgsa_no+"'";
					//system.out.println("FETCHING TCQ FOR SN-"+query);
					rset=stmt.executeQuery(query);
					String tmptcq="";
					String flag="";
					String sign="";
					String qty="";
					if(rset.next())
					{
						tmptcq=rset.getString(3);
						flag=rset.getString(5);
						sign=rset.getString(6);
						qty=rset.getString(7);
						SNStartdate=rset.getString(1);
						SNEnddate=rset.getString(2);
						
					}
					/*if(flag.equalsIgnoreCase("Y"))
					{
						if(sign.equalsIgnoreCase("+"))
						{
							tmptcq=Double.parseDouble(tmptcq)+Double.parseDouble(qty)+"";
						}
						else if(sign.equalsIgnoreCase("-"))
						{
							tmptcq=Double.parseDouble(tmptcq)-Double.parseDouble(qty)+"";
						}
					}*/
					
					TCQ1=tmptcq;
				}
				else if(contract_type.equalsIgnoreCase("L"))
				{
					String query="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ, " +
							" RATE FROM DLNG_LOA_MST "+ 
							" WHERE CUSTOMER_CD='"+customer_cd+"' AND LOA_REV_NO='"+sn_rev_no+"' " +
							" AND LOA_NO='"+sn_no+"' AND TENDER_NO='"+fgsa_no+"' ";
					//system.out.println("FETCHING TCQ FOR LOA-"+query);
					rset=stmt.executeQuery(query);
					while(rset.next())
					{
						TCQ1=rset.getString(3);
						SNStartdate=rset.getString(1);
						SNEnddate=rset.getString(2);
					}
				}
				else if(contract_type.equalsIgnoreCase("R"))
				{
					String query="SELECT TO_CHAR(CONTRACT_START_DT,'DD/MM/YYYY'), TO_CHAR(CONTRACT_END_DT,'DD/MM/YYYY'), "+ 
							" QTY_TO_BE_SUPPLY, RE_GAS_TARIF FROM FMS7_RE_GAS_CARGO_DTL " +
							" WHERE CUSTOMER_CD='"+customer_cd+"' AND " +
							" RE_GAS_NO='"+fgsa_no+"'" +
							" AND CARGO_SEQ_NO='"+sn_no+"'  ";
					//system.out.println("FETCHING TCQ FOR RE-GAS-"+query);
					rset=stmt.executeQuery(query);
					while(rset.next())
					{
						TCQ1=rset.getString(3);
						SNStartdate=rset.getString(1);
						SNEnddate=rset.getString(2);
					}
				}
				String query1="SELECT NVL(SUM(QTY_MMBTU),'0'), NVL(sum(qty_offspec),'0.00') FROM DLNG_DAILY_ALLOCATION_DTL " +
				" WHERE CUSTOMER_CD='"+customer_cd+"' AND " +
				" FGSA_NO= '"+fgsa_no+"'"+ 
				" AND SN_NO='"+sn_no+"' AND CONTRACT_TYPE='"+contract_type+"' " +
				" AND GAS_DT>=TO_DATE('"+SNStartdate+"','DD/MM/YYYY') " +
				" AND GAS_DT<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')";
				//system.out.println("ALOCATED QTY--"+query1);
				rset1=stmt1.executeQuery(query1);
				//String tmpaloctnqty="";
				//String tmpoffspec="";
				
				if(rset1.next())
				{
					String tmpqty=Double.parseDouble(rset1.getString(1))-Double.parseDouble(rset1.getString(2))+"";
					AllocatedQty1=tmpqty;
				}
				
				
				String tmpremaininngqty=Double.parseDouble(TCQ1)-Double.parseDouble(AllocatedQty1)+"";
				RemainingQty.add(tmpremaininngqty);
		
				String q1="select to_date('"+bill_period_end_dt+"','dd/mm/yyyy')-to_date('"+SNStartdate+"','dd/mm/yyyy')+1 from dual";
				
				//system.out.println("query for date count--"+q1);
				rset=stmt.executeQuery(q1);
				
				if(rset.next())
				{
					datecount=rset.getInt(1);
				}
				String tmpdate=SNStartdate;
				boolean flag=false;
				for(int i=0;i<datecount;i++)
				{
					BetweenDates.add(tmpdate);
					String query2="SELECT NVL(SUM(QTY_MMBTU),'0'), NVL(sum(qty_offspec),'0.00') FROM DLNG_DAILY_ALLOCATION_DTL " +
					" WHERE CUSTOMER_CD='"+customer_cd+"' AND " +
					" FGSA_NO= '"+fgsa_no+"'"+ 
					" AND SN_NO='"+sn_no+"' AND CONTRACT_TYPE='"+contract_type+"' " +
					" AND GAS_DT>=TO_DATE('"+SNStartdate+"','DD/MM/YYYY') " +
					" AND GAS_DT<=TO_DATE('"+tmpdate+"','DD/MM/YYYY')";
					////System.out.println("FETCH ALLOCATED DATA--"+query2);
					rset2=stmt2.executeQuery(query2);
					if(rset2.next())
					{
						BetweenQty.add(rset2.getString(1));
					}
					
					
					//String q="select to_date('"+tmpdate+"','dd/mm/yyyy')+1 from dual";
					String q="select to_char(to_date('"+tmpdate+"','dd/mm/yyyy')+1,'dd/mm/yyyy') from dual";
					rset3=stmt3.executeQuery(q);
					if(rset3.next())
					{
						tmpdate=rset3.getString(1);
						
					}
					
					if(Double.parseDouble(rset2.getString(1))>=(Double.parseDouble(TCQ1)-0.99) && flag==false)
					{
						cutoff=i;
						flag=true;
						cutoffDate=tmpdate;
					}
					
				}
				
				
				
				
				
				
			
			//systemout.println("BILL PERIOD START DATE=="+SNStartdate);
			//systemout.println("BILL PERIOD START DATE=="+SNEnddate);
			//systemout.println("DATE COUNT--"+datecount);
			//systemout.println("TCQ VECTOR--"+BetweenDates);
			//systemout.println("ALLOCATED VECTOR--"+BetweenQty);
			//systemout.println("CUTTOFF--"+cutoff);
			//systemout.println("TCQ--"+TCQ1);
			//systemout.println("CUTTOFF DATE--"+cutoffDate);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void fetchTotalQuantity()
	{
		try
		{		
			System.out.println(invoice_Customer_Cd.size()+"--HS-invoice_Contract_Type---"+invoice_Contract_Type);
			for(int i=0; i<invoice_Customer_Cd.size(); i++)
	  		{
				if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("S"))
				{
					
					String query="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ , RATE, " +
							" nvl(TCQ_REQUEST_FLAG,'N'), nvl(TCQ_REQUEST_SIGN,'+'), nvl(TCQ_REQUEST_QTY,'0') FROM DLNG_SN_MST " +
							" WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"'  AND SN_REV_NO='"+invoice_SN_Rev_No.elementAt(i)+"' " +
							" AND SN_NO='"+invoice_SN_No.elementAt(i)+"' " +
							" AND FlSA_REV_NO='"+invoice_FGSA_Rev_No.elementAt(i)+"' AND FlSA_NO='"+invoice_FGSA_No.elementAt(i)+"'";
					////systemout.println("FETCHING TCQ FOR SN-"+query);
					rset=stmt.executeQuery(query);
					String tmptcq="";
					String flag="";
					String sign="";
					String qty="";
					if(rset.next())
					{
						tmptcq=rset.getString(3);
						flag=rset.getString(5);
						sign=rset.getString(6);
						qty=rset.getString(7);						
					}					
					TCQ.add(tmptcq==""?"0.00":tmptcq); //HS20160615
				}
				else if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("L"))
				{
					String query="SELECT TO_CHAR(START_DT,'DD/MM/YYYY'), TO_CHAR(END_DT,'DD/MM/YYYY'), TCQ, " +
							" RATE FROM DLNG_LOA_MST "+ 
							" WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' AND LOA_REV_NO='"+invoice_SN_Rev_No.elementAt(i)+"' " +
							" AND LOA_NO='"+invoice_SN_No.elementAt(i)+"' AND TENDER_NO='"+invoice_FGSA_No.elementAt(i)+"' ";
					////systemout.println("FETCHING TCQ FOR LOA-"+query);
					rset=stmt.executeQuery(query);
					if(rset.next())
					{
						TCQ.add(rset.getString(3));
					}else{
						TCQ.add("0.00"); //HS20160615
					}
				}
				else if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("R"))
				{
					String query="SELECT TO_CHAR(CONTRACT_START_DT,'DD/MM/YYYY'), TO_CHAR(CONTRACT_END_DT,'DD/MM/YYYY'), "+ 
							" QTY_TO_BE_SUPPLY, RE_GAS_TARIF FROM FMS7_RE_GAS_CARGO_DTL " +
							" WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' AND " +
							" RE_GAS_NO='"+invoice_FGSA_No.elementAt(i)+"'" +
							" AND CARGO_SEQ_NO='"+invoice_SN_No.elementAt(i)+"'  ";
					////systemout.println("FETCHING TCQ FOR RE-GAS-"+query);
					rset=stmt.executeQuery(query);
					if(rset.next())
					{
						TCQ.add(rset.getString(3));
					}else{
						TCQ.add("0.00"); //HS20160615
					}
				}
				else if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
				{
					String query="SELECT TO_CHAR(CONTRACT_START_DT,'DD/MM/YYYY'), TO_CHAR(CONTRACT_END_DT,'DD/MM/YYYY'), "+ 
							" QTY_TO_BE_SUPPLY, REGAS_TARIF FROM FMS8_LNG_REGAS_CARGO_DTL " +
							" WHERE MAPPING_ID='"+invoice_Mapping_Id.elementAt(i)+"' " +
							" AND CARGO_SEQ_NO='"+invoice_SN_No.elementAt(i)+"'" +
							" AND FLAG='T' ";
					////systemout.println("FETCHING TCQ FOR LTCORA-"+query);
					rset=stmt.executeQuery(query);
					if(rset.next())
					{
						TCQ.add(rset.getString(3));
					}else{
						TCQ.add("0.00"); //HS20160615
					}
				}
				else if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
					String query="SELECT TO_CHAR(CONTRACT_START_DT,'DD/MM/YYYY'), TO_CHAR(CONTRACT_END_DT,'DD/MM/YYYY'), "+ 
							" QTY_TO_BE_SUPPLY, REGAS_TARIF FROM FMS8_LNG_REGAS_CARGO_DTL " +
							" WHERE MAPPING_ID='"+invoice_Mapping_Id.elementAt(i)+"' " +
							" AND CARGO_SEQ_NO='"+invoice_SN_No.elementAt(i)+"' " +
							" AND FLAG='C' ";
					////systemout.println("FETCHING TCQ FOR LTCORA-"+query);
					rset=stmt.executeQuery(query);
					if(rset.next())
					{
						TCQ.add(rset.getString(3));
					}else{
						TCQ.add("0.00"); //HS20160615
					}
				}
				String query1="SELECT NVL(SUM(QTY_MMBTU),'0'), NVL(sum(qty_offspec),'0.00') FROM DLNG_DAILY_ALLOCATION_DTL " +
				" WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' AND " +
				" FGSA_NO= '"+invoice_FGSA_No.elementAt(i)+"'"+ 
				" AND SN_NO='"+invoice_SN_No.elementAt(i)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
				" AND GAS_DT>=TO_DATE('"+invoice_SN_Start_Dt.elementAt(i)+"','DD/MM/YYYY') " +
				" AND GAS_DT<=TO_DATE('"+invoice_SN_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
			//	//System.out.println("ALOCATED QTY--"+query1);
				rset1=stmt1.executeQuery(query1);		
				if(rset1.next())
				{
					String tmpqty=Double.parseDouble(rset1.getString(1))-Double.parseDouble(rset1.getString(2))+"";
					AllocatedQty.add(tmpqty);
				}
				String tmpremaininngqty=Double.parseDouble(TCQ.elementAt(i).toString())-Double.parseDouble(AllocatedQty.elementAt(i).toString())+"";
				RemainingQty.add(tmpremaininngqty);
				////System.out.println("TOTAL---"+TCQ.elementAt(i)+"--"+AllocatedQty.elementAt(i)+"REMAINING---"+tmpremaininngqty);
					
				if(RemainingQty.elementAt(i).equals("0.0"))
				{
					//systemout.println("INSIDE--");
					String query2="SELECT to_char(max(gas_dt),'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
					" WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' AND " +
					" FGSA_NO= '"+invoice_FGSA_No.elementAt(i)+"'"+ 
					" AND SN_NO='"+invoice_SN_No.elementAt(i)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
					" AND GAS_DT>=TO_DATE('"+invoice_SN_Start_Dt.elementAt(i)+"','DD/MM/YYYY') " +
					" AND GAS_DT<=TO_DATE('"+invoice_SN_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
			//		//System.out.println("MAX-DT-of-ALOCATED QTY--"+query2);
					rset1=stmt1.executeQuery(query2);
					if(rset1.next())
					{
						cuttDate.add(rset1.getString(1));
					}
				}
				else
				{
					cuttDate.add("");
				}
				/////////SB20160603: added to get the Remaining Qty///////////
				String RemainingQtymmbtu = nf.format(Double.parseDouble(TCQ.elementAt(i).toString())-Double.parseDouble(AllocatedQty.elementAt(i).toString())); //SB20160603
				VBalanceQty_mmbtu.add(RemainingQtymmbtu);
				
				String plant_no="";
				String query="SELECT SEQ_NO FROM FMS7_CUSTOMER_PLANT_DTL "+ 
				" WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' " +
				" AND PLANT_NAME='"+invoice_Customer_Plant_Nm.elementAt(i)+"'" +
				" AND FLAG='T' ";
			//	//System.out.println("INV-LIST:FMS7_CUSTOMER_PLANT_DTL: "+query);
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					plant_no=(rset.getString(1));
				}
				
				String Inv_MappingId =invoice_Customer_Cd.elementAt(i)+"-"+invoice_Contract_Type.elementAt(i)+"-"+plant_no; //SB20160606
				
				queryString1 = "SELECT NVL(SUM(QTY_MMBTU),'0'), NVL(sum(qty_offspec),'0.00') " +
						" FROM DLNG_DAILY_ALLOCATION_DTL " +
				   "WHERE customer_cd="+invoice_Customer_Cd.elementAt(i)+" AND " +
				   "fgsa_no="+invoice_FGSA_No.elementAt(i)+" AND " +
				   "sn_no="+invoice_SN_No.elementAt(i)+" AND " +
				   "plant_seq_no="+plant_no+" AND " +
				   "contract_type='"+invoice_Contract_Type.elementAt(i)+"' AND " +
				   "gas_dt>=TO_DATE('"+invoice_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
				   "gas_dt<=TO_DATE('"+invoice_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
				   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";	
			//	//System.out.println("Bill-ALOCATED QTY: "+queryString1);
				rset1=stmt1.executeQuery(queryString1);	
				while(rset1.next())
				{
					String tmp_Bill_qty=Double.parseDouble(rset1.getString(1))-Double.parseDouble(rset1.getString(2))+"";
					VInv_AllocatedQty_mmbtu.add(tmp_Bill_qty);
					VInv_Mapping_Id.add(Inv_MappingId);
				}
				////////////^^^//////////////////////////////////////////////
	  		}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void fetchPaySecuritySearch()
	{
		try
		{	
			if(activity.trim().equalsIgnoreCase("adv") || activity.trim().equalsIgnoreCase("pay"))
			{
				queryString="SELECT PAY_CD,TO_CHAR(PAY_DT,'DD/MM/YYYY'),PAY_AMT,PAY_TYPE,ADV_TYPE FROM FMS7_PAYMENT_SECURITY " +
				"Where CUSTOMER_CD='"+customer_cd+"' AND ADV_TYPE='"+activity+"' " +
				"AND PAY_DT BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
				//systemout.println("FMS7_PAYMENT_SECURITY Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					PAY_CD.add(rset.getString(1)==null?"":rset.getString(1));
					PAY_DT.add(rset.getString(2)==null?"":rset.getString(2));
					PAY_AMT.add(rset.getString(3)==null?"":rset.getString(3));
					PAY_TYPE.add(rset.getString(4)==null?"":rset.getString(4));
					ADV_TYPE.add(rset.getString(5)==null?"":rset.getString(5));
				}
			}
			else if(activity.trim().equalsIgnoreCase("days"))
			{
				queryString="SELECT PAY_CD,TO_CHAR(PAY_DT,'DD/MM/YYYY'),PAY_AMT,PAY_TYPE,ADV_TYPE FROM FMS7_PAYMENT_SECURITY " +
				"Where CUSTOMER_CD='"+customer_cd+"' AND ADV_TYPE='"+activity+"' " +
				"AND PAY_DT BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY') ";
				//systemout.println("FMS7_PAYMENT_SECURITY Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	Vector NEW_INV_SEQ_NO_ = new Vector();
	public void fetchPaymentTrackingDetails()
	{
		try
		{						
			if(!(year.trim().equals("")) && !(year.trim().equals("0")) &&  !(customer_cd.trim().equals("")) && !(customer_cd.trim().equals("0")))
			{
				from_dt = "01/01/"+year.trim();
				to_dt = "31/12/"+year.trim();
				queryString="SELECT SN_NO,FGSA_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO,FINANCIAL_YEAR,NET_AMT_INR,TO_CHAR(DUE_DT,'DD/MM/YYYY'),NEW_INV_SEQ_NO FROM DLNG_INVOICE_MST " +
				"Where FLAG='Y' AND (NEW_INV_SEQ_NO NOT LIKE 'D%' OR NEW_INV_SEQ_NO IS NULL) AND  "
				+ "CUSTOMER_CD='"+customer_cd+"' AND DUE_DT BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY') " +
				"order by DUE_DT,FINANCIAL_YEAR,HLPL_INV_SEQ_NO ";
//				System.out.println("DLNG_INVOICE_MST Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{	
					SN_NO.add(rset.getString(1)==null?"":rset.getString(1));
					FGSA_NO.add(rset.getString(2)==null?"":rset.getString(2));				
					CONTRACT_TYPE.add(rset.getString(3)==null?"":rset.getString(3));
					HLPL_INV_SEQ_NO.add(rset.getString(4)==null?"":rset.getString(4));
					FINANCIAL_YEAR.add(rset.getString(5)==null?"":rset.getString(5));
					if(!rset.getString(5).trim().equals("") && rset.getString(5)!=null){
						INVOICE_NO.add(rset.getString(4)+"/"+rset.getString(5).substring(2,4)+"-"+rset.getString(5).substring(7));
					}
					NET_AMT_INR.add(rset.getString(6)==null?"":rset.getString(6));
					DUE_DT.add(rset.getString(7)==null?"":rset.getString(7));
					NEW_INV_SEQ_NO_.add(rset.getString(8)==null?"":rset.getString(8));
				}
				
				queryString="SELECT PAY_CD,TO_CHAR(PAY_DT,'DD/MM/YYYY'),PAY_AMT,PAY_TYPE,ADV_TYPE,SETTLE_FLAG FROM " +
				"FMS7_PAYMENT_SECURITY Where CUSTOMER_CD='"+customer_cd+"' " +
				"AND PAY_DT BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') AND TO_DATE('"+to_dt+"','DD/MM/YYYY') " +
				"order by PAY_DT ";
				//systemout.println("FMS7_PAYMENT_SECURITY Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					PAY_CD.add(rset.getString(1)==null?"":rset.getString(1));
					PAY_DT.add(rset.getString(2)==null?"":rset.getString(2));				
					PAY_AMT.add(rset.getString(3)==null?"":rset.getString(3));
					PAY_TYPE.add(rset.getString(4)==null?"":rset.getString(4));
					ADV_TYPE.add(rset.getString(5)==null?"":rset.getString(5));
					SETTLE_FLAG.add(rset.getString(6)==null?"":rset.getString(6));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void fetchPaySecurity()
	{
		////System.out.println("customer_cd"+customer_cd);
		try
		{			
			queryString="SELECT TO_CHAR(PAY_DT,'DD/MM/YYYY'),PAY_AMT,PAY_TYPE,SETTLE_FLAG,REMARK FROM FMS7_PAYMENT_SECURITY " +
					"Where CUSTOMER_CD='"+customer_cd+"' AND PAY_CD='"+pay_cd+"'  ";
			////System.out.println("FMS7_DR_CR_NOTE Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			if(rset.next())
			{	
				pay_dt 	     = rset.getString(1)==null?"":rset.getString(1);
				pay_amt 	 = rset.getString(2)==null?"":rset.getString(2);				
				pay_type     = rset.getString(3)==null?"":rset.getString(3);
				settle_flag  = rset.getString(4)==null?"":rset.getString(4);
				remark  	 = rset.getString(5)==null?"":rset.getString(5);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void fetchCrDrNoteCForm()
	{
		////System.out.println("customer_cd"+customer_cd);		
		try
		{		
			String [] plant_no_arr = plant_no.split("~~");
			String [] fin_yr_arr = fin_year.split("~~");
			String [] fgsa_no_arr = fgsa_no.split("~~");
			String [] sn_no_arr = sn_no.split("~~");
			String [] con_type_arr = contract_type.split("~~");
			String [] hlpl_no_arr = hlplInvoiceNo.split("~~");
			String [] inv_dt_arr = inv_dt.split("~~");
			String temp_dr_cr_no="";
			for(int i=0;i<plant_no_arr.length;i++)
			{
				queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
				"FINANCIAL_YEAR,SN_REV_NO,FGSA_REV_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,SALE_PRICE," +
				"GROSS_AMT_INR,NET_AMT_INR,CRITERIA,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,DR_CR_FLAG," +
				"DR_CR_NO,TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),DR_CR_EXG_RATE,DIFF_QTY,DIFF_AMT,DAY_DIFF," +
				"INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,DR_CR_DOC_NO,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
				"DR_CR_GROSS_AMT_USD,GROSS_AMT_USD,TAX_AMT_INR,DR_CR_FIN_YEAR,TAX_STRUCT_CD FROM FMS7_DR_CR_NOTE Where CUSTOMER_CD='"+customer_cd+"' " +
				"AND SN_NO='"+sn_no_arr[i]+"' AND FGSA_NO='"+fgsa_no_arr[i]+"' AND PLANT_SEQ_NO='"+plant_no_arr[i]+"' AND " +
				"CONTRACT_TYPE='"+con_type_arr[i]+"' AND HLPL_INV_SEQ_NO='"+hlpl_no_arr[i]+"' AND " +
				"FINANCIAL_YEAR='"+fin_yr_arr[i]+"' AND CRITERIA='"+criteria+"' AND DR_CR_FLAG='"+cr_dr+"' ";
				////System.out.println("FMS7_DR_CR_NOTE Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{				
					//sn_rev_no     = rset.getString(8)==null?"":rset.getString(8);
					//fgsa_rev_no   = rset.getString(9)==null?"":rset.getString(9);
					//inv_dt        = rset.getString(10)==null?"":rset.getString(10);
					//qty 		  = rset.getString(11)==null?"":rset.getString(11);
					//sale_price 	  = rset.getString(12)==null?"":rset.getString(12);		
					//gross_amt 	  = rset.getString(13)==null?"":rset.getString(13);
					//net_amt 	  = rset.getString(14)==null?"":rset.getString(14);				
					//criteria 	  = rset.getString(15)==null?"":rset.getString(15);							
					//due_dt 		  = rset.getString(16)==null?"":rset.getString(16);				
					//exg_rt 		  = rset.getString(17)==null?"":rset.getString(17);
					//cr_dr 		  = rset.getString(18)==null?"":rset.getString(18);
					temp_dr_cr_no 	  = rset.getString(19)==null?"":rset.getString(19);
					if(!temp_dr_cr_no.trim().equals(""))
					{
						/*if(Integer.parseInt(temp_dr_cr_no)<10)
						{
							dr_cr_no="000"+temp_dr_cr_no+"/"+rset.getString(35).substring(2,4)+"-"+rset.getString(35).substring(7);
						}
						else if(Integer.parseInt(temp_dr_cr_no)<100) 
						{
							dr_cr_no="00"+temp_dr_cr_no+"/"+rset.getString(35).substring(2,4)+"-"+rset.getString(35).substring(7);
						}
						else if(Integer.parseInt(temp_dr_cr_no)<1000) 
						{
							dr_cr_no="0"+temp_dr_cr_no+"/"+rset.getString(35).substring(2,4)+"-"+rset.getString(35).substring(7);
						}
						else
						{
							dr_cr_no=temp_dr_cr_no+"/"+rset.getString(35).substring(2,4)+"-"+rset.getString(35).substring(7);
						}*/
						dr_cr_no=temp_dr_cr_no+"/"+rset.getString(35).substring(2,4)+"-"+rset.getString(35).substring(7);
						//dr_cr_no=temp_dr_cr_no;
					}
					dr_cr_dt 	  = rset.getString(20)==null?"":rset.getString(20);
					//dr_cr_exg_rt  += rset.getString(21)==null?"":rset.getString(21)+"~~";						
					//diff_qty 	 += rset.getString(22)==null?"":rset.getString(22)+"~~";
					//diff_amt     += rset.getString(23)==null?"":rset.getString(23)+"~~";
					//day_diff     += rset.getString(24)==null?"":rset.getString(24)+"~~";					
					//int_type  	 = rset.getString(25)==null?"":rset.getString(25);
					//int_rate     = rset.getString(26)==null?"":rset.getString(26);
					/*if(int_amt.trim().equals(""))
					{
						int_amt += rset.getString(27)==null?"":rset.getString(27)+"~~";
					}*/				
					//dr_cr_doc_no = rset.getString(29)==null?"0":rset.getString(29);
					if(rset.getString(30)!=null && !(rset.getString(30).trim().equals("")))
					{
						dr_cr_gross_amt += nf.format(Double.parseDouble(rset.getString(30)))+"~~";
					}
					if(!(rset.getString(31).trim().equals("")) && rset.getString(31)!=null)
					{
						dr_cr_net_amt  += rset.getString(31)==null?"0":rset.getString(31)+"~~";
					}
					dr_cr_gross_usd += rset.getString(32)==null?"0":rset.getString(32)+"~~";
					gross_amt_usd += rset.getString(33)==null?"0":rset.getString(33)+"~~";
					//dr_cr_no_disp  = rset.getString(19)==null?"":rset.getString(19)+"/"+rset.getString(35).substring(2,4)+"-"+rset.getString(35).substring(7);
					dr_cr_fin_year = rset.getString(35)==null?"":rset.getString(35);
				}
			}////System.out.println("dr_cr_fin_year = "+dr_cr_fin_year);
			
			queryString="SELECT DR_CR_TOTAL_AMT_INR,DR_CR_TAX_PERCENT,REMARK FROM FMS7_DR_CR_C_FORM Where " +
			"DR_CR_NO='"+temp_dr_cr_no+"' AND DR_CR_DT=TO_DATE('"+dr_cr_dt +"','DD/MM/YYYY') AND " +
			"DR_CR_FIN_YEAR='"+dr_cr_fin_year+"' AND DR_CR_FLAG='"+cr_dr+"' ";
			//systemout.println("FMS7_DR_CR_C_FORM Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			if(rset.next())
			{
				int_rate = rset.getString(2)==null?"":rset.getString(2);
				add_amt = rset.getString(1)==null?"":rset.getString(1);
				remark  = rset.getString(3)==null?"":rset.getString(3);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void fetchCrDrNote()
	{		
		String days="365";
		//systemout.println("DR_CR_MAPPING_ID_STR 1"+DR_CR_MAPPING_ID_STR);
		try
		{			
			tax_amt_inr="0";
			
			double tax_amt = 0;
			String tax_cd = "0";
			String tax_factor = "0.00";
			double tot_tax_amt= 0;
			if(!(inv_dt.trim().equals("")) && inv_dt!=null)
			{	
				queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
							  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd+" AND " +
							  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd+" AND " +
							  "B.app_date<=TO_DATE('"+inv_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
				//systemout.println("Query For Finding Out Correct Tax Structure Details = "+queryString);
				//System.out.println("queryString======"+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					tax_cd = rset.getString(1);
					tax_factor = rset.getString(2);
					
					if(rset.getString(3).equals("1"))
					{
						if(!(dr_cr_gross_amt.trim().equals("")))
						{
							tax_amt = (Double.parseDouble(dr_cr_gross_amt)*Double.parseDouble(rset.getString(2)))/100;
						}
					}
					else if(rset.getString(3).equals("2"))
					{
						queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd+" AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd+" AND " +
									  "B.app_date<=TO_DATE('"+inv_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
						//systemout.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString1);
						//System.out.println("queryString1======"+queryString1);
				 		rset1=stmt1.executeQuery(queryString1);
				 		if(rset1.next())
				 		{
					 			if(rset1.getString(3).equals("1") )
								{
					 				if(!(dr_cr_gross_amt.trim().equals("")))
									{
					 					tax_amt = (Double.parseDouble(dr_cr_gross_amt)*Double.parseDouble(rset1.getString(2)))/100;
									}
								}
					 			tax_amt = (tax_amt*Double.parseDouble(rset.getString(2)))/100;
				 		}
				 		else
				 		{
				 			tax_amt = 0;
				 		}
					}
					else
					{
						tax_amt = 0;
					}
					System.out.println("herererer***********");
					customer_Invoice_Tax_Code.add(tax_cd);
					customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
					customer_Invoice_Tax_Amt.add(nf.format(tax_amt));
					tot_tax_amt+=tax_amt;
				}
		    }
			//systemout.println("customer_Invoice_Tax_Amt = "+customer_Invoice_Tax_Amt+", tax_amt_inr = "+tax_amt_inr);
			//for(int i=0;i<customer_Invoice_Tax_Amt.size();i++)
			//{
				tax_amt_inr = nf.format(tot_tax_amt);
			//}
			
			//tax_amt_inr = nf.format(Double.parseDouble(dr_cr_gross_amt));
			if(!(dr_cr_gross_amt.trim().equals("")))
			{
				dr_cr_net_amt = nf.format(Double.parseDouble(dr_cr_gross_amt) + Double.parseDouble(tax_amt_inr));
			}
			////System.out.println("tax_amt_inr = "+tax_amt_inr+", dr_cr_net_amt = "+dr_cr_net_amt);
			
			//Logic For Interest on Delayed Payment
			if(contract_type.trim().equalsIgnoreCase("S"))
			{
				queryString1="SELECT INT_CAL_RATE_CD,INT_CAL_PERCENTAGE,INT_CAL_SIGN FROM DLNG_SN_BILLING_DTL Where CUSTOMER_CD='"+customer_cd+"' " +
				"AND SN_NO='"+sn_no+"' AND FGSA_NO='"+fgsa_no+"' AND " +//"SN_REV_NO='"+sn_rev_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' AND " +
				"CONT_TYPE='S' AND INT_CAL_RATE_CD is not NULL ";
				////System.out.println("DLNG_SN_BILLING_DTL Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);				
				if(rset1.next())
				{
					int_cd  = rset1.getString(1)==null?"":rset1.getString(1);
					int_rate = rset1.getString(2)==null?"0":rset1.getString(2);
					int_sign = rset1.getString(3)==null?"":rset1.getString(3);
					if(!(int_rate.trim().equals("")) && !(day_diff.trim().equals("")) && !(net_amt.trim().equals("")))
					{
						int_amt = ""+nf.format((Double.parseDouble(int_rate)*Double.parseDouble(day_diff)*Double.parseDouble(net_amt))/(Double.parseDouble(days)*100));
					}
					else
					{
						int_amt = "";
					}
					if(!(int_cd.trim().equals("")))
					{
						queryString2="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST Where INT_RATE_CD='"+int_cd+"' ";
						//systemout.println("FMS7_CONT_INT_RATE_MST Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);				
						if(rset2.next())
						{
							int_type = rset2.getString(1)==null?"":rset2.getString(1);
						}
						else
						{
							int_type = "";
						}
					}
					else
					{
						int_type = "";
					}
				}
				else
				{
					queryString2="SELECT INT_CAL_RATE_CD,INT_CAL_PERCENTAGE FROM FMS7_FGSA_BILLING_DTL Where " +
					"CUSTOMER_CD='"+customer_cd+"' AND FGSA_NO='"+fgsa_no+"' AND " +
							//"FGSA_REV_NO='"+fgsa_rev_no+"' AND " +
							"CONT_TYPE='F' AND INT_CAL_RATE_CD is not NULL ";
					//systemout.println("FMS7_FGSA_BILLING_DTL Fetch Query = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);				
					if(rset2.next())
					{
						int_cd  = rset2.getString(1)==null?"":rset2.getString(1);
						int_rate = rset2.getString(2)==null?"0":rset2.getString(2);
						if(!(int_rate.trim().equals("")) && !(day_diff.trim().equals("")) && !(net_amt.trim().equals("")))
						{
							int_amt = ""+(Double.parseDouble(int_rate)*Double.parseDouble(day_diff)*Double.parseDouble(net_amt))/(Double.parseDouble(days)*100);
						}
						else
						{
							int_amt = "";
						}
						if(!(int_cd.trim().equals("")))
						{
							queryString3="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST Where INT_RATE_CD='"+int_cd+"' ";
							//systemout.println("FMS7_CONT_INT_RATE_MST Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);				
							if(rset1.next())
							{
								int_type = rset3.getString(1)==null?"":rset3.getString(1);
							}
							else
							{
								int_type = "";
							}
						}
						else
						{
							int_type = "";
						}
					}
					else
					{
						int_cd  = "";
						int_rate = "0";
						int_amt = "";
					}
				}
			}
			else if(contract_type.trim().equalsIgnoreCase("L"))
			{
				queryString1="SELECT INT_CAL_RATE_CD,INT_CAL_PERCENTAGE,INT_CAL_SIGN FROM DLNG_SN_BILLING_DTL Where CUSTOMER_CD='"+customer_cd+"' " +
				"AND SN_NO='"+sn_no+"' AND FGSA_NO='"+fgsa_no+"' AND " +//"SN_REV_NO='"+sn_rev_no+"' AND FGSA_REV_NO='0' AND " +
				"CONT_TYPE='L' AND INT_CAL_RATE_CD is not NULL ";
				//systemout.println("DLNG_SN_BILLING_DTL Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);				
				if(rset1.next())
				{
					int_cd  = rset1.getString(1)==null?"":rset1.getString(1);
					int_rate = rset1.getString(2)==null?"0":rset1.getString(2);
					int_sign = rset1.getString(3)==null?"":rset1.getString(3);
					if(!(int_rate.trim().equals("")) && !(day_diff.trim().equals("")) && !(net_amt.trim().equals("")))
					{
						int_amt = ""+(Double.parseDouble(int_rate)*Double.parseDouble(day_diff)*Double.parseDouble(net_amt))/(Double.parseDouble(days)*100);
					}
					else
					{
						int_amt = "";
					}
					if(!(int_cd.trim().equals("")))
					{
						queryString2="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST Where INT_RATE_CD='"+int_cd+"' ";
						//systemout.println("FMS7_CONT_INT_RATE_MST Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);				
						if(rset2.next())
						{
							int_type = rset2.getString(1)==null?"":rset2.getString(1);
						}
						else
						{
							int_type = "";
						}
					}
					else
					{
						int_type = "";
					}
				}
				else
				{
					queryString2="SELECT INT_CAL_RATE_CD,INT_CAL_PERCENTAGE FROM FMS7_FGSA_BILLING_DTL Where " +
					"CUSTOMER_CD='"+customer_cd+"' AND FGSA_NO='"+fgsa_no+"' AND " +//"FGSA_REV_NO='0' AND " +
					"CONT_TYPE='T' AND INT_CAL_RATE_CD is not NULL ";
					//systemout.println("FMS7_FGSA_BILLING_DTL Fetch Query = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);				
					if(rset2.next())
					{
						int_cd  = rset2.getString(1)==null?"":rset2.getString(1);
						int_rate = rset2.getString(2)==null?"0":rset2.getString(2);
						if(!(int_rate.trim().equals("")) && !(day_diff.trim().equals("")) && !(net_amt.trim().equals("")))
						{
							int_amt = ""+(Double.parseDouble(int_rate)*Double.parseDouble(day_diff)*Double.parseDouble(net_amt))/(Double.parseDouble(days)*100);
						}
						else
						{
							int_amt = "";
						}
						if(!(int_cd.trim().equals("")))
						{
							queryString3="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST Where INT_RATE_CD='"+int_cd+"' ";
							//systemout.println("FMS7_CONT_INT_RATE_MST Fetch Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);				
							if(rset1.next())
							{
								int_type = rset3.getString(1)==null?"":rset3.getString(1);
							}
							else
							{
								int_type = "";
							}
						}
						else
						{
							int_type = "";
						}
					}
					else
					{
						int_cd  = "";
						int_rate = "0";
						int_amt = "";
					}
				}
			}
			else if(contract_type.trim().equalsIgnoreCase("R"))
			{
				queryString1="SELECT INT_CAL_RATE_CD,INT_CAL_PERCENTAGE,INT_CAL_SIGN FROM FMS7_RE_GAS_BILLING_DTL Where CUSTOMER_CD='"+customer_cd+"' " +
				"AND RE_GAS_NO='"+fgsa_no+"' AND " +//" SN_REV_NO='"+sn_rev_no+"' AND FGSA_REV_NO='0' AND " +
				"CONT_TYPE='F' AND INT_CAL_RATE_CD is not NULL ";
				//systemout.println("FMS7_RE_GAS_BILLING_DTL Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);				
				if(rset1.next())
				{
					int_cd  = rset1.getString(1)==null?"":rset1.getString(1);
					int_rate = rset1.getString(2)==null?"0":rset1.getString(2);
					int_sign = rset1.getString(3)==null?"":rset1.getString(3);
					if(!(int_rate.trim().equals("")) && !(day_diff.trim().equals("")) && !(net_amt.trim().equals("")))
					{
						int_amt = ""+(Double.parseDouble(int_rate)*Double.parseDouble(day_diff)*Double.parseDouble(net_amt))/(Double.parseDouble(days)*100);
					}
					else
					{
						int_amt = "";
					}
					if(!(int_cd.trim().equals("")))
					{
						queryString2="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST Where INT_RATE_CD='"+int_cd+"' ";
						//systemout.println("FMS7_CONT_INT_RATE_MST Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);				
						if(rset2.next())
						{
							int_type = rset2.getString(1)==null?"":rset2.getString(1);
						}
						else
						{
							int_type = "";
						}
					}
					else
					{
						int_type = "";
					}
				}				
			}
			else if(contract_type.trim().equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
			{
				queryString1="SELECT INT_CAL_RATE_CD,INT_CAL_PERCENTAGE,INT_CAL_SIGN FROM FMS8_LNG_REGAS_BILLING_DTL Where  " +
				"MAPPING_ID='"+DR_CR_MAPPING_ID_STR+"' AND LTCORA_CN_FLAG='L' AND " +
				"INT_CAL_RATE_CD is not NULL ";
				//systemout.println("FMS7_RE_GAS_BILLING_DTL Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);				
				if(rset1.next())
				{
					int_cd  = rset1.getString(1)==null?"":rset1.getString(1);
					int_rate = rset1.getString(2)==null?"0":rset1.getString(2);
					int_sign = rset1.getString(3)==null?"":rset1.getString(3);
					if(!(int_rate.trim().equals("")) && !(day_diff.trim().equals("")) && !(net_amt.trim().equals("")))
					{
						int_amt = ""+(Double.parseDouble(int_rate)*Double.parseDouble(day_diff)*Double.parseDouble(net_amt))/(Double.parseDouble(days)*100);
					}
					else
					{
						int_amt = "";
					}
					if(!(int_cd.trim().equals("")))
					{
						queryString2="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST Where INT_RATE_CD='"+int_cd+"' ";
						//systemout.println("FMS7_CONT_INT_RATE_MST Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);				
						if(rset2.next())
						{
							int_type = rset2.getString(1)==null?"":rset2.getString(1);
						}
						else
						{
							int_type = "";
						}
					}
					else
					{
						int_type = "";
					}
				}				
			}
			else if(contract_type.trim().equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				queryString1="SELECT INT_CAL_RATE_CD,INT_CAL_PERCENTAGE,INT_CAL_SIGN FROM FMS8_LNG_REGAS_BILLING_DTL Where  " +
				"MAPPING_ID='"+DR_CR_MAPPING_ID_STR+"' AND  LTCORA_CN_FLAG='C' AND " +
				"INT_CAL_RATE_CD is not NULL ";
				////System.out.println("FMS7_RE_GAS_BILLING_DTL Fetch Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);				
				if(rset1.next())
				{
					int_cd  = rset1.getString(1)==null?"":rset1.getString(1);
					int_rate = rset1.getString(2)==null?"0":rset1.getString(2);
					int_sign = rset1.getString(3)==null?"":rset1.getString(3);
					if(!(int_rate.trim().equals("")) && !(day_diff.trim().equals("")) && !(net_amt.trim().equals("")))
					{
						int_amt = ""+(Double.parseDouble(int_rate)*Double.parseDouble(day_diff)*Double.parseDouble(net_amt))/(Double.parseDouble(days)*100);
					}
					else
					{
						int_amt = "";
					}
					if(!(int_cd.trim().equals("")))
					{
						queryString2="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST Where INT_RATE_CD='"+int_cd+"' ";
						////System.out.println("FMS7_CONT_INT_RATE_MST Fetch Query = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);				
						if(rset2.next())
						{
							int_type = rset2.getString(1)==null?"":rset2.getString(1);
						}
						else
						{
							int_type = "";
						}
					}
					else
					{
						int_type = "";
					}
				}
				else
				{
					////System.out.println("DR_CR_MAPPING_ID_STR"+DR_CR_MAPPING_ID_STR);
					String tempmapid[]=DR_CR_MAPPING_ID_STR.split("-");
					String tempmap_id=tempmapid[0]+"-"+tempmapid[1]+"-"+tempmapid[2]+"-0-0";
					queryString1="SELECT INT_CAL_RATE_CD,INT_CAL_PERCENTAGE,INT_CAL_SIGN FROM FMS8_LNG_REGAS_BILLING_DTL Where  " +
					"MAPPING_ID='"+tempmap_id+"' AND LTCORA_CN_FLAG='T' AND " +
					"INT_CAL_RATE_CD is not NULL ";
					////System.out.println("FMS7_RE_GAS_BILLING_DTL Fetch Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);				
					if(rset1.next())
					{
						int_cd  = rset1.getString(1)==null?"":rset1.getString(1);
						int_rate = rset1.getString(2)==null?"0":rset1.getString(2);
						int_sign = rset1.getString(3)==null?"":rset1.getString(3);
						if(!(int_rate.trim().equals("")) && !(day_diff.trim().equals("")) && !(net_amt.trim().equals("")))
						{
							int_amt = ""+(Double.parseDouble(int_rate)*Double.parseDouble(day_diff)*Double.parseDouble(net_amt))/(Double.parseDouble(days)*100);
						}
						else
						{
							int_amt = "";
						}
						if(!(int_cd.trim().equals("")))
						{
							queryString2="SELECT INT_RATE_NM FROM FMS7_CONT_INT_RATE_MST Where INT_RATE_CD='"+int_cd+"' ";
							////System.out.println("FMS7_CONT_INT_RATE_MST Fetch Query = "+queryString2);
							rset2 = stmt2.executeQuery(queryString2);				
							if(rset2.next())
							{
								int_type = rset2.getString(1)==null?"":rset2.getString(1);
							}
							else
							{
								int_type = "";
							}
						}
						else
						{
							int_type = "";
						}
					}
				}
			}
			
			queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
			"FINANCIAL_YEAR,SN_REV_NO,FGSA_REV_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,SALE_PRICE," +
			"GROSS_AMT_INR,NET_AMT_INR,CRITERIA,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,DR_CR_FLAG," +
			"DR_CR_NO,TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),DR_CR_EXG_RATE,DIFF_QTY,DIFF_AMT,DAY_DIFF," +
			"INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,DR_CR_DOC_NO,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
			"DR_CR_GROSS_AMT_USD,GROSS_AMT_USD,TAX_AMT_INR,TAX_STRUCT_CD,DR_CR_FIN_YEAR FROM FMS7_DR_CR_NOTE Where CUSTOMER_CD='"+customer_cd+"' " +
			"AND SN_NO='"+sn_no+"' AND FGSA_NO='"+fgsa_no+"' AND PLANT_SEQ_NO='"+plant_no+"' AND " +
			"CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+hlplInvoiceNo +"' AND " +
			"FINANCIAL_YEAR='"+fin_year+"' AND CRITERIA='"+criteria+"' ";
			//System.out.println("FMS7_DR_CR_NOTE Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			if(rset.next())
			{				
				//sn_rev_no     = rset.getString(8)==null?"":rset.getString(8);
				//fgsa_rev_no   = rset.getString(9)==null?"":rset.getString(9);
				//inv_dt        = rset.getString(10)==null?"":rset.getString(10);
				//qty 		  = rset.getString(11)==null?"":rset.getString(11);
				//sale_price 	  = rset.getString(12)==null?"":rset.getString(12);		
				//gross_amt 	  = rset.getString(13)==null?"":rset.getString(13);
				//net_amt 	  = rset.getString(14)==null?"":rset.getString(14);				
				//criteria 	  = rset.getString(15)==null?"":rset.getString(15);							
				//due_dt 		  = rset.getString(16)==null?"":rset.getString(16);				
				//exg_rt 		  = rset.getString(17)==null?"":rset.getString(17);
				//cr_dr 		  = rset.getString(18)==null?"":rset.getString(18);
				dr_cr_no 	  = rset.getString(19)==null?"":rset.getString(19);
				dr_cr_dt 	  = rset.getString(20)==null?"":rset.getString(20);
				dr_cr_exg_rt  = rset.getString(21)==null?"":rset.getString(21);						
				diff_qty 	 = rset.getString(22)==null?"":rset.getString(22);
				diff_amt     = rset.getString(23)==null?"":rset.getString(23);
				day_diff     = rset.getString(24)==null?"":rset.getString(24);					
				//int_type  	 = rset.getString(25)==null?"":rset.getString(25);
				
				int_rate_cal     = rset.getString(26)==null?"":rset.getString(26);
				if(int_amt.trim().equals(""))
				{
					int_amt = rset.getString(27)==null?"":rset.getString(27);
				}
				remark  	 = rset.getString(28)==null?"":rset.getString(28);
				dr_cr_doc_no = rset.getString(29)==null?"":rset.getString(29);
				if(rset.getString(30)!=null && !(rset.getString(30).trim().equals("")))
				{
					dr_cr_gross_amt = nf.format(Double.parseDouble(rset.getString(30)));
				}
				if(dr_cr_net_amt.trim().equals(""))
				{
					dr_cr_net_amt  = rset.getString(31)==null?"":rset.getString(31);
				}
				dr_cr_gross_usd = rset.getString(32)==null?"":rset.getString(32);
				gross_amt_usd = rset.getString(33)==null?"":rset.getString(33);
				//tax_amt_inr = rset.getString(34)==null?"":rset.getString(34);
				//tax_struc_cd = rset.getString(35)==null?"":rset.getString(35);
				dr_cr_no_disp  = rset.getString(19)==null?"":rset.getString(19)+"/"+rset.getString(36).substring(2,4)+"-"+rset.getString(36).substring(7);
				//////System.out.println("sn_rev_no "+sn_rev_no+"");
				//////System.out.println("dr_cr_no = "+dr_cr_no);
			}
			queryString1="SELECT A.INT_VAL FROM FMS7_INT_PAY_RATE_ENTRY A Where " +
			"A.INT_RATE_CD='"+int_cd+"' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_INT_PAY_RATE_ENTRY B " +
			"WHERE A.INT_RATE_CD=B.INT_RATE_CD AND B.EFF_DT<=TO_DATE('"+dr_cr_dt+"','DD/MM/YYYY')) ";
			////System.out.println("FMS7_INT_PAY_RATE_ENTRY Fetch Query = "+queryString1);
			rset1 = stmt1.executeQuery(queryString1);				
			if(rset1.next())
			{
				int_rate1=rset1.getString(1)==null?"0":rset1.getString(1);
			}
			else
			{
				int_rate1="0";
			}
			
			//if(int_rate_cal.trim().equals(""))
			//{
				int_rate_cal=""+(Double.parseDouble(int_rate1)+Double.parseDouble(int_rate));
			//}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	String cr_dr_yr="";
	Vector NEW_INV_SEQ_NO = new Vector();
	public void fetchDr_CrDetails()
	{
		String inv_no="";
		
		try
		{////System.out.println("DR_CR_NOTE: "+activity);
			Vector SEQ_NO = new Vector();
			if(activity.equalsIgnoreCase("insert"))
			{
				////System.out.println("hello222");
				if(criteria.equalsIgnoreCase("C-FORM"))
				{
					//System.out.println("helloccccccc");
					queryString="SELECT DISTINCT SEQ_NO FROM FMS7_CUSTOMER_PLANT_DTL WHERE PLANT_STATE NOT IN ('Gujarat') " +
							"and customer_cd='"+customer_cd+"'"; //ADDDED FOR FILTERING
					//System.out.println("FMS7_CUSTOMER_PLANT_DTL Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);				
					while(rset.next())
					{
						SEQ_NO.add(rset.getString(1)==null?"":rset.getString(1));
					}
					for(int i=0;i<SEQ_NO.size();i++)
					{
						queryString="SELECT A.SALE_PRICE,A.GROSS_AMT_INR,A.NET_AMT_INR,A.TOTAL_QTY,TO_CHAR(A.INVOICE_DT,'DD/MM/YYYY')," +
						"TO_CHAR(A.PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(A.PERIOD_END_DT,'DD/MM/YYYY'),A.PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO," +
						"A.CONTRACT_TYPE,A.FINANCIAL_YEAR,TO_CHAR(A.DUE_DT,'DD/MM/YYYY'),A.EXCHG_RATE_VALUE,A.SN_NO,A.SN_REV_NO,A.FGSA_NO," +
						"A.FGSA_REV_NO,A.GROSS_AMT_USD,TAX_STRUCT_CD " +
						",MAPPING_ID " +//ADDED FOR LTCORA AND CN
						",TAX_AMT_INR, FLAG,NEW_INV_SEQ_NO " + //SB20160401
						"FROM DLNG_INVOICE_MST A Where CUSTOMER_CD='"+customer_cd+"' AND " +
						"A.INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') AND " +
						"A.HLPL_INV_SEQ_NO || A.FINANCIAL_YEAR||A.CONTRACT_TYPE NOT IN (SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM FMS7_DR_CR_NOTE B " +
						"WHERE B.CRITERIA='"+criteria+"') AND A.PLANT_SEQ_NO='"+SEQ_NO.elementAt(i)+"' " +
						" AND APPROVED_FLAG='Y' AND FLAG='Y' " + //SB20160623
						" ORDER BY CONTRACT_TYPE "; //SB20160401
					//System.out.println("C-Form insert  Query = "+queryString);
						rset = stmt.executeQuery(queryString);
						while(rset.next())
						{			////System.out.println("SEQ_NO.elementAt(i)"+SEQ_NO.elementAt(i));	
							SALE_PRICE.add(rset.getString(1)==null?"":nf2.format(Double.parseDouble(rset.getString(1))));	
							GROSS_AMT_INR.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
							NET_AMT_INR.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
							TOTAL_QTY.add(rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4))));
							INVOICE_DT.add(rset.getString(5)==null?"":rset.getString(5));
							PERIOD_START_DT.add(rset.getString(6)==null?"":rset.getString(6));
							PERIOD_END_DT.add(rset.getString(7)==null?"":rset.getString(7));
							PLANT_SEQ_NO.add(rset.getString(8)==null?"":rset.getString(8));
							HLPL_INV_SEQ_NO.add(rset.getString(9)==null?"":rset.getString(9));
							CONTRACT_TYPE.add(rset.getString(10)==null?"":rset.getString(10));
							FINANCIAL_YEAR.add(rset.getString(11)==null?"":rset.getString(11));
							
							DUE_DT.add(rset.getString(12)==null?"":rset.getString(12));
							EXCHG_RATE_VALUE.add(rset.getString(13)==null?"":rset.getString(13));
							SN_NO.add(rset.getString(14)==null?"":rset.getString(14));
							SN_REV_NO.add(rset.getString(15)==null?"":rset.getString(15));
							FGSA_NO.add(rset.getString(16)==null?"":rset.getString(16));
							FGSA_REV_NO.add(rset.getString(17)==null?"":rset.getString(17));
							GROSS_AMT_USD.add(rset.getString(18)==null?"":rset.getString(18));
							TAX_STRUCT_CD.add(rset.getString(19)==null?"":rset.getString(19));
							DR_CR_MAPPING_ID.add(rset.getString(20)==null?"":rset.getString(20));//ADDED FOR LTCORA AND CN
							TAX_AMT_INR.add(rset.getString(21)==null?"":rset.getString(21));//SB20160401: For TAX_AMT
							FLAG.add(rset.getString(22)==null?"":rset.getString(22));//SB20160401: For FLAG
							if(rset.getString(9)!=null && !(rset.getString(9).trim().equals("")) )
							{
								if(Integer.parseInt(rset.getString(9))<10)
								{
									inv_no="000"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
								}
								else if(Integer.parseInt(rset.getString(9))<100) 
								{
									inv_no="00"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
								}
								else if(Integer.parseInt(rset.getString(9))<1000) 
								{
									inv_no="0"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
								}
								else
								{
									inv_no=rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
								}
								INVOICE_NO.add(""+inv_no);
							}
							NEW_INV_SEQ_NO.add(rset.getString(23)==null?inv_no:rset.getString(23));
						}
					}
				}
				else
				{
					//System.out.println("hello else---");
					queryString="SELECT SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,TOTAL_QTY,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')," +
					"TO_CHAR(PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(PERIOD_END_DT,'DD/MM/YYYY'),PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO," +
					"CONTRACT_TYPE,A.FINANCIAL_YEAR,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,SN_NO,SN_REV_NO,FGSA_NO,FGSA_REV_NO," +
					"GROSS_AMT_USD,TAX_STRUCT_CD " +
					",MAPPING_ID " +//ADDED OR  LTCORA AND CN
					",TAX_AMT_INR, FLAG,NEW_INV_SEQ_NO " + //SB20160401
					"FROM DLNG_INVOICE_MST A Where CUSTOMER_CD='"+customer_cd+"' AND A.FINANCIAL_YEAR='"+cr_dr_yr+"' AND " +
					"INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') AND " +
					"A.HLPL_INV_SEQ_NO||A.FINANCIAL_YEAR||A.CONTRACT_TYPE NOT IN (SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM FMS7_DR_CR_NOTE B WHERE " +
					"B.CRITERIA='"+criteria+"')" +
					" AND APPROVED_FLAG='Y' AND FLAG='Y' " + //SB20160623
					" ORDER BY CONTRACT_TYPE "; //SB20160401		
					//System.out.println("--RU-->"+queryString);
				}
				
				
			}
			else if(activity.equalsIgnoreCase("update"))
			{////System.out.println("2. DR_CR_NOTE: "+activity);
				//System.out.println("hello update");
				if(criteria.equalsIgnoreCase("C-FORM"))
				{
					////System.out.println("hello2  c update");
					queryString="SELECT DISTINCT SEQ_NO FROM FMS7_CUSTOMER_PLANT_DTL WHERE PLANT_STATE NOT IN ('Gujarat')";
					//System.out.println("FMS7_CUSTOMER_PLANT_DTL Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);				
					while(rset.next())
					{
						SEQ_NO.add(rset.getString(1)==null?"":rset.getString(1));
					}
					for(int i=0;i<SEQ_NO.size();i++)
					{
						queryString="SELECT A.SALE_PRICE,A.GROSS_AMT_INR,A.NET_AMT_INR,A.TOTAL_QTY,TO_CHAR(A.INVOICE_DT,'DD/MM/YYYY')," +
						"TO_CHAR(A.PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(A.PERIOD_END_DT,'DD/MM/YYYY'),A.PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO," +
						"A.CONTRACT_TYPE,A.FINANCIAL_YEAR,TO_CHAR(A.DUE_DT,'DD/MM/YYYY'),A.EXCHG_RATE_VALUE,A.SN_NO,A.SN_REV_NO," +
						"A.FGSA_NO,A.FGSA_REV_NO,A.GROSS_AMT_USD,A.TAX_STRUCT_CD " +
						",MAPPING_ID " + //ADDED FOR LTCORA AND CN
						",TAX_AMT_INR, FLAG,NEW_INV_SEQ_NO " + //SB20160401
						"FROM DLNG_INVOICE_MST A Where " +
						"A.CUSTOMER_CD='"+customer_cd+"' AND A.INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND " +
						"A.INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') AND A.HLPL_INV_SEQ_NO||A.FINANCIAL_YEAR||A.CONTRACT_TYPE IN " +
						"(SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM FMS7_DR_CR_NOTE B WHERE B.CRITERIA='"+criteria+"' " +
						"AND B.DR_CR_FLAG='"+cr_dr+"') AND A.PLANT_SEQ_NO='"+SEQ_NO.elementAt(i)+"'" +
						" AND APPROVED_FLAG='Y' AND FLAG='Y' " + //SB20160623
						" ORDER BY CONTRACT_TYPE "; //SB20160401
						//System.out.println("C-Form Update Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);
						while(rset.next())
						{				
							SALE_PRICE.add(rset.getString(1)==null?"":nf2.format(Double.parseDouble(rset.getString(1))));	
							GROSS_AMT_INR.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
							NET_AMT_INR.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
							TOTAL_QTY.add(rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4))));
							INVOICE_DT.add(rset.getString(5)==null?"":rset.getString(5));
							PERIOD_START_DT.add(rset.getString(6)==null?"":rset.getString(6));
							PERIOD_END_DT.add(rset.getString(7)==null?"":rset.getString(7));
							PLANT_SEQ_NO.add(rset.getString(8)==null?"":rset.getString(8));
							HLPL_INV_SEQ_NO.add(rset.getString(9)==null?"":rset.getString(9));
							CONTRACT_TYPE.add(rset.getString(10)==null?"":rset.getString(10));
							FINANCIAL_YEAR.add(rset.getString(11)==null?"":rset.getString(11));
							
							DUE_DT.add(rset.getString(12)==null?"":rset.getString(12));
							EXCHG_RATE_VALUE.add(rset.getString(13)==null?"":rset.getString(13));
							SN_NO.add(rset.getString(14)==null?"":rset.getString(14));
							SN_REV_NO.add(rset.getString(15)==null?"":rset.getString(15));
							FGSA_NO.add(rset.getString(16)==null?"":rset.getString(16));
							FGSA_REV_NO.add(rset.getString(17)==null?"":rset.getString(17));
							GROSS_AMT_USD.add(rset.getString(18)==null?"":rset.getString(18));
							TAX_STRUCT_CD.add(rset.getString(19)==null?"":rset.getString(19));
							DR_CR_MAPPING_ID.add(rset.getString(20)==null?"":rset.getString(20));//ADDED FOR LTCORA AND CN
							TAX_AMT_INR.add(rset.getString(21)==null?"":rset.getString(21));//SB20160401: For TAX_AMT
							FLAG.add(rset.getString(22)==null?"":rset.getString(22));//SB20160401: For FLAG
							if(rset.getString(9)!=null && !(rset.getString(9).trim().equals("")) )
							{
								if(Integer.parseInt(rset.getString(9))<10)
								{
									inv_no="000"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
								}
								else if(Integer.parseInt(rset.getString(9))<100) 
								{
									inv_no="00"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
								}
								else if(Integer.parseInt(rset.getString(9))<1000) 
								{
									inv_no="0"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
								}
								else
								{
									inv_no=rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
								}
								INVOICE_NO.add(""+inv_no);
							}	
							NEW_INV_SEQ_NO.add(rset.getString(23)==null?inv_no:rset.getString(23));
						}
					}
				}
				else
				{
					//System.out.println("helloc update else");
					queryString="SELECT SALE_PRICE,GROSS_AMT_INR,NET_AMT_INR,TOTAL_QTY,TO_CHAR(INVOICE_DT,'DD/MM/YYYY')," +
					"TO_CHAR(PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(PERIOD_END_DT,'DD/MM/YYYY'),PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO," +
					"CONTRACT_TYPE,A.FINANCIAL_YEAR,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,SN_NO,SN_REV_NO,FGSA_NO,FGSA_REV_NO," +
					"GROSS_AMT_USD,TAX_STRUCT_CD " +
					",MAPPING_ID " + //ADDED FOR LTCORA AND CN
					",TAX_AMT_INR, FLAG,NEW_INV_SEQ_NO " + //SB20160401
					"FROM DLNG_INVOICE_MST A Where CUSTOMER_CD='"+customer_cd+"' AND " +
					"INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND " +
					"INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') AND A.HLPL_INV_SEQ_NO||A.FINANCIAL_YEAR||A.CONTRACT_TYPE IN " +
					"(SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM FMS7_DR_CR_NOTE B WHERE B.CRITERIA='"+criteria+"' AND B.DR_CR_FLAG='"+cr_dr+"')" +
					" AND APPROVED_FLAG='Y' AND FLAG='Y' " + //SB20160623
					" ORDER BY CONTRACT_TYPE "; //SB20160401
					////System.out.println("----"+queryString);
				}
			}
			if(!criteria.equalsIgnoreCase("C-FORM"))
			{
				//System.out.println("hello not c form");
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{				
					SALE_PRICE.add(rset.getString(1)==null?"":nf2.format(Double.parseDouble(rset.getString(1))));	
					GROSS_AMT_INR.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					NET_AMT_INR.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					TOTAL_QTY.add(rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4))));
					INVOICE_DT.add(rset.getString(5)==null?"":rset.getString(5));
					PERIOD_START_DT.add(rset.getString(6)==null?"":rset.getString(6));
					PERIOD_END_DT.add(rset.getString(7)==null?"":rset.getString(7));
					PLANT_SEQ_NO.add(rset.getString(8)==null?"":rset.getString(8));
					HLPL_INV_SEQ_NO.add(rset.getString(9)==null?"":rset.getString(9));
					CONTRACT_TYPE.add(rset.getString(10)==null?"":rset.getString(10));
					FINANCIAL_YEAR.add(rset.getString(11)==null?"":rset.getString(11));
					
					DUE_DT.add(rset.getString(12)==null?"":rset.getString(12));
					EXCHG_RATE_VALUE.add(rset.getString(13)==null?"":rset.getString(13));
					SN_NO.add(rset.getString(14)==null?"":rset.getString(14));
					SN_REV_NO.add(rset.getString(15)==null?"":rset.getString(15));
					FGSA_NO.add(rset.getString(16)==null?"":rset.getString(16));
					FGSA_REV_NO.add(rset.getString(17)==null?"":rset.getString(17));
					GROSS_AMT_USD.add(rset.getString(18)==null?"":rset.getString(18));
					TAX_STRUCT_CD.add(rset.getString(19)==null?"":rset.getString(19));
					DR_CR_MAPPING_ID.add(rset.getString(20)==null?"":rset.getString(20));//ADDED FOR LTCORA AND CN
					TAX_AMT_INR.add(rset.getString(21)==null?"":rset.getString(21));//SB20160401: For TAX_AMT
					FLAG.add(rset.getString(22)==null?"":rset.getString(22));//SB20160401: For FLAG
					if(rset.getString(9)!=null && !(rset.getString(9).trim().equals("")) )
					{
						if(Integer.parseInt(rset.getString(9))<10)
						{
							inv_no="000"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
						}
						else if(Integer.parseInt(rset.getString(9))<100) 
						{
							inv_no="00"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
						}
						else if(Integer.parseInt(rset.getString(9))<1000) 
						{
							inv_no="0"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
						}
						else
						{
							inv_no=rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
						}
						INVOICE_NO.add(""+inv_no);
					}
					NEW_INV_SEQ_NO.add(rset.getString(23)==null?inv_no:rset.getString(23));
				}
			}
			for(int i=0;i<FINANCIAL_YEAR.size();i++)
			{
				if(!criteria.equalsIgnoreCase("REV_INV") && FLAG.elementAt(i).equals("X"))
				{
					queryString ="SELECT B.DR_CR_NO,B.DR_CR_FIN_YEAR, DR_CR_DOC_NO " +
					", APRV_BY, TO_CHAR(APRV_DT,'DD/MM/YYYY')" + //SB20160617
					" FROM FMS7_DR_CR_NOTE B " +
					"WHERE B.CRITERIA='REV_INV' AND " +
					"B.HLPL_INV_SEQ_NO='"+HLPL_INV_SEQ_NO.elementAt(i)+"' AND B.FINANCIAL_YEAR='"+FINANCIAL_YEAR.elementAt(i)+"' " +
					"AND CONTRACT_TYPE='"+CONTRACT_TYPE.elementAt(i)+"' ";//ADDED TO FILTER DATA 
				}
				else
				{
				queryString ="SELECT B.DR_CR_NO,B.DR_CR_FIN_YEAR, DR_CR_DOC_NO " +
						", APRV_BY, TO_CHAR(APRV_DT,'DD/MM/YYYY')" + //SB20160617
						" FROM FMS7_DR_CR_NOTE B " +
						"WHERE B.CRITERIA='"+criteria+"' AND " +
						"B.HLPL_INV_SEQ_NO='"+HLPL_INV_SEQ_NO.elementAt(i)+"' AND B.FINANCIAL_YEAR='"+FINANCIAL_YEAR.elementAt(i)+"' " +
						"AND CONTRACT_TYPE='"+CONTRACT_TYPE.elementAt(i)+"' ";//ADDED TO FILTER DATA 
				}
				////System.out.println("DR_CR_NOTE: FMS7_DR_CR_NOTE : "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					DR_CR_NO.add(rset.getString(1)==null?"":rset.getString(1));
					DR_CR_NO_DISP.add(rset.getString(1)==null?"":rset.getString(1)+("/"+rset.getString(2).substring(2,4)+"-"+rset.getString(2).substring(7)));
					DR_CR_FIN_YEAR.add(rset.getString(2)==null?"":rset.getString(2));
					DR_CR_DOC_NO.add(rset.getString(3)==null?"":rset.getString(3)); //SB20160502
					DR_CR_APRV_BY.add(rset.getString(4)==null?"":rset.getString(4)); //SB20160617
					DR_CR_APRV_DT.add(rset.getString(5)==null?"":rset.getString(5)); //SB20160617
				}
				else
				{
					DR_CR_NO.add("");
					DR_CR_FIN_YEAR.add("");
					DR_CR_NO_DISP.add("");
					DR_CR_DOC_NO.add(""); //SB20160502
					DR_CR_APRV_BY.add(""); //SB20160617
					DR_CR_APRV_DT.add(""); //SB20160617
				}
				
				queryString="select remark from fms7_dr_cr_dtl where CUSTOMER_CD='"+customer_cd+"' and HLPL_INV_SEQ_NO='"+HLPL_INV_SEQ_NO.elementAt(i)+"' and con_type='"+CONTRACT_TYPE.elementAt(i)+"' and FINANCIAL_YEAR='"+FINANCIAL_YEAR.elementAt(i)+"' ";
				////System.out.println("---"+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next()) {
					DR_CR_DTL_REM.add(rset.getString(1)==null?"":rset.getString(1));
				} else {
					DR_CR_DTL_REM.add("");
				}
			}
			////System.out.println("FINANCIAL_YEAR----"+FINANCIAL_YEAR);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	Vector DR_CR_NEW_INV_SEQ_NO = new Vector();
	public void fetchINV_Details()
	{
		String inv_no="";
		
		try
		{
			String query="select hlpl_inv_seq_no from fms7_dr_cr_dtl where customer_cd='"+customer_cd+"' and " +
					"INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND " +
					"INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') ";	
//			System.out.println("---query--"+query);
			rset = stmt.executeQuery(query);				
			while(rset.next())
			{
				Dr_cr_hlpl_inv_no.add(rset.getString(1)); 
			}
			////System.out.println("Dr_cr_hlpl_inv_no--"+Dr_cr_hlpl_inv_no.size());
			for(int i=0;i<Dr_cr_hlpl_inv_no.size();i++)
			{
				queryString="SELECT A.SALE_PRICE,A.GROSS_AMT_INR,A.NET_AMT_INR,A.TOTAL_QTY,TO_CHAR(A.INVOICE_DT,'DD/MM/YYYY')," +
						"TO_CHAR(A.PERIOD_START_DT,'DD/MM/YYYY'),TO_CHAR(A.PERIOD_END_DT,'DD/MM/YYYY')," +
						"A.PLANT_SEQ_NO,A.HLPL_INV_SEQ_NO,A.CONTRACT_TYPE,A.FINANCIAL_YEAR,TO_CHAR(A.DUE_DT,'DD/MM/YYYY')," +
						"A.EXCHG_RATE_VALUE,A.SN_NO,A.SN_REV_NO,A.FGSA_NO,A.FGSA_REV_NO,A.GROSS_AMT_USD,A.TAX_STRUCT_CD ," +
						"A.MAPPING_ID ,A.TAX_AMT_INR,A.FLAG,b.remark,a.remark_1,a.remark_2,a.remark_3,a.contact_person_cd"
						+ ",a.new_inv_seq_no "
						+ "FROM DLNG_INVOICE_MST A,fms7_dr_cr_dtl b  Where a.CUSTOMER_CD='"+customer_cd+"' AND a.financial_year='"+dr_cr_year+"' and " +
						"A.INVOICE_DT >= TO_DATE('"+from_dt+"','DD/MM/YYYY') AND A.INVOICE_DT <= TO_DATE('"+to_dt+"','DD/MM/YYYY') " +
						"and a.hlpl_inv_seq_no=b.hlpl_inv_seq_no and b.hlpl_inv_seq_no='"+Dr_cr_hlpl_inv_no.elementAt(i)+"' AND A.APPROVED_FLAG='Y' AND A.FLAG!='A' ORDER BY A.CONTRACT_TYPE "; //RG20161114 For fetching invoice data
//					System.out.println("---query--"+queryString);
					rset = stmt.executeQuery(queryString);				
					while(rset.next())
					{
						SALE_PRICE_DR_CR.add(rset.getString(1)==null?"":nf2.format(Double.parseDouble(rset.getString(1))));	
						GROSS_AMT_INR_DR_CR.add(rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2))));
						NET_AMT_INR_DR_CR.add(rset.getString(3)==null?"":nf.format(Double.parseDouble(rset.getString(3))));
						TOTAL_QTY_DR_CR.add(rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4))));
						INVOICE_DT_DR_CR.add(rset.getString(5)==null?"":rset.getString(5));
						PERIOD_START_DT_DR_CR.add(rset.getString(6)==null?"":rset.getString(6));
						PERIOD_END_DT_DR_CR.add(rset.getString(7)==null?"":rset.getString(7));
						PLANT_SEQ_NO_DR_CR.add(rset.getString(8)==null?"":rset.getString(8));
						HLPL_INV_SEQ_NO_DR_CR.add(rset.getString(9)==null?"":rset.getString(9));
						CONTRACT_TYPE_DR_CR.add(rset.getString(10)==null?"":rset.getString(10));
						FINANCIAL_YEAR_DR_CR.add(rset.getString(11)==null?"":rset.getString(11));
						
						DUE_DT_DR_CR.add(rset.getString(12)==null?"":rset.getString(12));
						EXCHG_RATE_VALUE_DR_CR.add(rset.getString(13)==null?"":rset.getString(13));
						SN_NO_DR_CR.add(rset.getString(14)==null?"":rset.getString(14));
						SN_REV_NO_DR_CR.add(rset.getString(15)==null?"":rset.getString(15));
						FGSA_NO_DR_CR.add(rset.getString(16)==null?"":rset.getString(16));
						FGSA_REV_NO_DR_CR.add(rset.getString(17)==null?"":rset.getString(17));
						GROSS_AMT_USD_DR_CR.add(rset.getString(18)==null?"":rset.getString(18));
						TAX_STRUCT_CD_DR_CR.add(rset.getString(19)==null?"":rset.getString(19));
						DR_CR_MAPPING_ID_DR_CR.add(rset.getString(20)==null?"":rset.getString(20));
						TAX_AMT_INR_DR_CR.add(rset.getString(21)==null?"":rset.getString(21));
						FLAG_DR_CR.add(rset.getString(22)==null?"":rset.getString(22));	
						REMARK_DR_CR.add(rset.getString(23)==null?"":rset.getString(23));
						REMARK_1_DR_CR.add(rset.getString(24)==null?"":rset.getString(24));
						REMARK_2_DR_CR.add(rset.getString(25)==null?"":rset.getString(25));
						REMARK_3_DR_CR.add(rset.getString(26)==null?"":rset.getString(26));
						Contact_person_cd_DR_CR.add(rset.getString(27)==null?"":rset.getString(27));
						
						if(rset.getString(9)!=null && !(rset.getString(9).trim().equals("")) )
						{
							if(Integer.parseInt(rset.getString(9))<10)
							{
								inv_no="000"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
							}
							else if(Integer.parseInt(rset.getString(9))<100) 
							{
								inv_no="00"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
							}
							else if(Integer.parseInt(rset.getString(9))<1000) 
							{
								inv_no="0"+rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
							}
							else
							{
								inv_no=rset.getString(9)+"/"+rset.getString(11).substring(2,4)+"-"+rset.getString(11).substring(7);
							}
							INVOICE_NO_DR_CR.add(""+inv_no);
						}	
						DR_CR_NEW_INV_SEQ_NO.add(rset.getString(28)==null?inv_no:rset.getString(28));
					}
			}
			
//			System.out.println("INVOICE_NO_DR_CR"+INVOICE_NO_DR_CR);
			
			String query2="select pan_no, gst_tin_no, cst_tin_no from fms7_customer_mst where customer_cd='"+customer_cd+"'";
			rset = stmt.executeQuery(query2);	
			////System.out.println("---query2---"+query2);
			if(rset.next())
			{
				pan_no=rset.getString(1)==null?"-":rset.getString(1);
				gst_tin_no=rset.getString(2);
				cst_tin_no=rset.getString(3);
			}
			for(int i=0;i<FINANCIAL_YEAR_DR_CR.size();i++){
				queryString1="select flag,dr_cr_doc_no from fms7_dr_cr_note where "
						+ "CUSTOMER_CD='"+customer_cd+"' and HLPL_INV_SEQ_NO='"+HLPL_INV_SEQ_NO_DR_CR.elementAt(i)+"' "
						+ "and contract_type='"+CONTRACT_TYPE_DR_CR.elementAt(i)+"' and FINANCIAL_YEAR='"+FINANCIAL_YEAR_DR_CR.elementAt(i)+"' ";
//				System.out.println("---"+queryString1);
				rset = stmt.executeQuery(queryString1);				
				if(rset.next()) {
					flag_dr_cr.add(rset.getString(1)==null?"":rset.getString(1));
					dr_cr_no2.add(rset.getString(2)==null?"":rset.getString(2));
				} else {
					flag_dr_cr.add("N");
					dr_cr_no2.add("-");
				}
			
			queryString2 = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' '),addr_flag " +
			  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
			  "WHERE A.customer_cd='"+customer_cd+"' AND A.def_inv_flag='Y' AND A.seq_no='"+Contact_person_cd_DR_CR.elementAt(i)+"' AND " +
			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
			  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND B.seq_no='"+Contact_person_cd_DR_CR.elementAt(i)+"' AND " +
			  "B.eff_dt<=TO_DATE('"+PERIOD_START_DT_DR_CR.elementAt(i)+"','DD/MM/YYYY'))";
//			System.out.println("STEP-1A.4:FMS7_CUSTOMER_CONTACT_MST: "+queryString2);
			rset = stmt.executeQuery(queryString2);
			if(rset.next())
			{
				if(rset.getString(3).equals(" "))
				{
					contact_Person_Name_And_Designation1.add(rset.getString(2).trim());
				}
				else
				{
					contact_Person_Name_And_Designation1.add(rset.getString(2).trim()+" ("+rset.getString(3).trim()+")");
				}
				contact_addr_flag1.add(rset.getString(4)==null?"":rset.getString(4));
			} else {
				contact_addr_flag1.add("");
				contact_Person_Name_And_Designation1.add("");
			}
			
			if(contact_addr_flag1.elementAt(i).toString().trim().equalsIgnoreCase("R") || contact_addr_flag1.elementAt(i).toString().trim().equalsIgnoreCase("C") || contact_addr_flag1.elementAt(i).toString().trim().equalsIgnoreCase("B"))
			{
				queryString3 = "SELECT addr,city,pin " +
							  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
							  "WHERE A.customer_cd="+customer_cd+" AND A.address_type='"+contact_addr_flag1.elementAt(i)+"' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag1.elementAt(i)+"' AND " +
							  "B.eff_dt<=TO_DATE('"+PERIOD_START_DT_DR_CR.elementAt(i)+"','DD/MM/YYYY'))";
				////System.out.println("STEP-1A.5:FMS7_CUSTOMER_ADDRESS_MST: "+queryString3);
				rset = stmt.executeQuery(queryString3);
				if(rset.next()) {
					contact_Customer_Person_Address1.add(rset.getString(1)==null?"":rset.getString(1));
					contact_Customer_Person_City1.add(rset.getString(2)==null?"":rset.getString(2));
					contact_Customer_Person_Pin1.add(rset.getString(3)==null?"":rset.getString(3));
				} else {
					contact_Customer_Person_Address1.add("");
					contact_Customer_Person_City1.add("");
					contact_Customer_Person_Pin1.add("");
				}
			}
			else
			{
				String new_plant_no="";
				if(!contact_addr_flag1.elementAt(i).toString().equalsIgnoreCase(""))
				new_plant_no = contact_addr_flag1.elementAt(i).toString().trim().substring(1);
				
				//if(new_plant_no.length()>=1) {
					queryString4 = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd='"+customer_cd+"' AND A.seq_no='"+new_plant_no+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+new_plant_no+"' AND " +
								  "B.eff_dt<=TO_DATE('"+PERIOD_START_DT_DR_CR.elementAt(i)+"','DD/MM/YYYY'))";
				//} else {
//					queryString = "SELECT plant_addr,plant_city,plant_pin " +
//								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
//								  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+customer_plant_seq_no+"' AND " +
//								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
//								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+customer_plant_seq_no+"' AND " +
//								  "B.eff_dt<=TO_DATE('"+PERIOD_START_DT_DR_CR.elementAt(i)+"','DD/MM/YYYY'))";
//				}
				////System.out.println("STEP-1A.6:FMS7_CUSTOMER_PLANT_DTL: "+queryString4);
				rset = stmt.executeQuery(queryString4);
				if(rset.next()) {
					contact_Customer_Person_Address1.add(rset.getString(1)==null?"":rset.getString(1));
					contact_Customer_Person_City1.add(rset.getString(2)==null?"":rset.getString(2));
					contact_Customer_Person_Pin1.add(rset.getString(3)==null?"":rset.getString(3));
				} else {
					contact_Customer_Person_Address1.add("");
					contact_Customer_Person_City1.add("");
					contact_Customer_Person_Pin1.add("");
				}
			}
		}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	String view_flag = "N";
	String invoice_dt = "",tot_qty="0",sale_rate="0",gross_amt_inr="0",net_amt_inr="0",exch_rate="0";
	String dr_cr_aprv_by = "0";
	public void Inv_dates()
	{
		try
		{
			if(view_flag.equals("Y")) {
				queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
						"FINANCIAL_YEAR,SN_REV_NO,FGSA_REV_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,SALE_PRICE," +
						"GROSS_AMT_INR,NET_AMT_INR,CRITERIA,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,DR_CR_FLAG," +
						"DR_CR_NO,TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),DR_CR_EXG_RATE,DIFF_QTY,DIFF_AMT,DAY_DIFF," +
						"INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,DR_CR_DOC_NO,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
						"DR_CR_GROSS_AMT_USD,GROSS_AMT_USD,TAX_AMT_INR,TAX_STRUCT_CD,DR_CR_FIN_YEAR,DR_CR_SALE_RATE "
						+ " "
						+ "FROM FMS7_DR_CR_NOTE Where CUSTOMER_CD='"+customer_cd+"' " +
						"AND SN_NO='"+sn_no+"' AND FGSA_NO='"+fgsa_no+"' AND PLANT_SEQ_NO='"+plant_no+"' AND " +
						"CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+hlplInvoiceNo +"' AND " +
						"FINANCIAL_YEAR='"+fin_year+"' AND CRITERIA='"+criteria+"' and DR_CR_FLAG='"+cr_dr1+"' ";
					rset = stmt.executeQuery(queryString);				
					if(rset.next())
					{
						invoice_dt = rset.getString(10)==null?"":rset.getString(10); 
						tot_qty = rset.getString(11)==null?"0":rset.getString(11);
						sale_rate = rset.getString(12)==null?"0":nf2.format(Double.parseDouble(rset.getString(12)));
						gross_amt_inr = rset.getString(13)==null?"0":nf3.format(Double.parseDouble(rset.getString(13)));
						net_amt_inr = rset.getString(14)==null?"0":nf3.format(Double.parseDouble(rset.getString(14)));
						exch_rate = rset.getString(17)==null?"0":rset.getString(17);
						tax_amt_inr = rset.getString(34)==null?"":nf3.format(Double.parseDouble(rset.getString(34)));
//						new_inv_seq_no = rset.getString(38)==null?"":rset.getString(38);
						
						dr_cr_sales_rate = rset.getString(37)==null?"":nf2.format(Double.parseDouble(rset.getString(37)));
						dr_cr_no 	  = rset.getString(19)==null?"":rset.getString(19);
						dr_cr_dt 	  = rset.getString(20)==null?"":rset.getString(20);
						dr_cr_exg_rt  = rset.getString(21)==null?"":rset.getString(21);						
						diff_qty 	 = rset.getString(22)==null?"":rset.getString(22);
						diff_amt     = rset.getString(23)==null?"":nf3.format(Double.parseDouble(rset.getString(23)));
						day_diff     = rset.getString(24)==null?"":rset.getString(24);					
						int_rate_cal     = rset.getString(26)==null?"":rset.getString(26);
						if(int_amt.trim().equals(""))
						{
							int_amt = rset.getString(27)==null?"":rset.getString(27);
						}
						remark  	 = rset.getString(28)==null?"":rset.getString(28);
						dr_cr_doc_no = rset.getString(29)==null?"":rset.getString(29);
						if(rset.getString(30)!=null && !(rset.getString(30).trim().equals("")))
						{
							dr_cr_gross_amt = nf3.format(Double.parseDouble(rset.getString(30)));
						}
						if(dr_cr_net_amt.trim().equals(""))
						{
							dr_cr_net_amt  = rset.getString(31)==null?"":nf3.format(Double.parseDouble(rset.getString(31)));
						}
						dr_cr_gross_usd = rset.getString(32)==null?"":rset.getString(32);
						gross_amt_usd = rset.getString(33)==null?"":rset.getString(33);
						dr_cr_no_disp  = rset.getString(19)==null?"":rset.getString(19)+"/"+rset.getString(36).substring(2,4)+"-"+rset.getString(36).substring(7);
					}
			} else {
				if(!st_dt.equals("")) {
					String dtgst = st_dt.substring(6,10)+st_dt.substring(3,5)+st_dt.substring(0,2);
					int dt2 = Integer.parseInt(dtgst);
					if(dt2>=gst_eff_dt) {
						date_flag = true;
					}
				}
				if(!st_dt.equalsIgnoreCase("")){
					queryString = "select TO_CHAR(TRUNC(to_date('"+st_dt+"','dd/mm/yyyy'),'DD')  + level - 1,'DD/MM/YYYY')as" +
							" EACH_DATE from dual A connect by level <= (SELECT to_date('"+end_dt+"','dd/mm/yyyy')- to_date('"+st_dt+"','dd/mm/yyyy') FROM DUAL )+1	";
					//System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);				
					while(rset.next())
					{
						Invoice_period_date1.add(rset.getString(1));	
					}
				}
				
				tax_amt_inr="0";
				
				double tax_amt = 0;
				String tax_cd = "0";
				String tax_factor = "0.00";
				double tot_tax_amt= 0;
					queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd='"+tax_struc_cd+"' AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd='"+tax_struc_cd+"' AND " +
								  "B.app_date<=TO_DATE('"+inv_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
					rset1=stmt1.executeQuery(queryString);
					while(rset1.next())
					{
						tax_cd = rset1.getString(1);
						tax_factor = rset1.getString(2);
						if(rset1.getString(3).equals("1"))
						{
							if(!(drcrgrossamt.trim().equals("")))
							{
								tax_amt = (Double.parseDouble(drcrgrossamt)*Double.parseDouble(rset1.getString(2)))/100;
							}
						}
						
						else if(rset1.getString(3).equals("2"))
						{
							queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
										  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd='"+tax_struc_cd+"' AND " +
										  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd='"+tax_struc_cd+"' AND " +
										  "B.app_date<=TO_DATE('"+inv_dt+"','DD/MM/YYYY')) AND A.tax_code='"+rset1.getString(4)+"'";
					 		rset2=stmt2.executeQuery(queryString1);
					 		if(rset2.next())
					 		{
						 			if(rset2.getString(3).equals("1") )
									{
						 				if(!(drcrgrossamt.trim().equals("")))
										{
						 					tax_amt = (Double.parseDouble(drcrgrossamt)*Double.parseDouble(rset2.getString(2)))/100;
										}
									}
						 			tax_amt = (tax_amt*Double.parseDouble(rset1.getString(2)))/100;
					 		}
					 		else
					 		{
					 			tax_amt = 0;
					 		}
						}
						else
						{
							tax_amt = 0;
						}
						System.out.println("herererer***********11111");
						customer_Invoice_Tax_Code.add(tax_cd);
						customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
						if(date_flag) {
							customer_Invoice_Tax_Amt.add(nf.format(Math.round(tax_amt)));
							tot_tax_amt+=Math.round(tax_amt);
						} else {
							customer_Invoice_Tax_Amt.add(nf.format(tax_amt));
							tot_tax_amt+=tax_amt;
						}
					}
					tax_amt_inr = nf.format(tot_tax_amt);

				if(!(drcrgrossamt.trim().equals("")))
				{
					if(date_flag) {
						dr_cr_net_amt = nf.format(Math.round(Double.parseDouble(drcrgrossamt) + Double.parseDouble(tax_amt_inr)));
					} else {
						dr_cr_net_amt = nf.format(Double.parseDouble(drcrgrossamt) + Double.parseDouble(tax_amt_inr));
					}
					
				}
				
				if(!drcrgrossamt.equalsIgnoreCase("")  || !drcrgrossamt.equalsIgnoreCase("null")){	
				queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
					"FINANCIAL_YEAR,SN_REV_NO,FGSA_REV_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,SALE_PRICE," +
					"GROSS_AMT_INR,NET_AMT_INR,CRITERIA,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,DR_CR_FLAG," +
					"DR_CR_NO,TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),DR_CR_EXG_RATE,DIFF_QTY,DIFF_AMT,DAY_DIFF," +
					"INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,DR_CR_DOC_NO,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
					"DR_CR_GROSS_AMT_USD,GROSS_AMT_USD,TAX_AMT_INR,TAX_STRUCT_CD,DR_CR_FIN_YEAR,DR_CR_SALE_RATE,NVL(APRV_BY,'0') "
					+ "FROM FMS7_DR_CR_NOTE Where CUSTOMER_CD='"+customer_cd+"' " +
					"AND SN_NO='"+sn_no+"' AND FGSA_NO='"+fgsa_no+"' AND PLANT_SEQ_NO='"+plant_no+"' AND " +
					"CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+hlplInvoiceNo +"' AND " +
					"FINANCIAL_YEAR='"+fin_year+"' AND CRITERIA='"+criteria+"' and DR_CR_FLAG='"+cr_dr1+"' ";
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{	
					dr_cr_aprv_by = rset.getString(28)==null?"0":rset.getString(28);
					dr_cr_sales_rate = rset.getString(37)==null?"":nf2.format(Double.parseDouble(rset.getString(37)));
					dr_cr_no 	  = rset.getString(19)==null?"":rset.getString(19);
					dr_cr_dt 	  = rset.getString(20)==null?"":rset.getString(20);
					dr_cr_exg_rt  = rset.getString(21)==null?"":rset.getString(21);						
					diff_qty 	 = rset.getString(22)==null?"":rset.getString(22);
					diff_amt     = rset.getString(23)==null?"":rset.getString(23);
					day_diff     = rset.getString(24)==null?"":rset.getString(24);					
					int_rate_cal     = rset.getString(26)==null?"":rset.getString(26);
					if(int_amt.trim().equals(""))
					{
						int_amt = rset.getString(27)==null?"":rset.getString(27);
					}
					remark  	 = rset.getString(28)==null?"":rset.getString(28);
					dr_cr_doc_no = rset.getString(29)==null?"":rset.getString(29);
					if(rset.getString(30)!=null && !(rset.getString(30).trim().equals("")))
					{
						dr_cr_gross_amt = nf.format(Double.parseDouble(rset.getString(30)));
					}
					if(dr_cr_net_amt.trim().equals(""))
					{
						dr_cr_net_amt  = rset.getString(31)==null?"":rset.getString(31);
					}
					dr_cr_gross_usd = rset.getString(32)==null?"":rset.getString(32);
					gross_amt_usd = rset.getString(33)==null?"":rset.getString(33);
					dr_cr_no_disp  = rset.getString(19)==null?"":rset.getString(19)+"/"+rset.getString(36).substring(2,4)+"-"+rset.getString(36).substring(7);
				}
				}
				if(!dr_cr_net_amt.equals("")) {
					if(date_flag) {
						dr_cr_net_amt = nf.format(Math.round(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_net_amt))));
					} else {
						dr_cr_net_amt = nf.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_net_amt)));	
					}
				}
				if(!dr_cr_gross_amt.equals("")) {
					if(date_flag) {
						dr_cr_gross_amt = nf.format(Math.round(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_gross_amt))));
					} else {
						dr_cr_gross_amt = nf.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_gross_amt)));
					}
				}
				if(!drcrgrossamt.equals("")) {
					if(date_flag) {
						drcrgrossamt = nf.format(Math.round(Double.parseDouble(""+NumberFormat.getInstance().parse(drcrgrossamt))));
					} else {
						drcrgrossamt = nf.format(Double.parseDouble(""+NumberFormat.getInstance().parse(drcrgrossamt)));
					}
				}
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
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST "
					+ "ORDER BY CUSTOMER_CD";
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
	
	public void Customer_DTL_Rpt()
	{
		try
		{
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST "
					+ "ORDER BY CUSTOMER_NAME";
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
	
	
	public void Customer_NM()
	{
		try
		{
			queryString = "SELECT CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+customer_cd+"'";
			//System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{				
				cust_nm=rset.getString(2)==null?"":rset.getString(2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void clearVectorAndStringVariables()
	{
		//Sart Of Invoice Preperation Variables Initialization ...
		invoice_Customer_Cd.clear();
		invoice_Customer_Abbr.clear();
		invoice_Customer_Plant_Seq_No.clear();
		invoice_Customer_Plant_Nm.clear();
		invoice_HLPL_Seq_No.clear();
		invoice_FGSA_No.clear();
		invoice_FGSA_Rev_No.clear();
		invoice_SN_No.clear();
		invoice_SN_Rev_No.clear();
		invoice_Contract_Type.clear();
		invoice_SN_Start_Dt.clear();
		invoice_SN_End_Dt.clear();
		invoice_Bill_Period_Start_Dt.clear();
		invoice_Bill_Period_End_Dt.clear();
		invoice_Due_Dt.clear();
		//End Of Invoice Preperation Variables Initialization ...
		
		//Start Of Specific Invoice Details Variables Initialization ...
		invoice_Customer_Contact_Cd.clear();
		invoice_Customer_Contact_Nm.clear();
		total_Invoice_Qty = "";
		invoice_Sales_Rate = "";
		invoice_Previous_Available_Exchg_Date = "";
		invoice_Exchg_Rate_RBI_Ref = "";
		invoice_Exchg_Rate_TT_Selling = "";
		invoice_Exchg_Rate_TT_Buying = "";
		invoice_Exchg_Rate_Avg_TT_Buy_Sell = "";
		invoice_Previous_Exchg_Rate_RBI_Ref = "";
		invoice_Previous_Exchg_Rate_TT_Selling = "";
		invoice_Previous_Exchg_Rate_TT_Buying = "";
		invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell = "";
		invoice_Particular_Day_Exchg_Rate_RBI_Ref = "";
		invoice_Particular_Day_Exchg_Rate_TT_Selling = "";
		invoice_Particular_Day_Exchg_Rate_TT_Buying = "";
		invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell = "";
		//End Of Specific Invoice Details Variables Initialization ...
	}
	
	String period_start_dt = "";
	String period_end_dt = "";
	String mappid="";	//BK20160331
	public void fetchInvoicePreperationDetails(){
		methodName = "fetchInvoicePreperationDetails()";
		try 
		{
			String period_end_dt_FIXED = ""; //SB20110920
			/*if(billCycle.equals("2") || billCycle.equals("1")) //1st-Fortnightly or 2nd-Fortnightly Invoice ...
			{
				if(billCycle.equals("2")) //2nd-Fortnightly Invoice ...
				{
					period_start_dt = "16"+"/"+month+"/"+year;
				}
				else  //1st-Fortnightly Invoice ...
				{
					period_start_dt = "01"+"/"+month+"/"+year;
				}
				if(billCycle.equals("1"))  //1st-Fortnightly Invoice ...
				{
					period_end_dt = "15"+"/"+month+"/"+year;
				}
				else  //2nd-Fortnightly Invoice ...
				{
					queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
					//systemout.println("FMS7-INV-LIST:QRY1001:SELECT:DUAL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						period_end_dt = rset.getString(1);
					}
				}
			}*/
			/*else if(billCycle.equals("3")) //1st-Weekly Invoice ...
			{
				period_start_dt = "01"+"/"+month+"/"+year;
				period_end_dt = "07"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("4")) //2nd-Weekly Invoice ...
			{
				period_start_dt = "08"+"/"+month+"/"+year;
				period_end_dt = "14"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("5")) //3rd-Weekly Invoice ...
			{
				period_start_dt = "15"+"/"+month+"/"+year;
				period_end_dt = "21"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("6")) //4th-Weekly Invoice ...
			{
				period_start_dt = "22"+"/"+month+"/"+year;
				period_end_dt = "28"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("9")) //5th-Weekly Invoice ...
			{
				period_start_dt = "29"+"/"+month+"/"+year;
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
				////systemout.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					period_end_dt = rset.getString(1);
				}
			}
			else if(billCycle.equals("7"))  //Monthly Invoice ...
			{
				period_start_dt = "01"+"/"+month+"/"+year;
				
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					period_end_dt = rset.getString(1);
				}
			}*/
			period_start_dt = billCycle;
			period_end_dt = billCycle;
			
			String dtgst = period_start_dt.substring(6,10)+period_start_dt.substring(3,5)+period_start_dt.substring(0,2);
			int dt2 = Integer.parseInt(dtgst);
			if(dt2>=gst_eff_dt) {
				date_flag = true;
			}
			Vector temp_Customer_Cd = new Vector();
			Vector temp_Customer_Abbr = new Vector();
			Vector temp_FGSA_No = new Vector();
			Vector temp_FGSA_Rev_No = new Vector();
			Vector temp_SN_No = new Vector();
			Vector temp_SN_Rev_No = new Vector();
			Vector temp_Contract_Type = new Vector();
			Vector temp_SN_Start_Dt = new Vector();
			Vector temp_SN_End_Dt = new Vector();
			Vector temp_Bill_Period_Start_Dt = new Vector();
			Vector temp_Bill_Period_End_Dt = new Vector();
			Vector temp_Due_Dt = new Vector();
			Vector temp_Exchg_Rate_Calculation_Method = new Vector();
			Vector temp_apprv=new Vector();
			Vector temp_Exchg_Rate_Cd = new Vector();
			Vector temp_Tcq = new Vector();
			
			queryString = "SELECT A.sn_no,A.sn_rev_no,A.fgsa_no,A.fgsa_rev_no,A.customer_cd," +
			//SB20111206			  "TO_CHAR(A.start_dt,'DD/MM/YYYY'),TO_CHAR(A.end_dt,'DD/MM/YYYY'),NVL(tcq,'0') " +
						  "TO_CHAR(A.start_dt,'DD/MM/YYYY'),TO_CHAR(A.end_dt,'DD/MM/YYYY'),NVL(tcq,'0')+NVL(VARIATION_QTY,'0') AS T_tcq " +
						  "FROM DLNG_SN_MST A WHERE A.start_dt IS NOT NULL AND A.end_dt IS NOT NULL AND " +
						  "((A.start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY'))) AND " +
						  "A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE " +
						  "A.sn_no=B.sn_no AND A.fgsa_no=B.fgsa_no AND A.fgsa_rev_no=B.fgsa_rev_no AND " +
						  "A.customer_cd=B.customer_cd AND " +
						  "((B.start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(B.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(B.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')))) " +
						  "ORDER BY A.customer_cd,A.fgsa_no,A.fgsa_rev_no,A.sn_no";
			System.out.println("STEP-1:DLNG_SN_MST: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String customer_abbr = "";
				String bill_period_start_dt = "";
				String bill_period_end_dt = "";
				String due_dt = "";
				int due_days = 0;
				queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+rset.getString(5)+"";
				////systemout.println("Query To FindOut Customer Abbriviation = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					customer_abbr = rset3.getString(1)==null?"":rset3.getString(1);
				}
				queryString3 = "SELECT TO_DATE('"+rset.getString(6)+"','DD/MM/YYYY') - TO_DATE('"+period_start_dt+"','DD/MM/YYYY') FROM DUAL";
				//////System.out.println("Query To FindOut Days Difference Between Two Start Dates = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_start_dt = rset.getString(6);
					}
					else
					{
						bill_period_start_dt = period_start_dt;
					}
				}
				queryString3 = "SELECT TO_DATE('"+rset.getString(7)+"','DD/MM/YYYY') - TO_DATE('"+period_end_dt+"','DD/MM/YYYY') FROM DUAL";
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_end_dt = period_end_dt;
					}
					else
					{
						bill_period_end_dt = rset.getString(7);
					}
				}
//				if(billCycle.equals("1") || billCycle.equals("2"))
//				{
					queryString1 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
							" FROM DLNG_SN_BILLING_DTL " +
								   "WHERE cont_type='S' AND customer_cd="+rset.getString(5)+" AND " +
								   "fgsa_no="+rset.getString(3)+" AND fgsa_rev_no="+rset.getString(4)+" AND " +
								   "sn_no="+rset.getString(1)+" AND sn_rev_no="+rset.getString(2)+"";					
					System.out.println("STEP-1A:DLNG_SN_BILLING_DTL: "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("F"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							temp_Customer_Cd.add(rset.getString(5));
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(rset.getString(3));
							temp_FGSA_Rev_No.add(rset.getString(4));
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add(rset.getString(2));
							temp_Contract_Type.add("S");
							temp_SN_Start_Dt.add(rset.getString(6));
							temp_SN_End_Dt.add(rset.getString(7));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(8));
						}
					}
					else
					{
						queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
								" FROM FMS7_FGSA_BILLING_DTL " +
						   		" WHERE cont_type='F' AND customer_cd="+rset.getString(5)+" AND " +
						   		"fgsa_no="+rset.getString(3)+" AND fgsa_rev_no="+rset.getString(4)+"";			
					System.out.println("STEP-1B:FMS7_FGSA_BILLING_DTL:  "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							if(rset2.getString(1).equalsIgnoreCase("F"))
							{
								due_days = Integer.parseInt(rset2.getString(2));
								queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									temp_Due_Dt.add(rset3.getString(1));
								}
								else
								{
									temp_Due_Dt.add(bill_period_end_dt);
								}
								
								temp_Customer_Cd.add(rset.getString(5));
								temp_Customer_Abbr.add(customer_abbr);
								temp_FGSA_No.add(rset.getString(3));
								temp_FGSA_Rev_No.add(rset.getString(4));
								temp_SN_No.add(rset.getString(1));
								temp_SN_Rev_No.add(rset.getString(2));
								temp_Contract_Type.add("S");
								temp_SN_Start_Dt.add(rset.getString(6));
								temp_SN_End_Dt.add(rset.getString(7));
								temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
								temp_Bill_Period_End_Dt.add(bill_period_end_dt);
								temp_Exchg_Rate_Cd.add(rset2.getString(3));
								temp_Exchg_Rate_Calculation_Method.add(rset2.getString(4));
								temp_Tcq.add(rset.getString(8));
							}
						}
					}
//				}
			}
			for(int i=0;i<temp_Customer_Cd.size();i++)
			{
				queryString = "SELECT DISTINCT(plant_seq_no) FROM DLNG_DAILY_ALLOCATION_DTL " +
							  "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
							  "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
							  //"fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
							  "sn_no="+temp_SN_No.elementAt(i)+" AND " +
							  //"sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
							  "contract_type='S' AND " +
							  "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
							  "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') " +
							  "ORDER BY plant_seq_no";				
				System.out.println("STEP2: Plant Seq NO For SN LIST = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				//////System.out.println("rset = "+rset);
				int cn=0;
				while(rset.next())
				{
					cn++;
					
					String bill_start_dt1 = "";
					String bill_end_dt1 = "";
					String bill_start_dt2 = "";
					String bill_end_dt2 = "";
					String bill_end_dt3 = "";
					String bill_end_dt4 = "";
					String plant_no = (rset.getString(1)==null?"0":rset.getString(1));
					
					String tmp_bill_period_end_dt = ""+temp_Bill_Period_End_Dt.elementAt(i);
					period_end_dt_FIXED = tmp_bill_period_end_dt;  //SB20111206
					
					queryString3 = "SELECT TO_CHAR(tax_struct_dt,'DD/MM/YYYY') " +
							" FROM FMS7_CUSTOMER_TAX_STRUCT_DTL WHERE " +
								   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND plant_seq_no="+rset.getString(1)+" AND " +
								   "tax_struct_dt>TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
								   "tax_struct_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
					System.out.println("===Query To FindOut TAX Structure Change Dates In-Between Invoice Billing Period = "+queryString3);
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						rset2 = stmt2.executeQuery("SELECT TO_CHAR(TO_DATE('"+rset3.getString(1)+"','DD/MM/YYYY') - 1,'DD/MM/YYYY') PREVIOUS_DATE FROM DUAL");
				        if(rset2.next())
				        {
				        	bill_start_dt1 = ""+temp_Bill_Period_Start_Dt.elementAt(i);
				        	bill_end_dt1 = rset2.getString("PREVIOUS_DATE");
				        	bill_start_dt2 = rset3.getString(1);
				        	bill_end_dt2 = ""+temp_Bill_Period_End_Dt.elementAt(i);
				        }				        
					}
				
					if(bill_start_dt1.equals("") || bill_end_dt1.equals("") || bill_start_dt2.equals("") || bill_end_dt2.equals(""))
					{
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 26th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='S' AND " +
									   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";						
						////System.out.println("Query For Fetching Last Allocation Date = "+queryString1);
						//here 14
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}						
					//	//System.out.println("Available total_qty_mmbtu: "+total_qty_mmbtu);
						//system.out.println("SUM "+total_qty_mmbtu);//System.out.println("SUM "+Double.parseDouble(""+temp_Tcq.elementAt(i)));
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							////System.out.println("---in if total_qty_mmbtu---");
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='S' AND " +
										   "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";							
						//	//System.out.println("****** Query For Fetching Last Allocation Date = "+queryString1);
							//system.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001F:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt3.trim().equals(""))
								{
									//temp_Bill_Period_End_Dt.setElementAt(bill_end_dt3,i);
									tmp_bill_period_end_dt = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
											" FROM DLNG_SN_BILLING_DTL " +
												   "WHERE cont_type='S' AND " +
												   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
												   "sn_no="+temp_SN_No.elementAt(i)+" " +
												   "ORDER BY sn_rev_no DESC";
									
							//		//System.out.println("Query To FindOut Due Days from SN Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										////system.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						
						//Above Whole Logic Ends Here For Full TCQ Occupation Before SN Contract End Date ... 
					//////	period_end_dt_FIXED = tmp_bill_period_end_dt;  //SB20110920
					////	//system.out.println("FMS7-INV-LIST:*1: DLNG_SN_MST ******: "+period_end_dt_FIXED);
						
						queryString1 = "SELECT NVL(A.sn_ref_no,'') FROM DLNG_SN_MST A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" " +
									   "AND A.sn_no="+temp_SN_No.elementAt(i)+" " +
									   "AND A.flsa_no='"+temp_FGSA_No.elementAt(i)+"' " +
									   "AND A.sn_ref_no IS NOT NULL " +
									   "ORDER BY A.sn_rev_no DESC";
					//	//System.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_SN_Ref_No.add(rset1.getString(1));
						}
						else
						{
							invoice_SN_Ref_No.add("");
						}
			
				//		//System.out.println("---invoice_SN_Ref_No---"+invoice_SN_Ref_No);
						
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY'))";
						////system.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						
//******** SB:as per Samir Shah dt06Dec2011 For Max End Date for the Billing irrespective of Plant SeqNo ***********//
						queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
						   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						   //"plant_seq_no="+plant_no+" AND " +
						   "contract_type='S' AND " +
						   "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
			//			//System.out.println("@@@@@FMS7-SN-INV-LIST:LAST-DATE W/O Plant:QRY-IL1001AA:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							tmp_bill_period_end_dt = rset1.getString(1)==null?"":rset1.getString(1);
						}
////////////This is for Billing Clause: dt07Dec2011:REL#20111201: ////////////////
						String clauseFlag="";
						queryString1 = "SELECT NVL(FLAG,'Y') FROM DLNG_SN_BILLING_DTL " +
						   "WHERE cont_type='S' AND " +
						   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND sn_no="+temp_SN_No.elementAt(i)+" " +
						   "ORDER BY sn_rev_no ASC";			
						//system.out.println("SN-CLAUSE: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);						
						while(rset1.next())
						{
							clauseFlag = rset1.getString(1);
						}
						//system.out.println("VALUE-CLAUSE-SN: "+clauseFlag);
////////////The above is for Billing Clause: dt07Dec2011 ////////////////
						// ^^^^^^^^^^This option is introduced as per SN logic dt: 06Dec2011
						
			//			//system.out.println("FMS7-INV-LIST:******111******: "+tmp_bill_period_end_dt);
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+temp_Bill_Period_Start_Dt.elementAt(i));
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							if(clauseFlag.equals("B"))
								invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED);
							//if(clauseFlag.equals("T"))
							//	invoice_Bill_Period_End_Dt.add(""+tmp_bill_period_end_dt);
							else
								invoice_Bill_Period_End_Dt.add(""+tmp_bill_period_end_dt); 
						}
						else
						{
							invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED);
						}
												
						invoice_total_qty_mmbtu.add(""+total_qty_mmbtu);  //SB20111207
						invoice_temp_Tcq_mmbtu.add(""+temp_Tcq.elementAt(i)); //SB20111207
						//system.out.println("LAST DATE OF ALLOCATED QTY in PLANT: " +period_end_dt_FIXED); 
						//system.out.println("LAST DATE OF ALLOCATED QTY in SN:    " +tmp_bill_period_end_dt);
//SB20111206 invoice_Bill_Period_End_Dt.add(""+tmp_bill_period_end_dt); 

//SB20110920 invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); //SB20110920: as per Doc.No: dt20Sep2011
  
 
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
						System.out.println("invoice_Mapping_Id--6--"+invoice_Mapping_Id);
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
									   "checked_flag,checked_by,authorized_flag,authorized_by," +
									   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd,gross_amt_inr,offspec_rate " +
									   ", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),NEW_INV_SEQ_NO " +  //SB20160530
									   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   //"fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   //"sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
						 				" AND FLAG ='Y' "; //SB20160331"; //chkhere31
						//System.out.println("---queryString2-------"+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									}
									else
									{
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
									if(seq_no<10)
									{
										inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									} else if(seq_no<10000) {
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									} else {
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");		
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");						}						
					}
					else
					{
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='S' AND " +
									   "gas_dt<=TO_DATE('"+bill_end_dt1+"','DD/MM/YYYY')";
						
				//		//System.out.println("Query For Fetching Last Allocation Date == "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}

						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='S' AND " +
										   "gas_dt>=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+bill_end_dt1+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
							////System.out.println("Query For Fetching Last Allocation Date === "+queryString1);
							////System.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001F:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								if(!bill_end_dt3.trim().equals(""))
								{
									bill_end_dt1 = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM DLNG_SN_BILLING_DTL " +
												   "WHERE cont_type='S' AND " +
												   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
												   "sn_no="+temp_SN_No.elementAt(i)+" " +
												   "ORDER BY sn_rev_no DESC";
									
									////system.out.println("Query To FindOut Due Days from SN Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										////system.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						//Above Whole Logic Ends Here For Full TCQ Occupation Before SN Contract End Date ...
						
						queryString1 = "SELECT NVL(A.sn_ref_no,'') FROM DLNG_SN_MST A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" " +
									   "AND A.sn_no="+temp_SN_No.elementAt(i)+" " +
									   "AND A.flsa_no='"+temp_FGSA_No.elementAt(i)+"' " +
									   "AND A.sn_ref_no IS NOT NULL " +
									   "ORDER BY A.sn_rev_no DESC";
						////system.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_SN_Ref_No.add(rset1.getString(1));
						}
						else
						{
							invoice_SN_Ref_No.add("");
						}
			
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY'))";
						////system.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}						
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+bill_start_dt1);
						invoice_Bill_Period_End_Dt.add(""+bill_end_dt1);
//						SB20110920invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); //SB20110920: as per Doc.No: dt20Sep2011
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						//1st 14th here
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
//						//System.out.println("invoice_pre_aprv--7--"+invoice_pre_aprv);
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd,gross_amt_inr,offspec_rate " +
						   			 ", TO_CHAR(INVOICE_DT, 'DD/MM/YYYY'),NEW_INV_SEQ_NO " +  //SB20160530
									   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   //"fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   //"sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
										" AND FLAG ='Y' "; //SB20160331";	//HERE31
				//		//System.out.println("---14th here queryString2---"+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
					//		//System.out.println("inside if");
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									}
									else
									{
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
						
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						total_qty_mmbtu = 0;
						bill_end_dt4 = "";
						
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='S' AND " +
									   "gas_dt<=TO_DATE('"+bill_end_dt2+"','DD/MM/YYYY')";
						
						////system.out.println("Query For Fetching Last Allocation Date = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
				//			//System.out.println("here if");
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='S' AND " +
										   "gas_dt>=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+bill_end_dt2+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
							////system.out.println("Query For Fetching Last Allocation Date = "+queryString1);
							//system.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001E:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt4 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt4.trim().equals(""))
								{
									bill_end_dt2 = bill_end_dt4;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM DLNG_SN_BILLING_DTL " +
												   "WHERE cont_type='S' AND " +
												   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
												   "sn_no="+temp_SN_No.elementAt(i)+" " +
												   "ORDER BY sn_rev_no DESC";
									
									////system.out.println("Query To FindOut Due Days from SN Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt4+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										////system.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						
						//Above Whole Logic Ends Here For Full TCQ Occupation Before SN Contract End Date ...
						//system.out.println("@@@@@FMS");
						queryString1 = "SELECT NVL(A.sn_ref_no,'') FROM DLNG_SN_MST A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" " +
									   "AND A.sn_no="+temp_SN_No.elementAt(i)+" " +
									   "AND A.flsa_no='"+temp_FGSA_No.elementAt(i)+"' " +
									   "AND A.sn_ref_no IS NOT NULL " +
									   "ORDER BY A.sn_rev_no DESC";
						////system.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_SN_Ref_No.add(rset1.getString(1));
						}
						else
						{
							invoice_SN_Ref_No.add("");
						}
			
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY'))";
						//system.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+bill_start_dt2);
						invoice_Bill_Period_End_Dt.add(""+bill_end_dt2);
//						SB20110920 invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); //SB20110920: as per Doc.No: dt20Sep2011
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
//						//System.out.println("invoice_pre_aprv--8--"+invoice_pre_aprv);
						
						//15th here
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd,gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),NEW_INV_SEQ_NO " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   //"fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   //"sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
										" AND FLAG ='Y' "; //SB20160331";  //HERE31 
				//		//System.out.println("---15th here queryString2---"+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
									if(seq_no<10)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									}
									else
									{
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
					}
				}
			}
			//End Of Fetching SN Related Records ...
//	//System.out.println("-------- END OF SN -------------");		
		////	//system.out.println("temp_Customer_Cd.size() = "+temp_Customer_Cd.size()+",  invoice_Customer_Cd.size() = "+invoice_Customer_Cd.size());
	//system.out.println("-------- START OF LOA -------------");					
//*********** Start Of Fetching LOA Related Records ...
			temp_Customer_Cd.clear();
			temp_Customer_Abbr.clear();
			temp_FGSA_No.clear();
			temp_FGSA_Rev_No.clear();
			temp_SN_No.clear();
			temp_SN_Rev_No.clear();
			temp_Contract_Type.clear();
			temp_SN_Start_Dt.clear();
			temp_SN_End_Dt.clear();
			temp_Bill_Period_Start_Dt.clear();
			temp_Bill_Period_End_Dt.clear();
			temp_Due_Dt.clear();
			temp_Exchg_Rate_Cd.clear();
			temp_Exchg_Rate_Calculation_Method.clear();
			temp_Tcq.clear();
			
			queryString = "SELECT A.loa_no,A.loa_rev_no,A.tender_no,A.customer_cd," +
			//SB20111206			  "TO_CHAR(A.start_dt,'DD/MM/YYYY'),TO_CHAR(A.end_dt,'DD/MM/YYYY'),NVL(tcq,'0') " +
						  "TO_CHAR(A.start_dt,'DD/MM/YYYY'),TO_CHAR(A.end_dt,'DD/MM/YYYY'),NVL(tcq,'0')+NVL(VARIATION_QTY,'0') AS T_tcq " +
						  "FROM DLNG_LOA_MST A WHERE A.start_dt IS NOT NULL AND A.end_dt IS NOT NULL AND " +
						  "((A.start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY'))) AND " +
						  "A.loa_rev_no=(SELECT MAX(B.loa_rev_no) FROM DLNG_LOA_MST B WHERE " +
						  "A.loa_no=B.loa_no AND A.tender_no=B.tender_no AND " +
						  "A.customer_cd=B.customer_cd AND " +
						  "((B.start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(B.end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(B.start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "B.end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')))) " +
						  "ORDER BY A.customer_cd,A.tender_no,A.loa_no";
			////system.out.println("Query For Fetching Correct LOA LIST For Invoice Generation Process = "+queryString);
			//rset = stmt.executeQuery(queryString); HIREN_20200227 to get SN contracts only
			while(rset.next())
			{
				String customer_abbr = "";
				String bill_period_start_dt = "";
				String bill_period_end_dt = "";
				String due_dt = "";
				int due_days = 0;
								
				queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+rset.getString(4)+"";
				////system.out.println("Query To FindOut Customer Abbriviation FOr LOA = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					customer_abbr = rset3.getString(1)==null?"":rset3.getString(1);
				}
				
				queryString3 = "SELECT TO_DATE('"+rset.getString(5)+"','DD/MM/YYYY') - TO_DATE('"+period_start_dt+"','DD/MM/YYYY') FROM DUAL";
			//	//System.out.println("Query To FindOut Days Difference Between Two Start Dates FOR LOA = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_start_dt = rset.getString(5);
					}
					else
					{
						bill_period_start_dt = period_start_dt;
					}
				}
				
				queryString3 = "SELECT TO_DATE('"+rset.getString(6)+"','DD/MM/YYYY') - TO_DATE('"+period_end_dt+"','DD/MM/YYYY') FROM DUAL";
			//	//System.out.println("Query To FindOut Days Difference Between Two End Dates For LOA = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_end_dt = period_end_dt;
					}
					else
					{
						bill_period_end_dt = rset.getString(6);
					}
				}
				
				if(billCycle.equals("1") || billCycle.equals("2"))
				{
					queryString1 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM DLNG_SN_BILLING_DTL " +
								   "WHERE cont_type='L' AND customer_cd="+rset.getString(4)+" AND " +
								   "fgsa_no="+rset.getString(3)+" AND fgsa_rev_no=0 AND " +
								   "sn_no="+rset.getString(1)+" AND sn_rev_no="+rset.getString(2)+"";
					////System.out.println("Query To FindOut LOA Bill Details Based On Fortnightly Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("F"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Customer_Cd.add(rset.getString(4));
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(rset.getString(3));
							temp_FGSA_Rev_No.add("0");
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add(rset.getString(2));
							temp_Contract_Type.add("L");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}
					else
					{
						queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM FMS7_FGSA_BILLING_DTL " +
						   			   "WHERE cont_type='T' AND customer_cd="+rset.getString(4)+" AND " +
						   			   "fgsa_no="+rset.getString(3)+" AND fgsa_rev_no=0";
			
						////System.out.println("Query To FindOut Tender Bill Details Based On Fortnightly Or NOT = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
			
						if(rset2.next())
						{
							if(rset2.getString(1).equalsIgnoreCase("F"))
							{
								due_days = Integer.parseInt(rset2.getString(2));
								
								queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
								////System.out.println("Query To FindOut Due Dates = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									temp_Due_Dt.add(rset3.getString(1));
								}
								else
								{
									temp_Due_Dt.add(bill_period_end_dt);
								}
								
								temp_Customer_Cd.add(rset.getString(4));
								temp_Customer_Abbr.add(customer_abbr);
								temp_FGSA_No.add(rset.getString(3));
								temp_FGSA_Rev_No.add("0");
								temp_SN_No.add(rset.getString(1));
								temp_SN_Rev_No.add(rset.getString(2));
								temp_Contract_Type.add("L");
								temp_SN_Start_Dt.add(rset.getString(5));
								temp_SN_End_Dt.add(rset.getString(6));
								temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
								temp_Bill_Period_End_Dt.add(bill_period_end_dt);
								temp_Exchg_Rate_Cd.add(rset2.getString(3));
								temp_Exchg_Rate_Calculation_Method.add(rset2.getString(4));
								temp_Tcq.add(rset.getString(7));
							}
						}
					}
				}
				else if(billCycle.equals("3") || billCycle.equals("4") || billCycle.equals("5") || billCycle.equals("6") || billCycle.equals("9"))
				{
					queryString1 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM DLNG_SN_BILLING_DTL " +
								   "WHERE cont_type='L' AND customer_cd="+rset.getString(4)+" AND " +
								   "fgsa_no="+rset.getString(3)+" AND fgsa_rev_no=0 AND " +
								   "sn_no="+rset.getString(1)+" AND sn_rev_no="+rset.getString(2)+"";
					
					////System.out.println("Query To FindOut LOA Bill Details Based On Fortnightly Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("W"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Customer_Cd.add(rset.getString(4));
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(rset.getString(3));
							temp_FGSA_Rev_No.add("0");
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add(rset.getString(2));
							temp_Contract_Type.add("L");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}
					else
					{
						queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM FMS7_FGSA_BILLING_DTL " +
						   			   "WHERE cont_type='T' AND customer_cd="+rset.getString(4)+" AND " +
						   			   "fgsa_no="+rset.getString(3)+" AND fgsa_rev_no=0";
			
						////System.out.println("Query To FindOut Tender Bill Details Based On Fortnightly Or NOT = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
			
						if(rset2.next())
						{
							if(rset2.getString(1).equalsIgnoreCase("W"))
							{
								due_days = Integer.parseInt(rset2.getString(2));
								
								queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
								////System.out.println("Query To FindOut Due Dates = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									temp_Due_Dt.add(rset3.getString(1));
								}
								else
								{
									temp_Due_Dt.add(bill_period_end_dt);
								}
								
								temp_Customer_Cd.add(rset.getString(4));
								temp_Customer_Abbr.add(customer_abbr);
								temp_FGSA_No.add(rset.getString(3));
								temp_FGSA_Rev_No.add("0");
								temp_SN_No.add(rset.getString(1));
								temp_SN_Rev_No.add(rset.getString(2));
								temp_Contract_Type.add("L");
								temp_SN_Start_Dt.add(rset.getString(5));
								temp_SN_End_Dt.add(rset.getString(6));
								temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
								temp_Bill_Period_End_Dt.add(bill_period_end_dt);
								temp_Exchg_Rate_Cd.add(rset2.getString(3));
								temp_Exchg_Rate_Calculation_Method.add(rset2.getString(4));
								temp_Tcq.add(rset.getString(7));
							}
						}
					}
				}
				else if(billCycle.equals("7"))
				{
					queryString1 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM DLNG_SN_BILLING_DTL " +
								   "WHERE cont_type='L' AND customer_cd="+rset.getString(4)+" AND " +
								   "fgsa_no="+rset.getString(3)+" AND fgsa_rev_no=0 AND " +
								   "sn_no="+rset.getString(1)+" AND sn_rev_no="+rset.getString(2)+"";
					
					////System.out.println("Query To FindOut LOA Bill Details Based On Fortnightly Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("M"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Customer_Cd.add(rset.getString(4));
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(rset.getString(3));
							temp_FGSA_Rev_No.add("0");
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add(rset.getString(2));
							temp_Contract_Type.add("L");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
							
						}
					}
					else
					{
						queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') FROM FMS7_FGSA_BILLING_DTL " +
						   			   "WHERE cont_type='T' AND customer_cd="+rset.getString(4)+" AND " +
						   			   "fgsa_no="+rset.getString(3)+" AND fgsa_rev_no=0";
						////System.out.println("Query To FindOut Tender Bill Details Based On Fortnightly Or NOT = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
			
						if(rset2.next())
						{
							if(rset2.getString(1).equalsIgnoreCase("M"))
							{
								due_days = Integer.parseInt(rset2.getString(2));
								
								queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
								////System.out.println("Query To FindOut Due Dates = "+queryString3);
								rset3 = stmt3.executeQuery(queryString3);
								if(rset3.next())
								{
									temp_Due_Dt.add(rset3.getString(1));
								}
								else
								{
									temp_Due_Dt.add(bill_period_end_dt);
								}
								
								temp_Customer_Cd.add(rset.getString(4));
								temp_Customer_Abbr.add(customer_abbr);
								temp_FGSA_No.add(rset.getString(3));
								temp_FGSA_Rev_No.add("0");
								temp_SN_No.add(rset.getString(1));
								temp_SN_Rev_No.add(rset.getString(2));
								temp_Contract_Type.add("L");
								temp_SN_Start_Dt.add(rset.getString(5));
								temp_SN_End_Dt.add(rset.getString(6));
								temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
								temp_Bill_Period_End_Dt.add(bill_period_end_dt);
								temp_Exchg_Rate_Cd.add(rset2.getString(3));
								temp_Exchg_Rate_Calculation_Method.add(rset2.getString(4));
								temp_Tcq.add(rset.getString(7));
								
							}
						}
					}
				}
			}
			
			//SN end_dt till here ie 14,15,14,15
			
			for(int i=0;i<temp_Customer_Cd.size();i++)
			{
				queryString = "SELECT DISTINCT(plant_seq_no) FROM DLNG_DAILY_ALLOCATION_DTL " +
							  "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
							  "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
							  "fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
							  "sn_no="+temp_SN_No.elementAt(i)+" AND " +
							  //"sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
							  "contract_type='L' AND " +
							  "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
							  "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') " +
							  "ORDER BY plant_seq_no";
				
				////System.out.println("Query For Fetching Correct Plant Seq NO For LOA LIST = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				////System.out.println("rset = "+rset);
				
				while(rset.next())
				{
					String bill_start_dt1 = "";
					String bill_end_dt1 = "";
					String bill_start_dt2 = "";
					String bill_end_dt2 = "";
					String bill_end_dt3 = "";
					String bill_end_dt4 = "";
					String plant_no = (rset.getString(1)==null?"0":rset.getString(1));
					
					String tmp_bill_period_end_dt = ""+temp_Bill_Period_End_Dt.elementAt(i);
					period_end_dt_FIXED = tmp_bill_period_end_dt;  //SB20110930
	//				//system.out.println("FMS7-INV-LIST:*2: DLNG_LOA_MST ******: "+period_end_dt_FIXED);
					
					queryString3 = "SELECT TO_CHAR(tax_struct_dt,'DD/MM/YYYY') FROM FMS7_CUSTOMER_TAX_STRUCT_DTL WHERE " +
								   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND plant_seq_no="+rset.getString(1)+" AND " +
								   "tax_struct_dt>TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
								   "tax_struct_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
					////System.out.println("Query To FindOut TAX Structure Change Dates In-Between Invoice Billing Period = "+queryString3);
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						rset2 = stmt2.executeQuery("SELECT TO_CHAR(TO_DATE('"+rset3.getString(1)+"','DD/MM/YYYY') - 1,'DD/MM/YYYY') PREVIOUS_DATE FROM DUAL");
				        if(rset2.next())
				        {
				        	bill_start_dt1 = ""+temp_Bill_Period_Start_Dt.elementAt(i);
				        	bill_end_dt1 = rset2.getString("PREVIOUS_DATE");
				        	bill_start_dt2 = rset3.getString(1);
				        	bill_end_dt2 = ""+temp_Bill_Period_End_Dt.elementAt(i);
				        }				        
					}
					
					if(bill_start_dt1.equals("") || bill_end_dt1.equals("") || bill_start_dt2.equals("") || bill_end_dt2.equals(""))
					{
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='L' AND " +
									   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
						
						////System.out.println("Query For Fetching Last Allocation Date = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='L' AND " +
										   "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
							////System.out.println("Query For Fetching Last Allocation Date = "+queryString1);
	/////						//System.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001D:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt3.trim().equals(""))
								{
									//temp_Bill_Period_End_Dt.setElementAt(bill_end_dt3,i);
									tmp_bill_period_end_dt = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM DLNG_SN_BILLING_DTL " +
												   "WHERE cont_type='L' AND " +
												   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
												   "sn_no="+temp_SN_No.elementAt(i)+" " +
												   "ORDER BY sn_rev_no DESC";
									
									////System.out.println("Query To FindOut Due Days from LOA Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										////System.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						//Above Whole Logic Ends Here For Full TCQ Occupation Before LOA Contract End Date ...
						
						
						
						queryString1 = "SELECT NVL(A.loa_ref_no,'') FROM DLNG_LOA_MST A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" " +
									   "AND A.loa_no="+temp_SN_No.elementAt(i)+" " +
									   "AND A.tender_no='"+temp_FGSA_No.elementAt(i)+"' " +
									   "AND A.loa_ref_no IS NOT NULL " +
									   "ORDER BY A.loa_rev_no DESC";
						////System.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_SN_Ref_No.add(rset1.getString(1));
						}
						else
						{
							invoice_SN_Ref_No.add("");
						}
			
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY'))";
						////System.out.println("Customer Plant Name Fetch Query For LOA = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}

//******** SB:as per Samir Shah dt06Dec2011 For Max End Date for the Billing irrespective of Plant SeqNo ***********//
						queryString1 = "SELECT MAX(TO_CHAR(gas_dt,'dd/mm/yyyy')) FROM DLNG_DAILY_ALLOCATION_DTL " +
						   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						   //"plant_seq_no="+plant_no+" AND " +
						   "contract_type='L' AND " +
						   "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
						//system.out.println("@@@@@FMS7-LOA-INV-LIST:LAST-DATE W/O Plant:QRY-IL1001AA:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							tmp_bill_period_end_dt = rset1.getString(1)==null?"":rset1.getString(1);
						}
////////////			This is for Billing Clause: dt07Dec2011:REL#20111201: ////////////////
						String clauseFlag="";
						queryString1 = "SELECT NVL(FLAG,'Y') FROM FMS7_FGSA_BILLING_DTL " +
						   "WHERE cont_type='L' AND " +
						   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						   "fgsa_no="+temp_FGSA_No.elementAt(i)+"  " +
						   "ORDER BY fgsa_rev_no ASC";			
						//system.out.println("SN-CLAUSE: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);						
						while(rset1.next())
						{
							clauseFlag = rset1.getString(1);
						}
						//system.out.println("VALUE-CLAUSE-LOA: "+clauseFlag);
////////////The above is for Billing Clause: dt07Dec2011 ////////////////
// ^^^^^^^^^^This option is introduced as per LOA logic dt: 06Dec2011
						
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+temp_Bill_Period_Start_Dt.elementAt(i));
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							if(clauseFlag.equals("B"))
								invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED);
							//if(clauseFlag.equals("T"))
							//	invoice_Bill_Period_End_Dt.add(""+tmp_bill_period_end_dt);
							else						
								invoice_Bill_Period_End_Dt.add(""+tmp_bill_period_end_dt);
						}
						else
						{
							invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); 
						}
						
						invoice_total_qty_mmbtu.add(""+total_qty_mmbtu);  //SB20111207
						invoice_temp_Tcq_mmbtu.add(""+temp_Tcq.elementAt(i)); //SB20111207
			//SB20111206			invoice_Bill_Period_End_Dt.add(""+tmp_bill_period_end_dt);
						//system.out.println("LOA-LAST DATE OF ALLOCATED QTY in PLANT: " +period_end_dt_FIXED); 
						//system.out.println("LOA-LAST DATE OF ALLOCATED QTY in LOA:   " +tmp_bill_period_end_dt);
//SB20111206	invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); //SB20110920: as per Doc.No: dt20Sep2011
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
//						//System.out.println("invoice_pre_aprv--9--"+invoice_pre_aprv);
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd,gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),NEW_INV_SEQ_NO " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   //"fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   //"sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+""+
						  			 " AND FLAG ='Y' "; //SB20160331"; //HERE31
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									}
									else
									{
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
					}
					else
					{
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='L' AND " +
									   "gas_dt<=TO_DATE('"+bill_end_dt1+"','DD/MM/YYYY')";
						////System.out.println("---------Query For Fetching Last Allocation Date = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='L' AND " +
										   "gas_dt>=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+bill_end_dt1+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							////System.out.println("Query For Fetching Last Allocation Date 5217= "+queryString1);
	////						//System.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001C:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt3.trim().equals(""))
								{
									bill_end_dt1 = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM DLNG_SN_BILLING_DTL " +
												   "WHERE cont_type='L' AND " +
												   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
												   "sn_no="+temp_SN_No.elementAt(i)+" " +
												   "ORDER BY sn_rev_no DESC";
									
									////System.out.println("Query To FindOut Due Days from LOA Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										////System.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						//Above Whole Logic Ends Here For Full TCQ Occupation Before LOA Contract End Date ...
						
						queryString1 = "SELECT NVL(A.loa_ref_no,'') FROM DLNG_LOA_MST A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" " +
									   "AND A.loa_no="+temp_SN_No.elementAt(i)+" " +
									   "AND A.tender_no='"+temp_FGSA_No.elementAt(i)+"' " +
									   "AND A.loa_ref_no IS NOT NULL " +
									   "ORDER BY A.loa_rev_no DESC";
						////System.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_SN_Ref_No.add(rset1.getString(1));
						}
						else
						{
							invoice_SN_Ref_No.add("");
						}
			
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY'))";
						////System.out.println("Customer Plant Name Fetch Query For LOA = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+bill_start_dt1);
						//change here 14th
						invoice_Bill_Period_End_Dt.add(""+bill_end_dt1); //BK20151123
						//LOA 14th here
						
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
						//System.out.println("invoice_pre_aprv--9--"+invoice_pre_aprv);
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd,gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),NEW_INV_SEQ_NO " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   //"fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   //"sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
										" AND FLAG ='Y' "; //SB20160331"; //HERE31
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									}
									else
									{
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
			
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						total_qty_mmbtu = 0;
						bill_end_dt4 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='L' AND " +
									   "gas_dt<=TO_DATE('"+bill_end_dt2+"','DD/MM/YYYY')";
						
						////System.out.println("Query For Fetching Last Allocation Date = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='L' AND " +
										   "gas_dt>=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+bill_end_dt2+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
						//	//System.out.println("Query For Fetching Last Allocation Date = "+queryString1);
		////					//System.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001B:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt4 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt4.trim().equals(""))
								{
									bill_end_dt2 = bill_end_dt4;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM DLNG_SN_BILLING_DTL " +
												   "WHERE cont_type='L' AND " +
												   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
												   "sn_no="+temp_SN_No.elementAt(i)+" " +
												   "ORDER BY sn_rev_no DESC";
									
									////System.out.println("Query To FindOut Due Days from LOA Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt4+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										////System.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						
						//Above Whole Logic Ends Here For Full TCQ Occupation Before LOA Contract End Date ...
						
						queryString1 = "SELECT NVL(A.loa_ref_no,'') FROM DLNG_LOA_MST A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" " +
									   "AND A.loa_no="+temp_SN_No.elementAt(i)+" " +
									   "AND A.tender_no='"+temp_FGSA_No.elementAt(i)+"' " +
									   "AND A.loa_ref_no IS NOT NULL " +
									   "ORDER BY A.loa_rev_no DESC";
						////System.out.println("Customer Plant Name Fetch Query = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_SN_Ref_No.add(rset1.getString(1));
						}
						else
						{
							invoice_SN_Ref_No.add("");
						}
			
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY'))";
						////System.out.println("Customer Plant Name Fetch Query For LOA = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+bill_start_dt2);
						invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED);
						//change here 15th
						//invoice_Bill_Period_End_Dt.add(""+bill_end_dt2);	//BK20151123
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
						
//						//System.out.println("invoice_pre_aprv--10--"+invoice_pre_aprv);
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd,gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'), NEW_INV_SEQ_NO " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   //"fgsa_rev_no="+temp_FGSA_Rev_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   //"sn_rev_no="+temp_SN_Rev_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
										" AND FLAG ='Y' "; //SB20160331"; //HERE31
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									}
									else
									{
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
					}
				}
			}
			
			//End Of Fetching LOA Related Records ...
//			//System.out.println("-------- END OF LOA -------------");				
			////system.out.println("temp_Customer_Cd.size() = "+temp_Customer_Cd.size()+",  invoice_Customer_Cd.size() = "+invoice_Customer_Cd.size());
			//system.out.println("-------- START OF RE-GAS -------------");			
			//********Start Of Fetching RE-GAS Related Records ...
			temp_Customer_Cd.clear();
			temp_Customer_Abbr.clear();
			temp_FGSA_No.clear();
			temp_FGSA_Rev_No.clear();
			temp_SN_No.clear();
			temp_SN_Rev_No.clear();
			temp_Contract_Type.clear();
			temp_SN_Start_Dt.clear();
			temp_SN_End_Dt.clear();
			temp_Bill_Period_Start_Dt.clear();
			temp_Bill_Period_End_Dt.clear();
			temp_Due_Dt.clear();
			temp_Exchg_Rate_Cd.clear();
			temp_Exchg_Rate_Calculation_Method.clear();
			temp_Tcq.clear();
			
			queryString = "SELECT A.cargo_seq_no,A.re_gas_rev_no,A.re_gas_no,A.customer_cd," +
						  "TO_CHAR(A.contract_start_dt,'DD/MM/YYYY'),TO_CHAR(A.contract_end_dt,'DD/MM/YYYY')," +
						  "NVL(A.qty_to_be_supply,'0') " +
						  "FROM FMS7_RE_GAS_CARGO_DTL A WHERE A.contract_start_dt IS NOT NULL AND " +
						  "A.contract_end_dt IS NOT NULL AND " +
						  "((A.contract_start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.contract_end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.contract_start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY'))) " +
						  "ORDER BY A.customer_cd,A.re_gas_no,A.cargo_seq_no";
	//System.out.println("RE-GAS:QRY-R1001:SELECT:FMS7_RE_GAS_CARGO_DTL: "+queryString);
			//rset = stmt.executeQuery(queryString); HIREN_20200227 to get SN contracts only
			while(rset.next())
			{
				String customer_abbr = "";
				String bill_period_start_dt = "";
				String bill_period_end_dt = "";
				String due_dt = "";
				int due_days = 0;
								
				queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+rset.getString(4)+"";
			//	System.out.println("Query To FindOut Customer Abbriviation For RE-GAS = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					customer_abbr = rset3.getString(1)==null?"":rset3.getString(1);
				}
				
				queryString3 = "SELECT TO_DATE('"+rset.getString(5)+"','DD/MM/YYYY') - TO_DATE('"+period_start_dt+"','DD/MM/YYYY') FROM DUAL";
				////system.out.println("Query To FindOut Days Difference Between Two Start Dates For RE-GAS = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_start_dt = rset.getString(5);
					}
					else
					{
						bill_period_start_dt = period_start_dt;
					}
				}
				
				queryString3 = "SELECT TO_DATE('"+rset.getString(6)+"','DD/MM/YYYY') - TO_DATE('"+period_end_dt+"','DD/MM/YYYY') FROM DUAL";
				////System.out.println("Query To FindOut Days Difference Between Two End Dates For RE-GAS = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_end_dt = period_end_dt;
					}
					else
					{
						bill_period_end_dt = rset.getString(6);
					}
				}
				
				if(billCycle.equals("1") || billCycle.equals("2"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS7_RE_GAS_BILLING_DTL A " +
								   "WHERE A.customer_cd="+rset.getString(4)+" AND " +
								   "A.re_gas_no="+rset.getString(3)+" AND " +
								   "A.re_gas_rev_no="+rset.getString(2)+"";
					
					////System.out.println("Query To FindOut RE-GAS Bill Details Based On Fortnightly Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("F"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Customer_Cd.add(rset.getString(4));
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(rset.getString(3));
							temp_FGSA_Rev_No.add(rset.getString(2));
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("R");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}
				}
				else if(billCycle.equals("3") || billCycle.equals("4") || billCycle.equals("5") || billCycle.equals("6") || billCycle.equals("9"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS7_RE_GAS_BILLING_DTL A " +
								   "WHERE A.customer_cd="+rset.getString(4)+" AND " +
								   "A.re_gas_no="+rset.getString(3)+" AND " +
								   "A.re_gas_rev_no="+rset.getString(2)+"";
					
					////System.out.println("Query To FindOut RE-GAS Bill Details Based On Weekly Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("W"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Customer_Cd.add(rset.getString(4));
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(rset.getString(3));
							temp_FGSA_Rev_No.add(rset.getString(2));
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("R");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}					
				}
				else if(billCycle.equals("7"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS7_RE_GAS_BILLING_DTL A " +
								   "WHERE A.customer_cd="+rset.getString(4)+" AND " +
								   "A.re_gas_no="+rset.getString(3)+" AND " +
								   "A.re_gas_rev_no="+rset.getString(2)+"";
					
					////System.out.println("Query To FindOut RE-GAS Bill Details Based On Monthly Basis Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("M"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Customer_Cd.add(rset.getString(4));
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(rset.getString(3));
							temp_FGSA_Rev_No.add(rset.getString(2));
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("R");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}					
				}
			}
			
			for(int i=0;i<temp_Customer_Cd.size();i++)
			{
				queryString = "SELECT DISTINCT(plant_seq_no) FROM DLNG_DAILY_ALLOCATION_DTL " +
							  "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
							  "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
							  "sn_no="+temp_SN_No.elementAt(i)+" AND " +
							  "contract_type='R' AND " +
							  "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
							  "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') " +
							  "ORDER BY plant_seq_no";
			//	System.out.println("SAMIK(A) --> Query For Fetching Correct Plant Seq NO For RE-GAS LIST = "+queryString);
				
				rset = stmt.executeQuery(queryString);
							
				while(rset.next())
				{
					String bill_start_dt1 = "";
					String bill_end_dt1 = "";
					String bill_start_dt2 = "";
					String bill_end_dt2 = "";
					String bill_end_dt3 = "";
					String bill_end_dt4 = "";
					String plant_no = (rset.getString(1)==null?"0":rset.getString(1));
					
					String tmp_bill_period_end_dt = ""+temp_Bill_Period_End_Dt.elementAt(i);
					period_end_dt_FIXED = tmp_bill_period_end_dt;  //SB20110920
	////				//System.out.println("FMS7-INV-LIST:******3: FMS7_RE_GAS_CARGO_DTL ******: "+period_end_dt_FIXED);
					
					queryString3 = "SELECT TO_CHAR(tax_struct_dt,'DD/MM/YYYY') FROM FMS7_CUSTOMER_SERVICE_TAX_DTL WHERE " +
								   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND plant_seq_no="+rset.getString(1)+" AND " +
								   "tax_struct_dt>TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
								   "tax_struct_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
					////System.out.println("Query To FindOut SERVICE TAX Structure Change Dates In-Between Invoice Billing Period For RE-GAS = "+queryString3);
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						rset2 = stmt2.executeQuery("SELECT TO_CHAR(TO_DATE('"+rset3.getString(1)+"','DD/MM/YYYY') - 1,'DD/MM/YYYY') PREVIOUS_DATE FROM DUAL");
				        if(rset2.next())
				        {
				        	bill_start_dt1 = ""+temp_Bill_Period_Start_Dt.elementAt(i);
				        	bill_end_dt1 = rset2.getString("PREVIOUS_DATE");
				        	bill_start_dt2 = rset3.getString(1);
				        	bill_end_dt2 = ""+temp_Bill_Period_End_Dt.elementAt(i);
				        }				        
					}
					
					if(bill_start_dt1.equals("") || bill_end_dt1.equals("") || bill_start_dt2.equals("") || bill_end_dt2.equals(""))
					{
						invoice_SN_Ref_No.add("");
						
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='R' AND " +
									   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
						
						////System.out.println("SAMIK(B) --> Query For Fetching Last Allocation Date = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='R' AND " +
										   "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
							////System.out.println("INV-LIST:RE-Gas:GAS-Allocation-Date: "+queryString1);
	////						//System.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001A:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt3.trim().equals(""))
								{
									//temp_Bill_Period_End_Dt.setElementAt(bill_end_dt3,i);
									tmp_bill_period_end_dt = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM FMS7_RE_GAS_BILLING_DTL " +
												   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "re_gas_no="+temp_FGSA_No.elementAt(i)+" " +
												   "ORDER BY re_gas_rev_no DESC";
									
		////							//System.out.println("Query To FindOut Due Days from RE GAS Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
		////								//System.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						//Above Whole Logic Ends Here For Full TCQ Occupation Before RE-Gas Contract End Date ...
						
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY'))";
	////					//System.out.println("Customer Plant Name Fetch Query For RE-GAS = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						// ******** SB:as per Samir Shah dt19Sep2011 For Max End Date for the Billing irrespective of Plant SeqNo ***********//
						queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
						   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						   //"plant_seq_no="+plant_no+" AND " +
						   "contract_type='R' AND " +
						   "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
	////					//System.out.println("FMS7-REG-INV-LIST:LAST-DATE W/O Plant:QRY-IL1001AA:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							tmp_bill_period_end_dt = rset1.getString(1)==null?"":rset1.getString(1);
						}
						// ^^^^^^^^^^This option is removed as per Rohit dt: 20Sep2011
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+temp_Bill_Period_Start_Dt.elementAt(i));
						invoice_total_qty_mmbtu.add(""+total_qty_mmbtu);  //SB20111207
						invoice_temp_Tcq_mmbtu.add(""+temp_Tcq.elementAt(i)); //SB20111207
						 //SB20110920invoice_Bill_Period_End_Dt.add(""+tmp_bill_period_end_dt);
						invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); //SB20110920: as per Doc.No: dt20Sep2011
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
//						//System.out.println("invoice_pre_aprv--11--"+invoice_pre_aprv);
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd," +
						   			   "gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),new_inv_seq_no " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
										" AND FLAG ='Y' "; //SB20160331";HERE31
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<10000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									} else {
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
					}
					else
					{
						invoice_SN_Ref_No.add("");
						
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='R' AND " +
									   "gas_dt<=TO_DATE('"+bill_end_dt1+"','DD/MM/YYYY')";
						
		////				//System.out.println("SAMIK(B-1) --> Query For Fetching Last Allocation Date = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='R' AND " +
										   "gas_dt>=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+bill_end_dt1+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
		////					//System.out.println("SAMIK(C-1) --> Query For Fetching Last Allocation Date = "+queryString1);
							
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt3.trim().equals(""))
								{
									bill_end_dt1 = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM FMS7_RE_GAS_BILLING_DTL " +
												   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "re_gas_no="+temp_FGSA_No.elementAt(i)+" " +
												   "ORDER BY re_gas_rev_no DESC";
									
		////							//System.out.println("Query To FindOut Due Days from RE GAS Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
		////								//System.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}

						//Above Whole Logic Ends Here For Full TCQ Occupation Before SN Contract End Date ...
						
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY'))";
	////				//System.out.println("Customer Plant Name Fetch Query For RE-GAS = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+bill_start_dt1);
						invoice_Bill_Period_End_Dt.add(""+bill_end_dt1);
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
//						//System.out.println("invoice_pre_aprv--12--"+invoice_pre_aprv);
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd," +
						   			   "gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),new_inv_seq_no " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
										" AND FLAG ='Y' "; //SB20160331";
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<10000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									} else {
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
			
						invoice_SN_Ref_No.add("");
						
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						total_qty_mmbtu = 0;
						bill_end_dt4 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='R' AND " +
									   "gas_dt<=TO_DATE('"+bill_end_dt2+"','DD/MM/YYYY')";
						
	////					//System.out.println("Query For Fetching Last Allocation Date = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='R' AND " +
										   "gas_dt>=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+bill_end_dt2+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
	////						//System.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt4 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt4.trim().equals(""))
								{
									bill_end_dt2 = bill_end_dt4;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM FMS7_RE_GAS_BILLING_DTL " +
												   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
												   "re_gas_no="+temp_FGSA_No.elementAt(i)+" " +
												   "ORDER BY re_gas_rev_no DESC";
									
	////								//System.out.println("Query To FindOut Due Days from RE GAS Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt4+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
	////									//System.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						//Above Whole Logic Ends Here For Full TCQ Occupation Before SN Contract End Date ...
						
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY'))";
	////					//System.out.println("Customer Plant Name Fetch Query For RE-GAS = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+bill_start_dt2);
						//SB20110920 invoice_Bill_Period_End_Dt.add(""+bill_end_dt2);
						invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); //SB20110920: as per Doc.No: dt20Sep2011
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(temp_Customer_Cd.elementAt(i)+"-"+temp_FGSA_No.elementAt(i)+"-"+temp_FGSA_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+temp_SN_Rev_No.elementAt(i)+"-"+temp_SN_No.elementAt(i)+"-"+plant_no); //Hiren_20200305
						invoice_pre_aprv.add("Y");
//						//System.out.println("invoice_pre_aprv--13--"+invoice_pre_aprv);
						
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd," +
						   			   "gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),new_inv_seq_no " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
										" AND FLAG ='Y' "; //SB20160331"; 	//HERE31
						rset2 = stmt2.executeQuery(queryString2);
						////System.out.println("JAVA inv sel query: "+queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<10000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									} else {
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
					}
				}
			}
			
////			//System.out.println("(temp_Customer_Cd.size()-2) = "+(temp_Customer_Cd.size()-2)+",  invoice_Customer_Cd.size() = "+invoice_Customer_Cd.size());
			//End Of Fetching RE-GAS Related Records ...
//			//System.out.println("-------- END OF RE-GAS -------------");	
			
			
			
			//////////*****************************LTCORA DETAILS***********************////////////////////////////
			
			temp_Customer_Cd.clear();
			temp_Customer_Abbr.clear();
			temp_FGSA_No.clear();
			temp_FGSA_Rev_No.clear();
			temp_SN_No.clear();
			temp_SN_Rev_No.clear();
			temp_Contract_Type.clear();
			temp_SN_Start_Dt.clear();
			temp_SN_End_Dt.clear();
			temp_Bill_Period_Start_Dt.clear();
			temp_Bill_Period_End_Dt.clear();
			temp_Due_Dt.clear();
			temp_Exchg_Rate_Cd.clear();
			temp_Exchg_Rate_Calculation_Method.clear();
			temp_Tcq.clear();
			Vector temp_Mapping_Id=new Vector();
			Vector temp_Max_Mapping_Id=new Vector();
			//2->REVNO 3->REGAS NO 4->CUST
			queryString = "SELECT A.cargo_seq_no,A.MAPPING_ID,A.MAPPING_ID,A.MAPPING_ID," +
						  "TO_CHAR(A.contract_start_dt,'DD/MM/YYYY'),TO_CHAR(A.contract_end_dt,'DD/MM/YYYY')," +
						  "NVL(A.qty_to_be_supply,'0') " +
						  "FROM FMS8_LNG_REGAS_CARGO_DTL A WHERE A.contract_start_dt IS NOT NULL AND " +
						  "A.contract_end_dt IS NOT NULL AND " +
						  "((A.contract_start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.contract_end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.contract_start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY'))) " +
						  "AND FLAG='T'" +
						  "ORDER BY A.MAPPING_ID,A.cargo_seq_no";
		//	System.out.println("LTCORA:QRY-R1001:SELECT:FMS8_LNG_REGAS_CARGO_DTL: "+queryString);
			//rset = stmt.executeQuery(queryString); HIREN_20200227 to get SN contracts only
			while(rset.next())
			{
				String map_id=rset.getString(2)==null?"":rset.getString(2);
				String tempmap_id[]=map_id.split("-");
				
				String temp_regas_no=tempmap_id[1];
				String temp_regas_rev_no=tempmap_id[2];
				String temp_cust_cd=tempmap_id[0];
				
				String customer_abbr = "";
				String bill_period_start_dt = "";
				String bill_period_end_dt = "";
				String due_dt = "";
				int due_days = 0;
				
				String q="select max(rev_no) from fms8_lng_regas_mst where customer_cd='"+temp_cust_cd+"'" +
						 " and agreement_no='"+temp_regas_no+"' and cn_no='0' and cn_rev_no='0' ";
						 		
				rset6=stmt6.executeQuery(q);
				if(rset6.next())
				{
					String temp_rev_no=rset6.getString(1);
					String temp_map_id=temp_cust_cd+"-"+temp_regas_no+"-"+temp_rev_no+"-0-0";
					temp_Max_Mapping_Id.add(temp_map_id);
					//temp_apprv.add(rset6.getString(2)==null?"N":rset6.getString(2));
				}
							
				queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+temp_cust_cd+"";
				////System.out.println("Query To FindOut Customer Abbriviation For LTCORA = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					customer_abbr = rset3.getString(1)==null?"":rset3.getString(1);
				}
				
				queryString3 = "SELECT TO_DATE('"+rset.getString(5)+"','DD/MM/YYYY') - TO_DATE('"+period_start_dt+"','DD/MM/YYYY') FROM DUAL";
				////System.out.println("Query To FindOut Days Difference Between Two Start Dates For ltcora = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_start_dt = rset.getString(5);
					}
					else
					{
						bill_period_start_dt = period_start_dt;
					}
				}
				
				queryString3 = "SELECT TO_DATE('"+rset.getString(6)+"','DD/MM/YYYY') - TO_DATE('"+period_end_dt+"','DD/MM/YYYY') FROM DUAL";
				////System.out.println("Query To FindOut Days Difference Between Two End Dates For LTCORA = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_end_dt = period_end_dt;
					}
					else
					{
						bill_period_end_dt = rset.getString(6);
					}
				}
				
				if(billCycle.equals("1") || billCycle.equals("2"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS8_LNG_REGAS_BILLING_DTL A " +
								   "WHERE A.MAPPING_ID='"+rset.getString(2)+"' " +
								   "";
					
					//system.out.println("Query To FindOut LTCORA Bill Details Based On Fortnightly Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("F"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////system.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Mapping_Id.add(rset.getString(2));
							temp_Customer_Cd.add(temp_cust_cd);
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(temp_regas_no);
							temp_FGSA_Rev_No.add(temp_regas_rev_no);
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("T");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}
				}
				else if(billCycle.equals("3") || billCycle.equals("4") || billCycle.equals("5") || billCycle.equals("6") || billCycle.equals("9"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS8_LNG_REGAS_BILLING_DTL A " +
								   "WHERE A.MAPPING_ID='"+rset.getString(2)+"'  " +
								   "";
					
					////System.out.println("Query To FindOut LTCORA Bill Details Based On Weekly Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("W"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Mapping_Id.add(rset.getString(2));
							temp_Customer_Cd.add(temp_cust_cd);
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(temp_regas_no);
							temp_FGSA_Rev_No.add(temp_regas_rev_no);
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("T");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}					
				}
				else if(billCycle.equals("7"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS8_LNG_REGAS_BILLING_DTL A " +
								   "WHERE A.MAPPING_ID='"+rset.getString(2)+"' " +
								   "";
					
					////System.out.println("Query To FindOut LTCORA Bill Details Based On Monthly Basis Or NOT = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("M"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								temp_Due_Dt.add(rset3.getString(1));
							}
							else
							{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Mapping_Id.add(rset.getString(2));
							temp_Customer_Cd.add(temp_cust_cd);
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(temp_regas_no);
							temp_FGSA_Rev_No.add(temp_regas_rev_no);
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("T");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}					
				}
				
			}
			
			for(int i=0;i<temp_Customer_Cd.size();i++)
			{
				String aprvflag="N";
				String q="select rev_no,pre_approval from fms8_lng_regas_mst where customer_cd='"+temp_Customer_Cd.elementAt(i)+"'" +
						 " and agreement_no='"+temp_FGSA_No.elementAt(i)+"' and cn_no='0' and cn_rev_no='0' ";
						 		
				rset6=stmt6.executeQuery(q);
				//system.out.println("query------ltcora"+q);
				if(rset6.next())
				{
					//String temp_rev_no=rset6.getString(1);
					//String temp_map_id=temp_cust_cd+"-"+temp_regas_no+"-"+temp_rev_no+"-0-0";
					//temp_Max_Mapping_Id.add(temp_map_id);
					aprvflag=(rset6.getString(2)==null?"N":rset6.getString(2));
				}
				
				queryString = "SELECT DISTINCT(plant_seq_no) FROM DLNG_DAILY_ALLOCATION_DTL " +
							  "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
							  "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
							  "sn_no="+temp_SN_No.elementAt(i)+" AND " +
							  "contract_type='T' AND " +
							  "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
							  "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') " +
							  "ORDER BY plant_seq_no";
				
				//system.out.println("SAMIK(A) --> Query For Fetching Correct Plant Seq NO For LTCORA LIST = "+queryString);
				rset = stmt.executeQuery(queryString);
							
				while(rset.next())
				{
					String bill_start_dt1 = "";
					String bill_end_dt1 = "";
					String bill_start_dt2 = "";
					String bill_end_dt2 = "";
					String bill_end_dt3 = "";
					String bill_end_dt4 = "";
					String plant_no = (rset.getString(1)==null?"0":rset.getString(1));
					
					String tmp_bill_period_end_dt = ""+temp_Bill_Period_End_Dt.elementAt(i);
					period_end_dt_FIXED = tmp_bill_period_end_dt;  //SB20110920
	////				//system.out.println("FMS7-INV-LIST:******3: FMS7_RE_GAS_CARGO_DTL ******: "+period_end_dt_FIXED);
					
					queryString3 = "SELECT TO_CHAR(tax_struct_dt,'DD/MM/YYYY') FROM FMS7_CUSTOMER_SERVICE_TAX_DTL WHERE " +
								   "customer_cd="+temp_Customer_Cd.elementAt(i)+" AND plant_seq_no="+rset.getString(1)+" AND " +
								   "tax_struct_dt>TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
								   "tax_struct_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
					////System.out.println("Query To FindOut SERVICE TAX Structure Change Dates In-Between Invoice Billing Period For LTCORA = "+queryString3);
					rset3 = stmt3.executeQuery(queryString3);
					if(rset3.next())
					{
						rset2 = stmt2.executeQuery("SELECT TO_CHAR(TO_DATE('"+rset3.getString(1)+"','DD/MM/YYYY') - 1,'DD/MM/YYYY') PREVIOUS_DATE FROM DUAL");
				        if(rset2.next())
				        {
				        	bill_start_dt1 = ""+temp_Bill_Period_Start_Dt.elementAt(i);
				        	bill_end_dt1 = rset2.getString("PREVIOUS_DATE");
				        	bill_start_dt2 = rset3.getString(1);
				        	bill_end_dt2 = ""+temp_Bill_Period_End_Dt.elementAt(i);
				        }				        
					}
					
					if(bill_start_dt1.equals("") || bill_end_dt1.equals("") || bill_start_dt2.equals("") || bill_end_dt2.equals(""))
					{
						invoice_SN_Ref_No.add("");
						
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='T' AND " +
									   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY')";
						
						//system.out.println("NEHA(B) --> Query For Fetching Last Allocation Date = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='T' AND " +
										   "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
							//system.out.println("NEHA(C) --> LTCORA Query For Fetching Last Allocation Date = "+queryString1);
	////						//System.out.println("FMS7-INV-LIST:LAST-DATE:QRY-IL1001A:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt3.trim().equals(""))
								{
									//temp_Bill_Period_End_Dt.setElementAt(bill_end_dt3,i);
									tmp_bill_period_end_dt = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM FMS8_LNG_REGAS_BILLING_DTL " +
												   "WHERE MAPPING_ID='"+temp_Max_Mapping_Id.elementAt(i)+"' " +
												   "ORDER BY MAPPING_ID DESC";
									
								//system.out.println("Query To FindOut Due Days from LTCORA Bill Details = "+queryString2);
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
		////								//System.out.println("Query To FindOut Due Dates = "+queryString3);
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						//Above Whole Logic Ends Here For Full TCQ Occupation Before RE-Gas Contract End Date ...
						
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY'))";
	////					//system.out.println("Customer Plant Name Fetch Query For LTCORA= "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						// ******** SB:as per Samir Shah dt19Sep2011 For Max End Date for the Billing irrespective of Plant SeqNo ***********//
						queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
						   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						   //"plant_seq_no="+plant_no+" AND " +
						   "contract_type='T' AND " +
						   "gas_dt>=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "gas_dt<=TO_DATE('"+temp_Bill_Period_End_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
	////					//System.out.println("FMS7-REG-INV-LIST:LAST-DATE W/O Plant:QRY-IL1001AA:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							tmp_bill_period_end_dt = rset1.getString(1)==null?"":rset1.getString(1);
						}
						// ^^^^^^^^^^This option is removed as per Rohit dt: 20Sep2011
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+temp_Bill_Period_Start_Dt.elementAt(i));
						invoice_total_qty_mmbtu.add(""+total_qty_mmbtu);  //SB20111207
						invoice_temp_Tcq_mmbtu.add(""+temp_Tcq.elementAt(i)); //SB20111207
						 //SB20110920invoice_Bill_Period_End_Dt.add(""+tmp_bill_period_end_dt);
						invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); //SB20110920: as per Doc.No: dt20Sep2011
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						invoice_Mapping_Id.add(""+temp_Mapping_Id.elementAt(i));
						invoice_pre_aprv.add(""+aprvflag);
//						//System.out.println("invoice_pre_aprv--14--"+invoice_pre_aprv);

						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd," +
						   			   "gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),new_inv_seq_no " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+temp_Bill_Period_Start_Dt.elementAt(i)+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+""+
										" AND FLAG ='Y' "; //SB20160331";   //HERE31
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next()){
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							String inv_seq_number = "";
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
									if(seq_no<10)
									{
										inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<10000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									} else {
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}else{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null){
								int seq_no = Integer.parseInt(rset2.getString(3));
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}else{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
					}
					else
					{
						//dont print
						invoice_SN_Ref_No.add("");
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						double total_qty_mmbtu = 0;
						bill_end_dt3 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='T' AND " +
									   "gas_dt<=TO_DATE('"+bill_end_dt1+"','DD/MM/YYYY')";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='T' AND " +
										   "gas_dt>=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+bill_end_dt1+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt3 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt3.trim().equals(""))
								{
									bill_end_dt1 = bill_end_dt3;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM FMS8_LNG_REGAS_BILLING_DTL " +
												   "WHERE MAPPING_ID='"+temp_Max_Mapping_Id.elementAt(i)+"' " +
												   "ORDER BY MAPPING_ID DESC";
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt3+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						//Above Whole Logic Ends Here For Full TCQ Occupation Before SN Contract End Date ...
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY'))";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+bill_start_dt1);
						invoice_Bill_Period_End_Dt.add(""+bill_end_dt1);
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(""+temp_Mapping_Id.elementAt(i));
						invoice_pre_aprv.add(""+aprvflag);
//						//System.out.println("invoice_pre_aprv--15--"+invoice_pre_aprv);
						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd," +
						   			   "gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),new_inv_seq_no " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+bill_start_dt1+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+""+
										" AND FLAG ='Y' "; //SB20160331";	//HERE31
						rset2 = stmt2.executeQuery(queryString2);
					//	//System.out.println("INV query SN: "+queryString2);
						if(rset2.next())
						{
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<10000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									} else {
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else
							{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null)
							{
								int seq_no = Integer.parseInt(rset2.getString(3));
								
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else
							{
								inv_seq_number = "";
							}
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else
						{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
						invoice_SN_Ref_No.add("");
						//Following Whole LOGIC Has Been Introduced By Samik Shah On 27th April, 2011 ...
						//Following Whole Logic Is For Finding Last Allocation Date While Whole TCQ Has Been Occupied ...
						total_qty_mmbtu = 0;
						bill_end_dt4 = "";
						queryString1 = "SELECT SUM(qty_mmbtu) FROM DLNG_DAILY_ALLOCATION_DTL " +
									   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
									   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
									   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
									   "contract_type='T' AND " +
									   "gas_dt<=TO_DATE('"+bill_end_dt2+"','DD/MM/YYYY')";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							total_qty_mmbtu = rset1.getDouble(1);
						}
						
						if(total_qty_mmbtu>=(Double.parseDouble(""+temp_Tcq.elementAt(i))-0.99))
						{
							queryString1 = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') FROM DLNG_DAILY_ALLOCATION_DTL " +
										   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
										   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
										   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
										   "plant_seq_no="+plant_no+" AND " +
										   "contract_type='T' AND " +
										   "gas_dt>=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY') AND " +
										   "gas_dt<=TO_DATE('"+bill_end_dt2+"','DD/MM/YYYY') AND " +
										   "qty_mmbtu>=0.99 ORDER BY gas_dt DESC";
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next())
							{
								bill_end_dt4 = rset1.getString(1)==null?"":rset1.getString(1);
								
								if(!bill_end_dt4.trim().equals(""))
								{
									bill_end_dt2 = bill_end_dt4;
									
									queryString2 = "SELECT NVL(billing_freq,'F'),NVL(due_date,'0'),NVL(exchng_rate_cd,'0'),NVL(exchng_rate_cal,'D') " +
												   "FROM FMS8_LNG_REGAS_BILLING_DTL " +
												   "WHERE MAPPING_ID='"+temp_Max_Mapping_Id.elementAt(i)+"'  " +
												   "ORDER BY MAPPING_ID DESC";
									rset2 = stmt2.executeQuery(queryString2);
									
									if(rset2.next())
									{
										int due_days = Integer.parseInt(rset2.getString(2));
											
										queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_end_dt4+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
										rset3 = stmt3.executeQuery(queryString3);
										if(rset3.next())
										{
											temp_Due_Dt.setElementAt(rset3.getString(1),i);
										}
									}
								}
							}
						}
						//Above Whole Logic Ends Here For Full TCQ Occupation Before SN Contract End Date ...
						queryString1 = "SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
									   "WHERE A.customer_cd="+temp_Customer_Cd.elementAt(i)+" AND A.seq_no="+rset.getString(1)+" " +
									   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
									   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
									   "AND B.eff_dt<=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY'))";
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Customer_Plant_Nm.add(rset1.getString(1));
						}
						else
						{
							invoice_Customer_Plant_Nm.add(" ");
						}
						invoice_Customer_Plant_Seq_No.add(rset.getString(1));
						invoice_Customer_Cd.add(""+temp_Customer_Cd.elementAt(i));
						invoice_Customer_Abbr.add(""+temp_Customer_Abbr.elementAt(i));
						invoice_FGSA_No.add(""+temp_FGSA_No.elementAt(i));
						invoice_FGSA_Rev_No.add(""+temp_FGSA_Rev_No.elementAt(i));
						invoice_SN_No.add(""+temp_SN_No.elementAt(i));
						invoice_SN_Rev_No.add(""+temp_SN_Rev_No.elementAt(i));
						invoice_Contract_Type.add(""+temp_Contract_Type.elementAt(i));
						invoice_SN_Start_Dt.add(""+temp_SN_Start_Dt.elementAt(i));
						invoice_SN_End_Dt.add(""+temp_SN_End_Dt.elementAt(i));
						invoice_Bill_Period_Start_Dt.add(""+bill_start_dt2);
						//SB20110920 invoice_Bill_Period_End_Dt.add(""+bill_end_dt2);
						invoice_Bill_Period_End_Dt.add(""+period_end_dt_FIXED); //SB20110920: as per Doc.No: dt20Sep2011
						invoice_HLPL_Seq_No.add("");
						invoice_Due_Dt.add(""+temp_Due_Dt.elementAt(i));
						invoice_Exchg_Rate_Cd.add(""+temp_Exchg_Rate_Cd.elementAt(i));
						invoice_Exchg_Rate_Calculation_Method.add(""+temp_Exchg_Rate_Calculation_Method.elementAt(i));
						
						invoice_Mapping_Id.add(""+temp_Mapping_Id.elementAt(i));
						invoice_pre_aprv.add(""+aprvflag);
//						//System.out.println("invoice_pre_aprv--1--"+invoice_pre_aprv);

						queryString2 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no," +
						   			   "checked_flag,checked_by,authorized_flag,authorized_by," +
						   			   "approved_flag,approved_by,exchg_rate_cd,gross_amt_usd," +
						   			   "gross_amt_inr,offspec_rate " +
						   			", TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),new_inv_seq_no " +  //SB20160530
						   			   "FROM DLNG_INVOICE_MST " +
						  			   "WHERE customer_cd="+temp_Customer_Cd.elementAt(i)+" AND " +
						  			   "fgsa_no="+temp_FGSA_No.elementAt(i)+" AND " +
						  			   "sn_no="+temp_SN_No.elementAt(i)+" AND " +
						  			   "contract_type='"+temp_Contract_Type.elementAt(i)+"' AND " +
						  			   "period_start_dt=TO_DATE('"+bill_start_dt2+"','DD/MM/YYYY') AND " +
						  			   "plant_seq_no="+rset.getString(1)+"" +
										" AND FLAG ='Y' "; //SB20160331";	//HERE31
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next()){
							hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
							hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
							
							inv_Checked_Flag.add(rset2.getString(4)==null?"":rset2.getString(4));
							inv_Checked_By.add(rset2.getString(5)==null?"0":rset2.getString(5));
							inv_Authorized_Flag.add(rset2.getString(6)==null?"":rset2.getString(6));
							inv_Authorized_By.add(rset2.getString(7)==null?"0":rset2.getString(7));
							inv_Approved_Flag.add(rset2.getString(8)==null?"":rset2.getString(8));
							inv_Approved_By.add(rset2.getString(9)==null?"0":rset2.getString(9));
							
							inv_Exchg_Rate_CD.add(rset2.getString(10)==null?"0":rset2.getString(10));
							inv_Gross_Amt_USD.add(rset2.getString(11)==null?"":rset2.getString(11));
							inv_Gross_Amt_INR.add(rset2.getString(12)==null?"":rset2.getString(12));
							inv_Offspec_Rate.add(rset2.getString(13)==null?"":rset2.getString(13));
							VPDF_Inv_Dt.add(rset2.getString(14)==null?"":rset2.getString(14)); //SB20160530
							
							String inv_seq_number = "";
							
							if(rset2.getString(1)!=null && rset2.getString(2)!=null){
								int seq_no = Integer.parseInt(rset2.getString(1));
								
									if(seq_no<10)
									{
										inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<100)
									{
										inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<1000)
									{
										inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
									}
									else if(seq_no<10000)
									{
										inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
									} else {
										inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
									}
							}
							else{
								inv_seq_number = "";
							}
							
							hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
							String new_no = rset2.getString(15)==null?"":rset2.getString(15);
							if(new_no.equals("")) {
								new_no = inv_seq_number;
							}
							new_Invoice_Seq_No.add(new_no);
							
							inv_seq_number = "";
							if(rset2.getString(3)!=null && rset2.getString(2)!=null){
								int seq_no = Integer.parseInt(rset2.getString(3));
								if(seq_no<10)
								{
									inv_seq_number = "0000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<100)
								{
									inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
								}
								else if(seq_no<1000)
								{
									inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
								} else if(seq_no<10000) {
									inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
								} else {
									inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
								}
							}
							else{
								inv_seq_number = "";
							}
							
							customer_Invoice_Actual_Seq_No.add(inv_seq_number);
						}
						else{
							hlpl_Invoice_Seq_No_Arr.add("");
							hlpl_Invoice_Financial_Year_Arr.add("");
							hlpl_Invoice_Actual_Seq_No.add("");
							customer_Invoice_Actual_Seq_No.add("");
							inv_Checked_Flag.add("");
							inv_Checked_By.add("0");
							inv_Authorized_Flag.add("");
							inv_Authorized_By.add("0");
							inv_Approved_Flag.add("");
							inv_Approved_By.add("0");
							inv_Exchg_Rate_CD.add("0");
							inv_Gross_Amt_USD.add("");
							inv_Gross_Amt_INR.add("");
							inv_Offspec_Rate.add("");
							VPDF_Inv_Dt.add(""); //SB20160530
							new_Invoice_Seq_No.add("");
						}
					}
				}
			}
			//////////***************************END OF LTCORA DETAILS***********************////////////////////////////
			//////////*****************************CN DETAILS***********************////////////////////////////
			temp_Customer_Cd.clear();
			temp_Customer_Abbr.clear();
			temp_FGSA_No.clear();
			temp_FGSA_Rev_No.clear();
			temp_SN_No.clear();
			temp_SN_Rev_No.clear();
			temp_Contract_Type.clear();
			temp_SN_Start_Dt.clear();
			temp_SN_End_Dt.clear();
			temp_Bill_Period_Start_Dt.clear();
			temp_Bill_Period_End_Dt.clear();
			temp_Due_Dt.clear();
			temp_Exchg_Rate_Cd.clear();
			temp_Exchg_Rate_Calculation_Method.clear();
			temp_Tcq.clear();
			temp_Mapping_Id.clear();
			temp_Max_Mapping_Id.clear();
			temp_apprv.clear();
			
			//2->REVNO 3->REGAS NO 4->CUST
			queryString = "SELECT A.cargo_seq_no,A.MAPPING_ID,A.MAPPING_ID,A.MAPPING_ID," +
						  "TO_CHAR(A.contract_start_dt,'DD/MM/YYYY'),TO_CHAR(A.contract_end_dt,'DD/MM/YYYY')," +
						  "NVL(A.qty_to_be_supply,'0') " +
						  "FROM FMS8_LNG_REGAS_CARGO_DTL A WHERE A.contract_start_dt IS NOT NULL AND " +
						  "A.contract_end_dt IS NOT NULL AND " +
						  "((A.contract_start_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_start_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.contract_end_dt>=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_end_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY')) OR " +
						  "(A.contract_start_dt<=TO_DATE('"+period_start_dt+"','DD/MM/YYYY') AND " +
						  "A.contract_end_dt>=TO_DATE('"+period_end_dt+"','DD/MM/YYYY'))) " +
						  "AND FLAG='C'" +
						  "ORDER BY A.MAPPING_ID,A.cargo_seq_no";
//System.out.println("STEP-1: FMS8_LNG_REGAS_CARGO_DTL---> "+queryString);
			//rset = stmt.executeQuery(queryString); HIREN_20200227 to get SN contracts only
			while(rset.next()){
				String map_id=rset.getString(2)==null?"":rset.getString(2);
				String tempmap_id[]=map_id.split("-");
//				//System.out.println("tempmap----"+tempmap_id[0]+"--"+tempmap_id[3]);
				mappid=map_id;	//BK20160331
				String temp_Aagreement_no=tempmap_id[1];
				String temp_Aagreement_rev_no=tempmap_id[2];
				String temp_regas_no=tempmap_id[3];
				String temp_regas_rev_no=tempmap_id[4];
				String temp_cust_cd=tempmap_id[0];
				String customer_abbr = "";
				String bill_period_start_dt = "";
				String bill_period_end_dt = "";
				String due_dt = "";
				int due_days = 0;
				
				String q="SELECT MAX(A.CN_REV_NO) FROM fms8_lng_regas_mst A WHERE A.AGREEMENT_NO='"+temp_Aagreement_no+"' " +
						"AND A.CUSTOMER_CD='"+temp_cust_cd+"' AND A.CN_NO='"+temp_regas_no+"' " +
					    "AND A.REV_NO='"+temp_Aagreement_rev_no+"' " ;
//				System.out.println("STEP-1A: FMS8_LNG_REGAS_CARGO_DTL---> "+q);
				rset6=stmt6.executeQuery(q);
				if(rset6.next()){
					String temp_rev_no=rset6.getString(1);
					String temp_map_id=temp_cust_cd+"-"+temp_Aagreement_no+"-"+temp_Aagreement_no+"-"+temp_regas_no+"-"+temp_rev_no;
					temp_Max_Mapping_Id.add(temp_map_id);
					//temp_apprv.add(rset6.getString(2)==null?"N":rset6.getString(2));
				}
				queryString3 = "SELECT customer_abbr FROM FMS7_CUSTOMER_MST WHERE customer_cd="+temp_cust_cd+"";
//				System.out.println("STEP-1B: FMS8_LNG_REGAS_CARGO_DTL---> "+q);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next()){
					customer_abbr = rset3.getString(1)==null?"":rset3.getString(1);
				}
				queryString3 = "SELECT TO_DATE('"+rset.getString(5)+"','DD/MM/YYYY') - TO_DATE('"+period_start_dt+"','DD/MM/YYYY') FROM DUAL";
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next()){
					if(Integer.parseInt(rset3.getString(1))>0){
						bill_period_start_dt = rset.getString(5);
					}
					else{
						bill_period_start_dt = period_start_dt;
					}
				}
				
				queryString3 = "SELECT TO_DATE('"+rset.getString(6)+"','DD/MM/YYYY') - TO_DATE('"+period_end_dt+"','DD/MM/YYYY') FROM DUAL";
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						bill_period_end_dt = period_end_dt;
					}
					else
					{
						bill_period_end_dt = rset.getString(6);
					}
				}
//				System.out.println(bill_period_start_dt+" Start Dt & End Dt "+bill_period_end_dt);
				from_dt=bill_period_start_dt;	//BK20160331
				to_dt=bill_period_end_dt;		//BK20160331
				if(billCycle.equals("1") || billCycle.equals("2"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS8_LNG_REGAS_BILLING_DTL A " +
								   "WHERE A.MAPPING_ID='"+rset.getString(2)+"'";
//					System.out.println("STEP5A: Contact C----> "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next()){
						if(rset1.getString(1).equalsIgnoreCase("F")){
							due_days = Integer.parseInt(rset1.getString(2));
							
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
//							System.out.println("Query To FindOut Due Dates = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next()){
								temp_Due_Dt.add(rset3.getString(1));
							}else{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							temp_Mapping_Id.add(rset.getString(2));
							temp_Customer_Cd.add(temp_cust_cd);
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(temp_regas_no);
							temp_FGSA_Rev_No.add(temp_regas_rev_no);
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("C");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}
				}
				else if(billCycle.equals("3") || billCycle.equals("4") || billCycle.equals("5") || billCycle.equals("6") || billCycle.equals("9"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS8_LNG_REGAS_BILLING_DTL A " +
								   "WHERE A.MAPPING_ID='"+rset.getString(2)+"'  ";
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next()){
						if(rset1.getString(1).equalsIgnoreCase("W")){
							due_days = Integer.parseInt(rset1.getString(2));
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next()){
								temp_Due_Dt.add(rset3.getString(1));
							}else{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							temp_Mapping_Id.add(rset.getString(2));
							temp_Customer_Cd.add(temp_cust_cd);
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(temp_regas_no);
							temp_FGSA_Rev_No.add(temp_regas_rev_no);
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("C");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}					
				}
				else if(billCycle.equals("7"))
				{
					queryString1 = "SELECT NVL(A.billing_freq,'F'),NVL(A.due_date,'0'),NVL(A.exchng_rate_cd,'0')," +
								   "NVL(A.exchng_rate_cal,'D') FROM FMS8_LNG_REGAS_BILLING_DTL A " +
								   "WHERE A.MAPPING_ID='"+rset.getString(2)+"' " +
								   "";
					rset1 = stmt1.executeQuery(queryString1);
					
					if(rset1.next())
					{
						if(rset1.getString(1).equalsIgnoreCase("M"))
						{
							due_days = Integer.parseInt(rset1.getString(2));
							queryString3 = "SELECT TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') + "+due_days+",'DD/MM/YYYY') FROM DUAL";
//						System.out.println("queryString3***"+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next()){
								temp_Due_Dt.add(rset3.getString(1));
							}else{
								temp_Due_Dt.add(bill_period_end_dt);
							}
							
							temp_Mapping_Id.add(rset.getString(2));
							temp_Customer_Cd.add(temp_cust_cd);
							temp_Customer_Abbr.add(customer_abbr);
							temp_FGSA_No.add(temp_regas_no);
							temp_FGSA_Rev_No.add(temp_regas_rev_no);
							temp_SN_No.add(rset.getString(1));
							temp_SN_Rev_No.add("0");
							temp_Contract_Type.add("C");
							temp_SN_Start_Dt.add(rset.getString(5));
							temp_SN_End_Dt.add(rset.getString(6));
							temp_Bill_Period_Start_Dt.add(bill_period_start_dt);
							temp_Bill_Period_End_Dt.add(bill_period_end_dt);
							temp_Exchg_Rate_Cd.add(rset1.getString(3));
							temp_Exchg_Rate_Calculation_Method.add(rset1.getString(4));
							temp_Tcq.add(rset.getString(7));
						}
					}					
				}
			}
			
//			System.out.println(temp_Customer_Cd.size()+" :==ExchangeRate==: "+temp_Exchg_Rate_Cd);
			
			for(int i=0;i<temp_Customer_Cd.size();i++){}
//			System.out.println("==invoice_Exchg_Rate_Cd="+invoice_Exchg_Rate_Cd);
			//////////***************************END OF CN DETAILS***********************////////////////////////////
			/*ADDED FOR FETCHING DELETE INVOICE FLAG*/ 
			Vector temp_file_nm=new Vector();
			////System.out.println("HS: -invoice_Mapping_Id--"+invoice_Mapping_Id);
			for(int i=0;i<invoice_Customer_Cd.size();i++)
			{
				int count=0;
				
				String query="SELECT COUNT(HLPL_INV_SEQ_NO) FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' "
						+ "AND PERIOD_END_DT > TO_DATE('"+period_end_dt+"','DD/MM/YYYY') ";
				rset=stmt.executeQuery(query);
				if(rset.next()){
					count=rset.getInt(1);
				}
				if(count==0){
					DelFlag.add("Y");
				}else{
					DelFlag.add("N");
				}
				
				if((""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("T")){
					String temp_map_id=""+invoice_Mapping_Id.elementAt(i);
				
					String tempmap_id[]=temp_map_id.split("-");
					String temp_ltcora_no=tempmap_id[1];
					String temp_ltcora_rev_no=tempmap_id[2];
					String mapping_id=""+invoice_Customer_Cd.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i)+"-"+invoice_FGSA_Rev_No.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+invoice_SN_Rev_No.elementAt(i)+"-T";
					
					queryString = "select price_rate,currency_cd,flag " +
				"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
				"LTCORA_NO='"+temp_ltcora_no+"' AND LTCORA_REV_NO='"+temp_ltcora_rev_no+"'  AND FLAG='R' ";
				
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next())
					{
						Invoice_Pending_approval.add("Y");
						
					}
					else
					{
						Invoice_Pending_approval.add("");
					}
					invoice_tax_adj.add("");
					tax_adj_flag_pdf.add("");
				}
				else if((""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("C"))
				{
					String temp_map_id=""+invoice_Mapping_Id.elementAt(i);
					String tempmap_id[]=temp_map_id.split("-");
					String temp_ltcora_no=tempmap_id[1];
					String temp_ltcora_rev_no=tempmap_id[2];
					String mapping_id=""+invoice_Customer_Cd.elementAt(i)+"-"+invoice_FGSA_No.elementAt(i)+"-"+invoice_FGSA_Rev_No.elementAt(i)+"-"+invoice_SN_No.elementAt(i)+"-"+invoice_SN_Rev_No.elementAt(i)+"-C";
					
					queryString = "select price_rate,currency_cd,flag " +
				"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
				"LTCORA_NO='"+temp_ltcora_no+"' AND LTCORA_REV_NO='"+temp_ltcora_rev_no+"'  AND FLAG='R' ";
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next())
					{
						Invoice_Pending_approval.add("Y");
						
					}
					else
					{
						Invoice_Pending_approval.add("");
					}
					if(date_flag) {
						queryString = "select price_rate,currency_cd,flag " +
								"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
								"LTCORA_NO='"+temp_ltcora_no+"' AND LTCORA_REV_NO='"+temp_ltcora_rev_no+"'  "
								+ "and price_cd in ('10','11','12','13')";
					} else {
						queryString = "select price_rate,currency_cd,flag " +
								"FROM fms7_cont_price_dtl WHERE mapping_id='"+mapping_id+"' AND " +
								"LTCORA_NO='"+temp_ltcora_no+"' AND LTCORA_REV_NO='"+temp_ltcora_rev_no+"'  and price_cd='6'";
					}
					
					////System.out.println("--queryString----no credit-- "+queryString);
						rset1=stmt1.executeQuery(queryString);
						if(rset1.next())
						{
							invoice_tax_adj.add("Y");
							
						}
						else
						{
							invoice_tax_adj.add("");
						}
						
						String temp_inv_dt="";
					String query1="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy'),to_char(INVOICE_DT,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' " +
						"AND HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
								"AND FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' AND FLAG='Y' AND (NEW_INV_SEQ_NO NOT LIKE 'D%' OR NEW_INV_SEQ_NO IS NULL) ";
				
						if((""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("T") || (""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("C"))
						{
							query1+=" and mapping_id='"+invoice_Mapping_Id.elementAt(i)+"'";
						}
						
						//system.out.println("Fetchin....."+query1);
						rset1=stmt1.executeQuery(query1);
						if(rset1.next())
						{
							temp_inv_dt=rset1.getString(2)==null?"":rset1.getString(2);
						}
						
						
						String Mapping_seq_no=invoice_Contract_Type.elementAt(i)+":"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+":"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+":"+temp_inv_dt;
						String mapid[]=invoice_Mapping_Id.elementAt(i).toString().split("-");
						String queryString1 = "";
						if(date_flag) {
							queryString1="select price_cd, AMOUNT, CURRENCY, TOTAL_TARIFF, INV_AMT_INR, " +
									" INV_AMT_USD, REC_FLAG, REMARK, FLAG, OPERATION, BASIC_INV_AMT_INR, BASIC_INV_AMT_USD from FMS7_INV_COMPO_DTL" +
									" where INV_SEQ_NO='"+Mapping_seq_no+"' and price_cd in ('10','11','12','13') and (flag_temp not like 'T' or flag_temp is null)";
							if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("T") || invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("C"))
							{
								queryString1+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
							}
						} else {
							queryString1="select price_cd, AMOUNT, CURRENCY, TOTAL_TARIFF, INV_AMT_INR, " +
									" INV_AMT_USD, REC_FLAG, REMARK, FLAG, OPERATION, BASIC_INV_AMT_INR, BASIC_INV_AMT_USD from FMS7_INV_COMPO_DTL" +
									" where INV_SEQ_NO='"+Mapping_seq_no+"' and price_cd='6' and (flag_temp not like 'T' or flag_temp is null)";
							if(invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("T") || invoice_Contract_Type.elementAt(i).toString().equalsIgnoreCase("C"))
							{
								queryString1+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
							}
						}
						
						rset2= stmt2.executeQuery(queryString1);
						if(rset2.next())
						{
							tax_adj_flag_pdf.add("Y");
						}
						else
						{
							tax_adj_flag_pdf.add("");
						}
				}
				else
				{
					Invoice_Pending_approval.add("");
					invoice_tax_adj.add("");
					tax_adj_flag_pdf.add("");
				}
				
				if((""+inv_Approved_Flag.elementAt(i)).equalsIgnoreCase("Y"))
				{
					////System.out.println("inside    iffffffffff");
					HttpSession sess = request.getSession();
					String invoice_pdf_path = sess.getAttribute("invoice_pdf_path").toString();
					
					String invoice_date1="";
					String invoice_date2="";
					String pdf_inv_dtl="";
					String query1="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy'),to_char(INVOICE_DT,'dd/mm/yyyy'),PDF_INV_DTL FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' " +
							"AND HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
									"AND FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' AND FLAG='Y'";
					
					if((""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("T") || (""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("C"))
					{
						query1+=" and mapping_id='"+invoice_Mapping_Id.elementAt(i)+"'";
					}
					
				//	//System.out.println("Fetchin....."+query1);
					rset1=stmt1.executeQuery(query1);
					if(rset1.next())
					{
						 invoice_date1=rset1.getString(1)==null?"":rset1.getString(1);
						 invoice_date2=rset1.getString(2)==null?"":rset1.getString(2);
						 pdf_inv_dtl=rset1.getString(3)==null?"":rset1.getString(3);
					}
					invoice_inv_period_dt.add(invoice_date1); //HS20160620
					invoice_inv_date.add(invoice_date2);
					
					if(pdf_inv_dtl.contains("T"))
					{
						pdf_color.add("T");
					}
					else if(pdf_inv_dtl.contains("D"))
					{
						pdf_color.add("D");
					}
					else if(pdf_inv_dtl.contains("O"))
					{
						pdf_color.add("O");
					}
				/*SB20160405	else if(pdf_inv_dtl.contains("Q")) //SB20160402
					{
						pdf_color.add("Q"); //SB20160402
					}*/
					else
					{
						pdf_color.add("N");
					}
					
				//SB20160402 if(pdf_inv_dtl.length()==3)
					boolean temp_flag = false;
					String q_debit = "SELECT COUNT(SN_NO) FROM FMS7_DR_CR_NOTE WHERE FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' "
							+ "AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' "
							+ "AND HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' "
							+ "AND CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' ";
					rset1 = stmt1.executeQuery(q_debit);
//					System.out.println("==="+q_debit);
					if(rset1.next()) {
						int count_debit = rset1.getInt(1);
						if(count_debit>0) {
							temp_flag = true;
						}
					}
						
					if(temp_flag) {
						if(invoice_Contract_Type.elementAt(i).equals("C")) {
							if(pdf_inv_dtl.length()>=4)  //SB20160405
							{
								customer_invoice_pdf_lock_flag.add("Y");
							}
							else
							{
								customer_invoice_pdf_lock_flag.add("N");
							}
						} else {
							if(pdf_inv_dtl.length()>=5)  //SB20160405
							{
								customer_invoice_pdf_lock_flag.add("Y");
							}
							else
							{
								customer_invoice_pdf_lock_flag.add("N");
							}
						}
					} else {
						if(date_flag && invoice_Contract_Type.elementAt(i).equals("C")) {
							if(pdf_inv_dtl.length()>=2) {
								customer_invoice_pdf_lock_flag.add("Y");
							} else {
								customer_invoice_pdf_lock_flag.add("N");
							}
						} else {
							if(pdf_inv_dtl.length()==3)  //SB20160405
							{
								customer_invoice_pdf_lock_flag.add("Y");
							}
							else
							{
								customer_invoice_pdf_lock_flag.add("N");
							}
						}
					}
//					System.out.println("customer invoice pdf lock flag=="+customer_invoice_pdf_lock_flag+"=="+pdf_inv_dtl+"=="+date_flag+"=="+temp_flag);
					//ADDED FOR INVOICEPDF CHANGE AFTER BENCH DATE1
					
					String tempD10[]=invoice_bench_date1.split("/");
					String d10=tempD10[2]+tempD10[1]+tempD10[0];
					
					String tempD20[]=invoice_date2.split("/");
					String d20=tempD20[2]+tempD20[1]+tempD20[0];
					////System.out.println("----------->"+d20+"-------"+d10);
					if(Integer.parseInt(d20)>Integer.parseInt(d10))
					{
						
//						//System.out.println("IN 1111 IFFFFF");
						int count_inv=0;
						customer_invoice_pdf_path_flag.add("Y"); //SB20181024
					//	System.out.println(i+"--customer_invoice_pdf_path_flag---->  "+customer_invoice_pdf_path_flag);
						customer_invoice_pdf_path.add("");
					}
					else
					{
//						//System.out.println("IN ELSE");
						String[] file_bunch_qtr= null;
						File lst_qtr= new File(invoice_pdf_path);
						file_bunch_qtr=lst_qtr.list();
						int count1=0;
						Vector customer_invoice_pdf_path1=new Vector();
						Vector customer_invoice_pdf_path_flag1=new Vector();
						Vector title_vect=new Vector();
						title_vect.add("O");
						title_vect.add("D");
						title_vect.add("T");
						title_vect.add("Q");
						int count2=0;
						for(int h=0;h<4;h++)
						{
								String invoice_pdf_path1=invoice_pdf_path;
							count2=0;
							//for ( int j=0;j<file_bunch_qtr.length;j++ )
						    //{
								
								//String file=file_bunch_qtr[j];
								////System.out.println("invoice_date1--"+invoice_date1);
								String f1="";
								
								//ADDED FOR INVOICE_NAME CHANGE AFTER BENCH DATE
								//Date d1=new Date(invoice_bench_date);
								//Date d2=new Date(invoice_date2);
								
								String tempD1[]=invoice_bench_date.split("/"); //10/03/2015
								String d1=tempD1[2]+tempD1[1]+tempD1[0];
								
								String tempD2[]=invoice_date2.split("/");
								String d2=tempD2[2]+tempD2[1]+tempD2[0];
								
									if(Integer.parseInt(d2)>Integer.parseInt(d1))
									{
										f1="INVOICE-"+invoice_date1.trim().substring(0,2)+invoice_date1.trim().substring(3,5)+invoice_date1.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(i)+"-"+invoice_Customer_Plant_Nm.elementAt(i)+"-"+invoice_Contract_Type.elementAt(i)+"-"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"-"+title_vect.elementAt(h);
									}
									else
									{
										f1="INVOICE-"+invoice_date1.trim().substring(0,2)+invoice_date1.trim().substring(3,5)+invoice_date1.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(i)+"-"+invoice_Customer_Plant_Nm.elementAt(i);
									}
									// //System.out.println("file---->"+file);
									////System.out.println("f1--"+f1);
									//HS20160621 if(file.startsWith(f1))
									f1=invoice_pdf_path1+"/"+f1;
									////System.out.println("rrrf1---"+f1);
									File inv_file=new File(f1+".pdf");
									////System.out.println("inv_file---"+inv_file);
									if(inv_file.exists())
									{
										////System.out.println("inside  rrr");
											count1++;
											count2++;
											//HS20160621 invoice_pdf_path1=invoice_pdf_path1+"//"+file;
											invoice_pdf_path1=invoice_pdf_path1+"//"+inv_file;
											////System.out.println("invoice_pdf_path1----"+invoice_pdf_path1);
								            String context_nm = request.getContextPath();
											String server_nm = request.getServerName();
											String server_port = ""+request.getServerPort();
											  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
											String url_start = "http://"+server_nm+":"+server_port+context_nm;
											
											String pdfpath = invoice_pdf_path1;
											pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
											
											pdfpath = url_start+"/pdf_reports/pdf_files"+pdfpath;
//											System.out.println("pdfpath---> "+pdfpath);
											customer_invoice_pdf_path1.add(pdfpath);
											customer_invoice_pdf_path_flag1.add("Y");
											
									}
								
						   // }
							if(count2==0)
							{
								customer_invoice_pdf_path1.add("");
								customer_invoice_pdf_path_flag1.add("N");
							}
						}
						if(count1==0)
						{
//							//System.out.println("IN 22222 IFFFFF");
							temp_file_nm.add("");
							//customer_invoice_pdf_path.add("");
							customer_invoice_pdf_path_flag.add("N");
						}
						else
						{
							////System.out.println("IN 3333 IFFFFF");
							customer_invoice_pdf_path_flag.add("Y");
						}
					//	//System.out.println(count1+"--customer_invoice_pdf_path_flag--->cnt1: "+customer_invoice_pdf_path_flag);
						customer_invoice_pdf_path.add(customer_invoice_pdf_path1);
					}	
					
				}
				else
				{
					////System.out.println("IN 44444 IFFFFF");
					temp_file_nm.add("");
					Vector customer_invoice_pdf_path1=new Vector();
					for(int h=0;h<4;h++)
					{
						customer_invoice_pdf_path1.add("");
					}
					
					pdf_color.add("N");
					
					customer_invoice_pdf_path.add(customer_invoice_pdf_path1);
					customer_invoice_pdf_path_flag.add("N");
					customer_invoice_pdf_lock_flag.add("N");
					
					String invoice_date2="",invoice_date1="";
					String query1="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy'),to_char(INVOICE_DT,'dd/mm/yyyy'),PDF_INV_DTL FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' " +
					"AND HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
							"AND FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' AND FLAG='Y' AND (NEW_INV_SEQ_NO NOT LIKE 'D%' OR NEW_INV_SEQ_NO IS NULL) ";
			
					if((""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("T") || (""+invoice_Contract_Type.elementAt(i)).equalsIgnoreCase("C"))
					{
						query1+=" and mapping_id='"+invoice_Mapping_Id.elementAt(i)+"'";
					}
					
				//	//System.out.println("Fetchin....."+query1);
					rset1=stmt1.executeQuery(query1);
					if(rset1.next()){
						 invoice_date1=rset1.getString(1)==null?"":rset1.getString(1);
						 invoice_date2=rset1.getString(2)==null?"":rset1.getString(2);
						 //pdf_inv_dtl=rset1.getString(3)==null?"":rset1.getString(3);
					}
					invoice_inv_period_dt.add(invoice_date1); //HS20160620
					invoice_inv_date.add(invoice_date2);
				}
			}
		//	//System.out.println("---invoice_inv_date---: "+invoice_inv_date);
			for(int i=0;i<invoice_Customer_Cd.size();i++){
				for(int j=0;j<invoice_Customer_Cd.size();j++){
					if(i!=j){
					if((""+inv_Approved_Flag.elementAt(i)).equalsIgnoreCase("Y") && (""+inv_Approved_Flag.elementAt(j)).equalsIgnoreCase("Y"))
					{
						HttpSession sess = request.getSession();
						String invoice_pdf_path = sess.getAttribute("invoice_pdf_path").toString();
							String invoice_dt2="";
						
						String invoice_date1="";
						String query1="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy'),to_char(INVOICE_DT,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(i)+"' " +
								"AND HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(i)+"' " +
								"AND FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' AND FLAG='Y' ";
						rset1=stmt1.executeQuery(query1);
						if(rset1.next()){
							 invoice_date1=rset1.getString(1)==null?"":rset1.getString(1);
							 invoice_dt2=rset1.getString(2)==null?"":rset1.getString(2);
						}
						
						String invoice_date2="";
						String query2="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(j)+"' " +
								"AND HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(j)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(j)+"' " +
								"AND FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(j)+"' AND FLAG='Y' ";
						rset2=stmt2.executeQuery(query2);
						if(rset2.next()){
							 invoice_date2=rset2.getString(1)==null?"":rset2.getString(1);
						}
						
						String tempD10[]=invoice_bench_date1.split("/"); //04072015
						String d10=tempD10[2]+tempD10[1]+tempD10[0];
						
						String tempD20[]=invoice_date2.split("/");
						String d20=tempD20[2]+tempD20[1]+tempD20[0];
						
						if(Integer.parseInt(d20)>Integer.parseInt(d10)){}
						else{
							String f1="";
							String f2="";
							String tempD1[]=invoice_bench_date.split("/");
							String d1=tempD1[2]+tempD1[1]+tempD1[0];
							
							String tempD2[]=invoice_dt2.split("/");
							String d2=tempD2[2]+tempD2[1]+tempD2[0];
							
							if(Integer.parseInt(d2)>Integer.parseInt(d1)){
								 f1="INVOICE-"+invoice_date1.trim().substring(0,2)+invoice_date1.trim().substring(3,5)+invoice_date1.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(i)+"-"+invoice_Customer_Plant_Nm.elementAt(i)+"-"+invoice_Contract_Type.elementAt(i)+"-"+hlpl_Invoice_Seq_No_Arr.elementAt(i);
								 f2="INVOICE-"+invoice_date2.trim().substring(0,2)+invoice_date2.trim().substring(3,5)+invoice_date2.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(j)+"-"+invoice_Customer_Plant_Nm.elementAt(j)+"-"+invoice_Contract_Type.elementAt(j)+"-"+hlpl_Invoice_Seq_No_Arr.elementAt(j);
							}
							else{
								 f1="INVOICE-"+invoice_date1.trim().substring(0,2)+invoice_date1.trim().substring(3,5)+invoice_date1.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(i)+"-"+invoice_Customer_Plant_Nm.elementAt(i);
								 f2="INVOICE-"+invoice_date2.trim().substring(0,2)+invoice_date2.trim().substring(3,5)+invoice_date2.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(j)+"-"+invoice_Customer_Plant_Nm.elementAt(j);
							}
							
							if(f1.equals(f2)){
								if(customer_invoice_pdf_path_flag.elementAt(i).equals("Y")){
									customer_invoice_pdf_path.remove(i);
									customer_invoice_pdf_path_flag.remove(i);
									customer_invoice_pdf_path.add(i,"");
									customer_invoice_pdf_path_flag.add(i,"M");
								}
							}
						}
					}
					}
				}
			}
			print_pdf_cr_dr_details(); //HS20160617----For credit/Debit
		}
		catch(Exception e)
		{
			//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchInvoicePreperationDetails() ...
	String flag="N"; 
	Vector flagqty=new Vector();
	Vector VRemark=new Vector();
	Vector VcrNewInvSeqNo=new Vector();
	Map credit_map = new HashMap();
	Vector fy_yr  = new Vector();
	public void print_pdf_cr_dr_details()  //HS20160617----For credit/Debit
	{
		methodName = "print_pdf_cr_dr_details()";
		try 
		{
			String inv_seq_number="";
			String cst_inv_seq_number="";
			
			////System.out.println("tax_adj_flag_pdf----"+tax_adj_flag_pdf+"--invoice_tax_adj----"+invoice_tax_adj);
			queryString2="select HLPL_INV_SEQ_NO, CONTRACT_TYPE, FINANCIAL_YEAR,mapping_id,new_inv_seq_no,"
					+ "customer_cd,fgsa_no,sn_no,plant_seq_no "
					+ " from DLNG_INVOICE_MST " +
			" where PERIOD_START_DT >= TO_DATE('"+period_start_dt+"','DD/MM/YYYY') "+
			" AND PERIOD_END_DT <= TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND FLAG='X' ORDER BY INVOICE_DT DESC";
//			System.out.println("queryString2----"+queryString2);
			rset3=stmt3.executeQuery(queryString2);
			while(rset3.next())
			{
				VCrInvSeqNo.add(rset3.getString(1));
				VCrContType.add(rset3.getString(2));
				VCrFY.add(rset3.getString(3));
				invoice_Mapping_Id.add(rset3.getString(4)==null?"":rset3.getString(4));
				VcrNewInvSeqNo.add(rset3.getString(5)==null?rset3.getString(1):rset3.getString(4));
				
				//customer_cd+":"+fgsa_no+":"+sn_no+":"+plant_no+":"+contract_type
				credit_map.put(rset3.getString(6)+":"+rset3.getString(7)+":"+rset3.getString(8)+":"+rset3.getString(9)+":"+rset3.getString(2),"Y");
			}
					int x=invoice_Customer_Plant_Seq_No.size();
					for(int k=0;k<VCrInvSeqNo.size();k++)
					{
						queryString2="SELECT CUSTOMER_CD,PLANT_SEQ_NO,HLPL_INV_SEQ_NO,FGSA_NO,"
								+ " SN_NO,SN_REV_NO,CONTRACT_TYPE,PERIOD_START_DT,PERIOD_END_DT,DUE_DT, "
								+ "EXCHG_RATE_TYPE,EXCHG_RATE_CD,CHECKED_FLAG,CUST_INV_SEQ_NO,FINANCIAL_YEAR,"
								+ " CHECKED_BY,AUTHORIZED_FLAG,AUTHORIZED_BY,APPROVED_FLAG,APPROVED_BY,"
								+ "GROSS_AMT_USD,GROSS_AMT_INR,OFFSPEC_RATE,PDF_INV_DTL,FGSA_REV_NO,to_char(PERIOD_END_DT,'dd/mm/yyyy'), " //HS20160620
								+ "NEW_INV_SEQ_NO  from DLNG_INVOICE_MST " +
								" WHERE FINANCIAL_YEAR='"+VCrFY.elementAt(k)+"' AND HLPL_INV_SEQ_NO='"+VCrInvSeqNo.elementAt(k)+"' " +
								" AND CONTRACT_TYPE='"+VCrContType.elementAt(k)+"' AND FLAG='X' " +
								" ORDER BY HLPL_INV_SEQ_NO DESC";
//						System.out.println("queryString2---1---"+queryString2);
					//rset3=stmt3.executeQuery(queryString2); HIREN_20200227 to get SN contracts only
					while(rset3.next())
					{
						String cstcd=rset3.getString(1)==null?"":rset3.getString(1);
						invoice_Customer_Cd.add(cstcd);
						
						String queryString1 = "SELECT CUSTOMER_NAME,CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST WHERE CUSTOMER_CD='"+rset3.getString("CUSTOMER_CD")+"'";
						rset=stmt.executeQuery(queryString1);
						while(rset.next())
						{
							invoice_Customer_Abbr.add(rset.getString(2));
						}
						
						String plantno=rset3.getString(2)==null?"":rset3.getString(2);
						invoice_Customer_Plant_Seq_No.add(plantno);
						
						String q1="SELECT NVL(A.plant_name,' ') FROM FMS7_CUSTOMER_PLANT_DTL A " +
								   "WHERE A.customer_cd='"+cstcd+"' AND A.seq_no='"+plantno+"' " +
								   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no) " ;
//								   "AND B.eff_dt<=TO_DATE('"+from_dt+"','DD/MM/YYYY'))"; HIREN_20200227 getting blank from_dt var
//						System.out.println("q111111*******"+q1);
						rset6=stmt6.executeQuery(q1);
						while(rset6.next())
						{
							invoice_Customer_Plant_Nm.add(rset6.getString(1));
						}
						
						invoice_HLPL_Seq_No.add(rset3.getString(3)==null?"":rset3.getString(3));
						
						String fgsano=rset3.getString(4)==null?"":rset3.getString(4);
						invoice_FGSA_No.add(fgsano);
						
						String fgsarevno=rset3.getString(25)==null?"":rset3.getString(25);
						invoice_FGSA_Rev_No.add(fgsarevno);
						
						String snno=rset3.getString(5)==null?"":rset3.getString(5);
						invoice_SN_No.add(snno);
						
						String sn_rev_no1=rset3.getString(6)==null?"":rset3.getString(6);
						invoice_SN_Rev_No.add(rset3.getString(6)==null?"":rset3.getString(6));
						
						String ctype=rset3.getString(7)==null?"":rset3.getString(7);
						invoice_Contract_Type.add(ctype);
						
						String psdt=rset3.getString(8)==null?"":rset3.getString(8);
					//	//System.out.println("INV:START-DT :"+psdt); 
						String f[]=psdt.split("-");
						String fdt[]=f[2].split(" ");
						String sdate=fdt[0]+"/"+f[1]+"/"+f[0];
						
				////SB		invoice_SN_Start_Dt.add(sdate); 
						invoice_SN_Start_Dt.add(""); 
					
						String pedt=rset3.getString(9)==null?"":rset3.getString(9);
						invoice_inv_period_dt.add(rset3.getString(26)==null?"":rset3.getString(26)); //HS20160620
						String t[]=pedt.split("-");
						String tdt[]=t[2].split(" ");
						String edate=tdt[0]+"/"+t[1]+"/"+t[0];
					////SB 	invoice_SN_End_Dt.add(edate);
						
						invoice_SN_End_Dt.add("");
						
						invoice_Bill_Period_Start_Dt.add(sdate);
						invoice_Bill_Period_End_Dt.add(edate);
						
						invoice_Due_Dt.add(rset3.getString(10)==null?"":rset3.getString(10));
						
						invoice_Exchg_Rate_Calculation_Method.add(rset3.getString(11)==null?"":rset3.getString(12));
						invoice_Exchg_Rate_Cd.add(rset3.getString(12)==null?"":rset3.getString(12));
						
						invoice_adjust_flag.add(rset3.getString(13)==null?"":rset3.getString(13));
						hlpl_Invoice_Seq_No_Arr.add(rset3.getString(3)==null?"":rset3.getString(3));
						
						String fyr=rset3.getString(15)==null?"":rset3.getString(15);
						hlpl_Invoice_Financial_Year_Arr.add(fyr);
						
						//HERE
						if(rset3.getString(3)!=null && rset3.getString(3)!=null)
						{
							int seq_no = Integer.parseInt(rset3.getString(3));
							
							if(seq_no<10)
							{
								inv_seq_number = "0000"+seq_no+"/"+rset3.getString(15);
							}
							else if(seq_no<100)
							{
								inv_seq_number = "000"+seq_no+"/"+rset3.getString(15);
							}
							else if(seq_no<1000)
							{
								inv_seq_number = "00"+seq_no+"/"+rset3.getString(15);
							}
							else if(seq_no<10000)
							{
								inv_seq_number = "0"+seq_no+"/"+rset3.getString(15);
							} else {
								inv_seq_number = ""+seq_no+"/"+rset3.getString(15);
							}
						}
						else
						{
							inv_seq_number = "";
						}
						////System.out.println("Deep inv_seq_number:"+inv_seq_number);
						hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
						
						new_Invoice_Seq_No.add(rset3.getString(27)==null?inv_seq_number:rset3.getString(27));
						
						if(rset3.getString(14)!=null && rset3.getString(14)!=null){
							int seq_no = Integer.parseInt(rset3.getString(14));
							
							if(seq_no<10){
								cst_inv_seq_number = "00"+seq_no+"/"+fyr;
							}
							else if(seq_no<100){
								cst_inv_seq_number = "0"+seq_no+"/"+fyr;
							}
							else{
								cst_inv_seq_number = ""+seq_no+"/"+fyr;
							}
						}else{
							cst_inv_seq_number = "";
						}
						customer_Invoice_Actual_Seq_No.add(cst_inv_seq_number);
						fy_yr.add(rset3.getString(15)==null?"":rset3.getString(15));
						inv_Checked_Flag.add(rset3.getString(13)==null?"":rset3.getString(13));
						inv_Checked_By.add(rset3.getString(16)==null?"":rset3.getString(16));
						
						inv_Authorized_Flag.add(rset3.getString(17)==null?"":rset3.getString(17));
						inv_Authorized_By.add(rset3.getString(18)==null?"":rset3.getString(18));
						
						inv_Approved_Flag.add(rset3.getString(19)==null?"":rset3.getString(19));//SB20160401
					//SB20160401	inv_Approved_Flag.add(rset3.getString("APPROVED_FLAG"));
						inv_Approved_By.add(rset3.getString(20)==null?"":rset3.getString(20));
						
						inv_Gross_Amt_USD.add(rset3.getString(21)==null?"":rset3.getString(21));
						inv_Gross_Amt_INR.add(rset3.getString(22)==null?"":rset3.getString(22));
						
						inv_Exchg_Rate_CD.add(rset3.getString(12)==null?"":rset3.getString(12));
						inv_Offspec_Rate.add(rset3.getString(23)==null?"":rset3.getString(23));
						//invoice_SN_Ref_No.add(rset3.getString("SN_REV_NO"));
						
						queryString1 = "SELECT NVL(A.sn_ref_no,'') FROM DLNG_SN_MST A " +
								   "WHERE A.customer_cd='"+cstcd+"' " +
								   "AND A.sn_no='"+snno+"' " +
								   "AND A.flsa_no='"+fgsano+"' " +
								   "AND A.sn_ref_no IS NOT NULL " +
								   "ORDER BY A.sn_rev_no DESC";
							rset1 = stmt1.executeQuery(queryString1);
							if(rset1.next()){
								invoice_SN_Ref_No.add(rset1.getString(1));
							}
							else{
								invoice_SN_Ref_No.add("");
							}
						
						
							String pdf_inv_dtl=rset3.getString(24)==null?"":rset3.getString(24);
						if(pdf_inv_dtl.contains("C") && !pdf_inv_dtl.equalsIgnoreCase(""))
							pdf_color.add("C"); //SB20160402:
						else
							pdf_color.add("N");
//						System.out.println("pdf_inv_dtl"+pdf_inv_dtl);
						if(pdf_inv_dtl.length()>=4 && !pdf_inv_dtl.equalsIgnoreCase("")){
							customer_invoice_pdf_lock_flag.add("Y");
							customer_invoice_pdf_path_flag.add("Y");
						}
						else{
							customer_invoice_pdf_lock_flag.add("N");
							customer_invoice_pdf_path_flag.add("N");
						}
					//	String mapp_id=CustomerCd+":"+FgsaNo+":"+FgsaRevNo+":"+SnNo+":"+SnRevNo+":"+plant_seq_no;
						//String mapping_id=cstcd+"-"+fgsano+"-"+fgsarevno+"-"+snno+"-"+sn_rev_no1; //HS20160620
						//invoice_Mapping_Id.add("1-1-0-3-0");
						Invoice_Pending_approval.add("-");
						DelFlag.add("-");
						invoice_pre_aprv.add("Y");
						////System.out.println("invoice_pre_aprv--5--"+invoice_pre_aprv);
						customer_invoice_pdf_path.add("-");
						
						 invoice_inv_date.add("15/03/2016"); //BK: to fill vector
						String temp_inv_dt="";
					//	//System.out.println("--invoice_Mapping_Id---"+invoice_Mapping_Id);
						String query1="SELECT to_char(PERIOD_END_DT,'dd/mm/yyyy'),to_char(INVOICE_DT,'dd/mm/yyyy') FROM DLNG_INVOICE_MST WHERE CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(x+k)+"' " +
								"AND HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(x+k)+"' AND CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(x+k)+"' " +
										"AND FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(x+k)+"' AND FLAG='X'";
							
							if((""+invoice_Contract_Type.elementAt(x+k)).equalsIgnoreCase("T") || (""+invoice_Contract_Type.elementAt(x+k)).equalsIgnoreCase("C"))
								{
									query1+=" and mapping_id='"+invoice_Mapping_Id.elementAt(x+k)+"'";
								}
					//		//System.out.println("--query1-- " +query1);	
								rset1=stmt1.executeQuery(query1);
								if(rset1.next())
								{
									temp_inv_dt=rset1.getString(2)==null?"":rset1.getString(2);
								}
						String Mapping_seq_no=invoice_Contract_Type.elementAt(x+k)+":"+hlpl_Invoice_Financial_Year_Arr.elementAt(x+k)+":"+hlpl_Invoice_Seq_No_Arr.elementAt(x+k)+":"+temp_inv_dt;
						String mapid[]=invoice_Mapping_Id.elementAt(x+k).toString().split("-");
						queryString="select price_cd, AMOUNT, CURRENCY, TOTAL_TARIFF, INV_AMT_INR, " +
								" INV_AMT_USD, REC_FLAG, REMARk, FLAG, OPERATION, BASIC_INV_AMT_INR, BASIC_INV_AMT_USD from FMS7_INV_COMPO_DTL" +
								" where INV_SEQ_NO like '"+Mapping_seq_no+"%' and price_cd='6' and (flag_temp not like 'T' or flag_temp is null)";
						if(invoice_Contract_Type.elementAt(x+k).toString().equalsIgnoreCase("T") || invoice_Contract_Type.elementAt(x+k).toString().equalsIgnoreCase("C"))
						{
							queryString+=" AND LTCORA_NO='"+mapid[1]+"' AND LTCORA_REV_NO='"+mapid[2]+"'";
						}
						////System.out.println("queryString---credit-- "+queryString);
						rset2= stmt2.executeQuery(queryString);
						if(rset2.next())
						{
							invoice_tax_adj.add("Y");
							tax_adj_flag_pdf.add("Y");
						}
						else
						{
							invoice_tax_adj.add("");
							tax_adj_flag_pdf.add("");
						}
					}
					}     //For
			/*SB20160615	queryString2="select TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') from DLNG_INVOICE_MST " +
						" where INVOICE_DT BETWEEN TO_DATE('"+from_dt+"','DD/MM/YYYY') "+
				"AND TO_DATE('"+to_dt+"','DD/MM/YYYY') AND FLAG='X' ORDER BY INVOICE_DT DESC";*/	
				queryString2="select TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') from DLNG_INVOICE_MST " +
				" where PERIOD_START_DT >= TO_DATE('"+period_start_dt+"','DD/MM/YYYY') "+
				" AND PERIOD_END_DT <= TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND FLAG='X' ORDER BY INVOICE_DT DESC";
				rset3=stmt3.executeQuery(queryString2);
				while(rset3.next()){
					VPDF_Inv_Dt.add(rset3.getString(1)); //SB20160530
				}
		
			
			////System.out.println(invoice_Customer_Cd.size()+"-----invoice_Customer_Cd--- "+invoice_Customer_Cd);
			for(int k=0;k<invoice_Customer_Cd.size();k++){
				queryString="SELECT A.FLAG,A.HLPL_INV_SEQ_NO,A.CONTRACT_TYPE " +
				", DR_CR_DOC_NO, DR_CR_FLAG, TO_CHAR(DR_CR_DT, 'DD-Mon-YY') " + //HS20160615 && SB20160526
				" , TO_CHAR(A.INVOICE_DT, 'DD/MM/YYYY'), NVL(B.APRV_BY,'0'), TO_CHAR(B.APRV_DT, 'DD/MM/YYYY'),B.CRITERIA,B.REMARK " + //SB20160526
				" FROM DLNG_INVOICE_MST A, FMS7_DR_CR_NOTE B WHERE " +
			"A.INVOICE_DT >= TO_DATE('"+invoice_Bill_Period_Start_Dt.elementAt(k)+"','DD/MM/YYYY')"+ // MCL20160531 AND A.INVOICE_DT <= TO_DATE('"+invoice_Bill_Period_End_Dt.elementAt(k)+"','DD/MM/YYYY') " + 
			"AND B.FLAG='Y' AND A.FLAG!='A' AND A.HLPL_INV_SEQ_NO='"+hlpl_Invoice_Seq_No_Arr.elementAt(k)+"' AND A.HLPL_INV_SEQ_NO=B.HLPL_INV_SEQ_NO  AND  " + // AND B.CRITERIA='DIFF-EXG' A.INVOICE_DT=TO_DATE('"+invoice_inv_date.elementAt(k)+"','DD/MM/YYYY') AND A.INVOICE_DT=B.INVOICE_DT
			" A.CUSTOMER_CD='"+invoice_Customer_Cd.elementAt(k)+"' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND A.CONTRACT_TYPE='"+invoice_Contract_Type.elementAt(k)+"' AND A.CONTRACT_TYPE=B.CONTRACT_TYPE "
		+ "AND A.FINANCIAL_YEAR = '"+hlpl_Invoice_Financial_Year_Arr.elementAt(k)+"' AND A.FINANCIAL_YEAR = B.FINANCIAL_YEAR "
		+ "AND A.HLPL_INV_SEQ_NO||A.FINANCIAL_YEAR||A.CONTRACT_TYPE IN (SELECT B.HLPL_INV_SEQ_NO||B.FINANCIAL_YEAR||B.CONTRACT_TYPE FROM FMS7_DR_CR_NOTE B " +
		"WHERE (B.CRITERIA='REV_INV' OR B.CRITERIA='DIFF-EXG'  OR B.CRITERIA='DIFF-PRICE'  OR B.CRITERIA='DIFF-QTY' ) )";	// B.CRITERIA='DIFF-EXG'
//		System.out.println(k+" :INVOICE-DR-CR: "+queryString); //RG20161125 For fetching debit credit note for all criteria.....
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					Vdrcrflag.add(rset1.getString(1));
					Vinvseqno.add(rset1.getString(2));
					Vctype.add(rset1.getString(3));
					VDrCrDocNo.add(rset1.getString(4)); //SB20160526
					VDrCrFlag.add(rset1.getString(5)); //SB20160526
					VDrCrDt.add(rset1.getString(6)); //SB20160601
					VDrCrAprvBy.add(rset1.getString(8)); //SB20160602
					VDrCrAprvDt.add(rset1.getString(9)); //SB20160602
					VDrcrCriteria.add(rset1.getString(10)); //RG20161210 for criteria
					VRemark.add(rset1.getString(11)); //RG20161210 for remark for rev_inv
					//invoice_SN_Start_Dt.add(invoice_SN_Start_Dt.elementAt(k));
				}else{
					Vdrcrflag.add("-");
					Vinvseqno.add("-");
					Vctype.add("-");
					VDrCrDocNo.add("-");//SB20160526
					VDrCrFlag.add("-");//SB20160526
					VDrCrDt.add(""); //SB20160601
					VDrCrAprvBy.add("0"); //SB20160602
					VDrCrAprvDt.add(""); //SB20160602
					VDrcrCriteria.add("");
					VRemark.add("");
					//invoice_SN_Start_Dt.add("");
				}
			}
			
			////RG20161212 For change in quantity .............
			for(int i=0;i<invoice_Customer_Cd.size();i++){
				int cnt1=0;
				Vector qty_dt=new Vector(); 
				String nom_rev_no="";
				flag="";
				queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
				"FINANCIAL_YEAR,CRITERIA,REMARK FROM FMS7_DR_CR_NOTE Where customer_cd='"+invoice_Customer_Cd.elementAt(i)+"' AND " +
				" FINANCIAL_YEAR='"+hlpl_Invoice_Financial_Year_Arr.elementAt(i)+"' AND CRITERIA='DIFF-QTY' and (dr_cr_flag='cr' OR dr_cr_flag='dr')" +
				" and hlpl_inv_seq_no='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' ";
				////System.out.println("queryString----"+queryString);
				rset1=stmt1.executeQuery(queryString);
				while(rset1.next()){
					////System.out.println("inside query");
					remark=rset1.getString(9);
			//SB20170127		String dt=remark.substring(48,remark.length()-1);
					String dt=remark.substring(48,remark.length()-1); //SB20170127
					
					String date[]=dt.split(",");
					for(int j=0;j<date.length;j++){  
					//String dates=date[cnt];
					qty_dt.add(date[j]);
					}
					for(int j=0;j<qty_dt.size();j++){  
					queryString = "SELECT NVL(MAX(nom_rev_no),'-1') FROM DLNG_DAILY_ALLOCATION_DTL " +
								  "WHERE sn_no='"+rset1.getString(1)+"' " +
								   "AND fgsa_no='"+rset1.getString(2)+"' " +
								   "AND customer_cd='"+rset1.getString(3)+"' " +
								   "AND plant_seq_no='"+rset1.getString(4)+"' " +
								   "AND contract_type='"+rset1.getString(5)+"' " +
								  // "AND nom_rev_no="+rev_no+" " +
								   "AND gas_dt=TO_DATE('"+qty_dt.elementAt(j)+"','DD/MM/YYYY')";
//					//System.out.println("----query-->"+queryString);
					rset=stmt.executeQuery(queryString);
					while(rset.next()){
						nom_rev_no=rset.getString(1);
					}
					
					String query="select * from FMS7_DAILY_ALLOCATION_DEL " +
				 			"WHERE sn_no='"+rset1.getString(1)+"' " +
						   "AND fgsa_no='"+rset1.getString(2)+"' " +
						   "AND customer_cd='"+rset1.getString(3)+"' " +
						   "AND plant_seq_no='"+rset1.getString(4)+"' " +
						   "AND contract_type='"+rset1.getString(5)+"' " +
						   "AND nom_rev_no="+nom_rev_no+" " +
						   "AND gas_dt=TO_DATE('"+qty_dt.elementAt(j)+"','DD/MM/YYYY')";
//					//System.out.println("---query---"+query);
					rset=stmt.executeQuery(query);
					if(rset.next()){
						cnt1++;
					}
					}
					if(cnt1==qty_dt.size() && cnt1>0){
						flag="Y";
					}
					
					if(qty_dt.size()>0 && cnt1==0){
						flag="N";
					}
				}
				flagqty.add(flag);  //RG20161212 If quanitty is changed then flag will be Y else N...............
				////System.out.println("--flag---"+flagqty.size()+"---"+flagqty);
			}
			
		}catch(Exception e)
		{
			//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Following Function Fetches Invoice Preperation Details Records On Selected Year & Month Basis ...
	//Following Function Has Been Introduced By Samik Shah On 20th May, 2010 ...
	public void fetchInvoicePreperationDetails_OLD()
	{}//End Of Method fetchInvoicePreperationDetails() ...
	
	public void fetchInvoicetracking()
	{
		methodName = "fetchInvoicetracking()";
		try 
		{
			String period_start_dt = "";
			String period_end_dt = "";
			String period_end_dt_FIXED = ""; 
			
			////System.out.println("billCycle = "+billCycle);
			
			if(billCycle.equals("2") || billCycle.equals("1")) //1st-Fortnightly or 2nd-Fortnightly Invoice ...
			{
				if(billCycle.equals("2")) //2nd-Fortnightly Invoice ...
				{
					period_start_dt = "16"+"/"+month+"/"+year;
				}
				else  //1st-Fortnightly Invoice ...
				{
					period_start_dt = "01"+"/"+month+"/"+year;
				}
				
				if(billCycle.equals("1"))  //1st-Fortnightly Invoice ...
				{
					period_end_dt = "15"+"/"+month+"/"+year;
				}
				else  //2nd-Fortnightly Invoice ...
				{
					queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
					////System.out.println("FMS7-INV-LIST:QRY1001:SELECT:DUAL: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						period_end_dt = rset.getString(1);
					}
				}
			}
			else if(billCycle.equals("3")) //1st-Weekly Invoice ...
			{
				period_start_dt = "01"+"/"+month+"/"+year;
				period_end_dt = "07"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("4")) //2nd-Weekly Invoice ...
			{
				period_start_dt = "08"+"/"+month+"/"+year;
				period_end_dt = "14"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("5")) //3rd-Weekly Invoice ...
			{
				period_start_dt = "15"+"/"+month+"/"+year;
				period_end_dt = "21"+"/"+month+"/"+year;
			}
			else if(billCycle.equals("6")) //4th-Weekly Invoice ...
			{
				period_start_dt = "22"+"/"+month+"/"+year;
				
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
				////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					period_end_dt = rset.getString(1);
				}
			}
			else if(billCycle.equals("7"))  //Monthly Invoice ...
			{
				period_start_dt = "01"+"/"+month+"/"+year;
				
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
				////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					period_end_dt = rset.getString(1);
				}
			}
			
			int fin_yr = Integer.parseInt(year);
			int fin_mon = Integer.parseInt(month);
			String financial_year = "";
			int inv_no = 0;
			String invoice_no = "0001";
			
			if(fin_mon>3)
			{
				financial_year = ""+fin_yr+"-"+(fin_yr+1);
			}
			else
			{
				financial_year = ""+(fin_yr-1)+"-"+fin_yr;
			}
			
			////System.out.println("financial_year = "+financial_year);
			
			//Procedure For Finding Out HLPL Invoice Sequence Number For The Specific Financial Year ...
			if(cont_type.trim().equals("R"))
			{
			//	queryString = "SELECT NVL(MAX(hlpl_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
			//			  	  "WHERE financial_year='"+financial_year+"' " +
			//			  	  "AND contract_type='"+contract_type.trim()+"'";

				queryString = "	SELECT MAX(no) AS no FROM "+
				"( "+
				" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_year+"' AND contract_type='R' "+
				" UNION ALL "+
				" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM FMS7_INVOICE_TRACKING_DTL WHERE financial_year='"+financial_year+"' AND contract_type='R' "+
				" )  NewTable ";
				
				
			}
			else
			{
			//	queryString = "SELECT NVL(MAX(hlpl_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
			 // 	  			  "WHERE financial_year='"+financial_year+"' " +
			//  	  			  "AND (contract_type='S' OR contract_type='L')";
				queryString = "	SELECT MAX(no) AS no FROM "+
				"( "+
				" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_year+"' AND (contract_type='S' OR contract_type='L') "+
				" UNION ALL "+
				" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM FMS7_INVOICE_TRACKING_DTL WHERE financial_year='"+financial_year+"' AND (contract_type='S' OR contract_type='L') "+
				" )  NewTable ";
			}
			
			////System.out.println("Query For Fetching MAX HLPL Invoice SEQ NO From DLNG_INVOICE_MST Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				inv_no = Integer.parseInt(rset.getString(1))+1;
				
				if(inv_no<10)
				{
					invoice_no = "000"+inv_no;
				}
				else if(inv_no<100)
				{
					invoice_no = "00"+inv_no;
				}
				else if(inv_no<1000)
				{
					invoice_no = "0"+inv_no;
				}
				else
				{
					invoice_no = ""+inv_no;
				}
				hlpl_Invoice_Seq_No = invoice_no+"/"+financial_year;
			}
			else
			{
				hlpl_Invoice_Seq_No = invoice_no+"/"+financial_year;
			}
			
			
			seq_no=(inv_no)+"";
					
			queryString = "SELECT hlpl_inv_seq_no,CUSTOMER_CD,PLANT_SEQ_NO FROM DLNG_INVOICE_MST " +
						  "WHERE period_start_dt between TO_DATE('"+period_start_dt+"','DD/MM/YYYY') and TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND " +
						  "period_end_dt between TO_DATE('"+period_start_dt+"','DD/MM/YYYY') and TO_DATE('"+period_end_dt+"','DD/MM/YYYY') and CONTRACT_TYPE='"+cont_type+"' ";
			////System.out.println(queryString);
			rset2 = stmt2.executeQuery(queryString);
			while(rset2.next())
			{
				hlpl_Invoice_Seq_No_Arr.add(rset2.getString(1)==null?"":rset2.getString(1));
				VCUSTOMER_CD.add(rset2.getString(2)==null?"":rset2.getString(2));
				invoice_Customer_Plant_Seq_No.add(rset2.getString(3)==null?"":rset2.getString(3));
			}
						
			for(int i=0;i<hlpl_Invoice_Seq_No_Arr.size();i++)
			{
			queryString1 = "SELECT hlpl_inv_seq_no,financial_year,cust_inv_seq_no,NET_AMT_INR,TOTAL_QTY,to_char(DUE_DT,'dd/mm/yy'),to_char(INVOICE_DT,'dd/mm/yy') " +
			   ",CONTRACT_TYPE,REMARK_3,SN_NO,FGSA_NO,FGSA_REV_NO FROM DLNG_INVOICE_MST " +
			   "WHERE hlpl_inv_seq_no='"+hlpl_Invoice_Seq_No_Arr.elementAt(i)+"' and period_start_dt between TO_DATE('"+period_start_dt+"','DD/MM/YYYY') and TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND " +
						  "period_end_dt between TO_DATE('"+period_start_dt+"','DD/MM/YYYY') and TO_DATE('"+period_end_dt+"','DD/MM/YYYY') and CONTRACT_TYPE='"+cont_type+"'";
			////System.out.println(queryString1);
			rset2 = stmt2.executeQuery(queryString1);
				if(rset2.next())
				{
					
					hlpl_Invoice_Financial_Year_Arr.add(rset2.getString(2)==null?"":rset2.getString(2));
					inv_net_Amt_INR.add(rset2.getString(4)==null?"":rset2.getString(4));
					Vinv_qty.add(rset2.getString(5)==null?"":rset2.getString(5));
					Vdue_dt.add(rset2.getString(6)==null?"":rset2.getString(6));
					Vinv_dt.add(rset2.getString(7)==null?"":rset2.getString(7));
					Vcont_type.add(rset2.getString(8)==null?"":rset2.getString(8));
					Vremarks.add(rset2.getString(9)==null?"":rset2.getString(9));
					Vsn_no.add(rset2.getString(10)==null?"":rset2.getString(10));
					Vfgsa_no.add(rset2.getString(11)==null?"":rset2.getString(11));
					Vsn_no_rev.add(rset2.getString(12)==null?"":rset2.getString(12));
					Vinv_flag.add("I");
					
					String inv_seq_number = "";
					
					if(rset2.getString(1)!=null && rset2.getString(2)!=null)
					{
						int seq_no = Integer.parseInt(rset2.getString(1));
						
						if(seq_no<10)
						{
							inv_seq_number = "000"+seq_no+"/"+rset2.getString(2);
						}
						else if(seq_no<100)
						{
							inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
						}
						else if(seq_no<1000)
						{
							inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
						}
						else
						{
							inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
						}
					}
					else
					{
						inv_seq_number = "";
					}
					
					hlpl_Invoice_Actual_Seq_No.add(inv_seq_number);
					
					inv_seq_number = "";
					if(rset2.getString(3)!=null && rset2.getString(2)!=null)
					{
						int seq_no = Integer.parseInt(rset2.getString(3));
						
						if(seq_no<10)
						{
							inv_seq_number = "00"+seq_no+"/"+rset2.getString(2);
						}
						else if(seq_no<100)
						{
							inv_seq_number = "0"+seq_no+"/"+rset2.getString(2);
						}
						else
						{
							inv_seq_number = ""+seq_no+"/"+rset2.getString(2);
						}
					}
					else
					{
						inv_seq_number = "";
					}
					
					customer_Invoice_Actual_Seq_No.add(inv_seq_number);
				}
				else
				{
					hlpl_Invoice_Seq_No_Arr.add("");
					hlpl_Invoice_Financial_Year_Arr.add("");
					hlpl_Invoice_Actual_Seq_No.add("");
					customer_Invoice_Actual_Seq_No.add("");
					inv_net_Amt_INR.add("");
					Vinv_qty.add("");
					Vdue_dt.add("");
					Vinv_dt.add("");
					Vcont_type.add("");
					Vremarks.add("");
					Vsn_no.add("");
					Vsn_no_rev.add("");
					Vinv_flag.add("I");
				}
				
				
				queryString2="select CUSTOMER_ABBR from FMS7_CUSTOMER_MST where CUSTOMER_CD='"+VCUSTOMER_CD.elementAt(i)+"'";
				rset=stmt.executeQuery(queryString2);
				if(rset.next())
				{
					Vcust_abr.add(rset.getString(1));
				}
				
				
				queryString3="select CARGO_REF_NO from FMS7_RE_GAS_CARGO_DTL where CUSTOMER_CD='"+VCUSTOMER_CD.elementAt(i)+"' and RE_GAS_NO='"+Vfgsa_no.elementAt(i)+"' and" +
						" RE_GAS_REV_NO='"+Vsn_no_rev.elementAt(i)+"' and CARGO_SEQ_NO='"+Vsn_no.elementAt(i)+"'";
				rset1=stmt1.executeQuery(queryString3);
				if(rset1.next())
				{
					Vcargo_ref_no.add(rset1.getString(1));
				}
				else
				{
					Vcargo_ref_no.add("");
				}
				
				queryString5="select PLANT_ABBR from FMS7_CUSTOMER_PLANT_DTL where CUSTOMER_CD='"+VCUSTOMER_CD.elementAt(i)+"' and SEQ_NO='"+invoice_Customer_Plant_Seq_No.elementAt(i)+"'";
				rset=stmt.executeQuery(queryString5);
				if(rset.next())
				{
				 invoice_Customer_Plant_Nm.add(rset.getString(1));
				}
				
			}	
					
			queryString4="select SN_NO,to_char(INV_DT,'dd/mm/yy'),PARTY_ABR,TOTAL_QTY,NET_AMT_INR,to_char(DUE_DT,'dd/mm/yy'),CARGO_REF_NO,BOE_NO,INV_FLAG,INV_SEQ_NO,CONTRACT_TYPE from " +
					"FMS7_INVOICE_TRACKING_DTL where month='"+month+"' and year='"+year+"' and BILL_CYCLE='"+billCycle+"' and CONTRACT_TYPE='"+cont_type+"'";
			////System.out.println(queryString4);
			rset=stmt.executeQuery(queryString4);
			while(rset.next())
			{
				Vsn_no.add(rset.getString(1)==null?"":rset.getString(1));
				Vinv_dt.add(rset.getString(2)==null?"":rset.getString(2));
				Vcust_abr.add(rset.getString(3)==null?"":rset.getString(3));
				Vinv_qty.add(rset.getString(4)==null?"":rset.getString(4));
				inv_net_Amt_INR.add(rset.getString(5)==null?"":rset.getString(5));
				Vdue_dt.add(rset.getString(6)==null?"":rset.getString(6));
				Vcargo_ref_no.add(rset.getString(7)==null?"":rset.getString(7));
				Vremarks.add(rset.getString(8)==null?"":rset.getString(8));
				Vinv_flag.add(rset.getString(9)==null?"":rset.getString(9));
				hlpl_Invoice_Actual_Seq_No.add(rset.getString(10)==null?"":rset.getString(10));
				Vcont_type.add(rset.getString(11)==null?"":rset.getString(11));
				invoice_Customer_Plant_Nm.add("-");
			}
			
			
		}
		catch(Exception e)
		{
			//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchInvoicetracking() ...
	

	String refresh_flag="N";
	String invoice_total_tax="0";
	String advancedFlg="NA";
	public String getAdvancedFlg() {
		return advancedFlg;
	}

	Vector multiple_adv_inv_no = new Vector();
	Vector multiple_adv_inv_dt = new Vector();
	String GST_INVOICE_SEQ_NO = "";
	
	public void fetchInvoiceDetails()throws SQLException,IOException		//BK20160211
	{	
		methodName = "fetchInvoiceDetails()";
		try 
		{
			String exch_rate_dt = "";
			
			queryString = "SELECT SERVICE_NM,SERVICE_CD,RULE_REMARK,SERVICE_DESC FROM FMS7_LNG_SALES_MAPPING WHERE "
					+ "CONTRACT_TYPE='"+contract_type+"' ";
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				sac_code = rset.getString(2)==null?"":rset.getString(2);
				sac_name = rset.getString(1)==null?"":rset.getString(1);
				rule_remark = rset.getString(3)==null?"":rset.getString(3);
				service_desc = rset.getString(4)==null?"":rset.getString(4);
			}
			
			queryString = "SELECT addr,city,pin,NVL(STATE,'N/A') " +
					  "FROM FMS7_SUPPLIER_ADDRESS_MST A " +
					  "WHERE A.supplier_cd=1 AND A.address_type='R' AND " +
					  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
					  "WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND " +
					  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
		System.out.println("Supplier Address Fetch Query = "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			contact_Suppl_Person_Address = rset.getString(1)==null?"":rset.getString(1);
			contact_Suppl_Person_City = rset.getString(2)==null?"":rset.getString(2);
			contact_Suppl_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
			contact_Suppl_State = rset.getString(4)==null?"":rset.getString(4);
			
			if(!contact_Suppl_State.equals("") && !contact_Suppl_State.equals("N/A")) {
				queryString = "SELECT NVL(STATE_CODE,'N/A') FROM STATE_MST WHERE UPPER(STATE_NM) = '"+contact_Suppl_State.toUpperCase()+"' ";
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					contact_Suppl_State_Code = rset.getString(1);
				}
			}
		}
		
		
			/*ADDED ON 13-05-2015 FOR IF TAX INVOICE IS NOT AVAILABLE WHILE PREPARING INVOICE, IT WILL GIVE ALERT*/
			
//			queryString = "SELECT A.customer_cd,A.gst_tin_no,A.cst_tin_no," +
//					  "TO_CHAR(A.gst_tin_dt,'DD-MM-YYYY'),TO_CHAR(A.cst_tin_dt,'DD-MM-YYYY') " +
//					  "FROM FMS7_CUSTOMER_PLANT_TAX_NOS A "+
//					  "WHERE A.customer_cd="+customer_cd+" AND A.plant_seq_no="+customer_plant_seq_no+"";
//			rset = stmt.executeQuery(queryString);
//			if(rset.next())
//			{
//				contact_Customer_GST_NO = rset.getString(2)==null?"":rset.getString(2);
//				contact_Customer_CST_NO = rset.getString(3)==null?"":rset.getString(3);
//				contact_Customer_GST_DT = rset.getString(4)==null?"":rset.getString(4);
//				contact_Customer_CST_DT = rset.getString(5)==null?"":rset.getString(5);
//			}
			
			//BK20160211
			queryString = "SELECT supplier_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY'),TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,PAN_NO,TO_CHAR(PAN_ISSUE_DT,'DD-MM-YYYY'),  " +
						 "GSTIN_NO,TO_CHAR(GSTIN_DT,'DD/MM/YYYY')  " + //RS01062017
						  "FROM FMS7_SUPPLIER_MST A " +
						  "WHERE A.supplier_cd=1 AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
	//		//System.out.println("Supplier Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Suppl_Name = rset.getString(1)==null?"":rset.getString(1);
				contact_Suppl_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Suppl_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Suppl_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Suppl_CST_DT = rset.getString(5)==null?"":rset.getString(5);
				contact_Suppl_Service_Tax_NO = rset.getString(6)==null?"":rset.getString(6);
				
				contact_Suppl_PAN_NO = rset.getString(7)==null?"":rset.getString(7);	//BK20160211
				contact_Suppl_PAN_DT = rset.getString(8)==null?"":rset.getString(8);	//BK20160211
				
				contact_Suppl_GSTIN_NO = rset.getString(9)==null?"":rset.getString(9);
				contact_Suppl_GSTIN_DT = rset.getString(10)==null?"":rset.getString(10);
			}
			
			Vector stat_no_temp=new Vector();
			Vector stat_nm_temp=new Vector();
			
			
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{
				//BK20160211
				queryString =  "select stat_cd,stat_nm from fms7_govt_stat_no where (stat_type='S' OR STAT_TYPE='G') order by stat_cd";
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					stat_no_temp.add(rset.getString(1));
					stat_nm_temp.add(rset.getString(2));
				}
				
				//BK20160211
				for(int i=0;i<stat_no_temp.size();i++)
				{
					int count_no=0;
				queryString = "SELECT A.stat_no, TO_CHAR(A.eff_dt,'DD-MM-YYYY'), B.stat_nm, B.stat_cd " +
							  "FROM FMS7_CUSTOMER_PLANT_TAX_CDS A, FMS7_GOVT_STAT_NO B " +
							  "WHERE A.stat_cd=B.stat_cd AND A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND (B.stat_type='S' OR B.STAT_TYPE='G') "
							  + " and b.stat_cd= '"+stat_no_temp.elementAt(i)+"'" +
							  "ORDER BY A.stat_cd";
				
				////System.out.println("Customer Plant's Tax Names Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					count_no++;
					String stat_nm = rset.getString(3)==null?"":rset.getString(3);
					String stat_no = rset.getString(1)==null?"":rset.getString(1);
					String stat_eff_dt = rset.getString(2)==null?"":rset.getString(2);
					String stat_cd = rset.getString(4)==null?"":rset.getString(4);
					
					if(!stat_nm.trim().equals(""))// && !stat_no.trim().equals("") && !stat_eff_dt.trim().equals("")
					{
						vSTAT_NO.add(stat_no.trim());
						vSTAT_NM.add(stat_nm.trim());
						vSTAT_CD.add(stat_cd.trim());
					}
					else
					{
						vSTAT_NO.add("");
						vSTAT_NM.add(""+stat_nm_temp.elementAt(i));
						vSTAT_CD.add(""+stat_no_temp.elementAt(i));
					}
				}
				if(count_no==0)
				{
					vSTAT_NO.add("");
					vSTAT_NM.add(""+stat_nm_temp.elementAt(i));
					vSTAT_CD.add(""+stat_no_temp.elementAt(i));
				}
				}
			}
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				//BK20160211
				queryString =  "select stat_cd,stat_nm from fms7_govt_stat_no where (stat_type='R' OR STAT_TYPE='G') order by stat_cd";
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					stat_no_temp.add(rset.getString(1));
					stat_nm_temp.add(rset.getString(2));
				}
				
				//BK20160211
				for(int i=0;i<stat_no_temp.size();i++)
				{
					int count_no=0;
				queryString = "SELECT A.stat_no, TO_CHAR(A.eff_dt,'DD-MM-YYYY'), B.stat_nm, B.stat_cd " +
							  "FROM FMS7_CUSTOMER_PLANT_TAX_CDS A, FMS7_GOVT_STAT_NO B " +
							  "WHERE A.stat_cd=B.stat_cd AND A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND (B.stat_type='R' OR B.STAT_TYPE='G') " 
							  + " and b.stat_cd= '"+stat_no_temp.elementAt(i)+"'" +
							  "ORDER BY A.stat_cd";
				
			System.out.println("Customer Plant's Tax Names Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					count_no++;
					String stat_nm = rset.getString(3)==null?"":rset.getString(3);
					String stat_no = rset.getString(1)==null?"":rset.getString(1);
					String stat_eff_dt = rset.getString(2)==null?"":rset.getString(2);
					String stat_cd = rset.getString(4)==null?"":rset.getString(4);
					
					if(!stat_nm.trim().equals(""))// && !stat_no.trim().equals("") && !stat_eff_dt.trim().equals("")
					{
						vSTAT_NO.add(stat_no.trim());
						vSTAT_NM.add(stat_nm.trim());
						vSTAT_CD.add(stat_cd.trim());
					}
					else
					{
						vSTAT_NO.add("");
						vSTAT_NM.add(""+stat_nm_temp.elementAt(i));
						vSTAT_CD.add(""+stat_no_temp.elementAt(i));
					}
				}
				if(count_no==0)
				{
					vSTAT_NO.add("");
					vSTAT_NM.add(""+stat_nm_temp.elementAt(i));
					vSTAT_CD.add(""+stat_no_temp.elementAt(i));
				}
				}
			}
			
			/***********************************************************************************/
			
			if(activity.equalsIgnoreCase("update"))
			{
				queryString = "SELECT CONTACT_PERSON_CD, TO_CHAR(EXCHG_RATE_DT,'DD/MM/YYYY'), " +
							  "EXCHG_RATE_VALUE, CUST_INV_SEQ_NO, " +
							  "TO_CHAR(INVOICE_DT,'DD/MM/YYYY'), TO_CHAR(DUE_DT,'DD/MM/YYYY'), " +
							  "REMARK_1, REMARK_2, EXCHG_RATE_INDEX, REMARK_3, " +
							  "INV_CUR_FLAG, " + //20141016
							  "MAPPING_ID,ADV_INV_NO,TO_CHAR(ADV_INV_DT ,'DD/MM/YYYY'),"
							  + "TO_CHAR(USER_DEFINED_DAY,'DD/MM/YYYY'),ADV_ADJ_FLG,NEW_INV_SEQ_NO,REMARK_SPECIFICATION " + //20141031 ADDED FOR LTCORA AND CN
							  "FROM DLNG_INVOICE_MST WHERE HLPL_INV_SEQ_NO='"+hlplInvoiceNo+"' AND " +
							  "FINANCIAL_YEAR='"+invFinancialYear+"' AND CONTRACT_TYPE='"+contract_type+"' AND FLAG='Y'";
//				//System.out.println("HS--Query for Fetching Contact Person, Customer Invoice Seq. NO & Exchange Rate Value of Already Saved Invoice = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					contactPersonCd = rset.getString(1)==null?"":rset.getString(1);
					exch_rate_dt = rset.getString(2)==null?"":rset.getString(2);
					customer_Invoice_Exchg_Rate2 = rset.getString(3)==null?"":rset.getString(3);
					customer_Invoice_Seq_No = rset.getString(4)==null?"":rset.getString(4);
					customer_Invoice_DT = rset.getString(5)==null?"":rset.getString(5);
					if(page_refresh_flag.trim().equalsIgnoreCase("N"))
					{
						invoice_date = customer_Invoice_DT;
					}
					customer_Invoice_Due_DT = rset.getString(6)==null?"":rset.getString(6);
					remark_1 = rset.getString(7)==null?"":rset.getString(7).trim();
					remark_2 = rset.getString(8)==null?"":rset.getString(8).trim();
					exchg_rate_ind = Integer.parseInt(rset.getString(9)==null?"-1":rset.getString(9));
					remark_3 = rset.getString(10)==null?"":rset.getString(10).trim();
////					RG 20140909
//					modifyadjremark = rset.getString(11)==null?"":rset.getString(11).trim();
					inv_cur_flag=rset.getString(11)==null?"N":rset.getString(11).trim();
					customer_inv_mapping_id=rset.getString(12)==null?"":rset.getString(12).trim();
					customer_ADV_INV_NO=rset.getString(13)==null?"":rset.getString(13).trim();
					customer_ADV_INV_DT=rset.getString(14)==null?"":rset.getString(14).trim();
					advancedFlg=rset.getString(16)==null?"NA":rset.getString(16).trim();
					//GST INVOICE NO
					GST_INVOICE_SEQ_NO = rset.getString(17)==null?"":rset.getString(17);
					rec_remark = rset.getString(18)==null?"":rset.getString(18);
					///ADDED ON 13-05-2015 FOR MODIFYING INVOICE ON USER DAY */
					
					if(refresh_flag.equals("N"))
					{
						particular_date=rset.getString(15)==null?"":rset.getString(15).trim();
					}
					
					if(customer_Invoice_Seq_No.trim().equals(""))
					{
						customer_Invoice_Seq_No = "00000";
					}
					else
					{
						if(Integer.parseInt(customer_Invoice_Seq_No)<10)
						{
							customer_Invoice_Seq_No = "0000"+customer_Invoice_Seq_No;
						}
						else if(Integer.parseInt(customer_Invoice_Seq_No)<100)
						{
							customer_Invoice_Seq_No = "000"+customer_Invoice_Seq_No;
						}
						else if(Integer.parseInt(customer_Invoice_Seq_No)<1000)
						{
							customer_Invoice_Seq_No = "00"+customer_Invoice_Seq_No;
						}
						else if(Integer.parseInt(customer_Invoice_Seq_No)<10000)
						{
							customer_Invoice_Seq_No = "0"+customer_Invoice_Seq_No;
						}
						else 
						{
							customer_Invoice_Seq_No = ""+customer_Invoice_Seq_No;
						}
					}
				}
				
				///ADDED BY RS 20170412 FOR MULTIPLE ADVANCE INVOICE DATA
				String query = "SELECT ADV_INV_NO, NVL(TO_CHAR(ADV_INV_DT,'DD/MM/YYYY'),'') "
						+ "FROM FMS8_ADV_INV_DTL "
						+ "WHERE FINANCIAL_YEAR = '"+invFinancialYear+"' AND CONTRACT_TYPE = '"+contract_type+"' "
						+ "AND HLPL_INV_SEQ_NO = '"+hlplInvoiceNo+"' AND ADV_INV_NO != '"+customer_ADV_INV_NO+"' AND FLAG='Y'";
				rset = stmt.executeQuery(query);
				while(rset.next())
				{
					multiple_adv_inv_no.add(rset.getString(1).replaceAll(" ", "@#@#"));
					multiple_adv_inv_dt.add(rset.getString(2)==null?"":rset.getString(2));
				}
				//////////////
			}
			
//			System.out.println("GST_INVOICE_SEQ_NO b4"+GST_INVOICE_SEQ_NO);
			
			if(contract_type.equalsIgnoreCase("S"))
			{
				queryString = "SELECT SN_REF_NO " +
							  "FROM DLNG_SN_MST WHERE " +
				  			  "flsa_no="+fgsa_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_ref_no IS NOT NULL AND " +
				  			  "customer_cd="+customer_cd+"";
		//		//System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  " || ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  " || ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  " FROM DLNG_FLSA_MST WHERE " +
							  " FLSA_NO ="+fgsa_no+" AND " +
							  " REV_NO ="+fgsa_rev_no+" AND " +
							  " customer_cd="+customer_cd+" " +
							  " ORDER BY rev_no DESC";
//				System.out.println("Signing Date Fetch Query For FGSA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM DLNG_SN_MST WHERE " +
				  			  "flsa_no="+fgsa_no+" AND flsa_rev_no="+fgsa_rev_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+customer_cd+"";
		//		//System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT LOA_REF_NO " +
							  "FROM DLNG_LOA_MST WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND LOA_REF_NO IS NOT NULL AND " +
				  			  "customer_cd="+customer_cd+"";
		//		//System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_TENDER_MST WHERE " +
							  "tender_no="+fgsa_no+" AND " +
							  "customer_cd="+customer_cd+"";
		//		//System.out.println("Signing Date Fetch Query For TENDER Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM DLNG_LOA_MST WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+customer_cd+"";
		//		//System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("R"))
			{}
			else if(contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
			{}
			else if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{}
			
			/************************************/
			queryString="SELECT ADV_INV_NO,TO_CHAR(ADV_INV_DT,'DD/MM/YYYY') FROM DLNG_INVOICE_MST WHERE"
					+ " CUSTOMER_CD='"+customer_cd+"' AND FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' "
					+ "AND SN_NO='"+sn_no+"' AND SN_REV_NO='"+sn_rev_no+"' AND "
					+ "CONTRACT_TYPE='"+contract_type+"' AND FLAG='Y'";
			
			if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
			{
				queryString+=" and mapping_id='"+customer_inv_mapping_id+"'";
			}
		System.out.println("..........."+queryString);
			rset1=stmt.executeQuery(queryString);
			while(rset1.next())
			{
				String tempNo=rset1.getString(1)==null?"":rset1.getString(1);
				if(!tempNo.equals(""))
				{
					customer_ADV_INV_NO=tempNo;
					customer_ADV_INV_DT=rset1.getString(2);
					break;
				}
			}
			////System.out.println("---boe_no: ------"+boe_no);
			/***********************************/
			
			
			queryString = "SELECT customer_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY'),TO_CHAR(cst_tin_dt,'DD-MM-YYYY') " +
						  "FROM FMS7_CUSTOMER_MST A " +
						  "WHERE A.customer_cd="+customer_cd+" AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
	//		//System.out.println("Customer Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Customer_Name = rset.getString(1)==null?"":rset.getString(1);
				contact_Customer_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Customer_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Customer_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Customer_CST_DT = rset.getString(5)==null?"":rset.getString(5);
			}
			
			queryString = "SELECT A.customer_cd,A.gst_tin_no,A.cst_tin_no," +
						  "TO_CHAR(A.gst_tin_dt,'DD-MM-YYYY'),TO_CHAR(A.cst_tin_dt,'DD-MM-YYYY') " +
						  "FROM FMS7_CUSTOMER_PLANT_TAX_NOS A "+
						  "WHERE A.customer_cd="+customer_cd+" AND A.plant_seq_no="+customer_plant_seq_no+"";
	//		//System.out.println("Customer Plant's GST & CST Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Customer_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Customer_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Customer_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Customer_CST_DT = rset.getString(5)==null?"":rset.getString(5);
			}
				
			
				////////////////////////////////////////////////////////////////////
			
			//BK20160211
			queryString = "SELECT supplier_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY'),TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),PAN_NO,TO_CHAR(PAN_ISSUE_DT,'DD-MM-YYYY'), " +
						"GSTIN_NO,TO_CHAR(GSTIN_DT,'DD/MM/YYYY') " +
						  "FROM FMS7_SUPPLIER_MST A " +
						  "WHERE A.supplier_cd=1 AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
	//		//System.out.println("Supplier Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Suppl_Name = rset.getString(1)==null?"":rset.getString(1);
				contact_Suppl_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Suppl_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Suppl_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Suppl_CST_DT = rset.getString(5)==null?"":rset.getString(5);
				
				contact_Suppl_PAN_NO = rset.getString(6)==null?"":rset.getString(6);	//BK20160211
				contact_Suppl_PAN_DT = rset.getString(7)==null?"":rset.getString(7);	//BK20160211
				
				contact_Suppl_GSTIN_NO = rset.getString(8)==null?"":rset.getString(8);	//RS01062017
				contact_Suppl_GSTIN_DT = rset.getString(9)==null?"":rset.getString(9);	//RS01062017
			}
			
			String temp_plant_no = "P"+customer_plant_seq_no.trim();
			queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' ') " +
						  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
						  "WHERE A.customer_cd="+customer_cd+" AND A.def_inv_flag='Y' AND " +
						  "A.active_flag='Y' AND (A.addr_flag='B' OR A.addr_flag='R' OR " +
						  "A.addr_flag='C' OR A.addr_flag='"+temp_plant_no+"') AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
						  "B.eff_dt<=TO_DATE('"+invoice_date+"','DD/MM/YYYY'))";
	//		//System.out.println("Customer Contact Person Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				invoice_Customer_Contact_Cd.add(rset.getString(1));
				if(rset.getString(3).equals(" "))
				{
					invoice_Customer_Contact_Nm.add(rset.getString(2).trim());
				}
				else
				{
					invoice_Customer_Contact_Nm.add(rset.getString(2).trim()+" ("+rset.getString(3).trim()+")");
				}
			}
			hlpl_Invoice_Seq_No = "";
			customer_Invoice_Seq_No = "";
			String tax_struct_cd = "0";
			
			if(contract_type.equalsIgnoreCase("S"))
			{
				queryString1 = "SELECT exchg_rate_note FROM DLNG_SN_BILLING_DTL " +
							   "WHERE cont_type='S' AND customer_cd="+customer_cd+" AND " +
							   "flsa_no="+fgsa_no+" AND flsa_rev_no="+fgsa_rev_no+" AND " +
							   "sn_no="+sn_no+" AND sn_rev_no="+sn_rev_no+"";
				
		//		//System.out.println("Query To FindOut SN Exchange Rate Note = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				
				if(rset1.next())
				{
					invoice_Exchg_Rate_Note = rset1.getString(1)==null?"":rset1.getString(1);
				}
				else
				{
					queryString2 = "SELECT exchg_rate_note FROM FMS7_FGSA_BILLING_DTL " +
								   "WHERE cont_type='F' AND customer_cd="+customer_cd+" AND " +
								   "fgsa_no="+fgsa_no+" AND fgsa_rev_no="+fgsa_rev_no+"";
					
		//			//System.out.println("Query To FindOut SN Exchange Rate Note = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					
					if(rset2.next())
					{
						invoice_Exchg_Rate_Note = rset2.getString(1)==null?"":rset2.getString(1);
					}
				}
			}
			else if(contract_type.equalsIgnoreCase("L"))
			{
				queryString1 = "SELECT exchg_rate_note FROM DLNG_SN_BILLING_DTL " +
							   "WHERE cont_type='L' AND customer_cd="+customer_cd+" AND " +
							   "flsa_no="+fgsa_no+" AND flsa_rev_no=0 AND " +
							   "sn_no="+sn_no+" AND sn_rev_no="+sn_rev_no+"";
				
		//		//System.out.println("Query To FindOut LOA Exchange Rate Note = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				
				if(rset1.next())
				{
					invoice_Exchg_Rate_Note = rset1.getString(1)==null?"":rset1.getString(1);
				}
				else
				{
					queryString2 = "SELECT exchg_rate_note FROM FMS7_FGSA_BILLING_DTL " +
								   "WHERE cont_type='T' AND customer_cd="+customer_cd+" AND " +
								   "fgsa_no="+fgsa_no+" AND fgsa_rev_no=0";
					
		//			//System.out.println("Query To FindOut LOA Exchange Rate Note = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					
					if(rset2.next())
					{
						invoice_Exchg_Rate_Note = rset2.getString(1)==null?"":rset2.getString(1);
					}
				}
			}
			else if(contract_type.equalsIgnoreCase("R"))
			{
				queryString1 = "SELECT exchg_rate_note FROM FMS7_RE_GAS_BILLING_DTL " +
							   "WHERE customer_cd="+customer_cd+" AND " +
							   "re_gas_no="+fgsa_no+" AND " +
							   "re_gas_rev_no="+fgsa_rev_no+"";
				
		//		//System.out.println("Query To FindOut RE-GAS Exchange Rate Note = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				
				if(rset1.next())
				{
					invoice_Exchg_Rate_Note = rset1.getString(1)==null?"":rset1.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
			{
				queryString1 = "SELECT EXCHNG_RATE_NOTE FROM FMS8_LNG_REGAS_BILLING_DTL " +
							   "WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND " +
							   "LTCORA_CN_FLAG='L'  " ;
							   //"re_gas_rev_no="+fgsa_rev_no+"";
				
		//		//System.out.println("Query To FindOut LTCORA Exchange Rate Note = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				
				if(rset1.next())
				{
					invoice_Exchg_Rate_Note = rset1.getString(1)==null?"":rset1.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				queryString1 = "SELECT EXCHNG_RATE_NOTE FROM FMS8_LNG_REGAS_BILLING_DTL " +
				   			"WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND " +
				   			"LTCORA_CN_FLAG='C'  " ;
				
		//		//System.out.println("Query To FindOut CN Exchange Rate Note = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				
				if(rset1.next())
				{
					invoice_Exchg_Rate_Note = rset1.getString(1)==null?"":rset1.getString(1);
				}
				else
				{
					////System.out.println("---customer_inv_mapping_id: "+customer_inv_mapping_id);
			///SB20181228: Why -C 		customer_inv_mapping_id="1-2-0-1-0-C";
					String temp_map_id[]=customer_inv_mapping_id.split("-");
					////System.out.println("---temp_map_id: "+temp_map_id.length);
					String temp_map_id1=temp_map_id[0]+"-"+temp_map_id[1]+"-"+temp_map_id[2]+"-0-0";
					queryString1 = "SELECT exchng_rate_note FROM FMS8_LNG_REGAS_BILLING_DTL " +
					   "WHERE MAPPING_ID="+temp_map_id1+" AND " +
					   "LTCORA_CN_FLAG='L'  " ;
					
					//System.out.println("Query To FindOut CN Exchange Rate Note = "+queryString1);
					//rset2 = stmt2.executeQuery(queryString1);
					//if(rset2.next())
					{
						//invoice_Exchg_Rate_Note = rset2.getString(1)==null?"":rset2.getString(1);
					}
				}
			}
			
			
			if(invoice_date!=null && !invoice_date.equals("") && !invoice_date.equals(" "))
			{
				int fin_yr = Integer.parseInt(invoice_date.substring(6));
				int fin_mon = Integer.parseInt(invoice_date.substring(3,5));
				String financial_year = "";
				int inv_no = 0;
				String invoice_no = "0001";
				String new_inv_no = "00000";
				
				if(fin_mon>3)
				{
					financial_year = ""+fin_yr+"-"+(fin_yr+1);
				}
				else
				{
					financial_year = ""+(fin_yr-1)+"-"+fin_yr;
				}
				
		//		//System.out.println("financial_year = "+financial_year);
				
				//Procedure For Finding Out HLPL Invoice Sequence Number For The Specific Financial Year ...
				
				String order_by=" ORDER BY HLPL_INV_SEQ_NO ASC";
						
						
				
				String query1="SELECT HLPL_INV_SEQ_NO FROM FMS7_INVOICE_DEL_LOG WHERE CUSTOMER_CD='"+customer_cd+"' AND " +
						"FGSA_NO='"+fgsa_no+"' AND FGSA_REV_NO='"+fgsa_rev_no+"' AND SN_NO='"+sn_no+"' " +
						"AND SN_REV_NO='"+sn_rev_no+"' AND "+ //PLANT_SEQ_NO='"+customer_plant_seq_no+"' AND " +
						"CONTRACT_TYPE='"+contract_type+"' AND FINANCIAL_YEAR='"+financial_year+"' AND PERIOD_END_DT=to_date('"+bill_period_end_dt+"','dd/mm/yyyy') " +
						"AND RESTORE_FLAG='N'  ";
				
				if(contract_type.trim().equals("T") || contract_type.trim().equals("C")) //ADDED FOR LTCORA AND CN
				{
					query1+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
				}
				query1+=order_by;
				
				////System.out.println("MAX...."+query1);
				rset4=stmt4.executeQuery(query1);
				if(rset4.next())
				{
					inv_no = Integer.parseInt(rset4.getString(1));
					if(date_flag) {
						if(inv_no<10)
						{
							invoice_no = "0000"+inv_no;
						}
						else if(inv_no<100)
						{
							invoice_no = "000"+inv_no;
						}
						else if(inv_no<1000)
						{
							invoice_no = "00"+inv_no;
						}
						else if(inv_no<10000)
						{
							invoice_no = "0"+inv_no;
						} else {
							invoice_no = ""+inv_no;
						}
					} else {
						if(inv_no<10)
						{
							invoice_no = "000"+inv_no;
						}
						else if(inv_no<100)
						{
							invoice_no = "00"+inv_no;
						}
						else if(inv_no<1000)
						{
							invoice_no = "0"+inv_no;
						}
						else
						{
							invoice_no = ""+inv_no;
						} 
					}
					////System.out.println("HS-invoice_no---> "+invoice_no);
					hlpl_Invoice_Seq_No = invoice_no+"/"+financial_year;
				}
				else
				{
					if(contract_type.trim().equals("R"))
					{
						queryString = "	SELECT MAX(no) AS no FROM "+
							"("+
							" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_year+"' AND contract_type='R' "+
							" )  NewTable ";
					}
					else if(contract_type.trim().equals("T") || contract_type.trim().equals("C")) //ADDED FOR LTCORA AND CN
					{
							queryString = "	SELECT MAX(no) AS no FROM "+
							"("+
							" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_year+"' "
							+ "AND (contract_type in ('C','T','Y','Z')) AND FLAG!='A' AND SUPPLIER_CD!='2' "+
							" )  NewTable ";
					}
					else
					{
							queryString = "	SELECT MAX(no) AS no FROM "+
							"("+
							" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_year+"' AND (contract_type='S' OR contract_type='L') "+
							" )  NewTable ";
					}
					System.out.println("Max invoice seq no 1---"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						//added by RS 19062017
//						if(date_flag) {
							String temp_inv_no = rset.getString(1);
							String q_new_invoice_no = "SELECT NVL(NEW_INV_SEQ_NO,'') FROM DLNG_INVOICE_MST WHERE "
									+ "HLPL_INV_SEQ_NO='"+temp_inv_no+"' AND FINANCIAL_YEAR = '"+financial_year+"' AND ";
							if(contract_type.equalsIgnoreCase("C") || contract_type.equalsIgnoreCase("T")) {
								//FOR CN LTCORA PERIOD AND OTHER INVOICES AND SUG INVOICE
								q_new_invoice_no += "CONTRACT_TYPE IN ('C','T','Y','Z') AND FLAG!='A' AND SUPPLIER_CD!='2' ";
							} else if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")) {
								// FOR SN AND LOA
								q_new_invoice_no += "CONTRACT_TYPE IN ('S','L','K') ";
							} else {
								//FOR REGAS
								q_new_invoice_no += "CONTRACT_TYPE IN ('R') ";
							}
							System.out.println("=Fetching New Inv Seq No==="+q_new_invoice_no);
							rset1 = stmt1.executeQuery(q_new_invoice_no);
							if(rset1.next()) {
								new_inv_no = rset1.getString(1)==null?"":rset1.getString(1);
								if(!new_inv_no.equals("")) {
									if(contract_type.equals("C")) {
										new_inv_no = new_inv_no.substring(0,5);
									} else {
										new_inv_no = new_inv_no.substring(0,4);
									}
								} else {
									if(contract_type.trim().equals("R"))
									{
										queryString = "	SELECT MAX(no) AS no FROM "+
											"("+
											" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_year+"' AND contract_type='R' AND NEW_INV_SEQ_NO IS NOT NULL "+
											" )  NewTable ";
									}
									else if(contract_type.trim().equals("T") || contract_type.trim().equals("C")) //ADDED FOR LTCORA AND CN
									{
											queryString = "	SELECT MAX(no) AS no FROM "+
											"("+
											" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_year+"' "
											+ "AND (contract_type in ('C','T','Y','Z')) AND NEW_INV_SEQ_NO IS NOT NULL AND FLAG!='A' AND SUPPLIER_CD!='2' "+
											" )  NewTable ";
									}
									else
									{
											queryString = "	SELECT MAX(no) AS no FROM "+
											"("+
											" SELECT NVL(MAX(hlpl_inv_seq_no),'0') as no FROM DLNG_INVOICE_MST WHERE financial_year='"+financial_year+"' AND (contract_type='S' OR contract_type='L') AND NEW_INV_SEQ_NO IS NOT NULL "+
											" )  NewTable ";
									}
//									System.out.println("=Fetching Max No 2==="+queryString);
									rset2 = stmt2.executeQuery(queryString);
									if(rset2.next()) {
										String temp_no = rset2.getString(1)==null?"":rset2.getString(1);
										if(!temp_no.equals("")) {
											String q_new_invoice_no1 = "SELECT NVL(NEW_INV_SEQ_NO,'') FROM DLNG_INVOICE_MST WHERE "
													+ "HLPL_INV_SEQ_NO='"+temp_no+"' AND FINANCIAL_YEAR = '"+financial_year+"' AND ";
											if(contract_type.equalsIgnoreCase("C") || contract_type.equalsIgnoreCase("T")) {
												//FOR CN LTCORA PERIOD AND OTHER INVOICES AND SUG INVOICE
												q_new_invoice_no1 += "CONTRACT_TYPE IN ('C','T','Y','Z') AND FLAG!='A' AND SUPPLIER_CD!='2' ";
											} else if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")) {
												// FOR SN AND LOA
												q_new_invoice_no1 += "CONTRACT_TYPE IN ('S','L','K') ";
											} else {
												//FOR REGAS
												q_new_invoice_no1 += "CONTRACT_TYPE IN ('R') ";
											}
//											System.out.println("=Fetching New Inv Seq No 2==="+q_new_invoice_no1);
											rset1 = stmt1.executeQuery(q_new_invoice_no1);
											if(rset1.next()) {
												new_inv_no = rset1.getString(1)==null?"":rset1.getString(1);
												if(!new_inv_no.equals("")) {
													if(contract_type.equals("C")) {
														new_inv_no = new_inv_no.substring(0,5);
													} else {
														new_inv_no = new_inv_no.substring(0,4);
													}
												}
											}
										}
									}
								}
							} 
							if(new_inv_no.equals("")) {
									new_inv_no = "00000";
							}
							new_inv_no = ""+(Integer.parseInt(new_inv_no)+1);
							if(contract_type.equals("C")) {
								if(new_inv_no.length()==1) {
									new_inv_no = "0000"+new_inv_no;
								} else if(new_inv_no.length()==2) {
									new_inv_no = "000"+new_inv_no;
								} else if(new_inv_no.length()==3) {
									new_inv_no = "00"+new_inv_no;
								} else if(new_inv_no.length()==4) {
									new_inv_no = "0"+new_inv_no;
								} else if(new_inv_no.length()==5) {
									new_inv_no = new_inv_no;
								}
							} else {
								if(new_inv_no.length()==1) {
									new_inv_no = "000"+new_inv_no;
								} else if(new_inv_no.length()==2) {
									new_inv_no = "00"+new_inv_no;
								} else if(new_inv_no.length()==3) {
									new_inv_no = "0"+new_inv_no;
								} else if(new_inv_no.length()==4) {
									new_inv_no = new_inv_no;
								}
							}
							
							if(activity.equals("insert"))
//								GST_INVOICE_SEQ_NO = new_inv_no + "/" + financial_year.substring(0,5)+financial_year.substring(7,9);
								GST_INVOICE_SEQ_NO = new_inv_no + "/" + financial_year.substring(2,5)+financial_year.substring(7,9);
//						}
							boolean temp_flag = false;
							String dtgst = bill_period_start_dt.substring(6,10)+bill_period_start_dt.substring(3,5)+bill_period_start_dt.substring(0,2);
							int dt2 = Integer.parseInt(dtgst);
							if(dt2<gst_eff_dt) {
								temp_flag = true;
							}
							if(temp_flag && activity.equals("insert"))
								GST_INVOICE_SEQ_NO = "";
							if(temp_flag && activity.equals("update")) 
								GST_INVOICE_SEQ_NO = "";
						//////
						
						inv_no = Integer.parseInt(rset.getString(1))+1;
						if(contract_type.equals("S") || contract_type.equals("L")) {
							if(inv_no<10)
							{
								invoice_no = "000"+inv_no;
							}
							else if(inv_no<100)
							{
								invoice_no = "00"+inv_no;
							}
							else if(inv_no<1000)
							{
								invoice_no = "0"+inv_no;
							}
							else {
								invoice_no = ""+inv_no;
							}
							hlpl_Invoice_Seq_No = invoice_no+"/"+financial_year;
						} else {
							if(date_flag) {
								if(inv_no<10)
								{
									invoice_no = "0000"+inv_no;
								}
								else if(inv_no<100)
								{
									invoice_no = "000"+inv_no;
								}
								else if(inv_no<1000)
								{
									invoice_no = "00"+inv_no;
								}
								else if(inv_no<10000)
								{
									invoice_no = "0"+inv_no;
								} else {
									invoice_no = ""+inv_no;
								}
							} else {
								if(inv_no<10)
								{
									invoice_no = "000"+inv_no;
								}
								else if(inv_no<100)
								{
									invoice_no = "00"+inv_no;
								}
								else if(inv_no<1000)
								{
									invoice_no = "0"+inv_no;
								}
								else {
									invoice_no = ""+inv_no;
								}
							}
							
							hlpl_Invoice_Seq_No = invoice_no+"/"+financial_year;
						}
						
					}
					else
					{
						hlpl_Invoice_Seq_No = invoice_no+"/"+financial_year;
//						if(date_flag)
//							GST_INVOICE_SEQ_NO = new_inv_no + "/" + financial_year.substring(0,5)+financial_year.substring(6,9);
							GST_INVOICE_SEQ_NO = new_inv_no + "/" + financial_year.substring(2,5)+financial_year.substring(6,9);
					}
				}
			
				System.out.println("New Inv Seq No-----"+GST_INVOICE_SEQ_NO);
				System.out.println("Hlpl Inv Seq No-----"+hlpl_Invoice_Seq_No);
				
				inv_no = 0;
				invoice_no = "00001";
				
				//Procedure For Finding Out CUSTOMER Invoice Sequence Number For The Specific Financial Year ...
				if(contract_type.trim().equals("R"))
				{
					queryString = "SELECT NVL(MAX(cust_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
							  	  "WHERE financial_year='"+financial_year+"' " +
							  	  "AND contract_type='"+contract_type.trim()+"'";
				}
				else if(contract_type.trim().equals("T")) //ADDED FOR LTCORA AND CN
				{
					queryString = "SELECT NVL(MAX(cust_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
							  	  "WHERE financial_year='"+financial_year+"' " +
							  	  "AND contract_type IN ('C','T','Y','Z') AND FLAG!='A' AND SUPPLIER_CD!='2' ";
				}
				else if(contract_type.trim().equals("C")) //ADDED FOR LTCORA AND CN
				{
					queryString = "SELECT NVL(MAX(cust_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
							  	  "WHERE financial_year='"+financial_year+"' " +
							  	  "AND contract_type  IN ('C','T','Y','Z') AND FLAG!='A' AND SUPPLIER_CD!='2' ";
				}
				else
				{
					queryString = "SELECT NVL(MAX(cust_inv_seq_no),'0') FROM DLNG_INVOICE_MST " +
				  	  			  "WHERE financial_year='"+financial_year+"' " +
				  	  			  "AND (contract_type='S' OR contract_type='L')";
				}			
		System.out.println("Query For Fetching MAX CUSTOMER Invoice SEQ NO From DLNG_INVOICE_MST Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					inv_no = Integer.parseInt(rset.getString(1))+1;
					if(date_flag) {
						if(inv_no<10)
						{
							invoice_no = "0000"+inv_no;
						}
						else if(inv_no<100)
						{
							invoice_no = "000"+inv_no;
						}
						else if(inv_no<1000)
						{
							invoice_no = "00"+inv_no;
						} else if(inv_no<10000) {
							invoice_no = "0"+inv_no;
						} else {
							invoice_no = ""+inv_no;
						}
					} else {
						if(inv_no<10)
						{
							invoice_no = "000"+inv_no;
						}
						else if(inv_no<100)
						{
							invoice_no = "00"+inv_no;
						}
						else if(inv_no<1000)
						{
							invoice_no = "0"+inv_no;
						} else {
							invoice_no = ""+inv_no;
						}
					}
					
					customer_Invoice_Seq_No = invoice_no+"/"+financial_year;
				}
				else
				{
					customer_Invoice_Seq_No = invoice_no+"/"+financial_year;
				}
				
				if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
				{
					queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
								  "A.customer_cd="+customer_cd+" AND " +
								  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
						 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
						 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
						 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
					System.out.println("...HERE TAX_STRUCT_DTL..."+queryString);
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
				}
				else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
					queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
								  "A.customer_cd="+customer_cd+" AND " +
								  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
						 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
						 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
						 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
					System.out.println("====="+queryString);
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
				}
				else
				{
					tax_Structure_Dtl = "";
					tax_struct_cd = "0";
				}
			}
			
			double Offspec_Qty1 = 0;
			double Offspec_Qty = 0;
			double FM_Qty = 0;
			String Offspec_Flag = "N";
			
			queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
						  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
						  "FROM DLNG_DAILY_ALLOCATION_DTL " +
						  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
						  "sn_no="+sn_no+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
						  "gas_dt>=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY') AND " +
						  "gas_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') AND " +
						  "contract_type='"+contract_type+"'";
			if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
					queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
			}
			System.out.println("==DLNG_DAILY_ALLOCATION_DTL==="+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				Offspec_Flag = rset.getString(3);
				if(Offspec_Flag.trim().equalsIgnoreCase("N"))
				{
					Offspec_Qty1 += Double.parseDouble(rset.getString(1));
				}
				else
				{
					Offspec_Qty1 += Double.parseDouble(rset.getString(1));
					Offspec_Qty += Double.parseDouble(rset.getString(1));
				}
				FM_Qty += Double.parseDouble(rset.getString(2));
			}

			if(Offspec_Qty>=0.01)
			{
				accepted_Offspec_Qty = nf.format(Offspec_Qty);
			}
			else
			{
				accepted_Offspec_Qty = "";
			}
			
			if(FM_Qty>=0.01)
			{
				accepted_FM_Qty = nf.format(FM_Qty);
			}
			else
			{
				accepted_FM_Qty = "";
			}
			
			queryString = "SELECT NVL(SUM(qty_mmbtu),'0.00') FROM DLNG_DAILY_ALLOCATION_DTL " +
						  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
						  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
						  "sn_no="+sn_no+" AND " +
						  "contract_type='"+contract_type+"' AND " +
						  "gas_dt>=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY') AND " +
						  "gas_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') AND " +
						  "plant_seq_no="+customer_plant_seq_no+"";
			
			if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
					queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
			}
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				total_Invoice_Qty = nf.format(Double.parseDouble(rset.getString(1))-Offspec_Qty1);
			}
			
			
			if(contract_type.equalsIgnoreCase("S"))
			{
				queryString = "SELECT NVL(rate,'0') FROM DLNG_SN_MST " +
							  "WHERE customer_cd="+customer_cd+" AND flsa_no="+fgsa_no+" AND " +
							  "flsa_rev_no="+fgsa_rev_no+" AND sn_no="+sn_no+" AND " +
							  "sn_rev_no="+sn_rev_no+"";
				
		//		//System.out.println("Query For Fetching Sales Price Rate For Invoicing From DLNG_SN_MST Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Sales_Rate = nf2.format(Double.parseDouble(rset.getString(1)));					
				}
			}
			else if(contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT NVL(rate,'0') FROM DLNG_LOA_MST " +
							  "WHERE customer_cd="+customer_cd+" AND tender_no="+fgsa_no+" AND " +
							  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+"";
				
		//		//System.out.println("Query For Fetching Sales Price Rate For Invoicing From DLNG_LOA_MST Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Sales_Rate = nf2.format(Double.parseDouble(rset.getString(1)));
				}
			}
			else if(contract_type.equalsIgnoreCase("R"))
			{
				queryString = "SELECT NVL(re_gas_tarif,'0') FROM FMS7_RE_GAS_CARGO_DTL " +
							  "WHERE customer_cd="+customer_cd+" AND re_gas_no="+fgsa_no+" AND " +
							  "cargo_seq_no="+sn_no+"";
				
		//		//System.out.println("Query For Fetching Sales Price Rate For Invoicing From FMS7_RE_GAS_CARGO_DTL Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Sales_Rate = nf2.format(Double.parseDouble(rset.getString(1)));
				}
			}
			else if(contract_type.equalsIgnoreCase("T")) //ADDED FROR LTCORA AND CN
			{
				queryString = "SELECT NVL(REGAS_TARIF,'0') FROM FMS8_LNG_REGAS_CARGO_DTL " +
							  "WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND " +
							  "FLAG='T' AND " +
							  "cargo_seq_no="+sn_no+"";
				
		//		//System.out.println("Query For Fetching Sales Price Rate For Invoicing From FMS7_RE_GAS_CARGO_DTL Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Sales_Rate = nf2.format(Double.parseDouble(rset.getString(1)));
				}
			}
			else if(contract_type.equalsIgnoreCase("C")) //ADDED FROR LTCORA AND CN
			{
				queryString = "SELECT NVL(REGAS_TARIF,'0') FROM FMS8_LNG_REGAS_CARGO_DTL " +
							  "WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND " +
							  "FLAG='C' AND " +
							  "cargo_seq_no="+sn_no+"";
				
				System.out.println("Query For Fetching Sales Price Rate For Invoicing From FMS7_RE_GAS_CARGO_DTL Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Sales_Rate = nf2.format(Double.parseDouble(rset.getString(1)));
				}
			}
			
			//ADDED BY NB20140911 SALES PRICE IN INR
			/*if(invoice_Sales_Rate_INR.equalsIgnoreCase(""))
			{
				invoice_Grossamt_INR_On_SalesRate_INR="";
			}
			else
			{
				invoice_Grossamt_INR_On_SalesRate_INR=""+(Double.parseDouble(total_Invoice_Qty)*Double.parseDouble(invoice_Sales_Rate_INR));
				invoice_Grossamt_INR_On_SalesRate_INR=nf.format(Double.parseDouble(invoice_Grossamt_INR_On_SalesRate_INR));
			}*/
			//END
			
			
			queryString = "SELECT TO_CHAR(A.eff_dt,'DD/MM/YYYY') FROM FMS7_EXCHG_RATE_ENTRY A " +
						  "WHERE A.exchg_rate_cd="+exchg_rate_cd+" " +
						  "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_EXCHG_RATE_ENTRY B " +
						  "WHERE A.exchg_rate_cd=B.exchg_rate_cd AND " +
						  "B.eff_dt<TO_DATE('"+invoice_date+"','DD/MM/YYYY'))";
	//		//System.out.println("Query For Fetching Invoice Previous Available Exchange Day From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				invoice_Previous_Available_Exchg_Date = rset.getString(1);
			}
				
			
			//Following Code Is For Fetching Exchange Rate On Previous Available Date Than Invoice Date ...			
			if(invoice_Previous_Available_Exchg_Date!=null && !invoice_Previous_Available_Exchg_Date.equals("") && !invoice_Previous_Available_Exchg_Date.equals(" "))
			{
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+rbi_ref_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+invoice_Previous_Available_Exchg_Date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Previous_Exchg_Rate_RBI_Ref = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Previous_Exchg_Rate_RBI_Ref = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_selling_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+invoice_Previous_Available_Exchg_Date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Previous_Exchg_Rate_TT_Selling = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Previous_Exchg_Rate_TT_Selling = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_buying_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+invoice_Previous_Available_Exchg_Date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Previous_Exchg_Rate_TT_Buying = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Previous_Exchg_Rate_TT_Buying = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_avg_tt_selling_buying_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+invoice_Previous_Available_Exchg_Date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell = "N/A";
				}
			}
			
			//Following Code Is Fetching Exchange Rate On Invoice Date ...
			if(invoice_date!=null && !invoice_date.equals("") && !invoice_date.equals(" "))
			{
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+rbi_ref_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+invoice_date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Exchg_Rate_RBI_Ref = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Exchg_Rate_RBI_Ref = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_selling_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+invoice_date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Exchg_Rate_TT_Selling = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Exchg_Rate_TT_Selling = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_buying_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+invoice_date+"','DD/MM/YYYY')";
			//	//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Exchg_Rate_TT_Buying = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Exchg_Rate_TT_Buying = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_avg_tt_selling_buying_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+invoice_date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Exchg_Rate_Avg_TT_Buy_Sell = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Exchg_Rate_Avg_TT_Buy_Sell = "N/A";
				}
			}
			
			//Following Code Is Fetching User Defined Particular Date Exchange Rates ...
			if(particular_date!=null && !particular_date.equals("") && !particular_date.equals(" "))
			{
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+rbi_ref_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+particular_date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Particular_Day_Exchg_Rate_RBI_Ref = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Particular_Day_Exchg_Rate_RBI_Ref = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_selling_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+particular_date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Particular_Day_Exchg_Rate_TT_Selling = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Particular_Day_Exchg_Rate_TT_Selling = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_buying_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+particular_date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Particular_Day_Exchg_Rate_TT_Buying = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Particular_Day_Exchg_Rate_TT_Buying = "N/A";
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_avg_tt_selling_buying_cd+" " +
				  			  "AND A.eff_dt=TO_DATE('"+particular_date+"','DD/MM/YYYY')";
		//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell = nf2.format(Double.parseDouble(rset.getString(1)));
				}
				else
				{
					invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell = "N/A";
				}
			}
			
		//	//System.out.println("exchg_rate_cal_method = "+exchg_rate_cal_method);
			
			//Following Code Is Fetching Exchange Rates For A Specified Duration (e.g. Whole Week, First Fortnight, Second Fortnight, or for a Month, etc.) ...
			if(bill_period_start_dt!=null && !bill_period_start_dt.equals("") && !bill_period_start_dt.equals(" ") && bill_period_end_dt!=null && !bill_period_end_dt.equals("") && !bill_period_end_dt.equals(" "))
			{
				int date_part = 0;
				String date_portion = "";
				
				queryString3 = "SELECT TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') - TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY') + 1 FROM DUAL";
//				//System.out.println("Query To FindOut Dates For Billing Duration = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						for(int i=0; i<Integer.parseInt(rset3.getString(1)); i++)
						{
							date_part = Integer.parseInt(bill_period_start_dt.substring(0,2));
							date_part += i;  
							if(date_part>9)
							{
								date_portion = date_part+bill_period_start_dt.substring(2);
							}
							else if(date_part<=9 && date_part>0)
							{
								date_portion = "0"+date_part+bill_period_start_dt.substring(2);
							}
							else
							{
								date_portion = bill_period_end_dt;
							}
							
							invoice_Period_Dates.add(date_portion);
						}
					}					
				}
								
				for(int i=0; i<invoice_Period_Dates.size(); i++)
				{
					queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+rbi_ref_cd+" " +
					  			  "AND A.eff_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
		//			//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						invoice_Period_Exchg_Rate_RBI_Ref.add(nf2.format(Double.parseDouble(rset.getString(1))));
					}
					else
					{
						queryString1 = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+rbi_ref_cd+" " +
						  			  "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_EXCHG_RATE_ENTRY B WHERE A.exchg_rate_cd=B.exchg_rate_cd AND " +
						  			  "B.eff_dt<TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY'))";
			//			//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Period_Exchg_Rate_RBI_Ref.add(nf2.format(Double.parseDouble(rset1.getString(1))));
						}
						else
						{
							invoice_Period_Exchg_Rate_RBI_Ref.add("N/A");
						}
					}
					
					queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_selling_cd+" " +
					  			  "AND A.eff_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
			//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						invoice_Period_Exchg_Rate_TT_Selling.add(nf2.format(Double.parseDouble(rset.getString(1))));
					}
					else
					{
						queryString1 = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_selling_cd+" " +
						  			  "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_EXCHG_RATE_ENTRY B WHERE A.exchg_rate_cd=B.exchg_rate_cd AND " +
						  			  "B.eff_dt<TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY'))";
				//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Period_Exchg_Rate_TT_Selling.add(nf2.format(Double.parseDouble(rset1.getString(1))));
						}
						else
						{
							invoice_Period_Exchg_Rate_TT_Selling.add("N/A");
						}
					}
					
					queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_buying_cd+" " +
					  			  "AND A.eff_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
			//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						invoice_Period_Exchg_Rate_TT_Buying.add(nf2.format(Double.parseDouble(rset.getString(1))));
					}
					else
					{
						queryString1 = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_buying_cd+" " +
						  			  "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_EXCHG_RATE_ENTRY B WHERE A.exchg_rate_cd=B.exchg_rate_cd AND " +
						  			  "B.eff_dt<TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY'))";
				//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Period_Exchg_Rate_TT_Buying.add(nf2.format(Double.parseDouble(rset1.getString(1))));
						}
						else
						{
							invoice_Period_Exchg_Rate_TT_Buying.add("N/A");
						}
					}
					
					queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_avg_tt_selling_buying_cd+" " +
					  			  "AND A.eff_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
			//		//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell.add(nf2.format(Double.parseDouble(rset.getString(1))));
					}
					else
					{
						queryString1 = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_avg_tt_selling_buying_cd+" " +
						  			  "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_EXCHG_RATE_ENTRY B WHERE A.exchg_rate_cd=B.exchg_rate_cd AND " +
						  			  "B.eff_dt<TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY'))";
			//			//System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString1);
						
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell.add(nf2.format(Double.parseDouble(rset1.getString(1))));
						}
						else
						{
							invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell.add("N/A");
						}
					}
					
					queryString = "SELECT NVL(SUM(qty_mmbtu),'0.00'),NVL(SUM(qty_offspec),'0.00') FROM DLNG_DAILY_ALLOCATION_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  "sn_no="+sn_no+" AND " +
								  "contract_type='"+contract_type+"' AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "plant_seq_no="+customer_plant_seq_no+"";
					
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
							queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
					}
					
			//		//System.out.println("Query For Fetching Total QTY For Invoicing From DLNG_DAILY_ALLOCATION_DTL Table = "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						daily_Invoice_QTY.add(nf.format(Double.parseDouble(rset.getString(1))-Double.parseDouble(rset.getString(2))));
					}
					else
					{
						daily_Invoice_QTY.add(nf.format(Double.parseDouble("0.00")));
					}					
				}
			}
			
	//		//System.out.println("customer_Invoice_Gross_Amt_USD = "+customer_Invoice_Gross_Amt_USD+",  customer_Invoice_Gross_Amt_INR = "+customer_Invoice_Gross_Amt_INR);
			if(customer_Invoice_Gross_Amt_USD!=null && !customer_Invoice_Gross_Amt_USD.trim().equals("") && customer_Invoice_Gross_Amt_INR!=null && !customer_Invoice_Gross_Amt_INR.trim().equals(""))
			{
				double total_tax_amt = 0;
				double tax_amt = 0;
				String tax_cd = "0";
				String tax_factor = "0.00";
				
				queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
							  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
							  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
							  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
				System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					tax_cd = rset.getString(1);
					tax_factor = rset.getString(2);
					
					if(rset.getString(3).equals("1"))
					{
						////System.out.println("TAX----aa----"+Double.parseDouble(rset.getString(2)));
						tax_amt = (Double.parseDouble(customer_Invoice_Gross_Amt_INR)*Double.parseDouble(rset.getString(2)))/100;
						////System.out.println("TAX---aa111-----"+tax_amt);
					}
					else if(rset.getString(3).equals("2"))
					{
						queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
									  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
//						System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString1);
						rset1=stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							////System.out.println("TAX--------"+Double.parseDouble(rset.getString(2)));
							
					 			if(rset1.getString(3).equals("1"))
								{
									tax_amt = (Double.parseDouble(customer_Invoice_Gross_Amt_INR)*Double.parseDouble(rset1.getString(2)))/100;
								}
					 			////System.out.println("TAX---111-----"+tax_amt);
					 			tax_amt = (tax_amt*Double.parseDouble(rset.getString(2)))/100;
					 			////System.out.println("TAX---111-bb----"+tax_amt);
						}
						else
						{
							tax_amt = 0;
						}
					}
					else
					{
						tax_amt = 0;
					}
					System.out.println("herererer***********222222");
					customer_Invoice_Tax_Code.add(tax_cd);
					customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
					if(date_flag && contract_type.equalsIgnoreCase("C")) {
						customer_Invoice_Tax_Amt.add(nf.format(Math.round(tax_amt)));
						total_tax_amt += Math.round(tax_amt);
//						total_tax_amt += Double.parseDouble(nf.format(Math.round(tax_amt)));
					} else {
						customer_Invoice_Tax_Amt.add(nf3.format(tax_amt));
						total_tax_amt += Double.parseDouble(nf.format(tax_amt));
					}
				}
				////System.out.println("Deep:  total_tax_amt "+total_tax_amt);
				////System.out.println("Deep:  customer_Invoice_Gross_Amt_INR "+customer_Invoice_Gross_Amt_INR);
				if(date_flag && contract_type.equalsIgnoreCase("C")) {
					invoice_total_tax=nf3.format(Math.round(total_tax_amt));
					customer_Invoice_Net_Amt_INR = nf3.format(Math.round(Math.round(total_tax_amt)+Double.parseDouble(customer_Invoice_Gross_Amt_INR)));
				} else {
					invoice_total_tax=nf3.format(total_tax_amt);
					customer_Invoice_Net_Amt_INR = nf3.format(total_tax_amt+Double.parseDouble(customer_Invoice_Gross_Amt_INR));
				}
//				System.out.println("customer_Invoice_Net_Amt_INR"+customer_Invoice_Net_Amt_INR);
				for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
				{
					queryString = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
								  "tax_code="+customer_Invoice_Tax_Code.elementAt(i)+"";
//					////System.out.println("Query For Fetching Tax Name = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						String tax_abbr = rset.getString(1).trim()==null?"":rset.getString(1).trim();
						String tax_nm = rset.getString(2).trim()==null?"":rset.getString(2).trim();
						if(tax_abbr.contains("GST"))
							tax_gst = true;
						customer_Invoice_Tax_Abbr.add(tax_abbr);
						customer_Invoice_Tax_Name.add(tax_nm);
					}
					else
					{
						customer_Invoice_Tax_Abbr.add("");
						customer_Invoice_Tax_Name.add("");
					}
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchInvoiceDetails() ...
	
	
	
	//Following Function Fetches Invoice Details Record For Selected SN & Plant & For Selected Bill Period ...
	//Following Function Has Been Introduced By Samik Shah On 21st May, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 1st June, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 2nd June, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 10th June, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 26th August, 2010 ...
		
	public void CHECKFORCOLUMN()
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_INVOICE_MST' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'ADV_INV_DT' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table DLNG_INVOICE_MST add ADV_INV_DT date";
				stmt.executeUpdate(query);
				/*query="alter table DLNG_FLSA_MST add pre_approval varchar2(1)";
				stmt.executeUpdate(query);
				query="alter table DLNG_FLSA_MST add pre_approval_by number(6,0)";
				stmt.executeUpdate(query);*/
				conn.commit();
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void createColumn()
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_INVOICE_MST' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'USER_DEFINED_DAY' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table DLNG_INVOICE_MST add USER_DEFINED_DAY date";
				stmt.executeUpdate(query);
				conn.commit();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void CHECKFORCOLUMN_PDF() throws Exception
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_INVOICE_MST' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'PDF_INV_DTL' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table DLNG_INVOICE_MST add PDF_INV_DTL VARCHAR2(20)";
				stmt.executeUpdate(query);
				/*query="alter table DLNG_FLSA_MST add pre_approval varchar2(1)";
				stmt.executeUpdate(query);
				query="alter table DLNG_FLSA_MST add pre_approval_by number(6,0)";
				stmt.executeUpdate(query);*/
			}
			//ADDED BY RS19062017 FOR GST....NEW INVOICE SEQ NO IN DLNG_INVOICE_MST AND LOG_FMS7_INVOICE_MST
			count = 0;
			query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_INVOICE_MST' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'NEW_INV_SEQ_NO' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="ALTER TABLE DLNG_INVOICE_MST ADD NEW_INV_SEQ_NO VARCHAR2(15)";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE LOG_FMS7_INVOICE_MST ADD NEW_INV_SEQ_NO VARCHAR2(15)";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE DLNG_INVOICE_MST ADD SUPPLIER_CD NUMBER(3,0)";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE LOG_FMS7_INVOICE_MST ADD SUPPLIER_CD NUMBER(3,0)";
				stmt.executeUpdate(query);
			}
			count = 0;
			query = "SELECT COUNT(TNAME) FROM TAB WHERE UPPER(TNAME) = 'FMS7_DR_CR_DTL' ";
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				count = rset.getInt(1);
			}
			if(count==0) {
				query = "CREATE TABLE FMS7_DR_CR_DTL (CUSTOMER_CD NUMBER(6,0) NOT NULL ,"
						+ "HLPL_INV_SEQ_NO NUMBER(4,0) NOT NULL ,"
						+ "CON_TYPE VARCHAR2(1 BYTE) NOT NULL ,"
						+ "INVOICE_DT DATE NOT NULL , "
						+ "FINANCIAL_YEAR VARCHAR2(9 BYTE) NOT NULL ,"
						+ "ENT_DT DATE NOT NULL ,"
						+ "REMARK VARCHAR2(500 BYTE) NOT NULL ,"
						+ "EMP_CD NUMBER(6,0) NOT NULL ,"
						+ "CONSTRAINT FMS7_DR_CR_DTL_PK "
						+ "PRIMARY KEY (CUSTOMER_CD, HLPL_INV_SEQ_NO, CON_TYPE, FINANCIAL_YEAR))";
				stmt.executeUpdate(query);
			}
			conn.commit();
		}
		catch(Exception e)
		{
			conn.rollback();
			e.printStackTrace();
		}
	}
	
	public void CreateTable_dlng_inv_pdf_dtl() throws SQLException
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(*) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'dlng_inv_pdf_dtl' ";					
		//	//System.out.println("dlng_inv_pdf_dtl:SELECT: "+query);
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="CREATE TABLE dlng_inv_pdf_dtl "+
					"( "+
					 " PDF_INV_NM  VARCHAR2(100 BYTE)                NOT NULL, "+
					 " INV_TYPE    CHAR(1 BYTE)                      NOT NULL, "+
					 " PDF_FILE    BLOB, "+
					 " CREATED_DT  DATE, "+
					 " PDF_SIGNED  BLOB, "+
					 " SIGNED_DT   DATE, "+
					 " FLAG        CHAR(1 BYTE) "+
					 " )";
			//	//System.out.println("dlng_inv_pdf_dtl:CREATE: "+query);
				stmt.executeUpdate(query);
				
				query="ALTER TABLE dlng_inv_pdf_dtl ADD ( "+
					  " PRIMARY KEY (PDF_INV_NM, INV_TYPE)) ";
		//		//System.out.println("dlng_inv_pdf_dtl:ALTER: "+query);
				stmt.executeUpdate(query);
				conn.commit();
			}
			
			
		}
		catch(Exception e)
		{
			conn.rollback(); //RS20170412 
			e.printStackTrace();
		}
	}
	public void fetchInvoiceReport_PR()throws SQLException,IOException		//BK20160211
	{
		methodName = "fetchInvoiceReport()";
		try 
		{
			DataBean_Entity de = new DataBean_Entity();
			de.init();
			
			String exchg_rate_dt = "";
			String formatted_exchg_rate_dt = "";
			
			queryString = "SELECT SERVICE_NM,SERVICE_CD,RULE_REMARK,SERVICE_DESC FROM FMS7_LNG_SALES_MAPPING WHERE "
					+ "CONTRACT_TYPE='"+contract_type+"' ";
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				sac_code = rset.getString(2)==null?"":rset.getString(2);
				sac_name = rset.getString(1)==null?"":rset.getString(1);
				rule_remark = rset.getString(3)==null?"":rset.getString(3);
				service_desc = rset.getString(4)==null?"":rset.getString(4);
			}
			
			if(contract_type.equalsIgnoreCase("S"))
			{
				queryString = "SELECT SN_REF_NO " +
							  "FROM DLNG_SN_MST WHERE " +
				  			  "flsa_no="+fgsa_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_ref_no IS NOT NULL AND " +
				  			  "customer_cd="+customer_cd+" ORDER BY sn_rev_no DESC";
				////System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_FLSA_MST WHERE " +
							  "flsa_no="+fgsa_no+" AND " +
							  "rev_no="+fgsa_rev_no+" AND " +
							  "customer_cd="+customer_cd+" " +
							  "ORDER BY rev_no DESC";
				////System.out.println("Signing Date Fetch Query For FGSA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM DLNG_SN_MST WHERE " +
				  			  "flsa_no="+fgsa_no+" AND flsa_rev_no="+fgsa_rev_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+customer_cd+"";
				////System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT LOA_REF_NO " +
							  "FROM DLNG_LOA_MST WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND LOA_REF_NO IS NOT NULL AND " +
				  			  "customer_cd="+customer_cd+" ORDER BY loa_rev_no DESC";
				////System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_TENDER_MST WHERE " +
							  "tender_no="+fgsa_no+" AND " +
							  "customer_cd="+customer_cd+"";
				////System.out.println("Signing Date Fetch Query For TENDER Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM DLNG_LOA_MST WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+customer_cd+"";
				////System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("R"))
			{}
			else if(contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
			{}
			else if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{}


if(!exchg_rate_dt.equals(""))
{
	queryString = "SELECT TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'fmddth') " +
				  "|| ' ' || TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'fmMonth') " +
				  "|| ' ' || TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'yyyy') " +
				  "FROM DUAL";
	////System.out.println("Formatted Exchange Rate Date Fetch Query = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		formatted_exchg_rate_dt = rset.getString(1)==null?"":rset.getString(1);
	}
}
if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
{
	queryString = "SELECT A.tax_struct_dtl, A.tax_struct_cd FROM FMS7_CUSTOMER_TAX_STRUCT_DTL A WHERE " +
				  "A.customer_cd="+customer_cd+" AND " +
				  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
		 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_TAX_STRUCT_DTL B WHERE " +
		 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
		 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
	
		System.out.println("Query For Finding Out Correct Tax Structure For***"+queryString);
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
}
else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
{}

String dtgst = bill_period_start_dt.substring(6,10)+bill_period_start_dt.substring(3,5)+bill_period_start_dt.substring(0,2);
int dt2 = Integer.parseInt(dtgst);
if(dt2>=gst_eff_dt) {
	date_flag = true;
}

////System.out.println("customer_Invoice_Gross_Amt_INR.java. "+customer_Invoice_Gross_Amt_INR);
double tax_amt = 0;
String tax_cd = "0";
String tax_factor = "0.00";

queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
			  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
			  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
			  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString);
rset=stmt.executeQuery(queryString);
while(rset.next())
{
	tax_cd = rset.getString(1);
	tax_factor = rset.getString(2);
	
	if(rset.getString(3).equals("1"))
	{
		tax_amt = (Double.parseDouble(""+NumberFormat.getInstance().parse(customer_Invoice_Gross_Amt_INR))*Double.parseDouble(rset.getString(2)))/100;
	}
	else if(rset.getString(3).equals("2"))
	{
		queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
					  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
					  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
					  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
		////System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString1);
		rset1=stmt1.executeQuery(queryString1);
		if(rset1.next())
		{
	 			if(rset1.getString(3).equals("1"))
				{
					tax_amt = (Double.parseDouble(""+NumberFormat.getInstance().parse(customer_Invoice_Gross_Amt_INR))*Double.parseDouble(rset1.getString(2)))/100;
				}
				
	 			tax_amt = (tax_amt*Double.parseDouble(rset.getString(2)))/100;
		}
		else
		{
			tax_amt = 0;
		}
	}
	else
	{
		tax_amt = 0;
	}
	customer_Invoice_Tax_Code.add(tax_cd);
	customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
	if(date_flag && contract_type.equals("C")) {
		customer_Invoice_Tax_Amt_INR1+=Math.round(tax_amt);
		customer_Invoice_Tax_Amt.add(nf3.format(Math.round(tax_amt)));
	}  else {
		customer_Invoice_Tax_Amt_INR1+=tax_amt;
		customer_Invoice_Tax_Amt.add(nf3.format(tax_amt));
	}
	
}

for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{
	queryString = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
				  "tax_code="+customer_Invoice_Tax_Code.elementAt(i)+"";
	////System.out.println("Query For Fetching Tax Name = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		String tax_abbr = rset.getString(1).trim()==null?"":rset.getString(1).trim();
		String tax_nm = rset.getString(2).trim()==null?"":rset.getString(2).trim();
		if(tax_abbr.contains("GST")) {
			tax_gst = true;
		}
		customer_Invoice_Tax_Abbr.add(tax_abbr);
		customer_Invoice_Tax_Name.add(tax_nm);
	}
	else
	{
		customer_Invoice_Tax_Abbr.add("");
		customer_Invoice_Tax_Name.add("");
	}
}
System.out.println("customer_Invoice_Tax_Code = "+customer_Invoice_Tax_Code);
if(customer_Invoice_Tax_Code.size()>0) {
queryString = "SELECT sht_nm FROM FMS7_TAX_MST WHERE tax_code="+customer_Invoice_Tax_Code.firstElement()+"";

		System.out.println("Query For Fetching Tax Name = "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			String tax_nm = rset.getString(1).trim()==null?"":rset.getString(1).trim();
			
			if(tax_nm.contains("VAT"))
			{
				customer_Invoice_Tax_Flag = "V";
			}
			else if(tax_nm.contains("CST"))
			{
				customer_Invoice_Tax_Flag = "C";
			}
			else if(tax_nm.contains("ST"))
			{
				customer_Invoice_Tax_Flag = "S";
			}
			else 
			{
				customer_Invoice_Tax_Flag = "S";
			}
		}
}
queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' '),addr_flag " +
			  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
			  "WHERE A.customer_cd='"+customer_cd+"' AND A.def_inv_flag='Y' AND A.seq_no='"+contactPersonCd+"' AND " +
			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
			  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND B.seq_no='"+contactPersonCd+"' AND " +
			  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
System.out.println("Customer Contact Person Fetch Query = "+queryString);
rset = stmt.executeQuery(queryString);
if(rset.next())
{
	if(rset.getString(3).equals(" "))
	{
		contact_Person_Name_And_Designation = rset.getString(2).trim();
	}
	else
	{
		contact_Person_Name_And_Designation = rset.getString(2).trim()+" ("+rset.getString(3).trim()+")";
	}
	contact_addr_flag = rset.getString(4)==null?"":rset.getString(4);
}
if(contact_addr_flag.trim().equalsIgnoreCase("R") || contact_addr_flag.trim().equalsIgnoreCase("C") || contact_addr_flag.trim().equalsIgnoreCase("B"))
{
	queryString = "SELECT addr,city,pin,state " +
				  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
				  "WHERE A.customer_cd="+customer_cd+" AND A.address_type='"+contact_addr_flag+"' AND " +
				  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
				  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"' AND " +
				  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
	System.out.println("Customer Address Fetch Query = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
		contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
		contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
		contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
	}
	
	queryString = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
			+ "AND SEQ_NO = '"+customer_plant_seq_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
			+ "WHERE B.SEQ_NO='"+customer_plant_seq_no+"' AND B.CUSTOMER_CD='"+customer_cd+"' AND B.EFF_DT <= TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
	System.out.println("Fetchinng State-8888-"+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next()) {
		contact_Customer_Plant_State = rset.getString(1)==null?"":rset.getString(1);
	} else {
		contact_Customer_Plant_State = "";
	}
}else{
	String new_plant_no = "";
	if(!contact_addr_flag.equalsIgnoreCase("")) {
		new_plant_no = contact_addr_flag.trim().substring(1);
	}
	
	if(new_plant_no.length()>=1)
	{
		queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
					  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
					  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+new_plant_no+"' AND " +
					  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
					  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+new_plant_no+"' AND " +
					  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
		
		queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
				+ "AND SEQ_NO = '"+new_plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
				+ "WHERE B.SEQ_NO='"+new_plant_no+"' AND B.CUSTOMER_CD='"+customer_cd+"' AND B.EFF_DT <= TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
	}
	else
	{
		queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
					  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
					  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+customer_plant_seq_no+"' AND " +
					  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
					  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+customer_plant_seq_no+"' AND " +
					  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
		
		queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
				+ "AND SEQ_NO = '"+customer_plant_seq_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
				+ "WHERE B.SEQ_NO='"+customer_plant_seq_no+"' AND B.CUSTOMER_CD='"+customer_cd+"' AND B.EFF_DT <= TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
	}
//	System.out.println("Customer Address Fetch Query = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
		contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
		contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
		contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
	}
	
	rset1 = stmt.executeQuery(queryString1);
	if(rset1.next()) {
		contact_Customer_Plant_State = rset1.getString(1)==null?"":rset1.getString(1);
	} else {
		contact_Customer_Plant_State = "";
	}
}

if(contact_Customer_Plant_State!="") {
	queryString = "SELECT STATE_CODE FROM STATE_MST WHERE UPPER(STATE_NM) = '"+contact_Customer_Plant_State.toUpperCase()+"' ";
	rset = stmt.executeQuery(queryString);
	if(rset.next()) {
		contact_Customer_State_Code = rset.getString(1)==null?"":rset.getString(1);
	}
}

queryString = "SELECT customer_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY') " +
  "FROM FMS7_CUSTOMER_MST A " +
  "WHERE A.customer_cd="+customer_cd+" AND " +
  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
  "WHERE A.customer_cd=B.customer_cd AND " +
  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
////System.out.println("Customer Details Fetch Query = "+queryString);
rset = stmt.executeQuery(queryString);
if(rset.next())
{
	contact_Customer_Name = rset.getString(1)==null?"":rset.getString(1);
}
if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
{
	//BK20160211
	queryString = "SELECT A.stat_no, TO_CHAR(A.eff_dt,'DD-MM-YYYY'), B.stat_nm, B.stat_cd " +
				  "FROM FMS7_CUSTOMER_PLANT_TAX_CDS A, FMS7_GOVT_STAT_NO B " +
				  "WHERE A.stat_cd=B.stat_cd AND A.customer_cd="+customer_cd+" AND " +
				  "A.plant_seq_no="+customer_plant_seq_no+" AND (B.stat_type='S' OR B.stat_type='G') " +
				  "ORDER BY A.stat_cd";
	
	//System.out.println("Customer Plant's Tax Names Details Fetch Query = "+queryString);
	rset = stmt.executeQuery(queryString);
	while(rset.next())
	{
		String stat_nm = rset.getString(3)==null?"":rset.getString(3);
		String stat_no = rset.getString(1)==null?"":rset.getString(1);
		String stat_eff_dt = rset.getString(2)==null?"":rset.getString(2);
		
		if(!stat_nm.trim().equals(""))// && !stat_no.trim().equals("") && !stat_eff_dt.trim().equals("")
		{
			vSTAT_NO.add(stat_no.trim());
			vSTAT_EFF_DT.add(stat_eff_dt.trim());
			vSTAT_NM.add(stat_nm.trim());
			vSTAT_CD.add(rset.getString(4)==null?"0":rset.getString(4));
		}
	}
}
else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
{}			

queryString = "SELECT addr,city,pin,NVL(STATE,'N/A') " +
			  "FROM FMS7_SUPPLIER_ADDRESS_MST A " +
			  "WHERE A.supplier_cd=1 AND A.address_type='R' AND " +
			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
			  "WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND " +
			  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
////System.out.println("Supplier Address Fetch Query = "+queryString);
rset = stmt.executeQuery(queryString);
if(rset.next())
{
	contact_Suppl_Person_Address = rset.getString(1)==null?"":rset.getString(1);
	contact_Suppl_Person_City = rset.getString(2)==null?"":rset.getString(2);
	contact_Suppl_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
	contact_Suppl_State = rset.getString(4)==null?"":rset.getString(4);
	
	if(!contact_Suppl_State.equals("") && !contact_Suppl_State.equals("N/A")) {
		queryString = "SELECT NVL(STATE_CODE,'N/A') FROM STATE_MST WHERE UPPER(STATE_NM) = '"+contact_Suppl_State.toUpperCase()+"' ";
		rset = stmt.executeQuery(queryString);
		if(rset.next()) {
			contact_Suppl_State_Code = rset.getString(1);
		}
	}
}
queryString = "SELECT supplier_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
			  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY'),"
			  + "PAN_NO,TO_CHAR(PAN_ISSUE_DT,'DD-MM-YYYY'),"
			  + "GSTIN_NO,TO_CHAR(GSTIN_DT,'DD/MM/YYYY') " +
			  "FROM FMS7_SUPPLIER_MST A " +
			  "WHERE A.supplier_cd=1 AND " +
			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
			  "WHERE A.supplier_cd=B.supplier_cd AND " +
			  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
////System.out.println("Supplier Details Fetch Query = "+queryString);
rset = stmt.executeQuery(queryString);
if(rset.next())
{
	contact_Suppl_Name = rset.getString(1)==null?"":rset.getString(1);
	contact_Suppl_GST_NO = rset.getString(2)==null?"":rset.getString(2);
	contact_Suppl_CST_NO = rset.getString(3)==null?"":rset.getString(3);
	contact_Suppl_GST_DT = rset.getString(4)==null?"":rset.getString(4);
	contact_Suppl_CST_DT = rset.getString(5)==null?"":rset.getString(5);
	contact_Suppl_Service_Tax_NO = rset.getString(6)==null?"":rset.getString(6);
	contact_Suppl_Service_Tax_DT = rset.getString(7)==null?"":rset.getString(7);
	contact_Suppl_PAN_NO = rset.getString(8)==null?"":rset.getString(8);	//BK20160211
	contact_Suppl_PAN_DT = rset.getString(9)==null?"":rset.getString(9);	//BK20160211
	contact_Suppl_GSTIN_NO = rset.getString(10)==null?"":rset.getString(10);	//RS01062017
	contact_Suppl_GSTIN_DT = rset.getString(11)==null?"":rset.getString(11);	//RS01062017
}
queryString = "SELECT TO_CHAR(TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'),'DD-Mon-YY'),TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'),'DD-Mon-YY') FROM DUAL";
////System.out.println("Bill Period Start & End Dates Fetch Query = "+queryString);
rset = stmt.executeQuery(queryString);
if(rset.next())
{
	customer_Invoice_Start_DT = rset.getString(1)==null?"":rset.getString(1);
	customer_Invoice_End_DT = rset.getString(2)==null?"":rset.getString(2);
}

if(!customer_Invoice_Exchg_Rate_Cd.equals(sbi_avg_tt_selling_buying_cd))
{
	queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+customer_Invoice_Exchg_Rate_Cd+" " +
				  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
	////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		customer_Invoice_Exchg_Rate_Code.add(customer_Invoice_Exchg_Rate_Cd);
		customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
		customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
	}
	else
	{
		customer_Invoice_Exchg_Rate_Code.add(customer_Invoice_Exchg_Rate_Cd);
		customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
		customer_Invoice_Exchg_Rate_Value.add("0.00");
	}
}
else
{
	queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_buying_cd+" " +
				  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
	////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		customer_Invoice_Exchg_Rate_Code.add(sbi_tt_buying_cd);
		customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
		customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
	}
	else
	{
		customer_Invoice_Exchg_Rate_Code.add(sbi_tt_buying_cd);
		customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
		customer_Invoice_Exchg_Rate_Value.add("0.00");
	}
	
	queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_selling_cd+" " +
				  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
	////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		customer_Invoice_Exchg_Rate_Code.add(sbi_tt_selling_cd);
		customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
		customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
	}
	else
	{
		customer_Invoice_Exchg_Rate_Code.add(sbi_tt_selling_cd);
		customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
		customer_Invoice_Exchg_Rate_Value.add("0.00");
	}
}
for(int i=0; i<customer_Invoice_Exchg_Rate_Code.size(); i++)
{
	queryString = "SELECT A.exc_rate_nm FROM FMS7_CONT_EXCHG_RATE_MST A WHERE " +
				  "A.exc_rate_cd="+customer_Invoice_Exchg_Rate_Code.elementAt(i)+"";
	////System.out.println("Query For Fetching Exchange Rate Name For Invoicing From FMS7_CONT_EXCHG_RATE_MST Table = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next()){
		customer_Invoice_Exchg_Rate_Name.add(rset.getString(1)==null?"":rset.getString(1));
	}
	else
	{
		customer_Invoice_Exchg_Rate_Name.add("");
	}
}

//Following Code Is Fetching Exchange Rates For Specified Duration (e.g. Whole Week, First Fortnight, Second Fortnight, Or For Whole Month, etc.) ...
//Following Logic Has Been Introduced By Samik Shah On 8th June, 2010 ...
if(bill_period_start_dt!=null && !bill_period_start_dt.equals("") && !bill_period_start_dt.equals(" ") && bill_period_end_dt!=null && !bill_period_end_dt.equals("") && !bill_period_end_dt.equals(" "))
{
	int date_part = 0;
	String date_portion = "";
	
	queryString3 = "SELECT TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') - TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY') + 1 FROM DUAL";
	////System.out.println("Query To FindOut Dates For Billing Duration = "+queryString3);
	rset3 = stmt3.executeQuery(queryString3);
	if(rset3.next())
	{
		if(Integer.parseInt(rset3.getString(1))>0)
		{
			for(int i=0; i<Integer.parseInt(rset3.getString(1)); i++)
			{
				date_part = Integer.parseInt(bill_period_start_dt.substring(0,2));
				date_part += i;  
				if(date_part>9)
				{
					date_portion = date_part+bill_period_start_dt.substring(2);
				}
				else if(date_part<=9 && date_part>0)
				{
					date_portion = "0"+date_part+bill_period_start_dt.substring(2);
				}
				else
				{
					date_portion = bill_period_end_dt;
				}
				
				invoice_Period_Dates.add(date_portion);
			}
		}					
	}
	
	double SN_Qty_Before_Inv_Period_Start = 0;
	double SN_Offspec_Qty_Before_Inv_Period_Start = 0;
					
	queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
				  " WHERE MAPPING_ID = '"+customer_inv_mapping_id+"' "
				 + " AND gas_dt<TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY')";
	if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
	{
			queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
	}
	
	////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS/ltcora/cn Before This Invoice Period From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
	
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		SN_Qty_Before_Inv_Period_Start = Double.parseDouble(rset.getString(1));
	}
	
	queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
	  			  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
				  "FROM DLNG_DAILY_ALLOCATION_DTL " +
				  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
				  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
				  "sn_no="+sn_no+" AND " +
				  "contract_type='"+contract_type+"' AND " +
				  "gas_dt<TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY')";
	
	if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
	{
			queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
	}
	
	////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS/LTCORA/CN Before This Invoice Period From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
	
//	rset = stmt.executeQuery(queryString);
	while(rset.next())
	{
		if(rset.getString(3).trim().equalsIgnoreCase("N"))
		{
			SN_Offspec_Qty_Before_Inv_Period_Start += Double.parseDouble(rset.getString(1));
		}
	}SN_Qty_Before_Inv_Period_Start -= SN_Offspec_Qty_Before_Inv_Period_Start;
	
	double Total_Gas_Delv_Qty = 0;
	liability_exist_flag = "N";
					
	for(int i=0; i<invoice_Period_Dates.size(); i++)
	{
		double Buyer_Nom_Qty = 0;
		double Seller_Nom_Qty = 0;
		double mdcq_percentage = 100;
		double DCQ_Qty = 0;
		double MDCQ_Qty = 0;
		double PNQ_Qty = 0;
		double Seller_RE_Qty = 0;
		double Gas_Delv_RE_Qty = 0;
		double Gas_Delv_Qty = 0;
		double FM_Qty = 0;
		double Offspec_Qty = 0;
		double Offspec_Qty_Accepted = 0;
		double Buyer_Suspension_Qty = 0;
		double Failure_To_Delv_Qty = 0;
		double Offspec_Rate = 0; 
		String Offspec_Flag = "N";
		double SN_Qty_On_Inv_Period_Dates = 0;
		double total_buyer_nom_for_gas_day = 0;
		
		queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
					  "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
		   			  "WHERE A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
		   			  "AND A.nom_rev_no=" +
					  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
					  "WHERE B.sn_no=A.sn_no " +
					  //"AND B.sn_rev_no=A.sn_rev_no " + //Commented By Samik Shah On 27th May, 2011 ...
					  "AND B.fgsa_no=A.fgsa_no " +
					  //"AND B.fgsa_rev_no=A.fgsa_rev_no " + //Commented By Samik Shah On 27th May, 2011 ...
					  "AND B.customer_cd=A.customer_cd AND B.transporter_cd=A.transporter_cd " +
					  "AND B.contract_type=A.contract_type AND B.gas_dt=A.gas_dt " +
					  "AND B.plant_seq_no=A.plant_seq_no)";
		
		if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
		{
				queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
		}
		
		////System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			total_buyer_nom_for_gas_day = rset.getDouble(1);
		}
		
		queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
					  "WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND " +
					  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
		
		if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
		{
				queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
		}
		
		////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
		
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			SN_Qty_On_Inv_Period_Dates = Double.parseDouble(rset.getString(1));
		}
		
		queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
			  			  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
					  "FROM DLNG_DAILY_ALLOCATION_DTL " +
					  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
					  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
					  "sn_no="+sn_no+" AND " +
					  "contract_type='"+contract_type+"' AND " +
					  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
		
		if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
		{
				queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
		}
		
		////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
		
//		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			if(rset.getString(3).trim().equalsIgnoreCase("N"))
			{
				SN_Qty_On_Inv_Period_Dates -= Double.parseDouble(rset.getString(1));
			}
		}

		if(contract_type.equalsIgnoreCase("S"))
		{
			queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_SN_MST " +
						  "WHERE customer_cd="+customer_cd+" AND " +
						  "flsa_no="+fgsa_no+" AND " +
						  //"fgsa_rev_no="+fgsa_rev_no+" AND " +
						  "sn_no="+sn_no+" AND " +
						  "sn_rev_no="+sn_rev_no+"";
			
			////System.out.println("FMS7-SN-INV:DCQ:QRY-I0001:DLNG_SN_MST:SELECT: "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				mdcq_percentage = Double.parseDouble(rset.getString(2));
				MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
			}
			
			queryString4 = "SELECT NVL(A.dcq,'0') FROM DLNG_SN_DCQ_DTL A " +
			   			   "WHERE A.customer_cd="+customer_cd+" AND A.FLSA_NO="+fgsa_no+" " +
			   			   //"AND A.fgsa_rev_no="+fgsa_rev_no+" " +
			   			   "AND A.sn_no="+sn_no+" " +
			   			   "AND A.sn_rev_no="+sn_rev_no+" " +
			   			   "AND A.from_dt<=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
			   			   "AND A.to_dt>=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
			   			   "ORDER BY A.from_dt DESC";
			//////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
			////System.out.println("FMS7-SN-INV:DCQ:QRY-I0002:DLNG_SN_DCQ_DTL:SELECT: "+queryString4);
			rset4 = stmt4.executeQuery(queryString4);
			if(rset4.next())
			{
				//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
				if(Double.parseDouble(rset4.getString(1))>=0)
				{
					DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
					MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
				}
			}
								
			////System.out.println("MDCQ_Qty = "+MDCQ_Qty);
		}
		else if(contract_type.equalsIgnoreCase("L"))
		{
			queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_LOA_MST " +
						  "WHERE customer_cd="+customer_cd+" AND tender_no="+fgsa_no+" AND " +
						  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+"";
			
		//	//System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_LOA_MST Table = "+queryString);
			////System.out.println("FMS7-LOA-INV:DCQ:QRY-I0003:DLNG_LOA_MST:SELECT: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				mdcq_percentage = Double.parseDouble(rset.getString(2));
				MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
			}
			
			queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
			   			   "WHERE A.customer_cd="+customer_cd+" AND A.tender_no="+fgsa_no+" " +
			   			   "AND A.loa_no="+sn_no+" " +
			   			   "AND A.loa_rev_no="+sn_rev_no+" " +
			   			   "AND A.from_dt<=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
			   			   "AND A.to_dt>=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
			   			   "ORDER BY A.from_dt DESC";
			////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
			////System.out.println("FMS7-LOA-INV:DCQ:QRY-I0004:FMS7_LOA_DCQ_DTL:SELECT: "+queryString4);
			rset4 = stmt4.executeQuery(queryString4);
			if(rset4.next())
			{
				//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
				if(Double.parseDouble(rset4.getString(1))>=0)
				{
					DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
					MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
				}
			}
		}
		else if(contract_type.equalsIgnoreCase("R"))
		{}
		else if(contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
		{}
		else if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
		{}
		invoice_Period_DCQ.add(nf5.format(DCQ_Qty));
		
		Buyer_Nom_Qty = 0;
		
		queryString1 = "SELECT DISTINCT(transporter_cd) FROM FMS7_DAILY_BUYER_NOM_DTL " +
					  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
					  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
					  "sn_no="+sn_no+" AND " +
					  "contract_type='"+contract_type+"' AND " +
					  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
					  "plant_seq_no="+customer_plant_seq_no+"";
		
		if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
		{
				queryString1+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
		}
		////System.out.println("FMS7-BUYER-INV:BUY-NOM-QTY:QRY-I0007:FMS7_DAILY_BUYER_NOM_DTL:SELECT: "+queryString1);
		////System.out.println("Query For Fetching Buyer Nom Dtl - Transporter From FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString1);
		rset1 = stmt1.executeQuery(queryString1);
		while(rset1.next())
		{
			
			if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_BUYER_NOM_DTL A " +
			  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
			  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
			  	  "A.sn_no="+sn_no+" AND " +
			  	  "A.contract_type='"+contract_type+"' AND " +
			  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
			  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
			  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
			  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
			  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
			  	  "B.sn_no="+sn_no+" AND " +
			  	  "B.contract_type='"+contract_type+"' AND " +
			  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
			  	  "B.transporter_cd="+rset1.getString(1)+" AND MAPPING_ID='"+customer_inv_mapping_id+"')" +
			  	  " AND MAPPING_ID='"+customer_inv_mapping_id+"'";
				
			}
			else
			{
				queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_BUYER_NOM_DTL A " +
			  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
			  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
			  	  "A.sn_no="+sn_no+" AND " +
			  	  "A.contract_type='"+contract_type+"' AND " +
			  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
			  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
			  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
			  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
			  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
			  	  "B.sn_no="+sn_no+" AND " +
			  	  "B.contract_type='"+contract_type+"' AND " +
			  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
			  	  "B.transporter_cd="+rset1.getString(1)+")";
			}
			
			
			
			
			////System.out.println("FMS7-BUYER-INV:BUY-NOM-QTY:QRY-I0007A:FMS7_DAILY_BUYER_NOM_DTL:SELECT: "+queryString);
			////System.out.println("Query For Fetching Buyer Nom QTY From FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString);
		
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				Buyer_Nom_Qty += Double.parseDouble(rset.getString(1));
			}
		}
		
		invoice_Period_Buyer_Nom_Qty.add(nf.format(Buyer_Nom_Qty));
		
		Seller_Nom_Qty = 0;
		Seller_RE_Qty = 0;
							
		queryString1 = "SELECT DISTINCT(transporter_cd) FROM FMS7_DAILY_SELLER_NOM_DTL " +
					  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
					  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
					  "sn_no="+sn_no+" AND " +
					  "contract_type='"+contract_type+"' AND " +
					  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
					  "plant_seq_no="+customer_plant_seq_no+"";
		if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
		{
				queryString1+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
		}
		
		////System.out.println("FMS7-SELLER-INV:SEL-NOM-QTY:QRY-I0008:FMS7_DAILY_SELLER_NOM_DTL:SELECT: "+queryString1);
	//	//System.out.println("Query For Fetching Seller Nom Dtl - Transporter From FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString1);
		rset1 = stmt1.executeQuery(queryString1);
		while(rset1.next())
		{
			
			if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_SELLER_NOM_DTL A " +
			  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
			  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
			  	  "A.sn_no="+sn_no+" AND " +
			  	  "A.contract_type='"+contract_type+"' AND " +
			  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
			  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
			  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
			  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
			  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
			  	  "B.sn_no="+sn_no+" AND " +
			  	  "B.contract_type='"+contract_type+"' AND " +
			  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
			  	  "B.transporter_cd="+rset1.getString(1)+" " +
			  	  "AND MAPPING_ID='"+customer_inv_mapping_id+"') " +
			  	  "AND MAPPING_ID='"+customer_inv_mapping_id+"'";
				
				
			}
			else
			{
				queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_SELLER_NOM_DTL A " +
			  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
			  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
			  	  "A.sn_no="+sn_no+" AND " +
			  	  "A.contract_type='"+contract_type+"' AND " +
			  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
			  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
			  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
			  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
			  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
			  	  "B.sn_no="+sn_no+" AND " +
			  	  "B.contract_type='"+contract_type+"' AND " +
			  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
			  	  "B.transporter_cd="+rset1.getString(1)+")";
	
	}
	////System.out.println("FMS7-SELLER-INV:SEL-NOM-QTY:QRY-I0008A:FMS7_DAILY_SELLER_NOM_DTL:SELECT: "+queryString);
	////System.out.println("Query For Fetching Seller Nom QTY From FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString);

	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		Seller_Nom_Qty += Double.parseDouble(rset.getString(1));
	}
}

MDCQ_Qty = Double.parseDouble(nf5.format(MDCQ_Qty));
PNQ_Qty = 0;
double re_qty = 28952; //Introduced By Samik Shah On 7th May, 2011 ...

if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("T") || contract_type.trim().equalsIgnoreCase("C"))
{
	if(total_buyer_nom_for_gas_day<re_qty)
	{
		PNQ_Qty = 0;
		Seller_RE_Qty = Seller_Nom_Qty;
	}
	else
	{
		if(Buyer_Nom_Qty<MDCQ_Qty)
		{
			PNQ_Qty = Buyer_Nom_Qty;
		}
		else
		{
			PNQ_Qty = MDCQ_Qty;
		}
		
		PNQ_Qty = Double.parseDouble(nf.format(PNQ_Qty));
							
		Seller_RE_Qty = Seller_Nom_Qty - PNQ_Qty;
		
		if(Seller_RE_Qty<0)
		{
			Seller_RE_Qty = 0;
		}
	}
}
else
{
	if(total_buyer_nom_for_gas_day<re_qty)
	{
		PNQ_Qty = 0;
		Seller_RE_Qty = Seller_Nom_Qty;
	}
	else
	{
		if(Buyer_Nom_Qty<MDCQ_Qty)
		{
			PNQ_Qty = Buyer_Nom_Qty;
		}
		else
		{
			PNQ_Qty = MDCQ_Qty;
		}
		
		PNQ_Qty = Double.parseDouble(nf.format(PNQ_Qty));
		
		Seller_RE_Qty = Seller_Nom_Qty - PNQ_Qty;
		
		if(Seller_RE_Qty<0)
		{
			Seller_RE_Qty = 0;
		}
	}
}

////System.out.println("PNQ_Qty--->>>>"+PNQ_Qty+"-Seller_RE_Qty-"+Seller_RE_Qty);	
invoice_Period_Seller_Nom_PNQ.add(nf.format(PNQ_Qty));
invoice_Period_Seller_Nom_RE_Qty.add(nf.format(Seller_RE_Qty));
		
queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
			  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
			  "FROM DLNG_DAILY_ALLOCATION_DTL " +
			  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
			  "sn_no="+sn_no+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
			  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  "contract_type='"+contract_type+"'";

if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
{
		queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
}
////System.out.println("FMS7-INV:SEL-ALLOC-QTY:QRY-I0009:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString);
////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);

//rset = stmt.executeQuery(queryString);
while(rset.next())
{
	Offspec_Flag = rset.getString(3);
	if(Offspec_Flag.trim().equalsIgnoreCase("N"))
	{
		Offspec_Qty += Double.parseDouble(rset.getString(1));
	}
	else if(Offspec_Flag.trim().equalsIgnoreCase("Y"))
	{
		Offspec_Qty_Accepted += Double.parseDouble(rset.getString(1));
	}
	FM_Qty += Double.parseDouble(rset.getString(2));
}					


queryString = "SELECT NVL(buy_suspen_qty,'0.00'),NVL(fail_delv_qty,'0.00') " +
			  "FROM FMS7_LIABILITY_DTL " +
			  "WHERE customer_cd="+customer_cd+" AND " +
			  "fgsa_no="+fgsa_no+" AND " +
			  "sn_no="+sn_no+" AND " +
			  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  "contract_type='"+contract_type+"'";
////System.out.println("FMS7-LIA-INV:LIABILITY-QTY:QRY-I00010:FMS7_LIABILITY_DTL:SELECT: "+queryString);
////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);

rset = stmt.executeQuery(queryString);
if(rset.next())
{
	Buyer_Suspension_Qty = rset.getDouble(1);
	Failure_To_Delv_Qty = rset.getDouble(2);
}

if(Offspec_Qty>=0.01)
{
	invoice_Period_Buyer_Off_Spec_Qty.add(nf.format(Offspec_Qty));
	liability_exist_flag = "Y";
}
else
{
	invoice_Period_Buyer_Off_Spec_Qty.add("");
}

if(Buyer_Suspension_Qty>=0.01)
{
	invoice_Period_Buyer_Suspension_Qty.add(nf.format(Buyer_Suspension_Qty));
	liability_exist_flag = "Y";
}
else
{
	invoice_Period_Buyer_Suspension_Qty.add("");
}

if(Failure_To_Delv_Qty>=0.01)
{
	invoice_Period_Delv_Failure_Qty.add(nf.format(Failure_To_Delv_Qty));
	liability_exist_flag = "Y";
}
else
{
	invoice_Period_Delv_Failure_Qty.add("");
}
if(FM_Qty>=0.01)
{
	invoice_Period_FM_Qty.add(nf.format(FM_Qty));
	liability_exist_flag = "Y";
}
else
{
	invoice_Period_FM_Qty.add("");
}

//Logic To Be Developed For Following Quantities Will Be Done Later ... 
invoice_Period_Buyer_Shortfall_Qty.add("");
invoice_Period_Total_Shortfall_Qty.add("");
invoice_Period_LD_Credit_Payable.add("");					


Gas_Delv_RE_Qty = 0;
Gas_Delv_Qty = 0;

queryString = "SELECT NVL(daily_qty,'0.00') FROM DLNG_INVOICE_DTL WHERE " +
			  "financial_year='"+invFinancialYear+"' AND " +
			  "hlpl_inv_seq_no='"+hlplInvoiceNo+"' AND " +
			  "allocation_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
			  "contract_type='"+contract_type+"'";


//System.out.println("FMS7-INV:DELV-QTY:QRY-I00011:DLNG_INVOICE_DTL:SELECT: "+queryString);
////System.out.println("Invoice & Due Dates Fetch Query = "+queryString);
rset = stmt.executeQuery(queryString);
if(rset.next())
{
	if(Offspec_Qty_Accepted>=0.01)
	{
		Gas_Delv_Qty = Double.parseDouble(rset.getString(1))+Offspec_Qty_Accepted;
	}
	else
	{
		Gas_Delv_Qty = Double.parseDouble(rset.getString(1));
	}
}

PNQ_Qty = 0;

if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("T") || contract_type.trim().equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
{
	/*if(Gas_Delv_Qty<re_qty)
	{
		PNQ_Qty = 0;
		Gas_Delv_RE_Qty = Gas_Delv_Qty;
	}
	else
	{*/
		if(Gas_Delv_Qty<MDCQ_Qty)
		{
			PNQ_Qty = Gas_Delv_Qty;
		}
		else
		{
			PNQ_Qty = MDCQ_Qty;
		}
							
		Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;
		
		if(Gas_Delv_RE_Qty<0)
		{
			Gas_Delv_RE_Qty = 0;
		}
	//}
}
else
{
	if(Gas_Delv_Qty<MDCQ_Qty)
	{
		PNQ_Qty = Gas_Delv_Qty;
	}
	else
	{
		PNQ_Qty = MDCQ_Qty;
	}
						
	Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;
	
	if(Gas_Delv_RE_Qty<0)
	{
		Gas_Delv_RE_Qty = 0;
	}
}

/*Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;

if(Gas_Delv_RE_Qty<0)
{
	Gas_Delv_RE_Qty = 0;
}*/
invoice_Period_Gas_Delivered_PNQ.add(nf.format(PNQ_Qty));
invoice_Period_Gas_Delivered_Re_Qty.add(nf.format(Gas_Delv_RE_Qty));
invoice_Period_Gas_Delivered_Total_Qty.add(nf.format(Gas_Delv_Qty));

Total_Gas_Delv_Qty += Gas_Delv_Qty;

invoice_Period_Cumulative_Qty.add(nf.format(Total_Gas_Delv_Qty));
SN_Qty_Before_Inv_Period_Start += SN_Qty_On_Inv_Period_Dates;
invoice_Period_Cumulative_SN_Qty.add(nf.format(SN_Qty_Before_Inv_Period_Start));
}


if(!date_portion.trim().equals("") && date_portion.trim().length()==10)
{
String period_start_dt = "";
String period_end_dt = "";

month = date_portion.trim().substring(3,5);
year = date_portion.trim().substring(6);
			
////System.out.println("billCycle = "+billCycle);

if(billCycle.equals("2") || billCycle.equals("1")) //1st-Fortnightly or 2nd-Fortnightly Invoice ...
{
	if(billCycle.equals("2")) //2nd-Fortnightly Invoice ...
	{
		period_start_dt = "16"+"/"+month+"/"+year;
	}
	else  //1st-Fortnightly Invoice ...
	{
		period_start_dt = "01"+"/"+month+"/"+year;
	}
	
	if(billCycle.equals("1"))  //1st-Fortnightly Invoice ...
	{
		period_end_dt = "15"+"/"+month+"/"+year;
	}
	else  //2nd-Fortnightly Invoice ...
	{
		queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
		////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			period_end_dt = rset.getString(1);
		}
	}
}
else if(billCycle.equals("3")) //1st-Weekly Invoice ...
{
	period_start_dt = "01"+"/"+month+"/"+year;
	period_end_dt = "07"+"/"+month+"/"+year;
}
else if(billCycle.equals("4")) //2nd-Weekly Invoice ...
{
	period_start_dt = "08"+"/"+month+"/"+year;
	period_end_dt = "14"+"/"+month+"/"+year;
}
else if(billCycle.equals("5")) //3rd-Weekly Invoice ...
{
	period_start_dt = "15"+"/"+month+"/"+year;
	period_end_dt = "21"+"/"+month+"/"+year;
}
else if(billCycle.equals("6")) //4th-Weekly Invoice ...
{
	period_start_dt = "22"+"/"+month+"/"+year;
	period_end_dt = "28"+"/"+month+"/"+year;
}
else if(billCycle.equals("9")) //5th-Weekly Invoice ...
{
	period_start_dt = "29"+"/"+month+"/"+year;
	
	queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
	////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		period_end_dt = rset.getString(1);
	}
}
else if(billCycle.equals("7"))  //Monthly Invoice ...
{
	period_start_dt = "01"+"/"+month+"/"+year;
	
	queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
	////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		period_end_dt = rset.getString(1);
	}
}

String new_period_end_dt = "";
String new_period_start_dt = "";
int date_diff = 0;

if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
{
	queryString = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') " +
	  "FROM DLNG_ALLOC_MST " +
	  "WHERE gas_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND " +
	  "ENTRY_TOT_ENE>=0.99 " +
	  "AND MAPPING_ID='"+customer_inv_mapping_id+"' " +
	  "ORDER BY gas_dt DESC";
		
}
else
{
	queryString = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') " +
	  "FROM DLNG_ALLOC_MST " +
	  "WHERE gas_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY') "
	  + "  AND MAPPING_ID='"+customer_inv_mapping_id+"' and "+
	  "ENTRY_TOT_ENE>=0.99 " +
	  "ORDER BY gas_dt DESC";
}

//System.out.println("FMS7-INV:END-DATE:QRY-I00012:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString);
rset = stmt.executeQuery(queryString);
if(rset.next())
{
	new_period_end_dt = rset.getString(1);
}

if(!new_period_end_dt.trim().equals("") && new_period_end_dt.trim().length()==10)
{
	queryString = "SELECT " +
				  "TO_DATE('"+new_period_end_dt+"','dd/mm/yyyy')" +
				  "-" +
				  "TO_DATE('"+invoice_Period_Dates.elementAt(invoice_Period_Dates.size()-1)+"','dd,mm/yyyy') " +
				  "FROM DUAL";
	
	rset = stmt.executeQuery(queryString);
	if(rset.next())
	{
		date_diff = rset.getInt(1);
		
		if(date_diff>0)
		{
			queryString2 = "SELECT TO_CHAR(TO_DATE('"+invoice_Period_Dates.elementAt(invoice_Period_Dates.size()-1)+"','dd,mm/yyyy')+1,'dd/mm/yyyy') FROM DUAL";
			rset2 = stmt2.executeQuery(queryString2);
			if(rset2.next())
			{
				new_period_start_dt = rset2.getString(1);
			}
		}
	}
}
if(date_diff>0 && !new_period_start_dt.trim().equals("") && new_period_start_dt.trim().length()==10)
{
	for(int i=0; i<date_diff; i++)
	{
		double SN_Qty_On_Inv_Period_Dates = 0;
		double mdcq_percentage = 100;
		double DCQ_Qty = 0;
		double MDCQ_Qty = 0;
		double PNQ_Qty = 0;
		
		queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
					  "WHERE gas_dt=(TO_DATE('"+new_period_start_dt+"','DD/MM/YYYY')+"+i+")"
					 + " AND MAPPING_ID='"+customer_inv_mapping_id+"'";
		
		if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
		{
				queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
		}
		
		////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
		
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			SN_Qty_On_Inv_Period_Dates = Double.parseDouble(rset.getString(1));
		}
		
		String period_dt = "";
		
		queryString = "SELECT TO_CHAR(TO_DATE('"+new_period_start_dt+"','dd/mm/yyyy')+"+i+",'dd/mm/yyyy') FROM DUAL";
		
		////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
		
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			period_dt = rset.getString(1);
		}
		
		if(contract_type.equalsIgnoreCase("S"))
		{
			queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_SN_MST " +
						  "WHERE customer_cd="+customer_cd+" AND " +
						  "flsa_no="+fgsa_no+" AND " +
						  //"fgsa_rev_no="+fgsa_rev_no+" AND " +
						  "sn_no="+sn_no+" AND " +
						  "sn_rev_no="+sn_rev_no+"";
			
			////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				mdcq_percentage = Double.parseDouble(rset.getString(2));
				MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
			}
			
			queryString4 = "SELECT NVL(A.dcq,'0') FROM DLNG_SN_DCQ_DTL A " +
			   			   "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" " +
			   			   //"AND A.fgsa_rev_no="+fgsa_rev_no+" " +
			   			   "AND A.sn_no="+sn_no+" " +
			   			   "AND A.sn_rev_no="+sn_rev_no+" " +
			   			   "AND A.from_dt<=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
			   			   "AND A.to_dt>=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
			   			   "ORDER BY A.from_dt DESC";
			////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
			rset4 = stmt4.executeQuery(queryString4);
			if(rset4.next())
			{
				//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
				if(Double.parseDouble(rset4.getString(1))>=0)
				{
					DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
					MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
				}
			}
								
			////System.out.println("MDCQ_Qty = "+MDCQ_Qty);
		}
		else if(contract_type.equalsIgnoreCase("L"))
		{
			queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_LOA_MST " +
						  "WHERE customer_cd="+customer_cd+" AND tender_no="+fgsa_no+" AND " +
						  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+"";
			
			////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_LOA_MST Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				mdcq_percentage = Double.parseDouble(rset.getString(2));
				MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
			}
			
			queryString4 = "SELECT NVL(A.dcq,'0') FROM DLNG_LOA_DCQ_DTL A " +
			   			   "WHERE A.customer_cd="+customer_cd+" AND A.tender_no="+fgsa_no+" " +
			   			   "AND A.loa_no="+sn_no+" " +
			   			   "AND A.loa_rev_no="+sn_rev_no+" " +
			   			   "AND A.from_dt<=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
			   			   "AND A.to_dt>=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
			   			   "ORDER BY A.from_dt DESC";
			////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
			rset4 = stmt4.executeQuery(queryString4);
			if(rset4.next())
			{
				if(Double.parseDouble(rset4.getString(1))>0)
				{
					DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
					MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
				}
			}
		}
		else if(contract_type.equalsIgnoreCase("R"))
		{
			queryString = "SELECT NVL(mdcq_percentage,'100.00') FROM FMS7_RE_GAS_MST " +
						  "WHERE customer_cd="+customer_cd+" AND re_gas_no="+fgsa_no+" AND " +
						  "rev_no="+fgsa_rev_no+"";
			
			////System.out.println("Query For Fetching MDCQ Percentage For the Invoice Period From FMS7_RE_GAS_MST Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				mdcq_percentage = Double.parseDouble(rset.getString(1));
			}
			
			queryString = "SELECT NVL(dcq_qty,'0.00') FROM FMS7_RE_GAS_CARGO_DTL " +
						  "WHERE customer_cd="+customer_cd+" AND re_gas_no="+fgsa_no+" AND " +
						  "cargo_seq_no="+sn_no+"";
			
			////System.out.println("Query For Fetching DCQ For the Invoice Period From FMS7_RE_GAS_CARGO_DTL Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
			}
			MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;					
		}
		else if(contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
		{
			queryString = "SELECT NVL(mdcq_percentage,'100.00') FROM FMS8_LNG_REGAS_MST " +
						  "WHERE customer_cd="+customer_cd+" AND AGREEMENT_NO="+fgsa_no+" AND " +
						  "rev_no="+fgsa_rev_no+" " +
						  "AND CN_NO='0' AND CN_REV_NO='0'";
			
			////System.out.println("Query For Fetching MDCQ Percentage For the Invoice Period From FMS7_RE_GAS_MST Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				mdcq_percentage = Double.parseDouble(rset.getString(1));
			}
			
			queryString = "SELECT NVL(dcq_qty,'0.00') FROM FMS8_LNG_REGAS_CARGO_DTL " +
						  "WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND FLAG='T' AND " +
						  "cargo_seq_no="+sn_no+"";
			
			////System.out.println("Query For Fetching DCQ For the Invoice Period From FMS7_RE_GAS_CARGO_DTL Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
			}
			MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;					
		}
		else if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
		{
			queryString = "SELECT NVL(mdcq_percentage,'100.00') FROM FMS8_LNG_REGAS_MST " +
						  "WHERE customer_cd="+customer_cd+" AND CN_NO="+fgsa_no+" AND " +
						  "CN_REV_NO="+fgsa_rev_no+" " +
						  "AND MAPPING_ID='"+customer_inv_mapping_id+"'";
			
			////System.out.println("Query For Fetching MDCQ Percentage For the Invoice Period From FMS7_RE_GAS_MST Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				mdcq_percentage = Double.parseDouble(rset.getString(1));
			}
			
			queryString = "SELECT NVL(dcq_qty,'0.00') FROM FMS8_LNG_REGAS_CARGO_DTL " +
						  "WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND FLAG='C' AND " +
						  "cargo_seq_no="+sn_no+"";
			
			////System.out.println("Query For Fetching DCQ For the Invoice Period From FMS7_RE_GAS_CARGO_DTL Table = "+queryString);
			
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
			}
			MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;					
		}
		
		invoice_Period_Dates.add(period_dt);
		invoice_Period_DCQ.add(nf5.format(DCQ_Qty));
		invoice_Period_Buyer_Nom_Qty.add(nf.format(0));
		invoice_Period_Buyer_Shortfall_Qty.add("");
		invoice_Period_Buyer_Off_Spec_Qty.add("");
		invoice_Period_Buyer_Suspension_Qty.add("");
		invoice_Period_Delv_Failure_Qty.add("");
		invoice_Period_Total_Shortfall_Qty.add("");
		invoice_Period_LD_Credit_Payable.add("");
		invoice_Period_Seller_Nom_PNQ.add(nf.format(0));
		invoice_Period_Gas_Delivered_PNQ.add(nf.format(0));
		invoice_Period_Seller_Nom_RE_Qty.add(nf.format(0));
		invoice_Period_FM_Qty.add("");
		invoice_Period_Gas_Delivered_Re_Qty.add(nf.format(0));
		invoice_Period_Gas_Delivered_Total_Qty.add(nf.format(0));
		invoice_Period_Cumulative_Qty.add(nf.format(Total_Gas_Delv_Qty));
		SN_Qty_Before_Inv_Period_Start += SN_Qty_On_Inv_Period_Dates;
		invoice_Period_Cumulative_SN_Qty.add(nf.format(SN_Qty_Before_Inv_Period_Start));
	}
}
}
}		
//Date d_eff = new Date(gst_eff_dt);
//Date d1 = new Date(bill_period_start_dt);
//if(d1.after(d_eff) || d1.equals(d_eff)) {
//	date_flag = true;
//}

}
catch(Exception e)
{
//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
e.printStackTrace();
}
}//End Of Method fetchInvoiceReportPreview() ...
	public void EnterData() throws SQLException	
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS7_LNG_SALES_MAPPING' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'SERVICE_NM' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="ALTER TABLE FMS7_LNG_SALES_MAPPING ADD SERVICE_NM VARCHAR2(35)";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE FMS7_LNG_SALES_MAPPING ADD SERVICE_CD VARCHAR2(35)";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE FMS7_LNG_SALES_MAPPING ADD RULE_REMARK VARCHAR2(500)";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE FMS7_LNG_SALES_MAPPING ADD SERVICE_DESC VARCHAR2(500)";
				stmt.executeUpdate(query);
				
				query="ALTER TABLE FMS7_LNG_SALES_MAPPING ADD SUPPLIER_CD NUMBER(3,0)";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO FMS7_LNG_SALES_MAPPING(CONTRACT_TYPE,EFF_DT,ACCOUNT_CD,ACCOUNT_TYPE,"
						+ "EMP_CD,ENT_DT,FLAG) VALUES('1',TO_DATE('01/07/2017','DD/MM/YYYY'), "
						+ "'701003','P','',SYSDATE,'Y')";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO FMS7_LNG_SALES_MAPPING(CONTRACT_TYPE,EFF_DT,ACCOUNT_CD,ACCOUNT_TYPE,"
						+ "EMP_CD,ENT_DT,FLAG) VALUES('2',TO_DATE('01/07/2017','DD/MM/YYYY'), "
						+ "'701003','P','',SYSDATE,'Y')";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO FMS7_LNG_SALES_MAPPING(CONTRACT_TYPE,EFF_DT,ACCOUNT_CD,ACCOUNT_TYPE,"
						+ "EMP_CD,ENT_DT,FLAG) VALUES('X',TO_DATE('01/07/2017','DD/MM/YYYY'), "
						+ "'701003','P','',SYSDATE,'Y')";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO FMS7_LNG_SALES_MAPPING(CONTRACT_TYPE,EFF_DT,ACCOUNT_CD,ACCOUNT_TYPE,"
						+ "EMP_CD,ENT_DT,FLAG) VALUES('Y',TO_DATE('01/07/2017','DD/MM/YYYY'), "
						+ "'701003','P','',SYSDATE,'Y')";
				stmt.executeUpdate(query);
				
				query = "INSERT INTO FMS7_LNG_SALES_MAPPING(CONTRACT_TYPE,EFF_DT,ACCOUNT_CD,ACCOUNT_TYPE,"
						+ "EMP_CD,ENT_DT,FLAG) VALUES('Z',TO_DATE('01/07/2017','DD/MM/YYYY'), "
						+ "'701003','P','',SYSDATE,'Y')";
				stmt.executeUpdate(query);
				
				query = "UPDATE FMS7_LNG_SALES_MAPPING SET SERVICE_NM='SAC',SERVICE_CD='XXXXXXX',"
						+ "RULE_REMARK='Issued under GST Rule',SERVICE_DESC = '(Description Of Type Of Service To Be Given By Finance)' "
						+ "WHERE CONTRACT_TYPE IN ('R','C') ";
				stmt.executeUpdate(query);
				
				query = "UPDATE FMS7_LNG_SALES_MAPPING SET SUPPLIER_CD='1' "
						+ "WHERE CONTRACT_TYPE IN ('R','C','S','L','X','Y','Z') ";
				stmt.executeUpdate(query);
				
				query = "UPDATE FMS7_LNG_SALES_MAPPING SET SUPPLIER_CD='2' "
						+ "WHERE CONTRACT_TYPE IN ('1','2') ";
				stmt.executeUpdate(query);
				
				conn.commit();
			}
		}
		catch(Exception e)
		{
			conn.rollback();
			e.printStackTrace();
		}
	}
	String advadjFlg="";
	
	public String getAdvadjFlg() {
		return advadjFlg;
	}
	double tot_tax_amt1= 0;
	double tot_tax_amt2= 0;
	String contact_Suppl_State = "";
	String contact_Suppl_State_Code = "";
	String contact_Customer_Plant_State = "";
	String contact_Customer_State_Code = "";
	String sac_code = "", sac_name = "", rule_remark = "Issued under Rule-4A of the Service Tax Rules, 1994", service_desc = "";
	boolean date_flag = false;
	int gst_eff_dt = 20170701; //GST TAX STRUCTURE EFFECTIVE FROM 
	String new_inv_seq_no = "";
	boolean tax_gst = false;
	Vector customer_Invoice_Bank_Name = new Vector();
	String tcs_amt="0";
	String tcs_nm="";
	String tcs_sht_nm="";
	String fact="";
	String tcs_app_flag=""; 
	
	public void fetchInvoiceReport()throws SQLException,IOException
	{
		methodName = "fetchInvoiceReport()";
		try 
		{
			//System.out.println("STEP-1A: GET DETAILS: "+methodName);
			String tax_struct_cd = "0";
			String exchg_rate_dt = "";
			String formatted_exchg_rate_dt = "";
			
			queryString = "SELECT SERVICE_NM,SERVICE_CD,RULE_REMARK,SERVICE_DESC FROM FMS7_LNG_SALES_MAPPING WHERE "
					+ "CONTRACT_TYPE='"+contract_type+"' ";
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				sac_code = rset.getString(2)==null?"":rset.getString(2);
				sac_name = rset.getString(1)==null?"":rset.getString(1);
				rule_remark = rset.getString(3)==null?"":rset.getString(3);
				service_desc = rset.getString(4)==null?"":rset.getString(4);
			}
//			System.out.println("contract_type***"+contract_type);
			if(contract_type.equalsIgnoreCase("S"))
			{
				queryString = "SELECT SN_REF_NO " +
							  "FROM DLNG_SN_MST WHERE " +
				  			  "FLSA_NO="+fgsa_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_ref_no IS NOT NULL AND " +
				  			  "customer_cd="+customer_cd+" ORDER BY sn_rev_no DESC";
				
//				System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  " || ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  " || ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  " FROM DLNG_FLSA_MST WHERE " +
							  " FLSA_NO="+fgsa_no+" AND " +
							  " rev_no="+fgsa_rev_no+" AND " +
							  " customer_cd="+customer_cd+" " +
							  " ORDER BY rev_no DESC";
//				System.out.println("Signing Date Fetch Query For FGSA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM DLNG_SN_MST WHERE " +
				  			  "FLSA_NO="+fgsa_no+" AND FLSA_REV_NO="+fgsa_rev_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+customer_cd+"";
				System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT LOA_REF_NO " +
							  "FROM DLNG_LOA_MST WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND LOA_REF_NO IS NOT NULL AND " +
				  			  "customer_cd="+customer_cd+" ORDER BY loa_rev_no DESC";
				////System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_TENDER_MST WHERE " +
							  "tender_no="+fgsa_no+" AND " +
							  "customer_cd="+customer_cd+"";
				////System.out.println("Signing Date Fetch Query For TENDER Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM DLNG_LOA_MST WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+customer_cd+"";
				////System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("R"))
			{}
			else if(contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
			{}
			else if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{}
			
			queryString = "SELECT TO_CHAR(invoice_dt,'DD-Mon-YY'),TO_CHAR(due_dt,'DD-Mon-YY')," +
						  "contact_person_cd,tax_flag,sale_price,total_qty,gross_amt_usd,gross_amt_inr," +
						  "net_amt_inr,exchg_rate_value,tax_struct_cd,exchg_rate_cd," +
						  "TO_CHAR(exchg_rate_dt,'DD/MM/YYYY'),remark_1,remark_2," +
						  "offspec_flag,offspec_qty,offspec_rate,remark_3,tax_amt_inr " +
						  ",ADV_INV_NO,TO_CHAR(ADV_INV_DT,'DD-Mon-YY'),to_char(invoice_dt,'dd/mm/yyyy'),"
						  + "ADV_ADJ_FLG,NEW_INV_SEQ_NO,REMARK_SPECIFICATION " +  //HS20160903
						  "FROM DLNG_INVOICE_MST WHERE " +
						  "financial_year='"+invFinancialYear+"' AND " +
						  "hlpl_inv_seq_no="+hlplInvoiceNo+" AND " +
						  "contract_type='"+contract_type+"' AND FLAG!='A'";
			
			if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
					queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
			}
			
			System.out.println("Deeep STEP1A.1:DLNG_INVOICE_MST: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				double total_inv_qty = 0;
				double gross_amt = 0;
				double total_offspec_qty = 0;
				customer_Invoice_DT1 = rset.getString(23)==null?"":rset.getString(23);
				customer_Invoice_DT = rset.getString(1)==null?"":rset.getString(1);
				customer_Invoice_Due_DT = rset.getString(2)==null?"":rset.getString(2);
				contactPersonCd = rset.getString(3)==null?"0":rset.getString(3);
				customer_Invoice_Tax_Flag = rset.getString(4)==null?"V":rset.getString(4);
				invoice_Sales_Rate = rset.getString(5)==null?"0.0000":nf2.format(Double.parseDouble(rset.getString(5)));
				total_Invoice_Qty = rset.getString(6)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(6)));
				
				total_inv_qty = Double.parseDouble(rset.getString(6)==null?"0.00":rset.getString(6));
				gross_amt = Double.parseDouble(rset.getString(7)==null?"0.00":rset.getString(7));
				
				customer_Invoice_Gross_Amt_USD = rset.getString(7)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(7)));
				customer_Invoice_Gross_Amt_INR = rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8)));
				customer_Invoice_Net_Amt_INR = rset.getString(9)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(9)));
				customer_Invoice_Exchg_Rate = rset.getString(10)==null?"0.00":nf2.format(Double.parseDouble(rset.getString(10)));
				tax_struct_cd = rset.getString(11)==null?"0":rset.getString(11);
				customer_Invoice_Exchg_Rate_Cd = rset.getString(12)==null?"0":rset.getString(12);
				exchg_rate_dt = rset.getString(13)==null?"":rset.getString(13);
				remark_1 = rset.getString(14)==null?"":rset.getString(14);
				remark_2 = rset.getString(15)==null?"":rset.getString(15);
				
				offspec_Flag = rset.getString(16)==null?"N":rset.getString(16);
				total_Offspec_Qty = rset.getString(17)==null?"":nf3.format(Double.parseDouble(rset.getString(17)));
				offspec_Sales_Rate = rset.getString(18)==null?"":rset.getString(18);
				remark_3 = rset.getString(19)==null?"":rset.getString(19).trim();
				customer_Invoice_Tax_Amt_INR = rset.getString(20)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(20).trim()));
				
				advance_inv_no=rset.getString(21)==null?"":rset.getString(21);
				advance_inv_dt=rset.getString(22)==null?"":rset.getString(22);
				advadjFlg=rset.getString(24)==null?"NA":rset.getString(24);
				new_inv_seq_no = rset.getString(25)==null?"":rset.getString(25);
				rec_remark = rset.getString(26)==null?"":rset.getString(26);
				
				total_offspec_qty = Double.parseDouble(rset.getString(17)==null?"0.00":rset.getString(17));
				
				if(offspec_Flag.trim().equalsIgnoreCase("Y") && !total_Offspec_Qty.trim().equals("") && !offspec_Sales_Rate.trim().equals(""))
				{
					offspec_Amt_USD = nf3.format(total_offspec_qty*Double.parseDouble(offspec_Sales_Rate));
					total_Gas_Delivered = total_Invoice_Qty;
					total_Invoice_Qty = nf3.format(total_inv_qty+total_offspec_qty);
					gas_Delivered_Amt_USD = nf3.format(gross_amt-(total_offspec_qty*Double.parseDouble(offspec_Sales_Rate)));
					//gas_Delivered_Amt_USD = customer_Invoice_Gross_Amt_USD;
					//customer_Invoice_Gross_Amt_USD = nf3.format(gross_amt+(Double.parseDouble(total_Offspec_Qty)*Double.parseDouble(offspec_Sales_Rate)));
				}
				else
				{
					total_Gas_Delivered = total_Invoice_Qty;
					gas_Delivered_Amt_USD = customer_Invoice_Gross_Amt_USD;
				}
				
				
			}
			else
			{
				queryString = "SELECT TO_CHAR(invoice_dt,'DD-Mon-YY'),TO_CHAR(due_dt,'DD-Mon-YY')," +
						  "contact_person_cd,tax_flag,sale_price,total_qty,gross_amt_usd,gross_amt_inr," +
						  "net_amt_inr,exchg_rate_value,tax_struct_cd,exchg_rate_cd," +
						  "TO_CHAR(exchg_rate_dt,'DD/MM/YYYY'),remark_1,remark_2," +
						  "offspec_flag,offspec_qty,offspec_rate,remark_3,tax_amt_inr " +
						  ",ADV_INV_NO,TO_CHAR(ADV_INV_DT,'DD-Mon-YY'),to_char(invoice_dt,'dd/mm/yyyy'),"
						  + "ADV_ADJ_FLG,NEW_INV_SEQ_NO,REMARK_SPECIFICATION " +
						  "FROM DLNG_INVOICE_MST WHERE " +
						  "financial_year='"+invFinancialYear+"' AND " +
						  "hlpl_inv_seq_no="+hlplInvoiceNo+" AND " +
						  "contract_type='"+contract_type+"' AND FLAG!='A'";
				System.out.println("STEP1A.2:DLNG_INVOICE_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				
				if(rset.next())
				{
					double total_inv_qty = 0;
					double gross_amt = 0;
					double total_offspec_qty = 0;
					customer_Invoice_DT1 = rset.getString(23)==null?"":rset.getString(23);
					customer_Invoice_DT = rset.getString(1)==null?"":rset.getString(1);
					customer_Invoice_Due_DT = rset.getString(2)==null?"":rset.getString(2);
					contactPersonCd = rset.getString(3)==null?"0":rset.getString(3);
					customer_Invoice_Tax_Flag = rset.getString(4)==null?"V":rset.getString(4);
					invoice_Sales_Rate = rset.getString(5)==null?"0.0000":nf2.format(Double.parseDouble(rset.getString(5)));
					total_Invoice_Qty = rset.getString(6)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(6)));
					
					total_inv_qty = Double.parseDouble(rset.getString(6)==null?"0.00":rset.getString(6));
					gross_amt = Double.parseDouble(rset.getString(7)==null?"0.00":rset.getString(7));
					
					customer_Invoice_Gross_Amt_USD = rset.getString(7)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(7)));
					customer_Invoice_Gross_Amt_INR = rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8)));
					customer_Invoice_Net_Amt_INR = rset.getString(9)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(9)));
					customer_Invoice_Exchg_Rate = rset.getString(10)==null?"0.00":nf2.format(Double.parseDouble(rset.getString(10)));
					tax_struct_cd = rset.getString(11)==null?"0":rset.getString(11);
					customer_Invoice_Exchg_Rate_Cd = rset.getString(12)==null?"0":rset.getString(12);
					exchg_rate_dt = rset.getString(13)==null?"":rset.getString(13);
					remark_1 = rset.getString(14)==null?"":rset.getString(14);
					remark_2 = rset.getString(15)==null?"":rset.getString(15);
					
					offspec_Flag = rset.getString(16)==null?"N":rset.getString(16);
					total_Offspec_Qty = rset.getString(17)==null?"":nf3.format(Double.parseDouble(rset.getString(17)));
					offspec_Sales_Rate = rset.getString(18)==null?"":rset.getString(18);
					remark_3 = rset.getString(19)==null?"":rset.getString(19).trim();
					customer_Invoice_Tax_Amt_INR = rset.getString(20)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(20).trim()));
					
					advance_inv_no=rset.getString(21)==null?"":rset.getString(21);
					advance_inv_dt=rset.getString(22)==null?"":rset.getString(22);
					advadjFlg=rset.getString(24)==null?"NA":rset.getString(24);
					new_inv_seq_no = rset.getString(25)==null?"":rset.getString(25);
					rec_remark = rset.getString(26)==null?"":rset.getString(26);
					total_offspec_qty = Double.parseDouble(rset.getString(17)==null?"0.00":rset.getString(17));
					
					if(offspec_Flag.trim().equalsIgnoreCase("Y") && !total_Offspec_Qty.trim().equals("") && !offspec_Sales_Rate.trim().equals(""))
					{
						offspec_Amt_USD = nf3.format(total_offspec_qty*Double.parseDouble(offspec_Sales_Rate));
						total_Gas_Delivered = total_Invoice_Qty;
						total_Invoice_Qty = nf3.format(total_inv_qty+total_offspec_qty);
						gas_Delivered_Amt_USD = nf3.format(gross_amt-(total_offspec_qty*Double.parseDouble(offspec_Sales_Rate)));
						//gas_Delivered_Amt_USD = customer_Invoice_Gross_Amt_USD;
						//customer_Invoice_Gross_Amt_USD = nf3.format(gross_amt+(Double.parseDouble(total_Offspec_Qty)*Double.parseDouble(offspec_Sales_Rate)));
					}
					else
					{
						total_Gas_Delivered = total_Invoice_Qty;
						gas_Delivered_Amt_USD = customer_Invoice_Gross_Amt_USD;
					}
				}
				
			}
			System.out.println("bill_period_start_dt******"+bill_period_start_dt);
			String dtgst = bill_period_start_dt.substring(6,10)+bill_period_start_dt.substring(3,5)+bill_period_start_dt.substring(0,2);
			int dt2 = Integer.parseInt(dtgst);
			if(dt2>=gst_eff_dt) {
				date_flag = true;
			}
			if(rec_remark.trim().length()>1) {
				advance_inv_no = rec_remark;
			} else {
			if(advance_inv_no != "") {
				String same_no = advance_inv_no, diff_no = "";
				String invoice_dtl = advance_inv_no + " dated "+advance_inv_dt; 
				String invoice_dates = advance_inv_dt;
				String query = "SELECT ADV_INV_NO, NVL(TO_CHAR(ADV_INV_DT,'DD-Mon-YY'),'') "
						+ "FROM FMS8_ADV_INV_DTL "
						+ "WHERE FINANCIAL_YEAR = '"+invFinancialYear+"' AND CONTRACT_TYPE = '"+contract_type+"' "
						+ "AND HLPL_INV_SEQ_NO = '"+hlplInvoiceNo+"' AND ADV_INV_NO != '"+advance_inv_no+"' ";
				rset = stmt.executeQuery(query);
				while(rset.next())
				{
						String dt = rset.getString(2);
						if(!advance_inv_dt.trim().equals(dt.trim()) && !invoice_dates.contains(dt)) {
							if(diff_no.length()==0) {
								diff_no = rset.getString(1)+" dated "+rset.getString(2);
							} else {
								diff_no += " , "+ rset.getString(1)+" dated "+rset.getString(2);
							}
						} else {
							same_no += ", "+rset.getString(1);
						}
				}
				same_no += " dated "+advance_inv_dt;
				if(diff_no.length()!=0) {
					same_no += " , "+diff_no;
				}
				advance_inv_no = same_no; 
//				advance_inv_dt = invoice_dates;
			}}
			
			if(!exchg_rate_dt.equals(""))
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'fmddth') " +
							  "|| ' ' || TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'fmMonth') " +
							  "|| ' ' || TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'yyyy') " +
							  "FROM DUAL";
				////System.out.println("Formatted Exchange Rate Date Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					formatted_exchg_rate_dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}

			double tax_amt = 0;
			String tax_cd = "0";
			String tax_factor = "0.00";
			
			queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
						  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
						  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
						  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
			System.out.println("STEP-1A.3:FMS7_TAX_STRUCTURE_DTL: "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				tax_cd = rset.getString(1);
				tax_factor = rset.getString(2);
				////System.out.println("tax_cd--------"+tax_cd);
				if(rset.getString(3).equals("1"))
				{
					tax_amt = (Double.parseDouble(customer_Invoice_Gross_Amt_INR)*Double.parseDouble(rset.getString(2)))/100;
				}
				else if(rset.getString(3).equals("2"))
				{
					queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
								  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
					////System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString1);
			 		rset1=stmt1.executeQuery(queryString1);
			 		if(rset1.next())
			 		{
				 			if(rset1.getString(3).equals("1"))
							{
								tax_amt = (Double.parseDouble(customer_Invoice_Gross_Amt_INR)*Double.parseDouble(rset1.getString(2)))/100;
							}
							
				 			tax_amt = (tax_amt*Double.parseDouble(rset.getString(2)))/100;
			 		}
			 		else
			 		{
			 			tax_amt = 0;
			 		}
				}
				else
				{
					tax_amt = 0;
				}
				
				customer_Invoice_Tax_Code.add(tax_cd);
				customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
				
				if(date_flag && contract_type.equals("C")) {
					customer_Invoice_Tax_Amt.add(nf3.format(tax_amt));
				} else {
					customer_Invoice_Tax_Amt.add(nf3.format(tax_amt));
				}
				////System.out.println("customer_Invoice_Tax_Code--"+customer_Invoice_Tax_Code);
			}
			
			if(!customer_Invoice_Gross_Amt_INR.equalsIgnoreCase("")) {
				if(date_flag && contract_type.equals("C")) {
					customer_Invoice_Gross_Amt_INR = nf3.format(Math.round(Double.parseDouble(customer_Invoice_Gross_Amt_INR)));
				} else {
					customer_Invoice_Gross_Amt_INR = nf3.format(Double.parseDouble(customer_Invoice_Gross_Amt_INR));
				}
			}
				
//			System.out.println("customer_Invoice_Tax_Amt"+customer_Invoice_Tax_Amt+"=="+customer_Invoice_Gross_Amt_INR);
			for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
			{
				queryString = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
							  "tax_code="+customer_Invoice_Tax_Code.elementAt(i)+"";
				////System.out.println("Query For Fetching Tax Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					String tax_abbr = rset.getString(1).trim()==null?"":rset.getString(1).trim();
					String tax_nm = rset.getString(2).trim()==null?"":rset.getString(2).trim();
					if(tax_abbr.contains("GST")) {
						tax_gst = true;
					}
					customer_Invoice_Tax_Abbr.add(tax_abbr);
					customer_Invoice_Tax_Name.add(tax_nm);
				}
				else
				{
					customer_Invoice_Tax_Abbr.add("");
					customer_Invoice_Tax_Name.add("");
				}
			}
			////System.out.println("customer_Invoice_Tax_Name: "+customer_Invoice_Tax_Name);
			queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' '),addr_flag " +
						  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
						  "WHERE A.customer_cd='"+customer_cd+"' AND A.def_inv_flag='Y' AND A.seq_no="+contactPersonCd+" AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND B.seq_no="+contactPersonCd+" AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			System.out.println("STEP-1A.4:FMS7_CUSTOMER_CONTACT_MST: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				if(rset.getString(3).equals(" "))
				{
					contact_Person_Name_And_Designation = rset.getString(2).trim();
				}
				else
				{
					contact_Person_Name_And_Designation = rset.getString(2).trim()+" ("+rset.getString(3).trim()+")";
				}
				contact_addr_flag = rset.getString(4)==null?"":rset.getString(4);
			}
			
						
			if(contact_addr_flag.trim().equalsIgnoreCase("R") || contact_addr_flag.trim().equalsIgnoreCase("C") || contact_addr_flag.trim().equalsIgnoreCase("B"))
			{
				queryString = "SELECT addr,city,pin,state " +
						  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
						  "WHERE A.customer_cd="+customer_cd+" AND A.address_type='"+contact_addr_flag+"' AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"' AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			System.out.println("Hiren***Customer Address Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
				contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
				contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
				contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
			}
			
			queryString = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
					+ "AND SEQ_NO = '"+customer_plant_seq_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
					+ "WHERE B.SEQ_NO='"+customer_plant_seq_no+"' AND B.CUSTOMER_CD='"+customer_cd+"' AND B.EFF_DT <= TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			System.out.println("Fetchinng State-8888-"+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				contact_Customer_Plant_State = rset.getString(1)==null?"":rset.getString(1);
			} else {
				contact_Customer_Plant_State = "";
			}
				
			}
			else
			{
				String new_plant_no="";
				if(!contact_addr_flag.equalsIgnoreCase(""))
				new_plant_no = contact_addr_flag.trim().substring(1);
				
				if(new_plant_no.length()>=1)
				{
					queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+new_plant_no+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+new_plant_no+"' AND " +
								  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
					
					queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
							+ "AND SEQ_NO = '"+new_plant_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
							+ "WHERE B.SEQ_NO='"+new_plant_no+"' AND B.CUSTOMER_CD='"+customer_cd+"' AND B.EFF_DT <= TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
				}
				else
				{
					queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd='"+customer_cd+"' AND A.seq_no='"+customer_plant_seq_no+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+customer_plant_seq_no+"' AND " +
								  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
					
					queryString1 = "SELECT PLANT_STATE FROM FMS7_CUSTOMER_PLANT_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
							+ "AND SEQ_NO = '"+customer_plant_seq_no+"' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B "
							+ "WHERE B.SEQ_NO='"+customer_plant_seq_no+"' AND B.CUSTOMER_CD='"+customer_cd+"' AND B.EFF_DT <= TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
				}
				
				System.out.println("STEP-1A.6:FMS7_CUSTOMER_PLANT_DTL: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
					contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
					contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
					contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
				}
				
				rset1 = stmt.executeQuery(queryString1);
				if(rset1.next()) {
					contact_Customer_Plant_State = rset1.getString(1)==null?"":rset1.getString(1);
				} else {
					contact_Customer_Plant_State = "";
				}
			}
			
			if(contact_Customer_Plant_State!="") {
				queryString = "SELECT STATE_CODE FROM STATE_MST WHERE UPPER(STATE_NM) = '"+contact_Customer_Plant_State.toUpperCase()+"' ";
				rset = stmt.executeQuery(queryString);
				if(rset.next()) {
					contact_Customer_State_Code = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			
			queryString = "SELECT customer_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
			  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY') " +
			  "FROM FMS7_CUSTOMER_MST A " +
			  "WHERE A.customer_cd='"+customer_cd+"' AND " +
			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
			  "WHERE A.customer_cd=B.customer_cd AND " +
			  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			System.out.println("STEP-1A.7:FMS7_CUSTOMER_MST: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Customer_Name = rset.getString(1)==null?"":rset.getString(1);
				
			}
			//HERE BK vSTAT_NM
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT A.stat_no, TO_CHAR(A.eff_dt,'DD-MM-YYYY'), B.stat_nm, B.stat_cd " +
							  "FROM FMS7_CUSTOMER_PLANT_TAX_CDS A, FMS7_GOVT_STAT_NO B " +
							  "WHERE A.stat_cd=B.stat_cd AND A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND (B.stat_type='S' OR B.STAT_TYPE='G') " +
							  "ORDER BY A.stat_cd";
				////System.out.println("STEP-1A.8:FMS7_CUSTOMER_PLANT_TAX_CDS:FMS7_GOVT_STAT_NO: "+queryString);
				////System.out.println("Customer Plant's Tax Names Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String stat_nm = rset.getString(3)==null?"":rset.getString(3);
					String stat_no = rset.getString(1)==null?"":rset.getString(1);
					String stat_eff_dt = rset.getString(2)==null?"":rset.getString(2);
					
					if(!stat_nm.trim().equals(""))// && !stat_no.trim().equals("") && !stat_eff_dt.trim().equals("")
					{
						vSTAT_NO.add(stat_no.trim());
						vSTAT_EFF_DT.add(stat_eff_dt.trim());
						vSTAT_NM.add(stat_nm.trim());
						vSTAT_CD.add(rset.getString(4)==null?"0":rset.getString(4));
					}
				}
			}
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{}			
			
			queryString = "SELECT addr,city,pin,NVL(STATE,'N/A') " +
						  "FROM FMS7_SUPPLIER_ADDRESS_MST A " +
						  "WHERE A.supplier_cd=1 AND A.address_type='R' AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			////System.out.println("Supplier Address Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Suppl_Person_Address = rset.getString(1)==null?"":rset.getString(1);
				contact_Suppl_Person_City = rset.getString(2)==null?"":rset.getString(2);
				contact_Suppl_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
				contact_Suppl_State = rset.getString(4)==null?"":rset.getString(4);
				
				if(!contact_Suppl_State.equals("")) {
					queryString = "SELECT NVL(STATE_CODE,'N/A') FROM STATE_MST WHERE UPPER(STATE_NM) = '"+contact_Suppl_State.toUpperCase()+"' ";
					rset = stmt.executeQuery(queryString);
					if(rset.next()) {
						contact_Suppl_State_Code = rset.getString(1);
					}
				}
			}
			
			//BK20160211
			queryString = "SELECT supplier_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
						  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY'),"
						  + "PAN_NO,TO_CHAR(PAN_ISSUE_DT,'DD-MM-YYYY'), "
						  + "GSTIN_NO,TO_CHAR(GSTIN_DT,'DD/MM/YYYY') " +
						  "FROM FMS7_SUPPLIER_MST A " +
						  "WHERE A.supplier_cd=1 AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			////System.out.println("===QUERY FMS7_SUPPLIER_MST PAN === "+queryString);
			////System.out.println("Supplier Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Suppl_Name = rset.getString(1)==null?"":rset.getString(1);
				contact_Suppl_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Suppl_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Suppl_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Suppl_CST_DT = rset.getString(5)==null?"":rset.getString(5);
				contact_Suppl_Service_Tax_NO = rset.getString(6)==null?"":rset.getString(6);
				contact_Suppl_Service_Tax_DT = rset.getString(7)==null?"":rset.getString(7);
				contact_Suppl_PAN_NO = rset.getString(8)==null?"":rset.getString(8);	//BK20160211
				contact_Suppl_PAN_DT = rset.getString(9)==null?"":rset.getString(9);	//BK20160211
				contact_Suppl_GSTIN_NO = rset.getString(10)==null?"":rset.getString(10);	//RS01062017
				contact_Suppl_GSTIN_DT = rset.getString(11)==null?"":rset.getString(11);	//RS01062017
			}
			
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'),'DD-Mon-YY'),TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'),'DD-Mon-YY') FROM DUAL";
			//System.out.println("Bill Period Start & End Dates Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				customer_Invoice_Start_DT = rset.getString(1)==null?"":rset.getString(1);
				customer_Invoice_End_DT = rset.getString(2)==null?"":rset.getString(2);
			}
			
			if(!customer_Invoice_Exchg_Rate_Cd.equals(sbi_avg_tt_selling_buying_cd))
			{
				queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+customer_Invoice_Exchg_Rate_Cd+" " +
							  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
				////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_Exchg_Rate_Code.add(customer_Invoice_Exchg_Rate_Cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					customer_Invoice_Exchg_Rate_Code.add(customer_Invoice_Exchg_Rate_Cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add("0.00");
				}
			}
			else
			{
				queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_buying_cd+" " +
							  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
				////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_Exchg_Rate_Code.add(sbi_tt_buying_cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					customer_Invoice_Exchg_Rate_Code.add(sbi_tt_buying_cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add("0.00");
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_selling_cd+" " +
							  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
				////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_Exchg_Rate_Code.add(sbi_tt_selling_cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					customer_Invoice_Exchg_Rate_Code.add(sbi_tt_selling_cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add("0.00");
				}
			}
			
			for(int i=0; i<customer_Invoice_Exchg_Rate_Code.size(); i++)
			{
				queryString = "SELECT A.exc_rate_nm,bank_abbr FROM FMS7_CONT_EXCHG_RATE_MST A WHERE " +
							  "A.exc_rate_cd="+customer_Invoice_Exchg_Rate_Code.elementAt(i)+"";
				////System.out.println("Query For Fetching Exchange Rate Name For Invoicing From FMS7_CONT_EXCHG_RATE_MST Table = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_Exchg_Rate_Name.add(rset.getString(1)==null?"":rset.getString(1));
					customer_Invoice_Bank_Name.add(rset.getString(2)==null?"":rset.getString(2));
				}
				else
				{
					customer_Invoice_Exchg_Rate_Name.add("");
					customer_Invoice_Bank_Name.add("");
				}
			}
			
			//Following Code Is Fetching Exchange Rates For Specified Duration (e.g. Whole Week, First Fortnight, Second Fortnight, Or For Whole Month, etc.) ...
			//Following Logic Has Been Introduced By Samik Shah On 8th June, 2010 ...
			if(bill_period_start_dt!=null && !bill_period_start_dt.equals("") && !bill_period_start_dt.equals(" ") && bill_period_end_dt!=null && !bill_period_end_dt.equals("") && !bill_period_end_dt.equals(" "))
			{
				int date_part = 0;
				String date_portion = "";
				queryString3 = "SELECT TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') - TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY') +1 FROM DUAL";
//			System.out.println("Query To FindOut Dates For Billing Duration = "+queryString3);
				//here
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						for(int i=0; i<Integer.parseInt(rset3.getString(1)); i++)
						{
							date_part = Integer.parseInt(bill_period_start_dt.substring(0,2));
							date_part += i;  
							if(date_part>9)
							{
								date_portion = date_part+bill_period_start_dt.substring(2);
							}
							else if(date_part<=9 && date_part>0)
							{
								date_portion = "0"+date_part+bill_period_start_dt.substring(2);
							}
							else
							{
								date_portion = bill_period_end_dt;
							}
							
							invoice_Period_Dates.add(date_portion);  //1
						}
					}					
				}
//				invoice_Period_Dates.removeElementAt(invoice_Period_Dates.size()-1);
//				System.out.println("JAVA invoice_Period_Dates.size(): "+invoice_Period_Dates.size());
				
				double SN_Qty_Before_Inv_Period_Start = 0;
				double SN_Offspec_Qty_Before_Inv_Period_Start = 0;
								
				queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
							  "WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND " +
							  "gas_dt<TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY')";
				if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
						queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
				}
				//System.out.println("STEP-1A.9:DLNG_DAILY_ALLOCATION_DTL: "+queryString);
				////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS/ltcora/cn Before This Invoice Period From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					SN_Qty_Before_Inv_Period_Start = Double.parseDouble(rset.getString(1));
				}
				
				queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
				  			  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL " +
							  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
							  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
							  "sn_no="+sn_no+" AND " +
							  "contract_type='"+contract_type+"' AND " +
							  "gas_dt<TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY')";
				
				if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
						queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
				}
				
				////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS/LTCORA/CN Before This Invoice Period From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
				
//				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					if(rset.getString(3).trim().equalsIgnoreCase("N"))
					{
						SN_Offspec_Qty_Before_Inv_Period_Start += Double.parseDouble(rset.getString(1));
					}
				}
				
				SN_Qty_Before_Inv_Period_Start -= SN_Offspec_Qty_Before_Inv_Period_Start;
				
				double Total_Gas_Delv_Qty = 0;
				liability_exist_flag = "N";
								
				for(int i=0; i<invoice_Period_Dates.size(); i++)
				{
					double Buyer_Nom_Qty = 0;
					double Seller_Nom_Qty = 0;
					double mdcq_percentage = 100;
					double DCQ_Qty = 0;
					double MDCQ_Qty = 0;
					double PNQ_Qty = 0;
					double Seller_RE_Qty = 0;
					double Gas_Delv_RE_Qty = 0;
					double Gas_Delv_Qty = 0;
					double FM_Qty = 0;
					double Offspec_Qty = 0;
					double Offspec_Qty_Accepted = 0;
					double Buyer_Suspension_Qty = 0;
					double Failure_To_Delv_Qty = 0;
					double Offspec_Rate = 0; 
					String Offspec_Flag = "N";
					double SN_Qty_On_Inv_Period_Dates = 0;
					double total_buyer_nom_for_gas_day = 0;
					
					queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
								  "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
					   			  "WHERE A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
					   			  "AND A.nom_rev_no=" +
								  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
								  "WHERE B.sn_no=A.sn_no " +
								  //"AND B.sn_rev_no=A.sn_rev_no " + //Commented By Samik Shah On 27th May, 2011 ...
								  "AND B.fgsa_no=A.fgsa_no " +
								  //"AND B.fgsa_rev_no=A.fgsa_rev_no " + //Commented By Samik Shah On 27th May, 2011 ...
								  "AND B.customer_cd=A.customer_cd AND B.transporter_cd=A.transporter_cd " +
								  "AND B.contract_type=A.contract_type AND B.gas_dt=A.gas_dt " +
								  "AND B.plant_seq_no=A.plant_seq_no)";
					
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
							queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
					}
					
					////System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						total_buyer_nom_for_gas_day = rset.getDouble(1);
					}
					
					queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
								  "WHERE MAPPING_ID='"+customer_inv_mapping_id+"' AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
					
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
							queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
					}
					
					////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						SN_Qty_On_Inv_Period_Dates = Double.parseDouble(rset.getString(1));
					}
					
					queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
		  			  			  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  "sn_no="+sn_no+" AND " +
								  "contract_type='"+contract_type+"' AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
					
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
							queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
					}
					
					////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
					
//					rset = stmt.executeQuery(queryString);
					while(rset.next())
					{
						if(rset.getString(3).trim().equalsIgnoreCase("N"))
						{
							SN_Qty_On_Inv_Period_Dates -= Double.parseDouble(rset.getString(1));
						}
					}
		
					if(contract_type.equalsIgnoreCase("S"))
					{
						queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_SN_MST " +
									  "WHERE customer_cd="+customer_cd+" AND " +
									  "flsa_no="+fgsa_no+" AND " +
									  //"fgsa_rev_no="+fgsa_rev_no+" AND " +
									  "sn_no="+sn_no+" AND " +
									  "sn_rev_no="+sn_rev_no+"";
						
						////System.out.println("FMS7-SN-INV:DCQ:QRY-I0001:DLNG_SN_MST:SELECT: "+queryString);
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
							mdcq_percentage = Double.parseDouble(rset.getString(2));
							MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
						}
						
						queryString4 = "SELECT NVL(A.dcq,'0') FROM DLNG_SN_DCQ_DTL A " +
						   			   "WHERE A.customer_cd="+customer_cd+" AND A.flsa_no="+fgsa_no+" " +
						   			   //"AND A.fgsa_rev_no="+fgsa_rev_no+" " +
						   			   "AND A.sn_no="+sn_no+" " +
						   			   "AND A.sn_rev_no="+sn_rev_no+" " +
						   			   "AND A.from_dt<=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
						   			   "AND A.to_dt>=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
						   			   "ORDER BY A.from_dt DESC";
						////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
						////System.out.println("FMS7-SN-INV:DCQ:QRY-I0002:DLNG_SN_DCQ_DTL:SELECT: "+queryString4);
						rset4 = stmt4.executeQuery(queryString4);
						if(rset4.next())
						{
							//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
							if(Double.parseDouble(rset4.getString(1))>=0)
							{
								DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
								MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
							}
						}
											
						////System.out.println("MDCQ_Qty = "+MDCQ_Qty);
					}
					else if(contract_type.equalsIgnoreCase("L"))
					{
						queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_LOA_MST " +
									  "WHERE customer_cd="+customer_cd+" AND tender_no="+fgsa_no+" AND " +
									  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+"";
						
					//	////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_LOA_MST Table = "+queryString);
					//	//System.out.println("FMS7-LOA-INV:DCQ:QRY-I0003:DLNG_LOA_MST:SELECT: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
							mdcq_percentage = Double.parseDouble(rset.getString(2));
							MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
						}
						
						queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
						   			   "WHERE A.customer_cd="+customer_cd+" AND A.tender_no="+fgsa_no+" " +
						   			   "AND A.loa_no="+sn_no+" " +
						   			   "AND A.loa_rev_no="+sn_rev_no+" " +
						   			   "AND A.from_dt<=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
						   			   "AND A.to_dt>=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
						   			   "ORDER BY A.from_dt DESC";
						////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
						////System.out.println("FMS7-LOA-INV:DCQ:QRY-I0004:FMS7_LOA_DCQ_DTL:SELECT: "+queryString4);
						rset4 = stmt4.executeQuery(queryString4);
						if(rset4.next())
						{
							//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
							if(Double.parseDouble(rset4.getString(1))>=0)
							{
								DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
								MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
							}
						}
					}
					else if(contract_type.equalsIgnoreCase("R"))
					{}
					else if(contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
					{}
					else if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{}
					
					invoice_Period_DCQ.add(nf.format(DCQ_Qty));
					
					Buyer_Nom_Qty = 0;
					
					queryString1 = "SELECT DISTINCT(transporter_cd) FROM FMS7_DAILY_BUYER_NOM_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  "sn_no="+sn_no+" AND " +
								  "contract_type='"+contract_type+"' AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "plant_seq_no="+customer_plant_seq_no+"";
					
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
							queryString1+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
					}
					////System.out.println("FMS7-BUYER-INV:BUY-NOM-QTY:QRY-I0007:FMS7_DAILY_BUYER_NOM_DTL:SELECT: "+queryString1);
					////System.out.println("Query For Fetching Buyer Nom Dtl - Transporter From FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						
						if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
						{
							queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_BUYER_NOM_DTL A " +
						  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
						  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
						  	  "A.sn_no="+sn_no+" AND " +
						  	  "A.contract_type='"+contract_type+"' AND " +
						  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
						  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
						  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
						  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
						  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
						  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
						  	  "B.sn_no="+sn_no+" AND " +
						  	  "B.contract_type='"+contract_type+"' AND " +
						  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
						  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
						  	  "B.transporter_cd="+rset1.getString(1)+" AND MAPPING_ID='"+customer_inv_mapping_id+"')" +
						  	  " AND MAPPING_ID='"+customer_inv_mapping_id+"'";
							
						}
						else
						{
							queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_BUYER_NOM_DTL A " +
						  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
						  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
						  	  "A.sn_no="+sn_no+" AND " +
						  	  "A.contract_type='"+contract_type+"' AND " +
						  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
						  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
						  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
						  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
						  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
						  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
						  	  "B.sn_no="+sn_no+" AND " +
						  	  "B.contract_type='"+contract_type+"' AND " +
						  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
						  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
						  	  "B.transporter_cd="+rset1.getString(1)+")";
						}
						
						////System.out.println("FMS7-BUYER-INV:BUY-NOM-QTY:QRY-I0007A:FMS7_DAILY_BUYER_NOM_DTL:SELECT: "+queryString);
						////System.out.println("Query For Fetching Buyer Nom QTY From FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString);
					
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							Buyer_Nom_Qty += Double.parseDouble(rset.getString(1));
						}
					}
					
					invoice_Period_Buyer_Nom_Qty.add(nf.format(Buyer_Nom_Qty));
					
					Seller_Nom_Qty = 0;
					Seller_RE_Qty = 0;
										
					queryString1 = "SELECT DISTINCT(transporter_cd) FROM FMS7_DAILY_SELLER_NOM_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  "sn_no="+sn_no+" AND " +
								  "contract_type='"+contract_type+"' AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "plant_seq_no="+customer_plant_seq_no+"";
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
							queryString1+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
					}
					
				////System.out.println("HS: -FMS7-SELLER-INV:SEL-NOM-QTY:QRY-I0008:FMS7_DAILY_SELLER_NOM_DTL:SELECT: "+queryString1);
				//	//System.out.println("Query For Fetching Seller Nom Dtl - Transporter From FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						
						if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
						{
							queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_SELLER_NOM_DTL A " +
						  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
						  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
						  	  "A.sn_no="+sn_no+" AND " +
						  	  "A.contract_type='"+contract_type+"' AND " +
						  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
						  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
						  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
						  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
						  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
						  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
						  	  "B.sn_no="+sn_no+" AND " +
						  	  "B.contract_type='"+contract_type+"' AND " +
						  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
						  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
						  	  "B.transporter_cd="+rset1.getString(1)+" " +
						  	  "AND MAPPING_ID='"+customer_inv_mapping_id+"') " +
						  	  "AND MAPPING_ID='"+customer_inv_mapping_id+"'";
							
							
						}
						else
						{
						queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
								  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  	  "A.sn_no="+sn_no+" AND " +
								  	  "A.contract_type='"+contract_type+"' AND " +
								  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
								  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
								  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
								  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
								  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  	  "B.sn_no="+sn_no+" AND " +
								  	  "B.contract_type='"+contract_type+"' AND " +
								  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
								  	  "B.transporter_cd="+rset1.getString(1)+")";
						
						}
						////System.out.println("FMS7-SELLER-INV:SEL-NOM-QTY:QRY-I0008A:FMS7_DAILY_SELLER_NOM_DTL:SELECT: "+queryString);
						////System.out.println("Query For Fetching Seller Nom QTY From FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString);
					
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							Seller_Nom_Qty += Double.parseDouble(rset.getString(1));
						}
					}
					
					MDCQ_Qty = Double.parseDouble(nf5.format(MDCQ_Qty));
					PNQ_Qty = 0;
					double re_qty = 28952; //Introduced By Samik Shah On 7th May, 2011 ...
					
					if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("T") || contract_type.trim().equalsIgnoreCase("C"))
					{
						if(total_buyer_nom_for_gas_day<re_qty)
						{
							PNQ_Qty = 0;
							Seller_RE_Qty = Seller_Nom_Qty;
						}
						else
						{
							if(Buyer_Nom_Qty<MDCQ_Qty)
							{
								PNQ_Qty = Buyer_Nom_Qty;
							}
							else
							{
								PNQ_Qty = MDCQ_Qty;
							}
							
							PNQ_Qty = Double.parseDouble(nf.format(PNQ_Qty));
												
							Seller_RE_Qty = Seller_Nom_Qty - PNQ_Qty;
							
							if(Seller_RE_Qty<0)
							{
								Seller_RE_Qty = 0;
							}
						}
					}
					else
					{
						if(total_buyer_nom_for_gas_day<re_qty)
						{
							PNQ_Qty = 0;
							Seller_RE_Qty = Seller_Nom_Qty;
						}
						else
						{
							if(Buyer_Nom_Qty<MDCQ_Qty)
							{
								PNQ_Qty = Buyer_Nom_Qty;
							}
							else
							{
								PNQ_Qty = MDCQ_Qty;
							}
							
							PNQ_Qty = Double.parseDouble(nf.format(PNQ_Qty));
							
							Seller_RE_Qty = Seller_Nom_Qty - PNQ_Qty;
							
							if(Seller_RE_Qty<0)
							{
								Seller_RE_Qty = 0;
							}
						}
					}
									////System.out.println("PNQ_Qty--->>>>"+PNQ_Qty+"-Seller_RE_Qty-"+Seller_RE_Qty);	
					invoice_Period_Seller_Nom_PNQ.add(nf.format(PNQ_Qty));
					invoice_Period_Seller_Nom_RE_Qty.add(nf.format(Seller_RE_Qty));
							
					queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
								  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  "sn_no="+sn_no+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "contract_type='"+contract_type+"'";
					
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
							queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
					}
				//	//System.out.println("FMS7-INV:SEL-ALLOC-QTY:QRY-I0009:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString);
					////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);
					
//					rset = stmt.executeQuery(queryString);
					while(rset.next())
					{
						Offspec_Flag = rset.getString(3);
						if(Offspec_Flag.trim().equalsIgnoreCase("N"))
						{
							Offspec_Qty += Double.parseDouble(rset.getString(1));
						}
						else if(Offspec_Flag.trim().equalsIgnoreCase("Y"))
						{
							Offspec_Qty_Accepted += Double.parseDouble(rset.getString(1));
						}
						FM_Qty += Double.parseDouble(rset.getString(2));
					}					
					
					
					queryString = "SELECT NVL(buy_suspen_qty,'0.00'),NVL(fail_delv_qty,'0.00') " +
								  "FROM FMS7_LIABILITY_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND " +
								  "fgsa_no="+fgsa_no+" AND " +
								  "sn_no="+sn_no+" AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "contract_type='"+contract_type+"'";
					////System.out.println("FMS7-LIA-INV:LIABILITY-QTY:QRY-I00010:FMS7_LIABILITY_DTL:SELECT: "+queryString);
					////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						Buyer_Suspension_Qty = rset.getDouble(1);
						Failure_To_Delv_Qty = rset.getDouble(2);
					}
					
					if(Offspec_Qty>=0.01)
					{
						invoice_Period_Buyer_Off_Spec_Qty.add(nf.format(Offspec_Qty));
						liability_exist_flag = "Y";
					}
					else
					{
						invoice_Period_Buyer_Off_Spec_Qty.add("");
					}
					
					if(Buyer_Suspension_Qty>=0.01)
					{
						invoice_Period_Buyer_Suspension_Qty.add(nf.format(Buyer_Suspension_Qty));
						liability_exist_flag = "Y";
					}
					else
					{
						invoice_Period_Buyer_Suspension_Qty.add("");
					}
					
					if(Failure_To_Delv_Qty>=0.01)
					{
						invoice_Period_Delv_Failure_Qty.add(nf.format(Failure_To_Delv_Qty));
						liability_exist_flag = "Y";
					}
					else
					{
						invoice_Period_Delv_Failure_Qty.add("");
					}
					
					if(FM_Qty>=0.01)
					{
						invoice_Period_FM_Qty.add(nf.format(FM_Qty));
						liability_exist_flag = "Y";
					}
					else
					{
						invoice_Period_FM_Qty.add("");
					}
					
					//Logic To Be Developed For Following Quantities Will Be Done Later ... 
					invoice_Period_Buyer_Shortfall_Qty.add("");
					invoice_Period_Total_Shortfall_Qty.add("");
					invoice_Period_LD_Credit_Payable.add("");					
					
					
					Gas_Delv_RE_Qty = 0;
					Gas_Delv_Qty = 0;
					
					queryString = "SELECT NVL(daily_qty,'0.00') FROM DLNG_INVOICE_DTL WHERE " +
								  "financial_year='"+invFinancialYear+"' AND " +
								  "hlpl_inv_seq_no="+hlplInvoiceNo+" AND " +
								  "allocation_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "contract_type='"+contract_type+"'";
					
					
					////System.out.println("FMS7-INV:DELV-QTY:QRY-I00011:DLNG_INVOICE_DTL:SELECT: "+queryString);
					////System.out.println("Invoice & Due Dates Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(Offspec_Qty_Accepted>=0.01)
						{
							Gas_Delv_Qty = Double.parseDouble(rset.getString(1))+Offspec_Qty_Accepted;
						}
						else
						{
							Gas_Delv_Qty = Double.parseDouble(rset.getString(1));
						}
					}
					
					PNQ_Qty = 0;
					
					if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("T") || contract_type.trim().equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
						/*if(Gas_Delv_Qty<re_qty)
						{
							PNQ_Qty = 0;
							Gas_Delv_RE_Qty = Gas_Delv_Qty;
						}
						else
						{*/
							if(Gas_Delv_Qty<MDCQ_Qty)
							{
								PNQ_Qty = Gas_Delv_Qty;
							}
							else
							{
								PNQ_Qty = MDCQ_Qty;
							}
												
							Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;
							
							if(Gas_Delv_RE_Qty<0)
							{
								Gas_Delv_RE_Qty = 0;
							}
						//}
					}
					else
					{
						if(Gas_Delv_Qty<MDCQ_Qty)
						{
							PNQ_Qty = Gas_Delv_Qty;
						}
						else
						{
							PNQ_Qty = MDCQ_Qty;
						}
											
						Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;
						
						if(Gas_Delv_RE_Qty<0)
						{
							Gas_Delv_RE_Qty = 0;
						}
					}
					
					/*Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;
					
					if(Gas_Delv_RE_Qty<0)
					{
						Gas_Delv_RE_Qty = 0;
					}*/
					invoice_Period_Gas_Delivered_PNQ.add(nf.format(PNQ_Qty));
					invoice_Period_Gas_Delivered_Re_Qty.add(nf.format(Gas_Delv_RE_Qty));
					invoice_Period_Gas_Delivered_Total_Qty.add(nf.format(Gas_Delv_Qty));
					
					Total_Gas_Delv_Qty += Gas_Delv_Qty;
					
					invoice_Period_Cumulative_Qty.add(nf.format(Total_Gas_Delv_Qty));
					SN_Qty_Before_Inv_Period_Start += SN_Qty_On_Inv_Period_Dates;
					invoice_Period_Cumulative_SN_Qty.add(nf.format(SN_Qty_Before_Inv_Period_Start));
				}
				
				
				if(!date_portion.trim().equals("") && date_portion.trim().length()==10)
				{
					String period_start_dt = "";
					String period_end_dt = "";
					
					month = date_portion.trim().substring(3,5);
					year = date_portion.trim().substring(6);
								
					////System.out.println("billCycle = "+billCycle);
					
					if(billCycle.equals("2") || billCycle.equals("1")) //1st-Fortnightly or 2nd-Fortnightly Invoice ...
					{
						if(billCycle.equals("2")) //2nd-Fortnightly Invoice ...
						{
							period_start_dt = "16"+"/"+month+"/"+year;
						}
						else  //1st-Fortnightly Invoice ...
						{
							period_start_dt = "01"+"/"+month+"/"+year;
						}
						
						if(billCycle.equals("1"))  //1st-Fortnightly Invoice ...
						{
							period_end_dt = "15"+"/"+month+"/"+year;
						}
						else  //2nd-Fortnightly Invoice ...
						{
							queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
					//		//System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								period_end_dt = rset.getString(1);
							}
						}
						
					//	//System.out.println("===JAVA period_start_dt:=== "+period_start_dt);
					//	//System.out.println("===JAVA period_end_dt:=== "+period_end_dt);
					}
					else if(billCycle.equals("3")) //1st-Weekly Invoice ...
					{
						period_start_dt = "01"+"/"+month+"/"+year;
						period_end_dt = "07"+"/"+month+"/"+year;
					}
					else if(billCycle.equals("4")) //2nd-Weekly Invoice ...
					{
						period_start_dt = "08"+"/"+month+"/"+year;
						period_end_dt = "14"+"/"+month+"/"+year;
					}
					else if(billCycle.equals("5")) //3rd-Weekly Invoice ...
					{
						period_start_dt = "15"+"/"+month+"/"+year;
						period_end_dt = "21"+"/"+month+"/"+year;
					}
					else if(billCycle.equals("6")) //4th-Weekly Invoice ...
					{
						period_start_dt = "22"+"/"+month+"/"+year;
						period_end_dt = "28"+"/"+month+"/"+year;
					}
					else if(billCycle.equals("9")) //5th-Weekly Invoice ...
					{
						period_start_dt = "29"+"/"+month+"/"+year;
						
						queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
						////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							period_end_dt = rset.getString(1);
						}
					}
					else if(billCycle.equals("7"))  //Monthly Invoice ...
					{
						period_start_dt = "01"+"/"+month+"/"+year;
						
						queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
						//System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							period_end_dt = rset.getString(1);
						}
					}
					
					String new_period_end_dt = "";
					String new_period_start_dt = "";
					int date_diff = 0;
					
					if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
					{
						queryString = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') " +
						  " FROM DLNG_ALLOC_MST " +
						  " WHERE  gas_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND " +
						  " ENTRY_TOT_ENE>=0.99 " +
						  " AND MAPPING_ID='"+customer_inv_mapping_id+"' " +
						  " ORDER BY gas_dt DESC";
							
					}
					else
					{
						queryString = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') " +
								  " FROM DLNG_ALLOC_MST " +
								  " WHERE  gas_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY') AND " +
								  " ENTRY_TOT_ENE>=0.99 " +
								  " AND MAPPING_ID='"+customer_inv_mapping_id+"' " +
								  " ORDER BY gas_dt DESC";
					}
					
					////System.out.println("FMS7-INV:END-DATE:QRY-I00012:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						new_period_end_dt = rset.getString(1);
					}
					
					if(!new_period_end_dt.trim().equals("") && new_period_end_dt.trim().length()==10)
					{
						queryString = "SELECT " +
									  "TO_DATE('"+new_period_end_dt+"','dd/mm/yyyy')" +
									  "-" +
									  "TO_DATE('"+invoice_Period_Dates.elementAt(invoice_Period_Dates.size()-1)+"','dd,mm/yyyy') " +
									  "FROM DUAL";
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							date_diff = rset.getInt(1);
							
							if(date_diff>0)
							{
								queryString2 = "SELECT TO_CHAR(TO_DATE('"+invoice_Period_Dates.elementAt(invoice_Period_Dates.size()-1)+"','dd,mm/yyyy')+1,'dd/mm/yyyy') FROM DUAL";
								rset2 = stmt2.executeQuery(queryString2);
								if(rset2.next())
								{
									new_period_start_dt = rset2.getString(1);
								}
							}
						}
					}
					
					if(date_diff>0 && !new_period_start_dt.trim().equals("") && new_period_start_dt.trim().length()==10)
					{
						for(int i=0; i<date_diff; i++)
						{
							double SN_Qty_On_Inv_Period_Dates = 0;
							double mdcq_percentage = 100;
							double DCQ_Qty = 0;
							double MDCQ_Qty = 0;
							double PNQ_Qty = 0;
							
							
							queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
									  " WHERE MAPPING_ID = '"+customer_inv_mapping_id+"' "
									 + " AND gas_dt<TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY')";
							
							if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
							{
									queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
							}
							
							////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
							
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								SN_Qty_On_Inv_Period_Dates = Double.parseDouble(rset.getString(1));
							}
							
							String period_dt = "";
							
							queryString = "SELECT TO_CHAR(TO_DATE('"+new_period_start_dt+"','dd/mm/yyyy')+"+i+",'dd/mm/yyyy') FROM DUAL";
							
							////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
							
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								period_dt = rset.getString(1);
							}
							
							if(contract_type.equalsIgnoreCase("S"))
							{
								queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_SN_MST " +
											  "WHERE customer_cd="+customer_cd+" AND " +
											  "flsa_no="+fgsa_no+" AND " +
											  //"fgsa_rev_no="+fgsa_rev_no+" AND " +
											  "sn_no="+sn_no+" AND " +
											  "sn_rev_no="+sn_rev_no+"";
								
								////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);
								
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
									mdcq_percentage = Double.parseDouble(rset.getString(2));
									MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
								}
								
								queryString4 = "SELECT NVL(A.dcq,'0') FROM DLNG_SN_DCQ_DTL A " +
								   			   "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" " +
								   			   //"AND A.fgsa_rev_no="+fgsa_rev_no+" " +
								   			   "AND A.sn_no="+sn_no+" " +
								   			   "AND A.sn_rev_no="+sn_rev_no+" " +
								   			   "AND A.from_dt<=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
								   			   "AND A.to_dt>=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
								   			   "ORDER BY A.from_dt DESC";
								////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
									if(Double.parseDouble(rset4.getString(1))>=0)
									{
										DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
										MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
									}
								}
													
								////System.out.println("MDCQ_Qty = "+MDCQ_Qty);
							}
							else if(contract_type.equalsIgnoreCase("L"))
							{
								queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_LOA_MST " +
											  "WHERE customer_cd="+customer_cd+" AND tender_no="+fgsa_no+" AND " +
											  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+"";
								
								////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_LOA_MST Table = "+queryString);
								
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
									mdcq_percentage = Double.parseDouble(rset.getString(2));
									MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
								}
								
								queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
								   			   "WHERE A.customer_cd="+customer_cd+" AND A.tender_no="+fgsa_no+" " +
								   			   "AND A.loa_no="+sn_no+" " +
								   			   "AND A.loa_rev_no="+sn_rev_no+" " +
								   			   "AND A.from_dt<=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
								   			   "AND A.to_dt>=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
								   			   "ORDER BY A.from_dt DESC";
								////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									if(Double.parseDouble(rset4.getString(1))>0)
									{
										DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
										MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
									}
								}
							}
							else if(contract_type.equalsIgnoreCase("R"))
							{}
							else if(contract_type.equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
							{}
							else if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
							{}
							
						//	//System.out.println("JAVA after invoice_Period_Dates.size(): "+invoice_Period_Dates.size());
							////System.out.println("JAVA period_dt: "+DCQ_Qty);
							//invoice_Period_Dates.add(period_dt);
							invoice_Period_DCQ.add(nf.format(DCQ_Qty));
							invoice_Period_Buyer_Nom_Qty.add(nf.format(0));
							invoice_Period_Buyer_Shortfall_Qty.add("");
							invoice_Period_Buyer_Off_Spec_Qty.add("");
							invoice_Period_Buyer_Suspension_Qty.add("");
							invoice_Period_Delv_Failure_Qty.add("");
							invoice_Period_Total_Shortfall_Qty.add("");
							invoice_Period_LD_Credit_Payable.add("");
							invoice_Period_Seller_Nom_PNQ.add(nf.format(0));
							invoice_Period_Gas_Delivered_PNQ.add(nf.format(0));
							invoice_Period_Seller_Nom_RE_Qty.add(nf.format(0));
							invoice_Period_FM_Qty.add("");
							invoice_Period_Gas_Delivered_Re_Qty.add(nf.format(0));
							invoice_Period_Gas_Delivered_Total_Qty.add(nf.format(0));
							invoice_Period_Cumulative_Qty.add(nf.format(Total_Gas_Delv_Qty));
							SN_Qty_Before_Inv_Period_Start += SN_Qty_On_Inv_Period_Dates;
							invoice_Period_Cumulative_SN_Qty.add(nf.format(SN_Qty_Before_Inv_Period_Start));
						}
					}
				}
			}
			if(flag1.equalsIgnoreCase("Y")){     //RG20161214 for debit credit note
			if(!drcrcriteria.equalsIgnoreCase("")){
			String con_typ1="";
			queryString="SELECT A.SN_NO,A.FGSA_NO,A.CUSTOMER_CD,A.PLANT_SEQ_NO,A.CONTRACT_TYPE,A.HLPL_INV_SEQ_NO," +
			"A.FINANCIAL_YEAR,A.SN_REV_NO,A.FGSA_REV_NO,TO_CHAR(A.INVOICE_DT,'DD/MM/YYYY'),A.TOTAL_QTY," +
			"A.SALE_PRICE,A.GROSS_AMT_INR,A.NET_AMT_INR,A.CRITERIA,TO_CHAR(A.DUE_DT,'DD/MM/YYYY')," +
			"A.EXCHG_RATE_VALUE,A.DR_CR_FLAG,A.DR_CR_NO,TO_CHAR(A.DR_CR_DT,'DD-Mon-YY')," +
			"A.DR_CR_EXG_RATE,A.DIFF_QTY,A.DIFF_AMT,A.DAY_DIFF,A.INT_RATE_TYPE,A.INT_RATE," +
			"A.INT_AMT,A.REMARK,A.DR_CR_DOC_NO,A.DR_CR_GROSS_AMT_INR,A.DR_CR_NET_AMT_INR," +
			"A.DR_CR_GROSS_AMT_USD,A.GROSS_AMT_USD,A.TAX_AMT_INR,A.TAX_STRUCT_CD,A.DR_CR_SALE_RATE,"
			+ " TAX_REMARK,TO_CHAR(A.DUE_DT,'DD-Mon-YY'),DR_CR_TCS_FLAG,DR_CR_QTY,DIFF_TAX,ITEM_DESCRIPTION"
			+ " FROM DLNG_DR_CR_NOTE A " +
			"WHERE A.SN_NO = '"+sn_no+"' AND  A.FGSA_NO = '"+fgsa_no+"' AND " +
			"A.CUSTOMER_CD = '"+customer_cd+"' AND A.PLANT_SEQ_NO = '"+customer_plant_seq_no+"' AND " +
			"A.CONTRACT_TYPE = '"+contract_type+"' AND A.HLPL_INV_SEQ_NO = '"+hlplInvoiceNo+"' AND " +
			"A.FINANCIAL_YEAR = '"+invFinancialYear+"' AND A.CRITERIA='"+drcrcriteria+"' AND (A.DR_CR_FLAG='dr' OR A.DR_CR_FLAG='cr' ) ";
			System.out.println(" query---->"+queryString);
			rset = stmt.executeQuery(queryString);				
			if(rset.next())
			{	
				con_typ1        = rset.getString(5)==null?"":rset.getString(5);
				inv_dt1        = rset.getString(10)==null?"":rset.getString(10);
				qty1 		  = rset.getString(11)==null?"":nf.format(Double.parseDouble(rset.getString(11)));
				sale_price1 	  = rset.getString(12)==null?"":rset.getString(12);		
				gross_amt1 	  = rset.getString(13)==null?"":rset.getString(13);
				net_amt1 	  = rset.getString(14)==null?"":rset.getString(14);			
				criteria1 	  = rset.getString(15)==null?"":rset.getString(15);							
				due_dt1 		  = rset.getString(16)==null?"":rset.getString(16);				
				exg_rt1		  = rset.getString(17)==null?"":nf2.format(Double.parseDouble(rset.getString(17)));;
				cr_dr2 		  = rset.getString(18)==null?"":rset.getString(18);
				dr_cr_no1 	  = rset.getString(19)==null?"":rset.getString(19);
				dr_cr_dt1 	  = rset.getString(20)==null?"":rset.getString(20);
				dr_cr_exg_rt1  = rset.getString(21)==null?"":nf2.format(Double.parseDouble(rset.getString(21)));;						
				remark1  	 = rset.getString(28)==null?"":rset.getString(28);
				dr_cr_doc_no1 = rset.getString(29)==null?"":rset.getString(29);
				dr_cr_sales_rate = rset.getString(36)==null?"":rset.getString(36);
				tax_amt_inr = rset.getString(37)==null?"":rset.getString(37);
				dr_cr_due_dt = rset.getString(38)==null?"":rset.getString(38);
				dr_cr_tcs_flag = rset.getString(39)==null?"":rset.getString(39);
				
				dr_cr_qty = rset.getString(40)==null?"":rset.getString(40); //Hiren_20210212
				diff_qty = rset.getString(22)==null?"":rset.getString(22);//Hiren_20210212
				diff_tax = rset.getString(41)==null?"":rset.getString(41); //Hiren_20210218
				item_desc = rset.getString(42)==null?"":rset.getString(42); //Hiren_20210218
				
				if(!diff_tax.equals("")) {
					diff_tax = nf.format(Double.parseDouble(diff_tax+""));
				}
				
				if(!diff_qty.equals("")) {
					diff_qty = nf.format(Double.parseDouble(diff_qty+""));
				}
				if(!dr_cr_qty.equals("")) {
					dr_cr_qty = nf.format(Double.parseDouble(dr_cr_qty+""));
				}
				
				if(rset.getString(30)!=null && !(rset.getString(30).trim().equals("")))
				{
					dr_cr_gross_amt1 = nf.format(Double.parseDouble(rset.getString(30)));
				}
				if(dr_cr_net_amt1.trim().equals(""))
				{
					dr_cr_net_amt1  = rset.getString(31)==null?"":rset.getString(31);
				}
				if(rset.getString(17)!=null && !(rset.getString(17).trim().equals("")) &&  rset.getString(21)!=null && !(rset.getString(21).trim().equals("")))
				{
					if(cr_dr2.equalsIgnoreCase("dr")){
						diff_exg_rt1 = nf2.format(Double.parseDouble(rset.getString(21)) -  Double.parseDouble(rset.getString(17)));
					}
					else{
						diff_exg_rt1 = nf2.format(Double.parseDouble(rset.getString(17)) -  Double.parseDouble(rset.getString(21)));	
					}
				}
				if(rset.getString(36)!=null && !(rset.getString(36).trim().equals("")) &&  rset.getString(12)!=null && !(rset.getString(12).trim().equals("")))
				{
					if(cr_dr2.equalsIgnoreCase("dr")){
						diff_sale_rt1 = nf2.format(Double.parseDouble(rset.getString(36)) -  Double.parseDouble(rset.getString(12)));
					}else if(cr_dr2.equalsIgnoreCase("cr")){
						diff_sale_rt1 = nf2.format(Double.parseDouble(rset.getString(12))- Double.parseDouble(rset.getString(36)));
					}
					////System.out.println("----sale-->"+diff_sale_rt1);
				}
				if(rset.getString(14)!=null && !(rset.getString(14).trim().equals("")) &&  rset.getString(31)!=null && !(rset.getString(31).trim().equals("")))
				{
					dr_cr_net_amt_inr1 = nf.format(Double.parseDouble(rset.getString(14)) +  Double.parseDouble(rset.getString(31)));
				}
				if(rset.getString(11)!=null && !(rset.getString(11).trim().equals("")) &&  rset.getString(22)!=null && !(rset.getString(22).trim().equals("")))
				{
					if(cr_dr2.trim().equalsIgnoreCase("dr"))
					{
						applicable_qty1 = nf.format(Double.parseDouble(rset.getString(11)) +  Double.parseDouble(rset.getString(22)));
					}
					else if(cr_dr2.trim().equalsIgnoreCase("cr"))
					{
						applicable_qty1 = nf.format(Double.parseDouble(rset.getString(11)) -  Double.parseDouble(rset.getString(22)));
					}
				}
				tax_amt_inr1 = rset.getString(34)==null?"":rset.getString(34);
				dr_cr_gross_usd1 = rset.getString(32)==null?"":rset.getString(32);
				gross_amt_usd1 = rset.getString(33)==null?"":rset.getString(33);
				tax_struc_cd1 = rset.getString(35)==null?"":rset.getString(35);
			}
			double tax_amt1 = 0;
			String tax_cd1 = "0";
			String tax_factor1 = "0.00";
			
			String dr_cr_flag1 ="";
			
			queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
						  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd1+" AND " +
						  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd1+" AND " +
						  "B.app_date<=TO_DATE('"+inv_dt1+"','DD/MM/YYYY')) ORDER BY A.tax_code";
			System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				tax_cd1 = rset.getString(1);
				tax_factor1 = rset.getString(2);
				String tax_title1 = "";
				if(rset.getString(3).equals("1"))
				{
					if(!(dr_cr_gross_amt1.trim().equals("")))
					{
						tax_amt1 = (Double.parseDouble(dr_cr_gross_amt1)*Double.parseDouble(rset.getString(2)))/100;
						if(cr_dr2.trim().equalsIgnoreCase("dr"))
						{
							if(con_typ1.equalsIgnoreCase("C")){
								dr_cr_flag1 = "On Debit";
							} else {
								dr_cr_flag1 = "On Debit";
							}
						}
						tax_title1 = dr_cr_flag1+" Gross Amount";
					}
				}
				else if(rset.getString(3).equals("2"))
				{
					queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd1+" AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd1+" AND " +
								  "B.app_date<=TO_DATE('"+inv_dt1+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
					////System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString1);
			 		rset1=stmt1.executeQuery(queryString1);
			 		if(rset1.next())
			 		{
				 			if(rset1.getString(3).equals("1") )
							{
				 				if(!(dr_cr_gross_amt1.trim().equals("")))
								{
				 					tax_amt1 = (Double.parseDouble(dr_cr_gross_amt1)*Double.parseDouble(rset1.getString(2)))/100;
								}
							}
							
				 			tax_amt1 = (tax_amt1*Double.parseDouble(rset.getString(2)))/100;
			 		}
			 		else
			 		{
			 			tax_amt1 = 0;
			 		}
			 		
			 		queryString2 = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
					  "tax_code="+rset.getString(4)+"";
					////System.out.println("Query For Fetching DEPENDENT Tax Abbr = "+queryString2);
					rset2 = stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						String tax_abbr1 = rset2.getString(1)==null?"":rset2.getString(1);
						tax_title1 = "ON "+tax_abbr1;
					}
					else
					{
						tax_title1 = "";
					}
				}
				else
				{
					tax_amt1 = 0;
				}
				
				customer_Invoice_Tax_Code1.add(tax_cd1);
				customer_Invoice_Tax_Rate1.add(nf.format(Double.parseDouble(tax_factor1)));
				
				TAX_ON_TITLE1.add(tax_title1);
				if(date_flag && contract_type.equals("C")) {
					tot_tax_amt1+=Math.round(tax_amt1);
					customer_Invoice_Tax_Amt1.add(nf3.format(tax_amt1));
				} else {
					tot_tax_amt1+=tax_amt1;
					customer_Invoice_Tax_Amt1.add(nf3.format(tax_amt1));
				}
				////System.out.println("tot_tax_amt1----"+tot_tax_amt1);
			}
			for(int i=0; i<customer_Invoice_Tax_Code1.size(); i++)
			{
				queryString = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
							  "tax_code="+customer_Invoice_Tax_Code1.elementAt(i)+"";
				////System.out.println("Query For Fetching Tax Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					String tax_nm1 = rset.getString(2).trim()==null?"":rset.getString(2).trim();
					TAX_NAME1.add(tax_nm1);
				}
				else
				{
					TAX_NAME1.add("");
				}					
			}
			if(criteria1.contains("DIFF-QTY")){
				//if(cr_dr2.equalsIgnoreCase("dr"))
				{
				double Offspec_Qty2 = 0;
				double Offspec_Qty3 = 0;
				double FM_Qty1 = 0;
				String Offspec_Flag1 = "N";
				
				
				queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
							  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL " +
							  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
							  "sn_no="+sn_no+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
							  "gas_dt>=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY') AND " +
							  "gas_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') AND " +
							  "contract_type='"+contract_type+"'";
				
				if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
						queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
				}
				//System.out.println("Query For Fetching Offspec & FM Quantities From DLNG_DAILY_ALLOCATION_DTL Table = "+queryString);
				
//				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					Offspec_Flag1 = rset.getString(3);
					if(Offspec_Flag1.trim().equalsIgnoreCase("N"))
					{
						Offspec_Qty2 += Double.parseDouble(rset.getString(1));
					}
					else
					{
						Offspec_Qty2 += Double.parseDouble(rset.getString(1));
						Offspec_Qty3 += Double.parseDouble(rset.getString(1));
					}
					FM_Qty1 += Double.parseDouble(rset.getString(2));
				}

				if(Offspec_Qty3>=0.01)
				{
					accepted_Offspec_Qty1 = nf.format(Offspec_Qty3);
				}
				else
				{
					accepted_Offspec_Qty1 = "";
				}
				
				if(FM_Qty1>=0.01)
				{
					accepted_FM_Qty1 = nf.format(FM_Qty1);
				}
				else
				{
					accepted_FM_Qty1 = "";
				}
				
				
				queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
						  " WHERE alloc_ID LIKE '"+customer_inv_mapping_id+"-%"+customer_plant_seq_no+"' " 
						 + " AND gas_dt<TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY')";
				
				if(contract_type.equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
				{
						queryString+=" AND MAPPING_ID='"+customer_inv_mapping_id+"'";
				}
				
				System.out.println("Query For Fetching Total QTY For Invoicing From DLNG_DAILY_ALLOCATION_DTL Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					total_Invoice_Qty1 = nf.format(Double.parseDouble(rset.getString(1))-Offspec_Qty2);
				}
				
				//if(cr_dr2.equalsIgnoreCase("dr")){
					diff_qty1= nf.format(Double.parseDouble(total_Invoice_Qty1) -  Double.parseDouble(qty1));
				//} 
				if(date_flag) {
					gross_amt1 = nf.format(Math.round(Double.parseDouble(diff_qty1) *  Double.parseDouble(sale_price1)* Double.parseDouble(exg_rt1)));
				} else {
					gross_amt1 = nf.format(Double.parseDouble(diff_qty1) *  Double.parseDouble(sale_price1)* Double.parseDouble(exg_rt1));
				}
				
				////System.out.println("diff_qty1------>"+diff_qty1);
				double tax_amt2 = 0;
				String tax_cd2 = "0";
				String tax_factor2 = "0.00";
				
				String dr_cr_flag2 ="";
				
				queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
							  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd1+" AND " +
							  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd1+" AND " +
							  "B.app_date<=TO_DATE('"+inv_dt1+"','DD/MM/YYYY')) ORDER BY A.tax_code";
				System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					tax_cd2 = rset.getString(1);
					tax_factor2 = rset.getString(2);
					String tax_title2 = "";
					if(rset.getString(3).equals("1"))
					{
						if(!(gross_amt1.trim().equals("")))
						{
							tax_amt2 = (Double.parseDouble(gross_amt1)*Double.parseDouble(rset.getString(2)))/100;
							if(cr_dr2.trim().equalsIgnoreCase("dr"))
							{
								if(con_typ1.equalsIgnoreCase("C")){
									dr_cr_flag2 = "On Supplementary";
								} else {
									dr_cr_flag2 = "On Debit";
								}
							} else {
									dr_cr_flag2 = "On Credit";
							}
							tax_title2 = dr_cr_flag2+" Gross Amount";
						}
					}
					else if(rset.getString(3).equals("2"))
					{
						queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd1+" AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd1+" AND " +
									  "B.app_date<=TO_DATE('"+inv_dt1+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
						////System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString1);
				 		rset1=stmt1.executeQuery(queryString1);
				 		if(rset1.next())
				 		{
					 			if(rset1.getString(3).equals("1") )
								{
					 				if(!(gross_amt1.trim().equals("")))
									{
					 					tax_amt2 = (Double.parseDouble(gross_amt1)*Double.parseDouble(rset1.getString(2)))/100;
									}
								}
								
					 			tax_amt2 = (tax_amt2*Double.parseDouble(rset.getString(2)))/100;
				 		}
				 		else
				 		{
				 			tax_amt2 = 0;
				 		}
				 		
				 		queryString2 = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
						  "tax_code="+rset.getString(4)+"";
						////System.out.println("Query For Fetching DEPENDENT Tax Abbr = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							String tax_abbr2 = rset2.getString(1)==null?"":rset2.getString(1);
							tax_title2 = "ON "+tax_abbr2;
						}
						else
						{
							tax_title2 = "";
						}
					}
					else
					{
						tax_amt2 = 0;
					}
					
					customer_Invoice_Tax_Code2.add(tax_cd2);
					customer_Invoice_Tax_Rate2.add(nf.format(Double.parseDouble(tax_factor2)));
					
					TAX_ON_TITLE2.add(tax_title2);
					if(date_flag && contract_type.equals("C")) {
						tot_tax_amt2+=Math.round(tax_amt2);
						customer_Invoice_Tax_Amt2.add(nf3.format(Math.round(tax_amt2)));
					} else {
						tot_tax_amt2+=tax_amt2;
						customer_Invoice_Tax_Amt2.add(nf3.format(tax_amt2));
					}
					
					//System.out.println("tot_tax_amt1----"+customer_Invoice_Tax_Amt2+"---"+customer_Invoice_Tax_Code2+"---"+customer_Invoice_Tax_Rate2);
				}
				for(int i=0; i<customer_Invoice_Tax_Code2.size(); i++)
				{
					queryString = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
								  "tax_code="+customer_Invoice_Tax_Code2.elementAt(i)+"";
					////System.out.println("Query For Fetching Tax Name = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						String tax_nm2 = rset.getString(2).trim()==null?"":rset.getString(2).trim();
						TAX_NAME2.add(tax_nm2);
					}
					else
					{
						TAX_NAME2.add("");
					}					
				}
				
				net_amt_inr1 = nf.format(Double.parseDouble(gross_amt1) +  tot_tax_amt2);
			////System.out.println("-tot----"+TAX_NAME2);
			}
		}
			}			////System.out.println(invoice_Period_DCQ.size()+"--END of Invoice Report-STEP-1--->>>>>"+invoice_Period_DCQ);
			}	////System.out.println(invoice_Period_Dates.size()+"--invoice_Period_Dates--->>>>>"+invoice_Period_Dates);
			
//			Date d_eff = new Date(gst_eff_dt);
//			Date d1 = new Date(bill_period_start_dt);
//			if(d1.after(d_eff) || d1.equals(d_eff)) {
//				date_flag = true;
//			}
			
			if(!(dr_cr_gross_amt1.trim().equals("")))
			{
				dr_cr_gross_amt1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_gross_amt1)));
			}
			if(!(net_amt_inr1.trim().equals("")))
			{
				net_amt_inr1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(net_amt_inr1)));
			}
			if(!(gross_amt1.trim().equals("")))
			{
				gross_amt1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(gross_amt1)));
			}
			if(!(dr_cr_net_amt_inr1.trim().equals("")))
			{
				dr_cr_net_amt_inr1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_net_amt_inr1)));
			}
			if(!(net_amt1.trim().equals("")))
			{
				net_amt1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(net_amt1)));
			}
			if(!(dr_cr_net_amt1.trim().equals("")))
			{
				dr_cr_net_amt1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_net_amt1)));
			}
			if(!(gross_amt_usd1.trim().equals("")))
			{
				gross_amt_usd1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(gross_amt_usd1)));
			}
			if(!(dr_cr_gross_usd1.trim().equals("")))
			{
				dr_cr_gross_usd1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_gross_usd1)));
			}
			if(!(dr_cr_gross_usd1.trim().equals("")))
			{
				dr_cr_gross_usd1 = nf3.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_gross_usd1)));
			}
			if(!(diff_sale_rt1.trim().equals("")))
			{
				diff_sale_rt1 = nf2.format(Double.parseDouble(""+NumberFormat.getInstance().parse(diff_sale_rt1)));
			}
			if(!(sale_price1.trim().equals("")))
			{
				sale_price1 = nf2.format(Double.parseDouble(""+NumberFormat.getInstance().parse(sale_price1)));
			}
			if(!(dr_cr_sales_rate.trim().equals("")))
			{
				dr_cr_sales_rate = nf2.format(Double.parseDouble(""+NumberFormat.getInstance().parse(dr_cr_sales_rate)));
			}
			
			
			
				/////////RS13062017
				queryString = "SELECT A.TAX_STRUCT_DTL,A.TAX_STRUCT_CD FROM FMS7_CUSTOMER_SERVICE_TAX_DTL A WHERE " +
						  "A.customer_cd="+customer_cd+" AND " +
						  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
				 		  "A.tax_struct_dt=(SELECT MAX(B.tax_struct_dt) FROM FMS7_CUSTOMER_SERVICE_TAX_DTL B WHERE " +
				 		  "A.customer_cd=B.customer_cd AND A.plant_seq_no=B.plant_seq_no AND " +
				 		  "B.tax_struct_dt<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'))";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					tax_Structure_Dtl = rset.getString(1)==null?"":rset.getString(1);
//					tax_struct_cd = rset.getString(2)==null?"0":rset.getString(2);
				}
				else
				{
					tax_Structure_Dtl = "";
//					tax_struct_cd = "0";
				}
				//////
				
			
				/* *****Hiren_20201110****fetching TCS details****** */  
				/*String tcs_sql = "select TCS_AMT from FMS7_INVOICE_TCS_DTL where"
						+ " CUSTOMER_CD='"+customer_cd+"' and FINANCIAL_YEAR='"+invFinancialYear+"' "
						+ " and HLPL_INV_SEQ_NO='"+hlplInvoiceNo+"' and CONTRACT_TYPE='"+contract_type+"'"
						+ " and INVOICE_TYPE='SALES' and COMMODITY_TYPE='DLNG' ";
				System.out.println("tcs_sql*****"+tcs_sql);
				rset = stmt.executeQuery(tcs_sql);
				if(rset.next()) {
					tcs_app_flag = "Y";
					tcs_amt = rset.getString(1)==null?"0":rset.getString(1);
				}
				
				queryString="SELECT TAX_CODE,FACTOR FROM FMS7_TAX_STRUCTURE_DTL A WHERE TAX_STR_CD='22' AND APP_DATE=(SELECT MAX(B.APP_DATE) "
						+ "FROM FMS7_TAX_STRUCTURE_DTL B WHERE A.TAX_STR_CD=B.TAX_STR_CD AND B.TAX_STR_CD='22' AND " +
						  "B.APP_DATE<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
						// "B.APP_DATE<=TO_DATE('01/08/2020','DD/MM/YYYY'))";
				rset=stmt.executeQuery(queryString);
//				System.out.println("queryString---"+queryString);
				if(rset.next()){
					
					fact=nf4.format(Double.parseDouble(rset.getString(2)==null?"0":rset.getString(2)));
					queryString1 = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
							  "tax_code="+rset.getString(1)+"";
					System.out.println("Query For Fetching Tax Name = "+queryString);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						tcs_nm=rset1.getString(2)==null?"":rset1.getString(2);
						tcs_sht_nm=rset1.getString(1)==null?"":rset1.getString(1);
						tcs_nm=tcs_nm+" ("+tcs_sht_nm+")";
					}
				}*/
				
				System.out.println("criteria--tcs-"+drcrcriteria);
				if(cr_dr2.equalsIgnoreCase("dr") || cr_dr2.equalsIgnoreCase("cr") && (!drcrcriteria.contains("REV_INV") && !drcrcriteria.equals(""))){
					if(cr_dr2.equalsIgnoreCase("dr")){
						queryString="SELECT HLPL_INV_SEQ_NO,TCS_AMT FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
								+ "AND FINANCIAL_YEAR='"+invFinancialYear+"' and hlpl_inv_seq_no='"+dr_cr_no1+"' and contract_type='"+contract_type+"' "
								+ "AND (INVOICE_TYPE='DEBIT') AND FLAG='Y' and commodity_type='DLNG' ";
						rset=stmt.executeQuery(queryString);
					}else{
						queryString="SELECT HLPL_INV_SEQ_NO,TCS_AMT FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
								+ "AND FINANCIAL_YEAR='"+invFinancialYear+"' and hlpl_inv_seq_no='"+dr_cr_no1+"' and contract_type='"+contract_type+"' "
								+ "AND (INVOICE_TYPE='CREDIT') AND FLAG='Y' and commodity_type='DLNG' ";
						rset=stmt.executeQuery(queryString);
					}
					System.out.println("queryString--for tcs-"+queryString);
				}else{
					queryString="SELECT HLPL_INV_SEQ_NO,nvl(TCS_AMT,0) FROM FMS7_INVOICE_TCS_DTL WHERE CUSTOMER_CD='"+customer_cd+"' "
							+ "AND FINANCIAL_YEAR='"+invFinancialYear+"' and hlpl_inv_seq_no='"+hlplInvoiceNo+"' and contract_type='"+contract_type+"' "
							+ "AND INVOICE_TYPE='SALES' AND FLAG='Y' and commodity_type='DLNG' ";
					rset=stmt.executeQuery(queryString);
					System.out.println("queryString--for tcs-"+queryString);
				}
				System.out.println("queryString--for tcs-"+queryString);
				if(rset.next()){
					tcs_app_flag="Y";
					tcs_amt = nf3.format(Double.parseDouble(rset.getString(2)==null?"0":rset.getString(2)));
					
					if(cr_dr2.equalsIgnoreCase("dr") || cr_dr2.equalsIgnoreCase("cr")){
						queryString1="SELECT tax_struct_cd,to_char(dr_cr_dt,'dd/mm/yyyy'),to_char(invoice_dt,'dd/mm/yyyy') from dlng_dr_cr_note where CUSTOMER_CD='"+customer_cd+"' "
								+ "AND dr_cr_FIN_YEAR='"+invFinancialYear+"' and hlpl_inv_seq_no='"+hlplInvoiceNo+"' and dr_cr_no='"+dr_cr_no1+"' and "
								+ "dr_cr_flag='"+cr_dr2+"' and contract_type='"+contract_type+"'";
							rset1=stmt1.executeQuery(queryString1);
							//System.out.println("queryString1--for tcs-"+queryString1);
					}else{
						queryString1="SELECT tax_struct_cd,to_char(invoice_dt,'dd/mm/yyyy') from dlng_invoice_mst where CUSTOMER_CD='"+customer_cd+"' "
							+ "AND FINANCIAL_YEAR='"+invFinancialYear+"' and hlpl_inv_seq_no='"+hlplInvoiceNo+"' and contract_type='"+contract_type+"'";
						rset1=stmt1.executeQuery(queryString1);
						//System.out.println("queryString1--for tcs-"+queryString1);
					}
					if(rset1.next()){
						String query="SELECT TAX_CODE,FACTOR FROM FMS7_TAX_STRUCTURE_DTL A WHERE TAX_STR_CD='22' AND APP_DATE=(SELECT MAX(B.APP_DATE) "
								+ "FROM FMS7_TAX_STRUCTURE_DTL B WHERE A.TAX_STR_CD=B.TAX_STR_CD AND B.TAX_STR_CD='22' AND " +
								  "B.APP_DATE<=TO_DATE('"+rset1.getString(2)+"','DD/MM/YYYY'))";
								// "B.APP_DATE<=TO_DATE('01/08/2020','DD/MM/YYYY'))";
						rset2=stmt2.executeQuery(query);
						System.out.println("query--for tcs-"+query);
						if(rset2.next()){
							fact=nf4.format(Double.parseDouble(rset2.getString(2)==null?"0":rset2.getString(2)));
							String query1 = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
									  "tax_code="+rset2.getString(1)+"";
							//System.out.println("Query For Fetching Tax Namefor tcs = "+query1);
							rset3 = stmt3.executeQuery(query1);
							if(rset3.next())
							{
								tcs_nm=rset3.getString(2)==null?"":rset3.getString(2);
								tcs_sht_nm=rset3.getString(1)==null?"":rset3.getString(1);
								tcs_nm=tcs_nm+" ("+tcs_sht_nm+")";
							}
						}
					}
				}
			}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchInvoiceReport() ...


	
	
	//Following Function Fetches Invoice Details Record For Selected Invoice ...
	//Following Function Has Been Defined By Samik Shah On 4th June, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 5th June, 2010 ...
	//Following Function Has Been Modified By Samik Shah On 21st April, 2011 ...
	//Following Function Has Been Modified By Samik Shah On 22nd June, 2011 ...
	//Following Function Has Been Modified By Samik Shah On 3rd September, 2011 ...
	public void fetchInvoiceReport_OLD()
	{
		methodName = "fetchInvoiceReport()";
		try 
		{
			String tax_struct_cd = "0";
			String exchg_rate_dt = "";
			String formatted_exchg_rate_dt = "";
			
			if(contract_type.equalsIgnoreCase("S"))
			{
				queryString = "SELECT SN_REF_NO " +
							  "FROM DLNG_SN_MST WHERE " +
				  			  "flsa_no="+fgsa_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_ref_no IS NOT NULL AND " +
				  			  "customer_cd="+customer_cd+" ORDER BY sn_rev_no DESC";
				////System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = " SELECT TO_CHAR(signing_dt,'fmddth') " +
							  " || ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  " || ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  " FROM DLNG_FLSA_MST WHERE " +
							  " flsa_no="+fgsa_no+" AND " +
							  " rev_no="+fgsa_rev_no+" AND " +
							  " customer_cd="+customer_cd+" " +
							  " ORDER BY rev_no DESC";
				////System.out.println("Signing Date Fetch Query For FGSA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM DLNG_SN_MST WHERE " +
				  			  "flsa_no="+fgsa_no+" AND flsa_rev_no="+fgsa_rev_no+" AND " +
				  			  "sn_no="+sn_no+" AND sn_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+customer_cd+"";
				////System.out.println("Signing Date Fetch Query For SN Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT LOA_REF_NO " +
							  "FROM DLNG_LOA_MST WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND LOA_REF_NO IS NOT NULL AND " +
				  			  "customer_cd="+customer_cd+" ORDER BY loa_rev_no DESC";
				////System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sn_ref_no = rset.getString(1)==null?"":rset.getString(1);
				}
	
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM DLNG_TENDER_MST WHERE " +
							  "tender_no="+fgsa_no+" AND " +
							  "customer_cd="+customer_cd+"";
				////System.out.println("Signing Date Fetch Query For TENDER Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
				
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
				  			  "FROM DLNG_LOA_MST WHERE " +
				  			  "tender_no="+fgsa_no+" AND " +
				  			  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+" AND " +
				  			  "customer_cd="+customer_cd+"";
				////System.out.println("Signing Date Fetch Query For LOA Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}
			else if(contract_type.equalsIgnoreCase("R"))
			{
				queryString = "SELECT TO_CHAR(signing_dt,'fmddth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'fmMonth') " +
							  "|| ' ' || TO_CHAR(signing_dt,'yyyy') " +
							  "FROM FMS7_RE_GAS_MST WHERE " +
							  "re_gas_no="+fgsa_no+" AND " +
							  "customer_cd="+customer_cd+" " +
							  "ORDER BY rev_no DESC";
				////System.out.println("Signing Date Fetch Query For RE-GAS Master = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_FGSA_Dt = rset.getString(1)==null?"":rset.getString(1);
					customer_Invoice_SN_Dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}

			queryString = "SELECT TO_CHAR(invoice_dt,'DD-Mon-YY'),TO_CHAR(due_dt,'DD-Mon-YY')," +
						  "contact_person_cd,tax_flag,sale_price,total_qty,gross_amt_usd,gross_amt_inr," +
						  "net_amt_inr,exchg_rate_value,tax_struct_cd,exchg_rate_cd," +
						  "TO_CHAR(exchg_rate_dt,'DD/MM/YYYY'),remark_1,remark_2," +
						  "offspec_flag,offspec_qty,offspec_rate,remark_3,tax_amt_inr " +
						  "FROM DLNG_INVOICE_MST WHERE " +
						  "financial_year='"+invFinancialYear+"' AND " +
						  "hlpl_inv_seq_no="+hlplInvoiceNo+" AND " +
						  "contract_type='"+contract_type+"' AND FLAG!='A'";
			////System.out.println("Invoice & Due Dates Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				double total_inv_qty = 0;
				double gross_amt = 0;
				double total_offspec_qty = 0;
				
				customer_Invoice_DT = rset.getString(1)==null?"":rset.getString(1);
				customer_Invoice_Due_DT = rset.getString(2)==null?"":rset.getString(2);
				contactPersonCd = rset.getString(3)==null?"0":rset.getString(3);
				customer_Invoice_Tax_Flag = rset.getString(4)==null?"V":rset.getString(4);
				invoice_Sales_Rate = rset.getString(5)==null?"0.0000":nf2.format(Double.parseDouble(rset.getString(5)));
				total_Invoice_Qty = rset.getString(6)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(6)));
				
				total_inv_qty = Double.parseDouble(rset.getString(6)==null?"0.00":rset.getString(6));
				gross_amt = Double.parseDouble(rset.getString(7)==null?"0.00":rset.getString(7));
				
				customer_Invoice_Gross_Amt_USD = rset.getString(7)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(7)));
				customer_Invoice_Gross_Amt_INR = rset.getString(8)==null?"0.00":nf.format(Double.parseDouble(rset.getString(8)));
				customer_Invoice_Net_Amt_INR = rset.getString(9)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(9)));
				customer_Invoice_Exchg_Rate = rset.getString(10)==null?"0.00":nf2.format(Double.parseDouble(rset.getString(10)));
				tax_struct_cd = rset.getString(11)==null?"0":rset.getString(11);
				customer_Invoice_Exchg_Rate_Cd = rset.getString(12)==null?"0":rset.getString(12);
				exchg_rate_dt = rset.getString(13)==null?"":rset.getString(13);
				remark_1 = rset.getString(14)==null?"":rset.getString(14);
				remark_2 = rset.getString(15)==null?"":rset.getString(15);
				
				offspec_Flag = rset.getString(16)==null?"N":rset.getString(16);
				total_Offspec_Qty = rset.getString(17)==null?"":nf3.format(Double.parseDouble(rset.getString(17)));
				offspec_Sales_Rate = rset.getString(18)==null?"":rset.getString(18);
				remark_3 = rset.getString(19)==null?"":rset.getString(19).trim();
				customer_Invoice_Tax_Amt_INR = rset.getString(20)==null?"0.00":nf3.format(Double.parseDouble(rset.getString(20).trim()));
				
				total_offspec_qty = Double.parseDouble(rset.getString(17)==null?"0.00":rset.getString(17));
				
				if(offspec_Flag.trim().equalsIgnoreCase("Y") && !total_Offspec_Qty.trim().equals("") && !offspec_Sales_Rate.trim().equals(""))
				{
					offspec_Amt_USD = nf3.format(total_offspec_qty*Double.parseDouble(offspec_Sales_Rate));
					total_Gas_Delivered = total_Invoice_Qty;
					total_Invoice_Qty = nf3.format(total_inv_qty+total_offspec_qty);
					gas_Delivered_Amt_USD = nf3.format(gross_amt-(total_offspec_qty*Double.parseDouble(offspec_Sales_Rate)));
					//gas_Delivered_Amt_USD = customer_Invoice_Gross_Amt_USD;
					//customer_Invoice_Gross_Amt_USD = nf3.format(gross_amt+(Double.parseDouble(total_Offspec_Qty)*Double.parseDouble(offspec_Sales_Rate)));
				}
				else
				{
					total_Gas_Delivered = total_Invoice_Qty;
					gas_Delivered_Amt_USD = customer_Invoice_Gross_Amt_USD;
				}
			}

			if(!exchg_rate_dt.equals(""))
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'fmddth') " +
							  "|| ' ' || TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'fmMonth') " +
							  "|| ' ' || TO_CHAR(TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY'),'yyyy') " +
							  "FROM DUAL";
				////System.out.println("Formatted Exchange Rate Date Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					formatted_exchg_rate_dt = rset.getString(1)==null?"":rset.getString(1);
				}
			}

			double tax_amt = 0;
			String tax_cd = "0";
			String tax_factor = "0.00";
			
			queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
						  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
						  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
						  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
			////System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				tax_cd = rset.getString(1);
				tax_factor = rset.getString(2);
				
				if(rset.getString(3).equals("1"))
				{
					tax_amt = (Double.parseDouble(customer_Invoice_Gross_Amt_INR)*Double.parseDouble(rset.getString(2)))/100;
				}
				else if(rset.getString(3).equals("2"))
				{
					queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
								  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
								  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
								  "B.app_date<=TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
					////System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString1);
			 		rset1=stmt1.executeQuery(queryString1);
			 		if(rset1.next())
			 		{
				 			if(rset1.getString(3).equals("1"))
							{
								tax_amt = (Double.parseDouble(customer_Invoice_Gross_Amt_INR)*Double.parseDouble(rset1.getString(2)))/100;
							}
							
				 			tax_amt = (tax_amt*Double.parseDouble(rset.getString(2)))/100;
			 		}
			 		else
			 		{
			 			tax_amt = 0;
			 		}
				}
				else
				{
					tax_amt = 0;
				}
				
				customer_Invoice_Tax_Code.add(tax_cd);
				customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
				customer_Invoice_Tax_Amt.add(nf3.format(tax_amt));
			}
						
			customer_Invoice_Gross_Amt_INR = nf3.format(Double.parseDouble(customer_Invoice_Gross_Amt_INR));
			
			for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
			{
				queryString = "SELECT sht_nm,tax_name FROM FMS7_TAX_MST WHERE " +
							  "tax_code="+customer_Invoice_Tax_Code.elementAt(i)+"";
				////System.out.println("Query For Fetching Tax Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					String tax_abbr = rset.getString(1).trim()==null?"":rset.getString(1).trim();
					String tax_nm = rset.getString(2).trim()==null?"":rset.getString(2).trim();
					customer_Invoice_Tax_Abbr.add(tax_abbr);
					customer_Invoice_Tax_Name.add(tax_nm);
				}
				else
				{
					customer_Invoice_Tax_Abbr.add("");
					customer_Invoice_Tax_Name.add("");
				}
			}
			
			queryString = "SELECT NVL(A.seq_no,'0'),NVL(contact_person,' '),NVL(contact_desig,' '),addr_flag " +
						  "FROM FMS7_CUSTOMER_CONTACT_MST A " +
						  "WHERE A.customer_cd="+customer_cd+" AND A.def_inv_flag='Y' AND A.seq_no="+contactPersonCd+" AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND B.seq_no="+contactPersonCd+" AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			////System.out.println("Customer Contact Person Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				if(rset.getString(3).equals(" "))
				{
					contact_Person_Name_And_Designation = rset.getString(2).trim();
				}
				else
				{
					contact_Person_Name_And_Designation = rset.getString(2).trim()+" ("+rset.getString(3).trim()+")";
				}
				contact_addr_flag = rset.getString(4)==null?"":rset.getString(4);
			}
			
						
			if(contact_addr_flag.trim().equalsIgnoreCase("R") || contact_addr_flag.trim().equalsIgnoreCase("C") || contact_addr_flag.trim().equalsIgnoreCase("B"))
			{
				queryString = "SELECT addr,city,pin,state " +
							  "FROM FMS7_CUSTOMER_ADDRESS_MST A " +
							  "WHERE A.customer_cd="+customer_cd+" AND A.address_type='"+contact_addr_flag+"' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
							  "WHERE A.customer_cd=B.customer_cd AND B.address_type='"+contact_addr_flag+"' AND " +
							  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
				System.out.println("Customer Address Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
					contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
					contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
					contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
				}
			}
			else
			{
				String new_plant_no = contact_addr_flag.trim().substring(1);
				
				if(new_plant_no.length()>=1)
				{
					queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+new_plant_no+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+new_plant_no+"' AND " +
								  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
				}
				else
				{
					queryString = "SELECT plant_addr,plant_city,plant_pin,PLANT_STATE " +
								  "FROM FMS7_CUSTOMER_PLANT_DTL A " +
								  "WHERE A.customer_cd="+customer_cd+" AND A.seq_no='"+customer_plant_seq_no+"' AND " +
								  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								  "WHERE A.customer_cd=B.customer_cd AND B.seq_no='"+customer_plant_seq_no+"' AND " +
								  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
				}
				
				////System.out.println("Customer Address Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					contact_Customer_Person_Address = rset.getString(1)==null?"":rset.getString(1);
					contact_Customer_Person_City = rset.getString(2)==null?"":rset.getString(2);
					contact_Customer_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
					contact_Customer_Person_State = rset.getString(4)==null?"":rset.getString(4);
				}
			}
			queryString = "SELECT customer_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
			  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY') " +
			  "FROM FMS7_CUSTOMER_MST A " +
			  "WHERE A.customer_cd="+customer_cd+" AND " +
			  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
			  "WHERE A.customer_cd=B.customer_cd AND " +
			  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			////System.out.println("Customer Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Customer_Name = rset.getString(1)==null?"":rset.getString(1);
				
			}
			//JHP20120131
			/*queryString = "SELECT customer_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
						  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY') " +
			  			  "FROM FMS7_CUSTOMER_MST A " +
						  "WHERE A.customer_cd="+customer_cd+" AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_MST B " +
						  "WHERE A.customer_cd=B.customer_cd AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			//System.out.println("Customer Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Customer_Name = rset.getString(1)==null?"":rset.getString(1);
				contact_Customer_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Customer_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Customer_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Customer_CST_DT = rset.getString(5)==null?"":rset.getString(5);
				contact_Customer_Service_Tax_NO = rset.getString(6)==null?"":rset.getString(6);
				contact_Customer_Service_Tax_DT = rset.getString(7)==null?"":rset.getString(7);
			}
			//JHP
			queryString = "SELECT A.customer_cd,A.gst_tin_no,A.cst_tin_no," +
						  "TO_CHAR(A.gst_tin_dt,'DD-MM-YYYY'),TO_CHAR(A.cst_tin_dt,'DD-MM-YYYY')," +
						  "A.service_tax_no,TO_CHAR(A.service_tax_dt,'DD-MM-YYYY')," +
						  "A.gvat_tin_no,TO_CHAR(A.gvat_tin_dt,'DD-MM-YYYY') " +
						  "FROM FMS7_CUSTOMER_PLANT_TAX_NOS A " +
						  "WHERE A.customer_cd="+customer_cd+" AND A.plant_seq_no="+customer_plant_seq_no+"";
			//System.out.println("Customer Plant's GST & CST Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Customer_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Customer_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Customer_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Customer_CST_DT = rset.getString(5)==null?"":rset.getString(5);
				contact_Customer_Service_Tax_NO = rset.getString(6)==null?"":rset.getString(6);
				contact_Customer_Service_Tax_DT = rset.getString(7)==null?"":rset.getString(7);
				contact_Customer_GVAT_NO = rset.getString(8)==null?"":rset.getString(8);
				contact_Customer_GVAT_DT = rset.getString(9)==null?"":rset.getString(9);
			}
			*/
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{
				queryString = "SELECT A.stat_no, TO_CHAR(A.eff_dt,'DD-MM-YYYY'), B.stat_nm, B.stat_cd " +
							  "FROM FMS7_CUSTOMER_PLANT_TAX_CDS A, FMS7_GOVT_STAT_NO B " +
							  "WHERE A.stat_cd=B.stat_cd AND A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND B.stat_type='S' " +
							  "ORDER BY A.stat_cd";
				
				////System.out.println("Customer Plant's Tax Names Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String stat_nm = rset.getString(3)==null?"":rset.getString(3);
					String stat_no = rset.getString(1)==null?"":rset.getString(1);
					String stat_eff_dt = rset.getString(2)==null?"":rset.getString(2);
					
					if(!stat_nm.trim().equals("") && !stat_no.trim().equals("") && !stat_eff_dt.trim().equals(""))
					{
						vSTAT_NO.add(stat_no.trim());
						vSTAT_EFF_DT.add(stat_eff_dt.trim());
						vSTAT_NM.add(stat_nm.trim());
						vSTAT_CD.add(rset.getString(4)==null?"0":rset.getString(4));
					}
				}
			}
			else if(contract_type.equalsIgnoreCase("R"))
			{
				queryString = "SELECT A.stat_no, TO_CHAR(A.eff_dt,'DD-MM-YYYY'), B.stat_nm, B.stat_cd " +
							  "FROM FMS7_CUSTOMER_PLANT_TAX_CDS A, FMS7_GOVT_STAT_NO B " +
							  "WHERE A.stat_cd=B.stat_cd AND A.customer_cd="+customer_cd+" AND " +
							  "A.plant_seq_no="+customer_plant_seq_no+" AND B.stat_type='R' " +
							  "ORDER BY A.stat_cd";
				
				////System.out.println("Customer Plant's Tax Names Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String stat_nm = rset.getString(3)==null?"":rset.getString(3);
					String stat_no = rset.getString(1)==null?"":rset.getString(1);
					String stat_eff_dt = rset.getString(2)==null?"":rset.getString(2);
					
					if(!stat_nm.trim().equals("") && !stat_no.trim().equals("") && !stat_eff_dt.trim().equals(""))
					{
						vSTAT_NO.add(stat_no.trim());
						vSTAT_EFF_DT.add(stat_eff_dt.trim());
						vSTAT_NM.add(stat_nm.trim());
						vSTAT_CD.add(rset.getString(4)==null?"0":rset.getString(4));
					}
				}
			}			
			
			queryString = "SELECT addr,city,pin " +
						  "FROM FMS7_SUPPLIER_ADDRESS_MST A " +
						  "WHERE A.supplier_cd=1 AND A.address_type='R' AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			////System.out.println("Supplier Address Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Suppl_Person_Address = rset.getString(1)==null?"":rset.getString(1);
				contact_Suppl_Person_City = rset.getString(2)==null?"":rset.getString(2);
				contact_Suppl_Person_Pin = rset.getString(3)==null?"":rset.getString(3);
			}
			
			queryString = "SELECT supplier_name,gst_tin_no,cst_tin_no,TO_CHAR(gst_tin_dt,'DD-MM-YYYY')," +
						  "TO_CHAR(cst_tin_dt,'DD-MM-YYYY'),addl_no,TO_CHAR(addl_issue_dt,'DD-MM-YYYY') " +
						  "FROM FMS7_SUPPLIER_MST A " +
						  "WHERE A.supplier_cd=1 AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND " +
						  "B.eff_dt<=TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'))";
			////System.out.println("Supplier Details Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				contact_Suppl_Name = rset.getString(1)==null?"":rset.getString(1);
				contact_Suppl_GST_NO = rset.getString(2)==null?"":rset.getString(2);
				contact_Suppl_CST_NO = rset.getString(3)==null?"":rset.getString(3);
				contact_Suppl_GST_DT = rset.getString(4)==null?"":rset.getString(4);
				contact_Suppl_CST_DT = rset.getString(5)==null?"":rset.getString(5);
				contact_Suppl_Service_Tax_NO = rset.getString(6)==null?"":rset.getString(6);
				contact_Suppl_Service_Tax_DT = rset.getString(7)==null?"":rset.getString(7);
			}
			
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY'),'DD-Mon-YY'),TO_CHAR(TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY'),'DD-Mon-YY') FROM DUAL";
			////System.out.println("Bill Period Start & End Dates Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				customer_Invoice_Start_DT = rset.getString(1)==null?"":rset.getString(1);
				customer_Invoice_End_DT = rset.getString(2)==null?"":rset.getString(2);
			}
			
			if(!customer_Invoice_Exchg_Rate_Cd.equals(sbi_avg_tt_selling_buying_cd))
			{
				queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+customer_Invoice_Exchg_Rate_Cd+" " +
							  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
				////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_Exchg_Rate_Code.add(customer_Invoice_Exchg_Rate_Cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					customer_Invoice_Exchg_Rate_Code.add(customer_Invoice_Exchg_Rate_Cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add("0.00");
				}
			}
			else
			{
				queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_buying_cd+" " +
							  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
				////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_Exchg_Rate_Code.add(sbi_tt_buying_cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					customer_Invoice_Exchg_Rate_Code.add(sbi_tt_buying_cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add("0.00");
				}
				
				queryString = "SELECT NVL(A.exchg_val,'0.00') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+sbi_tt_selling_cd+" " +
							  "AND A.eff_dt=TO_DATE('"+exchg_rate_dt+"','DD/MM/YYYY')";
				////System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_Exchg_Rate_Code.add(sbi_tt_selling_cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add(nf2.format(Double.parseDouble(rset.getString(1))));
				}
				else
				{
					customer_Invoice_Exchg_Rate_Code.add(sbi_tt_selling_cd);
					customer_Invoice_Exchg_Rate_Date.add(formatted_exchg_rate_dt);
					customer_Invoice_Exchg_Rate_Value.add("0.00");
				}
			}
			
			for(int i=0; i<customer_Invoice_Exchg_Rate_Code.size(); i++)
			{
				queryString = "SELECT A.exc_rate_nm FROM FMS7_CONT_EXCHG_RATE_MST A WHERE " +
							  "A.exc_rate_cd="+customer_Invoice_Exchg_Rate_Code.elementAt(i)+"";
				////System.out.println("Query For Fetching Exchange Rate Name For Invoicing From FMS7_CONT_EXCHG_RATE_MST Table = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					customer_Invoice_Exchg_Rate_Name.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else
				{
					customer_Invoice_Exchg_Rate_Name.add("");
				}
			}
			
			//Following Code Is Fetching Exchange Rates For Specified Duration (e.g. Whole Week, First Fortnight, Second Fortnight, Or For Whole Month, etc.) ...
			//Following Logic Has Been Introduced By Samik Shah On 8th June, 2010 ...
			if(bill_period_start_dt!=null && !bill_period_start_dt.equals("") && !bill_period_start_dt.equals(" ") && bill_period_end_dt!=null && !bill_period_end_dt.equals("") && !bill_period_end_dt.equals(" "))
			{
				int date_part = 0;
				String date_portion = "";
				
				queryString3 = "SELECT TO_DATE('"+bill_period_end_dt+"','DD/MM/YYYY') - TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY') + 1 FROM DUAL";
				////System.out.println("Query To FindOut Dates For Billing Duration = "+queryString3);
				rset3 = stmt3.executeQuery(queryString3);
				if(rset3.next())
				{
					if(Integer.parseInt(rset3.getString(1))>0)
					{
						for(int i=0; i<Integer.parseInt(rset3.getString(1)); i++)
						{
							date_part = Integer.parseInt(bill_period_start_dt.substring(0,2));
							date_part += i;  
							if(date_part>9)
							{
								date_portion = date_part+bill_period_start_dt.substring(2);
							}
							else if(date_part<=9 && date_part>0)
							{
								date_portion = "0"+date_part+bill_period_start_dt.substring(2);
							}
							else
							{
								date_portion = bill_period_end_dt;
							}
							
							invoice_Period_Dates.add(date_portion);
						}
					}					
				}
				
				double SN_Qty_Before_Inv_Period_Start = 0;
				double SN_Offspec_Qty_Before_Inv_Period_Start = 0;
								
				queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
						  " WHERE MAPPING_ID = '"+customer_inv_mapping_id+"' "
						 + " AND gas_dt<TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY')";
				
				////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS Before This Invoice Period From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
				
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					SN_Qty_Before_Inv_Period_Start = Double.parseDouble(rset.getString(1));
				}
				
				queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
				  			  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
							  "FROM DLNG_DAILY_ALLOCATION_DTL " +
							  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
							  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
							  "sn_no="+sn_no+" AND " +
							  "contract_type='"+contract_type+"' AND " +
							  "gas_dt<TO_DATE('"+bill_period_start_dt+"','DD/MM/YYYY')";
				
				////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS Before This Invoice Period From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
				
//				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					if(rset.getString(3).trim().equalsIgnoreCase("N"))
					{
						SN_Offspec_Qty_Before_Inv_Period_Start += Double.parseDouble(rset.getString(1));
					}
				}
				
				SN_Qty_Before_Inv_Period_Start -= SN_Offspec_Qty_Before_Inv_Period_Start;
				
				double Total_Gas_Delv_Qty = 0;
				liability_exist_flag = "N";
								
				for(int i=0; i<invoice_Period_Dates.size(); i++)
				{
					double Buyer_Nom_Qty = 0;
					double Seller_Nom_Qty = 0;
					double mdcq_percentage = 100;
					double DCQ_Qty = 0;
					double MDCQ_Qty = 0;
					double PNQ_Qty = 0;
					double Seller_RE_Qty = 0;
					double Gas_Delv_RE_Qty = 0;
					double Gas_Delv_Qty = 0;
					double FM_Qty = 0;
					double Offspec_Qty = 0;
					double Offspec_Qty_Accepted = 0;
					double Buyer_Suspension_Qty = 0;
					double Failure_To_Delv_Qty = 0;
					double Offspec_Rate = 0; 
					String Offspec_Flag = "N";
					double SN_Qty_On_Inv_Period_Dates = 0;
					double total_buyer_nom_for_gas_day = 0;
					
					queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0') " +
								  "FROM FMS7_DAILY_BUYER_NOM_DTL A " +
					   			  "WHERE A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
					   			  "AND A.nom_rev_no=" +
								  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
								  "WHERE B.sn_no=A.sn_no " +
								  //"AND B.sn_rev_no=A.sn_rev_no " + //Commented By Samik Shah On 27th May, 2011 ...
								  "AND B.fgsa_no=A.fgsa_no " +
								  //"AND B.fgsa_rev_no=A.fgsa_rev_no " + //Commented By Samik Shah On 27th May, 2011 ...
								  "AND B.customer_cd=A.customer_cd AND B.transporter_cd=A.transporter_cd " +
								  "AND B.contract_type=A.contract_type AND B.gas_dt=A.gas_dt " +
								  "AND B.plant_seq_no=A.plant_seq_no)";
					
					////System.out.println("Fetch Query FROM FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						total_buyer_nom_for_gas_day = rset.getDouble(1);
					}
					
					queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
							  " WHERE MAPPING_ID = '"+customer_inv_mapping_id+"' "
							 + " AND gas_dt<TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
					////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						SN_Qty_On_Inv_Period_Dates = Double.parseDouble(rset.getString(1));
					}
					
					queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
		  			  			  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  "sn_no="+sn_no+" AND " +
								  "contract_type='"+contract_type+"' AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY')";
					
					////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
					
//					rset = stmt.executeQuery(queryString);
					while(rset.next())
					{
						if(rset.getString(3).trim().equalsIgnoreCase("N"))
						{
							SN_Qty_On_Inv_Period_Dates -= Double.parseDouble(rset.getString(1));
						}
					}
		
					if(contract_type.equalsIgnoreCase("S"))
					{
						queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_SN_MST " +
									  "WHERE customer_cd="+customer_cd+" AND " +
									  "flsa_no="+fgsa_no+" AND " +
									  //"fgsa_rev_no="+fgsa_rev_no+" AND " +
									  "sn_no="+sn_no+" AND " +
									  "sn_rev_no="+sn_rev_no+"";
						
						////System.out.println("FMS7-SN-INV:DCQ:QRY-I0001:DLNG_SN_MST:SELECT: "+queryString);
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
							mdcq_percentage = Double.parseDouble(rset.getString(2));
							MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
						}
						
						queryString4 = "SELECT NVL(A.dcq,'0') FROM DLNG_SN_DCQ_DTL A " +
						   			   "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" " +
						   			   //"AND A.fgsa_rev_no="+fgsa_rev_no+" " +
						   			   "AND A.sn_no="+sn_no+" " +
						   			   "AND A.sn_rev_no="+sn_rev_no+" " +
						   			   "AND A.from_dt<=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
						   			   "AND A.to_dt>=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
						   			   "ORDER BY A.from_dt DESC";
						////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
						////System.out.println("FMS7-SN-INV:DCQ:QRY-I0002:DLNG_SN_DCQ_DTL:SELECT: "+queryString4);
						rset4 = stmt4.executeQuery(queryString4);
						if(rset4.next())
						{
							//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
							if(Double.parseDouble(rset4.getString(1))>=0)
							{
								DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
								MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
							}
						}
											
						////System.out.println("MDCQ_Qty = "+MDCQ_Qty);
					}
					else if(contract_type.equalsIgnoreCase("L"))
					{
						queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_LOA_MST " +
									  "WHERE customer_cd="+customer_cd+" AND tender_no="+fgsa_no+" AND " +
									  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+"";
						
					//	//System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_LOA_MST Table = "+queryString);
						//System.out.println("FMS7-LOA-INV:DCQ:QRY-I0003:DLNG_LOA_MST:SELECT: "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
							mdcq_percentage = Double.parseDouble(rset.getString(2));
							MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
						}
						
						queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
						   			   "WHERE A.customer_cd="+customer_cd+" AND A.tender_no="+fgsa_no+" " +
						   			   "AND A.loa_no="+sn_no+" " +
						   			   "AND A.loa_rev_no="+sn_rev_no+" " +
						   			   "AND A.from_dt<=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
						   			   "AND A.to_dt>=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') " +
						   			   "ORDER BY A.from_dt DESC";
						////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
						////System.out.println("FMS7-LOA-INV:DCQ:QRY-I0004:FMS7_LOA_DCQ_DTL:SELECT: "+queryString4);
						rset4 = stmt4.executeQuery(queryString4);
						if(rset4.next())
						{
							//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
							if(Double.parseDouble(rset4.getString(1))>=0)
							{
								DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
								MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
							}
						}
					}
					else if(contract_type.equalsIgnoreCase("R"))
					{
						queryString = "SELECT NVL(mdcq_percentage,'100.00') FROM FMS7_RE_GAS_MST " +
									  "WHERE customer_cd="+customer_cd+" AND re_gas_no="+fgsa_no+" AND " +
									  "rev_no="+fgsa_rev_no+"";
						////System.out.println("FMS7-REG-INV:DCQ:QRY-I0005:FMS7_RE_GAS_MST:SELECT: "+queryString);
						////System.out.println("Query For Fetching MDCQ Percentage For the Invoice Period From FMS7_RE_GAS_MST Table = "+queryString);
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							mdcq_percentage = Double.parseDouble(rset.getString(1));
						}
						
						queryString = "SELECT NVL(dcq_qty,'0.00') FROM FMS7_RE_GAS_CARGO_DTL " +
									  "WHERE customer_cd="+customer_cd+" AND re_gas_no="+fgsa_no+" AND " +
									  "cargo_seq_no="+sn_no+"";
						////System.out.println("FMS7-REG-INV:DCQ:QRY-I0006:FMS7_RE_GAS_CARGO_DTL:SELECT: "+queryString);
						////System.out.println("Query For Fetching DCQ For the Invoice Period From FMS7_RE_GAS_CARGO_DTL Table = "+queryString);
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
						}
						MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;					
					}
					
					invoice_Period_DCQ.add(nf5.format(DCQ_Qty));
					
					Buyer_Nom_Qty = 0;
					
					queryString1 = "SELECT DISTINCT(transporter_cd) FROM FMS7_DAILY_BUYER_NOM_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  "sn_no="+sn_no+" AND " +
								  "contract_type='"+contract_type+"' AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "plant_seq_no="+customer_plant_seq_no+"";
					////System.out.println("FMS7-BUYER-INV:BUY-NOM-QTY:QRY-I0007:FMS7_DAILY_BUYER_NOM_DTL:SELECT: "+queryString1);
					////System.out.println("Query For Fetching Buyer Nom Dtl - Transporter From FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_BUYER_NOM_DTL A " +
								  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
								  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  	  "A.sn_no="+sn_no+" AND " +
								  	  "A.contract_type='"+contract_type+"' AND " +
								  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
								  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
								  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_BUYER_NOM_DTL B " +
								  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
								  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  	  "B.sn_no="+sn_no+" AND " +
								  	  "B.contract_type='"+contract_type+"' AND " +
								  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
								  	  "B.transporter_cd="+rset1.getString(1)+")";
						////System.out.println("FMS7-BUYER-INV:BUY-NOM-QTY:QRY-I0007A:FMS7_DAILY_BUYER_NOM_DTL:SELECT: "+queryString);
						////System.out.println("Query For Fetching Buyer Nom QTY From FMS7_DAILY_BUYER_NOM_DTL Table = "+queryString);
					
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							Buyer_Nom_Qty += Double.parseDouble(rset.getString(1));
						}
					}
					
					invoice_Period_Buyer_Nom_Qty.add(nf.format(Buyer_Nom_Qty));
					
					Seller_Nom_Qty = 0;
					Seller_RE_Qty = 0;
										
					queryString1 = "SELECT DISTINCT(transporter_cd) FROM FMS7_DAILY_SELLER_NOM_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  //"fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  "sn_no="+sn_no+" AND " +
								  "contract_type='"+contract_type+"' AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "plant_seq_no="+customer_plant_seq_no+"";
					////System.out.println("FMS7-SELLER-INV:SEL-NOM-QTY:QRY-I0008:FMS7_DAILY_SELLER_NOM_DTL:SELECT: "+queryString1);
				//	//System.out.println("Query For Fetching Seller Nom Dtl - Transporter From FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						queryString = "SELECT NVL(SUM(A.qty_mmbtu),'0.00') FROM FMS7_DAILY_SELLER_NOM_DTL A " +
								  	  "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" AND " +
								  	  //"A.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  	  "A.sn_no="+sn_no+" AND " +
								  	  "A.contract_type='"+contract_type+"' AND " +
								  	  "A.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  	  "A.plant_seq_no="+customer_plant_seq_no+" AND " +
								  	  "A.transporter_cd="+rset1.getString(1)+" AND A.nom_rev_no=" +
								  	  "(SELECT NVL(MAX(B.nom_rev_no),'0') FROM FMS7_DAILY_SELLER_NOM_DTL B " +
								  	  "WHERE B.customer_cd="+customer_cd+" AND B.fgsa_no="+fgsa_no+" AND " +
								  	  //"B.fgsa_rev_no="+fgsa_rev_no+" AND " + //Commented By Samik Shah On 26th August, 2010 ...
								  	  "B.sn_no="+sn_no+" AND " +
								  	  "B.contract_type='"+contract_type+"' AND " +
								  	  "B.gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  	  "B.plant_seq_no="+customer_plant_seq_no+" AND " +
								  	  "B.transporter_cd="+rset1.getString(1)+")";
						////System.out.println("FMS7-SELLER-INV:SEL-NOM-QTY:QRY-I0008A:FMS7_DAILY_SELLER_NOM_DTL:SELECT: "+queryString);
						////System.out.println("Query For Fetching Seller Nom QTY From FMS7_DAILY_SELLER_NOM_DTL Table = "+queryString);
					
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							Seller_Nom_Qty += Double.parseDouble(rset.getString(1));
						}
					}
					
					MDCQ_Qty = Double.parseDouble(nf5.format(MDCQ_Qty));
					PNQ_Qty = 0;
					double re_qty = 28952; //Introduced By Samik Shah On 7th May, 2011 ...
					
					if(contract_type.trim().equalsIgnoreCase("R"))
					{
						if(total_buyer_nom_for_gas_day<re_qty)
						{
							PNQ_Qty = 0;
							Seller_RE_Qty = Seller_Nom_Qty;
						}
						else
						{
							if(Buyer_Nom_Qty<MDCQ_Qty)
							{
								PNQ_Qty = Buyer_Nom_Qty;
							}
							else
							{
								PNQ_Qty = MDCQ_Qty;
							}
							
							PNQ_Qty = Double.parseDouble(nf.format(PNQ_Qty));
												
							Seller_RE_Qty = Seller_Nom_Qty - PNQ_Qty;
							
							if(Seller_RE_Qty<0)
							{
								Seller_RE_Qty = 0;
							}
						}
					}
					else
					{
						if(total_buyer_nom_for_gas_day<re_qty)
						{
							PNQ_Qty = 0;
							Seller_RE_Qty = Seller_Nom_Qty;
						}
						else
						{
							if(Buyer_Nom_Qty<MDCQ_Qty)
							{
								PNQ_Qty = Buyer_Nom_Qty;
							}
							else
							{
								PNQ_Qty = MDCQ_Qty;
							}
							
							PNQ_Qty = Double.parseDouble(nf.format(PNQ_Qty));
							
							Seller_RE_Qty = Seller_Nom_Qty - PNQ_Qty;
							
							if(Seller_RE_Qty<0)
							{
								Seller_RE_Qty = 0;
							}
						}
					}
										
					invoice_Period_Seller_Nom_PNQ.add(nf.format(PNQ_Qty));
					invoice_Period_Seller_Nom_RE_Qty.add(nf.format(Seller_RE_Qty));
							
					queryString = "SELECT NVL(qty_offspec,'0.00'),NVL(qty_fm,'0.00')," +
								  "NVL(offspec_flag,'N'),NVL(offspec_rate,'0.0000') " +
								  "FROM DLNG_DAILY_ALLOCATION_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND fgsa_no="+fgsa_no+" AND " +
								  "sn_no="+sn_no+" AND plant_seq_no="+customer_plant_seq_no+" AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "contract_type='"+contract_type+"'";
					////System.out.println("FMS7-INV:SEL-ALLOC-QTY:QRY-I0009:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString);
					////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);
					
//					rset = stmt.executeQuery(queryString);
					while(rset.next())
					{
						Offspec_Flag = rset.getString(3);
						if(Offspec_Flag.trim().equalsIgnoreCase("N"))
						{
							Offspec_Qty += Double.parseDouble(rset.getString(1));
						}
						else if(Offspec_Flag.trim().equalsIgnoreCase("Y"))
						{
							Offspec_Qty_Accepted += Double.parseDouble(rset.getString(1));
						}
						FM_Qty += Double.parseDouble(rset.getString(2));
					}					
					
					
					queryString = "SELECT NVL(buy_suspen_qty,'0.00'),NVL(fail_delv_qty,'0.00') " +
								  "FROM FMS7_LIABILITY_DTL " +
								  "WHERE customer_cd="+customer_cd+" AND " +
								  "fgsa_no="+fgsa_no+" AND " +
								  "sn_no="+sn_no+" AND " +
								  "gas_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "contract_type='"+contract_type+"'";
					////System.out.println("FMS7-LIA-INV:LIABILITY-QTY:QRY-I00010:FMS7_LIABILITY_DTL:SELECT: "+queryString);
					////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);
					
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						Buyer_Suspension_Qty = rset.getDouble(1);
						Failure_To_Delv_Qty = rset.getDouble(2);
					}
					
					if(Offspec_Qty>=0.01)
					{
						invoice_Period_Buyer_Off_Spec_Qty.add(nf.format(Offspec_Qty));
						liability_exist_flag = "Y";
					}
					else
					{
						invoice_Period_Buyer_Off_Spec_Qty.add("");
					}
					
					if(Buyer_Suspension_Qty>=0.01)
					{
						invoice_Period_Buyer_Suspension_Qty.add(nf.format(Buyer_Suspension_Qty));
						liability_exist_flag = "Y";
					}
					else
					{
						invoice_Period_Buyer_Suspension_Qty.add("");
					}
					
					if(Failure_To_Delv_Qty>=0.01)
					{
						invoice_Period_Delv_Failure_Qty.add(nf.format(Failure_To_Delv_Qty));
						liability_exist_flag = "Y";
					}
					else
					{
						invoice_Period_Delv_Failure_Qty.add("");
					}
					
					if(FM_Qty>=0.01)
					{
						invoice_Period_FM_Qty.add(nf.format(FM_Qty));
						liability_exist_flag = "Y";
					}
					else
					{
						invoice_Period_FM_Qty.add("");
					}
					
					//Logic To Be Developed For Following Quantities Will Be Done Later ... 
					invoice_Period_Buyer_Shortfall_Qty.add("");
					invoice_Period_Total_Shortfall_Qty.add("");
					invoice_Period_LD_Credit_Payable.add("");					
					
					
					Gas_Delv_RE_Qty = 0;
					Gas_Delv_Qty = 0;
					
					queryString = "SELECT NVL(daily_qty,'0.00') FROM DLNG_INVOICE_DTL WHERE " +
								  "financial_year='"+invFinancialYear+"' AND " +
								  "hlpl_inv_seq_no="+hlplInvoiceNo+" AND " +
								  "allocation_dt=TO_DATE('"+invoice_Period_Dates.elementAt(i)+"','DD/MM/YYYY') AND " +
								  "contract_type='"+contract_type+"'";
					////System.out.println("FMS7-INV:DELV-QTY:QRY-I00011:DLNG_INVOICE_DTL:SELECT: "+queryString);
					////System.out.println("Invoice & Due Dates Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(Offspec_Qty_Accepted>=0.01)
						{
							Gas_Delv_Qty = Double.parseDouble(rset.getString(1))+Offspec_Qty_Accepted;
						}
						else
						{
							Gas_Delv_Qty = Double.parseDouble(rset.getString(1));
						}
					}
					
					PNQ_Qty = 0;
					
					if(contract_type.trim().equalsIgnoreCase("R"))
					{
						/*if(Gas_Delv_Qty<re_qty)
						{
							PNQ_Qty = 0;
							Gas_Delv_RE_Qty = Gas_Delv_Qty;
						}
						else
						{*/
							if(Gas_Delv_Qty<MDCQ_Qty)
							{
								PNQ_Qty = Gas_Delv_Qty;
							}
							else
							{
								PNQ_Qty = MDCQ_Qty;
							}
												
							Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;
							
							if(Gas_Delv_RE_Qty<0)
							{
								Gas_Delv_RE_Qty = 0;
							}
						//}
					}
					else
					{
						if(Gas_Delv_Qty<MDCQ_Qty)
						{
							PNQ_Qty = Gas_Delv_Qty;
						}
						else
						{
							PNQ_Qty = MDCQ_Qty;
						}
											
						Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;
						
						if(Gas_Delv_RE_Qty<0)
						{
							Gas_Delv_RE_Qty = 0;
						}
					}
					
					/*Gas_Delv_RE_Qty = Gas_Delv_Qty - PNQ_Qty;
					
					if(Gas_Delv_RE_Qty<0)
					{
						Gas_Delv_RE_Qty = 0;
					}*/
					invoice_Period_Gas_Delivered_PNQ.add(nf.format(PNQ_Qty));
					invoice_Period_Gas_Delivered_Re_Qty.add(nf.format(Gas_Delv_RE_Qty));
					invoice_Period_Gas_Delivered_Total_Qty.add(nf.format(Gas_Delv_Qty));
					
					Total_Gas_Delv_Qty += Gas_Delv_Qty;
					
					invoice_Period_Cumulative_Qty.add(nf.format(Total_Gas_Delv_Qty));
					SN_Qty_Before_Inv_Period_Start += SN_Qty_On_Inv_Period_Dates;
					invoice_Period_Cumulative_SN_Qty.add(nf.format(SN_Qty_Before_Inv_Period_Start));
				}
				
				
				if(!date_portion.trim().equals("") && date_portion.trim().length()==10)
				{
					String period_start_dt = "";
					String period_end_dt = "";
					
					month = date_portion.trim().substring(3,5);
					year = date_portion.trim().substring(6);
								
					////System.out.println("billCycle = "+billCycle);
					
					if(billCycle.equals("2") || billCycle.equals("1")) //1st-Fortnightly or 2nd-Fortnightly Invoice ...
					{
						if(billCycle.equals("2")) //2nd-Fortnightly Invoice ...
						{
							period_start_dt = "16"+"/"+month+"/"+year;
						}
						else  //1st-Fortnightly Invoice ...
						{
							period_start_dt = "01"+"/"+month+"/"+year;
						}
						
						if(billCycle.equals("1"))  //1st-Fortnightly Invoice ...
						{
							period_end_dt = "15"+"/"+month+"/"+year;
						}
						else  //2nd-Fortnightly Invoice ...
						{
							queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
							////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								period_end_dt = rset.getString(1);
							}
						}
					}
					else if(billCycle.equals("3")) //1st-Weekly Invoice ...
					{
						period_start_dt = "01"+"/"+month+"/"+year;
						period_end_dt = "07"+"/"+month+"/"+year;
					}
					else if(billCycle.equals("4")) //2nd-Weekly Invoice ...
					{
						period_start_dt = "08"+"/"+month+"/"+year;
						period_end_dt = "14"+"/"+month+"/"+year;
					}
					else if(billCycle.equals("5")) //3rd-Weekly Invoice ...
					{
						period_start_dt = "15"+"/"+month+"/"+year;
						period_end_dt = "21"+"/"+month+"/"+year;
					}
					else if(billCycle.equals("6")) //4th-Weekly Invoice ...
					{
						period_start_dt = "22"+"/"+month+"/"+year;
						
						queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
						////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							period_end_dt = rset.getString(1);
						}
					}
					else if(billCycle.equals("7"))  //Monthly Invoice ...
					{
						period_start_dt = "01"+"/"+month+"/"+year;
						
						queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+month+"/"+year+"','MM/YYYY')),'DD/MM/YYYY') FROM DUAL";
						////System.out.println("Query For Fetch Last Date Of The Selected Month = "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							period_end_dt = rset.getString(1);
						}
					}
					
					String new_period_end_dt = "";
					String new_period_start_dt = "";
					int date_diff = 0;
					
					queryString = "SELECT TO_CHAR(gas_dt,'dd/mm/yyyy') " +
							  "FROM DLNG_ALLOC_MST " +
							  "WHERE gas_dt<=TO_DATE('"+period_end_dt+"','DD/MM/YYYY') "
							  + "  AND MAPPING_ID='"+customer_inv_mapping_id+"' and "+
							  "ENTRY_TOT_ENE>=0.99 " +
							  "ORDER BY gas_dt DESC";
					////System.out.println("FMS7-INV:END-DATE:QRY-I00012:DLNG_DAILY_ALLOCATION_DTL:SELECT: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						new_period_end_dt = rset.getString(1);
					}
					
					if(!new_period_end_dt.trim().equals("") && new_period_end_dt.trim().length()==10)
					{
						queryString = "SELECT " +
									  "TO_DATE('"+new_period_end_dt+"','dd/mm/yyyy')" +
									  "-" +
									  "TO_DATE('"+invoice_Period_Dates.elementAt(invoice_Period_Dates.size()-1)+"','dd,mm/yyyy') " +
									  "FROM DUAL";
						
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							date_diff = rset.getInt(1);
							
							if(date_diff>0)
							{
								queryString2 = "SELECT TO_CHAR(TO_DATE('"+invoice_Period_Dates.elementAt(invoice_Period_Dates.size()-1)+"','dd,mm/yyyy')+1,'dd/mm/yyyy') FROM DUAL";
								rset2 = stmt2.executeQuery(queryString2);
								if(rset2.next())
								{
									new_period_start_dt = rset2.getString(1);
								}
							}
						}
					}
					
					if(date_diff>0 && !new_period_start_dt.trim().equals("") && new_period_start_dt.trim().length()==10)
					{
						for(int i=0; i<date_diff; i++)
						{
							double SN_Qty_On_Inv_Period_Dates = 0;
							double mdcq_percentage = 100;
							double DCQ_Qty = 0;
							double MDCQ_Qty = 0;
							double PNQ_Qty = 0;
							
							
							queryString = "SELECT NVL(SUM(ENTRY_TOT_ENE),'0.00') FROM DLNG_ALLOC_MST " +
									  " WHERE MAPPING_ID = '"+customer_inv_mapping_id+"' "+
									  " gas_dt=(TO_DATE('"+new_period_start_dt+"','DD/MM/YYYY')+"+i+")";
							////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								SN_Qty_On_Inv_Period_Dates = Double.parseDouble(rset.getString(1));
							}
							String period_dt = "";
							queryString = "SELECT TO_CHAR(TO_DATE('"+new_period_start_dt+"','dd/mm/yyyy')+"+i+",'dd/mm/yyyy') FROM DUAL";
							////System.out.println("Query For Fetching Total QTY For SN/LOA/RE-GAS On Invoice Period Date From DLNG_DAILY_ALLOCATION_DTL Table :\n "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								period_dt = rset.getString(1);
							}
							
							if(contract_type.equalsIgnoreCase("S"))
							{
								queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_SN_MST " +
											  "WHERE customer_cd="+customer_cd+" AND " +
											  "flsa_no="+fgsa_no+" AND " +
											  //"fgsa_rev_no="+fgsa_rev_no+" AND " +
											  "sn_no="+sn_no+" AND " +
											  "sn_rev_no="+sn_rev_no+"";
								
								////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_SN_MST Table = "+queryString);
								
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
									mdcq_percentage = Double.parseDouble(rset.getString(2));
									MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
								}
								
								queryString4 = "SELECT NVL(A.dcq,'0') FROM DLNG_SN_DCQ_DTL A " +
								   			   "WHERE A.customer_cd="+customer_cd+" AND A.fgsa_no="+fgsa_no+" " +
								   			   //"AND A.fgsa_rev_no="+fgsa_rev_no+" " +
								   			   "AND A.sn_no="+sn_no+" " +
								   			   "AND A.sn_rev_no="+sn_rev_no+" " +
								   			   "AND A.from_dt<=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
								   			   "AND A.to_dt>=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
								   			   "ORDER BY A.from_dt DESC";
								////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									//SB20110915 if(Double.parseDouble(rset4.getString(1))>0)
									if(Double.parseDouble(rset4.getString(1))>=0)
									{
										DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
										MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
									}
								}
							}
							else if(contract_type.equalsIgnoreCase("L"))
							{
								queryString = "SELECT NVL(dcq,'0.00'),NVL(mdcq_percentage,'100.00') FROM DLNG_LOA_MST " +
											  "WHERE customer_cd="+customer_cd+" AND tender_no="+fgsa_no+" AND " +
											  "loa_no="+sn_no+" AND loa_rev_no="+sn_rev_no+"";
								
								////System.out.println("Query For Fetching DCQ For the Invoice Period From DLNG_LOA_MST Table = "+queryString);
								
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
									mdcq_percentage = Double.parseDouble(rset.getString(2));
									MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
								}
								
								queryString4 = "SELECT NVL(A.dcq,'0') FROM FMS7_LOA_DCQ_DTL A " +
								   			   "WHERE A.customer_cd="+customer_cd+" AND A.tender_no="+fgsa_no+" " +
								   			   "AND A.loa_no="+sn_no+" " +
								   			   "AND A.loa_rev_no="+sn_rev_no+" " +
								   			   "AND A.from_dt<=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
								   			   "AND A.to_dt>=TO_DATE('"+period_dt+"','DD/MM/YYYY') " +
								   			   "ORDER BY A.from_dt DESC";
								////System.out.println("Customer SN DCQ Fetch Query = "+queryString4);
								rset4 = stmt4.executeQuery(queryString4);
								if(rset4.next())
								{
									if(Double.parseDouble(rset4.getString(1))>0)
									{
										DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset4.getString(1))));
										MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;
									}
								}
							}
							else if(contract_type.equalsIgnoreCase("R"))
							{
								queryString = "SELECT NVL(mdcq_percentage,'100.00') FROM FMS7_RE_GAS_MST " +
											  "WHERE customer_cd="+customer_cd+" AND re_gas_no="+fgsa_no+" AND " +
											  "rev_no="+fgsa_rev_no+"";
								
								////System.out.println("Query For Fetching MDCQ Percentage For the Invoice Period From FMS7_RE_GAS_MST Table = "+queryString);
								
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									mdcq_percentage = Double.parseDouble(rset.getString(1));
								}
								
								queryString = "SELECT NVL(dcq_qty,'0.00') FROM FMS7_RE_GAS_CARGO_DTL " +
											  "WHERE customer_cd="+customer_cd+" AND re_gas_no="+fgsa_no+" AND " +
											  "cargo_seq_no="+sn_no+"";
								
								////System.out.println("Query For Fetching DCQ For the Invoice Period From FMS7_RE_GAS_CARGO_DTL Table = "+queryString);
								
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									DCQ_Qty = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
								}
								MDCQ_Qty = (DCQ_Qty*mdcq_percentage)/100;					
							}
							
							invoice_Period_Dates.add(period_dt);
							invoice_Period_DCQ.add(nf5.format(DCQ_Qty));
							invoice_Period_Buyer_Nom_Qty.add(nf.format(0));
							invoice_Period_Buyer_Shortfall_Qty.add("");
							invoice_Period_Buyer_Off_Spec_Qty.add("");
							invoice_Period_Buyer_Suspension_Qty.add("");
							invoice_Period_Delv_Failure_Qty.add("");
							invoice_Period_Total_Shortfall_Qty.add("");
							invoice_Period_LD_Credit_Payable.add("");
							invoice_Period_Seller_Nom_PNQ.add(nf.format(0));
							invoice_Period_Gas_Delivered_PNQ.add(nf.format(0));
							invoice_Period_Seller_Nom_RE_Qty.add(nf.format(0));
							invoice_Period_FM_Qty.add("");
							invoice_Period_Gas_Delivered_Re_Qty.add(nf.format(0));
							invoice_Period_Gas_Delivered_Total_Qty.add(nf.format(0));
							invoice_Period_Cumulative_Qty.add(nf.format(Total_Gas_Delv_Qty));
							SN_Qty_Before_Inv_Period_Start += SN_Qty_On_Inv_Period_Dates;
							invoice_Period_Cumulative_SN_Qty.add(nf.format(SN_Qty_Before_Inv_Period_Start));
						}
					}
				}
			}			
		}
		catch(Exception e)
		{
			//System.out.println("EXCEPTION IN Method : "+methodName+" Of "+databeanName+" DataBean = "+e.getMessage());
			e.printStackTrace();
		}
	}//End Of Method fetchInvoiceReport() ...
///////////////////SB20160329: New Fn for Reverse Invoice////////////////
	public void ReverseInvoice_CrNote()
	{		
		String days="365";
		////System.out.println("DRCR: Reverse Invoice : -----Started >>>");
		try
		{			
			tax_amt_inr="0";
			
			double tax_amt = 0;
			String tax_cd = "0";
			String tax_factor = "0.00";
			double tot_tax_amt= 0;
			if(!(inv_dt.trim().equals("")) && inv_dt!=null)
			{	
				queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
							  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd+" AND " +
							  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd+" AND " +
							  "B.app_date<=TO_DATE('"+inv_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code";
				////System.out.println("INV-REVERSE:R001: "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					tax_cd = rset.getString(1);
					tax_factor = rset.getString(2);
					
					if(rset.getString(3).equals("1"))
					{
						if(!(dr_cr_gross_amt.trim().equals("")))
						{
							tax_amt = (Double.parseDouble(dr_cr_gross_amt)*Double.parseDouble(rset.getString(2)))/100;
						}
					}
					else if(rset.getString(3).equals("2"))
					{
						queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struc_cd+" AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struc_cd+" AND " +
									  "B.app_date<=TO_DATE('"+inv_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset.getString(4)+"";
						////System.out.println("INV-REVERSE:R002: "+queryString1);
				 		rset1=stmt1.executeQuery(queryString1);
				 		if(rset1.next())
				 		{
					 			if(rset1.getString(3).equals("1") )
								{
					 				if(!(dr_cr_gross_amt.trim().equals("")))
									{
					 					tax_amt = (Double.parseDouble(dr_cr_gross_amt)*Double.parseDouble(rset1.getString(2)))/100;
									}
								}
								
					 			tax_amt = (tax_amt*Double.parseDouble(rset.getString(2)))/100;
				 		}
				 		else
				 		{
				 			tax_amt = 0;
				 		}
					}
					else
					{
						tax_amt = 0;
					}
					
					customer_Invoice_Tax_Code.add(tax_cd);
					customer_Invoice_Tax_Rate.add(nf.format(Double.parseDouble(tax_factor)));
					customer_Invoice_Tax_Amt.add(nf.format(tax_amt));
					tot_tax_amt+=tax_amt;
				}
		    }
			//systemout.println("customer_Invoice_Tax_Amt = "+customer_Invoice_Tax_Amt+", tax_amt_inr = "+tax_amt_inr);
			//for(int i=0;i<customer_Invoice_Tax_Amt.size();i++)
			//{
				tax_amt_inr = nf.format(tot_tax_amt);
			//}
			
			//tax_amt_inr = nf.format(Double.parseDouble(dr_cr_gross_amt));
			if(!(dr_cr_gross_amt.trim().equals("")))
			{
				dr_cr_net_amt = nf.format(Double.parseDouble(dr_cr_gross_amt) + Double.parseDouble(tax_amt_inr));
			}
			////System.out.println("tax_amt_inr = "+tax_amt_inr+", dr_cr_net_amt = "+dr_cr_net_amt);
			////System.out.println("contract_type:  "+contract_type);
			//Logic For Interest on Delayed Payment
			if(contract_type.trim().equalsIgnoreCase("S"))
			{
				////System.out.println("S: contract_type:  "+contract_type);
			}
			else if(contract_type.trim().equalsIgnoreCase("L"))
			{
				////System.out.println("L: contract_type:  "+contract_type);
			}
			else if(contract_type.trim().equalsIgnoreCase("R"))
			{
				////System.out.println("R: contract_type:  "+contract_type);
			}
			else if(contract_type.trim().equalsIgnoreCase("T")) //ADDED FOR LTCORA AND CN
			{
				////System.out.println("T: contract_type:  "+contract_type);
			}
			else if(contract_type.trim().equalsIgnoreCase("C")) //ADDED FOR LTCORA AND CN
			{
				////System.out.println("C: contract_type:  "+contract_type);
			}

			queryString="SELECT SN_NO,FGSA_NO,CUSTOMER_CD,PLANT_SEQ_NO,CONTRACT_TYPE,HLPL_INV_SEQ_NO," +
			"FINANCIAL_YEAR,SN_REV_NO,FGSA_REV_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TOTAL_QTY,SALE_PRICE," +
			"GROSS_AMT_INR,NET_AMT_INR,CRITERIA,TO_CHAR(DUE_DT,'DD/MM/YYYY'),EXCHG_RATE_VALUE,DR_CR_FLAG," +
			"DR_CR_NO,TO_CHAR(DR_CR_DT,'DD/MM/YYYY'),DR_CR_EXG_RATE,DIFF_QTY,DIFF_AMT,DAY_DIFF," +
			"INT_RATE_TYPE,INT_RATE,INT_AMT,REMARK,DR_CR_DOC_NO,DR_CR_GROSS_AMT_INR,DR_CR_NET_AMT_INR," +
			"DR_CR_GROSS_AMT_USD,GROSS_AMT_USD,TAX_AMT_INR,TAX_STRUCT_CD,DR_CR_FIN_YEAR FROM FMS7_DR_CR_NOTE Where CUSTOMER_CD='"+customer_cd+"' " +
			"AND SN_NO='"+sn_no+"' AND FGSA_NO='"+fgsa_no+"' AND PLANT_SEQ_NO='"+plant_no+"' AND " +
			"CONTRACT_TYPE='"+contract_type+"' AND HLPL_INV_SEQ_NO='"+hlplInvoiceNo +"' AND " +
			"FINANCIAL_YEAR='"+fin_year+"' AND CRITERIA='"+criteria+"' ";
			////System.out.println("FMS7_DR_CR_NOTE : "+queryString);
			rset = stmt.executeQuery(queryString);				
			if(rset.next())
			{				
				//sn_rev_no     = rset.getString(8)==null?"":rset.getString(8);
				//fgsa_rev_no   = rset.getString(9)==null?"":rset.getString(9);
				//inv_dt        = rset.getString(10)==null?"":rset.getString(10);
				//qty 		  = rset.getString(11)==null?"":rset.getString(11);
				//sale_price 	  = rset.getString(12)==null?"":rset.getString(12);		
				//gross_amt 	  = rset.getString(13)==null?"":rset.getString(13);
				//net_amt 	  = rset.getString(14)==null?"":rset.getString(14);				
				//criteria 	  = rset.getString(15)==null?"":rset.getString(15);							
				//due_dt 		  = rset.getString(16)==null?"":rset.getString(16);				
				//exg_rt 		  = rset.getString(17)==null?"":rset.getString(17);
				//cr_dr 		  = rset.getString(18)==null?"":rset.getString(18);
				dr_cr_no 	  = rset.getString(19)==null?"":rset.getString(19);
				dr_cr_dt 	  = rset.getString(20)==null?"":rset.getString(20);
				dr_cr_exg_rt  = rset.getString(21)==null?"":rset.getString(21);						
				diff_qty 	 = rset.getString(22)==null?"":rset.getString(22);
				diff_amt     = rset.getString(23)==null?"":rset.getString(23);
				day_diff     = rset.getString(24)==null?"":rset.getString(24);					
				//int_type  	 = rset.getString(25)==null?"":rset.getString(25);
				
				int_rate_cal     = rset.getString(26)==null?"":rset.getString(26);
				if(int_amt.trim().equals(""))
				{
					int_amt = rset.getString(27)==null?"":rset.getString(27);
				}
				remark  	 = rset.getString(28)==null?"":rset.getString(28);
				dr_cr_doc_no = rset.getString(29)==null?"":rset.getString(29);
				if(rset.getString(30)!=null && !(rset.getString(30).trim().equals("")))
				{
					dr_cr_gross_amt = nf.format(Double.parseDouble(rset.getString(30)));
				}
				if(dr_cr_net_amt.trim().equals(""))
				{
					dr_cr_net_amt  = rset.getString(31)==null?"":rset.getString(31);
				}
				dr_cr_gross_usd = rset.getString(32)==null?"":rset.getString(32);
				gross_amt_usd = rset.getString(33)==null?"":rset.getString(33);
				//tax_amt_inr = rset.getString(34)==null?"":rset.getString(34);
				//tax_struc_cd = rset.getString(35)==null?"":rset.getString(35);
				dr_cr_no_disp  = rset.getString(19)==null?"":rset.getString(19)+"/"+rset.getString(36).substring(2,4)+"-"+rset.getString(36).substring(7);
				//////System.out.println("sn_rev_no "+sn_rev_no+"");
				//////System.out.println("dr_cr_no = "+dr_cr_no);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	/////////////SB20160527: Get Invoice available for the FY //////////////
	public void fetchInvAvailableFY()
	{
		try
		{
			String query="SELECT DISTINCT(FINANCIAL_YEAR) FROM DLNG_INVOICE_MST " +
					" ORDER BY FINANCIAL_YEAR DESC";
			rset=stmt.executeQuery(query);
		//	//System.out.println("INV-FY: "+query);
			while(rset.next())
			{
				VInv_FY.add(rset.getString(1));				
			}
			if(fy.equals("0"))
			{
				for(int i=0; i<VInv_FY.size(); i++) 
				{
					query="SELECT DISTINCT(INVOICE_DT), TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') " +
							" FROM DLNG_INVOICE_MST WHERE FINANCIAL_YEAR='"+VInv_FY.elementAt(i)+"' " +
							" ORDER BY INVOICE_DT DESC";
					rset=stmt.executeQuery(query);
					////System.out.println("1. INV-DT: "+query);
					while(rset.next())
					{
						VInv_Dt_FY.add(rset.getString(2));	
					}
				}
			}
			else
			{
				query=  " SELECT DISTINCT(INVOICE_DT), TO_CHAR(INVOICE_DT, 'DD/MM/YYYY') " +
						" FROM DLNG_INVOICE_MST " +
						" WHERE FINANCIAL_YEAR='"+fy+"' " +
						" ORDER BY INVOICE_DT DESC";
				rset=stmt.executeQuery(query);
				////System.out.println("2. INV-DT: "+query);
				while(rset.next())
				{
					VInv_Dt_FY.add(rset.getString(2));	
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	Map invoice_signded_pdf=new HashMap();//HS20160604
	Map invoice_view_pdf=new HashMap();
	
	public void PDFFileNmForInvoice()
	{
		try
		{
			HttpSession sess = request.getSession();
			/*String invoice_bench_date="10/03/2015";
			//SB String invoice_pdf_path = sess.getAttribute("invoice_pdf_path").toString();
			
			String curr_date="01/01/2016";
		
			String tempD1[]=invoice_bench_date.split("/");
			String d1=tempD1[2]+tempD1[1]+tempD1[0];
			
			String tempD2[]=curr_date.split("/");
			String d2=tempD2[2]+tempD2[1]+tempD2[0];
			
			if(Integer.parseInt(d2)>Integer.parseInt(d1))
			{*/
			////System.out.println(VPDF_Inv_Dt.size()+"---VPDF_Inv_Dt-- "+invoice_inv_period_dt);
			////System.out.println(hlpl_Invoice_Seq_No_Arr.size()+"---hlpl_Invoice_Seq_No_Arr-- "+hlpl_Invoice_Seq_No_Arr);
				for(int j=0; j<hlpl_Invoice_Seq_No_Arr.size(); j++)
				{
				//HS20160620	String invoice_date=""+VPDF_Inv_Dt.elementAt(j);
					String invoice_date=""+	invoice_inv_period_dt.elementAt(j); //HS20160620
				//	//System.out.println("----invoice_date-----> "+invoice_date);
//					System.out.println("--path--"+invoice_Customer_Plant_Nm.elementAt(j));
					if(!invoice_date.equals(""))
						VPDF_File_Nm.add("INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+invoice_Customer_Abbr.elementAt(j)+"-"+invoice_Customer_Plant_Nm.elementAt(j)+"-"+invoice_Contract_Type.elementAt(j)+"-"+hlpl_Invoice_Seq_No_Arr.elementAt(j));
					else
						VPDF_File_Nm.add("-");
					VCustContPlantStDt.add(invoice_Customer_Abbr.elementAt(j)+"-"+invoice_Customer_Plant_Nm.elementAt(j)+"-"+invoice_Contract_Type.elementAt(j)+"-"+invoice_SN_No.elementAt(j)+"-"+invoice_FGSA_No.elementAt(j));
//HS20160730					VCustContPlantStDt.add(invoice_Customer_Abbr.elementAt(j)+"-"+invoice_Customer_Plant_Nm.elementAt(j)+"-"+invoice_Contract_Type.elementAt(j)+"-"+invoice_SN_No.elementAt(j));
				}
//				System.out.println("**** VPDF_File_Nm------"+VPDF_File_Nm);
				///0-----View PDF-----HS20160603-------///
				String file1="",file2="";

				for(int i = 0; i < VPDF_File_Nm.size(); i++){//HS20160618
					Vector invoice_path=new Vector();
						String qq1="select pdf_inv_nm,inv_type from  dlng_inv_pdf_dtl"
								+ " where pdf_inv_nm='"+VPDF_File_Nm.elementAt(i).toString()+"' ";
						rset=stmt.executeQuery(qq1);
						
						while(rset.next()){
							file1=rset.getString(1)+"-"+rset.getString(2);
							invoice_path.add(file1);
						}
				invoice_view_pdf.put(i, invoice_path);
				}	
				//--------EnD---HS20160603------------------//	
				
				///0----Signed PDF-----HS20160604-------///
				/*for(int i = 0; i < VPDF_File_Nm.size(); i++){
					Vector invoice_path=new Vector();
					//File f=new File(s1+"/"+VPDF_File_Nm.elementAt(i).toString());
						String qq1="select pdf_inv_nm,inv_type from  dlng_inv_pdf_dtl"
								+ " where pdf_inv_nm='"+VPDF_File_Nm.elementAt(i).toString()+"' and pdf_signed is not null";
						////System.out.println("-------qq1-----"+qq1);
						rset=stmt.executeQuery(qq1);
						
						while(rset.next()){
							file2=rset.getString(1)+"-"+rset.getString(2);
							invoice_path.add(file2);
						}
						invoice_signded_pdf.put(i, invoice_path);
				}	*/
				//--------EnD---HS20160604------------------//
		//		//System.out.println("invoice_signded_pdf0-------"+invoice_signded_pdf);
		//		//System.out.println("invoice_view_pdf0-------"+invoice_view_pdf);
			//}
				
				
				/*--------Not required now----------------HS20160722
				 * This code is used for renaming the file names and it needs to exceute only once to rename file name
				 * Ex.--- INVOICE-07022015-BPCL-BPCL Mumbai Refinery-O-2015-02-16 16-31-40.pdf to be change as 
				 * 			INVOICE-07022015-BPCL-BPCL Mumbai Refinery-O.pdf
				  //------------HS20160609---For Folder files inclusion in Table and rename files ---//
				 
				String invoice_pdf_path = sess.getAttribute("invoice_pdf_path").toString();
				
				String[] file_bunch_qtr= null;
				File lst_qtr= new File(invoice_pdf_path);
				file_bunch_qtr=lst_qtr.list();
				int view_cnt=0,rename_cnt=0,else_cnt=0,inv_cnt=0;
				for ( int i=0;i<file_bunch_qtr.length;i++ )
			    {
					String file=file_bunch_qtr[i];
					
						////System.out.println(i+"----file_bunch_qtr[i]--------"+file_bunch_qtr[i]);//HS20160618
						if(file_bunch_qtr[i].toString().contains(".pdf")){
							String temp_type=file_bunch_qtr[i].toString().trim().substring(file_bunch_qtr[i].toString().trim().length()-6,file_bunch_qtr[i].toString().trim().length());
							 if(!temp_type.equalsIgnoreCase("-O.pdf") && !temp_type.equalsIgnoreCase("-D.pdf") && !temp_type.equalsIgnoreCase("-T.pdf") && !temp_type.equalsIgnoreCase("-C.pdf")){
								 ////System.out.println("path----------"+file_bunch_qtr[i]);
								 String hh=file_bunch_qtr[i].toString().trim().replace(".pdf", "").substring(0,file_bunch_qtr[i].toString().replace(".pdf", "").length()-20);
									////System.out.println("path----------"+hh);
									String inv_type=hh.substring(hh.length()-1).trim();
									if(inv_type.equalsIgnoreCase("O") || inv_type.equalsIgnoreCase("D") || inv_type.equalsIgnoreCase("T") || inv_type.equalsIgnoreCase("C")  ){
										String quer="select count(*) from dlng_inv_pdf_dtl where"
												+ " PDF_INV_NM='"+hh.substring(0,hh.length()-2).trim()+"'"
														+ " and INV_TYPE='"+hh.substring(hh.length()-1).trim()+"'";
										
										rset=stmt.executeQuery(quer);
										if(rset.next()){
											if(rset.getInt(1)>0){
												////System.out.println("queey0----"+quer);
												////System.out.println("--hs----"+hh);
												//break;
											}else{
												if(hh.contains("_V")){
													view_cnt++;
												}else{
													PreparedStatement pstmt=null;
													try{//HS20160618
														 pstmt = conn.prepareStatement("insert into dlng_inv_pdf_dtl  "
																+ "  (PDF_INV_NM,created_dt,INV_TYPE,flag) values ('"+hh.substring(0,hh.length()-2).trim()+"',sysdate,'"+hh.substring(hh.length()-1).trim()+"','Y') ");
														
														File blob = new File(invoice_pdf_path+"//"+file);
													//	FileInputStream in = new FileInputStream(blob);
														//pstmt.setBinaryStream(1, in, (int)blob.length());
														//pstmt.setInt(2, 101);  
														pstmt.executeUpdate();
														conn.commit();
														////System.out.println("-----------hh.pdf-------"+invoice_pdf_path+"//"+hh+".pdf");
														File newfile1 =new File(invoice_pdf_path+"//"+hh+".pdf");
														if(blob.renameTo(newfile1)){
															rename_cnt++;
														}
													}
													catch(Exception e){
														if(pstmt!=null){pstmt.close();}
														e.printStackTrace();
													}finally{
														if(pstmt!=null){pstmt.close();}
													}
												}
											}
										}
									}else{
										inv_cnt++;
									}
							 }else{
								 else_cnt++;
							 }
						}
					}
					//System.out.println("--Rename files -cnt----"+rename_cnt);
					//System.out.println("--View files -cnt----"+view_cnt);
					//System.out.println("--View else_cnt -cnt----"+else_cnt);
					//System.out.println("--View inv_cnt -cnt----"+inv_cnt);
					//System.out.println("VCustContPlantStDt: "+VCustContPlantStDt.size());
					//System.out.println("VCustContPlantStDt: "+VCustContPlantStDt);
					//-----------END---HS20160906--------*/
					
					int start=0;
					for(int i=start; i<VCustContPlantStDt.size(); i++)
					{////System.out.println(i+" 1.Value: "+VCustContPlantStDt.elementAt(i));
						int count=0;
						for(int j=start+1; j<VCustContPlantStDt.size(); j++)
						{////System.out.println(j+" 2.Value: "+VCustContPlantStDt.elementAt(j));
							if(VCustContPlantStDt.elementAt(i).equals(VCustContPlantStDt.elementAt(j)))
							{////System.out.println(j+" Value = "+VCustContPlantStDt.elementAt(j));
								VCustContPlantStDt_Flag.add("Y");
								VCustContPlantCrDrAprv_Flag.add(VDrCrAprvBy.elementAt(j)); //SB20160623
								start++;
								count++;
								break;
							}
						}
						if(count==0)
						{
							if(!VDrCrAprvBy.elementAt(i).equals("0"))
								VCustContPlantStDt_Flag.add("Y");
							else
								VCustContPlantStDt_Flag.add("N");
								VCustContPlantCrDrAprv_Flag.add("X"); //SB20160623
								start++;
							}
					}
					////System.out.println("VCustContPlantStDt_Flag: "+VCustContPlantStDt_Flag.size());
					////System.out.println("VCustContPlantStDt_Flag: "+VCustContPlantStDt_Flag);
		}
		catch(Exception e)
		{
			//System.out.println("Exception in createPdfFileForInvoice() Method :\n"+e.getMessage());
			e.printStackTrace();
		}
		//SB return invoice_pdf_path;
	}
	
	/////SB20160602:///
	public void AddColumnFMS7_DR_CR_NOTE()
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS7_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'SUN_APPROVAL' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table FMS7_DR_CR_NOTE add SUN_APPROVAL CHAR(1)";
				stmt.executeUpdate(query);
				query="alter table FMS7_DR_CR_NOTE add SUN_APPROVAL_BY NUMBER(6)";
				stmt.executeUpdate(query);
				query="alter table FMS7_DR_CR_NOTE add SUN_APPROVAL_DT DATE";
				stmt.executeUpdate(query);
				conn.commit();
			}
			
			count=0;
			query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'FMS7_DR_CR_NOTE' "
					+ "AND UPPER(COLUMN_NAME) LIKE 'APRV_BY' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="alter table FMS7_DR_CR_NOTE add APRV_BY NUMBER(6)";
				stmt.executeUpdate(query);
				query="alter table FMS7_DR_CR_NOTE add APRV_DT DATE";
				stmt.executeUpdate(query);
				conn.commit();
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
////////////////SB20180926/////////////////////
	public void AllocationDataFYwise()
	{
		try 
		{
			queryString1 = " SELECT SUPPLIER_ABBR, SUPPLIER_NAME, PLANT_STATE  FROM FMS7_SUPPLIER_MST A, FMS7_SUPPLIER_PLANT_DTL B WHERE A.SUPPLIER_CD=B.SUPPLIER_CD ";
		//	System.out.println("Min. Yr "+queryString1+rset1+stmt1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				Supl_abr = rset1.getString(1);
				Supl_nm = rset1.getString(2);
				Supl_plant_state = rset1.getString(3);
			}
			
			int MinYear=0, MinMonth=0,MaxYear=0,MaxMonth=0;
			queryString1 = "SELECT TO_CHAR(MIN(GAS_DT),'YYYY'), TO_CHAR(MIN(GAS_DT),'MM'), TO_CHAR(MAX(GAS_DT),'YYYY'), TO_CHAR(MAX(GAS_DT),'MM') " +
					" FROM DLNG_ALLOC_MST ";
	//		System.out.println("Min. Yr "+queryString1+rset1+stmt1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				MinYear = rset1.getInt(1);
				MinMonth = rset1.getInt(2);	
				MaxYear = rset1.getInt(3);
				MaxMonth = rset1.getInt(4);	
				AllocatedMinFY=""+MinYear;
				AllocatedMaxFY=""+MaxYear;
				AllocatedMaxMth=""+MaxMonth;
				
			}
			
			for(int i=MaxYear; i>=MinYear; i--)
			{
				FY_AllocatedData.add(i);
				FY_StartingYr.add(i);
				FY_EndingYr.add(i+1);
			}
//			System.out.println("AllocatedData Yr "+FY_AllocatedData);
//			System.out.println("StartingYr "+FY_StartingYr);
//			System.out.println("EndingYr "+FY_EndingYr);
//			System.out.println("MinMonth "+MinMonth);
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
	String AllocatedMinFY="";
	public String getAllocatedMinFY() {return AllocatedMinFY;}
	String AllocatedMaxFY="";
	public String getAllocatedMaxFY() {return AllocatedMaxFY;}
	String AllocatedMaxMth="";
	public String getAllocatedMaxMth() {return AllocatedMaxMth;} //SB20181020
	Vector FY_AllocatedData= new Vector();
	public Vector getFY_AllocatedData() {return FY_AllocatedData;}	
	Vector FY_StartingYr= new Vector();
	public Vector getFY_StartingYr() {return FY_StartingYr;}
	Vector FY_EndingYr= new Vector();
	public Vector getFY_EndingYr() {return FY_EndingYr;}
	///////////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////
/////	
	public Vector TAX_AMT_INR=new Vector();  //SB20160401 
	public Vector FLAG=new Vector();  //SB20160401
	public Vector getTAX_AMT_INR() {return TAX_AMT_INR;}  //SB20160401
	public Vector getFLAG() {return FLAG;}  //SB20160401
////////////////////////////////////////////
		
	//Following String Setter Methods Are Defined By Samik Shah On 20th May, 2010 ...
	public void setCallFlag(String callFlag) {this.callFlag = callFlag;} //Introduced By Samik Shah On 20th May, 2010 ...
	public void setMonth(String month) {this.month = month;}
	public void setYear(String year) {this.year = year;}
	public void setBillCycle(String billCycle) {this.billCycle = billCycle;}
	//Following String Setter Methods Are Defined By Samik Shah On 21st May, 2010 ...
	public void setCustomerCd(String customer_cd) {this.customer_cd = customer_cd;}
	public void setFgsaNo(String fgsa_no) {this.fgsa_no = fgsa_no;}
	public void setFgsaRevNo(String fgsa_rev_no) {this.fgsa_rev_no = fgsa_rev_no;}
	public void setSnNo(String sn_no) {this.sn_no = sn_no;}
	public void setSnRevNo(String sn_rev_no) {this.sn_rev_no = sn_rev_no;}
	public void setContractType(String contract_type) {this.contract_type = contract_type;}
	public void setCustomerPlantSeqNo(String customer_plant_seq_no) {this.customer_plant_seq_no = customer_plant_seq_no;}
	public void setBillPeriodStartDt(String bill_period_start_dt) {this.bill_period_start_dt = bill_period_start_dt;}
	public void setBillPeriodEndDt(String bill_period_end_dt) {this.bill_period_end_dt = bill_period_end_dt;}
	public void setExchgRateCd(String exchg_rate_cd) {this.exchg_rate_cd = exchg_rate_cd;}
	public void setInvoiceDate(String invoice_date) {this.invoice_date = invoice_date;}
	public void setParticularDate(String particular_date) {this.particular_date = particular_date;}
	public void setExchgRateCalMethod(String exchg_rate_cal_method) {this.exchg_rate_cal_method = exchg_rate_cal_method;}
	//Following String Setter Methods Has Been Defined By Samik Shah On 27th May, 2010 ...
	public void setRbiRefCd(String rbi_ref_cd) {this.rbi_ref_cd = rbi_ref_cd;}
	public void setSbiTtSellingCd(String sbi_tt_selling_cd) {this.sbi_tt_selling_cd = sbi_tt_selling_cd;}
	public void setSbiTtBuyingCd(String sbi_tt_buying_cd) {this.sbi_tt_buying_cd = sbi_tt_buying_cd;}
	public void setSbiAvgTtSellingBuyingCd(String sbi_avg_tt_selling_buying_cd) {this.sbi_avg_tt_selling_buying_cd = sbi_avg_tt_selling_buying_cd;}
	//Following String Setter Methods Has Been Defined By Samik Shah On 4th June, 2010 ...
	public void setContactPersonCd(String contactPersonCd) {this.contactPersonCd = contactPersonCd;}
	public void setHlplInvoiceNo(String hlplInvoiceNo) {this.hlplInvoiceNo = hlplInvoiceNo;}
	public void setInvFinancialYear(String invFinancialYear) {this.invFinancialYear = invFinancialYear;}
	//Following (4) Setter Methods Has Been Defined By Samik Shah On 20th January, 2011 ...
	public void setCustomer_Invoice_Gross_Amt_USD(String customer_Invoice_Gross_Amt_USD) {this.customer_Invoice_Gross_Amt_USD = customer_Invoice_Gross_Amt_USD;}
	public void setCustomer_Invoice_Gross_Amt_INR(String customer_Invoice_Gross_Amt_INR) {this.customer_Invoice_Gross_Amt_INR = customer_Invoice_Gross_Amt_INR;}
	public void setCustomer_Invoice_Net_Amt_INR(String customer_Invoice_Net_Amt_INR) {this.customer_Invoice_Net_Amt_INR = customer_Invoice_Net_Amt_INR;}
	public void setCustomer_Invoice_Exchg_Rate(String customer_Invoice_Exchg_Rate) {this.customer_Invoice_Exchg_Rate = customer_Invoice_Exchg_Rate;}
	//Following String Setter Method Has Been Defined By Samik Shah On 25th January, 2011 ...
	public void setPage_refresh_flag(String page_refresh_flag) {this.page_refresh_flag = page_refresh_flag;}
	
	//Following Vectors Getter Methods Are Defined By Samik Shah For Invoice Generation Process List On 20th May, 2010 ...
	public Vector getInvoice_Customer_Cd() {return invoice_Customer_Cd;}
	public Vector getInvoice_Customer_Abbr() {return invoice_Customer_Abbr;}
	public Vector getInvoice_Customer_Plant_Seq_No() {return invoice_Customer_Plant_Seq_No;}
	public Vector getInvoice_Customer_Plant_Nm() {return invoice_Customer_Plant_Nm;}
	public Vector getInvoice_HLPL_Seq_No() {return invoice_HLPL_Seq_No;}
	public Vector getInvoice_FGSA_No() {return invoice_FGSA_No;}
	public Vector getInvoice_FGSA_Rev_No() {return invoice_FGSA_Rev_No;}
	public Vector getInvoice_SN_No() {return invoice_SN_No;}
	public Vector getInvoice_SN_Rev_No() {return invoice_SN_Rev_No;}
	public Vector getInvoice_Contract_Type() {return invoice_Contract_Type;}
	public Vector getInvoice_SN_Start_Dt() {return invoice_SN_Start_Dt;}
	public Vector getInvoice_SN_End_Dt() {return invoice_SN_End_Dt;}
	public Vector getInvoice_Bill_Period_Start_Dt() {return invoice_Bill_Period_Start_Dt;}
	public Vector getInvoice_Bill_Period_End_Dt() {return invoice_Bill_Period_End_Dt;}
	public Vector getInvoice_Due_Dt() {return invoice_Due_Dt;}
	public Vector getInvoice_Exchg_Rate_Calculation_Method() {return invoice_Exchg_Rate_Calculation_Method;}
	public Vector getInvoice_Exchg_Rate_Cd() {return invoice_Exchg_Rate_Cd;}
	
	//Following Vectors & String Getter Methods Are Defined By Samik Shah For Fetching Particular Invoice Details On 21st May, 2010 ...
	public Vector getInvoice_Customer_Contact_Cd() {return invoice_Customer_Contact_Cd;}
	public Vector getInvoice_Customer_Contact_Nm() {return invoice_Customer_Contact_Nm;}
	public String getTotal_Invoice_Qty() {return total_Invoice_Qty;}
	public String getInvoice_Sales_Rate() {return invoice_Sales_Rate;}
	public String getInvoice_Previous_Available_Exchg_Date() {return invoice_Previous_Available_Exchg_Date;}
	public String getInvoice_Exchg_Rate_RBI_Ref() {return invoice_Exchg_Rate_RBI_Ref;}
	public String getInvoice_Exchg_Rate_TT_Selling() {return invoice_Exchg_Rate_TT_Selling;}
	public String getInvoice_Exchg_Rate_TT_Buying() {return invoice_Exchg_Rate_TT_Buying;}
	public String getInvoice_Exchg_Rate_Avg_TT_Buy_Sell() {return invoice_Exchg_Rate_Avg_TT_Buy_Sell;}
	public String getInvoice_Previous_Exchg_Rate_RBI_Ref() {return invoice_Previous_Exchg_Rate_RBI_Ref;}
	public String getInvoice_Previous_Exchg_Rate_TT_Selling() {return invoice_Previous_Exchg_Rate_TT_Selling;}
	public String getInvoice_Previous_Exchg_Rate_TT_Buying() {return invoice_Previous_Exchg_Rate_TT_Buying;}
	public String getInvoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell() {return invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell;}
	public String getInvoice_Particular_Day_Exchg_Rate_RBI_Ref() {return invoice_Particular_Day_Exchg_Rate_RBI_Ref;}
	public String getInvoice_Particular_Day_Exchg_Rate_TT_Selling() {return invoice_Particular_Day_Exchg_Rate_TT_Selling;}
	public String getInvoice_Particular_Day_Exchg_Rate_TT_Buying() {return invoice_Particular_Day_Exchg_Rate_TT_Buying;}
	public String getInvoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell() {return invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell;}

	//Following Vector Getter Methods Are Defined By Samik Shah For Fetching Exchange Rates For Specified Period On 27th May, 2010 ...
	public Vector getInvoice_Period_Dates() {return invoice_Period_Dates;}
	public Vector getInvoice_Period_Exchg_Rate_RBI_Ref() {return invoice_Period_Exchg_Rate_RBI_Ref;}
	public Vector getInvoice_Period_Exchg_Rate_TT_Selling() {return invoice_Period_Exchg_Rate_TT_Selling;}
	public Vector getInvoice_Period_Exchg_Rate_TT_Buying() {return invoice_Period_Exchg_Rate_TT_Buying;}
	public Vector getInvoice_Period_Exchg_Rate_Avg_TT_Buy_Sell() {return invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell;}

	//Following Vector & String Getter Methods Has Been Defined By Samik Shah On 2nd June, 2010 ...
	public Vector getDaily_Invoice_QTY() {return daily_Invoice_QTY;}
	public String getHlpl_Invoice_Seq_No() {return hlpl_Invoice_Seq_No;}
	public String getCustomer_Invoice_Seq_No() {return customer_Invoice_Seq_No;}
	public Vector getHlpl_Invoice_Seq_No_Arr() {return hlpl_Invoice_Seq_No_Arr;}
	public String getTax_Structure_Dtl() {return tax_Structure_Dtl;}

	//Following Vector Getter Methods Has Been Defined By Samik Shah On 3rd June, 2010 ...
	public Vector getHlpl_Invoice_Financial_Year_Arr() {return hlpl_Invoice_Financial_Year_Arr;}
	public Vector getHlpl_Invoice_Actual_Seq_No() {return hlpl_Invoice_Actual_Seq_No;}
	public Vector getCustomer_Invoice_Actual_Seq_No() {return customer_Invoice_Actual_Seq_No;}

	//Following String Getter Methods Has Been Defined By Samik Shah On 4th June, 2010 ...
	public String getContact_Person_Name_And_Designation() {return contact_Person_Name_And_Designation;}
	
	public String getContact_Customer_Name() {return contact_Customer_Name;}
	public String getContact_Customer_Person_Address() {return contact_Customer_Person_Address;}
	public String getContact_Customer_Person_City() {return contact_Customer_Person_City;}
	public String getContact_Customer_Person_Pin() {return contact_Customer_Person_Pin;}
	public String getContact_Customer_GST_NO() {return contact_Customer_GST_NO;}
	public String getContact_Customer_CST_NO() {return contact_Customer_CST_NO;}
	public String getContact_Customer_GST_DT() {return contact_Customer_GST_DT;}
	public String getContact_Customer_CST_DT() {return contact_Customer_CST_DT;}

	public String getContact_Suppl_Name() {return contact_Suppl_Name;}
	public String getContact_Suppl_Person_Address() {return contact_Suppl_Person_Address;}
	public String getContact_Suppl_Person_City() {return contact_Suppl_Person_City;}
	public String getContact_Suppl_Person_Pin() {return contact_Suppl_Person_Pin;}
	public String getContact_Suppl_GST_NO() {return contact_Suppl_GST_NO;}
	public String getContact_Suppl_CST_NO() {return contact_Suppl_CST_NO;}
	public String getContact_Suppl_GST_DT() {return contact_Suppl_GST_DT;}
	public String getContact_Suppl_CST_DT() {return contact_Suppl_CST_DT;}

	public String getCustomer_Invoice_DT() {return customer_Invoice_DT;}
	public String getCustomer_Invoice_Due_DT() {return customer_Invoice_Due_DT;}
	public String getCustomer_Invoice_Start_DT() {return customer_Invoice_Start_DT;}
	public String getCustomer_Invoice_End_DT() {return customer_Invoice_End_DT;}

	//Following String & Vector Getter Methods Has Been Defined By Samik Shah On 5th June, 2010 ...
	public String getCustomer_Invoice_Tax_Flag() {return customer_Invoice_Tax_Flag;}
	public String getCustomer_Invoice_SN_Dt() {return customer_Invoice_SN_Dt;}
	public String getCustomer_Invoice_FGSA_Dt() {return customer_Invoice_FGSA_Dt;}
	public String getCustomer_Invoice_Gross_Amt_USD() {return customer_Invoice_Gross_Amt_USD;}
	public String getCustomer_Invoice_Gross_Amt_INR() {return customer_Invoice_Gross_Amt_INR;}
	public String getCustomer_Invoice_Net_Amt_INR() {return customer_Invoice_Net_Amt_INR;}
	public String getCustomer_Invoice_Exchg_Rate() {return customer_Invoice_Exchg_Rate;}
	public String getCustomer_Invoice_Exchg_Rate2() {return customer_Invoice_Exchg_Rate2;}
	public Vector getCustomer_Invoice_Tax_Code() {return customer_Invoice_Tax_Code;}
	public Vector getCustomer_Invoice_Tax_Abbr() {return customer_Invoice_Tax_Abbr;}
	public Vector getCustomer_Invoice_Tax_Name() {return customer_Invoice_Tax_Name;}
	public Vector getCustomer_Invoice_Tax_Amt() {return customer_Invoice_Tax_Amt;}
	public Vector getCustomer_Invoice_Tax_Rate() {return customer_Invoice_Tax_Rate;}

	//Following String & Vector Getter Methods Has Been Defined By Samik Shah On 7th June, 2010 ...
	public String getCustomer_Invoice_Exchg_Rate_Cd() {return customer_Invoice_Exchg_Rate_Cd;}
	public Vector getCustomer_Invoice_Exchg_Rate_Code() {return customer_Invoice_Exchg_Rate_Code;}
	public Vector getCustomer_Invoice_Exchg_Rate_Name() {return customer_Invoice_Exchg_Rate_Name;}
	public Vector getCustomer_Invoice_Exchg_Rate_Date() {return customer_Invoice_Exchg_Rate_Date;}
	public Vector getCustomer_Invoice_Exchg_Rate_Value() {return customer_Invoice_Exchg_Rate_Value;}

	//Following Vector Getter Methods Has Been Defined By Samik Shah On 8th June, 2010 ...
	public Vector getInvoice_Period_DCQ() {return invoice_Period_DCQ;}
	public Vector getInvoice_Period_Buyer_Nom_Qty() {return invoice_Period_Buyer_Nom_Qty;}
	public Vector getInvoice_Period_Seller_Nom_PNQ() {return invoice_Period_Seller_Nom_PNQ;}
	public Vector getInvoice_Period_Seller_Nom_RE_Qty() {return invoice_Period_Seller_Nom_RE_Qty;}
	public Vector getInvoice_Period_Gas_Delivered_PNQ() {return invoice_Period_Gas_Delivered_PNQ;}
	public Vector getInvoice_Period_Gas_Delivered_Re_Qty() {return invoice_Period_Gas_Delivered_Re_Qty;}
	public Vector getInvoice_Period_Gas_Delivered_Total_Qty() {return invoice_Period_Gas_Delivered_Total_Qty;}
	public Vector getInvoice_Period_Cumulative_Qty() {return invoice_Period_Cumulative_Qty;}
	public Vector getInvoice_Period_Cumulative_SN_Qty() {return invoice_Period_Cumulative_SN_Qty;}
	public Vector getInvoice_Period_Buyer_Shortfall_Qty() {return invoice_Period_Buyer_Shortfall_Qty;}
	public Vector getInvoice_Period_Buyer_Off_Spec_Qty() {return invoice_Period_Buyer_Off_Spec_Qty;}
	public Vector getInvoice_Period_Buyer_Suspension_Qty() {return invoice_Period_Buyer_Suspension_Qty;}
	public Vector getInvoice_Period_Delv_Failure_Qty() {return invoice_Period_Delv_Failure_Qty;}
	public Vector getInvoice_Period_Total_Shortfall_Qty() {return invoice_Period_Total_Shortfall_Qty;}
	public Vector getInvoice_Period_LD_Credit_Payable() {return invoice_Period_LD_Credit_Payable;}
	public Vector getInvoice_Period_FM_Qty() {return invoice_Period_FM_Qty;}

	//Following String Getter Method Has Been Defined By Samik Shah On 10th June, 2010 ...
	public String getInvoice_Exchg_Rate_Note() {return invoice_Exchg_Rate_Note;}

	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 17th June, 2010 ...
	public String getRemark_1() {return remark_1;}
	public String getRemark_2() {return remark_2;}

	//Following (4) String Getter Methods Has Been Defined By Samik Shah On 26th August, 2010 ...
	public String getContact_Customer_Service_Tax_NO() {return contact_Customer_Service_Tax_NO;}
	public String getContact_Customer_Service_Tax_DT() {return contact_Customer_Service_Tax_DT;}
	public String getContact_Suppl_Service_Tax_NO() {return contact_Suppl_Service_Tax_NO;}
	public String getContact_Suppl_Service_Tax_DT() {return contact_Suppl_Service_Tax_DT;}

	public Vector getCUST_CD() {
		return CUST_CD;
	}

	public Vector getCUST_NM() {
		return CUST_NM;
	}

	public void setFrom_dt(String from_dt) {
		this.from_dt = from_dt;
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

	public Vector getGROSS_AMT_INR() {
		return GROSS_AMT_INR;
	}

	public Vector getINVOICE_DT() {
		return INVOICE_DT;
	}

	public Vector getNET_AMT_INR(){
		return NET_AMT_INR;
	}
	public Vector getPERIOD_END_DT() {
		return PERIOD_END_DT;
	}
	public Vector getPERIOD_START_DT() {
		return PERIOD_START_DT;
	}
	public Vector getTOTAL_QTY() {
		return TOTAL_QTY;
	}
	public Vector getSALE_PRICE() {
		return SALE_PRICE;
	}
	public Vector getCONTRACT_TYPE() {
		return CONTRACT_TYPE;
	}
	public Vector getFINANCIAL_YEAR() {
		return FINANCIAL_YEAR;
	}
	public Vector getHLPL_INV_SEQ_NO() {
		return HLPL_INV_SEQ_NO;
	}
	public Vector getINVOICE_NO() {
		return INVOICE_NO;
	}
	public Vector getPLANT_SEQ_NO() {
		return PLANT_SEQ_NO;
	}
	public String getCust_nm() {
		return cust_nm;
	}
	public Vector getDUE_DT() {
		return DUE_DT;
	}
	public Vector getEXCHG_RATE_VALUE() {
		return EXCHG_RATE_VALUE;
	}
	public Vector getFGSA_NO() {
		return FGSA_NO;
	}
	public Vector getFGSA_REV_NO() {
		return FGSA_REV_NO;
	}

	public Vector getSN_NO() {
		return SN_NO;
	}
	public Vector getSN_REV_NO() {
		return SN_REV_NO;
	}
	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}
	public void setFgsa_no(String fgsa_no) {
		this.fgsa_no = fgsa_no;
	}
	public void setSn_no(String sn_no) {
		this.sn_no = sn_no;
	}
	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}
	public void setPlant_no(String plant_no) {
		this.plant_no = plant_no;
	}
	public String getCr_dr() {
		return cr_dr;
	}
	public String getCriteria() {
		return criteria;
	}
	public String getDay_diff() {
		return day_diff;
	}
	public String getDiff_amt() {
		return diff_amt;
	}
	public String getDiff_qty() {
		return diff_qty;
	}
	public String getDr_cr_dt() {
		return dr_cr_dt;
	}
	public String getDr_cr_exg_rt() {
		return dr_cr_exg_rt;
	}
	public String getDr_cr_no() {
		return dr_cr_no;
	}
	public String getDue_dt() {
		return due_dt;
	}
	public String getExg_rt() {
		return exg_rt;
	}
	public String getGross_amt() {
		return gross_amt;
	}
	public String getInt_amt() {
		return int_amt;
	}
	public String getInt_rate() {
		return int_rate;
	}
	public String getInt_type() {
		return int_type;
	}
	public String getInv_dt() {
		return inv_dt;
	}
	public String getInv_no() {
		return inv_no;
	}
	public String getNet_amt() {
		return net_amt;
	}
	public String getQty() {
		return qty;
	}
	public String getRemark() {
		return remark;
	}
	public String getSale_price() {
		return sale_price;
	}
	public String getInt_cd() {
		return int_cd;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getDr_cr_doc_no() {
		return dr_cr_doc_no;
	}


	public Vector getGROSS_AMT_USD() {
		return GROSS_AMT_USD;
	}


	public Vector getTAX_STRUCT_CD() {
		return TAX_STRUCT_CD;
	}


	public String getTax_amt_inr() {
		return tax_amt_inr;
	}


	public String getDr_cr_fin_year() {
		return dr_cr_fin_year;
	}


	public void setDr_cr_fin_year(String dr_cr_fin_year) {
		this.dr_cr_fin_year = dr_cr_fin_year;
	}


	public String getDr_cr_gross_amt() {
		return dr_cr_gross_amt;
	}


	public String getDr_cr_gross_usd() {
		return dr_cr_gross_usd;
	}


	public String getDr_cr_net_amt() {
		return dr_cr_net_amt;
	}


	public String getGross_amt_usd() {
		return gross_amt_usd;
	}


	public String getTax_struc_cd() {
		return tax_struc_cd;
	}


	public void setTax_struc_cd(String tax_struc_cd) {
		this.tax_struc_cd = tax_struc_cd;
	}


	public void setDr_cr_gross_amt(String dr_cr_gross_amt) {
		this.dr_cr_gross_amt = dr_cr_gross_amt;
	}


	public void setInv_dt(String inv_dt) {
		this.inv_dt = inv_dt;
	}


	public void setActivity(String activity) {
		this.activity = activity;
	}


	public Vector getDR_CR_FIN_YEAR() {
		return DR_CR_FIN_YEAR;
	}


	public Vector getDR_CR_NO() {
		return DR_CR_NO;
	}


	public Vector getDR_CR_NO_DISP() {
		return DR_CR_NO_DISP;
	}
	public Vector getDR_CR_DOC_NO() {return DR_CR_DOC_NO;} //SB20160502
	

	public String getDr_cr_no_disp() {
		return dr_cr_no_disp;
	}


	public void setCr_dr(String cr_dr) {
		this.cr_dr = cr_dr;
	}


	public String getInt_sign() {
		return int_sign;
	}


	public void setInt_cd(String int_cd) {
		this.int_cd = int_cd;
	}


	public String getInt_rate1() {
		return int_rate1;
	}


	public void setDr_cr_dt(String dr_cr_dt) {
		this.dr_cr_dt = dr_cr_dt;
	}


	public String getInt_rate_cal() {
		return int_rate_cal;
	}


	public void setPay_cd(String pay_cd) {
		this.pay_cd = pay_cd;
	}


	public String getPay_dt() {
		return pay_dt;
	}


	public String getPay_amt() {
		return pay_amt;
	}


	public String getPay_type() {
		return pay_type;
	}


	public String getSettle_flag() {
		return settle_flag;
	}


	public Vector getPAY_CD() {
		return PAY_CD;
	}


	public Vector getPAY_DT() {
		return PAY_DT;
	}


	public Vector getPAY_AMT() {
		return PAY_AMT;
	}


	public Vector getPAY_TYPE() {
		return PAY_TYPE;
	}


	public Vector getADV_TYPE() {
		return ADV_TYPE;
	}


	public Vector getSETTLE_FLAG() {
		return SETTLE_FLAG;
	}


	public void setOperation(String operation) {
		this.operation = operation;
	}

	//Following (6) Vector Getter Methods Has Been Defined By Samik Shah On 20th January, 2011 ...
	public Vector getInv_Checked_Flag() {return inv_Checked_Flag;}
	public Vector getInv_Checked_By() {return inv_Checked_By;}
	public Vector getInv_Authorized_Flag() {return inv_Authorized_Flag;}
	public Vector getInv_Authorized_By() {return inv_Authorized_By;}
	public Vector getInv_Approved_Flag() {return inv_Approved_Flag;}
	public Vector getInv_Approved_By() {return inv_Approved_By;}

	//Following (3) Vector Getter Methods Has Been Defined By Samik Shah On 22nd January, 2011 ...
	public Vector getInv_Gross_Amt_USD() {return inv_Gross_Amt_USD;}
	public Vector getInv_Gross_Amt_INR() {return inv_Gross_Amt_INR;}
	public Vector getInv_Exchg_Rate_CD() {return inv_Exchg_Rate_CD;}

	//Following String Getter Method Has Been Defined By Samik Shah On 25th January, 2011 ...
	public String getContactPersonCd() {return contactPersonCd;}

	//Following int Getter Method Has Been Defined By Samik Shah On 25th January, 2011 ...
	public int getExchg_rate_ind() {return exchg_rate_ind;}

	//Following String Getter Method Has Been Defined By Achal Pathak On 5th February, 2011 ...
	public String getAdd_amt() {return add_amt;}
	
	//Following (2) Vector Getter Methods Has Been Defined By Samik Shah On 8th February, 2011 ...
	public Vector getDaily_Buyer_Allocation_Offspec_Rate() {return daily_Buyer_Allocation_Offspec_Rate;}
	public Vector getDaily_Buyer_Allocation_Offspec_Flag() {return daily_Buyer_Allocation_Offspec_Flag;}

	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 8th February, 2011 ...
	public String getAccepted_Offspec_Qty() {return accepted_Offspec_Qty;}
	public String getAccepted_FM_Qty() {return accepted_FM_Qty;}

	//Following (6) String Getter Methods Has Been Defined By Samik Shah On 9th February, 2011 ...
	public String getTotal_Offspec_Qty() {return total_Offspec_Qty;}
	public String getOffspec_Sales_Rate() {return offspec_Sales_Rate;}
	public String getOffspec_Flag() {return offspec_Flag;}
	public String getOffspec_Amt_USD() {return offspec_Amt_USD;}
	public String getTotal_Gas_Delivered() {return total_Gas_Delivered;}
	public String getGas_Delivered_Amt_USD() {return gas_Delivered_Amt_USD;}

	//Following Vector Getter Method Has Been Defined By Samik Shah On 10th February, 2011 ...
	public Vector getInv_Offspec_Rate() {return inv_Offspec_Rate;}

	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 15th April, 2011 ...
	public String getContact_addr_flag() {return contact_addr_flag;}
	public String getSn_ref_no() {return sn_ref_no;}

	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 16th April, 2011 ...
	public String getContact_Customer_GVAT_NO() {return contact_Customer_GVAT_NO;}
	public String getContact_Customer_GVAT_DT() {return contact_Customer_GVAT_DT;}


	//Following Vector Getter Method Has Been Introduced By Samik Shah On 18th April, 2011 ...
	public Vector getInvoice_SN_Ref_No() {return invoice_SN_Ref_No;}

	//Following (3) String Getter Methods Has Been Defined By Samik Shah On 12th May, 2011 ...
	public String getBoe_no() {return boe_no;}
	public String getBoe_dt() {return boe_dt;}
	public String getRemark_3() {return remark_3;}

	//Following String Getter Method Has Been Introduced By Samik Shah On 13th May, 2011 ...
	public String getCustomer_Invoice_Tax_Amt_INR() {return customer_Invoice_Tax_Amt_INR;}

	//Following String Getter Method Has Been Defined By Samik Shah On 4th July, 2011 ...
	public String getLiability_exist_flag() {return liability_exist_flag;}

	//Following (4) Vector Getter Methods Have Been Introduced By Samik Shah On 3rd September, 2011 ...
	public Vector getVSTAT_CD() {return vSTAT_CD;}
	public Vector getVSTAT_NM() {return vSTAT_NM;}
	public Vector getVSTAT_NO() {return vSTAT_NO;}
	public Vector getVSTAT_EFF_DT() {return vSTAT_EFF_DT;}
	
	public Vector getVinvoice_total_qty_mmbtu() {return invoice_total_qty_mmbtu;} //SB20111207
	public Vector getVinvoice_temp_Tcq_mmbtu() {return invoice_temp_Tcq_mmbtu;} //SB20111207


	public Vector getInv_net_Amt_INR() {
		return inv_net_Amt_INR;
	}


	public Vector getVCUSTOMER_CD() {
		return VCUSTOMER_CD;
	}


	public Vector getVcust_abr() {
		return Vcust_abr;
	}


	public Vector getVinv_qty() {
		return Vinv_qty;
	}


	public Vector getVdue_dt() {
		return Vdue_dt;
	}


	public Vector getVinv_dt() {
		return Vinv_dt;
	}


	public Vector getVcont_type() {
		return Vcont_type;
	}


	public Vector getVremarks() {
		return Vremarks;
	}


	public Vector getVsn_no() {
		return Vsn_no;
	}


	public Vector getVsn_no_rev() {
		return Vsn_no_rev;
	}


	public Vector getVcargo_ref_no() {
		return Vcargo_ref_no;
	}


	public Vector getVfgsa_no() {
		return Vfgsa_no;
	}


	public void setCont_type(String cont_type) {
		this.cont_type = cont_type;
	}


	public Vector getVinv_flag() {
		return Vinv_flag;
	}


	public String getSeq_no() {
		return seq_no;
	}

	public Vector getTCQ() {
		return TCQ;
	}

	public void setAllocatedQty(Vector allocatedQty) {
		AllocatedQty = allocatedQty;
	}

	public Vector getAllocatedQty() {
		return AllocatedQty;
	}

	public Vector getRemainingQty() {
		return RemainingQty;
	}

	public String getCutoffDate() {
		return cutoffDate;
	}

	public void setAttachment1flag(String attachment1flag) {
		this.attachment1flag = attachment1flag;
	}

	public void setTcqflag(String tcqflag) {
		this.tcqflag = tcqflag;
	}

	public Vector getCuttDate() {
		return cuttDate;
	}

	public String getModifyadjremark() {
		return modifyadjremark;
	}

	public void setModifyadjremark(String modifyadjremark) {
		this.modifyadjremark = modifyadjremark;
	}


	public String getADJUST_AMT() {
		return ADJUST_AMT;
	}


	public void setADJUST_AMT(String adjust_amt) {
		ADJUST_AMT = adjust_amt;
	}


	public String getADJUST_CUR() {
		return ADJUST_CUR;
	}


	public void setADJUST_CUR(String adjust_cur) {
		ADJUST_CUR = adjust_cur;
	}


	public String getADJUST_FLAG_MST() {
		return ADJUST_FLAG_MST;
	}


	public void setADJUST_FLAG_MST(String adjust_flag_mst) {
		ADJUST_FLAG_MST = adjust_flag_mst;
	}


	public String getDISCOUNT_FLAG_MST() {
		return DISCOUNT_FLAG_MST;
	}


	public void setDISCOUNT_FLAG_MST(String discount_flag_mst) {
		DISCOUNT_FLAG_MST = discount_flag_mst;
	}


	public String getDISCOUNT_PRICE() {
		return DISCOUNT_PRICE;
	}


	public void setDISCOUNT_PRICE(String discount_price) {
		DISCOUNT_PRICE = discount_price;
	}


	public String getTARIFF_FLAG_MST() {
		return TARIFF_FLAG_MST;
	}


	public void setTARIFF_FLAG_MST(String tariff_flag_mst) {
		TARIFF_FLAG_MST = tariff_flag_mst;
	}


	public String getTARIFF_INR() {
		return TARIFF_INR;
	}


	public void setTARIFF_INR(String tariff_inr) {
		TARIFF_INR = tariff_inr;
	}


	

	public String getAdjusttotalavailbalMst() {
		return AdjusttotalavailbalMst;
	}


	public void setAdjusttotalavailbalMst(String adjusttotalavailbalMst) {
		AdjusttotalavailbalMst = adjusttotalavailbalMst;
	}


	public String getInv_cur_flag() {
		return inv_cur_flag;
	}


	public void setInv_cur_flag(String inv_cur_flag) {
		this.inv_cur_flag = inv_cur_flag;
	}


	public Vector getInvoice_Mapping_Id() {
		return invoice_Mapping_Id;
	}


	public void setInvoice_Mapping_Id(Vector invoice_Mapping_Id) {
		this.invoice_Mapping_Id = invoice_Mapping_Id;
	}


	public String getCustomer_inv_mapping_id() {
		return customer_inv_mapping_id;
	}


	public void setCustomer_inv_mapping_id(String customer_inv_mapping_id) {
		this.customer_inv_mapping_id = customer_inv_mapping_id;
	}


	public Vector getDR_CR_MAPPING_ID() {
		return DR_CR_MAPPING_ID;
	} 


	public void setDR_CR_MAPPING_ID(Vector dr_cr_mapping_id) {
		DR_CR_MAPPING_ID = dr_cr_mapping_id;
	}


	public String getDR_CR_MAPPING_ID_STR() {
		return DR_CR_MAPPING_ID_STR;
	}


	public void setDR_CR_MAPPING_ID_STR(String dr_cr_mapping_id_str) {
		DR_CR_MAPPING_ID_STR = dr_cr_mapping_id_str;
	}


	public String getInvOldFinancialYear() {
		return invOldFinancialYear;
	}


	public void setInvOldFinancialYear(String invOldFinancialYear) {
		this.invOldFinancialYear = invOldFinancialYear;
	}


	public String getAdvance_inv_no() {
		return advance_inv_no;
	}


	public void setAdvance_inv_no(String advance_inv_no) {
		this.advance_inv_no = advance_inv_no;
	}


	public String getCustomer_ADV_INV_DT() {
		return customer_ADV_INV_DT;
	}


	public void setCustomer_ADV_INV_DT(String customer_ADV_INV_DT) {
		this.customer_ADV_INV_DT = customer_ADV_INV_DT;
	}


	public String getCustomer_ADV_INV_NO() {
		return customer_ADV_INV_NO;
	}


	public void setCustomer_ADV_INV_NO(String customer_ADV_INV_NO) {
		this.customer_ADV_INV_NO = customer_ADV_INV_NO;
	}


	public String getAdvance_inv_dt() {
		return advance_inv_dt;
	}


	public void setAdvance_inv_dt(String advance_inv_dt) {
		this.advance_inv_dt = advance_inv_dt;
	}

	public String getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(String maxYear) {
		this.maxYear = maxYear;
	}

	public Vector getDelFlag() {
		return DelFlag;
	}

	public void setDelFlag(Vector delFlag) {
		DelFlag = delFlag;
	}

	public Vector getInvoice_Pending_approval() {
		return Invoice_Pending_approval;
	}

	public void setInvoice_Pending_approval(Vector invoice_Pending_approval) {
		Invoice_Pending_approval = invoice_Pending_approval;
	}

	public void setContact_addr_flag(String contact_addr_flag) {
		this.contact_addr_flag = contact_addr_flag;
	}

	public String getTax_struct_cd() {
		return tax_struct_cd;
	}

	public void setTax_struct_cd(String tax_struct_cd) {
		this.tax_struct_cd = tax_struct_cd;
	}

	public double getCustomer_Invoice_Tax_Amt_INR1() {
		return customer_Invoice_Tax_Amt_INR1;
	}

	public void setCustomer_Invoice_Tax_Amt_INR1(double customer_Invoice_Tax_Amt_INR1) {
		this.customer_Invoice_Tax_Amt_INR1 = customer_Invoice_Tax_Amt_INR1;
	}

	public Vector getInvoice_pre_aprv() {
		return invoice_pre_aprv;
	}

	public void setInvoice_pre_aprv(Vector invoice_pre_aprv) {
		this.invoice_pre_aprv = invoice_pre_aprv;
	}

	public String getCustomer_Invoice_DT1() {
		return customer_Invoice_DT1;
	}

	public void setCustomer_Invoice_DT1(String customer_Invoice_DT1) {
		this.customer_Invoice_DT1 = customer_Invoice_DT1;
	}

	public Vector getCustomer_invoice_pdf_path() {
		return customer_invoice_pdf_path;
	}

	public void setCustomer_invoice_pdf_path(Vector customer_invoice_pdf_path) {
		this.customer_invoice_pdf_path = customer_invoice_pdf_path;
	}

	public Vector getCustomer_invoice_pdf_path_flag() {
		return customer_invoice_pdf_path_flag;
	}

	public void setCustomer_invoice_pdf_path_flag(
			Vector customer_invoice_pdf_path_flag) {
		this.customer_invoice_pdf_path_flag = customer_invoice_pdf_path_flag;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Vector getCustomer_invoice_pdf_lock_flag() {
		return customer_invoice_pdf_lock_flag;
	}

	public void setCustomer_invoice_pdf_lock_flag(
			Vector customer_invoice_pdf_lock_flag) {
		this.customer_invoice_pdf_lock_flag = customer_invoice_pdf_lock_flag;
	}

	public String getParticular_date() {
		return particular_date;
	}

	public String getRefresh_flag() {
		return refresh_flag;
	}

	public void setRefresh_flag(String refresh_flag) {
		this.refresh_flag = refresh_flag;
	}

	public Vector getPdf_color() {
		return pdf_color;
	}

	public void setPdf_color(Vector pdf_color) {
		this.pdf_color = pdf_color;
	}

	public Vector getInvoice_inv_date() {
		return invoice_inv_date;
	}

	public void setInvoice_inv_date(Vector invoice_inv_date) {
		this.invoice_inv_date = invoice_inv_date;
	}

	public String getTAX_ADV_ADJUST_AMT() {
		return TAX_ADV_ADJUST_AMT;
	}

	public void setTAX_ADV_ADJUST_AMT(String tax_adv_adjust_amt) {
		TAX_ADV_ADJUST_AMT = tax_adv_adjust_amt;
	}

	public String getTAX_ADV_ADJUST_CUR() {
		return TAX_ADV_ADJUST_CUR;
	}

	public void setTAX_ADV_ADJUST_CUR(String tax_adv_adjust_cur) {
		TAX_ADV_ADJUST_CUR = tax_adv_adjust_cur;
	}

	public String getTAX_ADV_ADJUST_FLAG_MST() {
		return TAX_ADV_ADJUST_FLAG_MST;
	}

	public void setTAX_ADV_ADJUST_FLAG_MST(String tax_adv_adjust_flag_mst) {
		TAX_ADV_ADJUST_FLAG_MST = tax_adv_adjust_flag_mst;
	}

	public String getInvoice_total_tax() {
		return invoice_total_tax;
	}

	public void setInvoice_total_tax(String invoice_total_tax) {
		this.invoice_total_tax = invoice_total_tax;
	}

	public Vector getInvoice_tax_adj() {
		return invoice_tax_adj;
	}

	public void setInvoice_tax_adj(Vector invoice_tax_adj) {
		this.invoice_tax_adj = invoice_tax_adj;
	}

	public String getAdjust_total_avail_bal_tax() {
		return Adjust_total_avail_bal_tax;
	}

	public void setAdjust_total_avail_bal_tax(String adjust_total_avail_bal_tax) {
		Adjust_total_avail_bal_tax = adjust_total_avail_bal_tax;
	}

	public String getMODIFY_TAX_ADV_ADJUSTMENT() {
		return MODIFY_TAX_ADV_ADJUSTMENT;
	}

	public void setMODIFY_TAX_ADV_ADJUSTMENT(String modify_tax_adv_adjustment) {
		MODIFY_TAX_ADV_ADJUSTMENT = modify_tax_adv_adjustment;
	}

	public String getMODIFY_TAX_ADV_CURR() {
		return MODIFY_TAX_ADV_CURR;
	}

	public void setMODIFY_TAX_ADV_CURR(String modify_tax_adv_curr) {
		MODIFY_TAX_ADV_CURR = modify_tax_adv_curr;
	}

	public String getMODIFY_TAX_INV_AMT_INR() {
		return MODIFY_TAX_INV_AMT_INR;
	}

	public void setMODIFY_TAX_INV_AMT_INR(String modify_tax_inv_amt_inr) {
		MODIFY_TAX_INV_AMT_INR = modify_tax_inv_amt_inr;
	}

	public Vector getTax_adj_flag_pdf() {
		return tax_adj_flag_pdf;
	}

	public void setTax_adj_flag_pdf(Vector tax_adj_flag_pdf) {
		this.tax_adj_flag_pdf = tax_adj_flag_pdf;
	}

	public String getMODIFY_TAX_ADV_FLAG() {
		return MODIFY_TAX_ADV_FLAG;
	}

	public void setMODIFY_TAX_ADV_FLAG(String mODIFY_TAX_ADV_FLAG) {
		MODIFY_TAX_ADV_FLAG = mODIFY_TAX_ADV_FLAG;
	}


	public Vector getRpt_trk_sn_customer_cd() {
		return rpt_trk_sn_customer_cd;
	}


	public void setRpt_trk_sn_customer_cd(Vector rpt_trk_sn_customer_cd) {
		this.rpt_trk_sn_customer_cd = rpt_trk_sn_customer_cd;
	}


	public Vector getRpt_trk_sn_customer_nm() {
		return rpt_trk_sn_customer_nm;
	}


	public void setRpt_trk_sn_customer_nm(Vector rpt_trk_sn_customer_nm) {
		this.rpt_trk_sn_customer_nm = rpt_trk_sn_customer_nm;
	}


	public Vector getRpt_trk_sn_inv_no() {
		return rpt_trk_sn_inv_no;
	}


	public void setRpt_trk_sn_inv_no(Vector rpt_trk_sn_inv_no) {
		this.rpt_trk_sn_inv_no = rpt_trk_sn_inv_no;
	}


	public Vector getRpt_trk_sn_inv_dt() {
		return rpt_trk_sn_inv_dt;
	}


	public void setRpt_trk_sn_inv_dt(Vector rpt_trk_sn_inv_dt) {
		this.rpt_trk_sn_inv_dt = rpt_trk_sn_inv_dt;
	}


	public Vector getRpt_trk_sn_inv_qty() {
		return rpt_trk_sn_inv_qty;
	}


	public void setRpt_trk_sn_inv_qty(Vector rpt_trk_sn_inv_qty) {
		this.rpt_trk_sn_inv_qty = rpt_trk_sn_inv_qty;
	}


	public Vector getRpt_trk_sn_inv_amt() {
		return rpt_trk_sn_inv_amt;
	}


	public void setRpt_trk_sn_inv_amt(Vector rpt_trk_sn_inv_amt) {
		this.rpt_trk_sn_inv_amt = rpt_trk_sn_inv_amt;
	}


	public Vector getRpt_trk_sn_due_dt() {
		return rpt_trk_sn_due_dt;
	}


	public void setRpt_trk_sn_due_dt(Vector rpt_trk_sn_due_dt) {
		this.rpt_trk_sn_due_dt = rpt_trk_sn_due_dt;
	}


	public Vector getRpt_trk_sn_boe_no() {
		return rpt_trk_sn_boe_no;
	}


	public void setRpt_trk_sn_boe_no(Vector rpt_trk_sn_boe_no) {
		this.rpt_trk_sn_boe_no = rpt_trk_sn_boe_no;
	}


	public Vector getRpt_trk_sn_contrct_no() {
		return rpt_trk_sn_contrct_no;
	}


	public void setRpt_trk_sn_contrct_no(Vector rpt_trk_sn_contrct_no) {
		this.rpt_trk_sn_contrct_no = rpt_trk_sn_contrct_no;
	}


	public Vector getRpt_trk_re_customer_cd() {
		return rpt_trk_re_customer_cd;
	}


	public void setRpt_trk_re_customer_cd(Vector rpt_trk_re_customer_cd) {
		this.rpt_trk_re_customer_cd = rpt_trk_re_customer_cd;
	}


	public Vector getRpt_trk_re_customer_nm() {
		return rpt_trk_re_customer_nm;
	}


	public void setRpt_trk_re_customer_nm(Vector rpt_trk_re_customer_nm) {
		this.rpt_trk_re_customer_nm = rpt_trk_re_customer_nm;
	}


	public Vector getRpt_trk_re_inv_no() {
		return rpt_trk_re_inv_no;
	}


	public void setRpt_trk_re_inv_no(Vector rpt_trk_re_inv_no) {
		this.rpt_trk_re_inv_no = rpt_trk_re_inv_no;
	}


	public Vector getRpt_trk_re_inv_dt() {
		return rpt_trk_re_inv_dt;
	}


	public void setRpt_trk_re_inv_dt(Vector rpt_trk_re_inv_dt) {
		this.rpt_trk_re_inv_dt = rpt_trk_re_inv_dt;
	}


	public Vector getRpt_trk_re_inv_qty() {
		return rpt_trk_re_inv_qty;
	}


	public void setRpt_trk_re_inv_qty(Vector rpt_trk_re_inv_qty) {
		this.rpt_trk_re_inv_qty = rpt_trk_re_inv_qty;
	}


	public Vector getRpt_trk_re_inv_amt() {
		return rpt_trk_re_inv_amt;
	}


	public void setRpt_trk_re_inv_amt(Vector rpt_trk_re_inv_amt) {
		this.rpt_trk_re_inv_amt = rpt_trk_re_inv_amt;
	}


	public Vector getRpt_trk_re_due_dt() {
		return rpt_trk_re_due_dt;
	}


	public void setRpt_trk_re_due_dt(Vector rpt_trk_re_due_dt) {
		this.rpt_trk_re_due_dt = rpt_trk_re_due_dt;
	}


	public Vector getRpt_trk_re_boe_no() {
		return rpt_trk_re_boe_no;
	}


	public void setRpt_trk_re_boe_no(Vector rpt_trk_re_boe_no) {
		this.rpt_trk_re_boe_no = rpt_trk_re_boe_no;
	}


	public Vector getRpt_trk_re_contrct_no() {
		return rpt_trk_re_contrct_no;
	}


	public void setRpt_trk_re_contrct_no(Vector rpt_trk_re_contrct_no) {
		this.rpt_trk_re_contrct_no = rpt_trk_re_contrct_no;
	}


	public Vector getRpt_trk_lt_customer_cd() {
		return rpt_trk_lt_customer_cd;
	}


	public void setRpt_trk_lt_customer_cd(Vector rpt_trk_lt_customer_cd) {
		this.rpt_trk_lt_customer_cd = rpt_trk_lt_customer_cd;
	}


	public Vector getRpt_trk_lt_customer_nm() {
		return rpt_trk_lt_customer_nm;
	}


	public void setRpt_trk_lt_customer_nm(Vector rpt_trk_lt_customer_nm) {
		this.rpt_trk_lt_customer_nm = rpt_trk_lt_customer_nm;
	}


	public Vector getRpt_trk_lt_inv_no() {
		return rpt_trk_lt_inv_no;
	}


	public void setRpt_trk_lt_inv_no(Vector rpt_trk_lt_inv_no) {
		this.rpt_trk_lt_inv_no = rpt_trk_lt_inv_no;
	}


	public Vector getRpt_trk_lt_inv_dt() {
		return rpt_trk_lt_inv_dt;
	}


	public void setRpt_trk_lt_inv_dt(Vector rpt_trk_lt_inv_dt) {
		this.rpt_trk_lt_inv_dt = rpt_trk_lt_inv_dt;
	}


	public Vector getRpt_trk_lt_inv_qty() {
		return rpt_trk_lt_inv_qty;
	}


	public void setRpt_trk_lt_inv_qty(Vector rpt_trk_lt_inv_qty) {
		this.rpt_trk_lt_inv_qty = rpt_trk_lt_inv_qty;
	}


	public Vector getRpt_trk_lt_inv_amt() {
		return rpt_trk_lt_inv_amt;
	}


	public void setRpt_trk_lt_inv_amt(Vector rpt_trk_lt_inv_amt) {
		this.rpt_trk_lt_inv_amt = rpt_trk_lt_inv_amt;
	}


	public Vector getRpt_trk_lt_due_dt() {
		return rpt_trk_lt_due_dt;
	}


	public void setRpt_trk_lt_due_dt(Vector rpt_trk_lt_due_dt) {
		this.rpt_trk_lt_due_dt = rpt_trk_lt_due_dt;
	}


	public Vector getRpt_trk_lt_boe_no() {
		return rpt_trk_lt_boe_no;
	}


	public void setRpt_trk_lt_boe_no(Vector rpt_trk_lt_boe_no) {
		this.rpt_trk_lt_boe_no = rpt_trk_lt_boe_no;
	}


	public Vector getRpt_trk_lt_contrct_no() {
		return rpt_trk_lt_contrct_no;
	}


	public void setRpt_trk_lt_contrct_no(Vector rpt_trk_lt_contrct_no) {
		this.rpt_trk_lt_contrct_no = rpt_trk_lt_contrct_no;
	}


	public String getContact_Suppl_PAN_NO() {
		return contact_Suppl_PAN_NO;
	}


	public void setContact_Suppl_PAN_NO(String contact_Suppl_PAN_NO) {
		this.contact_Suppl_PAN_NO = contact_Suppl_PAN_NO;
	}


	public String getContact_Suppl_PAN_DT() {
		return contact_Suppl_PAN_DT;
	}


	public void setContact_Suppl_PAN_DT(String contact_Suppl_PAN_DT) {
		this.contact_Suppl_PAN_DT = contact_Suppl_PAN_DT;
	}


	public Vector getVdrcrflag() {
		return Vdrcrflag;
	}


	public void setVdrcrflag(Vector vdrcrflag) {
		Vdrcrflag = vdrcrflag;
	}


	public void setTotal_Invoice_Qty(String total_Invoice_Qty) {
		this.total_Invoice_Qty = total_Invoice_Qty;
	}
	
/////SB20160505: For SBC & KKC //////////////////
	String SBC_Flag_Service="N";
	String SBC_AMT_Service="0";
	String SBC_CUR_Service="INR";
	
	String KKC_Flag_Service="N";
	String KKC_AMT_Service="0";
	String KKC_CUR_Service="INR";
	
	String MODIFY_SBC_ADV_ADJUSTMENT="0";
	String Adjust_total_avail_bal_sbc="0";
	String MODIFY_SBC_ADV_CURR="INR";
	String MODIFY_SBC_INV_AMT_INR="0";
	String MODIFY_SBC_ADV_FLAG="N";
	
	String MODIFY_KKC_ADV_ADJUSTMENT="0";
	String Adjust_total_avail_bal_kkc="0";
	String MODIFY_KKC_ADV_CURR="INR";
	String MODIFY_KKC_INV_AMT_INR="0";
	String MODIFY_KKC_ADV_FLAG="N";
	//For PDf File Name, etc...
	String invoice_title="ORIGINAL"; //SB20160527
	Vector VPDF_File_Nm=new Vector(); //SB20160527
	Vector VPDF_Inv_Dt=new Vector(); //SB20160530
	public Vector getVPDF_File_Nm() {return VPDF_File_Nm;}//SB20160527
	public Vector getVPDF_Inv_Dt() {return VPDF_Inv_Dt;}//SB20160527
	//////////
	Vector VInv_FY=new Vector();		//SB20160527
	Vector VInv_Dt_FY=new Vector();		//SB20160527
	public Vector getVInv_FY() {return VInv_FY;}//SB20160527
	public Vector getVInv_Dt_FY() {return VInv_Dt_FY;}//SB20160527
	String fy ="";
	public void setFY(String FY) {this.fy = FY;} //SB20160527
	
	Vector VCrInvSeqNo=new Vector();		//SB20160601
	Vector VCrContType=new Vector();		//SB20160601
	Vector VCrFY=new Vector();		//SB20160601
	
	Vector VDrCrDocNo=new Vector();		//SB20160526
	public Vector getVDrCrDocNo() {return VDrCrDocNo;}//SB20160526
	Vector VDrCrFlag=new Vector();		//SB20160526
	public Vector getVDrCrFlag() {return VDrCrFlag;}//SB20160526 
	Vector VDrCrDt=new Vector();		//SB20160601
	public Vector getVDrCrDt() {return VDrCrDt;}//SB20160601
	Vector VDrCrAprvBy=new Vector();		//SB20160601
	public Vector getVDrCrAprvBy() {return VDrCrAprvBy;}//SB20160602
	Vector VDrCrAprvDt=new Vector();		//SB20160601
	public Vector getVDrCrAprvDt() {return VDrCrAprvDt;}//SB20160602
	Vector VBalanceQty_mmbtu=new Vector();		//SB20160603 
	public Vector getVBalanceQty_mmbtu() {return VBalanceQty_mmbtu;}//SB20160603
	Vector VInv_AllocatedQty_mmbtu=new Vector();		//SB20160604 
	public Vector getVInv_AllocatedQty_mmbtu() {return VInv_AllocatedQty_mmbtu;}//SB20160604
	Vector VInv_Mapping_Id=new Vector();		//SB20160604 
	public Vector getVInv_Mapping_Id() {return VInv_Mapping_Id;}//SB20160604
	
	public String getSBC_Flag_Service() {return SBC_Flag_Service;}
	public String getSBC_AMT_Service() {return SBC_AMT_Service;}
	public String getSBC_CUR_Service() {return SBC_CUR_Service;}
	
	public String getMODIFY_SBC_ADV_ADJUSTMENT() {return MODIFY_SBC_ADV_ADJUSTMENT;}
	public String getAdjust_total_avail_bal_sbc() {return Adjust_total_avail_bal_sbc;}
	public String getMODIFY_SBC_ADV_CURR() {return MODIFY_SBC_ADV_CURR;}
	public String getMODIFY_SBC_INV_AMT_INR() {return MODIFY_SBC_INV_AMT_INR;}
	public String getMODIFY_SBC_ADV_FLAG() {return MODIFY_SBC_ADV_FLAG;}
/////////////////////////////////////////////

	public Map getInvoice_view_pdf() {
		return invoice_view_pdf;
	}
	public Map getInvoice_signded_pdf() {
		return invoice_signded_pdf;
	}


	public String getKKC_AMT_Service() {
		return KKC_AMT_Service;
	}


	public String getKKC_CUR_Service() {
		return KKC_CUR_Service;
	}


	public String getKKC_Flag_Service() {
		return KKC_Flag_Service;
	}


	public String getMODIFY_KKC_ADV_ADJUSTMENT() {
		return MODIFY_KKC_ADV_ADJUSTMENT;
	}


	public String getMODIFY_KKC_ADV_CURR() {
		return MODIFY_KKC_ADV_CURR;
	}


	public String getMODIFY_KKC_ADV_FLAG() {
		return MODIFY_KKC_ADV_FLAG;
	}


	public String getMODIFY_KKC_INV_AMT_INR() {
		return MODIFY_KKC_INV_AMT_INR;
	}


	public String getAdjust_total_avail_bal_kkc() {
		return Adjust_total_avail_bal_kkc;
	}
/////SB20160617: For DR-Cr Approval Process/////////////////
	public Vector DR_CR_APRV_BY=new Vector();  //SB20160617
	public Vector DR_CR_APRV_DT=new Vector();  //SB20160617
	public Vector DR_CR_DTL_REM=new Vector();  //SB20160617
	
	public Vector getDR_CR_APRV_BY() {return DR_CR_APRV_BY;}  //SB20160617
	public Vector getDR_CR_APRV_DT() {return DR_CR_APRV_DT;}  //SB20160617
	
	Vector VCustContPlantStDt=new Vector(); //SB20160527
	Vector VCustContPlantStDt_Flag=new Vector(); //SB20160530
	Vector VCustContPlantCrDrAprv_Flag=new Vector(); //SB20160623
	
	public Vector getVCustContPlantStDt() {return VCustContPlantStDt;}//SB20160527
	public Vector getVCustContPlantStDt_Flag() {return VCustContPlantStDt_Flag;}//SB20160623
	public Vector getVCustContPlantCrDrAprv_Flag() {return VCustContPlantCrDrAprv_Flag;}//SB20160623
	/////////////////////////////////////////////////////////////


	public Vector getDR_CR_DTL_REM() {
		return DR_CR_DTL_REM;
	}


	public Vector getCONTRACT_TYPE_DR_CR() {
		return CONTRACT_TYPE_DR_CR;
	}


	public void setCONTRACT_TYPE_DR_CR(Vector contract_type_dr_cr) {
		CONTRACT_TYPE_DR_CR = contract_type_dr_cr;
	}


	public Vector getDR_CR_MAPPING_ID_DR_CR() {
		return DR_CR_MAPPING_ID_DR_CR;
	}


	public void setDR_CR_MAPPING_ID_DR_CR(Vector dr_cr_mapping_id_dr_cr) {
		DR_CR_MAPPING_ID_DR_CR = dr_cr_mapping_id_dr_cr;
	}


	public Vector getEXCHG_RATE_VALUE_DR_CR() {
		return EXCHG_RATE_VALUE_DR_CR;
	}


	public void setEXCHG_RATE_VALUE_DR_CR(Vector exchg_rate_value_dr_cr) {
		EXCHG_RATE_VALUE_DR_CR = exchg_rate_value_dr_cr;
	}


	public Vector getGROSS_AMT_INR_DR_CR() {
		return GROSS_AMT_INR_DR_CR;
	}


	public void setGROSS_AMT_INR_DR_CR(Vector gross_amt_inr_dr_cr) {
		GROSS_AMT_INR_DR_CR = gross_amt_inr_dr_cr;
	}


	public Vector getGROSS_AMT_USD_DR_CR() {
		return GROSS_AMT_USD_DR_CR;
	}


	public void setGROSS_AMT_USD_DR_CR(Vector gross_amt_usd_dr_cr) {
		GROSS_AMT_USD_DR_CR = gross_amt_usd_dr_cr;
	}


	public Vector getHLPL_INV_SEQ_NO_DR_CR() {
		return HLPL_INV_SEQ_NO_DR_CR;
	}


	public void setHLPL_INV_SEQ_NO_DR_CR(Vector hlpl_inv_seq_no_dr_cr) {
		HLPL_INV_SEQ_NO_DR_CR = hlpl_inv_seq_no_dr_cr;
	}


	public Vector getPERIOD_END_DT_DR_CR() {
		return PERIOD_END_DT_DR_CR;
	}


	public void setPERIOD_END_DT_DR_CR(Vector period_end_dt_dr_cr) {
		PERIOD_END_DT_DR_CR = period_end_dt_dr_cr;
	}


	public Vector getPERIOD_START_DT_DR_CR() {
		return PERIOD_START_DT_DR_CR;
	}


	public void setPERIOD_START_DT_DR_CR(Vector period_start_dt_dr_cr) {
		PERIOD_START_DT_DR_CR = period_start_dt_dr_cr;
	}


	public Vector getPLANT_SEQ_NO_DR_CR() {
		return PLANT_SEQ_NO_DR_CR;
	}


	public void setPLANT_SEQ_NO_DR_CR(Vector plant_seq_no_dr_cr) {
		PLANT_SEQ_NO_DR_CR = plant_seq_no_dr_cr;
	}


	public Vector getREMARK_DR_CR() {
		return REMARK_DR_CR;
	}


	public void setREMARK_DR_CR(Vector remark_dr_cr) {
		REMARK_DR_CR = remark_dr_cr;
	}


	public Vector getSN_NO_DR_CR() {
		return SN_NO_DR_CR;
	}


	public void setSN_NO_DR_CR(Vector sn_no_dr_cr) {
		SN_NO_DR_CR = sn_no_dr_cr;
	}


	public Vector getSN_REV_NO_DR_CR() {
		return SN_REV_NO_DR_CR;
	}


	public void setSN_REV_NO_DR_CR(Vector sn_rev_no_dr_cr) {
		SN_REV_NO_DR_CR = sn_rev_no_dr_cr;
	}


	public Vector getTAX_AMT_INR_DR_CR() {
		return TAX_AMT_INR_DR_CR;
	}


	public void setTAX_AMT_INR_DR_CR(Vector tax_amt_inr_dr_cr) {
		TAX_AMT_INR_DR_CR = tax_amt_inr_dr_cr;
	}


	public Vector getTAX_STRUCT_CD_DR_CR() {
		return TAX_STRUCT_CD_DR_CR;
	}


	public void setTAX_STRUCT_CD_DR_CR(Vector tax_struct_cd_dr_cr) {
		TAX_STRUCT_CD_DR_CR = tax_struct_cd_dr_cr;
	}


	public Vector getTOTAL_QTY_DR_CR() {
		return TOTAL_QTY_DR_CR;
	}


	public void setTOTAL_QTY_DR_CR(Vector total_qty_dr_cr) {
		TOTAL_QTY_DR_CR = total_qty_dr_cr;
	}


	public Vector getSALE_PRICE_DR_CR() {
		return SALE_PRICE_DR_CR;
	}


	public Vector getNET_AMT_INR_DR_CR() {
		return NET_AMT_INR_DR_CR;
	}


	public Vector getINVOICE_DT_DR_CR() {
		return INVOICE_DT_DR_CR;
	}


	public Vector getINVOICE_NO_DR_CR() {
		return INVOICE_NO_DR_CR;
	}


	public Vector getFGSA_REV_NO_DR_CR() {
		return FGSA_REV_NO_DR_CR;
	}


	public void setFGSA_REV_NO_DR_CR(Vector fgsa_rev_no_dr_cr) {
		FGSA_REV_NO_DR_CR = fgsa_rev_no_dr_cr;
	}


	public Vector getFGSA_NO_DR_CR() {
		return FGSA_NO_DR_CR;
	}


	public Vector getDUE_DT_DR_CR() {
		return DUE_DT_DR_CR;
	}


	public Vector getFINANCIAL_YEAR_DR_CR() {
		return FINANCIAL_YEAR_DR_CR;
	}
	public String getCst_tin_no() {
		return cst_tin_no;
	}


	public void setCst_tin_no(String cst_tin_no) {
		this.cst_tin_no = cst_tin_no;
	}


	public String getGst_tin_no() {
		return gst_tin_no;
	}


	public void setGst_tin_no(String gst_tin_no) {
		this.gst_tin_no = gst_tin_no;
	}


	public String getPan_no() {
		return pan_no;
	}


	public Vector getInvoice_period_date() {
		return invoice_period_date;
	}


	public String getEnd_dt() {
		return end_dt;
	}


	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}


	public void setSt_dt(String st_dt) {
		this.st_dt = st_dt;
	}


	public Vector getInvoice_period_date1() {
		return Invoice_period_date1;
	}


	public void setCr_dr_yr(String cr_dr_yr) {
		this.cr_dr_yr = cr_dr_yr;
	}


	public void setDr_cr_year(String dr_cr_year) {
		this.dr_cr_year = dr_cr_year;
	}


	public Vector getREMARK_2_DR_CR() {
		return REMARK_2_DR_CR;
	}


	public void setREMARK_2_DR_CR(Vector remark_2_dr_cr) {
		REMARK_2_DR_CR = remark_2_dr_cr;
	}


	public Vector getREMARK_3_DR_CR() {
		return REMARK_3_DR_CR;
	}


	public void setREMARK_3_DR_CR(Vector remark_3_dr_cr) {
		REMARK_3_DR_CR = remark_3_dr_cr;
	}


	public Vector getREMARK_1_DR_CR() {
		return REMARK_1_DR_CR;
	}


	public Vector getFlag_dr_cr() {
		return flag_dr_cr;
	}


	public Vector getContact_Customer_Person_City1() {
		return contact_Customer_Person_City1;
	}


	public void setContact_Customer_Person_City1(
			Vector contact_Customer_Person_City1) {
		this.contact_Customer_Person_City1 = contact_Customer_Person_City1;
	}


	public Vector getContact_Customer_Person_Pin1() {
		return contact_Customer_Person_Pin1;
	}


	public void setContact_Customer_Person_Pin1(Vector contact_Customer_Person_Pin1) {
		this.contact_Customer_Person_Pin1 = contact_Customer_Person_Pin1;
	}


	public Vector getContact_Customer_Person_Address1() {
		return contact_Customer_Person_Address1;
	}


	public void setCr_dr1(String cr_dr1) {
		this.cr_dr1 = cr_dr1;
	}


	public Vector getVctype() {
		return Vctype;
	}


	public void setDrcrgrossamt(String drcrgrossamt) {
		this.drcrgrossamt = drcrgrossamt;
	}


	public String getApplicable_qty1() {
		return applicable_qty1;
	}


	public void setApplicable_qty1(String applicable_qty1) {
		this.applicable_qty1 = applicable_qty1;
	}


	public String getCr_dr2() {
		return cr_dr2;
	}


	public void setCr_dr2(String cr_dr2) {
		this.cr_dr2 = cr_dr2;
	}


	public String getCriteria1() {
		return criteria1;
	}


	public void setCriteria1(String criteria1) {
		this.criteria1 = criteria1;
	}


	public String getDr_cr_dt1() {
		return dr_cr_dt1;
	}


	public void setDr_cr_dt1(String dr_cr_dt1) {
		this.dr_cr_dt1 = dr_cr_dt1;
	}


	public String getDr_cr_exg_rt1() {
		return dr_cr_exg_rt1;
	}


	public void setDr_cr_exg_rt1(String dr_cr_exg_rt1) {
		this.dr_cr_exg_rt1 = dr_cr_exg_rt1;
	}


	public String getDr_cr_gross_amt1() {
		return dr_cr_gross_amt1;
	}


	public void setDr_cr_gross_amt1(String dr_cr_gross_amt1) {
		this.dr_cr_gross_amt1 = dr_cr_gross_amt1;
	}


	public String getDr_cr_no1() {
		return dr_cr_no1;
	}


	public void setDr_cr_no1(String dr_cr_no1) {
		this.dr_cr_no1 = dr_cr_no1;
	}


	public String getDue_dt1() {
		return due_dt1;
	}


	public void setDue_dt1(String due_dt1) {
		this.due_dt1 = due_dt1;
	}


	public String getGross_amt1() {
		return gross_amt1;
	}


	public void setGross_amt1(String gross_amt1) {
		this.gross_amt1 = gross_amt1;
	}


	public String getInv_dt1() {
		return inv_dt1;
	}


	public void setInv_dt1(String inv_dt1) {
		this.inv_dt1 = inv_dt1;
	}


	public String getQty1() {
		return qty1;
	}


	public void setQty1(String qty1) {
		this.qty1 = qty1;
	}


	public String getRemark1() {
		return remark1;
	}


	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}


	public String getSale_price1() {
		return sale_price1;
	}


	public void setSale_price1(String sale_price1) {
		this.sale_price1 = sale_price1;
	}


	public String getDiff_exg_rt1() {
		return diff_exg_rt1;
	}


	public String getDr_cr_net_amt_inr1() {
		return dr_cr_net_amt_inr1;
	}


	public String getTax_amt_inr1() {
		return tax_amt_inr1;
	}


	public String getDr_cr_net_amt1() {
		return dr_cr_net_amt1;
	}


	public String getExg_rt1() {
		return exg_rt1;
	}


	public Vector getCustomer_Invoice_Tax_Amt1() {
		return customer_Invoice_Tax_Amt1;
	}


	public void setCustomer_Invoice_Tax_Amt1(Vector customer_Invoice_Tax_Amt1) {
		this.customer_Invoice_Tax_Amt1 = customer_Invoice_Tax_Amt1;
	}


	public Vector getCustomer_Invoice_Tax_Code1() {
		return customer_Invoice_Tax_Code1;
	}


	public void setCustomer_Invoice_Tax_Code1(Vector customer_Invoice_Tax_Code1) {
		this.customer_Invoice_Tax_Code1 = customer_Invoice_Tax_Code1;
	}


	public Vector getCustomer_Invoice_Tax_Rate1() {
		return customer_Invoice_Tax_Rate1;
	}


	public void setCustomer_Invoice_Tax_Rate1(Vector customer_Invoice_Tax_Rate1) {
		this.customer_Invoice_Tax_Rate1 = customer_Invoice_Tax_Rate1;
	}


	public Vector getTAX_NAME1() {
		return TAX_NAME1;
	}


	public void setTAX_NAME1(Vector tax_name1) {
		TAX_NAME1 = tax_name1;
	}


	public Vector getTAX_ON_TITLE1() {
		return TAX_ON_TITLE1;
	}


	public String getGross_amt_usd1() {
		return gross_amt_usd1;
	}


	public String getDr_cr_doc_no1() {
		return dr_cr_doc_no1;
	}


	public double getTot_tax_amt1() {
		return tot_tax_amt1;
	}


	public Vector getVDrcrCriteria() {
		return VDrcrCriteria;
	}


	public void setDrcrcriteria(String drcrcriteria) {
		this.drcrcriteria = drcrcriteria;
	}


	public String getDiff_sale_rt1() {
		return diff_sale_rt1;
	}


	public String getDr_cr_sales_rate() {
		return dr_cr_sales_rate;
	}


	public String getFlag() {
		return flag;
	}


	public String getTotal_Invoice_Qty1() {
		return total_Invoice_Qty1;
	}


	public Vector getCustomer_Invoice_Tax_Amt2() {
		return customer_Invoice_Tax_Amt2;
	}


	public void setCustomer_Invoice_Tax_Amt2(Vector customer_Invoice_Tax_Amt2) {
		this.customer_Invoice_Tax_Amt2 = customer_Invoice_Tax_Amt2;
	}


	public Vector getCustomer_Invoice_Tax_Code2() {
		return customer_Invoice_Tax_Code2;
	}


	public void setCustomer_Invoice_Tax_Code2(Vector customer_Invoice_Tax_Code2) {
		this.customer_Invoice_Tax_Code2 = customer_Invoice_Tax_Code2;
	}


	public Vector getCustomer_Invoice_Tax_Rate2() {
		return customer_Invoice_Tax_Rate2;
	}


	public void setCustomer_Invoice_Tax_Rate2(Vector customer_Invoice_Tax_Rate2) {
		this.customer_Invoice_Tax_Rate2 = customer_Invoice_Tax_Rate2;
	}


	public String getNet_amt_inr1() {
		return net_amt_inr1;
	}


	public void setNet_amt_inr1(String net_amt_inr1) {
		this.net_amt_inr1 = net_amt_inr1;
	}


	public Vector getTAX_NAME2() {
		return TAX_NAME2;
	}


	public void setTAX_NAME2(Vector tax_name2) {
		TAX_NAME2 = tax_name2;
	}


	public Vector getTAX_ON_TITLE2() {
		return TAX_ON_TITLE2;
	}


	public String getDiff_qty1() {
		return diff_qty1;
	}


	public double getTot_tax_amt2() {
		return tot_tax_amt2;
	}


	public Vector getFlagqty() {
		return flagqty;
	}


	public void setFlag_drcr(String flag_drcr) {
		this.flag_drcr = flag_drcr;
	}


	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}


	public Vector getVRemark() {
		return VRemark;
	}


	public Vector getFLAG_DR_CR() {
		return FLAG_DR_CR;
	}


	public Vector getDr_cr_no2() {
		return dr_cr_no2;
	}

	public Vector getMultiple_adv_inv_no() {
		return multiple_adv_inv_no;
	}

	public void setMultiple_adv_inv_no(Vector multiple_adv_inv_no) {
		this.multiple_adv_inv_no = multiple_adv_inv_no;
	}

	public Vector getMultiple_adv_inv_dt() {
		return multiple_adv_inv_dt;
	}

	public void setMultiple_adv_inv_dt(Vector multiple_adv_inv_dt) {
		this.multiple_adv_inv_dt = multiple_adv_inv_dt;
	}

	public String getContact_Suppl_GSTIN_NO() {
		return contact_Suppl_GSTIN_NO;
	}

	public void setContact_Suppl_GSTIN_NO(String contact_Suppl_GSTIN_NO) {
		this.contact_Suppl_GSTIN_NO = contact_Suppl_GSTIN_NO;
	}

	public String getContact_Suppl_GSTIN_DT() {
		return contact_Suppl_GSTIN_DT;
	}

	public void setContact_Suppl_GSTIN_DT(String contact_Suppl_GSTIN_DT) {
		this.contact_Suppl_GSTIN_DT = contact_Suppl_GSTIN_DT;
	}

	public String getContact_Customer_Plant_State() {
		return contact_Customer_Plant_State;
	}

	public void setContact_Customer_Plant_State(String contact_Customer_Plant_State) {
		this.contact_Customer_Plant_State = contact_Customer_Plant_State;
	}

	public String getContact_Customer_State_Code() {
		return contact_Customer_State_Code;
	}

	public void setContact_Customer_State_Code(String contact_Customer_State_Code) {
		this.contact_Customer_State_Code = contact_Customer_State_Code;
	}

	public String getSac_code() {
		return sac_code;
	}

	public void setSac_code(String sac_code) {
		this.sac_code = sac_code;
	}

	public String getSac_name() {
		return sac_name;
	}

	public void setSac_name(String sac_name) {
		this.sac_name = sac_name;
	}

	public String getRule_remark() {
		return rule_remark;
	}

	public void setRule_remark(String rule_remark) {
		this.rule_remark = rule_remark;
	}

	public String getContact_Suppl_State() {
		return contact_Suppl_State;
	}

	public void setContact_Suppl_State(String contact_Suppl_State) {
		this.contact_Suppl_State = contact_Suppl_State;
	}

	public String getContact_Suppl_State_Code() {
		return contact_Suppl_State_Code;
	}

	public void setContact_Suppl_State_Code(String contact_Suppl_State_Code) {
		this.contact_Suppl_State_Code = contact_Suppl_State_Code;
	}

	public boolean isDate_flag() {
		return date_flag;
	}

	public void setDate_flag(boolean date_flag) {
		this.date_flag = date_flag;
	}

	public Vector getTAX_FLAG_SERVICE() {
		return TAX_FLAG_SERVICE;
	}

	public void setTAX_FLAG_SERVICE(Vector tAX_FLAG_SERVICE) {
		TAX_FLAG_SERVICE = tAX_FLAG_SERVICE;
	}

	public Vector getTAX_AMT_SERVICE() {
		return TAX_AMT_SERVICE;
	}

	public void setTAX_AMT_SERVICE(Vector tAX_AMT_SERVICE) {
		TAX_AMT_SERVICE = tAX_AMT_SERVICE;
	}

	public Vector getTAX_CUR_SERVICE() {
		return TAX_CUR_SERVICE;
	}

	public void setTAX_CUR_SERVICE(Vector tAX_CUR_SERVICE) {
		TAX_CUR_SERVICE = tAX_CUR_SERVICE;
	}

	public Vector getTAX_CODE() {
		return TAX_CODE;
	}

	public void setTAX_CODE(Vector tAX_CODE) {
		TAX_CODE = tAX_CODE;
	}

	public Map getTax_avail_balance() {
		return tax_avail_balance;
	}

	public void setTax_avail_balance(Map tax_avail_balance) {
		this.tax_avail_balance = tax_avail_balance;
	}

	public Map getTax_adv_flag() {
		return tax_adv_flag;
	}

	public void setTax_adv_flag(Map tax_adv_flag) {
		this.tax_adv_flag = tax_adv_flag;
	}

	public Map getTax_adv_adjustment() {
		return tax_adv_adjustment;
	}

	public void setTax_adv_adjustment(Map tax_adv_adjustment) {
		this.tax_adv_adjustment = tax_adv_adjustment;
	}

	public Map getTax_adv_curr() {
		return tax_adv_curr;
	}

	public void setTax_adv_curr(Map tax_adv_curr) {
		this.tax_adv_curr = tax_adv_curr;
	}

	public Map getTax_inv_amt_inr() {
		return tax_inv_amt_inr;
	}

	public void setTax_inv_amt_inr(Map tax_inv_amt_inr) {
		this.tax_inv_amt_inr = tax_inv_amt_inr;
	}

	public Vector getTAX_ABBR() {
		return TAX_ABBR;
	}

	public void setTAX_ABBR(Vector tAX_ABBR) {
		TAX_ABBR = tAX_ABBR;
	}

	public String getGST_INVOICE_SEQ_NO() {
		return GST_INVOICE_SEQ_NO;
	}

	public void setGST_INVOICE_SEQ_NO(String gST_INVOICE_SEQ_NO) {
		GST_INVOICE_SEQ_NO = gST_INVOICE_SEQ_NO;
	}

	public Vector getNew_Invoice_Seq_No() {
		return new_Invoice_Seq_No;
	}

	public void setNew_Invoice_Seq_No(Vector new_Invoice_Seq_No) {
		this.new_Invoice_Seq_No = new_Invoice_Seq_No;
	}

	public String getNew_inv_seq_no() {
		return new_inv_seq_no;
	}

	public void setNew_inv_seq_no(String new_inv_seq_no) {
		this.new_inv_seq_no = new_inv_seq_no;
	}

	public Vector getNEW_INV_SEQ_NO() {
		return NEW_INV_SEQ_NO;
	}

	public void setNEW_INV_SEQ_NO(Vector nEW_INV_SEQ_NO) {
		NEW_INV_SEQ_NO = nEW_INV_SEQ_NO;
	}

	public Vector getDR_CR_NEW_INV_SEQ_NO() {
		return DR_CR_NEW_INV_SEQ_NO;
	}

	public void setDR_CR_NEW_INV_SEQ_NO(Vector dR_CR_NEW_INV_SEQ_NO) {
		DR_CR_NEW_INV_SEQ_NO = dR_CR_NEW_INV_SEQ_NO;
	}

	public boolean isTax_gst() {
		return tax_gst;
	}

	public void setTax_gst(boolean tax_gst) {
		this.tax_gst = tax_gst;
	}

	public String getService_desc() {
		return service_desc;
	}

	public void setService_desc(String service_desc) {
		this.service_desc = service_desc;
	}


	public String getCust_nm_rpt() {
		return cust_nm_rpt;
	}


	public void setCust_nm_rpt(String cust_nm_rpt) {
		this.cust_nm_rpt = cust_nm_rpt;
	}


	public String getYear_rpt() {
		return year_rpt;
	}


	public void setYear_rpt(String year_rpt) {
		this.year_rpt = year_rpt;
	}


	public String getTo_year_rpt() {
		return to_year_rpt;
	}


	public void setTo_year_rpt(String to_year_rpt) {
		this.to_year_rpt = to_year_rpt;
	}


	public String getMonth_rpt() {
		return month_rpt;
	}


	public void setMonth_rpt(String month_rpt) {
		this.month_rpt = month_rpt;
	}


	public String getTo_month_rpt() {
		return to_month_rpt;
	}


	public void setTo_month_rpt(String to_month_rpt) {
		this.to_month_rpt = to_month_rpt;
	}


	public String getInvoice_type_rpt() {
		return invoice_type_rpt;
	}


	public void setInvoice_type_rpt(String invoice_type_rpt) {
		this.invoice_type_rpt = invoice_type_rpt;
	}


	public Map getCredit_map() {
		return credit_map;
	}


	public void setCredit_map(Map credit_map) {
		this.credit_map = credit_map;
	}


	public Vector getvSTAT_CD() {
		return vSTAT_CD;
	}


	public void setvSTAT_CD(Vector vSTAT_CD) {
		this.vSTAT_CD = vSTAT_CD;
	}


	public String getView_flag() {
		return view_flag;
	}


	public void setView_flag(String view_flag) {
		this.view_flag = view_flag;
	}


	public String getInvoice_dt() {
		return invoice_dt;
	}


	public void setInvoice_dt(String invoice_dt) {
		this.invoice_dt = invoice_dt;
	}


	public String getTot_qty() {
		return tot_qty;
	}


	public void setTot_qty(String tot_qty) {
		this.tot_qty = tot_qty;
	}


	public String getSale_rate() {
		return sale_rate;
	}


	public void setSale_rate(String sale_rate) {
		this.sale_rate = sale_rate;
	}


	public String getGross_amt_inr() {
		return gross_amt_inr;
	}


	public void setGross_amt_inr(String gross_amt_inr) {
		this.gross_amt_inr = gross_amt_inr;
	}


	public String getNet_amt_inr() {
		return net_amt_inr;
	}


	public void setNet_amt_inr(String net_amt_inr) {
		this.net_amt_inr = net_amt_inr;
	}


	public String getExch_rate() {
		return exch_rate;
	}


	public void setExch_rate(String exch_rate) {
		this.exch_rate = exch_rate;
	}


	public Vector getNEW_INV_SEQ_NO_() {
		return NEW_INV_SEQ_NO_;
	}


	public void setNEW_INV_SEQ_NO_(Vector nEW_INV_SEQ_NO_) {
		NEW_INV_SEQ_NO_ = nEW_INV_SEQ_NO_;
	}


	public Vector getCustomer_Invoice_Bank_Name() {
		return customer_Invoice_Bank_Name;
	}


	public void setCustomer_Invoice_Bank_Name(Vector customer_Invoice_Bank_Name) {
		this.customer_Invoice_Bank_Name = customer_Invoice_Bank_Name;
	}


	public Vector getHLPL_INV_SEQ_NO_RPT() {
		return HLPL_INV_SEQ_NO_RPT;
	}


	public void setHLPL_INV_SEQ_NO_RPT(Vector hLPL_INV_SEQ_NO_RPT) {
		HLPL_INV_SEQ_NO_RPT = hLPL_INV_SEQ_NO_RPT;
	}


	public Vector getNEW_INV_SEQ_NO_RPT() {
		return NEW_INV_SEQ_NO_RPT;
	}


	public void setNEW_INV_SEQ_NO_RPT(Vector nEW_INV_SEQ_NO_RPT) {
		NEW_INV_SEQ_NO_RPT = nEW_INV_SEQ_NO_RPT;
	}


	public Vector getCONTRACT_TYPE_RPT() {
		return CONTRACT_TYPE_RPT;
	}


	public void setCONTRACT_TYPE_RPT(Vector cONTRACT_TYPE_RPT) {
		CONTRACT_TYPE_RPT = cONTRACT_TYPE_RPT;
	}


	public Vector getCUSTOMER_CD_RPT() {
		return CUSTOMER_CD_RPT;
	}


	public void setCUSTOMER_CD_RPT(Vector cUSTOMER_CD_RPT) {
		CUSTOMER_CD_RPT = cUSTOMER_CD_RPT;
	}


	public Vector getGROSS_AMT_INR_RPT() {
		return GROSS_AMT_INR_RPT;
	}


	public void setGROSS_AMT_INR_RPT(Vector gROSS_AMT_INR_RPT) {
		GROSS_AMT_INR_RPT = gROSS_AMT_INR_RPT;
	}


	public Vector getNET_AMT_INR_RPT() {
		return NET_AMT_INR_RPT;
	}


	public void setNET_AMT_INR_RPT(Vector nET_AMT_INR_RPT) {
		NET_AMT_INR_RPT = nET_AMT_INR_RPT;
	}


	public Vector getFLAG_RPT() {
		return FLAG_RPT;
	}


	public void setFLAG_RPT(Vector fLAG_RPT) {
		FLAG_RPT = fLAG_RPT;
	}


	public Vector getINVOICE_DT_RPT() {
		return INVOICE_DT_RPT;
	}


	public void setINVOICE_DT_RPT(Vector iNVOICE_DT_RPT) {
		INVOICE_DT_RPT = iNVOICE_DT_RPT;
	}


	public Vector getCUSTOMER_ABBR_RPT() {
		return CUSTOMER_ABBR_RPT;
	}


	public void setCUSTOMER_ABBR_RPT(Vector cUSTOMER_ABBR_RPT) {
		CUSTOMER_ABBR_RPT = cUSTOMER_ABBR_RPT;
	}


	public String getMONTH_RPT() {
		return MONTH_RPT;
	}


	public void setMONTH_RPT(String mONTH_RPT) {
		MONTH_RPT = mONTH_RPT;
	}


	public String getYEAR_RPT() {
		return YEAR_RPT;
	}


	public void setYEAR_RPT(String yEAR_RPT) {
		YEAR_RPT = yEAR_RPT;
	}


	public Vector getINVOICE_TYPE_RPT() {
		return INVOICE_TYPE_RPT;
	}


	public void setINVOICE_TYPE_RPT(Vector iNVOICE_TYPE_RPT) {
		INVOICE_TYPE_RPT = iNVOICE_TYPE_RPT;
	}


	public Vector getENT_BY_RPT() {
		return ENT_BY_RPT;
	}


	public void setENT_BY_RPT(Vector eNT_BY_RPT) {
		ENT_BY_RPT = eNT_BY_RPT;
	}


	public Vector getTAX_AMT_RPT() {
		return TAX_AMT_RPT;
	}


	public void setTAX_AMT_RPT(Vector tAX_AMT_RPT) {
		TAX_AMT_RPT = tAX_AMT_RPT;
	}


	public Vector getADV_HLPL_INV_SEQ_NO_RPT() {
		return ADV_HLPL_INV_SEQ_NO_RPT;
	}


	public void setADV_HLPL_INV_SEQ_NO_RPT(Vector aDV_HLPL_INV_SEQ_NO_RPT) {
		ADV_HLPL_INV_SEQ_NO_RPT = aDV_HLPL_INV_SEQ_NO_RPT;
	}


	public Vector getADV_NEW_INV_SEQ_NO_RPT() {
		return ADV_NEW_INV_SEQ_NO_RPT;
	}


	public void setADV_NEW_INV_SEQ_NO_RPT(Vector aDV_NEW_INV_SEQ_NO_RPT) {
		ADV_NEW_INV_SEQ_NO_RPT = aDV_NEW_INV_SEQ_NO_RPT;
	}


	public Vector getADV_CONTRACT_TYPE_RPT() {
		return ADV_CONTRACT_TYPE_RPT;
	}


	public void setADV_CONTRACT_TYPE_RPT(Vector aDV_CONTRACT_TYPE_RPT) {
		ADV_CONTRACT_TYPE_RPT = aDV_CONTRACT_TYPE_RPT;
	}


	public Vector getADV_CUSTOMER_CD_RPT() {
		return ADV_CUSTOMER_CD_RPT;
	}


	public void setADV_CUSTOMER_CD_RPT(Vector aDV_CUSTOMER_CD_RPT) {
		ADV_CUSTOMER_CD_RPT = aDV_CUSTOMER_CD_RPT;
	}


	public Vector getADV_GROSS_AMT_INR_RPT() {
		return ADV_GROSS_AMT_INR_RPT;
	}


	public void setADV_GROSS_AMT_INR_RPT(Vector aDV_GROSS_AMT_INR_RPT) {
		ADV_GROSS_AMT_INR_RPT = aDV_GROSS_AMT_INR_RPT;
	}


	public Vector getADV_NET_AMT_INR_RPT() {
		return ADV_NET_AMT_INR_RPT;
	}


	public void setADV_NET_AMT_INR_RPT(Vector aDV_NET_AMT_INR_RPT) {
		ADV_NET_AMT_INR_RPT = aDV_NET_AMT_INR_RPT;
	}


	public Vector getADV_FLAG_RPT() {
		return ADV_FLAG_RPT;
	}


	public void setADV_FLAG_RPT(Vector aDV_FLAG_RPT) {
		ADV_FLAG_RPT = aDV_FLAG_RPT;
	}


	public Vector getADV_INVOICE_DT_RPT() {
		return ADV_INVOICE_DT_RPT;
	}


	public void setADV_INVOICE_DT_RPT(Vector aDV_INVOICE_DT_RPT) {
		ADV_INVOICE_DT_RPT = aDV_INVOICE_DT_RPT;
	}


	public Vector getADV_CUSTOMER_ABBR_RPT() {
		return ADV_CUSTOMER_ABBR_RPT;
	}


	public void setADV_CUSTOMER_ABBR_RPT(Vector aDV_CUSTOMER_ABBR_RPT) {
		ADV_CUSTOMER_ABBR_RPT = aDV_CUSTOMER_ABBR_RPT;
	}


	public Vector getADV_INVOICE_TYPE_RPT() {
		return ADV_INVOICE_TYPE_RPT;
	}


	public void setADV_INVOICE_TYPE_RPT(Vector aDV_INVOICE_TYPE_RPT) {
		ADV_INVOICE_TYPE_RPT = aDV_INVOICE_TYPE_RPT;
	}


	public Vector getADV_ENT_BY_RPT() {
		return ADV_ENT_BY_RPT;
	}


	public void setADV_ENT_BY_RPT(Vector aDV_ENT_BY_RPT) {
		ADV_ENT_BY_RPT = aDV_ENT_BY_RPT;
	}


	public Vector getADV_TAX_AMT_RPT() {
		return ADV_TAX_AMT_RPT;
	}


	public void setADV_TAX_AMT_RPT(Vector aDV_TAX_AMT_RPT) {
		ADV_TAX_AMT_RPT = aDV_TAX_AMT_RPT;
	}


	public Vector getSN_HLPL_INV_SEQ_NO_RPT() {
		return SN_HLPL_INV_SEQ_NO_RPT;
	}


	public void setSN_HLPL_INV_SEQ_NO_RPT(Vector sN_HLPL_INV_SEQ_NO_RPT) {
		SN_HLPL_INV_SEQ_NO_RPT = sN_HLPL_INV_SEQ_NO_RPT;
	}


	public Vector getSN_NEW_INV_SEQ_NO_RPT() {
		return SN_NEW_INV_SEQ_NO_RPT;
	}


	public void setSN_NEW_INV_SEQ_NO_RPT(Vector sN_NEW_INV_SEQ_NO_RPT) {
		SN_NEW_INV_SEQ_NO_RPT = sN_NEW_INV_SEQ_NO_RPT;
	}


	public Vector getSN_CONTRACT_TYPE_RPT() {
		return SN_CONTRACT_TYPE_RPT;
	}


	public void setSN_CONTRACT_TYPE_RPT(Vector sN_CONTRACT_TYPE_RPT) {
		SN_CONTRACT_TYPE_RPT = sN_CONTRACT_TYPE_RPT;
	}


	public Vector getSN_CUSTOMER_CD_RPT() {
		return SN_CUSTOMER_CD_RPT;
	}


	public void setSN_CUSTOMER_CD_RPT(Vector sN_CUSTOMER_CD_RPT) {
		SN_CUSTOMER_CD_RPT = sN_CUSTOMER_CD_RPT;
	}


	public Vector getSN_GROSS_AMT_INR_RPT() {
		return SN_GROSS_AMT_INR_RPT;
	}


	public void setSN_GROSS_AMT_INR_RPT(Vector sN_GROSS_AMT_INR_RPT) {
		SN_GROSS_AMT_INR_RPT = sN_GROSS_AMT_INR_RPT;
	}


	public Vector getSN_NET_AMT_INR_RPT() {
		return SN_NET_AMT_INR_RPT;
	}


	public void setSN_NET_AMT_INR_RPT(Vector sN_NET_AMT_INR_RPT) {
		SN_NET_AMT_INR_RPT = sN_NET_AMT_INR_RPT;
	}


	public Vector getSN_FLAG_RPT() {
		return SN_FLAG_RPT;
	}


	public void setSN_FLAG_RPT(Vector sN_FLAG_RPT) {
		SN_FLAG_RPT = sN_FLAG_RPT;
	}


	public Vector getSN_INVOICE_DT_RPT() {
		return SN_INVOICE_DT_RPT;
	}


	public void setSN_INVOICE_DT_RPT(Vector sN_INVOICE_DT_RPT) {
		SN_INVOICE_DT_RPT = sN_INVOICE_DT_RPT;
	}


	public Vector getSN_CUSTOMER_ABBR_RPT() {
		return SN_CUSTOMER_ABBR_RPT;
	}


	public void setSN_CUSTOMER_ABBR_RPT(Vector sN_CUSTOMER_ABBR_RPT) {
		SN_CUSTOMER_ABBR_RPT = sN_CUSTOMER_ABBR_RPT;
	}


	public Vector getSN_INVOICE_TYPE_RPT() {
		return SN_INVOICE_TYPE_RPT;
	}


	public void setSN_INVOICE_TYPE_RPT(Vector sN_INVOICE_TYPE_RPT) {
		SN_INVOICE_TYPE_RPT = sN_INVOICE_TYPE_RPT;
	}


	public Vector getSN_ENT_BY_RPT() {
		return SN_ENT_BY_RPT;
	}


	public void setSN_ENT_BY_RPT(Vector sN_ENT_BY_RPT) {
		SN_ENT_BY_RPT = sN_ENT_BY_RPT;
	}


	public Vector getSN_TAX_AMT_RPT() {
		return SN_TAX_AMT_RPT;
	}


	public void setSN_TAX_AMT_RPT(Vector sN_TAX_AMT_RPT) {
		SN_TAX_AMT_RPT = sN_TAX_AMT_RPT;
	}


	public Vector getCR_HLPL_INV_SEQ_NO_RPT() {
		return CR_HLPL_INV_SEQ_NO_RPT;
	}


	public void setCR_HLPL_INV_SEQ_NO_RPT(Vector cR_HLPL_INV_SEQ_NO_RPT) {
		CR_HLPL_INV_SEQ_NO_RPT = cR_HLPL_INV_SEQ_NO_RPT;
	}


	public Vector getCR_NEW_INV_SEQ_NO_RPT() {
		return CR_NEW_INV_SEQ_NO_RPT;
	}


	public void setCR_NEW_INV_SEQ_NO_RPT(Vector cR_NEW_INV_SEQ_NO_RPT) {
		CR_NEW_INV_SEQ_NO_RPT = cR_NEW_INV_SEQ_NO_RPT;
	}


	public Vector getCR_CONTRACT_TYPE_RPT() {
		return CR_CONTRACT_TYPE_RPT;
	}


	public void setCR_CONTRACT_TYPE_RPT(Vector cR_CONTRACT_TYPE_RPT) {
		CR_CONTRACT_TYPE_RPT = cR_CONTRACT_TYPE_RPT;
	}


	public Vector getCR_CUSTOMER_CD_RPT() {
		return CR_CUSTOMER_CD_RPT;
	}


	public void setCR_CUSTOMER_CD_RPT(Vector cR_CUSTOMER_CD_RPT) {
		CR_CUSTOMER_CD_RPT = cR_CUSTOMER_CD_RPT;
	}


	public Vector getCR_GROSS_AMT_INR_RPT() {
		return CR_GROSS_AMT_INR_RPT;
	}


	public void setCR_GROSS_AMT_INR_RPT(Vector cR_GROSS_AMT_INR_RPT) {
		CR_GROSS_AMT_INR_RPT = cR_GROSS_AMT_INR_RPT;
	}


	public Vector getCR_NET_AMT_INR_RPT() {
		return CR_NET_AMT_INR_RPT;
	}


	public void setCR_NET_AMT_INR_RPT(Vector cR_NET_AMT_INR_RPT) {
		CR_NET_AMT_INR_RPT = cR_NET_AMT_INR_RPT;
	}


	public Vector getCR_FLAG_RPT() {
		return CR_FLAG_RPT;
	}


	public void setCR_FLAG_RPT(Vector cR_FLAG_RPT) {
		CR_FLAG_RPT = cR_FLAG_RPT;
	}


	public Vector getCR_INVOICE_DT_RPT() {
		return CR_INVOICE_DT_RPT;
	}


	public void setCR_INVOICE_DT_RPT(Vector cR_INVOICE_DT_RPT) {
		CR_INVOICE_DT_RPT = cR_INVOICE_DT_RPT;
	}


	public Vector getCR_CUSTOMER_ABBR_RPT() {
		return CR_CUSTOMER_ABBR_RPT;
	}


	public void setCR_CUSTOMER_ABBR_RPT(Vector cR_CUSTOMER_ABBR_RPT) {
		CR_CUSTOMER_ABBR_RPT = cR_CUSTOMER_ABBR_RPT;
	}


	public Vector getCR_INVOICE_TYPE_RPT() {
		return CR_INVOICE_TYPE_RPT;
	}


	public void setCR_INVOICE_TYPE_RPT(Vector cR_INVOICE_TYPE_RPT) {
		CR_INVOICE_TYPE_RPT = cR_INVOICE_TYPE_RPT;
	}


	public Vector getCR_ENT_BY_RPT() {
		return CR_ENT_BY_RPT;
	}


	public void setCR_ENT_BY_RPT(Vector cR_ENT_BY_RPT) {
		CR_ENT_BY_RPT = cR_ENT_BY_RPT;
	}


	public Vector getCR_TAX_AMT_RPT() {
		return CR_TAX_AMT_RPT;
	}


	public void setCR_TAX_AMT_RPT(Vector cR_TAX_AMT_RPT) {
		CR_TAX_AMT_RPT = cR_TAX_AMT_RPT;
	}


	public Vector getCR_CRITERIA_RPT() {
		return CR_CRITERIA_RPT;
	}


	public void setCR_CRITERIA_RPT(Vector cR_CRITERIA_RPT) {
		CR_CRITERIA_RPT = cR_CRITERIA_RPT;
	}


	public Vector getCR_DOC_NO_RPT() {
		return CR_DOC_NO_RPT;
	}


	public void setCR_DOC_NO_RPT(Vector cR_DOC_NO_RPT) {
		CR_DOC_NO_RPT = cR_DOC_NO_RPT;
	}
	public Vector getCR_DT_RPT() {
		return CR_DT_RPT;
	}
	public void setCR_DT_RPT(Vector cR_DT_RPT) {
		CR_DT_RPT = cR_DT_RPT;
	}
	public Vector getOTH_HLPL_INV_SEQ_NO_RPT() {
		return OTH_HLPL_INV_SEQ_NO_RPT;
	}
	public void setOTH_HLPL_INV_SEQ_NO_RPT(Vector oTH_HLPL_INV_SEQ_NO_RPT) {
		OTH_HLPL_INV_SEQ_NO_RPT = oTH_HLPL_INV_SEQ_NO_RPT;
	}
	public Vector getOTH_NEW_INV_SEQ_NO_RPT() {
		return OTH_NEW_INV_SEQ_NO_RPT;
	}
	public void setOTH_NEW_INV_SEQ_NO_RPT(Vector oTH_NEW_INV_SEQ_NO_RPT) {
		OTH_NEW_INV_SEQ_NO_RPT = oTH_NEW_INV_SEQ_NO_RPT;
	}
	public Vector getOTH_CONTRACT_TYPE_RPT() {
		return OTH_CONTRACT_TYPE_RPT;
	}
	public void setOTH_CONTRACT_TYPE_RPT(Vector oTH_CONTRACT_TYPE_RPT) {
		OTH_CONTRACT_TYPE_RPT = oTH_CONTRACT_TYPE_RPT;
	}
	public Vector getOTH_CUSTOMER_CD_RPT() {
		return OTH_CUSTOMER_CD_RPT;
	}
	public void setOTH_CUSTOMER_CD_RPT(Vector oTH_CUSTOMER_CD_RPT) {
		OTH_CUSTOMER_CD_RPT = oTH_CUSTOMER_CD_RPT;
	}
	public Vector getOTH_GROSS_AMT_INR_RPT() {
		return OTH_GROSS_AMT_INR_RPT;
	}
	public void setOTH_GROSS_AMT_INR_RPT(Vector oTH_GROSS_AMT_INR_RPT) {
		OTH_GROSS_AMT_INR_RPT = oTH_GROSS_AMT_INR_RPT;
	}
	public Vector getOTH_NET_AMT_INR_RPT() {
		return OTH_NET_AMT_INR_RPT;
	}
	public void setOTH_NET_AMT_INR_RPT(Vector oTH_NET_AMT_INR_RPT) {
		OTH_NET_AMT_INR_RPT = oTH_NET_AMT_INR_RPT;
	}
	public Vector getOTH_FLAG_RPT() {
		return OTH_FLAG_RPT;
	}
	public void setOTH_FLAG_RPT(Vector oTH_FLAG_RPT) {
		OTH_FLAG_RPT = oTH_FLAG_RPT;
	}
	public Vector getOTH_INVOICE_DT_RPT() {
		return OTH_INVOICE_DT_RPT;
	}
	public void setOTH_INVOICE_DT_RPT(Vector oTH_INVOICE_DT_RPT) {
		OTH_INVOICE_DT_RPT = oTH_INVOICE_DT_RPT;
	}
	public Vector getOTH_CUSTOMER_ABBR_RPT() {
		return OTH_CUSTOMER_ABBR_RPT;
	}
	public void setOTH_CUSTOMER_ABBR_RPT(Vector oTH_CUSTOMER_ABBR_RPT) {
		OTH_CUSTOMER_ABBR_RPT = oTH_CUSTOMER_ABBR_RPT;
	}
	public Vector getOTH_INVOICE_TYPE_RPT() {
		return OTH_INVOICE_TYPE_RPT;
	}
	public void setOTH_INVOICE_TYPE_RPT(Vector oTH_INVOICE_TYPE_RPT) {
		OTH_INVOICE_TYPE_RPT = oTH_INVOICE_TYPE_RPT;
	}
	public Vector getOTH_ENT_BY_RPT() {
		return OTH_ENT_BY_RPT;
	}
	public void setOTH_ENT_BY_RPT(Vector oTH_ENT_BY_RPT) {
		OTH_ENT_BY_RPT = oTH_ENT_BY_RPT;
	}
	public Vector getOTH_TAX_AMT_RPT() {
		return OTH_TAX_AMT_RPT;
	}
	public void setOTH_TAX_AMT_RPT(Vector oTH_TAX_AMT_RPT) {
		OTH_TAX_AMT_RPT = oTH_TAX_AMT_RPT;
	}
	public Vector getOTH_GROSS_AMT_CUR_RPT() {
		return OTH_GROSS_AMT_CUR_RPT;
	}
	public void setOTH_GROSS_AMT_CUR_RPT(Vector oTH_GROSS_AMT_CUR_RPT) {
		OTH_GROSS_AMT_CUR_RPT = oTH_GROSS_AMT_CUR_RPT;
	}
	public Vector getAPPROVED_FLAG_RPT() {
		return APPROVED_FLAG_RPT;
	}
	public void setAPPROVED_FLAG_RPT(Vector aPPROVED_FLAG_RPT) {
		APPROVED_FLAG_RPT = aPPROVED_FLAG_RPT;
	}
	public Vector getCHECKED_FLAG_RPT() {
		return CHECKED_FLAG_RPT;
	}
	public void setCHECKED_FLAG_RPT(Vector cHECKED_FLAG_RPT) {
		CHECKED_FLAG_RPT = cHECKED_FLAG_RPT;
	}
	public Vector getOTH_APPROVED_FLAG_RPT() {
		return OTH_APPROVED_FLAG_RPT;
	}
	public void setOTH_APPROVED_FLAG_RPT(Vector oTH_APPROVED_FLAG_RPT) {
		OTH_APPROVED_FLAG_RPT = oTH_APPROVED_FLAG_RPT;
	}
	public Vector getOTH_CHECKED_FLAG_RPT() {
		return OTH_CHECKED_FLAG_RPT;
	}
	public void setOTH_CHECKED_FLAG_RPT(Vector oTH_CHECKED_FLAG_RPT) {
		OTH_CHECKED_FLAG_RPT = oTH_CHECKED_FLAG_RPT;
	}
	public Vector getADV_APPROVED_FLAG_RPT() {
		return ADV_APPROVED_FLAG_RPT;
	}
	public void setADV_APPROVED_FLAG_RPT(Vector aDV_APPROVED_FLAG_RPT) {
		ADV_APPROVED_FLAG_RPT = aDV_APPROVED_FLAG_RPT;
	}
	public Vector getADV_CHECKED_FLAG_RPT() {
		return ADV_CHECKED_FLAG_RPT;
	}
	public void setADV_CHECKED_FLAG_RPT(Vector aDV_CHECKED_FLAG_RPT) {
		ADV_CHECKED_FLAG_RPT = aDV_CHECKED_FLAG_RPT;
	}
	public Vector getSN_APPROVED_FLAG_RPT() {
		return SN_APPROVED_FLAG_RPT;
	}
	public void setSN_APPROVED_FLAG_RPT(Vector sN_APPROVED_FLAG_RPT) {
		SN_APPROVED_FLAG_RPT = sN_APPROVED_FLAG_RPT;
	}
	public Vector getSN_CHECKED_FLAG_RPT() {
		return SN_CHECKED_FLAG_RPT;
	}
	public void setSN_CHECKED_FLAG_RPT(Vector sN_CHECKED_FLAG_RPT) {
		SN_CHECKED_FLAG_RPT = sN_CHECKED_FLAG_RPT;
	}
	public Vector getCR_APPROVED_FLAG_RPT() {
		return CR_APPROVED_FLAG_RPT;
	}
	public void setCR_APPROVED_FLAG_RPT(Vector cR_APPROVED_FLAG_RPT) {
		CR_APPROVED_FLAG_RPT = cR_APPROVED_FLAG_RPT;
	}


	public Vector getAPPROVED_DT_RPT() {
		return APPROVED_DT_RPT;
	}


	public void setAPPROVED_DT_RPT(Vector aPPROVED_DT_RPT) {
		APPROVED_DT_RPT = aPPROVED_DT_RPT;
	}


	public Vector getCHECKED_DT_RPT() {
		return CHECKED_DT_RPT;
	}


	public void setCHECKED_DT_RPT(Vector cHECKED_DT_RPT) {
		CHECKED_DT_RPT = cHECKED_DT_RPT;
	}


	public Vector getOTH_APPROVED_DT_RPT() {
		return OTH_APPROVED_DT_RPT;
	}


	public void setOTH_APPROVED_DT_RPT(Vector oTH_APPROVED_DT_RPT) {
		OTH_APPROVED_DT_RPT = oTH_APPROVED_DT_RPT;
	}


	public Vector getOTH_CHECKED_DT_RPT() {
		return OTH_CHECKED_DT_RPT;
	}


	public void setOTH_CHECKED_DT_RPT(Vector oTH_CHECKED_DT_RPT) {
		OTH_CHECKED_DT_RPT = oTH_CHECKED_DT_RPT;
	}


	public Vector getADV_APPROVED_DT_RPT() {
		return ADV_APPROVED_DT_RPT;
	}


	public void setADV_APPROVED_DT_RPT(Vector aDV_APPROVED_DT_RPT) {
		ADV_APPROVED_DT_RPT = aDV_APPROVED_DT_RPT;
	}


	public Vector getADV_CHECKED_DT_RPT() {
		return ADV_CHECKED_DT_RPT;
	}


	public void setADV_CHECKED_DT_RPT(Vector aDV_CHECKED_DT_RPT) {
		ADV_CHECKED_DT_RPT = aDV_CHECKED_DT_RPT;
	}


	public Vector getSN_APPROVED_DT_RPT() {
		return SN_APPROVED_DT_RPT;
	}


	public void setSN_APPROVED_DT_RPT(Vector sN_APPROVED_DT_RPT) {
		SN_APPROVED_DT_RPT = sN_APPROVED_DT_RPT;
	}


	public Vector getSN_CHECKED_DT_RPT() {
		return SN_CHECKED_DT_RPT;
	}


	public void setSN_CHECKED_DT_RPT(Vector sN_CHECKED_DT_RPT) {
		SN_CHECKED_DT_RPT = sN_CHECKED_DT_RPT;
	}


	public Vector getCR_APPROVED_DT_RPT() {
		return CR_APPROVED_DT_RPT;
	}


	public void setCR_APPROVED_DT_RPT(Vector cR_APPROVED_DT_RPT) {
		CR_APPROVED_DT_RPT = cR_APPROVED_DT_RPT;
	}


	public String getDr_cr_aprv_by() {
		return dr_cr_aprv_by;
	}


	public void setDr_cr_aprv_by(String dr_cr_aprv_by) {
		this.dr_cr_aprv_by = dr_cr_aprv_by;
	}


	public String getRec_remark() {
		return rec_remark;
	}


	public void setRec_remark(String rec_remark) {
		this.rec_remark = rec_remark;
	}
	public Vector getvDtCnt() {
		return vDtCnt;
	}
	public Map getDtMap() {
		return dtMap;
	}
	public String getTcs_amt() {
		return tcs_amt;
	}
	public String getFact() {
		return fact;
	}
	public String getTcs_nm() {
		return tcs_nm;
	}
	public String getTcs_app_flag() {
		return tcs_app_flag;
	}
	public String getContact_Customer_Person_State() {
		return contact_Customer_Person_State;
	}
	public String getDr_cr_due_dt() {
		return dr_cr_due_dt;
	}
	public void setDr_cr_due_dt(String dr_cr_due_dt) {
		this.dr_cr_due_dt = dr_cr_due_dt;
	}
	public String getDr_cr_qty() {
		return dr_cr_qty;
	}
	public void setDr_cr_qty(String dr_cr_qty) {
		this.dr_cr_qty = dr_cr_qty;
	}
	public String getDiff_tax() {
		return diff_tax;
	}
	public void setDiff_tax(String diff_tax) {
		this.diff_tax = diff_tax;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	
}//End Of Class DataBean_Sales_Invoice .