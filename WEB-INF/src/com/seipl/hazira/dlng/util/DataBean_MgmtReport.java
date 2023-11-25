package com.seipl.hazira.dlng.util;
import javax.naming.*;
import javax.sql.*;

import java.util.*;
import java.sql.*;
import java.text.*;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

public class DataBean_MgmtReport
{
    Connection conn; 
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
	
	//Following NumberFormat Object is defined by Achal Pathak ... On 22nd Nov., 2010 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf1 = new DecimalFormat("###########0");
	NumberFormat nf2 = new DecimalFormat("###########0");
	NumberFormat nf3 = new DecimalFormat("###,###,###,###");
	NumberFormat nf4 = new DecimalFormat("###,###,###,#00");
	NumberFormat nf5 = new DecimalFormat("###########0");
	NumberFormat nf6 = new DecimalFormat("###,###,###,##0.00");
	NumberFormat nf7 = new DecimalFormat("#############0.0000");
		
    NumberFormat nf8 = new DecimalFormat("###########0");
	NumberFormat nf9 = new DecimalFormat("###,###,###");
	//Following util Object is defined by Achal Pathak ... On 22nd Nov., 2010 ...
	UtilBean util = new UtilBean();
		
	//Following (3) String Variables are defined by Achal Pathak ... On 22nd Nov., 2010 ...
	public String callFlag="";	
	public String cargo_ref_no="";
	public String year="";
	public String to_year="";
	public String trader_cd  ="";
	public String cargo_ref_cd = "";
	public String from_date ="";
	public String to_date = "";
	public String month = "";
	public String to_month = "";
	public Vector YEAR = new Vector();
	String mnth="";
	
	//	Following String Variable/s are defined by Achal Pathak ... On 24th Nov., 2010 ...
	String tea_tot_mt="";
	String tea_tot_scm="";
	String tea_tot_mmbtu="";
	String resel_tot_mt="";
	String resel_tot_scm="";
	String resel_tot_mmbtu="";	
	String pow_tot_mmbtu = "";
	String pow_tot_scm = "";
	String pow_tot_mt = "";
	String fer_tot_mmbtu = "";
	String fer_tot_scm = "";
	String fer_tot_mt = "";
	String pet_tot_mmbtu = "";
	String pet_tot_scm = "";
	String pet_tot_mt = "";
	String ref_tot_mmbtu = "";
	String ref_tot_scm = "";
	String ref_tot_mt = "";
	String oth_tot_mmbtu = "";
	String oth_tot_scm = "";
	String oth_tot_mt = "";
	String irn_tot_mmbtu = "";
	String irn_tot_scm = "";
	String irn_tot_mt = "";
	String tot_mmbtu = "";
	String tot_scm = "";
	String tot_mt = "";
	
	String tot_mmbtu1 = "";
	String tot_scm1 = "";
	String tot_mt1 = "";
	String tot_mmbtu2 = "";
	String tot_scm2 = "";
	String tot_mt2 = "";
	String tot_mmbtu3 = "";
	String tot_scm3 = "";
	String tot_mt3 = "";
	String tot_mmbtu4 = "";
	String tot_scm4 = "";
	String tot_mt4 = "";
	
	String guj_tot_mmbtu1 = "";
	String guj_tot_scm1 = "";
	String guj_tot_mt1 = "";
	String guj_tot_mmbtu2 = "";
	String guj_tot_scm2 = "";
	String guj_tot_mt2 = "";
	String guj_tot_mmbtu3 = "";
	String guj_tot_scm3 = "";
	String guj_tot_mt3 = "";
	String guj_tot_mmbtu4 = "";
	String guj_tot_scm4 = "";
	String guj_tot_mt4 = "";
		
	//Variable declaration for the mandate tracker by Achal: on 22nd November 2010	
	Vector CARGO_REF_CD = new Vector();
	Vector CUSTOMER_NM = new Vector();
	Vector CUSTOMER_CD = new Vector();
	
	Vector QTY_MT = new Vector();
	Vector GUJ_QTY_MT = new Vector();
	Vector CON_QTY_MT = new Vector();
	Vector GUJ_CON_QTY_MT = new Vector();
	Vector POW_QTY_MT = new Vector();
	Vector GUJ_POW_QTY_MT = new Vector();
	Vector FER_QTY_MT = new Vector();
	Vector GUJ_FER_QTY_MT = new Vector();
	Vector PET_QTY_MT = new Vector();
	Vector GUJ_PET_QTY_MT = new Vector();
	Vector REF_QTY_MT = new Vector();
	Vector GUJ_REF_QTY_MT = new Vector();
	Vector IRN_QTY_MT = new Vector();
	Vector GUJ_IRN_QTY_MT = new Vector();
	Vector OTH_QTY_MT = new Vector();
	Vector GUJ_OTH_QTY_MT = new Vector();
	
	//Following (4) Vectors Has Been Introduced By Samik Shah On 25th June, 2011 ...
	String TOTAL_QTY_SCM_NUMERIC = "";
	String TOTAL_QTY_MMBTU_NUMERIC = "";
	Vector QTY_MMBTU_NUMERIC = new Vector();
	Vector QTY_SCM_NUMERIC = new Vector();

	
	Vector QTY_MMBTU = new Vector();
	Vector GUJ_QTY_MMBTU = new Vector();
	Vector CON_QTY_MMBTU = new Vector();
	Vector GUJ_CON_QTY_MMBTU = new Vector();
	Vector POW_QTY_MMBTU = new Vector();
	Vector GUJ_POW_QTY_MMBTU = new Vector();
	Vector FER_QTY_MMBTU = new Vector();
	Vector GUJ_FER_QTY_MMBTU = new Vector();
	Vector PET_QTY_MMBTU = new Vector();
	Vector GUJ_PET_QTY_MMBTU = new Vector();
	Vector REF_QTY_MMBTU = new Vector();
	Vector GUJ_REF_QTY_MMBTU = new Vector();
	Vector IRN_QTY_MMBTU = new Vector();
	Vector GUJ_IRN_QTY_MMBTU = new Vector();
	Vector OTH_QTY_MMBTU = new Vector();
	Vector GUJ_OTH_QTY_MMBTU = new Vector();
		
	Vector QTY_SCM = new Vector();
	Vector GUJ_QTY_SCM = new Vector();
	Vector CON_QTY_SCM = new Vector();
	Vector GUJ_CON_QTY_SCM = new Vector();
	Vector POW_QTY_SCM = new Vector();
	Vector GUJ_POW_QTY_SCM = new Vector();
	Vector FER_QTY_SCM = new Vector();
	Vector GUJ_FER_QTY_SCM = new Vector();
	Vector PET_QTY_SCM = new Vector();
	Vector GUJ_PET_QTY_SCM = new Vector();
	Vector REF_QTY_SCM = new Vector();
	Vector GUJ_REF_QTY_SCM = new Vector();
	Vector IRN_QTY_SCM = new Vector();
	Vector GUJ_IRN_QTY_SCM = new Vector();
	Vector OTH_QTY_SCM = new Vector();
	Vector GUJ_OTH_QTY_SCM = new Vector();
	
	Vector AVG_GCV = new Vector();
	Vector AVG_CON_GCV = new Vector();
//	Following (2) Variable Has Been Introduced By Jaimin Patel On 05th Oct, 2011 ...
	boolean Flag1_sales=false;
	boolean Flag2_Re_Gas=false;
	//-----Vector Declared by Achal on 03/01/2011
	Vector SN_NO = new Vector();
	Vector SN_REF_NO = new Vector();
	Vector FGSA_NO = new Vector();
	Vector TCQ = new Vector();
	Vector TENDER_NO = new Vector();
	Vector LOA_NO = new Vector();
	Vector CONT_TYPE = new Vector();
	Vector COUNT =new Vector();
	Vector BALANCE =new Vector();	
	Vector CUSTOMER_CD_RE_GAS =new Vector();
	Vector CUSTOMER_NM_RE_GAS =new Vector(); 
	Vector RE_GAS_NO =new Vector();
	Vector REMARK_RE_GAS_NO =new Vector();
	Vector RE_GAS_CARGO_NO =new Vector();
	Vector CAPACITY =new Vector(); 
	Vector QTY_MMBTU_RE_GAS =new Vector(); 
	Vector BALANCE_RE_GAS =new Vector();	
	Vector TOTAL =new Vector();
	Vector TOTAL_RE_GAS =new Vector();
	
	//Following (6) Vectors Has Been Defined By Samik Shah On 14th February, 2011 ...
	public Vector INNER_CUSTOMER_CD_RE_GAS = new Vector();
	public Vector INNER_CUSTOMER_CD_SN_LOA = new Vector();
	public Vector RE_GAS_OUTER_COUNTER = new Vector();
	public Vector SN_LOA_OUTER_COUNTER = new Vector();
	public Vector FINAL_COUNT = new Vector();
	public Vector SEND_OUT_REMARK = new Vector();
	
	//Following (1) int Variable Has Been Defined By Samik Shah On 14th February, 2011 ...
	public int max_count_for_column = 0; 
	
	//Following (2) Vectors Has Been Defined By Samik Shah On 17th February, 2011 ...
	public Vector RE_GAS_TOTAL_BALANCE = new Vector();
	public Vector SN_LOA_TOTAL_BALANCE = new Vector();
	
	//Following (8) String Variables Has Been Defined By Samik Shah On 18th February, 2011 ...
	public String prev_month_sales = "";
	public String prev_month_regas = "";
	public String prev_month_total_sales = "";
	public String prev_month_sales_mcm = "";
	public String prev_month_regas_mcm = "";
	public String prev_month_total_sales_mcm = "";
	public String selected_month_year = "";
	public String selected_prev_month_year = "";
	
	//Following (32) String Variables Has Been Defined By Samik Shah On 19th February, 2011 ...
	public String month_opening_stock = "";
	public String month_opening_stock_mcm = "";
	public String month_opening_stock_regas = "";
	public String month_opening_stock_regas_mcm = "";
	public String month_opening_stock_sales = "";
	public String month_opening_stock_sales_mcm = "";
	public String dead_stock = "";
	public String dead_stock_mcm = "";
	public String volume_received_sales = "";
	public String volume_received_sales_mcm = "";
	public String volume_received_regas = "";
	public String volume_received_regas_mcm = "";
	public String volume_received_total = "";
	public String volume_received_total_mcm = "";
	public String month_sales = "";
	public String month_regas = "";
	public String month_total_sales = "";
	public String month_sales_mcm = "";
	public String month_regas_mcm = "";
	public String month_total_sales_mcm = "";
	public String volume_expected_sales = "";
	public String volume_expected_sales_mcm = "";
	public String volume_expected_regas = "";
	public String volume_expected_regas_mcm = "";
	public String volume_expected_total = "";
	public String volume_expected_total_mcm = "";
	public String internal_consumption_sales = "";
	public String internal_consumption_sales_mcm = "";
	public String internal_consumption_regas = "";
	public String internal_consumption_regas_mcm = "";
	public String internal_consumption_total = "";
	public String internal_consumption_total_mcm = "";
		
	//Following double Variable Has Been Defined By Samik Shah On 19th February, 2011 ...
	public double consumption_percentage = 2.0;
	
	//Following (20) String Variables Has Been Defined By Samik Shah On 21st February, 2011 ...
	public String outstanding_commitment_sales = "";
	public String outstanding_commitment_sales_mcm = "";
	public String outstanding_commitment_regas = "";
	public String outstanding_commitment_regas_mcm = "";
	public String outstanding_commitment_total = "";
	public String outstanding_commitment_total_mcm = "";
	public String re_commitment_sales = "";
	public String re_commitment_sales_mcm = "";
	public String internal_consumption_re_commitment_sales = "";
	public String internal_consumption_re_commitment_sales_mcm = "";
	public String net_uncommited_sales = "";
	public String net_uncommited_sales_mcm = "";
	public String net_uncommited_regas = "";
	public String net_uncommited_regas_mcm = "";
	public String net_uncommited_total = "";
	public String net_uncommited_total_mcm = "";
	public String net_uncommited_overcommited_sales = "";
	public String net_uncommited_overcommited_sales_mcm = "";
	public String net_uncommited_overcommited_total = "";
	public String net_uncommited_overcommited_total_mcm = "";
	
	//Following (8) Vectors Has Been Defined By Samik Shah On 25th February, 2011 ...
	public Vector QTY_MMSCM = new Vector();
	public Vector QTY_MMSCM_FIRM = new Vector();
	public Vector QTY_MMSCM_RE_SALES = new Vector();
	public Vector QTY_MMSCM_TOTAL_FIRM_RE = new Vector();
	public Vector QTY_MMSCM_RE_GAS = new Vector();
	public Vector QTY_MMSCM_RE_GAS_FIRM = new Vector();
	public Vector SALES_END_DT = new Vector();
	public Vector RE_GAS_END_DT = new Vector();
	
	//Following (5) String Variables are related to SN/LOA Contracts TOTAL -- Has Been Defined By Samik Shah On 25th February, 2011 ...
	public String total_sales_gas_day_mmbtu_qty_str = "";
	public String total_sales_gas_day_mmscm_qty_str = "";			
	public String sum_sales_firm_qty_str = "";
	public String sum_sales_re_qty_str = "";
	public String sum_sales_re_firm_qty_str = "";
	
	//Following (3) String Variables are related to Re-GAS Contracts TOTAL -- Has Been Defined By Samik Shah On 25th February, 2011 ...
	public String total_regas_gas_day_mmbtu_qty_str = "";
	public String total_regas_gas_day_mmscm_qty_str = "";
	public String total_regas_firm_mmscm_qty_str = "";
	
	//Following (2) String Variables are related to Future Cargo Arrival TOTAL -- Has Been Defined By Samik Shah On 25th February, 2011 ...
	public String total_expected_delivered_m3_qty_str = "";
	public String total_expected_delivered_mmscm_qty_str = "";
	
	//Both the following Vectors are Primary Vectors ... (Expected Cargo Arrivals)
	//Has Been Defined By Samik Shah On 26th February, 2011 ...
	public Vector EXP_CARGO_REF_NO = new Vector(); 
	public Vector EXP_VESSEL_NM = new Vector();
	
	//Following (4) Vectors are Secondary Vectors ... (Expected Cargo Arrivals)
	//Has Been Defined By Samik Shah On 26th February, 2011 ...
	public Vector EXP_OWN_TP_NM = new Vector(); 
	public Vector EXP_M3_LNG = new Vector();
	public Vector EXP_MMSCM_QTY = new Vector();
	public Vector EXP_TIME_OF_ARRIVAL = new Vector();
	
	//Following (8) String Variables are related to Tank Inventory on Daily Basis -- Has Been Defined By Samik Shah On 28th February, 2011 ...
	public String t1_m3_of_lng = "";
	public String t1_mmscm_of_gas = "";			
	public String t2_m3_of_lng = "";
	public String t2_mmscm_of_gas = "";
	public String saleable_stock_with_re_gas = "";
	public String saleable_stock = "";
	public String asking_rate_to_avoid_stock_out = "";
	public String internal_consumption_on_proposed_volume = "";
	
	//Following (3) String Variables Has Been Defined By Samik Shah On 1st March, 2011 ...
	public String last_arrival_date = "";
	public String long_short_position_mmscm = "";
	public String asking_rate_required_to_accommodate_cargo = "";
	
	
	Vector GAS_DT = new Vector();
	Vector TOTAL_QTY_SCM = new Vector();
	Vector TOTAL_QTY_MMBTU = new Vector();
	Vector GCV  = new Vector();
	Vector KCAL_SCM  = new Vector();
	String total = "";
	String total_regas = "";
	
	//Introduce By Milan 20111013
	Vector CUSTOMER_CD_REGAS =new Vector();
	Vector CUSTOMER_NM_REGAS =new Vector(); 
	Vector GAS_DT_REGAS = new Vector();
	Vector TOTAL_QTY_SCM_REGAS = new Vector();
	Vector TOTAL_QTY_MMBTU_REGAS = new Vector();
	Vector GCV_REGAS  = new Vector();
	Vector KCAL_SCM_REGAS  = new Vector();
//	Following (4) Vectors Has Been Introduced By mILAN On 13th oCT, 2011 ...
	String TOTAL_QTY_SCM_NUMERIC_REGAS = "";
	String TOTAL_QTY_MMBTU_NUMERIC_REGAS = "";
	Vector QTY_MMBTU_NUMERIC_REGAS = new Vector();
	Vector QTY_SCM_NUMERIC_REGAS = new Vector();
	Vector QTY_MMBTU_REGAS = new Vector();
	Vector QTY_SCM_REGAS = new Vector();
	String GRAND_TOTAL_MMBTU_NUMERIC = "";
	String GRAND_TOTAL_SCM_NUMERIC = "";
	
	//Following Variables Has Been Defined By Achal Pathak On 5th March, 2011 ...
	Vector CUST_CD  = new Vector();	
	Vector CUST_NM  = new Vector();
	Vector CALENDAR  = new Vector();
	Vector GIFT  = new Vector();
	Vector DIARY  = new Vector();
	Vector LEAFLET  = new Vector();
	Vector OTHER_1  = new Vector();
	Vector OTHER_2  = new Vector();

	Vector TRADER_CD  = new Vector();
	Vector TRADER_NAME  = new Vector();
	Vector TRANSPORTER_CD  = new Vector();
	Vector TRANSPORTER_NAME  = new Vector();
	Vector OTHER_CD  = new Vector();
	Vector OTHER_NAME  = new Vector();
	
	Vector SEQ_NO  = new Vector();
	Vector CONTACT_PERSON  = new Vector();
	Vector DESCRIPTION  = new Vector();
	
	Vector MONTH_VALUE = new Vector();
	
	String criteria="";
	String customer_cd="";
	int loop_count=0;
	
	//Following (2) String Variables Has Been Introduced By Samik Shah On 25th March, 2011 ...
	public String daily_graph_from_date ="";
	public String daily_graph_to_date ="";
	
//	FOLLOWING VECTOR HAVE BEEN DEFINED BY Milan Dalsaniya ON 22st NOV 2011...
//	 SUFFIX M TO THE VARIABLE STAND FOR THE MARGIN DTL VECTORS FOR SN
	Vector CUSTOMER_CD_SN_M=new Vector();
	Vector CUSTOMER_ABR_SN_M=new Vector();
	Vector FGSA_NO_M =new Vector();
	Vector FGSA_REV_NO_M =new Vector();
	Vector SN_NO_M =new Vector();
	Vector SN_REV_NO_M =new Vector();
	Vector START_DT_SN_M=new Vector();
	Vector END_DT_SN_M=new Vector();
	Vector RATE_SN_M=new Vector();
	Vector CARGO_TOT_MARGIN_SN_M=new Vector();

	Vector cargo_cnt = new Vector();
	Vector CARGO_REF_NO_SN_M=new Vector();
	Vector CARGO_ALLOC_QTY_SN_M=new Vector();
	Vector CARGO_COST_PRICE_SN_M=new Vector();
	Vector CARGO_MARGIN_SN_M=new Vector();
	Vector CARGO_NM_SN_M=new Vector();
	Vector SN_REF_NO_M=new Vector();
	
	String min_margin = "";
	String max_margin = "";
	String avg_margin = "";
	
//	FOLLOWING VECTOR HAVE BEEN DEFINED BY Milan Dalsaniya ON 22st NOV 2011...
//	 SUFFIX M TO THE VARIABLE STAND FOR THE MARGIN DTL VECTORS FOR CARGO
	Vector SELLER_CD  = new Vector();	
	Vector SELLER_NM  = new Vector();
	
	String Seller_cd = "";
	
	Vector CARGO_REF_CD_M=new Vector();
	Vector CARGO_NM_M = new Vector();
	Vector CARGO_PRICE_M = new Vector();
	Vector DELV_FROM_DT_M = new Vector();
	Vector DELV_TO_DT_M = new Vector();
	Vector TRD_CD_M = new Vector();
	Vector ACT_ARRV_DT_M = new Vector();
	Vector MAN_CONF_CD_M = new Vector();
	Vector MAN_CD_M = new Vector();
	
	Vector CARGO_ALLOC_TOT_M=new Vector();
	Vector CARGO_MARGIN_TOT_M = new Vector();
	Vector CARGO_VOL_M=new Vector();
	Vector CARGO_AVL_FOR_SALE_M=new Vector();
	Vector CARGO_BAL_M=new Vector();
	
	//Variables are declare for the methods which are called from other bean
	
	String delv_to_dt ="";
	double conf_price =0;
	double custom_dty = 0;

////FOr Parliament reports
	 String plant_st="GUJARAT";
	 String[][] sect_year_data;  
	 Vector SECTOR_CD=new Vector();
	 Vector SECTOR_NM=new Vector();
	 Vector SECTOR_CD_other=new Vector();
	 Vector SECTOR_NM_other=new Vector();
	 Vector SECTOR_CD1=new Vector();
	 Vector SECTOR_NM1=new Vector();
		 Vector Sel_com_mmbtu=new Vector();
	 Vector vsec_qty_val_btu[];
	 Vector vsec_qty_val_mt[];
	 Vector vsec_qty_val_scm[];
	 
	 Vector vsec_qty_val_btu_noguj[];
	 Vector vsec_qty_val_mt_noguj[];
	 Vector vsec_qty_val_scm_noguj[];
	 
	 Vector vsec_qty_val_btu_other[];
	 Vector vsec_qty_val_mt_other[];
	 Vector vsec_qty_val_scm_other[];
	 
	 Vector vsec_qty_val_btu_noguj_other[];
	 Vector vsec_qty_val_mt_noguj_other[];
	 Vector vsec_qty_val_scm_noguj_other[];
	 
	 Vector vsec_qty_val_btu1[];
	 Vector vsec_qty_val_mt1[];
	 Vector vsec_qty_val_scm1[];
	 
	 Vector vsec_qty_val_btu_noguj1[];
	 Vector vsec_qty_val_mt_noguj1[];
	 Vector vsec_qty_val_scm_noguj1[];
	 
	 //ADDDED FOR LTCORA AND CN (qTY TO BE SUPPLIED )
	 	Vector GAS_DT_LTCORA=new Vector();
		Vector TOTAL_QTY_MMBTU_LTCORA=new Vector();
		Vector TOTAL_QTY_SCM_LTCORA=new Vector();
		Vector GCV_LTCORA=new Vector();
		Vector KCAL_SCM_LTCORA=new Vector();
		String TOTAL_QTY_MMBTU_NUMERIC_LTCORA="";
		String TOTAL_QTY_SCM_NUMERIC_LTCORA="";
		Vector CUSTOMER_CD_LTCORA= new Vector();
		Vector QTY_MMBTU_LTCORA= new Vector();
		Vector QTY_SCM_LTCORA= new Vector();
		Vector QTY_MMBTU_NUMERIC_LTCORA= new Vector();
		Vector QTY_SCM_NUMERIC_LTCORA= new Vector();
		Vector CUSTOMER_NM_LTCORA= new Vector();
		String GRAND_TOTAL_MMBTU_NUMERIC_LTCORA="";
		String GRAND_TOTAL_SCM_NUMERIC_LTCORA="";
	 
		Vector LTCORA_TOTAL_UNLOADED_VOLUME = new Vector();
		Vector LTCORA_TOTAL_SUG = new Vector();
		Vector LTCORA_TOTAL_NET_COMMITTED = new Vector();
		Vector LTCORA_TOTAL_VOLUME_EXPECTED = new Vector();
		Vector LTCORA_TOTAL_VOLUME_SUPPLIED = new Vector();
		Vector LTCORA_TOTAL_UNLOADED_VOLUME_MCM = new Vector();
		Vector LTCORA_TOTAL_SUG_MCM = new Vector();
		Vector LTCORA_TOTAL_NET_COMMITTED_MCM = new Vector();
		Vector LTCORA_TOTAL_VOLUME_EXPECTED_MCM = new Vector();
		Vector LTCORA_TOTAL_VOLUME_SUPPLIED_MCM = new Vector();
		
		Vector REGAS_TOTAL_UNLOADED_VOLUME = new Vector();
		Vector REGAS_TOTAL_SUG = new Vector();
		Vector REGAS_TOTAL_NET_COMMITTED = new Vector();
		Vector REGAS_TOTAL_VOLUME_EXPECTED = new Vector();
		Vector REGAS_TOTAL_VOLUME_SUPPLIED = new Vector();
		Vector REGAS_TOTAL_UNLOADED_VOLUME_MCM = new Vector();
		Vector REGAS_TOTAL_SUG_MCM = new Vector();
		Vector REGAS_TOTAL_NET_COMMITTED_MCM = new Vector();
		Vector REGAS_TOTAL_VOLUME_EXPECTED_MCM = new Vector();
		Vector REGAS_TOTAL_VOLUME_SUPPLIED_MCM = new Vector();
		
		String volume_expected_LTCORA="";
		String volume_expected_LTCORA_mcm="";
		String volume_received_LTCORA="";
		String volume_received_LTCORA_mcm="";
		String month_opening_stock_LTCORA="";
		String month_opening_stock_LTCORA_mcm="";
		String internal_consumption_LTCORA="";
		String internal_consumption_LTCORA_mcm="";
		String month_LTCORA="";
		String month_LTCORA_mcm="";
		String prev_month_LTCORA="";
		String prev_month_LTCORA_mcm="";
		String prev_month_month_LTCORA_mcm="";
		
		String outstanding_commitment_LTCORA = "";
		String outstanding_commitment_LTCORA_mcm = "";
		String net_uncommited_LTCORA = "";
		String net_uncommited_LTCORA_mcm ="";
		
		
		Vector LTCORA_NO=new Vector();
		Vector LTCORA_CAPACITY=new Vector();
		Vector LTCORA_REMARK_RE_GAS_NO=new Vector();
		Vector LTCORA_CARGO_NO=new Vector();
		Vector INNER_CUSTOMER_CD_LTCORA=new Vector();
		Vector BALANCE_LTCORA=new Vector();
		Vector LTCORA_OUTER_COUNTER=new Vector();
		Vector LTCORA_TOTAL_BALANCE=new Vector();
		Vector LTCORA_CONT_TYPE=new Vector();
		//Vector QTY_MMBTU_LTCORA=new Vector();
	
		
		Vector QTY_MMSCM_LTCORA=new Vector();
		boolean Flag2_LTCORA=false;
		Vector LTCORA_END_DT=new Vector();
		Vector QTY_MMSCM_LTCORA_FIRM=new Vector();
		String total_LTCORA_gas_day_mmbtu_qty_str="";
		String total_LTCORA_gas_day_mmscm_qty_str="";
		String total_LTCORA_firm_mmscm_qty_str="";
		
		
		
	//Following Vector declared By JHP 20120521
	 Vector Vparty=new Vector();
	 Vector Vparty_cont_type=new Vector();
	public double getCustom_dty() {
		return custom_dty;
	}


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
	    			
	    			if(callFlag.equalsIgnoreCase("RLNG_IMPORT_FY_STATE"))
	    			{
	    		    	fetchRLngImportFinYearState();	    				
	    			}
	    			else if(callFlag.equalsIgnoreCase("RLNG_IMPORT_FY"))
	    			{
	    				AllocationDataFYwise(); //SB20180924
	    		    	fetchRLngImportFinYear();	    				
	    			}
	    			else if(callFlag.equalsIgnoreCase("Send_Out"))
	    			{
	    		    	fetchSendOut();	    				
	    			}
	    			else if(callFlag.equalsIgnoreCase("TOATL_QTY_SUPPLY"))
	    			{
	    		    	//fetchTotalQtySupply();
	    				 //Introduce by Milan 20111014
	    				AllocationDataFYwise(); //SB20180924
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					fetchTotalQtySupplyNew_WITH_ACCESS();
	    				} else {
	    					fetchTotalQtySupplyNew();
	    				}
	    			}
	    			else if(callFlag.equalsIgnoreCase("Daily_Report"))
	    			{
//	    				//System.out.println("---JAVA Customer_access_flag---: "+Customer_access_flag);
	    				createTable(); //RS29032017
	    				if(Customer_access_flag.equals("Y"))
	    				{
	    					fetchDailyReport_WITH_ACCESS();
		    		    	fetchaskingrate_WITH_ACCESS();//Modified By JHP20120106
	    				} 
	    				else 
	    				{
	    					fetchDailyReport();
		    		    	fetchaskingrate();//Modified By JHP20120106
	    				}
	    				fetchUserRemark(); //RS29032017
	    		    }
	    			else if(callFlag.equalsIgnoreCase("GIFT_TRACKING")) // Introduced by Achal on 05-03-2011
	    			{
	    				Customer_DTL();
	    				Trader_DTL();
	    				Transporter_DTL();
	    				Other_DTL();
	    		    	fetchGiftTracking();	    				
	    			}
	    			else if(callFlag.equalsIgnoreCase("GIFT_TRACKING_DETAILS")) // Introduced by Achal on 07-03-2011
	    			{
	    				fetchGiftTrackingDetails();
	    			}
	    			else if(callFlag.equalsIgnoreCase("SN_WISE_MARGIN"))  //MD20111122 
	    			{	
	    				Customer_DTL();
	    				fetchSNWiseMargin();
	    			}
					else if(callFlag.equalsIgnoreCase("CARGO_WISE_MARGIN"))  //MD20111123
	    			{	
	    				Seller_DTL();
	    				fetchCargoWiseMargin();
	    			}
					else if(callFlag.equalsIgnoreCase("FROM_OTHER_BEAN_CUSTOM_DUTY_CAL"))  //MD20111123
	    			{	
	    				custom_dty = CalCustomDutyOfCargo(delv_to_dt,conf_price);
	    			}
					else if(callFlag.equalsIgnoreCase("RLNG_IMPORT_FY_STATE_Sec"))
	    			{
//						AllocationDataFYwise(); //SB20180924
						sector_specification2();
	    				other_specification();
	    				sector_specification_reseller();
	    			}
					else if(callFlag.equalsIgnoreCase("RLNG_IMPORT_FY_STATE_Sec2"))
	    			{
						AllocationDataFYwise(); //SB20180924
						sector_specification2();
	    				other_specification();
	    				sector_specification_reseller();
	    			}
					else if(callFlag.equalsIgnoreCase("Send_Out_New_Sales"))
	    			{
						fetchSendOut_new_sales();	    				
	    			}
					//

	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }	    
	    catch(Exception e)
	    {
	    	////System.out.println("Exception:(DataBean_Cargo_Processing)-(init()): "+e);
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	if(rset != null)
	    	{
				try
				{ rset.close(); }
				catch(SQLException e)
				{ 
					e.printStackTrace();
					////System.out.println("rset is not close " + e); 
					}
			}
	    	if(rset1 != null)
	    	{
				try
				{ rset1.close(); }
				catch(SQLException e)
				{ e.printStackTrace();
					////System.out.println("rset1 is not close " + e); 
				}
			}
	    	if(rset2 != null)
	    	{
				try
				{ rset2.close(); }
				catch(SQLException e)
				{ e.printStackTrace(); 
				////System.out.println("rset2 is not close " + e); 
				}
			}
	    	if(rset3 != null)
	    	{
				try
				{ rset3.close(); }
				catch(SQLException e)
				{ e.printStackTrace();
					////System.out.println("rset1 is not close " + e); 
				}
			}
	    	if(rset4 != null)
	    	{
				try
				{ rset4.close(); }
				catch(SQLException e)
				{ e.printStackTrace(); 
				////System.out.println("rset2 is not close " + e); 
				}
			}
			if(stmt != null)
			{
				try
				{ stmt.close(); }
				catch(SQLException e)
				{ e.printStackTrace(); 
				////System.out.println("stmt is not close " + e); 
				}
			}
			if(stmt1 != null)
			{
				try
				{ stmt1.close(); }
				catch(SQLException e)
				{  e.printStackTrace(); 
				////System.out.println("stmt1 is not close " + e); 
				}
			}
			if(stmt2 != null)
			{
				try
				{ stmt2.close(); }
				catch(SQLException e)
				{ e.printStackTrace(); 
				////System.out.println("stmt2 is not close " + e); 
				}
			}
			if(stmt3 != null)
			{
				try
				{ stmt3.close(); }
				catch(SQLException e)
				{ e.printStackTrace(); 
				////System.out.println("stmt2 is not close " + e); 
				}
			}
			if(stmt4 != null)
			{
				try
				{ stmt4.close(); }
				catch(SQLException e)
				{ e.printStackTrace(); 
				////System.out.println("stmt2 is not close " + e); 
				}
			}
			if(conn != null)
			{
				try
				{ conn.close(); }
				catch(SQLException e)
				{ e.printStackTrace(); 
				////System.out.println("conn is not close " + e); 
				}
			}
	    }
	}
	
	Vector RE_GAS_TOTAL_BALANCE_MCM=new Vector();
	Vector LTCORA_TOTAL_BALANCE_MCM=new Vector();
	String selected_from_date1="";
	String selected_from_date="";
	
	public void fetchSendOut_new_sales() 
	{
		try
		{
			Vector temp_CUSTOMER_CD_SN = new Vector();
			Vector temp_CUSTOMER_CD_LOA = new Vector();
			Vector temp_CUSTOMER_CD = new Vector();
			
			double net_uncommited_total_volume = 0;
			double net_uncommited_total_mcm_volume = 0;
			String first_day_month = "01/"+from_date.substring(3);
			String before_15day_month = "";
			String end_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))+1);//12/08/2011
			String expected_volume_end_date = "";
			
			//////System.out.println("from_date"+from_date+"--"+end_date);
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')-15,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					before_15day_month = rset.getString(1);
				}
			}
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','DD/MM/YYYY'),'ddth Month-YY') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					selected_from_date = rset.getString(1);
					
				}
			}
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','DD/MM/YYYY'),'ddth Month, YYYY') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					selected_from_date1 = rset.getString(1);
					
				}
			}
			//////System.out.println("selected_from_date1 = "+selected_from_date1);
			
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+first_day_month+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				selected_month_year = rset.getString(1);
			}
			//////System.out.println("selected_month_year = "+selected_month_year);
			
			int mnth = Integer.parseInt(from_date.substring(3,5));
			String month = "";
			String year = "";
			if(mnth==1)
			{
				mnth = 12;
				month = "12";
				year = ""+(Integer.parseInt(from_date.substring(6))-1);
			}
			else
			{
				year = from_date.substring(6);
				mnth = mnth-1;
				if(mnth<10)
				{
					month = "0"+mnth;
				}
				else
				{
					month = ""+mnth;
				}
			}
			
			String previous_month_start_date = "01/"+month+"/"+year;
			double conversion_factor_mcm = 38900;
			
			double vol_exp_sales = 0;
			double vol_exp_sales1 = 0;
			double vol_exp_regas = 0;
			double vol_exp_total = 0;
			
			double vol_exp_LTCORA = 0; //ADDED FOR LTCORA AND CN
			double vol_recv_LTCORA = 0;
			double opening_stock_LTCORA = 0;
			double int_consumption_LTCORA = 0;
			double mon_LTCORA = 0;
			
			double opening_stock_for_ltcora_calc=0;
			
			double vol_recv_sales = 0;
			double vol_recv_regas = 0;
			double vol_recv_total = 0;
			
			double opening_stock_sales = 0;
			double opening_stock_regas = 0;
			double opening_stock = 0;
			
			double qty_to_be_supplied = 0;
			
			double int_consumption_sales = 0;
			double int_consumption_regas = 0;
			double int_consumption_total = 0;
			consumption_percentage = 2.0;
			
			double dead_stk = 700200;
			
			double month_total = 0;
			double mon_sales = 0;
			double mon_regas = 0;			
			
			//Following Logic is Updated By Milan Dalsaniya 20111011
			if(expected_volume_end_date.length()>=10)
			{
				//queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
				//			  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
				//			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
				
//				queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
//				  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
				queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
				  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
			
//				////System.out.println("Query for FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vol_exp_sales = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
//					////System.out.println("Jaimin::"+vol_exp_sales);
					//volume_expected_sales = nf3.format(vol_exp_sales);
					//volume_expected_sales_mcm = nf.format(vol_exp_sales/conversion_factor_mcm);
				}
//				queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
//				  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy') ) AND CARGO_STATUS='T' AND UNIT_CD='2'";
				queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
				  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy') ) AND CARGO_STATUS='T' AND UNIT_CD='2'";
			
			//	////System.out.println("Query for FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vol_exp_sales1 = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))*1000000));
					//////System.out.println("Jaimin::"+vol_exp_sales1);
					
				}
				
				volume_expected_sales = nf3.format(vol_exp_sales+vol_exp_sales1);
				volume_expected_sales_mcm = nf.format((vol_exp_sales+vol_exp_sales1)/conversion_factor_mcm);
				
//				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
//							  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//							  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')";
				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
				  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')";

			//	////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					/*vol_exp_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_expected_regas = nf3.format(vol_exp_regas);
					volume_expected_regas_mcm = nf.format(vol_exp_regas/conversion_factor_mcm);*/
					vol_exp_regas = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
					volume_expected_regas = nf3.format(vol_exp_regas);
					volume_expected_regas_mcm = nf.format(vol_exp_regas/conversion_factor_mcm);
					//////System.out.println("Jaimin::"+rset.getString(2));
				}
				
				
				////ADDED FOR LTCORA AND CN
				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS8_LNG_REGAS_CARGO_DTL A " +
				  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')";

			//	////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					/*vol_exp_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_expected_regas = nf3.format(vol_exp_regas);
					volume_expected_regas_mcm = nf.format(vol_exp_regas/conversion_factor_mcm);*/
					vol_exp_LTCORA = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
					volume_expected_LTCORA = nf3.format(vol_exp_LTCORA);
					volume_expected_LTCORA_mcm = nf.format(vol_exp_LTCORA/conversion_factor_mcm);
				}
				//END
				//////System.out.println("Jaimin::"+vol_exp_LTCORA);
				vol_exp_total = vol_exp_sales+vol_exp_sales1 + vol_exp_regas+vol_exp_LTCORA;//ADDED FOR LTCORA AND CN
				volume_expected_total = nf3.format(vol_exp_total);
				volume_expected_total_mcm = nf.format(vol_exp_total/conversion_factor_mcm);
			}
			
			if(from_date.length()>=10)
			{
				queryString = "SELECT SUM(B.QTY_MMBTU) FROM FMS7_CARGO_ARRIVAL_DTL A, FMS7_CARGO_QQ_DTL B " +
							  "WHERE A.CARGO_REF_NO=B.CARGO_REF_NO AND A.SPLIT_SEQ=B.SPLIT_SEQ AND A.SPLIT_SEQ='0' AND " +
							  "(A.ACT_ARRV_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
							  "TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			//	////System.out.println("Query for FMS7_CARGO_QQ_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vol_recv_sales = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_received_sales = nf3.format(vol_recv_sales);
					volume_received_sales_mcm = nf.format(vol_recv_sales/conversion_factor_mcm);
				}
				
//				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
//							  "WHERE A.ACTUAL_RECPT_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
//							  "TO_DATE('"+from_date+"','DD/MM/YYYY')";
//JHP20120717			
				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
				  "WHERE A.ACTUAL_RECPT_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+from_date+"','DD/MM/YYYY')";
			//	////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					/*vol_recv_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_received_regas =" nf3.format(vol_recv_regas);
					volume_received_regas_mcm = nf.format(vol_recv_regas/conversion_factor_mcm);*/
					vol_recv_regas = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
					volume_received_regas = nf3.format(vol_recv_regas);
					volume_received_regas_mcm = nf.format(vol_recv_regas/conversion_factor_mcm);
				}
				
				//ADDED FOR LTCORA AND CN
				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS8_LNG_REGAS_CARGO_DTL A " +
				  "WHERE A.ACTUAL_RECPT_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+from_date+"','DD/MM/YYYY')";
			//	////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					/*vol_recv_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_received_regas =" nf3.format(vol_recv_regas);
					volume_received_regas_mcm = nf.format(vol_recv_regas/conversion_factor_mcm);*/
					vol_recv_LTCORA = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
					volume_received_LTCORA = nf3.format(vol_recv_LTCORA);
					volume_received_LTCORA_mcm = nf.format(vol_recv_LTCORA/conversion_factor_mcm);
				}
				///END
				
				
				vol_recv_total = vol_recv_sales + vol_recv_regas+vol_recv_LTCORA;//ADDED FOR LTCORA AND CN
				volume_received_total = nf3.format(vol_recv_total);
				volume_received_total_mcm = nf.format(vol_recv_total/conversion_factor_mcm);
								
				
				Vector fgsa_NO = new Vector();
				Vector sn_NO = new Vector();
				Vector cust_CD = new Vector();
				int counter = 0;
				
				
				queryString2 = "SELECT A.PERCENTAGE " +
							   "FROM FMS7_CONSUMPTION_MST A WHERE " +
							   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
							   "B.EFF_DT<=TO_DATE('"+first_day_month+"','DD/MM/YYYY'))";
				
			//	////System.out.println("Internal Consumption Percentage Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					String int_consumption = rset2.getString(1)==null?"":rset2.getString(1);
					if(!int_consumption.trim().equals(""))
					{
						consumption_percentage = Double.parseDouble(int_consumption);
					}
				}
				
				consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
				
				queryString = "SELECT energy_mmbtu FROM FMS7_INVENTORY_OPENING_BALANCE " +
							  "WHERE opening_dt=TO_DATE('"+first_day_month+"','dd/mm/yyyy')";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_opening_stock = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
					opening_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					month_opening_stock_mcm = nf.format(opening_stock/conversion_factor_mcm);
//JHP20120829		int_consumption_total = ((opening_stock +vol_exp_total)*consumption_percentage)/100.00;
					int_consumption_total = ((opening_stock - dead_stk +vol_exp_total)*consumption_percentage)/100.00;

					internal_consumption_total = nf3.format(int_consumption_total);
					internal_consumption_total_mcm = nf.format(int_consumption_total/conversion_factor_mcm);
				}
				
				
				queryString = "SELECT RE_GAS_NO, CARGO_SEQ_NO, CUSTOMER_CD, QTY_TO_BE_SUPPLY " +
							  "FROM FMS7_RE_GAS_CARGO_DTL " +
							  "WHERE TO_DATE('"+first_day_month+"','DD/MM/YYYY') " +
							  "BETWEEN CONTRACT_START_DT AND CONTRACT_END_DT";
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					fgsa_NO.add(rset.getString(1)==null?"":rset.getString(1));
					sn_NO.add(rset.getString(2)==null?"":rset.getString(2));
					cust_CD.add(rset.getString(3)==null?"":rset.getString(3));
					qty_to_be_supplied += rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4))));
					++counter;
				}
				
				if(counter>0)
				{
					for(int i=0; i<counter; i++)
					{
						queryString = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   			  "where CONTRACT_TYPE='R' AND " +
						   			  "GAS_DT<TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						   			  "FGSA_NO='"+fgsa_NO.elementAt(i)+"' AND " +
						   			  "SN_NO='"+sn_NO.elementAt(i)+"' AND CUSTOMER_CD='"+cust_CD.elementAt(i)+"'";
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							qty_to_be_supplied -= rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1))));
						}
					}
					
					month_opening_stock_regas = nf3.format(qty_to_be_supplied);
					opening_stock_regas = Double.parseDouble(nf5.format(qty_to_be_supplied));
					month_opening_stock_regas_mcm = nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm);
				}
				
				if(qty_to_be_supplied!=0)
				{
					month_opening_stock_sales = nf3.format(opening_stock - qty_to_be_supplied);
					opening_stock_for_ltcora_calc=opening_stock - qty_to_be_supplied;//ADDED FOR LTCORA AND CN
					
					opening_stock_sales = Double.parseDouble(nf5.format(opening_stock - qty_to_be_supplied));
					month_opening_stock_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock/conversion_factor_mcm)) - Double.parseDouble(nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm)));
					
//JHP20120829	    int_consumption_sales = (((opening_stock - qty_to_be_supplied)+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
					int_consumption_sales = (((opening_stock - qty_to_be_supplied - dead_stk)+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;

					internal_consumption_sales = nf3.format(int_consumption_sales);
					internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
					
					int_consumption_regas = ((qty_to_be_supplied+vol_exp_regas)*consumption_percentage)/100.00;
					internal_consumption_regas = nf3.format(int_consumption_regas);
					internal_consumption_regas_mcm = nf.format(int_consumption_regas/conversion_factor_mcm);
				}
				else
				{
					month_opening_stock_sales = month_opening_stock;
					opening_stock_for_ltcora_calc=opening_stock;//ADDED FOR LTCORA AND CN
					
					opening_stock_sales = opening_stock;
					month_opening_stock_sales_mcm = month_opening_stock_mcm;
					
//JHP20120829		int_consumption_sales = ((opening_stock+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
					internal_consumption_sales = nf3.format(int_consumption_sales);
					internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
					
					int_consumption_regas = (vol_exp_regas*consumption_percentage)/100.00;
					internal_consumption_regas = nf3.format(int_consumption_regas);
					internal_consumption_regas_mcm = nf.format(int_consumption_regas/conversion_factor_mcm);
				}
				
				///ADDED FOR LTCORA AND CN
				Vector temp_mapping_id=new Vector();
				Vector temp_flag=new Vector();
				sn_NO.clear();
				cust_CD.clear();
				qty_to_be_supplied=0;
				counter=0;
				queryString = "SELECT MAPPING_ID, CARGO_SEQ_NO, FLAG, QTY_TO_BE_SUPPLY " +
				  "FROM FMS8_LNG_REGAS_CARGO_DTL " +
				  "WHERE TO_DATE('"+first_day_month+"','DD/MM/YYYY') " +
				  "BETWEEN CONTRACT_START_DT AND CONTRACT_END_DT";
			//	////System.out.println("++ queryString "+queryString);
					rset = stmt.executeQuery(queryString);
					while(rset.next())
					{
						temp_mapping_id.add(rset.getString(1)==null?"":rset.getString(1));
						String temp_mapid=rset.getString(1)==null?"":rset.getString(1);
						String tempid[]=temp_mapid.split("-");
						sn_NO.add(rset.getString(2)==null?"":rset.getString(2));
						temp_flag.add(rset.getString(3)==null?"":rset.getString(3));
						cust_CD.add(tempid[0]);
						qty_to_be_supplied += rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4))));
						++counter;
					}
				//	////System.out.println("temp_flag   "+qty_to_be_supplied+" counter  "+sn_NO);
					if(counter>0)
					{
						for(int i=0; i<counter; i++)
						{
							queryString = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			  "where CONTRACT_TYPE='"+temp_flag.elementAt(i)+"' AND " +
							   			  "GAS_DT<TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
							   			  "MAPPING_ID='"+temp_mapping_id.elementAt(i)+"' AND " +
							   			  "SN_NO='"+sn_NO.elementAt(i)+"' AND CUSTOMER_CD='"+cust_CD.elementAt(i)+"'";
						//	////System.out.println("--- queryString "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								qty_to_be_supplied -= rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1))));
							}
						}
						
						month_opening_stock_LTCORA = nf3.format(qty_to_be_supplied);
						opening_stock_LTCORA = Double.parseDouble(nf5.format(qty_to_be_supplied));
						month_opening_stock_LTCORA_mcm = nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm);
					}
					
				//	////System.out.println("qty_to_be_supplied  "+qty_to_be_supplied+" vol_exp_LTCORA "+vol_exp_LTCORA+" consumption_percentage "+consumption_percentage);
				//	////System.out.println((qty_to_be_supplied+vol_exp_LTCORA)*consumption_percentage);
					
					
					if(qty_to_be_supplied!=0)
					{
						month_opening_stock_sales = nf3.format(opening_stock_for_ltcora_calc - qty_to_be_supplied);
						opening_stock_sales = Double.parseDouble(nf5.format(opening_stock_for_ltcora_calc - qty_to_be_supplied));
						month_opening_stock_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_for_ltcora_calc/conversion_factor_mcm)) - Double.parseDouble(nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm)));
						
//	JHP20120829	    int_consumption_sales = (((opening_stock - qty_to_be_supplied)+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
						int_consumption_sales = (((opening_stock_for_ltcora_calc - qty_to_be_supplied - dead_stk)+vol_exp_sales+vol_exp_LTCORA+vol_exp_sales1)*consumption_percentage)/100.00;

						internal_consumption_sales = nf3.format(int_consumption_sales);
						internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
						
						int_consumption_LTCORA = ((qty_to_be_supplied+vol_exp_LTCORA)*consumption_percentage)/100.00;
						internal_consumption_LTCORA = nf3.format(int_consumption_LTCORA);
						internal_consumption_LTCORA_mcm = nf.format(int_consumption_LTCORA/conversion_factor_mcm);
					}
					else
					{
						month_opening_stock_sales = ""+opening_stock_for_ltcora_calc;
						opening_stock_sales = opening_stock_for_ltcora_calc;
						month_opening_stock_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_for_ltcora_calc/conversion_factor_mcm)));
						
//	JHP20120829		int_consumption_sales = ((opening_stock+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
						internal_consumption_sales = nf3.format(int_consumption_sales);
						internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
						
						int_consumption_LTCORA = (vol_exp_LTCORA*consumption_percentage)/100.00;
						internal_consumption_LTCORA = nf3.format(int_consumption_LTCORA);
						internal_consumption_LTCORA_mcm = nf.format(int_consumption_LTCORA/conversion_factor_mcm);
					}
				//	////System.out.println("internal_consumption_LTCORA  "+internal_consumption_LTCORA);
				/////END 
					
					
				queryString = "SELECT TANK1_D1_VOLUME, TANK2_D1_VOLUME, TANK_DTL_DT " +
							  "FROM FMS7_TANK_MASTER_DTL " +
							  "WHERE TANK_DTL_DT<TO_DATE('"+first_day_month+"','DD/MM/YYYY') " +
							  "ORDER BY TANK_DTL_DT DESC";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					dead_stk = rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1))));
					dead_stk += rset.getString(2)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(2))));
					dead_stk = 700200;				
				}
				dead_stock = nf3.format(dead_stk);
				dead_stock_mcm = nf.format(dead_stk/conversion_factor_mcm);
								
				
				
				String dd=from_date.substring(0,2);
				if(Integer.parseInt(dd)<=15)
				{
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
				}
				else
				{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";	
				}
			//	////System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					selected_prev_month_year = rset.getString(2);
//				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
//							   "where CONTRACT_TYPE='R' AND " +
//							   "GAS_DT<=TO_DATE('"+from_date.trim()+"','DD/MM/YYYY') AND " +
//							   "GAS_DT>=TO_DATE('"+first_day_month+"','DD/MM/YYYY')";
					if(Integer.parseInt(dd)<=15)
					{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CONTRACT_TYPE='R' AND " +
								   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
								   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
					}
					else
					{
						queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where CONTRACT_TYPE='R' AND " +
						   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
						   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
			
					}
			//	////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					month_regas = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
					mon_regas = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					month_regas_mcm = nf.format(mon_regas/conversion_factor_mcm);
					month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				}
				
				
//				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
//							   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
//							   "GAS_DT<=TO_DATE('"+from_date.trim()+"','DD/MM/YYYY') AND " +
//							   "GAS_DT>=TO_DATE('"+first_day_month+"','DD/MM/YYYY')";
				if(Integer.parseInt(dd)<=15)
				{
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
							   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
							   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
				}
				else
				{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
					   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
					   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
					   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
		
				}
			//	////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					month_sales = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
					mon_sales = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					month_sales_mcm = nf.format(mon_sales/conversion_factor_mcm);
					month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				}					
				
				///ADDED FOR LTCORA AND CN
				if(Integer.parseInt(dd)<=15)
				{
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
							   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
							   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
				}
				else
				{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
					   "where (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
					   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
					   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
		
				}
			//	////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					month_LTCORA = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
					mon_LTCORA = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					month_LTCORA_mcm = nf.format(mon_sales/conversion_factor_mcm);
					month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				}					
				///END
				
				month_total_sales = nf3.format(month_total);
				month_total_sales_mcm = nf.format(month_total/conversion_factor_mcm);
				}
			}
			
			if(previous_month_start_date.length()>=10)
			{
				String dd=from_date.substring(0,2);
				
				if(Integer.parseInt(dd)<=15)
				{
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
				}
				else
				{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";	
				}
			//	////System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					selected_prev_month_year = rset.getString(2);
					double prev_month_total = 0;
					double pre_mon_sales = 0;
					double pre_mon_regas = 0;
					double pre_mon_LTCORA= 0;
					if(Integer.parseInt(dd)<=15)
					{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CONTRACT_TYPE='R' AND " +
								   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
								   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
					}
					else
					{
						queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where CONTRACT_TYPE='R' AND " +
						   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
						   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
			
					}
					//////System.out.println("QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						prev_month_regas = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
						pre_mon_regas = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						prev_month_regas_mcm = nf.format(pre_mon_regas/conversion_factor_mcm);
						prev_month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					}
					
					if(Integer.parseInt(dd)<=15)
					{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
								   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
								   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
					}
					else
					{
						queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
						   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
						   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
			
					}
					//////System.out.println("QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						prev_month_sales = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
						pre_mon_sales = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						prev_month_sales_mcm = nf.format(pre_mon_sales/conversion_factor_mcm);
						prev_month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					}	
					
					
					//ADDED FOR LTCORA AND CN
					if(Integer.parseInt(dd)<=15)
					{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
								   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
								   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
					}
					else
					{
						queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
						   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
						   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
			
					}
					//////System.out.println("QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						prev_month_LTCORA = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
						pre_mon_LTCORA = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						prev_month_LTCORA_mcm = nf.format(pre_mon_LTCORA/conversion_factor_mcm);
						prev_month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					}
					/////END
					
					
					prev_month_total_sales = nf3.format(prev_month_total);
					prev_month_total_sales_mcm = nf.format(prev_month_total/conversion_factor_mcm);
				}
			}
			
			
			//Following Coding Is Related to Re-Gas Contracts ...
			double outstanding_commit_regas = 0;
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "(A.CONTRACT_START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.CONTRACT_END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy'))  " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.CONTRACT_START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.CONTRACT_START_DT) AND " +
						  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
			
			//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
						  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
			queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
			  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
		//	////System.out.println(">>>>Re-Gas Customer Code"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));				
			}
			
			//All Customer's Data Involved In LIVE Re-Gas Contracts depends upon Selected Period ...
			for(int i=0;i<CUSTOMER_CD_RE_GAS.size();i++)
			{				
				Vector tmp_RE_GAS_NO = new Vector();
				Vector tmp_CAPACITY = new Vector();
				Vector tmp_RE_GAS_CARGO_NO = new Vector();
				Vector tmp_INNER_CUSTOMER_CD_RE_GAS = new Vector();
				Vector tmp_QTY_MMBTU = new Vector();
				Vector tmp_QTY_BALANCE = new Vector();
				Vector tmp_REMARK_RE_GAS = new Vector();
				String tmp_RE_GAS_CLOSURE_DT = "";
				
				double total_balance = 0;
				
				queryString = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
							  "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
			//	////System.out.println(">>>>Customer for ReGas Name"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CUSTOMER_NM_RE_GAS.add(rset.getString(2)==null?"":rset.getString(2));				
				}
				else
				{
					CUSTOMER_NM_RE_GAS.add("");
				}
				
				int count = 0;
				
				double adq_qty, sug, edq_qty, qty_to_supply = 0, supplied = 0;
				double total_adq_qty = 0, total_sug = 0, total_edq_qty = 0, total_qty_to_supply = 0, total_supplied = 0;
				
				
				queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD, " +
				  "NVL(A.REGAS_CLOSURE_FLAG, 'N'),TO_CHAR(A.REGAS_CLOSURE_DT,'DD/MM/YYYY'),"
				  + "NVL(A.ADQ_QTY,'0'),NVL(A.SYS_USE_GAS,'0'),NVL(A.EDQ_QTY,'0'),NVL(A.QTY_TO_BE_SUPPLY,'0') "
				  + "from FMS7_RE_GAS_CARGO_DTL A where "+			
				  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
				  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
				  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
				  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
				  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
			//	////System.out.println(">>>>Re-Gas Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				//BALANCE_RE_GAS	QTY_MMBTU_RE_GAS	CAPACITY
				while(rset.next())
				{
					tmp_RE_GAS_NO.add(rset.getString(1)==null?"":rset.getString(1));
					tmp_CAPACITY.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					tmp_RE_GAS_CARGO_NO.add(rset.getString(3)==null?"":rset.getString(3));
					tmp_INNER_CUSTOMER_CD_RE_GAS.add(rset.getString(4)==null?"0":rset.getString(4));
					tmp_RE_GAS_CLOSURE_DT = rset.getString(6)==null?"0":rset.getString(6);
					
					adq_qty = rset.getDouble(10);
					sug = rset.getDouble(11);
					edq_qty = rset.getDouble(12);
					qty_to_supply = rset.getDouble(13);
					
					if(adq_qty == 0)
					{
						sug = (edq_qty * sug) / 100;
					} else {
						sug = (adq_qty * sug) / 100;
					}
					
					total_adq_qty += adq_qty;
					total_sug += sug;
					total_edq_qty += edq_qty;
					total_qty_to_supply += qty_to_supply;
					
					double balance = 0;
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(3)+"' AND FGSA_NO='"+rset.getString(1)+"' " +
								   "AND CONTRACT_TYPE='R' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
				//	////System.out.println(">>>>QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					//	Introduce By Milan Dalsaniya 20111012					
					if(rset1.next())
					{
						supplied = rset1.getDouble(1);
						total_supplied += supplied;
						tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						String tmp = "";
						if (!tmp_RE_GAS_CLOSURE_DT.equals("0")){
						//	////System.out.println("from if of dual >>>>>>> ");
							queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_RE_GAS_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
						//	////System.out.println(">>>>Dual = "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if (rset2.next())
							{
								tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
						//		////System.out.println("Count >>>>>>>> "+tmp);
							}
						}
						
						if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							balance = balance*-1;
							tmp_REMARK_RE_GAS.add("Over supplied by "+nf3.format(balance)+" Mmbtus");
							balance = 0;
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
						{
							tmp_REMARK_RE_GAS.add("Short closed by "+nf3.format(balance)+" Mmbtus");	
							tmp_QTY_BALANCE.add("-");
							balance = 0;
							//tmp_REMARK_RE_GAS.add("");
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance==0)
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK_RE_GAS.add("Closed");
							balance = 0;
						}
						else
						{
							if (balance < 0 )
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK_RE_GAS.add("Excess supplied by "+nf3.format(balance*(-1))+" Mmbtus");
								balance = 0;
							}
							else if (balance == 0 )
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK_RE_GAS.add("Closed");
								balance = 0;
							}
							else
							{
								tmp_QTY_BALANCE.add(""+nf3.format(balance));
								tmp_REMARK_RE_GAS.add("Short closed by "+nf3.format(balance)+" Mmbtus");
							}
						}
					}
					else
					{
						tmp_REMARK_RE_GAS.add("");
						tmp_QTY_MMBTU.add("");
						balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						tmp_QTY_BALANCE.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					}
					total_balance += balance;
					++count;
				}
				
				if(count==0)
				{
					tmp_RE_GAS_NO.add("");
					tmp_REMARK_RE_GAS.add("");
					tmp_CAPACITY.add("");
					tmp_RE_GAS_CARGO_NO.add("");
					tmp_QTY_MMBTU.add("");
					tmp_QTY_BALANCE.add("");
					tmp_INNER_CUSTOMER_CD_RE_GAS.add(""+CUSTOMER_CD_RE_GAS.elementAt(i));
				}
				
				RE_GAS_NO.add(tmp_RE_GAS_NO);				
				CAPACITY.add(tmp_CAPACITY);
				REMARK_RE_GAS_NO.add(tmp_REMARK_RE_GAS);
				RE_GAS_CARGO_NO.add(tmp_RE_GAS_CARGO_NO);
				INNER_CUSTOMER_CD_RE_GAS.add(tmp_INNER_CUSTOMER_CD_RE_GAS);
				QTY_MMBTU_RE_GAS.add(tmp_QTY_MMBTU);
				BALANCE_RE_GAS.add(tmp_QTY_BALANCE);
				RE_GAS_OUTER_COUNTER.add(""+count);
				RE_GAS_TOTAL_BALANCE.add(nf3.format(total_balance));
				RE_GAS_TOTAL_BALANCE_MCM.add(nf.format(total_balance/conversion_factor_mcm));
				outstanding_commit_regas += Double.parseDouble(nf5.format(total_balance));
				

				REGAS_TOTAL_UNLOADED_VOLUME.add(total_adq_qty==0?"-":nf3.format(total_adq_qty));
				REGAS_TOTAL_SUG.add(total_sug==0?"-":nf3.format(total_sug));
				REGAS_TOTAL_NET_COMMITTED.add(total_edq_qty==0?"-":nf3.format(total_edq_qty));
				REGAS_TOTAL_VOLUME_EXPECTED.add(total_qty_to_supply==0?"-":nf3.format(total_qty_to_supply));
				REGAS_TOTAL_VOLUME_SUPPLIED.add(total_supplied==0?"-":nf3.format(total_supplied));
				
				REGAS_TOTAL_UNLOADED_VOLUME_MCM.add((total_adq_qty/conversion_factor_mcm)==0?"-":nf.format(total_adq_qty/conversion_factor_mcm));
				REGAS_TOTAL_SUG_MCM.add((total_sug/conversion_factor_mcm)==0?"-":nf.format(total_sug/conversion_factor_mcm));
				REGAS_TOTAL_NET_COMMITTED_MCM.add((total_edq_qty/conversion_factor_mcm)==0?"-":nf.format(total_edq_qty/conversion_factor_mcm));
				REGAS_TOTAL_VOLUME_EXPECTED_MCM.add((total_qty_to_supply/conversion_factor_mcm)==0?"-":nf.format(total_qty_to_supply/conversion_factor_mcm));
				REGAS_TOTAL_VOLUME_SUPPLIED_MCM.add((total_supplied/conversion_factor_mcm)==0?"-":nf.format(total_supplied/conversion_factor_mcm));
			}
			outstanding_commitment_regas = nf3.format(outstanding_commit_regas);
			outstanding_commitment_regas_mcm = nf.format(outstanding_commit_regas/conversion_factor_mcm);
			
			
			////System.out.println("QTY_MMBTU_RE_GAS"+QTY_MMBTU_RE_GAS);
			
			////////////////////ADDED FOR LTCORA AND CN
			
//			Following Coding Is Related to LTCORA Contracts ...
			double outstanding_commit_LTCORA = 0;
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "(A.CONTRACT_START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.CONTRACT_END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy'))  " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.CONTRACT_START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.CONTRACT_START_DT) AND " +
						  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
			
			//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
						  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
			  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			queryString="select DISTINCT(C.CUSTOMER_CD) from FMS8_LNG_REGAS_CARGO_DTL A, " +
					"FMS8_LNG_REGAS_MST C where ((to_date('"+before_15day_month+"','dd/mm/yyyy') between  " +
					"A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN  " +
					"TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
					"AND A.MAPPING_ID=C.MAPPING_ID  AND " +
					"C.cn_rev_no=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B where C.agreement_no=B.agreement_no " +
					"AND C.customer_cd=B.customer_cd  AND B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND " +
					"B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE  D.agreement_no=B.agreement_no AND " +
					"B.customer_cd=D.customer_cd  AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) " +
					"AND a.mapping_id=C.mapping_id order by C.CUSTOMER_CD ";
			
		//	////System.out.println(">>>>LTCORA AND CN Customer Code"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				CUSTOMER_CD_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));				
			}
			
			//All Customer's Data Involved In LIVE Re-Gas Contracts depends upon Selected Period ...
			for(int i=0;i<CUSTOMER_CD_LTCORA.size();i++)
			{				
				Vector tmp_RE_GAS_NO = new Vector();
				Vector tmp_CAPACITY = new Vector();
				Vector tmp_RE_GAS_CARGO_NO = new Vector();
				Vector tmp_INNER_CUSTOMER_CD_RE_GAS = new Vector();
				Vector tmp_QTY_MMBTU = new Vector();
				Vector tmp_QTY_BALANCE = new Vector();
				Vector tmp_REMARK_RE_GAS = new Vector();
				String tmp_RE_GAS_CLOSURE_DT = "";
				Vector temp_mapping_id=new Vector();
				Vector temp_ltcora_cont_type=new Vector();
				
				double total_balance = 0;
				
				queryString = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
							  "where CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
			//	////System.out.println(">>>>Customer for ReGas Name"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CUSTOMER_NM_LTCORA.add(rset.getString(2)==null?"":rset.getString(2));				
				}
				else
				{
					CUSTOMER_NM_LTCORA.add("");
				}
				
				int count = 0;
				double adq_qty, sug, edq_qty, qty_to_supply = 0, supplied = 0;
				double total_adq_qty = 0, total_sug = 0, total_edq_qty = 0, total_qty_to_supply = 0, total_supplied = 0;
				
				queryString="select A.MAPPING_ID,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.FLAG, " +
				"NVL(A.REGAS_CLOSURE_FLAG, 'N'),TO_CHAR(A.REGAS_CLOSURE_DT,'DD/MM/YYYY'), " +
				"A.REGAS_TARIF ,A.cargo_ref_no ,C.CN_NO, "
				+ "NVL(A.ADQ_QTY,'0'),NVL(A.SYS_USE_GAS,'1'),NVL(A.EDQ_QTY,'0'),NVL(A.QTY_TO_BE_SUPPLY,'0') "
				+ "from FMS8_LNG_REGAS_CARGO_DTL A, " +
				"FMS8_LNG_REGAS_MST C where ((to_date('"+before_15day_month+"','dd/mm/yyyy') between  " +
				"A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN  " +
				"TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				"AND A.MAPPING_ID=C.MAPPING_ID  AND C.CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' AND " +
				"C.cn_rev_no=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B where C.agreement_no=B.agreement_no " +
				"AND C.customer_cd=B.customer_cd  AND B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND " +
				"B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE  D.agreement_no=B.agreement_no AND " +
				"B.customer_cd=D.customer_cd  AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) " +
				"AND a.mapping_id=C.mapping_id order by C.CUSTOMER_CD ";
				
				////System.out.println(">>>>LTCORA N CN Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				//BALANCE_RE_GAS	QTY_MMBTU_RE_GAS	CAPACITY
				while(rset.next())
				{
					temp_mapping_id.add(rset.getString(1)==null?"":rset.getString(1));
					String temp_map_id=rset.getString(1)==null?"":rset.getString(1);
					String map_id[]=temp_map_id.split("-");
					String tempconttype=rset.getString(4)==null?"T":rset.getString(4);
					String temp_re_gas_no_ltcora="";
					
					if(tempconttype.equalsIgnoreCase("T"))
					{
						tmp_RE_GAS_NO.add(map_id[1]);
						temp_re_gas_no_ltcora=map_id[1];
						temp_ltcora_cont_type.add("LTCORA");
					}
					else if(tempconttype.equalsIgnoreCase("C"))
					{
						tmp_RE_GAS_NO.add(map_id[3]);
						temp_re_gas_no_ltcora=map_id[3];
						temp_ltcora_cont_type.add("CN");
					}
						
					adq_qty = rset.getDouble(10);
					sug = rset.getDouble(11);
					edq_qty = rset.getDouble(12);
					qty_to_supply = rset.getDouble(13);
					
					if(adq_qty == 0)
					{
						sug = (edq_qty * sug) / 100;
					} else {
						sug = (adq_qty * sug) / 100;
					}
					
					total_adq_qty += adq_qty;
					total_sug += sug;
					total_edq_qty += edq_qty;
					total_qty_to_supply += qty_to_supply;
					
					//tmp_RE_GAS_NO.add(rset.getString(1)==null?"":rset.getString(1));
					tmp_CAPACITY.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					tmp_RE_GAS_CARGO_NO.add(rset.getString(3)==null?"":rset.getString(3));
					tmp_INNER_CUSTOMER_CD_RE_GAS.add(map_id[0]);
					tmp_RE_GAS_CLOSURE_DT = rset.getString(6)==null?"0":rset.getString(6);
					
				//	////System.out.println("tmp_RE_GAS_NO "+tmp_RE_GAS_NO+" tempconttype "+tempconttype);
					double balance = 0;
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(3)+"' AND FGSA_NO='"+temp_re_gas_no_ltcora+"' " +
								   "AND CONTRACT_TYPE='"+tempconttype+"' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
								   "AND MAPPING_ID='"+temp_map_id+"'";
					
				//	////System.out.println(">>>>QTY_MMBTU ltcora = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					//	Introduce By Milan Dalsaniya 20111012					
					if(rset1.next())
					{
						supplied = rset1.getDouble(1);
						total_supplied += supplied;
						tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						String tmp = "";
						if (!tmp_RE_GAS_CLOSURE_DT.equals("0")){
						//	//System.out.println("from if of dual >>>>>>> ");
							queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_RE_GAS_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
						//	//System.out.println(">>>>Dual = "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if (rset2.next())
							{
								tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
								////System.out.println("Count >>>>>>>> "+tmp);
							}
						}
						
						if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							balance = balance*-1;
							tmp_REMARK_RE_GAS.add("Over supplied by "+nf3.format(balance)+" Mmbtus");
							balance = 0;
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
						{
							tmp_REMARK_RE_GAS.add("Short closed by "+nf3.format(balance)+" Mmbtus");	
							tmp_QTY_BALANCE.add("-");
							balance = 0;
							//tmp_REMARK_RE_GAS.add("");
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance==0)
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK_RE_GAS.add("Closed");
							balance = 0;
						}
						else
						{
							if (balance < 0 )
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK_RE_GAS.add("Excess supplied by "+nf3.format(balance*(-1))+" Mmbtus");
								balance = 0;
							}
							else if (balance == 0 )
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK_RE_GAS.add("Closed");
								balance = 0;
							}
							else
							{
								tmp_QTY_BALANCE.add(""+nf3.format(balance));
								tmp_REMARK_RE_GAS.add("Short closed by "+nf3.format(balance)+" Mmbtus");
							}
						}
					}
					else
					{
						tmp_REMARK_RE_GAS.add("");
						tmp_QTY_MMBTU.add("");
						balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						tmp_QTY_BALANCE.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					}
					total_balance += balance;
					++count;
				}
				
				if(count==0)
				{
					tmp_RE_GAS_NO.add("");
					tmp_REMARK_RE_GAS.add("");
					tmp_CAPACITY.add("");
					tmp_RE_GAS_CARGO_NO.add("");
					tmp_QTY_MMBTU.add("");
					tmp_QTY_BALANCE.add("");
					tmp_INNER_CUSTOMER_CD_RE_GAS.add(""+CUSTOMER_CD_RE_GAS.elementAt(i));
				}
				
				LTCORA_NO.add(tmp_RE_GAS_NO);	
				LTCORA_CONT_TYPE.add(temp_ltcora_cont_type);
				LTCORA_CAPACITY.add(tmp_CAPACITY);
				LTCORA_REMARK_RE_GAS_NO.add(tmp_REMARK_RE_GAS);
				LTCORA_CARGO_NO.add(tmp_RE_GAS_CARGO_NO);
				INNER_CUSTOMER_CD_LTCORA.add(tmp_INNER_CUSTOMER_CD_RE_GAS);
				QTY_MMBTU_LTCORA.add(tmp_QTY_MMBTU);
				BALANCE_LTCORA.add(tmp_QTY_BALANCE);
				LTCORA_OUTER_COUNTER.add(""+count);
				LTCORA_TOTAL_BALANCE.add(nf3.format(total_balance));
				LTCORA_TOTAL_BALANCE_MCM.add(nf.format(total_balance/conversion_factor_mcm));
				outstanding_commit_LTCORA += Double.parseDouble(nf5.format(total_balance));
				
				
				LTCORA_TOTAL_UNLOADED_VOLUME.add(total_adq_qty==0?"-":nf3.format(total_adq_qty));
				LTCORA_TOTAL_SUG.add(total_sug==0?"-":nf3.format(total_sug));
				LTCORA_TOTAL_NET_COMMITTED.add(total_edq_qty==0?"-":nf3.format(total_edq_qty));
				LTCORA_TOTAL_VOLUME_EXPECTED.add(total_qty_to_supply==0?"-":nf3.format(total_qty_to_supply));
				LTCORA_TOTAL_VOLUME_SUPPLIED.add(total_supplied==0?"-":nf3.format(total_supplied));
				
				LTCORA_TOTAL_UNLOADED_VOLUME_MCM.add((total_adq_qty/conversion_factor_mcm)==0?"-":nf.format(total_adq_qty/conversion_factor_mcm));
				LTCORA_TOTAL_SUG_MCM.add((total_sug/conversion_factor_mcm)==0?"-":nf.format(total_sug/conversion_factor_mcm));
				LTCORA_TOTAL_NET_COMMITTED_MCM.add((total_edq_qty/conversion_factor_mcm)==0?"-":nf.format(total_edq_qty/conversion_factor_mcm));
				LTCORA_TOTAL_VOLUME_EXPECTED_MCM.add((total_qty_to_supply/conversion_factor_mcm)==0?"-":nf.format(total_qty_to_supply/conversion_factor_mcm));
				LTCORA_TOTAL_VOLUME_SUPPLIED_MCM.add((total_supplied/conversion_factor_mcm)==0?"-":nf.format(total_supplied/conversion_factor_mcm));
			}
			outstanding_commitment_LTCORA = nf3.format(outstanding_commit_LTCORA);
			outstanding_commitment_LTCORA_mcm = nf.format(outstanding_commit_LTCORA/conversion_factor_mcm);
			
			
			
			//System.out.println("QTY_MMBTU_LTCORA"+QTY_MMBTU_LTCORA);
			
			////////////////////END
			
			
			queryString =  "select DISTINCT(A.CUSTOMER_CD) from FMS7_SN_MST A where " +
			   "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
			   "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
			   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
			   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
			   "ORDER BY A.CUSTOMER_CD";
		//	//System.out.println("SN Customer Code = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				temp_CUSTOMER_CD_SN.add(rset.getString(1)==null?"":rset.getString(1));				
			}
			
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
						  "(A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT) AND " +
						  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
						  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
			
//			FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
						  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
						  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...

			queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
			  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
			  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
			
		//	//System.out.println("LOA Customer Code = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				temp_CUSTOMER_CD_LOA.add(rset.getString(1)==null?"":rset.getString(1));
			}
			
			for(int i=0;i<temp_CUSTOMER_CD_SN.size();i++)
			{	
				for(int j=0;j<temp_CUSTOMER_CD_LOA.size();j++)
				{
					if((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD_LOA.elementAt(j)).trim()))
					{
						temp_CUSTOMER_CD.add((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim());
						break;
					}
				}
			}
			
			for(int i=0;i<temp_CUSTOMER_CD_SN.size();i++)
			{
				int inner_count = 0;
				for(int j=0;j<temp_CUSTOMER_CD.size();j++)
				{
					if((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD.elementAt(j)).trim()))
					{
						++inner_count;
						break;
					}					
				}
				
				if(inner_count==0)
				{
					CUSTOMER_CD.add((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim());
				}
			}
			
			for(int i=0;i<temp_CUSTOMER_CD_LOA.size();i++)
			{
				int inner_count = 0;
				for(int j=0;j<temp_CUSTOMER_CD.size();j++)
				{
					if((""+temp_CUSTOMER_CD_LOA.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD.elementAt(j)).trim()))
					{
						++inner_count;
						break;
					}					
				}
				
				if(inner_count==0)
				{
					CUSTOMER_CD.add((""+temp_CUSTOMER_CD_LOA.elementAt(i)).trim());
				}
			}
						
			for(int j=0;j<temp_CUSTOMER_CD.size();j++)
			{
				CUSTOMER_CD.add((""+temp_CUSTOMER_CD.elementAt(j)).trim());
			}
			
			//All Customer's Data Involved In LIVE SN or LOA depends upon Selected Period ...
			double outstanding_commit_sales = 0;
			double re_commit_sales = 0;
						
			for(int i=0; i<CUSTOMER_CD.size(); i++)
			{				
				Vector tmp_SN_NO = new Vector();
				Vector tmp_SN_REF_NO = new Vector();
				Vector tmp_FGSA_NO = new Vector();
				Vector tmp_TCQ = new Vector();
				Vector tmp_INNER_CUSTOMER_CD_SN_LOA = new Vector();
				Vector tmp_CONT_TYPE = new Vector();
				Vector tmp_QTY_MMBTU = new Vector();
				Vector tmp_QTY_BALANCE = new Vector();
				Vector tmp_REMARK = new Vector();
				String tmp_SN_CLOSURE_DT = "";
				String tmp_SN_variant_qty = "0";
				String tmp_SN_end_dt="";
				
				queryString = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
							  "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
				////System.out.println("Customer Name"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));				
				}
				else
				{
					CUSTOMER_NM.add("");
				}
				
				int cnt=0;
				double total_balance = 0;
				
				//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
				/*queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD,NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO from FMS7_SN_MST A where "+			
							  "((A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT)) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
							  "where A.SN_NO=B.SN_NO AND " +
							  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
				
				//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...

//				FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
				/*queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD," +
							  "NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO,A.sn_ref_no from FMS7_SN_MST A where "+			
							  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
							  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
							  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
							  "where A.SN_NO=B.SN_NO AND " +
							  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

//				FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
				queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD," +
				  "NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO,A.sn_ref_no,TO_CHAR(A.SN_CLOSURE_DT,'DD/MM/YYYY')" +
				  ",VARIATION_QTY, TO_CHAR(A.END_DT,'DD/MM/YYYY') " +
				  " from FMS7_SN_MST A where "+			
				  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
				  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
				  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
				  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
				  "where A.SN_NO=B.SN_NO AND " +
				  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
			//	//System.out.println("SN Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					++cnt;
					tmp_SN_NO.add(rset.getString(1)==null?"":rset.getString(1));
					tmp_FGSA_NO.add(rset.getString(2)==null?"":rset.getString(2));
					tmp_TCQ.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					tmp_INNER_CUSTOMER_CD_SN_LOA.add(rset.getString(4)==null?"0":rset.getString(4));
					tmp_CONT_TYPE.add("S");
					tmp_SN_REF_NO.add(rset.getString(7)==null?"":rset.getString(7));
					tmp_SN_CLOSURE_DT = rset.getString(8)==null?"0":rset.getString(8);
					tmp_SN_variant_qty = rset.getString(9)==null?"0":nf5.format(Double.parseDouble(rset.getString(9)));
					tmp_SN_end_dt = rset.getString(10)==null?"":rset.getString(10);
					int re_count = 0;
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_SN_LD_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					////System.out.println("FMS7_SN_LD_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_SN_MAKEUPGAS_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					////System.out.println("FMS7_SN_MAKEUPGAS_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_SN_TOP_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					////System.out.println("FMS7_SN_TOP_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
		
					double balance = 0; 
						
					
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND FGSA_NO='"+rset.getString(2)+"' " +
								   "AND CONTRACT_TYPE='S' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
					////System.out.println("QTY_MMBTU = "+queryString1); 
					rset1 = stmt1.executeQuery(queryString1);
//					FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
					if(rset1.next())
					{
						tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
												
						balance = ((Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))))+Double.parseDouble(tmp_SN_variant_qty))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						
						String tmp = "";
						if (!tmp_SN_CLOSURE_DT.equals("0")){
							////System.out.println("from if of dual of SN >>>>>>> ");
							queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_SN_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
							////System.out.println(">>>>Dual SN = "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if (rset2.next())
							{
								tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
								////System.out.println("Count SN>>>>>>>> "+tmp);
								////System.out.println("balance SN>>>>>>>> "+balance);
							}
						}
						if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Short closed by "+nf3.format(balance)+" Mmbtus");
							balance = 0;
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Excess supplied by "+nf3.format(balance*(-1))+" Mmbtus");
							balance = 0;
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance==0)
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Closed");
							balance = 0;
						}
						else
						{
							//tmp_QTY_BALANCE.add(nf3.format(Double.parseDouble(rset.getString(3)==null?"0":nf.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf.format(Double.parseDouble(rset1.getString(1))))));
							
							
							
							String tempD10[]=from_date.split("/");
							String d10=tempD10[2]+tempD10[1]+tempD10[0];
							
							String tempD20[]=tmp_SN_end_dt.split("/");
							String d20=tempD20[2]+tempD20[1]+tempD20[0];
							
							
							if(Integer.parseInt(d20)>Integer.parseInt(d10))
							{
								// not closed
								
								if (balance==0)
								{
									tmp_QTY_BALANCE.add("-");
									tmp_REMARK.add("Closed");
									balance = 0;
								}else
								{
									tmp_QTY_BALANCE.add(nf3.format(balance));
									tmp_REMARK.add("");
								}
								
								
								
							}else
							{
								//closed
								if (balance<0)
								{
									tmp_QTY_BALANCE.add("-");
									tmp_REMARK.add("Excess supplied by "+nf3.format(balance*(-1))+" Mmbtus");
									balance = 0;
									
								}
								else if (balance==0)
								{
									tmp_QTY_BALANCE.add("-");
									tmp_REMARK.add("Closed");
									balance = 0;
								}
								else
								{
									tmp_QTY_BALANCE.add(nf3.format(balance));
									tmp_REMARK.add("Short closed by "+nf3.format(balance)+" Mmbtus");
								}
							}
							
							
							
							
						}						
					}
					else
					{
						tmp_QTY_MMBTU.add("");
						balance = Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))));
						tmp_QTY_BALANCE.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
						tmp_REMARK.add("");
					}
					
					total_balance += balance;
					
					if(re_count>0)
					{
						outstanding_commit_sales += balance;
					}
					else
					{
						re_commit_sales += balance;
					}
				}
				
				
				double balance = 0;
				
				//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
				/*queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
							  "((A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (to_date('"+from_date+"', 'dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT)) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
							  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
				
//				FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			
				/*queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD, " +
							  "A.loa_ref_no from FMS7_LOA_MST A where "+			
							  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
							  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
							  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
							  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
//				FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
				queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD, " +
				  "A.loa_ref_no, A.LOA_CLOSURE_FLAG ,TO_CHAR(A.LOA_CLOSURE_DT,'DD/MM/YYYY'),"
				  + "TO_CHAR(A.END_DT,'DD/MM/YYYY') from FMS7_LOA_MST A where "+			
				  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
				  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
				  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
				  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
				  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
				  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
				
			//	////System.out.println("LOA NAME = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					++cnt;
					tmp_SN_NO.add(rset.getString(1)==null?"":rset.getString(1));
					tmp_FGSA_NO.add(rset.getString(2)==null?"":rset.getString(2));
					tmp_TCQ.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					tmp_INNER_CUSTOMER_CD_SN_LOA.add(rset.getString(4)==null?"0":rset.getString(4));
					tmp_CONT_TYPE.add("L");
					tmp_SN_REF_NO.add(rset.getString(5)==null?"":rset.getString(5));
					tmp_SN_CLOSURE_DT = rset.getString(7)==null?"0":rset.getString(7);
					String tmp_SN_CLOSURE_FLG = rset.getString(6)==null?"0":rset.getString(6);
					tmp_SN_end_dt = rset.getString(8)==null?"":rset.getString(8);
					//////System.out.println("LOA REF NO : "+rset.getString(5)==null?"":rset.getString(5));
					////System.out.println("LOA REF NO : "+rset.getString(5));
					int re_count = 0;
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_LD_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND LOA_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					////System.out.println("FMS7_LOA_LD_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_MAKEUPGAS_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND LOA_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					////System.out.println("FMS7_LOA_MAKEUPGAS_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_TOP_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND LOA_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					////System.out.println("FMS7_LOA_TOP_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND FGSA_NO='"+rset.getString(2)+"' " +
								   "AND CONTRACT_TYPE='L' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
					////System.out.println("QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
//					FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
					if(rset1.next())
					{
						tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						
						balance = (Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						
//						//System.out.println("balcnadfew--------------"+balance);
						
						String tmp = "";
						if (!tmp_SN_CLOSURE_DT.equals("0")){
							////System.out.println("from if of dual of LOA >>>>>>> ");
							queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_SN_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
							////System.out.println(">>>>Dual LOA= "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if (rset2.next())
							{
								tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
								////System.out.println("Count >>>>>>>> LOA "+tmp);
								////System.out.println("balance >>>>>>>> LOA "+balance);
							}
						}
						//CHECK FOR CLOSURE FLAG AND QUANTITY
						if(tmp_SN_CLOSURE_FLG.trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Short closed by "+nf3.format(balance)+" Mmbtus");
							balance = 0;
						}
						else if(tmp_SN_CLOSURE_FLG.trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Over supplied by "+nf3.format(balance*(-1))+" Mmbtus");
							balance = 0;
						}
						else if(tmp_SN_CLOSURE_FLG.trim().equalsIgnoreCase("Y") && balance==0)
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Closed");
							balance = 0;
						}
						else
						{
							//CHECK FOR TSP OF CONTRACT
							//tmp_QTY_BALANCE.add(nf3.format(Double.parseDouble(rset.getString(3)==null?"0":nf.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf.format(Double.parseDouble(rset1.getString(1))))));
							
							
							String tempD10[]=from_date.split("/");
							String d10=tempD10[2]+tempD10[1]+tempD10[0];
							
							String tempD20[]=tmp_SN_end_dt.split("/");
							String d20=tempD20[2]+tempD20[1]+tempD20[0];
							
							
							if(Integer.parseInt(d20)>Integer.parseInt(d10))
							{
								// not closed
								
								if (balance==0)
								{
									tmp_QTY_BALANCE.add("-");
									tmp_REMARK.add("Closed");
									balance = 0;
								}else
								{
									tmp_QTY_BALANCE.add(nf3.format(balance));
									tmp_REMARK.add("");
								}
								
								
								
							}
							else
							{
								//closed
								if (balance<0)
								{
									tmp_QTY_BALANCE.add("-");
									tmp_REMARK.add("Excess supplied by "+nf3.format(balance*(-1))+" Mmbtus");
									balance = 0;
									
								}
								else if (balance==0)
								{
									tmp_QTY_BALANCE.add("-");
									tmp_REMARK.add("Closed");
									balance = 0;
								}
								else
								{
									tmp_QTY_BALANCE.add(nf3.format(balance));
									tmp_REMARK.add("Short closed by "+nf3.format(balance)+" Mmbtus");
								}
							}
							
							
							
							
							
//							if (balance<0)
//							{
//								tmp_QTY_BALANCE.add("-");
//								tmp_REMARK.add("");
//								balance = 0;
//							}
//							else if (balance==0)
//							{
//								tmp_QTY_BALANCE.add("-");
//								tmp_REMARK.add("");
//								balance = 0;
//							}
//							else
//							{
//								tmp_QTY_BALANCE.add(nf3.format(balance));
//								tmp_REMARK.add("");
//							}
						}	
						
//						//System.out.println("balcnadfew--------------"+tmp_REMARK);
						/*
						if(balance<=0)
						{
							tmp_QTY_BALANCE.add("-");
							balance = 0;
						}
						else
						{
							tmp_QTY_BALANCE.add(nf3.format(balance));
						}*/
					}
					else
					{
						tmp_QTY_MMBTU.add("");
						balance = Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))));
						tmp_QTY_BALANCE.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					}
					
					tmp_REMARK.add("");
					total_balance += balance;
					
					if(re_count>0)
					{
						outstanding_commit_sales += balance;
					}
					else
					{
						re_commit_sales += balance;
					}
				}
				
				if(cnt==0)
				{
					tmp_SN_NO.add("");
					tmp_SN_REF_NO.add("");
					tmp_FGSA_NO.add("");
					tmp_TCQ.add("");
					tmp_INNER_CUSTOMER_CD_SN_LOA.add(""+CUSTOMER_CD.elementAt(i));
					tmp_CONT_TYPE.add("");
					tmp_QTY_MMBTU.add("");
					tmp_QTY_BALANCE.add("");
					tmp_REMARK.add("");
				}
				
//				//System.out.println("tmp_REMARK--------------"+tmp_REMARK);
				
				SN_NO.add(tmp_SN_NO);
				SN_REF_NO.add(tmp_SN_REF_NO);
				FGSA_NO.add(tmp_FGSA_NO);
				TCQ.add(tmp_TCQ);
				INNER_CUSTOMER_CD_SN_LOA.add(tmp_INNER_CUSTOMER_CD_SN_LOA);
				CONT_TYPE.add(tmp_CONT_TYPE);
				QTY_MMBTU.add(tmp_QTY_MMBTU);
				BALANCE.add(tmp_QTY_BALANCE);
				SN_LOA_OUTER_COUNTER.add(""+cnt);
				SEND_OUT_REMARK.add(tmp_REMARK);
				SN_LOA_TOTAL_BALANCE.add(nf3.format(total_balance));
			}
		//	//System.out.println("SN REF NO : "+SN_REF_NO);
			outstanding_commitment_sales = nf3.format(outstanding_commit_sales);
			outstanding_commitment_sales_mcm = nf.format(outstanding_commit_sales/conversion_factor_mcm);
			
			//ADDED FOR LTCORA AND CN
			outstanding_commitment_total = nf3.format(outstanding_commit_LTCORA+outstanding_commit_regas+outstanding_commit_sales);
			outstanding_commitment_total_mcm = nf.format((outstanding_commit_LTCORA/conversion_factor_mcm)+(outstanding_commit_regas/conversion_factor_mcm)+(outstanding_commit_sales/conversion_factor_mcm));
			
			re_commitment_sales = nf3.format(re_commit_sales);
			re_commitment_sales_mcm = nf.format(re_commit_sales/conversion_factor_mcm);
			internal_consumption_re_commitment_sales = nf3.format(re_commit_sales*consumption_percentage/100);
			internal_consumption_re_commitment_sales_mcm = nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100);
			
			//double net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
			//double net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
			
			double net_uncommit_sale =0;
			double net_uncommit_sale_mcm =0;
			
			String dd=from_date.substring(0,2);
			
			if(Integer.parseInt(dd)<=15)
			{
				net_uncommited_sales = nf3.format(Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales)));
				net_uncommited_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
				
				net_uncommited_regas = nf3.format(Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas)));
				net_uncommited_regas_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm));
			
				net_uncommited_LTCORA = nf3.format(Double.parseDouble(nf5.format(opening_stock_LTCORA))+Double.parseDouble(nf5.format(vol_exp_LTCORA))-Double.parseDouble(nf5.format(outstanding_commit_LTCORA))-Double.parseDouble(nf5.format(int_consumption_LTCORA)));
				net_uncommited_LTCORA_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_LTCORA/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_LTCORA))+Double.parseDouble(nf5.format(vol_exp_LTCORA))-Double.parseDouble(nf5.format(outstanding_commit_LTCORA))-Double.parseDouble(nf5.format(int_consumption_LTCORA));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_LTCORA/conversion_factor_mcm));
			
				//net_uncommited_total_volume= Double.parseDouble(nf5.format(opening_stock))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_regas))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_regas+outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_total));
				//net_uncommited_total_mcm_volume = Double.parseDouble(nf.format(opening_stock/conversion_factor_mcm))-Double.parseDouble(nf5.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))+Double.parseDouble(nf.format((vol_exp_sales+vol_exp_sales1)/conversion_factor_mcm))-Double.parseDouble(nf.format((outstanding_commit_regas+outstanding_commit_sales)/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_total/conversion_factor_mcm));
				
				
				 net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
				 net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
				
			}
			else
			{
				net_uncommited_sales = nf3.format(Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales)));
				net_uncommited_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
				
				net_uncommited_regas = nf3.format(Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_recv_regas))-Double.parseDouble(nf5.format(mon_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas)));
				net_uncommited_regas_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_recv_regas))-Double.parseDouble(nf5.format(mon_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm));
				
				net_uncommited_LTCORA = nf3.format(Double.parseDouble(nf5.format(opening_stock_LTCORA))+Double.parseDouble(nf5.format(vol_recv_LTCORA))-Double.parseDouble(nf5.format(mon_LTCORA))+Double.parseDouble(nf5.format(vol_exp_LTCORA))-Double.parseDouble(nf5.format(outstanding_commit_LTCORA))-Double.parseDouble(nf5.format(int_consumption_LTCORA)));
				net_uncommited_LTCORA_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_LTCORA/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_LTCORA))+Double.parseDouble(nf5.format(vol_recv_LTCORA))-Double.parseDouble(nf5.format(mon_LTCORA))+Double.parseDouble(nf5.format(vol_exp_LTCORA))-Double.parseDouble(nf5.format(outstanding_commit_LTCORA))-Double.parseDouble(nf5.format(int_consumption_LTCORA));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_LTCORA/conversion_factor_mcm));
				
				
				 net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
				 net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
				
			}
			
			
			net_uncommited_total = nf3.format(net_uncommited_total_volume);
			net_uncommited_total_mcm = nf.format(net_uncommited_total_mcm_volume);
			
			net_uncommited_overcommited_sales = nf3.format(net_uncommit_sale-re_commit_sales-(re_commit_sales*consumption_percentage/100));
			net_uncommited_overcommited_sales_mcm = nf.format(net_uncommit_sale_mcm-Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100)));
			
			net_uncommited_overcommited_total = nf3.format(net_uncommited_total_volume-re_commit_sales-(re_commit_sales*consumption_percentage/100));
			net_uncommited_overcommited_total_mcm = nf.format(net_uncommited_total_mcm_volume-Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100)));
				
			for(int i=0; i<SN_LOA_OUTER_COUNTER.size(); i++)
			{
				FINAL_COUNT.add(""+SN_LOA_OUTER_COUNTER.elementAt(i));
			}
			for(int i=0; i<RE_GAS_OUTER_COUNTER.size(); i++)
			{
				FINAL_COUNT.add(""+RE_GAS_OUTER_COUNTER.elementAt(i));
			}
			for(int i=0; i<LTCORA_OUTER_COUNTER.size(); i++)
			{
				FINAL_COUNT.add(""+LTCORA_OUTER_COUNTER.elementAt(i));
			}
			
			for(int i=0; i<FINAL_COUNT.size(); i++)
			{
				if(Integer.parseInt(""+FINAL_COUNT.elementAt(i))>max_count_for_column)
				{
					max_count_for_column = Integer.parseInt(""+FINAL_COUNT.elementAt(i));
				}
			}
			
			for(int i=0; i<SN_NO.size(); i++)
			{
				int count = ((Vector)SN_NO.elementAt(i)).size();
				String cust_cd = ""+((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).elementAt(count-1);
				for(int j=count; j<max_count_for_column; j++)
				{
					((Vector)SN_NO.elementAt(i)).add("");
					((Vector)SN_REF_NO.elementAt(i)).add("");
					((Vector)FGSA_NO.elementAt(i)).add("");
					((Vector)TCQ.elementAt(i)).add("");
					((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).add(""+cust_cd);
					((Vector)QTY_MMBTU.elementAt(i)).add("");
					((Vector)BALANCE.elementAt(i)).add("");
					((Vector)CONT_TYPE.elementAt(i)).add("");
					((Vector)SEND_OUT_REMARK.elementAt(i)).add("");
				}
			}
			
			for(int i=0; i<RE_GAS_NO.size(); i++)
			{
				int count = ((Vector)RE_GAS_NO.elementAt(i)).size();
				String cust_cd = ""+((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).elementAt(count-1);
				for(int j=count; j<max_count_for_column; j++)
				{
					((Vector)RE_GAS_NO.elementAt(i)).add("");				
					((Vector)CAPACITY.elementAt(i)).add("");
					((Vector)REMARK_RE_GAS_NO.elementAt(i)).add("");				
					((Vector)RE_GAS_CARGO_NO.elementAt(i)).add("");
					((Vector)QTY_MMBTU_RE_GAS.elementAt(i)).add("");
					((Vector)BALANCE_RE_GAS.elementAt(i)).add("");
					((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).add(""+cust_cd);
				}
			}
			
			for(int i=0; i<LTCORA_NO.size(); i++)
			{
				int count = ((Vector)LTCORA_NO.elementAt(i)).size();
				String cust_cd = ""+((Vector)INNER_CUSTOMER_CD_LTCORA.elementAt(i)).elementAt(count-1);
				for(int j=count; j<max_count_for_column; j++)
				{
					((Vector)LTCORA_NO.elementAt(i)).add("");	
					((Vector)LTCORA_CONT_TYPE.elementAt(i)).add("");
					((Vector)LTCORA_CAPACITY.elementAt(i)).add("");
					((Vector)LTCORA_REMARK_RE_GAS_NO.elementAt(i)).add("");				
					((Vector)LTCORA_CARGO_NO.elementAt(i)).add("");
					((Vector)QTY_MMBTU_LTCORA.elementAt(i)).add("");
					((Vector)BALANCE_LTCORA.elementAt(i)).add("");
					((Vector)INNER_CUSTOMER_CD_LTCORA.elementAt(i)).add(""+cust_cd);
				}
			}
			
		//	//System.out.println("max_count_for_column = "+max_count_for_column);
			
			/*for(int i=0; i<SN_NO.size(); i++)
			{
				//System.out.println("SN_NO size = "+((Vector)SN_NO.elementAt(i)).size());
				//System.out.println("FGSA_NO size = "+((Vector)FGSA_NO.elementAt(i)).size());
				//System.out.println("TCQ size = "+((Vector)TCQ.elementAt(i)).size());
				//System.out.println("INNER_CUSTOMER_CD_SN_LOA size = "+((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).size());
				//System.out.println("CONT_TYPE size = "+((Vector)CONT_TYPE.elementAt(i)).size());
			}
			
			for(int i=0; i<RE_GAS_NO.size(); i++)
			{
				//System.out.println("RE_GAS_NO size = "+((Vector)RE_GAS_NO.elementAt(i)).size());
				//System.out.println("CAPACITY size = "+((Vector)CAPACITY.elementAt(i)).size());
				//System.out.println("RE_GAS_CARGO_NO size = "+((Vector)RE_GAS_CARGO_NO.elementAt(i)).size());
				//System.out.println("INNER_CUSTOMER_CD_RE_GAS size = "+((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).size());
			}			
			
			total_regas=nf3.format(sum_balance_regas);
			total=nf3.format(sum_balance);*/
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchSendOut()-->"+e);
			  e.printStackTrace();
		}
	
	}
	public void fetchDailyReport_WITH_ACCESS()
	{
		try
		{
			double conversion_factor_mcm = 38900;
//			MD20111220		 double multiplying_factor_meter_cube = 0.04847;
	    double multiplying_factor_meter_cube = 0.042845;
			//Following (5) Variables are related to SN/LOA Contracts TOTAL ...
			double total_sales_gas_day_mmbtu_qty = 0;
			double total_sales_gas_day_mmscm_qty = 0;			
			double sum_sales_firm_qty = 0;
			double sum_sales_re_qty = 0;
			double sum_sales_re_firm_qty = 0;
			
			//Following (3) Variables are related to Re-GAS Contracts TOTAL ...
			double total_regas_gas_day_mmbtu_qty = 0;
			double total_regas_gas_day_mmscm_qty = 0;
			double total_regas_firm_mmscm_qty = 0;
			
			//Following (2) Variables are related to Future Cargo Arrival TOTAL ...
			double total_expected_delivered_m3_qty = 0;
			double total_expected_delivered_mmscm_qty = 0;
			
			
			Vector V_gas_dates = new Vector();
				//JHP Start			
			String query1="";
			String Temp_dt="";
			String Temp_dt1="";
			int k=7;
			int y=1;
			query1 = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+k+",'dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(query1);
			////System.out.println(query1);
			if(rset.next())
			{
				Temp_dt=rset.getString(1);
			}
			query1 = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+y+",'dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(query1);
			////System.out.println(query1);
			if(rset.next())
			{
				Temp_dt1=rset.getString(1);
			}
			query1="select DISTINCT A.CUSTOMER_CD,A.CONTRACT_TYPE "
					+ "from FMS7_DAILY_ALLOCATION_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B "
					+ "where A.GAS_DT between to_date('"+Temp_dt+"','dd/mm/yyyy') and "
					+ "to_date('"+Temp_dt1+"','dd/mm/yyyy') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
							+ "B.EMP_CD='"+Emp_cd+"' ";  
//			//System.out.println("j::"+query1);
			rset = stmt.executeQuery(query1);
			////System.out.println(query1);
			while(rset.next())
			{
				Vparty.add(rset.getString(1));
				Vparty_cont_type.add(rset.getString(2));
			}
			
			//JHP End		
			String query = "";
			
			for(int i=7; i>=1; i--)
			{
				query = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+i+",'dd/mm/yyyy') FROM DUAL";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					V_gas_dates.add(rset.getString(1));
				}
			}
			
			if(V_gas_dates.size()==7)
			{
				daily_graph_from_date = ""+V_gas_dates.elementAt(0);
				daily_graph_to_date = ""+V_gas_dates.elementAt(6);
			}
			
			String gas_day = "";
			
			String end_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))+1);
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				gas_day = rset.getString(1);
			}
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DDth, MONTH YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				selected_month_year = rset.getString(1);
			}
			
			String expected_volume_end_date = "";
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
			
			if(gas_day.trim().length()>=10)
			{
				Vector temp_Cust_CD = new Vector();
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_SN_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B  where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') between " +
							   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
							   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B,"
							   + "SEC_EMP_CUSTOMER_ALLOC_MST C WHERE " +
							   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD"
							   + " AND C.CUSTOMER_CD=B.CUSTOMER_CD AND C.EMP_CD='"+Emp_cd+"') "
							   + "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' " +
							   "ORDER BY A.CUSTOMER_CD";
//				//System.out.println("Distinct from FMS7_SN_MST = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_LOA_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') between " +
							   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
							   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B,"
							   + "SEC_EMP_CUSTOMER_ALLOC_MST C WHERE " +
							   "A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD "
							   + "AND C.CUSTOMER_CD=B.CUSTOMER_CD AND C.EMP_CD='"+Emp_cd+"') "
							   + "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' " +
							   "ORDER BY A.CUSTOMER_CD";
				////System.out.println("Distinct from FMS7_LOA_MST = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					temp_Cust_CD.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				for(int i=0; i<temp_Cust_CD.size(); i++)
				{
					int cnt = 0;
					for(int j=0; j<CUSTOMER_CD.size(); j++)
					{
						if((""+temp_Cust_CD.elementAt(i)).equals(""+CUSTOMER_CD.elementAt(j)))
						{
							++cnt;
						}
					}
					
					if(cnt==0)
					{
						CUSTOMER_CD.add(""+temp_Cust_CD.elementAt(i));
					}
				}
				
				
				for(int i=0; i<CUSTOMER_CD.size(); i++)
				{
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to SN/LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_sales_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_sales_gas_day_mmscm_qty += rset1.getString(1)==null?0:(Double.parseDouble(rset1.getString(2))/1000000);
						Flag1_sales=true;
					
					}
					else
					{
						
						QTY_MMBTU.add("");
						QTY_MMSCM.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
					////System.out.println("Customer Name for SN/LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM.add("");
					}
					
					String sales_end_dt = "";
					String re_end_dt = "";
					
					double sales_firm_qty = 0;
					double sales_re_firm_qty = 0;
										
					//Following Query Is Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.SN_NO,A.FGSA_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy') " +
								   "from FMS7_SN_MST A where " +
								   "(A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
								   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
								   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO) " +
								   "ORDER BY A.END_DT";*/
					
					//Following Query Is Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.SN_NO, A.FGSA_NO, TO_CHAR(A.END_DT,'dd/mm/yyyy'), A.FGSA_REV_NO, A.SN_REV_NO " +
								   "from FMS7_SN_MST A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.START_DT AND A.END_DT) OR (A.START_DT BETWEEN " +
								   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND " +
								   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
								   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_NO=B.FGSA_NO AND " +
								   "A.SN_NO=B.SN_NO) ORDER BY A.END_DT";
					////System.out.println("Master Query for FMS7_SN_MST = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						int re_count = 0;
						int clause_cd = 3; //3 Clause CD stands for Existance of Take or Pay Clause in the Supply Notice ...
						
						queryString2 = "select COUNT(A.sn_no) " +
									   "from FMS7_SN_TOP_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.SN_NO='"+rset1.getString(1)+"' and " +
									   "A.SN_REV_NO='"+rset1.getString(5)+"' and " +
									   "A.FGSA_REV_NO='"+rset1.getString(4)+"' and " +
									   "A.CONT_TYPE='S'";
						////System.out.println("Query for checking EXISTANCE of Take or Pay Clause in FMS7_SN_TOP_DTL Table = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							re_count = rset2.getInt(1);
						}
						
						String split_tcq_flag = "N";
						String re_qty = "0";
						String firm_qty = "0";
						
						queryString2 = "select A.TCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'), A.FGSA_REV_NO, A.SN_REV_NO,"
								+ "NVL(FIRM_QTY,'0'),NVL(RE_QTY,'0'),NVL(SPLIT_TCQ_FLAG,'N') " +
									   "from FMS7_SN_MST A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.SN_NO='"+rset1.getString(1)+"' and " +
									   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where " +
									   "A.CUSTOMER_CD=B.CUSTOMER_CD and A.FGSA_NO=B.FGSA_NO and A.SN_NO=B.SN_NO)";
						////System.out.println("2nd Master Query for FMS7_SN_MST = "+queryString1);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							split_tcq_flag = rset2.getString(7)==null?"N":rset2.getString(7);
							firm_qty = rset2.getString(5)==null?"0":rset2.getString(5);
							re_qty = rset2.getString(6)==null?"0":rset2.getString(6);
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='S' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							sales_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						////System.out.println("SN tcq = "+tcq+",  alloc_qty = "+alloc_qty);
						if(tcq>alloc_qty)
						{
							if(re_count>0 || split_tcq_flag.equals("Y"))
							{
								if(split_tcq_flag.equals("Y"))
								{
									if(Double.parseDouble(firm_qty)>=alloc_qty)
									{
										sales_firm_qty += (Double.parseDouble(firm_qty)-alloc_qty);
									}
									else
									{
										//ADDED BY RS07022017
										double temp_data = alloc_qty - Double.parseDouble(firm_qty);
										sales_re_firm_qty += (Double.parseDouble(re_qty)-temp_data);
									}
								}
								else
								{
									sales_firm_qty += (tcq-alloc_qty);
								}
							}
							else
							{
								if(split_tcq_flag.equals("Y"))
								{
									sales_re_firm_qty += (Double.parseDouble(re_qty)-alloc_qty);
								}
								else
								{
									sales_re_firm_qty += (tcq-alloc_qty);
								}
							}
						}
					}
					
					//Following Query Is Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.LOA_NO,A.TENDER_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy') " +
								   "from FMS7_LOA_MST A where " +
								   "(A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
								   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
								   "A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO) " +
								   "ORDER BY A.END_DT";*/
					
					//Following Query Is Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.LOA_NO,A.TENDER_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy'),A.LOA_REV_NO " +
								   "from FMS7_LOA_MST A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.START_DT AND A.END_DT) OR (A.START_DT BETWEEN " +
								   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND " +
								   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
								   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.TENDER_NO=B.TENDER_NO AND " +
								   "A.LOA_NO=B.LOA_NO) ORDER BY A.END_DT";
					
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						int re_count = 0;
						int clause_cd = 3; //3 Clause CD stands for Existance of Take or Pay Clause in the Letter of Agreement ...
						
						queryString2 = "select COUNT(A.loa_no) " +
									   "from FMS7_LOA_TOP_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.LOA_NO='"+rset1.getString(1)+"' and " +
									   "A.LOA_REV_NO='"+rset1.getString(4)+"'";
						////System.out.println("Query for checking EXISTANCE of Take or Pay Clause in FMS7_LOA_TOP_DTL Table = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							re_count = rset2.getInt(1);
						}
						
						String split_tcq_flag = "N";
						String re_qty = "0";
						String firm_qty = "0";
						
						queryString2 = "select A.TCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_REV_NO,"
								+ "NVL(FIRM_QTY,'0'),NVL(RE_QTY,'0'),NVL(SPLIT_TCQ_FLAG,'N') " +
									   "from FMS7_LOA_MST A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.TENDER_NO='"+rset1.getString(2)+"' and " +
									   "A.LOA_NO='"+rset1.getString(1)+"' and " +
									   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where " +
									   "A.CUSTOMER_CD=B.CUSTOMER_CD and A.TENDER_NO=B.TENDER_NO and " +
									   "A.LOA_NO=B.LOA_NO)";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							split_tcq_flag = rset2.getString(7)==null?"N":rset2.getString(7);
							firm_qty = rset2.getString(5)==null?"0":rset2.getString(5);
							re_qty = rset2.getString(6)==null?"0":rset2.getString(6);
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='L' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							re_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						////System.out.println("LOA tcq = "+tcq+",  alloc_qty = "+alloc_qty);
						if(tcq>alloc_qty)
						{
							if(re_count>0 || split_tcq_flag.equals("Y"))
							{
								if(split_tcq_flag.equals("Y"))
								{
									if(Double.parseDouble(firm_qty)>=alloc_qty)
									{
										sales_firm_qty += (Double.parseDouble(firm_qty)-alloc_qty);
									}
									else
									{
										//ADDED BY RS07022017
										double temp_data = alloc_qty - Double.parseDouble(firm_qty);
										sales_re_firm_qty += (Double.parseDouble(re_qty)-temp_data);
									}
								}
								else
								{
									sales_firm_qty += (tcq-alloc_qty);
								}
							}
							else
							{
								if(split_tcq_flag.equals("Y"))
								{
									sales_re_firm_qty += (Double.parseDouble(re_qty)-alloc_qty);
								}
								else
								{
									sales_re_firm_qty += (tcq-alloc_qty);
								}
							}
						}
					}
					
					
					if(sales_firm_qty>0)
					{
						
						QTY_MMSCM_FIRM.add(nf.format(sales_firm_qty/conversion_factor_mcm));
					}
					else
					{
						QTY_MMSCM_FIRM.add("");
					}
					
					sum_sales_firm_qty += Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm));
					
					////System.out.println("abcd  sales_re_firm_qty--- "+sales_re_firm_qty);
					if(sales_re_firm_qty>0)
					{
						QTY_MMSCM_RE_SALES.add(nf.format(sales_re_firm_qty/conversion_factor_mcm));
					}
					else
					{
						QTY_MMSCM_RE_SALES.add("");
					}
					
					sum_sales_re_qty += Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm));
					
					if(!sales_end_dt.trim().equals("") && !re_end_dt.trim().equals(""))
					{
						//Logic to be developed for Greater from above mentioned TWO Dates ...
						SALES_END_DT.add(""+sales_end_dt.trim());
					}
					else if(!sales_end_dt.trim().equals(""))
					{
						SALES_END_DT.add(""+sales_end_dt.trim());
					}
					else if(!re_end_dt.trim().equals(""))
					{
						SALES_END_DT.add(""+re_end_dt.trim());
					}
					else
					{
						SALES_END_DT.add("");
					}
					
					if((sales_firm_qty+sales_re_firm_qty)>0)
					{
						QTY_MMSCM_TOTAL_FIRM_RE.add(nf.format(Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm))+Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm))));
					}
					else
					{
						QTY_MMSCM_TOTAL_FIRM_RE.add("");
					}
					
					sum_sales_re_firm_qty += Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm))+Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm));					
				}
				
				total_sales_gas_day_mmbtu_qty_str = nf3.format(total_sales_gas_day_mmbtu_qty);
				total_sales_gas_day_mmscm_qty_str = nf.format(total_sales_gas_day_mmscm_qty);			
				sum_sales_firm_qty_str = nf.format(sum_sales_firm_qty);
				sum_sales_re_qty_str = nf.format(sum_sales_re_qty);
				sum_sales_re_firm_qty_str = nf.format(sum_sales_re_firm_qty);
				
				
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_RE_GAS_CARGO_DTL A,SEC_EMP_CUSTOMER_ALLOC_MST B where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
							   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
							   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) "
							   	+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' " +
							   "ORDER BY A.CUSTOMER_CD";
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				//Following Logic Is For Re-Gas Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
				   			  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND CONTRACT_TYPE='R'";
				////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));*/
				for(int i=0; i<CUSTOMER_CD_RE_GAS.size(); i++)
				{					
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND CONTRACT_TYPE='R' AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU_RE_GAS.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_regas_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM_RE_GAS.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_regas_gas_day_mmscm_qty += rset1.getString(1)==null?0:((Double.parseDouble(rset1.getString(2))/1000000));
						Flag2_Re_Gas=true;
					}
					else
					{
						
						QTY_MMBTU_RE_GAS.add("");
						QTY_MMSCM_RE_GAS.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
					////System.out.println("Customer Name for Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM_RE_GAS.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM_RE_GAS.add("");
					}
					
					String re_gas_end_dt = "";
					double regas_expected_mmscm_qty = 0;
					
					//Following Query Has Been Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "(A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' ORDER BY A.CONTRACT_END_DT";*/
					
					//Following Query Has Been Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
								   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
								   "ORDER BY A.CONTRACT_END_DT";
					
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						queryString2 = "select A.QTY_TO_BE_SUPPLY, TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
									   "from FMS7_RE_GAS_CARGO_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' and " +
									   "A.RE_GAS_NO='"+rset1.getString(2)+"' and " +
									   "A.CARGO_SEQ_NO='"+rset1.getString(1)+"'";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='R' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Allocated QTY Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							re_gas_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							regas_expected_mmscm_qty += (tcq-alloc_qty);
						}
					}
					
					RE_GAS_END_DT.add(""+re_gas_end_dt.trim());
					QTY_MMSCM_RE_GAS_FIRM.add(nf.format(regas_expected_mmscm_qty/conversion_factor_mcm));
					
					total_regas_firm_mmscm_qty += Double.parseDouble(nf.format(regas_expected_mmscm_qty/conversion_factor_mcm));
				}
				
				total_regas_gas_day_mmbtu_qty_str = nf3.format(total_regas_gas_day_mmbtu_qty);
				total_regas_gas_day_mmscm_qty_str = nf.format(total_regas_gas_day_mmscm_qty);
				total_regas_firm_mmscm_qty_str = nf.format(total_regas_firm_mmscm_qty);
				
				
				//////////////////////ADDED FOR LTCORA AND CN
				
				////GLOBAL
				
				
				//LOCAL
					double total_LTCORA_gas_day_mmbtu_qty=0;
					double total_LTCORA_gas_day_mmscm_qty=0;
					double total_LTCORA_firm_mmscm_qty=0;
				
				queryString1 = "select DISTINCT(C.CUSTOMER_CD) " +
				   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C, SEC_EMP_CUSTOMER_ALLOC_MST D "
				   + "where  A.MAPPING_ID=C.MAPPING_ID AND " +
				   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
				   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
				   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND C.CUSTOMER_CD = D.CUSTOMER_CD "
				   		+ "AND D.EMP_CD='"+Emp_cd+"' " +
				   "ORDER BY C.CUSTOMER_CD";
				
				/*queryString1 ="select DISTINCT(C.CUSTOMER_CD) from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C " +
						"where (A.CONTRACT_START_DT <= to_date('11/11/2014','dd/mm/yyyy') " +
						"AND A.CONTRACT_END_DT >= to_date('11/11/2014','dd/mm/yyyy'))  AND A.MAPPING_ID=C.MAPPING_ID " +
						" AND C.cn_rev_no=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B where " +
						"C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd  AND B.CN_NO=C.CN_NO " +
						"AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
						" D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd  AND D.agreement_no=C.agreement_no " +
						"AND D.customer_cd=C.customer_cd )) AND a.mapping_id=C.mapping_id " +
						"order by C.CUSTOMER_CD ";*/
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD_LTCORA.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				//Following Logic Is For Re-Gas Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
				   			  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND CONTRACT_TYPE='R'";
				////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));*/
				for(int i=0; i<CUSTOMER_CD_LTCORA.size(); i++)
				{					
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU_LTCORA.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_LTCORA_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM_LTCORA.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_LTCORA_gas_day_mmscm_qty += rset1.getString(1)==null?0:((Double.parseDouble(rset1.getString(2))/1000000));
						Flag2_LTCORA=true;
					}
					else
					{
						
						QTY_MMBTU_LTCORA.add("");
						QTY_MMSCM_LTCORA.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
					////System.out.println("Customer Name for Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM_LTCORA.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM_LTCORA.add("");
					}
					
					String LTCORA_end_dt = "";
					double LTCORA_expected_mmscm_qty = 0;
					
					//Following Query Has Been Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "(A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' ORDER BY A.CONTRACT_END_DT";*/
					
					//Following Query Has Been Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.CARGO_SEQ_NO,C.MAPPING_ID,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.FLAG  " +
								   "from FMS8_LNG_REGAS_CARGO_DTL A , FMS8_LNG_REGAS_MST C where  A.MAPPING_ID=C.MAPPING_ID AND " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
								   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "C.CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' " +
								   "ORDER BY A.CONTRACT_END_DT";
					////System.out.println("queryString1..........."+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						String temp_map_id=rset1.getString(2)==null?"":rset1.getString(2);
						String map_id[]=temp_map_id.split("-");
						String temp_cont_type=rset1.getString(4)==null?"":rset1.getString(4);
						String fgsa_no="";
						
						if(temp_cont_type.equalsIgnoreCase("T"))
						{
							fgsa_no=map_id[1];
						}
						else if(temp_cont_type.equalsIgnoreCase("C"))
						{
							fgsa_no=map_id[3];
						}
						////System.out.println("fgsa_no........."+fgsa_no+"map_id[2]"+temp_map_id);
						queryString2 = "select A.QTY_TO_BE_SUPPLY, TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
									   "from FMS8_LNG_REGAS_CARGO_DTL A,  FMS8_LNG_REGAS_MST C where " +
									   "C.CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' " +
									   "AND A.MAPPING_ID=C.MAPPING_ID and " +
									   "A.CARGO_SEQ_NO='"+rset1.getString(1)+"' " +
									   "AND A.MAPPING_ID='"+rset1.getString(2)+"'";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') and FGSA_NO='"+fgsa_no+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Allocated QTY Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							LTCORA_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							LTCORA_expected_mmscm_qty += (tcq-alloc_qty);
							
						}
						////System.out.println("CN   tcq......."+tcq+"alloc_qty......"+alloc_qty);
					}
					
					LTCORA_END_DT.add(""+LTCORA_end_dt.trim());
					////System.out.println("LTCORA_expected_mmscm_qty"+LTCORA_expected_mmscm_qty);
					QTY_MMSCM_LTCORA_FIRM.add(nf.format(LTCORA_expected_mmscm_qty/conversion_factor_mcm));
					total_LTCORA_firm_mmscm_qty += Double.parseDouble(nf.format(LTCORA_expected_mmscm_qty/conversion_factor_mcm));
				}
				
				total_LTCORA_gas_day_mmbtu_qty_str = nf3.format(total_LTCORA_gas_day_mmbtu_qty);
				total_LTCORA_gas_day_mmscm_qty_str = nf.format(total_LTCORA_gas_day_mmscm_qty);
				total_LTCORA_firm_mmscm_qty_str = nf.format(total_LTCORA_firm_mmscm_qty);
				
				///////////////END OF LTCORA AND CN
				
				
			}		
					
			
			Vector last_arrival_dt_own1=new Vector(); 
			Vector last_arrival_dt_tp1=new Vector(); 
			String last_arrival_dt_own = "";
			String last_arrival_dt_tp = "";
			
			//Following Logic Has Been Introduced By Samik Shah On 26th February, 2011 ...
			//Following Logic Fetches Future Caro Arrival Volumes in MMSCM & M3 of LNG ...
			queryString = "SELECT A.CARGO_REF_CD,A.CONFIRM_VOL,NVL(A.UNIT_CD,'1')," +
						  "TO_CHAR(A.DELV_FROM_DT,'dd/mm/yyyy') FROM " +
						  "FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_CARGO_NOMINATION B " +
						  "WHERE A.CARGO_REF_CD=B.CARGO_REF_CD AND (A.DELV_FROM_DT BETWEEN " +
						  "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND " +
						  "B.SHIP_CD IS NOT NULL ORDER BY A.DELV_FROM_DT";
			////System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String unit_cd = rset.getString(3)==null?"1":rset.getString(3);
				double mmbtu_qty = 0;
				
				if(unit_cd.trim().equals("1"))
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				else if(unit_cd.trim().equals("2"))
				{
					mmbtu_qty = rset.getString(2)==null?0:(Double.parseDouble(rset.getString(2))*1000000);
				}
				else
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				
				EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				//EXP_OWN_TP_NM.add("HLPL (OWN)");
				EXP_OWN_TP_NM.add("OWN");
			
				EXP_TIME_OF_ARRIVAL.add(rset.getString(4)==null?"":rset.getString(4));
				//JHP start
	//			EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
				EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
				
				double temp_qty=Double.parseDouble(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				EXP_M3_LNG.add(nf3.format(temp_qty));
				
				total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty));
//JHP20120524	total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
				total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
				//EXP_M3_LNG.add(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				//JHP End	
				
				last_arrival_dt_own = rset.getString(4)==null?"":rset.getString(4);
				last_arrival_dt_own1.add(rset.getString(4)==null?"":rset.getString(4));//JHP
				
				
				String cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);
				
				queryString1 = "SELECT A.SHIP_CD, B.SHIP_NAME FROM FMS7_CARGO_NOMINATION A, FMS7_SHIP_MST B " +
							   "WHERE A.CARGO_REF_CD='"+cargo_ref_no+"' AND A.SHIP_CD=B.SHIP_CD";
				////System.out.println("Inner Query for finding future Vessel List from FMS7_CARGO_NOMINATION = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					EXP_VESSEL_NM.add(rset1.getString(2)==null?"":rset1.getString(2));
				}
				else
				{
					EXP_VESSEL_NM.add("");
				}
			}
			
			
			queryString = "SELECT A.CARGO_REF_NO,A.CUSTOMER_CD,A.SHIP_NAME," +
						  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
						  "FROM FMS7_RE_GAS_CARGO_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
						  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') "
						  		+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' "
						  + " ORDER BY A.EDQ_FROM_DT";
			////System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				EXP_VESSEL_NM.add(rset.getString(3)==null?"":rset.getString(3));
				EXP_TIME_OF_ARRIVAL.add(rset.getString(5)==null?"":rset.getString(5));
				
				last_arrival_dt_tp = rset.getString(5)==null?"":rset.getString(5);
				last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
				
				double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
				//				JHP start
			//	EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
				EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
				
				double temp_qty1=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				temp_qty1=Math.floor((temp_qty1 - (temp_qty1 % 100))+ 500d);
				temp_qty1=(temp_qty1 - (temp_qty1 % 1000));
				EXP_M3_LNG.add(nf9.format(temp_qty1 ));
				
				total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty1));
	//JHP20120524			total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
		         total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//EXP_M3_LNG.add(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
//				JHP End			
			
		
				String customer_cd = rset.getString(2)==null?"0":rset.getString(2);
				String customer_nm = "";
				
				queryString1 = "SELECT A.CUSTOMER_NAME, A.CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A " +
							   "WHERE A.CUSTOMER_CD='"+customer_cd+"'";
				////System.out.println("Query for finding Customer Name & Abbr. of future Cargo Owner with respect to RE-Gas Contract = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					customer_nm = rset1.getString(2)==null?"":rset1.getString(2);
					EXP_OWN_TP_NM.add(customer_nm+"  TP  "); //ADDED BY NB20150831
					//EXP_OWN_TP_NM.add("TP");
				}
				else
				{
					//EXP_OWN_TP_NM.add("( TP)");
					EXP_OWN_TP_NM.add("TP");
				}
			}
			
			///////ADDED FOR LTCORA AND CN
			queryString = "SELECT A.CARGO_REF_NO,A.MAPPING_ID,B.SHIP_NAME," +
			  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
			  "FROM FMS8_LNG_REGAS_CARGO_DTL A , FMS7_SHIP_MST B, FMS8_LNG_REGAS_MST C,SEC_EMP_CUSTOMER_ALLOC_MST D " +
			  "WHERE A.SHIP_CD=B.SHIP_CD AND A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') AND "
			  		+ "C.CUSTOMER_CD=D.CUSTOMER_CD AND D.EMP_CD='"+Emp_cd+"' AND A.MAPPING_ID=C.MAPPING_ID "
			  		+ "ORDER BY A.EDQ_FROM_DT";
				////System.out.println("Query for finding future Cargo List from FMS8_LNG_REGAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String temp_map_id=rset.getString(2)==null?"":rset.getString(2);
					String map_id[]=temp_map_id.split("-");
					
					EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
					EXP_VESSEL_NM.add(rset.getString(3)==null?"":rset.getString(3));
					EXP_TIME_OF_ARRIVAL.add(rset.getString(5)==null?"":rset.getString(5));
					
					last_arrival_dt_tp = rset.getString(5)==null?"":rset.getString(5);
					last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
					
					double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
					//				JHP start
				//	EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
					EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
					
					double temp_qty1=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					temp_qty1=Math.floor((temp_qty1 - (temp_qty1 % 100))+ 500d);
					temp_qty1=(temp_qty1 - (temp_qty1 % 1000));
					EXP_M3_LNG.add(nf9.format(temp_qty1 ));
					
					total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty1));
				//JHP20120524			total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
				   total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
					//EXP_M3_LNG.add(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
					//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
				//	JHP End			
				
				
					String customer_cd = map_id[0];
					String customer_nm = "";
					
					queryString1 = "SELECT A.CUSTOMER_NAME, A.CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A " +
								   "WHERE A.CUSTOMER_CD='"+customer_cd+"'";
					////System.out.println("Query for finding Customer Name & Abbr. of future Cargo Owner with respect to RE-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						customer_nm = rset1.getString(2)==null?"":rset1.getString(2);
						EXP_OWN_TP_NM.add(customer_nm+"  TP  "); //ADDED BY NB20150831
						//EXP_OWN_TP_NM.add("TP");
					}
					else
					{
						//EXP_OWN_TP_NM.add("( TP)");
						EXP_OWN_TP_NM.add("TP");
					}
				}
				/////END			
			
			total_expected_delivered_m3_qty_str = nf3.format(total_expected_delivered_m3_qty);
//JHP20120524			total_expected_delivered_mmscm_qty_str = nf.format(total_expected_delivered_mmscm_qty);
			total_expected_delivered_mmscm_qty_str = nf1.format(total_expected_delivered_mmscm_qty);
						
			//Logic for finding out Total TANK Inventory & Saleable Stock on Daily basis ...
			consumption_percentage = 2.0;
			
			queryString = "SELECT A.PERCENTAGE " +
						   "FROM FMS7_CONSUMPTION_MST A WHERE " +
						   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
						   "B.EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			
			////System.out.println("Internal Consumption Percentage Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				String int_consumption = rset.getString(1)==null?"":rset.getString(1);
				if(!int_consumption.trim().equals(""))
				{
					consumption_percentage = Double.parseDouble(int_consumption);
				}
			}

			////System.out.println(".................................................."+consumption_percentage);
			
			consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
			
			double total_stock = 0;
			double t1_d1_dead_stock = 0;
			double t2_d1_dead_stock = 0;
			//double conversion_factor_from_m3lng_to_mmscm = 0.0005842;
			double conversion_factor_from_m3lng_to_mmscm = 0.000601;
			double total_dead_stock_mmscm = 19.11;
			double saleable_with_re = 0;
			
			queryString = "SELECT T1_VOLUME, T1_MMSCM, T2_VOLUME, T2_MMSCM " +
						  "FROM FMS7_INVENTORY_LEVEL_DTL WHERE " +
						  "INV_LEVEL_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
			////System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				t1_m3_of_lng = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
				t1_mmscm_of_gas = rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2)));
				t2_m3_of_lng = rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3)));
				t2_mmscm_of_gas = rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4)));
				total_stock = (rset.getString(2)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(2)))))+(rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4)))));
			}
			
			queryString = "SELECT A.TANK1_D1_VOLUME, A.TANK2_D1_VOLUME FROM FMS7_TANK_MASTER_DTL A WHERE " +
						  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
						  "B.TANK_DTL_DT<=TO_DATE('"+from_date+"','dd/mm/yyyy'))";
			////System.out.println("T1 & T2 Tank Inventory Detail Query here= "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))))*conversion_factor_from_m3lng_to_mmscm;
				t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))))*conversion_factor_from_m3lng_to_mmscm;
				
				////System.out.println("SAMIK --> Total Dead Stock = "+(t1_d1_dead_stock+t2_d1_dead_stock)+",  Actual Total Stock of TWO Tanks = "+total_stock);
				if(t1_d1_dead_stock>0 && t2_d1_dead_stock>0)
				{
					total_dead_stock_mmscm = Double.parseDouble(nf.format(t1_d1_dead_stock+t2_d1_dead_stock));
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
						saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
					}
				}
				else
				{
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
						saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
					}
				}
			}
			else
			{
				if(total_stock>total_dead_stock_mmscm)
				{
					saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
					saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
				}
			}
			
			String start_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))-1);
			
			String cut_off_date = "01/04/2011";
			
			queryString = "SELECT TO_DATE('"+cut_off_date+"','dd/mm/yyyy')-TO_DATE('"+start_date+"','dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			////System.out.println("querystring-->"+queryString);
			if(rset.next())
			{
				int outer_days_diff_count = rset.getInt(1);
				
				int inner_days_diff_count = 0;
				
				queryString2 = "SELECT TO_DATE('"+from_date+"','dd/mm/yyyy')-TO_DATE('"+cut_off_date+"','dd/mm/yyyy') FROM DUAL";
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					inner_days_diff_count = rset2.getInt(1);
				}
				
				if(outer_days_diff_count>0 && inner_days_diff_count>0)
				{
					start_date = cut_off_date;
				}
			}
						
			double regas_expected_mmscm_qty = 0;
			
			queryString = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.ACTUAL_RECPT_DT,'DD/MM/YYYY')," +
						   "A.CUSTOMER_CD,QTY_TO_BE_SUPPLY " +
						   "from FMS7_RE_GAS_CARGO_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B where " +
						   "A.ACTUAL_RECPT_DT between to_date('"+start_date+"','dd/mm/yyyy') and " +
						   "(to_date('"+from_date+"','dd/mm/yyyy')-1) AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
						   		+ "B.EMP_CD='"+Emp_cd+"' ";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				double tcq = 0;
				double alloc_qty = 0;
				
				tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(5)==null?"0":rset.getString(5))));
					
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
				   			   "where GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
							   "and CONTRACT_TYPE='R' and FGSA_NO='"+rset.getString(2)+"' and " +
							   "SN_NO='"+rset.getString(1)+"' and " +
							   "CUSTOMER_CD='"+rset.getString(4)+"'";
				////System.out.println("Re-Gas QTY Supplied to Customer Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1))));
				}
								
				regas_expected_mmscm_qty += (tcq-alloc_qty);				
			}
			
			
			
			
			
			/////////ADDED FOR LTCORA AND CN
				double LTCORA_expected_mmscm_qty = 0;
			
			queryString = "select A.CARGO_SEQ_NO,A.MAPPING_ID,TO_CHAR(A.ACTUAL_RECPT_DT,'DD/MM/YYYY')," +
						   "A.MAPPING_ID,QTY_TO_BE_SUPPLY,A.FLAG " +
						   "from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST B, SEC_EMP_CUSTOMER_ALLOC_MST C "
						   + "where B.CUSTOMER_CD=C.CUSTOMER_CD AND C.EMP_CD='"+Emp_cd+"' AND A.MAPPING_ID=B.MAPPING_ID AND " +
						   "A.ACTUAL_RECPT_DT between to_date('"+start_date+"','dd/mm/yyyy') and " +
						   "(to_date('"+from_date+"','dd/mm/yyyy')-1)";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String temp_map_id=rset.getString(2)==null?"":rset.getString(2);
				String map_id[]=temp_map_id.split("-");
				String temp_cont_type=rset.getString(6)==null?"":rset.getString(6);
				String fgsa_no="";
				String cust_cd=map_id[0];
				
				if(temp_cont_type.equalsIgnoreCase("T"))
				{
					fgsa_no=map_id[1];
				}
				else if(temp_cont_type.equalsIgnoreCase("C"))
				{
					fgsa_no=map_id[2];
				}
				
				
				
				double tcq = 0;
				double alloc_qty = 0;
				
				tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(5)==null?"0":rset.getString(5))));
					
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
				   			   "where GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
							   "and CONTRACT_TYPE='"+temp_cont_type+"' and FGSA_NO='"+fgsa_no+"' and " +
							   "SN_NO='"+rset.getString(1)+"' and " +
							   "CUSTOMER_CD='"+map_id[1]+"'";
//				//System.out.println("LTCORA QTY Supplied to Customer Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1))));
				}
								
				LTCORA_expected_mmscm_qty += (tcq-alloc_qty);				
			}
			
			///////END
			
			
			if(saleable_stock_with_re_gas.trim().equals(""))
			{
				saleable_stock = "";
			}
			else
			{
//				saleable_stock = nf.format(Double.parseDouble(saleable_stock_with_re_gas)-Double.parseDouble(nf.format((regas_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100)))-Double.parseDouble(nf.format((LTCORA_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100))));
				
				//RS 06022017 MODIFIED FOR THE DAILY REPORT TANK GRAPH ISSUE...
				saleable_stock = nf.format(Double.parseDouble(saleable_stock_with_re_gas)-Double.parseDouble(nf.format((regas_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100))));
			}
			
			String last_arrival_dt = "";
			
			if(!last_arrival_dt_own.trim().equals("") && !last_arrival_dt_tp.trim().equals(""))
			{
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+last_arrival_dt_own1.firstElement().toString().trim()+"','DD/MM/YYYY') - TO_DATE('"+last_arrival_dt_tp1.firstElement().toString().trim()+"','DD/MM/YYYY') FROM dual";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					no_of_days = rset.getInt(1);
				}
				
				if(no_of_days<0)
				{
					last_arrival_dt = last_arrival_dt_own1.firstElement().toString().trim();
				}
				else
				{
					last_arrival_dt = last_arrival_dt_tp1.firstElement().toString().trim();
				}
			}
			else if(!last_arrival_dt_own.trim().equals(""))
			{
				last_arrival_dt = last_arrival_dt_own1.firstElement().toString().trim();
			}
			else if(!last_arrival_dt_tp.trim().equals(""))
			{
				last_arrival_dt = last_arrival_dt_tp1.firstElement().toString().trim();
			}
			
			
			if(!last_arrival_dt.trim().equals(""))
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+last_arrival_dt.trim()+"','dd/mm/yyyy'),'FMDDTH MONTH') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				//	last_arrival_date = rset.getString(1);
				}
				
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+last_arrival_dt.trim()+"','DD/MM/YYYY') - (TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')-1) FROM dual";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					no_of_days = rset.getInt(1);
				}
				
				if(no_of_days>0)
				{
					if(!saleable_stock_with_re_gas.trim().equals(""))
					{
						asking_rate_to_avoid_stock_out = nf.format(Double.parseDouble(saleable_stock_with_re_gas.trim())/no_of_days);
					}
				}
			}
			double int_consumption = 0;
			if(!total_expected_delivered_mmscm_qty_str.trim().equals(""))
			{
//JHP20120524				//internal_consumption_on_proposed_volume = nf.format(Double.parseDouble(total_expected_delivered_mmscm_qty_str.trim())*(consumption_percentage/100));
//JHP20120524				//int_consumption = Double.parseDouble(total_expected_delivered_mmscm_qty_str.trim())*(consumption_percentage/100);
				
				internal_consumption_on_proposed_volume = nf.format(total_expected_delivered_mmscm_qty*(consumption_percentage/100));
				int_consumption = total_expected_delivered_mmscm_qty*(consumption_percentage/100);
		}
			
			double long_short_pos = (saleable_with_re+total_expected_delivered_mmscm_qty)-(sum_sales_firm_qty+total_regas_firm_mmscm_qty+int_consumption);
			
			if(long_short_pos>0)
			{
				long_short_position_mmscm = nf.format(long_short_pos);
			}
			else if(long_short_pos<0)
			{
				long_short_position_mmscm = "("+nf.format(long_short_pos*(-1))+")";
			}
		}	
			
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchSendOut()-->"+e);
			  e.printStackTrace();
		}
	}
	
	//ADDED BY RS29032017 FOR USER REMARK IN DAILY REPORT...
	
	public void createTable() throws SQLException
	{
		try
		{
			int count=0;
			String query="SELECT COUNT(*) FROM TABS WHERE TABLE_NAME LIKE 'FMS8_DAILY_RPT_REMARKS' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				count=rset.getInt(1);
			}
			if(count==0)
			{
				query="CREATE TABLE FMS8_DAILY_RPT_REMARKS (REMARK VARCHAR2(500) NOT NULL, "
						+ "GEN_DT DATE NOT NULL, "
						+ "ENTER_BY NUMBER(6,0), "
						+ "ENTER_DT DATE,"
						+ "FLAG CHAR(1), "
						+ "COUNTER NUMBER(2,0), "
						+ "CONSTRAINT PK_DLY_RPT_RMK PRIMARY KEY (GEN_DT,COUNTER)) ";
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
	
	String user_remark = "", emp_name = "", enter_dt = "";
	
	public void fetchUserRemark() throws SQLException
	{
		try
		{
			String query = "SELECT NVL(REMARK,''),ENTER_BY,TO_CHAR(ENTER_DT,'DD/MM/YYYY') "
					+ "FROM FMS8_DAILY_RPT_REMARKS WHERE "
					+ "GEN_DT = TO_DATE('"+from_date+"','DD/MM/YYYY') AND COUNTER = (SELECT MAX(COUNTER) FROM "
					+ "FMS8_DAILY_RPT_REMARKS WHERE GEN_DT = TO_DATE('"+from_date+"','DD/MM/YYYY')) ";
//			//System.out.println("===Query==="+query);
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				user_remark = rset.getString(1);
				enter_dt = rset.getString(3)==null?"":rset.getString(3);
				
				query = "SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD = '"+rset.getString(2)+"' ";
//				//System.out.println("===Query==="+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					emp_name = rset.getString(1).toUpperCase();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void fetchDailyReport()
	{
		try
		{
			double conversion_factor_mcm = 38900;
//			MD20111220		 double multiplying_factor_meter_cube = 0.04847;
	    double multiplying_factor_meter_cube = 0.042845;
			//Following (5) Variables are related to SN/LOA Contracts TOTAL ...
			double total_sales_gas_day_mmbtu_qty = 0;
			double total_sales_gas_day_mmscm_qty = 0;			
			double sum_sales_firm_qty = 0;
			double sum_sales_re_qty = 0;
			double sum_sales_re_firm_qty = 0;
			
			//Following (3) Variables are related to Re-GAS Contracts TOTAL ...
			double total_regas_gas_day_mmbtu_qty = 0;
			double total_regas_gas_day_mmscm_qty = 0;
			double total_regas_firm_mmscm_qty = 0;
			
			//Following (2) Variables are related to Future Cargo Arrival TOTAL ...
			double total_expected_delivered_m3_qty = 0;
			double total_expected_delivered_mmscm_qty = 0;
			
			
			Vector V_gas_dates = new Vector();
				//JHP Start			
			String query1="";
			String Temp_dt="";
			String Temp_dt1="";
			int k=7;
			int y=1;
			query1 = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+k+",'dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(query1);
			////System.out.println(query1);
			if(rset.next())
			{
				Temp_dt=rset.getString(1);
			}
			query1 = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+y+",'dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(query1);
			////System.out.println(query1);
			if(rset.next())
			{
				Temp_dt1=rset.getString(1);
			}
			query1="select DISTINCT A.CUSTOMER_CD,A.CONTRACT_TYPE from FMS7_DAILY_ALLOCATION_DTL A "
					+ ""
					+ "where A.GAS_DT between to_date('"+Temp_dt+"','dd/mm/yyyy') and to_date('"+Temp_dt1+"','dd/mm/yyyy') ";  
			////System.out.println("j::"+query1);
			rset = stmt.executeQuery(query1);
			////System.out.println(query1);
			while(rset.next())
			{
				Vparty.add(rset.getString(1));
				Vparty_cont_type.add(rset.getString(2));
			}
			
			//JHP End		
			String query = "";
			
			for(int i=7; i>=1; i--)
			{
				query = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+i+",'dd/mm/yyyy') FROM DUAL";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					V_gas_dates.add(rset.getString(1));
				}
			}
			
			if(V_gas_dates.size()==7)
			{
				daily_graph_from_date = ""+V_gas_dates.elementAt(0);
				daily_graph_to_date = ""+V_gas_dates.elementAt(6);
			}
			
			String gas_day = "";
			
			String end_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))+1);
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				gas_day = rset.getString(1);
			}
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DDth, MONTH YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				selected_month_year = rset.getString(1);
			}
			
			String expected_volume_end_date = "";
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
			
			if(gas_day.trim().length()>=10)
			{
				Vector temp_Cust_CD = new Vector();
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_SN_MST A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') between " +
							   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
							   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
							   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
							   "ORDER BY A.CUSTOMER_CD";
				////System.out.println("Distinct from FMS7_SN_MST = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_LOA_MST A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') between " +
							   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
							   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
							   "A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
							   "ORDER BY A.CUSTOMER_CD";
				////System.out.println("Distinct from FMS7_LOA_MST = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					temp_Cust_CD.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				for(int i=0; i<temp_Cust_CD.size(); i++)
				{
					int cnt = 0;
					for(int j=0; j<CUSTOMER_CD.size(); j++)
					{
						if((""+temp_Cust_CD.elementAt(i)).equals(""+CUSTOMER_CD.elementAt(j)))
						{
							++cnt;
						}
					}
					
					if(cnt==0)
					{
						CUSTOMER_CD.add(""+temp_Cust_CD.elementAt(i));
					}
				}
				
				//Following Logic Is For SN/LOA Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
							  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L')";
				////System.out.println("SN/LOA Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{*/
					//CUSTOMER_CD.add(rset.getString(1)==null?"0":rset.getString(1));
				for(int i=0; i<CUSTOMER_CD.size(); i++)
				{
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to SN/LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_sales_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_sales_gas_day_mmscm_qty += rset1.getString(1)==null?0:(Double.parseDouble(rset1.getString(2))/1000000);
						Flag1_sales=true;
					
					}
					else
					{
						
						QTY_MMBTU.add("");
						QTY_MMSCM.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
					////System.out.println("Customer Name for SN/LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM.add("");
					}
					
					String sales_end_dt = "";
					String re_end_dt = "";
					
					double sales_firm_qty = 0;
					double sales_re_firm_qty = 0;
										
					//Following Query Is Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.SN_NO,A.FGSA_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy') " +
								   "from FMS7_SN_MST A where " +
								   "(A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
								   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
								   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO) " +
								   "ORDER BY A.END_DT";*/
					
					//Following Query Is Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.SN_NO, A.FGSA_NO, TO_CHAR(A.END_DT,'dd/mm/yyyy'), A.FGSA_REV_NO, A.SN_REV_NO " +
								   "from FMS7_SN_MST A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.START_DT AND A.END_DT) OR (A.START_DT BETWEEN " +
								   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND " +
								   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
								   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_NO=B.FGSA_NO AND " +
								   "A.SN_NO=B.SN_NO) ORDER BY A.END_DT";
//					//System.out.println("Master Query for FMS7_SN_MST = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						int re_count = 0;
						int clause_cd = 3; //3 Clause CD stands for Existance of Take or Pay Clause in the Supply Notice ...
						
						queryString2 = "select COUNT(A.sn_no) " +
									   "from FMS7_SN_TOP_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.SN_NO='"+rset1.getString(1)+"' and " +
									   "A.SN_REV_NO='"+rset1.getString(5)+"' and " +
									   "A.FGSA_REV_NO='"+rset1.getString(4)+"' and " +
									   "A.CONT_TYPE='S'";
						////System.out.println("Query for checking EXISTANCE of Take or Pay Clause in FMS7_SN_TOP_DTL Table = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							re_count = rset2.getInt(1);
						}
						
						String split_tcq_flag = "N";
						String re_qty = "0";
						String firm_qty = "0";
						
						queryString2 = "select A.TCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'), A.FGSA_REV_NO, A.SN_REV_NO,"
									+ "NVL(FIRM_QTY,'0'), NVL(RE_QTY,'0'), NVL(SPLIT_TCQ_FLAG,'N') " +
									   "from FMS7_SN_MST A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.SN_NO='"+rset1.getString(1)+"' and " +
									   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where " +
									   "A.CUSTOMER_CD=B.CUSTOMER_CD and A.FGSA_NO=B.FGSA_NO and A.SN_NO=B.SN_NO)";
//						//System.out.println("2nd Master Query for FMS7_SN_MST = "+queryString1);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							split_tcq_flag = rset2.getString(7)==null?"N":rset2.getString(7);
							firm_qty = rset2.getString(5)==null?"0":rset2.getString(5);
							re_qty = rset2.getString(6)==null?"0":rset2.getString(6);
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='S' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
							//System.out.println("Re-Gas Live Distinct Customer Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							sales_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							if(re_count>0 || split_tcq_flag.equals("Y"))
							{
								if(split_tcq_flag.equals("Y"))
								{
									if(Double.parseDouble(firm_qty)>=alloc_qty)
									{
										sales_firm_qty += (Double.parseDouble(firm_qty)-alloc_qty);
									}
									else
									{
										/********ADDED FOR FIRM RE QTY DISPLAY OF SN AND LOA****ADDED BY RS07022017/***/
										double temp_data = alloc_qty - Double.parseDouble(firm_qty);
										
										sales_re_firm_qty += (Double.parseDouble(re_qty)-temp_data);
									}
								}
								else
								{
									sales_firm_qty += (tcq-alloc_qty);
								}
							}
							else
							{
								if(split_tcq_flag.equals("Y"))
								{
									sales_re_firm_qty += (Double.parseDouble(re_qty)-alloc_qty);
								}
								else
								{
									sales_re_firm_qty += (tcq-alloc_qty);
								}
							}
						}
					}
					
					
					
					//Following Query Is Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.LOA_NO,A.TENDER_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy') " +
								   "from FMS7_LOA_MST A where " +
								   "(A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
								   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
								   "A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO) " +
								   "ORDER BY A.END_DT";*/
					
					//Following Query Is Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.LOA_NO,A.TENDER_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy'),A.LOA_REV_NO " +
								   "from FMS7_LOA_MST A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.START_DT AND A.END_DT) OR (A.START_DT BETWEEN " +
								   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND " +
								   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
								   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.TENDER_NO=B.TENDER_NO AND " +
								   "A.LOA_NO=B.LOA_NO) ORDER BY A.END_DT";
					
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						int re_count = 0;
						int clause_cd = 3; //3 Clause CD stands for Existance of Take or Pay Clause in the Letter of Agreement ...
						
						String split_tcq_flag = "N";
						String re_qty = "0";
						String firm_qty = "0";
						
						queryString2 = "select COUNT(A.loa_no) " +
									   "from FMS7_LOA_TOP_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.LOA_NO='"+rset1.getString(1)+"' and " +
									   "A.LOA_REV_NO='"+rset1.getString(4)+"'";
						////System.out.println("Query for checking EXISTANCE of Take or Pay Clause in FMS7_LOA_TOP_DTL Table = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							re_count = rset2.getInt(1);
						}
						
						queryString2 = "select A.TCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_REV_NO,"
								+ "NVL(FIRM_QTY,'0'), NVL(RE_QTY,'0'), NVL(SPLIT_TCQ_FLAG,'N') " +
									   "from FMS7_LOA_MST A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.TENDER_NO='"+rset1.getString(2)+"' and " +
									   "A.LOA_NO='"+rset1.getString(1)+"' and " +
									   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where " +
									   "A.CUSTOMER_CD=B.CUSTOMER_CD and A.TENDER_NO=B.TENDER_NO and " +
									   "A.LOA_NO=B.LOA_NO)";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							split_tcq_flag = rset2.getString(6)==null?"N":rset2.getString(6);
							firm_qty = rset2.getString(4)==null?"0":rset2.getString(4);
							re_qty = rset2.getString(5)==null?"0":rset2.getString(5);
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='L' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							re_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						////System.out.println("LOA tcq = "+tcq+",  alloc_qty = "+alloc_qty);
						
						if(tcq>alloc_qty)
						{
							if(re_count>0 || split_tcq_flag.equals("Y"))
							{
								if(split_tcq_flag.equals("Y"))
								{
									if(Double.parseDouble(firm_qty)>=alloc_qty)
									{
										sales_firm_qty += (Double.parseDouble(firm_qty)-alloc_qty);
									}
									else
									{
										/********ADDED FOR FIRM RE QTY DISPLAY OF SN AND LOA //ADDED BY RS07022017****/
										double temp_data = alloc_qty - Double.parseDouble(firm_qty);
										sales_re_firm_qty += (Double.parseDouble(re_qty)-temp_data);
									}
								}
								else
								{
									sales_firm_qty += (tcq-alloc_qty);
								}
							}
							else
							{
								if(split_tcq_flag.equals("Y"))
								{
									sales_re_firm_qty += (Double.parseDouble(re_qty)-alloc_qty);
								}
								else
								{
									sales_re_firm_qty += (tcq-alloc_qty);
								}
							}
						}
						
//						if(tcq>alloc_qty)
//						{
//							if(re_count>0)
//							{
//								sales_firm_qty += (tcq-alloc_qty);
//							}
//							else
//							{
//								sales_re_firm_qty += (tcq-alloc_qty);
//							}
//						}
					}
					
					if(sales_firm_qty>0)
					{
						
						QTY_MMSCM_FIRM.add(nf.format(sales_firm_qty/conversion_factor_mcm));
					}
					else
					{
						QTY_MMSCM_FIRM.add("");
					}
					
					sum_sales_firm_qty += Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm));
					
					////System.out.println("abcd  sales_re_firm_qty--- "+sales_re_firm_qty);
					if(sales_re_firm_qty>0)
					{
						QTY_MMSCM_RE_SALES.add(nf.format(sales_re_firm_qty/conversion_factor_mcm));
					}
					else
					{
						QTY_MMSCM_RE_SALES.add("");
					}
					
					sum_sales_re_qty += Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm));
					
					if(!sales_end_dt.trim().equals("") && !re_end_dt.trim().equals(""))
					{
						//Logic to be developed for Greater from above mentioned TWO Dates ...
						SALES_END_DT.add(""+sales_end_dt.trim());
					}
					else if(!sales_end_dt.trim().equals(""))
					{
						SALES_END_DT.add(""+sales_end_dt.trim());
					}
					else if(!re_end_dt.trim().equals(""))
					{
						SALES_END_DT.add(""+re_end_dt.trim());
					}
					else
					{
						SALES_END_DT.add("");
					}
					
					if((sales_firm_qty+sales_re_firm_qty)>0)
					{
						QTY_MMSCM_TOTAL_FIRM_RE.add(nf.format(Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm))+Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm))));
					}
					else
					{
						QTY_MMSCM_TOTAL_FIRM_RE.add("");
					}
					
					sum_sales_re_firm_qty += Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm))+Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm));					
				}
				
				total_sales_gas_day_mmbtu_qty_str = nf3.format(total_sales_gas_day_mmbtu_qty);
				total_sales_gas_day_mmscm_qty_str = nf.format(total_sales_gas_day_mmscm_qty);			
				sum_sales_firm_qty_str = nf.format(sum_sales_firm_qty);
				sum_sales_re_qty_str = nf.format(sum_sales_re_qty);
				sum_sales_re_firm_qty_str = nf.format(sum_sales_re_firm_qty);
				
				
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_RE_GAS_CARGO_DTL A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
							   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
							   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							   "ORDER BY A.CUSTOMER_CD";
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				//Following Logic Is For Re-Gas Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
				   			  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND CONTRACT_TYPE='R'";
				////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));*/
				for(int i=0; i<CUSTOMER_CD_RE_GAS.size(); i++)
				{					
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND CONTRACT_TYPE='R' AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU_RE_GAS.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_regas_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM_RE_GAS.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_regas_gas_day_mmscm_qty += rset1.getString(1)==null?0:((Double.parseDouble(rset1.getString(2))/1000000));
						Flag2_Re_Gas=true;
					}
					else
					{
						
						QTY_MMBTU_RE_GAS.add("");
						QTY_MMSCM_RE_GAS.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
					////System.out.println("Customer Name for Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM_RE_GAS.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM_RE_GAS.add("");
					}
					
					String re_gas_end_dt = "";
					double regas_expected_mmscm_qty = 0;
					
					//Following Query Has Been Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "(A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' ORDER BY A.CONTRACT_END_DT";*/
					
					//Following Query Has Been Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
								   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
								   "ORDER BY A.CONTRACT_END_DT";
					
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						queryString2 = "select A.QTY_TO_BE_SUPPLY, TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
									   "from FMS7_RE_GAS_CARGO_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' and " +
									   "A.RE_GAS_NO='"+rset1.getString(2)+"' and " +
									   "A.CARGO_SEQ_NO='"+rset1.getString(1)+"'";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='R' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Allocated QTY Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							re_gas_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							regas_expected_mmscm_qty += (tcq-alloc_qty);
						}
					}
					
					RE_GAS_END_DT.add(""+re_gas_end_dt.trim());
					QTY_MMSCM_RE_GAS_FIRM.add(nf.format(regas_expected_mmscm_qty/conversion_factor_mcm));
					
					total_regas_firm_mmscm_qty += Double.parseDouble(nf.format(regas_expected_mmscm_qty/conversion_factor_mcm));
				}
				
				total_regas_gas_day_mmbtu_qty_str = nf3.format(total_regas_gas_day_mmbtu_qty);
				total_regas_gas_day_mmscm_qty_str = nf.format(total_regas_gas_day_mmscm_qty);
				total_regas_firm_mmscm_qty_str = nf.format(total_regas_firm_mmscm_qty);
				
				
				//////////////////////ADDED FOR LTCORA AND CN
				
				////GLOBAL
				
				
				//LOCAL
					double total_LTCORA_gas_day_mmbtu_qty=0;
					double total_LTCORA_gas_day_mmscm_qty=0;
					double total_LTCORA_firm_mmscm_qty=0;
				
				queryString1 = "select DISTINCT(C.CUSTOMER_CD) " +
				   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C where  A.MAPPING_ID=C.MAPPING_ID AND " +
				   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
				   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
				   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				   "ORDER BY C.CUSTOMER_CD";
				////System.out.println("..."+queryString1);
				/*queryString1 ="select DISTINCT(C.CUSTOMER_CD) from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C " +
						"where (A.CONTRACT_START_DT <= to_date('11/11/2014','dd/mm/yyyy') " +
						"AND A.CONTRACT_END_DT >= to_date('11/11/2014','dd/mm/yyyy'))  AND A.MAPPING_ID=C.MAPPING_ID " +
						" AND C.cn_rev_no=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B where " +
						"C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd  AND B.CN_NO=C.CN_NO " +
						"AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
						" D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd  AND D.agreement_no=C.agreement_no " +
						"AND D.customer_cd=C.customer_cd )) AND a.mapping_id=C.mapping_id " +
						"order by C.CUSTOMER_CD ";*/
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD_LTCORA.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				//Following Logic Is For Re-Gas Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
				   			  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND CONTRACT_TYPE='R'";
				////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));*/
				for(int i=0; i<CUSTOMER_CD_LTCORA.size(); i++)
				{					
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU_LTCORA.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_LTCORA_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM_LTCORA.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_LTCORA_gas_day_mmscm_qty += rset1.getString(1)==null?0:((Double.parseDouble(rset1.getString(2))/1000000));
						Flag2_LTCORA=true;
					}
					else
					{
						
						QTY_MMBTU_LTCORA.add("");
						QTY_MMSCM_LTCORA.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
					////System.out.println("Customer Name for Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM_LTCORA.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM_LTCORA.add("");
					}
					
					String LTCORA_end_dt = "";
					double LTCORA_expected_mmscm_qty = 0;
					
					//Following Query Has Been Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "(A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' ORDER BY A.CONTRACT_END_DT";*/
					
					//Following Query Has Been Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.CARGO_SEQ_NO,C.MAPPING_ID,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.FLAG  " +
								   "from FMS8_LNG_REGAS_CARGO_DTL A , FMS8_LNG_REGAS_MST C where  A.MAPPING_ID=C.MAPPING_ID AND " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
								   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "C.CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' " +
								   "ORDER BY A.CONTRACT_END_DT";
					////System.out.println("queryString1..........."+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						String temp_map_id=rset1.getString(2)==null?"":rset1.getString(2);
						String map_id[]=temp_map_id.split("-");
						String temp_cont_type=rset1.getString(4)==null?"":rset1.getString(4);
						String fgsa_no="";
						
						if(temp_cont_type.equalsIgnoreCase("T"))
						{
							fgsa_no=map_id[1];
						}
						else if(temp_cont_type.equalsIgnoreCase("C"))
						{
							fgsa_no=map_id[3];
						}
						////System.out.println("fgsa_no........."+fgsa_no+"map_id[2]"+temp_map_id);
						queryString2 = "select A.QTY_TO_BE_SUPPLY, TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
									   "from FMS8_LNG_REGAS_CARGO_DTL A,  FMS8_LNG_REGAS_MST C where " +
									   "C.CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' " +
									   "AND A.MAPPING_ID=C.MAPPING_ID and " +
									   "A.CARGO_SEQ_NO='"+rset1.getString(1)+"' " +
									   "AND A.MAPPING_ID='"+rset1.getString(2)+"'";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') and FGSA_NO='"+fgsa_no+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Allocated QTY Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							LTCORA_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							LTCORA_expected_mmscm_qty += (tcq-alloc_qty);
							
						}
						////System.out.println("CN   tcq......."+tcq+"alloc_qty......"+alloc_qty);
					}
					
					LTCORA_END_DT.add(""+LTCORA_end_dt.trim());
					////System.out.println("LTCORA_expected_mmscm_qty"+LTCORA_expected_mmscm_qty);
					QTY_MMSCM_LTCORA_FIRM.add(nf.format(LTCORA_expected_mmscm_qty/conversion_factor_mcm));
					total_LTCORA_firm_mmscm_qty += Double.parseDouble(nf.format(LTCORA_expected_mmscm_qty/conversion_factor_mcm));
				}
				
				total_LTCORA_gas_day_mmbtu_qty_str = nf3.format(total_LTCORA_gas_day_mmbtu_qty);
				total_LTCORA_gas_day_mmscm_qty_str = nf.format(total_LTCORA_gas_day_mmscm_qty);
				total_LTCORA_firm_mmscm_qty_str = nf.format(total_LTCORA_firm_mmscm_qty);
				
				///////////////END OF LTCORA AND CN
				
				
			}		
					
			
			Vector last_arrival_dt_own1=new Vector(); 
			Vector last_arrival_dt_tp1=new Vector(); 
			String last_arrival_dt_own = "";
			String last_arrival_dt_tp = "";
			
			//Following Logic Has Been Introduced By Samik Shah On 26th February, 2011 ...
			//Following Logic Fetches Future Caro Arrival Volumes in MMSCM & M3 of LNG ...
			queryString = "SELECT A.CARGO_REF_CD,A.CONFIRM_VOL,NVL(A.UNIT_CD,'1')," +
						  "TO_CHAR(A.DELV_FROM_DT,'dd/mm/yyyy') FROM " +
						  "FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_CARGO_NOMINATION B " +
						  "WHERE A.CARGO_REF_CD=B.CARGO_REF_CD AND (A.DELV_FROM_DT BETWEEN " +
						  "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND " +
						  "B.SHIP_CD IS NOT NULL ORDER BY A.DELV_FROM_DT";
			////System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String unit_cd = rset.getString(3)==null?"1":rset.getString(3);
				double mmbtu_qty = 0;
				
				if(unit_cd.trim().equals("1"))
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				else if(unit_cd.trim().equals("2"))
				{
					mmbtu_qty = rset.getString(2)==null?0:(Double.parseDouble(rset.getString(2))*1000000);
				}
				else
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				
				EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				//EXP_OWN_TP_NM.add("HLPL (OWN)");
				EXP_OWN_TP_NM.add("OWN");
			
				EXP_TIME_OF_ARRIVAL.add(rset.getString(4)==null?"":rset.getString(4));
				//JHP start
	//			EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
				EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
				
				double temp_qty=Double.parseDouble(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				EXP_M3_LNG.add(nf3.format(temp_qty));
				
				total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty));
//JHP20120524	total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
				total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
				//EXP_M3_LNG.add(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				//JHP End	
				
				last_arrival_dt_own = rset.getString(4)==null?"":rset.getString(4);
				last_arrival_dt_own1.add(rset.getString(4)==null?"":rset.getString(4));//JHP
				
				
				String cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);
				
				queryString1 = "SELECT A.SHIP_CD, B.SHIP_NAME FROM FMS7_CARGO_NOMINATION A, FMS7_SHIP_MST B " +
							   "WHERE A.CARGO_REF_CD='"+cargo_ref_no+"' AND A.SHIP_CD=B.SHIP_CD";
				////System.out.println("Inner Query for finding future Vessel List from FMS7_CARGO_NOMINATION = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					EXP_VESSEL_NM.add(rset1.getString(2)==null?"":rset1.getString(2));
				}
				else
				{
					EXP_VESSEL_NM.add("");
				}
			}
			
			
			queryString = "SELECT A.CARGO_REF_NO,A.CUSTOMER_CD,A.SHIP_NAME," +
						  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
						  "FROM FMS7_RE_GAS_CARGO_DTL A " +
						  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
			////System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				EXP_VESSEL_NM.add(rset.getString(3)==null?"":rset.getString(3));
				EXP_TIME_OF_ARRIVAL.add(rset.getString(5)==null?"":rset.getString(5));
				
				last_arrival_dt_tp = rset.getString(5)==null?"":rset.getString(5);
				last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
				
				double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
				//				JHP start
			//	EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
				EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
				
				double temp_qty1=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				temp_qty1=Math.floor((temp_qty1 - (temp_qty1 % 100))+ 500d);
				temp_qty1=(temp_qty1 - (temp_qty1 % 1000));
				EXP_M3_LNG.add(nf9.format(temp_qty1 ));
				
				total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty1));
	//JHP20120524			total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
		         total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//EXP_M3_LNG.add(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
//				JHP End			
			
		
				String customer_cd = rset.getString(2)==null?"0":rset.getString(2);
				String customer_nm = "";
				
				queryString1 = "SELECT A.CUSTOMER_NAME, A.CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A " +
							   "WHERE A.CUSTOMER_CD='"+customer_cd+"'";
				////System.out.println("Query for finding Customer Name & Abbr. of future Cargo Owner with respect to RE-Gas Contract = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					customer_nm = rset1.getString(2)==null?"":rset1.getString(2);
					EXP_OWN_TP_NM.add(customer_nm+"  TP  "); //ADDED BY NB20150831
					//EXP_OWN_TP_NM.add("TP");
				}
				else
				{
					//EXP_OWN_TP_NM.add("( TP)");
					EXP_OWN_TP_NM.add("TP");
				}
			}
			
			///////ADDED FOR LTCORA AND CN
			queryString = "SELECT A.CARGO_REF_NO,A.MAPPING_ID,B.SHIP_NAME," +
			  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
			  "FROM FMS8_LNG_REGAS_CARGO_DTL A , FMS7_SHIP_MST B " +
			  "WHERE A.SHIP_CD=B.SHIP_CD AND A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
				////System.out.println("Query for finding future Cargo List from FMS8_LNG_REGAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String temp_map_id=rset.getString(2)==null?"":rset.getString(2);
					String map_id[]=temp_map_id.split("-");
					
					EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
					EXP_VESSEL_NM.add(rset.getString(3)==null?"":rset.getString(3));
					EXP_TIME_OF_ARRIVAL.add(rset.getString(5)==null?"":rset.getString(5));
					
					last_arrival_dt_tp = rset.getString(5)==null?"":rset.getString(5);
					last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
					
					double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));

					////System.out.println("MMBTU QTY ---> "+mmbtu_qty);
					//	EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
					EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
					
					double temp_qty1=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					temp_qty1=Math.floor((temp_qty1 - (temp_qty1 % 100))+ 500d);
					temp_qty1=(temp_qty1 - (temp_qty1 % 1000));
					EXP_M3_LNG.add(nf9.format(temp_qty1 ));
					
					total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty1));
				//JHP20120524			total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
				   total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
					//EXP_M3_LNG.add(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
					//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
				//	JHP End			
				
				
					String customer_cd = map_id[0];
					String customer_nm = "";
					
					queryString1 = "SELECT A.CUSTOMER_NAME, A.CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A " +
								   "WHERE A.CUSTOMER_CD='"+customer_cd+"'";
					////System.out.println("Query for finding Customer Name & Abbr. of future Cargo Owner with respect to RE-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						customer_nm = rset1.getString(2)==null?"":rset1.getString(2);
						EXP_OWN_TP_NM.add(customer_nm+"  TP  "); //ADDED BY NB20150831
						//EXP_OWN_TP_NM.add("TP");
					}
					else
					{
						//EXP_OWN_TP_NM.add("( TP)");
						EXP_OWN_TP_NM.add("TP");
					}
				}
				/////END			
			
			total_expected_delivered_m3_qty_str = nf3.format(total_expected_delivered_m3_qty);
//JHP20120524			total_expected_delivered_mmscm_qty_str = nf.format(total_expected_delivered_mmscm_qty);
			total_expected_delivered_mmscm_qty_str = nf1.format(total_expected_delivered_mmscm_qty);
						
			//Logic for finding out Total TANK Inventory & Saleable Stock on Daily basis ...
			consumption_percentage = 2.0;
			
			queryString = "SELECT A.PERCENTAGE " +
						   "FROM FMS7_CONSUMPTION_MST A WHERE " +
						   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
						   "B.EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			
			////System.out.println("Internal Consumption Percentage Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				String int_consumption = rset.getString(1)==null?"":rset.getString(1);
				if(!int_consumption.trim().equals(""))
				{
					consumption_percentage = Double.parseDouble(int_consumption);
				}
			}

			////System.out.println(".................................................."+consumption_percentage);
			
			consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
			
			double total_stock = 0;
			double t1_d1_dead_stock = 0;
			double t2_d1_dead_stock = 0;
			//double conversion_factor_from_m3lng_to_mmscm = 0.0005842;
			double conversion_factor_from_m3lng_to_mmscm = 0.000601;
			double total_dead_stock_mmscm = 19.11;
			double saleable_with_re = 0;
			
			queryString = "SELECT T1_VOLUME, T1_MMSCM, T2_VOLUME, T2_MMSCM " +
						  "FROM FMS7_INVENTORY_LEVEL_DTL WHERE " +
						  "INV_LEVEL_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
			////System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				t1_m3_of_lng = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
				t1_mmscm_of_gas = rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2)));
				t2_m3_of_lng = rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3)));
				t2_mmscm_of_gas = rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4)));
				total_stock = (rset.getString(2)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(2)))))+(rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4)))));
			}
			
			queryString = "SELECT A.TANK1_D1_VOLUME, A.TANK2_D1_VOLUME FROM FMS7_TANK_MASTER_DTL A WHERE " +
						  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
						  "B.TANK_DTL_DT<=TO_DATE('"+from_date+"','dd/mm/yyyy'))";
			////System.out.println("T1 & T2 Tank Inventory Detail Query here= "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))))*conversion_factor_from_m3lng_to_mmscm;
				t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))))*conversion_factor_from_m3lng_to_mmscm;
				
				////System.out.println("SAMIK --> Total Dead Stock = "+(t1_d1_dead_stock+t2_d1_dead_stock)+",  Actual Total Stock of TWO Tanks = "+total_stock);
				if(t1_d1_dead_stock>0 && t2_d1_dead_stock>0)
				{
					total_dead_stock_mmscm = Double.parseDouble(nf.format(t1_d1_dead_stock+t2_d1_dead_stock));
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
						saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
					}
				}
				else
				{
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
						saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
					}
				}
			}
			else
			{
				if(total_stock>total_dead_stock_mmscm)
				{
					saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
					saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
				}
			}
			
			String start_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))-1);
			
			String cut_off_date = "01/04/2011";
			
			queryString = "SELECT TO_DATE('"+cut_off_date+"','dd/mm/yyyy')-TO_DATE('"+start_date+"','dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			////System.out.println("querystring-->"+queryString);
			if(rset.next())
			{
				int outer_days_diff_count = rset.getInt(1);
				
				int inner_days_diff_count = 0;
				
				queryString2 = "SELECT TO_DATE('"+from_date+"','dd/mm/yyyy')-TO_DATE('"+cut_off_date+"','dd/mm/yyyy') FROM DUAL";
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					inner_days_diff_count = rset2.getInt(1);
				}
				
				if(outer_days_diff_count>0 && inner_days_diff_count>0)
				{
					start_date = cut_off_date;
				}
			}
						
			double regas_expected_mmscm_qty = 0;
			
			queryString = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.ACTUAL_RECPT_DT,'DD/MM/YYYY')," +
						   "A.CUSTOMER_CD,QTY_TO_BE_SUPPLY " +
						   "from FMS7_RE_GAS_CARGO_DTL A where " +
						   "A.ACTUAL_RECPT_DT between to_date('"+start_date+"','dd/mm/yyyy') and " +
						   "(to_date('"+from_date+"','dd/mm/yyyy')-1)";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				double tcq = 0;
				double alloc_qty = 0;
				
				tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(5)==null?"0":rset.getString(5))));
					
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
				   			   "where GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
							   "and CONTRACT_TYPE='R' and FGSA_NO='"+rset.getString(2)+"' and " +
							   "SN_NO='"+rset.getString(1)+"' and " +
							   "CUSTOMER_CD='"+rset.getString(4)+"'";
				////System.out.println("Re-Gas QTY Supplied to Customer Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1))));
				}
								
				regas_expected_mmscm_qty += (tcq-alloc_qty);				
			}
			
			
			
			
			
			/////////ADDED FOR LTCORA AND CN
				double LTCORA_expected_mmscm_qty = 0;
			
			queryString = "select A.CARGO_SEQ_NO,A.MAPPING_ID,TO_CHAR(A.ACTUAL_RECPT_DT,'DD/MM/YYYY')," +
						   "A.MAPPING_ID,QTY_TO_BE_SUPPLY,FLAG " +
						   "from FMS8_LNG_REGAS_CARGO_DTL A where " +
						   "A.ACTUAL_RECPT_DT between to_date('"+start_date+"','dd/mm/yyyy') and " +
						   "(to_date('"+from_date+"','dd/mm/yyyy')-1)";
			////System.out.println("qerusdfjo=="+from_date+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String temp_map_id=rset.getString(2)==null?"":rset.getString(2);
				String map_id[]=temp_map_id.split("-");
				String temp_cont_type=rset.getString(6)==null?"":rset.getString(6);
				String fgsa_no="";
				String cust_cd=map_id[0];
				
				if(temp_cont_type.equalsIgnoreCase("T"))
				{
					fgsa_no=map_id[1];
				}
				else if(temp_cont_type.equalsIgnoreCase("C"))
				{
					fgsa_no=map_id[2];
				}
				
				
				
				double tcq = 0;
				double alloc_qty = 0;
				
				tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(5)==null?"0":rset.getString(5))));
					
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
				   			   "where GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
							   "and CONTRACT_TYPE='"+temp_cont_type+"' and FGSA_NO='"+fgsa_no+"' and " +
							   "SN_NO='"+rset.getString(1)+"' and " +
							   "CUSTOMER_CD='"+map_id[1]+"'";
				////System.out.println("LTCORA QTY Supplied to Customer Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1))));
				}
								
				LTCORA_expected_mmscm_qty += (tcq-alloc_qty);				
			}
			
			///////END
			
			
			if(saleable_stock_with_re_gas.trim().equals(""))
			{
				saleable_stock = "";
			}
			else
			{
//				saleable_stock = nf.format(Double.parseDouble(saleable_stock_with_re_gas)-Double.parseDouble(nf.format((regas_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100)))-Double.parseDouble(nf.format((LTCORA_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100))));
		//RS 06022016 FOR SALEABLE STOCK FOR THE TANK GRAPH DISPLAY
				saleable_stock = nf.format(Double.parseDouble(saleable_stock_with_re_gas)-Double.parseDouble(nf.format((regas_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100))));
			}
			
			String last_arrival_dt = "";
			
			if(!last_arrival_dt_own.trim().equals("") && !last_arrival_dt_tp.trim().equals(""))
			{
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+last_arrival_dt_own1.firstElement().toString().trim()+"','DD/MM/YYYY') - TO_DATE('"+last_arrival_dt_tp1.firstElement().toString().trim()+"','DD/MM/YYYY') FROM dual";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					no_of_days = rset.getInt(1);
				}
				
				if(no_of_days<0)
				{
					last_arrival_dt = last_arrival_dt_own1.firstElement().toString().trim();
				}
				else
				{
					last_arrival_dt = last_arrival_dt_tp1.firstElement().toString().trim();
				}
			}
			else if(!last_arrival_dt_own.trim().equals(""))
			{
				last_arrival_dt = last_arrival_dt_own1.firstElement().toString().trim();
			}
			else if(!last_arrival_dt_tp.trim().equals(""))
			{
				last_arrival_dt = last_arrival_dt_tp1.firstElement().toString().trim();
			}
			
			
			if(!last_arrival_dt.trim().equals(""))
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+last_arrival_dt.trim()+"','dd/mm/yyyy'),'FMDDTH MONTH') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				//	last_arrival_date = rset.getString(1);
				}
				
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+last_arrival_dt.trim()+"','DD/MM/YYYY') - (TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')-1) FROM dual";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					no_of_days = rset.getInt(1);
				}
				
				if(no_of_days>0)
				{
					if(!saleable_stock_with_re_gas.trim().equals(""))
					{
						asking_rate_to_avoid_stock_out = nf.format(Double.parseDouble(saleable_stock_with_re_gas.trim())/no_of_days);
					}
				}
			}
			double int_consumption = 0;
			if(!total_expected_delivered_mmscm_qty_str.trim().equals(""))
			{
//JHP20120524				//internal_consumption_on_proposed_volume = nf.format(Double.parseDouble(total_expected_delivered_mmscm_qty_str.trim())*(consumption_percentage/100));
//JHP20120524				//int_consumption = Double.parseDouble(total_expected_delivered_mmscm_qty_str.trim())*(consumption_percentage/100);
				
				internal_consumption_on_proposed_volume = nf.format(total_expected_delivered_mmscm_qty*(consumption_percentage/100));
				int_consumption = total_expected_delivered_mmscm_qty*(consumption_percentage/100);
			}
			
			double long_short_pos = (saleable_with_re+total_expected_delivered_mmscm_qty)-(sum_sales_firm_qty+total_regas_firm_mmscm_qty+int_consumption);
			
			if(long_short_pos>0)
			{
				long_short_position_mmscm = nf.format(long_short_pos);
			}
			else if(long_short_pos<0)
			{
				long_short_position_mmscm = "("+nf.format(long_short_pos*(-1))+")";
			}
		}	
			
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchSendOut()-->"+e);
			  e.printStackTrace();
		}
	}
	
	
	String final_last_arrival_date="";
	String asking_rate_dt="";
	
	public void fetchaskingrate_WITH_ACCESS()
	{
		try
		{

			double conversion_factor_mcm = 38900;
//MD20111220 double multiplying_factor_meter_cube = 0.04847;
			double multiplying_factor_meter_cube = 0.042845;
			//Following (5) Variables are related to SN/LOA Contracts TOTAL ...
			double total_sales_gas_day_mmbtu_qty = 0;
			double total_sales_gas_day_mmscm_qty = 0;			
			double sum_sales_firm_qty = 0;
			double sum_sales_re_qty = 0;
			double sum_sales_re_firm_qty = 0;
			
			//Following (3) Variables are related to Re-GAS Contracts TOTAL ...
			double total_regas_gas_day_mmbtu_qty = 0;
			double total_regas_gas_day_mmscm_qty = 0;
			double total_regas_firm_mmscm_qty = 0;
			
			//Following (2) Variables are related to Future Cargo Arrival TOTAL ...
			double total_expected_delivered_m3_qty = 0;
			double total_expected_delivered_mmscm_qty = 0;
			String saleable_stock_with_re_gas_1="";
			
						
			String query = "";
			String expected_volume_end_date = "";
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
					
			Vector last_arrival_dt_own1=new Vector(); 
			Vector last_arrival_dt_tp1=new Vector(); 
	       Vector EXP_M3=new Vector();
			//Following Logic Has Been Introduced By Samik Shah On 26th February, 2011 ...
			//Following Logic Fetches Future Caro Arrival Volumes in MMSCM & M3 of LNG ...
			queryString = "SELECT A.CARGO_REF_CD,A.CONFIRM_VOL,NVL(A.UNIT_CD,'1')," +
						  "TO_CHAR(A.DELV_FROM_DT,'dd/mm/yyyy') FROM " +
						  "FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_CARGO_NOMINATION B " +
						  "WHERE A.CARGO_REF_CD=B.CARGO_REF_CD AND (A.DELV_FROM_DT BETWEEN " +
						  "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND " +
						  "B.SHIP_CD IS NOT NULL ORDER BY A.DELV_FROM_DT";
		//	//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String unit_cd = rset.getString(3)==null?"1":rset.getString(3);
				double mmbtu_qty = 0;
				
				if(unit_cd.trim().equals("1"))
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				else if(unit_cd.trim().equals("2"))
				{
					mmbtu_qty = rset.getString(2)==null?0:(Double.parseDouble(rset.getString(2))*1000000);
				}
				else
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				
				//JHP S
				double temp_qty=Double.parseDouble(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				//JHP E
				EXP_M3.add(nf2.format(temp_qty));
				//EXP_M3.add(nf2.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				last_arrival_dt_own1.add(rset.getString(4)==null?"":rset.getString(4));//JHP
			}
			
			queryString = "SELECT A.CARGO_REF_NO,A.CUSTOMER_CD,A.SHIP_NAME," +
						  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
						  "FROM FMS7_RE_GAS_CARGO_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
						  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
						  		+ "B.EMP_CD='"+Emp_cd+"' "
						  		+ " ORDER BY A.EDQ_FROM_DT";
			////System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
				
				double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
				
			//				JHP S
				double temp_qty=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				//JHP E
				
				//EXP_M3.add(nf2.format(mmbtu_qty*multiplying_factor_meter_cube));
				EXP_M3.add(nf2.format(temp_qty));
			}
			
			queryString = "SELECT A.CARGO_REF_NO,A.MAPPING_ID,B.SHIP_NAME," +
			  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
			  "FROM FMS8_LNG_REGAS_CARGO_DTL A , FMS7_SHIP_MST B, FMS8_LNG_REGAS_MST C, SEC_EMP_CUSTOMER_ALLOC_MST D " +
			  "WHERE A.SHIP_CD=B.SHIP_CD AND A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') AND C.CUSTOMER_CD=D.CUSTOMER_CD AND "
			  		+ "D.EMP_CD='"+Emp_cd+"' AND C.MAPPING_ID=A.MAPPING_ID "
			  		+ " ORDER BY A.EDQ_FROM_DT";
				////System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
					
					double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
					
					double temp_qty=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
					temp_qty=(temp_qty - (temp_qty % 1000));
					
					//EXP_M3.add(nf2.format(mmbtu_qty*multiplying_factor_meter_cube));
					EXP_M3.add(nf2.format(temp_qty));
				}
			
			consumption_percentage = 2.0;
			
			queryString = "SELECT A.PERCENTAGE " +
						   "FROM FMS7_CONSUMPTION_MST A WHERE " +
						   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
						   "B.EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			
			////System.out.println("Internal Consumption Percentage Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				String int_consumption = rset.getString(1)==null?"":rset.getString(1);
				if(!int_consumption.trim().equals(""))
				{
					consumption_percentage = Double.parseDouble(int_consumption);
				}
			}

			consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
						
		
			double total_stock = 0;
			double t1_d1_dead_stock = 0;
			double t2_d1_dead_stock = 0;
			double total_capacity=0;
			//double conversion_factor_from_m3lng_to_mmscm = 0.0005842;
			double conversion_factor_from_m3lng_to_mmscm = 0.000601;
			double total_dead_stock_mmscm = 19.11;
			double conv_factor_m3_to_mmscm=0;
			
			queryString = "SELECT T1_VOLUME, T1_MMSCM, T2_VOLUME, T2_MMSCM ,T1_CONV_FACTOR_1,T2_CONV_FACTOR_1 " +
						  "FROM FMS7_INVENTORY_LEVEL_DTL WHERE " +
						  "INV_LEVEL_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
			////System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				//t1_m3_of_lng = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
			//	t1_mmscm_of_gas = rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2)));
			//	t2_m3_of_lng = rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3)));
			//	t2_mmscm_of_gas = rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4)));
				total_stock = (rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1)))))+(rset.getString(3)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(3)))));
				conv_factor_m3_to_mmscm= ((rset.getString(5)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(5)))))+(rset.getString(6)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(6))))));
			}
			
			queryString = "SELECT A.TANK1_D1_VOLUME, A.TANK2_D1_VOLUME,A.TANK1_T1_VOLUME, A.TANK1_T2_VOLUME FROM FMS7_TANK_MASTER_DTL A WHERE " +
						  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
						  "B.TANK_DTL_DT<=TO_DATE('"+from_date+"','dd/mm/yyyy'))";
			////System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				//t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))))*conversion_factor_from_m3lng_to_mmscm;
				//t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))))*conversion_factor_from_m3lng_to_mmscm;
				
				t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
				total_capacity=(rset.getString(3)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(3)))))+(rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4)))));
			//	//System.out.println("JAIMIN --> Total Dead Stock = "+(t1_d1_dead_stock+t2_d1_dead_stock)+",  Actual Total Stock of TWO Tanks = "+total_stock);
				if(t1_d1_dead_stock>0 && t2_d1_dead_stock>0)
				{
					total_dead_stock_mmscm = Double.parseDouble(nf.format(t1_d1_dead_stock+t2_d1_dead_stock));
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
						
					}
				}
				else
				{
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
						
					}
				}
			}
			else
			{
				if(total_stock>total_dead_stock_mmscm)
				{
					saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
					//saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));		
					
				}
			}
			
			////System.out.println("JAIMIN --> Total Dead Stock = "+total_capacity+" "+total_stock);
			////System.out.println("saleable_stock_with_re_gas_1:"+saleable_stock_with_re_gas_1);
			
		
			//JHP start
			String temp="";
            Vector last_arrival_dt1=new Vector();
            Vector final_value=new Vector();
            Vector last_arrival_date1=new Vector();
            int total_dt=last_arrival_dt_own1.size()+last_arrival_dt_tp1.size();
            for(int i=0;i<last_arrival_dt_own1.size();i++)
			{
			
				//JHP last_arrival_dt = last_arrival_dt_own.trim();
				
				last_arrival_dt1 .add(last_arrival_dt_own1.elementAt(i).toString().trim());
			}
			for(int i=0;i<last_arrival_dt_tp1.size();i++)
			{
				last_arrival_dt1.add(last_arrival_dt_tp1.elementAt(i).toString().trim());
			
			}
			//JHP END
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy"); 
			Vector a = new Vector(); 
			
			String[] t_m;
			String[] EXP_M3_QTY_Temp;
			String t1="";
			String t2="";
			t_m=new String[total_dt];
			EXP_M3_QTY_Temp=new String[total_dt];
			for(int i=0;i<total_dt;i++)
			{
				t_m[i]=last_arrival_dt1.elementAt(i).toString();
				EXP_M3_QTY_Temp[i]=EXP_M3.elementAt(i).toString();
			}
			/*
			for(int i=0;i<total_dt;i++)
			{		
			//System.out.println("UNSROTED DATE:"+t_m[i]+"UNSORTED EXP_M3_QTY_Temp[i]::"+EXP_M3_QTY_Temp[i]);
			}
			*/
			for(int i = 0; i < total_dt; i++)
			{
				  for(int j = 1; j < (total_dt-i); j++)
				  {
					  
					  if(Double.parseDouble(t_m[j-1].substring(6)) > Double.parseDouble(t_m[j].substring(6)))
					  {  
						  t1 = t_m[j-1];
						  t_m[j-1]=t_m[j];
						  t_m[j]=t1;
						  
						  t2 = EXP_M3_QTY_Temp[j-1];
						  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
						  EXP_M3_QTY_Temp[j]=t2;
					  }
					  else if(Double.parseDouble(t_m[j-1].substring(6))== Double.parseDouble(t_m[j].substring(6)))
					  {
					  if(Double.parseDouble(t_m[j-1].substring(3,5)) > Double.parseDouble(t_m[j].substring(3,5)))
					  {
					  t1 = t_m[j-1];
					  t_m[j-1]=t_m[j];
					  t_m[j]=t1;
					  
					  t2 = EXP_M3_QTY_Temp[j-1];
					  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
					  EXP_M3_QTY_Temp[j]=t2;
					  }
					  else if(Double.parseDouble(t_m[j-1].substring(3,5)) == Double.parseDouble(t_m[j].substring(3,5)))
					  {
						  if(Double.parseDouble(t_m[j-1].substring(0,2)) > Double.parseDouble(t_m[j].substring(0,2)))
						  {
							  t1 = t_m[j-1];
							  t_m[j-1]=t_m[j];
							  t_m[j]=t1;  
							  
							  t2 = EXP_M3_QTY_Temp[j-1];
							  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
							  EXP_M3_QTY_Temp[j]=t2;
						  }
					  }
				  }
				  }
				
			}
			
//			for(int i=0;i<total_dt;i++)
//			{		
//			//System.out.println("SORTED DATE:"+t_m[i]+"SORTED EXP_M3_QTY_Temp[i]::"+EXP_M3_QTY_Temp[i]);
//			}
			//JHP Start
			
			
			String sum="0";
			Vector v=new Vector();
			for(int i=0;i<total_dt;i++)
			{
				////System.out.println("HERE 2.........."+t_m[i]);
				
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+t_m[i].toString().trim()+"','DD/MM/YYYY') - (TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')-1) FROM dual";
				////System.out.println("003:"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					//no_of_days = rset.getInt(1);//JHP20120524
					no_of_days = rset.getInt(1)-1;
				}
				////System.out.println("no_of_days:"+no_of_days);
				sum=""+(Double.parseDouble(sum)+Double.parseDouble(EXP_M3_QTY_Temp[i]));
				if(no_of_days>0)
				{
				//	//System.out.println("HERE 3.........."+saleable_stock_with_re_gas);
					if(!saleable_stock_with_re_gas.trim().equals(""))
					{
						/////System.out.println("HERE 5..........");
						////System.out.println(total_stock+"-"+saleable_stock_with_re_gas_1+"+"+sum+"/"+(no_of_days+0.5)+"conv_factor_m3_to_mmscm::"+conv_factor_m3_to_mmscm/2);
						//temp = nf.format((((Double.parseDouble(saleable_stock_with_re_gas_1.trim())+Double.parseDouble(sum))-total_capacity)/no_of_days)*((conv_factor_m3_to_mmscm/2)/1000000));
//JHP20120426			temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/no_of_days)*((conv_factor_m3_to_mmscm/2)/1000000));
						
//						temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+0.5))*((conv_factor_m3_to_mmscm/2)/1000000));
//						JHP20121121 start
						if(Double.parseDouble(EXP_M3_QTY_Temp[i])>=158000)
						{
							temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+1))*((conv_factor_m3_to_mmscm/2)/1000000));
						}
						else
						{
							temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+0.5))*((conv_factor_m3_to_mmscm/2)/1000000));	
						}
//JHP20121121   END
					
					//	//System.out.println("JAIMIN::"+temp);
						
						if(Double.parseDouble(temp)<0)
						{
							temp="0";
							final_value.add("0");
							v.add(new Double(temp));
						}
						else
						{
							final_value.add(temp);
							v.add(new Double(temp));
						}
						
					}
				}//JHP20130214 start
				else
				{
					////System.out.println("HERE 4..........");
					temp="0";
					final_value.add("0");
					v.add(new Double(temp));
				}
				//JHP20130214 start
			}
			
			////System.out.println("JHP:"+final_value);
			////System.out.println("JHP:"+v);
			//JHP End
			
						
			////System.out.println("total_expected_delivered_mmscm_qty_str = "+total_expected_delivered_mmscm_qty_str+",  internal_consumption_on_proposed_volume = "+internal_consumption_on_proposed_volume);
			if(v.size()>0)
			{
			Object obj = Collections.max(v);
			
			asking_rate_required_to_accommodate_cargo= obj.toString();
           // //System.out.println("Maximum Element of Java Vector is : " + obj);
		    
				if(!asking_rate_dt.equalsIgnoreCase("")){
					for(int i=0;i<total_dt;i++)
					{
						if(asking_rate_dt.equals(t_m[i].toString().trim()))
						{
							obj=v.elementAt(i);
							asking_rate_required_to_accommodate_cargo= obj.toString();
						}
					}
				}
			
			
			}
			
			////System.out.println("vsize.........."+v.size());
			for(int i=0;i<total_dt;i++)
			{
				if(v.size()>0) {
				if(asking_rate_required_to_accommodate_cargo.equals(v.elementAt(i).toString()))
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+t_m[i].toString().trim()+"','dd/mm/yyyy'),'FMDDTH MONTH') FROM DUAL";
					////System.out.println("002:"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						last_arrival_date=rset.getString(1);
						final_last_arrival_date=t_m[i].toString().trim();
					}
				}
				} 
			}
		}
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchfetchaskingrate()-->"+e);
			  e.printStackTrace();
		}
	}
	

	public void fetchaskingrate()
	{
		try
		{

			double conversion_factor_mcm = 38900;
//MD20111220 double multiplying_factor_meter_cube = 0.04847;
			double multiplying_factor_meter_cube = 0.042845;
			//Following (5) Variables are related to SN/LOA Contracts TOTAL ...
			double total_sales_gas_day_mmbtu_qty = 0;
			double total_sales_gas_day_mmscm_qty = 0;			
			double sum_sales_firm_qty = 0;
			double sum_sales_re_qty = 0;
			double sum_sales_re_firm_qty = 0;
			
			//Following (3) Variables are related to Re-GAS Contracts TOTAL ...
			double total_regas_gas_day_mmbtu_qty = 0;
			double total_regas_gas_day_mmscm_qty = 0;
			double total_regas_firm_mmscm_qty = 0;
			
			//Following (2) Variables are related to Future Cargo Arrival TOTAL ...
			double total_expected_delivered_m3_qty = 0;
			double total_expected_delivered_mmscm_qty = 0;
			String saleable_stock_with_re_gas_1="";
			
						
			String query = "";
			String expected_volume_end_date = "";
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
					
			Vector last_arrival_dt_own1=new Vector(); 
			Vector last_arrival_dt_tp1=new Vector(); 
	       Vector EXP_M3=new Vector();
			//Following Logic Has Been Introduced By Samik Shah On 26th February, 2011 ...
			//Following Logic Fetches Future Caro Arrival Volumes in MMSCM & M3 of LNG ...
			queryString = "SELECT A.CARGO_REF_CD,A.CONFIRM_VOL,NVL(A.UNIT_CD,'1')," +
						  "TO_CHAR(A.DELV_FROM_DT,'dd/mm/yyyy') FROM " +
						  "FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_CARGO_NOMINATION B " +
						  "WHERE A.CARGO_REF_CD=B.CARGO_REF_CD AND (A.DELV_FROM_DT BETWEEN " +
						  "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND " +
						  "B.SHIP_CD IS NOT NULL ORDER BY A.DELV_FROM_DT";
		//	//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String unit_cd = rset.getString(3)==null?"1":rset.getString(3);
				double mmbtu_qty = 0;
				
				if(unit_cd.trim().equals("1"))
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				else if(unit_cd.trim().equals("2"))
				{
					mmbtu_qty = rset.getString(2)==null?0:(Double.parseDouble(rset.getString(2))*1000000);
				}
				else
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				
				//JHP S
				double temp_qty=Double.parseDouble(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				//JHP E
				EXP_M3.add(nf2.format(temp_qty));
				//EXP_M3.add(nf2.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				last_arrival_dt_own1.add(rset.getString(4)==null?"":rset.getString(4));//JHP
			}
			
			queryString = "SELECT A.CARGO_REF_NO,A.CUSTOMER_CD,A.SHIP_NAME," +
						  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
						  "FROM FMS7_RE_GAS_CARGO_DTL A " +
						  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
			////System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
				
				double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
				
			//				JHP S
				double temp_qty=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				//JHP E
				
				//EXP_M3.add(nf2.format(mmbtu_qty*multiplying_factor_meter_cube));
				EXP_M3.add(nf2.format(temp_qty));
			}
			
			queryString = "SELECT A.CARGO_REF_NO,A.MAPPING_ID,B.SHIP_NAME," +
			  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
			  "FROM FMS8_LNG_REGAS_CARGO_DTL A , FMS7_SHIP_MST B " +
			  "WHERE A.SHIP_CD=B.SHIP_CD AND A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
				////System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
					
					double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
					
					double temp_qty=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
					temp_qty=(temp_qty - (temp_qty % 1000));
					
					//EXP_M3.add(nf2.format(mmbtu_qty*multiplying_factor_meter_cube));
					EXP_M3.add(nf2.format(temp_qty));
				}
			
			consumption_percentage = 2.0;
			
			queryString = "SELECT A.PERCENTAGE " +
						   "FROM FMS7_CONSUMPTION_MST A WHERE " +
						   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
						   "B.EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			
			////System.out.println("Internal Consumption Percentage Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				String int_consumption = rset.getString(1)==null?"":rset.getString(1);
				if(!int_consumption.trim().equals(""))
				{
					consumption_percentage = Double.parseDouble(int_consumption);
				}
			}

			consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
						
		
			double total_stock = 0;
			double t1_d1_dead_stock = 0;
			double t2_d1_dead_stock = 0;
			double total_capacity=0;
			//double conversion_factor_from_m3lng_to_mmscm = 0.0005842;
			double conversion_factor_from_m3lng_to_mmscm = 0.000601;
			double total_dead_stock_mmscm = 19.11;
			double conv_factor_m3_to_mmscm=0;
			
			queryString = "SELECT T1_VOLUME, T1_MMSCM, T2_VOLUME, T2_MMSCM ,T1_CONV_FACTOR_1,T2_CONV_FACTOR_1 " +
						  "FROM FMS7_INVENTORY_LEVEL_DTL WHERE " +
						  "INV_LEVEL_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
			////System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				//t1_m3_of_lng = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
			//	t1_mmscm_of_gas = rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2)));
			//	t2_m3_of_lng = rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3)));
			//	t2_mmscm_of_gas = rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4)));
				total_stock = (rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1)))))+(rset.getString(3)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(3)))));
				conv_factor_m3_to_mmscm= ((rset.getString(5)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(5)))))+(rset.getString(6)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(6))))));
			}
			
			queryString = "SELECT A.TANK1_D1_VOLUME, A.TANK2_D1_VOLUME,A.TANK1_T1_VOLUME, A.TANK1_T2_VOLUME FROM FMS7_TANK_MASTER_DTL A WHERE " +
						  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
						  "B.TANK_DTL_DT<=TO_DATE('"+from_date+"','dd/mm/yyyy'))";
			////System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				//t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))))*conversion_factor_from_m3lng_to_mmscm;
				//t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))))*conversion_factor_from_m3lng_to_mmscm;
				
				t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
				total_capacity=(rset.getString(3)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(3)))))+(rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4)))));
			//	//System.out.println("JAIMIN --> Total Dead Stock = "+(t1_d1_dead_stock+t2_d1_dead_stock)+",  Actual Total Stock of TWO Tanks = "+total_stock);
				if(t1_d1_dead_stock>0 && t2_d1_dead_stock>0)
				{
					total_dead_stock_mmscm = Double.parseDouble(nf.format(t1_d1_dead_stock+t2_d1_dead_stock));
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
						
					}
				}
				else
				{
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
						
					}
				}
			}
			else
			{
				if(total_stock>total_dead_stock_mmscm)
				{
					saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
					//saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));		
					
				}
			}
			
			////System.out.println("JAIMIN --> Total Dead Stock = "+total_capacity+" "+total_stock);
			////System.out.println("saleable_stock_with_re_gas_1:"+saleable_stock_with_re_gas_1);
			
		
			//JHP start
			String temp="";
            Vector last_arrival_dt1=new Vector();
            Vector final_value=new Vector();
            Vector last_arrival_date1=new Vector();
            int total_dt=last_arrival_dt_own1.size()+last_arrival_dt_tp1.size();
            for(int i=0;i<last_arrival_dt_own1.size();i++)
			{
			
				//JHP last_arrival_dt = last_arrival_dt_own.trim();
				
				last_arrival_dt1 .add(last_arrival_dt_own1.elementAt(i).toString().trim());
			}
			for(int i=0;i<last_arrival_dt_tp1.size();i++)
			{
				last_arrival_dt1.add(last_arrival_dt_tp1.elementAt(i).toString().trim());
			
			}
			//JHP END
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy"); 
			Vector a = new Vector(); 
			
			String[] t_m;
			String[] EXP_M3_QTY_Temp;
			String t1="";
			String t2="";
			t_m=new String[total_dt];
			EXP_M3_QTY_Temp=new String[total_dt];
			for(int i=0;i<total_dt;i++)
			{
				t_m[i]=last_arrival_dt1.elementAt(i).toString();
				EXP_M3_QTY_Temp[i]=EXP_M3.elementAt(i).toString();
			}
			/*
			for(int i=0;i<total_dt;i++)
			{		
			//System.out.println("UNSROTED DATE:"+t_m[i]+"UNSORTED EXP_M3_QTY_Temp[i]::"+EXP_M3_QTY_Temp[i]);
			}
			*/
			for(int i = 0; i < total_dt; i++)
			{
				  for(int j = 1; j < (total_dt-i); j++)
				  {
					  
					  if(Double.parseDouble(t_m[j-1].substring(6)) > Double.parseDouble(t_m[j].substring(6)))
					  {  
						  t1 = t_m[j-1];
						  t_m[j-1]=t_m[j];
						  t_m[j]=t1;
						  
						  t2 = EXP_M3_QTY_Temp[j-1];
						  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
						  EXP_M3_QTY_Temp[j]=t2;
					  }
					  else if(Double.parseDouble(t_m[j-1].substring(6))== Double.parseDouble(t_m[j].substring(6)))
					  {
					  if(Double.parseDouble(t_m[j-1].substring(3,5)) > Double.parseDouble(t_m[j].substring(3,5)))
					  {
					  t1 = t_m[j-1];
					  t_m[j-1]=t_m[j];
					  t_m[j]=t1;
					  
					  t2 = EXP_M3_QTY_Temp[j-1];
					  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
					  EXP_M3_QTY_Temp[j]=t2;
					  }
					  else if(Double.parseDouble(t_m[j-1].substring(3,5)) == Double.parseDouble(t_m[j].substring(3,5)))
					  {
						  if(Double.parseDouble(t_m[j-1].substring(0,2)) > Double.parseDouble(t_m[j].substring(0,2)))
						  {
							  t1 = t_m[j-1];
							  t_m[j-1]=t_m[j];
							  t_m[j]=t1;  
							  
							  t2 = EXP_M3_QTY_Temp[j-1];
							  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
							  EXP_M3_QTY_Temp[j]=t2;
						  }
					  }
				  }
				  }
				
			}
			
//			for(int i=0;i<total_dt;i++)
//			{		
//			//System.out.println("SORTED DATE:"+t_m[i]+"SORTED EXP_M3_QTY_Temp[i]::"+EXP_M3_QTY_Temp[i]);
//			}
			//JHP Start
			
			
			String sum="0";
			Vector v=new Vector();
			for(int i=0;i<total_dt;i++)
			{
				////System.out.println("HERE 2.........."+t_m[i]);
				
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+t_m[i].toString().trim()+"','DD/MM/YYYY') - (TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')-1) FROM dual";
				////System.out.println("003:"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					//no_of_days = rset.getInt(1);//JHP20120524
					no_of_days = rset.getInt(1)-1;
				}
				////System.out.println("no_of_days:"+no_of_days);
				sum=""+(Double.parseDouble(sum)+Double.parseDouble(EXP_M3_QTY_Temp[i]));
				if(no_of_days>0)
				{
				//	//System.out.println("HERE 3.........."+saleable_stock_with_re_gas);
					if(!saleable_stock_with_re_gas.trim().equals(""))
					{
						/////System.out.println("HERE 5..........");
						////System.out.println(total_stock+"-"+saleable_stock_with_re_gas_1+"+"+sum+"/"+(no_of_days+0.5)+"conv_factor_m3_to_mmscm::"+conv_factor_m3_to_mmscm/2);
						//temp = nf.format((((Double.parseDouble(saleable_stock_with_re_gas_1.trim())+Double.parseDouble(sum))-total_capacity)/no_of_days)*((conv_factor_m3_to_mmscm/2)/1000000));
//JHP20120426			temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/no_of_days)*((conv_factor_m3_to_mmscm/2)/1000000));
						
//						temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+0.5))*((conv_factor_m3_to_mmscm/2)/1000000));
//						JHP20121121 start
						if(Double.parseDouble(EXP_M3_QTY_Temp[i])>=158000)
						{
							temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+1))*((conv_factor_m3_to_mmscm/2)/1000000));
						}
						else
						{
							temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+0.5))*((conv_factor_m3_to_mmscm/2)/1000000));	
						}
//JHP20121121   END
					
					//	//System.out.println("JAIMIN::"+temp);
						
						if(Double.parseDouble(temp)<0)
						{
							temp="0";
							final_value.add("0");
							v.add(new Double(temp));
						}
						else
						{
							final_value.add(temp);
							v.add(new Double(temp));
						}
						
					}
				}//JHP20130214 start
				else
				{
					////System.out.println("HERE 4..........");
					temp="0";
					final_value.add("0");
					v.add(new Double(temp));
				}
				//JHP20130214 start
			}
			
			////System.out.println("JHP:"+final_value);
			////System.out.println("JHP:"+v);
			//JHP End
			
						
			////System.out.println("total_expected_delivered_mmscm_qty_str = "+total_expected_delivered_mmscm_qty_str+",  internal_consumption_on_proposed_volume = "+internal_consumption_on_proposed_volume);
			if(v.size()>0)
			{
			Object obj = Collections.max(v);
			
			asking_rate_required_to_accommodate_cargo= obj.toString();
           // //System.out.println("Maximum Element of Java Vector is : " + obj);
		    
				if(!asking_rate_dt.equalsIgnoreCase("")){
					for(int i=0;i<total_dt;i++)
					{
						if(asking_rate_dt.equals(t_m[i].toString().trim()))
						{
							obj=v.elementAt(i);
							asking_rate_required_to_accommodate_cargo= obj.toString();
						}
					}
				}
			
			
			}
			
			////System.out.println("vsize.........."+v.size());
			for(int i=0;i<total_dt;i++)
			{
				if(v.size()>0) {
				if(asking_rate_required_to_accommodate_cargo.equals(v.elementAt(i).toString()))
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+t_m[i].toString().trim()+"','dd/mm/yyyy'),'FMDDTH MONTH') FROM DUAL";
					////System.out.println("002:"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						last_arrival_date=rset.getString(1);
						final_last_arrival_date=t_m[i].toString().trim();
					}
				}
				} 
			}
		}
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchfetchaskingrate()-->"+e);
			  e.printStackTrace();
		}
	}
	
//	/Logic for Dynamic parliament sector
	public void sector_specification(){				
		  mnth="04";
		  String mm="04";
		  String yy="";
		  String year1="";
	try{	
		queryString = "select SECTOR_CD, SECTOR_ABBR FROM FMS7_SECTOR_MST WHERE FLAG='P'  ORDER BY priority";
		////System.out.println("Sector Selection Sector Master Query = "+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			SECTOR_CD.add(rset.getString(1)==null?"":rset.getString(1));
			SECTOR_NM.add(rset.getString(2)==null?"":rset.getString(2));
		}
		////System.out.println("SECTOR_CD.size()::"+SECTOR_CD.size()+"year:"+year);
		if(!year.trim().equals("0") && !year.trim().equals(""))
		 {
			vsec_qty_val_btu = new Vector[SECTOR_CD.size()];
			vsec_qty_val_mt= new Vector[SECTOR_CD.size()];
			vsec_qty_val_scm= new Vector[SECTOR_CD.size()];
			vsec_qty_val_btu_noguj = new Vector[SECTOR_CD.size()];
			vsec_qty_val_mt_noguj= new Vector[SECTOR_CD.size()];
			vsec_qty_val_scm_noguj= new Vector[SECTOR_CD.size()];
		for(int j=0; j<SECTOR_CD.size(); j++)
		{
			
			  double qty_mmbtu1=0.0;
			  double qty_mmbtu2=0.0;
			  double qty_mmbtu3=0.0;
			  double qty_mmbtu4=0.0;
			  double qty_scm1=0.0;
			  double qty_scm2=0.0;
			  double qty_scm3=0.0;
			  double qty_scm4=0.0;
			  double qty_mt1=0.0;
			  double qty_mt2=0.0;
			  double qty_mt3=0.0;
			  double qty_mt4=0.0;
			  
			  double qty_mmbtu11=0.0;
			  double qty_mmbtu21=0.0;
			  double qty_mmbtu31=0.0;
			  double qty_mmbtu41=0.0;
			  double qty_scm11=0.0;
			  double qty_scm21=0.0;
			  double qty_scm31=0.0;
			  double qty_scm41=0.0;
			  double qty_mt11=0.0;
			  double qty_mt21=0.0;
			  double qty_mt31=0.0;
			  double qty_mt41=0.0;
			
			vsec_qty_val_btu[j]=new Vector();
			vsec_qty_val_mt[j]= new Vector();  
			vsec_qty_val_scm[j]= new Vector();
			vsec_qty_val_btu_noguj[j]=new Vector();
			vsec_qty_val_mt_noguj[j]= new Vector();
			vsec_qty_val_scm_noguj[j]= new Vector();
			////System.out.println("mnth ="+mnth);		
		for(int i=0;i<12;i++)
			{
				yy=year; 
				year1=year;
				mnth=""+(Integer.parseInt(mm)+i);
			////System.out.println("mnth ="+mnth);	
			////System.out.println("year = "+year+",yy ="+yy+",year1= "+year1);		
		if (Integer.parseInt(mnth)<=12 )
		{
			month=""+ Integer.parseInt(mnth);					
		}				
		else if(Integer.parseInt(mnth)>12)
		{
			month =""+(Integer.parseInt(mnth)%12);
			mnth=""+(Integer.parseInt(mnth)/12);
			year1=""+ (Integer.parseInt(yy)+1);						
		}					
		////System.out.println("month = "+month+"mnth ="+mnth);			
		from_date="01"+"/"+month+"/"+year1;
		////System.out.println("from_date = "+from_date);
		queryString ="select TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
		//////System.out.println("Last date = "+queryString);
		rset = stmt.executeQuery(queryString);				
		if(rset.next())
		{
			to_date=rset.getString(1)==null?"":rset.getString(1);
		}
		else
		{
			to_date="";
		}
				
	//	queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
	//	"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD.elementAt(j)+"' AND " +
	//	"TRIM(UPPER(B.PLANT_STATE))!='"+plant_st+"' AND "+
	//	"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";	
		Vector GAS_DT=new Vector();
		Vector plant=new Vector();
		Vector cust=new Vector();
		queryString ="select DISTINCT(to_char(A.GAS_DT,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD.elementAt(j)+"' AND " +
			"TRIM(UPPER(B.PLANT_STATE))!='"+plant_st+"' AND "+
			"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L')";
		////System.out.println(queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			GAS_DT.add(rset.getString(1)==null?"":rset.getString(1));
			cust.add(rset.getString(2)==null?"":rset.getString(2));
			plant.add(rset.getString(3)==null?"":rset.getString(3));
						
		}
		    double temp=0;
			String total_QTY_MMBTU_NUMERIC="";
			for(int k=0;k<GAS_DT.size();k++)
			{
				
			queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
			"where GAS_DT = to_date('"+GAS_DT.elementAt(k)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust.elementAt(k)+"' AND PLANT_SEQ_NO='"+plant.elementAt(k)+"'" +
			" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			////System.out.println("SUM(QTY_MMBTU) "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				temp += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
				 total_QTY_MMBTU_NUMERIC=""+(temp);
			}
			else
			{
				total_QTY_MMBTU_NUMERIC=null;
			}
			}
		
		////System.out.println("Sector Company List= "+total_QTY_MMBTU_NUMERIC);
		
			
					if(i<3)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu1+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==2)
							{
								qty_mt1 =  qty_mmbtu1 * 0.0193;
								qty_scm1=  qty_mmbtu1 / 38900;
								if(qty_mmbtu1 <99){
									if(qty_mmbtu1 >10){
										qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 10));
									}}else{
									    qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 100));
								    }
							/*	if(qty_mt1 <99){
									if(qty_mt1 >10){
										qty_mt1=(qty_mt1 - (qty_mt1 % 10));
									}}else{
									qty_mt1=(qty_mt1 - (qty_mt1 % 100));
								    }
								if(qty_scm1 <99){
									if(qty_scm1 >10){
										qty_scm1=(qty_scm1 - (qty_scm1 % 10));
									}}else{
										qty_scm1=(qty_scm1 - (qty_scm1 % 100));
								    }
							*/	
								 vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu1));
								 vsec_qty_val_mt[j].add(nf2.format(qty_mt1));
								 vsec_qty_val_scm[j].add(nf2.format(qty_scm1));
								 
							}
						}
						else
						{
							if(i==2)
							{									
								if(qty_mmbtu1==0.0 && qty_mmbtu1==0)
								{
									 vsec_qty_val_btu[j].add("0");
									 vsec_qty_val_mt[j].add("0");
									 vsec_qty_val_scm[j].add("0");
								}
								else
								{
									qty_mt1 =  qty_mmbtu1 * 0.0193;
									qty_scm1=  qty_mmbtu1 / 38900;
									if(qty_mmbtu1 <99){
										if(qty_mmbtu1 >10){
											qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 10));
										}}else{
										    qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 100));
									    }
								/*	if(qty_mt1 <99){
										if(qty_mt1 >10){
											qty_mt1=(qty_mt1 - (qty_mt1 % 10));
										}}else{
										qty_mt1=(qty_mt1 - (qty_mt1 % 100));
									    }
									if(qty_scm1 <99){
										if(qty_scm1 >10){
											qty_scm1=(qty_scm1 - (qty_scm1 % 10));
										}}else{
											qty_scm1=(qty_scm1 - (qty_scm1 % 100));
									    }
									*/
								 	vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu1));
									vsec_qty_val_mt[j].add(nf2.format(qty_mt1));
									vsec_qty_val_scm[j].add(nf2.format(qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu2+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==5)
							{
								qty_mt2 =  qty_mmbtu2 * 0.0193;
								qty_scm2=  qty_mmbtu2 / 38900;
								if(qty_mmbtu2 <99){
									if(qty_mmbtu2 >10){
										qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 10));
									}}else{
									    qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 100));
								    }
							/*	if(qty_mt2 <99){
									if(qty_mt2 >10){
										qty_mt2=(qty_mt2 - (qty_mt2 % 10));
									}}else{
									qty_mt2=(qty_mt2 - (qty_mt2 % 100));
								    }
								if(qty_scm2 <99){
									if(qty_scm2 >10){
										qty_scm2=(qty_scm2 - (qty_scm2 % 10));
									}}else{
										qty_scm2=(qty_scm2 - (qty_scm2 % 100));
								    }
								*/
								vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu2));
								 vsec_qty_val_mt[j].add(nf2.format(qty_mt2));
								 vsec_qty_val_scm[j].add(nf2.format(qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_mmbtu2==0.0 && qty_mmbtu2==0)
								{
									vsec_qty_val_btu[j].add("0");
									 vsec_qty_val_mt[j].add("0");
									 vsec_qty_val_scm[j].add("0");
								}
								else
								{
									qty_mt2 =  qty_mmbtu2 * 0.0193;
									qty_scm2=  qty_mmbtu2 / 38900;
									if(qty_mmbtu2 <99){
										if(qty_mmbtu2 >10){
											qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 10));
										}}else{
										    qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 100));
									    }
							/*		if(qty_mt2 <99){
										if(qty_mt2 >10){
											qty_mt2=(qty_mt2 - (qty_mt2 % 10));
										}}else{
										qty_mt2=(qty_mt2 - (qty_mt2 % 100));
									    }
									if(qty_scm2 <99){
										if(qty_scm2 >10){
											qty_scm2=(qty_scm2 - (qty_scm2 % 10));
										}}else{
											qty_scm2=(qty_scm2 - (qty_scm2 % 100));
									    }
							*/		
									vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu2));
									 vsec_qty_val_mt[j].add(nf2.format(qty_mt2));
									 vsec_qty_val_scm[j].add(nf2.format(qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu3+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							////System.out.println("jaimin:::"+qty_mmbtu3);
							if(i==8)
							{
								qty_mt3 =  qty_mmbtu3 * 0.0193;
								qty_scm3=  qty_mmbtu3 / 38900;
								if(qty_mmbtu3 <99){
									if(qty_mmbtu3 >10){
										qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 10));
									}}else{
									    qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 100));
								    }
							/*	if(qty_mt3 <99){
									if(qty_mt3 >10){
										qty_mt3=(qty_mt3 - (qty_mt3 % 10));
									}}else{
									qty_mt3=(qty_mt3 - (qty_mt3 % 100));
								    }
								if(qty_scm3 <99){
									if(qty_scm3 >10){
										qty_scm3=(qty_scm3 - (qty_scm3 % 10));
									}}else{
										qty_scm3=(qty_scm3 - (qty_scm3 % 100));
								    }
							*/	
								vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu3));
								 vsec_qty_val_mt[j].add(nf2.format(qty_mt3));
								 vsec_qty_val_scm[j].add(nf2.format(qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_mmbtu3==0.0 && qty_mmbtu3==0)
								{
									vsec_qty_val_btu[j].add("0");
									 vsec_qty_val_mt[j].add("0");
									 vsec_qty_val_scm[j].add("0");
								}
								else
								{
									qty_mt3 =  qty_mmbtu3 * 0.0193;
									qty_scm3=  qty_mmbtu3 / 38900;
									if(qty_mmbtu3 <99){
										if(qty_mmbtu3 >10){
											qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 10));
										}}else{
										    qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 100));
									    }
							/*		if(qty_mt3 <99){
										if(qty_mt3 >10){
											qty_mt3=(qty_mt3 - (qty_mt3 % 10));
										}}else{
										qty_mt3=(qty_mt3 - (qty_mt3 % 100));
									    }
									if(qty_scm3 <99){
										if(qty_scm3 >10){
											qty_scm3=(qty_scm3 - (qty_scm3 % 10));
										}}else{
											qty_scm3=(qty_scm3 - (qty_scm3 % 100));
									    }
							*/		
									vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu3));
									 vsec_qty_val_mt[j].add(nf2.format(qty_mt3));
									 vsec_qty_val_scm[j].add(nf2.format(qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu4+=Double.parseDouble(total_QTY_MMBTU_NUMERIC); 
							if(i==11)
							{
								qty_mt4 =  qty_mmbtu4 * 0.0193;
								qty_scm4=  qty_mmbtu4 / 38900;
								if(qty_mmbtu4 <99){
									if(qty_mmbtu4 >10){
										qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 10));
									}}else{
									    qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 100));
								    }
							/*	if(qty_mt4 <99){
									if(qty_mt4 >10){
										qty_mt4=(qty_mt4 - (qty_mt4 % 10));
									}}else{
									qty_mt4=(qty_mt4 - (qty_mt4 % 100));
								    }
								if(qty_scm4 <99){
									if(qty_scm4 >10){
										qty_scm4=(qty_scm4 - (qty_scm4 % 10));
									}}else{
										qty_scm4=(qty_scm4 - (qty_scm4 % 100));
								    }
								    */
								vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu4));
								 vsec_qty_val_mt[j].add(nf2.format(qty_mt4));
								 vsec_qty_val_scm[j].add(nf2.format(qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_mmbtu4==0.0 && qty_mmbtu4==0)
								{
									vsec_qty_val_btu[j].add("0");
									 vsec_qty_val_mt[j].add("0");
									 vsec_qty_val_scm[j].add("0");
								}
								else
								{
									qty_mt4 =  qty_mmbtu4 * 0.0193;
									qty_scm4=  qty_mmbtu4 / 38900;
									if(qty_mmbtu4 <99){
										if(qty_mmbtu4 >10){
											qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 10));
										}}else{
										    qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 100));
									    }
								/*	if(qty_mt4 <99){
										if(qty_mt4 >10){
											qty_mt4=(qty_mt4 - (qty_mt4 % 10));
										}}else{
										qty_mt4=(qty_mt4 - (qty_mt4 % 100));
									    }
									if(qty_scm4 <99){
										if(qty_scm4 >10){
											qty_scm4=(qty_scm4 - (qty_scm4 % 10));
										}}else{
											qty_scm4=(qty_scm4 - (qty_scm4 % 100));
									    }
								*/	
									vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu4));
									 vsec_qty_val_mt[j].add(nf2.format(qty_mt4));
									 vsec_qty_val_scm[j].add(nf2.format(qty_scm4));
								}
							}
						}
					}
			
		
		Vector GAS_DT1=new Vector();
		Vector plant1=new Vector();
		Vector cust1=new Vector();
		queryString ="select DISTINCT(to_char(A.GAS_DT,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD.elementAt(j)+"' AND " +
			"TRIM(UPPER(B.PLANT_STATE))='"+plant_st+"' AND "+
			"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') ";
		//////System.out.println("Outside guj::"+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			GAS_DT1.add(rset.getString(1)==null?"":rset.getString(1));
			cust1.add(rset.getString(2)==null?"":rset.getString(2));
			plant1.add(rset.getString(3)==null?"":rset.getString(3));				
		}
		    double temp_guj=0;
			String total_QTY_MMBTU_NUMERIC1="";
			for(int k=0;k<GAS_DT1.size();k++)
			{
				
			queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
			"where GAS_DT = to_date('"+GAS_DT1.elementAt(k)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust1.elementAt(k)+"' AND PLANT_SEQ_NO='"+plant1.elementAt(k)+"'" +
			" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			////System.out.println("SUM(QTY_MMBTU)2121212:: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				temp_guj += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
				total_QTY_MMBTU_NUMERIC1=""+(temp_guj);
			}
			
			}
		
		
		
		////System.out.println("Sector Company List= "+total_QTY_MMBTU_NUMERIC1);
			if(i<3)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu11+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							if(i==2)
							{
								qty_mt11 =  qty_mmbtu11 * 0.0193;
								qty_scm11=  qty_mmbtu11 / 38900;
								if(qty_mmbtu11 <99){
									if(qty_mmbtu11 >10){
										qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 10));
									}}else{
									    qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 100));
								    }
							/*	if(qty_mt11 <99){
									if(qty_mt11 >10){
										qty_mt11=(qty_mt11 - (qty_mt11 % 10));
									}}else{
									qty_mt11=(qty_mt11 - (qty_mt11 % 100));
								    }
								if(qty_scm11 <99){
									if(qty_scm11 >10){
										qty_scm11=(qty_scm11 - (qty_scm11 % 10));
									}}else{
										qty_scm11=(qty_scm11 - (qty_scm11 % 100));
								    }
							*/	
								 vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu11));
								 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt11));
								 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm11));
								 
							}
						}
						else
						{
							if(i==2)
							{									
								if(qty_mmbtu11==0.0 && qty_mmbtu11==0)
								{
									vsec_qty_val_btu_noguj[j].add("0");
									 vsec_qty_val_mt_noguj[j].add("0");
									 vsec_qty_val_scm_noguj[j].add("0");
								}
								else
								{
									qty_mt11 =  qty_mmbtu11 * 0.0193;
									qty_scm11=  qty_mmbtu11 / 38900;
									if(qty_mmbtu11 <99){
										if(qty_mmbtu11 >10){
											qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 10));
										}}else{
										    qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 100));
									    }
								/*	if(qty_mt11 <99){
										if(qty_mt11 >10){
											qty_mt11=(qty_mt11 - (qty_mt11 % 10));
										}}else{
										qty_mt11=(qty_mt11 - (qty_mt11 % 100));
									    }
									if(qty_scm11 <99){
										if(qty_scm11 >10){
											qty_scm11=(qty_scm11 - (qty_scm11 % 10));
										}}else{
											qty_scm11=(qty_scm11 - (qty_scm11 % 100));
									    }
								*/	
									vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu11));
									 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt11));
									 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm11));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu21+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							if(i==5)
							{
								qty_mt21 =  qty_mmbtu21 * 0.0193;
								qty_scm21=  qty_mmbtu21 / 38900;
								if(qty_mmbtu21 <99){
									if(qty_mmbtu21 >10){
										qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 10));
									}}else{
									    qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 100));
								    }
							/*	if(qty_mt21 <99){
									if(qty_mt21 >10){
										qty_mt21=(qty_mt21 - (qty_mt21 % 10));
									}}else{
									qty_mt21=(qty_mt21 - (qty_mt21 % 100));
								    }
								if(qty_scm21 <99){
									if(qty_scm21 >10){
										qty_scm21=(qty_scm21 - (qty_scm21 % 10));
									}}else{
										qty_scm21=(qty_scm21 - (qty_scm21 % 100));
								    }
								    */
								vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu21));
								 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt21));
								 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm21));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_mmbtu21==0.0 && qty_mmbtu21==0)
								{
									vsec_qty_val_btu_noguj[j].add("0");
									 vsec_qty_val_mt_noguj[j].add("0");
									 vsec_qty_val_scm_noguj[j].add("0");
								}
								else
								{
									qty_mt21 =  qty_mmbtu21 * 0.0193;
									qty_scm21=  qty_mmbtu21 / 38900;
									if(qty_mmbtu21 <99){
										if(qty_mmbtu21 >10){
											qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 10));
										}}else{
										    qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 100));
									    }
									/*	if(qty_mt21 <99){
										if(qty_mt21 >10){
											qty_mt21=(qty_mt21 - (qty_mt21 % 10));
										}}else{
										qty_mt21=(qty_mt21 - (qty_mt21 % 100));
									    }
									if(qty_scm21 <99){
										if(qty_scm21 >10){
											qty_scm21=(qty_scm21 - (qty_scm21 % 10));
										}}else{
											qty_scm21=(qty_scm21 - (qty_scm21 % 100));
									    }
									*/
									vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu21));
									 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt21));
									 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm21));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu31+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							
							
							if(i==8)
							{
								qty_mt31 =  qty_mmbtu31 * 0.0193;
								qty_scm31=  qty_mmbtu31 / 38900;
								if(qty_mmbtu31 <99){
									if(qty_mmbtu31 >10){
										qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 10));
									}}else{
									    qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 100));
								    }
							/*	if(qty_mt31 <99){
									if(qty_mt31 >10){
										qty_mt31=(qty_mt31 - (qty_mt31 % 10));
									}}else{
									qty_mt31=(qty_mt31 - (qty_mt31 % 100));
								    }
								if(qty_scm31 <99){
									if(qty_scm31 >10){
										qty_scm31=(qty_scm31 - (qty_scm31 % 10));
									}}else{
										qty_scm31=(qty_scm31 - (qty_scm31 % 100));
								    }
							*/	
								vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu31));
								 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt31));
								 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm31));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_mmbtu31==0.0 && qty_mmbtu31==0)
								{
									vsec_qty_val_btu_noguj[j].add("0");
									 vsec_qty_val_mt_noguj[j].add("0");
									 vsec_qty_val_scm_noguj[j].add("0");
								}
								else
								{
									qty_mt31 =  qty_mmbtu31 * 0.0193;
									qty_scm31=  qty_mmbtu31 / 38900;
									if(qty_mmbtu31 <99){
										if(qty_mmbtu31 >10){
											qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 10));
										}}else{
										    qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 100));
									    }
								/*	if(qty_mt31 <99){
										if(qty_mt31 >10){
											qty_mt31=(qty_mt31 - (qty_mt31 % 10));
										}}else{
										qty_mt31=(qty_mt31 - (qty_mt31 % 100));
									    }
									if(qty_scm31 <99){
										if(qty_scm31 >10){
											qty_scm31=(qty_scm31 - (qty_scm31 % 10));
										}}else{
											qty_scm31=(qty_scm31 - (qty_scm31 % 100));
									    }
								*/	
									vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu31));
									 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt31));
									 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm31));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu41+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1); 
							if(i==11)
							{
								qty_mt41 =  qty_mmbtu41 * 0.0193;
								qty_scm41=  qty_mmbtu41 / 38900;
								if(qty_mmbtu41 <99){
									if(qty_mmbtu41 >10){
										qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 10));
									}}else{
									    qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 100));
								    }
							/*	if(qty_mt41 <99){
									if(qty_mt41 >10){
										qty_mt41=(qty_mt41 - (qty_mt41 % 10));
									}}else{
									qty_mt41=(qty_mt41 - (qty_mt41 % 100));
								    }
								if(qty_scm41 <99){
									if(qty_scm41 >10){
										qty_scm41=(qty_scm41 - (qty_scm41 % 10));
									}}else{
										qty_scm41=(qty_scm41 - (qty_scm41 % 100));
								    }
							*/	
								vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu41));
								 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt41));
								 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm41));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_mmbtu41==0.0 && qty_mmbtu41==0)
								{
									vsec_qty_val_btu_noguj[j].add("0");
									 vsec_qty_val_mt_noguj[j].add("0");
									 vsec_qty_val_scm_noguj[j].add("0");
								}
								else
								{
									qty_mt41 =  qty_mmbtu41 * 0.0193;
									qty_scm41=  qty_mmbtu41 / 38900;
									if(qty_mmbtu41 <99){
										if(qty_mmbtu41 >10){
											qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 10));
										}}else{
										    qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 100));
									    }
								/*	if(qty_mt41 <99){
										if(qty_mt41 >10){
											qty_mt41=(qty_mt41 - (qty_mt41 % 10));
										}}else{
										qty_mt41=(qty_mt41 - (qty_mt41 % 100));
									    }
									if(qty_scm41 <99){
										if(qty_scm41 >10){
											qty_scm41=(qty_scm41 - (qty_scm41 % 10));
										}}else{
											qty_scm41=(qty_scm41 - (qty_scm41 % 100));
									    }
								*/	
									vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu41));
									 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt41));
									 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm41));
								}
							}
						}
					}
		  //vsec_qty_val_btu[j].add(QTY_MMBTU.elementAt(i));
		 // vsec_qty_val_mt[j].add(QTY_MT.elementAt(i));
		////System.out.println("vsec_qty_val_btu = "+vsec_qty_val_btu[j]);
		////System.out.println("vsec_qty_val_btu_noguj = "+vsec_qty_val_btu_noguj[j]);
		}
	 }
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	}
///////////////RS20181010: on demand by SB///////////////////////////////////
	int maxYear = 0; int minYear = 0;
	public void fetchMaxMinYear() throws Exception {
		try {
			queryString = "SELECT TO_CHAR(MIN(GAS_DT),'YYYY'),TO_CHAR(MAX(GAS_DT),'YYYY') FROM FMS7_DAILY_ALLOCATION_DTL ";
			rset = stmt.executeQuery(queryString);
			if(rset.next()) {
				minYear = rset.getInt(1);
				maxYear = rset.getInt(2);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sector_specification2(){

		  mnth="04";
		  String mm="04";
		  String yy="";
		  String year1="";
		  
	try{	
		fetchMaxMinYear();
		//fetch plant state for supplier....
		queryString = "SELECT PLANT_STATE FROM FMS7_SUPPLIER_PLANT_DTL WHERE SUPPLIER_CD='1' ";
		rset = stmt.executeQuery(queryString);
		if(rset.next()) {
			plant_st = rset.getString(1)==null?"":rset.getString(1).toUpperCase();
		}
		
		queryString = "select SECTOR_CD, SECTOR_ABBR FROM FMS7_SECTOR_MST WHERE FLAG='P'  ORDER BY priority";
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			SECTOR_CD.add(rset.getString(1)==null?"":rset.getString(1));
			SECTOR_NM.add(rset.getString(2)==null?"":rset.getString(2));
		}
		if(!year.trim().equals("0") && !year.trim().equals(""))
		 {
			vsec_qty_val_btu = new Vector[SECTOR_CD.size()];
			vsec_qty_val_mt= new Vector[SECTOR_CD.size()];
			vsec_qty_val_scm= new Vector[SECTOR_CD.size()];
			vsec_qty_val_btu_noguj = new Vector[SECTOR_CD.size()];
			vsec_qty_val_mt_noguj= new Vector[SECTOR_CD.size()];
			vsec_qty_val_scm_noguj= new Vector[SECTOR_CD.size()];
		for(int j=0; j<SECTOR_CD.size(); j++)
		{
			
			  double qty_mmbtu1=0.0;
			  double qty_mmbtu2=0.0;
			  double qty_mmbtu3=0.0;
			  double qty_mmbtu4=0.0;
			  double qty_scm1=0.0;
			  double qty_scm2=0.0;
			  double qty_scm3=0.0;
			  double qty_scm4=0.0;
			  double qty_mt1=0.0;
			  double qty_mt2=0.0;
			  double qty_mt3=0.0;
			  double qty_mt4=0.0;
			  
			  double qty_mmbtu11=0.0;
			  double qty_mmbtu21=0.0;
			  double qty_mmbtu31=0.0;
			  double qty_mmbtu41=0.0;
			  double qty_scm11=0.0;
			  double qty_scm21=0.0;
			  double qty_scm31=0.0;
			  double qty_scm41=0.0;
			  double qty_mt11=0.0;
			  double qty_mt21=0.0;
			  double qty_mt31=0.0;
			  double qty_mt41=0.0;
			
			vsec_qty_val_btu[j]=new Vector();
			vsec_qty_val_mt[j]= new Vector();  
			vsec_qty_val_scm[j]= new Vector();
			vsec_qty_val_btu_noguj[j]=new Vector();
			vsec_qty_val_mt_noguj[j]= new Vector();
			vsec_qty_val_scm_noguj[j]= new Vector();
		for(int i=0;i<12;i++)
			{
				yy=year; 
				year1=year;
				mnth=""+(Integer.parseInt(mm)+i);
		if (Integer.parseInt(mnth)<=12 )
		{
			month=""+ Integer.parseInt(mnth);					
		}				
		else if(Integer.parseInt(mnth)>12)
		{
			month =""+(Integer.parseInt(mnth)%12);
			mnth=""+(Integer.parseInt(mnth)/12);
			year1=""+ (Integer.parseInt(yy)+1);						
		}					
		from_date="01"+"/"+month+"/"+year1;
		queryString ="select TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
		rset = stmt.executeQuery(queryString);				
		if(rset.next())
		{
			to_date=rset.getString(1)==null?"":rset.getString(1);
		}
		else
		{
			to_date="";
		}
				
		Vector GAS_DT=new Vector();
		Vector plant=new Vector();
		Vector cust=new Vector();
		queryString ="select DISTINCT(to_char(A.GAS_DT,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD.elementAt(j)+"' AND " +
			"TRIM(UPPER(B.PLANT_STATE))!='"+plant_st+"' AND "+
			"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L')";
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			GAS_DT.add(rset.getString(1)==null?"":rset.getString(1));
			cust.add(rset.getString(2)==null?"":rset.getString(2));
			plant.add(rset.getString(3)==null?"":rset.getString(3));
						
		}
		    double temp=0;
			String total_QTY_MMBTU_NUMERIC="";
			for(int k=0;k<GAS_DT.size();k++)
			{
				
			queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
			"where GAS_DT = to_date('"+GAS_DT.elementAt(k)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust.elementAt(k)+"' AND PLANT_SEQ_NO='"+plant.elementAt(k)+"'" +
			" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				temp += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
				 total_QTY_MMBTU_NUMERIC=""+(temp);
			}
			else
			{
				total_QTY_MMBTU_NUMERIC=null;
			}
			}
					if(i<3)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu1+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==2)
							{
								qty_mt1 =  qty_mmbtu1 * 0.0193;
								qty_scm1=  qty_mmbtu1 / 38900;
								if(qty_mmbtu1 <99){
									if(qty_mmbtu1 >10){
										qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 10));
									}}else{
									    qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 100));
								    }
								 vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu1));
								 vsec_qty_val_mt[j].add(nf2.format(qty_mt1));
								 vsec_qty_val_scm[j].add(nf2.format(qty_scm1));
								 
							}
						}
						else
						{
							if(i==2)
							{									
								if(qty_mmbtu1==0.0 && qty_mmbtu1==0)
								{
									 vsec_qty_val_btu[j].add("0");
									 vsec_qty_val_mt[j].add("0");
									 vsec_qty_val_scm[j].add("0");
								}
								else
								{
									qty_mt1 =  qty_mmbtu1 * 0.0193;
									qty_scm1=  qty_mmbtu1 / 38900;
									if(qty_mmbtu1 <99){
										if(qty_mmbtu1 >10){
											qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 10));
										}}else{
										    qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 100));
									    }
								 	vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu1));
									vsec_qty_val_mt[j].add(nf2.format(qty_mt1));
									vsec_qty_val_scm[j].add(nf2.format(qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu2+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==5)
							{
								qty_mt2 =  qty_mmbtu2 * 0.0193;
								qty_scm2=  qty_mmbtu2 / 38900;
								if(qty_mmbtu2 <99){
									if(qty_mmbtu2 >10){
										qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 10));
									}}else{
									    qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 100));
								    }
								vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu2));
								 vsec_qty_val_mt[j].add(nf2.format(qty_mt2));
								 vsec_qty_val_scm[j].add(nf2.format(qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_mmbtu2==0.0 && qty_mmbtu2==0)
								{
									vsec_qty_val_btu[j].add("0");
									 vsec_qty_val_mt[j].add("0");
									 vsec_qty_val_scm[j].add("0");
								}
								else
								{
									qty_mt2 =  qty_mmbtu2 * 0.0193;
									qty_scm2=  qty_mmbtu2 / 38900;
									if(qty_mmbtu2 <99){
										if(qty_mmbtu2 >10){
											qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 10));
										}}else{
										    qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 100));
									    }
									vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu2));
									 vsec_qty_val_mt[j].add(nf2.format(qty_mt2));
									 vsec_qty_val_scm[j].add(nf2.format(qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu3+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==8)
							{
								qty_mt3 =  qty_mmbtu3 * 0.0193;
								qty_scm3=  qty_mmbtu3 / 38900;
								if(qty_mmbtu3 <99){
									if(qty_mmbtu3 >10){
										qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 10));
									}}else{
									    qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 100));
								    }
								vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu3));
								 vsec_qty_val_mt[j].add(nf2.format(qty_mt3));
								 vsec_qty_val_scm[j].add(nf2.format(qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_mmbtu3==0.0 && qty_mmbtu3==0)
								{
									vsec_qty_val_btu[j].add("0");
									 vsec_qty_val_mt[j].add("0");
									 vsec_qty_val_scm[j].add("0");
								}
								else
								{
									qty_mt3 =  qty_mmbtu3 * 0.0193;
									qty_scm3=  qty_mmbtu3 / 38900;
									if(qty_mmbtu3 <99){
										if(qty_mmbtu3 >10){
											qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 10));
										}}else{
										    qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 100));
									    }
									vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu3));
									 vsec_qty_val_mt[j].add(nf2.format(qty_mt3));
									 vsec_qty_val_scm[j].add(nf2.format(qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu4+=Double.parseDouble(total_QTY_MMBTU_NUMERIC); 
							if(i==11)
							{
								qty_mt4 =  qty_mmbtu4 * 0.0193;
								qty_scm4=  qty_mmbtu4 / 38900;
								if(qty_mmbtu4 <99){
									if(qty_mmbtu4 >10){
										qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 10));
									}}else{
									    qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 100));
								    }
								vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu4));
								 vsec_qty_val_mt[j].add(nf2.format(qty_mt4));
								 vsec_qty_val_scm[j].add(nf2.format(qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_mmbtu4==0.0 && qty_mmbtu4==0)
								{
									vsec_qty_val_btu[j].add("0");
									 vsec_qty_val_mt[j].add("0");
									 vsec_qty_val_scm[j].add("0");
								}
								else
								{
									qty_mt4 =  qty_mmbtu4 * 0.0193;
									qty_scm4=  qty_mmbtu4 / 38900;
									if(qty_mmbtu4 <99){
										if(qty_mmbtu4 >10){
											qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 10));
										}}else{
										    qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 100));
									    }
									vsec_qty_val_btu[j].add(nf2.format(qty_mmbtu4));
									 vsec_qty_val_mt[j].add(nf2.format(qty_mt4));
									 vsec_qty_val_scm[j].add(nf2.format(qty_scm4));
								}
							}
						}
					}
			
		
		Vector GAS_DT1=new Vector();
		Vector plant1=new Vector();
		Vector cust1=new Vector();
		queryString ="select DISTINCT(to_char(A.GAS_DT,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD.elementAt(j)+"' AND " +
			"TRIM(UPPER(B.PLANT_STATE))='"+plant_st+"' AND "+
			"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') ";
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			GAS_DT1.add(rset.getString(1)==null?"":rset.getString(1));
			cust1.add(rset.getString(2)==null?"":rset.getString(2));
			plant1.add(rset.getString(3)==null?"":rset.getString(3));				
		}
		    double temp_guj=0;
			String total_QTY_MMBTU_NUMERIC1="";
			for(int k=0;k<GAS_DT1.size();k++)
			{
				
			queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
			"where GAS_DT = to_date('"+GAS_DT1.elementAt(k)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust1.elementAt(k)+"' AND PLANT_SEQ_NO='"+plant1.elementAt(k)+"'" +
			" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				temp_guj += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
				total_QTY_MMBTU_NUMERIC1=""+(temp_guj);
			}
			}
			if(i<3)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu11+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							if(i==2)
							{
								qty_mt11 =  qty_mmbtu11 * 0.0193;
								qty_scm11=  qty_mmbtu11 / 38900;
								if(qty_mmbtu11 <99){
									if(qty_mmbtu11 >10){
										qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 10));
									}}else{
									    qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 100));
								    }
								 vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu11));
								 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt11));
								 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm11));
								 
							}
						}
						else
						{
							if(i==2)
							{									
								if(qty_mmbtu11==0.0 && qty_mmbtu11==0)
								{
									vsec_qty_val_btu_noguj[j].add("0");
									 vsec_qty_val_mt_noguj[j].add("0");
									 vsec_qty_val_scm_noguj[j].add("0");
								}
								else
								{
									qty_mt11 =  qty_mmbtu11 * 0.0193;
									qty_scm11=  qty_mmbtu11 / 38900;
									if(qty_mmbtu11 <99){
										if(qty_mmbtu11 >10){
											qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 10));
										}}else{
										    qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 100));
									    }
									vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu11));
									 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt11));
									 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm11));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu21+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							if(i==5)
							{
								qty_mt21 =  qty_mmbtu21 * 0.0193;
								qty_scm21=  qty_mmbtu21 / 38900;
								if(qty_mmbtu21 <99){
									if(qty_mmbtu21 >10){
										qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 10));
									}}else{
									    qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 100));
								    }
								vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu21));
								 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt21));
								 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm21));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_mmbtu21==0.0 && qty_mmbtu21==0)
								{
									vsec_qty_val_btu_noguj[j].add("0");
									 vsec_qty_val_mt_noguj[j].add("0");
									 vsec_qty_val_scm_noguj[j].add("0");
								}
								else
								{
									qty_mt21 =  qty_mmbtu21 * 0.0193;
									qty_scm21=  qty_mmbtu21 / 38900;
									if(qty_mmbtu21 <99){
										if(qty_mmbtu21 >10){
											qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 10));
										}}else{
										    qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 100));
									    }
									vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu21));
									 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt21));
									 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm21));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu31+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							
							
							if(i==8)
							{
								qty_mt31 =  qty_mmbtu31 * 0.0193;
								qty_scm31=  qty_mmbtu31 / 38900;
								if(qty_mmbtu31 <99){
									if(qty_mmbtu31 >10){
										qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 10));
									}}else{
									    qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 100));
								    }
								vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu31));
								 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt31));
								 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm31));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_mmbtu31==0.0 && qty_mmbtu31==0)
								{
									vsec_qty_val_btu_noguj[j].add("0");
									 vsec_qty_val_mt_noguj[j].add("0");
									 vsec_qty_val_scm_noguj[j].add("0");
								}
								else
								{
									qty_mt31 =  qty_mmbtu31 * 0.0193;
									qty_scm31=  qty_mmbtu31 / 38900;
									if(qty_mmbtu31 <99){
										if(qty_mmbtu31 >10){
											qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 10));
										}}else{
										    qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 100));
									    }
									vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu31));
									 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt31));
									 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm31));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu41+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1); 
							if(i==11)
							{
								qty_mt41 =  qty_mmbtu41 * 0.0193;
								qty_scm41=  qty_mmbtu41 / 38900;
								if(qty_mmbtu41 <99){
									if(qty_mmbtu41 >10){
										qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 10));
									}}else{
									    qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 100));
								    }
								vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu41));
								 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt41));
								 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm41));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_mmbtu41==0.0 && qty_mmbtu41==0)
								{
									vsec_qty_val_btu_noguj[j].add("0");
									 vsec_qty_val_mt_noguj[j].add("0");
									 vsec_qty_val_scm_noguj[j].add("0");
								}
								else
								{
									qty_mt41 =  qty_mmbtu41 * 0.0193;
									qty_scm41=  qty_mmbtu41 / 38900;
									if(qty_mmbtu41 <99){
										if(qty_mmbtu41 >10){
											qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 10));
										}}else{
										    qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 100));
									    }
									vsec_qty_val_btu_noguj[j].add(nf2.format(qty_mmbtu41));
									 vsec_qty_val_mt_noguj[j].add(nf2.format(qty_mt41));
									 vsec_qty_val_scm_noguj[j].add(nf2.format(qty_scm41));
								}
							}
						}
					}
		}
	 }
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	////////////////^RS20181010: On demand from SB ////////////////////////
	public void other_specification(){
		
		
		  mnth="04";
		  String mm="04";
		  String yy="";
		  String year1="";
		  
		
		  
		  
	try{	
		queryString = "select SECTOR_CD, SECTOR_ABBR FROM FMS7_SECTOR_MST WHERE FLAG='O'  ORDER BY priority";
		////System.out.println("Sector Selection Sector Master Query = "+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			SECTOR_CD_other.add(rset.getString(1)==null?"":rset.getString(1));
			SECTOR_NM_other.add(rset.getString(2)==null?"":rset.getString(2));
		}
		////System.out.println("SECTOR_CD.size()::"+SECTOR_CD_other.size()+"year:"+year);
		if(!year.trim().equals("0") && !year.trim().equals(""))
		 {
			vsec_qty_val_btu_other = new Vector[SECTOR_CD_other.size()];
			vsec_qty_val_mt_other= new Vector[SECTOR_CD_other.size()];
			vsec_qty_val_scm_other= new Vector[SECTOR_CD_other.size()];
			vsec_qty_val_btu_noguj_other = new Vector[SECTOR_CD_other.size()];
			vsec_qty_val_mt_noguj_other= new Vector[SECTOR_CD_other.size()];
			vsec_qty_val_scm_noguj_other= new Vector[SECTOR_CD_other.size()];
		for(int j=0; j<SECTOR_CD_other.size(); j++)
		{
			
			  double qty_mmbtu1=0.0;
			  double qty_mmbtu2=0.0;
			  double qty_mmbtu3=0.0;
			  double qty_mmbtu4=0.0;
			  double qty_scm1=0.0;
			  double qty_scm2=0.0;
			  double qty_scm3=0.0;
			  double qty_scm4=0.0;
			  double qty_mt1=0.0;
			  double qty_mt2=0.0;
			  double qty_mt3=0.0;
			  double qty_mt4=0.0;
			  
			  double qty_mmbtu11=0.0;
			  double qty_mmbtu21=0.0;
			  double qty_mmbtu31=0.0;
			  double qty_mmbtu41=0.0;
			  double qty_scm11=0.0;
			  double qty_scm21=0.0;
			  double qty_scm31=0.0;
			  double qty_scm41=0.0;
			  double qty_mt11=0.0;
			  double qty_mt21=0.0;
			  double qty_mt31=0.0;
			  double qty_mt41=0.0;
			
			vsec_qty_val_btu_other[j]=new Vector();
			vsec_qty_val_mt_other[j]= new Vector();  
			vsec_qty_val_scm_other[j]= new Vector();
			vsec_qty_val_btu_noguj_other[j]=new Vector();
			vsec_qty_val_mt_noguj_other[j]= new Vector();
			vsec_qty_val_scm_noguj_other[j]= new Vector();
			////System.out.println("mnth ="+mnth);		
		for(int i=0;i<12;i++)
			{
				yy=year; 
				year1=year;
				mnth=""+(Integer.parseInt(mm)+i);
			////System.out.println("mnth ="+mnth);	
			////System.out.println("year = "+year+",yy ="+yy+",year1= "+year1);		
		if (Integer.parseInt(mnth)<=12 )
		{
			month=""+ Integer.parseInt(mnth);					
		}				
		else if(Integer.parseInt(mnth)>12)
		{
			month =""+(Integer.parseInt(mnth)%12);
			mnth=""+(Integer.parseInt(mnth)/12);
			year1=""+ (Integer.parseInt(yy)+1);						
		}					
		////System.out.println("month = "+month+"mnth ="+mnth);			
		from_date="01"+"/"+month+"/"+year1;
		////System.out.println("from_date = "+from_date);
		queryString ="select TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
		//////System.out.println("Last date = "+queryString);
		rset = stmt.executeQuery(queryString);				
		if(rset.next())
		{
			to_date=rset.getString(1)==null?"":rset.getString(1);
		}
		else
		{
			to_date="";
		}
				
	//	queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
	//	"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD.elementAt(j)+"' AND " +
	//	"TRIM(UPPER(B.PLANT_STATE))!='"+plant_st+"' AND "+
	//	"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";	
		Vector GAS_DT12=new Vector();
		Vector plant12=new Vector();
		Vector cust12=new Vector();
		queryString ="select DISTINCT(to_char(A.GAS_DT,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD_other.elementAt(j)+"' AND " +
			"TRIM(UPPER(B.PLANT_STATE))!='"+plant_st+"' AND "+
			"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L')";
		////System.out.println(queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			GAS_DT12.add(rset.getString(1)==null?"":rset.getString(1));
			cust12.add(rset.getString(2)==null?"":rset.getString(2));
			plant12.add(rset.getString(3)==null?"":rset.getString(3));				
		}
		////System.out.println("Jaimin-->>>>>"+GAS_DT12);	
		    double temp=0;
			String total_QTY_MMBTU_NUMERIC="";
			for(int k=0;k<GAS_DT12.size();k++)
			{
				
			queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
			"where GAS_DT = to_date('"+GAS_DT12.elementAt(k)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust12.elementAt(k)+"' AND PLANT_SEQ_NO='"+plant12.elementAt(k)+"' " +
			" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			////System.out.println("SUM(QTY_MMBTU) "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				temp += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
				 total_QTY_MMBTU_NUMERIC=""+(temp);
			}
			else
			{
				total_QTY_MMBTU_NUMERIC=null;
			}
			}
		
		////System.out.println("Sector Company List= "+total_QTY_MMBTU_NUMERIC);
		
			
					if(i<3)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu1+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==2)
							{
								qty_mt1 =  qty_mmbtu1 * 0.0193;
								qty_scm1=  qty_mmbtu1 / 38900;
								if(qty_mmbtu1 <99){
									if(qty_mmbtu1 >10){
										qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 10));
									}}else{
									    qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 100));
								    }
							/*	if(qty_mt1 <99){
									if(qty_mt1 >10){
										qty_mt1=(qty_mt1 - (qty_mt1 % 10));
									}}else{
									qty_mt1=(qty_mt1 - (qty_mt1 % 100));
								    }
								if(qty_scm1 <99){
									if(qty_scm1 >10){
										qty_scm1=(qty_scm1 - (qty_scm1 % 10));
									}}else{
										qty_scm1=(qty_scm1 - (qty_scm1 % 100));
								    }
							*/	
								 vsec_qty_val_btu_other[j].add(nf2.format(qty_mmbtu1));
								 vsec_qty_val_mt_other[j].add(nf2.format(qty_mt1));
								 vsec_qty_val_scm_other[j].add(nf2.format(qty_scm1));
								 
							}
						}
						else
						{
							if(i==2)
							{									
								if(qty_mmbtu1==0.0 && qty_mmbtu1==0)
								{
									 vsec_qty_val_btu_other[j].add("0");
									 vsec_qty_val_mt_other[j].add("0");
									 vsec_qty_val_scm_other[j].add("0");
								}
								else
								{
									qty_mt1 =  qty_mmbtu1 * 0.0193;
									qty_scm1=  qty_mmbtu1 / 38900;
									if(qty_mmbtu1 <99){
										if(qty_mmbtu1 >10){
											qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 10));
										}}else{
										    qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 100));
									    }
							/*		if(qty_mt1 <99){
										if(qty_mt1 >10){
											qty_mt1=(qty_mt1 - (qty_mt1 % 10));
										}}else{
										qty_mt1=(qty_mt1 - (qty_mt1 % 100));
									    }
									if(qty_scm1 <99){
										if(qty_scm1 >10){
											qty_scm1=(qty_scm1 - (qty_scm1 % 10));
										}}else{
											qty_scm1=(qty_scm1 - (qty_scm1 % 100));
									    }
								*/	    
								 	vsec_qty_val_btu_other[j].add(nf2.format(qty_mmbtu1));
									vsec_qty_val_mt_other[j].add(nf2.format(qty_mt1));
									vsec_qty_val_scm_other[j].add(nf2.format(qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu2+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==5)
							{
								qty_mt2 =  qty_mmbtu2 * 0.0193;
								qty_scm2=  qty_mmbtu2 / 38900;
								if(qty_mmbtu2 <99){
									if(qty_mmbtu2 >10){
										qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 10));
									}}else{
									    qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 100));
								    }
							/*	if(qty_mt2 <99){
									if(qty_mt2 >10){
										qty_mt2=(qty_mt2 - (qty_mt2 % 10));
									}}else{
									qty_mt2=(qty_mt2 - (qty_mt2 % 100));
								    }
								if(qty_scm2 <99){
									if(qty_scm2 >10){
										qty_scm2=(qty_scm2 - (qty_scm2 % 10));
									}}else{
										qty_scm2=(qty_scm2 - (qty_scm2 % 100));
								    }
							*/	
								vsec_qty_val_btu_other[j].add(nf2.format(qty_mmbtu2));
								 vsec_qty_val_mt_other[j].add(nf2.format(qty_mt2));
								 vsec_qty_val_scm_other[j].add(nf2.format(qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_mmbtu2==0.0 && qty_mmbtu2==0)
								{
									vsec_qty_val_btu_other[j].add("0");
									 vsec_qty_val_mt_other[j].add("0");
									 vsec_qty_val_scm_other[j].add("0");
								}
								else
								{
									qty_mt2 =  qty_mmbtu2 * 0.0193;
									qty_scm2=  qty_mmbtu2 / 38900;
									if(qty_mmbtu2 <99){
										if(qty_mmbtu2 >10){
											qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 10));
										}}else{
										    qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 100));
									    }
							/*		if(qty_mt2 <99){
										if(qty_mt2 >10){
											qty_mt2=(qty_mt2 - (qty_mt2 % 10));
										}}else{
										qty_mt2=(qty_mt2 - (qty_mt2 % 100));
									    }
									if(qty_scm2 <99){
										if(qty_scm2 >10){
											qty_scm2=(qty_scm2 - (qty_scm2 % 10));
										}}else{
											qty_scm2=(qty_scm2 - (qty_scm2 % 100));
									    }
							*/		
									vsec_qty_val_btu_other[j].add(nf2.format(qty_mmbtu2));
									 vsec_qty_val_mt_other[j].add(nf2.format(qty_mt2));
									 vsec_qty_val_scm_other[j].add(nf2.format(qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu3+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							////System.out.println("jaimin:::"+qty_mmbtu3);
							if(i==8)
							{
								qty_mt3 =  qty_mmbtu3 * 0.0193;
								qty_scm3=  qty_mmbtu3 / 38900;
								if(qty_mmbtu3 <99){
									if(qty_mmbtu3 >10){
										qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 10));
									}}else{
									    qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 100));
								    }
							/*	if(qty_mt3 <99){
									if(qty_mt3 >10){
										qty_mt3=(qty_mt3 - (qty_mt3 % 10));
									}}else{
									qty_mt3=(qty_mt3 - (qty_mt3 % 100));
								    }
								if(qty_scm3 <99){
									if(qty_scm3 >10){
										qty_scm3=(qty_scm3 - (qty_scm3 % 10));
									}}else{
										qty_scm3=(qty_scm3 - (qty_scm3 % 100));
								    }
							*/	
								vsec_qty_val_btu_other[j].add(nf2.format(qty_mmbtu3));
								 vsec_qty_val_mt_other[j].add(nf2.format(qty_mt3));
								 vsec_qty_val_scm_other[j].add(nf2.format(qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_mmbtu3==0.0 && qty_mmbtu3==0)
								{
									vsec_qty_val_btu_other[j].add("0");
									 vsec_qty_val_mt_other[j].add("0");
									 vsec_qty_val_scm_other[j].add("0");
								}
								else
								{
									qty_mt3 =  qty_mmbtu3 * 0.0193;
									qty_scm3=  qty_mmbtu3 / 38900;
									if(qty_mmbtu3 <99){
										if(qty_mmbtu3 >10){
											qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 10));
										}}else{
										    qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 100));
									    }
								/*	if(qty_mt3 <99){
										if(qty_mt3 >10){
											qty_mt3=(qty_mt3 - (qty_mt3 % 10));
										}}else{
										qty_mt3=(qty_mt3 - (qty_mt3 % 100));
									    }
									if(qty_scm3 <99){
										if(qty_scm3 >10){
											qty_scm3=(qty_scm3 - (qty_scm3 % 10));
										}}else{
											qty_scm3=(qty_scm3 - (qty_scm3 % 100));
									    }
								*/	
									vsec_qty_val_btu_other[j].add(nf2.format(qty_mmbtu3));
									 vsec_qty_val_mt_other[j].add(nf2.format(qty_mt3));
									 vsec_qty_val_scm_other[j].add(nf2.format(qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu4+=Double.parseDouble(total_QTY_MMBTU_NUMERIC); 
							if(i==11)
							{
								qty_mt4 =  qty_mmbtu4 * 0.0193;
								qty_scm4=  qty_mmbtu4 / 38900;
								if(qty_mmbtu4 <99){
									if(qty_mmbtu4 >10){
										qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 10));
									}}else{
									    qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 100));
								    }
							/*	if(qty_mt4 <99){
									if(qty_mt4 >10){
										qty_mt4=(qty_mt4 - (qty_mt4 % 10));
									}}else{
									qty_mt4=(qty_mt4 - (qty_mt4 % 100));
								    }
								if(qty_scm4 <99){
									if(qty_scm4 >10){
										qty_scm4=(qty_scm4 - (qty_scm4 % 10));
									}}else{
										qty_scm4=(qty_scm4 - (qty_scm4 % 100));
								    }
							*/	
								vsec_qty_val_btu_other[j].add(nf2.format(qty_mmbtu4));
								 vsec_qty_val_mt_other[j].add(nf2.format(qty_mt4));
								 vsec_qty_val_scm_other[j].add(nf2.format(qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_mmbtu4==0.0 && qty_mmbtu4==0)
								{
									vsec_qty_val_btu_other[j].add("0");
									 vsec_qty_val_mt_other[j].add("0");
									 vsec_qty_val_scm_other[j].add("0");
								}
								else
								{
									qty_mt4 =  qty_mmbtu4 * 0.0193;
									qty_scm4=  qty_mmbtu4 / 38900;
									if(qty_mmbtu4 <99){
										if(qty_mmbtu4 >10){
											qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 10));
										}}else{
										    qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 100));
									    }
							/*		if(qty_mt4 <99){
										if(qty_mt4 >10){
											qty_mt4=(qty_mt4 - (qty_mt4 % 10));
										}}else{
										qty_mt4=(qty_mt4 - (qty_mt4 % 100));
									    }
									if(qty_scm4 <99){
										if(qty_scm4 >10){
											qty_scm4=(qty_scm4 - (qty_scm4 % 10));
										}}else{
											qty_scm4=(qty_scm4 - (qty_scm4 % 100));
									    }
							*/		    
									vsec_qty_val_btu_other[j].add(nf2.format(qty_mmbtu4));
									 vsec_qty_val_mt_other[j].add(nf2.format(qty_mt4));
									 vsec_qty_val_scm_other[j].add(nf2.format(qty_scm4));
								}
							}
						}
					}
			
		
		Vector GAS_DT112=new Vector();
		Vector plant112=new Vector();
		Vector cust112=new Vector();
		queryString ="select DISTINCT(to_char(A.GAS_DT,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD_other.elementAt(j)+"' AND " +
			"TRIM(UPPER(B.PLANT_STATE))='"+plant_st+"' AND "+
			"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') ";
		////System.out.println("Outside guj::"+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			GAS_DT112.add(rset.getString(1)==null?"":rset.getString(1));
			cust112.add(rset.getString(2)==null?"":rset.getString(2));
			plant112.add(rset.getString(3)==null?"":rset.getString(3));				
		}
		    double temp_guj=0;
			String total_QTY_MMBTU_NUMERIC1="";
			for(int k=0;k<GAS_DT112.size();k++)
			{
				
			queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
			"where GAS_DT = to_date('"+GAS_DT112.elementAt(k)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust112.elementAt(k)+"' AND PLANT_SEQ_NO='"+plant112.elementAt(k)+"'" +
			" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			////System.out.println("SUM(QTY_MMBTU)2121212:: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				temp_guj += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
				total_QTY_MMBTU_NUMERIC1=""+(temp_guj);
			}
			
			}
		
		
		
		////System.out.println("Sector Company List= "+total_QTY_MMBTU_NUMERIC1);
			if(i<3)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu11+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							if(i==2)
							{
								qty_mt11 =  qty_mmbtu11 * 0.0193;
								qty_scm11=  qty_mmbtu11 / 38900;
								if(qty_mmbtu11 <99){
									if(qty_mmbtu11 >10){
										qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 10));
									}}else{
									    qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 100));
								    }
							/*	if(qty_mt11 <99){
									if(qty_mt11 >10){
										qty_mt11=(qty_mt11 - (qty_mt11 % 10));
									}}else{
									qty_mt11=(qty_mt11 - (qty_mt11 % 100));
								    }
								if(qty_scm11 <99){
									if(qty_scm11 >10){
										qty_scm11=(qty_scm11 - (qty_scm11 % 10));
									}}else{
										qty_scm11=(qty_scm11 - (qty_scm11 % 100));
								    }
							*/	
								 vsec_qty_val_btu_noguj_other[j].add(nf2.format(qty_mmbtu11));
								 vsec_qty_val_mt_noguj_other[j].add(nf2.format(qty_mt11));
								 vsec_qty_val_scm_noguj_other[j].add(nf2.format(qty_scm11));
								 
							}
						}
						else
						{
							if(i==2)
							{									
								if(qty_mmbtu11==0.0 && qty_mmbtu11==0)
								{
									vsec_qty_val_btu_noguj_other[j].add("0");
									 vsec_qty_val_mt_noguj_other[j].add("0");
									 vsec_qty_val_scm_noguj_other[j].add("0");
								}
								else
								{
									qty_mt11 =  qty_mmbtu11 * 0.0193;
									qty_scm11=  qty_mmbtu11 / 38900;
									if(qty_mmbtu11 <99){
										if(qty_mmbtu11 >10){
											qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 10));
										}}else{
										    qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 100));
									    }
								/*	if(qty_mt11 <99){
										if(qty_mt11 >10){
											qty_mt11=(qty_mt11 - (qty_mt11 % 10));
										}}else{
										qty_mt11=(qty_mt11 - (qty_mt11 % 100));
									    }
									if(qty_scm11 <99){
										if(qty_scm11 >10){
											qty_scm11=(qty_scm11 - (qty_scm11 % 10));
										}}else{
											qty_scm11=(qty_scm11 - (qty_scm11 % 100));
									    }
								*/	    
									vsec_qty_val_btu_noguj_other[j].add(nf2.format(qty_mmbtu11));
									 vsec_qty_val_mt_noguj_other[j].add(nf2.format(qty_mt11));
									 vsec_qty_val_scm_noguj_other[j].add(nf2.format(qty_scm11));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu21+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							if(i==5)
							{
								qty_mt21 =  qty_mmbtu21 * 0.0193;
								qty_scm21=  qty_mmbtu21 / 38900;
								if(qty_mmbtu21 <99){
									if(qty_mmbtu21 >10){
										qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 10));
									}}else{
									    qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 100));
								    }
							/*	if(qty_mt21 <99){
									if(qty_mt21 >10){
										qty_mt21=(qty_mt21 - (qty_mt21 % 10));
									}}else{
									qty_mt21=(qty_mt21 - (qty_mt21 % 100));
								    }
								if(qty_scm21 <99){
									if(qty_scm21 >10){
										qty_scm21=(qty_scm21 - (qty_scm21 % 10));
									}}else{
										qty_scm21=(qty_scm21 - (qty_scm21 % 100));
								    }
							*/	
								vsec_qty_val_btu_noguj_other[j].add(nf2.format(qty_mmbtu21));
							     vsec_qty_val_mt_noguj_other[j].add(nf2.format(qty_mt21));
								 vsec_qty_val_scm_noguj_other[j].add(nf2.format(qty_scm21));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_mmbtu21==0.0 && qty_mmbtu21==0)
								{
									vsec_qty_val_btu_noguj_other[j].add("0");
									 vsec_qty_val_mt_noguj_other[j].add("0");
									 vsec_qty_val_scm_noguj_other[j].add("0");
								}
								else
								{
									qty_mt21 =  qty_mmbtu21 * 0.0193;
									qty_scm21=  qty_mmbtu21 / 38900;
									if(qty_mmbtu21 <99){
										if(qty_mmbtu21 >10){
											qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 10));
										}}else{
										    qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 100));
									    }
							/*		if(qty_mt21 <99){
										if(qty_mt21 >10){
											qty_mt21=(qty_mt21 - (qty_mt21 % 10));
										}}else{
										qty_mt21=(qty_mt21 - (qty_mt21 % 100));
									    }
									if(qty_scm21 <99){
										if(qty_scm21 >10){
											qty_scm21=(qty_scm21 - (qty_scm21 % 10));
										}}else{
											qty_scm21=(qty_scm21 - (qty_scm21 % 100));
									    }
							*/		
									vsec_qty_val_btu_noguj_other[j].add(nf2.format(qty_mmbtu21));
									 vsec_qty_val_mt_noguj_other[j].add(nf2.format(qty_mt21));
									 vsec_qty_val_scm_noguj_other[j].add(nf2.format(qty_scm21));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu31+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							
							
							if(i==8)
							{
								qty_mt31 =  qty_mmbtu31 * 0.0193;
								qty_scm31=  qty_mmbtu31 / 38900;
								if(qty_mmbtu31 <99){
									if(qty_mmbtu31 >10){
										qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 10));
									}}else{
									    qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 100));
								    }
							/*	if(qty_mt31 <99){
									if(qty_mt31 >10){
										qty_mt31=(qty_mt31 - (qty_mt31 % 10));
									}}else{
									qty_mt31=(qty_mt31 - (qty_mt31 % 100));
								    }
								if(qty_scm31 <99){
									if(qty_scm31 >10){
										qty_scm31=(qty_scm31 - (qty_scm31 % 10));
									}}else{
										qty_scm31=(qty_scm31 - (qty_scm31 % 100));
								    }
								
							*/	
								vsec_qty_val_btu_noguj_other[j].add(nf2.format(qty_mmbtu31));
								 vsec_qty_val_mt_noguj_other[j].add(nf2.format(qty_mt31));
								 vsec_qty_val_scm_noguj_other[j].add(nf2.format(qty_scm31));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_mmbtu31==0.0 && qty_mmbtu31==0)
								{
									vsec_qty_val_btu_noguj_other[j].add("0");
									 vsec_qty_val_mt_noguj_other[j].add("0");
									 vsec_qty_val_scm_noguj_other[j].add("0");
								}
								else
								{
									qty_mt31 =  qty_mmbtu31 * 0.0193;
									qty_scm31=  qty_mmbtu31 / 38900;
									if(qty_mmbtu31 <99){
										if(qty_mmbtu31 >10){
											qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 10));
										}}else{
										    qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 100));
									    }
							/*		if(qty_mt31 <99){
										if(qty_mt31 >10){
											qty_mt31=(qty_mt31 - (qty_mt31 % 10));
										}}else{
										qty_mt31=(qty_mt31 - (qty_mt31 % 100));
									    }
									if(qty_scm31 <99){
										if(qty_scm31 >10){
											qty_scm31=(qty_scm31 - (qty_scm31 % 10));
										}}else{
											qty_scm31=(qty_scm31 - (qty_scm31 % 100));
									    }
									
							*/		
									vsec_qty_val_btu_noguj_other[j].add(nf2.format(qty_mmbtu31));
									 vsec_qty_val_mt_noguj_other[j].add(nf2.format(qty_mt31));
									 vsec_qty_val_scm_noguj_other[j].add(nf2.format(qty_scm31));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu41+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1); 
							if(i==11)
							{
								qty_mt41 =  qty_mmbtu41 * 0.0193;
								qty_scm41=  qty_mmbtu41 / 38900;
								if(qty_mmbtu41 <99){
									if(qty_mmbtu41 >10){
										qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 10));
									}}else{
									    qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 100));
								    }
							/*	if(qty_mt41 <99){
									if(qty_mt41 >10){
										qty_mt41=(qty_mt41 - (qty_mt41 % 10));
									}}else{
									qty_mt41=(qty_mt41 - (qty_mt41 % 100));
								    }
								if(qty_scm41 <99){
									if(qty_scm41 >10){
										qty_scm41=(qty_scm41 - (qty_scm41 % 10));
									}}else{
										qty_scm41=(qty_scm41 - (qty_scm41 % 100));
								    }
							*/	
								vsec_qty_val_btu_noguj_other[j].add(nf2.format(qty_mmbtu41));
								 vsec_qty_val_mt_noguj_other[j].add(nf2.format(qty_mt41));
								 vsec_qty_val_scm_noguj_other[j].add(nf2.format(qty_scm41));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_mmbtu41==0.0 && qty_mmbtu41==0)
								{
									vsec_qty_val_btu_noguj_other[j].add("0");
									 vsec_qty_val_mt_noguj_other[j].add("0");
									 vsec_qty_val_scm_noguj_other[j].add("0");
								}
								else
								{
									qty_mt41 =  qty_mmbtu41 * 0.0193;
									qty_scm41=  qty_mmbtu41 / 38900;
									if(qty_mmbtu41 <99){
										if(qty_mmbtu41 >10){
											qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 10));
										}}else{
										    qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 100));
									    }
							/*		if(qty_mt41 <99){
										if(qty_mt41 >10){
											qty_mt41=(qty_mt41 - (qty_mt41 % 10));
										}}else{
										qty_mt41=(qty_mt41 - (qty_mt41 % 100));
									    }
									if(qty_scm41 <99){
										if(qty_scm41 >10){
											qty_scm41=(qty_scm41 - (qty_scm41 % 10));
										}}else{
											qty_scm41=(qty_scm41 - (qty_scm41 % 100));
									    }
							*/		
									vsec_qty_val_btu_noguj_other[j].add(nf2.format(qty_mmbtu41));
									 vsec_qty_val_mt_noguj_other[j].add(nf2.format(qty_mt41));
									 vsec_qty_val_scm_noguj_other[j].add(nf2.format(qty_scm41));
								}
							}
						}
					}
		  //vsec_qty_val_btu[j].add(QTY_MMBTU.elementAt(i));
		 // vsec_qty_val_mt[j].add(QTY_MT.elementAt(i));
		////System.out.println("vsec_qty_val_btu_other = "+vsec_qty_val_btu_other[j]);
		////System.out.println("vsec_qty_val_btu_noguj_other = "+vsec_qty_val_btu_noguj_other[j]);
		}
	 }
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public void sector_specification_reseller(){
		
		
		  mnth="04";
		  String mm="04";
		  String yy="";
		  String year1="";
		  
		
		  
		  
	try{	
		queryString = "select SECTOR_CD, SECTOR_ABBR FROM FMS7_SECTOR_MST WHERE FLAG='Y'  ORDER BY priority";
		////System.out.println("Sector Selection Sector Master Query = "+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			SECTOR_CD1.add(rset.getString(1)==null?"":rset.getString(1));
			SECTOR_NM1.add(rset.getString(2)==null?"":rset.getString(2));
		}
		////System.out.println("SECTOR_CD1.size()::"+SECTOR_CD1.size()+"year:"+year);
		if(!year.trim().equals("0") && !year.trim().equals(""))
		 {
			vsec_qty_val_btu1 = new Vector[SECTOR_CD1.size()];
			vsec_qty_val_mt1= new Vector[SECTOR_CD1.size()];
			vsec_qty_val_scm1= new Vector[SECTOR_CD1.size()];
			vsec_qty_val_btu_noguj1 = new Vector[SECTOR_CD1.size()];
			vsec_qty_val_mt_noguj1= new Vector[SECTOR_CD1.size()];
			vsec_qty_val_scm_noguj1= new Vector[SECTOR_CD1.size()];
		for(int j=0; j<SECTOR_CD1.size(); j++)
		{
			
			  double qty_mmbtu1=0.0;
			  double qty_mmbtu2=0.0;
			  double qty_mmbtu3=0.0;
			  double qty_mmbtu4=0.0;
			  double qty_scm1=0.0;
			  double qty_scm2=0.0;
			  double qty_scm3=0.0;
			  double qty_scm4=0.0;
			  double qty_mt1=0.0;
			  double qty_mt2=0.0;
			  double qty_mt3=0.0;
			  double qty_mt4=0.0;
			  
			  double qty_mmbtu11=0.0;
			  double qty_mmbtu21=0.0;
			  double qty_mmbtu31=0.0;
			  double qty_mmbtu41=0.0;
			  double qty_scm11=0.0;
			  double qty_scm21=0.0;
			  double qty_scm31=0.0;
			  double qty_scm41=0.0;
			  double qty_mt11=0.0;
			  double qty_mt21=0.0;
			  double qty_mt31=0.0;
			  double qty_mt41=0.0;
			
			vsec_qty_val_btu1[j]=new Vector();
			vsec_qty_val_mt1[j]= new Vector();  
			vsec_qty_val_scm1[j]= new Vector();
			vsec_qty_val_btu_noguj1[j]=new Vector();
			vsec_qty_val_mt_noguj1[j]= new Vector();
			vsec_qty_val_scm_noguj1[j]= new Vector();
			////System.out.println("mnth ="+mnth);		
		for(int i=0;i<12;i++)
			{
				yy=year; 
				year1=year;
				mnth=""+(Integer.parseInt(mm)+i);
			////System.out.println("mnth ="+mnth);	
			////System.out.println("year = "+year+",yy ="+yy+",year1= "+year1);		
		if (Integer.parseInt(mnth)<=12 )
		{
			month=""+ Integer.parseInt(mnth);					
		}				
		else if(Integer.parseInt(mnth)>12)
		{
			month =""+(Integer.parseInt(mnth)%12);
			mnth=""+(Integer.parseInt(mnth)/12);
			year1=""+ (Integer.parseInt(yy)+1);						
		}					
		////System.out.println("month = "+month+"mnth ="+mnth);			
		from_date="01"+"/"+month+"/"+year1;
		////System.out.println("from_date = "+from_date);
		queryString ="select TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
		//////System.out.println("Last date = "+queryString);
		rset = stmt.executeQuery(queryString);				
		if(rset.next())
		{
			to_date=rset.getString(1)==null?"":rset.getString(1);
		}
		else
		{
			to_date="";
		}
				
	//	queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
	//	"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD.elementAt(j)+"' AND " +
	//	"TRIM(UPPER(B.PLANT_STATE))!='"+plant_st+"' AND "+
	//	"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";	
		Vector GAS_DT34=new Vector();
		Vector plant34=new Vector();
		Vector cust34=new Vector();
		queryString ="select DISTINCT(to_char(A.GAS_DT,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD1.elementAt(j)+"' AND " +
			"TRIM(UPPER(B.PLANT_STATE))!='"+plant_st+"' AND "+
			"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L')";
		//////System.out.println(queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			GAS_DT34.add(rset.getString(1)==null?"":rset.getString(1));
			cust34.add(rset.getString(2)==null?"":rset.getString(2));
			plant34.add(rset.getString(3)==null?"":rset.getString(3));				
		}
		    double temp=0;
			String total_QTY_MMBTU_NUMERIC="";
			for(int k=0;k<GAS_DT34.size();k++)
			{
				
			queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
			"where GAS_DT = to_date('"+GAS_DT34.elementAt(k)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust34.elementAt(k)+"' AND PLANT_SEQ_NO='"+plant34.elementAt(k)+"' " +
			" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			////System.out.println("SUM(QTY_MMBTU) "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				temp += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
				 total_QTY_MMBTU_NUMERIC=""+(temp);
			}
			else
			{
				total_QTY_MMBTU_NUMERIC=null;
			}
			}
		
		////System.out.println("Sector Company List= "+total_QTY_MMBTU_NUMERIC);
		
			
					if(i<3)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu1+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==2)
							{
								qty_mt1 =  qty_mmbtu1 * 0.0193;
								qty_scm1=  qty_mmbtu1 / 38900;
								if(qty_mmbtu1 <99){
									if(qty_mmbtu1 >10){
										qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 10));
									}}else{
									    qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 100));
								    }
							/*	if(qty_mt1 <99){
									if(qty_mt1 >10){
										qty_mt1=(qty_mt1 - (qty_mt1 % 10));
									}}else{
									qty_mt1=(qty_mt1 - (qty_mt1 % 100));
								    }
								if(qty_scm1 <99){
									if(qty_scm1 >10){
										qty_scm1=(qty_scm1 - (qty_scm1 % 10));
									}}else{
										qty_scm1=(qty_scm1 - (qty_scm1 % 100));
								    }
							*/	    
								 vsec_qty_val_btu1[j].add(nf2.format(qty_mmbtu1));
								 vsec_qty_val_mt1[j].add(nf2.format(qty_mt1));
								 vsec_qty_val_scm1[j].add(nf2.format(qty_scm1));
								 
							}
						}
						else
						{
							if(i==2)
							{									
								if(qty_mmbtu1==0.0 && qty_mmbtu1==0)
								{
									 vsec_qty_val_btu1[j].add("0");
									 vsec_qty_val_mt1[j].add("0");
									 vsec_qty_val_scm1[j].add("0");
								}
								else
								{
									qty_mt1 =  qty_mmbtu1 * 0.0193;
									qty_scm1=  qty_mmbtu1 / 38900;
									if(qty_mmbtu1 <99){
										if(qty_mmbtu1 >10){
											qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 10));
										}}else{
										    qty_mmbtu1=(qty_mmbtu1 - (qty_mmbtu1 % 100));
									    }
							/*		if(qty_mt1 <99){
										if(qty_mt1 >10){
											qty_mt1=(qty_mt1 - (qty_mt1 % 10));
										}}else{
										qty_mt1=(qty_mt1 - (qty_mt1 % 100));
									    }
									if(qty_scm1 <99){
										if(qty_scm1 >10){
											qty_scm1=(qty_scm1 - (qty_scm1 % 10));
										}}else{
											qty_scm1=(qty_scm1 - (qty_scm1 % 100));
									    }
							*/		    
								 	vsec_qty_val_btu1[j].add(nf2.format(qty_mmbtu1));
									vsec_qty_val_mt1[j].add(nf2.format(qty_mt1));
									vsec_qty_val_scm1[j].add(nf2.format(qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu2+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							if(i==5)
							{
								qty_mt2 =  qty_mmbtu2 * 0.0193;
								qty_scm2=  qty_mmbtu2 / 38900;
								if(qty_mmbtu2 <99){
									if(qty_mmbtu2 >10){
										qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 10));
									}}else{
									    qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 100));
								    }
							/*	if(qty_mt2 <99){
									if(qty_mt2 >10){
										qty_mt2=(qty_mt2 - (qty_mt2 % 10));
									}}else{
									qty_mt2=(qty_mt2 - (qty_mt2 % 100));
								    }
								if(qty_scm2 <99){
									if(qty_scm2 >10){
										qty_scm2=(qty_scm2 - (qty_scm2 % 10));
									}}else{
										qty_scm2=(qty_scm2 - (qty_scm2 % 100));
								    }
							*/	
								vsec_qty_val_btu1[j].add(nf2.format(qty_mmbtu2));
								 vsec_qty_val_mt1[j].add(nf2.format(qty_mt2));
								 vsec_qty_val_scm1[j].add(nf2.format(qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_mmbtu2==0.0 && qty_mmbtu2==0)
								{
									vsec_qty_val_btu1[j].add("0");
									 vsec_qty_val_mt1[j].add("0");
									 vsec_qty_val_scm1[j].add("0");
								}
								else
								{
									qty_mt2 =  qty_mmbtu2 * 0.0193;
									qty_scm2=  qty_mmbtu2 / 38900;
									if(qty_mmbtu2 <99){
										if(qty_mmbtu2 >10){
											qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 10));
										}}else{
										    qty_mmbtu2=(qty_mmbtu2 - (qty_mmbtu2 % 100));
									    }
								/*	if(qty_mt2 <99){
										if(qty_mt2 >10){
											qty_mt2=(qty_mt2 - (qty_mt2 % 10));
										}}else{
										qty_mt2=(qty_mt2 - (qty_mt2 % 100));
									    }
									if(qty_scm2 <99){
										if(qty_scm2 >10){
											qty_scm2=(qty_scm2 - (qty_scm2 % 10));
										}}else{
											qty_scm2=(qty_scm2 - (qty_scm2 % 100));
									    }
								*/	
									vsec_qty_val_btu1[j].add(nf2.format(qty_mmbtu2));
									 vsec_qty_val_mt1[j].add(nf2.format(qty_mt2));
									 vsec_qty_val_scm1[j].add(nf2.format(qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu3+=Double.parseDouble(total_QTY_MMBTU_NUMERIC);
							////System.out.println("jaimin:::"+qty_mmbtu3);
							if(i==8)
							{
								qty_mt3 =  qty_mmbtu3 * 0.0193;
								qty_scm3=  qty_mmbtu3 / 38900;
								if(qty_mmbtu3 <99){
									if(qty_mmbtu3 >10){
										qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 10));
									}}else{
									    qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 100));
								    }
							/*	if(qty_mt3 <99){
									if(qty_mt3 >10){
										qty_mt3=(qty_mt3 - (qty_mt3 % 10));
									}}else{
									qty_mt3=(qty_mt3 - (qty_mt3 % 100));
								    }
								if(qty_scm3 <99){
									if(qty_scm3 >10){
										qty_scm3=(qty_scm3 - (qty_scm3 % 10));
									}}else{
										qty_scm3=(qty_scm3 - (qty_scm3 % 100));
								    }
								*/
								
								vsec_qty_val_btu1[j].add(nf2.format(qty_mmbtu3));
								 vsec_qty_val_mt1[j].add(nf2.format(qty_mt3));
								 vsec_qty_val_scm1[j].add(nf2.format(qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_mmbtu3==0.0 && qty_mmbtu3==0)
								{
									vsec_qty_val_btu1[j].add("0");
									 vsec_qty_val_mt1[j].add("0");
									 vsec_qty_val_scm1[j].add("0");
								}
								else
								{
									qty_mt3 =  qty_mmbtu3 * 0.0193;
									qty_scm3=  qty_mmbtu3 / 38900;
									if(qty_mmbtu3 <99){
										if(qty_mmbtu3 >10){
											qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 10));
										}}else{
										    qty_mmbtu3=(qty_mmbtu3 - (qty_mmbtu3 % 100));
									    }
								/*	if(qty_mt3 <99){
										if(qty_mt3 >10){
											qty_mt3=(qty_mt3 - (qty_mt3 % 10));
										}}else{
										qty_mt3=(qty_mt3 - (qty_mt3 % 100));
									    }
									if(qty_scm3 <99){
										if(qty_scm3 >10){
											qty_scm3=(qty_scm3 - (qty_scm3 % 10));
										}}else{
											qty_scm3=(qty_scm3 - (qty_scm3 % 100));
									    }
									
								*/	
									vsec_qty_val_btu1[j].add(nf2.format(qty_mmbtu3));
									 vsec_qty_val_mt1[j].add(nf2.format(qty_mt3));
									 vsec_qty_val_scm1[j].add(nf2.format(qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(!total_QTY_MMBTU_NUMERIC.equalsIgnoreCase(""))
						{
							qty_mmbtu4+=Double.parseDouble(total_QTY_MMBTU_NUMERIC); 
							if(i==11)
							{
								qty_mt4 =  qty_mmbtu4 * 0.0193;
								qty_scm4=  qty_mmbtu4 / 38900;
								if(qty_mmbtu4 <99){
									if(qty_mmbtu4 >10){
										qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 10));
									}}else{
									    qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 100));
								    }
							/*	if(qty_mt4 <99){
									if(qty_mt4 >10){
										qty_mt4=(qty_mt4 - (qty_mt4 % 10));
									}}else{
									qty_mt4=(qty_mt4 - (qty_mt4 % 100));
								    }
								if(qty_scm4 <99){
									if(qty_scm4 >10){
										qty_scm4=(qty_scm4 - (qty_scm4 % 10));
									}}else{
										qty_scm4=(qty_scm4 - (qty_scm4 % 100));
								    }
								*/
								vsec_qty_val_btu1[j].add(nf2.format(qty_mmbtu4));
								 vsec_qty_val_mt1[j].add(nf2.format(qty_mt4));
								 vsec_qty_val_scm1[j].add(nf2.format(qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_mmbtu4==0.0 && qty_mmbtu4==0)
								{
									vsec_qty_val_btu1[j].add("0");
									 vsec_qty_val_mt1[j].add("0");
									 vsec_qty_val_scm1[j].add("0");
								}
								else
								{
									qty_mt4 =  qty_mmbtu4 * 0.0193;
									qty_scm4=  qty_mmbtu4 / 38900;
									if(qty_mmbtu4 <99){
										if(qty_mmbtu4 >10){
											qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 10));
										}}else{
										    qty_mmbtu4=(qty_mmbtu4 - (qty_mmbtu4 % 100));
									    }
								/*	if(qty_mt4 <99){
										if(qty_mt4 >10){
											qty_mt4=(qty_mt4 - (qty_mt4 % 10));
										}}else{
										qty_mt4=(qty_mt4 - (qty_mt4 % 100));
									    }
									if(qty_scm4 <99){
										if(qty_scm4 >10){
											qty_scm4=(qty_scm4 - (qty_scm4 % 10));
										}}else{
											qty_scm4=(qty_scm4 - (qty_scm4 % 100));
									    }
								*/	
									vsec_qty_val_btu1[j].add(nf2.format(qty_mmbtu4));
									 vsec_qty_val_mt1[j].add(nf2.format(qty_mt4));
									 vsec_qty_val_scm1[j].add(nf2.format(qty_scm4));
								}
							}
						}
					}
			
		
		Vector GAS_DT134=new Vector();
		Vector plant134=new Vector();
		Vector cust134=new Vector();
		queryString ="select DISTINCT(to_char(A.GAS_DT,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='"+SECTOR_CD1.elementAt(j)+"' AND " +
			"TRIM(UPPER(B.PLANT_STATE))='"+plant_st+"' AND "+
			"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') ";
		//////System.out.println("Outside guj::"+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			GAS_DT134.add(rset.getString(1)==null?"":rset.getString(1));
			cust134.add(rset.getString(2)==null?"":rset.getString(2));
			plant134.add(rset.getString(3)==null?"":rset.getString(3));	
		}
		    double temp_guj=0;
			String total_QTY_MMBTU_NUMERIC1="";
			for(int k=0;k<GAS_DT134.size();k++)
			{
				
			queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
			"where GAS_DT = to_date('"+GAS_DT134.elementAt(k)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust134.elementAt(k)+"' AND PLANT_SEQ_NO='"+plant134.elementAt(k)+"' " +
			" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			////System.out.println("SUM(QTY_MMBTU)2121212:: "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				temp_guj += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
				total_QTY_MMBTU_NUMERIC1=""+(temp_guj);
			}
			
			}
		
		
		
		////System.out.println("Sector Company List= "+total_QTY_MMBTU_NUMERIC1);
			if(i<3)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu11+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							if(i==2)
							{
								qty_mt11 =  qty_mmbtu11 * 0.0193;
								qty_scm11=  qty_mmbtu11 / 38900;
								if(qty_mmbtu11 <99){
									if(qty_mmbtu11 >10){
										qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 10));
									}}else{
									    qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 100));
								    }
							/*	if(qty_mt11 <99){
									if(qty_mt11 >10){
										qty_mt11=(qty_mt11 - (qty_mt11 % 10));
									}}else{
									qty_mt11=(qty_mt11 - (qty_mt11 % 100));
								    }
								if(qty_scm11 <99){
									if(qty_scm11 >10){
										qty_scm11=(qty_scm11 - (qty_scm11 % 10));
									}}else{
										qty_scm11=(qty_scm11 - (qty_scm11 % 100));
								    }
							*/	
								 vsec_qty_val_btu_noguj1[j].add(nf2.format(qty_mmbtu11));
								 vsec_qty_val_mt_noguj1[j].add(nf2.format(qty_mt11));
								 vsec_qty_val_scm_noguj1[j].add(nf2.format(qty_scm11));
								 
							}
						}
						else
						{
							if(i==2)
							{									
								if(qty_mmbtu11==0.0 && qty_mmbtu11==0)
								{
									vsec_qty_val_btu_noguj1[j].add("0");
									 vsec_qty_val_mt_noguj1[j].add("0");
									 vsec_qty_val_scm_noguj1[j].add("0");
								}
								else
								{
									qty_mt11 =  qty_mmbtu11 * 0.0193;
									qty_scm11=  qty_mmbtu11 / 38900;
									if(qty_mmbtu11 <99){
										if(qty_mmbtu11 >10){
											qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 10));
										}}else{
										    qty_mmbtu11=(qty_mmbtu11 - (qty_mmbtu11 % 100));
									    }
							/*		if(qty_mt11 <99){
										if(qty_mt11 >10){
											qty_mt11=(qty_mt11 - (qty_mt11 % 10));
										}}else{
										qty_mt11=(qty_mt11 - (qty_mt11 % 100));
									    }
									if(qty_scm11 <99){
										if(qty_scm11 >10){
											qty_scm11=(qty_scm11 - (qty_scm11 % 10));
										}}else{
											qty_scm11=(qty_scm11 - (qty_scm11 % 100));
									    }
							*/		
									vsec_qty_val_btu_noguj1[j].add(nf2.format(qty_mmbtu11));
									 vsec_qty_val_mt_noguj1[j].add(nf2.format(qty_mt11));
									 vsec_qty_val_scm_noguj1[j].add(nf2.format(qty_scm11));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu21+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							if(i==5)
							{
								qty_mt21 =  qty_mmbtu21 * 0.0193;
								qty_scm21=  qty_mmbtu21 / 38900;
								if(qty_mmbtu21 <99){
									if(qty_mmbtu21 >10){
										qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 10));
									}}else{
									    qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 100));
								    }
							/*	if(qty_mt21 <99){
									if(qty_mt21 >10){
										qty_mt21=(qty_mt21 - (qty_mt21 % 10));
									}}else{
									qty_mt21=(qty_mt21 - (qty_mt21 % 100));
								    }
								if(qty_scm21 <99){
									if(qty_scm21 >10){
										qty_scm21=(qty_scm21 - (qty_scm21 % 10));
									}}else{
										qty_scm21=(qty_scm21 - (qty_scm21 % 100));
								    }
							*/	
								vsec_qty_val_btu_noguj1[j].add(nf2.format(qty_mmbtu21));
								 vsec_qty_val_mt_noguj1[j].add(nf2.format(qty_mt21));
								 vsec_qty_val_scm_noguj1[j].add(nf2.format(qty_scm21));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_mmbtu21==0.0 && qty_mmbtu21==0)
								{
									vsec_qty_val_btu_noguj1[j].add("0");
									 vsec_qty_val_mt_noguj1[j].add("0");
									 vsec_qty_val_scm_noguj1[j].add("0");
								}
								else
								{
									qty_mt21 =  qty_mmbtu21 * 0.0193;
									qty_scm21=  qty_mmbtu21 / 38900;
									if(qty_mmbtu21 <99){
										if(qty_mmbtu21 >10){
											qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 10));
										}}else{
										    qty_mmbtu21=(qty_mmbtu21 - (qty_mmbtu21 % 100));
									    }
								/*	if(qty_mt21 <99){
										if(qty_mt21 >10){
											qty_mt21=(qty_mt21 - (qty_mt21 % 10));
										}}else{
										qty_mt21=(qty_mt21 - (qty_mt21 % 100));
									    }
									if(qty_scm21 <99){
										if(qty_scm21 >10){
											qty_scm21=(qty_scm21 - (qty_scm21 % 10));
										}}else{
											qty_scm21=(qty_scm21 - (qty_scm21 % 100));
									    }
								*/	
									vsec_qty_val_btu_noguj1[j].add(nf2.format(qty_mmbtu21));
									 vsec_qty_val_mt_noguj1[j].add(nf2.format(qty_mt21));
									 vsec_qty_val_scm_noguj1[j].add(nf2.format(qty_scm21));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu31+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1);
							
							
							if(i==8)
							{
								qty_mt31 =  qty_mmbtu31 * 0.0193;
								qty_scm31=  qty_mmbtu31 / 38900;
								if(qty_mmbtu31 <99){
									if(qty_mmbtu31 >10){
										qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 10));
									}}else{
									    qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 100));
								    }
							/*	if(qty_mt31 <99){
									if(qty_mt31 >10){
										qty_mt31=(qty_mt31 - (qty_mt31 % 10));
									}}else{
									qty_mt31=(qty_mt31 - (qty_mt31 % 100));
								    }
								if(qty_scm31 <99){
									if(qty_scm31 >10){
										qty_scm31=(qty_scm31 - (qty_scm31 % 10));
									}}else{
										qty_scm31=(qty_scm31 - (qty_scm31 % 100));
								    }
							*/	
								vsec_qty_val_btu_noguj1[j].add(nf2.format(qty_mmbtu31));
								 vsec_qty_val_mt_noguj1[j].add(nf2.format(qty_mt31));
								 vsec_qty_val_scm_noguj1[j].add(nf2.format(qty_scm31));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_mmbtu31==0.0 && qty_mmbtu31==0)
								{
									vsec_qty_val_btu_noguj1[j].add("0");
									 vsec_qty_val_mt_noguj1[j].add("0");
									 vsec_qty_val_scm_noguj1[j].add("0");
								}
								else
								{
									qty_mt31 =  qty_mmbtu31 * 0.0193;
									qty_scm31=  qty_mmbtu31 / 38900;
									if(qty_mmbtu31 <99){
										if(qty_mmbtu31 >10){
											qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 10));
										}}else{
										    qty_mmbtu31=(qty_mmbtu31 - (qty_mmbtu31 % 100));
									    }
							/*		if(qty_mt31 <99){
										if(qty_mt31 >10){
											qty_mt31=(qty_mt31 - (qty_mt31 % 10));
										}}else{
										qty_mt31=(qty_mt31 - (qty_mt31 % 100));
									    }
									if(qty_scm31 <99){
										if(qty_scm31 >10){
											qty_scm31=(qty_scm31 - (qty_scm31 % 10));
										}}else{
											qty_scm31=(qty_scm31 - (qty_scm31 % 100));
									    }
							*/		
									vsec_qty_val_btu_noguj1[j].add(nf2.format(qty_mmbtu31));
									 vsec_qty_val_mt_noguj1[j].add(nf2.format(qty_mt31));
									 vsec_qty_val_scm_noguj1[j].add(nf2.format(qty_scm31));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(!total_QTY_MMBTU_NUMERIC1.equalsIgnoreCase(""))
						{
							qty_mmbtu41+=Double.parseDouble(total_QTY_MMBTU_NUMERIC1); 
							if(i==11)
							{
								qty_mt41 =  qty_mmbtu41 * 0.0193;
								qty_scm41=  qty_mmbtu41 / 38900;
								if(qty_mmbtu41 <99){
									if(qty_mmbtu41 >10){
										qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 10));
									}}else{
									    qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 100));
								    }
							/*	if(qty_mt41 <99){
									if(qty_mt41 >10){
										qty_mt41=(qty_mt41 - (qty_mt41 % 10));
									}}else{
									qty_mt41=(qty_mt41 - (qty_mt41 % 100));
								    }
								if(qty_scm41 <99){
									if(qty_scm41 >10){
										qty_scm41=(qty_scm41 - (qty_scm41 % 10));
									}}else{
										qty_scm41=(qty_scm41 - (qty_scm41 % 100));
								    }
							*/	
								vsec_qty_val_btu_noguj1[j].add(nf2.format(qty_mmbtu41));
								 vsec_qty_val_mt_noguj1[j].add(nf2.format(qty_mt41));
								 vsec_qty_val_scm_noguj1[j].add(nf2.format(qty_scm41));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_mmbtu41==0.0 && qty_mmbtu41==0)
								{
									vsec_qty_val_btu_noguj1[j].add("0");
									 vsec_qty_val_mt_noguj1[j].add("0");
									 vsec_qty_val_scm_noguj1[j].add("0");
								}
								else
								{
									qty_mt41 =  qty_mmbtu41 * 0.0193;
									qty_scm41=  qty_mmbtu41 / 38900;
									if(qty_mmbtu41 <99){
										if(qty_mmbtu41 >10){
											qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 10));
										}}else{
										    qty_mmbtu41=(qty_mmbtu41 - (qty_mmbtu41 % 100));
									    }
								/*	if(qty_mt41 <99){
										if(qty_mt41 >10){
											qty_mt41=(qty_mt41 - (qty_mt41 % 10));
										}}else{
										qty_mt41=(qty_mt41 - (qty_mt41 % 100));
									    }
									if(qty_scm41 <99){
										if(qty_scm41 >10){
											qty_scm41=(qty_scm41 - (qty_scm41 % 10));
										}}else{
											qty_scm41=(qty_scm41 - (qty_scm41 % 100));
									    }
								*/	
									
									vsec_qty_val_btu_noguj1[j].add(nf2.format(qty_mmbtu41));
									 vsec_qty_val_mt_noguj1[j].add(nf2.format(qty_mt41));
									 vsec_qty_val_scm_noguj1[j].add(nf2.format(qty_scm41));
								}
							}
						}
					}
		  //vsec_qty_val_btu[j].add(QTY_MMBTU.elementAt(i));
		 // vsec_qty_val_mt[j].add(QTY_MT.elementAt(i));
		////System.out.println("vsec_qty_val_btu = "+vsec_qty_val_btu1[j]);
		////System.out.println("vsec_qty_val_btu_noguj = "+vsec_qty_val_btu_noguj1[j]);
		}
	 }
	}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	
	//Logic for Gift Tracking for Employees Customer/Trader/Transporter/Other Wise
	public void fetchGiftTrackingDetails()
	{
		try
		{
			//////System.out.println("Criteria : "+criteria);					
			Vector MONTH = new Vector();
			Vector YEAR = new Vector();			
			String from_mon_yr=month+"/"+year;
			String to_mon_yr=to_month+"/"+to_year;
			
			queryString = "SELECT MONTHS_BETWEEN(TO_DATE('"+to_mon_yr+"','MM/YYYY'),TO_DATE('"+from_mon_yr+"','MM/YYYY'))+1 FROM dual";
			////System.out.println("MONTHS_BETWEEN Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);	
			if(rset.next())
			{
				loop_count=rset.getInt(1);
			}
			
			int month_cnt=Integer.parseInt(month);
			int year_cnt=Integer.parseInt(year);			
			String month_loop="";
						
			for(int i=0; i<loop_count; i++)
			{
				if(month_cnt>12)
				{
					month_cnt-=12;
					++year_cnt;					
				}
				
				MONTH.add(""+month_cnt);
				YEAR.add(""+year_cnt);
				++month_cnt;
			}
						
			if(criteria.trim().equalsIgnoreCase("customer"))
			{										
					queryString = "SELECT A.SEQ_NO,UPPER(A.CONTACT_PERSON) FROM " +
								  "FMS7_CUSTOMER_CONTACT_MST A " +
								  "WHERE A.CUSTOMER_CD="+customer_cd+" " +
								  "AND A.EFF_DT=(SELECT MAX(B.EFF_DT) " +
								  "FROM FMS7_CUSTOMER_CONTACT_MST B WHERE " +
								  "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.SEQ_NO=B.SEQ_NO " +
								  "AND B.EFF_DT<=sysdate)" +
								  "ORDER BY UPPER(A.CONTACT_PERSON)";
					////System.out.println("FMS7_CUSTOMER_CONTACT_MST Fetch Query = "+queryString);
					rset = stmt.executeQuery(queryString);	
					while(rset.next())
					{
						SEQ_NO.add(rset.getString(1)==null?"0":rset.getString(1));
						CONTACT_PERSON.add(rset.getString(2)==null?"":rset.getString(2));
					}
					
					for(int i=0;i<SEQ_NO.size();i++)
					{
						for(int j=0;j<MONTH.size();j++)
						{													
							queryString = "SELECT CALENDAR_FLAG,GIFT_FLAG,DIARY_FLAG,LEAFLET_FLAG," +
							"OTHER_1,OTHER_2,DESCRIPTION  FROM FMS7_CUSTOMER_GIFT_TRACK_DTL " +
							"WHERE CUSTOMER_CD="+customer_cd+" AND SEQ_NO="+SEQ_NO.elementAt(i)+" AND " +
							"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
							//////System.out.println("Calendar Fetch Query = "+queryString);
							rset = stmt.executeQuery(queryString);	
							if(rset.next())
							{
								CALENDAR.add(rset.getString(1)==null?"N":rset.getString(1));	
								GIFT.add(rset.getString(2)==null?"N":rset.getString(2));	
								DIARY.add(rset.getString(3)==null?"N":rset.getString(3));
								LEAFLET.add(rset.getString(4)==null?"N":rset.getString(4));	
								OTHER_1.add(rset.getString(5)==null?"N":rset.getString(5));	
								OTHER_2.add(rset.getString(6)==null?"N":rset.getString(6));	
								DESCRIPTION.add(rset.getString(7)==null?"":rset.getString(7));	
							}
							else
							{
								CALENDAR.add("N");	
								GIFT.add("N");	
								DIARY.add("N");
								LEAFLET.add("N");	
								OTHER_1.add("N");	
								OTHER_2.add("N");	
								DESCRIPTION.add("");	
							}
							
							if(Integer.parseInt(""+MONTH.elementAt(j))<10)
							{
								month_loop="0"+Integer.parseInt(""+MONTH.elementAt(j));
							}
							else
							{
								month_loop=""+Integer.parseInt(""+MONTH.elementAt(j));
							}
							
							String mon_yr=""+month_loop+"/"+YEAR.elementAt(j);
							queryString = "SELECT TO_CHAR(TO_DATE('"+mon_yr+"','MM/YYYY'),'MONTH, YYYY') FROM dual";
							////System.out.println("MONTH_VALUE Fetch Query = "+queryString);
							rset = stmt.executeQuery(queryString);	
							if(rset.next())
							{
								MONTH_VALUE.add(rset.getString(1)==null?"N":rset.getString(1));	
							}
							else
							{
								MONTH_VALUE.add("");
							}
						}
					}
			}//End of logic for Customer
			else if(criteria.trim().equalsIgnoreCase("transporter"))
			{
				queryString = "SELECT A.SEQ_NO,UPPER(A.CONTACT_PERSON) FROM " +
							  "FMS7_TRANSPORTER_CONTACT_MST A WHERE " +
							  "A.TRANSPORTER_CD="+customer_cd+" " +
							  "AND A.EFF_DT=(SELECT MAX(B.EFF_DT) " +
							  "FROM FMS7_TRANSPORTER_CONTACT_MST B WHERE " +
							  "A.TRANSPORTER_CD=B.TRANSPORTER_CD AND A.SEQ_NO=B.SEQ_NO " +
							  "AND B.EFF_DT<=sysdate)" +
							  "ORDER BY UPPER(A.CONTACT_PERSON)";
				////System.out.println("FMS7_TRANSPORTER_CONTACT_MST Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);	
				while(rset.next())
				{
					SEQ_NO.add(rset.getString(1)==null?"0":rset.getString(1));
					CONTACT_PERSON.add(rset.getString(2)==null?"":rset.getString(2));
				}
				
				for(int i=0;i<SEQ_NO.size();i++)
				{	
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT CALENDAR_FLAG,GIFT_FLAG,DIARY_FLAG,LEAFLET_FLAG," +
						"OTHER_1,OTHER_2,DESCRIPTION  FROM FMS7_TRANS_GIFT_TRACK_DTL " +
						"WHERE TRANSPORTER_CD="+customer_cd+" AND SEQ_NO="+SEQ_NO.elementAt(i)+" AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						//////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							CALENDAR.add(rset.getString(1)==null?"N":rset.getString(1));	
							GIFT.add(rset.getString(2)==null?"N":rset.getString(2));	
							DIARY.add(rset.getString(3)==null?"N":rset.getString(3));
							LEAFLET.add(rset.getString(4)==null?"N":rset.getString(4));	
							OTHER_1.add(rset.getString(5)==null?"N":rset.getString(5));	
							OTHER_2.add(rset.getString(6)==null?"N":rset.getString(6));	
							DESCRIPTION.add(rset.getString(7)==null?"":rset.getString(7));	
						}
						else
						{
							CALENDAR.add("N");	
							GIFT.add("N");	
							DIARY.add("N");
							LEAFLET.add("N");	
							OTHER_1.add("N");	
							OTHER_2.add("N");	
							DESCRIPTION.add("");	
						}
						
						if(Integer.parseInt(""+MONTH.elementAt(j))<10)
						{
							month_loop="0"+Integer.parseInt(""+MONTH.elementAt(j));
						}
						else
						{
							month_loop=""+Integer.parseInt(""+MONTH.elementAt(j));
						}
						
						String mon_yr=""+month_loop+"/"+YEAR.elementAt(j);
						queryString = "SELECT TO_CHAR(TO_DATE('"+mon_yr+"','MM/YYYY'),'MONTH, YYYY') FROM dual";
						////System.out.println("MONTH_VALUE Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							MONTH_VALUE.add(rset.getString(1)==null?"N":rset.getString(1));	
						}
						else
						{
							MONTH_VALUE.add("");
						}
					}
			    }
			}//End of the logic for Transporter
			else if(criteria.trim().equalsIgnoreCase("trader"))
			{
				queryString = "SELECT A.SEQ_NO,UPPER(A.CONTACT_PERSON) " +
							  "FROM FMS7_TRADER_CONTACT_MST A WHERE " +
							  "A.TRADER_CD="+customer_cd+" " +
							  "AND A.EFF_DT=(SELECT MAX(B.EFF_DT) " +
							  "FROM FMS7_TRADER_CONTACT_MST B WHERE " +
							  "A.TRADER_CD=B.TRADER_CD AND A.SEQ_NO=B.SEQ_NO " +
							  "AND B.EFF_DT<=sysdate)" +
							  "ORDER BY UPPER(A.CONTACT_PERSON)";
				////System.out.println("FMS7_TRADER_CONTACT_MST Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);	
				while(rset.next())
				{
					SEQ_NO.add(rset.getString(1)==null?"0":rset.getString(1));
					CONTACT_PERSON.add(rset.getString(2)==null?"":rset.getString(2));
				}
				for(int i=0;i<SEQ_NO.size();i++)
				{
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT CALENDAR_FLAG,GIFT_FLAG,DIARY_FLAG,LEAFLET_FLAG," +
						"OTHER_1,OTHER_2,DESCRIPTION  FROM FMS7_TRADER_GIFT_TRACK_DTL " +
						"WHERE TRADER_CD="+customer_cd+" AND SEQ_NO="+SEQ_NO.elementAt(i)+" AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						//////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							CALENDAR.add(rset.getString(1)==null?"N":rset.getString(1));	
							GIFT.add(rset.getString(2)==null?"N":rset.getString(2));	
							DIARY.add(rset.getString(3)==null?"N":rset.getString(3));
							LEAFLET.add(rset.getString(4)==null?"N":rset.getString(4));	
							OTHER_1.add(rset.getString(5)==null?"N":rset.getString(5));	
							OTHER_2.add(rset.getString(6)==null?"N":rset.getString(6));	
							DESCRIPTION.add(rset.getString(7)==null?"":rset.getString(7));	
						}
						else
						{
							CALENDAR.add("N");	
							GIFT.add("N");	
							DIARY.add("N");
							LEAFLET.add("N");	
							OTHER_1.add("N");	
							OTHER_2.add("N");	
							DESCRIPTION.add("");	
						}
						
						if(Integer.parseInt(""+MONTH.elementAt(j))<10)
						{
							month_loop="0"+Integer.parseInt(""+MONTH.elementAt(j));
						}
						else
						{
							month_loop=""+Integer.parseInt(""+MONTH.elementAt(j));
						}
						
						String mon_yr=""+month_loop+"/"+YEAR.elementAt(j);
						queryString = "SELECT TO_CHAR(TO_DATE('"+mon_yr+"','MM/YYYY'),'MONTH, YYYY') FROM dual";
						////System.out.println("MONTH_VALUE Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							MONTH_VALUE.add(rset.getString(1)==null?"N":rset.getString(1));	
						}
						else
						{
							MONTH_VALUE.add("");
						}
					}
			    }  
			}//End of the logic for Trader
			else if(criteria.trim().equalsIgnoreCase("other"))
			{
				queryString = "SELECT A.CONTACT_TYPE,UPPER(A.CONTACT_PERSON)," +
							  "A.CONTACT_DESIG FROM FMS7_OTHER_CONTACT_MST A " +
							  "WHERE A.OTHER_CD="+customer_cd+" " +
							  "AND A.EFF_DT=(SELECT MAX(B.EFF_DT) " +
							  "FROM FMS7_OTHER_CONTACT_MST B WHERE " +
							  "A.OTHER_CD=B.OTHER_CD AND A.CONTACT_TYPE=B.CONTACT_TYPE " +
							  "AND B.EFF_DT<=sysdate)" +
							  "ORDER BY UPPER(A.CONTACT_PERSON)";
				////System.out.println("FMS7_OTHER_CONTACT_MST Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);	
				while(rset.next())
				{
					SEQ_NO.add(rset.getString(1)==null?"":rset.getString(1));
					CONTACT_PERSON.add(rset.getString(2)==null?"":rset.getString(2));
				}
				
				for(int i=0;i<CONTACT_PERSON.size();i++)
				{
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT CALENDAR_FLAG,GIFT_FLAG,DIARY_FLAG,LEAFLET_FLAG," +
						"OTHER_1,OTHER_2,DESCRIPTION  FROM FMS7_OTHER_GIFT_TRACK_DTL " +
						"WHERE OTHER_CD="+customer_cd+" AND CONTACT_TYPE='"+SEQ_NO.elementAt(i)+"' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						//////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							CALENDAR.add(rset.getString(1)==null?"N":rset.getString(1));	
							GIFT.add(rset.getString(2)==null?"N":rset.getString(2));	
							DIARY.add(rset.getString(3)==null?"N":rset.getString(3));
							LEAFLET.add(rset.getString(4)==null?"N":rset.getString(4));	
							OTHER_1.add(rset.getString(5)==null?"N":rset.getString(5));	
							OTHER_2.add(rset.getString(6)==null?"N":rset.getString(6));	
							DESCRIPTION.add(rset.getString(7)==null?"":rset.getString(7));	
						}
						else
						{
							CALENDAR.add("N");	
							GIFT.add("N");	
							DIARY.add("N");
							LEAFLET.add("N");	
							OTHER_1.add("N");	
							OTHER_2.add("N");	
							DESCRIPTION.add("");	
						}
						
						if(Integer.parseInt(""+MONTH.elementAt(j))<10)
						{
							month_loop="0"+Integer.parseInt(""+MONTH.elementAt(j));
						}
						else
						{
							month_loop=""+Integer.parseInt(""+MONTH.elementAt(j));
						}
						
						String mon_yr=""+month_loop+"/"+YEAR.elementAt(j);
						queryString = "SELECT TO_CHAR(TO_DATE('"+mon_yr+"','MM/YYYY'),'MONTH, YYYY') FROM dual";
						////System.out.println("MONTH_VALUE Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							MONTH_VALUE.add(rset.getString(1)==null?"N":rset.getString(1));	
						}
						else
						{
							MONTH_VALUE.add("");
						}
					}
			    }   
			} //End of the logic for Other
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	
	public void fetchGiftTracking()
	{
		try
		{			
			////System.out.println("CUST_CD.size() = "+CUST_CD.size());
			int from_mon=Integer.parseInt(month);
			int to_mon=Integer.parseInt(to_month);
			int from_yr=Integer.parseInt(year);
			int to_yr=Integer.parseInt(to_year);
//			//System.out.println("Criteria : "+criteria);
			
			Vector MONTH = new Vector();
			Vector YEAR = new Vector();			
			String from_mon_yr=month+"/"+year;
			String to_mon_yr=to_month+"/"+to_year;
			
			queryString = "SELECT MONTHS_BETWEEN(TO_DATE('"+to_mon_yr+"','MM/YYYY'),TO_DATE('"+from_mon_yr+"','MM/YYYY'))+1 FROM dual";
//			//System.out.println("MONTHS_BETWEEN Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);	
			if(rset.next())
			{
				loop_count=rset.getInt(1);
			}
			
			int month_cnt=Integer.parseInt(month);
			int year_cnt=Integer.parseInt(year);			
			String month_loop="";
						
			for(int i=0; i<loop_count; i++)
			{
				if(month_cnt>12)
				{
					month_cnt-=12;
					++year_cnt;					
				}
				
				MONTH.add(""+month_cnt);
				YEAR.add(""+year_cnt);
				++month_cnt;
			}
			
			int cal = 0;
			int gift = 0;
			int diary = 0;
			int leaflet = 0;
			int other_1 = 0;
			int other_2 = 0;
			
			if(criteria.trim().equalsIgnoreCase("customer"))
			{
				for(int i=0;i<CUST_CD.size();i++)
				{	
					cal = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(CALENDAR_FLAG) FROM FMS7_CUSTOMER_GIFT_TRACK_DTL " +
						"WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" AND UPPER(CALENDAR_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							cal += rset.getInt(1);	
						}
					}
					CALENDAR.add(""+cal);
					
					gift = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(GIFT_FLAG) FROM FMS7_CUSTOMER_GIFT_TRACK_DTL " +
						"WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" AND UPPER(GIFT_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							gift += rset.getInt(1);
						}
					}
					GIFT.add(""+gift);
					
					diary = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(DIARY_FLAG ) FROM FMS7_CUSTOMER_GIFT_TRACK_DTL " +
						"WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" AND UPPER(DIARY_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							diary += rset.getInt(1);	
						}
					}
					DIARY.add(""+diary);
					
					leaflet = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(LEAFLET_FLAG) FROM FMS7_CUSTOMER_GIFT_TRACK_DTL " +
						"WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" AND UPPER(LEAFLET_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							leaflet += rset.getInt(1);	
						}
					}
					LEAFLET.add(""+leaflet);
					
					other_1 = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(OTHER_1) FROM FMS7_CUSTOMER_GIFT_TRACK_DTL " +
						"WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" AND UPPER(OTHER_1)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							other_1 += rset.getInt(1);	
						}
					}
					OTHER_1.add(""+other_1);
					
					other_2 = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(OTHER_2) FROM FMS7_CUSTOMER_GIFT_TRACK_DTL " +
						"WHERE CUSTOMER_CD="+CUST_CD.elementAt(i)+" AND UPPER(OTHER_2)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							other_2 += rset.getInt(1);	
						}
					}
					OTHER_2.add(""+other_2);
			    }
			}//End of logic for Customer
			else if(criteria.trim().equalsIgnoreCase("transporter"))
			{
				for(int i=0;i<TRANSPORTER_CD.size();i++)
				{							
					cal = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(CALENDAR_FLAG) FROM FMS7_TRANS_GIFT_TRACK_DTL " +
						"WHERE TRANSPORTER_CD="+TRANSPORTER_CD.elementAt(i)+" AND UPPER(CALENDAR_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							cal += rset.getInt(1);
						}
					}
					CALENDAR.add(""+cal);
					
					gift = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(GIFT_FLAG) FROM FMS7_TRANS_GIFT_TRACK_DTL " +
						"WHERE TRANSPORTER_CD="+TRANSPORTER_CD.elementAt(i)+" AND UPPER(GIFT_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							gift += rset.getInt(1);	
						}
					}
					GIFT.add(""+gift);
					
					diary = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(DIARY_FLAG) FROM FMS7_TRANS_GIFT_TRACK_DTL " +
						"WHERE TRANSPORTER_CD="+TRANSPORTER_CD.elementAt(i)+" AND UPPER(DIARY_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							diary += rset.getInt(1);
						}
					}
					DIARY.add(""+diary);
					
					leaflet = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(LEAFLET_FLAG) FROM FMS7_TRANS_GIFT_TRACK_DTL " +
						"WHERE TRANSPORTER_CD="+TRANSPORTER_CD.elementAt(i)+" AND UPPER(LEAFLET_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							leaflet += rset.getInt(1);	
						}
					}
					LEAFLET.add(""+leaflet);
					
					other_1 = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(OTHER_1) FROM FMS7_TRANS_GIFT_TRACK_DTL " +
						"WHERE TRANSPORTER_CD="+TRANSPORTER_CD.elementAt(i)+" AND UPPER(OTHER_1)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							other_1 += rset.getInt(1);	
						}
					}
					OTHER_1.add(""+other_1);
					
					other_2 = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(OTHER_2) FROM FMS7_TRANS_GIFT_TRACK_DTL " +
						"WHERE TRANSPORTER_CD="+TRANSPORTER_CD.elementAt(i)+" AND UPPER(OTHER_2)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							other_2 += rset.getInt(1);	
						}
					}
					OTHER_2.add(""+other_2);
			    }
			}//End of the logic for Transporter
			else if(criteria.trim().equalsIgnoreCase("trader"))
			{
				for(int i=0;i<TRADER_CD.size();i++)
				{	
					cal = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(CALENDAR_FLAG) FROM FMS7_TRADER_GIFT_TRACK_DTL " +
						"WHERE TRADER_CD="+TRADER_CD.elementAt(i)+" AND UPPER(CALENDAR_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							cal += rset.getInt(1);	
						}
					}
					CALENDAR.add(""+cal);
					
					gift = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(GIFT_FLAG) FROM FMS7_TRADER_GIFT_TRACK_DTL " +
						"WHERE TRADER_CD="+TRADER_CD.elementAt(i)+" AND UPPER(GIFT_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							gift += rset.getInt(1);	
						}
					}
					GIFT.add(""+gift);
					
					diary = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(DIARY_FLAG) FROM FMS7_TRADER_GIFT_TRACK_DTL " +
						"WHERE TRADER_CD="+TRADER_CD.elementAt(i)+" AND UPPER(DIARY_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							diary += rset.getInt(1);	
						}
					}
					DIARY.add(""+diary);
					
					leaflet = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(LEAFLET_FLAG) FROM FMS7_TRADER_GIFT_TRACK_DTL " +
						"WHERE TRADER_CD="+TRADER_CD.elementAt(i)+" AND UPPER(LEAFLET_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							leaflet += rset.getInt(1);	
						}
					}
					LEAFLET.add(""+leaflet);
					
					other_1 = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(OTHER_1) FROM FMS7_TRADER_GIFT_TRACK_DTL " +
						"WHERE TRADER_CD="+TRADER_CD.elementAt(i)+" AND UPPER(OTHER_1)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							other_1 += rset.getInt(1);	
						}
					}
					OTHER_1.add(""+other_1);
					
					other_2 = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(OTHER_2) FROM FMS7_TRADER_GIFT_TRACK_DTL " +
						"WHERE TRADER_CD="+TRADER_CD.elementAt(i)+" AND UPPER(OTHER_2)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							other_2 += rset.getInt(1);	
						}
					}
					OTHER_2.add(""+other_2);
			    }  
			}//End of the logic for Trader
			else if(criteria.trim().equalsIgnoreCase("other"))
			{
				for(int i=0;i<OTHER_CD.size();i++)
				{	
					cal = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(CALENDAR_FLAG) FROM FMS7_OTHER_GIFT_TRACK_DTL  " +
						"WHERE OTHER_CD="+OTHER_CD.elementAt(i)+" AND UPPER(CALENDAR_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							cal += rset.getInt(1);	
						}
					}
					CALENDAR.add(""+cal);
					
					gift = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(GIFT_FLAG) FROM FMS7_OTHER_GIFT_TRACK_DTL " +
						"WHERE OTHER_CD="+OTHER_CD.elementAt(i)+" AND UPPER(GIFT_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							gift += rset.getInt(1);
						}
					}
					GIFT.add(""+gift);
					
					diary = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(DIARY_FLAG) FROM FMS7_OTHER_GIFT_TRACK_DTL " +
						"WHERE OTHER_CD="+OTHER_CD.elementAt(i)+" AND UPPER(DIARY_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							diary += rset.getInt(1);	
						}
					}
					DIARY.add(""+diary);
					
					leaflet = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(LEAFLET_FLAG) FROM FMS7_OTHER_GIFT_TRACK_DTL " +
						"WHERE OTHER_CD="+OTHER_CD.elementAt(i)+" AND UPPER(LEAFLET_FLAG)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							leaflet += rset.getInt(1);	
						}
					}
					LEAFLET.add(""+leaflet);
					
					other_1 = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(OTHER_1) FROM FMS7_OTHER_GIFT_TRACK_DTL " +
						"WHERE OTHER_CD="+OTHER_CD.elementAt(i)+" AND UPPER(OTHER_1)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							other_1 += rset.getInt(1);	
						}
					}
					OTHER_1.add(""+other_1);
					
					other_2 = 0;
					
					for(int j=0;j<MONTH.size();j++)
					{
						queryString = "SELECT COUNT(OTHER_2) FROM FMS7_OTHER_GIFT_TRACK_DTL " +
						"WHERE OTHER_CD="+OTHER_CD.elementAt(i)+" AND UPPER(OTHER_2)='Y' AND " +
						"MONTH="+MONTH.elementAt(j)+" AND YEAR="+YEAR.elementAt(j)+"";
						////System.out.println("Calendar Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);	
						if(rset.next())
						{
							other_2 += rset.getInt(1);
						}
					}
					OTHER_2.add(""+other_2);
			    }   
			}//End of the logic for Other								
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
	
	public void Trader_DTL()
	{
		try
		{
			queryString = "SELECT Distinct TRADER_CD,TRADER_NAME,TRADER_ABBR FROM FMS7_TRADER_MST ORDER BY TRADER_ABBR";
			////System.out.println("FMS7_TRADER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				TRADER_CD.add(rset.getString(1)==null?"0":rset.getString(1));	
				TRADER_NAME.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
			
	public void Transporter_DTL()
	{
		try
		{
			queryString = "SELECT Distinct TRANSPORTER_CD,TRANSPORTER_NAME,TRANSPORTER_ABBR FROM FMS7_TRANSPORTER_MST ORDER BY TRANSPORTER_ABBR";
			////System.out.println("FMS7_TRANSPORTER_MST  Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				TRANSPORTER_CD.add(rset.getString(1)==null?"0":rset.getString(1));	
				TRANSPORTER_NAME.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void Other_DTL()
	{
		try
		{
			queryString = "SELECT Distinct OTHER_CD,OTHER_NAME,OTHER_ABBR FROM FMS7_OTHER_MST ORDER BY OTHER_ABBR";
			////System.out.println("FMS7_OTHER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				OTHER_CD.add(rset.getString(1)==null?"0":rset.getString(1));	
				OTHER_NAME.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	String Customer_access_flag = "N";
	String Emp_cd= "";
	
	public void Customer_DTL()
	{
		try
		{
			if(Customer_access_flag.equals("Y"))
			{
				queryString = "SELECT Distinct A.CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR FROM "
						+ "FMS7_CUSTOMER_MST A, SEC_EMP_CUSTOMER_ALLOC_MST B WHERE "
						+ "A.CUSTOMER_CD= B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ORDER BY CUSTOMER_ABBR";
			} else {
				queryString = "SELECT Distinct CUSTOMER_CD,CUSTOMER_NAME,CUSTOMER_ABBR "
						+ "FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_ABBR";
			}
			
			//////System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"0":rset.getString(1));	
				CUST_NM.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
//	THIS METHOD IS iNTRODUCE BY MILAN DALSANIYA ON 23TH, NOV'2011 
	public void Seller_DTL()
	{
		try
		{
			
			queryString = "SELECT Distinct TRADER_CD,TRADER_NAME,TRADER_ABBR FROM FMS7_TRADER_MST ORDER BY TRADER_ABBR";
			////System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				SELLER_CD.add(rset.getString(1)==null?"0":rset.getString(1));	
				SELLER_NM.add(rset.getString(3)==null?"":rset.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//=====================================================================================================
	//THIS METHOD IS iNTRODUCE BY MILAN DALSANIYA ON 13TH, OCT'2011 
	public void fetchTotalQtySupplyNew()
	{
		try
		{
//			//System.out.println("Last date = fetchTotalQtySupplyNew1");
			Vector tem_GAS_DT= new Vector();
			Vector temp_MMBTU= new Vector();
			Vector temp_SCM= new Vector();			
			Vector temp_CUSTOMER_CD_SUPPLY =new Vector();
			Vector temp_CUSTOMER_CD_SN =new Vector();
			Vector temp_CUSTOMER_CD_LOA =new Vector();
			Vector temp_CUSTOMER_CD_RE_GAS =new Vector();
			double total_QTY_SCM_NUMERIC = 0;
			double total_QTY_MMBTU_NUMERIC = 0;
			
		
			Vector tem_GAS_DT_REGAS= new Vector();
			Vector temp_MMBTU_REGAS= new Vector();
			Vector temp_SCM_REGAS= new Vector();			
			double total_QTY_SCM_NUMERIC_REGAS = 0;
			double total_QTY_MMBTU_NUMERIC_REGAS = 0;
			
			int scm_factor = 252000;
			
			if(Integer.parseInt(month)>0)
			{				
				from_date="01"+"/"+month+"/"+year;	
				queryString ="select TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
				rset = stmt.executeQuery(queryString);
				//////System.out.println("Last date = "+queryString);
				if(rset.next())
				{
					to_date=rset.getString(1)==null?"":rset.getString(1);
				}
				
				//FOR SN AND LOA
				queryString ="select DISTINCT TO_CHAR(A.GAS_DT,'dd-Mon-yyyy'),TO_CHAR(A.GAS_DT,'dd/mm/yyyy'),A.GAS_DT from FMS7_DAILY_ALLOCATION_DTL A " +
				"where A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy') " +
						" AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') ORDER BY A.GAS_DT";					
				//////System.out.println("FMS7_DAILY_ALLOCATION_DTL GAS_DT"+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					GAS_DT.add(rset.getString(1)==null?"":rset.getString(1));
					tem_GAS_DT.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				
				for(int i=0;i<GAS_DT.size();i++)
				{					 
					queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(i)+"', 'dd/mm/yyyy') " +
					" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";					
					//////System.out.println("SUM(QTY_MMBTU) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_MMBTU.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						////System.out.println(TOTAL_QTY_MMBTU);//MD20110916
						total_QTY_MMBTU_NUMERIC += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						temp_MMBTU.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_MMBTU.add("-");
						temp_MMBTU.add("");
					}
					
					queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(i)+"', 'dd/mm/yyyy')" +
					" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";					
					//////System.out.println("SUM(QTY_SCM) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_SCM.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						total_QTY_SCM_NUMERIC += Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						temp_SCM.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_SCM.add("-");
						temp_SCM.add("");
					}					
				}
			
				TOTAL_QTY_MMBTU_NUMERIC = nf6.format(total_QTY_MMBTU_NUMERIC);
				TOTAL_QTY_SCM_NUMERIC = nf3.format(total_QTY_SCM_NUMERIC);
				
								
				for(int i=0;i<TOTAL_QTY_SCM.size();i++)
				{
					if(!(""+temp_SCM.elementAt(i)).trim().equals("") && !(""+temp_MMBTU.elementAt(i)).trim().equals(""))
					{
						GCV.add(nf.format(Double.parseDouble(""+temp_SCM.elementAt(i)) / Double.parseDouble(""+temp_MMBTU.elementAt(i))));
						KCAL_SCM.add(nf3.format( (Double.parseDouble(""+temp_MMBTU.elementAt(i)) * scm_factor) / Double.parseDouble(""+temp_SCM.elementAt(i)) ));
					}
					else
					{
						GCV.add("-");
						KCAL_SCM.add("-");
					}					
				}
				
				//	FOR RE GAS
				queryString ="select DISTINCT TO_CHAR(A.GAS_DT,'dd-Mon-yyyy'),TO_CHAR(A.GAS_DT,'dd/mm/yyyy'),A.GAS_DT from FMS7_DAILY_ALLOCATION_DTL A " +
				"where A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy') " +
						" AND A.CONTRACT_TYPE = 'R' ORDER BY A.GAS_DT";					
				//////System.out.println("FMS7_DAILY_ALLOCATION_DTL GAS_DT"+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					GAS_DT_REGAS.add(rset.getString(1)==null?"":rset.getString(1));
					tem_GAS_DT_REGAS.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				
				for(int i=0;i<GAS_DT_REGAS.size();i++)
				{					
					queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(i)+"', 'dd/mm/yyyy')" +
					" AND CONTRACT_TYPE = 'R' ORDER BY GAS_DT";								
					//////System.out.println("SUM(QTY_MMBTU) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_MMBTU_REGAS.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						////System.out.println(TOTAL_QTY_MMBTU_REGAS);//MD20110916
						total_QTY_MMBTU_NUMERIC_REGAS += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						temp_MMBTU_REGAS.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_MMBTU_REGAS.add("-");
						temp_MMBTU_REGAS.add("");
					}
					
					queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(i)+"', 'dd/mm/yyyy') "+
					" AND CONTRACT_TYPE = 'R' ORDER BY GAS_DT";			
					//////System.out.println("SUM(QTY_SCM) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_SCM_REGAS.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						total_QTY_SCM_NUMERIC_REGAS += Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						temp_SCM_REGAS.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_SCM_REGAS.add("-");
						temp_SCM_REGAS.add("");
					}					
				}
				
				TOTAL_QTY_MMBTU_NUMERIC_REGAS = nf6.format(total_QTY_MMBTU_NUMERIC_REGAS);
				TOTAL_QTY_SCM_NUMERIC_REGAS = nf3.format(total_QTY_SCM_NUMERIC_REGAS);
				
								
				for(int i=0;i<TOTAL_QTY_SCM_REGAS.size();i++)
				{
					if(!(""+temp_SCM_REGAS.elementAt(i)).trim().equals("") && !(""+temp_MMBTU_REGAS.elementAt(i)).trim().equals(""))
					{
						GCV_REGAS.add(nf.format(Double.parseDouble(""+temp_SCM_REGAS.elementAt(i)) / Double.parseDouble(""+temp_MMBTU_REGAS.elementAt(i))));
						KCAL_SCM_REGAS.add(nf3.format( (Double.parseDouble(""+temp_MMBTU_REGAS.elementAt(i)) * scm_factor) / Double.parseDouble(""+temp_SCM_REGAS.elementAt(i)) ));
					}
					else
					{
						GCV_REGAS.add("-");
						KCAL_SCM_REGAS.add("-");
					}					
				}
				
				
				GRAND_TOTAL_MMBTU_NUMERIC = nf6.format(total_QTY_MMBTU_NUMERIC_REGAS+total_QTY_MMBTU_NUMERIC);
				GRAND_TOTAL_SCM_NUMERIC = nf3.format(total_QTY_SCM_NUMERIC_REGAS+total_QTY_SCM_NUMERIC);
				
				
					////////////FOR LTCORA AND CN****************//////////////
				//GLOBAL
				
				
				
				
				//TEMP
				Vector tem_GAS_DT_LTCORA=new Vector();
				double total_QTY_MMBTU_NUMERIC_LTCORA=0;
				double total_QTY_SCM_NUMERIC_LTCORA=0;
				Vector temp_MMBTU_LTCORA=new Vector();
				Vector temp_SCM_LTCORA=new Vector();
				Vector temp_CUSTOMER_CD_LTCORA=new Vector();
				
				Vector qty_mapping_id=new Vector();
				queryString ="select DISTINCT TO_CHAR(A.GAS_DT,'dd-Mon-yyyy'),TO_CHAR(A.GAS_DT,'dd/mm/yyyy'),A.GAS_DT " +
						"from FMS7_DAILY_ALLOCATION_DTL A " +
						"where A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') " +
						"AND to_date('"+to_date+"', 'dd/mm/yyyy') " +
						" AND (A.CONTRACT_TYPE = 'T' OR A.CONTRACT_TYPE = 'C') ORDER BY A.GAS_DT";					
//				//System.out.println("FMS7_DAILY_ALLOCATION_DTL GAS_DT LTCORA"+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					GAS_DT_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));
					tem_GAS_DT_LTCORA.add(rset.getString(2)==null?"":rset.getString(2));	
					
				}
				
				for(int i=0;i<GAS_DT_LTCORA.size();i++)
				{					
					queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(i)+"', 'dd/mm/yyyy')" +
					" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C')  ORDER BY GAS_DT";								
//					//System.out.println("SUM(QTY_MMBTU) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_MMBTU_LTCORA.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						////System.out.println(TOTAL_QTY_MMBTU_REGAS);//MD20110916
						total_QTY_MMBTU_NUMERIC_LTCORA += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						temp_MMBTU_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_MMBTU_LTCORA.add("-");
						temp_MMBTU_LTCORA.add("");
					}
					
					queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(i)+"', 'dd/mm/yyyy') "+
					" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C') ORDER BY GAS_DT";			
					//////System.out.println("SUM(QTY_SCM) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_SCM_LTCORA.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						total_QTY_SCM_NUMERIC_LTCORA += Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						temp_SCM_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_SCM_LTCORA.add("-");
						temp_SCM_LTCORA.add("");
					}					
				}
				
				TOTAL_QTY_MMBTU_NUMERIC_LTCORA = nf6.format(total_QTY_MMBTU_NUMERIC_LTCORA);
				TOTAL_QTY_SCM_NUMERIC_LTCORA = nf3.format(total_QTY_SCM_NUMERIC_LTCORA);
				
								
				for(int i=0;i<TOTAL_QTY_SCM_LTCORA.size();i++)
				{
					if(!(""+temp_SCM_LTCORA.elementAt(i)).trim().equals("") && !(""+temp_MMBTU_LTCORA.elementAt(i)).trim().equals(""))
					{
						GCV_LTCORA.add(nf.format(Double.parseDouble(""+temp_SCM_LTCORA.elementAt(i)) / Double.parseDouble(""+temp_MMBTU_LTCORA.elementAt(i))));
						KCAL_SCM_LTCORA.add(nf3.format( (Double.parseDouble(""+temp_MMBTU_LTCORA.elementAt(i)) * scm_factor) / Double.parseDouble(""+temp_SCM_LTCORA.elementAt(i)) ));
					}
					else
					{
						GCV_LTCORA.add("-");
						KCAL_SCM_LTCORA.add("-");
					}					
				}
				
				
				
				
				GRAND_TOTAL_MMBTU_NUMERIC_LTCORA = nf6.format(total_QTY_MMBTU_NUMERIC_LTCORA+total_QTY_MMBTU_NUMERIC_REGAS+total_QTY_MMBTU_NUMERIC);
				GRAND_TOTAL_SCM_NUMERIC_LTCORA = nf3.format(total_QTY_SCM_NUMERIC_LTCORA+total_QTY_SCM_NUMERIC_REGAS+total_QTY_SCM_NUMERIC);
				
				//////////END OF LTCORA AND CN
				
//				//System.out.println("GRAND_TOTAL_SCM_NUMERIC "+GRAND_TOTAL_SCM_NUMERIC);
				////System.out.println("TOTAL_QTY_MMBTU "+TOTAL_QTY_MMBTU);
				////System.out.println("TOTAL_QTY_SCM "+TOTAL_QTY_SCM);
				////System.out.println("GCV "+GCV);
				////System.out.println("KCAL_SCM "+KCAL_SCM);
				////System.out.println();
				////System.out.println("GAS_DT_REGAS "+GAS_DT_REGAS);
				////System.out.println("TOTAL_QTY_MMBTU_REGAS "+TOTAL_QTY_MMBTU_REGAS);
				////System.out.println("TOTAL_QTY_SCM_REGAS "+TOTAL_QTY_SCM_REGAS);
				////System.out.println("GCV_REGAS "+GCV_REGAS);
				////System.out.println("KCAL_SCM_REGAS "+KCAL_SCM_REGAS);
				
				//RE GAS CUSTOMER DETAIL
//				SN LOA CUSTOMER DETAIL 
				queryString ="select DISTINCT CUSTOMER_CD from FMS7_DAILY_ALLOCATION_DTL  " +
				"where GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')" ;					
//				//System.out.println("FMS7_DAILY_ALLOCATION_DTL RE GAS Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				
				//CUSTOMER WHO ARE LIVE FOR GIVEN MONTH
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS7_SN_MST A where "+			
				"(A.START_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) " +
				"OR (A.END_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy'))";				
//				//System.out.println("SN Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_SN.add(rset.getString(1)==null?"":rset.getString(1));
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
				"(A.START_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) " +
				"OR (A.END_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) ";					
//				//System.out.println("LOA Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_LOA.add(rset.getString(1)==null?"":rset.getString(1));
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_MST A where "+			
				"(A.START_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) " +
				"OR (A.END_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy'))";					
//				//System.out.println("Re-Gas Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				
				//ADDED FOR LTCORA AND CN
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS8_LNG_REGAS_MST A where "+			
				"(A.START_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) " +
				"OR (A.END_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy'))";					
//				//System.out.println("LTCORA AND CN Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				
//				//System.out.println("temp_CUSTOMER_CD_SUPPLY "+temp_CUSTOMER_CD_SUPPLY);
//				//System.out.println("temp_CUSTOMER_CD_SN "+temp_CUSTOMER_CD_SN);
//				//System.out.println("temp_CUSTOMER_CD_LOA "+temp_CUSTOMER_CD_LOA);
//				//System.out.println("temp_CUSTOMER_CD_RE_GAS "+temp_CUSTOMER_CD_RE_GAS);
				////END OF LTCORA AND CN
				
				
				//TO REMOVE DUPLICATE VALUES
				Vector cntm = new Vector();
				for (int i = 0; i<temp_CUSTOMER_CD_SUPPLY.size(); i++)
				{
					
					for (int j = i+1; j<temp_CUSTOMER_CD_SUPPLY.size(); j++)
					{
						if (temp_CUSTOMER_CD_SUPPLY.elementAt(i).toString().trim().equals(temp_CUSTOMER_CD_SUPPLY.elementAt(j).toString().trim()))
						{
							cntm.add(""+j);
						}
					}
					if (!cntm.isEmpty())
					{
						for (int j =0;j<cntm.size();j++)
						{
							temp_CUSTOMER_CD_SUPPLY.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						}
						cntm.clear();
					}
				}
				CUSTOMER_CD.clear();
			
				for (int j = 0; j<temp_CUSTOMER_CD_SUPPLY.size(); j++)
				{
					if (!temp_CUSTOMER_CD_SUPPLY.elementAt(j).toString().trim().equals(""))
					{
						CUSTOMER_CD.add(temp_CUSTOMER_CD_SUPPLY.elementAt(j).toString());
					}
				}
				
//				//System.out.println("CUSTOMER_CD "+CUSTOMER_CD);
				for(int i=0;i<CUSTOMER_CD.size();i++)
				{									
					queryString ="select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
							"where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' ";
					////System.out.println("Customer Name"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));				
					}						
				}
				
				
				CUSTOMER_NM_REGAS = CUSTOMER_NM;
				CUSTOMER_CD_REGAS = CUSTOMER_CD;
				
				CUSTOMER_CD_LTCORA = CUSTOMER_CD;
				CUSTOMER_NM_LTCORA = CUSTOMER_NM;
				//END
				//END
				
				//CUSTOMER_NM_REGAS	CUSTOMER_CD_REGAS	CUSTOMER_NM	CUSTOMER_CD
				//FOR SN AND LOA QTY_MMBTU AND QTY SCM 
				
				
				for(int i=0;i<GAS_DT.size();i++)
				{
					Vector temp_QTY_MMBTU = new Vector();
					Vector temp_QTY_SCM = new Vector();
										
					for(int j=0;j<CUSTOMER_CD.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD.elementAt(j)+"'" +
						" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";			
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_MMBTU.add("-");
						}
						
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD.elementAt(j)+"'" +
						" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_SCM.add("-");
						}
					}
					QTY_MMBTU.add(temp_QTY_MMBTU);
					QTY_SCM.add(temp_QTY_SCM);
					
				}
				
				for(int i=0;i<CUSTOMER_CD.size();i++)
				{
					double temp_QTY_MMBTU_NUMERIC = 0;
					double temp_QTY_SCM_NUMERIC = 0;
					
					for(int j=0;j<GAS_DT.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						"where GAS_DT=to_date('"+tem_GAS_DT.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD.elementAt(i)+"'" +
						" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";						
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_NUMERIC+=Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						}
												
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD.elementAt(i)+"'" +
						" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";							
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_NUMERIC+=Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						}
					}
					//QTY_MMBTU_NUMERIC	QTY_SCM_NUMERIC QTY_MMBTU_NUMERIC_REGAS	 QTY_SCM_NUMERIC_REGAS
					
					QTY_MMBTU_NUMERIC.add(""+temp_QTY_MMBTU_NUMERIC);
					QTY_SCM_NUMERIC.add(""+temp_QTY_SCM_NUMERIC);
				}
				//SN LOA END HERE
				
				//RE GAS START QTY_MMBTU AND QTY_SCM HERE

				
				for(int i=0;i<GAS_DT_REGAS.size();i++)
				{
					Vector temp_QTY_MMBTU_REGAS = new Vector();
					Vector temp_QTY_SCM_REGAS = new Vector();
										
					for(int j=0;j<CUSTOMER_CD_REGAS.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_REGAS.elementAt(j)+"'" +
						" AND CONTRACT_TYPE = 'R'";			
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_REGAS.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_MMBTU_REGAS.add("-");
						}
						
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_REGAS.elementAt(j)+"'" +
						" AND CONTRACT_TYPE = 'R'";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_REGAS.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_SCM_REGAS.add("-");
						}
					}
					QTY_MMBTU_REGAS.add(temp_QTY_MMBTU_REGAS);
					QTY_SCM_REGAS.add(temp_QTY_SCM_REGAS);
				}
				
				for(int i=0;i<CUSTOMER_CD_REGAS.size();i++)
				{
					double temp_QTY_MMBTU_NUMERIC_REGAS = 0;
					double temp_QTY_SCM_NUMERIC_REGAS = 0;
					
					for(int j=0;j<GAS_DT_REGAS.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						"where GAS_DT=to_date('"+tem_GAS_DT_REGAS.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_REGAS.elementAt(i)+"'" +
						" AND CONTRACT_TYPE = 'R'";					
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_NUMERIC_REGAS+=Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						}
												
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_REGAS.elementAt(i)+"'" +
						" AND CONTRACT_TYPE = 'R'";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_NUMERIC_REGAS+=Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						}
					}
					
					QTY_MMBTU_NUMERIC_REGAS.add(""+temp_QTY_MMBTU_NUMERIC_REGAS);
					QTY_SCM_NUMERIC_REGAS.add(""+temp_QTY_SCM_NUMERIC_REGAS);
					
				}
				//RE GAS END HERE
				
				//LTCORA AND CN START QTY_MMBTU AND QTY_SCM HERE //ADDED FOR LTCORA AND CN
				for(int i=0;i<GAS_DT_LTCORA.size();i++)
				{
					Vector temp_QTY_MMBTU_LTCORA = new Vector();
					Vector temp_QTY_SCM_LTCORA = new Vector();
										
					for(int j=0;j<CUSTOMER_CD_LTCORA.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_LTCORA.elementAt(j)+"'" +
						" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C') ";			
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_LTCORA.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_MMBTU_LTCORA.add("-");
						}
						
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_LTCORA.elementAt(j)+"'" +
						" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C')";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_LTCORA.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_SCM_LTCORA.add("-");
						}
					}
					QTY_MMBTU_LTCORA.add(temp_QTY_MMBTU_LTCORA);
					QTY_SCM_LTCORA.add(temp_QTY_SCM_LTCORA);
				}
				
				for(int i=0;i<CUSTOMER_CD_LTCORA.size();i++)
				{
					double temp_QTY_MMBTU_NUMERIC_LTCORA = 0;
					double temp_QTY_SCM_NUMERIC_LTCORA = 0;
					
					for(int j=0;j<GAS_DT_LTCORA.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						"where GAS_DT=to_date('"+tem_GAS_DT_LTCORA.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_LTCORA.elementAt(i)+"'" +
						" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C') ";					
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_NUMERIC_LTCORA+=Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						}
												
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_LTCORA.elementAt(i)+"'" +
						" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C')";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_NUMERIC_LTCORA+=Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						}
					}
					
					QTY_MMBTU_NUMERIC_LTCORA.add(""+temp_QTY_MMBTU_NUMERIC_LTCORA);
					QTY_SCM_NUMERIC_LTCORA.add(""+temp_QTY_SCM_NUMERIC_LTCORA);
					
				}
				/////END OF LTCORA AND CN
			}
		}
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchTotalQtySupplyNew()-->"+e);
			  e.printStackTrace();
		}
	}	
	
	//=====================================================================================================
	public void fetchTotalQtySupplyNew_WITH_ACCESS()
	{
		try
		{
//			//System.out.println("Last date = fetchTotalQtySupplyNew1");
			Vector tem_GAS_DT= new Vector();
			Vector temp_MMBTU= new Vector();
			Vector temp_SCM= new Vector();			
			Vector temp_CUSTOMER_CD_SUPPLY =new Vector();
			Vector temp_CUSTOMER_CD_SN =new Vector();
			Vector temp_CUSTOMER_CD_LOA =new Vector();
			Vector temp_CUSTOMER_CD_RE_GAS =new Vector();
			double total_QTY_SCM_NUMERIC = 0;
			double total_QTY_MMBTU_NUMERIC = 0;
			
		
			Vector tem_GAS_DT_REGAS= new Vector();
			Vector temp_MMBTU_REGAS= new Vector();
			Vector temp_SCM_REGAS= new Vector();			
			double total_QTY_SCM_NUMERIC_REGAS = 0;
			double total_QTY_MMBTU_NUMERIC_REGAS = 0;
			
			int scm_factor = 252000;
			
			if(Integer.parseInt(month)>0)
			{				
				from_date="01"+"/"+month+"/"+year;	
				queryString ="select TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
				rset = stmt.executeQuery(queryString);
				//////System.out.println("Last date = "+queryString);
				if(rset.next())
				{
					to_date=rset.getString(1)==null?"":rset.getString(1);
				}
				
				//FOR SN AND LOA
				queryString ="select DISTINCT TO_CHAR(A.GAS_DT,'dd-Mon-yyyy'),TO_CHAR(A.GAS_DT,'dd/mm/yyyy'),A.GAS_DT "
						+ "from FMS7_DAILY_ALLOCATION_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
				"where A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy') " +
						" AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L')  AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
						+ "B.EMP_CD='"+Emp_cd+"' "
						+ "ORDER BY A.GAS_DT";					
				//////System.out.println("FMS7_DAILY_ALLOCATION_DTL GAS_DT"+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					GAS_DT.add(rset.getString(1)==null?"":rset.getString(1));
					tem_GAS_DT.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				
				for(int i=0;i<GAS_DT.size();i++)
				{					 
					queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
					"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(i)+"', 'dd/mm/yyyy') " +
					" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ";					
					//////System.out.println("SUM(QTY_MMBTU) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_MMBTU.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						////System.out.println(TOTAL_QTY_MMBTU);//MD20110916
						total_QTY_MMBTU_NUMERIC += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						temp_MMBTU.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_MMBTU.add("-");
						temp_MMBTU.add("");
					}
					
					queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
					"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(i)+"', 'dd/mm/yyyy')" +
					" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"'";					
					//////System.out.println("SUM(QTY_SCM) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_SCM.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						total_QTY_SCM_NUMERIC += Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						temp_SCM.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_SCM.add("-");
						temp_SCM.add("");
					}					
				}
			
				TOTAL_QTY_MMBTU_NUMERIC = nf6.format(total_QTY_MMBTU_NUMERIC);
				TOTAL_QTY_SCM_NUMERIC = nf3.format(total_QTY_SCM_NUMERIC);
				
								
				for(int i=0;i<TOTAL_QTY_SCM.size();i++)
				{
					if(!(""+temp_SCM.elementAt(i)).trim().equals("") && !(""+temp_MMBTU.elementAt(i)).trim().equals(""))
					{
						GCV.add(nf.format(Double.parseDouble(""+temp_SCM.elementAt(i)) / Double.parseDouble(""+temp_MMBTU.elementAt(i))));
						KCAL_SCM.add(nf3.format( (Double.parseDouble(""+temp_MMBTU.elementAt(i)) * scm_factor) / Double.parseDouble(""+temp_SCM.elementAt(i)) ));
					}
					else
					{
						GCV.add("-");
						KCAL_SCM.add("-");
					}					
				}
				
				//	FOR RE GAS
				queryString ="select DISTINCT TO_CHAR(A.GAS_DT,'dd-Mon-yyyy'),TO_CHAR(A.GAS_DT,'dd/mm/yyyy'),A.GAS_DT "
						+ "from FMS7_DAILY_ALLOCATION_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
				"where A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy') " +
						" AND A.CONTRACT_TYPE = 'R' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' "
						+ "ORDER BY A.GAS_DT";					
				//////System.out.println("FMS7_DAILY_ALLOCATION_DTL GAS_DT"+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					GAS_DT_REGAS.add(rset.getString(1)==null?"":rset.getString(1));
					tem_GAS_DT_REGAS.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				
				for(int i=0;i<GAS_DT_REGAS.size();i++)
				{					
					queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A , SEC_EMP_CUSTOMER_ALLOC_MST B  " +
					"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(i)+"', 'dd/mm/yyyy')" +
					" AND CONTRACT_TYPE = 'R' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ORDER BY GAS_DT";								
					//////System.out.println("SUM(QTY_MMBTU) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_MMBTU_REGAS.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						////System.out.println(TOTAL_QTY_MMBTU_REGAS);//MD20110916
						total_QTY_MMBTU_NUMERIC_REGAS += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						temp_MMBTU_REGAS.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_MMBTU_REGAS.add("-");
						temp_MMBTU_REGAS.add("");
					}
					
					queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL A,SEC_EMP_CUSTOMER_ALLOC_MST B " +
					"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(i)+"', 'dd/mm/yyyy') "+
					" AND CONTRACT_TYPE = 'R' AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ORDER BY GAS_DT";			
					//////System.out.println("SUM(QTY_SCM) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_SCM_REGAS.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						total_QTY_SCM_NUMERIC_REGAS += Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						temp_SCM_REGAS.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_SCM_REGAS.add("-");
						temp_SCM_REGAS.add("");
					}					
				}
				
				TOTAL_QTY_MMBTU_NUMERIC_REGAS = nf6.format(total_QTY_MMBTU_NUMERIC_REGAS);
				TOTAL_QTY_SCM_NUMERIC_REGAS = nf3.format(total_QTY_SCM_NUMERIC_REGAS);
				
								
				for(int i=0;i<TOTAL_QTY_SCM_REGAS.size();i++)
				{
					if(!(""+temp_SCM_REGAS.elementAt(i)).trim().equals("") && !(""+temp_MMBTU_REGAS.elementAt(i)).trim().equals(""))
					{
						GCV_REGAS.add(nf.format(Double.parseDouble(""+temp_SCM_REGAS.elementAt(i)) / Double.parseDouble(""+temp_MMBTU_REGAS.elementAt(i))));
						KCAL_SCM_REGAS.add(nf3.format( (Double.parseDouble(""+temp_MMBTU_REGAS.elementAt(i)) * scm_factor) / Double.parseDouble(""+temp_SCM_REGAS.elementAt(i)) ));
					}
					else
					{
						GCV_REGAS.add("-");
						KCAL_SCM_REGAS.add("-");
					}					
				}
				
				
				GRAND_TOTAL_MMBTU_NUMERIC = nf6.format(total_QTY_MMBTU_NUMERIC_REGAS+total_QTY_MMBTU_NUMERIC);
				GRAND_TOTAL_SCM_NUMERIC = nf3.format(total_QTY_SCM_NUMERIC_REGAS+total_QTY_SCM_NUMERIC);
				
				
					////////////FOR LTCORA AND CN****************//////////////
				//GLOBAL
				
				//TEMP
				Vector tem_GAS_DT_LTCORA=new Vector();
				double total_QTY_MMBTU_NUMERIC_LTCORA=0;
				double total_QTY_SCM_NUMERIC_LTCORA=0;
				Vector temp_MMBTU_LTCORA=new Vector();
				Vector temp_SCM_LTCORA=new Vector();
				Vector temp_CUSTOMER_CD_LTCORA=new Vector();
				
				Vector qty_mapping_id=new Vector();
				queryString ="select DISTINCT TO_CHAR(A.GAS_DT,'dd-Mon-yyyy'),TO_CHAR(A.GAS_DT,'dd/mm/yyyy'),A.GAS_DT " +
						"from FMS7_DAILY_ALLOCATION_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
						"where A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') " +
						"AND to_date('"+to_date+"', 'dd/mm/yyyy') " +
						" AND (A.CONTRACT_TYPE = 'T' OR A.CONTRACT_TYPE = 'C') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
						+ "B.EMP_CD='"+Emp_cd+"' "
						+ "ORDER BY A.GAS_DT";					
//				//System.out.println("FMS7_DAILY_ALLOCATION_DTL GAS_DT LTCORA"+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					GAS_DT_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));
					tem_GAS_DT_LTCORA.add(rset.getString(2)==null?"":rset.getString(2));	
					
				}
				
				for(int i=0;i<GAS_DT_LTCORA.size();i++)
				{					
					queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
					"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(i)+"', 'dd/mm/yyyy')" +
					" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
					+ "B.EMP_CD='"+Emp_cd+"' ORDER BY GAS_DT";								
//					//System.out.println("SUM(QTY_MMBTU) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_MMBTU_LTCORA.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						////System.out.println(TOTAL_QTY_MMBTU_REGAS);//MD20110916
						total_QTY_MMBTU_NUMERIC_LTCORA += Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						temp_MMBTU_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_MMBTU_LTCORA.add("-");
						temp_MMBTU_LTCORA.add("");
					}
					
					queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL A, SEC_EMP_CUSTOMER_ALLOC_MST B " +
					"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(i)+"', 'dd/mm/yyyy') "+
					" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C') AND A.CUSTOMER_CD=B.CUSTOMER_CD AND "
					+ "B.EMP_CD='"+Emp_cd+"' "
					+ " ORDER BY GAS_DT";			
					//////System.out.println("SUM(QTY_SCM) "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						TOTAL_QTY_SCM_LTCORA.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						total_QTY_SCM_NUMERIC_LTCORA += Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						temp_SCM_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));
					}
					else
					{
						TOTAL_QTY_SCM_LTCORA.add("-");
						temp_SCM_LTCORA.add("");
					}					
				}
				
				TOTAL_QTY_MMBTU_NUMERIC_LTCORA = nf6.format(total_QTY_MMBTU_NUMERIC_LTCORA);
				TOTAL_QTY_SCM_NUMERIC_LTCORA = nf3.format(total_QTY_SCM_NUMERIC_LTCORA);
				
								
				for(int i=0;i<TOTAL_QTY_SCM_LTCORA.size();i++)
				{
					if(!(""+temp_SCM_LTCORA.elementAt(i)).trim().equals("") && !(""+temp_MMBTU_LTCORA.elementAt(i)).trim().equals(""))
					{
						GCV_LTCORA.add(nf.format(Double.parseDouble(""+temp_SCM_LTCORA.elementAt(i)) / Double.parseDouble(""+temp_MMBTU_LTCORA.elementAt(i))));
						KCAL_SCM_LTCORA.add(nf3.format( (Double.parseDouble(""+temp_MMBTU_LTCORA.elementAt(i)) * scm_factor) / Double.parseDouble(""+temp_SCM_LTCORA.elementAt(i)) ));
					}
					else
					{
						GCV_LTCORA.add("-");
						KCAL_SCM_LTCORA.add("-");
					}					
				}
				
				GRAND_TOTAL_MMBTU_NUMERIC_LTCORA = nf6.format(total_QTY_MMBTU_NUMERIC_LTCORA+total_QTY_MMBTU_NUMERIC_REGAS+total_QTY_MMBTU_NUMERIC);
				GRAND_TOTAL_SCM_NUMERIC_LTCORA = nf3.format(total_QTY_SCM_NUMERIC_LTCORA+total_QTY_SCM_NUMERIC_REGAS+total_QTY_SCM_NUMERIC);
				
				//////////END OF LTCORA AND CN
				
				//RE GAS CUSTOMER DETAIL
//				SN LOA CUSTOMER DETAIL 
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS7_DAILY_ALLOCATION_DTL A,SEC_EMP_CUSTOMER_ALLOC_MST B " +
						"where GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND "
						+ "A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' " ;					
//				//System.out.println("FMS7_DAILY_ALLOCATION_DTL RE GAS Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				
				//CUSTOMER WHO ARE LIVE FOR GIVEN MONTH
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS7_SN_MST A,SEC_EMP_CUSTOMER_ALLOC_MST B where "+			
				"(A.START_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) " +
				"OR (A.END_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) "
						+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ";				
//				//System.out.println("SN Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_SN.add(rset.getString(1)==null?"":rset.getString(1));
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A,SEC_EMP_CUSTOMER_ALLOC_MST B where "+			
				"(A.START_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) " +
				"OR (A.END_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) "
						+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ";					
//				//System.out.println("LOA Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_LOA.add(rset.getString(1)==null?"":rset.getString(1));
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_MST A,SEC_EMP_CUSTOMER_ALLOC_MST B where "+			
				"(A.START_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) " +
				"OR (A.END_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) "
						+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ";					
//				//System.out.println("Re-Gas Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				
				//ADDED FOR LTCORA AND CN
				queryString ="select DISTINCT A.CUSTOMER_CD from FMS8_LNG_REGAS_MST A,SEC_EMP_CUSTOMER_ALLOC_MST B where "+			
				"(A.START_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) " +
				"OR (A.END_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"', 'dd/mm/yyyy')) "
						+ "AND A.CUSTOMER_CD=B.CUSTOMER_CD AND B.EMP_CD='"+Emp_cd+"' ";					
//				//System.out.println("LTCORA AND CN Customer Code "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					temp_CUSTOMER_CD_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));
					temp_CUSTOMER_CD_SUPPLY.add(rset.getString(1)==null?"":rset.getString(1));
				}
				
				////END OF LTCORA AND CN
				//TO REMOVE DUPLICATE VALUES
				Vector cntm = new Vector();
				for (int i = 0; i<temp_CUSTOMER_CD_SUPPLY.size(); i++)
				{
					
					for (int j = i+1; j<temp_CUSTOMER_CD_SUPPLY.size(); j++)
					{
						if (temp_CUSTOMER_CD_SUPPLY.elementAt(i).toString().trim().equals(temp_CUSTOMER_CD_SUPPLY.elementAt(j).toString().trim()))
						{
							cntm.add(""+j);
						}
					}
					if (!cntm.isEmpty())
					{
						for (int j =0;j<cntm.size();j++)
						{
							temp_CUSTOMER_CD_SUPPLY.setElementAt("",Integer.parseInt(cntm.elementAt(j).toString()));
						}
						cntm.clear();
					}
				}
				CUSTOMER_CD.clear();
			
				for (int j = 0; j<temp_CUSTOMER_CD_SUPPLY.size(); j++)
				{
					if (!temp_CUSTOMER_CD_SUPPLY.elementAt(j).toString().trim().equals(""))
					{
						CUSTOMER_CD.add(temp_CUSTOMER_CD_SUPPLY.elementAt(j).toString());
					}
				}
				
//				//System.out.println("CUSTOMER_CD "+CUSTOMER_CD);
				for(int i=0;i<CUSTOMER_CD.size();i++)
				{									
					queryString ="select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
							"where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' ";
					////System.out.println("Customer Name"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));				
					}						
				}
				
				
				CUSTOMER_NM_REGAS = CUSTOMER_NM;
				CUSTOMER_CD_REGAS = CUSTOMER_CD;
				
				CUSTOMER_CD_LTCORA = CUSTOMER_CD;
				CUSTOMER_NM_LTCORA = CUSTOMER_NM;
				//END
				//END
				
				//CUSTOMER_NM_REGAS	CUSTOMER_CD_REGAS	CUSTOMER_NM	CUSTOMER_CD
				//FOR SN AND LOA QTY_MMBTU AND QTY SCM 
				
				
				for(int i=0;i<GAS_DT.size();i++)
				{
					Vector temp_QTY_MMBTU = new Vector();
					Vector temp_QTY_SCM = new Vector();
										
					for(int j=0;j<CUSTOMER_CD.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD.elementAt(j)+"'" +
						" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";			
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_MMBTU.add("-");
						}
						
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD.elementAt(j)+"'" +
						" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_SCM.add("-");
						}
					}
					QTY_MMBTU.add(temp_QTY_MMBTU);
					QTY_SCM.add(temp_QTY_SCM);
					
				}
				
				for(int i=0;i<CUSTOMER_CD.size();i++)
				{
					double temp_QTY_MMBTU_NUMERIC = 0;
					double temp_QTY_SCM_NUMERIC = 0;
					
					for(int j=0;j<GAS_DT.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						"where GAS_DT=to_date('"+tem_GAS_DT.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD.elementAt(i)+"'" +
						" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";						
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_NUMERIC+=Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						}
												
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD.elementAt(i)+"'" +
						" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";							
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_NUMERIC+=Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						}
					}
					//QTY_MMBTU_NUMERIC	QTY_SCM_NUMERIC QTY_MMBTU_NUMERIC_REGAS	 QTY_SCM_NUMERIC_REGAS
					
					QTY_MMBTU_NUMERIC.add(""+temp_QTY_MMBTU_NUMERIC);
					QTY_SCM_NUMERIC.add(""+temp_QTY_SCM_NUMERIC);
				}
				//SN LOA END HERE
				
				//RE GAS START QTY_MMBTU AND QTY_SCM HERE

				
				for(int i=0;i<GAS_DT_REGAS.size();i++)
				{
					Vector temp_QTY_MMBTU_REGAS = new Vector();
					Vector temp_QTY_SCM_REGAS = new Vector();
										
					for(int j=0;j<CUSTOMER_CD_REGAS.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_REGAS.elementAt(j)+"'" +
						" AND CONTRACT_TYPE = 'R'";			
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_REGAS.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_MMBTU_REGAS.add("-");
						}
						
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_REGAS.elementAt(j)+"'" +
						" AND CONTRACT_TYPE = 'R'";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_REGAS.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_SCM_REGAS.add("-");
						}
					}
					QTY_MMBTU_REGAS.add(temp_QTY_MMBTU_REGAS);
					QTY_SCM_REGAS.add(temp_QTY_SCM_REGAS);
				}
				
				for(int i=0;i<CUSTOMER_CD_REGAS.size();i++)
				{
					double temp_QTY_MMBTU_NUMERIC_REGAS = 0;
					double temp_QTY_SCM_NUMERIC_REGAS = 0;
					
					for(int j=0;j<GAS_DT_REGAS.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						"where GAS_DT=to_date('"+tem_GAS_DT_REGAS.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_REGAS.elementAt(i)+"'" +
						" AND CONTRACT_TYPE = 'R'";					
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_NUMERIC_REGAS+=Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						}
												
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_REGAS.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_REGAS.elementAt(i)+"'" +
						" AND CONTRACT_TYPE = 'R'";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_NUMERIC_REGAS+=Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						}
					}
					
					QTY_MMBTU_NUMERIC_REGAS.add(""+temp_QTY_MMBTU_NUMERIC_REGAS);
					QTY_SCM_NUMERIC_REGAS.add(""+temp_QTY_SCM_NUMERIC_REGAS);
					
				}
				//RE GAS END HERE
				
				//LTCORA AND CN START QTY_MMBTU AND QTY_SCM HERE //ADDED FOR LTCORA AND CN
				for(int i=0;i<GAS_DT_LTCORA.size();i++)
				{
					Vector temp_QTY_MMBTU_LTCORA = new Vector();
					Vector temp_QTY_SCM_LTCORA = new Vector();
										
					for(int j=0;j<CUSTOMER_CD_LTCORA.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_LTCORA.elementAt(j)+"'" +
						" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C') ";			
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_LTCORA.add(rset.getString(1)==null?"-":nf6.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_MMBTU_LTCORA.add("-");
						}
						
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(i)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_LTCORA.elementAt(j)+"'" +
						" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C')";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_LTCORA.add(rset.getString(1)==null?"-":nf3.format(Double.parseDouble(rset.getString(1))));
						}
						else
						{
							temp_QTY_SCM_LTCORA.add("-");
						}
					}
					QTY_MMBTU_LTCORA.add(temp_QTY_MMBTU_LTCORA);
					QTY_SCM_LTCORA.add(temp_QTY_SCM_LTCORA);
				}
				
				for(int i=0;i<CUSTOMER_CD_LTCORA.size();i++)
				{
					double temp_QTY_MMBTU_NUMERIC_LTCORA = 0;
					double temp_QTY_SCM_NUMERIC_LTCORA = 0;
					
					for(int j=0;j<GAS_DT_LTCORA.size();j++)
					{
						queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						"where GAS_DT=to_date('"+tem_GAS_DT_LTCORA.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_LTCORA.elementAt(i)+"'" +
						" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C') ";					
						//////System.out.println("SUM(QTY_MMBTU) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_MMBTU_NUMERIC_LTCORA+=Double.parseDouble(rset.getString(1)==null?"0":nf.format(Double.parseDouble(rset.getString(1))));
						}
												
						queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
						"where GAS_DT = to_date('"+tem_GAS_DT_LTCORA.elementAt(j)+"','dd/mm/yyyy') " +
						"AND CUSTOMER_CD = '"+CUSTOMER_CD_LTCORA.elementAt(i)+"'" +
						" AND (CONTRACT_TYPE = 'T' OR CONTRACT_TYPE = 'C')";						
						//////System.out.println("SUM(QTY_SCM) CUSTOMER WISE : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							temp_QTY_SCM_NUMERIC_LTCORA+=Double.parseDouble(rset.getString(1)==null?"0":nf1.format(Double.parseDouble(rset.getString(1))));
						}
					}
					
					QTY_MMBTU_NUMERIC_LTCORA.add(""+temp_QTY_MMBTU_NUMERIC_LTCORA);
					QTY_SCM_NUMERIC_LTCORA.add(""+temp_QTY_SCM_NUMERIC_LTCORA);
					
				}
				/////END OF LTCORA AND CN
			}
		}
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchTotalQtySupplyNew()-->"+e);
			  e.printStackTrace();
		}
	}	
	
		

	
	
public void fetchSendOut() 
	{
		try
		{
			Vector temp_CUSTOMER_CD_SN = new Vector();
			Vector temp_CUSTOMER_CD_LOA = new Vector();
			Vector temp_CUSTOMER_CD = new Vector();
			
			double net_uncommited_total_volume = 0;
			double net_uncommited_total_mcm_volume = 0;
//12/08/2010								
			String first_day_month = "01/"+from_date.substring(3);//01/08/2010
			String before_15day_month = "";//01/08/2010
			String end_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))+1);//12/08/2011
			
			String expected_volume_end_date = "";
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')-15,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					before_15day_month = rset.getString(1);
				}
			}
			////System.out.println("before_15day_month = "+before_15day_month);
			
			////System.out.println("expected_volume_end_date = "+expected_volume_end_date);
			
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+first_day_month+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				selected_month_year = rset.getString(1);
			}
			////System.out.println("selected_month_year = "+selected_month_year);
			int mnth = Integer.parseInt(from_date.substring(3,5));
			String month = "";
			String year = "";
			if(mnth==1)
			{
				mnth = 12;
				month = "12";
				year = ""+(Integer.parseInt(from_date.substring(6))-1);
			}
			else
			{
				year = from_date.substring(6);
				mnth = mnth-1;
				if(mnth<10)
				{
					month = "0"+mnth;
				}
				else
				{
					month = ""+mnth;
				}
			}
			
			String previous_month_start_date = "01/"+month+"/"+year;
			double conversion_factor_mcm = 38900;
			
			double vol_exp_sales = 0;
			double vol_exp_sales1 = 0;
			double vol_exp_regas = 0;
			double vol_exp_total = 0;
			
			double vol_exp_LTCORA = 0; //ADDED FOR LTCORA AND CN
			double vol_recv_LTCORA = 0;
			double opening_stock_LTCORA = 0;
			double int_consumption_LTCORA = 0;
			double mon_LTCORA = 0;
			
			double opening_stock_for_ltcora_calc=0;
			
			double vol_recv_sales = 0;
			double vol_recv_regas = 0;
			double vol_recv_total = 0;
			
			double opening_stock_sales = 0;
			double opening_stock_regas = 0;
			double opening_stock = 0;
			
			double qty_to_be_supplied = 0;
			
			double int_consumption_sales = 0;
			double int_consumption_regas = 0;
			double int_consumption_total = 0;
			consumption_percentage = 2.0;
			
			double dead_stk = 700200;
			
			double month_total = 0;
			double mon_sales = 0;
			double mon_regas = 0;			
			
			////GLOBAL
			
			
			
			
			
			//Following Logic is Updated By Milan Dalsaniya 20111011
			if(expected_volume_end_date.length()>=10)
			{
				//queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
				//			  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
				//			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
				
//				queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
//				  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
				queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
				  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
			
				////System.out.println("Query for FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vol_exp_sales = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					////System.out.println("Jaimin::"+vol_exp_sales);
					//volume_expected_sales = nf3.format(vol_exp_sales);
					//volume_expected_sales_mcm = nf.format(vol_exp_sales/conversion_factor_mcm);
				}
//				queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
//				  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy') ) AND CARGO_STATUS='T' AND UNIT_CD='2'";
				queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
				  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy') ) AND CARGO_STATUS='T' AND UNIT_CD='2'";
			
				////System.out.println("Query for FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vol_exp_sales1 = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))*1000000));
					////System.out.println("Jaimin::"+vol_exp_sales1);
					
				}
				
				volume_expected_sales = nf3.format(vol_exp_sales+vol_exp_sales1);
				volume_expected_sales_mcm = nf.format((vol_exp_sales+vol_exp_sales1)/conversion_factor_mcm);
				
//				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
//							  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//							  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')";
				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
				  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')";

				////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					/*vol_exp_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_expected_regas = nf3.format(vol_exp_regas);
					volume_expected_regas_mcm = nf.format(vol_exp_regas/conversion_factor_mcm);*/
					vol_exp_regas = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
					volume_expected_regas = nf3.format(vol_exp_regas);
					volume_expected_regas_mcm = nf.format(vol_exp_regas/conversion_factor_mcm);
				}
				
				
				////ADDED FOR LTCORA AND CN
				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS8_LNG_REGAS_CARGO_DTL A " +
				  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')";

				////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					/*vol_exp_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_expected_regas = nf3.format(vol_exp_regas);
					volume_expected_regas_mcm = nf.format(vol_exp_regas/conversion_factor_mcm);*/
					vol_exp_LTCORA = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
					volume_expected_LTCORA = nf3.format(vol_exp_LTCORA);
					volume_expected_LTCORA_mcm = nf.format(vol_exp_LTCORA/conversion_factor_mcm);
				}
				//END
				
				vol_exp_total = vol_exp_sales+vol_exp_sales1 + vol_exp_regas+vol_exp_LTCORA;//ADDED FOR LTCORA AND CN
				volume_expected_total = nf3.format(vol_exp_total);
				volume_expected_total_mcm = nf.format(vol_exp_total/conversion_factor_mcm);
			}
			
			if(from_date.length()>=10)
			{
				queryString = "SELECT SUM(B.QTY_MMBTU) FROM FMS7_CARGO_ARRIVAL_DTL A, FMS7_CARGO_QQ_DTL B " +
							  "WHERE A.CARGO_REF_NO=B.CARGO_REF_NO AND A.SPLIT_SEQ=B.SPLIT_SEQ AND A.SPLIT_SEQ='0' AND " +
							  "(A.ACT_ARRV_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
							  "TO_DATE('"+from_date+"','DD/MM/YYYY'))";
				////System.out.println("Query for FMS7_CARGO_QQ_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					vol_recv_sales = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_received_sales = nf3.format(vol_recv_sales);
					volume_received_sales_mcm = nf.format(vol_recv_sales/conversion_factor_mcm);
				}
				
//				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
//							  "WHERE A.ACTUAL_RECPT_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
//							  "TO_DATE('"+from_date+"','DD/MM/YYYY')";
//JHP20120717			
				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
				  "WHERE A.ACTUAL_RECPT_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+from_date+"','DD/MM/YYYY')";
				////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					/*vol_recv_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_received_regas =" nf3.format(vol_recv_regas);
					volume_received_regas_mcm = nf.format(vol_recv_regas/conversion_factor_mcm);*/
					vol_recv_regas = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
					volume_received_regas = nf3.format(vol_recv_regas);
					volume_received_regas_mcm = nf.format(vol_recv_regas/conversion_factor_mcm);
				}
				
				//ADDED FOR LTCORA AND CN
				queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS8_LNG_REGAS_CARGO_DTL A " +
				  "WHERE A.ACTUAL_RECPT_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+from_date+"','DD/MM/YYYY')";
				////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					/*vol_recv_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					volume_received_regas =" nf3.format(vol_recv_regas);
					volume_received_regas_mcm = nf.format(vol_recv_regas/conversion_factor_mcm);*/
					vol_recv_LTCORA = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
					volume_received_LTCORA = nf3.format(vol_recv_LTCORA);
					volume_received_LTCORA_mcm = nf.format(vol_recv_LTCORA/conversion_factor_mcm);
				}
				///END
				
				
				vol_recv_total = vol_recv_sales + vol_recv_regas+vol_recv_LTCORA;//ADDED FOR LTCORA AND CN
				volume_received_total = nf3.format(vol_recv_total);
				volume_received_total_mcm = nf.format(vol_recv_total/conversion_factor_mcm);
								
				
				Vector fgsa_NO = new Vector();
				Vector sn_NO = new Vector();
				Vector cust_CD = new Vector();
				int counter = 0;
				
				
				queryString2 = "SELECT A.PERCENTAGE " +
							   "FROM FMS7_CONSUMPTION_MST A WHERE " +
							   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
							   "B.EFF_DT<=TO_DATE('"+first_day_month+"','DD/MM/YYYY'))";
				
				////System.out.println("Internal Consumption Percentage Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					String int_consumption = rset2.getString(1)==null?"":rset2.getString(1);
					if(!int_consumption.trim().equals(""))
					{
						consumption_percentage = Double.parseDouble(int_consumption);
					}
				}
				
				consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
				
				queryString = "SELECT energy_mmbtu FROM FMS7_INVENTORY_OPENING_BALANCE " +
							  "WHERE opening_dt=TO_DATE('"+first_day_month+"','dd/mm/yyyy')";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					month_opening_stock = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
					opening_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
					month_opening_stock_mcm = nf.format(opening_stock/conversion_factor_mcm);
//JHP20120829		int_consumption_total = ((opening_stock +vol_exp_total)*consumption_percentage)/100.00;
					int_consumption_total = ((opening_stock - dead_stk +vol_exp_total)*consumption_percentage)/100.00;

					internal_consumption_total = nf3.format(int_consumption_total);
					internal_consumption_total_mcm = nf.format(int_consumption_total/conversion_factor_mcm);
				}
				
				queryString = "SELECT RE_GAS_NO, CARGO_SEQ_NO, CUSTOMER_CD, QTY_TO_BE_SUPPLY " +
							  "FROM FMS7_RE_GAS_CARGO_DTL " +
							  "WHERE TO_DATE('"+first_day_month+"','DD/MM/YYYY') " +
							  "BETWEEN CONTRACT_START_DT AND CONTRACT_END_DT";
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					fgsa_NO.add(rset.getString(1)==null?"":rset.getString(1));
					sn_NO.add(rset.getString(2)==null?"":rset.getString(2));
					cust_CD.add(rset.getString(3)==null?"":rset.getString(3));
					qty_to_be_supplied += rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4))));
					++counter;
				}
				
				if(counter>0)
				{
					for(int i=0; i<counter; i++)
					{
						queryString = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   			  "where CONTRACT_TYPE='R' AND " +
						   			  "GAS_DT<TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						   			  "FGSA_NO='"+fgsa_NO.elementAt(i)+"' AND " +
						   			  "SN_NO='"+sn_NO.elementAt(i)+"' AND CUSTOMER_CD='"+cust_CD.elementAt(i)+"'";
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							qty_to_be_supplied -= rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1))));
						}
					}
					
					month_opening_stock_regas = nf3.format(qty_to_be_supplied);
					opening_stock_regas = Double.parseDouble(nf5.format(qty_to_be_supplied));
					month_opening_stock_regas_mcm = nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm);
				}
				
				if(qty_to_be_supplied!=0)
				{
					month_opening_stock_sales = nf3.format(opening_stock - qty_to_be_supplied);
					opening_stock_for_ltcora_calc=opening_stock - qty_to_be_supplied;//ADDED FOR LTCORA AND CN
					
					opening_stock_sales = Double.parseDouble(nf5.format(opening_stock - qty_to_be_supplied));
					month_opening_stock_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock/conversion_factor_mcm)) - Double.parseDouble(nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm)));
					
//JHP20120829	    int_consumption_sales = (((opening_stock - qty_to_be_supplied)+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
					int_consumption_sales = (((opening_stock - qty_to_be_supplied - dead_stk)+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;

					internal_consumption_sales = nf3.format(int_consumption_sales);
					internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
					
					int_consumption_regas = ((qty_to_be_supplied+vol_exp_regas)*consumption_percentage)/100.00;
					internal_consumption_regas = nf3.format(int_consumption_regas);
					internal_consumption_regas_mcm = nf.format(int_consumption_regas/conversion_factor_mcm);
				}
				else
				{
					month_opening_stock_sales = month_opening_stock;
					opening_stock_for_ltcora_calc=opening_stock;//ADDED FOR LTCORA AND CN
					
					opening_stock_sales = opening_stock;
					month_opening_stock_sales_mcm = month_opening_stock_mcm;
					
//JHP20120829		int_consumption_sales = ((opening_stock+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
					internal_consumption_sales = nf3.format(int_consumption_sales);
					internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
					
					int_consumption_regas = (vol_exp_regas*consumption_percentage)/100.00;
					internal_consumption_regas = nf3.format(int_consumption_regas);
					internal_consumption_regas_mcm = nf.format(int_consumption_regas/conversion_factor_mcm);
				}
				
				///ADDED FOR LTCORA AND CN
				Vector temp_mapping_id=new Vector();
				Vector temp_flag=new Vector();
				sn_NO.clear();
				cust_CD.clear();
				qty_to_be_supplied=0;
				counter=0;
				queryString = "SELECT MAPPING_ID, CARGO_SEQ_NO, FLAG, QTY_TO_BE_SUPPLY " +
				  "FROM FMS8_LNG_REGAS_CARGO_DTL " +
				  "WHERE TO_DATE('"+first_day_month+"','DD/MM/YYYY') " +
				  "BETWEEN CONTRACT_START_DT AND CONTRACT_END_DT";
				////System.out.println("++ queryString "+queryString);
					rset = stmt.executeQuery(queryString);
					while(rset.next())
					{
						temp_mapping_id.add(rset.getString(1)==null?"":rset.getString(1));
						String temp_mapid=rset.getString(1)==null?"":rset.getString(1);
						String tempid[]=temp_mapid.split("-");
						sn_NO.add(rset.getString(2)==null?"":rset.getString(2));
						temp_flag.add(rset.getString(3)==null?"":rset.getString(3));
						cust_CD.add(tempid[0]);
						qty_to_be_supplied += rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4))));
						++counter;
					}
					////System.out.println("temp_flag   "+qty_to_be_supplied+" counter  "+sn_NO);
					if(counter>0)
					{
						for(int i=0; i<counter; i++)
						{
							queryString = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			  "where CONTRACT_TYPE='"+temp_flag.elementAt(i)+"' AND " +
							   			  "GAS_DT<TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
							   			  "MAPPING_ID='"+temp_mapping_id.elementAt(i)+"' AND " +
							   			  "SN_NO='"+sn_NO.elementAt(i)+"' AND CUSTOMER_CD='"+cust_CD.elementAt(i)+"'";
							////System.out.println("--- queryString "+queryString);
							rset = stmt.executeQuery(queryString);
							if(rset.next())
							{
								qty_to_be_supplied -= rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1))));
							}
						}
						
						month_opening_stock_LTCORA = nf3.format(qty_to_be_supplied);
						opening_stock_LTCORA = Double.parseDouble(nf5.format(qty_to_be_supplied));
						month_opening_stock_LTCORA_mcm = nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm);
					}
					
					////System.out.println("qty_to_be_supplied  "+qty_to_be_supplied+" vol_exp_LTCORA "+vol_exp_LTCORA+" consumption_percentage "+consumption_percentage);
					////System.out.println((qty_to_be_supplied+vol_exp_LTCORA)*consumption_percentage);
					
					
					if(qty_to_be_supplied!=0)
					{
						month_opening_stock_sales = nf3.format(opening_stock_for_ltcora_calc - qty_to_be_supplied);
						opening_stock_sales = Double.parseDouble(nf5.format(opening_stock_for_ltcora_calc - qty_to_be_supplied));
						month_opening_stock_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_for_ltcora_calc/conversion_factor_mcm)) - Double.parseDouble(nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm)));
						
//	JHP20120829	    int_consumption_sales = (((opening_stock - qty_to_be_supplied)+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
						int_consumption_sales = (((opening_stock_for_ltcora_calc - qty_to_be_supplied - dead_stk)+vol_exp_sales+vol_exp_LTCORA+vol_exp_sales1)*consumption_percentage)/100.00;

						internal_consumption_sales = nf3.format(int_consumption_sales);
						internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
						
						int_consumption_LTCORA = ((qty_to_be_supplied+vol_exp_LTCORA)*consumption_percentage)/100.00;
						internal_consumption_LTCORA = nf3.format(int_consumption_LTCORA);
						internal_consumption_LTCORA_mcm = nf.format(int_consumption_LTCORA/conversion_factor_mcm);
					}
					else
					{
						month_opening_stock_sales = ""+opening_stock_for_ltcora_calc;
						opening_stock_sales = opening_stock_for_ltcora_calc;
						month_opening_stock_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_for_ltcora_calc/conversion_factor_mcm)));
						
//	JHP20120829		int_consumption_sales = ((opening_stock+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
						internal_consumption_sales = nf3.format(int_consumption_sales);
						internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
						
						int_consumption_LTCORA = (vol_exp_LTCORA*consumption_percentage)/100.00;
						internal_consumption_LTCORA = nf3.format(int_consumption_LTCORA);
						internal_consumption_LTCORA_mcm = nf.format(int_consumption_LTCORA/conversion_factor_mcm);
					}
					////System.out.println("internal_consumption_LTCORA  "+internal_consumption_LTCORA);
				/////END 
					
					
				queryString = "SELECT TANK1_D1_VOLUME, TANK2_D1_VOLUME, TANK_DTL_DT " +
							  "FROM FMS7_TANK_MASTER_DTL " +
							  "WHERE TANK_DTL_DT<TO_DATE('"+first_day_month+"','DD/MM/YYYY') " +
							  "ORDER BY TANK_DTL_DT DESC";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					dead_stk = rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1))));
					dead_stk += rset.getString(2)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(2))));
					dead_stk = 700200;				
				}
				dead_stock = nf3.format(dead_stk);
				dead_stock_mcm = nf.format(dead_stk/conversion_factor_mcm);
								
				
				
				String dd=from_date.substring(0,2);
				if(Integer.parseInt(dd)<=15)
				{
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
				}
				else
				{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";	
				}
				////System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					selected_prev_month_year = rset.getString(2);
//				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
//							   "where CONTRACT_TYPE='R' AND " +
//							   "GAS_DT<=TO_DATE('"+from_date.trim()+"','DD/MM/YYYY') AND " +
//							   "GAS_DT>=TO_DATE('"+first_day_month+"','DD/MM/YYYY')";
					if(Integer.parseInt(dd)<=15)
					{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CONTRACT_TYPE='R' AND " +
								   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
								   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
					}
					else
					{
						queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where CONTRACT_TYPE='R' AND " +
						   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
						   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
			
					}
				////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					month_regas = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
					mon_regas = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					month_regas_mcm = nf.format(mon_regas/conversion_factor_mcm);
					month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				}
				
				
//				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
//							   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
//							   "GAS_DT<=TO_DATE('"+from_date.trim()+"','DD/MM/YYYY') AND " +
//							   "GAS_DT>=TO_DATE('"+first_day_month+"','DD/MM/YYYY')";
				if(Integer.parseInt(dd)<=15)
				{
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
							   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
							   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
				}
				else
				{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
					   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
					   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
					   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
		
				}
				////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					month_sales = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
					mon_sales = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					month_sales_mcm = nf.format(mon_sales/conversion_factor_mcm);
					month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				}					
				
				///ADDED FOR LTCORA AND CN
				if(Integer.parseInt(dd)<=15)
				{
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
							   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
							   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
				}
				else
				{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
					   "where (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
					   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
					   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
		
				}
				////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					month_LTCORA = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
					mon_LTCORA = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					month_LTCORA_mcm = nf.format(mon_sales/conversion_factor_mcm);
					month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				}					
				///END
				
				month_total_sales = nf3.format(month_total);
				month_total_sales_mcm = nf.format(month_total/conversion_factor_mcm);
				}
			}
			
			if(previous_month_start_date.length()>=10)
			{
				String dd=from_date.substring(0,2);
				
				if(Integer.parseInt(dd)<=15)
				{
				queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
				}
				else
				{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";	
				}
				////System.out.println(queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					selected_prev_month_year = rset.getString(2);
					double prev_month_total = 0;
					double pre_mon_sales = 0;
					double pre_mon_regas = 0;
					double pre_mon_LTCORA= 0;
					if(Integer.parseInt(dd)<=15)
					{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CONTRACT_TYPE='R' AND " +
								   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
								   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
					}
					else
					{
						queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where CONTRACT_TYPE='R' AND " +
						   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
						   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
			
					}
					//////System.out.println("QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						prev_month_regas = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
						pre_mon_regas = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						prev_month_regas_mcm = nf.format(pre_mon_regas/conversion_factor_mcm);
						prev_month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					}
					
					if(Integer.parseInt(dd)<=15)
					{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
								   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
								   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
					}
					else
					{
						queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
						   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
						   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
			
					}
					//////System.out.println("QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						prev_month_sales = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
						pre_mon_sales = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						prev_month_sales_mcm = nf.format(pre_mon_sales/conversion_factor_mcm);
						prev_month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					}	
					
					
					//ADDED FOR LTCORA AND CN
					if(Integer.parseInt(dd)<=15)
					{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
								   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
								   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
					}
					else
					{
						queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
						   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
						   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
			
					}
					//////System.out.println("QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						prev_month_LTCORA = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
						pre_mon_LTCORA = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						prev_month_LTCORA_mcm = nf.format(pre_mon_LTCORA/conversion_factor_mcm);
						prev_month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					}
					/////END
					
					
					prev_month_total_sales = nf3.format(prev_month_total);
					prev_month_total_sales_mcm = nf.format(prev_month_total/conversion_factor_mcm);
				}
			}
			
			
			//Following Coding Is Related to Re-Gas Contracts ...
			double outstanding_commit_regas = 0;
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "(A.CONTRACT_START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.CONTRACT_END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy'))  " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.CONTRACT_START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.CONTRACT_START_DT) AND " +
						  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
			
			//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
						  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
			queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
			  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
			////System.out.println(">>>>Re-Gas Customer Code"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));				
			}
			
			//All Customer's Data Involved In LIVE Re-Gas Contracts depends upon Selected Period ...
			for(int i=0;i<CUSTOMER_CD_RE_GAS.size();i++)
			{				
				Vector tmp_RE_GAS_NO = new Vector();
				Vector tmp_CAPACITY = new Vector();
				Vector tmp_RE_GAS_CARGO_NO = new Vector();
				Vector tmp_INNER_CUSTOMER_CD_RE_GAS = new Vector();
				Vector tmp_QTY_MMBTU = new Vector();
				Vector tmp_QTY_BALANCE = new Vector();
				Vector tmp_REMARK_RE_GAS = new Vector();
				String tmp_RE_GAS_CLOSURE_DT = "";
				
				double total_balance = 0;
				
				queryString = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
							  "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
				////System.out.println(">>>>Customer for ReGas Name"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CUSTOMER_NM_RE_GAS.add(rset.getString(2)==null?"":rset.getString(2));				
				}
				else
				{
					CUSTOMER_NM_RE_GAS.add("");
				}
				
				int count = 0;
				
				//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
				/*queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
							  "((A.CONTRACT_START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (A.CONTRACT_END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy'))  " +
							  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.CONTRACT_START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.CONTRACT_START_DT)) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
							  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
				
				//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
				
				//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
				/*queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD, " +
							  "NVL(A.REGAS_CLOSURE_FLAG, 'N') from FMS7_RE_GAS_CARGO_DTL A where "+			
							  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
							  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
							  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
							  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
							  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

				//FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
				queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD, " +
				  "NVL(A.REGAS_CLOSURE_FLAG, 'N'),TO_CHAR(A.REGAS_CLOSURE_DT,'DD/MM/YYYY') from FMS7_RE_GAS_CARGO_DTL A where "+			
				  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
				  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
				  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
				  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
				  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
				////System.out.println(">>>>Re-Gas Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				//BALANCE_RE_GAS	QTY_MMBTU_RE_GAS	CAPACITY
				while(rset.next())
				{
					tmp_RE_GAS_NO.add(rset.getString(1)==null?"":rset.getString(1));
					tmp_CAPACITY.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					tmp_RE_GAS_CARGO_NO.add(rset.getString(3)==null?"":rset.getString(3));
					tmp_INNER_CUSTOMER_CD_RE_GAS.add(rset.getString(4)==null?"0":rset.getString(4));
					tmp_RE_GAS_CLOSURE_DT = rset.getString(6)==null?"0":rset.getString(6);
					
					double balance = 0;
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(3)+"' AND FGSA_NO='"+rset.getString(1)+"' " +
								   "AND CONTRACT_TYPE='R' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
					////System.out.println(">>>>QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					//	Introduce By Milan Dalsaniya 20111012					
					if(rset1.next())
					{
						tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						String tmp = "";
						if (!tmp_RE_GAS_CLOSURE_DT.equals("0")){
							////System.out.println("from if of dual >>>>>>> ");
							queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_RE_GAS_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
							////System.out.println(">>>>Dual = "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if (rset2.next())
							{
								tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
								////System.out.println("Count >>>>>>>> "+tmp);
							}
						}
						
						if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							balance = balance*-1;
							tmp_REMARK_RE_GAS.add("Over supplied by "+nf3.format(balance)+" Mmbtus");
							balance = 0;
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
						{
							tmp_REMARK_RE_GAS.add("Short closed by "+nf3.format(balance)+" Mmbtus");	
							tmp_QTY_BALANCE.add("-");
							balance = 0;
							//tmp_REMARK_RE_GAS.add("");
						}
						else
						{
							if (balance < 0 )
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK_RE_GAS.add("");
								balance = 0;
							}
							else if (balance == 0 )
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK_RE_GAS.add("");
								balance = 0;
							}
							else
							{
								tmp_QTY_BALANCE.add(""+nf3.format(balance));
								tmp_REMARK_RE_GAS.add("");
							}
						}
					}
					else
					{
						tmp_REMARK_RE_GAS.add("");
						tmp_QTY_MMBTU.add("");
						balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						tmp_QTY_BALANCE.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					}
					total_balance += balance;
					++count;
				}
				
				if(count==0)
				{
					tmp_RE_GAS_NO.add("");
					tmp_REMARK_RE_GAS.add("");
					tmp_CAPACITY.add("");
					tmp_RE_GAS_CARGO_NO.add("");
					tmp_QTY_MMBTU.add("");
					tmp_QTY_BALANCE.add("");
					tmp_INNER_CUSTOMER_CD_RE_GAS.add(""+CUSTOMER_CD_RE_GAS.elementAt(i));
				}
				
				RE_GAS_NO.add(tmp_RE_GAS_NO);				
				CAPACITY.add(tmp_CAPACITY);
				REMARK_RE_GAS_NO.add(tmp_REMARK_RE_GAS);
				RE_GAS_CARGO_NO.add(tmp_RE_GAS_CARGO_NO);
				INNER_CUSTOMER_CD_RE_GAS.add(tmp_INNER_CUSTOMER_CD_RE_GAS);
				QTY_MMBTU_RE_GAS.add(tmp_QTY_MMBTU);
				BALANCE_RE_GAS.add(tmp_QTY_BALANCE);
				RE_GAS_OUTER_COUNTER.add(""+count);
				RE_GAS_TOTAL_BALANCE.add(nf3.format(total_balance));
				outstanding_commit_regas += Double.parseDouble(nf5.format(total_balance));
			}
			outstanding_commitment_regas = nf3.format(outstanding_commit_regas);
			outstanding_commitment_regas_mcm = nf.format(outstanding_commit_regas/conversion_factor_mcm);
			
			
			////////////////////ADDED FOR LTCORA AND CN
			
//			Following Coding Is Related to LTCORA Contracts ...
			double outstanding_commit_LTCORA = 0;
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "(A.CONTRACT_START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.CONTRACT_END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy'))  " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.CONTRACT_START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.CONTRACT_START_DT) AND " +
						  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
			
			//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
						  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
			  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			queryString="select DISTINCT(C.CUSTOMER_CD) from FMS8_LNG_REGAS_CARGO_DTL A, " +
					"FMS8_LNG_REGAS_MST C where ((to_date('"+before_15day_month+"','dd/mm/yyyy') between  " +
					"A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN  " +
					"TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
					"AND A.MAPPING_ID=C.MAPPING_ID  AND " +
					"C.cn_rev_no=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B where C.agreement_no=B.agreement_no " +
					"AND C.customer_cd=B.customer_cd  AND B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND " +
					"B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE  D.agreement_no=B.agreement_no AND " +
					"B.customer_cd=D.customer_cd  AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) " +
					"AND a.mapping_id=C.mapping_id order by C.CUSTOMER_CD ";
			
			////System.out.println(">>>>LTCORA AND CN Customer Code"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				CUSTOMER_CD_LTCORA.add(rset.getString(1)==null?"":rset.getString(1));				
			}
			
			//All Customer's Data Involved In LIVE Re-Gas Contracts depends upon Selected Period ...
			for(int i=0;i<CUSTOMER_CD_LTCORA.size();i++)
			{				
				Vector tmp_RE_GAS_NO = new Vector();
				Vector tmp_CAPACITY = new Vector();
				Vector tmp_RE_GAS_CARGO_NO = new Vector();
				Vector tmp_INNER_CUSTOMER_CD_RE_GAS = new Vector();
				Vector tmp_QTY_MMBTU = new Vector();
				Vector tmp_QTY_BALANCE = new Vector();
				Vector tmp_REMARK_RE_GAS = new Vector();
				String tmp_RE_GAS_CLOSURE_DT = "";
				Vector temp_mapping_id=new Vector();
				Vector temp_ltcora_cont_type=new Vector();
				
				double total_balance = 0;
				
				queryString = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
							  "where CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
				////System.out.println(">>>>Customer for ReGas Name"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CUSTOMER_NM_LTCORA.add(rset.getString(2)==null?"":rset.getString(2));				
				}
				else
				{
					CUSTOMER_NM_LTCORA.add("");
				}
				
				int count = 0;
				
				//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
				/*queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
							  "((A.CONTRACT_START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (A.CONTRACT_END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy'))  " +
							  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.CONTRACT_START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.CONTRACT_START_DT)) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
							  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
				
				//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
				
				//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
				/*queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD, " +
							  "NVL(A.REGAS_CLOSURE_FLAG, 'N') from FMS7_RE_GAS_CARGO_DTL A where "+			
							  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
							  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
							  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
							  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
							  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

				//FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
				/*queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD, " +
				  "NVL(A.REGAS_CLOSURE_FLAG, 'N'),TO_CHAR(A.REGAS_CLOSURE_DT,'DD/MM/YYYY') from FMS7_RE_GAS_CARGO_DTL A where "+			
				  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
				  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
				  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
				  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
				  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
				
				queryString="select A.MAPPING_ID,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.FLAG, " +
				"NVL(A.REGAS_CLOSURE_FLAG, 'N'),TO_CHAR(A.REGAS_CLOSURE_DT,'DD/MM/YYYY'), " +
				"A.REGAS_TARIF ,A.cargo_ref_no ,C.CN_NO from FMS8_LNG_REGAS_CARGO_DTL A, " +
				"FMS8_LNG_REGAS_MST C where ((to_date('"+before_15day_month+"','dd/mm/yyyy') between  " +
				"A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN  " +
				"TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				"AND A.MAPPING_ID=C.MAPPING_ID  AND C.CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' AND " +
				"C.cn_rev_no=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B where C.agreement_no=B.agreement_no " +
				"AND C.customer_cd=B.customer_cd  AND B.CN_NO=C.CN_NO AND c.rev_no=B.rev_no AND " +
				"B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE  D.agreement_no=B.agreement_no AND " +
				"B.customer_cd=D.customer_cd  AND D.agreement_no=C.agreement_no AND D.customer_cd=C.customer_cd )) " +
				"AND a.mapping_id=C.mapping_id order by C.CUSTOMER_CD ";
				
				////System.out.println(">>>>LTCORA N CN Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				//BALANCE_RE_GAS	QTY_MMBTU_RE_GAS	CAPACITY
				while(rset.next())
				{
					temp_mapping_id.add(rset.getString(1)==null?"":rset.getString(1));
					String temp_map_id=rset.getString(1)==null?"":rset.getString(1);
					String map_id[]=temp_map_id.split("-");
					String tempconttype=rset.getString(4)==null?"T":rset.getString(4);
					String temp_re_gas_no_ltcora="";
					
					if(tempconttype.equalsIgnoreCase("T"))
					{
						tmp_RE_GAS_NO.add(map_id[1]);
						temp_re_gas_no_ltcora=map_id[1];
						temp_ltcora_cont_type.add("LTCORA");
					}
					else if(tempconttype.equalsIgnoreCase("C"))
					{
						tmp_RE_GAS_NO.add(map_id[3]);
						temp_re_gas_no_ltcora=map_id[3];
						temp_ltcora_cont_type.add("CN");
					}
							
					//tmp_RE_GAS_NO.add(rset.getString(1)==null?"":rset.getString(1));
					tmp_CAPACITY.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					tmp_RE_GAS_CARGO_NO.add(rset.getString(3)==null?"":rset.getString(3));
					tmp_INNER_CUSTOMER_CD_RE_GAS.add(map_id[0]);
					tmp_RE_GAS_CLOSURE_DT = rset.getString(6)==null?"0":rset.getString(6);
					
					////System.out.println("tmp_RE_GAS_NO "+tmp_RE_GAS_NO+" tempconttype "+tempconttype);
					double balance = 0;
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(3)+"' AND FGSA_NO='"+temp_re_gas_no_ltcora+"' " +
								   "AND CONTRACT_TYPE='"+tempconttype+"' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
								   "AND MAPPING_ID='"+temp_map_id+"'";
					
					////System.out.println(">>>>QTY_MMBTU ltcora = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					//	Introduce By Milan Dalsaniya 20111012					
					if(rset1.next())
					{
						tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						String tmp = "";
						if (!tmp_RE_GAS_CLOSURE_DT.equals("0")){
							////System.out.println("from if of dual >>>>>>> ");
							queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_RE_GAS_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
							////System.out.println(">>>>Dual = "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if (rset2.next())
							{
								tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
								////System.out.println("Count >>>>>>>> "+tmp);
							}
						}
						
						if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							balance = balance*-1;
							tmp_REMARK_RE_GAS.add("Over supplied by "+nf3.format(balance)+" Mmbtus");
							balance = 0;
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
						{
							tmp_REMARK_RE_GAS.add("Short closed by "+nf3.format(balance)+" Mmbtus");	
							tmp_QTY_BALANCE.add("-");
							balance = 0;
							//tmp_REMARK_RE_GAS.add("");
						}
						else
						{
							if (balance < 0 )
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK_RE_GAS.add("");
								balance = 0;
							}
							else if (balance == 0 )
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK_RE_GAS.add("");
								balance = 0;
							}
							else
							{
								tmp_QTY_BALANCE.add(""+nf3.format(balance));
								tmp_REMARK_RE_GAS.add("");
							}
						}
					}
					else
					{
						tmp_REMARK_RE_GAS.add("");
						tmp_QTY_MMBTU.add("");
						balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						tmp_QTY_BALANCE.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
					}
					total_balance += balance;
					++count;
				}
				
				if(count==0)
				{
					tmp_RE_GAS_NO.add("");
					tmp_REMARK_RE_GAS.add("");
					tmp_CAPACITY.add("");
					tmp_RE_GAS_CARGO_NO.add("");
					tmp_QTY_MMBTU.add("");
					tmp_QTY_BALANCE.add("");
					tmp_INNER_CUSTOMER_CD_RE_GAS.add(""+CUSTOMER_CD_RE_GAS.elementAt(i));
				}
				
				LTCORA_NO.add(tmp_RE_GAS_NO);	
				LTCORA_CONT_TYPE.add(temp_ltcora_cont_type);
				LTCORA_CAPACITY.add(tmp_CAPACITY);
				LTCORA_REMARK_RE_GAS_NO.add(tmp_REMARK_RE_GAS);
				LTCORA_CARGO_NO.add(tmp_RE_GAS_CARGO_NO);
				INNER_CUSTOMER_CD_LTCORA.add(tmp_INNER_CUSTOMER_CD_RE_GAS);
				QTY_MMBTU_LTCORA.add(tmp_QTY_MMBTU);
				BALANCE_LTCORA.add(tmp_QTY_BALANCE);
				LTCORA_OUTER_COUNTER.add(""+count);
				LTCORA_TOTAL_BALANCE.add(nf3.format(total_balance));
				outstanding_commit_LTCORA += Double.parseDouble(nf5.format(total_balance));
			}
			outstanding_commitment_LTCORA = nf3.format(outstanding_commit_LTCORA);
			outstanding_commitment_LTCORA_mcm = nf.format(outstanding_commit_LTCORA/conversion_factor_mcm);
			
			
			
			////////////////////END
			
			
			
			//Following Coding Is Related to SN/LOA Contracts ...
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_SN_MST A where "+			
						  "(A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT) AND " +
						  "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND " +
						  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...

			//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...

			/*queryString =  "select DISTINCT(A.CUSTOMER_CD) from FMS7_SN_MST A where " +
						   "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
						   "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
						   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
						   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
						   "ORDER BY A.CUSTOMER_CD";*/
//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...		
			
			queryString =  "select DISTINCT(A.CUSTOMER_CD) from FMS7_SN_MST A where " +
			   "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
			   "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
			   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
			   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
			   "ORDER BY A.CUSTOMER_CD";
			////System.out.println("SN Customer Code = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				temp_CUSTOMER_CD_SN.add(rset.getString(1)==null?"":rset.getString(1));				
			}
			
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
						  "(A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT) AND " +
						  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
						  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
			
//			FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
						  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
						  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...

			queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
			  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
			  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
			
			////System.out.println("LOA Customer Code = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				temp_CUSTOMER_CD_LOA.add(rset.getString(1)==null?"":rset.getString(1));
			}
			
			for(int i=0;i<temp_CUSTOMER_CD_SN.size();i++)
			{	
				for(int j=0;j<temp_CUSTOMER_CD_LOA.size();j++)
				{
					if((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD_LOA.elementAt(j)).trim()))
					{
						temp_CUSTOMER_CD.add((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim());
						break;
					}
				}
			}
			
			for(int i=0;i<temp_CUSTOMER_CD_SN.size();i++)
			{
				int inner_count = 0;
				for(int j=0;j<temp_CUSTOMER_CD.size();j++)
				{
					if((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD.elementAt(j)).trim()))
					{
						++inner_count;
						break;
					}					
				}
				
				if(inner_count==0)
				{
					CUSTOMER_CD.add((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim());
				}
			}
			
			for(int i=0;i<temp_CUSTOMER_CD_LOA.size();i++)
			{
				int inner_count = 0;
				for(int j=0;j<temp_CUSTOMER_CD.size();j++)
				{
					if((""+temp_CUSTOMER_CD_LOA.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD.elementAt(j)).trim()))
					{
						++inner_count;
						break;
					}					
				}
				
				if(inner_count==0)
				{
					CUSTOMER_CD.add((""+temp_CUSTOMER_CD_LOA.elementAt(i)).trim());
				}
			}
						
			for(int j=0;j<temp_CUSTOMER_CD.size();j++)
			{
				CUSTOMER_CD.add((""+temp_CUSTOMER_CD.elementAt(j)).trim());
			}
			
			//All Customer's Data Involved In LIVE SN or LOA depends upon Selected Period ...
			double outstanding_commit_sales = 0;
			double re_commit_sales = 0;
						
			for(int i=0; i<CUSTOMER_CD.size(); i++)
			{				
				Vector tmp_SN_NO = new Vector();
				Vector tmp_SN_REF_NO = new Vector();
				Vector tmp_FGSA_NO = new Vector();
				Vector tmp_TCQ = new Vector();
				Vector tmp_INNER_CUSTOMER_CD_SN_LOA = new Vector();
				Vector tmp_CONT_TYPE = new Vector();
				Vector tmp_QTY_MMBTU = new Vector();
				Vector tmp_QTY_BALANCE = new Vector();
				Vector tmp_REMARK = new Vector();
				String tmp_SN_CLOSURE_DT = "";
				
				queryString = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
							  "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
				//////System.out.println("Customer Name"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));				
				}
				else
				{
					CUSTOMER_NM.add("");
				}
				
				int cnt=0;
				double total_balance = 0;
				
				//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
				/*queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD,NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO from FMS7_SN_MST A where "+			
							  "((A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT)) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
							  "where A.SN_NO=B.SN_NO AND " +
							  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
				
				//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...

//				FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
				/*queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD," +
							  "NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO,A.sn_ref_no from FMS7_SN_MST A where "+			
							  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
							  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
							  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
							  "where A.SN_NO=B.SN_NO AND " +
							  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

//				FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
				queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD," +
				  "NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO,A.sn_ref_no,TO_CHAR(A.SN_CLOSURE_DT,'DD/MM/YYYY') from FMS7_SN_MST A where "+			
				  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
				  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
				  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
				  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
				  "where A.SN_NO=B.SN_NO AND " +
				  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
				  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
				////System.out.println("SN Name = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					++cnt;
					tmp_SN_NO.add(rset.getString(1)==null?"":rset.getString(1));
					tmp_FGSA_NO.add(rset.getString(2)==null?"":rset.getString(2));
					tmp_TCQ.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					tmp_INNER_CUSTOMER_CD_SN_LOA.add(rset.getString(4)==null?"0":rset.getString(4));
					tmp_CONT_TYPE.add("S");
					tmp_SN_REF_NO.add(rset.getString(7)==null?"":rset.getString(7));
					tmp_SN_CLOSURE_DT = rset.getString(8)==null?"0":rset.getString(8);
					int re_count = 0;
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_SN_LD_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					//////System.out.println("FMS7_SN_LD_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_SN_MAKEUPGAS_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					//////System.out.println("FMS7_SN_MAKEUPGAS_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_SN_TOP_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					//////System.out.println("FMS7_SN_TOP_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
		
					double balance = 0; 
						
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND FGSA_NO='"+rset.getString(2)+"' " +
								   "AND CONTRACT_TYPE='S' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
					//////System.out.println("QTY_MMBTU = "+queryString1); 
					rset1 = stmt1.executeQuery(queryString1);
//					FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
					if(rset1.next())
					{
						tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
												
						balance = (Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						
						String tmp = "";
						if (!tmp_SN_CLOSURE_DT.equals("0")){
							////System.out.println("from if of dual of SN >>>>>>> ");
							queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_SN_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
							////System.out.println(">>>>Dual SN = "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if (rset2.next())
							{
								tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
								////System.out.println("Count SN>>>>>>>> "+tmp);
								////System.out.println("balance SN>>>>>>>> "+balance);
							}
						}
						if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Short closed by "+nf3.format(balance)+" Mmbtus");
							balance = 0;
						}
						else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Over supplied by "+nf3.format(balance*(-1))+" Mmbtus");
							balance = 0;
						}
						else
						{
							//tmp_QTY_BALANCE.add(nf3.format(Double.parseDouble(rset.getString(3)==null?"0":nf.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf.format(Double.parseDouble(rset1.getString(1))))));
							if (balance<=0)
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK.add("");
								balance = 0;
							}
							else if (balance==0)
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK.add("");
								balance = 0;
							}
							else
							{
								tmp_QTY_BALANCE.add(nf3.format(balance));
								tmp_REMARK.add("");
							}
						}						
					}
					else
					{
						tmp_QTY_MMBTU.add("");
						balance = Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))));
						tmp_QTY_BALANCE.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
						tmp_REMARK.add("");
					}
					
					total_balance += balance;
					
					if(re_count>0)
					{
						outstanding_commit_sales += balance;
					}
					else
					{
						re_commit_sales += balance;
					}
				}
				
				
				double balance = 0;
				
				//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
				/*queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
							  "((A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
							  "OR (to_date('"+from_date+"', 'dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT)) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
							  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
				
//				FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			
				/*queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD, " +
							  "A.loa_ref_no from FMS7_LOA_MST A where "+			
							  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
							  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
							  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
							  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
							  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
//				FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
				queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD, " +
				  "A.loa_ref_no, A.LOA_CLOSURE_FLAG ,TO_CHAR(A.LOA_CLOSURE_DT,'DD/MM/YYYY') from FMS7_LOA_MST A where "+			
				  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
				  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
				  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
				  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
				  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
				  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
				  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
				
				////System.out.println("LOA NAME = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					++cnt;
					tmp_SN_NO.add(rset.getString(1)==null?"":rset.getString(1));
					tmp_FGSA_NO.add(rset.getString(2)==null?"":rset.getString(2));
					tmp_TCQ.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					tmp_INNER_CUSTOMER_CD_SN_LOA.add(rset.getString(4)==null?"0":rset.getString(4));
					tmp_CONT_TYPE.add("L");
					tmp_SN_REF_NO.add(rset.getString(5)==null?"":rset.getString(5));
					tmp_SN_CLOSURE_DT = rset.getString(7)==null?"0":rset.getString(7);
					String tmp_SN_CLOSURE_FLG = rset.getString(6)==null?"0":rset.getString(6);
					//////System.out.println("LOA REF NO : "+rset.getString(5)==null?"":rset.getString(5));
					////System.out.println("LOA REF NO : "+rset.getString(5));
					int re_count = 0;
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_LD_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND LOA_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					//////System.out.println("FMS7_LOA_LD_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_MAKEUPGAS_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND LOA_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					//////System.out.println("FMS7_LOA_MAKEUPGAS_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_TOP_DTL " +
								   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND LOA_NO='"+rset.getString(1)+"' AND " +
								   "FGSA_NO='"+rset.getString(2)+"'";
					//////System.out.println("FMS7_LOA_TOP_DTL Query = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						re_count += rset1.getInt(1);
					}
					
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
								   "AND SN_NO='"+rset.getString(1)+"' AND FGSA_NO='"+rset.getString(2)+"' " +
								   "AND CONTRACT_TYPE='L' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
					//////System.out.println("QTY_MMBTU = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
//					FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
					if(rset1.next())
					{
						tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						
						balance = (Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
						
						String tmp = "";
						if (!tmp_SN_CLOSURE_DT.equals("0")){
							////System.out.println("from if of dual of LOA >>>>>>> ");
							queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_SN_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
							////System.out.println(">>>>Dual LOA= "+queryString1);
							rset2 = stmt2.executeQuery(queryString1);
							if (rset2.next())
							{
								tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
								////System.out.println("Count >>>>>>>> LOA "+tmp);
								////System.out.println("balance >>>>>>>> LOA "+balance);
							}
						}
						if(tmp_SN_CLOSURE_FLG.trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Short closed by "+nf3.format(balance)+" Mmbtus");
							balance = 0;
						}
						else if(tmp_SN_CLOSURE_FLG.trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("Over supplied by "+nf3.format(balance*(-1))+" Mmbtus");
							balance = 0;
						}
						else
						{
							//tmp_QTY_BALANCE.add(nf3.format(Double.parseDouble(rset.getString(3)==null?"0":nf.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf.format(Double.parseDouble(rset1.getString(1))))));
							if (balance<=0)
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK.add("");
								balance = 0;
							}
							else if (balance==0)
							{
								tmp_QTY_BALANCE.add("-");
								tmp_REMARK.add("");
								balance = 0;
							}
							else
							{
								tmp_QTY_BALANCE.add(nf3.format(balance));
								tmp_REMARK.add("");
							}
						}					
						/*
						if(balance<=0)
						{
							tmp_QTY_BALANCE.add("-");
							balance = 0;
						}
						else
						{
							tmp_QTY_BALANCE.add(nf3.format(balance));
						}*/
					}
					else
					{
						tmp_QTY_MMBTU.add("");
						balance = Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))));
						tmp_QTY_BALANCE.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					}
					
					tmp_REMARK.add("");
					total_balance += balance;
					
					if(re_count>0)
					{
						outstanding_commit_sales += balance;
					}
					else
					{
						re_commit_sales += balance;
					}
				}
				
				if(cnt==0)
				{
					tmp_SN_NO.add("");
					tmp_SN_REF_NO.add("");
					tmp_FGSA_NO.add("");
					tmp_TCQ.add("");
					tmp_INNER_CUSTOMER_CD_SN_LOA.add(""+CUSTOMER_CD.elementAt(i));
					tmp_CONT_TYPE.add("");
					tmp_QTY_MMBTU.add("");
					tmp_QTY_BALANCE.add("");
					tmp_REMARK.add("");
				}
				
				SN_NO.add(tmp_SN_NO);
				SN_REF_NO.add(tmp_SN_REF_NO);
				FGSA_NO.add(tmp_FGSA_NO);
				TCQ.add(tmp_TCQ);
				INNER_CUSTOMER_CD_SN_LOA.add(tmp_INNER_CUSTOMER_CD_SN_LOA);
				CONT_TYPE.add(tmp_CONT_TYPE);
				QTY_MMBTU.add(tmp_QTY_MMBTU);
				BALANCE.add(tmp_QTY_BALANCE);
				SN_LOA_OUTER_COUNTER.add(""+cnt);
				SEND_OUT_REMARK.add(tmp_REMARK);
				SN_LOA_TOTAL_BALANCE.add(nf3.format(total_balance));
			}
			////System.out.println("SN REF NO : "+SN_REF_NO);
			outstanding_commitment_sales = nf3.format(outstanding_commit_sales);
			outstanding_commitment_sales_mcm = nf.format(outstanding_commit_sales/conversion_factor_mcm);
			
			//ADDED FOR LTCORA AND CN
			outstanding_commitment_total = nf3.format(outstanding_commit_LTCORA+outstanding_commit_regas+outstanding_commit_sales);
			outstanding_commitment_total_mcm = nf.format((outstanding_commit_LTCORA/conversion_factor_mcm)+(outstanding_commit_regas/conversion_factor_mcm)+(outstanding_commit_sales/conversion_factor_mcm));
			
			re_commitment_sales = nf3.format(re_commit_sales);
			re_commitment_sales_mcm = nf.format(re_commit_sales/conversion_factor_mcm);
			internal_consumption_re_commitment_sales = nf3.format(re_commit_sales*consumption_percentage/100);
			internal_consumption_re_commitment_sales_mcm = nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100);
			
			//double net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
			//double net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
			
			double net_uncommit_sale =0;
			double net_uncommit_sale_mcm =0;
			
			String dd=from_date.substring(0,2);
			
			if(Integer.parseInt(dd)<=15)
			{
				net_uncommited_sales = nf3.format(Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales)));
				net_uncommited_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
				
				net_uncommited_regas = nf3.format(Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas)));
				net_uncommited_regas_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm));
			
				net_uncommited_LTCORA = nf3.format(Double.parseDouble(nf5.format(opening_stock_LTCORA))+Double.parseDouble(nf5.format(vol_exp_LTCORA))-Double.parseDouble(nf5.format(outstanding_commit_LTCORA))-Double.parseDouble(nf5.format(int_consumption_LTCORA)));
				net_uncommited_LTCORA_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_LTCORA/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_LTCORA))+Double.parseDouble(nf5.format(vol_exp_LTCORA))-Double.parseDouble(nf5.format(outstanding_commit_LTCORA))-Double.parseDouble(nf5.format(int_consumption_LTCORA));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_LTCORA/conversion_factor_mcm));
			
				
				 net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
				 net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
				
			}
			else
			{
				net_uncommited_sales = nf3.format(Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales)));
				net_uncommited_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
				
				net_uncommited_regas = nf3.format(Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_recv_regas))-Double.parseDouble(nf5.format(mon_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas)));
				net_uncommited_regas_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_recv_regas))-Double.parseDouble(nf5.format(mon_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm));
				
				net_uncommited_LTCORA = nf3.format(Double.parseDouble(nf5.format(opening_stock_LTCORA))+Double.parseDouble(nf5.format(vol_recv_LTCORA))-Double.parseDouble(nf5.format(mon_LTCORA))+Double.parseDouble(nf5.format(vol_exp_LTCORA))-Double.parseDouble(nf5.format(outstanding_commit_LTCORA))-Double.parseDouble(nf5.format(int_consumption_LTCORA)));
				net_uncommited_LTCORA_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_LTCORA/conversion_factor_mcm)));
				net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_LTCORA))+Double.parseDouble(nf5.format(vol_recv_LTCORA))-Double.parseDouble(nf5.format(mon_LTCORA))+Double.parseDouble(nf5.format(vol_exp_LTCORA))-Double.parseDouble(nf5.format(outstanding_commit_LTCORA))-Double.parseDouble(nf5.format(int_consumption_LTCORA));
				net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_LTCORA/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_LTCORA/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_LTCORA/conversion_factor_mcm));
				
				
				 net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
				 net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
				
			}
			
			net_uncommited_total = nf3.format(net_uncommited_total_volume);
			net_uncommited_total_mcm = nf.format(net_uncommited_total_mcm_volume);
			
			net_uncommited_overcommited_sales = nf3.format(net_uncommit_sale-re_commit_sales-(re_commit_sales*consumption_percentage/100));
			net_uncommited_overcommited_sales_mcm = nf.format(net_uncommit_sale_mcm-Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100)));
			
			net_uncommited_overcommited_total = nf3.format(net_uncommited_total_volume-re_commit_sales-(re_commit_sales*consumption_percentage/100));
			net_uncommited_overcommited_total_mcm = nf.format(net_uncommited_total_mcm_volume-Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100)));
				
			for(int i=0; i<SN_LOA_OUTER_COUNTER.size(); i++)
			{
				FINAL_COUNT.add(""+SN_LOA_OUTER_COUNTER.elementAt(i));
			}
			for(int i=0; i<RE_GAS_OUTER_COUNTER.size(); i++)
			{
				FINAL_COUNT.add(""+RE_GAS_OUTER_COUNTER.elementAt(i));
			}
			for(int i=0; i<LTCORA_OUTER_COUNTER.size(); i++)
			{
				FINAL_COUNT.add(""+LTCORA_OUTER_COUNTER.elementAt(i));
			}
			
			for(int i=0; i<FINAL_COUNT.size(); i++)
			{
				if(Integer.parseInt(""+FINAL_COUNT.elementAt(i))>max_count_for_column)
				{
					max_count_for_column = Integer.parseInt(""+FINAL_COUNT.elementAt(i));
				}
			}
			
			for(int i=0; i<SN_NO.size(); i++)
			{
				int count = ((Vector)SN_NO.elementAt(i)).size();
				String cust_cd = ""+((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).elementAt(count-1);
				for(int j=count; j<max_count_for_column; j++)
				{
					((Vector)SN_NO.elementAt(i)).add("");
					((Vector)SN_REF_NO.elementAt(i)).add("");
					((Vector)FGSA_NO.elementAt(i)).add("");
					((Vector)TCQ.elementAt(i)).add("");
					((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).add(""+cust_cd);
					((Vector)QTY_MMBTU.elementAt(i)).add("");
					((Vector)BALANCE.elementAt(i)).add("");
					((Vector)CONT_TYPE.elementAt(i)).add("");
					((Vector)SEND_OUT_REMARK.elementAt(i)).add("");
				}
			}
			
			for(int i=0; i<RE_GAS_NO.size(); i++)
			{
				int count = ((Vector)RE_GAS_NO.elementAt(i)).size();
				String cust_cd = ""+((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).elementAt(count-1);
				for(int j=count; j<max_count_for_column; j++)
				{
					((Vector)RE_GAS_NO.elementAt(i)).add("");				
					((Vector)CAPACITY.elementAt(i)).add("");
					((Vector)REMARK_RE_GAS_NO.elementAt(i)).add("");				
					((Vector)RE_GAS_CARGO_NO.elementAt(i)).add("");
					((Vector)QTY_MMBTU_RE_GAS.elementAt(i)).add("");
					((Vector)BALANCE_RE_GAS.elementAt(i)).add("");
					((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).add(""+cust_cd);
				}
			}
			
			for(int i=0; i<LTCORA_NO.size(); i++)
			{
				int count = ((Vector)LTCORA_NO.elementAt(i)).size();
				String cust_cd = ""+((Vector)INNER_CUSTOMER_CD_LTCORA.elementAt(i)).elementAt(count-1);
				for(int j=count; j<max_count_for_column; j++)
				{
					((Vector)LTCORA_NO.elementAt(i)).add("");	
					((Vector)LTCORA_CONT_TYPE.elementAt(i)).add("");
					((Vector)LTCORA_CAPACITY.elementAt(i)).add("");
					((Vector)LTCORA_REMARK_RE_GAS_NO.elementAt(i)).add("");				
					((Vector)LTCORA_CARGO_NO.elementAt(i)).add("");
					((Vector)QTY_MMBTU_LTCORA.elementAt(i)).add("");
					((Vector)BALANCE_LTCORA.elementAt(i)).add("");
					((Vector)INNER_CUSTOMER_CD_LTCORA.elementAt(i)).add(""+cust_cd);
				}
			}
			
			////System.out.println("max_count_for_column = "+max_count_for_column);
			
			/*for(int i=0; i<SN_NO.size(); i++)
			{
				//System.out.println("SN_NO size = "+((Vector)SN_NO.elementAt(i)).size());
				//System.out.println("FGSA_NO size = "+((Vector)FGSA_NO.elementAt(i)).size());
				//System.out.println("TCQ size = "+((Vector)TCQ.elementAt(i)).size());
				//System.out.println("INNER_CUSTOMER_CD_SN_LOA size = "+((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).size());
				//System.out.println("CONT_TYPE size = "+((Vector)CONT_TYPE.elementAt(i)).size());
			}
			
			for(int i=0; i<RE_GAS_NO.size(); i++)
			{
				//System.out.println("RE_GAS_NO size = "+((Vector)RE_GAS_NO.elementAt(i)).size());
				//System.out.println("CAPACITY size = "+((Vector)CAPACITY.elementAt(i)).size());
				//System.out.println("RE_GAS_CARGO_NO size = "+((Vector)RE_GAS_CARGO_NO.elementAt(i)).size());
				//System.out.println("INNER_CUSTOMER_CD_RE_GAS size = "+((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).size());
			}			
			
			total_regas=nf3.format(sum_balance_regas);
			total=nf3.format(sum_balance);*/
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchSendOut()-->"+e);
			  e.printStackTrace();
		}
	
	}

public void fetchSendOut_OLD() //JHP20120601
{
	try
	{
		Vector temp_CUSTOMER_CD_SN = new Vector();
		Vector temp_CUSTOMER_CD_LOA = new Vector();
		Vector temp_CUSTOMER_CD = new Vector();
		
		double net_uncommited_total_volume = 0;
		double net_uncommited_total_mcm_volume = 0;
//12/08/2010								
		String first_day_month = "01/"+from_date.substring(3);//01/08/2010
		String before_15day_month = "";//01/08/2010
		String end_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))+1);//12/08/2011
		
		String expected_volume_end_date = "";
		
		if(from_date.trim().length()==10)
		{
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
						  "FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				expected_volume_end_date = rset.getString(1);
			}
		}
		if(from_date.trim().length()==10)
		{
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')-15,'dd/mm/yyyy') " +
						  "FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				before_15day_month = rset.getString(1);
			}
		}
		////System.out.println("before_15day_month = "+before_15day_month);
		
		////System.out.println("expected_volume_end_date = "+expected_volume_end_date);
		
		queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+first_day_month+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
		rset = stmt.executeQuery(queryString);
		if(rset.next())
		{
			selected_month_year = rset.getString(1);
		}
		////System.out.println("selected_month_year = "+selected_month_year);
		int mnth = Integer.parseInt(from_date.substring(3,5));
		String month = "";
		String year = "";
		if(mnth==1)
		{
			mnth = 12;
			month = "12";
			year = ""+(Integer.parseInt(from_date.substring(6))-1);
		}
		else
		{
			year = from_date.substring(6);
			mnth = mnth-1;
			if(mnth<10)
			{
				month = "0"+mnth;
			}
			else
			{
				month = ""+mnth;
			}
		}
		
		String previous_month_start_date = "01/"+month+"/"+year;
		double conversion_factor_mcm = 38900;
		
		double vol_exp_sales = 0;
		double vol_exp_sales1 = 0;
		double vol_exp_regas = 0;
		double vol_exp_total = 0;
		
		double vol_recv_sales = 0;
		double vol_recv_regas = 0;
		double vol_recv_total = 0;
		
		double opening_stock_sales = 0;
		double opening_stock_regas = 0;
		double opening_stock = 0;
		
		double qty_to_be_supplied = 0;
		
		double int_consumption_sales = 0;
		double int_consumption_regas = 0;
		double int_consumption_total = 0;
		consumption_percentage = 2.0;
		
		double dead_stk = 700200;
		
		double month_total = 0;
		double mon_sales = 0;
		double mon_regas = 0;			
		//Following Logic is Updated By Milan Dalsaniya 20111011
		if(expected_volume_end_date.length()>=10)
		{
			//queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
			//			  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
			//			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
			
//			queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
//			  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//			  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
			queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
			  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')) AND CARGO_STATUS='T' AND UNIT_CD='1'";
		
			////System.out.println("Query for FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				vol_exp_sales = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				////System.out.println("Jaimin::"+vol_exp_sales);
				//volume_expected_sales = nf3.format(vol_exp_sales);
				//volume_expected_sales_mcm = nf.format(vol_exp_sales/conversion_factor_mcm);
			}
//			queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
//			  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//			  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy') ) AND CARGO_STATUS='T' AND UNIT_CD='2'";
			queryString = "SELECT SUM(A.CONFIRM_VOL) FROM FMS7_MAN_CONFIRM_CARGO_DTL A " +
			  "WHERE (A.DELV_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy') ) AND CARGO_STATUS='T' AND UNIT_CD='2'";
		
			////System.out.println("Query for FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				vol_exp_sales1 = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))*1000000));
				////System.out.println("Jaimin::"+vol_exp_sales1);
				
			}
			
			volume_expected_sales = nf3.format(vol_exp_sales+vol_exp_sales1);
			volume_expected_sales_mcm = nf.format((vol_exp_sales+vol_exp_sales1)/conversion_factor_mcm);
			
//			queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
//						  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY')+1 AND " +
//						  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')";
			queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
			  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE(TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'dd/mm/yyyy'),'dd/mm/yyyy')";

			////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				/*vol_exp_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				volume_expected_regas = nf3.format(vol_exp_regas);
				volume_expected_regas_mcm = nf.format(vol_exp_regas/conversion_factor_mcm);*/
				vol_exp_regas = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
				volume_expected_regas = nf3.format(vol_exp_regas);
				volume_expected_regas_mcm = nf.format(vol_exp_regas/conversion_factor_mcm);
			}
			
			vol_exp_total = vol_exp_sales+vol_exp_sales1 + vol_exp_regas;
			volume_expected_total = nf3.format(vol_exp_total);
			volume_expected_total_mcm = nf.format(vol_exp_total/conversion_factor_mcm);
		}
		
		if(from_date.length()>=10)
		{
			queryString = "SELECT SUM(B.QTY_MMBTU) FROM FMS7_CARGO_ARRIVAL_DTL A, FMS7_CARGO_QQ_DTL B " +
						  "WHERE A.CARGO_REF_NO=B.CARGO_REF_NO AND A.SPLIT_SEQ=B.SPLIT_SEQ AND A.SPLIT_SEQ='0' AND " +
						  "(A.ACT_ARRV_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			////System.out.println("Query for FMS7_CARGO_QQ_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				vol_recv_sales = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				volume_received_sales = nf3.format(vol_recv_sales);
				volume_received_sales_mcm = nf.format(vol_recv_sales/conversion_factor_mcm);
			}
			
//			queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
//						  "WHERE A.ACTUAL_RECPT_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
//						  "TO_DATE('"+from_date+"','DD/MM/YYYY')";
//JHP20120717			
			queryString = "SELECT SUM(A.QTY_TO_BE_SUPPLY),SUM(A.ADQ_QTY) FROM FMS7_RE_GAS_CARGO_DTL A " +
			  "WHERE A.ACTUAL_RECPT_DT BETWEEN TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+from_date+"','DD/MM/YYYY')";
////System.out.println("Query for FMS7_RE_GAS_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				/*vol_recv_regas = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				volume_received_regas =" nf3.format(vol_recv_regas);
				volume_received_regas_mcm = nf.format(vol_recv_regas/conversion_factor_mcm);*/
				vol_recv_regas = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
				volume_received_regas = nf3.format(vol_recv_regas);
				volume_received_regas_mcm = nf.format(vol_recv_regas/conversion_factor_mcm);
			}
			
			vol_recv_total = vol_recv_sales + vol_recv_regas;
			volume_received_total = nf3.format(vol_recv_total);
			volume_received_total_mcm = nf.format(vol_recv_total/conversion_factor_mcm);
							
			
			Vector fgsa_NO = new Vector();
			Vector sn_NO = new Vector();
			Vector cust_CD = new Vector();
			int counter = 0;
			
			
			queryString2 = "SELECT A.PERCENTAGE " +
						   "FROM FMS7_CONSUMPTION_MST A WHERE " +
						   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
						   "B.EFF_DT<=TO_DATE('"+first_day_month+"','DD/MM/YYYY'))";
			
			////System.out.println("Internal Consumption Percentage Fetch Query = "+queryString2);
			rset2 = stmt2.executeQuery(queryString2);
			if(rset2.next())
			{
				String int_consumption = rset2.getString(1)==null?"":rset2.getString(1);
				if(!int_consumption.trim().equals(""))
				{
					consumption_percentage = Double.parseDouble(int_consumption);
				}
			}
			
			consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
			
			queryString = "SELECT energy_mmbtu FROM FMS7_INVENTORY_OPENING_BALANCE " +
						  "WHERE opening_dt=TO_DATE('"+first_day_month+"','dd/mm/yyyy')";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				month_opening_stock = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
				opening_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				month_opening_stock_mcm = nf.format(opening_stock/conversion_factor_mcm);
//JHP20120829		int_consumption_total = ((opening_stock +vol_exp_total)*consumption_percentage)/100.00;
				int_consumption_total = ((opening_stock - dead_stk +vol_exp_total)*consumption_percentage)/100.00;

				internal_consumption_total = nf3.format(int_consumption_total);
				internal_consumption_total_mcm = nf.format(int_consumption_total/conversion_factor_mcm);
			}
			
			queryString = "SELECT RE_GAS_NO, CARGO_SEQ_NO, CUSTOMER_CD, QTY_TO_BE_SUPPLY " +
						  "FROM FMS7_RE_GAS_CARGO_DTL " +
						  "WHERE TO_DATE('"+first_day_month+"','DD/MM/YYYY') " +
						  "BETWEEN CONTRACT_START_DT AND CONTRACT_END_DT";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				fgsa_NO.add(rset.getString(1)==null?"":rset.getString(1));
				sn_NO.add(rset.getString(2)==null?"":rset.getString(2));
				cust_CD.add(rset.getString(3)==null?"":rset.getString(3));
				qty_to_be_supplied += rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4))));
				++counter;
			}
			
			if(counter>0)
			{
				for(int i=0; i<counter; i++)
				{
					queryString = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
					   			  "where CONTRACT_TYPE='R' AND " +
					   			  "GAS_DT<TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
					   			  "FGSA_NO='"+fgsa_NO.elementAt(i)+"' AND " +
					   			  "SN_NO='"+sn_NO.elementAt(i)+"' AND CUSTOMER_CD='"+cust_CD.elementAt(i)+"'";
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						qty_to_be_supplied -= rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1))));
					}
				}
				
				month_opening_stock_regas = nf3.format(qty_to_be_supplied);
				opening_stock_regas = Double.parseDouble(nf5.format(qty_to_be_supplied));
				month_opening_stock_regas_mcm = nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm);
			}
			
			if(qty_to_be_supplied!=0)
			{
				month_opening_stock_sales = nf3.format(opening_stock - qty_to_be_supplied);
				opening_stock_sales = Double.parseDouble(nf5.format(opening_stock - qty_to_be_supplied));
				month_opening_stock_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock/conversion_factor_mcm)) - Double.parseDouble(nf.format(Double.parseDouble(nf5.format(qty_to_be_supplied))/conversion_factor_mcm)));
				
//JHP20120829	    int_consumption_sales = (((opening_stock - qty_to_be_supplied)+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
				int_consumption_sales = (((opening_stock - qty_to_be_supplied - dead_stk)+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;

				internal_consumption_sales = nf3.format(int_consumption_sales);
				internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
				
				int_consumption_regas = ((qty_to_be_supplied+vol_exp_regas)*consumption_percentage)/100.00;
				internal_consumption_regas = nf3.format(int_consumption_regas);
				internal_consumption_regas_mcm = nf.format(int_consumption_regas/conversion_factor_mcm);
			}
			else
			{
				month_opening_stock_sales = month_opening_stock;
				opening_stock_sales = opening_stock;
				month_opening_stock_sales_mcm = month_opening_stock_mcm;
				
//JHP20120829		int_consumption_sales = ((opening_stock+vol_exp_sales+vol_exp_sales1)*consumption_percentage)/100.00;
				internal_consumption_sales = nf3.format(int_consumption_sales);
				internal_consumption_sales_mcm = nf.format(int_consumption_sales/conversion_factor_mcm);
				
				int_consumption_regas = (vol_exp_regas*consumption_percentage)/100.00;
				internal_consumption_regas = nf3.format(int_consumption_regas);
				internal_consumption_regas_mcm = nf.format(int_consumption_regas/conversion_factor_mcm);
			}
			
			queryString = "SELECT TANK1_D1_VOLUME, TANK2_D1_VOLUME, TANK_DTL_DT " +
						  "FROM FMS7_TANK_MASTER_DTL " +
						  "WHERE TANK_DTL_DT<TO_DATE('"+first_day_month+"','DD/MM/YYYY') " +
						  "ORDER BY TANK_DTL_DT DESC";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				dead_stk = rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1))));
				dead_stk += rset.getString(2)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(2))));
				dead_stk = 700200;				
			}
			dead_stock = nf3.format(dead_stk);
			dead_stock_mcm = nf.format(dead_stk/conversion_factor_mcm);
							
			
			
			String dd=from_date.substring(0,2);
			if(Integer.parseInt(dd)<=15)
			{
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
			}
			else
			{
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";	
			}
			////System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				selected_prev_month_year = rset.getString(2);
//			queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
//						   "where CONTRACT_TYPE='R' AND " +
//						   "GAS_DT<=TO_DATE('"+from_date.trim()+"','DD/MM/YYYY') AND " +
//						   "GAS_DT>=TO_DATE('"+first_day_month+"','DD/MM/YYYY')";
				if(Integer.parseInt(dd)<=15)
				{
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where CONTRACT_TYPE='R' AND " +
							   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
							   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
				}
				else
				{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
					   "where CONTRACT_TYPE='R' AND " +
					   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
					   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
		
				}
			////System.out.println("QTY_MMBTU = "+queryString1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				month_regas = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
				mon_regas = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				month_regas_mcm = nf.format(mon_regas/conversion_factor_mcm);
				month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
			}
			
			
//			queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
//						   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
//						   "GAS_DT<=TO_DATE('"+from_date.trim()+"','DD/MM/YYYY') AND " +
//						   "GAS_DT>=TO_DATE('"+first_day_month+"','DD/MM/YYYY')";
			if(Integer.parseInt(dd)<=15)
			{
			queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
						   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
						   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
						   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
			}
			else
			{
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
				   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
				   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
				   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
	
			}
			////System.out.println("QTY_MMBTU = "+queryString1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				month_sales = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
				mon_sales = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				month_sales_mcm = nf.format(mon_sales/conversion_factor_mcm);
				month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
			}					
			
			
			month_total_sales = nf3.format(month_total);
			month_total_sales_mcm = nf.format(month_total/conversion_factor_mcm);
			}
		}
		
		if(previous_month_start_date.length()>=10)
		{
			String dd=from_date.substring(0,2);
			
			if(Integer.parseInt(dd)<=15)
			{
			queryString = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";
			}
			else
			{
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DD/MM/YYYY'), TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','DD/MM/YYYY')),'MONTH-YY') FROM DUAL";	
			}
			////System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				selected_prev_month_year = rset.getString(2);
				double prev_month_total = 0;
				double pre_mon_sales = 0;
				double pre_mon_regas = 0;
				if(Integer.parseInt(dd)<=15)
				{
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where CONTRACT_TYPE='R' AND " +
							   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
							   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
				}
				else
				{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
					   "where CONTRACT_TYPE='R' AND " +
					   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
					   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
		
				}
				//////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					prev_month_regas = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
					pre_mon_regas = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					prev_month_regas_mcm = nf.format(pre_mon_regas/conversion_factor_mcm);
					prev_month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				}
				
				if(Integer.parseInt(dd)<=15)
				{
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
							   "GAS_DT<=TO_DATE('"+rset.getString(1)+"','DD/MM/YYYY') AND " +
							   "GAS_DT>=TO_DATE('"+previous_month_start_date+"','DD/MM/YYYY')";
				}
				else
				{
					queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
					   "where (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
					   "GAS_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY')-1 AND " +
					   "GAS_DT>=TO_DATE('"+before_15day_month+"','DD/MM/YYYY')";
		
				}
				//////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					prev_month_sales = rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1)));
					pre_mon_sales = rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
					prev_month_sales_mcm = nf.format(pre_mon_sales/conversion_factor_mcm);
					prev_month_total += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
				}					
				
				prev_month_total_sales = nf3.format(prev_month_total);
				prev_month_total_sales_mcm = nf.format(prev_month_total/conversion_factor_mcm);
			}
		}
		
		
		//Following Coding Is Related to Re-Gas Contracts ...
		double outstanding_commit_regas = 0;
		//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
		/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
					  "(A.CONTRACT_START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
					  "OR (A.CONTRACT_END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy'))  " +
					  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.CONTRACT_START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.CONTRACT_START_DT) AND " +
					  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
					  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
		
		//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
		
		//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
		/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
					  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
					  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
					  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
					  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
					  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
					  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
		
//		FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
		queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
		  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
		  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
		  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
		  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
		  "A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
		  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
		////System.out.println(">>>>Re-Gas Customer Code"+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));				
		}
		
		//All Customer's Data Involved In LIVE Re-Gas Contracts depends upon Selected Period ...
		for(int i=0;i<CUSTOMER_CD_RE_GAS.size();i++)
		{				
			Vector tmp_RE_GAS_NO = new Vector();
			Vector tmp_CAPACITY = new Vector();
			Vector tmp_RE_GAS_CARGO_NO = new Vector();
			Vector tmp_INNER_CUSTOMER_CD_RE_GAS = new Vector();
			Vector tmp_QTY_MMBTU = new Vector();
			Vector tmp_QTY_BALANCE = new Vector();
			Vector tmp_REMARK_RE_GAS = new Vector();
			String tmp_RE_GAS_CLOSURE_DT = "";
			
			double total_balance = 0;
			
			queryString = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
						  "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
			////System.out.println(">>>>Customer for ReGas Name"+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				CUSTOMER_NM_RE_GAS.add(rset.getString(2)==null?"":rset.getString(2));				
			}
			else
			{
				CUSTOMER_NM_RE_GAS.add("");
			}
			
			int count = 0;
			
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "((A.CONTRACT_START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.CONTRACT_END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy'))  " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.CONTRACT_START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.CONTRACT_START_DT)) " +
						  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
						  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
			
			//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD, " +
						  "NVL(A.REGAS_CLOSURE_FLAG, 'N') from FMS7_RE_GAS_CARGO_DTL A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
						  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
						  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

			//FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
			queryString = "select A.RE_GAS_NO,A.QTY_TO_BE_SUPPLY,A.CARGO_SEQ_NO,A.CUSTOMER_CD, " +
			  "NVL(A.REGAS_CLOSURE_FLAG, 'N'),TO_CHAR(A.REGAS_CLOSURE_DT,'DD/MM/YYYY') from FMS7_RE_GAS_CARGO_DTL A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.CONTRACT_START_DT and A.CONTRACT_END_DT) OR (A.CONTRACT_START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
			  "AND A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
			  "AND A.RE_GAS_REV_NO=(SELECT MAX(B.RE_GAS_REV_NO) FROM FMS7_RE_GAS_CARGO_DTL B where A.RE_GAS_NO=B.RE_GAS_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
			////System.out.println(">>>>Re-Gas Name = "+queryString);
			rset = stmt.executeQuery(queryString);
			//BALANCE_RE_GAS	QTY_MMBTU_RE_GAS	CAPACITY
			while(rset.next())
			{
				tmp_RE_GAS_NO.add(rset.getString(1)==null?"":rset.getString(1));
				tmp_CAPACITY.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
				tmp_RE_GAS_CARGO_NO.add(rset.getString(3)==null?"":rset.getString(3));
				tmp_INNER_CUSTOMER_CD_RE_GAS.add(rset.getString(4)==null?"0":rset.getString(4));
				tmp_RE_GAS_CLOSURE_DT = rset.getString(6)==null?"0":rset.getString(6);
				
				double balance = 0;
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
							   "AND SN_NO='"+rset.getString(3)+"' AND FGSA_NO='"+rset.getString(1)+"' " +
							   "AND CONTRACT_TYPE='R' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
				////System.out.println(">>>>QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				//	Introduce By Milan Dalsaniya 20111012					
				if(rset1.next())
				{
					tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
					balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
					String tmp = "";
					if (!tmp_RE_GAS_CLOSURE_DT.equals("0")){
						////System.out.println("from if of dual >>>>>>> ");
						queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_RE_GAS_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
						////System.out.println(">>>>Dual = "+queryString1);
						rset2 = stmt2.executeQuery(queryString1);
						if (rset2.next())
						{
							tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
							////System.out.println("Count >>>>>>>> "+tmp);
						}
					}
					
					if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
					{
						tmp_QTY_BALANCE.add("-");
						balance = balance*-1;
						tmp_REMARK_RE_GAS.add("Over supplied by "+nf3.format(balance)+" Mmbtus");
						balance = 0;
					}
					else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
					{
						tmp_REMARK_RE_GAS.add("Short closed by "+nf3.format(balance)+" Mmbtus");	
						tmp_QTY_BALANCE.add("-");
						balance = 0;
						//tmp_REMARK_RE_GAS.add("");
					}
					else
					{
						if (balance < 0 )
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK_RE_GAS.add("");
							balance = 0;
						}
						else if (balance == 0 )
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK_RE_GAS.add("");
							balance = 0;
						}
						else
						{
							tmp_QTY_BALANCE.add(""+nf3.format(balance));
							tmp_REMARK_RE_GAS.add("");
						}
					}
				}
				else
				{
					tmp_REMARK_RE_GAS.add("");
					tmp_QTY_MMBTU.add("");
					balance = (Double.parseDouble(rset.getString(2)==null?"0":nf5.format(Double.parseDouble(rset.getString(2))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
					tmp_QTY_BALANCE.add(rset.getString(2)==null?"":nf3.format(Double.parseDouble(rset.getString(2))));
				}
				total_balance += balance;
				++count;
			}
			
			if(count==0)
			{
				tmp_RE_GAS_NO.add("");
				tmp_REMARK_RE_GAS.add("");
				tmp_CAPACITY.add("");
				tmp_RE_GAS_CARGO_NO.add("");
				tmp_QTY_MMBTU.add("");
				tmp_QTY_BALANCE.add("");
				tmp_INNER_CUSTOMER_CD_RE_GAS.add(""+CUSTOMER_CD_RE_GAS.elementAt(i));
			}
			
			RE_GAS_NO.add(tmp_RE_GAS_NO);				
			CAPACITY.add(tmp_CAPACITY);
			REMARK_RE_GAS_NO.add(tmp_REMARK_RE_GAS);
			RE_GAS_CARGO_NO.add(tmp_RE_GAS_CARGO_NO);
			INNER_CUSTOMER_CD_RE_GAS.add(tmp_INNER_CUSTOMER_CD_RE_GAS);
			QTY_MMBTU_RE_GAS.add(tmp_QTY_MMBTU);
			BALANCE_RE_GAS.add(tmp_QTY_BALANCE);
			RE_GAS_OUTER_COUNTER.add(""+count);
			RE_GAS_TOTAL_BALANCE.add(nf3.format(total_balance));
			outstanding_commit_regas += Double.parseDouble(nf5.format(total_balance));
		}
		outstanding_commitment_regas = nf3.format(outstanding_commit_regas);
		outstanding_commitment_regas_mcm = nf.format(outstanding_commit_regas/conversion_factor_mcm);
		
		
		//Following Coding Is Related to SN/LOA Contracts ...
		//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
		/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_SN_MST A where "+			
					  "(A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
					  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
					  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT) AND " +
					  "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where A.SN_NO=B.SN_NO AND " +
					  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
					  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
		
		//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...

		//FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...

		/*queryString =  "select DISTINCT(A.CUSTOMER_CD) from FMS7_SN_MST A where " +
					   "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
					   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
					   "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
					   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
					   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
					   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
					   "ORDER BY A.CUSTOMER_CD";*/
//		FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...		
		
		queryString =  "select DISTINCT(A.CUSTOMER_CD) from FMS7_SN_MST A where " +
		   "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
		   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
		   "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
		   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
		   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
		   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
		   "ORDER BY A.CUSTOMER_CD";
		////System.out.println("SN Customer Code = "+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			temp_CUSTOMER_CD_SN.add(rset.getString(1)==null?"":rset.getString(1));				
		}
		
		//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
		/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
					  "(A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
					  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
					  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT) AND " +
					  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
					  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
					  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
		
		//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...
		
//		FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
		/*queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
					  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
					  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
					  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
					  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
					  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
					  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
					  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

//		FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...

		queryString = "select DISTINCT A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
		  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
		  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
		  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
		  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
		  "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
		  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
		  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
		
		////System.out.println("LOA Customer Code = "+queryString);
		rset = stmt.executeQuery(queryString);
		while(rset.next())
		{
			temp_CUSTOMER_CD_LOA.add(rset.getString(1)==null?"":rset.getString(1));
		}
		
		for(int i=0;i<temp_CUSTOMER_CD_SN.size();i++)
		{	
			for(int j=0;j<temp_CUSTOMER_CD_LOA.size();j++)
			{
				if((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD_LOA.elementAt(j)).trim()))
				{
					temp_CUSTOMER_CD.add((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim());
					break;
				}
			}
		}
		
		for(int i=0;i<temp_CUSTOMER_CD_SN.size();i++)
		{
			int inner_count = 0;
			for(int j=0;j<temp_CUSTOMER_CD.size();j++)
			{
				if((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD.elementAt(j)).trim()))
				{
					++inner_count;
					break;
				}					
			}
			
			if(inner_count==0)
			{
				CUSTOMER_CD.add((""+temp_CUSTOMER_CD_SN.elementAt(i)).trim());
			}
		}
		
		for(int i=0;i<temp_CUSTOMER_CD_LOA.size();i++)
		{
			int inner_count = 0;
			for(int j=0;j<temp_CUSTOMER_CD.size();j++)
			{
				if((""+temp_CUSTOMER_CD_LOA.elementAt(i)).trim().equals((""+temp_CUSTOMER_CD.elementAt(j)).trim()))
				{
					++inner_count;
					break;
				}					
			}
			
			if(inner_count==0)
			{
				CUSTOMER_CD.add((""+temp_CUSTOMER_CD_LOA.elementAt(i)).trim());
			}
		}
					
		for(int j=0;j<temp_CUSTOMER_CD.size();j++)
		{
			CUSTOMER_CD.add((""+temp_CUSTOMER_CD.elementAt(j)).trim());
		}
		
		//All Customer's Data Involved In LIVE SN or LOA depends upon Selected Period ...
		double outstanding_commit_sales = 0;
		double re_commit_sales = 0;
					
		for(int i=0; i<CUSTOMER_CD.size(); i++)
		{				
			Vector tmp_SN_NO = new Vector();
			Vector tmp_SN_REF_NO = new Vector();
			Vector tmp_FGSA_NO = new Vector();
			Vector tmp_TCQ = new Vector();
			Vector tmp_INNER_CUSTOMER_CD_SN_LOA = new Vector();
			Vector tmp_CONT_TYPE = new Vector();
			Vector tmp_QTY_MMBTU = new Vector();
			Vector tmp_QTY_BALANCE = new Vector();
			Vector tmp_REMARK = new Vector();
			String tmp_SN_CLOSURE_DT = "";
			
			queryString = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
						  "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
			//////System.out.println("Customer Name"+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				CUSTOMER_NM.add(rset.getString(2)==null?"":rset.getString(2));				
			}
			else
			{
				CUSTOMER_NM.add("");
			}
			
			int cnt=0;
			double total_balance = 0;
			
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD,NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO from FMS7_SN_MST A where "+			
						  "((A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (to_date('"+from_date+"','dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT)) " +
						  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
						  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
						  "where A.SN_NO=B.SN_NO AND " +
						  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
			//Following Query Has Been Introduced By Samik Shah On 10th September, 2011 ...

//			FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
			/*queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD," +
						  "NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO,A.sn_ref_no from FMS7_SN_MST A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
						  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
						  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
						  "where A.SN_NO=B.SN_NO AND " +
						  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/

//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
			queryString = "select A.SN_NO,A.FGSA_NO,A.TCQ,A.CUSTOMER_CD," +
			  "NVL(A.SN_CLOSURE_FLAG,'N'),A.FGSA_REV_NO,A.sn_ref_no,TO_CHAR(A.SN_CLOSURE_DT,'DD/MM/YYYY') from FMS7_SN_MST A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
			  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
			  "AND A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B " +
			  "where A.SN_NO=B.SN_NO AND " +
			  "A.FGSA_NO=B.FGSA_NO AND A.FGSA_REV_NO=B.FGSA_REV_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
			////System.out.println("SN Name = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				++cnt;
				tmp_SN_NO.add(rset.getString(1)==null?"":rset.getString(1));
				tmp_FGSA_NO.add(rset.getString(2)==null?"":rset.getString(2));
				tmp_TCQ.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
				tmp_INNER_CUSTOMER_CD_SN_LOA.add(rset.getString(4)==null?"0":rset.getString(4));
				tmp_CONT_TYPE.add("S");
				tmp_SN_REF_NO.add(rset.getString(7)==null?"":rset.getString(7));
				tmp_SN_CLOSURE_DT = rset.getString(8)==null?"0":rset.getString(8);
				int re_count = 0;
				
				queryString1 = "SELECT COUNT(*) FROM FMS7_SN_LD_DTL " +
							   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							   "AND SN_NO='"+rset.getString(1)+"' AND " +
							   "FGSA_NO='"+rset.getString(2)+"'";
				//////System.out.println("FMS7_SN_LD_DTL Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					re_count += rset1.getInt(1);
				}
				
				queryString1 = "SELECT COUNT(*) FROM FMS7_SN_MAKEUPGAS_DTL " +
							   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							   "AND SN_NO='"+rset.getString(1)+"' AND " +
							   "FGSA_NO='"+rset.getString(2)+"'";
				//////System.out.println("FMS7_SN_MAKEUPGAS_DTL Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					re_count += rset1.getInt(1);
				}
				
				queryString1 = "SELECT COUNT(*) FROM FMS7_SN_TOP_DTL " +
							   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							   "AND SN_NO='"+rset.getString(1)+"' AND " +
							   "FGSA_NO='"+rset.getString(2)+"'";
				//////System.out.println("FMS7_SN_TOP_DTL Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					re_count += rset1.getInt(1);
				}
	
				double balance = 0; 
					
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							   "AND SN_NO='"+rset.getString(1)+"' AND FGSA_NO='"+rset.getString(2)+"' " +
							   "AND CONTRACT_TYPE='S' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
				//////System.out.println("QTY_MMBTU = "+queryString1); 
				rset1 = stmt1.executeQuery(queryString1);
//				FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
				if(rset1.next())
				{
					tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
											
					balance = (Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
					
					String tmp = "";
					if (!tmp_SN_CLOSURE_DT.equals("0")){
						////System.out.println("from if of dual of SN >>>>>>> ");
						queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_SN_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
						////System.out.println(">>>>Dual SN = "+queryString1);
						rset2 = stmt2.executeQuery(queryString1);
						if (rset2.next())
						{
							tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
							////System.out.println("Count SN>>>>>>>> "+tmp);
							////System.out.println("balance SN>>>>>>>> "+balance);
						}
					}
					if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
					{
						tmp_QTY_BALANCE.add("-");
						tmp_REMARK.add("Short closed by "+nf3.format(balance)+" Mmbtus");
						balance = 0;
					}
					else if(rset.getString(5).trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
					{
						tmp_QTY_BALANCE.add("-");
						tmp_REMARK.add("Over supplied by "+nf3.format(balance*(-1))+" Mmbtus");
						balance = 0;
					}
					else
					{
						//tmp_QTY_BALANCE.add(nf3.format(Double.parseDouble(rset.getString(3)==null?"0":nf.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf.format(Double.parseDouble(rset1.getString(1))))));
						if (balance<=0)
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("");
							balance = 0;
						}
						else if (balance==0)
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("");
							balance = 0;
						}
						else
						{
							tmp_QTY_BALANCE.add(nf3.format(balance));
							tmp_REMARK.add("");
						}
					}						
				}
				else
				{
					tmp_QTY_MMBTU.add("");
					balance = Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))));
					tmp_QTY_BALANCE.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
					tmp_REMARK.add("");
				}
				
				total_balance += balance;
				
				if(re_count>0)
				{
					outstanding_commit_sales += balance;
				}
				else
				{
					re_commit_sales += balance;
				}
			}
			
			
			double balance = 0;
			
			//Following Query Has Been Commented By Samik Shah On 10th September, 2011 ...
			/*queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD from FMS7_LOA_MST A where "+			
						  "((A.START_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (A.END_DT between to_date('"+first_day_month+"','dd/mm/yyyy') AND to_date('"+from_date+"','dd/mm/yyyy')) " +
						  "OR (to_date('"+from_date+"', 'dd/mm/yyyy')<A.START_DT AND to_date('"+end_date+"','dd/mm/yyyy')>=A.START_DT)) " +
						  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
						  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
						  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
			
//			FOLLOWING Query Has Been cOMMENTED By MILAN DALSANIYA On 12th Oct, 2011 ...
		
			/*queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD, " +
						  "A.loa_ref_no from FMS7_LOA_MST A where "+			
						  "((to_date('"+first_day_month+"','dd/mm/yyyy') between " +
						  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
						  "TO_DATE('"+first_day_month+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
						  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
						  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
						  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
						  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";*/
//			FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
			queryString = "select A.LOA_NO,A.TENDER_NO,A.TCQ,A.CUSTOMER_CD, " +
			  "A.loa_ref_no, A.LOA_CLOSURE_FLAG ,TO_CHAR(A.LOA_CLOSURE_DT,'DD/MM/YYYY') from FMS7_LOA_MST A where "+			
			  "((to_date('"+before_15day_month+"','dd/mm/yyyy') between " +
			  "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
			  "TO_DATE('"+before_15day_month+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
			  "AND A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
			  "AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B " +
			  "where A.LOA_NO=B.LOA_NO AND A.TENDER_NO=B.TENDER_NO " +
			  "AND A.CUSTOMER_CD=B.CUSTOMER_CD)";
			
			////System.out.println("LOA NAME = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				++cnt;
				tmp_SN_NO.add(rset.getString(1)==null?"":rset.getString(1));
				tmp_FGSA_NO.add(rset.getString(2)==null?"":rset.getString(2));
				tmp_TCQ.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
				tmp_INNER_CUSTOMER_CD_SN_LOA.add(rset.getString(4)==null?"0":rset.getString(4));
				tmp_CONT_TYPE.add("L");
				tmp_SN_REF_NO.add(rset.getString(5)==null?"":rset.getString(5));
				tmp_SN_CLOSURE_DT = rset.getString(7)==null?"0":rset.getString(7);
				String tmp_SN_CLOSURE_FLG = rset.getString(6)==null?"0":rset.getString(6);
				//////System.out.println("LOA REF NO : "+rset.getString(5)==null?"":rset.getString(5));
				////System.out.println("LOA REF NO : "+rset.getString(5));
				int re_count = 0;
				
				queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_LD_DTL " +
							   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							   "AND LOA_NO='"+rset.getString(1)+"' AND " +
							   "FGSA_NO='"+rset.getString(2)+"'";
				//////System.out.println("FMS7_LOA_LD_DTL Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					re_count += rset1.getInt(1);
				}
				
				queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_MAKEUPGAS_DTL " +
							   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							   "AND LOA_NO='"+rset.getString(1)+"' AND " +
							   "FGSA_NO='"+rset.getString(2)+"'";
				//////System.out.println("FMS7_LOA_MAKEUPGAS_DTL Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					re_count += rset1.getInt(1);
				}
				
				queryString1 = "SELECT COUNT(*) FROM FMS7_LOA_TOP_DTL " +
							   "WHERE CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							   "AND LOA_NO='"+rset.getString(1)+"' AND " +
							   "FGSA_NO='"+rset.getString(2)+"'";
				//////System.out.println("FMS7_LOA_TOP_DTL Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					re_count += rset1.getInt(1);
				}
				
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' " +
							   "AND SN_NO='"+rset.getString(1)+"' AND FGSA_NO='"+rset.getString(2)+"' " +
							   "AND CONTRACT_TYPE='L' AND GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY')";
				//////System.out.println("QTY_MMBTU = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
//				FOLLOWING Query Has Been INTRODUCE BY MILAN DALSANIYA On 12th Oct, 2011 ...
				if(rset1.next())
				{
					tmp_QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
					
					balance = (Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf5.format(Double.parseDouble(rset1.getString(1)))));
					
					String tmp = "";
					if (!tmp_SN_CLOSURE_DT.equals("0")){
						////System.out.println("from if of dual of LOA >>>>>>> ");
						queryString1 = "SELECT COUNT(*) FROM DUAL WHERE TO_DATE('"+tmp_SN_CLOSURE_DT.trim()+"','DD/MM/YYYY') <= TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')";
						////System.out.println(">>>>Dual LOA= "+queryString1);
						rset2 = stmt2.executeQuery(queryString1);
						if (rset2.next())
						{
							tmp = (rset2.getString(1)==null? "milna" : rset2.getString(1));
							////System.out.println("Count >>>>>>>> LOA "+tmp);
							////System.out.println("balance >>>>>>>> LOA "+balance);
						}
					}
					if(tmp_SN_CLOSURE_FLG.trim().equalsIgnoreCase("Y") && balance>0 && tmp.trim().equals("1"))
					{
						tmp_QTY_BALANCE.add("-");
						tmp_REMARK.add("Short closed by "+nf3.format(balance)+" Mmbtus");
						balance = 0;
					}
					else if(tmp_SN_CLOSURE_FLG.trim().equalsIgnoreCase("Y") && balance<0 && tmp.trim().equals("1"))
					{
						tmp_QTY_BALANCE.add("-");
						tmp_REMARK.add("Over supplied by "+nf3.format(balance*(-1))+" Mmbtus");
						balance = 0;
					}
					else
					{
						//tmp_QTY_BALANCE.add(nf3.format(Double.parseDouble(rset.getString(3)==null?"0":nf.format(Double.parseDouble(rset.getString(3))))-Double.parseDouble(rset1.getString(1)==null?"0":nf.format(Double.parseDouble(rset1.getString(1))))));
						if (balance<=0)
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("");
							balance = 0;
						}
						else if (balance==0)
						{
							tmp_QTY_BALANCE.add("-");
							tmp_REMARK.add("");
							balance = 0;
						}
						else
						{
							tmp_QTY_BALANCE.add(nf3.format(balance));
							tmp_REMARK.add("");
						}
					}					
					/*
					if(balance<=0)
					{
						tmp_QTY_BALANCE.add("-");
						balance = 0;
					}
					else
					{
						tmp_QTY_BALANCE.add(nf3.format(balance));
					}*/
				}
				else
				{
					tmp_QTY_MMBTU.add("");
					balance = Double.parseDouble(rset.getString(3)==null?"0":nf5.format(Double.parseDouble(rset.getString(3))));
					tmp_QTY_BALANCE.add(rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3))));
				}
				
				tmp_REMARK.add("");
				total_balance += balance;
				
				if(re_count>0)
				{
					outstanding_commit_sales += balance;
				}
				else
				{
					re_commit_sales += balance;
				}
			}
			
			if(cnt==0)
			{
				tmp_SN_NO.add("");
				tmp_SN_REF_NO.add("");
				tmp_FGSA_NO.add("");
				tmp_TCQ.add("");
				tmp_INNER_CUSTOMER_CD_SN_LOA.add(""+CUSTOMER_CD.elementAt(i));
				tmp_CONT_TYPE.add("");
				tmp_QTY_MMBTU.add("");
				tmp_QTY_BALANCE.add("");
				tmp_REMARK.add("");
			}
			
			SN_NO.add(tmp_SN_NO);
			SN_REF_NO.add(tmp_SN_REF_NO);
			FGSA_NO.add(tmp_FGSA_NO);
			TCQ.add(tmp_TCQ);
			INNER_CUSTOMER_CD_SN_LOA.add(tmp_INNER_CUSTOMER_CD_SN_LOA);
			CONT_TYPE.add(tmp_CONT_TYPE);
			QTY_MMBTU.add(tmp_QTY_MMBTU);
			BALANCE.add(tmp_QTY_BALANCE);
			SN_LOA_OUTER_COUNTER.add(""+cnt);
			SEND_OUT_REMARK.add(tmp_REMARK);
			SN_LOA_TOTAL_BALANCE.add(nf3.format(total_balance));
		}
		////System.out.println("SN REF NO : "+SN_REF_NO);
		outstanding_commitment_sales = nf3.format(outstanding_commit_sales);
		outstanding_commitment_sales_mcm = nf.format(outstanding_commit_sales/conversion_factor_mcm);
		outstanding_commitment_total = nf3.format(outstanding_commit_regas+outstanding_commit_sales);
		outstanding_commitment_total_mcm = nf.format((outstanding_commit_regas/conversion_factor_mcm)+(outstanding_commit_sales/conversion_factor_mcm));
		re_commitment_sales = nf3.format(re_commit_sales);
		re_commitment_sales_mcm = nf.format(re_commit_sales/conversion_factor_mcm);
		internal_consumption_re_commitment_sales = nf3.format(re_commit_sales*consumption_percentage/100);
		internal_consumption_re_commitment_sales_mcm = nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100);
		
		//double net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
		//double net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
		
		double net_uncommit_sale =0;
		double net_uncommit_sale_mcm =0;
		
		String dd=from_date.substring(0,2);
		
		if(Integer.parseInt(dd)<=15)
		{
			net_uncommited_sales = nf3.format(Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales)));
			net_uncommited_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm)));
			net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
			net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
			
			net_uncommited_regas = nf3.format(Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas)));
			net_uncommited_regas_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm)));
			net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas));
			net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm));
		
			 net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
			 net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
			
		}
		else
		{
			net_uncommited_sales = nf3.format(Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales)));
			net_uncommited_sales_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm)));
			net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
			net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
			
			net_uncommited_regas = nf3.format(Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_recv_regas))-Double.parseDouble(nf5.format(mon_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas)));
			net_uncommited_regas_mcm = nf.format(Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm)));
			net_uncommited_total_volume += Double.parseDouble(nf5.format(opening_stock_regas))+Double.parseDouble(nf5.format(vol_recv_regas))-Double.parseDouble(nf5.format(mon_regas))+Double.parseDouble(nf5.format(vol_exp_regas))-Double.parseDouble(nf5.format(outstanding_commit_regas))-Double.parseDouble(nf5.format(int_consumption_regas));
			net_uncommited_total_mcm_volume += Double.parseDouble(nf.format(opening_stock_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_regas/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_regas/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_regas/conversion_factor_mcm));
			
			 net_uncommit_sale = Double.parseDouble(nf5.format(opening_stock_sales))-Double.parseDouble(nf5.format(dead_stk))+Double.parseDouble(nf5.format(vol_recv_sales))-Double.parseDouble(nf5.format(mon_sales))+Double.parseDouble(nf5.format(vol_exp_sales+vol_exp_sales1))-Double.parseDouble(nf5.format(outstanding_commit_sales))-Double.parseDouble(nf5.format(int_consumption_sales));
			 net_uncommit_sale_mcm = Double.parseDouble(nf.format(opening_stock_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(dead_stk/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_recv_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(mon_sales/conversion_factor_mcm))+Double.parseDouble(nf.format(vol_exp_sales+vol_exp_sales1/conversion_factor_mcm))-Double.parseDouble(nf.format(outstanding_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(int_consumption_sales/conversion_factor_mcm));
			
		}
		
		net_uncommited_total = nf3.format(net_uncommited_total_volume);
		net_uncommited_total_mcm = nf.format(net_uncommited_total_mcm_volume);
		
		net_uncommited_overcommited_sales = nf3.format(net_uncommit_sale-re_commit_sales-(re_commit_sales*consumption_percentage/100));
		net_uncommited_overcommited_sales_mcm = nf.format(net_uncommit_sale_mcm-Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100)));
		
		net_uncommited_overcommited_total = nf3.format(net_uncommited_total_volume-re_commit_sales-(re_commit_sales*consumption_percentage/100));
		net_uncommited_overcommited_total_mcm = nf.format(net_uncommited_total_mcm_volume-Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))-Double.parseDouble(nf.format(Double.parseDouble(nf.format(re_commit_sales/conversion_factor_mcm))*consumption_percentage/100)));
			
		for(int i=0; i<SN_LOA_OUTER_COUNTER.size(); i++)
		{
			FINAL_COUNT.add(""+SN_LOA_OUTER_COUNTER.elementAt(i));
		}
		for(int i=0; i<RE_GAS_OUTER_COUNTER.size(); i++)
		{
			FINAL_COUNT.add(""+RE_GAS_OUTER_COUNTER.elementAt(i));
		}
		
		for(int i=0; i<FINAL_COUNT.size(); i++)
		{
			if(Integer.parseInt(""+FINAL_COUNT.elementAt(i))>max_count_for_column)
			{
				max_count_for_column = Integer.parseInt(""+FINAL_COUNT.elementAt(i));
			}
		}
		
		for(int i=0; i<SN_NO.size(); i++)
		{
			int count = ((Vector)SN_NO.elementAt(i)).size();
			String cust_cd = ""+((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).elementAt(count-1);
			for(int j=count; j<max_count_for_column; j++)
			{
				((Vector)SN_NO.elementAt(i)).add("");
				((Vector)SN_REF_NO.elementAt(i)).add("");
				((Vector)FGSA_NO.elementAt(i)).add("");
				((Vector)TCQ.elementAt(i)).add("");
				((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).add(""+cust_cd);
				((Vector)QTY_MMBTU.elementAt(i)).add("");
				((Vector)BALANCE.elementAt(i)).add("");
				((Vector)CONT_TYPE.elementAt(i)).add("");
				((Vector)SEND_OUT_REMARK.elementAt(i)).add("");
			}
		}
		
		for(int i=0; i<RE_GAS_NO.size(); i++)
		{
			int count = ((Vector)RE_GAS_NO.elementAt(i)).size();
			String cust_cd = ""+((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).elementAt(count-1);
			for(int j=count; j<max_count_for_column; j++)
			{
				((Vector)RE_GAS_NO.elementAt(i)).add("");				
				((Vector)CAPACITY.elementAt(i)).add("");
				((Vector)REMARK_RE_GAS_NO.elementAt(i)).add("");				
				((Vector)RE_GAS_CARGO_NO.elementAt(i)).add("");
				((Vector)QTY_MMBTU_RE_GAS.elementAt(i)).add("");
				((Vector)BALANCE_RE_GAS.elementAt(i)).add("");
				((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).add(""+cust_cd);
			}
		}
		
		////System.out.println("max_count_for_column = "+max_count_for_column);
		
		/*for(int i=0; i<SN_NO.size(); i++)
		{
			//System.out.println("SN_NO size = "+((Vector)SN_NO.elementAt(i)).size());
			//System.out.println("FGSA_NO size = "+((Vector)FGSA_NO.elementAt(i)).size());
			//System.out.println("TCQ size = "+((Vector)TCQ.elementAt(i)).size());
			//System.out.println("INNER_CUSTOMER_CD_SN_LOA size = "+((Vector)INNER_CUSTOMER_CD_SN_LOA.elementAt(i)).size());
			//System.out.println("CONT_TYPE size = "+((Vector)CONT_TYPE.elementAt(i)).size());
		}
		
		for(int i=0; i<RE_GAS_NO.size(); i++)
		{
			//System.out.println("RE_GAS_NO size = "+((Vector)RE_GAS_NO.elementAt(i)).size());
			//System.out.println("CAPACITY size = "+((Vector)CAPACITY.elementAt(i)).size());
			//System.out.println("RE_GAS_CARGO_NO size = "+((Vector)RE_GAS_CARGO_NO.elementAt(i)).size());
			//System.out.println("INNER_CUSTOMER_CD_RE_GAS size = "+((Vector)INNER_CUSTOMER_CD_RE_GAS.elementAt(i)).size());
		}			
		
		total_regas=nf3.format(sum_balance_regas);
		total=nf3.format(sum_balance);*/
	}		
	catch(Exception e)
	{
		  //System.out.println("Exception in Databean_MgmtReport--->fetchSendOut()-->"+e);
		  e.printStackTrace();
	}

}


	
	public void fetchaskingrate_live() //2015-09-09
	{
		try
		{

			double conversion_factor_mcm = 38900;
//MD20111220 double multiplying_factor_meter_cube = 0.04847;
			double multiplying_factor_meter_cube = 0.042845;
			//Following (5) Variables are related to SN/LOA Contracts TOTAL ...
			double total_sales_gas_day_mmbtu_qty = 0;
			double total_sales_gas_day_mmscm_qty = 0;			
			double sum_sales_firm_qty = 0;
			double sum_sales_re_qty = 0;
			double sum_sales_re_firm_qty = 0;
			
			//Following (3) Variables are related to Re-GAS Contracts TOTAL ...
			double total_regas_gas_day_mmbtu_qty = 0;
			double total_regas_gas_day_mmscm_qty = 0;
			double total_regas_firm_mmscm_qty = 0;
			
			//Following (2) Variables are related to Future Cargo Arrival TOTAL ...
			double total_expected_delivered_m3_qty = 0;
			double total_expected_delivered_mmscm_qty = 0;
			String saleable_stock_with_re_gas_1="";
			
						
			String query = "";
			
	
		
			
			
			String expected_volume_end_date = "";
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
			
			
							
					
			Vector last_arrival_dt_own1=new Vector(); 
			Vector last_arrival_dt_tp1=new Vector(); 
	       Vector EXP_M3=new Vector();
			//Following Logic Has Been Introduced By Samik Shah On 26th February, 2011 ...
			//Following Logic Fetches Future Caro Arrival Volumes in MMSCM & M3 of LNG ...
			queryString = "SELECT A.CARGO_REF_CD,A.CONFIRM_VOL,NVL(A.UNIT_CD,'1')," +
						  "TO_CHAR(A.DELV_FROM_DT,'dd/mm/yyyy') FROM " +
						  "FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_CARGO_NOMINATION B " +
						  "WHERE A.CARGO_REF_CD=B.CARGO_REF_CD AND (A.DELV_FROM_DT BETWEEN " +
						  "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND " +
						  "B.SHIP_CD IS NOT NULL ORDER BY A.DELV_FROM_DT";
			//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String unit_cd = rset.getString(3)==null?"1":rset.getString(3);
				double mmbtu_qty = 0;
				
				if(unit_cd.trim().equals("1"))
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				else if(unit_cd.trim().equals("2"))
				{
					mmbtu_qty = rset.getString(2)==null?0:(Double.parseDouble(rset.getString(2))*1000000);
				}
				else
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				
				//JHP S
				double temp_qty=Double.parseDouble(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				//JHP E
				
				
				EXP_M3.add(nf2.format(temp_qty));
				//EXP_M3.add(nf2.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				last_arrival_dt_own1.add(rset.getString(4)==null?"":rset.getString(4));//JHP
				
				
			}
			
			
			queryString = "SELECT A.CARGO_REF_NO,A.CUSTOMER_CD,A.SHIP_NAME," +
						  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
						  "FROM FMS7_RE_GAS_CARGO_DTL A " +
						  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
			//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				
				
				last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
				
				double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
				
			//				JHP S
				double temp_qty=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				//JHP E
				
				//EXP_M3.add(nf2.format(mmbtu_qty*multiplying_factor_meter_cube));
				EXP_M3.add(nf2.format(temp_qty));
				
					
				
			}
			
			queryString = "SELECT A.CARGO_REF_NO,A.MAPPING_ID,B.SHIP_NAME," +
			  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
			  "FROM FMS8_LNG_REGAS_CARGO_DTL A , FMS7_SHIP_MST B " +
			  "WHERE A.SHIP_CD=B.SHIP_CD AND A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
				//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					
					//System.out.println("HERE......");
					last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
					
					double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
					
				//				JHP S
					double temp_qty=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
					temp_qty=(temp_qty - (temp_qty % 1000));
					
					//JHP E
					
					//EXP_M3.add(nf2.format(mmbtu_qty*multiplying_factor_meter_cube));
					EXP_M3.add(nf2.format(temp_qty));
					
						
					
				}
			
         consumption_percentage = 2.0;
			
			queryString = "SELECT A.PERCENTAGE " +
						   "FROM FMS7_CONSUMPTION_MST A WHERE " +
						   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
						   "B.EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			
			//System.out.println("Internal Consumption Percentage Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				String int_consumption = rset.getString(1)==null?"":rset.getString(1);
				if(!int_consumption.trim().equals(""))
				{
					consumption_percentage = Double.parseDouble(int_consumption);
				}
			}

			consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
						
		
			double total_stock = 0;
			double t1_d1_dead_stock = 0;
			double t2_d1_dead_stock = 0;
			double total_capacity=0;
			//double conversion_factor_from_m3lng_to_mmscm = 0.0005842;
			double conversion_factor_from_m3lng_to_mmscm = 0.000601;
			double total_dead_stock_mmscm = 19.11;
			double conv_factor_m3_to_mmscm=0;
			
			queryString = "SELECT T1_VOLUME, T1_MMSCM, T2_VOLUME, T2_MMSCM ,T1_CONV_FACTOR_1,T2_CONV_FACTOR_1 " +
						  "FROM FMS7_INVENTORY_LEVEL_DTL WHERE " +
						  "INV_LEVEL_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
			//System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				//t1_m3_of_lng = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
			//	t1_mmscm_of_gas = rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2)));
			//	t2_m3_of_lng = rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3)));
			//	t2_mmscm_of_gas = rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4)));
				total_stock = (rset.getString(1)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(1)))))+(rset.getString(3)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(3)))));
				conv_factor_m3_to_mmscm= ((rset.getString(5)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(5)))))+(rset.getString(6)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(6))))));
			}
			
			queryString = "SELECT A.TANK1_D1_VOLUME, A.TANK2_D1_VOLUME,A.TANK1_T1_VOLUME, A.TANK1_T2_VOLUME FROM FMS7_TANK_MASTER_DTL A WHERE " +
						  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
						  "B.TANK_DTL_DT<=TO_DATE('"+from_date+"','dd/mm/yyyy'))";
			//System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				//t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))))*conversion_factor_from_m3lng_to_mmscm;
				//t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))))*conversion_factor_from_m3lng_to_mmscm;
				
				t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))));
				t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))));
				total_capacity=(rset.getString(3)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(3)))))+(rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4)))));
				//System.out.println("JAIMIN --> Total Dead Stock = "+(t1_d1_dead_stock+t2_d1_dead_stock)+",  Actual Total Stock of TWO Tanks = "+total_stock);
				if(t1_d1_dead_stock>0 && t2_d1_dead_stock>0)
				{
					total_dead_stock_mmscm = Double.parseDouble(nf.format(t1_d1_dead_stock+t2_d1_dead_stock));
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
						
					}
				}
				else
				{
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
						
					}
				}
			}
			else
			{
				if(total_stock>total_dead_stock_mmscm)
				{
					saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));
					//saleable_stock_with_re_gas_1 = nf.format((total_capacity - total_stock )*((100-consumption_percentage)/100));		
					
				}
			}
			
			//System.out.println("JAIMIN --> Total Dead Stock = "+total_capacity+" "+total_stock);
			//System.out.println("saleable_stock_with_re_gas_1:"+saleable_stock_with_re_gas_1);
			
		
			//JHP start
			String temp="";
            Vector last_arrival_dt1=new Vector();
            Vector final_value=new Vector();
            Vector last_arrival_date1=new Vector();
            int total_dt=last_arrival_dt_own1.size()+last_arrival_dt_tp1.size();
            for(int i=0;i<last_arrival_dt_own1.size();i++)
			{
			
				//JHP last_arrival_dt = last_arrival_dt_own.trim();
				
				last_arrival_dt1 .add(last_arrival_dt_own1.elementAt(i).toString().trim());
			}
			for(int i=0;i<last_arrival_dt_tp1.size();i++)
			{
				last_arrival_dt1.add(last_arrival_dt_tp1.elementAt(i).toString().trim());
			
			}
			//JHP END
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy"); 
			Vector a = new Vector(); 
			
			String[] t_m;
			String[] EXP_M3_QTY_Temp;
			String t1="";
			String t2="";
			t_m=new String[total_dt];
			EXP_M3_QTY_Temp=new String[total_dt];
			for(int i=0;i<total_dt;i++)
			{
				t_m[i]=last_arrival_dt1.elementAt(i).toString();
				EXP_M3_QTY_Temp[i]=EXP_M3.elementAt(i).toString();
			}
			/*
			for(int i=0;i<total_dt;i++)
			{		
			//System.out.println("UNSROTED DATE:"+t_m[i]+"UNSORTED EXP_M3_QTY_Temp[i]::"+EXP_M3_QTY_Temp[i]);
			}
			*/
			for(int i = 0; i < total_dt; i++)
			{
				  for(int j = 1; j < (total_dt-i); j++)
				  {
					  
					  if(Double.parseDouble(t_m[j-1].substring(6)) > Double.parseDouble(t_m[j].substring(6)))
					  {  
						  t1 = t_m[j-1];
						  t_m[j-1]=t_m[j];
						  t_m[j]=t1;
						  
						  t2 = EXP_M3_QTY_Temp[j-1];
						  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
						  EXP_M3_QTY_Temp[j]=t2;
					  }
					  else if(Double.parseDouble(t_m[j-1].substring(6))== Double.parseDouble(t_m[j].substring(6)))
					  {
					  if(Double.parseDouble(t_m[j-1].substring(3,5)) > Double.parseDouble(t_m[j].substring(3,5)))
					  {
					  t1 = t_m[j-1];
					  t_m[j-1]=t_m[j];
					  t_m[j]=t1;
					  
					  t2 = EXP_M3_QTY_Temp[j-1];
					  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
					  EXP_M3_QTY_Temp[j]=t2;
					  }
					  else if(Double.parseDouble(t_m[j-1].substring(3,5)) == Double.parseDouble(t_m[j].substring(3,5)))
					  {
						  if(Double.parseDouble(t_m[j-1].substring(0,2)) > Double.parseDouble(t_m[j].substring(0,2)))
						  {
							  t1 = t_m[j-1];
							  t_m[j-1]=t_m[j];
							  t_m[j]=t1;  
							  
							  t2 = EXP_M3_QTY_Temp[j-1];
							  EXP_M3_QTY_Temp[j-1]=EXP_M3_QTY_Temp[j];
							  EXP_M3_QTY_Temp[j]=t2;
						  }
					  }
				  }
				  }
				
			}
			
			for(int i=0;i<total_dt;i++)
			{		
			//System.out.println("SORTED DATE:"+t_m[i]+"SORTED EXP_M3_QTY_Temp[i]::"+EXP_M3_QTY_Temp[i]);
			}
			//JHP Start
			String sum="0";
			Vector v=new Vector();
			for(int i=0;i<total_dt;i++)
			{
				//System.out.println("HERE 2..........");
				
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+t_m[i].toString().trim()+"','DD/MM/YYYY') - (TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')-1) FROM dual";
				//System.out.println("003:"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					//no_of_days = rset.getInt(1);//JHP20120524
					no_of_days = rset.getInt(1)-1;
				}
				sum=""+(Double.parseDouble(sum)+Double.parseDouble(EXP_M3_QTY_Temp[i]));
				if(no_of_days>0)
				{
					//System.out.println("HERE 3.........."+saleable_stock_with_re_gas);
					if(!saleable_stock_with_re_gas.trim().equals(""))
					{
						//System.out.println("HERE 5..........");
						//System.out.println(total_stock+"-"+saleable_stock_with_re_gas_1+"+"+sum+"/"+(no_of_days+0.5)+"conv_factor_m3_to_mmscm::"+conv_factor_m3_to_mmscm/2);
						//temp = nf.format((((Double.parseDouble(saleable_stock_with_re_gas_1.trim())+Double.parseDouble(sum))-total_capacity)/no_of_days)*((conv_factor_m3_to_mmscm/2)/1000000));
//JHP20120426			temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/no_of_days)*((conv_factor_m3_to_mmscm/2)/1000000));
						
//						temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+0.5))*((conv_factor_m3_to_mmscm/2)/1000000));
//						JHP20121121 start
						if(Double.parseDouble(EXP_M3_QTY_Temp[i])>=158000)
						{
						temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+1))*((conv_factor_m3_to_mmscm/2)/1000000));
						}
						else
						{
							temp = nf.format(((Double.parseDouble(sum)-(Double.parseDouble(saleable_stock_with_re_gas_1.trim())))/(no_of_days+0.5))*((conv_factor_m3_to_mmscm/2)/1000000));	
						}
//JHP20121121   END
					
						//System.out.println("JAIMIN::"+temp);
						
						if(Double.parseDouble(temp)<0)
						{
							temp="0";
							final_value.add("0");
							v.add(new Double(temp));
						}
						else
						{
							final_value.add(temp);
							v.add(new Double(temp));
						}
						
					}
				}//JHP20130214 start
				else
				{
					//System.out.println("HERE 4..........");
					temp="0";
					final_value.add("0");
					v.add(new Double(temp));
				}
				//JHP20130214 start
			}
			
			//System.out.println("JHP:"+final_value);
			//JHP End
			
						
			//System.out.println("total_expected_delivered_mmscm_qty_str = "+total_expected_delivered_mmscm_qty_str+",  internal_consumption_on_proposed_volume = "+internal_consumption_on_proposed_volume);
			if(v.size()>0)
			{
			Object obj = Collections.max(v);
			
			asking_rate_required_to_accommodate_cargo= obj.toString();
            //System.out.println("Maximum Element of Java Vector is : " + obj);
		     }
			//System.out.println("vsize.........."+v.size());
			for(int i=0;i<total_dt;i++)
			{
				if(asking_rate_required_to_accommodate_cargo.equals(v.elementAt(i).toString()))
				{
					queryString = "SELECT TO_CHAR(TO_DATE('"+t_m[i].toString().trim()+"','dd/mm/yyyy'),'FMDDTH MONTH') FROM DUAL";
					//System.out.println("002:"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						last_arrival_date=rset.getString(1);
					}
				}
			}
		
			
		}
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchfetchaskingrate()-->"+e);
			  e.printStackTrace();
		}
	}
	
	
	//Following Method For Daily Report Generation Has Been Introduced By Samik Shah On 25th February, 2011 ...
	public void fetchDailyReport_live()
	{
		try
		{
			double conversion_factor_mcm = 38900;
//			MD20111220		 double multiplying_factor_meter_cube = 0.04847;
	    double multiplying_factor_meter_cube = 0.042845;
			//Following (5) Variables are related to SN/LOA Contracts TOTAL ...
			double total_sales_gas_day_mmbtu_qty = 0;
			double total_sales_gas_day_mmscm_qty = 0;			
			double sum_sales_firm_qty = 0;
			double sum_sales_re_qty = 0;
			double sum_sales_re_firm_qty = 0;
			
			//Following (3) Variables are related to Re-GAS Contracts TOTAL ...
			double total_regas_gas_day_mmbtu_qty = 0;
			double total_regas_gas_day_mmscm_qty = 0;
			double total_regas_firm_mmscm_qty = 0;
			
			//Following (2) Variables are related to Future Cargo Arrival TOTAL ...
			double total_expected_delivered_m3_qty = 0;
			double total_expected_delivered_mmscm_qty = 0;
			
			
			Vector V_gas_dates = new Vector();
				//JHP Start			
			String query1="";
			String Temp_dt="";
			String Temp_dt1="";
			int k=7;
			int y=1;
			query1 = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+k+",'dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(query1);
			//System.out.println(query1);
			if(rset.next())
			{
				Temp_dt=rset.getString(1);
			}
			query1 = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+y+",'dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(query1);
			//System.out.println(query1);
			if(rset.next())
			{
				Temp_dt1=rset.getString(1);
			}
			query1="select DISTINCT A.CUSTOMER_CD,A.CONTRACT_TYPE from FMS7_DAILY_ALLOCATION_DTL A where A.GAS_DT between to_date('"+Temp_dt+"','dd/mm/yyyy') and to_date('"+Temp_dt1+"','dd/mm/yyyy') ";  
		//System.out.println("j::"+query1);
			rset = stmt.executeQuery(query1);
			//System.out.println(query1);
			while(rset.next())
			{
				Vparty.add(rset.getString(1));
				Vparty_cont_type.add(rset.getString(2));
			}
			
			//JHP End		
			String query = "";
			
			for(int i=7; i>=1; i--)
			{
				query = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+i+",'dd/mm/yyyy') FROM DUAL";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					V_gas_dates.add(rset.getString(1));
				}
			}
			
			if(V_gas_dates.size()==7)
			{
				daily_graph_from_date = ""+V_gas_dates.elementAt(0);
				daily_graph_to_date = ""+V_gas_dates.elementAt(6);
			}
			
			String gas_day = "";
			
			String end_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))+1);
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				gas_day = rset.getString(1);
			}
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DDth, MONTH YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				selected_month_year = rset.getString(1);
			}
			
			String expected_volume_end_date = "";
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
			
			if(gas_day.trim().length()>=10)
			{
				Vector temp_Cust_CD = new Vector();
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_SN_MST A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') between " +
							   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
							   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
							   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
							   "ORDER BY A.CUSTOMER_CD";
				//System.out.println("Distinct from FMS7_SN_MST = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_LOA_MST A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') between " +
							   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
							   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
							   "A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
							   "ORDER BY A.CUSTOMER_CD";
				//System.out.println("Distinct from FMS7_LOA_MST = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					temp_Cust_CD.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				for(int i=0; i<temp_Cust_CD.size(); i++)
				{
					int cnt = 0;
					for(int j=0; j<CUSTOMER_CD.size(); j++)
					{
						if((""+temp_Cust_CD.elementAt(i)).equals(""+CUSTOMER_CD.elementAt(j)))
						{
							++cnt;
						}
					}
					
					if(cnt==0)
					{
						CUSTOMER_CD.add(""+temp_Cust_CD.elementAt(i));
					}
				}
				
				//Following Logic Is For SN/LOA Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
							  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L')";
				////System.out.println("SN/LOA Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{*/
					//CUSTOMER_CD.add(rset.getString(1)==null?"0":rset.getString(1));
				for(int i=0; i<CUSTOMER_CD.size(); i++)
				{
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to SN/LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_sales_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_sales_gas_day_mmscm_qty += rset1.getString(1)==null?0:(Double.parseDouble(rset1.getString(2))/1000000);
						Flag1_sales=true;
					
					}
					else
					{
						
						QTY_MMBTU.add("");
						QTY_MMSCM.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
					////System.out.println("Customer Name for SN/LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM.add("");
					}
					
					String sales_end_dt = "";
					String re_end_dt = "";
					
					double sales_firm_qty = 0;
					double sales_re_firm_qty = 0;
										
					//Following Query Is Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.SN_NO,A.FGSA_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy') " +
								   "from FMS7_SN_MST A where " +
								   "(A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
								   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
								   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO) " +
								   "ORDER BY A.END_DT";*/
					
					//Following Query Is Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.SN_NO, A.FGSA_NO, TO_CHAR(A.END_DT,'dd/mm/yyyy'), A.FGSA_REV_NO, A.SN_REV_NO " +
								   "from FMS7_SN_MST A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.START_DT AND A.END_DT) OR (A.START_DT BETWEEN " +
								   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND " +
								   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
								   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_NO=B.FGSA_NO AND " +
								   "A.SN_NO=B.SN_NO) ORDER BY A.END_DT";
					//System.out.println("Master Query for FMS7_SN_MST = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						int re_count = 0;
						int clause_cd = 3; //3 Clause CD stands for Existance of Take or Pay Clause in the Supply Notice ...
						
						queryString2 = "select COUNT(A.sn_no) " +
									   "from FMS7_SN_TOP_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.SN_NO='"+rset1.getString(1)+"' and " +
									   "A.SN_REV_NO='"+rset1.getString(5)+"' and " +
									   "A.FGSA_REV_NO='"+rset1.getString(4)+"' and " +
									   "A.CONT_TYPE='S'";
						//System.out.println("Query for checking EXISTANCE of Take or Pay Clause in FMS7_SN_TOP_DTL Table = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							re_count = rset2.getInt(1);
						}
						
						queryString2 = "select A.TCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'), A.FGSA_REV_NO, A.SN_REV_NO " +
									   "from FMS7_SN_MST A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.SN_NO='"+rset1.getString(1)+"' and " +
									   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where " +
									   "A.CUSTOMER_CD=B.CUSTOMER_CD and A.FGSA_NO=B.FGSA_NO and A.SN_NO=B.SN_NO)";
//						//System.out.println("2nd Master Query for FMS7_SN_MST = "+queryString1);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='S' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							sales_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						//System.out.println("tcq = "+tcq+",  alloc_qty = "+alloc_qty);
						if(tcq>alloc_qty)
						{
							if(re_count>0)
							{
								sales_firm_qty += (tcq-alloc_qty);
							}
							else
							{
								sales_re_firm_qty += (tcq-alloc_qty);
							}
						}
					}
					
					//Following Query Is Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.LOA_NO,A.TENDER_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy') " +
								   "from FMS7_LOA_MST A where " +
								   "(A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
								   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
								   "A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO) " +
								   "ORDER BY A.END_DT";*/
					
					//Following Query Is Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.LOA_NO,A.TENDER_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy'),A.LOA_REV_NO " +
								   "from FMS7_LOA_MST A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.START_DT AND A.END_DT) OR (A.START_DT BETWEEN " +
								   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND " +
								   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
								   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.TENDER_NO=B.TENDER_NO AND " +
								   "A.LOA_NO=B.LOA_NO) ORDER BY A.END_DT";
					
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						int re_count = 0;
						int clause_cd = 3; //3 Clause CD stands for Existance of Take or Pay Clause in the Letter of Agreement ...
						
						queryString2 = "select COUNT(A.loa_no) " +
									   "from FMS7_LOA_TOP_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.LOA_NO='"+rset1.getString(1)+"' and " +
									   "A.LOA_REV_NO='"+rset1.getString(4)+"'";
						//System.out.println("Query for checking EXISTANCE of Take or Pay Clause in FMS7_LOA_TOP_DTL Table = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							re_count = rset2.getInt(1);
						}
						
						queryString2 = "select A.TCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_REV_NO " +
									   "from FMS7_LOA_MST A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.TENDER_NO='"+rset1.getString(2)+"' and " +
									   "A.LOA_NO='"+rset1.getString(1)+"' and " +
									   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where " +
									   "A.CUSTOMER_CD=B.CUSTOMER_CD and A.TENDER_NO=B.TENDER_NO and " +
									   "A.LOA_NO=B.LOA_NO)";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='L' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							re_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							if(re_count>0)
							{
								sales_firm_qty += (tcq-alloc_qty);
							}
							else
							{
								sales_re_firm_qty += (tcq-alloc_qty);
							}
						}
					}
					
					if(sales_firm_qty>0)
					{
						QTY_MMSCM_FIRM.add(nf.format(sales_firm_qty/conversion_factor_mcm));
					}
					else
					{
						QTY_MMSCM_FIRM.add("");
					}
					
					sum_sales_firm_qty += Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm));
					
					if(sales_re_firm_qty>0)
					{
						QTY_MMSCM_RE_SALES.add(nf.format(sales_re_firm_qty/conversion_factor_mcm));
					}
					else
					{
						QTY_MMSCM_RE_SALES.add("");
					}
					
					sum_sales_re_qty += Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm));
					
					if(!sales_end_dt.trim().equals("") && !re_end_dt.trim().equals(""))
					{
						//Logic to be developed for Greater from above mentioned TWO Dates ...
						SALES_END_DT.add(""+sales_end_dt.trim());
					}
					else if(!sales_end_dt.trim().equals(""))
					{
						SALES_END_DT.add(""+sales_end_dt.trim());
					}
					else if(!re_end_dt.trim().equals(""))
					{
						SALES_END_DT.add(""+re_end_dt.trim());
					}
					else
					{
						SALES_END_DT.add("");
					}
					
					if((sales_firm_qty+sales_re_firm_qty)>0)
					{
						QTY_MMSCM_TOTAL_FIRM_RE.add(nf.format(Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm))+Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm))));
					}
					else
					{
						QTY_MMSCM_TOTAL_FIRM_RE.add("");
					}
					
					sum_sales_re_firm_qty += Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm))+Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm));					
				}
				
				total_sales_gas_day_mmbtu_qty_str = nf3.format(total_sales_gas_day_mmbtu_qty);
				total_sales_gas_day_mmscm_qty_str = nf.format(total_sales_gas_day_mmscm_qty);			
				sum_sales_firm_qty_str = nf.format(sum_sales_firm_qty);
				sum_sales_re_qty_str = nf.format(sum_sales_re_qty);
				sum_sales_re_firm_qty_str = nf.format(sum_sales_re_firm_qty);
				
				
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_RE_GAS_CARGO_DTL A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
							   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
							   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							   "ORDER BY A.CUSTOMER_CD";
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				//Following Logic Is For Re-Gas Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
				   			  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND CONTRACT_TYPE='R'";
				////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));*/
				for(int i=0; i<CUSTOMER_CD_RE_GAS.size(); i++)
				{					
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND CONTRACT_TYPE='R' AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU_RE_GAS.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_regas_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM_RE_GAS.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_regas_gas_day_mmscm_qty += rset1.getString(1)==null?0:((Double.parseDouble(rset1.getString(2))/1000000));
						Flag2_Re_Gas=true;
					}
					else
					{
						
						QTY_MMBTU_RE_GAS.add("");
						QTY_MMSCM_RE_GAS.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
					////System.out.println("Customer Name for Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM_RE_GAS.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM_RE_GAS.add("");
					}
					
					String re_gas_end_dt = "";
					double regas_expected_mmscm_qty = 0;
					
					//Following Query Has Been Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "(A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' ORDER BY A.CONTRACT_END_DT";*/
					
					//Following Query Has Been Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
								   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
								   "ORDER BY A.CONTRACT_END_DT";
					
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						queryString2 = "select A.QTY_TO_BE_SUPPLY, TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
									   "from FMS7_RE_GAS_CARGO_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' and " +
									   "A.RE_GAS_NO='"+rset1.getString(2)+"' and " +
									   "A.CARGO_SEQ_NO='"+rset1.getString(1)+"'";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='R' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
							//System.out.println("Re-Gas Live Distinct Customer Allocated QTY Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							re_gas_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							regas_expected_mmscm_qty += (tcq-alloc_qty);
						}
					}
					
					RE_GAS_END_DT.add(""+re_gas_end_dt.trim());
					QTY_MMSCM_RE_GAS_FIRM.add(nf.format(regas_expected_mmscm_qty/conversion_factor_mcm));
					
					total_regas_firm_mmscm_qty += Double.parseDouble(nf.format(regas_expected_mmscm_qty/conversion_factor_mcm));
				}
				
				total_regas_gas_day_mmbtu_qty_str = nf3.format(total_regas_gas_day_mmbtu_qty);
				total_regas_gas_day_mmscm_qty_str = nf.format(total_regas_gas_day_mmscm_qty);
				total_regas_firm_mmscm_qty_str = nf.format(total_regas_firm_mmscm_qty);
				
				
				//////////////////////ADDED FOR LTCORA AND CN
				
				////GLOBAL
				
				
				//LOCAL
					double total_LTCORA_gas_day_mmbtu_qty=0;
					double total_LTCORA_gas_day_mmscm_qty=0;
					double total_LTCORA_firm_mmscm_qty=0;
				
				queryString1 = "select DISTINCT(C.CUSTOMER_CD) " +
				   "FROM FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C where  A.MAPPING_ID=C.MAPPING_ID AND " +
				   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
				   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
				   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
				   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
				   "ORDER BY C.CUSTOMER_CD";
				
				/*queryString1 ="select DISTINCT(C.CUSTOMER_CD) from FMS8_LNG_REGAS_CARGO_DTL A, FMS8_LNG_REGAS_MST C " +
						"where (A.CONTRACT_START_DT <= to_date('11/11/2014','dd/mm/yyyy') " +
						"AND A.CONTRACT_END_DT >= to_date('11/11/2014','dd/mm/yyyy'))  AND A.MAPPING_ID=C.MAPPING_ID " +
						" AND C.cn_rev_no=(SELECT MAX(B.CN_REV_NO) FROM FMS8_LNG_REGAS_MST B where " +
						"C.agreement_no=B.agreement_no AND C.customer_cd=B.customer_cd  AND B.CN_NO=C.CN_NO " +
						"AND c.rev_no=B.rev_no AND B.REV_NO=(SELECT MAX(D.REV_NO) FROM FMS8_LNG_REGAS_MST D WHERE " +
						" D.agreement_no=B.agreement_no AND B.customer_cd=D.customer_cd  AND D.agreement_no=C.agreement_no " +
						"AND D.customer_cd=C.customer_cd )) AND a.mapping_id=C.mapping_id " +
						"order by C.CUSTOMER_CD ";*/
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD_LTCORA.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				//Following Logic Is For Re-Gas Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
				   			  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND CONTRACT_TYPE='R'";
				////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));*/
				for(int i=0; i<CUSTOMER_CD_LTCORA.size(); i++)
				{					
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
					//System.out.println("Allocated QTY_MMBTU For The Gas Day related to Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU_LTCORA.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_LTCORA_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM_LTCORA.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_LTCORA_gas_day_mmscm_qty += rset1.getString(1)==null?0:((Double.parseDouble(rset1.getString(2))/1000000));
						Flag2_LTCORA=true;
					}
					else
					{
						
						QTY_MMBTU_LTCORA.add("");
						QTY_MMSCM_LTCORA.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
					////System.out.println("Customer Name for Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM_LTCORA.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM_LTCORA.add("");
					}
					
					String LTCORA_end_dt = "";
					double LTCORA_expected_mmscm_qty = 0;
					
					//Following Query Has Been Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "(A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' ORDER BY A.CONTRACT_END_DT";*/
					
					//Following Query Has Been Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.CARGO_SEQ_NO,C.MAPPING_ID,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY'),A.FLAG  " +
								   "from FMS8_LNG_REGAS_CARGO_DTL A , FMS8_LNG_REGAS_MST C where  A.MAPPING_ID=C.MAPPING_ID AND " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
								   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "C.CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' " +
								   "ORDER BY A.CONTRACT_END_DT";
					//System.out.println("queryString1..........."+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						String temp_map_id=rset1.getString(2)==null?"":rset1.getString(2);
						String map_id[]=temp_map_id.split("-");
						String temp_cont_type=rset1.getString(4)==null?"":rset1.getString(4);
						String fgsa_no="";
						
						if(temp_cont_type.equalsIgnoreCase("T"))
						{
							fgsa_no=map_id[1];
						}
						else if(temp_cont_type.equalsIgnoreCase("C"))
						{
							fgsa_no=map_id[3];
						}
						//System.out.println("fgsa_no........."+fgsa_no+"map_id[2]"+temp_map_id);
						queryString2 = "select A.QTY_TO_BE_SUPPLY, TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
									   "from FMS8_LNG_REGAS_CARGO_DTL A,  FMS8_LNG_REGAS_MST C where " +
									   "C.CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"' " +
									   "AND A.MAPPING_ID=C.MAPPING_ID and " +
									   "A.CARGO_SEQ_NO='"+rset1.getString(1)+"' " +
									   "AND A.MAPPING_ID='"+rset1.getString(2)+"'";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and (CONTRACT_TYPE='T' OR CONTRACT_TYPE='C') and FGSA_NO='"+fgsa_no+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD_LTCORA.elementAt(i)+"'";
							//System.out.println("Re-Gas Live Distinct Customer Allocated QTY Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							LTCORA_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							LTCORA_expected_mmscm_qty += (tcq-alloc_qty);
							
						}
						//System.out.println("tcq......."+tcq+"alloc_qty......"+alloc_qty);
					}
					
					LTCORA_END_DT.add(""+LTCORA_end_dt.trim());
					//System.out.println("LTCORA_expected_mmscm_qty"+LTCORA_expected_mmscm_qty);
					QTY_MMSCM_LTCORA_FIRM.add(nf.format(LTCORA_expected_mmscm_qty/conversion_factor_mcm));
					
					total_LTCORA_firm_mmscm_qty += Double.parseDouble(nf.format(LTCORA_expected_mmscm_qty/conversion_factor_mcm));
				}
				
				total_LTCORA_gas_day_mmbtu_qty_str = nf3.format(total_LTCORA_gas_day_mmbtu_qty);
				total_LTCORA_gas_day_mmscm_qty_str = nf.format(total_LTCORA_gas_day_mmscm_qty);
				total_LTCORA_firm_mmscm_qty_str = nf.format(total_LTCORA_firm_mmscm_qty);
				
				///////////////END OF LTCORA AND CN
				
				
			}		
					
			
			Vector last_arrival_dt_own1=new Vector(); 
			Vector last_arrival_dt_tp1=new Vector(); 
			String last_arrival_dt_own = "";
			String last_arrival_dt_tp = "";
			
			//Following Logic Has Been Introduced By Samik Shah On 26th February, 2011 ...
			//Following Logic Fetches Future Caro Arrival Volumes in MMSCM & M3 of LNG ...
			queryString = "SELECT A.CARGO_REF_CD,A.CONFIRM_VOL,NVL(A.UNIT_CD,'1')," +
						  "TO_CHAR(A.DELV_FROM_DT,'dd/mm/yyyy') FROM " +
						  "FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_CARGO_NOMINATION B " +
						  "WHERE A.CARGO_REF_CD=B.CARGO_REF_CD AND (A.DELV_FROM_DT BETWEEN " +
						  "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND " +
						  "B.SHIP_CD IS NOT NULL ORDER BY A.DELV_FROM_DT";
			//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String unit_cd = rset.getString(3)==null?"1":rset.getString(3);
				double mmbtu_qty = 0;
				
				if(unit_cd.trim().equals("1"))
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				else if(unit_cd.trim().equals("2"))
				{
					mmbtu_qty = rset.getString(2)==null?0:(Double.parseDouble(rset.getString(2))*1000000);
				}
				else
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				
				EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				//EXP_OWN_TP_NM.add("HLPL (OWN)");
				EXP_OWN_TP_NM.add("OWN");
			
				EXP_TIME_OF_ARRIVAL.add(rset.getString(4)==null?"":rset.getString(4));
				//JHP start
	//			EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
				EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
				
				double temp_qty=Double.parseDouble(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				EXP_M3_LNG.add(nf3.format(temp_qty));
				
				total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty));
//JHP20120524	total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
				total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
				//EXP_M3_LNG.add(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				//JHP End	
				
				last_arrival_dt_own = rset.getString(4)==null?"":rset.getString(4);
				last_arrival_dt_own1.add(rset.getString(4)==null?"":rset.getString(4));//JHP
				
				
				String cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);
				
				queryString1 = "SELECT A.SHIP_CD, B.SHIP_NAME FROM FMS7_CARGO_NOMINATION A, FMS7_SHIP_MST B " +
							   "WHERE A.CARGO_REF_CD='"+cargo_ref_no+"' AND A.SHIP_CD=B.SHIP_CD";
				//System.out.println("Inner Query for finding future Vessel List from FMS7_CARGO_NOMINATION = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					EXP_VESSEL_NM.add(rset1.getString(2)==null?"":rset1.getString(2));
				}
				else
				{
					EXP_VESSEL_NM.add("");
				}
			}
			
			
			queryString = "SELECT A.CARGO_REF_NO,A.CUSTOMER_CD,A.SHIP_NAME," +
						  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
						  "FROM FMS7_RE_GAS_CARGO_DTL A " +
						  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
			//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				EXP_VESSEL_NM.add(rset.getString(3)==null?"":rset.getString(3));
				EXP_TIME_OF_ARRIVAL.add(rset.getString(5)==null?"":rset.getString(5));
				
				last_arrival_dt_tp = rset.getString(5)==null?"":rset.getString(5);
				last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
				
				double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
				//				JHP start
			//	EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
				EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
				
				double temp_qty1=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				temp_qty1=Math.floor((temp_qty1 - (temp_qty1 % 100))+ 500d);
				temp_qty1=(temp_qty1 - (temp_qty1 % 1000));
				EXP_M3_LNG.add(nf9.format(temp_qty1 ));
				
				total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty1));
	//JHP20120524			total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
		         total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//EXP_M3_LNG.add(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
//				JHP End			
			
		
				String customer_cd = rset.getString(2)==null?"0":rset.getString(2);
				String customer_nm = "";
				
				queryString1 = "SELECT A.CUSTOMER_NAME, A.CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A " +
							   "WHERE A.CUSTOMER_CD='"+customer_cd+"'";
				//System.out.println("Query for finding Customer Name & Abbr. of future Cargo Owner with respect to RE-Gas Contract = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					customer_nm = rset1.getString(2)==null?"":rset1.getString(2);
					//EXP_OWN_TP_NM.add(customer_nm+" (&nbsp;&nbsp;TP&nbsp;&nbsp;)");
					EXP_OWN_TP_NM.add("TP");
				}
				else
				{
					//EXP_OWN_TP_NM.add("( TP)");
					EXP_OWN_TP_NM.add("TP");
				}
			}
			
			///////ADDED FOR LTCORA AND CN
			queryString = "SELECT A.CARGO_REF_NO,A.MAPPING_ID,B.SHIP_NAME," +
			  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
			  "FROM FMS8_LNG_REGAS_CARGO_DTL A , FMS7_SHIP_MST B " +
			  "WHERE A.SHIP_CD=B.SHIP_CD AND A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
			  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
				//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					String temp_map_id=rset.getString(2)==null?"":rset.getString(2);
					String map_id[]=temp_map_id.split("-");
					
					EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
					EXP_VESSEL_NM.add(rset.getString(3)==null?"":rset.getString(3));
					EXP_TIME_OF_ARRIVAL.add(rset.getString(5)==null?"":rset.getString(5));
					
					last_arrival_dt_tp = rset.getString(5)==null?"":rset.getString(5);
					last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
					
					double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
					//				JHP start
				//	EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
					EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
					
					double temp_qty1=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					temp_qty1=Math.floor((temp_qty1 - (temp_qty1 % 100))+ 500d);
					temp_qty1=(temp_qty1 - (temp_qty1 % 1000));
					EXP_M3_LNG.add(nf9.format(temp_qty1 ));
					
					total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty1));
				//JHP20120524			total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
				   total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
					//EXP_M3_LNG.add(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
					//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
					//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
				//	JHP End			
				
				
					String customer_cd = map_id[0];
					String customer_nm = "";
					
					queryString1 = "SELECT A.CUSTOMER_NAME, A.CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A " +
								   "WHERE A.CUSTOMER_CD='"+customer_cd+"'";
					//System.out.println("Query for finding Customer Name & Abbr. of future Cargo Owner with respect to RE-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						customer_nm = rset1.getString(2)==null?"":rset1.getString(2);
						//EXP_OWN_TP_NM.add(customer_nm+" (&nbsp;&nbsp;TP&nbsp;&nbsp;)");
						EXP_OWN_TP_NM.add("TP");
					}
					else
					{
						//EXP_OWN_TP_NM.add("( TP)");
						EXP_OWN_TP_NM.add("TP");
					}
				}
				/////END			
			
			total_expected_delivered_m3_qty_str = nf3.format(total_expected_delivered_m3_qty);
//JHP20120524			total_expected_delivered_mmscm_qty_str = nf.format(total_expected_delivered_mmscm_qty);
			total_expected_delivered_mmscm_qty_str = nf1.format(total_expected_delivered_mmscm_qty);
						
			//Logic for finding out Total TANK Inventory & Saleable Stock on Daily basis ...
			consumption_percentage = 2.0;
			
			queryString = "SELECT A.PERCENTAGE " +
						   "FROM FMS7_CONSUMPTION_MST A WHERE " +
						   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
						   "B.EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			
			//System.out.println("Internal Consumption Percentage Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				String int_consumption = rset.getString(1)==null?"":rset.getString(1);
				if(!int_consumption.trim().equals(""))
				{
					consumption_percentage = Double.parseDouble(int_consumption);
				}
			}

		//	//System.out.println(".................................................."+consumption_percentage);
			
			consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
			
			double total_stock = 0;
			double t1_d1_dead_stock = 0;
			double t2_d1_dead_stock = 0;
			//double conversion_factor_from_m3lng_to_mmscm = 0.0005842;
			double conversion_factor_from_m3lng_to_mmscm = 0.000601;
			double total_dead_stock_mmscm = 19.11;
			double saleable_with_re = 0;
			
			queryString = "SELECT T1_VOLUME, T1_MMSCM, T2_VOLUME, T2_MMSCM " +
						  "FROM FMS7_INVENTORY_LEVEL_DTL WHERE " +
						  "INV_LEVEL_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
			//System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				t1_m3_of_lng = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
				t1_mmscm_of_gas = rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2)));
				t2_m3_of_lng = rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3)));
				t2_mmscm_of_gas = rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4)));
				total_stock = (rset.getString(2)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(2)))))+(rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4)))));
			}
			
			queryString = "SELECT A.TANK1_D1_VOLUME, A.TANK2_D1_VOLUME FROM FMS7_TANK_MASTER_DTL A WHERE " +
						  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
						  "B.TANK_DTL_DT<=TO_DATE('"+from_date+"','dd/mm/yyyy'))";
			//System.out.println("T1 & T2 Tank Inventory Detail Query here= "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))))*conversion_factor_from_m3lng_to_mmscm;
				t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))))*conversion_factor_from_m3lng_to_mmscm;
				
				//System.out.println("SAMIK --> Total Dead Stock = "+(t1_d1_dead_stock+t2_d1_dead_stock)+",  Actual Total Stock of TWO Tanks = "+total_stock);
				if(t1_d1_dead_stock>0 && t2_d1_dead_stock>0)
				{
					total_dead_stock_mmscm = Double.parseDouble(nf.format(t1_d1_dead_stock+t2_d1_dead_stock));
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
						saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
					}
				}
				else
				{
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
						saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
					}
				}
			}
			else
			{
				if(total_stock>total_dead_stock_mmscm)
				{
					saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
					saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
				}
			}
			
			String start_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))-1);
			
			String cut_off_date = "01/04/2011";
			
			queryString = "SELECT TO_DATE('"+cut_off_date+"','dd/mm/yyyy')-TO_DATE('"+start_date+"','dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				int outer_days_diff_count = rset.getInt(1);
				
				int inner_days_diff_count = 0;
				
				queryString2 = "SELECT TO_DATE('"+from_date+"','dd/mm/yyyy')-TO_DATE('"+cut_off_date+"','dd/mm/yyyy') FROM DUAL";
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					inner_days_diff_count = rset2.getInt(1);
				}
				
				if(outer_days_diff_count>0 && inner_days_diff_count>0)
				{
					start_date = cut_off_date;
				}
			}
						
			double regas_expected_mmscm_qty = 0;
			
			queryString = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.ACTUAL_RECPT_DT,'DD/MM/YYYY')," +
						   "A.CUSTOMER_CD,QTY_TO_BE_SUPPLY " +
						   "from FMS7_RE_GAS_CARGO_DTL A where " +
						   "A.ACTUAL_RECPT_DT between to_date('"+start_date+"','dd/mm/yyyy') and " +
						   "(to_date('"+from_date+"','dd/mm/yyyy')-1)";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				double tcq = 0;
				double alloc_qty = 0;
				
				tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(5)==null?"0":rset.getString(5))));
					
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
				   			   "where GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
							   "and CONTRACT_TYPE='R' and FGSA_NO='"+rset.getString(2)+"' and " +
							   "SN_NO='"+rset.getString(1)+"' and " +
							   "CUSTOMER_CD='"+rset.getString(4)+"'";
				//System.out.println("Re-Gas QTY Supplied to Customer Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1))));
				}
								
				regas_expected_mmscm_qty += (tcq-alloc_qty);				
			}
			
			
			
			
			
			/////////ADDED FOR LTCORA AND CN
				double LTCORA_expected_mmscm_qty = 0;
			
			queryString = "select A.CARGO_SEQ_NO,A.MAPPING_ID,TO_CHAR(A.ACTUAL_RECPT_DT,'DD/MM/YYYY')," +
						   "A.MAPPING_ID,QTY_TO_BE_SUPPLY,FLAG " +
						   "from FMS8_LNG_REGAS_CARGO_DTL A where " +
						   "A.ACTUAL_RECPT_DT between to_date('"+start_date+"','dd/mm/yyyy') and " +
						   "(to_date('"+from_date+"','dd/mm/yyyy')-1)";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String temp_map_id=rset.getString(2)==null?"":rset.getString(2);
				String map_id[]=temp_map_id.split("-");
				String temp_cont_type=rset.getString(6)==null?"":rset.getString(6);
				String fgsa_no="";
				String cust_cd=map_id[0];
				
				if(temp_cont_type.equalsIgnoreCase("T"))
				{
					fgsa_no=map_id[1];
				}
				else if(temp_cont_type.equalsIgnoreCase("C"))
				{
					fgsa_no=map_id[2];
				}
				
				
				
				double tcq = 0;
				double alloc_qty = 0;
				
				tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(5)==null?"0":rset.getString(5))));
					
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
				   			   "where GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
							   "and CONTRACT_TYPE='"+temp_cont_type+"' and FGSA_NO='"+fgsa_no+"' and " +
							   "SN_NO='"+rset.getString(1)+"' and " +
							   "CUSTOMER_CD='"+map_id[1]+"'";
				//System.out.println("LTCORA QTY Supplied to Customer Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1))));
				}
								
				LTCORA_expected_mmscm_qty += (tcq-alloc_qty);				
			}
			
			///////END
			
			
			if(saleable_stock_with_re_gas.trim().equals(""))
			{
				saleable_stock = "";
			}
			else
			{
				saleable_stock = nf.format(Double.parseDouble(saleable_stock_with_re_gas)-Double.parseDouble(nf.format((regas_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100)))-Double.parseDouble(nf.format((LTCORA_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100))));
			}
			
			String last_arrival_dt = "";
			
			if(!last_arrival_dt_own.trim().equals("") && !last_arrival_dt_tp.trim().equals(""))
			{
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+last_arrival_dt_own1.firstElement().toString().trim()+"','DD/MM/YYYY') - TO_DATE('"+last_arrival_dt_tp1.firstElement().toString().trim()+"','DD/MM/YYYY') FROM dual";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					no_of_days = rset.getInt(1);
				}
				
				if(no_of_days<0)
				{
					last_arrival_dt = last_arrival_dt_own1.firstElement().toString().trim();
				}
				else
				{
					last_arrival_dt = last_arrival_dt_tp1.firstElement().toString().trim();
				}
			}
			else if(!last_arrival_dt_own.trim().equals(""))
			{
				last_arrival_dt = last_arrival_dt_own1.firstElement().toString().trim();
			}
			else if(!last_arrival_dt_tp.trim().equals(""))
			{
				last_arrival_dt = last_arrival_dt_tp1.firstElement().toString().trim();
			}
			
			
			if(!last_arrival_dt.trim().equals(""))
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+last_arrival_dt.trim()+"','dd/mm/yyyy'),'FMDDTH MONTH') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				//	last_arrival_date = rset.getString(1);
				}
				
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+last_arrival_dt.trim()+"','DD/MM/YYYY') - (TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')-1) FROM dual";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					no_of_days = rset.getInt(1);
				}
				
				if(no_of_days>0)
				{
					if(!saleable_stock_with_re_gas.trim().equals(""))
					{
						asking_rate_to_avoid_stock_out = nf.format(Double.parseDouble(saleable_stock_with_re_gas.trim())/no_of_days);
					}
				}
			}
			
			
			
			double int_consumption = 0;
			if(!total_expected_delivered_mmscm_qty_str.trim().equals(""))
			{
//JHP20120524				//internal_consumption_on_proposed_volume = nf.format(Double.parseDouble(total_expected_delivered_mmscm_qty_str.trim())*(consumption_percentage/100));
//JHP20120524				//int_consumption = Double.parseDouble(total_expected_delivered_mmscm_qty_str.trim())*(consumption_percentage/100);
				
				internal_consumption_on_proposed_volume = nf.format(total_expected_delivered_mmscm_qty*(consumption_percentage/100));
				int_consumption = total_expected_delivered_mmscm_qty*(consumption_percentage/100);
		}
			
			double long_short_pos = (saleable_with_re+total_expected_delivered_mmscm_qty)-(sum_sales_firm_qty+total_regas_firm_mmscm_qty+int_consumption);
			
			if(long_short_pos>0)
			{
				long_short_position_mmscm = nf.format(long_short_pos);
			}
			else if(long_short_pos<0)
			{
				long_short_position_mmscm = "("+nf.format(long_short_pos*(-1))+")";
			}
						
			
			
		}	
			
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchSendOut()-->"+e);
			  e.printStackTrace();
		}
	}
	
	public void fetchDailyReport_OLD()
	{
		try
		{
			double conversion_factor_mcm = 38900;
//			MD20111220		 double multiplying_factor_meter_cube = 0.04847;
	    double multiplying_factor_meter_cube = 0.042845;
			//Following (5) Variables are related to SN/LOA Contracts TOTAL ...
			double total_sales_gas_day_mmbtu_qty = 0;
			double total_sales_gas_day_mmscm_qty = 0;			
			double sum_sales_firm_qty = 0;
			double sum_sales_re_qty = 0;
			double sum_sales_re_firm_qty = 0;
			
			//Following (3) Variables are related to Re-GAS Contracts TOTAL ...
			double total_regas_gas_day_mmbtu_qty = 0;
			double total_regas_gas_day_mmscm_qty = 0;
			double total_regas_firm_mmscm_qty = 0;
			
			//Following (2) Variables are related to Future Cargo Arrival TOTAL ...
			double total_expected_delivered_m3_qty = 0;
			double total_expected_delivered_mmscm_qty = 0;
			
			
			Vector V_gas_dates = new Vector();
				//JHP Start			
			String query1="";
			String Temp_dt="";
			String Temp_dt1="";
			int k=7;
			int y=1;
			query1 = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+k+",'dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(query1);
			//System.out.println(query1);
			if(rset.next())
			{
				Temp_dt=rset.getString(1);
			}
			query1 = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+y+",'dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(query1);
			//System.out.println(query1);
			if(rset.next())
			{
				Temp_dt1=rset.getString(1);
			}
			query1="select DISTINCT A.CUSTOMER_CD,A.CONTRACT_TYPE from FMS7_DAILY_ALLOCATION_DTL A where A.GAS_DT between to_date('"+Temp_dt+"','dd/mm/yyyy') and to_date('"+Temp_dt1+"','dd/mm/yyyy') ";  
		//System.out.println("j::"+query1);
			rset = stmt.executeQuery(query1);
			//System.out.println(query1);
			while(rset.next())
			{
				Vparty.add(rset.getString(1));
				Vparty_cont_type.add(rset.getString(2));
			}
			
			//JHP End		
			String query = "";
			
			for(int i=7; i>=1; i--)
			{
				query = "SELECT TO_CHAR(TO_DATE('"+from_date+"','dd/mm/yyyy')-"+i+",'dd/mm/yyyy') FROM DUAL";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					V_gas_dates.add(rset.getString(1));
				}
			}
			
			if(V_gas_dates.size()==7)
			{
				daily_graph_from_date = ""+V_gas_dates.elementAt(0);
				daily_graph_to_date = ""+V_gas_dates.elementAt(6);
			}
			
			String gas_day = "";
			
			String end_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))+1);
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY')-1,'DD/MM/YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				gas_day = rset.getString(1);
			}
			
			queryString = "SELECT TO_CHAR(TO_DATE('"+from_date+"','DD/MM/YYYY'),'DDth, MONTH YYYY') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				selected_month_year = rset.getString(1);
			}
			
			String expected_volume_end_date = "";
			
			if(from_date.trim().length()==10)
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+from_date.trim()+"','dd/mm/yyyy')+60,'dd/mm/yyyy') " +
							  "FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					expected_volume_end_date = rset.getString(1);
				}
			}
			
			if(gas_day.trim().length()>=10)
			{
				Vector temp_Cust_CD = new Vector();
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_SN_MST A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') between " +
							   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
							   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
							   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
							   "ORDER BY A.CUSTOMER_CD";
				//System.out.println("Distinct from FMS7_SN_MST = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_LOA_MST A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') between " +
							   "A.START_DT and A.END_DT) OR (A.START_DT BETWEEN " +
							   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
							   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
							   "A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND A.CUSTOMER_CD=B.CUSTOMER_CD) " +
							   "ORDER BY A.CUSTOMER_CD";
				//System.out.println("Distinct from FMS7_LOA_MST = "+queryString1);
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					temp_Cust_CD.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				for(int i=0; i<temp_Cust_CD.size(); i++)
				{
					int cnt = 0;
					for(int j=0; j<CUSTOMER_CD.size(); j++)
					{
						if((""+temp_Cust_CD.elementAt(i)).equals(""+CUSTOMER_CD.elementAt(j)))
						{
							++cnt;
						}
					}
					
					if(cnt==0)
					{
						CUSTOMER_CD.add(""+temp_Cust_CD.elementAt(i));
					}
				}
				
				//Following Logic Is For SN/LOA Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
							  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L')";
				////System.out.println("SN/LOA Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{*/
					//CUSTOMER_CD.add(rset.getString(1)==null?"0":rset.getString(1));
				for(int i=0; i<CUSTOMER_CD.size(); i++)
				{
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND (CONTRACT_TYPE='S' OR CONTRACT_TYPE='L') AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to SN/LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_sales_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_sales_gas_day_mmscm_qty += rset1.getString(1)==null?0:(Double.parseDouble(rset1.getString(2))/1000000);
						Flag1_sales=true;
					
					}
					else
					{
						
						QTY_MMBTU.add("");
						QTY_MMSCM.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
					////System.out.println("Customer Name for SN/LOA = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM.add("");
					}
					
					String sales_end_dt = "";
					String re_end_dt = "";
					
					double sales_firm_qty = 0;
					double sales_re_firm_qty = 0;
										
					//Following Query Is Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.SN_NO,A.FGSA_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy') " +
								   "from FMS7_SN_MST A where " +
								   "(A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
								   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
								   "A.FGSA_NO=B.FGSA_NO AND A.SN_NO=B.SN_NO) " +
								   "ORDER BY A.END_DT";*/
					
					//Following Query Is Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.SN_NO, A.FGSA_NO, TO_CHAR(A.END_DT,'dd/mm/yyyy'), A.FGSA_REV_NO, A.SN_REV_NO " +
								   "from FMS7_SN_MST A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.START_DT AND A.END_DT) OR (A.START_DT BETWEEN " +
								   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND " +
								   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B WHERE " +
								   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.FGSA_NO=B.FGSA_NO AND " +
								   "A.SN_NO=B.SN_NO) ORDER BY A.END_DT";
					//System.out.println("Master Query for FMS7_SN_MST = "+queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						int re_count = 0;
						int clause_cd = 3; //3 Clause CD stands for Existance of Take or Pay Clause in the Supply Notice ...
						
						queryString2 = "select COUNT(A.sn_no) " +
									   "from FMS7_SN_TOP_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.SN_NO='"+rset1.getString(1)+"' and " +
									   "A.SN_REV_NO='"+rset1.getString(5)+"' and " +
									   "A.FGSA_REV_NO='"+rset1.getString(4)+"' and " +
									   "A.CONT_TYPE='S'";
						//System.out.println("Query for checking EXISTANCE of Take or Pay Clause in FMS7_SN_TOP_DTL Table = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							re_count = rset2.getInt(1);
						}
						
						queryString2 = "select A.TCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'), A.FGSA_REV_NO, A.SN_REV_NO " +
									   "from FMS7_SN_MST A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.SN_NO='"+rset1.getString(1)+"' and " +
									   "A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM FMS7_SN_MST B where " +
									   "A.CUSTOMER_CD=B.CUSTOMER_CD and A.FGSA_NO=B.FGSA_NO and A.SN_NO=B.SN_NO)";
//						//System.out.println("2nd Master Query for FMS7_SN_MST = "+queryString1);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='S' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							sales_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						//System.out.println("tcq = "+tcq+",  alloc_qty = "+alloc_qty);
						if(tcq>alloc_qty)
						{
							if(re_count>0)
							{
								sales_firm_qty += (tcq-alloc_qty);
							}
							else
							{
								sales_re_firm_qty += (tcq-alloc_qty);
							}
						}
					}
					
					//Following Query Is Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.LOA_NO,A.TENDER_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy') " +
								   "from FMS7_LOA_MST A where " +
								   "(A.END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
								   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
								   "A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO) " +
								   "ORDER BY A.END_DT";*/
					
					//Following Query Is Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.LOA_NO,A.TENDER_NO,TO_CHAR(A.END_DT,'dd/mm/yyyy'),A.LOA_REV_NO " +
								   "from FMS7_LOA_MST A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.START_DT AND A.END_DT) OR (A.START_DT BETWEEN " +
								   "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' AND " +
								   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B WHERE " +
								   "A.CUSTOMER_CD=B.CUSTOMER_CD AND A.TENDER_NO=B.TENDER_NO AND " +
								   "A.LOA_NO=B.LOA_NO) ORDER BY A.END_DT";
					
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						int re_count = 0;
						int clause_cd = 3; //3 Clause CD stands for Existance of Take or Pay Clause in the Letter of Agreement ...
						
						queryString2 = "select COUNT(A.loa_no) " +
									   "from FMS7_LOA_TOP_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.FGSA_NO='"+rset1.getString(2)+"' and " +
									   "A.LOA_NO='"+rset1.getString(1)+"' and " +
									   "A.LOA_REV_NO='"+rset1.getString(4)+"'";
						//System.out.println("Query for checking EXISTANCE of Take or Pay Clause in FMS7_LOA_TOP_DTL Table = "+queryString2);
						rset2 = stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							re_count = rset2.getInt(1);
						}
						
						queryString2 = "select A.TCQ, TO_CHAR(A.END_DT,'DD/MM/YYYY'),A.LOA_REV_NO " +
									   "from FMS7_LOA_MST A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"' and " +
									   "A.TENDER_NO='"+rset1.getString(2)+"' and " +
									   "A.LOA_NO='"+rset1.getString(1)+"' and " +
									   "A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM FMS7_LOA_MST B where " +
									   "A.CUSTOMER_CD=B.CUSTOMER_CD and A.TENDER_NO=B.TENDER_NO and " +
									   "A.LOA_NO=B.LOA_NO)";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='L' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD.elementAt(i)+"'";
							////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							re_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							if(re_count>0)
							{
								sales_firm_qty += (tcq-alloc_qty);
							}
							else
							{
								sales_re_firm_qty += (tcq-alloc_qty);
							}
						}
					}
					
					if(sales_firm_qty>0)
					{
						QTY_MMSCM_FIRM.add(nf.format(sales_firm_qty/conversion_factor_mcm));
					}
					else
					{
						QTY_MMSCM_FIRM.add("");
					}
					
					sum_sales_firm_qty += Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm));
					
					if(sales_re_firm_qty>0)
					{
						QTY_MMSCM_RE_SALES.add(nf.format(sales_re_firm_qty/conversion_factor_mcm));
					}
					else
					{
						QTY_MMSCM_RE_SALES.add("");
					}
					
					sum_sales_re_qty += Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm));
					
					if(!sales_end_dt.trim().equals("") && !re_end_dt.trim().equals(""))
					{
						//Logic to be developed for Greater from above mentioned TWO Dates ...
						SALES_END_DT.add(""+sales_end_dt.trim());
					}
					else if(!sales_end_dt.trim().equals(""))
					{
						SALES_END_DT.add(""+sales_end_dt.trim());
					}
					else if(!re_end_dt.trim().equals(""))
					{
						SALES_END_DT.add(""+re_end_dt.trim());
					}
					else
					{
						SALES_END_DT.add("");
					}
					
					if((sales_firm_qty+sales_re_firm_qty)>0)
					{
						QTY_MMSCM_TOTAL_FIRM_RE.add(nf.format(Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm))+Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm))));
					}
					else
					{
						QTY_MMSCM_TOTAL_FIRM_RE.add("");
					}
					
					sum_sales_re_firm_qty += Double.parseDouble(nf.format(sales_re_firm_qty/conversion_factor_mcm))+Double.parseDouble(nf.format(sales_firm_qty/conversion_factor_mcm));					
				}
				
				total_sales_gas_day_mmbtu_qty_str = nf3.format(total_sales_gas_day_mmbtu_qty);
				total_sales_gas_day_mmscm_qty_str = nf.format(total_sales_gas_day_mmscm_qty);			
				sum_sales_firm_qty_str = nf.format(sum_sales_firm_qty);
				sum_sales_re_qty_str = nf.format(sum_sales_re_qty);
				sum_sales_re_firm_qty_str = nf.format(sum_sales_re_firm_qty);
				
				
				
				queryString1 = "select DISTINCT(A.CUSTOMER_CD) " +
							   "from FMS7_RE_GAS_CARGO_DTL A where " +
							   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
							   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
							   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
							   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) " +
							   "ORDER BY A.CUSTOMER_CD";
				rset1=stmt1.executeQuery(queryString1);
				while(rset1.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset1.getString(1)==null?"0":rset1.getString(1));
				}
				
				//Following Logic Is For Re-Gas Contracts ...
				/*queryString = "select DISTINCT(CUSTOMER_CD) from FMS7_DAILY_ALLOCATION_DTL " +
				   			  "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
							  "AND CONTRACT_TYPE='R'";
				////System.out.println("Re-Gas Live Distinct Customer Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					CUSTOMER_CD_RE_GAS.add(rset.getString(1)==null?"":rset.getString(1));*/
				for(int i=0; i<CUSTOMER_CD_RE_GAS.size(); i++)
				{					
					queryString1 = "select SUM(QTY_MMBTU),SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL " +
								   "where GAS_DT=TO_DATE('"+gas_day+"','DD/MM/YYYY')" +
								   "AND CONTRACT_TYPE='R' AND " +
								   "CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
					////System.out.println("Allocated QTY_MMBTU For The Gas Day related to Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						QTY_MMBTU_RE_GAS.add(rset1.getString(1)==null?"":nf3.format(Double.parseDouble(rset1.getString(1))));
						total_regas_gas_day_mmbtu_qty += rset1.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1))));
						QTY_MMSCM_RE_GAS.add(rset1.getString(1)==null?"":nf.format(Double.parseDouble(rset1.getString(2))/1000000));
						total_regas_gas_day_mmscm_qty += rset1.getString(1)==null?0:((Double.parseDouble(rset1.getString(2))/1000000));
						Flag2_Re_Gas=true;
					}
					else
					{
						
						QTY_MMBTU_RE_GAS.add("");
						QTY_MMSCM_RE_GAS.add("");
					}
					
					queryString1 = "select CUSTOMER_NAME,CUSTOMER_ABBR from FMS7_CUSTOMER_MST " +
								   "where CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
					////System.out.println("Customer Name for Re-Gas Contract = "+queryString1);
					rset1 = stmt1.executeQuery(queryString1);
					if(rset1.next())
					{
						CUSTOMER_NM_RE_GAS.add(rset1.getString(2)==null?"":rset1.getString(2));				
					}
					else
					{
						CUSTOMER_NM_RE_GAS.add("");
					}
					
					String re_gas_end_dt = "";
					double regas_expected_mmscm_qty = 0;
					
					//Following Query Has Been Commented By Samik Shah On 25th June, 2011 ...
					/*queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "(A.CONTRACT_END_DT between to_date('"+from_date+"','dd/mm/yyyy') and " +
								   "to_date('"+end_date+"','dd/mm/yyyy')) and " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' ORDER BY A.CONTRACT_END_DT";*/
					
					//Following Query Has Been Introduced By Samik Shah On 25th June, 2011 ...
					queryString1 = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
								   "from FMS7_RE_GAS_CARGO_DTL A where " +
								   "((to_date('"+from_date+"','dd/mm/yyyy') BETWEEN " +
								   "A.CONTRACT_START_DT AND A.CONTRACT_END_DT) OR (A.EDQ_FROM_DT " +
								   "BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
								   "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY'))) AND " +
								   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' " +
								   "ORDER BY A.CONTRACT_END_DT";
					
					rset1 = stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						double tcq = 0;
						double alloc_qty = 0;
						
						queryString2 = "select A.QTY_TO_BE_SUPPLY, TO_CHAR(A.CONTRACT_END_DT,'DD/MM/YYYY') " +
									   "from FMS7_RE_GAS_CARGO_DTL A where " +
									   "A.CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"' and " +
									   "A.RE_GAS_NO='"+rset1.getString(2)+"' and " +
									   "A.CARGO_SEQ_NO='"+rset1.getString(1)+"'";
						rset2=stmt2.executeQuery(queryString2);
						if(rset2.next())
						{
							tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset2.getString(1)==null?"0":rset2.getString(1))));
							
							queryString3 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
							   			   "where GAS_DT<=TO_DATE('"+gas_day+"','DD/MM/YYYY') " +
										   "and CONTRACT_TYPE='R' and FGSA_NO='"+rset1.getString(2)+"' and " +
										   "SN_NO='"+rset1.getString(1)+"' and " +
										   "CUSTOMER_CD='"+CUSTOMER_CD_RE_GAS.elementAt(i)+"'";
							//System.out.println("Re-Gas Live Distinct Customer Allocated QTY Query = "+queryString3);
							rset3 = stmt3.executeQuery(queryString3);
							if(rset3.next())
							{
								alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset3.getString(1)==null?"0":rset3.getString(1))));
							}
							
							re_gas_end_dt = rset2.getString(2)==null?"":rset2.getString(2);
						}
						
						if(tcq>alloc_qty)
						{
							regas_expected_mmscm_qty += (tcq-alloc_qty);
						}
					}
					
					RE_GAS_END_DT.add(""+re_gas_end_dt.trim());
					QTY_MMSCM_RE_GAS_FIRM.add(nf.format(regas_expected_mmscm_qty/conversion_factor_mcm));
					
					total_regas_firm_mmscm_qty += Double.parseDouble(nf.format(regas_expected_mmscm_qty/conversion_factor_mcm));
				}
				
				total_regas_gas_day_mmbtu_qty_str = nf3.format(total_regas_gas_day_mmbtu_qty);
				total_regas_gas_day_mmscm_qty_str = nf.format(total_regas_gas_day_mmscm_qty);
				total_regas_firm_mmscm_qty_str = nf.format(total_regas_firm_mmscm_qty);
			}		
					
			
			Vector last_arrival_dt_own1=new Vector(); 
			Vector last_arrival_dt_tp1=new Vector(); 
			String last_arrival_dt_own = "";
			String last_arrival_dt_tp = "";
			
			//Following Logic Has Been Introduced By Samik Shah On 26th February, 2011 ...
			//Following Logic Fetches Future Caro Arrival Volumes in MMSCM & M3 of LNG ...
			queryString = "SELECT A.CARGO_REF_CD,A.CONFIRM_VOL,NVL(A.UNIT_CD,'1')," +
						  "TO_CHAR(A.DELV_FROM_DT,'dd/mm/yyyy') FROM " +
						  "FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_CARGO_NOMINATION B " +
						  "WHERE A.CARGO_REF_CD=B.CARGO_REF_CD AND (A.DELV_FROM_DT BETWEEN " +
						  "TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY')) AND " +
						  "B.SHIP_CD IS NOT NULL ORDER BY A.DELV_FROM_DT";
			//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				String unit_cd = rset.getString(3)==null?"1":rset.getString(3);
				double mmbtu_qty = 0;
				
				if(unit_cd.trim().equals("1"))
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				else if(unit_cd.trim().equals("2"))
				{
					mmbtu_qty = rset.getString(2)==null?0:(Double.parseDouble(rset.getString(2))*1000000);
				}
				else
				{
					mmbtu_qty = rset.getString(2)==null?0:Double.parseDouble(rset.getString(2));
				}
				
				EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				//EXP_OWN_TP_NM.add("HLPL (OWN)");
				EXP_OWN_TP_NM.add("OWN");
			
				EXP_TIME_OF_ARRIVAL.add(rset.getString(4)==null?"":rset.getString(4));
				//JHP start
	//			EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
				EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
				
				double temp_qty=Double.parseDouble(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				temp_qty=Math.floor((temp_qty - (temp_qty % 100))+ 500d);
				temp_qty=(temp_qty - (temp_qty % 1000));
				
				EXP_M3_LNG.add(nf3.format(temp_qty));
				
				total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty));
//JHP20120524	total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
				total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
				//EXP_M3_LNG.add(nf8.format(((mmbtu_qty/conversion_factor_mcm)*1000000)/600));
				//JHP End	
				
				last_arrival_dt_own = rset.getString(4)==null?"":rset.getString(4);
				last_arrival_dt_own1.add(rset.getString(4)==null?"":rset.getString(4));//JHP
				
				
				String cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);
				
				queryString1 = "SELECT A.SHIP_CD, B.SHIP_NAME FROM FMS7_CARGO_NOMINATION A, FMS7_SHIP_MST B " +
							   "WHERE A.CARGO_REF_CD='"+cargo_ref_no+"' AND A.SHIP_CD=B.SHIP_CD";
				//System.out.println("Inner Query for finding future Vessel List from FMS7_CARGO_NOMINATION = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					EXP_VESSEL_NM.add(rset1.getString(2)==null?"":rset1.getString(2));
				}
				else
				{
					EXP_VESSEL_NM.add("");
				}
			}
			
			
			queryString = "SELECT A.CARGO_REF_NO,A.CUSTOMER_CD,A.SHIP_NAME," +
						  "A.ADQ_QTY,TO_CHAR(A.EDQ_FROM_DT,'dd/mm/yyyy') " +
						  "FROM FMS7_RE_GAS_CARGO_DTL A " +
						  "WHERE A.EDQ_FROM_DT BETWEEN TO_DATE('"+from_date+"','DD/MM/YYYY') AND " +
						  "TO_DATE('"+expected_volume_end_date+"','DD/MM/YYYY') ORDER BY A.EDQ_FROM_DT";
			//System.out.println("Query for finding future Cargo List from FMS7_MAN_CONFIRM_CARGO_DTL = "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				EXP_CARGO_REF_NO.add(rset.getString(1)==null?"0":rset.getString(1));
				EXP_VESSEL_NM.add(rset.getString(3)==null?"":rset.getString(3));
				EXP_TIME_OF_ARRIVAL.add(rset.getString(5)==null?"":rset.getString(5));
				
				last_arrival_dt_tp = rset.getString(5)==null?"":rset.getString(5);
				last_arrival_dt_tp1.add(rset.getString(5)==null?"":rset.getString(5));
				
				double mmbtu_qty = rset.getString(4)==null?0:Double.parseDouble(rset.getString(4));
				//				JHP start
			//	EXP_MMSCM_QTY.add(nf.format(mmbtu_qty/conversion_factor_mcm));
				EXP_MMSCM_QTY.add(nf1.format(mmbtu_qty/conversion_factor_mcm));
				
				double temp_qty1=Double.parseDouble(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				temp_qty1=Math.floor((temp_qty1 - (temp_qty1 % 100))+ 500d);
				temp_qty1=(temp_qty1 - (temp_qty1 % 1000));
				EXP_M3_LNG.add(nf9.format(temp_qty1 ));
				
				total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(temp_qty1));
	//JHP20120524			total_expected_delivered_mmscm_qty += Double.parseDouble(nf1.format(mmbtu_qty/conversion_factor_mcm));
		         total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//EXP_M3_LNG.add(nf8.format(mmbtu_qty*multiplying_factor_meter_cube));
				//total_expected_delivered_mmscm_qty += Double.parseDouble(nf.format(mmbtu_qty/conversion_factor_mcm));
				//total_expected_delivered_m3_qty += Double.parseDouble(nf5.format(mmbtu_qty*multiplying_factor_meter_cube));
//				JHP End			
			
		
				String customer_cd = rset.getString(2)==null?"0":rset.getString(2);
				String customer_nm = "";
				
				queryString1 = "SELECT A.CUSTOMER_NAME, A.CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST A " +
							   "WHERE A.CUSTOMER_CD='"+customer_cd+"'";
				//System.out.println("Query for finding Customer Name & Abbr. of future Cargo Owner with respect to RE-Gas Contract = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					customer_nm = rset1.getString(2)==null?"":rset1.getString(2);
					//EXP_OWN_TP_NM.add(customer_nm+" (&nbsp;&nbsp;TP&nbsp;&nbsp;)");
					EXP_OWN_TP_NM.add("TP");
				}
				else
				{
					//EXP_OWN_TP_NM.add("( TP)");
					EXP_OWN_TP_NM.add("TP");
				}
			}
			
			total_expected_delivered_m3_qty_str = nf3.format(total_expected_delivered_m3_qty);
//JHP20120524			total_expected_delivered_mmscm_qty_str = nf.format(total_expected_delivered_mmscm_qty);
			total_expected_delivered_mmscm_qty_str = nf1.format(total_expected_delivered_mmscm_qty);
						
			//Logic for finding out Total TANK Inventory & Saleable Stock on Daily basis ...
			consumption_percentage = 2.0;
			
			queryString = "SELECT A.PERCENTAGE " +
						   "FROM FMS7_CONSUMPTION_MST A WHERE " +
						   "A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
						   "B.EFF_DT<=TO_DATE('"+from_date+"','DD/MM/YYYY'))";
			
			//System.out.println("Internal Consumption Percentage Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				String int_consumption = rset.getString(1)==null?"":rset.getString(1);
				if(!int_consumption.trim().equals(""))
				{
					consumption_percentage = Double.parseDouble(int_consumption);
				}
			}

			consumption_percentage = Double.parseDouble(nf.format(consumption_percentage));
			
			double total_stock = 0;
			double t1_d1_dead_stock = 0;
			double t2_d1_dead_stock = 0;
			//double conversion_factor_from_m3lng_to_mmscm = 0.0005842;
			double conversion_factor_from_m3lng_to_mmscm = 0.000601;
			double total_dead_stock_mmscm = 19.11;
			double saleable_with_re = 0;
			
			queryString = "SELECT T1_VOLUME, T1_MMSCM, T2_VOLUME, T2_MMSCM " +
						  "FROM FMS7_INVENTORY_LEVEL_DTL WHERE " +
						  "INV_LEVEL_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
			//System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				t1_m3_of_lng = rset.getString(1)==null?"":nf3.format(Double.parseDouble(rset.getString(1)));
				t1_mmscm_of_gas = rset.getString(2)==null?"":nf.format(Double.parseDouble(rset.getString(2)));
				t2_m3_of_lng = rset.getString(3)==null?"":nf3.format(Double.parseDouble(rset.getString(3)));
				t2_mmscm_of_gas = rset.getString(4)==null?"":nf.format(Double.parseDouble(rset.getString(4)));
				total_stock = (rset.getString(2)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(2)))))+(rset.getString(4)==null?0:Double.parseDouble(nf.format(Double.parseDouble(rset.getString(4)))));
			}
			
			queryString = "SELECT A.TANK1_D1_VOLUME, A.TANK2_D1_VOLUME FROM FMS7_TANK_MASTER_DTL A WHERE " +
						  "A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
						  "B.TANK_DTL_DT<=TO_DATE('"+from_date+"','dd/mm/yyyy'))";
			//System.out.println("T1 & T2 Tank Inventory Detail Query = "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				t1_d1_dead_stock = rset.getString(1)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(1))))*conversion_factor_from_m3lng_to_mmscm;
				t2_d1_dead_stock = rset.getString(2)==null?0:Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(2))))*conversion_factor_from_m3lng_to_mmscm;
				
				//System.out.println("SAMIK --> Total Dead Stock = "+(t1_d1_dead_stock+t2_d1_dead_stock)+",  Actual Total Stock of TWO Tanks = "+total_stock);
				if(t1_d1_dead_stock>0 && t2_d1_dead_stock>0)
				{
					total_dead_stock_mmscm = Double.parseDouble(nf.format(t1_d1_dead_stock+t2_d1_dead_stock));
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
						saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
					}
				}
				else
				{
					if(total_stock>total_dead_stock_mmscm)
					{
						saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
						saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
					}
				}
			}
			else
			{
				if(total_stock>total_dead_stock_mmscm)
				{
					saleable_stock_with_re_gas = nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100));
					saleable_with_re = Double.parseDouble(nf.format((total_stock - total_dead_stock_mmscm)*((100-consumption_percentage)/100)));
				}
			}
			
			String start_date = from_date.substring(0,6)+(Integer.parseInt(from_date.substring(6))-1);
			
			String cut_off_date = "01/04/2011";
			
			queryString = "SELECT TO_DATE('"+cut_off_date+"','dd/mm/yyyy')-TO_DATE('"+start_date+"','dd/mm/yyyy') FROM DUAL";
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				int outer_days_diff_count = rset.getInt(1);
				
				int inner_days_diff_count = 0;
				
				queryString2 = "SELECT TO_DATE('"+from_date+"','dd/mm/yyyy')-TO_DATE('"+cut_off_date+"','dd/mm/yyyy') FROM DUAL";
				rset2 = stmt2.executeQuery(queryString2);
				if(rset2.next())
				{
					inner_days_diff_count = rset2.getInt(1);
				}
				
				if(outer_days_diff_count>0 && inner_days_diff_count>0)
				{
					start_date = cut_off_date;
				}
			}
						
			double regas_expected_mmscm_qty = 0;
			
			queryString = "select A.CARGO_SEQ_NO,A.RE_GAS_NO,TO_CHAR(A.ACTUAL_RECPT_DT,'DD/MM/YYYY')," +
						   "A.CUSTOMER_CD,QTY_TO_BE_SUPPLY " +
						   "from FMS7_RE_GAS_CARGO_DTL A where " +
						   "A.ACTUAL_RECPT_DT between to_date('"+start_date+"','dd/mm/yyyy') and " +
						   "(to_date('"+from_date+"','dd/mm/yyyy')-1)";
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				double tcq = 0;
				double alloc_qty = 0;
				
				tcq = Double.parseDouble(nf5.format(Double.parseDouble(rset.getString(5)==null?"0":rset.getString(5))));
					
				queryString1 = "select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL " +
				   			   "where GAS_DT<TO_DATE('"+from_date+"','DD/MM/YYYY') " +
							   "and CONTRACT_TYPE='R' and FGSA_NO='"+rset.getString(2)+"' and " +
							   "SN_NO='"+rset.getString(1)+"' and " +
							   "CUSTOMER_CD='"+rset.getString(4)+"'";
				//System.out.println("Re-Gas QTY Supplied to Customer Query = "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					alloc_qty = Double.parseDouble(nf5.format(Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1))));
				}
								
				regas_expected_mmscm_qty += (tcq-alloc_qty);				
			}
			
			if(saleable_stock_with_re_gas.trim().equals(""))
			{
				saleable_stock = "";
			}
			else
			{
				saleable_stock = nf.format(Double.parseDouble(saleable_stock_with_re_gas)-Double.parseDouble(nf.format((regas_expected_mmscm_qty/conversion_factor_mcm)*((100-consumption_percentage)/100))));
			}
			
			String last_arrival_dt = "";
			
			if(!last_arrival_dt_own.trim().equals("") && !last_arrival_dt_tp.trim().equals(""))
			{
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+last_arrival_dt_own1.firstElement().toString().trim()+"','DD/MM/YYYY') - TO_DATE('"+last_arrival_dt_tp1.firstElement().toString().trim()+"','DD/MM/YYYY') FROM dual";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					no_of_days = rset.getInt(1);
				}
				
				if(no_of_days<0)
				{
					last_arrival_dt = last_arrival_dt_own1.firstElement().toString().trim();
				}
				else
				{
					last_arrival_dt = last_arrival_dt_tp1.firstElement().toString().trim();
				}
			}
			else if(!last_arrival_dt_own.trim().equals(""))
			{
				last_arrival_dt = last_arrival_dt_own1.firstElement().toString().trim();
			}
			else if(!last_arrival_dt_tp.trim().equals(""))
			{
				last_arrival_dt = last_arrival_dt_tp1.firstElement().toString().trim();
			}
			
			
			if(!last_arrival_dt.trim().equals(""))
			{
				queryString = "SELECT TO_CHAR(TO_DATE('"+last_arrival_dt.trim()+"','dd/mm/yyyy'),'FMDDTH MONTH') FROM DUAL";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
				//	last_arrival_date = rset.getString(1);
				}
				
				int no_of_days = 0;
				queryString = "SELECT TO_DATE('"+last_arrival_dt.trim()+"','DD/MM/YYYY') - (TO_DATE('"+from_date.trim()+"','DD/MM/YYYY')-1) FROM dual";
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					no_of_days = rset.getInt(1);
				}
				
				if(no_of_days>0)
				{
					if(!saleable_stock_with_re_gas.trim().equals(""))
					{
						asking_rate_to_avoid_stock_out = nf.format(Double.parseDouble(saleable_stock_with_re_gas.trim())/no_of_days);
					}
				}
			}
			
			
			
			double int_consumption = 0;
			if(!total_expected_delivered_mmscm_qty_str.trim().equals(""))
			{
//JHP20120524				//internal_consumption_on_proposed_volume = nf.format(Double.parseDouble(total_expected_delivered_mmscm_qty_str.trim())*(consumption_percentage/100));
//JHP20120524				//int_consumption = Double.parseDouble(total_expected_delivered_mmscm_qty_str.trim())*(consumption_percentage/100);
				
				internal_consumption_on_proposed_volume = nf.format(total_expected_delivered_mmscm_qty*(consumption_percentage/100));
				int_consumption = total_expected_delivered_mmscm_qty*(consumption_percentage/100);
		}
			
			double long_short_pos = (saleable_with_re+total_expected_delivered_mmscm_qty)-(sum_sales_firm_qty+total_regas_firm_mmscm_qty+int_consumption);
			
			if(long_short_pos>0)
			{
				long_short_position_mmscm = nf.format(long_short_pos);
			}
			else if(long_short_pos<0)
			{
				long_short_position_mmscm = "("+nf.format(long_short_pos*(-1))+")";
			}
						
			
			
		}	
			
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchSendOut()-->"+e);
			  e.printStackTrace();
		}
	}
	
	
	
	
	public void fetchRLngImportFinYearState()
	{
		try
		{								
		  mnth="04";
		  String mm="04";
		  String yy="";
		  String year1="";
		  //--Re-Seller
		  double qty_mmbtu1=0.0;
		  double qty_mmbtu2=0.0;
		  double qty_mmbtu3=0.0;
		  double qty_mmbtu4=0.0;
		  
		  double guj_qty_mmbtu1=0.0;
		  double guj_qty_mmbtu2=0.0;
		  double guj_qty_mmbtu3=0.0;
		  double guj_qty_mmbtu4=0.0;		  		  
		  
		  double qty_scm1=0.0;
		  double qty_scm2=0.0;
		  double qty_scm3=0.0;
		  double qty_scm4=0.0;
		  
		  double guj_qty_scm1=0.0;
		  double guj_qty_scm2=0.0;
		  double guj_qty_scm3=0.0;
		  double guj_qty_scm4=0.0;
		  
		  double qty_mt1=0.0;
		  double qty_mt2=0.0;
		  double qty_mt3=0.0;
		  double qty_mt4=0.0;
		  
		  double guj_qty_mt1=0.0;
		  double guj_qty_mt2=0.0;
		  double guj_qty_mt3=0.0;
		  double guj_qty_mt4=0.0;
		  //--Tea Plantation
		  double con_qty_mmbtu1=0.0;
		  double con_qty_mmbtu2=0.0;
		  double con_qty_mmbtu3=0.0;
		  double con_qty_mmbtu4=0.0;
		  
		  double guj_qty_con_mmbtu1=0.0;
		  double guj_qty_con_mmbtu2=0.0;
		  double guj_qty_con_mmbtu3=0.0;
		  double guj_qty_con_mmbtu4=0.0;
		  
		  double con_qty_mt1=0.0;
		  double con_qty_mt2=0.0;
		  double con_qty_mt3=0.0;
		  double con_qty_mt4=0.0;
		  
		  double guj_qty_con_mt1=0.0;
		  double guj_qty_con_mt2=0.0;
		  double guj_qty_con_mt3=0.0;
		  double guj_qty_con_mt4=0.0;
		  
		  double qty_con_scm1=0.0;
		  double qty_con_scm2=0.0;
		  double qty_con_scm3=0.0;
		  double qty_con_scm4=0.0;
		  
		  double guj_qty_con_scm1=0.0;
		  double guj_qty_con_scm2=0.0;
		  double guj_qty_con_scm3=0.0;
		  double guj_qty_con_scm4=0.0;
		  //--Power Sector
		  double pow_qty_mmbtu1=0.0;
		  double pow_qty_mmbtu2=0.0;
		  double pow_qty_mmbtu3=0.0;
		  double pow_qty_mmbtu4=0.0;
		  
		  double guj_qty_pow_mmbtu1=0.0;
		  double guj_qty_pow_mmbtu2=0.0;
		  double guj_qty_pow_mmbtu3=0.0;
		  double guj_qty_pow_mmbtu4=0.0;
		  
		  double pow_qty_mt1=0.0;
		  double pow_qty_mt2=0.0;
		  double pow_qty_mt3=0.0;
		  double pow_qty_mt4=0.0;
		  
		  double guj_qty_pow_mt1=0.0;
		  double guj_qty_pow_mt2=0.0;
		  double guj_qty_pow_mt3=0.0;
		  double guj_qty_pow_mt4=0.0;
		  
		  double pow_qty_scm1=0.0;
		  double pow_qty_scm2=0.0;
		  double pow_qty_scm3=0.0;
		  double pow_qty_scm4=0.0;
		  
		  double guj_qty_pow_scm1=0.0;
		  double guj_qty_pow_scm2=0.0;
		  double guj_qty_pow_scm3=0.0;
		  double guj_qty_pow_scm4=0.0;
		  
		  //--Fertiliser
		  double fer_qty_mmbtu1=0.0;
		  double fer_qty_mmbtu2=0.0;
		  double fer_qty_mmbtu3=0.0;
		  double fer_qty_mmbtu4=0.0;
		  
		  double guj_qty_fer_mmbtu1=0.0;
		  double guj_qty_fer_mmbtu2=0.0;
		  double guj_qty_fer_mmbtu3=0.0;
		  double guj_qty_fer_mmbtu4=0.0;
		  
		  double fer_qty_mt1=0.0;
		  double fer_qty_mt2=0.0;
		  double fer_qty_mt3=0.0;
		  double fer_qty_mt4=0.0;
		  
		  double guj_qty_fer_mt1=0.0;
		  double guj_qty_fer_mt2=0.0;
		  double guj_qty_fer_mt3=0.0;
		  double guj_qty_fer_mt4=0.0;
		  
		  double fer_qty_scm1=0.0;
		  double fer_qty_scm2=0.0;
		  double fer_qty_scm3=0.0;
		  double fer_qty_scm4=0.0;
		  
		  double guj_qty_fer_scm1=0.0;
		  double guj_qty_fer_scm2=0.0;
		  double guj_qty_fer_scm3=0.0;
		  double guj_qty_fer_scm4=0.0;
		  
		  //--Petroleum
		  double pet_qty_mmbtu1=0.0;
		  double pet_qty_mmbtu2=0.0;
		  double pet_qty_mmbtu3=0.0;
		  double pet_qty_mmbtu4=0.0;
		  
		  double guj_qty_pet_mmbtu1=0.0;
		  double guj_qty_pet_mmbtu2=0.0;
		  double guj_qty_pet_mmbtu3=0.0;
		  double guj_qty_pet_mmbtu4=0.0;
		  
		  double pet_qty_mt1=0.0;
		  double pet_qty_mt2=0.0;
		  double pet_qty_mt3=0.0;
		  double pet_qty_mt4=0.0;
		  
		  double guj_qty_pet_mt1=0.0;
		  double guj_qty_pet_mt2=0.0;
		  double guj_qty_pet_mt3=0.0;
		  double guj_qty_pet_mt4=0.0;
		  
		  double pet_qty_scm1=0.0;
		  double pet_qty_scm2=0.0;
		  double pet_qty_scm3=0.0;
		  double pet_qty_scm4=0.0;
		  
		  double guj_qty_pet_scm1=0.0;
		  double guj_qty_pet_scm2=0.0;
		  double guj_qty_pet_scm3=0.0;
		  double guj_qty_pet_scm4=0.0;
		  
		  //--Refineries
		  double ref_qty_mmbtu1=0.0;
		  double ref_qty_mmbtu2=0.0;
		  double ref_qty_mmbtu3=0.0;
		  double ref_qty_mmbtu4=0.0;
		  
		  double guj_qty_ref_mmbtu1=0.0;
		  double guj_qty_ref_mmbtu2=0.0;
		  double guj_qty_ref_mmbtu3=0.0;
		  double guj_qty_ref_mmbtu4=0.0;
		  
		  double ref_qty_mt1=0.0;
		  double ref_qty_mt2=0.0;
		  double ref_qty_mt3=0.0;
		  double ref_qty_mt4=0.0;
		  
		  double guj_qty_ref_mt1=0.0;
		  double guj_qty_ref_mt2=0.0;
		  double guj_qty_ref_mt3=0.0;
		  double guj_qty_ref_mt4=0.0;
		  
		  double ref_qty_scm1=0.0;
		  double ref_qty_scm2=0.0;
		  double ref_qty_scm3=0.0;
		  double ref_qty_scm4=0.0;
		  
		  double guj_qty_ref_scm1=0.0;
		  double guj_qty_ref_scm2=0.0;
		  double guj_qty_ref_scm3=0.0;
		  double guj_qty_ref_scm4=0.0;
		  
		  //--Sponge Iron
		  double irn_qty_mmbtu1=0.0;
		  double irn_qty_mmbtu2=0.0;
		  double irn_qty_mmbtu3=0.0;
		  double irn_qty_mmbtu4=0.0;
		  
		  double guj_qty_irn_mmbtu1=0.0;
		  double guj_qty_irn_mmbtu2=0.0;
		  double guj_qty_irn_mmbtu3=0.0;
		  double guj_qty_irn_mmbtu4=0.0;
		  
		  double irn_qty_mt1=0.0;
		  double irn_qty_mt2=0.0;
		  double irn_qty_mt3=0.0;
		  double irn_qty_mt4=0.0;
		  
		  double guj_qty_irn_mt1=0.0;
		  double guj_qty_irn_mt2=0.0;
		  double guj_qty_irn_mt3=0.0;
		  double guj_qty_irn_mt4=0.0;
		  
		  double irn_qty_scm1=0.0;
		  double irn_qty_scm2=0.0;
		  double irn_qty_scm3=0.0;
		  double irn_qty_scm4=0.0;
		  
		  double guj_qty_irn_scm1=0.0;
		  double guj_qty_irn_scm2=0.0;
		  double guj_qty_irn_scm3=0.0;
		  double guj_qty_irn_scm4=0.0;
		  
		  //--Others
		  double oth_qty_mmbtu1=0.0;
		  double oth_qty_mmbtu2=0.0;
		  double oth_qty_mmbtu3=0.0;
		  double oth_qty_mmbtu4=0.0;
		  
		  double guj_qty_oth_mmbtu1=0.0;
		  double guj_qty_oth_mmbtu2=0.0;
		  double guj_qty_oth_mmbtu3=0.0;
		  double guj_qty_oth_mmbtu4=0.0;
		  
		  double oth_qty_mt1=0.0;
		  double oth_qty_mt2=0.0;
		  double oth_qty_mt3=0.0;
		  double oth_qty_mt4=0.0;
		  
		  double guj_qty_oth_mt1=0.0;
		  double guj_qty_oth_mt2=0.0;
		  double guj_qty_oth_mt3=0.0;
		  double guj_qty_oth_mt4=0.0;
		  
		  double oth_qty_scm1=0.0;
		  double oth_qty_scm2=0.0;
		  double oth_qty_scm3=0.0;
		  double oth_qty_scm4=0.0;
		  
		  double guj_qty_oth_scm1=0.0;
		  double guj_qty_oth_scm2=0.0;
		  double guj_qty_oth_scm3=0.0;
		  double guj_qty_oth_scm4=0.0;
		  
		  if(!year.trim().equals("0") && !year.trim().equals(""))
		  {			
			for(int i=0;i<12;i++)
			{
				yy=year; 
				year1=year;
				mnth=""+(Integer.parseInt(mm)+i);
				
				if (Integer.parseInt(mnth)<=12 )
				{
					month=""+ Integer.parseInt(mnth);					
				}				
				else if(Integer.parseInt(mnth)>12)
				{
					month =""+(Integer.parseInt(mnth)%12);
					mnth=""+(Integer.parseInt(mnth)/12);
					year1=""+ (Integer.parseInt(yy)+1);						
				}					
				////System.out.println("month = "+month+"mnth ="+mnth);//System.out.println("year = "+year+",yy ="+yy+",year1= "+year1);				
				from_date="01"+"/"+month+"/"+year1;
				//////System.out.println("from_date = "+from_date);
				queryString ="select TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
				////System.out.println("Last date = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					to_date=rset.getString(1)==null?"":rset.getString(1);
				}
				else
				{
					to_date="";
				}
				
				//Start for MT & MMBTU for Re-Sellers for Non-Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND " +
				"TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
						//////System.out.println("QTY_MMBTU = "+rset.getString(1));
						if(i<3)
						{
							if(rset.getString(1)!=null)
							{
								qty_mmbtu1+=Double.parseDouble(rset.getString(1));
								if(i==2)
								{
									qty_mt1 =  qty_mmbtu1 * 0.0193;
									QTY_MMBTU.add(nf2.format(qty_mmbtu1));
									QTY_MT.add(nf2.format(qty_mt1));
								}
							}
							else
							{
								if(i==2)
								{									
									if(qty_mmbtu1==0.0 && qty_mmbtu1==0)
									{
										QTY_MMBTU.add("-");
										QTY_MT.add("-");
									}
									else
									{
										qty_mt1 =  qty_mmbtu1 * 0.0193;
										QTY_MMBTU.add(nf2.format(qty_mmbtu1));
										QTY_MT.add(nf2.format(qty_mt1));
									}
								}
							}
						}
						else if(i>=3 && i<6)
						{
							if(rset.getString(1)!=null)
							{
								qty_mmbtu2+=Double.parseDouble(rset.getString(1));
								if(i==5)
								{
									qty_mt2 =  qty_mmbtu2 * 0.0193;
									QTY_MMBTU.add(nf2.format(qty_mmbtu2));
									QTY_MT.add(nf2.format(qty_mt2));
								}
							}
							else
							{
								if(i==5)
								{
									if(qty_mmbtu2==0.0 && qty_mmbtu2==0)
									{
										QTY_MMBTU.add("-");
										QTY_MT.add("-");
									}
									else
									{
										qty_mt2 =  qty_mmbtu2 * 0.0193;
										QTY_MMBTU.add(nf2.format(qty_mmbtu2));
										QTY_MT.add(nf2.format(qty_mt2));
									}
								}
							}
						}
						else if(i>=6 && i<9)
						{
							if(rset.getString(1)!=null)
							{
								qty_mmbtu3+=Double.parseDouble(rset.getString(1));
								if(i==8)
								{
									qty_mt3 =  qty_mmbtu3 * 0.0193;
									QTY_MMBTU.add(nf2.format(qty_mmbtu3));
									QTY_MT.add(nf2.format(qty_mt3));
								}
							}
							else
							{
								if(i==8)
								{
									if(qty_mmbtu3==0.0 && qty_mmbtu3==0)
									{
										QTY_MMBTU.add("-");
										QTY_MT.add("-");
									}
									else
									{
										qty_mt3 =  qty_mmbtu3 * 0.0193;
										QTY_MMBTU.add(nf2.format(qty_mmbtu3));
										QTY_MT.add(nf2.format(qty_mt3));
									}
								}
							}
						}
						else if(i>=9 && i<12)
						{
							if(rset.getString(1)!=null)
							{
								qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
								if(i==11)
								{
									qty_mt4 =  qty_mmbtu4 * 0.0193;
									QTY_MMBTU.add(nf2.format(qty_mmbtu4));
									QTY_MT.add(nf2.format(qty_mt4));
								}
							}
							else
							{
								if(i==11)
								{
									if(qty_mmbtu4==0.0 && qty_mmbtu4==0)
									{
										QTY_MMBTU.add("-");
										QTY_MT.add("-");
									}
									else
									{
										qty_mt4 =  qty_mmbtu4 * 0.0193;
										QTY_MMBTU.add(nf2.format(qty_mmbtu4));
										QTY_MT.add(nf2.format(qty_mt4));
									}
								}
							}
						}//QTY_MMBTU.add(nf2.format(qty_mmbtu1)+""+nf2.format(qty_mmbtu2)+""+nf2.format(qty_mmbtu4));										
				}
				else
				{
					QTY_MMBTU.add("-");
					QTY_MT.add("-");
				}
				// End of MT of reseller for Non-Gujarat
				// Start for MT & MMBTU for Re-Sellers for Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND " +
				"TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{	
					//////System.out.println("GUJ_QTY_MMBTU = "+rset.getString(1));
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_mmbtu1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								guj_qty_mt1 = guj_qty_mmbtu1 * 0.0193;
								GUJ_QTY_MMBTU.add(nf2.format(guj_qty_mmbtu1));
								GUJ_QTY_MT.add(nf2.format(guj_qty_mt1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_mmbtu1==0.0 && guj_qty_mmbtu1==0)
								{
									GUJ_QTY_MMBTU.add("-");
									GUJ_QTY_MT.add("-");
								}
								else
								{
									guj_qty_mt1 = guj_qty_mmbtu1 * 0.0193;
									GUJ_QTY_MMBTU.add(nf2.format(guj_qty_mmbtu1));
									GUJ_QTY_MT.add(nf2.format(guj_qty_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								guj_qty_mt2 = guj_qty_mmbtu2 * 0.0193;
								GUJ_QTY_MMBTU.add(nf2.format(guj_qty_mmbtu2));
								GUJ_QTY_MT.add(nf2.format(guj_qty_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_mmbtu2==0.0 && guj_qty_mmbtu2==0)
								{
									GUJ_QTY_MMBTU.add("-");
									GUJ_QTY_MT.add("-");
								}
								else
								{
									guj_qty_mt2 = guj_qty_mmbtu2 * 0.0193;
									GUJ_QTY_MMBTU.add(nf2.format(guj_qty_mmbtu2));
									GUJ_QTY_MT.add(nf2.format(guj_qty_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								guj_qty_mt3 = guj_qty_mmbtu3 * 0.0193;
								GUJ_QTY_MMBTU.add(nf2.format(guj_qty_mmbtu3));
								GUJ_QTY_MT.add(nf2.format(guj_qty_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_mmbtu3==0.0 && guj_qty_mmbtu3==0)
								{
									GUJ_QTY_MMBTU.add("-");
									GUJ_QTY_MT.add("-");
								}
								else
								{
									guj_qty_mt3 = guj_qty_mmbtu3 * 0.0193;
									GUJ_QTY_MMBTU.add(nf2.format(guj_qty_mmbtu3));
									GUJ_QTY_MT.add(nf2.format(guj_qty_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								guj_qty_mt4 = guj_qty_mmbtu4 * 0.0193;
								GUJ_QTY_MMBTU.add(nf2.format(guj_qty_mmbtu4));
								GUJ_QTY_MT.add(nf2.format(guj_qty_mt4));
							}							
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_mmbtu4==0.0 && guj_qty_mmbtu4==0)
								{
									GUJ_QTY_MMBTU.add("-");
									GUJ_QTY_MT.add("-");
								}
								else
								{
									guj_qty_mt4 = guj_qty_mmbtu4 * 0.0193;
									GUJ_QTY_MMBTU.add(nf2.format(guj_qty_mmbtu4));
									GUJ_QTY_MT.add(nf2.format(guj_qty_mt4));
								}
							}
						}
					}//QTY_MMBTU.add(nf2.format(qty_mmbtu1)+""+nf2.format(qty_mmbtu2)+""+nf2.format(qty_mmbtu4));												
				}
				else
				{
					GUJ_QTY_MMBTU.add("-");
					GUJ_QTY_MT.add("-");
				}
				//End of Re-Seller for MMBTU & MT of Gujarat
				
				//Start for SCM for Re-Sellers for Non-Gujarat				
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{
					//////System.out.println("QTY_SCM = "+rset.getString(1));
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							qty_scm1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								QTY_SCM.add(nf2.format(qty_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(qty_scm1==0.0 && qty_scm1==0)
								{
									QTY_SCM.add("-");
								}
								else
								{
									QTY_SCM.add(nf2.format(qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							qty_scm2+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==5)
							{
								QTY_SCM.add(nf2.format(qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_scm2==0.0 && qty_scm2==0)
								{
									QTY_SCM.add("-");
								}
								else
								{
									QTY_SCM.add(nf2.format(qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							qty_scm3+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==8)
							{
								QTY_SCM.add(nf2.format(qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_scm3==0.0 && qty_scm3==0)
								{
									QTY_SCM.add("-");
								}
								else
								{
									QTY_SCM.add(nf2.format(qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							qty_scm4+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)); 
							if(i==11)
							{
								QTY_SCM.add(nf2.format(qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_scm4==0.0 && qty_scm4==0)
								{
									QTY_SCM.add("-");
								}
								else
								{
									QTY_SCM.add(nf2.format(qty_scm4));
								}
							}
						}
					}//QTY_SCM.add(rset.getString(1)==null?"-":nf2.format(Double.parseDouble(rset.getString(1))));												
				}
				else
				{
					QTY_SCM.add("-");
				}
				//End of SCM for Reseller of Non-Gujarat
				//Start for SCM for Re-Sellers for Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(GUJ_QTY_SCM)"+queryString);
				if(rset.next())
				{		
					////System.out.println("GUJ_QTY_SCM = "+rset.getString(1));
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								GUJ_QTY_SCM.add(nf2.format(guj_qty_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_scm1==0.0 && guj_qty_scm1==0)
								{
									GUJ_QTY_SCM.add("-");
								}
								else
								{
									GUJ_QTY_SCM.add(nf2.format(guj_qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								GUJ_QTY_SCM.add(nf2.format(guj_qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_scm2==0.0 && guj_qty_scm2==0)
								{
									GUJ_QTY_SCM.add("-");
								}
								else
								{
									GUJ_QTY_SCM.add(nf2.format(guj_qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								GUJ_QTY_SCM.add(nf2.format(guj_qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_scm3==0.0 && guj_qty_scm3==0)
								{
									GUJ_QTY_SCM.add("-");
								}
								else
								{
									GUJ_QTY_SCM.add(nf2.format(guj_qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								GUJ_QTY_SCM.add(nf2.format(guj_qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_scm4==0.0 && guj_qty_scm4==0)
								{
									GUJ_QTY_SCM.add("-");
								}
								else
								{
									GUJ_QTY_SCM.add(nf2.format(guj_qty_scm4));
								}
							}
						}
					}												
				}
				else
				{
					GUJ_QTY_SCM.add("-");
				}
				//End of SCM for Resellers of Gujarat
				//Start for MMBTU & MT for Tea Plantation for Non-Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='400' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							con_qty_mmbtu1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								con_qty_mt1 =  con_qty_mmbtu1 * 0.0193;
								CON_QTY_MMBTU.add(nf2.format(con_qty_mmbtu1));
								CON_QTY_MT.add(nf2.format(con_qty_mt1));
							}
						}
						else
						{
							if(i==2)
							{									
								if(con_qty_mmbtu1==0.0 && con_qty_mmbtu1==0)
								{
									CON_QTY_MMBTU.add("-");
									CON_QTY_MT.add("-");
								}
								else
								{
									con_qty_mt1 =  con_qty_mmbtu1 * 0.0193;
									CON_QTY_MMBTU.add(nf2.format(con_qty_mmbtu1));
									CON_QTY_MT.add(nf2.format(con_qty_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							con_qty_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								con_qty_mt2 =  con_qty_mmbtu2 * 0.0193;
								CON_QTY_MMBTU.add(nf2.format(con_qty_mmbtu2));
								CON_QTY_MT.add(nf2.format(con_qty_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(con_qty_mmbtu2==0.0 && con_qty_mmbtu2==0)
								{
									CON_QTY_MMBTU.add("-");
									CON_QTY_MT.add("-");
								}
								else
								{
									con_qty_mt2 =  con_qty_mmbtu2 * 0.0193;
									CON_QTY_MMBTU.add(nf2.format(con_qty_mmbtu2));
									CON_QTY_MT.add(nf2.format(con_qty_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							con_qty_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								con_qty_mt3 =  con_qty_mmbtu3 * 0.0193;
								CON_QTY_MMBTU.add(nf2.format(con_qty_mmbtu3));
								CON_QTY_MT.add(nf2.format(con_qty_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(con_qty_mmbtu3==0.0 && con_qty_mmbtu3==0)
								{
									CON_QTY_MMBTU.add("-");
									CON_QTY_MT.add("-");
								}
								else
								{
									con_qty_mt3 =  con_qty_mmbtu3 * 0.0193;
									CON_QTY_MMBTU.add(nf2.format(con_qty_mmbtu3));
									CON_QTY_MT.add(nf2.format(con_qty_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							con_qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								con_qty_mt4 =  con_qty_mmbtu4 * 0.0193;
								CON_QTY_MMBTU.add(nf2.format(con_qty_mmbtu4));
								CON_QTY_MT.add(nf2.format(con_qty_mt4));
							}
						}
						else
						{
							if(i==11)
							{
								if(con_qty_mmbtu4==0.0 && con_qty_mmbtu4==0)
								{
									CON_QTY_MMBTU.add("-");
									CON_QTY_MT.add("-");
								}
								else
								{
									con_qty_mt4 =  con_qty_mmbtu4 * 0.0193;
									CON_QTY_MMBTU.add(nf2.format(con_qty_mmbtu4));
									CON_QTY_MT.add(nf2.format(con_qty_mt4));
								}
							}
						}
					}											
				}
				else
				{
					CON_QTY_MMBTU.add("-");
					CON_QTY_MT.add("-");
				}
				//Start for MMBTU & MT for Tea Plantation for Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='400' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_con_mmbtu1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								guj_qty_con_mt1 = guj_qty_con_mmbtu1 * 0.0193;
								GUJ_CON_QTY_MMBTU.add(nf2.format(guj_qty_con_mmbtu1));
								GUJ_CON_QTY_MT.add(nf2.format(guj_qty_con_mt1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_con_mmbtu1==0.0 && guj_qty_con_mmbtu1==0)
								{
									GUJ_CON_QTY_MMBTU.add("-");
									GUJ_CON_QTY_MT.add("-");
								}
								else
								{
									guj_qty_con_mt1 = guj_qty_con_mmbtu1 * 0.0193;
									GUJ_CON_QTY_MMBTU.add(nf2.format(guj_qty_con_mmbtu1));
									GUJ_CON_QTY_MT.add(nf2.format(guj_qty_con_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_con_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								guj_qty_con_mt2 = guj_qty_con_mmbtu2 * 0.0193;
								GUJ_CON_QTY_MMBTU.add(nf2.format(guj_qty_con_mmbtu2));
								GUJ_CON_QTY_MT.add(nf2.format(guj_qty_con_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_con_mmbtu2==0.0 && guj_qty_con_mmbtu2==0)
								{
									GUJ_CON_QTY_MMBTU.add("-");
									GUJ_CON_QTY_MT.add("-");
								}
								else
								{
									guj_qty_con_mt2 = guj_qty_con_mmbtu2 * 0.0193;
									GUJ_CON_QTY_MMBTU.add(nf2.format(guj_qty_con_mmbtu2));
									GUJ_CON_QTY_MT.add(nf2.format(guj_qty_con_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_con_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								guj_qty_con_mt3 = guj_qty_mmbtu3 * 0.0193;
								GUJ_CON_QTY_MMBTU.add(nf2.format(guj_qty_con_mmbtu3));
								GUJ_CON_QTY_MT.add(nf2.format(guj_qty_con_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_con_mmbtu3==0.0 && guj_qty_con_mmbtu3==0)
								{
									GUJ_CON_QTY_MMBTU.add("-");
									GUJ_CON_QTY_MT.add("-");
								}
								else
								{
									guj_qty_con_mt3 = guj_qty_con_mmbtu3 * 0.0193;
									GUJ_CON_QTY_MMBTU.add(nf2.format(guj_qty_con_mmbtu3));
									GUJ_CON_QTY_MT.add(nf2.format(guj_qty_con_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_con_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								guj_qty_con_mt4 = guj_qty_con_mmbtu4 * 0.0193;
								GUJ_CON_QTY_MMBTU.add(nf2.format(guj_qty_con_mmbtu4));
								GUJ_CON_QTY_MT.add(nf2.format(guj_qty_con_mt4));
							}							
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_con_mmbtu4==0.0 && guj_qty_con_mmbtu4==0)
								{
									GUJ_CON_QTY_MMBTU.add("-");
									GUJ_CON_QTY_MT.add("-");
								}
								else
								{
									guj_qty_con_mt4 = guj_qty_con_mmbtu4 * 0.0193;
									GUJ_CON_QTY_MMBTU.add(nf2.format(guj_qty_con_mmbtu4));
									GUJ_CON_QTY_MT.add(nf2.format(guj_qty_con_mt4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_CON_QTY_MMBTU.add("-");
					GUJ_CON_QTY_MT.add("-");
				}
				//Start for SCM for Tea Plantation for Non-Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='400' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							qty_con_scm1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								CON_QTY_SCM.add(nf2.format(qty_con_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(qty_con_scm1==0.0 && qty_con_scm1==0)
								{
									CON_QTY_SCM.add("-");
								}
								else
								{
									CON_QTY_SCM.add(nf2.format(qty_con_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							qty_con_scm2+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==5)
							{
								CON_QTY_SCM.add(nf2.format(qty_con_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(qty_con_scm2==0.0 && qty_con_scm2==0)
								{
									CON_QTY_SCM.add("-");
								}
								else
								{
									CON_QTY_SCM.add(nf2.format(qty_con_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							qty_con_scm3+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==8)
							{
								CON_QTY_SCM.add(nf2.format(qty_con_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(qty_con_scm3==0.0 && qty_con_scm3==0)
								{
									CON_QTY_SCM.add("-");
								}
								else
								{
									CON_QTY_SCM.add(nf2.format(qty_con_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							qty_con_scm4+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)); 
							if(i==11)
							{
								CON_QTY_SCM.add(nf2.format(qty_con_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(qty_con_scm4==0.0 && qty_con_scm4==0)
								{
									CON_QTY_SCM.add("-");
								}
								else
								{
									CON_QTY_SCM.add(nf2.format(qty_con_scm4));
								}
							}
						}
					}											
				}
				else
				{
					CON_QTY_SCM.add("-");
				}
				//Start for SCM for Tea Plantation for Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='400' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_con_scm1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								GUJ_CON_QTY_SCM.add(nf2.format(guj_qty_con_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_con_scm1==0.0 && guj_qty_con_scm1==0)
								{
									GUJ_CON_QTY_SCM.add("-");
								}
								else
								{
									GUJ_CON_QTY_SCM.add(nf2.format(guj_qty_con_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_con_scm2+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==5)
							{
								GUJ_CON_QTY_SCM.add(nf2.format(guj_qty_con_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_con_scm2==0.0 && guj_qty_con_scm2==0)
								{
									GUJ_CON_QTY_SCM.add("-");
								}
								else
								{
									GUJ_CON_QTY_SCM.add(nf2.format(guj_qty_con_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_con_scm3+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==8)
							{
								GUJ_CON_QTY_SCM.add(nf2.format(guj_qty_con_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_con_scm3==0.0 && guj_qty_con_scm3==0)
								{
									GUJ_CON_QTY_SCM.add("-");
								}
								else
								{
									GUJ_CON_QTY_SCM.add(nf2.format(guj_qty_con_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_con_scm4+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)); 
							if(i==11)
							{
								GUJ_CON_QTY_SCM.add(nf2.format(guj_qty_con_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_con_scm4==0.0 && guj_qty_con_scm4==0)
								{
									GUJ_CON_QTY_SCM.add("-");
								}
								else
								{
									GUJ_CON_QTY_SCM.add(nf2.format(guj_qty_con_scm4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_CON_QTY_SCM.add("-");
				}
				//End for SCM for Tea Plantation for Gujarat
				//Start for MMBTU & MT for Power Sector for Non-Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='509' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							pow_qty_mmbtu1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								pow_qty_mt1 =  pow_qty_mmbtu1 * 0.0193;
								POW_QTY_MMBTU.add(nf2.format(pow_qty_mmbtu1));
								POW_QTY_MT.add(nf2.format(pow_qty_mt1));
							}
						}
						else
						{
							if(i==2)
							{									
								if(pow_qty_mmbtu1==0.0 && pow_qty_mmbtu1==0)
								{
									POW_QTY_MMBTU.add("-");
									POW_QTY_MT.add("-");
								}
								else
								{
									pow_qty_mt1 =  pow_qty_mmbtu1 * 0.0193;
									POW_QTY_MMBTU.add(nf2.format(pow_qty_mmbtu1));
									POW_QTY_MT.add(nf2.format(pow_qty_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							pow_qty_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								pow_qty_mt2 =  pow_qty_mmbtu2 * 0.0193;
								POW_QTY_MMBTU .add(nf2.format(pow_qty_mmbtu2));
								POW_QTY_MT.add(nf2.format(pow_qty_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(pow_qty_mmbtu2==0.0 && pow_qty_mmbtu2==0)
								{
									POW_QTY_MMBTU .add("-");
									POW_QTY_MT.add("-");
								}
								else
								{
									pow_qty_mt2 =  pow_qty_mmbtu2 * 0.0193;
									POW_QTY_MMBTU .add(nf2.format(pow_qty_mmbtu2));
									POW_QTY_MT.add(nf2.format(pow_qty_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							pow_qty_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								pow_qty_mt3 =  pow_qty_mmbtu3 * 0.0193;
								POW_QTY_MMBTU .add(nf2.format(pow_qty_mmbtu3));
								POW_QTY_MT.add(nf2.format(pow_qty_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(pow_qty_mmbtu3==0.0 && pow_qty_mmbtu3==0)
								{
									POW_QTY_MMBTU .add("-");
									POW_QTY_MT.add("-");
								}
								else
								{
									pow_qty_mt3 =  pow_qty_mmbtu3 * 0.0193;
									POW_QTY_MMBTU .add(nf2.format(pow_qty_mmbtu3));
									POW_QTY_MT.add(nf2.format(pow_qty_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							pow_qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								pow_qty_mt4 =  pow_qty_mmbtu4 * 0.0193;
								POW_QTY_MMBTU.add(nf2.format(pow_qty_mmbtu4));
								POW_QTY_MT.add(nf2.format(pow_qty_mt4));
							}
						}
						else
						{
							if(i==11)
							{
								if(pow_qty_mmbtu4==0.0 && pow_qty_mmbtu4==0)
								{
									POW_QTY_MMBTU .add("-");
									POW_QTY_MT.add("-");
								}
								else
								{
									pow_qty_mt4 =  pow_qty_mmbtu4 * 0.0193;
									POW_QTY_MMBTU .add(nf2.format(pow_qty_mmbtu4));
									POW_QTY_MT.add(nf2.format(pow_qty_mt4));
								}
							}
						}
					}											
				}
				else
				{
					POW_QTY_MMBTU .add("-");
					POW_QTY_MT.add("-");
				}
				//Start for MMBTU & MT for Power Sector for Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='509' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pow_mmbtu1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								guj_qty_pow_mt1 = guj_qty_pow_mmbtu1 * 0.0193;
								GUJ_POW_QTY_MMBTU.add(nf2.format(guj_qty_pow_mmbtu1));
								GUJ_POW_QTY_MT.add(nf2.format(guj_qty_pow_mt1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_pow_mmbtu1==0.0 && guj_qty_pow_mmbtu1==0)
								{
									GUJ_POW_QTY_MMBTU.add("-");
									GUJ_POW_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pow_mt1 = guj_qty_pow_mmbtu1 * 0.0193;
									GUJ_POW_QTY_MMBTU.add(nf2.format(guj_qty_pow_mmbtu1));
									GUJ_POW_QTY_MT.add(nf2.format(guj_qty_pow_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pow_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								guj_qty_pow_mt2 = guj_qty_pow_mmbtu2 * 0.0193;
								GUJ_POW_QTY_MMBTU.add(nf2.format(guj_qty_pow_mmbtu2));
								GUJ_POW_QTY_MT.add(nf2.format(guj_qty_pow_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_pow_mmbtu2==0.0 && guj_qty_pow_mmbtu2==0)
								{
									GUJ_POW_QTY_MMBTU.add("-");
									GUJ_POW_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pow_mt2 = guj_qty_pow_mmbtu2 * 0.0193;
									GUJ_POW_QTY_MMBTU.add(nf2.format(guj_qty_pow_mmbtu2));
									GUJ_POW_QTY_MT.add(nf2.format(guj_qty_pow_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pow_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								guj_qty_pow_mt3 = guj_qty_mmbtu3 * 0.0193;
								GUJ_POW_QTY_MMBTU.add(nf2.format(guj_qty_pow_mmbtu3));
								GUJ_POW_QTY_MT.add(nf2.format(guj_qty_pow_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_pow_mmbtu3==0.0 && guj_qty_pow_mmbtu3==0)
								{
									GUJ_POW_QTY_MMBTU.add("-");
									GUJ_POW_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pow_mt3 = guj_qty_pow_mmbtu3 * 0.0193;
									GUJ_POW_QTY_MMBTU.add(nf2.format(guj_qty_pow_mmbtu3));
									GUJ_POW_QTY_MT.add(nf2.format(guj_qty_pow_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pow_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								guj_qty_pow_mt4 = guj_qty_pow_mmbtu4 * 0.0193;
								GUJ_POW_QTY_MMBTU.add(nf2.format(guj_qty_pow_mmbtu4));
								GUJ_POW_QTY_MT.add(nf2.format(guj_qty_pow_mt4));
							}							
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_pow_mmbtu4==0.0 && guj_qty_pow_mmbtu4==0)
								{
									GUJ_POW_QTY_MMBTU.add("-");
									GUJ_POW_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pow_mt4 = guj_qty_pow_mmbtu4 * 0.0193;
									GUJ_POW_QTY_MMBTU.add(nf2.format(guj_qty_pow_mmbtu4));
									GUJ_POW_QTY_MT.add(nf2.format(guj_qty_pow_mt4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_POW_QTY_MMBTU.add("-");
					GUJ_POW_QTY_MT.add("-");
				}
				//End of MMBTU & MT of Power Sector for Gujarat
				//Start for SCM for Power Sector for Non-Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='509' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							pow_qty_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								POW_QTY_SCM.add(nf2.format(pow_qty_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(pow_qty_scm1==0.0 && pow_qty_scm1==0)
								{
									POW_QTY_SCM.add("-");
								}
								else
								{
									POW_QTY_SCM.add(nf2.format(pow_qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							pow_qty_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								POW_QTY_SCM.add(nf2.format(pow_qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(pow_qty_scm2==0.0 && pow_qty_scm2==0)
								{
									POW_QTY_SCM.add("-");
								}
								else
								{
									POW_QTY_SCM.add(nf2.format(pow_qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							pow_qty_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								POW_QTY_SCM.add(nf2.format(pow_qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(pow_qty_scm3==0.0 && pow_qty_scm3==0)
								{
									POW_QTY_SCM.add("-");
								}
								else
								{
									POW_QTY_SCM.add(nf2.format(pow_qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							pow_qty_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								POW_QTY_SCM .add(nf2.format(pow_qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(pow_qty_scm4==0.0 && pow_qty_scm4==0)
								{
									POW_QTY_SCM .add("-");
								}
								else
								{
									POW_QTY_SCM .add(nf2.format(pow_qty_scm4));
								}
							}
						}
					}											
				}
				else
				{
					POW_QTY_SCM.add("-");
				}
				//End for SCM of Power Sector for Non Gujarat
				//Start for SCM for Power Sector for Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='509' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pow_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								GUJ_POW_QTY_SCM.add(nf2.format(guj_qty_pow_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_pow_scm1==0.0 && guj_qty_pow_scm1==0)
								{
									GUJ_POW_QTY_SCM.add("-");
								}
								else
								{
									GUJ_POW_QTY_SCM.add(nf2.format(guj_qty_pow_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pow_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								GUJ_POW_QTY_SCM.add(nf2.format(guj_qty_pow_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_pow_scm2==0.0 && guj_qty_pow_scm2==0)
								{
									GUJ_POW_QTY_SCM.add("-");
								}
								else
								{
									GUJ_POW_QTY_SCM.add(nf2.format(guj_qty_pow_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pow_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								GUJ_POW_QTY_SCM.add(nf2.format(guj_qty_pow_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_pow_scm3==0.0 && guj_qty_pow_scm3==0)
								{
									GUJ_POW_QTY_SCM.add("-");
								}
								else
								{
									GUJ_POW_QTY_SCM.add(nf2.format(guj_qty_pow_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pow_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								GUJ_POW_QTY_SCM.add(nf2.format(guj_qty_pow_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_pow_scm4==0.0 && guj_qty_pow_scm4==0)
								{
									GUJ_POW_QTY_SCM.add("-");
								}
								else
								{
									GUJ_POW_QTY_SCM.add(nf2.format(guj_qty_pow_scm4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_POW_QTY_SCM.add("-");
				}
				//End of the Power Sector SCM of Gujarat & End of the Power Sector Code
				//Start of the Fertilisers MT & MMBTU of Non-Gujarat 
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='701' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							fer_qty_mmbtu1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								fer_qty_mt1 =  fer_qty_mmbtu1 * 0.0193;
								FER_QTY_MMBTU.add(nf2.format(fer_qty_mmbtu1));
								FER_QTY_MT.add(nf2.format(fer_qty_mt1));
							}
						}
						else
						{
							if(i==2)
							{									
								if(fer_qty_mmbtu1==0.0 && fer_qty_mmbtu1==0)
								{
									FER_QTY_MMBTU.add("-");
									FER_QTY_MT.add("-");
								}
								else
								{
									fer_qty_mt1 =  fer_qty_mmbtu1 * 0.0193;
									FER_QTY_MMBTU.add(nf2.format(fer_qty_mmbtu1));
									FER_QTY_MT.add(nf2.format(fer_qty_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							fer_qty_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								fer_qty_mt2 =  fer_qty_mmbtu2 * 0.0193;
								FER_QTY_MMBTU .add(nf2.format(fer_qty_mmbtu2));
								FER_QTY_MT.add(nf2.format(fer_qty_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(fer_qty_mmbtu2==0.0 && fer_qty_mmbtu2==0)
								{
									FER_QTY_MMBTU.add("-");
									FER_QTY_MT.add("-");
								}
								else
								{
									fer_qty_mt2 =  fer_qty_mmbtu2 * 0.0193;
									FER_QTY_MMBTU.add(nf2.format(fer_qty_mmbtu2));
									FER_QTY_MT.add(nf2.format(fer_qty_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							fer_qty_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								fer_qty_mt3 =  fer_qty_mmbtu3 * 0.0193;
								FER_QTY_MMBTU.add(nf2.format(fer_qty_mmbtu3));
								FER_QTY_MT.add(nf2.format(fer_qty_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(fer_qty_mmbtu3==0.0 && fer_qty_mmbtu3==0)
								{
									FER_QTY_MMBTU.add("-");
									FER_QTY_MT.add("-");
								}
								else
								{
									fer_qty_mt3 =  fer_qty_mmbtu3 * 0.0193;
									FER_QTY_MMBTU.add(nf2.format(fer_qty_mmbtu3));
									FER_QTY_MT.add(nf2.format(fer_qty_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							fer_qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								fer_qty_mt4 =  fer_qty_mmbtu4 * 0.0193;
								FER_QTY_MMBTU.add(nf2.format(fer_qty_mmbtu4));
								FER_QTY_MT.add(nf2.format(fer_qty_mt4));
							}
						}
						else
						{
							if(i==11)
							{
								if(fer_qty_mmbtu4==0.0 && fer_qty_mmbtu4==0)
								{
									FER_QTY_MMBTU.add("-");
									FER_QTY_MT.add("-");
								}
								else
								{
									fer_qty_mt4 =  fer_qty_mmbtu4 * 0.0193;
									FER_QTY_MMBTU.add(nf2.format(fer_qty_mmbtu4));
									FER_QTY_MT.add(nf2.format(fer_qty_mt4));
								}
							}
						}
					}											
				}
				else
				{
					FER_QTY_MMBTU.add("-");
					FER_QTY_MT.add("-");
				}
				//End of MMBTU & MT for Fertilisers for Non-Gujarat
				//Start for MMBTU & MT for Fertiliser for Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='701' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_fer_mmbtu1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								guj_qty_fer_mt1 = guj_qty_fer_mmbtu1 * 0.0193;
								GUJ_FER_QTY_MMBTU.add(nf2.format(guj_qty_fer_mmbtu1));
								GUJ_FER_QTY_MT.add(nf2.format(guj_qty_fer_mt1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_fer_mmbtu1==0.0 && guj_qty_fer_mmbtu1==0)
								{
									GUJ_FER_QTY_MMBTU.add("-");
									GUJ_FER_QTY_MT.add("-");
								}
								else
								{
									guj_qty_fer_mt1 = guj_qty_fer_mmbtu1 * 0.0193;
									GUJ_FER_QTY_MMBTU.add(nf2.format(guj_qty_fer_mmbtu1));
									GUJ_FER_QTY_MT.add(nf2.format(guj_qty_fer_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_fer_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								guj_qty_fer_mt2 = guj_qty_fer_mmbtu2 * 0.0193;
								GUJ_FER_QTY_MMBTU.add(nf2.format(guj_qty_fer_mmbtu2));
								GUJ_FER_QTY_MT.add(nf2.format(guj_qty_fer_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_fer_mmbtu2==0.0 && guj_qty_fer_mmbtu2==0)
								{
									GUJ_FER_QTY_MMBTU.add("-");
									GUJ_FER_QTY_MT.add("-");
								}
								else
								{
									guj_qty_fer_mt2 = guj_qty_fer_mmbtu2 * 0.0193;
									GUJ_FER_QTY_MMBTU.add(nf2.format(guj_qty_fer_mmbtu2));
									GUJ_FER_QTY_MT.add(nf2.format(guj_qty_fer_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_fer_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								guj_qty_fer_mt3 = guj_qty_fer_mmbtu3 * 0.0193;
								GUJ_FER_QTY_MMBTU.add(nf2.format(guj_qty_fer_mmbtu3));
								GUJ_FER_QTY_MT.add(nf2.format(guj_qty_fer_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_fer_mmbtu3==0.0 && guj_qty_fer_mmbtu3==0)
								{
									GUJ_FER_QTY_MMBTU.add("-");
									GUJ_FER_QTY_MT.add("-");
								}
								else
								{
									guj_qty_fer_mt3 = guj_qty_fer_mmbtu3 * 0.0193;
									GUJ_FER_QTY_MMBTU.add(nf2.format(guj_qty_fer_mmbtu3));
									GUJ_FER_QTY_MT.add(nf2.format(guj_qty_fer_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_fer_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								guj_qty_fer_mt4 = guj_qty_fer_mmbtu4 * 0.0193;
								GUJ_FER_QTY_MMBTU.add(nf2.format(guj_qty_fer_mmbtu4));
								GUJ_FER_QTY_MT.add(nf2.format(guj_qty_fer_mt4));
							}							
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_fer_mmbtu4==0.0 && guj_qty_fer_mmbtu4==0)
								{
									GUJ_FER_QTY_MMBTU.add("-");
									GUJ_FER_QTY_MT.add("-");
								}
								else
								{
									guj_qty_fer_mt4 = guj_qty_fer_mmbtu4 * 0.0193;
									GUJ_FER_QTY_MMBTU.add(nf2.format(guj_qty_fer_mmbtu4));
									GUJ_FER_QTY_MT.add(nf2.format(guj_qty_fer_mt4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_FER_QTY_MMBTU.add("-");
					GUJ_FER_QTY_MT.add("-");
				}
				//End of MMBTU & MT of Fertiliser for Gujarat
				//Start for SCM for Fertiliser for Non-Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='701' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							fer_qty_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								FER_QTY_SCM.add(nf2.format(fer_qty_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(fer_qty_scm1==0.0 && fer_qty_scm1==0)
								{
									FER_QTY_SCM.add("-");
								}
								else
								{
									FER_QTY_SCM.add(nf2.format(fer_qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							fer_qty_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								FER_QTY_SCM .add(nf2.format(fer_qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(fer_qty_scm2==0.0 && fer_qty_scm2==0)
								{
									FER_QTY_SCM.add("-");
								}
								else
								{
									FER_QTY_SCM.add(nf2.format(fer_qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							fer_qty_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								FER_QTY_SCM.add(nf2.format(fer_qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(fer_qty_scm3==0.0 && fer_qty_scm3==0)
								{
									FER_QTY_SCM.add("-");
								}
								else
								{
									FER_QTY_SCM.add(nf2.format(fer_qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							fer_qty_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								FER_QTY_SCM.add(nf2.format(fer_qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(fer_qty_scm4==0.0 && fer_qty_scm4==0)
								{
									FER_QTY_SCM.add("-");
								}
								else
								{
									FER_QTY_SCM.add(nf2.format(fer_qty_scm4));
								}
							}
						}
					}											
				}
				else
				{
					FER_QTY_SCM.add("-");
				}
				//End for SCM of Fertilisers for Non Gujarat
				//Start for SCM for Fertilisers for Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='701' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_fer_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								GUJ_FER_QTY_SCM.add(nf2.format(guj_qty_fer_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_fer_scm1==0.0 && guj_qty_fer_scm1==0)
								{
									GUJ_FER_QTY_SCM.add("-");
								}
								else
								{
									GUJ_FER_QTY_SCM.add(nf2.format(guj_qty_fer_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_fer_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								GUJ_FER_QTY_SCM.add(nf2.format(guj_qty_fer_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_fer_scm2==0.0 && guj_qty_fer_scm2==0)
								{
									GUJ_FER_QTY_SCM.add("-");
								}
								else
								{
									GUJ_FER_QTY_SCM.add(nf2.format(guj_qty_fer_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_fer_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								GUJ_FER_QTY_SCM.add(nf2.format(guj_qty_fer_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_fer_scm3==0.0 && guj_qty_fer_scm3==0)
								{
									GUJ_FER_QTY_SCM.add("-");
								}
								else
								{
									GUJ_FER_QTY_SCM.add(nf2.format(guj_qty_fer_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_fer_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								GUJ_FER_QTY_SCM.add(nf2.format(guj_qty_fer_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_fer_scm4==0.0 && guj_qty_fer_scm4==0)
								{
									GUJ_FER_QTY_SCM.add("-");
								}
								else
								{
									GUJ_FER_QTY_SCM.add(nf2.format(guj_qty_fer_scm4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_FER_QTY_SCM.add("-");
				}
				//End of the Fertilisers Code with SCM of Gujarat
				//Start of the Petrochemical code with MT & MMBTU for Non-Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='707' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							pet_qty_mmbtu1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								pet_qty_mt1 =  pet_qty_mmbtu1 * 0.0193;
								PET_QTY_MMBTU.add(nf2.format(pet_qty_mmbtu1));
								PET_QTY_MT.add(nf2.format(pet_qty_mt1));
							}
						}
						else
						{
							if(i==2)
							{									
								if(pet_qty_mmbtu1==0.0 && pet_qty_mmbtu1==0)
								{
									PET_QTY_MMBTU.add("-");
									PET_QTY_MT.add("-");
								}
								else
								{
									pet_qty_mt1 =  pet_qty_mmbtu1 * 0.0193;
									PET_QTY_MMBTU.add(nf2.format(pet_qty_mmbtu1));
									PET_QTY_MT.add(nf2.format(pet_qty_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							pet_qty_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								pet_qty_mt2 =  pet_qty_mmbtu2 * 0.0193;
								PET_QTY_MMBTU .add(nf2.format(pet_qty_mmbtu2));
								PET_QTY_MT.add(nf2.format(pet_qty_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(pet_qty_mmbtu2==0.0 && pet_qty_mmbtu2==0)
								{
									PET_QTY_MMBTU.add("-");
									PET_QTY_MT.add("-");
								}
								else
								{
									pet_qty_mt2 =  pet_qty_mmbtu2 * 0.0193;
									PET_QTY_MMBTU.add(nf2.format(pet_qty_mmbtu2));
									PET_QTY_MT.add(nf2.format(pet_qty_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							pet_qty_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								pet_qty_mt3 =  pet_qty_mmbtu3 * 0.0193;
								PET_QTY_MMBTU.add(nf2.format(pet_qty_mmbtu3));
								PET_QTY_MT.add(nf2.format(pet_qty_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(pet_qty_mmbtu3==0.0 && pet_qty_mmbtu3==0)
								{
									PET_QTY_MMBTU.add("-");
									PET_QTY_MT.add("-");
								}
								else
								{
									pet_qty_mt3 =  pet_qty_mmbtu3 * 0.0193;
									PET_QTY_MMBTU.add(nf2.format(pet_qty_mmbtu3));
									PET_QTY_MT.add(nf2.format(pet_qty_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							pet_qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								pet_qty_mt4 =  pet_qty_mmbtu4 * 0.0193;
								PET_QTY_MMBTU.add(nf2.format(pet_qty_mmbtu4));
								PET_QTY_MT.add(nf2.format(pet_qty_mt4));
							}
						}
						else
						{
							if(i==11)
							{
								if(pet_qty_mmbtu4==0.0 && pet_qty_mmbtu4==0)
								{
									PET_QTY_MMBTU.add("-");
									PET_QTY_MT.add("-");
								}
								else
								{
									pet_qty_mt4 =  pet_qty_mmbtu4 * 0.0193;
									PET_QTY_MMBTU.add(nf2.format(pet_qty_mmbtu4));
									PET_QTY_MT.add(nf2.format(pet_qty_mt4));
								}
							}
						}
					}											
				}
				else
				{
					PET_QTY_MMBTU.add("-");
					PET_QTY_MT.add("-");
				}
				//End of MMBTU & MT for Petrochemicals for Non-Gujarat
				//Start for MMBTU & MT for Petrochemicals for Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='707' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pet_mmbtu1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								guj_qty_pet_mt1 = guj_qty_pet_mmbtu1 * 0.0193;
								GUJ_PET_QTY_MMBTU.add(nf2.format(guj_qty_pet_mmbtu1));
								GUJ_PET_QTY_MT.add(nf2.format(guj_qty_pet_mt1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_pet_mmbtu1==0.0 && guj_qty_pet_mmbtu1==0)
								{
									GUJ_PET_QTY_MMBTU.add("-");
									GUJ_PET_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pet_mt1 = guj_qty_pet_mmbtu1 * 0.0193;
									GUJ_PET_QTY_MMBTU.add(nf2.format(guj_qty_pet_mmbtu1));
									GUJ_PET_QTY_MT.add(nf2.format(guj_qty_pet_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pet_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								guj_qty_pet_mt2 = guj_qty_pet_mmbtu2 * 0.0193;
								GUJ_PET_QTY_MMBTU.add(nf2.format(guj_qty_pet_mmbtu2));
								GUJ_PET_QTY_MT.add(nf2.format(guj_qty_pet_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_pet_mmbtu2==0.0 && guj_qty_pet_mmbtu2==0)
								{
									GUJ_PET_QTY_MMBTU.add("-");
									GUJ_PET_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pet_mt2 = guj_qty_pet_mmbtu2 * 0.0193;
									GUJ_PET_QTY_MMBTU.add(nf2.format(guj_qty_pet_mmbtu2));
									GUJ_PET_QTY_MT.add(nf2.format(guj_qty_pet_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pet_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								guj_qty_pet_mt3 = guj_qty_pet_mmbtu3 * 0.0193;
								GUJ_PET_QTY_MMBTU.add(nf2.format(guj_qty_pet_mmbtu3));
								GUJ_PET_QTY_MT.add(nf2.format(guj_qty_pet_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_pet_mmbtu3==0.0 && guj_qty_pet_mmbtu3==0)
								{
									GUJ_PET_QTY_MMBTU.add("-");
									GUJ_PET_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pet_mt3 = guj_qty_pet_mmbtu3 * 0.0193;
									GUJ_PET_QTY_MMBTU.add(nf2.format(guj_qty_pet_mmbtu3));
									GUJ_PET_QTY_MT.add(nf2.format(guj_qty_pet_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pet_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								guj_qty_pet_mt4 = guj_qty_pet_mmbtu4 * 0.0193;
								GUJ_PET_QTY_MMBTU.add(nf2.format(guj_qty_pet_mmbtu4));
								GUJ_PET_QTY_MT.add(nf2.format(guj_qty_pet_mt4));
							}							
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_pet_mmbtu4==0.0 && guj_qty_pet_mmbtu4==0)
								{
									GUJ_PET_QTY_MMBTU.add("-");
									GUJ_PET_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pet_mt4 = guj_qty_pet_mmbtu4 * 0.0193;
									GUJ_PET_QTY_MMBTU.add(nf2.format(guj_qty_pet_mmbtu4));
									GUJ_PET_QTY_MT.add(nf2.format(guj_qty_pet_mt4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_PET_QTY_MMBTU.add("-");
					GUJ_PET_QTY_MT.add("-");
				}
				//End of MMBTU & MT of Petrochemicals for Gujarat
				//Start for SCM for Petrochemicals for Non-Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='707' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							pet_qty_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								PET_QTY_SCM.add(nf2.format(pet_qty_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(pet_qty_scm1==0.0 && pet_qty_scm1==0)
								{
									PET_QTY_SCM.add("-");
								}
								else
								{
									PET_QTY_SCM.add(nf2.format(pet_qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							pet_qty_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								PET_QTY_SCM .add(nf2.format(pet_qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(pet_qty_scm2==0.0 && pet_qty_scm2==0)
								{
									PET_QTY_SCM.add("-");
								}
								else
								{
									PET_QTY_SCM.add(nf2.format(pet_qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							pet_qty_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								PET_QTY_SCM.add(nf2.format(pet_qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(pet_qty_scm3==0.0 && pet_qty_scm3==0)
								{
									PET_QTY_SCM.add("-");
								}
								else
								{
									PET_QTY_SCM.add(nf2.format(pet_qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							pet_qty_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								PET_QTY_SCM.add(nf2.format(pet_qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(pet_qty_scm4==0.0 && pet_qty_scm4==0)
								{
									PET_QTY_SCM.add("-");
								}
								else
								{
									PET_QTY_SCM.add(nf2.format(pet_qty_scm4));
								}
							}
						}
					}											
				}
				else
				{
					PET_QTY_SCM.add("-");
				}
				//End for SCM of Petrochemicals for Non Gujarat
				//Start for SCM for Petrochemicals for Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='707' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pet_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								GUJ_PET_QTY_SCM.add(nf2.format(guj_qty_pet_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_pet_scm1==0.0 && guj_qty_pet_scm1==0)
								{
									GUJ_PET_QTY_SCM.add("-");
								}
								else
								{
									GUJ_PET_QTY_SCM.add(nf2.format(guj_qty_pet_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pet_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								GUJ_PET_QTY_SCM.add(nf2.format(guj_qty_pet_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_pet_scm2==0.0 && guj_qty_pet_scm2==0)
								{
									GUJ_PET_QTY_SCM.add("-");
								}
								else
								{
									GUJ_PET_QTY_SCM.add(nf2.format(guj_qty_pet_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pet_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								GUJ_PET_QTY_SCM.add(nf2.format(guj_qty_pet_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_pet_scm3==0.0 && guj_qty_pet_scm3==0)
								{
									GUJ_PET_QTY_SCM.add("-");
								}
								else
								{
									GUJ_PET_QTY_SCM.add(nf2.format(guj_qty_pet_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_pet_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								GUJ_PET_QTY_SCM.add(nf2.format(guj_qty_pet_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_pet_scm4==0.0 && guj_qty_pet_scm4==0)
								{
									GUJ_PET_QTY_SCM.add("-");
								}
								else
								{
									GUJ_PET_QTY_SCM.add(nf2.format(guj_qty_pet_scm4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_PET_QTY_SCM.add("-");
				}
				//End of the Petrochemical code with SCM for Gujarat
				//Start of the Refineries code wirh MMBTU & MT for Non-Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='809' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							ref_qty_mmbtu1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								ref_qty_mt1 =  ref_qty_mmbtu1 * 0.0193;
								REF_QTY_MMBTU.add(nf2.format(ref_qty_mmbtu1));
								REF_QTY_MT.add(nf2.format(ref_qty_mt1));
							}
						}
						else
						{
							if(i==2)
							{									
								if(ref_qty_mmbtu1==0.0 && ref_qty_mmbtu1==0)
								{
									REF_QTY_MMBTU.add("-");
									REF_QTY_MT.add("-");
								}
								else
								{
									ref_qty_mt1 =  ref_qty_mmbtu1 * 0.0193;
									REF_QTY_MMBTU.add(nf2.format(ref_qty_mmbtu1));
									REF_QTY_MT.add(nf2.format(ref_qty_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							ref_qty_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								ref_qty_mt2 =  ref_qty_mmbtu2 * 0.0193;
								REF_QTY_MMBTU .add(nf2.format(ref_qty_mmbtu2));
								REF_QTY_MT.add(nf2.format(ref_qty_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(ref_qty_mmbtu2==0.0 && ref_qty_mmbtu2==0)
								{
									REF_QTY_MMBTU.add("-");
									REF_QTY_MT.add("-");
								}
								else
								{
									ref_qty_mt2 = ref_qty_mmbtu2 * 0.0193;
									REF_QTY_MMBTU.add(nf2.format(ref_qty_mmbtu2));
									REF_QTY_MT.add(nf2.format(ref_qty_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							ref_qty_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								ref_qty_mt3 =  ref_qty_mmbtu3 * 0.0193;
								REF_QTY_MMBTU.add(nf2.format(ref_qty_mmbtu3));
								REF_QTY_MT.add(nf2.format(ref_qty_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(ref_qty_mmbtu3==0.0 && ref_qty_mmbtu3==0)
								{
									REF_QTY_MMBTU.add("-");
									REF_QTY_MT.add("-");
								}
								else
								{
									ref_qty_mt3 =  ref_qty_mmbtu3 * 0.0193;
									REF_QTY_MMBTU.add(nf2.format(ref_qty_mmbtu3));
									REF_QTY_MT.add(nf2.format(ref_qty_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							ref_qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								ref_qty_mt4 =  ref_qty_mmbtu4 * 0.0193;
								REF_QTY_MMBTU.add(nf2.format(ref_qty_mmbtu4));
								REF_QTY_MT.add(nf2.format(ref_qty_mt4));
							}
						}
						else
						{
							if(i==11)
							{
								if(ref_qty_mmbtu4==0.0 && ref_qty_mmbtu4==0)
								{
									REF_QTY_MMBTU.add("-");
									REF_QTY_MT.add("-");
								}
								else
								{
									ref_qty_mt4 =  ref_qty_mmbtu4 * 0.0193;
									REF_QTY_MMBTU.add(nf2.format(ref_qty_mmbtu4));
									REF_QTY_MT.add(nf2.format(ref_qty_mt4));
								}
							}
						}
					}											
				}
				else
				{
					REF_QTY_MMBTU.add("-");
					REF_QTY_MT.add("-");
				}
				//End of MMBTU & MT for Petrochemicals for Non-Gujarat
				//Start for MMBTU & MT for Petrochemicals for Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='809' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_ref_mmbtu1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								guj_qty_ref_mt1 = guj_qty_ref_mmbtu1 * 0.0193;
								GUJ_REF_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu1));
								GUJ_REF_QTY_MT.add(nf2.format(guj_qty_ref_mt1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_ref_mmbtu1==0.0 && guj_qty_ref_mmbtu1==0)
								{
									GUJ_REF_QTY_MMBTU.add("-");
									GUJ_REF_QTY_MT.add("-");
								}
								else
								{
									guj_qty_ref_mt1 = guj_qty_ref_mmbtu1 * 0.0193;
									GUJ_REF_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu1));
									GUJ_REF_QTY_MT.add(nf2.format(guj_qty_ref_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_ref_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								guj_qty_ref_mt2 = guj_qty_ref_mmbtu2 * 0.0193;
								GUJ_REF_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu2));
								GUJ_REF_QTY_MT.add(nf2.format(guj_qty_ref_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_ref_mmbtu2==0.0 && guj_qty_ref_mmbtu2==0)
								{
									GUJ_REF_QTY_MMBTU.add("-");
									GUJ_REF_QTY_MT.add("-");
								}
								else
								{
									guj_qty_ref_mt2 = guj_qty_ref_mmbtu2 * 0.0193;
									GUJ_REF_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu2));
									GUJ_REF_QTY_MT.add(nf2.format(guj_qty_ref_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_ref_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								guj_qty_ref_mt3 = guj_qty_ref_mmbtu3 * 0.0193;
								GUJ_REF_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu3));
								GUJ_REF_QTY_MT.add(nf2.format(guj_qty_ref_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_ref_mmbtu3==0.0 && guj_qty_ref_mmbtu3==0)
								{
									GUJ_REF_QTY_MMBTU.add("-");
									GUJ_REF_QTY_MT.add("-");
								}
								else
								{
									guj_qty_pet_mt3 = guj_qty_ref_mmbtu3 * 0.0193;
									GUJ_REF_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu3));
									GUJ_REF_QTY_MT.add(nf2.format(guj_qty_ref_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_ref_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								guj_qty_ref_mt4 = guj_qty_ref_mmbtu4 * 0.0193;
								GUJ_REF_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu4));
								GUJ_REF_QTY_MT.add(nf2.format(guj_qty_ref_mt4));
							}							
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_ref_mmbtu4==0.0 && guj_qty_ref_mmbtu4==0)
								{
									GUJ_REF_QTY_MMBTU.add("-");
									GUJ_REF_QTY_MT.add("-");
								}
								else
								{
									guj_qty_ref_mt4 = guj_qty_ref_mmbtu4 * 0.0193;
									GUJ_REF_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu4));
									GUJ_REF_QTY_MT.add(nf2.format(guj_qty_ref_mt4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_REF_QTY_MMBTU.add("-");
					GUJ_REF_QTY_MT.add("-");
				}
				//End of MMBTU & MT of Refineries for Gujarat
				//Start for SCM for Refineries for Non-Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='809' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							ref_qty_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								REF_QTY_SCM.add(nf2.format(ref_qty_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(ref_qty_scm1==0.0 && ref_qty_scm1==0)
								{
									REF_QTY_SCM.add("-");
								}
								else
								{
									REF_QTY_SCM.add(nf2.format(ref_qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							ref_qty_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								REF_QTY_SCM .add(nf2.format(ref_qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(ref_qty_scm2==0.0 && ref_qty_scm2==0)
								{
									REF_QTY_SCM.add("-");
								}
								else
								{
									REF_QTY_SCM.add(nf2.format(ref_qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							ref_qty_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								REF_QTY_SCM.add(nf2.format(ref_qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(ref_qty_scm3==0.0 && ref_qty_scm3==0)
								{
									REF_QTY_SCM.add("-");
								}
								else
								{
									REF_QTY_SCM.add(nf2.format(ref_qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							ref_qty_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								REF_QTY_SCM.add(nf2.format(ref_qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(ref_qty_scm4==0.0 && ref_qty_scm4==0)
								{
									REF_QTY_SCM.add("-");
								}
								else
								{
									PET_QTY_SCM.add(nf2.format(ref_qty_scm4));
								}
							}
						}
					}											
				}
				else
				{
					REF_QTY_SCM.add("-");
				}
				//End for SCM of Petrochemicals for Non Gujarat
				//Start for SCM for Petrochemicals for Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='809' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_ref_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								GUJ_REF_QTY_SCM.add(nf2.format(guj_qty_ref_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_ref_scm1==0.0 && guj_qty_ref_scm1==0)
								{
									GUJ_REF_QTY_SCM.add("-");
								}
								else
								{
									GUJ_REF_QTY_SCM.add(nf2.format(guj_qty_ref_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_ref_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								GUJ_REF_QTY_SCM.add(nf2.format(guj_qty_ref_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_ref_scm2==0.0 && guj_qty_ref_scm2==0)
								{
									GUJ_REF_QTY_SCM.add("-");
								}
								else
								{
									GUJ_REF_QTY_SCM.add(nf2.format(guj_qty_ref_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_ref_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								GUJ_REF_QTY_SCM.add(nf2.format(guj_qty_ref_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_ref_scm3==0.0 && guj_qty_ref_scm3==0)
								{
									GUJ_REF_QTY_SCM.add("-");
								}
								else
								{
									GUJ_REF_QTY_SCM.add(nf2.format(guj_qty_ref_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_ref_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								GUJ_REF_QTY_SCM.add(nf2.format(guj_qty_ref_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_ref_scm4==0.0 && guj_qty_ref_scm4==0)
								{
									GUJ_REF_QTY_SCM.add("-");
								}
								else
								{
									GUJ_REF_QTY_SCM.add(nf2.format(guj_qty_ref_scm4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_REF_QTY_SCM.add("-");
				}
				//End of the Refineries code with SCM for Gujarat
				//Start of the Iron Code with MT & MMBTU of Non-Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='752' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							irn_qty_mmbtu1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								irn_qty_mt1 =  irn_qty_mmbtu1 * 0.0193;
								IRN_QTY_MMBTU.add(nf2.format(irn_qty_mmbtu1));
								IRN_QTY_MT.add(nf2.format(irn_qty_mt1));
							}
						}
						else
						{
							if(i==2)
							{									
								if(irn_qty_mmbtu1==0.0 && irn_qty_mmbtu1==0)
								{
									IRN_QTY_MMBTU.add("-");
									IRN_QTY_MT.add("-");
								}
								else
								{
									irn_qty_mt1 =  irn_qty_mmbtu1 * 0.0193;
									IRN_QTY_MMBTU.add(nf2.format(irn_qty_mmbtu1));
									IRN_QTY_MT.add(nf2.format(irn_qty_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							irn_qty_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								irn_qty_mt2 =  ref_qty_mmbtu2 * 0.0193;
								IRN_QTY_MMBTU .add(nf2.format(irn_qty_mmbtu2));
								IRN_QTY_MT.add(nf2.format(irn_qty_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(irn_qty_mmbtu2==0.0 && irn_qty_mmbtu2==0)
								{
									IRN_QTY_MMBTU.add("-");
									IRN_QTY_MT.add("-");
								}
								else
								{
									irn_qty_mt2 = irn_qty_mmbtu2 * 0.0193;
									IRN_QTY_MMBTU.add(nf2.format(irn_qty_mmbtu2));
									IRN_QTY_MT.add(nf2.format(irn_qty_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							irn_qty_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								irn_qty_mt3 =  irn_qty_mmbtu3 * 0.0193;
								IRN_QTY_MMBTU.add(nf2.format(irn_qty_mmbtu3));
								IRN_QTY_MT.add(nf2.format(irn_qty_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(irn_qty_mmbtu3==0.0 && irn_qty_mmbtu3==0)
								{
									IRN_QTY_MMBTU.add("-");
									IRN_QTY_MT.add("-");
								}
								else
								{
									irn_qty_mt3 =  irn_qty_mmbtu3 * 0.0193;
									IRN_QTY_MMBTU.add(nf2.format(irn_qty_mmbtu3));
									IRN_QTY_MT.add(nf2.format(irn_qty_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							irn_qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								irn_qty_mt4 =  irn_qty_mmbtu4 * 0.0193;
								IRN_QTY_MMBTU.add(nf2.format(irn_qty_mmbtu4));
								IRN_QTY_MT.add(nf2.format(irn_qty_mt4));
							}
						}
						else
						{
							if(i==11)
							{
								if(irn_qty_mmbtu4==0.0 && irn_qty_mmbtu4==0)
								{
									IRN_QTY_MMBTU.add("-");
									IRN_QTY_MT.add("-");
								}
								else
								{
									irn_qty_mt4 =  irn_qty_mmbtu4 * 0.0193;
									IRN_QTY_MMBTU.add(nf2.format(irn_qty_mmbtu4));
									IRN_QTY_MT.add(nf2.format(irn_qty_mt4));
								}
							}
						}
					}											
				}
				else
				{
					IRN_QTY_MMBTU.add("-");
					IRN_QTY_MT.add("-");
				}
				//End of MMBTU & MT for Petrochemicals for Non-Gujarat
				//Start for MMBTU & MT for Petrochemicals for Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='752' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_irn_mmbtu1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								guj_qty_irn_mt1 = guj_qty_irn_mmbtu1 * 0.0193;
								GUJ_IRN_QTY_MMBTU.add(nf2.format(guj_qty_irn_mmbtu1));
								GUJ_IRN_QTY_MT.add(nf2.format(guj_qty_irn_mt1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_irn_mmbtu1==0.0 && guj_qty_irn_mmbtu1==0)
								{
									GUJ_IRN_QTY_MMBTU.add("-");
									GUJ_IRN_QTY_MT.add("-");
								}
								else
								{
									guj_qty_irn_mt1 = guj_qty_irn_mmbtu1 * 0.0193;
									GUJ_IRN_QTY_MMBTU.add(nf2.format(guj_qty_ref_mmbtu1));
									GUJ_IRN_QTY_MT.add(nf2.format(guj_qty_irn_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_irn_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								guj_qty_irn_mt2 = guj_qty_irn_mmbtu2 * 0.0193;
								GUJ_IRN_QTY_MMBTU.add(nf2.format(guj_qty_irn_mmbtu2));
								GUJ_IRN_QTY_MT.add(nf2.format(guj_qty_irn_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_irn_mmbtu2==0.0 && guj_qty_irn_mmbtu2==0)
								{
									GUJ_IRN_QTY_MMBTU.add("-");
									GUJ_IRN_QTY_MT.add("-");
								}
								else
								{
									guj_qty_irn_mt2 = guj_qty_irn_mmbtu2 * 0.0193;
									GUJ_IRN_QTY_MMBTU.add(nf2.format(guj_qty_irn_mmbtu2));
									GUJ_IRN_QTY_MT.add(nf2.format(guj_qty_irn_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_irn_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								guj_qty_irn_mt3 = guj_qty_irn_mmbtu3 * 0.0193;
								GUJ_IRN_QTY_MMBTU.add(nf2.format(guj_qty_irn_mmbtu3));
								GUJ_IRN_QTY_MT.add(nf2.format(guj_qty_irn_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_irn_mmbtu3==0.0 && guj_qty_irn_mmbtu3==0)
								{
									GUJ_IRN_QTY_MMBTU.add("-");
									GUJ_IRN_QTY_MT.add("-");
								}
								else
								{
									guj_qty_irn_mt3 = guj_qty_irn_mmbtu3 * 0.0193;
									GUJ_IRN_QTY_MMBTU.add(nf2.format(guj_qty_irn_mmbtu3));
									GUJ_IRN_QTY_MT.add(nf2.format(guj_qty_irn_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_irn_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								guj_qty_irn_mt4 = guj_qty_irn_mmbtu4 * 0.0193;
								GUJ_IRN_QTY_MMBTU.add(nf2.format(guj_qty_irn_mmbtu4));
								GUJ_IRN_QTY_MT.add(nf2.format(guj_qty_irn_mt4));
							}							
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_irn_mmbtu4==0.0 && guj_qty_irn_mmbtu4==0)
								{
									GUJ_IRN_QTY_MMBTU.add("-");
									GUJ_IRN_QTY_MT.add("-");
								}
								else
								{
									guj_qty_irn_mt4 = guj_qty_irn_mmbtu4 * 0.0193;
									GUJ_IRN_QTY_MMBTU.add(nf2.format(guj_qty_irn_mmbtu4));
									GUJ_IRN_QTY_MT.add(nf2.format(guj_qty_irn_mt4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_IRN_QTY_MMBTU.add("-");
					GUJ_IRN_QTY_MT.add("-");
				}
				//End of MMBTU & MT of Iron for Gujarat
				//Start for SCM for Iron for Non-Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='752' AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							irn_qty_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								IRN_QTY_SCM.add(nf2.format(irn_qty_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(irn_qty_scm1==0.0 && irn_qty_scm1==0)
								{
									IRN_QTY_SCM.add("-");
								}
								else
								{
									IRN_QTY_SCM.add(nf2.format(irn_qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							irn_qty_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								IRN_QTY_SCM .add(nf2.format(irn_qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(irn_qty_scm2==0.0 && irn_qty_scm2==0)
								{
									IRN_QTY_SCM.add("-");
								}
								else
								{
									IRN_QTY_SCM.add(nf2.format(irn_qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							irn_qty_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								IRN_QTY_SCM.add(nf2.format(irn_qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(irn_qty_scm3==0.0 && irn_qty_scm3==0)
								{
									IRN_QTY_SCM.add("-");
								}
								else
								{
									IRN_QTY_SCM.add(nf2.format(irn_qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							irn_qty_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								IRN_QTY_SCM.add(nf2.format(irn_qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(irn_qty_scm4==0.0 && irn_qty_scm4==0)
								{
									IRN_QTY_SCM.add("-");
								}
								else
								{
									IRN_QTY_SCM.add(nf2.format(irn_qty_scm4));
								}
							}
						}
					}											
				}
				else
				{
					IRN_QTY_SCM.add("-");
				}
				//End for SCM of Iron for Non Gujarat
				//Start for SCM for Iron for Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='752' AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_irn_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								GUJ_IRN_QTY_SCM.add(nf2.format(guj_qty_irn_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_irn_scm1==0.0 && guj_qty_irn_scm1==0)
								{
									GUJ_IRN_QTY_SCM.add("-");
								}
								else
								{
									GUJ_IRN_QTY_SCM.add(nf2.format(guj_qty_irn_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_irn_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								GUJ_IRN_QTY_SCM.add(nf2.format(guj_qty_irn_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_irn_scm2==0.0 && guj_qty_irn_scm2==0)
								{
									GUJ_IRN_QTY_SCM.add("-");
								}
								else
								{
									GUJ_IRN_QTY_SCM.add(nf2.format(guj_qty_irn_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_irn_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								GUJ_IRN_QTY_SCM.add(nf2.format(guj_qty_irn_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_irn_scm3==0.0 && guj_qty_irn_scm3==0)
								{
									GUJ_IRN_QTY_SCM.add("-");
								}
								else
								{
									GUJ_IRN_QTY_SCM.add(nf2.format(guj_qty_irn_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_irn_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								GUJ_IRN_QTY_SCM.add(nf2.format(guj_qty_irn_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_irn_scm4==0.0 && guj_qty_irn_scm4==0)
								{
									GUJ_IRN_QTY_SCM.add("-");
								}
								else
								{
									GUJ_IRN_QTY_SCM.add(nf2.format(guj_qty_irn_scm4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_IRN_QTY_SCM.add("-");
				}
				//End of the Iron code with SCM for Gujarat
				//Start of the Others Code With MT & MMBTU for Non-Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='752' AND B.PLANT_SECTOR!='000' " +
				"AND B.PLANT_SECTOR!='701' AND B.PLANT_SECTOR!='400' AND B.PLANT_SECTOR!='509' AND B.PLANT_SECTOR!='809' AND B.PLANT_SECTOR!='707' " +
				"AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							oth_qty_mmbtu1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								oth_qty_mt1 =  oth_qty_mmbtu1 * 0.0193;
								OTH_QTY_MMBTU.add(nf2.format(oth_qty_mmbtu1));
								OTH_QTY_MT.add(nf2.format(oth_qty_mt1));								
							}
						}
						else
						{
							if(i==2)
							{									
								if(oth_qty_mmbtu1==0.0 && oth_qty_mmbtu1==0)
								{
									OTH_QTY_MMBTU.add("-");
									OTH_QTY_MT.add("-");
								}
								else
								{
									oth_qty_mt1 =  oth_qty_mmbtu1 * 0.0193;
									OTH_QTY_MMBTU.add(nf2.format(oth_qty_mmbtu1));
									OTH_QTY_MT.add(nf2.format(oth_qty_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							oth_qty_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								irn_qty_mt2 =  ref_qty_mmbtu2 * 0.0193;
								OTH_QTY_MMBTU .add(nf2.format(irn_qty_mmbtu2));
								OTH_QTY_MT.add(nf2.format(irn_qty_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(oth_qty_mmbtu2==0.0 && oth_qty_mmbtu2==0)
								{
									OTH_QTY_MMBTU.add("-");
									OTH_QTY_MT.add("-");
								}
								else
								{
									oth_qty_mt2 = oth_qty_mmbtu2 * 0.0193;
									OTH_QTY_MMBTU.add(nf2.format(oth_qty_mmbtu2));
									OTH_QTY_MT.add(nf2.format(oth_qty_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							oth_qty_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								oth_qty_mt3 =  oth_qty_mmbtu3 * 0.0193;
								OTH_QTY_MMBTU.add(nf2.format(oth_qty_mmbtu3));
								OTH_QTY_MT.add(nf2.format(oth_qty_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(oth_qty_mmbtu3==0.0 && oth_qty_mmbtu3==0)
								{
									OTH_QTY_MMBTU.add("-");
									OTH_QTY_MT.add("-");
								}
								else
								{
									oth_qty_mt3 =  oth_qty_mmbtu3 * 0.0193;
									OTH_QTY_MMBTU.add(nf2.format(oth_qty_mmbtu3));
									OTH_QTY_MT.add(nf2.format(oth_qty_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							oth_qty_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								oth_qty_mt4 =  oth_qty_mmbtu4 * 0.0193;
								OTH_QTY_MMBTU.add(nf2.format(oth_qty_mmbtu4));
								OTH_QTY_MT.add(nf2.format(oth_qty_mt4));
							}
						}
						else
						{
							if(i==11)
							{
								if(oth_qty_mmbtu4==0.0 && oth_qty_mmbtu4==0)
								{
									OTH_QTY_MMBTU.add("-");
									OTH_QTY_MT.add("-");
								}
								else
								{
									oth_qty_mt4 =  oth_qty_mmbtu4 * 0.0193;
									OTH_QTY_MMBTU.add(nf2.format(oth_qty_mmbtu4));
									OTH_QTY_MT.add(nf2.format(oth_qty_mt4));
								}
							}
						}
					}											
				}
				else
				{
					OTH_QTY_MMBTU.add("-");
					OTH_QTY_MT.add("-");
				}
				//End of MMBTU & MT for Others for Non-Gujarat
				//Start for MMBTU & MT for Others for Gujarat
				queryString ="select SUM(A.QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='752' AND B.PLANT_SECTOR!='000' " +
				"AND B.PLANT_SECTOR!='701' AND B.PLANT_SECTOR!='400' AND B.PLANT_SECTOR!='509' AND B.PLANT_SECTOR!='809' AND B.PLANT_SECTOR!='707' " +
				"AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_oth_mmbtu1+=Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1));
							if(i==2)
							{
								guj_qty_oth_mt1 = guj_qty_oth_mmbtu1 * 0.0193;
								GUJ_OTH_QTY_MMBTU.add(nf2.format(guj_qty_oth_mmbtu1));
								GUJ_OTH_QTY_MT.add(nf2.format(guj_qty_oth_mt1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_oth_mmbtu1==0.0 && guj_qty_oth_mmbtu1==0)
								{
									GUJ_OTH_QTY_MMBTU.add("-");
									GUJ_OTH_QTY_MT.add("-");
								}
								else
								{
									guj_qty_oth_mt1 = guj_qty_oth_mmbtu1 * 0.0193;
									GUJ_OTH_QTY_MMBTU.add(nf2.format(guj_qty_oth_mmbtu1));
									GUJ_OTH_QTY_MT.add(nf2.format(guj_qty_oth_mt1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_oth_mmbtu2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								guj_qty_oth_mt2 = guj_qty_oth_mmbtu2 * 0.0193;
								GUJ_OTH_QTY_MMBTU.add(nf2.format(guj_qty_oth_mmbtu2));
								GUJ_OTH_QTY_MT.add(nf2.format(guj_qty_oth_mt2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_oth_mmbtu2==0.0 && guj_qty_oth_mmbtu2==0)
								{
									GUJ_OTH_QTY_MMBTU.add("-");
									GUJ_OTH_QTY_MT.add("-");
								}
								else
								{
									guj_qty_irn_mt2 = guj_qty_oth_mmbtu2 * 0.0193;
									GUJ_OTH_QTY_MMBTU.add(nf2.format(guj_qty_oth_mmbtu2));
									GUJ_OTH_QTY_MT.add(nf2.format(guj_qty_oth_mt2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_oth_mmbtu3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								guj_qty_oth_mt3 = guj_qty_oth_mmbtu3 * 0.0193;
								GUJ_OTH_QTY_MMBTU.add(nf2.format(guj_qty_oth_mmbtu3));
								GUJ_OTH_QTY_MT.add(nf2.format(guj_qty_oth_mt3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_oth_mmbtu3==0.0 && guj_qty_oth_mmbtu3==0)
								{
									GUJ_OTH_QTY_MMBTU.add("-");
									GUJ_OTH_QTY_MT.add("-");
								}
								else
								{
									guj_qty_oth_mt3 = guj_qty_oth_mmbtu3 * 0.0193;
									GUJ_OTH_QTY_MMBTU.add(nf2.format(guj_qty_oth_mmbtu3));
									GUJ_OTH_QTY_MT.add(nf2.format(guj_qty_oth_mt3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_oth_mmbtu4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								guj_qty_oth_mt4 = guj_qty_oth_mmbtu4 * 0.0193;
								GUJ_OTH_QTY_MMBTU.add(nf2.format(guj_qty_oth_mmbtu4));
								GUJ_OTH_QTY_MT.add(nf2.format(guj_qty_oth_mt4));
							}							
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_oth_mmbtu4==0.0 && guj_qty_oth_mmbtu4==0)
								{
									GUJ_OTH_QTY_MMBTU.add("-");
									GUJ_OTH_QTY_MT.add("-");
								}
								else
								{
									guj_qty_oth_mt4 = guj_qty_oth_mmbtu4 * 0.0193;
									GUJ_OTH_QTY_MMBTU.add(nf2.format(guj_qty_oth_mmbtu4));
									GUJ_OTH_QTY_MT.add(nf2.format(guj_qty_oth_mt4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_OTH_QTY_MMBTU.add("-");
					GUJ_OTH_QTY_MT.add("-");
				}
				//End of MMBTU & MT of Others for Gujarat
				//Start for SCM for Others for Non-Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='752' AND B.PLANT_SECTOR!='000' " +
				"AND B.PLANT_SECTOR!='701' AND B.PLANT_SECTOR!='400' AND B.PLANT_SECTOR!='509' AND B.PLANT_SECTOR!='809' AND B.PLANT_SECTOR!='707' " +
				"AND TRIM(UPPER(B.PLANT_STATE))!='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							oth_qty_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								OTH_QTY_SCM.add(nf2.format(oth_qty_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(oth_qty_scm1==0.0 && oth_qty_scm1==0)
								{
									OTH_QTY_SCM.add("-");
								}
								else
								{
									OTH_QTY_SCM.add(nf2.format(oth_qty_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							oth_qty_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								OTH_QTY_SCM .add(nf2.format(oth_qty_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(oth_qty_scm2==0.0 && oth_qty_scm2==0)
								{
									OTH_QTY_SCM.add("-");
								}
								else
								{
									OTH_QTY_SCM.add(nf2.format(oth_qty_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							oth_qty_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								OTH_QTY_SCM.add(nf2.format(oth_qty_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(oth_qty_scm3==0.0 && oth_qty_scm3==0)
								{
									OTH_QTY_SCM.add("-");
								}
								else
								{
									OTH_QTY_SCM.add(nf2.format(oth_qty_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							oth_qty_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								OTH_QTY_SCM.add(nf2.format(oth_qty_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(oth_qty_scm4==0.0 && oth_qty_scm4==0)
								{
									OTH_QTY_SCM.add("-");
								}
								else
								{
									OTH_QTY_SCM.add(nf2.format(oth_qty_scm4));
								}
							}
						}
					}											
				}
				else
				{
					OTH_QTY_SCM.add("-");
				}
				//End for SCM of Others for Non Gujarat
				//Start for SCM for Others for Gujarat
				queryString ="select (SUM(A.QTY_SCM))/1000000 from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='752' AND B.PLANT_SECTOR!='000' " +
				"AND B.PLANT_SECTOR!='701' AND B.PLANT_SECTOR!='400' AND B.PLANT_SECTOR!='509' AND B.PLANT_SECTOR!='809' AND B.PLANT_SECTOR!='707' " +
				"AND TRIM(UPPER(B.PLANT_STATE))='GUJARAT' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{		
					if(i<3)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_oth_scm1+=Double.parseDouble(rset.getString(1));
							if(i==2)
							{
								GUJ_OTH_QTY_SCM.add(nf2.format(guj_qty_oth_scm1));
							}
						}
						else
						{
							if(i==2)
							{
								if(guj_qty_oth_scm1==0.0 && guj_qty_oth_scm1==0)
								{
									GUJ_OTH_QTY_SCM.add("-");
								}
								else
								{
									GUJ_OTH_QTY_SCM.add(nf2.format(guj_qty_oth_scm1));
								}
							}
						}
					}
					else if(i>=3 && i<6)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_oth_scm2+=Double.parseDouble(rset.getString(1));
							if(i==5)
							{
								GUJ_OTH_QTY_SCM.add(nf2.format(guj_qty_oth_scm2));
							}
						}
						else
						{
							if(i==5)
							{
								if(guj_qty_oth_scm2==0.0 && guj_qty_oth_scm2==0)
								{
									GUJ_OTH_QTY_SCM.add("-");
								}
								else
								{
									GUJ_OTH_QTY_SCM.add(nf2.format(guj_qty_oth_scm2));
								}
							}
						}
					}
					else if(i>=6 && i<9)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_oth_scm3+=Double.parseDouble(rset.getString(1));
							if(i==8)
							{
								GUJ_OTH_QTY_SCM.add(nf2.format(guj_qty_oth_scm3));
							}
						}
						else
						{
							if(i==8)
							{
								if(guj_qty_oth_scm3==0.0 && guj_qty_oth_scm3==0)
								{
									GUJ_OTH_QTY_SCM.add("-");
								}
								else
								{
									GUJ_OTH_QTY_SCM.add(nf2.format(guj_qty_oth_scm3));
								}
							}
						}
					}
					else if(i>=9 && i<12)
					{
						if(rset.getString(1)!=null)
						{
							guj_qty_oth_scm4+=Double.parseDouble(rset.getString(1)); 
							if(i==11)
							{
								GUJ_OTH_QTY_SCM.add(nf2.format(guj_qty_oth_scm4));
							}
						}
						else
						{
							if(i==11)
							{
								if(guj_qty_oth_scm4==0.0 && guj_qty_oth_scm4==0)
								{
									GUJ_OTH_QTY_SCM.add("-");
								}
								else
								{
									GUJ_OTH_QTY_SCM.add(nf2.format(guj_qty_oth_scm4));
								}
							}
						}
					}																
				}
				else
				{
					GUJ_OTH_QTY_SCM.add("-");
				}
				//End of the Others code with SCM for Gujarat
				if(i==2)
				{
					tot_mmbtu1=""+nf2.format(qty_mmbtu1+con_qty_mmbtu1+pow_qty_mmbtu1+fer_qty_mmbtu1+
							pet_qty_mmbtu1+ref_qty_mmbtu1+irn_qty_mmbtu1+oth_qty_mmbtu1);
					tot_scm1=""+nf2.format(qty_scm1+qty_con_scm1+pow_qty_scm1+fer_qty_scm1+
							pet_qty_scm1+ref_qty_scm1+irn_qty_scm1+oth_qty_scm1);
					tot_mt1=""+nf2.format(qty_mt1+con_qty_mt1+pow_qty_mt1+fer_qty_mt1+
							pet_qty_mt1+ref_qty_mt1+irn_qty_mt1+oth_qty_mt1);
					guj_tot_mmbtu1=""+nf2.format(guj_qty_mmbtu1+guj_qty_con_mmbtu1+guj_qty_pow_mmbtu1+
							guj_qty_fer_mmbtu1+guj_qty_pet_mmbtu1+guj_qty_ref_mmbtu1+guj_qty_irn_mmbtu1+
							guj_qty_oth_mmbtu1);
					guj_tot_scm1=""+nf2.format(guj_qty_scm1+guj_qty_con_scm1+guj_qty_pow_scm1+
							guj_qty_fer_scm1+guj_qty_pet_scm1+guj_qty_ref_scm1+guj_qty_irn_scm1+
							guj_qty_oth_scm1);
					guj_tot_mt1=""+nf2.format(guj_qty_ref_mt1+guj_qty_irn_mt1+guj_qty_pet_mt1+
							guj_qty_fer_mt1+guj_qty_pow_mt1+guj_qty_con_mt1+guj_qty_mt1+
							guj_qty_oth_mt1);
					
				}
				else if(i==5)
				{
					tot_mmbtu2=""+nf2.format(qty_mmbtu2+con_qty_mmbtu2+pow_qty_mmbtu2+fer_qty_mmbtu2+
							pet_qty_mmbtu2+ref_qty_mmbtu2+irn_qty_mmbtu2+oth_qty_mmbtu2);
					tot_scm2=""+nf2.format(qty_scm2+qty_con_scm2+pow_qty_scm2+fer_qty_scm2+pet_qty_scm2+
							ref_qty_scm2+irn_qty_scm2+oth_qty_scm2);
					tot_mt2=""+nf2.format(qty_mt2+con_qty_mt2+pow_qty_mt2+fer_qty_mt2+pet_qty_mt2+
							ref_qty_mt2+irn_qty_mt2+oth_qty_mt2);
					guj_tot_mmbtu2=""+nf2.format(guj_qty_mmbtu2+guj_qty_con_mmbtu2+guj_qty_pow_mmbtu2+
							guj_qty_fer_mmbtu2+guj_qty_pet_mmbtu2+guj_qty_ref_mmbtu2+guj_qty_irn_mmbtu2+
							guj_qty_oth_mmbtu2);
					guj_tot_scm2=""+nf2.format(guj_qty_scm2+guj_qty_con_scm2+guj_qty_pow_scm2+
							guj_qty_fer_scm2+guj_qty_pet_scm2+guj_qty_ref_scm2+guj_qty_irn_scm2+
							guj_qty_oth_scm2);
					guj_tot_mt2=""+nf2.format(guj_qty_mt2+guj_qty_con_mt2+guj_qty_pow_mt2+guj_qty_fer_mt2+
							guj_qty_pet_mt2+guj_qty_ref_mt2+guj_qty_irn_mt2+guj_qty_oth_mt2);
				}
				else if(i==8)
				{
					tot_mmbtu3=""+nf2.format(qty_mmbtu3+con_qty_mmbtu3+pow_qty_mmbtu3+fer_qty_mmbtu3+
							pet_qty_mmbtu3+ref_qty_mmbtu3+irn_qty_mmbtu3+oth_qty_mmbtu3);
					tot_scm3=""+nf2.format(qty_scm3+qty_con_scm3+pow_qty_scm3+fer_qty_scm3+pet_qty_scm3+
							ref_qty_scm3+irn_qty_scm3+oth_qty_scm3);
					tot_mt3=""+nf2.format(qty_mt3+con_qty_mt3+pow_qty_mt3+fer_qty_mt3+pet_qty_mt3+
							ref_qty_mt3+irn_qty_mt3+oth_qty_mt3);
					guj_tot_mmbtu3=""+nf2.format(guj_qty_mmbtu3+guj_qty_con_mmbtu3+guj_qty_pow_mmbtu3+
							guj_qty_fer_mmbtu3+guj_qty_pet_mmbtu3+guj_qty_ref_mmbtu3+guj_qty_irn_mmbtu3+
							guj_qty_oth_mmbtu3);
					guj_tot_scm3=""+nf2.format(guj_qty_scm3+guj_qty_con_scm3+guj_qty_pow_scm3+
							guj_qty_fer_scm3+guj_qty_pet_scm3+guj_qty_ref_scm3+guj_qty_irn_scm3+
							guj_qty_oth_scm3);
					guj_tot_mt3=""+nf2.format(guj_qty_mt3+guj_qty_con_mt3+guj_qty_pow_mt3+guj_qty_fer_mt3+
							guj_qty_pet_mt3+guj_qty_ref_mt3+guj_qty_irn_mt3+guj_qty_oth_mt3);
					
				}
				else if(i==11)
				{
					tot_mmbtu4=""+nf2.format(qty_mmbtu4+con_qty_mmbtu4+pow_qty_mmbtu4+fer_qty_mmbtu4+
							pet_qty_mmbtu4+ref_qty_mmbtu4+irn_qty_mmbtu4+oth_qty_mmbtu4);
					tot_scm4=""+nf2.format(qty_scm4+qty_con_scm4+pow_qty_scm4+fer_qty_scm4+pet_qty_scm4+
							ref_qty_scm4+irn_qty_scm4+oth_qty_scm4);
					tot_mt4=""+nf2.format(qty_mt4+con_qty_mt4+pow_qty_mt4+fer_qty_mt4+pet_qty_mt4+ref_qty_mt4+
							irn_qty_mt4+oth_qty_mt4);
					guj_tot_mmbtu4=""+nf2.format(guj_qty_mmbtu4+guj_qty_con_mmbtu4+guj_qty_pow_mmbtu4+
							guj_qty_fer_mmbtu4+guj_qty_pet_mmbtu4+guj_qty_ref_mmbtu4+guj_qty_irn_mmbtu4+
							guj_qty_oth_mmbtu4);
					guj_tot_scm4=""+nf2.format(guj_qty_scm4+guj_qty_con_scm4+guj_qty_pow_scm4+guj_qty_fer_scm4+
							guj_qty_pet_scm4+guj_qty_ref_scm4+guj_qty_irn_scm4+guj_qty_oth_scm4);
					guj_tot_mt4=""+nf2.format(guj_qty_mt4+guj_qty_con_mt4+guj_qty_pow_mt4+guj_qty_fer_mt4+
							guj_qty_pet_mt4+guj_qty_ref_mt4+guj_qty_irn_mt4+guj_qty_oth_mt4);
				}
			}
			double sum_mmbtu =0.0;
			double sum_scm =0.0;
			double sum_mt =0.0;
			sum_mmbtu+=qty_mmbtu1+qty_mmbtu2+qty_mmbtu3+qty_mmbtu4+guj_qty_mmbtu1+guj_qty_mmbtu2+
			guj_qty_mmbtu3+guj_qty_mmbtu4+con_qty_mmbtu1+con_qty_mmbtu2+con_qty_mmbtu3+
			con_qty_mmbtu4+guj_qty_con_mmbtu1+guj_qty_con_mmbtu2+guj_qty_con_mmbtu3+
			guj_qty_con_mmbtu4+pow_qty_mmbtu1+pow_qty_mmbtu2+pow_qty_mmbtu3+pow_qty_mmbtu4+
			guj_qty_pow_mmbtu1+guj_qty_pow_mmbtu2+guj_qty_pow_mmbtu3+guj_qty_pow_mmbtu4+
			pow_qty_mmbtu1+pow_qty_mmbtu2+pow_qty_mmbtu3+pow_qty_mmbtu4+guj_qty_pow_mmbtu1+
			guj_qty_pow_mmbtu2+guj_qty_pow_mmbtu3+guj_qty_pow_mmbtu4+fer_qty_mmbtu1+
			fer_qty_mmbtu2+fer_qty_mmbtu3+fer_qty_mmbtu4+guj_qty_fer_mmbtu1+guj_qty_fer_mmbtu2+
			guj_qty_fer_mmbtu3+guj_qty_fer_mmbtu4+pet_qty_mmbtu1+pet_qty_mmbtu2+pet_qty_mmbtu3+
			pet_qty_mmbtu4+guj_qty_pet_mmbtu1+guj_qty_pet_mmbtu2+guj_qty_pet_mmbtu3+
			guj_qty_pet_mmbtu4+ref_qty_mmbtu1+ref_qty_mmbtu2+ref_qty_mmbtu3+ref_qty_mmbtu4+
			guj_qty_ref_mmbtu1+guj_qty_ref_mmbtu2+guj_qty_ref_mmbtu3+guj_qty_ref_mmbtu4+
			oth_qty_mmbtu1+oth_qty_mmbtu2+oth_qty_mmbtu3+oth_qty_mmbtu4+guj_qty_oth_mmbtu1+
			guj_qty_oth_mmbtu2+guj_qty_oth_mmbtu3+guj_qty_oth_mmbtu4;
			
			sum_scm+=qty_scm1+qty_scm2+qty_scm3+qty_scm4+guj_qty_scm1+guj_qty_scm2+guj_qty_scm3+
			guj_qty_scm4+qty_con_scm1+qty_con_scm2+qty_con_scm3+qty_con_scm4+guj_qty_con_scm1+
			guj_qty_con_scm2+guj_qty_con_scm3+guj_qty_con_scm4+pow_qty_scm1+pow_qty_scm2+
			pow_qty_scm3+pow_qty_scm4+guj_qty_pow_scm1+guj_qty_pow_scm2+guj_qty_pow_scm3+
			guj_qty_pow_scm4+fer_qty_scm1+fer_qty_scm2+fer_qty_scm3+fer_qty_scm4+
			guj_qty_fer_scm1+guj_qty_fer_scm2+guj_qty_fer_scm3+guj_qty_fer_scm4+pet_qty_scm1+
			pet_qty_scm2+pet_qty_scm3+pet_qty_scm4+guj_qty_pet_scm1+guj_qty_pet_scm2+
			guj_qty_pet_scm3+guj_qty_pet_scm4+ref_qty_scm1+ref_qty_scm2+ref_qty_scm3+
			ref_qty_scm4+guj_qty_ref_scm1+guj_qty_ref_scm2+guj_qty_ref_scm3+guj_qty_ref_scm4+
			oth_qty_scm1+oth_qty_scm2+oth_qty_scm3+oth_qty_scm4+guj_qty_oth_scm1+
			guj_qty_oth_scm2+guj_qty_oth_scm3+guj_qty_oth_scm4+irn_qty_scm1+irn_qty_scm2+
			irn_qty_scm3+irn_qty_scm4+guj_qty_irn_scm1+guj_qty_irn_scm2+guj_qty_irn_scm3+
			guj_qty_irn_scm4;
			
			sum_mt+=qty_mt1+qty_mt2+qty_mt3+qty_mt4+guj_qty_mt1+guj_qty_mt2+guj_qty_mt3+guj_qty_mt4+
			con_qty_mt1+con_qty_mt2+con_qty_mt3+con_qty_mt4+guj_qty_con_mt1+guj_qty_con_mt2+
			guj_qty_con_mt3+guj_qty_con_mt4+pow_qty_mt1+pow_qty_mt2+pow_qty_mt3+pow_qty_mt4+
			guj_qty_pow_mt1+guj_qty_pow_mt2+guj_qty_pow_mt3+guj_qty_pow_mt4+fer_qty_mt1+
			fer_qty_mt2+fer_qty_mt3+fer_qty_mt4+guj_qty_fer_mt1+guj_qty_fer_mt2+guj_qty_fer_mt3+
			guj_qty_fer_mt4+pet_qty_mt1+pet_qty_mt2+pet_qty_mt3+pet_qty_mt4+guj_qty_pet_mt1+
			guj_qty_pet_mt2+guj_qty_pet_mt3+guj_qty_pet_mt4+ref_qty_mt1+ref_qty_mt2+ref_qty_mt3+
			ref_qty_mt4+guj_qty_ref_mt1+guj_qty_ref_mt2+guj_qty_ref_mt3+guj_qty_ref_mt4+oth_qty_mt1+
			oth_qty_mt2+oth_qty_mt3+oth_qty_mt4+guj_qty_oth_mt1+guj_qty_oth_mt2+guj_qty_oth_mt3+
			guj_qty_oth_mt4+irn_qty_mt1+irn_qty_mt2+irn_qty_mt3+irn_qty_mt4+guj_qty_irn_mt1+
			guj_qty_irn_mt2+guj_qty_irn_mt3+guj_qty_irn_mt4;
			
			resel_tot_mmbtu = ""+nf2.format(qty_mmbtu1+qty_mmbtu2+qty_mmbtu3+qty_mmbtu4+guj_qty_mmbtu1+guj_qty_mmbtu2+guj_qty_mmbtu3+guj_qty_mmbtu4);
			resel_tot_scm = ""+nf2.format(qty_scm1+qty_scm2+qty_scm3+qty_scm4+guj_qty_scm1+guj_qty_scm2+guj_qty_scm3+guj_qty_scm4);
			resel_tot_mt = ""+nf2.format(qty_mt1+qty_mt2+qty_mt3+qty_mt4+guj_qty_mt1+guj_qty_mt2+guj_qty_mt3+guj_qty_mt4);
			
			tea_tot_mmbtu = ""+nf2.format(con_qty_mmbtu1+con_qty_mmbtu2+con_qty_mmbtu3+con_qty_mmbtu4+guj_qty_con_mmbtu1+guj_qty_con_mmbtu2+guj_qty_con_mmbtu3+guj_qty_con_mmbtu4);	
			tea_tot_scm = ""+nf2.format(qty_con_scm1+qty_con_scm2+qty_con_scm3+qty_con_scm4+guj_qty_con_scm1+guj_qty_con_scm2+guj_qty_con_scm3+guj_qty_con_scm4);
			tea_tot_mt = ""+nf2.format(con_qty_mt1+con_qty_mt2+con_qty_mt3+con_qty_mt4+guj_qty_con_mt1+guj_qty_con_mt2+guj_qty_con_mt3+guj_qty_con_mt4);
						
			pow_tot_mmbtu = ""+nf2.format(pow_qty_mmbtu1+pow_qty_mmbtu2+pow_qty_mmbtu3+pow_qty_mmbtu4+guj_qty_pow_mmbtu1+guj_qty_pow_mmbtu2+guj_qty_pow_mmbtu3+guj_qty_pow_mmbtu4);
			pow_tot_scm = ""+nf2.format(pow_qty_scm1+pow_qty_scm2+pow_qty_scm3+pow_qty_scm4+guj_qty_pow_scm1+guj_qty_pow_scm2+guj_qty_pow_scm3+guj_qty_pow_scm4);
			pow_tot_mt = ""+nf2.format(pow_qty_mt1+pow_qty_mt2+pow_qty_mt3+pow_qty_mt4+guj_qty_pow_mt1+guj_qty_pow_mt2+guj_qty_pow_mt3+guj_qty_pow_mt4);
			
			fer_tot_mmbtu = ""+nf2.format(fer_qty_mmbtu1+fer_qty_mmbtu2+fer_qty_mmbtu3+fer_qty_mmbtu4+guj_qty_fer_mmbtu1+guj_qty_fer_mmbtu2+guj_qty_fer_mmbtu3+guj_qty_fer_mmbtu4);
			fer_tot_scm = ""+nf2.format(fer_qty_scm1+fer_qty_scm2+fer_qty_scm3+fer_qty_scm4+guj_qty_fer_scm1+guj_qty_fer_scm2+guj_qty_fer_scm3+guj_qty_fer_scm4);
			fer_tot_mt = ""+nf2.format(fer_qty_mt1+fer_qty_mt2+fer_qty_mt3+fer_qty_mt4+guj_qty_fer_mt1+guj_qty_fer_mt2+guj_qty_fer_mt3+guj_qty_fer_mt4);
			
			pet_tot_mmbtu = ""+nf2.format(pet_qty_mmbtu1+pet_qty_mmbtu2+pet_qty_mmbtu3+pet_qty_mmbtu4+guj_qty_pet_mmbtu1+guj_qty_pet_mmbtu2+guj_qty_pet_mmbtu3+guj_qty_pet_mmbtu4);
			pet_tot_scm = ""+nf2.format(pet_qty_scm1+pet_qty_scm2+pet_qty_scm3+pet_qty_scm4+guj_qty_pet_scm1+guj_qty_pet_scm2+guj_qty_pet_scm3+guj_qty_pet_scm4);
			pet_tot_mt = ""+nf2.format(pet_qty_mt1+pet_qty_mt2+pet_qty_mt3+pet_qty_mt4+guj_qty_pet_mt1+guj_qty_pet_mt2+guj_qty_pet_mt3+guj_qty_pet_mt4);
			
			ref_tot_mmbtu = ""+nf2.format(ref_qty_mmbtu1+ref_qty_mmbtu2+ref_qty_mmbtu3+ref_qty_mmbtu4+guj_qty_ref_mmbtu1+guj_qty_ref_mmbtu2+guj_qty_ref_mmbtu3+guj_qty_ref_mmbtu4);
			ref_tot_scm = ""+nf2.format(ref_qty_scm1+ref_qty_scm2+ref_qty_scm3+ref_qty_scm4+guj_qty_ref_scm1+guj_qty_ref_scm2+guj_qty_ref_scm3+guj_qty_ref_scm4);
			ref_tot_mt = ""+nf2.format(ref_qty_mt1+ref_qty_mt2+ref_qty_mt3+ref_qty_mt4+guj_qty_ref_mt1+guj_qty_ref_mt2+guj_qty_ref_mt3+guj_qty_ref_mt4);
			
			oth_tot_mmbtu = ""+nf2.format(oth_qty_mmbtu1+oth_qty_mmbtu2+oth_qty_mmbtu3+oth_qty_mmbtu4+guj_qty_oth_mmbtu1+guj_qty_oth_mmbtu2+guj_qty_oth_mmbtu3+guj_qty_oth_mmbtu4);
			oth_tot_scm = ""+nf2.format(oth_qty_scm1+oth_qty_scm2+oth_qty_scm3+oth_qty_scm4+guj_qty_oth_scm1+guj_qty_oth_scm2+guj_qty_oth_scm3+guj_qty_oth_scm4);
			oth_tot_mt = ""+nf2.format(oth_qty_mt1+oth_qty_mt2+oth_qty_mt3+oth_qty_mt4+guj_qty_oth_mt1+guj_qty_oth_mt2+guj_qty_oth_mt3+guj_qty_oth_mt4);
			
			irn_tot_mmbtu = ""+nf2.format(irn_qty_mmbtu1+irn_qty_mmbtu2+irn_qty_mmbtu3+irn_qty_mmbtu4+guj_qty_irn_mmbtu1+guj_qty_irn_mmbtu2+guj_qty_irn_mmbtu3+guj_qty_irn_mmbtu4);
			irn_tot_scm = ""+nf2.format(irn_qty_scm1+irn_qty_scm2+irn_qty_scm3+irn_qty_scm4+guj_qty_irn_scm1+guj_qty_irn_scm2+guj_qty_irn_scm3+guj_qty_irn_scm4);
			irn_tot_mt = ""+nf2.format(irn_qty_mt1+irn_qty_mt2+irn_qty_mt3+irn_qty_mt4+guj_qty_irn_mt1+guj_qty_irn_mt2+guj_qty_irn_mt3+guj_qty_irn_mt4);
			
			tot_mmbtu = ""+nf2.format(sum_mmbtu);
			tot_scm = ""+nf2.format(sum_scm);
			tot_mt = ""+nf2.format(sum_mt);
		  }
		  else
		  {
		  }
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchRLngImportFinYearState()-->"+e);
			  e.printStackTrace();
		}
	}
	
	
	public void fetchRLngImportFinYear()
	{
		try
		{								
		  mnth="04";
		  String mm="04";
		  String yy="";
		  String year1="";	
		  int cnt;
		  int con_cnt =0;
		  int days=0;
		  Vector DAYS= new Vector();
		  //Vector GCV = new Vector();
		  //Vector CON_GCV = new Vector();
		  if(!year.trim().equals("0") && !year.trim().equals(""))
		  {			
			for(int i=0;i<12;i++)
			{
				yy=year; 
				year1=year;
				mnth=""+(Integer.parseInt(mm)+i);
				
				if (Integer.parseInt(mnth)<=12 )
				{
					month=""+ Integer.parseInt(mnth);					
				}				
				else if(Integer.parseInt(mnth)>12)
				{
					month =""+(Integer.parseInt(mnth)%12);
					mnth=""+(Integer.parseInt(mnth)/12);
					year1=""+ (Integer.parseInt(yy)+1);						
				}
						
				////System.out.println("month = "+month+"mnth ="+mnth);
				////System.out.println("year = "+year+",yy ="+yy+",year1= "+year1);
				if(month.trim().equals("1") ||  month.trim().equals("3") ||  
				   month.trim().equals("5") ||  month.trim().equals("7") 
				   ||  month.trim().equals("8") ||  month.trim().equals("10") 
				   ||  month.trim().equals("12"))
				{
					days=31;
				}
				else if(month.trim().equals("4") ||  month.trim().equals("6") ||  
						month.trim().equals("9") ||  month.trim().equals("11"))
				{
					days=30;
				}
				else if(month.trim().equals("2"))
				{
					if( (Integer.parseInt(year1)%4==0))
					{
						days=29;
					}
					else
					{
						days=28;
					}
				}
				DAYS.add(""+days);
				from_date="01"+"/"+month+"/"+year1;
				////System.out.println("from_date = "+from_date);
				queryString ="select TO_CHAR(LAST_DAY(TO_DATE('"+from_date+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
			//	////System.out.println("Last date = "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					to_date=rset.getString(1)==null?"":rset.getString(1);
				}
				else
				{
					to_date="";
				}
				cnt=0;
				
				double mmbtu = 0;
				double con_mmbtu =0;
				double mt = 0;
				double con_mt = 0;
				double mmscm = 0;
				double con_mmscm = 0;
				
				queryString ="select COUNT(A.GCV) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(GCV)"+queryString);
				if(rset.next())
				{		
					cnt = rset.getInt(1);												
				}
				queryString ="select SUM(A.GCV) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
					"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND "+
					"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L')  order by A.GAS_DT";	
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(GCV) = "+queryString);
				if(rset.next())
				{	
					if(cnt>0)
					{
						//AVG_GCV.add(rset.getString(1)==null?"-":nf2.format(Double.parseDouble(rset.getString(1))/cnt));	
					}
					else
					{
						//AVG_GCV.add(rset.getString(1)==null?"-":nf2.format(Double.parseDouble(rset.getString(1))));
					}
				}
				else
				{
					//AVG_GCV.add("-");
				}
				con_cnt=0;
				queryString ="select COUNT(A.GCV) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='000' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";
				rset = stmt.executeQuery(queryString);
			//	//System.out.println("SUM(CONT_GCV)"+queryString);
				if(rset.next())
				{		
					con_cnt = rset.getInt(1);													
				}
				queryString ="select SUM(A.GCV) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='000' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy')AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";
				rset = stmt.executeQuery(queryString);
			//	//System.out.println("SUM(CONT_GCV)"+queryString);
				if(rset.next())
				{	
					////System.out.println("con_cnt = "+con_cnt);
					if(con_cnt>0)
					{
						//AVG_CON_GCV.add(rset.getString(1)==null?"-":nf2.format(Double.parseDouble(rset.getString(1))/con_cnt));
					}
					else
					{
						//AVG_CON_GCV.add(rset.getString(1)==null?"-":nf2.format(Double.parseDouble(rset.getString(1))));
					}
				}
				else
				{
					//AVG_CON_GCV.add("-");
				}
				
				Vector GAS_DT=new Vector();
				Vector plant=new Vector();
				Vector cust=new Vector();
				queryString ="select distinct(to_char(A.gas_dt,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO  from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') ";	
				//System.out.println("Fetch Gas_dt"+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
				GAS_DT.add(rset.getString(1)==null?"":rset.getString(1));
				cust.add(rset.getString(2)==null?"":rset.getString(2));
				plant.add(rset.getString(3)==null?"":rset.getString(3));
				}
				String temp="0";
				String temp1="0";
				String sum1="";
				for(int j=0;j<GAS_DT.size();j++)
				{
					//queryString ="select sum(distinct(A.QTY_MMBTU)) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
					//"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND "+
				//	"A.GAS_DT = to_date('"+GAS_DT.elementAt(j)+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";	
					queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+GAS_DT.elementAt(j)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust.elementAt(j)+"' AND PLANT_SEQ_NO='"+plant.elementAt(j)+"'" +
					" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
					
					rset = stmt.executeQuery(queryString);
					////System.out.println("SUM(QTY_MMBTU)"+queryString);
					if(rset.next())
					{	
						temp=(Double.parseDouble(temp)+Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)))+"";
						
						if(temp!=null && !(temp.trim().equals("")))
						{
							//mmbtu = Double.parseDouble(nf1.format(Double.parseDouble(temp)/100))*100;
							if(Double.parseDouble(temp) <99){
								if(Double.parseDouble(temp) >10){
									mmbtu = Double.parseDouble(temp)- (Double.parseDouble(temp) % 10);
								}}else{
									mmbtu = Double.parseDouble(temp)- (Double.parseDouble(temp) % 100);
							    }
						
							//mt = Double.parseDouble(nf1.format((Double.parseDouble(temp)*0.0193)/10))*10;
							mt = Double.parseDouble(nf1.format((Double.parseDouble(temp)*0.0193)));
							
						}
						else
						{
							mmbtu = 0;
							mt = 0;
							//QTY_MT.add("-");
						}
					}
					else
					{
						mmbtu = 0;
						mt = 0;
						//QTY_MMBTU.add("-");
						//QTY_MT.add("-");
					}
					
					//queryString ="select sum(distinct(A.QTY_SCM)) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
					//"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR='000' AND "+
					//"A.GAS_DT = to_date('"+GAS_DT.elementAt(j)+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";	
					queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+GAS_DT.elementAt(j)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust.elementAt(j)+"' AND PLANT_SEQ_NO='"+plant.elementAt(j)+"'" +
					" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
					
					rset = stmt.executeQuery(queryString);
					////System.out.println("SUM(QTY_SCM)"+queryString);
					if(rset.next())
					{	
						temp1=(Double.parseDouble(temp1)+Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)))+"";
						
						mmscm = Double.parseDouble(nf1.format(Double.parseDouble(temp1)/1000000));
					}
					else
					{
						mmscm = 0;
						//QTY_SCM.add("-");
					}
				}
				double t=0;
				if(Double.parseDouble(temp) <99){
					if(Double.parseDouble(temp) >10){
						t = Double.parseDouble(temp)- (Double.parseDouble(temp) % 10);
					}}else{
						t = Double.parseDouble(temp)- (Double.parseDouble(temp) % 100);
				    }
				
				sum1=nf2.format(t)+"";
				////System.out.println("sum1::"+sum1+"temp:"+temp+"temp1:"+temp1);
				QTY_MMBTU.add(sum1);
			//	QTY_MT.add(nf2.format(Double.parseDouble(nf1.format((Double.parseDouble(temp)*0.0193)/10))*10));
				QTY_MT.add(nf2.format(Double.parseDouble(nf1.format((Double.parseDouble(temp)*0.0193)))));
				QTY_SCM.add(nf2.format(Double.parseDouble(temp1)/1000000)+"");
				
				Vector GAS_DT1=new Vector();
				Vector plant1=new Vector();
				Vector cust1=new Vector();
				queryString ="select distinct(to_char(A.gas_dt,'dd/mm/yyyy')),A.CUSTOMER_CD,A.PLANT_SEQ_NO from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='000' AND "+
				"A.GAS_DT between to_date('"+from_date+"', 'dd/mm/yyyy') AND to_date('"+to_date+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') ";	
				////System.out.println("Fetch Gas_dt"+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
				GAS_DT1.add(rset.getString(1)==null?"":rset.getString(1));
				cust1.add(rset.getString(2)==null?"":rset.getString(2));
				plant1.add(rset.getString(3)==null?"":rset.getString(3));
				}
				String temp_1="0";
				String temp_2="0";
				String sum2="0";
				for(int j=0;j<GAS_DT1.size();j++)
				{
			//	queryString ="select sum(distinct(A.QTY_MMBTU)) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
			//	"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='000' AND "+
			//	"A.GAS_DT = to_date('"+GAS_DT1.elementAt(j)+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";	
					queryString ="select SUM(QTY_MMBTU) from FMS7_DAILY_ALLOCATION_DTL  " +
					"where GAS_DT = to_date('"+GAS_DT1.elementAt(j)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust1.elementAt(j)+"' AND PLANT_SEQ_NO='"+plant1.elementAt(j)+"'" +
					" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
				
				////System.out.println("SUM(QTY_MMBTU)"+queryString);
				rset = stmt.executeQuery(queryString);
				
				if(rset.next())
				{		
					temp_1=(Double.parseDouble(temp_1)+Double.parseDouble(rset.getString(1)==null?"0":rset.getString(1)))+"";
					
					if(temp_1!=null && !(temp_1.trim().equals("")))
					{
					//	con_mmbtu = Double.parseDouble(nf1.format(Double.parseDouble(temp_1)/100))*100;
						
						if(Double.parseDouble(temp_1) <99){
							if(Double.parseDouble(temp_1) >10){
								con_mmbtu = Double.parseDouble(temp_1)-(Double.parseDouble(temp_1) % 10);
							}}else{
								con_mmbtu = Double.parseDouble(temp_1)-(Double.parseDouble(temp_1) % 100);
						    }
					//	con_mt = Double.parseDouble(nf1.format((Double.parseDouble(temp_1)*0.0193)/10))*10;
						con_mt = Double.parseDouble(nf1.format((Double.parseDouble(temp_1)*0.0193)));
					}
					else
					{
						con_mmbtu = 0;
						con_mt = 0;
						//CON_QTY_MT.add("-");
					}
				}
				else
				{
					con_mmbtu = 0;
					con_mt = 0;
					//CON_QTY_MMBTU.add("-");
					//CON_QTY_MT.add("-");
				}
				
				//queryString ="select sum(distinct(A.QTY_SCM)) from FMS7_DAILY_ALLOCATION_DTL A,FMS7_CUSTOMER_PLANT_DTL B where "+
				//"A.CUSTOMER_CD=B.CUSTOMER_CD AND A.PLANT_SEQ_NO=B.SEQ_NO AND B.PLANT_SECTOR!='000' AND "+
				//"A.GAS_DT = to_date('"+GAS_DT1.elementAt(j)+"','dd/mm/yyyy') AND (A.CONTRACT_TYPE = 'S' OR A.CONTRACT_TYPE = 'L') order by A.GAS_DT";	
				queryString ="select SUM(QTY_SCM) from FMS7_DAILY_ALLOCATION_DTL  " +
				"where GAS_DT = to_date('"+GAS_DT1.elementAt(j)+"', 'dd/mm/yyyy') AND CUSTOMER_CD='"+cust1.elementAt(j)+"' AND PLANT_SEQ_NO='"+plant1.elementAt(j)+"'" +
				" AND (CONTRACT_TYPE = 'S' OR CONTRACT_TYPE = 'L')";				
			
				rset = stmt.executeQuery(queryString);
				////System.out.println("SUM(QTY_SCM)"+queryString);
				if(rset.next())
				{	
					temp_2=(Double.parseDouble(temp_2)+Double.parseDouble(rset.getString(1)==null?"":rset.getString(1)))+"";
					con_mmscm = Double.parseDouble(nf1.format(Double.parseDouble(temp_2)/1000000));
				}
				else
				{
					con_mmscm = 0;
				//	CON_QTY_SCM.add("-");
				}//////System.out.println("GCV = "+GCV+", CON_GCV = "+CON_GCV+", days"+DAYS);
				
				}
				double t1=0;
				if(Double.parseDouble(temp_1) <99){
					if(Double.parseDouble(temp_1) >10){
						t1 = Double.parseDouble(temp_1)- (Double.parseDouble(temp_1) % 10);
					}}else{
						t1 = Double.parseDouble(temp_1)- (Double.parseDouble(temp_1) % 100);
				    }
				sum2=nf2.format(t1)+"";
				////System.out.println("sum2::"+sum2+"temp_1:"+temp_1+"temp_2:"+temp_2);
				CON_QTY_MMBTU.add(sum2);
				//CON_QTY_MT.add(nf2.format(Double.parseDouble(nf1.format((Double.parseDouble(temp_1)*0.0193)/10))*10));
				CON_QTY_MT.add(nf2.format(Double.parseDouble(nf1.format((Double.parseDouble(temp_1)*0.0193)))));
				CON_QTY_SCM.add(nf2.format(Double.parseDouble(temp_2)/1000000)+"");
				
				if(con_mmscm>0 && con_mmbtu>0)
				{
					AVG_CON_GCV.add(nf2.format((con_mmbtu*0.252)/con_mmscm));
				}
				else
				{
					AVG_CON_GCV.add("-");
				}
				
				if(mmscm>0 && mmbtu>0)
				{
					AVG_GCV.add(nf2.format((mmbtu*0.252)/mmscm));
				}
				else
				{
					AVG_GCV.add("-");
				}
			}			
			/*for(int j=0;j<GCV.size();j++)
			{
				if(!(""+GCV.elementAt(j)).trim().equals("") && !(""+GCV.elementAt(j)).trim().equals("-") && !(""+DAYS.elementAt(j)).trim().equals(""))
				{
					AVG_GCV.add(nf2.format(Double.parseDouble(""+GCV.elementAt(j))/Double.parseDouble(""+DAYS.elementAt(j))));
				}
				else
				{
					AVG_GCV.add("-");
				}
			}
			
			for(int j=0;j<CON_GCV.size();j++)
			{
				if(!(""+CON_GCV.elementAt(j)).trim().equals("") && !(""+CON_GCV.elementAt(j)).trim().equals("-"))
				{
					AVG_CON_GCV.add(nf2.format(Double.parseDouble(""+CON_GCV.elementAt(j))/Double.parseDouble(""+DAYS.elementAt(j))));						
				}
				else
				{
					AVG_CON_GCV.add("-");
				}
			}*/			
		  }
		  else
		  {
		  }
		}		
		catch(Exception e)
		{
			  //System.out.println("Exception in Databean_MgmtReport--->fetchRLngImportFinYear()-->"+e);
			  e.printStackTrace();
		}
	}
	//Introduce by Milan Dalsaniya	MD20111122
	public void fetchSNWiseMargin()
	{
		
		try
		{
			
//			//System.out.println(">>>>>>>>>>>>>>>>fetchSNWiseMargin()");
			Vector fgsa_no = new Vector();
			Vector fgsa_rev_no = new Vector();
			
			if(!customer_cd.equals("0"))
			{
				
				queryString ="select A.CUSTOMER_CD, A.SN_NO, A.SN_REV_NO," +
				" TO_CHAR(A.START_DT,'DD/MM/YYYY'), TO_CHAR(A.END_DT,'DD/MM/YYYY')," +
				" A.rate, A.SN_REF_NO, FGSA_NO, FGSA_REV_NO" +
				" from FMS7_SN_MST A "+
				" WHERE " +
				" A.START_DT <= TO_DATE('"+to_date+"','DD/MM/YYYY')" +
				" AND A.END_DT >= TO_DATE('"+from_date+"','DD/MM/YYYY')" +
				" and A.CUSTOMER_CD = '"+customer_cd+"'" +
				" order by A.customer_cd, A.fgsa_no, A.fgsa_REV_NO,A.SN_NO, A.SN_REV_NO,A.START_DT";
			}
			else
			{
				
				queryString ="select A.CUSTOMER_CD, A.SN_NO, A.SN_REV_NO," +
				" TO_CHAR(A.START_DT,'DD/MM/YYYY'), TO_CHAR(A.END_DT,'DD/MM/YYYY')," +
				" A.rate, A.SN_REF_NO, FGSA_NO, FGSA_REV_NO" +
				" from FMS7_SN_MST A "+
				" WHERE " +
				" A.START_DT <= TO_DATE('"+to_date+"','DD/MM/YYYY')" +
				" AND A.END_DT >= TO_DATE('"+from_date+"','DD/MM/YYYY')" +
				" order by A.customer_cd, A.fgsa_no, A.fgsa_REV_NO,A.SN_NO, A.SN_REV_NO,A.START_DT";
			}
//			//System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			
			
			while(rset.next())
			{
				CUSTOMER_CD_SN_M.add(rset.getString(1)==null?"0":rset.getString(1));
				SN_NO_M.add(rset.getString(2)==null?"":rset.getString(2));
				SN_REV_NO_M.add(rset.getString(3)==null?"":rset.getString(3));
				START_DT_SN_M.add(rset.getString(4)==null?"":rset.getString(4));
				END_DT_SN_M.add(rset.getString(5)==null?"":rset.getString(5));
				RATE_SN_M.add(rset.getString(6)==null?"-":rset.getString(6));
				
				fgsa_no.add(rset.getString(8)==null?"-":rset.getString(8));
				fgsa_rev_no.add(rset.getString(9)==null?"-":rset.getString(9));
			}
			
			for(int i=0;i<CUSTOMER_CD_SN_M.size();i++)
			{
				queryString ="select CUSTOMER_ABBR,CUSTOMER_NAME,CUSTOMER_CD from FMS7_CUSTOMER_MST where CUSTOMER_CD="+CUSTOMER_CD_SN_M.elementAt(i)+" ";
				rset = stmt.executeQuery(queryString);
				//	  //System.out.println(queryString);
				if(rset.next())
				{
					CUSTOMER_ABR_SN_M.add(rset.getString(2)==null?"":rset.getString(2));					
				}
				else
				{
					CUSTOMER_ABR_SN_M.add("");
				}
				
				
				
				queryString ="select CARGO_REF_NO, ALLOC_QTY, MARGIN, COST_PRICE" +
				" from FMS7_SN_CARGO_DTL A" +
				" where sn_no = '"+SN_NO_M.elementAt(i)+"' and fgsa_no = '"+fgsa_no.elementAt(i)+"' " +
				" and fgsa_rev_no = '"+fgsa_rev_no.elementAt(i)+"' and customer_cd = '"+CUSTOMER_CD_SN_M.elementAt(i)+"'" +
				" and sn_rev_no = '"+SN_REV_NO_M.elementAt(i)+"'";
				rset = stmt.executeQuery(queryString);
				//	  //System.out.println("SN CARGO DETAIL "+queryString);
				
				int cnt =0;
				String cargo_margin = "";
				String cargo_margin1 = "";
				String cargo_ref_no = "";
				double cargo_cost_price = 0;
				boolean flg = false;
				int k =0;
				/*	//System.out.println("SN "+SN_NO_M.elementAt(i));
				 //System.out.println("SN rev "+SN_REV_NO_M.elementAt(i));
				 //System.out.println("fgsa no "+fgsa_no.elementAt(i));
				 //System.out.println("FGSA REV "+fgsa_rev_no.elementAt(i));
				 
				 */
				while(rset.next())
				{
					
					//	//System.out.println((rset.getString(1)==null?"-":rset.getString(1)));
					CARGO_REF_NO_SN_M.add(rset.getString(1)==null?"-":rset.getString(1));
					cargo_ref_no = rset.getString(1)==null?"0":rset.getString(1);
					CARGO_ALLOC_QTY_SN_M.add(rset.getString(2)==null?"-":rset.getString(2));
					cargo_margin = rset.getString(3)==null?"-":rset.getString(3);
					
					CARGO_COST_PRICE_SN_M.add(rset.getString(4)==null?"-":rset.getString(4));
					cargo_cost_price = Double.parseDouble((rset.getString(4)==null?"0":rset.getString(4)));
					
					////System.out.println("CARGO "+cargo_ref_no); 
					//	//System.out.println(">>>>>>>>>>>>>>>>");
					//===== ADJUSTMENTS FOR MARGIN=========
					if(cargo_margin.equals("-"))
					{
						
						queryString ="select MARGIN, COST_PRICE " +
						" from FMS7_SN_CARGO_DTL A " +
						" where sn_no = '"+SN_NO_M.elementAt(i)+"' and fgsa_no = '"+fgsa_no.elementAt(i)+"' " +
						" and fgsa_rev_no = '"+fgsa_rev_no.elementAt(i)+"' and customer_cd = '"+CUSTOMER_CD_SN_M.elementAt(i)+"'  " +
						" and CARGO_REF_NO = '"+cargo_ref_no+"' and sn_rev_no ='0' ";
						
						rset4 = stmt4.executeQuery(queryString);
						
						String rset4_1 ="";
						if(rset4.next())
						{
							rset4_1 = rset4.getString(1)==null?"-":rset4.getString(1);
							////System.out.println("TEST 111111 1");
							if(!rset4_1.equals("-") && !rset4_1.equals("0") && !rset4_1.equals(""))
							{
								////System.out.println("TEST 111111 2");
								flg = true;
								cargo_margin1 = rset4_1;
								
								CARGO_MARGIN_SN_M.add(rset4_1);
								
								//	  //System.out.println("TEST 111111");
							}
							//	  //System.out.println("TEST 111111");
							
						}
						else 
						{
							////System.out.println("TEST 111111 3");
							queryString ="select MARGIN, COST_PRICE " +
							" from FMS7_SN_CARGO_DTL A " +
							" where sn_no = '"+SN_NO_M.elementAt(i)+"' and fgsa_no = '"+fgsa_no.elementAt(i)+"' " +
							" and fgsa_rev_no = '"+fgsa_rev_no.elementAt(i)+"' and customer_cd = '"+CUSTOMER_CD_SN_M.elementAt(i)+"'  " +
							" and CARGO_REF_NO = '"+cargo_ref_no+"' ";
							
							rset3 = stmt3.executeQuery(queryString);
							//	  //System.out.println("TEST 111111");
							String rset3_1 ="";
							while(rset3.next())
							{
								////System.out.println("TEST 111111 while");
								rset3_1 = rset3.getString(1)==null?"-":rset3.getString(1);
								if(!rset3_1.equals("0") && !rset3_1.equals("-") && !rset3_1.equals(""))
								{
									//	  //System.out.println("TEST 111111 4");
									flg = true;
									cargo_margin1 = rset3_1;
									CARGO_MARGIN_SN_M.add(rset3_1);
									
									//	  //System.out.println("TEST 111111");
									break;
								}
								
							}
							
							
						}
						if(!flg)
						{
							//	//System.out.println("TEST 111111 5");
							CARGO_MARGIN_SN_M.add(nf.format(Double.parseDouble(RATE_SN_M.elementAt(i).toString())-cargo_cost_price));
							cargo_margin1 = ""+(Double.parseDouble(RATE_SN_M.elementAt(i).toString())-cargo_cost_price);
							
							//	  //System.out.println("TEST 111111");
						}
					}
					else
					{
						//	//System.out.println("SN CARGO DETAIL "+queryString);
						
						cargo_margin1 = rset.getString(3)==null?"-":rset.getString(3);
						CARGO_MARGIN_SN_M.add(rset.getString(3)==null?"-":rset.getString(3));
					}
					
					queryString = "select B.SHIP_NAME FROM FMS7_CARGO_NOMINATION A, FMS7_SHIP_MST B " +
					"where A.CARGO_REF_CD='"+rset.getString(1)+"' and A.SHIP_CD=B.SHIP_CD";
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next())
					{				
						CARGO_NM_SN_M.add(rset1.getString(1)==null?"":rset1.getString(1));
					}
					else
					{
						CARGO_NM_SN_M.add("-");
					}
					cnt++;
					k++;
					//	//System.out.println("FLAG "+flg+" "+cargo_margin+" "+cargo_margin1);
					//	//System.out.println(">>>>>>>>>>>>>>>");
				}
				if(cnt==0)
				{
					CUSTOMER_CD_SN_M.setElementAt("N",i);
					SN_NO_M.setElementAt("N",i);
					SN_REV_NO_M.setElementAt("N",i);
					START_DT_SN_M.setElementAt("N",i);
					END_DT_SN_M.setElementAt("N",i);
					RATE_SN_M.setElementAt("N",i);
					fgsa_no.setElementAt("N",i);
					fgsa_rev_no.setElementAt("N",i);
					
				}
				//	  //System.out.println("a1 "+a1);
				//	  //System.out.println("a2 "+a2);
				
				cargo_cnt.add(""+cnt);
			}
			
//			CALCULATION OF WIEGHTED MARGIN FOR EACH REVISION
//			MINIMUM, MAXIMUM AND AVARAGE WIEGHTED MARGINE
			
			
			FGSA_NO_M = fgsa_no;
			FGSA_REV_NO_M = fgsa_rev_no;
			
			String Sfgsa_no = "";
			String Sfgsa_rev_no = "";
			String Ssn_no = "";
			String Ssn_rev_no = "";
			String Scust_no = "";
			double xy_new =0;
			double x_plus_x_new =0;
			double cal_margin1 = 0;
			double cal_margin2 = 0;
			double x_plus_x_old = 0;
			double xy_old = 0;
			double Final = 0;
			int j =0 ;
			boolean kk = false;
			
			double ni = 0;
			double avg = 0;
			double max = 0;
			double min = 0;
			int x = 0;
			if(!cargo_cnt.isEmpty() && Integer.parseInt(cargo_cnt.firstElement().toString())!=0)
			{
				for(int k=0; k<Integer.parseInt(cargo_cnt.firstElement().toString()); k++)
				{
					xy_new+= Double.parseDouble(CARGO_MARGIN_SN_M.elementAt(k).toString().trim())*Double.parseDouble(CARGO_ALLOC_QTY_SN_M.elementAt(k).toString().trim());
					x_plus_x_new+= Double.parseDouble(CARGO_ALLOC_QTY_SN_M.elementAt(k).toString().trim());
				}
			}
			max = xy_new/x_plus_x_new;
			min = xy_new/x_plus_x_new;
			xy_new = 0;
			x_plus_x_new = 0;
			
			for (int i=0; i<SN_NO_M.size(); i++)
			{
				if(!SN_NO_M.elementAt(i).toString().trim().equals("N"))
				{
					if(Scust_no.trim().equalsIgnoreCase(CUSTOMER_CD_SN_M.elementAt(i).toString().trim()) && 
							Sfgsa_no.trim().equalsIgnoreCase(FGSA_NO_M.elementAt(i).toString().trim()) &&
							Sfgsa_rev_no.trim().equalsIgnoreCase(FGSA_REV_NO_M.elementAt(i).toString().trim()) &&
							Ssn_no.trim().equalsIgnoreCase(SN_NO_M.elementAt(i).toString().trim()))
					{
						kk = true;
						if(!Ssn_rev_no.trim().equalsIgnoreCase(SN_REV_NO_M.elementAt(i).toString().trim()))
						{
							////System.out.println(" from if ");
							xy_new = 0;
							x_plus_x_new = 0;
							if(Integer.parseInt(cargo_cnt.elementAt(i).toString())!=0)
							{
								for(int k=0; k<Integer.parseInt(cargo_cnt.elementAt(i).toString()); k++)
								{
									////System.out.println(i+" "+j);
									////System.out.println("CARGO "+CARGO_REF_NO_SN_M.elementAt(j));
									xy_new+= Double.parseDouble(CARGO_MARGIN_SN_M.elementAt(j).toString().trim())*Double.parseDouble(CARGO_ALLOC_QTY_SN_M.elementAt(j).toString().trim());
									x_plus_x_new+= Double.parseDouble(CARGO_ALLOC_QTY_SN_M.elementAt(j).toString().trim());
									j++;
								}
							}
							cal_margin1 = xy_old+xy_new;
							cal_margin2 = x_plus_x_old+x_plus_x_new;
							////System.out.println("cal_margin1 "+cal_margin1);
							////System.out.println("cal_margin2 "+cal_margin2);
							if(cal_margin2 == 0)
							{
								
								Final = 0;
							}
							else
							{
								Final = cal_margin1/cal_margin2;
							}
							//			CARGO_TOT_MARGIN_SN_M.setElementAt(nf7.format(Final),i);
							ni = Final;
							CARGO_TOT_MARGIN_SN_M.add(nf7.format(Final));
							////System.out.println("Final "+Final);
						}
						else
						{
							cal_margin1 = 0;
							cal_margin2 = 0;
						}
						if(kk)
						{
							xy_old += xy_new;
							x_plus_x_old += x_plus_x_new;
						}
					}
					else
					{
						kk = false;
						////System.out.println(SN_NO_M.elementAt(i).toString().trim()+" "+SN_REV_NO_M.elementAt(i).toString().trim());
						xy_new = 0;
						x_plus_x_new = 0;
						if(Integer.parseInt(cargo_cnt.elementAt(i).toString())!=0)
						{
							////System.out.println(" from else ");
							for(int k=0; k<Integer.parseInt(cargo_cnt.elementAt(i).toString()); k++)
							{
								////System.out.println(i+" "+j);
								xy_new+= Double.parseDouble(CARGO_MARGIN_SN_M.elementAt(j).toString().trim())*Double.parseDouble(CARGO_ALLOC_QTY_SN_M.elementAt(j).toString().trim());
								x_plus_x_new+= Double.parseDouble(CARGO_ALLOC_QTY_SN_M.elementAt(j).toString().trim());
								j++;
							}
						}
						cal_margin1 = xy_new;
						cal_margin2 = x_plus_x_new;
						////System.out.println("cal_margin1 "+cal_margin1);
						////System.out.println("cal_margin2 "+cal_margin2);
						if(cal_margin2 == 0)
						{
							
							Final = 0;
						}
						else
						{
							Final = cal_margin1/cal_margin2;
						}
						//		CARGO_TOT_MARGIN_SN_M.setElementAt(nf7.format(Final),i);
						CARGO_TOT_MARGIN_SN_M.add(nf7.format(Final));
						ni = Final;
						
						////System.out.println("Final "+Final);
						
						cal_margin1 = 0;
						cal_margin2 = 0;
						
						xy_old = xy_new;
						x_plus_x_old = x_plus_x_new;
					}
					Sfgsa_no = FGSA_NO_M.elementAt(i).toString().trim();
					Sfgsa_rev_no = FGSA_REV_NO_M.elementAt(i).toString().trim();
					Ssn_no = SN_NO_M.elementAt(i).toString().trim();
					Ssn_rev_no = SN_REV_NO_M.elementAt(i).toString().trim();
					Scust_no = CUSTOMER_CD_SN_M.elementAt(i).toString().trim();
					
					if(ni<min)
					{
						min = ni;
					}
					if(ni>max)
					{
						max = ni;
					}
					
					avg +=Final;
					x++;
				}
				else
				{
					CARGO_TOT_MARGIN_SN_M.add("N");
				}
				
				////System.out.println(">>>>>>>>>>>>>>");
				
			}
			avg = avg/x;
			max_margin = nf7.format(max);
			min_margin = nf7.format(min);
			avg_margin = nf7.format(avg);
			
			
			int k=0;
			for(int i=0; i<SN_NO_M.size(); i++)
			{
				if(!SN_NO_M.elementAt(i).toString().trim().equals("N"))
				{
					queryString = "select distinct(sn_ref_no) from fms7_sn_mst " +
					" where sn_no = '"+SN_NO_M.elementAt(i).toString().trim()+"' and fgsa_no = '"+FGSA_NO_M.elementAt(i).toString().trim()+"' " +
					" and fgsa_rev_no = '"+FGSA_REV_NO_M.elementAt(i).toString().trim()+"' and customer_cd = '"+CUSTOMER_CD_SN_M.elementAt(i).toString().trim()+"'";
					
					rset = stmt.executeQuery(queryString);
					while(rset.next())
					{
						if(!(rset.getString(1)==null?"N": rset.getString(1)).equals("N"))
						{
							k++;
							SN_REF_NO_M.add(rset.getString(1)==null?SN_NO_M.elementAt(i).toString().trim() : rset.getString(1));
						}
					}
					if(k==0)
					{
						SN_REF_NO_M.add(SN_NO_M.elementAt(i).toString().trim());
					}
					else
					{
						k =0;
					}
				}
				else
				{
					SN_REF_NO_M.add("N");
				}
			}
			
				
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchSNWiseMargin()-->"+e);
			e.printStackTrace();
		}
		
	}
	
	////Introduce by Milan Dalsaniya	20111123
	public void fetchCargoWiseMargin()
	{
		try
		{
			
			////System.out.println(">>>>>>>>>>>>>>>>milan fetchCargoWiseMargin "+Seller_cd);
			Vector conf_vol = new Vector();
			Vector unit_abr = new Vector();
			Vector CARGO_PRICE_M_tmp = new Vector();
			if(!Seller_cd.equals("0"))
			{
				
				queryString = "SELECT A.CARGO_REF_CD, A.PRICE, TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY')," +
				"A.CONFIRM_VOL, C.UNIT_ABR, A.MAN_CD, A.MAN_CONF_CD, D.TRD_CD" +
				" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_UNIT_MST C, FMS7_MAN_REQ_MST D" +
				" WHERE A.DELV_FROM_DT <= TO_DATE('"+to_date+"','DD/MM/YYYY')" +
				" AND A.DELV_TO_DT >= TO_DATE('"+from_date+"','DD/MM/YYYY') " +
				" and D.TRD_CD = '"+Seller_cd+"'" +
				" and A.UNIT_CD=C.UNIT_CD" +
				" and A.MAN_CD = D.MAN_CD" +
				" order by A.DELV_FROM_DT";
			}
			else
			{
				queryString = "SELECT A.CARGO_REF_CD, A.PRICE, TO_CHAR(A.DELV_FROM_DT,'DD/MM/YYYY'), TO_CHAR(A.DELV_TO_DT,'DD/MM/YYYY')," +
				"A.CONFIRM_VOL, C.UNIT_ABR, A.MAN_CD, A.MAN_CONF_CD, D.TRD_CD" +
				" FROM FMS7_MAN_CONFIRM_CARGO_DTL A, FMS7_UNIT_MST C, FMS7_MAN_REQ_MST D" +
				" WHERE A.DELV_FROM_DT <= TO_DATE('"+to_date+"','DD/MM/YYYY')" +
				" AND A.DELV_TO_DT >= TO_DATE('"+from_date+"','DD/MM/YYYY') " +
				" and A.UNIT_CD=C.UNIT_CD" +
				" and A.MAN_CD = D.MAN_CD" +
				" order by A.DELV_FROM_DT";
			}
			////System.out.println(">>>>>>>>>>"+queryString);
			rset = stmt.executeQuery(queryString);
			
			while(rset.next())
			{
				
				////System.out.println(rset.getString(7));
				CARGO_REF_CD_M.add(rset.getString(1)==null?"0":rset.getString(1));
				CARGO_PRICE_M.add(rset.getString(2)==null?"-":rset.getString(2));
				CARGO_PRICE_M_tmp.add(rset.getString(2)==null?"0":rset.getString(2));
				DELV_FROM_DT_M.add(rset.getString(3)==null?"":rset.getString(3));
				DELV_TO_DT_M.add(rset.getString(4)==null?"":rset.getString(4));
				conf_vol.add(rset.getString(5)==null?"0":rset.getString(5));
				unit_abr.add(rset.getString(6)==null?"0":rset.getString(6));
				MAN_CONF_CD_M.add(rset.getString(8)==null?"0":rset.getString(8));
				MAN_CD_M.add(rset.getString(7)==null?"0":rset.getString(7));
				
				queryString2 = "SELECT TRADER_ABBR FROM FMS7_TRADER_MST " +
				" WHERE TRADER_CD = '"+(rset.getString(9)==null?"0":rset.getString(9))+"'";
				////System.out.println(">>>>>>>>>>"+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
				////System.out.println(">>>>>>>>>>"+queryString2);
				if(rset2.next())
				{
					TRD_CD_M.add(rset2.getString(1)==null?"0":rset2.getString(1));
				}
				else
				{
					TRD_CD_M.add("-");
				}
				
				
			}
			
			double losses_percentage = 0.0;
			double reconciled_qty = 0.0;
			for(int i=0; i<CARGO_REF_CD_M.size(); i++)
			{
				queryString = "select B.VESSEL_NM, to_char(B.ACT_ARRV_DT,'dd/mm/yyyy')" +
				" FROM FMS7_CARGO_ARRIVAL_DTL B" +
				" where B.CARGO_REF_NO='"+CARGO_REF_CD_M.elementAt(i)+"'";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next())
				{				
					CARGO_NM_M.add(rset1.getString(1)==null?"":rset1.getString(1));
					ACT_ARRV_DT_M.add(rset1.getString(2)==null?"":rset1.getString(2));
					
				}
				else
				{
					CARGO_NM_M.add("-");
					ACT_ARRV_DT_M.add("-");
				}
				
				double actual_volume = 0;
				double vol_available_for_sale = 0.0;
				
				queryString2 = "SELECT A.INTERNAL_CONSUMPTION " +
				"FROM FMS7_TANK_MASTER_DTL A WHERE " +
				"A.TANK_DTL_DT=(SELECT MAX(B.TANK_DTL_DT) FROM FMS7_TANK_MASTER_DTL B WHERE " +
				"B.TANK_DTL_DT<=TO_DATE('"+DELV_TO_DT_M.elementAt(i)+"','DD/MM/YYYY'))";
//				////System.out.println("1");
				//////System.out.println("Tank Master Details Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
//				////System.out.println("1.1");
				if(rset2.next())
				{
					String internal_consumption = rset2.getString(1)==null?"0":rset2.getString(1);
					if(!internal_consumption.trim().equals(""))
					{
						losses_percentage = Double.parseDouble(internal_consumption);
					}
				}
				
				queryString2 = "SELECT A.PERCENTAGE " +
				"FROM FMS7_CONSUMPTION_MST A WHERE " +
				"A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CONSUMPTION_MST B WHERE " +
				"B.EFF_DT<=TO_DATE('"+DELV_TO_DT_M.elementAt(i)+"','DD/MM/YYYY'))";
				
//				////System.out.println("Internal Consumption Percentage Fetch Query = "+queryString2);
				rset2 = stmt2.executeQuery(queryString2);
//				////System.out.println("2");
				if(rset2.next())
				{
					String internal_consumption = rset2.getString(1)==null?"0":rset2.getString(1);
					if(!internal_consumption.trim().equals(""))
					{
						losses_percentage = Double.parseDouble(internal_consumption);
					}
				}
//				////System.out.println("3");
				if(unit_abr.elementAt(i).toString().trim().equalsIgnoreCase("MMBTU"))
				{
					////System.out.println("3.1");
					queryString1 = "SELECT SUM(A.RECONCIL_QTY) " +
					"FROM FMS7_CARGO_RECONCIL_DTL A " +
					"WHERE A.CARGO_REF_NO="+CARGO_REF_CD_M.elementAt(i)+"";
					////System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString1); 
					rset1 = stmt1.executeQuery(queryString1);
					////System.out.println("3.2");
					if(rset1.next())
					{
						reconciled_qty = Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1));
					}
					else
					{
						reconciled_qty = 0;
					}
					actual_volume = Double.parseDouble(conf_vol.elementAt(i).toString());
					vol_available_for_sale = (actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100);
					CARGO_VOL_M.add(nf.format(Double.parseDouble(conf_vol.elementAt(i).toString())));
					CARGO_AVL_FOR_SALE_M.add(nf.format(vol_available_for_sale));
					////System.out.println("3.3");
				}
				else
				{
//					////System.out.println("4.1");
					queryString1 = "SELECT SUM(A.RECONCIL_QTY) " +
					"FROM FMS7_CARGO_RECONCIL_DTL A " +
					"WHERE A.CARGO_REF_NO="+CARGO_REF_CD_M.elementAt(i)+"";
					//////System.out.println("Original Cargo Reconcilation Details QTY Fetch Query = "+queryString1); 
					rset1 = stmt1.executeQuery(queryString1);
//					////System.out.println("4.2");
					if(rset1.next())
					{
						reconciled_qty = Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1));
					}
					else
					{
						reconciled_qty = 0;
					}
					
					
					if(unit_abr.elementAt(i).toString().trim().equalsIgnoreCase("TBTU"))
					{
						double l = (Double.parseDouble(conf_vol.elementAt(i).toString())*1000000);
						actual_volume = l;
						vol_available_for_sale = (actual_volume+reconciled_qty) - (((actual_volume+reconciled_qty)*losses_percentage)/100);
						CARGO_AVL_FOR_SALE_M.add(nf.format(vol_available_for_sale));
						CARGO_VOL_M.add(nf.format(l));
					}
					else
					{
						double l = 0;
						CARGO_AVL_FOR_SALE_M.add(nf.format(l));
						CARGO_VOL_M.add(nf.format(l));
					}
				}
				double alloc = 0.0;
				double margin_alloc = 0.0;
				queryString = "select sum(A.MARGIN*A.ALLOC_QTY), sum(A.ALLOC_QTY) " +
				" FROM FMS7_SN_CARGO_DTL A" +
				" where A.CARGO_REF_NO='"+CARGO_REF_CD_M.elementAt(i)+"'" +
				"";
//				////System.out.println("ok");
				rset1=stmt1.executeQuery(queryString);
//				////System.out.println("ok");
				if(rset1.next())
				{		
					alloc += Double.parseDouble(rset1.getString(2)==null?"0":rset1.getString(2));
					margin_alloc += Double.parseDouble(rset1.getString(1)==null?"0":rset1.getString(1));
					//	CARGO_MARGIN_TOT_M.add(rset1.getString(1)==null?"":rset1.getString(1));
					//	CARGO_ALLOC_TOT_M.add(rset1.getString(2)==null?"":rset1.getString(2));
					if(alloc != 0)
					{
						CARGO_MARGIN_TOT_M.add(nf7.format(margin_alloc/alloc));
						CARGO_ALLOC_TOT_M.add(nf7.format(alloc));
					}
					else
					{
						CARGO_MARGIN_TOT_M.add("-");
						CARGO_ALLOC_TOT_M.add("-");	
					}
					
				}
				else
				{
					CARGO_MARGIN_TOT_M.add("-");
					CARGO_ALLOC_TOT_M.add("-");
				}
				
				double custom_dty = CalCustomDutyOfCargo(DELV_TO_DT_M.elementAt(i).toString().trim(),Double.parseDouble(CARGO_PRICE_M_tmp.elementAt(i).toString().trim()));
				//////System.out.println("CARGO ref "+CARGO_REF_CD_M.elementAt(i));
				////System.out.println("CUSTOM + PRICe "+custom_dty+" "+ Double.parseDouble(CARGO_PRICE_M_tmp.elementAt(i).toString().trim()));
				CARGO_PRICE_M_tmp.setElementAt(nf7.format(custom_dty+Double.parseDouble(CARGO_PRICE_M_tmp.elementAt(i).toString().trim())),i);
				
			}
			
			
			
			
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Databean_Contract_MasterReport--->fetchCargoWiseMargin()-->"+e);
			e.printStackTrace();
		}
		
		
	}
	
	//Introduce By Milan Dalsaniya to calculate Custom Duty for the cargo by just passing 
	//effective tax date for the cargo and the amount on which we want to calculate the tax 
	public double CalCustomDutyOfCargo(String delv_to_dt, double conf_price)
	{
		double cd_charge_per_mmbtu = 0;
		try {
			
//			//System.out.println("OK 5  "+delv_to_dt+" "+conf_price);
			double tax_amt = 0;
			String tax_str_cd = "0";
			
			
			queryString1 = "SELECT A.TAX_STR_CD FROM FMS7_CARGO_TAX_MST A " +
			"WHERE A.APP_DATE=(SELECT MAX(B.APP_DATE) FROM FMS7_CARGO_TAX_MST B WHERE " +
			"B.APP_DATE<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY'))";
//			//System.out.println("Custom Duty Details Query = "+queryString1+rset1+stmt1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				tax_str_cd = rset1.getString(1)==null?"0":rset1.getString(1);
				
			}
			
//			//System.out.println("tax_str_cd = "+tax_str_cd);
			
			queryString1 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
			"TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
			"A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
			"B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) ORDER BY A.tax_code DESC";
//			//System.out.println("Query For Finding Out Correct Tax Structure Details = "+queryString1);
			rset1=stmt1.executeQuery(queryString1);
			while(rset1.next())
			{
				
				if(rset1.getString(3).equals("1"))
				{
					tax_amt = (conf_price)*Double.parseDouble(rset1.getString(2))/100;
				}
				else if(rset1.getString(3).equals("2"))
				{
					queryString2 = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
					"TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_str_cd+" AND " +
					"A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_str_cd+" AND " +
					"B.app_date<=TO_DATE('"+delv_to_dt+"','DD/MM/YYYY')) AND A.tax_code="+rset1.getString(4)+"";
//					//System.out.println("Query For Finding Out Tax Which Is Dependent On Other Tax Value = "+queryString2);
					rset2=stmt2.executeQuery(queryString2);
					if(rset2.next())
					{
						if(rset2.getString(3).equals("1"))
						{
							tax_amt = ((Double.parseDouble(""+conf_price)*Double.parseDouble(rset2.getString(2)))/100);
						}
						
						tax_amt = (((tax_amt)*Double.parseDouble(rset1.getString(2)))/100);
					}
					else
					{
						tax_amt = 0.00;
					}			 		
				}
				else
				{
					tax_amt = 0.00;
				}
				
				cd_charge_per_mmbtu += tax_amt;
			}
			
//			//System.out.println("OK 5  "+delv_to_dt+" "+cd_charge_per_mmbtu);
			//CARGO_COST_PER_MMBTU.add(nf2.format(conf_price+cd_charge_per_mmbtu));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cd_charge_per_mmbtu;
	}
	public void AllocationDataFYwise()
	{
		try 
		{
			queryString1 = " SELECT SUPPLIER_ABBR, SUPPLIER_NAME, PLANT_STATE  FROM FMS7_SUPPLIER_MST A, FMS7_SUPPLIER_PLANT_DTL B WHERE A.SUPPLIER_CD=B.SUPPLIER_CD ";
			//System.out.println("Min. Yr "+queryString1+rset1+stmt1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				Supl_abr = rset1.getString(1);
				Supl_nm = rset1.getString(2);
				Supl_plant_state = rset1.getString(3);
			}
			
			int MinYear=0, MinMonth=0,MaxYear=0;
			queryString1 = "SELECT TO_CHAR(MIN(GAS_DT),'YYYY'), TO_CHAR(MIN(GAS_DT),'MM'), TO_CHAR(MAX(GAS_DT),'YYYY') FROM FMS7_DAILY_ALLOCATION_DTL ";
			//System.out.println("Min. Yr "+queryString1+rset1+stmt1);
			rset1 = stmt1.executeQuery(queryString1);
			if(rset1.next())
			{
				MinYear = rset1.getInt(1);
				MinMonth = rset1.getInt(2);	
				MaxYear = rset1.getInt(3);
				AllocatedMinFY=""+MinYear;
				AllocatedMaxFY=""+MaxYear;
				
			}
			if(MinMonth<4)
			{
				AllocatedMinFY = ""+(MinYear-1);
				MinYear = (MinYear-1);
			}
			
			for(int i=MaxYear; i>=MinYear; i--)
			{
				FY_AllocatedData.add(i+"-"+(i+1));
				FY_StartingYr.add(i);
				FY_EndingYr.add(i+1);
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
	String AllocatedMinFY="";
	public String getAllocatedMinFY() {return AllocatedMinFY;}
	String AllocatedMaxFY="";
	public String getAllocatedMaxFY() {return AllocatedMaxFY;}
	Vector FY_AllocatedData= new Vector();
	public Vector getFY_AllocatedData() {return FY_AllocatedData;}	
	Vector FY_StartingYr= new Vector();
	public Vector getFY_StartingYr() {return FY_StartingYr;}
	Vector FY_EndingYr= new Vector();
	public Vector getFY_EndingYr() {return FY_EndingYr;}
	//--------------Setters-------------------------
	public void setCallFlag(String callFlag) {this.callFlag = callFlag;}	
	public void setCargo_ref_cd(String cargo_ref_cd) {this.cargo_ref_cd = cargo_ref_cd;}		
	public void setFrom_date(String from_date) {this.from_date = from_date;}
	public void setTo_date(String to_date) {this.to_date = to_date;}	
	public void setCargo_ref_no(String cargo_ref_no) {this.cargo_ref_no = cargo_ref_no;}
	public void setYear(String year) {this.year = year;}
	public void setMonth(String month) {this.month = month;}
	public void setTrader_cd(String trader_cd) {this.trader_cd = trader_cd;}
	public void setTo_year(String to_year) {this.to_year = to_year;}
	public void setYEAR(Vector year) {YEAR = year;}
	//--------------getters-------------------------
	public String getMonth() {return month;}
	public String getMnth() {return mnth;}		
	public Vector getCARGO_REF_CD() {return CARGO_REF_CD;}	
	public Vector getCUSTOMER_CD() {return CUSTOMER_CD;}
	public Vector getCUSTOMER_NM() {return CUSTOMER_NM;}
	public Vector getQTY_MT() {return QTY_MT;}	
	public Vector getQTY_MMBTU() {return QTY_MMBTU;}
	public Vector getCON_QTY_MMBTU() {return CON_QTY_MMBTU;}
	public Vector getCON_QTY_MT() {return CON_QTY_MT;}
	public Vector getCON_QTY_SCM() {return CON_QTY_SCM;}
	public Vector getQTY_SCM() {return QTY_SCM;}
    public Vector getAVG_CON_GCV() {return AVG_CON_GCV;}
	public Vector getAVG_GCV() {return AVG_GCV;}


	public Vector getGUJ_QTY_MMBTU() {return GUJ_QTY_MMBTU;}
	public Vector getGUJ_QTY_SCM() {return GUJ_QTY_SCM;}
	public String getResel_tot_mmbtu() {return resel_tot_mmbtu;}
	public String getResel_tot_mt() {return resel_tot_mt;}
	public String getResel_tot_scm() {return resel_tot_scm;}
	public Vector getGUJ_CON_QTY_MMBTU() {return GUJ_CON_QTY_MMBTU;}
	public String getTea_tot_mmbtu() {return tea_tot_mmbtu;}
	public String getTea_tot_mt() {return tea_tot_mt;}
	public String getTea_tot_scm() {return tea_tot_scm;}
	public Vector getGUJ_QTY_MT() {return GUJ_QTY_MT;}
	public Vector getGUJ_CON_QTY_MT() {return GUJ_CON_QTY_MT;}
	public Vector getGUJ_CON_QTY_SCM() {return GUJ_CON_QTY_SCM;}
	public Vector getGUJ_POW_QTY_MMBTU() {return GUJ_POW_QTY_MMBTU;}
	public Vector getGUJ_POW_QTY_MT() {return GUJ_POW_QTY_MT;}
	public Vector getGUJ_POW_QTY_SCM() {return GUJ_POW_QTY_SCM;}
	public Vector getPOW_QTY_MMBTU() {return POW_QTY_MMBTU;}
	public Vector getPOW_QTY_MT() {return POW_QTY_MT;}
	public Vector getPOW_QTY_SCM() {return POW_QTY_SCM;}
	public String getPow_tot_mmbtu() {return pow_tot_mmbtu;}
	public String getPow_tot_mt() {return pow_tot_mt;}
	public String getPow_tot_scm() {return pow_tot_scm;}
	public Vector getFER_QTY_MMBTU() {return FER_QTY_MMBTU;}
	public Vector getFER_QTY_MT() {return FER_QTY_MT;}
	public Vector getFER_QTY_SCM() {return FER_QTY_SCM;}
	public Vector getGUJ_FER_QTY_MMBTU() {return GUJ_FER_QTY_MMBTU;}
	public Vector getGUJ_FER_QTY_MT() {return GUJ_FER_QTY_MT;}
	public Vector getGUJ_FER_QTY_SCM() {return GUJ_FER_QTY_SCM;}
	public String getFer_tot_mmbtu() {return fer_tot_mmbtu;}
	public String getFer_tot_mt() {return fer_tot_mt;}
	public String getFer_tot_scm() {return fer_tot_scm;}
	public String getPet_tot_mmbtu() {return pet_tot_mmbtu;}
	public String getPet_tot_mt() {return pet_tot_mt;}
	public String getPet_tot_scm() {return pet_tot_scm;}
	public Vector getGUJ_PET_QTY_MMBTU() {return GUJ_PET_QTY_MMBTU;}
	public Vector getGUJ_PET_QTY_MT() {return GUJ_PET_QTY_MT;}
	public Vector getGUJ_PET_QTY_SCM() {return GUJ_PET_QTY_SCM;}
	public Vector getPET_QTY_MMBTU() {return PET_QTY_MMBTU;}
	public Vector getPET_QTY_MT() {return PET_QTY_MT;}
	public Vector getPET_QTY_SCM() {return PET_QTY_SCM;}
	public String getRef_tot_mmbtu() {return ref_tot_mmbtu;}
	public String getRef_tot_mt() {return ref_tot_mt;}
	public String getRef_tot_scm() {return ref_tot_scm;}
	public Vector getGUJ_REF_QTY_MMBTU() {return GUJ_REF_QTY_MMBTU;}
	public Vector getGUJ_REF_QTY_MT() {return GUJ_REF_QTY_MT;}
	public Vector getGUJ_REF_QTY_SCM() {return GUJ_REF_QTY_SCM;}
	public Vector getREF_QTY_MMBTU() {return REF_QTY_MMBTU;}
	public Vector getREF_QTY_MT() {return REF_QTY_MT;}
	public Vector getREF_QTY_SCM() {return REF_QTY_SCM;}
	public Vector getGUJ_IRN_QTY_MMBTU() {return GUJ_IRN_QTY_MMBTU;}
	public Vector getGUJ_IRN_QTY_MT() {return GUJ_IRN_QTY_MT;}
	public Vector getGUJ_IRN_QTY_SCM() {return GUJ_IRN_QTY_SCM;}
	public Vector getGUJ_OTH_QTY_MMBTU() {return GUJ_OTH_QTY_MMBTU;}
	public Vector getGUJ_OTH_QTY_MT() {return GUJ_OTH_QTY_MT;}
	public Vector getGUJ_OTH_QTY_SCM() {return GUJ_OTH_QTY_SCM;}
	public Vector getIRN_QTY_MMBTU() {return IRN_QTY_MMBTU;}
	public Vector getIRN_QTY_MT() {return IRN_QTY_MT;}
	public Vector getIRN_QTY_SCM() {return IRN_QTY_SCM;}
	public Vector getOTH_QTY_MMBTU() {return OTH_QTY_MMBTU;}
	public Vector getOTH_QTY_MT() {return OTH_QTY_MT;}
	public Vector getOTH_QTY_SCM() {return OTH_QTY_SCM;}
	public String getIrn_tot_mmbtu() {return irn_tot_mmbtu;}
	public String getIrn_tot_mt() {return irn_tot_mt;}
	public String getIrn_tot_scm() {return irn_tot_scm;}
	public String getOth_tot_mmbtu() {return oth_tot_mmbtu;}
	public String getOth_tot_mt() {return oth_tot_mt;}
	public String getOth_tot_scm() {return oth_tot_scm;}
	public String getTot_mmbtu() {return tot_mmbtu;}
	public String getTot_mt() {return tot_mt;}
	public String getTot_scm() {return tot_scm;}
	public String getGuj_tot_mmbtu1() {return guj_tot_mmbtu1;}
	public String getGuj_tot_mmbtu2() {return guj_tot_mmbtu2;}
	public String getGuj_tot_mmbtu3() {return guj_tot_mmbtu3;}
	public String getGuj_tot_mmbtu4() {return guj_tot_mmbtu4;}
	public String getGuj_tot_mt1() {return guj_tot_mt1;}
	public String getGuj_tot_mt2() {return guj_tot_mt2;}
	public String getGuj_tot_mt3() {return guj_tot_mt3;}
	public String getGuj_tot_mt4() {return guj_tot_mt4;}
	public String getGuj_tot_scm1() {return guj_tot_scm1;}
	public String getGuj_tot_scm2() {return guj_tot_scm2;}
	public String getGuj_tot_scm3() {return guj_tot_scm3;}
	public String getGuj_tot_scm4() {return guj_tot_scm4;}
	public String getTot_mmbtu1() {return tot_mmbtu1;}
	public String getTot_mmbtu2() {return tot_mmbtu2;}
	public String getTot_mmbtu3() {return tot_mmbtu3;}
	public String getTot_mmbtu4() {return tot_mmbtu4;}
	public String getTot_mt1() {return tot_mt1;}
	public String getTot_mt2() {return tot_mt2;}
	public String getTot_mt3() {return tot_mt3;}
	public String getTot_mt4() {return tot_mt4;}
	public String getTot_scm1() {return tot_scm1;}
	public String getTot_scm2() {return tot_scm2;}
	public String getTot_scm3() {return tot_scm3;}
	public String getTot_scm4() {return tot_scm4;}



	public Vector getSN_NO() {
		return SN_NO;
	}

	public Vector getFGSA_NO() {
		return FGSA_NO;
	}

	public Vector getTCQ() {
		return TCQ;
	}

	public Vector getTENDER_NO() {
		return TENDER_NO;
	}

	public Vector getLOA_NO() {
		return LOA_NO;
	}
	
	public Vector getCONT_TYPE() {
		return CONT_TYPE;
	}

	public Vector getCOUNT() {
		return COUNT;
	}

	public Vector getBALANCE() {
		return BALANCE;
	}

	public String getTotal() {
		return total;
	}

	public Vector getTOTAL() {
		return TOTAL;
	}

	public Vector getCUSTOMER_CD_RE_GAS() {
		return CUSTOMER_CD_RE_GAS;
	}

	public Vector getCUSTOMER_NM_RE_GAS() {
		return CUSTOMER_NM_RE_GAS;
	}

	public Vector getRE_GAS_NO() {
		return RE_GAS_NO;
	}

	public Vector getRE_GAS_CARGO_NO() {
		return RE_GAS_CARGO_NO;
	}

	public Vector getCAPACITY() {
		return CAPACITY;
	}

	public Vector getQTY_MMBTU_RE_GAS() {
		return QTY_MMBTU_RE_GAS;
	}

	public Vector getBALANCE_RE_GAS() {
		return BALANCE_RE_GAS;
	}

	public String getTotal_regas() {
		return total_regas;
	}

	public Vector getTOTAL_RE_GAS() {
		return TOTAL_RE_GAS;
	}

	public Vector getGAS_DT() {
		return GAS_DT;
	}

	public Vector getTOTAL_QTY_SCM() {
		return TOTAL_QTY_SCM;
	}

	public Vector getTOTAL_QTY_MMBTU() {
		return TOTAL_QTY_MMBTU;
	}

	public Vector getGCV() {
		return GCV;
	}

	public Vector getKCAL_SCM() {
		return KCAL_SCM;
	}

	//Following (5) Vector Getter Methods Has Been Defined By Samik Shah On 14th February, 2011 ...
	public Vector getINNER_CUSTOMER_CD_RE_GAS() {return INNER_CUSTOMER_CD_RE_GAS;}
	public Vector getINNER_CUSTOMER_CD_SN_LOA() {return INNER_CUSTOMER_CD_SN_LOA;}
	public Vector getRE_GAS_OUTER_COUNTER() {return RE_GAS_OUTER_COUNTER;}
	public Vector getSN_LOA_OUTER_COUNTER() {return SN_LOA_OUTER_COUNTER;}
	public Vector getFINAL_COUNT() {return FINAL_COUNT;}
	public Vector getSEND_OUT_REMARK() {return SEND_OUT_REMARK;}

	//Following (1) int Getter Method Has Been Defined By Samik Shah On 14th February, 2011 ...
	public int getMax_count_for_column() {return max_count_for_column;}

	//Following (2) Vector Getter Methods Has Been Defined By Samik Shah On 17th February, 2011 ...
	public Vector getRE_GAS_TOTAL_BALANCE() {return RE_GAS_TOTAL_BALANCE;}
	public Vector getSN_LOA_TOTAL_BALANCE() {return SN_LOA_TOTAL_BALANCE;}

	//Following (8) String Getter Methods Has Been Defined By Samik Shah On 18th February, 2011 ...
	public String getPrev_month_sales() {return prev_month_sales;}
	public String getPrev_month_regas() {return prev_month_regas;}
	public String getPrev_month_total_sales() {return prev_month_total_sales;}
	public String getPrev_month_sales_mcm() {return prev_month_sales_mcm;}
	public String getPrev_month_regas_mcm() {return prev_month_regas_mcm;}
	public String getPrev_month_total_sales_mcm() {return prev_month_total_sales_mcm;}
	public String getSelected_month_year() {return selected_month_year;}
	public String getSelected_prev_month_year() {return selected_prev_month_year;}

	//Following (32) String Getter Methods Has Been Defined By Samik Shah On 19th February, 2011 ...
	public String getMonth_opening_stock() {return month_opening_stock;}
	public String getMonth_opening_stock_mcm() {return month_opening_stock_mcm;}
	public String getMonth_opening_stock_regas() {return month_opening_stock_regas;}
	public String getMonth_opening_stock_regas_mcm() {return month_opening_stock_regas_mcm;}
	public String getMonth_opening_stock_sales() {return month_opening_stock_sales;}
	public String getMonth_opening_stock_sales_mcm() {return month_opening_stock_sales_mcm;}
	public String getDead_stock() {return dead_stock;}
	public String getDead_stock_mcm() {return dead_stock_mcm;}
	public String getVolume_received_sales() {return volume_received_sales;}
	public String getVolume_received_sales_mcm() {return volume_received_sales_mcm;}
	public String getVolume_received_regas() {return volume_received_regas;}
	public String getVolume_received_regas_mcm() {return volume_received_regas_mcm;}
	public String getVolume_received_total() {return volume_received_total;}
	public String getVolume_received_total_mcm() {return volume_received_total_mcm;}
	public String getMonth_sales() {return month_sales;}
	public String getMonth_regas() {return month_regas;}
	public String getMonth_total_sales() {return month_total_sales;}
	public String getMonth_sales_mcm() {return month_sales_mcm;}
	public String getMonth_regas_mcm() {return month_regas_mcm;}
	public String getMonth_total_sales_mcm() {return month_total_sales_mcm;}
	public String getVolume_expected_sales() {return volume_expected_sales;}
	public String getVolume_expected_sales_mcm() {return volume_expected_sales_mcm;}
	public String getVolume_expected_regas() {return volume_expected_regas;}
	public String getVolume_expected_regas_mcm() {return volume_expected_regas_mcm;}
	public String getVolume_expected_total() {return volume_expected_total;}
	public String getVolume_expected_total_mcm() {return volume_expected_total_mcm;}
	public String getInternal_consumption_sales() {return internal_consumption_sales;}
	public String getInternal_consumption_sales_mcm() {return internal_consumption_sales_mcm;}
	public String getInternal_consumption_regas() {return internal_consumption_regas;}
	public String getInternal_consumption_regas_mcm() {return internal_consumption_regas_mcm;}
	public String getInternal_consumption_total() {return internal_consumption_total;}
	public String getInternal_consumption_total_mcm() {return internal_consumption_total_mcm;}

	//Following double Getter Method Has Been Defined By Samik Shah On 19th February, 2011 ...
	public double getConsumption_percentage() {return consumption_percentage;}

	//Following (20) String Getter Methods Has Been Defined By Samik Shah On 21st February, 2011 ...
	public String getOutstanding_commitment_sales() {return outstanding_commitment_sales;}
	public String getOutstanding_commitment_sales_mcm() {return outstanding_commitment_sales_mcm;}
	public String getOutstanding_commitment_regas() {return outstanding_commitment_regas;}
	public String getOutstanding_commitment_regas_mcm() {return outstanding_commitment_regas_mcm;}
	public String getOutstanding_commitment_total() {return outstanding_commitment_total;}
	public String getOutstanding_commitment_total_mcm() {return outstanding_commitment_total_mcm;}
	public String getRe_commitment_sales() {return re_commitment_sales;}
	public String getRe_commitment_sales_mcm() {return re_commitment_sales_mcm;}
	public String getInternal_consumption_re_commitment_sales() {return internal_consumption_re_commitment_sales;}
	public String getInternal_consumption_re_commitment_sales_mcm() {return internal_consumption_re_commitment_sales_mcm;}
	public String getNet_uncommited_sales() {return net_uncommited_sales;}
	public String getNet_uncommited_sales_mcm() {return net_uncommited_sales_mcm;}
	public String getNet_uncommited_regas() {return net_uncommited_regas;}
	public String getNet_uncommited_regas_mcm() {return net_uncommited_regas_mcm;}
	public String getNet_uncommited_total() {return net_uncommited_total;}
	public String getNet_uncommited_total_mcm() {return net_uncommited_total_mcm;}
	public String getNet_uncommited_overcommited_sales() {return net_uncommited_overcommited_sales;}
	public String getNet_uncommited_overcommited_sales_mcm() {return net_uncommited_overcommited_sales_mcm;}
	public String getNet_uncommited_overcommited_total() {return net_uncommited_overcommited_total;}
	public String getNet_uncommited_overcommited_total_mcm() {return net_uncommited_overcommited_total_mcm;}


	//Following (8) Vector Getter Methods Has Been Defined By Samik Shah On 25th February, 2011 ...
	public Vector getQTY_MMSCM() {return QTY_MMSCM;}
	public Vector getQTY_MMSCM_FIRM() {return QTY_MMSCM_FIRM;}
	public Vector getQTY_MMSCM_RE_SALES() {return QTY_MMSCM_RE_SALES;}
	public Vector getQTY_MMSCM_TOTAL_FIRM_RE() {return QTY_MMSCM_TOTAL_FIRM_RE;}
	public Vector getQTY_MMSCM_RE_GAS() {return QTY_MMSCM_RE_GAS;}
	public Vector getQTY_MMSCM_RE_GAS_FIRM() {return QTY_MMSCM_RE_GAS_FIRM;}
	public Vector getSALES_END_DT() {return SALES_END_DT;}
	public Vector getRE_GAS_END_DT() {return RE_GAS_END_DT;}

	//Following (5) String Getter Methods are related to SN/LOA Contracts TOTAL -- Has Been Defined By Samik Shah On 25th February, 2011 ...
	public String getTotal_sales_gas_day_mmbtu_qty_str() {return total_sales_gas_day_mmbtu_qty_str;}
	public String getTotal_sales_gas_day_mmscm_qty_str() {return total_sales_gas_day_mmscm_qty_str;}
	public String getSum_sales_firm_qty_str() {return sum_sales_firm_qty_str;}
	public String getSum_sales_re_qty_str() {return sum_sales_re_qty_str;}
	public String getSum_sales_re_firm_qty_str() {return sum_sales_re_firm_qty_str;}

	//Following (3) String Getter Methods are related to Re-GAS Contracts TOTAL -- Has Been Defined By Samik Shah On 25th February, 2011 ...
	public String getTotal_regas_gas_day_mmbtu_qty_str() {return total_regas_gas_day_mmbtu_qty_str;}
	public String getTotal_regas_gas_day_mmscm_qty_str() {return total_regas_gas_day_mmscm_qty_str;}
	public String getTotal_regas_firm_mmscm_qty_str() {return total_regas_firm_mmscm_qty_str;}

	//Following (2) String Getter Methods are related to Future Cargo Arrival TOTAL -- Has Been Defined By Samik Shah On 25th February, 2011 ...
	public String getTotal_expected_delivered_m3_qty_str() {return total_expected_delivered_m3_qty_str;}
	public String getTotal_expected_delivered_mmscm_qty_str() {return total_expected_delivered_mmscm_qty_str;}

	//Both the following Vector Getter Methods are Primary Vector Getter Methods ... (Expected Cargo Arrivals)
	//Has Been Defined By Samik Shah On 26th February, 2011 ...
	public Vector getEXP_CARGO_REF_NO() {return EXP_CARGO_REF_NO;}
	public Vector getEXP_VESSEL_NM() {return EXP_VESSEL_NM;}

	//Following All (4) Vector Getter Methods are Primary Vector Getter Methods ... (Expected Cargo Arrivals)
	//Has Been Defined By Samik Shah On 26th February, 2011 ...
	public Vector getEXP_OWN_TP_NM() {return EXP_OWN_TP_NM;}
	public Vector getEXP_M3_LNG() {return EXP_M3_LNG;}
	public Vector getEXP_MMSCM_QTY() {return EXP_MMSCM_QTY;}
	public Vector getEXP_TIME_OF_ARRIVAL() {return EXP_TIME_OF_ARRIVAL;}

	//Following (8) String Getter Methods are related to Tank Inventory on Daily Basis ...
	//Has Been Defined By Samik Shah On 28th February, 2011 ...
	public String getT1_m3_of_lng() {return t1_m3_of_lng;}
	public String getT1_mmscm_of_gas() {return t1_mmscm_of_gas;}
	public String getT2_m3_of_lng() {return t2_m3_of_lng;}
	public String getT2_mmscm_of_gas() {return t2_mmscm_of_gas;}
	public String getSaleable_stock_with_re_gas() {return saleable_stock_with_re_gas;}
	public String getSaleable_stock() {return saleable_stock;}
	public String getAsking_rate_to_avoid_stock_out() {return asking_rate_to_avoid_stock_out;}
	public String getInternal_consumption_on_proposed_volume() {return internal_consumption_on_proposed_volume;}

	//Following (3) String Getter Methods Has Been Defined By Samik Shah On 1st March, 2011 ...
	public String getLast_arrival_date() {return last_arrival_date;}
	public String getLong_short_position_mmscm() {return long_short_position_mmscm;}
	public String getAsking_rate_required_to_accommodate_cargo() {return asking_rate_required_to_accommodate_cargo;}


	public void setTo_month(String to_month) {
		this.to_month = to_month;
	}


	public Vector getCUST_CD() {
		return CUST_CD;
	}


	public Vector getCUST_NM() {
		return CUST_NM;
	}


	public Vector getCALENDAR() {
		return CALENDAR;
	}


	public Vector getGIFT() {
		return GIFT;
	}


	public Vector getDIARY() {
		return DIARY;
	}


	public Vector getLEAFLET() {
		return LEAFLET;
	}


	public Vector getOTHER_1() {
		return OTHER_1;
	}


	public Vector getOTHER_2() {
		return OTHER_2;
	}


	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}


	public Vector getTRADER_CD() {
		return TRADER_CD;
	}


	public Vector getTRADER_NAME() {
		return TRADER_NAME;
	}


	public Vector getTRANSPORTER_CD() {
		return TRANSPORTER_CD;
	}


	public Vector getTRANSPORTER_NAME() {
		return TRANSPORTER_NAME;
	}


	public Vector getOTHER_CD() {
		return OTHER_CD;
	}


	public Vector getOTHER_NAME() {
		return OTHER_NAME;
	}



	public Vector getCONTACT_PERSON() {
		return CONTACT_PERSON;
	}



	public Vector getSEQ_NO() {
		return SEQ_NO;
	}



	public void setCustomer_cd(String customer_cd) {
		this.customer_cd = customer_cd;
	}



	public Vector getDESCRIPTION() {
		return DESCRIPTION;
	}


	public Vector getMONTH_VALUE() {
		return MONTH_VALUE;
	}


	public int getLoop_count() {
		return loop_count;
	}

	//Following (2) String Getter Methods Has Been Introduced By Samik Shah On 25th March, 2011 ...
	public String getDaily_graph_from_date() {return daily_graph_from_date;}
	public String getDaily_graph_to_date() {return daily_graph_to_date;}

	//Following (4) Vector Getter Methods Has Been Introduced By Samik Shah On 25th June, 2011 ...
	public String getTOTAL_QTY_SCM_NUMERIC() {return TOTAL_QTY_SCM_NUMERIC;}
	public String getTOTAL_QTY_MMBTU_NUMERIC() {return TOTAL_QTY_MMBTU_NUMERIC;}
	public Vector getQTY_MMBTU_NUMERIC() {return QTY_MMBTU_NUMERIC;}
	public Vector getQTY_SCM_NUMERIC() {return QTY_SCM_NUMERIC;}


	public Vector getSN_REF_NO() {
		return SN_REF_NO;
	}


	public Vector getREMARK_RE_GAS_NO() {
		return REMARK_RE_GAS_NO;
	}


	public Vector getGAS_DT_REGAS() {
		return GAS_DT_REGAS;
	}


	public Vector getGCV_REGAS() {
		return GCV_REGAS;
	}


	public Vector getKCAL_SCM_REGAS() {
		return KCAL_SCM_REGAS;
	}


	public Vector getQTY_MMBTU_NUMERIC_REGAS() {
		return QTY_MMBTU_NUMERIC_REGAS;
	}


	public Vector getQTY_MMBTU_REGAS() {
		return QTY_MMBTU_REGAS;
	}


	public Vector getQTY_SCM_NUMERIC_REGAS() {
		return QTY_SCM_NUMERIC_REGAS;
	}


	public Vector getQTY_SCM_REGAS() {
		return QTY_SCM_REGAS;
	}


	public String getTOTAL_QTY_MMBTU_NUMERIC_REGAS() {
		return TOTAL_QTY_MMBTU_NUMERIC_REGAS;
	}


	public Vector getTOTAL_QTY_MMBTU_REGAS() {
		return TOTAL_QTY_MMBTU_REGAS;
	}


	public String getTOTAL_QTY_SCM_NUMERIC_REGAS() {
		return TOTAL_QTY_SCM_NUMERIC_REGAS;
	}


	public Vector getTOTAL_QTY_SCM_REGAS() {
		return TOTAL_QTY_SCM_REGAS;
	}


	public Vector getCUSTOMER_CD_REGAS() {
		return CUSTOMER_CD_REGAS;
	}


	public Vector getCUSTOMER_NM_REGAS() {
		return CUSTOMER_NM_REGAS;
	}


	public String getGRAND_TOTAL_MMBTU_NUMERIC() {
		return GRAND_TOTAL_MMBTU_NUMERIC;
	}


	public String getGRAND_TOTAL_SCM_NUMERIC() {
		return GRAND_TOTAL_SCM_NUMERIC;
	}
//	Following (2) Variable Has Been Introduced By JHP On 05th Oct, 2011 ...
	

	public Vector getCARGO_ALLOC_QTY_SN_M() {
		return CARGO_ALLOC_QTY_SN_M;
	}


	public Vector getCargo_cnt() {
		return cargo_cnt;
	}


	public Vector getCARGO_MARGIN_SN_M() {
		return CARGO_MARGIN_SN_M;
	}


	public Vector getCARGO_NM_SN_M() {
		return CARGO_NM_SN_M;
	}


	public Vector getCARGO_REF_NO_SN_M() {
		return CARGO_REF_NO_SN_M;
	}


	public Vector getCARGO_COST_PRICE_SN_M() {
		return CARGO_COST_PRICE_SN_M;
	}


	public Vector getCUSTOMER_ABR_SN_M() {
		return CUSTOMER_ABR_SN_M;
	}


	public Vector getCUSTOMER_CD_SN_M() {
		return CUSTOMER_CD_SN_M;
	}


	public Vector getEND_DT_SN_M() {
		return END_DT_SN_M;
	}


	public Vector getRATE_SN_M() {
		return RATE_SN_M;
	}


	public Vector getSN_NO_M() {
		return SN_NO_M;
	}


	public Vector getSN_REF_NO_M() {
		return SN_REF_NO_M;
	}


	public Vector getSTART_DT_SN_M() {
		return START_DT_SN_M;
	}


	public Vector getCARGO_TOT_MARGIN_SN_M() {
		return CARGO_TOT_MARGIN_SN_M;
	}


	public String getAvg_margin() {
		return avg_margin;
	}


	public String getMax_margin() {
		return max_margin;
	}


	public String getMin_margin() {
		return min_margin;
	}


	public Vector getSELLER_CD() {
		return SELLER_CD;
	}


	public Vector getSELLER_NM() {
		return SELLER_NM;
	}


	public void setSeller_cd(String seller_cd) {
		Seller_cd = seller_cd;
	}


	public Vector getCARGO_REF_CD_M() {
		return CARGO_REF_CD_M;
	}


	public Vector getCARGO_VOL_M() {
		return CARGO_VOL_M;
	}


	public Vector getCARGO_PRICE_M() {
		return CARGO_PRICE_M;
	}


	public Vector getDELV_FROM_DT_M() {
		return DELV_FROM_DT_M;
	}


	public Vector getDELV_TO_DT_M() {
		return DELV_TO_DT_M;
	}


	public Vector getTRD_CD_M() {
		return TRD_CD_M;
	}


	public Vector getACT_ARRV_DT_M() {
		return ACT_ARRV_DT_M;
	}


	public Vector getCARGO_ALLOC_TOT_M() {
		return CARGO_ALLOC_TOT_M;
	}


	public Vector getCARGO_MARGIN_TOT_M() {
		return CARGO_MARGIN_TOT_M;
	}


	public Vector getCARGO_AVL_FOR_SALE_M() {
		return CARGO_AVL_FOR_SALE_M;
	}


	public Vector getCARGO_BAL_M() {
		return CARGO_BAL_M;
	}


	public Vector getCARGO_NM_M() {
		return CARGO_NM_M;
	}


	public Vector getMAN_CONF_CD_M() {
		return MAN_CONF_CD_M;
	}


	public Vector getMAN_CD_M() {
		return MAN_CD_M;
	}


	public Vector getSN_REV_NO_M() {
		return SN_REV_NO_M;
	}


	public Vector getFGSA_NO_M() {
		return FGSA_NO_M;
	}


	public Vector getFGSA_REV_NO_M() {
		return FGSA_REV_NO_M;
	}


	public void setDelv_to_dt(String delv_to_dt) {
		this.delv_to_dt = delv_to_dt;
	}


	public void setConf_price(double conf_price) {
		this.conf_price = conf_price;
	}
//	Following (2) Variable Has Been Introduced By JHP On 05th Oct, 2011 ...
	public boolean isFlag1_sales() {return Flag1_sales;}
	public boolean isFlag2_Re_Gas() {return Flag2_Re_Gas;}
	public Vector getSECTOR_CD() {
		return SECTOR_CD;
	}
	public Vector getSECTOR_NM() {
		return SECTOR_NM;
	}
	public Vector getSel_com_mmbtu() {
		return Sel_com_mmbtu;
	}
	public Vector[] getVsec_qty_val_btu() {
		return vsec_qty_val_btu;
	}
	public Vector[] getVsec_qty_val_mt() {
		return vsec_qty_val_mt;
	}
	public Vector[] getVsec_qty_val_btu_noguj() {
		return vsec_qty_val_btu_noguj;
	}
	public Vector[] getVsec_qty_val_mt_noguj() {
		return vsec_qty_val_mt_noguj;
	}
	public Vector[] getVsec_qty_val_scm() {
		return vsec_qty_val_scm;
	}
	public Vector[] getVsec_qty_val_scm_noguj() {
		return vsec_qty_val_scm_noguj;
	}
	public Vector[] getVsec_qty_val_btu_other() {
		return vsec_qty_val_btu_other;
	}
	public Vector[] getVsec_qty_val_mt_other() {
		return vsec_qty_val_mt_other;
	}
	public Vector[] getVsec_qty_val_scm_other() {
		return vsec_qty_val_scm_other;
	}
	public Vector[] getVsec_qty_val_btu_noguj_other() {
		return vsec_qty_val_btu_noguj_other;
	}
	public Vector[] getVsec_qty_val_mt_noguj_other() {
		return vsec_qty_val_mt_noguj_other;
	}
	public Vector[] getVsec_qty_val_scm_noguj_other() {
		return vsec_qty_val_scm_noguj_other;
	}
	public Vector getSECTOR_NM_other() {
		return SECTOR_NM_other;
	}
	public Vector getSECTOR_CD_other() {
		return SECTOR_CD_other;
	}
	public Vector[] getVsec_qty_val_btu1() {
		return vsec_qty_val_btu1;
	}
	public Vector[] getVsec_qty_val_mt1() {
		return vsec_qty_val_mt1;
	}
	public Vector[] getVsec_qty_val_scm1() {
		return vsec_qty_val_scm1;
	}
	public Vector[] getVsec_qty_val_btu_noguj1() {
		return vsec_qty_val_btu_noguj1;
	}
	public Vector[] getVsec_qty_val_mt_noguj1() {
		return vsec_qty_val_mt_noguj1;
	}
	public Vector[] getVsec_qty_val_scm_noguj1() {
		return vsec_qty_val_scm_noguj1;
	}
	public Vector getSECTOR_CD1() {
		return SECTOR_CD1;
	}
	public Vector getSECTOR_NM1() {
		return SECTOR_NM1;
	}
  public Vector getVparty() {
		return Vparty;
	}
	public Vector getVparty_cont_type() {
		return Vparty_cont_type;
	}


	public Vector getGAS_DT_LTCORA() {
		return GAS_DT_LTCORA;
	}


	public void setGAS_DT_LTCORA(Vector gas_dt_ltcora) {
		GAS_DT_LTCORA = gas_dt_ltcora;
	}


	public Vector getTOTAL_QTY_MMBTU_LTCORA() {
		return TOTAL_QTY_MMBTU_LTCORA;
	}


	public void setTOTAL_QTY_MMBTU_LTCORA(Vector total_qty_mmbtu_ltcora) {
		TOTAL_QTY_MMBTU_LTCORA = total_qty_mmbtu_ltcora;
	}


	public Vector getTOTAL_QTY_SCM_LTCORA() {
		return TOTAL_QTY_SCM_LTCORA;
	}


	public void setTOTAL_QTY_SCM_LTCORA(Vector total_qty_scm_ltcora) {
		TOTAL_QTY_SCM_LTCORA = total_qty_scm_ltcora;
	}


	public Vector getGCV_LTCORA() {
		return GCV_LTCORA;
	}


	public void setGCV_LTCORA(Vector gcv_ltcora) {
		GCV_LTCORA = gcv_ltcora;
	}


	public Vector getKCAL_SCM_LTCORA() {
		return KCAL_SCM_LTCORA;
	}


	public void setKCAL_SCM_LTCORA(Vector kcal_scm_ltcora) {
		KCAL_SCM_LTCORA = kcal_scm_ltcora;
	}


	public String getTOTAL_QTY_MMBTU_NUMERIC_LTCORA() {
		return TOTAL_QTY_MMBTU_NUMERIC_LTCORA;
	}


	public void setTOTAL_QTY_MMBTU_NUMERIC_LTCORA(
			String total_qty_mmbtu_numeric_ltcora) {
		TOTAL_QTY_MMBTU_NUMERIC_LTCORA = total_qty_mmbtu_numeric_ltcora;
	}


	public String getTOTAL_QTY_SCM_NUMERIC_LTCORA() {
		return TOTAL_QTY_SCM_NUMERIC_LTCORA;
	}


	public void setTOTAL_QTY_SCM_NUMERIC_LTCORA(String total_qty_scm_numeric_ltcora) {
		TOTAL_QTY_SCM_NUMERIC_LTCORA = total_qty_scm_numeric_ltcora;
	}


	public Vector getCUSTOMER_CD_LTCORA() {
		return CUSTOMER_CD_LTCORA;
	}


	public void setCUSTOMER_CD_LTCORA(Vector customer_cd_ltcora) {
		CUSTOMER_CD_LTCORA = customer_cd_ltcora;
	}


	public Vector getQTY_MMBTU_LTCORA() {
		return QTY_MMBTU_LTCORA;
	}


	public void setQTY_MMBTU_LTCORA(Vector qty_mmbtu_ltcora) {
		QTY_MMBTU_LTCORA = qty_mmbtu_ltcora;
	}


	public Vector getQTY_SCM_LTCORA() {
		return QTY_SCM_LTCORA;
	}


	public void setQTY_SCM_LTCORA(Vector qty_scm_ltcora) {
		QTY_SCM_LTCORA = qty_scm_ltcora;
	}


	public Vector getQTY_MMBTU_NUMERIC_LTCORA() {
		return QTY_MMBTU_NUMERIC_LTCORA;
	}


	public void setQTY_MMBTU_NUMERIC_LTCORA(Vector qty_mmbtu_numeric_ltcora) {
		QTY_MMBTU_NUMERIC_LTCORA = qty_mmbtu_numeric_ltcora;
	}


	public Vector getQTY_SCM_NUMERIC_LTCORA() {
		return QTY_SCM_NUMERIC_LTCORA;
	}


	public void setQTY_SCM_NUMERIC_LTCORA(Vector qty_scm_numeric_ltcora) {
		QTY_SCM_NUMERIC_LTCORA = qty_scm_numeric_ltcora;
	}


	public Vector getCUSTOMER_NM_LTCORA() {
		return CUSTOMER_NM_LTCORA;
	}


	public void setCUSTOMER_NM_LTCORA(Vector customer_nm_ltcora) {
		CUSTOMER_NM_LTCORA = customer_nm_ltcora;
	}


	public String getGRAND_TOTAL_MMBTU_NUMERIC_LTCORA() {
		return GRAND_TOTAL_MMBTU_NUMERIC_LTCORA;
	}


	public void setGRAND_TOTAL_MMBTU_NUMERIC_LTCORA(
			String grand_total_mmbtu_numeric_ltcora) {
		GRAND_TOTAL_MMBTU_NUMERIC_LTCORA = grand_total_mmbtu_numeric_ltcora;
	}


	public String getGRAND_TOTAL_SCM_NUMERIC_LTCORA() {
		return GRAND_TOTAL_SCM_NUMERIC_LTCORA;
	}


	public void setGRAND_TOTAL_SCM_NUMERIC_LTCORA(
			String grand_total_scm_numeric_ltcora) {
		GRAND_TOTAL_SCM_NUMERIC_LTCORA = grand_total_scm_numeric_ltcora;
	}


	public String getInternal_consumption_LTCORA() {
		return internal_consumption_LTCORA;
	}


	public void setInternal_consumption_LTCORA(String internal_consumption_LTCORA) {
		this.internal_consumption_LTCORA = internal_consumption_LTCORA;
	}


	public String getInternal_consumption_LTCORA_mcm() {
		return internal_consumption_LTCORA_mcm;
	}


	public void setInternal_consumption_LTCORA_mcm(
			String internal_consumption_LTCORA_mcm) {
		this.internal_consumption_LTCORA_mcm = internal_consumption_LTCORA_mcm;
	}


	public Vector getLTCORA_CAPACITY() {
		return LTCORA_CAPACITY;
	}


	public void setLTCORA_CAPACITY(Vector ltcora_capacity) {
		LTCORA_CAPACITY = ltcora_capacity;
	}


	public Vector getLTCORA_CARGO_NO() {
		return LTCORA_CARGO_NO;
	}


	public void setLTCORA_CARGO_NO(Vector ltcora_cargo_no) {
		LTCORA_CARGO_NO = ltcora_cargo_no;
	}


	public Vector getLTCORA_NO() {
		return LTCORA_NO;
	}


	public void setLTCORA_NO(Vector ltcora_no) {
		LTCORA_NO = ltcora_no;
	}


	public Vector getLTCORA_OUTER_COUNTER() {
		return LTCORA_OUTER_COUNTER;
	}


	public void setLTCORA_OUTER_COUNTER(Vector ltcora_outer_counter) {
		LTCORA_OUTER_COUNTER = ltcora_outer_counter;
	}


	public Vector getLTCORA_REMARK_RE_GAS_NO() {
		return LTCORA_REMARK_RE_GAS_NO;
	}


	public void setLTCORA_REMARK_RE_GAS_NO(Vector ltcora_remark_re_gas_no) {
		LTCORA_REMARK_RE_GAS_NO = ltcora_remark_re_gas_no;
	}


	public Vector getLTCORA_TOTAL_BALANCE() {
		return LTCORA_TOTAL_BALANCE;
	}


	public void setLTCORA_TOTAL_BALANCE(Vector ltcora_total_balance) {
		LTCORA_TOTAL_BALANCE = ltcora_total_balance;
	}


	public String getMonth_LTCORA() {
		return month_LTCORA;
	}


	public void setMonth_LTCORA(String month_LTCORA) {
		this.month_LTCORA = month_LTCORA;
	}


	public String getMonth_LTCORA_mcm() {
		return month_LTCORA_mcm;
	}


	public void setMonth_LTCORA_mcm(String month_LTCORA_mcm) {
		this.month_LTCORA_mcm = month_LTCORA_mcm;
	}


	public String getMonth_opening_stock_LTCORA() {
		return month_opening_stock_LTCORA;
	}


	public void setMonth_opening_stock_LTCORA(String month_opening_stock_LTCORA) {
		this.month_opening_stock_LTCORA = month_opening_stock_LTCORA;
	}


	public String getMonth_opening_stock_LTCORA_mcm() {
		return month_opening_stock_LTCORA_mcm;
	}


	public void setMonth_opening_stock_LTCORA_mcm(
			String month_opening_stock_LTCORA_mcm) {
		this.month_opening_stock_LTCORA_mcm = month_opening_stock_LTCORA_mcm;
	}


	public String getNet_uncommited_LTCORA() {
		return net_uncommited_LTCORA;
	}


	public void setNet_uncommited_LTCORA(String net_uncommited_LTCORA) {
		this.net_uncommited_LTCORA = net_uncommited_LTCORA;
	}


	public String getNet_uncommited_LTCORA_mcm() {
		return net_uncommited_LTCORA_mcm;
	}


	public void setNet_uncommited_LTCORA_mcm(String net_uncommited_LTCORA_mcm) {
		this.net_uncommited_LTCORA_mcm = net_uncommited_LTCORA_mcm;
	}


	public String getOutstanding_commitment_LTCORA() {
		return outstanding_commitment_LTCORA;
	}


	public void setOutstanding_commitment_LTCORA(
			String outstanding_commitment_LTCORA) {
		this.outstanding_commitment_LTCORA = outstanding_commitment_LTCORA;
	}


	public String getOutstanding_commitment_LTCORA_mcm() {
		return outstanding_commitment_LTCORA_mcm;
	}


	public void setOutstanding_commitment_LTCORA_mcm(
			String outstanding_commitment_LTCORA_mcm) {
		this.outstanding_commitment_LTCORA_mcm = outstanding_commitment_LTCORA_mcm;
	}


	public String getPrev_month_LTCORA() {
		return prev_month_LTCORA;
	}


	public void setPrev_month_LTCORA(String prev_month_LTCORA) {
		this.prev_month_LTCORA = prev_month_LTCORA;
	}


	public String getPrev_month_LTCORA_mcm() {
		return prev_month_LTCORA_mcm;
	}


	public void setPrev_month_LTCORA_mcm(String prev_month_LTCORA_mcm) {
		this.prev_month_LTCORA_mcm = prev_month_LTCORA_mcm;
	}


	public String getPrev_month_month_LTCORA_mcm() {
		return prev_month_month_LTCORA_mcm;
	}


	public void setPrev_month_month_LTCORA_mcm(String prev_month_month_LTCORA_mcm) {
		this.prev_month_month_LTCORA_mcm = prev_month_month_LTCORA_mcm;
	}


	public String getVolume_expected_LTCORA() {
		return volume_expected_LTCORA;
	}


	public void setVolume_expected_LTCORA(String volume_expected_LTCORA) {
		this.volume_expected_LTCORA = volume_expected_LTCORA;
	}


	public String getVolume_expected_LTCORA_mcm() {
		return volume_expected_LTCORA_mcm;
	}


	public void setVolume_expected_LTCORA_mcm(String volume_expected_LTCORA_mcm) {
		this.volume_expected_LTCORA_mcm = volume_expected_LTCORA_mcm;
	}


	public String getVolume_received_LTCORA() {
		return volume_received_LTCORA;
	}


	public void setVolume_received_LTCORA(String volume_received_LTCORA) {
		this.volume_received_LTCORA = volume_received_LTCORA;
	}


	public String getVolume_received_LTCORA_mcm() {
		return volume_received_LTCORA_mcm;
	}


	public void setVolume_received_LTCORA_mcm(String volume_received_LTCORA_mcm) {
		this.volume_received_LTCORA_mcm = volume_received_LTCORA_mcm;
	}


	public Vector getBALANCE_LTCORA() {
		return BALANCE_LTCORA;
	}


	public void setBALANCE_LTCORA(Vector balance_ltcora) {
		BALANCE_LTCORA = balance_ltcora;
	}


	public Vector getINNER_CUSTOMER_CD_LTCORA() {
		return INNER_CUSTOMER_CD_LTCORA;
	}


	public void setINNER_CUSTOMER_CD_LTCORA(Vector inner_customer_cd_ltcora) {
		INNER_CUSTOMER_CD_LTCORA = inner_customer_cd_ltcora;
	}


	public Vector getLTCORA_CONT_TYPE() {
		return LTCORA_CONT_TYPE;
	}


	public void setLTCORA_CONT_TYPE(Vector ltcora_cont_type) {
		LTCORA_CONT_TYPE = ltcora_cont_type;
	}


	public Vector getQTY_MMSCM_LTCORA() {
		return QTY_MMSCM_LTCORA;
	}


	public void setQTY_MMSCM_LTCORA(Vector qty_mmscm_ltcora) {
		QTY_MMSCM_LTCORA = qty_mmscm_ltcora;
	}


	public boolean isFlag2_LTCORA() {
		return Flag2_LTCORA;
	}


	public void setFlag2_LTCORA(boolean flag2_LTCORA) {
		Flag2_LTCORA = flag2_LTCORA;
	}


	public Vector getLTCORA_END_DT() {
		return LTCORA_END_DT;
	}


	public void setLTCORA_END_DT(Vector ltcora_end_dt) {
		LTCORA_END_DT = ltcora_end_dt;
	}


	public Vector getQTY_MMSCM_LTCORA_FIRM() {
		return QTY_MMSCM_LTCORA_FIRM;
	}


	public void setQTY_MMSCM_LTCORA_FIRM(Vector qty_mmscm_ltcora_firm) {
		QTY_MMSCM_LTCORA_FIRM = qty_mmscm_ltcora_firm;
	}


	public String getTotal_LTCORA_gas_day_mmbtu_qty_str() {
		return total_LTCORA_gas_day_mmbtu_qty_str;
	}


	public void setTotal_LTCORA_gas_day_mmbtu_qty_str(
			String total_LTCORA_gas_day_mmbtu_qty_str) {
		this.total_LTCORA_gas_day_mmbtu_qty_str = total_LTCORA_gas_day_mmbtu_qty_str;
	}


	public String getTotal_LTCORA_gas_day_mmscm_qty_str() {
		return total_LTCORA_gas_day_mmscm_qty_str;
	}


	public void setTotal_LTCORA_gas_day_mmscm_qty_str(
			String total_LTCORA_gas_day_mmscm_qty_str) {
		this.total_LTCORA_gas_day_mmscm_qty_str = total_LTCORA_gas_day_mmscm_qty_str;
	}


	public String getTotal_LTCORA_firm_mmscm_qty_str() {
		return total_LTCORA_firm_mmscm_qty_str;
	}


	public void setTotal_LTCORA_firm_mmscm_qty_str(
			String total_LTCORA_firm_mmscm_qty_str) {
		this.total_LTCORA_firm_mmscm_qty_str = total_LTCORA_firm_mmscm_qty_str;
	}


	public String getFinal_last_arrival_date() {
		return final_last_arrival_date;
	}


	public void setFinal_last_arrival_date(String final_last_arrival_date) {
		this.final_last_arrival_date = final_last_arrival_date;
	}


	public String getAsking_rate_dt() {
		return asking_rate_dt;
	}


	public void setAsking_rate_dt(String asking_rate_dt) {
		this.asking_rate_dt = asking_rate_dt;
	}


	public Vector getRE_GAS_TOTAL_BALANCE_MCM() {
		return RE_GAS_TOTAL_BALANCE_MCM;
	}


	public void setRE_GAS_TOTAL_BALANCE_MCM(Vector rE_GAS_TOTAL_BALANCE_MCM) {
		RE_GAS_TOTAL_BALANCE_MCM = rE_GAS_TOTAL_BALANCE_MCM;
	}


	public Vector getLTCORA_TOTAL_BALANCE_MCM() {
		return LTCORA_TOTAL_BALANCE_MCM;
	}


	public void setLTCORA_TOTAL_BALANCE_MCM(Vector lTCORA_TOTAL_BALANCE_MCM) {
		LTCORA_TOTAL_BALANCE_MCM = lTCORA_TOTAL_BALANCE_MCM;
	}


	public String getSelected_from_date1() {
		return selected_from_date1;
	}


	public void setSelected_from_date1(String selected_from_date1) {
		this.selected_from_date1 = selected_from_date1;
	}


	public String getSelected_from_date() {
		return selected_from_date;
	}


	public void setSelected_from_date(String selected_from_date) {
		this.selected_from_date = selected_from_date;
	}


	public String getCustomer_access_flag() {
		return Customer_access_flag;
	}


	public void setCustomer_access_flag(String customer_access_flag) {
		Customer_access_flag = customer_access_flag;
	}


	public String getEmp_cd() {
		return Emp_cd;
	}


	public void setEmp_cd(String emp_cd) {
		Emp_cd = emp_cd;
	}


	public Vector getLTCORA_TOTAL_UNLOADED_VOLUME() {
		return LTCORA_TOTAL_UNLOADED_VOLUME;
	}


	public void setLTCORA_TOTAL_UNLOADED_VOLUME(Vector lTCORA_TOTAL_UNLOADED_VOLUME) {
		LTCORA_TOTAL_UNLOADED_VOLUME = lTCORA_TOTAL_UNLOADED_VOLUME;
	}


	public Vector getLTCORA_TOTAL_SUG() {
		return LTCORA_TOTAL_SUG;
	}


	public void setLTCORA_TOTAL_SUG(Vector lTCORA_TOTAL_SUG) {
		LTCORA_TOTAL_SUG = lTCORA_TOTAL_SUG;
	}


	public Vector getLTCORA_TOTAL_NET_COMMITTED() {
		return LTCORA_TOTAL_NET_COMMITTED;
	}


	public void setLTCORA_TOTAL_NET_COMMITTED(Vector lTCORA_TOTAL_NET_COMMITTED) {
		LTCORA_TOTAL_NET_COMMITTED = lTCORA_TOTAL_NET_COMMITTED;
	}


	public Vector getLTCORA_TOTAL_VOLUME_EXPECTED() {
		return LTCORA_TOTAL_VOLUME_EXPECTED;
	}


	public void setLTCORA_TOTAL_VOLUME_EXPECTED(Vector lTCORA_TOTAL_VOLUME_EXPECTED) {
		LTCORA_TOTAL_VOLUME_EXPECTED = lTCORA_TOTAL_VOLUME_EXPECTED;
	}


	public Vector getLTCORA_TOTAL_VOLUME_SUPPLIED() {
		return LTCORA_TOTAL_VOLUME_SUPPLIED;
	}


	public void setLTCORA_TOTAL_VOLUME_SUPPLIED(Vector lTCORA_TOTAL_VOLUME_SUPPLIED) {
		LTCORA_TOTAL_VOLUME_SUPPLIED = lTCORA_TOTAL_VOLUME_SUPPLIED;
	}


	public Vector getLTCORA_TOTAL_UNLOADED_VOLUME_MCM() {
		return LTCORA_TOTAL_UNLOADED_VOLUME_MCM;
	}


	public void setLTCORA_TOTAL_UNLOADED_VOLUME_MCM(Vector lTCORA_TOTAL_UNLOADED_VOLUME_MCM) {
		LTCORA_TOTAL_UNLOADED_VOLUME_MCM = lTCORA_TOTAL_UNLOADED_VOLUME_MCM;
	}


	public Vector getLTCORA_TOTAL_SUG_MCM() {
		return LTCORA_TOTAL_SUG_MCM;
	}


	public void setLTCORA_TOTAL_SUG_MCM(Vector lTCORA_TOTAL_SUG_MCM) {
		LTCORA_TOTAL_SUG_MCM = lTCORA_TOTAL_SUG_MCM;
	}


	public Vector getLTCORA_TOTAL_NET_COMMITTED_MCM() {
		return LTCORA_TOTAL_NET_COMMITTED_MCM;
	}


	public void setLTCORA_TOTAL_NET_COMMITTED_MCM(Vector lTCORA_TOTAL_NET_COMMITTED_MCM) {
		LTCORA_TOTAL_NET_COMMITTED_MCM = lTCORA_TOTAL_NET_COMMITTED_MCM;
	}


	public Vector getLTCORA_TOTAL_VOLUME_EXPECTED_MCM() {
		return LTCORA_TOTAL_VOLUME_EXPECTED_MCM;
	}


	public void setLTCORA_TOTAL_VOLUME_EXPECTED_MCM(Vector lTCORA_TOTAL_VOLUME_EXPECTED_MCM) {
		LTCORA_TOTAL_VOLUME_EXPECTED_MCM = lTCORA_TOTAL_VOLUME_EXPECTED_MCM;
	}


	public Vector getLTCORA_TOTAL_VOLUME_SUPPLIED_MCM() {
		return LTCORA_TOTAL_VOLUME_SUPPLIED_MCM;
	}


	public void setLTCORA_TOTAL_VOLUME_SUPPLIED_MCM(Vector lTCORA_TOTAL_VOLUME_SUPPLIED_MCM) {
		LTCORA_TOTAL_VOLUME_SUPPLIED_MCM = lTCORA_TOTAL_VOLUME_SUPPLIED_MCM;
	}


	public Vector getREGAS_TOTAL_UNLOADED_VOLUME() {
		return REGAS_TOTAL_UNLOADED_VOLUME;
	}


	public void setREGAS_TOTAL_UNLOADED_VOLUME(Vector rEGAS_TOTAL_UNLOADED_VOLUME) {
		REGAS_TOTAL_UNLOADED_VOLUME = rEGAS_TOTAL_UNLOADED_VOLUME;
	}


	public Vector getREGAS_TOTAL_SUG() {
		return REGAS_TOTAL_SUG;
	}


	public void setREGAS_TOTAL_SUG(Vector rEGAS_TOTAL_SUG) {
		REGAS_TOTAL_SUG = rEGAS_TOTAL_SUG;
	}


	public Vector getREGAS_TOTAL_NET_COMMITTED() {
		return REGAS_TOTAL_NET_COMMITTED;
	}


	public void setREGAS_TOTAL_NET_COMMITTED(Vector rEGAS_TOTAL_NET_COMMITTED) {
		REGAS_TOTAL_NET_COMMITTED = rEGAS_TOTAL_NET_COMMITTED;
	}


	public Vector getREGAS_TOTAL_VOLUME_EXPECTED() {
		return REGAS_TOTAL_VOLUME_EXPECTED;
	}


	public void setREGAS_TOTAL_VOLUME_EXPECTED(Vector rEGAS_TOTAL_VOLUME_EXPECTED) {
		REGAS_TOTAL_VOLUME_EXPECTED = rEGAS_TOTAL_VOLUME_EXPECTED;
	}


	public Vector getREGAS_TOTAL_VOLUME_SUPPLIED() {
		return REGAS_TOTAL_VOLUME_SUPPLIED;
	}


	public void setREGAS_TOTAL_VOLUME_SUPPLIED(Vector rEGAS_TOTAL_VOLUME_SUPPLIED) {
		REGAS_TOTAL_VOLUME_SUPPLIED = rEGAS_TOTAL_VOLUME_SUPPLIED;
	}


	public Vector getREGAS_TOTAL_UNLOADED_VOLUME_MCM() {
		return REGAS_TOTAL_UNLOADED_VOLUME_MCM;
	}


	public void setREGAS_TOTAL_UNLOADED_VOLUME_MCM(Vector rEGAS_TOTAL_UNLOADED_VOLUME_MCM) {
		REGAS_TOTAL_UNLOADED_VOLUME_MCM = rEGAS_TOTAL_UNLOADED_VOLUME_MCM;
	}


	public Vector getREGAS_TOTAL_SUG_MCM() {
		return REGAS_TOTAL_SUG_MCM;
	}


	public void setREGAS_TOTAL_SUG_MCM(Vector rEGAS_TOTAL_SUG_MCM) {
		REGAS_TOTAL_SUG_MCM = rEGAS_TOTAL_SUG_MCM;
	}


	public Vector getREGAS_TOTAL_NET_COMMITTED_MCM() {
		return REGAS_TOTAL_NET_COMMITTED_MCM;
	}


	public void setREGAS_TOTAL_NET_COMMITTED_MCM(Vector rEGAS_TOTAL_NET_COMMITTED_MCM) {
		REGAS_TOTAL_NET_COMMITTED_MCM = rEGAS_TOTAL_NET_COMMITTED_MCM;
	}


	public Vector getREGAS_TOTAL_VOLUME_EXPECTED_MCM() {
		return REGAS_TOTAL_VOLUME_EXPECTED_MCM;
	}


	public void setREGAS_TOTAL_VOLUME_EXPECTED_MCM(Vector rEGAS_TOTAL_VOLUME_EXPECTED_MCM) {
		REGAS_TOTAL_VOLUME_EXPECTED_MCM = rEGAS_TOTAL_VOLUME_EXPECTED_MCM;
	}


	public Vector getREGAS_TOTAL_VOLUME_SUPPLIED_MCM() {
		return REGAS_TOTAL_VOLUME_SUPPLIED_MCM;
	}


	public void setREGAS_TOTAL_VOLUME_SUPPLIED_MCM(Vector rEGAS_TOTAL_VOLUME_SUPPLIED_MCM) {
		REGAS_TOTAL_VOLUME_SUPPLIED_MCM = rEGAS_TOTAL_VOLUME_SUPPLIED_MCM;
	}


	public String getUser_remark() {
		return user_remark;
	}


	public void setUser_remark(String user_remark) {
		this.user_remark = user_remark;
	}


	public String getEmp_name() {
		return emp_name;
	}


	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}


	public String getEnter_dt() {
		return enter_dt;
	}


	public void setEnter_dt(String enter_dt) {
		this.enter_dt = enter_dt;
	}


	public int getMaxYear() {
		return maxYear;
	}


	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}


	public int getMinYear() {
		return minYear;
	}


	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}


	public String getPlant_st() {
		return plant_st;
	}


	public void setPlant_st(String plant_st) {
		this.plant_st = plant_st;
	}


	


	
}//End Of Class DataBean_Cargo ...